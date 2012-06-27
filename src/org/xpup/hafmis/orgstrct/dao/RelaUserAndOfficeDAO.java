
package org.xpup.hafmis.orgstrct.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndOffice;

public class RelaUserAndOfficeDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public RelaUserAndOffice queryById(Serializable id) {  
    Validate.notNull(id);
    return  (RelaUserAndOffice) getHibernateTemplate().get(RelaUserAndOffice.class,id);    
  }
  /**
   * 插入记录
   * @param relaUserAndOrgD
   * @return
   */
  public Serializable insert(RelaUserAndOffice relaUserAndOffice){
    Serializable id = null;
    try{    
    Validate.notNull(relaUserAndOffice);
    id = getHibernateTemplate().save(relaUserAndOffice);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 删除单个记录
   * @param relaUserAndOffice
   */
  public void delete(RelaUserAndOffice relaUserAndOffice){
    Validate.notNull(relaUserAndOffice);
    getHibernateTemplate().delete(relaUserAndOffice);
  }
  /**
   * 
   * @param username
   * @param office
   * @return
   */
  public RelaUserAndOffice queryUserOff(final String username,final String office){
    RelaUserAndOffice relaUserAndOffice = null;
    relaUserAndOffice = (RelaUserAndOffice)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
              " from RelaUserAndOffice relaUserAndOffice where relaUserAndOffice.username='"+username+"'" +
                  " and relaUserAndOffice.office = '"+office+"'";   

              Query query=session.createQuery(hql);     
              return query.uniqueResult();
          }
        });
    if(relaUserAndOffice == null){
      relaUserAndOffice = new RelaUserAndOffice();
    }
    return relaUserAndOffice;
  }
}
