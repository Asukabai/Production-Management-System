package com.ss.managesys.entity.code;

/** ---gys-common---
 * 响应码
* @author GuoYansong*/
public class ResultCode {
    /**空数据*/
    public static final Long NO = 0L;
    /**成功*/
    public static final Long OK = 1L;
    /**D90已拆除*/
    public static final Long D90 = 2L;
    /**其他错误*/
    public static final Long OTHER_ERROR = -1L;
    /**用户密码错误*/
    public static final Long USER_PASSWORD_ERROR = -100L;
    /**用户不存在*/
    public static final Long USER_NONE = -101L;
    /**请求过快*/
    public static final Long REQUEST_TO0_FAST = -102L;
    /**ip访问受限*/
    public static final Long IP_VISIT_CANt = -103L;
    /**请求类型错误*/
    public static final Long REQUEST_TYPE_ERROR = -104L;


    /**未知异常*/
    public static final Long UNKNOWN_ABNORMAL = -9999L;
}
