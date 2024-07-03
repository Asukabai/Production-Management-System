package com.ss.managesys.entity.to;

import lombok.Data;

/**[组长] 指派任务
 * @author GuoYanSong
 * @Date 2023-4-20*/
@Data
public class ProdTaskUserTo {
    private Integer Id;
    private Integer prodId;
    private String taskName;
    private Integer userID;
    private Integer auth;
    private Integer taskOrder;
    private Integer teamleaderId;
}
