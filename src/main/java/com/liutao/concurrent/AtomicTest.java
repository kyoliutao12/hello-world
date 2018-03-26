package com.liutao.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
	public static AtomicInteger race = new AtomicInteger(0);
	static Object obj;
	public static void increase() {
		race.incrementAndGet();
	}
	private static final int THREADS_COUNT = 20;
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				
				public void run() {
					for (int j = 0; j < 10000; j++) {
						increase();
					}
				}
			});
			threads[i].start();
		}
		
		while(Thread.activeCount() > 1) {
			Thread.yield();
		}
		
		System.out.println(race);
	}
}
