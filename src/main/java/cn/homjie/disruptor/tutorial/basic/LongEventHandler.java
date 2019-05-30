package cn.homjie.disruptor.tutorial.basic;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiehong.jh
 * @date 2019-05-30
 */
@Slf4j
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        log.info("Sequence:{}, Event: {}", sequence, event);
    }
}
