package com.yanxin.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultBody<T extends Object> implements Serializable {

    private Integer code;

    private String message;

    private T data;

}
