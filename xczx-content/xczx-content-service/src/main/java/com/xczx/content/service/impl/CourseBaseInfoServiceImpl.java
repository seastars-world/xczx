package com.xczx.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xczx.base.exception.XCZXException;
import com.xczx.base.model.PageParams;
import com.xczx.base.model.PageResult;
import com.xczx.content.mapper.CourseBaseMapper;
import com.xczx.content.mapper.CourseCategoryMapper;
import com.xczx.content.mapper.CourseMarketMapper;
import com.xczx.content.model.dto.AddCourseDTO;
import com.xczx.content.model.dto.CourseBaseInfoDTO;
import com.xczx.content.model.dto.EditCourseDTO;
import com.xczx.content.model.dto.QueryCourseParamsDTO;
import com.xczx.content.model.po.CourseBase;
import com.xczx.content.model.po.CourseCategory;
import com.xczx.content.model.po.CourseMarket;
import com.xczx.content.service.CourseBaseInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description 课程信息管理业务接口实现类
 * @author cyhjava
 * @date 2022/9/6 21:45
 * @version 1.0
 */
@Service
public class CourseBaseInfoServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase> implements CourseBaseInfoService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Autowired
    CourseMarketMapper courseMarketMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDTO queryCourseParams) {


        //构建查询条件对象
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //构建查询条件，根据课程名称查询
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParams.getCourseName()), CourseBase::getName, queryCourseParams.getCourseName());
        //构建查询条件，根据课程审核状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParams.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParams.getAuditStatus());
        //构建查询条件，根据课程发布状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParams.getPublishStatus()), CourseBase::getStatus, queryCourseParams.getPublishStatus());
        //分页对象
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        // 查询数据内容获得结果
        Page<CourseBase> pageResult = getBaseMapper().selectPage(page, queryWrapper);
        // 获取数据列表
        List<CourseBase> list = pageResult.getRecords();
        // 获取数据总数
        long total = pageResult.getTotal();
        // 构建结果集
        PageResult<CourseBase> result = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
        return result;
    }

    @Transactional
    @Override
    public CourseBaseInfoDTO createCourseBase(Long companyId, AddCourseDTO dto) {

        //合法性校验
        /*if (StringUtils.isBlank(dto.getName())) {
            throw new XCZXException("课程名称为空");
        }

        if (StringUtils.isBlank(dto.getMt())) {
            throw new XCZXException("课程分类为空");
        }

        if (StringUtils.isBlank(dto.getSt())) {
            throw new XCZXException("课程分类为空");
        }

        if (StringUtils.isBlank(dto.getGrade())) {
            throw new XCZXException("课程等级为空");
        }

        if (StringUtils.isBlank(dto.getTeachmode())) {
            throw new XCZXException("教育模式为空");
        }

        if (StringUtils.isBlank(dto.getUsers())) {
            throw new XCZXException("适应人群为空");
        }

        if (StringUtils.isBlank(dto.getCharge())) {
            throw new XCZXException("收费规则为空");
        }*/
        //新增对象
        CourseBase courseBaseNew = new CourseBase();
        //将填写的课程信息赋值给新增对象
        BeanUtils.copyProperties(dto, courseBaseNew);
        //设置审核状态
        courseBaseNew.setAuditStatus("202002");
        //设置发布状态
        courseBaseNew.setStatus("203001");
        //机构id
        courseBaseNew.setCompanyId(companyId);
        //添加时间
        courseBaseNew.setCreateDate(LocalDateTime.now());
        //插入课程基本信息表
        int insert = getBaseMapper().insert(courseBaseNew);
        if (insert <= 0) {
            throw new XCZXException("新增课程基本信息失败");
        }
        CourseMarket courseMarketNew = new CourseMarket();
        Long courseId = courseBaseNew.getId();
        BeanUtils.copyProperties(dto, courseMarketNew);
        courseMarketNew.setId(courseId);
        int i = saveCourseMarket(courseMarketNew);
        if (i <= 0) {
            throw new XCZXException("保存课程营销信息失败");
        }
        //查询课程基本信息及营销信息并返回
        return getCourseBaseInfo(courseId);

    }

    //保存课程营销信息
    private int saveCourseMarket(CourseMarket courseMarketNew) {
        //收费规则
        String charge = courseMarketNew.getCharge();
        if (StringUtils.isBlank(charge)) {
            throw new XCZXException("收费规则没有选择");
        }
        //收费规则为收费
        if (charge.equals("201001")) {
            if (courseMarketNew.getPrice() == null || courseMarketNew.getPrice().floatValue() <= 0) {
                throw new XCZXException("课程为收费价格不能为空且必须大于0");
            }
        }
        //根据id从课程营销表查询
        CourseMarket courseMarketObj = courseMarketMapper.selectById(courseMarketNew.getId());
        if (courseMarketObj == null) {
            return courseMarketMapper.insert(courseMarketNew);
        } else {
            BeanUtils.copyProperties(courseMarketNew, courseMarketObj);
            courseMarketObj.setId(courseMarketNew.getId());
            return courseMarketMapper.updateById(courseMarketObj);
        }
    }
    //根据课程id查询课程基本信息，包括基本信息和营销信息
    public CourseBaseInfoDTO getCourseBaseInfo(long courseId) {

        CourseBase courseBase = getBaseMapper().selectById(courseId);
        if (courseBase == null) {
            return null;
        }
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        CourseBaseInfoDTO courseBaseInfoDTO = new CourseBaseInfoDTO();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDTO);
        if (courseMarket != null) {
            BeanUtils.copyProperties(courseMarket, courseBaseInfoDTO);
        }

        //查询分类名称
        CourseCategory courseCategoryBySt = courseCategoryMapper.selectById(courseBase.getSt());
        courseBaseInfoDTO.setStName(courseCategoryBySt.getName());
        CourseCategory courseCategoryByMt = courseCategoryMapper.selectById(courseBase.getMt());
        courseBaseInfoDTO.setMtName(courseCategoryByMt.getName());

        return courseBaseInfoDTO;
    }

    @Transactional
    @Override
    public CourseBaseInfoDTO updateCourseBase(Long companyId, EditCourseDTO dto) {

        //课程id
        Long courseId = dto.getId();
        CourseBase courseBase = getBaseMapper().selectById(courseId);
        if(courseBase==null){
            XCZXException.cast("课程不存在");
        }

        //校验本机构只能修改本机构的课程
        if(!courseBase.getCompanyId().equals(companyId)){
            XCZXException.cast("本机构只能修改本机构的课程");
        }

        //封装基本信息的数据
        BeanUtils.copyProperties(dto,courseBase);
        courseBase.setChangeDate(LocalDateTime.now());

        //更新课程基本信息
        int i = getBaseMapper().updateById(courseBase);

        //封装营销信息的数据
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(dto,courseMarket);
        saveCourseMarket(courseMarket);
        //查询课程信息
        CourseBaseInfoDTO courseBaseInfo = this.getCourseBaseInfo(courseId);
        return courseBaseInfo;
    }


}