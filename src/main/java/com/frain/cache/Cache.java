/**
 * 
 */
package com.frain.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kalven.meng
 *
 */
public class Cache<K, V> {
	private static final Logger LOG = LoggerFactory.getLogger(Cache.class);
	private ConcurrentMap<K, V> cacheObjMap = new ConcurrentHashMap<>();
	private DelayQueue<DelayItem<Pair<K, V>>> queue = new DelayQueue<DelayItem<Pair<K, V>>>();
	private Thread daemonThread ;
	
	public Cache() {
		Runnable daemonTask = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				daemonCheck();
			}
		};
		daemonThread = new Thread(daemonTask);
		daemonThread.setDaemon(true);
		daemonThread.setName("Cache Daemon");
		daemonThread.start();
	}
	
	private void daemonCheck(){
        LOG.info("cache service started.");
		while (true) {
			try {
				DelayItem<Pair<K, V>> delayItem = queue.take();
				if (delayItem != null) {
					Pair<K, V> pair = delayItem.getItem();
					cacheObjMap.remove(pair.first, pair.second);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
                LOG.error("cache service stopped Interrupted",e);
                break;
			}
		}
        LOG.info("cache service stopped.");
	}
	
	public void put(K key,V value,long timeout,TimeUnit unit) {
		// TODO Auto-generated method stub
		V oldValue = cacheObjMap.put(key, value);
		
		if (oldValue != null) {
			queue.remove(key);
		}
		
		long nanoTime = TimeUnit.NANOSECONDS.convert(timeout, unit);
		
		queue.put(new DelayItem<Pair<K,V>>(new Pair<K, V>(key, value), nanoTime));
		
	}
	
	public V get (K key) {
		return cacheObjMap.get(key);
	}
	
	public boolean containKey(K key) {
		return cacheObjMap.containsKey(key);
	}
}
