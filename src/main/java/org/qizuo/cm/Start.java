package org.qizuo.cm;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;
import org.qizuo.cm.interceptor.springmvc.SpringmvcDispatcherServlet;
import org.qizuo.cm.listener.session.SessionListener;
import org.springframework.context.ApplicationListener;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import java.util.EnumSet;

/**
 * @Author: fangl
 * @Description: 初始化
 * @Date: 17:24 2018/10/25
 */
public class Start implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //spring启动项(Listener配置)
        servletContext.setInitParameter("contextConfigLocation","classpath:config/spring/applicationContext.xml");
        servletContext.addListener(ContextLoaderListener.class);

        //日志打印(Listener配置)
        servletContext.setInitParameter("logbackConfigLocation","classpath:config/log/logback.xml");
        servletContext.addListener(LogbackConfigListener.class);

        //listener加载(Listener可以用上述的加载方式，也可以用哦个方式)
        /*ContextLoaderListener logbackConfigLocation=new ContextLoaderListener();
        logbackConfigLocation.initWebApplicationContext(servletContext);*/

        //单个用户登录监听
        //servletContext.addListener(SessionListener.class);

        //字符集过滤(filter方式加载)
        CharacterEncodingFilter characterEncodingFilter=new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        FilterRegistration.Dynamic encoding =servletContext.addFilter("characterEncodingFilter",characterEncodingFilter);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet
                .allOf(DispatcherType.class);
        dispatcherTypes.add(DispatcherType.REQUEST);
        dispatcherTypes.add(DispatcherType.FORWARD);
        encoding.addMappingForUrlPatterns(dispatcherTypes,true,"/");

        //springMvc启动项(servlet方式加载)
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("classpath:config/spring/spring-mvc.xml");
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new SpringmvcDispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
}
