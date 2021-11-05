package com.yanxin.admin.elastic.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-09-23 15:31
 */
@Document(indexName = "task")
@Data
public class TaskExecuteInfo {

    @Id
    private String _id;

    @Field(type = FieldType.Date, format = DateFormat.date)
    private String create_date;

    @Field(type = FieldType.Keyword)
    private String job_num;

    @Field(type = FieldType.Text)
    private String logs;

    @Field(type = FieldType.Keyword)
    private Integer operator;

    @Field(type = FieldType.Object)
    private Object response;

    @Field(type = FieldType.Keyword)
    private String status;

}
