package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.SearchLackInfo;

public class SearchLackInfoDAODW extends HibernateDaoSupport{
  /**
   * ²åÈë¼ÇÂ¼
   * 
   * @param searchLackInfo
   * @return
   */
  public Serializable insert(SearchLackInfo searchLackInfo) {
    Serializable id = null;
    try {
      Validate.notNull(searchLackInfo);
      id = getHibernateTemplate().save(searchLackInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
}
