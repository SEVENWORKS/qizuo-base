package org.qizuo.cm.modules.system.dao;

import org.qizuo.cm.modules.base.dao.BaseDao;
import org.qizuo.cm.modules.system.pojo.DictItemPoJo;
import org.qizuo.cm.modules.system.pojo.DictPoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 字典字表
 * @Date: 11:42 2019/1/1
 */
@Repository
public interface DictItemDao extends BaseDao<DictItemPoJo> {
    /**
     * @author: fangl
     * @description: 批量插入
     * @date: 10:38 2019/2/12
     */
    boolean insert(DictPoJo dictPoJo);

    /**
     * @author: fangl
     * @description: 更新状态
     * @date: 10:40 2019/2/12
     */
    @Override
    boolean uStatus(DictItemPoJo dictItemPoJo);
}
