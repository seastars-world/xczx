package com.xczx.content.service.impl;

import com.xczx.content.mapper.CourseCategoryMapper;
import com.xczx.content.model.dto.CourseCategoryTreeDTO;
import com.xczx.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    public List<CourseCategoryTreeDTO> queryTreeNodes(String id) {

        List<CourseCategoryTreeDTO> courseCategoryTreeDTOs = courseCategoryMapper.selectTreeNodes(id);
        //将list转map,以备使用,排除根节点
        Map<String, CourseCategoryTreeDTO> mapTemp = courseCategoryTreeDTOs.stream().filter(item->!id.equals(item.getId())).collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));
        //最终返回的list
        List<CourseCategoryTreeDTO> categoryTreeDTOs = new ArrayList<>();
        //依次遍历每个元素,排除根节点
        courseCategoryTreeDTOs.stream().filter(item->!id.equals(item.getId())).forEach(item->{
            if(item.getParentid().equals(id)){
                categoryTreeDTOs.add(item);
            }
            //找到当前节点的父节点
            CourseCategoryTreeDTO courseCategoryTreeDto = mapTemp.get(item.getParentid());
            if(courseCategoryTreeDto!=null){
                if(courseCategoryTreeDto.getChildrenTreeNodes() ==null){
                    courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<>());
                }
                //下边开始往ChildrenTreeNodes属性中放子节点
                courseCategoryTreeDto.getChildrenTreeNodes().add(item);
            }
        });
        return categoryTreeDTOs;
    }
}
