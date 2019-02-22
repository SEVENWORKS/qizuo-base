package org.qizuo.cm;

import org.qizuo.cm.interceptor.springmvc.SpringmvcDispatcherServlet;
import org.qizuo.cm.listener.session.SessionListener;
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
        //字符集过滤
        CharacterEncodingFilter characterEncodingFilter=new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        FilterRegistration.Dynamic encoding =servletContext.addFilter("characterEncodingFilter",characterEncodingFilter);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet
                .allOf(DispatcherType.class);
        dispatcherTypes.add(DispatcherType.REQUEST);
        dispatcherTypes.add(DispatcherType.FORWARD);
        encoding.addMappingForUrlPatterns(dispatcherTypes,true,"/");

        //springMvc启动项
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("classpath:config/spring/spring-mvc.xml");
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new SpringmvcDispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        //spring启动项
        servletContext.setInitParameter("contextConfigLocation","classpath:config/spring/applicationContext.xml");
        ContextLoaderListener contextLoaderListener=new ContextLoaderListener();
        contextLoaderListener.initWebApplicationContext(servletContext);

        //单个用户登录监听
        servletContext.addListener(SessionListener.class);

        //url重写
        /*UrlRewriteFilter urlRewriteFilter=new UrlRewriteFilter();
        Hashtable<String,String> ht=new Hashtable<>();
        ht.put("confPath","classpath:config/urlrewrite/urlrewrite.xml");
        FilterConfig filterConfig=new RunConfig(servletContext,ht);
        urlRewriteFilter.init(filterConfig);
        FilterRegistration.Dynamic urlRewrite =servletContext.addFilter("urlRewriteFilter",urlRewriteFilter);
        EnumSet<DispatcherType> dispatcherTypes2 = EnumSet
                .allOf(DispatcherType.class);
        dispatcherTypes.add(DispatcherType.REQUEST);
        dispatcherTypes.add(DispatcherType.FORWARD);
        urlRewrite.addMappingForUrlPatterns(dispatcherTypes2,true,"/");*/
    }
}
