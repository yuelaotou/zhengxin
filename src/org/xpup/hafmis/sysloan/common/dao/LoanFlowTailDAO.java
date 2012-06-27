package org.xpup.hafmis.sysloan.common.dao;

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
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentTail;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaSaveDTO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto.BailpickupTbDTO;

public class LoanFlowTailDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public LoanFlowTail queryById(Serializable id) {
    Validate.notNull(id);
    return (LoanFlowTail) getHibernateTemplate().get(LoanFlowTail.class, id);
  }

  /**
   * 插入记录
   * 
   * @param LoanFlowTail
   * @return
   */
  public Serializable insert(LoanFlowTail loanFlowTail) {
    Serializable id = null;
    try {
      Validate.notNull(loanFlowTail);
      id = getHibernateTemplate().save(loanFlowTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  
  /**
   * 删除单个记录
   * @param loanFlowTail
   */
  public void delete(LoanFlowTail loanFlowTail){
    Validate.notNull(loanFlowTail);
    getHibernateTemplate().delete(loanFlowTail);
  }

  /**
   * author wsh 查询扣款帐号是否可以进行修改
   * 
   * @param contractId
   * @return
   */
  public Integer queryCountByContractId_wsh(final String contractId) {

    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.biz_st) " + "from pl202 a,pl203 b "
                + "where b.flow_head_id=a.flow_head_id and "
                + "a.biz_st in(1,2,3,4,5) and " + "b.contract_id= ?";
            Vector parameters = new Vector();
            if (contractId != null) {
              parameters.add(contractId);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            try {
              obj = (Object) query.uniqueResult();
            } catch (Exception e) {
              // TODO: handle exception
              e.printStackTrace();
            }
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
   * query pl600
   * @author yangg
   * @param office
   * @param bizDate
   * @return String
   */
  public String queryPL600Num(final String office, final String bizDate) {
    String num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select t.num from pl600 t where t.office = ? and t.year = ?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, office);
            query.setParameter(1, bizDate);
            return query.uniqueResult()+"";
          }
        });
    return num;
  }
  /**
   * query pl601_num
   * @author yangg
   * @param office
   * @param bizDate
   * @return
   */
  public String queryPL601Num(final String office, final String bizDate) {
    String num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select min(t.num) from pl601 t where t.office = ? and t.year = ?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, office);
            query.setParameter(1, bizDate);
            return query.uniqueResult() != null ? query.uniqueResult() : "";
          }
        });
    return num;
  }
  /**
   * update pl600
   * @author yangg
   * @param office
   * @param bizDate
   * @param num
   */
  public void updatePL600Num(final String office, final String bizDate,
      final String num) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = "update pl600 t set t.num = '"+num+"' where t.office = '"+office+"' and t.year = '"+bizDate+"'";
      st.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * delete pl600
   * @author yangg
   * @param office
   * @param bizDate
   * @param num
   */
  public void deletePL601Num(final String office, final String bizDate,
      final String num) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = "delete pl601 t where t.num = '"+num+"' and t.office = '"+office+"' and t.year = '"+bizDate+"'";
      st.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * insert pl600
   * @author yangg
   * @param office
   * @param bizDate
   * @param num
   */
  public void insertPL601Num(final String office, final String bizDate,
      final String num) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
      .openSession().connection();
      Statement st = conn.createStatement();
      String sql = "insert into pl601 values (seq_pl601.nextval,'"+office+"','"+bizDate+"','"+num+"')";
      st.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * query queryNum_yg
   * @author yangg
   * @return List
   */
  public List queryNum_yg(final String bankid) {
    List list =  getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select t.batch_num from pl202 t where t.biz_st='1' and t.loan_bank_id = ?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, bankid);
            return query.list();
          }
        });
    return list;
  }
  /**
   * 根据合同编号查询会计日期内提前还款次数 办理回收提前还款 jj
   * 
   * @param contractId
   * @param bizDate
   * @return
   */
  public Integer queryCallbackAheadCounts_LJ(final String contractId,final String bizDate){
    Integer counts = new Integer(0);
    try {
      counts = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select count( distinct loanFlowHead.flowHeadId) " +

                  " from LoanFlowTail loanFlowTail, LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";

              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }

              if (bizDate != null && !bizDate.equals("")) {
                criterion += "loanFlowHead.bizDate like ?  and ";
                parameters.add(bizDate+"%");
              }

              if (criterion.length() != 0)
                criterion = "where loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and loanFlowHead.bizType='3' and  "
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
    return counts;
  }
  
  /**
   * 挂账维护
   * 
   * @author 郭婧平 2007-9-28 根据头表id删除尾表的记录
   */
  public void deleteLoanFlowTailAll(final String flowHeadId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete LoanFlowTail loanFlowTail where loanFlowTail.flowHeadId=?";
          session.createQuery(sql).setBigDecimal(0, new BigDecimal(flowHeadId))
              .executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * yuqf
   * 
   * @param id
   * @return 根据合同编号查询流水ID
   */
  public String queryHeadId(final String id) {
    String headId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select distinct t.flow_head_id from pl203 t where t.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);

            return query.uniqueResult().toString();
          }
        });

    return headId;
  }

  /**
   * 回收维护弹出信息查询(单笔)
   * jj
   * @param HeadId
   * @return
   */
  public List queryLoanFlowTailByHeadId_LJ(final String headId){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTail.yearMonth,loanFlowTail.loanType,loanFlowTail.shouldCorpus," +
                  " loanFlowTail.shouldInterest,loanFlowTail.shouldPunishInterest,loanFlowTail.loanRate,loanFlowTail.realCorpus," +
                  " loanFlowTail.realInterest,loanFlowTail.realPunishInterest," +
                  " borrowerAcc.loanBankId" +
                  " from LoanFlowTail loanFlowTail,BorrowerAcc borrowerAcc " +
                  " where loanFlowTail.contractId = borrowerAcc.contractId";
              Vector parameters = new Vector();
              String criterion = "";

              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }

              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion+" order by loanFlowTail.flowTailId ASC ";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              List temp_list = new ArrayList();
              Object obj [] = null;
              Iterator it = query.iterate();
              while(it.hasNext()){
                obj  = (Object[])it.next();
                ShouldBackListDTO dto = new ShouldBackListDTO();
                dto.setLoanKouYearmonth(obj[0].toString());
                dto.setLoanKouType(obj[1].toString());
                dto.setShouldCorpus(new BigDecimal(obj[2].toString()));
                dto.setShouldInterest(new BigDecimal(obj[3].toString()));
                dto.setPunishInterest(new BigDecimal(obj[4].toString()));
                if(obj[5]!=null){
                  dto.setLoanRate(new BigDecimal(obj[5].toString()));                  
                }
                dto.setRealCorpus(new BigDecimal(obj[6].toString()));
                dto.setRealInterest(new BigDecimal(obj[7].toString()));
                dto.setRealPunishInterest(new BigDecimal(obj[8].toString()));
                dto.setLoanBankId(obj[9].toString());
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
   * 回收办理提前还款查询PL203中的最大还至年月
   * 为了计算已经还过多少个月
   * jj
   * @param HeadId
   * @return
   */
  public List queryYearMonth_LJ(final String loanKouAcc){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select pl203.year_month " +
                  " from PL203 pl203  where pl203.flow_tail_id=(select max(p.flow_tail_id) from PL203 p where p.loan_kou_acc='"+loanKouAcc+"')";
              Query query = session.createSQLQuery(sql);
              List temp_list = new ArrayList();
              Object obj  = null;
              Iterator it = query.list().iterator();
              while(it.hasNext()){
                obj  = (Object)it.next();
                ShouldBackListDTO dto = new ShouldBackListDTO();
                dto.setLoanKouYearmonth(obj.toString());
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
   * 回收办理银行导入后查询流水尾表
   * jj
   * @param HeadId
   * @return
   */
  public List queryRealLoanFlowTailByHeadId_LJ(final String headId){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTail.yearMonth,loanFlowTail.loanType,loanFlowTail.realCorpus," +
                  "loanFlowTail.realInterest,loanFlowTail.realPunishInterest,loanFlowTail.loanRate,loanFlowTail.contractId " +
                  " from LoanFlowTail loanFlowTail ";
              Vector parameters = new Vector();
              String criterion = "";

              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }

              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion+" order by loanFlowTail.flowTailId ASC ";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              List temp_list = new ArrayList();
              Object obj [] = null;
              Iterator it = query.iterate();
              while(it.hasNext()){
                obj  = (Object[])it.next();
                ShouldBackListDTO dto = new ShouldBackListDTO();
                dto.setLoanKouYearmonth(obj[0].toString());
                dto.setLoanKouType(obj[1].toString());
                dto.setShouldCorpus(new BigDecimal(obj[2].toString()));
                dto.setShouldInterest(new BigDecimal(obj[3].toString()));
                dto.setPunishInterest(new BigDecimal(obj[4].toString()));
                if(obj[5]!=null){
                  dto.setLoanRate(new BigDecimal(obj[5].toString()));                  
                }
                dto.setContractId(obj[6].toString());
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
   * 回收维护打印明细
   * jj
   * @param HeadId
   * @return
   */
  public List queryPrintLoanFlowTailByHeadId_LJ(final String headId){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTail.yearMonth," +
                  " loanFlowTail.loanKouAcc," +
                  " loanFlowTail.contractId," +
                  " borrower.borrowerName," +
                  " borrower.cardNum," +
                  " nvl(loanFlowTail.shouldCorpus,0)," +
                  " nvl(loanFlowTail.shouldInterest,0)," +
                  " nvl(loanFlowTail.shouldPunishInterest,0)," +
                  " loanFlowTail.realCorpus," +
                  " loanFlowTail.realInterest," +
                  " loanFlowTail.realPunishInterest," +
                  "(loanFlowTail.realCorpus+loanFlowTail.realInterest+loanFlowTail.realPunishInterest)," +
                  " loanFlowTail.occurMoney," +
                  " borrowerAcc.loanBankId," +
                  " loanFlowTail.loanType," +
                  " loanFlowHead.batchNum " +
                  " from LoanFlowTail loanFlowTail,Borrower borrower,BorrowerAcc borrowerAcc,LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = borrowerAcc.contractId and borrower.contractId = loanFlowTail.contractId and loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion+" order by loanFlowTail.flowTailId ASC  ";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              List temp_list = new ArrayList();
              Object obj [] = null;
              Iterator it = query.iterate();
              while(it.hasNext()){
                obj  = (Object[])it.next();
                ShouldBackListDTO dto = new ShouldBackListDTO();
                dto.setLoanKouYearmonth(obj[0].toString());
                dto.setLoanKouAcc(obj[1].toString());
                dto.setContractId(obj[2].toString());
                dto.setBorrowerName(obj[3].toString());
                dto.setCardNum(obj[4].toString());
                dto.setShouldCorpus(new BigDecimal(obj[5].toString()));
                dto.setShouldInterest(new BigDecimal(obj[6].toString()));
                dto.setShouldPunishInterest(new BigDecimal(obj[7].toString()));
                dto.setRealCorpus(new BigDecimal(obj[8].toString()));
                dto.setRealInterest(new BigDecimal(obj[9].toString()));
                dto.setRealPunishInterest(new BigDecimal(obj[10].toString()));
                dto.setRealMoney(new BigDecimal(obj[11].toString()));
                dto.setOccurMoney(new BigDecimal(obj[12].toString()));
                dto.setMoney(new BigDecimal(obj[11].toString()));
                dto.setLoanBankId(obj[13].toString());
                String loanKouType = obj[14].toString();
                if(loanKouType==null){
                  loanKouType = "";
                }
                dto.setLoanKouType(loanKouType);
                String batchNum = null;
                if(obj[15]!=null){
                batchNum = obj[15].toString();
                }
                if(batchNum == null){
                  batchNum = "";
                }
                dto.setBatchNum(batchNum);
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
   * 根据头表ID查询尾表ID
   * 回收办理确定时用
   * jj
   * @param headId
   * @return
   * 银行代扣导入时调用（根据导入文件来更新流水表中状态为导出的数据）jj
   */
  public List queryLoanFlowTailIDByHeadId_LJ(final String headId){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTail.flowTailId from LoanFlowTail loanFlowTail ";
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (criterion.length() != 0)
                criterion = "where nvl(loanFlowTail.shouldCorpus-loanFlowTail.realCorpus,0)>0 and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion + "order by loanFlowTail.loanKouAcc DESC ";
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
   * 银行代扣导入（根据导入文件来更新流水表中状态为导出的数据）
   * jj
   * @param headId
   * @return
   * 
   */
  public List queryImportLoanFlowTailIDByHeadId_LJ(final String headId){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTail.flowTailId from LoanFlowTail loanFlowTail ";
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion + "order by loanFlowTail.loanKouAcc DESC ";
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
   * sy
   * @param id
   * @return contract_id,office,biz_date,doc_num
   * 根据合同编号查找撤销凭证好号所需要的条件
   */
  public List queryLoanFlowHeadInfo(final String flowHeadId){
    List list=new ArrayList();
     try{
       list= (List)getHibernateTemplate().executeFind(
           new HibernateCallback() {
           public Object doInHibernate(Session session)
               throws HibernateException, SQLException {
              String sql="select rpl203.contract_id, rpl202.doc_num, rpl202.biz_date, rplbb10.office,rpl202.loan_bank_id from pl203 rpl203, pl202 rpl202, bb105 rplbb10 where rpl203.flow_head_id = rpl202.flow_head_id "
                +"and rpl202.loan_bank_id = rplbb10.coll_bank_id and rpl202.flow_head_id = ?";          
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, flowHeadId);

               return query.list();
           }
       }  
   );
     }catch(Exception e){
       e.printStackTrace();
     }
  return  list;
  }

  /**
   * 判断该贷款账号下是否存在未记账的保证金登记业务（BIZ_TYPE=14，BIZ_ST！=6）
   * 
   * @author 王野 2007-10-03
   * @param loanKouAcc
   * @return
   */
  public boolean isExistsLoanFlowHeadByLoanKouAcc(final String loanKouAcc) {
    boolean flag = true;
    BigDecimal flowHeadId = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p202.flow_head_id "
                + " from pl203 p203, pl202 p202 "
                + " where p203.flow_head_id = p202.flow_head_id "
                + " and p202.biz_type = '14' "
                + " and (p202.biz_st< '6' or p202.biz_st > '6') "
                + " and p203.loan_kou_acc  =? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, loanKouAcc);
            return query.uniqueResult();
          }
        });
    if (flowHeadId == null || flowHeadId.equals("")) {
      flag = false;
    }
    return flag;
  }
  
  /**
   * 回收维护明细查询（批量）
   * jj
   * @param headId
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public List queryFlowTailByHeadIdMX_LJ(final String headId,final String orderBy, final String order, final int start,
      final int pageSize){
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTail.yearMonth,loanFlowTail.loanKouAcc,borrower.borrowerName," +
                  "borrower.cardNum,nvl(loanFlowTail.shouldCorpus,0),nvl(loanFlowTail.shouldInterest,0),nvl(loanFlowTail.shouldPunishInterest,0)," +
                  " loanFlowTail.realCorpus,loanFlowTail.realInterest,loanFlowTail.realPunishInterest," +
                  "(loanFlowTail.realCorpus+loanFlowTail.realInterest+loanFlowTail.realPunishInterest),loanFlowTail.occurMoney," +
                  "(loanFlowTail.realCorpus+loanFlowTail.realInterest+loanFlowTail.realPunishInterest+loanFlowTail.occurMoney),loanFlowTail.loanType, " +
                  " borrowerAcc.loanRepayDay,loanFlowTail.flowTailId,loanFlowTail.contractId,"+
                  " borrowerAcc.loanBankId" +
                  " from LoanFlowTail loanFlowTail,Borrower borrower,BorrowerAcc borrowerAcc " +
                  " where borrower.contractId = loanFlowTail.contractId and borrowerAcc.contractId = borrower.contractId" ;
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              String ob = orderBy;
              if (ob == null)
                ob = " loanFlowTail.loanKouAcc ";

              String od = order;
              if (od == null)
                od = "DESC";             
              hql = hql + criterion+ " order by " + ob + " ,loanFlowTail.yearMonth " + od ;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              query.setFirstResult(start);
              if (pageSize > 0)
              query.setMaxResults(pageSize);   
              
              List temp_list = new ArrayList();
              Object obj [] = null;
              Iterator it = query.iterate();
              while(it.hasNext()){
                obj  = (Object[])it.next();
                ShouldBackListDTO dto = new ShouldBackListDTO();
                dto.setLoanKouYearmonth(obj[0].toString());
                dto.setLoanKouAcc(obj[1].toString());
                dto.setBorrowerName(obj[2].toString());
                dto.setCardNum(obj[3].toString());
                dto.setShouldCorpus(new BigDecimal(obj[4].toString()));
                dto.setShouldInterest(new BigDecimal(obj[5].toString()));
                dto.setShouldPunishInterest(new BigDecimal(obj[6].toString()));
                dto.setRealCorpus(new BigDecimal(obj[7].toString()));
                dto.setRealInterest(new BigDecimal(obj[8].toString()));
                dto.setRealPunishInterest(new BigDecimal(obj[9].toString()));
                dto.setRealMoney(new BigDecimal(obj[10].toString()));
                dto.setOccurMoney(new BigDecimal(obj[11].toString()));
                dto.setMoney(new BigDecimal(obj[12].toString()));
                dto.setLoanKouType(obj[13].toString());
                dto.setLoanRepayDay(obj[14].toString());
                dto.setId(obj[15].toString());
                dto.setContractId(obj[16].toString());
                dto.setLoanBankId(obj[17].toString());
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
   * 回收维护明细查询Counts（批量）
   * jj
   * @param headId
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public int queryFlowTailCountsByHeadIdMX_LJ(final String headId){
    int counts = 0;
    try {
      Integer count = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(loanFlowTail.flowTailId) from LoanFlowTail loanFlowTail ";
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
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
      counts=count.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return counts;
  }
  /**
   * 回收维护明细查询合计（批量）
   * jj
   * @param headId
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public List queryFlowTailTotalByHeadIdMX_LJ(final String headId){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowHead.realCount,nvl(loanFlowHead.shouldCorpus,0)+nvl(loanFlowHead.shouldOverdueCorpus,0)," +
                  "nvl(loanFlowHead.shouldInterest,0)+nvl(loanFlowHead.shouldOverdueInterest,0),nvl(loanFlowHead.shouldPunishInterest,0)," +
                  " loanFlowHead.realCorpus+loanFlowHead.realOverdueCorpus,loanFlowHead.realInterest+loanFlowHead.realOverdueInterest," +
                  "loanFlowHead.realPunishInterest," +
                  "loanFlowHead.realCorpus+loanFlowHead.realInterest+loanFlowHead.realOverdueCorpus+loanFlowHead.realOverdueInterest" +
                  "+loanFlowHead.realPunishInterest,loanFlowHead.occurMoney," +
                  "loanFlowHead.realCorpus+loanFlowHead.realInterest+loanFlowHead.realOverdueCorpus+loanFlowHead.realOverdueInterest+loanFlowHead.realPunishInterest+loanFlowHead.occurMoney" +
                  " from LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowHead.flowHeadId = ?  and ";
                parameters.add(new Integer(headId));
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));                       
              hql = hql + criterion ;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              List temp_list = new ArrayList();
              Object obj [] = null;
              Iterator it = query.iterate();
              while(it.hasNext()){
                obj  = (Object[])it.next();
                ShouldBackListDTO dto = new ShouldBackListDTO();
                dto.setCounts(obj[0].toString());
                dto.setShouldCorpus(new BigDecimal(obj[1].toString()));
                dto.setShouldInterest(new BigDecimal(obj[2].toString()));
                dto.setShouldPunishInterest(new BigDecimal(obj[3].toString()));
                dto.setRealCorpus(new BigDecimal(obj[4].toString()));
                dto.setRealInterest(new BigDecimal(obj[5].toString()));
                dto.setRealPunishInterest(new BigDecimal(obj[6].toString()));
                dto.setRealMoney(new BigDecimal(obj[7].toString()));
                dto.setOccurMoney(new BigDecimal(obj[8].toString()));
                dto.setMoney(new BigDecimal(obj[9].toString()));
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
   * 回收维护明细打印（批量）
   * jj
   * @param headId
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public List queryFlowTailByHeadIdMXPrint_LJ(final String headId,final String orderBy, final String order, final int start,
      final int pageSize){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTail.yearMonth,loanFlowTail.loanKouAcc,borrower.borrowerName," +
                  "borrower.cardNum,nvl(loanFlowTail.shouldCorpus,0),nvl(loanFlowTail.shouldInterest,0),nvl(loanFlowTail.shouldPunishInterest,0)," +
                  " loanFlowTail.realCorpus,loanFlowTail.realInterest,loanFlowTail.realPunishInterest," +
                  "(loanFlowTail.realCorpus+loanFlowTail.realInterest+loanFlowTail.realPunishInterest),loanFlowTail.occurMoney," +
                  "(loanFlowTail.realCorpus+loanFlowTail.realInterest+loanFlowTail.realPunishInterest+loanFlowTail.occurMoney),loanFlowTail.loanType, " +
                  " borrowerAcc.loanRepayDay,loanFlowTail.flowTailId,loanFlowTail.contractId "+
                  " from LoanFlowTail loanFlowTail,Borrower borrower,BorrowerAcc borrowerAcc ";
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and borrowerAcc.contractId = borrower.contractId and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              String ob = orderBy;
              if (ob == null)
                ob = " loanFlowTail.loanKouAcc ";

              String od = order;
              if (od == null)
                od = "DESC";             
              hql = hql + criterion+ " order by " + ob + ",loanFlowTail.yearMonth " + od ;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }              
              List temp_list = new ArrayList();
              Object obj [] = null;
              Iterator it = query.iterate();
              while(it.hasNext()){
                obj  = (Object[])it.next();
                ShouldBackListDTO dto = new ShouldBackListDTO();
                dto.setLoanKouYearmonth(obj[0].toString());
                dto.setLoanKouAcc(obj[1].toString());
                dto.setBorrowerName(obj[2].toString());
                dto.setCardNum(obj[3].toString());
                dto.setShouldCorpus(new BigDecimal(obj[4].toString()));
                dto.setShouldInterest(new BigDecimal(obj[5].toString()));
                dto.setShouldPunishInterest(new BigDecimal(obj[6].toString()));
                dto.setRealCorpus(new BigDecimal(obj[7].toString()));
                dto.setRealInterest(new BigDecimal(obj[8].toString()));
                dto.setRealPunishInterest(new BigDecimal(obj[9].toString()));
                dto.setRealMoney(new BigDecimal(obj[10].toString()));
                dto.setOccurMoney(new BigDecimal(obj[11].toString()));
                dto.setMoney(new BigDecimal(obj[12].toString()));
                dto.setLoanKouType(obj[13].toString());
                dto.setLoanRepayDay(obj[14].toString());
                dto.setId(obj[15].toString());
                dto.setContractId(obj[16].toString());
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
   * 银行代扣导出查询列表信息
   * jj
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */ 
  public List queryFlowTailByLoanBankId_LJ(final String loanBankId,final String headId,final String loanKouAcc,final String contractId,final String borrowerName,
      final String cardNum,final String bizType,final String bizSt,final String orderBy, final String order, 
      final int start,final int pageSize,final int page,final String yearMonth,final String day,final String bizDate){
    List list = null;
    final String year=bizDate.substring(0,4);
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select borrower.borrowerName,borrower.cardNum,loanFlowTail.yearMonth,loanFlowTail.loanKouAcc,loanFlowTail.contractId," +
                  "nvl(loanFlowTail.shouldCorpus,0),nvl(loanFlowTail.shouldInterest,0),nvl(loanFlowTail.shouldPunishInterest,0)," +
                  "nvl(loanFlowTail.shouldCorpus,0)+nvl(loanFlowTail.shouldInterest,0)+nvl(loanFlowTail.shouldPunishInterest,0)," +
                  " loanFlowTail.occurMoney," +
                  " nvl(loanFlowTail.shouldCorpus,0)+nvl(loanFlowTail.shouldInterest,0)+nvl(loanFlowTail.shouldPunishInterest,0)+loanFlowTail.occurMoney," +
                  " loanFlowTail.loanType,loanFlowTail.flowTailId," +
                  " borrowerAcc.loanRepayDay,nvl(borrowerAcc.ovaerLoanRepay,0),loanFlowHead.flowHeadId,loanFlowHead.reserveaA,loanFlowHead.bizDate "+
                  " from LoanFlowTail loanFlowTail,Borrower borrower,BorrowerAcc borrowerAcc,LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanFlowHead.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId));
              }
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (borrowerName != null && !borrowerName.equals("")) {
                criterion += "borrower.borrowerName like ?  and ";
                parameters.add("%"+borrowerName+"%");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = ?  and ";
                parameters.add(cardNum);
              }
              if (bizType != null && !bizType.equals("")) {
                criterion += "loanFlowHead.bizType = ?  and ";
                parameters.add(bizType);
              }
              if (bizSt != null && !bizSt.equals("")) {
                criterion += "loanFlowHead.bizSt = ?  and ";
                parameters.add(bizSt);
              }
              if (yearMonth != null && !yearMonth.equals("")&& day !=null && !day.equals("")) {
                criterion += "loanFlowHead.reserveaA = ?  and ";
                parameters.add(yearMonth+day);
              }else{
                criterion += "(loanFlowHead.reserveaA between ?  and ?) and";
                parameters.add(bizDate);
                parameters.add(year+"1231");
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and borrowerAcc.contractId = borrower.contractId and " +
                    " loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and loanFlowHead.batchNum is null and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              
              String ob = orderBy;
              if (ob == null)
                ob = " loanFlowTail.loanKouAcc ";
              String od = order;
              if (od == null)
                od = "DESC";     
              
              hql = hql + criterion+ " order by " + ob + " " + od +",loanFlowTail.yearMonth ";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);       
              Iterator it=query.iterate();
              //List queryList=query.list();
              if(!it.hasNext()){           
                query.setFirstResult(pageSize*(page-2));
                if (pageSize > 0)
                  query.setMaxResults(pageSize);
                it=query.iterate();
              }
              List temp_list = new ArrayList();
              Object obj [] = null;
              while(it.hasNext()){
                obj  = (Object[])it.next();
                BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
                dto.setBorrowerName(obj[0].toString());
                dto.setCardNum(obj[1].toString());
                dto.setLoanKouYearmonth(obj[2].toString());
                dto.setLoanKouAcc(obj[3].toString());
                dto.setContractId(obj[4].toString());
                dto.setShouldCorpus(new BigDecimal(obj[5].toString()));
                dto.setShouldInterest(new BigDecimal(obj[6].toString()));
                dto.setPunishInterest(new BigDecimal(obj[7].toString()));
                dto.setSumMoney(new BigDecimal(obj[8].toString()));
                dto.setOccurMoney(new BigDecimal(obj[9].toString()));
                dto.setRealMoney(new BigDecimal(obj[10].toString()));
                dto.setLoanType(obj[11].toString());
                dto.setId(obj[12].toString());
                dto.setLoanRepayDay(obj[13].toString());
                dto.setOvaerLoanRepay(new BigDecimal(obj[14].toString()));
                dto.setHeadId(obj[15].toString());
                if(obj[16]!=null){
                  dto.setReserveaA(obj[16].toString());
                }
                dto.setBizDate(obj[17].toString());
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
   * 银行代扣导出查询列表信息---有批次号的，为公积金还贷做准备的
   * 郭婧平
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @param batchNum
   * @return
   */ 
  public List queryFlowTailByLoanBankId_GJP(final String loanBankId,final String headId,final String loanKouAcc,final String contractId,final String borrowerName,
      final String cardNum,final String bizType,final String bizSt,final String orderBy, final String order, 
      final int start,final int pageSize,final int page,final String yearMonth,final String day,final String bizDate,final String batchNum){
    List list = null;
    final String year=bizDate.substring(0,4);
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select borrower.borrowerName,borrower.cardNum,loanFlowTail.yearMonth,loanFlowTail.loanKouAcc,loanFlowTail.contractId," +
                  "nvl(loanFlowTail.shouldCorpus,0),nvl(loanFlowTail.shouldInterest,0),nvl(loanFlowTail.shouldPunishInterest,0)," +
                  "nvl(loanFlowTail.shouldCorpus,0)+nvl(loanFlowTail.shouldInterest,0)+nvl(loanFlowTail.shouldPunishInterest,0)," +
                  " loanFlowTail.occurMoney," +
                  " nvl(loanFlowTail.shouldCorpus,0)+nvl(loanFlowTail.shouldInterest,0)+nvl(loanFlowTail.shouldPunishInterest,0)+loanFlowTail.occurMoney," +
                  " loanFlowTail.loanType,loanFlowTail.flowTailId," +
                  " borrowerAcc.loanRepayDay,nvl(borrowerAcc.ovaerLoanRepay,0),loanFlowHead.flowHeadId,loanFlowHead.reserveaA,loanFlowHead.bizDate "+
                  " from LoanFlowTail loanFlowTail,Borrower borrower,BorrowerAcc borrowerAcc,LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanFlowHead.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId));
              }
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (borrowerName != null && !borrowerName.equals("")) {
                criterion += "borrower.borrowerName like ?  and ";
                parameters.add("%"+borrowerName+"%");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = ?  and ";
                parameters.add(cardNum);
              }
              if (bizType != null && !bizType.equals("")) {
                criterion += "loanFlowHead.bizType = ?  and ";
                parameters.add(bizType);
              }
              if (bizSt != null && !bizSt.equals("")) {
                criterion += "loanFlowHead.bizSt = ?  and ";
                parameters.add(bizSt);
              }
              if (yearMonth != null && !yearMonth.equals("")&& day !=null && !day.equals("")) {
                criterion += "loanFlowHead.reserveaA = ?  and ";
                parameters.add(yearMonth+day);
              }else{
                criterion += "(loanFlowHead.reserveaA between ?  and ?) and ";
                parameters.add(bizDate);
                parameters.add(year+"1231");
              }
              if (batchNum != null && !batchNum.equals("")) {
                criterion += " loanFlowHead.batchNum = ?  and ";
                parameters.add(batchNum);
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and borrowerAcc.contractId = borrower.contractId and " +
                    " loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and "
                +"loanFlowTail.loanKouAcc in (select fundloanInfo.loanKouAcc from FundloanInfo fundloanInfo where fundloanInfo.reserveaB = '1' and fundloanInfo.status = '0') and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              
              String ob = orderBy;
              if (ob == null)
                ob = " loanFlowTail.loanKouAcc ";
              String od = order;
              if (od == null)
                od = "DESC";     
              
              hql = hql + criterion+ " order by " + ob + " " + od +",loanFlowTail.yearMonth ";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);       
              Iterator it=query.iterate();
              //List queryList=query.list();
              if(!it.hasNext()){           
                query.setFirstResult(pageSize*(page-2));
                if (pageSize > 0)
                  query.setMaxResults(pageSize);
                it=query.iterate();
              }
              List temp_list = new ArrayList();
              Object obj [] = null;
              while(it.hasNext()){
                obj  = (Object[])it.next();
                BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
                dto.setBorrowerName(obj[0].toString());
                dto.setCardNum(obj[1].toString());
                dto.setLoanKouYearmonth(obj[2].toString());
                dto.setLoanKouAcc(obj[3].toString());
                dto.setContractId(obj[4].toString());
                dto.setShouldCorpus(new BigDecimal(obj[5].toString()));
                dto.setShouldInterest(new BigDecimal(obj[6].toString()));
                dto.setPunishInterest(new BigDecimal(obj[7].toString()));
                dto.setSumMoney(new BigDecimal(obj[8].toString()));
                dto.setOccurMoney(new BigDecimal(obj[9].toString()));
                dto.setRealMoney(new BigDecimal(obj[10].toString()));
                dto.setLoanType(obj[11].toString());
                dto.setId(obj[12].toString());
                dto.setLoanRepayDay(obj[13].toString());
                dto.setOvaerLoanRepay(new BigDecimal(obj[14].toString()));
                dto.setHeadId(obj[15].toString());
                if(obj[16]!=null){
                  dto.setReserveaA(obj[16].toString());
                }
                dto.setBizDate(obj[17].toString());
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
   * 银行代扣导出查询列表信息记录数
   * jj
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @return
   */
  public int queryFlowTailCountsByLoanBankId_LJ(final String loanBankId,final String headId,final String loanKouAcc,final String contractId,final String borrowerName,
      final String cardNum,final String bizType,final String bizSt,final String yearMonth,final String day,final String bizDate){
    int count = 0;
    final String year=bizDate.substring(0,4);
    try {
      Integer counts = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(loanFlowTail.flowTailId)"+
                  " from LoanFlowTail loanFlowTail,Borrower borrower,LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanFlowHead.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId));
              }
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (borrowerName != null && !borrowerName.equals("")) {
                criterion += "borrower.borrowerName like ?  and ";
                parameters.add("%"+borrowerName+"%");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = ?  and ";
                parameters.add(cardNum);
              }
              if (bizType != null && !bizType.equals("")) {
                criterion += "loanFlowHead.bizType = ?  and ";
                parameters.add(bizType);
              }
              if (bizSt != null && !bizSt.equals("")) {
                criterion += "loanFlowHead.bizSt = ?  and ";
                parameters.add(bizSt);
              }
              if (yearMonth != null && !yearMonth.equals("")&& day !=null && !day.equals("")) {
                criterion += "loanFlowHead.reserveaA = ?  and ";
                parameters.add(yearMonth+day);
              }else{
                criterion += "(loanFlowHead.reserveaA between ?  and ?) and";
                parameters.add(bizDate);
                parameters.add(year+"1231");
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and " +
                " loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and loanFlowHead.batchNum is null and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
                            
              hql = hql + criterion ;
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
   * 银行代扣导出查询列表信息记录数---有批次号的，为公积金还贷做准备的
   * 郭婧平
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param batchNum
   * @return
   */
  public int queryFlowTailCountsByLoanBankId_GJP(final String loanBankId,final String headId,final String loanKouAcc,final String contractId,final String borrowerName,
      final String cardNum,final String bizType,final String bizSt,final String yearMonth,final String day,final String bizDate,final String batchNum){
    int count = 0;
    final String year=bizDate.substring(0,4);
    try {
      Integer counts = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(loanFlowTail.flowTailId)"+
                  " from LoanFlowTail loanFlowTail,Borrower borrower,LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanFlowHead.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId));
              }
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (borrowerName != null && !borrowerName.equals("")) {
                criterion += "borrower.borrowerName like ?  and ";
                parameters.add("%"+borrowerName+"%");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = ?  and ";
                parameters.add(cardNum);
              }
              if (bizType != null && !bizType.equals("")) {
                criterion += "loanFlowHead.bizType = ?  and ";
                parameters.add(bizType);
              }
              if (bizSt != null && !bizSt.equals("")) {
                criterion += "loanFlowHead.bizSt = ?  and ";
                parameters.add(bizSt);
              }
              if (yearMonth != null && !yearMonth.equals("")&& day !=null && !day.equals("")) {
                criterion += "loanFlowHead.reserveaA = ?  and ";
                parameters.add(yearMonth+day);
              }else{
                criterion += "(loanFlowHead.reserveaA between ?  and ?) and ";
                parameters.add(bizDate);
                parameters.add(year+"1231");
              }
              
              if (batchNum != null && !batchNum.equals("")) {
                criterion += " loanFlowHead.batchNum = ?  and ";
                parameters.add(batchNum);
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and " +
                " loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and "
                +"loanFlowTail.loanKouAcc in (select fundloanInfo.loanKouAcc from FundloanInfo fundloanInfo where fundloanInfo.reserveaB = '1' and fundloanInfo.status = '0') and "                
                    + criterion.substring(0, criterion.lastIndexOf("and"));
                            
              hql = hql + criterion ;
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
   * 银行代扣导出合计
   * jj
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @return
   */
  public List queryFlowTailTotalByLoanBankId_LJ(final String loanBankId,final String headId,final String loanKouAcc,final String contractId,final String borrowerName,
      final String cardNum,final String bizType,final String bizSt,final String yearMonth,final String day,final String bizDate){
    List list = null;
    final String year=bizDate.substring(0,4);
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select nvl(count(distinct loanFlowTail.loanKouAcc ),0), nvl(sum(loanFlowTail.shouldCorpus),0)," +
              " nvl(sum(loanFlowTail.shouldInterest),0),nvl(sum(loanFlowTail.shouldPunishInterest),0)," +               
              "nvl(sum(loanFlowTail.shouldCorpus)+sum(loanFlowTail.shouldInterest)+sum(loanFlowTail.shouldPunishInterest),0)," +
              "nvl(abs(sum(loanFlowTail.occurMoney)),0)," +
"nvl(sum(loanFlowTail.realCorpus)+sum(loanFlowTail.realInterest)+sum(loanFlowTail.realPunishInterest)+sum(loanFlowTail.occurMoney),0)" +
                  " from Borrower borrower,LoanFlowTail loanFlowTail,LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanFlowHead.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId));
              }
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (borrowerName != null && !borrowerName.equals("")) {
                criterion += "borrower.borrowerName like ?  and ";
                parameters.add("%"+borrowerName+"%");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = ?  and ";
                parameters.add(cardNum);
              }
              if (bizType != null && !bizType.equals("")) {
                criterion += "loanFlowHead.bizType = ?  and ";
                parameters.add(bizType);
              }
              if (bizSt != null && !bizSt.equals("")) {
                criterion += "loanFlowHead.bizSt = ?  and ";
                parameters.add(bizSt);
              }
              if (yearMonth != null && !yearMonth.equals("")&& day !=null && !day.equals("")) {
                criterion += "loanFlowHead.reserveaA = ?  and ";
                parameters.add(yearMonth+day);
              }else{
                criterion += "(loanFlowHead.reserveaA between ?  and ?) and";
                parameters.add(bizDate);
                parameters.add(year+"1231");
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and "+
                " loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion ;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator it=query.iterate();
              List temp_list = new ArrayList();
              Object obj [] = null;
              while(it.hasNext()){
                obj  = (Object[])it.next();
                BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
                dto.setShouldCount(obj[0].toString());
                dto.setShouldCorpus(new BigDecimal(obj[1].toString()));
                dto.setShouldInterest(new BigDecimal(obj[2].toString()));
                dto.setPunishInterest(new BigDecimal(obj[3].toString()));
                dto.setSumMoney(new BigDecimal(obj[4].toString()));
                dto.setOccurMoney(new BigDecimal(obj[5].toString()));
                dto.setRealMoney(new BigDecimal(obj[6].toString()));
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
   * 银行代扣导出合计---有批次号的，为公积金还贷做准备的
   * 郭婧平
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @return
   */
  public List queryFlowTailTotalByLoanBankId_GJP(final String loanBankId,final String headId,final String loanKouAcc,final String contractId,final String borrowerName,
      final String cardNum,final String bizType,final String bizSt,final String yearMonth,final String day,final String bizDate,final String batchNum){
    List list = null;
    final String year=bizDate.substring(0,4);
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select nvl(count(distinct loanFlowTail.loanKouAcc ),0), nvl(sum(loanFlowTail.shouldCorpus),0),"
              + " nvl(sum(loanFlowTail.shouldInterest),0),nvl(sum(loanFlowTail.shouldPunishInterest),0),"
              + " nvl(sum(loanFlowTail.shouldCorpus)+sum(loanFlowTail.shouldInterest)+sum(loanFlowTail.shouldPunishInterest),0),"
              + " nvl(abs(sum(loanFlowTail.occurMoney)),0),"
              + " nvl(sum(loanFlowTail.realCorpus)+sum(loanFlowTail.realInterest)+sum(loanFlowTail.realPunishInterest)+sum(loanFlowTail.occurMoney),0)"
              + " from Borrower borrower,LoanFlowTail loanFlowTail,LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanFlowHead.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId));
              }
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (borrowerName != null && !borrowerName.equals("")) {
                criterion += "borrower.borrowerName like ?  and ";
                parameters.add("%"+borrowerName+"%");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = ?  and ";
                parameters.add(cardNum);
              }
              if (bizType != null && !bizType.equals("")) {
                criterion += "loanFlowHead.bizType = ?  and ";
                parameters.add(bizType);
              }
              if (bizSt != null && !bizSt.equals("")) {
                criterion += "loanFlowHead.bizSt = ?  and ";
                parameters.add(bizSt);
              }
              if (yearMonth != null && !yearMonth.equals("")&& day !=null && !day.equals("")) {
                criterion += "loanFlowHead.reserveaA = ?  and ";
                parameters.add(yearMonth+day);
              }else{
                criterion += "(loanFlowHead.reserveaA between ?  and ?) and ";
                parameters.add(bizDate);
                parameters.add(year+"1231");
              }
              if (batchNum != null && !batchNum.equals("")) {
                criterion += " loanFlowHead.batchNum = ?  and ";
                parameters.add(batchNum);
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and "+
                " loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and "
                +"loanFlowTail.loanKouAcc in (select fundloanInfo.loanKouAcc from FundloanInfo fundloanInfo where fundloanInfo.reserveaB = '1' and fundloanInfo.status = '0') and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion ;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator it=query.iterate();
              List temp_list = new ArrayList();
              Object obj [] = null;
              while(it.hasNext()){
                obj  = (Object[])it.next();
                BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
                dto.setShouldCount(obj[0].toString());
                dto.setShouldCorpus(new BigDecimal(obj[1].toString()));
                dto.setShouldInterest(new BigDecimal(obj[2].toString()));
                dto.setPunishInterest(new BigDecimal(obj[3].toString()));
                dto.setSumMoney(new BigDecimal(obj[4].toString()));
                dto.setOccurMoney(new BigDecimal(obj[5].toString()));
                dto.setRealMoney(new BigDecimal(obj[6].toString()));
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
   * 银行代扣导出(足额)
   * 数据导出
   * jj
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */ 
  public List queryExportFlowTail_LJA(final String headId){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select borrower.borrowerName,loanFlowTail.yearMonth,loanFlowTail.loanKouAcc,loanFlowTail.contractId," +
                  " nvl(loanFlowTail.shouldCorpus,0)+nvl(loanFlowTail.shouldInterest,0)+nvl(loanFlowTail.shouldPunishInterest,0)+nvl(loanFlowTail.occurMoney,0)" +
                  " from LoanFlowTail loanFlowTail,Borrower borrower ";
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId  and nvl(loanFlowTail.shouldCorpus-loanFlowTail.realCorpus,0)>0 and "  
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              
              hql = hql + criterion + "order by loanFlowTail.loanKouAcc DESC,loanFlowTail.yearMonth ";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
    
              Iterator it=query.iterate();
              List temp_list = new ArrayList();
              Object obj [] = null;
              while(it.hasNext()){
                obj  = (Object[])it.next();
                BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
                dto.setBorrowerName(obj[0].toString());
                dto.setLoanKouYearmonth(obj[1].toString());
                dto.setLoanKouAcc(obj[2].toString());
                dto.setContractId(obj[3].toString());
                dto.setRealMoney(new BigDecimal(obj[4].toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
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
  // 公积金还贷导出用.
  public List queryExportFlowTail_fund(final String headId){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select borrower.borrowerName,loanFlowTail.yearMonth,loanFlowTail.loanKouAcc,loanFlowTail.contractId," +
                  " nvl(loanFlowTail.shouldCorpus,0)+nvl(loanFlowTail.shouldInterest,0)+nvl(loanFlowTail.shouldPunishInterest,0)+nvl(loanFlowTail.occurMoney,0)" +
                  " from LoanFlowTail loanFlowTail,Borrower borrower ";//,FundloanInfo fundloanInfo
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (criterion.length() != 0)
                criterion = " where borrower.contractId = loanFlowTail.contractId  and nvl(loanFlowTail.shouldCorpus-loanFlowTail.realCorpus,0)>0 and " 
                 //   +" loanFlowTail.loanKouAcc=fundloanInfo.loanKouAcc and fundloanInfo.reserveaB='1' and fundloanInfo.status='0' and fundloanInfo.seq='0' and "
                  +" loanFlowTail.loanKouAcc in (select fundloanInfo.loanKouAcc from FundloanInfo fundloanInfo where fundloanInfo.reserveaB = '1' and fundloanInfo.status = '0') and " 
                  + criterion.substring(0, criterion.lastIndexOf("and"));
              
              hql = hql + criterion + "order by loanFlowTail.loanKouAcc DESC,loanFlowTail.yearMonth ";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
    
              Iterator it=query.iterate();
              List temp_list = new ArrayList();
              Object obj [] = null;
              while(it.hasNext()){
                obj  = (Object[])it.next();
                BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
                dto.setBorrowerName(obj[0].toString());
                dto.setLoanKouYearmonth(obj[1].toString());
                dto.setLoanKouAcc(obj[2].toString());
                dto.setContractId(obj[3].toString());
                dto.setRealMoney(new BigDecimal(obj[4].toString()));
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
   * 银行代扣导出(全额)
   * 数据导出
   * jj
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */ 
  public List queryExportFlowTail_LJB(final String headId){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select borrower.borrowerName,loanFlowTail.loanKouAcc,loanFlowTail.contractId," +
                  " sum(nvl(loanFlowTail.shouldCorpus,0)+nvl(loanFlowTail.shouldInterest,0)+nvl(loanFlowTail.shouldPunishInterest,0)+nvl(loanFlowTail.occurMoney,0))" +
                  " from LoanFlowTail loanFlowTail,Borrower borrower ";
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and " 
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              
              hql = hql + criterion+"group by borrower.borrowerName,loanFlowTail.loanKouAcc,loanFlowTail.contractId  order by loanFlowTail.loanKouAcc DESC ";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
    
              Iterator it=query.iterate();
              List temp_list = new ArrayList();
              Object obj [] = null;
              while(it.hasNext()){
                obj  = (Object[])it.next();
                BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
                dto.setBorrowerName(obj[0].toString());
                dto.setLoanKouAcc(obj[1].toString());
                dto.setContractId(obj[2].toString());
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
   * 银行代扣导出打印
   * jj
   * @param loanBankId
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param bizType
   * @param bizSt
   * @return
   */
  public List queryPrintFlowTailByLoanBankId_LJ(final String loanBankId,final String headId,final String loanKouAcc,final String contractId,final String borrowerName,
      final String cardNum,final String bizType,final String bizSt){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select borrower.borrowerName,borrower.cardNum,loanFlowTail.yearMonth,loanFlowTail.loanKouAcc,loanFlowTail.contractId," +
                  "nvl(loanFlowTail.shouldCorpus,0),nvl(loanFlowTail.shouldInterest,0),nvl(loanFlowTail.shouldPunishInterest,0)," +
                  "nvl(loanFlowTail.shouldCorpus,0)+nvl(loanFlowTail.shouldInterest,0)+nvl(loanFlowTail.shouldPunishInterest,0)," +
                  " loanFlowTail.occurMoney," +
                  " nvl(loanFlowTail.shouldCorpus,0)+nvl(loanFlowTail.shouldInterest,0)+nvl(loanFlowTail.shouldPunishInterest,0)+loanFlowTail.occurMoney," +
                  " loanFlowTail.loanType,loanFlowTail.flowTailId," +
                  " borrowerAcc.loanRepayDay,nvl(borrowerAcc.ovaerLoanRepay,0) "+
                  " from LoanFlowTail loanFlowTail,Borrower borrower,BorrowerAcc borrowerAcc,LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanFlowHead.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId));
              }
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (borrowerName != null && !borrowerName.equals("")) {
                criterion += "borrower.borrowerName like ?  and ";
                parameters.add("%"+borrowerName+"%");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = ?  and ";
                parameters.add(cardNum);
              }
              if (bizType != null && !bizType.equals("")) {
                criterion += "loanFlowHead.bizType = ?  and ";
                parameters.add(bizType);
              }
              if (bizSt != null && !bizSt.equals("")) {
                criterion += "loanFlowHead.bizSt = ?  and ";
                parameters.add(bizSt);
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and borrowerAcc.contractId = borrower.contractId and " +
                    " loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));  
              
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator it=query.iterate();
              List temp_list = new ArrayList();
              Object obj [] = null;
              while(it.hasNext()){
                obj  = (Object[])it.next();
                BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
                dto.setBorrowerName(obj[0].toString());
                dto.setCardNum(obj[1].toString());
                dto.setLoanKouYearmonth(obj[2].toString());
                dto.setLoanKouAcc(obj[3].toString());
                dto.setContractId(obj[4].toString());
                dto.setShouldCorpus(new BigDecimal(obj[5].toString()));
                dto.setShouldInterest(new BigDecimal(obj[6].toString()));
                dto.setPunishInterest(new BigDecimal(obj[7].toString()));
                dto.setSumMoney(new BigDecimal(obj[8].toString()));
                dto.setOccurMoney(new BigDecimal(obj[9].toString()));
                dto.setRealMoney(new BigDecimal(obj[10].toString()));
                dto.setLoanType(obj[11].toString());
                dto.setId(obj[12].toString());
                dto.setLoanRepayDay(obj[13].toString());
                dto.setOvaerLoanRepay(new BigDecimal(obj[14].toString()));
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
   * 银行代扣导出全部删除
   * jj
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param bizType
   * @param bizSt
   * @return
   */
  public List deleteFlowTailByLoanBankId_LJ(final String headId,final String loanKouAcc,final String contractId,final String borrowerName,
      final String cardNum,final String bizType,final String bizSt){
    List list = null;
    try {
     
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTails.flowTailId from LoanFlowTail loanFlowTails,Borrower borrower,LoanFlowHead loanFlowHead ";
              //Vector parameters = new Vector();
              String criterion = "";
  
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTails.flowHeadId = "+headId+"  and ";
                //parameters.add(new BigDecimal(headId));
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTails.loanKouAcc = "+loanKouAcc+" and ";
                //parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTails.contractId = "+contractId+"  and ";
                //parameters.add(contractId);
              }
              if (borrowerName != null && !borrowerName.equals("")) {
                criterion += "borrower.borrowerName like '%"+borrowerName+"%'  and ";
                //parameters.add("%"+borrowerName+"%");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = "+cardNum+"  and ";
                //parameters.add(cardNum);
              }
              if (bizType != null && !bizType.equals("")) {
                criterion += "loanFlowHead.bizType = "+bizType+"  and ";
                //parameters.add(bizType);
              }
              if (bizSt != null && !bizSt.equals("")) {
                criterion += "loanFlowHead.bizSt = "+bizSt+"  and ";
                //parameters.add(bizSt);
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTails.contractId  and " +
                    " loanFlowHead.flowHeadId = loanFlowTails.flowHeadId and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;             
              //String deletehsql="delete from LoanFlowTail loanFlowTail2 where loanFlowTail2.flowTailId in ( "+hql+" )"; 
              Query query = session.createQuery(hql);                   
              //query.list();
//              for (int i = 0; i < parameters.size(); i++) {
//                query.setParameter(i, parameters.get(i));
//              }
              Iterator it=query.iterate();
              Object obj=null;
              while(it.hasNext()){
                obj=(Object)it.next();
                String deletehsql=" delete from LoanFlowTail loanFlowTail2 where loanFlowTail2.flowTailId="+obj.toString()+""; 
                session.createQuery(deletehsql).executeUpdate();
              }
              return null;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  

  /**
   * 根据头表ID和贷款账号查询流水尾表
   * 银行代扣导入全额扣款更新尾表时调用
   * jj
   * @param headId
   * @param contractId
   * @return
   */
  public List queryExportFlowTailByHeadId_LJ(final String headId,final String loanKouAcc){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTail.contractId,loanFlowTail.shouldCorpus," +
                  " loanFlowTail.shouldInterest,loanFlowTail.shouldPunishInterest,loanFlowTail.loanType,loanFlowTail.flowTailId," +
                  "loanFlowTail.loanKouAcc,loanFlowTail.realCorpus,loanFlowTail.realInterest,loanFlowTail.realPunishInterest,loanFlowTail.occurMoney" +
                  " from LoanFlowTail loanFlowTail";
              String criterion = "";
              Vector parameters = new Vector();
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                  parameters.add(new BigDecimal(headId));
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                  parameters.add(loanKouAcc);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion +"order by loanFlowTail.yearMonth";             
              Query query = session.createQuery(hql);  
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }                 
              Iterator it=query.iterate();
              List temp_list = new ArrayList();
              Object obj [] = null;
              while(it.hasNext()){
                obj  = (Object[])it.next();
                ShouldBackListDTO dto = new ShouldBackListDTO();
                dto.setContractId(obj[0].toString());
                dto.setShouldCorpus(new BigDecimal(obj[1].toString()));
                dto.setShouldInterest(new BigDecimal(obj[2].toString()));
                dto.setShouldPunishInterest(new BigDecimal(obj[3].toString()));
                dto.setLoanKouType(obj[4].toString());
                dto.setId(obj[5].toString());
                dto.setLoanKouAcc(obj[6].toString());
                dto.setRealCorpus(new BigDecimal(obj[7].toString()));
                dto.setRealInterest(new BigDecimal(obj[8].toString()));
                dto.setRealPunishInterest(new BigDecimal(obj[9].toString()));
                dto.setOccurMoney(new BigDecimal(obj[10].toString()));
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
   * 银行代扣导出删除时调用
   * 根据头表ID查询尾表中是否还存在记录
   * jj
   * @param headId
   * @return
   */
  public List queryBankLoanFlowTailByHeadId_LJ(final String headId){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTail.contractId,nvl(loanFlowTail.shouldCorpus,0),nvl(loanFlowTail.shouldInterest,0),nvl(loanFlowTail.shouldPunishInterest,0)," +
              " loanFlowTail.occurMoney," +         
              " loanFlowTail.loanType" +
              " from LoanFlowTail loanFlowTail ";
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it=query.iterate();
          List temp_list = new ArrayList();
          Object obj [] = null;
          while(it.hasNext()){
            obj  = (Object[])it.next();
            BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
            dto.setContractId(obj[0].toString());
            dto.setShouldCorpus(new BigDecimal(obj[1].toString()));
            dto.setShouldInterest(new BigDecimal(obj[2].toString()));
            dto.setPunishInterest(new BigDecimal(obj[3].toString()));
            dto.setOccurMoney(new BigDecimal(obj[4].toString()));
            dto.setLoanType(obj[5].toString());
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
   * 根据PL202主键查询该笔业务是否是最后一笔业务
   * @param flowHeadId
   * @return 返回该笔业务最大的FlowHeadId
   * @author 付云峰
   */
  public String queryMaxFlowHeadId(final String flowHeadId) {
    Object obj = "";
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select max(p2.flow_head_id) "
              + "from pl203 p3,pl202 p2 where "
              + "p3.flow_head_id=p2.flow_head_id and "
              + "p2.biz_type in ('2','3','4','5','6','7') and "
              + "(p2.is_adjust is null or p2.is_adjust='') and "
              + "p3.contract_id=(select distinct p.contract_id from pl203 p where p.flow_head_id=?)";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(flowHeadId));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj.toString();
  }
  
  /**
   * 查询此业务是否做过除发放与保证金以外的业务
   * @param flowHeadId
   * @return
   * @author 付云峰
   */
  public int queryIsExistFlowHeadId(final String flowHeadId){
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select p2.flow_head_id " +
                       "from pl203 p3,pl202 p2 where " +
                       "p3.flow_head_id=p2.flow_head_id and " +
                       "p2.biz_type in ('2','3','4','5','6','7','8','9','10','11','12','13','15') and " +
                       "p3.contract_id=(select p3.contract_id from pl203 p3 where p3.flow_head_id=?)";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(flowHeadId));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }
  
  /**
   * 查询批量回收中每个合同id不是最后一笔业务的相关业务信息
   * @param flowHeadId
   * @param loanbankList
   * @return 不是最后一笔业务的相关信息
   * @author 付云峰
   */
  public List queryIsNotMaxFlowHeadId(final String flowHeadId, final List loanbankList){
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Vector parameters = new Vector();
          String criterion = "";
          String sql = "select p3.contract_id,p3.flow_head_id,p3.loan_kou_acc " +
                       "from pl203 p3 " +
                       "where p3.flow_head_id = ? and " +
                       "p3.contract_id in (select distinct(p3.contract_id) " +
                                           "from pl203 p3, pl202 p2 " +
                                           "where p3.flow_head_id = p2.flow_head_id and p2.flow_head_id > ? and " +
                                           "(p2.is_adjust is null or p2.is_adjust='') ";
          
          parameters.add(new Integer(flowHeadId));
          parameters.add(new Integer(flowHeadId));
          
          if (loanbankList != null && loanbankList.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList.size(); i++) {
              criterion += " p2.loan_bank_id=? or ";
              parameters.add(loanbankList.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          
          if (criterion.length() != 0)
            criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));
          
          sql = sql + criterion + ")";
          
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
   * 根据贷款帐号来查询贷款人姓名 
   * 由于此方法用来进行错帐调整所以写在这个DAO下
   * @param flowHeadId
   * @param loanbankList
   * @return 不是最后一笔业务的相关信息
   * @author 付云峰
   */
  public String queryBorrowerNameByLoanKouAcc(final String loanKouAcc,
      final List loanbankList) {
    String borrowerName = "";
    try {
      borrowerName = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = "select " + "p0.borrower_name from pl111 p1, "
                  + "pl110 p0 " + "where p1.contract_id = p0.contract_id and "
                  + "p1.loan_kou_acc = ?";
              Vector parameters = new Vector();
              String criterion = "";
              
              parameters.add(loanKouAcc.trim());
              if (loanbankList != null && loanbankList.size() > 0) {
                criterion += "( ";
                for (int i = 0; i < loanbankList.size(); i++) {
                  criterion += " p1.loan_bank_id=? or ";
                  parameters.add(loanbankList.get(i));
                }
                criterion = criterion.substring(0, criterion.lastIndexOf("or"));
                criterion += ") and";
              }

              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              sql = sql + criterion;
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

    return borrowerName;
  }
  public String queryBorrowerBankIdByLoanKouAcc(final String loanKouAcc,
      final List loanbankList) {
    String bankId = "";
    try {
      bankId = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String sql = "select p1.loan_bank_id from pl111 p1 where p1.loan_kou_acc = ?";
              Vector parameters = new Vector();
              String criterion = "";
              
              parameters.add(loanKouAcc.trim());
              if (loanbankList != null && loanbankList.size() > 0) {
                criterion += "( ";
                for (int i = 0; i < loanbankList.size(); i++) {
                  criterion += " p1.loan_bank_id=? or ";
                  parameters.add(loanbankList.get(i));
                }
                criterion = criterion.substring(0, criterion.lastIndexOf("or"));
                criterion += ") and";
              }

              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              sql = sql + criterion;
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult().toString();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return bankId;
  }
  
  /**
   * 根据PL202的头表id查询被调整的业务尾表中的内容
   * @param flowHeadId
   * @return
   * @author 付云峰
   */
  public List queryLoanFlowTailByFlowHeadId(final String flowHeadId){
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select " +
                       "p03.contract_id as contractidtail," +
                       "p03.loan_kou_acc as loankouacctail," +
                       "p03.year_month as yearmonthtail," +
                       "nvl(p03.should_corpus,0) as shocortail," +
                       "nvl(p03.should_interest,0) as shointtail," +
                       "nvl(p03.should_punish_interest,0) as shopuninttail," +
                       "nvl(p03.temp_punish_interest,0) as temppuninttail," +
                       "nvl(p03.real_corpus,0) as realcortail," +
                       "nvl(p03.real_interest,0) as realinttail," +
                       "nvl(p03.real_punish_interest,0) as realpuninttail," +
                       "nvl(p03.occur_money,0) as occurmoneytail," +
                       "p03.loan_type as loantypetail " +
                       "from pl203 p03 where p03.flow_head_id=? ";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(flowHeadId));
          
          AdjustAccountTaSaveDTO temp_adjustAccountTaSaveDTO = null;
          List temp_adjustAccountTaSaveDTOList = new ArrayList();
          Object[] obj = null;
          Iterator it = query.list().iterator();
          
          while (it.hasNext()) {
            temp_adjustAccountTaSaveDTO = new AdjustAccountTaSaveDTO();
            obj=(Object[])it.next();
            if (obj[0]!=null) {
              temp_adjustAccountTaSaveDTO.setContractId(obj[0].toString());
            }
            if (obj[1]!=null) {
              temp_adjustAccountTaSaveDTO.setLoanKouAcc(obj[1].toString());
            }
            if (obj[2]!=null) {
              temp_adjustAccountTaSaveDTO.setYearMonth(obj[2].toString());
            }
            if (obj[3]!=null) {
              temp_adjustAccountTaSaveDTO.setShouldCorpusTail(new BigDecimal(obj[3].toString()));
            }
            if (obj[4]!=null) {
              temp_adjustAccountTaSaveDTO.setShouldInterestTail(new BigDecimal(obj[4].toString()));
            }
            if (obj[5]!=null) {
              temp_adjustAccountTaSaveDTO.setShouldPunishInterestTail(new BigDecimal(obj[5].toString()));
            }
            if (obj[6]!=null) {
              temp_adjustAccountTaSaveDTO.setTempPunishInterest(new BigDecimal(obj[6].toString()));
            }
            if (obj[7]!=null) {
              temp_adjustAccountTaSaveDTO.setRealCorpusTail(new BigDecimal(obj[7].toString()));
            }
            if (obj[8]!=null) {
              temp_adjustAccountTaSaveDTO.setRealInterestTail(new BigDecimal(obj[8].toString()));
            }
            if (obj[9]!=null) {
              temp_adjustAccountTaSaveDTO.setRealPunishInterestTail(new BigDecimal(obj[9].toString()));
            }
            if (obj[10]!=null) {
              temp_adjustAccountTaSaveDTO.setOccurMoneyTail(new BigDecimal(obj[10].toString()));
            }
            if (obj[11]!=null) {
              temp_adjustAccountTaSaveDTO.setLoanType(obj[11].toString());
            }
            
            temp_adjustAccountTaSaveDTOList.add(temp_adjustAccountTaSaveDTO);
          }
          
          return temp_adjustAccountTaSaveDTOList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * yuqf
   * 2007-10-16
   * 保证金提取维护有条件查询纪录
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param loanBank
   * @param bizStatus
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List bailpickupTbdefaltQueryByCondition(final String loanKouAcc, final String contractId, 
      final String borrowerName,final String cardNum, final String loanBank,final String bizStatus, 
      final String orderBy, final String order, final int start,final int pageSize,final int page,
      final SecurityInfo securityInfo){
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " select t1.loan_kou_acc,t1.contract_id," +
              " t2.borrower_name,t2.card_num," +
              " t3.occur_money,t3.other_interest ,t3.flow_head_id" +
              " from pl203 t1,pl110 t2,pl202 t3 " +
              " where t2.contract_id = t1.contract_id " +
              " and t1.flow_head_id = t3.flow_head_id and t3.biz_type='14' " +
              " and t3.occur_money<0 " +
              " and t3.loan_bank_id "+ securityInfo.getDkSecuritySQL();
          String criterion = "";
          Vector parameters = new Vector();
          
          if(loanKouAcc != null && !"".equals(loanKouAcc)){
            criterion += "  t1.loan_kou_acc = ?  and ";
            parameters.add(loanKouAcc);
          }
          if(contractId != null && !"".equals(contractId)){
            criterion += " t1.contract_id = ?  and ";
            parameters.add(contractId);
          }
          if(borrowerName != null && !"".equals(borrowerName)){
            criterion += " t2.borrower_name = ?  and ";
            parameters.add(borrowerName);
          }
          if(cardNum != null && !"".equals(cardNum)){
            criterion += " t2.card_num = ?  and ";
            parameters.add(cardNum);
          }
          if(loanBank != null && !"".equals(loanBank)){
            criterion += " t3.loan_bank_id = ?  and ";
            parameters.add(loanBank);
          }
          if(bizStatus != null && !"".equals(bizStatus)){
            criterion += " t3.biz_st = ?  and ";
            parameters.add(bizStatus);
          }else{
            criterion += " t3.biz_st in (4,5,6) and ";
          }
          if (criterion.length() != 0){
            criterion = " and " 
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }
          String ob = orderBy;
          if (ob == null)
            ob = " t3.flow_head_id ";
          String od = order;
          if (od == null)
            od = "DESC";     
          
          sql = sql + criterion + " order by " + ob + " " + od ;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0){
            query.setMaxResults(pageSize);       
          Iterator itt=query.list().iterator();
          //List queryList=query.list();
          if(!itt.hasNext()){           
            query.setFirstResult(pageSize*(page-2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            itt=query.list().iterator();
           }
          }
          List temp_bailpickupTbDTOList = new ArrayList();
          Object[] obj = null;
          Iterator it = query.list().iterator();
          
          while (it.hasNext()) {
            obj=(Object[])it.next();
            BigDecimal tempOccurMoney = new BigDecimal(0.00);
            BigDecimal tempOtherInterest = new BigDecimal(0.00);
            BigDecimal pickupMoney = new BigDecimal(0.00);
            BailpickupTbDTO bailpickupTbDTO = new BailpickupTbDTO();
            if (obj[0]!=null) {
              bailpickupTbDTO.setLoanKouAcc(obj[0].toString());
            }
            if (obj[1]!=null) {
              bailpickupTbDTO.setContractId(obj[1].toString());
            }
            if (obj[2]!=null) {
              bailpickupTbDTO.setBorrowerName(obj[2].toString());
            }
            if (obj[3]!=null) {
              bailpickupTbDTO.setCardNum(obj[3].toString());
            }
            if (obj[4]!=null) {
              bailpickupTbDTO.setOccurMoney(obj[4].toString());
              tempOccurMoney = new BigDecimal(obj[4].toString());
            }
            if (obj[5]!=null) {
              bailpickupTbDTO.setOtherInterest(obj[5].toString());
              tempOtherInterest = new BigDecimal(obj[5].toString());
            }
            if(obj[6]!=null){
              bailpickupTbDTO.setId(obj[6].toString());
            }
            pickupMoney = tempOtherInterest.subtract(tempOccurMoney);
            bailpickupTbDTO.setPickupMoney(pickupMoney.toString());
            temp_bailpickupTbDTOList.add(bailpickupTbDTO);
          }
          
          return temp_bailpickupTbDTOList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  public int bailpickupTbdefaltQueryByConditionCount(final String loanKouAcc, final String contractId,
      final String borrowerName,final String cardNum, final String loanBank,final String bizStatus, 
      final String orderBy, final String order, final int start,final int pageSize,final int page,
      final SecurityInfo securityInfo){
    List list = new ArrayList();
    int count = 0;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " select t1.loan_kou_acc,t1.contract_id," +
              " t2.borrower_name,t2.card_num," +
              " t3.occur_money,t3.other_interest ,t3.flow_head_id" +
              " from pl203 t1,pl110 t2,pl202 t3 " +
              " where t2.contract_id = t1.contract_id and " +
              " t1.flow_head_id = t3.flow_head_id and " +
              " t3.biz_type='14' and t3.occur_money<0 " +
              " and t3.loan_bank_id "+ securityInfo.getDkSecuritySQL();
          String criterion = "";
          Vector parameters = new Vector();
          
          if(loanKouAcc != null && !"".equals(loanKouAcc)){
            criterion += "  t1.loan_kou_acc = ?  and ";
            parameters.add(loanKouAcc);
          }
          if(contractId != null && !"".equals(contractId)){
            criterion += " t1.contract_id = ?  and ";
            parameters.add(contractId);
          }
          if(borrowerName != null && !"".equals(borrowerName)){
            criterion += " t2.borrower_name = ?  and ";
            parameters.add(borrowerName);
          }
          if(cardNum != null && !"".equals(cardNum)){
            criterion += " t2.card_num = ?  and ";
            parameters.add(cardNum);
          }
          if(loanBank != null && !"".equals(loanBank)){
            criterion += " t3.loan_bank_id = ?  and ";
            parameters.add(loanBank);
          }
          if(bizStatus != null && !"".equals(bizStatus)){
            criterion += " t3.biz_st = ?  and ";
            parameters.add(bizStatus);
          }else{
            criterion += " t3.biz_st in (4,5,6) and ";
          }
          if (criterion.length() != 0){
            criterion = " and " 
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }
          String ob = orderBy;
          if (ob == null)
            ob = " t3.flow_head_id ";
          String od = order;
          if (od == null)
            od = "DESC";     
          
          sql = sql + criterion + " order by " + ob + " " + od ;
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
    count = list.size();
    return count;
  }
  /**
   * yuqf
   * 2007-10-16
   * 保证金提取维护默认查询状态为4的纪录
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List bailpickupTbdefaltQuery(final String orderBy, final String order, final int start,final int pageSize,final int page,final SecurityInfo securityInfo){
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " select t1.loan_kou_acc,t1.contract_id," +
              " t2.borrower_name,t2.card_num," +
              " t3.occur_money,t3.other_interest,t3.flow_head_id " +
              " from pl203 t1,pl110 t2,pl202 t3 " +
              " where t2.contract_id = t1.contract_id and t1.flow_head_id = t3.flow_head_id " +
              " and t3.biz_type='14' and t3.occur_money<0 and t3.biz_st='4' and t3.loan_bank_id "+securityInfo.getDkSecuritySQL();

 
          String ob = orderBy;
          if (ob == null)
            ob = " t3.flow_head_id ";
          String od = order;
          if (od == null)
            od = "DESC";     
          
          sql = sql + " order by " + ob + " " + od ;
          Query query = session.createSQLQuery(sql);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);       
          Iterator itt=query.list().iterator();
//          List queryList=query.list();
          if(!itt.hasNext()){           
            query.setFirstResult(pageSize*(page-2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
//            itt=query.iterate();
          }
          List temp_bailpickupTbDTOList = new ArrayList();
          Object[] obj = null;
          Iterator it = query.list().iterator();
          
          while (it.hasNext()) {
            obj=(Object[])it.next();
            BigDecimal tempOccurMoney = new BigDecimal(0.00);
            BigDecimal tempOtherInterest = new BigDecimal(0.00);
            BigDecimal pickupMoney = new BigDecimal(0.00);
            BailpickupTbDTO bailpickupTbDTO = new BailpickupTbDTO();
            if (obj[0]!=null) {
              bailpickupTbDTO.setLoanKouAcc(obj[0].toString());
            }
            if (obj[1]!=null) {
              bailpickupTbDTO.setContractId(obj[1].toString());
            }
            if (obj[2]!=null) {
              bailpickupTbDTO.setBorrowerName(obj[2].toString());
            }
            if (obj[3]!=null) {
              bailpickupTbDTO.setCardNum(obj[3].toString());
            }
            if (obj[4]!=null) {
              bailpickupTbDTO.setOccurMoney(obj[4].toString());
              tempOccurMoney = new BigDecimal(obj[4].toString());
            }
            if (obj[5]!=null) {
              bailpickupTbDTO.setOtherInterest(obj[5].toString());
              tempOtherInterest = new BigDecimal(obj[5].toString());
            }
            if(obj[6]!=null){
              bailpickupTbDTO.setId(obj[6].toString());
            }
            pickupMoney = tempOtherInterest.subtract(tempOccurMoney);
            bailpickupTbDTO.setPickupMoney(pickupMoney.toString());
            temp_bailpickupTbDTOList.add(bailpickupTbDTO);
          }
          
          return temp_bailpickupTbDTOList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * count 
   * yuqf
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public int bailpickupTbdefaltQueryCount(final String orderBy, final String order, final int start,final int pageSize,final int page,final SecurityInfo securityInfo){
    List list = new ArrayList();
    int count = 0;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " select t1.loan_kou_acc,t1.contract_id," +
              " t2.borrower_name,t2.card_num," +
              " t3.occur_money,t3.other_interest,t3.flow_head_id " +
              " from pl203 t1,pl110 t2,pl202 t3 " +
              " where t2.contract_id = t1.contract_id and t1.flow_head_id = t3.flow_head_id " +
              " and t3.biz_type='14' and t3.occur_money<0 and t3.biz_st='4' and t3.loan_bank_id "+securityInfo.getDkSecuritySQL();

          String ob = orderBy;
          if (ob == null)
            ob = " t3.flow_head_id ";
          String od = order;
          if (od == null)
            od = "DESC";     
          
          sql = sql + " order by " + ob + " " + od ;
          Query query = session.createSQLQuery(sql);
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
   * 银行代扣导入查询实还人数
   * 在插入流水头表时用到
   * jj
   * @param headId
   * @return
   */
  public int queryRealCountsByHeadId_LJ(final String headId){
    Integer count = null;
    try {
      count = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(distinct loanFlowTail.contractId) from LoanFlowTail loanFlowTail ";
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (criterion.length() != 0)
                criterion = "where (loanFlowTail.realCorpus+loanFlowTail.realInterest+loanFlowTail.realPunishInterest)>0 and "
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
    return count.intValue();
  }
  /**
   * 银行代扣导入查询列表信息
   * jj 2007-10-16
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */ 
  public List queryImportFlowTailByLoanBankId_LJ(final String loanBankId,final String headId,final String loanKouAcc,final String contractId,final String borrowerName,
      final String cardNum,final String bizType,final String bizSt,final String orderBy, final String order, final int start,
      final int pageSize,final int page,final SecurityInfo securityInfo){
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException,
            SQLException {
          String hql = "select borrower.borrowerName,borrower.cardNum,loanFlowTail.yearMonth,loanFlowTail.loanKouAcc,loanFlowTail.contractId,"
              + " nvl(loanFlowTail.realCorpus,0),nvl(loanFlowTail.realInterest,0),nvl(loanFlowTail.realPunishInterest,0),"
              + " nvl(loanFlowTail.realCorpus,0)+nvl(loanFlowTail.realInterest,0)+nvl(loanFlowTail.realPunishInterest,0),"
              + " loanFlowTail.occurMoney,"
              + " nvl(loanFlowTail.realCorpus,0)+nvl(loanFlowTail.realInterest,0)+nvl(loanFlowTail.realPunishInterest,0)+loanFlowTail.occurMoney,"
              + " loanFlowTail.loanType,loanFlowTail.flowTailId,"
              + " borrowerAcc.loanRepayDay,nvl(borrowerAcc.ovaerLoanRepay,0),loanFlowHead.flowHeadId,loanFlowHead.reserveaA "
              + " from LoanFlowTail loanFlowTail,Borrower borrower,BorrowerAcc borrowerAcc,LoanFlowHead loanFlowHead "
              + " where borrower.contractId = loanFlowTail.contractId and borrowerAcc.contractId = borrower.contractId and "
              + " loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and loanFlowHead.loanBankId "
              + securityInfo.getDkSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanFlowHead.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId));
          }
          if (headId != null && !headId.equals("")) {
            criterion += "loanFlowTail.flowHeadId = ?  and ";
            parameters.add(new BigDecimal(headId));
          }
          if (loanKouAcc != null && !loanKouAcc.equals("")) {
            criterion += "loanFlowTail.loanKouAcc = ?  and ";
            parameters.add(loanKouAcc);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += "loanFlowTail.contractId = ?  and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !borrowerName.equals("")) {
            criterion += "borrower.borrowerName like ?  and ";
            parameters.add("%" + borrowerName + "%");
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += "borrower.cardNum = ?  and ";
            parameters.add(cardNum);
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += "loanFlowHead.bizType = ?  and ";
            parameters.add(bizType);
          }
          if (bizSt != null && !bizSt.equals("")) {
            criterion += "loanFlowHead.bizSt = ?  and ";
            parameters.add(bizSt);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " loanFlowTail.loanKouAcc ";
          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator it = query.iterate();
          // List queryList=query.list();
          if (!it.hasNext()) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            it = query.iterate();
          }
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
            dto.setBorrowerName(obj[0].toString());
            dto.setCardNum(obj[1].toString());
            dto.setLoanKouYearmonth(obj[2].toString());
            dto.setLoanKouAcc(obj[3].toString());
            dto.setContractId(obj[4].toString());
            dto.setRealCorpus(new BigDecimal(obj[5].toString()));
            dto.setRealInterest(new BigDecimal(obj[6].toString()));
            dto.setRealPunishInterest(new BigDecimal(obj[7].toString()));
            dto.setSumMoney(new BigDecimal(obj[8].toString()));
            dto.setOccurMoney(new BigDecimal(obj[9].toString()));
            dto.setRealMoney(new BigDecimal(obj[10].toString()));
            dto.setLoanType(obj[11].toString());
            dto.setId(obj[12].toString());
            dto.setLoanRepayDay(obj[13].toString());
            dto.setOvaerLoanRepay(new BigDecimal(obj[14].toString()));
            dto.setHeadId(obj[15].toString());
            if (obj[16] != null) {
              dto.setBizDate(obj[16].toString());
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
   * 银行代扣导入查询列表信息记录数 jj 2007-10-16
   * 
   * @param loanBankId
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param bizType
   * @param bizSt
   * @return
   */
  public int queryImportFlowTailCountsByLoanBankId_LJ(final String loanBankId,final String headId,final String loanKouAcc,final String contractId,final String borrowerName,
      final String cardNum,final String bizType,final String bizSt,final SecurityInfo securityInfo){
    int count = 0;
    try {
      Integer counts = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(loanFlowTail.flowTailId)"+
                  " from LoanFlowTail loanFlowTail,Borrower borrower,LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanFlowHead.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId));
              }
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (borrowerName != null && !borrowerName.equals("")) {
                criterion += "borrower.borrowerName like ?  and ";
                parameters.add("%"+borrowerName+"%");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = ?  and ";
                parameters.add(cardNum);
              }
              if (bizType != null && !bizType.equals("")) {
                criterion += "loanFlowHead.bizType = ?  and ";
                parameters.add(bizType);
              }
              if (bizSt != null && !bizSt.equals("")) {
                criterion += "loanFlowHead.bizSt = ?  and ";
                parameters.add(bizSt);
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and " +
                " loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and  loanFlowHead.loanBankId "+securityInfo.getDkSecurityHqlSQL()+" and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
                            
              hql = hql + criterion ;
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
   * 银行代扣导入合计
   * jj 2007-10-16
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @return
   */
  public List queryImportFlowTailTotalByLoanBankId_LJ(final String loanBankId,final String headId,final String loanKouAcc,final String contractId,final String borrowerName,
      final String cardNum,final String bizType,final String bizSt,final SecurityInfo securityInfo){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowHead.realCount, nvl(loanFlowHead.realCorpus,0)+nvl(loanFlowHead.realOverdueCorpus,0)," +
                  "nvl(loanFlowHead.realInterest,0)+nvl(loanFlowHead.realOverdueInterest,0),nvl(loanFlowHead.realPunishInterest,0)," +               
                  "nvl(loanFlowHead.realCorpus,0)+nvl(loanFlowHead.realOverdueCorpus,0)+nvl(loanFlowHead.realInterest,0)+nvl(loanFlowHead.realOverdueInterest,0)+" +
                  "nvl(loanFlowHead.realPunishInterest,0),loanFlowHead.occurMoney," +
                  "nvl(loanFlowHead.realCorpus,0)+nvl(loanFlowHead.realOverdueCorpus,0)+nvl(loanFlowHead.realInterest,0)+nvl(loanFlowHead.realOverdueInterest,0)+nvl(loanFlowHead.realPunishInterest,0)+loanFlowHead.occurMoney" +
                  " from Borrower borrower,LoanFlowTail loanFlowTail,LoanFlowHead loanFlowHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (loanBankId != null && !loanBankId.equals("")) {
                criterion += "loanFlowHead.loanBankId = ?  and ";
                parameters.add(new BigDecimal(loanBankId));
              }
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (loanKouAcc != null && !loanKouAcc.equals("")) {
                criterion += "loanFlowTail.loanKouAcc = ?  and ";
                parameters.add(loanKouAcc);
              }
              if (contractId != null && !contractId.equals("")) {
                criterion += "loanFlowTail.contractId = ?  and ";
                parameters.add(contractId);
              }
              if (borrowerName != null && !borrowerName.equals("")) {
                criterion += "borrower.borrowerName like ?  and ";
                parameters.add("%"+borrowerName+"%");
              }
              if (cardNum != null && !cardNum.equals("")) {
                criterion += "borrower.cardNum = ?  and ";
                parameters.add(cardNum);
              }
              if (bizType != null && !bizType.equals("")) {
                criterion += "loanFlowHead.bizType = ?  and ";
                parameters.add(bizType);
              }
              if (bizSt != null && !bizSt.equals("")) {
                criterion += "loanFlowHead.bizSt = ?  and ";
                parameters.add(bizSt);
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and "+
                " loanFlowHead.flowHeadId = loanFlowTail.flowHeadId and  loanFlowHead.loanBankId "+securityInfo.getDkSecurityHqlSQL()+" and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion ;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator it=query.iterate();
              List temp_list = new ArrayList();
              Object obj [] = null;
              while(it.hasNext()){
                obj  = (Object[])it.next();
                BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
                dto.setRealCount(obj[0].toString());
                dto.setRealCorpus(new BigDecimal(obj[1].toString()));
                dto.setRealInterest(new BigDecimal(obj[2].toString()));
                dto.setRealPunishInterest(new BigDecimal(obj[3].toString()));
                dto.setSumMoney(new BigDecimal(obj[4].toString()));
                dto.setOccurMoney(new BigDecimal(obj[5].toString()));
                dto.setRealMoney(new BigDecimal(obj[6].toString()));
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
   * yuqf
   * 2007-10-17
   * 保证金提取维护删除
   * @param headId
   * @return
   */
  public List deleteFlowTailByHeadId_YU(final String headId){
    List list = null;
    try {
     
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTails.flowTailId from LoanFlowTail loanFlowTails where loanFlowTails.flowHeadId=?";
              Query query = session.createQuery(hql);                   
              query.setParameter(0, new BigDecimal(headId));
              Iterator it=query.iterate();
              Object obj=null;
              while(it.hasNext()){
                obj=(Object)it.next();
                String deletehsql=" delete from LoanFlowTail loanFlowTail2 where loanFlowTail2.flowTailId="+obj.toString()+""; 
                session.createQuery(deletehsql).executeUpdate();
              }
              return null;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * yuqf
   * 2007-10-17
   * 保证金提取维护删除按钮
   * 根据头表ID查询尾表合同编号;
   * @param headId
   * @return
   */
  public String queryContractByHeadId_YU(final String headId){
    String contract = "";
    try {
     
      contract = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select t.contract_id from pl203 t where t.flow_head_id=?";
              Query query = session.createSQLQuery(hql);                  
              query.setParameter(0, headId);
            
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return contract;
  }
  /**
   * yuqf
   * 2007-10-17
   * 根据头表ID,查询贷款账号
   * @param id
   * @return
   */
  public String queryContractId(final String id) {
    String headId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select distinct t.loan_kou_acc from pl203 t where t.flow_head_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);

            return query.uniqueResult().toString();
          }
        });

    return headId;
  }
  /**
   * yuqf
   * 2007-10-16
   * 保证金提取合计
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param loanBank
   * @param bizStatus
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   */
  public Object[] querySumYU(final String loanKouAcc, final String contractId, 
      final String borrowerName,final String cardNum, final String loanBank,final String bizStatus, 
      final String orderBy, final String order, final int start,final int pageSize,final int page,
      final SecurityInfo securityInfo){
    Object[] obj = new Object[3];
    try {
      obj = (Object[])getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " select sum(t3.occur_money),sum(t3.other_interest)," +
              " sum(t3.other_interest-t3.occur_money)  " +
              " from pl203 t1,pl110 t2,pl202 t3  where " +
              " t2.contract_id = t1.contract_id  and " +
              " t1.flow_head_id = t3.flow_head_id and " +
              " t3.biz_type='14' and t3.occur_money<0 and " +
              " t3.loan_bank_id "+ securityInfo.getDkSecuritySQL();

          String criterion = "";
          Vector parameters = new Vector();
          
          if(loanKouAcc != null && !"".equals(loanKouAcc)){
            criterion += "  t1.loan_kou_acc = ?  and ";
            parameters.add(loanKouAcc);
          }
          if(contractId != null && !"".equals(contractId)){
            criterion += " t1.contract_id = ?  and ";
            parameters.add(contractId);
          }
          if(borrowerName != null && !"".equals(borrowerName)){
            criterion += " t2.borrower_name = ?  and ";
            parameters.add(borrowerName);
          }
          if(cardNum != null && !"".equals(cardNum)){
            criterion += " t2.card_num = ?  and ";
            parameters.add(cardNum);
          }
          if(loanBank != null && !"".equals(loanBank)){
            criterion += " t3.loan_bank_id = ?  and ";
            parameters.add(loanBank);
          }
          if(bizStatus != null && !"".equals(bizStatus)){
            criterion += " t3.biz_st = ?  and ";
            parameters.add(bizStatus);
          }else{
            criterion += " t3.biz_st in (4,5,6) and ";
          }
          if (criterion.length() != 0){
            criterion = " and " 
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }
          String ob = orderBy;
          if (ob == null)
            ob = " t3.flow_head_id ";
          String od = order;
          if (od == null)
            od = "DESC";     
          
          sql = sql + criterion + " order by " + ob + " " + od ;
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
    return obj;
  }
  public Object[] querySumYU_(final String orderBy, final String order, final int start,final int pageSize,final int page,
      final SecurityInfo securityInfo){
    Object[] obj = new Object[3];
    try {
      obj = (Object[])getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " select sum(t3.occur_money),sum(t3.other_interest)," +
              " sum(t3.other_interest-t3.occur_money)  " +
              " from pl203 t1,pl110 t2,pl202 t3 " +
              " where t2.contract_id = t1.contract_id and t1.flow_head_id = t3.flow_head_id" +
              " and t3.biz_type='14' and t3.occur_money<0 and t3.biz_st='4' and t3.loan_bank_id "+securityInfo.getDkSecuritySQL();

   
          Query query = session.createSQLQuery(sql);
        
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * 根据合同编号查询贷款余额
   * 
   * @param contractId 合同编号
   * @return 贷款余额
   * @author 付云峰
   */
  public Object queryLoanMoney(final String contractId){
    Object obj = new Object();
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select p111.overplus_loan_money from pl111 p111 where p111.contract_id=?";
          Query query = session.createSQLQuery(sql);
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
   * 修改pl210中的状态
   * @param contractId
   * @author 付云峰
   */
  public void updateCongealInfo(final String contractId){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update CongealInfo congealInfo set congealInfo.status=0 "
              + "where congealInfo.contractId=?";
          Query query = session.createQuery(hql);
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
   * 修改PL111中的合同状态
   * @param contractId
   * @author 付云峰
   */
  public void updateBorrowerAcc(final String contractId){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update BorrowerAcc borrowerAcc set borrowerAcc.contractSt=11 "
              + "where borrowerAcc.contractId=?";
          Query query = session.createQuery(hql);
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
   * 在删除时将pl201中的状态还原
   * @param flowHeadId
   * @author 付云峰
   * @throws BusinessException 
   */
  public void updateCongealInfoInDelete(final String flowHeadId) throws BusinessException {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String temp_str = "";
          String sql = "select p111.contract_id " +
              "from pl203 p203,pl111 p111 " +
              "where p203.contract_id=p111.contract_id and p111.overplus_loan_money=0 and p203.flow_head_id=?";
          // 查询将要修改的纪录
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(flowHeadId));
          List list = query.list();
          if (list.size()==0) {
            return null;
          }
          for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            temp_str = temp_str +"'"+obj.toString()+"',";
          }
          temp_str = temp_str.substring(0, temp_str.lastIndexOf(","));
          // 更新PL111的状态
          String hql1 = "update BorrowerAcc borrowerAcc set borrowerAcc.contractSt=12 "
              + "where borrowerAcc.contractId in ("+temp_str+")";
          Query query1 = session.createQuery(hql1);
          query1.executeUpdate();
          // 更新PL201的状态
          String hql2 = "update CongealInfo a" + " set a.status = 1"
              + " where a.contractId in ("+temp_str+")";
          Query query2 = session.createQuery(hql2);
          query2.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("");
    }
  }
  /**
   * 打印PL203中的明细
   * @param HeadId
   * @return
   * @author 付云峰
   */
  public List queryPrintLoanFlowTailByHeadId_FYF(final String headId){
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select loanFlowTail.yearMonth,loanFlowTail.loanKouAcc,loanFlowTail.contractId,borrower.borrowerName," +
                  "borrower.cardNum,nvl(loanFlowTail.shouldCorpus,0),nvl(loanFlowTail.shouldInterest,0),nvl(loanFlowTail.shouldPunishInterest,0)," +
                  " loanFlowTail.realCorpus,loanFlowTail.realInterest,loanFlowTail.realPunishInterest," +
                  "(loanFlowTail.realCorpus+loanFlowTail.realInterest+loanFlowTail.realPunishInterest),loanFlowTail.occurMoney" +
                  " from LoanFlowTail loanFlowTail,Borrower borrower  ";
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null && !headId.equals("")) {
                criterion += "loanFlowTail.flowHeadId = ?  and ";
                parameters.add(new BigDecimal(headId));
              }
              if (criterion.length() != 0)
                criterion = "where borrower.contractId = loanFlowTail.contractId and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion+" order by loanFlowTail.flowTailId ASC  ";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              List temp_list = new ArrayList();
              Object obj [] = null;
              Iterator it = query.iterate();
              while(it.hasNext()){
                obj  = (Object[])it.next();
                ShouldBackListDTO dto = new ShouldBackListDTO();
                if (obj[0]!=null) {
                  dto.setLoanKouYearmonth(obj[0].toString());
                }
                if (obj[1]!=null) {
                  dto.setLoanKouAcc(obj[1].toString());
                }
                if (obj[2]!=null) {
                  dto.setContractId(obj[2].toString());
                }
                if (obj[3]!=null) {
                  dto.setBorrowerName(obj[3].toString());
                }
                if (obj[4]!=null) {
                  dto.setCardNum(obj[4].toString());
                }
                if (obj[5]!=null) {
                  dto.setShouldCorpus(new BigDecimal(obj[5].toString()));
                }
                if (obj[6]!=null) {
                  dto.setShouldInterest(new BigDecimal(obj[6].toString()));
                }
                if (obj[7]!=null) {
                  dto.setShouldPunishInterest(new BigDecimal(obj[7].toString()));
                }
                if (obj[8]!=null) {
                  dto.setRealCorpus(new BigDecimal(obj[8].toString()));
                }
                if (obj[9]!=null) {
                  dto.setRealInterest(new BigDecimal(obj[9].toString()));
                }
                if (obj[10]!=null) {
                  dto.setRealPunishInterest(new BigDecimal(obj[10].toString()));
                }
                if (obj[11]!=null) {
                  dto.setRealMoney(new BigDecimal(obj[11].toString()));
                }
                if (obj[12]!=null) {
                  dto.setOccurMoney(new BigDecimal(obj[12].toString()));
                }
                if (obj[11]!=null) {
                  dto.setMoney(new BigDecimal(obj[11].toString()));
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
  public LoancallbackTaAF queryYearMonth(final String flaowheadid){
    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    try {
      loancallbackTaAF = (LoancallbackTaAF)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "select max(t.yearMonth),min(t.yearMonth) from LoanFlowTail t where t.flowHeadId="+flaowheadid;
          Query query = session.createQuery(sql);
          LoancallbackTaAF loancallbackTaAF2 = new LoancallbackTaAF();
          Object obj [] = null;
          Iterator it = query.iterate();
          while(it.hasNext()){
            obj  = (Object[])it.next();
            loancallbackTaAF2.setMonthYear(obj[0].toString());//缴至日期
            loancallbackTaAF2.setBegainMonthYear(obj[1].toString());//起始日期
          }
          return loancallbackTaAF2;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loancallbackTaAF;
  }
  
  public void insertPL101_1_jj(String contractId,String type,String bizDate,BigDecimal aheadmoney,String deadline
      ,BigDecimal corpusInterest,BigDecimal overplusLoanMoney,String matter,String pl202_id,String olddeadline){
    try{
      Connection conn = getHibernateTemplate().getSessionFactory().openSession().connection();
      Statement st = conn.createStatement();
      String sql = "insert into PL101_1 (id,contract_id,type,bizdate,aheadmoney,deadline,corpus_interest,overplus_loan_money,mark_a,mark_b,mark_c,mark_d,plflowid,olddeadline ) " +
          " values(SEQ_PL101_1.Nextval,'"+contractId+"','"+type+"','"+bizDate+"',"+aheadmoney+",'"+deadline+"'" +
              ",'"+corpusInterest+"','"+overplusLoanMoney+"','"+matter+"',0,0,0,"+pl202_id+","+olddeadline+")";
      st.executeUpdate(sql);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public String selectPL101_1_jj(){
    Object id = "";
      id=(Object)getHibernateTemplate().execute(new HibernateCallback(){
       public Object doInHibernate(Session session) throws HibernateException, SQLException {
         String sql="select max(p.id) from PL101_1 p";
         Query query=session.createSQLQuery(sql);
         return query.uniqueResult();
       }});
    return id.toString();
  }
  public String selectPL101_1_olddeadline_wsh(final String flowid){
    Object id = "";
      id=(Object)getHibernateTemplate().execute(new HibernateCallback(){
       public Object doInHibernate(Session session) throws HibernateException, SQLException {
         String sql="select pl101_1.olddeadline from pl101_1 where pl101_1.plflowid="+flowid;
         Query query=session.createSQLQuery(sql);
         return query.uniqueResult();
       }});
    return id.toString();
  }
  public String selectPL101_1_type_wsh(final String flowid){
    Object id = "";
      id=(Object)getHibernateTemplate().execute(new HibernateCallback(){
       public Object doInHibernate(Session session) throws HibernateException, SQLException {
         String sql="select pl101_1.type from pl101_1 where pl101_1.plflowid="+flowid;
         Query query=session.createSQLQuery(sql);
         return query.uniqueResult();
       }});
    return id.toString();
  }
  public String selectPL101_1_interest_wsh(final String flowid){
    Object id = "";
      id=(Object)getHibernateTemplate().execute(new HibernateCallback(){
       public Object doInHibernate(Session session) throws HibernateException, SQLException {
         String sql="select pl101_1.CORPUS_INTEREST from pl101_1 where pl101_1.plflowid="+flowid;
         Query query=session.createSQLQuery(sql);
         return query.uniqueResult();
       }});
    return id.toString();
  }
  public void updatePL112_1_jjByContractId(String contractId){
    try{
      Connection conn = getHibernateTemplate().getSessionFactory().openSession().connection();
      Statement st = conn.createStatement();
      String sql = "update PL112_1 p set p.status = '1' where p.contract_id = '"+contractId+"' and p.status = '0' ";
      st.execute(sql);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  public void updatePL112_1_jjByAheadCheckId(String aheadCheckId){
    try{
      Connection conn = getHibernateTemplate().getSessionFactory().openSession().connection();
      Statement st = conn.createStatement();
      String sql = "update PL112_1 p set p.status = '0' where p.id = '"+aheadCheckId+"' and p.status = '1' ";
      st.execute(sql);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  public void deletePL101_1_jj(String chgId){
    try{
      Connection conn = getHibernateTemplate().getSessionFactory().openSession().connection();
      Statement st = conn.createStatement();
      String sql = "delete from PL101_1 p where p.id = '"+chgId+"' ";
      st.execute(sql);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  public String[] queryPl101_1Info(final String plflowid) {
    String happenMoney[] = new String[3];
    try {
      happenMoney = (String[]) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select contract_id," +
                  "type," +
                  "bizdate," +
                  "aheadmoney," +
                  "deadline," +
                  "corpus_interest," +
                  "overplus_loan_money," +
                  "olddeadline " +
                  "from pl101_1 where pl101_1.plflowid = ?";
              Query query = session.createSQLQuery(hql);

              query.setParameter(0, plflowid);
              String str[] = new String[7];

              if (query.list() != null) {
                Object obj[] = new Object[3];
                obj = (Object[]) query.list().get(0);
                if (obj[1] != null && !"".equals(obj[0])) {
                  str[0] = obj[1].toString();
                } else {
                  str[0] = "";
                }
                if (obj[2] != null && !"".equals(obj[2])) {
                  str[1] = obj[2].toString();
                } else {
                  str[1] = "";
                }
                if (obj[3] != null && !"".equals(obj[3])) {
                  str[2] = obj[3].toString();
                } else {
                  str[2] = "";
                }
                if (obj[4] != null && !"".equals(obj[4])) {
                  str[3] = obj[4].toString();
                } else {
                  str[3] = "";
                }
                if (obj[5] != null && !"".equals(obj[5])) {
                  str[4] = obj[5].toString();
                } else {
                  str[4] = "";
                }
                if (obj[6] != null && !"".equals(obj[6])) {
                  str[5] = obj[6].toString();
                } else {
                  str[5] = "";
                }
                if (obj[7] != null && !"".equals(obj[7])) {
                  str[6] = obj[7].toString();
                } else {
                  str[6] = "";
                }
                return str;
              } else {
                String s[] = new String[2];
                s[0] = "";
                s[1] = "";
                s[2] = "";
                s[3] = "";
                s[4] = "";
                s[5] = "";
                s[6] = "";
                return s;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return happenMoney;
  }
  public String selectPL202_BankId_wsh(final String flowid){
    Object id = "";
      id=(Object)getHibernateTemplate().execute(new HibernateCallback(){
       public Object doInHibernate(Session session) throws HibernateException, SQLException {
         String sql="select bb105.coll_bank_name from pl202,pl002,bb105 where pl202.loan_bank_id=pl002.loan_bank_id and  bb105.coll_bank_id=pl002.loan_bank_id and pl202.flow_head_id="+flowid;
         Query query=session.createSQLQuery(sql);
         return query.uniqueResult();
       }});
    return id.toString();
  }
}
