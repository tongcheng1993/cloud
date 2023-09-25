package com.zifuji.cloud.server.business.module.relation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.base.feign.sys.vo.DownloadFileVo;
import com.zifuji.cloud.server.business.module.relation.controller.mo.AuditFriendApplyControllerMo;
import com.zifuji.cloud.server.business.module.relation.controller.mo.MakeFriendApplyControllerMo;
import com.zifuji.cloud.server.business.module.relation.controller.mo.FriendInfoControllerMo;
import com.zifuji.cloud.server.business.module.relation.controller.qo.FriendPageQo;
import com.zifuji.cloud.server.business.module.relation.service.RelationService;
import com.zifuji.cloud.server.business.module.relation.controller.vo.FriendInfoControllerVo;
import com.zifuji.cloud.server.business.module.relation.controller.vo.FriendRelationControllerVo;
import com.zifuji.cloud.server.business.module.relation.controller.vo.FriendControllerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@Api(tags = "关系控制器")
@RestController
@RequestMapping(value = "/relation")
@AllArgsConstructor
public class RelationController {

    private RelationService relationService;

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "刷新token，将新token返回")
    @PostMapping(value = "/makeFriendApply")
    @PreAuthorize(value = "hasAnyAuthority('ROLE_register')")
    public Result<Boolean> makeFriendApply() {
        return Result.setObj(true);
    }

}
