package com.ss.managesys.mapper;

import com.ss.managesys.push_data.PushProductMGNData;
import com.ss.managesys.push_data.PushTaskMGNData;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**PushData
 * @author GuoYanSong
 * @Date 2023-4-18*/
@Mapper
public interface PushDataMapper {
    /**查 Task */
    ArrayList<PushTaskMGNData> selectPushTaskMGNData(String prodModel);

    /**查 单个Task */
    ArrayList<PushTaskMGNData> selectPushTaskOne(String prodModel,String taskname);

    /**查 product */
    PushProductMGNData selectPushProductMGNData(String prodModel);
}
