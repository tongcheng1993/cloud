package com.zifuji.cloud.server.sys.module.notice.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.notice.qo.NoticePageQo;
import com.zifuji.cloud.server.sys.module.notice.service.NoticeService;
import com.zifuji.cloud.server.sys.module.notice.vo.NoticeVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(value = "notice")
@RestController
@RequestMapping(value = "/notice")
@AllArgsConstructor
public class NoticeController {


    private NoticeService noticeService;

    @ApiOperation(value = "查询列表")
    @PostMapping(value = "/queryPageNotice")
    public Result<IPage<NoticeVo>> queryPageNotice(@RequestBody @Valid NoticePageQo noticePageQo) {

        IPage<NoticeVo> result = noticeService.queryPageNotice(noticePageQo);

        return Result.setObj(result);
    }


}
