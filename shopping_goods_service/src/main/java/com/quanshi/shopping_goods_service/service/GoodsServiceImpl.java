package com.quanshi.shopping_goods_service.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.*;
import com.quanshi.shopping_common.service.IGoodsService;
import com.quanshi.shopping_goods_service.mapper.GoodsImageMapper;
import com.quanshi.shopping_goods_service.mapper.GoodsMapper;
import com.quanshi.shopping_goods_service.mapper.GoodsSpecifiactionOptionMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@DubboService
@Transactional
public class GoodsServiceImpl implements IGoodsService {


    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Autowired
    private GoodsSpecifiactionOptionMapper goodsSpecifiactionOptionMapper;

    @Override

    public void add(Goods goods) {

        /**
         * 1. 商品基本信息 t_goods
         * 2. 商品的图片  t_goods_image
         * 3. 商品规格项 t_goods_specification_option
         */
        //1. 商品基本信息 t_goods
        goodsMapper.insert(goods);//数据库自动生成id，保存商品以后，会将商品id放回并封装到商品对

        //获取商品id
        Long goodsId = goods.getId();

        //2. 商品的图片  t_goods_image
        List<GoodsImage> images = goods.getImages();
        for (GoodsImage image : images) {
            //设置图片的商品id
            image.setGoodsId(goodsId);
            goodsImageMapper.insert(image);
        }

        //3. 商品规格项 t_goods_specification_option
        //3.1获取商品规格
        List<Specification> specifications = goods.getSpecifications();

        //循环商品规格
        for (Specification specification : specifications) {
            //获取商品规格项
            List<SpecificationOption> options = specification.getSpecificationOptions();
            if (CollUtil.isNotEmpty(options)){
                for (SpecificationOption option : options) {
                    //创建商品规格选项中间表的实体对象t_goods_specification_option
                    GoodsSpecificationOption goodsSpecificationOption = new GoodsSpecificationOption();
                    goodsSpecificationOption.setGid(goodsId);
                    goodsSpecificationOption.setOptionId(option.getId());

                    //插入t_goods_specification_option 数据
                    goodsSpecifiactionOptionMapper.insert(goodsSpecificationOption);
                }
            }

        }
    }

    @Override
    public void update(Goods goods) {
        /**
         * 1. 商品基本信息 t_goods
         * 2. 商品的图片  t_goods_image
         * 3. 商品规格项 t_goods_specification_option
         */
        //1. 商品基本信息 t_goods
        goodsMapper.updateById(goods);//数据库自动生成id，保存商品以后，会将商品id放回并封装到商品对

        //获取商品id
        Long goodsId = goods.getId();


        //2. 商品的图片  t_goods_image
        //2.0 删除之前的图片数据
        LambdaUpdateWrapper<GoodsImage> goodsImageLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        goodsImageLambdaUpdateWrapper.eq(GoodsImage::getGoodsId, goodsId);
        goodsImageMapper.delete(goodsImageLambdaUpdateWrapper);


        //2.1新增图片
        List<GoodsImage> images = goods.getImages();
        for (GoodsImage image : images) {
            //设置图片的商品id
            image.setGoodsId(goodsId);
            goodsImageMapper.insert(image);
        }

        //3. 商品规格项 t_goods_specification_option
        //3.0先删除之前的商品规格选项
        LambdaUpdateWrapper<GoodsSpecificationOption> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(GoodsSpecificationOption::getGid, goodsId);
        goodsSpecifiactionOptionMapper.delete(lambdaUpdateWrapper);

        //3.1获取商品规格
        List<Specification> specifications = goods.getSpecifications();

        //循环商品规格
        for (Specification specification : specifications) {
            //获取商品规格项
            List<SpecificationOption> options = specification.getSpecificationOptions();

            for (SpecificationOption option : options) {
                //创建商品规格选项中间表的实体对象t_goods_specification_option
                GoodsSpecificationOption goodsSpecificationOption = new GoodsSpecificationOption();
                goodsSpecificationOption.setGid(goodsId);
                goodsSpecificationOption.setOptionId(option.getId());

                //插入t_goods_specification_option 数据
                goodsSpecifiactionOptionMapper.insert(goodsSpecificationOption);
            }
        }

    }

    @Override
    public Goods findById(Long id) {
        return goodsMapper.findById(id);
    }

    @Override
    public void putAway(Long id, Boolean isMarketable) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setIsMarketable(isMarketable);
        goodsMapper.updateById(goods);
    }

    @Override
    public Page<Goods> search(Goods goods, int page, int size) {
        //创建条件对象
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(goods) && StrUtil.isNotBlank(goods.getGoodsName())) {
            queryWrapper.like(Goods::getGoodsName, goods.getGoodsName());
            queryWrapper.or();
            queryWrapper.like(Goods::getCaption, goods.getGoodsName());
        }
        //创建分页对象
        Page<Goods> paramsPage = new Page<>(page, size);
        return goodsMapper.selectPage(paramsPage, queryWrapper);
    }
}
