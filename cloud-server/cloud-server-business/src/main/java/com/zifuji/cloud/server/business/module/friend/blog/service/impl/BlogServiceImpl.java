package com.zifuji.cloud.server.business.module.friend.blog.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.business.db.friend.entity.BlogEntity;
import com.zifuji.cloud.server.business.db.friend.service.BlogEntityService;
import com.zifuji.cloud.server.business.module.friend.blog.mo.SaveBlogControllerMo;
import com.zifuji.cloud.server.business.module.friend.blog.qo.BlogPageQo;
import com.zifuji.cloud.server.business.module.friend.blog.service.BlogService;
import com.zifuji.cloud.server.business.module.friend.blog.vo.BlogControllerVo;

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
    public BlogControllerVo saveBlogMo(SaveBlogControllerMo saveBlogMo) {
        BlogControllerVo vo = new BlogControllerVo();

        BlogEntity blogEntity = null;
        if(ObjectUtil.isNull(saveBlogMo.getId())){
            blogEntity = new BlogEntity();
            blogEntity.setBlogName(saveBlogMo.getBlogName());
            blogEntity.setBlogAuth(SecurityUtil.getUserDetails().getUserName());
            blogEntity.setBlogType(saveBlogMo.getBlogType());
            blogEntity.setBlogContent(saveBlogMo.getBlogContent());
            blogEntity.setBlogText(saveBlogMo.getBlogText());
            blogEntityService.save(blogEntity);

        }else{
            blogEntity = blogEntityService.getById(saveBlogMo.getId());
            if(ObjectUtil.isNull(blogEntity)){
                throw new Exception200("数据错误");
            }
            if(blogEntity.getCreateBy() != SecurityUtil.getUserDetails().getId()){
                throw new Exception200("数据错误");
            }
            blogEntity.setBlogName(saveBlogMo.getBlogName());
            blogEntity.setBlogType(saveBlogMo.getBlogType());
            blogEntity.setBlogContent(saveBlogMo.getBlogContent());
            blogEntity.setBlogText(saveBlogMo.getBlogText());
            blogEntityService.updateById(blogEntity);
        }
        vo.setId(blogEntity.getId());
        vo.setBlogName(blogEntity.getBlogName());
        return vo;
    }

    @Override
    public BlogControllerVo getBlogById(Long id) {
        BlogControllerVo vo = new BlogControllerVo();

        BlogEntity blogEntity = blogEntityService.getById(id);
        if(ObjectUtil.isNull(blogEntity)){
           throw  new Exception200("数据错误");
        }
        // 获取自己的博客 or 获取其他人公开的博客
        if(SecurityUtil.getUserDetails().getId().equals(blogEntity.getCreateBy())){
            BeanUtil.copyProperties(blogEntity,vo);
        }else if (StrUtil.equals(blogEntity.getBlogType(),"公开")){
            BeanUtil.copyProperties(blogEntity,vo);
        }else{
            throw  new Exception200("数据错误");
        }
        return vo;
    }

    @Override

    public IPage<BlogControllerVo> queryPageWebBlog(BlogPageQo blogPageQo) {

        return null;
    }

    @Override

    public IPage<BlogControllerVo> queryPageMyBlog(BlogPageQo blogPageQo) {

        return null;
    }
}