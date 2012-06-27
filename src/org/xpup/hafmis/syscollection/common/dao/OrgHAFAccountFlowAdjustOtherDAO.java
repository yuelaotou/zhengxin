package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowAdjustOther;

/**
 * 单位住房公积业务流水-调其他G
 * 
 *@author 李娟
 *2007-6-25
 */
public class OrgHAFAccountFlowAdjustOtherDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgHAFAccountFlowAdjustOther queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowAdjustOther) getHibernateTemplate().get(OrgHAFAccountFlowAdjustOther.class,id);    
  }
  /**
   * 插入记录
   * @param orgHAFAccountFlowAdjustOther
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowAdjustOther orgHAFAccountFlowAdjustOther){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowAdjustOther);
    id = getHibernateTemplate().save(orgHAFAccountFlowAdjustOther); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}