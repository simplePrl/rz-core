package com.rz.core.practice.thread;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ThreadHelper {
	public static void main(String[] args) {
		ThreadHelper threadHelper = new ThreadHelper();

		//threadHelper.testLock();
		//threadHelper.testCountDownLatch();
		threadHelper.testSemaphore();
		
		System.out.println("bottom...");
	}

	// save it in main cache and thread cache
	private int notSafe;
	// save it in main cache only, so every thread can get same value. [safe =
	// 10] is atom, [safe = safe + 1] is not atom set
	private volatile int safe;
	// [threadSafe = threadSafe + 1] or [threadSafe++] is atom set
	private int threadSafe;

	private synchronized void setIncrease() {
		this.threadSafe++;
	}

	private Object lock = new Object();

	private void testLock() {
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				synchronized (this.lock) {
					try {
						System.out.println(Thread.currentThread().getName() + ": " + new Date().toString());
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, String.valueOf(i));
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void testCountDownLatch() {
		int count = 10;
		CountDownLatch countDownLatch = new CountDownLatch(count);
		Thread thread = new Thread(() -> {
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " done " + new Date().toString());
		}, "asd");
		thread.setDaemon(true);
		thread.start();

		for (int i = 0; i < count; i++) {
			System.out.println(String.valueOf(i) + ": " + new Date().toString());
			countDownLatch.countDown();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void testSemaphore(){
		Semaphore semaphore = new Semaphore(0);
		Thread thread = new Thread(() -> {
			while(true){
				try {
					semaphore.acquire(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ": " + new Date().toString());
			}
		}, "asd");
		thread.start();
		
		for (int i = 0; i < 10; i++) {			
			semaphore.release();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
