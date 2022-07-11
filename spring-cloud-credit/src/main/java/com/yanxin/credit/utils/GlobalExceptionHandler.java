package com.yanxin.credit.utils;

import com.yanxin.common.base.ResultBody;
import com.yanxin.common.enums.ResultEnum;
import com.yanxin.common.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 统一异常处理
     * AuthenticationException
     *
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public static ResultBody authenticationException(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        ResultEnum code = ResultEnum.ERROR;
        if (ex instanceof UsernameNotFoundException) {
            code = ResultEnum.USERNAME_NOT_FOUND;
        } else if (ex instanceof BadCredentialsException) {
            code = ResultEnum.BAD_CREDENTIALS;
        } else if (ex instanceof AccountExpiredException) {
            code = ResultEnum.ACCOUNT_EXPIRED;
        } else if (ex instanceof LockedException) {
            code = ResultEnum.ACCOUNT_LOCKED;
        } else if (ex instanceof DisabledException) {
            code = ResultEnum.ACCOUNT_DISABLED;
        } else if (ex instanceof CredentialsExpiredException) {
            code = ResultEnum.CREDENTIALS_EXPIRED;
        } else if (ex instanceof InsufficientAuthenticationException) {
            code = ResultEnum.UNAUTHORIZED;
        }
        return ResultUtils.error(code.getCode(), ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public static ResultBody handlerException(Exception e) {

        return ResultUtils.error(ResultEnum.ERROR.getCode(), e.getMessage());
    }
}
