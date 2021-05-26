package com.github.icezerocat.study.websocket.socket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: WebSocket服务端代码，包含接收消息，推送消息等接口
 * CreateDate:  2021/5/14 10:52
 *
 * @author zero
 * @version 1.0
 */
@Component
@ServerEndpoint(value = "/socket/{name}")
public class WebSocketServer {

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static AtomicInteger online = new AtomicInteger();

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
     */
    private static Map<String, Session> sessionPools = new HashMap<>();

    /**
     * 发送消息方法
     *
     * @param session 客户端与socket建立的会话
     * @param message 消息
     * @throws IOException 发送消息异常
     */
    private void sendMessage(Session session, String message) throws IOException {
        if (session != null) {
            session.getBasicRemote().sendText(message);
        }
    }


    /**
     * 连接建立成功调用
     *
     * @param session  客户端与socket建立的会话
     * @param userName 客户端的userName
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "name") String userName) {
        sessionPools.put(userName, session);
        addOnlineCount();
        System.out.println(userName + "加入webSocket！当前人数为" + online);
        onMessage("欢迎" + userName + "加入连接！");
    }

    /**
     * 关闭连接时调用
     *
     * @param userName 关闭连接的客户端的姓名
     */
    @OnClose
    public void onClose(@PathParam(value = "name") String userName) {
        sessionPools.remove(userName);
        subOnlineCount();
        System.out.println(userName + "断开webSocket连接！当前人数为" + online);
        this.sendInfo("zero", "用户" + userName + "已下线");
    }

    /**
     * 收到客户端消息时触发（群发）
     *
     * @param message 消息
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("消息：" + message);
        for (Session session : sessionPools.values()) {
            try {
                sendMessage(session, message + "(用户总数:" + online + ")  " + new SimpleDateFormat().format(new Date()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给指定用户发送消息
     *
     * @param userName 用户名
     * @param message  消息
     */
    public void sendInfo(String userName, String message) {
        System.out.println(userName + ":" + message);
        Session session = sessionPools.get(userName);
        try {
            sendMessage(session, userName + ":" + message + "(用户总数:" + online + ")  " + new SimpleDateFormat().format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加客户端
     */
    private static void addOnlineCount() {
        online.incrementAndGet();
    }

    /**
     * 断开客户端
     */
    private static void subOnlineCount() {
        online.decrementAndGet();
    }

    /**
     * 关闭全部连接
     *
     * @return 返回链接人数
     */
    public static int closeAll() {
        for (String userName : sessionPools.keySet()) {
            sessionPools.remove(userName);
            subOnlineCount();
        }
        return online.get();
    }

    /**
     * 获取在线人数
     *
     * @return 在线人数
     */
    public static int onlineCount() {
        return online.get();
    }

    /**
     * 获取在线用户
     *
     * @return 在线用户
     */
    public static Set<String> onlineUser() {
        return new HashSet<>(sessionPools.keySet());
    }
}
