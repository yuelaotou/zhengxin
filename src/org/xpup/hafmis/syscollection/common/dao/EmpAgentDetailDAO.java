package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAgentDetail;
/**
 * Copy Right Information : 职工代扣清册表 Goldsoft Project :
 * EmpAgentDetailDAO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.17
 */
public class EmpAgentDetailDAO extends HibernateDaoSupport {
  
  /**
   * 插入信息
   * 
   * @param agentDetail
   * @return
   * @author 付云峰
   */
  public Serializable insert(EmpAgentDetail empAgentDetail) {
    Serializable id = null;
    try {
      Validate.notNull(empAgentDetail);
      id = getHibernateTemplate().save(empAgentDetail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
}
