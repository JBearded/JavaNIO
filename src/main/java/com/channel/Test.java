package com.channel;

public class Test {

	public synchronized void set(){
		System.out.println("set");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("set过去10秒");
	}
	
	public synchronized void get(){
		System.out.println("get");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("get过去10秒");
	}
}
