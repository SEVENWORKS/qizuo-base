package org.qizuo.cm.utils;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.modules.system.pojo.LogPoJo;
import org.qizuo.cm.modules.system.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author :fangl
 * @Description: 日志工具
 * @Date:14:12 2018/10/29
 */
public class LogUtil {
    /**
     * 日志jdbc操作对象
     */
    private static LogService logService = SpringUtils.getBean(LogService.class);

    /**
     * @author: fangl
     * @description: 日志slf4j对象获取
     * logger.error("[info message]");
     * logger.info("[info message]{},{},{},{}", "abc", false, 123,  new Slf4jTest());
     * logger.debug("[debug message]");
     * logger.trace("[trace message]");
     * @date: 17:03 2019/2/22
     */
    public static Logger getLogger(Class clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * @author: fangl
     * @description: 插入日志
     * @date: 17:37 2019/1/9
     */
    public static boolean insert(LogPoJo logPoJo) {
        //先判断开关
        if (StringUtils.equals(Global.YES, Global.LOG_SAVE)) {
            return logService.insert(logPoJo);
        } else {
            return true;
        }
    }

}
