package com.github.icezerocat.study.autoconfigure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Description: 老婆
 * CreateDate:  2021/7/5 15:13
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class D_WifeConfig {
    public D_WifeConfig() {
        log.debug("老婆");
    }
}
