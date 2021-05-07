package com.xdcplus.netty.common.encode;

import com.xdcplus.netty.common.model.AgvMessage;
import com.xdcplus.netty.common.tool.Converter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by haoxy on 2018/10/17.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 * 客户端编码器
 */
public class HeartbeatEncoder extends MessageToByteEncoder<AgvMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, AgvMessage megHeader, ByteBuf byteBuf) throws Exception {
        byte[] a = Converter.compileObject(megHeader);
        ByteBuf b= Unpooled.copiedBuffer(a);
//        System.out.println(Converter.bytesToHexString(a));
        byteBuf.writeBytes(b);
    }
}
