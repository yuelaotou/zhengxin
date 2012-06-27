package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseListImportDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.BailDTO;

public class ChgPaymentTailDAODW extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public ChgPaymentTail queryById(Integer id) {
    Validate.notNull(id);
    return (ChgPaymentTail) getHibernateTemplate()
        .get(ChgPaymentTail.class, id);
  }

  /**
   * 插入记录
   * 
   * @param chgPaymentTail
   * @return
   */
  public Serializable insert(ChgPaymentTail chgPaymentTail) {
    Serializable id = null;
    try {
      Validate.notNull(chgPaymentTail);
      id = getHibernateTemplate().save(chgPaymentTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 李娟 根据单位编号和缴存ID、缴存状态查询 职工增加或减少金额(正数时为增加，负数时为减少)
   * 
   * @param orgid
   * @param payheadId
   * @param payStatus
   * @return
   */
  public BigDecimal queryEmpPayMoney(final Serializable orgid,
      final Serializable payheadId, final String[] payStatus) {
    BigDecimal paymoney = null;
    try {
      paymoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select sum(chgPaymentTail.empPay-chgPaymentTail.oldEmpPay) "
                  + " from ChgPaymentTail chgPaymentTail ";
              Vector parameters = new Vector();
              String criterion = "";


             
        if (orgid != null&&!orgid.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.org.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
        }
        
        if (payheadId != null&&!payheadId.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.paymentHead.id = ?  and ";
          parameters.add(new Integer(payheadId.toString()));
        }else{
          criterion += " chgPaymentTail.chgPaymentHead.paymentHead.id is null and ";
        }
        if(payStatus != null && payStatus.length>0){
          criterion +="( ";
          for(int i=0;i<payStatus.length;i++){
            criterion += " chgPaymentTail.payStatus = ? or ";
            parameters.add(new Integer(payStatus[i]));
          }
          criterion =  criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") and";
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus = 2 and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        
        return query.uniqueResult();
      }
    });
    }catch(Exception e){
        e.printStackTrace();
      }
    if(paymoney == null){
      paymoney = new BigDecimal(0.00); 
          }


    return paymoney;
  }

  /**
   * 李娟 根据单位编号和缴存ID、缴存状态查询 单位增加或减少金额(正数时为增加，负数时为减少)
   * 
   * @param orgid
   * @param payheadId
   * @param payStatus
   * @return
   */
  public BigDecimal queryOrgPayMoney(final Serializable orgid,
      final Serializable payheadId, final String[] payStatus) {
    BigDecimal paymoney = null;
    try {
      paymoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select sum(chgPaymentTail.orgPay-chgPaymentTail.oldOrgPay) "
                  + " from ChgPaymentTail chgPaymentTail ";
              Vector parameters = new Vector();
              String criterion = "";
        if (orgid != null&&!orgid.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.org.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
        }
        
        if (payheadId != null&&!payheadId.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.paymentHead.id = ?  and ";
          parameters.add(new Integer(payheadId.toString()));
        }else{
          criterion += " chgPaymentTail.chgPaymentHead.paymentHead.id is null and ";
        }
        if(payStatus != null && payStatus.length>0){
          criterion +="( ";
          for(int i=0;i<payStatus.length;i++){
            criterion += " chgPaymentTail.payStatus = ? or ";
            parameters.add(new Integer(payStatus[i]));
          }
          criterion =  criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") and";
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus = 2 and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        
        return query.uniqueResult();
      }
    });
    }catch(Exception e){
        e.printStackTrace();
      }

    if (paymoney == null) {
      paymoney = new BigDecimal(0.00);
    }

    return paymoney;
  }

  /**
   * 根据主键删除单个记录
   * 
   * @return 吴洪涛 6.30
   */
  public void delete(ChgPaymentTail chgPaymentTail) {
    Validate.notNull(chgPaymentTail);
    getHibernateTemplate().delete(chgPaymentTail);
  }

  
  /**
   * 删除list
   */
  public void deleteList(List list){
    Validate.notNull(list);
    getHibernateTemplate().deleteAll(list);
  }
  
  public void deleteChgTail(final Integer chgheadId){
    try{
      getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
               String sql="delete ChgPaymentTail chgPaymentTail where chgPaymentTail.chgPaymentHead.id=?";          
               session.createQuery(sql).setInteger(0, chgheadId.intValue()).executeUpdate();
                return null;
            }
          }  
      );
        }catch(Exception e){
          e.printStackTrace();
        }
  }
  
  public List queryByCriterionsWuht(final String id, final String chgMonth,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
          parameters.add(chgMonth);
        }
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

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
    }

    );

    return orglist;
  }

  /**
   * 根据输入的条件查询职工信息(List)
   * type=A(ChgPaymentSalaryBase)
   * @param String id,
   * @param String chgMonth
   * @return 吴洪涛
   */
  public List queryByCriterions(final String id, final String chgMonth,
      final String orderBy, final String order, final int start,
      final int pageSize,final int page) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
          parameters.add(chgMonth);
        }
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

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
         List queryList=query.list();
        if(queryList==null||queryList.size()==0){           
          query.setFirstResult(pageSize*(page-2));
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          queryList=query.list();
        }
        return query.list();
      }
    }
    );

    return orglist;
  }

  public int queryCountByCriterions(final String id,
      final String changementMonth) {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String criterion = "";

          Vector parameters = new Vector();

          String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
          if (changementMonth != null) {
            criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
            parameters.add(changementMonth);
          }
          if (id != null) {
            criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
            parameters.add(new Integer(id));
          }

          if (criterion.length() != 0)
            criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));

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
   * 根据输入的条件查询职工信息
   * type=A(ChgPaymentSalaryBase)
   * @param String empid,
   * @param String orgid
   * @return 吴洪涛
   */
  public ChgPaymentTail queryChgPaymentSalaryBaseWUHT(
      final String empid, final String orgid) {
    ChgPaymentTail chgPaymentTail = (ChgPaymentTail) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
            Vector parameters = new Vector();
            String criterion = "";

            if (empid != null && !empid.equals("")) {
              criterion += "chgPaymentTail.empId = ?  and ";
              parameters.add(new Integer(empid));
            }

            if (orgid != null && !orgid.equals("")) {
              criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
              parameters.add(new Integer(orgid));
            }

            if (criterion.length() != 0)
              criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();
          }
        });
    return chgPaymentTail;
  }
  /**
   * 根据输入的条件查询职工信息
   * type=B(ChgPaymentPayment)
   * @param String empid,
   * @param String orgid
   * @return 吴洪涛
   */
  public ChgPaymentTail queryChgPaymentPaymentWUHT(
      final String empid, final String orgid) {
    ChgPaymentTail chgPaymentTail = (ChgPaymentTail) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
            Vector parameters = new Vector();
            String criterion = "";

            if (empid != null && !empid.equals("")) {
              criterion += "chgPaymentTail.empId = ?  and ";
              parameters.add(new Integer(empid));
            }

            if (orgid != null && !orgid.equals("")) {
              criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
              parameters.add(new Integer(orgid));
            }

            if (criterion.length() != 0)
              criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();
          }
        });
    return chgPaymentTail;
  }
  
  /**
   * 根据输入的条件查询职工信息(List)
   * type=B(ChgPaymentPayment)
   * @param String id,
   * @param String chgMonth
   * @return 吴洪涛
   */
  public List queryChgPaymentPaymentOtherWuht(final String id,
      final String chgMonth, final String orderBy, final String order,
      final int start, final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
          parameters.add(chgMonth);
        }
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.id ";

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
    }

    );

    return orglist;
  }
  public List queryChgPaymentPaymentWuht(final String id,
      final String chgMonth, final String orderBy, final String order,
      final int start, final int pageSize,final int page) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
          parameters.add(chgMonth);
        }
        if (id.trim() != null) {
          criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(id.trim()));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.id ";

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
         List   queryList=query.list();
        if(queryList==null||queryList.size()==0){           
          query.setFirstResult(pageSize*(page-2));
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          queryList=query.list();
        }
        return query.list();
      }
    }

    );

    return orglist;
  }

  public int queryChgPaymentPaymentWuht(final String id,
      final String changementMonth) {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String criterion = "";

          Vector parameters = new Vector();

          String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";

          if (changementMonth != null) {
            criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
            parameters.add(changementMonth);
          }
          if (id.trim() != null) {
            criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
            parameters.add(new Integer(id.trim()));
          }

          if (criterion.length() != 0)
            criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));

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
   * 根据输入的条件查询职工信息(List)
   * type=A(ChgPaymentSalaryBase)
   * @param String id,
   * @return 吴洪涛
   */
  public List queryChgPaymentTailByCriterionsWuht(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
  
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
   
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=2 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

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
    }

    );

    return orglist;
  }

  /**
   * 根据输入的条件查询职工信息(List)
   * type=B(ChgPaymentPayment)
   * @param String id,
   * @param String chgMonth
   * @return 吴洪涛
   */
  public List queryChgPaymentPaymentbyCriterionsWuht(final String id, final String orderBy, final String order,
      final int start, final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
     
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=2 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

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
        return query.list();
      }
    }

    );

    return orglist;
  }

  public List queryChgPaymentPaymentbyCriterionsOtherWuht(final String id, final String orderBy, final String order,
      final int start, final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
     
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=2 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

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
    }

    );

    return orglist;
  }

  /**
   * 根据输入的条件查询信息(List)(统计查询)
   * type=A(ChgPaymentSalaryBase)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return 吴洪涛
   */
  public List queryChgPaymentTailByCriterionsWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail.chgPaymentHead.org.id,"
                      +"chgPaymentTail.chgPaymentHead.org.orgInfo.name,"
                      +"emp.empId,"
                      +"emp.empInfo.name,"
                      +"chgPaymentTail.oldSalaryBase,"
                      +"chgPaymentTail.salaryBase,"
                      +"chgPaymentTail.chgPaymentHead.chgMonth,"
                      +"chgPaymentTail.chgPaymentHead.bizDate,"
                      +"chgPaymentTail.orgPay,"
                      +"chgPaymentTail.empPay,"
                      +"chgPaymentTail.oldOrgPay,"
                      +"chgPaymentTail.oldEmpPay"
                      +" from Emp emp,ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // 付云峰修改：加入一个新的查询条件用于弹出框的查询，根据AA202的id来查询
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += " chgPaymentTail.empId = ? and ";
          parameters.add(new Integer(empId));
        }
        
        
        
        
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and emp.empId=chgPaymentTail.empId and chgPaymentTail.chgPaymentHead.org.id=emp.org.id and chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        String ob = orderBy;
        if (ob == null)
          ob = " chgPaymentTail.chgPaymentHead.org.id DESC, chgPaymentTail.empId DESC, chgPaymentTail.id  ";

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
        Iterator it = query.list().iterator();
        List chgPaymentTailList = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj=(Object[])it.next();
          ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
          ChgPaymentHead chgPaymentHead = new ChgPaymentHead();
          Org org = new Org();
          Emp emp = new Emp();
          
          org.setId(new Integer(obj[0].toString()));
          org.getOrgInfo().setName(obj[1].toString());
          chgPaymentTail.setEmpId(new Integer(obj[2].toString()));
          emp.getEmpInfo().setName(obj[3].toString());
          chgPaymentTail.setOldSalaryBase(new BigDecimal(obj[4].toString()));
          chgPaymentTail.setSalaryBase(new BigDecimal(obj[5].toString()));
          chgPaymentHead.setChgMonth(obj[6].toString());
          chgPaymentHead.setBizDate(obj[7].toString());
          
          BigDecimal oldOrgEmpPay = new BigDecimal(0.0);
          BigDecimal orgEmpPay = new BigDecimal(0.0);
          oldOrgEmpPay=oldOrgEmpPay.add(new BigDecimal(obj[10].toString()));
          oldOrgEmpPay=oldOrgEmpPay.add(new BigDecimal(obj[11].toString()));
          orgEmpPay=orgEmpPay.add(new BigDecimal(obj[8].toString()));
          orgEmpPay=orgEmpPay.add(new BigDecimal(obj[9].toString()));
          
          chgPaymentTail.setOldOrgEmpPaySum(oldOrgEmpPay);
          chgPaymentTail.setOrgEmpPaySum(orgEmpPay);
          
          chgPaymentHead.setOrg(org);
          chgPaymentTail.setChgPaymentHead(chgPaymentHead);
          chgPaymentTail.setEmp(emp);
          chgPaymentTailList.add(chgPaymentTail);
        }
       
        return chgPaymentTailList;
      }
    }
    );

    return orglist;
  }

  /**
   * 根据输入的条件查询信息(List)(统计查询)(全部)
   * type=A(ChgPaymentSalaryBase)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return 吴洪涛
   */
  
  public List queryChgPaymentTailByCriterionsOtherWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // 付云峰修改：加入一个新的查询条件用于弹出框的查询，根据AA202的id来查询
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += "  chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += " chgPaymentTail.empId = ? and ";
          parameters.add(new Integer(empId));
        }
        
        
        
        if (criterion.length() != 0)
          criterion = "where  chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        
        String ob = orderBy;
        if (ob == null)
          ob = " chgPaymentTail.chgPaymentHead.org.id DESC, chgPaymentTail.empId DESC, chgPaymentTail.id  ";

        String od = order;
        if (od == null)
          od = "ASC";

        hql = hql + criterion + "order by " + ob + " " + od;
        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    
//        Iterator it = query.list().iterator();
//        List chgPaymentTailList = new ArrayList();
//        Object obj[] = null;
//        while (it.hasNext()) {
//          obj=(Object[])it.next();
//          ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
//          chgPaymentTail = (ChgPaymentTail)obj[0];
//          Emp emp = new Emp();
//          emp.getEmpInfo().setName(obj[1].toString());
//          chgPaymentTail.setEmp(emp);
//          chgPaymentTailList.add(chgPaymentTail);
//        }

       
        return query.list();
      }
    }

    );

    return orglist;
  }

  /**
   * 根据输入的条件查询信息(List)(统计查询)(  调整职工数：列表中的不同职工总数)
   * type=A(ChgPaymentSalaryBase)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return 吴洪涛
   */

  public List queryChgPaymentTailByCriterionsCountWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select distinct  chgPaymentTail.empId from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // 付云峰修改：加入一个新的查询条件用于弹出框的查询，根据AA202的id来查询
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += "  chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += " chgPaymentTail.empId = ? and ";
          parameters.add(new Integer(empId));
        }
        
        
        
        
        if (criterion.length() != 0)
          criterion = "where  chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        
       


        hql = hql + criterion ;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    


       
        return query.list();
      }
    }

    );

    return orglist;
  }

  /**
   * 根据输入的条件查询信息(List)(统计查询)
   * type=A(ChgPaymentPayment)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return 吴洪涛
   */
  public List queryChgPaymentChgpayChgPaymentTailByCriterionsWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
 
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail.chgPaymentHead.org.id,"
                    +"chgPaymentTail.chgPaymentHead.org.orgInfo.name,"
                    +"emp.empId,"
                    +"emp.empInfo.name,"
                    +"chgPaymentTail.oldSalaryBase,"
                    +"chgPaymentTail.salaryBase,"
                    +"chgPaymentTail.chgPaymentHead.chgMonth,"
                    +"chgPaymentTail.chgPaymentHead.bizDate,"
                    +"chgPaymentTail.orgPay,"
                    +"chgPaymentTail.empPay,"
                    +"chgPaymentTail.oldOrgPay,"
                    +"chgPaymentTail.oldEmpPay"
                    +" from Emp emp,ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // 付云峰修改：加入一个新的查询条件用于弹出框的查询，根据AA202的id来查询
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += "  chgPaymentTail.empId = ? and ";
          parameters.add(new Integer(empId));
        }
        
        
        
        
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and emp.empId=chgPaymentTail.empId and chgPaymentTail.chgPaymentHead.org.id=emp.org.id and chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        String ob = orderBy;
        if (ob == null)
          ob = " chgPaymentTail.chgPaymentHead.org.id DESC, chgPaymentTail.empId DESC, chgPaymentTail.id  ";

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
        Iterator it = query.list().iterator();
        List chgPaymentTailList = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj=(Object[])it.next();
          ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
          ChgPaymentHead chgPaymentHead = new ChgPaymentHead();
          Org org = new Org();
          Emp emp = new Emp();
          
          org.setId(new Integer(obj[0].toString()));
          org.getOrgInfo().setName(obj[1].toString());
          chgPaymentTail.setEmpId(new Integer(obj[2].toString()));
          emp.getEmpInfo().setName(obj[3].toString());
          chgPaymentTail.setOldSalaryBase(new BigDecimal(obj[4].toString()));
          chgPaymentTail.setSalaryBase(new BigDecimal(obj[5].toString()));
          chgPaymentHead.setChgMonth(obj[6].toString());
          chgPaymentHead.setBizDate(obj[7].toString());
          
          BigDecimal oldOrgEmpPay = new BigDecimal(0.0);
          BigDecimal orgEmpPay = new BigDecimal(0.0);
          oldOrgEmpPay=oldOrgEmpPay.add(new BigDecimal(obj[10].toString()));
          oldOrgEmpPay=oldOrgEmpPay.add(new BigDecimal(obj[11].toString()));
          orgEmpPay=orgEmpPay.add(new BigDecimal(obj[8].toString()));
          orgEmpPay=orgEmpPay.add(new BigDecimal(obj[9].toString()));
          
          chgPaymentTail.setOldOrgEmpPaySum(oldOrgEmpPay);
          chgPaymentTail.setOrgEmpPaySum(orgEmpPay);
          
          chgPaymentHead.setOrg(org);
          chgPaymentTail.setChgPaymentHead(chgPaymentHead);
          chgPaymentTail.setEmp(emp);
          chgPaymentTailList.add(chgPaymentTail);
        }
       
        return chgPaymentTailList;
      }
    }
//11111111111111111111111
    );

    return orglist;
  }

  /**
   * 根据输入的条件查询信息(List)(统计查询)(全部)
   * type=A(chgPaymentPayment)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return 吴洪涛
   */
  
  public List queryChgPaymentChgpayChgPaymentTailByCriterionsOtherWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // 付云峰修改：加入一个新的查询条件用于弹出框的查询，根据AA202的id来查询
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += "  chgPaymentTail.empId = ? and ";
          parameters.add(new Integer(empId));
        }
        
        
        
        if (criterion.length() != 0)
          criterion = "where  chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        
        String ob = orderBy;
        if (ob == null)
          ob = " chgPaymentTail.chgPaymentHead.org.id DESC, chgPaymentTail.empId DESC, chgPaymentTail.id  ";

        String od = order;
        if (od == null)
          od = "ASC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    
//        Iterator it = query.list().iterator();
//        List chgPaymentTailList = new ArrayList();
//        Object obj[] = null;
//        while (it.hasNext()) {
//          obj=(Object[])it.next();
//          ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
//          chgPaymentTail = (ChgPaymentTail)obj[0];
//          Emp emp = new Emp();
//          emp.getEmpInfo().setName(obj[1].toString());
//          chgPaymentTail.setEmp(emp);
//          chgPaymentTailList.add(chgPaymentTail);
//        }

       
        return query.list();
      }
    }

    );

    return orglist;
  }
  
  
  /**
   * 根据输入的条件查询信息(List)(统计查询)(  调整职工数：列表中的不同职工总数)
   * type=A(chgPaymentPayment)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return 吴洪涛
   */

  public List queryChgPaymentChgpayChgPaymentTailByCriterionsCountWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select distinct  chgPaymentTail.empId from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // 付云峰修改：加入一个新的查询条件用于弹出框的查询，根据AA202的id来查询
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// 开始日期
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += "  chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += "  To_Char(chgPaymentTail.empId) like ? and ";
          parameters.add("%" + empId + "%");
        }
        
        
        
        
        if (criterion.length() != 0)
          criterion = "where  chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        
       


        hql = hql + criterion ;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    


       
        return query.list();
      }
    }

    );

    return orglist;
  }
  
  /**
   * 根据AA202id 查找尾表
   * @param String id AA202id
   * @return AA203集合
   * 付云峰
   */
  public List queryChgPaymentTailByChgPaymentPayment(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = null;
  try {
    orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail ";
        String criterion = "";
        Vector parameters = new Vector();
   
        if (id != null) {
          criterion += "where chgPaymentTail.chgPaymentHead.id = ? ";
          parameters.add(new Integer(id));
        }
        

        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

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
        return query.list();
      }
    }

    );
  } catch (Exception e) {
    e.printStackTrace();
  }
    return orglist;
  }
  
  public int countChgPaymentTailByChgPaymentPayment(final String id) {

    List orglist = null;
  try {
    orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail ";
        String criterion = "";
        Vector parameters = new Vector();
   
        if (id != null) {
          criterion += "where chgPaymentTail.chgPaymentHead.id = ? ";
          parameters.add(new Integer(id));
        }

        hql = hql + criterion ;

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
    return orglist.size();
  }
  
  /**
   * 于庆丰 sum 缴额调整--缴额变化
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
     
        String hql = "select sum((chgPaymentTail.orgPay+chgPaymentTail.empPay)-(chgPaymentTail.oldOrgPay+chgPaymentTail.oldEmpPay)) from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment ";
        Vector parameters = new Vector();
        String criterion = "";
        if(office != null && !"".equals(office)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.officecode = ? and ";
          parameters.add(office);
        }
        if(bank != null && !"".equals(bank)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.collectionBankId = ? and ";
          parameters.add(bank);
        }
        if(orgCharacter != null && !"".equals(orgCharacter)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.character = ? and ";
          parameters.add(orgCharacter);
        }
        if(dept != null && !"".equals(dept)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.deptInCharge = ? and ";
          parameters.add(dept);
        }
        if(ragion != null && !"".equals(ragion)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.region = ? and ";
          parameters.add(ragion);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ?)  and ";
          parameters.add(startDate);
          parameters.add(endDate);
        }
        if (criterion.length() != 0) 
//          criterion = "where (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id  "
//              + securityInfo.getGjjSecurityHqlSQL()
//              + " and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
          criterion = " where chgPaymentTail.chgPaymentHead.chgStatus = 2 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and (chgPaymentTail.payStatus=1 or chgPaymentTail.payStatus=3 or chgPaymentTail.payStatus=4)  and "
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
          if(countTemp==null){
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
  
  /**
   * 于庆丰 sum 缴额调整--缴额变化
   * @param office
   * @param bank
   * @param orgCharacter
   * @param dept
   * @param ragion
   * @param startDate
   * @param endDate
   * @return
   */
  public int queryCount_(final String office,final String bank,final String orgCharacter,
      final String dept,final String ragion,final String startDate,final String endDate){
    int count = 0;
    try{
      Integer countInteger = (Integer)getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
     
        String hql = "select sum((chgPaymentTail.orgPay+chgPaymentTail.empPay)-(chgPaymentTail.oldOrgPay+chgPaymentTail.oldEmpPay)) from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase ";
        Vector parameters = new Vector();
        String criterion = "";
        if(office != null && !"".equals(office)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.officecode = ? and ";
          parameters.add(office);
        }
        if(bank != null && !"".equals(bank)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.collectionBankId = ? and ";
          parameters.add(bank);
        }
        if(orgCharacter != null && !"".equals(orgCharacter)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.character = ? and ";
          parameters.add(orgCharacter);
        }
        if(dept != null && !"".equals(dept)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.deptInCharge = ? and ";
          parameters.add(dept);
        }
        if(ragion != null && !"".equals(ragion)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.region = ? and ";
          parameters.add(ragion);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ?)  and ";
          parameters.add(startDate);
          parameters.add(endDate);
        }
        if (criterion.length() != 0) 
//          criterion = "where (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id  "
//              + securityInfo.getGjjSecurityHqlSQL()
//              + " and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
          criterion = " where chgPaymentTail.chgPaymentHead.chgStatus = 2 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and (chgPaymentTail.payStatus=1 or chgPaymentTail.payStatus=3 or chgPaymentTail.payStatus=4)  and "
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
          if(countTemp==null){
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
  /**
   * hanl
   * 公积金连续汇缴月数
   * @param empid
   * @return
   * @throws Exception
   */
  public String countMonthPaymentTail(final String empid)throws Exception{
    String count="0";
    count = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select count(monthPaymentTail.id) from MonthPaymentTail monthPaymentTail ";
          String criterion = "";
          Vector parameters = new Vector();
     
          if (empid != null&&empid.equals("")) {
            criterion += "where monthPaymentTail. = ? ";
            parameters.add(new Integer(empid));
          }

          hql = hql + criterion ;

          Query query = session.createQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.uniqueResult().toString();
        }
      }
      );
    return count;
  }
  /**
   * hanl
   * 曾逾期
   * @param empid
   * @return
   * @throws Exception
   */
  public String countOverdueInfoOweMonth(final String contractId)throws Exception{
    String oweMonth="0";
    oweMonth = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select count(distinct(t.year_month )) from pl203 t where t.loan_type = 2 ";
          if(contractId != null && !"".equals(contractId)){
            hql = hql+" and t.contract_id = '"+contractId+"'";
          }
          Query query = session.createSQLQuery(hql);

          return query.uniqueResult().toString();
        }
      }
      );
    return oweMonth;
  }
  /**
   * 吴迪 2007-12-17
   * 查询工资基数调整的导入list
   * @param empid
   * @return
   * @throws Exception
   */
  public List findAddchgslarybaseListImportList(final String orgheadID) {
    List list = null;
    try {
      list = (List)getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select aa203.emp_id," + "　(select distinct ba002.name"
              + "  from BA002 ba002, AA002 aa002 "
              + "  where aa002.emp_info_id = ba002.id "
              + "  and aa002.id = aa203.emp_id) name,"
              + "　(select distinct ba002.card_num "
              + "  from BA002 ba002, AA002 aa002 "
              + "  where aa002.emp_info_id = ba002.id "
              + "  and aa002.id = aa203.emp_id) cardnum,"
              + "　aa203.old_salary_base, " + "aa203.salary_base "
              + "  from  AA203 aa203" + " where aa203.chng_head_id= ?　";
            
          Query query = session.createSQLQuery(sql);
          query.setString(0,orgheadID);
          List queryList = query.list();
          List t = new ArrayList();
          ChgslarybaseListImportDTO chgslarybaseListImportDTOtemp= new ChgslarybaseListImportDTO();
          t.add(chgslarybaseListImportDTOtemp);
          Iterator it = queryList.iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ChgslarybaseListImportDTO chgslarybaseListImportDTO = new ChgslarybaseListImportDTO();
            if (obj[0] != null && !obj[0].equals(""))
              chgslarybaseListImportDTO.setEmpId(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              chgslarybaseListImportDTO.setEmpName(obj[1].toString());
            if (obj[2] != null && !obj[2].equals(""))
              chgslarybaseListImportDTO.setCardNum(obj[2].toString());
            if (obj[3] != null && !obj[3].equals(""))
              chgslarybaseListImportDTO.setOldSalaryBase(obj[3].toString());
            if (obj[4] != null && !obj[4].equals(""))
              chgslarybaseListImportDTO.setSalaryBase(obj[4].toString());
            t.add(chgslarybaseListImportDTO);
          }
          return t;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}