package org.qizuo.cm.utils;

import org.qizuo.cm.Global;
import org.qizuo.cm.modules.system.pojo.UserPoJo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author :fangl
 * @Description: user工具
 * @Date:14:12 2018/10/29
 */
public class UserUtil {
    /**
     * @author: fangl
     * @description: 获取用户信息
     * @date: 17:33 2019/1/9
     */
    public static UserPoJo qUser(HttpServletRequest httpServletRequest){
        //获取当前session
        HttpSession httpSession=httpServletRequest.getSession();
        //从当前session中获取用户信息
        UserPoJo userPoJo =(UserPoJo)httpSession.getAttribute(Global.SESSION_USER);
        return userPoJo;
    }

}
