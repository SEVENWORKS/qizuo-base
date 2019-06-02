package org.qizuo.cm.controller.jump.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: fangl
 * @Description: 系统基本跳转控制器
 * @Date: 18:01 2018/10/30
 */
@RequestMapping(value = "${url_jump}/system/base/", method = RequestMethod.GET)
@Controller
public class SystemBaseJump {
    /**
     * @Author: fangl
     * @Description: qizuo后台登录
     * @Date: 16:47 2018/11/2
     */
    @RequestMapping("login")
    public String login() {
        return "/base/base_login";
    }

    /**
     * @Author: fangl
     * @Description: 页面框架体跳转
     * @Date: 16:48 2018/11/2
     */
    @RequestMapping("container")
    public String container() {
        return "/base/base_container";
    }
}
