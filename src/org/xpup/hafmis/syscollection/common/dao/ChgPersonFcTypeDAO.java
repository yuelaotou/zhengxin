package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonFcType;

public class ChgPersonFcTypeDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ChgPersonFcType queryById(Integer id) {
    Validate.notNull(id);
    return  (ChgPersonFcType) getHibernateTemplate().get(ChgPersonFcType.class,id);    
  }
  /**
   * 插入记录
   * @param chgPaymentHead
   * @return
   */
  public Serializable insert(ChgPersonFcType chgPersonFcType){
    Serializable id = null;
    try{    
    Validate.notNull(chgPersonFcType);
    id = getHibernateTemplate().save(chgPersonFcType);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
