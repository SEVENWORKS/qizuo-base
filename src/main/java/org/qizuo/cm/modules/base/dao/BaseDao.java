package org.qizuo.cm.modules.base.dao;

import org.qizuo.cm.modules.base.pojo.BasePoJo;
import org.qizuo.cm.modules.base.pojo.PagePoJo;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 基本dao层
 * @Date: 15:17 2018/10/30
 */
public interface BaseDao<P extends BasePoJo> {
    /**
     * 增加
     */
    boolean insert(P p);

    /**
     * 更新
     */
    boolean update(P p);

    /**
     * 删除
     */
    boolean delete(P p);

    /**
     * 筛选单个
     */
    P query(P p);

    /**
     * 筛选多个
     */
    List<P> qList(P p);

    /**
     * 查询分页
     */
    List<P> qPageQZ(PagePoJo<P> page);

    /** ******************************以下常用********************************************** */
    /**
     * 更新状态
     */
    boolean uStatus(P p);
}
