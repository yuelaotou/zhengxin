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
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.CardMunChange;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.dto.SpecialInfoChgDTO;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;

/**
 * 借款人额度信息PL115
 * 
 * @author 李娟 2007-9-13
 */
public class BorrowerLoanInfoDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public BorrowerLoanInfo queryById(Serializable id) {
    Validate.notNull(id);
    return (BorrowerLoanInfo) getHibernateTemplate().get(
        BorrowerLoanInfo.class, id);
  }

  /**
   * 插入记录
   * 
   * @param borrowerLoanInfo
   * @return
   */
  public Serializable insert(BorrowerLoanInfo borrowerLoanInfo) {
    Serializable id = null;
    try {
      Validate.notNull(borrowerLoanInfo);
      id = getHibernateTemplate().save(borrowerLoanInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 更新对应的月利率 param loanMonthRate，corpusInterest，contractId shiy 2007.09.22
   */
  public void updateBorrowerLoanInfo_sy(final BigDecimal loanMonthRate,
      final BigDecimal corpusInterest, final String contractId)
      throws BusinessException {
    try {
      BorrowerLoanInfo borrowerLoanInfo = (BorrowerLoanInfo) getHibernateTemplate()
          .load(BorrowerLoanInfo.class, contractId);
      borrowerLoanInfo.setCorpusInterest(corpusInterest);
      borrowerLoanInfo.setLoanMonthRate(loanMonthRate);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("更新申请人信息失败！");
    }
  }

  /**
   * yuqf 查询PL115字段，用于借款合同信息页面显示
   * 
   * @param id
   * @return
   */
  public EndorsecontractTaDTO queryBorrowerLoanInfoYU(final String id,
      final SecurityInfo securityInfo) {
    EndorsecontractTaDTO endorsecontractTaDTO = null;
    try {
      endorsecontractTaDTO = (EndorsecontractTaDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              // TODO Auto-generated method stub

              String hql = "select t.loan_month_rate,t.loan_mode,t.loan_money,t.loan_time_limit,t.corpus_interest from pl115 t where t.contract_id='"
                  + id + "'";
              Query query = session.createSQLQuery(hql);
              EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
              if (query.list().size() != 0) {
                Iterator iterate = query.list().iterator();
                Object[] obj = null;
                while (iterate.hasNext()) {
                  obj = (Object[]) iterate.next();
                  if (obj[0] != null) {
                    endorsecontractTaDTO.setMonthInterest(obj[0].toString());// 每月利率
                  }
                  if (obj[1] != null) {
                    endorsecontractTaDTO.setCreditType(obj[1].toString());// 还款方式
                  }
                  if (obj[2] != null) {
                    endorsecontractTaDTO.setDebitMoney(obj[2].toString());// 贷款金额
                  }
                  if (obj[3] != null) {
                    endorsecontractTaDTO.setTerm(obj[3].toString());// 贷款期限
                  }
                  if (obj[4] != null) {
                    if ("2".equals(obj[1].toString())
                        || "4".equals(obj[1].toString())
                        || "5".equals(obj[1].toString())) {
                      endorsecontractTaDTO.setCorpusInterest(obj[4].toString());// 月换本息
                    }
                  }
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

  /**
   * hanL
   */

  public boolean isFindContractidByContractid(final String contractid) {
    boolean b = true;
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.contract_id from pl115 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);

            return query.uniqueResult();
          }
        });

    if (id == null || id.equals("")) {
      b = false;
    }
    return b;
  }

  /**
   * shiy param 合同状态，合同编号 return list
   */
  public List queryBorrowerLoanInfo_sy(final String contractst,
      final String contractId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select borrower.borrowerName,borrower.cardKind,borrower.cardNum,borrowerAcc.loanBankId, borrowerLoanInfo.loanMoney, borrowerLoanInfo.loanTimeLimit,borrowerLoanInfo.loanMonthRate,borrowerLoanInfo.loanMode,borrowerLoanInfo.corpusInterest,borrowerLoanInfo.loanRateType,"
              + " borrower.empId,borrower.orgId,borrowerAcc.loanKouAcc  "
              + " from BorrowerLoanInfo borrowerLoanInfo,Borrower borrower,BorrowerAcc borrowerAcc "
              + "where borrowerLoanInfo.contractId=borrower.contractId and borrower.contractId=borrowerAcc.contractId and borrowerAcc.contractSt=? and borrowerAcc.contractId=? and borrowerAcc.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Query query = session.createQuery(hql);
          query.setString(0, contractst);
          query.setString(1, contractId);
          Iterator it = query.iterate();
          List t = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
            loanaccordDTO.setBorrowerName(obj[0] + "");
            loanaccordDTO.setCardKind(obj[1] + "");
            loanaccordDTO.setCardNum(obj[2] + "");
            loanaccordDTO.setLoanBankId(obj[3] + "");
            loanaccordDTO.setLoanMoney(new BigDecimal(obj[4] + ""));
            loanaccordDTO.setLoanTimeLimit(obj[5] + "");
            loanaccordDTO.setLoanMonthRate(new BigDecimal(obj[6] + ""));
            loanaccordDTO.setLoanMode(obj[7] + "");
            if (obj[7].toString().equals("1")) {
              loanaccordDTO.setCorpusInterest(new BigDecimal(0.00));
            } else {
              if (obj[8] != null)
                loanaccordDTO.setCorpusInterest(new BigDecimal(obj[8] + ""));
            }
            loanaccordDTO.setLoanRateType(obj[9] + "");
            if (obj[10] != null) {
              loanaccordDTO.setEmpId(new BigDecimal(obj[10] + ""));
            }
            if (obj[11] != null) {
              loanaccordDTO.setOrgId(new BigDecimal(obj[11] + ""));
            }
            if (obj[12] != null)
              loanaccordDTO.setLoanKouAcc(obj[12].toString());
            t.add(loanaccordDTO);
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
   * shiy param 合同状态，合同编号 return list
   */
  public List queryBorrowerLoanTbInfo_sy(final String contractst,
      final String contractId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select borrower.borrowerName,borrower.cardKind,borrower.cardNum,borrowerAcc.loanMoney,borrowerAcc.loanTimeLimit, "
              + "borrowerAcc.loanMode,borrowerAcc.overplusLoanMoney,borrowerAcc.overplusLimite,"
              + "borrowerAcc.loanBankId,borrowerAcc.loanKouAcc,borrowerAcc.loanRepayDay,borrowerAcc.contractSt,borrowerAcc.loanStartDate"
              + " from Borrower borrower,BorrowerAcc borrowerAcc "
              + "where  borrower.contractId=borrowerAcc.contractId and borrowerAcc.contractSt in ("
              + contractst
              + ") and borrowerAcc.contractId=? "
              + "and borrowerAcc.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Query query = session.createQuery(hql);
          // query.setParameter(0, contractst);
          query.setParameter(0, contractId);
          Iterator it = query.iterate();
          List t = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            PrintplanDTO printplanDTO = new PrintplanDTO();
            printplanDTO.setBorrowerName(obj[0] + "");
            printplanDTO.setCardKind(obj[1] + "");
            printplanDTO.setCardNum(obj[2] + "");
            printplanDTO.setLoanMoney(new BigDecimal(obj[3] + ""));
            printplanDTO.setLoanTimeLimit(obj[4] + "");
            printplanDTO.setLoanMode(obj[5] + "");
            printplanDTO.setOverplusLoanMoney(new BigDecimal(obj[6] + ""));
            printplanDTO.setOverplusLimite(obj[7] + "");
            printplanDTO.setLoanBankId(obj[8] + "");
            printplanDTO.setLoanKouAcc(obj[9] + "");
            printplanDTO.setLoanRepayDay(obj[10].toString());
            printplanDTO.setContractSt(obj[11].toString());
            printplanDTO.setLoanStartDate(obj[12].toString());
            t.add(printplanDTO);
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
   * shiy param bizSt，loanBankId,contractId,bizType,borrowerName,cardNum 合同编号
   * return list
   */
  public List queryBorrowerLoanList_sy(final String bizSt,
      final String loanBankId, final String contractId, final String bizType,
      final String borrowerName, final String cardNum, final String orderBy,
      final String order, final int start, final int pageSize,
      final SecurityInfo securityInfo, final int page) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select borrower.borrowerName,borrower.cardKind,borrower.cardNum,borrowerAcc.loanBankId,"
              + "borrowerLoanInfo.loanMoney, borrowerLoanInfo.loanTimeLimit,borrowerLoanInfo.loanMonthRate,borrowerLoanInfo.loanMode,"
              + "borrowerLoanInfo.corpusInterest,borrowerLoanInfo.loanRateType,borrower.contractId,borrowerAcc.loanStartDate,loanFlowHead.bizSt,borrowerAcc.loanKouAcc,loanFlowHead.flowHeadId"
              + " from BorrowerLoanInfo borrowerLoanInfo,Borrower borrower,BorrowerAcc borrowerAcc,LoanFlowTail loanFlowTail,LoanFlowHead loanFlowHead ";
          Vector parameters = new Vector();
          String criterion = "";

          if (bizSt != null && !bizSt.equals("")) {
            if (bizSt.equals("456")) {
              criterion += "loanFlowHead.bizSt in ('4','5','6')  and ";
            } else {
              criterion += "loanFlowHead.bizSt= ?  and ";
              parameters.add(bizSt);
            }
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId= ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += "loanFlowTail.contractId= ?  and ";
            parameters.add(contractId);
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType= ?  and ";
            parameters.add(bizType);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += "borrower.borrowerName= ?  and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += "borrower.cardNum= ?  and ";
            parameters.add(cardNum);
          }
          if (criterion.length() != 0)
            criterion = " where borrowerLoanInfo.contractId=borrower.contractId and borrower.contractId=borrowerAcc.contractId and borrowerAcc.contractId=loanFlowTail.contractId and loanFlowHead.flowHeadId= loanFlowTail.flowHeadId"
                + " and borrowerAcc.loanBankId "
                + securityInfo.getDkSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = "borrower.contractId";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + order;
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
            LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
            loanaccordDTO.setBorrowerName(obj[0] + "");
            loanaccordDTO.setCardKind(obj[1] + "");
            loanaccordDTO.setCardNum(obj[2] + "");
            loanaccordDTO.setLoanBankId(obj[3] + "");
            loanaccordDTO.setLoanMoney(new BigDecimal(obj[4] + ""));
            loanaccordDTO.setLoanTimeLimit(obj[5] + "");
            loanaccordDTO.setLoanMonthRate(new BigDecimal(obj[6] + ""));
            loanaccordDTO.setLoanMode(obj[7] + "");
            if (obj[7].toString().equals("1")) {
              loanaccordDTO.setCorpusInterest(new BigDecimal(0.00));
            } else {
              if (obj[8] != null && !obj[8].toString().equals(""))
                loanaccordDTO.setCorpusInterest(new BigDecimal(obj[8] + ""));
            }
            loanaccordDTO.setLoanRateType(obj[9] + "");
            loanaccordDTO.setContractId(obj[10] + "");
            loanaccordDTO.setLoanStartDate(obj[11] + "");
            loanaccordDTO.setBizSt(obj[12] + "");
            loanaccordDTO.setLoanKouAcc(obj[13] + "");
            loanaccordDTO.setFlowHeadId(new Integer((obj[14] + "")));
            t.add(loanaccordDTO);
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
   * 统计显示的个数 shiy param bizSt，loanBankId,contractId,bizType,borrowerName,cardNum
   * 合同编号 return list
   */
  public List countBorrowerLoanList_sy(final String bizSt,
      final String loanBankId, final String contractId, final String bizType,
      final String borrowerName, final String cardNum,
      final SecurityInfo securityInfo,final String loanFlowHeadId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select borrower.borrowerName,borrower.cardKind,borrower.cardNum,borrowerAcc.loanBankId,"
              + "borrowerLoanInfo.loanMoney, borrowerLoanInfo.loanTimeLimit,borrowerLoanInfo.loanMonthRate,borrowerLoanInfo.loanMode,"
              + "borrowerLoanInfo.corpusInterest,borrowerLoanInfo.loanRateType,borrower.contractId,borrowerAcc.loanStartDate,"
              + "loanFlowHead.bizSt,borrowerAcc.loanKouAcc,borrowerLoanInfo.firstLoanMoney,loanFlowHead.noteNum "
              + " from BorrowerLoanInfo borrowerLoanInfo,Borrower borrower,BorrowerAcc borrowerAcc,"
              + "LoanFlowTail loanFlowTail,LoanFlowHead loanFlowHead ";
          Vector parameters = new Vector();
          String criterion = "";

          if (bizSt != null && !bizSt.equals("")) {
            if (bizSt.equals("456")) {
              criterion += "loanFlowHead.bizSt in ('4','5','6')  and ";
            } else {
              criterion += "loanFlowHead.bizSt= ?  and ";
              parameters.add(bizSt);
            }
          }
          if (loanFlowHeadId != null && !loanFlowHeadId.equals("")) {
            criterion += "loanFlowHead.flowHeadId= ?  and ";
            parameters.add(new Integer(loanFlowHeadId));
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId= ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += "loanFlowTail.contractId= ?  and ";
            parameters.add(contractId);
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType= ?  and ";
            parameters.add(bizType);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += "borrower.borrowerName= ?  and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += "borrower.cardNum= ?  and ";
            parameters.add(cardNum);
          }
          if (criterion.length() != 0)
            criterion = " where borrowerLoanInfo.contractId=borrower.contractId and borrower.contractId=borrowerAcc.contractId and borrowerAcc.contractId=loanFlowTail.contractId and loanFlowHead.flowHeadId= loanFlowTail.flowHeadId"
                + " and borrowerAcc.loanBankId "
                + securityInfo.getDkSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.iterate();
          List t = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
            loanaccordDTO.setBorrowerName(obj[0] + "");
            loanaccordDTO.setCardKind(obj[1] + "");
            loanaccordDTO.setCardNum(obj[2] + "");
            loanaccordDTO.setLoanBankId(obj[3] + "");
            loanaccordDTO.setLoanMoney(new BigDecimal(obj[4] + ""));
            loanaccordDTO.setLoanTimeLimit(obj[5] + "");
            loanaccordDTO.setLoanMonthRate(new BigDecimal(obj[6] + ""));
            loanaccordDTO.setLoanMode(obj[7] + "");
            if (obj[7].toString().equals("1")) {
              loanaccordDTO.setCorpusInterest(new BigDecimal(0.00));
            } else {
              if (obj[8] != null && !obj[8].toString().equals(""))
                loanaccordDTO.setCorpusInterest(new BigDecimal(obj[8] + ""));
            }
            loanaccordDTO.setLoanRateType(obj[9] + "");
            loanaccordDTO.setContractId(obj[10] + "");
            loanaccordDTO.setLoanStartDate(obj[11] + "");
            loanaccordDTO.setBizSt(obj[12] + "");
            loanaccordDTO.setLoanKouAcc(obj[13] + "");
            if (obj[14] != null)
              loanaccordDTO.setFirstLoanMoney(new BigDecimal(obj[14] + ""));
            if (obj[15] != null)
              loanaccordDTO.setNoteNum(obj[15] + "");
            t.add(loanaccordDTO);
          }
          return t;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryBorrowerLoanList(final String bizSt,
      final String loanBankId, final String contractId, final String bizType,
      final String borrowerName, final String cardNum, final String orderBy,
      final String order, final int start, final int pageSize,
      final SecurityInfo securityInfo, final int page,
      final String loanStartDate, final String loanEndDate) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select borrower.borrowerName,borrower.cardKind,borrower.cardNum,borrowerAcc.loanBankId,"
              + "borrowerLoanInfo.loanMoney, borrowerLoanInfo.loanTimeLimit,borrowerLoanInfo.loanMonthRate,borrowerLoanInfo.loanMode,"
              + "borrowerLoanInfo.corpusInterest,borrowerLoanInfo.loanRateType,borrower.contractId,borrowerAcc.loanStartDate,loanFlowHead.bizSt,borrowerAcc.loanKouAcc,loanFlowHead.flowHeadId"
              + " from BorrowerLoanInfo borrowerLoanInfo,Borrower borrower,BorrowerAcc borrowerAcc,LoanFlowTail loanFlowTail,LoanFlowHead loanFlowHead ";
          Vector parameters = new Vector();
          String criterion = "";

          if (bizSt != null && !bizSt.equals("")) {
            if (bizSt.equals("456")) {
              criterion += "loanFlowHead.bizSt in ('4','5','6')  and ";
            } else {
              criterion += "loanFlowHead.bizSt= ?  and ";
              parameters.add(bizSt);
            }
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId= ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += "loanFlowTail.contractId like ?  and ";
            parameters.add("%"+contractId+"%");
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType= ?  and ";
            parameters.add(bizType);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += "borrower.borrowerName like ?  and ";
            parameters.add("%"+borrowerName+"%");
          }
          if (cardNum != null && !cardNum.equals("")) {
            String tempCardNum = "";
            if (cardNum.length() == 15){
              tempCardNum = CardMunChange.get18Id(cardNum);
              criterion += "(borrower.cardNum = ? or borrower.cardNum = ?) and ";
              parameters.add(cardNum);
              parameters.add(tempCardNum);
            }
            else if (cardNum.length() == 18){
              tempCardNum = CardMunChange.get15Id(cardNum);
              criterion += "(borrower.cardNum = ? or borrower.cardNum = ?) and ";
              parameters.add(cardNum);
              parameters.add(tempCardNum);
            }else{
              criterion += "borrower.cardNum like ?  and ";
              parameters.add("%"+cardNum+"%");
            }
          }
          
          if (loanStartDate != null && !loanStartDate.equals("")) {
            criterion += "to_number(borrowerAcc.loanStartDate)>= ?  and ";
            parameters.add(loanStartDate);
          }
          if (loanEndDate != null && !loanEndDate.equals("")) {
            criterion += "to_number(borrowerAcc.loanStartDate)<= ?  and ";
            parameters.add(loanEndDate);
          }
          if (criterion.length() != 0)
            criterion = " where borrowerLoanInfo.contractId=borrower.contractId and borrower.contractId=borrowerAcc.contractId and borrowerAcc.contractId=loanFlowTail.contractId and loanFlowHead.flowHeadId= loanFlowTail.flowHeadId"
                + " and borrowerAcc.loanBankId "
                + securityInfo.getDkSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = "borrower.contractId";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by loanFlowHead.docNum asc," + ob + " " + order;
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
            LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
            loanaccordDTO.setBorrowerName(obj[0] + "");
            loanaccordDTO.setCardKind(obj[1] + "");
            loanaccordDTO.setCardNum(obj[2] + "");
            loanaccordDTO.setLoanBankId(obj[3] + "");
            loanaccordDTO.setLoanMoney(new BigDecimal(obj[4] + ""));
            loanaccordDTO.setLoanTimeLimit(obj[5] + "");
            loanaccordDTO.setLoanMonthRate(new BigDecimal(obj[6] + ""));
            loanaccordDTO.setLoanMode(obj[7] + "");
            if (obj[7].toString().equals("1")) {
              loanaccordDTO.setCorpusInterest(new BigDecimal(0.00));
            } else {
              if (obj[8] != null && !obj[8].toString().equals(""))
                loanaccordDTO.setCorpusInterest(new BigDecimal(obj[8] + ""));
            }
            loanaccordDTO.setLoanRateType(obj[9] + "");
            loanaccordDTO.setContractId(obj[10] + "");
            loanaccordDTO.setLoanStartDate(obj[11] + "");
            loanaccordDTO.setBizSt(obj[12] + "");
            loanaccordDTO.setLoanKouAcc(obj[13] + "");
            loanaccordDTO.setFlowHeadId(new Integer((obj[14] + "")));
            t.add(loanaccordDTO);
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
   * 统计显示的个数 shiy param bizSt，loanBankId,contractId,bizType,borrowerName,cardNum
   * 合同编号 return list
   */
  public List countBorrowerLoanList(final String bizSt,
      final String loanBankId, final String contractId, final String bizType,
      final String borrowerName, final String cardNum,
      final SecurityInfo securityInfo, final String loanStartDate,
      final String loanEndDate) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select borrower.borrowerName,borrower.cardKind,borrower.cardNum,borrowerAcc.loanBankId,"
              + "borrowerLoanInfo.loanMoney, borrowerLoanInfo.loanTimeLimit,borrowerLoanInfo.loanMonthRate,borrowerLoanInfo.loanMode,"
              + "borrowerLoanInfo.corpusInterest,borrowerLoanInfo.loanRateType,borrower.contractId,borrowerAcc.loanStartDate,loanFlowHead.bizSt," +
                  "borrowerAcc.loanKouAcc,borrowerLoanInfo.firstLoanMoney,loanFlowHead.noteNum,loanFlowHead.docNum "
              + " from BorrowerLoanInfo borrowerLoanInfo,Borrower borrower,BorrowerAcc borrowerAcc,LoanFlowTail loanFlowTail,LoanFlowHead loanFlowHead ";
          Vector parameters = new Vector();
          String criterion = "";

          if (bizSt != null && !bizSt.equals("")) {
            if (bizSt.equals("456")) {
              criterion += "loanFlowHead.bizSt in ('4','5','6')  and ";
            } else {
              criterion += "loanFlowHead.bizSt= ?  and ";
              parameters.add(bizSt);
            }
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId= ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += "loanFlowTail.contractId like ?  and ";
            parameters.add("%"+contractId+"%");
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType= ?  and ";
            parameters.add(bizType);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += "borrower.borrowerName like ?  and ";
            parameters.add("%"+borrowerName+"%");
          }
          if (cardNum != null && !cardNum.equals("")) {
            String tempCardNum = "";
            if (cardNum.length() == 15){
              tempCardNum = CardMunChange.get18Id(cardNum);
              criterion += "(borrower.cardNum = ? or borrower.cardNum = ?) and ";
              parameters.add(cardNum);
              parameters.add(tempCardNum);
            }
            else if (cardNum.length() == 18){
              tempCardNum = CardMunChange.get15Id(cardNum);
              criterion += "(borrower.cardNum = ? or borrower.cardNum = ?) and ";
              parameters.add(cardNum);
              parameters.add(tempCardNum);
            }else{
              criterion += "borrower.cardNum like ?  and ";
              parameters.add("%"+cardNum+"%");
            }
          }
          if (loanStartDate != null && !loanStartDate.equals("")) {
            criterion += "to_number(borrowerAcc.loanStartDate)>= ?  and ";
            parameters.add(loanStartDate);
          }
          if (loanEndDate != null && !loanEndDate.equals("")) {
            criterion += "to_number(borrowerAcc.loanStartDate)<= ?  and ";
            parameters.add(loanEndDate);
          }
          if (criterion.length() != 0)
            criterion = " where borrowerLoanInfo.contractId=borrower.contractId and borrower.contractId=borrowerAcc.contractId and borrowerAcc.contractId=loanFlowTail.contractId and loanFlowHead.flowHeadId= loanFlowTail.flowHeadId"
                + " and borrowerAcc.loanBankId "
                + securityInfo.getDkSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion+" order by loanFlowHead.docNum asc";
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.iterate();
          List t = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
            loanaccordDTO.setBorrowerName(obj[0] + "");
            loanaccordDTO.setCardKind(obj[1] + "");
            loanaccordDTO.setCardNum(obj[2] + "");
            loanaccordDTO.setLoanBankId(obj[3] + "");
            loanaccordDTO.setLoanMoney(new BigDecimal(obj[4] + ""));
            loanaccordDTO.setLoanTimeLimit(obj[5] + "");
            loanaccordDTO.setLoanMonthRate(new BigDecimal(obj[6] + ""));
            loanaccordDTO.setLoanMode(obj[7] + "");
            if (obj[7].toString().equals("1")) {
              loanaccordDTO.setCorpusInterest(new BigDecimal(0.00));
            } else {
              if (obj[8] != null && !obj[8].toString().equals(""))
                loanaccordDTO.setCorpusInterest(new BigDecimal(obj[8] + ""));
            }
            loanaccordDTO.setLoanRateType(obj[9] + "");
            loanaccordDTO.setContractId(obj[10] + "");
            loanaccordDTO.setLoanStartDate(obj[11] + "");
            loanaccordDTO.setBizSt(obj[12] + "");
            loanaccordDTO.setLoanKouAcc(obj[13] + "");
            if (obj[14] != null)
              loanaccordDTO.setFirstLoanMoney(new BigDecimal(obj[14] + ""));
            loanaccordDTO.setNoteNum(obj[15] + "");
            loanaccordDTO.setDocNum(obj[16] + "");
            t.add(loanaccordDTO);
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
   * hanl 删除pl115
   * 
   * @param id
   */
  public void deleteBorrowerLoanInfo(final String id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "delete from BorrowerLoanInfo borrowerloaninfo where borrowerloaninfo.contractId=?";
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
   * 特殊信息变更
   * 
   * @author 郭婧平 2007-10-05 根据合同编号查询页面所需数据(上半部分) 查询条件：contractId
   */
  public SpecialInfoChgDTO querySpecialInfoChgInfo(final String contractId,
      final List loanbankList) throws Exception {
    SpecialInfoChgDTO specialInfoChgDTO = null;
    try {
      specialInfoChgDTO = (SpecialInfoChgDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select pl115.contract_id," + "pl110.borrower_name,"
                  + "pl115.loan_money," + "pl115.loan_time_limit,"
                  + "pl115.loan_month_rate," + "pl115.loan_mode,"
                  + "pl115.corpus_interest," + "pl115.first_loan_money,"
                  + "pl115.loan_poundage," + "pl111.loan_bank_id "
                  + "from PL115 pl115,PL110 pl110,PL111 pl111 "
                  + "where pl110.contract_id=pl115.contract_id "
                  + "and pl111.contract_id=pl115.contract_id "
                  + "and pl115.contract_id=?";
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
              query.setString(0, contractId);
              for (int i = 0, j = 1; i < parameters.size(); i++, j++) {
                query.setParameter(j, parameters.get(i));
              }
              obj = (Object[]) query.uniqueResult();
              SpecialInfoChgDTO dto = null;
              if (obj != null) {
                dto = new SpecialInfoChgDTO();
                dto.setContractId(obj[0].toString());
                dto.setBorrowerName(obj[1].toString());
                dto.setLoanMoney(new BigDecimal(obj[2].toString()));
                dto.setLoanTimeLimit(obj[3].toString());
                dto.setLoanMonthRate(new BigDecimal(obj[4].toString()));
                dto.setLoanMode(obj[5].toString());
                if (obj[6] != null) {
                  dto.setCorpusInterest(new BigDecimal(obj[6].toString()));
                }
                if (obj[7] != null) {
                  dto.setFirstLoanMoney(new BigDecimal(obj[7].toString()));
                }
                if (obj[8] != null) {
                  dto.setLoanPoundage(new BigDecimal(obj[8].toString()));
                }
                if (obj[9] != null) {
                  dto.setLoanBankId(obj[9].toString());
                }
              }
              return dto;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return specialInfoChgDTO;
  }

  /**
   * shiy 求利率
   * 
   * @param bankId
   * @param loantype
   * @return contractId,loanRateType
   */

  public List queryContractId_sy(final Integer bankId, final String loanRateType) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select borrowerAcc.contractId,borrowerLoanInfo.loanMode,borrowerLoanInfo.loanMoney,borrowerLoanInfo.loanTimeLimit from BorrowerAcc borrowerAcc,BorrowerLoanInfo borrowerLoanInfo where  borrowerAcc.contractId=borrowerLoanInfo.contractId and borrowerAcc.loanBankId=? and borrowerLoanInfo.loanRateType=? and borrowerAcc.contractSt in (1,2,3,4,5,6,9,10)";
          Query query = session.createQuery(hql);
          query.setParameter(0, new BigDecimal(bankId.toString()));
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
   * 郭婧平 通过合同编号查询pl115表 2008.01.31
   * 
   * @param contractId
   */

  public Object[] querybyContractId(final String contractId) {
    Object[] obj = new Object[3];
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pl115.corpus_interest,pl115.loan_rate_type, pl115.loan_month_rate from PL115 pl115 where pl115.contract_id = ? ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, contractId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * 郭婧平 2008.01.31 通过银行id查询pl001表信息
   */

  public Object[] querybyLoanBankId(final String loanRateType,
      final String loanBankId) {
    Object[] obj = new Object[2];
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select qpl001.status, qpl001.loan_month_rate "
              + "from pl001 qpl001 where qpl001.loan_rate_id = (select max(qpl001.loan_rate_id) "
              + "from pl001 qpl001, pl002 qpl002 "
              + "where qpl001.loan_rate_type = ? "
              + "and qpl001.office = qpl002.office "
              + "and qpl002.loan_bank_id = ?)";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, loanRateType);
          query.setParameter(1, loanBankId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  public BigDecimal queryInterest_jj(final String contractId) {
    BigDecimal obj = null;
    try {
      obj = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select nvl(sum(t.real_interest)+sum(t.real_punish_interest),0) from pl203 t,pl202 p where t.contract_id = ? and "
                  + " t.flow_head_id = p.flow_head_id and p.biz_st = 6 and p.biz_type in (2,3,4,5,16) ";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, contractId);
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (obj == null) {
      obj = new BigDecimal(0.00);
    }
    return obj;
  }
}
