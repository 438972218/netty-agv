package com.xdcplus.xdcweb;

import com.alibaba.fastjson.JSON;
import com.xdcplus.netty.common.model.*;
import com.xdcplus.netty.common.tool.Converter;
import com.xdcplus.xdcweb.basics.common.utils.TcpClientUtils;
import com.xdcplus.xdcweb.basics.handler.ClientHandler;
import com.xdcplus.xdcweb.basics.handler.TcpClient;
import com.xdcplus.xdcweb.global.utils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class XdcWebApplicationTests {

    @Test
    void contextLoads() {
        AgvInteraction agvInteraction=new AgvInteraction();
        agvInteraction.setTaskNum(100);
        Task task=new Task();
        task.setAgvId(1);
        task.setActionType(10);
        StationInfo stationInfo=new StationInfo();
        stationInfo.setStationAsyn(1);
        stationInfo.setStationIndex(1);
        stationInfo.setStationId(10);
        StationTaskInfo stationTaskInfo=new StationTaskInfo();
        stationTaskInfo.setActionIndex("1");
        stationTaskInfo.setActionAxis(1);
        stationTaskInfo.setTaskType(1);
        stationTaskInfo.setIfType(3);
        List<StationTaskInfo> stationTaskInfos= Arrays.asList(stationTaskInfo);
        stationInfo.setStationTaskInfo(stationTaskInfos);
        List<StationInfo> stationInfos= Arrays.asList(stationInfo);
        task.setStationInfo(stationInfos);
        agvInteraction.setTask(task);
        String json = Converter.agvInteractionToJson(agvInteraction);
        ClientHandler.getPackNr();
        System.out.println(json);
    }



    @Test
    void contextLoads1() {
        String jsonStr="{\n" +
                "\"agvState\": [\n" +
                "{\n" +
                "\"agvId\": 106, \n" +
                "\"taskNum\": 212, \n" +
                "\"agvEnergy\": 80, \n" +
                "\"state\": 3, \n" +
                "\"curStationNum\": 3241, \n" +
                "\"mapId\":15,\n" +
                "\"x\": 2571, \n" +
                "\"y\": 3627, \n" +
                "\"axisState\": [\n" +
                "{\n" +
                "\"actionAxis\": 1, \n" +
                "\"materialCount\": 2, \n" +
                "\"materialType\": 1\n" +
                "}\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "\"agvId\": 102, \n" +
                "\"taskNum\": -1, \n" +
                "\"agvEnergy\": 40, \n" +
                "\"state\": 2, \n" +
                "\"curStationNum\": 3211, \n" +
                "\"mapId\":15,\n" +
                "\"x\": 26571, \n" +
                "\"y\": 12627, \n" +
                "\"axisState\": [\n" +
                "{\n" +
                "\"actionAxis\": 1, \n" +
                "\"materialCount\": 0, \n" +
                "\"materialType\": 1\n" +
                "}, \n" +
                "{\n" +
                "\"actionAxis\": 2, \n" +
                "\"materialCount\": 0, \n" +
                "\"materialType\": 2\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "], \n" +
                "\"alarmState\": [\n" +
                "{\n" +
                "\"alarmSource\": 1, \n" +
                "\"alarmLevel\": 2, \n" +
                "\"alarmCode\": 061, \n" +
                "\"alarmDetail\": \"xxx\",\n" +
                "\"startTime\": \"xxxx:xx:xx:xx:xx:xx\", \n" +
                "\"duration\": 1000\n" +
                "}, \n" +
                "{\n" +
                "\"alarmSource\": 1, \n" +
                "\"alarmLevel\": 1, \n" +
                "\"alarmCode\": 063, \n" +
                "\"alarmDetail\": \"xxx\",\n" +
                "\"startTime\": \"xxxx:xx:xx:xx:xx:xx\", \n" +
                "\"duration\": 2000\n" +
                "}, \n" +
                "]\n" +
                "}\n";
        AgvPad agvPad = JSON.parseObject(jsonStr , AgvPad.class);
        System.out.println(agvPad);
    }


    @Test
    public void test1() {

        TcpClient.send(new AgvMessage());

    }






}
