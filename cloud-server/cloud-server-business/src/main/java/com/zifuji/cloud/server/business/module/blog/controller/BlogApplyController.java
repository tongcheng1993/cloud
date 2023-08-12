package com.zifuji.cloud.server.business.module.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.blog.vo.BlogControllerVo;
import com.zifuji.cloud.server.business.module.blog.mo.SaveBlogControllerMo;
import com.zifuji.cloud.server.business.module.blog.qo.BlogPageQo;
import com.zifuji.cloud.server.business.module.blog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Api(value = "blog")
@RestController
@RequestMapping(value = "/blog/apply")
@AllArgsConstructor
public class BlogApplyController {

    private BlogService blogService;

    @ApiOperation(value = "web端保存自己的博客")
    @PostMapping(value = "/saveBlog")
    public Result<BlogControllerVo> saveBlog(@RequestBody @Valid SaveBlogControllerMo saveBlogMo) {

        BlogControllerVo result = blogService.saveBlogMo(saveBlogMo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "web端查看博客")
    @GetMapping(value = "/getBlogById")
    public Result<BlogControllerVo> getBlogById(@NotNull(message = "") Long id) {

        BlogControllerVo result = blogService.getBlogById(id);

        return Result.setObj(result);
    }

    @ApiOperation(value = "web端分页查看公开的博客")
    @PostMapping(value = "/queryPageWebBlog")
    public Result<IPage<BlogControllerVo>> queryPageWebBlog(@RequestBody @Valid BlogPageQo blogPageQo) {

        IPage<BlogControllerVo> result = blogService.queryPageWebBlog(blogPageQo);

        return Result.setObj(result);
    }


    @ApiOperation(value = "web端分页查看自己的博客")
    @PostMapping(value = "/queryPageMyBlog")
    public Result<IPage<BlogControllerVo>> queryPageMyBlog (@RequestBody @Valid BlogPageQo blogPageQo) {

        IPage<BlogControllerVo> result = blogService.queryPageMyBlog(blogPageQo);

        return Result.setObj(result);
    }

}
