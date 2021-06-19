package github.com.icezerocat.study.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 0.0.0
 */
@EnableEurekaServer
@SpringBootApplication
public class StudyEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyEurekaApplication.class, args);
    }

}
