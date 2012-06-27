package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountAdjustOther;

/**
 * 错账调整头表-调其他G 
 * 
 *@author 李娟
 *2007-6-19
 */
public class AdjustWrongAccountAdjustOtherDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public AdjustWrongAccountAdjustOther queryById(Serializable id) {  
    Validate.notNull(id);
    return  (AdjustWrongAccountAdjustOther) getHibernateTemplate().get(AdjustWrongAccountAdjustOther.class,id);    
  }
  /**
   * 插入记录
   * @param adjustWrongAccountAdjustOther
   * @return
   */
  public Serializable insert(AdjustWrongAccountAdjustOther adjustWrongAccountAdjustOther){
    Serializable id = null;
    try{    
    Validate.notNull(adjustWrongAccountAdjustOther);
    id = getHibernateTemplate().save(adjustWrongAccountAdjustOther);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
}
