package com.zifuji.cloud.server.business.module.blog.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.business.db.friend.entity.FriendBlogEntity;
import com.zifuji.cloud.server.business.db.friend.service.FriendBlogEntityService;
import com.zifuji.cloud.server.business.module.blog.qo.BlogPageQo;
import com.zifuji.cloud.server.business.module.blog.vo.BlogControllerVo;
import com.zifuji.cloud.server.business.module.blog.mo.SaveBlogControllerMo;
import com.zifuji.cloud.server.business.module.blog.service.BlogService;

import com.zifuji.cloud.server.base.util.SecurityUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

    private FriendBlogEntityService friendBlogEntityService;


    @Override
    public BlogControllerVo saveBlogMo(SaveBlogControllerMo saveBlogMo) {
        BlogControllerVo vo = new BlogControllerVo();

        FriendBlogEntity friendBlogEntity = null;
        if(ObjectUtil.isNull(saveBlogMo.getId())){
            friendBlogEntity = new FriendBlogEntity();
            friendBlogEntity.setBlogName(saveBlogMo.getBlogName());
            friendBlogEntity.setBlogAuth(SecurityUtil.getUserDetails().getUserName());
            friendBlogEntity.setBlogType(saveBlogMo.getBlogType());
            friendBlogEntity.setBlogContent(saveBlogMo.getBlogContent());
            friendBlogEntity.setBlogText(saveBlogMo.getBlogText());
            friendBlogEntityService.save(friendBlogEntity);

        }else{
            friendBlogEntity = friendBlogEntityService.getById(saveBlogMo.getId());
            if(ObjectUtil.isNull(friendBlogEntity)){
                throw new Exception20000("数据错误");
            }
            if(friendBlogEntity.getCreateBy() != SecurityUtil.getUserDetails().getId()){
                throw new Exception20000("数据错误");
            }
            friendBlogEntity.setBlogName(saveBlogMo.getBlogName());
            friendBlogEntity.setBlogType(saveBlogMo.getBlogType());
            friendBlogEntity.setBlogContent(saveBlogMo.getBlogContent());
            friendBlogEntity.setBlogText(saveBlogMo.getBlogText());
            friendBlogEntityService.updateById(friendBlogEntity);
        }
        vo.setId(friendBlogEntity.getId());
        vo.setBlogName(friendBlogEntity.getBlogName());
        return vo;
    }

    @Override
    public BlogControllerVo getBlogById(Long id) {
        BlogControllerVo vo = new BlogControllerVo();

        FriendBlogEntity friendBlogEntity = friendBlogEntityService.getById(id);
        if(ObjectUtil.isNull(friendBlogEntity)){
           throw  new Exception20000("数据错误");
        }
        // 获取自己的博客 or 获取其他人公开的博客
        if(SecurityUtil.getUserDetails().getId().equals(friendBlogEntity.getCreateBy())){
            BeanUtil.copyProperties(friendBlogEntity,vo);
        }else if (StrUtil.equals(friendBlogEntity.getBlogType(),"公开")){
            BeanUtil.copyProperties(friendBlogEntity,vo);
        }else{
            throw  new Exception20000("数据错误");
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
