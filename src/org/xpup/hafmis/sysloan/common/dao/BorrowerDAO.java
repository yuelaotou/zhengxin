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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.CardMunChange;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.sysloan.common.biz.contractpop.dto.ContractpopDTO;
import org.xpup.hafmis.sysloan.common.biz.loankouaccpop.dto.LoanKouAccpopDTO;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTeDTO;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.EmpinfoDto;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.dto.ReceiveaccModifyDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.dto.AssureborrowerqueryDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.dto.LoanapplyInfoDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.dto.QueryPayOffRecordsTaListDTO;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 借款人信息表PL110
 * 
 * @author 李娟 2007-9-13
 */
public class BorrowerDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public Borrower queryById(Serializable id) {
    Validate.notNull(id);
    return (Borrower) getHibernateTemplate().get(Borrower.class, id);
  }

  /**
   * 插入记录
   * 
   * @param borrower
   * @return
   */
  public Serializable insert(Borrower borrower) {
    Serializable id = null;
    try {
      Validate.notNull(borrower);
      id = getHibernateTemplate().save(borrower);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 根据合同编号查询借款人姓名－－－yuqf
   * 
   * @param id
   * @return
   */
  public String queryBorrowerName(final String id) {
    String name = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.borrower_name from pl110 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);

            return query.uniqueResult();
          }
        });

    return name;
  }

  /**
   * 合同弹出框列表---yuqf 操作员权限
   * 
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param empId
   * @param empSt
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryListByConditionYU(final String contractId,
      final String borrowerName, final String cardNum, final String empId,
      final List empSt, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = " select p110.contract_id,p110.borrower_name,p110.card_num,p110.org_id,p110.org_name,p110.emp_id,"
              + "p111.contract_st from pl110 p110,pl111 p111 where p110.contract_id=p111.contract_id and p110.operator "
              + securityInfo.getDkUserSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num like ? and ";
            parameters.add("%" + cardNum + "%");
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " p110.emp_id like ? and ";
            parameters.add("%" + empId + "%");
          }
          if (empSt != null && empSt.size() > 0) {
            for (int i = 0; i < empSt.size(); i++) {
              if (empSt.get(i).toString().equals("1002")) {
                criterion += " p111.contract_st = 15  and p111.is_contract_write=0 and ";
                criterion += " not exists (select p.contract_id from pl120 p where p.contract_id=p111.contract_id) and ";
              }
            }
          }
          if (empSt != null && empSt.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < empSt.size(); i++) {
              criterion += " p111.contract_st = ? or ";
              parameters.add(empSt.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " pl10.contract_id ";

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

          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            ContractpopDTO contractpopDTO = new ContractpopDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              contractpopDTO.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              contractpopDTO.setBorrowerName(obj[1].toString());
            }
            if (obj[2] != null) {
              contractpopDTO.setCardNum(obj[2].toString());
            }
            if (obj[3] != null) {
              contractpopDTO.setOrgId(obj[3].toString());
            }
            if (obj[4] != null) {
              contractpopDTO.setOrgName(obj[4].toString());
            }
            if (obj[5] != null) {
              contractpopDTO.setEmpId(obj[5].toString());
            }
            String contractSt = "";
            if (obj[6] != null) {
              contractSt = obj[6].toString();
            }
            // 枚举转换合同状态
            try {
              contractpopDTO.setContractSt(BusiTools.getBusiValue(Integer
                  .parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS));
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            templist.add(contractpopDTO);
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
   * 合同弹出框列表---yuqf 银行权限
   * 
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param empId
   * @param empSt
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryListByConditionYU_(final String contractId,
      final String borrowerName, final String cardNum, final String empId,
      final List empSt, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = " select p110.contract_id,p110.borrower_name,p110.card_num,p110.org_id,p110.org_name,p110.emp_id,"
              + " p111.contract_st from pl110 p110,pl111 p111 where p110.contract_id=p111.contract_id and p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num like ? and ";
            parameters.add("%" + cardNum + "%");
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " p110.emp_id like ? and ";
            parameters.add("%" + empId + "%");
          }
          if (empSt != null && empSt.size() > 0) {
            for (int i = 0; i < empSt.size(); i++) {
              if (empSt.get(i).toString().equals("1001")) {
                criterion += " not exists (select p.contract_id from pl120 p where p.contract_id=p111.contract_id) and ";
              }
            }
          }
          if (empSt != null && empSt.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < empSt.size(); i++) {
              criterion += " p111.contract_st = ? or ";
              parameters.add(empSt.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " pl10.contract_id ";

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

          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            ContractpopDTO contractpopDTO = new ContractpopDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              contractpopDTO.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              contractpopDTO.setBorrowerName(obj[1].toString());
            }
            if (obj[2] != null) {
              contractpopDTO.setCardNum(obj[2].toString());
            }
            if (obj[3] != null) {
              contractpopDTO.setOrgId(obj[3].toString());
            }
            if (obj[4] != null) {
              contractpopDTO.setOrgName(obj[4].toString());
            }
            if (obj[5] != null) {
              contractpopDTO.setEmpId(obj[5].toString());
            }
            String contractSt = "";
            if (obj[6] != null) {
              contractSt = obj[6].toString();
            }
            // 枚举转换合同状态
            try {
              contractpopDTO.setContractSt(BusiTools.getBusiValue(Integer
                  .parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS));
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            templist.add(contractpopDTO);
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
   * 合同弹出框count ----yuqf 操作员权限
   * 
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param empId
   * @param empSt
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public int queryListCountByConditionYU(final String contractId,
      final String borrowerName, final String cardNum, final String empId,
      final List empSt, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = " select p110.contract_id,p110.borrower_name,p110.card_num,p110.org_id,p110.org_name,p110.emp_id,"
              + "p111.contract_st from pl110 p110,pl111 p111 where p110.contract_id=p111.contract_id and p110.operator "
              + securityInfo.getDkUserSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num like ? and ";
            parameters.add("%" + cardNum + "%");
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " p110.emp_id like ? and ";
            parameters.add("%" + new BigDecimal(empId) + "%");
          }
          if (empSt != null && empSt.size() > 0) {
            for (int i = 0; i < empSt.size(); i++) {
              if (empSt.get(i).toString().equals("1002")) {
                criterion += " p111.contract_st = 15  and p111.is_contract_write=0 and ";
                criterion += " not exists (select p.contract_id from pl120 p where p.contract_id=p111.contract_id) and ";
              }
            }
          }
          if (empSt != null && empSt.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < empSt.size(); i++) {
              criterion += " p111.contract_st = ? or ";
              parameters.add(empSt.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          if (criterion.length() != 0)
            criterion = " and "
            // criterion = " and
                // p111.loan_bank_id"+securityInfo.getDkSecuritySQL()
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
   * 合同弹出框count ----yuqf 银行权限
   * 
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param empId
   * @param empSt
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public int queryListCountByConditionYU_(final String contractId,
      final String borrowerName, final String cardNum, final String empId,
      final List empSt, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = " select p110.contract_id,p110.borrower_name,p110.card_num,p110.org_id,p110.org_name,p110.emp_id,"
              + " p111.contract_st from pl110 p110,pl111 p111 where p110.contract_id=p111.contract_id and p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num like ? and ";
            parameters.add("%" + cardNum + "%");
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " p110.emp_id like ? and ";
            parameters.add("%" + new BigDecimal(empId) + "%");
          }

          if (empSt != null && empSt.size() > 0) {
            for (int i = 0; i < empSt.size(); i++) {
              if (empSt.get(i).toString().equals("1001")) {
                criterion += " not exists (select p.contract_id from pl120 p where p.contract_id=p111.contract_id) and ";
              }
            }
          }

          if (empSt != null && empSt.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < empSt.size(); i++) {
              criterion += " p111.contract_st = ? or ";
              parameters.add(empSt.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          if (criterion.length() != 0)
            criterion = " and "
            // criterion = " and
                // p111.loan_bank_id"+securityInfo.getDkSecuritySQL()
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
   * yuqf 查询PL110字段，用于借款合同信息页面显示
   * 
   * @param id
   * @return
   */
  public EndorsecontractTaDTO queryBorrowerInfoYU(final String id,
      final SecurityInfo securityInfo) {
    EndorsecontractTaDTO endorsecontractTaDTO = null;
    try {
      endorsecontractTaDTO = (EndorsecontractTaDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              // TODO Auto-generated method stub

              String hql = "select t.borrower_name,t.card_kind,t.card_num,t.office from pl110 t where t.contract_id='"
                  + id + "'";
              Query query = session.createSQLQuery(hql);

              EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                endorsecontractTaDTO.setDebitter(obj[0].toString());// 借款人
                endorsecontractTaDTO.setCertificateType(obj[1].toString());// 证件类型
                endorsecontractTaDTO.setCertificateNum(obj[2].toString());// 证件号码
                endorsecontractTaDTO.setOffice(obj[3].toString());
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
   * hanL 根据借款人姓名证件号查询合同号
   */
  public List findEmpinborrowByEmpid(final String borrowname,
      final String cardNum) {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select p.contract_id from pl110 p where p.borrower_name = ? and "
            + "(p.card_num = ? or p.card_num = ?)";
        String tempCardnum = "";
        if (cardNum.length() == 18) {
          tempCardnum = CardMunChange.get15Id(cardNum);
        }
        if (cardNum.length() == 15) {
          tempCardnum = CardMunChange.get18Id(cardNum);
        }
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, borrowname);
        query.setParameter(1, cardNum);
        query.setParameter(2, tempCardnum);
        return query.list();
      }
    });
    return list;
  }
  /**
   * hanL 根据借款人姓名证件号查询合同号
   */
  public List findEmpinborrowByEmpid_wsh(final String borrowname,
      final String cardNum) {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select p.contract_id from pl113 p where p.name= ? and "
            + " (p.card_num = ? or p.card_num = ?) and p.status ='0' ";
        String tempCardnum = "";
        if (cardNum.length() == 18) {
          tempCardnum = CardMunChange.get15Id(cardNum);
        }
        if (cardNum.length() == 15) {
          tempCardnum = CardMunChange.get18Id(cardNum);
        }
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, borrowname);
        query.setParameter(1, cardNum);
        query.setParameter(2, tempCardnum);
        return query.list();
      }
    });
    return list;
  }
  public List findEmpinborrowByEmpid_wsh(final String borrowname,
      final String cardnum, final String cardNum_1) {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select  p.contract_id from pl110 p where p.borrower_name=? and (p.card_num=?"
            + " or p.card_num = ?)";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, borrowname);
        query.setParameter(1, cardnum);
        query.setParameter(2, cardNum_1);
        return query.list();
      }
    });
    return list;
  }

  /**
   * hanL 查询pl110
   */
  public Borrower findBorrrowInfoByContractid(final String contractid) {

    return (Borrower) getHibernateTemplate().get(Borrower.class, contractid);
  }

  /**
   * hanL 删除pl110
   */
  public void deleteBorrowerInfo(final String contractid) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "delete from Borrower borrower where borrower.contractId=?";
          Query query = session.createQuery(sql);
          query.setParameter(0, contractid);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * author wsh 查询扣款帐号修改页面信息
   * 
   * @param contractId
   * @return ReceiveaccModifyAF
   */
  public ReceiveaccModifyDTO queryBorrowerInfo_wsh(final String contractId) {
    ReceiveaccModifyDTO receiveaccModifyDTO = (ReceiveaccModifyDTO) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl111.contract_id contract_id,"
                + "pl110.borrower_name borrower_name,"
                + "pl110.card_kind card_kind,"
                + "pl110.card_num card_num,"
                + "pl110.org_name org_name,"
                + "pl111.loan_bank_id loan_bank_id,"
                + "pl111.loan_kou_acc loan_kou_acc "
                + "from pl110, pl111 "
                + "where pl110.contract_id=pl111.contract_id and pl111.contract_id = ? ";
            Vector parameters = new Vector();
            if (contractId != null) {
              parameters.add(contractId);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj[] = null;
            obj = (Object[]) query.uniqueResult();
            ReceiveaccModifyDTO temp_receiveaccModifyDTO = new ReceiveaccModifyDTO();
            if (obj[0] != null) {
              temp_receiveaccModifyDTO.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              temp_receiveaccModifyDTO.setBorrowerName(obj[1].toString());
            }
            if (obj[2] != null) {
              temp_receiveaccModifyDTO.setCardKind(obj[2].toString());
            }
            if (obj[3] != null) {
              temp_receiveaccModifyDTO.setCardNum(obj[3].toString());
            }

            if (obj[4] != null) {
              temp_receiveaccModifyDTO.setOrgName(obj[4].toString());
            }
            if (obj[5] != null) {
              temp_receiveaccModifyDTO.setLoanankId(obj[5].toString());
            }
            if (obj[6] != null) {
              temp_receiveaccModifyDTO.setOldLoanKouAcc(obj[6].toString());
            }
            return temp_receiveaccModifyDTO;
          }
        });
    return receiveaccModifyDTO;
  }

  /**
   * author wsh 查询新扣款帐号是否存在
   * 
   * @param newLoanKouAcc
   * @return
   */
  public Integer findBorrowerAccByLoanKouAcc_wsh(final String newLoanKouAcc) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(a.contract_id) from pl111 a where a.loan_kou_acc= ? ";
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
   * 贷款账号弹出框
   * 
   * @author 郭婧平 2007-9-24
   *         查询条件：loankouacc,contractId,borrowerName,cardNum,empId,empSt,
   */

  public List queryLoanKouAccpopListByCondition(final String loankouacc,
      final String contractId, final String borrowerName, final String cardNum,
      final String empId, final List empSt, final int start,
      final int pageSize, final String orderBy, final String order,
      final SecurityInfo securityInfo) {

    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = " select p110.contract_id,p110.borrower_name,p110.card_num,p110.org_id,p110.org_name,p110.emp_id,"
              + "p111.contract_st,p111.loan_kou_acc from pl110 p110,pl111 p111 where p110.contract_id=p111.contract_id ";

          Vector parameters = new Vector();
          String criterion = "";

          if (loankouacc != null && !"".equals(loankouacc)) {
            criterion += " p111.loan_kou_acc like ? and ";
            parameters.add("%" + loankouacc + "%");
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num like ? and ";
            parameters.add("%" + cardNum + "%");
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " to_char(p110.emp_id) like ? and ";
            parameters.add("%" + empId + "%");
          }

          if (empSt != null && empSt.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < empSt.size(); i++) {
              criterion += " p111.contract_st = ? or ";
              parameters.add(empSt.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          List loanbankList1 = null;
          try {
            // 取出用户权限放款银行,显示在下拉菜单中
            List loanbankList = securityInfo.getDkUserBankList();
            loanbankList1 = new ArrayList();
            Userslogincollbank bank = null;
            Iterator itr1 = loanbankList.iterator();
            while (itr1.hasNext()) {
              bank = (Userslogincollbank) itr1.next();
              loanbankList1.add(bank.getCollBankId());
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
          if (loanbankList1 != null && loanbankList1.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList1.size(); i++) {
              criterion += " p111.loan_bank_id = ? or ";
              parameters.add(loanbankList1.get(i));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p110.contract_id ";

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

          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            LoanKouAccpopDTO loanKouAccpopDTO = new LoanKouAccpopDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              loanKouAccpopDTO.setContractId(obj[0].toString());
            }
            if (obj[1] != null) {
              loanKouAccpopDTO.setBorrowerName(obj[1].toString());
            }
            if (obj[2] != null) {
              loanKouAccpopDTO.setCardNum(obj[2].toString());
            }
            if (obj[3] != null) {
              loanKouAccpopDTO.setOrgId(obj[3].toString());
            }
            if (obj[4] != null) {
              loanKouAccpopDTO.setOrgName(obj[4].toString());
            }
            if (obj[5] != null) {
              loanKouAccpopDTO.setEmpId(obj[5].toString());
            }
            String contractSt = "";
            if (obj[6] != null) {
              contractSt = obj[6].toString();
            }
            if (obj[7] != null) {
              loanKouAccpopDTO.setLoankouacc(obj[7].toString());
            }
            // 枚举转换合同状态
            try {
              loanKouAccpopDTO.setContractSt(BusiTools.getBusiValue(Integer
                  .parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS));
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            templist.add(loanKouAccpopDTO);
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
   * 贷款账号弹出框————count
   * 
   * @author 郭婧平 2007-9-24
   *         查询条件：loankouacc,contractId,borrowerName,cardNum,empId,empSt,
   */

  public int queryLoanKouAccListCountByCondition(final String loankouacc,
      final String contractId, final String borrowerName, final String cardNum,
      final String empId, final List empSt, final SecurityInfo securityInfo) {

    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = " select p110.contract_id,p110.borrower_name,p110.card_num,p110.org_id,p110.org_name,p110.emp_id,"
              + "p111.contract_st,p111.loan_kou_acc from pl110 p110,pl111 p111 where p110.contract_id=p111.contract_id ";

          Vector parameters = new Vector();
          String criterion = "";

          if (loankouacc != null && !"".equals(loankouacc)) {
            criterion += " p111.loan_kou_acc like ? and ";
            parameters.add("%" + loankouacc + "%");
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num like ? and ";
            parameters.add("%" + cardNum + "%");
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " to_char(p110.emp_id) like ? and ";
            parameters.add("%" + empId + "%");
          }

          if (empSt != null && empSt.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < empSt.size(); i++) {
              criterion += " p111.contract_st = ? or ";
              parameters.add(empSt.get(i).toString());
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }

          List loanbankList1 = null;
          try {
            // 取出用户权限放款银行,显示在下拉菜单中
            List loanbankList = securityInfo.getDkUserBankList();
            loanbankList1 = new ArrayList();
            Userslogincollbank bank = null;
            Iterator itr1 = loanbankList.iterator();
            while (itr1.hasNext()) {
              bank = (Userslogincollbank) itr1.next();
              loanbankList1.add(bank.getCollBankId());
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
          if (loanbankList1 != null && loanbankList1.size() > 0) {
            criterion += "( ";
            for (int i = 0; i < loanbankList1.size(); i++) {
              criterion += " p111.loan_bank_id = ? or ";
              parameters.add(loanbankList1.get(i));
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
          return query.list();
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list.size();
  }

  /**
   * hanL 根据合同号，查询借款人姓名
   * 
   * @param contractid
   * @return
   */
  public String findBorrowerNameInfoByContractid(final String contractid) {

    String name = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.borrower_name from pl110 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);

            return query.uniqueResult();
          }
        });

    return name;
  }

  /**
   * 查询审核贷款列表
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
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   */
  public List queryBorrowerListByCriterions(final String contractId,
      final String borrowerName, final String cardNum,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,
      final String contractStFind, final String isContractWrite,
      final String type, final int start, final String orderBy,
      final String order, final int pageSize, final int page,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p110.contract_id,p110.borrower_name,p110.card_num,"
              + "p115.loan_money,p115.loan_time_limit,p111.loan_bank_id,p110.org_name,"
              + "p114.house_area,p114.house_type,p111.contract_st,p114.bargainor_house_area "
              + "from pl110 p110,pl111 p111,pl114 p114,pl115 p115 "
              + "where "
              + "p110.contract_id=p111.contract_id "
              + "and "
              + "p110.contract_id=p115.contract_id "
              + "and "
              + "p111.contract_id=p114.contract_id and p110.operator "
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

          String ob = orderBy;
          if (ob == null)
            ob = " p110.contract_id ";

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
   * 查询审核贷款列表记录数(count)
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
   * @param securityInfo
   * @return
   */
  public int queryBorrowerCountByCriterions(final String contractId,
      final String borrowerName, final String cardNum,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,
      final String contractStFind, final String isContractWrite,
      final String type, final SecurityInfo securityInfo) {
    List list = new ArrayList();

    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select p110.contract_id,p110.borrower_name,p110.card_num,"
              + "p115.loan_money,p115.loan_time_limit,p111.loan_bank_id,p110.org_name,"
              + "p114.house_area,p114.house_type,p111.contract_st "
              + "from pl110 p110,pl111 p111,pl114 p114,pl115 p115 "
              + "where "
              + "p110.contract_id=p111.contract_id "
              + "and "
              + "p110.contract_id=p115.contract_id "
              + "and "
              + "p111.contract_id=p114.contract_id and p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
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

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id = ? and ";
            parameters.add(contractId);
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (orgName != null && !"".equals(orgName)) {
            criterion += " p110.org_name = ? and ";
            parameters.add(orgName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num = ? and ";
            parameters.add(cardNum);
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
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  public int queryBorrowerCountByCriterions_wsh(final String contractId,
      final String officeCode, final String borrowerName, final String cardNum,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,
      final String contractStFind, final String contractSt,
      final String isContractWrite, final String type,
      final SecurityInfo securityInfo, final String beginBackDate,
      final String endBackDate) {
    Integer count = new Integer(0);
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(p110.contract_id)"
              + " from pl110 p110,pl111 p111,pl114 p114,pl115 p115 "
              + " where p110.contract_id=p111.contract_id "
              + " and p110.contract_id=p115.contract_id "
              + " and p111.contract_id=p114.contract_id ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" +contractId + "%");
          }

          if (officeCode != null && !"".equals(officeCode)) {
            criterion += " p110.office = ? and ";
            parameters.add(officeCode);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
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

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num = ? and ";
            parameters.add(cardNum);
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
          if (beginBackDate != null && !"".equals(beginBackDate)) {
            criterion += " p110.re_date >= ? and ";
            parameters.add(beginBackDate);
          }

          if (endBackDate != null && !"".equals(endBackDate)) {
            criterion += " p110.re_date <= ? and ";
            parameters.add(endBackDate);
          }
          if (contractStFind != null && !"".equals(contractStFind)) {
            if(!contractStFind.equals("1")){
              criterion += " p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL()+" and ";
            }
            criterion += " p111.contract_st = ? and ";
            parameters.add(contractStFind);
          }else{
            criterion += " p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL()+" and ";
          }
          if (isContractWrite != null && !"".equals(isContractWrite)) {
            if(contractStFind != null && !"".equals(contractStFind) && !contractStFind.equals("1")){
              criterion += " p111.is_contract_write = ? and ";
              parameters.add(isContractWrite);
            }
          }

          if (type == null || "".equals(type)) {
            criterion += " p111.contract_st in (" + contractSt + ") and ";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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
   * 查询审批贷款列表
   * 
   * @author 王野 2007-09-27
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param loanBankName
   * @param orgName
   * @param houseType
   * @param type
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   */
  public List queryBorrowerListByCriterions(final String contractId,
      final String borrowerName, final String cardNum, final String officeCode,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,
      final String contractStFind, final String type, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String st) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p110.contract_id,p110.borrower_name,p110.card_num,"
              + " p115.loan_money,p115.loan_time_limit,p111.loan_bank_id,p110.org_name,"
              + " case when p114.house_type = '01' then to_char(p114.house_area) else p114.bargainor_house_area end,"
              + " p114.house_type,p111.contract_st,"
              + " case when p114.house_type = '01' then p114.house_totle_price else p114.bargainor_totle_price end,"
              + " (select a.name from pl113 a where a.auxiliary_id=(select max( p113.auxiliary_id)"
              + " from pl113 p113 "
              + " where p113.contract_id = p110.contract_id)),"
              + " case when p110.privilege_borrower_id is null then 1 else 0 end,"
              + " p110.operator,"
              + " case when p114.house_type = '01' then to_char(p114.house_addr) else p114.bargainor_house_addr end"
              + " from pl110 p110,pl111 p111,pl114 p114,pl115 p115"
              + " where p110.contract_id=p111.contract_id"
              + " and p110.contract_id=p115.contract_id"
              + " and p111.contract_id=p114.contract_id and p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();

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
          System.out.println(st + "============>");
          if (type == null || "".equals(type)) {
            criterion += " p111.contract_st in (?) and ";
            parameters.add(st);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p110.contract_id ";

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

  public List queryBorrowerListByCriterions_wsh(final String contractId,
      final String borrowerName, final String cardNum, final String officeCode,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,final String begindate, final String enddate,
      final String contractStFind, final String type, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p110.contract_id,p110.borrower_name,p110.card_num,"
            + " case when p111.contract_st between 11 and 13 then p111.loan_money else p115.loan_money end loanmoney,"
            + " p115.loan_time_limit,p111.loan_bank_id,p110.org_name,"
            + " case when p114.house_type = '01' then to_char(p114.house_area) else p114.bargainor_house_area end,"
            + " p114.house_type,p111.contract_st,"
            + " case when p114.house_type = '01' then p114.house_totle_price else p114.bargainor_totle_price end,"
            + " (select a.name from pl113 a where a.auxiliary_id=(select max( p113.auxiliary_id)"
            + " from pl113 p113 "
            + " where p113.contract_id = p110.contract_id)),"
            + " case when p110.privilege_borrower_id is null then 1 else 0 end,"
            + " p110.operator,"
            + " case when p114.house_type = '01' then to_char(p114.house_addr) else p114.bargainor_house_addr end,"
            + " p111.chkagainperson,"
            + " p111.vipchkagainperson,"
            + " p111.lastchkperson,"
            + " p110.lastcheck_date"
            + " from pl110 p110,pl111 p111,pl114 p114,pl115 p115"
            + " where p110.contract_id=p111.contract_id"
            + " and p110.contract_id=p115.contract_id"
            + " and p111.contract_id=p114.contract_id  ";

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

          String ob = orderBy;
          if (ob == null)
            ob = " p110.contract_id ";

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
  public List queryBorrowerListByCriterions_wsh_yg(final String contractId,
      final String borrowerName, final String cardNum, final String officeCode,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,final String begindate, final String enddate,
      final String contractStFind, final String type, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        
        public Object doInHibernate(Session session) throws HibernateException,
        SQLException {
          
          String hql = "select p110.contract_id,p110.borrower_name,p110.card_num,"
            + " case when p111.contract_st between 11 and 13 then p111.loan_money else p115.loan_money end loanmoney,"
            + " p115.loan_time_limit,p111.loan_bank_id,p110.org_name,"
            + " case when p114.house_type = '01' then to_char(p114.house_area) else p114.bargainor_house_area end,"
            + " p114.house_type,p111.contract_st,"
            + " case when p114.house_type = '01' then p114.house_totle_price else p114.bargainor_totle_price end,"
            + " (select a.name from pl113 a where a.auxiliary_id=(select max( p113.auxiliary_id)"
            + " from pl113 p113 "
            + " where p113.contract_id = p110.contract_id)),"
            + " case when p110.privilege_borrower_id is null then 1 else 0 end,"
            + " p110.operator,"
            + " case when p114.house_type = '01' then to_char(p114.house_addr) else p114.bargainor_house_addr end,"
            + " p111.chkagainperson,"
            + " p111.vipchkagainperson"
            + " from pl110 p110,pl111 p111,pl114 p114,pl115 p115"
            + " where p110.contract_id=p111.contract_id"
            + " and p110.contract_id=p115.contract_id"
            + " and p111.contract_id=p114.contract_id ";
          
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
          
          String ob = orderBy;
          if (ob == null)
            ob = " p110.contract_id ";
          
          String od = order;
          if (od == null)
            od = " DESC";
          
          hql = hql + criterion + " order by " + ob + " " + od;
          
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
   * 查询审批贷款列表记录数(count)
   * 
   * @author 王野 2007-09-27
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param loanBankName
   * @param orgName
   * @param houseType
   * @param type
   * @param securityInfo
   * @return
   */
  public int queryBorrowerCountByCriterions(final String contractId,
      final String borrowerName, final String cardNum, final String officeCode,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,
      final String contractStFind, final String type,
      final SecurityInfo securityInfo, final String st) {
    Integer count = new Integer(0);

    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(p110.contract_id)"
              + " from pl110 p110,pl111 p111,pl114 p114,pl115 p115"
              + " where p110.contract_id=p111.contract_id"
              + " and p110.contract_id=p115.contract_id"
              + " and p111.contract_id=p114.contract_id and p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
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

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id = ? and ";
            parameters.add(contractId);
          }

          if (officeCode != null && !"".equals(officeCode)) {
            criterion += " p110.office = ? and ";
            parameters.add(officeCode);
          }
          
          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (orgName != null && !"".equals(orgName)) {
            criterion += " p110.org_name = ? and ";
            parameters.add(orgName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num = ? and ";
            parameters.add(cardNum);
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
            criterion += " p111.contract_st in (?) and ";
            parameters.add(st);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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

  public int queryBorrowerCountByCriterions_wsh(final String contractId,
      final String borrowerName, final String cardNum, final String officeCode,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,final String begindate, final String enddate,
      final String contractStFind, final String type,
      final SecurityInfo securityInfo) {
    Integer count = new Integer(0);

    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(p110.contract_id)"
              + "from pl110 p110,pl111 p111,pl114 p114,pl115 p115 " + "where "
              + "p110.contract_id=p111.contract_id " + "and "
              + "p110.contract_id=p115.contract_id " + "and "
              + "p111.contract_id=p114.contract_id";
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

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (officeCode != null && !"".equals(officeCode)) {
            criterion += " p110.office = ? and ";
            parameters.add(officeCode);
          }
          
          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (orgName != null && !"".equals(orgName)) {
            criterion += " p110.org_name = ? and ";
            parameters.add(orgName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num = ? and ";
            parameters.add(cardNum);
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
          return Integer.valueOf(query.uniqueResult().toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }

  /**
   * hanl 是否是特殊借款
   * 
   * @param contractid
   * @return
   */

  public String findPrivilegeBorrowerIdByContractid(final String contractid) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.privilege_borrower_id from pl110 p where p.contract_id=? and p.privilege_borrower_id!=0 ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);
            if (query.uniqueResult() == null) {
              return null;
            }
            return query.uniqueResult().toString();
          }
        });
    return id;
  }

  /**
   * yuqf 签订合同维护列表显示之多表查询 PL111 是否有是否签订＝0 合同状态＝2 的 纪录
   * 
   * @return 2007-10-03
   */
  public List queryTeList02YU(final String contractId, final String

  debitter, final String empId, final String cardNum, final String bank,
      final String

      loanTimeLimit, final String startSDate, final String startEDate,
      final String endSDate, final String endEDate, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new

      HibernateCallback() {

        public Object doInHibernate(Session session) throws

        HibernateException, SQLException {
          String hql = " select t1.contract_id,"
              + " t1.emp_id,"
              + " t1.borrower_name,"
              + " t3.loan_money,"
              + " t4.loan_start_date,"
              + " t3.loan_time_limit,"
              + " t3.loan_month_rate,"
              + " t3.corpus_interest,"
              + " t2.loan_bank_id,"
              + " to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd'),"
              + " t2.is_contract_write,"
              + " t3.loan_mode, "
              + " t2.contract_st, "
              + " t4.photo_url2, "
              // + " t6.fund_status "
              + " (select t6.fund_status from pl114 t5,pl006 t6 where t5.floor_id=t6.floor_id and t5.contract_id = t4.contract_id)"
              + " from pl110 t1, pl111 t2, pl115 t3, pl120 t4 "
              + " where t1.contract_id = t2.contract_id"
              + " and t2.contract_id = t3.contract_id"
              + " and t3.contract_id = t4.contract_id"
              // + " and t4.contract_id = t5.contract_id"
              // + " and t5.floor_id=t6.floor_id"
              + " and t2.is_contract_write = '0'"
              + " and t2.contract_st = '15'" + " and t1.operator "
              + securityInfo.getDkUserSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " t1.contract_id like ? and ";
            parameters.add("%"+contractId+"%");
          }

          if (debitter != null && !"".equals(debitter)) {
            criterion += " t1.borrower_name = ? and ";
            parameters.add(debitter);
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " t1.emp_id = ? and ";
            parameters.add(empId);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " t1.card_num = ? and ";
            parameters.add(cardNum);
          }

          if (bank != null && !"".equals(bank)) {
            criterion += " t2.loan_bank_id = ? and ";
            parameters.add(bank);
          }

          if (loanTimeLimit != null && !"".equals(loanTimeLimit)) {
            criterion += " t3.loan_time_limit = ? and ";
            parameters.add(loanTimeLimit);
          }

          // if (startSDate != null && !"".equals(startSDate) && startEDate !=
          //
          // null && !"".equals(startEDate)) {
          // criterion += " (t4.loan_start_date between ? and ?) and ";
          // parameters.add(startSDate);
          // parameters.add(startEDate);
          // }
          if (startSDate != null && !"".equals(startSDate)) {
            criterion += " (t4.loan_start_date  >=  ?) and ";
            parameters.add(startSDate);
          }
          if (startEDate != null && !"".equals(startEDate)) {
            criterion += " (t4.loan_start_date  <= ?) and ";
            parameters.add(startEDate);
          }

          // if (endSDate != null && !"".equals(endSDate) && endEDate != null
          //
          // && !"".equals(endEDate)) {
          // criterion += "
          // (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t2.loan_time_limit),'yyyymmdd')
          // between ? and ?) and ";
          // parameters.add(endSDate);
          // parameters.add(endEDate);
          // }
          if (endSDate != null && !"".equals(endSDate)) {
            criterion += " (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') >= ?) and ";
            parameters.add(endSDate);
          }
          if (endEDate != null && !"".equals(endEDate)) {
            criterion += " (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') <= ?) and ";
            parameters.add(endEDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " t1.contract_id ";

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
          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            EndorsecontractTeDTO endorsecontractTeDTO = new

            EndorsecontractTeDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              endorsecontractTeDTO.setContractId(obj[0].toString());// 合同编号
            }
            if (obj[1] != null) {
              endorsecontractTeDTO.setEmpId(obj[1].toString());// 职工编号
            }
            if (obj[2] != null) {
              endorsecontractTeDTO.setDebitter(obj[2].toString());// 借款人
            }
            if (obj[3] != null) {
              endorsecontractTeDTO.setLoanMoney(obj[3].toString());// 借款金额
            }
            if (obj[4] != null) {
              endorsecontractTeDTO.setStartDate(obj[4].toString());// 借款开始时间
            }
            if (obj[5] != null) {
              endorsecontractTeDTO.setLoanTimeLimit(obj[5].toString());// 还款期限
            }
            if (obj[6] != null) {
              endorsecontractTeDTO.setLoanMonthRate(obj[6].toString());// 月利息
            }
            if (obj[7] != null) {
              endorsecontractTeDTO.setCorpusInterest(obj[7].toString());// 月还本息
            }
            if (obj[8] != null) {
              endorsecontractTeDTO.setBank(obj[8].toString());// 放款银行
            }
            if (obj[9] != null) {
              endorsecontractTeDTO.setEndDate(obj[9].toString());// 结束日期
            }
            if (obj[10] != null) {
              endorsecontractTeDTO.setContractStatus(obj[10].toString());// 合同状态
            }
            if (obj[11] != null) {
              endorsecontractTeDTO.setLoanMode(obj[11].toString());// 还款方式
            }
            if (obj[12] != null) {
              endorsecontractTeDTO.setContractSt(obj[12].toString());// 还款方式
            }
            if (obj[13] != null) {
              endorsecontractTeDTO.setPhotoUrl(obj[13].toString());// 还款方式
            }
            if (obj[14] != null) {
              if ("1".equals(obj[14].toString())) {
                endorsecontractTeDTO.setFundStatus("是");
              } else {
                endorsecontractTeDTO.setFundStatus("否");
              }
            } else {
              endorsecontractTeDTO.setFundStatus("—");
            }
            templist.add(endorsecontractTeDTO);
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
   * yuqf count 纪录 签订合同维护列表显示之多表查询 PL111 是否有是否签订＝0 合同状态＝2 的 纪录
   * 
   * @return 2007-10-03
   */
  public int queryCountTeList02YU(final String contractId, final String

  debitter, final String empId, final String cardNum, final String bank,
      final String

      loanTimeLimit, final String startSDate, final String startEDate,
      final String endSDate, final String endEDate, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo) {
    Integer count = new Integer(0);
    try {
      count = (Integer) getHibernateTemplate().execute(new

      HibernateCallback() {

        public Object doInHibernate(Session session) throws

        HibernateException, SQLException {
          String hql = " select count(t1.contract_id) "
              + " from pl110 t1, pl111 t2, pl115 t3, pl120 t4 "
              + " where t1.contract_id = t2.contract_id"
              + " and t2.contract_id = t3.contract_id"
              + " and t3.contract_id = t4.contract_id"
              // + " and t4.contract_id = t5.contract_id "
              // + " and t5.floor_id=t6.floor_id "
              + " and t2.is_contract_write = '0'"
              + " and t2.contract_st = '15'" + " and t1.operator "
              + securityInfo.getDkUserSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " t1.contract_id like ? and ";
            parameters.add("%"+contractId+"%");
          }

          if (debitter != null && !"".equals(debitter)) {
            criterion += " t1.borrower_name = ? and ";
            parameters.add(debitter);
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " t1.emp_id = ? and ";
            parameters.add(empId);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " t1.card_num = ? and ";
            parameters.add(cardNum);
          }

          if (bank != null && !"".equals(bank)) {
            criterion += " t2.loan_bank_id = ? and ";
            parameters.add(bank);
          }

          if (loanTimeLimit != null && !"".equals(loanTimeLimit)) {
            criterion += " t3.loan_time_limit = ? and ";
            parameters.add(loanTimeLimit);
          }

          // if (startSDate != null && !"".equals(startSDate)
          // && startEDate != null && !"".equals(startEDate)) {
          // criterion += " (t4.loan_start_date between ? and ?) and ";
          // parameters.add(startSDate);
          // parameters.add(startEDate);
          // }
          if (startSDate != null && !"".equals(startSDate)) {
            criterion += " (t4.loan_start_date  >=  ?) and ";
            parameters.add(startSDate);
          }
          if (startEDate != null && !"".equals(startEDate)) {
            criterion += " (t4.loan_start_date  <=  ?) and ";
            parameters.add(startEDate);
          }

          // if (endSDate != null && !"".equals(endSDate) && endEDate != null
          // && !"".equals(endEDate)) {
          // criterion += "
          // (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t2.loan_time_limit),'yyyymmdd')
          // between ? and ?) and ";
          // parameters.add(endSDate);
          // parameters.add(endEDate);
          // }
          if (endSDate != null && !"".equals(endSDate)) {
            criterion += " (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') >= ?) and ";
            parameters.add(endSDate);
          }
          if (endEDate != null && !"".equals(endEDate)) {
            criterion += " (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') <= ?) and ";
            parameters.add(endEDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " t1.contract_id ";

          String od = order;
          if (od == null)
            od = " DESC";

          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
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
   * yuqf 签订合同维护列表显示之多表查询 PL111 是否有是否签订＝0 合同状态＝4 的 纪录
   * 
   * @return 2007-10-03
   */
  public List queryTeList04YU(final String contractId, final String

  debitter, final String empId, final String cardNum, final String bank,
      final String

      loanTimeLimit, final String startSDate, final String startEDate,
      final String endSDate, final String endEDate, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new

      HibernateCallback() {

        public Object doInHibernate(Session session) throws

        HibernateException, SQLException {
          String hql = " select t1.contract_id,"
              + " t1.emp_id,"
              + " t1.borrower_name,"
              + " t3.loan_money,"
              + " t4.loan_start_date,"
              + " t3.loan_time_limit,"
              + " t3.loan_month_rate,"
              + " t3.corpus_interest,"
              + " t2.loan_bank_id,"
              + " t2.is_contract_write,"
              + " to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd'),"
              + " t3.loan_mode, " + " t2.contract_st "
              + " from pl110 t1, pl111 t2, pl115 t3, pl120 t4"
              + " where t1.contract_id = t2.contract_id"
              + " and t2.contract_id = t3.contract_id"
              + " and t3.contract_id = t4.contract_id"
              + " and t2.is_contract_write = '0'"
              + " and t2.contract_st = '15'" + " and t1.operator "
              + securityInfo.getDkUserSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " t1.contract_id like ? and ";
            parameters.add("%"+contractId+"%");
          }

          if (debitter != null && !"".equals(debitter)) {
            criterion += " t1.borrower_name = ? and ";
            parameters.add(debitter);
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " t1.emp_id = ? and ";
            parameters.add(empId);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " t1.card_num = ? and ";
            parameters.add(cardNum);
          }

          if (bank != null && !"".equals(bank)) {
            criterion += " t2.loan_bank_id = ? and ";
            parameters.add(bank);
          }

          if (loanTimeLimit != null && !"".equals(loanTimeLimit)) {
            criterion += " t3.loan_time_limit = ? and ";
            parameters.add(loanTimeLimit);
          }

          // if (startSDate != null && !"".equals(startSDate) && startEDate !=
          //
          // null && !"".equals(startEDate)) {
          // criterion += " (t4.loan_start_date between ? and ?) and ";
          // parameters.add(startSDate);
          // parameters.add(startEDate);
          // }
          if (startSDate != null && !"".equals(startSDate)) {
            criterion += " (t4.loan_start_date  between >=  ?) and ";
            parameters.add(startSDate);
          }
          if (startEDate != null && !"".equals(startEDate)) {
            criterion += " (t4.loan_start_date  between <=  ?) and ";
            parameters.add(startEDate);
          }

          // if (endSDate != null && !"".equals(endSDate) && endEDate != null
          // && !"".equals(endEDate)) {
          // criterion += "
          // (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t2.loan_time_limit),'yyyymmdd')
          // between ? and ?) and ";
          // parameters.add(endSDate);
          // parameters.add(endEDate);
          // }
          if (endSDate != null && !"".equals(endSDate)) {
            criterion += " (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') >= ?) and ";
            parameters.add(endSDate);
          }
          if (endEDate != null && !"".equals(endEDate)) {
            criterion += " (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') <= ?) and ";
            parameters.add(endEDate);
          }

          if (criterion.length() != 0)
            // criterion = " and t1.operator="
            // + securityInfo.getUserInfo().getUsername()
            // + criterion.substring(0, criterion.lastIndexOf("and"));
            criterion = " and"
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " t1.contract_id ";

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
          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            EndorsecontractTeDTO endorsecontractTeDTO = new

            EndorsecontractTeDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              endorsecontractTeDTO.setContractId(obj[0].toString());// 合同编号
            }
            if (obj[1] != null) {
              endorsecontractTeDTO.setEmpId(obj[1].toString());// 职工编号
            }
            if (obj[2] != null) {
              endorsecontractTeDTO.setDebitter(obj[2].toString());// 借款人
            }
            if (obj[3] != null) {
              endorsecontractTeDTO.setLoanMoney(obj[3].toString());// 借款金额
            }
            if (obj[4] != null) {
              endorsecontractTeDTO.setStartDate(obj[4].toString());// 借款开始时间
            }
            if (obj[5] != null) {
              endorsecontractTeDTO.setLoanTimeLimit(obj[5].toString());// 还款期限
            }
            if (obj[6] != null) {
              endorsecontractTeDTO.setLoanMonthRate(obj[6].toString());// 月利息
            }
            if (obj[7] != null) {
              endorsecontractTeDTO.setCorpusInterest(obj[7].toString());// 月还本息
            }
            if (obj[8] != null) {
              endorsecontractTeDTO.setBank(obj[8].toString());// 放款银行
            }
            if (obj[9] != null) {
              endorsecontractTeDTO.setContractStatus(obj[9].toString());// 合同状态
            }
            if (obj[10] != null) {
              endorsecontractTeDTO.setEndDate(obj[10].toString());// 终止日期
            }
            if (obj[11] != null) {
              endorsecontractTeDTO.setLoanMode(obj[11].toString());// 还款方式
            }
            if (obj[12] != null) {
              endorsecontractTeDTO.setContractSt(obj[12].toString());// 还款方式
            }
            templist.add(endorsecontractTeDTO);
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
   * yuqf count 纪录 签订合同维护列表显示之多表查询 PL111 是否有是否签订＝0 合同状态＝4 的 纪录
   * 
   * @return 2007-10-03
   */
  public int queryCountTeList04YU(final String contractId, final String

  debitter, final String empId, final String cardNum, final String bank,
      final String

      loanTimeLimit, final String startSDate, final String startEDate,
      final String endSDate, final String endEDate, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new

      HibernateCallback() {

        public Object doInHibernate(Session session) throws

        HibernateException, SQLException {
          String hql = " select count(t1.contract_id) "
              + " from pl110 t1, pl111 t2, pl115 t3, pl120 t4"
              + " where t1.contract_id = t2.contract_id"
              + " and t2.contract_id = t3.contract_id"
              + " and t3.contract_id = t4.contract_id"
              + " and t2.is_contract_write = '0'"
              + " and t2.contract_st = '15'" + " and t1.operator "
              + securityInfo.getDkUserSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " t1.contract_id like ? and ";
            parameters.add("%"+contractId+"%");
          }

          if (debitter != null && !"".equals(debitter)) {
            criterion += " t1.borrower_name = ? and ";
            parameters.add(debitter);
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " t1.emp_id = ? and ";
            parameters.add(empId);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " t1.card_num = ? and ";
            parameters.add(cardNum);
          }

          if (bank != null && !"".equals(bank)) {
            criterion += " t2.loan_bank_id = ? and ";
            parameters.add(bank);
          }

          if (loanTimeLimit != null && !"".equals(loanTimeLimit)) {
            criterion += " t3.loan_time_limit = ? and ";
            parameters.add(loanTimeLimit);
          }

          // if (startSDate != null && !"".equals(startSDate) && startEDate !=
          //
          // null && !"".equals(startEDate)) {
          // criterion += " (t4.loan_start_date between ? and ?) and ";
          // parameters.add(startSDate);
          // parameters.add(startEDate);
          // }
          if (startSDate != null && !"".equals(startSDate)) {
            criterion += " (t4.loan_start_date  between >=  ?) and ";
            parameters.add(startSDate);
          }
          if (startEDate != null && !"".equals(startEDate)) {
            criterion += " (t4.loan_start_date  between <=  ?) and ";
            parameters.add(startEDate);
          }
          // if (endSDate != null && !"".equals(endSDate) && endEDate != null
          // && !"".equals(endEDate)) {
          // criterion += "
          // (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t2.loan_time_limit),'yyyymmdd')
          // between ? and ?) and ";
          // parameters.add(endSDate);
          // parameters.add(endEDate);
          // }
          if (endSDate != null && !"".equals(endSDate)) {
            criterion += " (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') >= ?) and ";
            parameters.add(endSDate);
          }
          if (endEDate != null && !"".equals(endEDate)) {
            criterion += " (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') <= ?) and ";
            parameters.add(endEDate);
          }

          if (criterion.length() != 0)
            // criterion = " and t1.operator="
            // + securityInfo.getUserInfo().getUsername()
            // + criterion.substring(0, criterion.lastIndexOf("and"));
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " t1.contract_id ";

          String od = order;
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
            EndorsecontractTeDTO endorsecontractTeDTO = new

            EndorsecontractTeDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              endorsecontractTeDTO.setContractId(obj[0].toString());// 合同编号
            }
            if (obj[1] != null) {
              endorsecontractTeDTO.setEmpId(obj[1].toString());// 职工编号
            }
            if (obj[2] != null) {
              endorsecontractTeDTO.setDebitter(obj[2].toString());// 借款人
            }
            if (obj[3] != null) {
              endorsecontractTeDTO.setLoanMoney(obj[3].toString());// 借款金额
            }
            if (obj[4] != null) {
              endorsecontractTeDTO.setStartDate(obj[4].toString());// 借款开始时间
            }
            if (obj[5] != null) {
              endorsecontractTeDTO.setLoanTimeLimit(obj[5].toString());// 还款期限
            }
            if (obj[6] != null) {
              endorsecontractTeDTO.setLoanMonthRate(obj[6].toString());// 月利息
            }
            if (obj[7] != null) {
              endorsecontractTeDTO.setCorpusInterest(obj[7].toString());// 月还本息
            }
            if (obj[8] != null) {
              endorsecontractTeDTO.setBank(obj[8].toString());// 放款银行
            }
            if (obj[10] != null) {
              endorsecontractTeDTO.setContractSt(obj[10].toString());// 放款银行
            }
            templist.add(endorsecontractTeDTO);
          }
          return templist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  /**
   * yuqf 签订合同维护列表显示之多表查询纪录 有条件查询
   * 
   * @return 2007-10-03
   */
  public List queryTeListYU(final String contractId, final String

  debitter, final String empId, final String cardNum, final String bank,
      final String

      loanTimeLimit, final String startSDate, final String startEDate,
      final String endSDate, final String endEDate, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String contractSt) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new

      HibernateCallback() {

        public Object doInHibernate(Session session) throws

        HibernateException, SQLException {
          String hql = " select t1.contract_id,"
              + " t1.emp_id,"
              + " t1.borrower_name,"
              + " t3.loan_money,"
              + " t4.loan_start_date,"
              + " t3.loan_time_limit,"
              + " t3.loan_month_rate,"
              + " t3.corpus_interest,"
              + " t2.loan_bank_id,"
              + " t2.is_contract_write,"
              + " to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd'),"
              + " t3.loan_mode, "
              + " t2.contract_st,"
              // + " t6.fund_status,"
              + " (select t6.fund_status from pl114 t5,pl006 t6 where t5.floor_id=t6.floor_id and t5.contract_id = t4.contract_id),"
              + " t4.photo_url2 "
              + " from pl110 t1, pl111 t2, pl115 t3, pl120 t4 "
              + " where t1.contract_id = t2.contract_id"
              + " and t2.contract_id = t3.contract_id"
              + " and t3.contract_id = t4.contract_id" + " and t1.operator "
              + securityInfo.getDkUserSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " and t1.contract_id like ? ";
            parameters.add("%"+contractId+"%");
          }

          if (debitter != null && !"".equals(debitter)) {
            criterion += " and t1.borrower_name = ? ";
            parameters.add(debitter);
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " and t1.emp_id = ?  ";
            parameters.add(empId);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " and t1.card_num = ?  ";
            parameters.add(cardNum);
          }

          if (bank != null && !"".equals(bank)) {
            criterion += " and t2.loan_bank_id = ?  ";
            parameters.add(bank);
          }

          if (loanTimeLimit != null && !"".equals(loanTimeLimit)) {
            criterion += " and t3.loan_time_limit = ?  ";
            parameters.add(loanTimeLimit);
          }

          // if (startSDate != null && !"".equals(startSDate) && startEDate !=
          // null && !"".equals(startEDate)) {
          // criterion += " and (t4.loan_start_date between ? and ?) ";
          // parameters.add(startSDate);
          // parameters.add(startEDate);
          // }
          if (startSDate != null && !"".equals(startSDate)) {
            criterion += " and (t4.loan_start_date  >= ?)  ";
            parameters.add(startSDate);
          }
          if (startEDate != null && !"".equals(startEDate)) {
            criterion += " and (t4.loan_start_date  <= ?)  ";
            parameters.add(startEDate);
          }

          // if (endSDate != null && !"".equals(endSDate) && endEDate != null
          // && !"".equals(endEDate)) {
          // criterion += " and
          // (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t2.loan_time_limit),'yyyymmdd')
          // between ? and ?) ";
          // parameters.add(endSDate);
          // parameters.add(endEDate);
          // }
          if (endSDate != null && !"".equals(endSDate)) {
            criterion += " and (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') >= ?)  ";
            parameters.add(endSDate);
          }
          if (endEDate != null && !"".equals(endEDate)) {
            criterion += " and (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') <= ?)  ";
            parameters.add(endEDate);
          }
          if (contractSt != null && !"".equals(contractSt)) {
            criterion += " and t2.contract_st=? ";
            parameters.add(contractSt);
          }

          // if (criterion.length() != 0)
          // criterion = " and t1.operator "
          // + securityInfo.getDkUserSecuritySQL()
          // // + criterion.substring(0, criterion.lastIndexOf("and"));
          // + criterion;

          String ob = orderBy;
          if (ob == null)
            ob = " t1.contract_id ";

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
          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            EndorsecontractTeDTO endorsecontractTeDTO = new

            EndorsecontractTeDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              endorsecontractTeDTO.setContractId(obj[0].toString());// 合同编号
            }
            if (obj[1] != null) {
              endorsecontractTeDTO.setEmpId(obj[1].toString());// 职工编号
            }
            if (obj[2] != null) {
              endorsecontractTeDTO.setDebitter(obj[2].toString());// 借款人
            }
            if (obj[3] != null) {
              endorsecontractTeDTO.setLoanMoney(obj[3].toString());// 借款金额
            }
            if (obj[4] != null) {
              endorsecontractTeDTO.setStartDate(obj[4].toString());// 借款开始时间
            }
            if (obj[5] != null) {
              endorsecontractTeDTO.setLoanTimeLimit(obj[5].toString());// 还款期限
            }
            if (obj[6] != null) {
              endorsecontractTeDTO.setLoanMonthRate(obj[6].toString());// 月利息
            }
            if (obj[7] != null) {
              endorsecontractTeDTO.setCorpusInterest(obj[7].toString());// 月还本息
            }
            if (obj[8] != null) {
              endorsecontractTeDTO.setBank(obj[8].toString());// 放款银行
            }
            if (obj[9] != null) {
              endorsecontractTeDTO.setContractStatus(obj[9].toString());// 合同状态
            }
            if (obj[10] != null) {
              endorsecontractTeDTO.setEndDate(obj[10].toString());// 合同中止日期
            }
            if (obj[11] != null) {
              endorsecontractTeDTO.setLoanMode(obj[11].toString());// 还款方式
            }
            if (obj[12] != null) {
              endorsecontractTeDTO.setContractSt(obj[12].toString());// 合同状态
            }
            if (obj[13] != null) {
              if ("1".equals(obj[13].toString())) {
                endorsecontractTeDTO.setFundStatus("是");
              } else {
                endorsecontractTeDTO.setFundStatus("否");
              }
            } else {
              endorsecontractTeDTO.setFundStatus("—");
            }
            if (obj[14] != null) {
              endorsecontractTeDTO.setPhotoUrl(obj[14].toString());
            }
            templist.add(endorsecontractTeDTO);
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
   * 合同维护 count有条件查询 yuqf 2007-10-05
   * 
   * @param contractId
   * @param debitter
   * @param empId
   * @param cardNum
   * @param bank
   * @param loanTimeLimit
   * @param startSDate
   * @param startEDate
   * @param endSDate
   * @param endEDate
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @return
   */
  public int queryCountTeListYU(final String contractId, final String

  debitter, final String empId, final String cardNum, final String bank,
      final String

      loanTimeLimit, final String startSDate, final String startEDate,
      final String endSDate, final String endEDate, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo, final String contractSt) {
    Integer count = new Integer(0);
    try {
      count = (Integer) getHibernateTemplate().execute(new

      HibernateCallback() {

        public Object doInHibernate(Session session) throws

        HibernateException, SQLException {
          String hql = " select count(t1.contract_id) "
              + " from pl110 t1, pl111 t2, pl115 t3, pl120 t4 "
              + " where t1.contract_id = t2.contract_id"
              + " and t2.contract_id = t3.contract_id"
              + " and t3.contract_id = t4.contract_id " + " and t1.operator "
              + securityInfo.getDkUserSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " and t1.contract_id like ?  ";
            parameters.add("%"+contractId+"%");
          }

          if (debitter != null && !"".equals(debitter)) {
            criterion += " and t1.borrower_name = ?  ";
            parameters.add(debitter);
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " and t1.emp_id = ?  ";
            parameters.add(empId);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " and t1.card_num = ?  ";
            parameters.add(cardNum);
          }

          if (bank != null && !"".equals(bank)) {
            criterion += " and t2.loan_bank_id = ?  ";
            parameters.add(bank);
          }

          if (loanTimeLimit != null && !"".equals(loanTimeLimit)) {
            criterion += " and t3.loan_time_limit = ?  ";
            parameters.add(loanTimeLimit);
          }

          // if (startSDate != null && !"".equals(startSDate) && startEDate !=
          //
          // null && !"".equals(startEDate)) {
          // criterion += " and (t4.loan_start_date between ? and ?) ";
          // parameters.add(startSDate);
          // parameters.add(startEDate);
          // }
          if (startSDate != null && !"".equals(startSDate)) {
            criterion += " and (t4.loan_start_date    >=  ?)  ";
            parameters.add(startSDate);
          }
          if (startEDate != null && !"".equals(startEDate)) {
            criterion += " and (t4.loan_start_date   <=  ?)  ";
            parameters.add(startEDate);
          }
          // if (endSDate != null && !"".equals(endSDate) && endEDate != null
          // && !"".equals(endEDate)) {
          // criterion += " and
          // (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t2.loan_time_limit),'yyyymmdd')
          // between ? and ?) ";
          // parameters.add(endSDate);
          // parameters.add(endEDate);
          // }
          if (endSDate != null && !"".equals(endSDate)) {
            criterion += " and (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') >= ?)  ";
            parameters.add(endSDate);
          }
          if (endEDate != null && !"".equals(endEDate)) {
            criterion += " and (to_char(add_months(to_date(substr(t4.loan_start_date,0,6),'yyyymm'),t3.loan_time_limit),'yyyymmdd') <= ?)  ";
            parameters.add(endEDate);
          }
          if (contractSt != null && !"".equals(contractSt)) {
            criterion += " and t2.contract_st=? ";
            parameters.add(contractSt);
          }
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
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
   * hanl 求出列表要显示的信息 ---带分页的list 不包括申请状态
   * 
   * @param contactid
   * @param borrowername
   * @param empid
   * @param cardnum
   * @param housetype
   * @param contact_st
   * @param office
   * @param loanbank
   * @param paymood
   * @param assistantorgid
   * @param headid
   * @param floorid
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List findLoanapplyInfo(final String contactid,
      final String borrowername, final String empid, final String cardnum,
      final String housetype, final String contact_st, final String office,
      final String loanbank, final String paymood, final String assistantorgid,
      final String headid, final String floorName, final String houseAreaSt,
      final String houseAreaEnd, final String agreementDateSt,
      final String agreementDateEnd, final String loanMoneySt,
      final String loanMoneyEnd, final String loanTimeLimitSt,
      final String loanTimeLimitEnd, final String isSignAgreement,
      final String signAgreementDateStart, final String signAgreementDateEnd,
      final String consortname, final String consortcardnum,
      final String consortempid, final String orderBy, final String orderother,
      final int start, final int pageSize, final int page,
      final SecurityInfo securityInfo,final String loanType,
      final String isRecoverClear, final String recoverClearDateSt, final String recoverClearDateEnd) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new

      HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select distinct p10.contract_id,p10.borrower_name,p10.card_num,p14.house_type,p14.house_totle_price,"
              + " p14.house_area,p14.bargainor_totle_price,p14.bargainor_house_area,p15.loan_money,p15.loan_time_limit,"
              + " p15.loan_month_rate,p15.loan_mode,p11.loan_bank_id,p11.loan_start_date,p11.contract_st,"
              + " nvl((select count(p400.loan_kou_acc) from pl400 p400 where p400.loan_kou_acc = p11.loan_kou_acc"
              + " and p400.status = 0 and p400.reservea_b = 1),0),nvl(p11.overplus_loan_money,0), "
              + " p11.payoffdate"
              + " from pl110 p10,pl114 p14,pl111 p11,pl115 p15,pl120 p20,pl113 p13 "
              + " where p10.contract_id = p14.contract_id "
              + " and p14.contract_id = p11.contract_id "
              + " and p11.contract_id = p15.contract_id "
              + " and p15.contract_id = p13.contract_id(+) "
              + " and p11.contract_id = p20.contract_id "
              + " and p11.contract_st !='1' and p11.is_contract_write!='0' "
              + " and p11.loan_bank_id " + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          String criterion1 = "";
          String sql1 = "select p6.floor_id from pl006 p6";

          if (contactid != null && !"".equals(contactid)) {
            criterion += " p10.contract_id like ? and ";
            parameters.add("%"+contactid+"%");
          }
          if (borrowername != null && !"".equals(borrowername)) {
            criterion += " p10.borrower_name like ? and ";
            parameters.add("%" + borrowername + "%");
          }
          if (empid != null && !"".equals(empid)) {
            criterion += " p10.emp_id like ? and ";
            parameters.add("%" + empid + "%");
          }
          if (cardnum != null && !"".equals(cardnum)) {
            if (cardnum.length() == 15) {
              String cardnum_temp = CardMunChange.get18Id(cardnum);
              criterion += " (p10.card_num like ? or p10.card_num like ? ) and ";
              parameters.add("%" + cardnum + "%");
              parameters.add("%" + cardnum_temp + "%");
            } else if (cardnum.length() == 18) {
              String cardnum_temp = CardMunChange.get15Id(cardnum);
              criterion += " (p10.card_num like ? or p10.card_num like ? ) and ";
              parameters.add("%" + cardnum + "%");
              parameters.add("%" + cardnum_temp + "%");
            }else{
              criterion += " p10.card_num like ? and ";
              parameters.add("%" + cardnum + "%");
            }
          }
          if (consortname != null && !"".equals(consortname)) {
            criterion += " p13.name like ? and ";
            parameters.add("%" + consortname + "%");
          }
          if (consortcardnum != null && !"".equals(consortcardnum)) {
            if (consortcardnum.length() == 15) {
              String consortcardnum_temp = CardMunChange
                  .get18Id(consortcardnum);
              criterion += " (p13.card_num like ? or p13.card_num like ? ) and ";
              parameters.add("%" + consortcardnum + "%");
              parameters.add("%" + consortcardnum_temp + "%");
            } else if (consortcardnum.length() == 18) {
              String consortcardnum_temp = CardMunChange
                  .get15Id(consortcardnum);
              criterion += " (p13.card_num like ? or p13.card_num like ? ) and ";
              parameters.add("%" + consortcardnum + "%");
              parameters.add("%" + consortcardnum_temp + "%");
            }else{
              criterion += " p13.card_num like ? and ";
              parameters.add("%" + consortcardnum + "%");
            }
          }
          if (consortempid != null && !"".equals(consortempid)) {
            criterion += " p13.emp_id like ? and ";
            parameters.add("%" + consortempid + "%");
          }
          if (housetype != null && !"".equals(housetype)) {
            criterion += " p14.house_type= ? and ";
            parameters.add(housetype);
          }
          if (contact_st != null && !"".equals(contact_st)) {
            criterion += " p11.contract_st= ? and ";
            parameters.add(contact_st);
          }
          if (office != null && !"".equals(office)) {
            criterion += " p10.office= ? and ";
            parameters.add(office);
          }
          if (loanbank != null && !"".equals(loanbank)) {
            criterion += " p11.loan_bank_id= ? and ";
            parameters.add(loanbank);
          }
          if (paymood != null && !"".equals(paymood)) {
            criterion += " p15.loan_mode= ? and ";
            parameters.add(paymood);
          }
          if (assistantorgid != null && !"".equals(assistantorgid)) {
            criterion += " p20.assistant_org_id= ? and ";
            parameters.add(assistantorgid);
          }
          System.out.println(headid+"-----------headid");
          if (headid != null && !"".equals(headid)) {
            criterion += " p14.head_id = ? and ";
            criterion1 += " p6.head_id = '" + headid + "' and ";
            parameters.add(headid);
          }
          if (floorName != null && !"".equals(floorName)) {
            criterion1 += " p6.floor_name = '" + floorName + "' and ";
          }
          if (houseAreaSt != null && !"".equals(houseAreaSt)) {
            criterion += " (p14.house_area >= ? or to_number(p14.bargainor_house_area) >= ?) and ";
            parameters.add(houseAreaSt);
            parameters.add(houseAreaSt);
          }
          if (houseAreaEnd != null && !"".equals(houseAreaEnd)) {
            criterion += " (p14.house_area <= ? or to_number(p14.bargainor_house_area) <= ?) and ";
            parameters.add(houseAreaEnd);
            parameters.add(houseAreaEnd);
          }
          if (agreementDateSt != null && !"".equals(agreementDateSt)) {
            criterion += " p11.loan_start_date >= ? and ";
            parameters.add(agreementDateSt);
          }
          if (agreementDateEnd != null && !"".equals(agreementDateEnd)) {
            criterion += " p11.loan_start_date <= ? and ";
            parameters.add(agreementDateEnd);
          }
          if (loanMoneySt != null && !"".equals(loanMoneySt)) {
            criterion += " p11.loan_money >= ? and ";
            parameters.add(loanMoneySt);
          }
          if (loanMoneyEnd != null && !"".equals(loanMoneyEnd)) {
            criterion += " p11.loan_money <= ? and ";
            parameters.add(loanMoneyEnd);
          }
          if (loanTimeLimitSt != null && !"".equals(loanTimeLimitSt)) {
            criterion += " to_number(p11.loan_time_limit) >= ? and ";
            parameters.add(loanTimeLimitSt);
          }
          if (loanTimeLimitEnd != null && !"".equals(loanTimeLimitEnd)) {
            criterion += " to_number(p11.loan_time_limit) <= ? and ";
            parameters.add(loanTimeLimitEnd);
          }
          if (isSignAgreement != null && !"".equals(isSignAgreement)) {
            if (isSignAgreement.equals("0")) {
              criterion += " (select count(p4.loan_kou_acc) from pl400 p4 "
                  + " where p4.loan_kou_acc = p11.loan_kou_acc"
                  + " and p4.status = 0" + " and p4.reservea_b = 1) > 0 and ";
            } else {
              criterion += " (select count(p4.loan_kou_acc) from pl400 p4 "
                  + " where p4.loan_kou_acc = p11.loan_kou_acc"
                  + " and p4.status = 0" + " and p4.reservea_b = 1) = 0 and ";
            }
          }
          if (signAgreementDateStart != null && !"".equals(signAgreementDateStart)) {
            criterion += " p10.contract_id in (select t.contract_id from pl400 t where t.reservea_a >= '"+signAgreementDateStart+"') and ";
          }
          if (signAgreementDateEnd != null && !"".equals(signAgreementDateEnd)) {
            criterion += " p10.contract_id in (select t.contract_id from pl400 t where t.reservea_a <= '"+signAgreementDateEnd+"') and ";
          }
          if (loanType != null && !"".equals(loanType)&& "0".equals(loanType)) {
            criterion += " p10.loantype = 1 and ";
          }
          if (loanType != null && !"".equals(loanType)&& "1".equals(loanType)) {
            criterion += " p10.loantype = 0 and ";
          }
          if (isRecoverClear != null) {
            if(isRecoverClear.equals("0"))
              criterion += " p11.contract_st = 12 and ";
            else if(isRecoverClear.equals("1"))
              criterion += " p11.contract_st <> 12 and ";
          }
          if (recoverClearDateSt != null && !"".equals(recoverClearDateSt)) {
            criterion += " (p11.payoffdate >= ? and p11.contract_st = 12) and ";
            parameters.add(recoverClearDateSt);
          }
          if (recoverClearDateEnd != null && !"".equals(recoverClearDateEnd)) {
            criterion += " (p11.payoffdate <= ? and p11.contract_st = 12) and ";
            parameters.add(recoverClearDateEnd);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          if (criterion1.length() != 0){
            criterion1 = "  where "
                + criterion1.substring(0, criterion1.lastIndexOf("and"));
          }
          String ob = orderBy;
          if (ob == null)
            ob = "p10.contract_id";

          String od = orderother;
          if (od == null)
            od = " DESC";

          if (floorName != null && !"".equals(floorName)) {
            hql=hql+"  and p14.floor_id in ( "+sql1+criterion1 +" )";
          }
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
            LoanapplyInfoDTO loanapplyInfoDTO = new LoanapplyInfoDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              loanapplyInfoDTO.setContactid(obj[0].toString());// 合同编号
            }
            if (obj[1] != null) {
              loanapplyInfoDTO.setBorrowername(obj[1].toString());// 借款人
            }
            if (obj[2] != null) {
              loanapplyInfoDTO.setCardnum(obj[2].toString());// 证件号码
            }
            if (obj[3] != null) {
              loanapplyInfoDTO.setHuosetype(obj[3].toString());// 购房类型
            }
            if (obj[4] != null) {
              loanapplyInfoDTO.setShousetolprice(obj[4].toString());// 房屋总价（商品房）
            }
            if (obj[5] != null) {
              loanapplyInfoDTO.setShousearea(obj[5].toString());// 房屋面积（商品房）
            }
            if (obj[6] != null) {
              loanapplyInfoDTO.setEhousetolprice(obj[6].toString());// 房屋总价（二手房）
            }
            if (obj[7] != null) {
              loanapplyInfoDTO.setEhousearea(obj[7].toString());// 房屋面积（二手房）
            }
            if (obj[8] != null) {
              loanapplyInfoDTO.setLoanmoney(obj[8].toString());// 贷款金额
            }
            if (obj[9] != null) {
              loanapplyInfoDTO.setLoanlimit(obj[9].toString());// 贷款期限
            }
            if (obj[10] != null) {
              loanapplyInfoDTO.setMonthrate(obj[10].toString());// 每月利率
            }
            if (obj[11] != null) {
              loanapplyInfoDTO.setPaymood(obj[11].toString());// 还款方式
            }
            if (obj[12] != null) {
              loanapplyInfoDTO.setLoanBank(obj[12].toString());// 放款银行
            }
            if (obj[13] != null) {
              loanapplyInfoDTO.setAgreementDate(obj[13].toString());// 签订日期
            }
            if (obj[14] != null) {
              loanapplyInfoDTO.setContractSt(obj[14].toString());// 合同状态
            }
            if (obj[15] != null) {
              loanapplyInfoDTO.setIsSignAgreement(obj[15].toString());// 是否签订公积金协议
            }
            if (obj[16] != null) {
              loanapplyInfoDTO.setLoanBalance(obj[16].toString());
            }
            if (obj[17] != null) {
              loanapplyInfoDTO.setClearDate(obj[17].toString());
            }
            templist.add(loanapplyInfoDTO);
          }
          return templist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public Vector findAllLoanapplyInfo(final String contactid,
      final String borrowername, final String empid, final String cardnum,
      final String housetype, final String contact_st, final String office,
      final String loanbank, final String paymood, final String assistantorgid,
      final String headid, final String floorName,
      final String houseAreaSt,
      final String houseAreaEnd, final String agreementDateSt,
      final String agreementDateEnd, final String loanMoneySt,
      final String loanMoneyEnd, final String loanTimeLimitSt,
      final String loanTimeLimitEnd, final String isSignAgreement,
      final String signAgreementDateStart, final String signAgreementDateEnd,
      final SecurityInfo securityInfo, final String consortname,
      final String consortcardnum, final String consortempid,final String loanType,
      final String isRecoverClear, final String recoverClearDateSt, final String recoverClearDateEnd) {

    Vector list = new Vector();
    try {
      list = (Vector) getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select distinct p10.contract_id,p10.borrower_name,p10.card_num,p14.house_type,p14.house_totle_price,"
                  + " p14.house_area,p14.bargainor_totle_price,p14.bargainor_house_area,p15.loan_money,p15.loan_time_limit,"
                  + " p15.loan_month_rate,p15.loan_mode,nvl(p11.overplus_loan_money,0),"
                  + " p11.loan_start_date,p11.contract_st,"
                  + " nvl((select count(p400.loan_kou_acc) from pl400 p400 where p400.loan_kou_acc = p11.loan_kou_acc"
                  + " and p400.status = 0 and p400.reservea_b = 1),0) "
                  + " from pl110 p10,pl114 p14,pl111 p11,pl115 p15,pl120 p20,pl113 p13 "
                  + " where p10.contract_id = p14.contract_id "
                  + " and p14.contract_id = p11.contract_id "
                  + " and p11.contract_id = p15.contract_id "
                  + " and p15.contract_id = p13.contract_id(+) "
                  + " and p11.contract_id = p20.contract_id "
                  + " and p11.contract_st !='1' and p11.is_contract_write !='0' and p11.loan_bank_id "
                  + securityInfo.getDkSecuritySQL();
              Vector parameters = new Vector();
              String criterion = "";
              String criterion1 = "";
              String sql1 = "select p6.floor_id from pl006 p6";
              if (contactid != null && !"".equals(contactid)) {
                criterion += " p10.contract_id like ? and ";
                parameters.add("%"+contactid+"%");
              }
              if (borrowername != null && !"".equals(borrowername)) {
                criterion += " p10.borrower_name like ? and ";
                parameters.add("%" + borrowername + "%");
              }
              if (empid != null && !"".equals(empid)) {
                criterion += " p10.emp_id like ? and ";
                parameters.add("%" + empid + "%");
              }
              if (cardnum != null && !"".equals(cardnum)) {
                if (cardnum.length() == 15) {
                  String cardnum_temp = CardMunChange.get18Id(cardnum);
                  criterion += " (p10.card_num like ? or p10.card_num like ? ) and ";
                  parameters.add("%" + cardnum + "%");
                  parameters.add("%" + cardnum_temp + "%");
                } else if (cardnum.length() == 18) {
                  String cardnum_temp = CardMunChange.get15Id(cardnum);
                  criterion += " (p10.card_num like ? or p10.card_num like ? ) and ";
                  parameters.add("%" + cardnum + "%");
                  parameters.add("%" + cardnum_temp + "%");
                }else{
                  criterion += " p10.card_num like ? and ";
                  parameters.add("%" + cardnum + "%");
                }
              }
              if (consortname != null && !"".equals(consortname)) {
                criterion += " p13.name like ? and ";
                parameters.add("%" + consortname + "%");
              }
              if (consortcardnum != null && !"".equals(consortcardnum)) {
                if (consortcardnum.length() == 15) {
                  String consortcardnum_temp = CardMunChange
                      .get18Id(consortcardnum);
                  criterion += " (p13.card_num like ? or p13.card_num like ? ) and ";
                  parameters.add("%" + consortcardnum + "%");
                  parameters.add("%" + consortcardnum_temp + "%");
                } else if (consortcardnum.length() == 18) {
                  String consortcardnum_temp = CardMunChange
                      .get15Id(consortcardnum);
                  criterion += " (p13.card_num like ? or p13.card_num like ? ) and ";
                  parameters.add("%" + consortcardnum + "%");
                  parameters.add("%" + consortcardnum_temp + "%");
                }else{
                  criterion += " p13.card_num like ? and ";
                  parameters.add("%" + consortcardnum + "%");
                }
              }
              if (consortempid != null && !"".equals(consortempid)) {
                criterion += " p13.emp_id like ? and ";
                parameters.add("%" + consortempid + "%");
              }
              if (housetype != null && !"".equals(housetype)) {
                criterion += " p14.house_type= ? and ";
                parameters.add(housetype);
              }
              if (contact_st != null && !"".equals(contact_st)) {
                criterion += " p11.contract_st= ? and ";
                parameters.add(contact_st);
              }
              if (office != null && !"".equals(office)) {
                criterion += " p10.office= ? and ";
                parameters.add(office);
              }
              if (loanbank != null && !"".equals(loanbank)) {
                criterion += " p11.loan_bank_id= ? and ";
                parameters.add(loanbank);
              }
              if (paymood != null && !"".equals(paymood)) {
                criterion += " p15.loan_mode= ? and ";
                parameters.add(paymood);
              }
              if (assistantorgid != null && !"".equals(assistantorgid)) {
                criterion += " p20.assistant_org_id= ? and ";
                parameters.add(assistantorgid);
              }
              if (headid != null && !"".equals(headid)) {
                criterion += " p14.head_id = ? and ";
                criterion1 += " p6.head_id = '" + headid + "' and ";
                parameters.add(headid);
              }
              if (floorName != null && !"".equals(floorName)) {
                criterion1 += " p6.floor_name = '" + floorName + "' and ";
              }
              if (houseAreaSt != null && !"".equals(houseAreaSt)) {
                criterion += " (p14.house_area >= ? or to_number(p14.bargainor_house_area) >= ?) and ";
                parameters.add(houseAreaSt);
                parameters.add(houseAreaSt);
              }
              if (houseAreaEnd != null && !"".equals(houseAreaEnd)) {
                criterion += " (p14.house_area <= ? or to_number(p14.bargainor_house_area) <= ?) and ";
                parameters.add(houseAreaEnd);
                parameters.add(houseAreaEnd);
              }
              if (agreementDateSt != null && !"".equals(agreementDateSt)) {
                criterion += " p11.loan_start_date >= ? and ";
                parameters.add(agreementDateSt);
              }
              if (agreementDateEnd != null && !"".equals(agreementDateEnd)) {
                criterion += " p11.loan_start_date <= ? and ";
                parameters.add(agreementDateEnd);
              }
              if (loanMoneySt != null && !"".equals(loanMoneySt)) {
                criterion += " p11.loan_money >= ? and ";
                parameters.add(loanMoneySt);
              }
              if (loanMoneyEnd != null && !"".equals(loanMoneyEnd)) {
                criterion += " p11.loan_money <= ? and ";
                parameters.add(loanMoneyEnd);
              }
              if (loanTimeLimitSt != null && !"".equals(loanTimeLimitSt)) {
                criterion += " to_number(p11.loan_time_limit) >= ? and ";
                parameters.add(loanTimeLimitSt);
              }
              if (loanTimeLimitEnd != null && !"".equals(loanTimeLimitEnd)) {
                criterion += " to_number(p11.loan_time_limit) <= ? and ";
                parameters.add(loanTimeLimitEnd);
              }
              if (isSignAgreement != null && !"".equals(isSignAgreement)) {
                if (isSignAgreement.equals("0")) {
                  criterion += " (select count(p4.loan_kou_acc) from pl400 p4 "
                      + " where p4.loan_kou_acc = p11.loan_kou_acc"
                      + " and p4.status = 0"
                      + " and p4.reservea_b = 1) > 0 and ";
                } else {
                  criterion += " (select count(p4.loan_kou_acc) from pl400 p4 "
                      + " where p4.loan_kou_acc = p11.loan_kou_acc"
                      + " and p4.status = 0"
                      + " and p4.reservea_b = 1) = 0 and ";
                }
              }
              if (signAgreementDateStart != null && !"".equals(signAgreementDateStart)) {
                criterion += " p10.contract_id in (select t.contract_id from pl400 t where t.reservea_a >= '"+signAgreementDateStart+"') and ";
              }
              if (signAgreementDateEnd != null && !"".equals(signAgreementDateEnd)) {
                criterion += " p10.contract_id in (select t.contract_id from pl400 t where t.reservea_a <= '"+signAgreementDateEnd+"') and ";
              }
              if (loanType != null && !"".equals(loanType)&& "0".equals(loanType)) {
                criterion += " p10.loantype = 1 and ";
              }
              if (loanType != null && !"".equals(loanType)&& "1".equals(loanType)) {
                criterion += " p10.loantype = 0 and ";
              }
              if (isRecoverClear != null) {
                if(isRecoverClear.equals("0"))
                  criterion += " p11.contract_st = 12 and ";
                else if(isRecoverClear.equals("1"))
                  criterion += " p11.contract_st <> 12 and ";
              }
              if (recoverClearDateSt != null && !"".equals(recoverClearDateSt)) {
                criterion += " (p11.payoffdate >= ? and p11.contract_st = 12) and ";
                parameters.add(recoverClearDateSt);
              }
              if (recoverClearDateEnd != null && !"".equals(recoverClearDateEnd)) {
                criterion += " (p11.payoffdate <= ? and p11.contract_st = 12) and ";
                parameters.add(recoverClearDateEnd);
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              if (criterion1.length() != 0){
                criterion1 = "  where "
                    + criterion1.substring(0, criterion1.lastIndexOf("and"));
              }
              if (floorName != null && !"".equals(floorName)) {
                hql=hql+"  and p14.floor_id in ( "+sql1+criterion1 +" )";
              }
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              Vector templist = new Vector();

              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                LoanapplyInfoDTO loanapplyInfoDTO = new LoanapplyInfoDTO();
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanapplyInfoDTO.setContactid(obj[0].toString());// 合同编号
                }
                if (obj[1] != null) {
                  loanapplyInfoDTO.setBorrowername(obj[1].toString());// 借款人
                }
                if (obj[2] != null) {
                  loanapplyInfoDTO.setCardnum(obj[2].toString());// 证件号码
                }
                if (obj[3] != null) {
                  loanapplyInfoDTO.setHuosetype(obj[3].toString());// 购房类型
                }
                if (obj[4] != null) {
                  loanapplyInfoDTO.setShousetolprice(obj[4].toString());// 房屋总价（商品房）
                }
                if (obj[5] != null) {
                  loanapplyInfoDTO.setShousearea(obj[5].toString());// 房屋面积（商品房）
                }
                if (obj[6] != null) {
                  loanapplyInfoDTO.setEhousetolprice(obj[6].toString());// 房屋总价（二手房）
                }
                if (obj[7] != null) {
                  loanapplyInfoDTO.setEhousearea(obj[7].toString());// 房屋面积（二手房）
                }
                if (obj[8] != null) {
                  loanapplyInfoDTO.setLoanmoney(obj[8].toString());// 贷款金额
                }
                if (obj[9] != null) {
                  loanapplyInfoDTO.setLoanlimit(obj[9].toString());// 贷款期限
                }
                if (obj[10] != null) {
                  loanapplyInfoDTO.setMonthrate(obj[10].toString());// 每月利率
                }
                if (obj[11] != null) {
                  loanapplyInfoDTO.setPaymood(obj[11].toString());// 还款方式
                }
                if (obj[12] != null) {
                  loanapplyInfoDTO.setLoanBalance(obj[12].toString());// 贷款余额
                }
                if (obj[13] != null) {
                  loanapplyInfoDTO.setAgreementDate(obj[13].toString());// 公积金签订日期
                }
                if (obj[14] != null) {
                  loanapplyInfoDTO.setContractSt(obj[14].toString());// 合同状态
                }
                if (obj[15] != null) {
                  loanapplyInfoDTO.setIsSignAgreement(obj[15].toString());// 是否签订公积金协议
                }
                templist.add(loanapplyInfoDTO);
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
   * hanl 查询PL110字段，
   * 
   * @param id
   * @return
   */
  public EndorsecontractTaDTO queryBorrowerInfoHl(final String id) {
    EndorsecontractTaDTO endorsecontractTaDTO = null;
    try {
      endorsecontractTaDTO = (EndorsecontractTaDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              // TODO Auto-generated method stub

              String hql = "select t.borrower_name,t.card_kind,t.card_num,t.office from pl110 t where t.contract_id = '"
                  + id + "'";

              Query query = session.createSQLQuery(hql);

              EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                endorsecontractTaDTO.setDebitter(obj[0].toString());// 借款人
                endorsecontractTaDTO.setCertificateType(obj[1].toString());// 证件类型
                endorsecontractTaDTO.setCertificateNum(obj[2].toString());// 证件号码
                endorsecontractTaDTO.setOffice(obj[3].toString());
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
   * hanl 查询有没有辅助借款人
   * 
   * @param empid
   * @return
   * @throws Exception
   */
  public String countPeopleNum(final String contractid) {

    String num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(p.auxiliary_id) from pl113 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);

            return query.uniqueResult().toString();
          }
        });

    return num;
  }

  /**
   * hanl 查询有没有辅助借款人
   * 
   * @param empid
   * @return
   * @throws Exception
   */
  public String countPeopleNum_Age(final String contractid) {

    String num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.age from pl113 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);

            return query.uniqueResult().toString();
          }
        });

    return num;
  }

  /**
   * hanl 查询有没有辅助借款人
   * 
   * @param empid
   * @return
   * @throws Exception
   */
  public String countPeopleNum_EmpId(final String contractid) {

    String num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.emp_id from pl113 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            } else {
              return "";
            }
          }
        });

    return num;
  }

  /**
   * hanl 查询有没有辅助借款人
   * 
   * @param empid
   * @return
   * @throws Exception
   */
  public String countPeopleNum_Id(final String contractid) {

    String num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.org_id from pl113 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);

            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            } else {
              return "";
            }
          }
        });

    return num;
  }

  /**
   * hanl 查询有没有辅助借款人
   * 
   * @param empid
   * @return
   * @throws Exception
   */
  public String countPeopleNum_Sex(final String contractid) {

    String num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.sex from pl113 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);

            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            } else {
              return "";
            }
          }
        });

    return num;
  }

  /*
   * 保证人辅助借款人列表——1
   */
  public List findAssureborrowerqueryList_wsh(final String empid,
      final String empName, final String contractId, final String loanKouAcc,
      final String orgId, final String orgName,
      final SecurityInfo securityInfo, final String orderBy,
      final String orderother, final int start, final int pageSize,
      final int page) {

    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new

      HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.org_id as a," + " ba001.name as b,"
              + "t.id as c," + " ba002.name as d,"
              + " t.pre_balance + t.cur_balance as e, " + "pl210.type as f, "
              + " pl110.emp_id as g, " + "pl110.borrower_name as h, "
              + " pl110.contract_id  as i," + " pl111.loan_kou_acc  as j  "
              + "from aa002 t join" + " ba002 on ba002.id = t.emp_info_id "
              + "join aa001 on t.org_id = aa001.id "
              + "join ba001 on aa001.orginfo_id = ba001.id "
              + "join pl210 on t.id = pl210.emp_id "
              + "join pl110 on pl110.contract_id = pl210.contract_id "
              + "join pl111 on pl111.contract_id = pl110.contract_id ";
          // + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (empid != null && !"".equals(empid)) {
            criterion += " t.id= ? and ";
            parameters.add(empid);
          }
          if (empName != null && !"".equals(empName)) {
            criterion += " ba002.name like ? and ";
            parameters.add("%" + empName + "%");
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " pl111.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " pl111.loan_kou_acc= ? and ";
            parameters.add(loanKouAcc);
          }
          if (orgId != null && !"".equals(orgId)) {
            criterion += " aa001.id= ? and ";
            parameters.add(new Integer(orgId));
          }
          if (orgName != null && !"".equals(orgName)) {
            criterion += " ba001.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (criterion.length() != 0) {
            criterion = " where  "
                + criterion.substring(0, criterion.lastIndexOf("and"))
                + " and pl111.contract_st = '11' "
                + "and t.pre_integral + t.cur_integral > 0 and pl210.type > 1 and pl111.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          } else {
            criterion = " where  "
                + "  pl111.contract_st = '11' "
                + "and t.pre_integral + t.cur_integral > 0 and pl210.type > 1 and pl111.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          }

          hql = hql + criterion;
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
            AssureborrowerqueryDTO assureborrowerqueryDTO = new AssureborrowerqueryDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              assureborrowerqueryDTO.setOrgId(obj[0].toString());// 合同编号
            }
            if (obj[1] != null) {
              assureborrowerqueryDTO.setOtherBorrowerOrgName(obj[1].toString());// 借款人
            }
            if (obj[2] != null) {
              assureborrowerqueryDTO.setOtherBorrowerEmpId(obj[2].toString());// 借款人姓名
            }
            if (obj[3] != null) {
              assureborrowerqueryDTO.setOtherBorrowerName(obj[3].toString());// 借款人职工编号
            }
            if (obj[4] != null) {
              assureborrowerqueryDTO.setLoanMoney(obj[4].toString());// 借款人单位
            }
            if (obj[5] != null) {
              assureborrowerqueryDTO.setType(obj[5].toString());// 借款人证件号码
            }
            if (obj[6] != null) {
              assureborrowerqueryDTO.setBorrowerEmpId(obj[6].toString());// 辅助借款人姓名
            }
            if (obj[7] != null) {
              assureborrowerqueryDTO.setBorrowerName(obj[7].toString());// 辅助借款人职工编号
            }
            if (obj[8] != null) {
              assureborrowerqueryDTO.setContractId(obj[8].toString());// 辅助借款人单位名称
            }
            if (obj[9] != null) {
              assureborrowerqueryDTO.setLoanKouAcc(obj[9].toString());// 贷款金额
            }

            templist.add(assureborrowerqueryDTO);
          }
          return templist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 保证人辅助借款人列表——1
   */
  public List findAssureborrowerqueryCountList_wsh(final String empid,
      final String empName, final String contractId, final String loanKouAcc,
      final String orgId, final String orgName,
      final SecurityInfo securityInfo, final String orderBy,
      final String orderother, final int start, final int pageSize,
      final int page) {

    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new

      HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.org_id as a," + " ba001.name as b,"
              + "t.id as c," + " ba002.name as d,"
              + " t.pre_balance + t.cur_balance as e, " + "pl210.type as f, "
              + " pl110.emp_id as g, " + "pl110.borrower_name as h, "
              + " pl110.contract_id  as i," + " pl111.loan_kou_acc  as j  "
              + "from aa002 t join" + " ba002 on ba002.id = t.emp_info_id "
              + "join aa001 on t.org_id = aa001.id "
              + "join ba001 on aa001.orginfo_id = ba001.id "
              + "join pl210 on t.id = pl210.emp_id "
              + "join pl110 on pl110.contract_id = pl210.contract_id "
              + "join pl111 on pl111.contract_id = pl110.contract_id ";
          // + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (empid != null && !"".equals(empid)) {
            criterion += " t.id= ? and ";
            parameters.add(empid);
          }
          if (empName != null && !"".equals(empName)) {
            criterion += " ba002.name like ? and ";
            parameters.add("%" + empName + "%");
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " pl111.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " pl111.loan_kou_acc= ? and ";
            parameters.add(loanKouAcc);
          }
          if (orgId != null && !"".equals(orgId)) {
            criterion += " aa001.id= ? and ";
            parameters.add(new Integer(orgId));
          }
          if (orgName != null && !"".equals(orgName)) {
            criterion += " ba001.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (criterion.length() != 0) {
            criterion = " where  "
                + criterion.substring(0, criterion.lastIndexOf("and"))
                + " and pl111.contract_st = '11' "
                + "and t.pre_integral + t.cur_integral > 0 and pl210.type > 1 and pl111.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          } else {
            criterion = " where  "
                + "  pl111.contract_st = '11' "
                + "and t.pre_integral + t.cur_integral > 0 and pl210.type > 1 and pl111.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          }

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
   * 保证人辅助借款人列表——1
   */
  public List findAssureborrowerqueryPrintList_wsh(final String empid,
      final String empName, final String contractId, final String loanKouAcc,
      final String orgId, final String orgName,
      final SecurityInfo securityInfo, final String orderBy,
      final String orderother, final int start, final int pageSize,
      final int page) {

    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new

      HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.org_id as a," + " ba001.name as b,"
              + "t.id as c," + " ba002.name as d,"
              + " t.pre_balance + t.cur_balance as e, " + "pl210.type as f, "
              + " pl110.emp_id as g, " + "pl110.borrower_name as h, "
              + " pl110.contract_id  as i," + " pl111.loan_kou_acc  as j  "
              + "from aa002 t join" + " ba002 on ba002.id = t.emp_info_id "
              + "join aa001 on t.org_id = aa001.id "
              + "join ba001 on aa001.orginfo_id = ba001.id "
              + "join pl210 on t.id = pl210.emp_id "
              + "join pl110 on pl110.contract_id = pl210.contract_id "
              + "join pl111 on pl111.contract_id = pl110.contract_id ";
          // + securityInfo.getDkSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (empid != null && !"".equals(empid)) {
            criterion += " t.id= ? and ";
            parameters.add(empid);
          }
          if (empName != null && !"".equals(empName)) {
            criterion += " ba002.name like ? and ";
            parameters.add("%" + empName + "%");
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " pl111.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (loanKouAcc != null && !"".equals(loanKouAcc)) {
            criterion += " pl111.loan_kou_acc= ? and ";
            parameters.add(loanKouAcc);
          }
          if (orgId != null && !"".equals(orgId)) {
            criterion += " aa001.id= ? and ";
            parameters.add(new Integer(orgId));
          }
          if (orgName != null && !"".equals(orgName)) {
            criterion += " ba001.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (criterion.length() != 0) {
            criterion = " where  "
                + criterion.substring(0, criterion.lastIndexOf("and"))
                + " and pl111.contract_st = '11' "
                + "and t.pre_integral + t.cur_integral > 0 and pl210.type > 1 and pl111.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          } else {
            criterion = " where  "
                + "  pl111.contract_st = '11' "
                + "and t.pre_integral + t.cur_integral > 0 and pl210.type > 1 and pl111.loan_bank_id "
                + securityInfo.getDkSecuritySQL();
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            AssureborrowerqueryDTO assureborrowerqueryDTO = new AssureborrowerqueryDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              assureborrowerqueryDTO.setOrgId(obj[0].toString());// 合同编号
            }
            if (obj[1] != null) {
              assureborrowerqueryDTO.setOtherBorrowerOrgName(obj[1].toString());// 借款人
            }
            if (obj[2] != null) {
              assureborrowerqueryDTO.setOtherBorrowerEmpId(obj[2].toString());// 借款人姓名
            }
            if (obj[3] != null) {
              assureborrowerqueryDTO.setOtherBorrowerName(obj[3].toString());// 借款人职工编号
            }
            if (obj[4] != null) {
              assureborrowerqueryDTO.setLoanMoney(obj[4].toString());// 借款人单位
            }
            if (obj[5] != null) {
              assureborrowerqueryDTO.setType(obj[5].toString());// 借款人证件号码
            }
            if (obj[6] != null) {
              assureborrowerqueryDTO.setBorrowerEmpId(obj[6].toString());// 辅助借款人姓名
            }
            if (obj[7] != null) {
              assureborrowerqueryDTO.setBorrowerName(obj[7].toString());// 辅助借款人职工编号
            }
            if (obj[8] != null) {
              assureborrowerqueryDTO.setContractId(obj[8].toString());// 辅助借款人单位名称
            }
            if (obj[9] != null) {
              assureborrowerqueryDTO.setLoanKouAcc(obj[9].toString());// 贷款金额
            }

            templist.add(assureborrowerqueryDTO);
          }
          return templist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryListByCondition_wsh(final String contractId,
      final String borrowerName, final String cardNum, final String empId,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = " select p110.contract_id,p110.borrower_name,p110.card_num,p110.org_id,p110.org_name,p110.emp_id,"
              + "p111.contract_st from pl110 p110,pl111 p111 where p110.contract_id=p111.contract_id ";
          // " p110.operator "
          // + securityInfo.getDkUserSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" + contractId + "%");
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " (p110.card_num like ? or p110.standby_card_num like ? ) and ";
            parameters.add("%" + cardNum + "%");
            parameters.add("%" + cardNum + "%");
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " p110.emp_id like ? and ";
            parameters.add("%" + empId + "%");
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
   * 职工弹出框列表---wsh 操作员权限
   * 
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param empId
   * @param empSt
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryListByCondition_wsh_print(final String contractId,
      final String contractIdEnd, final String borrowerName,
      final String cardNum, final String empId, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = " select p110.contract_id,p110.borrower_name,p110.card_num,p110.org_id,p110.org_name,p110.emp_id,"
              + "p111.contract_st from pl110 p110,pl111 p111 where p110.contract_id=p111.contract_id ";
          // " p110.operator "
          // + securityInfo.getDkUserSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id >= ? and ";
            parameters.add(contractId);
          }
          if (contractIdEnd != null && !"".equals(contractIdEnd)) {
            criterion += " p110.contract_id <= ? and ";
            parameters.add(contractIdEnd);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " (p110.card_num like ? or p110.standby_card_num like ? ) and ";
            parameters.add("%" + cardNum + "%");
            parameters.add("%" + cardNum + "%");
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " p110.emp_id like ? and ";
            parameters.add("%" + empId + "%");
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

  // 根据借款人职工编号查询贷款人信息
  public Borrower queryByBorrowerByEmpId(final String empId) {
    Borrower borrower = new Borrower();
    try {
      borrower = (Borrower) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select borrower from Borrower borrower,BorrowerAcc  borrowerAcc  ";
              Vector parameters = new Vector();
              String criterion = "";

              if (empId != null) {
                criterion += "  borrower.empId=?  and borrowerAcc.contractId=borrower.contractId and borrowerAcc.contractSt='11' and  ";
                parameters.add(new BigDecimal(empId));
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf(" and "));
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
    return borrower;
  }

  public EmpinfoDto queryEmpInfo(final String orgid, final String empId) {
    EmpinfoDto borrower = null;
    try {
      borrower = (EmpinfoDto) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select a.name as empname,a.card_num,d.name as orgname ,d.tel,d.address,c.name as officename,t.salary_base,t.emp_pay + t.org_pay as allpay,"
                  + "t.pre_balance + t.cur_balance as balance ,t.pay_status,substr(a.opendate,0,6),t.emp_pay_month,nvl(t.pay_oldyear,0) as allpay_t from aa002 t, ba002 a, aa001 b, ba001 d, bb101 c"
                  + " where t.emp_info_id = a.id and b.orginfo_id = d.id and t.org_id = b.id and d.officecode = c.id  and ";
              Vector parameters = new Vector();
              String criterion = "";

              if (empId != null) {
                criterion += " t.id=? and ";
                System.out.println("s.."+empId);
                parameters.add(new BigDecimal(empId));
              }
              if (orgid != null && orgid != "") {
                criterion += " t.org_id=? and ";
                parameters.add(new BigDecimal(orgid));
              }
              if (criterion.length() != 0)
                criterion = criterion.substring(0, criterion
                    .lastIndexOf(" and "));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              EmpinfoDto infodto = null;
              while (iterate.hasNext()) {
                infodto = new EmpinfoDto();
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  infodto.setEmp_name(obj[0].toString());
                }
                if (obj[1] != null) {
                  infodto.setEmp_card_num(obj[1].toString());
                }
                if (obj[2] != null) {
                  infodto.setOrg_name(obj[2].toString());
                }
                if (obj[3] != null) {
                  infodto.setOrg_tel(obj[3].toString());
                }
                if (obj[4] != null) {
                  infodto.setOrg_adder(obj[4].toString());
                }
                if (obj[5] != null) {
                  infodto.setOffice(obj[5].toString());
                }
                if (obj[6] != null) {
                  infodto.setMonth_sal(new BigDecimal(obj[6].toString()));
                }
                if (obj[7] != null) {
                  infodto.setMonth_pay(new BigDecimal(obj[7].toString()));
                }
                if (obj[8] != null) {
                  infodto.setAblence(new BigDecimal(obj[8].toString()));
                }
                if (obj[9] != null) {
                  infodto.setEmp_status(obj[9].toString());
                  // System.out.println("Emp_status=="+infodto.getEmp_status());
                  if (infodto.getEmp_status().toString().equals("1")) {
                    infodto.setEmp_status("正常");
                  }
                  if (infodto.getEmp_status().toString().equals("2")) {
                    infodto.setEmp_status("封存");
                  }
                  if (infodto.getEmp_status().toString().equals("3")) {
                    infodto.setEmp_status("销户");
                  }
                  if (infodto.getEmp_status().toString().equals("4")) {
                    infodto.setEmp_status("调出");
                  }
                  if (infodto.getEmp_status().toString().equals("5")) {
                    infodto.setEmp_status("删除");
                  }
                }
                if (obj[10] != null) {
                  infodto.setFirst_pay(obj[10].toString());
                }
                if (obj[11] != null) {
                  infodto.setNow_pay(obj[11].toString());
                }
                if (obj[12] != null && obj[12].toString() != "0") {

                  infodto.setMonth_pay(new BigDecimal(obj[12].toString()));
                }
              }

              return infodto;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return borrower;
  }

  public EmpinfoDto queryEmpInfo_s(final String orgid, final String empId) {
    EmpinfoDto borrower = null;
    try {
      borrower = (EmpinfoDto) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select a.name as empname,a.card_num,d.name as orgname ,d.tel,d.address,c.name as officename,t.salary_base,t.emp_pay + t.org_pay as allpay,"
                  + "t.pre_balance + t.cur_balance as balance ,t.pay_status,substr(a.opendate,0,6),t.emp_pay_month,nvl(t.pay_oldyear,0) as allpay_t from aa002 t, ba002 a, aa001 b, ba001 d, bb101 c"
                  + " where t.emp_info_id = a.id and b.orginfo_id = d.id and t.org_id = b.id and d.officecode = c.id  and ";
              Vector parameters = new Vector();
              String criterion = "";

              if (empId != null) {
                criterion += " t.id=? and ";
                parameters.add(new BigDecimal(empId));
              }
              if (orgid != null && orgid != "") {
                criterion += " t.org_id=? and ";
                parameters.add(new BigDecimal(orgid));
              }
              if (criterion.length() != 0)
                criterion = criterion.substring(0, criterion
                    .lastIndexOf(" and "));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              EmpinfoDto infodto = null;
              while (iterate.hasNext()) {
                infodto = new EmpinfoDto();
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  infodto.setEmp_name_s(obj[0].toString());
                }
                if (obj[1] != null) {
                  infodto.setEmp_card_num_s(obj[1].toString());
                }
                if (obj[2] != null) {
                  infodto.setOrg_name_s(obj[2].toString());
                }
                if (obj[3] != null) {
                  infodto.setOrg_tel_s(obj[3].toString());
                }
                if (obj[4] != null) {
                  infodto.setOrg_adder_s(obj[4].toString());
                }
                if (obj[5] != null) {
                  infodto.setOffice_s(obj[5].toString());
                }
                if (obj[6] != null) {
                  infodto.setMonth_sal_s(new BigDecimal(obj[6].toString()));
                }
                if (obj[7] != null) {
                  infodto.setMonth_pay_s(new BigDecimal(obj[7].toString()));
                }
                if (obj[8] != null) {
                  infodto.setAblence_s(new BigDecimal(obj[8].toString()));
                }
                if (obj[9] != null) {
                  infodto.setEmp_status_s(obj[9].toString());
                  // System.out.println("Emp_status=="+infodto.getEmp_status());
                  if (infodto.getEmp_status_s().toString().equals("1")) {
                    infodto.setEmp_status_s("正常");
                  }
                  if (infodto.getEmp_status_s().toString().equals("2")) {
                    infodto.setEmp_status_s("封存");
                  }
                  if (infodto.getEmp_status_s().toString().equals("3")) {
                    infodto.setEmp_status_s("销户");
                  }
                  if (infodto.getEmp_status_s().toString().equals("4")) {
                    infodto.setEmp_status_s("调出");
                  }
                  if (infodto.getEmp_status_s().toString().equals("5")) {
                    infodto.setEmp_status_s("删除");
                  }
                }
                if (obj[10] != null) {
                  infodto.setFirst_pay_s(obj[10].toString());
                }
                if (obj[11] != null) {
                  infodto.setNow_pay_s(obj[11].toString());
                }
                if (obj[12] != null && obj[12].toString() != "0") {

                  infodto.setMonth_pay_s(new BigDecimal(obj[12].toString()));
                }
              }

              return infodto;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return borrower;
  }

  /**
   * 查询审核贷款列表
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
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   */
  // wuht
  public List queryBorrowerListByCriterions_wuht(final String contractId,
      final String officeCode, final String borrowerName, final String cardNum,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,
      final String contractStFind, final String contractSt,
      final String isContractWrite, final String type, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo,
      final String beginBackDate, final String endBackDate) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p110.contract_id,p110.borrower_name,p110.card_num,"
              + " case when p111.contract_st between 11 and 13 then p111.loan_money else p115.loan_money end loanmoney,"
              + " p115.loan_time_limit,p111.loan_bank_id,p110.org_name,"
              + " case when p114.house_type='01' then to_char(p114.house_area) else p114.bargainor_house_area end,"
              + " p114.house_type,p111.contract_st,"
              + " p114.bargainor_house_area ,"
              + " case when p114.house_type='01' then p114.house_totle_price else p114.bargainor_totle_price end,"
              + " p114.house_totle_price, "
              + " case when p114.house_type='01' then p114.house_addr else p114.bargainor_house_addr end, "
              + " p114.bargainor_house_addr, p114.remark , "
              + " (select a.name from pl113 a where a.auxiliary_id=(select max(p113.auxiliary_id) "
              + " from pl113 p113 "
              + " where p113.contract_id = p110.contract_id)),"
              + " p110.operator,"
              + " p110.re_date,"
              + " p114.photo_url,p111.is_contract_write,p110.loanvipcheck_date "
              + " from pl110 p110,pl111 p111,pl114 p114,pl115 p115"
              + " where p110.contract_id=p111.contract_id"
              + " and p110.contract_id=p115.contract_id"
              + " and p111.contract_id=p114.contract_id ";

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" +contractId + "%");
          }
          if (officeCode != null && !"".equals(officeCode)) {
            criterion += " p110.office = ? and ";
            parameters.add(officeCode);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
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
          if (beginBackDate != null && !"".equals(beginBackDate)) {
            criterion += " p110.re_date >= ? and ";
            parameters.add(beginBackDate);
          }

          if (endBackDate != null && !"".equals(endBackDate)) {
            criterion += " p110.re_date <= ? and ";
            parameters.add(endBackDate);
          }
          if (contractStFind != null && !"".equals(contractStFind)) {
            if(!contractStFind.equals("1")){
              criterion += " p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL()+" and ";
            }
            criterion += " p111.contract_st = ? and ";
            parameters.add(contractStFind);
          }else{
            criterion += " p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL()+" and ";
          }

          if (isContractWrite != null && !"".equals(isContractWrite)) {
            if(contractStFind != null && !"".equals(contractStFind) && !contractStFind.equals("1")){
              criterion += " p111.is_contract_write = ? and ";
              parameters.add(isContractWrite);
            }
          }

          if (type == null || "".equals(type)) {
            criterion += " p111.contract_st in (" + contractSt + ") and ";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p110.contract_id ";

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

  public List queryBorrowerListByCriterionsAll_wuht(final String contractId,
      final String officeCode, final String borrowerName, final String cardNum,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,
      final String contractStFind, final String contractSt,
      final String isContractWrite, final String type, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo,
      final String beginBackDate, final String endBackDate) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p110.contract_id,p110.borrower_name,p110.card_num,"
              + " case when p111.contract_st between 11 and 13 then p111.loan_money else p115.loan_money end loanmoney,"
              + " p115.loan_time_limit,p111.loan_bank_id,p110.org_name,"
              + " case when p114.house_type='01' then to_char(p114.house_area) else p114.bargainor_house_area end,"
              + " p114.house_type,p111.contract_st,p114.bargainor_house_area ,"
              + " case when p114.house_type='01' then p114.house_totle_price else p114.bargainor_totle_price end,"
              + " p114.house_totle_price,"
              + " case when p114.house_type='01' then p114.house_addr else  p114.bargainor_house_addr end, "
              + " p114.bargainor_house_addr, p114.remark ,"
              + " p005.developer_name,"
              + " p110.operator,"
              + " (select distinct bb101.name from bb101 bb101 where bb101.id = p110.office) "
              + " from pl110 p110,pl111 p111,pl114 p114,pl115 p115,pl005 p005 "
              + " where p110.contract_id=p111.contract_id "
              + " and p110.contract_id=p115.contract_id "
              + " and p111.contract_id=p114.contract_id "
              + " and p005.id(+)=p114.head_id ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id like ? and ";
            parameters.add("%" +contractId + "%");
          }

          if (officeCode != null && !"".equals(officeCode)) {
            criterion += " p110.office = ? and ";
            parameters.add(officeCode);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name like ? and ";
            parameters.add("%" + borrowerName + "%");
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
            criterion += " p110.op_time <= ? and ";
            parameters.add(endBizDate);
          }
          if (beginBackDate != null && !"".equals(beginBackDate)) {
            criterion += " p110.re_date >= ? and ";
            parameters.add(beginBackDate);
          }

          if (endBackDate != null && !"".equals(endBackDate)) {
            criterion += " p110.re_date <= ? and ";
            parameters.add(endBackDate);
          }
          if (contractStFind != null && !"".equals(contractStFind)) {
            if(!contractStFind.equals("1")){
              criterion += " p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL()+" and ";
            }
            criterion += " p111.contract_st = ? and ";
            parameters.add(contractStFind);
          }else{
            criterion += " p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL()+" and ";
          }

          if (isContractWrite != null && !"".equals(isContractWrite)) {
            if(contractStFind != null && !"".equals(contractStFind) && !contractStFind.equals("1")){
              criterion += " p111.is_contract_write = ? and ";
              parameters.add(isContractWrite);
            }
          }

          if (type == null || "".equals(type)) {
            criterion += " p111.contract_st in (" + contractSt + ") and ";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p110.contract_id ";

          String od = order;
          if (od == null)
            od = " DESC";

          hql = hql + criterion + " order by " + ob + " " + od;

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

  public List queryBorrowerListByCriterionsSum_wuht(final String officeCode,
      final int start, final String orderBy, final String order,
      final int pageSize, final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p110.contract_id,p110.borrower_name,p110.card_num,"
              + " case when p111.contract_st between 11 and 13 then p111.loan_money else p115.loan_money end loanmoney,"
              + "p115.loan_time_limit,p111.loan_bank_id,p110.org_name,"
              + "p114.house_area,p114.house_type,p111.contract_st,p114.bargainor_house_area ,"
              + "p114.bargainor_totle_price, p114.house_totle_price, p114.house_addr,  p114.bargainor_house_addr, p114.remark "
              + "from pl110 p110,pl111 p111,pl114 p114,pl115 p115 "
              + "where "
              + "p110.contract_id=p111.contract_id "
              + "and "
              + "p110.contract_id=p115.contract_id "
              + "and "
              + "p111.contract_id=p114.contract_id "
              + "and p110.office "
              + securityInfo.getOfficeSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null && !"".equals(officeCode)) {
            criterion += " p110.office = ? and ";
            parameters.add(officeCode);
          }
          if (bizDate != null && !"".equals(bizDate)) {
            criterion += " ((p111.loan_start_date is null and p111.contract_st>=3)"
                + " or (substr(p111.loan_start_date, 0, 4) = ?)) and ";
            parameters.add(bizDate.substring(0, 4));
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " p110.contract_id ";

          String od = order;
          if (od == null)
            od = " DESC";

          hql = hql + criterion + " order by " + ob + " " + od;

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

  // 办事处，银行，发放日期，还清日期，合同编号，姓名，身份证号，
  public List queryBorrowerListByCriterions(final SecurityInfo securityInfo,
      final String offic, final String loanBankName, final String borrowerName,
      final String contractId, final String cardNum,
      final String loanStartDate, final String loanEndDate,
      final String loanPayOffDate, final String loanPayOffEndDate,
      final int start, final String orderBy, final String order,
      final int pageSize, final int page,final String houseType ) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pl111.loan_kou_acc,pl111.contract_id,pl110.borrower_name,"
              + "pl111.loan_money,pl111.loan_time_limit,sum(pl203.real_interest + pl203.real_punish_interest),"
              + "sum(pl203.real_corpus + pl203.real_interest + pl203.real_punish_interest),pl111.loan_start_date,"
              + "(select m.a from (select p.loan_start_date,p.loan_time_limit,add_months(to_date(p.LOAN_START_DATE, 'yyyymmdd'),"
              + "p.loan_time_limit) as a,p.contract_id as b from pl111 p ) m where m.b = pl111.contract_id) as dqrq,"
              + "pl111.PAYOFFDATE as huankuanriqi"
              + " from pl110 , pl111 ,pl114, pl203 where pl110.contract_id = pl111.contract_id and pl114.contract_id=pl110.contract_id "
              + "and pl111.loan_kou_acc = pl203.loan_kou_acc and pl111.contract_st='12'"
              + " and   pl111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          String criterion = "";
          Vector parameters = new Vector();
          if (offic != null && !"".equals(offic)) {
            criterion += " pl110.office=? and ";
            parameters.add(offic);
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " pl111.loan_bank_id =? and ";
            parameters.add(loanBankName);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " pl110.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " pl110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " pl110.card_num = ? and ";
            parameters.add(cardNum);
          }
          if (loanPayOffDate != null && !"".equals(loanPayOffDate)) {
            criterion += " pl111.PAYOFFDATE >=? and ";
            parameters.add(loanPayOffDate);
          }
          if (loanPayOffEndDate != null && !"".equals(loanPayOffEndDate)) {
            criterion += " pl111.PAYOFFDATE <=? and ";
            parameters.add(loanPayOffEndDate);
          }
          if (loanStartDate != null && !"".equals(loanStartDate)) {
            criterion += " pl111.loan_start_date >= ? and ";
            parameters.add(loanStartDate);
          }
          if (loanEndDate != null && !"".equals(loanEndDate)) {
            criterion += " pl111.loan_start_date <= ? and ";
            parameters.add(loanEndDate);
          }
          if (houseType != null && !"".equals(houseType)) {
            criterion += " pl114.house_type = ? and ";
            parameters.add(houseType);
          }
          if (criterion.length() != 0)
            criterion = " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " pl110.contract_id ";

          String od = order;
          if (od == null)
            od = " DESC";

          hql = hql
              + criterion
              + " group by pl111.loan_kou_acc,pl111.contract_id,"
              + "pl110.borrower_name,pl111.loan_money,pl111.loan_time_limit,pl111.loan_start_date,pl111.PAYOFFDATE "
              + " order by " + ob + " " + od;
          ;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          List queryList = query.list();
          List printList = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            QueryPayOffRecordsTaListDTO queryPayOffRecordsTaListDTO = new QueryPayOffRecordsTaListDTO();
            obj = (Object[]) iterate.next();

            if (obj[0] != null) {
              queryPayOffRecordsTaListDTO.setLoanKouAcc(obj[0].toString());

            }
            if (obj[1] != null) {
              queryPayOffRecordsTaListDTO.setContractId(obj[1].toString());
            }
            if (obj[2] != null) {
              queryPayOffRecordsTaListDTO.setBorrowerName(obj[2].toString());
            }
            if (obj[3] != null) {
              queryPayOffRecordsTaListDTO.setLoanMoney(obj[3].toString());
            }
            if (obj[4] != null) {
              queryPayOffRecordsTaListDTO.setLoanTimeLimit(obj[4].toString());
            }
            if (obj[5] != null) {
              queryPayOffRecordsTaListDTO.setInterest(obj[5].toString());
            }
            if (obj[6] != null) {
              queryPayOffRecordsTaListDTO.setCorpus(obj[6].toString());
            }
            if (obj[7] != null) {
              queryPayOffRecordsTaListDTO.setLoanStartDate(obj[7].toString());
            }
            if (obj[8] != null) {
              queryPayOffRecordsTaListDTO.setLoanRepayDay((obj[8].toString())
                  .replaceAll("-", ""));
            }
            if (obj[9] != null) {
              queryPayOffRecordsTaListDTO.setLoanPayOffDate(obj[9].toString());
            }
            printList.add(queryPayOffRecordsTaListDTO);
          }
          return printList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  public List queryBorrowerListByCriterionsAll(final SecurityInfo securityInfo,
      final String offic, final String loanBankName, final String borrowerName,
      final String contractId, final String cardNum,
      final String loanStartDate, final String loanEndDate,
      final String loanPayOffDate, final String loanPayOffEndDate,
      final int start, final String orderBy, final String order,
      final int pageSize, final int page,final String houseType) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pl111.loan_kou_acc,pl111.contract_id,pl110.borrower_name,"
              + "pl111.loan_money,pl111.loan_time_limit,sum(pl203.real_interest + pl203.real_punish_interest),"
              + "sum(pl203.real_corpus + pl203.real_interest + pl203.real_punish_interest),pl111.loan_start_date,"
              + "(select m.a from (select p.loan_start_date,p.loan_time_limit,add_months(to_date(p.LOAN_START_DATE, 'yyyymmdd'),"
              + "p.loan_time_limit) as a,p.contract_id as b from pl111 p ) m where m.b = pl111.contract_id) as dqrq,"
              + "pl111.PAYOFFDATE as huankuanriqi"
              + " from pl110 , pl111 ,pl114, pl203 where pl110.contract_id = pl111.contract_id and pl110.contract_id = pl114.contract_id "
              + "and pl111.loan_kou_acc = pl203.loan_kou_acc and pl111.contract_st='12'"
              + " and   pl111.loan_bank_id " + securityInfo.getDkSecuritySQL();

          String criterion = "";
          Vector parameters = new Vector();
          if (offic != null && !"".equals(offic)) {
            criterion += " pl110.office=? and ";
            parameters.add(offic);
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " pl111.loan_bank_id =? and ";
            parameters.add(loanBankName);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " pl110.contract_id = ? and ";
            parameters.add(contractId);
          }
          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " pl110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " pl110.card_num = ? and ";
            parameters.add(cardNum);
          }
          if (loanPayOffDate != null && !"".equals(loanPayOffDate)) {
            criterion += " pl111.PAYOFFDATE >=? and ";
            parameters.add(loanPayOffDate);
          }
          if (loanPayOffEndDate != null && !"".equals(loanPayOffEndDate)) {
            criterion += " pl111.PAYOFFDATE <=? and ";
            parameters.add(loanPayOffEndDate);
          }
          if (loanStartDate != null && !"".equals(loanStartDate)) {
            criterion += " pl111.loan_start_date >= ? and ";
            parameters.add(loanStartDate);
          }
          if (loanEndDate != null && !"".equals(loanEndDate)) {
            criterion += " pl111.loan_start_date <= ? and ";
            parameters.add(loanEndDate);
          }
          if (houseType != null && !"".equals(houseType)) {
            criterion += " pl114.house_type = ? and ";
            parameters.add(houseType);
          }
          if (criterion.length() != 0)
            criterion = " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = " pl110.contract_id ";

          String od = order;
          if (od == null)
            od = " DESC";

          hql = hql
              + criterion
              + " group by pl111.loan_kou_acc,pl111.contract_id,"
              + "pl110.borrower_name,pl111.loan_money,pl111.loan_time_limit,pl111.loan_start_date,pl111.PAYOFFDATE "
              + " order by " + ob + " " + od;
          ;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List queryList = query.list();
          List printList = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            QueryPayOffRecordsTaListDTO queryPayOffRecordsTaListDTO = new QueryPayOffRecordsTaListDTO();
            obj = (Object[]) iterate.next();

            if (obj[0] != null) {
              queryPayOffRecordsTaListDTO.setLoanKouAcc(obj[0].toString());

            }
            if (obj[1] != null) {
              queryPayOffRecordsTaListDTO.setContractId(obj[1].toString());
            }
            if (obj[2] != null) {
              queryPayOffRecordsTaListDTO.setBorrowerName(obj[2].toString());
            }
            if (obj[3] != null) {
              queryPayOffRecordsTaListDTO.setLoanMoney(obj[3].toString());
            }
            if (obj[4] != null) {
              queryPayOffRecordsTaListDTO.setLoanTimeLimit(obj[4].toString());
            }
            if (obj[5] != null) {
              queryPayOffRecordsTaListDTO.setInterest(obj[5].toString());
            }
            if (obj[6] != null) {
              queryPayOffRecordsTaListDTO.setCorpus(obj[6].toString());
            }
            if (obj[7] != null) {
              queryPayOffRecordsTaListDTO.setLoanStartDate(obj[7].toString());
            }
            if (obj[8] != null) {
              queryPayOffRecordsTaListDTO.setLoanRepayDay((obj[8].toString())
                  .replaceAll("-", ""));
            }
            if (obj[9] != null) {
              queryPayOffRecordsTaListDTO.setLoanPayOffDate(obj[9].toString());
            }
            printList.add(queryPayOffRecordsTaListDTO);
          }
          return printList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }

  public List queryRedatePrintList(final String contractId,
      final String officeCode, final String borrowerName, final String cardNum,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,
      final String contractStFind, final String contractSt,
      final String isContractWrite, final String type,
      final SecurityInfo securityInfo, final String beginBackDate,
      final String endBackDate) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id = ? and ";
            parameters.add(contractId);
          }

          if (officeCode != null && !"".equals(officeCode)) {
            criterion += " p110.office = ? and ";
            parameters.add(officeCode);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num = ? and ";
            parameters.add(cardNum);
          }

          if (contractId != null && !"".equals(contractId)) {
            criterion += " p110.contract_id = ? and ";
            parameters.add(contractId);
          }

          if (loanBankName != null && !"".equals(loanBankName)) {
            criterion += " p111.loan_bank_id = ? and ";
            parameters.add(loanBankName);
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += " p110.borrower_name = ? and ";
            parameters.add(borrowerName);
          }

          if (orgName != null && !"".equals(orgName)) {
            criterion += " p110.org_name = ? and ";
            parameters.add(orgName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " p110.card_num = ? and ";
            parameters.add(cardNum);
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
          if (beginBackDate != null && !"".equals(beginBackDate)) {
            criterion += " p110.re_date >= ? and ";
            parameters.add(beginBackDate);
          }

          if (endBackDate != null && !"".equals(endBackDate)) {
            criterion += " p110.re_date <= ? and ";
            parameters.add(endBackDate);
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
            criterion += " p111.contract_st = ? and ";
            parameters.add(contractSt);
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
          List printList = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
          }
          return printList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }
  public Emp queryByCriterions(final String empid, final String orgid) {

    Emp emp = null;
    emp = (Emp) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select emp from Emp emp  ";
        Vector parameters = new Vector();
        String criterion = "";
        if (empid != null && !empid.equals("")) {
          criterion += "emp.empId = ? and ";
          parameters.add(new Integer(empid));
        }
        if (orgid != null && !orgid.equals("")) {
          criterion += "emp.org.id = ? and ";
          parameters.add(new Integer(orgid));
        }
        if (criterion.length() != 0)
          criterion = " where  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;

        Query query0 = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query0.setParameter(i, parameters.get(i));
        }
        return query0.uniqueResult();
      }
    });

    return emp;
  }
  public List queryBorrowerListByCriterions_yk(final String contractId,
      final String borrowerName, final String cardNum, final String officeCode,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,final String begindate, final String enddate,
      final String contractStFind, final String type, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p110.contract_id,p110.borrower_name,p110.card_num,"
              + " case when p111.contract_st between 11 and 13 then p111.loan_money else p115.loan_money end loanmoney,"
              + " p115.loan_time_limit,p111.loan_bank_id,p110.org_name,"
              + " case when p114.house_type = '01' then to_char(p114.house_area) else p114.bargainor_house_area end,"
              + " p114.house_type,p111.contract_st,"
              + " case when p114.house_type = '01' then p114.house_totle_price else p114.bargainor_totle_price end,"
              + " (select a.name from pl113 a where a.auxiliary_id=(select max( p113.auxiliary_id)"
              + " from pl113 p113 "
              + " where p113.contract_id = p110.contract_id)),"
              + " case when p110.privilege_borrower_id is null then 1 else 0 end,"
              + " p110.operator,"
              + " case when p114.house_type = '01' then to_char(p114.house_addr) else p114.bargainor_house_addr end,"
              + " p111.mark_a,"
              + " p110.loanvipcheck_date"
              + " from pl110 p110,pl111 p111,pl114 p114,pl115 p115"
              + " where p110.contract_id=p111.contract_id"
              + " and p110.contract_id=p115.contract_id"
              + " and p111.contract_id=p114.contract_id  ";

          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
              criterion += " p110.contract_id like ? and ";
              parameters.add("%" + contractId + "%");
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
              criterion += " p110.borrower_name like ? and ";
              parameters.add("%" + borrowerName + "%");
          }

          if (cardNum != null && !"".equals(cardNum)) {
              criterion += " p110.card_num like ? and ";
              parameters.add("%" + cardNum + "%");
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
              criterion += " p110.org_name like ? and ";
              parameters.add("%" + orgName + "%");
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
            criterion += " p110.loanvipcheck_date >= ? and ";
            parameters.add(begindate);
          }
          
          if (enddate != null && !"".equals(enddate)) {
            criterion += " p110.loanvipcheck_date <= ? and ";
            parameters.add(enddate);
          }

          if (contractStFind != null && !"".equals(contractStFind)) {
            criterion += " p111.contract_st = ? and ";
            parameters.add(contractStFind);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p110.contract_id ";

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
  public int queryBorrowerCountByCriterions_yk(final String contractId,
      final String borrowerName, final String cardNum, final String officeCode,
      final String loanBankName, final String orgName, final String houseType,
      final String beginBizDate, final String endBizDate,
      final String contractStFind, final String type,
      final SecurityInfo securityInfo) {
    Integer count = new Integer(0);

    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(p110.contract_id)"
              + " from pl110 p110,pl111 p111,pl114 p114,pl115 p115"
              + " where p110.contract_id=p111.contract_id"
              + " and p110.contract_id=p115.contract_id"
              + " and p111.contract_id=p114.contract_id  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (contractId != null && !"".equals(contractId)) {
            if (type != null) {
              criterion += " p110.contract_id like ? and ";
              parameters.add("%" + contractId + "%");
            } else {
              criterion += " p110.contract_id like ? and ";
              parameters.add(contractId);
            }
          }

          if (borrowerName != null && !"".equals(borrowerName)) {
            if (type != null) {
              criterion += " p110.borrower_name like ? and ";
              parameters.add("%" + borrowerName + "%");
            } else {
              criterion += " p110.borrower_name = ? and ";
              parameters.add(borrowerName);
            }
          }

          if (cardNum != null && !"".equals(cardNum)) {
            if (type != null) {
              criterion += " p110.card_num like ? and ";
              parameters.add("%" + cardNum + "%");
            } else {
              criterion += " p110.card_num = ? and ";
              parameters.add(cardNum);
            }
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
            if (type != null) {
              criterion += " p110.org_name like ? and ";
              parameters.add("%" + orgName + "%");
            } else {
              criterion += " p110.org_name = ? and ";
              parameters.add(orgName);
            }
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

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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
  public int queryEmpSalary(final String empName, final String cardNum) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String tempCardNum = "";
            if (cardNum.length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNum);
            if (cardNum.length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNum);
            String sql = "select case"
         +" when (select count(t.id)"
         +"  from ba002 t, aa002 c"
         +"  where t.name = '"+empName
         +"' and (t.card_num = '"+cardNum+"' or t.card_num = '"+tempCardNum+"')"
         +" and t.id = c.emp_info_id"
         +" and c.pay_status in (1, 2)) = 0 then"
         +" 0"
         +" when (select count(t.id)"
         +"  from ba002 t, aa002 c"
         +"  where t.name = '"+empName
         +"'   and (t.card_num = '"+cardNum+"' or t.card_num ='"+tempCardNum+"')"
         +"   and t.id = c.emp_info_id"
         +"   and c.pay_status in (1, 2)) = 1 then"
         +" (select m.salary_base from aa002 m where m.emp_info_id=b.id and m.pay_status in(1,2))"
         +"  when (select count(t.id)"
         +"  from ba002 t, aa002 c"
         +" where t.name = '"+empName
         +"'    and (t.card_num = '"+cardNum+"' or t.card_num ='"+tempCardNum+"')"
         +"  and t.id = c.emp_info_id"
         +"    and c.pay_status in (1, 2)) > 1 then"
         +"  (select m.salary_base from aa002 m where m.emp_info_id=b.id and m.pay_status=1)"
         +" end"
         +"  from aa002 a, ba002 b"
         +"  where a.emp_info_id = b.id"
         +"  and a.pay_status in(1,2) and (b.card_num = '"+cardNum+"' or b.card_num ='"+tempCardNum+"')";
            Query query = session.createSQLQuery(sql);
            if(query.uniqueResult()!=null){
              return Integer.valueOf(query.uniqueResult().toString());
            }else{
              return Integer.valueOf("0");
            }
            
          }
        });
    return count.intValue();
  }
  public String countPeopleNum_EmpName(final String contractid) {

    String num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.name from pl113 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            } else {
              return "";
            }
          }
        });

    return num;
  }
  public String countPeopleNum_EmpCardNum(final String contractid) {

    String num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.card_num from pl113 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            } else {
              return "";
            }
          }
        });

    return num;
  }
  public String countPeopleNum_EmpSalary(final String contractid) {

    String num = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select p.month_salary from pl113 p where p.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, contractid);
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            } else {
              return "";
            }
          }
        });

    return num;
  }
}
