package org.qizuo.cm.modules.system.service;

import org.qizuo.cm.modules.base.service.BaseService;
import org.qizuo.cm.modules.system.dao.MenuDao;
import org.qizuo.cm.modules.system.dao.UserDao;
import org.qizuo.cm.modules.system.pojo.MenuPoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 菜单
 * @Date: 12:13 2019/1/1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuService extends BaseService<MenuDao,MenuPoJo>{

    /**
     * @author: fangl
     * @description: 菜单递归查询
     * @date: 17:42 2019/1/9
     */
    public List<MenuPoJo> qEachList(MenuPoJo menuPoJo) {
        return dao.qEachList(menuPoJo);
    }
}
