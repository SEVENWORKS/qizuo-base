package org.qizuo.cm.modules.system.service;

import org.qizuo.cm.modules.base.service.BaseService;
import org.qizuo.cm.modules.system.dao.DictItemDao;
import org.qizuo.cm.modules.system.dao.UserDao;
import org.qizuo.cm.modules.system.pojo.DictItemPoJo;
import org.qizuo.cm.modules.system.pojo.DictPoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 字典主表
 * @Date: 12:13 2019/1/1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DictItemService extends BaseService<DictItemDao,DictItemPoJo>{
    /**
     * @author: fangl
     * @description: 批量插入
     * @date: 10:37 2019/2/12
     */
    public boolean insert(DictPoJo dictPoJo){
        return dao.insert(dictPoJo);
    }

    /**
     * @author: fangl
     * @description: 更新状态
     * @date: 10:41 2019/2/12
     */
    @Override
    public boolean uStatus(DictItemPoJo dictItemPoJo){
        return dao.uStatus(dictItemPoJo);
    }
}
