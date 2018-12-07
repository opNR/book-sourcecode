package mi.feng.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import mi.feng.netty.protocol.Packet;
import mi.feng.netty.protocol.PacketCodeC;
import mi.feng.netty.protocol.request.LoginRequestPacket;
import mi.feng.netty.serialize.Serializer;
import mi.feng.netty.serialize.impl.JSONSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/7 10:35
 * @Description:
 */
public class TestPacketCodeC {

    public static void main(String[] args) {

    }

    @Test
    public void encode() {
        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("userName");
        loginRequestPacket.setPassword("password");

        PacketCodeC packetCodeC = PacketCodeC.INSTANCE;
        ByteBuf byteBuf = packetCodeC.encode(ByteBufAllocator.DEFAULT.buffer(), loginRequestPacket);
        Packet decodedPacket = packetCodeC.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));

    }
}
