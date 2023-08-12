package com.zifuji.cloud.server.business.module.book.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.base.feign.sys.FileFeignClient;
import com.zifuji.cloud.server.base.properties.ZfjProperties;
import com.zifuji.cloud.server.base.util.ZfjFileUtil;
import com.zifuji.cloud.server.business.db.edu.entity.EduBookEntity;
import com.zifuji.cloud.server.business.db.edu.entity.EduBookSectionContentEntity;
import com.zifuji.cloud.server.business.db.edu.entity.EduBookSectionEntity;
import com.zifuji.cloud.server.business.db.edu.service.EduBookEntityService;
import com.zifuji.cloud.server.business.db.edu.service.EduBookSectionContentEntityService;
import com.zifuji.cloud.server.business.db.edu.service.EduBookSectionEntityService;
import com.zifuji.cloud.server.business.module.book.controller.mo.CreateNewBookMo;
import com.zifuji.cloud.server.business.module.book.controller.qo.QueryPageBookQo;
import com.zifuji.cloud.server.business.module.book.controller.vo.CreateNewBookVo;
import com.zifuji.cloud.server.business.module.book.controller.vo.QueryPageBookVo;
import com.zifuji.cloud.server.business.module.book.mapper.BookMapper;
import com.zifuji.cloud.server.business.module.book.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private ZfjProperties zfjProperties;

    private FileFeignClient fileFeignClient;

    private BookMapper bookMapper;

    private EduBookEntityService eduBookEntityService;

    private EduBookSectionEntityService eduBookSectionEntityService;

    private EduBookSectionContentEntityService eduBookSectionContentEntityService;

    private StringRedisTemplate stringRedisTemplate;

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public CreateNewBookVo createNewBook(CreateNewBookMo createNewBookMo) {
        QueryWrapper<EduBookEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(EduBookEntity::getBookName, createNewBookMo.getBookName())
                .eq(EduBookEntity::getBookAuth, createNewBookMo.getBookAuth())
                .eq(EduBookEntity::getBookType, createNewBookMo.getBookType());
        EduBookEntity eduBookEntity = eduBookEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(eduBookEntity)) {
            eduBookEntity = new EduBookEntity();
            BeanUtil.copyProperties(createNewBookMo, eduBookEntity);
            eduBookEntityService.save(eduBookEntity);
        }
        CreateNewBookVo vo = new CreateNewBookVo();
        BeanUtil.copyProperties(eduBookEntity, vo);
        return vo;
    }

    @Override
    public IPage<QueryPageBookVo> queryPageBook(QueryPageBookQo queryPageBookQo) {
        return null;
    }


    @Override
    public String getNextBookSectionId(Long id) {
        EduBookSectionEntity entity = eduBookSectionEntityService.getById(id);
        if (ObjectUtil.isNull(entity)) {
            throw new Exception20000("");
        }
        QueryWrapper<EduBookSectionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(EduBookSectionEntity::getBookId, entity.getBookId())
                .gt(EduBookSectionEntity::getId, id)
                .orderByAsc(EduBookSectionEntity::getId)
                .last("limit 0,1");
        entity = eduBookSectionEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(entity)) {
            return "0";
        }
        return entity.getId() + "";
    }

    @Override
    public String getLastBookSectionId(Long id) {
        EduBookSectionEntity entity = eduBookSectionEntityService.getById(id);
        if (ObjectUtil.isNull(entity)) {
            throw new Exception20000("");
        }
        QueryWrapper<EduBookSectionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(EduBookSectionEntity::getBookId, entity.getBookId())
                .lt(EduBookSectionEntity::getId, id)
                .orderByDesc(EduBookSectionEntity::getId)
                .last("limit 0,1");
        entity = eduBookSectionEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(entity)) {
            return "0";
        }
        return entity.getId() + "";
    }

    @Override
    public Boolean job() {
        String job = stringRedisTemplate.opsForValue().get("job");
        if (StrUtil.isBlank(job)) {
            log.info("activeCount" + threadPoolTaskExecutor.getActiveCount());
            log.info("poolSize" + threadPoolTaskExecutor.getPoolSize());
            threadPoolTaskExecutor.execute(() -> {
                log.info("threadPoolTaskExecutor run");
                stringRedisTemplate.opsForValue().set("job", "job", 60, TimeUnit.SECONDS);
                String jobNum = stringRedisTemplate.opsForValue().get("jobNum");
                if (StrUtil.isBlank(jobNum)) {
                    Long num = stringRedisTemplate.opsForValue().increment("jobNum");
                    log.info("num" + num);
                }
                String uri = "www.quanben.so";
                String url = "https://" + uri + "/top/postdate/" + jobNum + ".html";
                String html = HttpUtil.get(url);
                String titles = ReUtil.get("<div id=\"main\">(.*?)</div><div class=\"footer clear\">", html, 1);
                List<String> lis = ReUtil.findAll("<li>(.*?)</li>", titles, 1);

                // 这一页所有的小说
                for (String li : lis) {
                    // 类型
                    String type = ReUtil.get("<span class=\\\"s1\\\">\\[(.*?)\\]</span>", li, 1);
                    // 名字
                    String name = ReUtil.get("title=\\\"(.*?)\\\"", li, 1);
                    // 作者
                    String auth = ReUtil.get("<span class=\\\"s4\\\">(.*?)</span>", li, 1);
                    // 最后一章名字
                    String lastSec = ReUtil.get("<span class=\\\"s3\\\">(.*?)</span>", li, 1);
                    String lastSec2 = ReUtil.get(">(.*?)</a>", lastSec, 1);
                    QueryWrapper<EduBookEntity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.lambda()
                            .eq(EduBookEntity::getBookName, name)
                            .eq(EduBookEntity::getBookAuth, auth)
                            .eq(EduBookEntity::getBookType, type);
                    EduBookEntity eduBookEntity = eduBookEntityService.getOne(queryWrapper);
                    Long bId = 0L;
                    if (ObjectUtil.isNull(eduBookEntity)) {
                        eduBookEntity = new EduBookEntity();
                        eduBookEntity.setBookName(name);
                        eduBookEntity.setBookAuth(auth);
                        eduBookEntity.setBookType(type);
                        eduBookEntityService.save(eduBookEntity);
                        bId = eduBookEntity.getId();
                    } else {
                        bId = eduBookEntity.getId();
                    }
                    QueryWrapper<EduBookSectionEntity> eduBookSectionEntityQueryWrapper = new QueryWrapper<>();
                    eduBookSectionEntityQueryWrapper.lambda()
                            .eq(EduBookSectionEntity::getBookId, bId)
                            .eq(EduBookSectionEntity::getSectionName, lastSec2);
                    EduBookSectionEntity eduBookSectionEntity = eduBookSectionEntityService.getOne(eduBookSectionEntityQueryWrapper);
                    // 没有查到说明没有更新到最新一章，所以
                    if (ObjectUtil.isNull(eduBookSectionEntity)) {
                        // 获取书详情
                        String sec = ReUtil.get("<span class=\\\"s2\\\"><a href=\\\"(.*?)\\\" title=", li, 1);
                        String secs = HttpUtil.get(sec);
                        // 封面
                        if (ObjectUtil.isNull(eduBookEntity.getBookImg()) || 0 == eduBookEntity.getBookImg()) {
                            String imgUrl = ReUtil.get("<img width=\\\"120px\\\" height=\\\"180px\\\" src=\\\"(.*?)\\\" alt=\\\"", secs, 1);
                            String tempFilePath = zfjProperties.getTempDirPath() + File.separator + ZfjFileUtil.getUrlFileName(imgUrl);
                            File file = HttpUtil.downloadFileFromUrl(imgUrl, tempFilePath);
                            MultipartFile multipartFile = ZfjFileUtil.getMultipartFile(file);
                            if (ObjectUtil.isNull(multipartFile)) {
                                throw new Exception20000("multipartFile");
                            }
                            Result<String> r = fileFeignClient.uploadFile(multipartFile);
                            if (ObjectUtil.isNotNull(r) && ObjectUtil.isNotNull(r.getResult())) {
                                String fileId = r.getResult();
                                eduBookEntity.setBookImg(Long.valueOf(fileId));
                                eduBookEntityService.updateById(eduBookEntity);
                            }
                        }
                        // 章节名
                        List<String> secnames = ReUtil.findAll("<dd>(.*?)</dd>", secs, 1);
                        QueryWrapper<EduBookSectionEntity> count = new QueryWrapper<>();
                        count.lambda()
                                .eq(EduBookSectionEntity::getBookId, bId);
                        int countint = eduBookSectionEntityService.count(count);
                        for (int j = countint + 12; j < secnames.size(); j++) {
                            String jstr = secnames.get(j);
                            String secname = ReUtil.get(">(.*?)<", jstr, 1);
                            if (StrUtil.isNotBlank(secname)) {
                                QueryWrapper<EduBookSectionEntity> e = new QueryWrapper<>();
                                e.lambda()
                                        .eq(EduBookSectionEntity::getBookId, bId)
                                        .eq(EduBookSectionEntity::getSectionName, secname);
                                EduBookSectionEntity entity = eduBookSectionEntityService.getOne(e);
                                if (ObjectUtil.isNull(entity)) {
                                    entity = new EduBookSectionEntity();
                                    entity.setBookId(bId);
                                    entity.setSortNum((long) j);
                                    entity.setSectionName(secname);
                                    eduBookSectionEntityService.save(entity);
                                    Long sId = entity.getId();
                                    String securl = ReUtil.get("href=\\\"(.*?)\\\"", jstr, 1);
                                    String seccontent = HttpUtil.get(sec + securl);
                                    seccontent = ReUtil.get("<div id=\"content\" name=\"content\">全本小说网 (.*?)</div><center class=\"clear\">", seccontent, 1);
                                    seccontent = ReUtil.delFirst(uri + "，最快更新(.*?) ！<br><br>", seccontent);
                                    EduBookSectionContentEntity eduBookSectionContentEntity = new EduBookSectionContentEntity();
                                    eduBookSectionContentEntity.setSectionId(sId);
                                    eduBookSectionContentEntity.setSectionContent(seccontent);
                                    eduBookSectionContentEntityService.save(eduBookSectionContentEntity);
                                    log.info("入库一个章节");
                                    stringRedisTemplate.delete("job");
                                    return;
                                }
                            }
                        }
                    }
                }
                // 所有小说都走完了 就增加
                Long num = stringRedisTemplate.opsForValue().increment("jobNum");
                log.info("num" + num);
                stringRedisTemplate.delete("job");
            });
        } else {
            log.info("已经存在job直接返回");
        }
        return true;
    }


}
