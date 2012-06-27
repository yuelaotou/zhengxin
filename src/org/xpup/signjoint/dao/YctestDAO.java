package org.xpup.signjoint.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.signjoint.entity.Yctest;


public class YctestDAO extends HibernateDaoSupport {
  
  public Yctest queryById(Integer id) {
    Validate.notNull(id);
    return  (Yctest) getHibernateTemplate().get(Yctest.class,id);    
  }
}
