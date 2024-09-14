package com.quanshi.shopping_goods_service.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.Specification;
import com.quanshi.shopping_common.entity.SpecificationOption;
import com.quanshi.shopping_common.entity.SpecificationOptions;
import com.quanshi.shopping_common.service.ISpecificationService;
import com.quanshi.shopping_goods_service.mapper.SpecificationMapper;
import com.quanshi.shopping_goods_service.mapper.SpecificationOptionMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class SpecificationServiceImpl implements ISpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    @Override
    public void add(Specification specification) {
        specificationMapper.insert(specification);
    }

    @Override
    public void update(Specification specification) {
        specificationMapper.updateById(specification);
    }

    @Override
    public void delete(Long[] ids) {
        //创建条件对象
        LambdaUpdateWrapper<Specification> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Specification::getId,ids);
        specificationMapper.delete(updateWrapper);

        //specificationMapper.deleteBatchIds(Arrays.asList(ids));


    }

    @Override
    public Specification findById(Long id) {
        Specification specification = specificationMapper.findById(id);
        return specification;
    }


    @Override
    public List<Specification> findByProductTypeId(Long id) {

        List<Specification> specifications = specificationMapper.findByProductTypeId(id);

        return specifications;
    }

    @Override
    public Page<Specification> search(int page, int size) {
        return specificationMapper.selectPage(new Page<>(page,size),null);
    }


    @Override
    public void addOption(SpecificationOptions specificationOptions) {
        /*
        *
        * {
              "specId": 5
                  "optionName": [
                    "41寸",
                    "42寸",
                    "..."
                  ]
            }
        * */
        //将参数拆解封装成一个个商品规格项对应的实体类，插入商品规格项表中t_specification_option
        String[] optionNames = specificationOptions.getOptionName();
        for (String optionName : optionNames) {
            //创建商品规格选项对象
            SpecificationOption specificationOption = new SpecificationOption();
            specificationOption.setSpecId(specificationOptions.getSpecId());
            specificationOption.setOptionName(optionName);

            specificationOptionMapper.insert(specificationOption);
        }
    }

    @Override
    public void deleteOption(Long[] ids) {
        LambdaUpdateWrapper<SpecificationOption> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(SpecificationOption::getId,ids);
        specificationOptionMapper.delete(updateWrapper);

        //specificationOptionMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
