package com.ityouzi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Auther: Liberal-World
 * @Date: 2020-05-19 17:37
 * @Description:
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("javaboy")
                .password("123").roles("admin");
    }

    /**
     * 实现记住我这个功能
     * 添加一个 .rememberMe() 即可，自动登录功能就成功添加进来了。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .rememberMe()
                .key("javaboy")
                .and()
                .csrf().disable();
    }

}
