package mi.feng.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import mi.feng.netty.protocol.Packet;
import mi.feng.netty.protocol.PacketCodeC;
import mi.feng.netty.protocol.request.LoginRequestPacket;
import mi.feng.netty.protocol.request.MessageRequestPacket;
import mi.feng.netty.protocol.response.LoginResponsePacket;
import mi.feng.netty.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/7 10:13
 * @Description:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录……");

        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket) {
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            if (valid(loginRequestPacket)) {
                //校验成功
                loginResponsePacket.setSuccess(true);
            } else {
                //校验失败
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号或密码失败");
            }

            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc().buffer(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);

        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + ": 收到客户端消息：" + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复[" + messageRequestPacket.getMessage() +"]");
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc().buffer(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }
}
