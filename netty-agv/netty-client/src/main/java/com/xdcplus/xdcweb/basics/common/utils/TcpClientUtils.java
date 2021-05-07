package com.xdcplus.xdcweb.basics.common.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.xdcplus.netty.common.model.AgvMessage;
import com.xdcplus.xdcweb.basics.common.config.NettyTcpConfig;
import com.xdcplus.xdcweb.basics.handler.TcpClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Fish Fei
 */
@Slf4j
public class TcpClientUtils {

//    private static TcpClient client;
//    private static NettyTcpConfig nettyTcpConfig = SpringUtil.getBean(NettyTcpConfig.class);
//
//    private TcpClientUtils(){}
//
//
//
//    public static void init() {
//
//        if (ObjectUtil.isNull(client)) {
//            synchronized (TcpClientUtils.class) {
//                if (ObjectUtil.isNull(client)) {
//                    client = new TcpClient(nettyTcpConfig.getHost(), nettyTcpConfig.getPort());
//                }
//            }
//        }
//
//
//        client.init();
//
//    }
//
//    public static boolean send(AgvMessage agvMessage) {
//
//        try {
//
////            init();
//
//
//            TcpClient.send(agvMessage);
//            return Boolean.TRUE;
//        }catch (Exception e) {
//            log.error("send {}", e.getMessage());
//        }
//
//        return Boolean.FALSE;
//    }




























}
