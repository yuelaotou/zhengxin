package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowTransOut;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;

public class OrgHAFAccountFlowTransOutDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgHAFAccountFlowTransOut queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowTransOut) getHibernateTemplate().get(OrgHAFAccountFlowTransOut.class,id);    
  }
  /**
   * 插入记录
   * @param OrgHAFAccountFlowTransOut
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowTransOut);
    id = getHibernateTemplate().save(orgHAFAccountFlowTransOut); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  //根据ORGID查询
  public List queryByOrgID(final Integer id){
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from OrgHAFAccountFlowTransOut tot where tot.org.id = ? "  ;
          Query query = session.createQuery(hql);
          query.setParameter(0, id);
          return query.list();
        }
      }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  //AA101
  public List FindOrgHAFAccountWZQ(final String Headoutid){
    List list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " select oh from OrgHAFAccountFlowTransOut oh,TranOutHead toh where oh.org.id = toh.tranOutOrg.id and toh.id = ?";
        Query query = session.createQuery(hql);
//        if(Headoutid!=null&&Headoutid.equals("")){
           query.setParameter(0, new Integer(Headoutid));
//        }
        return query.list();
      }
    });
    return list;
  }
  
  public OrgHAFAccountFlowTransOut queryOrgHAFAccount(final String tranHeadid){
    OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut = null;
    orgHAFAccountFlowTransOut = (OrgHAFAccountFlowTransOut) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "  from OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut " +
            " where orgHAFAccountFlowTransOut.bizId = ? ";
        Query query = session.createQuery(hql);
//        if(Headoutid!=null&&Headoutid.equals("")){
           query.setParameter(0, new BigDecimal(tranHeadid));
//        }
        return query.uniqueResult();
      }
    });
    return orgHAFAccountFlowTransOut;
  }

  //撤消转出--删除101
  public void delete(OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut) {
    try {
      Validate.notNull(orgHAFAccountFlowTransOut);
      getHibernateTemplate().delete(orgHAFAccountFlowTransOut);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  
}















