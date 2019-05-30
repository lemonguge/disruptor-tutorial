package cn.homjie.disruptor.tutorial.basic;

import java.util.stream.IntStream;

import cn.homjie.disruptor.tutorial.util.SimpleThreadFactory;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiehong.jh
 * @date 2019-05-30
 */
@Slf4j
public class LongEventMain {

    public static void main(String[] args) {
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1 << 4;

        final String prefix = "disruptor-worker-";
        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize,
            new SimpleThreadFactory(prefix), ProducerType.SINGLE, new BlockingWaitStrategy());

        // Connect the handler
        LongEventHandler[] eventHandlers = IntStream.range(0, 3)
            .mapToObj(i -> new LongEventHandler())
            .toArray(LongEventHandler[]::new);
        LongWorkHandler[] workHandlers = IntStream.range(0, 3)
            .mapToObj(i -> new LongWorkHandler())
            .toArray(LongWorkHandler[]::new);
        // 广播：对于多个消费者，每条信息会达到所有的消费者，被多次处理
        disruptor.handleEventsWith(eventHandlers);
        // 分组：对于同一组内的多个消费者，每条信息只会被组内一个消费者处理
        disruptor.handleEventsWithWorkerPool(workHandlers);

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        int loop = 10;
        while (loop-- > 0) {
            producer.publish((long)loop);
        }

        log.info("publish ok");
        disruptor.shutdown();
    }
}
