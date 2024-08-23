package com.xczx.base.model;

import lombok.Data;

/**
 * @description 分页查询通用参数
 * @author cyhjava
 * @date 2022/9/6 14:02
 * @version 1.0
 */
@Data
public class PageParams {

    //当前页码
    private Long pageNo = 1L;

    //每页记录数默认值
    private Long pageSize = 10L;

    public PageParams() {

    }

    public PageParams(long pageNo, long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
