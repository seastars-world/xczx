package com.xczx.content.controller;

import com.xczx.base.model.PageParams;
import com.xczx.base.model.PageResult;
import com.xczx.content.model.dto.AddCourseDTO;
import com.xczx.content.model.dto.CourseBaseInfoDTO;
import com.xczx.content.model.dto.EditCourseDTO;
import com.xczx.content.model.dto.QueryCourseParamsDTO;
import com.xczx.content.model.po.CourseBase;
import com.xczx.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description 课程信息编辑接口
 * @author cyhjava
 * @date 2022/9/6 11:29
 * @version 1.0
 */
@Api(value = "课程信息管理接口",tags = "课程信息编辑接口")
@RestController
public class CourseBaseInfoController {

    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody QueryCourseParamsDTO queryCourseParams) {

        return courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParams);
    }

    @ApiOperation("新增课程基础信息")
    @PostMapping("/course")
    public CourseBaseInfoDTO createCourseBase(@RequestBody @Validated AddCourseDTO addCourseDTO){
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.createCourseBase(companyId,addCourseDTO);
    }

    @ApiOperation("根据课程id查询课程基础信息")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDTO getCourseBaseById(@PathVariable Long courseId){
        return courseBaseInfoService.getCourseBaseInfo(courseId);
    }

    @ApiOperation("修改课程基础信息")
    @PutMapping("/course")
    public CourseBaseInfoDTO modifyCourseBase(@RequestBody @Validated EditCourseDTO editCourseDTO){
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.updateCourseBase(companyId,editCourseDTO);

    }




}
