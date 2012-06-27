package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.CreditIdentity;

public class CreditIdentityDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public CreditIdentity queryById(Serializable id) {
    Validate.notNull(id);
    return (CreditIdentity) getHibernateTemplate()
        .get(CreditIdentity.class, id);
  }

  /**
   * 插入记录
   * 
   * @param CreditIdentity
   * @return
   */
  public Serializable insert(CreditIdentity creditIdentity) {
    Serializable id = null;
    try {
      Validate.notNull(creditIdentity);
      id = getHibernateTemplate().save(creditIdentity);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

}
