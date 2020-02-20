package com.xingzhi.apartment.listener;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Autowired
    private Logger logger;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.debug(">>>>>>>>>> The Application is started.");
        String serverInfo = servletContextEvent.getServletContext().getServerInfo();
        logger.debug(">>>>>>>>>> The server information: " + serverInfo);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.debug(">>>>>>>>>> The Application is destroyed!");
    }
}
