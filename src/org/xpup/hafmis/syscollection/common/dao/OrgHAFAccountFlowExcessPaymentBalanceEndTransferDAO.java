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
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowExcessPaymentBalanceEndTransfer;

public class OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgHAFAccountFlowExcessPaymentBalanceEndTransfer queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowExcessPaymentBalanceEndTransfer) getHibernateTemplate().get(OrgHAFAccountFlowExcessPaymentBalanceEndTransfer.class,id);    
  }
  /**
   * 插入记录
   * @param OrgHAFAccountFlowExcessPaymentBalanceEndTransfer
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowExcessPaymentBalanceEndTransfer orgHAFAccountFlowExcessPaymentBalanceEndTransfer){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowExcessPaymentBalanceEndTransfer);
    id = getHibernateTemplate().save(orgHAFAccountFlowExcessPaymentBalanceEndTransfer); 
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
  public List orgHAFAccountFlowExcessPaymentBalanceEndTransfer(final Serializable orgid,final Integer bizStatus ){

    List list=new ArrayList();
    list = (List) getHibernateTemplate()
    .executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from OrgHAFAccountFlowExcessPaymentBalanceEndTransfer orgHAFAccountFlowExcessPaymentBalanceEndTransfer ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgid != null&&!orgid.equals("")) {
          criterion += "orgHAFAccountFlowExcessPaymentBalanceEndTransfer.org.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
        }

        if (bizStatus != null) {
          criterion += "orgHAFAccountFlowExcessPaymentBalanceEndTransfer.bizStatus >= ?  and ";
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
}
