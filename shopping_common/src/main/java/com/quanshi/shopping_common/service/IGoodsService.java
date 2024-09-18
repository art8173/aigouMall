package com.quanshi.shopping_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.Goods;

public interface IGoodsService {
    // 新增商品
    void add(Goods goods);
    // 修改商品
    void update(Goods goods);
    // 根据id查询商品详情
    Goods findById(Long id);
    // 上架/下架商品
    void putAway(Long id,Boolean isMarketable);
    // 分页查询
    Page<Goods> search(Goods goods, int page, int size);
}
