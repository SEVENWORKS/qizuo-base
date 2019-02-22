package org.qizuo.cm.listener.session;

import org.qizuo.cm.Global;
import org.qizuo.cm.modules.system.pojo.UserPoJo;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: fangl
 * @description: 单用户登录,监听所有session的创建
 * @date: 10:54 2019/1/11
 */
public class SessionListener implements HttpSessionListener{
    /** 登入用户 */
    public static Map<String, HttpSession> LOGIN_USER_MAP = new HashMap<String, HttpSession>();
    /** 下线用户 */
    public static Map<String, String> LOGOUT_USER_MAP = new HashMap<String, String>();
    /**
     * @author: fangl
     * @description: 监听sessing创建
     * @date: 10:55 2019/1/11
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    /**
     * @author: fangl
     * @description: 监听session摧毁
     * @date: 10:55 2019/1/11
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        UserPoJo userPoJo = (UserPoJo) session.getAttribute(Global.SESSION_USER);
        if(null != userPoJo){
            LOGIN_USER_MAP.remove(userPoJo.getUserName());
        }
    }
}
