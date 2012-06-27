package org.xpup.hafmis.demo.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.demo.domain.entity.Demo;

public class DemoDwDAO  extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public Demo queryById(Integer id) {
    Validate.notNull(id);
    return  (Demo) getHibernateTemplate().get(Demo.class,id);    
  }
  /**
   * 插入记录
   * @param demo
   * @return
   */
  public Serializable insert(Demo demo){
    Serializable id = null;
    try{    
    Validate.notNull(demo);
    id = getHibernateTemplate().save(demo);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
