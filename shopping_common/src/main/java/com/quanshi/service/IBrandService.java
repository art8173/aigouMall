package com.quanshi.service;

import com.quanshi.entity.Brand;

public interface IBrandService {
    /**
     * 根据id查询品牌信息
     * @param id 品牌id
     * @return 品牌对象
     */
    Brand findById(Long id);
}
