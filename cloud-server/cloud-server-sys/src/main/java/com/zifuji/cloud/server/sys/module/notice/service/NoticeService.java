package com.zifuji.cloud.server.sys.module.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.notice.qo.NoticePageQo;
import com.zifuji.cloud.server.sys.module.notice.vo.NoticeVo;

public interface NoticeService {

    IPage<NoticeVo> queryPageNotice(NoticePageQo noticePageQo);
}
