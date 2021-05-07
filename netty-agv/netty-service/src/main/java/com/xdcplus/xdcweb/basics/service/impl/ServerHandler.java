package com.xdcplus.xdcweb.basics.service.impl;

import com.xdcplus.netty.common.model.AgvMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<AgvMessage> {
    private volatile Integer heart =0;
    private volatile Integer packNr =0;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, AgvMessage msg) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        if ("OK".equals(msg)) {
            //客户端心跳返回忽略
        } else if(msg.getHeart()!=null) {
        	//客户端主动心跳
//            ctx.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        	ctx.channel().writeAndFlush(msg);
        } else {
        	//业务逻辑
        }
		
	}

//	@Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
//            throws Exception {
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent event = (IdleStateEvent) evt;
//            if (event.state().equals(IdleState.READER_IDLE)) {
//                System.out.println("Server READER_IDLE");
//                // 超过时间没有收到请求，服务器主动关闭channel
//                ctx.close();
//            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
//                System.out.println("Server WRITER_IDLE");
//                MegHeader megHeader=new MegHeader();
//                heart=heart+1;
//                if(heart==257){
//                    heart=1;
//                }
//                packNr=packNr+1;
//                if(packNr==60001){
//                    packNr=1;
//                }
//                megHeader.setHeart(heart);
//                megHeader.setPackNr(packNr);
//                megHeader.setPackType(257);
//
//                ctx.writeAndFlush(megHeader).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//            } else if (event.state().equals(IdleState.ALL_IDLE)) {
//                System.out.println("Server ALL_IDLE");
//                // 发送心跳，注意要使用writeAndFlush，使用write由于包太小，可能会不直接发送
////                ctx.channel().writeAndFlush("ping\n");
////                MegHeader megHeader=new MegHeader();
////                heart=heart+1;
////                if(heart==257){
////                    heart=1;
////                }
////                packNr=packNr+1;
////                if(packNr==60001){
////                    packNr=1;
////                }
////                megHeader.setHeart(heart);
////                megHeader.setPackNr(packNr);
////                megHeader.setPackType(257);
////
////                ctx.writeAndFlush(megHeader).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//            }
//        }
//        super.userEventTriggered(ctx, evt);
//    }
	

}
