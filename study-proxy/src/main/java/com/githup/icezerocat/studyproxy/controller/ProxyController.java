package com.githup.icezerocat.studyproxy.controller;

import com.githup.icezerocat.studyproxy.CglibProxy;
import com.githup.icezerocat.studyproxy.Star;
import com.githup.icezerocat.studyproxy.StarProxy;
import com.githup.icezerocat.studyproxy.impl.LiuDeHua;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 代理控制器
 * CreateDate:  2020/9/14 9:28
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@RestController("proxy")
public class ProxyController {

    /**
     * 开始代理
     */
    @GetMapping("startProxy")
    public void startProxy() {
        //JDK代理
        LiuDeHua liuDeHua = new LiuDeHua();
        StarProxy starProxy = new StarProxy();
        starProxy.setTarget(liuDeHua);
        Object o = starProxy.creatProxyObj();
        Star star = (Star) o;

        CglibProxy cglibProxy = new CglibProxy();
        Object proxyObj = cglibProxy.CreatProxyObj(LiuDeHua.class);
        Star cglibStar = (Star) proxyObj;
        log.debug("{},{}", star.dance("跳舞"), cglibStar.sing("今夜唱不停"));
    }
}
