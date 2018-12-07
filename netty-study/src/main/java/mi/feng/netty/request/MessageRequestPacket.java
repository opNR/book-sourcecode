package mi.feng.netty.request;

import mi.feng.netty.protocol.command.Packet;

import static mi.feng.netty.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/7 14:15
 * @Description:
 */
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
