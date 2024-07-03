package com.ss.managesys.interceptors.exception;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ss.managesys.entity.code.ResultCode;
import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.util.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** ---gys-common---
 * 全局异常响应
 * @author GuoYansong
 * */

@Slf4j
@RestControllerAdvice
@ApiResult
public class GlobalExceptionHandler {

//    /**空指针异常*/
//    @ExceptionHandler({NullPointerException.class})
//    public RespondDto nullPointException(NullPointerException e){
//        log.info("空指针异常:{},全局异常已生效",e.getMessage());
//        return new RespondDto(ResultCode.NO,"全局异常生效，空指针异常",e.getMessage());
//    }

    /**Excel文件上传版本不支持*/
    @ExceptionHandler({StringIndexOutOfBoundsException.class})
    public RespondDto stringIndexException(StringIndexOutOfBoundsException e){
        log.info("String字符截取异常:{},全局异常已生效",e.getMessage());
        return new RespondDto(ResultCode.OTHER_ERROR,"String字符截取异常:{}",e.getMessage());
    }

    /**处理失效的payload异常*/
    @ExceptionHandler({InvalidClaimException.class})
    public RespondDto tokenException(InvalidClaimException e){
        String message = e.getMessage();
        log.info("失效的payload异常：{}，全局异常已生效",message);
        return new RespondDto(ResultCode.UNKNOWN_ABNORMAL,"全局异常生效，失效的payload："+message);
    }

    /**处理算法不匹配异常*/
    @ExceptionHandler({AlgorithmMismatchException.class})
    public RespondDto tokenException(AlgorithmMismatchException e){
        String message = e.getMessage();
        log.info("算法不匹配异常：{}，全局异常已生效",message);
        return new RespondDto(ResultCode.UNKNOWN_ABNORMAL,"全局异常生效，算法不匹配："+message);
    }

    /**处理令牌过期异常*/
    @ExceptionHandler({TokenExpiredException.class})
    public RespondDto tokenException(TokenExpiredException e){
        String message = e.getMessage();
        log.info("处理令牌过期异常：{}，全局异常已生效",message);
        return new RespondDto(ResultCode.UNKNOWN_ABNORMAL,"全局异常生效，处理令牌过期："+message);
    }

    /**处理token签名不一致异常*/
    @ExceptionHandler({SignatureVerificationException.class})
    public RespondDto tokenException(SignatureVerificationException e){
        String message = e.getMessage();
        log.info("token签名不一致异常：{}，全局异常已生效",message);
        return new RespondDto(ResultCode.UNKNOWN_ABNORMAL,"全局异常生效，token签名不一致："+message);
    }

//    /**处理其他异常*/
//    @ExceptionHandler({Exception.class})
//    public RespondDto onKnowException(Exception e){
//        String message = e.getMessage();
//        log.info("未知异常：{}，全局异常已生效",message);
//        return new RespondDto(ResultCode.UNKNOWN_ABNORMAL,"全局异常生效，异常信息："+message);
//    }


}
