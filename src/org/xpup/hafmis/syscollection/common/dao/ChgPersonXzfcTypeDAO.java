package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonXzfcType;

public class ChgPersonXzfcTypeDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ChgPersonXzfcType queryById(Integer id) {
    Validate.notNull(id);
    return  (ChgPersonXzfcType) getHibernateTemplate().get(ChgPersonXzfcType.class,id);    
  }
  /**
   * 插入记录
   * @param chgPaymentHead
   * @return
   */
  public Serializable insert(ChgPersonXzfcType chgPersonXzfcType){
    Serializable id = null;
    try{    
    Validate.notNull(chgPersonXzfcType);
    id = getHibernateTemplate().save(chgPersonXzfcType);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}