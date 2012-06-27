package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnBizActivityLog;

public class FnBizActivityLogDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public FnBizActivityLog queryById(Serializable id) {
    Validate.notNull(id);
    return (FnBizActivityLog) getHibernateTemplate().get(
        FnBizActivityLog.class, id);
  }

  /**
   * 插入记录
   * 
   * @param BookParameter
   * @return
   */
  public Serializable insert(FnBizActivityLog fnBizActivityLog) {
    Serializable id = null;
    try {
      Validate.notNull(fnBizActivityLog);
      id = getHibernateTemplate().save(fnBizActivityLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 现金日记账
   * @author 郭婧平
   * 2007-10-18
   * 删除fn310表记录（CREDENCE_NUM=所选记录的凭证号 and CREDENCE_DATE=凭证日期 and  OFFICE=所属办事处 and CREDENCE_TYPE=1）
   */
  public void deleteFnBizActivityLog(final String credenceNum,final String credenceDate,final String office,final SecurityInfo securityInfo) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from FnBizActivityLog fnBizActivityLog where fnBizActivityLog.credenceNum = ? " +
              "and fnBizActivityLog.credenceDate = ? and fnBizActivityLog.office = ? and fnBizActivityLog.credenceType = '1' " +
              "and fnBizActivityLog.bookId=? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, credenceNum);
          query.setParameter(1, credenceDate);
          query.setParameter(2, office);
          query.setParameter(3, securityInfo.getBookId());
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 凭证审核_凭证审核_撤消复核
   * @author wsh
   * 2007-10-29
   * 删除fn310表记录（CREDENCE_NUM=所选记录的凭证号 and CREDENCE_DATE=凭证日期 and  OFFICE=所属办事处 and CREDENCE_TYPE=1 动作ACTION=0、凭证类型CREDENCE_TYPE=0.）
   */
  public void deleteFnBizActivityLog_wsh(final String credenceNum,final String credenceDate,final String office,final SecurityInfo securityInfo) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from FnBizActivityLog fnBizActivityLog " +
              "where fnBizActivityLog.credenceNum = ? " +
              "and fnBizActivityLog.credenceDate = ? " +
              "and fnBizActivityLog.office = ?" +
              " and fnBizActivityLog.credenceType = '0' " +
              "and fnBizActivityLog.action='0' " +
              "and fnBizActivityLog.bookId = ? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, credenceNum);
          query.setParameter(1, credenceDate);
          query.setParameter(2, office);
          query.setParameter(3, securityInfo.getBookId());
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 凭证录入
   * @author 刘冰
   * 2007-11-7
   * 删除fn310表记录（CREDENCE_NUM=所选记录的凭证号 and CREDENCE_DATE=凭证日期 and  OFFICE=所属办事处 and CREDENCE_TYPE=0）
   */
  public void deleteFnBizActivityLog_lb(final String credenceNum,final String credenceDate,final String office,final String credenceType,final SecurityInfo securityInfo) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from FnBizActivityLog fnBizActivityLog where fnBizActivityLog.credenceNum = ? " +
              "and fnBizActivityLog.credenceDate = ? and fnBizActivityLog.office = ? and fnBizActivityLog.credenceType = ? " +
              "and fnBizActivityLog.bookId=? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, credenceNum);
          query.setParameter(1, credenceDate);
          query.setParameter(2, office);
          query.setParameter(3, credenceType);
          query.setParameter(4, securityInfo.getBookId());
          
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
