package com.ityouzi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

/**
 * @Auther: Liberal-World
 * @Date: 2020-05-19 20:33
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
     * 数据源
     */
    @Autowired
    DataSource dataSource;


    /**
     * 持久化token
     * Security中，默认是使用PersistentTokenRepository的子类InMemoryTokenRepositoryImpl，将token放在内存中
     * 如果使用JdbcTokenRepositoryImpl，会创建表persistent_logins，将token持久化到数据库
     */
    @Bean
    JdbcTokenRepositoryImpl jdbcTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
//    @Bean
//    PersistentTokenRepository persistentTokenRepository(){
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);      // 设置数据源
//        jdbcTokenRepository.setCreateTableOnStartup(true);  // 启动创建表，创建成功后注释掉
//        return jdbcTokenRepository;
//    }


    /**
     * formLogin()是默认的登录表单页，如果不配置 loginPage(url)，则使用 spring security
     * 默认的登录页，如果配置了 loginPage()则使用自定义的登录页
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()// 对请求授权
//                .antMatchers("/rememberme").rememberMe()
//                .antMatchers("/admin").fullyAuthenticated()
                .anyRequest().authenticated()// 任何请求都需要身份认证
                .and()
                .formLogin()
                .and()
                .rememberMe()
                .key("javaboy")
                .tokenRepository(jdbcTokenRepository())
                .and()
                .csrf().disable();      // 关闭跨站伪造
    }


}
