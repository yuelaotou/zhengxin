package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanContract;
import org.xpup.hafmis.sysloan.common.domain.entity.PledgeContract;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaAF;

public class LoanContractDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public LoanContract queryById(Serializable id) {

    Validate.notNull(id);
    return  (LoanContract) getHibernateTemplate().get(LoanContract.class,id);
   
  }
  /**
   * 根据主键删除
   * yuqf
   * @param id
   * @return
   */
  public void deleteById(String id) {
    try {
      Validate.notNull(id);
      // getHibernateTemplate().clear();
      LoanContract loanContract = queryById(id);

      getHibernateTemplate().delete(loanContract);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 根据主键查询
   * yuqf
   * @param id
   * @return
   */
  public String queryByIdYU(final String id){
    Validate.notNull(id);
    String idd = "";
    try{
      idd = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub
          
          String hql = "select t.contract_id from pl120 t where t.contract_id='"+id+"'";
         
          Query query = session.createSQLQuery(hql);
        
          return query.uniqueResult();
        }

      });
    }catch(Exception e){
      e.printStackTrace();
    }
    return idd;
  }
  /**
   * 根据主键查询
   * yuqf
   * @param id
   * @return
   */
  public String queryById_yg(final String id){
    Validate.notNull(id);
    String idd = "";
    try{
      idd = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
        SQLException {
          String hql = "select t.contract_id from pl121 t where t.status = '0' and t.contract_id='"+id+"'";
          Query query = session.createSQLQuery(hql);
          return query.uniqueResult()==null ? "":query.uniqueResult();
        }
      });
    }catch(Exception e){
      e.printStackTrace();
    }
    return idd;
  }
  /**
   * yuqf
   * 2007-10-29
   * 查询担保公司名称
   * @param id
   * @return
   */
  public String queryAssOrgByIdYU(final String id){
    Validate.notNull(id);
    String idd = "";
    try{
      idd = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub
          String hql = "select tt.assistant_org_name from pl120 t,pl007 tt where t.assistant_org_id=tt.assistant_org_id and t.contract_id='"+id+"'";
          Query query = session.createSQLQuery(hql);
     
          return query.uniqueResult();
        }

      });
    }catch(Exception e){
      e.printStackTrace();
    }
    return idd;
  }

  /**
   * 插入记录
   * @param LoanContract
   * @return
   */
  public Serializable insert(LoanContract loanContract){
    Serializable id = null;
    try{    
    Validate.notNull(loanContract);
    id = getHibernateTemplate().save(loanContract);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  /**
   * yuqf
   * 查询PL120字段，用于借款合同信息页面显示
   * @param id
   * @return
   */
  public EndorsecontractTaDTO queryLoanContractInfoYU(final String id,final SecurityInfo securityInfo){
    EndorsecontractTaDTO endorsecontractTaDTO = null;
    try{
      endorsecontractTaDTO = (EndorsecontractTaDTO) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub
          String hql = "select t.assurer,t.agreement_date,t.loan_start_date,t.assistant_org_id,t.photo_url2 from pl120 t where t.contract_id='"+id+"'";
          Query query = session.createSQLQuery(hql);
        
          EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            if(obj[0] != null){
              endorsecontractTaDTO.setAssurer(obj[0].toString());//保证方
            }
            if(obj[1] != null){
              endorsecontractTaDTO.setContractSureDate(obj[1].toString());//合同签订日期
            }
            if(obj[2] != null){
              endorsecontractTaDTO.setDebitMoneyStaDate(obj[2].toString());//借款起始日期
            }
            if(obj[3]!= null){
              endorsecontractTaDTO.setAssistantOrgId(obj[3].toString());//担保公司名称
            }
            if(obj[4]!=null){
              endorsecontractTaDTO.setPhotoUrl(obj[4].toString());//路径
            }
          }
          /*********************3**************************/
//          if(query.uniqueResult() != null){
//            endorsecontractTaDTO.setAssurer(query.uniqueResult().toString());
//          }
          /*********************3**************************/
          return endorsecontractTaDTO;
        }

      });
    }catch(Exception e){
      e.printStackTrace();
    }
    return endorsecontractTaDTO;
  }
  /**
   * 下达发放通知书_确定
   * @author wsh
   * 2007-10-03
   * 根据PL120中的合同编号修改PL120.LOAN_PAY_DATE
   * 查询条件：contractId
   */
  public void updateLoanContract_wsh(final String loanPayDate,final String  contractId) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update LoanContract loanContract " +
              "set loanContract.loanPayDate = ? " +             
              "where loanContract.contractId = ? ";
          Query query=session.createQuery(hql);
          query.setString(0, loanPayDate);
          query.setString(1, contractId);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
public String findIssuenoticeBizDate(final String contractId)throws Exception{
        String bizDate = (String) getHibernateTemplate().execute(
            new HibernateCallback() {
              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                String hql = "select t.loan_pay_date from pl120 t where t.contract_id= ? ";
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
                String temp_bizDate = "";
                if (obj != null) {
                  temp_bizDate = obj.toString();
                }
                return temp_bizDate;
              }
            });
        return bizDate;
}
public String findloanBankInAccount(final String contractId)throws Exception{
  String bizDate = (String) getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
          String hql = "select t.inAccount from pl002 t where t.loan_bank_id= ? ";
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
          String temp_bizDate = "";
          if (obj != null) {
            temp_bizDate = obj.toString();
          }
          return temp_bizDate;
        }
      });
  return bizDate;
}
public String findloanBankOutAccount(final String contractId)throws Exception{
  String bizDate = (String) getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
          String hql = "select t.outAccount from pl002 t where t.loan_bank_id= ? ";
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
          String temp_bizDate = "";
          if (obj != null) {
            temp_bizDate = obj.toString();
          }
          return temp_bizDate;
        }
      });
  return bizDate;
}
}
