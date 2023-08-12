package com.zifuji.cloud.server.office.module.words.controller;

import com.zifuji.cloud.base.bean.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "words控制器")
@RestController
@RequestMapping(value = "/words")
@AllArgsConstructor
public class WordsController {

    //增加
    @ApiOperation(value = "words 测试")
    @PostMapping(value = "/test")
    public Result<Boolean> test() {
        return Result.setObj(true);
    }

}
