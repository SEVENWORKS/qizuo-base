package org.qizuo.cm.modules.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
import org.qizuo.cm.modules.base.pojo.PagePoJo;
import org.qizuo.cm.modules.system.pojo.DictPoJo;
import org.qizuo.cm.modules.system.pojo.LogPoJo;
import org.qizuo.cm.modules.system.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author: fangl
 * @description: 日志控制器
 * @date: 14:09 2018/10/29
 */
@RequestMapping(value = "/admin/system/log/",method = RequestMethod.POST)
@Controller
@ResponseBody
public class LogController {
    @Autowired
    private LogService logService;
    /**
     * @author: fangl
     * @description: 日志分页列表
     * @date: 15:01 2019/1/9
     */
    @RequestMapping("page")
    public BackResultPoJo page(PagePoJo<LogPoJo> pagePoJo,LogPoJo logPoJo){
        logPoJo.setOrderBy("a.BASE_CREATE_TM desc");
        pagePoJo.setEntity(logPoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"成功",logService.qPageFL(pagePoJo));
    }

    /**
     * @author: fangl
     * @description: 查找单个
     * @date: 9:07 2019/2/13
     */
    @RequestMapping("query")
    public BackResultPoJo query(LogPoJo logPoJo){
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"获取成功",logService.query(logPoJo));
    }
}
