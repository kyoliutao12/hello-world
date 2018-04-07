package com.liutao.bitmap;

public class BitMap {

	/**
	 * bitsMap�б��������ĸ��������磬���Ա���100�����֣�����0-99
	 */
	private long MAX;
	private long MIN;
	public static int[] bitsMap;
	
	public BitMap(long numbersMax, long numbersMin){
		this.MAX = numbersMax;
		this.MIN = numbersMin;
		//������Ҫ��������ָ�����������������Ĵ�С
		bitsMap = new int[(int)(MAX >> 5) + ((MAX & 31) > 0 ? 1 : 0)];
	}
	
	public void insert(long number){
		if(number <0 || number > MAX){
			throw new IllegalArgumentException("index value illegal!");
		}
		//�����ֵҪ���ý�bitsMap������±�
		int belowIndex = (int)(number >> 5);
		//�����ֵ��ƫ����
		int offset = (int)(number & 31);
		int inData = bitsMap[belowIndex];
		bitsMap[belowIndex] = inData | (1 << offset);
	}
	
	public void delete(long number){
		if(number <0 || number > MAX){
			throw new IllegalArgumentException("index value illegal!");
		}
		//�����ֵҪ���ý�bitsMap������±�
		int belowIndex = (int)(number >> 5);
		//�����ֵ��ƫ����
		int offset = (int)(number & 31);
		//���ҵ�number��λͼ�е�λ�ã�Ȼ���󷴣��ٸ�ԭ����ֵ��
		bitsMap[belowIndex] = bitsMap[belowIndex] & (~(1 << offset));
	}
	
	public int search(long number){
		if(number <0 || number > MAX){
			throw new IllegalArgumentException("index value illegal!");
		}
		//�����ֵҪ���ý�bitsMap������±�
		int belowIndex = (int)(number >> 5);
		//�����ֵ��ƫ����
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
