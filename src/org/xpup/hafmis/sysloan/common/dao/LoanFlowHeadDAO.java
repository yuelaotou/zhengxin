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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountPopFindDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountPopListDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaSaveDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTbFindDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTbListDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.AdjustAccountDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.BailDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.BizCheckShowListDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.BizCheckTotalDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.LoanaccordDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.OverPayDTO;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto.ClearAccountBalanceInfoDTO;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto.ClearaccountDTO;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto.ClearaccountTotalDTO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTbDTO;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTaDTO;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTbDTO;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTbTotleDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTbDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.dto.InterestAccInfDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.dto.ParticularglDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.dto.PrincipalAccInfDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.dto.LoanBackBankDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryClearDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTaDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTbDTO;

public class LoanFlowHeadDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public LoanFlowHead queryById(Serializable id) {
    Validate.notNull(id);
    return (LoanFlowHead) getHibernateTemplate().get(LoanFlowHead.class, id);
  }

  /**
   * 插入记录
   * 
   * @param LoanFlowHead
   * @return
   */
  public Serializable insert(LoanFlowHead loanFlowHead) {
    Serializable id = null;
    try {
      Validate.notNull(loanFlowHead);
      id = getHibernateTemplate().save(loanFlowHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 删除单个记录
   * 
   * @param loanFlowHead
   */
  public void delete(LoanFlowHead loanFlowHead) {
    Validate.notNull(loanFlowHead);
    getHibernateTemplate().delete(loanFlowHead);
  }

  /**
   * 根据合同编号查询流水表是否存在未记账的记录 回收办理 jj 呆账核销调用
   * 
   * @param contractId
   * @return
   */
  public List queryBizStListByLoanKouAcc_LJ(final String contractId,
      final String bizTypeim) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanFlowTail.flowTailId from LoanFlowTail loanFlowTail, LoanFlowHead loanFlowHead";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !contractId.equals("")) {
            criterion += "loanFlowTail.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (bizTypeim != null && !bizTypeim.equals("")) {
            criterion += "loanFlowHead.bizType = ?  and ";
            parameters.add(bizTypeim);
          }
          if (criterion.length() != 0)
            criterion = " where loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and loanFlowHead.bizSt<6 and "
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
   * 查询回收维护列表 jj
   * 
   * @param loanKouAcc
   * @param contractId
   * @param name
   * @param cardNum
   * @param docNum
   * @param type
   * @param loanBankId
   * @param status
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param securityInfo
   * @param page
   * @return
   */

  public List queryCallbackList_LJ(final String loanKouAcc,
      final String contractId, final String name, final String cardNum,
      final String docNum, final String type, final String loanBankId,
      final String status, final String orderBy, final String order,
      final int start, final int pageSize, final SecurityInfo securityInfo,
      final int page, final String dateStart, final String dateEnd,
      final String yesOrNo) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select loankouacc, contractId,borrowername,docnum,cardNum,realcorpus,realinterest,occurmoney,"
              + "bizType,bizSt,loanBankId,headid,realPunishInterest,bizDate,batchNum,mark "
              + " from ("
              + "select pl202.flow_head_id as headid,"
              + " '' as loankouacc,"
              + " pl202.doc_num as docnum,"
              + " '' as contractId,"
              + " '' as borrowername,"
              + " '' as cardNum,"
              + " nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + " nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as realinterest,"
              + " nvl(pl202.occur_money,0) as occurmoney,"
              + " pl202.biz_type as bizType,"
              + " pl202.biz_st as bizSt,"
              + " pl202.loan_bank_id as loanBankId,"
              + " pl202.real_punish_interest as realPunishInterest,"
              + " pl202.biz_date as bizDate,"
              + " pl202.batch_num as batchNum,p101_1.mark_a as mark "
              + " from PL202 pl202,pl101_1 p101_1 "
              + " where pl202.biz_type = '5'  and p101_1.plflowid(+)=pl202.flow_head_id "
              + " union "
              + " select distinct pl202.flow_head_id as headid,"
              + " pl203.loan_kou_acc as loankouacc,"
              + " pl202.doc_num  as docnum,"
              + " pl203.contract_id as contractId,"
              + " pl110.borrower_name,"
              + " pl110.card_num as cardNum,"
              + " nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + " nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as realinterest,"
              + " nvl(pl202.occur_money,0) as occurmoney,"
              + " pl202.biz_type as bizType,"
              + " pl202.biz_st as bizSt,"
              + " pl202.loan_bank_id as loanBankId,"
              + " pl202.real_punish_interest as realPunishInterest,"
              + " pl202.biz_date as bizDate,"
              + " pl202.batch_num as batchNum,p101_1.mark_a as mark "
              + " from PL203 pl203, PL202 pl202,PL110 pl110,pl101_1 p101_1"
              + " where pl202.flow_head_id = pl203.flow_head_id and pl202.biz_type in ('2', '3', '4') and pl110.contract_id=pl203.contract_id and pl202.flow_head_id=p101_1.plflowid(+) "
              + " ) where loanbankid " + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += "loankouacc = ?  and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += "contractId = ?  and ";
            parameters.add(contractId);
          }
          if (name != null && !name.equals("")) {
            criterion += "borrowername like ? escape '/'  and ";
            parameters.add("%" + name + "%");
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += "cardNum = ?  and ";
            parameters.add(cardNum);
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += "docnum = ?  and ";
            parameters.add(docNum);
          }
          if (type != null && !type.equals("")) {
            criterion += "bizType = ?  and ";
            parameters.add(type);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (yesOrNo != null && !"".equals(yesOrNo)) {
            if ("0".equals(yesOrNo)) {
              criterion += " batchNum is not null and ";
            } else if ("1".equals(yesOrNo)) {
              criterion += " batchNum is  null and ";
            }
          }
          if (status != null && !status.equals("")) {
            if (status.equals("0")) {
              criterion += "(bizSt >1 and bizSt < 5) and ";
            } else {
              criterion += "bizSt = ?  and ";
              parameters.add(status);
            }
          } else {
            criterion += "(bizSt >1 and bizSt < 7) and ";
          }
          if (dateStart != null && !dateStart.equals("") && dateEnd != null
              && !dateEnd.equals("")) {
            criterion += "(bizDate between ? and ? ) and ";
            parameters.add(dateStart);
            parameters.add(dateEnd);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " headid ";

          String od = order;
          if (od == null)
            od = "DESC";
          sql = sql + criterion + " order by " + ob + " " + od;

          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator it = query.list().iterator();
          List queryList = query.list();
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            it = queryList.iterator();
          }
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            LoancallbackTbDTO dto = new LoancallbackTbDTO();
            if (obj[0] != null) {
              dto.setLoanKouAcc(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setContractId(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setBorrowerName(obj[2].toString());
            }
            if (obj[3] != null) {
              dto.setDocNum(obj[3].toString());
            }
            if (obj[4] != null) {
              dto.setCardNum(obj[4].toString());
            }
            dto.setRealCorpus(new BigDecimal(obj[5].toString()));
            dto.setRealInterest(new BigDecimal(obj[6].toString()));
            dto.setOccurMoney(new BigDecimal(obj[7].toString()));
            dto.setBizType(obj[8].toString());
            dto.setBizSt(obj[9].toString());
            dto.setId(obj[11].toString());
            dto.setRealPunishInterest(new BigDecimal(obj[12].toString()));
            dto.setBizDate(obj[13].toString());
            if (obj[14] != null) {
              dto.setYesOrNo(obj[14].toString());
            }
            if (obj[15] != null && obj[15].toString().equals("0")) {
              dto.setMark("是");
            } else {
              dto.setMark("否");
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

  /**
   * 回收维护列表记录条数 jj
   * 
   * @param loanKouAcc
   * @param contractId
   * @param name
   * @param cardNum
   * @param docNum
   * @param type
   * @param loanBankId
   * @param status
   * @param securityInfo
   * @return
   */
  public int queryCallbackListCounts_LJ(final String loanKouAcc,
      final String contractId, final String name, final String cardNum,
      final String docNum, final String type, final String loanBankId,
      final String status, final SecurityInfo securityInfo,
      final String dateStart, final String dateEnd, final String yesOrNo) {
    int count = 0;
    try {

      Integer counts = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(distinct loanFlowHead.flowHeadId) "
                  + " from Borrower borrower,BorrowerAcc borrowerAcc, LoanFlowTail loanFlowTail,LoanFlowHead loanFlowHead where loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and loanFlowHead.bizType>1 and loanFlowHead.bizType <6 and "
                  + " borrower.contractId = loanFlowTail.contractId and borrower.contractId = borrowerAcc.contractId "
                  + " and borrowerAcc.loanBankId "
                  + securityInfo.getDkSecurityHqlSQL();
              Vector parameters = new Vector();
              String criterion = "";

              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (name != null && !name.equals("")) {
                criterion += "borrower.borrowerName like ?  and ";
                parameters.add("'%'" + name + "'%'");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = ?  and ";
                parameters.add(cardNum);
              }
              if (docNum != null && !docNum.equals("")) {
                criterion += "loanFlowHead.docNum = ?  and ";
                parameters.add(docNum);
              }
              if (type != null && !type.equals("")) {
                criterion += "loanFlowHead.bizType = ?  and ";
                parameters.add(type);
              }
              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "borrowerAcc.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId));
              }
              if (yesOrNo != null && !"".equals(yesOrNo)) {
                if ("0".equals(yesOrNo)) {
                  criterion += " loanFlowHead.batchNum is not null and ";
                } else if ("1".equals(yesOrNo)) {
                  criterion += " loanFlowHead.batchNum is  null and ";
                }
              }
              if (status != null && !status.equals("")) {
                if (status.equals("0")) {
                  criterion += "(loanFlowHead.bizSt >1 and loanFlowHead.bizSt < 5) and ";
                } else {
                  criterion += "loanFlowHead.bizSt = ?  and ";
                  parameters.add(status);
                }
              } else {
                criterion += "(loanFlowHead.bizSt >1 and loanFlowHead.bizSt < 7) and ";
              }
              if (dateStart != null && !dateStart.equals("") && dateEnd != null
                  && !dateEnd.equals("")) {
                criterion += "(loanFlowHead.bizDate between ? and ? ) and ";
                parameters.add(dateStart);
                parameters.add(dateEnd);
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult();
            }
          });
      count = counts.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 回收列表合计 jj
   * 
   * @param loanKouAcc
   * @param contractId
   * @param name
   * @param cardNum
   * @param docNum
   * @param type
   * @param loanBankId
   * @param status
   * @param securityInfo
   * @return
   */
  public List queryCallbackTotal_LJ(final String loanKouAcc,
      final String contractId, final String name, final String cardNum,
      final String docNum, final String type, final String loanBankId,
      final String status, final SecurityInfo securityInfo,
      final String dateStart, final String dateEnd) {
    List list = null;
    try {

      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select loankouacc, contractId,borrowername,docnum,cardNum,realcorpus,realinterest,occurmoney,"
              + "bizType,bizSt,loanBankId,headid,bizDate,realPunishInterest,realCount "
              + " from ("
              + "select pl202.flow_head_id as headid,"
              + " '' as loankouacc,"
              + " pl202.doc_num as docnum,"
              + " '' as contractId,"
              + " '' as borrowername,"
              + " '' as cardNum,"
              + " nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + " nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as realinterest"
              + ",nvl(pl202.real_punish_interest,0) as realPunishInterest,"
              + " nvl(pl202.occur_money,0) as occurmoney,"
              + " pl202.biz_type as bizType,"
              + " pl202.biz_st as bizSt,"
              + " pl202.loan_bank_id as loanBankId,"
              + " pl202.biz_date as bizDate,"
              + "pl202.real_count as realCount"
              + " from PL202 pl202 "
              + " where pl202.biz_type = '5'"
              + " union "
              + " select distinct pl202.flow_head_id as headid,"
              + " pl203.loan_kou_acc as loankouacc,"
              + " pl202.doc_num  as docnum,"
              + " pl203.contract_id as contractId,"
              + " pl110.borrower_name,"
              + " pl110.card_num as cardNum,"
              + " nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + " nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as realinterest "
              + ",nvl(pl202.real_punish_interest,0) as realPunishInterest,"
              + " nvl(pl202.occur_money,0) as occurmoney,"
              + " pl202.biz_type as bizType,"
              + " pl202.biz_st as bizSt,"
              + " pl202.loan_bank_id as loanBankId,"
              + " pl202.biz_date as bizDate,"
              + "pl202.real_count as realCount"
              + " from PL203 pl203, PL202 pl202,PL110 pl110"
              + " where pl202.flow_head_id = pl203.flow_head_id and pl202.biz_type in ('2', '3', '4') and pl110.contract_id=pl203.contract_id"
              + " ) where loanbankid " + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += "loankouacc = ?  and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += "contractId = ?  and ";
            parameters.add(contractId);
          }
          if (name != null && !name.equals("")) {
            criterion += "borrowername like ?  and ";
            parameters.add("%" + name + "%");
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += "cardNum = ?  and ";
            parameters.add(cardNum);
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += "docnum = ?  and ";
            parameters.add(docNum);
          }
          if (type != null && !type.equals("")) {
            criterion += "bizType = ?  and ";
            parameters.add(type);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (status != null && !status.equals("")) {
            if (status.equals("0")) {
              criterion += "(bizSt >1 and bizSt < 5) and ";
            } else {
              criterion += "bizSt = ?  and ";
              parameters.add(status);
            }
          } else {
            criterion += "(bizSt >1 and bizSt < 7) and ";
          }
          if (dateStart != null && !dateStart.equals("") && dateEnd != null
              && !dateEnd.equals("")) {
            criterion += "(bizDate between ? and ?) and ";
            parameters.add(dateStart);
            parameters.add(dateEnd);
          }
          if (criterion.length() != 0)
            criterion = "  and "
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
            LoancallbackTbDTO dto = new LoancallbackTbDTO();
            if (obj[0] != null) {
              dto.setLoanKouAcc(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setContractId(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setBorrowerName(obj[2].toString());
            }
            if (obj[3] != null) {
              dto.setDocNum(obj[3].toString());
            }
            if (obj[4] != null) {
              dto.setCardNum(obj[4].toString());
            }
            dto.setRealCorpus(new BigDecimal(obj[5].toString()));
            dto.setRealInterest(new BigDecimal(obj[6].toString()));
            dto.setOccurMoney(new BigDecimal(obj[7].toString()));
            dto.setBizType(obj[8].toString());
            dto.setBizSt(obj[9].toString());
            dto.setId(obj[11].toString());
            dto.setRealPunishInterest(new BigDecimal(obj[13].toString()));
            dto.setRealCount(obj[14].toString());
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
   * 结算单查询 贷方 单笔回收2，部份提前3，一次性清还4，批量回收5 笔数，正常本金，逾期本金，利息，罚息，挂账金额 jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceCallBack_LJ(final String bizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(loanFlowHead.flowHeadId),nvl(sum(loanFlowHead.realCorpus),0),nvl(sum(loanFlowHead.realOverdueCorpus),0),"
              + " nvl(sum(loanFlowHead.realInterest+loanFlowHead.realOverdueInterest),0),"
              + " nvl(sum(loanFlowHead.realPunishInterest),0),nvl(sum(loanFlowHead.occurMoney),0),"
              + " max(loanFlowHead.checkPerson),max(loanFlowHead.clearAccPerson) "
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ?  and ";
            parameters.add(bizType);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCount(new Integer(obj[0].toString()));
            dto.setCorpus(new BigDecimal(obj[1].toString()));
            dto.setOverdueCorpus(new BigDecimal(obj[2].toString()));
            dto.setInterest(new BigDecimal(obj[3].toString()));
            dto.setPunishInterest(new BigDecimal(obj[4].toString()));
            dto.setOverMoney(new BigDecimal(obj[5].toString()));
            if (obj[6] != null) {
              dto.setCheckPerson(obj[6].toString());
            }
            if (obj[7] != null) {
              dto.setClearAccPerson(obj[7].toString());
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

  /**
   * 结算单查询 贷方 呆账核销6、7，核销收回8、9 笔数，正常本金，逾期本金，利息，罚息，呆账核销金额 jj 2007-10-31
   * 
   * @param bizType1
   * @param bizType2
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceDestroy_LJ(final String bizType1,
      final String bizType2, final String loanBankId, final String makePerson,
      final String startDate, final String endDate, final String bizSt,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(loanFlowHead.flowHeadId),nvl(sum(loanFlowHead.realCorpus),0),nvl(sum(loanFlowHead.realOverdueCorpus),0),"
              + " nvl(sum(loanFlowHead.realInterest+loanFlowHead.realOverdueInterest),0),"
              + " nvl(sum(loanFlowHead.realPunishInterest),0),nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType1 != null && !bizType1.equals("") && bizType2 != null
              && !bizType2.equals("")) {
            criterion += "(loanFlowHead.bizType = ? or loanFlowHead.bizType = ?) and ";
            parameters.add(bizType1);
            parameters.add(bizType2);
          } else if (bizType1 != null && !bizType1.equals("")) {
            criterion += " loanFlowHead.bizType = ?  and ";
            parameters.add(bizType1);
          } else if (bizType2 != null && !bizType2.equals("")) {
            criterion += " loanFlowHead.bizType = ?  and ";
            parameters.add(bizType2);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCount(new Integer(obj[0].toString()));
            dto.setCorpus(new BigDecimal(obj[1].toString()));
            dto.setOverdueCorpus(new BigDecimal(obj[2].toString()));
            dto.setInterest(new BigDecimal(obj[3].toString()));
            dto.setPunishInterest(new BigDecimal(obj[4].toString()));
            dto.setOccurMoney(new BigDecimal(obj[5].toString()));
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
   * 结算单查询 贷方 挂账13 笔数，挂账金额 jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceOverpay_LJ(final String bizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(loanFlowHead.flowHeadId),nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCount(new Integer(obj[0].toString()));
            dto.setOverMoney(new BigDecimal(obj[1].toString()));
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
   * 结算单查询 贷方 错账调整12 笔数， jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountAdjustaccountCount_LJ(final String bizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(loanFlowHead.flowHeadId)  "
              + " from LoanFlowHead loanFlowHead where loanFlowHead.wrongBizType<>1 and loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCount(new Integer(obj.toString()));
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
   * 结算单查询 贷方 错账调整12 正常本金，利息，罚息 jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountAdjustaccountA_LJ(final String bizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.realCorpus),0),nvl(sum(loanFlowHead.realInterest+loanFlowHead.realOverdueInterest),0),"
              + " nvl(sum(loanFlowHead.realPunishInterest),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and (loanFlowHead.wrongBizType in(2,5,6,7) or loanFlowHead.wrongBizType is null) ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCorpus(new BigDecimal(obj[0].toString()));
            dto.setInterest(new BigDecimal(obj[1].toString()));
            dto.setPunishInterest(new BigDecimal(obj[2].toString()));
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
   * 结算单查询 贷方 错账调整12 呆账核销金额 jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountAdjustaccountC_LJ(final String bizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)  "
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.wrongBizType in(6,7) ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setOccurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 贷方 错账调整12 挂账金额 jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountAdjustaccountD_LJ(final String bizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)  "
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.wrongBizType in(2,5) ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setOverMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 贷方 错账调整12 逾期本金 jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountAdjustaccountB_LJ(final String bizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.realOverdueCorpus),0)  "
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.wrongBizType in(2,5,6,7) ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setOverdueCorpus(new BigDecimal(obj.toString()));
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
   * 结算单查询 借方 发放1 笔数，发生额 jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceLoanaccord_LJ(final String bizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(loanFlowHead.flowHeadId),nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCount(new Integer(obj[0].toString()));
            dto.setOccurMoney(new BigDecimal(obj[1].toString()));
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
   * 结算单查询 借方 错账调整12调发放1 笔数，发生额 jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceAdjustaccountLoanaccord_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(loanFlowHead.flowHeadId),nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          if (wrongBizType != null && !wrongBizType.equals("")) {
            criterion += "loanFlowHead.wrongBizType = ? and ";
            parameters.add(wrongBizType);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCount(new Integer(obj[0].toString()));
            dto.setOccurMoney(new BigDecimal(obj[1].toString()));
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
   * 结算单查询 期初本金余额 PL202中BIZ_DATE<会计日期的sum（BIZ_TYPE=1与BIZ_TYPE=12 and
   * WRONG_BIZ_TYPE=1的OCCUR_MONEY）-REAL_CORPUS-REAL_OVERDUE_CORPUS jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceInitialStagesCorpusA_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select nvl(sum(loanFlowHead.occurMoney),0) from LoanFlowHead loanFlowHead where (loanFlowHead.bizType=1 or ( loanFlowHead.bizType=12 and loanFlowHead.wrongBizType=1)) and loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = " + loanBankId + "  and ";
            // parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = '" + makePerson + "'  and ";
            // parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate < " + startDate + "  and ";
            // parameters.add(startDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = " + bizSt + "  and ";
            // parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          // Query query = session.createQuery(hql);
          // for (int i = 0; i < parameters.size(); i++) {
          // query.setParameter(i, parameters.get(i));
          // }
          Query query = session.createQuery(hql);
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setInitialStages_corpus(new BigDecimal(obj.toString()));
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
   * 结算单查询 期初本金余额 PL202中BIZ_DATE<会计日期的sum（BIZ_TYPE=1与BIZ_TYPE=12 and
   * WRONG_BIZ_TYPE=1的OCCUR_MONEY）-REAL_CORPUS-REAL_OVERDUE_CORPUS jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceInitialStagesCorpusB_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select nvl(sum(loanFlowHead.realCorpus+loanFlowHead.realOverdueCorpus),0) "
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate < ?  and ";
            parameters.add(startDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setInitialStages_corpus(new BigDecimal(obj.toString()));
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
   * 结算单查询 期初利息余额 期初利息余额：PL202中BIZ_DATE<会计日期的sum（REAL_INTEREST+REAL_OVERDUE_INTEREST）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceInitialStagesInterest_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.realInterest+loanFlowHead.realOverdueInterest),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate < ?  and ";
            parameters.add(startDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setInitialStages_interest(new BigDecimal(obj.toString()));
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
   * 结算单查询 期末利息余额 期末利息余额：PL202中BIZ_DATE<=会计日期的sum（REAL_INTEREST+REAL_OVERDUE_INTEREST）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceInitialFinalInterest_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.realInterest+loanFlowHead.realOverdueInterest),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate <= ?  and ";
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setFinal_interest(new BigDecimal(obj.toString()));
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
   * 结算单查询 期末本金余额：PL202中BIZ_DATE<=会计日期的sum（BIZ_TYPE=1与BIZ_TYPE=12 and
   * WRONG_BIZ_TYPE=1的OCCUR_MONEY）-sum（REAL_CORPUS+REAL_OVERDUE_CORPUS） jj
   * 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceFinalCorpusA_LJ(final String bizType,
      final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select nvl(sum(loanFlowHead.occurMoney),0) from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and (loanFlowHead.bizType=1 or ( loanFlowHead.bizType=12 and loanFlowHead.wrongBizType=1)) ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate <= ?  and ";
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setFinal_corpus(new BigDecimal(obj.toString()));
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
   * 结算单查询 期末本金余额：PL202中BIZ_DATE<=会计日期的sum（BIZ_TYPE=1与BIZ_TYPE=12 and
   * WRONG_BIZ_TYPE=1的OCCUR_MONEY）-sum（REAL_CORPUS+REAL_OVERDUE_CORPUS） jj
   * 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceFinalCorpusB_LJ(final String bizType,
      final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select nvl(sum(loanFlowHead.realCorpus+loanFlowHead.realOverdueCorpus),0) "
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate <= ?  and ";
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setFinal_corpus(new BigDecimal(obj.toString()));
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
   * 结算单查询 逾期本金发生额,PL202中BIZ_DATE=会计日期的记录中的REAL_OVERDUE_CORPUS
   * 利息发生额：PL202中BIZ_DATE=会计日期的sum（REAL_INTEREST+REAL_OVERDUE_INTEREST）
   * 罚息发生额：PL202中BIZ_DATE=会计日期的sum（REAL_PUNISH_INTEREST） jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceOccurMoney_LJ(final String bizType,
      final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.realCorpus),0),nvl(sum(loanFlowHead.realOverdueCorpus),0),"
              + " nvl(sum(loanFlowHead.realInterest+loanFlowHead.realOverdueInterest),0),nvl(sum(loanFlowHead.realPunishInterest),0) "
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object[] obj = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCorpus_occurMoney(new BigDecimal(obj[0].toString()));
            dto.setOverdue_occurMoney(new BigDecimal(obj[1].toString()));
            dto.setInterest_occurMoney(new BigDecimal(obj[2].toString()));
            dto.setPunishInterest_occurMoney(new BigDecimal(obj[3].toString()));
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
   * 结算单查询 正常本金发生额,PL202中BIZ_DATE=会计日期的记录中的sum(发放+错账1)-REAL_CORPUS,
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceCorpusOccurMoney_LJ(final String bizType,
      final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and "
              + " (loanFlowHead.bizType = 1 or loanFlowHead.bizType = 12 and loanFlowHead.wrongBizType = 1 )";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ?  and ?) and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCorpus_occurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 期初罚息余额 期初罚息余额：PL202中BIZ_DATE<会计日期的sum（REAL_PUNISH_INTEREST） jj
   * 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceInitialStagesPunishInterest_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.realPunishInterest),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate < ?  and ";
            parameters.add(startDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setInitialStages_punishInterest(new BigDecimal(obj.toString()));
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
   * 结算单查询 期末罚息余额 期末罚息余额：PL202中BIZ_DATE<=会计日期的sum（REAL_PUNISH_INTEREST） jj
   * 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceFinalPunishInterest_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.realPunishInterest),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate <= ?  and ";
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setFinal_punishInterest(new BigDecimal(obj.toString()));
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
   * 结算单查询 期初呆账核销金额 期初未收回金额：PL202中BIZ_DATE<会计日期的sum（BIZ_TYPE=6、7的OCCUR_MONEY）-sum（BIZ_TYPE=8、9的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceInitialStagesDestroyOccurMoneyBad_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType in (6,7) ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate < ?  and ";
            parameters.add(startDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setInitialStages_destroyOccurMoney(new BigDecimal(obj
                .toString()));
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
   * 结算单查询 期初呆账核销金额 期初未收回金额：PL202中BIZ_DATE<会计日期的sum（BIZ_TYPE=6、7的OCCUR_MONEY）-sum（BIZ_TYPE=8、9的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceInitialStagesDestroyOccurMoneyBadBack_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType in (8,9) ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate < ?  and ";
            parameters.add(startDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setInitialStages_destroyOccurMoney(new BigDecimal(obj
                .toString()));
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
   * 结算单查询 期初呆账核销金额 期初未收回金额：PL202中BIZ_DATE<会计日期的sum（BIZ_TYPE=6、7的OCCUR_MONEY）+sum（BIZ_TYPE=8、9的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceInitialStagesDestroyOccurMoneyA_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate < ?  and ";
            parameters.add(startDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setInitialStages_destroyOccurMoney(new BigDecimal(obj
                .toString()));
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
   * 结算单查询 期末呆账核销金额 期末未收回金额：PL202中BIZ_DATE<=会计日期的sum（BIZ_TYPE=6、7的OCCUR_MONEY）-sum（BIZ_TYPE=8、9的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceFinalDestroyOccurMoneyBad_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType in (6,7) ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate <= ?  and ";
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setFinal_destroyOccurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 期末呆账核销金额 期末未收回金额：PL202中BIZ_DATE<=会计日期的sum（BIZ_TYPE=6、7的OCCUR_MONEY）-sum（BIZ_TYPE=8、9的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceFinalDestroyOccurMoneyBadBack_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType in (8,9) ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate <= ?  and ";
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setFinal_destroyOccurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 期末呆账核销金额 期末未收回金额：PL202中BIZ_DATE<=会计日期的sum（BIZ_TYPE=6、7的OCCUR_MONEY）+sum（BIZ_TYPE=8、9的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceFinalDestroyOccurMoneyA_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType >5 and loanFlowHead.bizType <10 ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate <= ?  and ";
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setFinal_destroyOccurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 呆账核销发生额 呆账核销发生额：PL202中BIZ_DATE＝会计日期的sum（BIZ_TYPE=6、7的OCCUR_MONEY） jj
   * 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceDestroyOccurMoney_LJ(
      final String bizType1, final String bizType2, final String wrongBizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType1 != null && !bizType1.equals("") && bizType2 != null
              && !bizType2.equals("")) {
            criterion += "(loanFlowHead.bizType = ? or loanFlowHead.bizType = ?) and ";
            parameters.add(bizType1);
            parameters.add(bizType2);
          } else if (bizType1 != null && !bizType1.equals("")) {
            criterion += " loanFlowHead.bizType = ?  and ";
            parameters.add(bizType1);
          } else if (bizType2 != null && !bizType2.equals("")) {
            criterion += " loanFlowHead.bizType = ?  and ";
            parameters.add(bizType2);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ? and ? )  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setDestroyOccurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 核销收回发生额 核销收回发生额：PL202中BIZ_DATE＝会计日期的sum（BIZ_TYPE=8、9的OCCUR_MONEY） jj
   * 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceDestroybackOccurMoney_LJ(
      final String bizType1, final String bizType2, final String wrongBizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType1 != null && !bizType1.equals("") && bizType2 != null
              && !bizType2.equals("")) {
            criterion += "(loanFlowHead.bizType = ? or loanFlowHead.bizType = ?) and ";
            parameters.add(bizType1);
            parameters.add(bizType2);
          } else if (bizType1 != null && !bizType1.equals("")) {
            criterion += " loanFlowHead.bizType = ?  and ";
            parameters.add(bizType1);
          } else if (bizType2 != null && !bizType2.equals("")) {
            criterion += " loanFlowHead.bizType = ?  and ";
            parameters.add(bizType2);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += "(loanFlowHead.bizDate between ? and ? )  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setDestroybackOccurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 期初挂账金额 期初挂账金额：PL202中BIZ_DATE<会计日期的sum（BIZ_TYPE=13的OCCUR_MONEY）+sum（BIZ_TYPE=2、3、4、5的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceInitialStagesOverpayOccurMoney_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType in (2,3,4,5,12,13) ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate < ?  and ";
            parameters.add(startDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setInitialStages_overpayOccurMoney(new BigDecimal(obj
                .toString()));
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
   * 结算单查询 期初挂账金额 期初挂账金额：PL202中BIZ_DATE<会计日期的sum（BIZ_TYPE=13的OCCUR_MONEY）+sum（BIZ_TYPE=2、3、4、5的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceInitialStagesOverpayOccurMoneyA_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType in (2,3,4,5,13) ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate < ?  and ";
            parameters.add(startDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setInitialStages_overpayOccurMoney(new BigDecimal(obj
                .toString()));
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
   * 结算单查询 挂账发生额
   * 挂账发生额：PL202中BIZ_DATE=会计日期的sum（BIZ_TYPE=13的OCCUR_MONEY）+sum（BIZ_TYPE=2、3、4、5的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceOverpayOccurMoney_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType in (2,3,4,5,13) ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " (loanFlowHead.bizDate between ? and ?)  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setOverpayOccurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 挂账发生额
   * 挂账发生额：PL202中BIZ_DATE=会计日期的sum（BIZ_TYPE=13的OCCUR_MONEY）+sum（BIZ_TYPE=2、3、4、5的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceOverpayOccurMoneyA_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType in (2,3,4,5,13) ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " (loanFlowHead.bizDate between ? and ?)  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setOverpayOccurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 期末挂账金额 期末挂账金额：PL202中BIZ_DATE<=会计日期的sum（BIZ_TYPE=13的OCCUR_MONEY）+sum（BIZ_TYPE=2、3、4、5的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceFinalOverpayOccurMoney_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType in (2,3,4,5,13) ";
          Vector parameters = new Vector();
          String criterion = "";
          // if (bizType != null && !bizType.equals("")) {
          // criterion += "loanFlowHead.bizType = ? and ";
          // parameters.add(bizType);
          // }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate <= ?  and ";
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setFinal_overpayOccurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 期末挂账金额 期末挂账金额：PL202中BIZ_DATE<=会计日期的sum（BIZ_TYPE=13的OCCUR_MONEY）+sum（BIZ_TYPE=2、3、4、5的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceFinalOverpayOccurMoneyA_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate <= ?  and ";
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setFinal_overpayOccurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 期初保证金余额 期初保证金余额：PL202中BIZ_DATE<会计日期的sum（BIZ_TYPE=14的OCCUR_MONEY）+sum（BIZ_TYPE=15的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceInitialStagesBailOccurMoney_LJ(
      final String bizType1, final String bizType2, final String wrongBizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType in (14,15) ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType1 != null && !bizType1.equals("") && bizType2 != null
              && !bizType2.equals("")) {
            criterion += "(loanFlowHead.bizType = ? or loanFlowHead.bizType = ?) and ";
            parameters.add(bizType1);
            parameters.add(bizType2);
          } else if (bizType1 != null && !bizType1.equals("")) {
            criterion += " loanFlowHead.bizType = ?  and ";
            parameters.add(bizType1);
          } else if (bizType2 != null && !bizType2.equals("")) {
            criterion += " loanFlowHead.bizType = ?  and ";
            parameters.add(bizType2);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate < ?  and ";
            parameters.add(startDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setInitialStages_bailOccurMoney(new BigDecimal(obj.toString()));
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
   * 结算单查询 保证金发生额：PL202中BIZ_DATE=会计日期的sum（BIZ_TYPE=14,15的OCCUR_MONEY） jj
   * 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceBailOccurMoney_LJ(final String bizType,
      final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(loanFlowHead.flowHeadId), nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " (loanFlowHead.bizDate between ? and ? )  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object[] obj = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCount(new Integer(obj[0].toString()));
            dto.setBailOccurMoney(new BigDecimal(obj[1].toString()));
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
   * 结算单查询 贷方取正值 保证金发生额：PL202中BIZ_DATE=会计日期的sum（BIZ_TYPE=14,15的OCCUR_MONEY） jj
   * 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceBailOccurMoneyCredit_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(loanFlowHead.flowHeadId), nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.occurMoney >0 and loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " (loanFlowHead.bizDate between ? and ? )  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object[] obj = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCount(new Integer(obj[0].toString()));
            dto.setBailOccurMoney(new BigDecimal(obj[1].toString()));
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
   * 结算单查询 借方取负值 保证金发生额：PL202中BIZ_DATE=会计日期的sum（BIZ_TYPE=14的OCCUR_MONEY） jj
   * 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceBailOccurMoneyDebit_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(loanFlowHead.flowHeadId), nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.occurMoney < 0 and loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " (loanFlowHead.bizDate between ? and ? )  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object[] obj = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCount(new Integer(obj[0].toString()));
            dto.setBailOccurMoney(new BigDecimal(obj[1].toString()));
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
   * 结算单查询 借方 保证金提取利息：PL202中BIZ_DATE=会计日期的sum（BIZ_TYPE=14的otherInterest）并且发生额为负
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceBailOtherInterestDebit_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(loanFlowHead.flowHeadId), nvl(sum(loanFlowHead.otherInterest),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.occurMoney < 0 and loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " (loanFlowHead.bizDate between ? and ? )  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object[] obj = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCount(new Integer(obj[0].toString()));
            dto.setBailInterestOccurMoney(new BigDecimal(obj[1].toString()));
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
   * 结算单查询 保证金利息：PL202中BIZ_DATE=会计日期的sum（BIZ_TYPE=14的otherInterest） jj
   * 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceBailOtherInterest_LJ(
      final String bizType, final String wrongBizType, final String loanBankId,
      final String makePerson, final String startDate, final String endDate,
      final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(loanFlowHead.flowHeadId), nvl(sum(loanFlowHead.otherInterest),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ? and ";
            parameters.add(bizType);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " (loanFlowHead.bizDate between ? and ? )  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object[] obj = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setCount(new Integer(obj[0].toString()));
            dto.setBailInterestOccurMoney(new BigDecimal(obj[1].toString()));
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
   * 结算单查询 期末保证金余额 期末保证金余额：PL202中BIZ_DATE<=会计日期的sum（BIZ_TYPE=14的OCCUR_MONEY）+sum（BIZ_TYPE=15的OCCUR_MONEY）
   * jj 2007-10-31
   * 
   * @param bizType
   * @param loanBankId
   * @param makePerson
   * @param startDate
   * @param endDate
   * @param bizSt
   * @param securityInfo
   * @return
   */
  public List queryClearAccountBalanceFinalBailOccurMoney_LJ(
      final String bizType1, final String bizType2, final String wrongBizType,
      final String loanBankId, final String makePerson, final String startDate,
      final String endDate, final String bizSt, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(loanFlowHead.occurMoney),0)"
              + " from LoanFlowHead loanFlowHead where loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL()
              + " and loanFlowHead.bizType in (14,15) ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizType1 != null && !bizType1.equals("") && bizType2 != null
              && !bizType2.equals("")) {
            criterion += "(loanFlowHead.bizType = ? or loanFlowHead.bizType = ?) and ";
            parameters.add(bizType1);
            parameters.add(bizType2);
          } else if (bizType1 != null && !bizType1.equals("")) {
            criterion += " loanFlowHead.bizType = ?  and ";
            parameters.add(bizType1);
          } else if (bizType2 != null && !bizType2.equals("")) {
            criterion += " loanFlowHead.bizType = ?  and ";
            parameters.add(bizType2);
          }
          // if (wrongBizType != null && !wrongBizType.equals("")) {
          // criterion += "loanFlowHead.wrongBizType = ? and ";
          // parameters.add(wrongBizType);
          // }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (makePerson != null && !makePerson.equals("")) {
            criterion += "loanFlowHead.makePerson = ?  and ";
            parameters.add(makePerson);
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {
            criterion += " loanFlowHead.bizDate <= ?  and ";
            parameters.add(endDate);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = "  and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            ClearAccountBalanceInfoDTO dto = new ClearAccountBalanceInfoDTO();
            dto.setFinal_bailOccurMoney(new BigDecimal(obj.toString()));
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
   * 挂账维护
   * 
   * @author 郭婧平 2007-9-28 根据id删除pl202记录
   */
  public void deleteLoanFlowHead(final String flowHeadId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from LoanFlowHead loanFlowHead where loanFlowHead.flowHeadId= ? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, new Integer(flowHeadId));
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 更新记录
   * 
   * @param LoanFlowHead
   * @return
   */
  public void update(LoanFlowHead loanFlowHead) {
    Serializable id = null;
    try {
      Validate.notNull(loanFlowHead);
      getHibernateTemplate().update(loanFlowHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 根据条件查询业务复核列表
   * 
   * @author 吴迪 2007-09-2９
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @param securityInfo
   * @param docNum
   * @param contractId
   * @param loanKouAcc
   * @param borrowerName
   * @param makePerson
   * @param bizType
   * @param bizSt
   * @param loanBankName
   * @param beginBizDate
   * @param endBizDate
   * @return
   */
  public List queryBizCheckShowListByCriterions(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select docnum,loankouacc, contractId,borrowername,bizType,occurMoney,realcorpus,reclaimAccrual,realPunishInterest,badDebt,putUpMoney,bail,bailAccrual,bizSt,bizDate,wrongBizType,loanBankID, makePerson,flowheadID from "
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
              + "0 as bail,"
              + "0 as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID"
              + " from PL202 pl202"
              + " where pl202.biz_type = '5'"
              + " or (pl202.biz_type = '12' and pl202.wrong_biz_type = '5')"
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
              + " pl202.flow_head_id as flowheadID"
              + " from PL203 pl203, PL202 pl202,PL110 pl110"
              + " where pl202.flow_head_id = pl203.flow_head_id  and pl110.contract_id=pl203.contract_id"
              + " and ((pl202.biz_type in"
              + " ('1', '2', '3', '4', '6', '7', '8', '9', '13', '14')) or"
              + " (pl202.biz_type = '12' and"
              + " pl202.wrong_biz_type in ('1', '2', '6', '7')) or"
              + " (pl202.biz_type = '12' and pl202.wrong_biz_type is null))"
              + ")";
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
            criterion += "   bizSt = ? and ";
            parameters.add(bizSt);
          } else {
            criterion += "   bizSt in (4,5,6) and ";
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
          if (criterion.length() != 0)
            criterion = " where " + " loanBankID "
                + securityInfo.getDkSecuritySQL() + " and "
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
          // 业务复核列表
          List bizCheckShowList = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            BizCheckShowListDTO bizCheckShowListDTO = new BizCheckShowListDTO();
            obj = (Object[]) iterate.next();
            // 凭证编号PL202.DOC_NUM
            if (obj[0] != null && !obj[0].equals(""))
              bizCheckShowListDTO.setDocNum(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              // 贷款账号PL203.LOAN_KOU_ACC
              bizCheckShowListDTO.setLoanKouAcc(obj[1].toString());
            // 合同编号PL203.CONTRACT_ID
            if (obj[2] != null && !obj[2].equals(""))
              bizCheckShowListDTO.setContractId(obj[2].toString());
            // 借款人姓名PL110.BORROWER_NAME
            if (obj[3] != null && !obj[3].equals(""))
              bizCheckShowListDTO.setBorrowerName(obj[3].toString());
            // 业务类型L202.BIZ_TYPE
            if (obj[4] != null && !obj[4].equals("")) {
              bizCheckShowListDTO.setBizType(obj[4].toString());
              bizCheckShowListDTO.setOriginalitybizType(obj[4].toString());
            }
            // 发放金额L202.OCCUR_MONEY(当业务类型=1或业务类型=12，WRONG_BIZ_TYPE=1时)
            if (obj[5] != null && !obj[5].equals("")) {
              if (obj[4].equals("1")) {
                bizCheckShowListDTO.setOccurMoney(obj[5].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("1")) {
                    bizCheckShowListDTO.setOccurMoney(obj[5].toString());
                  }
                }
              }
            }
            // 回收本金PL202.REAL_CORPUS+PL202.REAL_OVERDUE_CORPUS(当业务类型=2,3,4,5,6,7,12时)
            BigDecimal reclaimCorpus = new BigDecimal(0.00);// 回收本金
            if (obj[6] != null && !obj[6].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {
                reclaimCorpus = new BigDecimal(obj[6].toString());
                bizCheckShowListDTO.setReclaimCorpus(obj[6].toString());
              }
            }
            // 回收利息PL202.REAL_INTEREST+PL202.REAL_OVERDUE_INTEREST(当业务类型=2,3,4,5,6,7,12时)
            BigDecimal reclaimAccrual = new BigDecimal(0.00);// 回收利息
            if (obj[7] != null && !obj[7].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {
                reclaimAccrual = new BigDecimal(obj[7].toString());
                bizCheckShowListDTO.setReclaimAccrual(obj[7].toString());
              }
            }
            // 回收罚息PL202.REAL_PUNISH_INTEREST(当业务类型=2,3,4,5,6,7,12时)
            BigDecimal realPunishInterest = new BigDecimal(0.00);// 回收罚息
            if (obj[8] != null && !obj[8].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {
                realPunishInterest = new BigDecimal(obj[8].toString());
                bizCheckShowListDTO.setRealPunishInterest(obj[8].toString());
              }
            }
            // 呆账核销金额PL202.OCCUR_MONEY(当业务类型=6,7,8,9或业务类型=12，WRONG_BIZ_TYPE=6或业务类型=12，WRONG_BIZ_TYPE=７)
            if (obj[9] != null && !obj[9].equals("")) {
              if (obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("8") || obj[4].equals("9")) {
                bizCheckShowListDTO.setBadDebt(obj[9].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("6") || obj[15].equals("7")) {
                    bizCheckShowListDTO.setBadDebt(obj[9].toString());
                  }
                }
              }
            }
            // 挂账金额PL202.OCCUR_MONEY(当业务类型=2,3,4,5,13或业务类型=12，WRONG_BIZ_TYPE=6或业务类型=2，WRONG_BIZ_TYPE=5)
            BigDecimal putupmoney = new BigDecimal(0.00);// 回收利息
            if (obj[10] != null && !obj[10].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("13")) {
                putupmoney = new BigDecimal(obj[10].toString());
                bizCheckShowListDTO.setPutUpMoney(obj[10].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("2") || obj[15].equals("5")) {
                    putupmoney = new BigDecimal(obj[10].toString());
                    bizCheckShowListDTO.setPutUpMoney(obj[10].toString());
                  }
                }
              }
            }

            // 保证金PL202.OCCUR_MONEY(当业务类型=14时)
            if (obj[11] != null && !obj[11].equals("")) {
              if (obj[4].equals("14")) {
                bizCheckShowListDTO.setBail(obj[11].toString());
              }
            }
            // 保证金利息(当业务类型=14时)
            if (obj[12] != null && !obj[12].equals("")) {
              if (obj[4].equals("14")) {
                bizCheckShowListDTO.setBailAccrual(obj[12].toString());
              }
            }
            // 业务状态PL202.BIZ_ST
            if (obj[13] != null && !obj[13].equals(""))
              bizCheckShowListDTO.setBizSt(obj[13].toString());
            // 办理日期PL202.BIZ_DATE
            if (obj[14] != null && !obj[14].equals(""))
              bizCheckShowListDTO.setBizDate(obj[14].toString());
            // pl202.flow_head_id
            if (obj[18] != null && !obj[18].equals(""))
              bizCheckShowListDTO.setFlowHeadId(obj[18].toString());
            // 本次应还金额=回收本金＋回收利息＋回收罚息
            BigDecimal reclaim = new BigDecimal(0.00);// 本次实还金额
            reclaim = reclaimCorpus.add(reclaimAccrual.add(realPunishInterest));
            bizCheckShowListDTO.setReclaim(reclaim);
            // 本次实还金额=回收本金＋回收利息＋回收罚息＋挂帐金额
            BigDecimal reclaimback = new BigDecimal(0.00);// 本次实收金额
            reclaimback = reclaim.add(putupmoney);
            bizCheckShowListDTO.setReclaimback(reclaimback);
            // 枚举转换业务状态
            try {
              bizCheckShowListDTO.setBizSt(BusiTools.getBusiValue(Integer
                  .parseInt("" + bizCheckShowListDTO.getBizSt()),
                  BusiConst.PLBUSINESSSTATE));
            } catch (Exception e) {
              e.printStackTrace();
            }

            // 枚举转换业务类型
            try {
              bizCheckShowListDTO.setBizType(BusiTools.getBusiValue(Integer
                  .parseInt("" + bizCheckShowListDTO.getBizType()),
                  BusiConst.PLBUSINESSTYPE));
            } catch (Exception e) {
              e.printStackTrace();
            }

            bizCheckShowList.add(bizCheckShowListDTO);
          }
          return bizCheckShowList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  /**
   * 业务复核列表合计信息
   * 
   * @author 吴迪 2007-10-29
   * @param securityInfo
   * @param docNum
   * @param contractId
   * @param loanKouAcc
   * @param borrowerName
   * @param makePerson
   * @param bizType
   * @param bizSt
   * @param loanBankName
   * @param beginBizDate
   * @param endBizDate
   * @return
   */
  public BizCheckTotalDTO queryBizCheckShowListCountByCriterions(
      final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate) {

    BizCheckTotalDTO bizCheckTotalDTO = new BizCheckTotalDTO();
    try {
      bizCheckTotalDTO = (BizCheckTotalDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select distinct a.flow_head_id ,"
                  + "a.biz_type ,"
                  + "a.biz_st ,"
                  + "a.wrong_biz_type ,"
                  + "nvl(a.occur_money, 0) as occurMoney,"
                  + "nvl(a.real_corpus, 0) + nvl(a.real_overdue_corpus, 0) as realcorpus,"
                  + "nvl(a.real_interest, 0) + nvl(a.real_overdue_interest, 0) as reclaimAccrual,"
                  + "nvl(a.real_punish_interest, 0) as realPunishInterest,"
                  + "nvl(a.occur_money, 0) as badDebt,"
                  + "nvl(a.occur_money, 0) as putUpMoney,"
                  + "nvl(a.occur_money, 0) as bail,"
                  + "nvl(a.other_interest, 0) as bailAccrual"
                  + " from pl202 a, pl203 b, pl110 c"
                  + " where a.flow_head_id = b.flow_head_id"
                  + "  and b.contract_id = c.contract_id"
                  + " and a.biz_type in"
                  + "  ('1', '2', '3', '4', '5', '6', '7', '8', '9', '12', '13', '14')";

              String criterion = "";
              Vector parameters = new Vector();

              if (docNum != null && !"".equals(docNum)) {
                criterion += " a.doc_num = ? and ";
                parameters.add(docNum);
              }
              if (contractId != null && !"".equals(contractId)) {
                criterion += " b.contract_id  = ? and ";
                parameters.add(contractId);
              }

              if (loanKouAcc != null && !"".equals(loanKouAcc)) {
                criterion += " b.loan_kou_acc = ? and ";
                parameters.add(loanKouAcc);
              }
              if (borrowerName != null && !"".equals(borrowerName)) {
                criterion += " c.borrower_name = ? and ";
                parameters.add(borrowerName);
              }
              if (makePerson != null && !"".equals(makePerson)) {
                criterion += " a.make_person = ? and ";
                parameters.add(makePerson);
              }
              if (bizType != null && !"".equals(bizType)) {
                criterion += " a.biz_type = ? and ";
                parameters.add(bizType);
              }
              if (bizSt != null && !"".equals(bizSt)) {
                criterion += "  a.biz_st = ? and ";
                parameters.add(bizSt);

              } else {
                criterion += "  a.biz_st in (4,5,6) and ";
              }
              if (loanBankName != null && !"".equals(loanBankName)) {
                criterion += " a.loan_bank_id = ? and ";
                parameters.add(loanBankName);
              }
              if (beginBizDate != null && !"".equals(beginBizDate)) {
                criterion += " a.biz_date >= ? and ";
                parameters.add(beginBizDate);
              }
              if (endBizDate != null && !"".equals(endBizDate)) {
                criterion += " a.biz_date <= ? and ";
                parameters.add(endBizDate);
              }
              if (criterion.length() != 0)
                criterion = " and " + " a.loan_bank_id  "
                    + securityInfo.getDkSecuritySQL() + " and  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;

              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              List queryList = query.list();

              BizCheckTotalDTO bizCheckTotalDTO = new BizCheckTotalDTO();

              BigDecimal occurMoneyTotle = new BigDecimal(0.00);// 发放金额-总额

              BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// 回收本金-总额

              BigDecimal reclaimAccrualTotle = new BigDecimal(0.00);// 回收利息-总额总额

              BigDecimal realPunishInterestTotle = new BigDecimal(0.00);// 回收罚息-总额

              BigDecimal badDebtTotle = new BigDecimal(0.00);// 呆账核销金额-总额

              BigDecimal putUpMoneyTotle = new BigDecimal(0.00);// 挂账金额-总额

              BigDecimal bailTotle = new BigDecimal(0.00);// 保证金-总额

              BigDecimal bailAccrualTotle = new BigDecimal(0.00);// 保证金利息-总额

              int affirmbizSt = 0;// 确认状态个数

              int checkbizSt = 0;// 复合状态个数

              int count = 0;
              Iterator iteratetotle = queryList.iterator();
              Object[] objtotle = null;

              while (iteratetotle.hasNext()) {

                objtotle = (Object[]) iteratetotle.next();

                if (objtotle[0] != null && !objtotle[0].equals("")) {
                  count = count + 1;
                }
                if (objtotle[2] != null && !objtotle[2].equals("")) {
                  if (objtotle[2].toString().equals("4")) {
                    affirmbizSt = affirmbizSt + 1;
                  } else if (objtotle[2].toString().equals("5")) {
                    checkbizSt = checkbizSt + 1;
                  }

                }
                // 发放金额-总额
                if (objtotle[4] != null && !objtotle[4].equals("")) {
                  if (objtotle[1].equals("1")) {
                    occurMoneyTotle = occurMoneyTotle.add(new BigDecimal(
                        objtotle[4].toString()));
                  } else if (objtotle[1].equals("12")) {
                    if ((objtotle[3] != null && !objtotle[3].equals(""))) {
                      if (objtotle[3].equals("1")) {
                        occurMoneyTotle = occurMoneyTotle.add(new BigDecimal(
                            objtotle[4].toString()));
                      }
                    }
                  }
                }
                // 回收本金-总额

                if (objtotle[5] != null && !objtotle[5].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {
                    reclaimCorpusTotle = reclaimCorpusTotle.add(new BigDecimal(
                        objtotle[5].toString()));
                  }
                }
                // 回收利息-总额
                if (objtotle[6] != null && !objtotle[6].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {
                    reclaimAccrualTotle = reclaimAccrualTotle
                        .add(new BigDecimal(objtotle[6].toString()));
                  }
                }
                // 回收罚息-总额
                if (objtotle[7] != null && !objtotle[7].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {

                    realPunishInterestTotle = realPunishInterestTotle
                        .add(new BigDecimal(objtotle[7].toString()));
                  }
                }
                // 呆账核销金额-总额

                if (objtotle[8] != null && !objtotle[8].equals("")) {
                  if (objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("8") || objtotle[1].equals("9")) {
                    badDebtTotle = badDebtTotle.add(new BigDecimal(objtotle[8]
                        .toString()));
                  } else if (objtotle[1].equals("12")) {
                    if ((objtotle[3] != null && !objtotle[3].equals(""))) {
                      if (objtotle[3].equals("6") || objtotle[3].equals("7")) {
                        badDebtTotle = badDebtTotle.add(new BigDecimal(
                            objtotle[8].toString()));
                      }
                    }
                  }
                }
                // 挂账金额-总额
                if (objtotle[9] != null && !objtotle[9].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("13")) {
                    putUpMoneyTotle = putUpMoneyTotle.add(new BigDecimal(
                        objtotle[9].toString()));
                  } else if (objtotle[1].equals("12")) {
                    if ((objtotle[3] != null && !objtotle[3].equals(""))) {
                      if (objtotle[3].equals("2") || objtotle[3].equals("5")) {
                        putUpMoneyTotle = putUpMoneyTotle.add(new BigDecimal(
                            objtotle[9].toString()));

                      }
                    }
                  }
                }
                // 保证金-总额
                if (objtotle[10] != null && !objtotle[10].equals("")) {
                  if (objtotle[1].equals("14")) {
                    bailTotle = bailTotle.add(new BigDecimal(objtotle[10]
                        .toString()));
                  }
                }
                // 保证金利息-总额
                if (objtotle[11] != null && !objtotle[11].equals("")) {
                  if (objtotle[4].equals("14")) {
                    bailAccrualTotle = bailAccrualTotle.add(new BigDecimal(
                        objtotle[11].toString()));
                  }
                }

              }
              bizCheckTotalDTO.setCount(count);
              bizCheckTotalDTO.setAffirmbizSt(affirmbizSt);
              bizCheckTotalDTO.setCheckbizSt(checkbizSt);
              bizCheckTotalDTO.setOccurMoneyTotle(occurMoneyTotle);
              bizCheckTotalDTO.setReclaimCorpusTotle(reclaimCorpusTotle);
              bizCheckTotalDTO.setReclaimAccrualTotle(reclaimAccrualTotle);
              bizCheckTotalDTO
                  .setRealPunishInterestTotle(realPunishInterestTotle);
              bizCheckTotalDTO.setBadDebtTotle(badDebtTotle);
              bizCheckTotalDTO.setBailAccrualTotle(bailAccrualTotle);
              bizCheckTotalDTO.setBailTotle(bailTotle);
              bizCheckTotalDTO.setPutUpMoneyTotle(putUpMoneyTotle);
              // 本次应还金额=回收本金总额＋回收利息总额＋回收罚息总额
              BigDecimal reclaimtotle = new BigDecimal(0.00);// 本次实还金额
              reclaimtotle = reclaimCorpusTotle.add(reclaimAccrualTotle
                  .add(realPunishInterestTotle));
              // 本次实还金额=回收本金总额＋回收利息总额＋回收罚息总额＋挂帐金额
              BigDecimal reclaimbacktotle = new BigDecimal(0.00);// 本次实收金额
              reclaimbacktotle = reclaimtotle.add(putUpMoneyTotle);
              bizCheckTotalDTO.setReclaimtotle(reclaimtotle);
              bizCheckTotalDTO.setReclaimbacktotle(reclaimbacktotle);
              return bizCheckTotalDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bizCheckTotalDTO;
  }

  /**
   * 根据条件查询保证金登记维护列表
   * 
   * @author 王野 2007-10-03
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param loanBankName
   * @param bizSt
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @return
   */
  public List queryBailenRolListByCriterions(final String contractId,
      final String borrowerName, final String cardNum,
      final String loanBankName, final String bizSt, final String findType,
      final int start, final String orderBy, final String order,
      final int pageSize, final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p110.contract_id contract_id,"
              + " p110.borrower_name borrower_name,"
              + " p110.card_num card_num," + " p202.loan_bank_id loan_bank_id,"
              + " p202.biz_date biz_date," + " p202.occur_money occur_money,"
              + " p202.biz_st biz_st," + " p202.flow_head_id flow_head_id "
              + " from pl203 p203,pl110 p110,pl202 p202"
              + " where p110.contract_id = p203.contract_id "
              + " and p203.flow_head_id = p202.flow_head_id "
              + " and p202.occur_money >= 0 " + " and p202.biz_type = '14' "
              + " and p202.loan_bank_id " + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (findType != null || !"".equals(findType)) {
            criterion += " p202.biz_st in  " + findType + " and ";
          }

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
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }

          if (bizSt != null && !"".equals(bizSt)) {
            criterion += " p202.biz_st = ? and ";
            parameters.add(bizSt);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p202.flow_head_id ";

          String od = order;
          if (od == null)
            od = " DESC";

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
   * 查询保证金登记维护列表 保证金金额合计+记录数(count)
   * 
   * @author 王野 2007-10-03
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param loanBankName
   * @param bizSt
   * @param findType
   * @return list
   */
  public List queryBailenRolSumMoneyByCriterions(final String contractId,
      final String borrowerName, final String cardNum,
      final String loanBankName, final String bizSt, final String findType,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(p202.flow_head_id) as count_headid,nvl(sum(p202.occur_money),0) as occur_totle_money "
              + " from pl203 p203,pl110 p110,pl202 p202 "
              + " where p110.contract_id = p203.contract_id "
              + " and p203.flow_head_id = p202.flow_head_id "
              + " and p202.occur_money >= 0 "
              + " and p202.biz_type = '14'"
              + " and p202.loan_bank_id " + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (findType != null || !"".equals(findType)) {
            criterion += " p202.biz_st in  " + findType + " and ";
          }

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
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }

          if (bizSt != null && !"".equals(bizSt)) {
            criterion += " p202.biz_st = ? and ";
            parameters.add(bizSt);
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
          Object[] obj = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BailenRolTbDTO dto = new BailenRolTbDTO();
            dto.setCount(new Integer(obj[0].toString()));
            dto.setOccurMoneyTotal(new BigDecimal(obj[1].toString()));
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
   * 保证金登记维护
   * 
   * @author 王野 2007-10-04 通过贷款流水账头表ID查询业务状态
   * @param flowHeadId
   * @return
   */
  public String queryBizStByFlowHeadId(final String flowHeadId) {
    String bizSt = null;
    bizSt = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select loanFlowHead.bizSt from LoanFlowHead loanFlowHead where loanFlowHead.flowHeadId = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(flowHeadId));
        return query.uniqueResult();
      }
    });
    return bizSt;
  }

  /**
   * 保证金登记维护列表打印
   * 
   * @author 王野 2007-10-04
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param loanBankName
   * @param bizSt
   * @param findType
   * @return
   */
  public List queryBailenRolPrintListByCriterions(
      final SecurityInfo securityInfo, final String contractId,
      final String borrowerName, final String cardNum,
      final String loanBankName, final String bizSt, final String findType) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p110.contract_id contract_id,"
              + " p110.borrower_name borrower_name,"
              + " p110.card_num card_num," + " p202.loan_bank_id loan_bank_id,"
              + " p202.biz_date biz_date," + " p202.occur_money occur_money,"
              + " p202.biz_st biz_st "
              + " from pl110 p110, pl202 p202, pl203 p203 "
              + " where p110.contract_id = p203.contract_id "
              + " and p203.flow_head_id = p202.flow_head_id "
              + " and p202.occur_money >= 0 " + " and p202.biz_type = '14' "
              + " and p202.loan_bank_id " + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (findType != null || !"".equals(findType)) {
            criterion += " p202.biz_st in  " + findType + " and ";
          }

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
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }

          if (bizSt != null && !"".equals(bizSt)) {
            criterion += " p202.biz_st = ? and ";
            parameters.add(bizSt);
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

  /**
   * 办理保证金结息
   * 
   * @author 王野 2007-10-06 判断该银行下会计日期为"会计年的6月30日"的是否存在结息(false) （PL202.Type=15）
   * @param bizDate
   * @param loanBankName
   * @return
   */
  public boolean isExistsClearinterestTa(final String bizDate,
      final String loanBankName) {
    boolean flag = true;
    String clearinterestTaCount = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(p202.flow_head_id) "
                + " from pl202 p202 " + " where p202.biz_type = 15 "
                + " and p202.biz_date = ? " + " and p202.loan_bank_id = ? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, bizDate);
            query.setParameter(1, loanBankName);
            return query.uniqueResult().toString();
          }
        });
    if (new Integer(clearinterestTaCount).intValue() > 0) {
      flag = false;
    }
    return flag;
  }

  /**
   * 办理保证金结息
   * 
   * @author 王野 2007-10-05 根据条件查询保证金结息列表
   * @param beginDate
   * @param endDate
   * @param loanBankName
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @return
   */
  public List queryBailClearInterestTaListByCriterions(
      final String loanBankName, final int start, final String orderBy,
      final String order, final int pageSize, final int page) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p111.loan_bank_id loan_bank_id, "
              + " (select pl003.param_explain from pl003 pl003 where pl003.loan_bank_id=p111.loan_bank_id and pl003.param_type='A' and pl003.param_num=8 and pl003.param_value=2) param_explain_d, "
              + " (select pl003.param_explain from pl003 pl003 where pl003.loan_bank_id=p111.loan_bank_id and pl003.param_type='A' and pl003.param_num=8 and pl003.param_value=1) param_explain_l, "
              + " p111.loan_kou_acc loan_kou_acc, "
              + " p111.contract_id contract_id, "
              + " p110.borrower_name borrower_name, "
              + " p110.card_num card_num, "
              + " p111.bail_balance bail_balance, "
              + " p111.cur_integral cur_integral "
              + " from pl110 p110, pl111 p111 "
              + " where p110.contract_id = p111.contract_id "
              + " and p111.cur_integral is not null ";

          Vector parameters = new Vector();
          String criterion = "";

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p111.loan_bank_id ";

          String od = order;
          if (od == null)
            od = " DESC";

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
   * 办理保证金结息
   * 
   * @author 王野 2007-10-05 查询保证金结息列表(全部)
   * @param beginDate
   * @param endDate
   * @param loanBankName
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @return
   */
  public List queryBailClearInterestTaCountByCriterions(
      final String loanBankName) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p111.loan_bank_id loan_bank_id, "
              + " (select pl003.param_explain from pl003 pl003 where pl003.loan_bank_id=p111.loan_bank_id and pl003.param_type='A' and pl003.param_num=8 and pl003.param_value=2) param_explain_d, "
              + " (select pl003.param_explain from pl003 pl003 where pl003.loan_bank_id=p111.loan_bank_id and pl003.param_type='A' and pl003.param_num=8 and pl003.param_value=1) param_explain_l, "
              + " p111.loan_kou_acc loan_kou_acc, "
              + " p111.contract_id contract_id, "
              + " p110.borrower_name borrower_name, "
              + " p110.card_num card_num, "
              + " p111.bail_balance bail_balance, "
              + " p111.pre_integral pre_integral, "
              + " p111.cur_integral cur_integral "
              + " from pl110 p110, pl111 p111 "
              + " where p110.contract_id = p111.contract_id "
              + " and p111.cur_integral is not null ";

          Vector parameters = new Vector();
          String criterion = "";

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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
   * 办理保证金结息
   * 
   * @author 王野 2007-10-05 查询结息前保证金合计
   * @param beginDate
   * @param endDate
   * @param loanBankName
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @return
   */
  public BigDecimal queryBailClearInterestTaSumMoneyByCriterions(
      final String loanBankName) {
    BigDecimal bailBalanceTotle = new BigDecimal(0.00);
    try {
      bailBalanceTotle = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(p111.bail_balance) bail_balance_total "
                  + " from pl110 p110, pl111 p111 "
                  + " where p110.contract_id = p111.contract_id "
                  + " and p111.cur_integral is not null ";

              Vector parameters = new Vector();
              String criterion = "";

              if (loanBankName != null && !"".equals(loanBankName)) {
                criterion += " p111.loan_bank_id = ? and ";
                parameters.add(loanBankName);
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
    return bailBalanceTotle;
  }

  /**
   * 办理保证金结息
   * 
   * @author 王野 2007-10-06 判断列表中是否存在未记账的记录（该操作员权限下是否存在PL202.TYPE=14 and
   *         PL202.BIZ_ST!=6的记录）
   * @param loanBankName
   * @return
   */
  public boolean isExistsClearinterestTaNoBizSt6(final String loanBankName) {
    boolean flag = true;
    String noBizSt6Count = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(p202.flow_head_id) "
                + " from pl202 p202 "
                + " where p202.biz_st<=5 and p202.biz_type = 14 "
                + " and p202.loan_bank_id = ? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, loanBankName);
            return query.uniqueResult().toString();

          }
        });
    if (new Integer(noBizSt6Count).intValue() > 0) {
      flag = false;
    }
    return flag;
  }

  /**
   * 办理保证金结息
   * 
   * @author 王野 2007-10-06 判断该结息年度下是否存在结息的记录（该操作员权限下是否存在PL202.TYPE=15 and
   *         PL202.BIZ_ST=6的记录，BIZ_DATE的年在会计年-1到会计年之间）
   * @param bizDate
   * @param loanBankName
   * @return
   */
  public boolean isExistsClearinterestTaBisSt6(final String bizDate,
      final String loanBankName) {
    boolean flag = true;
    String bizSt6Count = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(p202.flow_head_id) "
                + " from pl202 p202 "
                + " where p202.biz_type = 15 and p202.biz_st=6"
                + " and p202.biz_date = ? " + " and p202.loan_bank_id = ? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, bizDate);
            query.setParameter(1, loanBankName);
            return query.uniqueResult().toString();

          }
        });
    if (new Integer(bizSt6Count).intValue() > 0) {
      flag = false;
    }
    return flag;
  }

  /**
   * 保证金结息维护
   * 
   * @author 王野 2007-10-08 根据条件查询保证金结息维护列表
   * @param loanKouAcc
   * @param borrowerName
   * @param bizDate
   * @param loanBankName
   * @param bankList
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @return
   */
  public List queryBailClearInterestTbListByCriterions(final String loanKouAcc,
      final String borrowerName, final String bizDate,
      final String loanBankName, final List bankList, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p202.biz_date biz_date,"
              + " p202.loan_bank_id loan_bank_id,"
              + " p203.loan_kou_acc loan_kou_acc,"
              + "  p110.borrower_name borrower_name,"
              + " p111.bail_balance bail_balance,"
              + " p203.occur_money occur_money,"
              + " (select sum(pl203.occur_money) "
              + " from pl203 pl203,pl202 pl202 "
              + " where pl203.flow_head_id = pl202.flow_head_id "
              + " and pl203.contract_id = p203.contract_id "
              + " and pl202.biz_date >= p202.biz_date and pl202.biz_type = 15) afteryear_occur_money "
              + " from pl202 p202, pl203 p203, pl111 p111, pl110 p110 "
              + " where p110.contract_id = p111.contract_id "
              + " and p111.contract_id = p203.contract_id "
              + " and p203.flow_head_id = p202.flow_head_id "
              + " and p111.loan_bank_id = p202.loan_bank_id "
              + " and p202.biz_type = 15 ";

          Vector parameters = new Vector();
          String criterion = "";

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          } else {// 权限为操作员所管的银行
            if (bankList != null && bankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < bankList.size(); i++) {
                criterion += " p202.loan_bank_id = ? or ";
                parameters.add(bankList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
          }

          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " p203.loan_kou_acc = ? and ";
            parameters.add(loanKouAcc);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (bizDate != null && !"".equals(bizDate)) {
            criterion += " p202.biz_date = ? and ";
            parameters.add(bizDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p202.biz_date, p202.loan_bank_id ";

          String od = order;
          if (od == null)
            od = " DESC";

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
   * 保证金结息维护
   * 
   * @author 王野 2007-10-08 查询保证金结息维护列表(全部)
   * @param loanKouAcc
   * @param borrowerName
   * @param bizDate
   * @param loanBankName
   * @param bankList
   * @return
   */
  public List queryBailClearInterestTbCountByCriterions(
      final String loanKouAcc, final String borrowerName, final String bizDate,
      final String loanBankName, final List bankList) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p202.biz_date biz_date,"
              + " p202.loan_bank_id loan_bank_id,"
              + " p203.loan_kou_acc loan_kou_acc,"
              + "  p110.borrower_name borrower_name,"
              + " p111.bail_balance bail_balance,"
              + " p203.occur_money occur_money,"
              + " (select sum(pl203.occur_money) "
              + " from pl203 pl203,pl202 pl202 "
              + " where pl203.flow_head_id = pl202.flow_head_id "
              + " and pl203.contract_id = p203.contract_id "
              + " and pl202.biz_date >= p202.biz_date and pl202.biz_type = 15) afteryear_occur_money "
              + " from pl202 p202, pl203 p203, pl111 p111, pl110 p110 "
              + " where p110.contract_id = p111.contract_id "
              + " and p111.contract_id = p203.contract_id "
              + " and p203.flow_head_id = p202.flow_head_id "
              + " and p111.loan_bank_id = p202.loan_bank_id "
              + " and p202.biz_type = 15 ";

          Vector parameters = new Vector();
          String criterion = "";

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          } else {
            if (bankList != null && bankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < bankList.size(); i++) {
                criterion += " p202.loan_bank_id = ? or ";
                parameters.add(bankList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
          }

          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " p203.loan_kou_acc = ? and ";
            parameters.add(loanKouAcc);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (bizDate != null && !"".equals(bizDate)) {
            criterion += " p202.biz_date = ? and ";
            parameters.add(bizDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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
   * 银行代扣导出 根据放款银行查询是否有未记账的业务 jj
   * 
   * @param loanBankId
   * @return
   */
  public List queryBizStListByLoanBank_LJ(final String loanBankId) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanFlowHead.flowHeadId from LoanFlowHead loanFlowHead";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (criterion.length() != 0)
            criterion = " where loanFlowHead.bizSt>1 and loanFlowHead.bizSt<6 and loanFlowHead.bizType "
                + "in('2','3','4','5','6','7','8','9','12','13') and "
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
   * 银行代扣导入判断是否有批量未记账的业务 jj
   * 
   * @param loanBankId
   * @return
   */
  public List queryBizStListImportByLoanBank_LJ(final String loanBankId,
      final String bizType) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanFlowHead.flowHeadId from LoanFlowHead loanFlowHead";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ?  and ";
            parameters.add(bizType);
          }
          if (criterion.length() != 0)
            criterion = " where loanFlowHead.bizSt>1 and loanFlowHead.bizSt<6 and "
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
   * 查询错帐调整弹出框的方法
   * 
   * @param dto 封装了查询条件的DTO
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws Exception
   * @author 付云峰
   */
  public List queryAdjustAccountPopList(final AdjustAccountPopFindDTO dto,
      final String orderBy, final String order, final int start,
      final int pageSize, final List loanbankList) throws Exception {

    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select headid,loankouacc,docnum,contractId, borrowername,cardNum,loanBackPay,occurmoney,bizType,loanbankid,bizdate from ("
              + "select p02.flow_head_id as headid,"
              + "'' as loankouacc,"
              + "p02.doc_num as docnum,"
              + "'' as contractId,"
              + "'' as borrowername,"
              + "'' as cardNum,"
              + "nvl(p02.real_corpus,0)+nvl(p02.real_interest,0)+nvl(p02.real_overdue_corpus,0)+nvl(p02.real_overdue_interest,0)+nvl(p02.real_punish_interest,0) as loanBackPay,"
              + "nvl(p02.occur_money,0) as occurmoney,"
              + "p02.biz_type as bizType,"
              + "p02.loan_bank_id as loanbankid,"
              + "p02.biz_date as bizdate "
              + "from pl202 p02"
              + " where p02.biz_type = '5' and p02.biz_st='6'"
              + " union "
              + "select p02.flow_head_id as headid,"
              + "p03.loan_kou_acc as loankouacc,"
              + "p02.doc_num as docnum,"
              + "p03.contract_id as contractId,"
              + "p10.borrower_name as borrowername,"
              + "p10.card_num as cardNum,"
              + "nvl(p02.real_corpus,0)+nvl(p02.real_interest,0)+nvl(p02.real_overdue_corpus,0)+nvl(p02.real_overdue_interest,0)+nvl(p02.real_punish_interest,0) as loanBackPay,"
              + "nvl(p02.occur_money,0) as occurmoney,"
              + "p02.biz_type as bizType,"
              + "p02.loan_bank_id as loanbankid,"
              + "p02.biz_date as bizdate "
              + "from pl203 p03, pl202 p02,pl110 p10"
              + " where p02.flow_head_id=p03.flow_head_id and p03.contract_id=p10.contract_id and p02.biz_type in ('1','2', '6', '7') and p02.biz_st='6') res";

          Vector parameters = new Vector();
          String criterion = "";
          String contractId = dto.getContractId();
          if (contractId != null && !contractId.equals("")) {
            criterion += " res.contractId=? and ";
            parameters.add(contractId.trim());
          }
          String loanKouAcc = dto.getLoanKouAcc();
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += " res.loankouacc=? and ";
            parameters.add(loanKouAcc.trim());
          }
          String borrowerName = dto.getBorrowerName();
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " res.borrowername=? and ";
            parameters.add(borrowerName.trim());
          }

          String docNum = dto.getDocNum();
          if (docNum != null && !docNum.equals("")) {
            criterion += " res.docnum=? and ";
            parameters.add(docNum.trim());
          }

          String bizDate = dto.getBizDate();
          if (bizDate != null && !bizDate.equals("")) {
            criterion += " res.bizdate=? and ";
            parameters.add(bizDate.trim());
          }

          String bizType = dto.getBizType();
          if (bizType != null && !bizType.equals("")) {
            criterion += " res.bizType=? and ";
            parameters.add(bizType.trim());
          }

          String loanBankId = dto.getLoanBankId();
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " res.loanbankid=? and ";
            parameters.add(loanBankId.trim());
          } else {
            if (loanbankList != null && loanbankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanbankList.size(); i++) {
                criterion += " res.loanbankid=? or ";
                parameters.add(loanbankList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
          }

          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " res.loankouacc ";

          String od = order;
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
          Object obj[] = null;
          AdjustAccountPopListDTO adjustAccountPopListDTO = null;
          // 暂时存放列表的DTO
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // 将查询的结果封装到DTO中
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            adjustAccountPopListDTO = new AdjustAccountPopListDTO();
            if (obj[0] != null) {
              adjustAccountPopListDTO.setFlowHeadId(new Integer(obj[0]
                  .toString()));
            }
            if (obj[1] != null) {
              adjustAccountPopListDTO.setLoanKouAcc(obj[1].toString());
            }
            if (obj[2] != null) {
              adjustAccountPopListDTO.setDocNum(obj[2].toString());
            }
            if (obj[3] != null) {
              adjustAccountPopListDTO.setContractId(obj[3].toString());
            }
            if (obj[4] != null) {
              adjustAccountPopListDTO.setBorrowerName(obj[4].toString());
            }
            if (obj[5] != null) {
              adjustAccountPopListDTO.setCardNum(obj[5].toString());
            }
            if (obj[6] != null) {
              adjustAccountPopListDTO.setLoanBackPay(new BigDecimal(obj[6]
                  .toString()));
            }
            if (obj[7] != null) {
              adjustAccountPopListDTO.setOccurMoney(new BigDecimal(obj[7]
                  .toString()));
            }
            if (obj[8] != null) {
              adjustAccountPopListDTO.setBizType(obj[8].toString());
            }
            if (obj[9] != null) {
              adjustAccountPopListDTO.setLoanbankid(obj[9].toString());
            }
            if (obj[10] != null) {
              adjustAccountPopListDTO.setBizdate(obj[10].toString());
            }

            temp_list.add(adjustAccountPopListDTO);
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
   * 查询错帐调整弹出框count的方法
   * 
   * @param dto
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return count
   * @throws Exception
   * @author 付云峰
   */
  public int queryAdjustAccountPopCount(final AdjustAccountPopFindDTO dto,
      final String orderBy, final String order, final int start,
      final int pageSize, final List loanbankList) throws Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select "
              + "distinct(p02.flow_head_id) "
              + "from pl202 p02,pl203 p03,pl110 p10 where p02.flow_head_id=p03.flow_head_id and p03.contract_id=p10.contract_id and p02.biz_type in ('1','2','5','6','7') and p02.biz_st=6 ";

          Vector parameters = new Vector();
          String criterion = "";

          String contractId = dto.getContractId();
          if (contractId != null && !contractId.equals("")) {
            criterion += " p03.contract_id=? and ";
            parameters.add(contractId.trim());
          }
          String loanKouAcc = dto.getLoanKouAcc();
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += " p03.loan_kou_acc=? and ";
            parameters.add(loanKouAcc.trim());
          }

          String borrowerName = dto.getBorrowerName();
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " p10.borrower_name=? and ";
            parameters.add(borrowerName.trim());
          }

          String docNum = dto.getDocNum();
          if (docNum != null && !docNum.equals("")) {
            criterion += " p02.doc_num=? and ";
            parameters.add(docNum.trim());
          }

          String bizDate = dto.getBizDate();
          if (bizDate != null && !bizDate.equals("")) {
            criterion += " p02.biz_date=? and ";
            parameters.add(bizDate.trim());
          }

          String bizType = dto.getBizType();
          if (bizType != null && !bizType.equals("")) {
            criterion += " p02.biz_type=? and ";
            parameters.add(bizType.trim());
          }

          String loanBankId = dto.getLoanBankId();
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " p02.loan_bank_id=? and ";
            parameters.add(loanBankId.trim());
          } else {
            if (loanbankList != null && loanbankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanbankList.size(); i++) {
                criterion += " p02.loan_bank_id=? or ";
                parameters.add(loanbankList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
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

          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  /**
   * 根据PL202主键查询业务状态
   * 
   * @param flowHeadId
   * @return 返回一个Object[] 其中obj[0]是业务状态，obj[1]是否做过错帐调整业务，null没做过 1做过
   * @author 付云峰
   */
  public Object[] queryBizTypeByFlowHeadId(final String flowHeadId) {
    Object[] obj = new Object[2];
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select p2.biz_type,p2.is_adjust,p2.loan_bank_id from pl202 p2 where p2.flow_head_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(flowHeadId));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (obj[0] == null) {
      obj[0] = "";
    }
    if (obj[1] == null) {
      obj[1] = "";
    }
    if (obj[2] == null) {
      obj[2] = "";
    }
    return obj;
  }

  /**
   * 查询批量回收中FlowHeadId最大的业务
   * 
   * @param flowHeadId
   * @param loanbankList
   * @return FlowHeadId
   * @author 付云峰
   */
  public String queryMaxFlowHeadId(final Integer bankid) {
    Object obj = "";
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select max(p2.flow_head_id) "
              + "from pl202 p2 where "
              + "p2.biz_type = '5' and "
              + "(p2.is_adjust is null or p2.is_adjust='') and p2.loan_bank_id="
              + bankid.toString();

          Query query = session.createSQLQuery(sql);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj.toString();
  }

  /**
   * 返回将要调整的单笔业务的页面显示信息
   * 
   * @param flowHeadId
   * @return
   * @author 付云峰
   */
  public AdjustAccountTaInfoDTO queryAdjustAccountSingle(final String flowHeadId) {
    AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = null;
    try {
      adjustAccountTaInfoDTO = (AdjustAccountTaInfoDTO) getHibernateTemplate()
          .execute(new

          HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = "select distinct "
                  + "p2.flow_head_id,"
                  + "p2.biz_type,"
                  + "nvl(p2.real_corpus,0)*-1+nvl(p2.real_overdue_corpus,0)*-1,"
                  + "nvl(p2.real_interest,0)*-1+nvl(p2.real_overdue_interest,0)*-1,"
                  + "p2.real_punish_interest*-1," + "p0.borrower_name,"
                  + "p2.make_person," + "p3.loan_kou_acc "
                  + "from pl202 p2, pl203 p3, pl110 p0 "
                  + "where p2.flow_head_id=p3.flow_head_id and "
                  + "p3.contract_id=p0.contract_id and " + "p2.flow_head_id=?";

              Query query = session.createSQLQuery(sql);
              query.setParameter(0, new Integer(flowHeadId));
              AdjustAccountTaInfoDTO temp_adjustAccountTaInfoDTO = new AdjustAccountTaInfoDTO();
              Object[] obj = (Object[]) query.uniqueResult();
              if (obj[0] != null) {
                temp_adjustAccountTaInfoDTO.setFlowHeadId(obj[0].toString());
              }
              if (obj[1] != null) {
                temp_adjustAccountTaInfoDTO.setBizType(obj[1].toString());
              }
              if (obj[2] != null) {
                temp_adjustAccountTaInfoDTO.setCallbackMoney(new BigDecimal(
                    obj[2].toString()));
              }
              if (obj[3] != null) {
                temp_adjustAccountTaInfoDTO.setCallbackInterest(new BigDecimal(
                    obj[3].toString()));
              }
              if (obj[4] != null) {
                temp_adjustAccountTaInfoDTO.setPunishInterest(new BigDecimal(
                    obj[4].toString()));
              }
              if (obj[5] != null) {
                temp_adjustAccountTaInfoDTO.setBorrowerName(obj[5].toString());
              }
              if (obj[6] != null) {
                temp_adjustAccountTaInfoDTO.setMakePerson(obj[6].toString());
              }
              if (obj[7] != null) {
                temp_adjustAccountTaInfoDTO.setLoanKouAcc(obj[7].toString());
              }
              return temp_adjustAccountTaInfoDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return adjustAccountTaInfoDTO;
  }

  /**
   * 返回将要调整的发放业务的页面显示信息
   * 
   * @param flowHeadId
   * @return
   * @author 付云峰
   */
  public AdjustAccountTaInfoDTO queryAdjustAccountPutout(final String flowHeadId) {
    AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = null;
    try {
      adjustAccountTaInfoDTO = (AdjustAccountTaInfoDTO) getHibernateTemplate()
          .execute(new

          HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = "select " + "p2.flow_head_id," + "p2.biz_type,"
                  + "p0.borrower_name," + "p2.make_person,"
                  + "p2.occur_money*-1," + "p3.loan_kou_acc "
                  + "from pl203 p3,pl202 p2,pl110 p0 "
                  + "where p2.flow_head_id=p3.flow_head_id and "
                  + "p3.contract_id=p0.contract_id and " + "p2.flow_head_id=?";

              Query query = session.createSQLQuery(sql);
              query.setParameter(0, new Integer(flowHeadId));
              AdjustAccountTaInfoDTO temp_adjustAccountTaInfoDTO = new AdjustAccountTaInfoDTO();
              Object[] obj = (Object[]) query.uniqueResult();
              if (obj[0] != null) {
                temp_adjustAccountTaInfoDTO.setFlowHeadId(obj[0].toString());
              }
              if (obj[1] != null) {
                temp_adjustAccountTaInfoDTO.setBizType(obj[1].toString());
              }
              if (obj[2] != null) {
                temp_adjustAccountTaInfoDTO.setBorrowerName(obj[2].toString());
              }
              if (obj[3] != null) {
                temp_adjustAccountTaInfoDTO.setMakePerson(obj[3].toString());
              }
              if (obj[4] != null) {
                temp_adjustAccountTaInfoDTO.setPutOutMoney(new BigDecimal(
                    obj[4].toString()));
              }
              if (obj[5] != null) {
                temp_adjustAccountTaInfoDTO.setLoanKouAcc(obj[5].toString());
              }

              return temp_adjustAccountTaInfoDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return adjustAccountTaInfoDTO;
  }

  /**
   * 返回将要调整的批量回收业务的页面显示信息
   * 
   * @param flowHeadId
   * @return
   * @author 付云峰
   */
  public AdjustAccountTaInfoDTO queryAdjustAccountBatch(final String flowHeadId) {
    AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = null;
    try {
      adjustAccountTaInfoDTO = (AdjustAccountTaInfoDTO) getHibernateTemplate()
          .execute(new

          HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = "select "
                  + "p2.flow_head_id,"
                  + "p2.biz_type,"
                  + "nvl(p2.real_corpus,0)*-1+nvl(p2.real_overdue_corpus,0)*-1,"
                  + "nvl(p2.real_interest,0)*-1+nvl(p2.real_overdue_interest,0)*-1,"
                  + "p2.real_punish_interest*-1," + "p2.make_person "
                  + "from pl202 p2 " + "where p2.flow_head_id=?";

              Query query = session.createSQLQuery(sql);
              query.setParameter(0, new Integer(flowHeadId));
              AdjustAccountTaInfoDTO temp_adjustAccountTaInfoDTO = new AdjustAccountTaInfoDTO();
              Object[] obj = (Object[]) query.uniqueResult();

              if (obj[0] != null) {
                temp_adjustAccountTaInfoDTO.setFlowHeadId(obj[0].toString());
              } else {
                temp_adjustAccountTaInfoDTO.setFlowHeadId("");
              }
              if (obj[1] != null) {
                temp_adjustAccountTaInfoDTO.setBizType(obj[1].toString());
              } else {
                temp_adjustAccountTaInfoDTO.setBizType("");
              }
              if (obj[2] != null) {
                temp_adjustAccountTaInfoDTO.setCallbackMoney(new BigDecimal(
                    obj[2].toString()));
              } else {
                temp_adjustAccountTaInfoDTO.setCallbackMoney(new BigDecimal(
                    0.00));
              }
              if (obj[3] != null) {
                temp_adjustAccountTaInfoDTO.setCallbackInterest(new BigDecimal(
                    obj[3].toString()));
              } else {
                temp_adjustAccountTaInfoDTO.setCallbackInterest(new BigDecimal(
                    0.00));
              }
              if (obj[4] != null) {
                temp_adjustAccountTaInfoDTO.setPunishInterest(new BigDecimal(
                    obj[4].toString()));
              } else {
                temp_adjustAccountTaInfoDTO.setPunishInterest(new BigDecimal(
                    0.00));
              }
              if (obj[5] != null) {
                temp_adjustAccountTaInfoDTO.setMakePerson(obj[5].toString());
              } else {
                temp_adjustAccountTaInfoDTO.setMakePerson("");
              }
              return temp_adjustAccountTaInfoDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return adjustAccountTaInfoDTO;
  }

  /**
   * 查询被调整的业务信息，用来进行调整(批量调整)
   * 
   * @param flowHeadId
   * @return
   * @author 付云峰
   */
  public AdjustAccountTaSaveDTO queryAdjustAccountInfoBatch(
      final String flowHeadId) {
    AdjustAccountTaSaveDTO adjustAccountTaSaveDTO = null;
    try {
      adjustAccountTaSaveDTO = (AdjustAccountTaSaveDTO) getHibernateTemplate()
          .execute(new

          HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = "select " + "nvl(p02.should_count,0) as shocount,"
                  + "nvl(p02.should_corpus,0) as shocorpus,"
                  + "nvl(p02.should_interest,0) as shointerest,"
                  + "nvl(p02.should_overdue_corpus,0) as shoovercor,"
                  + "nvl(p02.should_overdue_interest,0) as shooverint,"
                  + "nvl(p02.should_punish_interest,0) as shopunint,"
                  + "nvl(p02.real_corpus,0) as realcor,"
                  + "nvl(p02.real_interest,0) as realint,"
                  + "nvl(p02.real_overdue_corpus,0) as realovercor,"
                  + "nvl(p02.real_overdue_interest,0) as realoverint,"
                  + "nvl(p02.real_punish_interest,0) as realpunint,"
                  + "nvl(p02.occur_money,0) as occurmoney,"
                  + "p02.loan_bank_id as loanbank," + "p02.hedai_org hedaiorg,"
                  + "p02.flow_head_id flowheadid," + "p02.biz_type as type,"
                  + "p02.make_person as makeper "
                  + "from pl202 p02 where p02.flow_head_id=? ";

              Query query = session.createSQLQuery(sql);
              query.setParameter(0, new Integer(flowHeadId));
              AdjustAccountTaSaveDTO temp_adjustAccountTaSaveDTO = new AdjustAccountTaSaveDTO();
              Object[] obj = (Object[]) query.uniqueResult();
              if (obj[0] != null) {
                temp_adjustAccountTaSaveDTO.setShouldCount(new BigDecimal(
                    obj[0].toString()));
              }
              if (obj[1] != null) {
                temp_adjustAccountTaSaveDTO.setShouldCorpus(new BigDecimal(
                    obj[1].toString()));
              }
              if (obj[2] != null) {
                temp_adjustAccountTaSaveDTO.setShouldInterest(new BigDecimal(
                    obj[2].toString()));
              }
              if (obj[3] != null) {
                temp_adjustAccountTaSaveDTO
                    .setShouldOverdueCorpus(new BigDecimal(obj[3].toString()));
              }
              if (obj[4] != null) {
                temp_adjustAccountTaSaveDTO
                    .setShouldOverdueInterest(new BigDecimal(obj[4].toString()));
              }
              if (obj[5] != null) {
                temp_adjustAccountTaSaveDTO
                    .setShouldPunishInterest(new BigDecimal(obj[5].toString()));
              }

              if (obj[6] != null) {
                temp_adjustAccountTaSaveDTO.setRealCorpus(new BigDecimal(obj[6]
                    .toString()));
              }
              if (obj[7] != null) {
                temp_adjustAccountTaSaveDTO.setRealInterest(new BigDecimal(
                    obj[7].toString()));
              }
              if (obj[8] != null) {
                temp_adjustAccountTaSaveDTO
                    .setRealOverdueCorpus(new BigDecimal(obj[8].toString()));
              }
              if (obj[9] != null) {
                temp_adjustAccountTaSaveDTO
                    .setRealOverdueInterest(new BigDecimal(obj[9].toString()));
              }
              if (obj[10] != null) {
                temp_adjustAccountTaSaveDTO
                    .setRealPunishInterest(new BigDecimal(obj[10].toString()));
              }
              if (obj[11] != null) {
                temp_adjustAccountTaSaveDTO.setOccurMoney(new BigDecimal(
                    obj[11].toString()));
              }
              if (obj[12] != null) {
                temp_adjustAccountTaSaveDTO.setLoanBankId(obj[12].toString());
              }
              if (obj[13] != null) {
                temp_adjustAccountTaSaveDTO.setHedaiOrg(obj[13].toString());
              }
              if (obj[14] != null) {
                temp_adjustAccountTaSaveDTO.setFlowHeadId(obj[14].toString());
              }
              if (obj[15] != null) {
                temp_adjustAccountTaSaveDTO.setBizType(obj[15].toString());
              }
              if (obj[16] != null) {
                temp_adjustAccountTaSaveDTO.setMakePerson(obj[16].toString());
              }

              return temp_adjustAccountTaSaveDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return adjustAccountTaSaveDTO;
  }

  /**
   * 单笔回收是查询出头表的信息。
   * 
   * @param flowHeadId 头表id
   * @return
   */
  public AdjustAccountTaSaveDTO queryAdjustAccountInfoSingleHead(
      final String flowHeadId) {
    AdjustAccountTaSaveDTO adjustAccountTaSaveDTO = null;
    try {
      adjustAccountTaSaveDTO = (AdjustAccountTaSaveDTO) getHibernateTemplate()
          .execute(new

          HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = "select " + "nvl(p02.should_count,0) as shocount,"
                  + "nvl(p02.should_corpus,0) as shocorpus,"
                  + "nvl(p02.should_interest,0) as shointerest,"
                  + "nvl(p02.should_overdue_corpus,0) as shoovercor,"
                  + "nvl(p02.should_overdue_interest,0) as shooverint,"
                  + "nvl(p02.should_punish_interest,0) as shopunint,"
                  + "nvl(p02.real_corpus,0) as realcor,"
                  + "nvl(p02.real_interest,0) as realint,"
                  + "nvl(p02.real_overdue_corpus,0) as realovercor,"
                  + "nvl(p02.real_overdue_interest,0) as realoverint,"
                  + "nvl(p02.real_punish_interest,0) as realpunint,"
                  + "nvl(p02.occur_money,0) as occurmoney,"
                  + "p02.loan_bank_id as loanbank," + "p02.hedai_org hedaiorg,"
                  + "p02.flow_head_id flowheadid," + "p02.biz_type as type,"
                  + "p02.make_person as makeper,"
                  + "p02.real_count as realcount " +

                  "from pl202 p02 where p02.flow_head_id=? ";

              Query query = session.createSQLQuery(sql);
              query.setParameter(0, new Integer(flowHeadId));
              AdjustAccountTaSaveDTO temp_adjustAccountTaSaveDTO = new AdjustAccountTaSaveDTO();
              Object[] obj = (Object[]) query.uniqueResult();
              if (obj[0] != null) {
                temp_adjustAccountTaSaveDTO.setShouldCount(new BigDecimal(
                    obj[0].toString()));
              }
              if (obj[1] != null) {
                temp_adjustAccountTaSaveDTO.setShouldCorpus(new BigDecimal(
                    obj[1].toString()));
              }
              if (obj[2] != null) {
                temp_adjustAccountTaSaveDTO.setShouldInterest(new BigDecimal(
                    obj[2].toString()));
              }
              if (obj[3] != null) {
                temp_adjustAccountTaSaveDTO
                    .setShouldOverdueCorpus(new BigDecimal(obj[3].toString()));
              }
              if (obj[4] != null) {
                temp_adjustAccountTaSaveDTO
                    .setShouldOverdueInterest(new BigDecimal(obj[4].toString()));
              }
              if (obj[5] != null) {
                temp_adjustAccountTaSaveDTO
                    .setShouldPunishInterest(new BigDecimal(obj[5].toString()));
              }

              if (obj[6] != null) {
                temp_adjustAccountTaSaveDTO.setRealCorpus(new BigDecimal(obj[6]
                    .toString()));
              }
              if (obj[7] != null) {
                temp_adjustAccountTaSaveDTO.setRealInterest(new BigDecimal(
                    obj[7].toString()));
              }
              if (obj[8] != null) {
                temp_adjustAccountTaSaveDTO
                    .setRealOverdueCorpus(new BigDecimal(obj[8].toString()));
              }
              if (obj[9] != null) {
                temp_adjustAccountTaSaveDTO
                    .setRealOverdueInterest(new BigDecimal(obj[9].toString()));
              }
              if (obj[10] != null) {
                temp_adjustAccountTaSaveDTO
                    .setRealPunishInterest(new BigDecimal(obj[10].toString()));
              }
              if (obj[11] != null) {
                temp_adjustAccountTaSaveDTO.setOccurMoney(new BigDecimal(
                    obj[11].toString()));
              }
              if (obj[12] != null) {
                temp_adjustAccountTaSaveDTO.setLoanBankId(obj[12].toString());
              }
              if (obj[13] != null) {
                temp_adjustAccountTaSaveDTO.setHedaiOrg(obj[13].toString());
              }
              if (obj[14] != null) {
                temp_adjustAccountTaSaveDTO.setFlowHeadId(obj[14].toString());
              }
              if (obj[15] != null) {
                temp_adjustAccountTaSaveDTO.setBizType(obj[15].toString());
              }
              if (obj[16] != null) {
                temp_adjustAccountTaSaveDTO.setMakePerson(obj[16].toString());
              }

              if (obj[17] != null) {
                temp_adjustAccountTaSaveDTO.setRealCount(new BigDecimal(obj[17]
                    .toString()));
              }

              return temp_adjustAccountTaSaveDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return adjustAccountTaSaveDTO;
  }

  /**
   * 查询被调整的业务信息，用来进行调整(单笔调整) 单笔调整时需要将PL203的信息查询出来
   * 
   * @param flowHeadId
   * @return
   * @author 付云峰
   */
  public AdjustAccountTaSaveDTO queryAdjustAccountInfoSingle(
      final String flowHeadId) {
    AdjustAccountTaSaveDTO adjustAccountTaSaveDTO = null;
    try {
      adjustAccountTaSaveDTO = (AdjustAccountTaSaveDTO) getHibernateTemplate()
          .execute(new

          HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = "select "
                  + "nvl(p02.should_count,0) as shocount,"
                  + "nvl(p02.should_corpus,0) as shocorpus,"
                  + "nvl(p02.should_interest,0) as shointerest,"
                  + "nvl(p02.should_overdue_corpus,0) as shoovercor,"
                  + "nvl(p02.should_overdue_interest,0) as shooverint,"
                  + "nvl(p02.should_punish_interest,0) as shopunint,"
                  + "nvl(p02.real_corpus,0) as realcor,"
                  + "nvl(p02.real_interest,0) as realint,"
                  + "nvl(p02.real_overdue_corpus,0) as realovercor,"
                  + "nvl(p02.real_overdue_interest,0) as realoverint,"
                  + "nvl(p02.real_punish_interest,0) as realpunint,"
                  + "nvl(p02.occur_money,0) as occurmoney,"
                  + "p02.loan_bank_id as loanbank,"
                  + "p02.hedai_org hedaiorg,"
                  + "p02.flow_head_id flowheadid,"
                  + "p02.biz_type as type,"
                  + "p02.make_person as makeper,"
                  + "p03.contract_id as contractidtail,"
                  + "p03.loan_kou_acc as loankouacctail,"
                  + "p03.year_month as yearmonthtail,"
                  + "nvl(p03.should_corpus,0) as shocortail,"
                  + "nvl(p03.should_interest,0) as shointtail,"
                  + "nvl(p03.should_punish_interest,0) as shopuninttail,"
                  + "nvl(p03.temp_punish_interest,0) as temppuninttail,"
                  + "nvl(p03.real_corpus,0) as realcortail,"
                  + "nvl(p03.real_interest,0) as realinttail,"
                  + "nvl(p03.real_punish_interest,0) as realpuninttail,"
                  + "nvl(p03.occur_money,0) as occurmoneytail,"
                  + "p03.loan_type as loantypetail, "
                  + "p02.real_count as realcount "
                  +

                  "from pl203 p03,pl202 p02 where p02.flow_head_id=p03.flow_head_id and p02.flow_head_id=? ";

              Query query = session.createSQLQuery(sql);
              query.setParameter(0, new Integer(flowHeadId));
              AdjustAccountTaSaveDTO temp_adjustAccountTaSaveDTO = new AdjustAccountTaSaveDTO();
              Object[] obj = (Object[]) query.uniqueResult();
              if (obj[0] != null) {
                temp_adjustAccountTaSaveDTO.setShouldCount(new BigDecimal(
                    obj[0].toString()));
              }
              if (obj[1] != null) {
                temp_adjustAccountTaSaveDTO.setShouldCorpus(new BigDecimal(
                    obj[1].toString()));
              }
              if (obj[2] != null) {
                temp_adjustAccountTaSaveDTO.setShouldInterest(new BigDecimal(
                    obj[2].toString()));
              }
              if (obj[3] != null) {
                temp_adjustAccountTaSaveDTO
                    .setShouldOverdueCorpus(new BigDecimal(obj[3].toString()));
              }
              if (obj[4] != null) {
                temp_adjustAccountTaSaveDTO
                    .setShouldOverdueInterest(new BigDecimal(obj[4].toString()));
              }
              if (obj[5] != null) {
                temp_adjustAccountTaSaveDTO
                    .setShouldPunishInterest(new BigDecimal(obj[5].toString()));
              }

              if (obj[6] != null) {
                temp_adjustAccountTaSaveDTO.setRealCorpus(new BigDecimal(obj[6]
                    .toString()));
              }
              if (obj[7] != null) {
                temp_adjustAccountTaSaveDTO.setRealInterest(new BigDecimal(
                    obj[7].toString()));
              }
              if (obj[8] != null) {
                temp_adjustAccountTaSaveDTO
                    .setRealOverdueCorpus(new BigDecimal(obj[8].toString()));
              }
              if (obj[9] != null) {
                temp_adjustAccountTaSaveDTO
                    .setRealOverdueInterest(new BigDecimal(obj[9].toString()));
              }
              if (obj[10] != null) {
                temp_adjustAccountTaSaveDTO
                    .setRealPunishInterest(new BigDecimal(obj[10].toString()));
              }
              if (obj[11] != null) {
                temp_adjustAccountTaSaveDTO.setOccurMoney(new BigDecimal(
                    obj[11].toString()));
              }
              if (obj[12] != null) {
                temp_adjustAccountTaSaveDTO.setLoanBankId(obj[12].toString());
              }
              if (obj[13] != null) {
                temp_adjustAccountTaSaveDTO.setHedaiOrg(obj[13].toString());
              }
              if (obj[14] != null) {
                temp_adjustAccountTaSaveDTO.setFlowHeadId(obj[14].toString());
              }
              if (obj[15] != null) {
                temp_adjustAccountTaSaveDTO.setBizType(obj[15].toString());
              }
              if (obj[16] != null) {
                temp_adjustAccountTaSaveDTO.setMakePerson(obj[16].toString());
              }
              if (obj[17] != null) {
                temp_adjustAccountTaSaveDTO.setContractId(obj[17].toString());
              }
              if (obj[18] != null) {
                temp_adjustAccountTaSaveDTO.setLoanKouAcc(obj[18].toString());
              }
              if (obj[19] != null) {
                temp_adjustAccountTaSaveDTO.setYearMonth(obj[19].toString());
              }
              if (obj[20] != null) {
                temp_adjustAccountTaSaveDTO.setShouldCorpusTail(new BigDecimal(
                    obj[20].toString()));
              }
              if (obj[21] != null) {
                temp_adjustAccountTaSaveDTO
                    .setShouldInterestTail(new BigDecimal(obj[21].toString()));
              }
              if (obj[22] != null) {
                temp_adjustAccountTaSaveDTO
                    .setShouldPunishInterestTail(new BigDecimal(obj[22]
                        .toString()));
              }
              if (obj[23] != null) {
                temp_adjustAccountTaSaveDTO
                    .setTempPunishInterest(new BigDecimal(obj[23].toString()));
              }
              if (obj[24] != null) {
                temp_adjustAccountTaSaveDTO.setRealCorpusTail(new BigDecimal(
                    obj[24].toString()));
              }
              if (obj[25] != null) {
                temp_adjustAccountTaSaveDTO.setRealInterestTail(new BigDecimal(
                    obj[25].toString()));
              }
              if (obj[26] != null) {
                temp_adjustAccountTaSaveDTO
                    .setRealPunishInterestTail(new BigDecimal(obj[26]
                        .toString()));
              }
              if (obj[27] != null) {
                temp_adjustAccountTaSaveDTO.setOccurMoneyTail(new BigDecimal(
                    obj[27].toString()));
              }
              if (obj[28] != null) {
                temp_adjustAccountTaSaveDTO.setLoanType(obj[28].toString());
              }
              if (obj[29] != null) {
                temp_adjustAccountTaSaveDTO.setRealCount(new BigDecimal(obj[29]
                    .toString()));
              }

              return temp_adjustAccountTaSaveDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return adjustAccountTaSaveDTO;
  }

  /**
   * 根据放款银行取出办事处，用来生成凭证号
   * 
   * @param bankId
   * @return
   * @author 付云峰
   */
  public String queryOfficeByBank(final String bankId) {
    String office = "";
    try {
      office = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select b05.office from bb105 b05 where b05.coll_bank_id=?";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(bankId));

          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return office;
  }

  /**
   * 错帐调整后修改is_adjust的方法
   * 
   * @param flowHeadId
   * @throws Exception
   * @author 付云峰
   */
  public void updateAdjustAccountIs_adjust(final String flowHeadId,
      final String isAdjust) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update LoanFlowHead loanFlowHead set loanFlowHead.isAdjust=? "
              + "where loanFlowHead.flowHeadId=? ";
          session.createQuery(hql).setParameter(0, isAdjust).setParameter(1,
              new Integer(flowHeadId)).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 查询错帐维护列表的方法
   * 
   * @param dto
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param loanbankList
   * @param page
   * @return
   * @throws Exception
   * @author 付云峰
   */
  public List queryAdjustAccountList(final AdjustAccountTbFindDTO dto,
      final String type, final String orderBy, final String order,
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
          String temp_bizBt = "";
          if (type == null || type.equals("")) {
            temp_bizBt = "res.bizst='4' ";
          } else {
            temp_bizBt = "res.bizst in ('4','5','6') ";
          }
          String hql = "select docnum,"
              + "loankouacc,borrowername,wrongbiztype,occurmoney,realcorpus,realinterest,realpunish,"
              + "flowheadid,bizst,loanbankid,contractid,cardnum,wrongflownum "
              + "from (select p02.doc_num as docnum,"
              + "'' as loankouacc,"
              + "'' as borrowername,"
              + "p02.wrong_biz_type as wrongbiztype,"
              + "nvl(p02.occur_money, 0) as occurmoney,"
              + "nvl(p02.real_corpus, 0) + nvl(p02.real_overdue_corpus, 0) as realcorpus,"
              + "nvl(p02.real_interest, 0) + nvl(p02.real_overdue_interest, 0) as realinterest,"
              + "nvl(p02.real_punish_interest, 0) as realpunish,"
              + "p02.flow_head_id as flowheadid,"
              + "p02.biz_st as bizst,"
              + "p02.loan_bank_id as loanbankid,"
              + "'' as contractid,"
              + "'' as cardnum,"
              + "p02.wrong_flow_num as wrongFlowNum "
              + "from pl202 p02, pl110 p10, pl203 p03 "
              + "where p02.flow_head_id = p03.flow_head_id "
              + "and p03.contract_id = p10.contract_id "
              + "and p02.wrong_biz_type='5' "
              + "and p02.biz_type='12'"
              + " union "
              + "select p02.doc_num as docnum,"
              + "p03.loan_kou_acc as loankouacc,"
              + "p10.borrower_name as borrowername,"
              + "p02.wrong_biz_type as wrongbiztype,"
              + "nvl(p02.occur_money, 0) as occurmoney,"
              + "nvl(p02.real_corpus, 0) + nvl(p02.real_overdue_corpus, 0) as realcorpus,"
              + "nvl(p02.real_interest, 0) + nvl(p02.real_overdue_interest, 0) as realinterest,"
              + "nvl(p02.real_punish_interest, 0) as realpunish,"
              + "p02.flow_head_id as flowheadid,"
              + "p02.biz_st as bizst,"
              + "p02.loan_bank_id as loanbankid,"
              + "p03.contract_id as contractid,"
              + "p10.card_num as cardnum,"
              + "p02.wrong_flow_num as wrongFlowNum "
              + "from pl202 p02, pl110 p10, pl203 p03 "
              + "where p02.flow_head_id = p03.flow_head_id "
              + "and p03.contract_id = p10.contract_id "
              + "and (p02.wrong_biz_type in ('1', '2', '6', '7') or p02.wrong_biz_type is null)"
              + "and p02.biz_type='12'" + ") res where " + temp_bizBt;

          Vector parameters = new Vector();
          String criterion = "";

          String loanKouAcc = dto.getLoanKouAcc().trim();
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += " res.loankouacc=? and ";
            parameters.add(loanKouAcc);
          }

          String contractId = dto.getContractId().trim();
          if (contractId != null && !contractId.equals("")) {
            criterion += " res.contractid=? and ";
            parameters.add(contractId);
          }

          String borrowerName = dto.getBorrowerName().trim();
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " res.borrowername=? and ";
            parameters.add(borrowerName);
          }

          String cardNum = dto.getCardNum().trim();
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " res.cardnum=? and ";
            parameters.add(cardNum);
          }

          String docNum = dto.getDocNum().trim();
          if (docNum != null && !docNum.equals("")) {
            criterion += " res.docnum=? and ";
            parameters.add(docNum);
          }

          String bizType = dto.getBizType().trim();
          if (bizType != null && !bizType.equals("")) {
            criterion += " res.wrongbiztype=? and ";
            parameters.add(bizType);
          }

          String bizSt = dto.getBizSt().trim();
          if (bizSt != null && !bizSt.equals("")) {
            criterion += " res.bizst=? and ";
            parameters.add(bizSt);
          }

          String loanBankId = dto.getLoanBankId().trim();
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " res.loanbankid=? and ";
            parameters.add(loanBankId);
          } else {
            if (loanbankList != null && loanbankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanbankList.size(); i++) {
                criterion += " res.loanbankid=? or ";
                parameters.add(loanbankList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " res.flowheadid ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          if (pageSize != 0) {
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
          }
          List queryList = query.list();
          // 删除有用
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }

          Iterator it = queryList.iterator();
          Object obj[] = null;
          AdjustAccountTbListDTO adjustAccountTbListDTO = null;
          List temp_adjustAccountTbListDTOList = new ArrayList();
          while (it.hasNext()) {
            adjustAccountTbListDTO = new AdjustAccountTbListDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              adjustAccountTbListDTO.setDocNum(obj[0].toString());
            }
            if (obj[1] != null) {
              adjustAccountTbListDTO.setLoanKouAcc(obj[1].toString());
            }
            if (obj[2] != null) {
              adjustAccountTbListDTO.setBorrowerName(obj[2].toString());
            }
            if (obj[3] != null) {
              adjustAccountTbListDTO.setBizType(obj[3].toString());
            }
            if (obj[4] != null) {
              adjustAccountTbListDTO.setOccurMoney(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[5] != null) {
              adjustAccountTbListDTO.setCallbackCorpus(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              adjustAccountTbListDTO.setCallbackInterest(new BigDecimal(obj[6]
                  .toString()));
            }
            if (obj[7] != null) {
              adjustAccountTbListDTO.setPunishInterest(new BigDecimal(obj[7]
                  .toString()));
            }
            if (obj[8] != null) {
              adjustAccountTbListDTO.setFlowHeadId(obj[8].toString());
            }
            if (obj[9] != null) {
              adjustAccountTbListDTO.setBisSt(obj[9].toString());
            }
            if (obj[11] != null) {
              adjustAccountTbListDTO.setContractId(obj[11].toString());
            }
            if (obj[13] != null) {
              adjustAccountTbListDTO.setWrongFlowNum(obj[13].toString());
            }
            BigDecimal callbackTotal = adjustAccountTbListDTO
                .getCallbackCorpus().add(
                    adjustAccountTbListDTO.getCallbackInterest()).add(
                    adjustAccountTbListDTO.getPunishInterest());
            adjustAccountTbListDTO.setCallbackTotal(callbackTotal);
            temp_adjustAccountTbListDTOList.add(adjustAccountTbListDTO);
          }

          return temp_adjustAccountTbListDTOList;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询错帐维护列表count的方法
   * 
   * @param dto
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param loanbankList
   * @param page
   * @return
   * @throws Exception
   * @author 付云峰
   */
  public List queryAdjustAccountCount(final AdjustAccountTbFindDTO dto,
      final String type, final List loanbankList) throws Exception {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String temp_bizBt = "";
          if (type == null || type.equals("")) {
            temp_bizBt = "p02.biz_st='4' ";
          } else {
            temp_bizBt = "p02.biz_st in ('4','5','6') ";
          }
          String hql = "select distinct " + "nvl(p02.occur_money,0),"
              + "nvl(p02.real_corpus,0)+nvl(p02.real_overdue_corpus,0),"
              + "nvl(p02.real_interest,0)+nvl(p02.real_overdue_interest,0),"
              + "nvl(p02.real_punish_interest,0)," + "p02.flow_head_id, "
              + "p02.biz_st," + "p02.wrong_biz_type "
              + "from pl202 p02, pl110 p10, pl203 p03 "
              + "where p02.flow_head_id = p03.flow_head_id and "
              + "p03.contract_id = p10.contract_id and p02.biz_type='12' and "
              + temp_bizBt;

          Vector parameters = new Vector();
          String criterion = "";

          String loanKouAcc = dto.getLoanKouAcc().trim();
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += " p03.loan_kou_acc=? and ";
            parameters.add(loanKouAcc);
          }

          String contractId = dto.getContractId().trim();
          if (contractId != null && !contractId.equals("")) {
            criterion += " p03.contract_id=? and ";
            parameters.add(contractId);
          }

          String borrowerName = dto.getBorrowerName().trim();
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += " p10.borrower_name=? and ";
            parameters.add(borrowerName);
          }

          String cardNum = dto.getCardNum().trim();
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " p10.card_num=? and ";
            parameters.add(cardNum);
          }

          String docNum = dto.getDocNum().trim();
          if (docNum != null && !docNum.equals("")) {
            criterion += " p02.doc_num=? and ";
            parameters.add(docNum);
          }

          String bizType = dto.getBizType().trim();
          if (bizType != null && !bizType.equals("")) {
            criterion += " p02.wrong_biz_type=? and ";
            parameters.add(bizType);
          }

          String bizSt = dto.getBizSt().trim();
          if (bizSt != null && !bizSt.equals("")) {
            criterion += " p02.biz_st=? and ";
            parameters.add(bizSt);
          }

          String loanBankId = dto.getLoanBankId().trim();
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " p02.loan_bank_id=? and ";
            parameters.add(loanBankId);
          } else {
            if (loanbankList != null && loanbankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < loanbankList.size(); i++) {
                criterion += " p02.loan_bank_id=? or ";
                parameters.add(loanbankList.get(i));
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
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

          List queryList = query.list();

          Iterator it = queryList.iterator();
          Object obj[] = null;
          AdjustAccountTbListDTO adjustAccountTbListDTO = null;
          List temp_adjustAccountTbListDTOList = new ArrayList();
          while (it.hasNext()) {
            adjustAccountTbListDTO = new AdjustAccountTbListDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              adjustAccountTbListDTO.setOccurMoney(new BigDecimal(obj[0]
                  .toString()));
            }
            if (obj[1] != null) {
              adjustAccountTbListDTO.setCallbackCorpus(new BigDecimal(obj[1]
                  .toString()));
            }
            if (obj[2] != null) {
              adjustAccountTbListDTO.setCallbackInterest(new BigDecimal(obj[2]
                  .toString()));
            }
            if (obj[3] != null) {
              adjustAccountTbListDTO.setPunishInterest(new BigDecimal(obj[3]
                  .toString()));
            }
            if (obj[4] != null) {
              adjustAccountTbListDTO.setFlowHeadId(obj[4].toString());
            }
            if (obj[6] != null) {
              adjustAccountTbListDTO.setBizType(obj[6].toString());
            }
            BigDecimal callbackTotal = adjustAccountTbListDTO
                .getCallbackCorpus().add(
                    adjustAccountTbListDTO.getCallbackInterest()).add(
                    adjustAccountTbListDTO.getPunishInterest());
            adjustAccountTbListDTO.setCallbackTotal(callbackTotal);
            temp_adjustAccountTbListDTOList.add(adjustAccountTbListDTO);
          }

          return temp_adjustAccountTbListDTOList;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询业务状态与错帐类型，用来进行删除前的判断..还有错帐流水号
   * 
   * @param flowHeadId
   * @return 业务状态与错帐类型
   * @author 付云峰
   */
  public Object[] queryBizStAndWrongBizType(final String flowHeadId) {
    Object[] obj = null;
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select p02.biz_st,p02.wrong_biz_type,p02.wrong_flow_num from pl202 p02 where p02.flow_head_id=?";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(flowHeadId));

          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return obj;
  }

  /**
   * 查询打印信息
   * 
   * @param flowHeadId
   * @return
   * @author 付云峰
   */
  public AdjustAccountTbListDTO queryAdjustAccountPrint(final String flowHeadId) {
    AdjustAccountTbListDTO adjustAccountTbListDTO = null;
    try {
      adjustAccountTbListDTO = (AdjustAccountTbListDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = "select docnum,loankouacc,borrowername,wrongbiztype,occurmoney,corpus,interest,punishinterest,flowheadid from "
                  + "(select p02.doc_num as docnum,"
                  + "p03.loan_kou_acc as loankouacc,"
                  + "p10.borrower_name as borrowername,"
                  + "p02.wrong_biz_type as wrongbiztype,"
                  + "nvl(p02.occur_money, 0) as occurmoney,"
                  + "nvl(p02.real_corpus, 0) + nvl(p02.real_overdue_corpus, 0) as corpus,"
                  + "nvl(p02.real_interest, 0) + nvl(p02.real_overdue_interest, 0) as interest,"
                  + "nvl(p02.real_punish_interest, 0) as punishinterest,"
                  + "p02.flow_head_id as flowheadid "
                  + "from pl202 p02, pl203 p03, pl110 p10 "
                  + "where p02.flow_head_id = p03.flow_head_id "
                  + "and p03.contract_id = p10.contract_id "
                  + "and p02.wrong_biz_type in ('1','2','6','7')"
                  + " union "
                  + "select p02.doc_num as docnum,"
                  + "'' as loankouacc,"
                  + "'' as borrowername,"
                  + "p02.wrong_biz_type as wrongbiztype,"
                  + "nvl(p02.occur_money, 0) as occurmoney,"
                  + "nvl(p02.real_corpus, 0) + nvl(p02.real_overdue_corpus, 0) as corpus,"
                  + "nvl(p02.real_interest, 0) + nvl(p02.real_overdue_interest, 0) as interest,"
                  + "nvl(p02.real_punish_interest, 0) as punishinterest,"
                  + "p02.flow_head_id as flowheadid "
                  + "from pl202 p02, pl203 p03, pl110 p10 "
                  + "where p02.flow_head_id = p03.flow_head_id "
                  + "and p03.contract_id = p10.contract_id "
                  + "and p02.wrong_biz_type='5'"
                  + ") res where res.flowheadid = ?";

              Query query = session.createSQLQuery(sql);
              query.setParameter(0, new Integer(flowHeadId));
              AdjustAccountTbListDTO temp_adjustAccountTbListDTO = new AdjustAccountTbListDTO();
              Object[] obj = (Object[]) query.uniqueResult();

              if (obj[0] != null) {
                temp_adjustAccountTbListDTO.setDocNum(obj[0].toString());
              }
              if (obj[1] != null) {
                temp_adjustAccountTbListDTO.setLoanKouAcc(obj[1].toString());
              }
              if (obj[2] != null) {
                temp_adjustAccountTbListDTO.setBorrowerName(obj[2].toString());
              }
              if (obj[3] != null) {
                temp_adjustAccountTbListDTO.setBizType(obj[3].toString());
              }
              if (obj[4] != null) {
                temp_adjustAccountTbListDTO.setOccurMoney(new BigDecimal(obj[4]
                    .toString()));
              }
              if (obj[5] != null) {
                temp_adjustAccountTbListDTO.setCallbackCorpus(new BigDecimal(
                    obj[5].toString()));
              }
              if (obj[6] != null) {
                temp_adjustAccountTbListDTO.setCallbackInterest(new BigDecimal(
                    obj[6].toString()));
              }
              if (obj[7] != null) {
                temp_adjustAccountTbListDTO.setPunishInterest(new BigDecimal(
                    obj[7].toString()));
              }

              return temp_adjustAccountTbListDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return adjustAccountTbListDTO;
  }

  /**
   * 查询定期利息
   * 
   * @param bankId
   * @return
   * @author yuqf 2007-10-15
   */
  public String queryParamexplainYU(final String bankId) {
    String paramexplain = "";
    try {
      paramexplain = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = "select distinct pl003.param_explain from pl003 pl003 where  pl003.param_type='A' and pl003.param_num=8 and pl003.param_value=2";
              if (bankId != null && !"".equals(bankId)) {
                sql = sql + " and pl003.loan_bank_id= " + bankId;
              }
              Query query = session.createSQLQuery(sql);

              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramexplain;
  }

  /**
   * 查询活期利息
   * 
   * @param bankId
   * @return
   * @author yuqf 2007-10-15
   */
  public String queryParamexplainYU_(final String loanKouAcc) {
    String paramexplain = "";
    try {
      paramexplain = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = "select distinct pl003.param_explain from pl003 pl003,pl111 p111 where pl003.loan_bank_id=p111.loan_bank_id and pl003.param_type='A' and pl003.param_num=8 and pl003.param_value=1";
              if (loanKouAcc != null && !"".equals(loanKouAcc)) {
                sql = sql + " and p111.loan_kou_acc='" + loanKouAcc + "'";
              }
              Query query = session.createSQLQuery(sql);

              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramexplain;
  }

  /**
   * yuqf 2007-10-15 根据合同编号查询PL202下是否有未记账业务
   * 
   * @param contractId
   * @return
   */
  public List queryBizTypeByContractIdYU(final String contractId) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select t.flow_head_id from pl202 t,pl203 t1 where t.flow_head_id=t1.flow_head_id and t.biz_type='14' and t.biz_st !='6' and t1.contract_id=?";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, contractId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * yuqf 2007-10-15 根据合同编号查询PL111放款银行，用于生成凭证号
   * 
   * @param contractId
   * @return
   */
  public String queryBankByContractIdYU(final String contractId) {
    String bankid = "";
    try {
      bankid = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " select t.loan_bank_id from pl111 t where t.contract_id=?";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, contractId);
          if (query.uniqueResult() != null) {
            return query.uniqueResult().toString();
          } else {
            return null;
          }
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bankid;
  }

  /**
   * 根据头表id查询发放信息
   * 
   * @author 吴迪 2007-10-11
   * @param flowHeadId
   * @param securityInfo
   * @return
   */
  public List queryLoanaccorById(final String flowHeadId,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select pl203.contract_id  as contractID,"
              + "pl110.borrower_name    as borrowerName,"
              + "pl110.card_kind        as cardKind,"
              + "pl110.card_num         as cardNum,"
              + "pl111.loan_bank_id     as loanBankId,"
              + "pl111.loan_money       as loanMoney,"
              + "pl111.loan_time_limit  as loanTimeLimit,"
              + "pl115.loan_month_rate  as loanMonthRate,"
              + "pl115.loan_mode        as loanMode,"
              + "pl115.corpus_interest  as corpusInterest,"
              + "pl111.loan_kou_acc     as loanKouAcc,"
              + "pl111.loan_start_date  as loanStartDate,"
              + "pl111.loan_repay_day   as loanRepayDay,"
              + "pl115.first_loan_money as firstLoanMoney"
              + " from PL202 pl202, PL203 pl203, PL110 pl110, PL111 pl111, PL115 pl115"
              + " where pl202.flow_head_id = pl203.flow_head_id"
              + " and pl203.contract_id = pl110.contract_id"
              + " and pl111.contract_id = pl203.contract_id"
              + " and pl115.contract_id = pl203.contract_id"
              + " and pl202.flow_head_id = ? " + " and pl111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setString(0, flowHeadId);
          List queryList = query.list();
          List t = new ArrayList();
          Iterator it = queryList.iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();

            LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
            if (obj[0] != null && !obj[0].equals(""))
              loanaccordDTO.setContractId(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              loanaccordDTO.setBorrowerName(obj[1].toString());
            if (obj[2] != null && !obj[2].equals(""))
              loanaccordDTO.setCardKind(obj[2].toString());
            if (obj[3] != null && !obj[3].equals(""))
              loanaccordDTO.setCardNum(obj[3].toString());
            if (obj[4] != null && !obj[4].equals(""))
              loanaccordDTO.setLoanBankId(obj[4].toString());
            if (obj[5] != null && !obj[5].equals(""))
              loanaccordDTO.setLoanMoney(new BigDecimal(obj[5].toString()));
            if (obj[6] != null && !obj[6].equals(""))
              loanaccordDTO.setLoanTimeLimit(obj[6].toString());
            if (obj[7] != null && !obj[7].equals(""))
              loanaccordDTO.setLoanMonthRate(new BigDecimal(obj[7].toString()));
            if (obj[8] != null && !obj[8].equals(""))
              loanaccordDTO.setLoanMode(obj[8].toString());
            if (obj[9] != null && !obj[9].equals(""))
              loanaccordDTO
                  .setCorpusInterest(new BigDecimal(obj[9].toString()));
            if (obj[10] != null && !obj[10].equals(""))
              loanaccordDTO.setLoanKouAcc(obj[10].toString());
            if (obj[11] != null && !obj[11].equals(""))
              loanaccordDTO.setLoanStartDate(obj[11].toString());
            if (obj[12] != null && !obj[12].equals(""))
              loanaccordDTO.setLoanRepayDay(obj[12].toString());
            if (obj[13] != null && !obj[13].equals(""))
              loanaccordDTO
                  .setFirstLoanMoney(new BigDecimal(obj[13].toString()));
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

  public List queryLoanaccorById_wsh(final String flowHeadId,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select pl203.contract_id  as contractID,"
              + "pl110.borrower_name    as borrowerName,"
              + "pl110.card_kind        as cardKind,"
              + "pl110.card_num         as cardNum,"
              + "pl111.loan_bank_id     as loanBankId,"
              + "pl111.loan_money       as loanMoney,"
              + "pl111.loan_time_limit  as loanTimeLimit,"
              + "pl115.loan_month_rate  as loanMonthRate,"
              + "pl115.loan_mode        as loanMode,"
              + "pl115.corpus_interest  as corpusInterest,"
              + "pl111.loan_kou_acc     as loanKouAcc,"
              + "pl111.loan_start_date  as loanStartDate,"
              + "pl111.loan_repay_day   as loanRepayDay,"
              + "pl115.first_loan_money as firstLoanMoney"
              + " from PL202 pl202, PL203 pl203, PL110 pl110, PL111 pl111, PL115 pl115"
              + " where pl202.flow_head_id = pl203.flow_head_id"
              + " and pl203.contract_id = pl110.contract_id"
              + " and pl111.contract_id = pl203.contract_id"
              + " and pl115.contract_id = pl203.contract_id"
              + " and pl202.flow_head_id = ? " + " and pl111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setString(0, flowHeadId);
          List queryList = query.list();
          List t = new ArrayList();
          Iterator it = queryList.iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();

            LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
            if (obj[0] != null && !obj[0].equals(""))
              loanaccordDTO.setContractId(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              loanaccordDTO.setBorrowerName(obj[1].toString());
            if (obj[2] != null && !obj[2].equals(""))
              loanaccordDTO.setCardKind(obj[2].toString());
            if (obj[3] != null && !obj[3].equals(""))
              loanaccordDTO.setCardNum(obj[3].toString());
            if (obj[4] != null && !obj[4].equals(""))
              loanaccordDTO.setLoanBankId(obj[4].toString());
            if (obj[5] != null && !obj[5].equals(""))
              loanaccordDTO.setLoanMoney(new BigDecimal(obj[5].toString()));
            if (obj[6] != null && !obj[6].equals(""))
              loanaccordDTO.setLoanTimeLimit(obj[6].toString());
            if (obj[7] != null && !obj[7].equals(""))
              loanaccordDTO.setLoanMonthRate(new BigDecimal(obj[7].toString()));
            if (obj[8] != null && !obj[8].equals(""))
              loanaccordDTO.setLoanMode(obj[8].toString());
            if (obj[9] != null && !obj[9].equals(""))
              loanaccordDTO
                  .setCorpusInterest(new BigDecimal(obj[9].toString()));
            if (obj[10] != null && !obj[10].equals(""))
              loanaccordDTO.setLoanKouAcc(obj[10].toString());
            if (obj[11] != null && !obj[11].equals(""))
              loanaccordDTO.setLoanStartDate(obj[11].toString());
            if (obj[12] != null && !obj[12].equals(""))
              loanaccordDTO.setLoanRepayDay(obj[12].toString());
            if (obj[13] != null && !obj[13].equals(""))
              loanaccordDTO
                  .setFirstLoanMoney(new BigDecimal(obj[13].toString()));
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
   * 根据头表id查询挂帐信息
   * 
   * @author 吴迪 2007-10-12
   * @param flowHeadId
   * @param securityInfo
   * @return
   */
  public List queryOverPayById(final String flowHeadId,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select pl203.loan_kou_acc        as loanKouAccm,"
              + "pl203.contract_id         as contractId,"
              + "pl110.borrower_name       as borrowerName,"
              + "pl110.card_kind           as cardKind,"
              + "pl110.card_num            as cardNum,"
              + "pl111.overplus_loan_money as overplusLoanMoney,"
              + "Pl111.Overplus_Limite     as overplusLimite,"
              + "pl115.loan_mode           as loanMode,"
              + "pl111.ovaer_loan_repay    as ovaerLoanRepay,"
              + "pl202.occur_money         as occurMoney,"
              + "pl202.clear_acc_person    as clearAccPerson,"
              + "pl202.check_person        as checkPerson ,"
              + "pl202.DOC_NUM             as dnum,"
              + "pl202.NOTE_NUM            as cnnum "
              + " from PL202 pl202, PL203 pl203, PL110 pl110, PL111 pl111, PL115 pl115"
              + " where pl202.flow_head_id = pl203.flow_head_id"
              + " and pl203.contract_id = pl110.contract_id"
              + " and pl111.contract_id = pl203.contract_id"
              + " and pl115.contract_id = pl203.contract_id"
              + " and pl202.flow_head_id = ? " + " and pl111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setString(0, flowHeadId);
          List queryList = query.list();
          List t = new ArrayList();
          Iterator it = queryList.iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            OverPayDTO overPayDTO = new OverPayDTO();
            if (obj[0] != null && !obj[0].equals(""))
              overPayDTO.setLoankouacc(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              overPayDTO.setContractId(obj[1].toString());
            if (obj[2] != null && !obj[2].equals(""))
              overPayDTO.setBorrowerName(obj[2].toString());
            if (obj[3] != null && !obj[3].equals(""))
              overPayDTO.setCardKind(obj[3].toString());
            if (obj[4] != null && !obj[4].equals(""))
              overPayDTO.setCardNum(obj[4].toString());
            if (obj[5] != null && !obj[5].equals(""))
              overPayDTO
                  .setOverplusLoanMoney(new BigDecimal(obj[5].toString()));
            if (obj[6] != null && !obj[6].equals(""))
              overPayDTO.setOverplusLimite(obj[6].toString());
            if (obj[7] != null && !obj[7].equals(""))
              overPayDTO.setLoanMode(obj[7].toString());
            if (obj[8] != null && !obj[8].equals(""))
              overPayDTO.setOvaerLoanRepay(new BigDecimal(obj[8].toString()));
            if (obj[9] != null && !obj[9].equals(""))
              overPayDTO.setOverpayMoney(new BigDecimal(obj[9].toString()));
            if (obj[10] != null && !obj[10].equals(""))
              overPayDTO.setClearAccPerson(obj[10].toString());
            if (obj[11] != null && !obj[11].equals(""))
              overPayDTO.setCheckPerson(obj[11].toString());
            if (obj[12] != null && !obj[12].equals("")) {
              overPayDTO.setDocNum(obj[12].toString());
            } else {
              overPayDTO.setDocNum("");
            }

            if (obj[13] != null && !obj[13].equals("")) {
              overPayDTO.setNoteNum(obj[13].toString());
            } else {
              overPayDTO.setNoteNum("");
            }
            t.add(overPayDTO);
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
   * 根据头表id查询保证金收提信息
   * 
   * @author 吴迪 2007-10-12
   * @param flowHeadId
   * @param securityInfo
   * @return
   */
  public List queryBailById(final String flowHeadId,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select pl203.loan_kou_acc        as loanKouAccm,"
              + "pl203.contract_id         as contractId,"
              + "pl110.borrower_name       as borrowerName,"
              + "pl202.occur_money         as occurMoney,"
              + "pl202.other_interest      as otherInterest,"
              + "pl111.overplus_loan_money as overplusLoanMoney,"
              + "pl111.no_back_money       as noBackMoney,"
              + "pl111.ovaer_loan_repay    as ovaerLoanRepay"
              + " from PL202 pl202, PL203 pl203, PL110 pl110, PL111 pl111"
              + " where pl202.flow_head_id = pl203.flow_head_id"
              + " and pl203.contract_id = pl110.contract_id"
              + " and pl111.contract_id = pl203.contract_id"
              + " and pl202.flow_head_id = ? " + " and pl111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setString(0, flowHeadId);
          List queryList = query.list();
          List t = new ArrayList();
          Iterator it = queryList.iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BailDTO bailDTO = new BailDTO();
            if (obj[0] != null && !obj[0].equals(""))
              bailDTO.setLoankouacc(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              bailDTO.setContractId(obj[1].toString());
            if (obj[2] != null && !obj[2].equals(""))
              bailDTO.setBorrowerName(obj[2].toString());
            if (obj[3] != null && !obj[3].equals(""))
              bailDTO.setBailMoney(new BigDecimal(obj[3].toString()));
            if (obj[4] != null && !obj[4].equals(""))
              bailDTO.setAccrual(new BigDecimal(obj[4].toString()));
            if (obj[5] != null && !obj[5].equals(""))
              bailDTO.setOverpusLoanMoney(new BigDecimal(obj[5].toString()));
            if (obj[6] != null && !obj[6].equals(""))
              bailDTO.setNoBackMoney(new BigDecimal(obj[6].toString()));
            if (obj[7] != null && !obj[7].equals(""))
              bailDTO.setOvaerLoanRepay(new BigDecimal(obj[7].toString()));
            t.add(bailDTO);
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
   * 根据头表id查询错帐调整信息
   * 
   * @author 吴迪 2007-10-13
   * @param flowHeadId
   * @param securityInfo
   * @return
   */
  public List queryAdjustAccountById(final String flowHeadId,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select docnum,"
              + "loankouacc,"
              + "bizst,"
              + "occurmoney,"
              + "realcorpus,"
              + "realinterest,"
              + "realpunish,"
              + "borrowername,"
              + "flowheadid,"
              + "loanBankId"
              + " from (select (select p.doc_num"
              + " from pl202 p"
              + " where p.flow_head_id = p02.wrong_flow_num) as docnum,"
              + "'' as loankouacc,"
              + "p02.wrong_biz_type as bizst,"
              + "0 as occurmoney,"
              + "nvl(p02.real_corpus, 0) + nvl(p02.real_overdue_corpus, 0) as realcorpus,"
              + "nvl(p02.real_interest, 0) + nvl(p02.real_overdue_interest, 0) as realinterest,"
              + "nvl(p02.real_punish_interest, 0) as realpunish,"
              + "'' as borrowername,"
              + "p02.flow_head_id as flowheadid,"
              + "p02.loan_bank_id as loanBankId"
              + " from pl202 p02"
              + " where p02.wrong_biz_type = '5'"
              + " union"
              + " select (select p.doc_num"
              + " from pl202 p"
              + " where p.flow_head_id = p02.wrong_flow_num) as docnum,"
              + "p03.loan_kou_acc as loankouacc,"
              + "p02.wrong_biz_type as bizst,"
              + "nvl(p02.occur_money, 0) as occurmoney,"
              + "nvl(p02.real_corpus, 0) + nvl(p02.real_overdue_corpus, 0) as realcorpus,"
              + "nvl(p02.real_interest, 0) + nvl(p02.real_overdue_interest, 0) as realinterest,"
              + "nvl(p02.real_punish_interest, 0) as realpunish,"
              + "p10.borrower_name as borrowername,"
              + "p02.flow_head_id as flowheadid,"
              + "p02.loan_bank_id as loanBankId"
              + " from pl202 p02, pl110 p10, pl203 p03"
              + " where p02.flow_head_id = p03.flow_head_id"
              + " and p03.contract_id = p10.contract_id"
              + " and (p02.wrong_biz_type in ('1', '2', '6', '7') or"
              + " p02.wrong_biz_type is null))  res"
              + " where res.flowheadid =?" + " and res.loanBankId "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setString(0, flowHeadId);
          List queryList = query.list();
          List t = new ArrayList();
          Iterator it = queryList.iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            AdjustAccountDTO adjustAccountDTO = new AdjustAccountDTO();
            if (obj[0] != null && !obj[0].equals("")) {
              if (obj[2] != null && !obj[2].equals("")) {
                if (obj[2].equals("5") || obj[2].equals("1")
                    || obj[2].equals("2") || obj[2].equals("6")
                    || obj[2].equals("7"))
                  adjustAccountDTO.setDocNum(obj[0].toString());
              }
            }
            if (obj[1] != null && !obj[1].equals("")) {
              if (obj[2] == null || obj[2].equals(""))
                adjustAccountDTO.setLoanKouAcc(obj[1].toString());

            }
            if (obj[2] != null && !obj[2].equals("")) {
              if (obj[2].equals("5") || obj[2].equals("1")
                  || obj[2].equals("2") || obj[2].equals("6")
                  || obj[2].equals("7"))
                adjustAccountDTO.setBizType(obj[2].toString());

            }
            if (obj[3] != null && !obj[3].equals("")) {
              if (obj[2] != null && !obj[2].equals("") && obj[2].equals("1"))

                adjustAccountDTO
                    .setOccurMoney(new BigDecimal(obj[3].toString()));
              else
                adjustAccountDTO.setOccurMoney(null);
            } else
              adjustAccountDTO.setOccurMoney(null);
            if (obj[4] != null && !obj[4].equals("")) {
              adjustAccountDTO.setReclaimCorpus(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[5] != null && !obj[5].equals("")) {
              adjustAccountDTO.setReclaimAccrual(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null && !obj[6].equals("")) {
              adjustAccountDTO.setRealPunishInterest(new BigDecimal(obj[6]
                  .toString()));
            }
            if (obj[7] != null && !obj[7].equals("")) {
              adjustAccountDTO.setBorrowerName(obj[7].toString());
            }
            t.add(adjustAccountDTO);
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
   * yuqf 2007-10-17 根据头表ID查询业务状态
   * 
   * @param id
   * @param securityInfo
   * @return
   */
  public String queryBizStById(final String id, final SecurityInfo securityInfo) {
    String bizSt = "";

    try {
      bizSt = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " select t.biz_st from pl202 t where t.flow_head_id=? and t.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setString(0, id);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bizSt;
  }

  /**
   * yuqf 2007-10-30 查询头表凭证号
   * 
   * @param id
   * @param securityInfo
   * @return
   */
  public String queryDocNumById(final String id, final SecurityInfo securityInfo) {
    String bizSt = "";

    try {
      bizSt = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " select t.doc_num from pl202 t where t.flow_head_id=? and t.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setString(0, id);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bizSt;
  }

  /**
   * Description 贷款业务流水统计查询列表
   * 
   * @author wangy 2007-10-16
   * @param 根据条件查询列表
   * @param 由LoanBusiFlowQueryBS调用
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @param securityInfo
   * @param docNum
   * @param contractId
   * @param loanKouAcc
   * @param borrowerName
   * @param makePerson
   * @param bizType
   * @param bizSt
   * @param loanBankName
   * @param beginBizDate
   * @param endBizDate
   * @param noteNum
   * @return List
   */
  public List queryLoanBusiFlowQueryListByCriterions(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String noteNum, final String isGjjLoanback) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select docnum,loankouacc, contractId,borrowername,bizType,occurMoney,realcorpus,"
              + "reclaimAccrual,realPunishInterest,badDebt,putUpMoney,bail,bailAccrual,bizSt,"
              + "bizDate,wrongBizType,loanBankID, makePerson,flowheadID,notenum,batch_num from "
              + "("
              + " select pl202.doc_num as docnum,"
              + "'' as loankouacc, "
              + "'' as contractId,"
              + "'' as borrowername,"
              + " pl202.biz_type as bizType,"
              + " 0 as occurMoney,"
              + " nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + " nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + " nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + " 0 as badDebt,"
              + " nvl(pl202.occur_money, 0) as putUpMoney,"
              + " nvl(pl202.occur_money,0) as bail,"
              + " nvl(pl202.other_interest,0) as bailAccrual,"
              + " pl202.biz_st as bizSt,"
              + " pl202.biz_date as bizDate,"
              + " pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum,"
              + " pl202.batch_num as batch_num"
              + " from PL202 pl202"
              + " where pl202.biz_type = '5' or (pl202.biz_type = '12' and pl202.wrong_biz_type = '5') or pl202.biz_type = '15'"
              + " union"
              + " select pl202.doc_num as docnum,"
              + " pl203.loan_kou_acc as loankouacc,"
              + " pl203.contract_id as contractId,"
              + " pl110.borrower_name  as borrowername,"
              + " pl202.biz_type as bizType,"
              + " nvl(pl202.occur_money,0) as occurMoney,"
              + " nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + " nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + " nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + " nvl(pl202.occur_money,0) as badDebt,"
              + " nvl(pl202.occur_money,0) as putUpMoney,"
              + " nvl(pl202.occur_money,0) as bail,"
              + " nvl(pl202.other_interest,0) as bailAccrual,"
              + " pl202.biz_st as bizSt,"
              + " pl202.biz_date as bizDate,"
              + " pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum,"
              + " pl202.batch_num as batch_num"
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
          ob = " bizDate,docnum ";
          String od = order;
          if (od == null)
            od = " ASC";

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

          if ("0".equals(isGjjLoanback)) {
            criterion += " batch_num is not null and ";
          } else if ("1".equals(isGjjLoanback)) {
            criterion += " (batch_num is null or batch_num = '') and ";
          }

          if (criterion.length() != 0)
            criterion = " where " + " loanBankID "
                + securityInfo.getDkSecuritySQL() + " and  "
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

  public List queryLoanXieYiListByCriterions_wsh(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String office,
      final String loanbank, final String contractId,
      final String borrowerName, final String beginBizDate,
      final String endBizDate, final String beginDelDate,
      final String endDelDate, final String isDelete) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  distinct p110.contract_id   contractid, "
              + " p400.reservea_c beixuannum,"
              + " p110.borrower_name borrowername,"
              + " p110.card_num      cardnum," + " p110.emp_id        empid,"
              + " p113.name          name," + " p113.card_num      bcardnun,"
              + " p113.emp_id        bempid," + " p400.reservea_a    ra,"
              + " p400.date_stop     ds"
              + " from pl110 p110, pl111 p111, pl400 p400, pl113 p113"
              + " where p110.contract_id = p111.contract_id "
              + " and p111.contract_id = p400.contract_id "
              + " and p110.contract_id = p113.contract_id(+) ";
          String criterion = "";
          Vector parameters = new Vector();
          String ob = orderBy;
          if (ob == null)
            ob = " p110.contract_id ";

          String od = order;
          if (od == null)
            od = " DESC";

          if (office != null && !"".equals(office)) {
            criterion += " p110.office = ? and ";
            parameters.add(office);
          }

          if (loanbank != null && !"".equals(loanbank)) {
            criterion += " p111.loan_bank_id  = ? and ";
            parameters.add(loanbank);
          } else {
            criterion += " p111.loan_bank_id "
                + securityInfo.getDkSecuritySQL() + " and  ";
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }

          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " p400.reservea_a >= ? and ";
            parameters.add(beginBizDate);
          }

          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " p400.reservea_a <= ? and ";
            parameters.add(endBizDate);
          }
          if (beginDelDate != null && !"".equals(beginDelDate)) {
            criterion += " p400.date_stop >= ? and ";
            parameters.add(beginDelDate);
          }

          if (endDelDate != null && !"".equals(endDelDate)) {
            criterion += " p400.date_stop <= ? and ";
            parameters.add(endDelDate);
          }

          if (isDelete != null && !"".equals(isDelete) && "1".equals(isDelete)) {
            criterion += " p400.date_stop is not null and ";
          }
          if (isDelete != null && !"".equals(isDelete) && "0".equals(isDelete)) {
            criterion += " p400.date_stop is  null and ";
          }

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          }

          hql = hql + " and " + criterion + " order by " + ob + " " + od;

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

  public List queryLoanXieYiAllListByCriterions_wsh(
      final SecurityInfo securityInfo, final String office,
      final String loanbank, final String contractId,
      final String borrowerName, final String beginBizDate,
      final String endBizDate, final String beginDelDate,
      final String endDelDate, final String isDelete) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  distinct p110.contract_id   contractid, "
              + " p400.reservea_c beixuannum,"
              + " p110.borrower_name borrowername,"
              + " p110.card_num      cardnum," + " p110.emp_id        empid,"
              + " p113.name          name," + " p113.card_num      bcardnun,"
              + " p113.emp_id        bempid," + " p400.reservea_a    ra,"
              + " p400.date_stop     ds"
              + " from pl110 p110, pl111 p111, pl400 p400, pl113 p113"
              + " where p110.contract_id = p111.contract_id "
              + " and p111.contract_id = p400.contract_id "
              + " and p110.contract_id = p113.contract_id(+) ";
          String criterion = "";
          Vector parameters = new Vector();

          if (office != null && !"".equals(office)) {
            criterion += " p110.office = ? and ";
            parameters.add(office);
          }

          if (loanbank != null && !"".equals(loanbank)) {
            criterion += " p111.loan_bank_id  = ? and ";
            parameters.add(loanbank);
          } else {
            criterion += " p111.loan_bank_id "
                + securityInfo.getDkSecuritySQL() + " and  ";
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }

          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " p400.reservea_a >= ? and ";
            parameters.add(beginBizDate);
          }

          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " p400.reservea_a <= ? and ";
            parameters.add(endBizDate);
          }
          if (beginDelDate != null && !"".equals(beginDelDate)) {
            criterion += " p400.date_stop >= ? and ";
            parameters.add(beginDelDate);
          }

          if (endDelDate != null && !"".equals(endDelDate)) {
            criterion += " p400.date_stop <= ? and ";
            parameters.add(endDelDate);
          }

          if (isDelete != null && !"".equals(isDelete) && "1".equals(isDelete)) {
            criterion += " p400.date_stop is not null and ";
          }
          if (isDelete != null && !"".equals(isDelete) && "0".equals(isDelete)) {
            criterion += " p400.date_stop is  null and ";
          }

          if (criterion.length() != 0)

            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + " and  " + criterion;

          Query query = session.createSQLQuery(hql);
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

  public String queryLoanXieYiAllListByCriterions_count_wsh(
      final SecurityInfo securityInfo, final String office,
      final String loanbank, final String contractId,
      final String borrowerName, final String beginBizDate,
      final String endBizDate, final String beginDelDate,
      final String endDelDate, final String isDelete) {
    String count = "0";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct p110.contract_id) "
              + " from pl110 p110, pl111 p111, pl400 p400, pl113 p113"
              + " where p110.contract_id = p111.contract_id "
              + " and p111.contract_id = p400.contract_id "
              + " and p110.contract_id = p113.contract_id(+) ";
          String criterion = "";
          Vector parameters = new Vector();

          if (office != null && !"".equals(office)) {
            criterion += " p110.office = ? and ";
            parameters.add(office);
          }

          if (loanbank != null && !"".equals(loanbank)) {
            criterion += " p111.loan_bank_id  = ? and ";
            parameters.add(loanbank);
          } else {
            criterion += " p111.loan_bank_id "
                + securityInfo.getDkSecuritySQL() + " and  ";
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }

          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " p400.reservea_a >= ? and ";
            parameters.add(beginBizDate);
          }

          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " p400.reservea_a <= ? and ";
            parameters.add(endBizDate);
          }
          if (beginDelDate != null && !"".equals(beginDelDate)) {
            criterion += " p400.date_stop >= ? and ";
            parameters.add(beginDelDate);
          }

          if (endDelDate != null && !"".equals(endDelDate)) {
            criterion += " p400.date_stop <= ? and ";
            parameters.add(endDelDate);
          }

          if (isDelete != null && !"".equals(isDelete) && "1".equals(isDelete)) {
            criterion += " p400.date_stop is not null and ";
          }
          if (isDelete != null && !"".equals(isDelete) && "0".equals(isDelete)) {
            criterion += " p400.date_stop is  null and ";
          }

          if (criterion.length() != 0)

            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + " and  " + criterion;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return count;
  }

  public List queryLoanBackListByCriterions_wsh(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String office,
      final String loanbank, final String contractId,
      final String borrowerName, final String cardNum, final String docNum,
      final String beginBizDate, final String endBizDate, final String orgId,
      final String orgName, final String empId, final String noteNum) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a001.id as orgid, b001.name as orgname,"
              + " p110.contract_id, p110.borrower_name,"
              + " p203.year_month, p203.real_corpus,"
              + " p203.real_interest, p203.real_punish_interest,p203.real_corpus+p203.real_interest+p203.real_punish_interest,"
              + " max(p400.emp_id), p400.emp_name, p202.biz_date,"
              + " p202.batch_num from pl202 p202, pl203 p203,"
              + " pl110 p110, pl111 p111, pl400 p400,"
              + " aa410 a410, aa411 a411, aa001 a001,"
              + " ba001 b001 where p202.flow_head_id = p203.flow_head_id"
              + " and p203.contract_id = p110.contract_id"
              + " and p203.contract_id = p111.contract_id"
              + " and p203.contract_id = p400.contract_id"
              + " and p202.batch_num = a410.batch_num"
              + " and a410.id = a411.head_id"
              + " and a411.emp_id = p400.emp_id"
              + " and a411.org_id = p400.org_id and a411.org_id = a001.id"
              + " and a411.collflag = 1 and a001.orginfo_id = b001.id"
              + " and p203.year_month=a411.year_month and p202.biz_st=6 ";
          String criterion = "";
          Vector parameters = new Vector();

          if (office != null && !"".equals(office)) {
            criterion += " and p110.office = ? ";
            parameters.add(office);
          }
          if (loanbank != null && !"".equals(loanbank)) {
            criterion += " and p111.loan_bank_id = ? ";
            parameters.add(loanbank);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " and p110.contract_id = ? ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " and p110.borrower_name =? ";
            parameters.add(borrowerName);
          }
          if (orgId != null && !"".equals(orgId)) {
            criterion += " and a001.id = ? ";
            parameters.add(new Integer(orgId));
          }
          if (orgName != null && !"".equals(orgName)) {
            criterion += " and b001.name like ? ";
            parameters.add("%" + orgName + "%");
          }
          if (empId != null && !"".equals(empId)) {
            criterion += " and a411.emp_id =? ";
            parameters.add(new Integer(empId));
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " and b001.card_num = ? ";
            parameters.add(cardNum);
          }
          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " and a410.biz_date >= ? ";
            parameters.add(beginBizDate);
          }
          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " and a410.biz_date <= ? ";
            parameters.add(endBizDate);
          }
          if (noteNum != null && !"".equals(noteNum)) {
            criterion += " and p202.batch_num = ? ";
            parameters.add(noteNum);
          }
          sql = sql + criterion
              + " group by a001.id,b001.name,p110.contract_id,p110.borrower_name,p203.year_month,p203.real_corpus,p203.real_interest,p203.real_punish_interest,p203.real_corpus + p203.real_interest + p203.real_punish_interest,p400.emp_name,p202.biz_date,p202.batch_num order by a001.id, p110.contract_id, p203.year_month";
          Query query = session.createSQLQuery(sql);
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

  public List queryLoanBackAllListByCriterions_wsh(
      final SecurityInfo securityInfo, final String office,
      final String loanbank, final String contractId,
      final String borrowerName, final String cardNum, final String docNum,
      final String beginBizDate, final String endBizDate, final String orgId,
      final String orgName, final String empId, final String noteNum) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a001.id as orgid, b001.name as orgname,"
              + " p110.contract_id, p110.borrower_name,"
              + " p203.year_month, p203.real_corpus,"
              + " p203.real_interest, p203.real_punish_interest,p203.real_corpus+p203.real_interest+p203.real_punish_interest,"
              + " max(p400.emp_id), p400.emp_name, p202.biz_date,"
              + " p202.batch_num from pl202 p202, pl203 p203,"
              + " pl110 p110, pl111 p111, pl400 p400,"
              + " aa410 a410, aa411 a411, aa001 a001,"
              + " ba001 b001 where p202.flow_head_id = p203.flow_head_id"
              + " and p203.contract_id = p110.contract_id"
              + " and p203.contract_id = p111.contract_id"
              + " and p203.contract_id = p400.contract_id"
              + " and p202.batch_num = a410.batch_num"
              + " and a410.id = a411.head_id"
              + " and a411.emp_id = p400.emp_id"
              + " and a411.org_id = p400.org_id and a411.org_id = a001.id"
              + " and a411.collflag = 1 and a001.orginfo_id = b001.id"
              + " and p203.year_month=a411.year_month and p202.biz_st=6 ";
          String criterion = "";
          Vector parameters = new Vector();

          if (office != null && !"".equals(office)) {
            criterion += " and p110.office = ? ";
            parameters.add(office);
          }
          if (loanbank != null && !"".equals(loanbank)) {
            criterion += " and p111.loan_bank_id = ? ";
            parameters.add(loanbank);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " and p110.contract_id = ? ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " and p110.borrower_name =? ";
            parameters.add(borrowerName);
          }
          if (orgId != null && !"".equals(orgId)) {
            criterion += " and a001.id = ? ";
            parameters.add(new Integer(orgId));
          }
          if (orgName != null && !"".equals(orgName)) {
            criterion += " and b001.name like ? ";
            parameters.add("%" + orgName + "%");
          }
          if (empId != null && !"".equals(empId)) {
            criterion += " and a411.emp_id =? ";
            parameters.add(new Integer(empId));
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " and b001.card_num = ? ";
            parameters.add(cardNum);
          }
          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " and a410.biz_date >= ? ";
            parameters.add(beginBizDate);
          }
          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " and a410.biz_date <= ? ";
            parameters.add(endBizDate);
          }
          if (noteNum != null && !"".equals(noteNum)) {
            criterion += " and p202.batch_num = ? ";
            parameters.add(noteNum);
          }
          sql = sql + criterion
              + " group by a001.id,b001.name,p110.contract_id,p110.borrower_name,p203.year_month,p203.real_corpus,p203.real_interest,p203.real_punish_interest,p203.real_corpus + p203.real_interest + p203.real_punish_interest,p400.emp_name,p202.biz_date,p202.batch_num order by a001.id, p110.contract_id, p203.year_month";
          Query query = session.createSQLQuery(sql);
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

  public Object[] queryLoanBackAllListByCriterions_data_1_wsh(
      final SecurityInfo securityInfo, final String office,
      final String loanbank, final String contractId,
      final String borrowerName, final String cardNum, final String docNum,
      final String beginBizDate, final String endBizDate, final String orgId,
      final String orgName, final String empId, final String noteNum) {
    Object obj[] = null;
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select nvl(sum(p203.real_corpus),0),"
              + " nvl(sum(p203.real_interest),0), nvl(sum(p203.real_punish_interest),0)"
              + " from pl202 p202, pl203 p203,"
              + " pl110 p110, pl111 p111, pl400 p400,"
              + " aa410 a410, aa411 a411, aa001 a001,"
              + " ba001 b001 where p202.flow_head_id = p203.flow_head_id"
              + " and p203.contract_id = p110.contract_id"
              + " and p203.contract_id = p111.contract_id"
              + " and p203.contract_id = p400.contract_id"
              + " and p202.batch_num = a410.batch_num"
              + " and a410.id = a411.head_id"
              + " and a411.emp_id = p400.emp_id"
              + " and a411.org_id = p400.org_id and a411.org_id = a001.id"
              + " and a411.collflag = 1 and a001.orginfo_id = b001.id"
              + " and p203.year_month=a411.year_month and p400.reservea_b=1 and p202.biz_st=6 ";
          String criterion = "";
          Vector parameters = new Vector();

          if (office != null && !"".equals(office)) {
            criterion += " and p110.office = ? ";
            parameters.add(office);
          }
          if (loanbank != null && !"".equals(loanbank)) {
            criterion += " and p111.loan_bank_id = ? ";
            parameters.add(loanbank);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " and p110.contract_id = ? ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " and p110.borrower_name =? ";
            parameters.add(borrowerName);
          }
          if (orgId != null && !"".equals(orgId)) {
            criterion += " and a001.id = ? ";
            parameters.add(new Integer(orgId));
          }
          if (orgName != null && !"".equals(orgName)) {
            criterion += " and b001.name like ? ";
            parameters.add("%" + orgName + "%");
          }
          if (empId != null && !"".equals(empId)) {
            criterion += " and a411.emp_id =? ";
            parameters.add(new Integer(empId));
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " and b001.card_num = ? ";
            parameters.add(cardNum);
          }
          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " and a410.biz_date >= ? ";
            parameters.add(beginBizDate);
          }
          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " and a410.biz_date <= ? ";
            parameters.add(endBizDate);
          }
          if (noteNum != null && !"".equals(noteNum)) {
            criterion += " and p202.batch_num = ? ";
            parameters.add(noteNum);
          }
          sql = sql + criterion;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return (Object[]) query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }
  public Object[] queryLoanBackAllListByCriterions_date(
      final SecurityInfo securityInfo, final String office,
      final String loanbank, final String contractId,
      final String borrowerName, final String cardNum, final String docNum,
      final String beginBizDate, final String endBizDate, final String orgId,
      final String orgName, final String empId, final String noteNum) {
    Object obj[] = null;
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select min(p202.biz_date),max(p202.biz_date)"
              + " from pl202 p202, pl203 p203,"
              + " pl110 p110, pl111 p111, pl400 p400,"
              + " aa410 a410, aa411 a411, aa001 a001,"
              + " ba001 b001 where p202.flow_head_id = p203.flow_head_id"
              + " and p203.contract_id = p110.contract_id"
              + " and p203.contract_id = p111.contract_id"
              + " and p203.contract_id = p400.contract_id"
              + " and p202.batch_num = a410.batch_num"
              + " and a410.id = a411.head_id"
              + " and a411.emp_id = p400.emp_id"
              + " and a411.org_id = p400.org_id and a411.org_id = a001.id"
              + " and a411.collflag = 1 and a001.orginfo_id = b001.id"
              + " and p203.year_month=a411.year_month and p400.reservea_b=1 and p202.biz_st=6 ";
          String criterion = "";
          Vector parameters = new Vector();

          if (office != null && !"".equals(office)) {
            criterion += " and p110.office = ? ";
            parameters.add(office);
          }
          if (loanbank != null && !"".equals(loanbank)) {
            criterion += " and p111.loan_bank_id = ? ";
            parameters.add(loanbank);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " and p110.contract_id = ? ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " and p110.borrower_name =? ";
            parameters.add(borrowerName);
          }
          if (orgId != null && !"".equals(orgId)) {
            criterion += " and a001.id = ? ";
            parameters.add(new Integer(orgId));
          }
          if (orgName != null && !"".equals(orgName)) {
            criterion += " and b001.name like ? ";
            parameters.add("%" + orgName + "%");
          }
          if (empId != null && !"".equals(empId)) {
            criterion += " and a411.emp_id =? ";
            parameters.add(new Integer(empId));
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " and b001.card_num = ? ";
            parameters.add(cardNum);
          }
          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " and a410.biz_date >= ? ";
            parameters.add(beginBizDate);
          }
          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " and a410.biz_date <= ? ";
            parameters.add(endBizDate);
          }
          if (noteNum != null && !"".equals(noteNum)) {
            criterion += " and p202.batch_num = ? ";
            parameters.add(noteNum);
          }
          sql = sql + criterion;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return (Object[]) query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }
  public List queryLoanBackBankListByCriterions_yg(
      final SecurityInfo securityInfo, final String office,
      final String loanbank, final String contractId,
      final String borrowerName, final String cardNum, final String docNum,
      final String beginBizDate, final String endBizDate, final String orgId,
      final String orgName, final String empId, final String noteNum) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select p111.loan_bank_id,nvl(sum(p203.real_corpus),0),"
              + " nvl(sum(p203.real_interest),0), nvl(sum(p203.real_punish_interest),0),count(distinct p400.emp_id)"
              + " from pl202 p202, pl203 p203,"
              + " pl110 p110, pl111 p111, pl400 p400,"
              + " aa410 a410, aa411 a411, aa001 a001,"
              + " ba001 b001 where p202.flow_head_id = p203.flow_head_id"
              + " and p203.contract_id = p110.contract_id"
              + " and p203.contract_id = p111.contract_id"
              + " and p203.contract_id = p400.contract_id"
              + " and p202.batch_num = a410.batch_num"
              + " and a410.id = a411.head_id"
              + " and a411.emp_id = p400.emp_id"
              + " and a411.org_id = p400.org_id and a411.org_id = a001.id"
              + " and a411.collflag = 1 and a001.orginfo_id = b001.id"
              + " and p203.year_month=a411.year_month and p202.biz_st=6 ";
          String criterion = "";
          Vector parameters = new Vector();

          if (office != null && !"".equals(office)) {
            criterion += " and p110.office = ? ";
            parameters.add(office);
          }
          if (loanbank != null && !"".equals(loanbank)) {
            criterion += " and p111.loan_bank_id = ? ";
            parameters.add(loanbank);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " and p110.contract_id = ? ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " and p110.borrower_name =? ";
            parameters.add(borrowerName);
          }
          if (orgId != null && !"".equals(orgId)) {
            criterion += " and a001.id = ? ";
            parameters.add(new Integer(orgId));
          }
          if (orgName != null && !"".equals(orgName)) {
            criterion += " and b001.name like ? ";
            parameters.add("%" + orgName + "%");
          }
          if (empId != null && !"".equals(empId)) {
            criterion += " and a411.emp_id =? ";
            parameters.add(new Integer(empId));
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " and b001.card_num = ? ";
            parameters.add(cardNum);
          }
          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " and a410.biz_date >= ? ";
            parameters.add(beginBizDate);
          }
          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " and a410.biz_date <= ? ";
            parameters.add(endBizDate);
          }
          if (noteNum != null && !"".equals(noteNum)) {
            criterion += " and p202.batch_num = ? ";
            parameters.add(noteNum);
          }
          sql = sql + criterion + " group by p111.loan_bank_id ";

          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          LoanBackBankDTO loanBackBankDTO = null;
          Iterator it = query.list().iterator();
          List rlist = new ArrayList();
          while (it.hasNext()) {
            Object obj[] = (Object[]) it.next();
            loanBackBankDTO = new LoanBackBankDTO();
            loanBackBankDTO.setCollbankname(obj[0].toString());
            loanBackBankDTO.setCorpus(new BigDecimal(obj[1].toString()));
            loanBackBankDTO.setInterest(new BigDecimal(obj[2].toString()));
            loanBackBankDTO
                .setCorpusInterest(new BigDecimal(obj[3].toString()));
            loanBackBankDTO.setAll(loanBackBankDTO.getCorpus().add(
                loanBackBankDTO.getInterest()).add(
                loanBackBankDTO.getCorpusInterest()));
            loanBackBankDTO.setCount(Integer.parseInt(obj[4].toString()));
            rlist.add(loanBackBankDTO);
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
   * Description 贷款业务流水统计查询列表
   * 
   * @author wangy 2007-10-16
   * @param 查询全部(用于合计、打印和count)
   * @param 由LoanBusiFlowQueryBS调用
   * @param securityInfo
   * @param docNum
   * @param contractId
   * @param loanKouAcc
   * @param borrowerName
   * @param makePerson
   * @param bizType
   * @param bizSt
   * @param loanBankName
   * @param beginBizDate
   * @param endBizDate
   * @param noteNum
   * @return List
   */
  public List queryLoanBusiFlowQueryAllByCriterions(
      final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String noteNum, final String isGjjLoanback) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select docnum,loankouacc, contractId,borrowername,bizType,occurMoney,realcorpus,reclaimAccrual,"
              + "realPunishInterest,badDebt,putUpMoney,bail,bailAccrual,bizSt,"
              + "bizDate,wrongBizType,loanBankID, makePerson,flowheadID,notenum,batch_num from "
              + "("
              + " select pl202.doc_num as docnum,"
              + " '' as loankouacc, "
              + " '' as contractId,"
              + " '' as borrowername,"
              + " pl202.biz_type as bizType,"
              + " 0 as occurMoney,"
              + " nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + " nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + " nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + " 0 as badDebt,"
              + " nvl(pl202.occur_money, 0) as putUpMoney,"
              + " nvl(pl202.occur_money,0) as bail,"
              + " nvl(pl202.other_interest,0) as bailAccrual,"
              + " pl202.biz_st as bizSt,"
              + " pl202.biz_date as bizDate,"
              + " pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum,"
              + " pl202.batch_num as batch_num"
              + " from PL202 pl202"
              + " where pl202.biz_type = '5' or (pl202.biz_type = '12' and pl202.wrong_biz_type = '5') or pl202.biz_type = '15'"
              + " union"
              + " select pl202.doc_num as docnum,"
              + " pl203.loan_kou_acc as loankouacc,"
              + " pl203.contract_id as contractId,"
              + " pl110.borrower_name  as borrowername,"
              + " pl202.biz_type as bizType,"
              + " nvl(pl202.occur_money,0) as occurMoney,"
              + " nvl(pl202.real_corpus,0)+nvl(pl202.real_overdue_corpus,0) as realcorpus,"
              + " nvl(pl202.real_interest,0)+nvl(pl202.real_overdue_interest,0) as reclaimAccrual,"
              + " nvl(pl202.real_punish_interest, 0) as realPunishInterest,"
              + " nvl(pl202.occur_money,0) as badDebt,"
              + " nvl(pl202.occur_money,0) as putUpMoney,"
              + " nvl(pl202.occur_money,0) as bail,"
              + " nvl(pl202.other_interest,0) as bailAccrual,"
              + " pl202.biz_st as bizSt,"
              + " pl202.biz_date as bizDate,"
              + " pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " pl202.note_num as notenum,"
              + " pl202.batch_num as batch_num"
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
          String ob = " bizDate,docnum ";
          String od = " ASC";

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
          if ("0".equals(isGjjLoanback)) {
            criterion += " batch_num is not null and ";
          } else if ("1".equals(isGjjLoanback)) {
            criterion += " (batch_num is null or batch_num = '') and ";
          }
          if (criterion.length() != 0)
            criterion = " where " + " loanBankID "
                + securityInfo.getDkSecuritySQL() + " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + od;

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
   * 银行代扣导入模块 根据银行代码查询流水表中是否存在业务状态为导入的信息 jj 2007-10-15
   * 
   * @param loanBankId
   * @param bizStatus
   * @return
   */
  public List queryLoanFlowImportByLoanBankId_LJ(final String loanBankId,
      final String bizStatus) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanFlowHead.flowHeadId from LoanFlowHead loanFlowHead";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (bizStatus != null && !bizStatus.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizStatus);
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
   * 根据银行代码和业务状态查询流水头表 在银行代扣导出和银行代扣导入时查询该银行下是否存在导出记录 jj
   * 
   * @param loanBankId
   * @param bizSt
   * @return
   */
  public List queryExportListByLoanKouAcc_LJ(final String loanBankId,
      final String bizSt, final String batchNum) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanFlowHead.flowHeadId,loanFlowHead.reserveaA,loanFlowHead.batchNum from LoanFlowHead loanFlowHead";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (batchNum != null && !batchNum.equals("")) {
            criterion += "loanFlowHead.batchNum = ? and ";
            parameters.add(batchNum);
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
   * 根据银行代码和业务状态查询流水头表 在银行代扣导出和银行代扣导入时查询该银行下是否存在导出记录 jj
   * 
   * @param loanBankId
   * @param bizSt
   * @return
   */
  public List queryExportListByLoanKouAcc_LJ_yg(final String loanBankId,
      final String bizSt, final String batchNum) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanFlowHead.flowHeadId,loanFlowHead.reserveaA,loanFlowHead.batchNum from LoanFlowHead loanFlowHead";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          criterion += "loanFlowHead.batchNum is not null and ";
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
   * 生成公积金导出数据 根据银行代码和业务状态查询流水头尾表,合同编号必须是已签订合同的 郭婧平
   * 
   * @param loanBankId
   * @param bizSt
   * @return
   */
  public List queryExportListByLoanKouAcc_GJP(final String loanBankId,
      final String bizSt, final List idList) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pl202.FLOW_HEAD_ID,pl202.RESERVEA_A,pl202.BATCH_NUM from PL202 pl202,PL203 pl203 ";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "pl202.LOAN_BANK_ID = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "pl202.BIZ_ST = ?  and ";
            parameters.add(bizSt);
          }
          criterion += "pl202.BATCH_NUM is not null and pl202.FLOW_HEAD_ID=pl203.FLOW_HEAD_ID and ";
          if (idList != null && idList.size() != 0) {
            criterion += " ( ";
            for (int i = 0; i < idList.size(); i++) {
              criterion += " pl203.CONTRACT_ID = ? or ";
              parameters.add(idList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and ";
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
   * 生成公积金导出数据 根据银行代码和业务状态查询流水头尾表,合同编号必须是已签订合同的 郭婧平
   * 
   * @param loanBankId
   * @param bizSt
   * @return
   */
  public List queryExportListByLoanKouAcc_GJP_yg(final String loanBankId,
      final String bizSt, final List idList) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pl202.FLOW_HEAD_ID,pl202.RESERVEA_A,pl202.BATCH_NUM from PL202 pl202,PL203 pl203 ";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "pl202.LOAN_BANK_ID = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "pl202.BIZ_ST = ?  and ";
            parameters.add(bizSt);
          }
          criterion += "pl202.BATCH_NUM is null and pl202.FLOW_HEAD_ID=pl203.FLOW_HEAD_ID and ";
          if (idList != null && idList.size() != 0) {
            criterion += " ( ";
            for (int i = 0; i < idList.size(); i++) {
              criterion += " pl203.CONTRACT_ID = ? or ";
              parameters.add(idList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and ";
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
   * 查询改批次号在pl202表中是否存在 郭婧平 2007.12.14
   */
  public List queryBatchNum_GJP(final String batchNum) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanFlowHead.flowHeadId from LoanFlowHead loanFlowHead where loanFlowHead.batchNum = ? ";
          Query query = session.createQuery(hql);
          query.setString(0, batchNum);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Description 贷款业务流水统计查询列表
   * 
   * @author wangy 2007-10-18
   * @param 根据PL202.FLOW_HEAD_ID查询结转余额信息
   * @param 由LoanBusiFlowQueryBS调用
   * @param flowHeadId
   * @param securityInfo
   * @return List
   */
  public List queryLoanBusiFlowQueryClearByFlowHeadId(final String flowHeadId,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select pl203.contract_id as contractID,"
              + " pl110.borrower_name as borrowerName,"
              + " pl110.card_kind as cardKind,"
              + " pl110.card_num as cardNum,"
              + " pl202.loan_bank_id as loanBankId,"
              + " pl203.loan_kou_acc as loanKouAcc,"
              + " pl111.loan_money as loanMoney,"
              + " pl202.real_corpus as real_corpus,"
              + " pl111.loan_time_limit as loanTimeLimit,"
              + " pl115.loan_month_rate as loanMonthRate "
              + " from PL202 pl202, PL203 pl203, PL110 pl110, PL111 pl111, PL115 pl115 "
              + " where pl110.contract_id = pl203.contract_id "
              + " and pl111.contract_id = pl203.contract_id "
              + " and pl115.contract_id = pl203.contract_id "
              + " and pl203.flow_head_id = pl202.flow_head_id "
              + " and pl202.flow_head_id = ? and pl111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setString(0, flowHeadId);
          List queryList = query.list();
          List tempList = new ArrayList();
          Iterator it = queryList.iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            LoanBusiFlowQueryClearDTO loanBusiFlowQueryClearDTO = new LoanBusiFlowQueryClearDTO();
            if (obj[0] != null && !obj[0].equals(""))
              loanBusiFlowQueryClearDTO.setContractId(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              loanBusiFlowQueryClearDTO.setBorrowerName(obj[1].toString());
            if (obj[2] != null && !obj[2].equals(""))
              loanBusiFlowQueryClearDTO.setCardKind(obj[2].toString());
            if (obj[3] != null && !obj[3].equals(""))
              loanBusiFlowQueryClearDTO.setCardNum(obj[3].toString());
            if (obj[4] != null && !obj[4].equals(""))
              loanBusiFlowQueryClearDTO.setLoanBankId(obj[4].toString());
            if (obj[5] != null && !obj[5].equals(""))
              loanBusiFlowQueryClearDTO.setLoanKouAcc(obj[5].toString());
            if (obj[6] != null && !obj[6].equals(""))
              loanBusiFlowQueryClearDTO.setLoanMoney(new BigDecimal(obj[6]
                  .toString()));
            if (obj[7] != null && !obj[7].equals(""))
              loanBusiFlowQueryClearDTO.setRealCorpus(new BigDecimal(obj[7]
                  .toString()));
            if (obj[8] != null && !obj[8].equals(""))
              loanBusiFlowQueryClearDTO.setLoanTimeLimit(obj[8].toString());
            if (obj[9] != null && !obj[9].equals(""))
              loanBusiFlowQueryClearDTO.setLoanMonthRate(obj[9].toString());
            tempList.add(loanBusiFlowQueryClearDTO);
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
   * 呆账核销维护页面列表查询 jj 2007-10-19
   * 
   * @param loanKouAcc
   * @param contractId
   * @param name
   * @param cardNum
   * @param docNum
   * @param type
   * @param loanBankId
   * @param status
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param securityInfo
   * @param page
   * @return
   */
  public List queryBadDestroyList_LJ(final String loanKouAcc,
      final String contractId, final String name, final String cardNum,
      final String docNum, final String loanBankId, final String status,
      final String orderBy, final String order, final int start,
      final int pageSize, final SecurityInfo securityInfo, final int page) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select distinct loanFlowHead.flowHeadId,loanFlowTail.loanKouAcc,loanFlowHead.docNum,"
              + " loanFlowTail.contractId,borrower.borrowerName,borrower.cardNum, "
              + " nvl(loanFlowHead.realCorpus,0)+nvl(loanFlowHead.realOverdueCorpus,0),"
              + " nvl(loanFlowHead.realInterest,0)+nvl(loanFlowHead.realOverdueInterest,0)+nvl(loanFlowHead.realPunishInterest,0),"
              + " nvl(loanFlowHead.realCorpus,0)+nvl(loanFlowHead.realOverdueCorpus,0)+nvl(loanFlowHead.realInterest,0)+nvl(loanFlowHead.realOverdueInterest,0)+nvl(loanFlowHead.realPunishInterest,0),"
              + " loanFlowHead.bizSt "
              + " from LoanFlowTail loanFlowTail,Borrower borrower,LoanFlowHead loanFlowHead  where loanFlowHead.flowHeadId = loanFlowTail.flowHeadId "
              + " and loanFlowTail.contractId = borrower.contractId and (loanFlowHead.bizType = 6 or loanFlowHead.bizType = 7 )"
              + " and loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += "loanFlowTail.loanKouAcc = ?  and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += "loanFlowTail.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (name != null && !name.equals("")) {
            criterion += "borrower.borrowerName like ? escape '/'  and ";
            parameters.add("%" + name + "%");
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += "borrower.cardNum = ?  and ";
            parameters.add(cardNum);
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += "loanFlowHead.docNum = ?  and ";
            parameters.add(docNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (status != null && !status.equals("")) {
            if (status.equals("0")) {
              criterion += "(loanFlowHead.bizSt >1 and loanFlowHead. bizSt < 5) and ";
            } else {
              criterion += "loanFlowHead.bizSt = ?  and ";
              parameters.add(status);
            }
          } else {
            criterion += "(loanFlowHead.bizSt >1 and loanFlowHead. bizSt < 7) and ";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " loanFlowHead.flowHeadId ";

          String od = order;
          if (od == null)
            od = "DESC";
          sql = sql + criterion + " order by " + ob + " " + od;

          Query query = session.createQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator it = query.list().iterator();
          List queryList = query.list();
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            it = queryList.iterator();
          }
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BadDebtDestroyTbDTO dto = new BadDebtDestroyTbDTO();
            dto.setId(obj[0].toString());
            dto.setLoanKouAcc(obj[1].toString());
            dto.setDocNum(obj[2].toString());
            dto.setContractId(obj[3].toString());
            dto.setBorrowerName(obj[4].toString());
            dto.setCardNum(obj[5].toString());
            dto.setRealCorpus(new BigDecimal(obj[6].toString()));
            dto.setRealInterest(new BigDecimal(obj[7].toString()));
            dto.setRealMoney(new BigDecimal(obj[8].toString()));
            dto.setBizSt(obj[9].toString());
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
   * 呆账核销列表记录数 jj 2007-10-19
   * 
   * @param loanKouAcc
   * @param contractId
   * @param name
   * @param cardNum
   * @param docNum
   * @param type
   * @param loanBankId
   * @param status
   * @param securityInfo
   * @return
   */
  public int queryBadDestroyCount_LJ(final String loanKouAcc,
      final String contractId, final String name, final String cardNum,
      final String docNum, final String loanBankId, final String status,
      final SecurityInfo securityInfo) {
    int count = 0;
    try {
      Integer counts = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select count(distinct loanFlowHead.flowHeadId)"
                  + " from LoanFlowTail loanFlowTail,Borrower borrower,LoanFlowHead loanFlowHead where loanFlowHead.flowHeadId = loanFlowTail.flowHeadId "
                  + " and loanFlowTail.contractId = borrower.contractId and (loanFlowHead.bizType = 6 or loanFlowHead.bizType = 7)"
                  + " and loanFlowHead.loanBankId "
                  + securityInfo.getDkSecurityHqlSQL() + " ";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (name != null && !name.equals("")) {
                criterion += "borrower.borrowerName like ? escape '/'  and ";
                parameters.add("%" + name + "%");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = ?  and ";
                parameters.add(cardNum);
              }
              if (docNum != null && !docNum.equals("")) {
                criterion += "loanFlowHead.docNum = ?  and ";
                parameters.add(docNum);
              }
              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanFlowHead.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId));
              }
              if (status != null && !status.equals("")) {
                if (status.equals("0")) {
                  criterion += "(loanFlowHead.bizSt >1 and loanFlowHead. bizSt < 5) and ";
                } else {
                  criterion += "loanFlowHead.bizSt = ?  and ";
                  parameters.add(status);
                }
              } else {
                criterion += "(loanFlowHead.bizSt >1 and loanFlowHead. bizSt < 7) and ";
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              sql = sql + criterion;
              Query query = session.createQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult();
            }
          });
      count = counts.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 呆账核销列表合计 jj 2007-10-19
   * 
   * @param loanKouAcc
   * @param contractId
   * @param name
   * @param cardNum
   * @param docNum
   * @param type
   * @param loanBankId
   * @param status
   * @param securityInfo
   * @return
   */
  public List queryBadDestroyTotal_LJ(final String loanKouAcc,
      final String contractId, final String name, final String cardNum,
      final String docNum, final String loanBankId, final String status,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select distinct loanFlowHead.flowHeadId,"
              + " nvl(loanFlowHead.realCorpus,0)+nvl(loanFlowHead.realOverdueCorpus,0),"
              + " nvl(loanFlowHead.realInterest,0)+nvl(loanFlowHead.realOverdueInterest,0)+nvl(loanFlowHead.realPunishInterest,0),"
              + " nvl(loanFlowHead.realCorpus,0)+nvl(loanFlowHead.realOverdueCorpus,0)+nvl(loanFlowHead.realInterest,0)+nvl(loanFlowHead.realOverdueInterest,0)+nvl(loanFlowHead.realPunishInterest,0)"
              + " from LoanFlowTail loanFlowTail,Borrower borrower,LoanFlowHead loanFlowHead where loanFlowHead.flowHeadId = loanFlowTail.flowHeadId "
              + " and loanFlowTail.contractId = borrower.contractId and (loanFlowHead.bizType = 6 or loanFlowHead.bizType = 7)"
              + " and loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL() + " ";
          Vector parameters = new Vector();
          String criterion = "";
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += "loanFlowTail.loanKouAcc = ?  and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += "loanFlowTail.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (name != null && !name.equals("")) {
            criterion += "borrower.borrowerName like ? escape '/'  and ";
            parameters.add("%" + name + "%");
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += "borrower.cardNum = ?  and ";
            parameters.add(cardNum);
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += "loanFlowHead.docNum = ?  and ";
            parameters.add(docNum);
          }
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (status != null && !status.equals("")) {
            if (status.equals("0")) {
              criterion += "(loanFlowHead.bizSt >1 and loanFlowHead. bizSt < 5) and ";
            } else {
              criterion += "loanFlowHead.bizSt = ?  and ";
              parameters.add(status);
            }
          } else {
            criterion += "(loanFlowHead.bizSt >1 and loanFlowHead. bizSt < 7) and ";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          sql = sql + criterion;

          Query query = session.createQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BadDebtDestroyTbDTO dto = new BadDebtDestroyTbDTO();
            dto.setId(obj[0].toString());
            dto.setRealCorpus(new BigDecimal(obj[1].toString()));
            dto.setRealInterest(new BigDecimal(obj[2].toString()));
            dto.setRealMoney(new BigDecimal(obj[3].toString()));
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
   * Description 贷款业务流水统计查询列表
   * 
   * @author wangy 2007-10-19
   * @param 根据PL202.FLOW_HEAD_ID条件查询结息信息
   * @param 由LoanBusiFlowQueryBS调用
   * @param flowHeadId
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @return List
   */
  public List queryBailClearInterestTbListByCriterions(final String flowHeadId,
      final int start, final String orderBy, final String order,
      final int pageSize, final int page) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p202.biz_date biz_date,"
              + " p202.loan_bank_id loan_bank_id,"
              + " p203.loan_kou_acc loan_kou_acc,"
              + "  p110.borrower_name borrower_name,"
              + " p111.bail_balance bail_balance,"
              + " p203.occur_money occur_money,"
              + " (select sum(pl203.occur_money) "
              + " from pl203 pl203,pl202 pl202 "
              + " where pl203.flow_head_id = pl202.flow_head_id "
              + " and pl203.contract_id = p203.contract_id "
              + " and pl202.biz_date >= p202.biz_date ) afteryear_occur_money "
              + " from pl202 p202, pl203 p203, pl111 p111, pl110 p110 "
              + " where p110.contract_id = p111.contract_id "
              + " and p111.contract_id = p203.contract_id "
              + " and p203.flow_head_id = p202.flow_head_id "
              + " and p202.biz_type = 15 ";

          Vector parameters = new Vector();
          String criterion = "";

          if (flowHeadId != null && !"".equals(flowHeadId)) {
            criterion += " p202.flow_head_id = ? and ";
            parameters.add(flowHeadId);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p202.biz_date, p202.loan_bank_id ";

          String od = order;
          if (od == null)
            od = " DESC";

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
   * Description 贷款业务流水统计查询列表
   * 
   * @author wangy 2007-10-19
   * @param 根据PL202.FLOW_HEAD_ID查询全部结息信息
   * @param 由LoanBusiFlowQueryBS调用
   * @param flowHeadId
   * @return List
   */
  public List queryBailClearInterestTbCountByCriterions(final String flowHeadId) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p202.biz_date biz_date,"
              + " p202.loan_bank_id loan_bank_id,"
              + " p203.loan_kou_acc loan_kou_acc,"
              + "  p110.borrower_name borrower_name,"
              + " p111.bail_balance bail_balance,"
              + " p203.occur_money occur_money,"
              + " (select sum(pl203.occur_money) "
              + " from pl203 pl203,pl202 pl202 "
              + " where pl203.flow_head_id = pl202.flow_head_id "
              + " and pl203.contract_id = p203.contract_id "
              + " and pl202.biz_date >= p202.biz_date ) afteryear_occur_money "
              + " from pl202 p202, pl203 p203, pl111 p111, pl110 p110 "
              + " where p110.contract_id = p111.contract_id "
              + " and p111.contract_id = p203.contract_id "
              + " and p203.flow_head_id = p202.flow_head_id "
              + " and p202.biz_type = 15 ";

          Vector parameters = new Vector();
          String criterion = "";

          if (flowHeadId != null && !"".equals(flowHeadId)) {
            criterion += " p202.flow_head_id = ? and ";
            parameters.add(flowHeadId);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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
   * 石岩 扎账 判断是以中心为主的还是以银行为主的参数，深入储存过程的202要修改的流水记录，IP地址，结息日-会计日期，操作员
   */

  public void clearAccount_sy(String param, String pl202Id, String ip,
      String numberdate, String operator) throws BusinessException,
      HibernateException, SQLException {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      CallableStatement cs = conn.prepareCall("{call CLEARACCONT(?,?,?,?,?)}");
      cs.setString(1, param);
      cs.setString(2, pl202Id);
      cs.setString(3, ip);
      cs.setString(4, numberdate);
      cs.setString(5, operator);
      cs.execute();
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("扎账失败!!!");
    }
  }

  /**
   * 石岩 扎账 判断是以中心为主的还是以银行为主的参数，登陆下所管的人，IP地址，结息日-会计日期，操作员
   */

  public void clearAccount_all(String param, String userall, String ip,
      String numberdate, String operator, List list) throws BusinessException,
      HibernateException, SQLException {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      // 循环插入临时表PreparedStatement不能使用，因为其相当开启隐形游标当时164抛出异常
      Statement statement = conn.createStatement();
      for (int i = 0; i < list.size(); i++) {
        String temp_flowid = "";
        temp_flowid = list.get(i) + "";
        String sql = "insert into plcleartemp(id)" + "values ('" + temp_flowid
            + "')";
        statement.executeUpdate(sql);
      }
      CallableStatement cs = conn.prepareCall("{call CLEARACCONTALL(?,?,?,?)}");
      cs.setString(1, param);
      cs.setString(2, ip);
      cs.setString(3, numberdate);
      cs.setString(4, operator);
      cs.execute();
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("扎账失败!!!");
    }
  }

  /**
   * 石岩 查找出所有符合扎账要求的头表id 状态，类型 return list
   */
  public List queryLoanFiowHeadClearAccount_sy(final String bizSt,
      final String bizType, final SecurityInfo securityInfo,
      final String userName) throws BusinessException {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanFlowHead.flowHeadId from LoanFlowHead loanFlowHead";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType in ( " + bizType + " )  and ";
            // parameters.add(bizType);
          }
          if (userName != null && !userName.equals("")) {
            criterion += "loanFlowHead.makePerson in ( " + userName
                + " )  and ";
            // parameters.add(userName);
          }
          if (criterion.length() != 0)
            criterion = " where loanFlowHead.loanBankId "
                + securityInfo.getDkSecurityHqlSQL() + " and  "
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
   * 求出带分页的list，查看个人流水 hanl
   * 
   * @param docnum
   * @param biztype
   * @param bizdateB
   * @param bizdateE
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   */
  public List findIndividualFlowList(final String contractIdHl,
      final String docnum, final String biztype, final String bizdateB,
      final String bizdateE, final String orderBy, final String orderother,
      final int start, final int pageSize, final int page,
      final SecurityInfo securityInfo) {

    List list = new ArrayList();
    try {
      Validate.isTrue(orderother == null || "ASC".equalsIgnoreCase(orderother)
          || "DESC".equalsIgnoreCase(orderother));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p202.biz_date,p203.year_month,p202.doc_num,p202.biz_type,"
              + "p203.occur_money,p203.should_corpus,p203.should_interest,p203.should_punish_interest,"
              + "p203.real_corpus,p203.real_interest,p203.real_punish_interest,p203.loan_type,"
              + "p202.wrong_biz_type,p202.batch_num "
              + "from pl202 p202,pl203 p203 where p203.flow_head_id=p202.flow_head_id and (p203.real_corpus!=0 or p203.real_interest!=0 or  p203.real_punish_interest!=0 or p202.biz_type=1 or p202.biz_type=12) "
              + "and p202.loan_bank_id " + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          criterion += " p203.contract_id= ? and ";
          parameters.add(contractIdHl);

          if (docnum != null && !"".equals(docnum)) {
            criterion += "  p202.doc_num=? and ";
            parameters.add(docnum);
          }
          if (biztype != null && !"".equals(biztype)) {
            criterion += " p202.biz_type=? and ";
            parameters.add(biztype);
          }
          if (bizdateB != null && !"".equals(bizdateB)) {
            criterion += " p202.biz_date>=? and ";
            parameters.add(bizdateB);
          }
          if (bizdateE != null && !"".equals(bizdateE)) {
            criterion += " p202.biz_date<=? and ";
            parameters.add(bizdateE);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p202.biz_date ";

          String od = orderother;
          if (od == null)
            od = "ASC";

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
              LoandeskaccqueryTbDTO loandeskaccqueryTbDTO = new LoandeskaccqueryTbDTO();

              if (obj[0] != null) {
                loandeskaccqueryTbDTO.setId(obj[0].toString());
              }
              if (obj[0] != null) {
                loandeskaccqueryTbDTO.setBizdate(obj[0].toString());
              }
              if (obj[1] != null) {
                loandeskaccqueryTbDTO.setYearmonth(obj[1].toString());
              }
              if (obj[2] != null) {
                loandeskaccqueryTbDTO.setDocnum(obj[2].toString());
              }
              if (obj[3] != null) {
                loandeskaccqueryTbDTO.setBiztype(obj[3].toString());
              }
              if (obj[4] != null) {
                loandeskaccqueryTbDTO.setOccurmoney(obj[4].toString());
              }
              if (obj[5] != null) {
                loandeskaccqueryTbDTO.setShouldcorpus(obj[5].toString());
              }
              if (obj[6] != null) {
                loandeskaccqueryTbDTO.setShouldinterest(obj[6].toString());
              }
              if (obj[7] != null) {
                loandeskaccqueryTbDTO
                    .setShouldpunishinterest(obj[7].toString());
              }
              if (obj[8] != null) {
                loandeskaccqueryTbDTO.setRealcorpus(obj[8].toString());
              }
              if (obj[9] != null) {
                loandeskaccqueryTbDTO.setRealinterest(obj[9].toString());
              }
              if (obj[10] != null) {
                loandeskaccqueryTbDTO.setRealpunishinterest(obj[10].toString());
              }
              if (obj[11] != null) {
                loandeskaccqueryTbDTO.setLoantype(obj[11].toString());
              }
              if (obj[12] != null) {
                loandeskaccqueryTbDTO.setWrongbiztype(obj[12].toString());
              }
              if (obj[13] != null && !"".equals(obj[13].toString())) {
                loandeskaccqueryTbDTO.setBatchNum(obj[13].toString());
              } else{
                loandeskaccqueryTbDTO.setBatchNum("否");
              }
              loandeskaccqueryTbDTO.setSumCorpusInterest(new BigDecimal(
                  loandeskaccqueryTbDTO.getRealcorpus()).add(
                  new BigDecimal(loandeskaccqueryTbDTO.getRealinterest()))
                  .add(
                      new BigDecimal(loandeskaccqueryTbDTO
                          .getRealpunishinterest())));
              temp_list.add(loandeskaccqueryTbDTO);
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

  public String queryPL400SEQ(final String contractid) {
    String str = "";
    try {
      str = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select min(p4.seq) from pl400 p4 where p4.contract_id = '"
              + contractid + "'";
          Query query = session.createSQLQuery(hql);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }
  public String queryPL400SEQYG(final String contractid,final String batchNum,final String yearmonth) {
    String str = "";
    try {
      str = (String) getHibernateTemplate().execute(new HibernateCallback() {
        
        public Object doInHibernate(Session session) throws HibernateException,
        SQLException {
          String sql = "select p.seq from aa411 t, aa410 a,pl400 p "
              + "where t.head_id = a.id and t.contract_id=p.contract_id "
              + "and p.emp_id=t.emp_id and p.org_id=t.org_id "
              + "and t.contract_id = '"+contractid+"' and t.collflag = 1 "
              + "and p.reservea_b='1' "
              + "and a.batch_num='"+batchNum+"' and t.year_month= '"+yearmonth+"'";
          Query query = session.createSQLQuery(sql);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }

  /**
   * 贷方笔数 错账
   * 
   * @param office
   * @param bankId
   * @param startDate
   * @param endDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @param securityInfo
   * @return
   */
  public String queryLoanFlowHeadCreditAdjustByOffice(final String office,
      final String bankId, final String startDate, final String endDate,
      final String orderBy, final String order, final int pageSize,
      final int start, final SecurityInfo securityInfo) {
    String happenAcc = "";
    try {
      happenAcc = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select count(t.flow_head_id) "
                  + "from pl002 t2,pl202 t "
                  + "where t.biz_type = 12 and t.loan_bank_id=t2.loan_bank_id "
                  + "and t.wrong_biz_type in (2, 5, 6, 7)"
                  + "and t.loan_bank_id " + securityInfo.getDkSecuritySQL()
                  + "and t.biz_date between ? and ? and t.biz_st=6";

              String ob = orderBy;
              if (ob == null)
                ob = " t.flow_head_id ";

              String od = order;
              if (od == null)
                od = " DESC";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t.loan_bank_id=" + bankId;
              }
              if (office != null && !"".equals(office)) {
                hql = hql + " and t2.office='" + office + "'";
              }
              // hql = hql + " order by " + ob + " " + od;
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                return "0";
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenAcc;
  }

  /**
   * 期末贷方笔数 错账
   * 
   * @param office
   * @param bankId
   * @param startDate
   * @param endDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @param securityInfo
   * @return
   */
  public String queryLoanFlowHeadCreditAdjustEndByOffice(final String office,
      final String bankId, final String startDate, final String endDate,
      final String orderBy, final String order, final int pageSize,
      final int start, final SecurityInfo securityInfo) {
    String happenAcc = "";
    try {
      happenAcc = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select count(t.flow_head_id) "
                  + "from pl002 t2,pl202 t "
                  + "where t.biz_type = 12 and t.loan_bank_id=t2.loan_bank_id "
                  + "and t.wrong_biz_type in (2, 5, 6, 7)"
                  + "and t.loan_bank_id " + securityInfo.getDkSecuritySQL()
                  + "and t.biz_date <= ?  and t.biz_st=6";

              String ob = orderBy;
              if (ob == null)
                ob = " t.flow_head_id ";

              String od = order;
              if (od == null)
                od = " DESC";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t.loan_bank_id=" + bankId;
              }
              if (office != null && !"".equals(office)) {
                hql = hql + " and t2.office='" + office + "'";
              }
              // hql = hql + " order by " + ob + " " + od;
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, endDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                return "0";
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenAcc;
  }

  /**
   * 根据条件查询核销收回维护信息列表
   * 
   * @author 吴迪 2007-10-17
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   */
  public List queryDestoryBackTbShowListByCriterions(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String loanKouAcc,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanBankName, final String bizSt, final String docNum) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p203.loan_kou_acc as loanKouAcc,"
              + "p202.doc_num as docNum," + "p203.contract_id as contract,"
              + "p110.borrower_name as borrowerName,"
              + "p110.card_num as cardNum," + "p202.occur_money as occurMoney,"
              + "p202.biz_st as bizSt," + "p202.flow_head_id  as flowHeadId"
              + " from pl202 p202, pl203 p203, pl110 p110"
              + " where p202.flow_head_id = p203.flow_head_id"
              + " and p203.contract_id =p110.contract_id"
              + " and p202.biz_type in(8,9)";

          String criterion = "";
          Vector parameters = new Vector();
          String ob = orderBy;
          if (ob == null)
            ob = " p202.flow_head_id ";

          String od = order;
          if (od == null)
            od = " DESC";

          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " p203.loan_kou_acc = ? and ";
            parameters.add(loanKouAcc);
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p203.contract_id = ? and ";
            parameters.add(contractId);
          }

          if (docNum != null && !"".equals(docNum)) {
            criterion += " p202.doc_num = ? and ";
            parameters.add(docNum);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num= ? and ";
            parameters.add(cardNum);
          }
          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }

          if (bizSt != null && !"".equals(bizSt)) {
            criterion += "  p202.biz_st = ? and ";
            parameters.add(bizSt);
          } else {
            criterion += "  p202.biz_st in (4,5,6) and ";
          }
          if (criterion.length() != 0)
            criterion = " and   p202.loan_bank_id "
                + securityInfo.getDkSecuritySQL() + " and  "
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
          // System.out.println(queryList.size()+"queryList.size()*******");
          List tempList = new ArrayList();
          // 核销收回维护信息列表
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            DestoryBackTbDTO destoryBackTbDTO = new DestoryBackTbDTO();
            obj = (Object[]) iterate.next();

            if (obj[0] != null) {
              destoryBackTbDTO.setLoanKouAcc(obj[0].toString());
            }
            if (obj[1] != null) {
              destoryBackTbDTO.setDocNum(obj[1].toString());
            }
            if (obj[2] != null) {
              destoryBackTbDTO.setContractId(obj[2].toString());
            }
            if (obj[3] != null) {
              destoryBackTbDTO.setBorrowerName(obj[3].toString());
            }
            if (obj[4] != null) {
              destoryBackTbDTO.setCardNum(obj[4].toString());
            }
            if (obj[5] != null) {
              destoryBackTbDTO.setReclaimCorpus(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              destoryBackTbDTO.setBizSt(obj[6].toString());
            }
            if (obj[7] != null) {
              destoryBackTbDTO.setFlowHeadId(obj[7].toString());
            }
            // 枚举转换业务状态
            try {
              destoryBackTbDTO.setBizSt(BusiTools.getBusiValue(Integer
                  .parseInt("" + destoryBackTbDTO.getBizSt()),
                  BusiConst.PLBUSINESSSTATE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            tempList.add(destoryBackTbDTO);
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
   * 根据贷款账号在PL202中查询状态为1、２、３、４、５的记录 核销收回 吴迪
   * 
   * @param loanKouAcc
   * @param securityInfo
   * @return List
   */
  public List queryDestoryBackByLoanKouAcc_WU(final String loanKouAcc,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select p203.loan_kou_acc"
              + " from pl202 p202, pl203 p203"
              + " where p202.flow_head_id = p203.flow_head_id"
              + " and p202.biz_st in (1,2,3,4,5)" + " and p203.loan_kou_acc= ?"
              + " and p202.loan_bank_id " + securityInfo.getDkSecuritySQL();
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
   * 根据条件查询核销收回维护信息列表总记录
   * 
   * @author 吴迪 2007-10-18
   * @param securityInfo
   * @return
   */
  public List queryDestoryBackTbShowListCountByCriterions(
      final SecurityInfo securityInfo, final String loanKouAcc,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanBankName, final String bizSt, final String docNum) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p203.loan_kou_acc as loanKouAcc,"
              + "p202.doc_num as docNum," + "p203.contract_id as contract,"
              + "p110.borrower_name as borrowerName,"
              + "p110.card_num as cardNum," + "p202.occur_money as occurMoney,"
              + "p202.biz_st as bizSt," + "p202.flow_head_id  as flowHeadId"
              + " from pl202 p202, pl203 p203, pl110 p110"
              + " where p202.flow_head_id = p203.flow_head_id"
              + " and p203.contract_id =p110.contract_id"
              + " and p202.biz_type in(8,9)";

          String criterion = "";
          Vector parameters = new Vector();

          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " p203.loan_kou_acc = ? and ";
            parameters.add(loanKouAcc);
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p203.contract_id = ? and ";
            parameters.add(contractId);
          }

          if (docNum != null && !"".equals(docNum)) {
            criterion += " p202.doc_num = ? and ";
            parameters.add(docNum);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num= ? and ";
            parameters.add(cardNum);
          }
          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }

          if (bizSt != null && !"".equals(bizSt)) {
            criterion += "  p202.biz_st = ? and ";
            parameters.add(bizSt);
          } else {
            criterion += "  p202.biz_st in (4,5,6) and ";
          }
          if (criterion.length() != 0)
            criterion = " and   p202.loan_bank_id "
                + securityInfo.getDkSecuritySQL() + " and  "
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
            DestoryBackTbDTO destoryBackTbDTO = new DestoryBackTbDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              destoryBackTbDTO.setLoanKouAcc(obj[0].toString());
            }
            if (obj[1] != null) {
              destoryBackTbDTO.setDocNum(obj[1].toString());
            }
            if (obj[2] != null) {
              destoryBackTbDTO.setContractId(obj[2].toString());
            }
            if (obj[3] != null) {
              destoryBackTbDTO.setBorrowerName(obj[3].toString());
            }
            if (obj[4] != null) {
              destoryBackTbDTO.setCardNum(obj[4].toString());
            }
            if (obj[5] != null) {
              destoryBackTbDTO.setReclaimCorpus(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              destoryBackTbDTO.setBizSt(obj[6].toString());
            }
            // 枚举转换业务状态
            try {
              destoryBackTbDTO.setBizSt(BusiTools.getBusiValue(Integer
                  .parseInt("" + destoryBackTbDTO.getBizSt()),
                  BusiConst.PLBUSINESSSTATE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            printList.add(destoryBackTbDTO);
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
   * 根据头表id查询核销收回维护-核销收回维护弹出眶
   * 
   * @author 吴迪 2007-10-20
   * @param flowHeadId
   * @param securityInfo
   * @return
   */
  public List querydestoryBackTbWindowById(final String flowHeadId,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select flowHeadId," + "loanKouAcc," + "contractId,"
              + "borrowerName," + "cardKind," + "cardNum," + "loanMode,"
              + "assistantOrgName," + "occurMoney," + "loanBankId"
              + " from (select p202.flow_head_id as flowHeadId,"
              + "p203.loan_kou_acc as loanKouAcc,"
              + "p203.contract_id as contractId,"
              + "p110.borrower_name as borrowerName,"
              + "p110.card_kind as cardKind," + "p110.card_num as cardNum,"
              + "p111.loan_mode as loanMode," + "'' as assistantOrgName,"
              + "p202.occur_money as occurMoney,"
              + "p111.loan_bank_id as loanBankId"
              + " from pl202 p202,pl203 p203,pl111 p111,pl110 p110"
              + " where p202.flow_head_id = p203.flow_head_id"
              + " and p203.contract_id = p110.contract_id"
              + " and p203.contract_id = p111.contract_id"
              + " and p202.hedai_org is null" + " union"
              + " select p202.flow_head_id       as flowHeadId,"
              + "p203.loan_kou_acc       as loanKouAcc,"
              + "p203.contract_id        as contractId,"
              + "p110.borrower_name      as borrowerName,"
              + "p110.card_kind          as cardKind,"
              + "p110.card_num           as cardNum,"
              + "p111.loan_mode          as loanMode,"
              + "p007.assistant_org_name as assistantOrgName,"
              + "p202.occur_money        as occurMoney,"
              + "p111.loan_bank_id as loanBankId"
              + " from pl202 p202,pl203 p203,pl111 p111,pl110 p110,pl007 p007"
              + " where p202.flow_head_id = p203.flow_head_id"
              + " and p203.contract_id = p110.contract_id"
              + " and p203.contract_id = p111.contract_id"
              + " and p202.hedai_org = p007.assistant_org_id) res"
              + " where res.flowHeadId =?" + " and res.loanBankId "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setString(0, flowHeadId);
          List t = new ArrayList();
          Object[] obj = (Object[]) query.uniqueResult();
          DestoryBackTaDTO destoryBackTaDTO = new DestoryBackTaDTO();
          if (obj[1] != null && !obj[1].equals(""))
            destoryBackTaDTO.setLoanKouAcc(obj[1].toString());
          if (obj[2] != null && !obj[2].equals(""))
            destoryBackTaDTO.setContractId(obj[2].toString());
          if (obj[3] != null && !obj[3].equals(""))
            destoryBackTaDTO.setBorrowerName(obj[3].toString());
          if (obj[4] != null && !obj[4].equals(""))
            destoryBackTaDTO.setCardKind(obj[4].toString());
          if (obj[5] != null && !obj[5].equals(""))
            destoryBackTaDTO.setCardNum(obj[5].toString());
          if (obj[6] != null && !obj[6].equals(""))
            destoryBackTaDTO.setLoanMode(obj[6].toString());
          if (obj[7] != null && !obj[7].equals(""))
            destoryBackTaDTO.setBackunitName(obj[7].toString());
          if (obj[8] != null && !obj[8].equals(""))
            destoryBackTaDTO.setBackMoney(new BigDecimal(obj[8].toString()));
          t.add(destoryBackTaDTO);
          return t;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl 求出不带分页的list，查看个人流水
   * 
   * @param contractIdHl
   * @param docnum
   * @param biztype
   * @param bizdateB
   * @param bizdateE
   * @param securityInfo
   * @return
   */
  public List findIndividualFlowAllList(final String contractIdHl,
      final String docnum, final String biztype, final String bizdateB,
      final String bizdateE, final SecurityInfo securityInfo,
      final String orderBy, final String orderother) {

    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p202.biz_date,p203.year_month,p202.doc_num,p202.biz_type,p203.occur_money,p203.should_corpus,p203.should_interest,p203.should_punish_interest,p203.real_corpus,p203.real_interest,p203.real_punish_interest,p203.loan_type,p202.wrong_biz_type,p202.batch_num from pl202 p202,pl203 p203 where p203.flow_head_id=p202.flow_head_id and (p203.real_corpus!=0 or p203.real_interest!=0 or  p203.real_punish_interest!=0 or p202.biz_type=1 or p202.biz_type=12)  and p202.loan_bank_id "
              + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          criterion += " p203.contract_id= ? and ";
          parameters.add(contractIdHl);

          if (docnum != null && !"".equals(docnum)) {
            criterion += "  p202.doc_num=? and ";
            parameters.add(docnum);
          }
          if (biztype != null && !"".equals(biztype)) {
            criterion += " p202.biz_type=? and ";
            parameters.add(biztype);
          }
          if (bizdateB != null && !"".equals(bizdateB)) {
            criterion += " p202.biz_date>=? and ";
            parameters.add(bizdateB);
          }
          if (bizdateE != null && !"".equals(bizdateE)) {
            criterion += " p202.biz_date<=? and ";
            parameters.add(bizdateE);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " p202.biz_date ";

          String od = orderother;
          if (od == null)
            od = "ASC";

          hql = hql + criterion + "order by " + ob + " " + od;
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
              LoandeskaccqueryTbDTO loandeskaccqueryTbDTO = new LoandeskaccqueryTbDTO();

              if (obj[0] != null) {
                loandeskaccqueryTbDTO.setId(obj[0].toString());
              }
              if (obj[0] != null) {
                loandeskaccqueryTbDTO.setBizdate(obj[0].toString());
              }
              if (obj[1] != null) {
                loandeskaccqueryTbDTO.setYearmonth(obj[1].toString());
              }
              if (obj[2] != null) {
                loandeskaccqueryTbDTO.setDocnum(obj[2].toString());
              }
              if (obj[3] != null) {
                loandeskaccqueryTbDTO.setBiztype(obj[3].toString());
              }
              if (obj[4] != null) {
                loandeskaccqueryTbDTO.setOccurmoney(obj[4].toString());
              }
              if (obj[5] != null) {
                loandeskaccqueryTbDTO.setShouldcorpus(obj[5].toString());
              }
              if (obj[6] != null) {
                loandeskaccqueryTbDTO.setShouldinterest(obj[6].toString());
              }
              if (obj[7] != null) {
                loandeskaccqueryTbDTO
                    .setShouldpunishinterest(obj[7].toString());
              }
              if (obj[8] != null) {
                loandeskaccqueryTbDTO.setRealcorpus(obj[8].toString());
              }
              if (obj[9] != null) {
                loandeskaccqueryTbDTO.setRealinterest(obj[9].toString());
              }
              if (obj[10] != null) {
                loandeskaccqueryTbDTO.setRealpunishinterest(obj[10].toString());
              }
              if (obj[11] != null) {
                loandeskaccqueryTbDTO.setLoantype(obj[11].toString());
              }
              if (obj[12] != null) {
                loandeskaccqueryTbDTO.setWrongbiztype(obj[12].toString());
              }
              if (obj[13] != null && !"".equals(obj[13].toString())) {
                loandeskaccqueryTbDTO.setBatchNum(obj[13].toString());
              } else {
                loandeskaccqueryTbDTO.setBatchNum("否");
              }
              loandeskaccqueryTbDTO.setSumCorpusInterest(new BigDecimal(
                  loandeskaccqueryTbDTO.getRealcorpus()).add(
                  new BigDecimal(loandeskaccqueryTbDTO.getRealinterest()))
                  .add(
                      new BigDecimal(loandeskaccqueryTbDTO
                          .getRealpunishinterest())));
              temp_list.add(loandeskaccqueryTbDTO);
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

  // 本金--月查询分页
  public List queryListByDate_ByOffice(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String office,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select t2.office, substr(t1.biz_date, 0, 6)"
              + " from pl202 t1, pl002 t2 where t1.biz_st=6 and t1.loan_bank_id = t2.loan_bank_id "
              + " and t1.biz_date between ? and ?  and t1.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (office != null && !"".equals(office)) {
            hql = hql + " and t2.office='" + office + "'";
          }
          hql = hql + " group by t2.office, substr(t1.biz_date, 0, 6)";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryListByDate_ByOfficeCount(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String office,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select t2.office, substr(t1.biz_date, 0, 6)"
              + " from pl202 t1, pl002 t2 where t1.biz_st=6 and t1.loan_bank_id = t2.loan_bank_id "
              + " and t1.biz_date between ? and ?  and t1.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (office != null && !"".equals(office)) {
            hql = hql + " and t2.office='" + office + "'";
          }
          hql = hql + " group by t2.office, substr(t1.biz_date, 0, 6)";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);
          // query.setFirstResult(start);
          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 本金--月查询分页
  public List queryListByDay_ByOffice(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String office,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select t2.office, t1.biz_date"
              + " from pl202 t1, pl002 t2 where t1.biz_st=6 and t1.loan_bank_id = t2.loan_bank_id "
              + " and t1.biz_date between ? and ?  and t1.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (office != null && !"".equals(office)) {
            hql = hql + " and t2.office='" + office + "'";
          }
          hql = hql + " group by t2.office, t1.biz_date";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryListByDay_ByOfficeCount(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String office,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select t2.office, t1.biz_date"
              + " from pl202 t1, pl002 t2 where t1.biz_st=6 and t1.loan_bank_id = t2.loan_bank_id "
              + " and t1.biz_date between ? and ?  and t1.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (office != null && !"".equals(office)) {
            hql = hql + " and t2.office='" + office + "'";
          }
          hql = hql + " group by t2.office, t1.biz_date";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);
          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 本金 --月查询分页
  public List queryListByDate_ByBank(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String bankId,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select t.loan_bank_id,substr(t.biz_date,0,6) from "
              + " pl202 t where t.biz_st=6 and t.biz_date between ? and ?  and t.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (bankId != null && !"".equals(bankId)) {
            hql = hql + " and t.loan_bank_id='" + bankId + "'";
          }
          hql = hql + " group by substr(t.biz_date,0,6),t.loan_bank_id ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryListByDate_ByBankCount(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String bankId,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select t.loan_bank_id,substr(t.biz_date,0,6) from "
              + " pl202 t where t.biz_st=6 and t.biz_date between ?  and ?  and t.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (bankId != null && !"".equals(bankId)) {
            hql = hql + " and t.loan_bank_id='" + bankId + "'";
          }
          hql = hql + " group by substr(t.biz_date,0,6),t.loan_bank_id ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);
          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 本金 --月查询分页
  public List queryListByDay_ByBank(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String bankId,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select t.loan_bank_id,t.biz_date from "
              + " pl202 t where t.biz_st=6 and t.biz_date between ?  and ?  and t.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (bankId != null && !"".equals(bankId)) {
            hql = hql + " and t.loan_bank_id='" + bankId + "'";
          }
          hql = hql + " group by t.biz_date,t.loan_bank_id ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryListByDay_ByBankCount(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String bankId,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select t.loan_bank_id,t.biz_date from "
              + " pl202 t where t.biz_st=6 and t.biz_date between ?  and ?  and t.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (bankId != null && !"".equals(bankId)) {
            hql = hql + " and t.loan_bank_id='" + bankId + "'";
          }
          hql = hql + " group by t.biz_date,t.loan_bank_id ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);

          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 本金--月查询分页
  public List queryListByDate_ByDep(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String buyhouseorgid,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select b.head_id, substr(t.biz_date, 0, 6) "
              + " from  pl203 t1, pl114 b,pl202 t "
              + " where t.biz_st=6 and t.flow_head_id = t1.flow_head_id  "
              + " and t1.contract_id = b.contract_id   "
              + " and t.biz_date between ? and ?  and  t.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
            hql = hql + " and b.head_id='" + buyhouseorgid + "'";
          }
          hql = hql + "group by b.head_id,substr(t.biz_date,0,6) ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryListByDate_ByDepCount(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String buyhouseorgid,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select b.head_id, substr(t.biz_date, 0, 6) "
              + " from  pl203 t1, pl114 b,pl202 t "
              + " where t.biz_st=6 and t.flow_head_id = t1.flow_head_id  "
              + " and t1.contract_id = b.contract_id   "
              + " and t.biz_date between ? and ?  and t.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
            hql = hql + " and b.head_id='" + buyhouseorgid + "'";
          }
          hql = hql + "group by b.head_id,substr(t.biz_date,0,6) ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);
          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 本金--月查询分页
  public List queryListByDay_ByDep(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String buyhouseorgid,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select b.head_id, t.biz_date "
              + " from  pl203 t1, pl114 b,pl202 t "
              + " where t.biz_st=6 and t.flow_head_id = t1.flow_head_id  "
              + " and t1.contract_id = b.contract_id   "
              + " and t.biz_date between ? and ?  and t.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
            hql = hql + " and b.head_id='" + buyhouseorgid + "'";
          }
          hql = hql + "group by b.head_id,t.biz_date ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryListByDay_ByDepCount(final String startDate,
      final String endDate, final String orderBy, final String order,
      final int pageSize, final int start, final String buyhouseorgid,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select b.head_id, t.biz_date "
              + " from  pl203 t1, pl114 b,pl202 t "
              + " where t.biz_st=6 and t.flow_head_id = t1.flow_head_id  "
              + " and t1.contract_id = b.contract_id   "
              + " and t.biz_date between ? and ?  and t.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
            hql = hql + " and b.head_id='" + buyhouseorgid + "'";
          }
          hql = hql + "group by b.head_id,t.biz_date ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);

          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl 明细账 带分页的，求年份的
   * 
   * @param borrowercontractid
   * @param bizdateB
   * @param bizdateE
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List findFirstParticularg(final String borrowercontractid,
      final String bizdateB, final String bizdateE, final String orderBy,
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
          String hql = "select substr(pl202.biz_date, 0, 4),sum(pl203.real_corpus),"
              + " sum(pl203.real_interest),sum(pl203.real_punish_interest) "
              + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id"
              + " and pl203.contract_id=? and pl202.biz_date between ? and ? and pl202.loan_bank_id "
              + securityInfo.getDkSecuritySQL()
              + " group by substr(pl202.biz_date, 0, 4) ";

          String ob = orderBy;
          if (ob == null)
            ob = " sum(pl203.real_corpus) ";

          String od = orderother;
          if (od == null)
            od = "DESC";

          hql = hql + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, borrowercontractid);
          query.setParameter(1, bizdateB);
          query.setParameter(2, bizdateE);

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {

              ParticularglDTO particularglDTO = new ParticularglDTO();
              if (obj[0] != null) {
                particularglDTO.setOcyear(obj[0].toString());
              }
              if (obj[1] != null) {
                particularglDTO.setThispaymoney(obj[1].toString());
              } else {
                particularglDTO.setThispaymoney("0.00");
              }
              if (obj[2] != null) {
                particularglDTO.setThisinterest(obj[2].toString());
              } else {
                particularglDTO.setThisinterest("0.00");
              }
              if (obj[3] != null) {
                particularglDTO.setThispunishinterest(obj[3].toString());
              } else {
                particularglDTO.setThispunishinterest("0.00");
              }

              temp_list.add(particularglDTO);
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
   * hanl 明细账 累计借方发生额中的 发放金(初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryOcMoney(final String bizdate, final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 累计借方发生额中的 错账金额(初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryWrongMoney(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_type = 12 and pl202.wrong_biz_type = 1 "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 挂账金额 (初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanrepay(final String bizdate, final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('3','4','5','13') "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 挂账金额 错账类型的(初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanrepayWrong(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money)from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('2','5') "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 保证金(初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryBallbalance(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('14','15') "
                  + "and pl202.biz_date<? and pl203.contract_id=?  ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 呆账核销金额 (初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal querybaddebtmoney(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('6','7','8','9') "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 呆账核销金额 错账类型的 (初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal querybaddebtmoneyWrong(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 "
                  + "and pl202.wrong_biz_type in ('6','7') "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 累计借方发生额中的 发放金(本期)
   * 
   * @param ocyear
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public String queryThisOcMoney(final String bizdateB, final String bizdateE,
      final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date between ? and ?  and pl203.contract_id=? and substr(pl202.biz_date, 0, 4)=? group by substr(pl202.biz_date, 0, 4)";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 累计借方发生额中的 错账金额(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public String queryThisWrongOcMoney(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type = 1 "
                  + "and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 4)=?  group by substr(pl202.biz_date, 0, 4)";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 挂账金额(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public String queryThisLoanrepay(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('3','4','5','13') "
                  + "and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 4)=? group by substr(pl202.biz_date, 0, 4) ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }

            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 挂账金额错账类型(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public String queryThisWrongLoanrepay(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('2','5') "
                  + "and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 4)=? group by substr(pl202.biz_date, 0, 4) ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 保证金(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public String queryThisballbalance(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('14','15')"
                  + " and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 4)=? group by substr(pl202.biz_date, 0, 4) ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 呆账核销金额(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public String queryThisbadocmoney(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('6','7','8','9') "
                  + "and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 4)=? group by substr(pl202.biz_date, 0, 4) ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 呆账核销金额 错账账类型的(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public String queryThisbadwocmoney(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money)"
                  + " from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('6','7')"
                  + " and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 4)=? group by substr(pl202.biz_date, 0, 4) ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 贷方发生额(初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryPaymoney(final String bizdate, final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.real_corpus) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 累计借方发生额中的 发放金(期末)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryOcMoneylast(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date<=? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 累计借方发生额中的 错账金额(期末)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryWrongMoneylast(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_type = 12 and pl202.wrong_biz_type = 1 "
                  + "and pl202.biz_date<=? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 贷方发生额(末期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryPaymoneylast(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.real_corpus) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_date<=? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 不带分页的 明细账
   * 
   * @param borrowercontractid
   * @param bizdateB
   * @param bizdateE
   * @param securityInfo
   * @return
   */
  public List findFirstParticulargListAll(final String borrowercontractid,
      final String bizdateB, final String bizdateE,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select substr(pl202.biz_date, 0, 4),sum(pl203.real_corpus),"
              + " sum(pl203.real_interest),sum(pl203.real_punish_interest) "
              + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id"
              + " and pl203.contract_id=? and pl202.biz_date between ? and ? and pl202.loan_bank_id "
              + securityInfo.getDkSecuritySQL()
              + " group by substr(pl202.biz_date, 0, 4) ";

          hql = hql;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, borrowercontractid);
          query.setParameter(1, bizdateB);
          query.setParameter(2, bizdateE);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {

              ParticularglDTO particularglDTO = new ParticularglDTO();
              if (obj[0] != null) {
                particularglDTO.setOcyear(obj[0].toString());
              }
              if (obj[1] != null) {
                particularglDTO.setThispaymoney(obj[1].toString());
              } else {
                particularglDTO.setThispaymoney("0.00");
              }
              if (obj[2] != null) {
                particularglDTO.setThisinterest(obj[2].toString());
              } else {
                particularglDTO.setThisinterest("0.00");
              }
              if (obj[3] != null) {
                particularglDTO.setThispunishinterest(obj[3].toString());
              } else {
                particularglDTO.setThispunishinterest("0.00");
              }

              temp_list.add(particularglDTO);
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
   * hanl 明细账 带分页的，求月份的
   * 
   * @param borrowercontractid
   * @param bizdateB
   * @param bizdateE
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List findFirstParticulargMonth(final String borrowercontractid,
      final String bizdateB, final String bizdateE, final String orderBy,
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
          String hql = "select substr(pl202.biz_date, 0, 6),sum(pl203.real_corpus),"
              + " sum(pl203.real_interest),sum(pl203.real_punish_interest) "
              + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id"
              + " and pl203.contract_id=? and pl202.biz_date between ? and ? and pl202.loan_bank_id "
              + securityInfo.getDkSecuritySQL()
              + " group by substr(pl202.biz_date, 0, 6) ";

          String ob = orderBy;
          if (ob == null)
            ob = " substr(pl202.biz_date, 0, 6) ";

          String od = orderother;
          if (od == null)
            od = "DESC";

          hql = hql + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, borrowercontractid);
          query.setParameter(1, bizdateB);
          query.setParameter(2, bizdateE);

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {

              ParticularglDTO particularglDTO = new ParticularglDTO();
              if (obj[0] != null) {
                particularglDTO.setOcyear(obj[0].toString());
              }
              if (obj[1] != null) {
                particularglDTO.setThispaymoney(obj[1].toString());
              } else {
                particularglDTO.setThispaymoney("0.00");
              }
              if (obj[2] != null) {
                particularglDTO.setThisinterest(obj[2].toString());
              } else {
                particularglDTO.setThisinterest("0.00");
              }
              if (obj[3] != null) {
                particularglDTO.setThispunishinterest(obj[3].toString());
              } else {
                particularglDTO.setThispunishinterest("0.00");
              }

              temp_list.add(particularglDTO);
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
   * hanl 明细账 此方法求发生月里的 累计借方发生额中的 发放金(初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryOcMoneyMonth(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 累计借方发生额中的 错账金额(初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryWrongMoneyMonth(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_type = 12 and pl202.wrong_biz_type = 1 "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 贷方发生额(初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryPaymoneyMonth(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.real_corpus) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 累计借方发生额中的 发放金(期末)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryOcMoneylastMonth(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date<=? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 累计借方发生额中的 错账金额(期末)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryWrongMoneylastMonth(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_type = 12 and pl202.wrong_biz_type = 1 "
                  + "and pl202.biz_date<=? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 贷方发生额(末期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryPaymoneylastMonth(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.real_corpus) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_date<=? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 累计借方发生额中的 发放金(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public String queryThisOcMoneyMonth(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date between ? and ?  and pl203.contract_id=? and substr(pl202.biz_date, 0, 6)=? group by substr(pl202.biz_date, 0, 6)";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 累计借方发生额中的 错账金额(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public String queryThisWrongOcMoneyMonth(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type = 1 "
                  + "and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 6)=?  group by substr(pl202.biz_date, 0, 6)";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 挂账金额(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */

  public String queryThisLoanrepayMonth(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('3','4','5','13') "
                  + "and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 6)=? group by substr(pl202.biz_date, 0, 6) ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }

            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 挂账金额错账类型(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */

  public String queryThisWrongLoanrepayMonth(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('2','5') "
                  + "and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 6)=? group by substr(pl202.biz_date, 0, 6) ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 保证金(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */

  public String queryThisballbalanceMonth(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('14','15')"
                  + " and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 6)=? group by substr(pl202.biz_date, 0, 6) ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 呆账核销金额(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */

  public String queryThisbadocmoneyMonth(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('6','7','8','9') "
                  + "and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 6)=? group by substr(pl202.biz_date, 0, 6) ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生月里的 呆账核销金额 错账账类型的(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */

  public String queryThisbadwocmoneyMonth(final String bizdateB,
      final String bizdateE, final String contractid, final String ocyear) {
    String happenMoney = "0.00";
    try {
      happenMoney = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money)"
                  + " from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('6','7')"
                  + " and pl202.biz_date between ? and ? and pl203.contract_id =? and substr(pl202.biz_date, 0, 6)=? group by substr(pl202.biz_date, 0, 6) ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, bizdateE);
              query.setParameter(2, contractid);
              query.setParameter(3, ocyear);
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                String money = "0.00";
                return money;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 不带分页的 明细账 此方法求发生月里的
   * 
   * @param borrowercontractid
   * @param bizdateB
   * @param bizdateE
   * @param securityInfo
   * @return
   */
  public List findFirstParticulargListAllMonth(final String borrowercontractid,
      final String bizdateB, final String bizdateE,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select substr(pl202.biz_date, 0, 6),sum(pl203.real_corpus),"
              + " sum(pl203.real_interest),sum(pl203.real_punish_interest) "
              + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id"
              + " and pl203.contract_id=? and pl202.biz_date between ? and ? and pl202.loan_bank_id "
              + securityInfo.getDkSecuritySQL()
              + " group by substr(pl202.biz_date, 0, 6) ";

          hql = hql;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, borrowercontractid);
          query.setParameter(1, bizdateB);
          query.setParameter(2, bizdateE);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {

              ParticularglDTO particularglDTO = new ParticularglDTO();
              if (obj[0] != null) {
                particularglDTO.setOcyear(obj[0].toString());
              }
              if (obj[1] != null) {
                particularglDTO.setThispaymoney(obj[1].toString());
              } else {
                particularglDTO.setThispaymoney("0.00");
              }
              if (obj[2] != null) {
                particularglDTO.setThisinterest(obj[2].toString());
              } else {
                particularglDTO.setThisinterest("0.00");
              }
              if (obj[3] != null) {
                particularglDTO.setThispunishinterest(obj[3].toString());
              } else {
                particularglDTO.setThispunishinterest("0.00");
              }

              temp_list.add(particularglDTO);
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
   * hanl 明细账 带分页的，求日的
   * 
   * @param borrowercontractid
   * @param bizdateB
   * @param bizdateE
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List findFirstParticulargDay(final String borrowercontractid,
      final String bizdateB, final String bizdateE, final String orderBy,
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
          String hql = "select pl202.biz_date,sum(pl203.real_corpus),"
              + " sum(pl203.real_interest),sum(pl203.real_punish_interest) "
              + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id"
              + " and pl203.contract_id=? and pl202.biz_date between ? and ? and pl202.loan_bank_id "
              + securityInfo.getDkSecuritySQL() + " group by pl202.biz_date ";

          String ob = orderBy;
          if (ob == null)
            ob = " pl202.biz_date ";

          String od = orderother;
          if (od == null)
            od = "DESC";

          hql = hql + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, borrowercontractid);
          query.setParameter(1, bizdateB);
          query.setParameter(2, bizdateE);

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {

              ParticularglDTO particularglDTO = new ParticularglDTO();
              if (obj[0] != null) {
                particularglDTO.setOcyear(obj[0].toString());
              }
              if (obj[1] != null) {
                particularglDTO.setThispaymoney(obj[1].toString());
              } else {
                particularglDTO.setThispaymoney("0.00");
              }
              if (obj[2] != null) {
                particularglDTO.setThisinterest(obj[2].toString());
              } else {
                particularglDTO.setThisinterest("0.00");
              }
              if (obj[3] != null) {
                particularglDTO.setThispunishinterest(obj[3].toString());
              } else {
                particularglDTO.setThispunishinterest("0.00");
              }

              temp_list.add(particularglDTO);
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
   * hanl 明细账 此方法求发生日里的 累计借方发生额中的 发放金(初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryOcMoneyDay(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 累计借方发生额中的 错账金额(初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryWrongMoneyDay(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_type = 12 and pl202.wrong_biz_type = 1 "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 贷方发生额(初期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryPaymoneyDay(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.real_corpus) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_date<? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 累计借方发生额中的 发放金(期末)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryOcMoneylastDay(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date<=? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 累计借方发生额中的 错账金额(期末)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryWrongMoneylastDay(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_type = 12 and pl202.wrong_biz_type = 1 "
                  + "and pl202.biz_date<=? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 贷方发生额(末期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryPaymoneylastDay(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.real_corpus) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_date<=? and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 累计借方发生额中的 发放金(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryThisOcMoneyDay(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date = ?  and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 累计借方发生额中的 错账金额(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryThisWrongOcMoneyDay(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type = 1 "
                  + "and pl202.biz_date = ? and pl203.contract_id =? ";
              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);

              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 挂账金额(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryThisLoanrepayDay(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('3','4','5','13') "
                  + "and pl202.biz_date = ? and pl203.contract_id =? ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 挂账金额错账类型(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryThisWrongLoanrepayDay(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('2','5') "
                  + "and pl202.biz_date = ? and pl203.contract_id =? ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);

              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 保证金(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryThisballbalanceDay(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('14','15')"
                  + " and pl202.biz_date = ? and pl203.contract_id =? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 呆账核销金额(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryThisbadocmoneyDay(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('6','7','8','9') "
                  + "and pl202.biz_date = ? and pl203.contract_id =? ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 明细账 此方法求发生日里的 呆账核销金额 错账账类型的(本期)
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryThisbadwocmoneyDay(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money)"
                  + " from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('6','7')"
                  + " and pl202.biz_date = ? and pl203.contract_id =? ";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 不带分页的 明细账 此方法求发生日里的
   * 
   * @param borrowercontractid
   * @param bizdateB
   * @param bizdateE
   * @param securityInfo
   * @return
   */
  public List findFirstParticulargListAllDay(final String borrowercontractid,
      final String bizdateB, final String bizdateE,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pl202.biz_date,sum(pl203.real_corpus),"
              + " sum(pl203.real_interest),sum(pl203.real_punish_interest) "
              + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id"
              + " and pl203.contract_id=? and pl202.biz_date between ? and ? and pl202.loan_bank_id "
              + securityInfo.getDkSecuritySQL() + " group by pl202.biz_date ";

          hql = hql;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, borrowercontractid);
          query.setParameter(1, bizdateB);
          query.setParameter(2, bizdateE);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {

              ParticularglDTO particularglDTO = new ParticularglDTO();
              if (obj[0] != null) {
                particularglDTO.setOcyear(obj[0].toString());
              }
              if (obj[1] != null) {
                particularglDTO.setThispaymoney(obj[1].toString());
              } else {
                particularglDTO.setThispaymoney("0.00");
              }
              if (obj[2] != null) {
                particularglDTO.setThisinterest(obj[2].toString());
              } else {
                particularglDTO.setThisinterest("0.00");
              }
              if (obj[3] != null) {
                particularglDTO.setThispunishinterest(obj[3].toString());
              } else {
                particularglDTO.setThispunishinterest("0.00");
              }

              temp_list.add(particularglDTO);
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
   * 查询批量复核，撤消批量复合头表PL202 ID 吴迪
   * 
   * @param securityInfo
   * @param docNum
   * @param contractId
   * @param loanKouAcc
   * @param borrowerName
   * @param makePerson
   * @param bizType
   * @param bizSt
   * @param loanBankName
   * @param beginBizDate
   * @param endBizDate
   */
  public List queryFlowHeadIdByCriterions(final SecurityInfo securityInfo,
      final String docNum, final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select distinct a.flow_head_id "
              + " from pl202 a, pl203 b, pl110 c"
              + " where a.flow_head_id = b.flow_head_id"
              + "  and b.contract_id = c.contract_id"
              + " and a.biz_type in"
              + "  ('1', '2', '3', '4', '5', '6', '7', '8', '9', '12', '13', '14')";

          String criterion = "";
          Vector parameters = new Vector();

          if (docNum != null && !"".equals(docNum)) {
            criterion += " a.doc_num = ? and ";
            parameters.add(docNum);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " b.contract_id  = ? and ";
            parameters.add(contractId);
          }

          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " b.loan_kou_acc = ? and ";
            parameters.add(loanKouAcc);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " c.borrower_name = ? and ";
            parameters.add(borrowerName);
          }
          if (makePerson != null && !"".equals(makePerson)) {
            criterion += " a.make_person = ? and ";
            parameters.add(makePerson);
          }
          if (bizType != null && !"".equals(bizType)) {
            criterion += " a.biz_type = ? and ";
            parameters.add(bizType);
          }

          if (bizSt != null && !"".equals(bizSt)) {
            criterion += "  a.biz_st = ? and ";
            parameters.add(bizSt);
          }
          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " a.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }
          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " a.biz_date >= ? and ";
            parameters.add(beginBizDate);
          }
          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " a.biz_date <= ? and ";
            parameters.add(endBizDate);
          }
          if (criterion.length() != 0)
            criterion = " and " + " a.loan_bank_id  "
                + securityInfo.getDkSecuritySQL() + " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion;
          Query query = session.createSQLQuery(sql);
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
   * yuqf 2007-10-30 利息总账 按办事处、银行查询 期初利息
   * 
   * @param office
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByDatepre1_Int(final String office,
      final String bankId, final String startDate,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl202 t1,pl002 t2 "
                  + " where t1.loan_bank_id=t2.loan_bank_id "
                  + " and t1.biz_date<? and t1.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t1.loan_bank_id='" + bankId + "'";
              }
              if (office != null && !"".equals(office)) {
                hql = hql + " and t2.office='" + office + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-10-30 利息总账 按办事处、银行查询 期初逾期利息
   * 
   * @param office
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByDatepre2_Int(final String office,
      final String bankId, final String startDate,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_overdue_interest) "
                  + " from pl202 t1,pl002 t2 "
                  + " where t1.loan_bank_id=t2.loan_bank_id "
                  + " and t1.biz_date<? and t1.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t1.loan_bank_id='" + bankId + "'";
              }
              if (office != null && !"".equals(office)) {
                hql = hql + " and t2.office='" + office + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-10-30 利息总账 按办事处、银行查询 期初逾期利息
   * 
   * @param office
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByDatepre2_P(final String office,
      final String bankId, final String startDate,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_punish_interest) "
                  + " from pl202 t1,pl002 t2 "
                  + " where t1.loan_bank_id=t2.loan_bank_id "
                  + " and t1.biz_date<? and t1.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t1.loan_bank_id='" + bankId + "'";
              }
              if (office != null && !"".equals(office)) {
                hql = hql + " and t2.office='" + office + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-10-30 利息总账 按办事处、银行查询 本期利息
   * 
   * @param office
   * @param bankId
   * @param startDate
   * @param endDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByDateths1_Int(final String office,
      final String bankId, final String startDate, final String endDate,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select sum(t1.real_interest) "
                  + " from pl202 t1,pl002 t2 "
                  + " where t1.loan_bank_id=t2.loan_bank_id "
                  + " and t1.biz_date between ? and ? and t1.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t1.loan_bank_id='" + bankId + "'";
              }
              if (office != null && !"".equals(office)) {
                hql = hql + " and t2.office='" + office + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-10-30 利息总账 按办事处、银行查询 逾期本期利息
   * 
   * @param office
   * @param bankId
   * @param startDate
   * @param endDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByDateths2_Int(final String office,
      final String bankId, final String startDate, final String endDate,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select sum(t1.real_overdue_interest) "
                  + " from pl202 t1,pl002 t2 "
                  + " where t1.loan_bank_id=t2.loan_bank_id "
                  + " and t1.biz_date between ? and ?  and t1.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t1.loan_bank_id='" + bankId + "'";
              }
              if (office != null && !"".equals(office)) {
                hql = hql + " and t2.office='" + office + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-10-30 利息总账 按办事处、银行查询 逾期本期利息
   * 
   * @param office
   * @param bankId
   * @param startDate
   * @param endDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByDateths2_P(final String office,
      final String bankId, final String startDate, final String endDate,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select sum(t1.real_punish_interest) "
                  + " from pl202 t1,pl002 t2 "
                  + " where t1.loan_bank_id=t2.loan_bank_id "
                  + " and t1.biz_date between ? and ?  and t1.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t1.loan_bank_id='" + bankId + "'";
              }
              if (office != null && !"".equals(office)) {
                hql = hql + " and t2.office='" + office + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-10-30 利息总账 按办事处、银行查询 期末利息
   * 
   * @param office
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByDateend1_Int(final String office,
      final String bankId, final String startDate,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl202 t1,pl002 t2 "
                  + " where t1.loan_bank_id=t2.loan_bank_id "
                  + " and t1.biz_date<=?";

              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t1.loan_bank_id='" + bankId + "'";
              }
              if (office != null && !"".equals(office)) {
                hql = hql + " and t2.office='" + office + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-10-30 利息总账 按办事处、银行查询 期末利息
   * 
   * @param office
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByDateend2_Int(final String office,
      final String bankId, final String startDate,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_overdue_interest) "
                  + " from pl202 t1,pl002 t2 "
                  + " where t1.loan_bank_id=t2.loan_bank_id "
                  + " and t1.biz_date<=?";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t1.loan_bank_id='" + bankId + "'";
              }
              if (office != null && !"".equals(office)) {
                hql = hql + " and t2.office='" + office + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * 根据条件查询核销收回维护合计信息
   * 
   * @author 吴迪 2007-10-18
   * @param securityInfo
   * @return
   */
  public DestoryBackTbTotleDTO queryDestoryBackTbShowListTotleByCriterions(
      final SecurityInfo securityInfo, final String loanKouAcc,
      final String contractId, final String borrowerName, final String cardNum,
      final String loanBankName, final String bizSt, final String docNum) {
    DestoryBackTbTotleDTO destoryBackTbTotleDTO = new DestoryBackTbTotleDTO();
    try {
      destoryBackTbTotleDTO = (DestoryBackTbTotleDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(*) as count,"
                  + "sum (p202.occur_money)   as occurMoney "
                  + " from pl202 p202, pl203 p203, pl110 p110 "
                  + " where p202.flow_head_id = p203.flow_head_id"
                  + " and p203.contract_id = p110.contract_id "
                  + " and p202.biz_type in (8, 9) ";

              String criterion = "";
              Vector parameters = new Vector();

              if (loanKouAcc != null && !"".equals(loanKouAcc)) {
                criterion += " p203.loan_kou_acc = ? and ";
                parameters.add(loanKouAcc);
              }

              if (contractId != null && !"".equals(contractId)) {
                criterion += " p203.contract_id = ? and ";
                parameters.add(contractId);
              }

              if (docNum != null && !"".equals(docNum)) {
                criterion += " p202.doc_num = ? and ";
                parameters.add(docNum);
              }
              if (borrowerName != null && !"".equals(borrowerName)) {
                criterion += " p110.borrower_name = ? and ";
                parameters.add(borrowerName);
              }
              if (cardNum != null && !"".equals(cardNum)) {
                criterion += " p110.card_num= ? and ";
                parameters.add(cardNum);
              }
              if (loanBankName != null && !"".equals(loanBankName)) {
                criterion += " p202.loan_bank_id = ? and ";
                parameters.add(loanBankName);
              }

              if (bizSt != null && !"".equals(bizSt)) {
                criterion += "  p202.biz_st = ? and ";
                parameters.add(bizSt);
              } else {
                criterion += "  p202.biz_st in (4,5,6) and ";
              }
              if (criterion.length() != 0)
                criterion = " and   p202.loan_bank_id "
                    + securityInfo.getDkSecuritySQL() + " and  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              DestoryBackTbTotleDTO destoryBackTbTotleDTO = new DestoryBackTbTotleDTO();
              Object[] obj = (Object[]) query.uniqueResult();
              if (obj[0] != null && !"".equals(obj[0])) {
                destoryBackTbTotleDTO.setCount(new Integer(obj[0].toString())
                    .intValue());
              }
              if (obj[1] != null && !"".equals(obj[1])) {
                destoryBackTbTotleDTO.setReclaimCorpusTotle(new BigDecimal(
                    obj[1].toString()));
              }
              return destoryBackTbTotleDTO;
            }

          });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return destoryBackTbTotleDTO;
  }

  /**
   * 根据条件查询扎账列表
   * 
   * @author shiy 2007-10-2９ 模仿业务复核
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @param securityInfo
   * @param docNum
   * @param contractId
   * @param loanKouAcc
   * @param borrowerName
   * @param makePerson
   * @param bizType
   * @param bizSt
   * @param loanBankName
   * @param beginBizDate
   * @param endBizDate
   * @return
   */
  public List queryClearaccoutShowListByCriterions(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String userName) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select docnum,loankouacc, contractId,borrowername,bizType,occurMoney,realcorpus,reclaimAccrual,realPunishInterest,badDebt,putUpMoney,bail,bailAccrual,bizSt,bizDate,wrongBizType,loanBankID, makePerson,flowheadID from "
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
              + "0 as bail,"
              + "0 as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID"
              + " from PL202 pl202"
              + " where pl202.biz_type = '5'"
              + " or (pl202.biz_type = '12' and pl202.wrong_biz_type = '5')"
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
              + " pl202.flow_head_id as flowheadID"
              + " from PL203 pl203, PL202 pl202,PL110 pl110"
              + " where pl202.flow_head_id = pl203.flow_head_id  and pl110.contract_id=pl203.contract_id"
              + " and ((pl202.biz_type in"
              + " ('1', '2', '3', '4', '6', '7', '8', '9', '13', '14')) or"
              + " (pl202.biz_type = '12' and"
              + " pl202.wrong_biz_type in ('1', '2', '6', '7')) or"
              + " (pl202.biz_type = '12' and pl202.wrong_biz_type is null))"
              + ")";
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
            // System.out.println("最后天+++++++" + endBizDate);
          }
          if (userName != null && !"".equals(userName)) {
            criterion += " makePerson in ( " + userName + " ) and ";
            // parameters.add(userName);
          }
          if (criterion.length() != 0)
            criterion = " where " + " loanBankID  "
                + securityInfo.getDkSecuritySQL() + " and "
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
          // System.out.println("最后天+++++++");
          // 业务复核列表
          List bizCheckShowList = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            ClearaccountDTO clearaccountDTO = new ClearaccountDTO();
            obj = (Object[]) iterate.next();
            // 凭证编号PL202.DOC_NUM
            if (obj[0] != null && !obj[0].equals(""))
              clearaccountDTO.setDocNum(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              // 贷款账号PL203.LOAN_KOU_ACC
              clearaccountDTO.setLoanKouAcc(obj[1].toString());
            // 合同编号PL203.CONTRACT_ID
            if (obj[2] != null && !obj[2].equals(""))
              clearaccountDTO.setContractId(obj[2].toString());
            // 借款人姓名PL110.BORROWER_NAME
            if (obj[3] != null && !obj[3].equals(""))
              clearaccountDTO.setBorrowerName(obj[3].toString());
            // 业务类型L202.BIZ_TYPE
            if (obj[4] != null && !obj[4].equals("")) {
              clearaccountDTO.setBizType(obj[4].toString());
              clearaccountDTO.setOriginalitybizType(obj[4].toString());
            }
            // 发放金额L202.OCCUR_MONEY(当业务类型=1或业务类型=12，WRONG_BIZ_TYPE=1时)
            if (obj[5] != null && !obj[5].equals("")) {
              if (obj[4].equals("1")) {
                clearaccountDTO.setOccurMoney(obj[5].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("1")) {
                    clearaccountDTO.setOccurMoney(obj[5].toString());
                  }
                }
              }
            }
            // 回收本金PL202.REAL_CORPUS+PL202.REAL_OVERDUE_CORPUS(当业务类型=2,3,4,5,6,7,12时)
            if (obj[6] != null && !obj[6].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {

                clearaccountDTO.setReclaimCorpus(obj[6].toString());
              }
            }
            // 回收利息PL202.REAL_INTEREST+PL202.REAL_OVERDUE_INTEREST(当业务类型=2,3,4,5,6,7,12时)
            if (obj[7] != null && !obj[7].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {
                clearaccountDTO.setReclaimAccrual(obj[7].toString());
              }
            }
            // 回收罚息PL202.REAL_PUNISH_INTEREST(当业务类型=2,3,4,5,6,7,12时)
            if (obj[8] != null && !obj[8].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {
                clearaccountDTO.setRealPunishInterest(obj[8].toString());
              }
            }
            // 呆账核销金额PL202.OCCUR_MONEY(当业务类型=6,7,8,9或业务类型=12，WRONG_BIZ_TYPE=6或业务类型=12，WRONG_BIZ_TYPE=７)
            if (obj[9] != null && !obj[9].equals("")) {
              if (obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("8") || obj[4].equals("9")) {
                clearaccountDTO.setBadDebt(obj[9].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("6") || obj[15].equals("7")) {
                    clearaccountDTO.setBadDebt(obj[9].toString());
                  }
                }
              }
            }
            // 挂账金额PL202.OCCUR_MONEY(当业务类型=2,3,4,5,13或业务类型=12，WRONG_BIZ_TYPE=6或业务类型=2，WRONG_BIZ_TYPE=5)
            if (obj[10] != null && !obj[10].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("13")) {
                clearaccountDTO.setPutUpMoney(obj[10].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("2") || obj[15].equals("5")) {
                    clearaccountDTO.setPutUpMoney(obj[10].toString());
                  }
                }
              }
            }
            // 保证金PL202.OCCUR_MONEY(当业务类型=14时)
            if (obj[11] != null && !obj[11].equals("")) {
              if (obj[4].equals("14")) {
                clearaccountDTO.setBail(obj[11].toString());
              }
            }
            // 保证金利息(当业务类型=14时)
            if (obj[12] != null && !obj[12].equals("")) {
              if (obj[4].equals("14")) {
                clearaccountDTO.setBailAccrual(obj[12].toString());
              }
            }
            // 业务状态PL202.BIZ_ST
            if (obj[13] != null && !obj[13].equals(""))
              clearaccountDTO.setBizSt(obj[13].toString());
            // 办理日期PL202.BIZ_DATE
            if (obj[14] != null && !obj[14].equals(""))
              clearaccountDTO.setBizDate(obj[14].toString());
            // pl202.flow_head_id
            if (obj[18] != null && !obj[18].equals(""))
              clearaccountDTO.setFlowHeadId(obj[18].toString());

            // 枚举转换业务状态
            try {
              clearaccountDTO.setBizSt(BusiTools.getBusiValue(Integer
                  .parseInt("" + clearaccountDTO.getBizSt()),
                  BusiConst.PLBUSINESSSTATE));
            } catch (Exception e) {
              e.printStackTrace();
            }

            // 枚举转换业务类型
            try {
              clearaccountDTO.setBizType(BusiTools.getBusiValue(Integer
                  .parseInt("" + clearaccountDTO.getBizType()),
                  BusiConst.PLBUSINESSTYPE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            if (clearaccountDTO.getRealPunishInterest() != null
                && clearaccountDTO.getReclaimAccrual() != null
                && clearaccountDTO.getReclaimCorpus() != null) {
              // 计算回收金额=回收金额+回收利息+回收罚息
              clearaccountDTO.setSumReclaimMoney((new BigDecimal(
                  clearaccountDTO.getRealPunishInterest())).add(
                  new BigDecimal(clearaccountDTO.getReclaimAccrual())).add(
                  new BigDecimal(clearaccountDTO.getReclaimCorpus()))
                  .toString());
            }
            bizCheckShowList.add(clearaccountDTO);
          }
          return bizCheckShowList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据条件查询全部扎账记录
   * 
   * @author shiy 2008-03-26
   * @param securityInfo
   * @param docNum
   * @param contractId
   * @param loanKouAcc
   * @param borrowerName
   * @param makePerson
   * @param bizType
   * @param bizSt
   * @param loanBankName
   * @param beginBizDate
   * @param endBizDate
   * @return
   */
  public List queryClearaccoutListByCriterions(final SecurityInfo securityInfo,
      final String docNum, final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String userName) {
    List list = new ArrayList();
    try {

      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select flowheadID from "
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
              + "0 as bail,"
              + "0 as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID"
              + " from PL202 pl202"
              + " where pl202.biz_type = '5'"
              + " or (pl202.biz_type = '12' and pl202.wrong_biz_type = '5')"
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
              + " pl202.flow_head_id as flowheadID"
              + " from PL203 pl203, PL202 pl202,PL110 pl110"
              + " where pl202.flow_head_id = pl203.flow_head_id  and pl110.contract_id=pl203.contract_id"
              + " and ((pl202.biz_type in"
              + " ('1', '2', '3', '4', '6', '7', '8', '9', '13', '14')) or"
              + " (pl202.biz_type = '12' and"
              + " pl202.wrong_biz_type in ('1', '2', '6', '7')) or"
              + " (pl202.biz_type = '12' and pl202.wrong_biz_type is null))"
              + ")";
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
            // System.out.println("最后天+++++++" + endBizDate);
          }
          if (userName != null && !"".equals(userName)) {
            criterion += " makePerson in ( " + userName + " ) and ";
            // parameters.add(userName);
          }
          if (criterion.length() != 0)
            criterion = " where " + " loanBankID  "
                + securityInfo.getDkSecuritySQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          // System.out.println("最后天+++++++");
          // 业务复核列表
          List headlist = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj = null;
          while (it.hasNext()) {
            obj = (Object) it.next();
            headlist.add(obj.toString());
          }
          return headlist;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  /**
   * 将要全部扎账的记录暂时封存到oracle自带的临时表中以便在存储过程中调用
   */
  public void insertTempFlowHeadId(final String flowHeadId)
      throws BusinessException {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      String sql = "insert into tempfolwheadid(id)" + "values ('" + flowHeadId
          + "')";
      Statement statement = conn.createStatement();
      statement.executeUpdate(sql);
      ;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("全部扎账失败！");
    }

  }

  /**
   * 根据条件查询扎账列表
   * 
   * @author shiy 2007-10-2９ 模仿业务复核
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @param securityInfo
   * @param docNum
   * @param contractId
   * @param loanKouAcc
   * @param borrowerName
   * @param makePerson
   * @param bizType
   * @param bizSt
   * @param loanBankName
   * @param beginBizDate
   * @param endBizDate
   * @return
   */
  public List queryClearaccoutShowListByCriterions_LJ(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String userName) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select docnum,loankouacc, contractId,borrowername,bizType,occurMoney,realcorpus,"
              + "reclaimAccrual,realPunishInterest,badDebt,putUpMoney,bail,bailAccrual,bizSt,bizDate,wrongBizType,"
              + "loanBankID, makePerson,flowheadID,real_corpus,real_interest,real_punish_interest,loan_bank_id,batch_num from "
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
              + "0 as bail,"
              + "0 as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " nvl(pl202.real_corpus,0) as real_corpus,"
              + " nvl(pl202.real_interest,0) as real_interest,"
              + " nvl(pl202.real_punish_interest,0) as real_punish_interest,"
              + " pl202.loan_bank_id as loan_bank_id, "
              + " pl202.batch_num as batch_num "
              + " from PL202 pl202"
              + " where pl202.biz_type = '5'"
              + " or (pl202.biz_type = '12' and pl202.wrong_biz_type = '5')"
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
              + " nvl(pl202.real_corpus,0) as real_corpus,"
              + " nvl(pl202.real_interest,0) as real_interest,"
              + " nvl(pl202.real_punish_interest,0) as real_punish_interest,"
              + " pl202.loan_bank_id as loan_bank_id, "
              + " pl202.batch_num as batch_num "
              + " from PL203 pl203, PL202 pl202,PL110 pl110"
              + " where pl202.flow_head_id = pl203.flow_head_id  and pl110.contract_id=pl203.contract_id"
              + " and ((pl202.biz_type in"
              + " ('1', '2', '3', '4', '13')) or"
              + " (pl202.biz_type = '12' and"
              + " pl202.wrong_biz_type in ('1', '2', '6', '7')) or"
              + " (pl202.biz_type = '12' and pl202.wrong_biz_type is null))"
              + ") where "
              + " loanBankID  "
              + securityInfo.getDkSecuritySQL()
              + " and bizSt = 6 ";
          String criterion = "";
          Vector parameters = new Vector();
          String ob = orderBy;
          if (ob == null)
            ob = " bizDate ";

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

          // if (bizSt != null && !"".equals(bizSt)) {
          // criterion += " bizSt = ? and ";
          // parameters.add(bizSt);
          // } else {
          // criterion += " bizSt in (4,5,6) and ";
          // }
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
            // System.out.println("最后天+++++++" + endBizDate);
          }
          // if (userName != null && !"".equals(userName)) {
          // criterion += " makePerson in ( "+userName+" ) and ";
          // // parameters.add(userName);
          // }
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
          // System.out.println("最后天+++++++");
          // 业务复核列表
          List bizCheckShowList = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            ClearaccountDTO clearaccountDTO = new ClearaccountDTO();
            obj = (Object[]) iterate.next();
            // 凭证编号PL202.DOC_NUM
            if (obj[0] != null && !obj[0].equals(""))
              clearaccountDTO.setDocNum(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              // 贷款账号PL203.LOAN_KOU_ACC
              clearaccountDTO.setLoanKouAcc(obj[1].toString());
            // 合同编号PL203.CONTRACT_ID
            if (obj[2] != null && !obj[2].equals(""))
              clearaccountDTO.setContractId(obj[2].toString());
            // 借款人姓名PL110.BORROWER_NAME
            if (obj[3] != null && !obj[3].equals(""))
              clearaccountDTO.setBorrowerName(obj[3].toString());
            // 业务类型L202.BIZ_TYPE
            if (obj[4] != null && !obj[4].equals("")) {
              clearaccountDTO.setBizType(obj[4].toString());
              clearaccountDTO.setOriginalitybizType(obj[4].toString());
            }
            // 发放金额L202.OCCUR_MONEY(当业务类型=1或业务类型=12，WRONG_BIZ_TYPE=1时)
            if (obj[5] != null && !obj[5].equals("")) {
              if (obj[4].equals("1")) {
                clearaccountDTO.setOccurMoney(obj[5].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("1")) {
                    clearaccountDTO.setOccurMoney(obj[5].toString());
                  }
                }
              }
            }
            // 回收本金PL202.REAL_CORPUS+PL202.REAL_OVERDUE_CORPUS(当业务类型=2,3,4,5,6,7,12时)
            if (obj[6] != null && !obj[6].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {

                clearaccountDTO.setReclaimCorpus(obj[6].toString());
              }
            }
            // 回收利息PL202.REAL_INTEREST+PL202.REAL_OVERDUE_INTEREST(当业务类型=2,3,4,5,6,7,12时)
            if (obj[7] != null && !obj[7].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {
                clearaccountDTO.setReclaimAccrual(obj[7].toString());
              }
            }
            // 回收罚息PL202.REAL_PUNISH_INTEREST(当业务类型=2,3,4,5,6,7,12时)
            if (obj[8] != null && !obj[8].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {
                clearaccountDTO.setRealPunishInterest(obj[8].toString());
              }
            }
            // 呆账核销金额PL202.OCCUR_MONEY(当业务类型=6,7,8,9或业务类型=12，WRONG_BIZ_TYPE=6或业务类型=12，WRONG_BIZ_TYPE=７)
            /*
             * if (obj[9] != null && !obj[9].equals("")) { if
             * (obj[4].equals("6") || obj[4].equals("7") || obj[4].equals("8") ||
             * obj[4].equals("9")) {
             * clearaccountDTO.setBadDebt(obj[9].toString()); } else if
             * (obj[4].equals("12")) { if ((obj[15] != null &&
             * !obj[15].equals(""))) { if (obj[15].equals("6") ||
             * obj[15].equals("7")) {
             * clearaccountDTO.setBadDebt(obj[9].toString()); } } } }
             */
            // 挂账金额PL202.OCCUR_MONEY(当业务类型=2,3,4,5,13或业务类型=12，WRONG_BIZ_TYPE=6或业务类型=2，WRONG_BIZ_TYPE=5)
            if (obj[10] != null && !obj[10].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("13")) {
                clearaccountDTO.setPutUpMoney(obj[10].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("2") || obj[15].equals("5")) {
                    clearaccountDTO.setPutUpMoney(obj[10].toString());
                  }
                }
              }
            }
            // 保证金PL202.OCCUR_MONEY(当业务类型=14时)
            /*
             * if (obj[11] != null && !obj[11].equals("")) { if
             * (obj[4].equals("14")) {
             * clearaccountDTO.setBail(obj[11].toString()); } } //
             * 保证金利息(当业务类型=14时) if (obj[12] != null && !obj[12].equals("")) { if
             * (obj[4].equals("14")) {
             * clearaccountDTO.setBailAccrual(obj[12].toString()); } }
             */
            // 业务状态PL202.BIZ_ST
            if (obj[13] != null && !obj[13].equals(""))
              clearaccountDTO.setBizSt(obj[13].toString());
            // 办理日期PL202.BIZ_DATE
            if (obj[14] != null && !obj[14].equals(""))
              clearaccountDTO.setBizDate(obj[14].toString());
            // pl202.flow_head_id
            if (obj[18] != null && !obj[18].equals(""))
              clearaccountDTO.setFlowHeadId(obj[18].toString());
            // 实还本金
            if (obj[19] != null && !obj[19].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")) {
                clearaccountDTO.setRealCorpus(obj[19].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("2") || obj[15].equals("3")
                      || obj[15].equals("4") || obj[15].equals("5")) {
                    clearaccountDTO.setRealCorpus(obj[19].toString());
                  }
                }
              }
            }
            // 实还利息
            if (obj[20] != null && !obj[20].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")) {
                clearaccountDTO.setRealInterest(obj[20].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("2") || obj[15].equals("3")
                      || obj[15].equals("4") || obj[15].equals("5")) {
                    clearaccountDTO.setRealInterest(obj[20].toString());
                  }
                }
              }
            }
            // 实还罚息
            if (obj[21] != null && !obj[21].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")) {
                clearaccountDTO.setRealPunish_interest(obj[21].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("2") || obj[15].equals("3")
                      || obj[15].equals("4") || obj[15].equals("5")) {
                    clearaccountDTO.setRealPunish_interest(obj[21].toString());
                  }
                }
              }
            }
            if (obj[22] != null && !obj[22].equals("")) {
              clearaccountDTO.setLoanBank(obj[22].toString());
            }
            if (obj[23] != null && !obj[23].equals("")) {
              clearaccountDTO.setIsGjjLoanBack("是");
            } else {
              clearaccountDTO.setIsGjjLoanBack("否");
            }
            // 枚举转换业务状态
            try {
              clearaccountDTO.setBizSt(BusiTools.getBusiValue(Integer
                  .parseInt("" + clearaccountDTO.getBizSt()),
                  BusiConst.PLBUSINESSSTATE));
            } catch (Exception e) {
              e.printStackTrace();
            }

            // 枚举转换业务类型
            try {
              clearaccountDTO.setBizType(BusiTools.getBusiValue(Integer
                  .parseInt("" + clearaccountDTO.getBizType()),
                  BusiConst.PLBUSINESSTYPE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            if (clearaccountDTO.getRealPunishInterest() != null
                && clearaccountDTO.getReclaimAccrual() != null
                && clearaccountDTO.getReclaimCorpus() != null) {
              // 计算回收金额=回收金额+回收利息+回收罚息
              clearaccountDTO.setSumReclaimMoney((new BigDecimal(
                  clearaccountDTO.getRealPunishInterest())).add(
                  new BigDecimal(clearaccountDTO.getReclaimAccrual())).add(
                  new BigDecimal(clearaccountDTO.getReclaimCorpus()))
                  .toString());
            }
            bizCheckShowList.add(clearaccountDTO);
          }
          return bizCheckShowList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据条件查询扎账列表(打印)
   * 
   * @author shiy 2007-10-2９ 模仿业务复核
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @param securityInfo
   * @param docNum
   * @param contractId
   * @param loanKouAcc
   * @param borrowerName
   * @param makePerson
   * @param bizType
   * @param bizSt
   * @param loanBankName
   * @param beginBizDate
   * @param endBizDate
   * @return
   */
  public List queryClearaccoutShowListByCriterionsPrint_LJ(final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String userName) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select docnum,loankouacc, contractId,"
              + "borrowername,bizType,occurMoney,realcorpus,reclaimAccrual,"
              + "realPunishInterest,badDebt,putUpMoney,bail,bailAccrual,bizSt,"
              + "bizDate,wrongBizType,loanBankID, makePerson,flowheadID,"
              + "real_corpus,real_interest,real_punish_interest,loan_bank_id, "
              + "make_person,check_person,clear_acc_person,batch_num from "
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
              + "0 as bail,"
              + "0 as bailAccrual,"
              + "pl202.biz_st as bizSt,"
              + "pl202.biz_date as bizDate,"
              + "pl202.wrong_biz_type as wrongBizType,"
              + " pl202.loan_bank_id as loanBankID,"
              + " pl202.make_person as makePerson,"
              + " pl202.flow_head_id as flowheadID,"
              + " nvl(pl202.real_corpus,0) as real_corpus,"
              + " nvl(pl202.real_interest,0) as real_interest,"
              + " nvl(pl202.real_punish_interest,0) as real_punish_interest,"
              + " pl202.loan_bank_id as loan_bank_id, "
              + " pl202.make_person as make_person, "
              + " pl202.check_person as check_person, "
              + " pl202.clear_acc_person as clear_acc_person, "
              + " pl202.batch_num as batch_num "
              + " from PL202 pl202"
              + " where pl202.biz_type = '5'"
              + " or (pl202.biz_type = '12' and pl202.wrong_biz_type = '5')"
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
              + " nvl(pl202.real_corpus,0) as real_corpus,"
              + " nvl(pl202.real_interest,0) as real_interest,"
              + " nvl(pl202.real_punish_interest,0) as real_punish_interest,"
              + " pl202.loan_bank_id as loan_bank_id, "
              + " pl202.make_person as make_person, "
              + " pl202.check_person as check_person, "
              + " pl202.clear_acc_person as clear_acc_person, "
              + " pl202.batch_num as batch_num"
              + " from PL203 pl203, PL202 pl202,PL110 pl110"
              + " where pl202.flow_head_id = pl203.flow_head_id  and pl110.contract_id=pl203.contract_id"
              + " and ((pl202.biz_type in" + " ('1', '2', '3', '4', '13')) or"
              + " (pl202.biz_type = '12' and"
              + " pl202.wrong_biz_type in ('1', '2', '6', '7')) or"
              + " (pl202.biz_type = '12' and pl202.wrong_biz_type is null))"
              + ") where " + " loanBankID  " + securityInfo.getDkSecuritySQL()
              + " and bizSt = 6 ";
          String criterion = "";
          Vector parameters = new Vector();
          String ob = orderBy;
          if (ob == null)
            ob = " bizDate ";

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

          // if (bizSt != null && !"".equals(bizSt)) {
          // criterion += " bizSt = ? and ";
          // parameters.add(bizSt);
          // } else {
          // criterion += " bizSt in (4,5,6) and ";
          // }
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
            // System.out.println("最后天+++++++" + endBizDate);
          }
          // if (userName != null && !"".equals(userName)) {
          // criterion += " makePerson in ( "+userName+" ) and ";
          // // parameters.add(userName);
          // }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + od;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);
          List queryList = query.list();
          // System.out.println("最后天+++++++");
          // 业务复核列表
          List bizCheckShowList = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            ClearaccountDTO clearaccountDTO = new ClearaccountDTO();
            obj = (Object[]) iterate.next();
            // 凭证编号PL202.DOC_NUM
            if (obj[0] != null && !obj[0].equals(""))
              clearaccountDTO.setDocNum(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              // 贷款账号PL203.LOAN_KOU_ACC
              clearaccountDTO.setLoanKouAcc(obj[1].toString());
            // 合同编号PL203.CONTRACT_ID
            if (obj[2] != null && !obj[2].equals(""))
              clearaccountDTO.setContractId(obj[2].toString());
            // 借款人姓名PL110.BORROWER_NAME
            if (obj[3] != null && !obj[3].equals(""))
              clearaccountDTO.setBorrowerName(obj[3].toString());
            // 业务类型L202.BIZ_TYPE
            if (obj[4] != null && !obj[4].equals("")) {
              clearaccountDTO.setBizType(obj[4].toString());
              clearaccountDTO.setOriginalitybizType(obj[4].toString());
            }
            // 发放金额L202.OCCUR_MONEY(当业务类型=1或业务类型=12，WRONG_BIZ_TYPE=1时)
            if (obj[5] != null && !obj[5].equals("")) {
              if (obj[4].equals("1")) {
                clearaccountDTO.setOccurMoney(obj[5].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("1")) {
                    clearaccountDTO.setOccurMoney(obj[5].toString());
                  }
                }
              }
            }
            // 回收本金PL202.REAL_CORPUS+PL202.REAL_OVERDUE_CORPUS(当业务类型=2,3,4,5,6,7,12时)
            if (obj[6] != null && !obj[6].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {

                clearaccountDTO.setReclaimCorpus(obj[6].toString());
              }
            }
            // 回收利息PL202.REAL_INTEREST+PL202.REAL_OVERDUE_INTEREST(当业务类型=2,3,4,5,6,7,12时)
            if (obj[7] != null && !obj[7].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {
                clearaccountDTO.setReclaimAccrual(obj[7].toString());
              }
            }
            // 回收罚息PL202.REAL_PUNISH_INTEREST(当业务类型=2,3,4,5,6,7,12时)
            if (obj[8] != null && !obj[8].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("6") || obj[4].equals("7")
                  || obj[4].equals("12")) {
                clearaccountDTO.setRealPunishInterest(obj[8].toString());
              }
            }
            // 呆账核销金额PL202.OCCUR_MONEY(当业务类型=6,7,8,9或业务类型=12，WRONG_BIZ_TYPE=6或业务类型=12，WRONG_BIZ_TYPE=７)
            /*
             * if (obj[9] != null && !obj[9].equals("")) { if
             * (obj[4].equals("6") || obj[4].equals("7") || obj[4].equals("8") ||
             * obj[4].equals("9")) {
             * clearaccountDTO.setBadDebt(obj[9].toString()); } else if
             * (obj[4].equals("12")) { if ((obj[15] != null &&
             * !obj[15].equals(""))) { if (obj[15].equals("6") ||
             * obj[15].equals("7")) {
             * clearaccountDTO.setBadDebt(obj[9].toString()); } } } }
             */
            // 挂账金额PL202.OCCUR_MONEY(当业务类型=2,3,4,5,13或业务类型=12，WRONG_BIZ_TYPE=6或业务类型=2，WRONG_BIZ_TYPE=5)
            if (obj[10] != null && !obj[10].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")
                  || obj[4].equals("13")) {
                clearaccountDTO.setPutUpMoney(obj[10].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("2") || obj[15].equals("5")) {
                    clearaccountDTO.setPutUpMoney(obj[10].toString());
                  }
                }
              }
            }
            // 保证金PL202.OCCUR_MONEY(当业务类型=14时)
            /*
             * if (obj[11] != null && !obj[11].equals("")) { if
             * (obj[4].equals("14")) {
             * clearaccountDTO.setBail(obj[11].toString()); } } //
             * 保证金利息(当业务类型=14时) if (obj[12] != null && !obj[12].equals("")) { if
             * (obj[4].equals("14")) {
             * clearaccountDTO.setBailAccrual(obj[12].toString()); } }
             */
            // 业务状态PL202.BIZ_ST
            if (obj[13] != null && !obj[13].equals(""))
              clearaccountDTO.setBizSt(obj[13].toString());
            // 办理日期PL202.BIZ_DATE
            if (obj[14] != null && !obj[14].equals(""))
              clearaccountDTO.setBizDate(obj[14].toString());
            // pl202.flow_head_id
            if (obj[18] != null && !obj[18].equals(""))
              clearaccountDTO.setFlowHeadId(obj[18].toString());
            // 实还本金
            if (obj[19] != null && !obj[19].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")) {
                clearaccountDTO.setRealCorpus(obj[19].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("2") || obj[15].equals("3")
                      || obj[15].equals("4") || obj[15].equals("5")) {
                    clearaccountDTO.setRealCorpus(obj[19].toString());
                  }
                }
              }
            }
            // 实还利息
            if (obj[20] != null && !obj[20].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")) {
                clearaccountDTO.setRealInterest(obj[20].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("2") || obj[15].equals("3")
                      || obj[15].equals("4") || obj[15].equals("5")) {
                    clearaccountDTO.setRealInterest(obj[20].toString());
                  }
                }
              }
            }
            // 实还罚息
            if (obj[21] != null && !obj[21].equals("")) {
              if (obj[4].equals("2") || obj[4].equals("3")
                  || obj[4].equals("4") || obj[4].equals("5")) {
                clearaccountDTO.setRealPunish_interest(obj[21].toString());
              } else if (obj[4].equals("12")) {
                if ((obj[15] != null && !obj[15].equals(""))) {
                  if (obj[15].equals("2") || obj[15].equals("3")
                      || obj[15].equals("4") || obj[15].equals("5")) {
                    clearaccountDTO.setRealPunish_interest(obj[21].toString());
                  }
                }
              }
            }
            if (obj[22] != null && !obj[22].equals("")) {
              clearaccountDTO.setLoanBank(obj[22].toString());
            }
            if (obj[23] != null && !obj[23].equals("")) {
              clearaccountDTO.setMakeBillPerson(obj[23].toString());
            }
            if (obj[24] != null && !obj[24].equals("")) {
              clearaccountDTO.setCheckPerson(obj[24].toString());
            }
            if (obj[25] != null && !obj[25].equals("")) {
              clearaccountDTO.setClearAccPerson(obj[25].toString());
            }
            if (obj[26] != null && !obj[26].equals("")) {
              clearaccountDTO.setIsGjjLoanBack("是");
            } else {
              clearaccountDTO.setIsGjjLoanBack("否");
            }
            // 枚举转换业务状态
            try {
              clearaccountDTO.setBizSt(BusiTools.getBusiValue(Integer
                  .parseInt("" + clearaccountDTO.getBizSt()),
                  BusiConst.PLBUSINESSSTATE));
            } catch (Exception e) {
              e.printStackTrace();
            }

            // 枚举转换业务类型
            try {
              clearaccountDTO.setBizType(BusiTools.getBusiValue(Integer
                  .parseInt("" + clearaccountDTO.getBizType()),
                  BusiConst.PLBUSINESSTYPE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            if (clearaccountDTO.getRealPunishInterest() != null
                && clearaccountDTO.getReclaimAccrual() != null
                && clearaccountDTO.getReclaimCorpus() != null) {
              // 计算回收金额=回收金额+回收利息+回收罚息
              clearaccountDTO.setSumReclaimMoney((new BigDecimal(
                  clearaccountDTO.getRealPunishInterest())).add(
                  new BigDecimal(clearaccountDTO.getReclaimAccrual())).add(
                  new BigDecimal(clearaccountDTO.getReclaimCorpus()))
                  .toString());
            }
            bizCheckShowList.add(clearaccountDTO);
          }
          return bizCheckShowList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  /**
   * 扎账列表合计信息
   * 
   * @author shiy 2007-10-29 模仿业务复核
   * @param securityInfo
   * @param docNum
   * @param contractId
   * @param loanKouAcc
   * @param borrowerName
   * @param makePerson
   * @param bizType
   * @param bizSt
   * @param loanBankName
   * @param beginBizDate
   * @param endBizDate
   * @return
   */
  public ClearaccountTotalDTO queryClearaccountShowListCountByCriterions(
      final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String usetName) {

    ClearaccountTotalDTO clearaccountTotalDTO = new ClearaccountTotalDTO();
    try {
      clearaccountTotalDTO = (ClearaccountTotalDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select distinct a.flow_head_id ,"
                  + "a.biz_type ,"
                  + "a.biz_st ,"
                  + "a.wrong_biz_type ,"
                  + "nvl(a.occur_money, 0) as occurMoney,"
                  + "nvl(a.real_corpus, 0) + nvl(a.real_overdue_corpus, 0) as realcorpus,"
                  + "nvl(a.real_interest, 0) + nvl(a.real_overdue_interest, 0) as reclaimAccrual,"
                  + "nvl(a.real_punish_interest, 0) as realPunishInterest,"
                  + "nvl(a.occur_money, 0) as badDebt,"
                  + "nvl(a.occur_money, 0) as putUpMoney,"
                  + "nvl(a.occur_money, 0) as bail,"
                  + "nvl(a.other_interest, 0) as bailAccrual,"
                  + " nvl(a.real_count,0) as realcount "
                  + " from pl202 a, pl203 b, pl110 c"
                  + " where a.flow_head_id = b.flow_head_id"
                  + "  and b.contract_id = c.contract_id"
                  + " and a.biz_type in"
                  + "  ('1', '2', '3', '4', '5', '12', '13')";

              String criterion = "";
              Vector parameters = new Vector();

              if (docNum != null && !"".equals(docNum)) {
                criterion += " a.doc_num = ? and ";
                parameters.add(docNum);
              }
              if (contractId != null && !"".equals(contractId)) {
                criterion += " b.contract_id  = ? and ";
                parameters.add(contractId);
              }

              if (loanKouAcc != null && !"".equals(loanKouAcc)) {
                criterion += " b.loan_kou_acc = ? and ";
                parameters.add(loanKouAcc);
              }
              if (borrowerName != null && !"".equals(borrowerName)) {
                criterion += " c.borrower_name = ? and ";
                parameters.add(borrowerName);
              }
              if (makePerson != null && !"".equals(makePerson)) {
                criterion += " a.make_person = ? and ";
                parameters.add(makePerson);
              }
              if (bizType != null && !"".equals(bizType)) {
                criterion += " a.biz_type = ? and ";
                parameters.add(bizType);
              }

              if (bizSt != null && !"".equals(bizSt)) {
                criterion += "  a.biz_st = ? and ";
                parameters.add(bizSt);
              } else {
                criterion += "  a.biz_st in (4,5,6) and ";
              }
              if (loanBankName != null && !"".equals(loanBankName)) {
                criterion += " a.loan_bank_id = ? and ";
                parameters.add(loanBankName);
              }
              if (beginBizDate != null && !"".equals(beginBizDate)) {
                criterion += " a.biz_date >= ? and ";
                parameters.add(beginBizDate);
              }
              if (endBizDate != null && !"".equals(endBizDate)) {
                criterion += " a.biz_date <= ? and ";
                parameters.add(endBizDate);
              }
              if (usetName != null && !"".equals(usetName)) {
                criterion += " a.make_person in ( " + usetName + " ) and ";
                // parameters.add(usetName);
              }
              if (criterion.length() != 0)
                criterion = " and " + " a.loan_bank_id  "
                    + securityInfo.getDkSecuritySQL() + " and  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;

              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              List queryList = query.list();

              ClearaccountTotalDTO clearaccountTotalDTO = new ClearaccountTotalDTO();

              BigDecimal occurMoneyTotle = new BigDecimal(0.00);// 发放金额-总额

              BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// 回收本金-总额

              BigDecimal reclaimAccrualTotle = new BigDecimal(0.00);// 回收利息-总额总额

              BigDecimal realPunishInterestTotle = new BigDecimal(0.00);// 回收罚息-总额

              // BigDecimal badDebtTotle = new BigDecimal(0.00);// 呆账核销金额-总额

              BigDecimal putUpMoneyTotle = new BigDecimal(0.00);// 挂账金额-总额

              // BigDecimal bailTotle = new BigDecimal(0.00);// 保证金-总额

              // BigDecimal bailAccrualTotle = new BigDecimal(0.00);// 保证金利息-总额

              int realcount = 0; // 实还人数

              int affirmbizSt = 0;// 确认状态个数

              int checkbizSt = 0;// 复合状态个数

              int count = 0;
              Iterator iteratetotle = queryList.iterator();
              Object[] objtotle = null;

              while (iteratetotle.hasNext()) {

                objtotle = (Object[]) iteratetotle.next();

                if (objtotle[0] != null && !objtotle[0].equals("")) {
                  count = count + 1;
                }
                if (objtotle[2] != null && !objtotle[2].equals("")) {
                  if (objtotle[2].toString().equals("4")) {
                    affirmbizSt = affirmbizSt + 1;
                  } else if (objtotle[2].toString().equals("5")) {
                    checkbizSt = checkbizSt + 1;
                  }

                }
                // 发放金额-总额
                if (objtotle[4] != null && !objtotle[4].equals("")) {
                  if (objtotle[1].equals("1")) {
                    occurMoneyTotle = occurMoneyTotle.add(new BigDecimal(
                        objtotle[4].toString()));
                  } else if (objtotle[1].equals("12")) {
                    if ((objtotle[3] != null && !objtotle[3].equals(""))) {
                      if (objtotle[3].equals("1")) {
                        occurMoneyTotle = occurMoneyTotle.add(new BigDecimal(
                            objtotle[4].toString()));
                      }
                    }
                  }
                }
                // 回收本金-总额

                if (objtotle[5] != null && !objtotle[5].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {
                    reclaimCorpusTotle = reclaimCorpusTotle.add(new BigDecimal(
                        objtotle[5].toString()));
                  }
                }
                // 回收利息-总额
                if (objtotle[6] != null && !objtotle[6].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {
                    reclaimAccrualTotle = reclaimAccrualTotle
                        .add(new BigDecimal(objtotle[6].toString()));
                  }
                }
                // 回收罚息-总额
                if (objtotle[7] != null && !objtotle[7].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {

                    realPunishInterestTotle = realPunishInterestTotle
                        .add(new BigDecimal(objtotle[7].toString()));
                  }
                }
                // 呆账核销金额-总额

                /*
                 * if (objtotle[8] != null && !objtotle[8].equals("")) { if
                 * (objtotle[1].equals("6") || objtotle[1].equals("7") ||
                 * objtotle[1].equals("8") || objtotle[1].equals("9")) {
                 * badDebtTotle = badDebtTotle.add(new BigDecimal(objtotle[8]
                 * .toString())); } else if (objtotle[1].equals("12")) { if
                 * ((objtotle[3] != null && !objtotle[3].equals(""))) { if
                 * (objtotle[3].equals("6") || objtotle[3].equals("7")) {
                 * badDebtTotle = badDebtTotle.add(new BigDecimal(
                 * objtotle[8].toString())); } } } }
                 */
                // 挂账金额-总额
                if (objtotle[9] != null && !objtotle[9].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("13")) {
                    putUpMoneyTotle = putUpMoneyTotle.add(new BigDecimal(
                        objtotle[9].toString()));
                  } else if (objtotle[1].equals("12")) {
                    if ((objtotle[3] != null && !objtotle[3].equals(""))) {
                      if (objtotle[3].equals("2") || objtotle[3].equals("5")) {
                        putUpMoneyTotle = putUpMoneyTotle.add(new BigDecimal(
                            objtotle[9].toString()));

                      }
                    }
                  }
                }
                // 保证金-总额
                /*
                 * if (objtotle[10] != null && !objtotle[10].equals("")) { if
                 * (objtotle[1].equals("14")) { bailTotle = bailTotle.add(new
                 * BigDecimal(objtotle[10] .toString())); } } // 保证金利息-总额 if
                 * (objtotle[11] != null && !objtotle[11].equals("")) { if
                 * (objtotle[4].equals("14")) { bailAccrualTotle =
                 * bailAccrualTotle.add(new BigDecimal(
                 * objtotle[11].toString())); } }
                 */
                //
                // if (objtotle[12] != null && !objtotle[12].equals("")) {
                if (objtotle[1].equals("2") || objtotle[1].equals("3")
                    || objtotle[1].equals("4") || objtotle[1].equals("5")
                    || objtotle[1].equals("6") || objtotle[1].equals("7")
                    || objtotle[1].equals("12")) {

                  realcount = realcount
                      + Integer.parseInt(objtotle[12].toString());
                }
                // }
              }
              clearaccountTotalDTO.setCount(count);
              // System.out.println(count+" count");
              clearaccountTotalDTO.setAffirmbizSt(affirmbizSt);
              // System.out.println(affirmbizSt+" affirmbizSt");
              clearaccountTotalDTO.setCheckbizSt(checkbizSt);
              // System.out.println(checkbizSt+" checkbizSt");
              clearaccountTotalDTO.setOccurMoneyTotle(occurMoneyTotle);
              clearaccountTotalDTO.setReclaimCorpusTotle(reclaimCorpusTotle);
              clearaccountTotalDTO.setReclaimAccrualTotle(reclaimAccrualTotle);
              clearaccountTotalDTO
                  .setRealPunishInterestTotle(realPunishInterestTotle);
              // clearaccountTotalDTO.setBadDebtTotle(badDebtTotle);
              // clearaccountTotalDTO.setBailAccrualTotle(bailAccrualTotle);
              // clearaccountTotalDTO.setBailTotle(bailTotle);
              clearaccountTotalDTO.setPutUpMoneyTotle(putUpMoneyTotle);
              // System.out.println(putUpMoneyTotle+" putUpMoneyTotle");

              clearaccountTotalDTO.setRealcount(realcount);
              return clearaccountTotalDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return clearaccountTotalDTO;
  }

  /**
   * 扎账列表合计信息
   * 
   * @author shiy 2007-10-29 模仿业务复核
   * @param securityInfo
   * @param docNum
   * @param contractId
   * @param loanKouAcc
   * @param borrowerName
   * @param makePerson
   * @param bizType
   * @param bizSt
   * @param loanBankName
   * @param beginBizDate
   * @param endBizDate
   * @return
   */
  public ClearaccountTotalDTO queryClearaccountShowListCountByCriterions_LJ(
      final SecurityInfo securityInfo, final String docNum,
      final String contractId, final String loanKouAcc,
      final String borrowerName, final String makePerson, final String bizType,
      final String bizSt, final String loanBankName, final String beginBizDate,
      final String endBizDate, final String usetName) {

    ClearaccountTotalDTO clearaccountTotalDTO = new ClearaccountTotalDTO();
    try {
      clearaccountTotalDTO = (ClearaccountTotalDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select distinct a.flow_head_id ,"
                  + "a.biz_type ,"
                  + "a.biz_st ,"
                  + "a.wrong_biz_type ,"
                  + "nvl(a.occur_money, 0) as occurMoney,"
                  + "nvl(a.real_corpus, 0) + nvl(a.real_overdue_corpus, 0) as realcorpus,"
                  + "nvl(a.real_interest, 0) + nvl(a.real_overdue_interest, 0) as reclaimAccrual,"
                  + "nvl(a.real_punish_interest, 0) as realPunishInterest,"
                  + "nvl(a.occur_money, 0) as badDebt,"
                  + "nvl(a.occur_money, 0) as putUpMoney,"
                  + "nvl(a.occur_money, 0) as bail,"
                  + "nvl(a.other_interest, 0) as bailAccrual,"
                  + " nvl(a.real_corpus,0) as real_corpus,"
                  + " nvl(a.real_interest,0) as real_interest,"
                  + " nvl(a.real_punish_interest,0) as real_punish_interest "
                  + " from pl202 a, pl203 b, pl110 c"
                  + " where a.flow_head_id = b.flow_head_id"
                  + "  and b.contract_id = c.contract_id"
                  + " and a.biz_type in"
                  + "  ('1', '2', '3', '4', '5', '12', '13') and  a.loan_bank_id  "
                  + securityInfo.getDkSecuritySQL() + " and a.biz_st = 6 ";

              String criterion = "";
              Vector parameters = new Vector();

              if (docNum != null && !"".equals(docNum)) {
                criterion += " a.doc_num = ? and ";
                parameters.add(docNum);
              }
              if (contractId != null && !"".equals(contractId)) {
                criterion += " b.contract_id  = ? and ";
                parameters.add(contractId);
              }

              if (loanKouAcc != null && !"".equals(loanKouAcc)) {
                criterion += " b.loan_kou_acc = ? and ";
                parameters.add(loanKouAcc);
              }
              if (borrowerName != null && !"".equals(borrowerName)) {
                criterion += " c.borrower_name = ? and ";
                parameters.add(borrowerName);
              }
              if (makePerson != null && !"".equals(makePerson)) {
                criterion += " a.make_person = ? and ";
                parameters.add(makePerson);
              }
              if (bizType != null && !"".equals(bizType)) {
                criterion += " a.biz_type = ? and ";
                parameters.add(bizType);
              }

              // if (bizSt != null && !"".equals(bizSt)) {
              // criterion += " a.biz_st = ? and ";
              // parameters.add(bizSt);
              // } else {
              // criterion += " a.biz_st in (4,5,6) and ";
              // }
              if (loanBankName != null && !"".equals(loanBankName)) {
                criterion += " a.loan_bank_id = ? and ";
                parameters.add(loanBankName);
              }
              if (beginBizDate != null && !"".equals(beginBizDate)) {
                criterion += " a.biz_date >= ? and ";
                parameters.add(beginBizDate);
              }
              if (endBizDate != null && !"".equals(endBizDate)) {
                criterion += " a.biz_date <= ? and ";
                parameters.add(endBizDate);
              }
              if (usetName != null && !"".equals(usetName)) {
                criterion += " a.make_person in ( '" + usetName + "' ) and ";
                // parameters.add(usetName);
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;

              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              List queryList = query.list();

              ClearaccountTotalDTO clearaccountTotalDTO = new ClearaccountTotalDTO();

              BigDecimal occurMoneyTotle = new BigDecimal(0.00);// 发放金额-总额

              BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// 回收本金-总额

              BigDecimal reclaimAccrualTotle = new BigDecimal(0.00);// 回收利息-总额总额

              BigDecimal realPunishInterestTotle = new BigDecimal(0.00);// 回收罚息-总额

              // BigDecimal badDebtTotle = new BigDecimal(0.00);// 呆账核销金额-总额

              BigDecimal putUpMoneyTotle = new BigDecimal(0.00);// 挂账金额-总额

              BigDecimal realCorpus = new BigDecimal(0.00);// 实还本金

              BigDecimal realInterest = new BigDecimal(0.00);// 实还利息

              BigDecimal realPunishInterest = new BigDecimal(0.00);// 实还罚息

              // BigDecimal bailTotle = new BigDecimal(0.00);// 保证金-总额

              // BigDecimal bailAccrualTotle = new BigDecimal(0.00);// 保证金利息-总额

              int affirmbizSt = 0;// 确认状态个数

              int checkbizSt = 0;// 复合状态个数

              int count = 0;
              Iterator iteratetotle = queryList.iterator();
              Object[] objtotle = null;

              while (iteratetotle.hasNext()) {

                objtotle = (Object[]) iteratetotle.next();

                if (objtotle[0] != null && !objtotle[0].equals("")) {
                  count = count + 1;
                }
                if (objtotle[2] != null && !objtotle[2].equals("")) {
                  if (objtotle[2].toString().equals("4")) {
                    affirmbizSt = affirmbizSt + 1;
                  } else if (objtotle[2].toString().equals("5")) {
                    checkbizSt = checkbizSt + 1;
                  }

                }
                // 发放金额-总额
                if (objtotle[4] != null && !objtotle[4].equals("")) {
                  if (objtotle[1].equals("1")) {
                    occurMoneyTotle = occurMoneyTotle.add(new BigDecimal(
                        objtotle[4].toString()));
                  } else if (objtotle[1].equals("12")) {
                    if ((objtotle[3] != null && !objtotle[3].equals(""))) {
                      if (objtotle[3].equals("1")) {
                        occurMoneyTotle = occurMoneyTotle.add(new BigDecimal(
                            objtotle[4].toString()));
                      }
                    }
                  }
                }
                // 回收本金-总额

                if (objtotle[5] != null && !objtotle[5].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {
                    reclaimCorpusTotle = reclaimCorpusTotle.add(new BigDecimal(
                        objtotle[5].toString()));
                  }
                }
                // 回收利息-总额
                if (objtotle[6] != null && !objtotle[6].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {
                    reclaimAccrualTotle = reclaimAccrualTotle
                        .add(new BigDecimal(objtotle[6].toString()));
                  }
                }
                // 回收罚息-总额
                if (objtotle[7] != null && !objtotle[7].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {

                    realPunishInterestTotle = realPunishInterestTotle
                        .add(new BigDecimal(objtotle[7].toString()));
                  }
                }

                if (objtotle[12] != null && !objtotle[12].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {
                    realCorpus = realCorpus.add(new BigDecimal(objtotle[12]
                        .toString()));
                  }
                }
                if (objtotle[13] != null && !objtotle[13].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {
                    realInterest = realInterest.add(new BigDecimal(objtotle[13]
                        .toString()));
                  }
                }
                if (objtotle[14] != null && !objtotle[14].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("6") || objtotle[1].equals("7")
                      || objtotle[1].equals("12")) {
                    realPunishInterest = realPunishInterest.add(new BigDecimal(
                        objtotle[14].toString()));
                  }
                }
                // 呆账核销金额-总额

                /*
                 * if (objtotle[8] != null && !objtotle[8].equals("")) { if
                 * (objtotle[1].equals("6") || objtotle[1].equals("7") ||
                 * objtotle[1].equals("8") || objtotle[1].equals("9")) {
                 * badDebtTotle = badDebtTotle.add(new BigDecimal(objtotle[8]
                 * .toString())); } else if (objtotle[1].equals("12")) { if
                 * ((objtotle[3] != null && !objtotle[3].equals(""))) { if
                 * (objtotle[3].equals("6") || objtotle[3].equals("7")) {
                 * badDebtTotle = badDebtTotle.add(new BigDecimal(
                 * objtotle[8].toString())); } } } }
                 */
                // 挂账金额-总额
                if (objtotle[9] != null && !objtotle[9].equals("")) {
                  if (objtotle[1].equals("2") || objtotle[1].equals("3")
                      || objtotle[1].equals("4") || objtotle[1].equals("5")
                      || objtotle[1].equals("13")) {
                    putUpMoneyTotle = putUpMoneyTotle.add(new BigDecimal(
                        objtotle[9].toString()));
                  } else if (objtotle[1].equals("12")) {
                    if ((objtotle[3] != null && !objtotle[3].equals(""))) {
                      if (objtotle[3].equals("2") || objtotle[3].equals("5")) {
                        putUpMoneyTotle = putUpMoneyTotle.add(new BigDecimal(
                            objtotle[9].toString()));

                      }
                    }
                  }
                }
                // 保证金-总额
                /*
                 * if (objtotle[10] != null && !objtotle[10].equals("")) { if
                 * (objtotle[1].equals("14")) { bailTotle = bailTotle.add(new
                 * BigDecimal(objtotle[10] .toString())); } } // 保证金利息-总额 if
                 * (objtotle[11] != null && !objtotle[11].equals("")) { if
                 * (objtotle[4].equals("14")) { bailAccrualTotle =
                 * bailAccrualTotle.add(new BigDecimal(
                 * objtotle[11].toString())); } }
                 */
              }
              clearaccountTotalDTO.setCount(count);
              // System.out.println(count+" count");
              clearaccountTotalDTO.setAffirmbizSt(affirmbizSt);
              // System.out.println(affirmbizSt+" affirmbizSt");
              clearaccountTotalDTO.setCheckbizSt(checkbizSt);
              // System.out.println(checkbizSt+" checkbizSt");
              clearaccountTotalDTO.setOccurMoneyTotle(occurMoneyTotle);
              clearaccountTotalDTO.setReclaimCorpusTotle(reclaimCorpusTotle);
              clearaccountTotalDTO.setReclaimAccrualTotle(reclaimAccrualTotle);
              clearaccountTotalDTO
                  .setRealPunishInterestTotle(realPunishInterestTotle);
              // clearaccountTotalDTO.setBadDebtTotle(badDebtTotle);
              // clearaccountTotalDTO.setBailAccrualTotle(bailAccrualTotle);
              // clearaccountTotalDTO.setBailTotle(bailTotle);
              clearaccountTotalDTO.setPutUpMoneyTotle(putUpMoneyTotle);
              clearaccountTotalDTO.setRealCorpusTotal(realCorpus);
              clearaccountTotalDTO.setRealInterestTotal(realInterest);
              clearaccountTotalDTO
                  .setRealPunish_interestTotal(realPunishInterest);
              // System.out.println(putUpMoneyTotle+" putUpMoneyTotle");
              return clearaccountTotalDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return clearaccountTotalDTO;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatepre1_IntByDep(
      final String contracId, final String bankId, final String startDate,
      final String buyhouseid, final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(t1.real_interest)"
                  + "  from pl203 t1, pl202 t2,pl114 t3 "
                  + " where t1.flow_head_id = t2.flow_head_id"
                  + " and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type = 1"
                  + " and t2.biz_date < ? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();

              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id=" + contracId;
              }
              if (buyhouseid != null && !"".equals(buyhouseid)) {
                hql = hql + " and t3.head_id ='" + buyhouseid + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatepre1_IntByFloor(
      final String contracId, final String bankId, final String startDate,
      final String floorid, final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(t1.real_interest)"
                  + "  from pl203 t1, pl202 t2,pl006 b2,pl114 b1"
                  + " where t1.flow_head_id = t2.flow_head_id and t1.contract_id=b1.contract_id and b1.floor_id=b2.floor_id"
                  + " and t1.loan_type = 1"
                  + " and t2.biz_date < ? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              // String ob = orderBy;
              // if (ob == null)
              // ob = " t2.flow_head_id ";
              //
              // String od = order;
              // if (od == null)
              // od = " DESC";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id=" + contracId;
              }
              if (floorid != null && !"".equals(floorid)) {
                hql = hql + " and b2.floor_id='" + floorid + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatepre1_IntByAssorg(
      final String contracId, final String bankId, final String startDate,
      final String assorgid, final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(t1.real_interest)"
                  + "  from pl203 t1, pl202 t2,pl120 t3"
                  + " where t1.flow_head_id = t2.flow_head_id and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type = 1"
                  + " and t2.biz_date < ? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id=" + contracId;
              }
              if (assorgid != null && !"".equals(assorgid)) {
                hql = hql + " and t3.assistant_org_id='" + assorgid + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-11-01 利息总账 按开发商、楼盘、担保公司 查询 期初利息
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByContracIdDatepre1_Int(
      final String contracId, final String bankId, final String startDate) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(t1.real_interest)"
                  + "  from pl203 t1, pl202 t2"
                  + " where t1.flow_head_id = t2.flow_head_id"
                  + " and t1.loan_type = 1" + " and t2.biz_date < ?";

              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id=" + contracId;
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatepre2_IntByDep(
      final String contracId, final String bankId, final String startDate,
      final String buyhouseid, final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + "  from pl203 t1, pl202 t2,pl114 t3 "
                  + " where t1.flow_head_id=t2.flow_head_id "
                  + " and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type=2 and t2.biz_date<? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id=" + contracId;
              }
              if (buyhouseid != null && !"".equals(buyhouseid)) {
                hql = hql + " and t3.head_id ='" + buyhouseid + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatepre2_IntByFloor(
      final String contracId, final String bankId, final String startDate,
      final String floorid, final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + "   from pl203 t1, pl202 t2,pl006 b2,pl114 b1"
                  + " where t1.flow_head_id=t2.flow_head_id  and t1.contract_id=b1.contract_id and b1.floor_id=b2.floor_id "
                  + " and t1.loan_type=2 and t2.biz_date<? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id=" + contracId;
              }
              if (floorid != null && !"".equals(floorid)) {
                hql = hql + " and b2.floor_id='" + floorid + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatepre2_IntByAssorg(
      final String contracId, final String bankId, final String startDate,
      final String assorgid, final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + "  from pl203 t1, pl202 t2,pl120 t3"
                  + " where t1.flow_head_id=t2.flow_head_id and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type=2 and t2.biz_date<? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id=" + contracId;
              }
              if (assorgid != null && !"".equals(assorgid)) {
                hql = hql + "  and t3.assistant_org_id='" + assorgid + "'";
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-11-01 利息总账 按开发商、楼盘、担保公司 查询 期初逾期利息
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByContracIdDatepre2_Int(
      final String contracId, final String bankId, final String startDate) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + "  from pl203 t1, pl202 t2"
                  + " where t1.flow_head_id=t2.flow_head_id "
                  + " and t1.loan_type=2 and t2.biz_date<?";

              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id=" + contracId;
              }
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, startDate);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatethis1_IntByDep(
      final String contracId, final String bankId, final String startDate,
      final String endDate, final String buyhouseid,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl203 t1, pl202 t2,pl114 t3 where t1.flow_head_id=t2.flow_head_id "
                  + " and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type=1 and t2.biz_date between ? and ? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (buyhouseid != null && !"".equals(buyhouseid)) {
                hql = hql + " and t3.head_id ='" + buyhouseid + "'";
              }
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatethis1_IntByFloor(
      final String contracId, final String bankId, final String startDate,
      final String endDate, final String floorid,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + "  from pl203 t1, pl202 t2,pl006 b2,pl114 b1 where t1.flow_head_id=t2.flow_head_id and t1.contract_id=b1.contract_id and b1.floor_id=b2.floor_id"
                  + " and t1.loan_type=1 and t2.biz_date between ? and ? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();

              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (floorid != null && !"".equals(floorid)) {
                hql = hql + " and b2.floor_id=" + "'" + floorid + "'";
              }
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatethis1_IntByAssorg(
      final String contracId, final String bankId, final String startDate,
      final String endDate, final String assorgid,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + "  from pl203 t1, pl202 t2,pl120 t3 where t1.flow_head_id=t2.flow_head_id and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type=1 and t2.biz_date between ? and ? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (assorgid != null && !"".equals(assorgid)) {
                hql = hql + " and t3.assistant_org_id='" + assorgid + "'";
              }
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-11-01 利息总账 按开发商、楼盘、担保公司 查询 本期利息
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByContracIdDatethis1_Int(
      final String contracId, final String bankId, final String startDate,
      final String endDate) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl203 t1, pl202 t2 where t1.flow_head_id=t2.flow_head_id "
                  + " and t1.loan_type=1 and t2.biz_date between ? and ?";

              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }

              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatethis2_IntByDep(
      final String contracId, final String bankId, final String startDate,
      final String endDate, final String buyhouseid,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl203 t1, pl202 t2,pl114 t3 where t1.flow_head_id=t2.flow_head_id "
                  + " and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type=2 and t2.biz_date between ? and ? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (buyhouseid != null && !"".equals(buyhouseid)) {
                hql = hql + " and t3.head_id ='" + buyhouseid + "'";
              }
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatethis2_IntByFloor(
      final String contracId, final String bankId, final String startDate,
      final String endDate, final String floorid,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl203 t1, pl202 t2,pl006 b2,pl114 b1 where t1.flow_head_id=t2.flow_head_id and t1.contract_id=b1.contract_id and b1.floor_id=b2.floor_id"
                  + " and t1.loan_type=2 and t2.biz_date between ? and ? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (floorid != null && !"".equals(floorid)) {
                hql = hql + " and b2.floor_id='" + floorid + "'";
              }
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDatethis2_IntByAssorg(
      final String contracId, final String bankId, final String startDate,
      final String endDate, final String assorgid,
      final SecurityInfo securityInfo) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl203 t1, pl202 t2,pl120 t3 where t1.flow_head_id=t2.flow_head_id and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type=2 and t2.biz_date between ? and ? and t2.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (assorgid != null && !"".equals(assorgid)) {
                hql = hql + " and t3.assistant_org_id='" + assorgid + "'";
              }
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);
              // query.setFirstResult(start);
              // if (pageSize > 0)
              // query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-11-01 利息总账 按开发商、楼盘、担保公司 查询 本期逾期利息
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByContracIdDatethis2_Int(
      final String contracId, final String bankId, final String startDate,
      final String endDate) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl203 t1, pl202 t2 where t1.flow_head_id=t2.flow_head_id "
                  + " and t1.loan_type=2 and t2.biz_date between ? and ?";

              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setParameter(1, endDate);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDateend1_IntByDep(
      final String contracId, final String bankId, final String startDate,
      final String orderBy, final String order, final int pageSize,
      final int start, final String buyhouseid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + "  from pl203 t1, pl202 t2,pl114 t3  where t1.flow_head_id=t2.flow_head_id "
                  + " and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type=1 and t2.biz_date <= ?";
              String ob = orderBy;
              if (ob == null)
                ob = " t2.flow_head_id ";

              String od = order;
              if (od == null)
                od = " DESC";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (buyhouseid != null && !"".equals(buyhouseid)) {
                hql = hql + " and t3.head_id ='" + buyhouseid + "'";
              }
              hql = hql + " order by " + ob + " " + od;
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDateend1_IntByFloor(
      final String contracId, final String bankId, final String startDate,
      final String orderBy, final String order, final int pageSize,
      final int start, final String floorid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + "  from pl203 t1, pl202 t2,pl006 b2,pl114 b1 where t1.flow_head_id=t2.flow_head_id and t1.contract_id=b1.contract_id and b1.floor_id=b2.floor_id"
                  + " and t1.loan_type=1 and t2.biz_date <= ?";
              String ob = orderBy;
              if (ob == null)
                ob = " t2.flow_head_id ";

              String od = order;
              if (od == null)
                od = " DESC";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (floorid != null && !"".equals(floorid)) {
                hql = hql + " and b2.floor_id='" + floorid + "'";
              }
              hql = hql + " order by " + ob + " " + od;
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDateend1_IntByAssorg(
      final String contracId, final String bankId, final String startDate,
      final String orderBy, final String order, final int pageSize,
      final int start, final String assorgid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + "  from pl203 t1, pl202 t2,pl120 t3 where t1.flow_head_id=t2.flow_head_id and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type=1 and t2.biz_date <= ?";
              String ob = orderBy;
              if (ob == null)
                ob = " t2.flow_head_id ";

              String od = order;
              if (od == null)
                od = " DESC";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (assorgid != null && !"".equals(assorgid)) {
                hql = hql + " and t3.assistant_org_id='" + assorgid + "'";
              }
              hql = hql + " order by " + ob + " " + od;
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-11-01 利息总账 按开发商、楼盘、担保公司 查询 期末利息
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByContracIdDateend1_Int(
      final String contracId, final String bankId, final String startDate,
      final String orderBy, final String order, final int pageSize,
      final int start) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + "  from pl203 t1, pl202 t2 where t1.flow_head_id=t2.flow_head_id "
                  + " and t1.loan_type=1 and t2.biz_date <= ?";
              String ob = orderBy;
              if (ob == null)
                ob = " t2.flow_head_id ";

              String od = order;
              if (od == null)
                od = " DESC";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              hql = hql + " order by " + ob + " " + od;
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDateend2_IntByDep(
      final String contracId, final String bankId, final String startDate,
      final String orderBy, final String order, final int pageSize,
      final int start, final String buyhouseid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl203 t1, pl202 t2,pl114 t3 where t1.flow_head_id=t2.flow_head_id "
                  + " and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type=2 and t2.biz_date <= ?";
              String ob = orderBy;
              if (ob == null)
                ob = " t2.flow_head_id ";

              String od = order;
              if (od == null)
                od = " DESC";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (buyhouseid != null && !"".equals(buyhouseid)) {
                hql = hql + " and t3.head_id ='" + buyhouseid + "'";
              }
              hql = hql + " order by " + ob + " " + od;
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDateend2_IntByFloor(
      final String contracId, final String bankId, final String startDate,
      final String orderBy, final String order, final int pageSize,
      final int start, final String floorid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl203 t1, pl202 t2,pl006 b2,pl114 b1 where t1.flow_head_id=t2.flow_head_id and t1.contract_id=b1.contract_id and b1.floor_id=b2.floor_id "
                  + " and t1.loan_type=2 and t2.biz_date <= ?";
              String ob = orderBy;
              if (ob == null)
                ob = " t2.flow_head_id ";

              String od = order;
              if (od == null)
                od = " DESC";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (floorid != null && !"".equals(floorid)) {
                hql = hql + " and b2.floor_id='" + floorid + "'";
              }
              hql = hql + " order by " + ob + " " + od;
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryLoanFlowHeadByContracIdDateend2_IntByAssorg(
      final String contracId, final String bankId, final String startDate,
      final String orderBy, final String order, final int pageSize,
      final int start, final String assorgid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl203 t1, pl202 t2,pl120 t3 where t1.flow_head_id=t2.flow_head_id and t1.contract_id = t3.contract_id"
                  + " and t1.loan_type=2 and t2.biz_date <= ?";
              String ob = orderBy;
              if (ob == null)
                ob = " t2.flow_head_id ";

              String od = order;
              if (od == null)
                od = " DESC";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              if (assorgid != null && !"".equals(assorgid)) {
                hql = hql + " and t3.assistant_org_id='" + assorgid + "'";
              }
              hql = hql + " order by " + ob + " " + od;
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * yuqf 2007-11-01 利息总账 按开发商、楼盘、担保公司 查询 期末逾期利息
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public BigDecimal queryLoanFlowHeadByContracIdDateend2_Int(
      final String contracId, final String bankId, final String startDate,
      final String orderBy, final String order, final int pageSize,
      final int start) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t1.real_interest) "
                  + " from pl203 t1, pl202 t2 where t1.flow_head_id=t2.flow_head_id "
                  + " and t1.loan_type=2 and t2.biz_date <= ?";
              String ob = orderBy;
              if (ob == null)
                ob = " t2.flow_head_id ";

              String od = order;
              if (od == null)
                od = " DESC";
              if (bankId != null && !"".equals(bankId)) {
                hql = hql + " and t2.loan_bank_id=" + bankId;
              }
              if (contracId != null && !"".equals(contracId)) {
                hql = hql + " and t1.contract_id =" + contracId;
              }
              hql = hql + " order by " + ob + " " + od;
              Query query = session.createSQLQuery(hql);
              // query.setParameter(0, contracId);
              query.setParameter(0, startDate);
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * hanl 根据合同编号求出pl203里的金额
   * 
   * @param contactid
   * @return
   */
  public LoandeskaccqueryTaDTO queryRMoney(final String contactid) {
    LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = new LoandeskaccqueryTaDTO();
    try {
      loandeskaccqueryTaDTO = (LoandeskaccqueryTaDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(p203.real_corpus),sum(p203.real_interest),sum(p203.real_punish_interest) from pl203 p203 where p203.contract_id =? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, contactid);
              LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = new LoandeskaccqueryTaDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loandeskaccqueryTaDTO.setRealcorpus(obj[0].toString());
                }
                if (obj[1] != null) {
                  loandeskaccqueryTaDTO.setRealinterest(obj[1].toString());
                }
                if (obj[2] != null) {
                  loandeskaccqueryTaDTO
                      .setRealpunishinterest(obj[2].toString());
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

  /**
   * 查询结算号是否属于个贷业务
   * 
   * @param settNum 结算号
   * @return 如果等于0则为归集业务
   */
  public int queryCountBySettNum(final String settNum) {
    List list = null;

    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p202.flow_head_id from pl202 p202 where p202.note_num=?";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, settNum);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  /**
   * 查询pl400中所有的合同编号 郭婧平 2007.12.24
   */
  public List queryContractId_GJP() {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct p400.contract_id from pl400 p400";
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
   * 业务日志查询 李鹏 2008.1.30
   */
  public String queryContractId_LP(final String bizid) {
    String bizSt = null;
    bizSt = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select distinct max (t.contract_id) from pl203 t ,pl202 tt where t.flow_head_id=tt.flow_head_id and tt.flow_head_id=? ";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, new Integer(bizid));
        return query.uniqueResult();
      }
    });
    return bizSt;
  }

  public List findFirstParticulargDay_wsh(final String borrowercontractid,
      final String bizdateB, final String bizdateE, final String orderBy,
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
          String hql = "select pl202.biz_date,sum(pl203.real_corpus),"
              + " sum(pl203.real_interest),sum(pl203.real_punish_interest) "
              + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id"
              + " and pl203.contract_id=? and pl202.biz_date between ? and ? and pl202.biz_st='6' and pl202.loan_bank_id "
              + securityInfo.getDkSecuritySQL() + " group by pl202.biz_date ";

          String ob = orderBy;
          if (ob == null)
            ob = " pl202.biz_date ";

          String od = orderother;
          if (od == null)
            od = "DESC";

          hql = hql + "order by " + ob + " " + "ASC";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, borrowercontractid);
          query.setParameter(1, bizdateB);
          query.setParameter(2, bizdateE);

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {

              ParticularglDTO particularglDTO = new ParticularglDTO();
              if (obj[0] != null) {
                particularglDTO.setOcyear(obj[0].toString());
              }
              if (obj[1] != null) {
                particularglDTO.setThispaymoney(obj[1].toString());
              } else {
                particularglDTO.setThispaymoney("0.00");
              }
              if (obj[2] != null) {
                particularglDTO.setThisinterest(obj[2].toString());
              } else {
                particularglDTO.setThisinterest("0.00");
              }
              if (obj[3] != null) {
                particularglDTO.setThispunishinterest(obj[3].toString());
              } else {
                particularglDTO.setThispunishinterest("0.00");
              }

              temp_list.add(particularglDTO);
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

  public BigDecimal queryOcMoneyDay_1(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date<? and pl203.contract_id=? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryWrongMoneyDay_1(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_type = 12 and pl202.wrong_biz_type = 1 "
                  + "and pl202.biz_date<? and pl203.contract_id=? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryPaymoneyDay_1(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.real_corpus) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_date<? and pl203.contract_id=? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisLoanrepayDay_2(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('3','4','5','13') "
                  + "and pl202.biz_date < ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisWrongLoanrepayDay_2(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('2','5') "
                  + "and pl202.biz_date < ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);

              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisballbalanceDay_2(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('14','15')"
                  + " and pl202.biz_date < ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisbadocmoneyDay_2(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('6','7','8','9') "
                  + "and pl202.biz_date < ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisbadwocmoneyDay_2(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money)"
                  + " from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('6','7')"
                  + " and pl202.biz_date < ? and pl203.contract_id =? and pl202.biz_st='6' ";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisOcMoneyDay_1(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date = ?  and pl203.contract_id=? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  /**
   * 个贷账务查询 ——查询凭证号
   * 
   * @param contracId
   * @param bankId
   * @param startDate
   * @param orderBy
   * @param order
   * @param pageSize
   * @param start
   * @return
   */
  public String[] queryThisOcMoneyDay_DOc_NumAnd_BizType(final String bizdateB,
      final String contractid) {
    String happenMoney[] = new String[3];
    try {
      happenMoney = (String[]) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select pl202.doc_num,"
                  + "pl202.biz_type,pl202.NOTE_NUM "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_date = ? " + "and pl202.biz_st='6' "
                  + "and pl203.contract_id = ?";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              String str[] = new String[3];

              if (query.list() != null) {
                Object obj[] = new Object[3];
                obj = (Object[]) query.list().get(0);
                if (obj[0] != null && !"".equals(obj[0])) {
                  str[0] = obj[0].toString();
                } else {
                  str[0] = "";
                }
                if (obj[1] != null && !"".equals(obj[1])) {
                  str[1] = obj[1].toString();
                } else {
                  str[1] = "";
                }
                if (obj[2] != null && !"".equals(obj[2])) {
                  str[2] = obj[2].toString();
                } else {
                  str[2] = "";
                }
                return str;
              } else {
                String s[] = new String[2];
                s[0] = "";
                s[1] = "";
                s[2] = "";
                return s;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisLoanrepayDay_1(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('3','4','5','13') "
                  + "and pl202.biz_date = ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisWrongLoanrepayDay_1(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('2','5') "
                  + "and pl202.biz_date = ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);

              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisballbalanceDay_1(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('14','15')"
                  + " and pl202.biz_date = ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisbadocmoneyDay_1(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('6','7','8','9') "
                  + "and pl202.biz_date = ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisbadwocmoneyDay_1(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money)"
                  + " from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('6','7')"
                  + " and pl202.biz_date = ? and pl203.contract_id =? and pl202.biz_st='6' ";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public List findFirstParticulargListAllDay_wsh(
      final String borrowercontractid, final String bizdateB,
      final String bizdateE, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pl202.biz_date,sum(pl203.real_corpus),"
              + " sum(pl203.real_interest),sum(pl203.real_punish_interest) "
              + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id"
              + " and pl203.contract_id=? and pl202.biz_date between ? and ? and pl202.biz_st='6' and pl202.loan_bank_id "
              + securityInfo.getDkSecuritySQL()
              + " group by pl202.biz_date order by pl202.biz_date ASC ";

          hql = hql;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, borrowercontractid);
          query.setParameter(1, bizdateB);
          query.setParameter(2, bizdateE);

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {

              ParticularglDTO particularglDTO = new ParticularglDTO();
              if (obj[0] != null) {
                particularglDTO.setOcyear(obj[0].toString());
              }
              if (obj[1] != null) {
                particularglDTO.setThispaymoney(obj[1].toString());
              } else {
                particularglDTO.setThispaymoney("0.00");
              }
              if (obj[2] != null) {
                particularglDTO.setThisinterest(obj[2].toString());
              } else {
                particularglDTO.setThisinterest("0.00");
              }
              if (obj[3] != null) {
                particularglDTO.setThispunishinterest(obj[3].toString());
              } else {
                particularglDTO.setThispunishinterest("0.00");
              }

              temp_list.add(particularglDTO);
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

  public BigDecimal queryOcMoneyDay_wsh_qichu_1(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 1 "
                  + "and pl202.biz_date<=? and pl202.biz_date>="
                  + bizdate.substring(0, 4) + "0101"
                  + " and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryWrongMoneyDay_wsh_qichu_1(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_type = 12 and pl202.wrong_biz_type = 1 "
                  + "and pl202.biz_date<=? and pl202.biz_date>="
                  + bizdate.substring(0, 4) + "0101"
                  + " and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryPaymoneyDay_qichu_wsh_1(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.real_corpus) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_date<=? and pl202.biz_date>="
                  + bizdate.substring(0, 4) + "0101"
                  + " and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryPaymoneyDay_qichuInterest_wsh_1(final String bizdate,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.real_interest) "
                  + "from PL203 pl203, PL202 pl202 "
                  + "where pl203.flow_head_id = pl202.flow_head_id "
                  + "and pl202.biz_date<=? and pl202.biz_date>="
                  + bizdate.substring(0, 4) + "0101"
                  + " and pl203.contract_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdate);
              query.setParameter(1, contractid);

              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisLoanrepayDay_3(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('3','4','5','13') "
                  + "and pl202.biz_date <= ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisWrongLoanrepayDay_3(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202"
                  + " where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('2','5') "
                  + "and pl202.biz_date <= ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);

              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisballbalanceDay_3(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('14','15')"
                  + " and pl202.biz_date <= ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisbadocmoneyDay_3(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money) "
                  + "from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type in ('6','7','8','9') "
                  + "and pl202.biz_date <= ? and pl203.contract_id =? and pl202.biz_st='6' ";

              Query query = session.createSQLQuery(hql);

              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public BigDecimal queryThisbadwocmoneyDay_3(final String bizdateB,
      final String contractid) {
    BigDecimal happenMoney = new BigDecimal(0.00);
    try {
      happenMoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select sum(pl203.occur_money)"
                  + " from PL203 pl203, PL202 pl202 where pl203.flow_head_id = pl202.flow_head_id and pl202.biz_type = 12 and pl202.wrong_biz_type in ('6','7')"
                  + " and pl202.biz_date <= ? and pl203.contract_id =? and pl202.biz_st='6' ";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, bizdateB);
              query.setParameter(1, contractid);
              if (query.uniqueResult() != null) {
                BigDecimal temphappenMoney = new BigDecimal(query
                    .uniqueResult().toString());
                return temphappenMoney;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }

  public String queryCaiwType(final String a) {
    String type = "";
    try {
      type = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select t.credence_st from fn201 t where t.sett_num=? ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, a);
          String temptype = "";
          if (query.uniqueResult() != null) {
            temptype = query.uniqueResult().toString();
            return temptype;
          } else {
            return temptype;
          }
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return type;
  }

  /**
   * @author yangg
   * @return
   */
  public String queryNoteNum() {
    Object o = "";
    try {
      o = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select seq_notnum.nextval from dual";
          Query query = session.createSQLQuery(sql);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "/" + o.toString();
  }

  /**
   * yqf 查询PL500 期初利息
   * 
   * @param bizdate
   * @param contractid
   * @return
   */
  public BigDecimal queryPl500Interest(final String startDate,
      final String endDate, final String loanBankId) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select nvl(sum(t.interest),0) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();

              if (startDate != null && !"".equals(startDate)) {
                criterion += " t.add_date < ? and ";
                parameters.add(startDate);
              }
              if (loanBankId != null && !"".equals(loanBankId)) {
                criterion += " t.loan_bank_id=? and ";
                parameters.add(loanBankId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query.uniqueResult()
                    .toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }

  /**
   * yqf 查询PL500 期初逾期利息余额
   * 
   * @param bizdate
   * @param contractid
   * @return
   */
  public BigDecimal queryPl500Punish_interest(final String startDate,
      final String endDate, final String loanBankId) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select nvl(sum(t.punish_interest),0) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();

              if (startDate != null && !"".equals(startDate)) {
                criterion += " t.add_date < ? and ";
                parameters.add(startDate);
              }
              if (loanBankId != null && !"".equals(loanBankId)) {
                criterion += " t.loan_bank_id=? and ";
                parameters.add(loanBankId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query.uniqueResult()
                    .toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }

  /**
   * yqf 查询PL500 期末利息
   * 
   * @param bizdate
   * @param contractid
   * @return
   */
  public BigDecimal queryPl500FinalInterest(final String startDate,
      final String endDate, final String loanBankId) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select nvl(sum(t.interest),0) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();

              if (endDate != null && !"".equals(endDate)) {
                criterion += " t.add_date <=? and ";
                parameters.add(endDate);
              }
              if (loanBankId != null && !"".equals(loanBankId)) {
                criterion += " t.loan_bank_id=? and ";
                parameters.add(loanBankId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query.uniqueResult()
                    .toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }

  /**
   * yqf 查询PL500 期末逾期利息余额
   * 
   * @param bizdate
   * @param contractid
   * @return
   */
  public BigDecimal queryPl500FinalPunish_interest(final String startDate,
      final String endDate, final String loanBankId) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select nvl(sum(t.punish_interest),0) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();

              if (endDate != null && !"".equals(endDate)) {
                criterion += " t.add_date <= ? and ";
                parameters.add(endDate);
              }
              if (loanBankId != null && !"".equals(loanBankId)) {
                criterion += " t.loan_bank_id=? and ";
                parameters.add(loanBankId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query.uniqueResult()
                    .toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }

  /**
   * yqf 查询PL500 期初本金
   * 
   * @param bizdate
   * @param contractid
   * @return
   */
  public BigDecimal getPl500Corpus(final String startDate,
      final String endDate, final String office) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select nvl(sum(t.corpus),0) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();

              if (startDate != null && !"".equals(startDate)) {
                criterion += " t.add_date  <?  and ";
                parameters.add(startDate);
              }
              if (office != null && !"".equals(office)) {
                criterion += " t.office=? and ";
                parameters.add(office);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query.uniqueResult()
                    .toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }

  /**
   * yqf 查询PL500 期末本金
   * 
   * @param bizdate
   * @param contractid
   * @return
   */
  public BigDecimal getPl500finalCorpus(final String startDate,
      final String endDate, final String office) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select nvl(sum(t.corpus),0) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();

              if (endDate != null && !"".equals(endDate)) {
                criterion += " t.add_date <=? and ";
                parameters.add(endDate);
              }
              if (office != null && !"".equals(office)) {
                criterion += " t.office=? and ";
                parameters.add(office);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query.uniqueResult()
                    .toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }

  /**
   * yqf 查询PL500 期初本金
   * 
   * @param bizdate
   * @param contractid
   * @return
   */
  public BigDecimal queryPl500Corpus(final String startDate,
      final String endDate, final String loanBankId) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select nvl(sum(t.corpus),0) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();

              if (startDate != null && !"".equals(startDate)) {
                criterion += " t.add_date < ?  and ";
                parameters.add(startDate);
              }
              if (loanBankId != null && !"".equals(loanBankId)) {
                criterion += " t.loan_bank_id=? and ";
                parameters.add(loanBankId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query.uniqueResult()
                    .toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }

  /**
   * yqf 查询PL500 期末本金
   * 
   * @param bizdate
   * @param contractid
   * @return
   */
  public BigDecimal queryPl500finalCorpus(final String startDate,
      final String endDate, final String loanBankId) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select nvl(sum(t.corpus),0) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();

              if (endDate != null && !"".equals(endDate)) {
                criterion += " t.add_date <=? and ";
                parameters.add(endDate);
              }
              if (loanBankId != null && !"".equals(loanBankId)) {
                criterion += " t.loan_bank_id=? and ";
                parameters.add(loanBankId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query.uniqueResult()
                    .toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }

  /**
   * @author yangg
   * @return
   */
  public List queryBankList(final String officeCode) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select pl002.loan_bank_id from PL002 pl002 where pl002.office = ? ";
          Query query = session.createSQLQuery(sql);
          query.setString(0, officeCode);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public String findMydate() {
    String str = "";
    try {
      str = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select min(t.biz_date) from pl202 t where t.biz_type != '1' and t.biz_st = '6'";
          Query query = session.createSQLQuery(sql);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }

  /**
   * 查询本金总账信息(按年)
   * 
   * @param startYear
   * @param endYear
   * @param loanBankId
   * @return
   */
  public List queryYearAccInfoList(final String startYear,
      final String endYear, final String loanBankId) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select substr(p202.biz_date, 0, 4),"
              + " p202.loan_bank_id," + " nvl(sum(p202.occur_money), 0),"
              + " nvl(sum(p202.real_corpus), 0),"
              + " nvl(sum(p202.real_overdue_corpus), 0),"
              + " count(p202.flow_head_id)," + " p202.biz_type,"
              + " p202.wrong_biz_type" + " from pl202 p202"
              + " where p202.biz_st = 6";
          String criterion = "";
          Vector parameters = new Vector();
          if (startYear != null && !"".equals(startYear)) {
            criterion += " substr(p202.biz_date,0,4) >= ? and ";
            parameters.add(startYear);
          }
          if (endYear != null && !"".equals(endYear)) {
            criterion += " substr(p202.biz_date,0,4) <= ? and ";
            parameters.add(endYear);
          }
          if (loanBankId != null && !"".equals(loanBankId)) {
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankId);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql
              + criterion
              + " group by substr(p202.biz_date, 0, 4), p202.loan_bank_id, p202.biz_type, p202.wrong_biz_type";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list = new ArrayList();
          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          PrincipalAccInfDTO dto = null;
          while (iterator.hasNext()) {
            obj = (Object[]) iterator.next();
            dto = new PrincipalAccInfDTO();
            dto.setYear(obj[0].toString());
            dto.setLoanBankId(obj[1].toString());
            dto.setOccurMoney(obj[2].toString());
            dto.setRealCorpus(obj[3].toString());
            dto.setRealOverdueCorpus(obj[4].toString());
            dto.setShouldCount(obj[5].toString());
            dto.setBizType(obj[6].toString());
            if (obj[7] != null)
              dto.setWrongBizType(obj[7].toString());
            else
              dto.setWrongBizType("");
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

  /**
   * 查询本金总账信息(按月)
   * 
   * @param startYear
   * @param endYear
   * @param loanBankId
   * @return
   */
  public List queryMonthAccInfoList(final String year, final String loanBankId) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select substr(p202.biz_date, 0, 6),"
              + " p202.loan_bank_id," + " nvl(sum(p202.occur_money), 0),"
              + " nvl(sum(p202.real_corpus), 0),"
              + " nvl(sum(p202.real_overdue_corpus), 0),"
              + " count(p202.flow_head_id)," + " p202.biz_type,"
              + " p202.wrong_biz_type" + " from pl202 p202"
              + " where p202.biz_st = 6";
          String criterion = "";
          Vector parameters = new Vector();
          if (year != null && !"".equals(year)) {
            criterion += " substr(p202.biz_date,0,4) = ? and ";
            parameters.add(year);
          }
          if (loanBankId != null && !"".equals(loanBankId)) {
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankId);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql
              + criterion
              + " group by substr(p202.biz_date, 0, 6), p202.loan_bank_id, p202.biz_type, p202.wrong_biz_type";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list = new ArrayList();
          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          PrincipalAccInfDTO dto = null;
          while (iterator.hasNext()) {
            obj = (Object[]) iterator.next();
            dto = new PrincipalAccInfDTO();
            dto.setMonth(obj[0].toString());
            dto.setLoanBankId(obj[1].toString());
            dto.setOccurMoney(obj[2].toString());
            dto.setRealCorpus(obj[3].toString());
            dto.setRealOverdueCorpus(obj[4].toString());
            dto.setShouldCount(obj[5].toString());
            dto.setBizType(obj[6].toString());
            if (obj[7] != null)
              dto.setWrongBizType(obj[7].toString());
            else
              dto.setWrongBizType("");
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

  /**
   * 查询本金总账信息(按日)
   * 
   * @param startYear
   * @param endYear
   * @param loanBankId
   * @return
   */
  public List queryDayAccInfoList(final String month, final String loanBankId) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select p202.biz_date," + " p202.loan_bank_id,"
              + " nvl(sum(p202.occur_money), 0),"
              + " nvl(sum(p202.real_corpus), 0),"
              + " nvl(sum(p202.real_overdue_corpus), 0),"
              + " count(p202.flow_head_id)," + " p202.biz_type,"
              + " p202.wrong_biz_type" + " from pl202 p202"
              + " where p202.biz_st = 6";
          String criterion = "";
          Vector parameters = new Vector();
          if (month != null && !"".equals(month)) {
            criterion += " substr(p202.biz_date,0,6) = ? and ";
            parameters.add(month);
          }
          if (loanBankId != null && !"".equals(loanBankId)) {
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankId);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql
              + criterion
              + " group by p202.biz_date, p202.loan_bank_id, p202.biz_type, p202.wrong_biz_type";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list = new ArrayList();
          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          PrincipalAccInfDTO dto = null;
          while (iterator.hasNext()) {
            obj = (Object[]) iterator.next();
            dto = new PrincipalAccInfDTO();
            dto.setDate(obj[0].toString());
            dto.setLoanBankId(obj[1].toString());
            dto.setOccurMoney(obj[2].toString());
            dto.setRealCorpus(obj[3].toString());
            dto.setRealOverdueCorpus(obj[4].toString());
            dto.setShouldCount(obj[5].toString());
            dto.setBizType(obj[6].toString());
            if (obj[7] != null)
              dto.setWrongBizType(obj[7].toString());
            else
              dto.setWrongBizType("");
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

  /**
   * 查询利息总账信息(按年)
   * 
   * @param startYear
   * @param endYear
   * @param loanBankId
   * @return
   */
  public List queryYearIntAccInfoList(final String startYear,
      final String endYear, final String loanBankId) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select substr(p202.biz_date, 0, 4),"
              + " p202.loan_bank_id,"
              + " nvl(sum(p202.real_interest) + sum(p202.real_overdue_interest), 0),"
              + " nvl(sum(p202.real_punish_interest), 0)" + " from pl202 p202"
              + " where p202.biz_st = 6";
          // LoanFlowHead
          String criterion = "";
          Vector parameters = new Vector();
          if (startYear != null && !"".equals(startYear)) {
            criterion += " substr(p202.biz_date,0,4) >= ? and ";
            parameters.add(startYear);
          }
          if (endYear != null && !"".equals(endYear)) {
            criterion += " substr(p202.biz_date,0,4) <= ? and ";
            parameters.add(endYear);
          }
          if (loanBankId != null && !"".equals(loanBankId)) {
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankId);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion
              + " group by substr(p202.biz_date, 0, 4), p202.loan_bank_id";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list = new ArrayList();
          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          InterestAccInfDTO dto = null;
          while (iterator.hasNext()) {
            obj = (Object[]) iterator.next();
            dto = new InterestAccInfDTO();
            dto.setYear(obj[0].toString());
            dto.setLoanBankId(obj[1].toString());
            dto.setRealInterest(obj[2].toString());
            dto.setRealOverdueInterest(obj[3].toString());
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

  /**
   * 查询利息总账信息(按月)
   * 
   * @param startYear
   * @param endYear
   * @param loanBankId
   * @return
   */
  public List queryMonthIntAccInfoList(final String year,
      final String loanBankId) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select substr(p202.biz_date, 0, 6),"
              + " p202.loan_bank_id,"
              + " nvl(sum(p202.real_interest) + sum(p202.real_overdue_interest), 0),"
              + " nvl(sum(p202.real_punish_interest), 0)" + " from pl202 p202"
              + " where p202.biz_st = 6";
          // LoanFlowHead
          String criterion = "";
          Vector parameters = new Vector();
          if (year != null && !"".equals(year)) {
            criterion += " substr(p202.biz_date,0,4) = ? and ";
            parameters.add(year);
          }
          if (loanBankId != null && !"".equals(loanBankId)) {
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankId);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion
              + " group by substr(p202.biz_date, 0, 6), p202.loan_bank_id";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list = new ArrayList();
          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          InterestAccInfDTO dto = null;
          while (iterator.hasNext()) {
            obj = (Object[]) iterator.next();
            dto = new InterestAccInfDTO();
            dto.setMonth(obj[0].toString());
            dto.setLoanBankId(obj[1].toString());
            dto.setRealInterest(obj[2].toString());
            dto.setRealOverdueInterest(obj[3].toString());
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

  /**
   * 查询利息总账信息(按日)
   * 
   * @param startYear
   * @param endYear
   * @param loanBankId
   * @return
   */
  public List queryDayIntAccInfoList(final String month, final String loanBankId) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select p202.biz_date,"
              + " p202.loan_bank_id,"
              + " nvl(sum(p202.real_interest) + sum(p202.real_overdue_interest), 0),"
              + " nvl(sum(p202.real_punish_interest), 0)" + " from pl202 p202"
              + " where p202.biz_st = 6";
          String criterion = "";
          Vector parameters = new Vector();
          if (month != null && !"".equals(month)) {
            criterion += " substr(p202.biz_date,0,6) = ? and ";
            parameters.add(month);
          }
          if (loanBankId != null && !"".equals(loanBankId)) {
            criterion += " p202.loan_bank_id = ? and ";
            parameters.add(loanBankId);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion + " group by p202.biz_date, p202.loan_bank_id";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list = new ArrayList();
          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          InterestAccInfDTO dto = null;
          while (iterator.hasNext()) {
            obj = (Object[]) iterator.next();
            dto = new InterestAccInfDTO();
            dto.setDate(obj[0].toString());
            dto.setLoanBankId(obj[1].toString());
            dto.setRealInterest(obj[2].toString());
            dto.setRealOverdueInterest(obj[3].toString());
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

  /**
   * 查询期初借方金额
   * 
   * @param bankId
   * @param date
   * @return
   */
  public BigDecimal queryBgnDebitCorpBalance(final String bankId,
      final String date) {
    BigDecimal balance = new BigDecimal(0.00);
    try {
      balance = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select sum(t.occur_money)" + " from pl202 t "
                  + " where t.biz_st = 6" + " and (t.biz_type = 1"
                  + " or (t.biz_type = 12 and t.wrong_biz_type = 1))";
              String criterion = "";
              Vector parameters = new Vector();
              if (bankId != null && !"".equals(bankId)) {
                criterion += " t.loan_bank_id = ? and ";
                parameters.add(bankId);
              }
              if (date != null && !"".equals(date)) {
                criterion += " t.biz_date < ? and ";
                parameters.add(date);
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              sql += criterion;
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return balance;
  }

  /**
   * 查询期初贷方金额
   * 
   * @param bankId
   * @param date
   * @return
   */
  public BigDecimal queryBgnCreditCorpBalance(final String bankId,
      final String date) {
    BigDecimal balance = new BigDecimal(0.00);
    try {
      balance = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select sum(t.real_corpus)+sum(t.real_overdue_corpus)"
                  + " from pl202 t " + " where t.biz_st = 6";
              String criterion = "";
              Vector parameters = new Vector();
              if (bankId != null && !"".equals(bankId)) {
                criterion += " t.loan_bank_id = ? and ";
                parameters.add(bankId);
              }
              if (date != null && !"".equals(date)) {
                criterion += " t.biz_date < ? and ";
                parameters.add(date);
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              sql += criterion;
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return balance;
  }

  /**
   * 查询期初借方笔数
   * 
   * @param bankId
   * @param date
   * @return
   */
  public int queryBgnDebitCorpCount(final String bankId, final String date) {
    Integer count = null;
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(t.flow_head_id)" + " from pl202 t "
              + " where t.biz_st = 6" + " and t.biz_type = 1";
          String criterion = "";
          Vector parameters = new Vector();
          if (bankId != null && !"".equals(bankId)) {
            criterion += " t.loan_bank_id = ? and ";
            parameters.add(bankId);
          }
          if (date != null && !"".equals(date)) {
            criterion += " t.biz_date < ? and ";
            parameters.add(date);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql += criterion;
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
   * 查询期初贷方笔数
   * 
   * @param bankId
   * @param date
   * @return
   */
  public int queryBgnCreditCorpCount(final String bankId, final String date) {
    Integer count = null;
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(t.flow_head_id)" + " from pl202 t "
              + " where t.biz_st = 6" + " and t.biz_type in(2,3,4,5,6,7,11)";
          String criterion = "";
          Vector parameters = new Vector();
          if (bankId != null && !"".equals(bankId)) {
            criterion += " t.loan_bank_id = ? and ";
            parameters.add(bankId);
          }
          if (date != null && !"".equals(date)) {
            criterion += " t.biz_date < ? and ";
            parameters.add(date);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql += criterion;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          int a = Integer.parseInt(query.uniqueResult().toString());
          sql = "select count(t.flow_head_id)" + " from pl202 t "
              + " where t.biz_st = 6"
              + " and t.biz_type = 12 and t.wrong_biz_type in (2,5,6,7)";
          criterion = "";
          parameters = new Vector();
          if (bankId != null && !"".equals(bankId)) {
            criterion += " t.loan_bank_id = ? and ";
            parameters.add(bankId);
          }
          if (date != null && !"".equals(date)) {
            criterion += " t.biz_date < ? and ";
            parameters.add(date);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql += criterion;
          query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          int b = Integer.parseInt(query.uniqueResult().toString());
          return new Integer(a - b);
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }

  /**
   * 查询期初利息
   * 
   * @param bankId
   * @param date
   * @return
   */
  public BigDecimal queryBgnInterest(final String bankId, final String date) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select sum(t.real_interest) + sum(t.real_overdue_interest)"
                  + " from pl202 t " + " where t.biz_st = 6";
              String criterion = "";
              Vector parameters = new Vector();
              if (bankId != null && !"".equals(bankId)) {
                criterion += " t.loan_bank_id = ? and ";
                parameters.add(bankId);
              }
              if (date != null && !"".equals(date)) {
                criterion += " t.biz_date < ? and ";
                parameters.add(date);
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              sql += criterion;
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }

  /**
   * 查询期初逾期利息
   * 
   * @param bankId
   * @param date
   * @return
   */
  public BigDecimal queryBgnOverdueInterest(final String bankId,
      final String date) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select sum(t.real_punish_interest)"
                  + " from pl202 t " + " where t.biz_st = 6";
              String criterion = "";
              Vector parameters = new Vector();
              if (bankId != null && !"".equals(bankId)) {
                criterion += " t.loan_bank_id = ? and ";
                parameters.add(bankId);
              }
              if (date != null && !"".equals(date)) {
                criterion += " t.biz_date < ? and ";
                parameters.add(date);
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              sql += criterion;
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }

  /**
   * 查询pl500本金借方金额(差的钱)
   * 
   * @param startDate
   * @param endDate
   * @param loanBankId
   * @return
   */
  public BigDecimal queryPl500CorpDebit(final String startDate,
      final String loanBankId) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t.corpus_debit) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();

              if (startDate != null && !"".equals(startDate)) {
                criterion += " t.add_date < ? and ";
                parameters.add(startDate);
              }
              if (loanBankId != null && !"".equals(loanBankId)) {
                criterion += " t.loan_bank_id=? and ";
                parameters.add(loanBankId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query.uniqueResult()
                    .toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }

  /**
   * 查询pl500本金贷方金额(差的钱)
   * 
   * @param startDate
   * @param endDate
   * @param loanBankId
   * @return
   */
  public BigDecimal queryPl500CorpCredit(final String startDate,
      final String loanBankId) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(t.corpus_credit) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();

              if (startDate != null && !"".equals(startDate)) {
                criterion += " t.add_date < ? and ";
                parameters.add(startDate);
              }
              if (loanBankId != null && !"".equals(loanBankId)) {
                criterion += " t.loan_bank_id=? and ";
                parameters.add(loanBankId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query.uniqueResult()
                    .toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }
}
