package com.quanshi.shopping_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.Specification;
import com.quanshi.shopping_common.entity.SpecificationOptions;

import java.util.List;

public interface ISpecificationService {
    // 新增商品规格
    void add(Specification specification);
    // 修改商品规格
    void update(Specification specification);
    // 删除商品规格
    void delete(Long[] ids);
    // 根据id查询商品规格
    Specification findById(Long id);
    // 分页查询商品规格
    Page<Specification> search(int page, int size);
    // 查询某种商品类型下的所有规格
    List<Specification> findByProductTypeId(Long id);

    // 新增商品规格项
    void addOption(SpecificationOptions specificationOptions);
    // 删除商品规格项
    void deleteOption(Long[] ids);
}
