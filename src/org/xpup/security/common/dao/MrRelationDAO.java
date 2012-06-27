package org.xpup.security.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.security.common.domain.MrRelation;

public class MrRelationDAO extends HibernateDaoSupport {

  public List queryByCriterions(final Serializable menuItemId,
      final Serializable roleId) {

    List mrRelations = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from MrRelation mrRelation where mrRelation.role.id = :roleId and mrRelation.menuItem.id = :menuItemId";
            return session.createQuery(hql).setParameter("roleId", roleId)
                .setParameter("menuItemId", menuItemId).list();
          }
        });

    return mrRelations;
  }

  public Serializable insert(MrRelation mrRelation) {
    return getHibernateTemplate().save(mrRelation);
  }

  public void deleteAllByRoleId(final Serializable roleId) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "delete MrRelation mrRelation where mrRelation.role.id = :roleId";
        session.createQuery(hql).setParameter("roleId", roleId).executeUpdate();
        return null;
      }
    });
  }
}
