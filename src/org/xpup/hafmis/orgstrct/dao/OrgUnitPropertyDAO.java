package org.xpup.hafmis.orgstrct.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.domain.OrgUnitProperty;
import org.xpup.hafmis.orgstrct.domain.enums.PropertyTypeEnum;

public class OrgUnitPropertyDAO extends HibernateDaoSupport {

  public List queryByCriterions(final Serializable orgUnitId,
      final PropertyTypeEnum type) {

    List props = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from OrgUnitProperty oup ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgUnitId != null) {
          criterion += "oup.organizationUnit.id = ? and ";
          parameters.add(orgUnitId);
        }

        if (type != null) {
          criterion += "oup.ouptItem.type = ? and ";
          parameters.add(new Integer(type.getValue()));
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
    return props;
  }

  public Serializable insert(OrgUnitProperty orgUnitProperty) {
    return getHibernateTemplate().save(orgUnitProperty);
  }

  public void deleteByOrgUnitId(final Serializable orgUnitId) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "delete OrgUnitProperty oup where oup.organizationUnit.id = :orgUnitId";
        session.createQuery(hql).setParameter("orgUnitId", orgUnitId)
            .executeUpdate();
        return null;
      }
    });
  }

}
