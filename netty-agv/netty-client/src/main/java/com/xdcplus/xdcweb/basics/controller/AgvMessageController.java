package com.xdcplus.xdcweb.basics.controller;


import com.xdcplus.netty.common.model.*;
import com.xdcplus.netty.common.tool.Converter;
import com.xdcplus.xdcweb.XdcWebApplicationRunner;
import com.xdcplus.xdcweb.basics.common.utils.TcpClientUtils;
import com.xdcplus.xdcweb.basics.handler.ClientHandler;
import com.xdcplus.xdcweb.basics.handler.TcpClient;
import com.xdcplus.xdcweb.basics.service.IAgvMessageService;
import com.xdcplus.xdcweb.basics.handler.HeartbeatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fish
 * @since 2021-04-30
 */
@RestController
@RequestMapping("/agv-message")
public class AgvMessageController {
    @Autowired
    IAgvMessageService agvMessageService;

    @GetMapping("/send")
    public void send() throws UnsupportedEncodingException {
        AgvMessage agvMessage = new AgvMessage();
        ClientHandler.getPackNr();

        agvMessage.setPackNr(2);
        //任务交互
        agvMessage.setPackType(259);
        agvMessage.setType("client");

        AgvInteraction agvInteraction = new AgvInteraction();
        agvInteraction.setTaskNum(100);
        Task task = new Task();
        task.setAgvId(1);
        task.setActionType(10);
        StationInfo stationInfo = new StationInfo();
        stationInfo.setStationAsyn(1);
        stationInfo.setStationIndex(1);
        stationInfo.setStationId(10);
        StationTaskInfo stationTaskInfo = new StationTaskInfo();
        stationTaskInfo.setActionIndex("1");
        stationTaskInfo.setActionAxis(1);
        stationTaskInfo.setTaskType(1);
        stationTaskInfo.setIfType(3);
        List<StationTaskInfo> stationTaskInfos = Arrays.asList(stationTaskInfo);
        stationInfo.setStationTaskInfo(stationTaskInfos);
        List<StationInfo> stationInfos = Arrays.asList(stationInfo);
        task.setStationInfo(stationInfos);
        agvInteraction.setTask(task);


        String json = Converter.agvInteractionToJson(agvInteraction);

        agvMessage.setData(json);

        byte[] a = Converter.compileObject(agvMessage);
        agvMessage.setBytes(a);
        agvMessageService.save(agvMessage);

        TcpClient.send(agvMessage);

    }

    @PostMapping("/list")
    public List<AgvMessage> list() {

        List<AgvMessage> agvMessages = agvMessageService.list();

        return agvMessages;
    }

}
