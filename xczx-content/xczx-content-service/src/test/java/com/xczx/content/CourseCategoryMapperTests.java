package com.xczx.content;

import com.xczx.content.mapper.CourseCategoryMapper;
import com.xczx.content.model.dto.CourseCategoryTreeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CourseCategoryMapperTests {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Test
    public void test() {
        List<CourseCategoryTreeDTO> courseCategoryTreeDTOS = courseCategoryMapper.selectTreeNodes("1");
        System.out.println(courseCategoryTreeDTOS);
    }
}
