package mi.feng.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import mi.feng.netty.protocol.request.MessageRequestPacket;
import mi.feng.netty.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/7 16:42
 * @Description:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket requestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端消息：" + requestPacket.getMessage());

        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setMessage("服务端回复[" + requestPacket.getMessage() +"]");
        ctx.channel().writeAndFlush(requestPacket);
    }
}
