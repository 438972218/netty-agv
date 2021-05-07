package com.xdcplus.xdcweb.basics.handler;

import cn.hutool.extra.spring.SpringUtil;
import com.xdcplus.xdcweb.basics.common.config.NettyTcpConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : Fish Fei
 */
@Component
public class HeartbeatClient {

//    @Value("${netty.server.port}")
//    private int nettyPort;
//    @Value("${netty.server.host}")
//    private String host;
//
////    public static TcpClient client;
//    private static TcpClient client;
//    private static NettyTcpConfig nettyTcpConfig = SpringUtil.getBean(NettyTcpConfig.class);
//
//    @PostConstruct
//    public void start() throws InterruptedException {
//        client = new TcpClient(host, nettyPort);
//        System.out.println("==========Start Server First==========");
//        client.init();
//    }
}
