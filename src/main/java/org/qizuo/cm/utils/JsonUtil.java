package org.qizuo.cm.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: fangl
 * @description: json常用处理工具
 * @date: 14:42 2019/1/11
 */
public class JsonUtil {
    /**
     * @author: fangl
     * @description: 将BackResult转换成json串返回
     * @date: 14:42 2019/1/11
     */
    public static String renderString(HttpServletResponse response, Object object) {
        String jsonString = JSONObject.toJSONString(object);
        try {
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(jsonString);
        } catch (IOException e) {
        }
        return null;
    }
}
