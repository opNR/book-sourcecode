package mi.feng.netty.client;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import mi.feng.netty.request.LoginRequestPacket;
import mi.feng.netty.protocol.command.Packet;
import mi.feng.netty.protocol.command.PacketCodeC;
import mi.feng.netty.response.LoginResponsePacket;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/6 14:22
 * @Description:
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "：客户端开始登陆");
        //创建登陆对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("MiFeng");
        loginRequestPacket.setPassword("123456");

        //编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        //写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + "：客户端登陆成功");
            } else {
                System.out.println(new Date() + "：客户端登陆失败,原因：" + loginResponsePacket.getReason());
            }
        }
    }
}
