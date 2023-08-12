package com.zifuji.cloud.server.business.module.book.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.book.controller.mo.CreateNewBookMo;
import com.zifuji.cloud.server.business.module.book.controller.qo.QueryPageBookQo;
import com.zifuji.cloud.server.business.module.book.controller.vo.CreateNewBookVo;
import com.zifuji.cloud.server.business.module.book.controller.vo.QueryPageBookVo;

public interface BookService {


    CreateNewBookVo createNewBook(CreateNewBookMo createNewBookMo);

    IPage<QueryPageBookVo> queryPageBook(QueryPageBookQo queryPageBookQo);

    String getNextBookSectionId(Long id);

    String getLastBookSectionId(Long id);

    Boolean job();

}
