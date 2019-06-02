package org.qizuo.cm.modules.system.pojo;

import org.qizuo.cm.modules.base.pojo.BasePoJo;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 消息
 * @Date: 14:20 2018/10/29
 */
public class MsgPoJo extends BasePoJo {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息跳转url
     */
    private String jumpUrl;
    /**
     * 类型
     */
    private String type;
    /**
     * 发送所有关联
     */
    private String sendTypeId;
    private String sendTypeNm;
    /**
     * 发送单个关联
     */
    private String sendUserId;
    private String sendUserNm;
    /**
     * 发送多个关联
     */
    private String sendUserIds;
    private List<MsgPoJo> msgPoJos;
    /**
     * 是否已读
     */
    private String isRead;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getSendTypeId() {
        return sendTypeId;
    }

    public void setSendTypeId(String sendTypeId) {
        this.sendTypeId = sendTypeId;
    }

    public String getSendTypeNm() {
        return sendTypeNm;
    }

    public void setSendTypeNm(String sendTypeNm) {
        this.sendTypeNm = sendTypeNm;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getSendUserNm() {
        return sendUserNm;
    }

    public void setSendUserNm(String sendUserNm) {
        this.sendUserNm = sendUserNm;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSendUserIds() {
        return sendUserIds;
    }

    public void setSendUserIds(String sendUserIds) {
        this.sendUserIds = sendUserIds;
    }

    public List<MsgPoJo> getMsgPoJos() {
        return msgPoJos;
    }

    public void setMsgPoJos(List<MsgPoJo> msgPoJos) {
        this.msgPoJos = msgPoJos;
    }
}
