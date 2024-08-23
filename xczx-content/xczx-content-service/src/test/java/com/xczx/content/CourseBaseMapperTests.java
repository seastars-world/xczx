package com.xczx.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xczx.base.model.PageParams;
import com.xczx.base.model.PageResult;
import com.xczx.content.mapper.CourseBaseMapper;
import com.xczx.content.model.dto.QueryCourseParamsDTO;
import com.xczx.content.model.po.CourseBase;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CourseBaseMapperTests {

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Test
    public void test() {
        CourseBase courseBase = courseBaseMapper.selectById(18);
        Assertions.assertNotNull(courseBase);

        QueryCourseParamsDTO dto = new QueryCourseParamsDTO();
        dto.setCourseName("java");

        LambdaQueryWrapper<CourseBase> wrapper = new LambdaQueryWrapper<CourseBase>()
                .like(StringUtils.isNotEmpty(dto.getCourseName()),CourseBase::getName, dto.getCourseName())
                .eq(StringUtils.isNotEmpty(dto.getAuditStatus()), CourseBase::getAuditStatus, dto.getAuditStatus());

        PageParams pageParams = new PageParams(1, 2);

        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());

        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, wrapper);

        List<CourseBase> list = pageResult.getRecords();

        long total = pageResult.getTotal();

        PageResult<CourseBase> result = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());

        System.out.println(result);
    }
}
