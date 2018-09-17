package com.mp;

public class Client {
	private boolean visiable;
	//{@com.mp.Test}
	//com.mp.Test
	public boolean isVisiable() {
		return visiable;
	}
	public void setVisiable(boolean visiable) {
		this.visiable = visiable;
	}
	public Client(){

		System.out.println("Hello is calling");
	}
}
