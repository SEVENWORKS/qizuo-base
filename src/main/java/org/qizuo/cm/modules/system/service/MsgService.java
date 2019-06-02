package org.qizuo.cm.modules.system.service;

import org.qizuo.cm.modules.base.service.BaseService;
import org.qizuo.cm.modules.system.dao.MsgDao;
import org.qizuo.cm.modules.system.dao.UserDao;
import org.qizuo.cm.modules.system.pojo.MsgPoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 消息
 * @Date: 12:13 2019/1/1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MsgService extends BaseService<MsgDao, MsgPoJo> {
    /**
     * @author: fangl
     * @description: 更新成已读状态
     * @date: 10:01 2019/2/18
     */
    public boolean uRead(MsgPoJo msgPoJo) {
        return dao.uRead(msgPoJo);
    }

    /**
     * @author: fangl
     * @description: 插入多条
     * @date: 10:32 2019/2/19
     */
    public boolean ibatch(MsgPoJo msgPoJo) {
        return dao.ibatch(msgPoJo);
    }
}
