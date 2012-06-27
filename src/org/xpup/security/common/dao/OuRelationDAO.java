package org.xpup.security.common.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.security.common.domain.OuRelation;

public class OuRelationDAO extends HibernateDaoSupport {

  public Serializable insert(OuRelation ouRelation) {
    return getHibernateTemplate().save(ouRelation);
  }

  public void deleteAllByUserId(final Serializable userId) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "delete OuRelation ouRelation where ouRelation.user.id = :userId";
        session.createQuery(hql).setParameter("userId", userId).executeUpdate();
        return null;
      }
    });
  }

}
