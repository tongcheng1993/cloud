package com.zifuji.cloud.server.sys.module.useDb.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.db.useDb.entity.SqlRecordEntity;
import com.zifuji.cloud.server.sys.db.useDb.service.SqlRecordEntityService;
import com.zifuji.cloud.server.sys.module.useDb.mapper.UseDbMapper;
import com.zifuji.cloud.server.sys.module.useDb.qo.ExecuteSqlQo;
import com.zifuji.cloud.server.sys.module.useDb.service.UseDbService;
import com.zifuji.cloud.server.sys.module.useDb.vo.ExecuteSqlVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class UseDbServiceImpl implements UseDbService {

    private UseDbMapper useDbMapper;

    private SqlRecordEntityService sqlRecordEntityService;

    @Override
    public Boolean testOpenDb() {
        String name = "";
        String host = "";
        String port = "";
        String userName = "";
        String passWord = "";
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(name);
        dataSource.setUrl(host+port);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWord);
        Configuration configuration = new Configuration();
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        return null;
    }

    @Override
    public ExecuteSqlVo executeSql(ExecuteSqlQo executeSqlQo) {
        ExecuteSqlVo vo = new ExecuteSqlVo();
        String[] strings = executeSqlQo.getSql().split(";");
        vo.setSqlCount(strings.length);
        vo.setType(executeSqlQo.getType());
        if (StrUtil.equals(executeSqlQo.getType(), "insert")) {
            if (ObjectUtil.isNotEmpty(strings)) {
                for (String str : strings) {
                    insertSql(str);
                }
            }
        } else if (StrUtil.equals(executeSqlQo.getType(), "update")) {
            if (strings.length > 1) {
                throw new Exception200("update 语句只能一句");
            }
            if (ObjectUtil.isNotEmpty(strings)) {
                for (String str : strings) {
                    updateSql(str);
                }
            }
        } else if (StrUtil.equals(executeSqlQo.getType(), "delete")) {
            if (strings.length > 1) {
                throw new Exception200("delete 语句只能一句");
            }
            if (ObjectUtil.isNotEmpty(strings)) {
                for (String str : strings) {
                    deleteSql(str);
                }
            }
        } else if (StrUtil.equals(executeSqlQo.getType(), "select")) {
            if (strings.length > 1) {
                throw new Exception200("select 语句只能一句");
            }
            if (ObjectUtil.isNotEmpty(strings)) {
                for (String str : strings) {
                    IPage<Map<String, Object>> page = selectSql(executeSqlQo.getCurrent(), executeSqlQo.getSize(), str);
                    vo.setPage(page);
                }
            }
        } else {

        }
        return vo;
    }

    @Override
    public Boolean insertSql(String sql) {
        Boolean flag = SqlRunner.db().insert(sql);
        SqlRecordEntity sqlRecordEntity = new SqlRecordEntity();
        sqlRecordEntity.setSqlType("insert");
        sqlRecordEntity.setSqlNote(sql);
        sqlRecordEntity.setSqlResult(flag);
        sqlRecordEntityService.save(sqlRecordEntity);
        return flag;
    }

    @Override
    public Boolean updateSql(String sql) {
        // update 语句需要检查 必须要求where
        if (sql.contains("where")) {
            Boolean flag = SqlRunner.db().update(sql);
            SqlRecordEntity sqlRecordEntity = new SqlRecordEntity();
            sqlRecordEntity.setSqlType("update");
            sqlRecordEntity.setSqlNote(sql);
            sqlRecordEntity.setSqlResult(flag);
            sqlRecordEntityService.save(sqlRecordEntity);
            return flag;
        } else {
            throw new Exception200("update 语句需要检查 必须要求where");
        }
    }

    @Override
    public Boolean deleteSql(String sql) {
        // delete 语句需要检查 必须要求where
        if (sql.contains("where")) {
            Boolean flag = SqlRunner.db().delete(sql);
            SqlRecordEntity sqlRecordEntity = new SqlRecordEntity();
            sqlRecordEntity.setSqlType("delete");
            sqlRecordEntity.setSqlNote(sql);
            sqlRecordEntity.setSqlResult(flag);
            sqlRecordEntityService.save(sqlRecordEntity);
            return flag;
        } else {
            throw new Exception200("update 语句需要检查 必须要求where");
        }

    }

    @Override
    public IPage<Map<String, Object>> selectSql(long current, long size, String sql) {
        IPage<Map<String, Object>> page = new Page<Map<String, Object>>(current, size);
        page = SqlRunner.db().selectPage(page, sql);
        return page;
    }
}
