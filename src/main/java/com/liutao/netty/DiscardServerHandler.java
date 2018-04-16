package com.liutao.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelHandlerAdapter{
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        try {
            ByteBuf in = (ByteBuf) msg;
            // ��ӡ�ͻ������룬��������ĵ��ַ�
            System.out.print(in.toString(CharsetUtil.UTF_8));
        } finally {
            /**
             * ByteBuf��һ�����ü�������������������ʾ�ص���release()�������ͷš�
             * ���ס��������ְ�����ͷ����д��ݵ������������ü�������
             */
            // �����յ�������
            ReferenceCountUtil.release(msg);
        }
        
    }
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /**
         * exceptionCaught() �¼��������ǵ����� Throwable ����Żᱻ���ã����� Netty ���� IO
         * ������ߴ������ڴ����¼�ʱ�׳����쳣ʱ���ڴ󲿷�����£�������쳣Ӧ�ñ���¼���� ���Ұѹ����� channel
         * ���رյ���Ȼ����������Ĵ���ʽ����������ͬ�쳣��������в� ͬ��ʵ�֣�������������ڹر�����֮ǰ����һ�����������Ӧ��Ϣ��
         */
        // �����쳣�͹ر�
        cause.printStackTrace();
        ctx.close();
    }
}
