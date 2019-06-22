package org.qizuo.cm.controller.jump;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: fangl
 * @Description: QIZUO
 * @Date: 18:01 2018/10/30
 */
@RequestMapping(value = "${url_jump}/qizuo/", method = RequestMethod.GET)
@Controller
public class QizuoJump {
    /**
     * @Author: fangl
     * @Description: 主页登录
     * @Date: 16:47 2018/11/2
     */
    @RequestMapping("list")
    public String login() {
        return "/qizuo/qizuo_qizuo";
    }

    /** ********************************前端****************************************** */
    /**
     * upload
     */
    @RequestMapping("upload_layui")
    public String upload_layui() {
        return "/qizuo/before/upload/layui";
    }

    @RequestMapping("upload_jqueryUploadify")
    public String upload_jqueryUploadify() {
        return "/qizuo/before/upload/jqueryUploadify";
    }

    /**
     * alert
     */
    @RequestMapping("alert_layui")
    public String alert_layui() {
        return "/qizuo/before/alert/layui";
    }

    @RequestMapping("alert_artDialog")
    public String alert_artDialog() {
        return "/qizuo/before/alert/artDialog";
    }

    /**
     * time
     */
    @RequestMapping("time_layui")
    public String time_layui() {
        return "/qizuo/before/time/layui";
    }

    /**
     * select
     */
    @RequestMapping("select_bootstrapSelectpicker")
    public String select_bootstrapSelectpicker() {
        return "/qizuo/before/select/bootstrapSelectpicker";
    }

    @RequestMapping("select_chosen")
    public String select_chosen() {
        return "/qizuo/before/select/chosen";
    }

    /**
     * tree
     */
    @RequestMapping("tree_ztree")
    public String tree_ztree() {
        return "/qizuo/before/tree/ztree";
    }

    /**
     * map
     */
    @RequestMapping("map_baidu")
    public String map_baidu() {
        return "/qizuo/before/map/baidu";
    }

    /**
     * chart
     */
    @RequestMapping("chart_echarts")
    public String chart_echarts() {
        return "/qizuo/before/chart/echarts";
    }

    /**
     * template
     */
    @RequestMapping("template_artTemplate")
    public String template_artTemplate() {
        return "/qizuo/before/template/artTemplate";
    }

    @RequestMapping("template_jqueryTemplate")
    public String template_jqueryTemplate() {
        return "/qizuo/before/template/jqueryTemplate";
    }
    /** slide */

    /**
     * textarea
     */
    @RequestMapping("textarea_autosize")
    public String textarea_autosize() {
        return "/qizuo/before/textarea/autosize";
    }

    @RequestMapping("textarea_scroll")
    public String textarea_scroll() {
        return "/qizuo/before/textarea/scroll";
    }

    @RequestMapping("textarea_simditor")
    public String textarea_simditor() {
        return "/qizuo/before/textarea/simditor";
    }

    @RequestMapping("textarea_ueditor")
    public String textarea_ueditor() {
        return "/qizuo/before/textarea/ueditor";
    }

    @RequestMapping("textarea_markedown")
    public String textarea_markedown() {
        return "/qizuo/before/textarea/markedown";
    }

    @RequestMapping("textarea_wysiwyeEditor")
    public String textarea_wysiwyeEditor() {
        return "/qizuo/before/textarea/wysiwyeEditor";
    }

    /**
     * formValidate
     */
    @RequestMapping("formValidate_jqueryValidate")
    public String formValidate_jqueryValidate() {
        return "/qizuo/before/formValidate/jqueryValidate";
    }

    /**
     * css
     */
    @RequestMapping("css_css2")
    public String css_css2() {
        return "/qizuo/before/css/css2";
    }

    @RequestMapping("css_css3")
    public String css_css3() {
        return "/qizuo/before/css/css3";
    }

    /**
     * html
     */
    @RequestMapping("html_html")
    public String html_html() {
        return "/qizuo/before/html/html";
    }

    @RequestMapping("html_html5")
    public String css_html5() {
        return "/qizuo/before/html/html5";
    }

    /** ********************************后端****************************************** */

    /** ********************************移动端****************************************** */

}
