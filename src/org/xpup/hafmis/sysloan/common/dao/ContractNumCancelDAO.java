package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.ContractNumCancel;

/**
 * 作废合同编号表PL102
 * 
 *@author 李娟
 *2007-9-13
 */
public class ContractNumCancelDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ContractNumCancel queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ContractNumCancel) getHibernateTemplate().get(ContractNumCancel.class,id);    
  }
  /**
   * 插入记录(撤销凭证号)
   * 
   * @param contractNumCancel
   * @return
   */
  public Serializable insert(ContractNumCancel  contractNumCancel) {
    Serializable id = null;
    try {
      Validate.notNull(contractNumCancel);
      id = getHibernateTemplate().save(contractNumCancel);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
}
