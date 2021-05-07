package com.xdcplus.xdcweb.basics.service;

import com.xdcplus.netty.common.model.AgvMessage;

/**
 * @author : Fish Fei
 */
public interface AckService {

    AgvMessage sendOkAck(AgvMessage agvMessage);

    void acceptAck(AgvMessage agvMessage);
}
