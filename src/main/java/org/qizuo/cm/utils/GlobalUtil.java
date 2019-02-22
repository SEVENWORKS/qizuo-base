package org.qizuo.cm.utils;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.modules.system.pojo.MenuPoJo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: fangl
 * @description: 常用小工具
 * @date: 9:49 2019/1/15
 */
public class GlobalUtil {
    /**
     * @author: fangl
     * @description: 树形菜单整理示例
     * @date: 9:50 2019/1/15
     */
    public static List<MenuPoJo> treeClean(String id,List<MenuPoJo> treeList){
        //初次判断
        if(null==treeList||treeList.size()==0){
            return null;
        }
        //返回列表
        List<MenuPoJo> backList=new ArrayList();
        //初次筛选
        for (MenuPoJo menuPoJo : treeList) {
            //对比id和parentId
            if (StringUtils.isNotBlank(menuPoJo.getParentId())) {
                if (id.equals(menuPoJo.getParentId())) {
                    backList.add(menuPoJo);
                }
            }
        }
        //判断
        if (null==backList||backList.size() == 0) {
            return null;
        }
        //递归
        for (MenuPoJo menuPoJo : backList) {
            menuPoJo.setMenuPoJos(treeClean(menuPoJo.getBaseId(), treeList));
        }
        return backList;
    }
}
