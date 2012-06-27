package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonXzzcType;

public class ChgPersonXzzcTypeDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ChgPersonXzzcType queryById(Integer id) {
    Validate.notNull(id);
    return  (ChgPersonXzzcType) getHibernateTemplate().get(ChgPersonXzzcType.class,id);    
  }
  /**
   * 插入记录
   * @param chgPaymentHead
   * @return
   */
  public Serializable insert(ChgPersonXzzcType chgPersonXzzcType){
    Serializable id = null;
    try{    
    Validate.notNull(chgPersonXzzcType);
    id = getHibernateTemplate().save(chgPersonXzzcType);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}