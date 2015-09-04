package com.channel;

public class TestMain {

	public static Test test = new Test();
	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				test.set();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				test.get();
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("end");
		return;
		
	}
}
