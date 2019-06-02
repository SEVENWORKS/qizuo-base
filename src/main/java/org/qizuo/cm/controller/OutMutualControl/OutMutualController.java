package org.qizuo.cm.controller.OutMutualControl;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.GlobalUtil;
import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
import org.qizuo.cm.utils.JsonUtil;
import org.qizuo.cm.utils.SessionUtil;
import org.qizuo.cm.utils.SpringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.util.Map;

/**
 * @author: fangl
 * @description: 交互中心
 * @date: 15:57 2019/6/2
 */
@RequestMapping(value = "${url_module}/outMutual/outMutual/", method = RequestMethod.POST)
@Controller
@ResponseBody
public class OutMutualController {
    /**
     * @author: fangl
     * @description: 获取数据
     * @date: 16:25 2019/1/8
     */
    @RequestMapping("qOutMutual")
    public void qOutMutual() {
        //获取数据
        JdbcTemplate jdbcTemplate = SpringUtils.getBean(JdbcTemplate.class);
        Map<String, Object> map = jdbcTemplate.queryForMap("");
        //返回
        JsonUtil.httpBackJson(GlobalUtil.qHttpServletResponse(), "");
    }

    /**
     * @author: fangl
     * @description: 插入和修改数据
     * @date: 16:25 2019/1/8
     */
    @RequestMapping("iuOutMutual")
    public void iuOutMutual(String id) {
        //获取数据
        JdbcTemplate jdbcTemplate = SpringUtils.getBean(JdbcTemplate.class);
        int back=jdbcTemplate.update("");
        //返回
        JsonUtil.httpBackJson(GlobalUtil.qHttpServletResponse(), "");
    }

    /**
     * @author: fangl
     * @description: 删除数据
     * @date: 16:25 2019/1/8
     */
    @RequestMapping("dOutMutual")
    public void dOutMutual(@RequestParam String id) {
        //获取数据
        JdbcTemplate jdbcTemplate = SpringUtils.getBean(JdbcTemplate.class);
        int back=jdbcTemplate.update("");
        //返回
        JsonUtil.httpBackJson(GlobalUtil.qHttpServletResponse(), "");
    }
}
