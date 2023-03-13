package com.zifuji.cloud.server.sys.module.friend.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.friend.mo.AuditFriendApplyMo;
import com.zifuji.cloud.server.sys.module.friend.mo.MakeFriendApplyMo;
import com.zifuji.cloud.server.sys.module.friend.mo.FriendInfoMo;
import com.zifuji.cloud.server.sys.module.friend.qo.FriendPageQo;
import com.zifuji.cloud.server.sys.module.friend.service.FriendService;
import com.zifuji.cloud.server.sys.module.friend.vo.FriendInfoVo;
import com.zifuji.cloud.server.sys.module.friend.vo.FriendRelationVo;
import com.zifuji.cloud.server.sys.module.friend.vo.FriendVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(value = "friend")
@RestController
@RequestMapping(value = "/friend")
@AllArgsConstructor
public class FriendController {

    private FriendService friendService;



    @PreAuthorize("hasAnyRole('register')")
    @ApiOperation(value = "分页查询交友列表")
    @PostMapping(value = "/queryPageFriend")
    public Result<IPage<FriendVo>> queryPageFriend(@RequestBody @Valid FriendPageQo friendPageQo) {
        log.info(JSONObject.toJSONString(friendPageQo));
        IPage<FriendVo> result = friendService.queryPageFriend(friendPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<FriendVo>>().setObj(result);
    }

    @PreAuthorize("hasAnyRole('register')")
    @ApiOperation(value = "查看他人交友信息")
    @GetMapping(value = "/getFriendInfoById")
    public Result<FriendInfoVo> getFriendInfoById(@RequestParam Long friendId) {
        log.info(JSONObject.toJSONString(friendId));
        FriendInfoVo result = friendService.getFriendInfoById(friendId);
        log.info(JSONObject.toJSONString(result));
        return new Result<FriendInfoVo>().setObj(result);
    }

    @PreAuthorize("hasAnyRole('register')")
    @ApiOperation(value = "保存自身交友信息")
    @PostMapping(value = "/saveFriendInfo")
    public Result<String> saveFriendInfo(@RequestBody @Valid FriendInfoMo friendInfoMo) {
        log.info(JSONObject.toJSONString(friendInfoMo));
        String result = friendService.saveFriendInfo(friendInfoMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @PreAuthorize("hasAnyRole('register')")
    @ApiOperation(value = "获取自身交友信息")
    @GetMapping(value = "/getFriendInfoByMyself")
    public Result<FriendInfoVo> getFriendInfoByMyself() {
        log.info(JSONObject.toJSONString("空参数"));
        FriendInfoVo result = friendService.getFriendInfoByMyself();
        log.info(JSONObject.toJSONString(result));
        return new Result<FriendInfoVo>().setObj(result);
    }


    @PreAuthorize("hasAnyRole('register')")
    @ApiOperation(value = "发出交朋友的申请 需要消费积分")
    @PostMapping(value = "/makeFriendApply")
    public Result<String> makeFriendApply(@RequestBody @Valid MakeFriendApplyMo makeFriendApplyMo) {
        log.info(JSONObject.toJSONString(makeFriendApplyMo));
        String result = friendService.makeFriendApply(makeFriendApplyMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }


    @PreAuthorize("hasAnyRole('register')")
    @ApiOperation(value = "同意或者拒绝 朋友申请")
    @PostMapping(value = "/auditFriendApply")
    public Result<String> auditFriendApply(@RequestBody @Valid AuditFriendApplyMo auditFriendApplyMo) {
        log.info(JSONObject.toJSONString(auditFriendApplyMo));
        String result = friendService.auditFriendApply(auditFriendApplyMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }


    public Result<List<FriendRelationVo>> getListFriendRelation() {
        return new Result<List<FriendRelationVo>>().setObj(null);
    }



    public Result<Boolean> saveFriendNote(){
        return new Result<Boolean>().setObj(null);
    }

}
