package com.ss.managesys.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ss.managesys.entity.po.User;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Calendar;

/** ---gys-common---
 * JWT工具类
 *  @author GuoYanSong*/

@Slf4j
@Service
public class JWTUtils {

    private static final String SIGN = "1qwe";

    /**生成token  header.payload.signature
     * 设置过期时间  .withExpiresAt(instance.getTime())  7day*/
    public String tokenMaker(User user){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);

//        .withHeader(),header是默认的可以不写
        JWTCreator.Builder builder = JWT.create();
//                payload  不可放置登陆密码
        builder.withClaim("name",user.getUserName());
        builder.withClaim("userId",user.getUserId().toString());
        builder.withClaim("time",System.currentTimeMillis());

        String token = "Bearer "+builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN))+"";

//        log.info("生成的token：{}",token);
        return token;
    }

    /**验证token
     *验证失败会抛异常，成功则返回DecodedJWT对象verify
     * @param token
     * @return DecodedJWT
     *int值无法用asString获取 */
    public DecodedJWT tokenUse(@NonNull String token){
        //创建验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGN)).build();
        //验证token
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify;
    }

    public String getJustToken(@RequestHeader("token") String header){
        return header.substring(6);
    }
}
