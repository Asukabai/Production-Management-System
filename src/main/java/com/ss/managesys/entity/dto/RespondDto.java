package com.ss.managesys.entity.dto;

import com.ss.managesys.entity.code.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


/** ---gys-common---
 * 响应实体类
 * @author GuoYanSong*/

@Data
@AllArgsConstructor
public class RespondDto<T> implements Serializable {

    private Long result;
    private Long reqID = -1L;
    private Long respID = -1L;
    private String sender = "";
    private String sendee = "";
    /**消息体*/
    private  String msg;
    /**响应数据*/
    private T respData;

    public RespondDto(T respData) {
        this.respData = respData;
    }

    public RespondDto(Long result, String msg, T respData) {
        this.result = result;
        this.msg = msg;
        this.respData = respData;
    }

    public RespondDto(Long result, String msg) {
        this.result = result;
        this.msg = msg;
    }
}
