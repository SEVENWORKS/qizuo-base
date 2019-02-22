package org.qizuo.cm.modules.base.pojo;

import org.apache.commons.lang3.StringUtils;
import org.qizuo.cm.Global;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.qizuo.cm.utils.HttpUtil;
import org.qizuo.cm.utils.IDUtil;
import org.qizuo.cm.utils.UserUtil;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @Author: fangl
 * @Description: 基本实体类
 * @Date: 10:25 2018/10/30
 */
public class BasePoJo extends IntOutDatasPoJo {
    /**id*/
    private String baseId;
    /**创建ip*/
    private String baseCreateIp;
    /**更新ip*/
    private String baseUpdateIp;
    /**创建人*/
    private String baseCreateUserId;
    private String baseCreateUserNm;
    /**创建时间*/
    private String baseCreateTime;
    /**更新人*/
    private String baseUpdateUserId;
    private String baseUpdateUserNm;
    /**更新时间*/
    private String baseUpdateTime;
    /**状态 1有效 0无效 */
    private String baseStatus;
    /**备注 数据备注*/
    private String baseRemarks;
    /** 排序 */
    private String orderBy;

    /** 其它 */
    /** 创建人信息 */
    private UserPoJo baseCreateUser;
    /** 更新人信息 */
    private UserPoJo baseUpdateUser;

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getBaseCreateTime() {
        return baseCreateTime;
    }

    public void setBaseCreateTime(String baseCreateTime) {
        this.baseCreateTime = baseCreateTime;
    }

    public String getBaseStatus() {
        return baseStatus;
    }

    public void setBaseStatus(String baseStatus) {
        this.baseStatus = baseStatus;
    }

    public String getBaseRemarks() {
        return baseRemarks;
    }

    public void setBaseRemarks(String baseRemarks) {
        this.baseRemarks = baseRemarks;
    }

    public String getBaseCreateUserId() {
        return baseCreateUserId;
    }

    public void setBaseCreateUserId(String baseCreateUserId) {
        this.baseCreateUserId = baseCreateUserId;
    }

    public String getBaseUpdateUserId() {
        return baseUpdateUserId;
    }

    public void setBaseUpdateUserId(String baseUpdateUserId) {
        this.baseUpdateUserId = baseUpdateUserId;
    }

    public String getBaseUpdateTime() {
        return baseUpdateTime;
    }

    public void setBaseUpdateTime(String baseUpdateTime) {
        this.baseUpdateTime = baseUpdateTime;
    }

    public String getBaseCreateIp() {
        return baseCreateIp;
    }

    public void setBaseCreateIp(String baseCreateIp) {
        this.baseCreateIp = baseCreateIp;
    }

    public String getBaseUpdateIp() {
        return baseUpdateIp;
    }

    public void setBaseUpdateIp(String baseUpdateIp) {
        this.baseUpdateIp = baseUpdateIp;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getBaseCreateUserNm() {
        return baseCreateUserNm;
    }

    public void setBaseCreateUserNm(String baseCreateUserNm) {
        this.baseCreateUserNm = baseCreateUserNm;
    }

    public String getBaseUpdateUserNm() {
        return baseUpdateUserNm;
    }

    public void setBaseUpdateUserNm(String baseUpdateUserNm) {
        this.baseUpdateUserNm = baseUpdateUserNm;
    }

    public UserPoJo getBaseCreateUser() {
        return baseCreateUser;
    }

    public void setBaseCreateUser(UserPoJo baseCreateUser) {
        this.baseCreateUser = baseCreateUser;
    }

    public UserPoJo getBaseUpdateUser() {
        return baseUpdateUser;
    }

    public void setBaseUpdateUser(UserPoJo baseUpdateUser) {
        this.baseUpdateUser = baseUpdateUser;
    }

    /** 插入前动作 */
    public void preIDo(HttpServletRequest httpServletRequest){
        //人员
        UserPoJo userPoJo=UserUtil.qUser(httpServletRequest);
        this.baseCreateUserId=userPoJo.getBaseId();
        //状态
        this.baseStatus= Global.STATUS_YES;
        //ip
        String ip= HttpUtil.getIpAddress(httpServletRequest);
        this.baseCreateIp=ip;
        //id
        this.baseId=IDUtil.nextId();
    }

    /** 更新前动作 */
    public void preUDo(HttpServletRequest httpServletRequest){
        //人员
        UserPoJo userPoJo=UserUtil.qUser(httpServletRequest);
        this.baseUpdateUserId=userPoJo.getBaseId();
        //ip
        String ip= HttpUtil.getIpAddress(httpServletRequest);
        this.baseUpdateIp=ip;
    }
}
