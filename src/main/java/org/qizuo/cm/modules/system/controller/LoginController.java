package org.qizuo.cm.modules.system.controller;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.frame.listener.session.SessionListener;
import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
import org.qizuo.cm.modules.system.pojo.MenuPoJo;
import org.qizuo.cm.modules.system.pojo.MsgPoJo;
import org.qizuo.cm.modules.system.pojo.RolePoJo;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.qizuo.cm.modules.system.service.MenuService;
import org.qizuo.cm.modules.system.service.MsgService;
import org.qizuo.cm.modules.system.service.UserService;
import org.qizuo.cm.utils.GlobalUtil;
import org.qizuo.cm.utils.PropertiesUtil;
import org.qizuo.cm.utils.SessionUtil;
import org.qizuo.cm.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: fangl
 * @description: 登录控制器
 * @date: 14:09 2018/10/29
 */
@RequestMapping(value = "${url_module}/system/login/",method = RequestMethod.POST)
@Controller
@ResponseBody
public class LoginController {
    @Autowired
    private MsgService msgService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;

    /**
     * @author: fangl
     * @description: 图片验证码验证
     * @date: 16:25 2019/1/8
     */
    @RequestMapping("imgCheck")
    public BackResultPoJo imgCheck(HttpServletRequest httpServletRequest,String imgCode){
        //获取
        String imgC=(String)SessionUtil.sessionGet(httpServletRequest,Global.SESSION_IMGCODE);
        //验证
        if(StringUtils.isNotBlank(imgC)&&imgCode.equals(imgC)){
            return new BackResultPoJo(BackResultPoJo.SUCCESS,"验证成功");
        }else{
            return new BackResultPoJo(BackResultPoJo.FAILURE,"验证码不正确");
        }
    }

    /**
     * @author: fangl
     * @description: 登录验证
     * @date: 15:45 2019/1/8
     */
    @RequestMapping("loginCheck")
    public BackResultPoJo loginCheck(HttpServletRequest httpServletRequest,String username, String password, String type)throws Exception{
        String jumpUrl="";
        //登录
        if(StringUtils.isNotBlank(type) && Global.YES.equals(type)){
            //截取
            int split=username.indexOf(",");
            if(-1!=split){
                password=username.substring(split+1);
                username=username.substring(0,split);
            }
            //特殊登录从配置文件中读取
            String path=Global.class.getClassLoader().getResource("/")+"config/properties/base.properties";
            Map<String,String> backMap=PropertiesUtil.read(path.substring(5));
            String adminUsername=backMap.get("admin_username");
            String adminPassword=backMap.get("admin_password");
            //比较
            if(StringUtils.equals(adminUsername,username)&&StringUtils.equals(adminPassword,password)){
                //特殊用户封装
                UserPoJo userPoJo=new UserPoJo();
                userPoJo.setBaseId(StringUtils.equals(Global.YES,Global.ADMIN_VIEW)?"":Global.ADMIN_ID);
                userPoJo.setUserName(username);
                userPoJo.setName("七作");
                userPoJo.setRoleIds(Global.ADMIN_ROLEIDS);
                //菜单
                MenuPoJo menuPoJo=new MenuPoJo();
                menuPoJo.setBaseId(Global.TREE_FIRST);
                userPoJo.setMenuPoJos(menuService.qEachList(menuPoJo));

                //放入session中
                HttpSession login=SessionUtil.sessionAdd(httpServletRequest,Global.SESSION_USER,userPoJo);

                //是否读取配置中的菜单信息
                if(StringUtils.equals(Global.YES,Global.ADMIN_MENU)){
                    menuPoJo.setName("系统专用");
                    menuPoJo.setTheme("sa-side-home");
                    menuPoJo.setUrl(Global.ADMIN_OPEN_WINDOW+Global.ADMIN_FRAME);

                    List<MenuPoJo> menuPoJos=new ArrayList<>();
                    MenuPoJo menuPoJo1=new MenuPoJo();
                    String adminMenuRole=backMap.get("admin_menu_role");
                    menuPoJo1.setName("角色");
                    menuPoJo1.setUrl(adminMenuRole);
                    menuPoJos.add(menuPoJo1);
                    MenuPoJo menuPoJo2=new MenuPoJo();
                    String adminMenuMenu=backMap.get("admin_menu_menu");
                    menuPoJo2.setName("菜单");
                    menuPoJo2.setUrl(adminMenuMenu);
                    menuPoJos.add(menuPoJo2);
                    MenuPoJo menuPoJo3=new MenuPoJo();
                    String adminMenuDict=backMap.get("admin_menu_dict");
                    menuPoJo3.setName("字典");
                    menuPoJo3.setUrl(adminMenuDict);
                    menuPoJos.add(menuPoJo3);
                    MenuPoJo menuPoJo4=new MenuPoJo();
                    String adminMenuLog=backMap.get("admin_menu_log");
                    menuPoJo4.setName("日志");
                    menuPoJo4.setUrl(adminMenuLog);
                    menuPoJos.add(menuPoJo4);
                    MenuPoJo menuPoJo5=new MenuPoJo();
                    String adminMenuUser=backMap.get("admin_menu_user");
                    menuPoJo5.setName("用户管理");
                    menuPoJo5.setUrl(adminMenuUser);
                    menuPoJos.add(menuPoJo5);

                    menuPoJo.setMenuPoJos(menuPoJos);
                    List<MenuPoJo> menus=userPoJo.getMenuPoJos();
                    if(null==menus){
                        menus=new ArrayList<>();
                        menus.add(menuPoJo);
                        userPoJo.setMenuPoJos(menus);
                    }else{
                        menus.add(menuPoJo);
                    }
                }

                //管理员只允许单个登录
                //当前登录所有人
                Map<String, HttpSession> loginUser=SessionListener.LOGIN_USER_MAP;
                //判断用户名是否存在
                HttpSession loged=loginUser.get(username);
                if(null!=loged){
                    //移除
                    SessionListener.LOGIN_USER_MAP.remove(username);
                    //销毁
                    loged.invalidate();
                }

                //当前登录人放入map中
                SessionListener.LOGIN_USER_MAP.put(username,login);

                //跳转路径
                jumpUrl=Global.LOGIN_CHANGE_URL;
            }else{
                return new BackResultPoJo(BackResultPoJo.FAILURE,"密码或者用户名不正确");
            }
        }else{
            //从数据库匹配
            UserPoJo userPoJo=new UserPoJo();
            userPoJo.setUserName(username);
            userPoJo=userService.qUserAllMsg(userPoJo);
            userPoJo.setMenuPoJos(GlobalUtil.treeClean(Global.TREE_FIRST,userPoJo.getMenuPoJos()));
            //对比
            if(null!=userPoJo&&StringUtils.equals(password,userPoJo.getPassWord())){
                //放入session中
                HttpSession login=SessionUtil.sessionAdd(httpServletRequest,Global.SESSION_USER,userPoJo);
                //判断是否单个登录
                if(StringUtils.equals(Global.YES,Global.LOGIN_ONLY)){
                    //清除登录
                    //当前登录所有人
                    Map<String, HttpSession> loginUser=SessionListener.LOGIN_USER_MAP;
                    //判断用户名是否存在
                    HttpSession loged=loginUser.get(username);
                    if(null!=loged){
                        //销毁
                        loged.invalidate();
                        //移除
                        SessionListener.LOGIN_USER_MAP.remove(username);
                    }
                    //当前登录人放入map中
                    SessionListener.LOGIN_USER_MAP.put(username,login);

                    //跳转路径
                    List<RolePoJo> rolePoJos=userPoJo.getRolePoJos();
                    if(null!=rolePoJos&&rolePoJos.size()>0){
                        //取第一个跳转路径
                        jumpUrl=rolePoJos.get(0).getJumpUrl();
                    }
                }
            }else{
                return new BackResultPoJo(BackResultPoJo.FAILURE,"密码或者用户名不正确");
            }
        }
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"登录成功",jumpUrl);
    }

    /**
     * @author: fangl
     * @description: 登出
     * @date: 16:14 2019/1/9
     */
    @RequestMapping("loginOut")
    public BackResultPoJo loginOut(HttpServletRequest httpServletRequest){
        UserPoJo userPoJo=UserUtil.qUser(httpServletRequest);
        if(null!=userPoJo){
            //当前登录所有人
            Map<String, HttpSession> loginUser=SessionListener.LOGIN_USER_MAP;
            //判断用户名是否存在
            HttpSession loged=loginUser.get(userPoJo.getUserName());
            if(null!=loged){
                //销毁
                loged.invalidate();
                //移除
                SessionListener.LOGIN_USER_MAP.remove(userPoJo.getUserName());
            }
        }
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"清除登录");
    }

    /**
     * @author: fangl
     * @description: 框架数据统一查询接口
     * @date: 11:03 2019/1/10
     */
    @RequestMapping("frameData")
    public BackResultPoJo frameData(HttpServletRequest httpServletRequest){
        //用户信息
        UserPoJo userPoJo=UserUtil.qUser(httpServletRequest);
        //返回数据集map
        Map<String,Object> map=new HashMap<>();
        //消息列表
        MsgPoJo msgPoJo=new MsgPoJo();
        msgPoJo.setBaseStatus(Global.STATUS_YES);
        msgPoJo.setSendUserId(userPoJo.getBaseId());
        map.put("baseHeader_msgList",msgService.qList(msgPoJo));
        //用户和菜单信息
        map.put("userMsg", userPoJo);
        return new BackResultPoJo(BackResultPoJo.SUCCESS,"成功",map);
    }

}
