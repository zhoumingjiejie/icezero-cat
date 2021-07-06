package com.github.icezerocat.study.autoconfigure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 儿子
 * CreateDate:  2021/7/5 15:06
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@Configuration
@AutoConfigureAfter(B_ParentConfig.class)
public class A_SonConfig {

    public A_SonConfig() {
        log.debug("儿子");
    }
}
