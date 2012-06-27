package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestHead;

public class SettInterestHeadDAODW extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public SettInterestHead queryById(Serializable id) {
    Validate.notNull(id);
    return (SettInterestHead) getHibernateTemplate().get(
        SettInterestHead.class, id);
  }

  /**
   * 插入记录
   * 
   * @param settInterestResult
   * @return
   */
  public Serializable insert(SettInterestHead settInterestHead) {
    Serializable id = null;
    try {
      Validate.notNull(settInterestHead);
      id = getHibernateTemplate().save(settInterestHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }

  public String findIntersInfo(final String pid, final String pempid) {
    String money = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(a317.pre_interest + a317.cur_interest) from da001 d001, aa316 a316, aa317 a317 "
                + "where a316.id = a317.sett_head_id and d001.center_head_id = a316.id and d001.org_head_id = ? and a317.emp_id = ?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, new Integer(pid));
            query.setParameter(1, new Integer(pempid));
            if (query.uniqueResult() == null) {
              return "0";
            } else {
              return query.uniqueResult().toString();
            }
          }
        });

    return money;
  }

  /**
   * 根据AA316.ID，判断DA001中是否存在DA001.ORG_HEAD_ID = 单位版AA316.ID的记录。如果存在返回true
   * 
   * @author wangy 2007-12-21
   * @param id AA316.ID
   * @return
   */
  public boolean isExistsOVAccBalance(final String id, final String orgId, 
      final SecurityInfo securityInfo) {
    boolean flag = true;
    String daId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.id from da001 t where t.status=1 and t.type='H' and t.org_head_id=? and t.org_id=? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);
            query.setParameter(1, orgId);
            if (query.uniqueResult()==null) {
              return null;
            } else {
              return query.uniqueResult().toString();
            }
          }
        });
    if (daId == null || daId.equals("")) {
      flag = false;
    }
    return flag;
  }

  /**
   * 通过OrgId,accYear,查询中心版AA316.ID
   * 
   * @author wangy 2007-12-21
   * @param orgId 单位编号
   * @param accYear 结转年度
   * @return
   */
  public String querySettInterestHeadIdByOrgIdAndYear(final String orgId, final String accYear) {
    String aa316Id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select a316.id from aa316 a316 where a316.type = 'A' and a316.org_id=? and a316.year=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, new Integer(orgId));
            query.setParameter(1, accYear);
            if (query.uniqueResult()==null) {
              return null;
            } else {
              return query.uniqueResult().toString();
            }
          }
        });
    return aa316Id;
  }
}
