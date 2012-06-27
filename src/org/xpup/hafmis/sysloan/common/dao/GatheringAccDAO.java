package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
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
import org.xpup.hafmis.sysloan.common.domain.entity.GatheringAcc;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.dto.GatheringAccInfoDTO;

public class GatheringAccDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public GatheringAcc queryById(Serializable id) {
    Validate.notNull(id);
    return (GatheringAcc) getHibernateTemplate().get(GatheringAcc.class, id);
  }

  /**
   * 插入记录
   * 
   * @param GatheringAcc
   * @return
   */
  public Serializable insert(GatheringAcc gatheringAcc) {
    Serializable id = null;
    try {
      Validate.notNull(gatheringAcc);
      id = getHibernateTemplate().save(gatheringAcc);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * author wsh 根据输入的条件查询银行扣款账号修改流水信息(List)  
   * @param String contractId,
   * @param String borrwerName
   * @param String cardNum
   * @return
   */
  public List queryGathingAccList_wsh(final String contractId,
      final String borrwerName, final String cardNum, final String orderBy,
      final String order, final int start, final int pageSize,final List bankList) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List gatheringAccList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select c.contract_id contractid,"
                + "a.borrower_name borrowername," + "a.card_num cardNum,"
                + "b.loan_bank_id loanbankid," + "c.old_bank_acc oldbankacc,"
                + "c.new_bank_acc newbankacc," + "c.oprator oprator ,"
                + "c.receive_bank_modify_id receiveankmodifyid,"
                + "c.modify_date "
                + "from pl110 a, pl111 b, pl130 c ";
            String criterion = "";
            List list = new ArrayList();
            List temp_list = new ArrayList();
            Vector parameters = new Vector();
            if (contractId != null && !contractId.equals("")) {
              criterion += " c.contract_id = ?  and ";
              parameters.add(contractId);
            }
            if (bankList != null && bankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < bankList.size(); i++) {
                criterion += " b.loan_bank_id = ? or ";
                parameters.add(bankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (borrwerName != null && !borrwerName.equals("")) {
              criterion += " a.borrower_name = ?  and ";
              parameters.add( borrwerName);
            }
            if (cardNum != null && !cardNum.equals("")) {
              criterion += " a.card_num = ? and ";
              parameters.add(cardNum);
            }
            if (criterion.length() != 0) {
              criterion = "where a.contract_id = b.contract_id and a.contract_id = c.contract_id and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            } else {
              criterion = "where a.contract_id = b.contract_id and a.contract_id = c.contract_id ";
            }
            String ob = orderBy;
            if (ob == null)
              ob = "receiveankmodifyid ";
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
            Object obj[] = null;
            temp_list = query.list();
            Iterator iter = temp_list.iterator();
            while (iter.hasNext()) {
              GatheringAccInfoDTO gatheringAccInfoDTO = new GatheringAccInfoDTO();
              obj = (Object[]) iter.next();
              if (obj[0] != null) {
                gatheringAccInfoDTO.setContractId(obj[0].toString());
              }
              if (obj[1] != null) {
                gatheringAccInfoDTO.setBorrowerName(obj[1].toString());
              }
              if (obj[2] != null) {
                gatheringAccInfoDTO.setCardNum(obj[2].toString());
              }
              if (obj[3] != null) {
                gatheringAccInfoDTO.setLoanBankId(obj[3].toString());
              }

              if (obj[4] != null) {
                gatheringAccInfoDTO.setOldBankAcc(obj[4].toString());
              }
              if (obj[5] != null) {
                gatheringAccInfoDTO.setNewBankAcc(obj[5].toString());
              }
              if (obj[6] != null) {
                gatheringAccInfoDTO.setOprator(obj[6].toString());
              }
              if (obj[7] != null) {
                gatheringAccInfoDTO.setReceiveBankModifyId(obj[7].toString());
              }
              if (obj[8] != null) {
                gatheringAccInfoDTO.setModifyDate(obj[8].toString());
              }

              list.add(gatheringAccInfoDTO);
            }
            return list;
          }
        });
    return gatheringAccList;
  }

  /**
   * author wsh 根据输入的条件查询银行扣款账号修改流水信息(List) 
   * @param String contractId,
   * @param String borrwerName
   * @param String cardNum
   * @return
   */
  public GatheringAccInfoDTO queryGathingAccInfo_wsh(
      final String receiveBankModifyId) {
    GatheringAccInfoDTO gatheringAccInfoDTO = (GatheringAccInfoDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select c.contract_id contractid,"
                + "a.borrower_name borrowername," 
                + "a.card_num cardNum,"
                + "b.loan_bank_id loanbankid," 
                + "c.old_bank_acc oldbankacc,"
                + "c.new_bank_acc newbankacc," 
                + "c.oprator oprator,"
                + "c.modify_date "
                + "from pl110 a, pl111 b, pl130 c ";
            String criterion = "";
            List list = new ArrayList();
            List temp_list = new ArrayList();
            Vector parameters = new Vector();
            if (receiveBankModifyId != null && !"".equals(receiveBankModifyId)) {
              criterion += "  c.receive_bank_modify_id = ?  and ";
              parameters.add(new Integer(receiveBankModifyId));
            }
            if (criterion.length() != 0) {
              criterion = "where a.contract_id = b.contract_id and a.contract_id = c.contract_id and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            } else {
              criterion = "where a.contract_id = b.contract_id and a.contract_id = c.contract_id ";
            }
            hql = hql + criterion;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj[] = null;
            temp_list = query.list();
            Iterator iter = temp_list.iterator();
            GatheringAccInfoDTO temp_gatheringAccInfoDTO = new GatheringAccInfoDTO();
            obj = (Object[]) iter.next();
            if (obj[0] != null) {
              temp_gatheringAccInfoDTO.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              temp_gatheringAccInfoDTO.setBorrowerName(obj[1].toString());
            }
            if (obj[2] != null) {
              temp_gatheringAccInfoDTO.setCardNum(obj[2].toString());
            }
            if (obj[3] != null) {
              temp_gatheringAccInfoDTO.setLoanBankId(obj[3].toString());
            }

            if (obj[4] != null) {
              temp_gatheringAccInfoDTO.setOldBankAcc(obj[4].toString());
            }
            if (obj[5] != null) {
              temp_gatheringAccInfoDTO.setNewBankAcc(obj[5].toString());
            }
            if (obj[6] != null) {
              temp_gatheringAccInfoDTO.setOprator(obj[6].toString());
            }
            if (obj[7] != null) {
              temp_gatheringAccInfoDTO.setModifyDate(obj[7].toString());
            }
            return temp_gatheringAccInfoDTO;
          }
        });
    return gatheringAccInfoDTO;
  }

  /**
   * author wsh 根据输入的条件查询银行扣款账号修改流水信息记录数量
   * @param String contractId,
   * @param String borrwerName
   * @param String cardNum
   * @return
   */
  public Integer queryGathingAccCount_wsh(final String contractId,
      final String borrwerName, final String cardNum,final List bankList) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(c.contract_id) from pl110 a, pl111 b, pl130 c ";
            Vector parameters = new Vector();
            String criterion = "";
            if (contractId != null && !contractId.equals("")) {
              criterion += " c.contract_id = ?  and ";
              parameters.add(contractId);
            }
            if (borrwerName != null && !borrwerName.equals("")) {
              criterion += " a.borrower_name like ?  and ";
              parameters.add("%" + borrwerName + "%");
            }
            if (bankList != null && bankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < bankList.size(); i++) {
                criterion += " b.loan_bank_id = ? or ";
                parameters.add(bankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (cardNum != null && !cardNum.equals("")) {
              criterion += " a.card_num = ? and ";
              parameters.add(cardNum);
            }
            if (criterion.length() != 0) {
              criterion = " where a.contract_id = b.contract_id and a.contract_id = c.contract_id and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            } else {
              criterion = " where a.contract_id = b.contract_id and a.contract_id = c.contract_id ";
            }
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
  //包钢
  public String findExitGJJBack(final String contractId) {
    String status = "";
    status = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select t.loan_kou_acc from pl400 t where t.contract_id=? and t.status='0'";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0,contractId);
        List list=query.list();
        String st="";
        if(list!=null&&list.size()!=0){
          st=list.get(0).toString();
        }
        return st;
      }
    });

    return status;
  }
  
  
  public void updatePL400KOUACC_wsh(final String loanKouAcc,final  String contractId){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update FundloanInfo fundloanInfo set fundloanInfo.loanKouAcc=? "
              + "where fundloanInfo.contractId=? "
              + "and fundloanInfo.status='0' ";
          Query query = session.createQuery(hql);
          query.setString(0, loanKouAcc);
          query.setString(1, contractId);
      
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  //包钢
  public void updatePL201bankId_wsh(final String loanBankId,final  String contractId){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update RestoreLoan restoreLoan set restoreLoan.loanBankId=? "
              + "where restoreLoan.contractId=? ";
          Query query = session.createQuery(hql);
          query.setString(0, loanBankId);
          query.setString(1, contractId);
      
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void updatePL004bankId_wsh(final String loanBankId,final  String contractId){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update LoanContractPara loanContractPara set loanContractPara.loanBankId=? "
              + "where loanContractPara.contractId=? ";
          Query query = session.createQuery(hql);
          query.setString(0, loanBankId);
          query.setString(1, contractId);
      
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  
  /**
   * hanl
   * 删除pl130
   * @param id
   */
    public void deleteGatheringAcc(final String id) {
      try {
        getHibernateTemplate().execute(new HibernateCallback() {

          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {

            String sql = "delete from GatheringAcc gatheringAcc where gatheringAcc.contractId=?";
            Query query = session.createQuery(sql);
            query.setParameter(0, id);
            return new Integer(query.executeUpdate());
          }
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
