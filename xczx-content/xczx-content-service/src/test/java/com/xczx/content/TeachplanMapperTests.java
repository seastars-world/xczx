package com.xczx.content;

import com.xczx.content.mapper.TeachplanMapper;
import com.xczx.content.model.dto.TeachplanDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TeachplanMapperTests {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Test
    public void test() {
        List<TeachplanDTO> teachplanDTOS = teachplanMapper.selectTreeNodes(117L);
        System.out.println(teachplanDTOS);
    }
}
