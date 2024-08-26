package com.xczx.content.controller;

import com.xczx.content.model.dto.SaveTeachplanDTO;
import com.xczx.content.model.dto.TeachplanDTO;
import com.xczx.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 课程计划编辑接口
 * @author cyhjava
 * @version 1.0
 */
@Api(value = "课程计划管理接口",tags = "课程计划编辑接口")
@RestController
public class TeachplanController {

     @Autowired
     TeachplanService teachplanService;

     @ApiOperation("查询课程计划树形结构")
     @ApiImplicitParam(value = "courseId",name = "课程Id",required = true,dataType = "Long",paramType = "path")
     @GetMapping("/teachplan/{courseId}/tree-nodes")
     public List<TeachplanDTO> getTreeNodes(@PathVariable Long courseId){
         return teachplanService.findTeachplanTree(courseId);
     }

    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void saveTeachplan( @RequestBody SaveTeachplanDTO teachplan){
        teachplanService.saveTeachplan(teachplan);
    }


}
