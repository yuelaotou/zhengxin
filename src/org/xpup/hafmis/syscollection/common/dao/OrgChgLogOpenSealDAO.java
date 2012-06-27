package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChgLogOpenSeal;

public class OrgChgLogOpenSealDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgChgLogOpenSeal queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgChgLogOpenSeal) getHibernateTemplate().get(OrgChgLogOpenSeal.class,id);    
  }
  /**
   * 插入记录
   * @param OrgChgLogOpenSeal
   * @return
   */
  public Serializable insert(OrgChgLogOpenSeal orgChgLogOpenSeal){
    Serializable id = null;
    try{    
    Validate.notNull(orgChgLogOpenSeal);
    id = getHibernateTemplate().save(orgChgLogOpenSeal); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}