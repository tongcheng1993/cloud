package com.zifuji.cloud.server.sys.module.blog.service.impl;

<<<<<<< HEAD
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
=======
import cn.hutool.core.util.ObjectUtil;
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.db.blog.entity.BlogEntity;
import com.zifuji.cloud.server.sys.db.blog.service.BlogEntityService;
import com.zifuji.cloud.server.sys.module.blog.mo.SaveBlogMo;
import com.zifuji.cloud.server.sys.module.blog.qo.BlogPageQo;
import com.zifuji.cloud.server.sys.module.blog.service.BlogService;
import com.zifuji.cloud.server.sys.module.blog.vo.BlogVo;
<<<<<<< HEAD
import com.zifuji.cloud.starter.web.object.SecurityUtil;
=======
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

    private BlogEntityService blogEntityService;


    @Override
    public BlogVo saveBlogMo(SaveBlogMo saveBlogMo) {
        BlogVo vo = new BlogVo();
<<<<<<< HEAD
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
    public BlogVo getBlogById(Long id) {
        BlogVo vo = new BlogVo();

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
=======
        if(ObjectUtil.isNull(saveBlogMo.getId())){
            BlogEntity blogEntity = new BlogEntity();
            blogEntity.setBlogName(saveBlogMo.getBlogName());
            blogEntity.setBlogAuth(saveBlogMo.getBlogAuth());
            blogEntity.setBlogContent(saveBlogMo.getBlogContent());
            blogEntityService.save(blogEntity);
            vo.setId(blogEntity.getId());
            vo.setBlogName(blogEntity.getBlogName());
        }else{
            BlogEntity blogEntity = blogEntityService.getById(saveBlogMo.getId());
            if(ObjectUtil.isNull(blogEntity)){
                throw new Exception200("");
            }
            blogEntity.setBlogName(saveBlogMo.getBlogName());
            blogEntity.setBlogAuth(saveBlogMo.getBlogAuth());
            blogEntity.setBlogContent(saveBlogMo.getBlogContent());
            blogEntityService.updateById(blogEntity);
            vo.setId(blogEntity.getId());
            vo.setBlogName(blogEntity.getBlogName());
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        }
        return vo;
    }

    @Override
<<<<<<< HEAD
    public IPage<BlogVo> queryPageWebBlog(BlogPageQo blogPageQo) {
=======
    public IPage<BlogVo> webQueryPageBlog(BlogPageQo blogPageQo) {
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        return null;
    }

    @Override
<<<<<<< HEAD
    public IPage<BlogVo> queryPageMyBlog(BlogPageQo blogPageQo) {
=======
    public IPage<BlogVo> queryPageBlog(BlogPageQo blogPageQo) {
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        return null;
    }
}
