package com.zifuji.cloud.server.business.module.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.blog.controller.qo.BlogPageQo;
import com.zifuji.cloud.server.business.module.blog.controller.vo.SaveBlogVo;
import com.zifuji.cloud.server.business.module.blog.controller.mo.SaveBlogMo;
import com.zifuji.cloud.server.business.module.blog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Api(tags = "博客控制器")
@RestController
@RequestMapping(value = "/blog")
@AllArgsConstructor
public class BlogController {

    private BlogService blogService;


    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "管理端查看博客列表，分页返回")
    @PostMapping(value = "/queryPageBlog")
    @PreAuthorize(value = "hasAnyAuthority('business:blog:queryPageBlog')")
    public Result<IPage<String>> queryPageBlog() {
        return null;
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "管理端查看博客列表，全部返回")
    @PostMapping(value = "/queryListBlog")
    @PreAuthorize(value = "hasAnyAuthority('business:blog:queryListBlog')")
    public Result<List<String>> queryListBlog() {
        return null;
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "管理端查找博客，全部返回")
    @PostMapping(value = "/queryBlog")
    @PreAuthorize(value = "hasAnyAuthority('business:blog:queryBlog')")
    public Result<String> queryBlog() {
        return null;
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "管理端精准查找博客，全部返回")
    @PostMapping(value = "/getBlog")
    @PreAuthorize(value = "hasAnyAuthority('business:blog:getBlog')")
    public Result<String> getBlog() {
        return null;
    }

    public Result<String> insertBlog() {
        return null;
    }

    public Result<String> updateBlog() {
        return null;
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "管理端删除博客")
    @PostMapping(value = "/deleteBlog")
    @PreAuthorize(value = "hasAnyAuthority('business:blog:deleteBlog')")
    public Result<String> deleteBlog() {
        return null;
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "申报端注册用户保存自己的博客信息")
    @PostMapping(value = "/saveBlog")
    @PreAuthorize("hasAnyRole('register')")
    public Result<SaveBlogVo> saveBlog(@RequestBody @Valid SaveBlogMo saveBlogMo) {
        return Result.setObj(blogService.saveBlog(saveBlogMo));
    }


}
