package com.quanshi.shopping_goods_service.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.Brand;
import com.quanshi.shopping_common.enums.BusErrorEnum;
import com.quanshi.shopping_common.exception.BusException;
import com.quanshi.shopping_common.service.IBrandService;
import com.quanshi.shopping_goods_service.mapper.BrandMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;


@DubboService
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public Brand findById(Long id) {

        if(id < 0){
            //抛出业务逻辑异常，参数错误
//            throw new BusException(BusErrorEnum.PARAMETER_ERROR);
            BusException.busException(BusErrorEnum.PARAMETER_ERROR);
        } else if (id==0) {
            System.out.println(1/0);//模拟系统异常
        }

        return brandMapper.selectById(id);
    }

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectList(null);
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateById(brand);
    }

    @Override
    public void delete(Long id) {
        brandMapper.deleteById(id);
    }

    @Override
    public Page<Brand> search(Brand brand, int page, int size) {

        //创建条件对象
        LambdaQueryWrapper<Brand> queryWrapper = new LambdaQueryWrapper<>();

        if (Objects.nonNull(brand) && StrUtil.isNotBlank(brand.getName())){
            //品牌名称模糊查询
            queryWrapper.like(Brand::getName,brand.getName());
        }

        //创建分页对象
        Page<Brand> paramsPage = new Page<>(page,size);

        return brandMapper.selectPage(paramsPage,queryWrapper);
    }

    public static void main(String[] args) {

        //实际开发中，“” “ ” null 三种都是空
        System.out.println(StrUtil.isNotBlank(""));
        System.out.println(StrUtil.isNotBlank(" "));
        System.out.println(StrUtil.isNotBlank(null));
        System.out.println(StrUtil.isNotBlank("华为"));


    }

}
