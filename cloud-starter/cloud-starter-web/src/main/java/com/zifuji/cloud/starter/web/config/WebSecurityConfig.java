package com.zifuji.cloud.starter.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.zifuji.cloud.starter.web.object.SimpleAccessDeniedHandler;
import com.zifuji.cloud.starter.web.object.SimpleAuthenticationEntryPoint;
import com.zifuji.cloud.starter.web.object.TokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public WebSecurityConfig() {
        System.out.println("WebSecurityConfig");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .addFilter(new TokenFilter(authenticationManager()))
                .exceptionHandling().accessDeniedHandler(new SimpleAccessDeniedHandler()).authenticationEntryPoint(new SimpleAuthenticationEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico","/index.html","/actuator/**", "/swagger-ui.html", "/webjars/**", "/v2/**", "/swagger-resources/**");
    }
}