package com.ss.managesys.entity.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**接收 登陆用入参
 * @author GuoYanSong
 * @Date 2023-4-12*/
@Data
public class InLogin implements Serializable {

    //    un    pw 仅登陆使用
    @SerializedName("userName")
    private String userName;
    private String passWord;

    private String oldPW;
    private String newPW;
}
