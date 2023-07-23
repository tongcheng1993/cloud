package com.zifuji.cloud.server.business.module.edu.book.controller;


import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.edu.book.mo.CreateNewBookControllerMo;
import com.zifuji.cloud.server.business.module.edu.book.mo.CreateNewBookServiceMo;
import com.zifuji.cloud.server.business.module.edu.book.qo.QueryBookServiceQo;
import com.zifuji.cloud.server.business.module.edu.book.service.BookService;
import com.zifuji.cloud.server.business.module.edu.book.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Api(value = "书籍管理")
@RestController
@RequestMapping(value = "/book")
@AllArgsConstructor
public class BookController {


    private BookService bookService;

    @ApiOperation(value = "")
    @GetMapping(value = "/getShouYeTuiJian")
    public Result<List<GetShouYeTuiJianControllerVo>> getShouYeTuiJian() {
        List<BookServiceVo> booServiceVoList = bookService.getShouYeTuiJian();
        return new Result<List<GetShouYeTuiJianControllerVo>>().setObj(booServiceVoList.stream().map(booServiceVo -> {
            GetShouYeTuiJianControllerVo getShouYeTuiJianControllerVo = new GetShouYeTuiJianControllerVo();
            getShouYeTuiJianControllerVo.setId(booServiceVo.getId());
            getShouYeTuiJianControllerVo.setName(booServiceVo.getBName());
            getShouYeTuiJianControllerVo.setAuth(booServiceVo.getBAuth());
            getShouYeTuiJianControllerVo.setType(booServiceVo.getBType());
            getShouYeTuiJianControllerVo.setImg(booServiceVo.getBImg());
            getShouYeTuiJianControllerVo.setLastSecId(booServiceVo.getLastSId());
            getShouYeTuiJianControllerVo.setLastSecName(booServiceVo.getLastSName());
            return getShouYeTuiJianControllerVo;
        }).collect(Collectors.toList()));
    }

    @ApiOperation(value = "")
    @GetMapping(value = "/getZuiXinXiaoShuo")
    public Result<List<GetZuiXinXiaoShuoControllerVo>> getZuiXinXiaoShuo() {
        List<BookServiceVo> booServiceVoList = bookService.getZuiXinXiaoShuo();
        return new Result<List<GetZuiXinXiaoShuoControllerVo>>().setObj(booServiceVoList.stream().map(booServiceVo -> {
            GetZuiXinXiaoShuoControllerVo getZuiXinXiaoShuoControllerVo = new GetZuiXinXiaoShuoControllerVo();
            getZuiXinXiaoShuoControllerVo.setId(booServiceVo.getId());
            getZuiXinXiaoShuoControllerVo.setName(booServiceVo.getBName());
            getZuiXinXiaoShuoControllerVo.setAuth(booServiceVo.getBAuth());
            getZuiXinXiaoShuoControllerVo.setType(booServiceVo.getBType());
            return getZuiXinXiaoShuoControllerVo;
        }).collect(Collectors.toList()));
    }

    @PostMapping(value = "/getBookDetail")
    public Result<GetBookDetailControllerVo> getBookDetail(@RequestBody Long id) {
        GetBookDetailControllerVo getBookDetailControllerVo = new GetBookDetailControllerVo();
        BookServiceVo bookServiceVo = bookService.getBookDetail(id);
        getBookDetailControllerVo.setId(bookServiceVo.getId());
        getBookDetailControllerVo.setName(bookServiceVo.getBName());
        getBookDetailControllerVo.setAuth(bookServiceVo.getBAuth());
        getBookDetailControllerVo.setType(bookServiceVo.getBType());
        getBookDetailControllerVo.setImg(bookServiceVo.getBImg());
        return new Result<GetBookDetailControllerVo>().setObj(getBookDetailControllerVo);
    }

    @PostMapping(value = "/getBookSectionList")
    public Result<List<GetBookSectionListControllerVo>> getBookSectionList(@RequestBody Long id) {
        QueryBookServiceQo queryBookServiceQo = new QueryBookServiceQo();
        queryBookServiceQo.setBookId(id);
        List<BookSectionServiceVo> bookSectionServiceVoList = bookService.queryListBookSection(queryBookServiceQo);
        return new Result<List<GetBookSectionListControllerVo>>().setObj(bookSectionServiceVoList.stream().map(bookSectionServiceVo -> {
            GetBookSectionListControllerVo getBookSectionListControllerVo = new GetBookSectionListControllerVo();
            getBookSectionListControllerVo.setSecId(bookSectionServiceVo.getSecId());
            getBookSectionListControllerVo.setSecName(bookSectionServiceVo.getSecName());
            return getBookSectionListControllerVo;
        }).collect(Collectors.toList()));
    }

    @PostMapping(value = "/getBookSectionDetail")
    public Result<GetBookSectionDetailControllerVo> getBookSectionDetail(@RequestBody Long id) {
        GetBookSectionDetailControllerVo getBookSectionDetailControllerVo = new GetBookSectionDetailControllerVo();
        BookSectionServiceVo bookSectionServiceVo = bookService.getBookSectionDetail(id);
        getBookSectionDetailControllerVo.setSecName(bookSectionServiceVo.getSecName());
        getBookSectionDetailControllerVo.setSecContent(bookSectionServiceVo.getSecContent());
        return new Result<GetBookSectionDetailControllerVo>().setObj(getBookSectionDetailControllerVo);
    }

    @GetMapping(value = "/getNextBookSectionId")
    public Result<String> getNextBookSectionId(@RequestParam Long id) {
        return new Result<String>().setObj(bookService.getNextBookSectionId(id));
    }

    @GetMapping(value = "/getLastBookSectionId")
    public Result<String> getLastBookSectionId(@RequestParam Long id) {
        return new Result<String>().setObj(bookService.getLastBookSectionId(id));
    }

    @ApiOperation(value = "")
    @PostMapping(value = "/createNewBook")
    public Result<Long> createNewBook(@RequestBody @Valid CreateNewBookControllerMo createNewBookControllerMo) {
        CreateNewBookServiceMo serviceMo = new CreateNewBookServiceMo();
        serviceMo.setBName(createNewBookControllerMo.getBName());
        serviceMo.setBAuth(createNewBookControllerMo.getBAuth());
        return new Result<Long>().setObj(bookService.createNewBook(serviceMo));
    }

    @ApiOperation(value = "")
    @PostMapping(value = "/listBook")
    public Result<Boolean> listBook() {
        return new Result<Boolean>().setObj(true);
    }

    @ApiOperation(value = "")
    @GetMapping(value = "/job")
    public Result<Boolean> job() {
        bookService.job();
        return new Result<Boolean>().setObj(true);
    }

}
