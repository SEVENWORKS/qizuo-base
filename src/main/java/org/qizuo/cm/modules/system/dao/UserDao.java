package org.qizuo.cm.modules.system.dao;

import org.qizuo.cm.modules.base.dao.BaseDao;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.springframework.stereotype.Component;

/**
 * @Author: fangl
 * @Description: 用户
 * @Date: 11:42 2019/1/1
 */
public interface UserDao extends BaseDao<UserPoJo> {
    /**
     * @author: fangl
     * @description: 根据用户名称获取用户信息
     * @date: 16:01 2019/1/10
     */
    UserPoJo qPasswordByName(String username);

    /**
     * @author: fangl
     * @description: 查询单个用户所有信息
     * @date: 10:28 2019/1/14
     */
    UserPoJo qUserAllMsg(UserPoJo userPoJo);
}
