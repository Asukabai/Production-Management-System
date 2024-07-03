package com.ss.managesys.push_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SseSendOneData
 * @Description TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushOneClientData {

    String clientId;
    String msgType;
    Object data;
}
