package com.xczx.content;

import com.xczx.content.model.dto.TeachplanDTO;
import com.xczx.content.service.TeachplanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TeachplanServiceTests {

    @Autowired
    TeachplanService teachplanService;

    @Test
    public void test() {
        List<TeachplanDTO> teachplanTree = teachplanService.findTeachplanTree(117L);
        System.out.println(teachplanTree);

    }
}
