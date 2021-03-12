package com.kyobobook.mdm.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import websquare.http.DefaultRequestDispatcher;
 
@Configuration
public class ServletRegistrationConfig {
    @Bean
    public ServletRegistrationBean<DefaultRequestDispatcher> getServletRegistrationBean() {
        ServletRegistrationBean<DefaultRequestDispatcher> websquareDispatcher = new ServletRegistrationBean<>(new websquare.http.DefaultRequestDispatcher());
        websquareDispatcher.addUrlMappings("*.wq");
        return websquareDispatcher;
    }
}