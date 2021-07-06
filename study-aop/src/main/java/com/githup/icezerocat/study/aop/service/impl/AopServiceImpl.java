package com.githup.icezerocat.study.aop.service.impl;

import com.githup.icezerocat.study.aop.service.AopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description: aop实现类
 * CreateDate:  2021/6/24 11:41
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@Service("aopService")
public class AopServiceImpl implements AopService {

    @Override
    public String say(String value) {
        log.debug("1:{}", this.pro(value));
        log.debug("2:{}", this.pri(value));
        log.debug("3:{}", sta(value));
        log.debug("4:{}", this.pub(value));
        return value;
    }

    public String pub(String value) {
        log.debug("pub:{}", value);
        return value;
    }

    protected String pro(String value) {
        log.debug("pro:{}", value);
        return value;
    }

    private String pri(String value) {
        log.debug("pri:{}", value);
        return value;
    }

    public static String sta(String value) {
        log.debug("sta:{}", value);
        return value;
    }
}
