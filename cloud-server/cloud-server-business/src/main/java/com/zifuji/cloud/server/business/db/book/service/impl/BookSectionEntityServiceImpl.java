package com.zifuji.cloud.server.business.db.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.book.entity.BookSectionEntity;
import com.zifuji.cloud.server.business.db.book.mapper.BookSectionEntityMapper;
import com.zifuji.cloud.server.business.db.book.service.BookSectionEntityService;
import org.springframework.stereotype.Service;

@Service
public class BookSectionEntityServiceImpl extends ServiceImpl<BookSectionEntityMapper, BookSectionEntity> implements BookSectionEntityService {
}
