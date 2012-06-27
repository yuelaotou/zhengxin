package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPickBizActivityLog;

/**
 * 业务活动日志-特殊提取
 * 
 *@author 李娟
 *2007-6-20
 */
public class SpecialPickBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public SpecialPickBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (SpecialPickBizActivityLog) getHibernateTemplate().get(SpecialPickBizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param specialPickBizActivityLog
   * @return
   */
  public Serializable insert(SpecialPickBizActivityLog specialPickBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(specialPickBizActivityLog);
    id = getHibernateTemplate().save(specialPickBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
}