package com.github.icezerocat.study.autoconfigure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;

/**
 * Description: 小三
 * CreateDate:  2021/7/5 15:07
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
@DependsOn("d_WifeConfig")
public class C_DemoConfig {
    public C_DemoConfig() {
        log.debug("小三");
    }
}
