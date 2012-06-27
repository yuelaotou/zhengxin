package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowAdjustPick;

/**
 * 单位住房公积业务流水-调提取L
 * 
 *@author 李娟
 *2007-6-25
 */
public class OrgHAFAccountFlowAdjustPickDAO extends HibernateDaoSupport{
  
  /**
   * 根据id查询
   * @param id
   * @return
   */
  public OrgHAFAccountFlowAdjustPick queryById(Serializable id){
    Validate.notNull(id);
    return (OrgHAFAccountFlowAdjustPick) getHibernateTemplate().
    get(OrgHAFAccountFlowAdjustPick.class, id);
    
  }
  /**
   * 插入记录
   * @param orgHAFAccountFlowAdjustPick
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowAdjustPick orgHAFAccountFlowAdjustPick){
    Serializable id = null;
    try{
      id = getHibernateTemplate().save(orgHAFAccountFlowAdjustPick);
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}