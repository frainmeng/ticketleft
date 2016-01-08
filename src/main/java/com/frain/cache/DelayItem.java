/**
 * 
 */
package com.frain.cache;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kalven.meng
 *
 */
public class DelayItem<T> implements Delayed {

	public static final long NANO_ORIGIN = System.nanoTime(); 
	
	
	final static long now() {
		return System.nanoTime() - NANO_ORIGIN;
	}
	
	private static final AtomicLong sequencer = new AtomicLong(0);
	
	private final long sequenceNumber;
	
	private final long time;
	
	private final T item;
	
	public DelayItem(T submit, long timeout) {
        this.time = now() + timeout;
        this.item = submit;
        this.sequenceNumber = sequencer.getAndIncrement();
    }

	public T getItem() {
		return this.item;
	}
	
	
	
	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		if (o == this) {
			return 0;
		}
		if (o instanceof DelayItem) {
			DelayItem other = (DelayItem)o;
			
			long diff = time - other.time;
			
			if (diff < 0) {
				return -1;
			} else if (diff > 0) {
				return 1;
			} else if (sequenceNumber < other.sequenceNumber) {
				return -1;
			} else {
				return 1;
			}
		}
		long d = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		return d == 0 ? 0 : (d < 0 ? -1 : 1);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		long d = unit.convert(time - now(), TimeUnit.NANOSECONDS);
		return d;
	}

}
