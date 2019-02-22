package org.qizuo.cm.modules.system.dao;

import org.qizuo.cm.modules.base.dao.BaseDao;
import org.qizuo.cm.modules.system.pojo.DictPoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author: fangl
 * @Description: 字典主表
 * @Date: 11:42 2019/1/1
 */
@Repository
public interface DictDao extends BaseDao<DictPoJo>{
    /**
     * @author: fangl
     * @description: 更新状态
     * @date: 10:40 2019/2/12
     */
    boolean uStatus(DictPoJo dictPoJo);
}
