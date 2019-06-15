package org.qizuo.cm;

import org.qizuo.cm.modules.base.pojo.BasePoJo;
import org.qizuo.cm.utils.IDUtil;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 全局变量和全局方法
 * @Date: 11:12 2018/10/30
 */
public class Global {
    /**
     * 全局YES
     */
    public static final String YES = "1";
    /**
     * 全局NO
     */
    public static final String NO = "0";
    /**
     * 有效
     */
    public static final String STATUS_YES = "0";
    /**
     * 无效
     */
    public static final String STATUS_NO = "1";
    /**
     * 树形菜单首节点配置
     */
    public static final String TREE_FIRST = "0";

    /** ***********************登录配置*************************** */
    /**
     * 是否单个登录
     */
    public static final String LOGIN_ONLY = "1";
    /**
     * login url
     */
    public static final String LOGIN_URL = "/jump/system/base/login";
    /**
     * login check
     */
    public static final String LOGIN_CHECK = "system/login/loginCheck";
    /**
     * 基本login跳转url
     */
    public static final String LOGIN_CHANGE_URL = "/jump/system/base/container";

    /** *********************session配置************************** */
    /**
     * 图片验证码
     */
    public static final String SESSION_IMGCODE = "sessionImgcode";
    /**
     * 用户信息
     */
    public static final String SESSION_USER = "sessionUser";

    /** **************************管理员配置********************** */
    /**
     * 是否隐身操作(如果隐身操作，管理员账号操作的时候不会出现id字段)
     */
    public static final String ADMIN_VIEW = "0";
    /**
     * 主键(只有在没有隐身操作的时候，主键才会作用)
     */
    public static final String ADMIN_ID = "1";
    /**
     * 管理员权限
     */
    public static final String ADMIN_ROLEIDS = "-9";
    /**
     * 是否读取配置菜单(properties配置的基本菜单)
     */
    public static final String ADMIN_MENU = "1";
    /**
     * 系统管理框架页面示例
     */
    public static final String ADMIN_FRAME = "static/baseframehtml/index.html";
    /**
     * 标识需要新页面打开(如果url被这个加在最前面，则会重新开个窗口打开)
     */
    public static final String ADMIN_OPEN_WINDOW = "windowOpen/";

    /** ****************************异常打印配置********************** */
    /**
     * 是否打印异常
     */
    public static final String EXCEPTION_PRINT = "1";
    /**
     * 是否保存异常
     */
    public static final String EXCEPTION_SAVE = "1";

    /** ****************************日志配置********************** */
    /**
     * 是否保存日志
     */
    public static final String LOG_SAVE = "1";
    /**
     * 日志类型
     */
    public static final String LOG_TYPE_EXCEPTION = "0";

    /** ****************************数据权限********************** */
    /**
     * 增
     */
    public static final String DATAROLE_INSERT = "0";
    /**
     * 删
     */
    public static final String DATAROLE_DELETE = "1";
    /**
     * 查
     */
    public static final String DATAROLE_QUERY = "2";
    /**
     * 改
     */
    public static final String DATAROLE_UPDATE = "3";

    /** ****************************数据库信息********************** */
    /**
     * 当前数据库名称
     */
    public static final String DATABASE_NAME = "\'qizuo\'";

    /** ****************************公共方法区********************** */
    /**
     * 批量生成主键
     */
    public static <P extends BasePoJo> void nextIds(List<P> entitys) {
        if (null != entitys && entitys.size() > 0) {
            for (P p : entitys) {
                p.setBaseId(IDUtil.nextId());
            }
        }
    }
}
