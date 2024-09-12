package com.quanshi.service;

import com.quanshi.entity.Brand;
import com.quanshi.mapper.BrandMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


@DubboService
public class BrandServiceImpl implements IBrandService{

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public Brand findById(Long id) {
        return brandMapper.selectById(id);
    }
}
