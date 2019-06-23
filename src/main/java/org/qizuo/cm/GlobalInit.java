package org.qizuo.cm;

import org.qizuo.cm.modules.system.dao.UserDao;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.qizuo.cm.modules.system.service.UserService;
import org.qizuo.cm.utils.SpringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fangl
 * @Description: 初始化加载
 * @Date: 11:12 2018/10/30
 */
public class GlobalInit {
    /** 所有用户信息 */
    public static List<UserPoJo> userPoJos=new ArrayList<>();

    /**
     * @author: fangl
     * @description: 加载所有用户信息
     * @date: 14:12 2019/6/23
     */
    public static boolean qUserPoJos(){
        UserDao userDao = SpringUtils.getBean(UserDao.class);
        userPoJos =userDao.qList(new UserPoJo());
        return true;
    }
}
