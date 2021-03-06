package org.qizuo.cm.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: fangl
 * @description: json常用处理工具
 * @date: 14:42 2019/1/11
 */
public class JsonUtil {
    /**
     * @author: fangl
     * @description: http返回json串，不借用springmvc的视图返回器转换了
     * @date: 14:42 2019/1/11
     */
    public static void httpBackJson(HttpServletResponse response, String jsonString) {
        try {
            //返回体设置
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            //流数据返回
            PrintWriter printWriter = response.getWriter();
            printWriter.print(jsonString);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
        }
    }

    /**
     * @author: fangl
     * @description: json串转换成功对象(jackSon)
     * @date: 22:13 2019/6/8
     */
    public static Object jsonToObject(String json) {
        return JSON.parse(json);
    }

    /**
     * @author: fangl
     * @description: 对象转换成json串(jackSon)
     * @date: 22:13 2019/6/8
     */
    public static String objectToJson(Object o) {
        return JSON.toJSONString(o, SerializerFeature.WriteMapNullValue);
    }
}
