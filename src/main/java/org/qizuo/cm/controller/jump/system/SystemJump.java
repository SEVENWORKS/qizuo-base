package org.qizuo.cm.controller.jump.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: fangl
 * @Description: 系统跳转控制器
 * @Date: 18:01 2018/10/30
 */
@RequestMapping(value = "${url_jump}/system/sys/", method = RequestMethod.GET)
@Controller
public class SystemJump {
    /**
     * @Author: fangl
     * @Description: 字典表
     * @Date: 16:49 2018/11/2
     */
    @RequestMapping("dict")
    public String dict() {
        return "/system/system_dict";
    }

    /**
     * @Author: fangl
     * @Description: 字典表插入和编辑
     * @Date: 16:49 2018/11/2
     */
    @RequestMapping("dictDo")
    public String dictDo(String baseId, Model model) {
        model.addAttribute("baseId", baseId);
        return "/system/system_dict_do";
    }

    /**
     * @Author: fangl
     * @Description: 日志
     * @Date: 16:49 2018/11/2
     */
    @RequestMapping("log")
    public String log() {
        return "/system/system_log";
    }

    /**
     * @Author: fangl
     * @Description: 日志插入和编辑
     * @Date: 16:49 2018/11/2
     */
    @RequestMapping("logDo")
    public String logDo(String baseId, Model model) {
        model.addAttribute("baseId", baseId);
        return "/system/system_log_do";
    }

    /**
     * @Author: fangl
     * @Description: 资源菜单
     * @Date: 16:49 2018/11/2
     */
    @RequestMapping("menu")
    public String menu() {
        return "/system/system_menu";
    }

    /**
     * @Author: fangl
     * @Description: 资源菜单插入和编辑
     * @Date: 16:49 2018/11/2
     */
    @RequestMapping("menuDo")
    public String menuDo(String baseId, String parentId, Model model) {
        model.addAttribute("baseId", baseId);
        //父节点
        model.addAttribute("parentId", parentId);
        return "/system/system_menu_do";
    }

    /**
     * @Author: fangl
     * @Description: 角色
     * @Date: 16:49 2018/11/2
     */
    @RequestMapping("role")
    public String role() {
        return "/system/system_role";
    }

    /**
     * @Author: fangl
     * @Description: 角色插入和编辑
     * @Date: 16:49 2018/11/2
     */
    @RequestMapping("roleDo")
    public String roleDo(String baseId, Model model) {
        model.addAttribute("baseId", baseId);
        return "/system/system_role_do";
    }

    /**
     * @Author: fangl
     * @Description: 用户管理
     * @Date: 16:49 2018/11/2
     */
    @RequestMapping("user")
    public String user() {
        return "/system/system_user";
    }

    /**
     * @Author: fangl
     * @Description: 用户管理插入和编辑
     * @Date: 16:49 2018/11/2
     */
    @RequestMapping("userDo")
    public String userDo(String baseId, Model model) {
        model.addAttribute("baseId", baseId);
        return "/system/system_user_do";
    }

    /**
     * @author: fangl
     * @description: 消息发送
     * @date: 8:56 2019/2/18
     */
    @RequestMapping("msgDo")
    public String msgDo(String baseId, Model model) {
        model.addAttribute("baseId", baseId);
        return "/system/system_msg_do";
    }

}
