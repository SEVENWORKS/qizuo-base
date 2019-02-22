package org.qizuo.cm.modules.system.pojo;

import org.qizuo.cm.modules.base.pojo.BasePoJo;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 字典主表
 * @Date: 14:20 2018/10/29
 */
public class DictPoJo extends BasePoJo {
    /** 值 */
    private String value;
    /** 名称 */
    private String label;
    /** 子值集合 */
    private List<DictItemPoJo> dictItemPoJos;

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

    public List<DictItemPoJo> getDictItemPoJos() {
        return dictItemPoJos;
    }

    public void setDictItemPoJos(List<DictItemPoJo> dictItemPoJos) {
        this.dictItemPoJos = dictItemPoJos;
    }
}
