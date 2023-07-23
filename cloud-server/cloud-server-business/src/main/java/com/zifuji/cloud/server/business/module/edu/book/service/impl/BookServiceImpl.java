package com.zifuji.cloud.server.business.module.edu.book.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.base.feign.sys.FileFeignClient;
import com.zifuji.cloud.server.base.properties.ZfjProperties;
import com.zifuji.cloud.server.base.util.ZfjFileUtil;
import com.zifuji.cloud.server.business.db.edu.entity.EduBookEntity;
import com.zifuji.cloud.server.business.db.edu.entity.EduBookSectionContentEntity;
import com.zifuji.cloud.server.business.db.edu.entity.EduBookSectionEntity;
import com.zifuji.cloud.server.business.db.edu.service.EduBookEntityService;
import com.zifuji.cloud.server.business.db.edu.service.EduBookSectionContentEntityService;
import com.zifuji.cloud.server.business.db.edu.service.EduBookSectionEntityService;
import com.zifuji.cloud.server.business.module.edu.book.mapper.BookMapper;
import com.zifuji.cloud.server.business.module.edu.book.mo.CreateNewBookServiceMo;
import com.zifuji.cloud.server.business.module.edu.book.mo.CreateNewContentServiceMo;
import com.zifuji.cloud.server.business.module.edu.book.mo.CreateNewSectionServiceMo;
import com.zifuji.cloud.server.business.module.edu.book.service.BookService;
import com.zifuji.cloud.server.business.module.edu.book.vo.BookServiceVo;
import com.zifuji.cloud.server.business.module.edu.book.qo.QueryBookServiceQo;
import com.zifuji.cloud.server.business.module.edu.book.vo.BookSectionServiceVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

    @Override
    public Long createNewBook(CreateNewBookServiceMo createNewBookServiceMo) {
        QueryWrapper<EduBookEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(EduBookEntity::getBName, createNewBookServiceMo.getBName())
                .eq(EduBookEntity::getBAuth, createNewBookServiceMo.getBAuth())
                .eq(EduBookEntity::getBType, createNewBookServiceMo.getBType());
        EduBookEntity eduBookEntity = eduBookEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(eduBookEntity)) {
            eduBookEntity = new EduBookEntity();
            eduBookEntity.setBName(createNewBookServiceMo.getBName());
            eduBookEntity.setBType(createNewBookServiceMo.getBType());
            eduBookEntity.setBAuth(createNewBookServiceMo.getBAuth());
            eduBookEntityService.save(eduBookEntity);
        }
        return eduBookEntity.getId();
    }

    @Override
    public Long createNewSection(CreateNewSectionServiceMo createNewSectionServiceMo) {
        QueryWrapper<EduBookSectionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(EduBookSectionEntity::getBId, createNewSectionServiceMo.getBId())
                .eq(EduBookSectionEntity::getSName, createNewSectionServiceMo.getSName());
        EduBookSectionEntity eduBookSectionEntity = eduBookSectionEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(eduBookSectionEntity)) {
            eduBookSectionEntity = new EduBookSectionEntity();
            eduBookSectionEntity.setBId(createNewSectionServiceMo.getBId());
            eduBookSectionEntity.setSName(createNewSectionServiceMo.getSName());
            eduBookSectionEntityService.save(eduBookSectionEntity);
        }
        return eduBookSectionEntity.getId();
    }

    @Override
    public Long createNewContent(CreateNewContentServiceMo createNewContentServiceMo) {
        QueryWrapper<EduBookSectionContentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(EduBookSectionContentEntity::getSId, createNewContentServiceMo.getSId());
        EduBookSectionContentEntity eduBookSectionContentEntity = eduBookSectionContentEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(eduBookSectionContentEntity)) {
            eduBookSectionContentEntity = new EduBookSectionContentEntity();
            eduBookSectionContentEntity.setSId(createNewContentServiceMo.getSId());
            eduBookSectionContentEntity.setCContent(createNewContentServiceMo.getCContent());
            eduBookSectionContentEntityService.save(eduBookSectionContentEntity);
        }
        return eduBookSectionContentEntity.getId();
    }


    @Override
    public IPage<BookServiceVo> queryPageBook(Page page, QueryBookServiceQo queryBookServiceQo) {
        return null;
    }

    @Override
    public List<BookServiceVo> queryListBook(QueryBookServiceQo queryBookServiceQo) {
        return null;
    }


    @Override
    public void job() {
        try {
            job1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BookServiceVo> getShouYeTuiJian() {
        List<BookServiceVo> booServiceVoList = new ArrayList<>();
        List<Map<String, Object>> list = bookMapper.getShouYeTuiJian();
        if (ObjectUtil.isNotEmpty(list)) {
            for (Map<String, Object> map : list) {
                BookServiceVo booServiceVo = new BookServiceVo();
                booServiceVo.setId((Long) map.get("id"));
                booServiceVo.setBAuth((String) map.get("bAuth"));
                booServiceVo.setBName((String) map.get("bName"));
                booServiceVo.setBType((String) map.get("bType"));
                booServiceVo.setBImg((Long) map.get("bImg"));
                booServiceVo.setLastSId((Long) map.get("sId"));
                booServiceVo.setLastSName((String) map.get("sName"));
                booServiceVoList.add(booServiceVo);
            }
        }
        return booServiceVoList;
    }

    @Override
    public List<BookServiceVo> getZuiXinXiaoShuo() {
        List<BookServiceVo> booServiceVoList = new ArrayList<>();
        List<Map<String, Object>> list = bookMapper.getZuiXinXiaoShuo();
        if (ObjectUtil.isNotEmpty(list)) {
            for (Map<String, Object> map : list) {
                BookServiceVo booServiceVo = new BookServiceVo();
                booServiceVo.setId((Long) map.get("id"));
                booServiceVo.setBAuth((String) map.get("bAuth"));
                booServiceVo.setBName((String) map.get("bName"));
                booServiceVo.setBType((String) map.get("bType"));
                booServiceVoList.add(booServiceVo);
            }
        }
        return booServiceVoList;
    }

    @Override
    public BookServiceVo getBookDetail(Long id) {
        BookServiceVo booServiceVo = new BookServiceVo();
        EduBookEntity eduBookEntity = eduBookEntityService.getById(id);
        if (ObjectUtil.isNull(eduBookEntity)) {
            throw new Exception200("");
        }
        booServiceVo.setId(eduBookEntity.getId());
        booServiceVo.setBName(eduBookEntity.getBName());
        booServiceVo.setBAuth(eduBookEntity.getBAuth());
        booServiceVo.setBType(eduBookEntity.getBType());
        booServiceVo.setBImg(eduBookEntity.getBImg());
        return booServiceVo;
    }


    @Override
    public BookSectionServiceVo getBookSectionDetail(Long id) {
        BookSectionServiceVo bookSectionServiceVo = new BookSectionServiceVo();
        EduBookSectionEntity eduBookSectionEntity = eduBookSectionEntityService.getById(id);
        QueryWrapper<EduBookSectionContentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(EduBookSectionContentEntity::getSId, id);
        EduBookSectionContentEntity eduBookSectionContentEntity = eduBookSectionContentEntityService.getOne(queryWrapper);
        bookSectionServiceVo.setSecName(eduBookSectionEntity.getSName());
        bookSectionServiceVo.setSecContent(eduBookSectionContentEntity.getCContent());
        return bookSectionServiceVo;
    }

    @Override
    public List<BookSectionServiceVo> queryListBookSection(QueryBookServiceQo queryBookServiceQo) {
        QueryWrapper<EduBookSectionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(EduBookSectionEntity::getBId, queryBookServiceQo.getBookId())
                .orderByAsc(EduBookSectionEntity::getSortNum)
                .orderByAsc(EduBookSectionEntity::getId);
        List<EduBookSectionEntity> eduBookSectionEntityList = eduBookSectionEntityService.list(queryWrapper);
        return eduBookSectionEntityList.stream().map(eduBookSectionEntity -> {
            BookSectionServiceVo bookSectionServiceVo = new BookSectionServiceVo();
            bookSectionServiceVo.setSecId(eduBookSectionEntity.getId());
            bookSectionServiceVo.setSecName(eduBookSectionEntity.getSName());
            return bookSectionServiceVo;
        }).collect(Collectors.toList());
    }

    @Override
    public String getNextBookSectionId(Long id) {
        EduBookSectionEntity entity = eduBookSectionEntityService.getById(id);
        if(ObjectUtil.isNull(entity)){
            throw new Exception200("");
        }
        QueryWrapper<EduBookSectionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(EduBookSectionEntity::getBId, entity.getBId())
                .gt(EduBookSectionEntity::getId, id)
                .orderByAsc(EduBookSectionEntity::getId)
                .last("limit 0,1");
        entity = eduBookSectionEntityService.getOne(queryWrapper);
        if(ObjectUtil.isNull(entity)){
            return  "0";
        }
        return entity.getId() + "";
    }

    @Override
    public String getLastBookSectionId(Long id) {
        EduBookSectionEntity entity = eduBookSectionEntityService.getById(id);
        if(ObjectUtil.isNull(entity)){
            throw new Exception200("");
        }
        QueryWrapper<EduBookSectionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(EduBookSectionEntity::getBId, entity.getBId())
                .lt(EduBookSectionEntity::getId, id)
                .orderByDesc(EduBookSectionEntity::getId)
                .last("limit 0,1");
        entity = eduBookSectionEntityService.getOne(queryWrapper);
        if(ObjectUtil.isNull(entity)){
            return  "0";
        }
        return entity.getId() + "";
    }


    void job1() throws InterruptedException {
        String job = stringRedisTemplate.opsForValue().get("job");
        if(StrUtil.isNotBlank(job)){
            return;
        }else{
            log.info("开启新线程");
            Thread thread = new Thread(){
                @Override
                public void run() {
                    // 获取到书名列表
                    for (int i = 1; i < 3000; i++) {
                        String url = "https://www.quanben.la/top/postdate/" + i + ".html";
                        String html = HttpUtil.get(url);
                        String titles = ReUtil.get("<div id=\"main\">(.*?)</div><div class=\"footer clear\">", html, 1);
                        List<String> lis = ReUtil.findAll("<li>(.*?)</li>", titles, 1);
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
                            Long bId = 0L;

                            QueryWrapper<EduBookEntity> queryWrapper = new QueryWrapper<>();
                            queryWrapper.lambda()
                                    .eq(EduBookEntity::getBName, name)
                                    .eq(EduBookEntity::getBAuth, auth)
                                    .eq(EduBookEntity::getBType, type);
                            EduBookEntity eduBookEntity = eduBookEntityService.getOne(queryWrapper);
                            if (ObjectUtil.isNull(eduBookEntity)) {
                                eduBookEntity = new EduBookEntity();
                                eduBookEntity.setBName(name);
                                eduBookEntity.setBType(type);
                                eduBookEntity.setBAuth(auth);
                                eduBookEntityService.save(eduBookEntity);
                                bId = eduBookEntity.getId();
                            } else {
                                bId = eduBookEntity.getId();
                            }
                            QueryWrapper<EduBookSectionEntity> eduBookSectionEntityQueryWrapper = new QueryWrapper<>();
                            eduBookSectionEntityQueryWrapper.lambda()
                                    .eq(EduBookSectionEntity::getBId, bId)
                                    .eq(EduBookSectionEntity::getSName, lastSec2);
                            EduBookSectionEntity eduBookSectionEntity = eduBookSectionEntityService.getOne(eduBookSectionEntityQueryWrapper);
                            // 没有查到说明没有更新到最新一章，所以
                            if (ObjectUtil.isNull(eduBookSectionEntity)) {
                                String sec = ReUtil.get("<span class=\\\"s2\\\"><a href=\\\"(.*?)\\\" title=", li, 1);
                                String secs = HttpUtil.get(sec);
                                // 封面
                                if (ObjectUtil.isNull(eduBookEntity.getBImg()) || 0 == eduBookEntity.getBImg()) {
                                    String imgUrl = ReUtil.get("<img width=\\\"120px\\\" height=\\\"180px\\\" src=\\\"(.*?)\\\" alt=\\\"", secs, 1);
                                    String tempFilePath = zfjProperties.getTempDirPath() + File.separator + ZfjFileUtil.getUrlFileName(imgUrl);
                                    File file = HttpUtil.downloadFileFromUrl(imgUrl, tempFilePath);
                                    MultipartFile multipartFile = ZfjFileUtil.getMultipartFile(file);
                                    if (ObjectUtil.isNull(multipartFile)) {
                                        throw new Exception200("multipartFile");
                                    }
                                    Result<String> r = fileFeignClient.uploadFile(multipartFile);
                                    if (ObjectUtil.isNotNull(r) && ObjectUtil.isNotNull(r.getResult())) {
                                        String fileId = r.getResult();
                                        eduBookEntity.setBImg(Long.valueOf(fileId));
                                        eduBookEntityService.updateById(eduBookEntity);
                                    }
                                }
                                // 章节名
                                List<String> secnames = ReUtil.findAll("<dd>(.*?)</dd>", secs, 1);
                                if (secnames.size() < 12) {
                                    continue;
                                }
                                QueryWrapper<EduBookSectionEntity> count = new QueryWrapper<>();
                                count.lambda()
                                        .eq(EduBookSectionEntity::getBId, bId);
                                int countint = eduBookSectionEntityService.count(count);
                                if (countint > 3) {
                                    countint = countint - 3;
                                } else {
                                    countint = 0;
                                }
                                for (int j = countint + 12; j < secnames.size(); j++) {
                                    String jstr = secnames.get(j);
                                    String secname = ReUtil.get(">(.*?)<", jstr, 1);
                                    if (StrUtil.isNotBlank(secname)) {
                                        QueryWrapper<EduBookSectionEntity> e = new QueryWrapper<>();
                                        e.lambda()
                                                .eq(EduBookSectionEntity::getBId, bId)
                                                .eq(EduBookSectionEntity::getSName, secname);
                                        EduBookSectionEntity entity = eduBookSectionEntityService.getOne(e);
                                        if (ObjectUtil.isNull(entity)) {
                                            entity = new EduBookSectionEntity();
                                            entity.setBId(bId);
                                            entity.setSortNum((long) j);
                                            entity.setSName(secname);
                                            eduBookSectionEntityService.save(entity);
                                            Long sId = entity.getId();
                                            String securl = ReUtil.get("href=\\\"(.*?)\\\"", jstr, 1);
                                            String seccontent = HttpUtil.get(sec + securl);
                                            seccontent = ReUtil.get("<div id=\"content\" name=\"content\">全本小说网 (.*?)</div><center class=\"clear\">", seccontent, 1);
                                            seccontent = ReUtil.delFirst("www.quanben.la，最快更新(.*?) ！<br><br>", seccontent);
                                            EduBookSectionContentEntity eduBookSectionContentEntity = new EduBookSectionContentEntity();
                                            eduBookSectionContentEntity.setSId(sId);
                                            eduBookSectionContentEntity.setCContent(seccontent);
                                            eduBookSectionContentEntityService.save(eduBookSectionContentEntity);
                                            try {
                                                log.info("入库一个章节");
                                                Thread.sleep(5000L);
                                            } catch (InterruptedException ex) {
                                                ex.printStackTrace();
                                            }
                                            stringRedisTemplate.opsForValue().set("job","job",30, TimeUnit.SECONDS);
                                        } else {
                                            if (ObjectUtil.isNull(entity.getSortNum()) || 0 == entity.getSortNum()) {
                                                entity.setSortNum((long) j);
                                                eduBookSectionEntityService.updateById(entity);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            };
            thread.start();
        }

    }


}
