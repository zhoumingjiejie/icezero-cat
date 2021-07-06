package com.github.icezerocat.study.autoconfigure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * Description: B_ParentConfig
 * CreateDate:  2021/7/5 15:05
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@Configuration
//@AutoConfigureBefore(A_SonConfig.class)
public class B_ParentConfig {

    public B_ParentConfig() {
        log.debug("父亲");
    }
}
