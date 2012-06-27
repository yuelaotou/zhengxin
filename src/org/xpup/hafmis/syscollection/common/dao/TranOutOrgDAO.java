package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;

/**
 * 转出单位
 * 
 *@author 李娟
 *2007-6-20
 */
public class TranOutOrgDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public TranOutOrg queryById(Integer id) {
    Validate.notNull(id);
    return  (TranOutOrg) getHibernateTemplate().get(TranOutOrg.class,id);    
  }
  /**
   * 插入记录
   * @param tranOutOrg
   * @return
   */
  public Serializable insert(TranOutOrg tranOutOrg){
    Serializable id = null;
    try{    
    Validate.notNull(tranOutOrg);
    id = getHibernateTemplate().save(tranOutOrg); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  

}
