package com.xdcplus.xdcweb.basics.handler;

import com.alibaba.fastjson.JSON;
import com.xdcplus.netty.common.model.AgvMessage;
import com.xdcplus.netty.common.model.AgvPad;
import com.xdcplus.netty.common.model.AgvState;
import com.xdcplus.netty.common.model.AxisState;
import com.xdcplus.xdcweb.basics.mapper.AgvStateMapper;
import com.xdcplus.xdcweb.basics.service.AckService;
import com.xdcplus.xdcweb.basics.service.IAgvMessageService;
import com.xdcplus.xdcweb.basics.service.IAgvStateService;
import com.xdcplus.xdcweb.basics.service.IAxisStateService;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @author : Fish Fei
 */
public class ClientHandler extends SimpleChannelInboundHandler<AgvMessage> {

    private static volatile Integer heart =0;

    private static volatile Integer packNr =0;

    @Autowired
    IAgvMessageService agvMessageService;

    @Autowired
    AckService ackService;

    @Autowired
    IAgvStateService agvStateService;

    @Autowired
    IAxisStateService axisStateService;

    @Autowired
    AgvStateMapper agvStateMapper;

    public static Integer getPackNr(){
        return packNr;
    }

    public static void setPackNr(Integer packNr){
        ClientHandler.packNr =packNr;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                System.out.println("Client READER_IDLE");
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                System.out.println("Client WRITER_IDLE");
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                System.out.println("Client ALL_IDLE");
                // 发送心跳，注意要使用writeAndFlush，使用write由于包太小，可能会不直接发送
                //向服务端发送消息
                if(TcpClient.restrict){
                    AgvMessage agvMessage = combineAgvMessage();

                    ctx.writeAndFlush(agvMessage).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                }
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("hell world \n");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AgvMessage agvMessage) throws Exception {
        System.out.println("Server say : " + agvMessage);
        //接收到数据
        //判断报文类型
        if(agvMessage.getPackType()==258){
            agvMessage.setType("server");
            agvMessageService.save(agvMessage);
            //ack确认包
        }else if(agvMessage.getPackType()==260){
            agvMessage.setType("server");
            agvMessageService.save(agvMessage);
            //状态报文
            String data = agvMessage.getData();
            AgvPad agvPad = JSON.parseObject(data , AgvPad.class);
            Optional.ofNullable(agvPad).ifPresent(agvPad1 -> {
                List<AgvState> agvStates = agvPad1.getAgvStates();
                for(AgvState agvState:agvStates){
                    Integer id = agvStateMapper.insertReturnId(agvState);
                    if(agvState.getAxisStates()!=null){
                        for(AxisState axisState:agvState.getAxisStates()){
                            axisState.setAgvStateId(id);
                            axisStateService.save(axisState);
                        }
                    }
                }
            });
        }
    }

    /**
     * 心跳数据
     * @return AgvMessage
     */
    public AgvMessage combineAgvMessage(){
        AgvMessage agvMessage=new AgvMessage();
        heart=heart+1;
        if(heart==257){
            heart=1;
        }
        packNr=packNr+1;
        if(packNr==60001){
            packNr=1;
        }
        agvMessage.setHeart(heart);
        agvMessage.setPackNr(packNr);
        agvMessage.setPackType(257);
        return agvMessage;
    }

}
