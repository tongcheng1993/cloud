package com.zifuji.cloud.server.business.module.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.blog.mo.SaveBlogControllerMo;
import com.zifuji.cloud.server.business.module.blog.qo.BlogPageQo;
import com.zifuji.cloud.server.business.module.blog.vo.BlogControllerVo;

public interface BlogService {

    BlogControllerVo saveBlogMo(SaveBlogControllerMo saveBlogMo);

    BlogControllerVo getBlogById(Long id );

    IPage<BlogControllerVo> queryPageWebBlog(BlogPageQo blogPageQo);

    IPage<BlogControllerVo> queryPageMyBlog(BlogPageQo blogPageQo);

}
