package org.qizuo.cm.utils;

import org.qizuo.cm.modules.system.pojo.MsgPoJo;
import org.qizuo.cm.modules.system.service.MsgService;

/**
 * @Author :fangl
 * @Description: 消息工具
 * @Date:14:12 2018/10/29
 */
public class MsgUtil {
    /**
     * 日志jdbc操作对象
     */
    private static MsgService msgService = SpringUtils.getBean(MsgService.class);

    /**
     * @author: fangl
     * @description: 插入消息
     * @date: 17:37 2019/1/9
     */
    public static boolean insert(MsgPoJo msgPoJo) {
        msgPoJo.preIDo();
        return msgService.insert(msgPoJo);
    }

}
