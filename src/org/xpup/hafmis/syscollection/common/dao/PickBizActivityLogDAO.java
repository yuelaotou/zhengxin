package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.PickBizActivityLog;

/**
 * 业务活动日志-提取
 * 
 *@author 李娟
 *2007-6-20
 */
public class PickBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public PickBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (PickBizActivityLog) getHibernateTemplate().get(PickBizActivityLog.class,id);    
  }
  /**
   * 李文浩
   * 删除PickBizActivityLog对象
   */
  public void delete(PickBizActivityLog s){
    try{
      getHibernateTemplate().delete(s);
    }catch(Exception a){
      a.printStackTrace();
    }
  }
  /**
   * 李文浩..根据Action=1和bizId求出日志记录
   */
  public PickBizActivityLog queryByBizId(final int bizId){
    PickBizActivityLog bizActivityLog = new PickBizActivityLog();
    try{
      PickBizActivityLog l = null;
      bizActivityLog = (PickBizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
              //此记录一定存在..并且只能有一条
              String hql = "from PickBizActivityLog bizActivityLog where bizActivityLog.bizid = ? and bizActivityLog.action=1";
              BizActivityLog b = (BizActivityLog)session.createQuery(hql).setInteger(0, bizId).uniqueResult();
              return b;
            }
          });
      return bizActivityLog;
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 郭婧平..根据Action=3和bizId求出日志记录
   */
  public PickBizActivityLog queryByBizId1(final int bizId){
    PickBizActivityLog bizActivityLog = new PickBizActivityLog();
    try{
      PickBizActivityLog l = null;
      bizActivityLog = (PickBizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
              //此记录一定存在..并且只能有一条
              String hql = "from PickBizActivityLog bizActivityLog where bizActivityLog.bizid = ? and bizActivityLog.action=3";
              BizActivityLog b = (BizActivityLog)session.createQuery(hql).setInteger(0, bizId).uniqueResult();
              return b;
            }
          });
      return bizActivityLog;
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 插入记录
   * @param pickBizActivityLog
   * @return
   */
  public Serializable insert(PickBizActivityLog pickBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(pickBizActivityLog);
    id = getHibernateTemplate().save(pickBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
}