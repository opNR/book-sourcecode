package mi.feng.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import mi.feng.netty.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/7 16:54
 * @Description:
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket responsePacket) throws Exception {
        System.out.println(new Date() + ": 收到服务端消息：" + responsePacket.getMessage());
    }
}
