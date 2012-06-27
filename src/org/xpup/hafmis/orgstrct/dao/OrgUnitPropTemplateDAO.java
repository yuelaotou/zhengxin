package org.xpup.hafmis.orgstrct.dao;

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
import org.xpup.common.enums.OrderEnum;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.exception.IllegalDuplicationException;
import org.xpup.hafmis.orgstrct.domain.OrgUnitPropTemplate;

public class OrgUnitPropTemplateDAO extends HibernateDaoSupport {

  public OrgUnitPropTemplate queryById(Serializable id) {
    Validate.notNull(id, "参数id不能为空");

    return (OrgUnitPropTemplate) getHibernateTemplate().get(
        OrgUnitPropTemplate.class, id);
  }

  public List queryByCriterions(final String name, final String description,
      final String orderBy, final OrderEnum order, final int start,
      final int pageSize) {

    Validate.notNull(orderBy, "参数orderBy不能为空！");
    Validate.notNull(order, "参数order不能为空！");
    Validate.isTrue(orderBy.matches("oupt\\.(id|name|innerName|description)$"),
        "参数orderBy(" + orderBy
            + ")不符合要求！它必须是oupt.id, oupt.name, oupt.innerName, oupt.description中的一个。");

    List oupts = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from OrgUnitPropTemplate oupt ";
        Vector parameters = new Vector();
        String criterion = "";

        if (name != null) {
          criterion += "oupt.name like ? escape '/' and ";
          parameters.add("%" + name + "%");
        }

        if (description != null) {
          criterion += "oupt.description like ? escape '/' and ";
          parameters.add("%" + description + "%");
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
    return oupts;
  }

  public int queryCountByCriterions(final String name, final String description) {

    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(oupt.id) from OrgUnitPropTemplate oupt ";
            Vector parameters = new Vector();
            String criterion = "";

            if (name != null) {
              criterion += "oupt.name like ? escape '/' and ";
              parameters.add("%" + name + "%");
            }

            if (description != null) {
              criterion += "oupt.description like ? escape '/' and ";
              parameters.add("%" + description + "%");
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

  public Serializable insert(OrgUnitPropTemplate orgUnitPropTemplate)
      throws BusinessException {
    Validate.notNull(orgUnitPropTemplate, "参数orgUnitPropTemplate不能为空！");
    Validate.notNull(orgUnitPropTemplate.getName(),
        "orgUnitPropTemplate.getName()不能为空！");

    int count = getHibernateTemplate()
        .find(
            "from OrgUnitPropTemplate oupt where lower(oupt.name) = ? or lower(oupt.innerName) = ?",
            new Object[] { orgUnitPropTemplate.getName().toLowerCase().trim(),
                orgUnitPropTemplate.getInnerName().toLowerCase().trim() })
        .size();
    if (count != 0) {
      throw new IllegalDuplicationException("模板名或内部名称出现重复，请使用其他名称！");
    }
    return getHibernateTemplate().save(orgUnitPropTemplate);
  }

  public void deleteById(Serializable id) throws BusinessException {
    Validate.notNull(id, "参数id不能为空");

    OrgUnitPropTemplate oupt = (OrgUnitPropTemplate) getHibernateTemplate()
        .get(OrgUnitPropTemplate.class, id);
    if (oupt == null) {
      throw new EntityNotFoundException("模板不存在，或已经被删除!");
    }
    getHibernateTemplate().delete(oupt);
  }

  public void checkBeforeUpdate(OrgUnitPropTemplate oupt)
      throws BusinessException {
    int count = getHibernateTemplate()
        .find(
            "from OrgUnitPropTemplate oupt where (lower(oupt.name) = ? or lower(oupt.innerName) = ?) and oupt.id <> ?",
            new Object[] { oupt.getName().toLowerCase().trim(),
                oupt.getInnerName().toLowerCase().trim(), oupt.getId() })
        .size();
    if (count != 0) {
      throw new IllegalDuplicationException("模板名或内部名称出现重复，请使用其他名称！");
    }
  }
}
