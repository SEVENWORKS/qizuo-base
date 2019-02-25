package org.qizuo.cm.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: fangl
 * @description: http工具
 * @date: 8:57 2019/1/14
 */
public class HttpUtil {
    /** *****************************************HttpServletRequest操作***************************************** */
    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    /*public static String getIpAddress(HttpServletRequest request){
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }*/

    /**
     * @author: fangl
     * @description: 举例
     * @date: 19:48 2019/2/13
     */
    public void requestForExample(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //返回请求行中的资源名称
        String uri = request.getRequestURI();
        //获得客户端发送请求的完整url
        String url = request.getRequestURL().toString();
        //返回发出请求的IP地址
        String ip = request.getRemoteAddr();
        //返回请求行中的参数部分
        String params = request.getQueryString();
        //返回发出请求的客户机的主机名
        String host=request.getRemoteHost();
        //返回发出请求的客户机的端口号
        int port =request.getRemotePort();
     }

    /**
     * 获取所有请求头
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>(16);
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
    public static String getHeadersChar(HttpServletRequest request) {
        String headers = "";
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headers += key + "=" + value + "\r\n";
        }
        return headers;
    }

    /**
     * 获取method （大写）
     */
    public static String getRequestMethod(HttpServletRequest request) {
        String sMethod = request.getMethod();
        return sMethod.toUpperCase();
    }

    /**
     * 获取参数(相当于请求体)
     */
    public static Map<String,String> getParams(HttpServletRequest request){
        TreeMap<String, String> values = new TreeMap<String, String>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name =(String) paramNames.nextElement();
            String value = request.getParameter(name);
            values.put(name, value);
        }
        return values;
    }
    public static String getParamsChar(HttpServletRequest request){
        String params = "";
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = (String) paramNames.nextElement();
            String value = request.getParameter(name);
            params += "&" + name + "=" + value;
        }
        return params;
    }


    /**
     * 请求体二进制读取
     */
    public static InputStream getRequestBody(HttpServletRequest request) {
        InputStream requestEntity = null;
        try {
            requestEntity = request.getInputStream();
        }
        catch (IOException ex) {
        }
        return requestEntity;
    }
    /**
     * 请求体字符串读取
     */
    public static String getRequestBodyChar(HttpServletRequest request) {
        try {
            BufferedReader br = request.getReader();
            String str, wholeStr = "";
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
            return wholeStr;
        }catch (Exception e){

        }
        return "";
    }

    /** *****************************************http请求发送***************************************** */


}
