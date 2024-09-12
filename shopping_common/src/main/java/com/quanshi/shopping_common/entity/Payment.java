package com.quanshi.shopping_common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@TableName("t_payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("orderId")
    private String orderId;

    @TableField("transactionId")
    private String transactionId;

    @TableField("tradeType")
    private String tradeType;

    @TableField("tradeState")
    private String tradeState;

    @TableField("payerTotal")
    private BigDecimal payerTotal;

    @TableField("content")
    private String content;

    @TableField("createTime")
    private LocalDateTime createTime;
}
