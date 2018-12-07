package mi.feng.netty.server;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import mi.feng.netty.protocol.command.Packet;
import mi.feng.netty.protocol.command.PacketCodeC;
import mi.feng.netty.request.LoginRequestPacket;
import mi.feng.netty.response.LoginResponsePacket;

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
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            if (valid(loginRequestPacket)) {
                //校验成功
                loginResponsePacket.setSuccess(true);
            } else {
                //校验失败
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号或密码失败");
            }

            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        return false;
    }
}
