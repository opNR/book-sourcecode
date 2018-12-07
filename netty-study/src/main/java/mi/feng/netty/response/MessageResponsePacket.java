package mi.feng.netty.response;

import mi.feng.netty.protocol.command.Packet;

import static mi.feng.netty.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/7 14:17
 * @Description:
 */
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
