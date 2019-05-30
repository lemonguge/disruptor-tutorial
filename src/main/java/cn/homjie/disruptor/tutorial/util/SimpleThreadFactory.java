package cn.homjie.disruptor.tutorial.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jiehong.jh
 * @date 2019-05-30
 */
public class SimpleThreadFactory implements ThreadFactory {

    private final AtomicInteger sequence = new AtomicInteger(1);
    private final String prefix;

    public SimpleThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        int seq = sequence.getAndIncrement();
        thread.setName(prefix + seq);
        return thread;
    }
}
