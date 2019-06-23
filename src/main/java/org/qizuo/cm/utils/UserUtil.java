package org.qizuo.cm.utils;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.GlobalInit;
import org.qizuo.cm.GlobalUtil;
import org.qizuo.cm.modules.system.pojo.UserPoJo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author :fangl
 * @Description: user工具
 * @Date:14:12 2018/10/29
 */
public class UserUtil {
    /**
     * @author: fangl
     * @description: 获取用户信息(系统管理员user和第三方user肯定只会有一个存在)
     * @date: 17:33 2019/1/9
     */
    public static UserPoJo qUser() {
        //先获取第三方user
        UserPoJo userPoJo = qOtherUser();

        //如果没有，就获取当前系统user
        if (null == userPoJo) {
            //获取当前session
            HttpSession httpSession = GlobalUtil.qHttpServletRequest().getSession();
            //从当前session中获取用户信息
            userPoJo = (UserPoJo) httpSession.getAttribute(Global.SESSION_USER);
        }

        return userPoJo;
    }

    /**
     * @author: fangl
     * @description: 插入第三方User
     * @date: 17:33 2019/1/9
     */
    public static boolean iOtherUser(String key) {
        //取用户列表
        List<UserPoJo> userPoJos = GlobalInit.userPoJos;
        if (null != userPoJos && userPoJos.size() > 0) {
            //标识
            boolean result=true;
            //获取用户信息
            for (UserPoJo userPoJo : userPoJos) {
                if (StringUtils.isNotBlank(userPoJo.getOutMutualKey()) && key.equals(userPoJo.getOutMutualKey())) {
                    //放到request域中
                    GlobalUtil.qHttpServletRequest().setAttribute(Global.SESSION_USER, userPoJo);
                    //标识转变
                    result=false;
                    break;
                }
            }

            //标识判断
            if(result){
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * @author: fangl
     * @description: 获取第三方User
     * @date: 17:33 2019/1/9
     */
    public static UserPoJo qOtherUser() {
        //从request中获取
        return (UserPoJo) GlobalUtil.qHttpServletRequest().getAttribute(Global.SESSION_USER);
    }

}
