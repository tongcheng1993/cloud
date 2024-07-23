package com.zifuji.cloud.server.business.module.book.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.db.book.entity.BookEntity;
import com.zifuji.cloud.server.business.db.book.entity.BookSectionEntity;
import com.zifuji.cloud.server.business.module.book.controller.mo.AddBookMo;
import com.zifuji.cloud.server.business.module.book.controller.mo.AddBookSectionMo;
import com.zifuji.cloud.server.business.module.book.controller.qo.*;
import com.zifuji.cloud.server.business.module.book.controller.vo.*;
import com.zifuji.cloud.server.business.module.book.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "书籍管理")
@RestController
@RequestMapping(value = "/book")
@AllArgsConstructor
public class BookController {

	private BookService bookService;

	@ApiOperation(value = "")
	@PostMapping(value = "/addBook")
	public Result<BookVo> addBook(@RequestBody @Valid AddBookMo addBookMo) {
		return Result.setObj(bookService.addBook(addBookMo));
	}

	public Result<BookVo> resetBook() {
		return Result.setObj(null);
	}

	public Result<Boolean> delBook() {
		return Result.setObj(null);
	}

	@ApiOperation(value = "")
	@PostMapping(value = "/queryOneBookById")
	public Result<BookVo> queryOneBookById(@RequestBody @Valid QueryBookQo queryBookQo) {
		return Result.setObj(bookService.queryOneBookById(queryBookQo));
	}

	@ApiOperation(value = "")
	@PostMapping(value = "/queryPageBook")
	public Result<IPage<BookVo>> queryPageBook(@RequestBody @Valid QueryBookQo queryBookQo) {
		return Result.setObj(bookService.queryPageBook(queryBookQo));
	}

	@ApiOperation(value = "")
	@PostMapping(value = "/queryListBook")
	public Result<List<BookVo>> queryListBook(@RequestBody @Valid QueryBookQo queryBookQo) {
		return Result.setObj(bookService.queryListBook(queryBookQo));
	}

	@ApiOperation(value = "")
	@PostMapping(value = "/addBookSection")
	public Result<BookSectionVo> addBookSection(@RequestBody @Valid AddBookSectionMo addBookSectionMo) {
		return Result.setObj(bookService.addBookSection(addBookSectionMo));
	}

	@ApiOperation(value = "")
	@PostMapping(value = "/queryOneBookSectionById")
	public Result<BookSectionVo> queryOneBookSectionById(@RequestBody @Valid QueryBookSectionQo queryBookSectionQo) {
		return Result.setObj(bookService.queryOneBookSectionById(queryBookSectionQo));
	}

	@ApiOperation(value = "")
	@PostMapping(value = "/queryPageBookSection")
	public Result<IPage<BookSectionVo>> queryPageBookSection(
			@RequestBody @Valid QueryBookSectionQo queryBookSectionQo) {
		return null;
	}

	@ApiOperation(value = "")
	@PostMapping(value = "/queryListBookSection")
	public Result<List<BookSectionVo>> queryListBookSection(@RequestBody @Valid QueryBookSectionQo queryBookSectionQo) {
		return Result.setObj(bookService.queryListBookSection(queryBookSectionQo));
	}

	@ApiOperation(value = "")
	@PostMapping(value = "/queryPageBookSectionContent")
	public Result<IPage<BookSectionContentVo>> queryPageBookSectionContent() {
		return null;
	}

	@ApiOperation(value = "")
	@PostMapping(value = "/queryListBookSectionContent")
	public Result<IPage<BookSectionContentVo>> queryListBookSectionContent() {
		return null;
	}

}
