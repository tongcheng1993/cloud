package com.zifuji.cloud.server.business.module.blog.controller;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.blog.mo.SaveBlogMo;
import com.zifuji.cloud.server.business.module.blog.service.BlogService;
import com.zifuji.cloud.server.business.module.blog.vo.BlogVo;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "blog")
@RestController
@RequestMapping(value = "/blog")
@AllArgsConstructor
public class BlogController {

    private BlogService blogService;

    @PostMapping(value = "/saveBlogMo")
    public Result<BlogVo> saveBlogMo(SaveBlogMo saveBlogMo) {

        BlogVo result = blogService.saveBlogMo(saveBlogMo);

        return new Result<BlogVo>().setObj(result);
    }



}
