package org.qizuo.cm.modules.base.pojo;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 分页实体类(为什么要继承list, 因为分页拦截器那边)
 * @Date: 10:28 2018/11/19
 */
public class PagePoJo<P> /*extends ArrayList<P> */ {
    /**
     * 页数
     */
    private int pageNo = 1;
    /**
     * 每页数量
     */
    private int pageSize = 10;
    /**
     * 数据总条数
     */
    private int totalCount;
    /**
     * 数据总页数(非必要)
     */
    private int totalPage;
    /**
     * 关联实体类
     */
    private P entity;
    /**
     * 单页实体类集合
     */
    private List<P> entitys;

    public PagePoJo() {

    }

    public PagePoJo(int pageNo, int pageSize, int totalCount, List<P> entitys) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.entitys = entitys;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        /** 设置总条数的时候同时设置总页数 */
        computeTotalPage();
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public P getEntity() {
        return entity;
    }

    public void setEntity(P entity) {
        this.entity = entity;
    }

    public List<P> getEntitys() {
        return entitys;
    }

    public void setEntitys(List<P> entitys) {
        this.entitys = entitys;
    }

    private void computeTotalPage() {
        if (getPageSize() > 0 && getTotalCount() > -1) {
            this.totalPage = (int) (getTotalCount() % getPageSize() == 0 ? getTotalCount() / getPageSize() : getTotalCount() / getPageSize() + 1);
        }
    }
}
