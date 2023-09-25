package com.zifuji.cloud.server.business.module.book.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.base.feign.sys.FileFeignClient;
import com.zifuji.cloud.server.base.properties.ZfjProperties;
import com.zifuji.cloud.server.base.util.SecurityUtil;
import com.zifuji.cloud.server.base.util.ZfjFileUtil;
import com.zifuji.cloud.server.business.db.book.entity.BookEntity;
import com.zifuji.cloud.server.business.db.book.entity.BookSectionContentEntity;
import com.zifuji.cloud.server.business.db.book.entity.BookSectionEntity;
import com.zifuji.cloud.server.business.db.book.service.BookEntityService;
import com.zifuji.cloud.server.business.db.book.service.BookSectionContentEntityService;
import com.zifuji.cloud.server.business.db.book.service.BookSectionEntityService;
import com.zifuji.cloud.server.business.module.book.controller.mo.CreateNewBookMo;
import com.zifuji.cloud.server.business.module.book.controller.qo.GetMyUploadBookControllerQo;
import com.zifuji.cloud.server.business.module.book.controller.qo.QueryPageBookQo;
import com.zifuji.cloud.server.business.module.book.controller.vo.CreateNewBookVo;
import com.zifuji.cloud.server.business.module.book.controller.vo.QueryPageBookVo;
import com.zifuji.cloud.server.business.module.book.mapper.BookMapper;
import com.zifuji.cloud.server.business.module.book.service.BookService;
import com.zifuji.cloud.server.business.module.book.service.bo.BookBo;
import com.zifuji.cloud.server.business.module.book.service.bo.BookSectionBo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private ZfjProperties zfjProperties;

    private FileFeignClient fileFeignClient;

    private BookMapper bookMapper;

    private BookEntityService bookEntityService;

    private BookSectionEntityService bookSectionEntityService;

    private BookSectionContentEntityService bookSectionContentEntityService;

    private StringRedisTemplate stringRedisTemplate;

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public CreateNewBookVo createNewBook(CreateNewBookMo createNewBookMo) {
        QueryWrapper<BookEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BookEntity::getBookName, createNewBookMo.getBookName())
                .eq(BookEntity::getBookAuth, createNewBookMo.getBookAuth())
                .eq(BookEntity::getBookType, createNewBookMo.getBookType());
        BookEntity bookEntity = bookEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(bookEntity)) {
            bookEntity = new BookEntity();
            BeanUtil.copyProperties(createNewBookMo, bookEntity);
            bookEntityService.save(bookEntity);
        }
        CreateNewBookVo vo = new CreateNewBookVo();
        BeanUtil.copyProperties(bookEntity, vo);
        return vo;
    }

    @Override
    public IPage<QueryPageBookVo> queryPageBook(QueryPageBookQo queryPageBookQo) {
        return null;
    }


    @Override
    public Boolean job() {
        String job = stringRedisTemplate.opsForValue().get("job");
        if (StrUtil.isBlank(job)) {
            String pageNum = stringRedisTemplate.opsForValue().get("pageNum");
            if (StrUtil.isBlank(pageNum)) {
                stringRedisTemplate.opsForValue().increment("pageNum");
                pageNum = "1";
            }
            stringRedisTemplate.opsForValue().set("job", "job", 60, TimeUnit.SECONDS);
            String uri = "www.quanben.so";
            String url = "https://" + uri + "/top/postdate/" + pageNum + ".html";
            String html = HttpUtil.get(url);
            String titles = ReUtil.get("<div id=\"main\">(.*?)</div><div class=\"footer clear\">", html, 1);
            List<String> lis = ReUtil.findAll("<li>(.*?)</li>", titles, 1);

            String bookNum = stringRedisTemplate.opsForValue().get("bookNum");
            if (StrUtil.isBlank(bookNum)) {
                stringRedisTemplate.opsForValue().increment("bookNum");
                bookNum = "1";
            }
            int i = Integer.parseInt(bookNum) - 1;
            for (; i < lis.size(); i++) {
                String li = lis.get(i);
                // 类型
                String type = ReUtil.get("<span class=\\\"s1\\\">\\[(.*?)\\]</span>", li, 1);
                // 名字
                String name = ReUtil.get("title=\\\"(.*?)\\\"", li, 1);
                // 作者
                String auth = ReUtil.get("<span class=\\\"s4\\\">(.*?)</span>", li, 1);
                // 最后一章名字
                String lastSec = ReUtil.get("<span class=\\\"s3\\\">(.*?)</span>", li, 1);
                String lastSec2 = ReUtil.get(">(.*?)</a>", lastSec, 1);
                log.info(name);
                QueryWrapper<BookEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda()
                        .eq(BookEntity::getBookName, name)
                        .eq(BookEntity::getBookAuth, auth)
                        .eq(BookEntity::getBookType, type);
                BookEntity bookEntity = bookEntityService.getOne(queryWrapper);
                Long bId = 0L;
                if (ObjectUtil.isNull(bookEntity)) {
                    bookEntity = new BookEntity();
                    bookEntity.setBookName(name);
                    bookEntity.setBookAuth(auth);
                    bookEntity.setBookType(type);
                    bookEntityService.save(bookEntity);
                    bId = bookEntity.getId();
                } else {
                    bId = bookEntity.getId();
                }
                // 获取书详情
                stringRedisTemplate.expire("job", 60, TimeUnit.SECONDS);
                String sec = ReUtil.get("<span class=\\\"s2\\\"><a href=\\\"(.*?)\\\" title=", li, 1);
                String secs = HttpUtil.get(sec);
                // 封面
                if (ObjectUtil.isNull(bookEntity.getBookImg()) || 0 == bookEntity.getBookImg()) {
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
                        bookEntity.setBookImg(Long.valueOf(fileId));
                        bookEntityService.updateById(bookEntity);
                    }
                }
                // 章节名
                List<String> secnames = ReUtil.findAll("<dd>(.*?)</dd>", secs, 1);
                if (!(secnames.size() >= 24)) {
                    continue;
                }
                String secNum = stringRedisTemplate.opsForValue().get("secNum");
                if (StrUtil.isBlank(secNum)) {
                    stringRedisTemplate.opsForValue().increment("secNum");
                    secNum = "1";
                }
                int j = Integer.parseInt(secNum) - 1 + 12;
                while (j < secnames.size()) {
                    log.info("sec" + j);
                    String jstr = secnames.get(j);
                    j++;
                    stringRedisTemplate.opsForValue().increment("secNum");
                    String secname = ReUtil.get(">(.*?)<", jstr, 1);
                    if (StrUtil.isNotBlank(secname)) {
                        log.info(secname);
                        QueryWrapper<BookSectionEntity> e = new QueryWrapper<>();
                        e.lambda()
                                .eq(BookSectionEntity::getBookId, bId)
                                .eq(BookSectionEntity::getSectionName, secname);
                        BookSectionEntity entity = bookSectionEntityService.getOne(e);
                        stringRedisTemplate.expire("job", 60, TimeUnit.SECONDS);
                        if (ObjectUtil.isNull(entity)) {
                            entity = new BookSectionEntity();
                            entity.setBookId(bId);
                            entity.setSortNum(j);
                            entity.setSectionName(secname);
                            bookSectionEntityService.save(entity);
                            String securl = ReUtil.get("href=\\\"(.*?)\\\"", jstr, 1);
                            String seccontent = HttpUtil.get(sec + securl);
                            seccontent = ReUtil.get("<div id=\"content\" name=\"content\">全本小说网 (.*?)</div><center class=\"clear\">", seccontent, 1);
                            seccontent = ReUtil.delFirst(uri + "，最快更新(.*?) ！<br><br>", seccontent);
                            BookSectionContentEntity bookSectionContentEntity = new BookSectionContentEntity();
                            bookSectionContentEntity.setSectionId(entity.getId());
                            bookSectionContentEntity.setSectionContent(seccontent);
                            bookSectionContentEntityService.save(bookSectionContentEntity);
                            log.info("入库一个章节");
                            stringRedisTemplate.delete("job");
                            return true;
                        } else {
                            entity.setSortNum(j);
                            bookSectionEntityService.updateById(entity);
                            QueryWrapper<BookSectionContentEntity> q1 = new QueryWrapper<>();
                            q1.lambda().eq(BookSectionContentEntity::getSectionId, entity.getId());
                            BookSectionContentEntity bookSectionContentEntity = bookSectionContentEntityService.getOne(q1);
                            if (ObjectUtil.isNull(bookSectionContentEntity)) {
                                String securl = ReUtil.get("href=\\\"(.*?)\\\"", jstr, 1);
                                String seccontent = HttpUtil.get(sec + securl);
                                seccontent = ReUtil.get("<div id=\"content\" name=\"content\">全本小说网 (.*?)</div><center class=\"clear\">", seccontent, 1);
                                seccontent = ReUtil.delFirst(uri + "，最快更新(.*?) ！<br><br>", seccontent);
                                bookSectionContentEntity = new BookSectionContentEntity();
                                bookSectionContentEntity.setSectionId(entity.getId());
                                bookSectionContentEntity.setSectionContent(seccontent);
                                bookSectionContentEntityService.save(bookSectionContentEntity);
                            }
                        }
                    }
                }
                stringRedisTemplate.opsForValue().increment("bookNum");
                stringRedisTemplate.delete("secNum");
            }
            // 所有小说都走完了 就增加
            stringRedisTemplate.opsForValue().increment("pageNum");
            stringRedisTemplate.delete("bookNum");
            stringRedisTemplate.delete("job");
        } else {
            log.info("已经存在job直接返回");
        }
        return true;
    }

    @Override
    public IPage<BookBo> getMyUploadBook(GetMyUploadBookControllerQo getMyUploadBookControllerQo) {
        Page<BookEntity> page = new Page<>(getMyUploadBookControllerQo.getCurrent(), getMyUploadBookControllerQo.getSize());
        UserInfo userInfo = SecurityUtil.getUserDetails();
        QueryWrapper<BookEntity> eduBookEntityQueryWrapper = new QueryWrapper<>();
        eduBookEntityQueryWrapper.lambda().eq(BookEntity::getCreateBy, userInfo.getId());
        page = bookEntityService.page(page, eduBookEntityQueryWrapper);
        return page.convert(bookEntity -> {
            BookBo bookBo = new BookBo();
            BeanUtil.copyProperties(bookEntity, bookBo);
            return bookBo;
        });
    }

    @Override
    public BookBo getBookDetail(String id) {
        BookEntity bookEntity = bookEntityService.getById(id);
        if (ObjectUtil.isNull(bookEntity)) {
            throw new Exception20000("");
        }
        BookBo bookBo = new BookBo();
        BeanUtil.copyProperties(bookEntity, bookBo);
        return bookBo;
    }

    @Override
    public List<BookSectionBo> getBookSectionList(String id) {
        List<BookSectionBo> bookSectionBoList = new ArrayList<>();
        QueryWrapper<BookSectionEntity> eduBookSectionEntityQueryWrapper = new QueryWrapper<>();
        eduBookSectionEntityQueryWrapper.lambda().eq(BookSectionEntity::getBookId, id);
        List<BookSectionEntity> bookSectionEntityList = bookSectionEntityService.list(eduBookSectionEntityQueryWrapper);
        if (ObjectUtil.isNotEmpty(bookSectionEntityList)) {
            bookSectionBoList = bookSectionEntityList.stream().map(bookSectionEntity -> {
                BookSectionBo bo = new BookSectionBo();
                BeanUtil.copyProperties(bookSectionEntity, bo);
                return bo;
            }).collect(Collectors.toList());
        }
        return bookSectionBoList;
    }

    @Override
    public BookSectionBo getBookSectionDetail(String id) {
        BookSectionBo bookSectionBo = new BookSectionBo();
        BookSectionEntity entity = bookSectionEntityService.getById(id);
        if (ObjectUtil.isNull(entity)) {
            throw new Exception20000("");
        }
        QueryWrapper<BookSectionContentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BookSectionContentEntity::getSectionId, entity.getId());
        BookSectionContentEntity bookSectionContentEntity = bookSectionContentEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNotNull(bookSectionContentEntity)) {
            bookSectionBo.setSectionContent(bookSectionContentEntity.getSectionContent());
        }
        // nextId
        QueryWrapper<BookSectionEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.lambda()
                .eq(BookSectionEntity::getBookId, entity.getBookId())
                .gt(BookSectionEntity::getSortNum, entity.getSortNum())
                .orderByAsc(BookSectionEntity::getSortNum)
                .last("limit 0,1");
        BookSectionEntity nextEntity = bookSectionEntityService.getOne(queryWrapper1);
        if (ObjectUtil.isNull(nextEntity)) {
            bookSectionBo.setNextId(0L);
        } else {
            bookSectionBo.setNextId(nextEntity.getId());
        }

        // lastId
        QueryWrapper<BookSectionEntity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lambda()
                .eq(BookSectionEntity::getBookId, entity.getBookId())
                .lt(BookSectionEntity::getSortNum, entity.getSortNum())
                .orderByDesc(BookSectionEntity::getSortNum)
                .last("limit 0,1");
        BookSectionEntity lastEntity = bookSectionEntityService.getOne(queryWrapper2);
        if (ObjectUtil.isNull(lastEntity)) {
            bookSectionBo.setLastId(0L);
        } else {
            bookSectionBo.setLastId(lastEntity.getId());
        }
        return bookSectionBo;
    }


}
