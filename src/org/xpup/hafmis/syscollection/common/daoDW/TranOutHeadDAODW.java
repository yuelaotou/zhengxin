package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;

import org.apache.commons.lang.Validate;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;


/**
 * 转出头表
 * 
 * @author 李娟 2007-6-19
 */
public class TranOutHeadDAODW extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public TranOutHead queryById(Serializable id) {
   
    Validate.notNull(id);
  
   return (TranOutHead) getHibernateTemplate().get(TranOutHead.class, id);
  }
  /**
   * 插入记录
   * 
   * @param empInfo
   * @return
   */
  public Serializable insert(TranOutHead tranOutHead) {
    Serializable id = null;
    try {
      Validate.notNull(tranOutHead);
      id = getHibernateTemplate().save(tranOutHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }
  
  
}



