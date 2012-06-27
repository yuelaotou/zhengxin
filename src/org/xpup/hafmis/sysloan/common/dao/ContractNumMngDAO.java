package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.sysloan.common.domain.entity.ContractNumMng;

/**
 * 合同流水号管理表PL101
 * 
 *@author 李娟
 *2007-9-13
 */
public class ContractNumMngDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ContractNumMng queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ContractNumMng) getHibernateTemplate().get(ContractNumMng.class,id);    
  }
  /**
   * 插入记录
   * 
   * @param contractNumMng
   * @return
   */
  public Serializable insert(ContractNumMng  contractNumMng) {
    Serializable id = null;
    try {
      Validate.notNull(contractNumMng);
      id = getHibernateTemplate().save(contractNumMng);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  
  
  /**
   * 查找作废的合同编号
   * officeCode：办事处代码 
   * 刘洋
   */
  public String getCancelContractId(final String officeCode,final String houseType) {
    String cancelContractId = "";
    cancelContractId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String tempCancelContractId = "";
            String hql = "select min(contractNumCancel.cancelcontractid) from ContractNumCancel contractNumCancel where contractNumCancel.office=? and contractNumCancel.reserveaA=? ";       
            Query query0 = session.createQuery(hql);        
            query0.setString(0,officeCode);
            query0.setString(1,houseType);
            if (query0.list().size() > 0) {
              tempCancelContractId = (String) query0.uniqueResult();
            }
            return tempCancelContractId;
          }
        });
    return cancelContractId;
  }
  
  /**
   * 查找合同编号
   * officeCode：办事处代码 
   * 刘洋
   */
  public String getMaxContractId(final String officeCode,final String houseType) {
    String contractId = "";
    contractId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String tempContractId = "";
            String hql = "select contractNumMng.flowNum from ContractNumMng contractNumMng where contractNumMng.office=? and contractNumMng.reserveaA=? ";       
            Query query0 = session.createQuery(hql);        
            query0.setString(0,officeCode);
            query0.setString(1,houseType);
            if (query0.list().size() > 0) {
              tempContractId = (String) query0.uniqueResult();
            }
            return tempContractId;
          }
        });
    return contractId;
  }
  
  /**
   * 生成合同编号
   *  officeCode：办事处代码 bizYear：业务年
   *  @author 刘洋
   */

  public String getContractId(final String officeCode, final String bizYear,
      final String houseType, final Map officeInnerCodeMap)
      throws Exception, BusinessException {
    String contractId = "";
    String cancelContractId = "";
    int len0 = 0;
    int len1 = 0;
    int cha = 0;
    String office = "";
    String addstring = "";
    try {
      cancelContractId = this.getCancelContractId(officeCode,houseType);
      if (cancelContractId!= null&&!(cancelContractId.equals(""))) {
        final String deleteCancelContractId=cancelContractId;
        getHibernateTemplate().execute(
            new HibernateCallback() {
              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                String hql = "delete from ContractNumCancel contractNumCancel where contractNumCancel.office=? and contractNumCancel.cancelcontractid=? and contractNumCancel.reserveaA=? ";       
                Query query0 = session.createQuery(hql);        
                query0.setString(0,officeCode);
                query0.setString(1,deleteCancelContractId);
                query0.setString(2,houseType);
                query0.executeUpdate();
                return null;
              }
            });
        office = (String)officeInnerCodeMap.get(officeCode);
        if(office.length()<2){
          office = "0" + office;
        }
        String str = "";
        for(int i=6;i>cancelContractId.length();i--){
          str += "0";
        }
        cancelContractId = str + cancelContractId;
        contractId=bizYear+office+houseType+cancelContractId;
      } else {
        String maxContractId=this.getMaxContractId(officeCode,houseType);
        len0 = maxContractId.length();
        String contractIdAdd = "";
        if(maxContractId!=null && !"".equals(maxContractId)){
         contractIdAdd = String.valueOf(Integer.parseInt(maxContractId)+1);
        }
        len1 = contractIdAdd.length();     
        cha = len0 - len1;
        if (cha > 0) {
          for (int i = 0; i < cha; i++) {
            addstring = addstring + "0";
          }
          contractIdAdd = addstring + contractIdAdd;
        }
        final String contractIdUpdate=contractIdAdd;
        getHibernateTemplate().execute(
            new HibernateCallback() {
              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                String hql = "update  ContractNumMng contractNumMng set contractNumMng.flowNum=?  where contractNumMng.office=? and contractNumMng.reserveaA=?  ";       
                Query query0 = session.createQuery(hql);    
                query0.setString(0,contractIdUpdate);
                query0.setString(1,officeCode);
                query0.setString(2,houseType);
                query0.executeUpdate();
                return null;
              }
            });
        office = (String)officeInnerCodeMap.get(officeCode);
        if(office.length()<2){
          office = "0" + office;
        }
        String str = "";
        for(int i=6;i>contractIdAdd.length();i--){
          str += "0";
        }
        contractIdAdd = str + contractIdAdd;
        contractId=bizYear+office+houseType+contractIdAdd;
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("生成合同号错误!");
    }
    return contractId;
  }
}
