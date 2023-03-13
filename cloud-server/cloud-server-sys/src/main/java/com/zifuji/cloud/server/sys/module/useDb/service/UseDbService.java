package com.zifuji.cloud.server.sys.module.useDb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.useDb.mo.ExecuteSqlMo;
import com.zifuji.cloud.server.sys.module.useDb.qo.ExecuteSqlQo;
import com.zifuji.cloud.server.sys.module.useDb.vo.ExecuteSqlVo;

import java.util.Map;

public interface UseDbService {

    Boolean testOpenDb();
    ExecuteSqlVo executeSql(ExecuteSqlQo executeSqlQo);

    Boolean insertSql(String sql);

    Boolean updateSql(String sql);

    Boolean deleteSql(String sql);

    IPage<Map<String,Object>> selectSql(long current,long size,String sql);

}
