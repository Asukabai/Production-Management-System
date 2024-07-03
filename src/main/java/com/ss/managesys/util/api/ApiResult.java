package com.ss.managesys.util.api;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** ---gys-common---
 * @Author : GuoYanSong
 * 使用该自定义注解后每个方法都返回 ResponseObj类型
 * 需配合 MyResponseBodyAdvice 使用
 * @Date 2023-2-8
 * @Target 表明注解是用在方法上的
 * @Retention 定义被它所注解的注解保留到runtime运行时
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@RestController
//@Controller
public @interface ApiResult {
}