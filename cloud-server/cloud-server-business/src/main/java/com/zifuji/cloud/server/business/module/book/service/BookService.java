package com.zifuji.cloud.server.business.module.book.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.db.book.entity.BookEntity;
import com.zifuji.cloud.server.business.db.book.entity.BookSectionEntity;
import com.zifuji.cloud.server.business.module.book.controller.mo.AddBookMo;
import com.zifuji.cloud.server.business.module.book.controller.mo.AddBookSectionMo;
import com.zifuji.cloud.server.business.module.book.controller.qo.QueryBookQo;
import com.zifuji.cloud.server.business.module.book.controller.qo.QueryBookSectionQo;
import com.zifuji.cloud.server.business.module.book.controller.vo.BookSectionVo;
import com.zifuji.cloud.server.business.module.book.controller.vo.BookVo;

import java.util.List;

public interface BookService {

	BookVo addBook(AddBookMo addBookMo);

	BookVo resetBook();

	Boolean delBook();

	BookSectionVo addBookSection(AddBookSectionMo addBookSectionMo);

	BookVo queryOneBookById(QueryBookQo queryBookQo);

	IPage<BookVo> queryPageBook(QueryBookQo queryBookQo);

	List<BookVo> queryListBook(QueryBookQo queryBookQo);

	BookSectionVo queryOneBookSectionById(QueryBookSectionQo queryBookSectionQo);

	IPage<BookSectionVo> queryPageBookSection(QueryBookSectionQo queryBookSectionQo);

	List<BookSectionVo> queryListBookSection(QueryBookSectionQo queryBookSectionQo);

}
