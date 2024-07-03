package com.ss.managesys.push_data;

import com.ss.managesys.entity.po.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @ClassName SseSendOneData
 * @Description TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushProductMGNData {
    String prodModel;
    String prodStage;
    String prodDirector;
    String prodStatus;
    ArrayList<PushTaskMGNData> task;
//    List<Task> task;
}
