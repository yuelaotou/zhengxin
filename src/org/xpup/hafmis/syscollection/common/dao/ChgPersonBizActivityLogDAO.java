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
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;

/**
 * 业务活动日志-人员变更
 * 
 *@author 李娟
 *2007-6-20
 */
public class ChgPersonBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ChgPersonBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ChgPersonBizActivityLog) getHibernateTemplate().get(ChgPersonBizActivityLog.class,id);    
  }
  /**
   * 插入记录
   * @param chgPersonBizActivityLog
   * @return
   */
  public Serializable insert(ChgPersonBizActivityLog chgPersonBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(chgPersonBizActivityLog);
    id = getHibernateTemplate().save(chgPersonBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }

  /**
   * 王菱
   * 根据业务ID 和 动作 查询业务信息
   */
  public ChgPersonBizActivityLog queryChgPersonBiz_WL(final String bizid,
      final String action) {

    ChgPersonBizActivityLog chgPersonBizActivityLog = null;
    chgPersonBizActivityLog = (ChgPersonBizActivityLog) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgPersonBizActivityLog chgPersonBizActivityLog  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (bizid != null||!bizid.equals("")) {
              criterion += "chgPersonBizActivityLog.bizid = ? and ";
              parameters.add(new Integer(bizid));
            }
            if (action != null||!action.equals("")) {
              criterion += "chgPersonBizActivityLog.action = ? and ";
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

    return chgPersonBizActivityLog;
  }

  /**
   * 王菱
   * 删除单个记录
   * @param chgPersonBizActivityLog
   */
  public void delete_WL(ChgPersonBizActivityLog chgPersonBizActivityLog){
    Validate.notNull(chgPersonBizActivityLog);
    getHibernateTemplate().delete(chgPersonBizActivityLog);
  }
  
}