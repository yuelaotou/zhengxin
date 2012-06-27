package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChgLogOpen;


public class OrgChgLogOpenDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgChgLogOpen queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgChgLogOpen) getHibernateTemplate().get(OrgChgLogOpen.class,id);    
  }
  /**
   * 插入记录
   * @param orgChgLogOpen
   * @return
   */
  public Serializable insert(OrgChgLogOpen orgChgLogOpen){
    Serializable id = null;
    try{    
    Validate.notNull(orgChgLogOpen);
    id = getHibernateTemplate().save(orgChgLogOpen); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
