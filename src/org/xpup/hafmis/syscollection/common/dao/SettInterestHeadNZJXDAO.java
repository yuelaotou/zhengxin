package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestHeadNZJX;

public class SettInterestHeadNZJXDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public SettInterestHeadNZJX queryById(Integer id) {
    Validate.notNull(id);
    return  (SettInterestHeadNZJX) getHibernateTemplate().get(SettInterestHeadNZJX.class,id);    
  }
  /**
   * 插入记录
   * @param chgPaymentHead
   * @return
   */
  public Serializable insert(SettInterestHeadNZJX settInterestHeadNZJX){
    Serializable id = null;
    try{    
    Validate.notNull(settInterestHeadNZJX);
    id = getHibernateTemplate().save(settInterestHeadNZJX);
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
