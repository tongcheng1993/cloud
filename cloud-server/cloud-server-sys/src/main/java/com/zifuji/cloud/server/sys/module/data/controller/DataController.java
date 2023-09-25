package com.zifuji.cloud.server.sys.module.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.data.qo.PersonPageQo;
import com.zifuji.cloud.server.sys.module.data.vo.PersonControllerVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.data.service.DataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Slf4j
@Api(tags = "数据展示控制器")
@RestController
@RequestMapping(value = "/data")
@AllArgsConstructor
public class DataController {

    private DataService dataService;

    @ApiOperation(value = "上传人员文件")
    @PostMapping(value = "/uploadPersonListFile")
    public Result<Boolean> uploadPersonListFile(MultipartFile file) {
        log.info(JSONObject.toJSONString("文件参数" + file.getOriginalFilename()));
        Boolean result = dataService.uploadPersonListFile(file);
        log.info(JSONObject.toJSONString(result));
        return Result.setObj(result);
    }
    @ApiOperation(value = "上传电话文件")
    @PostMapping(value = "/uploadPhoneListFile")
    public Result<Boolean> uploadPhoneListFile(MultipartFile file) {

        Boolean result = dataService.uploadPhoneListFile(file);

        return Result.setObj(result);
    }

    public Result<IPage<PersonControllerVo>> queryPagePerson(@RequestBody PersonPageQo personPageQo) {
        return Result.setObj(null);
    }
}
