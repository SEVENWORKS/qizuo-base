package org.qizuo.cm.modules.qizuo.service;

import org.qizuo.cm.modules.base.service.BaseService;
import org.qizuo.cm.modules.qizuo.dao.QizuoDao;
import org.qizuo.cm.modules.qizuo.pojo.QizuoPoJo;
import org.qizuo.cm.modules.system.dao.UserDao;
import org.qizuo.cm.modules.system.pojo.UserPoJo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: fangl
 * @Description: QIZUO
 * @Date: 12:13 2019/1/1
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class QizuoService extends BaseService<QizuoDao, QizuoPoJo> {

}
