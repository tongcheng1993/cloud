package com.zifuji.cloud.server.business.module.book.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.bean.Exception20000;
import com.zifuji.cloud.server.business.db.book.entity.BookEntity;
import com.zifuji.cloud.server.business.db.book.entity.BookSectionContentEntity;
import com.zifuji.cloud.server.business.db.book.entity.BookSectionEntity;
import com.zifuji.cloud.server.business.db.book.service.BookEntityService;
import com.zifuji.cloud.server.business.db.book.service.BookSectionContentEntityService;
import com.zifuji.cloud.server.business.db.book.service.BookSectionEntityService;
import com.zifuji.cloud.server.business.module.book.controller.mo.AddBookMo;
import com.zifuji.cloud.server.business.module.book.controller.mo.AddBookSectionMo;
import com.zifuji.cloud.server.business.module.book.controller.qo.QueryBookQo;
import com.zifuji.cloud.server.business.module.book.controller.qo.QueryBookSectionQo;
import com.zifuji.cloud.server.business.module.book.controller.vo.BookSectionVo;
import com.zifuji.cloud.server.business.module.book.controller.vo.BookVo;
import com.zifuji.cloud.server.business.module.book.mapper.BookMapper;
import com.zifuji.cloud.server.business.module.book.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

	private BookMapper bookMapper;

	private BookEntityService bookEntityService;

	private BookSectionEntityService bookSectionEntityService;

	private BookSectionContentEntityService bookSectionContentEntityService;

	@Override
	public BookVo addBook(AddBookMo addBookMo) {
		BookEntity bookEntity = new BookEntity();
		BeanUtil.copyProperties(addBookMo, bookEntity);
		if (bookEntityService.save(bookEntity)) {
			BookVo vo = new BookVo();
			BeanUtil.copyProperties(bookEntity, vo);
			return vo;
		} else {
			throw new Exception20000("");
		}
	}

	@Override
	public BookVo resetBook() {
		return null;
	}

	@Override
	public Boolean delBook() {
		return null;
	}

	@Override
	public BookSectionVo addBookSection(AddBookSectionMo addBookSectionMo) {
		BookSectionEntity bookSectionEntity = new BookSectionEntity();
		BeanUtil.copyProperties(addBookSectionMo, bookSectionEntity);
		bookSectionEntityService.save(bookSectionEntity);
		BookSectionContentEntity bookSectionContentEntity = new BookSectionContentEntity();
		bookSectionContentEntity.setSectionId(bookSectionEntity.getTableId());
		bookSectionContentEntity.setSectionContent(addBookSectionMo.getSectionContent());
		bookSectionContentEntityService.save(bookSectionContentEntity);
		BookSectionVo vo = new BookSectionVo();
		BeanUtil.copyProperties(bookSectionEntity, vo);
		return vo;
	}

	@Override
	public BookVo queryOneBookById(QueryBookQo queryBookQo) {
		if (ObjectUtil.isNull(queryBookQo.getTableIdEq())) {
			throw new Exception20000("");
		}
		QueryWrapper<BookEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BookEntity::getTableId, queryBookQo.getTableIdEq());
		BookEntity bookEntity = bookEntityService.getOne(queryWrapper);
		BookVo vo = new BookVo();
		BeanUtil.copyProperties(bookEntity, vo);
		return vo;
	}

	@Override
	public IPage<BookVo> queryPageBook(QueryBookQo queryBookQo) {
		Page<BookEntity> page = new Page<>(queryBookQo.getCurrent(), queryBookQo.getSize());
		QueryWrapper<BookEntity> queryWrapper = new QueryWrapper<>();
		if (StrUtil.isNotBlank(queryBookQo.getBookName())) {
			queryWrapper.lambda().eq(BookEntity::getBookName, queryBookQo.getBookName());
		}
		if (StrUtil.isNotBlank(queryBookQo.getBookAuth())) {
			queryWrapper.lambda().eq(BookEntity::getBookAuth, queryBookQo.getBookAuth());
		}
		if (StrUtil.isNotBlank(queryBookQo.getBookType())) {
			queryWrapper.lambda().eq(BookEntity::getBookType, queryBookQo.getBookType());
		}
		page = bookEntityService.page(page, queryWrapper);
		return page.convert(bookEntity -> {
			BookVo vo = new BookVo();
			BeanUtil.copyProperties(bookEntity, vo);
			return vo;
		});
	}

	@Override
	public List<BookVo> queryListBook(QueryBookQo queryBookQo) {
		return null;
	}

	@Override
	public BookSectionVo queryOneBookSectionById(QueryBookSectionQo queryBookSectionQo) {
		if (ObjectUtil.isNull(queryBookSectionQo.getTableIdEq())) {
			throw new Exception20000("");
		}
		if (0 == queryBookSectionQo.getTableIdEq()) {
			throw new Exception20000("");
		}
		QueryWrapper<BookSectionEntity> bookSectionEntityQueryWrapper = new QueryWrapper<>();
		bookSectionEntityQueryWrapper.lambda().eq(BookSectionEntity::getTableId, queryBookSectionQo.getTableIdEq());
		BookSectionEntity bookSectionEntity = bookSectionEntityService.getOne(bookSectionEntityQueryWrapper);

		QueryWrapper<BookSectionContentEntity> bookSectionContentEntityQueryWrapper = new QueryWrapper<>();
		bookSectionContentEntityQueryWrapper.lambda().eq(BookSectionContentEntity::getSectionId,
				bookSectionEntity.getTableId());
		BookSectionContentEntity bookSectionContentEntity = bookSectionContentEntityService
				.getOne(bookSectionContentEntityQueryWrapper);

		QueryWrapper<BookSectionEntity> lastBookSectionEntityQueryWrapper = new QueryWrapper<>();
		lastBookSectionEntityQueryWrapper.lambda().eq(BookSectionEntity::getBookId, bookSectionEntity.getBookId())
				.eq(BookSectionEntity::getSectionNo, (bookSectionEntity.getSectionNo() - 1));
		BookSectionEntity lastBookSectionEntity = bookSectionEntityService.getOne(lastBookSectionEntityQueryWrapper);

		QueryWrapper<BookSectionEntity> nextBookSectionEntityQueryWrapper = new QueryWrapper<>();
		nextBookSectionEntityQueryWrapper.lambda().eq(BookSectionEntity::getBookId, bookSectionEntity.getBookId())
				.eq(BookSectionEntity::getSectionNo, (bookSectionEntity.getSectionNo() + 1));
		BookSectionEntity nextBookSectionEntity = bookSectionEntityService.getOne(nextBookSectionEntityQueryWrapper);

		BookSectionVo vo = new BookSectionVo();
		BeanUtil.copyProperties(bookSectionEntity, vo);
		vo.setSectionContent(bookSectionContentEntity.getSectionContent());
		if (ObjectUtil.isNotNull(lastBookSectionEntity)) {
			vo.setLastId(lastBookSectionEntity.getTableId());
		} else {
			vo.setLastId(0L);
		}
		if (ObjectUtil.isNotNull(nextBookSectionEntity)) {
			vo.setNextId(nextBookSectionEntity.getTableId());
		} else {
			vo.setNextId(0L);
		}

		return vo;
	}

	@Override
	public IPage<BookSectionVo> queryPageBookSection(QueryBookSectionQo queryBookSectionQo) {
		Page<BookSectionEntity> page = new Page<>(queryBookSectionQo.getCurrent(), queryBookSectionQo.getSize());
		QueryWrapper<BookSectionEntity> queryWrapper = new QueryWrapper<>();
		if (ObjectUtil.isNotNull(queryBookSectionQo.getBookId())) {
			queryWrapper.lambda().eq(BookSectionEntity::getBookId, queryBookSectionQo.getBookId());
		}
		if (StrUtil.isNotBlank(queryBookSectionQo.getSectionName())) {
			queryWrapper.lambda().eq(BookSectionEntity::getSectionName, queryBookSectionQo.getSectionName());
		}
		page = bookSectionEntityService.page(page, queryWrapper);
		return page.convert(bookSectionEntity -> {
			BookSectionVo vo = new BookSectionVo();
			BeanUtil.copyProperties(bookSectionEntity, vo);
			return vo;
		});
	}

	@Override
	public List<BookSectionVo> queryListBookSection(QueryBookSectionQo queryBookSectionQo) {
		QueryWrapper<BookSectionEntity> queryWrapper = new QueryWrapper<>();
		if (ObjectUtil.isNotNull(queryBookSectionQo.getBookId())) {
			queryWrapper.lambda().eq(BookSectionEntity::getBookId, queryBookSectionQo.getBookId());
		}
		if (StrUtil.isNotBlank(queryBookSectionQo.getSectionName())) {
			queryWrapper.lambda().eq(BookSectionEntity::getSectionName, queryBookSectionQo.getSectionName());
		}
		List<BookSectionEntity> list = bookSectionEntityService.list(queryWrapper);
		return list.stream().map(bookSectionEntity -> {
			BookSectionVo vo = new BookSectionVo();
			BeanUtil.copyProperties(bookSectionEntity, vo);
			return vo;
		}).collect(Collectors.toList());
	}

}
