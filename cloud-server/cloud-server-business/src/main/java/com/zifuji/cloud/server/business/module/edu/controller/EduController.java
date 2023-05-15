package com.zifuji.cloud.server.business.module.edu.controller;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.edu.service.EduService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Api(value = "edu")
@RestController
@RequestMapping(value = "/edu")
@AllArgsConstructor
public class EduController {


    private EduService eduService;

    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/uploadFile")
    public Result<Boolean> uploadFile(MultipartFile file) {

        Boolean result = true;

        return new Result<Boolean>().setObj(result);
    }







}
