package com.xczx.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xczx.base.model.PageParams;
import com.xczx.base.model.PageResult;
import com.xczx.content.model.dto.AddCourseDTO;
import com.xczx.content.model.dto.CourseBaseInfoDTO;
import com.xczx.content.model.dto.EditCourseDTO;
import com.xczx.content.model.dto.QueryCourseParamsDTO;
import com.xczx.content.model.po.CourseBase;

/**
 * @description 课程基本信息管理业务接口
 * @author cyhjava
 * @date 2022/9/6 21:42
 * @version 1.0
 */
public interface CourseBaseInfoService extends IService<CourseBase> {

    /**
     * @description 课程查询接口
     * @param pageParams 分页参数
     * @param queryCourseParams 条件条件
     * @return com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase>
     * @author cyhjava
     * @date 2022/9/6 21:44
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDTO queryCourseParams);

    /**
     * @description 添加课程基本信息
     * @param companyId  教学机构id
     * @param addCourseDto  课程基本信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @author cyhjava
     * @date 2022/9/7 17:51
     */
    CourseBaseInfoDTO createCourseBase(Long companyId, AddCourseDTO addCourseDto);

    /**
     * @param courseId 课程id
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 根据id查询课程基本信息
     * @author cyhjava
     * @date 2022/10/9 8:13
     */
    CourseBaseInfoDTO getCourseBaseInfo(long courseId);

    /**
     * @description 修改课程信息
     * @param companyId  机构id
     * @param dto  课程信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @author cyhjava
     * @date 2022/9/8 21:04
     */
    CourseBaseInfoDTO updateCourseBase(Long companyId, EditCourseDTO dto);
}
