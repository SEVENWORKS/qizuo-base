package org.qizuo.cm.modules.system.dao;

import org.qizuo.cm.modules.base.dao.BaseDao;
import org.qizuo.cm.modules.system.pojo.LogPoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author: fangl
 * @Description: 日志
 * @Date: 11:42 2019/1/1
 */
@Repository
public interface LogDao extends BaseDao<LogPoJo>{
    
}
