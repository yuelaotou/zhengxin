
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
import org.xpup.hafmis.orgstrct.domain.RelaUserAndUser;

public class RelaUserAndUserDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public RelaUserAndUser queryById(Serializable id) {  
    Validate.notNull(id);
    return  (RelaUserAndUser) getHibernateTemplate().get(RelaUserAndUser.class,id);    
  }
  /**
   * 插入记录
   * @param relaUserAndUser
   * @return
   */
  public Serializable insert(RelaUserAndUser relaUserAndUser){
    Serializable id = null;
    try{    
    Validate.notNull(relaUserAndUser);
    id = getHibernateTemplate().save(relaUserAndUser);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 删除单个记录
   * @param relaUserAndUser
   */
  public void delete(RelaUserAndUser relaUserAndUser){
    Validate.notNull(relaUserAndUser);
    getHibernateTemplate().delete(relaUserAndUser);
  }
  /**
   * 用户用户查询
   * jj
   * @param username
   * @param subusername
   * @return
   */
  public RelaUserAndUser queryUserUsers(final String username,final String subusername){
    RelaUserAndUser relaUserAndUser = null;
    relaUserAndUser = (RelaUserAndUser)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
              " from RelaUserAndUser relaUserAndUser where relaUserAndUser.username='"+username+"'" +
                  " and relaUserAndUser.subusername = '"+subusername+"'";   

              Query query=session.createQuery(hql);     
              return query.uniqueResult();
          }
        });
    if(relaUserAndUser == null){
      relaUserAndUser = new RelaUserAndUser();
    }
    return relaUserAndUser;
  }
}
