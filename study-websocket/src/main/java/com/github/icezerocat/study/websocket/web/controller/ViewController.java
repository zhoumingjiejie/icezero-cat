package com.github.icezerocat.study.websocket.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description: 视图跳转控制器
 * CreateDate:  2021/5/14 11:17
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("view")
public class ViewController {
    /**
     * 打开templates页面
     *
     * @param path 页面路径（可选）
     * @param name html文件名
     * @return 返回html
     */
    @RequestMapping("att")
    public String view(@RequestParam(required = false, defaultValue = "") String path, @RequestParam String name) {
        if (name.indexOf("/") != 0 && path.lastIndexOf("/") != path.length() - 1) {
            name = "/" + name;
        }
        name = name.replace(".html", "");
        return StringUtils.hasLength(path) ? name : (path + name);
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("client")
    public String client() {
        return "client";
    }
}
