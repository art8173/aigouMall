package com.quanshi.shopping_goods_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quanshi.shopping_common.entity.Goods;

public interface GoodsMapper extends BaseMapper<Goods> {


    Goods findById(Long id);
}
