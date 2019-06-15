package org.qizuo.cm.modules.base.service;

import org.qizuo.cm.modules.base.dao.BaseDao;
import org.qizuo.cm.modules.base.pojo.BasePoJo;
import org.qizuo.cm.modules.base.pojo.PagePoJo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: fangl
 * @Description: 基本service层
 * @Date: 15:17 2018/10/30
 */
@Transactional(rollbackFor = Exception.class)
public class BaseService<D extends BaseDao<P>, P extends BasePoJo> {

    @Autowired
    protected D dao;

    /**
     * 增加(所有增加i开头)
     */
    public boolean insert(P p) {
        //添加插入基本信息
        p.preIDo();
        return dao.insert(p);
    }

    /**
     * 更新(所有更新u开头)
     */
    public boolean update(P p) {
        //添加更新基本信息
        p.preUDo();
        return dao.update(p);
    }

    /**
     * 删除(所有删除d开头)
     */
    public boolean delete(P p) {
        return dao.delete(p);
    }

    /**
     * 筛选单个(所有筛选q开头)
     */
    public P query(P p) {
        return dao.query(p);
    }

    /**
     * 筛选多个
     */
    public List<P> qList(P p) {
        return dao.qList(p);
    }

    /**
     * 查询分页(本框架分页以pageQZ名称进行拦截)
     */
    public PagePoJo<P> qPageQZ(PagePoJo<P> page) {
        page.setEntitys(dao.qPageQZ(page));
        return page;
    }

    /** ******************************以下常用********************************************** */
    /**
     * 更新状态
     */
    public boolean uStatus(P p) {
        return dao.uStatus(p);
    }
}
