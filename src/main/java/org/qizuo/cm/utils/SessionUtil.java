package org.qizuo.cm.utils;

import org.qizuo.cm.GlobalUtil;

import javax.servlet.http.HttpSession;

/**
 * @Author :fangl
 * @Description: session工具
 * @Date:14:12 2018/10/29
 */
public class SessionUtil {
    /**
     * @author: fangl
     * @description: 将用户信息放入缓存中
     * @date: 17:33 2019/1/9
     */
    public static HttpSession sessionAdd(String name, Object value) {
        //获取当前session
        HttpSession httpSession = GlobalUtil.qHttpServletRequest().getSession();
        if (null != httpSession) {
            //存储值
            httpSession.setAttribute(name, value);
        }
        return httpSession;
    }

    /**
     * @author: fangl
     * @description: 从缓存中移除某个
     * @date: 17:36 2019/1/9
     */
    public static void sessionRemove(String name) {
        //获取当前session
        HttpSession httpSession = GlobalUtil.qHttpServletRequest().getSession();
        if (null != httpSession) {
            //销毁
            httpSession.removeAttribute(name);
        }
    }

    /**
     * @author: fangl
     * @description: 从缓存中销毁
     * @date: 17:36 2019/1/9
     */
    public static void sessionDestroy() {
        //获取当前session
        HttpSession httpSession = GlobalUtil.qHttpServletRequest().getSession();
        if (null != httpSession) {
            //销毁
            httpSession.invalidate();
        }
    }

    /**
     * @author: fangl
     * @description: 从缓存中读取用户信息
     * @date: 17:32 2019/1/9
     */
    public static Object sessionGet(String name) {
        //获取当前session
        HttpSession httpSession = GlobalUtil.qHttpServletRequest().getSession();
        if (null != httpSession) {
            //返回获取值
            return httpSession.getAttribute(name);
        } else {
            return null;
        }
    }

}
