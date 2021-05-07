package com.xdcplus.xdcweb.basics.service.impl;

import com.xdcplus.netty.common.model.AgvMessage;
import com.xdcplus.xdcweb.basics.handler.ClientHandler;
import com.xdcplus.xdcweb.basics.service.AckService;

/**
 * @author : Fish Fei
 */
public class AckServiceImpl implements AckService {
    @Override
    public AgvMessage sendOkAck(AgvMessage agvMessage) {
        AgvMessage agvMessage1=new AgvMessage();
        agvMessage1.setPackNr(agvMessage.getPackNr()+1);
        agvMessage1.setPackAckNr(agvMessage.getPackNr());
        agvMessage1.setPackAckSt(99);
        agvMessage1.setPackType(258);
        ClientHandler.setPackNr(agvMessage1.getPackNr());
        return agvMessage1;
    }

    @Override
    public void acceptAck(AgvMessage agvMessage) {
    }
}
