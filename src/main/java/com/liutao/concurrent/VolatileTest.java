package com.liutao.concurrent;

public class VolatileTest {
	public static volatile int race = 0;
	
	
	public static void increase(String obj) {
		synchronized(obj) {
		race++;
		}
	}
	private static final int THREADS_COUNT = 20;
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[THREADS_COUNT];
		final String s = new String("aaaa");
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				
				public void run() {
					for (int j = 0; j < 10000; j++) {
						increase(s);
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
