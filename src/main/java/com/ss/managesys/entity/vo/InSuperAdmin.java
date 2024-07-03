package com.ss.managesys.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**接收 超级管理员操作员工 入参
 * @author GuoYanSong
 * @Date 2023-4-12*/
@Data
public class InSuperAdmin implements Serializable {
    private Integer auth;

//    @SerializedName(value = "userID")
    @JsonProperty("userID")
    private Integer userId;
    private String newUserName;
    private String keywords;
    private Integer currentPage;
    private Integer pageSize;
    private ArrayList<Integer> userIDList;

}
