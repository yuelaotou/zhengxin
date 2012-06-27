  package org.xpup.hafmis.sysloan.common.dao;

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
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.dto.EmpaccountinfoDTO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanRate;
import org.xpup.hafmis.sysloan.dataready.rate.dto.RateDTO;

/**
 * 贷款利率维护表PL001
 * 
 * @author 李娟 2007-9-13
 */
public class LoanRateDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public LoanRate queryById(Serializable id) {
    Validate.notNull(id);
    return (LoanRate) getHibernateTemplate().get(LoanRate.class, id);
  }

  /**
   * 插入记录
   * 
   * @param loanRate
   * @return
   */
  public Serializable insert(LoanRate loanRate) {
    Serializable id = null;
    try {
      Validate.notNull(loanRate);
      id = getHibernateTemplate().save(loanRate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 查询记录 07.09.21
   * 
   * @param orderBy，order，start，pageSize，page
   * @return list
   */
  public List queryRate_sy(final String officecode,final String usetime,
      final String ratetype,final String basis,
      final String orderBy, final String order,
      final int start, final int pageSize, final int page){
    List list = null;
    try {
      list = (List) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select loanRate.loanRateId," +
                           "loanRate.office,loanRate.loanRateType," +
                           "loanRate.loanMonthRate,loanRate.ajustDate," +
                           "loanRate.appDate,loanRate.status,loanRate.adjustBasis from LoanRate loanRate ";
              Vector parameters = new Vector();
              String criterion = "";

              if (officecode != null && !officecode.equals("")) {
                criterion += " loanRate.office = ?  and ";
                parameters.add(officecode);
              }

              if (usetime != null && !usetime.equals("")) {
                criterion += " loanRate.appDate = ?  and ";
                parameters.add(usetime.trim());
              }

              if (ratetype != null && !ratetype.equals("")) {
                criterion += " loanRate.loanRateType = ?  and ";
                parameters.add(ratetype);
              }
              
              if (basis != null && !basis.equals("")) {
                criterion += " loanRate.adjustBasis like ?  and ";
                parameters.add("%"+basis.trim()+"%");
              }

              if (criterion.length() != 0)
                criterion = "where  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              
              String ob = orderBy;
              if (ob == null)
                ob = "loanRate.loanRateId";
              String od = order;
              if (od == null)
                od = "DESC";
              
              hql = hql + criterion+"order by " + ob + " " + order;
              
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
              Iterator it = queryList.iterator();
              List t = new ArrayList();
              Object obj[] = null;
              while (it.hasNext()) {
                obj = (Object[]) it.next();
                RateDTO rateDTO = new RateDTO();
                rateDTO.setId(new Integer(obj[0].toString()));
                rateDTO.setOffice(obj[1].toString());
                rateDTO.setLoanRateType(obj[2].toString());
                rateDTO.setLoanMonthRate(new BigDecimal(obj[3].toString()));
                rateDTO.setAjustDate(obj[4].toString());
                // 启用时间可以为空。
                if (obj[5] != null) {
                  rateDTO.setAppDate(obj[5].toString());
                }
                rateDTO.setStatus(obj[6].toString());
                if (obj[7] !=null){
                  rateDTO.setAdjustBasis(obj[7].toString());
                }else{
                  rateDTO.setAdjustBasis("");
                }
                t.add(rateDTO);
              }
              return t;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 统计查询记录 07.09.21
   * 
   * @param
   * @return list 中存在个数组存的是利率pl001的个数
   */
  public int countRate_sy(final String officecode,final String usetime, final String ratetype,final String basis) {
    Integer count = new Integer(0);
      try {
        count = (Integer) getHibernateTemplate().execute(
            new HibernateCallback() {
              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {

                String hql = "select loanRate.loanRateId, loanRate.office,loanRate.loanRateType,loanRate.loanMonthRate,loanRate.ajustDate,loanRate.appDate,loanRate.status from LoanRate loanRate ";
                Vector parameters = new Vector();
                String criterion = "";

                if (officecode != null && !officecode.equals("")) {
                  criterion += " loanRate.office = ?  and ";
                  parameters.add(officecode);
                }

                if (usetime != null && !usetime.equals("")) {
                  criterion += " loanRate.appDate = ?  and ";
                  parameters.add(usetime.trim());
                }

                if (ratetype != null && !ratetype.equals("")) {
                  criterion += " loanRate.loanRateType = ?  and ";
                  parameters.add(ratetype);
                }

                if (basis != null && !basis.equals("")) {
                  criterion += " loanRate.adjustBasis like ?  and ";
                  parameters.add("%"+basis.trim()+"%");
                }
                
                if (criterion.length() != 0)
                  criterion = "where  "
                      + criterion.substring(0, criterion.lastIndexOf("and"));
                
                hql = hql + criterion;
                
                Query query = session.createQuery(hql);
                for (int i = 0; i < parameters.size(); i++) {
                  query.setParameter(i, parameters.get(i));
                }
                 return new Integer(query.list().size());
              }
            });
      } catch (Exception e) {
        e.printStackTrace();
      }
    return count.intValue();
  }

  /**
   * 查找记录 07.09.21
   * 
   * @param office
   * @return list
   */
  public List queryRate_sy(final String offciecode, final String loanRateType) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select max(loanRate.loanRateId) from LoanRate loanRate where loanRate.office=? and loanRate.loanRateType=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, offciecode);
          query.setParameter(1, loanRateType);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查找办事处名称 07.09.21
   * 
   * @param officecode
   * @return list中包含officename
   */
  public List queryOfficecodeName(final String officecode) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select organizationUnit.name from OrganizationUnit organizationUnit where organizationUnit.type='2' and organizationUnit.id = ?";
          Query query = session.createQuery(hql);
          query.setString(0, officecode);
          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 石岩
   * 利率启用
   * 办事处的集合，启用时间，启用的新利率，启用时间前的上个月及最后一天
   */
  
  public void useRate_sy(String pl001Id,String usedate,String lastloankou,String ip,String operator,String appmode) throws BusinessException, HibernateException, SQLException{
    try{
    Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
    CallableStatement cs=conn.prepareCall("{call USERATE(?,?,?,?,?,?)}");
    cs.setString(1, pl001Id);
    cs.setString(2, usedate);
    cs.setString(3, lastloankou);
    cs.setString(4, ip);
    cs.setString(5, operator);
    cs.setString(6, appmode);
    cs.execute();
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("利率调整失败!!!");
    }
  }
  /**
   * 查找记录 07.09.21
   * 
   * @param office
   * @return list
   */
  public List queryRate_sy() {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select max(loanRate.loanRateId),loanRate.office,loanRate.loanRateType from LoanRate loanRate where loanRate.status='0' group by loanRate.office , loanRate.loanRateType ";
          Query query = session.createQuery(hql);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * hanl
   * 求利率
   * @param office
   * @param loantype
   * @return
   */

  public BigDecimal findMontRate(final String office,final String loantype) {
    BigDecimal monthRate = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.loan_month_rate from pl001 p where p.loan_rate_id=(select max(p1.loan_rate_id) from pl001 p1 where p1.office=? and p1.loan_rate_type=?)";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, office);
            query.setParameter(1, loantype);
            return query.uniqueResult();
          }
        });

    return monthRate;
  }
  public String findLatestDate(final String office,final String appDate,
      final String ratetype,final String adjustBasis) {
    String latest = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(p.ajust_date) from pl001 p " +
                         "where p.loan_rate_id = (select max(p1.loan_rate_id) from pl001 p1 ";
            Vector parameters = new Vector();
            String criterion = "";

            if (office != null && !office.equals("")) {
              criterion += " p1.office = ?  and ";
              parameters.add(office);
            }

            if (appDate != null && !appDate.equals("")) {
              criterion += " p1.app_date = ?  and ";
              parameters.add(appDate.trim());
            }

            if (ratetype != null && !ratetype.equals("")) {
              criterion += " p1.loan_rate_type = ?  and ";
              parameters.add(ratetype);
            }

            if (adjustBasis != null && !adjustBasis.equals("")) {
              criterion += " p1.adjusted_basis like ?  and ";
              parameters.add("%"+adjustBasis.trim()+"%");
            }
            
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            
            hql = hql + criterion + ")";
            
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();
          }
        });

    return latest;
  }
  /**
   * wsh
   * 求利率
   * @param office
   * @param loantype
   * @return
   */

  public BigDecimal findMontRate_wsh(final String contractId,final String loanKouYearMonth) {
    BigDecimal monthRate = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl201.loan_rate from pl201 where pl201.contract_id=? and pl201.loan_kou_yearmonth=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractId);
            query.setParameter(1, loanKouYearMonth);
            return query.uniqueResult();
          }
        });

    return monthRate;
  }
  /**
   * yg
   * @return String
   */
  
  public String findpl111_timelimit(final String contractId) {
    String timelimit = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
            String hql = "select t.overplus_limite from pl111 t where t.contract_id = ?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractId);
            return query.uniqueResult();
          }
        });
    return timelimit;
  }
  /**
   * shiy
   * 求利率
   * @param bankId
   * @param loantype
   * @return
   */

  public BigDecimal findMontRate_sy(final String bankId, final String loantype) {
    BigDecimal monthRate = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl001.loan_month_rate  from pl001 where pl001.loan_rate_id = (select max(qpl001.loan_rate_id)"
                + " from pl001 qpl001, pl002 qpl002"
                + " where qpl001.loan_rate_type = '"
                + loantype
                + "'"
                + " and qpl002.office = qpl001.office"
                + " and qpl002.loan_bank_id = " + bankId + ")";
            Query query = session.createSQLQuery(hql);
            return query.uniqueResult();
          }
        });

    return monthRate;
  }

  public BigDecimal findMontRate_yg(final String office, final String loantype) {
    BigDecimal monthRate = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl001.loan_month_rate  from pl001 where pl001.loan_rate_id = (select max(qpl001.loan_rate_id)"
                + " from pl001 qpl001, pl002 qpl002"
                + " where qpl001.loan_rate_type = '"
                + loantype
                + "'"
                + " and qpl002.office = qpl001.office"
                + " and qpl001.office = '" + office + "')";
            Query query = session.createSQLQuery(hql);
            return query.uniqueResult();
          }
        });
    return monthRate;
  }
  
  public List findEmpInfo(final String empid, final String orgid) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
        SQLException {
          String hql = "from Emp emp where emp.empId=? and emp.org.id=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(empid));
          query.setParameter(1, new Integer(orgid));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  public List queryRate_wsh(final String officecode,
      final String ratetype){
    List list = null;
    try {
      list = (List) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select loanRate.loanRateId," +
                           "loanRate.office,loanRate.loanRateType," +
                           "loanRate.loanMonthRate,loanRate.ajustDate," +
                           "loanRate.appDate,loanRate.status,loanRate.adjustBasis from LoanRate loanRate ";
              Vector parameters = new Vector();
              String criterion = "";

              if (officecode != null && !officecode.equals("")) {
                criterion += " loanRate.office = ?  and ";
                parameters.add(officecode);
              }

              if (ratetype != null && !ratetype.equals("")) {
                criterion += " loanRate.loanRateType = ?  and ";
                parameters.add(ratetype);
              }
              
              
              if (criterion.length() != 0)
                criterion = "where  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
            
              
              hql = hql + criterion;
              
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              List queryList = query.list();
              Iterator it = queryList.iterator();
              List t = new ArrayList();
              Object obj[] = null;
              while (it.hasNext()) {
                obj = (Object[]) it.next();
                RateDTO rateDTO = new RateDTO();
                rateDTO.setId(new Integer(obj[0].toString()));
                rateDTO.setOffice(obj[1].toString());
                rateDTO.setLoanRateType(obj[2].toString());
                rateDTO.setLoanMonthRate(new BigDecimal(obj[3].toString()));
                rateDTO.setAjustDate(obj[4].toString());
                // 启用时间可以为空。
                if (obj[5] != null) {
                  rateDTO.setAppDate(obj[5].toString());
                }
                rateDTO.setStatus(obj[6].toString());
                if (obj[7] !=null){
                  rateDTO.setAdjustBasis(obj[7].toString());
                }else{
                  rateDTO.setAdjustBasis("");
                }
                t.add(rateDTO);
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
