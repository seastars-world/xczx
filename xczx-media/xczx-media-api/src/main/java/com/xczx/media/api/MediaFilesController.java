package com.xczx.media.api;

import com.xczx.base.model.PageParams;
import com.xczx.base.model.PageResult;
import com.xczx.media.model.dto.QueryMediaParamsDTO;
import com.xczx.media.model.dto.UploadFileParamsDTO;
import com.xczx.media.model.dto.UploadFileResultDTO;
import com.xczx.media.model.po.MediaFiles;
import com.xczx.media.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Api(value = "媒资文件管理接口",tags = "媒资文件编辑接口")
@RestController
public class MediaFilesController {

    @Autowired
    MediaFileService mediaFileService;

    @ApiOperation("媒资列表查询接口")
    @PostMapping("/files")
    public PageResult<MediaFiles> list(PageParams pageParams, @RequestBody QueryMediaParamsDTO queryMediaParamsDTO){
        Long companyId = 1232141425L;
        return mediaFileService.queryMediaFiels(companyId,pageParams,queryMediaParamsDTO);

    }

    @ApiOperation("上传文件")
    @RequestMapping(value = "/upload/coursefile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadFileResultDTO upload(@RequestPart("filedata") MultipartFile upload) throws IOException {

        Long companyId = 1232141425L;
        UploadFileParamsDTO uploadFileParamsDTO = new UploadFileParamsDTO();
        //文件大小
        uploadFileParamsDTO.setFileSize(upload.getSize());
        //图片
        uploadFileParamsDTO.setFileType("001001");
        //文件名称
        uploadFileParamsDTO.setFilename(upload.getOriginalFilename());//文件名称
        //创建临时文件
        File tempFile = File.createTempFile("minio", "temp");
        //上传的文件拷贝到临时文件
        upload.transferTo(tempFile);
        //文件路径
        String absolutePath = tempFile.getAbsolutePath();
        //上传文件
        return mediaFileService.uploadFile(companyId, uploadFileParamsDTO, absolutePath);

    }

}
