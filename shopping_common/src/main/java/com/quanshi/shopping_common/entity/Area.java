package com.quanshi.shopping_common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 行政区域县区信息表
 * </p>
 *
 * @author
 * @since 2024-09-12
 */
@Getter
@Setter
@TableName("t_area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    @TableField("id")
    private String id;

    /**
     * 区域名称
     */
    @TableField("area")
    private String area;

    /**
     * 城市ID
     */
    @TableField("cityId")
    private String cityId;
}
