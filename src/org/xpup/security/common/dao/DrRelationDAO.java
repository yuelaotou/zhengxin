package org.xpup.security.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.security.common.domain.DrRelation;

public class DrRelationDAO extends HibernateDaoSupport {

  public Serializable insert(DrRelation drRelation) throws BusinessException {
    return getHibernateTemplate().save(drRelation);
  }

  public List queryByUsername(String username, String daInnerName) {

    String hql = "select drRelation from DrRelation drRelation inner join drRelation.role role inner join role.users user where user.username = ? and drRelation.dataAccess.innerName = ?";
    return getHibernateTemplate().find(hql,
        new String[] { username, daInnerName });
  }

  public List queryByRoleId(Serializable roleId) {
    Validate.notNull(roleId, "参数roleId不能为空。");

    String hql = "from DrRelation drRelation where drRelation.role.id = ?";
    return getHibernateTemplate().find(hql, roleId);
  }

  public void deleteByRoleId(final Serializable roleId) {
    Validate.notNull(roleId, "参数roleId不能为空。");

    super.getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "delete DrRelation drRelation where drRelation.role.id = :roleId";
        session.createQuery(hql).setParameter("roleId", roleId).executeUpdate();
        return null;
      }
    });
  }

}
