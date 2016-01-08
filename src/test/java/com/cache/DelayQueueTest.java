package com.cache;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.frain.cache.Cache;

public class DelayQueueTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*long starttime = System.nanoTime();
		System.out.println(starttime);
		System.out.println("打印。。。。。。");
		long endtime = System.nanoTime();
		System.out.println(endtime);
		long delay = endtime - starttime;
		System.out.println(TimeUnit.NANOSECONDS.convert(delay, TimeUnit.NANOSECONDS));
		System.out.println(delay);*/
		
		Cache<String, String> cache = new Cache<String, String>();
		cache.put("a", "value_aaa", 10, TimeUnit.SECONDS);
		cache.put("b", "value_bbb", 5, TimeUnit.SECONDS);
		cache.put("c", "value_ccc", 3, TimeUnit.SECONDS);
		
		int i = 0;
		for (;;) {
			Thread.sleep(1000 * 1);
			i++;
			System.out.println("第" + i + "秒结果：");
			String valueA = cache.get("a");
			System.out.println("valueA:" + valueA);
			String valueB = cache.get("b");
			System.out.println("valueB:" + valueB);
			String valueC = cache.get("c");
			System.out.println("valueC:" + valueC);
		}
	}
	public static void testDelayQueue() {
		DelayQueue<Delayed> dq = new DelayQueue<Delayed>();
	}
}
