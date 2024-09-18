package com.quanshi.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.SeckillGoods;
import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.ISeckillGoodsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seckillGoods")
public class SeckillGoodsController {

    @DubboReference
    private ISeckillGoodsService seckillGoodsService;


    /**
     * 新增秒杀商品
     * @param seckillGoods 秒杀商品对象
     * @return
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody SeckillGoods seckillGoods){
        seckillGoodsService.add(seckillGoods);
        return BaseResult.success();
    }

    /**
     * 根据主键id修改秒杀商品
     * @param seckillGoods 秒杀商品对象
     * @return
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody SeckillGoods seckillGoods){
        seckillGoodsService.update(seckillGoods);
        return BaseResult.success();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页条数
     * @return
     */
    @GetMapping("/findPage")
    public BaseResult findPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        Page<SeckillGoods> search = seckillGoodsService.findPage(page, size);
        return BaseResult.success(search);
    }
}
