package mi.feng.netty.protocol.response;

import mi.feng.netty.protocol.Packet;

import static mi.feng.netty.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/6 14:35
 * @Description:
 */
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
