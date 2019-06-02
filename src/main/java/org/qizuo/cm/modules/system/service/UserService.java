package org.qizuo.cm.modules.system.service;

import org.qizuo.cm.modules.base.service.BaseService;
import org.qizuo.cm.modules.system.dao.UserDao;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: fangl
 * @Description: 用户
 * @Date: 12:13 2019/1/1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends BaseService<UserDao, UserPoJo> {
    /**
     * @author: fangl
     * @description: 根据用户名获取用户信息
     * @date: 15:59 2019/1/10
     */
    public UserPoJo qPasswordByName(String username) {
        return dao.qPasswordByName(username);
    }

    /**
     * @author: fangl
     * @description: 查询单个用户所有信息
     * @date: 10:29 2019/1/14
     */
    public UserPoJo qUserAllMsg(UserPoJo userPoJo) {
        return dao.qUserAllMsg(userPoJo);
    }

}
