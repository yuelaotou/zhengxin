package org.xpup.hafmis.orgstrct.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndOrg;

public class RelaUserAndOrgDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public RelaUserAndOrg queryById(Serializable id) {  
    Validate.notNull(id);
    return  (RelaUserAndOrg) getHibernateTemplate().get(RelaUserAndOrg.class,id);    
  }
  /**
   * 插入记录
   * @param relaUserAndOrgD
   * @return
   */
  public Serializable insert(RelaUserAndOrg relaUserAndOrg){
    Serializable id = null;
    try{    
    Validate.notNull(relaUserAndOrg);
    id = getHibernateTemplate().save(relaUserAndOrg);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 删除单个记录
   * @param relaUserAndOrgD
   */
  public void delete(RelaUserAndOrg relaUserAndOrg){
    Validate.notNull(relaUserAndOrg);
    getHibernateTemplate().delete(relaUserAndOrg);
  }
  /**
   * 
   * @param username
   * @param orgid
   * @return
   */
  public RelaUserAndOrg queryByUserOrg(final String username,final String orgid){
    RelaUserAndOrg relaUserAndOrg = null;
    relaUserAndOrg = (RelaUserAndOrg)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
              " from RelaUserAndOrg relaUserAndOrg where relaUserAndOrg.username='"+username+"'" +
                  " and relaUserAndOrg.orgId = "+new BigDecimal(orgid);   

              Query query=session.createQuery(hql);     
              return query.uniqueResult();
          }
        });
    if(relaUserAndOrg == null){
      relaUserAndOrg = new RelaUserAndOrg();
    }
    return relaUserAndOrg;
  }
}
