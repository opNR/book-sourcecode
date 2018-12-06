package mi.feng.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/3 16:01
 * @Description:
 */
public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        //线程组：监听端口、accept新连接
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        //线程组：处理一条连接的数据读写
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");

        serverBootstrap
                // 1. 指定线程模型
                .group(boosGroup, workerGroup)
                // 2. 指定IO模型为 NIO
                .channel(NioServerSocketChannel.class)

                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                .childAttr(clientKey, "clientValue")

                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 3. 处理新连接数据的读写处理逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });

        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port){
        //绑定端口：异步方法
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功");
            }else {
                System.err.println("端口[" + port + "]绑定失败");
                bind(serverBootstrap, port+1);
            }
        });
    }
}
