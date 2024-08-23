package com.xczx.content.model.dto;

import com.xczx.content.model.po.Teachplan;
import com.xczx.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @description 课程计划树型结构dto
 * @author cyhjava
 * @date 2022/9/9 10:27
 * @version 1.0
 */
@Data
@ToString
public class TeachplanDTO extends Teachplan {

  //课程计划关联的媒资信息
  TeachplanMedia teachplanMedia;

  //子结点
  List<TeachplanDTO> teachPlanTreeNodes;

}
