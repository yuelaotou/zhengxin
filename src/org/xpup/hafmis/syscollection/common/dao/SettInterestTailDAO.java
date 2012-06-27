package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestTail;

/**
 * 结息尾表 
 * 
 *@author 李娟
 *2007-6-19
 */
public class SettInterestTailDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public SettInterestTail queryById(Serializable id) {  
    Validate.notNull(id);
    return  (SettInterestTail) getHibernateTemplate().get(SettInterestTail.class,id);    
  }
  /**
   * 插入记录
   * @param settInterestTail
   * @return
   */
  public Serializable insert(SettInterestTail settInterestTail){
    Serializable id = null;
    try{    
    Validate.notNull(settInterestTail);
    id = getHibernateTemplate().save(settInterestTail);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }

}
