package org.qizuo.cm.frame.filter_interceptor.springmvc;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.modules.system.pojo.RolePoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.qizuo.cm.utils.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: fangl
 * @Description: springmvc未登录拦截器
 * @Date: 10:13 2018/11/19
 */
@Controller
public class SpringmvcInterceptor extends HandlerInterceptorAdapter {
    /**
     * 不拦截url
     */
    private List<String> exceptUrls;

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }

    /**
     * @Author: fangl
     * @Description: 调用最终方法之前(即是controller方法调用之前, 这个最终方法即是配置文件上来的)
     * @Date: 16:12 2018/11/24
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //判断用户是否登录
        UserPoJo userPoJo = UserUtil.qUser();

        //获取当前访问url，过滤用
        String requestUri = httpServletRequest.getRequestURI();
        if (requestUri.startsWith(httpServletRequest.getContextPath())) {
            requestUri = requestUri.substring(httpServletRequest.getContextPath().length(), requestUri.length());
        }

        //判断路径是否是白名单
        /*boolean jump = true;
        for (String url : exceptUrls) {
            if (requestUri.contains(url)) {
                jump = false;
                break;
            }
        }*/

        //判断是否存在用户信息
        if (null == userPoJo) {
            //登录过，防止重复登录
            if (requestUri.contains(Global.LOGIN_URL)||requestUri.contains(Global.LOGIN_CHECK)) {
                return true;
            } else {
                //未登录，跳转到登录页面
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + Global.LOGIN_URL);
                return false;
            }
        } else {
            //登录过，防止重复登录
            if (requestUri.contains(Global.LOGIN_URL)||requestUri.contains(Global.LOGIN_CHECK)) {
                //获取当前用户登陆后跳转的页面
                String jumpUrl = Global.LOGIN_CHANGE_URL;
                List<RolePoJo> rolePoJos = userPoJo.getRolePoJos();
                if (null != rolePoJos) {
                    for (RolePoJo rolePoJo : rolePoJos) {
                        if (StringUtils.isNotBlank(rolePoJo.getJumpUrl())) {
                            jumpUrl = rolePoJo.getJumpUrl();
                            break;
                        }
                    }
                }
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + jumpUrl);
                return false;
            } else {
                return true;
            }

        }

        //返回为false，就不会调用后面拦截器和目标方法
    }

    /**
     * @Author: fangl
     * @Description: 调用最终方法之前(即是controller方法调用之前, 这个最终方法即是配置文件上来的)，渲染制图之前
     * @Date: 16:12 2018/11/24
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * @Author: fangl
     * @Description: 渲染视图之后调用
     * @Date: 16:12 2018/11/24
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
