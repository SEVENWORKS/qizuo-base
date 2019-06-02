package org.qizuo.cm.frame.filter_interceptor.exception;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
import org.qizuo.cm.modules.system.pojo.LogPoJo;
import org.qizuo.cm.utils.HttpUtil;
import org.qizuo.cm.utils.JsonUtil;
import org.qizuo.cm.utils.LogUtil;
import org.qizuo.cm.utils.MobileUtil;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author: fangl
 * @Description: 异常触发拦截器(属于springmvc)
 * @Date: 10:12 2018/11/19
 */
@ControllerAdvice
public class SpringmvcException {
    /**
     * @Author: fangl
     * @Description: 默认异常处理方法(这个地方的处理主要是web端的拦截, 移动端请求请自行catch)
     * @Date: 0:04 2018/11/25
     */
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class, UncategorizedSQLException.class})
    public String defaultHandler(HttpServletRequest request, HttpServletResponse response, final Exception e) {
        //是否打印异常
        if (StringUtils.equals(Global.YES, Global.EXCEPTION_PRINT)) {
            e.printStackTrace();
        }
        //是否保存异常
        if (StringUtils.equals(Global.YES, Global.EXCEPTION_SAVE)) {
            LogPoJo logPoJo = new LogPoJo();
            logPoJo.setTypeCd(Global.LOG_TYPE_EXCEPTION);

            //转换其它信息
            String userAgent = MobileUtil.getUserAgent(request).toString();
            String requestParam = HttpUtil.getParamsChar(request);
            String requestBody = HttpUtil.getRequestBodyChar(request);
            String requestMethod = HttpUtil.getRequestMethod(request);

            logPoJo.setContent(getStackTraceAsString(e) + "\n"
                    + "*************************************" + "\n"
                    + "userAgent:" + userAgent + "\n"
                    + "requestParam:" + requestParam + "\n"
                    + "requestBody:" + requestBody + "\n"//这个requestbody和requestparam是对立的，一般只会保存一个
                    + "requestMethod:" + requestMethod + "\n");
            LogUtil.insert(logPoJo);
        }
        //判断是否是ajax请求
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return JsonUtil.renderString(response, new BackResultPoJo(BackResultPoJo.ERROR, "系统错误"));
        } else {
            return "/base/404";
        }
    }

    /**
     * @author: fangl
     * @description: 将ErrorStack转化为String
     * @date: 14:44 2019/1/11
     */
    public static String getStackTraceAsString(Throwable e) {
        if (e == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
