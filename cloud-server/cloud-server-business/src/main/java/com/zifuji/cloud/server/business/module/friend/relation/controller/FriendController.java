package com.zifuji.cloud.server.business.module.friend.relation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.BaseFile;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.friend.relation.mo.AuditFriendApplyControllerMo;
import com.zifuji.cloud.server.business.module.friend.relation.mo.MakeFriendApplyControllerMo;
import com.zifuji.cloud.server.business.module.friend.relation.mo.FriendInfoControllerMo;
import com.zifuji.cloud.server.business.module.friend.relation.qo.FriendPageQo;
import com.zifuji.cloud.server.business.module.friend.relation.service.FriendService;
import com.zifuji.cloud.server.business.module.friend.relation.vo.FriendInfoControllerVo;
import com.zifuji.cloud.server.business.module.friend.relation.vo.FriendRelationControllerVo;
import com.zifuji.cloud.server.business.module.friend.relation.vo.FriendControllerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@Api(value = "friend")
@RestController
@RequestMapping(value = "/friend")
@AllArgsConstructor
public class FriendController {

    private FriendService friendService;


    @ApiOperation(value = "分页查询交友列表")
    @PostMapping(value = "/queryPageFriend")
    public Result<IPage<FriendControllerVo>> queryPageFriend(@RequestBody @Valid FriendPageQo friendPageQo) {

        IPage<FriendControllerVo> result = friendService.queryPageFriend(friendPageQo);

        return new Result<IPage<FriendControllerVo>>().setObj(result);
    }


    @ApiOperation(value = "查看他人交友信息")
    @GetMapping(value = "/getFriendInfoById")
    public Result<FriendInfoControllerVo> getFriendInfoById(@RequestParam Long friendId) {

        FriendInfoControllerVo result = friendService.getFriendInfoById(friendId);

        return new Result<FriendInfoControllerVo>().setObj(result);
    }

    @ApiOperation(value = "上传交友照片文件")
    @PostMapping(value = "/uploadFriendInfoImgFile")
    public Result<String> uploadFriendInfoImgFile(MultipartFile file) {

        String result = friendService.uploadFriendInfoImgFile(file);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "下载交友照片文件")
    @PostMapping(value = "/downloadFriendInfoImgFile")
    public Result<BaseFile> downloadFriendInfoImgFile(Long id) throws IOException {

        BaseFile result = friendService.downloadFriendInfoImgFile(id);

        return new Result<BaseFile>().setObj(result);
    }

    @ApiOperation(value = "保存自身交友信息")
    @PostMapping(value = "/saveFriendInfo")
    public Result<String> saveFriendInfo(@RequestBody @Valid FriendInfoControllerMo friendInfoMo) {

        String result = friendService.saveFriendInfo(friendInfoMo);

        return new Result<String>().setObj(result);
    }


    @ApiOperation(value = "获取自身交友信息")
    @GetMapping(value = "/getFriendInfoByMyself")
    public Result<FriendInfoControllerVo> getFriendInfoByMyself() {

        FriendInfoControllerVo result = friendService.getFriendInfoByMyself();

        return new Result<FriendInfoControllerVo>().setObj(result);
    }


    @ApiOperation(value = "发出交朋友的申请 需要消费积分")
    @PostMapping(value = "/makeFriendApply")
    public Result<String> makeFriendApply(@RequestBody @Valid MakeFriendApplyControllerMo makeFriendApplyMo) {

        String result = friendService.makeFriendApply(makeFriendApplyMo);

        return new Result<String>().setObj(result);
    }


    @ApiOperation(value = "同意或者拒绝 朋友申请")
    @PostMapping(value = "/auditFriendApply")
    public Result<String> auditFriendApply(@RequestBody @Valid AuditFriendApplyControllerMo auditFriendApplyMo) {

        String result = friendService.auditFriendApply(auditFriendApplyMo);

        return new Result<String>().setObj(result);
    }


    public Result<List<FriendRelationControllerVo>> getListFriendRelation() {
        return new Result<List<FriendRelationControllerVo>>().setObj(null);
    }


    public Result<Boolean> saveFriendNote() {
        return new Result<Boolean>().setObj(null);
    }

}
