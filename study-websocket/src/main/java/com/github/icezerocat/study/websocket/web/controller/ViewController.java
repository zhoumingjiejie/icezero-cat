package com.github.icezerocat.study.websocket.web.controller;

import com.github.icezerocat.study.websocket.socket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

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

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    @Resource
    private WebSocketServer webSocketServer;

    /**
     * 给指定用户推送消息
     *
     * @param userName 用户名
     * @param message  消息
     */
    @ResponseBody
    @RequestMapping(value = "/sendInfo", method = RequestMethod.GET)
    public void sendInfo(@RequestParam String userName, @RequestParam String message) {
        webSocketServer.sendInfo(userName, message);
    }

    /**
     * 给所有用户推送消息
     *
     * @param message 消息
     */
    @ResponseBody
    @RequestMapping(value = "/onMessage", method = RequestMethod.GET)
    public void onMessage(@RequestParam String message) {
        webSocketServer.onMessage(message);
    }

    /**
     * 关闭全部连接
     *
     * @return 返回链接人数
     */
    @ResponseBody
    @RequestMapping("closeAll")
    public int closeAll() {
        return WebSocketServer.closeAll();
    }

    /**
     * 获取在线人数
     *
     * @return 在线人数
     */
    @ResponseBody
    @RequestMapping("onlineCount")
    public int onlineCount() {
        return WebSocketServer.onlineCount();
    }

    /**
     * 获取在线用户
     *
     * @return 在线人数
     */
    @ResponseBody
    @RequestMapping("onlineUser")
    public Set<String> onlineUser() {
        return WebSocketServer.onlineUser();
    }

    /**
     * info
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("info")
    public void info(String info) {
        System.out.println(info + "\t" + new SimpleDateFormat().format(new Date()));
    }
}
