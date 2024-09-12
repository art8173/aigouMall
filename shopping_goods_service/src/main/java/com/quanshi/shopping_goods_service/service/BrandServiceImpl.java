package com.quanshi.shopping_goods_service.service;

import com.quanshi.shopping_common.entity.Brand;
import com.quanshi.shopping_common.enums.BusErrorEnum;
import com.quanshi.shopping_common.exception.BusException;
import com.quanshi.shopping_common.service.IBrandService;
import com.quanshi.shopping_goods_service.mapper.BrandMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


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
}
