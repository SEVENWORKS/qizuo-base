package org.qizuo.cm.modules.base.pojo;

import java.util.List;
import java.util.Map;

/**
 * @Author: fangl
 * @Description: 专做参数传递
 * @Date: 14:23 2018/10/29
 */
public class IOPoJo {
    /** key */
    private String key;
    /** value */
    private String value;
    /** keys */
    private List<String> keys;
    /** values */
    private List<String> values;
    /** map */
    private Map<Object,Object> params;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public Map<Object, Object> getParams() {
        return params;
    }

    public void setParams(Map<Object, Object> params) {
        this.params = params;
    }
}
