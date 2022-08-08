package com.yanxin.credit.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-10 17:23
 */
@Data
public class CarDTO {

    private Integer carId;
    /**
     * 厂商
     */
    private String company;

    /**
     * 报废价格
     */
    private BigDecimal price;

    /**
     * 报废类型 car,轿车, bus 汽车, truck 货车
     */
    private String recycleType;

    /**
     * 报废时间
     */
    private Date recycleTime;

    /**
     * 回收人名称
     */
    private String recycleUser;

    /**
     * 收车地点
     */
    private String recycleLocation;

    /**
     * 驾驶证
     */
    private Boolean driverLicense;

    /**
     * 行驶证
     */
    private Boolean motiveLicense;

    /**
     * 手续是否办完
     */
    private Boolean finish;
}
