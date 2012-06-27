package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPayBizActivityLog;

/**
 * 单位补缴
 * 
 *@author 李娟
 *2007-6-20
 */
public class OrgAddPayDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgAddPay queryById(Serializable id) {  
    Validate.notNull(id);
    return  (OrgAddPay) getHibernateTemplate().get(OrgAddPay.class,id);    
  }
  /**
   * 插入记录
   * @param orgAddPay
   * @return
   */
  public Serializable insert(OrgAddPay orgAddPay){
    Serializable id = null;
    try{    
    Validate.notNull(orgAddPay);
    id = getHibernateTemplate().save(orgAddPay);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 删除单个记录
   * @param orgAddPay
   */
  public void delete(OrgAddPay orgAddPay){
    Validate.notNull(orgAddPay);
    getHibernateTemplate().delete(orgAddPay);
  }
  /**
   * 删除list
   */
  public void deleteList(List list){
    Validate.notNull(list);
    getHibernateTemplate().deleteAll(list);
  }
  /**
   * 李娟
   * 根据单位ID和缴存状态查询
   * @param orgid
   * @param payStatus
   * @return
   */
  public OrgAddPay queryOrgAddPayByOrgIdLJ(final Serializable orgid,final Integer payStatus ){
    OrgAddPay orgAddPay = null;
    orgAddPay = (OrgAddPay) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from OrgAddPay orgAddPay ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgid != null&&!orgid.equals("")) {
          criterion += "orgAddPay.org.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
        }

        if (payStatus != null&&!payStatus.equals("")) {
          criterion += "orgAddPay.payStatus = ?  and ";
          parameters.add(payStatus);
        }
        
        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.uniqueResult();
      }
    });
    if(orgAddPay == null){
      orgAddPay = new OrgAddPay();
    }
    return orgAddPay;
  }
  /**
   * 郭婧平
   * 根据票据编号查询
   * @param noteNum
   * @return
   */
  public OrgAddPay queryOrgAddPayByNotenum(final String noteNum){
    OrgAddPay orgAddPay = null;
    orgAddPay = (OrgAddPay) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from OrgAddPay orgAddPay ";
        Vector parameters = new Vector();
        String criterion = "";

        if (noteNum != null&&!noteNum.equals("")) {
          criterion += "orgAddPay.noteNum = ?  and ";
          parameters.add(noteNum);
        }

        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.uniqueResult();
      }
    });
    if(orgAddPay == null){
      orgAddPay = new OrgAddPay();
    }
    return orgAddPay;
  }
  public String findIdTemp(final String headId){
    Validate.notNull(headId);
    String ID = "";
    try {
      ID =  (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select aa302.id from aa302 aa302 where aa302.pay_head_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, headId);
          List list = query.list();
          return list==null||list.size()==0?"":list.get(0).toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ID;
  }
}