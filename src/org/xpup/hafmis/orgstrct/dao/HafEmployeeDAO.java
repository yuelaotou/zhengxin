package org.xpup.hafmis.orgstrct.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.xpup.common.enums.OrderEnum;
import org.xpup.common.enums.SexEnum;
import org.xpup.hafmis.orgstrct.domain.enums.DutyEnum;
import org.xpup.security.common.dao.UserDAO;

public class HafEmployeeDAO extends UserDAO {

  public List queryByCriterions(final String username, final String realName,
      final String email, final SexEnum sex, final DutyEnum duty,
      final Boolean enabled, final Boolean locked, final String orderBy,
      final OrderEnum order, final int start, final int pageSize) {

    Validate.notNull(orderBy, "参数orderBy不能为空！");
    Validate.notNull(order, "参数order不能为空！");
    Validate
        .isTrue(
            orderBy
                .matches("employee\\.(id|username|realName|sex|duty|enabled|locked)$"),
            "参数orderBy("
                + orderBy
                + ")不符合要求！它必须是employee.id, employee.username, employee.realName, employee.sex, employee.duty, employee.enabled,employee.locked中的一个。");

    List emps = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from HafEmployee employee ";
        Vector parameters = new Vector();
        String criterion = "";

        if (username != null) {
          criterion += "employee.username like ? escape '/' and ";
          parameters.add("%" + username + "%");
        }

        if (realName != null) {
          criterion += "employee.realName like ? escape '/' and ";
          parameters.add("%" + realName + "%");
        }

        if (sex != null) {
          criterion += "employee.sex = ? and ";
          parameters.add(new Integer(sex.getValue()));
        }

        if (duty != null) {
          criterion += "employee.duty = ? and ";
          parameters.add(new Integer(duty.getValue()));
        }

        if (enabled != null) {
          criterion += "employee.enabled = ? and ";
          parameters.add(enabled);
        }

        if (locked != null) {
          criterion += "employee.locked = ? and ";
          parameters.add(locked);
        }

        if (email != null) {
          criterion += "employee.email like ? escape '/' and ";
          parameters.add("%" + email + "%");
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
    return emps;
  }

  public int queryCountByCriterions(final String username,
      final String realName, final String email, final SexEnum sex,
      final DutyEnum duty, final Boolean enabled, final Boolean locked) {

    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(employee.id) from HafEmployee employee ";
            Vector parameters = new Vector();
            String criterion = "";

            if (username != null) {
              criterion += "employee.username like ? escape '/' and ";
              parameters.add("%" + username + "%");
            }

            if (realName != null) {
              criterion += "employee.realName like ? escape '/' and ";
              parameters.add("%" + realName + "%");
            }

            if (sex != null) {
              criterion += "employee.sex = ? and ";
              parameters.add(new Integer(sex.getValue()));
            }

            if (duty != null) {
              criterion += "employee.duty = ? and ";
              parameters.add(new Integer(duty.getValue()));
            }

            if (enabled != null) {
              criterion += "employee.enabled = ? and ";
              parameters.add(enabled);
            }

            if (locked != null) {
              criterion += "employee.locked = ? and ";
              parameters.add(locked);
            }

            if (email != null) {
              criterion += "employee.email like ? escape '/' and ";
              parameters.add("%" + email + "%");
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
    return count.intValue();
  }

  //吴洪涛  2008－3－14  角色
  public List queryNameById( final String id) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select ca102.name " +
              " from ca102 ca102, ca103 ca103" +
              " where ca102.id = ca103.role_id"  ;

          String criterion = "";
          Vector parameters = new Vector();
           
         
          if (id != null && !"".equals(id)) {
            criterion += " ca103.user_id= ?  and ";
            parameters.add(id.trim());
          }
          if (criterion.length() != 0)
            criterion = " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.list();
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  
}
