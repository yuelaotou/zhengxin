package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

public class OrgInfoDAODW extends HibernateDaoSupport{
  /**
   * 删除BA001信息copy归集
   */
    public void deleteOrgInfoByIdSL(OrgInfo orgInfo) {
      try {
        getHibernateTemplate().delete(orgInfo);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    /**
     * 删除BA001信息copy归集
     */
    public OrgInfo queryById(Serializable id) {
      Validate.notNull(id);
      return  (OrgInfo) getHibernateTemplate().get(OrgInfo.class,id);    
    }
}
