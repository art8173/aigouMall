package com.quanshi.shopping_common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author
 * @since 2024-09-12
 */
@Getter
@Setter
@TableName("t_seckill_goods")
public class SeckillGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 秒杀商品Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 对应的商品Id
     */
    @TableField("goodsId")
    private Long goodsId;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 商品图片
     */
    @TableField("headerPic")
    private String headerPic;

    /**
     * 原价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 秒杀价格
     */
    @TableField("costPrice")
    private BigDecimal costPrice;

    /**
     * 开始时间
     */
    @TableField("startTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("endTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 秒杀商品数
     */
    @TableField("num")
    private Integer num;

    /**
     * 剩余库存数
     */
    @TableField("stockCount")
    private Integer stockCount;

    /**
     * 描述
     */
    @TableField("introduction")
    private String introduction;
}
