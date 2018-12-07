package mi.feng.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import mi.feng.netty.protocol.Packet;
import mi.feng.netty.protocol.PacketCodeC;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/7 16:59
 * @Description:
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
