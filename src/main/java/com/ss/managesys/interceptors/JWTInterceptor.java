package com.ss.managesys.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ss.managesys.util.JWTUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

/** ---gys-common---
 * @author GuoYansong
 * JWT token过滤器
 * 需要配合配置文件使用 config
 */

@Slf4j
@Component
public class JWTInterceptor implements HandlerInterceptor {

    LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
//    stringLongHashMap 迭代2.0 使用 用来存放用户的tel 和 time
    HashMap<String, Long> stringLongHashMap = new HashMap();

    @Autowired
    private JWTUtils jwtUtils;

    /**
     * 1、获取请求头中的token
     * 此处设定情况 为携带Bearer的状态
     * 2、验证令牌
     * 3、前段携带 token 字段名称为 Authorization 2023-2-24日修改
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tokenTest = request.getHeader("Authorization");
//        log.info("打印token:{}", tokenTest);
        if (tokenTest == null) {
            log.warn("Authorization为空");
            return false;
        } else {
//            linkedHashSet.add(tokenTest.substring(7));


            /*-----------------迭代 3.0 设置用户任意登陆设备数量  2023-3-13----------------------------*/
//            /**限制每个用户任意设备在线
//             * 1、遍历存放 token 的集合 linkedHashSet ，
//             *                  解析出对应的用户标识tel ，作为key 存入 threeHashMap;
//             * 2、再次遍历 linkedHashSet 依据tel 存入 time；
//             * 3、当time 数量（设备数量大于3时）剔除最小的time；
//             * 4、校验：返回是否通过；*/
//            HashMap<String, ArrayList<Long>> threeHashMap = new HashMap<>();
//
//            for (String s : linkedHashSet) {
//                DecodedJWT decode = JWT.decode(s);
//                String tel = decode.getClaim("tel").asString();
//                ArrayList<Long> longs = new ArrayList<>();
//                threeHashMap.put(tel, longs);
//            }
//
//            for (String s : linkedHashSet) {
//                DecodedJWT decode = JWT.decode(s);
//                String tel = decode.getClaim("tel").asString();
//                Long time = decode.getClaim("time").asLong();
//                threeHashMap.get(tel).add(time);
////                log.info("【【【hashMap->arraylist:time:{},tel:{}]",threeHashMap.get(tel),tel);
//                if (threeHashMap.get(tel).size() > 3) {
////                    log.info("【【Collections.min(threeHashMap.get(tel):{}",threeHashMap.get(tel).indexOf(Collections.min(threeHashMap.get(tel))));
//                    threeHashMap.get(tel).remove(threeHashMap.get(tel).indexOf(Collections.min(threeHashMap.get(tel))));
////                    log.info("【【【remove后arraylist】->arraylist:{}",threeHashMap.get(tel));
//                }
//            }
//
//            /*剔除 set 中过时的 token*/
//            Iterator<String> iterator = linkedHashSet.iterator();
//            if (iterator.hasNext()){
//                String next = iterator.next();
//                DecodedJWT decode = JWT.decode(next);
//                String tel = decode.getClaim("tel").asString();
//                Long time = decode.getClaim("time").asLong();
//                if(!threeHashMap.get(tel).contains(time)){
//                    linkedHashSet.remove(next);
//                }
//            }
//
//            String authorization = request.getHeader("Authorization").substring(7);
//            DecodedJWT decodedJWT = JWT.decode(authorization);
//            String tel = decodedJWT.getClaim("tel").asString();
//            Long time = decodedJWT.getClaim("time").asLong();
//            if (threeHashMap.get(tel).contains(time)) {
//                log.info("hashMapTime:{},time:{}",threeHashMap.get(tel),time);
//                log.info("放行此用户，此时该用户：tel:{}已登录设备数量：{}", tel, threeHashMap.get(tel).size());
//                return true;
//            } else {
//                log.info("hashMapTime:{},time:{}",threeHashMap.get(tel),time);
//                log.info("拦截此用户，此时该用户：tel:{}已登录设备数量：{}", tel, threeHashMap.get(tel).size());
//                return false;
//            }
//        }
//    }

            /*-----------------迭代 2.0 用户只能登陆单设备  2023-3-10----------------------------*/
            /**限制每个用户只能一台设备在线
             * 1、linkedHasSet 确保 token 唯一存入
             * 2、遍历linkedHashSet 把token载荷解码为 tel 和 time 并存入hashmap tel 为 key time 为value
             * 3、以当前token的key 去获取hashmap中保存的key对应的 时间戳，比较时间戳 保存为最新时间戳
             * 4、以当前token的key 比较hashmap中对应key的时间戳，相同则通过校验*/

            /*-------遍历LinkedHashSet 一共有多少 token 进行请求------*/
////            Iterator<String> it1 = linkedHashSet.iterator();
////            int i = 1;
////            while (it1.hasNext()){
////                System.out.println((i++)+"次linedHashSet:"+it1.next());
////            }
//            /*--------遍历 linkedHashSet 剔除重复的登录用户--------*/
//            for (String o : linkedHashSet) {
//
//                DecodedJWT decode = JWT.decode(o);
//
//                String tel = decode.getClaim("tel").asString();
//                Long time = decode.getClaim("time").asLong();
//
//                /*--------防止空map全走 null->else--------*/
////                stringLongHashMap.put(tel, time);
//
//                if (stringLongHashMap.get(tel) != null) {
//                    if (time > stringLongHashMap.get(tel)) {
//                        stringLongHashMap.remove(tel);
//                        stringLongHashMap.put(tel, time);
//                        log.info("存在新时间：{}", stringLongHashMap.get(tel));
//                    }
//
//                } else {
//                    stringLongHashMap.put(tel, time);
//                    log.info("不存在：{}", stringLongHashMap.get(tel));
//                }
//            }
//
////        System.out.println("-----剔除重复用户后的：stringLongHashMap-----");
////        for (Map.Entry<String, Long> stringLongEntry : stringLongHashMap.entrySet()) {
////            System.out.println(stringLongEntry);
////        }
//
//            String authorization = request.getHeader("Authorization").substring(7);
//            DecodedJWT decodedJWT = JWT.decode(authorization);
//            String tel = decodedJWT.getClaim("tel").asString();
//            Long time = decodedJWT.getClaim("time").asLong();
//
//            /*剔除 set 中过时的 token*/
//
//            Iterator<String> iterator = linkedHashSet.iterator();
//            while (iterator.hasNext()){
//                String next = iterator.next();
//                DecodedJWT decode = JWT.decode(next);
//                String itTel = decode.getClaim("tel").asString();
//                Long itTime = decode.getClaim("time").asLong();
//
//                if(!stringLongHashMap.get(itTel).equals(itTime)){
////                    linkedHashSet.remove(next);
//                    iterator.remove();
//                }
//            }
//            /*遍历hashset2*/
////            int j = 1;
////            Iterator<String> it2 = linkedHashSet.iterator();
////            while (it2.hasNext()){
////                System.out.println("删除后："+(j++)+"次linedHashSet:"+it2.next());
////            }
//
//
//            if (stringLongHashMap.get(tel).longValue() == time.longValue()) {
//                log.info("单设备登陆成功gettel:{},time:{}", stringLongHashMap.get(tel), time);
//                return true;
//            } else {
//                log.info("单设备登陆失败gettel:{},time:{}", stringLongHashMap.get(tel), time);
//                return false;
//            }
//        }
//    }
            /*------------------迭代 1.0 校验token--------------------------*/
//            log.info("开始进行token校验 获取token:{}", tokenTest);
            if (tokenTest == null) {
                log.info("没有Authorization，如果是登陆则忽略");
                return false;
            } else {
                if (request.getHeader("Authorization").length() > 6) {
                    String token = request.getHeader("Authorization").substring(7);
                    jwtUtils.tokenUse(token);
//                    log.info("Authorization:{}", token);
                    return true;
                } else {
                    return false;
                }
            }

        }
    }
}
