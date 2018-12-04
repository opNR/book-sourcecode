package mi.feng.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import mi.feng.netty.FirstClientHandler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/4 15:36
 * @Description:
 */
public class NettyClient {

    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1. 指定线程模型
                .group(workGroup)
                // 2. 指定IO模型为 NIO
                .channel(NioSocketChannel.class)
                // 绑定自定义属性到 channel
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                // 设置TCP底层属性
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                // 3. 连接读写处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });

        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);

    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功！");
            } else if (retry == 0) {
                System.out.println("重试次数已用完，放弃连接！");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 >> order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连...");

                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry-1), delay, TimeUnit.SECONDS);
            }
        });
    }
}
