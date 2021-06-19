package github.com.icezerocat.study.configclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Description: 启动初始文件
 * CreateDate:  2021/6/11 14:34
 *
 * @author zero
 * @version 1.0
 */
@Component
public class ApplicationConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${my.config:}")
    private String config;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            System.out.println("hello config:".concat(this.config));
//        }
    }
}
