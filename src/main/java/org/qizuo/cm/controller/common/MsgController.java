package org.qizuo.cm.controller.common;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
import org.qizuo.cm.modules.system.pojo.MsgPoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.qizuo.cm.modules.system.service.MsgService;
import org.qizuo.cm.utils.IDUtil;
import org.qizuo.cm.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: fangl
 * @description: 消息控制器
 * @date: 14:09 2018/10/29
 */
@RequestMapping(value = "${url_module}/system/msg/", method = RequestMethod.POST)
@Controller
@ResponseBody
public class MsgController {
    @Autowired
    private MsgService msgService;

    /**
     * @author: fangl
     * @description: 根据用户获取消息列表
     * @date: 15:06 2019/1/9
     */
    @RequestMapping("list")
    public BackResultPoJo list(MsgPoJo msgPoJo) {
        //获取当前登录人
        UserPoJo userPoJo = UserUtil.qUser();
        msgPoJo.setSendUserId(userPoJo.getBaseId());
        msgPoJo.setOrderBy("a.IS_READ");
        List<MsgPoJo> msgPoJos = msgService.qList(msgPoJo);
        //如果需要别的类型消息，请自行添加查询列表msgPoJos中，当前只查询关联人消息
        return new BackResultPoJo(BackResultPoJo.SUCCESS, "成功", msgPoJos);
    }

    /**
     * @author: fangl
     * @description: 查找单个
     * @date: 9:07 2019/2/13
     */
    @RequestMapping("query")
    public BackResultPoJo query(MsgPoJo msgPoJo) {
        return new BackResultPoJo(BackResultPoJo.SUCCESS, "获取成功", msgService.query(msgPoJo));
    }

    /**
     * @author: fangl
     * @description: 插入
     * @date: 15:06 2019/1/9
     */
    @RequestMapping("iuDo")
    public BackResultPoJo insert(MsgPoJo msgPoJo) {
        if (StringUtils.isNotBlank(msgPoJo.getSendUserIds())) {
            String[] sendUserIdAr = msgPoJo.getSendUserIds().split(",");
            if (null != sendUserIdAr && sendUserIdAr.length > 0) {
                //分解装备
                List<MsgPoJo> msgPoJos = new ArrayList<>();
                for (String sendUserId : sendUserIdAr) {
                    MsgPoJo mp = new MsgPoJo();
                    mp.setBaseId(IDUtil.nextId());
                    mp.setSendUserId(sendUserId);
                    mp.setContent(msgPoJo.getContent());
                    msgPoJos.add(mp);
                }
                //插入准备
                msgPoJo.preIDo();
                msgPoJo.setMsgPoJos(msgPoJos);
                msgService.ibatch(msgPoJo);
            }
        } else {
            //插入准备
            msgService.insert(msgPoJo);
        }
        return new BackResultPoJo(BackResultPoJo.SUCCESS, "操作成功", null);
    }

    /**
     * @author: fangl
     * @description: 修改消息状态
     * @date: 16:35 2019/1/9
     */
    @RequestMapping("delete")
    public BackResultPoJo uStatus(MsgPoJo msgPoJo) {
        msgPoJo.setBaseStatus(Global.STATUS_NO);
        msgService.uStatus(msgPoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS, "操作成功", null);
    }

    /**
     * @author: fangl
     * @description: 更改成已读(这种状态最好不要跟status这个基本字段进行冗余)
     * @date: 16:35 2019/1/9
     */
    @RequestMapping("uRead")
    public BackResultPoJo uRead(MsgPoJo msgPoJo) {
        msgPoJo.setIsRead(Global.YES);
        msgService.uRead(msgPoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS, "操作成功", null);
    }
}
