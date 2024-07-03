package com.ss.managesys.interceptors.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.util.api.ApiResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/** ---gys-common---
 * 响应增强类  本质仍是拦截器
 * @Author : GuoYanSong
 * 响应信息统一包装返回
 * supports() 方法判断通过后使用beforeBodyWrite() 方法返回，默认为ok(data） 其他模式需要自定义
 * 最主要的作用是将其他数据类型以设定格式：resultObj 响应
 * @Date 2023-2-8
 **/
@RestControllerAdvice
@Slf4j
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //判断是否使用了自定义注解
        if(returnType.getDeclaringClass().isAnnotationPresent(ApiResult.class)){
            return true;
        }else {
            return false;
        }
    }

    /**重写beforeBodyWrite方法
     * 默认返回的格式是 请求成功的自定义数据类型；
     * 1、如果data 即 controller 返回的值是预期格式 RespondObj 则直接返回；
     * 2、controller 会直接将 String 类型返回，所以将当为 String 类型时进行 json 转化；
     * 3、其他情况下则将controller 的 return值作为data 重新格式化；
     **/
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (data instanceof RespondDto) {
            return data;
        } else if (data instanceof String) {
            try{
                log.info("String转json了");
                response.getHeaders().setContentType(MediaType.parseMediaType("application/json;charset=utf-8"));
                return objectMapper.writeValueAsString(new RespondDto(data));
            }catch (JsonProcessingException e){
                throw new RuntimeException("将 Response 对象序列化为 json 字符串时发生异常", e);
            }
        } else {
            log.info("advice统一返回值转换完毕");
            return new RespondDto(data);
        }
    }
}
