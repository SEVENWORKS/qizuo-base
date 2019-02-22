package org.qizuo.cm.modules.system.controller;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
import org.qizuo.cm.modules.base.pojo.PagePoJo;
import org.qizuo.cm.modules.system.pojo.LogPoJo;
import org.qizuo.cm.modules.system.pojo.MenuPoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.qizuo.cm.modules.system.service.MenuService;
import org.qizuo.cm.utils.SessionUtil;
import org.qizuo.cm.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: fangl
 * @description: 菜单控制器
 * @date: 14:09 2018/10/29
 */
@RequestMapping(value = "/admin/system/menu/",method = RequestMethod.POST)
@Controller
@ResponseBody
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * @author: fangl
     * @description: 获取相应菜单列表
     * @date: 17:10 2019/1/8
     */
    @RequestMapping("list")
    public BackResultPoJo list(MenuPoJo menuPoJo){
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"获取成功",menuService.qList(menuPoJo));
    }

    /**
     * @author: fangl
     * @description: 根据用户获取菜单递归列表
     * @date: 17:10 2019/1/8
     */
    @RequestMapping("qEachList")
    public BackResultPoJo qEachList(MenuPoJo menuPoJo){
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"获取成功",menuService.qEachList(menuPoJo));
    }

    /**
     * @author: fangl
     * @description: 菜单分页列表
     * @date: 15:04 2019/1/9
     */
    @RequestMapping("page")
    public BackResultPoJo page(PagePoJo<MenuPoJo> pagePoJo,MenuPoJo menuPoJo){
        pagePoJo.setEntity(menuPoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"获取成功",menuService.qPageFL(pagePoJo));
    }

    /**
     * @author: fangl
     * @description: 查找单个
     * @date: 9:07 2019/2/13
     */
    @RequestMapping("query")
    public BackResultPoJo query(MenuPoJo menuPoJo){
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"获取成功",menuService.query(menuPoJo));
    }

    /**
     * @author: fangl
     * @description: 菜单新增或者修改
     * @date: 15:04 2019/1/9
     */
    @RequestMapping("iuDo")
    public BackResultPoJo iuDo(HttpServletRequest httpServletRequest,MenuPoJo menuPoJo){
        if(StringUtils.isBlank(menuPoJo.getBaseId())){
            //插入准备
            menuPoJo.preIDo(httpServletRequest);
            //插入
            menuService.insert(menuPoJo);
        }else{
            //更新准备
            menuPoJo.preUDo(httpServletRequest);
            //更新
            menuService.update(menuPoJo);
        }
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"操作成功",null);
    }

    /**
     * @author: fangl
     * @description: 菜单删除
     * @date: 15:04 2019/1/9
     */
    @RequestMapping("delete")
    public BackResultPoJo delete(MenuPoJo menuPoJo){
        menuService.delete(menuPoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"删除成功",null);
    }

}
