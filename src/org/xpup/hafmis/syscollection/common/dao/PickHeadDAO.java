package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.dto.QueryorgversionintDTO;
import org.xpup.hafmis.syscollection.collloanbackcheck.dto.CollLoanbackcheckDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.OrgSearchConditionDTO;
import org.xpup.hafmis.syscollection.querystatistics.collbyfund.dto.CollByFundBankDTO;
import org.xpup.hafmis.syscollection.querystatistics.collbyfund.dto.CollByFundDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.dto.PickMonRepBankPopInfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.dto.PickMonRepInfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickyearreport.dto.PickYearRepInfoDTO;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

/**
 * 提取头表
 * 
 * @author 李娟 2007-6-21
 */
public class PickHeadDAO extends HibernateDaoSupport {
  public PickHead queryById(Serializable id) {
    Validate.notNull(id);
    return (PickHead) getHibernateTemplate().get(PickHead.class, id);
  }

  public List test(final Integer balance, final Integer end) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List li = session.createQuery(
              "from PickHead p where p.pickBalance between ? and ?")
              .setInteger(0, balance.intValue()).setInteger(1, end.intValue())
              .list();
          return li;
        }
      });
      return list;
    } catch (Exception s) {
      // System.out.println("出现错误:"+s.getMessage());
      s.printStackTrace();
    }
    return null;
  }

  public String queryUnitAcc(final String org_id) {
    String str = "";
    try {
      str = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(t.org_id) from unitacc t where t.org_id = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(org_id));
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return str;
  }

  /**
   * @author yangg
   * @param headid
   * @return
   */
  public String getOperator_yg(final String headid) {
    String op = "";
    try {
      op = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select b.real_name from bb201 b,ca101 c,aa319 a where a.operator=c.username and c.id = b.id and a.bizid = ? and a.type = 'D' and a.action =1";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(headid));
          return query.uniqueResult();
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return op;
  }

  /**
   * 李文浩..维护 根据条件获得维护列表的数据
   */
  public List getVindicatePageData(final OrgSearchConditionDTO dto,
      final String orderBy, final String order, final int start,
      final int pageSize, final SecurityInfo info) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        PickHead p;
        PickTail t;
        String hql = "";
        if (dto.getSelect() != null)// 进入了查询的状态 改变hql语句
          hql = "from PickHead p where  p.batchnum=null and p.org.id "
              + info.getGjjSecurityHqlSQL(); // 如果是查询
        // 那么就会查出来所有的状态而不是
        // 单单的状态1和三的记录
        else
          hql = "from PickHead p where p.batchnum=null and (p.pickSatatus = 3 ) and p.org.id "
              + info.getGjjSecurityHqlSQL();
        String criterion = "";
        List parameter = new ArrayList();
        if (dto.getOrgId() != null && !dto.getOrgId().equals("")) {
          criterion = criterion + " to_char(p.org.id) like ? and ";
          parameter.add("%" + new Integer(dto.getOrgId()).toString() + "%");
        }
        if (dto.getOrgName() != null && !dto.getOrgName().equals("")) {
          criterion = criterion + "p.org.orgInfo.name like ? and ";
          parameter.add("%" + dto.getOrgName() + "%");
        }
        if (dto.getNoteNumber() != null && !dto.getNoteNumber().equals("")) {
          criterion = criterion + "p.noteNum like ? and ";
          parameter.add("%" + dto.getNoteNumber() + "%");
        }
        if (dto.getDocNumber() != null && !dto.getDocNumber().equals("")) {
          criterion = criterion + "p.docNum like ? and ";
          parameter.add("%" + dto.getDocNumber() + "%");
        }
        if (dto.getPickDate() != null && !dto.getPickDate().equals("")) {
          criterion = criterion + "p.settDate >= ? and ";
          parameter.add(dto.getPickDate());
        }
        if (dto.getPickDate_end() != null && !dto.getPickDate_end().equals("")) {
          criterion = criterion + "p.settDate <= ? and ";
          parameter.add(dto.getPickDate_end());
        }
        if (dto.getState() != null && !dto.getState().equals("")) {
          criterion = criterion + "p.pickSatatus = ? and ";
          parameter.add(new BigDecimal(dto.getState()));
        }
        if (dto.getCollectionBank() != null
            && !dto.getCollectionBank().equals("")) {
          criterion = criterion + "p.org.orgInfo.collectionBankId = ? and ";
          parameter.add(dto.getCollectionBank());
        }
        // if (dto.getReason() != null && !dto.getReason().equals("")) {
        // criterion = criterion + "to_char(p.reason) = ? and ";
        // parameter.add(dto.getReason());
        // }
        if (dto.getStart() != null && !dto.getStart().equals("")
            && dto.getEnd() != null && !dto.getEnd().equals("")) {
          criterion = criterion + " p.pickBalance between ? and ? and";
          parameter.add(new BigDecimal(dto.getStart()));
          parameter.add(new BigDecimal(dto.getEnd()));
        }
        String ob = orderBy;
        if (ob == null)
          ob = " p.org.id ";
        String or = order;
        if (or == null)
          or = "asc";
        if (criterion.length() != 0) {
          criterion = " and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + or;
        } else {
          hql = hql + criterion + " order by " + ob + " " + or;
        }

        Query query = session.createQuery(hql);
        for (int i = 0; i < parameter.size(); i++) {
          query.setParameter(i, parameter.get(i));
        }
        List l = query.setFirstResult(start).setMaxResults(pageSize).list();
        session.clear();
        return l;
      }
    });
    return list;
  }

  /**
   * 李文浩 获得全部单位提取的信息的集合..根据此集合可以算出来此单汇总
   */
  public List getVindicateAllDate(final OrgSearchConditionDTO dto,
      final SecurityInfo info) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "";
          if (dto.getSelect() != null)// 进入了查询的状态 改变hql语句
            hql = "from PickHead p where p.batchnum=null and p.org.id "
                + info.getGjjSecurityHqlSQL(); // 如果是查询 那么就会查出来所有的状态而不是
          else
            hql = "from PickHead p where p.batchnum=null and (p.pickSatatus = 3) and p.org.id "
                + info.getGjjSecurityHqlSQL();

          String criterion = "";
          List parameter = new ArrayList();
          if (dto.getOrgId() != null && !dto.getOrgId().equals("")) {
            criterion = criterion + " to_char(p.org.id) like ? and ";
            parameter.add("%" + new Integer(dto.getOrgId()).toString() + "%");
          }
          if (dto.getOrgName() != null && !dto.getOrgName().equals("")) {
            criterion = criterion + "p.org.orgInfo.name like ? and ";
            parameter.add("%" + dto.getOrgName() + "%");
          }
          if (dto.getNoteNumber() != null && !dto.getNoteNumber().equals("")) {
            criterion = criterion + "p.noteNum like ? and ";
            parameter.add("%" + dto.getNoteNumber() + "%");
          }
          if (dto.getDocNumber() != null && !dto.getDocNumber().equals("")) {
            criterion = criterion + "p.docNum like ? and ";
            parameter.add("%" + dto.getDocNumber() + "%");
          }
          if (dto.getPickDate() != null && !dto.getPickDate().equals("")) {
            criterion = criterion + "p.settDate >= ? and ";
            parameter.add(dto.getPickDate());
          }
          if (dto.getPickDate_end() != null
              && !dto.getPickDate_end().equals("")) {
            criterion = criterion + "p.settDate <= ? and ";
            parameter.add(dto.getPickDate_end());
          }
          if (dto.getState() != null && !dto.getState().equals("")) {
            criterion = criterion + "p.pickSatatus = ? and ";
            parameter.add(new BigDecimal(dto.getState()));
          }
          if (dto.getCollectionBank() != null
              && !dto.getCollectionBank().equals("")) {
            criterion = criterion + "p.org.orgInfo.collectionBankId = ? and ";
            parameter.add(dto.getCollectionBank());
          }
          if (criterion.length() != 0) {
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
          } else {
            hql = hql + criterion;
          }
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameter.size(); i++) {
            query.setParameter(i, parameter.get(i));
          }
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
   * 李文浩 根据头表的Id来得到单位编号
   */
  public Org getOrgByPickHeadId(Integer headId) {
    try {
      PickHead head = queryById(headId);
      return head.getOrg();
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 李文浩 获得全部单位提取的信息的总行数
   */
  public int getVindicateCount(final OrgSearchConditionDTO dto,
      final SecurityInfo info) {
    String count = "";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "";
          if (dto.getSelect() != null)// 进入了查询的状态 改变hql语句
            hql = "select count(p.noteNum) from PickHead p where p.batchnum=null and p.org.id "
                + info.getGjjSecurityHqlSQL(); // 如果是查询 那么就会查出来所有的状态而不是
          else
            hql = "select count(p.noteNum) from PickHead p where p.batchnum=null and (p.pickSatatus = 3 ) and p.org.id "
                + info.getGjjSecurityHqlSQL();
          String criterion = "";
          List parameter = new ArrayList();
          if (dto.getOrgId() != null && !dto.getOrgId().equals("")) {
            criterion = criterion + " to_char(p.org.id) like ? and ";
            parameter.add("%" + new Integer(dto.getOrgId()).toString() + "%");
          }
          if (dto.getOrgName() != null && !dto.getOrgName().equals("")) {
            criterion = criterion + "p.org.orgInfo.name like ? and ";
            parameter.add("%" + dto.getOrgName() + "%");
          }
          if (dto.getNoteNumber() != null && !dto.getNoteNumber().equals("")) {
            criterion = criterion + "p.noteNum like ? and ";
            parameter.add("%" + dto.getNoteNumber() + "%");
          }
          if (dto.getDocNumber() != null && !dto.getDocNumber().equals("")) {
            criterion = criterion + "p.docNum like ? and ";
            parameter.add("%" + dto.getDocNumber() + "%");
          }
          if (dto.getPickDate() != null && !dto.getPickDate().equals("")) {
            criterion = criterion + "p.settDate >= ? and ";
            parameter.add(dto.getPickDate());
          }
          if (dto.getCollectionBank() != null
              && !dto.getCollectionBank().equals("")) {
            criterion = criterion + "p.org.orgInfo.collectionBankId = ? and ";
            parameter.add(dto.getCollectionBank());
          }
          if (dto.getPickDate_end() != null
              && !dto.getPickDate_end().equals("")) {
            criterion = criterion + "p.settDate <= ? and ";
            parameter.add(dto.getPickDate_end());
          }
          if (dto.getState() != null && !dto.getState().equals("")) {
            criterion = criterion + "p.pickSatatus = ? and ";
            parameter.add(new BigDecimal(dto.getState()));
          }
          if (criterion.length() != 0) {
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
          } else {
            hql = hql + criterion;
          }
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameter.size(); i++) {
            query.setParameter(i, parameter.get(i));
          }
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return Integer.parseInt(count);
  }

  /**
   * 根据头表的id而删除头表
   */
  public void deleteById(Serializable id) {
    try {
      getHibernateTemplate().delete(queryById(id));
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  /**
   * 根据头表的id而删除头表
   */
  public void deleteById_LY(final Integer id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete PickHead pickHead where pickHead.id=?";
          session.createQuery(sql).setInteger(0, id.intValue()).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 李文浩 看看头表是否存在此单位状态等于1的记录 因为这条记录只能有一条 ..所以用Object来接收
   */
  public Serializable findPickHeadStatueOneByOrgId(final int orgId) {
    try {
      PickHead obj = (PickHead) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              Object o = session.createQuery(
                  "from PickHead h where h.org.id = ? and h.pickSatatus = 1")
                  .setInteger(0, orgId).uniqueResult();
              return o;
            }
          });
      if (obj == null)
        return null;
      return obj.getId();
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  public List test(BigDecimal one, BigDecimal two) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List li = session
            .createQuery("from PickHead p where p.pickBalance<900").list();
        return li;
      }
    });
    return list;
  }

  /*
   * 李文浩 根据用户输入的id来查找AA306表中状态为1的记录
   */
  public PickHead findByOrgId(final int id) {
    PickHead pickHead = null;
    try {// 取出来AA306中状态为1的记录...可以取出来AA306中的id..在根据这个id去尾表里找head.id的结果集..状态我为1的
      // 只能有一条记录
      pickHead = (PickHead) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              // 其实只能有一条记录...但是如果数据库出现了两条记录 那么数据库是错误的..而我的程序也不会报错
              List s = session.createQuery(
                  "from PickHead p where p.org.id=:id and p.pickSatatus=1")
                  .setInteger("id", id).list();
              if (s.isEmpty()) {
                return null;
              } else
                return (PickHead) s.get(0);
            }
          });
      return pickHead;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 根据单位编号来判断提取状态是否!=5
   */
  public PickHead findStatusNotIsFive(final int id) {
    PickHead pickHead = null;
    try {// 取出来AA306中状态为1的记录...可以取出来AA306中的id..在根据这个id去尾表里找head.id的结果集..状态我为1的
      // 只能有一条记录
      pickHead = (PickHead) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List list = new ArrayList();
              list = session.createQuery(
                  "from PickHead p where p.org.id=:id and p.pickSatatus!=5")
                  .setInteger("id", id).list();
              if (list.isEmpty()) {// 不存在..返回null
                return null;
              } else
                return (PickHead) list.get(0);
            }
          });
      return pickHead;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 根据单位编号来判断提取状态是否!=5
   */
  public boolean isHaveNoComplite(final int id) {
    boolean flag = true;
    try {// 取出来AA306中状态为1的记录...可以取出来AA306中的id..在根据这个id去尾表里找head.id的结果集..状态我为1的
      // 只能有一条记录
      List list = (List) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              Query query = session
                  .createQuery(
                      "select p.id from PickHead p where p.org.id=:id and p.pickSatatus!=5")
                  .setInteger("id", id);
              return query.list();
            }
          });
      if (list == null || list.size() == 0) {
        flag = false;
      }
    } catch (Exception s) {
      s.printStackTrace();
    }
    return flag;
  }

  /*
   * 李文浩 根据id来寻找Org的对象
   */
  public Org findOrgById(int id) {
    try {
      Org org = (Org) getHibernateTemplate().get(Org.class, new Integer(id));
      return org;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /*
   * 李文浩..根据id来寻找OrgInfo的对象
   */
  public OrgInfo findOrgInfoById(int id) {
    try {
      OrgInfo orgInfo = (OrgInfo) getHibernateTemplate().get(OrgInfo.class,
          new Integer(id));
      return orgInfo;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 插入头表 并且返回此头表的id
   */
  public Serializable insert(PickHead pickHead) {
    Serializable id = null;
    try {
      Validate.notNull(pickHead);
      id = getHibernateTemplate().save(pickHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }

  /**
   * 李文浩...更新头表的方法
   */
  public void updatePickHead(PickHead head) {
    try {
      getHibernateTemplate().update(head);
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  /**
   * 根据org_id查询
   * 
   * @param id
   * @return
   */
  public PickHead queryByOrgId(final Serializable id) {
    PickHead pickHead = null;
    try {
      Validate.notNull(id);
      pickHead = (PickHead) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select pickHead from PickHead pickHead where pickHead.org.id = ?";
              Vector parameters = new Vector();
              parameters.add((Integer) id);

              Query query = session.createQuery(hql);
              query.setParameter(0, parameters.get(0));
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pickHead;
  }

  // 判断特殊提取时是否有未记账的转出
  public List queryTranOutHeadByOrgid(final String orgid) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select tranOutHead from TranOutHead tranOutHead where tranOutHead.tranStatus not in (5) and tranOutHead.tranOutOrg.id = ?  ";
          Vector parameters = new Vector();
          parameters.add(new Integer(orgid));
          Query query = session.createQuery(hql);
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
   * 完成提取
   */
  public void overPickUp(final Integer pickHeadId, final Integer orgId,
      final String docnum, final String settdate, final String notenum,
      final String operator, String officeCode, String moneyBank,
      final String registrations) {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call OverPickUpDoPre(?,?,?,?,?,?,?,?,?)}");
      cs.setInt(1, pickHeadId.intValue());
      cs.setInt(2, orgId.intValue());
      cs.setString(3, docnum);
      cs.setString(4, settdate);
      cs.setString(5, notenum);
      cs.setString(6, operator);
      cs.setString(7, officeCode);
      cs.setString(8, moneyBank);
      cs.setString(9, registrations);
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * 公积金还贷的业务复核列表查询
   * 
   * @param officeCode
   * @param collectionBankId
   * @param bizStatus
   * @param batch_num
   * @param startDate
   * @param endDate
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List collLoanbackcheckFindList(final String flag,
      final String officeCode, final String collectionBankId,
      final String bizStatus, final String batch_num, final String startDate,
      final String endDate, final String orderBy, final String order,
      final int start, final int pageSize, final int page, final Integer orgid,
      final String orgname, final Integer empid, final String empname)
      throws BusinessException {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List returnList = new ArrayList();
          String hql = " select aa410.batch_num as batch_num,"
              + "aa306.doc_num as doc_num,aa306.org_id as orgid,ba001.name as orgname,"
              + "aa307.emp_id as empid,ba002.name as empname,"
              + "aa307.pick_pre_balance + aa307.pick_cur_balance as money,"
              + "aa306.sett_date as settdate,aa306.pick_satatus as status,"
              + "pl110.borrower_name,aa307.contract_id "
              + "from AA306 aa306,AA307 aa307,BA001 ba001,"
              + "AA001 aa001,AA410 aa410,BA002 ba002, AA002 aa002, PL110 pl110 "
              + "where aa410.batch_num = aa306.batch_num "
              + "and pl110.contract_id = aa307.contract_id "
              + "and aa306.org_id = aa001.id "
              + "and aa001.orginfo_id = ba001.id "
              + "and aa306.id = aa307.pickhead_id "
              + "and aa307.emp_id = aa002.id and aa002.org_id=aa306.org_id "
              + "and aa002.emp_info_id = ba002.id and ";
          Vector parameters = new Vector();
          String criterion = "";

          if (officeCode != null && !officeCode.equals("")) {
            criterion += " ba001.OFFICECODE = ?  and ";
            parameters.add(officeCode);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " ba001.name like ?  and ";
            parameters.add("%" + orgname + "%");
          }
          if (orgid != null && !orgid.equals("")) {
            criterion += " aa002.org_id = ?  and ";
            parameters.add(orgid);
          }
          if (empid != null && !empid.equals("")) {
            criterion += " aa002.id = ?  and ";
            parameters.add(empid);
          }
          if (empname != null && !empname.equals("")) {
            criterion += " ba002.name like ?  and ";
            parameters.add("%" + empname + "%");
          }

          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " ba001.COLLECTION_BANK_ID = ?  and ";
            parameters.add(collectionBankId);
          }
          if ("1".equals(flag)) {
            criterion += " aa306.pick_satatus='3' and ";
          } else if ("2".equals(flag)) {
            criterion += " aa306.pick_satatus='4' and ";
          } else {
            if (bizStatus != null && !bizStatus.equals("")) {
              criterion += " aa306.pick_satatus = ?  and ";
              parameters.add(bizStatus);
            } else {
              criterion += " aa306.pick_satatus in('3','4','5')  and ";
            }
          }
          if (batch_num != null && !batch_num.equals("")) {
            criterion += " aa306.BATCH_NUM = ?  and ";
            parameters.add(batch_num);
          }
          if (startDate != null && !startDate.equals("")) {
            criterion += " aa306.SETT_DATE >= ?  and ";
            parameters.add(startDate);
          }
          if (endDate != null && !endDate.equals("")) {
            criterion += " aa306.SETT_DATE <= ?  and ";
            parameters.add(endDate);
          }
          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          }

          String ob = orderBy;
          if (ob == null)
            ob = " aa306.batch_num ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          CollLoanbackcheckDTO collLoanbackcheckDTO = null;
          Object obj[] = null;
          Iterator iter = query.list().iterator();
          while (iter.hasNext()) {
            String a = "";
            obj = (Object[]) iter.next();
            collLoanbackcheckDTO = new CollLoanbackcheckDTO();
            collLoanbackcheckDTO.setBatch_num(obj[0].toString());
            collLoanbackcheckDTO.setDoc_num(obj[1].toString());
            collLoanbackcheckDTO.setOrg_id(obj[2].toString());
            collLoanbackcheckDTO.setOrg_name(obj[3].toString());
            collLoanbackcheckDTO.setEmp_id(BusiTools.convertSixNumber(obj[4]
                .toString()));
            collLoanbackcheckDTO.setEmp_name(obj[5].toString());
            collLoanbackcheckDTO.setMoney(new BigDecimal(obj[6].toString()));
            collLoanbackcheckDTO.setSett_date(obj[7].toString());
            if (obj[8].toString().equals("3")) {
              a = "确认";
            } else if (obj[8].toString().equals("4")) {
              a = "复核";
            } else if (obj[8].toString().equals("5")) {
              a = "入账";
            }
            collLoanbackcheckDTO.setPick_satatus(a);
            collLoanbackcheckDTO.setBorrowerName(obj[9].toString());
            collLoanbackcheckDTO.setContractId(obj[10].toString());
            returnList.add(collLoanbackcheckDTO);
          }
          List queryList = returnList;

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList = returnList;
          }
          return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 公积金还贷的业务复核列表条数
   * 
   * @param officeCode
   * @param collectionBankId
   * @param bizStatus
   * @param batch_num
   * @param startDate
   * @param endDate
   * @return
   */
  public List collLoanbackcheckFindAllList(final String flag,
      final String officeCode, final String collectionBankId,
      final String bizStatus, final String batch_num, final String startDate,
      final String endDate, final Integer orgid, final String orgname,
      final Integer empid, final String empname) throws BusinessException {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List returnList = new ArrayList();
          String hql = " select aa410.batch_num as batch_num,"
              + "aa306.doc_num as doc_num,aa306.org_id as orgid,ba001.name as orgname,"
              + "aa307.emp_id as empid,ba002.name as empname,"
              + "aa307.pick_pre_balance + aa307.pick_cur_balance as money,"
              + "aa306.sett_date as settdate,aa306.pick_satatus as status,"
              + "pl110.borrower_name,aa307.contract_id "
              + "from AA306 aa306,AA307 aa307,BA001 ba001,"
              + "AA001 aa001,AA410 aa410,BA002 ba002, AA002 aa002, PL110 pl110 "
              + "where aa410.batch_num = aa306.batch_num "
              + "and pl110.contract_id = aa307.contract_id "
              + "and aa306.org_id = aa001.id "
              + "and aa001.orginfo_id = ba001.id "
              + "and aa306.id = aa307.pickhead_id "
              + "and aa307.emp_id = aa002.id "
              + "and aa002.emp_info_id = ba002.id and  aa002.org_id=aa306.org_id and ";
          Vector parameters = new Vector();
          String criterion = "";

          if (officeCode != null && !officeCode.equals("")) {
            criterion += " ba001.OFFICECODE = ?  and ";
            parameters.add(officeCode);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " ba001.name like ?  and ";
            parameters.add("%" + orgname + "%");
          }
          if (orgid != null && !orgid.equals("")) {
            criterion += " aa002.org_id = ?  and ";
            parameters.add(orgid);
          }
          if (empid != null && !empid.equals("")) {
            criterion += " aa002.id = ?  and ";
            parameters.add(empid);
          }
          if (empname != null && !empname.equals("")) {
            criterion += " ba002.name like ?  and ";
            parameters.add("%" + empname + "%");
          }

          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " ba001.COLLECTION_BANK_ID = ?  and ";
            parameters.add(collectionBankId);
          }
          if ("1".equals(flag)) {
            criterion += " aa306.pick_satatus='3' and ";
          } else if ("2".equals(flag)) {
            criterion += " aa306.pick_satatus='4' and ";
          } else {
            if (bizStatus != null && !bizStatus.equals("")) {
              criterion += " aa306.pick_satatus = ?  and ";
              parameters.add(bizStatus);
            } else {
              criterion += " aa306.pick_satatus in('3','4','5')  and ";
            }
          }
          if (batch_num != null && !batch_num.equals("")) {
            criterion += " aa306.BATCH_NUM = ?  and ";
            parameters.add(batch_num);
          }
          if (startDate != null && !startDate.equals("")) {
            criterion += " aa306.SETT_DATE >= ?  and ";
            parameters.add(startDate);
          }
          if (endDate != null && !endDate.equals("")) {
            criterion += " aa306.SETT_DATE <= ?  and ";
            parameters.add(endDate);
          }
          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          }

          hql = hql + criterion + " order by aa307.emp_id ";
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          CollLoanbackcheckDTO collLoanbackcheckDTO = null;
          Object obj[] = null;
          Iterator iter = query.list().iterator();
          while (iter.hasNext()) {
            String a = "";
            obj = (Object[]) iter.next();
            collLoanbackcheckDTO = new CollLoanbackcheckDTO();
            collLoanbackcheckDTO.setBatch_num(obj[0].toString());
            collLoanbackcheckDTO.setDoc_num(obj[1].toString());
            collLoanbackcheckDTO.setOrg_id(obj[2].toString());
            collLoanbackcheckDTO.setOrg_name(obj[3].toString());
            collLoanbackcheckDTO.setEmp_id(obj[4].toString());
            collLoanbackcheckDTO.setEmp_name(obj[5].toString());
            collLoanbackcheckDTO.setMoney(new BigDecimal(obj[6].toString()));
            collLoanbackcheckDTO.setSett_date(obj[7].toString());
            collLoanbackcheckDTO.setPick_satatus_num(obj[8].toString());
            if (obj[8].toString().equals("3")) {
              a = "确认";
            } else if (obj[8].toString().equals("4")) {
              a = "复核";
            } else if (obj[8].toString().equals("5")) {
              a = "入账";
            }
            collLoanbackcheckDTO.setPick_satatus(a);
            collLoanbackcheckDTO.setBorrowerName(obj[9].toString());
            collLoanbackcheckDTO.setContractId(obj[10].toString());
            returnList.add(collLoanbackcheckDTO);
          }
          return returnList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据批次号获得AA306的ID
   * 
   * @param butenum
   * @return
   */
  public List getPickHeadIDByButenum(final String butenum)
      throws BusinessException {
    List id = null;
    try {
      id = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select h.id from PickHead h where h.batchnum=? ";
          Query query = session.createQuery(hql);
          query.setString(0, butenum);

          CollLoanbackcheckDTO collLoanbackcheckDTO = null;
          Object obj = null;
          List returnList = new ArrayList();
          Iterator iter = query.list().iterator();
          while (iter.hasNext()) {
            obj = (Object) iter.next();
            collLoanbackcheckDTO = new CollLoanbackcheckDTO();
            collLoanbackcheckDTO.setSeqid(obj.toString());
            returnList.add(collLoanbackcheckDTO);
          }
          return returnList;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return id;
  }

  public Integer query_havingstatus(final String officeId) {
    Integer name = new Integer(0);
    try {
      name = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(count(a.id),0)  from aa306 a  where a.batch_num='"
              + officeId + "' and a.pick_satatus='5'";
          Query query = session.createSQLQuery(hql);
          return new Integer("" + query.uniqueResult());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return name;
  }

  /**
   * hanl 查询单位版数据库1、AA306关联AA307，AA309关联AA310，AA101关联AA102
   * 
   * @param orgId
   * @param orgName
   * @param empId
   * @param empName
   * @param clearInterestType
   * @param orderBy
   * @param orderother
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   */
  public List findPickuplist(final String orgId, final String orgName,
      final String empId, final String empName, final String clearInterestType,
      final String orderBy, final String orderother, final int start,
      final int pageSize, final int page, final SecurityInfo securityInfo) {
    List list = null;
    try {
      Validate.isTrue(orderother == null || "ASC".equalsIgnoreCase(orderother)
          || "DESC".equalsIgnoreCase(orderother));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select id,empid,money,orgid,orgname,empname,type from (select a306.id id, a307.emp_id empid, "
              + "sum(a307.pick_pre_balance + a307.pick_cur_balance) money, a306.org_id orgid, b001.name orgname,b002.name empname, 'B' type  "
              + "from aa306 a306, aa307 a307, ba001 b001, aa001 a001,ba002 b002,aa002 a002 where a306.id = a307.pickhead_id   and a307.pick_type = '2'   "
              + "and a001.orginfo_id = b001.id   and a306.org_id = a001.id and a002.emp_info_id=b002.id and a307.emp_id=a002.id group by a306.id, a307.emp_id,"
              + " a306.org_id, b001.name,b002.name union select a309.id id, a310.emp_id empid, sum(a310.pre_balance + a310.cur_balance) money, a309.out_org_id orgid,"
              + " b001.name orgname,b002.name empname, 'C' type  from aa309 a309, aa310 a310, ba001 b001, aa001 a001,ba002 b002,aa002 a002 "
              + "where a309.id = a310.tran_out_head_id   and a310.is_sett_intrerest = '1'   and a001.orginfo_id = b001.id   and a309.out_org_id = a001.id   "
              + "and a002.emp_info_id=b002.id   and a002.id=a310.emp_id group by a309.id, a310.emp_id, a309.out_org_id, b001.name,b002.name "
              + "union select to_number(a101.biz_id) id, a102.emp_id empid,0 money, a101.org_id orgid, b001.name orgname,b002.name empname,'A' type  from "
              + "aa101 a101, aa102 a102, ba001 b001, aa001 a001,ba002 b002,aa002 a002 where a101.id = a102.org_flow_id   and a101.biz_type = 'H'   "
              + "and a001.orginfo_id = b001.id   and a101.org_id = a001.id  "
              + " and a002.emp_info_id=b002.id   and a002.id=a102.emp_id group by  a101.biz_id,a102.emp_id, a101.org_id, b001.name,b002.name) aa ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !"".equals("orgId")) {
            criterion += " orgid= ? and ";
            parameters.add(orgId);
          }
          if (orgName != null && !"".equals("orgName")) {
            criterion += "orgname= ? and ";
            parameters.add(orgName);
          }
          if (empId != null && !"".equals("empId")) {
            criterion += "empid= ? and ";
            parameters.add(empId);
          }
          if (empName != null && !"".equals("empName")) {
            criterion += "empname= ? and ";
            parameters.add(empName);
          }
          if (clearInterestType != null && !"".equals(clearInterestType)) {
            criterion += "type= ? and ";
            parameters.add(clearInterestType);
          }

          if (criterion.length() != 0) {
            criterion = "where orgid " + securityInfo.getGjjSecuritySQL()
                + "and " + criterion.substring(0, criterion.lastIndexOf("and"));

          } else {
            criterion = "where orgid " + securityInfo.getGjjSecuritySQL();
          }
          String ob = orderBy;
          if (ob == null)
            ob = "empid";

          String od = orderother;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + orderother;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);

          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();
          List queryList1 = new ArrayList();
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList = query.list();
          }
          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            QueryorgversionintDTO dto = new QueryorgversionintDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              dto.setPid(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setEmpId(obj[1].toString());
            }
            if (obj[5] != null) {
              dto.setEmpName(obj[5].toString());
            }
            if (obj[2] != null) {
              dto.setMoney(new BigDecimal(obj[2].toString()));
            }
            if (obj[6] != null) {
              dto.setClearInterestType(obj[6].toString());
            }
            queryList1.add(dto);
          }

          return queryList1;
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public int findPickupalllist(final String orgId, final String orgName,
      final String empId, final String empName, final String clearInterestType,
      final SecurityInfo securityInfo) {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select id,empid,money,orgid,orgname,empname,type from (select a306.id id, a307.emp_id empid, "
              + "sum(a307.pick_pre_balance + a307.pick_cur_balance) money, a306.org_id orgid, b001.name orgname,b002.name empname, 'B' type  "
              + "from aa306 a306, aa307 a307, ba001 b001, aa001 a001,ba002 b002,aa002 a002 where a306.id = a307.pickhead_id   and a307.pick_type = '2'   "
              + "and a001.orginfo_id = b001.id   and a306.org_id = a001.id and a002.emp_info_id=b002.id and a307.emp_id=a002.pk_id group by a306.id, a307.emp_id,"
              + " a306.org_id, b001.name,b002.name union select a309.id id, a310.emp_id empid, sum(a310.pre_balance + a310.cur_balance) money, a309.out_org_id orgid,"
              + " b001.name orgname,b002.name empname, 'C' type  from aa309 a309, aa310 a310, ba001 b001, aa001 a001,ba002 b002,aa002 a002 "
              + "where a309.id = a310.tran_out_head_id   and a310.is_sett_intrerest = '1'   and a001.orginfo_id = b001.id   and a309.out_org_id = a001.id   "
              + "and a002.emp_info_id=b002.id   and a002.pk_id=a310.emp_id group by a309.id, a310.emp_id, a309.out_org_id, b001.name,b002.name "
              + "union select to_number(a101.id) id, a102.emp_id empid,0 money, a101.org_id orgid, b001.name orgname,b002.name empname,'A' type  from "
              + "aa101 a101, aa102 a102, ba001 b001, aa001 a001,ba002 b002,aa002 a002 where a101.id = a102.org_flow_id   and a101.biz_type = 'H'   "
              + "and a001.orginfo_id = b001.id   and a101.org_id = a001.id  "
              + " and a002.emp_info_id=b002.id   and a002.pk_id=a102.emp_id group by  a101.id,a102.emp_id, a101.org_id, b001.name,b002.name) aa ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !"".equals("orgId")) {
            criterion += " orgid= ? and ";
            parameters.add(orgId);
          }
          if (orgName != null && !"".equals("orgName")) {
            criterion += "orgname= ? and ";
            parameters.add(orgName);
          }
          if (empId != null && !"".equals("empId")) {
            criterion += "empid= ? and ";
            parameters.add(empId);
          }
          if (empName != null && !"".equals("empName")) {
            criterion += "empname= ? and ";
            parameters.add(empName);
          }
          if (clearInterestType != null && !"".equals(clearInterestType)) {
            criterion += "type= ? and ";
            parameters.add(clearInterestType);
          }

          if (criterion.length() != 0) {
            criterion = "where orgid " + securityInfo.getGjjSecuritySQL()
                + "and " + criterion.substring(0, criterion.lastIndexOf("and"));

          } else {
            criterion = "where orgid " + securityInfo.getGjjSecuritySQL();
          }
          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List queryList1 = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            QueryorgversionintDTO dto = new QueryorgversionintDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              dto.setPid(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setEmpId(obj[1].toString());
            }
            if (obj[5] != null) {
              dto.setEmpName(obj[5].toString());
            }
            if (obj[2] != null) {
              dto.setMoney(new BigDecimal(obj[2].toString()));
            }
            if (obj[6] != null) {
              dto.setClearInterestType(obj[6].toString());
            }
            queryList1.add(dto);
          }

          return queryList1;
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  // 判断添加提取的并发问题---是否是重复添加
  public List isDoubleAdd(final Integer orgid, final Integer empid) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select aa306.id " + "from AA306 aa306,AA307 aa307 "
              + "where aa306.org_id=? and " + "aa306.pick_satatus=1 and "
              + "aa306.id=aa307.pickhead_id and " + "aa307.emp_id=? ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, orgid);
          query.setParameter(1, empid);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 判断添加特殊提取的并发问题---是否是重复添加
  public List isDoubleAdd_SpecialPick(final Integer orgid, final Integer empid) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select aa308.id " + "from AA308 aa308,AA307 aa307 "
              + "where aa308.org_id=? and " + "aa308.is_pick=1 and "
              + "aa308.id=aa307.special_pickhead_id and " + "aa307.emp_id=? ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, orgid);
          query.setParameter(1, empid);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询提取统计月报表
   * 
   * @param orgid
   * @param empid
   * @return list
   */
  public List queryMonReportInfoList(final String office, final String bank,
      final String startDate, final SecurityInfo securityInfo,
      final String endDate) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select a307.pick_reason," + " a101.moneybank,"
              + " nvl(count(a307.id),0),"
              + " nvl(sum(a307.pick_pre_balance+a307.pick_cur_balance),0)"
              + " from aa307 a307,aa101 a101"
              + " where a101.biz_id = a307.pickhead_id "
              + " and a101.biz_status = 5" + " and a101.biz_type = 'D'"
              + " and a101.org_id " + securityInfo.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (office != null && !"".equals(office)) {
            criterion += " a101.officecode = ? and ";
            parameters.add(office);
          }
          if (bank != null && !"".equals(bank)) {
            criterion += " a101.moneybank = ? and ";
            parameters.add(bank);
          }
          if ((startDate != null && !"".equals(startDate))
              && (endDate != null && !"".equals(endDate))) {
            criterion += " a101.sett_date between ? and ? and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (criterion.length() != 0) {
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }
          sql = sql
              + criterion
              + " group by a101.moneybank,a307.pick_reason order by a101.moneybank";
          Query query = session.createSQLQuery(sql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List returnList = new ArrayList();

          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          while (iterator.hasNext()) {
            PickMonRepInfoDTO dto = new PickMonRepInfoDTO();
            obj = (Object[]) iterator.next();
            if (obj[0] != null) {
              dto.setPickReason(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setCollBank(obj[1].toString());
              dto.setCollBankId(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setPersonCount_temp(new Integer(obj[2].toString()));
            }
            if (obj[3] != null) {
              dto.setPickMoney_temp(new BigDecimal(obj[3].toString()));
            }
            returnList.add(dto);
          }
          return returnList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询提取统计年报表
   * 
   * @param orgid
   * @param empid
   * @return list
   */
  public List queryYearReportInfoList(final String office, final String bank,
      final String pickMonth, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select a307.pick_reason," + " nvl(count(a307.id),0),"
              + " nvl(sum(a307.pick_pre_balance+a307.pick_cur_balance),0)"
              + " from aa307 a307,aa101 a101"
              + " where a101.biz_id = a307.pickhead_id "
              + " and a101.biz_status = 5" + " and a101.biz_type = 'D'"
              + " and a101.org_id " + securityInfo.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (office != null && !"".equals(office)) {
            criterion += " a101.officecode = ? and ";
            parameters.add(office);
          }
          if (bank != null && !"".equals(bank)) {
            criterion += " a101.moneybank = ? and ";
            parameters.add(bank);
          }
          if (pickMonth != null && !"".equals(pickMonth)) {
            criterion += " substr(a101.sett_date,0,6) = ? and ";
            parameters.add(pickMonth);
          }
          if (criterion.length() != 0) {
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }
          sql = sql + criterion + " group by a307.pick_reason";
          Query query = session.createSQLQuery(sql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List returnList = new ArrayList();

          Iterator iterator = query.list().iterator();
          Object[] obj = null;
          while (iterator.hasNext()) {
            PickYearRepInfoDTO dto = new PickYearRepInfoDTO();
            obj = (Object[]) iterator.next();
            if (obj[0] != null) {
              dto.setPickReason(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setPersonCount_temp(new Integer(obj[1].toString()));
            }
            if (obj[2] != null) {
              dto.setPickMoney_temp(new BigDecimal(obj[2].toString()));
            }
            returnList.add(dto);
          }
          return returnList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * yqf 归集--统计查询--提取统计月报表--银行弹出框
   * 
   * @param orgId
   * @param orgName
   * @param empId
   * @param empName
   * @param pickReason
   * @param startDate
   * @param endDate
   * @param bankId
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryPickBankPopList(final String orgId, final String orgName,
      final String empId, final String empName, final String pickReason,
      final String startDate, final String endDate, final String bankId,
      final int start, final int pageSize, final String orderBy,
      final String order, final SecurityInfo securityInfo) {

    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          session.clear();
          String hql = " select distinct t.emp_id as 职工编号,"
              + " e.name as 职工名称,"
              + " a.org_id as 单位编号,"
              + " c.name as 单位名称,"
              + " nvl(t.pick_pre_balance,0) + nvl(t.pick_cur_balance,0) as 提取金额,"
              + " nvl(t.pick_pre_interest,0) + nvl(t.pick_cur_interest,0) as 提取利息,"
              + " (nvl(t.pick_pre_balance,0) + nvl(t.pick_cur_balance,0))+(nvl(t.pick_pre_interest,0) + nvl(t.pick_cur_interest,0)) as 总额,"
              + " t.pick_reason as 提取原因"
              + " from aa307 t, aa101 a, aa001 b, ba001 c, aa002 d, ba002 e"
              + " where a.biz_id = t.pickhead_id" + " and a.org_id = b.id"
              + " and b.orginfo_id = c.id" + " and t.emp_id = d.id"
              + " and d.emp_info_id = e.id" + " and a.biz_status = '5'"
              + " and a.biz_type = 'D'";

          Vector parameters = new Vector();
          String criterion = "";

          if (bankId != null && !"".equals(bankId)) {//
            criterion += " a.moneybank = ? and ";
            parameters.add(bankId);
          }

          if ((startDate != null && !"".equals(startDate))
              && (endDate != null && !"".equals(endDate))) {// 部门名称
            criterion += " a.sett_date between ? and ?  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (orgId != null && !"".equals(orgId)) {//
            criterion += " a.org_id = ? and ";
            parameters.add(orgId);
          }
          if (orgName != null && !"".equals(orgName)) {//
            criterion += " c.name = ? and ";
            parameters.add(orgName);
          }
          if (empId != null && !"".equals(empId)) {//
            criterion += " t.emp_id = ? and ";
            parameters.add(empId);
          }
          if (empName != null && !"".equals(empName)) {//
            criterion += " e.name = ? and ";
            parameters.add(empName);
          }
          if (pickReason != null && !"".equals(pickReason)) {//
            criterion += " t.pick_reason = ? and ";
            parameters.add(pickReason);
          }
          if (criterion.length() != 0) {
            criterion = " and a.org_id " + securityInfo.getGjjSecuritySQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }

          String ob = orderBy;
          if (ob == null)
            ob = " a.org_id ";
          String or = order;
          if (or == null)
            or = "asc";

          hql = hql + criterion + " order by " + ob + " " + or;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          PickMonRepBankPopInfoDTO dto = null;
          Object obj[] = null;
          Iterator iter = query.list().iterator();
          List templist = new ArrayList();
          while (iter.hasNext()) {
            obj = (Object[]) iter.next();
            dto = new PickMonRepBankPopInfoDTO();
            if (obj[3] != null) {
              dto.setOrgName(obj[3].toString());
            }
            if (obj[1] != null) {
              dto.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setOrgId(obj[2].toString());
            }
            if (obj[0] != null) {
              dto.setEmpId(obj[0].toString());
            }
            if (obj[4] != null) {
              dto.setPickMoney(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              dto.setPickInterest(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null) {
              dto.setSumPickMoney(new BigDecimal(obj[6].toString()));
            }
            if (obj[7] != null) {
              dto.setPickReason(obj[7].toString());
            }
            templist.add(dto);
          }
          return templist;
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public List queryPickBankPopCount(final String orgId, final String orgName,
      final String empId, final String empName, final String pickReason,
      final String startDate, final String endDate, final String bankId,
      final int start, final int pageSize, final String orderBy,
      final String order, final SecurityInfo securityInfo) {

    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          session.clear();
          String hql = " select distinct t.emp_id as 职工编号,"
              + " e.name as 职工名称,"
              + " a.org_id as 单位编号,"
              + " c.name as 单位名称,"
              + " nvl(t.pick_pre_balance,0) + nvl(t.pick_cur_balance,0) as 提取金额,"
              + " nvl(t.pick_pre_interest,0) + nvl(t.pick_cur_interest,0) as 提取利息,"
              + " (nvl(t.pick_pre_balance,0) + nvl(t.pick_cur_balance,0))+(nvl(t.pick_pre_interest,0) + nvl(t.pick_cur_interest,0)) as 总额,"
              + " t.pick_reason as 提取原因"
              + " from aa307 t, aa101 a, aa001 b, ba001 c, aa002 d, ba002 e"
              + " where a.biz_id = t.pickhead_id" + " and a.org_id = b.id"
              + " and b.orginfo_id = c.id" + " and t.emp_id = d.id"
              + " and d.emp_info_id = e.id" + " and a.biz_status = '5'"
              + " and a.biz_type = 'D'";

          Vector parameters = new Vector();
          String criterion = "";

          if (bankId != null && !"".equals(bankId)) {//
            criterion += " a.moneybank = ? and ";
            parameters.add(bankId);
          }

          if ((startDate != null && !"".equals(startDate))
              && (endDate != null && !"".equals(endDate))) {// 部门名称
            criterion += " a.sett_date between ? and ?  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (orgId != null && !"".equals(orgId)) {//
            criterion += " a.org_id = ? and ";
            parameters.add(orgId);
          }
          if (orgName != null && !"".equals(orgName)) {//
            criterion += " c.name = ? and ";
            parameters.add(orgName);
          }
          if (empId != null && !"".equals(empId)) {//
            criterion += " t.emp_id = ? and ";
            parameters.add(empId);
          }
          if (empName != null && !"".equals(empName)) {//
            criterion += " e.name = ? and ";
            parameters.add(empName);
          }
          if (pickReason != null && !"".equals(pickReason)) {//
            criterion += " t.pick_reason = ? and ";
            parameters.add(pickReason);
          }
          if (criterion.length() != 0) {
            criterion = " and a.org_id " + securityInfo.getGjjSecuritySQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }

          String ob = orderBy;
          if (ob == null)
            ob = " a.org_id ";
          String or = order;
          if (or == null)
            or = "asc";

          hql = hql + criterion + " order by " + ob + " " + or;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          PickMonRepBankPopInfoDTO dto = null;
          Object obj[] = null;
          Iterator iter = query.list().iterator();
          List templist = new ArrayList();
          while (iter.hasNext()) {
            obj = (Object[]) iter.next();
            dto = new PickMonRepBankPopInfoDTO();
            if (obj[3] != null) {
              dto.setOrgName(obj[3].toString());
            }
            if (obj[1] != null) {
              dto.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setOrgId(obj[2].toString());
            }
            if (obj[0] != null) {
              dto.setEmpId(obj[0].toString());
            }
            if (obj[4] != null) {
              dto.setPickMoney(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              dto.setPickInterest(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null) {
              dto.setSumPickMoney(new BigDecimal(obj[6].toString()));
            }
            if (obj[7] != null) {
              dto.setPickReason(obj[7].toString());
            }
            templist.add(dto);
          }
          return templist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryCollByFundByCriterions(final String officeCode,
      final String collBankId, final String contractId, final String orgId,
      final String orgName, final String empId, final String empName,
      final String cardNum, final String begDate, final String endDate,
      final String batchNum, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          session.clear();
          String sql = " select a001.id as orgid," + " b001.name as orgname,"
              + " a411.contract_id, a411.year_month,"
              + " a411.should_corpus, a411.emp_id as empid,"
              + " b002.name as empname, a410.biz_date,a410.batch_num"
              + " from aa001 a001, ba001 b001, aa002 a002,"
              + " ba002 b002, aa410 a410, aa411 a411"
              + " where a001.orginfo_id = b001.id"
              + " and a002.emp_info_id=b002.id and a001.id = a002.org_id"
              + " and a410.id=a411.head_id and a001.id=a411.org_id"
              + " and a002.id=a411.emp_id and a411.collflag=1 ";

          Vector parameters = new Vector();
          String criterion = "";

          if (officeCode != null && !"".equals(officeCode)) {
            criterion += " and b001.officecode = ? ";
            parameters.add(officeCode);
          }
          if (collBankId != null && !"".equals(collBankId)) {
            criterion += " and b001.collection_bank_id = ? ";
            parameters.add(collBankId);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " and a411.contract_id = ? ";
            parameters.add(contractId);
          }
          if (orgId != null && !"".equals(orgId)) {
            criterion += " and a001.id = ? ";
            parameters.add(new Integer(orgId));
          }
          if (orgName != null && !"".equals(orgName)) {
            criterion += " and b001.name like ? ";
            parameters.add("%" + orgName + "%");
          }
          if (empId != null && !"".equals(empId)) {
            criterion += " and a002.id = ? ";
            parameters.add(new Integer(empId));
          }
          if (empName != null && !"".equals(empName)) {
            criterion += " and b002.name = ? ";
            parameters.add(empName);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " and b002.card_num = ? ";
            parameters.add(cardNum);
          }
          if (begDate != null && !"".equals(begDate)) {
            criterion += " and a410.biz_date >= ? ";
            parameters.add(begDate);
          }
          if (endDate != null && !"".equals(endDate)) {
            criterion += " and a410.biz_date <= ? ";
            parameters.add(endDate);
          }
          if (batchNum != null && !"".equals(batchNum)) {
            criterion += " and a410.batch_num = ? ";
            parameters.add(batchNum);
          }
          String ob = orderBy;
          if (ob == null)
            ob = " a001.id ";
          String or = order;
          if (or == null)
            or = "asc";

          sql = sql + criterion + " order by " + ob + " " + or;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          CollByFundDTO dto = null;
          Object obj[] = null;
          Iterator iter = query.list().iterator();
          List templist = new ArrayList();
          while (iter.hasNext()) {
            obj = (Object[]) iter.next();
            dto = new CollByFundDTO();
            if (obj[0] != null) {
              dto.setOrgId(new BigDecimal(obj[0].toString()));
            }
            if (obj[1] != null) {
              dto.setOrgName(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setContractId(obj[2].toString());
            }
            if (obj[3] != null) {
              dto.setYearMonth(obj[3].toString());
            }
            if (obj[4] != null) {
              dto.setMoney(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              dto.setEmpId(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null) {
              dto.setEmpName(obj[6].toString());
            }
            if (obj[7] != null) {
              dto.setKouDate(obj[7].toString());
            }
            if (obj[8] != null) {
              dto.setBatchNum(obj[8].toString());
            }
            templist.add(dto);
          }
          return templist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryCollByFundAllByCriterions(final String officeCode,
      final String collBankId, final String contractId, final String orgId,
      final String orgName, final String empId, final String empName,
      final String cardNum, final String begDate, final String endDate,
      final String batchNum) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          session.clear();
          String sql = " select a001.id as orgid," + " b001.name as orgname,"
              + " a411.contract_id, a411.year_month,"
              + " a411.should_corpus, a411.emp_id as empid,"
              + " b002.name as empname, a410.biz_date,a410.batch_num"
              + " from aa001 a001, ba001 b001, aa002 a002,"
              + " ba002 b002, aa410 a410, aa411 a411"
              + " where a001.orginfo_id = b001.id"
              + " and a002.emp_info_id=b002.id and a001.id = a002.org_id"
              + " and a410.id=a411.head_id and a001.id=a411.org_id"
              + " and a002.id=a411.emp_id and a411.collflag=1 ";

          Vector parameters = new Vector();
          String criterion = "";

          if (officeCode != null && !"".equals(officeCode)) {
            criterion += " and b001.officecode = ? ";
            parameters.add(officeCode);
          }
          if (collBankId != null && !"".equals(collBankId)) {
            criterion += " and b001.collection_bank_id = ? ";
            parameters.add(collBankId);
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " and a411.contract_id = ? ";
            parameters.add(contractId);
          }
          if (orgId != null && !"".equals(orgId)) {
            criterion += " and a001.id = ? ";
            parameters.add(new Integer(orgId));
          }
          if (orgName != null && !"".equals(orgName)) {
            criterion += " and b001.name like ? ";
            parameters.add("%" + orgName + "%");
          }
          if (empId != null && !"".equals(empId)) {
            criterion += " and a002.id = ? ";
            parameters.add(new Integer(empId));
          }
          if (empName != null && !"".equals(empName)) {
            criterion += " and b002.name = ? ";
            parameters.add(empName);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " and b002.card_num = ? ";
            parameters.add(cardNum);
          }
          if (begDate != null && !"".equals(begDate)) {
            criterion += " and a410.biz_date >= ? ";
            parameters.add(begDate);
          }
          if (endDate != null && !"".equals(endDate)) {
            criterion += " and a410.biz_date <= ? ";
            parameters.add(endDate);
          }
          if (batchNum != null && !"".equals(batchNum)) {
            criterion += " and a410.batch_num = ? ";
            parameters.add(batchNum);
          }

          sql = sql + criterion;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          CollByFundDTO dto = null;
          Object obj[] = null;
          Iterator iter = query.list().iterator();
          List templist = new ArrayList();
          while (iter.hasNext()) {
            obj = (Object[]) iter.next();
            dto = new CollByFundDTO();
            if (obj[0] != null) {
              dto.setOrgId(new BigDecimal(obj[0].toString()));
            }
            if (obj[1] != null) {
              dto.setOrgName(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setContractId(obj[2].toString());
            }
            if (obj[3] != null) {
              dto.setYearMonth(obj[3].toString());
            }
            if (obj[4] != null) {
              dto.setMoney(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              dto.setEmpId(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null) {
              dto.setEmpName(obj[6].toString());
            }
            if (obj[7] != null) {
              dto.setKouDate(obj[7].toString());
            }
            if (obj[8] != null) {
              dto.setBatchNum(obj[8].toString());
            }
            templist.add(dto);
          }
          return templist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryCollByFundCollBankByCriterions(final String officeCode,
      final String collBankId, final String contractId, final String orgId,
      final String orgName, final String empId, final String empName,
      final String cardNum, final String begDate, final String endDate,
      final String batchNum, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          session.clear();
          String sql = " select b001.collection_bank_id,sum(a411.should_corpus),count(distinct a411.emp_id)"
              + " from aa001 a001, ba001 b001, aa002 a002,"
              + " ba002 b002, aa410 a410, aa411 a411"
              + " where a001.orginfo_id = b001.id"
              + " and a002.emp_info_id=b002.id and a001.id = a002.org_id"
              + " and a410.id=a411.head_id and a001.id=a411.org_id"
              + " and a002.id=a411.emp_id and a411.collflag=1 ";

          Vector parameters = new Vector();
          String criterion = "";

          if (officeCode != null && !"".equals(officeCode)) {
            criterion += " and b001.officecode = ? ";
            parameters.add(officeCode);
          }
          if (collBankId != null && !"".equals(collBankId)) {
            criterion += " and b001.collection_bank_id = ? ";
            parameters.add(collBankId);
          }
          if (orgId != null && !"".equals(orgId)) {
            criterion += " and a001.id = ? ";
            parameters.add(new Integer(orgId));
          }
          if (contractId != null && !"".equals(contractId)) {
            criterion += " and a411.contract_id = ? ";
            parameters.add(contractId);
          }
          if (orgName != null && !"".equals(orgName)) {
            criterion += " and b001.name like ? ";
            parameters.add("%" + orgName + "%");
          }
          if (empId != null && !"".equals(empId)) {
            criterion += " and a002.id = ? ";
            parameters.add(new Integer(empId));
          }
          if (empName != null && !"".equals(empName)) {
            criterion += " and b002.name = ? ";
            parameters.add(empName);
          }
          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " and b002.card_num = ? ";
            parameters.add(cardNum);
          }
          if (begDate != null && !"".equals(begDate)) {
            criterion += " and a410.biz_date >= ? ";
            parameters.add(begDate);
          }
          if (endDate != null && !"".equals(endDate)) {
            criterion += " and a410.biz_date <= ? ";
            parameters.add(endDate);
          }
          if (batchNum != null && !"".equals(batchNum)) {
            criterion += " and a410.batch_num = ? ";
            parameters.add(batchNum);
          }
          sql = sql + criterion + " group by b001.collection_bank_id ";
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          CollByFundBankDTO dto = null;
          Object obj[] = null;
          Iterator iter = query.list().iterator();
          List templist = new ArrayList();
          while (iter.hasNext()) {
            obj = (Object[]) iter.next();
            dto = new CollByFundBankDTO();
            if (obj[0] != null) {
              dto.setCollBankId(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setMoney(new BigDecimal(obj[1].toString()));
            }
            if (obj[2] != null) {
              dto.setCount(Integer.parseInt(obj[2].toString()));
            }
            templist.add(dto);
          }
          return templist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public BigDecimal queryCollByFundSumByCriterions(final String officeCode,
      final String collBankId, final String contractId, final String orgId,
      final String orgName, final String empId, final String empName,
      final String cardNum, final String begDate, final String endDate,
      final String batchNum) {
    BigDecimal num = new BigDecimal(0.00);
    try {
      num = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              session.clear();
              String sql = " select nvl(sum(a411.should_corpus),0)"
                  + " from aa001 a001, ba001 b001, aa002 a002,"
                  + " ba002 b002, aa410 a410, aa411 a411"
                  + " where a001.orginfo_id = b001.id"
                  + " and a002.emp_info_id=b002.id and a001.id = a002.org_id"
                  + " and a410.id=a411.head_id and a001.id=a411.org_id"
                  + " and a002.id=a411.emp_id and a411.collflag=1 ";

              Vector parameters = new Vector();
              String criterion = "";

              if (officeCode != null && !"".equals(officeCode)) {
                criterion += " and b001.officecode = ? ";
                parameters.add(officeCode);
              }
              if (collBankId != null && !"".equals(collBankId)) {
                criterion += " and b001.collection_bank_id = ? ";
                parameters.add(collBankId);
              }
              if (contractId != null && !"".equals(contractId)) {
                criterion += " and a411.contract_id = ? ";
                parameters.add(contractId);
              }
              if (orgId != null && !"".equals(orgId)) {
                criterion += " and a001.id = ? ";
                parameters.add(new Integer(orgId));
              }
              if (orgName != null && !"".equals(orgName)) {
                criterion += " and b001.name like ? ";
                parameters.add("%" + orgName + "%");
              }
              if (empId != null && !"".equals(empId)) {
                criterion += " and a002.id = ? ";
                parameters.add(new Integer(empId));
              }
              if (empName != null && !"".equals(empName)) {
                criterion += " and b002.name = ? ";
                parameters.add(empName);
              }
              if (cardNum != null && !"".equals(cardNum)) {
                criterion += " and b002.card_num = ? ";
                parameters.add(cardNum);
              }
              if (begDate != null && !"".equals(begDate)) {
                criterion += " and a410.biz_date >= ? ";
                parameters.add(begDate);
              }
              if (endDate != null && !"".equals(endDate)) {
                criterion += " and a410.biz_date <= ? ";
                parameters.add(endDate);
              }
              if (batchNum != null && !"".equals(batchNum)) {
                criterion += " and a410.batch_num = ? ";
                parameters.add(batchNum);
              }

              sql = sql + criterion;
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return new BigDecimal(query.uniqueResult().toString());
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return num;
  }
  public String queryCollByFundDateByCriterions(final String officeCode,
      final String collBankId, final String contractId, final String orgId,
      final String orgName, final String empId, final String empName,
      final String cardNum, final String begDate, final String endDate,
      final String batchNum) {
    String date = "";
    try {
      date = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
              session.clear();
              String sql = " select min(a410.biz_date),max(a410.biz_date)"
                + " from aa001 a001, ba001 b001, aa002 a002,"
                + " ba002 b002, aa410 a410, aa411 a411"
                + " where a001.orginfo_id = b001.id"
                + " and a002.emp_info_id=b002.id and a001.id = a002.org_id"
                + " and a410.id=a411.head_id and a001.id=a411.org_id"
                + " and a002.id=a411.emp_id and a411.collflag=1 ";
              
              Vector parameters = new Vector();
              String criterion = "";
              
              if (officeCode != null && !"".equals(officeCode)) {
                criterion += " and b001.officecode = ? ";
                parameters.add(officeCode);
              }
              if (collBankId != null && !"".equals(collBankId)) {
                criterion += " and b001.collection_bank_id = ? ";
                parameters.add(collBankId);
              }
              if (contractId != null && !"".equals(contractId)) {
                criterion += " and a411.contract_id = ? ";
                parameters.add(contractId);
              }
              if (orgId != null && !"".equals(orgId)) {
                criterion += " and a001.id = ? ";
                parameters.add(new Integer(orgId));
              }
              if (orgName != null && !"".equals(orgName)) {
                criterion += " and b001.name like ? ";
                parameters.add("%" + orgName + "%");
              }
              if (empId != null && !"".equals(empId)) {
                criterion += " and a002.id = ? ";
                parameters.add(new Integer(empId));
              }
              if (empName != null && !"".equals(empName)) {
                criterion += " and b002.name = ? ";
                parameters.add(empName);
              }
              if (cardNum != null && !"".equals(cardNum)) {
                criterion += " and b002.card_num = ? ";
                parameters.add(cardNum);
              }
              if (begDate != null && !"".equals(begDate)) {
                criterion += " and a410.biz_date >= ? ";
                parameters.add(begDate);
              }
              if (endDate != null && !"".equals(endDate)) {
                criterion += " and a410.biz_date <= ? ";
                parameters.add(endDate);
              }
              if (batchNum != null && !"".equals(batchNum)) {
                criterion += " and a410.batch_num = ? ";
                parameters.add(batchNum);
              }
              sql = sql + criterion;
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Object obj[]=(Object[])query.uniqueResult();
              if(obj[0]!=null && obj[1]!=null){
                if(obj[0].toString().equals(obj[1].toString())){
                  return obj[0].toString();
                }else{
                  return obj[0].toString() + " 至 " + obj[1].toString();
                }
              }else{
                return "";
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return date;
  }

}
