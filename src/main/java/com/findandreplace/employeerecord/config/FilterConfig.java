package com.findandreplace.employeerecord.config;

import com.findandreplace.employeerecord.interceptors.GeneralFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<GeneralFilter> employeeFilter(){
        GeneralFilter generalFilter = new GeneralFilter();
        FilterRegistrationBean registry = new FilterRegistrationBean<GeneralFilter>();
        registry.setFilter(generalFilter);
        registry.addUrlPatterns("/api/employees/*");
        return registry;
    }
}
