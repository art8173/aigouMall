package com.quanshi.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.ProductType;
import com.quanshi.shopping_common.entity.Specification;
import com.quanshi.shopping_common.entity.SpecificationOptions;
import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.ISpecificationService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @DubboReference
    private ISpecificationService specificationService;

    /**
     * 新增商品规格
     * @param specification 商品规格对象
     * @return
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Specification specification){
        specificationService.add(specification);
        return BaseResult.success();
    }


    /**
     * 根据主键id修改商品规格
     * @param specification 商品规格对象
     * @return
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody Specification specification){
        specificationService.update(specification);
        return BaseResult.success();
    }

    /**
     * 根据主键id删除商品规格
     * @param ids 主键id
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResult delete(Long[] ids){
        specificationService.delete(ids);
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
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        Page<Specification> search = specificationService.search(page, size);
        return BaseResult.success(search);
    }

    /**
     * 根据主键id查询
     * @param id 主键
     * @return
     */
    @GetMapping("/findById")
    public BaseResult findById(Long id){
        Specification specification = specificationService.findById(id);
        return BaseResult.success(specification);
    }

    /**
     * 根据商品分类id查询商品规格
     * @param id 商品分类id
     * @return
     */
    @GetMapping("/findByProductTypeId")
    public BaseResult findByProductTypeId(Long id){
        List<Specification> specifications = specificationService.findByProductTypeId(id);
        return BaseResult.success(specifications);
    }

    /**
     * 添加规格项
     * @param specificationOptions 规格项参数对象
     * @return
     */
    @PostMapping("/addOption")
    public BaseResult addOption(@RequestBody SpecificationOptions specificationOptions){
        specificationService.addOption(specificationOptions);
        return BaseResult.success();
    }

    /**
     * 批量删除规格项
     * @param ids 规格项id
     * @return
     */
    @DeleteMapping("/deleteOption")
    public BaseResult deleteOption(Long[] ids){
        specificationService.deleteOption(ids);
        return BaseResult.success();
    }

}
