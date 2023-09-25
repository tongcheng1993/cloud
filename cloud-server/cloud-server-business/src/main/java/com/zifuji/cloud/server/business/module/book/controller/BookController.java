package com.zifuji.cloud.server.business.module.book.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.book.controller.mo.CreateNewBookMo;
import com.zifuji.cloud.server.business.module.book.controller.qo.*;
import com.zifuji.cloud.server.business.module.book.controller.vo.*;
import com.zifuji.cloud.server.business.module.book.service.BookService;
import com.zifuji.cloud.server.business.module.book.service.bo.BookBo;
import com.zifuji.cloud.server.business.module.book.service.bo.BookSectionBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Api(tags = "书籍管理")
@RestController
@RequestMapping(value = "/book")
@AllArgsConstructor
public class BookController {


    private BookService bookService;


    @ApiOperation(value = "")
    @PostMapping(value = "/getMyUploadBook")
    public Result<IPage<GetMyUploadBookControllerVo>> getMyUploadBook(@RequestBody @Valid GetMyUploadBookControllerQo getMyUploadBookControllerQo) {
        IPage<BookBo> page = bookService.getMyUploadBook(getMyUploadBookControllerQo);
        return Result.setObj(page.convert(bookBo -> {
            GetMyUploadBookControllerVo vo = new GetMyUploadBookControllerVo();
            BeanUtil.copyProperties(bookBo, vo);
            return vo;
        }));
    }

    @ApiOperation(value = "")
    @PostMapping(value = "/createNewBook")
    public Result<CreateNewBookVo> createNewBook(@RequestBody @Valid CreateNewBookMo createNewBookMo) {
        return Result.setObj(bookService.createNewBook(createNewBookMo));
    }

    @ApiOperation(value = "")
    @PostMapping(value = "/getBookDetail")
    public Result<GetBookDetailControllerVo> getBookDetail(@RequestBody @Valid GetBookDetailControllerQo getBookDetailControllerQo) {
        GetBookDetailControllerVo getBookDetailControllerVo = new GetBookDetailControllerVo();
        BookBo bookBo = bookService.getBookDetail(getBookDetailControllerQo.getId());
        BeanUtil.copyProperties(bookBo, getBookDetailControllerVo);
        return Result.setObj(getBookDetailControllerVo);
    }

    @ApiOperation(value = "")
    @PostMapping(value = "/getBookSectionList")
    public Result<List<GetBookSectionListControllerVo>> getBookSectionList(@RequestBody @Valid GetBookSectionListControllerQo getBookSectionListControllerQo) {
        List<GetBookSectionListControllerVo> getBookSectionListControllerVoList = new ArrayList<>();
        List<BookSectionBo> bookSectionBoList = bookService.getBookSectionList(getBookSectionListControllerQo.getId());
        getBookSectionListControllerVoList = bookSectionBoList.stream().map(bookSectionBo -> {
            GetBookSectionListControllerVo vo = new GetBookSectionListControllerVo();
            BeanUtil.copyProperties(bookSectionBo, vo);
            return vo;
        }).collect(Collectors.toList());
        return Result.setObj(getBookSectionListControllerVoList);
    }

    @ApiOperation(value = "")
    @PostMapping(value = "/getBookSectionDetail")
    public Result<GetBookSectionDetailControllerVo> getBookSectionDetail(@RequestBody @Valid GetBookSectionDetailControllerQo getBookSectionDetailControllerQo) {
        GetBookSectionDetailControllerVo getBookSectionDetailControllerVo = new GetBookSectionDetailControllerVo();
        BookSectionBo bookSectionBo = bookService.getBookSectionDetail(getBookSectionDetailControllerQo.getId());
        BeanUtil.copyProperties(bookSectionBo, getBookSectionDetailControllerVo);
        return Result.setObj(getBookSectionDetailControllerVo);
    }


    @ApiOperation(value = "")
    @PostMapping(value = "/queryPageBook")
    public Result<IPage<QueryPageBookVo>> queryPageBook(@RequestBody @Valid QueryPageBookQo queryPageBookQo) {
        return Result.setObj(bookService.queryPageBook(queryPageBookQo));
    }


    @GetMapping(value = "/job")
    public Result<Boolean> job() {
        return Result.setObj(bookService.job());
    }

}
