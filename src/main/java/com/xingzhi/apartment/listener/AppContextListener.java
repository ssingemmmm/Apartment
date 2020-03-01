//package com.xingzhi.apartment.listener;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//@WebListener
//public class AppContextListener implements ServletContextListener {
//    @Autowired
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        if (logger == null) {
//            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, request.getServletContext());
//        }
//        logger.debug(">>>>>>>>>> The Application is started.");
//        String serverInfo = servletContextEvent.getServletContext().getServerInfo();
//        logger.debug(">>>>>>>>>> The server information: " + serverInfo);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent event) {
//        if (logger == null) {
//            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, request.getServletContext());
//        }
//        logger.debug(">>>>>>>>>> The Application is destroyed!");
//    }
//}
