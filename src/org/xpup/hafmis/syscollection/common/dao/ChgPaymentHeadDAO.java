package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentHead;

public class ChgPaymentHeadDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ChgPaymentHead queryById(Integer id) {
    Validate.notNull(id);
    return  (ChgPaymentHead) getHibernateTemplate().get(ChgPaymentHead.class,id);    
  }
  /**
   * 插入记录
   * @param chgPaymentHead
   * @return
   */
  public Serializable insert(ChgPaymentHead chgPaymentHead){
    Serializable id = null;
    try{    
    Validate.notNull(chgPaymentHead);
    id = getHibernateTemplate().save(chgPaymentHead);
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 李娟
   * 根据缴存头表ID及启用状态查询
   * @param payHeadId
   * @param chgStatus
   * @return
   */
  public List queryChgPaymentHeadByPayHeadId(final Serializable orgid,final Serializable payHeadId,final Integer chgStatus ){
    List chgPaymentHead = null;
    chgPaymentHead =  getHibernateTemplate()
    .executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from ChgPaymentHead chgPaymentHead ";
        Vector parameters = new Vector();
        String criterion = "";
        if (orgid != null&&!orgid.equals("")) {
          criterion += "chgPaymentHead.org.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
        }
        if (payHeadId != null&&!payHeadId.equals("")) {
          criterion += "chgPaymentHead.paymentHead.id = ?  and ";
          parameters.add(new Integer(payHeadId.toString()));
        }else{
          criterion += "chgPaymentHead.paymentHead.id is null and ";
        }

        if (chgStatus != null&&!chgStatus.equals("")) {
          criterion += "chgPaymentHead.chgStatus = ?  and ";
          parameters.add(chgStatus);
        }
        
        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
      }
    });
    return chgPaymentHead;
  }
  /**
   * 查询缴额变更头表（AA202）的最大的id
   * 没有条件
   * @return 吴洪涛
   */
  public Serializable queryChgPaymentHeadWuht(){
    Serializable id = null;
    id = (Serializable) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "select max(chgPaymentHead.id) " +
            "from ChgPaymentHead chgPaymentHead ";
        Vector parameters = new Vector();

        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.uniqueResult();
      }
    });
   
    return id;
  }
  
  /**
   * 于庆丰 count chg_type=2
   * @param office
   * @param bank
   * @param orgCharacter
   * @param dept
   * @param ragion
   * @param startDate
   * @param endDate
   * @return
   */
  public int queryCount(final String office,final String bank,final String orgCharacter,
      final String dept,final String ragion,final String startDate,final String endDate){
    int count = 0;
    try{
      Integer countInteger = (Integer)getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
     
        String hql = "select count(chgPaymentHead.id) from ChgPaymentHead chgPaymentHead ";
        Vector parameters = new Vector();
        String criterion = "";
        if(office != null && !"".equals(office)){
          criterion += " chgPaymentHead.org.orgInfo.officecode = ? and ";
          parameters.add(office);
        }
        if(bank != null && !"".equals(bank)){
          criterion += " chgPaymentHead.org.orgInfo.collectionBankId = ? and ";
          parameters.add(bank);
        }
        if(orgCharacter != null && !"".equals(orgCharacter)){
          criterion += " chgPaymentHead.org.orgInfo.character = ? and ";
          parameters.add(orgCharacter);
        }
        if(dept != null && !"".equals(dept)){
          criterion += " chgPaymentHead.org.orgInfo.deptInCharge = ? and ";
          parameters.add(dept);
        }
        if(ragion != null && !"".equals(ragion)){
          criterion += " chgPaymentHead.org.orgInfo.region = ? and ";
          parameters.add(ragion);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
          criterion += " (chgPaymentHead.bizDate  between ?  and  ?)  and ";
          parameters.add(startDate);
          parameters.add(endDate);
        }
        if (criterion.length() != 0) 
          criterion = " where chgPaymentHead.chgStatus=2 and chgPaymentHead.chgtype=B and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
//          criterion = " where "
//            + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion ;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Integer countTemp=new Integer(0);
          Iterator it=query.iterate();          
          if(it.hasNext()){
            countTemp=(Integer)it.next();
          }
          return countTemp;
      }
    });
      count = countInteger.intValue();
    }catch(Exception e){
      e.printStackTrace();
    }
    return count;
  }
 
}
