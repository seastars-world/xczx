package com.xczx.content.service;

import com.xczx.content.model.dto.SaveTeachplanDTO;
import com.xczx.content.model.dto.TeachplanDTO;

import java.util.List;

/**
 * @description 课程基本信息管理业务接口
 * @author cyhjava
 * @date 2022/9/6 21:42
 * @version 1.0
 */
public interface TeachplanService {
    /**
     * @description 查询课程计划树型结构
     * @param courseId  课程id
     * @return List<TeachplanDto>
     * @author cyhjava
     * @date 2022/9/9 11:13
     */
    List<TeachplanDTO> findTeachplanTree(long courseId);

    /**
     * @description 只在课程计划
     * @param teachplanDTO  课程计划信息
     * @return void
     * @author cyhjava
     * @date 2022/9/9 13:39
     */
    void saveTeachplan(SaveTeachplanDTO teachplanDTO);
}
