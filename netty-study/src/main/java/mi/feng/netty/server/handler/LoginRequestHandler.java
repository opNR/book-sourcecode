package mi.feng.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import mi.feng.netty.protocol.request.LoginRequestPacket;
import mi.feng.netty.protocol.response.LoginResponsePacket;

import java.util.Date;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/7 16:33
 * @Description:
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket requestPacket) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录……");

        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(requestPacket.getVersion());

        if (valid(requestPacket)) {
            //校验成功
            responsePacket.setSuccess(true);

            System.out.println(new Date() + ": 登录成功!");
        } else {
            //校验失败
            responsePacket.setSuccess(false);

            responsePacket.setReason("账号或密码失败");
        }

        ctx.channel().writeAndFlush(responsePacket);
    }

    private boolean valid(LoginRequestPacket requestPacket){
        return true;
    }
}
