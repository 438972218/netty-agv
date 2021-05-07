package com.xdcplus.xdcweb.basics.handler;

import cn.hutool.extra.spring.SpringUtil;
import com.xdcplus.netty.common.encode.HeartbeatDecoder;
import com.xdcplus.netty.common.encode.HeartbeatEncoder;
import com.xdcplus.netty.common.model.AgvMessage;
import com.xdcplus.xdcweb.basics.common.config.NettyTcpConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Slf4j
@Component
//@DependsOn("nettyTcpConfig")
public class TcpClient {

    private static EventLoopGroup workerGroup;

    private static Bootstrap bootstrap;

    private volatile boolean closed = false;

    private static Channel ch;

    public volatile static boolean restrict = true;

    @Autowired
    NettyTcpConfig nettyTcpConfig;

    private TcpClient() {

    }

    public void close() {
        closed = true;
        workerGroup.shutdownGracefully();
        System.out.println("Stopped Tcp Client: " + getServerInfo());
    }

    /**
     * 心跳
     */
    @PostConstruct
    public void init() {
        closed = false;

        workerGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addFirst(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        super.channelActive(ctx);
                        log.info("客户端Active .....");
                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        super.exceptionCaught(ctx, cause);
                        log.info("client exception");
                    }

                    @Override
                    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                        super.channelInactive(ctx);
                        ctx.channel().eventLoop().schedule(() -> doConnect(), 1, TimeUnit.SECONDS);
                    }
                });

                pipeline.addLast("ping", new IdleStateHandler(60, 15, 6, TimeUnit.SECONDS));
                pipeline.addLast("encoder", new HeartbeatEncoder());
                pipeline.addLast("decoder", new HeartbeatDecoder());
                // 客户端的逻辑
                pipeline.addLast("handler", new ClientHandler());
            }
        });

        doConnect();
    }

    /**
     * 连接
     */
    private Channel doConnect() {
        if (closed) {
            return null;
        }

        ChannelFuture future = bootstrap.connect(new InetSocketAddress(nettyTcpConfig.getHost(), nettyTcpConfig.getPort()));
        System.out.println(Thread.currentThread().getName() + " Client start connectting Tcp Client : " + getServerInfo());

        future.addListener((ChannelFutureListener) f -> {
            if (f.isSuccess()) {
                ch = f.channel();
                System.out.println(Thread.currentThread().getName() + " Client connect Tcp Client Success: " + getServerInfo());
            } else {
                System.out.println(Thread.currentThread().getName() + " Client connect Failed: " + getServerInfo());
                f.channel().eventLoop().schedule(() -> doConnect(), 1, TimeUnit.SECONDS);
            }
        });
        return ch;
    }

    private String getServerInfo() {
        return String.format("RemoteHost=%s RemotePort=%d",
                nettyTcpConfig.getHost(), nettyTcpConfig.getPort());
    }


    /**
     * 手动发送数据
     *
     * @param msg
     */
    public static void send(AgvMessage msg) {
        if (ch != null && ch.isActive()) {
            restrict = false;
            ch.writeAndFlush(msg);
            restrict = true;
        }
    }
}
