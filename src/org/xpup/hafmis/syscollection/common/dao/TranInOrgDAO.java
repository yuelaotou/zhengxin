package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;

/**
 * 转入单位
 * 
 *@author 李娟
 *2007-6-20
 */
public class TranInOrgDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public TranInOrg queryById(Integer id) {
    Validate.notNull(id);
    return  (TranInOrg) getHibernateTemplate().get(TranInOrg.class,id);    
  }
  /**
   * 插入记录
   * @param tranInOrg
   * @return
   */
  public Serializable insert(TranInOrg tranInOrg){
    Serializable id = null;
    try{    
    Validate.notNull(tranInOrg);
    id = getHibernateTemplate().save(tranInOrg); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  
 
 
  /**
   * 职工是否已经存在在业务尾表（头表状态：录入清册）
   * @param orgId
   * @param empId
   * @return
   */public String queryOrgHeadId(final String orgId, final String empId) {
    String orgheadid = "";
     try {
      orgheadid  = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String ssql = "select a309.id from aa309 a309, aa310 a310 " +
                  "where a309.id = a310.tran_out_head_id " +
                  "and a309.out_org_id = ? " +
                  "and a309.tran_status = 1 " +
                  "and a310.emp_id = ? ";
              Query query = session.createSQLQuery(ssql);
              query.setParameter(0,new Integer(orgId));
              query.setParameter(1,new Integer(empId));
              if(query.uniqueResult()==null){
                return "0";
                
              }else{
                return query.uniqueResult().toString();
              }          
            }
          });
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return orgheadid;
  }

}
