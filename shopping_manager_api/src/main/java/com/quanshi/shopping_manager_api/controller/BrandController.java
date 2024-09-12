package com.quanshi.shopping_manager_api.controller;


import com.quanshi.shopping_common.entity.Brand;
import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.IBrandService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/brand")
public class BrandController {

    @DubboReference
    private IBrandService brandService;

    @GetMapping("/findById")
    public BaseResult findById(Long id){

        Brand brand = brandService.findById(id);

        return BaseResult.success(brand);
    }

}
