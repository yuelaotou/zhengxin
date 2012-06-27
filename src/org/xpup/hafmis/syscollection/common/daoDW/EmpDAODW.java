package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;

public class EmpDAODW extends HibernateDaoSupport{
  /**
   * 查找ba002
   * 
   */
  public EmpInfo queryEmpInfoById(Integer id) {
    Validate.notNull(id);
    return (EmpInfo) getHibernateTemplate().get(EmpInfo.class, id);
  }
  /**
   * 插入记录
   * @param empInfo
   * @return
   */
  public Serializable insert(EmpInfo empInfo){
    Serializable id = null;
    try{    
    Validate.notNull(empInfo);
    id = getHibernateTemplate().save(empInfo);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 插入记录
   * 
   * @param emp
   * @return
   */
  public Serializable insert(Emp emp) {
    Serializable id = null;
    try {
      Validate.notNull(emp);
      id = getHibernateTemplate().save(emp);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   *根据orgId删除emp 
   * orgId
   * 
   */
  public void deleteEmp_sy(final Integer orgId){
    try{
  getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
           String sql="delete Emp emp where emp.orgid=?";          
           session.createQuery(sql).setInteger(0, orgId.intValue()).executeUpdate();
            return null;
        }
      }  
  );
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * 根据单位编号和职工编号查询copy归集
   * 
   * @param empId
   * @param orgId
   * @return
   */
  public List queryByEmpId_lg(final Integer empId, final Integer orgId) {
    Validate.notNull(orgId);
    List empList = null;

    try {
      empList = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "from Emp emp ";
              Vector parameters = new Vector();
              String criterion = "";

              if (empId != null) {
                criterion += "  emp.empId=?  and  ";
                parameters.add(empId);
              }
              if (orgId != null) {
                criterion += "  emp.org.id=?  and  ";
                parameters.add(orgId);
              }

              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf(" and "));

              hql = hql + criterion;

              Query query = session.createQuery(hql);
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
    return empList;
  }
  /**
   * 判断AA002表中是否存在与将要删除的职工empid相同的纪录
   * 
   * @return 如果存在相同的empid返回true，否则返回false。
   */
  public boolean getEmpidCount(final Integer empid,final Integer orgid) {
    Integer empCount = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(*) from Emp emp ";
            Vector parameters = new Vector();
            String criterion = "";
            if (empid != null&&orgid != null) {
              criterion = " where emp.empId = ? and emp.org.id!=?";
              parameters.add(new Integer(empid.toString()));
              parameters.add(new Integer(orgid.toString()));
            }
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));
            query.setParameter(1, parameters.get(1));
            return query.uniqueResult();
          }
        });
    if (empCount.intValue() >= 1) {
      return true;
    } else {
      return false;
    }
  }
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public Emp queryById(Integer id) {
    Validate.notNull(id);
    return (Emp) getHibernateTemplate().get(Emp.class, id);
  }
  /*
   * 删除AA002职工信息
   */
  public void deleteEmpByIdSL(Integer id) {
    try {

      Validate.notNull(id);
      Emp emp = queryById(id);
      getHibernateTemplate().delete(emp);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public EmpInfo queryEmpInfoById(Serializable id) {  
    Validate.notNull(id);
    return  (EmpInfo) getHibernateTemplate().get(EmpInfo.class,id);    
  }
  /*
   * 删除BA002信息
   */
    public void deleteEmpInfoByIdSL(Serializable id) {
      try {

        Validate.notNull(id);
        EmpInfo empInfo = queryEmpInfoById(id);
        getHibernateTemplate().delete(empInfo);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    /**
     * 王菱 根据单位ID 查询单位下的所有职工列表
     * 
     * @param orgid
     * @return
     */
    public List getEmpList_WL(final String orgid) {

      List list = null;
      try {
        list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {

            String hql = "from Emp emp  ";

            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null) {
              criterion += "emp.org.id = ? and ";
              parameters.add(new Integer(orgid));
            }

            if (criterion.length() != 0)
              criterion = "where  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;

            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }

            if (query0.list() == null) {
              List returnList = new ArrayList();
              return returnList;
            }
            return query0.list();
          }
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
      return list;
    }
    /**
     * copy归集
     * 判断同一职工姓名和证件号码的人是否在该单位存在：BA002、AA002关联，BA002中职工姓名和证件号码等于录入的值，AA002中的单位编码等于录入的值，是否存在这样的记录
     * param orgid param empName, param cardNum,证件号码 return emp
     */
    public List getEmp_WL(final String orgID, final String empName,
        final String cardNum,final Integer empId) {

      List list = null;
      try {
        list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {

            String hql = "from Emp emp  ";

            Vector parameters = new Vector();
            String criterion = "";

            if (orgID != null && !orgID.equals("")) {
              criterion += "emp.org.id = ? and ";
              parameters.add(new Integer(orgID));
            }

            if (empName != null && !empName.equals("")) {
              criterion += "emp.empInfo.name = ? and ";
              parameters.add(empName);
            }

            if (cardNum != null && !cardNum.equals("")) {
              criterion += "emp.empInfo.cardNum = ? and ";
              parameters.add(cardNum);
            }
            if (empId != null) {
              criterion += "emp.empId = ? and ";
              parameters.add(empId);
            }
            if (criterion.length() != 0)
              criterion = "where  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;

            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }

            if (query0.list() == null) {
              List returnList = new ArrayList();
              return returnList;
            }
            return query0.list();
          }
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
      return list;
    }
    /**
     *单位版职工开户职工编号修改的时候使用 
     * empId ,orgId,oldEmpId
     */
    public void changeEmpId_sy(String empId, String orgId, String oldEmpId) throws BusinessException,
        HibernateException, SQLException {
      try {
        Connection conn = getHibernateTemplate().getSessionFactory()
            .openSession().connection();
        CallableStatement cs = conn.prepareCall("{call CHANGEEMPID(?,?,?)}");
        cs.setInt(1, new Integer(empId).intValue());
        cs.setInt(2, new Integer(orgId).intValue());
        cs.setString(3, oldEmpId);

        cs.execute();
      } catch (Exception e) {
        throw new BusinessException("职工编号修改失败!!!");
      }
    }
    /**
     * 韩亮..根据此单位id和职工id来获得这个职工能够最多提取多少钱
     */
    public BigDecimal getTheEmployeeBalance(final int orgId, final int empId) {
      try {
        BigDecimal balance = (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                BigDecimal d = (BigDecimal) session
                    .createQuery(
                        "select sum(e.curBalance+e.preBalance) from Emp e where e.org.id=? and e.empId=?")
                    .setInteger(0, orgId).setInteger(1, empId).uniqueResult();
                return d;
              }
            });
        return balance;
      } catch (Exception s) {

        s.printStackTrace();
      }
      return null;
    }
    /**
     * 吴洪涛 查询职工信息
     */

    public Emp queryByCriterions(final String empid, final String orgid) {

      Emp emp = null;
      emp = (Emp) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select emp from Emp emp  ";
          Vector parameters = new Vector();
          String criterion = "";
          if (empid != null && !empid.equals("")) {
            criterion += "emp.empId = ? and ";
            parameters.add(new Integer(empid));
          }
          if (orgid != null && !orgid.equals("")) {
            criterion += "emp.org.id = ? and ";
            parameters.add(new Integer(orgid));
          }
          if (criterion.length() != 0)
            criterion = " where  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query0 = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query0.setParameter(i, parameters.get(i));
          }
          return query0.uniqueResult();
        }
      });

      return emp;
    }
}
