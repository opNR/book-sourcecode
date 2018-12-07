package mi.feng.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/5 10:49
 * @Description:
 */
public class TestByteBuf {

    public static void main(String[] args) {

        //创建一个16字节的buffer,这里默认是创建heap buffer
        ByteBuf buf = Unpooled.buffer(16);
        System.out.println("capacity=" + buf.capacity());
        System.out.println("maxCapacity=" + buf.maxCapacity());

        //写数据
        for (int i=0; i<16; i++) {
            buf.writeByte(i+1);
        }

        //读书节
        for (int i = 0; i < buf.capacity(); i++) {
            System.out.print(buf.getByte(i) + ",");
        }
        System.out.println();

        System.out.println("writerIndex=" + buf.writerIndex());
        System.out.println("readerIndex=" + buf.readerIndex());

        System.out.println("=============================================");

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(9,100);

        //9,100, 0,0,false, 0,9,true,100
        print("allocate ByteBuf(9,100)", byteBuf);

        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        //9,100, 0,4,true, 4,5,true,96
        print("writeBytes(1,2,3,4)", byteBuf);

        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写, 写完 int 类型之后，写指针增加4
        byteBuf.writeInt(12);
        //9,100, 0,8,true, 8,1,true,92
        print("writeInt(12)", byteBuf);

        // write 方法改变写指针, 写完之后写指针等于 capacity 的时候，buffer 不可写
        byteBuf.writeBytes(new byte[]{5});
        //9,100, 0,9,true, 9,0,false,91
        print("writeBytes(5)", byteBuf);

        // write 方法改变写指针，写的时候发现 buffer 不可写则开始扩容，扩容之后 capacity 随即改变 (随机?)
        byteBuf.writeBytes(new byte[]{6});
        //X,100, 0,10,true, 10,X,true,90
        print("writeBytes(6)", byteBuf);

        // get 方法不改变读写指针
        System.out.println("getByte(3) return: " + byteBuf.getByte(3));
        System.out.println("getShort(3) return: " + byteBuf.getShort(3));
        System.out.println("getInt(3) return: " + byteBuf.getInt(3));
        print("getByte()", byteBuf);

        // set 方法不改变读写指针
        byteBuf.setByte(byteBuf.readableBytes() + 1, 0);
        print("setByte()", byteBuf);

        // read 方法改变读指针
        byte[] dst = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(dst);
        print("readBytes(" + dst.length + ")", byteBuf);
    }

    private static void print(String action, ByteBuf buffer){
        System.out.println("after---------------------" + action + "--------------------");
        System.out.println("capacity=" + buffer.capacity());
        System.out.println("maxCapacity=" + buffer.maxCapacity());

        System.out.println("readerIndex=" + buffer.readerIndex());
        System.out.println("readableBytes=" + buffer.readableBytes());
        System.out.println("isReadable=" + buffer.isReadable());

        System.out.println("writerIndex=" + buffer.writerIndex());
        System.out.println("writableBytes=" + buffer.writableBytes());
        System.out.println("isWritable=" + buffer.isWritable());
        System.out.println("maxCapacity=" + buffer.maxWritableBytes());
    }
}
