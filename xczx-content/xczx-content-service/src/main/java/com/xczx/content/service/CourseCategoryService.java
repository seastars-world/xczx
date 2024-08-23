package com.xczx.content.service;

import com.xczx.content.model.dto.CourseCategoryTreeDTO;

import java.util.List;

public interface CourseCategoryService {
    /**
     * 课程分类树形结构查询
     *
     * @return
     */
    List<CourseCategoryTreeDTO> queryTreeNodes(String id);
}
