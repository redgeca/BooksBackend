package com.productions.redge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
public class BackendConfig extends WebMvcConfigurationSupport   {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("POST")
            .allowedHeaders("Content-Type", "Authorization", "total-count")
            .allowCredentials(false)
            .maxAge(32400);  // 9 hours max age
    }
}