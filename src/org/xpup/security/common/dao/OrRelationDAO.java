package org.xpup.security.common.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.security.common.domain.OrRelation;

public class OrRelationDAO extends HibernateDaoSupport {

  public Serializable insert(OrRelation orRelation) {
    return getHibernateTemplate().save(orRelation);
  }

  public void deleteAllByRoleId(final Serializable roleId) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "delete OrRelation orRelation where orRelation.role.id = :roleId";
        session.createQuery(hql).setParameter("roleId", roleId).executeUpdate();
        return null;
      }
    });
  }
}
