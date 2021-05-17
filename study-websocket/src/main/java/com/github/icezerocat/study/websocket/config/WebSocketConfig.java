package com.github.icezerocat.study.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Description: socket配置类,往 spring 容器中注入ServerEndpointExporter实例
 * CreateDate:  2021/5/14 10:47
 *
 * @author zero
 * @version 1.0
 */
@Configuration
public class WebSocketConfig {

    /**
     * 服务端点导出器
     *
     * @return 端点导出器
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
