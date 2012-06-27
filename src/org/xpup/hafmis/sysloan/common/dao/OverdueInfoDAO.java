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
import org.xpup.hafmis.common.util.CardMunChange;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.OverdueInfo;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTaDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.dto.OverdueInfoDTO;

/**
 * 逾期信息PL205
 * 
 * @author 李娟 2007-9-24
 */
public class OverdueInfoDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public OverdueInfo queryById(Serializable id) {
    Validate.notNull(id);
    return (OverdueInfo) getHibernateTemplate().get(OverdueInfo.class, id);
  }

  /**
   * 插入记录
   * 
   * @param overdueInfo
   * @return
   */
  public Serializable insert(OverdueInfo overdueInfo) {
    Serializable id = null;
    try {
      Validate.notNull(overdueInfo);
      id = getHibernateTemplate().save(overdueInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 统计查询-逾期查询 根据登录用户生成逾期数据 jj
   * 
   * @param username
   * @param bizDate
   * @param bizDay
   * @param bizYearMonth
   */
  public void executeCreateOverdueData(final String username,
      final String bizDate, final Integer bizDay, final Integer bizYearMonth,
      final String loanBankId) {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call CreateOverdueData(?,?,?,?,?)}");
      cs.setString(1, username);
      cs.setString(2, bizDate);
      cs.setInt(3, bizDay.intValue());
      cs.setInt(4, bizYearMonth.intValue());
      cs.setString(5, loanBankId);
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public void execUpdatePunishInst(final String bizDate) {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call createpunishinterest(?)}");
      cs.setString(1, bizDate);
      cs.execute();
      conn.close();
      if(cs != null)
        cs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public void deleteOverdueInfoByLoanBankId(final String loanBankId,
      final String bizDate) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete OverdueInfo overdueInfo where overdueInfo.loanBankId = ? and overdueInfo.oweDate = ? ";
          Query query = session.createQuery(sql);
          query.setString(0, loanBankId);
          query.setString(1, bizDate);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteOverdueInfoByContractId(final String contractId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete OverdueInfo overdueInfo where overdueInfo.contractId=? ";
          Query query = session.createQuery(sql);
          query.setString(0, contractId);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * hanl 根据合同编号查询逾期月数和月还款额
   * 
   * @param contactid
   * @return
   */
  public LoandeskaccqueryTaDTO queryOvermonthMonthpay(final String contactid) {
    LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = new LoandeskaccqueryTaDTO();
    try {
      loandeskaccqueryTaDTO = (LoandeskaccqueryTaDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.reserve_a,p.owe_month from pl205 p where p.owe_date=(select max(t.owe_date) from pl205 t where t.contract_id=?) and p.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, contactid);
              query.setParameter(1, contactid);
              LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = new LoandeskaccqueryTaDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loandeskaccqueryTaDTO.setMonthpay(obj[0].toString());
                } else {
                  loandeskaccqueryTaDTO.setMonthpay("0");
                }
                if (obj[1] != null) {
                  loandeskaccqueryTaDTO.setOwemonth(obj[1].toString());
                }
              }
              return loandeskaccqueryTaDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loandeskaccqueryTaDTO;
  }

  public List queryOverdueInfoContactId() {
    List contactIdList = null;
    try {
      contactIdList = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select overdueInfo.contractId from OverdueInfo overdueInfo ";
              Query query = session.createQuery(hql);
              return query.list();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return contactIdList;
  }

  public List queryOverdueInfoContactIds(final SecurityInfo securityInfo) {
    List contactIdList = null;
    try {
      contactIdList = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select overdueInfo.contractId,overdueInfo.id from OverdueInfo overdueInfo where overdueInfo.oweDate = ? and overdueInfo.loanBankId "
                  + securityInfo.getDkSecurityHqlSQL();
              Query query = session.createQuery(hql);
              query.setString(0, securityInfo.getUserInfo().getPlbizDate()
                  .substring(0, 6));
              return query.list();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return contactIdList;
  }

  public Integer queryOverdueInfoContactIds(final String contactId,
      final SecurityInfo securityInfo) {
    Integer pkId = null;
    try {
      pkId = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select overdueInfo.id from OverdueInfo overdueInfo where overdueInfo.oweDate = ? and overdueInfo.contractId = ?";
          Query query = session.createQuery(hql);
          query.setString(0, securityInfo.getUserInfo().getPlbizDate());
          query.setString(1, contactId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pkId;
  }

  public void insertOver_data_jj(String contractId, BigDecimal punish_interest) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = "insert into over_data (contract_id,punish_interest,over_id) "
          + " values('" + contractId + "','" + punish_interest + "','0')";
      st.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void executeUpdateOverdueData(String yearMonth) {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call updateoverdata (?)}");
      cs.setString(1, yearMonth);
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

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
              + "  and pl201.loan_kou_yearmonth <= '"
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
            criterion = " where ((shouldCorpus-realCorpus)>0 or (shouldInterest-realInterest)>0 or punishInterest>0) and "
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
  public List queryOverdueList(final String office, final String bankId,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanKouAcc, final String overdueMonthSt, final String overdueMonthEnd,
      final String agreement,final int start,
      final String orderBy, final String order, final int pageSize,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select p111.contract_id,"
              + " p110.borrower_name,"
              + " p110.org_name,"
              + " p111.loan_kou_acc,"
              + " p110.house_tel tel,"
              + " p110.home_mobile,"
              + " p121.pledge_addr,"
              + " p110.home_addr,"
              + " p111.loan_money,"
              + " p111.overplus_loan_money,"
              + " p113.name,"
              + " p113.org_name astorgname,"
              + " p113.home_mobile astmobile,"
              + " p111.loan_start_date," 
              + " sum(p201.should_corpus),"
              + " sum(p201.should_interest),"
              + " sum(p201.should_corpus) + sum(p201.should_interest),"
              + " to_char(add_months(to_date(min(p201.loan_kou_yearmonth), 'yyyyMM'), -1),'yyyyMMdd') mon,"
              + " sum(p201.reservea_c),"
              + " count(p201.id),"
              + " p111.loan_bank_id"
              + " from pl110 p110, pl111 p111, pl113 p113, pl201 p201, pl121 p121"
              + " where p110.contract_id = p111.contract_id"
              + " and p111.contract_id = p201.contract_id"
              + " and p113.contract_id(+) = p111.contract_id"
              + " and p111.contract_id = p121.contract_id(+)"
              + " and (p113.name is null or "
              + " (p113.relation = 1"
              + " and p113.status = 0))"
              + " and p201.should_corpus > p201.real_corpus"
              + " and to_date('"
              + securityInfo.getUserInfo().getPlbizDate()
              + "', 'yyyyMMdd') - repay_date(p201.loan_kou_yearmonth, p111.loan_repay_day) > 0"
              + " and p201.loan_bank_id " + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (office != null && !office.equals("")) {
            criterion += " p110.office = ? and ";
            parameters.add(office);
          }
          if (bankId != null && !bankId.equals("")) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(bankId);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%"+contractId+"%");
          }
          if (borrowerName != null && !borrowerName.trim().equals("")) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName.trim() + "%");
          }
          if (cardNum != null && !cardNum.trim().equals("")) {
            String tempCardNum = "";
            if (cardNum.trim().length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNum.trim());
            else if (cardNum.length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNum.trim());
            criterion += "(p110.card_num = ? or p110.card_num = ?) and ";
            parameters.add(cardNum.trim());
            parameters.add(tempCardNum);
          }
          if (loanKouAcc != null && !loanKouAcc.trim().equals("")) {
            criterion += " p111.loan_kou_acc like ? and ";
            parameters.add("%" + loanKouAcc.trim() + "%");
          }
          if (loanKouAcc != null && !loanKouAcc.trim().equals("")) {
            criterion += " p111.loan_kou_acc like ? and ";
            parameters.add("%" + loanKouAcc.trim() + "%");
          }
          String ob = orderBy;
          if (ob == null)
            ob = "p111.contract_id";
          String od = order;
          if (od == null)
            od = " ASC ";
          String groupBy = " group by p111.contract_id,"
              + " p110.borrower_name," + " p110.org_name,"
              + " p111.loan_kou_acc," + " p110.house_tel,"
              + " p110.home_mobile," + " p121.pledge_addr," + " p110.home_addr,"
              + " p111.loan_money," + " p111.overplus_loan_money,"
              + " p113.name," + " p113.org_name," + " p113.home_mobile,"
              + " p111.loan_start_date," + " p111.loan_bank_id";
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion + groupBy;
          if (overdueMonthSt != null && overdueMonthEnd != null) {
            sql += " having count(p111.contract_id) between ? and ?";
            parameters.add(overdueMonthSt);
            parameters.add(overdueMonthEnd);
          } else if (overdueMonthSt != null) {
            sql += " having count(p111.contract_id) >= ? ";
            parameters.add(overdueMonthSt);
          } else if (overdueMonthEnd != null) {
            sql += " having count(p111.contract_id) <= ? ";
            parameters.add(overdueMonthEnd);
          }
          sql += " order by " + ob + " " + od;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List temp_list = new ArrayList();
          Object obj[] = null;
          Iterator it = query.list().iterator();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            OverdueInfoDTO dto = new OverdueInfoDTO();
            dto.setContractId(obj[0].toString());
            dto.setBorrowerName(obj[1].toString());
            if (obj[2] != null)
              dto.setBorrowerOrgName(obj[2].toString());
            dto.setLoanKouAcc(obj[3].toString());
            if (obj[4] != null)
              dto.setBorrowerTel(obj[4].toString());
            if (obj[5] != null)
              dto.setBorrowerMobile(obj[5].toString());
            if (obj[6] != null)
              dto.setBorrowerOrgTel(obj[6].toString());
            if (obj[7] != null)
              dto.setHouseAddr(obj[7].toString());
            dto.setLoanMoney(new BigDecimal(obj[8].toString()));
            dto.setBalance(new BigDecimal(obj[9].toString()));
            if (obj[10] != null)
              dto.setAstBorrowerName(obj[10].toString());
            if (obj[11] != null)
              dto.setAstBorrowerOrgName(obj[11].toString());
            if (obj[12] != null)
              dto.setAstBorrowerMobile(obj[12].toString());
            dto.setLoanStartDate(obj[13].toString());
            dto.setCorpus(new BigDecimal(obj[14].toString()));
            dto.setInterest(new BigDecimal(obj[15].toString()));
            dto.setShouldPayMoney(new BigDecimal(obj[16].toString()));
            dto.setRepayMonth(obj[17].toString());
            dto.setPunishInterest(new BigDecimal(obj[18].toString()));
            dto.setOverdueMonths(obj[19].toString());
            dto.setLoanBank(obj[20].toString());
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
  public int queryOverdueCount(final String office, final String bankId,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanKouAcc, final String overdueMonthSt, final String overdueMonthEnd,
      final String agreement,
      final SecurityInfo securityInfo) {
    Integer count = null;
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select sum(count(distinct p111.contract_id))"
              + " from pl110 p110, pl111 p111, pl201 p201"
              + " where p110.contract_id = p111.contract_id"
              + " and p111.contract_id = p201.contract_id"
              + " and p201.should_corpus > p201.real_corpus "
              + " and to_date('"
              + securityInfo.getUserInfo().getPlbizDate()
              + "', 'yyyyMMdd') - repay_date(p201.loan_kou_yearmonth, p111.loan_repay_day) > 0"
              + " and p201.loan_bank_id " + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (office != null && !office.equals("")) {
            criterion += " p110.office = ? and ";
            parameters.add(office);
          }
          if (bankId != null && !bankId.equals("")) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(bankId);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%"+contractId+"%");
          }
          if (borrowerName != null && !borrowerName.trim().equals("")) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName.trim() + "%");
          }
          if (cardNum != null && !cardNum.trim().equals("")) {
            String tempCardNum = "";
            if (cardNum.trim().length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNum.trim());
            else if (cardNum.length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNum.trim());
            criterion += "(p110.card_num = ? or p110.card_num = ?) and ";
            parameters.add(cardNum.trim());
            parameters.add(tempCardNum);
          }
          if (loanKouAcc != null && !loanKouAcc.trim().equals("")) {
            criterion += " p111.loan_kou_acc like ? and ";
            parameters.add("%" + loanKouAcc.trim() + "%");
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion + " group by p111.contract_id";
          if (overdueMonthSt != null && overdueMonthEnd != null) {
            sql += " having count(p111.contract_id) between ? and ?";
            parameters.add(overdueMonthSt);
            parameters.add(overdueMonthEnd);
          } else if (overdueMonthSt != null) {
            sql += " having count(p111.contract_id) >= ? ";
            parameters.add(overdueMonthSt);
          } else if (overdueMonthEnd != null) {
            sql += " having count(p111.contract_id) <= ? ";
            parameters.add(overdueMonthEnd);
          }
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Object count = (Object) query.uniqueResult();
          if (count == null)
            return new Integer(0);
          else
            return Integer.valueOf(count.toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }
  public List queryOverdueInfoList(final String contractId, final int start,
      final int pageSize, final String orderBy, final String order,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select p111.contract_id,"
              + " p201.loan_kou_yearmonth,"
              + " p201.should_corpus,"
              + " p201.should_interest,"
              + " p201.should_corpus + p201.should_interest,"
              + " p201.reservea_c"
              + " from pl111 p111, pl201 p201"
              + " where p111.contract_id = p201.contract_id"
              + " and p111.contract_id = '"
              + contractId
              + "' and p201.should_corpus > p201.real_corpus "
              + " and to_date('"
              + securityInfo.getUserInfo().getPlbizDate()
              + "', 'yyyyMMdd') - repay_date(p201.loan_kou_yearmonth, p111.loan_repay_day) > 0";

          String ob = orderBy;
          if (ob == null)
            ob = "p111.contract_id";
          String od = order;
          if (od == null)
            od = " ASC ";
          sql += " order by " + ob + " " + od;
          Query query = session.createSQLQuery(sql);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List list = new ArrayList();
          Object obj[] = null;
          Iterator it = query.list().iterator();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            OverdueInfoDTO dto = new OverdueInfoDTO();
            dto.setContractId(obj[0].toString());
            dto.setOverdueMonths(obj[1].toString());
            dto.setCorpus(new BigDecimal(obj[2].toString()));
            dto.setInterest(new BigDecimal(obj[3].toString()));
            dto.setShouldPayMoney(new BigDecimal(obj[4].toString()));
            dto.setPunishInterest(new BigDecimal(obj[5].toString()));
            list.add(dto);
          }
          return list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public int queryOverdueInfoCount(final String contractId,
      final SecurityInfo securityInfo) {
    Integer count = null;
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(p111.contract_id)"
              + " from pl111 p111, pl201 p201"
              + " where p111.contract_id = p201.contract_id"
              + " and p111.contract_id = '"
              + contractId
              + "' and p201.should_corpus > p201.real_corpus "
              + " and to_date('"
              + securityInfo.getUserInfo().getPlbizDate()
              + "', 'yyyyMMdd') - repay_date(p201.loan_kou_yearmonth, p111.loan_repay_day) > 0";
          Query query = session.createSQLQuery(sql);
          return Integer.valueOf(query.uniqueResult().toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }
  public Object[] queryTotalInfoByCriterions(final String office, final String bankId,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanKouAcc, final String overdueMonthSt, final String overdueMonthEnd,
      final String agreement,
      final SecurityInfo securityInfo) {
    Object[] info = null;
    try {
      info = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select sum(sum(p201.should_corpus)),"
              + " sum(sum(p201.should_interest)),"
              + " sum(sum(p201.should_corpus) + sum(p201.should_interest))"
              + " from pl110 p110, pl111 p111, pl201 p201"
              + " where p110.contract_id = p111.contract_id"
              + " and p111.contract_id = p201.contract_id"
              + " and p201.should_corpus > p201.real_corpus "
              + " and to_date('"
              + securityInfo.getUserInfo().getPlbizDate()
              + "', 'yyyyMMdd') - repay_date(p201.loan_kou_yearmonth, p111.loan_repay_day) > 0"
              + " and p201.loan_bank_id " + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (office != null && !office.equals("")) {
            criterion += " p110.office = ? and ";
            parameters.add(office);
          }
          if (bankId != null && !bankId.equals("")) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(bankId);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%"+contractId+"%");
          }
          if (borrowerName != null && !borrowerName.trim().equals("")) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName.trim() + "%");
          }
          if (cardNum != null && !cardNum.trim().equals("")) {
            String tempCardNum = "";
            if (cardNum.trim().length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNum.trim());
            else if (cardNum.length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNum.trim());
            criterion += "(p110.card_num = ? or p110.card_num = ?) and ";
            parameters.add(cardNum.trim());
            parameters.add(tempCardNum);
          }
          if (loanKouAcc != null && !loanKouAcc.trim().equals("")) {
            criterion += " p111.loan_kou_acc like ? and ";
            parameters.add("%" + loanKouAcc.trim() + "%");
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion + " group by p111.contract_id";
          if (overdueMonthSt != null && overdueMonthEnd != null) {
            sql += " having count(p111.contract_id) between ? and ?";
            parameters.add(overdueMonthSt);
            parameters.add(overdueMonthEnd);
          } else if (overdueMonthSt != null) {
            sql += " having count(p111.contract_id) >= ? ";
            parameters.add(overdueMonthSt);
          } else if (overdueMonthEnd != null) {
            sql += " having count(p111.contract_id) <= ? ";
            parameters.add(overdueMonthEnd);
          }
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return info;
  }
  public int queryPl201_1(final String date) {
    Integer count = null;
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(t.id) from pl201_1 t where t.query_date = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, date);
          return Integer.valueOf(query.uniqueResult().toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }
  public void updatePl201_1(final String date) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = " update pl201_1 t set t.query_date = '" + date + "'";
      st.executeUpdate(sql);
      st.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 查询还款中的人数
   * @param date
   * @return
   */
  public int queryHKZCount(final String office, final String bankId,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanKouAcc, final SecurityInfo securityInfo) {
    Integer count = null;
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select count(p111.contract_id)"
              + " from pl110 p110, pl111 p111"
              + " where p110.contract_id = p111.contract_id"
              + " and p111.contract_st = 11"
              + " and P111.loan_bank_id " + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (office != null && !office.equals("")) {
            criterion += " p110.office = ? and ";
            parameters.add(office);
          }
          if (bankId != null && !bankId.equals("")) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(bankId);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%"+contractId+"%");
          }
          if (borrowerName != null && !borrowerName.trim().equals("")) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName.trim() + "%");
          }
          if (cardNum != null && !cardNum.trim().equals("")) {
            String tempCardNum = "";
            if (cardNum.trim().length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNum.trim());
            else if (cardNum.length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNum.trim());
            criterion += "(p110.card_num = ? or p110.card_num = ?) and ";
            parameters.add(cardNum.trim());
            parameters.add(tempCardNum);
          }
          if (loanKouAcc != null && !loanKouAcc.trim().equals("")) {
            criterion += " p111.loan_kou_acc like ? and ";
            parameters.add("%" + loanKouAcc.trim() + "%");
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion;
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
   * 查询还款中的贷款余额
   * @param date
   * @return
   */
  public BigDecimal queryHKZBalance(final String office, final String bankId,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanKouAcc, final SecurityInfo securityInfo) {
    BigDecimal balance = null;
    try {
      balance = (BigDecimal) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select sum(p111.overplus_loan_money)"
              + " from pl110 p110, pl111 p111"
              + " where p110.contract_id = p111.contract_id"
              + " and p111.contract_st = 11"
              + " and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (office != null && !office.equals("")) {
            criterion += " p110.office = ? and ";
            parameters.add(office);
          }
          if (bankId != null && !bankId.equals("")) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(bankId);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%"+contractId+"%");
          }
          if (borrowerName != null && !borrowerName.trim().equals("")) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName.trim() + "%");
          }
          if (cardNum != null && !cardNum.trim().equals("")) {
            String tempCardNum = "";
            if (cardNum.trim().length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNum.trim());
            else if (cardNum.length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNum.trim());
            criterion += "(p110.card_num = ? or p110.card_num = ?) and ";
            parameters.add(cardNum.trim());
            parameters.add(tempCardNum);
          }
          if (loanKouAcc != null && !loanKouAcc.trim().equals("")) {
            criterion += " p111.loan_kou_acc like ? and ";
            parameters.add("%" + loanKouAcc.trim() + "%");
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Object obj = query.uniqueResult();
          if(obj == null)
            return new BigDecimal(0.00);
          else 
            return new BigDecimal(obj.toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return balance;
  }
}
