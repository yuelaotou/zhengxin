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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.CongealInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.querycongeallog.dto.QueryCongeallogDTO;

public class CongealInfoDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public CongealInfo queryById(Serializable id) {
    Validate.notNull(id);
    return (CongealInfo) getHibernateTemplate().get(CongealInfo.class, id);
  }

  /**
   * 插入记录
   * 
   * @param CongealInfo
   * @return
   */
  public Serializable insert(CongealInfo congealInfo) {
    Serializable id = null;
    try {
      Validate.notNull(congealInfo);
      id = getHibernateTemplate().save(congealInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 贷款户注销
   * 
   * @author wsh 2007-9-28 根据PL111中的合同编号修改更新PL210.STATUS=1 查询条件：contractId
   */
  public void updateCongealInfoStatus(final String status,
      final String contractId) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update CongealInfo congealInfo set congealInfo.status = ? where congealInfo.contractId = ? ";
          Query query = session.createQuery(hql);
          query.setString(0, status);
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
   * hanl 更新冻结表
   * 
   * @param status
   * @param id
   * @param contactid
   */

  public void updateCongealInfo(final String status, final String id,
      final String contactid) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update CongealInfo congealInfo set congealInfo.status = ? where congealInfo.personId = ? and congealInfo.contractId=? ";
          Query query = session.createQuery(hql);
          query.setString(0, status);
          query.setString(1, id);
          query.setString(2, contactid);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 返回冻结表信息
   * @param bankId
   * @param officeName
   * @param type
   * @param empName
   * @param status
   * @param cardNum
   * @param contractId
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List queryCongeallogList(final String bankId, final String officeName,
      final String type, final String empName, final String status,
      final String cardNum, final String contractId, final String orderBy,
      final String orderother, final int start, final int pageSize,
      final int page, final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new

      HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p210.contract_id, p110.office,p111.loan_bank_id,p210.org_id,"
              + "p110.org_name,p210.emp_id,p210.emp_name,p210.card_num,p210.status,p210.type,"
              + "p110.acc_blnce,p111.loan_money,p111.loan_time_limit from pl210 p210, pl110 p110, pl111 p111 "
              + "where p210.contract_id = p110.contract_id and p110.contract_id = p111.contract_id "
              + "and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p210.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (bankId != null && !"".equals(bankId)) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(new Integer(bankId));
          }
          if (officeName != null && !"".equals(officeName)) {
            criterion += " p110.office = ? and ";
            parameters.add(officeName);
          }
          if (type != null && !"".equals(type)) {
            criterion += " p210.type = ? and ";
            parameters.add(type);
          }
          if (empName != null && !"".equals(empName)) {
            criterion += " p210.emp_name = ? and ";
            parameters.add(empName);
          }
          if (status != null && !"".equals(status)) {
            criterion += " p210.status = ? and ";
            parameters.add(status);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p210.card_num = ? and ";
            parameters.add(cardNum);
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "p210.contract_id";

          String od = orderother;
          if (od == null)
            od = " DESC ";

          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            QueryCongeallogDTO queryCongeallogDTO = new QueryCongeallogDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              queryCongeallogDTO.setContactId(obj[0].toString());// 合同编号
            }
            if (obj[1] != null) {
              queryCongeallogDTO.setOfficeName(obj[1].toString());// 办事处
            }
            if (obj[2] != null) {
              queryCongeallogDTO.setBankId(obj[2].toString());// 放款银行
            }
            if (obj[3] != null) {
              queryCongeallogDTO.setOrgId(obj[3].toString());// 单位编号
            }
            if (obj[4] != null) {
              queryCongeallogDTO.setOrgName(obj[4].toString());// 单位名称
            }
            if (obj[5] != null) {
              queryCongeallogDTO.setEmpId(obj[5].toString());// 职工编号
            }
            if (obj[6] != null) {
              queryCongeallogDTO.setEmpName(obj[6].toString());// 借款人
            }
            if (obj[7] != null) {
              queryCongeallogDTO.setCardNum(obj[7].toString());// 证件号码
            }
            
            if (obj[8] != null) { //冻结状态
              if(obj[8].toString().equals("0")){
                queryCongeallogDTO.setStatus("冻结");
              }else{
                queryCongeallogDTO.setStatus("解冻");
              }
            }
            
            if (obj[9] != null) { // 冻结类型
              if(obj[9].toString().equals("1")){
                queryCongeallogDTO.setType("借款人");
              }else if(obj[9].toString().equals("2")){
                queryCongeallogDTO.setType("辅助借款人");
              }else{
                queryCongeallogDTO.setType("保证人");
              }
            }
            
            if (obj[10] != null) {
              queryCongeallogDTO.setMoney(new BigDecimal(obj[10].toString()));// 账户余额
            }
            if (obj[11] != null) {
              queryCongeallogDTO.setLoanMoney(new BigDecimal(obj[11].toString()));// 贷款金额
            }
            if (obj[12] != null) {
              queryCongeallogDTO.setLoanTime(new BigDecimal(obj[12].toString()));// 贷款期限
            }
            templist.add(queryCongeallogDTO);
          }
          return templist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  
  /**
   * 返回冻结表信息
   * @param bankId
   * @param officeName
   * @param type
   * @param empName
   * @param status
   * @param cardNum
   * @param contractId
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public int queryCongeallogListCount(final String bankId, final String officeName,
      final String type, final String empName, final String status,
      final String cardNum, final String contractId, final String orderBy,
      final String orderother, final int start, final int pageSize,
      final int page, final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new

      HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p210.contract_id, p110.office,p111.loan_bank_id,p210.org_id,"
              + "p110.org_name,p210.emp_id,p210.emp_name,p210.card_num,p210.status,p210.type,"
              + "p110.acc_blnce,p111.loan_money,p111.loan_time_limit from pl210 p210, pl110 p110, pl111 p111 "
              + "where p210.contract_id = p110.contract_id and p110.contract_id = p111.contract_id "
              + "and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p210.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (bankId != null && !"".equals(bankId)) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(new Integer(bankId));
          }
          if (officeName != null && !"".equals(officeName)) {
            criterion += " p110.office = ? and ";
            parameters.add(officeName);
          }
          if (type != null && !"".equals(type)) {
            criterion += " p210.type = ? and ";
            parameters.add(type);
          }
          if (empName != null && !"".equals(empName)) {
            criterion += " p210.emp_name = ? and ";
            parameters.add(empName);
          }
          if (status != null && !"".equals(status)) {
            criterion += " p210.status = ? and ";
            parameters.add(status);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p210.card_num = ? and ";
            parameters.add(cardNum);
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "p210.contract_id";

          String od = orderother;
          if (od == null)
            od = " DESC";

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
    return list.size();
  }
  
  /*
   * 返回全部信息
   */
  public List queryCongeallogAllList(final String bankId, final String officeName,
      final String type, final String empName, final String status,
      final String cardNum, final String contractId, final String orderBy,
      final String orderother, final int start, final int pageSize,
      final int page, final SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new

      HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p210.contract_id, p110.office,p111.loan_bank_id,p210.org_id,"
              + "p110.org_name,p210.emp_id,p210.emp_name,p210.card_num,p210.status,p210.type,"
              + "p110.acc_blnce,p111.loan_money,p111.loan_time_limit from pl210 p210, pl110 p110, pl111 p111 "
              + "where p210.contract_id = p110.contract_id and p110.contract_id = p111.contract_id "
              + "and p111.loan_bank_id " + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p210.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (bankId != null && !"".equals(bankId)) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(new Integer(bankId));
          }
          if (officeName != null && !"".equals(officeName)) {
            criterion += " p110.office = ? and ";
            parameters.add(officeName);
          }
          if (type != null && !"".equals(type)) {
            criterion += " p210.type = ? and ";
            parameters.add(type);
          }
          if (empName != null && !"".equals(empName)) {
            criterion += " p210.emp_name = ? and ";
            parameters.add(empName);
          }
          if (status != null && !"".equals(status)) {
            criterion += " p210.status = ? and ";
            parameters.add(status);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p210.card_num = ? and ";
            parameters.add(cardNum);
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "p210.contract_id";

          String od = orderother;
          if (od == null)
            od = " DESC";

          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List templist = new ArrayList();
          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            QueryCongeallogDTO queryCongeallogDTO = new QueryCongeallogDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              queryCongeallogDTO.setContactId(obj[0].toString());// 合同编号
            }
            if (obj[1] != null) {
              queryCongeallogDTO.setOfficeName(obj[1].toString());// 办事处
            }
            if (obj[2] != null) {
              queryCongeallogDTO.setBankId(obj[2].toString());// 放款银行
            }
            if (obj[3] != null) {
              queryCongeallogDTO.setOrgId(obj[3].toString());// 单位编号
            }
            if (obj[4] != null) {
              queryCongeallogDTO.setOrgName(obj[4].toString());// 单位名称
            }
            if (obj[5] != null) {
              queryCongeallogDTO.setEmpId(obj[5].toString());// 职工编号
            }
            if (obj[6] != null) {
              queryCongeallogDTO.setEmpName(obj[6].toString());// 借款人
            }
            if (obj[7] != null) {
              queryCongeallogDTO.setCardNum(obj[7].toString());// 证件号码
            }
            
            if (obj[8] != null) { //冻结状态
              if(obj[8].toString().equals("0")){
                queryCongeallogDTO.setStatus("冻结");
              }else{
                queryCongeallogDTO.setStatus("解冻");
              }
            }
            
            if (obj[9] != null) { // 冻结类型
              if(obj[9].toString().equals("1")){
                queryCongeallogDTO.setType("借款人");
              }else if(obj[9].toString().equals("2")){
                queryCongeallogDTO.setType("辅助借款人");
              }else{
                queryCongeallogDTO.setType("保证人");
              }
            }
            
            if (obj[10] != null) {
              queryCongeallogDTO.setMoney(new BigDecimal(obj[10].toString()));// 账户余额
            }
            if (obj[11] != null) {
              queryCongeallogDTO.setLoanMoney(new BigDecimal(obj[11].toString()));// 贷款金额
            }
            if (obj[12] != null) {
              queryCongeallogDTO.setLoanTime(new BigDecimal(obj[12].toString()));// 贷款期限
            }
            templist.add(queryCongeallogDTO);
          }
          
          return templist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl
   * 删除
   * @param id
   */
    public void deleteCongealInfo(final String id) {
      try {
        getHibernateTemplate().execute(new HibernateCallback() {

          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {

            String sql = "delete from CongealInfo congealInfo where congealInfo.contractId=?";
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
