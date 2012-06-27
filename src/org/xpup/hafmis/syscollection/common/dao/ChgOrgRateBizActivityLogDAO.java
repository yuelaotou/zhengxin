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
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRateBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;

/**
 * 业务活动日志-汇缴比例调整
 * 
 *@author 李娟
 *2007-6-20
 */
public class ChgOrgRateBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ChgOrgRateBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ChgOrgRateBizActivityLog) getHibernateTemplate().get(ChgOrgRateBizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param chgOrgRateBizActivityLog
   * @return
   */
  public Serializable insert(ChgOrgRateBizActivityLog chgOrgRateBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(chgOrgRateBizActivityLog);
    id = getHibernateTemplate().save(chgOrgRateBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 王菱
   * 根据业务ID 和 动作 查询业务信息
   */
  public ChgOrgRateBizActivityLog queryChgOrgRateBiz_WL(final String bizid,
      final String action) {

    ChgOrgRateBizActivityLog chgOrgRateBizActivityLog = null;
    chgOrgRateBizActivityLog = (ChgOrgRateBizActivityLog) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgOrgRateBizActivityLog chgOrgRateBizActivityLog  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (bizid != null||!bizid.equals("")) {
              criterion += "chgOrgRateBizActivityLog.bizid = ? and ";
              parameters.add(new Integer(bizid));
            }
            if (action != null||!action.equals("")) {
              criterion += "chgOrgRateBizActivityLog.action = ? and ";
              parameters.add(new Integer(action));
            }
            if (criterion.length() != 0) 
              criterion = "where  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;

            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }
            return  query0.uniqueResult();
          }
        });

    return chgOrgRateBizActivityLog;
  }
  /**
   * 王菱
   * 删除单个记录
   * @param ChgOrgRateBizActivityLog
   */
  public void delete_WL(ChgOrgRateBizActivityLog chgOrgRateBizActivityLog){
    Validate.notNull(chgOrgRateBizActivityLog);
    getHibernateTemplate().delete(chgOrgRateBizActivityLog);
  }
}