package com.github.icezerocat.studydocs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Description: WebConfig
 * CreateDate:  2020/10/27 14:41
 *
 * @author zero
 * @version 1.0
 */
//@EnableWebMvc
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    //jsp页面的视图解析器，解析到webapp下的jsp/目录下查找对应的jsp页面
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("classpath:");
        resolver.setSuffix(".jsp");
        resolver.setViewNames("*");//这里是设置该视图解析器所要匹配哪些格式的视图“*”代表匹配所有格式“
        resolver.setOrder(2);//优先级,Spring配置多个视图解析器，数字越小，优先级越高，越先匹配
        return resolver;
    }

    /**
     * 下面都是配置thymeleaf的视图解析器相关的内容
     * 对thymeleaf的视图解析器，解析到webapp下的html目录下查找对应的页面
     *
     * @return 模板解析器
     */
    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // templateEngine
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolverThymeLeaf() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("utf-8");
        viewResolver.setOrder(1);//设置该视图解析器优先级为1
        //下面是设置该视图解析器所要匹配哪些格式的视图"html/*", "vue/*","jsps/*","templates/*"代表匹配"html/*", "vue/*","jsps/*","templates/*"格式的所有视图
        viewResolver.setViewNames(new String[]{"html/*", "vue/*", "jsps/*", "templates/*"});
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
