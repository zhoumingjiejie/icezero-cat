package com.github.icezerocat.studydocs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Description: TODO
 * CreateDate:  2020/10/27 15:16
 *
 * @author zero
 * @version 1.0
 */
@Configuration
public class ViewResolverConfiguration {
    //用来定义 DispatcherServlet 应用上下文中的 bean
    @Configuration
    @EnableWebMvc
    @ComponentScan("com.github.icezerocat")
    public class WebConfig extends WebMvcConfigurerAdapter {
        @Bean
        public ViewResolver viewResolver() {
            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
            resolver.setPrefix("/");
            resolver.setSuffix(".jsp");
            resolver.setViewNames("*");
            resolver.setOrder(2);
            return resolver;
        }

        @Bean
        public ITemplateResolver templateResolver() {
            SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
            templateResolver.setTemplateMode("HTML5");
            templateResolver.setPrefix("/templates/");
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
            viewResolver.setOrder(1);
            //viewResolver.setViewNames(new String[]{"thyme/*"});
            viewResolver.setViewNames(new String[]{"thymeleaf/*", "vue/*"});
            return viewResolver;
        }

        @Override
        public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            super.addResourceHandlers(registry);
        }
    }

}
