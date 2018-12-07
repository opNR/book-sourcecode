package mi.feng.netty.utils;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import mi.feng.netty.attributes.Attributes;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/7 14:24
 * @Description:
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
