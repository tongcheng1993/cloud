package com.zifuji.cloud.server.sys.module.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.blog.mo.SaveBlogMo;
import com.zifuji.cloud.server.sys.module.blog.qo.BlogPageQo;
import com.zifuji.cloud.server.sys.module.blog.service.BlogService;
import com.zifuji.cloud.server.sys.module.blog.vo.BlogVo;
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
    public Result<BlogVo> saveBlog(@RequestBody @Valid SaveBlogMo saveBlogMo) {
        log.info(JSONObject.toJSONString(saveBlogMo));
        BlogVo result = blogService.saveBlogMo(saveBlogMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<BlogVo>().setObj(result);
    }

    @ApiOperation(value = "web端查看博客")
    @GetMapping(value = "/getBlogById")
    public Result<BlogVo> getBlogById(@NotNull(message = "") Long id) {
        log.info(JSONObject.toJSONString(id));
        BlogVo result = blogService.getBlogById(id);
        log.info(JSONObject.toJSONString(result));
        return new Result<BlogVo>().setObj(result);
    }

    @ApiOperation(value = "web端分页查看公开的博客")
    @PostMapping(value = "/queryPageWebBlog")
    public Result<IPage<BlogVo>> queryPageWebBlog(@RequestBody @Valid BlogPageQo blogPageQo) {
        log.info(JSONObject.toJSONString(blogPageQo));
        IPage<BlogVo> result = blogService.queryPageWebBlog(blogPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<BlogVo>>().setObj(result);
    }


    @ApiOperation(value = "web端分页查看自己的博客")
    @PostMapping(value = "/queryPageMyBlog")
    public Result<IPage<BlogVo>> queryPageMyBlog (@RequestBody @Valid BlogPageQo blogPageQo) {
        log.info(JSONObject.toJSONString(blogPageQo));
        IPage<BlogVo> result = blogService.queryPageMyBlog(blogPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<BlogVo>>().setObj(result);
    }

}
