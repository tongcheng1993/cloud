package com.zifuji.cloud.server.business.module.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.blog.mo.SaveBlogMo;
import com.zifuji.cloud.server.business.module.blog.qo.BlogPageQo;
import com.zifuji.cloud.server.business.module.blog.vo.BlogVo;

public interface BlogService {

    BlogVo saveBlogMo(SaveBlogMo saveBlogMo);

    BlogVo getBlogById(Long id );

    IPage<BlogVo> queryPageWebBlog(BlogPageQo blogPageQo);

    IPage<BlogVo> queryPageMyBlog(BlogPageQo blogPageQo);

}
