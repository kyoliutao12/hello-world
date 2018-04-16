package com.liutao.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {
	private int port;
	public DiscardServer(int port) {
        super();
        this.port = port;
    }
	
	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("׼�����ж˿ڣ�" + port);
        
        try {
            /**
             * ServerBootstrap ��һ������NIO����ĸ��������� ����������������ֱ��ʹ��Channel
             */
            ServerBootstrap b = new ServerBootstrap();
            /**
             * ��һ���Ǳ���ģ����û������group���ᱨjava.lang.IllegalStateException: group not
             * set�쳣
             */
            b = b.group(bossGroup, workerGroup);
            /***
             * ServerSocketChannel��NIO��selectorΪ��������ʵ�ֵģ����������µ�����
             * �������Channel��λ�ȡ�µ�����.
             */
            b = b.channel(NioServerSocketChannel.class);
            /***
             * ������¼������ྭ���ᱻ��������һ��������Ѿ����յ�Channel�� ChannelInitializer��һ������Ĵ����࣬
             * ����Ŀ���ǰ���ʹ��������һ���µ�Channel��
             * Ҳ������ͨ������һЩ���������NettyServerHandler������һ���µ�Channel
             * �������Ӧ��ChannelPipeline��ʵ������������ ����ĳ����ĸ���ʱ������������Ӹ���Ĵ����ൽpipline�ϣ�
             * Ȼ����ȡ��Щ�����ൽ�������ϡ�
             */
            b = b.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new DiscardServerHandler());// demo1.discard
                    // ch.pipeline().addLast(new
                    // ResponseServerHandler());//demo2.echo
                    // ch.pipeline().addLast(new
                    // TimeServerHandler());//demo3.time
                }
            });
            /***
             * �������������ָ����ͨ��ʵ�ֵ����ò����� ��������дһ��TCP/IP�ķ���ˣ�
             * ������Ǳ���������socket�Ĳ���ѡ�����tcpNoDelay��keepAlive��
             * ��ο�ChannelOption����ϸ��ChannelConfigʵ�ֵĽӿ��ĵ��Դ˿��Զ�ChannelOptions����һ����ŵ���ʶ��
             */
            b = b.option(ChannelOption.SO_BACKLOG, 128);
            /***
             * option()���ṩ��NioServerSocketChannel�������ս��������ӡ�
             * childOption()���ṩ���ɸ��ܵ�ServerChannel���յ������ӣ�
             * �����������Ҳ��NioServerSocketChannel��
             */
            b = b.childOption(ChannelOption.SO_KEEPALIVE, true);
            /***
             * �󶨶˿ڲ�����ȥ���ս���������
             */
            ChannelFuture f = b.bind(port).sync();
            /**
             * �����һֱ�ȴ���ֱ��socket���ر�
             */
            f.channel().closeFuture().sync();
        } finally {
            /***
             * �ر�
             */
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
	}
	
	public static void main(String[] args) throws Exception {
		int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
        System.out.println("server:run()");
	}
}
