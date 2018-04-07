package com.liutao.datastruct.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryNode {
	public String name;
	public BinaryNode left;
	public BinaryNode right;
	
	public static void main(String[] args) {
		String str = "ab+cde+**";
		List<BinaryNode> stack = new ArrayList();
		char[] chars = str.toCharArray();
		for (char c : chars) {
			BinaryNode node = new BinaryNode();
			node.name = String.valueOf(c);
			if(c=='+' || c=='*') {
				node.right = stack.get(stack.size()-1);
				stack.remove(stack.size()-1);
				node.left = stack.get(stack.size()-1);
				stack.remove(stack.size()-1);
			}
			stack.add(node);
		}
		System.out.println(stack);
		System.out.println(".....");
	}
}
