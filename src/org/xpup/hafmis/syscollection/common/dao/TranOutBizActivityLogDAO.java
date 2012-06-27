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
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPayBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;

/**
 * 业务活动日志-转出
 * 
 *@author 李娟
 *2007-6-20
 */
public class TranOutBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public TranOutBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (TranOutBizActivityLog) getHibernateTemplate().get(TranOutBizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param tranOutBizActivityLog
   * @return
   */
  public Serializable insert(TranOutBizActivityLog tranOutBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(tranOutBizActivityLog);
    id = getHibernateTemplate().save(tranOutBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  
  
  //通过309.id=319BIZid 查询
  
  public List queryBiZInfo(final String Bizid) {
    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " from TranOutBizActivityLog tot where tot.bizid =?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(Bizid));
        return query.list();
      }
    });
    return list;
  }
  public TranOutBizActivityLog queryTranOutBizActivityLogByBizid(final String Bizid) {
    TranOutBizActivityLog tranOutBizActivityLog = null;
    tranOutBizActivityLog = (TranOutBizActivityLog) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " from TranOutBizActivityLog tot where tot.action=3 and tot.bizid =?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(Bizid));
        return query.uniqueResult();
      }
    });
    return tranOutBizActivityLog;
  }
  //删除
  public void delete(TranOutBizActivityLog tranOutBizActivityLog) {
    try {
      Validate.notNull(tranOutBizActivityLog);
      getHibernateTemplate().delete(tranOutBizActivityLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 根据业务ID和action查询
   * @param bizid
   * @param action
   * @return
   */
  public TranOutBizActivityLog queryTranOutBizActivityLog(final Serializable bizid,final Integer action){
    TranOutBizActivityLog tranOutBizActivityLog  = null;
    tranOutBizActivityLog = (TranOutBizActivityLog) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from TranOutBizActivityLog tranOutBizActivityLog ";
        Vector parameters = new Vector();
        String criterion = "";

        if (bizid != null&&!bizid.equals("")) {
          criterion += "tranOutBizActivityLog.bizid = ?  and ";
          parameters.add(new Integer(bizid.toString()));
        }
        if (action != null&&!action.equals("")) {
          criterion += "tranOutBizActivityLog.action = ?  and ";
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
    if(tranOutBizActivityLog == null){
      tranOutBizActivityLog = new TranOutBizActivityLog();
    }
    return tranOutBizActivityLog;
  }
  
  
  
}

























