package com.zifuji.cloud.server.sys.module.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.data.service.DataPhoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Api(value = "")
@RestController
@RequestMapping(value = "/data/phone")
@AllArgsConstructor
public class DataPhoneController {

    private DataPhoneService dataPhoneService;

    @ApiOperation(value = "上传电话文件")
    @PostMapping(value = "/uploadPhoneListFile")
    public Result<Boolean> uploadPhoneListFile(MultipartFile file) {
        log.info(JSONObject.toJSONString("文件参数"+file.getOriginalFilename()));
        Boolean result = dataPhoneService.uploadPhoneListFile(file);
        log.info(JSONObject.toJSONString(result));
        return new Result<Boolean>().setObj(result);
    }

}