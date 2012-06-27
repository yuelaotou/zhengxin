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
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowExcessPayment;

public class OrgHAFAccountFlowExcessPaymentDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgHAFAccountFlowExcessPayment queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowExcessPayment) getHibernateTemplate().get(OrgHAFAccountFlowExcessPayment.class,id);    
  }
  /**
   * 插入记录
   * @param OrgHAFAccountFlowExcessPayment
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowExcessPayment orgHAFAccountFlowExcessPayment){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowExcessPayment);
    id = getHibernateTemplate().save(orgHAFAccountFlowExcessPayment); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  
  /**
   * 
   * 根据单位ID查询业务流水
   * @param orgid
   * @return
   */
  public List queryOrgoverpayHAFAccountFlow(final Serializable orgid,final Integer bizStatus ){
    
    List list=new ArrayList();
    list = (List) getHibernateTemplate()
    .executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from OrgHAFAccountFlowExcessPayment orgHAFAccountFlowExcessPayment ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgid != null&&!orgid.equals("")) {
          criterion += "orgHAFAccountFlowExcessPayment.org.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
        }

        if (bizStatus != null) {
          criterion += "orgHAFAccountFlowExcessPayment.bizStatus >= ?  and ";
          parameters.add(new BigDecimal(bizStatus.toString()));
        }
        
        if (criterion.length() != 0)
          criterion = " where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
      }
    });

    return list;
  }

  public BigDecimal queryOrgoverpayfromAA301(final Serializable orgid) {
    BigDecimal bd = new BigDecimal(0.00);
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select nvl(sum(t.pay_money),0) from aa301 t where t.pay_type='D' and t.pay_status!=5 and t.org_id=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, new Integer(orgid.toString()));
        return query.uniqueResult().toString();
      }
    });
    bd=new BigDecimal(obj.toString());
    return bd;
  }
}