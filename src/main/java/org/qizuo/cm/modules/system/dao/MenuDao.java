package org.qizuo.cm.modules.system.dao;

import org.qizuo.cm.modules.base.dao.BaseDao;
import org.qizuo.cm.modules.system.pojo.MenuPoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 菜单
 * @Date: 11:42 2019/1/1
 */
@Repository
public interface MenuDao extends BaseDao<MenuPoJo> {

    /**
     * @author: fangl
     * @description: 查询菜单递归列表
     * @date: 17:44 2019/1/9
     */
    List<MenuPoJo> qEachList(MenuPoJo menuPoJo);


}
