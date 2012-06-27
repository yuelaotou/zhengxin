package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.EmpInfoPick;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickCheckDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickInterestReteDTO;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

/**
 * 提取尾表
 * 
 * @author 李娟 2007-6-21
 */
public class PickTailDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public PickTail queryById(Serializable id) {
    Validate.notNull(id);
    return (PickTail) getHibernateTemplate().get(PickTail.class, id);
  }

  /**
   * 李文浩 根据单位编号和职工编号查询出此职工的的利息--
   */
  public BigDecimal getTheEmpInterest(final int headId, final int orgId,
      final int empId) {
    try {
      BigDecimal totalInterest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              BigDecimal total = (BigDecimal) session
                  .createQuery(
                      "select sum(t.pickPreInterest+t.pickCurInterest) from PickHead h ,PickTail t where h.id=t.pickHead.id and h.id=? and h.org.id=? and t.empId=?")
                  .setInteger(0, headId).setInteger(1, orgId).setInteger(2,
                      empId).uniqueResult();
              return total;
            }
          });
      return totalInterest;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 李文浩 根据单位编号和职工编号查询出此职工的的余额
   */
  public BigDecimal getTheEmpBalance(final int headId, final int orgId,
      final int empId) {
    try {
      BigDecimal totalInterest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              BigDecimal total = (BigDecimal) session
                  .createQuery(
                      "select sum(t.pickCurBalance+t.pickPreBalance) from PickHead h ,PickTail t where h.id=t.pickHead.id and h.id=? and h.org.id=? and t.empId=?")
                  .setInteger(0, headId).setInteger(1, orgId).setInteger(2,
                      empId).uniqueResult();
              return total;
            }
          });
      return totalInterest;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 获得此职工提取的总钱数
   */
  public List getEmployeePickCountMoney(final String empId, final String time,
      final String reason) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select new PickTail(t,h.settDate) from PickHead h,PickTail t where t.pickHead.id = h.id and h.pickSatatus=5 and";
          String criterion = "";
          List parameter = new ArrayList();
          if (empId != null && !empId.equals("")) {
            criterion = criterion + " t.empId = ? and ";
            parameter.add(new Integer(empId));
          }
          if (time != null && !time.equals("")) {
            criterion = criterion + "h.settDate = ? and ";
            parameter.add(time);
          }
          if (reason != null && !reason.equals("")) {
            criterion = criterion + "t.pickReason = ? and ";
            parameter.add(new BigDecimal(reason));
          }
          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameter.size(); i++) {
            query.setParameter(i, parameter.get(i));
          }
          return query.list();
        }
      });
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  public int getEmployeePickInfoCount(final String empId, final String time,
      final String reason) {
    int count = 0;
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select new PickTail(t,h.settDate) from PickHead h,PickTail t where t.pickHead.id = h.id and h.pickSatatus=5 and";
          String criterion = "";
          List parameter = new ArrayList();
          if (empId != null && !empId.equals("")) {
            criterion = criterion + " t.empId = ? and ";
            parameter.add(new Integer(empId));
          }
          if (time != null && !time.equals("")) {
            criterion = criterion + "h.settDate = ? and ";
            parameter.add(time);
          }
          if (reason != null && !reason.equals("")) {
            criterion = criterion + "t.pickReason = ? and ";
            parameter.add(new BigDecimal(reason));
          }
          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameter.size(); i++) {
            query.setParameter(i, parameter.get(i));
          }
          return query.list();
        }
      });
      count = list.size();
      return count;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return count;
  }

  /**
   * 李文浩.. 根据职工编号显示职工的提取信息
   */
  public List getEmployeePickInfo(final String empId, final String time,
      final String reason, final String orderBy, final String order,
      final int start, final int pageSize) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select new PickTail(t,h.settDate) from PickHead h,PickTail t where t.pickHead.id = h.id and h.pickSatatus=5 and";
          String criterion = "";
          List parameter = new ArrayList();
          if (empId != null && !empId.equals("")) {
            criterion = criterion + " t.empId = ? and ";
            parameter.add(new Integer(empId));
          }
          if (time != null && !time.equals("")) {
            criterion = criterion + "h.settDate = ? and ";
            parameter.add(time);
          }
          if (reason != null && !reason.equals("")) {
            criterion = criterion + "t.pickReason = ? and ";
            parameter.add(new BigDecimal(reason));
          }
          String ob = orderBy;
          if (ob == null)
            ob = " h.settDate ";
          String or = order;
          if (or == null)
            or = "desc";
          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + "order by " + ob + " " + or;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameter.size(); i++) {
            query.setParameter(i, parameter.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            ;
          query.setMaxResults(pageSize);
          List l = query.list();
          return l;
        }
      });
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 李文浩.. 根据单位编号获得此完成提取列表显示所有职工的职工编号
   */
  public List getAllEmpId(final int headId) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {// 这个List一定不会为null,
            // 因为它是在完成提取中用的方法...如果没有数据的话完成提取的按钮就禁用的
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List l = session.createQuery(
                  "select p.empId from PickTail p where p.pickHead.id=?")
                  .setInteger(0, headId).list();
              return l;
            }
          });
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 根据职工id和头表id来删除在尾表中的此职工
   */
  public void deleteByEmpIdAndHeadId(final int empId, final int headId) {
    try {
      final PickTail t = findPickTailByHeadIdAndEmpId(headId, empId);// 获得尾表要删除的那条记录
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          session.delete(t);
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  /**
   * 李文浩..根据尾表的head_id来获得数据..看看是否为最后一条的数据
   */
  public List findDataByHeadId(final int headId) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          PickTail s = new PickTail();
          List l = session.createQuery(
              "from PickTail p where p.pickHead.id = ?").setInteger(0, headId)
              .list();
          return l;
        }
      });
      return list;
    } catch (Exception s) {
      s.printStackTrace();
      return null;
    }
  }

  /**
   * 李文浩--获得第一页数据的方法
   */
  public List findFirstPageData(final String orgCode, final String orderBy,
      final String order, final int start, final int pageSize)
      throws NumberFormatException {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select new PickTail(pt,emp) from PickTail pt,Emp emp where pt.pickHead.pickSatatus= 1 and pt.pickHead.org.id= emp.org.id and pt.empId = emp.empId and pt.pickHead.org.id = ? ";
          String ob = orderBy;
          if (ob == null && ob.equals(""))
            ob = " pt.id ";
          String od = order;
          if (od == null && od.equals(""))
            od = "DESC";
          hql = hql + "order by " + ob + " " + od;
          List t = session.createQuery(hql).setParameter(0,
              new Integer(orgCode)).setFirstResult(start).setMaxResults(
              pageSize).list();
          session.clear();
          return t;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 李文浩--获得第一页数据的方法
   */
  public List findFirstPageData_LY(final String orgCode, final String orderBy,
      final String order, final int start, final int pageSize)
      throws NumberFormatException {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String hql = "select pt.empId,pt.empName,emp.empInfo.cardNum,pt.pickSalary,pt.pickInterest,pt.pickReason,pt.pickType,pt.pickHead.id,pt.total,pt.reserveaA,pt.photourl from PickTail pt,Emp emp where pt.pickHead.pickSatatus= 1 and pt.pickHead.org.id= emp.org.id and pt.empId = emp.empId and pt.pickHead.org.id =? ";
          String ob = orderBy;
          if (ob == null && ob.equals(""))
            ob = " pt.id ";
          String od = order;
          if (od == null && od.equals(""))
            od = "DESC";
          hql = hql + "order by " + ob + " " + od;
          Query query = session.createQuery(hql).setParameter(0,
              new Integer(orgCode)).setFirstResult(start).setMaxResults(
              pageSize);
          Iterator it = query.iterate();
          List t = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            PickTail pickTail = new PickTail();
            Emp emp = new Emp();
            EmpInfo empInfo = new EmpInfo();
            pickTail.setEmpId(new Integer(obj[0].toString()));
            pickTail.setEmpName(obj[1].toString());
            empInfo.setName(obj[1].toString());
            empInfo.setCardNum(obj[2].toString());
            emp.setEmpInfo(empInfo);
            pickTail.setEmp(emp);
            pickTail.setPickSalary(new BigDecimal(obj[3].toString()));
            pickTail.setPickInterest(new BigDecimal(obj[4].toString()));
            pickTail.setPickReason(new BigDecimal(obj[5].toString()));
            try {
              if (pickTail.getPickReason().intValue() <= 6) {
                pickTail.setReason(BusiTools.getBusiValue(pickTail
                    .getPickReason().intValue(), BusiConst.SOMEPICK));
              } else if (pickTail.getPickReason().intValue() > 6) {
                pickTail.setReason(BusiTools.getBusiValue(pickTail
                    .getPickReason().intValue(), BusiConst.DISTROYPICK));
              }
              pickTail.setPickType(new BigDecimal(obj[6].toString()));
              pickTail.setPickDisplayType(BusiTools.getBusiValue(pickTail
                  .getPickType().intValue(), BusiConst.PICKUPTYPE));

            } catch (Exception e) {
              e.printStackTrace();
            }
            pickTail.getPickHead().setId(new Integer(obj[7].toString()));
            pickTail.setTotal(new BigDecimal(obj[8].toString()));
            if (obj[9] != null) {
              pickTail.setReserveaA(obj[9].toString());
            } else {
              pickTail.setReserveaA("");
            }
            if (obj[10] != null) {
              pickTail.setReserveaB(obj[10].toString());
            } else {
              pickTail.setReserveaB("");
            }
            t.add(pickTail);
          }
          // session.clear();
          return t;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public int findFirstPageDataCount(final String orgCode)
      throws NumberFormatException {
    Integer list = null;
    int count = 0;
    try {
      list = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(aa307.id) from AA307 aa307,AA306 aa306  where aa307.pickhead_id=aa306.id and aa306.pick_satatus=1 and  aa306.org_id = ? ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, new Integer(orgCode));
          Iterator it = query.list().iterator();
          Integer counttemp = new Integer(0);
          Object obj = null;
          if (it.hasNext()) {
            obj = (Object) it.next();
            counttemp = new Integer(obj.toString());
          }
          return counttemp;
        }
      });
      count = list.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 李文浩 。。。超连接查询职工对应的数据
   * 
   * @param orgCode
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws NumberFormatException
   */
  public List findTheOrgAllEmployee(final String headId, final String orderBy,
      final String order, final int start, final int pageSize)
      throws NumberFormatException {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select new PickTail(pt,emp) from PickTail pt,Emp emp where pt.pickHead.org.id= emp.org.id and pt.empId = emp.empId and pt.pickHead.id = ?";
          String ob = orderBy;
          if (ob == null && ob.equals(""))
            ob = " pt.id ";
          String od = order;
          if (od == null && od.equals(""))
            od = "DESC";
          hql = hql + "order by " + ob + " " + od;
          List t = session.createQuery(hql)
              .setParameter(0, new Integer(headId)).setFirstResult(start)
              .setMaxResults(pageSize).list();
          session.clear();
          return t;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 李文浩 。。。超连接查询职工对应的数据
   * 
   * @param orgCode
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws NumberFormatException
   */
  public List findTheOrgAllEmployee_LY(final String headId,
      final String orderBy, final String order, final int start,
      final int pageSize) throws NumberFormatException {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select pt.empId,pt.empName,emp.empInfo.cardNum,pt.pickSalary,pt.pickInterest,pt.pickReason,pt.pickType,pt.pickHead.id,pt.photourl,pt.reserveaA from PickTail pt,Emp emp where pt.pickHead.org.id= emp.org.id and pt.empId = emp.empId and pt.pickHead.id = ?";
          String ob = orderBy;
          if (ob == null && ob.equals(""))
            ob = " pt.id ";
          String od = order;
          if (od == null && od.equals(""))
            od = "DESC";
          hql = hql + "order by " + ob + " " + od;

          Query query = session.createQuery(hql).setParameter(0,
              new Integer(headId)).setFirstResult(start)
              .setMaxResults(pageSize);
          session.clear();
          Iterator it = query.iterate();
          List t = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            PickTail pickTail = new PickTail();
            Emp emp = new Emp();
            EmpInfo empInfo = new EmpInfo();
            pickTail.setEmpId(new Integer(obj[0].toString()));
            pickTail.setEmpName(obj[1].toString());
            empInfo.setName(obj[1].toString());
            empInfo.setCardNum(obj[2].toString());
            emp.setEmpInfo(empInfo);
            pickTail.setEmp(emp);
            pickTail.setPickSalary(new BigDecimal(obj[3].toString()));
            pickTail.setPickInterest(new BigDecimal(obj[4].toString()));
            pickTail.setPickReason(new BigDecimal(obj[5].toString()));
            try {
              if (pickTail.getPickReason().intValue() <= 6) {
                pickTail.setReason(BusiTools.getBusiValue(pickTail
                    .getPickReason().intValue(), BusiConst.SOMEPICK));
              } else if (pickTail.getPickReason().intValue() > 6) {
                pickTail.setReason(BusiTools.getBusiValue(pickTail
                    .getPickReason().intValue(), BusiConst.DISTROYPICK));
              }
              pickTail.setPickType(new BigDecimal(obj[6].toString()));
              pickTail.setPickDisplayType(BusiTools.getBusiValue(pickTail
                  .getPickType().intValue(), BusiConst.PICKUPTYPE));

            } catch (Exception e) {
              e.printStackTrace();
            }
            if (obj[8] != null) {
              pickTail.setPhotourl(obj[8].toString());
            } else {
              pickTail.setPhotourl("");
            }
            pickTail.getPickHead().setId(new Integer(obj[7].toString()));
            pickTail.setReserveaA(obj[9] == null ? "" : obj[9].toString());
            t.add(pickTail);
          }
          // session.clear();
          return t;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据head.id来来找到..尾表中所有的集合 因为这是一个删除的过程...这个集合一定是存在的
   */
  public List findPickTailByPickHeadId(final int headId) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List l = session.createQuery("from PickTail p where p.pickHead.id=?")
              .setInteger(0, headId).list();
          return l;
        }
      });
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 查找特殊提取
   */
  public List findPickTailBySpecialPick(final int headId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List l = session
              .createQuery(
                  "from PickTail p where p.pickHead.id=? and p.specialPick.id!=null ")
              .setInteger(0, headId).list();
          return l;
        }
      });

    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  /**
   * 全部删除的方法
   */
  public void deleteList(List list) {
    try {
      getHibernateTemplate().deleteAll(list);
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  public void deleteTail(final Integer picHeadId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete PickTail pickTail where pickTail.pickHead.id=?";
          session.createQuery(sql).setInteger(0, picHeadId.intValue())
              .executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 李文浩 查询尾表需要显示在浏览器上数据的总数
   */
  public Object[] queryPickTailCount(final int id) {
    Object obj[] = new Object[2];
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select new PickTail(pt,emp) from PickTail pt,Emp emp where pt.pickHead.pickSatatus= 1 and pt.pickHead.org.id= emp.org.id and pt.empId = emp.empId and pt.pickHead.org.id = ? ";
        return session.createQuery(hql).setInteger(0, id).list();
      }
    });
    obj[0] = new Integer(list.size());
    obj[1] = list;
    return obj;
  }

  /**
   * 李文浩 查询尾表需要显示在浏览器上数据的总数
   */
  public Object[] queryPickTailCount_LY(final int id) {
    Object obj[] = new Object[2];
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select  pt from PickTail pt where pt.pickHead.pickSatatus= 1  and pt.pickHead.org.id = ? ";
        return session.createQuery(hql).setInteger(0, id).list();
      }
    });
    obj[0] = new Integer(list.size());
    obj[1] = list;
    return obj;
  }

  /**
   * 李文浩 点超连接能够获得此头表id单位下所有职工的数量
   */
  public Object[] getTheOrgAllEmployee(final int id) {
    List list = new ArrayList();
    Object[] obj = new Object[2];
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select new PickTail(pt,emp) from PickTail pt,Emp emp where pt.pickHead.org.id= emp.org.id and pt.empId = emp.empId and pt.pickHead.id = ?";
        return session.createQuery(hql).setInteger(0, id).list();
      }
    });
    obj[0] = new Integer(list.size());
    obj[1] = list;
    return obj;
  }

  /**
   * ld_add 根据头表的id查询尾表的职工
   */
  public List getTailbyHeadid(final int id) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " from PickTail pt where pt.pickHead.id = ?";
        return session.createQuery(hql).setInteger(0, id).list();
      }
    });
    return list;
  }

  public List getTheOrgAllEmployee_LY_YG(final int id) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select sum(t.pick_pre_balance + t.pick_cur_balance),"
            + " sum(t.pick_pre_interest + t.pick_cur_interest),count(t.id)"
            + " from aa307 t where t.pickhead_id = ?";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, new Integer(id));
        Object obj[] = null;
        List t = new ArrayList();
        obj = (Object[]) query.uniqueResult();
        PickTail pickTail = new PickTail();
        pickTail.setSumBalance(new BigDecimal(obj[0].toString()));
        pickTail.setSumInterest(new BigDecimal(obj[1].toString()));
        pickTail.setSumPerson(Integer.parseInt(obj[2].toString()));
        t.add(pickTail);
        return t;
      }
    });
    return list;
  }

  public String getTheOrgAllEmployee_LY_YGA(final int id) {
    String s = "";
    s = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select count(t.id) from aa307 t where t.pickhead_id = ? and t.pick_type=1";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, new Integer(id));
        return query.uniqueResult().toString();
      }
    });
    return s;
  }

  /**
   * 李文浩 点超连接能够获得此头表id单位下所有职工的数量
   */
  public Object[] getTheOrgAllEmployee_LY(final int id) {
    List list = new ArrayList();
    Object[] obj = new Object[2];
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select pt.empId,pt.empName,emp.empInfo.cardNum,pt.pickSalary,pt.pickInterest,pt.pickReason,pt.pickType,pt.pickHead.id,pt.pickHead.pickBalance,pt.pickHead.distroyInterest,pt.pickCurBalance,pt.pickPreBalance,pt.pickPreInterest,pt.pickCurInterest,pt.pickHead.org.id,org.orgInfo.collectionBankId,pt.pickHead.docNum,pt.pickHead.noteNum "
            + ",pt.pickHead.reserveaA,pt.pickHead.settDate,pt.reserveaA from PickTail pt,Emp emp,Org org where org.id=pt.pickHead.org.id and pt.pickHead.org.id= emp.org.id and pt.empId = emp.empId and pt.pickHead.id = ?";
        Query query = session.createQuery(hql).setInteger(0, id);

        Iterator it = query.iterate();
        List t = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          PickTail pickTail = new PickTail();
          Emp emp = new Emp();
          EmpInfo empInfo = new EmpInfo();
          pickTail.setEmpId(new Integer(obj[0].toString()));
          pickTail.setEmpName(obj[1].toString());
          empInfo.setName(obj[1].toString());
          empInfo.setCardNum(obj[2].toString());
          emp.setEmpInfo(empInfo);
          pickTail.setEmp(emp);
          pickTail.setPickSalary(new BigDecimal(obj[3].toString()));
          pickTail.setPickInterest(new BigDecimal(obj[4].toString()));
          pickTail.setPickReason(new BigDecimal(obj[5].toString()));
          try {
            if (pickTail.getPickReason().intValue() <= 6) {
              pickTail.setReason(BusiTools.getBusiValue(pickTail
                  .getPickReason().intValue(), BusiConst.SOMEPICK));
            } else if (pickTail.getPickReason().intValue() > 6) {
              pickTail.setReason(BusiTools.getBusiValue(pickTail
                  .getPickReason().intValue(), BusiConst.DISTROYPICK));
            }
            pickTail.setPickType(new BigDecimal(obj[6].toString()));
            pickTail.setPickDisplayType(BusiTools.getBusiValue(pickTail
                .getPickType().intValue(), BusiConst.PICKUPTYPE));

          } catch (Exception e) {
            e.printStackTrace();
          }
          PickHead pickHead = new PickHead();
          Org org = new Org();
          OrgInfo orgInfo = new OrgInfo();
          org.setId(new BigDecimal(obj[14].toString()));
          orgInfo.setCollectionBankId(obj[15].toString());
          pickHead.setOrg(org);
          pickHead.getOrg().setOrgInfo(orgInfo);
          if (obj[16] != null) {
            pickHead.setDocNum(obj[16].toString());
          }
          if (obj[17] != null) {
            pickHead.setNoteNum(obj[17].toString());
          }
          if (obj[18] != null) {
            pickHead.setReserveaA(obj[18].toString());
          }
          if (obj[19] != null) {
            pickHead.setSettDate(obj[19].toString());
          }
          if (obj[20] != null) {
            pickTail.setReserveaA(obj[20].toString());
          }
          pickHead.setId(new Integer(obj[7].toString()));
          pickHead.setPickBalance(new BigDecimal(obj[8].toString()));
          pickHead.setDistroyInterest(new BigDecimal(obj[9].toString()));
          pickTail.setPickHead(pickHead);
          pickTail.setPickCurBalance(new BigDecimal(obj[10].toString()));
          pickTail.setPickPreBalance(new BigDecimal(obj[11].toString()));
          pickTail.setPickPreInterest(new BigDecimal(obj[12].toString()));
          pickTail.setPickCurInterest(new BigDecimal(obj[13].toString()));

          t.add(pickTail);
        }
        // session.clear();
        return t;
      }
    });
    obj[0] = new Integer(list.size());
    obj[1] = list;
    return obj;
  }

  /**
   * 李文浩.. 判断输入的职工编号是否在尾表里存在
   */
  public List findPickTailByEmpId(final int empId) {
    try {
      List obj = null;
      obj = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List l = session.createQuery("from PickTail p where p.empId=:empID")
              .setInteger("empID", empId).list();
          return l;
        }
      });
      if (!obj.isEmpty())
        return obj;
      return null;
    } catch (Exception s) {
      s.printStackTrace();
      // 如果出现异常..那么就是说明这条记录存在 return一个不为NULL
      return null;
    }
  }

  /**
   * 李文浩 根据此orgId和empId来判断此职工的状态是否小于5
   */
  public List findPickHeadStateByOrgIdAndEmpId(final int orgId, final int empId) {
    try {
      List obj = new ArrayList();
      obj = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List l = session
              .createQuery(
                  "select h from PickTail t,PickHead h where h.org.id=:orgId and t.empId=:empID and h.pickSatatus<5 and h.id=t.pickHead.id")
              .setInteger("orgId", orgId).setInteger("empID", empId).list();
          return l;
        }
      });
      return obj;
    } catch (Exception s) {
      s.printStackTrace();
      // 如果出现异常..那么就是说明这条记录存在 return一个不为NULL
      return null;
    }
  }

  /**
   * 判断这个职工是否为特殊提取 传递头表id和职工id...根据这两个id在尾表查找记录...此记录一定存在
   */
  public SpecialPick is_findEmployeeBySpecial(final int headId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Object obj = session.createQuery(
            "from PickTail p where p.pickHead.id=? and p.empId=?").setInteger(
            0, headId).setInteger(1, empId).uniqueResult();
        return obj;
      }
    });
    PickTail t = (PickTail) obj;
    SpecialPick special = t.getSpecialPick();
    if (special == null)
      return null;
    else {
      return special;
    }
  }

  /**
   * 根据尾职工id和头表的id来获得尾表的唯一一条记录
   */
  public PickTail findPickTailByHeadIdAndEmpId(final int headId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Object obj = session.createQuery(
            "from PickTail p where p.pickHead.id=? and p.empId=?").setInteger(
            0, headId).setInteger(1, empId).uniqueResult();// 这条记录一定是唯一的记录..也一定能存在..因为这个方法在删除里用
        return obj;
      }
    });
    return (PickTail) obj;
  }

  /**
   * 本年积数
   */
  public double getCurInt(final Integer orgId, final Integer empId,
      String moneyDate) {
    try {
      Object obj = new Object();
      final int number = this.getDay(moneyDate);
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select emp.curIntegral-emp.curBalance*(" + number
              + ") from Emp emp where emp.org.id=? and emp.empId=?";
          Object o = session.createQuery(hql).setInteger(0, orgId.intValue())
              .setInteger(1, empId.intValue()).uniqueResult();
          return o;
        }
      });
      double preInt = Double.parseDouble(obj.toString());
      return preInt;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return 999;
  }

  /**
   * 往年积数
   */
  public double getPreInt(final Integer orgId, final Integer empId,
      String moneyDate) {
    try {
      Object obj = new Object();
      final int number = this.getDay(moneyDate);// moneyDate必须是YYYYMMDD这样的类型...否则会报错
      System.out.println("number=" + number);
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select emp.preIntegral-emp.preBalance*(" + number
              + ") from Emp emp where emp.org.id=? and emp.empId=?";
          Object o = session.createQuery(hql).setInteger(0, orgId.intValue())
              .setInteger(1, empId.intValue()).uniqueResult();
          return o;
        }
      });
      double preInt = Double.parseDouble(obj.toString());
      return preInt;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return 999;
  }

  /**
   * 判断时间的方法
   */
  public int getDay(String moneyDate) {
    // Date date = new Date(new java.util.Date().getTime());
    // String year = moneyDate.substring(0,4);
    // String month = moneyDate.substring(4,6);
    // String day = moneyDate.substring(6,8);
    // Calendar convert = Calendar.getInstance();
    // Calendar result = Calendar.getInstance();
    // convert.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
    // //设置为本年的6月30日
    // result.set(Integer.parseInt(date.toString().substring(0,4)), 05,30);
    // if(convert.getTime().getTime()> result.getTime().getTime()){//设置为明年的
    // result.set(Integer.parseInt(date.toString().substring(0,4))+1, 05,30);
    // }
    // Timestamp one = new Timestamp(result.getTime().getTime());
    // Timestamp two = new Timestamp(convert.getTime().getTime());
    // int number =
    // BusiTools.minusDate(one.toString().substring(0,10),two.toString().substring(0,10));
    String nextJXdate = "";
    String sysDate = moneyDate;
    sysDate = sysDate.substring(0, 4) + "-" + sysDate.substring(4, 6) + "-"
        + sysDate.substring(6, sysDate.length());

    int empDays = Integer.parseInt(moneyDate.substring(4, 6));
    if (empDays < 7) {
      // 本年
      // 下一个结息日
      nextJXdate = sysDate.substring(0, 4) + "-06-30";
    } else if (empDays >= 7) {
      // 下一年
      // 下一个结息日
      nextJXdate = (Integer.parseInt(sysDate.substring(0, 4)) + 1) + "-06-30";
    }

    // 间隔天数
    int days = BusiTools.minusDate(nextJXdate, sysDate);
    return days;
    // return number;
  }

  /**
   * 根据职工的编号求出来往年和本年的余额 这个方法里面一定是有数据的
   */
  public Emp getPreBalanceAndCurBalance(final Integer orgId, final Integer empId) {
    try {// 这个方法是是在添加提取确定的时候而加入的..所以一定有记录...如果没有记录那么确定的按钮是灰色的
      Object obj = new Object();
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Object l = session.createQuery(
              "select e from Emp e where e.org.id=? and e.empId=?").setInteger(
              0, orgId.intValue()).setInteger(1, empId.intValue())
              .uniqueResult();
          return l;
        }
      });
      return (Emp) obj;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 根据职工的编号求出来往年和本年的余额 这个方法里面一定是有数据的
   */
  public EmpInfoPick getEmpInfo_LY(final Integer orgId, final Integer empId) {
    EmpInfoPick empInfo = null;
    try {// 这个方法是是在添加提取确定的时候而加入的..所以一定有记录...如果没有记录那么确定的按钮是灰色的
      empInfo = (EmpInfoPick) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              Query query = session
                  .createSQLQuery(
                      "select aa002.id ,aa002.pre_balance ,aa002.cur_balance from AA002 aa002 where   aa002.org_id=? and aa002.id=?")
                  .setInteger(0, orgId.intValue()).setInteger(1,
                      empId.intValue());
              Iterator it = query.list().iterator();
              Object obj[] = null;
              EmpInfoPick empInfotemp = new EmpInfoPick();
              if (it.hasNext()) {
                obj = (Object[]) it.next();
                empInfotemp.setEmpId(obj[0].toString());
                empInfotemp.setPre_balance(new BigDecimal(obj[1].toString()));
                empInfotemp.setCur_balance(new BigDecimal(obj[2].toString()));
              }
              return empInfotemp;
            }
          });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return empInfo;
  }

  /**
   * 获得最新的往年利率的
   */

  public double getPreRate(final String office) {
    BigDecimal pr = new BigDecimal(0.00);
    try {
      HafInterestRate hafInterestRate = null;

      hafInterestRate = (HafInterestRate) getHibernateTemplate().execute(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from HafInterestRate hafInterestRate where  hafInterestRate.id =(select max(hafInterestRate1.id) from HafInterestRate hafInterestRate1 where hafInterestRate1.officecode = ? and hafInterestRate1.isStart=2)";
          Query query = session.createQuery(hql);
          query.setParameter(0, office);
          return query.uniqueResult();

        }
      });
      try {
        pr = hafInterestRate.getPreRate();
      } catch (Exception s) {
      }
      return Double.parseDouble(pr.toString());
    } catch (Exception s) {
      s.printStackTrace();
      return Double.parseDouble(pr.toString());
    }
  }

  /**
   * r.preRate 获得最新本年利率
   */

  public double getCurRate(final String office) {
    BigDecimal cr = new BigDecimal(0.00);
    try {
      HafInterestRate hafInterestRate = null;

      hafInterestRate = (HafInterestRate) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "from HafInterestRate hafInterestRate where  hafInterestRate.id =(select max(hafInterestRate1.id) from HafInterestRate hafInterestRate1 where hafInterestRate1.officecode = ? and hafInterestRate1.isStart=2)";
              Query query = session.createQuery(hql);
              query.setParameter(0, office);
              return query.uniqueResult();
            }
          }

      );
      try {
        cr = hafInterestRate.getCurRate();
      } catch (Exception s) {
      }
      return Double.parseDouble(cr.toString());
    } catch (Exception s) {
      s.printStackTrace();
      return Double.parseDouble(cr.toString());
    }
  }

  /**
   * 插入AA101日志 此单位一共提取多少钱..
   */
  public BigDecimal getDebit(final int orgId) {
    try {
      BigDecimal de = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              BigDecimal total = (BigDecimal) session
                  .createQuery(
                      "select sum(t.pickCurBalance+t.pickPreBalance) from PickHead h ,PickTail t where h.id=t.pickHead.id and h.id=?")
                  .setInteger(0, orgId).uniqueResult();
              return total;
            }
          });
      return de;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 插入AA101日志 此单位一共提取多少利息..
   */
  public BigDecimal getInterest(final int orgId) {
    try {
      BigDecimal de = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {// 利息在添加提取的时候 自己给添加为0...如果这地方出错了 添加找结果去
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              BigDecimal total = (BigDecimal) session
                  .createQuery(
                      "select sum(t.pickCurInterest+t.pickPreInterest) from PickHead h ,PickTail t where h.id=t.pickHead.id and h.id=?")
                  .setInteger(0, orgId).uniqueResult();
              return total;
            }
          });
      return de;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 往年分段利息
   */
  // public double getPreSub(final int orgId,final int empId){
  // Object obj = getHibernateTemplate().execute(
  // new HibernateCallback(){
  // public Object doInHibernate(Session session) throws HibernateException,
  // SQLException {
  // Emp e = null;
  // String hql="select
  // e.preIntegralSubA*e.preRateA+e.preIntegralSubB*e.preRateB+e.preIntegralSubC*e.preRateC
  // from Emp e where e.org.id=? and e.empId=?";
  // Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
  // empId).uniqueResult();
  // return l;
  // }
  // }
  // );
  // if(obj!=null){
  // return Double.parseDouble(obj.toString());
  // }else{
  // return 0.00;
  // }
  //     
  // }
  /**
   * 往年分段利息A
   */
  public BigDecimal getPreSubA(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubA*e.preRateA from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 往年分段利息B
   */
  public BigDecimal getPreSubB(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubB*e.preRateB from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }

  }

  /**
   * 往年分段利息C
   */
  public BigDecimal getPreSubC(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubC*e.preRateC from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }

  }

  /**
   * 往年分段利息D
   */
  public BigDecimal getPreSubD(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubD*e.preRateD from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 往年分段利息E
   */
  public BigDecimal getPreSubE(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubE*e.preRateE from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 往年分段利息F
   */
  public BigDecimal getPreSubF(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubF*e.preRateF from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 往年分段利息G
   */
  public BigDecimal getPreSubG(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubG*e.preRateG from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 往年分段利息H
   */
  public BigDecimal getPreSubH(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubH*e.preRateH from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 往年分段利息I
   */
  public BigDecimal getPreSubI(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubI*e.preRateI from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 往年分段利息J
   */
  public BigDecimal getPreSubJ(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubJ*e.preRateJ from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 往年分段利息K
   */
  public BigDecimal getPreSubK(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubK*e.preRateK from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 往年分段利息L
   */
  public BigDecimal getPreSubL(final int orgId, final int empId) {
    Object obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Emp e = null;
        String hql = "select e.preIntegralSubL*e.preRateL from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息
   */
  // public double getCurSub(final int orgId,final int empId){
  // Object obj = new Object();
  // obj = getHibernateTemplate().execute(
  // new HibernateCallback(){
  // public Object doInHibernate(Session session) throws HibernateException,
  // SQLException {
  // String hql="select
  // e.curIntegralSubA*e.curRateA+e.curIntegralSubB*e.curRateB+e.curIntegralSubC*e.curRateC
  // from Emp e where e.org.id=? and e.empId=?";
  // Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
  // empId).uniqueResult();
  // return l;
  // }
  // }
  // );
  // if(obj!=null){
  // return Double.parseDouble(obj.toString());
  // }else{
  // return 0.00;
  // }
  // }
  /**
   * 本年分段利息A
   */
  public BigDecimal getCurSubA(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubA*e.curRateA from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息B
   */
  public BigDecimal getCurSubB(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubB*e.curRateB from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息C
   */
  public BigDecimal getCurSubC(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubC*e.curRateC from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息D
   */
  public BigDecimal getCurSubD(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubD*e.curRateD from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息E
   */
  public BigDecimal getCurSubE(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubE*e.curRateE from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息F
   */
  public BigDecimal getCurSubF(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubF*e.curRateF from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息G
   */
  public BigDecimal getCurSubG(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubG*e.curRateG from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息H
   */
  public BigDecimal getCurSubH(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubH*e.curRateH from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息I
   */
  public BigDecimal getCurSubI(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubI*e.curRateI from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息J
   */
  public BigDecimal getCurSubJ(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubJ*e.curRateJ from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息K
   */
  public BigDecimal getCurSubK(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubK*e.curRateK from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 本年分段利息L
   */
  public BigDecimal getCurSubL(final int orgId, final int empId) {
    Object obj = new Object();
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select e.curIntegralSubL*e.curRateL from Emp e where e.org.id=? and e.empId=?";
        Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1,
            empId).uniqueResult();
        return l;
      }
    });
    if (obj != null) {
      return new BigDecimal(obj.toString()).divide(new BigDecimal(365), 2,
          BigDecimal.ROUND_HALF_DOWN);
    } else {
      return new BigDecimal(0.00);
    }
  }

  /**
   * 计算年提取的金额..
   */
  public List getYearPickBalance(final int empId) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(h.id),sum(t.pickCurBalance+t.pickPreBalance) from PickHead h,PickTail t "
              + "where t.pickHead.id = h.id and substr(h.settDate,0,4)=? and t.empId=?";
          Time time = new Time(new java.util.Date().getTime());
          String t = time.toLocaleString().substring(0, 4);
          List l = session.createQuery(hql).setString(0, t)
              .setInteger(1, empId).list();
          return l;
        }
      });
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  /**
   * 计算年提取的金额..次数
   */
  public List getYearPickBalanceAndCount(final int empId,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String bizDate = securityInfo.getUserInfo().getBizDate();
          String pickDate1 = "";
          String pickDate2 = "";
          String year = bizDate.substring(0, 4);
          String month = bizDate.substring(4, 6);
          if (Integer.parseInt(month) >= 1 && Integer.parseInt(month) <= 6) {
            pickDate1 = (Integer.parseInt(year) - 1) + "07" + "01";
            pickDate2 = year + "06" + "30";
          } else if (Integer.parseInt(month) >= 7
              && Integer.parseInt(month) <= 12) {
            pickDate1 = year + "07" + "01";
            pickDate2 = (Integer.parseInt(year) + 1) + "06" + "30";
          }
          String hql = "select count(h.id),sum(t.pickCurBalance+t.pickPreBalance) from PickHead h,PickTail t "
              + "where t.pickHead.id = h.id and h.settDate between ? and ? and t.empId=?";
          // Time time = new Time(new java.util.Date().getTime());
          // String t = time.toLocaleString().substring(0,4);

          Query query = session.createQuery(hql);
          query.setParameter(0, pickDate1);
          query.setParameter(1, pickDate2);
          query.setParameter(2, new Integer(empId));

          return query.list();
        }
      });
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  /**
   * 李文浩 根据AA306表状态为１来取得尾表0AA307
   */
  public List findTailByHeadId(final PickHead head) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List result = session.createQuery(
              "from PickTail as t where t.pickHead.id=?").setInteger(0,
              Integer.parseInt(head.getId().toString())).list();
          if (result.isEmpty())
            return null;
          return result;
        }
      });
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 插入记录
   * 
   * @param pickTail
   * @return
   */
  public void save(PickTail pickTail) {
    try {
      getHibernateTemplate().save(pickTail);
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  public Serializable insert(PickTail pickTail) {
    Serializable id = null;
    try {
      Validate.notNull(pickTail);
      id = getHibernateTemplate().save(pickTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }

  /**
   * 提取情况按原因 prama orgid,officeid,bankid return list
   */
  public List queryPickupreason_sy(final String orgid, final String officeid,
      final String bankid, final String date, final SecurityInfo securityInfo,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select aa307.pick_reason as pickreason,(sum(aa307.pick_pre_balance)+sum(aa307.pick_cur_balance)) as sumpick,count(aa307.id) as countpeople from AA307 aa307, AA306 aa306, AA001 aa001, AA101 aa101,BB105 bb105,BB101 bb101 ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgid != null && !orgid.equals("")) {
            criterion += " aa306.org_id = ?  and ";
            parameters.add(new Integer(orgid));
          }
          if (officeid != null && !officeid.equals("")) {
            criterion += " aa101.officeCode = ?  and ";
            parameters.add(officeid);
          }
          if (bankid != null && !bankid.equals("")) {
            criterion += " aa101.moneyBank = ?  and ";
            parameters.add(new Integer(bankid));
          }
          if (date != null && !date.equals("")) {
            criterion += " aa101.sett_date = ?  and ";
            parameters.add(date);
          }
          if (criterion.length() != 0)
            criterion = " where aa306.org_id "
                + securityInfo.getGjjSecuritySQL()
                + " and aa307.pickhead_id = aa306.id and aa306.org_id=aa001.id and aa001.id=aa101.org_id and"
                + " aa101.officeCode=bb101.id and aa101.moneyBank=bb105.coll_bank_id  and aa306.pick_satatus = 5 and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = "pickreason";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by aa307.pick_reason " + " order by "
              + ob + " " + order;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          query.setFirstResult(start);
          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 提取情况按原因 prama orgid,officeid,bankid return list
   */
  public List countPickupreason_sy(final String orgid, final String officeid,
      final String bankid, final String date, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select aa307.pick_reason as pickreason,(sum(aa307.pick_pre_balance)+sum(aa307.pick_cur_balance)) as sumpick,count(aa307.id) as countpeople from AA307 aa307, AA306 aa306, AA001 aa001, AA101 aa101,BB105 bb105,BB101 bb101 ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgid != null && !orgid.equals("")) {
            criterion += " aa306.org_id = ?  and ";
            parameters.add(new Integer(orgid));
          }
          if (officeid != null && !officeid.equals("")) {
            criterion += " aa101.officeCode = ?  and ";
            parameters.add(officeid);
          }
          if (bankid != null && !bankid.equals("")) {
            criterion += " aa101.moneyBank = ?  and ";
            parameters.add(new Integer(bankid));
          }
          if (date != null && !date.equals("")) {
            criterion += " aa101.sett_date = ?  and ";
            parameters.add(date);
          }
          if (criterion.length() != 0)
            criterion = " where aa306.org_id "
                + securityInfo.getGjjSecuritySQL()
                + " and aa307.pickhead_id = aa306.id and aa306.org_id=aa001.id and aa001.id = aa101.org_id and"
                + " aa101.officeCode=bb101.id and aa101.moneyBank=bb105.coll_bank_id  and aa306.pick_satatus = 5 and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion + " group by aa307.pick_reason ";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 得到分段的利息与积数
   * 
   * @param orgId
   * @param empId
   * @return
   * @author 付云峰
   */
  public PickInterestReteDTO queryInterestAndRete(final Integer orgId,
      final Integer empId) {
    PickInterestReteDTO pickInterestReteDTO = null;
    pickInterestReteDTO = (PickInterestReteDTO) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select "
                + "a.pre_integral_sub_a, a.cur_integral_sub_a, a.pre_rate_a, a.cur_rate_a,"
                + "a.pre_integral_sub_b, a.cur_integral_sub_b, a.pre_rate_b, a.cur_rate_b,"
                + "a.pre_integral_sub_c, a.cur_integral_sub_c, a.pre_rate_c, a.cur_rate_c,"
                + "a.pre_integral_sub_d, a.cur_integral_sub_d, a.pre_rate_d, a.cur_rate_d,"
                + "a.pre_integral_sub_e, a.cur_integral_sub_e, a.pre_rate_e, a.cur_rate_e,"
                + "a.pre_integral_sub_f, a.cur_integral_sub_f, a.pre_rate_f, a.cur_rate_f,"
                + "a.pre_integral_sub_g, a.cur_integral_sub_g, a.pre_rate_g, a.cur_rate_g,"
                + "a.pre_integral_sub_h, a.cur_integral_sub_h, a.pre_rate_h, a.cur_rate_h,"
                + "a.pre_integral_sub_i, a.cur_integral_sub_i, a.pre_rate_j, a.cur_rate_i,"
                + "a.pre_integral_sub_j, a.cur_integral_sub_j, a.pre_rate_j, a.cur_rate_j,"
                + "a.pre_integral_sub_k, a.cur_integral_sub_k, a.pre_rate_k, a.cur_rate_k,"
                + "a.pre_integral_sub_l, a.cur_integral_sub_l, a.pre_rate_l, a.cur_rate_l,"
                + "a.pre_integral,a.cur_integral "
                + " from aa002 a where a.org_id=? and a.id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, orgId);
            query.setParameter(1, empId);
            PickInterestReteDTO temp_pickInterestReteDTO = new PickInterestReteDTO();
            Object[] obj = (Object[]) query.uniqueResult();
            // 分段利息及积数A
            if (obj[0] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubA(new BigDecimal(obj[0]
                  .toString()));
            }
            if (obj[1] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubA(new BigDecimal(obj[1]
                  .toString()));
            }
            if (obj[2] != null) {
              temp_pickInterestReteDTO.setPreRateA(new BigDecimal(obj[2]
                  .toString()));
            }
            if (obj[3] != null) {
              temp_pickInterestReteDTO.setCurRateA(new BigDecimal(obj[3]
                  .toString()));
            }
            // 分段利息及积数B
            if (obj[4] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubB(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[5] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubB(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              temp_pickInterestReteDTO.setPreRateB(new BigDecimal(obj[6]
                  .toString()));
            }
            if (obj[7] != null) {
              temp_pickInterestReteDTO.setCurRateB(new BigDecimal(obj[7]
                  .toString()));
            }
            // 分段利息及积数C
            if (obj[8] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubC(new BigDecimal(obj[8]
                  .toString()));
            }
            if (obj[9] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubC(new BigDecimal(obj[9]
                  .toString()));
            }
            if (obj[10] != null) {
              temp_pickInterestReteDTO.setPreRateC(new BigDecimal(obj[10]
                  .toString()));
            }
            if (obj[11] != null) {
              temp_pickInterestReteDTO.setCurRateC(new BigDecimal(obj[11]
                  .toString()));
            }
            // 分段利息及积数D
            if (obj[12] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubD(new BigDecimal(
                  obj[12].toString()));
            }
            if (obj[13] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubD(new BigDecimal(
                  obj[13].toString()));
            }
            if (obj[14] != null) {
              temp_pickInterestReteDTO.setPreRateD(new BigDecimal(obj[14]
                  .toString()));
            }
            if (obj[15] != null) {
              temp_pickInterestReteDTO.setCurRateD(new BigDecimal(obj[15]
                  .toString()));
            }
            // 分段利息及积数E
            if (obj[16] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubE(new BigDecimal(
                  obj[16].toString()));
            }
            if (obj[17] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubE(new BigDecimal(
                  obj[17].toString()));
            }
            if (obj[18] != null) {
              temp_pickInterestReteDTO.setPreRateE(new BigDecimal(obj[18]
                  .toString()));
            }
            if (obj[19] != null) {
              temp_pickInterestReteDTO.setCurRateE(new BigDecimal(obj[19]
                  .toString()));
            }
            // 分段利息及积数F
            if (obj[20] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubF(new BigDecimal(
                  obj[20].toString()));
            }
            if (obj[21] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubF(new BigDecimal(
                  obj[21].toString()));
            }
            if (obj[22] != null) {
              temp_pickInterestReteDTO.setPreRateF(new BigDecimal(obj[22]
                  .toString()));
            }
            if (obj[23] != null) {
              temp_pickInterestReteDTO.setCurRateF(new BigDecimal(obj[23]
                  .toString()));
            }
            // 分段利息及积数G
            if (obj[24] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubG(new BigDecimal(
                  obj[24].toString()));
            }
            if (obj[25] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubG(new BigDecimal(
                  obj[25].toString()));
            }
            if (obj[26] != null) {
              temp_pickInterestReteDTO.setPreRateG(new BigDecimal(obj[26]
                  .toString()));
            }
            if (obj[27] != null) {
              temp_pickInterestReteDTO.setCurRateG(new BigDecimal(obj[27]
                  .toString()));
            }
            // 分段利息及积数H
            if (obj[28] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubH(new BigDecimal(
                  obj[28].toString()));
            }
            if (obj[29] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubH(new BigDecimal(
                  obj[29].toString()));
            }
            if (obj[30] != null) {
              temp_pickInterestReteDTO.setPreRateH(new BigDecimal(obj[30]
                  .toString()));
            }
            if (obj[31] != null) {
              temp_pickInterestReteDTO.setCurRateH(new BigDecimal(obj[31]
                  .toString()));
            }
            // 分段利息及积数I
            if (obj[32] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubI(new BigDecimal(
                  obj[32].toString()));
            }
            if (obj[33] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubI(new BigDecimal(
                  obj[33].toString()));
            }
            if (obj[34] != null) {
              temp_pickInterestReteDTO.setPreRateI(new BigDecimal(obj[34]
                  .toString()));
            }
            if (obj[35] != null) {
              temp_pickInterestReteDTO.setCurRateI(new BigDecimal(obj[35]
                  .toString()));
            }
            // 分段利息及积数J
            if (obj[36] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubJ(new BigDecimal(
                  obj[36].toString()));
            }
            if (obj[37] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubJ(new BigDecimal(
                  obj[37].toString()));
            }
            if (obj[38] != null) {
              temp_pickInterestReteDTO.setPreRateJ(new BigDecimal(obj[38]
                  .toString()));
            }
            if (obj[39] != null) {
              temp_pickInterestReteDTO.setCurRateJ(new BigDecimal(obj[39]
                  .toString()));
            }
            // 分段利息及积数K
            if (obj[40] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubK(new BigDecimal(
                  obj[40].toString()));
            }
            if (obj[41] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubK(new BigDecimal(
                  obj[41].toString()));
            }
            if (obj[42] != null) {
              temp_pickInterestReteDTO.setPreRateK(new BigDecimal(obj[42]
                  .toString()));
            }
            if (obj[43] != null) {
              temp_pickInterestReteDTO.setCurRateK(new BigDecimal(obj[43]
                  .toString()));
            }
            // 分段利息及积数L
            if (obj[44] != null) {
              temp_pickInterestReteDTO.setPreIntegralSubL(new BigDecimal(
                  obj[44].toString()));
            }
            if (obj[45] != null) {
              temp_pickInterestReteDTO.setCurIntegralSubL(new BigDecimal(
                  obj[45].toString()));
            }
            if (obj[46] != null) {
              temp_pickInterestReteDTO.setPreRateL(new BigDecimal(obj[46]
                  .toString()));
            }
            if (obj[47] != null) {
              temp_pickInterestReteDTO.setCurRateL(new BigDecimal(obj[47]
                  .toString()));
            }
            if (obj[48] != null) {
              temp_pickInterestReteDTO.setPreIntegral(new BigDecimal(obj[48]
                  .toString()));
            }
            if (obj[49] != null) {
              temp_pickInterestReteDTO.setCurIntegral(new BigDecimal(obj[49]
                  .toString()));
            }
            return temp_pickInterestReteDTO;
          }
        });
    return pickInterestReteDTO;
  }

  /**
   * 李文浩 根据职工编号和单位编号同时来获得此职工的最大金额
   */
  // 吴洪涛修改、、2008－6－2
  public BigDecimal getMaxPickMoney(final int orgId, final int empId,
      String reason) {
    try {
      Object obj = new Object();
      // if(getHibernateTemplate() == null)
      // System.out.println("****************");
      // else
      // System.out.println("-----------------");
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Object obj = session
              .createQuery(
                  "select sum(e.curBalance+e.preBalance) from Emp e where e.org.id=? and e.empId=?")
              .setInteger(0, orgId).setInteger(1, empId).uniqueResult();
          return obj;
        }
      });
      return new BigDecimal(obj.toString());
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  public List checkperson(final String card_num, final String card_num_two) {
    List returnlist = null;
    try {
      returnlist = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              List l = session
                  .createSQLQuery(
                      "select * from pl210 pl210 where pl210.status = 0 and (pl210.card_num=? or pl210.card_num=?) and pl210.card_num not like '9%'")
                  .setString(0, card_num).setString(1, card_num_two).list();
              return l;

            }
          });
      return returnlist;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String checkperson_yg(final String card_num, final String card_num_two) {
    String count = "";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql_pl110 = "select count(p110.contract_id) from pl110 p110,pl111 p111 where p110.contract_id=p111.contract_id and p111.contract_st!='12' and p111.contract_id!='13' and (p110.card_num='"
              + card_num + "' or p110.card_num='" + card_num_two + "' )";
          String sql_pl113 = "select count(p113.auxiliary_id) from pl113 p113,pl111 p111 where p111.contract_id=p113.contract_id and p111.contract_st!='12' and p111.contract_id!='13' and p113.status = '0' and (p113.card_num='"
              + card_num + "' or p113.card_num='" + card_num_two + "') ";
          String sql_other = "select count(t.id) from othersloan t where t.borrowercardnum = '"
              + card_num
              + "' or t.borrowercardnum = '"
              + card_num_two
              + "' or t.assiscardnum = '"
              + card_num
              + "' or t.assiscardnum = '" + card_num_two + "'";
          Query query_pl110 = session.createSQLQuery(sql_pl110);
          if (query_pl110.uniqueResult().toString().equals("0")) {
            Query query_pl113 = session.createSQLQuery(sql_pl113);
            if (query_pl113.uniqueResult().toString().equals("0")) {
              Query query_other = session.createSQLQuery(sql_other);
              if (query_other.uniqueResult().toString().equals("0")) {
                return "0";
              } else {
                return "1";
              }
            } else {
              return "1";
            }
          } else {
            return "1";
          }
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  public int getpickup_pl(final int orgId) {
    try {
      Object obj = new Object();
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Object obj = session
              .createSQLQuery(
                  "select count(t.id) from aa307 t ,aa306 a "
                      + "where t.pickhead_id=a.id and t.pick_reason='3' and a.pick_satatus<'3' and a.org_id=?")
              .setInteger(0, orgId).uniqueResult();
          return obj;
        }
      });
      return new Integer(obj.toString()).intValue();
    } catch (Exception s) {
      s.printStackTrace();
    }
    return 0;
  }

  public int getpickup_not_pl(final int orgId) {
    try {
      Object obj = new Object();
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Object obj = session
              .createSQLQuery(
                  "select count(t.id) from aa307 t ,aa306 a "
                      + "where t.pickhead_id=a.id and t.pick_reason<>'3' and a.pick_satatus<'3' and a.org_id=?")
              .setInteger(0, orgId).uniqueResult();
          return obj;
        }
      });
      return new Integer(obj.toString()).intValue();
    } catch (Exception s) {
      s.printStackTrace();
    }
    return 0;
  }

  public List getpickup_not_aa306(final int orgId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select distinct t.pick_reason from aa307 t ,aa306 a "
              + "where t.pickhead_id=a.id and a.pick_satatus<'3' and a.org_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(orgId));
          return query.list();
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public String getpickup_oper(final String id) {
    try {
      Object obj = new Object();
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Object obj = session
              .createSQLQuery(
                  "select t.reservea_a from aa101 t where  t.biz_type='D' and t.biz_id= ?")
              .setString(0, id).uniqueResult();
          return obj;
        }
      });
      return obj.toString();
    } catch (Exception s) {
      s.printStackTrace();
    }
    return "";
  }

  public String getpickup_check(final String id) {
    try {
      Object obj = new Object();
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Object obj = session
              .createSQLQuery(
                  "select t.registrations from aa101 t where  t.biz_type='D' and t.biz_id= ?")
              .setString(0, id).uniqueResult();
          return obj + "";
        }
      });
      return obj + "";
    } catch (Exception s) {
      s.printStackTrace();
    }
    return "";
  }

  public List getPickCheckList(final String orgid, final String orgname,
      final String begdate, final String enddate, final String checkbegdate,
      final String checkenddate, final String ischecked) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a6.org_id, b1.name, count(a7.id),"
              + " sum(a7.pick_pre_balance + a7.pick_cur_balance),"
              + " sum(a7.pick_pre_interest + a7.pick_cur_interest),"
              + " a6.sett_date, a6.reservea_c, a6.pick_satatus,"
              + " a6.reservea_a, a6.reservea_b, a6.id"
              + " from aa306 a6, aa307 a7, aa001 a1, ba001 b1"
              + " where a6.id = a7.pickhead_id and a1.orginfo_id = b1.id"
              + " and a1.id = a6.org_id and a6.pick_satatus='3' and a6.reservea_a=0 ";

          Vector parameters = new Vector();
          String criterion = "";
          if (orgid != null && !orgid.equals("")) {
            criterion += " a6.org_id = ?  and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " b1.name like ?  and ";
            parameters.add("%" + orgname + "%");
          }
          if (begdate != null && !begdate.equals("")) {
            criterion += " a6.sett_date >= ?  and ";
            parameters.add(begdate);
          }
          if (enddate != null && !enddate.equals("")) {
            criterion += " a6.sett_date <= ?  and ";
            parameters.add(enddate);
          }
          if (checkbegdate != null && !checkbegdate.equals("")) {
            criterion += " a6.reservea_c >= ?  and ";
            parameters.add(checkbegdate);
          }
          if (checkenddate != null && !checkenddate.equals("")) {
            criterion += " a6.reservea_c <= ?  and ";
            parameters.add(checkenddate);
          }
          if (ischecked != null && !ischecked.equals("")) {
            criterion += " a6.reservea_b = ?  and ";
            parameters.add(ischecked);
          }
          if (criterion.length() != 0)
            criterion = "and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          else
            criterion = criterion + "and a6.reservea_b=1 ";

          sql = sql + criterion + " group by a6.org_id, b1.name,"
              + " a6.sett_date, a6.reservea_c, a6.pick_satatus,"
              + " a6.reservea_a, a6.reservea_b, a6.id order by a6.sett_date DESC,a6.id asc ";

          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          PickCheckDTO dto = null;
          Object[] obj = null;
          List rlist = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            dto = new PickCheckDTO();
            if (obj[0] != null) {
              dto.setOrgid(BusiTools.convertTenNumber(obj[0].toString()));
            }
            if (obj[1] != null) {
              dto.setOrgname(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setPersoncount(Integer.parseInt(obj[2].toString()));
            }
            dto.setCorpus(new BigDecimal(obj[3].toString()));
            dto.setInterest(new BigDecimal(obj[4].toString()));
            dto.setCorpusInterest(new BigDecimal(obj[3].toString())
                .add(new BigDecimal(obj[4].toString())));
            if (obj[5] != null) {
              dto.setPickdate(obj[5].toString());
            }
            if (obj[6] != null) {
              dto.setCheckdate(obj[6].toString());
            }
            if (obj[7] != null) {
              try {
                dto.setBiztype(BusiTools.getBusiValue(Integer.parseInt(obj[7]
                    .toString()), BusiConst.BUSINESSSTATE));
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
            if (obj[8] != null) {
              dto.setHestatus(obj[8].toString().equals("0") ? "审核通过" : "审核未通过");
            }
            if (obj[9] != null) {
              dto.setPistatus(obj[9].toString().equals("0") ? "审批通过" : "审批未通过");
            }
            dto.setId(obj[10].toString());
            rlist.add(dto);
          }
          return rlist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  public void setPickCheckStatus(String date,String type,String id) throws Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = " update aa306 a6 set a6.reservea_b = '"+type+"' ,a6.reservea_c = '"+date+"' ,a6.sett_date = '"+date+"' where a6.id = '"+new Integer(id)+"'";
      String sql1 = " update aa101 a1 set a1.sett_date = '"+date+"' where a1.biz_id = '"+new Integer(id)+"' and a1.biz_type = 'D' ";
      st.executeUpdate(sql);
      st.executeUpdate(sql1);
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
