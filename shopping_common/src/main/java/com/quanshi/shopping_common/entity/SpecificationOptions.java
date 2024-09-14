package com.quanshi.shopping_common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 前端新增和修改的时候参数 optionName是数组
 */
@Data
public class SpecificationOptions implements Serializable {

    private static final long serialVersionUID = 1L;

    private String[] optionName;//商品规格项名称集合
    private Long specId;//商品规格id
}
