package com.zifuji.cloud.server.business.module.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.blog.controller.mo.SaveBlogMo;
import com.zifuji.cloud.server.business.module.blog.controller.qo.BlogPageQo;
import com.zifuji.cloud.server.business.module.blog.controller.vo.SaveBlogVo;

public interface BlogService {

    SaveBlogVo saveBlog(SaveBlogMo saveBlogMo);


}
