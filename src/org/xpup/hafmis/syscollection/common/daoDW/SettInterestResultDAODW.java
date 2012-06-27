package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestResult;

/**
 * 结算结果
 * 
 * @author wangy 2007-12-19
 */
public class SettInterestResultDAODW extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public SettInterestResult queryById(Serializable id) {
    Validate.notNull(id);
    return (SettInterestResult) getHibernateTemplate().get(
        SettInterestResult.class, id);
  }

  /**
   * 插入记录
   * 
   * @param settInterestResult
   * @return
   */
  public Serializable insert(SettInterestResult settInterestResult) {
    Serializable id = null;
    try {
      Validate.notNull(settInterestResult);
      id = getHibernateTemplate().save(settInterestResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }
  
}
