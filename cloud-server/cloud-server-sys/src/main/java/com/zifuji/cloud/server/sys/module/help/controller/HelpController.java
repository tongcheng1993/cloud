package com.zifuji.cloud.server.sys.module.help.controller;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.help.mo.HelpContentMo;
import com.zifuji.cloud.server.sys.module.help.service.HelpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(value = "help")
@RestController
@RequestMapping(value = "/help")
@AllArgsConstructor
public class HelpController {

    private HelpService helpService;

    @ApiOperation(value = "")
    @PostMapping(value = "/saveHelpContent")
    public Result<String> saveHelpContent(@RequestBody @Valid HelpContentMo helpContentMo){
        log.info(JSONObject.toJSONString(helpContentMo));
        String result = helpService.saveHelpContent(helpContentMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "")
    @GetMapping(value = "/getHelpContent")
    public Result<String> getHelpContent(){
        log.info(JSONObject.toJSONString("空参数"));
        String result = helpService.getHelpContent();
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }


}
