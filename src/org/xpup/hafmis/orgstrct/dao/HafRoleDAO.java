package org.xpup.hafmis.orgstrct.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.xpup.common.enums.OrderEnum;
import org.xpup.security.common.dao.RoleDAO;

public class HafRoleDAO extends RoleDAO {

  public List queryByCriterions(final String name, final String description,
      final String orderBy, final OrderEnum order, final int start,
      final int pageSize) {

    Validate.isTrue(orderBy.matches("hafRole\\.(id|name)$"),
        "参数orderBy(" + orderBy
            + ")不符合要求！它必须是hafRole.id, hafRole.name中的一个。");

    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from HafRole hafRole ";
        Vector parameters = new Vector();
        String criterion = "";
        if (description != null) {
          criterion += "hafRole.description like ? escape '/' and ";
          parameters.add("%" + description + "%");
        }

        if (name != null) {
          criterion += "hafRole.name like ? escape '/' and ";
          parameters.add("%" + name + "%");
        }

        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        hql = hql + criterion + "order by " + orderBy + " " + order.getName();

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

  public int queryCountByCriterions(final String name, final String description) {

    Integer value = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(hafRole.id) from HafRole hafRole ";
            Vector parameters = new Vector();
            String criterion = "";
            if (description != null) {
              criterion += "hafRole.description = ? and ";
              parameters.add("%" + description + "%");
            }

            if (name != null) {
              criterion += "hafRole.name like ? escape '/' and ";
              parameters.add("%" + name + "%");
            }

            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));

            hql = hql + criterion;

            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            return query.uniqueResult();
          }
        });

    return value.intValue();
  }

}
