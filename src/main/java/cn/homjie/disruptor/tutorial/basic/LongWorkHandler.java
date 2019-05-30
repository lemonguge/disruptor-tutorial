package cn.homjie.disruptor.tutorial.basic;

import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiehong.jh
 * @date 2019-05-30
 */
@Slf4j
public class LongWorkHandler implements WorkHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event) {
        log.info("Event: {}", event);
    }
}
