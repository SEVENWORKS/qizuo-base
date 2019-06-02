package org.qizuo.cm.frame.filter_interceptor.springmvc;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @Author: fangl
 * @Description: 重写分发器, 所有请求都会进入这个分发器
 * @Date: 15:37 2018/11/24
 */
public class SpringmvcDispatcherServlet extends DispatcherServlet {
    public SpringmvcDispatcherServlet() {
    }

    public SpringmvcDispatcherServlet(WebApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }
}
