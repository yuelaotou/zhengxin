package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountAdjustPick;

/**
 * 单位住房公积业务流水-调提取L
 * 
 *@author 李娟
 *2007-6-25
 */
public class AdjustWrongAccountAdjustPickDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public AdjustWrongAccountAdjustPick queryById(Integer id) {
    Validate.notNull(id);
    return  (AdjustWrongAccountAdjustPick) getHibernateTemplate().get(AdjustWrongAccountAdjustPick.class,id);    
  }
  /**
   * 插入记录
   * @param adjustWrongAccountAdjustPick
   * @return
   */
  public Serializable insert(AdjustWrongAccountAdjustPick adjustWrongAccountAdjustPick){
    Serializable id = null;
    try{    
    Validate.notNull(adjustWrongAccountAdjustPick);
    id = getHibernateTemplate().save(adjustWrongAccountAdjustPick); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}