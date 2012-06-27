package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgBizActivityLog;

/**
 * 业务活动日志-单位变更
 * 
 *@author 李娟
 *2007-6-20
 */
public class ChgOrgBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ChgOrgBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ChgOrgBizActivityLog) getHibernateTemplate().get(ChgOrgBizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param chgOrgBizActivityLog
   * @return
   */
  public Serializable insert(ChgOrgBizActivityLog  chgOrgBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(chgOrgBizActivityLog);
    id = getHibernateTemplate().save(chgOrgBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
}