package com.yanxin.admin.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoodsDTO implements Serializable {

    private Integer goodsId;

    private String name;

    private Integer stock;

    private BigDecimal credit;

    private String imageUrl;

    private Integer createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    private Integer updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;
}
