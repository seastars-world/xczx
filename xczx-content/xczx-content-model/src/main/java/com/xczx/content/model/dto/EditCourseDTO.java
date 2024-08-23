package com.xczx.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description 添加课程dto
 * @author cyhjava
 * @date 2022/9/7 17:40
 * @version 1.0
 */
@Data
@ApiModel(value="EditCourseDto", description="修改课程基本信息")
public class EditCourseDTO extends AddCourseDTO {

 @ApiModelProperty(value = "课程id", required = true)
 private Long id;

}
