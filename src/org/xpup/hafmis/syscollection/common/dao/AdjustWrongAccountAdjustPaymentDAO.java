package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountAdjustPayment;

/**
 * 错账调整头表-调缴存 
 * 
 *@author 李娟
 *2007-6-19
 */
public class AdjustWrongAccountAdjustPaymentDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public AdjustWrongAccountAdjustPayment queryById(Serializable id) {  
    Validate.notNull(id);
    return  (AdjustWrongAccountAdjustPayment) getHibernateTemplate().get(AdjustWrongAccountAdjustPayment.class,id);    
  }
  /**
   * 插入记录
   * @param adjustWrongAccountAdjustPayment
   * @return
   */
  public Serializable insert(AdjustWrongAccountAdjustPayment adjustWrongAccountAdjustPayment){
    Serializable id = null;
    try{    
    Validate.notNull(adjustWrongAccountAdjustPayment);
    id = getHibernateTemplate().save(adjustWrongAccountAdjustPayment);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
}
