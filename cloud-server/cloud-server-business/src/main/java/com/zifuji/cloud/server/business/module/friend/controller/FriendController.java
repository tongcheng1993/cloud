package com.zifuji.cloud.server.business.module.friend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.MyFile;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.friend.mo.AuditFriendApplyMo;
import com.zifuji.cloud.server.business.module.friend.mo.MakeFriendApplyMo;
import com.zifuji.cloud.server.business.module.friend.mo.FriendInfoMo;
import com.zifuji.cloud.server.business.module.friend.qo.FriendPageQo;
import com.zifuji.cloud.server.business.module.friend.service.FriendService;
import com.zifuji.cloud.server.business.module.friend.vo.FriendInfoVo;
import com.zifuji.cloud.server.business.module.friend.vo.FriendRelationVo;
import com.zifuji.cloud.server.business.module.friend.vo.FriendVo;
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
    public Result<IPage<FriendVo>> queryPageFriend(@RequestBody @Valid FriendPageQo friendPageQo) {

        IPage<FriendVo> result = friendService.queryPageFriend(friendPageQo);

        return new Result<IPage<FriendVo>>().setObj(result);
    }


    @ApiOperation(value = "查看他人交友信息")
    @GetMapping(value = "/getFriendInfoById")
    public Result<FriendInfoVo> getFriendInfoById(@RequestParam Long friendId) {

        FriendInfoVo result = friendService.getFriendInfoById(friendId);

        return new Result<FriendInfoVo>().setObj(result);
    }

    @ApiOperation(value = "上传交友照片文件")
    @PostMapping(value = "/uploadFriendInfoImgFile")
    public Result<String> uploadFriendInfoImgFile(MultipartFile file) {

        String result = friendService.uploadFriendInfoImgFile(file);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "下载交友照片文件")
    @PostMapping(value = "/downloadFriendInfoImgFile")
    public Result<MyFile> downloadFriendInfoImgFile(Long id) throws IOException {

        MyFile result = friendService.downloadFriendInfoImgFile(id);

        return new Result<MyFile>().setObj(result);
    }

    @ApiOperation(value = "保存自身交友信息")
    @PostMapping(value = "/saveFriendInfo")
    public Result<String> saveFriendInfo(@RequestBody @Valid FriendInfoMo friendInfoMo) {

        String result = friendService.saveFriendInfo(friendInfoMo);

        return new Result<String>().setObj(result);
    }


    @ApiOperation(value = "获取自身交友信息")
    @GetMapping(value = "/getFriendInfoByMyself")
    public Result<FriendInfoVo> getFriendInfoByMyself() {

        FriendInfoVo result = friendService.getFriendInfoByMyself();

        return new Result<FriendInfoVo>().setObj(result);
    }


    @ApiOperation(value = "发出交朋友的申请 需要消费积分")
    @PostMapping(value = "/makeFriendApply")
    public Result<String> makeFriendApply(@RequestBody @Valid MakeFriendApplyMo makeFriendApplyMo) {

        String result = friendService.makeFriendApply(makeFriendApplyMo);

        return new Result<String>().setObj(result);
    }


    @ApiOperation(value = "同意或者拒绝 朋友申请")
    @PostMapping(value = "/auditFriendApply")
    public Result<String> auditFriendApply(@RequestBody @Valid AuditFriendApplyMo auditFriendApplyMo) {

        String result = friendService.auditFriendApply(auditFriendApplyMo);

        return new Result<String>().setObj(result);
    }


    public Result<List<FriendRelationVo>> getListFriendRelation() {
        return new Result<List<FriendRelationVo>>().setObj(null);
    }


    public Result<Boolean> saveFriendNote() {
        return new Result<Boolean>().setObj(null);
    }

}
