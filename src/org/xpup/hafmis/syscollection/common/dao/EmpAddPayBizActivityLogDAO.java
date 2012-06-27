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
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPayBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPayBizActivityLog;

/**
 * 业务活动日志-个人补缴
 * 
 *@author 李娟
 *2007-6-20
 */
public class EmpAddPayBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public EmpAddPayBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (EmpAddPayBizActivityLog) getHibernateTemplate().get(EmpAddPayBizActivityLog.class,id);    
  }
  
  public List queryByBizId(final Integer id) {  
    Validate.isTrue(id!=null);
    
    List list = (List)getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from EmpAddPayBizActivityLog empAddPayBizActivityLog ";
        Vector parameters = new Vector();
        String criterion = "";
        
        if (id != null) {
          criterion += " empAddPayBizActivityLog.bizid=? and ";
          parameters.add(id);
        }


        if (criterion.length() != 0)
            criterion = " where "
            + criterion.substring(0, criterion.lastIndexOf("and"));



        hql = hql + criterion ;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
      }
    });
    return list;     
  }
  /**
   * 于庆丰
   * 根据主键删除
   * @param id
   * @return
   */
  public void deleteById(String id) {
    try {
      Validate.notNull(id);
      EmpAddPayBizActivityLog empAddPayBizActivityLog = queryById(new Integer(id));
      getHibernateTemplate().delete(empAddPayBizActivityLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void deleteEmpAddPayBiz(Integer id) {  
    Validate.notNull(id);
    List list=this.queryByBizId(id);
    this.getHibernateTemplate().deleteAll(list);
  }
  /**
   * 插入记录
   * @param empAddPayBizActivityLog
   * @return
   */
  public Serializable insert(EmpAddPayBizActivityLog empAddPayBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(empAddPayBizActivityLog);
    id = getHibernateTemplate().save(empAddPayBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  
  public EmpAddPayBizActivityLog queryEmpAddPayBizActivityLog_lg(final Serializable empaddpayId,final Integer action){
    EmpAddPayBizActivityLog empAddPayBizActivityLog = new EmpAddPayBizActivityLog();
    empAddPayBizActivityLog = (EmpAddPayBizActivityLog) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from EmpAddPayBizActivityLog empAddPayBizActivityLog ";
        Vector parameters = new Vector();
        String criterion = "";

        if (empaddpayId != null&&!empaddpayId.equals("")) {
          criterion += "empAddPayBizActivityLog.bizid = ?  and ";
          parameters.add(new Integer(empaddpayId.toString()));
        }
        if (action != null&&!action.equals("")) {
          criterion += "empAddPayBizActivityLog.action = ?  and ";
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
    if(empAddPayBizActivityLog == null){
      empAddPayBizActivityLog = new EmpAddPayBizActivityLog();
    }
    return empAddPayBizActivityLog;
  }
  
  public void delete(EmpAddPayBizActivityLog empAddPayBizActivityLog){
    Validate.notNull(empAddPayBizActivityLog);
    getHibernateTemplate().delete(empAddPayBizActivityLog);
  }
  
  /**
   * 
   * 根据补缴ID和action查询
   * @param orgaddpayId
   * @param action
   * @return
   */
  public EmpAddPayBizActivityLog queryEmpAddPayBizActivityLog(final Serializable empaddpayId,final Integer action){
    EmpAddPayBizActivityLog empAddPayBizActivityLog = null;
    empAddPayBizActivityLog = (EmpAddPayBizActivityLog) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from EmpAddPayBizActivityLog empAddPayBizActivityLog ";
        Vector parameters = new Vector();
        String criterion = "";

        if (empaddpayId != null&&!empaddpayId.equals("")) {
          criterion += "empAddPayBizActivityLog.bizid = ?  and ";
          parameters.add(new Integer(empaddpayId.toString()));
        }
        if (action != null&&!action.equals("")) {
          criterion += "empAddPayBizActivityLog.action = ?  and ";
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
    if(empAddPayBizActivityLog == null){
      empAddPayBizActivityLog = new EmpAddPayBizActivityLog();
    }
    return empAddPayBizActivityLog;
  }
  
  /**
   * 根据补缴ID和action查询
   * @param orgaddpayId
   * @param action
   * @return
   */
  public EmpAddPayBizActivityLog queryEmpAddPayBizActivityLoglg(final Serializable orgaddpayId,final Integer action){
    EmpAddPayBizActivityLog empAddPayBizActivityLog = null;
    empAddPayBizActivityLog = (EmpAddPayBizActivityLog) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from EmpAddPayBizActivityLog empAddPayBizActivityLog ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgaddpayId != null&&!orgaddpayId.equals("")) {
          criterion += "empAddPayBizActivityLog.bizid = ?  and ";
          parameters.add(new Integer(orgaddpayId.toString()));
        }
        if (action != null&&!action.equals("")) {
          criterion += "empAddPayBizActivityLog.action = ?  and ";
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
    if(empAddPayBizActivityLog == null){
      empAddPayBizActivityLog = new EmpAddPayBizActivityLog();
    }
    return empAddPayBizActivityLog;
  }
}