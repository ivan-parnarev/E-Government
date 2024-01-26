package com.egovernment.main.config;

import com.egovernment.main.interceptor.AdminTokenInterceptor;
import com.egovernment.main.interceptor.UserTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final AdminTokenInterceptor adminTokenInterceptor;

    private final UserTokenInterceptor userTokenInterceptor;

    public InterceptorConfiguration(@Lazy AdminTokenInterceptor adminTokenInterceptor, @Lazy UserTokenInterceptor userTokenInterceptor) {
        this.adminTokenInterceptor = adminTokenInterceptor;
        this.userTokenInterceptor = userTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminTokenInterceptor)
                .addPathPatterns("/api/v1/campaigns/create/vote")
                .addPathPatterns("/api/v1/campaigns/create/census");
        registry.addInterceptor(userTokenInterceptor)
                .addPathPatterns("/api/v1/vote")
                .addPathPatterns("/api/v1/census");
    }
}
