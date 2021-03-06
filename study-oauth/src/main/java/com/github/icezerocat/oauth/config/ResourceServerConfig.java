package com.github.icezerocat.oauth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Description: 资源服务器配置
 * CreateDate:  2021/1/7 11:10
 *
 * @author zero
 * @version 1.0
 **/

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       /* http.authorizeRequests().anyRequest().authenticated()
                .and()
                .requestMatchers()
                //需要OAuth鉴权
                .antMatchers("/say/**");*/
       super.configure(http);
    }
}
