package org.qizuo.cm.modules.base.copy;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
import org.qizuo.cm.modules.base.pojo.PagePoJo;
import org.qizuo.cm.modules.system.pojo.RolePoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.qizuo.cm.modules.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: fangl
 * @description:
 * @date: 14:09 2018/10/29
 */
@RequestMapping(value = "/admin/xxxx/copy/",method = RequestMethod.POST)
@Controller
@ResponseBody
public class CopyController {
    @Autowired
    private RoleService roleService;

    /**
     * @author: fangl
     * @description: 分页列表
     * @date: 15:02 2019/1/9
     */
    @RequestMapping("page")
    public BackResultPoJo page(PagePoJo<RolePoJo> poJos, RolePoJo rolePoJo){
        poJos.setEntity(rolePoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"获取成功",roleService.qPageFL(poJos));
    }

    /**
     * @author: fangl
     * @description: 列表
     * @date: 17:10 2019/1/8
     */
    @RequestMapping("list")
    public BackResultPoJo list(RolePoJo rolePoJo){
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"获取成功",roleService.qList(rolePoJo));
    }

    /**
     * @author: fangl
     * @description: 查找单个
     * @date: 9:07 2019/2/13
     */
    @RequestMapping("query")
    public BackResultPoJo query(RolePoJo rolePoJo){
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"获取成功",roleService.query(rolePoJo));
    }

    /**
     * @author: fangl
     * @description: 新增或者修改
     * @date: 15:04 2019/1/9
     */
    @RequestMapping("iuDo")
    public BackResultPoJo iuDo(HttpServletRequest httpServletRequest, RolePoJo rolePoJo){
        if(StringUtils.isBlank(rolePoJo.getBaseId())){
            //插入准备
            rolePoJo.preIDo(httpServletRequest);
            //插入
            roleService.insert(rolePoJo);
        }else{
            //更新准备
            rolePoJo.preUDo(httpServletRequest);
            //更新
            roleService.update(rolePoJo);
        }
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"操作成功",null);
    }

    /**
     * @author: fangl
     * @description: 删除
     * @date: 15:04 2019/1/9
     */
    @RequestMapping("delete")
    public BackResultPoJo delete(RolePoJo rolePoJo){
        roleService.delete(rolePoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"删除成功",null);
    }
}
