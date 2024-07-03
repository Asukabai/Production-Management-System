package com.ss.managesys.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**prod_task_user 实体类
 * @author GuoYanSong
 * @Date 2023-4-16*/
@Data
@TableName("prod_task_user")
public class ProdTaskUser {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer Id;
    private Integer prodId;
    private String taskName;
    private Integer userID;
    private Integer auth;
}
