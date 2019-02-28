package org.qizuo.cm.modules.base.pojo;

/**
 * @Author: fangl
 * @Description: json专用返回实体类
 * @Date: 14:20 2018/10/29
 */
public class BackResultPoJo{
    /** 常用返回码 */
    public static final int ERROR=-9;
    public static final int SUCCESS=1;
    public static final int FAILURE=0;
    /** 返回码 */
    private int backCode;
    /** 返回信息 */
    private String backMsg;
    /** 返回数据 */
    private Object backData;

    public BackResultPoJo(int backCode, String backMsg) {
        this.backCode = backCode;
        this.backMsg = backMsg;
    }

    public BackResultPoJo(int backCode, String backMsg, Object backData) {
        this.backCode = backCode;
        this.backMsg = backMsg;
        this.backData = backData;
    }

    public BackResultPoJo(int backCode, Object backData) {
        this.backCode = backCode;
        this.backData = backData;
    }

    public int getBackCode() {
        return backCode;
    }

    public void setBackCode(int backCode) {
        this.backCode = backCode;
    }

    public String getBackMsg() {
        return backMsg;
    }

    public void setBackMsg(String backMsg) {
        this.backMsg = backMsg;
    }

    public Object getBackData() {
        return backData;
    }

    public void setBackData(Object backData) {
        this.backData = backData;
    }
}
