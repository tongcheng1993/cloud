package com.zifuji.cloud.server.sys.module.data.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.sys.db.data.service.PersonEntityService;
import com.zifuji.cloud.server.sys.module.data.component.ChinaIdCardUtil;
import com.zifuji.cloud.server.sys.module.data.qo.PersonPageQo;
import com.zifuji.cloud.server.sys.module.data.vo.PersonControllerVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zifuji.cloud.server.sys.db.data.entity.PersonEntity;
import com.zifuji.cloud.server.sys.module.data.service.DataPersonService;
import com.zifuji.cloud.server.base.properties.ZfjProperties;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@AllArgsConstructor
public class DataPersonServiceImpl implements DataPersonService {

    private PersonEntityService personEntityService;


    private ZfjProperties zfjProperties;

    @Override
    public Boolean uploadPersonListFile(MultipartFile file) {
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
                String cardNumber = ObjectUtil.isNull(map.get("cardNumber")) ? "" : map.get("cardNumber").toString();
                addPerson(name,cardNumber);
            }
        }
        FileUtil.del(tempDirPath);
        return Boolean.TRUE;
    }

    @Override
    public Boolean addPerson(String name, String cardNumber) {
       if (!ChinaIdCardUtil.validateCard(cardNumber)) {
            log.info("身份证数据错误");
           return false;
        }
        QueryWrapper<PersonEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(PersonEntity::getCardNumber, cardNumber)
                .eq(PersonEntity::getName, name);
        PersonEntity personEntity = personEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(personEntity)) {
            personEntity = new PersonEntity();
            personEntity.setName(name);
            personEntity.setCardNumber(cardNumber);
            personEntity.setBirthday(ChinaIdCardUtil.getBirthday(cardNumber));
            personEntity.setCardType("身份证");
            personEntity.setSex(ChinaIdCardUtil.getSex(cardNumber));
            personEntityService.save(personEntity);
            return Boolean.TRUE;
        }else{
            log.info("数据重复");
            return false;
        }

    }


    @Override
    public IPage<PersonControllerVo> queryPagePerson(PersonPageQo personPageQo) {
        return null;
    }

}
