package org.qizuo.cm.controller.OutMutualControl;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.GlobalUtil;
import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
import org.qizuo.cm.modules.base.pojo.PagePoJo;
import org.qizuo.cm.modules.system.pojo.LogPoJo;
import org.qizuo.cm.utils.JsonUtil;
import org.qizuo.cm.utils.SpringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: fangl
 * @description: 交互中心
 * @date: 15:57 2019/6/2
 */
@RequestMapping(value = "${url_module}/outMutual/{mainSQ}/", method = RequestMethod.POST)
@Controller
@ResponseBody
public class OutMutualController {
    /**
     * @author: fangl
     * @description: 获取所有数据库表信息
     * @date: 11:33 2019/6/9
     */
    @RequestMapping("qOutMutualDatabase")
    public void qOutMutualDatabase(@PathVariable String mainSQ, @RequestParam Boolean sign) {
        //组装sql
        String sql;

        //判断查表信息还是字段信息
        if (sign) {
            sql = "select table_name from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA=" + Global.DATABASE_NAME;
        } else {
            sql = "select column_name,column_type,column_comment from INFORMATION_SCHEMA.COLUMNS where TABLE_SCHEMA=" + Global.DATABASE_NAME + " and TABLE_NAME=\'" + mainSQ + "\'";
        }

        //获取数据
        JdbcTemplate jdbcTemplate = SpringUtils.getBean(JdbcTemplate.class);
        List<Map<String, Object>> back = jdbcTemplate.queryForList(sql);

        //过滤base_
        if (null != back && back.size() > 0) {
            for (int i = 0; i < back.size(); i++) {
                Map<String, Object> map = back.get(i);
                if (null != map.get("column_name")) {
                    String columnName = (String) map.get("column_name");
                    if (columnName.contains(Global.DATABASE_COLUMN)) {
                        back.remove(i);
                        i--;
                    }
                }
            }
        }

        //返回
        JsonUtil.httpBackJson(GlobalUtil.qHttpServletResponse(), JsonUtil.objectToJson(new BackResultPoJo(BackResultPoJo.SUCCESS, back)));
    }


    /**
     * @author: fangl
     * @description: 获取数据
     * @date: 16:25 2019/1/8
     */
    @RequestMapping("qOutMutual")
    public void qOutMutual(@PathVariable String mainSQ, Integer pageNo, Integer pageSize,
                           String conditions) {
        //组装sql
        String sql = "select * from " + mainSQ + " where BASE_STATUS=0 ";

        //条件
        if (StringUtils.isNotBlank(conditions)) {
            sql += " AND " + conditions;
        }

        //分页
        if (null != pageNo && null != pageSize) {
            sql += " limit " + ((pageNo - 1) * pageSize) + "," + pageSize;
        }

        //获取数据
        JdbcTemplate jdbcTemplate = SpringUtils.getBean(JdbcTemplate.class);
        List<Map<String, Object>> back = jdbcTemplate.queryForList(sql);

        //过滤base_
        if (null != back && back.size() > 0) {
            for (int i = 0; i < back.size(); i++) {
                Map<String, Object> map = back.get(i);
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    if (key.contains(Global.DATABASE_COLUMN) && !key.equals("BASE_ID")) {
                        iterator.remove();
                    }
                }
            }
        }

        //返回
        BackResultPoJo backResultPoJo;
        if (null != pageNo && null != pageSize) {
            //分页封装
            PagePoJo<Map<String, Object>> pagePoJo = new PagePoJo<>();
            pagePoJo.setPageNo(pageNo);
            pagePoJo.setPageSize(pageSize);
            pagePoJo.setEntitys(back);

            //获取总数
            String sqlCount = "select count(*) from " + mainSQ;

            //条件
            if (StringUtils.isNotBlank(conditions)) {
                sqlCount += " where " + conditions;
            }

            //查询塞入
            Integer count = jdbcTemplate.queryForObject(sqlCount, Integer.class);
            pagePoJo.setTotalCount(count);

            backResultPoJo = new BackResultPoJo(BackResultPoJo.SUCCESS, pagePoJo);
        } else {
            backResultPoJo = new BackResultPoJo(BackResultPoJo.SUCCESS, back);
        }
        JsonUtil.httpBackJson(GlobalUtil.qHttpServletResponse(), JsonUtil.objectToJson(backResultPoJo));
    }

    /**
     * @author: fangl
     * @description: 插入和修改数据
     * @date: 16:25 2019/1/8
     */
    @RequestMapping("iuOutMutual")
    public void iuOutMutual(@PathVariable String mainSQ, @RequestParam(required = false) Map<String, String> dataMap,
                            String conditions) {
        if (null != dataMap && dataMap.size() > 0) {
            //组装sql
            String sql;

            if (StringUtils.isBlank(dataMap.get("baseId"))) {
                //插入
                sql = "insert into " + mainSQ + GlobalUtil.qJdbcTemplateIn(dataMap);
            } else {
                //去除conditions
                dataMap.remove("conditions");
                //更新
                sql = "update " + mainSQ + " set " + GlobalUtil.qJdbcTemplateUpd(dataMap);
            }

            //条件
            if (StringUtils.isNotBlank(conditions)) {
                sql += " where " + conditions;
            }

            //获取数据
            JdbcTemplate jdbcTemplate = SpringUtils.getBean(JdbcTemplate.class);
            int back = jdbcTemplate.update(sql);

            //返回
            BackResultPoJo backResultPoJo;
            if (back == BackResultPoJo.SUCCESS) {
                backResultPoJo = new BackResultPoJo(BackResultPoJo.SUCCESS, "成功");
            } else {
                backResultPoJo = new BackResultPoJo(BackResultPoJo.FAILURE, "失败");
            }
            JsonUtil.httpBackJson(GlobalUtil.qHttpServletResponse(), JsonUtil.objectToJson(backResultPoJo));
        }
    }

    /**
     * @author: fangl
     * @description: 删除数据
     * @date: 16:25 2019/1/8
     */
    @RequestMapping("dOutMutual")
    public void dOutMutual(@PathVariable String mainSQ, @RequestParam Boolean really,
                           String conditions) {
        //组装sql
        String sql;

        if (really) {
            //删除
            sql = "delete from " + mainSQ;
        } else {
            sql = "update " + mainSQ + " set base_status = 1";
        }

        //条件
        if (StringUtils.isNotBlank(conditions)) {
            sql += " where " + conditions;
        }

        //删除数据
        JdbcTemplate jdbcTemplate = SpringUtils.getBean(JdbcTemplate.class);
        int back = jdbcTemplate.update(sql);


        //返回
        BackResultPoJo backResultPoJo;
        if (back == BackResultPoJo.SUCCESS) {
            backResultPoJo = new BackResultPoJo(BackResultPoJo.SUCCESS, "删除成功");
        } else {
            backResultPoJo = new BackResultPoJo(BackResultPoJo.FAILURE, "删除失败");
        }
        JsonUtil.httpBackJson(GlobalUtil.qHttpServletResponse(), JsonUtil.objectToJson(backResultPoJo));
    }
}
