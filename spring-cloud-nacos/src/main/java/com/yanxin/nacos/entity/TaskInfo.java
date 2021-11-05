package com.yanxin.nacos.entity;

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
public class TaskInfo {

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


    public String get_id() {
        return _id;
    }

    public TaskInfo set_id(String _id) {
        this._id = _id;
        return this;
    }

    public String getCreate_date() {
        return create_date;
    }

    public TaskInfo setCreate_date(String create_date) {
        this.create_date = create_date;
        return this;
    }

    public String getJob_num() {
        return job_num;
    }

    public TaskInfo setJob_num(String job_num) {
        this.job_num = job_num;
        return this;
    }

    public String getLogs() {
        return logs;
    }

    public TaskInfo setLogs(String logs) {
        this.logs = logs;
        return this;
    }

    public Integer getOperator() {
        return operator;
    }

    public TaskInfo setOperator(Integer operator) {
        this.operator = operator;
        return this;
    }

    public Object getResponse() {
        return response;
    }

    public TaskInfo setResponse(Object response) {
        this.response = response;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskInfo setStatus(String status) {
        this.status = status;
        return this;
    }
}
