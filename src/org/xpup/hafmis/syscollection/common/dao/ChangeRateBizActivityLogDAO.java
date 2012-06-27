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
import org.xpup.hafmis.syscollection.common.domain.entity.ChangeRateBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInBizActivityLog;

/**
 * 业务活动日志-利率调整
 * 
 *@author 李娟
 *2007-6-20
 */
public class ChangeRateBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ChangeRateBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ChangeRateBizActivityLog) getHibernateTemplate().get(ChangeRateBizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param changeRateBizActivityLog
   * @return
   */
  public Serializable insert(ChangeRateBizActivityLog changeRateBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(changeRateBizActivityLog);
    id = getHibernateTemplate().save(changeRateBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  public ChangeRateBizActivityLog queryByBizId(final String id,final Integer action,final String type){
    ChangeRateBizActivityLog changeRateBizActivityLog = null;
 
    try{
      Validate.notNull(id);
      changeRateBizActivityLog = (ChangeRateBizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "from ChangeRateBizActivityLog changeRateBizActivityLog ";
              Vector parameters = new Vector();
              String criterion = "";
              if(id != null && !"".equals(id)){
                criterion += "changeRateBizActivityLog.bizid = ? and ";
                parameters.add(new Integer(id));
              }
              if(action != null && !"".equals(action)){
                criterion += "changeRateBizActivityLog.action = ? and ";
                parameters.add(action);
              }
              if(type != null && !"".equals(type)){
                criterion += "changeRateBizActivityLog.types = ? and ";
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
      return changeRateBizActivityLog;
  }
  public void delete_sy(ChangeRateBizActivityLog changeRateBizActivityLog){
    Validate.notNull(changeRateBizActivityLog);
    getHibernateTemplate().delete(changeRateBizActivityLog);
  }
}