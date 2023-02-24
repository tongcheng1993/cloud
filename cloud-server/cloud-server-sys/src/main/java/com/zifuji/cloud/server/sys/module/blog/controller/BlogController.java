package com.zifuji.cloud.server.sys.module.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.blog.mo.SaveBlogMo;
import com.zifuji.cloud.server.sys.module.blog.qo.BlogPageQo;
import com.zifuji.cloud.server.sys.module.blog.service.BlogService;
import com.zifuji.cloud.server.sys.module.blog.vo.BlogVo;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Api(value = "blog")
@RestController
@RequestMapping(value = "/blog")
@AllArgsConstructor
public class BlogController {

    private BlogService blogService;

    @PostMapping(value = "/saveBlogMo")
    public Result<BlogVo> saveBlogMo(SaveBlogMo saveBlogMo) {
        log.info(JSONObject.toJSONString(saveBlogMo));
        BlogVo result = blogService.saveBlogMo(saveBlogMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<BlogVo>().setObj(result);
    }

    public Result<IPage<BlogVo>> queryPageBlog(@RequestBody @Valid BlogPageQo blogPageQo) {
        log.info(JSONObject.toJSONString(blogPageQo));
        IPage<BlogVo> result = blogService.queryPageBlog(blogPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<BlogVo>>().setObj(result);
    }

    public Result<IPage<BlogVo>> webQueryPageBlog(@RequestBody @Valid BlogPageQo blogPageQo) {
        log.info(JSONObject.toJSONString(blogPageQo));
        IPage<BlogVo> result = blogService.webQueryPageBlog(blogPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<BlogVo>>().setObj(result);
    }


}
