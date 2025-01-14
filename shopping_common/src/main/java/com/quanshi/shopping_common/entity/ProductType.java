package com.quanshi.shopping_common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 商品类目
 * </p>
 *
 * @author
 * @since 2024-09-12
 */
@Getter
@Setter
@TableName("t_product_type")
public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类目名称
     */
    @TableField("name")
    private String name;

    /**
     * 类型级别，分为1,2,3级,不能超过3级。
     */
    @TableField("level")
    private Integer level;

    /**
     * 父类目ID=0时，代表的是一级的类目
     */
    @TableField("parentId")
    private Long parentId;
}
