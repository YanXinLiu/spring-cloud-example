package com.yanxin.common.utils;

import com.yanxin.common.base.ResultBody;
import com.yanxin.common.consts.ResultEnum;

public class ResultUtils {


	public static ResultBody success(Object data){
		return new ResultBody(ResultEnum.OK.getCode(), "success", data);
	}

	public static ResultBody error(){
		return error(ResultEnum.ERROR.getCode(), "error");
	}

	public static ResultBody error(String message){
		return error(ResultEnum.ERROR.getCode(), message);
	}

	public static ResultBody error(int code, String message){
		return new ResultBody(code, message, null);
	}

	public static ResultBody error(int code, String message, Object data) {
		return new ResultBody(code, message, data);
	}
	
}
