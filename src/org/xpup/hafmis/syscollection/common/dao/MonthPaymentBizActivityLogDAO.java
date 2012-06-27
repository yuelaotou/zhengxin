package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentTail;

/**
 * 业务活动日志-汇缴
 * 
 *@author 李娟
 *2007-6-20
 */
public class MonthPaymentBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public MonthPaymentBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (MonthPaymentBizActivityLog) getHibernateTemplate().get(MonthPaymentBizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param monthPaymentBizActivityLog
   * @return
   */
  public Serializable insert(MonthPaymentBizActivityLog monthPaymentBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(monthPaymentBizActivityLog);
    id = getHibernateTemplate().save(monthPaymentBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 删除单个记录
   * @param monthPaymentBizActivityLog
   */
  public void delete(MonthPaymentBizActivityLog monthPaymentBizActivityLog){
    Validate.notNull(monthPaymentBizActivityLog);
    getHibernateTemplate().delete(monthPaymentBizActivityLog);
  }
  /**
   * 删除list
   */
  public void deleteList(List list){
    Validate.notNull(list);
    getHibernateTemplate().deleteAll(list);
  }
  /**
   * 李娟
   * 根据缴存ID和action查询
   * @param paymentId
   * @param action
   * @return
   */
  public MonthPaymentBizActivityLog queryMonthPaymentLogByPayHeadIdLJ(final Serializable paymentId,final Integer action){
    MonthPaymentBizActivityLog monthPaymentBizActivityLog = null;
    monthPaymentBizActivityLog = (MonthPaymentBizActivityLog) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from MonthPaymentBizActivityLog monthPaymentBizActivityLog ";
        Vector parameters = new Vector();
        String criterion = "";

        if (paymentId != null&&!paymentId.equals("")) {
          criterion += "monthPaymentBizActivityLog.bizid = ?  and ";
          parameters.add(new Integer(paymentId.toString()));
        }
        if (action != null&&!action.equals("")) {
          criterion += "monthPaymentBizActivityLog.action = ?  and ";
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
    if(monthPaymentBizActivityLog == null){
      monthPaymentBizActivityLog = new MonthPaymentBizActivityLog();
    }
    return monthPaymentBizActivityLog;
  }
  /**
   * 王硕
   * 根据缴存ID和action查询
   * @param paymentId
   * @param action
   * @return
   */
  public List queryMonthPaymentLogByPayHeadId(final Serializable paymentId,final String type){
    List list = null;
    list = (List) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from MonthPaymentBizActivityLog monthPaymentBizActivityLog ";
        Vector parameters = new Vector();
        String criterion = "";

        if (paymentId != null&&!paymentId.equals("")) {
          criterion += "monthPaymentBizActivityLog.bizid = ?  and ";
          parameters.add(new Integer(paymentId.toString()));
        }
       
          criterion += "monthPaymentBizActivityLog.action in (4,5) and ";
          if (type != null&&!type.equals("")) {
            criterion += "monthPaymentBizActivityLog.types = ?  and ";
            parameters.add(type);
          }
    
        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion+" order by monthPaymentBizActivityLog.action ASC";
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
      }
    });
    if(list == null){
      list = new ArrayList();
    }
    return list;
  }
}