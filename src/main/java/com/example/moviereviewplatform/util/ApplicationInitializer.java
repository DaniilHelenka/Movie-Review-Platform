package com.example.moviereviewplatform.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Получаем значение из application.properties
        String imageBaseUrl = new PropertiesUtil().get();
        // Сохраняем в атрибут контекста
        sce.getServletContext().setAttribute("imageBaseUrl", imageBaseUrl);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}