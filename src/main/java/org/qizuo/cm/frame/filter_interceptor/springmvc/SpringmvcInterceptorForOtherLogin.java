package org.qizuo.cm.frame.filter_interceptor.springmvc;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.GlobalUtil;
import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
import org.qizuo.cm.modules.system.pojo.RolePoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.qizuo.cm.utils.JsonUtil;
import org.qizuo.cm.utils.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: fangl
 * @Description: springmvc第三方未登录拦截器
 * @Date: 10:13 2018/11/19
 */
@Controller
public class SpringmvcInterceptorForOtherLogin extends HandlerInterceptorAdapter {
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
        //获取key
        String key = httpServletRequest.getParameter(Global.OUTMUTUAL_KEY);

        //user判断
        if (null != UserUtil.qUser()) {
            return true;
        }

        //判断key是否存在
        if (StringUtils.isBlank(key)) {
            //返回错误信息
            JsonUtil.httpBackJson(httpServletResponse, JsonUtil.objectToJson(new BackResultPoJo(BackResultPoJo.FAILURE, "门卫验证不成功")));

            return false;
        } else {
            //插入用户
            boolean result = UserUtil.iOtherUser(key);

            //判断结果
            if (result) {
                return true;
            } else {
                //返回错误信息
                JsonUtil.httpBackJson(httpServletResponse, JsonUtil.objectToJson(new BackResultPoJo(BackResultPoJo.FAILURE, "身份牌错误")));
                return false;
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
