package com.ss.managesys.service.impl;

import com.ss.managesys.entity.code.ResultCode;
import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.to.ProdTaskUserTo;
import com.ss.managesys.entity.vo.InOprLeader;
import com.ss.managesys.entity.vo.OprLeaderVo;
import com.ss.managesys.mapper.OprLeaderMapper;
import com.ss.managesys.service.OprLeaderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OprLeaderServiceImpl implements OprLeaderService {
    @Resource
    private OprLeaderMapper oprLeaderMapper;


    /**【第三方检验员终止任务】
     * update 修改任务状态为 任务终止6
     * 修改产品状态为 已停工 5*/
    @Override
    public RespondDto examinerStopTask(InOprLeader inOprLeader) {

        Integer taskStatus = oprLeaderMapper.updateTaskStatus(inOprLeader.getProdModel(), inOprLeader.getTaskName());
        Integer productStatus = oprLeaderMapper.updateProductStatus(inOprLeader.getProdModel());
        if(taskStatus == productStatus && taskStatus == 1){
            return new RespondDto(ResultCode.OK,"检验员终止任务成功");
        }else {
            return new RespondDto(ResultCode.NO,"检验员终止任务失败");
        }
    }

    /**  【操作员组长】 派发任务*/
    @Override
    public RespondDto sendTask(InOprLeader inOprLeader) {
        ProdTaskUserTo prodTaskUserTo = oprLeaderMapper.selectProdIdAndTaskOrder(inOprLeader.getProdModel(), inOprLeader.getTaskName(),inOprLeader.getLeaderId());
        if(prodTaskUserTo != null){
            oprLeaderMapper.insertptu(prodTaskUserTo.getProdId(),inOprLeader.getTaskName(),
                    inOprLeader.getMemberId(),prodTaskUserTo.getTaskOrder(),inOprLeader.getLeaderId());
            return new RespondDto(ResultCode.OK,"组长派发任务成功");
        }else {
            return new RespondDto(ResultCode.NO,"组长派发任务失败，检查任务");
        }
    }

    /**【组员列表】*/
    @Override
    public RespondDto showAllMember(InOprLeader inOprLeader) {
        List<OprLeaderVo> oprLeaderVos = oprLeaderMapper.showAllmemberIdAndName();
        return new RespondDto(ResultCode.OK,"组员姓名列表查询成功",oprLeaderVos);
    }
}
