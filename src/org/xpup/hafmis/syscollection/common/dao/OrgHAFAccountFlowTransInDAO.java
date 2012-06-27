package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowTransIn;

public class OrgHAFAccountFlowTransInDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgHAFAccountFlowTransIn queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowTransIn) getHibernateTemplate().get(OrgHAFAccountFlowTransIn.class,id);    
  }
  /**
   * 插入记录
   * @param OrgHAFAccountFlowTransIn
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowTransIn);
    id = getHibernateTemplate().save(orgHAFAccountFlowTransIn); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  public OrgHAFAccountFlowTransIn queryOrgHAFAccountFlowTransIn(final String bis_id){
    OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn= (OrgHAFAccountFlowTransIn) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn  where "
                + " orgHAFAccountFlowTransIn.bizId=?  ";
            Query query = session.createQuery(hql);
            query.setBigDecimal(0, new BigDecimal(bis_id));
            return query.uniqueResult();
          }
        });
    return orgHAFAccountFlowTransIn;
  }
  /**
   * 根据主键删除
   * 
   * @param id
   * @return
   */
  public void deleteById(OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn) {
    try {
      getHibernateTemplate().delete(orgHAFAccountFlowTransIn);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}