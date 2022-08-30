package org.example.IoTStudio.exception;


import org.example.IoTStudio.model.bean.CommonResult;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.authentication.BadCredentialsException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalException {

    // 捕获所有（Exception.class）中的异常并通过下面的方法返回
    @ExceptionHandler(value = Exception.class)
    public CommonResult<String> toHandlerException(Exception e) {
        //开发模式下将错误信息打印到控制台便与调试
        e.printStackTrace();
        if (e instanceof MethodArgumentNotValidException) {
            //参数验证错误
            return new CommonResult<>(40001, "Error", "参数验证错误");
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            //请求方法有误
            return new CommonResult<>(40002, "Error", "错误的请求方法");
        } else if (e instanceof HttpMessageNotReadableException) {
            //请求体错误
            return new CommonResult<>(40003, "Error", "错误的请求体");
        } else if (e instanceof IllegalArgumentException) {
            //错误的参数
            return new CommonResult<>(40004, "Error", "错误的请求参数");
        } else if (e instanceof AccessDeniedException) {
            //用户权限不足
            return new CommonResult<>(40005, "Error", e.getMessage());
        } else if (e instanceof BadCredentialsException) {
            //Token令牌过期
            return new CommonResult<>(40010, "Error", e.getMessage());
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户名或密码错误
            return new CommonResult<>(40011, "Error", e.getMessage());
        } else if (e instanceof DisabledException) {
            //账号被封禁
            return new CommonResult<>(40012, "Error", e.getMessage());
        } else if (e instanceof AuthenticationCredentialsNotFoundException) {
            //用户未登录
            return new CommonResult<>(40013, "Error", e.getMessage());
        } else if (e instanceof DuplicateKeyException) {
            //用户名重复
            return new CommonResult<>(40014, "Error", e.getMessage());
        } else {
            return new CommonResult<>(500, "Error", "未知错误！");
        }
    }
}

