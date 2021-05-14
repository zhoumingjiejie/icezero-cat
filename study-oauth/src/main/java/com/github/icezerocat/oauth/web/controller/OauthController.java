package com.github.icezerocat.oauth.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Description: 认证控制器
 * CreateDate:  2021/1/8 17:02
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("say")
public class OauthController {

    @RequestMapping("hello")
    public String hello() {
        log.debug("hello world:{}", new Date());
        return "hello world";
    }
}
