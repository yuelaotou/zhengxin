package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgOpenAccountBizActivityLog;


/**
 * 业务活动日志-单位开户
 * 
 *@author 李娟
 *2007-6-20
 */
public class OrgOpenAccountBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgOpenAccountBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (OrgOpenAccountBizActivityLog) getHibernateTemplate().get(OrgOpenAccountBizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param orgOpenAccountBizActivityLog
   * @return
   */
  public Serializable insert(OrgOpenAccountBizActivityLog orgOpenAccountBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(orgOpenAccountBizActivityLog);
    id = getHibernateTemplate().save(orgOpenAccountBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /*
   * 删除日志中单位开户信息
   */
  public void deleteOrgSL(final String id) {
    OrgOpenAccountBizActivityLog oaal = null;
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql="from OrgOpenAccountBizActivityLog log where log.bizid = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(id));
        return query.list();
      }
    });
    if(list!=null | list.size()!=0){
      for(int i=0;i<list.size();i++){
        oaal=(OrgOpenAccountBizActivityLog)list.get(i);
        if (oaal != null){
          getHibernateTemplate().delete(oaal);
        }
      }
    }
  }
}