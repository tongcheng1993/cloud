package com.zifuji.cloud.server.sys.module.dic.controller;

import java.util.List;

import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.server.sys.module.dic.vo.DicVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.dic.mo.SaveDicItemMo;
import com.zifuji.cloud.server.sys.module.dic.mo.SaveDicMo;
import com.zifuji.cloud.server.sys.module.dic.mo.UpdateDicMo;
import com.zifuji.cloud.server.sys.module.dic.qo.DicItemPageQo;
import com.zifuji.cloud.server.sys.module.dic.qo.DicPageQo;
import com.zifuji.cloud.server.sys.module.dic.service.DicService;
import com.zifuji.cloud.server.sys.module.dic.vo.DicItemVo;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@Slf4j
@Api(value = "数据字典控制器")
@RestController
@RequestMapping(value = "/dic")
@AllArgsConstructor
public class DicController {

    private DicService dicService;

    // 新增一个数据字典类型

    @PostMapping(value = "/saveDic")
    public Result<String> saveDic(@RequestBody @Valid SaveDicMo saveDicMo) {

        String result = dicService.saveDic(saveDicMo);

        return new Result<String>().setObj(result);
    }


    @PostMapping(value = "/updateDic")
    public Result<String> updateDic(@RequestBody @Valid UpdateDicMo updateDicMo) {

        String result = dicService.updateDic(updateDicMo);

        return new Result<String>().setObj(result);
    }


    @GetMapping(value = "/delDic")
    public Result<Boolean> delDic(@RequestParam Long id) {

        Boolean result = dicService.delDic(id);

        return new Result<Boolean>().setObj(result);
    }


    @GetMapping(value = "/getDicInfoById")
    public Result<DicVo> getDicInfoById(@RequestParam Long id) {

        DicVo result = dicService.getDicInfoById(id);

        return new Result<DicVo>().setObj(result);
    }

    @PostMapping(value = "/initDic")
    public Result<List<DicVo>> initDic() {

        List<DicVo> result = dicService.initDic();

        return new Result<List<DicVo>>().setObj(result);
    }

    @PostMapping(value = "/queryPageDic")
    public Result<IPage<DicVo>> queryPageDic(@RequestBody @Valid DicPageQo dicPageQo) {

        IPage<DicVo> result = dicService.queryPageDic(dicPageQo);

        return new Result<IPage<DicVo>>().setObj(result);
    }

    // 新增一个数据字典详细

    @PostMapping(value = "/saveDicItem")
    public Result<String> saveDicItem(@RequestBody @Valid SaveDicItemMo saveDicItemMo) {

        String result = dicService.saveDicItem(saveDicItemMo);

        return new Result<String>().setObj(result);
    }


    @PostMapping(value = "/queryListDicItem")
    public Result<List<DicItemVo>> queryListDicItem(@RequestBody @Valid DicItemPageQo dicItemPageQo) {

        List<DicItemVo> result = dicService.queryListDicItem(dicItemPageQo);

        return new Result<List<DicItemVo>>().setObj(result);
    }


    @PostMapping(value = "/queryListDicItemByDicCode")
    public Result<List<DicItemVo>> queryListDicItemByDicCode(String dicCode) {

        List<DicItemVo> result = dicService.queryListDicItemByDicCode(dicCode);

        return new Result<List<DicItemVo>>().setObj(result);
    }

}
