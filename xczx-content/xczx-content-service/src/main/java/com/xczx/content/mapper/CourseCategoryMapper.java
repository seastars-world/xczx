package com.xczx.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xczx.content.model.dto.CourseCategoryTreeDTO;
import com.xczx.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author cyhjava
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {

    List<CourseCategoryTreeDTO> selectTreeNodes(String id);

}
