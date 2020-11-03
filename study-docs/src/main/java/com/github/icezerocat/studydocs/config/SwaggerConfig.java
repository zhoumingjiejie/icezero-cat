package com.github.icezerocat.studydocs.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.github.xiaoymin.swaggerbootstrapui.model.SpringAddtionalModel;
import com.github.xiaoymin.swaggerbootstrapui.service.SpringAddtionalModelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Optional;

/**
 * Description: Swagger配置
 * CreateDate:  2020/4/7 14:38
 *
 * @author 0.0.0
 * @version 1.0
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Resource
    private SwaggerProperties swaggerProperties;

    private final SpringAddtionalModelService springAddtionalModelService;

    public SwaggerConfig(SpringAddtionalModelService springAddtionalModelService) {
        this.springAddtionalModelService = springAddtionalModelService;
    }

    /**
     * 创建接口api
     *
     * @return 案卷（创建结果）
     */
    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

        //自定义扫描models包
        Optional.ofNullable(this.swaggerProperties).map(SwaggerProperties::getBasePackages)
                .filter(basePackages -> CollectionUtils.isEmpty(Arrays.asList(basePackages))).ifPresent(basePackages->{
            SpringAddtionalModel springAddtionalModel = this.springAddtionalModelService.scan(basePackages);
            docket.additionalModels(springAddtionalModel.getFirst(), springAddtionalModel.getRemaining());
        });
        return docket;
    }
}
