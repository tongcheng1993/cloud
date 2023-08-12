package com.zifuji.cloud.server.sys.module.dic.controller;

import java.util.List;

import javax.validation.Valid;

import com.zifuji.cloud.server.sys.module.dic.vo.DicControllerVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.dic.mo.SaveDicItemControllerMo;
import com.zifuji.cloud.server.sys.module.dic.mo.SaveDicControllerMo;
import com.zifuji.cloud.server.sys.module.dic.mo.UpdateDicControllerMo;
import com.zifuji.cloud.server.sys.module.dic.qo.DicItemPageQo;
import com.zifuji.cloud.server.sys.module.dic.qo.DicPageQo;
import com.zifuji.cloud.server.sys.module.dic.service.DicService;
import com.zifuji.cloud.server.sys.module.dic.vo.DicItemControllerVo;

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
    public Result<String> saveDic(@RequestBody @Valid SaveDicControllerMo saveDicMo) {

        String result = dicService.saveDic(saveDicMo);

        return Result.setObj(result);
    }


    @PostMapping(value = "/updateDic")
    public Result<String> updateDic(@RequestBody @Valid UpdateDicControllerMo updateDicMo) {

        String result = dicService.updateDic(updateDicMo);

        return Result.setObj(result);
    }


    @GetMapping(value = "/delDic")
    public Result<Boolean> delDic(@RequestParam Long id) {

        Boolean result = dicService.delDic(id);

        return Result.setObj(result);
    }


    @GetMapping(value = "/getDicInfoById")
    public Result<DicControllerVo> getDicInfoById(@RequestParam Long id) {

        DicControllerVo result = dicService.getDicInfoById(id);

        return Result.setObj(result);
    }

    @PostMapping(value = "/initDic")
    public Result<List<DicControllerVo>> initDic() {

        List<DicControllerVo> result = dicService.initDic();

        return Result.setObj(result);
    }

    @PostMapping(value = "/queryPageDic")
    public Result<IPage<DicControllerVo>> queryPageDic(@RequestBody @Valid DicPageQo dicPageQo) {

        IPage<DicControllerVo> result = dicService.queryPageDic(dicPageQo);

        return Result.setObj(result);
    }

    // 新增一个数据字典详细

    @PostMapping(value = "/saveDicItem")
    public Result<String> saveDicItem(@RequestBody @Valid SaveDicItemControllerMo saveDicItemMo) {

        String result = dicService.saveDicItem(saveDicItemMo);

        return Result.setObj(result);
    }


    @PostMapping(value = "/queryListDicItem")
    public Result<List<DicItemControllerVo>> queryListDicItem(@RequestBody @Valid DicItemPageQo dicItemPageQo) {

        List<DicItemControllerVo> result = dicService.queryListDicItem(dicItemPageQo);

        return Result.setObj(result);
    }


    @PostMapping(value = "/queryListDicItemByDicCode")
    public Result<List<DicItemControllerVo>> queryListDicItemByDicCode(String dicCode) {

        List<DicItemControllerVo> result = dicService.queryListDicItemByDicCode(dicCode);

        return Result.setObj(result);
    }

}
