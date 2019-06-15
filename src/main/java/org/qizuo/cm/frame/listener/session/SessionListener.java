package org.qizuo.cm.frame.listener.session;

import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.qizuo.cm.utils.UserUtil;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: fangl
 * @description: 单用户登录, 监听所有session的创建
 * @date: 10:54 2019/1/11
 */
public class SessionListener implements HttpSessionListener {
    /**
     * 登入用户
     */
    public static Map<String, HttpSession> LOGIN_USER_MAP = new HashMap<String, HttpSession>();
    /**
     * 下线用户
     */
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
        UserPoJo userPoJo = UserUtil.qUser();
        if (null != userPoJo) {
            LOGIN_USER_MAP.remove(userPoJo.getUserName());
        }
    }

    /**
     * @author: fangl
     * @description: 清除session
     * @date: 21:27 2019/6/15
     */
    public static void loginSessionClear(String userName) {
        //判断用户名是否存在
        HttpSession loged = LOGIN_USER_MAP.get(userName);
        if (null != loged) {
            //销毁
            loged.invalidate();
            //移除
            SessionListener.LOGIN_USER_MAP.remove(userName);
        }
    }
}
