package com.xczx.content;

import com.xczx.base.model.PageParams;
import com.xczx.base.model.PageResult;
import com.xczx.content.model.dto.QueryCourseParamsDTO;
import com.xczx.content.model.po.CourseBase;
import com.xczx.content.service.CourseBaseInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseBaseInfoServiceTests {

    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @Test
    public void test() {
        QueryCourseParamsDTO dto = new QueryCourseParamsDTO();
        dto.setCourseName("java");
        dto.setAuditStatus("202004");

        PageParams pageParams = new PageParams(2L, 2L);

        PageResult<CourseBase> result = courseBaseInfoService.queryCourseBaseList(pageParams, dto);

        System.out.println(result);
    }
}
