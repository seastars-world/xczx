package com.xczx.content.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xczx.content.service.TeachplanWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyhjava
 */
@Slf4j
@RestController
@RequestMapping("teachplanWork")
public class TeachplanWorkController {

    @Autowired
    private TeachplanWorkService  teachplanWorkService;
}
