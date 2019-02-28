package org.qizuo.cm.modules.system.controller;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
import org.qizuo.cm.modules.base.pojo.PagePoJo;
import org.qizuo.cm.modules.system.pojo.RolePoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.qizuo.cm.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: fangl
 * @description: 用户控制器
 * @date: 14:09 2018/10/29
 */
@RequestMapping(value = "${url_module}/system/user/",method = RequestMethod.POST)
@Controller
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * @author: fangl
     * @description: 用户分页列表
     * @date: 15:02 2019/1/9
     */
    @RequestMapping("page")
    public BackResultPoJo page(PagePoJo<UserPoJo> poJos,UserPoJo userPoJo){
        poJos.setEntity(userPoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"获取成功",userService.qPageFL(poJos));
    }

    /**
     * @author: fangl
     * @description: 用户分页列表
     * @date: 15:02 2019/1/9
     */
    @RequestMapping("list")
    public BackResultPoJo list(UserPoJo userPoJo){
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"获取成功",userService.qList(userPoJo));
    }

    /**
     * @author: fangl
     * @description: 查找单个
     * @date: 9:07 2019/2/13
     */
    @RequestMapping("query")
    public BackResultPoJo query(UserPoJo userPoJo){
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"获取成功",userService.query(userPoJo));
    }

    /**
     * @author: fangl
     * @description: 增加或者修改用户
     * @date: 15:02 2019/1/9
     */
    @RequestMapping("iuDo")
    public BackResultPoJo iuDo(HttpServletRequest httpServletRequest, UserPoJo userPoJo){
        if(StringUtils.isBlank(userPoJo.getBaseId())){
            //插入准备
            userPoJo.preIDo(httpServletRequest);
            //插入
            userService.insert(userPoJo);
        }else{
            //更新准备
            userPoJo.preUDo(httpServletRequest);
            //更新
            userService.update(userPoJo);
        }
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"操作成功",null);
    }
    
    /**
     * @author: fangl
     * @description: 删除用户
     * @date: 15:02 2019/1/9
     */
    @RequestMapping("delete")
    public BackResultPoJo delete(UserPoJo userPoJo){
        userPoJo.setBaseStatus(Global.STATUS_NO);
        userService.uStatus(userPoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"操作成功",null);
    }
}