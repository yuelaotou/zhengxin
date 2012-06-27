
package org.xpup.hafmis.orgstrct.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndCollBank;
import org.xpup.hafmis.orgstrct.domain.RelaUserAndOffice;

public class RelaUserAndCollBankDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public RelaUserAndCollBank queryById(Serializable id) {  
    Validate.notNull(id);
    return  (RelaUserAndCollBank) getHibernateTemplate().get(RelaUserAndCollBank.class,id);    
  }
  /**
   * 插入记录
   * @param relaUserAndCollBank
   * @return
   */
  public Serializable insert(RelaUserAndCollBank relaUserAndCollBank){
    Serializable id = null;
    try{    
    Validate.notNull(relaUserAndCollBank);
    id = getHibernateTemplate().save(relaUserAndCollBank);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 删除单个记录
   * @param relaUserAndCollBank
   */
  public void delete(RelaUserAndCollBank relaUserAndCollBank){
    Validate.notNull(relaUserAndCollBank);
    getHibernateTemplate().delete(relaUserAndCollBank);
  }
  /**
   * 
   * @param username
   * @param office
   * @return
   */
  public RelaUserAndCollBank queryUserBank(final String username,final String collBankId){
    RelaUserAndCollBank relaUserAndCollBank = null;
    relaUserAndCollBank = (RelaUserAndCollBank)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String hql = 
              " from RelaUserAndCollBank relaUserAndCollBank where relaUserAndCollBank.username='"+username+"'" +
                  " and relaUserAndCollBank.collBankId = '"+collBankId+"'";   

              Query query=session.createQuery(hql);     
              return query.uniqueResult();
          }
        });
    if(relaUserAndCollBank == null){
      relaUserAndCollBank = new RelaUserAndCollBank();
    }
    return relaUserAndCollBank;
  }
}
