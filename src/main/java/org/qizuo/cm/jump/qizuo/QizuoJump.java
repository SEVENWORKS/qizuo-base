package org.qizuo.cm.jump.qizuo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: fangl
 * @Description: QIZUO
 * @Date: 18:01 2018/10/30
 */
@RequestMapping(value = "${url_jump}/qizuo/qizuo/",method = RequestMethod.GET)
@Controller
public class QizuoJump {
    /**
     * @Author: fangl
     * @Description: qizuo后台登录
     * @Date: 16:47 2018/11/2
     */
    @RequestMapping("list")
    public String login(){
        return "/qizuo/qizuo_qizuo";
    }

}
