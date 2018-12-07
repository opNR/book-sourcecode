package mi.feng.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import mi.feng.netty.protocol.request.LoginRequestPacket;
import mi.feng.netty.protocol.response.LoginResponsePacket;
import mi.feng.netty.utils.LoginUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/7 16:48
 * @Description:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //创建登陆对象
        LoginRequestPacket requestPacket = new LoginRequestPacket();
        requestPacket.setUserId(UUID.randomUUID().toString());
        requestPacket.setUserName("MiFeng");
        requestPacket.setPassword("123456");

        ctx.channel().writeAndFlush(requestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            System.out.println(new Date() + "：客户端登陆成功");

            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + "：客户端登陆失败,原因：" + responsePacket.getReason());
        }
    }
}
