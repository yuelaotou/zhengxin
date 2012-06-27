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
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPayBizActivityLog;

/**
 * 业务活动日志-单位补缴
 * 
 *@author 李娟
 *2007-6-20
 */
public class OrgAddPayBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgAddPayBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (OrgAddPayBizActivityLog) getHibernateTemplate().get(OrgAddPayBizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param orgAddPayBizActivityLog
   * @return
   */
  public Serializable insert(OrgAddPayBizActivityLog orgAddPayBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(orgAddPayBizActivityLog);
    id = getHibernateTemplate().save(orgAddPayBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 删除单个记录
   * @param orgAddPayBizActivityLog
   */
  public void delete(OrgAddPayBizActivityLog orgAddPayBizActivityLog){
    Validate.notNull(orgAddPayBizActivityLog);
    getHibernateTemplate().delete(orgAddPayBizActivityLog);
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
   * 根据补缴ID和action查询
   * @param orgaddpayId
   * @param action
   * @return
   */
  public OrgAddPayBizActivityLog queryOrgAddPayBizActivityLogLJ(final Serializable orgaddpayId,final Integer action){
    OrgAddPayBizActivityLog orgAddPayBizActivityLog = null;
    orgAddPayBizActivityLog = (OrgAddPayBizActivityLog) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from OrgAddPayBizActivityLog orgAddPayBizActivityLog ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgaddpayId != null&&!orgaddpayId.equals("")) {
          criterion += "orgAddPayBizActivityLog.bizid = ?  and ";
          parameters.add(new Integer(orgaddpayId.toString()));
        }
        if (action != null&&!action.equals("")) {
          criterion += "orgAddPayBizActivityLog.action = ?  and ";
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
    if(orgAddPayBizActivityLog == null){
      orgAddPayBizActivityLog = new OrgAddPayBizActivityLog();
    }
    return orgAddPayBizActivityLog;
  }
}