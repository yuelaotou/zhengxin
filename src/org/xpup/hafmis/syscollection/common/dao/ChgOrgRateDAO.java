package org.xpup.hafmis.syscollection.common.dao;

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
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.dto.ChgbizinfoDTO;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationTbDTO;

import java.util.*;

/**
 * @author 卢钢 2007-6-15
 */
public class ChgOrgRateDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public ChgOrgRate queryById(Integer id) {
    Validate.notNull(id);
    return (ChgOrgRate) getHibernateTemplate().get(ChgOrgRate.class, id);
  }

  /**
   * 插入记录
   * 
   * @param chgOrgRate
   * @return
   */
  public Serializable insert(ChgOrgRate chgOrgRate) {
    Serializable id = null;
    try {
      Validate.notNull(chgOrgRate);
      id = getHibernateTemplate().save(chgOrgRate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 王菱 删除单个记录
   * 
   * @param chgOrgRate
   */
  public void delete_WL(ChgOrgRate chgOrgRate) {
    Validate.notNull(chgOrgRate);
    getHibernateTemplate().delete(chgOrgRate);
  }

  /**
   * 王菱 判断是否存在未启用的汇缴比例调整
   * 
   * @param 单位ID
   * @return boolean:false－未启用；true－启用
   */
  public boolean getChgStatus(final Integer orgId) {
    Validate.notNull(orgId);

    boolean chgStatus = false;
    Integer TEMP_status = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select chgOrgRate.chgStatus from ChgOrgRate chgOrgRate where chgOrgRate.chgStatus=1 and chgOrgRate.org.id=? ";

            Query query = session.createQuery(hql);
            query.setParameter(0, orgId);

            return query.uniqueResult();
          }
        });
    if (TEMP_status == null) {
      chgStatus = true;
    } else {
      if (TEMP_status.equals(new Integer(1))) {
        chgStatus = false;
      } else {
        chgStatus = true;
      }
    }

    return chgStatus;
  }

  /**
   * 李娟 根据单位编号和缴存ID查询 职工增加减少金额
   * 
   * @param orgid
   * @param payheadId
   * @return
   */
  public BigDecimal queryEmpRateMoney(final Serializable orgid,
      final Serializable payheadId) {
    BigDecimal ratemoney = null;
    ratemoney = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(chgOrgRate.empPay-chgOrgRate.oldEmpPay) "
                + "from ChgOrgRate chgOrgRate ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null && !orgid.equals("")) {
              criterion += "chgOrgRate.org.id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }

            if (payheadId != null && !payheadId.equals("")) {
              criterion += "chgOrgRate.paymentHead.id = ?  and ";
              parameters.add(new Integer(payheadId.toString()));
            } else {
              criterion += "chgOrgRate.paymentHead.id is null and ";
            }
            if (criterion.length() != 0)
              criterion = "where chgOrgRate.chgStatus=2 and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();
          }
        });
    if (ratemoney == null) {
      ratemoney = new BigDecimal(0.00);
    }
    return ratemoney;
  }

  /**
   * 李娟 根据单位编号和缴存ID查询 单位增加减少金额
   * 
   * @param orgid
   * @param payheadId
   * @return
   */
  public BigDecimal queryOrgRateMoney(final Serializable orgid,
      final Serializable payheadId) {
    BigDecimal ratemoney = null;
    ratemoney = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(chgOrgRate.orgPay-chgOrgRate.oldOrgPay) "
                + "from ChgOrgRate chgOrgRate ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null && !orgid.equals("")) {
              criterion += "chgOrgRate.org.id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }

            if (payheadId != null && !payheadId.equals("")) {
              criterion += "chgOrgRate.paymentHead.id = ?  and ";
              parameters.add(new Integer(payheadId.toString()));
            } else {
              criterion += "chgOrgRate.paymentHead.id is null and ";
            }
            if (criterion.length() != 0)
              criterion = "where chgOrgRate.chgStatus=2 and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();
          }
        });
    if (ratemoney == null) {
      ratemoney = new BigDecimal(0.00);
    }
    return ratemoney;
  }

  /**
   * 李娟 根据缴存头表ID及启用状态查询
   * 
   * @param payHeadId
   * @param chgStatus
   * @return
   */
  public List queryChgOrgRateByPayHeadId(final Serializable orgid,
      final Serializable payHeadId, final Integer chgStatus) {
    List chgOrgRate = null;
    chgOrgRate = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from ChgOrgRate chgOrgRate ";
        Vector parameters = new Vector();
        String criterion = "";
        if (orgid != null&&!orgid.equals("")) {
          criterion += "chgOrgRate.org.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
        }
        if (payHeadId != null && !payHeadId.equals("")) {
          criterion += "chgOrgRate.paymentHead.id = ?  and ";
          parameters.add(new Integer(payHeadId.toString()));
        } else {
          criterion += "chgOrgRate.paymentHead.id is null and ";
        }

        if (chgStatus != null && !chgStatus.equals("")) {
          criterion += "chgOrgRate.chgStatus = ?  and ";
          parameters.add(chgStatus);
        }

        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
        }
      });

    return chgOrgRate;
  }

  /**
   * 王菱 可以进行汇缴比例调整业务的，进行页面显示信息的查询
   */
  public ChgOrgRate getChgOrgRateMessage_WL(final String orgID)
      throws BusinessException {
    ChgOrgRate chgOrgRate = null;
    chgOrgRate = (ChgOrgRate) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgOrgRate chgOrgRate ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgID != null && !orgID.equals("")) {
              criterion += "chgOrgRate.org.id = ?  and ";
              parameters.add(new Integer(orgID.toString()));
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
    return chgOrgRate;
  }

  /**
   * 王菱 根据单位ID和清册状态 得到AA201表信息
   */
  public ChgOrgRate getOrgRateMessage_WL(final String orgID, final String status)
      throws BusinessException {
    ChgOrgRate chgOrgRate = null;
    chgOrgRate = (ChgOrgRate) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgOrgRate chgOrgRate ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgID != null && !orgID.equals("")) {
              criterion += "chgOrgRate.org.id = ?  and ";
              parameters.add(new Integer(orgID));
            }
            if (status != null && !status.equals("")) {
              criterion += "chgOrgRate.chgStatus = ?  and ";
              parameters.add(new Integer(status));
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
    return chgOrgRate;
  }

  /**
   * 王菱 根据条件查询出汇缴比例调整维护列表 第一次
   */
  public List getChgOrgRateList_WL(final String orderBy, final String order,
      final int start, final int pageSize, final int page,
      final SecurityInfo securityInfo) {
    List chgOrgRateList = null;
    chgOrgRateList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgOrgRate chgOrgRate ";
            String criterion = " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and  chgOrgRate.paymentHead.id is null ";

            String ob = orderBy;
            if (ob == null)
              ob = " chgOrgRate.chgStatus ASC,chgOrgRate.id DESC ";

            String od = order;
            if (od == null)
              od = "";

            hql = hql + criterion + "  order by " + ob + " " + od;

            Query query = session.createQuery(hql);

            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);

            List queryList = query.list();

            if (queryList == null || queryList.size() == 0) {
              query.setFirstResult(pageSize * (page - 2));
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              queryList = query.list();
            }
            return queryList;
          }
        });
    return chgOrgRateList;
  }

  /**
   * 王菱 根据条件查询出汇缴比例调整维护列表的条数 第一次
   */
  public int queryChgorgrateMaintainList(final SecurityInfo securityInfo) {

    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgOrgRate chgOrgRate ";
          String criterion = " where chgOrgRate.org.id "
              + securityInfo.getGjjSecurityHqlSQL()
              + " and  chgOrgRate.paymentHead.id is null ";

          hql = hql + criterion;

          session.clear();
          Query query = session.createQuery(hql);

          return query.list();
        }
      }

      );
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 王菱 根据条件查询出汇缴比例调整维护列表
   */
  public List getChgOrgRateListByCriterions_WL(final String orderBy,
      final String order, final int start, final int pageSize,
      final String orgID, final String orgName, final String chgDate,
      final int page, final SecurityInfo securityInfo) {
    List chgOrgRateList = null;
    chgOrgRateList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgOrgRate chgOrgRate ";
            Vector parameters = new Vector();
            String criterion = "";
            if (orgID != null && !orgID.equals("")) {
              criterion += "To_Char(chgOrgRate.org.id) like ? and ";
              parameters.add("%" + orgID + "%");
            }
            if (orgName != null && !orgName.equals("")) {
              criterion += " chgOrgRate.org.orgInfo.name like ? and ";
              parameters.add("%" + orgName + "%");
            }

            if (chgDate != null && !chgDate.equals("")) {
              criterion += "chgOrgRate.chgMonth = ?  and ";
              parameters.add(chgDate);
            }

            if (criterion.length() != 0) {
              criterion = "where chgOrgRate.org.id "
                  + securityInfo.getGjjSecurityHqlSQL() + " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            } else {
              criterion = "where chgOrgRate.org.id "
                  + securityInfo.getGjjSecurityHqlSQL();
            }

            String ob = orderBy;
            if (ob == null)
              ob = " chgOrgRate.org.id  ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "  order by " + ob + " " + od;

            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);

            List queryList = query.list();

            if (queryList == null || queryList.size() == 0) {
              query.setFirstResult(pageSize * (page - 2));
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              queryList = query.list();
            }
            return queryList;
          }
        });
    return chgOrgRateList;
  }
  public List getChgOrgRateListByCriterions_wsh(final String orderBy,
      final String order, final int start, final int pageSize,
      final String orgID, final String orgName, final String chgDate,final String endDate,
      final int page, final SecurityInfo securityInfo) {
    List chgOrgRateList = null;
    chgOrgRateList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgOrgRate chgOrgRate ";
            Vector parameters = new Vector();
            String criterion = "";
            if (orgID != null && !orgID.equals("")) {
              criterion += "To_Char(chgOrgRate.org.id) like ? and ";
              parameters.add("%" + orgID + "%");
            }
            if (orgName != null && !orgName.equals("")) {
              criterion += " chgOrgRate.org.orgInfo.name like ? and ";
              parameters.add("%" + orgName + "%");
            }

            if (chgDate != null && !chgDate.equals("")) {
              criterion += "chgOrgRate.chgMonth >= ?  and ";
              parameters.add(chgDate);
            }
            if (endDate != null && !endDate.equals("")) {
              criterion += "chgOrgRate.chgMonth <= ?  and ";
              parameters.add(endDate);
            }

            if (criterion.length() != 0) {
              criterion = "where chgOrgRate.org.id "
                  + securityInfo.getGjjSecurityHqlSQL() + " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            } else {
              criterion = "where chgOrgRate.org.id "
                  + securityInfo.getGjjSecurityHqlSQL();
            }

            String ob = orderBy;
            if (ob == null)
              ob = " chgOrgRate.chgStatus ASC,chgOrgRate.id DESC ";

            String od = order;
            if (od == null)
              od = "";

            hql = hql + criterion + " order by " + ob + " " + od;

            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);

            List queryList = query.list();

            if (queryList == null || queryList.size() == 0) {
              query.setFirstResult(pageSize * (page - 2));
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              queryList = query.list();
            }
            return queryList;
          }
        });
    return chgOrgRateList;
  }

  /**
   * 王菱 根据条件查询出汇缴比例调整维护列表的条数
   */
  public int queryChgorgrateMaintainListByCriterions(final String orgID,
      final String orgName, final String chgDate,
      final SecurityInfo securityInfo) {

    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgOrgRate chgOrgRate ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgID != null && !orgID.equals("")) {
            criterion += "To_Char(chgOrgRate.org.id) like ? and ";
            parameters.add("%" + orgID + "%");
          }
          if (orgName != null && !orgName.equals("")) {
            criterion += " chgOrgRate.org.orgInfo.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgDate != null && !chgDate.equals("")) {
            criterion += "chgOrgRate.chgMonth = ?  and ";
            parameters.add(chgDate);
          }

          if (criterion.length() != 0) {
            criterion = "where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = "where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          hql = hql + criterion;

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  public int queryChgorgrateMaintainListByCriterions_wsh(final String orgID,
      final String orgName, final String chgDate,final String endDate,
      final SecurityInfo securityInfo) {

    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgOrgRate chgOrgRate ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgID != null && !orgID.equals("")) {
            criterion += "To_Char(chgOrgRate.org.id) like ? and ";
            parameters.add("%" + orgID + "%");
          }
          if (orgName != null && !orgName.equals("")) {
            criterion += " chgOrgRate.org.orgInfo.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgDate != null && !chgDate.equals("")) {
            criterion += "chgOrgRate.chgMonth >= ?  and ";
            parameters.add(chgDate);
          }
          if (endDate != null && !endDate.equals("")) {
            criterion += "chgOrgRate.chgMonth <= ?  and ";
            parameters.add(endDate);
          }

          if (criterion.length() != 0) {
            criterion = "where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = "where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          hql = hql + criterion;

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 于庆丰－－－根据各种条件查询
   * 
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public List queryChgorgrateListByCriterions(final String officeCode,
      final String collectionBank, final String orgId, final String orgName,
      final String chgMonthStart, final String chgMonthEnd,
      final String chgDateStart, final String chgDateEnd,
      final Integer chgStatus, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgOrgRate chgOrgRate";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null && !officeCode.equals("")) {// 办事处
            criterion += "chgOrgRate.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
            criterion += "chgOrgRate.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null && !orgId.equals("")) {// 单位编号
            criterion += "to_char(chgOrgRate.org.id) like ? and ";
            int index = 0;
            for(int i=0;i<orgId.length();i++){          
              String stri = orgId.substring(i, i+1);
              if(!"0".equals(stri)){
                index = i;
                break;
              }
            }
            String orgid = orgId.substring(index, orgId.length());
            parameters.add("%" + orgid + "%");

          }

          if (orgName != null && !orgName.equals("")) {// 单位名称
            criterion += "chgOrgRate.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && chgMonthEnd != null && !chgMonthEnd.equals("")) {// 有开始年月结束年月
            criterion += "(chgOrgRate.chgMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && (chgMonthEnd == null || chgMonthEnd.equals(""))) {// 有开始年月无结束年月
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthStart);
          }

          if (chgMonthEnd != null && !chgMonthEnd.equals("")
              && (chgMonthStart == null || chgMonthStart.equals(""))) {// 无开始年月有结束年月
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && chgDateEnd != null && !chgDateEnd.equals("")) {// 有开始日期结束日期
            criterion += " (chgOrgRate.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && (chgDateEnd == null || chgDateEnd.equals(""))) {// 有开始日期无结束日期
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (chgDateEnd != null && !chgDateEnd.equals("")
              && (chgDateStart == null || chgDateStart.equals(""))) {// 无开始日期有结束日期
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateEnd);
          }

          if (chgStatus != null && !chgStatus.equals("")) {// 业务状态
            criterion += " chgOrgRate.chgStatus = ? and ";
            parameters.add(chgStatus);
          }

          if (criterion.length() != 0) {
            criterion = " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql + " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }
          String ob = orderBy;
          if (ob == null)
            ob = " chgOrgRate.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "  order by " + ob + " " + od;
          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          return query.list();
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 于庆丰 根据条件查询总条数(分页)
   * 
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public List queryChgorgrateListSizeByCriterions(final String officeCode,
      final String collectionBank, final String orgId, final String orgName,
      final String chgMonthStart, final String chgMonthEnd,
      final String chgDateStart, final String chgDateEnd,
      final Integer chgStatus, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgOrgRate chgOrgRate";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null && !officeCode.equals("")) {// 办事处
            criterion += "chgOrgRate.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
            criterion += "chgOrgRate.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null && !orgId.equals("")) {// 单位编号
            criterion += "to_char(chgOrgRate.org.id) like ?  and ";
            parameters.add("%" + orgId + "%");
          }

          if (orgName != null && !orgName.equals("")) {// 单位名称
            criterion += "chgOrgRate.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && chgMonthEnd != null && !chgMonthEnd.equals("")) {// 有开始年月结束年月
            criterion += "(chgOrgRate.chgMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && (chgMonthEnd == null || chgMonthEnd.equals(""))) {// 有开始年月无结束年月
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthStart);
          }

          if (chgMonthEnd != null && !chgMonthEnd.equals("")
              && (chgMonthStart == null || chgMonthStart.equals(""))) {// 无开始年月有结束年月
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && chgDateEnd != null && !chgDateEnd.equals("")) {// 有开始日期结束日期
            criterion += " (chgOrgRate.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && (chgDateEnd == null || chgDateEnd.equals(""))) {// 有开始日期无结束日期
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (chgDateEnd != null && !chgDateEnd.equals("")
              && (chgDateStart == null || chgDateStart.equals(""))) {// 无开始日期有结束日期
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateEnd);
          }

          if (chgStatus != null && !chgStatus.equals("")) {// 业务状态
            criterion += " chgOrgRate.chgStatus = ? and ";
            parameters.add(chgStatus);
          }

          if (criterion.length() != 0) {
            criterion = " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
          } else {
            hql = hql + " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /**
   * 于庆丰 根据条件统计单位数（无重复）
   * 
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public List queryChgorgrateOrgSizeByCriterions(final String officeCode,
      final String collectionBank, final String orgId, final String orgName,
      final String chgMonthStart, final String chgMonthEnd,
      final String chgDateStart, final String chgDateEnd,
      final Integer chgStatus, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {

    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct chgOrgRate.org.id from ChgOrgRate chgOrgRate";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null && !officeCode.equals("")) {// 办事处
            criterion += "chgOrgRate.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
            criterion += "chgOrgRate.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null && !orgId.equals("")) {// 单位编号
            criterion += "to_char(chgOrgRate.org.id) like ?  and ";
            parameters.add("%" + orgId + "%");
          }

          if (orgName != null && !orgName.equals("")) {// 单位名称
            criterion += "chgOrgRate.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && chgMonthEnd != null && !chgMonthEnd.equals("")) {// 有开始年月结束年月
            criterion += "(chgOrgRate.chgMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && (chgMonthEnd == null || chgMonthEnd.equals(""))) {// 有开始年月无结束年月
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthStart);
          }

          if (chgMonthEnd != null && !chgMonthEnd.equals("")
              && (chgMonthStart == null || chgMonthStart.equals(""))) {// 无开始年月有结束年月
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && chgDateEnd != null && !chgDateEnd.equals("")) {// 有开始日期结束日期
            criterion += " (chgOrgRate.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && (chgDateEnd == null || chgDateEnd.equals(""))) {// 有开始日期无结束日期
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (chgDateEnd != null && !chgDateEnd.equals("")
              && (chgDateStart == null || chgDateStart.equals(""))) {// 无开始日期有结束日期
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateEnd);
          }

          if (chgStatus != null && !chgStatus.equals("")) {// 业务状态
            criterion += " chgOrgRate.chgStatus = ? and ";
            parameters.add(chgStatus);
          }

          if (criterion.length() != 0) {
            criterion = " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
          } else {
            hql = hql + " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  /**
   * 于庆丰 根据条件统计单位数（变更笔数）（有重复）
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryCountsByCriterions(final String officeCode,
      final String collectionBank, final String orgId, final String orgName,
      final String chgMonthStart, final String chgMonthEnd,
      final String chgDateStart, final String chgDateEnd,
      final Integer chgStatus, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {

    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select chgOrgRate.org.id from ChgOrgRate chgOrgRate";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null && !officeCode.equals("")) {// 办事处
            criterion += "chgOrgRate.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
            criterion += "chgOrgRate.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null && !orgId.equals("")) {// 单位编号
            criterion += "to_char(chgOrgRate.org.id) like ?  and ";
            parameters.add("%" + orgId + "%");
          }

          if (orgName != null && !orgName.equals("")) {// 单位名称
            criterion += "chgOrgRate.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && chgMonthEnd != null && !chgMonthEnd.equals("")) {// 有开始年月结束年月
            criterion += "(chgOrgRate.chgMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && (chgMonthEnd == null || chgMonthEnd.equals(""))) {// 有开始年月无结束年月
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthStart);
          }

          if (chgMonthEnd != null && !chgMonthEnd.equals("")
              && (chgMonthStart == null || chgMonthStart.equals(""))) {// 无开始年月有结束年月
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && chgDateEnd != null && !chgDateEnd.equals("")) {// 有开始日期结束日期
            criterion += " (chgOrgRate.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && (chgDateEnd == null || chgDateEnd.equals(""))) {// 有开始日期无结束日期
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (chgDateEnd != null && !chgDateEnd.equals("")
              && (chgDateStart == null || chgDateStart.equals(""))) {// 无开始日期有结束日期
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateEnd);
          }

          if (chgStatus != null && !chgStatus.equals("")) {// 业务状态
            criterion += " chgOrgRate.chgStatus = ? and ";
            parameters.add(chgStatus);
          }

          if (criterion.length() != 0) {
            criterion = " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
          } else {
            hql = hql + " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }


  /**
   * 于庆丰
   */
  public BigDecimal queryRate(final Integer id) {

    BigDecimal rate = new BigDecimal(0);
    try {
      rate = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select round(((a.org_pay+a.emp_pay)-(a.old_org_pay+a.old_emp_pay))/(a.old_org_pay+a.old_emp_pay),2) from aa201 a where a.id = "
                  + id;
              Query query = session.createSQLQuery(sql);
              return new BigDecimal(query.uniqueResult().toString());

              /**
               * Query query = session.createSQLQuery(sql); //
               * query.setParameter(0, parameters.get(0)); Object obj[] = null;
               * BigDecimal chgorgrate = new BigDecimal(0.00); Iterator iterate =
               * query.list().iterator(); while (iterate.hasNext()) { obj =
               * (Object[]) iterate.next(); chgorgrate=new
               * BigDecimal(obj[0].toString()); } return chgorgrate;
               */
            }
          }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return rate;
  }
  /**
   * 于庆丰 COUNT 201
   * @param office
   * @param bank
   * @param orgCharacter
   * @param dept
   * @param ragion
   * @param startDate
   * @param endDate
   * @return
   */
  public int queryCount(final String office,final String bank,final String orgCharacter,
      final String dept,final String ragion,final String startDate,final String endDate){
    int count = 0;
    try{
      Integer countInteger = (Integer)getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
     
        String hql = "select count(chgOrgRate.id) from ChgOrgRate chgOrgRate ";
        Vector parameters = new Vector();
        String criterion = "";
        if(office != null && !"".equals(office)){
          criterion += " chgOrgRate.org.orgInfo.officecode = ? and ";
          parameters.add(office);
        }
        if(bank != null && !"".equals(bank)){
          criterion += " chgOrgRate.org.orgInfo.collectionBankId = ? and ";
          parameters.add(bank);
        }
        if(orgCharacter != null && !"".equals(orgCharacter)){
          criterion += " chgOrgRate.org.orgInfo.character = ? and ";
          parameters.add(orgCharacter);
        }
        if(dept != null && !"".equals(dept)){
          criterion += " chgOrgRate.org.orgInfo.deptInCharge = ? and ";
          parameters.add(dept);
        }
        if(ragion != null && !"".equals(ragion)){
          criterion += " chgOrgRate.org.orgInfo.region = ? and ";
          parameters.add(ragion);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
          criterion += " (chgOrgRate.bizDate  between ?  and  ?)  and ";
          parameters.add(startDate);
          parameters.add(endDate);
        }
        if (criterion.length() != 0) 
//          criterion = "where (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id  "
//              + securityInfo.getGjjSecurityHqlSQL()
//              + " and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
          criterion = " where chgOrgRate.chgStatus=2 and "
            + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion ;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Integer countTemp=new Integer(0);
          Iterator it=query.iterate();          
          if(it.hasNext()){
            countTemp=(Integer)it.next();
          }
          return countTemp;
      }
    });
      count = countInteger.intValue();
    }catch(Exception e){
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * 于庆丰 sum 
   * @param office
   * @param bank
   * @param orgCharacter
   * @param dept
   * @param ragion
   * @param startDate
   * @param endDate
   * @return
   */
  public int querySum(final String office,final String bank,final String orgCharacter,
      final String dept,final String ragion,final String startDate,final String endDate){
    int count = 0;
    try{
      Integer countInteger = (Integer)getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
     
        String hql = "select sum((chgOrgRate.orgPay+chgOrgRate.empPay)-(chgOrgRate.oldOrgPay+chgOrgRate.oldEmpPay)) from ChgOrgRate chgOrgRate ";
        Vector parameters = new Vector();
        String criterion = "";
        if(office != null && !"".equals(office)){
          criterion += " chgOrgRate.org.orgInfo.officecode = ? and ";
          parameters.add(office);
        }
        if(bank != null && !"".equals(bank)){
          criterion += " chgOrgRate.org.orgInfo.collectionBankId = ? and ";
          parameters.add(bank);
        }
        if(orgCharacter != null && !"".equals(orgCharacter)){
          criterion += " chgOrgRate.org.orgInfo.character = ? and ";
          parameters.add(orgCharacter);
        }
        if(dept != null && !"".equals(dept)){
          criterion += " chgOrgRate.org.orgInfo.deptInCharge = ? and ";
          parameters.add(dept);
        }
        if(ragion != null && !"".equals(ragion)){
          criterion += " chgOrgRate.org.orgInfo.region = ? and ";
          parameters.add(ragion);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
          criterion += " (chgOrgRate.bizDate  between ?  and  ?)  and ";
          parameters.add(startDate);
          parameters.add(endDate);
        }
        if (criterion.length() != 0) 
//          criterion = "where (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id  "
//              + securityInfo.getGjjSecurityHqlSQL()
//              + " and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
          criterion = " where  "
            + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion ;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          BigDecimal countTemp=new BigDecimal(0.00);
          Iterator it=query.iterate();          
          if(it.hasNext()){
            countTemp=(BigDecimal)it.next();
          }
          if(countTemp == null){
            return new Integer(0);
          }else{
          return new Integer(countTemp.intValue());
          }
      }
    });
      count = countInteger.intValue();
    }catch(Exception e){
      e.printStackTrace();
    }
    return count;
  }

 public List  queryChgbizinfo_wsh1(final String orgId,final String orgName,final String chgMonth,final String bizDate){
   List list=null;
   try {
     
     list =getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session)
             throws HibernateException, SQLException {
           String hql ="select b.emp_id," +
              "f.name," +
              "f.card_num," +
              "'02'" +
              ",b.org_rate," +
              "b.emp_rate" +
              ",b.salary_base" +
              ",b.org_pay" +
              ",b.emp_pay" +
              ",b.emp_pay+b.org_pay," +
              "b.org_pay + b.emp_pay-b.old_org_pay-b.old_emp_pay " +
              "from aa201 a, aa201_1 b, aa001 c, ba001 d,aa002 e,ba002 f" +
              " where a.org_id = c.id " +
              "and c.orginfo_id = d.id " +
              "and a.id = b.chng_head_id " +
              "and e.org_id=c.id " +
              "and f.id=e.emp_info_id " +
              "and e.id=b.emp_id " ;
             // " a.org_id = ? order by a.id "; 
           Vector parameters = new Vector();
           String criterion = "";          
           if (orgId != null&&!orgId.equals("")) {
             criterion += " a.org_id =? and ";
             parameters.add(orgId);
           }
           if (orgName != null&&!orgName.equals("")) {
             criterion += " d.name=? and ";
             parameters.add(orgName);
           }
                             
           if (chgMonth != null&&!chgMonth.equals("")) {
             criterion += " a.CHG_MONTH=? and ";
             parameters.add(chgMonth);
           }  
           if (bizDate != null&&!bizDate.equals("")) {
             criterion += " a.BIZ_DATE=? and ";
             parameters.add(bizDate);
           } 
           if (criterion.length() != 0)   
             criterion=" and "+
             criterion.substring(0, criterion.lastIndexOf("and"));
        
          hql = hql + criterion + "order by a.id " ;
          Query query = session.createSQLQuery(hql);          
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it=query.list().iterator();
          List temp_list=new ArrayList();
          Object obj[]=null;
          while(it.hasNext()){
            obj=(Object[])it.next();
            if(obj!=null){
              ChgbizinfoDTO chgbizinfoDTO=new ChgbizinfoDTO();             
              if(obj[0]!=null)
                chgbizinfoDTO.setEmpid(obj[0].toString());
              else
                chgbizinfoDTO.setEmpid("");
              if(obj[1]!=null)
                chgbizinfoDTO.setEmpnamem(obj[1].toString());
              else
                chgbizinfoDTO.setEmpnamem("");
              if(obj[2]!=null)
                chgbizinfoDTO.setCardnumm(obj[2].toString());
              else
                chgbizinfoDTO.setCardnumm("");
              if(obj[3]!=null)
                chgbizinfoDTO.setType("02");
              else
                chgbizinfoDTO.setType("");
              if(obj[4]!=null)
                chgbizinfoDTO.setOrgRatem(obj[4].toString());
              else
                chgbizinfoDTO.setOrgRatem("");
              if(obj[5]!=null)
                chgbizinfoDTO.setEmpRatem(obj[5].toString());
              else
                chgbizinfoDTO.setEmpRatem("");
              if(obj[6]!=null)
                chgbizinfoDTO.setSalaryBasem(obj[6].toString());
              else
                chgbizinfoDTO.setSalaryBasem("");
              if(obj[7]!=null)
                chgbizinfoDTO.setOrgPay(obj[7].toString());
              else
                chgbizinfoDTO.setOrgPay("");
              if(obj[8]!=null)
                chgbizinfoDTO.setEmpPay(obj[8].toString());
              else
                chgbizinfoDTO.setEmpPay("");
              if(obj[9]!=null)
                chgbizinfoDTO.setPaySumm(obj[9].toString());
              else
                chgbizinfoDTO.setPaySumm("");
              if(obj[10]!=null)
                chgbizinfoDTO.setOldPaySum(obj[10].toString());
              else
                chgbizinfoDTO.setOldPaySum("");
              temp_list.add(chgbizinfoDTO);              
            }            
          }
          return temp_list;
         }
       }
   );
   } catch (Exception e) {
     e.printStackTrace();
   }
   return list;
 }
 public List  queryChgbizinfo_wsh2(final String orgId,final String orgName,final String chgMonth,final String bizDate){
   List list=null;
   try {
     
     list =getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session)
             throws HibernateException, SQLException {
           String hql ="select b.emp_id," +
              "f.name," +
              "f.card_num," +
              "'02'" +
              ",b.reservea_a," +
              "b.reservea_b" +
              ",b.salary_base" +
              ",b.org_pay" +
              ",b.emp_pay" +
              ",b.emp_pay+b.org_pay," +
              " b.emp_pay + b.org_pay-b.old_org_pay-b.old_emp_pay " +
              "from aa202 a, aa203 b, aa001 c, ba001 d,aa002 e,ba002 f" +
              " where a.org_id = c.id " +
              "and c.orginfo_id = d.id " +
              "and a.id = b.chng_head_id " +
              "and e.org_id=c.id " +
              "and f.id=e.emp_info_id " +
              "and e.id=b.emp_id " ;
             // " a.org_id = ? order by a.id "; 
           Vector parameters = new Vector();
           String criterion = "";          
           if (orgId != null&&!orgId.equals("")) {
             criterion += " a.org_id =? and ";
             parameters.add(orgId);
           }
           if (orgName != null&&!orgName.equals("")) {
             criterion += " d.name=? and ";
             parameters.add(orgName);
           }
                             
           if (chgMonth != null&&!chgMonth.equals("")) {
             criterion += " a.chg_month=? and ";
             parameters.add(chgMonth);
           }  
           if (bizDate != null&&!bizDate.equals("")) {
             criterion += " a.BIZ_DATE=? and ";
             parameters.add(bizDate);
           } 
           if (criterion.length() != 0)   
             criterion=" and "+
             criterion.substring(0, criterion.lastIndexOf("and"));
        
          hql = hql + criterion + "order by a.id " ;
          Query query = session.createSQLQuery(hql);          
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it=query.list().iterator();
          List temp_list=new ArrayList();
          Object obj[]=null;
          while(it.hasNext()){
            obj=(Object[])it.next();
            if(obj!=null){
              ChgbizinfoDTO chgbizinfoDTO=new ChgbizinfoDTO();             
              if(obj[0]!=null)
                chgbizinfoDTO.setEmpid(obj[0].toString());
              else
                chgbizinfoDTO.setEmpid("");
              if(obj[1]!=null)
                chgbizinfoDTO.setEmpnamem(obj[1].toString());
              else
                chgbizinfoDTO.setEmpnamem("");
              if(obj[2]!=null)
                chgbizinfoDTO.setCardnumm(obj[2].toString());
              else
                chgbizinfoDTO.setCardnumm("");
              if(obj[3]!=null)
                chgbizinfoDTO.setType("01");
              else
                chgbizinfoDTO.setType("");
              if(obj[4]!=null)
                chgbizinfoDTO.setOrgRatem(obj[4].toString());
              else
                chgbizinfoDTO.setOrgRatem("");
              if(obj[5]!=null)
                chgbizinfoDTO.setEmpRatem(obj[5].toString());
              else
                chgbizinfoDTO.setEmpRatem("");
              if(obj[6]!=null)
                chgbizinfoDTO.setSalaryBasem(obj[6].toString());
              else
                chgbizinfoDTO.setSalaryBasem("");
              if(obj[7]!=null)
                chgbizinfoDTO.setOrgPay(obj[7].toString());
              else
                chgbizinfoDTO.setOrgPay("");
              if(obj[8]!=null)
                chgbizinfoDTO.setEmpPay(obj[8].toString());
              else
                chgbizinfoDTO.setEmpPay("");
              if(obj[9]!=null)
                chgbizinfoDTO.setPaySumm(obj[9].toString());
              else
                chgbizinfoDTO.setPaySumm("");
              if(obj[10]!=null)
                chgbizinfoDTO.setOldPaySum(obj[10].toString());
              else
                chgbizinfoDTO.setOldPaySum("");
              temp_list.add(chgbizinfoDTO);              
            }            
          }
          return temp_list;
         }
       }
   );
   } catch (Exception e) {
     e.printStackTrace();
   }
   return list;
 }
 public List  queryChgbizinfo_wsh3(final String orgId,final String orgName,final String chgMonth,final String bizDate){
   List list=null;
   try {
     
     list =getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session)
             throws HibernateException, SQLException {
           String hql ="select b.emp_id," +
              "f.name," +
              "f.card_num," +
              "  case  when b.chg_type in ('2', '4') then  "+"03"+"   when b.chg_type = '1' then  "+"05"+"   when b.chg_type = '3' then "+"04"+"  end " +
              ",b.reservea_a," +
              "b.reservea_b" +
              ",b.salary_base" +
              ",b.org_pay" +
              ",b.emp_pay" +
              ",b.emp_pay+b.org_pay ," +
              "case when b.chg_type = '2' then 0  when b.chg_type = '4' then  0 - b.emp_pay - b.org_pay when b.chg_type in ('1', '3') then b.org_pay + b.emp_pay end " +
              "from aa204 a, aa205 b, aa001 c, ba001 d,aa002 e,ba002 f" +
              " where a.org_id = c.id " +
              "and c.orginfo_id = d.id " +
              "and a.id = b.chg_head_id " +
              "and e.org_id=c.id " +
              "and f.id=e.emp_info_id " +
              "and e.id=b.emp_id " ;
             // " a.org_id = ? order by a.id "; 
           Vector parameters = new Vector();
           String criterion = "";          
           if (orgId != null&&!orgId.equals("")) {
             criterion += " a.org_id =? and ";
             parameters.add(orgId);
           }
           if (orgName != null&&!orgName.equals("")) {
             criterion += " d.name=? and ";
             parameters.add(orgName);
           }
                             
           if (chgMonth != null&&!chgMonth.equals("")) {
             criterion += " a.CHNG_MONTH=? and ";
             parameters.add(chgMonth);
           }  
           if (bizDate != null&&!bizDate.equals("")) {
             criterion += " a.BIZ_DATE=? and ";
             parameters.add(bizDate);
           } 
           if (criterion.length() != 0)   
             criterion=" and "+
             criterion.substring(0, criterion.lastIndexOf("and"));
        
          hql = hql + criterion + "order by a.id " ;
          Query query = session.createSQLQuery(hql);          
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it=query.list().iterator();
          List temp_list=new ArrayList();
          Object obj[]=null;
          while(it.hasNext()){
            obj=(Object[])it.next();
            if(obj!=null){
              ChgbizinfoDTO chgbizinfoDTO=new ChgbizinfoDTO();             
              if(obj[0]!=null)
                chgbizinfoDTO.setEmpid(obj[0].toString());
              else
                chgbizinfoDTO.setEmpid("");
              if(obj[1]!=null)
                chgbizinfoDTO.setEmpnamem(obj[1].toString());
              else
                chgbizinfoDTO.setEmpnamem("");
              if(obj[2]!=null)
                chgbizinfoDTO.setCardnumm(obj[2].toString());
              else
                chgbizinfoDTO.setCardnumm("");
              if(obj[3]!=null){
                if("3".equals(obj[3].toString())){
                  chgbizinfoDTO.setType("03");
                }
                if("4".equals(obj[3].toString())){
                  chgbizinfoDTO.setType("04");
                }
                if("5".equals(obj[3].toString())){
                  chgbizinfoDTO.setType("05");
                }
              }
                
              else
                chgbizinfoDTO.setType("");
              if(obj[4]!=null)
                chgbizinfoDTO.setOrgRatem(obj[4].toString());
              else
                chgbizinfoDTO.setOrgRatem("");
              if(obj[5]!=null)
                chgbizinfoDTO.setEmpRatem(obj[5].toString());
              else
                chgbizinfoDTO.setEmpRatem("");
              if(obj[6]!=null)
                chgbizinfoDTO.setSalaryBasem(obj[6].toString());
              else
                chgbizinfoDTO.setSalaryBasem("");
              if(obj[7]!=null)
                chgbizinfoDTO.setOrgPay(obj[7].toString());
              else
                chgbizinfoDTO.setOrgPay("");
              if(obj[8]!=null)
                chgbizinfoDTO.setEmpPay(obj[8].toString());
              else
                chgbizinfoDTO.setEmpPay("");
              if(obj[9]!=null)
                chgbizinfoDTO.setPaySumm(obj[9].toString());
              else
                chgbizinfoDTO.setPaySumm("");
              if(obj[10]!=null)
                chgbizinfoDTO.setOldPaySum(obj[10].toString());
              else
                chgbizinfoDTO.setOldPaySum("");
              
              temp_list.add(chgbizinfoDTO);              
            }            
          }
          return temp_list;
         }
       }
   );
   } catch (Exception e) {
     e.printStackTrace();
   }
   return list;
 }
 public String[] queryOrgPaySum(final String orgId,final String month) {
   String str[] = new String [2];
   try {
     str = (String []) getHibernateTemplate().execute(new HibernateCallback() {
       public Object doInHibernate(Session session) throws HibernateException,
           SQLException {
         String sql = "select max(a.pay_money), " +
            "count(distinct c.emp_id) " +
            "from aa301 a, aa302 b, aa303 c " +
            "where a.id = b.pay_head_id  " +
            "and b.id = c.month_pay_head_id " +
            " and a.pay_type = 'A' " +
            "and a.org_id = ? and b.pay_month = ? ";
         Query query = session.createSQLQuery(sql);
         query.setParameter(0, orgId);
         query.setParameter(1, month);
         String str_1[]=new String [2];
         Object obj[]=null;
        
         if(query.uniqueResult()==null){
           return "";
         }else{
           
           obj=(Object[])query.uniqueResult();
           if(obj[0]!=null&&!"".equals(obj[0])){
             str_1[0]=obj[0].toString();
             }
           }
         if(obj[1]!=null&&!"".equals(obj[1])){
           str_1[1]=obj[1].toString();
           }
         
           return str_1;
         }
       
     });
   } catch (Exception e) {
     e.printStackTrace();
   }
   return str;
 }
 /**
  * 返回AA201、AA202、AA204中的最大ID根据单位编号和变更年月
  * 
  * @throws NumberFormatException
  * @throws Exception
  */
 public Integer getMaxHeadID_wsh(final String orgID,final String month)
     throws NumberFormatException, Exception {
   Integer maxHeadID = null;
   try {
     maxHeadID = (Integer) getHibernateTemplate().execute(
         new HibernateCallback() {
           public Object doInHibernate(Session session)
               throws HibernateException, SQLException {
             String hql0 = "select nvl(max(chgOrgRate.id),0) from ChgOrgRate chgOrgRate where  chgOrgRate.org.id=? and chgOrgRate.chgMonth=? ";
             Query query0 = session.createQuery(hql0);
             query0.setInteger(0, Integer.parseInt(orgID));
             query0.setString(1, month);
             String id0="";
             if(query0.uniqueResult()!=null){
               id0=query0.uniqueResult().toString();
             }

             String hql1 = "select nvl(max(chgPaymentHead.id),0) from ChgPaymentHead chgPaymentHead where chgPaymentHead.org.id=? and chgPaymentHead.chgMonth=? ";
             Query query1 = session.createQuery(hql1);
             query1.setInteger(0, Integer.parseInt(orgID));
             query1.setString(1, month);
             String id1="";
             if(query1.uniqueResult()!=null){
               id1=query1.uniqueResult().toString();
             }

             String hql2 = "select nvl(max(chgPersonHead.id),0) from  ChgPersonHead chgPersonHead where  chgPersonHead.org.id=? and chgPersonHead.chngMonth=? ";
             Query query2 = session.createQuery(hql2);
             query2.setInteger(0, Integer.parseInt(orgID));
             query2.setString(1, month);
             String id2="";
             if(query2.uniqueResult()!=null){
               id2=query2.uniqueResult().toString();
             }
             String result = "0";
             if(id0.compareTo(id1)>0){
               if(id0.compareTo(id2)>0){
                 result=id0;
               }else{
                 result=id2;
               }
             }else{
               if(id1.compareTo(id2)>0){
                 result=id1;
               }else{
                 result=id2;
               }
             }
             
             return new Integer(result.toString());
         }});
   } catch (Exception e) {
     e.printStackTrace();
   }
   return maxHeadID;
 }
}
