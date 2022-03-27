package com.example.test.servlet;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @author Java技术栈
 */
@Component
public class ServletConfig implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        ServletRegistration initServlet = servletContext.addServlet("ServletConfig", ThriftService.class);
        initServlet.addMapping("/thrifttest");
//        initServlet.setInitParameter("name", "javastack");
//        initServlet.setInitParameter("sex", "man");
    }
}