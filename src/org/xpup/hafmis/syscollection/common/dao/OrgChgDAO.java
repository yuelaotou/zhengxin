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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChg;

public class OrgChgDAO extends HibernateDaoSupport {

  public OrgChg queryById(Integer id) {
    Validate.notNull(id);
    return (OrgChg) getHibernateTemplate().get(OrgChg.class, id);
  }

  public Serializable insert(OrgChg orgChg) {
    Serializable id = null;
    try {
      Validate.notNull(orgChg);
      id = getHibernateTemplate().save(orgChg);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  public void update(OrgChg orgChg) {
    try {
      Validate.notNull(orgChg);
      getHibernateTemplate().update(orgChg);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void delete(OrgChg orgChg) {
    Validate.notNull(orgChg);
    getHibernateTemplate().delete(orgChg);
  }

  public List queryOrgChgList(final String orgid, final String status, final String print, final String orderBy,
      final String order, final int start, final int pageSize,final SecurityInfo securityInfo) {
    List list = null;
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from OrgChg orgChg where 1 = 1 ";
        Vector parameters = new Vector();
        String criterion = "";
        if (orgid != null && !orgid.equals("")) {
          criterion += " and orgChg.org.id = ? ";
          parameters.add(new Integer(orgid));
        }
        if (status != null && !status.equals("")) {
          criterion += " and orgChg.status = ? ";
          parameters.add(status);
        }
        if (print != null && !print.equals("")) {
          criterion += " and orgChg.print = ? ";
          parameters.add(print);
        }

        String ob = orderBy;
        if (ob == null)
          ob = "orgChg.id";
        String od = order;
        if (od == null)
          od = "ASC";
        if(criterion.length()==0){
          criterion += " and orgChg.status = 0 ";
        }
        hql = hql + criterion + " and orgChg.org.id " + securityInfo.getGjjSecurityHqlSQL() + " order by " + ob + " " + order;
        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);
        return query.list();
      }
    });
    return list;
  }

  public int queryOrgChgListAll(final String orgid, final String status, final String print,final SecurityInfo securityInfo) {
    Integer num = new Integer(0);
    num = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select count(orgChg.id) from OrgChg orgChg where 1 = 1 ";
        Vector parameters = new Vector();
        String criterion = "";
        if (orgid != null && !orgid.equals("")) {
          criterion += " and orgChg.org.id = ? ";
          parameters.add(new Integer(orgid));
        }
        if (status != null && !status.equals("")) {
          criterion += " and orgChg.status = ? ";
          parameters.add(status);
        }
        if (print != null && !print.equals("")) {
          criterion += " and orgChg.print = ? ";
          parameters.add(print);
        }
        if(criterion.length()==0){
          criterion += " and orgChg.status = 0 ";
        }
        hql = hql + criterion + " and orgChg.org.id " + securityInfo.getGjjSecurityHqlSQL();
        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.uniqueResult();
      }
    });
    return num.intValue();
  }
  public List queryOrgChgListCheck(final String orgid, final String status, final String print, final String orderBy,
      final String order, final int start, final int pageSize,final SecurityInfo securityInfo) {
    List list = null;
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from OrgChg orgChg where 1 = 1 ";
        Vector parameters = new Vector();
        String criterion = "";
        if (orgid != null && !orgid.equals("")) {
          criterion += " and orgChg.org.id = ? ";
          parameters.add(new Integer(orgid));
        }
        if (status != null && !status.equals("")) {
          criterion += " and orgChg.status = ? ";
          parameters.add(status);
        }
        if (print != null && !print.equals("")) {
          criterion += " and orgChg.print = ? ";
          parameters.add(print);
        }

        String ob = orderBy;
        if (ob == null)
          ob = "orgChg.id";
        String od = order;
        if (od == null)
          od = "ASC";
        if(criterion.length()==0){
          criterion += " and orgChg.status = '1'  and orgChg.print = '0' ";
        }
        hql = hql + criterion + " and orgChg.org.id " + securityInfo.getGjjSecurityHqlSQL() + " order by " + ob + " " + order;
        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);
        return query.list();
      }
    });
    return list;
  }
  public int queryOrgChgListAllCheck(final String orgid, final String status, final String print,final SecurityInfo securityInfo) {
    Integer num = new Integer(0);
    num = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select count(orgChg.id) from OrgChg orgChg where 1 = 1 ";
        Vector parameters = new Vector();
        String criterion = "";
        if (orgid != null && !orgid.equals("")) {
          criterion += " and orgChg.org.id = ? ";
          parameters.add(new Integer(orgid));
        }
        if (status != null && !status.equals("")) {
          criterion += " and orgChg.status = ? ";
          parameters.add(status);
        }
        if (print != null && !print.equals("")) {
          criterion += " and orgChg.print = ? ";
          parameters.add(print);
        }
        if(criterion.length()==0){
          criterion += " and orgChg.status = '1'  and orgChg.print = '0' ";
        }
        hql = hql + criterion + " and orgChg.org.id " + securityInfo.getGjjSecurityHqlSQL();
        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.uniqueResult();
      }
    });
    return num.intValue();
  }
  
  
}
