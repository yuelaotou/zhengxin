package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.AutoChangePopDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonEmpInfoDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgpersonQueryDTO;

public class ChgPersonTailDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public ChgPersonTail queryById(Integer id) {
    Validate.notNull(id);
    return (ChgPersonTail) getHibernateTemplate().get(ChgPersonTail.class, id);
  }

  /**
   * 插入记录
   * 
   * @param chgPersonTail
   * @return
   */
  public Serializable insert(ChgPersonTail chgPersonTail) {
    Serializable id = null;
    try {
      Validate.notNull(chgPersonTail);
      id = getHibernateTemplate().save(chgPersonTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 王菱 取得人员变更列表信息
   * 
   * @param id
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws Exception
   * @throws NumberFormatException
   */
  public List findChgpersonDoListByCriterions(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) throws NumberFormatException,
      Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from ChgPersonTail chgPersonTail where chgPersonTail.chgPersonHead.chgStatus=1  and ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += " chgPersonTail.chgPersonHead.org.id = ?  and ";
            parameters.add(new Integer(id));
          }

          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " chgPersonTail.id  ";

          String od = order;
          if (od == null)
            od = "ASC";

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
          return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 王菱 根据头表ID 取得人员变更列表信息
   * 
   * @param id
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws Exception
   * @throws NumberFormatException
   */
  public List findChgpersonDoListByHeadID_WL(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) throws NumberFormatException,
      Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from ChgPersonTail chgPersonTail  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += " where chgPersonTail.chgPersonHead.id = ?  and ";
            parameters.add(new Integer(id));
          }

          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " chgPersonTail.chgType  ";

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
          return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 王菱 取得人员变更列表条数
   * 
   * @param id
   * @return
   * @throws Exception
   * @throws NumberFormatException
   */
  public int queryChgpersonDoListByCriterions(final String id) {

    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from ChgPersonTail chgPersonTail  where chgPersonTail.chgPersonHead.chgStatus=1  and ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += " chgPersonTail.chgPersonHead.org.id = ?  and ";
            parameters.add(new Integer(id));
          }

          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

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
   * 王菱 取得人员变更列表条数
   * 
   * @param id
   * @return
   * @throws Exception
   * @throws NumberFormatException
   */
  public int queryChgpersonDoListByHeadID_WL(final String id) {

    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from ChgPersonTail chgPersonTail ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += " where chgPersonTail.chgPersonHead.id = ?  and ";
            parameters.add(new Integer(id));
          }

          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

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
   * 李娟 根据单位编号和缴存ID、变更类型查询增加或减少人数
   * 
   * @param orgid
   * @param payheadId
   * @param chgType
   * @return
   */
  public Integer queryPersonCount(final Serializable orgid,
      final Serializable payheadId, final String[] chgType) {
    Integer personCount = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(*) from ChgPersonTail chgPersonTail ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null && !orgid.equals("")) {
              criterion += "chgPersonTail.chgPersonHead. org.id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }

            if (payheadId != null && !payheadId.equals("")) {
              criterion += "chgPersonTail.chgPersonHead.paymentHead.id = ?  and ";
              parameters.add(new Integer(payheadId.toString()));
            } else {
              criterion += "chgPersonTail.chgPersonHead.paymentHead.id is null and ";
            }
            if (chgType != null && chgType.length > 0) {
              criterion += "( ";
              for (int i = 0; i < chgType.length; i++) {
                criterion += "chgPersonTail.chgType = ? or ";
                parameters.add(chgType[i]);
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (criterion.length() != 0)
              criterion = "where chgPersonTail.chgPersonHead.chgStatus = 2 and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            return query.uniqueResult();
          }
        });

    return personCount;
  }

  /**
   * 李娟 根据单位编号和缴存ID、变更类型查询职工增加或减少金额
   * 
   * @param orgid
   * @param payheadId
   * @param chgType
   * @return
   */
  public BigDecimal queryEmpPersonMoney(final Serializable orgid,
      final Serializable payheadId, final String[] chgType) {
    BigDecimal paymoney = null;
    paymoney = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(chgPersonTail.empPay) from ChgPersonTail chgPersonTail ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null && !orgid.equals("")) {
              criterion += "chgPersonTail.chgPersonHead. org.id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }

            if (payheadId != null && !payheadId.equals("")) {
              criterion += "chgPersonTail.chgPersonHead.paymentHead.id = ?  and ";
              parameters.add(new Integer(payheadId.toString()));
            } else {
              criterion += "chgPersonTail.chgPersonHead.paymentHead.id is null and ";
            }
            if (chgType != null && chgType.length > 0) {
              criterion += "( ";
              for (int i = 0; i < chgType.length; i++) {
                criterion += "chgPersonTail.chgType = ? or ";
                parameters.add(chgType[i]);
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (criterion.length() != 0)
              criterion = "where chgPersonTail.chgPersonHead.chgStatus = 2 and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            return query.uniqueResult();
          }
        });
    if (paymoney == null) {
      paymoney = new BigDecimal(0.00);
    }
    return paymoney;
  }

  /**
   * 李娟 根据单位编号和缴存ID、变更类型查询单位增加或减少金额
   * 
   * @param orgid
   * @param payheadId
   * @param chgType
   * @return
   */
  public BigDecimal queryOrgPersonMoney(final Serializable orgid,
      final Serializable payheadId, final String[] chgType) {
    BigDecimal paymoney = null;
    paymoney = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(chgPersonTail.orgPay) from ChgPersonTail chgPersonTail ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null && !orgid.equals("")) {
              criterion += "chgPersonTail.chgPersonHead. org.id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }

            if (payheadId != null && !payheadId.equals("")) {
              criterion += "chgPersonTail.chgPersonHead.paymentHead.id = ?  and ";
              parameters.add(new Integer(payheadId.toString()));
            } else {
              criterion += "chgPersonTail.chgPersonHead.paymentHead.id is null and ";
            }
            if (chgType != null && chgType.length > 0) {
              criterion += "( ";
              for (int i = 0; i < chgType.length; i++) {
                criterion += "chgPersonTail.chgType = ? or ";
                parameters.add(chgType[i]);
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (criterion.length() != 0)
              criterion = "where  chgPersonTail.chgPersonHead.chgStatus = 2 and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            return query.uniqueResult();
          }
        });
    if (paymoney == null) {
      paymoney = new BigDecimal(0.00);
    }
    return paymoney;
  }

  /**
   * 王菱 在未被启用的变更清册里是否存在该职工： AA204里 更状态=1.未启用对应AA205里变更清册id=AA204.id
   * AA205是否存在职工姓名、证件号码等于录入的值的记录 param orgID param empID, param empName, param
   * cardNum,证件号码 param chgMap_1 变更类型 param partInMap_1 是否参与汇缴 return
   * ChgPersonTail
   */
  public ChgPersonTail getChgPersonTail_WL(final String orgID,
      final String empID, final String empName, final String cardNum,
      final String chgMap_1, final String partInMap_1) {

    ChgPersonTail chgPersonTail = (ChgPersonTail) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgPersonTail chgPersonTail  ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgID != null && !orgID.equals("")) {
              criterion += " chgPersonTail.chgPersonHead.org.id=?  and ";
              parameters.add(new Integer(orgID.toString()));
            }
            if (chgMap_1.equals("1")) {// 变更类型：开户
              if (empName != null && !empName.equals("")) {
                criterion += " chgPersonTail.name=?  and  ";

                parameters.add(empName);
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += " (chgPersonTail.cardNum=? or chgPersonTail.standbyCardNum=?) and ";
                parameters.add(cardNum);
                parameters.add(cardNum);
              }
            } else {// 变更类型：启封或封存
              if (empID != null && !empID.equals("")) {
                criterion += " chgPersonTail.empId=?  and ";
                parameters.add(new Integer(empID));
              }
            }

            if (criterion.length() != 0)
              criterion = "where chgPersonTail.chgPersonHead.chgStatus=1 and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));

            hql = hql + criterion;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            return query.uniqueResult();
          }
        });
    return chgPersonTail;

  }

  /**
   * 王菱 AA205中是否存在变更清册ID=该删除记录的变更清册ID的其它记录
   */
  public List getOtherChgPersonTail(final String chgPersonHeadID,
      final ChgPersonTail chgPersonTail) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String tailID = chgPersonTail.getId().toString();
          String hql = " from ChgPersonTail chgPersonTail  where chgPersonTail.chgPersonHead.id=? and chgPersonTail <> ? ";
          Query query = session.createQuery(hql);
          query.setInteger(0, Integer.parseInt(chgPersonHeadID));
          query.setInteger(1, Integer.parseInt(tailID));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 王菱 删除单个记录
   * 
   * @param chgPersonTail
   */
  public void delete_WL(ChgPersonTail chgPersonTail) {
    Validate.notNull(chgPersonTail);
    getHibernateTemplate().delete(chgPersonTail);
  }

  /**
   * 王菱 删除list
   */
  public void deleteList_WL(final String chgpersonHeadID) {
    try {
      Validate.notNull(chgpersonHeadID);
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete ChgPersonTail chgPersonTail where chgPersonTail.chgPersonHead.id=?";
          session.createQuery(sql).setInteger(0,
              new Integer(chgpersonHeadID).intValue()).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 王菱 查询AA205中的记录列表
   */
  public List getChgPersonTailList_WL(final String chgPersonHeadID) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from ChgPersonTail chgPersonTail  where chgPersonTail.chgPersonHead.id=? ";
          Query query = session.createQuery(hql);
          query.setInteger(0, Integer.parseInt(chgPersonHeadID));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 王菱 统计查询-人员变更:根据各种条件查询职工列表
   * 
   * @param orgId
   * @param orgName
   * @param empId
   * @param empName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public List queryChgpersonEmpListByCriterions_WL(final String orgId,
      final String orgName, final String empId, final String empName,
      final String chgMonthStart, final String chgMonthEnd,
      final String chgDateStart, final String chgDateEnd, final int start,
      final int pageSize, final String orderBy, final String order,
      final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgPersonTail chgPersonTail ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {// 单位编号
            criterion += "chgPersonTail.chgPersonHead.org.id = ?  and ";
            parameters.add(new Integer(orgId));
          }

          if (orgName != null && !orgName.equals("")) {// 单位名称
            criterion += "chgPersonTail.chgPersonHead.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (empId != null && !empId.equals("")) {// 职工编号
            criterion += "chgPersonTail.empId = ?  and ";
            parameters.add(new Integer(empId));
          }

          if (orgName != null && !orgName.equals("")) {// 职工名称
            criterion += "chgPersonTail.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && chgMonthEnd != null && !chgMonthEnd.equals("")) {// 有开始年月结束年月
            criterion += "(chgPersonTail.chgPersonHead.chngMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && (chgMonthEnd == null || chgMonthEnd.equals(""))) {// 有开始年月无结束年月
            criterion += "chgPersonTail.chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthStart);
          }

          if (chgMonthEnd != null && !chgMonthEnd.equals("")
              && (chgMonthStart == null || chgMonthStart.equals(""))) {// 无开始年月有结束年月
            criterion += "chgPersonTail.chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && chgDateEnd != null && !chgDateEnd.equals("")) {// 有开始日期结束日期
            criterion += " (chgPersonTail.chgPersonHead.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && (chgDateEnd == null || chgDateEnd.equals(""))) {// 有开始日期无结束日期
            criterion += " chgPersonTail.chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (chgDateEnd != null && !chgDateEnd.equals("")
              && (chgDateStart == null || chgDateStart.equals(""))) {// 无开始日期有结束日期
            criterion += " chgPersonTail.chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (criterion.length() != 0) {
            criterion = " where chgPersonHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = " where chgPersonHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          String ob = orderBy;
          if (ob == null)
            ob = " chgPersonHead.id ";

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

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 王菱 统计查询-人员变更:根据各种条件查询职工条数
   * 
   * @param orgId
   * @param orgName
   * @param empId
   * @param empName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @return
   */
  public int queryChgpersonEmpCountByCriterions_WL(final String orgId,
      final String orgName, final String empId, final String empName,
      final String chgMonthStart, final String chgMonthEnd,
      final String chgDateStart, final String chgDateEnd,
      final SecurityInfo securityInfo) {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgPersonTail chgPersonTail";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {// 单位编号
            criterion += "chgPersonTail.chgPersonHead.org.id = ?  and ";
            parameters.add(new Integer(orgId));
          }

          if (orgName != null && !orgName.equals("")) {// 单位名称
            criterion += "chgPersonTail.chgPersonHead.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (empId != null && !empId.equals("")) {// 职工编号
            criterion += "chgPersonTail.empId = ?  and ";
            parameters.add(new Integer(empId));
          }

          if (orgName != null && !orgName.equals("")) {// 职工名称
            criterion += "chgPersonTail.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && chgMonthEnd != null && !chgMonthEnd.equals("")) {// 有开始年月结束年月
            criterion += "(chgPersonTail.chgPersonHead.chngMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && (chgMonthEnd == null || chgMonthEnd.equals(""))) {// 有开始年月无结束年月
            criterion += "chgPersonTail.chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthStart);
          }

          if (chgMonthEnd != null && !chgMonthEnd.equals("")
              && (chgMonthStart == null || chgMonthStart.equals(""))) {// 无开始年月有结束年月
            criterion += "chgPersonTail.chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && chgDateEnd != null && !chgDateEnd.equals("")) {// 有开始日期结束日期
            criterion += " (chgPersonTail.chgPersonHead.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && (chgDateEnd == null || chgDateEnd.equals(""))) {// 有开始日期无结束日期
            criterion += " chgPersonTail.chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (chgDateEnd != null && !chgDateEnd.equals("")
              && (chgDateStart == null || chgDateStart.equals(""))) {// 无开始日期有结束日期
            criterion += " chgPersonTail.chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (criterion.length() != 0) {
            criterion = " where chgPersonHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = " where chgPersonHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          hql = hql + criterion;
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
    count = list.size();
    return count;
  }

  /**
   * 王菱 统计查询-人员变更:根据头表ID查询职工列表
   */
  public List queryChgpersonEmpListByChgpersonHeadID_WL(
      final String chgpersonHeadID, final int start, final int pageSize,
      final String orderBy, final String order, final int page,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgPersonTail chgPersonTail ";
          Vector parameters = new Vector();
          String criterion = "";
          if (chgpersonHeadID != null && !chgpersonHeadID.equals("")) {
            criterion += "chgPersonTail.chgPersonHead.id = ?  and ";
            parameters.add(new Integer(chgpersonHeadID));
          }

          if (criterion.length() != 0) {
            criterion = "where chgPersonTail.chgPersonHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = "where chgPersonTail.chgPersonHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          String ob = orderBy;
          if (ob == null)
            ob = " chgPersonHead.id ";

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

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 王菱 统计查询-人员变更:根据头表ID查询职工条数
   */
  public int queryChgpersonEmpCountByChgpersonHeadID_WL(
      final String chgpersonHeadID, final SecurityInfo securityInfo) {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgPersonTail chgPersonTail ";
          Vector parameters = new Vector();
          String criterion = "";

          if (chgpersonHeadID != null && !chgpersonHeadID.equals("")) {
            criterion += "chgPersonTail.chgPersonHead.id = ?  and ";
            parameters.add(new Integer(chgpersonHeadID));
          }

          if (criterion.length() != 0) {
            criterion = "where chgPersonTail.chgPersonHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = "where chgPersonTail.chgPersonHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          hql = hql + criterion;
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
    count = list.size();
    return count;
  }

  /**
   * 王菱 统计查询-人员变更:根据条件查询职工列表（打印中用到）
   */
  public List queryChgpersonEmpListPrint_WL(final String orgId,
      final String orgName, final String empId, final String empName,
      final String chgMonthStart, final String chgMonthEnd,
      final String chgDateStart, final String chgDateEnd,
      final String chgpersonHeadID, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgPersonTail chgPersonTail ";
          Vector parameters = new Vector();
          String criterion = "";

          if (chgpersonHeadID != null && !chgpersonHeadID.equals("")) {
            criterion += "chgPersonTail.chgPersonHead.id = ?  and ";
            parameters.add(new Integer(chgpersonHeadID));
          } else {
            if (orgId != null && !orgId.equals("")) {// 单位编号
              criterion += "chgPersonTail.chgPersonHead.org.id = ?  and ";
              parameters.add(new Integer(orgId));
            }

            if (orgName != null && !orgName.equals("")) {// 单位名称
              criterion += "chgPersonTail.chgPersonHead.org.orgInfo.name like ?  and ";
              parameters.add("%" + orgName + "%");
            }

            if (empId != null && !empId.equals("")) {// 职工编号
              criterion += "chgPersonTail.empId = ?  and ";
              parameters.add(new Integer(empId));
            }

            if (orgName != null && !orgName.equals("")) {// 职工名称
              criterion += "chgPersonTail.name like ?  and ";
              parameters.add("%" + orgName + "%");
            }

            if (chgMonthStart != null && !chgMonthStart.equals("")
                && chgMonthEnd != null && !chgMonthEnd.equals("")) {// 有开始年月结束年月
              criterion += "(chgPersonTail.chgPersonHead.chngMonth between ? and ? ) and ";
              parameters.add(chgMonthStart);
              parameters.add(chgMonthEnd);
            }

            if (chgMonthStart != null && !chgMonthStart.equals("")
                && (chgMonthEnd == null || chgMonthEnd.equals(""))) {// 有开始年月无结束年月
              criterion += "chgPersonTail.chgPersonHead.chngMonth = ? and ";
              parameters.add(chgMonthStart);
            }

            if (chgMonthEnd != null && !chgMonthEnd.equals("")
                && (chgMonthStart == null || chgMonthStart.equals(""))) {// 无开始年月有结束年月
              criterion += "chgPersonTail.chgPersonHead.chngMonth = ? and ";
              parameters.add(chgMonthEnd);
            }

            if (chgDateStart != null && !chgDateStart.equals("")
                && chgDateEnd != null && !chgDateEnd.equals("")) {// 有开始日期结束日期
              criterion += " (chgPersonTail.chgPersonHead.bizDate  between ? and ? ) and ";
              parameters.add(chgDateStart);
              parameters.add(chgDateEnd);
            }

            if (chgDateStart != null && !chgDateStart.equals("")
                && (chgDateEnd == null || chgDateEnd.equals(""))) {// 有开始日期无结束日期
              criterion += " chgPersonTail.chgPersonHead.bizDate = ? and ";
              parameters.add(chgDateStart);
            }

            if (chgDateEnd != null && !chgDateEnd.equals("")
                && (chgDateStart == null || chgDateStart.equals(""))) {// 无开始日期有结束日期
              criterion += " chgPersonTail.chgPersonHead.bizDate = ? and ";
              parameters.add(chgDateStart);
            }
          }

          if (criterion.length() != 0) {
            criterion = "where chgPersonTail.chgPersonHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = "where chgPersonTail.chgPersonHead.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          String ob = " chgPersonTail.chgPersonHead.org.id desc,chgPersonTail.empId desc,chgPersonTail.id desc";

          hql = hql + criterion + "  order by " + ob;
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
   * 于庆丰 sum chgPersonTail.chgType=1 or chgPersonTail.chgType=3
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
  public int querySum(final String office, final String bank,
      final String orgCharacter, final String dept, final String ragion,
      final String startDate, final String endDate) {
    int count = 0;
    try {
      Integer countInteger = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(chgPersonTail.orgPay+chgPersonTail.empPay) from ChgPersonTail chgPersonTail ";
              Vector parameters = new Vector();
              String criterion = "";
              if (office != null && !"".equals(office)) {
                criterion += " chgPersonTail.chgPersonHead.org.orgInfo.officecode = ? and ";
                parameters.add(office);
              }
              if (bank != null && !"".equals(bank)) {
                criterion += " chgPersonTail.chgPersonHead.org.orgInfo.collectionBankId = ? and ";
                parameters.add(bank);
              }
              if (orgCharacter != null && !"".equals(orgCharacter)) {
                criterion += " chgPersonTail.chgPersonHead.org.orgInfo.character = ? and ";
                parameters.add(orgCharacter);
              }
              if (dept != null && !"".equals(dept)) {
                criterion += " chgPersonTail.chgPersonHead.org.orgInfo.deptInCharge = ? and ";
                parameters.add(dept);
              }
              if (ragion != null && !"".equals(ragion)) {
                criterion += " chgPersonTail.chgPersonHead.org.orgInfo.region = ? and ";
                parameters.add(ragion);
              }
              if (startDate != null && !"".equals(startDate) && endDate != null
                  && !"".equals(endDate)) {
                criterion += " (chgPersonTail.chgPersonHead.bizDate  between ?  and  ?)  and ";
                parameters.add(startDate);
                parameters.add(endDate);
              }
              if (criterion.length() != 0)
                // criterion = "where (orgHAFAccountFlow.bizStatus = 3 or
                // orgHAFAccountFlow.bizStatus = 4 or
                // orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id
                // "
                // + securityInfo.getGjjSecurityHqlSQL()
                // + " and "
                // + criterion.substring(0, criterion.lastIndexOf("and"));
                criterion = " where chgPersonTail.chgType=1 or chgPersonTail.chgType=3 and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              BigDecimal countTemp = new BigDecimal(0.00);
              Iterator it = query.iterate();
              if (it.hasNext()) {
                countTemp = (BigDecimal) it.next();
              }
              return new Integer(countTemp.intValue());
            }
          });
      count = countInteger.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 于庆丰 sum chgPersonTail.chgType=4
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
  public int querySum_(final String office, final String bank,
      final String orgCharacter, final String dept, final String ragion,
      final String startDate, final String endDate) {
    int count = 0;
    try {
      Integer countInteger = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(chgPersonTail.orgPay+chgPersonTail.empPay) from ChgPersonTail chgPersonTail ";
              Vector parameters = new Vector();
              String criterion = "";
              if (office != null && !"".equals(office)) {
                criterion += " chgPersonTail.chgPersonHead.org.orgInfo.officecode = ? and ";
                parameters.add(office);
              }
              if (bank != null && !"".equals(bank)) {
                criterion += " chgPersonTail.chgPersonHead.org.orgInfo.collectionBankId = ? and ";
                parameters.add(bank);
              }
              if (orgCharacter != null && !"".equals(orgCharacter)) {
                criterion += " chgPersonTail.chgPersonHead.org.orgInfo.character = ? and ";
                parameters.add(orgCharacter);
              }
              if (dept != null && !"".equals(dept)) {
                criterion += " chgPersonTail.chgPersonHead.org.orgInfo.deptInCharge = ? and ";
                parameters.add(dept);
              }
              if (ragion != null && !"".equals(ragion)) {
                criterion += " chgPersonTail.chgPersonHead.org.orgInfo.region = ? and ";
                parameters.add(ragion);
              }
              if (startDate != null && !"".equals(startDate) && endDate != null
                  && !"".equals(endDate)) {
                criterion += " (chgPersonTail.chgPersonHead.bizDate  between ?  and  ?)  and ";
                parameters.add(startDate);
                parameters.add(endDate);
              }
              if (criterion.length() != 0)
                // criterion = "where (orgHAFAccountFlow.bizStatus = 3 or
                // orgHAFAccountFlow.bizStatus = 4 or
                // orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id
                // "
                // + securityInfo.getGjjSecurityHqlSQL()
                // + " and "
                // + criterion.substring(0, criterion.lastIndexOf("and"));
                criterion = " where chgPersonTail.chgType=4 and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              BigDecimal countTemp = new BigDecimal(0.00);
              Iterator it = query.iterate();
              if (it.hasNext()) {
                countTemp = (BigDecimal) it.next();
              }
              if (countTemp == null) {
                return new Integer(0);
              } else {
                return new Integer(countTemp.intValue());
              }
            }
          });
      count = countInteger.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 吴迪 判断是否为aa205的最后一条 人员变更删除用
   */
  public List getOtherChgPersonTail_wd(final String chgPersonHeadID) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from ChgPersonTail chgPersonTail  where chgPersonTail.chgPersonHead.id=? ";
          Query query = session.createQuery(hql);
          query.setInteger(0, Integer.parseInt(chgPersonHeadID));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询未启用的变更
   * 
   * @param chgPersonHeadID 头表id
   * @param empId 职工id
   * @return 在AA205中的数目
   * @author 付云峰
   */
  public int queryEmpChangeById(final String chgPersonHeadID, final String empId) {

    Object obj = new Object();
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(a205.id) from aa205 a205 where a205.chg_head_id=? and a205.emp_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(chgPersonHeadID));
          query.setParameter(1, empId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    Integer count = new Integer(obj.toString());
    return count.intValue();
  }

  /**
   * 查询临时表中是否存在数据
   * 
   * @return 临时表中自动变更数据的个数
   * @author 付云峰
   */
  public int queryFnTempTable() {
    Object obj = new Object();
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f.temp_column1) from fn_temp_table f where f.type='0'";
          Query query = session.createSQLQuery(sql);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    Integer count = new Integer(obj.toString());
    return count.intValue();
  }

  /**
   * 删除临时表
   * 
   * @param type
   * @author 付云峰
   */
  public void deleteFnTempTable(String type) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      String sql = "delete FN_TEMP_TABLE t where t.type='" + type + "'";
      Statement statement = conn.createStatement();
      statement.executeUpdate(sql);
      statement.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 插入临时表的方法
   * 
   * @param printplanListDTO
   * @author 付云峰
   */
  public void insertFnTempTable(String empId, String empName, String cardNum,
      String payStatus) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      String sql = "insert into FN_TEMP_TABLE t(t.temp_column1,t.temp_column2,t.temp_column5,t.temp_column6,t.type) "
          + "values ('"
          + empId
          + "','"
          + empName
          + "','"
          + cardNum
          + "','"
          + payStatus + "','" + 0 + "')";
      Statement statement = conn.createStatement();
      statement.executeUpdate(sql);
      statement.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 插入临时表的方法
   * 
   * @param printplanListDTO
   * @author
   */
  public void insertFnTempTable_wl(List list) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement statement = conn.createStatement();
      for (int i = 0; i < list.size(); i++) {
        Object[] obj = (Object[]) list.get(i);
        String sql = "insert into FN_TEMP_TABLE t(t.temp_column1,t.temp_column2,t.temp_column5,t.temp_column6,t.type) "
            + "values ('"
            + obj[0].toString()
            + "','"
            + obj[1].toString()
            + "','"
            + obj[2].toString()
            + "','"
            + obj[3].toString()
            + "','"
            + 0
            + "')";
        statement.executeUpdate(sql);
      }
      statement.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 查询自动变更弹出框List
   * 
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @author 付云峰
   */
  public List queryAutoChangePopList(final String orderBy, final String order,
      final int start, final int pageSize) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.temp_column1,t.temp_column2,t.temp_column5,t.temp_column6 "
              + " from FN_TEMP_TABLE t where t.type='0'";
          Query query = session.createSQLQuery(hql);
          session.clear();
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          List temp_list = new ArrayList();
          Iterator it = query.list().iterator();
          Object[] obj = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            AutoChangePopDTO autoChangePopDTO = new AutoChangePopDTO();
            if (obj[0] != null) {
              autoChangePopDTO.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              autoChangePopDTO.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              autoChangePopDTO.setCardNum(obj[2].toString());
            }
            if (obj[3] != null) {
              autoChangePopDTO.setPayStatus(obj[3].toString());
            }
            temp_list.add(autoChangePopDTO);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryAutoChangePopListALL() {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.temp_column1,t.temp_column2,t.temp_column5,t.temp_column6 "
              + " from FN_TEMP_TABLE t where t.type='0'";
          Query query = session.createSQLQuery(hql);
          List temp_list = new ArrayList();
          Iterator it = query.list().iterator();
          Object[] obj = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            AutoChangePopDTO autoChangePopDTO = new AutoChangePopDTO();
            if (obj[0] != null) {
              autoChangePopDTO.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              autoChangePopDTO.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              autoChangePopDTO.setCardNum(obj[2].toString());
            }
            if (obj[3] != null) {
              autoChangePopDTO.setPayStatus(obj[3].toString());
            }
            temp_list.add(autoChangePopDTO);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询自动变更弹出框Count
   * 
   * @return
   * @author 付云峰
   */
  public List queryAutoChangePopCount() {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.temp_column1,t.temp_column2,t.temp_column5  "
              + " from FN_TEMP_TABLE t where t.type='0'";
          Query query = session.createSQLQuery(hql);
          session.clear();
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据单位编号与职工编号查询准备做变更人的信息
   * 
   * @param orgId
   * @param empId
   * @return
   * @author 付云峰
   */
  public ChgpersonEmpInfoDTO queryEmpInfo(final String orgId, final String empId) {
    ChgpersonEmpInfoDTO chgpersonEmpInfoDTO = null;
    try {
      chgpersonEmpInfoDTO = (ChgpersonEmpInfoDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select a.salary_base," + "a.org_pay,"
                  + "a.emp_pay," + "b.name," + "b.card_kind," + "b.card_num,"
                  + "b.birthday," + "b.sex," + "b.department," + "b.tel,"
                  + "b.mobile_tle," + "b.month_income,a.pay_status"
                  + " from aa002 a, ba002 b"
                  + " where a.emp_info_id = b.id and a.org_id=? and a.id=?";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, new Integer(orgId));
              query.setParameter(1, new Integer(empId));
              Object[] obj = (Object[]) query.uniqueResult();
              ChgpersonEmpInfoDTO chgpersonEmpInfoDTO = new ChgpersonEmpInfoDTO();
              if (obj[0] != null) {
                chgpersonEmpInfoDTO.setSalaryBase(new BigDecimal(obj[0]
                    .toString()));
              }
              if (obj[1] != null) {
                chgpersonEmpInfoDTO
                    .setOrgPay(new BigDecimal(obj[1].toString()));
              }
              if (obj[2] != null) {
                chgpersonEmpInfoDTO
                    .setEmpPay(new BigDecimal(obj[2].toString()));
              }
              if (obj[3] != null) {
                chgpersonEmpInfoDTO.setEmpName(obj[3].toString());
              }
              if (obj[4] != null) {
                chgpersonEmpInfoDTO.setCardKind(obj[4].toString());
              }
              if (obj[5] != null) {
                chgpersonEmpInfoDTO.setCardNum(obj[5].toString());
              }
              if (obj[6] != null) {
                chgpersonEmpInfoDTO.setBirthday(obj[6].toString());
              }
              if (obj[7] != null) {
                chgpersonEmpInfoDTO.setSex(obj[7].toString());
              }
              if (obj[8] != null) {
                chgpersonEmpInfoDTO.setDept(obj[8].toString());
              }
              if (obj[9] != null) {
                chgpersonEmpInfoDTO.setTel(obj[9].toString());
              }
              if (obj[10] != null) {
                chgpersonEmpInfoDTO.setMobileTel(obj[10].toString());
              }
              if (obj[11] != null) {
                chgpersonEmpInfoDTO.setMonthLncome(new BigDecimal(obj[11]
                    .toString()));
              }
              if (obj[12] != null) {
                chgpersonEmpInfoDTO.setPayStatus(obj[12].toString());
              }
              return chgpersonEmpInfoDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return chgpersonEmpInfoDTO;
  }

  /**
   * 王菱 取得人员变更列表信息
   * 
   * @param id
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws Exception
   * @throws NumberFormatException
   */
  // 吴洪涛 2008-6-16
  public List findChgpersonDoListAllByCriterions(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) throws NumberFormatException,
      Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from ChgPersonTail chgPersonTail where chgPersonTail.chgPersonHead.chgStatus=1  and ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += " chgPersonTail.chgPersonHead.org.id = ?  and ";
            parameters.add(new Integer(id));
          }

          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " chgPersonTail.chgType  ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List queryList = query.list();

          return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List findChgpersonDoListAllByCriterions_wsh(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) throws NumberFormatException,
      Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from ChgPersonTail chgPersonTail where  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += " chgPersonTail.chgPersonHead.id = ?  and ";
            parameters.add(new Integer(id));
          }

          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " chgPersonTail.chgType  ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List queryList = query.list();

          return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询归集银行
   * 
   * @return
   */
  public CollBank getCollBankByCollBankid(final String collBankid) {
    CollBank collBank = null;
    collBank = (CollBank) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = " from CollBank collBank where collBank.status=1 and collBank.collBankId = ? ";
            Query query = session.createQuery(sql);
            query.setParameter(0, new Integer(collBankid));

            return query.uniqueResult();
          }
        });
    return collBank;
  }

  /**
   * Copy Right Information
   * :增加自动启封按钮，功能为：点击后，弹出窗口，显示该单位状态为转入封存的职工，可以进行选择（可全选），选择后点击确定后，将选中职工加入到变更清册中，变更类型为启封。
   * AutoChangePopAF
   * 
   * @Version : v1.0
   * @author : 吴洪涛
   * @Date : 2008.6.18
   */
  public List queryTranInTailByCriterions(final String inOrgId,
      final String empId, final String orderBy, final String order,
      final int start, final int pageSize, final int page) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select tranInTail from TranInTail tranInTail,Emp emp ";
          Vector parameters = new Vector();
          String criterion = "";
          if (inOrgId != null && !inOrgId.equals("")) {
            criterion += "tranInTail.tranInHead.tranInOrg.id= ?  and ";
            parameters.add(new Integer(inOrgId));
          }
          if (empId != null && !empId.equals("")) {
            criterion += "tranInTail.empId= ? ";
            parameters.add(new Integer(empId));
          }

          if (criterion.length() != 0)
            criterion = "where emp.org.id=tranInTail.tranInHead.tranInOrg.id and emp.empId=tranInTail.empId "
                + "and (emp.payStatus='2' or emp.payStatus='5') and (tranInTail.isAutoChg not in (1) or tranInTail.isAutoChg is null) and tranInTail.tranInHead.tranStatus=5 and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " tranInTail.id  ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;
          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          List queryList = query.list();

          // if (queryList == null || queryList.size() == 0) {
          // query.setFirstResult(pageSize * (page - 2));
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);
          // queryList = query.list();
          // }
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryTranInTailByCriterionsAll(final String inOrgId,
      final String empId, final String orderBy, final String order,
      final int start, final int pageSize, final int page) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select tranInTail from TranInTail tranInTail,Emp emp ";
          Vector parameters = new Vector();
          String criterion = "";
          if (inOrgId != null && !inOrgId.equals("")) {
            criterion += "tranInTail.tranInHead.tranInOrg.id= ?  and ";
            parameters.add(new Integer(inOrgId));
          }
          if (empId != null && !empId.equals("")) {
            criterion += "tranInTail.empId= ?  and ";
            parameters.add(new Integer(empId));
          }

          if (criterion.length() != 0)
            // criterion = "where (tranInTail.isAutoChg not in (1) or
            // tranInTail.isAutoChg is null) and
            // tranInTail.tranInHead.tranStatus=5 and "
            // + criterion.substring(0, criterion.lastIndexOf("and"));
            criterion = " where emp.org.id=tranInTail.tranInHead.tranInOrg.id and emp.empId=tranInTail.empId "
                + "and (emp.payStatus='2' or emp.payStatus='5') and (tranInTail.isAutoChg not in (1) or tranInTail.isAutoChg is null) and tranInTail.tranInHead.tranStatus=5 and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " tranInTail.id  ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;
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

  public List queryTranInTail(final String inOrgId, final String empId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranInTail tranInTail ";
          Vector parameters = new Vector();
          String criterion = "";
          if (inOrgId != null && !inOrgId.equals("")) {
            criterion += "tranInTail.tranInHead.tranInOrg.id= ?  and ";
            parameters.add(new Integer(inOrgId));
          }
          if (empId != null && !empId.equals("")) {
            criterion += "tranInTail.empId= ?  and ";
            parameters.add(new Integer(empId));
          }

          if (criterion.length() != 0)
            criterion = "where (tranInTail.isAutoChg not in (1) or tranInTail.isAutoChg is null)  and tranInTail.tranInHead.tranStatus=5 and   "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
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

  public List queryTranInTailIsAutoChg(final String inOrgId, final String empId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranInTail tranInTail ";
          Vector parameters = new Vector();
          String criterion = "";
          if (inOrgId != null && !inOrgId.equals("")) {
            criterion += "tranInTail.tranInHead.tranInOrg.id= ?  and ";
            parameters.add(new Integer(inOrgId));
          }
          if (empId != null && !empId.equals("")) {
            criterion += "tranInTail.empId= ?  and ";
            parameters.add(new Integer(empId));
          }

          if (criterion.length() != 0)
            criterion = "where tranInTail.isAutoChg  in (1) and tranInTail.tranInHead.tranStatus=5 and   "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
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

  public List getChgpersonQueryList(final String office, final String bankid,
      final String begDate, final String endDate, final String type, final String orgid,
      final int start, final int pageSize) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a1.id as orgid,b1.name as orgname,a5.emp_id as empid,"
              + "a5.name as empname,a5.chg_type,a4.biz_date,a4.chg_status "
              + "from aa204 a4,aa205 a5,aa001 a1,ba001 b1 "
              + "where a4.id=a5.chg_head_id "
              + "and a4.org_id=a1.id "
              + "and a1.orginfo_id=b1.id ";
          if (office != null && !office.equals("")) {
            sql += "and b1.officecode = '" + office + "' ";
          }
          if (bankid != null && !bankid.equals("")) {
            sql += "and b1.collection_bank_id = '" + bankid + "' ";
          }
          if (begDate != null && !begDate.equals("")) {
            sql += "and a4.biz_date >= '" + begDate + "' ";
          }
          if (endDate != null && !endDate.equals("")) {
            sql += "and a4.biz_date <= '" + endDate + "' ";
          }
          if (type != null && !type.equals("")) {
            if (type.equals("12")) {
              sql += "and a5.chg_type in (1,2) ";
            } else {
              sql += "and a5.chg_type = '" + type + "' ";
            }
          }
          if (orgid != null && !orgid.equals("")) {
            sql += "and a1.id = " + new Integer(orgid) + " ";
          }
          sql += " order by a1.id";
          Query query = session.createSQLQuery(sql);
          query.setFirstResult(start);
          query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          Object[] obj = null;
          ChgpersonQueryDTO dto = null;
          List rlist = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            dto = new ChgpersonQueryDTO();
            dto.setOrgid(new BigDecimal(obj[0].toString()));
            dto.setOrgname(obj[1].toString());
            dto.setEmpid(new BigDecimal(obj[2].toString()));
            dto.setEmpname(obj[3].toString());
            if (obj[4].toString().equals("1") || obj[4].toString().equals("2")) {
              dto.setType("开户");
            } else if (obj[4].toString().equals("3")) {
              dto.setType("启封");
            } else if (obj[4].toString().equals("4")) {
              dto.setType("封存");
            }
            dto.setBizDate(obj[5].toString());
            if (obj[6].toString().equals("1")) {
              dto.setStatus("未启用");
            } else {
              dto.setStatus("启用");
            }
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

  public int getChgpersonQueryListAll(final String office, final String bankid,
      final String begDate, final String endDate, final String type, final String orgid) {
    Integer num = new Integer(0);
    try {
      num = (Integer) getHibernateTemplate().execute(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(a1.id) "
              + "from aa204 a4,aa205 a5,aa001 a1,ba001 b1 "
              + "where a4.id=a5.chg_head_id " + "and a4.org_id=a1.id "
              + "and a1.orginfo_id=b1.id ";
          if (office != null && !office.equals("")) {
            sql += "and b1.officecode = '" + office + "' ";
          }
          if (bankid != null && !bankid.equals("")) {
            sql += "and b1.collection_bank_id = '" + bankid + "' ";
          }
          if (begDate != null && !begDate.equals("")) {
            sql += "and a4.biz_date >= '" + begDate + "' ";
          }
          if (endDate != null && !endDate.equals("")) {
            sql += "and a4.biz_date <= '" + endDate + "' ";
          }
          if (type != null && !type.equals("")) {
            if (type.equals("12")) {
              sql += "and a5.chg_type in (1,2) ";
            } else {
              sql += "and a5.chg_type = '" + type + "' ";
            }
          }
          if (orgid != null && !orgid.equals("")) {
            sql += "and a1.id = " + new Integer(orgid) + " ";
          }
          Query query = session.createSQLQuery(sql);
          return new Integer(query.uniqueResult().toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return num.intValue();
  }

  public void deleteAsistantBorrowerInfoByAuxiliaryid(final String idaf) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "delete from FnTempTable p where p.type=" + idaf;
          Query query = session.createQuery(hql);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
