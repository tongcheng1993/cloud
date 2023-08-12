package com.zifuji.cloud.server.business.module.book.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.book.controller.mo.CreateNewBookMo;
import com.zifuji.cloud.server.business.module.book.controller.vo.CreateNewBookVo;
import com.zifuji.cloud.server.business.module.book.controller.vo.QueryPageBookVo;
import com.zifuji.cloud.server.business.module.book.service.BookService;
import com.zifuji.cloud.server.business.module.book.controller.qo.QueryPageBookQo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Api(value = "书籍管理")
@RestController
@RequestMapping(value = "/book")
@AllArgsConstructor
public class BookController {


    private BookService bookService;





    @ApiOperation(value = "")
    @PostMapping(value = "/createNewBook")
    public Result<CreateNewBookVo> createNewBook(@RequestBody @Valid CreateNewBookMo createNewBookMo) {
        return Result.setObj(bookService.createNewBook(createNewBookMo));
    }


    @GetMapping(value = "/getNextBookSectionId")
    public Result<String> getNextBookSectionId(@RequestParam Long id) {
        return Result.setObj(bookService.getNextBookSectionId(id));
    }

    @GetMapping(value = "/getLastBookSectionId")
    public Result<String> getLastBookSectionId(@RequestParam Long id) {
        return Result.setObj(bookService.getLastBookSectionId(id));
    }


    @ApiOperation(value = "")
    @PostMapping(value = "/queryPageBook")
    public Result<IPage<QueryPageBookVo>> queryPageBook(@RequestBody @Valid QueryPageBookQo queryPageBookQo) {
        return Result.setObj(bookService.queryPageBook(queryPageBookQo));
    }


    @GetMapping(value = "/job")
    public Result<Boolean> job(){
        return Result.setObj(bookService.job());
    }

}
