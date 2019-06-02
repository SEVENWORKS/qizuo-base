package org.qizuo.cm.controller.common;

import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
import org.qizuo.cm.modules.base.pojo.PagePoJo;
import org.qizuo.cm.modules.system.pojo.DictPoJo;
import org.qizuo.cm.modules.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: fangl
 * @description: 字典表控制器
 * @date: 14:09 2018/10/29
 */
@RequestMapping(value = "${url_module}/system/dict/", method = RequestMethod.POST)
@Controller
@ResponseBody
public class DictController {
    @Autowired
    private DictService dictService;

    /**
     * @author: fangl
     * @description: 字典表列表
     * @date: 17:10 2019/1/8
     */
    @RequestMapping("page")
    public BackResultPoJo page(PagePoJo<DictPoJo> poJos, DictPoJo dictPoJo) {
        poJos.setEntity(dictPoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS, "获取成功", dictService.qPageQZ(poJos));
    }

    /**
     * @author: fangl
     * @description: 查找单个
     * @date: 9:07 2019/2/13
     */
    @RequestMapping("query")
    public BackResultPoJo query(DictPoJo dictPoJo) {
        return new BackResultPoJo(BackResultPoJo.SUCCESS, "获取成功", dictService.query(dictPoJo));
    }

    /**
     * @author: fangl
     * @description: 字典表新增或者修改
     * @date: 15:04 2019/1/9
     */
    @RequestMapping("iuDo")
    public BackResultPoJo iuDo(DictPoJo dictPoJo) {
        //插入或者更新
        dictService.iuBatch(dictPoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS, "操作成功", null);
    }

    /**
     * @author: fangl
     * @description: 字典表删除
     * @date: 15:04 2019/1/9
     */
    @RequestMapping("delete")
    public BackResultPoJo delete(DictPoJo dictPoJo) {
        dictService.uStatus(dictPoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS, "删除成功", null);
    }
}
