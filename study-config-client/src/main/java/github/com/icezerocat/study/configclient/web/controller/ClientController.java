package github.com.icezerocat.study.configclient.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 客户端控制器
 * CreateDate:  2021/6/19 12:21
 *
 * @author zero
 * @version 1.0
 */
@RestController
@RequestMapping("client")
public class ClientController {

    @Value("${my.config:}")
    public String config;

    /**
     * 测试获取config的配置
     *
     * @return config的配置
     */
    @GetMapping("test")
    public String test() {
        System.out.println("test:".concat(this.config));
        return this.config;
    }
}
