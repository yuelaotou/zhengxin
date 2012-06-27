/**
 * Copy Right Information   : Goldsoft 
 * Project                  : EmpAddPayDAODW
 * @version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-12-17
 **/
package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;

/**
 * 个人补缴_中心版_操作单位库
 * 
 * @author wangy 2007-12-17
 */
public class EmpAddPayDAODW extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public EmpAddPay queryById(Serializable id) {  
    Validate.notNull(id);
    return  (EmpAddPay) getHibernateTemplate().get(EmpAddPay.class,id);    
  }

}

