package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentSalaryBase;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.dto.SalaryBaseChgDTO;

/**
 * @author 卢钢 2007-6-20
 */
public class ChgPaymentSalaryBaseDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public ChgPaymentSalaryBase queryById(Integer id) {
    Validate.notNull(id);
    return (ChgPaymentSalaryBase) getHibernateTemplate().get(
        ChgPaymentSalaryBase.class, id);
  }

  public void getHeadMessage_GJP(ChgPaymentSalaryBase chgPaymentSalaryBase)
      throws NumberFormatException, Exception {
    ChgPaymentSalaryBase chgPaymentSalaryBase1 = (ChgPaymentSalaryBase) getHibernateTemplate()
        .load(ChgPaymentSalaryBase.class, chgPaymentSalaryBase.getId());
    chgPaymentSalaryBase1.setChgStatus(chgPaymentSalaryBase.getChgStatus());
  }

  /**
   * 插入记录
   * 
   * @param chgOrgRate
   * @return
   */
  public Serializable insert(ChgPaymentSalaryBase chgPaymentSalaryBase) {
    Serializable id = null;
    try {
      Validate.notNull(chgPaymentSalaryBase);
      id = getHibernateTemplate().save(chgPaymentSalaryBase);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 键删除单个记录
   * 
   * @return 吴洪涛 6.30
   */
  public void delete(ChgPaymentSalaryBase chgPaymentSalaryBase) {
    Validate.notNull(chgPaymentSalaryBase);
    getHibernateTemplate().delete(chgPaymentSalaryBase);
  }

  /**
   * 查询记录 吴洪涛 2007.6.27
   * 
   * @return boolean:false－未启用；true－启用
   */
  public boolean getChgStatus(final Integer orgId) {
    Validate.notNull(orgId);

    boolean chgStatus = false;
    Integer TEMP_status = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select chgPaymentSalaryBase.chgStatus from ChgPaymentSalaryBase chgPaymentSalaryBase where chgPaymentSalaryBase.chgStatus=1 and chgPaymentSalaryBase.org.id=? ";
            Vector parameters = new Vector();
            parameters.add(orgId);

            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));

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
   * 查询记录(ChgPaymentSalaryBase) 吴洪涛 2007.6.27
   * 
   * @return
   */
  public ChgPaymentSalaryBase queryByCriterions(final String id) {

    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    chgPaymentSalaryBase = (ChgPaymentSalaryBase) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select chgPaymentSalaryBase from ChgPaymentSalaryBase chgPaymentSalaryBase  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (id != null) {
              criterion += "chgPaymentSalaryBase.org.id = ? and ";
              parameters.add(new Integer(id));
            }
            if (criterion.length() != 0)
              criterion = "where chgPaymentSalaryBase.chgStatus=1 and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;

            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }
            return query0.uniqueResult();
          }
        });

    return chgPaymentSalaryBase;
  }

  /**
   * 王菱 根据202的ID 查询调整的职工的状态
   * 
   * @return
   */
  public List querypay_statusByHeadID_WL(final String headid) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select chgPaymentTail.payStatus from ChgPaymentTail chgPaymentTail  ";
        Vector parameters = new Vector();
        String criterion = "";
        if (headid != null) {
          criterion += "chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(headid));
        }
        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;

        Query query0 = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query0.setParameter(i, parameters.get(i));
        }
        return query0.list();
      }
    });

    return list;
  }

  /**
   * 根据输入的条件查询单位信息(ChgPaymentSalaryBase) id chgPaymentSalaryBase.chgStatus=1
   * 
   * @return 吴洪涛
   */
  public ChgPaymentSalaryBase queryChgPaymentSalaryBaseByIdWuht(
      final String id, final String chgStatus) {

    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    chgPaymentSalaryBase = (ChgPaymentSalaryBase) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select chgPaymentSalaryBase from ChgPaymentSalaryBase chgPaymentSalaryBase  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (id != null) {
              criterion += "chgPaymentSalaryBase.id = ? and ";
              parameters.add(new Integer(id));
            }
            if (chgStatus != null) {
              criterion += "chgPaymentSalaryBase.chgStatus= ? and ";
              parameters.add(new Integer(chgStatus));
            }
            if (criterion.length() != 0) {
              criterion = "where   "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }
            hql = hql + criterion;

            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }
            return query0.uniqueResult();
          }
        });

    return chgPaymentSalaryBase;
  }


  /**
   * 根据输入的条件查询单位信息(List) type=A(ChgPaymentSalaryBase)
   * 
   * @param String id,
   * @param String chgMonth 无默认条件
   * @return 吴洪涛
   */
  public List queryChgPaymentSalaryBaseByCriterionsWuht(final String id,
      final String name, final String chgMonth, final String orderBy,
      final String order, final int start, final int pageSize, final int page,
      final SecurityInfo securityInfo) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentSalaryBase from ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null && !chgMonth.equals("")) {
          criterion += "chgPaymentSalaryBase.chgMonth = ?  and ";
          parameters.add(chgMonth);
        }

        if (name.trim() != null && !name.trim().equals("")) {
          criterion += "chgPaymentSalaryBase.org.orgInfo.name like ?  and ";
          parameters.add("%" + name.trim() + "%");
        }

        if (id.trim() != null && !id.trim().equals("")) {
          criterion += " To_Char(chgPaymentSalaryBase.org.id) like ? and ";
          parameters.add("%" + id.trim() + "%");
        }

        if (criterion.length() != 0) {
          criterion = "where  chgPaymentSalaryBase.org.id "
              + securityInfo.getGjjSecurityHqlSQL() + " and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        } else {
          criterion = "where  chgPaymentSalaryBase.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
        }
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentSalaryBase.id ";

        String od = order;
        if (od == null)
          od = "DESC";
        hql = hql + criterion + "order by " + ob + " " + od;

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
        return query.list();
      }
    });

    return orglist;
  }


  public int queryChgPaymentSalaryBaseByCriterionsWuht(final String id,
      final String name, final String chgMonth, final SecurityInfo securityInfo) {
    Integer count = null;
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select chgPaymentSalaryBase from ChgPaymentSalaryBase chgPaymentSalaryBase ";
          String criterion = "";
          Vector parameters = new Vector();
          if (chgMonth != null && !chgMonth.equals("")) {
            criterion += "chgPaymentSalaryBase.chgMonth = ?  and ";
            parameters.add(chgMonth);
          }

          if (name != null && !name.equals("")) {
            criterion += "chgPaymentSalaryBase.org.orgInfo.name like ?  and ";
            parameters.add("%" + name + "%");
          }

          if (id != null && !id.equals("")) {
            criterion += " To_Char(chgPaymentSalaryBase.org.id) like ? and ";
            parameters.add("%" + id + "%");
          }

          if (criterion.length() != 0) {
            criterion = "where  chgPaymentSalaryBase.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = "where  chgPaymentSalaryBase.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          hql = hql + criterion;

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return Integer.valueOf(query.uniqueResult().toString());
        }
      }

      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }


  public void updateSlaryChgUse(final Integer orgId, final Integer chgHeadId,
      final String chgDate) throws SQLException {

    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call PayChgUse(?,?,?)}");
      cs.setInt(1, orgId.intValue());
      cs.setInt(2, chgHeadId.intValue());
      cs.setString(3, chgDate);
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void updateSlaryChgReUse(final Integer orgId, final Integer chgHeadId)
      throws SQLException {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call PayChgReUse(?,?)}");
      cs.setInt(1, orgId.intValue());
      cs.setInt(2, chgHeadId.intValue());
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * 于庆丰 count chg_type=2
   * 
   * @param office
   * @param bank
   * @param orgCharacter
   * @param dept
   * @param ragion
   * @param startDate
   * @param endDate
   * @return
   */
  public int queryCount(final String office, final String bank,
      final String orgCharacter, final String dept, final String ragion,
      final String startDate, final String endDate) {
    int count = 0;
    try {
      Integer countInteger = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select count(chgPaymentSalaryBase.id) from ChgPaymentSalaryBase chgPaymentSalaryBase ";
              Vector parameters = new Vector();
              String criterion = "";
              if (office != null && !"".equals(office)) {
                criterion += " chgPaymentSalaryBase.org.orgInfo.officecode = ? and ";
                parameters.add(office);
              }
              if (bank != null && !"".equals(bank)) {
                criterion += " chgPaymentSalaryBase.org.orgInfo.collectionBankId = ? and ";
                parameters.add(bank);
              }
              if (orgCharacter != null && !"".equals(orgCharacter)) {
                criterion += " chgPaymentSalaryBase.org.orgInfo.character = ? and ";
                parameters.add(orgCharacter);
              }
              if (dept != null && !"".equals(dept)) {
                criterion += " chgPaymentSalaryBase.org.orgInfo.deptInCharge = ? and ";
                parameters.add(dept);
              }
              if (ragion != null && !"".equals(ragion)) {
                criterion += " chgPaymentSalaryBase.org.orgInfo.region = ? and ";
                parameters.add(ragion);
              }
              if (startDate != null && !"".equals(startDate) && endDate != null
                  && !"".equals(endDate)) {
                criterion += " (chgPaymentSalaryBase.bizDate  between ?  and  ?)  and ";
                parameters.add(startDate);
                parameters.add(endDate);
              }
              if (criterion.length() != 0)
                criterion = " where chgPaymentSalaryBase.chgStatus=2  and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              // criterion = " where "
              // + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Integer countTemp = new Integer(0);
              Iterator it = query.iterate();
              if (it.hasNext()) {
                countTemp = (Integer) it.next();
              }
              return countTemp;
            }
          });
      count = countInteger.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  /**
   * @author yangg @
   */
  public List querySalaryBaseChgList(final String orgId, final String orgName,
      final String startChgMonth, final String endChgMonth,
      final String type, final String orderBy, final String order,
      final int start, final int pageSize, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select a2.id,a1.id as orgid, b1.name as orgname,"
              + " count(a3.id), sum(a3.old_salary_base), sum(a3.salary_base),"
              + " a2.old_payment,"
              + " a2.old_payment + sum(a3.org_pay + a3.emp_pay) - sum(a3.old_org_pay + a3.old_emp_pay),"
              + " a2.chg_month,"
              + " a2.chg_status"
              + " from aa203 a3, aa202 a2, aa001 a1, ba001 b1"
              + " where a3.chng_head_id = a2.id and a1.id = a2.org_id"
              + " and a1.orginfo_id = b1.id "
              + " and a1.id " 
              + securityInfo.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (orgId != null && !"".equals(orgId.trim())) {
            criterion += " and a1.id = ? ";
            parameters.add(orgId.trim());
          }
          if (orgName != null && !"".equals(orgName.trim())) {
            criterion += " and b1.name like ? ";
            parameters.add("%" + orgName.trim() + "%");
          }
          if (startChgMonth != null && !"".equals(startChgMonth.trim())) {
            criterion += " and a2.chg_month >= ? ";
            parameters.add(startChgMonth.trim());
          }
          if (endChgMonth != null && !"".equals(endChgMonth.trim())) {
            criterion += " and a2.chg_month <= ? ";
            parameters.add(endChgMonth.trim());
          }
          if (type == null) {
            criterion += " and a1.pay_mode = 1 and (a2.chg_status = 1 or (a2.chg_status = 2 and a2.pay_head_id is null)) ";
          }
          String ob = orderBy;
          if (ob == null)
            ob = "a2.chg_status asc, a2.id desc";
          String od = order;
          if (od == null)
            od = "";
          sql = sql + criterion + " group by a2.id,a1.id, b1.name,"
              + " a2.old_payment,"
              + " a2.chg_month, a2.chg_status order by " + ob + " " + od;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator it = query.list().iterator();
          SalaryBaseChgDTO dto = null;
          List rlist = new ArrayList();
          while (it.hasNext()) {
            dto = new SalaryBaseChgDTO();
            Object[] obj = (Object[]) it.next();
            dto.setId(obj[0].toString());
            dto.setOrgid(org.xpup.hafmis.common.util.BusiTools.convertTenNumber(obj[1].toString()));
            dto.setOrgname(obj[2].toString());
            dto.setPersonCount(Integer.parseInt(obj[3].toString()));
            dto.setPreSalary(new BigDecimal(obj[4].toString()));
            dto.setCurSalary(new BigDecimal(obj[5].toString()));
            dto.setPrePay(new BigDecimal(obj[6].toString()));
            dto.setCurPay(new BigDecimal(obj[7].toString()));
            dto.setChgMonth(obj[8].toString());
            dto.setChgStatus(obj[9].toString());
            rlist.add(dto);
          }
          return rlist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  public int querySalaryBaseChgCount(final String orgId, final String orgName,
      final String startChgMonth, final String endChgMonth,
      final String type, final SecurityInfo securityInfo) {
    Integer count = null;
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select count(t.id) from "
              + " (select a2.id"
              + " from aa203 a3, aa202 a2, aa001 a1, ba001 b1"
              + " where a3.chng_head_id = a2.id and a1.id = a2.org_id"
              + " and a1.orginfo_id = b1.id "
              + " and a1.id "
              + securityInfo.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (orgId != null && !"".equals(orgId.trim())) {
            criterion += " and a1.id = ? ";
            parameters.add(orgId.trim());
          }
          if (orgName != null && !"".equals(orgName.trim())) {
            criterion += " and b1.name like ? ";
            parameters.add("%" + orgName.trim() + "%");
          }
          if (startChgMonth != null && !"".equals(startChgMonth.trim())) {
            criterion += " and a2.chg_month >= ? ";
            parameters.add(startChgMonth.trim());
          }
          if (endChgMonth != null && !"".equals(endChgMonth.trim())) {
            criterion += " and a2.chg_month <= ? ";
            parameters.add(endChgMonth.trim());
          }
          if (type == null) {
            criterion += " and a1.pay_mode = 1 and (a2.chg_status = 1 or (a2.chg_status = 2 and a2.pay_head_id is null)) ";
          }
          sql = sql + criterion + " group by a2.id) t";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return Integer.valueOf(query.uniqueResult().toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }
  /**
   * @author yangg @
   */
  public List querySalaryBaseChgList(final String orgId, final String orgName,
      final String officecode, final String collectionBankId,
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String chgStatus, final String orderBy, final String order,
      final int start, final int pageSize, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select a1.id as orgid, b1.name as orgname,"
              + " count(a3.id), a1.org_rate,"
              + " a1.emp_rate, sum(a3.old_salary_base), sum(a3.salary_base),"
              + " sum(a3.old_org_pay + a3.old_emp_pay), sum(a3.org_pay + a3.emp_pay),"
              + " a1.org_pay_month, a2.chg_month, a2.biz_date,a2.id as headid"
              + " from aa203 a3, aa202 a2, aa001 a1, ba001 b1"
              + " where a3.chng_head_id = a2.id and a1.id = a2.org_id"
              + " and a1.orginfo_id = b1.id ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgId != null && !"".equals(orgId.trim())) {
            criterion += " and a1.id = ? ";
            parameters.add(orgId.trim());
          }
          if (orgName != null && !"".equals(orgName.trim())) {
            criterion += " and b1.name like ? ";
            parameters.add("%" + orgName.trim() + "%");
          }
          if (officecode != null && !"".equals(officecode.trim())) {
            criterion += " and b1.officecode = ? ";
            parameters.add(officecode.trim());
          }
          if (collectionBankId != null && !"".equals(collectionBankId.trim())) {
            criterion += " and b1.collection_bank_id = ? ";
            parameters.add(collectionBankId.trim());
          }
          if (startChgMonth != null && !"".equals(startChgMonth.trim())) {
            criterion += " and a2.chg_month >= ? ";
            parameters.add(startChgMonth.trim());
          }
          if (endChgMonth != null && !"".equals(endChgMonth.trim())) {
            criterion += " and a2.chg_month <= ? ";
            parameters.add(endChgMonth.trim());
          }
          if (startBizDate != null && !"".equals(startBizDate.trim())) {
            criterion += " and a2.biz_date >= ? ";
            parameters.add(startBizDate.trim());
          }
          if (endBizDate != null && !"".equals(endBizDate.trim())) {
            criterion += " and a2.biz_date <= ? ";
            parameters.add(endBizDate.trim());
          }
          if (chgStatus != null && !"".equals(chgStatus.trim())) {
            criterion += " and a2.chg_status = ? ";
            parameters.add(chgStatus.trim());
          }
          String ob = orderBy;
          if (ob == null)
            ob = "a1.id,a2.chg_month";
          String od = order;
          if (od == null)
            od = "";
          sql = sql + criterion + " group by a1.id, b1.name,"
              + " a1.org_rate, a1.emp_rate, a1.org_pay_month,"
              + " a2.chg_month, a2.biz_date,a2.id order by " + ob + " " + od;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator it = query.list().iterator();
          SalaryBaseChgDTO dto = null;
          List rlist = new ArrayList();
          while (it.hasNext()) {
            dto = new SalaryBaseChgDTO();
            Object[] obj = (Object[]) it.next();
            dto.setOrgid(org.xpup.hafmis.common.util.BusiTools.convertTenNumber(obj[0].toString()));
            dto.setOrgname(obj[1].toString());
            if (obj[2] != null) {
              dto.setPersonCount(Integer.parseInt(obj[2].toString()));
            } else {
              dto.setPersonCount(0);
            }
            dto.setOrgrate(obj[3].toString());
            dto.setEmprate(obj[4].toString());
            dto.setPreSalary(new BigDecimal(obj[5].toString()));
            dto.setCurSalary(new BigDecimal(obj[6].toString()));
            dto.setPrePay(new BigDecimal(obj[7].toString()));
            dto.setCurPay(new BigDecimal(obj[8].toString()));
            dto.setPayless(new BigDecimal(obj[8].toString())
                .subtract(new BigDecimal(obj[7].toString())));
            dto.setPayMonth(obj[9].toString());
            dto.setChgMonth(obj[10].toString());
            dto.setBizdate(obj[11].toString());
            dto.setId(obj[12].toString());
            rlist.add(dto);
          }
          return rlist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * @author yangg @
   */
  public List querySalaryBaseChgListAll(final String orgId,
      final String orgName, final String officecode,
      final String collectionBankId, final String startChgMonth,
      final String endChgMonth, final String startBizDate,
      final String endBizDate, final String chgStatus, final String orderBy,
      final String order, final int start, final int pageSize,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select a1.id as orgid, b1.name as orgname,"
              + " count(a3.id), a1.org_rate,"
              + " a1.emp_rate, sum(a3.old_salary_base), sum(a3.salary_base),"
              + " sum(a3.old_org_pay + a3.old_emp_pay), sum(a3.org_pay + a3.emp_pay),"
              + " a1.org_pay_month, a2.chg_month, a2.biz_date,a2.id as headid"
              + " from aa203 a3, aa202 a2, aa001 a1, ba001 b1"
              + " where a3.chng_head_id = a2.id and a1.id = a2.org_id"
              + " and a1.orginfo_id = b1.id ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgId != null && !"".equals(orgId.trim())) {
            criterion += " and a1.id = ? ";
            parameters.add(orgId.trim());
          }
          if (orgName != null && !"".equals(orgName.trim())) {
            criterion += " and b1.name like ? ";
            parameters.add("%" + orgName.trim() + "%");
          }
          if (officecode != null && !"".equals(officecode.trim())) {
            criterion += " and b1.officecode = ? ";
            parameters.add(officecode.trim());
          }
          if (collectionBankId != null && !"".equals(collectionBankId.trim())) {
            criterion += " and b1.collection_bank_id = ? ";
            parameters.add(collectionBankId.trim());
          }
          if (startChgMonth != null && !"".equals(startChgMonth.trim())) {
            criterion += " and a2.chg_month >= ? ";
            parameters.add(startChgMonth.trim());
          }
          if (endChgMonth != null && !"".equals(endChgMonth.trim())) {
            criterion += " and a2.chg_month <= ? ";
            parameters.add(endChgMonth.trim());
          }
          if (startBizDate != null && !"".equals(startBizDate.trim())) {
            criterion += " and a2.biz_date >= ? ";
            parameters.add(startBizDate.trim());
          }
          if (endBizDate != null && !"".equals(endBizDate.trim())) {
            criterion += " and a2.biz_date <= ? ";
            parameters.add(endBizDate.trim());
          }
          if (chgStatus != null && !"".equals(chgStatus.trim())) {
            criterion += " and a2.chg_status = ? ";
            parameters.add(chgStatus.trim());
          }
          sql = sql + criterion + " group by a1.id, b1.name,"
              + " a1.org_rate, a1.emp_rate, a1.org_pay_month,"
              + " a2.chg_month, a2.biz_date,a2.id ";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          SalaryBaseChgDTO dto = null;
          List rlist = new ArrayList();
          while (it.hasNext()) {
            dto = new SalaryBaseChgDTO();
            Object[] obj = (Object[]) it.next();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            if (obj[2] != null) {
              dto.setPersonCount(Integer.parseInt(obj[2].toString()));
            } else {
              dto.setPersonCount(0);
            }
            dto.setOrgrate(obj[3].toString());
            dto.setEmprate(obj[4].toString());
            dto.setPreSalary(new BigDecimal(obj[5].toString()));
            dto.setCurSalary(new BigDecimal(obj[6].toString()));
            dto.setPrePay(new BigDecimal(obj[7].toString()));
            dto.setCurPay(new BigDecimal(obj[8].toString()));
            dto.setPayless(new BigDecimal(obj[8].toString())
                .subtract(new BigDecimal(obj[7].toString())));
            dto.setPayMonth(obj[9].toString());
            dto.setChgMonth(obj[10].toString());
            dto.setBizdate(obj[11].toString());
            dto.setId(obj[12].toString());
            rlist.add(dto);
          }
          return rlist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * @author yangg @
   */
  public Object[] querySalaryBaseChgElements(final String orgId,
      final String orgName, final String officecode,
      final String collectionBankId, final String startChgMonth,
      final String endChgMonth, final String startBizDate,
      final String endBizDate, final String chgStatus, final String orderBy,
      final String order, final int start, final int pageSize,
      final SecurityInfo securityInfo) {
    Object obj[] = null;
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select count(distinct a1.id),nvl(sum(a3.old_org_pay + a3.old_emp_pay),0),nvl(sum(a3.org_pay + a3.emp_pay),0)"
              + " from aa203 a3, aa202 a2, aa001 a1, ba001 b1"
              + " where a3.chng_head_id = a2.id and a1.id = a2.org_id"
              + " and a1.orginfo_id = b1.id ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgId != null && !"".equals(orgId.trim())) {
            criterion += " and a1.id = ? ";
            parameters.add(orgId.trim());
          }
          if (orgName != null && !"".equals(orgName.trim())) {
            criterion += " and b1.name like ? ";
            parameters.add("%" + orgName.trim() + "%");
          }
          if (officecode != null && !"".equals(officecode.trim())) {
            criterion += " and b1.officecode = ? ";
            parameters.add(officecode.trim());
          }
          if (collectionBankId != null && !"".equals(collectionBankId.trim())) {
            criterion += " and b1.collection_bank_id = ? ";
            parameters.add(collectionBankId.trim());
          }
          if (startChgMonth != null && !"".equals(startChgMonth.trim())) {
            criterion += " and a2.chg_month >= ? ";
            parameters.add(startChgMonth.trim());
          }
          if (endChgMonth != null && !"".equals(endChgMonth.trim())) {
            criterion += " and a2.chg_month <= ? ";
            parameters.add(endChgMonth.trim());
          }
          if (startBizDate != null && !"".equals(startBizDate.trim())) {
            criterion += " and a2.biz_date >= ? ";
            parameters.add(startBizDate.trim());
          }
          if (endBizDate != null && !"".equals(endBizDate.trim())) {
            criterion += " and a2.biz_date <= ? ";
            parameters.add(endBizDate.trim());
          }
          if (chgStatus != null && !"".equals(chgStatus.trim())) {
            criterion += " and a2.chg_status = ? ";
            parameters.add(chgStatus.trim());
          }
          sql = sql + criterion;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Object ob = query.uniqueResult();
          if (ob != null) {
            return (Object[]) ob;
          } else {
            return null;
          }
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

}
