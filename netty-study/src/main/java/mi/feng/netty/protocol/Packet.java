package mi.feng.netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/5 17:07
 * @Description:
 */
public abstract class Packet {

    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    @JSONField(serialize = false)
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
