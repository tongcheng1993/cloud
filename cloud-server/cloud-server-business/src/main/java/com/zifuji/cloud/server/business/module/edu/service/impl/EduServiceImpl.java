package com.zifuji.cloud.server.business.module.edu.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.business.db.edu.service.EduSchoolEntityService;
import com.zifuji.cloud.server.business.module.edu.service.EduService;
import com.zifuji.cloud.server.base.properties.ZfjProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@AllArgsConstructor
public class EduServiceImpl implements EduService {


    private EduSchoolEntityService eduSchoolEntityService;

    private ZfjProperties zfjProperties;

    @Override
    public Boolean uploadFile(MultipartFile file) {
        String tempDirPath = zfjProperties.getTempDirPath();
        String path = tempDirPath + File.separator + file.getOriginalFilename();
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception200(e.getMessage());
        }
        ExcelReader reader = ExcelUtil.getReader(path);
        List<Map<String, Object>> readAll = reader.readAll();
        if (ObjectUtil.isNotEmpty(readAll)) {
            for (Map<String, Object> map : readAll) {
                String name = ObjectUtil.isNull(map.get("name")) ? "" : map.get("name").toString();
                String phone = ObjectUtil.isNull(map.get("phone")) ? "" : map.get("phone").toString();


            }

        }

        FileUtil.del(tempDirPath);
        return Boolean.TRUE;
    }
}
