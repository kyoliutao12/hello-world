package com.liutao.bitmap;

public class BitMap {

	/**
	 * bitsMap中保存数量的个数，例如，可以保存100个数字，则是0-99
	 */
	private long MAX;
	private long MIN;
	public static int[] bitsMap;
	
	public BitMap(long numbersMax, long numbersMin){
		this.MAX = numbersMax;
		this.MIN = numbersMin;
		//根据需要保存的数字个数，计算所需数组的大小
		bitsMap = new int[(int)(MAX >> 5) + ((MAX & 31) > 0 ? 1 : 0)];
	}
	
	public void insert(long number){
		if(number <0 || number > MAX){
			throw new IllegalArgumentException("index value illegal!");
		}
		//求出该值要设置进bitsMap数组的下标
		int belowIndex = (int)(number >> 5);
		//求出该值的偏移量
		int offset = (int)(number & 31);
		int inData = bitsMap[belowIndex];
		bitsMap[belowIndex] = inData | (1 << offset);
	}
	
	public void delete(long number){
		if(number <0 || number > MAX){
			throw new IllegalArgumentException("index value illegal!");
		}
		//求出该值要设置进bitsMap数组的下标
		int belowIndex = (int)(number >> 5);
		//求出该值的偏移量
		int offset = (int)(number & 31);
		//先找到number在位图中的位置，然后求反，再跟原来的值求并
		bitsMap[belowIndex] = bitsMap[belowIndex] & (~(1 << offset));
	}
	
	public int search(long number){
		if(number <0 || number > MAX){
			throw new IllegalArgumentException("index value illegal!");
		}
		//求出该值要设置进bitsMap数组的下标
		int belowIndex = (int)(number >> 5);
		//求出该值的偏移量
		int offset = (int)(number & 31);
		return bitsMap[belowIndex] &= (1 << offset);
	}
	
	public static void main(String[] args) {
		BitMap map = new BitMap(100000000l, 0);
		for(int i=0; i<100000000; i++){
			map.insert(i);	
		}
		
		System.out.println(map.search(32384724));
	}
}
