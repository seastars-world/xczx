package com.xczx.media.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xczx.base.model.PageParams;
import com.xczx.base.model.PageResult;
import com.xczx.media.model.dto.QueryMediaParamsDTO;
import com.xczx.media.model.dto.UploadFileParamsDTO;
import com.xczx.media.model.dto.UploadFileResultDTO;
import com.xczx.media.model.po.MediaFiles;

/**
 * @description 媒资文件管理业务类
 * @author cyhjava
 * @date 2022/9/10 8:55
 * @version 1.0
 */
public interface MediaFileService extends IService<MediaFiles> {
    /**
     * @description 媒资文件查询方法
     * @param pageParams 分页参数
     * @param queryMediaParamsDTO 查询条件
     * @return com.xuecheng.base.model.PageResult<com.xuecheng.media.model.po.MediaFiles>
     * @author cyhjava
     * @date 2022/9/10 8:57
     */
    PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParams pageParams, QueryMediaParamsDTO queryMediaParamsDTO);

    /**
     * 上传文件
     * @param companyId 机构id
     * @param uploadFileParamsDTO 上传文件信息
     * @param localFilePath 文件磁盘路径
     * @return 文件信息
     */
    UploadFileResultDTO uploadFile(Long companyId, UploadFileParamsDTO uploadFileParamsDTO, String localFilePath);

    MediaFiles addMediaFilesToDb(Long companyId, String fileMd5, UploadFileParamsDTO uploadFileParamsDTO, String bucket, String objectName);


}
