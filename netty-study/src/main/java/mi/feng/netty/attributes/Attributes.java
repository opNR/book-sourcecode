package mi.feng.netty.attributes;

import io.netty.util.AttributeKey;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/7 14:22
 * @Description:
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
