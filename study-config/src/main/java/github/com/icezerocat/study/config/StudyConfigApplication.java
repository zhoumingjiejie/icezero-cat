package github.com.icezerocat.study.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author 0.0.0
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class StudyConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyConfigApplication.class, args);
    }

}
