package com.quanshi.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.Goods;
import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.IGoodsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @DubboReference
    private IGoodsService goodsService;

    /**
     * 根据主键id查询商品
     * @param id 主键id
     * @return
     */
    @GetMapping("/findById")
    public BaseResult findById(Long id){
        return BaseResult.success(goodsService.findById(id));
    }

    /**
     * 新增商品
     * @param goods 商品对象
     * @return
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Goods goods){
        System.out.println("goods = " + goods);
        goodsService.add(goods);
        return BaseResult.success();
    }

    /**
     * 根据主键id修改商品
     * @param goods 商品对象
     * @return
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody Goods goods){
        goodsService.update(goods);
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
                            Goods goods,
                            @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size){
        Page<Goods> search = goodsService.search(goods,page, size);
        return BaseResult.success(search);
    }

    /**
     * 商品上架下架
     * @param id 商品id
     * @param isMarketable true：商家，false 下架
     * @return
     */
    @PutMapping("/putAway")
    public BaseResult putAway(Long id,Boolean isMarketable){
        goodsService.putAway(id,isMarketable);
        return BaseResult.success();
    }

}
