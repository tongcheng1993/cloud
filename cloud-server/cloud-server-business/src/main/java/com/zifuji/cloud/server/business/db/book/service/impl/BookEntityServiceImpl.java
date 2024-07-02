package com.zifuji.cloud.server.business.db.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.book.entity.BookEntity;
import com.zifuji.cloud.server.business.db.book.mapper.BookEntityMapper;
import com.zifuji.cloud.server.business.db.book.service.BookEntityService;
import org.springframework.stereotype.Service;

@Service
public class BookEntityServiceImpl extends ServiceImpl<BookEntityMapper, BookEntity> implements BookEntityService {
}
