package com.zifuji.cloud.server.business.module.book.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.book.controller.mo.CreateNewBookMo;
import com.zifuji.cloud.server.business.module.book.controller.qo.GetMyUploadBookControllerQo;
import com.zifuji.cloud.server.business.module.book.controller.qo.QueryPageBookQo;
import com.zifuji.cloud.server.business.module.book.controller.vo.CreateNewBookVo;
import com.zifuji.cloud.server.business.module.book.controller.vo.QueryPageBookVo;
import com.zifuji.cloud.server.business.module.book.service.bo.BookBo;
import com.zifuji.cloud.server.business.module.book.service.bo.BookSectionBo;

import java.util.List;

public interface BookService {


    CreateNewBookVo createNewBook(CreateNewBookMo createNewBookMo);

    IPage<QueryPageBookVo> queryPageBook(QueryPageBookQo queryPageBookQo);

    Boolean job();

    IPage<BookBo> getMyUploadBook(GetMyUploadBookControllerQo getMyUploadBookControllerQo);

    BookBo getBookDetail(String id);

    List<BookSectionBo> getBookSectionList(String id);

    BookSectionBo getBookSectionDetail(String id);
}
