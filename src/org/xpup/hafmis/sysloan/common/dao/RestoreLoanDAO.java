package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
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
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.dto.CarryforwardTbDTO;
import org.xpup.hafmis.sysloan.common.domain.entity.RestoreLoan;
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanListDTO;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTcDTO;

public class RestoreLoanDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public RestoreLoan queryById(Serializable id) {
    Validate.notNull(id);
    return (RestoreLoan) getHibernateTemplate().get(RestoreLoan.class, id);
  }

  /**
   * 插入记录
   * 
   * @param RestoreLoan
   * @return
   */
  public Serializable insert(RestoreLoan restoreLoan) {
    Serializable id = null;
    try {
      Validate.notNull(restoreLoan);
      id = getHibernateTemplate().save(restoreLoan);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 根据合同编号查询应还信息(不算本月) 回收办理 jj
   * 
   * @param contractId
   * @return
   */
  public List queryRestoreLoanListByContractId_LJA(final String contractId,
      final String yearMonth) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select restoreLoan.loanKouYearmonth,restoreLoan.shouldCorpus,restoreLoan.shouldInterest,"
              + " restoreLoan.realCorpus, restoreLoan.realInterest,restoreLoan.punishInterest,restoreLoan.loanRate,restoreLoan.bizDate "
              + " from RestoreLoan restoreLoan ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "restoreLoan.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (yearMonth != null && !yearMonth.equals("")) {
            criterion += "restoreLoan.loanKouYearmonth < ?  and ";
            parameters.add(yearMonth);
          }
          if (criterion.length() != 0)
            criterion = " where (nvl(restoreLoan.shouldCorpus,0)-nvl(restoreLoan.realCorpus,0)>0"
                + " or nvl(restoreLoan.shouldInterest,0)-nvl(restoreLoan.realInterest,0)>0 or nvl(restoreLoan.punishInterest,0)>0) and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion
              + " order by restoreLoan.bizDate, restoreLoan.loanKouYearmonth ";
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List temp_list = new ArrayList();
          Iterator it = query.iterate();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
            shouldBackListDTO.setLoanKouYearmonth(obj[0].toString());
            shouldBackListDTO
                .setShouldCorpus(new BigDecimal(obj[1].toString()));
            shouldBackListDTO.setShouldInterest(new BigDecimal(obj[2]
                .toString()));
            shouldBackListDTO.setRealCorpus(new BigDecimal(obj[3].toString()));
            if (obj[6] != null) {
              shouldBackListDTO.setLoanRate(new BigDecimal(obj[6].toString()));
              shouldBackListDTO.setShow_loanRate(new BigDecimal(obj[6]
                  .toString()).multiply(new BigDecimal(100))
                  + "%");
            }
            shouldBackListDTO.setPunishInterest(new BigDecimal(obj[5]
                .toString()));
            shouldBackListDTO
                .setRealInterest(new BigDecimal(obj[4].toString()));
            if (obj[7] != null) {
              shouldBackListDTO.setBizDate(obj[7].toString());
            }
            temp_list.add(shouldBackListDTO);
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
   * 根据合同编号查询应还信息(算本月) 回收办理 jj
   * 
   * @param contractId
   * @return
   */
  public List queryRestoreLoanListByContractId_LJB(final String contractId,
      final String yearMonth) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select restoreLoan.loanKouYearmonth,restoreLoan.shouldCorpus,restoreLoan.shouldInterest,"
              + " restoreLoan.realCorpus, restoreLoan.realInterest,restoreLoan.punishInterest,restoreLoan.loanRate,restoreLoan.bizDate  "
              + " from RestoreLoan restoreLoan ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "restoreLoan.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (yearMonth != null && !yearMonth.equals("")) {
            criterion += "restoreLoan.loanKouYearmonth <= ?  and ";
            parameters.add(yearMonth);
          }
          if (criterion.length() != 0)
            criterion = " where (nvl(restoreLoan.shouldCorpus,0)-nvl(restoreLoan.realCorpus,0)>0"
                + " or nvl(restoreLoan.shouldInterest,0)-nvl(restoreLoan.realInterest,0)>0 or nvl(restoreLoan.punishInterest,0)>0) and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion
              + " order by restoreLoan.bizDate, restoreLoan.loanKouYearmonth ";
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List temp_list = new ArrayList();
          Iterator it = query.iterate();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
            shouldBackListDTO.setLoanKouYearmonth(obj[0].toString());
            shouldBackListDTO
                .setShouldCorpus(new BigDecimal(obj[1].toString()));
            shouldBackListDTO.setShouldInterest(new BigDecimal(obj[2]
                .toString()));
            shouldBackListDTO.setRealCorpus(new BigDecimal(obj[3].toString()));
            if (obj[6] != null) {
              shouldBackListDTO.setLoanRate(new BigDecimal(obj[6].toString()));
              shouldBackListDTO.setShow_loanRate(new BigDecimal(obj[6]
                  .toString()).multiply(new BigDecimal(100))
                  + "%");
            }
            shouldBackListDTO.setPunishInterest(new BigDecimal(obj[5]
                .toString()));
            shouldBackListDTO
                .setRealInterest(new BigDecimal(obj[4].toString()));
            if (obj[7] != null) {
              shouldBackListDTO.setBizDate(obj[7].toString());
            }
            temp_list.add(shouldBackListDTO);
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
   * 根据合同编号查询应还信息(不算本月) 回收办理 判断超前还款 jj
   * 
   * @param contractId
   * @return
   */
  public List queryRestoreLoanListByContractId_LJE(final String contractId,
      final String yearMonth) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select restoreLoan.loanKouYearmonth,restoreLoan.shouldCorpus,restoreLoan.shouldInterest,"
              + " restoreLoan.realCorpus, restoreLoan.realInterest,restoreLoan.punishInterest,restoreLoan.loanRate,restoreLoan.bizDate "
              + " from RestoreLoan restoreLoan ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "restoreLoan.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (yearMonth != null && !yearMonth.equals("")) {
            criterion += "restoreLoan.loanKouYearmonth > ?  and ";
            parameters.add(yearMonth);
          }
          if (criterion.length() != 0)
            criterion = " where (nvl(restoreLoan.shouldCorpus,0)+nvl(restoreLoan.shouldInterest,0)<>"
                + "nvl(restoreLoan.realCorpus,0)+nvl(restoreLoan.realInterest,0) and "
                + " nvl(restoreLoan.realCorpus,0)+nvl(restoreLoan.realInterest,0)>0) and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by restoreLoan.loanKouYearmonth ";
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List temp_list = new ArrayList();
          Iterator it = query.iterate();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
            shouldBackListDTO.setLoanKouYearmonth(obj[0].toString());
            shouldBackListDTO
                .setShouldCorpus(new BigDecimal(obj[1].toString()));
            shouldBackListDTO.setShouldInterest(new BigDecimal(obj[2]
                .toString()));
            shouldBackListDTO.setRealCorpus(new BigDecimal(obj[3].toString()));
            if (obj[6] != null) {
              shouldBackListDTO.setLoanRate(new BigDecimal(obj[6].toString()));
              shouldBackListDTO.setShow_loanRate(new BigDecimal(obj[6]
                  .toString()).multiply(new BigDecimal(100))
                  + "%");
            }
            shouldBackListDTO.setPunishInterest(new BigDecimal(obj[5]
                .toString()));
            shouldBackListDTO
                .setRealInterest(new BigDecimal(obj[4].toString()));
            if (obj[7] != null) {
              shouldBackListDTO.setBizDate(obj[7].toString());
            }
            temp_list.add(shouldBackListDTO);
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
   * 根据合同编号查询应还信息(算本月) 回收办理 判断超前还款 jj
   * 
   * @param contractId
   * @return
   */
  public List queryRestoreLoanListByContractId_LJD(final String contractId,
      final String yearMonth) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select restoreLoan.loanKouYearmonth,restoreLoan.shouldCorpus,restoreLoan.shouldInterest,"
              + " restoreLoan.realCorpus, restoreLoan.realInterest,restoreLoan.punishInterest,restoreLoan.loanRate,restoreLoan.bizDate  "
              + " from RestoreLoan restoreLoan ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "restoreLoan.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (yearMonth != null && !yearMonth.equals("")) {
            criterion += "restoreLoan.loanKouYearmonth >= ?  and ";
            parameters.add(yearMonth);
          }
          if (criterion.length() != 0)
            criterion = " where (nvl(restoreLoan.shouldCorpus,0)+nvl(restoreLoan.shouldInterest,0)<>"
                + "nvl(restoreLoan.realCorpus,0)+nvl(restoreLoan.realInterest,0) and "
                + " nvl(restoreLoan.realCorpus,0)+nvl(restoreLoan.realInterest,0)>0) and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by restoreLoan.loanKouYearmonth ";
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List temp_list = new ArrayList();
          Iterator it = query.iterate();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
            shouldBackListDTO.setLoanKouYearmonth(obj[0].toString());
            shouldBackListDTO
                .setShouldCorpus(new BigDecimal(obj[1].toString()));
            shouldBackListDTO.setShouldInterest(new BigDecimal(obj[2]
                .toString()));
            shouldBackListDTO.setRealCorpus(new BigDecimal(obj[3].toString()));
            if (obj[6] != null) {
              shouldBackListDTO.setLoanRate(new BigDecimal(obj[6].toString()));
              shouldBackListDTO.setShow_loanRate(new BigDecimal(obj[6]
                  .toString()).multiply(new BigDecimal(100))
                  + "%");
            }
            shouldBackListDTO.setPunishInterest(new BigDecimal(obj[5]
                .toString()));
            shouldBackListDTO
                .setRealInterest(new BigDecimal(obj[4].toString()));
            if (obj[7] != null) {
              shouldBackListDTO.setBizDate(obj[7].toString());
            }
            temp_list.add(shouldBackListDTO);
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
   * 根据合同编号查询会计年月下的应还信息 办理回收提前还款 jj
   * 
   * @param contractId
   * @param yearMonth
   * @return
   */
  public List queryRestoreLoanInfoByContractId_LJ(final String contractId,
      final String yearMonth) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select restoreLoan.loanKouYearmonth,restoreLoan.shouldCorpus,restoreLoan.shouldInterest,"
              + " restoreLoan.realCorpus, restoreLoan.realInterest,restoreLoan.punishInterest,restoreLoan.loanRate,restoreLoan.bizDate  "
              + " from RestoreLoan restoreLoan ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "restoreLoan.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (yearMonth != null && !yearMonth.equals("")) {
            criterion += "restoreLoan.loanKouYearmonth =  ? and ";
            parameters.add(yearMonth);
          }
          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List temp_list = new ArrayList();
          Iterator it = query.iterate();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
            shouldBackListDTO.setLoanKouYearmonth(obj[0].toString());
            shouldBackListDTO
                .setShouldCorpus(new BigDecimal(obj[1].toString()));
            shouldBackListDTO.setShouldInterest(new BigDecimal(obj[2]
                .toString()));
            shouldBackListDTO.setRealCorpus(new BigDecimal(obj[3].toString()));
            if (obj[6] != null) {
              shouldBackListDTO.setLoanRate(new BigDecimal(obj[6].toString()));
            }
            shouldBackListDTO.setPunishInterest(new BigDecimal(obj[5]
                .toString()));
            shouldBackListDTO
                .setRealInterest(new BigDecimal(obj[4].toString()));
            if (obj[7] != null) {
              shouldBackListDTO.setBizDate(obj[7].toString());
            }
            temp_list.add(shouldBackListDTO);
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
   * 回收咨询会计年内所有应还信息 jj
   * 
   * @param contractId
   * @param yearMonth
   * @return
   */
  public List queryRestoreLoanListByContractId_LJC(final String contractId,
      final String year) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select restoreLoan.loanKouYearmonth,restoreLoan.shouldCorpus,restoreLoan.shouldInterest,"
              + " restoreLoan.realCorpus, restoreLoan.realInterest,restoreLoan.punishInterest,restoreLoan.loanRate,restoreLoan.bizDate  "
              + " from RestoreLoan restoreLoan ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "restoreLoan.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (year != null && !year.equals("")) {
            criterion += "restoreLoan.loanKouYearmonth like ?  and ";
            parameters.add(year + "%");
          }
          if (criterion.length() != 0)
            criterion = " where (nvl(restoreLoan.shouldCorpus,0)-nvl(restoreLoan.realCorpus,0)>0"
                + " or nvl(restoreLoan.shouldInterest,0)-nvl(restoreLoan.realInterest,0)>0 or nvl(restoreLoan.punishInterest,0)>0) and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List temp_list = new ArrayList();
          Iterator it = query.iterate();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
            shouldBackListDTO.setLoanKouYearmonth(obj[0].toString());
            shouldBackListDTO
                .setShouldCorpus(new BigDecimal(obj[1].toString()));
            shouldBackListDTO.setShouldInterest(new BigDecimal(obj[2]
                .toString()));
            shouldBackListDTO.setRealCorpus(new BigDecimal(obj[3].toString()));
            if (obj[6] != null) {
              shouldBackListDTO.setLoanRate(new BigDecimal(obj[6].toString()));
            }
            shouldBackListDTO.setPunishInterest(new BigDecimal(obj[5]
                .toString()));
            shouldBackListDTO
                .setRealInterest(new BigDecimal(obj[4].toString()));
            if (obj[7] != null) {
              shouldBackListDTO.setBizDate(obj[7].toString());
            }
            temp_list.add(shouldBackListDTO);
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
   * 根据合同编号查询应还信息 还款计划报表
   * 
   * @param contractId
   * @return
   */
  public List queryRestoreLoanListByContractId_sy(final String contractId,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select restoreLoan.loanKouYearmonth,restoreLoan.shouldCorpus,restoreLoan.shouldInterest,"
              + " restoreLoan.realCorpus, restoreLoan.realInterest,restoreLoan.punishInterest,restoreLoan.loanRate,restoreLoan.bizDate  "
              + " from RestoreLoan restoreLoan ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "restoreLoan.contractId = ?  and ";
            parameters.add(contractId);
          }

          if (criterion.length() != 0)
            criterion = " where (nvl(restoreLoan.shouldCorpus,0)-nvl(restoreLoan.realCorpus,0)>0"
                + " or nvl(restoreLoan.shouldInterest,0)-nvl(restoreLoan.realInterest,0)>0 ) and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List temp_list = new ArrayList();
          Iterator it = query.iterate();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            PrintplanListDTO printplanListDTO = new PrintplanListDTO();
            printplanListDTO.setLoanKouYearmonth(obj[0].toString());
            printplanListDTO.setShouldCorpus(new BigDecimal(obj[1].toString()));
            printplanListDTO
                .setShouldInterest(new BigDecimal(obj[2].toString()));
            printplanListDTO.setRealCorpus(new BigDecimal(obj[3].toString()));
            if (obj[6] != null) {
              printplanListDTO.setLoanRate(new BigDecimal(obj[6].toString()));
            }
            printplanListDTO
                .setPunishInterest(new BigDecimal(obj[5].toString()));
            printplanListDTO.setRealInterest(new BigDecimal(obj[4].toString()));
            if (obj[7] != null) {
              printplanListDTO.setBizDate(obj[7].toString());
            }
            temp_list.add(printplanListDTO);
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
   * 根据合同编号查询应还信息 还款计划报表
   * 
   * @param contractId
   * @return 回收办理已调用jj
   */
  public List countRestoreLoanListByContractId_sy(final String contractId) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select restoreLoan.loanKouYearmonth,restoreLoan.shouldCorpus,restoreLoan.shouldInterest,"
              + " restoreLoan.realCorpus, restoreLoan.realInterest,nvl(restoreLoan.punishInterest,0),nvl(restoreLoan.loanRate,0),restoreLoan.bizDate "
              + " from RestoreLoan restoreLoan ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "restoreLoan.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (criterion.length() != 0)
            criterion = " where (nvl(restoreLoan.shouldCorpus,0)-nvl(restoreLoan.realCorpus,0)>0"
                + " or nvl(restoreLoan.shouldInterest,0)-nvl(restoreLoan.realInterest,0)>0)  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion + " order by restoreLoan.loanKouYearmonth ";
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List temp_list = new ArrayList();
          Iterator it = query.iterate();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            PrintplanListDTO printplanListDTO = new PrintplanListDTO();
            printplanListDTO.setLoanKouYearmonth(obj[0].toString());
            printplanListDTO.setShouldCorpus(new BigDecimal(obj[1].toString()));
            printplanListDTO
                .setShouldInterest(new BigDecimal(obj[2].toString()));
            printplanListDTO.setRealCorpus(new BigDecimal(obj[3].toString()));
            if (obj[6] != null) {
              printplanListDTO.setLoanRate(new BigDecimal(obj[6].toString()));
            }
            printplanListDTO
                .setPunishInterest(new BigDecimal(obj[5].toString()));
            printplanListDTO.setRealInterest(new BigDecimal(obj[4].toString()));
            if (obj[7] != null) {
              printplanListDTO.setBizDate(obj[7].toString());
            }
            temp_list.add(printplanListDTO);
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
   * 根据放款银行和日期查询应还信息
   * 
   * @param loanBankId
   * @param yearMonth
   * @return
   */
  public List queryRestoreLoanListByBank_LJ(final String loanBankId,
      final String yearMonth, final String day) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select loanKouAcc,contractId,ovaerLoanRepay,loanKouYearmonth,shouldCorpus,shouldInterest,"
              + " realCorpus,realInterest,punishInterest,loanRate,bizDate,loanBankId,loanRepayDay "
              + " from "
              + " (select pl111.loan_kou_acc as loanKouAcc, pl111.contract_id as contractId,"
              + " nvl(pl111.ovaer_loan_repay,0) as ovaerLoanRepay,pl201.loan_kou_yearmonth as loanKouYearmonth,"
              + " nvl(pl201.should_corpus,0) as shouldCorpus,nvl(pl201.should_interest,0) as shouldInterest,nvl(pl201.real_corpus,0) as realCorpus,"
              + " nvl(pl201.real_interest,0) as realInterest,nvl(pl201.punish_interest,0) as punishInterest,pl201.loan_rate as loanRate,"
              + " pl201.biz_date as bizDate,pl201.loan_bank_id as loanBankId,pl111.loan_repay_day as loanRepayDay "
              + " from PL201 pl201,PL111 pl111"
              + " where pl201.contract_id = pl111.contract_id and "
              + " (nvl(pl201.should_corpus,0)-nvl(pl201.real_corpus,0)>0 or nvl(pl201.should_interest,0)-nvl(pl201.real_interest,0)>0 or "
              + " nvl(pl201.punish_interest,0)>0) and "
              + " pl111.loan_repay_day > '"
              + day
              + "' and pl201.loan_kou_yearmonth < '"
              + yearMonth
              + "' "
              + " union "
              + " select pl111.loan_kou_acc as loanKouAcc, pl111.contract_id as contractId,"
              + " nvl(pl111.ovaer_loan_repay,0) as ovaerLoanRepay,pl201.loan_kou_yearmonth as loanKouYearmonth,"
              + " nvl(pl201.should_corpus,0) as shouldCorpus,nvl(pl201.should_interest,0) as shouldInterest,nvl(pl201.real_corpus,0) as realCorpus,"
              + " nvl(pl201.real_interest,0) as realInterest,nvl(pl201.punish_interest,0) as punishInterest,pl201.loan_rate as loanRate,"
              + " pl201.biz_date as bizDate,pl201.loan_bank_id as loanBankId,pl111.loan_repay_day as loanRepayDay "
              + " from PL201 pl201,PL111 pl111 "
              + " where pl201.contract_id = pl111.contract_id "
              + " and (nvl(pl201.should_corpus,0)-nvl(pl201.real_corpus,0)>0 or nvl(pl201.should_interest,0)-nvl(pl201.real_interest,0)>0 or "
              + " nvl(pl201.punish_interest,0)>0) and "
              + " pl111.loan_repay_day <= '"
              + day
              + "' and pl201.loan_kou_yearmonth <= '"
              + yearMonth
              + "'"
              + " union "
              + " select pl111.loan_kou_acc as loanKouAcc,"
              + " pl111.contract_id as contractId,"
              + " nvl(pl111.ovaer_loan_repay, 0) as ovaerLoanRepay,"
              + " pl201.loan_kou_yearmonth as loanKouYearmonth,"
              + " nvl(pl201.should_corpus, 0) as shouldCorpus,"
              + " nvl(pl201.should_interest, 0) as shouldInterest,"
              + " nvl(pl201.real_corpus, 0) as realCorpus,"
              + " nvl(pl201.real_interest, 0) as realInterest,"
              + " nvl(pl201.punish_interest, 0) as punishInterest,"
              + " pl201.loan_rate as loanRate,"
              + " pl201.biz_date as bizDate,"
              + " pl201.loan_bank_id as loanBankId,"
              + " pl111.loan_repay_day as loanRepayDay"
              + " from PL201 pl201, PL111 pl111"
              + " where pl201.contract_id = pl111.contract_id"
              + " and (nvl(pl201.should_corpus, 0) - nvl(pl201.real_corpus, 0) > 0 or"
              + " nvl(pl201.should_interest, 0) - nvl(pl201.real_interest, 0) > 0 or"
              + " nvl(pl201.punish_interest, 0) > 0)"
              + " and pl111.loan_repay_day > to_char(last_day(to_date('"
              + yearMonth
              + "','yyyymm')),'dd')"
              + " and pl201.loan_kou_yearmonth <= '"
              + yearMonth
              + "'"
              + " and to_char(last_day(to_date('"
              + yearMonth
              + "','yyyymm')),'dd') = '" + day + "')";

          Vector parameters = new Vector();
          String criterion = "";
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          sql = sql + criterion;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
            dto.setLoanKouAcc(obj[0].toString());
            dto.setContractId(obj[1].toString());
            dto.setOvaerLoanRepay(new BigDecimal(obj[2].toString()));
            dto.setLoanKouYearmonth(obj[3].toString());
            dto.setShouldCorpus(new BigDecimal(obj[4].toString()));
            dto.setShouldInterest(new BigDecimal(obj[5].toString()));
            dto.setRealCorpus(new BigDecimal(obj[6].toString()));
            dto.setRealInterest(new BigDecimal(obj[7].toString()));
            dto.setPunishInterest(new BigDecimal(obj[8].toString()));
            dto.setLoanRate(new BigDecimal(obj[9].toString()));
            if (obj[10] != null) {
              dto.setBizDate(obj[10].toString());
            }
            dto.setLoanRepayDay(obj[12].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryRestoreLoanListByBank_LJ_gjj(final String loanBankId,
      final String yearMonth, final String day) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select loanKouAcc,contractId,ovaerLoanRepay,loanKouYearmonth,shouldCorpus,shouldInterest,"
              + " realCorpus,realInterest,punishInterest,loanRate,bizDate,loanBankId,loanRepayDay "
              + " from "
              + " (select pl111.loan_kou_acc as loanKouAcc, pl111.contract_id as contractId,"
              + " nvl(pl111.ovaer_loan_repay,0) as ovaerLoanRepay,pl201.loan_kou_yearmonth as loanKouYearmonth,"
              + " nvl(pl201.should_corpus,0) as shouldCorpus,nvl(pl201.should_interest,0) as shouldInterest,nvl(pl201.real_corpus,0) as realCorpus,"
              + " nvl(pl201.real_interest,0) as realInterest,nvl(pl201.punish_interest,0) as punishInterest,pl201.loan_rate as loanRate,"
              + " pl201.biz_date as bizDate,pl201.loan_bank_id as loanBankId,pl111.loan_repay_day as loanRepayDay "
              + " from PL201 pl201,PL111 pl111"
              + " where pl201.contract_id = pl111.contract_id and "
              + " (nvl(pl201.should_corpus,0)-nvl(pl201.real_corpus,0)>0 or nvl(pl201.should_interest,0)-nvl(pl201.real_interest,0)>0 or "
              + " nvl(pl201.punish_interest,0)>0) and "
              + " pl111.loan_repay_day > '"
              + day
              + "' and pl201.loan_kou_yearmonth < '"
              + yearMonth
              + "' "
              + " union "
              + " select pl111.loan_kou_acc as loanKouAcc, pl111.contract_id as contractId,"
              + " nvl(pl111.ovaer_loan_repay,0) as ovaerLoanRepay,pl201.loan_kou_yearmonth as loanKouYearmonth,"
              + " nvl(pl201.should_corpus,0) as shouldCorpus,nvl(pl201.should_interest,0) as shouldInterest,nvl(pl201.real_corpus,0) as realCorpus,"
              + " nvl(pl201.real_interest,0) as realInterest,nvl(pl201.punish_interest,0) as punishInterest,pl201.loan_rate as loanRate,"
              + " pl201.biz_date as bizDate,pl201.loan_bank_id as loanBankId,pl111.loan_repay_day as loanRepayDay "
              + " from PL201 pl201,PL111 pl111 "
              + " where pl201.contract_id = pl111.contract_id "
              + " and (nvl(pl201.should_corpus,0)-nvl(pl201.real_corpus,0)>0 or nvl(pl201.should_interest,0)-nvl(pl201.real_interest,0)>0 or "
              + " nvl(pl201.punish_interest,0)>0) and "
              + " pl111.loan_repay_day <= '"
              + day
              + "' and pl201.loan_kou_yearmonth <= '"
              + yearMonth
              + "'"
              + " union "
              + " select pl111.loan_kou_acc as loanKouAcc,"
              + " pl111.contract_id as contractId,"
              + " nvl(pl111.ovaer_loan_repay, 0) as ovaerLoanRepay,"
              + " pl201.loan_kou_yearmonth as loanKouYearmonth,"
              + " nvl(pl201.should_corpus, 0) as shouldCorpus,"
              + " nvl(pl201.should_interest, 0) as shouldInterest,"
              + " nvl(pl201.real_corpus, 0) as realCorpus,"
              + " nvl(pl201.real_interest, 0) as realInterest,"
              + " nvl(pl201.punish_interest, 0) as punishInterest,"
              + " pl201.loan_rate as loanRate,"
              + " pl201.biz_date as bizDate,"
              + " pl201.loan_bank_id as loanBankId,"
              + " pl111.loan_repay_day as loanRepayDay"
              + " from PL201 pl201, PL111 pl111"
              + " where pl201.contract_id = pl111.contract_id"
              + " and (nvl(pl201.should_corpus, 0) - nvl(pl201.real_corpus, 0) > 0 or"
              + " nvl(pl201.should_interest, 0) - nvl(pl201.real_interest, 0) > 0 or"
              + " nvl(pl201.punish_interest, 0) > 0)"
              + " and pl111.loan_repay_day > to_char(last_day(to_date('"
              + yearMonth
              + "','yyyymm')),'dd')"
              + " and pl201.loan_kou_yearmonth <= '"
              + yearMonth
              + "'"
              + " and to_char(last_day(to_date('"
              + yearMonth
              + "','yyyymm')),'dd') = '" + day + "')";

          Vector parameters = new Vector();
          String criterion = "";
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " loanBankId = ?  and loanKouAcc in (select pl400.loan_kou_acc from pl400 pl400 where pl400.reservea_b = '1' and pl400.status = '0') and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          sql = sql + criterion;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
            dto.setLoanKouAcc(obj[0].toString());
            dto.setContractId(obj[1].toString());
            dto.setOvaerLoanRepay(new BigDecimal(obj[2].toString()));
            dto.setLoanKouYearmonth(obj[3].toString());
            dto.setShouldCorpus(new BigDecimal(obj[4].toString()));
            dto.setShouldInterest(new BigDecimal(obj[5].toString()));
            dto.setRealCorpus(new BigDecimal(obj[6].toString()));
            dto.setRealInterest(new BigDecimal(obj[7].toString()));
            dto.setPunishInterest(new BigDecimal(obj[8].toString()));
            dto.setLoanRate(new BigDecimal(obj[9].toString()));
            if (obj[10] != null) {
              dto.setBizDate(obj[10].toString());
            }
            dto.setLoanRepayDay(obj[12].toString());
            temp_list.add(dto);
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
   * 根据贷款帐号与还款年月查询本金与利息
   * 
   * @param yearMonth 还款年月
   * @param loanKouAcc 贷款帐号
   * @return
   * @author 付云峰
   */
  public Object[] queryCorpusAndInterest(final String yearMonth,
      final String loanKouAcc) {

    Object[] obj = null;
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select nvl(p01.real_corpus,0),nvl(p01.real_interest,0),nvl(p01.punish_interest,0) "
              + "from pl201 p01, pl111 p11 "
              + "where p11.contract_id = p01.contract_id and "
              + "p11.loan_kou_acc=? and " + "p01.loan_kou_yearmonth=?";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, loanKouAcc);
          query.setParameter(1, yearMonth);

          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return obj;
  }

  /**
   * 以银行为主时判断还款年月
   * 
   * @param yearMonth
   * @param loanKouAcc
   * @return
   */
  public Object[] queryCorpusAndInterest_bank(final String yearMonth,
      final String loanKouAcc) {

    Object[] obj = null;
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select nvl(sum(p203.real_corpus),0),nvl(sum(p203.real_interest),0),nvl(sum(p203.real_punish_interest),0)"
              + " from pl202 p202, pl203 p203"
              + " where p202.flow_head_id = p203.flow_head_id"
              + " and p202.biz_st = '6'"
              + " and p202.biz_type in ('2','3','4','5')"
              + " and p203.loan_kou_acc = ?" + " and p203.year_month = ?";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, loanKouAcc);
          query.setParameter(1, yearMonth);

          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return obj;
  }

  /**
   * hanl 求出上个月的月还款额
   * 
   * @param temp_contactid
   * @param yearmonth
   * @return
   */
  public String findPreMonthPay(final String temp_contactid,
      final String yearmonth) {
    String monthpay = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select nvl(sum(pl.should_corpus+pl.should_interest),0) from pl201 pl where pl.contract_id=? and pl.loan_kou_yearmonth=? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, temp_contactid);
            query.setParameter(1, yearmonth);
            String m = query.uniqueResult().toString();
            return m;
          }
        });

    return monthpay;
  }

  public String findPl004(final String temp_contactid) {
    String monthpay = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = " select distinct (t.contract_id) from pl400 t where t.status = '0' and t.reservea_b = '1' and t.contract_id = ?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, temp_contactid);
            String m = "";
            if (query.uniqueResult() != null
                && !"".equals(query.uniqueResult())) {
              m = "是";
            } else {
              m = "否";
            }
            return m;
          }
        });

    return monthpay;
  }

  /**
   * hanl 求出本月的月还款额
   * 
   * @param temp_contactid
   * @param yearmonth
   * @return
   */
  public String findthisMonthPay(final String temp_contactid,
      final String yearmonth) {
    String monthpay = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select nvl(sum(pl.should_corpus+pl.should_interest),0) from pl201 pl where pl.contract_id=? and pl.loan_kou_yearmonth=? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, temp_contactid);
            query.setParameter(1, yearmonth);
            Object obj = null;
            obj = (Object) query.uniqueResult();
            if (obj != null) {
              return obj.toString();
            }
            return "";
          }
        });

    return monthpay;
  }

  /**
   * hanl 求出未还金额（除了本月的）
   * 
   * @param contractIdHl
   * @param yearmonth
   * @return
   */
  public LoandeskaccqueryTcDTO findOweMoneya(final String contractIdHl,
      final String yearmonth) {
    LoandeskaccqueryTcDTO loandeskaccqueryTcDTO = null;
    try {
      loandeskaccqueryTcDTO = (LoandeskaccqueryTcDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(p.should_corpus-p.real_corpus),sum(p.should_interest-p.real_interest),sum(p.punish_interest) from pl201 p where p.contract_id=? and p.loan_kou_yearmonth<? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, contractIdHl);
              query.setParameter(1, yearmonth);
              LoandeskaccqueryTcDTO loandeskaccqueryTcDTO = new LoandeskaccqueryTcDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loandeskaccqueryTcDTO.setOwecorpus(obj[0].toString());
                }
                if (obj[1] != null) {
                  loandeskaccqueryTcDTO.setOweinterest(obj[1].toString());
                }
                if (obj[2] != null) {
                  loandeskaccqueryTcDTO.setPunishinterest(obj[2].toString());
                }
              }
              return loandeskaccqueryTcDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loandeskaccqueryTcDTO;
  }

  /**
   * hanl 求出未还金额，所有的
   * 
   * @param contractIdHl
   * @param yearmonth
   * @return
   */
  public LoandeskaccqueryTcDTO findOweMoneyb(final String contractIdHl,
      final String yearmonth) {
    LoandeskaccqueryTcDTO loandeskaccqueryTcDTO = null;
    try {
      loandeskaccqueryTcDTO = (LoandeskaccqueryTcDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(p.should_corpus-p.real_corpus),sum(p.should_interest-p.real_interest),sum(p.punish_interest) from pl201 p where p.contract_id=? and p.loan_kou_yearmonth<=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, contractIdHl);
              query.setParameter(1, yearmonth);
              LoandeskaccqueryTcDTO loandeskaccqueryTcDTO = new LoandeskaccqueryTcDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loandeskaccqueryTcDTO.setOwecorpus(obj[0].toString());
                }
                if (obj[1] != null) {
                  loandeskaccqueryTcDTO.setOweinterest(obj[1].toString());
                }
                if (obj[2] != null) {
                  loandeskaccqueryTcDTO.setPunishinterest(obj[2].toString());
                }
              }
              return loandeskaccqueryTcDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loandeskaccqueryTcDTO;
  }

  /**
   * 2007.10.30 查看是否年结记录 shiy param 合同编号，借款人姓名，贷款帐号，还款年月 return list
   */
  public List queryRestoreLoanList_sy(final String contractId,
      final String loanKouAcc, final String borrowerName,
      final String carryforwardYear, final String orderBy, final String order,
      final int start, final int pageSize, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select restoreLoan.loanBankId,borrowerAcc.loanKouAcc,restoreLoan.contractId,borrower.borrowerName,restoreLoan.loanKouYearmonth,restoreLoan.shouldCorpus,restoreLoan.shouldInterest "
              + " from RestoreLoan restoreLoan,BorrowerAcc borrowerAcc,Borrower borrower ";
          Vector parameters = new Vector();
          String criterion = "";
          if (contractId != null && !contractId.equals("")) {
            criterion += "restoreLoan.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += "borrower.borrowerName = ?  and ";
            parameters.add(borrowerName);
          }
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += "borrowerAcc.loanKouAcc = ?  and ";
            parameters.add(loanKouAcc);
          }
          if (carryforwardYear != null && !carryforwardYear.equals("")) {
            criterion += "restoreLoan.loanKouYearmonth  like ?  and ";
            parameters.add(carryforwardYear + "%");
          }
          if (criterion.length() != 0)
            criterion = " where borrowerAcc.contractId=restoreLoan.contractId and restoreLoan.contractId=borrower.contractId and restoreLoan.loanBankId "
                + securityInfo.getDkSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " restoreLoan.contractId ";

          String od = order;
          if (od == null)
            od = " DESC";
          hql = hql + criterion + " order by " + ob + " " + od;
          ;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List temp_list = new ArrayList();
          Iterator it = query.iterate();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CarryforwardTbDTO carryforwardTbDTO = new CarryforwardTbDTO();
            carryforwardTbDTO.setLoanBankId(obj[0] + "");
            carryforwardTbDTO.setLoanKouAcc(obj[1] + "");
            carryforwardTbDTO.setContractId(obj[2] + "");
            carryforwardTbDTO.setBorrowerName(obj[3] + "");
            carryforwardTbDTO.setLoanKouMonth(obj[4] + "");
            carryforwardTbDTO.setShouldCorpus(new BigDecimal(obj[5] + ""));
            carryforwardTbDTO.setShouldInterest(new BigDecimal(obj[6] + ""));
            carryforwardTbDTO.setNewLoanInterest(carryforwardTbDTO
                .getShouldCorpus().add(carryforwardTbDTO.getShouldInterest()));
            temp_list.add(carryforwardTbDTO);
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
   * 2007.10.30 查看是否年结记录 shiy param 合同编号，借款人姓名，贷款帐号，还款年月 return list
   */
  public int countRestoreLoanList_sy(final String contractId,
      final String loanKouAcc, final String borrowerName,
      final String carryforwardYear, final SecurityInfo securityInfo) {
    Integer countRestoreLoan = new Integer(0);
    try {
      countRestoreLoan = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(restoreLoan.id)"
                  + " from RestoreLoan restoreLoan,BorrowerAcc borrowerAcc,Borrower borrower ";
              Vector parameters = new Vector();
              String criterion = "";
              if (contractId != null && !contractId.equals("")) {
                criterion += "restoreLoan.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (borrowerName != null && !borrowerName.equals("")) {
                criterion += "borrower.borrowerName = ?  and ";
                parameters.add(borrowerName);
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "borrowerAcc.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (carryforwardYear != null && !carryforwardYear.equals("")) {
                criterion += "restoreLoan.loanKouYearmonth  like ?  and ";
                parameters.add(carryforwardYear + "%");
              }
              if (criterion.length() != 0)
                criterion = " where borrowerAcc.contractId=restoreLoan.contractId and restoreLoan.contractId=borrower.contractId and restoreLoan.loanBankId "
                    + securityInfo.getDkSecurityHqlSQL()
                    + " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              List list = query.list();
              Integer countnumber = new Integer(0);
              if (!list.isEmpty())
                countnumber = new Integer(list.get(0).toString());
              return countnumber;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return countRestoreLoan.intValue();
  }

  /**
   * 石岩 年终结转 结转银行，结转时间
   */

  public void useCarryforward_sy(String loanBankId, String bizdate, String ip,
      String operson) throws BusinessException, HibernateException,
      SQLException {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      CallableStatement cs = conn.prepareCall("{call CARRYFORWARD(?,?,?,?)}");
      cs.setString(1, loanBankId);
      cs.setString(2, bizdate);
      cs.setString(3, ip);
      cs.setString(4, operson);
      // cs.setString(5, operson);
      cs.execute();
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("年终结转失败!!!");
    }
  }

  /**
   * 石岩 年终结转以银行为主 结转银行，结转时间
   */
  public void useBankCarryforward_sy(final String bankId, final String bizdate) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update LoanBank loanBank set loanBank.yearClear=?  where  loanBank.loanBankId =? ";
          Query query = session.createQuery(hql);
          query.setString(0, bizdate);
          query.setBigDecimal(1, new BigDecimal(bankId));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 打印还款计划表
   * 
   * @author 郭婧平 2008-03-11 删除临时表FN_TEMP_TABLE中的数据
   */
  public void deleteFnTempTable() {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      String sql = "delete from FN_TEMP_TABLE t where t.type=2 ";
      Statement statement = conn.createStatement();
      statement.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * hanl 删除
   * 
   * @param id
   */
  public void deleteRestoreLoan(final String id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "delete from RestoreLoan restoreLoan where restoreLoan.contractId=?";
          Query query = session.createQuery(sql);
          query.setParameter(0, id);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 打印还款计划表
   * 
   * @author 郭婧平 2008-03-11 插入临时表FN_TEMP_TABLE中的数据 TEMP_COLUMN1 string 还款年月
   *         TEMP_COLUMN2 string 应还本金 TEMP_COLUMN3 number 应还利息 TEMP_COLUMN4
   *         number 应还本息合计 TEMP_COLUMN5 string 每月利率
   */
  public void insertFnTempTable(final PrintplanListDTO printplanListDTO) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      String sql = "insert into FN_TEMP_TABLE t(t.temp_column1,t.temp_column2,t.temp_column3,t.temp_column4,t.temp_column5,t.type ) "
          + "values ('"
          + printplanListDTO.getLoanKouYearmonth()
          + "','"
          + printplanListDTO.getShouldCorpus()
          + "',"
          + printplanListDTO.getShouldInterest()
          + ","
          + printplanListDTO.getCiMoney()
          + ",'"
          + printplanListDTO.getTemploanRate() + "','2')";
      Statement statement = conn.createStatement();
      statement.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 打印还款计划表
   * 
   * @author 郭婧平 2008-03-11 查询临时表FN_TEMP_TABLE中的数据 TEMP_COLUMN1 string 还款年月
   *         TEMP_COLUMN2 string 应还本金 TEMP_COLUMN3 number 应还利息 TEMP_COLUMN4
   *         number 应还本息合计 TEMP_COLUMN5 string 每月利率
   */
  public List queryFnTempTable(final String orderBy, final String order,
      final int start, final int pageSize) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.temp_column1,t.temp_column2,t.temp_column3,t.temp_column4,t.temp_column5  "
              + " from FN_TEMP_TABLE t where t.type=2 order by t.temp_column1 ";
          Query query = session.createSQLQuery(hql);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List temp_list = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            PrintplanListDTO printplanListDTO = new PrintplanListDTO();
            printplanListDTO.setLoanKouYearmonth(obj[0].toString());
            printplanListDTO.setShouldCorpus(new BigDecimal(obj[1].toString()));
            printplanListDTO
                .setShouldInterest(new BigDecimal(obj[2].toString()));
            printplanListDTO.setCiMoney(new BigDecimal(obj[3].toString()));
            printplanListDTO.setTemploanRate(obj[4].toString());
            temp_list.add(printplanListDTO);
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
   * 打印还款计划表
   * 
   * @author 郭婧平 2008-03-11 查询临时表FN_TEMP_TABLE中的所有数据--打印 TEMP_COLUMN1 string
   *         还款年月 TEMP_COLUMN2 string 应还本金 TEMP_COLUMN3 number 应还利息 TEMP_COLUMN4
   *         number 应还本息合计 TEMP_COLUMN5 string 每月利率
   */
  public List queryFnTempTableAll() {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.temp_column1,t.temp_column2,t.temp_column3,t.temp_column4,t.temp_column5  "
              + " from FN_TEMP_TABLE t where t.type=2 ";
          Query query = session.createSQLQuery(hql);
          List temp_list = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            PrintplanListDTO printplanListDTO = new PrintplanListDTO();
            printplanListDTO.setLoanKouYearmonth(obj[0].toString());
            printplanListDTO.setShouldCorpus(new BigDecimal(obj[1].toString()));
            printplanListDTO
                .setShouldInterest(new BigDecimal(obj[2].toString()));
            printplanListDTO.setCiMoney(new BigDecimal(obj[3].toString()));
            printplanListDTO.setTemploanRate(obj[4].toString());
            temp_list.add(printplanListDTO);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public String find(final String temp_contactid) {
    String monthpay = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(t.loan_kou_yearmonth) from pl201 t where t.should_corpus=t.real_corpus and t.real_interest=t.should_interest and t.contract_id=? ";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, temp_contactid);
            Object obj = null;
            obj = (Object) query.uniqueResult();
            if (obj != null) {
              return obj.toString();
            }
            String m = "";
            return m;
          }
        });

    return monthpay;
  }
  public String findMonthBetween(final String date_1,final String date_2) {
    String monthpay = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select months_between (to_date("+date_1+",'yyyymm'),to_date("+date_2+",'yyyymm')) from dual ";

            Query query = session.createSQLQuery(hql);
            Object obj = null;
            obj = (Object) query.uniqueResult();
            if (obj != null) {
              return obj.toString();
            }
            String m = "";
            return m;
          }
        });

    return monthpay;
  }
}
