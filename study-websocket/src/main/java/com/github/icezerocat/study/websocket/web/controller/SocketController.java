package com.github.icezerocat.study.websocket.web.controller;

import com.github.icezerocat.study.websocket.socket.WebSocketServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Description: Socket 控制器
 * CreateDate:  2021/5/14 11:04
 *
 * @author zero
 * @version 1.0
 */
public class SocketController {
    @Resource
    private WebSocketServer webSocketServer;

    /**
     * 给指定用户推送消息
     *
     * @param userName 用户名
     * @param message  消息
     */
    @RequestMapping(value = "/socket", method = RequestMethod.GET)
    public void testSocket1(@RequestParam String userName, @RequestParam String message) {
        webSocketServer.sendInfo(userName, message);
    }

    /**
     * 给所有用户推送消息
     *
     * @param message 消息
     */
    @RequestMapping(value = "/socket/all", method = RequestMethod.GET)
    public void testSocket2(@RequestParam String message) {
        webSocketServer.onMessage(message);
    }
}
