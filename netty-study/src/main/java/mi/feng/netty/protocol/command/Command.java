package mi.feng.netty.protocol.command;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/5 17:10
 * @Description:
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;

}
