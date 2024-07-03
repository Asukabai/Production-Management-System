package com.ss.managesys.push_data;

import com.ss.managesys.mapper.PushDataMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import hprose.client.HproseClient;
import hprose.client.HproseHttpClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Component
public class PushDataMain {

    @Resource
    private PushDataMapper pushDataMapper;

//    public static void main(String[] args) {
//        HproseHttpClient hproseClient= new HproseHttpClient();
//        hproseClient.useService("http://192.168.1.21:22324/ssmonitor/server/rpc");
//        PushDataService pushDataService=  hproseClient.useService(PushDataService.class);
//      Boolean isSendSucc= pushDataService.sendOneClientMessage(
//              new PushOneClientData("123asd","message",System.currentTimeMillis()+"1111111"));
//        System.out.println("isSendSucc  :"+isSendSucc);
//    }

    /*hprosePush */
    public void hprosePush(PushProductMGNData pushProductMGNData) {
        HproseHttpClient hproseClient= new HproseHttpClient();
        hproseClient.useService("http://192.168.1.21:22324/ssmonitor/server/rpc");
        PushDataService pushDataService=  hproseClient.useService(PushDataService.class);
        Boolean isSendSucc= pushDataService.sendOneClientMessage(
                new PushOneClientData("123asd","message",pushProductMGNData));
        System.out.println("isSendSucc  :"+isSendSucc);
    }

    public void creatAndPushData(String prodModel) {
        ArrayList<PushTaskMGNData> pushTaskMGNData = pushDataMapper.selectPushTaskMGNData(prodModel);
        PushProductMGNData pushProductMGNData = pushDataMapper.selectPushProductMGNData(prodModel);
        pushProductMGNData.setTask(pushTaskMGNData);
        pushData(pushProductMGNData);
    }
    public void creatAndPushData(String prodModel,String taskName) {
        PushProductMGNData pushProductMGNData = pushDataMapper.selectPushProductMGNData(prodModel);
        ArrayList<PushTaskMGNData> pushTaskOne = pushDataMapper.selectPushTaskOne(prodModel, taskName);
        pushProductMGNData.setTask(pushTaskOne);
        hprosePush(pushProductMGNData);
    }

    private void pushData(PushProductMGNData pushProductMGNData) {
        HproseHttpClient hproseClient= new HproseHttpClient();
        hproseClient.useService("http://192.168.1.21:22324/ssmonitor/server/rpc");
        PushDataService pushDataService=  hproseClient.useService(PushDataService.class);
        Boolean isSendSucc= pushDataService.sendOneClientMessage(
                new PushOneClientData("123asd","message",pushProductMGNData));
        System.out.println("isSendSucc  :"+isSendSucc);
    }
}
