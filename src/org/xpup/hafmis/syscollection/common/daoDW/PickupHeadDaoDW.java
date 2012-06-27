package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;

public class PickupHeadDaoDW extends HibernateDaoSupport{
  public PickHead queryById(Serializable id) {  
    Validate.notNull(id);
    return  (PickHead) getHibernateTemplate().get(PickHead.class,id);    
  }
}