package com.science.earth.biogeochemistry.freshwaters.pandora.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
public class ThymeleafConfig {
    
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}