package com.egovernment.egovbackend.config;

import com.egovernment.egovbackend.interceptor.AdminTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final AdminTokenInterceptor adminTokenInterceptor;

    public InterceptorConfiguration(@Lazy AdminTokenInterceptor tokenInterceptor) {
        this.adminTokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminTokenInterceptor)
                .addPathPatterns("/api/v1/campaigns/create/vote")
                .addPathPatterns("/api/v1/campaigns/create/census");
    }
}
