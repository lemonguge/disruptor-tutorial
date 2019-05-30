package cn.homjie.disruptor.tutorial.basic;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiehong.jh
 * @date 2019-05-30
 */
@Getter
@Setter
@Slf4j
public class LongEvent {

    private long value;

    public LongEvent() {
        log.info("LongEvent construct");
    }
}
