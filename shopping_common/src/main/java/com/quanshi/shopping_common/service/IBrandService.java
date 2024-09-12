package com.quanshi.shopping_common.service;


import com.quanshi.shopping_common.entity.Brand;

public interface IBrandService {
    /**
     * 根据id查询品牌信息
     * @param id 品牌id
     * @return 品牌对象
     */
    Brand findById(Long id);
}
