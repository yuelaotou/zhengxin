package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.dto.CarryforwardTaDTO;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTaDTO;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTbFindDTO;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTbShowListDTO;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.dto.AssurepledgechgTaDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticeTbDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyTeListDTO;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractPrintDTO;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractTaDTO;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractTbDTO;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.dto.AdvancepayloanTaDTO;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.dto.AdvancepayloanTbDTO;
import org.xpup.hafmis.sysloan.loancallback.contractchange.dto.ContractchangeDTO;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTaDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.dto.LoanerlogoutTaDTO;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.dto.LoanerlogoutTbDTO;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto.CheckQueryPlFnDTO;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto.CheckQueryPlFnTBDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.dto.FiveLevelQueryDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTaDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTcDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryShowListDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryTotleDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.dto.YearLoanContrastDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.dto.YearloanDTO;

/**
 * 借款人账户表PL111
 * 
 * @author 李娟 2007-9-13
 */
public class BorrowerAccDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public BorrowerAcc queryById(Serializable id) {
    Validate.notNull(id);
    return (BorrowerAcc) getHibernateTemplate().get(BorrowerAcc.class, id);
  }

  /**
   * 插入记录
   * 
   * @param borrowerAcc
   * @return
   */
  public Serializable insert(BorrowerAcc borrowerAcc) {
    Serializable id = null;
    try {
      Validate.notNull(borrowerAcc);
      id = getHibernateTemplate().save(borrowerAcc);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 审核贷款 统计借款金额
   * 
   * @author 王野 2007-09-24
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param loanBankName
   * @param orgName
   * @param houseType
   * @param beginBizDate
   * @param endBizDate
   * @param contractStFind
   * @param isContractWrite
   * @param type
   * @return
   */
  public BigDecimal queryBorrowerListByCriterions(final String contractId,
      final String borrowerName, final String cardNum,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,
      final String contractStFind, final String isContractWrite,
      final String type, final SecurityInfo securityInfo) {
    BigDecimal loanTotleMoney = new BigDecimal(0.00);
    try {
      loanTotleMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select sum(p115.loan_money) loantotalmoney from pl110 p110,pl111 p111,pl114 p114,pl115 p115 "
                  + " where  p110.contract_id=p111.contract_id and p110.contract_id=p115.contract_id and p111.contract_id=p114.contract_id and p110.operator "
                  + securityInfo.getDkUserSecuritySQL();
              Vector parameters = new Vector();
              String criterion = "";

              if (contractId != null && !"".equals(contractId)) {
                criterion += " p110.contract_id = ? and ";
                parameters.add(contractId);
              }

              if (borrowerName != null && !"".equals(borrowerName)) {
                criterion += " p110.borrower_name = ? and ";
                parameters.add(borrowerName);
              }

              if (cardNum != null && !"".equals(cardNum)) {
                criterion += " p110.card_num = ? and ";
                parameters.add(cardNum);
              }

              if (loanBankName != null && !"".equals(loanBankName)) {
                criterion += " p111.loan_bank_id = ? and ";
                parameters.add(loanBankName);
              }

              if (orgName != null && !"".equals(orgName)) {
                criterion += " p110.org_name = ? and ";
                parameters.add(orgName);
              }

              if (houseType != null && !"".equals(houseType)) {
                criterion += " p114.house_type = ? and ";
                parameters.add(houseType);
              }

              if (beginBizDate != null && !"".equals(beginBizDate)) {
                criterion += " to_char(p110.op_time,'yyyymmdd') >= ? and ";
                parameters.add(beginBizDate);
              }

              if (endBizDate != null && !"".equals(endBizDate)) {
                criterion += " to_char(p110.op_time,'yyyymmdd') <= ? and ";
                parameters.add(endBizDate);
              }

              if (contractStFind != null && !"".equals(contractStFind)) {
                criterion += " p111.contract_st = ? and ";
                parameters.add(contractStFind);
              }

              if (isContractWrite != null && !"".equals(isContractWrite)) {
                criterion += " p111.is_contract_write = ? and ";
                parameters.add(isContractWrite);
              }

              if (type == null || "".equals(type)) {
                criterion += " p111.contract_st in (2) and ";
              }

              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;

              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanTotleMoney;
  }

  /**
   * yuqf 查询PL111字段，用于借款合同信息页面显示
   * 
   * @param id
   * @return
   */
  public EndorsecontractTaDTO queryBorrowerAccInfoYU(final String id,
      final SecurityInfo securityInfo) {
    EndorsecontractTaDTO endorsecontractTaDTO = null;
    try {
      endorsecontractTaDTO = (EndorsecontractTaDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              // TODO Auto-generated method stub

              String hql = "select t.loan_money,t.loan_time_limit,t.is_contract_write,t.contract_st,t.loan_bank_id,t.loan_kou_acc"
                  + " from pl111 t,pl110 t1 where t.contract_id=t1.contract_id and t.contract_id='"
                  + id + "'";
              Query query = session.createSQLQuery(hql);
              EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  endorsecontractTaDTO.setDebitMoney(obj[0].toString());// 借款金额
                }
                if (obj[1] != null) {
                  endorsecontractTaDTO.setTerm(obj[1].toString());// 借款期限
                }
                if (obj[2] != null) {
                  endorsecontractTaDTO.setIsWrite(obj[2].toString());// 是否签订合同
                }
                if (obj[3] != null) {
                  endorsecontractTaDTO.setContractSt(obj[3].toString());// 合同状态
                }
                if (obj[4] != null) {
                  endorsecontractTaDTO.setBeentruster(obj[4].toString());// 乙方
                }
                if (obj[5] != null) {
                  endorsecontractTaDTO.setLoanKouAcc(obj[5].toString());
                }
              }
              return endorsecontractTaDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return endorsecontractTaDTO;
  }

  public String getLoanBankId(final String contractId) {
    String bankid = "";
    try {
      bankid = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.loan_bank_id from pl111 t where t.contract_id = '"
              + contractId + "'";
          Query query = session.createSQLQuery(hql);
          return query.uniqueResult() != null ? query.uniqueResult().toString()
              : "";
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bankid;
  }

  /**
   * 根据贷款账号、合同状态在PL111中查询输入的贷款账号是否存在 回收办理 jj
   * 
   * @param loanKouAcc
   * @param contractSt
   * @param securityInfo
   * @return 合同编号
   */
  public String queryBorrowerAccByLoanKouAcc_LJ(final String loanKouAcc,
      final String contractSt, final SecurityInfo securityInfo) {
    String contractId = null;
    try {
      contractId = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select borrowerAcc.contractId from BorrowerAcc borrowerAcc ";
              Vector parameters = new Vector();
              String criterion = "";

              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "borrowerAcc.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }

              if (contractSt != null && !contractSt.equals("")) {
                criterion += "borrowerAcc.contractSt = ?  and ";
                parameters.add(contractSt);
              }

              if (criterion.length() != 0)
                criterion = "where borrowerAcc.loanBankId "
                    + securityInfo.getDkSecurityHqlSQL() + " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return contractId;
  }

  /**
   * 银行代扣导入 根据贷款账号查询还款日 jj
   * 
   * @param loanKouAcc
   * @param contractSt
   * @param securityInfo
   * @return
   */
  public BigDecimal queryLoanRepayDayByLoanKouAcc_LJ(final String loanKouAcc,
      final String contractSt, final SecurityInfo securityInfo) {
    BigDecimal contractId = new BigDecimal(0.00);
    try {
      contractId = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select borrowerAcc.loanRepayDay from BorrowerAcc borrowerAcc ";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "borrowerAcc.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractSt != null && !contractSt.equals("")) {
                criterion += "borrowerAcc.contractSt = ?  and ";
                parameters.add(contractSt);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return contractId;
  }

  /**
   * 根据合同编号查询借款人及账户信息 回收办理 jj
   * 
   * @param contractId
   * @return
   */
  public List queryBorrowerAccInfoByLoanKouAcc_LJ(final String contractId) {

    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select borrower.borrowerName,borrower.cardKind,borrower.cardNum,"
              + " borrowerAcc.contractId, borrowerAcc.overplusLoanMoney,borrowerAcc.loanMode,borrowerAcc.loanKouAcc, "
              + " borrowerAcc.loanRepayDay,borrowerAcc.ovaerLoanRepay,borrowerAcc.loanBankId,borrowerAcc.loanStartDate,borrowerAcc.loanTimeLimit,"
              + " borrower.office "
              + " from BorrowerAcc borrowerAcc, Borrower borrower ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "borrowerAcc.contractId = ?  and ";
            parameters.add(contractId);
          }

          if (criterion.length() != 0)
            criterion = " where borrowerAcc.contractId = borrower.contractId and "
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
            BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
            borrowerInfoDTO.setBorrowerName(obj[0].toString());
            borrowerInfoDTO.setCardKind(obj[1].toString());
            borrowerInfoDTO.setCardNum(obj[2].toString());
            borrowerInfoDTO.setContractId(obj[3].toString());
            if (obj[6] != null) {
              borrowerInfoDTO.setLoanKouAcc(obj[6].toString());
            }
            if (obj[5] != null) {
              borrowerInfoDTO.setLoanMode(obj[5].toString());
            }
            if (obj[4] != null) {
              borrowerInfoDTO.setOverplusLoanMoney(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[7] != null) {
              borrowerInfoDTO.setLoanRepayDay(obj[7].toString());
            }
            if (obj[8] != null) {
              borrowerInfoDTO.setOvaerLoanRepay(new BigDecimal(obj[8]
                  .toString()));
            }
            if (obj[9] != null) {
              borrowerInfoDTO.setLoanBankId(new Integer(obj[9].toString()));
            }
            if (obj[10] != null) {
              borrowerInfoDTO.setLoanStartDate(obj[10].toString());
            }
            if (obj[11] != null) {
              borrowerInfoDTO.setLoanTimeLimit(obj[11].toString());
            }
            if (obj[12] != null) {
              borrowerInfoDTO.setOfficeCode(obj[12].toString());
            }
            temp_list.add(borrowerInfoDTO);
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
   * hanl
   * 
   * @param contractid
   * @return
   */
  public boolean isCheckBorrowByContractid(final String contractid) {
    boolean flag = true;
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.contract_id from pl111 p where p.contract_st in('12','13') and p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);

            return query.uniqueResult();
          }
        });

    if (id == null || id.equals("")) {
      flag = false;
    }
    return flag;
  }

  /**
   * 抵押质押解除办理
   * 
   * @author 郭婧平 2007-9-21 根据PL111中的贷款账号查询PL110,PL111,PL115中所需的数据
   *         查询条件：loadKouAcc
   */
  public RelieveContractTaDTO queryRelieveContractTaInfo(
      final String loadKouAcc, final List loanbankList) throws Exception {
    RelieveContractTaDTO relieveContractTaDTO = null;
    try {
      relieveContractTaDTO = (RelieveContractTaDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select " + "distinct " + "pl111.loan_kou_acc,"
                  + "pl111.contract_id," + "pl110.borrower_name,"
                  + "pl110.card_kind," + "pl110.card_num,"
                  + "pl111.overplus_loan_money," + "pl111.no_back_money,"
                  + "pl111.ovaer_loan_repay," + "pl111.bail_balance,"
                  + "pl115.loan_mode "
                  + "from PL110 pl110,PL115 pl115,PL111 pl111 "
                  + "where pl111.contract_id=pl110.contract_id "
                  + "and pl111.contract_id=pl115.contract_id "
                  + "and pl111.contract_st=12 " + "and pl111.loan_kou_acc=?";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanbankList != null && loanbankList.size() > 0) {
                criterion += "and ( ";
                for (int i = 0; i < loanbankList.size(); i++) {
                  criterion += " pl111.loan_bank_id = ? or ";
                  parameters.add(loanbankList.get(i));
                }
                criterion = criterion.substring(0, criterion.lastIndexOf("or"));
                criterion += ") ";
              }
              Object[] obj = null;
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              query.setString(0, loadKouAcc);
              for (int i = 0, j = 1; i < parameters.size(); i++, j++) {
                query.setParameter(j, parameters.get(i));
              }
              obj = (Object[]) query.uniqueResult();
              RelieveContractTaDTO relieveConDTO = null;
              if (obj != null) {
                relieveConDTO = new RelieveContractTaDTO();
                if (obj[0] != null) {
                  relieveConDTO.setLoadKouAcc(obj[0].toString());
                }
                relieveConDTO.setContractId(obj[1].toString());
                relieveConDTO.setBorrowerName(obj[2].toString());
                relieveConDTO.setCardKind(obj[3].toString());
                relieveConDTO.setCardNum(obj[4].toString());
                if (obj[5] != null) {
                  relieveConDTO.setOverplusLoanMoney(new BigDecimal(obj[5]
                      .toString()));
                }
                if (obj[6] != null) {
                  relieveConDTO
                      .setNoBackMoney(new BigDecimal(obj[6].toString()));
                }
                if (obj[7] != null) {
                  relieveConDTO.setOvaerLoanRepay(new BigDecimal(obj[7]
                      .toString()));
                }
                if (obj[8] != null) {
                  relieveConDTO
                      .setBailBalance(new BigDecimal(obj[8].toString()));
                }
                relieveConDTO.setLoanMode(obj[9].toString());
              }
              return relieveConDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return relieveContractTaDTO;
  }

  /**
   * 抵押质押解除办理
   * 
   * @author 郭婧平 2007-9-27 查询pl121表中符合该合同编号下的status为2的数据 查询条件：contractId
   */
  public int findRelieveContractByContractId(final String contractId) {
    List list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl121.id,pl122.id from PL121 pl121,PL122 pl122 "
                + "where (pl121.contract_id=? and pl121.status='2') "
                + "or (pl122.contract_id=? and pl122.status='2') ";
            Query query = session.createSQLQuery(hql);
            query.setString(0, contractId);
            query.setString(1, contractId);
            return query.list();
          }
        });
    return list.size();
  }

  /**
   * 抵押质押解除办理
   * 
   * @author 郭婧平 2007-9-21 根据PL121中的合同编号修改PL121中status为0的改为2 查询条件：contractId
   */
  public void updatePledgeContractStatus(final String contractId)
      throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update PledgeContract pledgeContract set pledgeContract.status='2' "
              + "where pledgeContract.status='0' "
              + "and pledgeContract.contractId=? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 抵押质押解除办理
   * 
   * @author 郭婧平 2007-9-22 根据PL121中的合同编号修改PL121中status为2的改为0 查询条件：contractId
   */
  public void updatePledgeContractStatusTb(final String contractId)
      throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update PledgeContract pledgeContract set pledgeContract.status='0' "
              + "where pledgeContract.status='2' "
              + "and pledgeContract.contractId=? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 抵押质押解除办理
   * 
   * @author 郭婧平 2007-9-21 根据PL122中的合同编号修改PL122中status为0的改为2 查询条件：contractId
   */
  public void updateImpawnContractStatus(final String contractId)
      throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update ImpawnContract impawnContract set impawnContract.status='2' "
              + "where impawnContract.status='0' "
              + "and impawnContract.contractId=? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 抵押质押解除维护
   * 
   * @author 郭婧平 2007-9-22 根据PL122中的合同编号修改PL122中status为2的改为0 查询条件：contractId
   */
  public void updateImpawnContractStatusTb(final String contractId)
      throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update ImpawnContract impawnContract set impawnContract.status='0' "
              + "where impawnContract.status='2' "
              + "and impawnContract.contractId=? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 抵押质押解除维护
   * 
   * @author 郭婧平 2007-9-22 根据查询条件查出pl111中贷款账号，合同编号，pl110中借款人姓名，证件号码
   *         查询条件：pl111.loanKouAcc,pl111.contractId,pl110.borrowerName,pl110.cardNum,pl111.loanBankId,pl121.status=2或pl122.status=2
   */
  public List queryRelieveContractTbList(final String loanKouAcc,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanBankId, final String orderBy, final String order,
      final int start, final int pageSize, final int page,
      final List loanbankList) throws Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select contractid, loankouacc, borrowername, cardnum, loanbankid "
              + "from (select distinct pl111.contract_id   as contractid,"
              + "pl111.loan_kou_acc  as loankouacc,"
              + "pl110.borrower_name as borrowername,"
              + "pl110.card_num      as cardnum,"
              + "pl111.loan_bank_id  as loanbankid "
              + "from PL121 pl121, PL111 pl111, PL110 pl110 "
              + "where pl111.contract_id = pl110.contract_id "
              + "and pl121.contract_id = pl111.contract_id "
              + "and pl121.status = '2' "
              + " union "
              + "select distinct pl111.contract_id   as contractid,"
              + "pl111.loan_kou_acc  as loankouacc,"
              + "pl110.borrower_name as borrowername,"
              + "pl110.card_num      as cardnum,"
              + "pl111.loan_bank_id  as loanbankid "
              + "from PL122 pl122, PL111 pl111, PL110 pl110 "
              + "where pl111.contract_id = pl110.contract_id "
              + "and pl122.contract_id = pl111.contract_id "
              + "and pl122.status = '2') res ";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += " res.loankouacc=? and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " res.contractid=? and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " res.borrowername=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " res.cardnum=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " res.loanbankid=? and ";
            parameters.add(loanBankId);
          }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " res.loanbankid = ? or ";
              parameters.add(loanbankList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " res.contractid ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          List queryList = query.list();

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }

          Iterator it = queryList.iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              RelieveContractTbDTO relieveContractTbDTO = new RelieveContractTbDTO();
              if (obj[0] != null) {
                relieveContractTbDTO.setContractId(obj[0].toString());
              }
              relieveContractTbDTO.setLoanKouAcc(obj[1].toString());
              relieveContractTbDTO.setBorrowerName(obj[2].toString());
              relieveContractTbDTO.setCardNum(obj[3].toString());
              temp_list.add(relieveContractTbDTO);
            }
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
   * 抵押质押解除维护
   * 
   * @author 郭婧平 2007-9-22 根据查询条件查出所有的pl111中贷款账号，合同编号，pl110中借款人姓名，证件号码
   *         为求页面数据条数和打印用
   *         查询条件：pl111.loanKouAcc,pl111.contractId,pl110.borrowerName,pl110.cardNum,pl111.loanBankId,pl121.status=2或pl122.status=2
   */
  public List queryRelieveContractTbListCount(final String loanKouAcc,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanBankId, final List loanbankList) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select contractid, loankouacc, borrowername, cardnum, loanbankid "
              + "from (select distinct pl111.contract_id   as contractid,"
              + "pl111.loan_kou_acc  as loankouacc,"
              + "pl110.borrower_name as borrowername,"
              + "pl110.card_num      as cardnum,"
              + "pl111.loan_bank_id  as loanbankid "
              + "from PL121 pl121, PL111 pl111, PL110 pl110 "
              + "where pl111.contract_id = pl110.contract_id "
              + "and pl121.contract_id = pl111.contract_id "
              + "and pl121.status = '2' "
              + " union "
              + "select distinct pl111.contract_id   as contractid,"
              + "pl111.loan_kou_acc  as loankouacc,"
              + "pl110.borrower_name as borrowername,"
              + "pl110.card_num      as cardnum,"
              + "pl111.loan_bank_id  as loanbankid "
              + "from PL122 pl122, PL111 pl111, PL110 pl110 "
              + "where pl111.contract_id = pl110.contract_id "
              + "and pl122.contract_id = pl111.contract_id "
              + "and pl122.status = '2') res ";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += " res.loankouacc=? and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " res.contractid=? and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " res.borrowername=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " res.cardnum=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " res.loanbankid=? and ";
            parameters.add(loanBankId);
          }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " res.loanbankid = ? or ";
              parameters.add(loanbankList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              RelieveContractTbDTO relieveContractTbDTO = new RelieveContractTbDTO();
              if (obj[0] != null) {
                relieveContractTbDTO.setContractId(obj[0].toString());
              }
              relieveContractTbDTO.setLoanKouAcc(obj[1].toString());
              relieveContractTbDTO.setBorrowerName(obj[2].toString());
              relieveContractTbDTO.setCardNum(obj[3].toString());
              temp_list.add(relieveContractTbDTO);
            }
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
   * 抵押质押解除维护--打印
   * 
   * @author 郭婧平 2007-9-24 根据PL111中的合同编号查询PL110,PL111,PL115中所需的数据
   *         查询条件：contractId
   */
  public RelieveContractPrintDTO queryRelieveContractTbPrintInfo(
      final String contractId) throws Exception {
    RelieveContractPrintDTO relieveContractPrintDTO = null;
    try {
      relieveContractPrintDTO = (RelieveContractPrintDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select pl111.loan_kou_acc," + "pl111.contract_id,"
                  + "pl110.borrower_name," + "pl110.card_kind,"
                  + "pl110.card_num," + "pl111.overplus_loan_money,"
                  + "pl111.no_back_money," + "pl111.ovaer_loan_repay,"
                  + "pl111.bail_balance," + "pl115.loan_mode "
                  + "from PL110 pl110,PL115 pl115,PL111 pl111 "
                  + "where pl111.contract_id=pl110.contract_id "
                  + "and pl111.contract_id=pl115.contract_id "
                  + "and pl111.contract_id=?";
              Object[] obj = null;
              Query query = session.createSQLQuery(hql);
              query.setString(0, contractId);
              obj = (Object[]) query.uniqueResult();
              RelieveContractPrintDTO relieveConDTO = null;
              if (obj != null) {
                relieveConDTO = new RelieveContractPrintDTO();
                if (obj[0] != null) {
                  relieveConDTO.setLoadKouAcc(obj[0].toString());
                }
                relieveConDTO.setContractId(obj[1].toString());
                relieveConDTO.setBorrowerName(obj[2].toString());
                relieveConDTO.setCardKind(obj[3].toString());
                relieveConDTO.setCardNum(obj[4].toString());
                if (obj[5] != null) {
                  relieveConDTO.setOverplusLoanMoney(new BigDecimal(obj[5]
                      .toString()).setScale(2));
                }
                if (obj[6] != null) {
                  relieveConDTO
                      .setNoBackMoney(new BigDecimal(obj[6].toString())
                          .setScale(2));
                }
                if (obj[7] != null) {
                  relieveConDTO.setOvaerLoanRepay(new BigDecimal(obj[7]
                      .toString()).setScale(2));
                }
                if (obj[8] != null) {
                  relieveConDTO
                      .setBailBalance(new BigDecimal(obj[8].toString())
                          .setScale(2));
                }
                relieveConDTO.setLoanMode(obj[9].toString());
              }
              return relieveConDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return relieveContractPrintDTO;
  }

  /**
   * author wsh 查询贷款帐号是否存在 贷款户注销
   * 
   * @param newLoanKouAcc
   * @return
   */
  public Integer findBorrowerAccByLoanKouAcc_wsh(final String loanKouAcc,
      final List loanBankList) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.loan_kou_acc= ?  and a.contract_st in(7,8,11,12) ";
            Vector parameters = new Vector();
            String criterion = "";
            if (loanKouAcc != null) {
              parameters.add(loanKouAcc);
            }
            if (loanBankList != null && loanBankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanBankList.size(); i++) {
                criterion += " a.loan_bank_id = ? or ";
                parameters.add(loanBankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }

  /**
   * 挂账办理
   * 
   * @author 郭婧平 2007-9-25 根据PL111中的贷款账号查询PL110,PL111中所需的数据 查询条件：loadKouAcc
   */
  public OverPayTaDTO queryOrgPayTaInfo(final String loadkouacc,
      final List loanbankList) throws Exception {
    OverPayTaDTO overPayTaDTO = null;
    try {
      overPayTaDTO = (OverPayTaDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select " + "distinct " + "pl111.contract_id,"
                  + "pl110.borrower_name," + "pl110.card_kind,"
                  + "pl110.card_num," + "pl111.loan_mode,"
                  + "pl111.ovaer_loan_repay," + "pl111.loan_bank_id,"
                  + "pl110.office " + "from PL110 pl110,PL111 pl111 "
                  + "where pl111.contract_id=pl110.contract_id "
                  + "and pl111.loan_kou_acc=?";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanbankList != null && loanbankList.size() > 0) {
                criterion += "and ( ";
                for (int i = 0; i < loanbankList.size(); i++) {
                  criterion += " pl111.loan_bank_id = ? or ";
                  parameters.add(loanbankList.get(i));
                }
                criterion = criterion.substring(0, criterion.lastIndexOf("or"));
                criterion += ") ";
              }
              Object[] obj = null;
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              query.setString(0, loadkouacc);
              for (int i = 0, j = 1; i < parameters.size(); i++, j++) {
                query.setParameter(j, parameters.get(i));
              }
              obj = (Object[]) query.uniqueResult();
              OverPayTaDTO dto = null;
              if (obj != null) {
                dto = new OverPayTaDTO();
                dto.setContractId(obj[0].toString());
                dto.setBorrowerName(obj[1].toString());
                dto.setCardKind(obj[2].toString());
                dto.setCardNum(obj[3].toString());
                if (obj[4] != null) {
                  dto.setLoanMode(obj[4].toString());
                }
                if (obj[5] != null) {
                  dto.setOvaerLoanRepay(new BigDecimal(obj[5].toString()));
                }
                if (obj[6] != null) {
                  dto.setLoanBankId(obj[6].toString());
                }
                dto.setOfficecode(obj[7].toString());
              }
              return dto;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return overPayTaDTO;
  }

  /**
   * 挂账办理
   * 
   * @author 郭婧平 2007-9-26
   *         根据PL203中的贷款账号查询符合pl202.biz_type为（2,3,4,5,6,7,8,9,12,13）和pl202.biz_st<6的数据
   *         查询条件：loadKouAcc
   */
  public int findBorrowerAccByLoanKouAcc(final String loankouacc) {
    List list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl202.flow_head_id from PL203 pl203,PL202 pl202 "
                + "where pl203.loan_kou_acc=? "
                + "and pl203.flow_head_id=pl202.flow_head_id "
                + "and pl202.biz_type in('2','3','4','5','6','7','8','9','12','13') "
                + "and pl202.biz_st<6 ";
            Query query = session.createSQLQuery(hql);
            query.setString(0, loankouacc);
            return query.list();
          }
        });
    return list.size();
  }

  /**
   * 挂账维护
   * 
   * @author 郭婧平 2007-9-27 根据条件查询列表
   *         查询条件：pl203.loankouacc,pl203.contractId,pl110.borrowerName,pl110.cardNum,pl202.docNum,pl202.loanBankId,pl202.bizSt
   */
  public List queryOverPayTbList(final OverPayTbFindDTO overPayTbFindDTO,
      final List loanbankList, final String type, final String orderBy,
      final String order, final int start, final int pageSize, final int page)
      throws Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pl203.loan_kou_acc," + "pl202.doc_num,"
              + "pl111.contract_id," + "pl110.borrower_name,"
              + "pl110.card_num," + "pl202.occur_money," + "pl202.biz_st,"
              + "pl202.flow_head_id "
              + "from PL110 pl110,PL111 pl111,PL202 pl202,PL203 pl203 "
              + "where pl111.contract_id=pl110.contract_id "
              + "and pl203.contract_id=pl111.contract_id "
              + "and pl203.flow_head_id=pl202.flow_head_id "
              + "and pl202.biz_type=13 ";
          Vector parameters = new Vector();
          String criterion = "";

          String loankouacc = overPayTbFindDTO.getLoankouacc().trim();
          if (loankouacc != null && !loankouacc.equals("")) {
            criterion += " pl203.loan_kou_acc=? and ";
            parameters.add(loankouacc);
          }
          String contractId = overPayTbFindDTO.getContractId().trim();
          if (contractId != null && !contractId.equals("")) {
            criterion += " pl111.contract_id=? and ";
            parameters.add(contractId);
          }
          String borrowerName = overPayTbFindDTO.getBorrowerName().trim();
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " pl110.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          String cardNum = overPayTbFindDTO.getCardNum().trim();
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " pl110.card_num=? and ";
            parameters.add(cardNum);
          }
          String docNum = overPayTbFindDTO.getDocNum().trim();
          if (docNum != null && !docNum.equals("")) {
            criterion += " pl202.doc_num=? and ";
            parameters.add(docNum);
          }
          String loanBankId = overPayTbFindDTO.getLoanBankId().trim();
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " pl202.loan_bank_id = ? and ";
            parameters.add(loanBankId);
          } else {
            if (loanbankList != null && loanbankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanbankList.size(); i++) {
                criterion += " pl202.loan_bank_id = ? or ";
                parameters.add(loanbankList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
          }
          String bizSt = overPayTbFindDTO.getBizSt().trim();
          if (bizSt != null && !bizSt.equals("")) {
            criterion += " pl202.biz_st=? and ";
            parameters.add(bizSt);
          } else {
            if (type != null && !type.equals("")) {
              criterion += " pl202.biz_st in (4,5,6) and ";
            } else {
              criterion += " pl202.biz_st=4 and ";
            }
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " pl202.flow_head_id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }

          Iterator it = queryList.iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              OverPayTbShowListDTO overPayTbShowListDTO = new OverPayTbShowListDTO();
              if (obj[0] != null) {
                overPayTbShowListDTO.setLoankouacc(obj[0].toString());
              }
              if (obj[1] != null) {
                overPayTbShowListDTO.setDocNum(obj[1].toString());
              }
              overPayTbShowListDTO.setContractId(obj[2].toString());
              overPayTbShowListDTO.setBorrowerName(obj[3].toString());
              overPayTbShowListDTO.setCardNum(obj[4].toString());
              if (obj[5] != null) {
                overPayTbShowListDTO.setOccurMoney(new BigDecimal(obj[5]
                    .toString()));
              }
              if (obj[6] != null) {
                overPayTbShowListDTO.setBizSt(obj[6].toString());
              }
              overPayTbShowListDTO.setFlowHeadId(obj[7].toString());
              temp_list.add(overPayTbShowListDTO);
            }
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
   * 挂账维护--count
   * 
   * @author 郭婧平 2007-9-27 根据条件查询列表
   *         查询条件：pl203.loankouacc,pl203.contractId,pl110.borrowerName,pl110.cardNum,pl202.docNum,pl202.loanBankId,pl202.bizSt
   */
  public List queryOverPayTbListCount(final OverPayTbFindDTO overPayTbFindDTO,
      final List loanbankList, final String type) throws Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pl111.contract_id," + "pl202.occur_money "
              + "from PL110 pl110,PL111 pl111,PL202 pl202,PL203 pl203 "
              + "where pl111.contract_id=pl110.contract_id "
              + "and pl203.contract_id=pl111.contract_id "
              + "and pl203.flow_head_id=pl202.flow_head_id "
              + "and pl202.biz_type=13 ";
          Vector parameters = new Vector();
          String criterion = "";

          String loankouacc = overPayTbFindDTO.getLoankouacc().trim();
          if (loankouacc != null && !loankouacc.equals("")) {
            criterion += " pl203.loan_kou_acc=? and ";
            parameters.add(loankouacc);
          }
          String contractId = overPayTbFindDTO.getContractId().trim();
          if (contractId != null && !contractId.equals("")) {
            criterion += " pl111.contract_id=? and ";
            parameters.add(contractId);
          }
          String borrowerName = overPayTbFindDTO.getBorrowerName().trim();
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " pl110.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          String cardNum = overPayTbFindDTO.getCardNum().trim();
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " pl110.card_num=? and ";
            parameters.add(cardNum);
          }
          String docNum = overPayTbFindDTO.getDocNum().trim();
          if (docNum != null && !docNum.equals("")) {
            criterion += " pl202.doc_num=? and ";
            parameters.add(docNum);
          }
          String loanBankId = overPayTbFindDTO.getLoanBankId().trim();
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " pl202.loan_bank_id = ? and ";
            parameters.add(loanBankId);
          } else {
            if (loanbankList != null && loanbankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanbankList.size(); i++) {
                criterion += " pl202.loan_bank_id = ? or ";
                parameters.add(loanbankList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
          }
          String bizSt = overPayTbFindDTO.getBizSt().trim();
          if (bizSt != null && !bizSt.equals("")) {
            criterion += " pl202.biz_st=? and ";
            parameters.add(bizSt);
          } else {
            if (type != null && !type.equals("")) {
              criterion += " pl202.biz_st in (4,5,6) and ";
            } else {
              criterion += " pl202.biz_st=4 and ";
            }
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              OverPayTbShowListDTO overPayTbShowListDTO = new OverPayTbShowListDTO();
              overPayTbShowListDTO.setContractId(obj[0].toString());
              if (obj[1] != null) {
                overPayTbShowListDTO.setOccurMoney(new BigDecimal(obj[1]
                    .toString()));
              }
              temp_list.add(overPayTbShowListDTO);
            }
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
   * hanl 根据合同号，查询是否有处于1.5.6这三种状态的记录
   * 
   * @param contractid
   * @return
   */
  public String findContractidByContractid(final String contractid) {

    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.contract_id from pl111 p where p.contract_st in('1','5','6') and p.contract_id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);
            return query.uniqueResult();
          }
        });

    return id;
  }

  /**
   * 根据合同查询借款人，辅助借款人，合同信息，等等图片路径
   * 
   * @author yangg
   * @param contractid
   * @return List
   */
  public List queryPhotoURLByContractID(final String contractId) {

    List photoURLList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql;
            sql = "select p13.photo_url from pl113 p13 where p13.contract_id = ?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, contractId);
            String photoUrl = query.uniqueResult() == null ? "" : query
                .uniqueResult().toString();
            List slist = new ArrayList();
            slist.add(photoUrl);
            // sql = "select p15.photo_url from pl115 p15 where p15.contract_id
            // = ?";
            // query = session.createSQLQuery(sql);
            // query.setParameter(0, contractId);
            // photoUrl = query.uniqueResult() == null ? "" :
            // query.uniqueResult()
            // .toString();
            slist.add(photoUrl);
            sql = "select p14.photo_url from pl114 p14 where p14.contract_id = ?";
            query = session.createSQLQuery(sql);
            query.setParameter(0, contractId);
            photoUrl = query.uniqueResult() == null ? "" : query.uniqueResult()
                .toString();
            slist.add(photoUrl);
            sql = "select p20.photo_url2 from pl120 p20 where p20.contract_id = ?";
            query = session.createSQLQuery(sql);
            query.setParameter(0, contractId);
            photoUrl = query.uniqueResult() == null ? "" : query.uniqueResult()
                .toString();
            slist.add(photoUrl);
            sql = "select p15.photo_url from pl115 p15 where p15.contract_id = ?";
            query = session.createSQLQuery(sql);
            query.setParameter(0, contractId);
            photoUrl = query.uniqueResult() == null ? "" : query.uniqueResult()
                .toString();
            slist.add(photoUrl);
            sql = "select p10.photo_url from pl110 p10 where p10.contract_id = ?";
            query = session.createSQLQuery(sql);
            query.setParameter(0, contractId);
            photoUrl = query.uniqueResult() == null ? "" : query.uniqueResult()
                .toString();
            slist.add(photoUrl);
            return slist;
          }
        });
    return photoURLList;
  }

  /**
   * 贷款户注销
   * 
   * @author wsh 2007-9-28 根据PL111中的贷款账号查询PL110,PL111中所需的数据 查询条件：loadKouAcc
   */
  public LoanerlogoutTaDTO queryLoanerlogoutTaInfo_wsh(final String loanKouAcc,
      final List loanBankList) throws Exception {
    LoanerlogoutTaDTO loanerlogoutTaDTO = null;
    try {
      loanerlogoutTaDTO = (LoanerlogoutTaDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select b.loan_kou_acc loankouacc,"
                  + "b.contract_id   contractid,"
                  + "a.borrower_name borrowername,"
                  + "a.card_kind     cardkind," + "a.card_num cardnum,"
                  + "b.overplus_loan_money overplusloanmoney,"
                  + "b.no_back_money nobackmoney,"
                  + "b.ovaer_loan_repay ovaerloanrepay,"
                  + "b.bail_balance bailbalance," + "b.loan_mode loanmode "
                  + "from pl110 a, pl111 b "
                  + "where a.contract_id = b.contract_id "
                  + "and b.loan_kou_acc = ? ";
              Object[] obj = null;
              Vector parameters = new Vector();
              String criterion = "";
              if (loanKouAcc != null) {
                parameters.add(loanKouAcc);
              }
              if (loanBankList != null && loanBankList.size() > 0) {
                criterion += "  and( ";
                for (int i = 0; i < loanBankList.size(); i++) {
                  criterion += " b.loan_bank_id = ? or ";
                  parameters.add(loanBankList.get(i).toString());
                }
                criterion = criterion.substring(0, criterion.lastIndexOf("or"));
                criterion += ")";
              }
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              obj = (Object[]) query.uniqueResult();
              LoanerlogoutTaDTO tempoanerlogoutTaDTO = null;
              if (obj != null) {
                tempoanerlogoutTaDTO = new LoanerlogoutTaDTO();
                if (obj[0] != null) {
                  tempoanerlogoutTaDTO.setLoadKouAcc(obj[0].toString());
                }
                tempoanerlogoutTaDTO.setContractId(obj[1].toString());
                tempoanerlogoutTaDTO.setBorrowerName(obj[2].toString());
                tempoanerlogoutTaDTO.setCardKind(obj[3].toString());
                tempoanerlogoutTaDTO.setCardNum(obj[4].toString());
                if (obj[5] != null) {
                  tempoanerlogoutTaDTO.setOverplusLoanMoney(new BigDecimal(
                      obj[5].toString()));
                }
                if (obj[6] != null) {
                  tempoanerlogoutTaDTO.setNoBackMoney(new BigDecimal(obj[6]
                      .toString()));
                }
                if (obj[7] != null) {
                  tempoanerlogoutTaDTO.setOvaerLoanRepay(new BigDecimal(obj[7]
                      .toString()));
                }
                if (obj[8] != null) {
                  tempoanerlogoutTaDTO.setBailBalance(new BigDecimal(obj[8]
                      .toString()));
                }
                tempoanerlogoutTaDTO.setLoanMode(obj[9].toString());
              }
              return tempoanerlogoutTaDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanerlogoutTaDTO;
  }

  /**
   * 贷款户注销
   * 
   * @author wsh 2007-9-28
   *         根据PL111中的合同编号修改PL111中OLD_CONTRACT_ST=CONTRACT_ST,CONTRACT_ST=13
   *         查询条件：contractId
   */
  public void updateBorrowerAccContractStatus(final String contractId)
      throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update BorrowerAcc borrowerAcc "
              + "set borrowerAcc.oldContractSt = borrowerAcc.contractSt,"
              + "borrowerAcc.contractSt = 13 "
              + "where borrowerAcc.contractId = ? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 贷款户注销
   * 
   * @author wsh 2007-9-29 根据查询条件查出pl111中贷款账号，合同编号，pl110中借款人姓名，证件号码
   *         查询条件：pl111.loanKouAcc,pl111.contractId,pl110.borrowerName,pl110.cardNum,pl111.loanBankId,pl111.contract_st='13'
   */
  public List queryLoanerlogoutTbList(final String loanKouAcc,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanBankId, final String orderBy, final String order,
      final int start, final int pageSize, final List loanbankList)
      throws Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct(a.contract_id),"
              + "a.loan_kou_acc loankouacc," + "b.borrower_name borrowername,"
              + "b.card_num  cardnum " + "from pl111 a,pl110 b "
              + "where a.contract_id=b.contract_id " + "and a.contract_st='13'";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += " a.loan_kou_acc=? and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " a.contract_id=? and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " b.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " b.card_num=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " a.loan_bank_id=? and ";
            parameters.add(loanBankId);
          }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " a.loan_bank_id = ? or ";
              parameters.add(loanbankList.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " a.contract_id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              LoanerlogoutTbDTO loanerlogoutTbDTO = new LoanerlogoutTbDTO();
              if (obj[0] != null) {
                loanerlogoutTbDTO.setContractId(obj[0].toString());
              }
              loanerlogoutTbDTO.setLoanKouAcc(obj[1].toString());
              loanerlogoutTbDTO.setBorrowerName(obj[2].toString());
              loanerlogoutTbDTO.setCardNum(obj[3].toString());
              temp_list.add(loanerlogoutTbDTO);
            }
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
   * 贷款户注销
   * 
   * @author wsh 2007-9-29 根据查询条件查出所有的pl111中贷款账号，合同编号，pl110中借款人姓名，证件号码
   *         为求页面数据条数和打印用
   *         查询条件：pl111.loanKouAcc,pl111.contractId,pl110.borrowerName,pl110.cardNum,pl111.loanBankId,pl111.contract_st='13'
   */
  public List queryLoanerlogoutTbListCount(final String loanKouAcc,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanBankId, final List loanbankList) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct(a.contract_id),"
              + "a.loan_kou_acc loankouacc," + "b.borrower_name borrowername,"
              + "b.card_num cardnum " + "from pl111 a,pl110 b "
              + "where a.contract_id=b.contract_id "
              + "and a.contract_st='13' ";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += " a.loan_kou_acc=? and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " a.contract_id=? and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " b.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " b.card_num=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " a.loan_bank_id=? and ";
            parameters.add(loanBankId);
          }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " a.loan_bank_id = ? or ";
              parameters.add(loanbankList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              LoanerlogoutTbDTO loanerlogoutTbDTO = new LoanerlogoutTbDTO();
              if (obj[0] != null) {
                loanerlogoutTbDTO.setContractId(obj[0].toString());
              }
              loanerlogoutTbDTO.setLoanKouAcc(obj[1].toString());
              loanerlogoutTbDTO.setBorrowerName(obj[2].toString());
              loanerlogoutTbDTO.setCardNum(obj[3].toString());
              temp_list.add(loanerlogoutTbDTO);
            }
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
   * 贷款户删除
   * 
   * @author yqf 2007-10-16 根据查询条件查出pl111中贷款账号，合同编号，pl110中借款人姓名，证件号码
   *         查询条件：pl111.loanKouAcc,pl111.contractId,pl110.borrowerName,pl110.cardNum,pl111.loanBankId,pl111.contract_st='13'
   */
  public List queryloandeleteList(final String loanKouAcc,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanBankId, final String orderBy, final String order,
      final int start, final int pageSize, final List loanbankList,
      final int page) throws Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct(a.contract_id),"
              + "a.loan_kou_acc loankouacc,"
              + "b.borrower_name borrowername,"
              + "b.card_num  cardnum "
              + "from pl111 a,pl110 b "
              + "where a.contract_id=b.contract_id and a.contract_st not in('10','11','12','13') ";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += " a.loan_kou_acc=? and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " a.contract_id=? and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " b.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " b.card_num=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " a.loan_bank_id=? and ";
            parameters.add(loanBankId);
          }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " a.loan_bank_id = ? or ";
              parameters.add(loanbankList.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " a.contract_id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);

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
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              LoanerlogoutTbDTO loanerlogoutTbDTO = new LoanerlogoutTbDTO();
              if (obj[0] != null) {
                loanerlogoutTbDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                loanerlogoutTbDTO.setLoanKouAcc(obj[1].toString());
              }
              if (obj[2] != null) {
                loanerlogoutTbDTO.setBorrowerName(obj[2].toString());
              }
              if (obj[3] != null) {
                loanerlogoutTbDTO.setCardNum(obj[3].toString());
              }
              temp_list.add(loanerlogoutTbDTO);
            }
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
   * 贷款户删除
   * 
   * @author yqf 2007-10-16 根据查询条件查出所有的pl111中贷款账号，合同编号，pl110中借款人姓名，证件号码
   *         为求页面数据条数和打印用
   *         查询条件：pl111.loanKouAcc,pl111.contractId,pl110.borrowerName,pl110.cardNum,pl111.loanBankId,pl111.contract_st='13'
   */
  public List queryloandeleteListCount(final String loanKouAcc,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanBankId, final List loanbankList) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct(a.contract_id),"
              + "a.loan_kou_acc loankouacc,"
              + "b.borrower_name borrowername,"
              + "b.card_num cardnum "
              + "from pl111 a,pl110 b "
              + "where a.contract_id=b.contract_id and a.contract_st not in('10','11','12','13') ";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += " a.loan_kou_acc=? and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " a.contract_id=? and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " b.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " b.card_num=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " a.loan_bank_id=? and ";
            parameters.add(loanBankId);
          }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " a.loan_bank_id = ? or ";
              parameters.add(loanbankList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              LoanerlogoutTbDTO loanerlogoutTbDTO = new LoanerlogoutTbDTO();
              if (obj[0] != null) {
                loanerlogoutTbDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                loanerlogoutTbDTO.setLoanKouAcc(obj[1].toString());
              }
              if (obj[2] != null) {
                loanerlogoutTbDTO.setBorrowerName(obj[2].toString());
              }
              if (obj[3] != null) {
                loanerlogoutTbDTO.setCardNum(obj[3].toString());
              }
              temp_list.add(loanerlogoutTbDTO);
            }
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
   * author wsh 贷款户注销 查询pl111中的的合同编号下的contract_st=13的是否存在
   * 
   * @param contractId
   * @return
   */
  public Integer findBorrowerAccByContractSt_wsh(final String contractId) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.contract_st='13' and a.contract_id= ? ";
            Vector parameters = new Vector();
            if (contractId != null) {
              parameters.add(contractId);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }

  /**
   * 贷款户注销-反注销
   * 
   * @author wsh 2007-9-28 根据PL111中的合同编号修改PL111中CONTRACT_ST=OLD_CONTRACT_ST
   *         查询条件：contractId
   */
  public void updateBorrowerAccContractStatus_wsh(final String contractId)
      throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update BorrowerAcc borrowerAcc "
              + "set borrowerAcc.contractSt=borrowerAcc.oldContractSt "
              + "where borrowerAcc.contractId = ? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 贷款户注销-打印
   * 
   * @author wsh 2007-9-29 根据查询条件查出pl111中贷款账号，合同编号，pl110中借款人姓名，证件号码
   *         查询条件：pl111.loanKouAcc,pl111.contractId,pl110.borrowerName,pl110.cardNum,pl111.loanBankId,pl111.contract_st='13'
   */
  public List queryLoanerlogoutTbList_wsh(final String loanKouAcc,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanBankId, final List loanbankList) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct(a.contract_id),"
              + "a.loan_kou_acc loankouacc," + "b.borrower_name borrowername,"
              + "b.card_num  cardnum " + "from pl111 a,pl110 b "
              + "where a.contract_id=b.contract_id " + "and a.contract_st='13'";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += " a.loan_kou_acc=? and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += " a.contract_id=? and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " b.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " b.card_num=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " a.loan_bank_id=? and ";
            parameters.add(loanBankId);
          }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " a.loan_bank_id = ? or ";
              parameters.add(loanbankList.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              LoanerlogoutTbDTO loanerlogoutTbDTO = new LoanerlogoutTbDTO();
              if (obj[0] != null) {
                loanerlogoutTbDTO.setContractId(obj[0].toString());
              }
              loanerlogoutTbDTO.setLoanKouAcc(obj[1].toString());
              loanerlogoutTbDTO.setBorrowerName(obj[2].toString());
              loanerlogoutTbDTO.setCardNum(obj[3].toString());
              temp_list.add(loanerlogoutTbDTO);
            }
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public BigDecimal queryBorrowerListByCriterions_wsh(final String contractId,
      final String borrowerName, final String cardNum, final String officeCode,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,
      final String begindate, final String enddate,
      final String contractStFind, final String type,
      final SecurityInfo securityInfo) {
    BigDecimal loanTotleMoney = new BigDecimal(0.00);
    try {
      loanTotleMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select sum(case when p111.contract_st between 11 and 13 then p111.loan_money else p115.loan_money end) loantotalmoney "
                  + " from pl110 p110,pl111 p111,pl114 p114,pl115 p115 "
                  + " where  p110.contract_id=p111.contract_id and p110.contract_id=p115.contract_id and p111.contract_id=p114.contract_id";
              Vector parameters = new Vector();
              String criterion = "";

              if (contractId != null && !"".equals(contractId)) {
                criterion += " p110.contract_id = ? and ";
                parameters.add(contractId);
              }

              if (borrowerName != null && !"".equals(borrowerName)) {
                criterion += " p110.borrower_name = ? and ";
                parameters.add(borrowerName);
              }

              if (cardNum != null && !"".equals(cardNum)) {
                criterion += " p110.card_num = ? and ";
                parameters.add(cardNum);
              }
              if (officeCode != null && !"".equals(officeCode)) {
                criterion += " p110.office = ? and ";
                parameters.add(officeCode);
              }

              if (loanBankName != null && !"".equals(loanBankName)) {
                criterion += " p111.loan_bank_id = ? and ";
                parameters.add(loanBankName);
              }

              if (orgName != null && !"".equals(orgName)) {
                criterion += " p110.org_name = ? and ";
                parameters.add(orgName);
              }

              if (houseType != null && !"".equals(houseType)) {
                criterion += " p114.house_type = ? and ";
                parameters.add(houseType);
              }

              if (beginBizDate != null && !"".equals(beginBizDate)) {
                criterion += " to_char(p110.op_time,'yyyymmdd') >= ? and ";
                parameters.add(beginBizDate);
              }

              if (endBizDate != null && !"".equals(endBizDate)) {
                criterion += " to_char(p110.op_time,'yyyymmdd') <= ? and ";
                parameters.add(endBizDate);
              }
              if (begindate != null && !"".equals(begindate)) {
                criterion += " p110.lastcheck_date >= ? and ";
                parameters.add(begindate);
              }

              if (enddate != null && !"".equals(enddate)) {
                criterion += " p110.lastcheck_date <= ? and ";
                parameters.add(enddate);
              }
              if (contractStFind != null && !"".equals(contractStFind)) {
                criterion += " p111.contract_st = ? and ";
                parameters.add(contractStFind);
              }

              if (type == null || "".equals(type)) {
                criterion += " p111.contract_st in (18) and ";
              }

              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;

              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanTotleMoney;
  }

  public BigDecimal queryBorrowerListByCriterions_wsh_yg(
      final String contractId, final String borrowerName, final String cardNum,
      final String officeCode, final String loanBankName, final String orgName,
      final String houseType, final String beginBizDate,
      final String endBizDate, final String contractStFind, final String type,
      final SecurityInfo securityInfo) {
    BigDecimal loanTotleMoney = new BigDecimal(0.00);
    try {
      loanTotleMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select sum(p114.bargainor_house_area) loantotalmoney from pl110 p110,pl111 p111,pl114 p114,pl115 p115 "
                  + " where  p110.contract_id=p111.contract_id and p110.contract_id=p115.contract_id and p111.contract_id=p114.contract_id and p110.operator "
                  + securityInfo.getDkUserSecuritySQL();
              Vector parameters = new Vector();
              String criterion = "";

              if (contractId != null && !"".equals(contractId)) {
                criterion += " p110.contract_id = ? and ";
                parameters.add(contractId);
              }

              if (borrowerName != null && !"".equals(borrowerName)) {
                criterion += " p110.borrower_name = ? and ";
                parameters.add(borrowerName);
              }

              if (cardNum != null && !"".equals(cardNum)) {
                criterion += " p110.card_num = ? and ";
                parameters.add(cardNum);
              }
              if (officeCode != null && !"".equals(officeCode)) {
                criterion += " p110.office = ? and ";
                parameters.add(officeCode);
              }

              if (loanBankName != null && !"".equals(loanBankName)) {
                criterion += " p111.loan_bank_id = ? and ";
                parameters.add(loanBankName);
              }

              if (orgName != null && !"".equals(orgName)) {
                criterion += " p110.org_name = ? and ";
                parameters.add(orgName);
              }

              if (houseType != null && !"".equals(houseType)) {
                criterion += " p114.house_type = ? and ";
                parameters.add(houseType);
              }

              if (beginBizDate != null && !"".equals(beginBizDate)) {
                criterion += " to_char(p110.op_time,'yyyymmdd') >= ? and ";
                parameters.add(beginBizDate);
              }

              if (endBizDate != null && !"".equals(endBizDate)) {
                criterion += " to_char(p110.op_time,'yyyymmdd') <= ? and ";
                parameters.add(endBizDate);
              }

              if (contractStFind != null && !"".equals(contractStFind)) {
                criterion += " p111.contract_st = ? and ";
                parameters.add(contractStFind);
              }

              if (type == null || "".equals(type)) {
                criterion += " p111.contract_st in (18) and ";
              }

              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;

              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanTotleMoney;
  }

  /**
   * yuqf 查看是否存在“是否签订合同”＝0 & 合同状态＝2（提交审核）的纪录
   * 
   * @param id
   * @return 2007-10-02
   */
  public List queryBorrowerListYU(final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = "select t.contract_id from pl111 t,pl110 t1 "
              + " where t.contract_id=t1.contract_id and t.is_contract_write='0' "
              + " and t. contract_st='2' and t1.operator='"
              + securityInfo.getUserInfo().getUsername() + "'";
          Query query = session.createSQLQuery(hql);

          return query.list();
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * yuqf 查看是否存在“是否签订合同”＝0 & 合同状态＝2（提交审核）的纪录
   * 
   * @param id
   * @return 2007-10-02
   */
  public List queryBorrowerListYU_(final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = "select t.contract_id from pl111 t,pl110 t1 "
              + " where t.contract_id=t1.contract_id and t.is_contract_write='0' "
              + " and t. contract_st='4' and t1.operator='"
              + securityInfo.getUserInfo().getUsername() + "'";
          Query query = session.createSQLQuery(hql);
          return query.list();
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 提前还款参数维护
   * 
   * @author 郭婧平 2007-10-04 根据loanBankId和contract_st=11查询pl111中的contract_id
   */
  public List queryContractIdByLoanBankId(final String loanBankId) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select borrowerAcc.contractId from BorrowerAcc borrowerAcc "
              + "where borrowerAcc.loanBankId=? and borrowerAcc.contractSt='11' ";
          Query query = session.createQuery(hql);
          query.setBigDecimal(0, new BigDecimal(loanBankId));
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          String obj = null;
          while (it.hasNext()) {
            BorrowerAcc borrowerAcc = new BorrowerAcc();
            obj = (String) it.next();
            borrowerAcc.setContractId(obj);
            temp_list.add(borrowerAcc);
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
   * 根据合同编号，判断该合同编号在表PL111中是否存在。如果存在返回true
   * 
   * @author 王野 2007-10-02
   * @param contractId
   * @return
   */
  public boolean isExistsBorrowerAccByContractId(final String contractId,
      final SecurityInfo securityInfo) {
    boolean flag = true;
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select t.contract_id from pl111 t where t.contract_id =? "
                + " and t.loan_bank_id " + securityInfo.getDkSecuritySQL();
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractId);
            return query.uniqueResult();
          }
        });
    if (id == null || id.equals("")) {
      flag = false;
    }
    return flag;
  }

  /**
   * hanl 翻页 状态为(1.5.6)
   * 
   * @param contractId
   * @param borrowerName
   * @param empId
   * @param cardNum
   * @param buyHouseType
   * @param contranct_st
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @param page
   * @return
   */

  public List findBorrowerAccList(final String contractId,
      final String borrowerName, final String empId, final String cardNum,
      final String buyHouseType, final String contranct_st,
      final String orderBy, final String orderother, final int start,
      final int pageSize, final int page, final SecurityInfo securityInfo) {
    List list = null;
    try {
      Validate.isTrue(orderother == null || "ASC".equalsIgnoreCase(orderother)
          || "DESC".equalsIgnoreCase(orderother));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p1.contract_id,p0.emp_id,p0.borrower_name,p0.card_num,p4.house_type,p1.loan_money,"
              + "p1.loan_time_limit,p1.contract_st,p0.is_print_apply,p0.re_date "
              + "from pl111 p1,pl114 p4,pl110 p0 ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "p1.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += "p0.borrower_name= ? and ";
            parameters.add(borrowerName);
          }
          if (empId != null && !empId.equals("")) {
            criterion += "p0.emp_id= ? and ";
            parameters.add(empId);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += "p0.card_num= ? and ";
            parameters.add(cardNum);
          }
          if (buyHouseType != null && !buyHouseType.equals("")) {
            criterion += "p4.house_type= ? and ";
            parameters.add(buyHouseType);
          }
          if (contranct_st != null && !contranct_st.equals("")) {
            criterion += "p1.contract_st= ? and ";
            parameters.add(contranct_st);
          }
          if (criterion.length() != 0) {
            criterion = "where p1.contract_id=p4.contract_id and p1.contract_id=p0.contract_id and p0.operator "
                + securityInfo.getDkUserSecuritySQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          } else {
            criterion = "where p1.contract_id=p4.contract_id and p1.contract_id=p0.contract_id "
                + "and p1.contract_st in('1','5','6','15') and  p0.operator "
                + securityInfo.getDkUserSecuritySQL();
          }

          String ob = orderBy;
          if (ob == null)
            ob = "p4.op_time";

          String od = orderother;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + " order by " + ob + " " + orderother
              + ",p1.contract_id desc";

          Query query = session.createSQLQuery(hql);
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
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl 求出总条数
   * 
   * @param contractId
   * @param borrowerName
   * @param empId
   * @param cardNum
   * @param buyHouseType
   * @param contranct_st
   * @return
   */
  public List findBorrowerAccListNum(final String contractId,
      final String borrowerName, final String empId, final String cardNum,
      final String buyHouseType, final String contranct_st,
      final SecurityInfo securityInfo) {

    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p1.contract_id," + "p0.emp_id,p0.borrower_name,"
              + "p0.card_num,p4.house_type,p1.loan_money,"
              + "p1.loan_time_limit,p1.contract_st,p0.is_print_apply"
              + " from pl111 p1,pl114 p4,pl110 p0 ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "p1.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += "p0.borrower_name= ? and ";
            parameters.add(borrowerName);
          }
          if (empId != null && !empId.equals("")) {
            criterion += "p0.emp_id= ? and ";
            parameters.add(empId);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += "p0.card_num= ? and ";
            parameters.add(cardNum);
          }
          if (buyHouseType != null && !buyHouseType.equals("")) {
            criterion += "p4.house_type= ? and ";
            parameters.add(buyHouseType);
          }
          if (contranct_st != null && !contranct_st.equals("")) {
            criterion += "p1.contract_st= ? and ";
            parameters.add(contranct_st);
          }
          if (criterion.length() != 0) {
            criterion = "where p1.contract_id=p4.contract_id and p1.contract_id=p0.contract_id and p0.operator "
                + securityInfo.getDkUserSecuritySQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          } else {
            criterion = "where p1.contract_id=p4.contract_id and p1.contract_id=p0.contract_id "
                + "and p1.contract_st in('1','5','6','15') and  p0.operator "
                + securityInfo.getDkUserSecuritySQL();
          }

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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
   * hanl 删除pl111
   * 
   * @param id
   */

  public void deleteBorrowerAccInfo(final String id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "delete from BorrowerAcc borroweracc where borroweracc.contractId=?";
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
   * 根据合同标号，返回页面要带出的信息List
   * 
   * @author 王野 2007-10-02
   * @param contractId
   * @return
   */
  public List queryBailenRolInfoByContractId(final String contractId,
      final SecurityInfo securityInfo) {
    List bailenRolTalist = new ArrayList();
    bailenRolTalist = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p111.contract_id,"
                + "p110.borrower_name,"
                + "p110.card_kind,"
                + "p110.card_num,"
                + "p111.loan_bank_id,"
                + "p111.loan_kou_acc,"
                + "(select distinct p002.loan_acc from pl111 p111,pl002 p002 where p111.loan_bank_id = p002.loan_bank_id and p002.loan_bank_id = "
                + " (select p111.loan_bank_id from pl110 p110, pl111 p111 where p110.contract_id = p111.contract_id "
                + " and p111.contract_id = ? and p111.loan_bank_id "
                + securityInfo.getDkSecuritySQL() + " ) ) "
                + " from pl110 p110, pl111 p111 "
                + " where p110.contract_id = p111.contract_id "
                + " and p111.contract_id = ?" + " and p111.loan_bank_id "
                + securityInfo.getDkSecuritySQL();

            Query query = session.createSQLQuery(hql);
            query.setParameter(1, contractId);
            query.setParameter(0, contractId);
            return query.list();
          }
        });
    return bailenRolTalist;
  }

  /**
   * 下达发放通知书_确定
   * 
   * @author wsh 2007-10-02 判断该合同编号下的CONTRACT_ST=4 and IS_CONTRACT_WRITE=2
   *         查询条件:pl111.contract_id
   */
  public Integer queryCountByContractId_wsh(final String contractId) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.contract_st = 14 and a.is_contract_write=1 and a.contract_id = ? ";
            Vector parameters = new Vector();
            if (contractId != null) {
              parameters.add(contractId);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }

  /**
   * 下达发放通知书_确定
   * 
   * @author wsh 2007-10-03 根据PL111中的合同编号修改PL111中CONTRACT_ST=9 查询条件：contractId
   */
  public void updateBorrowerAccContractStatus1_wsh(final String contractId)
      throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update BorrowerAcc borrowerAcc "
              + "set borrowerAcc.contractSt = 9 "
              + "where borrowerAcc.contractId = ? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateBorrowerAccContract(final String contractId,
      final String loanKouAcc) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update BorrowerAcc borrowerAcc "
              + "set borrowerAcc.contractSt = 9,borrowerAcc.loanKouAcc=? "
              + "where borrowerAcc.contractId = ? ";
          session.createQuery(hql).setString(0, loanKouAcc).setString(1,
              contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 下达发放通知书_维护_查询
   * 
   * @author wsh 2007-10-03 根据查询条件查出列表信息所有的pl111中，合同编号，pl110中借款人姓名，证件号码
   *         查询条件：pl111.contractId,pl110.borrowerName,pl110.cardNum,pl111.loanBankId
   */
  public List queryIssuenoticeTbListCount(final String contractId,
      final String borrowerName, final String cardNum, final String loanBankId,
      final String contractSt, final List loanbankList, final String type,
      final String isPrint) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Vector parameters = new Vector();
          String hql = "select b.contract_id contractid,"
              + " a.borrower_name borrowername,"
              + " a.card_num cardnum,"
              + " b.loan_bank_id loanbankid,"
              + " d.loan_money loanmoney,"
              + " d.loan_time_limit loantimelimit,"
              + " d.loan_month_rate loanmonthrate,"
              + " d.loan_mode loanmode,b.contract_st contractst,a.IS_PRINT_IOU,"
              + "(select case when pl114.head_id is not null then (select pl005.developer_name from pl005 where pl005.id = pl114.head_id) else '' end from pl114 where pl114.contract_id = a.contract_id),  "
              + " case when c.house_type = '01' then c.house_addr else c.bargainor_house_addr end"
              + " from pl110 a, pl111 b, pl114 c, pl115 d"
              + " where a.contract_id = b.contract_id"
              + " and a.contract_id = c.contract_id "
              + " and b.contract_id = d.contract_id ";
          if (contractSt != null && !contractSt.equals("")) {
            hql = hql + "and b.contract_st = ? ";
            parameters.add(contractSt);
          } else if ("1".equals(type)) {
            hql = hql + "and b.contract_st in(9,10,11,12,13) ";
          } else {
            hql = hql + "and b.contract_st = 9 ";
          }
          String criterion = "";
          if (contractId != null && !contractId.equals("")) {
            criterion += " a.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " a.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " a.card_num=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " b.loan_bank_id=? and ";
            parameters.add(loanBankId);
          }
          if (isPrint != null && !isPrint.equals("")) {
            criterion += " a.IS_PRINT_IOU=? and ";
            parameters.add(isPrint);
          }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " b.loan_bank_id = ? or ";
              parameters.add(loanbankList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              IssuenoticeTbDTO issuenoticeTbDTO = new IssuenoticeTbDTO();
              if (obj[0] != null) {
                issuenoticeTbDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                issuenoticeTbDTO.setBorrowerName(obj[1].toString());
              }

              if (obj[2] != null) {
                issuenoticeTbDTO.setCardNum(obj[2].toString());
              }
              if (obj[3] != null) {
                issuenoticeTbDTO.setLoanBankId(obj[3].toString());
              }
              if (obj[4] != null) {
                issuenoticeTbDTO
                    .setLoanMoney(new BigDecimal(obj[4].toString()));

              }
              if (obj[5] != null) {
                issuenoticeTbDTO.setLoanTimeLimit(obj[5].toString());

              }
              if (obj[6] != null) {
                issuenoticeTbDTO.setLoanMonthRate(new BigDecimal(obj[6]
                    .toString()));
              }
              if (obj[7] != null) {
                issuenoticeTbDTO.setLoanMode(obj[7].toString());
              }
              if (obj[8] != null) {
                issuenoticeTbDTO.setContractSt(obj[8].toString());
              }
              if (obj[9] != null) {
                if ("0".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("否");
                }
                if ("１".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("是");
                }
              }
              if (obj[10] != null) {
                issuenoticeTbDTO.setDeveloperName(obj[10].toString());
              }
              if (obj[11] != null) {
                issuenoticeTbDTO.setHouseAddr(obj[11].toString());
              }
              temp_list.add(issuenoticeTbDTO);

            }

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
   * 下达发放通知书_维护_查询
   * 
   * @author wsh 2007-9-29 根据查询条件查出pl111中贷款账号，合同编号，pl110中借款人姓名，证件号码 查询条件：
   *         pl111.loanKouAcc,pl111.contractId,pl110.borrowerName,pl110.cardNum,pl111.loanBankId,pl111.contract_st='13'
   */
  public List queryIssuenoticeTbList(final String contractId,
      final String borrowerName, final String cardNum, final String loanBankId,
      final String contractSt, final String orderBy, final String order,
      final int start, final int pageSize, final List loanbankList,
      final String type, final int page, final String isPrint) throws Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Vector parameters = new Vector();
          String hql = "select b.contract_id contractid,"
              + " a.borrower_name borrowername,"
              + " a.card_num cardnum,"
              + " b.loan_bank_id loanbankid,"
              + " d.loan_money loanmoney,"
              + " d.loan_time_limit loantimelimit,"
              + " d.loan_month_rate loanmonthrate,"
              + " d.loan_mode loanmode,b.contract_st contractst,a.IS_PRINT_IOU, "
              + " case when c.house_type = '01' then c.house_addr else c.bargainor_house_addr end,b.mark_a "
              + " from pl110 a, pl111 b, pl114 c, pl115 d"
              + " where a.contract_id = b.contract_id"
              + " and a.contract_id = c.contract_id "
              + " and b.contract_id = d.contract_id ";
          if (contractSt != null && !contractSt.equals("")) {
            hql = hql + "and b.contract_st = ? ";
            parameters.add(contractSt);
          } else if ("1".equals(type)) {
            hql = hql + "and b.contract_st in(9,10,11,12,13) ";
          } else {
            hql = hql + "and b.contract_st = 9 ";
          }

          String criterion = "";
          if (contractId != null && !contractId.equals("")) {
            criterion += " a.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " a.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " a.card_num=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " b.loan_bank_id=? and ";
            parameters.add(loanBankId);
          }
          if (isPrint != null && !isPrint.equals("")) {
            criterion += " a.IS_PRINT_IOU=? and ";
            parameters.add(isPrint);
          }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " b.loan_bank_id = ? or ";
              parameters.add(loanbankList.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " contractid ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List temp_list1 = new ArrayList();
          temp_list1 = query.list();
          if (temp_list1 == null || temp_list1.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            temp_list1 = query.list();
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              IssuenoticeTbDTO issuenoticeTbDTO = new IssuenoticeTbDTO();
              if (obj[0] != null) {
                issuenoticeTbDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                issuenoticeTbDTO.setBorrowerName(obj[1].toString());
              }

              if (obj[2] != null) {
                issuenoticeTbDTO.setCardNum(obj[2].toString());
              }
              if (obj[3] != null) {
                issuenoticeTbDTO.setLoanBankId(obj[3].toString());
              }
              if (obj[4] != null) {
                issuenoticeTbDTO
                    .setLoanMoney(new BigDecimal(obj[4].toString()));

              }
              if (obj[5] != null) {
                issuenoticeTbDTO.setLoanTimeLimit(obj[5].toString());

              }
              if (obj[6] != null) {
                issuenoticeTbDTO.setLoanMonthRate(new BigDecimal(obj[6]
                    .toString()));
              }
              if (obj[7] != null) {
                issuenoticeTbDTO.setLoanMode(obj[7].toString());
              }
              if (obj[8] != null) {
                issuenoticeTbDTO.setContractSt(obj[8].toString());
              }
              if (obj[9] != null) {
                if ("0".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("否");
                }
                if ("1".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("是");
                }
              }
              if (obj[10] != null) {
                issuenoticeTbDTO.setHouseAddr(obj[10].toString());
              }
              if (obj[11] != null) {
                if ("0".equals(obj[11].toString())) {
                  issuenoticeTbDTO.setIschecked("否");
                }
                if ("1".equals(obj[11].toString())) {
                  issuenoticeTbDTO.setIschecked("是");
                }
              }
              temp_list.add(issuenoticeTbDTO);

            }

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
   * author wsh 下达发放通知书_维护_删除 查询pl111中的的合同编号下的contract_st=9的是否存在
   * 
   * @param contractId
   * @return
   */
  public Integer findBorrowerAccByContractSt9_wsh(final String contractId) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.contract_st='9' and a.contract_id= ? ";
            Vector parameters = new Vector();
            if (contractId != null) {
              parameters.add(contractId);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }

  public Integer findBorrowerAccByContractSt9_wsh_1(final String contractId,
      final String mark_a, final String mark_b) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.contract_st='9' and a.contract_id= ? and (a.mark_a="
                + mark_a + " or a.mark_b=" + mark_b + ")";
            if ("3".equals(mark_b)) {
              hql = "select count(a.contract_id) from pl111 a where a.contract_st='9' and a.contract_id= ? and a.mark_a="
                  + mark_a;
            }
            Vector parameters = new Vector();
            if (contractId != null) {
              parameters.add(contractId);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }

  public Integer findBorrowerAccByContractSt9_wsh_3(final String contractId,
      final String mark) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.contract_st='9' and a.contract_id= ? and a.mark_b="
                + mark;
            Vector parameters = new Vector();
            if (contractId != null) {
              parameters.add(contractId);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }

  public Integer findBorrowerAccByContractSt9_wsh_2(final String contractId) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.contract_st='9' and a.contract_id= ? and a.mark_b='1' ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractId);
            return new Integer(query.uniqueResult().toString());
          }
        });
    return count;
  }

  /**
   * 下达发放通知书_确定
   * 
   * @author wsh 2007-10-03 根据PL111中的合同编号修改PL111中CONTRACT_ST=4 查询条件：contractId
   */
  public void updateBorrowerAccContractStatus4_wsh(final String contractId)
      throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update BorrowerAcc borrowerAcc "
              + "set borrowerAcc.contractSt = 14 "
              + "where borrowerAcc.contractId = ? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateBorrowerStatus_wsh(final String contractId)
      throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update Borrower borrower "
              + "set borrower.isPrintIou = 0 "
              + "where borrower.contractId = ? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 求出审核审批未通过的原因
   * 
   * @param contractId
   * @param type
   * @return
   */
  public String findReasonBycontractId(final String contractId,
      final String type) {

    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.reason_a,p.reason_b from pl111 p where p.contract_id=? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractId);
            Object[] obj = null;
            obj = (Object[]) query.uniqueResult();
            String reason = "";
            if (type.equals("5")) {
              reason = obj[0].toString();
            } else {
              reason = obj[1].toString();
            }
            return reason;
          }
        });

    return id;
  }

  /**
   * hanl 翻页 基本信息变更维护 状态为(7.8.11)
   * 
   * @param contractId
   * @param borrowerName
   * @param empId
   * @param cardNum
   * @param buyHouseType
   * @param contranct_st
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @param page
   * @return
   */

  public List findBasicBorrowerAccList(final String contractId,
      final String borrowerName, final String empId, final String cardNum,
      final String buyHouseType, final String contranct_st,
      final String orderBy, final String orderother, final int start,
      final int pageSize, final int page, final SecurityInfo securityInfo) {
    List list = null;
    try {
      Validate.isTrue(orderother == null || "ASC".equalsIgnoreCase(orderother)
          || "DESC".equalsIgnoreCase(orderother));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p1.contract_id,p0.emp_id,p0.borrower_name,p0.card_num,p4.house_type,p1.loan_money,p1.loan_time_limit,p1.contract_st from pl111 p1,pl114 p4,pl110 p0 ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "p1.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += "p0.borrower_name= ? and ";
            parameters.add(borrowerName);
          }
          if (empId != null && !empId.equals("")) {
            criterion += "p0.emp_id= ? and ";
            parameters.add(empId);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += "p0.card_num= ? and ";
            parameters.add(cardNum);
          }
          if (buyHouseType != null && !buyHouseType.equals("")) {
            criterion += "p4.house_type= ? and ";
            parameters.add(buyHouseType);
          }
          if (contranct_st != null && !contranct_st.equals("")) {
            criterion += "p1.contract_st= ? and ";
            parameters.add(contranct_st);
          }
          if (criterion.length() != 0) {
            criterion = "where p1.contract_id=p4.contract_id and p1.contract_id=p0.contract_id and p1.contract_st in('7','8','11') and p1.loan_bank_id in "
                + "((select distinct ca113.coll_bank_id from CA113 ca113, PL002 pl002 where pl002.loan_bank_id = coll_bank_id and ca113.username = '"
                + securityInfo.getUserInfo().getUsername()
                + "'))"
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          } else {
            criterion = "where p1.contract_id=p4.contract_id and p1.contract_id=p0.contract_id "
                + "and p1.contract_st in('7','8','11') and p1.loan_bank_id in "
                + "((select distinct ca113.coll_bank_id from CA113 ca113, PL002 pl002 where pl002.loan_bank_id = coll_bank_id and ca113.username = '"
                + securityInfo.getUserInfo().getUsername() + "'))";
          }

          String ob = orderBy;
          if (ob == null)
            ob = "p1.contract_st";

          String od = orderother;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + orderother
              + ",p1.contract_id desc";

          Query query = session.createSQLQuery(hql);
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
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl 求出总条数 状态为(7.8.11)
   * 
   * @param contractId
   * @param borrowerName
   * @param empId
   * @param cardNum
   * @param buyHouseType
   * @param contranct_st
   * @return
   */
  public List findBorrowerAccList(final String contractId,
      final String borrowerName, final String empId, final String cardNum,
      final String buyHouseType, final String contranct_st,
      final SecurityInfo securityInfo) {

    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p1.contract_id,p0.emp_id,p0.borrower_name,p0.card_num,p4.house_type,p1.loan_money,p1.loan_time_limit,p1.contract_st from pl111 p1,pl114 p4,pl110 p0 ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "p1.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += "p0.borrower_name= ? and ";
            parameters.add(borrowerName);
          }
          if (empId != null && !empId.equals("")) {
            criterion += "p0.emp_id= ? and ";
            parameters.add(empId);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += "p0.card_num= ? and ";
            parameters.add(cardNum);
          }
          if (buyHouseType != null && !buyHouseType.equals("")) {
            criterion += "p4.house_type= ? and ";
            parameters.add(buyHouseType);
          }
          if (contranct_st != null && !contranct_st.equals("")) {
            criterion += "p1.contract_st= ? and ";
            parameters.add(contranct_st);
          }
          if (criterion.length() != 0) {
            criterion = "where p1.contract_id=p4.contract_id and p1.contract_id=p0.contract_id and p1.contract_st in('7','8','11') and p1.loan_bank_id in "
                + "((select distinct ca113.coll_bank_id from CA113 ca113, PL002 pl002 where pl002.loan_bank_id = coll_bank_id and ca113.username = '"
                + securityInfo.getUserInfo().getUsername()
                + "'))"
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          } else {
            criterion = "where p1.contract_id=p4.contract_id and p1.contract_id=p0.contract_id "
                + "and p1.contract_st in('7','8','11') and p1.loan_bank_id in "
                + "((select distinct ca113.coll_bank_id from CA113 ca113, PL002 pl002 where pl002.loan_bank_id = coll_bank_id and ca113.username = '"
                + securityInfo.getUserInfo().getUsername() + "'))";
          }
          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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
   * yuqf 2007-10-09 查询PL111,pl110 ,pl120 ,pl115 ,pl114 合同编号 借款人姓名 借款金额 借款起始日期
   * 借款期限 借款终止日期 借款每月利率 月还本息 放款银行 查看扫描信息
   * 
   * @param contractId
   * @param debitter
   * @param empId
   * @param cardNum
   * @param houseType
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws Exception
   */
  public List queryBorrowerAccListByConditionYU(final String contractId,
      final String debitter, final String empId, final String cardNum,
      final String houseType, final String orderBy, final String order,
      final int start, final int pageSize, final SecurityInfo securityInfo)
      throws Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select t1.contract_id,t2.borrower_name,"
              + // 0,1
              " t1.loan_money,t3.loan_start_date,"
              + // 2,3
              " t1.loan_time_limit,t4.loan_month_rate,"
              + // 4,5
              " t4.corpus_interest,t1.loan_bank_id "
              + // 6,7
              " from pl111 t1,pl110 t2,pl120 t3,pl115 t4,pl114 t5 "
              + " where t1.contract_id=t2.contract_id and "
              + " t2.contract_id=t3.contract_id and "
              + " t3.contract_id=t4.contract_id and "
              + " t4.contract_id=t5.contract_id and "
              + " t1.contract_st in (7,8,11) and t1.loan_bank_id in ((select distinct ca113.coll_bank_id"
              + " from CA113 ca113, PL002 pl002 where pl002.loan_bank_id = coll_bank_id and ca113.username = '"
              + securityInfo.getUserInfo().getUsername() + "')) ";

          Vector parameters = new Vector();
          String criterion = "";
          if (contractId != null && !"".equals(contractId)) {
            criterion += "  t1.contract_id=?  and ";
            parameters.add(contractId);
          }
          if (debitter != null && !"".equals(debitter)) {
            criterion += "  t2.borrower_name=? and ";
            parameters.add(debitter);
          }
          if (empId != null && !"".equals(empId)) {
            criterion += "  t2.emp_id=? and ";
            parameters.add(empId);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += "  t2.card_num=? and ";
            parameters.add(cardNum);
          }
          if (houseType != null && !"".equals(houseType)) {
            criterion += "  t5.house_type=?  and ";
            parameters.add(houseType);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " t1.contract_id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              AssurepledgechgTaDTO assurepledgechgTaDTO = new AssurepledgechgTaDTO();
              if (obj[0] != null) {
                assurepledgechgTaDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                assurepledgechgTaDTO.setDebitter(obj[1].toString());
              }

              if (obj[2] != null) {
                assurepledgechgTaDTO.setLoanMoney(obj[2].toString());
              }
              if (obj[3] != null) {
                assurepledgechgTaDTO.setStartDate(obj[3].toString());
              }
              if (obj[4] != null) {
                assurepledgechgTaDTO.setLoanTimeLimit(obj[4].toString());

              }
              if (obj[5] != null) {
                assurepledgechgTaDTO.setLoanMonthRate(obj[5].toString());

              }
              if (obj[6] != null) {
                assurepledgechgTaDTO.setCorpusInterest(obj[6].toString());
              }
              if (obj[7] != null) {
                assurepledgechgTaDTO.setBank(obj[7].toString());
              }

              temp_list.add(assurepledgechgTaDTO);

            }

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
   * yuqf 2007-10-09 count list size;
   * 
   * @param contractId
   * @param debitter
   * @param empId
   * @param cardNum
   * @param houseType
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws Exception
   */
  public int queryBorrowerAccListCountByConditionYU(final String contractId,
      final String debitter, final String empId, final String cardNum,
      final String houseType, final String orderBy, final String order,
      final int start, final int pageSize, final SecurityInfo securityInfo)
      throws Exception {
    int i = 0;
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select t1.contract_id,t2.borrower_name,"
              + // 0,1
              " t1.loan_money,t3.loan_start_date,"
              + // 2,3
              " t1.loan_time_limit,t4.loan_month_rate,"
              + // 4,5
              " t4.corpus_interest,t1.loan_bank_id "
              + // 6,7
              " from pl111 t1,pl110 t2,pl120 t3,pl115 t4,pl114 t5 "
              + " where t1.contract_id=t2.contract_id and "
              + " t2.contract_id=t3.contract_id and "
              + " t3.contract_id=t4.contract_id and "
              + " t4.contract_id=t5.contract_id and "
              + " t1.contract_st in (7,8,11) and t1.loan_bank_id in ((select distinct ca113.coll_bank_id"
              + " from CA113 ca113, PL002 pl002 where pl002.loan_bank_id = coll_bank_id and ca113.username = '"
              + securityInfo.getUserInfo().getUsername() + "')) ";// getDkUserSecuritySQL()

          Vector parameters = new Vector();
          String criterion = "";
          if (contractId != null && !"".equals(contractId)) {
            criterion += " t1.contract_id=? and ";
            parameters.add(contractId);
          }
          if (debitter != null && !"".equals(debitter)) {
            criterion += " t2.borrower_name=? and ";
            parameters.add(debitter);
          }
          if (empId != null && !"".equals(empId)) {
            criterion += " t2.emp_id=? and ";
            parameters.add(empId);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " t2.card_num=? and ";
            parameters.add(cardNum);
          }
          if (houseType != null && !"".equals(houseType)) {
            criterion += " t5.house_type=? and ";
            parameters.add(houseType);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " t1.contract_id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    i = list.size();
    return i;
  }

  /**
   * 根据贷款帐号查询对应银行
   * 
   * @param loanKouAcc
   * @return
   * @author 付云峰
   */
  public Object[] queryLoanBankIdByLoanKouAcc(final String loanKouAcc) {
    Object[] obj = new Object[2];
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select p11.loan_bank_id,p11.contract_id from pl111 p11 where p11.loan_kou_acc=?";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, loanKouAcc);

          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * 根据贷款帐号查询/*** 贷款账号，合同编号，借款人姓名，证件号码，保证金余额，本年基数，往年基数，贷款余额,呆账未回收金额
   * 
   * @param loanKouAcc
   * @return
   * @author yuqf
   */
  public Object[] queryObjectByLoanKouAccYU(final String loanKouAcc) {
    Object[] obj = new Object[10];
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " select t.loan_kou_acc,t.contract_id,"
              + " t2.borrower_name,t2.card_num,"
              + " t.bail_balance,t.cur_integral,"
              + " t.pre_integral,t.overplus_loan_money,"
              + " t.no_back_money,t.ovaer_loan_repay from pl111 t,pl110 t2 "
              + " where t.contract_id=t2.contract_id and t.loan_kou_acc=?";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, loanKouAcc);

          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  public Object[] queryObjectByLoanKouAccYU_(final String loanKouAcc,
      final String flowHeadId) {
    Object[] obj = new Object[3];
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select  abs(a.occur_money),a.other_interest from pl203 a, pl202 t "
              + "where a.flow_head_id = t.flow_head_id and a.loan_kou_acc = ? and t.biz_type = 14 "
              + "and a.flow_head_id = ? ";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, loanKouAcc);
          query.setParameter(1, flowHeadId);

          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * hanl 求出带分页的台帐list
   * 
   * @param offic
   * @param payBank
   * @param developer
   * @param floorId
   * @param contactid
   * @param loankouacc
   * @param assistantorg
   * @param contact_st
   * @param borrowerName
   * @param cardNum
   * @param loanMoneyB
   * @param loanMoneyE
   * @param loanLimitB
   * @param loanLimitE
   * @param loanStartDateB
   * @param loanStartDateE
   * @param ageB
   * @param ageE
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   */
  public Object[] queryDeskAccTotalInfo(final String office,
      final String payBank, final String developer, final String contactid,
      final String loankouacc, final String assistantorg,
      final String contact_st, final String borrowerName, final String cardNum,
      final String loanMoneyB, final String loanMoneyE,
      final String loanLimitB, final String loanLimitE,
      final String loanStartDateB, final String loanStartDateE,
      final String ageB, final String ageE, final SecurityInfo securityInfo,
      final String orgId, final String eare, final String areaB,
      final String areaE, final String houseType, final String loanType) {
    Object[] info = null;
    try {
      info = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(t.contractid),"
              + " nvl(sum(t.loanmoney),0),"
              + " nvl(sum(t.overloanrepay),0),"
              + " nvl(sum(t.nobackmoney),0),"
              + " nvl(sum(t.bailbalance),0),"
              + " nvl(sum(t.monthpay),0),"
              + " nvl(sum(t.corpus),0),"
              + " nvl(sum(t.interest),0),"
              + " nvl(sum(t.punish),0),"
              + " nvl(sum(t.house_area),0),"
              + " nvl(sum(t.price),0),"
              + " nvl(sum(t.overplusmoney),0)"
              + " from("
              + " select p11.contract_id contractid,"
              + " p11.loan_money loanmoney,"
              + " p11.ovaer_loan_repay overloanrepay,"
              + " p11.no_back_money nobackmoney,"
              + " p11.bail_balance bailbalance,"
              + " (select p201.should_corpus + p201.should_interest "
              + " from pl201 p201 where p201.contract_id = p11.contract_id and p201.loan_kou_yearmonth = '"
              + securityInfo.getUserInfo().getPlbizDate().substring(0, 6)
              + "') monthpay,"
              + " sum(p203.real_corpus) as corpus,"
              + " sum(p203.real_interest) as interest,"
              + " sum(p203.real_punish_interest) as punish,"
              + " nvl(p14.bargainor_house_area,0)+nvl(p14.house_area,0) as house_area,"
              + " nvl(p14.bargainor_totle_price, 0) + nvl(p14.house_totle_price, 0) as price,"
              + " p11.overplus_loan_money overplusmoney"
              + " from pl110 p10, pl111 p11, pl114 p14, pl202 p202, pl203 p203 "
              + " where p10.contract_id = p11.contract_id "
              + " and p11.contract_id = p14.contract_id "
              + " and p202.flow_head_id = p203.flow_head_id"
              + " and p203.contract_id = p11.contract_id"
              + " and p11.contract_st in('11','12','13')"
              + " and p202.biz_st = 6" + " and p11.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (office != null && !"".equals(office)) {
            criterion += " p10.office=? and ";
            parameters.add(office);
          }
          if (payBank != null && !"".equals(payBank)) {
            criterion += " p11.loan_bank_id = ? and ";
            parameters.add(payBank);
          }
          if (developer != null && !"".equals(developer)) {
            criterion += " p14.head_id = ? and ";
            parameters.add(developer);
          }
          if (contactid != null && !"".equals(contactid)) {
            criterion += " p11.contract_id like ? and ";
            parameters.add("%" + contactid + "%");
          }
          if (loankouacc != null && !"".equals(loankouacc)) {
            criterion += " p11.loan_kou_acc=? and ";
            parameters.add(loankouacc);
          }
          if (assistantorg != null && !"".equals(assistantorg)) {
            criterion += " p11.contract_id in (select p120.contract_id from pl120 p120 where p120.assistant_org_id=?) and ";
            parameters.add(assistantorg);
          }
          if (contact_st != null && !"".equals(contact_st)) {
            criterion += " p11.contract_st=? and ";
            parameters.add(contact_st);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p10.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p10.card_num like ? and ";
            parameters.add("%" + cardNum + "%");
          }
          if (loanMoneyB != null && !"".equals(loanMoneyB)) {
            criterion += " p11.loan_money>=? and ";
            parameters.add(loanMoneyB);
          }
          if (loanMoneyE != null && !"".equals(loanMoneyE)) {
            criterion += " p11.loan_money<=? and ";
            parameters.add(loanMoneyE);
          }
          if (loanLimitB != null && !"".equals(loanLimitB)) {
            criterion += " to_number(p11.loan_time_limit)>=? and ";
            parameters.add(loanLimitB);
          }
          if (loanLimitE != null && !"".equals(loanLimitE)) {
            criterion += " to_number(p11.loan_time_limit)<=? and ";
            parameters.add(loanLimitE);
          }
          if (loanStartDateB != null && !"".equals(loanStartDateB)) {
            criterion += " to_number(p11.loan_start_date)>=? and ";
            parameters.add(loanStartDateB);
          }
          if (loanStartDateE != null && !"".equals(loanStartDateE)) {
            criterion += " to_number(p11.loan_start_date)<=? and ";
            parameters.add(loanStartDateE);
          }
          if (ageB != null && !"".equals(ageB)) {
            criterion += " p10.age>=? and ";
            parameters.add(ageB);
          }
          if (ageE != null && !"".equals(ageE)) {
            criterion += " p10.age<=? and ";
            parameters.add(ageE);
          }

          if (orgId != null && !"".equals(orgId)) {
            criterion += " p10.org_id=? and ";
            parameters.add(new Integer(orgId));
          }
          if (houseType != null && !"".equals(houseType)) {
            criterion += " p14.house_type=? and ";
            parameters.add(houseType);
          }
          if (loanType != null && !"".equals(loanType) && "0".equals(loanType)) {
            criterion += " p10.loantype=1 and ";
          }
          if (loanType != null && !"".equals(loanType) && "1".equals(loanType)) {
            criterion += " p10.loantype=0 and ";
          }
          if (areaB != null && !"".equals(areaB)) {
            criterion += " (p14.bargainor_house_area>=? or p14.house_area>=?) and ";
            parameters.add(new BigDecimal(areaB));
            parameters.add(new BigDecimal(areaB));
          }
          if (areaE != null && !"".equals(areaE)) {
            criterion += " (p14.bargainor_house_area<=? or p14.house_area<=?) and ";
            parameters.add(new BigDecimal(areaE));
            parameters.add(new BigDecimal(areaE));
          }
          // -------------------------------------------
          if (eare != null && !"".equals(eare)) {
            criterion += " (p14.bargainor_house_area=? or p14.house_area=?) and ";
            try {
              parameters.add(new BigDecimal(eare));
              parameters.add(new BigDecimal(eare));
            } catch (NumberFormatException e) {
              parameters.add(new BigDecimal("-10000"));
              parameters.add(new BigDecimal("-10000"));
            }
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String groupBy = "p11.contract_id,"
              + " p11.loan_money,"
              + " p11.ovaer_loan_repay,"
              + " p11.no_back_money,"
              + " p11.bail_balance,"
              + " nvl(p14.bargainor_house_area, 0) + nvl(p14.house_area, 0),"
              + " nvl(p14.bargainor_totle_price, 0) + nvl(p14.house_totle_price, 0),"
              + " p11.overplus_loan_money ";
          sql = sql + criterion + " group by " + groupBy + ") t";
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

  public List showLoandeskaccqueryList(final String office,
      final String payBank, final String developer, final String contactid,
      final String loankouacc, final String assistantorg,
      final String contact_st, final String borrowerName, final String cardNum,
      final String loanMoneyB, final String loanMoneyE,
      final String loanLimitB, final String loanLimitE,
      final String loanStartDateB, final String loanStartDateE,
      final String ageB, final String ageE, final String orderBy,
      final String orderother, final int start, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String orgId,
      final String eare, final String areaB, final String areaE,
      final String houseType, final String loanType) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p11.contract_id,"
              + " p11.loan_kou_acc,"
              + " p10.borrower_name,"
              + " p10.card_num,"
              + " p11.loan_bank_id,"
              + " p11.loan_money,"
              + " p11.loan_time_limit,"
              + " p11.loan_start_date,"
              + " p11.contract_st,"
              + " p11.ovaer_loan_repay,"
              + " p11.no_back_money,"
              + " p11.bail_balance,"
              + " p11.loan_repay_day,"
              + " p11.overplus_limite, "
              + " sum(p203.real_corpus) as corpus,"
              + " sum(p203.real_interest) as interest,"
              + " sum(p203.real_punish_interest) as punish,"
              + " p10.org_id,"
              + " p10.org_name,"
              + " nvl(p14.bargainor_house_area,0)+nvl(p14.house_area,0) as house_area,"
              + " p11.overplus_loan_money,"
              + " (select count(distinct p400.contract_id) from pl400 p400 "
              + " where p400.contract_id = p11.contract_id and p400.status = 0 and p400.reservea_b = 1) num,"
              + " (select p201.should_corpus + p201.should_interest "
              + " from pl201 p201 where p201.contract_id = p11.contract_id and p201.loan_kou_yearmonth = '"
              + securityInfo.getUserInfo().getPlbizDate().substring(0, 6)
              + "')"
              + " from pl110 p10, pl111 p11, pl114 p14, pl202 p202, pl203 p203 "
              + " where p10.contract_id = p11.contract_id "
              + " and p11.contract_id = p14.contract_id "
              + " and p202.flow_head_id = p203.flow_head_id"
              + " and p203.contract_id = p11.contract_id"
              + " and p11.contract_st in('11','12','13') "
              + " and p202.biz_st = 6" + " and p11.loan_bank_id "
              + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";
          if (office != null && !"".equals(office)) {
            criterion += " p10.office=? and ";
            parameters.add(office);
          }
          if (payBank != null && !"".equals(payBank)) {
            criterion += " p11.loan_bank_id = ? and ";
            parameters.add(payBank);
          }
          if (developer != null && !"".equals(developer)) {
            criterion += " p14.head_id = ? and ";
            parameters.add(developer);
          }
          if (contactid != null && !"".equals(contactid)) {
            criterion += " p11.contract_id like ? and ";
            parameters.add("%" + contactid + "%");
          }
          if (loankouacc != null && !"".equals(loankouacc)) {
            criterion += " p11.loan_kou_acc=? and ";
            parameters.add(loankouacc);
          }
          if (assistantorg != null && !"".equals(assistantorg)) {
            criterion += " p11.contract_id in (select p120.contract_id from pl120 p120 where p120.assistant_org_id=?) and ";
            parameters.add(assistantorg);
          }
          if (contact_st != null && !"".equals(contact_st)) {
            criterion += " p11.contract_st=? and ";
            parameters.add(contact_st);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += "  p10.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p10.card_num like ? and ";
            parameters.add("%" + cardNum + "%");
          }
          if (loanMoneyB != null && !"".equals(loanMoneyB)) {
            criterion += " p11.loan_money>=? and ";
            parameters.add(loanMoneyB);
          }
          if (loanMoneyE != null && !"".equals(loanMoneyE)) {
            criterion += " p11.loan_money<=? and ";
            parameters.add(loanMoneyE);
          }
          if (loanLimitB != null && !"".equals(loanLimitB)) {
            criterion += " to_number(p11.loan_time_limit)>=? and ";
            parameters.add(loanLimitB);
          }
          if (loanLimitE != null && !"".equals(loanLimitE)) {
            criterion += " to_number(p11.loan_time_limit)<=? and ";
            parameters.add(loanLimitE);
          }
          if (loanStartDateB != null && !"".equals(loanStartDateB)) {
            criterion += " to_number(p11.loan_start_date)>=? and ";
            parameters.add(loanStartDateB);
          }
          if (loanStartDateE != null && !"".equals(loanStartDateE)) {
            criterion += " to_number(p11.loan_start_date)<=? and ";
            parameters.add(loanStartDateE);
          }
          if (ageB != null && !"".equals(ageB)) {
            criterion += " p10.age>=? and ";
            parameters.add(ageB);
          }
          if (ageE != null && !"".equals(ageE)) {
            criterion += " p10.age<=? and ";
            parameters.add(ageE);
          }

          if (orgId != null && !"".equals(orgId)) {
            criterion += " p10.org_id=? and ";
            parameters.add(new Integer(orgId));
          }
          if (houseType != null && !"".equals(houseType)) {
            criterion += " p14.house_type=? and ";
            parameters.add(houseType);
          }
          if (loanType != null && !"".equals(loanType) && "0".equals(loanType)) {
            criterion += " p10.loantype=1 and ";
          }
          if (loanType != null && !"".equals(loanType) && "1".equals(loanType)) {
            criterion += " p10.loantype=0 and ";
          }
          if (areaB != null && !"".equals(areaB)) {
            criterion += " (p14.bargainor_house_area>=? or p14.house_area>=?) and ";
            parameters.add(new BigDecimal(areaB));
            parameters.add(new BigDecimal(areaB));
          }
          if (areaE != null && !"".equals(areaE)) {
            criterion += " (p14.bargainor_house_area<=? or p14.house_area<=?) and ";
            parameters.add(new BigDecimal(areaE));
            parameters.add(new BigDecimal(areaE));
          }
          // -------------------------------------------
          if (eare != null && !"".equals(eare)) {
            criterion += " (p14.bargainor_house_area=? or p14.house_area=?) and ";
            try {
              parameters.add(new BigDecimal(eare));
              parameters.add(new BigDecimal(eare));
            } catch (NumberFormatException e) {
              parameters.add(new BigDecimal("-10000"));
              parameters.add(new BigDecimal("-10000"));
            }
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p11.contract_id ";

          String od = orderother;
          if (od == null)
            od = "DESC";
          String groupBy = " p11.contract_id," + " p11.loan_kou_acc,"
              + " p10.borrower_name," + " p10.card_num," + " p11.loan_bank_id,"
              + " p11.loan_money," + " p11.loan_time_limit,"
              + " p11.loan_start_date," + " p11.contract_st,"
              + " p11.ovaer_loan_repay," + " p11.no_back_money,"
              + " p11.bail_balance," + " p11.loan_repay_day,"
              + " p11.overplus_limite, " + " p10.org_id," + " p10.org_name,"
              + " nvl(p14.bargainor_house_area,0)+nvl(p14.house_area,0),"
              + " p11.overplus_loan_money";
          hql = hql + criterion + " group by " + groupBy + " order by " + ob
              + " " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = new LoandeskaccqueryTaDTO();
              if (obj[0] != null) {
                loandeskaccqueryTaDTO.setId(obj[0].toString());
              }
              if (obj[0] != null) {
                loandeskaccqueryTaDTO.setContactid(obj[0].toString());
              }
              if (obj[1] != null) {
                loandeskaccqueryTaDTO.setLoankouacc(obj[1].toString());
              }
              if (obj[2] != null) {
                loandeskaccqueryTaDTO.setBorrowername(obj[2].toString());
              }
              if (obj[3] != null) {
                loandeskaccqueryTaDTO.setCardnum(obj[3].toString());
              }
              if (obj[4] != null) {
                loandeskaccqueryTaDTO.setPaybank(obj[4].toString());
              }
              if (obj[5] != null) {
                loandeskaccqueryTaDTO.setLoanmoney(obj[5].toString());
              }
              if (obj[6] != null) {
                loandeskaccqueryTaDTO.setLoanlimit(obj[6].toString());
              }
              if (obj[7] != null) {
                loandeskaccqueryTaDTO.setLoanstartdate(obj[7].toString());
              }
              if (obj[8] != null) {
                loandeskaccqueryTaDTO.setContact_st(obj[8].toString());
              }
              if (obj[9] != null) {
                loandeskaccqueryTaDTO.setOvareloanrepay(obj[9].toString());
              }
              if (obj[10] != null) {
                loandeskaccqueryTaDTO.setNobackmoney(obj[10].toString());
              }
              if (obj[11] != null) {
                loandeskaccqueryTaDTO.setBallbalance(obj[11].toString());
              }
              if (obj[12] != null) {
                loandeskaccqueryTaDTO.setPayday(obj[12].toString());
              }
              if (obj[13] != null) {
                loandeskaccqueryTaDTO.setOverlimited(obj[13].toString());
              }
              if (obj[14] != null) {
                loandeskaccqueryTaDTO.setRealcorpus(obj[14].toString());
              }
              if (obj[15] != null) {
                loandeskaccqueryTaDTO.setRealinterest(obj[15].toString());
              }
              if (obj[16] != null) {
                loandeskaccqueryTaDTO.setRealpunishinterest(obj[16].toString());
              }
              if (obj[17] != null) {
                loandeskaccqueryTaDTO.setOrgId(obj[17].toString());
              }
              if (obj[18] != null) {
                loandeskaccqueryTaDTO.setOrgName(obj[18].toString());
              }
              if (obj[19] != null) {
                loandeskaccqueryTaDTO.setAreaFrist(new BigDecimal(obj[19]
                    .toString()));
              }
              if (obj[20] != null) {
                loandeskaccqueryTaDTO.setLoanBalance(obj[20].toString());
              }
              loandeskaccqueryTaDTO
                  .setIsloanPay(obj[21].toString().equals("1") ? "是" : "否");
              if (obj[22] != null) {
                loandeskaccqueryTaDTO.setMonthpay(obj[22].toString());
              } else {
                loandeskaccqueryTaDTO.setMonthpay("0");
              }
              temp_list.add(loandeskaccqueryTaDTO);
            }
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
   * hanl 求出不带分页的台帐
   */
  public Vector showLoandeskaccqueryallList(final String offic,
      final String payBank, final String developer, final String floorId,
      final String contactid, final String loankouacc,
      final String assistantorg, final String contact_st,
      final String borrowerName, final String cardNum, final String loanMoneyB,
      final String loanMoneyE, final String loanLimitB,
      final String loanLimitE, final String loanStartDateB,
      final String loanStartDateE, final String ageB, final String ageE,
      final SecurityInfo securityInfo, final String orgId, final String eare,
      final String loupan, final String loupanhao, final String cengcishihao,
      final String areaB, final String areaE, final String houseType,
      final String loanType) {
    Vector list = new Vector();
    try {

      list = (Vector) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select p11.contract_id,"
                  + " p11.loan_kou_acc,"
                  + " p10.borrower_name,"
                  + " p10.card_num,"
                  + " p11.loan_bank_id,"
                  + " p11.loan_money,p11.loan_time_limit,p11.loan_start_date,p11.contract_st,p11.ovaer_loan_repay,"
                  + " p11.no_back_money,p11.bail_balance,p11.loan_repay_day,p11.overplus_limite, "
                  + " tt.corpus,tt.interest,tt.punish,p10.org_id,p10.org_name,p14.bargainor_house_area,"
                  + " p14.house_area,"
                  + " nvl(p14.house_area,0)+nvl(p14.bargainor_house_area,0) as mianji,"
                  + // 21
                  " nvl(p14.house_totle_price,0)+nvl(p14.bargainor_totle_price,0) as fangwuzongjia,"
                  + // 22
                  " p11.Overplus_Loan_Money as yue,"
                  + // 23
                  " p10.other_arrearage"
                  + " from (select p203.contract_id as contractid,sum(p203.real_corpus) "
                  + " as corpus,sum(p203.real_interest) as interest,sum(p203.real_punish_interest) "
                  + " as punish from pl203 p203, pl111 p111,pl202 p202 where p203.contract_id = p111.contract_id and p202.biz_st='6' and p202.flow_head_id=p203.flow_head_id "
                  + " group by p203.contract_id) tt,pl110 p10,pl111 p11,pl114 p14 "
                  + " where p10.contract_id=p11.contract_id  and p11.contract_id=tt.contractid "
                  + " and p11.contract_st in('11','12','13') and p14.contract_id=p10.contract_id and p11.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              Vector parameters = new Vector();
              String criterion = "";
              String criterion1 = "";
              String sql1 = "select p6.floor_id from pl006 p6";
              if (offic != null && !"".equals(offic)) {
                criterion += " p10.office=? and ";
                parameters.add(offic);
              }
              if (payBank != null && !"".equals(payBank)) {
                criterion += " p11.loan_bank_id =? and ";
                parameters.add(payBank);
              }
              if (developer != null && !"".equals(developer)) {
                criterion += " p14.head_id = ? and ";
                criterion1 += " p6.head_id = '" + developer + "' and ";
                parameters.add(developer);
              }
              if (loupan != null && !"".equals(loupan)) {
                criterion1 += " p6.floor_name = '" + loupan + "' and ";
              }
              if (floorId != null && !"".equals(floorId)
                  && !"all".equals(floorId)) {
                criterion += " p14.floor_id = ? and ";
                parameters.add(floorId);
              } else if (criterion1.length() != 0) {
                criterion += " p14.floor_id in (" + sql1 + " where "
                    + criterion1.substring(0, criterion1.lastIndexOf("and"))
                    + ") and ";
              }
              if (loupanhao != null && !"".equals(loupanhao)) {
                criterion += " p14.floor_num = ? and ";
                parameters.add(loupanhao);
              }
              if (loanType != null && !"".equals(loanType)
                  && "0".equals(loanType)) {
                criterion += " p10.loantype=1 and ";
              }
              if (loanType != null && !"".equals(loanType)
                  && "1".equals(loanType)) {
                criterion += " p10.loantype=0 and ";
              }
              if (cengcishihao != null && !"".equals(cengcishihao)) {
                criterion += " p14.room_num = ? and ";
                parameters.add(cengcishihao);
              }
              if (contactid != null && !"".equals(contactid)) {
                criterion += " p11.contract_id like ? and ";
                parameters.add("%" + contactid + "%");
              }
              if (loankouacc != null && !"".equals(loankouacc)) {
                criterion += " p11.loan_kou_acc=? and ";
                parameters.add(loankouacc);
              }
              if (assistantorg != null && !"".equals(assistantorg)) {
                criterion += " p11.contract_id in (select p120.contract_id from pl120 p120 where p120.assistant_org_id=?) and ";
                parameters.add(assistantorg);
              }
              if (contact_st != null && !"".equals(contact_st)) {
                criterion += " p11.contract_st=? and ";
                parameters.add(contact_st);
              }
              if (borrowerName != null && !"".equals(borrowerName)) {
                criterion += "  p10.borrower_name like ? and ";
                parameters.add("%" + borrowerName + "%");
              }
              if (cardNum != null && !"".equals(cardNum)) {
                criterion += " p10.card_num like ? and ";
                parameters.add("%" + cardNum + "%");
              }
              if (loanMoneyB != null && !"".equals(loanMoneyB)) {
                criterion += " p11.loan_money>=? and ";
                parameters.add(loanMoneyB);
              }
              if (loanMoneyE != null && !"".equals(loanMoneyE)) {
                criterion += " p11.loan_money<=? and ";
                parameters.add(loanMoneyE);
              }
              if (loanLimitB != null && !"".equals(loanLimitB)) {
                criterion += " p11.loan_time_limit>=? and ";
                parameters.add(loanLimitB);
              }
              if (loanLimitE != null && !"".equals(loanLimitE)) {
                criterion += " p11.loan_time_limit<=? and ";
                parameters.add(loanLimitE);
              }
              if (loanStartDateB != null && !"".equals(loanStartDateB)) {
                criterion += " p11.loan_start_date>=? and ";
                parameters.add(loanStartDateB);
              }
              if (loanStartDateE != null && !"".equals(loanStartDateE)) {
                criterion += " p11.loan_start_date<=? and ";
                parameters.add(loanStartDateE);
              }
              if (ageB != null && !"".equals(ageB)) {
                criterion += " p10.age>=? and ";
                parameters.add(ageB);
              }
              if (ageE != null && !"".equals(ageE)) {
                criterion += " p10.age<=? and ";
                parameters.add(ageE);
              }

              if (orgId != null && !"".equals(orgId)) {
                criterion += " p10.org_id=? and ";
                parameters.add(new Integer(orgId));
              }
              if (houseType != null && !"".equals(houseType)) {
                criterion += " p14.house_type=? and ";
                parameters.add(houseType);
              }

              if (areaB != null && !"".equals(areaB)) {
                criterion += " (p14.bargainor_house_area>=? or p14.house_area>=?) and ";
                parameters.add(new BigDecimal(areaB));
                parameters.add(new BigDecimal(areaB));
              }
              if (areaE != null && !"".equals(areaE)) {
                criterion += " (p14.bargainor_house_area<=? or p14.house_area<=?) and ";
                parameters.add(new BigDecimal(areaE));
                parameters.add(new BigDecimal(areaE));
              }
              // -------------------------------------------
              if (eare != null && !"".equals(eare)) {
                criterion += " (p14.bargainor_house_area=? or p14.house_area=?) and ";
                try {
                  parameters.add(new BigDecimal(eare));
                  parameters.add(new BigDecimal(eare));
                } catch (NumberFormatException exx) {
                  parameters.add(new BigDecimal("-10000"));
                  parameters.add(new BigDecimal("-10000"));
                }
              }

              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);

              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator it = query.list().iterator();
              Vector temp_list = null;
              temp_list = new Vector();
              Object obj[] = null;
              while (it.hasNext()) {
                obj = (Object[]) it.next();
                if (obj != null) {
                  LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = new LoandeskaccqueryTaDTO();
                  if (obj[0] != null) {
                    loandeskaccqueryTaDTO.setId(obj[0].toString());
                  }
                  if (obj[0] != null) {
                    loandeskaccqueryTaDTO.setContactid(obj[0].toString());
                  }
                  if (obj[1] != null) {
                    loandeskaccqueryTaDTO.setLoankouacc(obj[1].toString());
                  }
                  if (obj[2] != null) {
                    loandeskaccqueryTaDTO.setBorrowername(obj[2].toString());
                  }
                  if (obj[3] != null) {
                    loandeskaccqueryTaDTO.setCardnum(obj[3].toString());
                  }
                  if (obj[4] != null) {
                    loandeskaccqueryTaDTO.setPaybank(obj[4].toString());
                  }
                  if (obj[5] != null) {
                    loandeskaccqueryTaDTO.setLoanmoney(obj[5].toString());
                  }
                  if (obj[6] != null) {
                    loandeskaccqueryTaDTO.setLoanlimit(obj[6].toString());
                  }
                  if (obj[7] != null) {
                    loandeskaccqueryTaDTO.setLoanstartdate(obj[7].toString());
                  }
                  if (obj[8] != null) {
                    loandeskaccqueryTaDTO.setContact_st(obj[8].toString());
                  }
                  if (obj[9] != null) {
                    loandeskaccqueryTaDTO.setOvareloanrepay(obj[9].toString());
                  }
                  if (obj[10] != null) {
                    loandeskaccqueryTaDTO.setNobackmoney(obj[10].toString());
                  }
                  if (obj[11] != null) {
                    loandeskaccqueryTaDTO.setBallbalance(obj[11].toString());
                  }
                  if (obj[12] != null) {
                    loandeskaccqueryTaDTO.setLoanRepayDay(obj[12].toString());
                  }
                  if (obj[13] != null) {
                    loandeskaccqueryTaDTO.setOverlimited(obj[13].toString());
                  }
                  if (obj[14] != null) {
                    loandeskaccqueryTaDTO.setRealcorpus(obj[14].toString());
                  }
                  if (obj[15] != null) {
                    loandeskaccqueryTaDTO.setRealinterest(obj[15].toString());
                  }
                  if (obj[16] != null) {
                    loandeskaccqueryTaDTO.setRealpunishinterest(obj[16]
                        .toString());
                  }
                  if (obj[17] != null) {
                    loandeskaccqueryTaDTO.setOrgId(obj[17].toString());
                  }
                  if (obj[18] != null) {
                    loandeskaccqueryTaDTO.setOrgName(obj[18].toString());
                  }
                  if (obj[21] != null && !"".equals(obj[21].toString())) {
                    loandeskaccqueryTaDTO.setAreaFrist(new BigDecimal(obj[21]
                        .toString()));
                  }
                  if (obj[12] != null) {
                    loandeskaccqueryTaDTO.setPayday(obj[12].toString());
                  }
                  if (obj[13] != null) {
                    loandeskaccqueryTaDTO.setOverlimited(obj[13].toString());
                  }
                  if (obj[21] != null && !"".equals(obj[21].toString())) {
                    loandeskaccqueryTaDTO.setMinaji(new BigDecimal(obj[21]
                        .toString()));
                  }
                  if (obj[22] != null && !"".equals(obj[22].toString())) {
                    loandeskaccqueryTaDTO.setFangwuzongjia(new BigDecimal(
                        obj[22].toString()));
                  }
                  if (obj[23] != null && !"".equals(obj[23].toString())) {
                    loandeskaccqueryTaDTO.setDaikyue(new BigDecimal(obj[23]
                        .toString()));
                  }
                  if (obj[24] != null && !"".equals(obj[24].toString())) {
                    loandeskaccqueryTaDTO.setOtherArrearage(obj[24].toString());
                  }
                  loandeskaccqueryTaDTO.setSumCorpusInterest(new BigDecimal(
                      loandeskaccqueryTaDTO.getRealcorpus()).add(
                      new BigDecimal(loandeskaccqueryTaDTO.getRealinterest()))
                      .add(
                          new BigDecimal(loandeskaccqueryTaDTO
                              .getRealpunishinterest())));
                  temp_list.add(loandeskaccqueryTaDTO);
                }

              }
              return temp_list;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List showLoandeskaccqueryallList(final String offic,
      final String payBank, final String developer, final String floorId,
      final String contactid, final String loankouacc,
      final String assistantorg, final String contact_st,
      final String borrowerName, final String cardNum, final String loanMoneyB,
      final String loanMoneyE, final String loanLimitB,
      final String loanLimitE, final String loanStartDateB,
      final String loanStartDateE, final String ageB, final String ageE,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p11.contract_id,p11.loan_kou_acc,p10.borrower_name,p10.card_num,p11.loan_bank_id,p11.loan_money,p11.loan_time_limit,p11.loan_start_date,p11.contract_st,p11.ovaer_loan_repay,p11.no_back_money,p11.bail_balance,p11.loan_repay_day,p11.overplus_limite, tt.corpus,tt.interest,tt.punish from (select p203.contract_id as contractid,sum(p203.real_corpus) as corpus,sum(p203.real_interest) as interest,sum(p203.real_punish_interest) as punish from pl203 p203, pl111 p111 where p203.contract_id = p111.contract_id group by p203.contract_id) tt,pl110 p10,pl111 p11 where p10.contract_id=p11.contract_id  and p11.contract_id=tt.contractid and p11.contract_st in('11','12') and p11.loan_bank_id "
              + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";
          if (offic != null && !"".equals(offic)) {
            criterion += " p10.office=? and ";
            parameters.add(offic);
          }
          if (payBank != null && !"".equals(payBank)) {
            criterion += " p11.loan_bank_id =? and ";
            parameters.add(payBank);
          }
          if (developer != null && !"".equals(developer)) {
            criterion += " p11.contract_id in(select p114.contract_id from pl114 p114, pl006 p006 where p006.head_id = p114.head_id and p114.head_id =? and p114.floor_id = ? ) and ";
            parameters.add(developer);
            parameters.add(floorId);
          }
          if (contactid != null && !"".equals(contactid)) {
            criterion += " p11.contract_id=? and ";
            parameters.add(contactid);
          }
          if (loankouacc != null && !"".equals(loankouacc)) {
            criterion += " p11.loan_kou_acc=? and ";
            parameters.add(loankouacc);
          }
          if (assistantorg != null && !"".equals(assistantorg)) {
            criterion += " p11.contract_id in (select p120.contract_id from pl120 p120 where p120.assistant_org_id=?) and ";
            parameters.add(assistantorg);
          }
          if (contact_st != null && !"".equals(contact_st)) {
            criterion += " p11.contract_st=? and ";
            parameters.add(contact_st);
          }

          // 改为模糊查询//2008-1-14//吴洪涛
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += "  p10.borrower_name like ? and ";

            parameters.add("%" + borrowerName + "%");
          }
          // 改为模糊查询//2008-1-14//吴洪涛

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += "( p10.card_num =? or p10.standby_card_num = ? ) and ";
            parameters.add(cardNum);
            parameters.add(cardNum);
          }
          if (loanMoneyB != null && !"".equals(loanMoneyB)) {
            criterion += " p11.loan_money>=? and ";
            parameters.add(loanMoneyB);
          }
          if (loanMoneyE != null && !"".equals(loanMoneyE)) {
            criterion += " p11.loan_money<=? and ";
            parameters.add(loanMoneyE);
          }
          if (loanLimitB != null && !"".equals(loanLimitB)) {
            criterion += " p11.loan_time_limit>=? and ";
            parameters.add(loanLimitB);
          }
          if (loanLimitE != null && !"".equals(loanLimitE)) {
            criterion += " p11.loan_time_limit<=? and ";
            parameters.add(loanLimitE);
          }
          if (loanStartDateB != null && !"".equals(loanStartDateB)) {
            criterion += " p11.loan_start_date>=? and ";
            parameters.add(loanStartDateB);
          }
          if (loanStartDateE != null && !"".equals(loanStartDateE)) {
            criterion += " p11.loan_start_date<=? and ";
            parameters.add(loanStartDateE);
          }
          if (ageB != null && !"".equals(ageB)) {
            criterion += " p10.age>=? and ";
            parameters.add(ageB);
          }
          if (ageE != null && !"".equals(ageE)) {
            criterion += " p10.age<=? and ";
            parameters.add(ageE);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = new LoandeskaccqueryTaDTO();
              if (obj[0] != null) {
                loandeskaccqueryTaDTO.setId(obj[0].toString());
              }
              if (obj[0] != null) {
                loandeskaccqueryTaDTO.setContactid(obj[0].toString());
              }
              if (obj[1] != null) {
                loandeskaccqueryTaDTO.setLoankouacc(obj[1].toString());
              }
              if (obj[2] != null) {
                loandeskaccqueryTaDTO.setBorrowername(obj[2].toString());
              }
              if (obj[3] != null) {
                loandeskaccqueryTaDTO.setCardnum(obj[3].toString());
              }
              if (obj[4] != null) {
                loandeskaccqueryTaDTO.setPaybank(obj[4].toString());
              }
              if (obj[5] != null) {
                loandeskaccqueryTaDTO.setLoanmoney(obj[5].toString());
              }
              if (obj[6] != null) {
                loandeskaccqueryTaDTO.setLoanlimit(obj[6].toString());
              }
              if (obj[7] != null) {
                loandeskaccqueryTaDTO.setLoanstartdate(obj[7].toString());
              }
              if (obj[8] != null) {
                loandeskaccqueryTaDTO.setContact_st(obj[8].toString());
              }
              if (obj[9] != null) {
                loandeskaccqueryTaDTO.setOvareloanrepay(obj[9].toString());
              }
              if (obj[10] != null) {
                loandeskaccqueryTaDTO.setNobackmoney(obj[10].toString());
              }
              if (obj[11] != null) {
                loandeskaccqueryTaDTO.setBallbalance(obj[11].toString());
              }
              if (obj[14] != null) {
                loandeskaccqueryTaDTO.setRealcorpus(obj[14].toString());
              }
              if (obj[15] != null) {
                loandeskaccqueryTaDTO.setRealinterest(obj[15].toString());
              }
              if (obj[16] != null) {
                loandeskaccqueryTaDTO.setRealpunishinterest(obj[16].toString());
              }
              if (obj[12] != null) {
                loandeskaccqueryTaDTO.setPayday(obj[12].toString());
              }
              if (obj[13] != null) {
                loandeskaccqueryTaDTO.setOverlimited(obj[13].toString());
              }
              temp_list.add(loandeskaccqueryTaDTO);
            }

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
   * author wsh 查询合同编号是否在用户权限下 扣款账号修改
   * 
   * @param contractId 合同编号
   * @param loanBankList 用户权限银行
   * @return
   */
  public Integer queryIdExist_wsh(final String contractId,
      final List loanBankList) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.contract_id= ?  and a.contract_st in(7,8,11,12,13) ";
            Vector parameters = new Vector();
            String criterion = "";
            if (contractId != null) {
              parameters.add(contractId);
            }
            if (loanBankList != null && loanBankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanBankList.size(); i++) {
                criterion += " a.loan_bank_id = ? or ";
                parameters.add(loanBankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;

  }

  /**
   * author shiy 查询贷款帐号是否存在
   * 
   * @param newLoanKouAcc
   * @return
   */
  public Integer findBorrowerAccByLoanKouAcc_sy(final String loanKouAcc) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.loan_kou_acc= ? and a.contract_st <> 12";
            Query query = session.createSQLQuery(hql);
            query.setString(0, loanKouAcc);
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }

  /**
   * author wsh 查询新扣款帐号是否重复
   * 
   * @param newLoanKouAcc
   * @return
   */
  public Integer findBorrowerAccByLoanKouAcc_wsh(final String newLoanKouAcc) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.loan_kou_acc= ? and a.contract_st!=12 and a.contract_st!=13 ";
            Vector parameters = new Vector();
            if (newLoanKouAcc != null) {
              parameters.add(newLoanKouAcc);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }

  /**
   * author wsh 查询合同编号是否在用户权限下 划款账号修改
   * 
   * @param contractId 合同编号
   * @param loanBankList 用户权限银行
   * @return
   */
  public Integer queryIdExist1_wsh(final String contractId,
      final List loanBankList) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.contract_id= ? and a.contract_st in(1,2,3,4,5,6,9,10) ";
            Vector parameters = new Vector();
            String criterion = "";
            if (contractId != null) {
              parameters.add(contractId);
            }
            if (loanBankList != null && loanBankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanBankList.size(); i++) {
                criterion += " a.loan_bank_id = ? or ";
                parameters.add(loanBankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;
  }

  /**
   * author wsh 查询合同编号是否在用户权限下 下达发放通知书
   * 
   * @param contractId 合同编号
   * @param loanBankList 用户权限银行
   * @return
   */
  public Integer queryIdExist2_wsh(final String contractId,
      final List loanBankList) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.contract_id= ? and a.contract_st=14 ";
            Vector parameters = new Vector();
            String criterion = "";
            if (contractId != null) {
              parameters.add(contractId);
            }
            if (loanBankList != null && loanBankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanBankList.size(); i++) {
                criterion += " a.loan_bank_id = ? or ";
                parameters.add(loanBankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;

  }

  public Integer queryIdwsh(final String contractId) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(pl120.contract_id) from pl120 where pl120.contract_id=? ";
            Vector parameters = new Vector();

            if (contractId != null) {
              parameters.add(contractId);
            }

            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;

  }

  /**
   * Description 五级分类统计查询列表
   * 
   * @author wangy 2007-10-22
   * @param 根据条件查询正常的记录
   * @param 由FiveLevelQueryBS调用
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorId
   * @param assistantOrgId
   * @param securityInfo
   * @return List
   */
  public List queryFiveLevelQueryListByCriterionsNormal(
      final String bizYearMonth, final String bizDay, final String officeCode,
      final String loanBankId, final String developerId, final String floorId,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractId), nvl(sum(overplusLoanMoney), 0) "
              + " from (select p111.contract_id as contractId, "
              + " nvl(p111.overplus_loan_money, 0) as overplusLoanMoney "
              + " from pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";
          String subhql = "";

          if (!bizYearMonth.equals("") && !bizDay.equals("")) {
            subhql = " where p111.contract_st = 11 "
                + " and ((((months_between(add_months(to_date(?, 'yyyymm'), -1), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 < "
                + " (select to_number(p003.param_explain) "
                + " from pl003 p003 " + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' " + " and p003.param_value = '2' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) and "
                + " ? <= p111.loan_repay_day) "
                + " or (((months_between(to_date(?, 'yyyymm'), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 < "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' " + " and p003.param_value = '2' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) and "
                + " ? >= p111.loan_repay_day)) " + " and p111.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
            parameters.add(bizYearMonth);
            parameters.add(bizDay);
            parameters.add(bizYearMonth);
            parameters.add(bizDay);
          }

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110 "
                + subhql
                + " and p111.contract_id = p110.contract_id and p110.office = ? )";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += subhql + " and p111.loan_bank_id = ? )";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.head_id = ? )";
            parameters.add(developerId);
          }

          if (floorId != null && !floorId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.floor_id = ? )";
            parameters.add(floorId);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 "
                + subhql
                + " and p111.contract_id = p120.contract_id and p120.assistant_org_id = ? )";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          FiveLevelQueryDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new FiveLevelQueryDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setNormalCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setNormalValue(new BigDecimal(obj[1].toString()));
            tempList.add(dto);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Description 五级分类统计查询列表
   * 
   * @author wangy 2007-10-22
   * @param 根据条件查询关注的记录
   * @param 由FiveLevelQueryBS调用
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorId
   * @param assistantOrgId
   * @param securityInfo
   * @return List
   */
  public List queryFiveLevelQueryListByCriterionsAttention(
      final String bizYearMonth, final String bizDay, final String officeCode,
      final String loanBankId, final String developerId, final String floorId,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractId), nvl(sum(overplusLoanMoney), 0) "
              + " from (select p111.contract_id as contractId, "
              + " nvl(p111.overplus_loan_money, 0) as overplusLoanMoney "
              + " from pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";
          String subhql = "";

          if (!bizYearMonth.equals("") && !bizDay.equals("")) {
            subhql = " where p111.contract_st = 11 "
                + " and ((((months_between(add_months(to_date(?, 'yyyymm'), -1), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 >= "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' and p003.param_value = '2' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) and "
                + " ((months_between(add_months(to_date(?, 'yyyymm'), -1), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 < "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6'  and p003.param_value = '3' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) "
                + " and ? <= p111.loan_repay_day ) or "
                + " (((months_between(to_date(?, 'yyyymm'), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 >= "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' and p003.param_value = '2' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) and "
                + " ((months_between(to_date(?, 'yyyymm'), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 < "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' and p003.param_value = '3' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) "
                + " and ? > p111.loan_repay_day )) "
                + " and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();
            parameters.add(bizYearMonth);
            parameters.add(bizYearMonth);
            parameters.add(bizDay);
            parameters.add(bizYearMonth);
            parameters.add(bizYearMonth);
            parameters.add(bizDay);
          }

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110 "
                + subhql
                + " and p111.contract_id = p110.contract_id and p110.office = ? )";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += subhql + " and p111.loan_bank_id = ? )";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.head_id = ? )";
            parameters.add(developerId);
          }

          if (floorId != null && !floorId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.floor_id = ? )";
            parameters.add(floorId);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 "
                + subhql
                + " and p111.contract_id = p120.contract_id and p120.assistant_org_id = ? )";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          FiveLevelQueryDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new FiveLevelQueryDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setAttentionCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setAttentionValue(new BigDecimal(obj[1].toString()));
            tempList.add(dto);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Description 五级分类统计查询列表
   * 
   * @author wangy 2007-10-22
   * @param 根据条件查询次级的记录
   * @param 由FiveLevelQueryBS调用
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorId
   * @param assistantOrgId
   * @param securityInfo
   * @return List
   */
  public List queryFiveLevelQueryListByCriterionsSecondary(
      final String bizYearMonth, final String bizDay, final String officeCode,
      final String loanBankId, final String developerId, final String floorId,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractId), nvl(sum(overplusLoanMoney), 0) "
              + " from (select p111.contract_id as contractId, "
              + " nvl(p111.overplus_loan_money, 0) as overplusLoanMoney "
              + " from pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";
          String subhql = "";

          if (!bizYearMonth.equals("") && !bizDay.equals("")) {
            subhql = " where p111.contract_st = 11 "
                + " and ((((months_between(add_months(to_date(?, 'yyyymm'), -1), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 >= "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' and p003.param_value = '3' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) and "
                + " ((months_between(add_months(to_date(?, 'yyyymm'), -1), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 < "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6'  and p003.param_value = '4' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) "
                + " and ? <= p111.loan_repay_day ) or "
                + " (((months_between(to_date(?, 'yyyymm'), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 >= "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' and p003.param_value = '3' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) and "
                + " ((months_between(to_date(?, 'yyyymm'), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 < "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' and p003.param_value = '4' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) "
                + " and ? > p111.loan_repay_day )) "
                + " and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();
            parameters.add(bizYearMonth);
            parameters.add(bizYearMonth);
            parameters.add(bizDay);
            parameters.add(bizYearMonth);
            parameters.add(bizYearMonth);
            parameters.add(bizDay);
          }

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110 "
                + subhql
                + " and p111.contract_id = p110.contract_id and p110.office = ? )";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += subhql + " and p111.loan_bank_id = ? )";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.head_id = ? )";
            parameters.add(developerId);
          }

          if (floorId != null && !floorId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.floor_id = ? )";
            parameters.add(floorId);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 "
                + subhql
                + " and p111.contract_id = p120.contract_id and p120.assistant_org_id = ? )";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          FiveLevelQueryDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new FiveLevelQueryDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setSecondaryCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setSecondaryValue(new BigDecimal(obj[1].toString()));
            tempList.add(dto);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Description 五级分类统计查询列表
   * 
   * @author wangy 2007-10-22
   * @param 根据条件查询可疑的记录
   * @param 由FiveLevelQueryBS调用
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorId
   * @param assistantOrgId
   * @param securityInfo
   * @return List
   */
  public List queryFiveLevelQueryListByCriterionsShadiness(
      final String bizYearMonth, final String bizDay, final String officeCode,
      final String loanBankId, final String developerId, final String floorId,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractId), nvl(sum(overplusLoanMoney), 0) "
              + " from (select p111.contract_id as contractId, "
              + " nvl(p111.overplus_loan_money, 0) as overplusLoanMoney "
              + " from pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";
          String subhql = "";

          if (!bizYearMonth.equals("") && !bizDay.equals("")) {
            subhql = " where p111.contract_st = 11 "
                + " and ((((months_between(add_months(to_date(?, 'yyyymm'), -1), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 >= "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' and p003.param_value = '4' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) and "
                + " ((months_between(add_months(to_date(?, 'yyyymm'), -1), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 < "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6'  and p003.param_value = '5' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) "
                + " and ? <= p111.loan_repay_day ) or "
                + " (((months_between(to_date(?, 'yyyymm'), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 >= "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' and p003.param_value = '4' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) and "
                + " ((months_between(to_date(?, 'yyyymm'), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 < "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' and p003.param_value = '5' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) "
                + " and ? > p111.loan_repay_day )) "
                + " and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();
            parameters.add(bizYearMonth);
            parameters.add(bizYearMonth);
            parameters.add(bizDay);
            parameters.add(bizYearMonth);
            parameters.add(bizYearMonth);
            parameters.add(bizDay);
          }

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110 "
                + subhql
                + " and p111.contract_id = p110.contract_id and p110.office = ? )";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += subhql + " and p111.loan_bank_id = ? )";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.head_id = ? )";
            parameters.add(developerId);
          }

          if (floorId != null && !floorId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.floor_id = ? )";
            parameters.add(floorId);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 "
                + subhql
                + " and p111.contract_id = p120.contract_id and p120.assistant_org_id = ? )";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          FiveLevelQueryDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new FiveLevelQueryDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setShadinessCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setShadinessValue(new BigDecimal(obj[1].toString()));
            tempList.add(dto);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Description 五级分类统计查询列表
   * 
   * @author wangy 2007-10-22
   * @param 根据条件查询损失的记录
   * @param 由FiveLevelQueryBS调用
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorId
   * @param assistantOrgId
   * @param securityInfo
   * @return List
   */
  public List queryFiveLevelQueryListByCriterionsDamnify(
      final String bizYearMonth, final String bizDay, final String officeCode,
      final String loanBankId, final String developerId, final String floorId,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractId), nvl(sum(overplusLoanMoney), 0) "
              + " from (select p111.contract_id as contractId, "
              + " nvl(p111.overplus_loan_money, 0) as overplusLoanMoney "
              + " from pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";
          String subhql = "";

          if (!bizYearMonth.equals("") && !bizDay.equals("")) {
            subhql = " where p111.contract_st = 11 "
                + " and ((((months_between(add_months(to_date(?, 'yyyymm'), -1), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 >= "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' and p003.param_value = '5' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) and "
                + " ? <= substr(p111.loan_start_date, 7, 2)) "
                + " or (((months_between(to_date(?, 'yyyymm'), "
                + " to_date(substr(p111.loan_start_date, 0, 6), "
                + " 'yyyymm')) - "
                + " (p111.loan_time_limit - p111.overplus_limite))*30 >= "
                + " (select to_number(p003.param_explain) from pl003 p003 "
                + " where p003.param_type = 'A' "
                + " and p003.param_num = '6' and p003.param_value = '5' "
                + " and p003.loan_bank_id = p111.loan_bank_id)) and "
                + " ? > substr(p111.loan_start_date, 7, 2))) "
                + " and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();
            parameters.add(bizYearMonth);
            parameters.add(bizDay);
            parameters.add(bizYearMonth);
            parameters.add(bizDay);
          }

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110 "
                + subhql
                + " and p111.contract_id = p110.contract_id and p110.office = ? )";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += subhql + " and p111.loan_bank_id = ? )";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.head_id = ? )";
            parameters.add(developerId);
          }

          if (floorId != null && !floorId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.floor_id = ? )";
            parameters.add(floorId);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 "
                + subhql
                + " and p111.contract_id = p120.contract_id and p120.assistant_org_id = ? )";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          FiveLevelQueryDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new FiveLevelQueryDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setDamnifyCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setDamnifyValue(new BigDecimal(obj[1].toString()));
            tempList.add(dto);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Description 五级分类统计查询列表
   * 
   * @author wangy 2007-10-23
   * @param 根据条件查询正常的记录_以银行为主
   * @param 由FiveLevelQueryBS调用
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorId
   * @param assistantOrgId
   * @param securityInfo
   * @return List
   */
  public List queryFiveLevelQueryListByCriterionsNormalBank(
      final String officeCode, final String loanBankId,
      final String developerId, final String floorId,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractId), nvl(sum(overplusLoanMoney), 0) "
              + " from (select p111.contract_id as contractId, "
              + " nvl(p111.overplus_loan_money, 0) as overplusLoanMoney "
              + " from pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";
          String subhql = "";
          subhql = " where p111.contract_st = 11 "
              + " and ((select to_number(p205.owe_month) from pl205 p205 "
              + " where p205.owe_date=(select max(p.owe_date) from pl205 p where p.contract_id = p111.contract_id) and p205.contract_id=p111.contract_id) < "
              + " (select to_number(p003.param_explain) from pl003 p003 "
              + " where p003.param_type = 'A' and p003.param_num = '6' "
              + " and p003.param_value = '2' "
              + " and p003.loan_bank_id = p111.loan_bank_id)) "
              + " and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110 "
                + subhql
                + " and p111.contract_id = p110.contract_id and p110.office = ? )";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += subhql + " and p111.loan_bank_id = ? )";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.head_id = ? )";
            parameters.add(developerId);
          }

          if (floorId != null && !floorId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.floor_id = ? )";
            parameters.add(floorId);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 "
                + subhql
                + " and p111.contract_id = p120.contract_id and p120.assistant_org_id = ? )";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          FiveLevelQueryDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new FiveLevelQueryDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setNormalCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setNormalValue(new BigDecimal(obj[1].toString()));
            tempList.add(dto);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Description 五级分类统计查询列表
   * 
   * @author wangy 2007-10-22
   * @param 根据条件查询关注的记录_以银行为主
   * @param 由FiveLevelQueryBS调用
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorId
   * @param assistantOrgId
   * @param securityInfo
   * @return List
   */
  public List queryFiveLevelQueryListByCriterionsAttentionBank(
      final String officeCode, final String loanBankId,
      final String developerId, final String floorId,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractId), nvl(sum(overplusLoanMoney), 0) "
              + " from (select p111.contract_id as contractId, "
              + " nvl(p111.overplus_loan_money, 0) as overplusLoanMoney "
              + " from pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";
          String subhql = "";
          subhql = " where p111.contract_st = 11 "
              + " and (((select to_number(p205.owe_month) from pl205 p205 "
              + " where p205.owe_date=(select max(p.owe_date) from pl205 p where p.contract_id = p111.contract_id) and p205.contract_id=p111.contract_id) >= "
              + " (select to_number(p003.param_explain) from pl003 p003 "
              + " where p003.param_type = 'A' and p003.param_num = '6' "
              + " and p003.param_value = '2' "
              + " and p003.loan_bank_id = p111.loan_bank_id)) and "
              + " ((select to_number(p205.owe_month) from pl205 p205 "
              + " where p205.owe_date=(select max(p.owe_date) from pl205 p where p.contract_id = p111.contract_id) and p205.contract_id=p111.contract_id) < "
              + " (select to_number(p003.param_explain) from pl003 p003 "
              + " where p003.param_type = 'A' and p003.param_num = '6' "
              + " and p003.param_value = '3' "
              + " and p003.loan_bank_id = p111.loan_bank_id))) "
              + " and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110 "
                + subhql
                + " and p111.contract_id = p110.contract_id and p110.office = ? )";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += subhql + " and p111.loan_bank_id = ? )";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.head_id = ? )";
            parameters.add(developerId);
          }

          if (floorId != null && !floorId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.floor_id = ? )";
            parameters.add(floorId);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 "
                + subhql
                + " and p111.contract_id = p120.contract_id and p120.assistant_org_id = ? )";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          FiveLevelQueryDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new FiveLevelQueryDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setAttentionCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setAttentionValue(new BigDecimal(obj[1].toString()));
            tempList.add(dto);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Description 五级分类统计查询列表
   * 
   * @author wangy 2007-10-23
   * @param 根据条件查询次级的记录_以银行为主
   * @param 由FiveLevelQueryBS调用
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorId
   * @param assistantOrgId
   * @param securityInfo
   * @return List
   */
  public List queryFiveLevelQueryListByCriterionsSecondaryBank(
      final String officeCode, final String loanBankId,
      final String developerId, final String floorId,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractId), nvl(sum(overplusLoanMoney), 0) "
              + " from (select p111.contract_id as contractId, "
              + " nvl(p111.overplus_loan_money, 0) as overplusLoanMoney "
              + " from pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";
          String subhql = "";
          subhql = " where p111.contract_st = 11 "
              + " and (((select to_number(p205.owe_month) from pl205 p205 "
              + " where p205.owe_date=(select max(p.owe_date) from pl205 p where p.contract_id = p111.contract_id) and p205.contract_id=p111.contract_id) >= "
              + " (select to_number(p003.param_explain) from pl003 p003 "
              + " where p003.param_type = 'A' and p003.param_num = '6' "
              + " and p003.param_value = '3' "
              + " and p003.loan_bank_id = p111.loan_bank_id)) and "
              + " ((select to_number(p205.owe_month) from pl205 p205 "
              + " where p205.owe_date=(select max(p.owe_date) from pl205 p where p.contract_id = p111.contract_id) and p205.contract_id=p111.contract_id) < "
              + " (select to_number(p003.param_explain) from pl003 p003 "
              + " where p003.param_type = 'A' and p003.param_num = '6' "
              + " and p003.param_value = '4' "
              + " and p003.loan_bank_id = p111.loan_bank_id))) "
              + " and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110 "
                + subhql
                + " and p111.contract_id = p110.contract_id and p110.office = ? )";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += subhql + " and p111.loan_bank_id = ? )";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.head_id = ? )";
            parameters.add(developerId);
          }

          if (floorId != null && !floorId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.floor_id = ? )";
            parameters.add(floorId);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 "
                + subhql
                + " and p111.contract_id = p120.contract_id and p120.assistant_org_id = ? )";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          FiveLevelQueryDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new FiveLevelQueryDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setSecondaryCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setSecondaryValue(new BigDecimal(obj[1].toString()));
            tempList.add(dto);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Description 五级分类统计查询列表
   * 
   * @author wangy 2007-10-23
   * @param 根据条件查询可疑的记录_以银行为主
   * @param 由FiveLevelQueryBS调用
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorId
   * @param assistantOrgId
   * @param securityInfo
   * @return List
   */
  public List queryFiveLevelQueryListByCriterionsShadinessBank(
      final String officeCode, final String loanBankId,
      final String developerId, final String floorId,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractId), nvl(sum(overplusLoanMoney), 0) "
              + " from (select p111.contract_id as contractId, "
              + " nvl(p111.overplus_loan_money, 0) as overplusLoanMoney "
              + " from pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";
          String subhql = "";
          subhql = " where p111.contract_st = 11 "
              + " and (((select to_number(p205.owe_month) from pl205 p205 "
              + " where p205.owe_date=(select max(p.owe_date) from pl205 p where p.contract_id = p111.contract_id) and p205.contract_id=p111.contract_id) >= "
              + " (select to_number(p003.param_explain) from pl003 p003 "
              + " where p003.param_type = 'A' and p003.param_num = '6' "
              + " and p003.param_value = '4' "
              + " and p003.loan_bank_id = p111.loan_bank_id)) and "
              + " ((select to_number(p205.owe_month) from pl205 p205 "
              + " where p205.owe_date=(select max(p.owe_date) from pl205 p where p.contract_id = p111.contract_id) and p205.contract_id=p111.contract_id) < "
              + " (select to_number(p003.param_explain) from pl003 p003 "
              + " where p003.param_type = 'A' and p003.param_num = '6' "
              + " and p003.param_value = '5' "
              + " and p003.loan_bank_id = p111.loan_bank_id))) "
              + " and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110 "
                + subhql
                + " and p111.contract_id = p110.contract_id and p110.office = ? )";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += subhql + " and p111.loan_bank_id = ? )";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.head_id = ? )";
            parameters.add(developerId);
          }

          if (floorId != null && !floorId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.floor_id = ? )";
            parameters.add(floorId);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 "
                + subhql
                + " and p111.contract_id = p120.contract_id and p120.assistant_org_id = ? )";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          FiveLevelQueryDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new FiveLevelQueryDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setShadinessCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setShadinessValue(new BigDecimal(obj[1].toString()));
            tempList.add(dto);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Description 五级分类统计查询列表
   * 
   * @author wangy 2007-10-23
   * @param 根据条件查询损失的记录_以银行为主
   * @param 由FiveLevelQueryBS调用
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorId
   * @param assistantOrgId
   * @param securityInfo
   * @return List
   */
  public List queryFiveLevelQueryListByCriterionsDamnifyBank(
      final String officeCode, final String loanBankId,
      final String developerId, final String floorId,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractId), nvl(sum(overplusLoanMoney), 0) "
              + " from (select p111.contract_id as contractId, "
              + " nvl(p111.overplus_loan_money, 0) as overplusLoanMoney "
              + " from pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";
          String subhql = "";
          subhql = " where p111.contract_st = 11 "
              + " and ((select to_number(p205.owe_month) from pl205 p205 "
              + " where p205.owe_date=(select max(p.owe_date) from pl205 p where p.contract_id = p111.contract_id) and p205.contract_id=p111.contract_id) >= "
              + " (select to_number(p003.param_explain) from pl003 p003 "
              + " where p003.param_type = 'A' and p003.param_num = '6' "
              + " and p003.param_value = '5' "
              + " and p003.loan_bank_id = p111.loan_bank_id)) "
              + " and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110 "
                + subhql
                + " and p111.contract_id = p110.contract_id and p110.office = ? )";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += subhql + " and p111.loan_bank_id = ? )";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.head_id = ? )";
            parameters.add(developerId);
          }

          if (floorId != null && !floorId.equals("")) {
            criterion += " , pl114 p114 "
                + subhql
                + " and p111.contract_id = p114.contract_id and p114.floor_id = ? )";
            parameters.add(floorId);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 "
                + subhql
                + " and p111.contract_id = p120.contract_id and p120.assistant_org_id = ? )";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          FiveLevelQueryDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new FiveLevelQueryDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setDamnifyCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setDamnifyValue(new BigDecimal(obj[1].toString()));
            tempList.add(dto);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据贷款账号在PL111中查询状态为７、８、11、12、13的记录个数 核销收回 吴迪
   * 
   * @param loanKouAcc
   * @param securityInfo
   * @return List
   */
  public List queryDestoryBackNumByLoanKouAcc_WU(final String loanKouAcc,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select p1.loan_kou_acc" + " from pl111 p1"
              + " where p1.contract_st in (7, 8, 11, 12, 13)"
              + " and p1.loan_kou_acc=?" + " and p1.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setString(0, loanKouAcc);
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
   * 根据贷款账号在PL111、PL110中查询记录 核销收回 吴迪
   * 
   * @param loanKouAcc
   * @param securityInfo
   * @return List
   */
  public DestoryBackTaDTO queryDestoryBackByLoanKouAcc_WU(
      final String loanKouAcc, final SecurityInfo securityInfo) {
    DestoryBackTaDTO destoryBackTaDTO = new DestoryBackTaDTO();
    try {
      destoryBackTaDTO = (DestoryBackTaDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select p111.contract_id as contractId,"
                  + "p110.borrower_name as borrowerName,"
                  + "p110.card_kind as cardKind," + "p110.card_num as cardNum,"
                  + "p111.overplus_loan_money as overplusLoanMoney,"
                  + "p111.loan_mode as loanMode,"
                  + "p111.no_back_money as noBackMoney"
                  + " from pl111 p111, pl110 p110"
                  + " where p111.contract_id = p110.contract_id"
                  + " and p111.loan_kou_acc=?" + " and p111.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              Query query = session.createSQLQuery(sql);
              query.setString(0, loanKouAcc);
              DestoryBackTaDTO destoryBackTaDTO = new DestoryBackTaDTO();
              Object[] obj = (Object[]) query.uniqueResult();

              if (obj[0] != null && !obj[0].equals(""))
                destoryBackTaDTO.setContractId(obj[0].toString());
              if (obj[1] != null && !obj[1].equals(""))
                destoryBackTaDTO.setBorrowerName(obj[1].toString());
              if (obj[2] != null && !obj[2].equals(""))
                destoryBackTaDTO.setCardKind(obj[2].toString());
              if (obj[3] != null && !obj[3].equals(""))
                destoryBackTaDTO.setCardNum(obj[3].toString());
              if (obj[4] != null && !obj[4].equals(""))
                destoryBackTaDTO.setOverplusLoanMoney(new BigDecimal(obj[4]
                    .toString()));
              if (obj[5] != null && !obj[5].equals(""))
                destoryBackTaDTO.setLoanMode(obj[5].toString());
              if (obj[6] != null && !obj[6].equals(""))
                destoryBackTaDTO.setNoBackMoney(new BigDecimal(obj[6]
                    .toString()));
              // 证件类型对应的名称
              try {
                destoryBackTaDTO.setCardKindName(BusiTools.getBusiValue(Integer
                    .parseInt(destoryBackTaDTO.getCardKind()),
                    BusiConst.DOCUMENTSSTATE));
              } catch (Exception e) {
                e.printStackTrace();
              }
              // 还款方式
              try {
                destoryBackTaDTO.setLoanModeName(BusiTools.getBusiValue(Integer
                    .parseInt(destoryBackTaDTO.getLoanMode()),
                    BusiConst.PLRECOVERTYPE));
              } catch (Exception e) {
                e.printStackTrace();
              }
              return destoryBackTaDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return destoryBackTaDTO;
  }

  /**
   * hanl
   * 
   * @param contractIdHl
   * @return
   */
  public LoandeskaccqueryTcDTO findborrowerAccInfo(final String contractIdHl) {
    LoandeskaccqueryTcDTO loandeskaccqueryTcDTO = null;
    try {
      loandeskaccqueryTcDTO = (LoandeskaccqueryTcDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p205.owe_corpus,p205.owe_interest,p205.punish_interest,p205.loan_bank_id from pl205 p205 where  p205.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, contractIdHl);
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
                if (obj[3] != null) {
                  loandeskaccqueryTcDTO.setLoanBankId(obj[3].toString());
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
   * hanl 在根据合同编号，从pl115里求出额度信息里的值贷款金额，贷款期限
   * 
   * @param object
   * @return
   */
  public LoanapplyTeListDTO findLoanmoneyLimitInfo(final String contractIdHl) {
    LoanapplyTeListDTO loanapplyTeListDTO = null;
    try {
      loanapplyTeListDTO = (LoanapplyTeListDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.loan_money,p.loan_time_limit from pl115 p where p.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, contractIdHl);
              LoanapplyTeListDTO loanapplyTeListDTO = new LoanapplyTeListDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanapplyTeListDTO.setLoanmoney(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanapplyTeListDTO.setLoanlimit(obj[1].toString());
                }

              }
              return loanapplyTeListDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanapplyTeListDTO;
  }

  /**
   * 根据条件查询逾期还款信息列表
   * 
   * @author 吴迪 2007-10-23
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   */
  public List queryoverDueinfoQueryShowListByCriterions(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String offic,
      final String loanBankName, final String buyhouseorgid,
      final String floorId, final String assistantorg, final String contractId,
      final String loanKouAcc, final String borrowerName, final String cardNum,
      final String beginoweMonth, final String endoweMonth,
      final String isNoAcquittance) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p111.contract_id," + "p111.loan_kou_acc,"
              + "p110.borrower_name," + "p110.card_kind," + "p110.card_num,"
              + "p110.org_name," + "p110.house_tel," + "p110.home_mobile,"
              + "p110.org_tel," + "p205.owe_corpus," + "p205.owe_interest,"
              + "p205.punish_interest," + "p205.owe_month," + "p110.office,"
              + "p111.loan_bank_id," + "p205.owe_date," + "p205.id"
              + " from pl111 p111,pl110 p110,pl205 p205 "
              + " where p110.contract_id=p111.contract_id" + " and"
              + " p205.contract_id=p111.contract_id "
              + " and   p111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          String criterion = "";
          Vector parameters = new Vector();

          String ob = orderBy;
          if (ob == null)
            ob = " p111.contract_id ";

          String od = order;
          if (od == null)
            od = " DESC";
          if (offic != null && !"".equals(offic)) {
            criterion += " p110.office=? and ";
            parameters.add(offic);
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p111.loan_bank_id =? and ";
            parameters.add(loanBankName);
          }
          if (assistantorg != null && !"".equals(assistantorg)) {
            criterion += " p205.contract_id in (select p120.contract_id from pl120 p120 ,pl007 p007 "
                + " where p120.assistant_org_id=p007.assistant_org_id "
                + " and p007.assistant_org_id=? ) and ";
            parameters.add(assistantorg);
          }
          if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
            criterion += " p205.contract_id in(select p114.contract_id from pl114 p114, pl006 p006 where p006.head_id = p114.head_id and p114.head_id =? and p114.floor_id =? ) and ";
            parameters.add(buyhouseorgid);
            parameters.add(floorId);
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " p111.loan_kou_acc = ? and ";
            parameters.add(loanKouAcc);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num = ? and ";
            parameters.add(cardNum);
          }
          if (beginoweMonth != null && !"".equals(beginoweMonth)) {
            criterion += " p205.owe_month >= to_number( ? ) and ";
            parameters.add(beginoweMonth);
          }
          if (endoweMonth != null && !"".equals(endoweMonth)) {
            criterion += " p205.owe_month <= to_number( ? ) and ";
            parameters.add(endoweMonth.trim());
          }

          if (isNoAcquittance != null && !"".equals(isNoAcquittance)) {
            if (isNoAcquittance.equals("是")) {
              String time = securityInfo.getUserInfo().getPlbizDate();
              criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)<to_date(?,'yyyymmdd') and ";
              parameters.add(time.trim());
            } else {
              String time = securityInfo.getUserInfo().getPlbizDate();
              criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)>=to_date(?,'yyyymmdd') and ";
              parameters.add(time.trim());
            }
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();
          Iterator iterate = queryList.iterator();
          List overDueinfoQueryShowList = new ArrayList();
          Object[] obj = null;
          while (iterate.hasNext()) {
            OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO = new OverDueinfoQueryShowListDTO();
            obj = (Object[]) iterate.next();

            if (obj[0] != null) {
              overDueinfoQueryShowListDTO.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              overDueinfoQueryShowListDTO.setLoanKouAcc(obj[1].toString());
            }
            if (obj[2] != null) {
              overDueinfoQueryShowListDTO.setBorrowerName(obj[2].toString());
            }
            if (obj[3] != null) {
              overDueinfoQueryShowListDTO.setCardKind(obj[3].toString());
            }
            if (obj[4] != null) {
              overDueinfoQueryShowListDTO.setCardNum(obj[4].toString());
            }
            if (obj[5] != null) {
              overDueinfoQueryShowListDTO.setOrgName(obj[5].toString());
            }
            if (obj[6] != null) {
              overDueinfoQueryShowListDTO.setHouseTel(obj[6].toString());
            }
            if (obj[7] != null) {
              overDueinfoQueryShowListDTO.setHomeMobile(obj[7].toString());
            }
            if (obj[8] != null) {
              overDueinfoQueryShowListDTO.setOrgTel(obj[8].toString());
            }
            if (obj[9] != null) {
              overDueinfoQueryShowListDTO.setOweCorpus(new BigDecimal(obj[9]
                  .toString()));
            }
            if (obj[10] != null) {
              overDueinfoQueryShowListDTO.setOweInterest(new BigDecimal(obj[10]
                  .toString()));
            }
            if (obj[11] != null) {
              overDueinfoQueryShowListDTO.setPunishInterest(new BigDecimal(
                  obj[11].toString()));
            }
            if (obj[12] != null) {
              overDueinfoQueryShowListDTO.setOweMonth(obj[12].toString());
            }
            if (obj[15] != null) {
              overDueinfoQueryShowListDTO.setOweDate(obj[15].toString());
            }
            if (obj[16] != null) {
              overDueinfoQueryShowListDTO.setId(obj[16].toString());
            }

            // 证件类型对应的名称
            try {
              overDueinfoQueryShowListDTO.setCardKind(BusiTools.getBusiValue(
                  Integer.parseInt(overDueinfoQueryShowListDTO.getCardKind()),
                  BusiConst.DOCUMENTSSTATE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            overDueinfoQueryShowList.add(overDueinfoQueryShowListDTO);
          }
          return overDueinfoQueryShowList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  public List queryoverDueinfoQueryShowListByCriterions_yg(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String offic,
      final String loanBankName, final String buyhouseorgid,
      final String floorId, final String assistantorg, final String contractId,
      final String loanKouAcc, final String borrowerName, final String cardNum,
      final String beginoweMonth, final String endoweMonth,
      final String isNoAcquittance, final String housetype,
      final String buildAreaMin, final String buildAreaMax,
      final String floorid, final String floorName, final String floorNum,
      final String roomNum, final String agreement, final String applyDate,
      final String otherMoney, final String startDateMin,
      final String startDateMax) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p111.contract_id,p111.loan_kou_acc,"
              + " p110.borrower_name,p110.card_kind,p110.card_num,"
              + " p110.org_name,p110.house_tel,p110.home_mobile,"
              + " p110.org_tel,p205.owe_corpus,p205.owe_interest,"
              + " p205.punish_interest,p205.owe_month,p110.office,"
              + " p111.loan_bank_id,"
              + " p205.owe_date,p205.id,"
              + " p111.loan_money,"
              + " p111.loan_time_limit,"
              + " p111.overplus_loan_money,"
              + " b.name,"
              + " b.card_num as cna,"
              + " b.org_name as cnm,"
              + " b.home_mobile as cmm,"
              + " b.org_tel as ctl,"
              + " b.home_addr as had,"
              + " (select max(p400.id) as cas from pl400 p400 where p400.status='0' and p400.reservea_b='1' and p400.contract_id=p111.contract_id) as pida, "
              + " p111.reservea_c "
              + " from pl111 p111,pl110 p110,pl205 p205,pl114 p114,"
              + " (select p113.contract_id,p113.name,p113.card_num,p113.org_name,p113.home_mobile,p113.org_tel,p113.home_addr from pl113 p113) b "
              + " where p110.contract_id=p111.contract_id and "
              + " p205.contract_id=p111.contract_id and p114.contract_id = p111.contract_id "
              + " and b.contract_id (+)= p111.contract_id and p205.owe_date = '"
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + "' and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          String criterion = "";
          Vector parameters = new Vector();
          String criterion1 = "";
          String sql1 = "select p6.floor_id from pl006 p6";

          String ob = orderBy;
          if (ob == null)
            ob = " p111.contract_id ";

          String od = order;
          if (od == null)
            od = " DESC";
          if (offic != null && !"".equals(offic)) {
            criterion += " p110.office=? and ";
            parameters.add(offic);
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p111.loan_bank_id =? and ";
            parameters.add(loanBankName);
          }
          if (assistantorg != null && !"".equals(assistantorg)) {
            criterion += " p205.contract_id in (select p120.contract_id from pl120 p120 ,pl007 p007 "
                + " where p120.assistant_org_id=p007.assistant_org_id "
                + " and p007.assistant_org_id=? ) and ";
            parameters.add(assistantorg);
          }
          if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
            criterion += " p114.head_id = ? and ";
            criterion1 += " p6.head_id = '" + buyhouseorgid + "' and ";
            parameters.add(buyhouseorgid);
          }
          if (floorName != null && !"".equals(floorName)) {
            criterion1 += " p6.floor_name = '" + floorName + "' and ";
          }
          if (floorid != null && !"".equals(floorid) && !"all".equals(floorid)) {
            criterion += " p114.floor_id = ? and ";
            parameters.add(floorid);
          } else if (criterion1.length() != 0) {
            criterion += " p114.floor_id in (" + sql1 + " where "
                + criterion1.substring(0, criterion1.lastIndexOf("and"))
                + ") and ";
          }
          if (floorNum != null && !"".equals(floorNum)) {
            criterion += " p114.floor_num = ? and ";
            parameters.add(floorNum);
          }
          if (roomNum != null && !"".equals(roomNum)) {
            criterion += " p114.room_num = ? and ";
            parameters.add(roomNum);
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " p111.loan_kou_acc = ? and ";
            parameters.add(loanKouAcc);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num = ? and ";
            parameters.add(cardNum);
          }
          if (beginoweMonth != null && !"".equals(beginoweMonth)) {
            criterion += " p205.owe_month >= to_number( ? ) and ";
            parameters.add(beginoweMonth);
          }
          if (endoweMonth != null && !"".equals(endoweMonth)) {
            criterion += " p205.owe_month <= to_number( ? ) and ";
            parameters.add(endoweMonth.trim());
          }
          // ------------------------------------------------//
          if (housetype != null && !"".equals(housetype)) {
            criterion += " p114.house_type = ? and ";
            parameters.add(housetype.trim());
          }
          if (buildAreaMin != null && !"".equals(buildAreaMin)) {
            criterion += " (p114.house_area >= ? or to_number(p114.bargainor_house_area) >= ?) and ";
            parameters.add(buildAreaMin);
            parameters.add(buildAreaMin);
          }
          if (buildAreaMax != null && !"".equals(buildAreaMax)) {
            criterion += " (p114.house_area <= ? or to_number(p114.bargainor_house_area) <= ?) and ";
            parameters.add(buildAreaMax);
            parameters.add(buildAreaMax);
          }
          if (agreement != null && !"1".equals(agreement)) {
            criterion += " p111.contract_id in (select p400.contract_id from pl400 p400 where p400.status='0' and p400.reservea_b='1') and ";
          } else if (agreement != null && !"0".equals(agreement)) {
            criterion += " not exists (select p400.contract_id from pl400 p400 where p400.status='0' and p400.reservea_b='1' and p400.contract_id=p111.contract_id) and ";
          }
          if (applyDate != null && !"".equals(applyDate)) {
            criterion += " p111.reservea_c = ? and ";
            parameters.add(applyDate);
          }
          if (otherMoney != null && !"".equals(otherMoney)) {
            if (otherMoney.equals("0")) {
              criterion += " p110.other_arrearage = ? and ";
              parameters.add("1");
            } else {
              criterion += " p110.other_arrearage = ? and ";
              parameters.add("0");
            }

          }
          if (startDateMin != null && !"".equals(startDateMin)) {
            criterion += " p111.loan_start_date >= ? and ";
            parameters.add(startDateMin);
          }
          if (startDateMax != null && !"".equals(startDateMax)) {
            criterion += " p111.loan_start_date <= ? and ";
            parameters.add(startDateMax);
          }

          if (isNoAcquittance != null && !"".equals(isNoAcquittance)) {
            if (isNoAcquittance.equals("是")) {
              String time = securityInfo.getUserInfo().getPlbizDate();
              criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)<to_date(?,'yyyymmdd') and ";
              parameters.add(time.trim());
            } else {
              String time = securityInfo.getUserInfo().getPlbizDate();
              criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)>=to_date(?,'yyyymmdd') and ";
              parameters.add(time.trim());
            }
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();
          Iterator iterate = queryList.iterator();
          List overDueinfoQueryShowList = new ArrayList();
          Object[] obj = null;
          while (iterate.hasNext()) {
            OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO = new OverDueinfoQueryShowListDTO();
            obj = (Object[]) iterate.next();

            if (obj[0] != null) {
              overDueinfoQueryShowListDTO.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              overDueinfoQueryShowListDTO.setLoanKouAcc(obj[1].toString());
            }
            if (obj[2] != null) {
              overDueinfoQueryShowListDTO.setBorrowerName(obj[2].toString());
            }
            if (obj[3] != null) {
              overDueinfoQueryShowListDTO.setCardKind(obj[3].toString());
            }
            if (obj[4] != null) {
              overDueinfoQueryShowListDTO.setCardNum(obj[4].toString());
            }
            if (obj[5] != null) {
              overDueinfoQueryShowListDTO.setOrgName(obj[5].toString());
            }
            if (obj[6] != null) {
              overDueinfoQueryShowListDTO.setHouseTel(obj[6].toString());
            }
            if (obj[7] != null) {
              overDueinfoQueryShowListDTO.setHomeMobile(obj[7].toString());
            }
            if (obj[8] != null) {
              overDueinfoQueryShowListDTO.setOrgTel(obj[8].toString());
            }
            if (obj[9] != null) {
              overDueinfoQueryShowListDTO.setOweCorpus(new BigDecimal(obj[9]
                  .toString()));
            }
            if (obj[10] != null) {
              overDueinfoQueryShowListDTO.setOweInterest(new BigDecimal(obj[10]
                  .toString()));
            }
            if (obj[11] != null) {
              overDueinfoQueryShowListDTO.setPunishInterest(new BigDecimal(
                  obj[11].toString()));
            }
            if (obj[12] != null) {
              overDueinfoQueryShowListDTO.setOweMonth(obj[12].toString());
            }
            if (obj[15] != null) {
              overDueinfoQueryShowListDTO.setOweDate(obj[15].toString());
            }
            if (obj[16] != null) {
              overDueinfoQueryShowListDTO.setId(obj[16].toString());
            }
            if (obj[17] != null) {
              overDueinfoQueryShowListDTO.setLoan_money(obj[17].toString());
            }
            if (obj[18] != null) {
              overDueinfoQueryShowListDTO
                  .setLoan_time_limit(obj[18].toString());
            }
            if (obj[19] != null) {
              overDueinfoQueryShowListDTO.setOverplus_loan_money(obj[19]
                  .toString());
            }
            if (obj[20] != null) {
              overDueinfoQueryShowListDTO.setName(obj[20].toString());
            }
            if (obj[21] != null) {
              overDueinfoQueryShowListDTO.setCard_num(obj[21].toString());
            }
            if (obj[22] != null) {
              overDueinfoQueryShowListDTO.setOrg_name(obj[22].toString());
            }
            if (obj[23] != null) {
              overDueinfoQueryShowListDTO.setHome_mobile(obj[23].toString());
            }
            if (obj[24] != null) {
              overDueinfoQueryShowListDTO.setOrg_tel(obj[24].toString());
            }
            if (obj[25] != null) {
              overDueinfoQueryShowListDTO.setHome_addr(obj[25].toString());
            }
            if (obj[26] != null) {
              overDueinfoQueryShowListDTO.setAgreement("是");
            } else {
              overDueinfoQueryShowListDTO.setAgreement("否");
            }
            if (obj[27] != null) {
              overDueinfoQueryShowListDTO.setApplyDate(obj[27].toString());
            }
            String bizDate = securityInfo.getUserInfo().getBizDate();
            if (obj[27] != null) {
              if (Integer.parseInt(obj[27].toString()) >= Integer
                  .parseInt(bizDate)) {
                String sql_yg = "select sum(t.should_corpus+t.should_interest) from pl201 t "
                    + "where t.contract_id='"
                    + obj[0].toString()
                    + "' and t.loan_kou_yearmonth='"
                    + bizDate.substring(0, 6)
                    + "'";
                Query querya = session.createSQLQuery(sql_yg);
                if (querya.uniqueResult() != null) {
                  overDueinfoQueryShowListDTO.setNextCorpusInterest(querya
                      .uniqueResult().toString());
                } else {
                  overDueinfoQueryShowListDTO.setNextCorpusInterest("0");
                }
              } else {
                String sql_yg = "select sum(t.should_corpus+t.should_interest) from pl201 t "
                    + "where t.contract_id='"
                    + obj[0].toString()
                    + "' and t.loan_kou_yearmonth='"
                    + BusiTools.addMonth(bizDate.substring(0, 6), 1) + "'";
                Query querya = session.createSQLQuery(sql_yg);
                if (querya.uniqueResult() != null) {
                  overDueinfoQueryShowListDTO.setNextCorpusInterest(querya
                      .uniqueResult().toString());
                } else {
                  overDueinfoQueryShowListDTO.setNextCorpusInterest("0");
                }
              }
            }
            overDueinfoQueryShowListDTO
                .setCorpusInterestAll(overDueinfoQueryShowListDTO
                    .getOweCorpus().add(
                        overDueinfoQueryShowListDTO.getOweInterest()).add(
                        overDueinfoQueryShowListDTO.getPunishInterest())
                    .toString());
            overDueinfoQueryShowListDTO.setNextMoney(new BigDecimal(
                overDueinfoQueryShowListDTO.getCorpusInterestAll()).add(
                new BigDecimal(overDueinfoQueryShowListDTO
                    .getNextCorpusInterest())).toString());

            // 证件类型对应的名称
            try {
              overDueinfoQueryShowListDTO.setCardKind(BusiTools.getBusiValue(
                  Integer.parseInt(overDueinfoQueryShowListDTO.getCardKind()),
                  BusiConst.DOCUMENTSSTATE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            overDueinfoQueryShowList.add(overDueinfoQueryShowListDTO);
          }
          return overDueinfoQueryShowList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  public List queryoverDueinfoQueryShowListByCriterions_yga(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String offic,
      final String loanBankName, final String buyhouseorgid,
      final String floorId, final String assistantorg, final String contractId,
      final String loanKouAcc, final String borrowerName, final String cardNum,
      final String beginoweMonth, final String endoweMonth,
      final String isNoAcquittance, final String housetype,
      final String buildAreaMin, final String buildAreaMax,
      final String floorid, final String floorName, final String floorNum,
      final String roomNum, final String agreement, final String applyDate,
      final String otherMoney, final String startDateMin,
      final String startDateMax) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p111.contract_id,p111.loan_kou_acc,"
              + "p110.borrower_name,p110.card_kind,p110.card_num,"
              + "p110.org_name,p110.house_tel,p110.home_mobile,"
              + "p110.org_tel,p205.owe_corpus,p205.owe_interest,"
              + "p205.punish_interest,p205.owe_month,p110.office,"
              + "p111.loan_bank_id,"
              + "p205.owe_date,p205.id,"
              + "p111.loan_money,"
              + "p111.loan_time_limit,"
              + "p111.overplus_loan_money,"
              + "b.name,"
              + "b.card_num as cna,"
              + "b.org_name as cnm,"
              + "b.home_mobile as cmm,"
              + "b.org_tel as ctl,"
              + "b.home_addr as had,"
              + "(select max(p400.id) as cas from pl400 p400 where p400.status='0' and p400.reservea_b='1' and p400.contract_id=p111.contract_id) as pida, "
              + "p111.reservea_c "
              + " from pl111 p111,pl110 p110,pl205 p205,pl114 p114,"
              + " (select p113.contract_id,p113.name,p113.card_num,p113.org_name,p113.home_mobile,p113.org_tel,p113.home_addr from pl113 p113) b "
              + " where p110.contract_id=p111.contract_id and "
              + " p205.contract_id=p111.contract_id and p114.contract_id = p111.contract_id "
              + " and b.contract_id (+)= p111.contract_id and p205.owe_date = '"
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + "' and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          String criterion = "";
          Vector parameters = new Vector();
          String criterion1 = "";
          String sql1 = "select p6.floor_id from pl006 p6";

          String ob = orderBy;
          if (ob == null)
            ob = " p111.contract_id ";

          String od = order;
          if (od == null)
            od = " DESC";
          if (offic != null && !"".equals(offic)) {
            criterion += " p110.office=? and ";
            parameters.add(offic);
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p111.loan_bank_id =? and ";
            parameters.add(loanBankName);
          }
          if (assistantorg != null && !"".equals(assistantorg)) {
            criterion += " p205.contract_id in (select p120.contract_id from pl120 p120 ,pl007 p007 "
                + " where p120.assistant_org_id=p007.assistant_org_id "
                + " and p007.assistant_org_id=? ) and ";
            parameters.add(assistantorg);
          }
          if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
            criterion += " p114.head_id = ? and ";
            criterion1 += " p6.head_id = '" + buyhouseorgid + "' and ";
            parameters.add(buyhouseorgid);
          }
          if (floorName != null && !"".equals(floorName)) {
            criterion1 += " p6.floor_name = '" + floorName + "' and ";
          }
          if (floorid != null && !"".equals(floorid) && !"all".equals(floorid)) {
            criterion += " p114.floor_id = ? and ";
            parameters.add(floorid);
          } else if (criterion1.length() != 0) {
            criterion += " p114.floor_id in (" + sql1 + " where "
                + criterion1.substring(0, criterion1.lastIndexOf("and"))
                + ") and ";
          }
          if (floorNum != null && !"".equals(floorNum)) {
            criterion += " p114.floor_num = ? and ";
            parameters.add(floorNum);
          }
          if (roomNum != null && !"".equals(roomNum)) {
            criterion += " p114.room_num = ? and ";
            parameters.add(roomNum);
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " p111.loan_kou_acc = ? and ";
            parameters.add(loanKouAcc);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num = ? and ";
            parameters.add(cardNum);
          }
          if (beginoweMonth != null && !"".equals(beginoweMonth)) {
            criterion += " p205.owe_month >= to_number( ? ) and ";
            parameters.add(beginoweMonth);
          }
          if (endoweMonth != null && !"".equals(endoweMonth)) {
            criterion += " p205.owe_month <= to_number( ? ) and ";
            parameters.add(endoweMonth.trim());
          }
          // ------------------------------------------------//
          if (housetype != null && !"".equals(housetype)) {
            criterion += " p114.house_type = ? and ";
            parameters.add(housetype.trim());
          }
          if (buildAreaMin != null && !"".equals(buildAreaMin)) {
            criterion += " (p114.house_area >= ? or to_number(p114.bargainor_house_area) >= ?) and ";
            parameters.add(buildAreaMin);
            parameters.add(buildAreaMin);
          }
          if (buildAreaMax != null && !"".equals(buildAreaMax)) {
            criterion += " (p114.house_area <= ? or to_number(p114.bargainor_house_area) <= ?) and ";
            parameters.add(buildAreaMax);
            parameters.add(buildAreaMax);
          }
          if (agreement != null && !"1".equals(agreement)) {
            criterion += " p111.contract_id in (select p400.contract_id from pl400 p400 where p400.status='0' and p400.reservea_b='1') and ";
          } else if (agreement != null && !"0".equals(agreement)) {
            criterion += " not exists (select p400.contract_id from pl400 p400 where p400.status='0' and p400.reservea_b='1' and p400.contract_id=p111.contract_id) and ";
          }
          if (applyDate != null && !"".equals(applyDate)) {
            criterion += " p111.reservea_c = ? and ";
            parameters.add(applyDate);
          }
          if (otherMoney != null && !"".equals(otherMoney)) {
            if (otherMoney.equals("0")) {
              criterion += " p110.other_arrearage = ? and ";
              parameters.add("1");
            } else {
              criterion += " p110.other_arrearage = ? and ";
              parameters.add("0");
            }

          }
          if (startDateMin != null && !"".equals(startDateMin)) {
            criterion += " p111.loan_start_date >= ? and ";
            parameters.add(startDateMin);
          }
          if (startDateMax != null && !"".equals(startDateMax)) {
            criterion += " p111.loan_start_date <= ? and ";
            parameters.add(startDateMax);
          }

          if (isNoAcquittance != null && !"".equals(isNoAcquittance)) {
            if (isNoAcquittance.equals("是")) {
              String time = securityInfo.getUserInfo().getPlbizDate();
              criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)<to_date(?,'yyyymmdd') and ";
              parameters.add(time.trim());
            } else {
              String time = securityInfo.getUserInfo().getPlbizDate();
              criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)>=to_date(?,'yyyymmdd') and ";
              parameters.add(time.trim());
            }
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List queryList = query.list();
          Iterator iterate = queryList.iterator();
          List overDueinfoQueryShowList = new ArrayList();
          Object[] obj = null;
          while (iterate.hasNext()) {
            OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO = new OverDueinfoQueryShowListDTO();
            obj = (Object[]) iterate.next();

            if (obj[0] != null) {
              overDueinfoQueryShowListDTO.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              overDueinfoQueryShowListDTO.setLoanKouAcc(obj[1].toString());
            }
            if (obj[2] != null) {
              overDueinfoQueryShowListDTO.setBorrowerName(obj[2].toString());
            }
            if (obj[3] != null) {
              overDueinfoQueryShowListDTO.setCardKind(obj[3].toString());
            }
            if (obj[4] != null) {
              overDueinfoQueryShowListDTO.setCardNum(obj[4].toString());
            }
            if (obj[5] != null) {
              overDueinfoQueryShowListDTO.setOrgName(obj[5].toString());
            }
            if (obj[6] != null) {
              overDueinfoQueryShowListDTO.setHouseTel(obj[6].toString());
            }
            if (obj[7] != null) {
              overDueinfoQueryShowListDTO.setHomeMobile(obj[7].toString());
            }
            if (obj[8] != null) {
              overDueinfoQueryShowListDTO.setOrgTel(obj[8].toString());
            }
            if (obj[9] != null) {
              overDueinfoQueryShowListDTO.setOweCorpus(new BigDecimal(obj[9]
                  .toString()));
            }
            if (obj[10] != null) {
              overDueinfoQueryShowListDTO.setOweInterest(new BigDecimal(obj[10]
                  .toString()));
            }
            if (obj[11] != null) {
              overDueinfoQueryShowListDTO.setPunishInterest(new BigDecimal(
                  obj[11].toString()));
            }
            if (obj[12] != null) {
              overDueinfoQueryShowListDTO.setOweMonth(obj[12].toString());
            }
            if (obj[15] != null) {
              overDueinfoQueryShowListDTO.setOweDate(obj[15].toString());
            }
            if (obj[16] != null) {
              overDueinfoQueryShowListDTO.setId(obj[16].toString());
            }
            if (obj[17] != null) {
              overDueinfoQueryShowListDTO.setLoan_money(obj[17].toString());
            }
            if (obj[18] != null) {
              overDueinfoQueryShowListDTO
                  .setLoan_time_limit(obj[18].toString());
            }
            if (obj[19] != null) {
              overDueinfoQueryShowListDTO.setOverplus_loan_money(obj[19]
                  .toString());
            }
            if (obj[20] != null) {
              overDueinfoQueryShowListDTO.setName(obj[20].toString());
            }
            if (obj[21] != null) {
              overDueinfoQueryShowListDTO.setCard_num(obj[21].toString());
            }
            if (obj[22] != null) {
              overDueinfoQueryShowListDTO.setOrg_name(obj[22].toString());
            }
            if (obj[23] != null) {
              overDueinfoQueryShowListDTO.setHome_mobile(obj[23].toString());
            }
            if (obj[24] != null) {
              overDueinfoQueryShowListDTO.setOrg_tel(obj[24].toString());
            }
            if (obj[25] != null) {
              overDueinfoQueryShowListDTO.setHome_addr(obj[25].toString());
            }
            if (obj[26] != null) {
              overDueinfoQueryShowListDTO.setAgreement("是");
            } else {
              overDueinfoQueryShowListDTO.setAgreement("否");
            }
            if (obj[27] != null) {
              overDueinfoQueryShowListDTO.setApplyDate(obj[27].toString());
            }
            String bizDate = securityInfo.getUserInfo().getBizDate();
            if (obj[27] != null) {
              if (Integer.parseInt(obj[27].toString()) >= Integer
                  .parseInt(bizDate)) {
                String sql_yg = "select sum(t.should_corpus+t.should_interest) from pl201 t "
                    + "where t.contract_id='"
                    + obj[0].toString()
                    + "' and t.loan_kou_yearmonth='"
                    + bizDate.substring(0, 6)
                    + "'";
                Query querya = session.createSQLQuery(sql_yg);
                if (querya.uniqueResult() != null) {
                  overDueinfoQueryShowListDTO.setNextCorpusInterest(querya
                      .uniqueResult().toString());
                } else {
                  overDueinfoQueryShowListDTO.setNextCorpusInterest("0");
                }
              } else {
                String sql_yg = "select sum(t.should_corpus+t.should_interest) from pl201 t "
                    + "where t.contract_id='"
                    + obj[0].toString()
                    + "' and t.loan_kou_yearmonth='"
                    + BusiTools.addMonth(bizDate.substring(0, 6), 1) + "'";
                Query querya = session.createSQLQuery(sql_yg);
                if (querya.uniqueResult() != null) {
                  overDueinfoQueryShowListDTO.setNextCorpusInterest(querya
                      .uniqueResult().toString());
                } else {
                  overDueinfoQueryShowListDTO.setNextCorpusInterest("0");
                }
              }
            }
            overDueinfoQueryShowListDTO
                .setCorpusInterestAll(overDueinfoQueryShowListDTO
                    .getOweCorpus().add(
                        overDueinfoQueryShowListDTO.getOweInterest()).add(
                        overDueinfoQueryShowListDTO.getPunishInterest())
                    .toString());
            overDueinfoQueryShowListDTO.setNextMoney(new BigDecimal(
                overDueinfoQueryShowListDTO.getCorpusInterestAll()).add(
                new BigDecimal(overDueinfoQueryShowListDTO
                    .getNextCorpusInterest())).toString());

            // 证件类型对应的名称
            try {
              overDueinfoQueryShowListDTO.setCardKind(BusiTools.getBusiValue(
                  Integer.parseInt(overDueinfoQueryShowListDTO.getCardKind()),
                  BusiConst.DOCUMENTSSTATE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            overDueinfoQueryShowList.add(overDueinfoQueryShowListDTO);
          }
          return overDueinfoQueryShowList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据条件查询逾期还款信息列表总数
   * 
   * @author 吴迪 2007-10-23
   * @param securityInfo
   * @return
   */
  public List queryoverDueinfoQueryCountShowListByCriterions(
      final SecurityInfo securityInfo, final String offic,
      final String loanBankName, final String buyhouseorgid,
      final String floorId, final String assistantorg, final String contractId,
      final String loanKouAcc, final String borrowerName, final String cardNum,
      final String beginoweMonth, final String endoweMonth,
      final String isNoAcquittance) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p111.contract_id," + "p111.loan_kou_acc,"
              + "p110.borrower_name," + "p110.card_kind," + "p110.card_num,"
              + "p110.org_name," + "p110.house_tel," + "p110.home_mobile,"
              + "p110.org_tel," + "p205.owe_corpus," + "p205.owe_interest,"
              + "p205.punish_interest," + "p205.owe_month," + "p110.office,"
              + "p111.loan_bank_id," + "p205.owe_date"
              + " from pl111 p111,pl110 p110,pl205 p205"
              + " where p110.contract_id=p111.contract_id" + " and"
              + " p205.contract_id=p111.contract_id"
              + " and   p111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          String criterion = "";
          Vector parameters = new Vector();
          if (offic != null && !"".equals(offic)) {
            criterion += " p110.office=? and ";
            parameters.add(offic);
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p111.loan_bank_id =? and ";
            parameters.add(loanBankName);
          }
          if (assistantorg != null && !"".equals(assistantorg)) {
            criterion += " p205.contract_id in (select p120.contract_id from pl120 p120 ,pl007 p007 "
                + " where p120.assistant_org_id=p007.assistant_org_id "
                + " and p007.assistant_org_id=? ) and ";
            parameters.add(assistantorg);
          }
          if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
            criterion += " p205.contract_id in(select p114.contract_id from pl114 p114, pl006 p006 where p006.head_id = p114.head_id and p114.head_id =? and p114.floor_id =? ) and ";
            parameters.add(buyhouseorgid);
            parameters.add(floorId);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " p111.loan_kou_acc = ? and ";
            parameters.add(loanKouAcc);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num = ? and ";
            parameters.add(cardNum);
          }
          if (beginoweMonth != null && !"".equals(beginoweMonth)) {
            criterion += " p205.owe_month >= to_number( ? ) and ";
            parameters.add(beginoweMonth);
          }
          if (endoweMonth != null && !"".equals(endoweMonth)) {
            criterion += " p205.owe_month <= to_number( ? ) and ";
            parameters.add(endoweMonth.trim());
          }
          if (isNoAcquittance != null && !"".equals(isNoAcquittance)) {
            if (isNoAcquittance.equals("是")) {
              String time = securityInfo.getUserInfo().getPlbizDate();
              criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)<to_date(?,'yyyymmdd') and ";
              parameters.add(time.trim());
            } else {
              String time = securityInfo.getUserInfo().getPlbizDate();
              criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)>=to_date(?,'yyyymmdd') and ";
              parameters.add(time.trim());
            }
          }
          if (criterion.length() != 0)
            criterion = " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List queryList = query.list();
          List printList = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO = new OverDueinfoQueryShowListDTO();
            obj = (Object[]) iterate.next();

            if (obj[0] != null) {
              overDueinfoQueryShowListDTO.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              overDueinfoQueryShowListDTO.setLoanKouAcc(obj[1].toString());
            }
            if (obj[2] != null) {
              overDueinfoQueryShowListDTO.setBorrowerName(obj[2].toString());
            }
            if (obj[3] != null) {
              overDueinfoQueryShowListDTO.setCardKind(obj[3].toString());
            }
            if (obj[4] != null) {
              overDueinfoQueryShowListDTO.setCardNum(obj[4].toString());
            }
            if (obj[5] != null) {
              overDueinfoQueryShowListDTO.setOrgName(obj[5].toString());
            }
            if (obj[6] != null) {
              overDueinfoQueryShowListDTO.setHouseTel(obj[6].toString());
            }
            if (obj[7] != null) {
              overDueinfoQueryShowListDTO.setHomeMobile(obj[7].toString());
            }
            if (obj[8] != null) {
              overDueinfoQueryShowListDTO.setOrgTel(obj[8].toString());
            }
            if (obj[9] != null) {
              overDueinfoQueryShowListDTO.setOweCorpus(new BigDecimal(obj[9]
                  .toString()));
            }
            if (obj[10] != null) {
              overDueinfoQueryShowListDTO.setOweInterest(new BigDecimal(obj[10]
                  .toString()));
            }
            if (obj[11] != null) {
              overDueinfoQueryShowListDTO.setPunishInterest(new BigDecimal(
                  obj[11].toString()));
            }
            if (obj[12] != null) {
              overDueinfoQueryShowListDTO.setOweMonth(obj[12].toString());
            }
            if (obj[15] != null) {
              overDueinfoQueryShowListDTO.setOweDate(obj[15].toString());
            }
            // 证件类型对应的名称
            try {
              overDueinfoQueryShowListDTO.setCardKind(BusiTools.getBusiValue(
                  Integer.parseInt(overDueinfoQueryShowListDTO.getCardKind()),
                  BusiConst.DOCUMENTSSTATE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            printList.add(overDueinfoQueryShowListDTO);
          }
          return printList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据条件查询逾期还款单个打印
   * 
   * @author 吴迪 2007-10-25
   * @param securityInfo
   * @return
   */
  public OverDueinfoQueryShowListDTO queryoverDueinfoQueryPrintoneByCriterions(
      final SecurityInfo securityInfo, final String id) {
    OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO = new OverDueinfoQueryShowListDTO();
    try {
      overDueinfoQueryShowListDTO = (OverDueinfoQueryShowListDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select p111.contract_id," + "p111.loan_kou_acc,"
                  + "p110.borrower_name," + "p110.card_kind,"
                  + "p110.card_num," + "p110.org_name," + "p110.house_tel,"
                  + "p110.home_mobile," + "p110.org_tel," + "p205.owe_corpus,"
                  + "p205.owe_interest," + "p205.punish_interest,"
                  + "p205.owe_month," + "p110.office," + "p111.loan_bank_id,"
                  + "p205.owe_date" + " from pl111 p111,pl110 p110,pl205 p205"
                  + " where p110.contract_id=p111.contract_id" + " and"
                  + " p205.contract_id=p111.contract_id"
                  + " and   p111.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              String criterion = "";
              Vector parameters = new Vector();
              if (id != null && !"".equals(id)) {
                criterion += " p205.id = ? and ";
                parameters.add(id);
              }
              if (criterion.length() != 0)
                criterion = " and  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              Object[] obj = (Object[]) query.uniqueResult();
              OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO = new OverDueinfoQueryShowListDTO();
              if (obj[0] != null) {
                overDueinfoQueryShowListDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                overDueinfoQueryShowListDTO.setLoanKouAcc(obj[1].toString());
              }
              if (obj[2] != null) {
                overDueinfoQueryShowListDTO.setBorrowerName(obj[2].toString());
              }
              if (obj[3] != null) {
                overDueinfoQueryShowListDTO.setCardKind(obj[3].toString());
              }
              if (obj[4] != null) {
                overDueinfoQueryShowListDTO.setCardNum(obj[4].toString());
              }
              if (obj[5] != null) {
                overDueinfoQueryShowListDTO.setOrgName(obj[5].toString());
              }
              if (obj[6] != null) {
                overDueinfoQueryShowListDTO.setHouseTel(obj[6].toString());
              }
              if (obj[7] != null) {
                overDueinfoQueryShowListDTO.setHomeMobile(obj[7].toString());
              }
              if (obj[8] != null) {
                overDueinfoQueryShowListDTO.setOrgTel(obj[8].toString());
              }
              if (obj[9] != null) {
                overDueinfoQueryShowListDTO.setOweCorpus(new BigDecimal(obj[9]
                    .toString()));
              }

              if (obj[10] != null) {
                overDueinfoQueryShowListDTO.setOweInterest(new BigDecimal(
                    obj[10].toString()));
              }

              if (obj[11] != null) {
                overDueinfoQueryShowListDTO.setPunishInterest(new BigDecimal(
                    obj[11].toString()));
              }

              if (obj[12] != null) {
                overDueinfoQueryShowListDTO.setOweMonth(obj[12].toString());
              }
              if (obj[15] != null) {
                overDueinfoQueryShowListDTO.setOweDate(obj[15].toString());
              }
              // 证件类型对应的名称
              try {
                overDueinfoQueryShowListDTO.setCardKind(BusiTools
                    .getBusiValue(Integer.parseInt(overDueinfoQueryShowListDTO
                        .getCardKind()), BusiConst.DOCUMENTSSTATE));
              } catch (Exception e) {
                e.printStackTrace();
              }
              return overDueinfoQueryShowListDTO;
            }
          });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return overDueinfoQueryShowListDTO;
  }

  public OverDueinfoQueryShowListDTO queryoverDueinfoQueryPrintoneByCriterions_wsh(
      final SecurityInfo securityInfo, final String id) {
    OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO = new OverDueinfoQueryShowListDTO();
    try {
      overDueinfoQueryShowListDTO = (OverDueinfoQueryShowListDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select p111.contract_id,p111.loan_kou_acc,"
                  + "p110.borrower_name,p110.card_kind,p110.card_num,"
                  + "p110.org_name,p110.house_tel,p110.home_mobile,"
                  + "p110.org_tel,p205.owe_corpus,p205.owe_interest,"
                  + "p205.punish_interest,p205.owe_month,p110.office,"
                  + "p111.loan_bank_id,"
                  + "p205.owe_date,p205.id,"
                  + "p111.loan_money,"
                  + "p111.loan_time_limit,"
                  + "p111.overplus_loan_money,"
                  + "b.name,"
                  + "b.card_num as cna,"
                  + "b.org_name as cnm,"
                  + "b.home_mobile as cmm,"
                  + "b.org_tel as ctl,"
                  + "b.home_addr as had,"
                  + "(select max(p400.id) as cas from pl400 p400 where p400.status='0' and p400.reservea_b='1' and p400.contract_id=p111.contract_id) as pida, "
                  + "p111.reservea_c "
                  + " from pl111 p111,pl110 p110,pl205 p205,pl114 p114,"
                  + " (select p113.contract_id,p113.name,p113.card_num,p113.org_name,p113.home_mobile,p113.org_tel,p113.home_addr from pl113 p113) b "
                  + " where p110.contract_id=p111.contract_id and "
                  + " p205.contract_id=p111.contract_id and p114.contract_id = p111.contract_id "
                  + " and b.contract_id (+)= p111.contract_id and p205.owe_date = '"
                  + securityInfo.getUserInfo().getBizDate().substring(0, 6)
                  + "' and p111.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              String criterion = "";
              Vector parameters = new Vector();
              if (id != null && !"".equals(id)) {
                criterion += " p205.id = ? and ";
                parameters.add(id);
              }
              if (criterion.length() != 0)
                criterion = " and  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              Object[] obj = (Object[]) query.uniqueResult();
              OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO = new OverDueinfoQueryShowListDTO();
              if (obj[0] != null) {
                overDueinfoQueryShowListDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                overDueinfoQueryShowListDTO.setLoanKouAcc(obj[1].toString());
              }
              if (obj[2] != null) {
                overDueinfoQueryShowListDTO.setBorrowerName(obj[2].toString());
              }
              if (obj[3] != null) {
                overDueinfoQueryShowListDTO.setCardKind(obj[3].toString());
              }
              if (obj[4] != null) {
                overDueinfoQueryShowListDTO.setCardNum(obj[4].toString());
              }
              if (obj[5] != null) {
                overDueinfoQueryShowListDTO.setOrgName(obj[5].toString());
              }
              if (obj[6] != null) {
                overDueinfoQueryShowListDTO.setHouseTel(obj[6].toString());
              }
              if (obj[7] != null) {
                overDueinfoQueryShowListDTO.setHomeMobile(obj[7].toString());
              }
              if (obj[8] != null) {
                overDueinfoQueryShowListDTO.setOrgTel(obj[8].toString());
              }
              if (obj[9] != null) {
                overDueinfoQueryShowListDTO.setOweCorpus(new BigDecimal(obj[9]
                    .toString()));
              }
              if (obj[10] != null) {
                overDueinfoQueryShowListDTO.setOweInterest(new BigDecimal(
                    obj[10].toString()));
              }
              if (obj[11] != null) {
                overDueinfoQueryShowListDTO.setPunishInterest(new BigDecimal(
                    obj[11].toString()));
              }
              if (obj[12] != null) {
                overDueinfoQueryShowListDTO.setOweMonth(obj[12].toString());
              }
              if (obj[15] != null) {
                overDueinfoQueryShowListDTO.setOweDate(obj[15].toString());
              }
              if (obj[16] != null) {
                overDueinfoQueryShowListDTO.setId(obj[16].toString());
              }
              if (obj[17] != null) {
                overDueinfoQueryShowListDTO.setLoan_money(obj[17].toString());
              }
              if (obj[18] != null) {
                overDueinfoQueryShowListDTO.setLoan_time_limit(obj[18]
                    .toString());
              }
              if (obj[19] != null) {
                overDueinfoQueryShowListDTO.setOverplus_loan_money(obj[19]
                    .toString());
              }
              if (obj[20] != null) {
                overDueinfoQueryShowListDTO.setName(obj[20].toString());
              }
              if (obj[21] != null) {
                overDueinfoQueryShowListDTO.setCard_num(obj[21].toString());
              }
              if (obj[22] != null) {
                overDueinfoQueryShowListDTO.setOrg_name(obj[22].toString());
              }
              if (obj[23] != null) {
                overDueinfoQueryShowListDTO.setHome_mobile(obj[23].toString());
              }
              if (obj[24] != null) {
                overDueinfoQueryShowListDTO.setOrg_tel(obj[24].toString());
              }
              if (obj[25] != null) {
                overDueinfoQueryShowListDTO.setHome_addr(obj[25].toString());
              }
              if (obj[26] != null) {
                overDueinfoQueryShowListDTO.setAgreement("是");
              } else {
                overDueinfoQueryShowListDTO.setAgreement("否");
              }
              if (obj[27] != null) {
                overDueinfoQueryShowListDTO.setApplyDate(obj[27].toString());
              }
              String bizDate = securityInfo.getUserInfo().getBizDate();
              if (obj[27] != null) {
                if (Integer.parseInt(obj[27].toString()) >= Integer
                    .parseInt(bizDate)) {
                  String sql_yg = "select sum(t.should_corpus+t.should_interest) from pl201 t "
                      + "where t.contract_id='"
                      + obj[0].toString()
                      + "' and t.loan_kou_yearmonth='"
                      + bizDate.substring(0, 6) + "'";
                  Query querya = session.createSQLQuery(sql_yg);
                  if (querya.uniqueResult() != null) {
                    overDueinfoQueryShowListDTO.setNextCorpusInterest(querya
                        .uniqueResult().toString());
                  } else {
                    overDueinfoQueryShowListDTO.setNextCorpusInterest("0");
                  }
                } else {
                  String sql_yg = "select sum(t.should_corpus+t.should_interest) from pl201 t "
                      + "where t.contract_id='"
                      + obj[0].toString()
                      + "' and t.loan_kou_yearmonth='"
                      + BusiTools.addMonth(bizDate.substring(0, 6), 1) + "'";
                  Query querya = session.createSQLQuery(sql_yg);
                  if (querya.uniqueResult() != null) {
                    overDueinfoQueryShowListDTO.setNextCorpusInterest(querya
                        .uniqueResult().toString());
                  } else {
                    overDueinfoQueryShowListDTO.setNextCorpusInterest("0");
                  }
                }
              }
              overDueinfoQueryShowListDTO
                  .setCorpusInterestAll(overDueinfoQueryShowListDTO
                      .getOweCorpus().add(
                          overDueinfoQueryShowListDTO.getOweInterest()).add(
                          overDueinfoQueryShowListDTO.getPunishInterest())
                      .toString());
              overDueinfoQueryShowListDTO.setNextMoney(new BigDecimal(
                  overDueinfoQueryShowListDTO.getCorpusInterestAll()).add(
                  new BigDecimal(overDueinfoQueryShowListDTO
                      .getNextCorpusInterest())).toString());

              // 证件类型对应的名称
              try {
                overDueinfoQueryShowListDTO.setCardKind(BusiTools
                    .getBusiValue(Integer.parseInt(overDueinfoQueryShowListDTO
                        .getCardKind()), BusiConst.DOCUMENTSSTATE));
              } catch (Exception e) {
                e.printStackTrace();
              }
              return overDueinfoQueryShowListDTO;
            }
          });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return overDueinfoQueryShowListDTO;
  }

  /**
   * 根据银行代码和合同状态查询PL111
   * 
   * @param loanBankId
   * @param contractSt
   * @return
   */
  public List queryBorrowerAccInfoByLoanBankIdAcc_LJ(final String loanBankId,
      final String contractSt) {

    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select borrowerAcc.contractId "
              + " from BorrowerAcc borrowerAcc ";
          Vector parameters = new Vector();
          String criterion = "";

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "borrowerAcc.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (contractSt != null && !contractSt.equals("")) {
            criterion += "borrowerAcc.contractSt = ?  and ";
            parameters.add(contractSt);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 年终结转 查找满足状态,对应的贷款银行,同时在还款表的年份不存在的条件的合同编号 shiy return list
   */
  public List queryBorrowerAccList_sy(final String contractSt,
      final String loanBankId, final String loanKouYearmonth,
      final String orderBy, final String order, final int start,
      final int pageSize, final SecurityInfo securityInfo,
      final String loanBankName) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct qpl111.contract_id,qpl111.loan_kou_acc,qpl110.borrower_name"
              // ",qpl111.loan_mode, "
              // + "(select qpl201.loan_rate"
              // + " from pl201 qpl201 "
              // + " where qpl201.contract_id = qpl111.contract_id "
              // + " and qpl201.loan_kou_yearmonth = ? ),"
              // + " qpl111.loan_money - "
              // + " (select sum(qpl201.should_corpus)"
              // + " from pl201 qpl201 "
              // + " where qpl201.contract_id = qpl111.contract_id),"
              // + " to_number(qpl111.loan_time_limit) - "
              // + " to_number(months_between(to_date( ? , 'yyyymm'), "
              // + " to_date(substr(qpl111.loan_start_date, 0, 6),'yyyymm'))), "
              // + " (select spl201.should_corpus+spl201.should_interest from
              // pl201 spl201 where spl201.contract_id=qpl111.contract_id and "
              // + " spl201.loan_kou_yearmonth= ? ) "
              + " from pl111 qpl111, pl110 qpl110 ,pl201 qpl201 "
              + " where qpl111.contract_id = qpl110.contract_id "
              + " and qpl111.contract_id=qpl201.contract_id "
              + " and not exists (select distinct tpl201.contract_id from pl201 tpl201 where tpl201.contract_id = qpl111.contract_id and tpl201.loan_kou_yearmonth > ? ) "
              + "  and (to_number(qpl111.loan_time_limit) - to_number(months_between(to_date( ? , 'yyyymm'),"
              + " to_date(substr(qpl111.loan_start_date, 0, 6),'yyyymm'))))>0 "
              + " and qpl111.contract_st= ? ";
          Vector parameters = new Vector();
          String criterion = "";
          parameters.add(loanKouYearmonth);
          parameters.add(loanKouYearmonth);
          parameters.add(contractSt);
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " and qpl111.loan_bank_id = ? ";
            parameters.add(loanBankId);
          }
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List temp_list = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CarryforwardTaDTO carryforwardTaDTO = new CarryforwardTaDTO();
            carryforwardTaDTO.setContractId(obj[0] + "");
            carryforwardTaDTO.setLoanKouAcc(obj[1] + "");
            carryforwardTaDTO.setBorrowerName(obj[2] + "");
            // carryforwardTaDTO.setLoanMode(obj[3] + "");
            // if (obj[4] != null) {
            // carryforwardTaDTO.setLoanMonthRate(new BigDecimal(obj[4] + ""));
            // carryforwardTaDTO
            // .setTemploanMonthRate(new BigDecimal(obj[4] + "").multiply(
            // new BigDecimal(100.00)).toString()
            // + "%");
            // }
            // if (obj[5] != null)
            // carryforwardTaDTO
            // .setNewLoanLastMoney(new BigDecimal(obj[5] + ""));
            // if (obj[6] != null)
            // carryforwardTaDTO.setNewLoanlastTimeLimit(new BigDecimal(obj[6]
            // + ""));
            // // 等额本息的时候显示月还本息
            // if ((obj[3] + "").equals("2")) {
            // carryforwardTaDTO.setNewLoanInterest(obj[7] + "");
            // }
            carryforwardTaDTO.setLoanBankName(loanBankName);
            temp_list.add(carryforwardTaDTO);
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
   * 年终结转 统计满足状态,对应的贷款银行,同时在还款表的年份不存在的条件的合同编号 shiy return list
   */
  public List countBorrowerAccList_sy(final String contractSt,
      final String loanBankId, final String loanKouYearmonth,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct qpl111.contract_id "
              + " from pl111 qpl111, pl110 qpl110 ,pl201 qpl201"
              + " where qpl111.contract_id = qpl110.contract_id "
              + " and qpl111.contract_id=qpl201.contract_id "
              + " and not exists (select distinct tpl201.contract_id from pl201 tpl201 where tpl201.contract_id = qpl111.contract_id and tpl201.loan_kou_yearmonth > ? ) "
              + "  and (to_number(qpl111.loan_time_limit) - to_number(months_between(to_date( ? , 'yyyymm'),"
              + " to_date(substr(qpl111.loan_start_date, 0, 6),'yyyymm'))))>0 "
              + " and qpl111.contract_st= ? ";
          Vector parameters = new Vector();
          String criterion = "";
          parameters.add(loanKouYearmonth);
          parameters.add(loanKouYearmonth);
          parameters.add(contractSt);
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " and qpl111.loan_bank_id = ? ";
            parameters.add(loanBankId);
          }
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          // List list=query.list();
          // Integer countnumber=new Integer(0);
          // if(!list.isEmpty())
          // countnumber=new Integer(list.get(0).toString());
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据条件查询逾期还款合计信息
   * 
   * @author 吴迪 2007-11-2
   * @param securityInfo
   * @return
   */
  public OverDueinfoQueryTotleDTO queryoverDueinfoQueryTotleShowListByCriterions(
      final SecurityInfo securityInfo, final String offic,
      final String loanBankName, final String buyhouseorgid,
      final String floorId, final String assistantorg, final String contractId,
      final String loanKouAcc, final String borrowerName, final String cardNum,
      final String beginoweMonth, final String endoweMonth,
      final String isNoAcquittance) {
    OverDueinfoQueryTotleDTO overDueinfoQueryTotleDTO = new OverDueinfoQueryTotleDTO();
    try {
      overDueinfoQueryTotleDTO = (OverDueinfoQueryTotleDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select count(*)," + "sum(p205.owe_corpus),"
                  + "sum(p205.owe_interest)," + "sum(p205.punish_interest)"
                  + " from pl111 p111, pl110 p110, pl205 p205"
                  + " where p110.contract_id = p111.contract_id"
                  + " and p205.contract_id = p111.contract_id"
                  + " and   p111.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              String criterion = "";
              Vector parameters = new Vector();
              if (offic != null && !"".equals(offic)) {
                criterion += " p110.office=? and ";
                parameters.add(offic);
              }

              if (loanBankName != null && !"".equals(loanBankName)) {
                criterion += " p111.loan_bank_id =? and ";
                parameters.add(loanBankName);
              }
              if (assistantorg != null && !"".equals(assistantorg)) {
                criterion += " p205.contract_id in (select p120.contract_id from pl120 p120 ,pl007 p007 "
                    + " where p120.assistant_org_id=p007.assistant_org_id "
                    + " and p007.assistant_org_id=? ) and ";
                parameters.add(assistantorg);
              }
              if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
                criterion += " p205.contract_id in(select p114.contract_id from pl114 p114, pl006 p006 where p006.head_id = p114.head_id and p114.head_id =? and p114.floor_id =? ) and ";
                parameters.add(buyhouseorgid);
                parameters.add(floorId);
              }
              if (contractId != null && !"".equals(contractId)) {
                criterion += " p110.contract_id = ? and ";
                parameters.add(contractId);
              }
              if (loanKouAcc != null && !"".equals(loanKouAcc)) {
                criterion += " p111.loan_kou_acc = ? and ";
                parameters.add(loanKouAcc);
              }

              if (borrowerName != null && !"".equals(borrowerName)) {
                criterion += " p110.borrower_name = ? and ";
                parameters.add(borrowerName);
              }

              if (cardNum != null && !"".equals(cardNum)) {
                criterion += " p110.card_num = ? and ";
                parameters.add(cardNum);
              }
              if (beginoweMonth != null && !"".equals(beginoweMonth)) {
                criterion += " p205.owe_month >= to_number( ? ) and ";
                parameters.add(beginoweMonth);
              }
              if (endoweMonth != null && !"".equals(endoweMonth)) {
                criterion += " p205.owe_month <= to_number( ? ) and ";
                parameters.add(endoweMonth.trim());
              }

              if (isNoAcquittance != null && !"".equals(isNoAcquittance)) {
                if (isNoAcquittance.equals("是")) {
                  String time = securityInfo.getUserInfo().getPlbizDate();
                  criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)<to_date(?,'yyyymmdd') and ";
                  parameters.add(time.trim());
                } else {
                  String time = securityInfo.getUserInfo().getPlbizDate();
                  criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)>=to_date(?,'yyyymmdd') and ";
                  parameters.add(time.trim());
                }
              }

              if (criterion.length() != 0)
                criterion = " and  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Object[] obj = (Object[]) query.uniqueResult();
              OverDueinfoQueryTotleDTO overDueinfoQueryTotleDTO = new OverDueinfoQueryTotleDTO();
              if (obj[0] != null && !"".equals(obj[0]))
                overDueinfoQueryTotleDTO
                    .setCount(new Integer(obj[0].toString()).intValue());
              if (obj[1] != null && !"".equals(obj[1]))
                overDueinfoQueryTotleDTO.setOweCorpusTotle(new BigDecimal(
                    obj[1].toString()));
              if (obj[2] != null && !"".equals(obj[2]))
                overDueinfoQueryTotleDTO.setOweInterestTotle(new BigDecimal(
                    obj[2].toString()));
              if (obj[3] != null && !"".equals(obj[3]))
                overDueinfoQueryTotleDTO.setPunishInterest(new BigDecimal(
                    obj[3].toString()));
              return overDueinfoQueryTotleDTO;
            }
          });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return overDueinfoQueryTotleDTO;
  }

  public OverDueinfoQueryTotleDTO queryoverDueinfoQueryTotleShowListByCriterions_yg(
      final SecurityInfo securityInfo, final String offic,
      final String loanBankName, final String buyhouseorgid,
      final String floorId, final String assistantorg, final String contractId,
      final String loanKouAcc, final String borrowerName, final String cardNum,
      final String beginoweMonth, final String endoweMonth,
      final String isNoAcquittance, final String housetype,
      final String buildAreaMin, final String buildAreaMax,
      final String floorid, final String floorName, final String floorNum,
      final String roomNum, final String agreement, final String applyDate,
      final String otherMoney, final String startDateMin,
      final String startDateMax) {
    OverDueinfoQueryTotleDTO overDueinfoQueryTotleDTO = new OverDueinfoQueryTotleDTO();
    try {
      overDueinfoQueryTotleDTO = (OverDueinfoQueryTotleDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select count(*),"
                  + "sum(p205.owe_corpus),"
                  + "sum(p205.owe_interest),"
                  + "sum(p205.punish_interest)"
                  + " from pl111 p111,pl110 p110,pl205 p205,pl114 p114 "
                  + " where p110.contract_id=p111.contract_id"
                  + " and"
                  + " p205.contract_id=p111.contract_id and p114.contract_id = p111.contract_id  and p205.owe_date = '"
                  + securityInfo.getUserInfo().getBizDate().substring(0, 6)
                  + "'" + " and   p111.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();

              String criterion = "";
              Vector parameters = new Vector();
              String criterion1 = "";
              String sql1 = "select p6.floor_id from pl006 p6";

              if (offic != null && !"".equals(offic)) {
                criterion += " p110.office=? and ";
                parameters.add(offic);
              }

              if (loanBankName != null && !"".equals(loanBankName)) {
                criterion += " p111.loan_bank_id =? and ";
                parameters.add(loanBankName);
              }
              if (assistantorg != null && !"".equals(assistantorg)) {
                criterion += " p205.contract_id in (select p120.contract_id from pl120 p120 ,pl007 p007 "
                    + " where p120.assistant_org_id=p007.assistant_org_id "
                    + " and p007.assistant_org_id=? ) and ";
                parameters.add(assistantorg);
              }
              if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
                criterion += " p114.head_id = ? and ";
                criterion1 += " p6.head_id = '" + buyhouseorgid + "' and ";
                parameters.add(buyhouseorgid);
              }
              if (floorName != null && !"".equals(floorName)) {
                criterion1 += " p6.floor_name = '" + floorName + "' and ";
              }
              if (floorid != null && !"".equals(floorid)
                  && !"all".equals(floorid)) {
                criterion += " p114.floor_id = ? and ";
                parameters.add(floorid);
              } else if (criterion1.length() != 0) {
                criterion += " p114.floor_id in (" + sql1 + " where "
                    + criterion1.substring(0, criterion1.lastIndexOf("and"))
                    + ") and ";
              }
              if (floorNum != null && !"".equals(floorNum)) {
                criterion += " p114.floor_num = ? and ";
                parameters.add(floorNum);
              }
              if (roomNum != null && !"".equals(roomNum)) {
                criterion += " p114.room_num = ? and ";
                parameters.add(roomNum);
              }
              // if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
              // criterion += " p205.contract_id in(select p1114.contract_id
              // from pl114 p114, pl006 p006 where p006.head_id = p114.head_id
              // and p114.head_id =? and p114.floor_id =? ) and ";
              // parameters.add(buyhouseorgid);
              // parameters.add(floorId);
              // }

              if (contractId != null && !"".equals(contractId)) {
                criterion += " p110.contract_id = ? and ";
                parameters.add(contractId);
              }
              if (loanKouAcc != null && !"".equals(loanKouAcc)) {
                criterion += " p111.loan_kou_acc = ? and ";
                parameters.add(loanKouAcc);
              }

              if (borrowerName != null && !"".equals(borrowerName)) {
                criterion += " p110.borrower_name = ? and ";
                parameters.add(borrowerName);
              }

              if (cardNum != null && !"".equals(cardNum)) {
                criterion += " p110.card_num = ? and ";
                parameters.add(cardNum);
              }
              if (beginoweMonth != null && !"".equals(beginoweMonth)) {
                criterion += " p205.owe_month >= to_number( ? ) and ";
                parameters.add(beginoweMonth);
              }
              if (endoweMonth != null && !"".equals(endoweMonth)) {
                criterion += " p205.owe_month <= to_number( ? ) and ";
                parameters.add(endoweMonth.trim());
              }
              // ------------------------------------------------//
              if (housetype != null && !"".equals(housetype)) {
                criterion += " p114.house_type = ? and ";
                parameters.add(housetype.trim());
              }

              if (buildAreaMin != null && !"".equals(buildAreaMin)) {
                criterion += " (p114.house_area >= ? or to_number(p114.bargainor_house_area) >= ?) and ";
                parameters.add(buildAreaMin);
                parameters.add(buildAreaMin);
              }
              if (buildAreaMax != null && !"".equals(buildAreaMax)) {
                criterion += " (p114.house_area <= ? or to_number(p114.bargainor_house_area) <= ?) and ";
                parameters.add(buildAreaMax);
                parameters.add(buildAreaMax);
              }
              if (agreement != null && !"1".equals(agreement)) {
                criterion += " p111.contract_id in (select p400.contract_id from pl400 p400 where p400.status='0' and p400.reservea_b='1') and ";
              } else if (agreement != null && !"0".equals(agreement)) {
                criterion += " not exists (select p400.contract_id from pl400 p400 where p400.status='0' and p400.reservea_b='1' and p400.contract_id=p111.contract_id) and ";
              }
              if (applyDate != null && !"".equals(applyDate)) {
                criterion += " p111.reservea_c = ? and ";
                parameters.add(applyDate);
              }
              if (otherMoney != null && !"".equals(otherMoney)) {
                if (otherMoney.equals("0")) {
                  criterion += " p110.other_arrearage = ? and ";
                  parameters.add("1");
                } else {
                  criterion += " p110.other_arrearage = ? and ";
                  parameters.add("0");
                }

              }
              if (startDateMin != null && !"".equals(startDateMin)) {
                criterion += " p111.loan_start_date >= ? and ";
                parameters.add(startDateMin);
              }
              if (startDateMax != null && !"".equals(startDateMax)) {
                criterion += " p111.loan_start_date <= ? and ";
                parameters.add(startDateMax);
              }

              if (isNoAcquittance != null && !"".equals(isNoAcquittance)) {
                if (isNoAcquittance.equals("是")) {
                  String time = securityInfo.getUserInfo().getPlbizDate();
                  criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)<to_date(?,'yyyymmdd') and ";
                  parameters.add(time.trim());
                } else {
                  String time = securityInfo.getUserInfo().getPlbizDate();
                  criterion += "add_months(to_date(p111.loan_start_date,'yyyymmdd'),p111.loan_time_limit)>=to_date(?,'yyyymmdd') and ";
                  parameters.add(time.trim());
                }
              }

              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Object[] obj = (Object[]) query.uniqueResult();
              OverDueinfoQueryTotleDTO overDueinfoQueryTotleDTO = new OverDueinfoQueryTotleDTO();
              if (obj[0] != null && !"".equals(obj[0]))
                overDueinfoQueryTotleDTO
                    .setCount(new Integer(obj[0].toString()).intValue());
              if (obj[1] != null && !"".equals(obj[1]))
                overDueinfoQueryTotleDTO.setOweCorpusTotle(new BigDecimal(
                    obj[1].toString()));
              if (obj[2] != null && !"".equals(obj[2]))
                overDueinfoQueryTotleDTO.setOweInterestTotle(new BigDecimal(
                    obj[2].toString()));
              if (obj[3] != null && !"".equals(obj[3]))
                overDueinfoQueryTotleDTO.setPunishInterest(new BigDecimal(
                    obj[3].toString()));
              return overDueinfoQueryTotleDTO;
            }
          });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return overDueinfoQueryTotleDTO;
  }

  /**
   * author wsh 查询合同编号是否在用户权限下 下达发放通知书 合同是否签订
   * 
   * @param contractId 合同编号
   * @param loanBankList 用户权限银行
   * @return
   */
  public Integer queryContractWrite_wsh(final String contractId,
      final List loanBankList) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.contract_id= ? and a.contract_st=14 and a.is_contract_write=1 ";
            Vector parameters = new Vector();
            String criterion = "";
            if (contractId != null) {
              parameters.add(contractId);
            }
            if (loanBankList != null && loanBankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanBankList.size(); i++) {
                criterion += " a.loan_bank_id = ? or ";
                parameters.add(loanBankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (criterion.length() != 0)
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            Integer temp_count = new Integer(0);
            if (obj != null) {
              temp_count = new Integer(obj.toString());
            }
            return temp_count;
          }
        });
    return count;

  }

  /**
   * hanl 根据合同编号求出还款方式和贷款余额
   * 
   * @param contractIdHl
   * @return
   */
  public LoandeskaccqueryTcDTO queryloanMoneyLoanmood(final String contractIdHl) {
    LoandeskaccqueryTcDTO loandeskaccqueryTcDTO = null;
    try {
      loandeskaccqueryTcDTO = (LoandeskaccqueryTcDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.loan_mode,p.overplus_loan_money from pl111 p where p.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, contractIdHl);
              LoandeskaccqueryTcDTO loandeskaccqueryTcDTO = new LoandeskaccqueryTcDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();

                if (obj[0] != null) {
                  loandeskaccqueryTcDTO.setPayloanmood(obj[0].toString());
                }
                if (obj[1] != null) {
                  loandeskaccqueryTcDTO.setLoanleftmoney(obj[1].toString());
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
   * 获取已经启用得最新得利率 shiy 求利率
   * 
   * @param bankId
   * @param loantype
   * @return
   */

  public List findUseMontRate_sy(final String bankId, final String loantype) {
    List list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl001.loan_month_rate,pl001.status  from pl001 where pl001.loan_rate_id = (select max(qpl001.loan_rate_id)"
                + "   from pl001 qpl001, pl002 qpl002"
                + "   where qpl001.loan_rate_type = '"
                + loantype
                + "'"
                + "  and qpl002.office = qpl001.office"
                + "     and qpl002.loan_bank_id = " + bankId + ")";
            Query query = session.createSQLQuery(hql);
            return query.list();
          }
        });

    return list;
  }

  /*
   * 查询个贷财务对账列表信息 郭婧平 20080411
   */
  public List queryCheckQueryPlFnList(final String contractid,
      final String borrowername, final String loankouacc, final String cardnum,
      final String loanstartdateSt, final String loanstartdateEnd,
      final String orderBy, final String orderother, final int start,
      final int pageSize, final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      Validate.isTrue(orderother == null || "ASC".equalsIgnoreCase(orderother)
          || "DESC".equalsIgnoreCase(orderother));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p11.contract_id,"
              + "p11.loan_kou_acc,"
              + "p10.borrower_name,"
              + "p10.card_num,"
              + "p11.loan_bank_id,"
              + "p11.loan_money,"
              + "p11.loan_time_limit,"
              + "p11.ovaer_loan_repay,"
              + "p11.no_back_money,"
              + "p11.bail_balance,"
              + "p11.loan_repay_day,"
              + "tt.corpus,"
              + "tt.interest,"
              + "tt.punish,"
              + "p11.loan_mode,"
              + "p11.overplus_loan_money "
              + "from (select p203.contract_id as contractid,sum(p203.real_corpus) as corpus,sum(p203.real_interest) as interest,sum(p203.real_punish_interest) as punish from pl203 p203, pl111 p111 where p203.contract_id = p111.contract_id group by p203.contract_id) tt,pl110 p10,pl111 p11 "
              + "where p10.contract_id=p11.contract_id  "
              + "and p11.contract_id=tt.contractid "
              + "and p11.contract_st in('11','12') " +
              // "and p14.contract_id=p10.contract_id " +
              "and p11.loan_bank_id " + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";
          if (contractid != null && !"".equals(contractid)) {
            criterion += " p11.contract_id like ? and ";
            parameters.add("%" + contractid + "%");
          }
          if (loankouacc != null && !"".equals(loankouacc)) {
            criterion += " p11.loan_kou_acc like ? and ";
            parameters.add("%" + loankouacc + "%");
          }
          if (borrowername != null && !"".equals(borrowername)) {
            criterion += "  p10.borrower_name like ? and ";
            parameters.add("%" + borrowername + "%");
          }
          if (cardnum != null && !"".equals(cardnum)) {
            criterion += " (p10.card_num =? or p10.standby_card_num = ?) and ";
            parameters.add(cardnum);
            parameters.add(cardnum);
          }
          if (loanstartdateSt != null && !"".equals(loanstartdateSt)) {
            criterion += " p11.loan_start_date>=? and ";
            parameters.add(loanstartdateSt);
          }
          if (loanstartdateEnd != null && !"".equals(loanstartdateEnd)) {
            criterion += " p11.loan_start_date<=? and ";
            parameters.add(loanstartdateEnd);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p11.contract_id ";

          String od = orderother;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              CheckQueryPlFnDTO checkQueryPlFnDTO = new CheckQueryPlFnDTO();
              if (obj[0] != null) {
                checkQueryPlFnDTO.setContractid(obj[0].toString());//
              }
              if (obj[1] != null) {
                checkQueryPlFnDTO.setLoankouacc(obj[1].toString());//
              }
              if (obj[2] != null) {
                checkQueryPlFnDTO.setBorrowername(obj[2].toString());//
              }
              if (obj[3] != null) {
                checkQueryPlFnDTO.setCardnum(obj[3].toString());//
              }
              if (obj[4] != null) {
                checkQueryPlFnDTO.setLoanbank(obj[4].toString());//
              }
              if (obj[5] != null) {
                checkQueryPlFnDTO.setLoanmoney(obj[5].toString());//
              }
              if (obj[6] != null) {
                checkQueryPlFnDTO.setLoanlimit(obj[6].toString());//
              }
              if (obj[7] != null) {
                checkQueryPlFnDTO.setOveaerloanrepay(obj[7].toString());//
              }
              if (obj[8] != null) {
                checkQueryPlFnDTO.setNobackmoney(obj[8].toString());//
              }
              if (obj[9] != null) {
                checkQueryPlFnDTO.setBallbalance(obj[9].toString());//
              }
              if (obj[10] != null) {
                checkQueryPlFnDTO.setPayday(obj[10].toString());//
              }
              if (obj[11] != null) {
                checkQueryPlFnDTO.setSrealcorpus(obj[11].toString());//
              }
              if (obj[12] != null) {
                checkQueryPlFnDTO.setSrealinterest(obj[12].toString());//
              }
              if (obj[13] != null) {
                checkQueryPlFnDTO.setSrealpunishinterest(obj[13].toString());//
              }
              if (obj[14] != null) {
                checkQueryPlFnDTO.setLoanmode(obj[14].toString());//
              }
              if (obj[15] != null) {
                checkQueryPlFnDTO.setOverplusloanmoney(obj[15].toString());//
              }
              temp_list.add(checkQueryPlFnDTO);
            }

          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 查询个贷财务对账列表信息count 郭婧平 20080411
   */
  public List queryCheckQueryPlFnListCount(final String contractid,
      final String borrowername, final String loankouacc, final String cardnum,
      final String loanstartdateSt, final String loanstartdateEnd,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p11.contract_id,"
              + "p11.loan_kou_acc,"
              + "p10.borrower_name,"
              + "p10.card_num,"
              + "p11.loan_bank_id,"
              + "p11.loan_money,"
              + "p11.loan_time_limit,"
              + "p11.ovaer_loan_repay,"
              + "p11.no_back_money,"
              + "p11.bail_balance,"
              + "p11.loan_repay_day,"
              + "tt.corpus,"
              + "tt.interest,"
              + "tt.punish,"
              + "p11.loan_mode,"
              + "p11.overplus_loan_money "
              + "from (select p203.contract_id as contractid,sum(p203.real_corpus) as corpus,sum(p203.real_interest) as interest,sum(p203.real_punish_interest) as punish from pl203 p203, pl111 p111 where p203.contract_id = p111.contract_id group by p203.contract_id) tt,pl110 p10,pl111 p11 "
              + "where p10.contract_id=p11.contract_id  "
              + "and p11.contract_id=tt.contractid "
              + "and p11.contract_st in('11','12') " +
              // "and p14.contract_id=p10.contract_id " +
              "and p11.loan_bank_id " + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";
          if (contractid != null && !"".equals(contractid)) {
            criterion += " p11.contract_id like ? and ";
            parameters.add("%" + contractid + "%");
          }
          if (loankouacc != null && !"".equals(loankouacc)) {
            criterion += " p11.loan_kou_acc like ? and ";
            parameters.add("%" + loankouacc + "%");
          }
          if (borrowername != null && !"".equals(borrowername)) {
            criterion += "  p10.borrower_name like ? and ";
            parameters.add("%" + borrowername + "%");
          }

          if (cardnum != null && !"".equals(cardnum)) {
            criterion += " (p10.card_num =? or p10.standby_card_num = ?) and  ";
            parameters.add(cardnum);
            parameters.add(cardnum);
          }

          if (loanstartdateSt != null && !"".equals(loanstartdateSt)) {
            criterion += " p11.loan_start_date>=? and ";
            parameters.add(loanstartdateSt);
          }
          if (loanstartdateEnd != null && !"".equals(loanstartdateEnd)) {
            criterion += " p11.loan_start_date<=? and ";
            parameters.add(loanstartdateEnd);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

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

  /*
   * 查询借款人账列表信息 郭婧平 20080411
   */
  public List queryCheckQueryPlFnTBList(final String contractid,
      final String officecode, final String loanbank, final String empid,
      final String empname, final String plbizst, final String bizdateSt,
      final String bizdateEnd, final String fnbizst, final String orderBy,
      final String orderother, final int start, final int pageSize,
      final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      Validate.isTrue(orderother == null || "ASC".equalsIgnoreCase(orderother)
          || "DESC".equalsIgnoreCase(orderother));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct pl203.loan_kou_acc,"
              + "pl203.contract_id,"
              + "pl202.doc_num,"
              + "pl202.note_num,"
              + "pl110.emp_id,"
              + "pl110.borrower_name,"
              + "pl202.biz_type,"
              + "pl202.biz_date,"
              + "pl202.biz_st,"
              + "fn201.sett_date,"
              + "fn201.credence_st,"
              + "pl203.occur_money,"
              + "pl202.real_corpus,"
              + "fn201.credence_num,"
              + "(select nvl(abs(sum(p202.real_corpus-p203.occur_money)),0) "
              + "from pl202 p202,pl203 p203 "
              + "where p202.flow_head_id < pl202.flow_head_id and p202.flow_head_id=p203.flow_head_id) + pl202.real_corpus + pl203.occur_money "
              + "from pl203, pl202, pl110, fn201, pl111 "
              + "where pl203.flow_head_id = pl202.flow_head_id "
              + "and pl203.contract_id = pl110.contract_id "
              + "and pl202.note_num = fn201.sett_num "
              + "and pl203.contract_id = pl111.contract_id "
              + "and pl111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";
          if (contractid != null && !"".equals(contractid)) {
            criterion += " pl203.contract_id=? and ";
            parameters.add(contractid);
          }
          if (officecode != null && !"".equals(officecode)) {
            criterion += " pl110.office=? and ";
            parameters.add(officecode);
          }
          if (loanbank != null && !"".equals(loanbank)) {
            criterion += " pl111.loan_bank_id=? and ";
            parameters.add(loanbank);
          }
          if (empid != null && !"".equals(empid)) {
            criterion += " pl110.emp_id=? and ";
            parameters.add(empid);
          }
          if (empname != null && !"".equals(empname)) {
            criterion += " pl110.borrower_name=? and ";
            parameters.add(empname);
          }
          if (plbizst != null && !"".equals(plbizst)) {
            criterion += " pl202.biz_st=? and ";
            parameters.add(plbizst);
          }
          if (bizdateSt != null && !"".equals(bizdateSt)) {
            criterion += " pl202.biz_date>=? and ";
            parameters.add(bizdateSt);
          }
          if (bizdateEnd != null && !"".equals(bizdateEnd)) {
            criterion += " pl202.biz_date<=? and ";
            parameters.add(bizdateEnd);
          }
          if (fnbizst != null && !"".equals(fnbizst)) {
            criterion += " fn201.credence_st=? and ";
            parameters.add(fnbizst);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " pl203.contract_id ";

          String od = orderother;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              CheckQueryPlFnTBDTO checkQueryPlFnTBDTO = new CheckQueryPlFnTBDTO();
              if (obj[1] != null) {
                checkQueryPlFnTBDTO.setContractid(obj[1].toString());//
              }
              if (obj[0] != null) {
                checkQueryPlFnTBDTO.setLoankouacc(obj[0].toString());//
              }
              if (obj[2] != null) {
                checkQueryPlFnTBDTO.setCredencenum(obj[2].toString());//
              }
              if (obj[3] != null) {
                checkQueryPlFnTBDTO.setNotenum(obj[3].toString());//
              }
              if (obj[4] != null) {
                checkQueryPlFnTBDTO.setEmpid(obj[4].toString());//
              }
              if (obj[5] != null) {
                checkQueryPlFnTBDTO.setEmpname(obj[5].toString());//
              }
              if (obj[6] != null) {
                checkQueryPlFnTBDTO.setBiztype(obj[6].toString());//
              }
              if (obj[7] != null) {
                checkQueryPlFnTBDTO.setBizdate(obj[7].toString());//
              }
              if (obj[8] != null) {
                checkQueryPlFnTBDTO.setPlbizst(obj[8].toString());//
              }
              if (obj[9] != null) {
                checkQueryPlFnTBDTO.setSettledate(obj[9].toString());//
              }
              if (obj[10] != null) {
                checkQueryPlFnTBDTO.setCredenceSt(obj[10].toString());//
              }
              if (obj[11] != null) {
                checkQueryPlFnTBDTO.setDebit(obj[11].toString());//
              }
              if (obj[12] != null) {
                checkQueryPlFnTBDTO.setCredit(obj[12].toString());//
              }
              if (obj[13] != null) {
                checkQueryPlFnTBDTO.setFncredencenum(obj[13].toString());//
              }
              if (obj[14] != null) {
                checkQueryPlFnTBDTO.setBalance(new BigDecimal(200000).subtract(
                    new BigDecimal(obj[14].toString())).toString());//
              }
              temp_list.add(checkQueryPlFnTBDTO);
            }

          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 查询借款人账列表信息count----print 郭婧平 20080411
   */
  public List queryCheckQueryPlFnTBListCount(final String contractid,
      final String officecode, final String loanbank, final String empid,
      final String empname, final String plbizst, final String bizdateSt,
      final String bizdateEnd, final String fnbizst,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct pl203.loan_kou_acc,"
              + "pl203.contract_id,"
              + "pl202.doc_num,"
              + "pl202.note_num,"
              + "pl110.emp_id,"
              + "pl110.borrower_name,"
              + "pl202.biz_type,"
              + "pl202.biz_date,"
              + "pl202.biz_st,"
              + "fn201.sett_date,"
              + "fn201.credence_st,"
              + "pl203.occur_money,"
              + "pl202.real_corpus,"
              + "fn201.credence_num,"
              + "(select nvl(abs(sum(p202.real_corpus-p203.occur_money)),0) "
              + "from pl202 p202,pl203 p203 "
              + "where p202.flow_head_id < pl202.flow_head_id and p202.flow_head_id=p203.flow_head_id) + pl202.real_corpus + pl203.occur_money "
              + "from pl203, pl202, pl110, fn201, pl111 "
              + "where pl203.flow_head_id = pl202.flow_head_id "
              + "and pl203.contract_id = pl110.contract_id "
              + "and pl202.note_num = fn201.sett_num "
              + "and pl203.contract_id = pl111.contract_id "
              + "and pl111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";
          if (contractid != null && !"".equals(contractid)) {
            criterion += " pl203.contract_id=? and ";
            parameters.add(contractid);
          }
          if (officecode != null && !"".equals(officecode)) {
            criterion += " pl110.office=? and ";
            parameters.add(officecode);
          }
          if (loanbank != null && !"".equals(loanbank)) {
            criterion += " pl111.loan_bank_id=? and ";
            parameters.add(loanbank);
          }
          if (empid != null && !"".equals(empid)) {
            criterion += " pl110.emp_id=? and ";
            parameters.add(empid);
          }
          if (empname != null && !"".equals(empname)) {
            criterion += " pl110.borrower_name=? and ";
            parameters.add(empname);
          }
          if (plbizst != null && !"".equals(plbizst)) {
            criterion += " pl202.biz_st=? and ";
            parameters.add(plbizst);
          }
          if (bizdateSt != null && !"".equals(bizdateSt)) {
            criterion += " pl202.biz_date>=? and ";
            parameters.add(bizdateSt);
          }
          if (bizdateEnd != null && !"".equals(bizdateEnd)) {
            criterion += " pl202.biz_date<=? and ";
            parameters.add(bizdateEnd);
          }
          if (fnbizst != null && !"".equals(fnbizst)) {
            criterion += " fn201.credence_st=? and ";
            parameters.add(fnbizst);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              CheckQueryPlFnTBDTO checkQueryPlFnTBDTO = new CheckQueryPlFnTBDTO();
              if (obj[1] != null) {
                checkQueryPlFnTBDTO.setContractid(obj[1].toString());//
              }
              if (obj[0] != null) {
                checkQueryPlFnTBDTO.setLoankouacc(obj[0].toString());//
              }
              if (obj[2] != null) {
                checkQueryPlFnTBDTO.setCredencenum(obj[2].toString());//
              }
              if (obj[3] != null) {
                checkQueryPlFnTBDTO.setNotenum(obj[3].toString());//
              }
              if (obj[4] != null) {
                checkQueryPlFnTBDTO.setEmpid(obj[4].toString());//
              }
              if (obj[5] != null) {
                checkQueryPlFnTBDTO.setEmpname(obj[5].toString());//
              }
              if (obj[6] != null) {
                checkQueryPlFnTBDTO.setBiztype(obj[6].toString());//
              }
              if (obj[7] != null) {
                checkQueryPlFnTBDTO.setBizdate(obj[7].toString());//
              }
              if (obj[8] != null) {
                checkQueryPlFnTBDTO.setPlbizst(obj[8].toString());//
              }
              if (obj[9] != null) {
                checkQueryPlFnTBDTO.setSettledate(obj[9].toString());//
              }
              if (obj[10] != null) {
                checkQueryPlFnTBDTO.setCredenceSt(obj[10].toString());//
              }
              if (obj[11] != null) {
                checkQueryPlFnTBDTO.setDebit(obj[11].toString());//
              }
              if (obj[12] != null) {
                checkQueryPlFnTBDTO.setCredit(obj[12].toString());//
              }
              if (obj[13] != null) {
                checkQueryPlFnTBDTO.setFncredencenum(obj[13].toString());//
              }
              if (obj[14] != null) {
                checkQueryPlFnTBDTO.setBalance(new BigDecimal(200000).subtract(
                    new BigDecimal(obj[14].toString())).toString());//
              }
              temp_list.add(checkQueryPlFnTBDTO);
            }

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
   * 个贷财务对账查询---职工流水
   * 
   * @return
   * @author 郭婧平
   */
  public List queryCheckQueryPlFnTCList(final int start, final String orderBy,
      final String order, final int pageSize, final int page,
      final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String noteNum) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select docnum,loankouacc, contractId,borrowername,bizType,occurMoney,realcorpus,reclaimAccrual,realPunishInterest,badDebt,putUpMoney,bail,bailAccrual,bizSt,bizDate,wrongBizType,loanBankID, makePerson,flowheadID,notenum from "
              + "("
              + " select pl202.doc_num as docnum,"
              + "'' as loankouacc, "
              + "'' as contractId,"
              + "'' as borrowername,"
              + "pl202.biz_type as bizType,"
              + "0 as occurMoney,"
              + "nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + "nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + "nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + "0 as badDebt,"
              + "nvl(pl202.occur_money, 0) as putUpMoney,"
              + "nvl(pl202.occur_money,0) as bail,"
              + "nvl(pl202.other_interest,0) as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum"
              + " from PL202 pl202"
              + " where pl202.biz_type = '5' or (pl202.biz_type = '12' and pl202.wrong_biz_type = '5') or pl202.biz_type = '15'"
              + " union"
              + " select pl202.doc_num as docnum,"
              + "pl203.loan_kou_acc as loankouacc,"
              + "pl203.contract_id as contractId,"
              + "pl110.borrower_name  as borrowername,"
              + "pl202.biz_type as bizType,"
              + "nvl(pl202.occur_money,0) as occurMoney,"
              + "nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + "nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + "nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + "nvl(pl202.occur_money,0) as badDebt,"
              + "nvl(pl202.occur_money,0) as putUpMoney,"
              + "nvl(pl202.occur_money,0) as bail,"
              + "nvl(pl202.other_interest,0) as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum"
              + " from PL203 pl203, PL202 pl202,PL110 pl110"
              + " where pl202.flow_head_id = pl203.flow_head_id "
              + " and ("
              + " (pl202.biz_type in"
              + "('1', '2', '3', '4', '6', '7', '8', '9', '11', '13', '14') )"
              + " or (pl202.biz_type = '12' and  pl202.wrong_biz_type in('1', '2', '6', '7') )"
              + " or (pl202.biz_type = '12' and pl202.wrong_biz_type is null )"
              + ") " + " and pl110.contract_id=pl203.contract_id" + ")";
          String criterion = "";
          Vector parameters = new Vector();
          String ob = orderBy;
          if (ob == null)
            ob = " flowheadID ";

          String od = order;
          if (od == null)
            od = " DESC";

          if (docNum != null && !"".equals(docNum)) {
            criterion += " docnum = ? and ";
            parameters.add(docNum);
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " contractId  = ? and ";
            parameters.add(contractId);
          }

          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " loanKouAcc = ? and ";
            parameters.add(loanKouAcc);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " borrowername = ? and ";
            parameters.add(borrowerName);
          }
          if (makePerson != null && !"".equals(makePerson)) {
            criterion += " makePerson = ? and ";
            parameters.add(makePerson);
          }
          if (bizType != null && !"".equals(bizType)) {
            criterion += " bizType = ? and ";
            parameters.add(bizType);
          }

          if (bizSt != null && !"".equals(bizSt)) {
            criterion += "  bizSt = ? and ";
            parameters.add(bizSt);
          } else {
            criterion += "  bizSt in (4,5,6) and ";
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " loanBankID = ? and ";
            parameters.add(loanBankName);
          }

          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " bizDate >= ? and ";
            parameters.add(beginBizDate);
          }

          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " bizDate <= ? and ";
            parameters.add(endBizDate);
          }

          if (noteNum != null && !"".equals(noteNum)) {
            criterion += " notenum = ? and ";
            parameters.add(noteNum);
          }

          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + od;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
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
   * 个贷财务对账查询---职工流水---count
   * 
   * @return
   * @author 郭婧平
   */
  public List queryCheckQueryPlFnTCListCount(final SecurityInfo securityInfo,
      final String docNum, final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String noteNum) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select docnum,loankouacc, contractId,borrowername,bizType,occurMoney,realcorpus,reclaimAccrual,realPunishInterest,badDebt,putUpMoney,bail,bailAccrual,bizSt,bizDate,wrongBizType,loanBankID, makePerson,flowheadID,notenum from "
              + "("
              + " select pl202.doc_num as docnum,"
              + "'' as loankouacc, "
              + "'' as contractId,"
              + "'' as borrowername,"
              + "pl202.biz_type as bizType,"
              + "0 as occurMoney,"
              + "nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + "nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + "nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + "0 as badDebt,"
              + "nvl(pl202.occur_money, 0) as putUpMoney,"
              + "nvl(pl202.occur_money,0) as bail,"
              + "nvl(pl202.other_interest,0) as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum"
              + " from PL202 pl202"
              + " where pl202.biz_type = '5' or (pl202.biz_type = '12' and pl202.wrong_biz_type = '5') or pl202.biz_type = '15'"
              + " union"
              + " select pl202.doc_num as docnum,"
              + "pl203.loan_kou_acc as loankouacc,"
              + "pl203.contract_id as contractId,"
              + "pl110.borrower_name  as borrowername,"
              + "pl202.biz_type as bizType,"
              + "nvl(pl202.occur_money,0) as occurMoney,"
              + "nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + "nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + "nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + "nvl(pl202.occur_money,0) as badDebt,"
              + "nvl(pl202.occur_money,0) as putUpMoney,"
              + "nvl(pl202.occur_money,0) as bail,"
              + "nvl(pl202.other_interest,0) as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum"
              + " from PL203 pl203, PL202 pl202,PL110 pl110"
              + " where pl202.flow_head_id = pl203.flow_head_id "
              + " and ("
              + " (pl202.biz_type in"
              + "('1', '2', '3', '4', '6', '7', '8', '9', '11', '13', '14') )"
              + " or (pl202.biz_type = '12' and  pl202.wrong_biz_type in('1', '2', '6', '7') )"
              + " or (pl202.biz_type = '12' and pl202.wrong_biz_type is null )"
              + ") " + " and pl110.contract_id=pl203.contract_id" + ")";
          String criterion = "";
          Vector parameters = new Vector();

          if (docNum != null && !"".equals(docNum)) {
            criterion += " docnum = ? and ";
            parameters.add(docNum);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " contractId  = ? and ";
            parameters.add(contractId);
          }

          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " loanKouAcc = ? and ";
            parameters.add(loanKouAcc);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " borrowername = ? and ";
            parameters.add(borrowerName);
          }
          if (makePerson != null && !"".equals(makePerson)) {
            criterion += " makePerson = ? and ";
            parameters.add(makePerson);
          }
          if (bizType != null && !"".equals(bizType)) {
            criterion += " bizType = ? and ";
            parameters.add(bizType);
          }

          if (bizSt != null && !"".equals(bizSt)) {
            criterion += "  bizSt = ? and ";
            parameters.add(bizSt);
          } else {
            criterion += "  bizSt in (4,5,6) and ";
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " loanBankID = ? and ";
            parameters.add(loanBankName);
          }

          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " bizDate >= ? and ";
            parameters.add(beginBizDate);
          }

          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " bizDate <= ? and ";
            parameters.add(endBizDate);
          }

          if (noteNum != null && !"".equals(noteNum)) {
            criterion += " notenum = ? and ";
            parameters.add(noteNum);
          }

          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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
   * 年度贷款统计对照
   * 
   * @author yqf
   * @param bizDate
   * @param officecode
   * @param securityInfo
   * @return
   */
  public YearloanDTO queryYearLoanContrast(final String bizDate,
      final String officeCode, final SecurityInfo securityInfo) {
    YearloanDTO dto = new YearloanDTO();
    try {
      dto = (YearloanDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select count(p111.contract_id),"
                  + " sum(p202.occur_money),"
                  + " nvl(sum(p114.house_area),0)+nvl(sum(p114.bargainor_house_area),0),"
                  + " nvl(sum(p114.house_totle_price),0) + nvl(sum(p114.bargainor_totle_price),0)"
                  + " from PL111 p111, PL114 p114,PL110 p110, PL202 p202, PL203 p203"
                  + " where p111.contract_id = p114.contract_id"
                  + " and p110.contract_id = p111.contract_id"
                  + " and p111.contract_id = p203.contract_id"
                  + " and p202.flow_head_id = p203.flow_head_id"
                  + " and (p202.biz_type = 1 and p202.is_adjust is null)"
                  + " and p111.contract_st in (10, 11, 12, 13)"
                  + " and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();

              Vector parameters = new Vector();
              String criterion = "";
              if (bizDate != null && !"".equals(bizDate)) {
                criterion += " substr(p202.biz_date,0,6) = ? and ";
                parameters.add(bizDate);
              }
              if (officeCode != null && !"".equals(officeCode)) {
                criterion += " p110.office=? and ";
                parameters.add(officeCode);
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);

              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              Iterator it = query.list().iterator();
              List temp_list = new ArrayList();
              Object obj[] = null;
              YearloanDTO yearloanDTO = new YearloanDTO();
              if (it.hasNext()) {
                obj = (Object[]) it.next();
                if (obj != null) {
                  yearloanDTO.setMonth(bizDate);
                  if (obj[0] != null) {
                    yearloanDTO.setFfhs(Integer.parseInt(obj[0].toString()));//
                  }
                  if (obj[1] != null) {
                    yearloanDTO.setFfje(new BigDecimal(obj[1].toString()));//
                  }
                  if (obj[2] != null) {
                    yearloanDTO.setJjmj(new BigDecimal(obj[2].toString()));//
                  }
                  if (obj[3] != null) {
                    yearloanDTO.setFfzj(new BigDecimal(obj[3].toString()));//
                  }
                  temp_list.add(yearloanDTO);
                }

              }
              return yearloanDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }

  public AdvancepayloanTaDTO queryfindAdvancepayloanTaDTO(
      final String loadKouAcc, final String bizDate) throws Exception {
    AdvancepayloanTaDTO advancepayloanTaDTO = null;
    try {
      advancepayloanTaDTO = (AdvancepayloanTaDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select t.loan_kou_acc," + " t.contract_id,"
                  + " t2.borrower_name," + " t2.card_kind," + " t2.card_num,"
                  + " t.loan_repay_day, " + " t.overplus_loan_money,"
                  + " t.loan_mode," + " t.overplus_limite "
                  + " from pl111 t,pl110 t2 "
                  + " where t.contract_id=t2.contract_id and t.loan_kou_acc=?";
              String contractId = "";
              Object[] obj = null;
              Query query = session.createSQLQuery(hql);
              query.setString(0, loadKouAcc);
              obj = (Object[]) query.uniqueResult();
              AdvancepayloanTaDTO dto = null;
              if (obj != null) {
                dto = new AdvancepayloanTaDTO();
                if (obj[0] != null) {
                  dto.setLoanKouAcc(obj[0].toString());
                }
                if (obj[1] != null) {
                  contractId = obj[1].toString();
                  dto.setContractId(obj[1].toString());
                }
                if (obj[2] != null) {
                  dto.setBorrowerName(obj[2].toString());
                }
                if (obj[3] != null) {
                  dto.setCardKind(obj[3].toString());
                }
                if (obj[4] != null) {
                  dto.setCardNum(obj[4].toString());
                }
                if (obj[5] != null) {
                  int day = Integer.parseInt(obj[5].toString());
                  int bizday = Integer.parseInt(bizDate.substring(6, 8));
                  String date = "";
                  String ssql = " select nvl((t.should_corpus+t.should_interest),0) from pl201 t where t.loan_kou_yearmonth = ? and t.contract_id=? ";
                  if (bizday >= day) {
                    date = bizDate.substring(0, 6);
                  }
                  if (bizday < day) {
                    date = new Integer(Integer
                        .parseInt(bizDate.substring(0, 6)) + 1).toString();
                    if ("13".equals(String.valueOf(date).substring(4, 6))) {
                      date = new Integer(Integer.parseInt(bizDate.substring(0,
                          4)) + 1).toString()
                          + "01";
                    }

                  }
                  Query query2 = session.createSQLQuery(ssql);
                  query2.setString(0, new Integer(date).toString());
                  query2.setString(1, contractId);
                  String corpusIntegerest = "";
                  if (query2.uniqueResult() == null) {
                    corpusIntegerest = "0";
                  }
                  dto.setCorpusInterest(corpusIntegerest);
                }
                if (obj[6] != null) {
                  dto.setOverplusLoanMoney(obj[6].toString());
                }
                if (obj[7] != null) {
                  dto.setCreditType(obj[7].toString());
                }
                if (obj[8] != null) {
                  dto.setPre_term(obj[8].toString());
                }
              }
              return dto;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return advancepayloanTaDTO;
  }

  public void insertPl112_1(final String contractId, final String new_term,
      final String type, final String operator) throws Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String date = new Date().toLocaleString().substring(0, 10);
      String sql = " insert into pl112_1 (id,contract_id,overplus_limite,chg_type,status,operator,op_time) values (SEQ_PL112.NEXTVAL,'"
          + contractId
          + "','"
          + new_term
          + "','"
          + type
          + "','"
          + "0"
          + "','"
          + operator + "',to_date('" + date + "','yyyy-mm-dd'))";
      st.executeUpdate(sql);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deletePl112_1(final String id, final String operator)
      throws Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = " update pl112_1 set status='2',operator='" + operator
          + "' where id = '" + id + "'";
      st.executeUpdate(sql);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List queryTbList(final String contractId, final String borrowerName,
      final String cardNum, final String orderBy, final String order,
      final int start, final int pageSize, final int page, final String status)
      throws Exception {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select t1.contract_id,t2.borrower_name,t2.card_num,to_number(t1.overplus_limite),t1.chg_type,t1.operator,t1.op_time,t1.status,t1.id from pl112_1 t1,pl110 t2 where t1.contract_id=t2.contract_id and t1.status not in (2)";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " t1.contract_id=?  and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " t2.borrower_name=?  and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " t2.card_num=?  and ";
            parameters.add(cardNum);
          }
          if (status != null && !"".equals(status)) {
            criterion += " t1.status=?  and ";
            parameters.add(status);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " t1.contract_id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
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
          }

          List temp_list = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            AdvancepayloanTbDTO dto = new AdvancepayloanTbDTO();
            if (obj[0] != null) {
              dto.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setBorrowerName(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setCardNum(obj[2].toString());
            }
            if (obj[3] != null) {
              dto.setNew_term(obj[3].toString());
            }
            if (obj[4] != null) {
              if ("1".equals(obj[4].toString())) {
                dto.setType("延长");
              }
              if ("2".equals(obj[4].toString())) {
                dto.setType("缩短");
              }
            }
            if (obj[5] != null) {
              dto.setOperator(obj[5].toString());
            }
            if (obj[6] != null) {
              dto.setDate(obj[6].toString());
            }
            if (obj[7] != null) {
              if ("0".equals(obj[7].toString())) {// 未启用
                dto.setStatus("未启用");
              }
              if ("1".equals(obj[7].toString())) {// 已启用
                dto.setStatus("已启用");
              }
            }
            if (obj[8] != null) {
              dto.setId(obj[8].toString());
            }
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

  public List countTbList(final String contractId, final String borrowerName,
      final String cardNum, final String orderBy, final String order,
      final int start, final int pageSize, final String status)
      throws Exception {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select t1.contract_id,t2.borrower_name,t2.card_num,t1.overplus_limite,t1.chg_type,t1.operator,t1.op_time,t1.status from pl112_1 t1,pl110 t2 where t1.contract_id=t2.contract_id and t1.status not in (2)";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " t1.contract_id=?  and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " t2.borrower_name=?  and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " t2.card_num=?  and ";
            parameters.add(cardNum);
          }
          if (status != null && !"".equals(status)) {
            criterion += " t1.status=?  and ";
            parameters.add(status);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " t1.contract_id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List temp_list = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            AdvancepayloanTbDTO dto = new AdvancepayloanTbDTO();
            if (obj[0] != null) {
              dto.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setBorrowerName(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setCardNum(obj[2].toString());
            }
            if (obj[3] != null) {
              dto.setNew_term(obj[3].toString());
            }
            if (obj[4] != null) {
              dto.setType(obj[4].toString());
            }
            if (obj[5] != null) {
              dto.setOperator(obj[5].toString());
            }
            if (obj[6] != null) {
              dto.setDate(obj[6].toString());
            }
            if (obj[7] != null) {
              if ("0".equals(obj[7].toString())) {// 未启用
                dto.setStatus("未启用");
              }
              if ("1".equals(obj[7].toString())) {// 已启用
                dto.setStatus("已启用");
              }
            }
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

  public List queryContractchangeList(final String contractId,
      final String borrowerName, final String cardNum, final String startDate,
      final String endDate, final String orderBy, final String order,
      final int start, final int pageSize, final int page, final String type,
      final SecurityInfo securityInfo) throws Exception {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "";
          if ("1".equals(type)) {
            hql = " select t1.id,t1.contract_id,t2.borrower_name,t2.card_num,t1.deadline,t1.type,t1.bizdate,t1.mark_a,t1.mark_b,t1.mark_c,t1.mark_d,t1.aheadmoney,t1.overplus_loan_money,t1.plflowid  from pl101_1 t1,pl110 t2,pl111 t3 where t1.contract_id=t2.contract_id and t3.contract_id=t2.contract_id and t3.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          }
          if ("2".equals(type)) {
            hql = " select t1.id,t1.contract_id,t2.borrower_name,t2.card_num,t1.deadline,t1.type,t1.bizdate,t1.mark_a,t1.mark_b,t1.mark_c,t1.mark_d,t1.aheadmoney,t1.overplus_loan_money,t1.plflowid  from pl101_1 t1,pl110 t2, pl111 t3 where t1.contract_id=t2.contract_id and t1.mark_b=0 and t3.contract_id=t2.contract_id and t3.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          }
          if ("3".equals(type)) {
            hql = " select t1.id,t1.contract_id,t2.borrower_name,t2.card_num,t1.deadline,t1.type,t1.bizdate,t1.mark_a,t1.mark_b,t1.mark_c,t1.mark_d,t1.aheadmoney,t1.overplus_loan_money,t1.plflowid  from pl101_1 t1,pl110 t2, pl111 t3 where t1.contract_id=t2.contract_id and t1.mark_c=0 and t1.mark_b=1 and t3.contract_id=t2.contract_id and t3.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          }

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " t1.contract_id=?  and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " t2.borrower_name=?  and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " t2.card_num=?  and ";
            parameters.add(cardNum);
          }
          if (startDate != null && !"".equals(startDate)) {
            criterion += " t1.bizdate>=?  and ";
            parameters.add(startDate);
          }
          if (endDate != null && !"".equals(endDate)) {
            criterion += " t1.bizdate<=?  and ";
            parameters.add(endDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " t1.bizdate ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
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
          }

          List temp_list = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ContractchangeDTO dto = new ContractchangeDTO();
            if (obj[0] != null) {
              dto.setId(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setContractId(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setBorrowerName(obj[2].toString());
            }
            if (obj[3] != null) {
              dto.setCardNum(obj[3].toString());
            }
            if (obj[4] != null) {
              dto.setNew_term(obj[4].toString());
            }
            if (obj[5] != null) {
              if ("1".equals(obj[5].toString())) {
                dto.setType("提前部分还款");
              }
              if ("2".equals(obj[5].toString())) {
                dto.setType("延长还款期限");
              }
              if ("3".equals(obj[5].toString())) {
                dto.setType("缩短还款期限");
              }
              if ("4".equals(obj[5].toString())) {
                dto.setType("提前部分还款、延长还款期限");
              }
              if ("5".equals(obj[5].toString())) {
                dto.setType("提前部分还款、缩短还款期限");
              }
            }
            if (obj[6] != null) {
              dto.setDate(obj[6].toString());
            }
            if (obj[7] != null) {
              if ("0".equals(obj[7].toString())) {
                dto.setMark_a("否");
              }
              if ("1".equals(obj[7].toString())) {
                dto.setMark_a("是");
              }
            }
            if (obj[8] != null) {
              if ("0".equals(obj[8].toString())) {
                dto.setMark_b("否");
              }
              if ("1".equals(obj[8].toString())) {
                dto.setMark_b("是");
              }
            }
            if (obj[9] != null) {
              if ("0".equals(obj[9].toString())) {
                dto.setMark_c("否");
              }
              if ("1".equals(obj[9].toString())) {
                dto.setMark_c("是");
              }
            }
            if (obj[10] != null) {
              if ("0".equals(obj[10].toString())) {
                dto.setMark_d("否");
              }
              if ("1".equals(obj[10].toString())) {
                dto.setMark_d("是");
              }
            }
            if (obj[11] != null) {

              dto.setMoney_1(obj[11].toString());
            }
            if (obj[12] != null) {

              dto.setMoney_2(obj[12].toString());
            }
            if (obj[13] != null) {

              dto.setFlowid(obj[13].toString());
            }
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

  public List queryContractchangeCount(final String contractId,
      final String borrowerName, final String cardNum, final String startDate,
      final String endDate, final String orderBy, final String order,
      final int start, final int pageSize, final int page, final String type,
      final SecurityInfo securityInfo) throws Exception {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "";
          if ("1".equals(type)) {
            hql = " select t1.id,t1.contract_id,t2.borrower_name,t2.card_num,t1.deadline,t1.type,t1.bizdate,t1.mark_a,t1.mark_b,t1.mark_c,t1.mark_d,t1.aheadmoney,t1.overplus_loan_money from pl101_1 t1,pl110 t2,pl111 t3 where t1.contract_id=t2.contract_id  and t3.contract_id=t2.contract_id and t3.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          }
          if ("2".equals(type)) {
            hql = " select t1.id,t1.contract_id,t2.borrower_name,t2.card_num,t1.deadline,t1.type,t1.bizdate,t1.mark_a,t1.mark_b,t1.mark_c,t1.mark_d,t1.aheadmoney,t1.overplus_loan_money from pl101_1 t1,pl110 t2,pl111 t3 where t1.contract_id=t2.contract_id and t1.mark_b=0  and t3.contract_id=t2.contract_id and t3.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          }
          if ("3".equals(type)) {
            hql = " select t1.id,t1.contract_id,t2.borrower_name,t2.card_num,t1.deadline,t1.type,t1.bizdate,t1.mark_a,t1.mark_b,t1.mark_c,t1.mark_d,t1.aheadmoney,t1.overplus_loan_money from pl101_1 t1,pl110 t2,pl111 t3 where t1.contract_id=t2.contract_id and t1.mark_c=0 and t1.mark_b=1  and t3.contract_id=t2.contract_id and t3.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          }
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " t1.contract_id=?  and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " t2.borrower_name=?  and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " t2.card_num=?  and ";
            parameters.add(cardNum);
          }
          if (startDate != null && !"".equals(startDate)) {
            criterion += " t1.bizdate>=?  and ";
            parameters.add(startDate);
          }
          if (endDate != null && !"".equals(endDate)) {
            criterion += " t1.bizdate<=?  and ";
            parameters.add(endDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " t1.contract_id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);
          // List queryList = query.list();
          // if (queryList == null || queryList.size() == 0) {
          // query.setFirstResult(pageSize * (page - 2));
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);
          // }

          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public ContractchangeDTO printContractchangeDTO(final String id)
      throws Exception {
    ContractchangeDTO dto = null;

    try {
      dto = (ContractchangeDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select t1.contract_id as 合同编号,"
                  + " t1.bizdate as 提出申请日期,"
                  + "  t2.agreement_date as 合同签订日期,"
                  + " t1.aheadmoney as 提前还款金额,"
                  + " t1.overplus_loan_money as 贷款余额,"
                  + " t1.deadline as 剩余期限月,"
                  + " (t1.deadline/12) as 剩余期限年,"
                  + " t1.corpus_interest as 月还本息,"
                  + " t3.borrower_name as 借款人姓名,"
                  + " t1.type as 类型,"
                  + " t4.loan_time_limit 贷款期限,"
                  + " t1.olddeadline 原剩余期限"
                  + " from pl101_1 t1,pl120 t2,pl110 t3,pl111 t4 where t1.contract_id=t2.contract_id and t1.contract_id=t3.contract_id and t3.contract_id=t4.contract_id and t1.id=?";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, id);
              Iterator it = query.list().iterator();
              Object obj[] = null;
              ContractchangeDTO dto = new ContractchangeDTO();
              if (it.hasNext()) {
                obj = (Object[]) it.next();
                if (obj[0] != null) {
                  dto.setContractId(obj[0].toString());
                }
                if (obj[1] != null) {
                  dto.setDate(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2].toString())) {
                  dto.setAgreementDate(obj[2].toString());
                }
                if (obj[3] != null && !"".equals(obj[3].toString())) {
                  dto.setAheadmoney(new BigDecimal(obj[3].toString()).setScale(
                      2, BigDecimal.ROUND_HALF_UP));
                }
                if (obj[4] != null && !"".equals(obj[4].toString())) {
                  dto.setOverplusLoanMoney(new BigDecimal(obj[4].toString())
                      .setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (obj[5] != null) {
                  dto.setShengyuyue(obj[5].toString());
                }
                if (obj[6] != null) {
                  dto.setShengyunian(new BigDecimal(obj[6].toString())
                      .setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                }
                if (obj[7] != null && !"".equals(obj[7].toString())) {
                  dto.setMonthPay(new BigDecimal(obj[7].toString()).setScale(2,
                      BigDecimal.ROUND_HALF_UP));
                }
                if (obj[8] != null && !"".equals(obj[8].toString())) {
                  dto.setBorrowerName(obj[8].toString());
                }
                if (obj[9] != null && !"".equals(obj[9].toString())) {
                  dto.setType(obj[9].toString());
                }
                if (obj[10] != null && !"".equals(obj[10].toString())) {
                  dto.setLoanlimit(obj[10].toString());
                }
                if (obj[11] != null && !"".equals(obj[11].toString())) {
                  dto.setYuanlimit(obj[11].toString());
                }
              }
              return dto;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }

  public String queryIsBoKuan(final String contactId) {
    String str = "";
    str = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select pl006.fund_status from pl114,pl006 where pl006.floor_id=pl114.floor_id and pl114.contract_id=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, contactId);

        return query.uniqueResult();
      }
    });
    return str;
  }

  public void updatepl101_1_1(final String id) throws Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = " update pl101_1 set pl101_1.mark_b = '1' where pl101_1.id = "
          + id;
      st.executeUpdate(sql);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updatepl101_1_2(final String id) throws Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = " update pl101_1 set pl101_1.mark_c = '1',pl101_1.mark_a = '1' where pl101_1.id = "
          + id;
      st.executeUpdate(sql);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updatepl101_1_3(final String id) throws Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = " update pl101_1 set pl101_1.mark_d = '1' where pl101_1.id = "
          + id;
      st.executeUpdate(sql);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String queryIsPl101(final String contactId) {
    String str = "";
    str = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select count(*) from pl101_1 t where t.contract_id=? and t.mark_a='0' ";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, contactId);

        return query.uniqueResult().toString();
      }
    });
    return str;
  }

  public List queryIssuenoticeTcList(final String contractId,
      final String borrowerName, final String cardNum, final String loanBankId,
      final String contractSt, final String orderBy, final String order,
      final int start, final int pageSize, final List loanbankList,
      final String type, final int page, final String isPrint) throws Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Vector parameters = new Vector();
          String hql = "select b.contract_id contractid,"
              + " a.borrower_name borrowername,"
              + " a.card_num cardnum,"
              + " b.loan_bank_id loanbankid,"
              + " d.loan_money loanmoney,"
              + " d.loan_time_limit loantimelimit,"
              + " d.loan_month_rate loanmonthrate,"
              + " d.loan_mode loanmode,b.contract_st contractst,b.mark_a, "
              + " case when c.house_type = '01' then c.house_addr else c.bargainor_house_addr end,b.mark_b "
              + " from pl110 a, pl111 b, pl114 c, pl115 d"
              + " where a.contract_id = b.contract_id"
              + " and a.contract_id = c.contract_id "
              + " and b.contract_id = d.contract_id  and b.contract_st = 9 "
              + " and a.IS_PRINT_IOU='1' ";
          if (contractSt != null && !contractSt.equals("")) {
            hql = hql + " and b.mark_a=? ";
            parameters.add(contractSt);
          }

          String criterion = "";
          if (contractId != null && !contractId.equals("")) {
            criterion += " a.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " a.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " a.card_num=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " b.loan_bank_id=? and ";
            parameters.add(loanBankId);
          }
          // if (isPrint != null && !isPrint.equals("")) {
          // criterion += " a.IS_PRINT_IOU=? and ";
          // parameters.add(isPrint);
          // }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " b.loan_bank_id = ? or ";
              parameters.add(loanbankList.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " contractid ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by  b.mark_a," + ob + "  " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List temp_list1 = new ArrayList();
          temp_list1 = query.list();
          if (temp_list1 == null || temp_list1.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            temp_list1 = query.list();
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              IssuenoticeTbDTO issuenoticeTbDTO = new IssuenoticeTbDTO();
              if (obj[0] != null) {
                issuenoticeTbDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                issuenoticeTbDTO.setBorrowerName(obj[1].toString());
              }

              if (obj[2] != null) {
                issuenoticeTbDTO.setCardNum(obj[2].toString());
              }
              if (obj[3] != null) {
                issuenoticeTbDTO.setLoanBankId(obj[3].toString());
              }
              if (obj[4] != null) {
                issuenoticeTbDTO
                    .setLoanMoney(new BigDecimal(obj[4].toString()));

              }
              if (obj[5] != null) {
                issuenoticeTbDTO.setLoanTimeLimit(obj[5].toString());

              }
              if (obj[6] != null) {
                issuenoticeTbDTO.setLoanMonthRate(new BigDecimal(obj[6]
                    .toString()));
              }
              if (obj[7] != null) {
                issuenoticeTbDTO.setLoanMode(obj[7].toString());
              }
              if (obj[8] != null) {
                issuenoticeTbDTO.setContractSt(obj[8].toString());
              }
              if (obj[9] != null) {
                if ("0".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("否");
                }
                if ("1".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("是");
                }
              }
              if (obj[10] != null) {
                issuenoticeTbDTO.setHouseAddr(obj[10].toString());
              }
              if (obj[11] != null) {
                if ("0".equals(obj[11].toString())) {
                  issuenoticeTbDTO.setIschecked("否");
                }
                if ("1".equals(obj[11].toString())) {
                  issuenoticeTbDTO.setIschecked("是");
                }
              }
              temp_list.add(issuenoticeTbDTO);

            }

          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryIssuenoticeTcListCount(final String contractId,
      final String borrowerName, final String cardNum, final String loanBankId,
      final String contractSt, final List loanbankList, final String type,
      final String isPrint) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Vector parameters = new Vector();
          String hql = "select b.contract_id contractid,"
              + " a.borrower_name borrowername,"
              + " a.card_num cardnum,"
              + " b.loan_bank_id loanbankid,"
              + " d.loan_money loanmoney,"
              + " d.loan_time_limit loantimelimit,"
              + " d.loan_month_rate loanmonthrate,"
              + " d.loan_mode loanmode,b.contract_st contractst,a.IS_PRINT_IOU,"
              + "(select case when pl114.head_id is not null then (select pl005.developer_name from pl005 where pl005.id = pl114.head_id) else '' end from pl114 where pl114.contract_id = a.contract_id),  "
              + " case when c.house_type = '01' then c.house_addr else c.bargainor_house_addr end"
              + " from pl110 a, pl111 b, pl114 c, pl115 d"
              + " where a.contract_id = b.contract_id"
              + " and a.contract_id = c.contract_id "
              + " and b.contract_id = d.contract_id and b.contract_st = 9 "
              + " and a.IS_PRINT_IOU='1' ";
          if (contractSt != null && !contractSt.equals("")) {
            hql = hql + " and b.mark_a=? ";
            parameters.add(contractSt);
          }
          String criterion = "";
          if (contractId != null && !contractId.equals("")) {
            criterion += " a.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " a.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " a.card_num=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " b.loan_bank_id=? and ";
            parameters.add(loanBankId);
          }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " b.loan_bank_id = ? or ";
              parameters.add(loanbankList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              IssuenoticeTbDTO issuenoticeTbDTO = new IssuenoticeTbDTO();
              if (obj[0] != null) {
                issuenoticeTbDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                issuenoticeTbDTO.setBorrowerName(obj[1].toString());
              }

              if (obj[2] != null) {
                issuenoticeTbDTO.setCardNum(obj[2].toString());
              }
              if (obj[3] != null) {
                issuenoticeTbDTO.setLoanBankId(obj[3].toString());
              }
              if (obj[4] != null) {
                issuenoticeTbDTO
                    .setLoanMoney(new BigDecimal(obj[4].toString()));

              }
              if (obj[5] != null) {
                issuenoticeTbDTO.setLoanTimeLimit(obj[5].toString());

              }
              if (obj[6] != null) {
                issuenoticeTbDTO.setLoanMonthRate(new BigDecimal(obj[6]
                    .toString()));
              }
              if (obj[7] != null) {
                issuenoticeTbDTO.setLoanMode(obj[7].toString());
              }
              if (obj[8] != null) {
                issuenoticeTbDTO.setContractSt(obj[8].toString());
              }
              if (obj[9] != null) {
                if ("0".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("否");
                }
                if ("１".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("是");
                }
              }
              if (obj[10] != null) {
                issuenoticeTbDTO.setDeveloperName(obj[10].toString());
              }
              if (obj[11] != null) {
                issuenoticeTbDTO.setHouseAddr(obj[11].toString());
              }
              temp_list.add(issuenoticeTbDTO);

            }

          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void updateBorrowerAccContractStatus1_wsh_fin(final String contractId,
      final String mark, final String userName) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update BorrowerAcc borrowerAcc "
              + "set borrowerAcc.markA = " + mark
              + " ,borrowerAcc.finCheckMan='" + userName + "' "
              + " where borrowerAcc.contractId = ? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateBorrowerAccContractStatus1_wsh_fin_3(
      final String contractId, final String mark, final String userName)
      throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update BorrowerAcc borrowerAcc "
              + "set borrowerAcc.markB = " + mark + ",borrowerAcc.finPrintMan="
              + userName + " where borrowerAcc.contractId = ? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List queryIssuenoticeTdList(final String contractId,
      final String borrowerName, final String cardNum, final String loanBankId,
      final String contractSt, final String orderBy, final String order,
      final int start, final int pageSize, final List loanbankList,
      final String type, final int page, final String isPrint) throws Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Vector parameters = new Vector();
          String hql = "select b.contract_id contractid,"
              + " a.borrower_name borrowername,"
              + " a.card_num cardnum,"
              + " b.loan_bank_id loanbankid,"
              + " d.loan_money loanmoney,"
              + " d.loan_time_limit loantimelimit,"
              + " d.loan_month_rate loanmonthrate,"
              + " d.loan_mode loanmode,b.contract_st contractst,b.mark_b, "
              + " case when c.house_type = '01' then c.house_addr else c.bargainor_house_addr end"
              + " from pl110 a, pl111 b, pl114 c, pl115 d"
              + " where a.contract_id = b.contract_id"
              + " and a.contract_id = c.contract_id "
              + " and b.contract_id = d.contract_id "
              + " and a.IS_PRINT_IOU='1' " + " and b.mark_a='1' "
              + " and b.contract_st = 9 ";
          if (isPrint != null && !isPrint.equals("")) {
            hql = hql + " and b.mark_b=? ";
            parameters.add(isPrint);
          }
          String criterion = "";
          if (contractId != null && !contractId.equals("")) {
            criterion += " a.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " a.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " a.card_num=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " b.loan_bank_id=? and ";
            parameters.add(loanBankId);
          }
          // if (isPrint != null && !isPrint.equals("")) {
          // criterion += " a.IS_PRINT_IOU=? and ";
          // parameters.add(isPrint);
          // }
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " b.loan_bank_id = ? or ";
              parameters.add(loanbankList.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " contractid ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by b.mark_b," + ob + "  " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List temp_list1 = new ArrayList();
          temp_list1 = query.list();
          if (temp_list1 == null || temp_list1.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            temp_list1 = query.list();
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              IssuenoticeTbDTO issuenoticeTbDTO = new IssuenoticeTbDTO();
              if (obj[0] != null) {
                issuenoticeTbDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                issuenoticeTbDTO.setBorrowerName(obj[1].toString());
              }

              if (obj[2] != null) {
                issuenoticeTbDTO.setCardNum(obj[2].toString());
              }
              if (obj[3] != null) {
                issuenoticeTbDTO.setLoanBankId(obj[3].toString());
              }
              if (obj[4] != null) {
                issuenoticeTbDTO
                    .setLoanMoney(new BigDecimal(obj[4].toString()));

              }
              if (obj[5] != null) {
                issuenoticeTbDTO.setLoanTimeLimit(obj[5].toString());

              }
              if (obj[6] != null) {
                issuenoticeTbDTO.setLoanMonthRate(new BigDecimal(obj[6]
                    .toString()));
              }
              if (obj[7] != null) {
                issuenoticeTbDTO.setLoanMode(obj[7].toString());
              }
              if (obj[8] != null) {
                issuenoticeTbDTO.setContractSt(obj[8].toString());
              }
              if (obj[9] != null) {
                if ("0".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("否");
                }
                if ("1".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("是");
                }
              }
              if (obj[10] != null) {
                issuenoticeTbDTO.setHouseAddr(obj[10].toString());
              }
              temp_list.add(issuenoticeTbDTO);

            }

          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryIssuenoticeTdListCount(final String contractId,
      final String borrowerName, final String cardNum, final String loanBankId,
      final String contractSt, final List loanbankList, final String type,
      final String isPrint) throws Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Vector parameters = new Vector();
          String hql = "select b.contract_id contractid,"
              + " a.borrower_name borrowername,"
              + " a.card_num cardnum,"
              + " b.loan_bank_id loanbankid,"
              + " d.loan_money loanmoney,"
              + " d.loan_time_limit loantimelimit,"
              + " d.loan_month_rate loanmonthrate,"
              + " d.loan_mode loanmode,b.contract_st contractst,a.IS_PRINT_IOU,"
              + "(select case when pl114.head_id is not null then (select pl005.developer_name from pl005 where pl005.id = pl114.head_id) else '' end from pl114 where pl114.contract_id = a.contract_id),  "
              + " case when c.house_type = '01' then c.house_addr else c.bargainor_house_addr end"
              + " from pl110 a, pl111 b, pl114 c, pl115 d"
              + " where a.contract_id = b.contract_id"
              + " and a.contract_id = c.contract_id "
              + " and b.contract_id = d.contract_id "
              + " and a.IS_PRINT_IOU='1' " + " and b.mark_a='1' "
              + " and b.contract_st = 9 ";
          if (isPrint != null && !isPrint.equals("")) {
            hql = hql + " and b.mark_b=? ";
            parameters.add(isPrint);
          }
          String criterion = "";
          if (contractId != null && !contractId.equals("")) {
            criterion += " a.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " a.borrower_name=? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " a.card_num=? and ";
            parameters.add(cardNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " b.loan_bank_id=? and ";
            parameters.add(loanBankId);
          }

          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " b.loan_bank_id = ? or ";
              parameters.add(loanbankList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              IssuenoticeTbDTO issuenoticeTbDTO = new IssuenoticeTbDTO();
              if (obj[0] != null) {
                issuenoticeTbDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                issuenoticeTbDTO.setBorrowerName(obj[1].toString());
              }

              if (obj[2] != null) {
                issuenoticeTbDTO.setCardNum(obj[2].toString());
              }
              if (obj[3] != null) {
                issuenoticeTbDTO.setLoanBankId(obj[3].toString());
              }
              if (obj[4] != null) {
                issuenoticeTbDTO
                    .setLoanMoney(new BigDecimal(obj[4].toString()));

              }
              if (obj[5] != null) {
                issuenoticeTbDTO.setLoanTimeLimit(obj[5].toString());

              }
              if (obj[6] != null) {
                issuenoticeTbDTO.setLoanMonthRate(new BigDecimal(obj[6]
                    .toString()));
              }
              if (obj[7] != null) {
                issuenoticeTbDTO.setLoanMode(obj[7].toString());
              }
              if (obj[8] != null) {
                issuenoticeTbDTO.setContractSt(obj[8].toString());
              }
              if (obj[9] != null) {
                if ("0".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("否");
                }
                if ("１".equals(obj[9].toString())) {
                  issuenoticeTbDTO.setIsPrint("是");
                }
              }
              if (obj[10] != null) {
                issuenoticeTbDTO.setDeveloperName(obj[10].toString());
              }
              if (obj[11] != null) {
                issuenoticeTbDTO.setHouseAddr(obj[11].toString());
              }
              temp_list.add(issuenoticeTbDTO);

            }

          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void updateBorrowerAccContractStatus1_wsh_fin_chen(
      final String contractId, final String userName) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update BorrowerAcc borrowerAcc "
              + "set borrowerAcc.markB = '1' ,borrowerAcc.finPrintMan='"
              + userName + "'" + " where borrowerAcc.contractId = ? ";
          session.createQuery(hql).setString(0, contractId).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
