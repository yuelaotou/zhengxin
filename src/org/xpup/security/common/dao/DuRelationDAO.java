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
import org.xpup.security.common.domain.DuRelation;

public class DuRelationDAO extends HibernateDaoSupport {

  public Serializable insert(DuRelation duRelation) throws BusinessException {
    Validate.notNull(duRelation, "参数duRelation不能为空。");
    return super.getHibernateTemplate().save(duRelation);
  }

  public List queryByUsername(String username, String daInnerName) {
    Validate.notNull(username, "参数username不能为空。");

    String hql = "from DuRelation duRelation where duRelation.user.username = ? and duRelation.dataAccess.innerName = ?";
    return super.getHibernateTemplate().find(hql,
        new String[] { username, daInnerName });
  }

  public List queryByUserId(Serializable userId) {
    Validate.notNull(userId, "参数userId不能为空。");

    String hql = "from DuRelation duRelation where duRelation.user.id = ?";
    return super.getHibernateTemplate().find(hql, userId);
  }

  public void deleteByUserId(final Serializable userId) {
    Validate.notNull(userId, "参数userId不能为空。");

    super.getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "delete DuRelation duRelation where duRelation.user.id = :userId";
        session.createQuery(hql).setParameter("userId", userId).executeUpdate();
        return null;
      }
    });
  }
}
