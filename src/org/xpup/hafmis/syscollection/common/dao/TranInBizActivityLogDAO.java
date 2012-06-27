package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInBizActivityLog;

/**
 * 业务活动日志-转入
 * 
 *@author 李娟
 *2007-6-20
 */
public class TranInBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public TranInBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (TranInBizActivityLog) getHibernateTemplate().get(TranInBizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param tranInBizActivityLog
   * @return
   */
  public Serializable insert(TranInBizActivityLog tranInBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(tranInBizActivityLog);
    id = getHibernateTemplate().save(tranInBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /*
   *查找要删除的日志 
   * 
   **/
  public TranInBizActivityLog queryByBizId(final String id,final Integer action,final String type){
    TranInBizActivityLog tranInBizActivityLog = null;
 
    try{
      Validate.notNull(id);
      tranInBizActivityLog = (TranInBizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "from TranInBizActivityLog tranInBizActivityLog ";
              Vector parameters = new Vector();
              String criterion = "";
              if(id != null && !"".equals(id)){
                criterion += "tranInBizActivityLog.bizid = ? and ";
                parameters.add(new Integer(id));
              }
              if(action != null && !"".equals(action)){
                criterion += "tranInBizActivityLog.action = ? and ";
                parameters.add(action);
              }
              if(type != null && !"".equals(type)){
                criterion += "tranInBizActivityLog.types = ? and ";
                parameters.add(type);
              }
              if (criterion.length() != 0) 
                criterion = "where  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }     
              return  query.uniqueResult();
            }
          });
      }catch(Exception e){
        e.printStackTrace();
      }
      return tranInBizActivityLog;
  }
  public void delete_sy(TranInBizActivityLog tranInBizActivityLog){
    Validate.notNull(tranInBizActivityLog);
    getHibernateTemplate().delete(tranInBizActivityLog);
  }
}