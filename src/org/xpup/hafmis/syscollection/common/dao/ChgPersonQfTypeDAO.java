package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonQfType;

public class ChgPersonQfTypeDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ChgPersonQfType queryById(Integer id) {
    Validate.notNull(id);
    return  (ChgPersonQfType) getHibernateTemplate().get(ChgPersonQfType.class,id);    
  }
  /**
   * 插入记录
   * @param chgPaymentHead
   * @return
   */
  public Serializable insert(ChgPersonQfType chgPersonQfType){
    Serializable id = null;
    try{    
    Validate.notNull(chgPersonQfType);
    id = getHibernateTemplate().save(chgPersonQfType);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
