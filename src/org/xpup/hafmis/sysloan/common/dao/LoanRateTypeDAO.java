  package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanRateType;
import org.xpup.hafmis.sysloan.dataready.rate.dto.RateDTO;

public class LoanRateTypeDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public LoanRateType queryById(Serializable id) {
    Validate.notNull(id);
    return (LoanRateType) getHibernateTemplate().get(LoanRateType.class, id);
  }

  /**
   * 插入记录
   * 
   * @param loanRateType
   * @return
   */
  public Serializable insert(LoanRateType loanRateType) {
    Serializable id = null;
    try {
      Validate.notNull(loanRateType);
      id = getHibernateTemplate().save(loanRateType);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  public void delete(LoanRateType loanRateType) {
    getHibernateTemplate().delete(loanRateType);
  }
  public List queryRateTypeInfoList(final String rateType,final String rateExplain,
      final String rateDate,final String orderBy, final String order,
      final int start, final int pageSize, final int page){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " from LoanRateType loanRateType ";
              Vector parameters = new Vector();
              String criterion = "";

              if (rateType != null && !rateType.equals("")) {
                criterion += " loanRateType.loanRateType = ?  and ";
                parameters.add(rateType);
              }

              if (rateExplain != null && !rateExplain.equals("")) {
                criterion += " loanRateType.loanRateExplain = ?  and ";
                parameters.add(rateExplain);
              }

              if (rateDate != null && !rateDate.equals("")) {
                criterion += " loanRateType.loanRateDate = ?  and ";
                parameters.add(rateDate);
              }
              
              if (criterion.length() != 0)
                criterion = "where  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              
              String ob = orderBy;
              if (ob == null)
                ob = "loanRateType.id";
              String od = order;
              if (od == null)
                od = "DESC";
              
              hql = hql + criterion + " order by " + ob + " " + order;
              
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              
              return query.list();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  public List queryRateTypeInfoAllList(final String rateType,final String rateExplain,
      final String rateDate){
    List list = null;
    try {
      list =  getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " from LoanRateType loanRateType ";
              Vector parameters = new Vector();
              String criterion = "";

              if (rateType != null && !rateType.equals("")) {
                criterion += " loanRateType.loanRateType = ?  and ";
                parameters.add(rateType);
              }

              if (rateExplain != null && !rateExplain.equals("")) {
                criterion += " loanRateType.loanRateExplain = ?  and ";
                parameters.add(rateExplain);
              }

              if (rateDate != null && !rateDate.equals("")) {
                criterion += " loanRateType.loanRateDate = ?  and ";
                parameters.add(rateDate);
              }
              
              if (criterion.length() != 0)
                criterion = "where  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              
              hql = hql + criterion ;
              
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              
              return query.list();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 根据利率类型返回利率说明
   * @param rateType
   * @return
   */
  public String queryExplainByType(final String rateType){
    String str = null;
    try {
      str = (String)getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select loanRateType.loanRateExplain from LoanRateType loanRateType where loanRateType.loanRateType = ?";
              Query query = session.createQuery(hql);
              query.setParameter(0, rateType);
              return query.uniqueResult().toString();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }
}
