package com.zifuji.cloud.server.business.module.blog.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.business.db.blog.entity.BlogEntity;
import com.zifuji.cloud.server.business.db.blog.service.BlogEntityService;
import com.zifuji.cloud.server.business.module.blog.controller.qo.BlogPageQo;
import com.zifuji.cloud.server.business.module.blog.controller.vo.SaveBlogVo;
import com.zifuji.cloud.server.business.module.blog.controller.mo.SaveBlogMo;
import com.zifuji.cloud.server.business.module.blog.service.BlogService;

import com.zifuji.cloud.server.base.util.SecurityUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

    private BlogEntityService blogEntityService;


    @Override
    public SaveBlogVo saveBlog(SaveBlogMo saveBlogMo) {

        // 新建博客
        if (ObjectUtil.isNull(saveBlogMo.getId())) {
            BlogEntity blogEntity = new BlogEntity();
            blogEntity.setBlogName(saveBlogMo.getBlogName());
            blogEntity.setBlogAuth(SecurityUtil.getUserDetails().getUserName());
            blogEntity.setBlogType(saveBlogMo.getBlogType());
            blogEntity.setBlogContent(saveBlogMo.getBlogContent());
            blogEntity.setBlogText(saveBlogMo.getBlogText());
            blogEntityService.save(blogEntity);
            SaveBlogVo vo = new SaveBlogVo();
            vo.setId(blogEntity.getId());
            vo.setBlogName(blogEntity.getBlogName());
            return vo;
        } else {
            BlogEntity blogEntity = blogEntityService.getById(saveBlogMo.getId());
            if (ObjectUtil.isNull(blogEntity)) {
                throw new Exception20000("数据错误");
            }
            if (StrUtil.equals(blogEntity.getCreateBy() + "", SecurityUtil.getUserDetails().getId() + "")) {
                throw new Exception20000("数据错误");
            }
            blogEntity.setBlogName(saveBlogMo.getBlogName());
            blogEntity.setBlogType(saveBlogMo.getBlogType());
            blogEntity.setBlogContent(saveBlogMo.getBlogContent());
            blogEntity.setBlogText(saveBlogMo.getBlogText());
            blogEntityService.updateById(blogEntity);
            SaveBlogVo vo = new SaveBlogVo();
            vo.setId(blogEntity.getId());
            vo.setBlogName(blogEntity.getBlogName());
            return vo;
        }
    }



}
