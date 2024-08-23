package com.xczx.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xczx.content.mapper.TeachplanMapper;
import com.xczx.content.model.dto.SaveTeachplanDTO;
import com.xczx.content.model.dto.TeachplanDTO;
import com.xczx.content.model.po.Teachplan;
import com.xczx.content.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description 课程计划service接口实现类
 * @author cyhjava
 * @date 2022/9/9 11:14
 * @version 1.0
 */
 @Service
public class TeachplanServiceImpl implements TeachplanService {

   @Autowired
   TeachplanMapper teachplanMapper;
   @Override
   public List<TeachplanDTO> findTeachplanTree(long courseId) {

    List<TeachplanDTO> teachplanDTOS = teachplanMapper.selectTreeNodes(courseId);
    return teachplanDTOS;
    }

 @Transactional
 @Override
 public void saveTeachplan(SaveTeachplanDTO teachplanDto) {

  //课程计划id
  Long id = teachplanDto.getId();
  //修改课程计划
  if(id!=null){
   Teachplan teachplan = teachplanMapper.selectById(id);
   BeanUtils.copyProperties(teachplanDto,teachplan);
   teachplanMapper.updateById(teachplan);
  }else{
   //取出同父同级别的课程计划数量
   int count = getTeachplanCount(teachplanDto.getCourseId(), teachplanDto.getParentid());
   Teachplan teachplanNew = new Teachplan();
   //设置排序号
   teachplanNew.setOrderby(count+1);
   BeanUtils.copyProperties(teachplanDto,teachplanNew);

   teachplanMapper.insert(teachplanNew);

  }

 }
 /**
  * @description 获取最新的排序号
  * @param courseId  课程id
  * @param parentId  父课程计划id
  * @return int 最新排序号
  * @author cyhjava
  * @date 2022/9/9 13:43
  */
 private int getTeachplanCount(long courseId,long parentId){
  LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
  queryWrapper.eq(Teachplan::getCourseId,courseId);
  queryWrapper.eq(Teachplan::getParentid,parentId);
  Integer count = teachplanMapper.selectCount(queryWrapper);
  return count;
 }

}
