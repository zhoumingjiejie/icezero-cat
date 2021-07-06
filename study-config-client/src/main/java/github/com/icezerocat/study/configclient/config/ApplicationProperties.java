package github.com.icezerocat.study.configclient.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 德鲁伊数据源属性
 * CreateDate:  2021/7/6 15:14
 *
 * @author zero
 * @version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "my")
public class ApplicationProperties {
    /**
     * 链接驱动
     */
    @Value("${my.config::}")
    private String myConfig;
    /**
     * 链接地址
     */
    private String config;
}
