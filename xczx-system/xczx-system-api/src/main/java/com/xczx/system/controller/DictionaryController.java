package com.xczx.system.controller;

import com.xczx.system.model.po.Dictionary;
import com.xczx.system.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author cyhjava
 */
@Slf4j
@Api(value = "系统管理接口",tags = "系统编辑接口")
@RestController
public class DictionaryController  {

    @Autowired
    private DictionaryService dictionaryService;

    @ApiOperation("系统查询接口")
    @GetMapping("/dictionary/all")
    public List<Dictionary> queryAll() {
        return dictionaryService.queryAll();
    }

    @ApiOperation("单个系统查询接口")
    @GetMapping("/dictionary/code/{code}")
    public Dictionary getByCode(@PathVariable String code) {
        return dictionaryService.getByCode(code);
    }
}
