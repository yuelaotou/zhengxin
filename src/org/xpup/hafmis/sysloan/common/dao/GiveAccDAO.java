package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.GiveAcc;

public class GiveAccDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public GiveAcc queryById(Serializable id) {  
    Validate.notNull(id);
    return  (GiveAcc) getHibernateTemplate().get(GiveAcc.class,id);    
  }

  /**
   * 插入记录
   * @param GiveAcc
   * @return
   */
  public Serializable insert(GiveAcc giveAcc){
    Serializable id = null;
    try{    
    Validate.notNull(giveAcc);
    id = getHibernateTemplate().save(giveAcc);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  /**
   * hanl
   * 删除
   * @param id
   */
    public void deleteGiveAcc(final String id) {
      try {
        getHibernateTemplate().execute(new HibernateCallback() {

          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {

            String sql = "delete from GiveAcc giveAcc where giveAcc.contractId=?";
            Query query = session.createQuery(sql);
            query.setParameter(0, id);
            return new Integer(query.executeUpdate());
          }
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
