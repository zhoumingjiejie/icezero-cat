package com.github.icezerocat.study.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 0.0.0
 */
@Slf4j
@SpringBootApplication
public class StudyAutoconfigureApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyAutoconfigureApplication.class, args);
        log.debug("代码千万行，注释第一行");
    }

}
