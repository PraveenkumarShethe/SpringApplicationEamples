package com.example.StatelessAuthenticationApplication.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Praveenkumar on 5/11/2021.
 */
public class StatelessAuthWebAppInitializer implements WebApplicationInitializer {
    
    private static final Logger logger = LoggerFactory.getLogger(StatelessAuthWebAppInitializer.class);


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info(" onStartup ");
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",
                new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("*.html");
    }

    private AnnotationConfigWebApplicationContext getContext() {
        logger.info(" AnnotationConfigWebApplicationContext ");
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.example.StatelessAuthenticationApplication.config.StatelessWebapplicationConfiguration");
        return context;
    }
}
