package com.zifuji.cloud.server.business.module.edu.book.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.edu.book.mo.CreateNewBookServiceMo;
import com.zifuji.cloud.server.business.module.edu.book.mo.CreateNewContentServiceMo;
import com.zifuji.cloud.server.business.module.edu.book.mo.CreateNewSectionServiceMo;
import com.zifuji.cloud.server.business.module.edu.book.vo.BookServiceVo;
import com.zifuji.cloud.server.business.module.edu.book.qo.QueryBookServiceQo;
import com.zifuji.cloud.server.business.module.edu.book.vo.BookSectionServiceVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {


    Long createNewBook(CreateNewBookServiceMo createNewBookServiceMo);

    Long createNewSection(CreateNewSectionServiceMo createNewSectionServiceMo);

    Long createNewContent(CreateNewContentServiceMo createNewContentServiceMo);


    IPage<BookServiceVo> queryPageBook(Page page, QueryBookServiceQo queryBookServiceQo);

    List<BookServiceVo> queryListBook(QueryBookServiceQo queryBookServiceQo);


    List<BookServiceVo> getShouYeTuiJian();

    List<BookServiceVo> getZuiXinXiaoShuo();

    BookServiceVo getBookDetail(Long id);

    BookSectionServiceVo getBookSectionDetail(Long id);

    List<BookSectionServiceVo> queryListBookSection(QueryBookServiceQo queryBookServiceQo);

    String getNextBookSectionId(Long id);

    String getLastBookSectionId(Long id);

    void job();
}
