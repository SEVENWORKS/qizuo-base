package org.qizuo.cm.modules.system.dao;

import org.qizuo.cm.modules.base.dao.BaseDao;
import org.qizuo.cm.modules.system.pojo.MsgPoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 消息
 * @Date: 11:42 2019/1/1
 */
@Repository
public interface MsgDao extends BaseDao<MsgPoJo>{
    /**
     * @author: fangl
     * @description: 更新成已读状态
     * @date: 10:01 2019/2/18
     */
    boolean uRead(MsgPoJo msgPoJo);

    /**
     * @author: fangl
     * @description: 插入多条
     * @date: 10:37 2019/2/19
     */
    boolean ibatch(MsgPoJo msgPoJo);
}
