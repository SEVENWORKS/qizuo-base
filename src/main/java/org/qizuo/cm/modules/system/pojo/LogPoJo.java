package org.qizuo.cm.modules.system.pojo;

import org.qizuo.cm.modules.base.pojo.BasePoJo;

/**
 * @Author: fangl
 * @Description: 日志
 * @Date: 14:20 2018/10/29
 */
public class LogPoJo extends BasePoJo {
    /** 日志内容 */
    private String content;
    /** 日志类型 */
    private String typeCd;
    private String typeNm;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTypeCd() {
        return typeCd;
    }

    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    public String getTypeNm() {
        return typeNm;
    }

    public void setTypeNm(String typeNm) {
        this.typeNm = typeNm;
    }
}
