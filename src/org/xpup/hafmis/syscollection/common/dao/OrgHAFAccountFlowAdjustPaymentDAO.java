package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowAdjustPayment;
/**
 * 单位住房公积业务流水-调缴存K
 * 
 *@author 李娟
 *2007-6-25
 */
public class OrgHAFAccountFlowAdjustPaymentDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgHAFAccountFlowAdjustPayment queryById(Serializable id){
    Validate.notNull(id);
    return (OrgHAFAccountFlowAdjustPayment) getHibernateTemplate().get(OrgHAFAccountFlowAdjustPayment.class,id);
    
  }
  /**
   * 插入记录
   * @param orgAccountFlowAdjustPayment
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowAdjustPayment orgAccountFlowAdjustPayment){
    Serializable id = null;
    try{
      Validate.notNull(orgAccountFlowAdjustPayment);
      id=getHibernateTemplate().save(orgAccountFlowAdjustPayment);
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}