package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestHeadXHTQ;

public class SettInterestHeadXHTQDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public SettInterestHeadXHTQ queryById(Integer id) {
    Validate.notNull(id);
    return  (SettInterestHeadXHTQ) getHibernateTemplate().get(SettInterestHeadXHTQ.class,id);    
  }
  /**
   * 插入记录
   * @param chgPaymentHead
   * @return
   */
  public Serializable insert(SettInterestHeadXHTQ settInterestHeadXHTQ){
    Serializable id = null;
    try{    
    Validate.notNull(settInterestHeadXHTQ);
    id = getHibernateTemplate().save(settInterestHeadXHTQ);
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
