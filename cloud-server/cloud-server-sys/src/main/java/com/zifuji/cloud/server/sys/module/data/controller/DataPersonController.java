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
import com.zifuji.cloud.server.sys.module.data.service.DataPersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Slf4j
@Api(value = "")
@RestController
@RequestMapping(value = "/data/person")
@AllArgsConstructor
public class DataPersonController {

    private DataPersonService dataPersonService;

    @ApiOperation(value = "上传人员文件")
    @PostMapping(value = "/uploadPersonListFile")
    public Result<Boolean> uploadPersonListFile(MultipartFile file) {
        log.info(JSONObject.toJSONString("文件参数" + file.getOriginalFilename()));
        Boolean result = dataPersonService.uploadPersonListFile(file);
        log.info(JSONObject.toJSONString(result));
        return Result.setObj(result);
    }


    public Result<IPage<PersonControllerVo>> queryPagePerson(@RequestBody PersonPageQo personPageQo) {
        return Result.setObj(null);
    }
}
