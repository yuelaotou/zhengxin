package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.dto.PaymntPickDetailDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.dto.OrgpaymentstatisticsDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.dto.PaymentYearDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.dto.PaymntMonRepInfoDTO;

/**
 * 缴存头表
 * 
 * @author 李娟 2007-6-19
 */
public class PaymentHeadDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public PaymentHead queryById(Serializable id) {
    Validate.notNull(id);
    return (PaymentHead) getHibernateTemplate().get(PaymentHead.class, id);
  }

  public void deletePaymentHead_lg(Integer id) {
    Validate.notNull(id);
    PaymentHead paymentHead = this.queryById(id);
    this.getHibernateTemplate().delete(paymentHead);
  }

  /**
   * 插入记录
   * 
   * @param paymentHead
   * @return
   */
  public Serializable insert(PaymentHead paymentHead) {
    Serializable id = null;
    try {
      Validate.notNull(paymentHead);
      id = getHibernateTemplate().save(paymentHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }

  /**
   * 根据单位编号查询
   * 
   * @param orgId
   * @return
   */
  public List queryByOrgId(final Integer id) {
    Validate.isTrue(id != null);

    List list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from PaymentHead paymentHead ";
            Vector parameters = new Vector();
            String criterion = "";

            if (id != null) {
              criterion += " paymentHead.org.id=? and ";
              parameters.add(id);
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.list();
          }
        });
    return list;
  }

  /**
   * @param id
   * @param name
   * @param orderBy
   * @param start
   * @param pageSize
   * @return list
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryDemoListByCriterions(final String orgId,
      final String orgName, final String orderBy, final int start,
      final int pageSize) throws NumberFormatException, Exception {
    List list = null;
    try {

      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from PaymentHead ph where ph.payStatus = 2 or ph.payStatus = 3  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {
            criterion += "PaymentHead.id= ?  and ";
            parameters.add(orgId);
          }

          if (orgName != null && !orgName.equals("")) {
            criterion += "PaymentHead.name = ?  and ";
            parameters.add(orgName);
          }

          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " PaymentHead.id";

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

  // /**
  // * 查询
  // * @param id
  // * @param name
  // * @param orderBy
  // * @param start
  // * @param pageSize
  // * @return list
  // * @throws NumberFormatException
  // * @throws Exception
  // */
  // public List queryDemoListByCriterions(final String orgId, final String
  // orgName,
  // final String orderBy, final int start,
  // final int pageSize) throws NumberFormatException, Exception {
  // List list = null;
  // try {
  //      
  // Validate.isTrue(start >= 0);
  //
  // list = getHibernateTemplate().executeFind(
  // new HibernateCallback() {
  //
  // public Object doInHibernate(Session session)
  // throws HibernateException, SQLException {
  //
  // String hql = " from PaymentHead ph where ph.payStatus = 2 or ph.payStatus =
  // 3 ";
  // Vector parameters = new Vector();
  // String criterion = "";
  //
  // if (orgId != null&&!orgId.equals("")) {
  // criterion += "PaymentHead.id= ? and ";
  // parameters.add(orgId);
  // }
  //              
  // if (orgName != null&&!orgName.equals("")) {
  // criterion += "PaymentHead.name = ? and ";
  // parameters.add(orgName);
  // }
  //
  // if (criterion.length() != 0)
  // criterion = "where "
  // + criterion.substring(0, criterion.lastIndexOf("and"));
  //
  // String ob = orderBy;
  // if (ob == null)
  // ob = " PaymentHead.id";
  //
  // Query query = session.createQuery(hql);
  // for (int i = 0; i < parameters.size(); i++) {
  // query.setParameter(i, parameters.get(i));
  // }
  //
  // query.setFirstResult(start);
  // if (pageSize > 0)
  // query.setMaxResults(pageSize);
  //              
  // return query.list();
  // }
  // }
  //
  // );
  //
  // } catch (Exception e) {
  // e.printStackTrace();
  // }
  //
  // return list;
  // }
  //

  /**
   * 默认查询aa301中payStatus = 2 or 3的记录 于庆丰
   * 
   * @return list
   */
  public List defaultQuery(final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {

    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          // String hql = " from PaymentHead paymentHead where
          // paymentHead.payStatus = 2 or paymentHead.payStatus = 3 and
          // paymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
          String hql = " from PaymentHead paymentHead where  paymentHead.org.id "
              + securityInfo.getGjjSecurityHqlSQL()
              + " and (paymentHead.payStatus = 2 or paymentHead.payStatus = 3) ";
          String ob = orderBy;
          if (ob == null)
            ob = " paymentHead.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          // hql = hql + "order by " + ob + ", " + " paymentHead.payStatus " +
          // order;
          hql = hql + " order by " + " paymentHead.payStatus " + " ," + ob
              + " " + " ASC ";

          Query query = session.createQuery(hql);

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
   * 根据条件查询aa301(如没有条件查询全部) 于庆丰
   * 
   * @param paymentFindAF
   * @return list
   */
  public List queryByCondition(final String orgId, final String orgName,
      final String docNum, final String noteNum, final String payMoney,
      final String settDate, final String settDate1, final String payType,
      final Integer payStatus, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {

    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          session.clear();
          String hql = " from PaymentHead paymentHead  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {// 部门编号
            // criterion += " paymentHead.org.id like ? and ";
            // parameters.add("%"+ orgId +"%");
            criterion += " paymentHead.org.id = ? and ";
            parameters.add(new Integer(orgId));
          }

          if (orgName != null && !orgName.equals("")) {// 部门名称
            criterion += " paymentHead.org.orgInfo.name like  ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (docNum != null && !docNum.equals("")) {// 凭证编号
            criterion += " paymentHead.docNum like ?  and ";
            parameters.add("%" + docNum + "%");
          }

          if (noteNum != null && !noteNum.equals("")) {// 票据编号
            criterion += "paymentHead.noteNum like ?  and ";
            parameters.add("%" + noteNum + "%");
          }

          if (payMoney != null && !payMoney.equals("")) {// 缴存金额
            criterion += "paymentHead.payMoney = ?  and ";
            parameters.add(new BigDecimal(payMoney));
          }

          if (settDate != null && !settDate.equals("") && settDate1 != null
              && !settDate1.equals("")) {
            criterion += " (paymentHead.settDate between ?  and  ? ) and ";
            parameters.add(settDate);
            parameters.add(settDate1);
          } else if (settDate != null && !settDate.equals("")) {
            criterion += " paymentHead.settDate = ? and ";
            parameters.add(settDate);
          } else if (settDate1 != null && !settDate1.equals("")) {
            criterion += " paymentHead.settDate = ? and ";
            parameters.add(settDate1);
          }
          if (payType != null && !payType.equals("")) {// 业务类型
            criterion += "paymentHead.payType_ = ?  and ";
            parameters.add(payType);
          }

          if (payStatus != null && !payStatus.equals("")) {// 业务状态
            criterion += "paymentHead.payStatus = ?  and ";
            parameters.add(payStatus);
          }

          if (criterion.length() != 0) {
            criterion = "where paymentHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql + "where paymentHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          String ob = orderBy;
          if (ob == null)
            ob = " paymentHead.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          // hql = hql + criterion + "order by " + ob + " " + order;
          hql = hql + criterion + "order by " + " paymentHead.payStatus " + ","
              + ob + " " + " ASC ";
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
   * 默认查询条数
   * 
   * @param id
   * @param name
   * @return
   */
  public List queryPaymentCountByCriterions(final SecurityInfo securityInfo) {

    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from PaymentHead paymentHead where  paymentHead.org.id "
              + securityInfo.getGjjSecurityHqlSQL()
              + " and (paymentHead.payStatus = 2 or paymentHead.payStatus = 3) order by paymentHead.payStatus,paymentHead.id DESC";
          Query query = session.createQuery(hql);
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
   * 默认条件查询条数
   * 
   * @param id
   * @param name
   * @return
   */
  public List queryPaymentCountByCriterions2(final String orgId,
      final String orgName, final String docNum, final String noteNum,
      final String payMoney, final String settDate, final String settDate1,
      final String payType, final Integer payStatus, final int start,
      final int pageSize, final String orderBy, final String order,
      final SecurityInfo securityInfo) {

    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          session.clear();
          String hql = " from PaymentHead paymentHead  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {// 部门编号
            // criterion += "to_char(paymentHead.org.id) like ? and ";
            // parameters.add("%" + orgId + "%");
            criterion += " paymentHead.org.id = ? and ";
            parameters.add(new Integer(orgId));
          }

          if (orgName != null && !orgName.equals("")) {// 部门名称
            criterion += "paymentHead.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (docNum != null && !docNum.equals("")) {// 凭证编号
            criterion += "paymentHead.docNum like ?  and ";
            parameters.add("%" + docNum + "%");
          }

          if (noteNum != null && !noteNum.equals("")) {// 票据编号
            criterion += "paymentHead.noteNum like ?  and ";
            parameters.add("%" + noteNum + "%");
          }

          if (payMoney != null && !payMoney.equals("")) {// 缴存金额
            criterion += "paymentHead.payMoney = ?  and ";
            parameters.add(new BigDecimal(payMoney));
          }

          if (settDate != null && !settDate.equals("") && settDate1 != null
              && !settDate1.equals("")) {
            criterion += " (paymentHead.settDate between ?  and  ? ) and ";
            parameters.add(settDate);
            parameters.add(settDate1);
          } else if (settDate != null && !settDate.equals("")) {
            criterion += " paymentHead.settDate = ? and ";
            parameters.add(settDate);
          } else if (settDate1 != null && !settDate1.equals("")) {
            criterion += " paymentHead.settDate = ? and ";
            parameters.add(settDate1);
          }
          if (payType != null && !payType.equals("")) {// 业务类型
            criterion += "paymentHead.payType_ = ?  and ";
            parameters.add(payType);
          }

          if (payStatus != null && !payStatus.equals("")) {// 业务状态
            criterion += "paymentHead.payStatus = ?  and ";
            parameters.add(payStatus);
          }

          if (criterion.length() != 0) {
            criterion = "where paymentHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql + "where paymentHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          String ob = orderBy;
          if (ob == null)
            ob = " paymentHead.payStatus DESC,paymentHead.id ";

          String od = order;
          if (od == null)
            od = "DESC";
          session.clear();
          hql = hql + criterion + "order by " + ob + " " + order;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          return query.list();
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;

  }

  public List queryByOrgId_lg(final Integer id) {

    Validate.isTrue(id != null);

    List list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from PaymentHead paymentHead ";
            Vector parameters = new Vector();
            String criterion = "";

            if (id != null) {
              criterion += " paymentHead.org.id=? and paymentHead.pay_status=1 ";
              parameters.add(id);
            }

            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));

            hql = hql + criterion;

            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.list();
          }
        });
    return list;
  }

  public void updatePaymentHead(PaymentHead paymentHead) {

    try {
      Validate.notNull(paymentHead);
      this.getHibernateTemplate().update(paymentHead);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * 王菱 根据单位ID查询最小的业务类型为A的301ID
   * 
   * @param orgid
   * @return
   */
  public String queryMinIDByTypeA_WL(final String orgid) {
    String month = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select min(paymentHead.id) "
                + " from PaymentHead paymentHead where paymentHead.payType_='A' "
                + " and (paymentHead.payStatus=2 or paymentHead.payStatus=3 or paymentHead.payStatus=4 ) ";

            Vector parameters = new Vector();
            String criterion = "";
            if (orgid != null && !orgid.equals("")) {
              criterion += " paymentHead.org.id = ?  and ";
              parameters.add(new Integer(orgid));
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            } else {
              return null;
            }
          }
        });

    return month;
  }

  /**
   * 王菱 根据单位ID查询的AA301表中有的汇缴业务的条数
   * 
   * @param orgid
   * @return
   */
  public String queryCountByTypeA_WL(final String orgid, final String minID,
      final String maxID) {
    String month = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(paymentHead.id) "
                + " from PaymentHead paymentHead where paymentHead.payType_='A' "
                + " and (paymentHead.payStatus=2 or paymentHead.payStatus=3 or paymentHead.payStatus=4 ) ";

            Vector parameters = new Vector();
            String criterion = "";
            if (orgid != null && !orgid.equals("")) {
              criterion += " paymentHead.org.id = ?  and ";
              parameters.add(new Integer(orgid));
            }
            if (minID != null && !minID.equals("") && maxID != null
                && !maxID.equals("")) {
              criterion += " (paymentHead.id  between ? and ? ) and ";
              parameters.add(new Integer(minID));
              parameters.add(new Integer(maxID));
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            } else {
              return null;
            }
          }
        });

    return month;
  }

  /**
   * 于庆丰 统计查询---缴存提取统计---单位缴存情况 根据orgId,orgName,payMonth 查询列表显示 月分 ,单位缴额 ,职工缴额
   * AA301 PaymentHead,AA302 MonthPaymentHead,AA303 MonthPaymentTail,AA304
   * AddPayTail,AA001 Org,BA001 OrgInfo
   * 
   * @param orgId
   * @param orgName
   * @param payMonth
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryMonthEmpRealPayOrgRealPay(final String orgId,
      final String orgName, final String payMonth, final int start,
      final int pageSize, final String orderBy, final String order,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select paymonth, sum(orgpay), sum(emppay),orgid,orgname "
            + "from (select aa302.pay_month paymonth,"
            + " sum(aa303.org_real_pay) orgpay,"
            + " sum(aa303.emp_real_pay) emppay,aa301.org_id orgid,ba001.name orgname "
            + " from AA301 aa301, AA302 aa302, AA303 aa303, AA001 aa001, BA001 ba001"
            + " where aa301.id = aa302.pay_head_id"
            + "  and aa302.id = aa303.month_pay_head_id and aa001.id = aa301.org_id"
            + " and aa001.orginfo_id=ba001.id ";
        if (orgId != null && !"".equals(orgId)) {
          hql += " and aa301.org_id = " + orgId;
        }
        if (orgName != null && !"".equals(orgName)) {
          hql += " and aa301.org_id = aa001.id and aa001.orginfo_id = ba001.id and ba001.name like '%"
              + orgName + "%'";
        }
        if (payMonth != null && !"".equals(payMonth)) {
          hql += " and aa302.pay_month like '" + payMonth + "%'";
        }
        hql += " and aa301.org_id " + securityInfo.getGjjSecuritySQL();
        hql += " and aa301.pay_status=5 ";
        hql += "group by aa302.pay_month,aa301.org_id,ba001.name "
            +

            " union "
            +

            " select aa304.add_monht paymonth,"
            + " sum(aa304.org_add_money) orgpay,"
            + " sum(aa304.emp_add_money) emppay,aa301.org_id orgid,ba001.name orgname "
            + " from AA301 aa301, AA304 aa304,AA001 aa001,"
            + " BA001 ba001 "
            + " where aa304.pay_head_id = aa301.id and aa001.id = aa301.org_id "
            + " and aa001.orginfo_id=ba001.id ";

        if (orgId != null && !"".equals(orgId)) {
          hql += " and aa301.org_id = " + orgId;
        }
        if (orgName != null && !"".equals(orgName)) {
          hql += " and aa301.org_id = aa001.id and aa001.orginfo_id = ba001.id and ba001.name like '%"
              + orgName + "%'";
        }
        if (payMonth != null && !"".equals(payMonth)) {
          hql += " and aa304.add_monht like '" + payMonth + "%'";
        }
        hql += " and aa301.org_id " + securityInfo.getGjjSecuritySQL();
        hql += " and aa301.pay_status=5 ";
        hql += " group by aa304.add_monht,aa301.org_id,ba001.name) group by paymonth,orgid,orgname";

        // String ob = orderBy;
        // if (ob == null)
        // ob = " aa301.id ";
        //
        // String od = order;
        // if (od == null)
        // od = "DESC";

        hql = hql + " order by " + orderBy + " " + order;

        Query query = session.createSQLQuery(hql);

        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);
        return query.list();
      }
    });
    return list;
  }

  /**
   * 于庆丰 统计查询---缴存提取统计---单位缴存情况 根据orgId,orgName,payMonth 查询列表显示 月分 ,单位缴额 ,职工缴额
   * (合计分页)
   * 
   * @param orgId
   * @param orgName
   * @param payMonth
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryCountMonthEmpRealPayOrgRealPay(final String orgId,
      final String orgName, final String payMonth, final int start,
      final int pageSize, final String orderBy, final String order,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        // List temp_list = new ArrayList();
        // // String hql = "select
        // b.pay_month,sum(c.org_real_pay+d.org_add_money)," +
        // // "sum(c.emp_real_pay+d.emp_add_money) from AA301 a,AA302 b,AA303
        // c," +
        // // "AA304 d AA001 e,BA001 f where a.pay_status=5 ";
        // String hql = "select b.payMonth,sum(c.orgRealPay+d.orgAddMoney)," +
        // "sum(c.empRealPay+d.empAddMoney),a.id from PaymentHead
        // a,MonthPaymentHead b,MonthPaymentTail c," +
        // "AddPayTail d ";
        //
        // Vector parameters = new Vector();
        // String criterion = "";
        // if (orgId != null && !orgId.equals("")) {
        // criterion += " a.org.id = ? and ";
        // parameters.add(new Integer(orgId));
        // }
        // if(orgName != null && !orgName.equals("")){
        // criterion +=" a.org.orgInfo.name like ? and ";
        // parameters.add("%"+orgName+"%");
        // }
        // if(payMonth != null && !payMonth.equals("")){
        // criterion +=" b.payMonth = d.addMonht = ? ";
        // parameters.add(payMonth);
        // }
        // if (criterion.length() != 0)
        // criterion = "where a.org.id "
        // + securityInfo.getGjjSecurityHqlSQL() + " and a.payStatus=5 and"
        // + criterion.substring(0, criterion.lastIndexOf("and"));
        //       
        // String ob = orderBy;
        // if (ob == null)
        // ob = " a.id ";
        //
        // String od = order;
        // if (od == null)
        // od = "DESC";
        //
        // hql = hql + criterion + "order by " + ob + " " + od;
        // Query query = session.createQuery(hql);
        // for (int i = 0; i < parameters.size(); i++) {
        // query.setParameter(i, parameters.get(i));
        // }
        // Iterator it=query.iterate();
        // Object obj[]=null;
        // while(it.hasNext()){
        // obj=(Object[])it.next();
        // OrgpaymentstatisticsDTO orgpaymentstatisticsDTO = new
        // OrgpaymentstatisticsDTO();
        // orgpaymentstatisticsDTO.setPay_month(obj[0].toString());
        // orgpaymentstatisticsDTO.setOrgPay(obj[1].toString());
        // orgpaymentstatisticsDTO.setEmpPay(obj[2].toString());
        // orgpaymentstatisticsDTO.setId(obj[3].toString());
        // temp_list.add(orgpaymentstatisticsDTO);
        // }
        // return temp_list;
        // }
        // });
        // return list;
        String hql = "select paymonth, sum(orgpay), sum(emppay),orgid,orgname "
            + "from (select aa302.pay_month paymonth,"
            + " sum(aa303.org_real_pay) orgpay,"
            + " sum(aa303.emp_real_pay) emppay,aa301.org_id orgid,ba001.name orgname"
            + " from AA301 aa301, AA302 aa302, AA303 aa303, AA001 aa001, BA001 ba001"
            + " where aa301.id = aa302.pay_head_id"
            + "  and aa302.id = aa303.month_pay_head_id and aa001.id = aa301.org_id"
            + " and aa001.orginfo_id=ba001.id ";
        if (orgId != null && !"".equals(orgId)) {
          hql += " and aa301.org_id = " + orgId;
        }
        if (orgName != null && !"".equals(orgName)) {
          hql += " and aa301.org_id = aa001.id and aa001.orginfo_id = ba001.id and ba001.name like '%"
              + orgName + "%'";
        }
        if (payMonth != null && !"".equals(payMonth)) {
          hql += " and aa302.pay_month like '%" + payMonth + "%'";
        }
        hql += " and aa301.org_id " + securityInfo.getGjjSecuritySQL();
        hql += " and aa301.pay_status=5 ";
        hql += "group by aa302.pay_month,aa301.org_id,ba001.name"
            +

            " union "
            +

            " select aa304.add_monht paymonth,"
            + " sum(aa304.org_add_money) orgpay,"
            + " sum(aa304.emp_add_money) emppay,aa301.org_id orgid,ba001.name orgname "
            + " from AA301 aa301,AA304 aa304,AA001 aa001,"
            + " BA001 ba001 "
            + " where aa304.pay_head_id = aa301.id and aa001.id = aa301.org_id "
            + " and aa001.orginfo_id=ba001.id ";
        if (orgId != null && !"".equals(orgId)) {
          hql += " and aa301.org_id = " + orgId;
        }
        if (orgName != null && !"".equals(orgName)) {
          hql += " and aa301.org_id = aa001.id and aa001.orginfo_id = ba001.id and ba001.name like '%"
              + orgName + "%'";
        }
        if (payMonth != null && !"".equals(payMonth)) {
          hql += " and aa304.add_monht like '" + payMonth + "%'";
        }
        hql += " and aa301.org_id " + securityInfo.getGjjSecuritySQL();
        hql += " and aa301.pay_status=5 ";
        hql += " group by aa304.add_monht,aa301.org_id,ba001.name) group by paymonth,orgid,orgname";

        // String ob = orderBy;
        // if (ob == null)
        // ob = " aa301.id ";
        //
        // String od = order;
        // if (od == null)
        // od = "DESC";

        hql = hql + " order by " + orderBy + " " + order;

        Query query = session.createSQLQuery(hql);

        return query.list();
      }
    });
    return list;
  }

  /**
   * 于庆丰 职工情况列表
   * 
   * @param orgId
   * @param orgName
   * @param payMonth
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryMonthEmpRealPay(final String empId, final String empName,
      final String payMonth, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select paymonth, sum(orgpay), sum(emppay)"
            + "from (select aa302.pay_month paymonth,"
            + " sum(aa303.org_real_pay) orgpay,"
            + " sum(aa303.emp_real_pay) emppay"
            + " from AA301 aa301, AA302 aa302, AA303 aa303,AA002 aa002,BA002 ba002"
            + " where aa301.id = aa302.pay_head_id"
            + "  and aa302.id = aa303.month_pay_head_id "
            + " and aa303.emp_id = aa002.id "
            + " and aa002.emp_info_id = ba002.id ";
        if (empId != null && !"".equals(empId)) {
          hql += " and aa002.id = " + empId;
        }
        if (empName != null && !"".equals(empName)) {
          hql += " and aa002.emp_info_id = ba002.id and ba002.name like '%"
              + empName + "%'";
        }
        if (payMonth != null && !"".equals(payMonth)) {
          hql += " and aa302.pay_month like '" + payMonth + "%'";
        }
        hql += " and aa301.org_id " + securityInfo.getGjjSecuritySQL();
        hql += " and aa301.pay_status=5 ";
        hql += "group by aa302.pay_month"
            +

            " union "
            +

            " select aa304.add_monht paymonth,"
            + " sum(aa304.org_add_money) orgpay,"
            + " sum(aa304.emp_add_money) emppay "
            + " from AA301 aa301, AA304 aa304 ,AA002 aa002,BA002 ba002"
            + " where aa304.pay_head_id = aa301.id and aa304.emp_id = aa002.id "
            + " and aa002.emp_info_id = ba002.id ";

        if (empId != null && !"".equals(empId)) {
          hql += " and aa002.id = " + empId;
        }
        if (empName != null && !"".equals(empName)) {
          hql += " and aa002.emp_info_id = ba002.id and ba002.name like '%"
              + empName + "%'";
        }
        if (payMonth != null && !"".equals(payMonth)) {
          hql += " and aa304.add_monht like '" + payMonth + "%'";
        }
        hql += " and aa301.org_id " + securityInfo.getGjjSecuritySQL();
        hql += " and aa301.pay_status=5 ";
        hql += " group by aa304.add_monht) group by paymonth";

        // String ob = orderBy;
        // if (ob == null)
        // ob = " aa301.id ";
        //
        // String od = order;
        // if (od == null)
        // od = "DESC";

        hql = hql + " order by " + orderBy + " " + order;

        Query query = session.createSQLQuery(hql);
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);
        return query.list();
      }
    });
    return list;
  }

  public List queryEmppaymentstatisticsYear(final String empId) {
    List list = new ArrayList();
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select substr(b.pay_month,0,4) paymonth from aa303 a ,aa302 b where a.month_pay_head_id=b.id and a.emp_id = ? "
            + " union "
            + " select substr(a.add_monht,0,4) paymonth from aa304 a where a.emp_id = ? ";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, new Integer(empId));
        query.setParameter(1, new Integer(empId));
        return query.list();
      }
    });
    return list;
  }

  public List queryEmppaymentstatisticsList(final String empId,
      final String payMonth) {
    List list = new ArrayList();
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select paymonth,sum(orgpay),sum(emppay) from "
            + " (select sum(a.org_real_pay) orgpay,sum(a.emp_real_pay) emppay,substr(b.pay_month,5,6) paymonth "
            + " from aa303 a,aa302 b where a.month_pay_head_id=b.id "
            + " and a.emp_id = ? and b. pay_month like ? group by b.pay_month "
            + " union "
            + " select sum(c.org_add_money) orgpay,sum(c.emp_add_money) emppay,substr(c.add_monht,5,6) paymonth "
            + " from aa304 c where c.emp_id = ? and c.add_monht like ? "
            + " group by c.add_monht) group by paymonth";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, new Integer(empId));
        query.setParameter(1, payMonth + "%");
        query.setParameter(2, new Integer(empId));
        query.setParameter(3, payMonth + "%");
        OrgpaymentstatisticsDTO dto = null;
        List templist = new ArrayList();
        Iterator iterate = query.list().iterator();
        Object[] obj = null;
        while (iterate.hasNext()) {
          obj = (Object[]) iterate.next();
          dto = new OrgpaymentstatisticsDTO();
          dto.setPay_month(new Integer(obj[0].toString()));
          dto.setOrgPay(new BigDecimal(obj[1].toString()));
          dto.setEmpPay(new BigDecimal(obj[2].toString()));
          templist.add(dto);
        }
        return templist;
      }
    });
    return list;
  }

  public List queryEmppaymentstatisticsTotalList(final String empId,
      final String payMonth) {
    List list = new ArrayList();
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select sum(orgpay),sum(emppay) from "
            + " (select sum(a.org_real_pay) orgpay,sum(a.emp_real_pay) emppay,substr(b.pay_month,5,6) paymonth "
            + " from aa303 a,aa302 b where a.month_pay_head_id=b.id "
            + " and a.emp_id = ? and b. pay_month like ? group by b.pay_month "
            + " union "
            + " select sum(c.org_add_money) orgpay,sum(c.emp_add_money) emppay,substr(c.add_monht,5,6) paymonth "
            + " from aa304 c where c.emp_id = ? and c.add_monht like ? group by c.add_monht)";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, new Integer(empId));
        query.setParameter(1, payMonth + "%");
        query.setParameter(2, new Integer(empId));
        query.setParameter(3, payMonth + "%");
        OrgpaymentstatisticsDTO dto = null;
        List templist = new ArrayList();
        Iterator iterate = query.list().iterator();
        Object[] obj = null;
        while (iterate.hasNext()) {
          obj = (Object[]) iterate.next();
          dto = new OrgpaymentstatisticsDTO();
          dto.setOrgPay(new BigDecimal(obj[0].toString()));
          dto.setEmpPay(new BigDecimal(obj[1].toString()));
          templist.add(dto);
        }
        return templist;
      }
    });
    return list;
  }

  /**
   * 于庆丰 职工列表分页
   * 
   * @param empId
   * @param empName
   * @param payMonth
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryCountMonthEmpRealPay(final String empId,
      final String empName, final String payMonth, final int start,
      final int pageSize, final String orderBy, final String order,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select paymonth, sum(orgpay), sum(emppay)"
            + "from (select aa302.pay_month paymonth,"
            + " sum(aa303.org_real_pay) orgpay,"
            + " sum(aa303.emp_real_pay) emppay"
            + " from AA301 aa301, AA302 aa302, AA303 aa303,AA002 aa002,BA002 ba002"
            + " where aa301.id = aa302.pay_head_id"
            + "  and aa302.id = aa303.month_pay_head_id "
            + " and aa303.emp_id = aa002.id"
            + " and aa002.emp_info_id = ba002.id ";
        if (empId != null && !"".equals(empId)) {
          hql += " and aa002.id = " + empId;
        }
        if (empName != null && !"".equals(empName)) {
          hql += " and aa002.emp_info_id = ba002.id and ba002.name like '%"
              + empName + "%'";
        }
        if (payMonth != null && !"".equals(payMonth)) {
          hql += " and aa302.pay_month like '%" + payMonth + "%'";
        }
        hql += " and aa301.org_id " + securityInfo.getGjjSecuritySQL();
        hql += " and aa301.pay_status=5 ";
        hql += "group by aa302.pay_month"
            +

            " union "
            +

            " select aa304.add_monht paymonth,"
            + " sum(aa304.org_add_money) orgpay,"
            + " sum(aa304.emp_add_money) emppay "
            + " from AA301 aa301, AA304 aa304 ,AA002 aa002,BA002 ba002"
            + " where aa304.pay_head_id = aa301.id and aa304.emp_id = aa002.id "
            + " and aa002.emp_info_id = ba002.id ";

        if (empId != null && !"".equals(empId)) {
          hql += " and aa002.id = " + empId;
        }
        if (empName != null && !"".equals(empName)) {
          hql += " and aa002.emp_info_id = ba002.id and ba002.name like '%"
              + empName + "%'";
        }
        if (payMonth != null && !"".equals(payMonth)) {
          hql += " and aa304.add_monht like '%" + payMonth + "%'";
        }
        hql += " and aa301.org_id " + securityInfo.getGjjSecuritySQL();
        hql += " and aa301.pay_status=5 ";
        hql += " group by aa304.add_monht) group by paymonth";

        // String ob = orderBy;
        // if (ob == null)
        // ob = " aa301.id ";
        //
        // String od = order;
        // if (od == null)
        // od = "DESC";

        hql = hql + " order by " + orderBy + " " + order;

        Query query = session.createSQLQuery(hql);

        return query.list();
      }
    });
    return list;
  }

  /**
   * 查询AA301 id和类型为‘A’记录的代扣批号
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public String queryPaymentInfoCounts(final String id) throws Exception {
    String payMentCount = "";
    try {
      payMentCount = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select a301.financial_pay_id,a301.id from aa301 a301 "
                  + "where a301.id = ? and a301.pay_type = 'A' ";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, id);
              if (query.list().size() == 0) {
                return "";
              }
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              if (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] == null || obj[0].toString().equals("")) {
                  return "";
                } else {
                  return obj[0].toString();
                }
              }
              return "";
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return payMentCount;
  }

  /**
   * 根据单位ID查询最大的301ID
   * 
   * @param orgid
   * @return
   */
  public String queryMaxID(final String orgid) {
    String month = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(paymentHead.id) "
                + " from PaymentHead paymentHead where paymentHead.org.id = ? and paymentHead.payType_ = 'A' ";

            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(orgid));
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            } else {
              return "";
            }
          }
        });

    return month;
  }

  /**
   * 根据301ID查询302中最大的汇缴年月
   * 
   * @param id
   * @return
   */
  public String queryPaymentHeadMaxID(final String id) {
    String month = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(a302.pay_month) from aa302 a302 "
                + "where a302.pay_head_id = ? ";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, new Integer(id));
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            } else {
              return "";
            }
          }
        });

    return month;
  }

  /**
   * @param office 0:市本级 1:县区
   * @param yearMonth
   * @return
   */
  public List queryPaymntHeadBanks(final String office, final String yearMonth) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select b105.office,a101.moneybank"
              + " from aa101 a101,bb105 b105"
              + " where a101.moneybank = b105.coll_bank_id"
              + " and a101.biz_status = 5";
          Vector parameters = new Vector();
          String criterion = "";
          // 这个地方做下判断如果传入的是真实的办事处代码长度会大于1
          if (office != null) {
            if (office.length() > 1) {
              criterion += " b105.office = ? and ";
              parameters.add(office);
            } else if (office.equals("0")) {
              criterion += " b105.office = '" + BusiConst.OFFICECODE_SBJ
                  + "' and ";
            } else if (office.equals("1")) {
              criterion += " b105.office <> '" + BusiConst.OFFICECODE_SBJ
                  + "' and ";
            }
          }
          if (yearMonth != null && !yearMonth.equals("")) {
            criterion += " substr(a101.sett_date, 0, 6) = ? and ";
            parameters.add(yearMonth);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion + " group by b105.office,a101.moneybank ";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            PaymntPickDetailDTO dto = new PaymntPickDetailDTO();
            dto.setOffice(obj[0].toString());
            dto.setCollBankId(obj[1].toString());
            list.add(dto);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public List queryPaymntHeadDetail(final String yearMonth) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select t.sett_date, t.moneybank, t.biz_type, sum(t.debit), sum(t.credit)"
              + " from aa101 t" + " where t.biz_status = 5";
          Vector parameters = new Vector();
          String criterion = "";
          if (yearMonth != null && !yearMonth.equals("")) {
            criterion += " substr(t.sett_date, 0, 6) = ? and ";
            parameters.add(yearMonth);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion
              + " group by t.sett_date, t.moneybank, t.biz_type ";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            PaymntPickDetailDTO dto = new PaymntPickDetailDTO();
            dto.setSettDate(obj[0].toString());
            dto.setCollBankId(obj[1].toString());
            dto.setBizType(obj[2].toString());
            dto.setDebit(new BigDecimal(obj[3].toString()));
            dto.setCredit(new BigDecimal(obj[4].toString()));
            list.add(dto);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public BigDecimal queryMonthEndPaySumByBank(final String bankId,
      final String yearMonth, final String type) {
    BigDecimal money = null;
    try {
      money = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "";
              if (type.equals("0"))
                sql = " select sum(a101.credit)-sum(a101.debit)"
                    + " from aa101 a101" + " where a101.biz_status = 5"
                    + " and a101.biz_type in('A','B','C','M','K','L','G')";
              else
                sql = " select sum(a101.debit)" + " from aa101 a101"
                    + " where a101.biz_status = 5" + " and a101.biz_type = 'D'";
              Vector parameters = new Vector();
              String criterion = "";
              // 这个地方做下判断如果传入的是真实的办事处代码长度会大于1
              if (bankId != null) {
                if (bankId != null && !"".equals(bankId)) {
                  criterion += " a101.moneybank = ? and ";
                  parameters.add(bankId);
                }
              }
              if (yearMonth != null && !yearMonth.equals("")) {
                criterion += " substr(a101.sett_date, 0, 6) = ? and ";
                parameters.add(yearMonth);
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              sql = sql + criterion;
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              if (query.uniqueResult() != null)
                return new BigDecimal(query.uniqueResult().toString());
              else
                return new BigDecimal(0.00);
            }
          });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return money;
  }

  /**
   * @param office 0:市本级 1:县区
   * @param yearMonth
   * @return
   */
  public List queryPaymntHeadBanksByYear(final String office, final String year) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select b105.office,a101.moneybank"
              + " from aa101 a101,bb105 b105"
              + " where a101.moneybank = b105.coll_bank_id"
              + " and a101.biz_status = 5";
          Vector parameters = new Vector();
          String criterion = "";
          // 这个地方做下判断如果传入的是真实的办事处代码长度会大于1
          if (office != null) {
            if (office.length() > 1) {
              criterion += " b105.office = ? and ";
              parameters.add(office);
            } else if (office.equals("0")) {
              criterion += " b105.office = '" + BusiConst.OFFICECODE_SBJ
                  + "' and ";
            } else if (office.equals("1")) {
              criterion += " b105.office <> '" + BusiConst.OFFICECODE_SBJ
                  + "' and ";
            }
          }
          if (year != null && !year.equals("")) {
            criterion += " substr(a101.sett_date, 0, 4) = ? and ";
            parameters.add(year);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion + " group by b105.office,a101.moneybank ";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            PaymntPickDetailDTO dto = new PaymntPickDetailDTO();
            dto.setOffice(obj[0].toString());
            dto.setCollBankId(obj[1].toString());
            list.add(dto);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public List queryMonthPaymntHeadDetail(final String year) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select substr(t.sett_date,0,6) t.moneybank, t.biz_type, sum(t.debit), sum(t.credit)"
              + " from aa101 t" + " where t.biz_status = 5";
          Vector parameters = new Vector();
          String criterion = "";
          if (year != null && !year.equals("")) {
            criterion += " substr(t.sett_date, 0, 4) = ? and ";
            parameters.add(year);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion
              + " group by substr(t.sett_date,0,6), t.moneybank, t.biz_type ";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            PaymntPickDetailDTO dto = new PaymntPickDetailDTO();
            dto.setSettDate(obj[0].toString());
            dto.setCollBankId(obj[1].toString());
            dto.setBizType(obj[2].toString());
            dto.setDebit(new BigDecimal(obj[3].toString()));
            dto.setCredit(new BigDecimal(obj[4].toString()));
            list.add(dto);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public BigDecimal queryYearEndPaySumByBank(final String bankId,
      final String year, final String type) {
    BigDecimal money = null;
    try {
      money = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "";
              if (type.equals("0"))
                sql = " select sum(a101.credit)-sum(a101.debit)"
                    + " from aa101 a101" + " where a101.biz_status = 5"
                    + " and a101.biz_type in('A','B','C','M','K','L','G')";
              else
                sql = " select sum(a101.debit)" + " from aa101 a101"
                    + " where a101.biz_status = 5" + " and a101.biz_type = 'D'";
              Vector parameters = new Vector();
              String criterion = "";
              // 这个地方做下判断如果传入的是真实的办事处代码长度会大于1
              if (bankId != null) {
                if (bankId != null && !"".equals(bankId)) {
                  criterion += " a101.moneybank = ? and ";
                  parameters.add(bankId);
                }
              }
              if (year != null && !year.equals("")) {
                criterion += " substr(a101.sett_date, 0, 4) = ? and ";
                parameters.add(year);
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              sql = sql + criterion;
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Object obj = (Object) query.uniqueResult();
              if (obj == null)
                return new BigDecimal(0.00);
              else
                return new BigDecimal(obj.toString());
            }
          });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return money;
  }

  /**
   * 查询上月末余额
   * 
   * @param bank
   * @param yearMonth
   * @return
   */
  public BigDecimal queryPrevMonBalance(final String bank,
      final String yearMonth) {
    BigDecimal money = null;
    try {
      money = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select sum(t.credit) - sum(t.debit)"
                  + " from aa101 t" + " where t.biz_status = 5 ";
              Vector parameters = new Vector();
              String criterion = "";
              if (bank != null && !"".equals(bank)) {
                criterion += " t.moneybank = ? and ";
                parameters.add(bank);
              }
              if (yearMonth != null && !"".equals(yearMonth)) {
                criterion += " t.sett_date < ? and ";
                parameters.add(yearMonth);
              }
              if (criterion.length() != 0) {
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              }
              sql += criterion;
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Object obj = (Object) query.uniqueResult();
              if (obj == null)
                return new BigDecimal(0.00);
              else
                return new BigDecimal(obj.toString());
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return money;
  }
  public HashMap queryOverpayBalance(final String bank,
      final String yearMonth) {
    HashMap map = null;
    try {
      map = (HashMap) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select t.moneybank, nvl(sum(t.credit) - sum(t.debit),0)"
                  + " from aa101 t" 
                  + " where t.biz_status = 5 "
                  + " and t.biz_type = 'C'";
              Vector parameters = new Vector();
              String criterion = "";
              if (bank != null && !"".equals(bank)) {
                criterion += " t.moneybank = ? and ";
                parameters.add(bank);
              }
              if (yearMonth != null && !"".equals(yearMonth)) {
                criterion += " substr(t.sett_date,0,6) <= ? and ";
                parameters.add(yearMonth);
              }
              if (criterion.length() != 0) {
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              }
              sql += criterion + " group by t.moneybank";
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              HashMap m = new HashMap();
              Iterator it = query.list().iterator();
              Object obj[] = null;
              while (it.hasNext()) {
                obj = (Object[]) it.next();
                m.put(obj[0].toString(), new BigDecimal(obj[1].toString()));
              }
              return m;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }
  public BigDecimal queryCurYearPayMoney(final String bank,
      final String startDate, final String endDate) {
    BigDecimal money = null;
    try {
      money = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select sum(t.credit) - sum(t.debit)"
                  + " from aa101 t" + " where t.biz_status = 5"
                  + " and t.biz_type in ('A','B','C','M')";
              Vector parameters = new Vector();
              String criterion = "";
              if (bank != null && !"".equals(bank)) {
                criterion += " t.moneybank = ? and ";
                parameters.add(bank);
              }
              if (startDate != null && !"".equals(startDate)) {
                criterion += " t.sett_date >= ? and ";
                parameters.add(startDate);
              }
              if (endDate != null && !"".equals(endDate)) {
                criterion += " t.sett_date <= ? and ";
                parameters.add(endDate);
              }
              if (criterion.length() != 0) {
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              }
              sql += criterion;
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Object obj = (Object) query.uniqueResult();
              if (obj == null)
                return new BigDecimal(0.00);
              else
                return new BigDecimal(obj.toString());
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return money;
  }

  public List queryPaymntMonReportInfo(final String office, final String bank,
      final String yearMonth, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select a1.moneybank,"
              + " a1.biz_type,"
              + " case when a1.biz_type = 'C' then (sum(a1.credit) - sum(a1.debit)) else (sum(a2.credit) - sum(a2.debit)) end,"
              + " count(distinct a1.org_id)," + " count(distinct a2.emp_id)"
              + " from aa101 a1, aa102 a2" + " where a1.id = a2.org_flow_id(+)"
              + " and a1.biz_type in ('A', 'B', 'C', 'M') "
              + " and a1.biz_status = 5" + " and a1.org_id "
              + securityInfo.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (office != null && !"".equals(office)) {
            criterion += " a1.officecode = ? and ";
            parameters.add(office);
          }
          if (bank != null && !"".equals(bank)) {
            criterion += " a1.moneybank = ? and ";
            parameters.add(bank);
          }
          if (yearMonth != null && !"".equals(yearMonth)) {
            criterion += " substr(a1.sett_date,0,6) = ? and ";
            parameters.add(yearMonth);
          }
          if (criterion.length() != 0) {
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }
          sql = sql + criterion
              + " group by a1.moneybank, a1.biz_type order by a1.moneybank ";
          Query query = session.createSQLQuery(sql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List returnList = new ArrayList();

          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          while (iterator.hasNext()) {
            PaymntMonRepInfoDTO dto = new PaymntMonRepInfoDTO();
            obj = (Object[]) iterator.next();
            if (obj[0] != null) {
              dto.setCollBankId(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setBizType(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setMoney(new BigDecimal(obj[2].toString()));
            }
            if (obj[3] != null) {
              dto.setCurMonthOrgCount(Integer.valueOf(obj[3].toString()));
            }
            if (obj[4] != null) {
              dto.setCurMonthPsnCount(Integer.valueOf(obj[4].toString()));
            }
            returnList.add(dto);
          }
          return returnList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryPaymentYearReportInfo(final String office,
      final String bank, final String year) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select officecode,moneybank, sdate, sum(scount), sum(sempcount), sum(m) "
              + "from (select a1.officecode, "
              + "a1.moneybank, "
              + "substr(a1.sett_date, 0, 6) sdate, "
              + "sum(a2.credit) - sum(a2.debit) as m, "
              + "count(distinct a1.org_id) scount, "
              + "count(distinct a2.emp_id) sempcount "
              + "from aa101 a1, aa102 a2 "
              + "where a1.id = a2.org_flow_id(+) "
              + "and a1.biz_status = 5 "
              + "and a1.biz_type = 'A' "
              + "and substr(a1.sett_date,0,4) = '"
              + year
              + "' "
              + "group by a1.officecode, a1.moneybank, substr(a1.sett_date, 0, 6) "
              + "union all "
              + "select a1.officecode, "
              + "a1.moneybank, "
              + "substr(a1.sett_date, 0, 6) sdate, "
              + "sum(a2.credit) - sum(a2.debit) as m, "
              + "count(distinct a1.org_id) scount, "
              + "count(distinct a2.emp_id) sempcount "
              + "from aa101 a1, aa102 a2 "
              + "where a1.id = a2.org_flow_id(+) "
              + "and a1.biz_status = 5 "
              + "and a1.biz_type = 'B' "
              + "and substr(a1.sett_date,0,4) = '"
              + year
              + "' "
              + "group by a1.officecode, a1.moneybank, substr(a1.sett_date, 0, 6) "
              + "union all "
              + "select a1.officecode, "
              + "a1.moneybank, "
              + "substr(a1.sett_date, 0, 6) sdate, "
              + "sum(a2.credit) - sum(a2.debit) as m, "
              + "count(distinct a1.org_id) scount, "
              + "count(distinct a2.emp_id) sempcount "
              + "from aa101 a1, aa102 a2 "
              + "where a1.id = a2.org_flow_id(+) "
              + "and a1.biz_status = 5 "
              + "and a1.biz_type = 'M' "
              + "and substr(a1.sett_date,0,4) = '"
              + year
              + "' "
              + "group by a1.officecode, a1.moneybank, substr(a1.sett_date, 0, 6) "
              + "union all "
              + "select a1.officecode, "
              + "a1.moneybank, "
              + "substr(a1.sett_date, 0, 6) sdate, "
              + "sum(a1.credit) - sum(a1.debit) as m, "
              + "count(distinct a1.org_id) scount, "
              + "count(distinct a2.emp_id) sempcount "
              + "from aa101 a1, aa102 a2 "
              + "where a1.id = a2.org_flow_id(+) "
              + "and a1.biz_status = 5 "
              + "and a1.biz_type = 'C' "
              + "and substr(a1.sett_date,0,4) = '"
              + year
              + "' "
              + "group by a1.officecode, a1.moneybank, substr(a1.sett_date, 0, 6)) res "
              + "where 1=1 ";
          Vector parameters = new Vector();
          String criterion = "";
          if (office != null && !"".equals(office)) {
            criterion += "  and res.officecode = ?  ";
            parameters.add(office);
          }
          if (bank != null && !"".equals(bank)) {
            criterion += "  and res.moneybank = ? ";
            parameters.add(bank);
          }
          sql = sql
              + criterion
              + " group by res.officecode,res.moneybank, res.sdate order by res.moneybank, res.sdate ";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List returnList = new ArrayList();
          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          while (iterator.hasNext()) {
            PaymentYearDTO dto = new PaymentYearDTO();
            obj = (Object[]) iterator.next();
            if (obj[1] != null) {
              dto.setCollBankId(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setYearmonth(obj[2].toString());
            }
            if (obj[3] != null) {
              dto.setCount(Integer.parseInt(obj[3].toString()));
            }
            if (obj[4] != null) {
              dto.setPerson(Integer.parseInt(obj[4].toString()));
            }
            if (obj[5] != null) {
              dto.setMoney(new BigDecimal(obj[5].toString()));
            }
            returnList.add(dto);
          }
          return returnList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryPaymentLastyearReportInfo(final String office,
      final String bank, final String year) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select substr(t.sett_date, 0, 6),"
              + " sum(t.credit) - sum(t.debit)"
              + " from aa101 t"
              + " where t.biz_type in ('A', 'C', 'B', 'M')"
              + " and t.biz_status = 5"
              + " and substr(t.sett_date, 0, 4) = '"+year+"'";
          Vector parameters = new Vector();
          String criterion = "";

          if (office != null && !"".equals(office)) {
            criterion += " t.officecode = ? and ";
            parameters.add(office);
          }
          if (bank != null && !"".equals(bank)) {
            criterion += " t.moneybank = ? and ";
            parameters.add(bank);
          }
          sql = sql + criterion
              + " group by substr(t.sett_date, 0, 6) ";
          Query query = session.createSQLQuery(sql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List returnList = new ArrayList();
          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          while (iterator.hasNext()) {
            PaymentYearDTO dto = new PaymentYearDTO();
            obj = (Object[]) iterator.next();
            if (obj[0] != null) {
              dto.setYearmonth(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setMoney(new BigDecimal(obj[1].toString()));
            }
            returnList.add(dto);
          }
          return returnList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

}