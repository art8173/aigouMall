package com.quanshi.shopping_manager_api.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.Brand;
import com.quanshi.shopping_common.entity.Brand;
import com.quanshi.shopping_common.entity.Role;
import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.IBrandService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @DubboReference
    private IBrandService brandService;

    @GetMapping("/findById")
    public BaseResult findById(Long id){

        Brand brand = brandService.findById(id);

        return BaseResult.success(brand);
    }


    /**
     * 新增品牌
     * @param brand 品牌对象
     * @return
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Brand brand){
        brandService.add(brand);
        return BaseResult.success();
    }


    /**
     * 根据主键id修改品牌
     * @param brand 品牌对象
     * @return
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody Brand brand){
        brandService.update(brand);
        return BaseResult.success();
    }

    /**
     * 根据主键id删除品牌
     * @param id 主键id
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResult delete(Long id){
        brandService.delete(id);
        return BaseResult.success();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页条数
     * @return
     */
    @GetMapping("/search")
    public BaseResult search(
            Brand brand,
            @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size){
        Page<Brand> search = brandService.search(brand,page, size);
        return BaseResult.success(search);
    }
    /**
     * 查询所有品牌
     * @return
     */
    @GetMapping("/all")
    public BaseResult findAll(){
        List<Brand> all = brandService.findAll();
        return BaseResult.success(all);
    }

}
