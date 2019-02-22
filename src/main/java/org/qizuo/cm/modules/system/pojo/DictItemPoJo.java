package org.qizuo.cm.modules.system.pojo;

import org.qizuo.cm.modules.base.pojo.BasePoJo;

/**
 * @Author: fangl
 * @Description: 字典子表
 * @Date: 14:20 2018/10/29
 */
public class DictItemPoJo extends BasePoJo {
    /** 值 */
    private String value;
    /** 名称 */
    private String label;
    /** 主字典表ID */
    private String dictId;
    /** 主字典表值 */
    private String dictValue;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
}
