package com.barbearia.sistema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SecurityAcess securityAcess;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityAcess)

                .addPathPatterns("/admin/**", "/barbeiros/**", "/produtos/**", "/servicos/**", "/atendimentos/**")


                .excludePathPatterns("/","/produtos","/atendimentos/novo","/admin", "/admin/validarLogin", "/css/**", "/js/**");
    }
}
