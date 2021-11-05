package com.yanxin.admin.elastic.entity;

import lombok.Data;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-11-04 10:55
 */
@Data
public class TaskResponse {

    private String end_date;
    private String target;
    private String start_date;
    private String status;
    private String result;
    private String out_param;
}
