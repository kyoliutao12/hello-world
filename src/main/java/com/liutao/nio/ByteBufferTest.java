package com.liutao.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO，理解为面向缓冲区的读、写操作
 * @author missbaby
 *
 */
public class ByteBufferTest {
	public static void main(String[] args) {
		ByteBuffer buffer;
		FileInputStream fin = null;
		FileOutputStream fout = null;
		try {
			fin = new FileInputStream("/Users/missbaby/eclipse-workspace/testNIO.txt");
			FileChannel fchannel = fin.getChannel();
			buffer = ByteBuffer.allocate(1024);
			fout = new FileOutputStream("/Users/missbaby/eclipse-workspace/testNIO-back.txt");
			FileChannel outChannel = fout.getChannel();
			int readInt = -1;
			//Buffer一开始建立时，是clear过的，position=0，limit=cap
			System.out.println("当前位置是："+buffer.position()+", 限制位是："+buffer.limit());
			//循环从通道里往Buffer里写内容，如果read()返回 == -1，则说明通道里没有内容了
			while((readInt = fchannel.read(buffer)) != -1) {
				//写完Buffer之后，判断一下缓冲区当前的位置、限制
				System.out.println("当前位置是："+buffer.position()+", 限制位是："+buffer.limit());
				//读取缓冲区的内容，先把缓冲区转换为读模式
				buffer.flip();
				System.out.println("当前位置是："+buffer.position()+", 限制位是："+buffer.limit());
				//把缓冲区的内容读出来，转换为String，并且打印
				
				String readOne = new String(buffer.array());
				System.out.println("===================content start:===============");
				System.out.println(readOne);
				System.out.println("===================content end:===============");
				System.out.println("当前位置是："+buffer.position()+", 限制位是："+buffer.limit());
//				buffer.flip();
				System.out.println("当前位置是："+buffer.position()+", 限制位是："+buffer.limit());
				//从缓冲区中读取内容写入输出通道
				int outInt = 0;
				while((outInt = outChannel.write(buffer)) != 0) {
					System.out.println("读取了："+readInt+", 写入了："+outInt);
				}
				//准备重新读，将当前位置设为0，限制位设为容量
				buffer.clear();
				System.out.println("当前位置是："+buffer.position()+", 限制位是："+buffer.limit());
				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
