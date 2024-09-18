package com.quanshi.shopping_goods_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.SeckillGoods;
import com.quanshi.shopping_common.service.ISeckillGoodsService;
import com.quanshi.shopping_goods_service.mapper.SecKillGoodsMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class SecKillGoodsServiceImpl implements ISeckillGoodsService {

    @Autowired
    private SecKillGoodsMapper secKillGoodsMapper;

    @Override
    public void add(SeckillGoods seckillGoods) {
        secKillGoodsMapper.insert(seckillGoods);
    }

    @Override
    public void update(SeckillGoods seckillGoods) {
        secKillGoodsMapper.updateById(seckillGoods);
    }

    @Override
    public Page<SeckillGoods> findPage(int page, int size) {
        return secKillGoodsMapper.selectPage(new Page<>(page,size),null);
    }
}
