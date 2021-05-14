package com.github.icezerocat.oauth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Description: 授权服务配置
 * CreateDate:  2021/1/8 17:13
 *
 * @author zero
 * @version 1.0
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final TokenStore tokenStore;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
    }

    /**
     * 用来配置客户端详情服务(给谁发送令牌)
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //添加客户端信息
        clients
                //使用内存存储客户端信息
                .inMemory()
                //客户端ID
                .withClient("client")
                //密钥
                .secret(new BCryptPasswordEncoder().encode("secret"))
                //此客户端允许的授权类型：密码式：password,隐藏式：implicit,授权码：authorization_code,客户端凭证：client_credentials
                .authorizedGrantTypes("authorization_code", "implicit", "password", "client_credentials")
                //权限
                .scopes("app", "admin", "user")
                .redirectUris("http://www.baidu.com")
                //登录后绕过批准询问(/oauth/confirm_access)
                .autoApprove(true);
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 配置存储token的方式(默认InMemoryTokenStore)
        endpoints.tokenStore(this.tokenStore)
                // 密码模式，必须配置AuthenticationManager，不然不生效
                .authenticationManager(this.authenticationManager)
                // 密码模式，这里得配置UserDetailsService
                .userDetailsService(this.userDetailsService);

        /*
         * pathMapping用来配置端点URL链接，有两个参数，都将以 "/" 字符为开始的字符串
         *
         * defaultPath：这个端点URL的默认链接
         *
         * customPath：你要进行替代的URL链接
         */
        endpoints.pathMapping("/oauth/token", "/oauth/xwj");
    }
}
