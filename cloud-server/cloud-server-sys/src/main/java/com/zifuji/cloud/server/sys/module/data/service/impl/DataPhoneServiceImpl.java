package com.zifuji.cloud.server.sys.module.data.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.sys.db.data.entity.PhoneEntity;
import com.zifuji.cloud.server.sys.db.data.service.PhoneEntityService;
import com.zifuji.cloud.server.sys.module.data.service.DataPhoneService;
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
public class DataPhoneServiceImpl implements DataPhoneService {

    private PhoneEntityService phoneEntityService;

    private ZfjProperties zfjProperties;

    @Override
    public Boolean uploadPhoneListFile(MultipartFile file) {
        String tempDirPath = zfjProperties.getTempDirPath();
        String path = tempDirPath + File.separator + file.getOriginalFilename();
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception20000(e.getMessage());
        }
        ExcelReader reader = ExcelUtil.getReader(path);
        List<Map<String, Object>> readAll = reader.readAll();
        if (ObjectUtil.isNotEmpty(readAll)) {
            for (Map<String, Object> map : readAll) {
                String name = ObjectUtil.isNull(map.get("name")) ? "" : map.get("name").toString();
                String phone = ObjectUtil.isNull(map.get("phone")) ? "" : map.get("phone").toString();
                if (StrUtil.isBlank(phone)) {
                    log.info("手机号数据错误");
                    continue;
                }
                QueryWrapper<PhoneEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(PhoneEntity::getPhone, phone);
                PhoneEntity phoneEntity = phoneEntityService.getOne(queryWrapper);
                if (ObjectUtil.isNull(phoneEntity)) {
                    phoneEntity = new PhoneEntity();
                } else {
                    log.info("数据重复");
                    continue;
                }
                phoneEntity.setName(name);
                phoneEntity.setPhone(phone);
                phoneEntityService.save(phoneEntity);
            }

        }

        FileUtil.del(tempDirPath);
        return Boolean.TRUE;
    }
}
