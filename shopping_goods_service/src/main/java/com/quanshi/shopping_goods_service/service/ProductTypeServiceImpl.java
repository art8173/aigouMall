package com.quanshi.shopping_goods_service.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.ProductType;
import com.quanshi.shopping_common.enums.BusErrorEnum;
import com.quanshi.shopping_common.exception.BusException;
import com.quanshi.shopping_common.service.IProductTypeService;
import com.quanshi.shopping_goods_service.mapper.ProductTypeMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@DubboService
public class ProductTypeServiceImpl implements IProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public void add(ProductType productType) {
        /**
         * 保存思路
         * 1,根据前端提交的商品分类的Parentid去数据库查询对应的父分类的级别
         *  没有：当前包保存的数据就是1或者2级分类
         *  1/2级别：当前分类就是二级或者三级，设置当前分类的级别父分类级别+1
         *  三级分类：分类最多三级，三级分类不可以再设置子分类，抛出逻辑异常，告诉用户最多三级分类
         */

        //1,根据前端提交的商品分类的Parentid去数据库查询对应的父分类的级别
        ProductType parent = productTypeMapper.selectById(productType.getParentId());
        if(Objects.isNull(parent)){
            productType.setLevel(1);
        } else if (parent.getLevel()<3) {//父分类是一级或者是二级
            productType.setLevel(parent.getLevel()+1);
        } else if (parent.getLevel()==3) {//前端选择父分类是三级
            BusException.busException(BusErrorEnum.PRODUCT_TYPE_LEVEL_ERROR);
        }
        productTypeMapper.insert(productType);

    }

    @Override
    public void update(ProductType productType) {
        /**
         * 保存思路
         * 1,根据前端提交的商品分类的Parentid去数据库查询对应的父分类的级别
         *  没有：当前包保存的数据就是1或者2级分类
         *  1/2级别：当前分类就是二级或者三级，设置当前分类的级别父分类级别+1
         *  三级分类：分类最多三级，三级分类不可以再设置子分类，抛出逻辑异常，告诉用户最多三级分类
         */

        //1,根据前端提交的商品分类的Parentid去数据库查询对应的父分类的级别
        ProductType parent = productTypeMapper.selectById(productType.getParentId());
        if(Objects.isNull(parent)){
            productType.setLevel(1);
        } else if (parent.getLevel()<3) {//父分类是一级或者是二级
            productType.setLevel(parent.getLevel()+1);
        } else if (parent.getLevel()==3) {//前端选择父分类是三级
            BusException.busException(BusErrorEnum.PRODUCT_TYPE_LEVEL_ERROR);
        }
        productTypeMapper.updateById(productType);

    }

    @Override
    public ProductType findById(Long id) {
        return productTypeMapper.selectById(id);
    }

    @Override
    public void delete(Long id) {
        /**
         * 删除思路
         * 先根据删除的id查询是否有子分类
         * 没有：直接删除
         * 有：抛出逻辑异常，该分类还有子分类，不能删除
         */
        //根据删除的id查询是否有子分类
        LambdaQueryWrapper<ProductType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductType::getParentId,id);
        Long childCount = productTypeMapper.selectCount(queryWrapper);
        if (childCount>0){
            BusException.busException(BusErrorEnum.PRODUCT_TYPE_DELETE_ERROR);
        }
        productTypeMapper.deleteById(id);
    }

    @Override
    public Page<ProductType> search(ProductType productType, int page, int size) {
        LambdaQueryWrapper<ProductType> queryWrapper = new LambdaQueryWrapper<>();
        if(Objects.nonNull(productType)){
            if(StrUtil.isNotBlank(productType.getName())){
                queryWrapper.like(ProductType::getName,productType.getName());
            }

            if(productType.getParentId()>=0){
                queryWrapper.eq(ProductType::getParentId,productType.getParentId());
            }

        }

        return productTypeMapper.selectPage(new Page<>(page, size), queryWrapper);
    }

    @Override
    public List<ProductType> findProductType(ProductType productType) {
        LambdaQueryWrapper<ProductType> queryWrapper = new LambdaQueryWrapper<>();
        if(Objects.nonNull(productType) && StrUtil.isNotBlank(productType.getName())){
            queryWrapper.like(ProductType::getName,productType.getName());
        }
        return productTypeMapper.selectList(queryWrapper);
    }

    @Override
    public List<ProductType> findByParentId(Long parentId) {
        LambdaQueryWrapper<ProductType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductType::getParentId,parentId);
        return productTypeMapper.selectList(queryWrapper);
    }
}
