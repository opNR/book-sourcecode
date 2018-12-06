package mi.feng.netty.serialize;

import mi.feng.netty.serialize.impl.JSONSerializer;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/6 10:44
 * @Description:
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转成二进制
     * @param obj
     * @return
     */
    byte[] serialize(Object obj);

    /**
     * 二进制转为 java 对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
