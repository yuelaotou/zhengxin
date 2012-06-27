package org.xpup.hafmis.syscollection.common.dao;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CollParaDAO extends HibernateDaoSupport{
  /**
   * 查询凭证号生成方式，中心还是银行。//归集
   * @author 杨光
   * @return
   * @throws Exception
   */
  public String getDocNumDesignPara() throws Exception {
    String param_value = "";
    param_value = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select collPara.param_type from CollPara collPara where collPara.param_value='docNumDocument' ";

            Query query = session.createQuery(hql);
            Object obj = query.uniqueResult();
            if (obj != null) {
              return query.uniqueResult().toString();
            } else {
              return query.uniqueResult();
            }
          }
        });
    return param_value;
  }
  
  /**
   * 用户名生成方式，中心还是银行。//归集
   * @author 杨光
   * @return
   * @throws Exception
   */
  public String getNamePara() throws Exception {
    String param_value = "";
    param_value = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select collPara.param_type from CollPara collPara where collPara.param_value='name' ";

            Query query = session.createQuery(hql);
            Object obj = query.uniqueResult();
            if (obj != null) {
              return query.uniqueResult().toString();
            } else {
              return query.uniqueResult();
            }
          }
        });
    return param_value;
  }
  
  public String getLoanNamePara() throws Exception {
    String param_value = "";
    param_value = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select collPara.param_type from CollPara collPara where collPara.param_value='loanName' ";

            Query query = session.createQuery(hql);
            Object obj = query.uniqueResult();
            if (obj != null) {
              return query.uniqueResult().toString();
            } else {
              return query.uniqueResult();
            }
          }
        });
    return param_value;
  }
  /**
   * 查询凭证号生成方式，中心还是银行。//个贷
   * @author 杨光
   * @return
   * @throws Exception
   */
  public String getLoanDocNumDesignPara() throws Exception {
    String param_value = "";
    param_value = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select collPara.param_type from CollPara collPara where collPara.param_value='loanDocNumDocument' ";

            Query query = session.createQuery(hql);
            Object obj = query.uniqueResult();
            if (obj != null) {
              return query.uniqueResult().toString();
            } else {
              return query.uniqueResult();
            }
          }
        });
    return param_value;
  }
  public void updateDocNumDesignPara(final String docNumDocument) throws Exception {
    getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "update CollPara collPara set collPara.param_type =? where collPara.param_value='docNumDocument' ";

            Query query = session.createQuery(hql);
            query.setString(0,docNumDocument);
            query.executeUpdate();
            return null;
          }
        });
  }
  public void updateLoanDocNumDesignPara(final String loanDocNumDocument) throws Exception {
    getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "update CollPara collPara set collPara.param_type =? where collPara.param_value='loanDocNumDocument' ";

            Query query = session.createQuery(hql);
            query.setString(0,loanDocNumDocument);
            query.executeUpdate();
            return null;
          }
        });
  }
  
  public void updateNamePara(final String name) throws Exception {
    getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
           String hql = "update CollPara collPara set collPara.param_type =? where collPara.param_value='name' ";
            Query query = session.createQuery(hql);
            query.setString(0,name);
            query.executeUpdate();
            return null;
          }
        });
  }
  
  public void updateLoanNamePara(final String name) throws Exception {
    getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
           String hql = "update CollPara collPara set collPara.param_type =? where collPara.param_value='loanName' ";
            Query query = session.createQuery(hql);
            query.setString(0,name);
            query.executeUpdate();
            return null;
          }
        });
  }
  
}
