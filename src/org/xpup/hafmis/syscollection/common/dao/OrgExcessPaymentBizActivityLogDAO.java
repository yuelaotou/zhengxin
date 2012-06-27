package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPaymentBizActivityLog;

/**
 * 业务活动日志-挂账
 * 
 *@author 李娟
 *2007-6-20
 */
public class OrgExcessPaymentBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgExcessPaymentBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (OrgExcessPaymentBizActivityLog) getHibernateTemplate().get(OrgExcessPaymentBizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param orgExcessPaymentBizActivityLog
   * @return
   */
  public Serializable insert(OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(orgExcessPaymentBizActivityLog);
    id = getHibernateTemplate().save(orgExcessPaymentBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  
  /**
   * 
   * 根据AA301中的ID，关联AA319中TYPE=C的BIZID
   * @param orgid
   * @param payStatus
   * @return
   */
  public List queryOrgoverpayLog(final Serializable id){
    List list = null;
    list = (List) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog ";
        Vector parameters = new Vector();
        String criterion = "";

        if (id != null&&!id.equals("")) {
          criterion += "orgExcessPaymentBizActivityLog.bizid = ?  and ";
          parameters.add(new Integer(id.toString()));
        }


        
        if (criterion.length() != 0)
          criterion = " where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
      }
    });
         return list;
}
  public void deleteList(List list){
    
    try{    
    Validate.notNull(list);
    this.getHibernateTemplate().deleteAll(list);
    }catch(Exception e){
      e.printStackTrace();
    }
    
  } 
  /**
   * 删除单个记录
   * @param monthPayment
   */
  public void delete(final Integer bizId,final Integer action) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog " +
              "where orgExcessPaymentBizActivityLog.bizid=? " +
              "and orgExcessPaymentBizActivityLog.action=?";
          Query query = session.createQuery(sql);
          query.setParameter(0, bizId);
          query.setParameter(1, action);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 
   * 根据缴存ID和action查询
   * @param paymentId
   * @param action
   * @return
   * @author 付云峰
   */
  public OrgExcessPaymentBizActivityLog queryOrgExcessPaymentBizActivityLog(final Serializable paymentId,final Integer action){
    OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog = null;
    orgExcessPaymentBizActivityLog = (OrgExcessPaymentBizActivityLog) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog ";
        Vector parameters = new Vector();
        String criterion = "";

        if (paymentId != null&&!paymentId.equals("")) {
          criterion += "orgExcessPaymentBizActivityLog.bizid = ?  and ";
          parameters.add(new Integer(paymentId.toString()));
        }
        if (action != null&&!action.equals("")) {
          criterion += "orgExcessPaymentBizActivityLog.action = ?  and ";
          parameters.add(action);
        }
        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.uniqueResult();
      }
    });
    if(orgExcessPaymentBizActivityLog == null){
      orgExcessPaymentBizActivityLog = new OrgExcessPaymentBizActivityLog();
    }
    return orgExcessPaymentBizActivityLog;
  }
}