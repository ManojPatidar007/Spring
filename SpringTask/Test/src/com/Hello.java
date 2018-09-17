package com;

import java.awt.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Hello {
	public static void main(String ar[]){
		int b[]={5,20,2,10};
		int c=b.length; 
		int temp=0;
		LinkedList list=new LinkedList();
		list.add(20);
		list.add(10);
		list.add(5);
		list.sort(null);
		for(Object a:list){
			System.out.println("list"+a);}
		for(int i=0; i<=c;i++){
			for(int j=1;j<(c-i);j++){

				if(b[j-1]>b[j]){


					temp=b[j-1];
					b[j-1]=b[j];
					b[j]=temp;

				}

			}


		}
		for(int i=0;i<c;i++)
			System.out.println("  "+b[i]);
	}

}


