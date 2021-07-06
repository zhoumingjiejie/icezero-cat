package com.githup.icezerocat.study.aop.web.controller;

import com.githup.icezerocat.study.aop.service.AopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: aop控制器
 * CreateDate:  2021/6/24 12:02
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("aop")
@RequiredArgsConstructor
public class AopController {

    final private AopService aopService;

    @GetMapping("say")
    public String say() {
        String value = "hello";
        return this.aopService.say(value);
    }
}
