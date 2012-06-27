package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChgLogDelAccount;

public class OrgChgLogDelAccountDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgChgLogDelAccount queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgChgLogDelAccount) getHibernateTemplate().get(OrgChgLogDelAccount.class,id);    
  }
  /**
   * 插入记录
   * @param OrgChgLogDelAccount
   * @return
   */
  public Serializable insert(OrgChgLogDelAccount orgChgLogDelAccount){
    Serializable id = null;
    try{    
    Validate.notNull(orgChgLogDelAccount);
    id = getHibernateTemplate().save(orgChgLogDelAccount); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
