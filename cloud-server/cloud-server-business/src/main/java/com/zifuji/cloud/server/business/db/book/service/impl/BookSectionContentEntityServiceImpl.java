package com.zifuji.cloud.server.business.db.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.book.entity.BookSectionContentEntity;
import com.zifuji.cloud.server.business.db.book.mapper.BookSectionContentEntityMapper;
import com.zifuji.cloud.server.business.db.book.service.BookSectionContentEntityService;
import org.springframework.stereotype.Service;

@Service
public class BookSectionContentEntityServiceImpl extends ServiceImpl<BookSectionContentEntityMapper, BookSectionContentEntity> implements BookSectionContentEntityService {
}
