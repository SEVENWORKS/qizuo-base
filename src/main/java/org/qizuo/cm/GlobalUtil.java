package org.qizuo.cm;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.modules.system.pojo.MenuPoJo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public static List<MenuPoJo> treeClean(String id, List<MenuPoJo> treeList) {
        //初次判断
        if (null == treeList || treeList.size() == 0) {
            return null;
        }
        //返回列表
        List<MenuPoJo> backList = new ArrayList();
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
        if (null == backList || backList.size() == 0) {
            return null;
        }
        //递归
        for (MenuPoJo menuPoJo : backList) {
            menuPoJo.setMenuPoJos(treeClean(menuPoJo.getBaseId(), treeList));
        }
        return backList;
    }

    /**
     * @author: fangl
     * @description: 根据springmvc过滤器获取request
     * @date: 13:24 2019/6/2
     */
    public static HttpServletRequest qHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * @author: fangl
     * @description: 根据springmvc过滤器获取reSponse
     * @date: 13:24 2019/6/2
     */
    public static HttpServletResponse qHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * @author: fangl
     * @description: jdbcTemplate update拼接参数
     * @date: 23:36 2019/6/8
     */
    public static String qJdbcTemplateUpd(Map<String, String> data) {
        //返回
        String back = "";

        //拼接
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                back += entry.getKey() + "=\'" + entry.getValue() + "\',";
            }
        }

        //去除
        back = back.substring(0, back.length() - 1);

        return back;
    }

    /**
     * @author: fangl
     * @description: jdbcTemplate insert拼接参数
     * @date: 23:36 2019/6/8
     */
    public static String qJdbcTemplateIn(Map<String, String> data) {
        //返回
        String back = "(";

        //遍历key
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                back += entry.getKey() + ",";
            }
        }

        //去除
        back = back.substring(0, back.length() - 1);

        back += ") values (";

        //遍历value
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                back += "\'" + entry.getValue() + "\',";
            }
        }

        //去除
        back = back.substring(0, back.length() - 1);

        back += ")";

        return back;
    }
}
