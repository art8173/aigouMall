package com.quanshi.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.ProductType;
import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.IProductTypeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productType")
public class ProductTypeController {

    @DubboReference
    private IProductTypeService productTypeService;

    /**
     * 新增商品分类
     * @param productType 商品分类对象
     * @return
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody ProductType productType){
        productTypeService.add(productType);
        return BaseResult.success();
    }


    /**
     * 根据主键id修改商品分类
     * @param productType 商品分类对象
     * @return
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody ProductType productType){
        productTypeService.update(productType);
        return BaseResult.success();
    }

    /**
     * 根据主键id删除商品分类
     * @param id 主键id
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResult delete(Long id){
        productTypeService.delete(id);
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
            ProductType productType,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        Page<ProductType> search = productTypeService.search(productType,page, size);
        return BaseResult.success(search);
    }

    /**
     * 条件查询商品分类
     * @param productType 条件对象
     * @return
     */
    @GetMapping("/findProductType")
    public BaseResult search(ProductType productType){
        List<ProductType> result = productTypeService.findProductType(productType);
        return BaseResult.success(result);
    }
    /**
     * 根据主键id查询
     * @param id 主键
     * @return
     */
    @GetMapping("/findById")
    public BaseResult findById(Long id){

        ProductType productType = productTypeService.findById(id);

        return BaseResult.success(productType);
    }

    /**
     * 根据商品分类的父id查询所有的字分类
     * @param parentId 父id
     * @return
     */
    @GetMapping("/findByParentId")
    public BaseResult findByParentId(Long parentId){
        return BaseResult.success(productTypeService.findByParentId(parentId));
    }
}
