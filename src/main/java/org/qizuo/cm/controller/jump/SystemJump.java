package org.qizuo.cm.controller.jump;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: fangl
 * @Description: 系统跳转控制器
 * @Date: 18:01 2018/10/30
 */
@RequestMapping(value = "${url_jump}/system/", method = RequestMethod.GET)
@Controller
public class SystemJump {
    /**
     * @Author: fangl
     * @Description: qizuo后台登录
     * @Date: 16:47 2018/11/2
     */
    @RequestMapping("login")
    public String login() {
        return "/frame/base_login";
    }

    /**
     * @Author: fangl
     * @Description: 页面框架体跳转
     * @Date: 16:48 2018/11/2
     */
    @RequestMapping("container")
    public String container() {
        return "/frame/base_container";
    }

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

    /**
     * @author: fangl
     * @description: 库列表
     * @date: 8:56 2019/2/18
     */
    @RequestMapping("dataBase")
    public String dataBase() {
        return "/outMutualControl/dataBase";
    }

    /**
     * @author: fangl
     * @description: 单表字段信息
     * @date: 8:56 2019/2/18
     */
    @RequestMapping("dataBaseDo")
    public String dataBaseDo(String tName, Model model) {
        model.addAttribute("tName", tName);
        return "/outMutualControl/dataBase_do";
    }

    /**
     * @author: fangl
     * @description: 单表数据列表
     * @date: 8:56 2019/2/18
     */
    @RequestMapping("dataBaseTable")
    public String dataBaseTable(String tName, Model model) {
        model.addAttribute("tName", tName);
        return "/outMutualControl/dataBase_table";
    }

    /**
     * @author: fangl
     * @description: 添加或者修改
     * @date: 8:56 2019/2/18
     */
    @RequestMapping("dataBaseTableDo")
    public String dataBaseTableDo(String baseId, String tName, Model model) {
        model.addAttribute("baseId", baseId);
        model.addAttribute("tName", tName);
        return "/outMutualControl/dataBase_table_do";
    }

}
