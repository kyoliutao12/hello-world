package com.liutao.datastruct.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	public String name;
	public int size;
	public boolean isFile;
	public TreeNode firstChild;
	public TreeNode nextBro;
	public List<TreeNode> listChilds(){
		List<TreeNode> list = new ArrayList();
		TreeNode firstChild = this.firstChild;
		if(firstChild != null) {
			list.add(firstChild);
			while(true) {
				firstChild = firstChild.nextBro;
				if(firstChild != null) {
					list.add(firstChild);
				}else {
					break;
				}
			}
		}else{
			
		}
		return list;
	}
	
	public static void main(String[] args) {
		TreeNode usr = new TreeNode();
		usr.name="/usr";
		usr.size=1;
		
			TreeNode mark = new TreeNode();
			mark.name="mark";
			mark.size=1;
			usr.firstChild=mark;
				TreeNode book = new TreeNode();
				book.name="book";
				book.size=1;
				mark.firstChild=book;
				
					TreeNode ch1 = new TreeNode();
					ch1.name="ch1";
					ch1.size=3;
					ch1.isFile=true;
					book.firstChild=ch1;
					
					TreeNode ch2 = new TreeNode();
					ch2.name="ch2";
					ch2.size=4;
					ch2.isFile=true;
					ch1.nextBro=ch2;
					
					TreeNode ch3 = new TreeNode();
					ch3.name="ch3";
					ch3.size=5;
					ch3.isFile=true;
					ch2.nextBro=ch3;
			
			TreeNode alex = new TreeNode();
			alex.name="alex";
			alex.size=1;
			mark.nextBro=alex;
			
			TreeNode bill = new TreeNode();
			bill.name="bill";
			bill.size=1;
			alex.nextBro=bill;

		
		printAllSize(usr);
			
	}
	
	static int printAllSize(TreeNode node) {
		int size = node.size;
		if(node.isFile==false) {
			List<TreeNode> childs = node.listChilds();
			for (TreeNode nodes : childs) {
				size = size + printAllSize(nodes);
			}
			System.out.println(node.name+"_"+size);
			return size;
		}else {
			System.out.println(node.name+"_"+node.size);
			return node.size;
		}
	}
}
