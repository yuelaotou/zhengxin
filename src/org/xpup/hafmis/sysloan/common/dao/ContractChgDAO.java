package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.ContractChg;

public class ContractChgDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ContractChg queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ContractChg) getHibernateTemplate().get(ContractChg.class,id);    
  }

  /**
   * 插入记录
   * @param ContractChg
   * @return
   */
  public Serializable insert(ContractChg contractChg){
    Serializable id = null;
    try{    
    Validate.notNull(contractChg);
    id = getHibernateTemplate().save(contractChg);  
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
    public void deleteContractChg(final String id) {
      try {
        getHibernateTemplate().execute(new HibernateCallback() {

          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {

            String sql = "delete from ContractChg contractChg where contractChg.contractId=?";
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
