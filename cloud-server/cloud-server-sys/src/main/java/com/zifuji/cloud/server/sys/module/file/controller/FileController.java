package com.zifuji.cloud.server.sys.module.file.controller;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.file.qo.FilePageQo;
import com.zifuji.cloud.server.sys.module.file.service.FileService;
import com.zifuji.cloud.server.sys.module.file.vo.FileVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "file")
@RestController
@RequestMapping(value = "/file")
@AllArgsConstructor
public class FileController {

    private FileService fileService;

    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/uploadFile")
    public Result<String> uploadFile(String uploadPath, MultipartFile file) {
        log.info(JSONObject.toJSONString("文件参数"));
        String result = fileService.uploadFile(uploadPath, file);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "下载文件")
    @PostMapping(value = "/downloadFile")
    public Result<FileVo> downloadFile(@NotBlank(message = "uploadPath") String uploadPath, @NotNull(message = "id") Long id) throws IOException {
        FileVo result = fileService.downloadFile(uploadPath, id);
        return new Result<FileVo>().setObj(result);
    }

    @ApiOperation(value = "删除文件")
    @GetMapping(value = "/delFile")
    public Result<Boolean> delFile(Long id) {
        log.info(JSONObject.toJSONString(id));
        Boolean result = fileService.delFile(id);
        log.info(JSONObject.toJSONString(result));
        return new Result<Boolean>().setObj(result);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @ApiOperation(value = "查询文件保存记录")
    @PostMapping(value = "/queryPageFile")
    public Result<IPage<FileVo>> queryPageFile(@RequestBody @Valid FilePageQo filePageQo) {
        log.info(JSONObject.toJSONString(filePageQo));
        IPage<FileVo> result = fileService.queryPageFile(filePageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<FileVo>>().setObj(result);
    }

}
