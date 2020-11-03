package com.githup.icezerocat.studyproxy.impl;

import com.githup.icezerocat.studyproxy.Star;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description: 刘德华
 * CreateDate:  2020/9/14 8:56
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@Service
public class LiuDeHua implements Star {
    @Override
    public String sing(String name) {
        return "给我一杯忘情水——".concat(name);
    }

    @Override
    public String dance(String name) {
        return "开心的马骝——".concat(name);
    }
}
