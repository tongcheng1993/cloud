package com.zifuji.cloud.server.sys.module.help.controller;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.help.mo.HelpContentControllerMo;
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
    public Result<String> saveHelpContent(@RequestBody @Valid HelpContentControllerMo helpContentMo){

        String result = helpService.saveHelpContent(helpContentMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "")
    @GetMapping(value = "/getHelpContent")
    public Result<String> getHelpContent(){

        String result = helpService.getHelpContent();

        return new Result<String>().setObj(result);
    }


}
