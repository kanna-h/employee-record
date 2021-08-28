package com.findandreplace.employeerecord.interceptors;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class GeneralFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String parameter = request.getHeader("token");
        System.out.println("******************FILTER***************************");
        System.out.println(parameter);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
