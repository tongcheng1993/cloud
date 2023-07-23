package com.zifuji.cloud.server.sys.module.file.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import cn.hutool.core.io.IoUtil;
import com.zifuji.cloud.base.bean.FileBo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.file.qo.FilePageQo;
import com.zifuji.cloud.server.sys.module.file.service.FileService;
import com.zifuji.cloud.server.sys.module.file.vo.FileControllerVo;

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
    public Result<String> uploadFile(MultipartFile file) {
        String result = fileService.uploadFile(file);
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "下载文件")
    @GetMapping(value = "/downloadStreamFile")
    public void downloadStreamFile(@RequestParam Long id) throws IOException {
        log.info("" + id);
        FileBo result = fileService.downloadFileStream(id);

        log.info(result.getFileName());

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String mimeType = request.getServletContext().getMimeType(result.getFileName());
        // 清空response
        response.reset();
        // 设置response的Header
        response.setCharacterEncoding("UTF-8");
        // 设置ContentType，响应内容为二进制数据流，编码为utf-8，此处设定的编码是文件内容的编码
        //  + ";charset=utf-8"
        response.setContentType(mimeType);
        // Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
        // attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
        // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(result.getFileName(), "UTF-8"));
        // 告知浏览器文件的大小
        response.addHeader("Content-Length", "" + result.getFileSize());
        try {
            IoUtil.copy(result.getInputStream(), response.getOutputStream());
            response.flushBuffer();
            IoUtil.close(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("结束" + id);
    }


    @ApiOperation(value = "下载文件")
    @PostMapping(value = "/downloadFile")
    public Result<FileControllerVo> downloadFile(@RequestBody Long id) throws IOException {
        FileControllerVo result = fileService.downloadFile(id);
        return new Result<FileControllerVo>().setObj(result);
    }

    @ApiOperation(value = "删除文件")
    @GetMapping(value = "/delFile")
    public Result<Boolean> delFile(Long id) {
        Boolean result = fileService.delFile(id);
        return new Result<Boolean>().setObj(result);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @ApiOperation(value = "查询文件保存记录")
    @PostMapping(value = "/queryPageFile")
    public Result<IPage<FileControllerVo>> queryPageFile(@RequestBody @Valid FilePageQo filePageQo) {
        IPage<FileControllerVo> result = fileService.queryPageFile(filePageQo);
        return new Result<IPage<FileControllerVo>>().setObj(result);
    }

}
