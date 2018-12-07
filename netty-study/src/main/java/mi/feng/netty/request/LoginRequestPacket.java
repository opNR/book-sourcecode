package mi.feng.netty.request;

import mi.feng.netty.protocol.command.Packet;

import static mi.feng.netty.protocol.command.Command.LOGIN_REQUEST;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/5 17:11
 * @Description:
 */
public class LoginRequestPacket extends Packet {

    private String userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
