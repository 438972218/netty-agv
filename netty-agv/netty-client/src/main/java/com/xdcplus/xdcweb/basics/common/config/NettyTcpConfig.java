package com.xdcplus.xdcweb.basics.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : Fish Fei
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty.server")
public class NettyTcpConfig {

    private Integer port;
    private String host;

}
