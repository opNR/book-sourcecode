package mi.feng.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import mi.feng.netty.serialize.SerializeAlogrithm;
import mi.feng.netty.serialize.Serializer;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/6 10:52
 * @Description:
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializeAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
