package com.xdcplus.xdcweb.basics.service.impl;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by haoxy on 2018/10/17.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Component
public class HeartbeatServer {

    public static TcpServer tcpServer;

    @PostConstruct
    public void start() throws InterruptedException {
        tcpServer = new TcpServer(8080);

        System.out.println("==========Start Server First==========");
        tcpServer.init();
    }
}
