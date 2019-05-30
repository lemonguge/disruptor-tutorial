package cn.homjie.disruptor.tutorial.basic;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiehong.jh
 * @date 2019-05-30
 */
@Slf4j
public class LongEventProducer {

    private static final EventTranslatorOneArg<LongEvent, Long> TRANSLATOR =
        (event, sequence, value) -> event.setValue(value);

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void publish(Long value) {
        log.info("publish value: {}", value);
        ringBuffer.publishEvent(TRANSLATOR, value);
    }
}
