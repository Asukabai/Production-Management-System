package com.ss.managesys.config;

import com.ss.managesys.interceptors.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** ---gys-common---
 * @author GuoYansong
 * JWT token 过滤器配置
 */

@Configuration
public class JWTInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private JWTInterceptor jwtInterceptor;

    /**
     * 添加拦截器JWTInterceptor 并进行配置
     * 放行user/CommonUserLogin (登陆)
     * 拦截其余所有路径进行token验证
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/managesys/server/user/**",
                        "/managesys/server/examiner/**",
                        "/managesys/server/operator/**",
                        "/managesys/server/plan/**"
                        )
                .excludePathPatterns("/managesys/server/user/cuser_login");
    }
}
