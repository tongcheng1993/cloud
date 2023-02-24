package com.zifuji.cloud.server.sys.module.notice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.notice.qo.NoticePageQo;
import com.zifuji.cloud.server.sys.module.notice.service.NoticeService;
import com.zifuji.cloud.server.sys.module.notice.vo.NoticeVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    @Override
    public IPage<NoticeVo> queryPageNotice(NoticePageQo noticePageQo) {
        return null;
    }
}
