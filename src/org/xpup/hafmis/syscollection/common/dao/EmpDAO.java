package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.CardMunChange;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpAgentExportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseInfoDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.peoplebank.documents.dto.BankInterfaceInfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.dto.EmployeeInfoDisplayDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.dto.EmployeeInfoSearchDTO;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonEmpAccountDTO;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonEmpAccountPopDTO;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonEmpInfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonEmpPopDTO;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;
import org.xpup.hafmis.sysloan.common.biz.empaccountpop.dto.EmpAccountPopDTO;
import org.xpup.hafmis.sysloan.common.loanconditionsset.LoanConditionsParamSetDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyNewDTO;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyDTO;

public class EmpDAO extends HibernateDaoSupport {

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

  /**
   * 李文浩..系统查询->职工信息列表的查询方法
   */
  public List getEmployeeInfoList(final EmployeeInfoSearchDTO dto,
      final String orderBy, final String order, final int start,
      final int pageSize, final SecurityInfo info) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // e.org.id "+info.getGjjSecurityHqlSQL() 加上这个句话就是代表给任何条件都加上权限了
          // ;e.getOrg().getOrgInfo()
          String hql = "from Emp e where e.org.id "
              + info.getGjjSecurityHqlSQL() + " and ";
          List parameters = new ArrayList();
          String criterion = "";
          if (dto.getOrgId() != null && !dto.getOrgId().trim().equals("")) {
            // System.out.println("根据单位编号查找结果..."+dto.getOrgId());
            criterion += "e.org.id = ?  and ";
            parameters.add(new Integer(dto.getOrgId().trim()));
          }
          if (dto.getOrgName() != null && !dto.getOrgName().trim().equals("")) {
            // System.out.println("根据单位名称查找结果..."+dto.getOrgName());
            criterion += "e.org.orgInfo.name like ?  and ";
            parameters.add("%" + dto.getOrgName().trim() + "%");
          }
          if (dto.getEmpId() != null && !dto.getEmpId().trim().equals("")) {
            // System.out.println("根据职工编号来查找..."+dto.getEmpId());
            criterion += "e.empId = ?  and ";
            parameters.add(new Integer(dto.getEmpId().trim()));
          }
          if (dto.getEmpName() != null && !dto.getEmpName().trim().equals("")) {
            // System.out.println("根据职工姓名来查找..."+dto.getEmpName());
            criterion += "e.empInfo.name  like ?  and ";
            parameters.add("%" + dto.getEmpName().trim() + "%");
          }
          if (dto.getIdentityCard() != null
              && !dto.getIdentityCard().trim().equals("")) {// 输入任何证件号码都能够进去,不光是身份证号码
            // System.out.println("按照证件号码来查找...->"+dto.getIdentityCard());
            criterion += "e.empInfo.cardNum = ?  and ";
            parameters.add(dto.getIdentityCard().trim());
          }
          if (dto.getSex() != null && !dto.getSex().equals("")) {
            // System.out.println("按照男女来查找------>"+dto.getSex());
            criterion += "e.empInfo.sex = ?  and ";
            parameters.add(new BigDecimal(dto.getSex().trim()));
          }
          if (dto.getPayState() != null && !dto.getPayState().equals("")) {
            // System.out.println("缴存状态-->"+dto.getPayState());
            criterion += "e.payStatus = ?  and ";
            parameters.add(new BigDecimal(dto.getPayState().trim()));
          }
          if (dto.getSalaryStart() != null
              && !dto.getSalaryStart().trim().equals("")
              && dto.getSalaryEnd() != null
              && !dto.getSalaryEnd().trim().equals("")) {
            // System.out.println("进入查找基本工资的方法-->"+dto.getSalaryStart()+","+dto.getSalaryEnd());
            criterion += " e.salaryBase between ? and ? and";
            parameters.add(new BigDecimal(dto.getSalaryStart().trim()));
            parameters.add(new BigDecimal(dto.getSalaryEnd().trim()));
          }
          if (dto.getOrgPayStart() != null
              && !dto.getOrgPayStart().trim().equals("")
              && dto.getOrgPayEnd() != null
              && !dto.getOrgPayEnd().trim().equals("")) {
            // System.out.println("进入查找单位缴额的方法-->"+dto.getOrgPayStart()+","+dto.getOrgPayEnd());
            criterion += " e.orgPay between ? and ? and";
            parameters.add(new BigDecimal(dto.getOrgPayStart().trim()));
            parameters.add(new BigDecimal(dto.getOrgPayEnd().trim()));
          }
          if (dto.getEmpPayStart() != null
              && !dto.getEmpPayStart().trim().equals("")
              && dto.getEmpPayEnd() != null
              && !dto.getEmpPayEnd().trim().equals("")) {
            // System.out.println("进入查找职工缴额的方法-->"+dto.getEmpPayStart()+","+dto.getEmpPayEnd());
            criterion += " e.empPay between ? and ? and";
            parameters.add(new BigDecimal(dto.getEmpPayStart().trim()));
            parameters.add(new BigDecimal(dto.getEmpPayEnd().trim()));
          }
          String ob = orderBy;
          if (ob == null) {
            ob = " e.empId ";
          }
          String or = order;
          if (or == null) {
            or = "desc";
          }
          if (criterion.length() != 0) {// 如果有条件的话..因为程序指定不能够无条件查询..那么这个集合里一不会为null
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion + " order by " + ob + " " + or;
          }
          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start).setMaxResults(pageSize);
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
   * 
   */
  public String getOrgPayMonthEmpPayMonth(final String orgid, final String empid) {
    String ret = "";
    try {
      ret = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select case when (a.emp_pay_month=a.org_pay_month) then '1' else '2' end from aa002 a where a.org_id = ? and a.id = ? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(orgid));
          query.setParameter(1, new Integer(empid));
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return ret;
  }

  public String getAA307PhotoURL(final String pickheadid) {
    String ret = "";
    try {
      ret = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(a6.id) from aa307 a7,aa306 a6 "
              + "where a7.pickhead_id=a6.id and a7.photourl is null "
              + "and a6.id = ? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(pickheadid));
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return ret;
  }

  /**
   * 职工基本信息统计查询 计算年提取的金额..次数 郭婧平
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
          String hql = "select count(h.id),nvl(sum(t.pickCurBalance+t.pickPreBalance),0) from PickHead h,PickTail t "
              + "where t.pickHead.id = h.id and h.settDate between ? and ? and t.empId=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, pickDate1);
          query.setParameter(1, pickDate2);
          query.setParameter(2, new Integer(empId));
          Iterator it = query.iterate();
          Object obj[] = null;
          List l = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            EmployeeInfoDisplayDTO employeeInfoDisplayDTO = new EmployeeInfoDisplayDTO();
            employeeInfoDisplayDTO.setPickcount(new Integer(obj[0].toString()));
            employeeInfoDisplayDTO.setPickmoney(obj[1].toString());
            l.add(employeeInfoDisplayDTO);
          }
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
   * 职工基本信息统计查询
   */
  public List getEmployeeInfoList_LY(final EmployeeInfoSearchDTO dto,
      final String orderBy, final String order, final int start,
      final int pageSize, final SecurityInfo info) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a2.id,a2.salary_base,a2.org_pay,a2.emp_pay,"
              + " b2.name,b2.card_num,(a2.org_pay+a2.emp_pay),"
              + " a2.pay_status,a2.org_pay_month, a2.emp_pay_month,"
              + " b2.sex,b2.card_kind,"
              + " a1.pay_mode,a1.id orgid,(a2.pre_balance+a2.cur_balance),a2.pay_oldyear"
              + " from aa001 a1, ba001 b1, aa002 a2, ba002 b2"
              + " where a1.orginfo_id = b1.id" + " and a2.org_id = a1.id"
              + " and a2.emp_info_id = b2.id" + " and a1.id "
              + info.getGjjSecuritySQL();
          List parameters = new ArrayList();
          String criterion = "";
          if (dto.getOrgId() != null && !dto.getOrgId().trim().equals("")) {
            criterion += "a1.id = ?  and ";
            parameters.add(new Integer(dto.getOrgId().trim()));
          }
          if (dto.getOrgName() != null && !dto.getOrgName().trim().equals("")) {

            criterion += "b1.name like ?  and ";
            parameters.add("%" + dto.getOrgName().trim() + "%");
          }
          if (dto.getEmpId() != null && !dto.getEmpId().equals("")) {
            criterion += "a2.id = ? and ";
            parameters.add(new Integer(dto.getEmpId().trim()));
          }
          if (dto.getEmpName() != null && !dto.getEmpName().equals("")) {
            criterion += "b2.name like ? and ";
            parameters.add("%" + dto.getEmpName() + "%");
          }
          if (dto.getIdentityCard() != null
              && !dto.getIdentityCard().trim().equals("")) {
            String tempCardNum = "";
            if (dto.getIdentityCard().trim().length() == 15) {
              tempCardNum = CardMunChange.get18Id(dto.getIdentityCard().trim());
              criterion += "(b2.card_num = ? or b2.card_num = ?) and ";
              parameters.add(dto.getIdentityCard().trim());
              parameters.add(tempCardNum);
            } else if (dto.getIdentityCard().trim().length() == 18) {
              tempCardNum = CardMunChange.get15Id(dto.getIdentityCard().trim());
              criterion += "(b2.card_num = ? or b2.card_num = ?) and ";
              parameters.add(dto.getIdentityCard().trim());
              parameters.add(tempCardNum);
            } else {
              criterion += "b2.card_num like ?  and ";
              parameters.add("%" + dto.getIdentityCard().trim() + "%");
            }
          }
          if (dto.getSex() != null && !dto.getSex().equals("")) {
            criterion += "b2.sex= ?  and ";
            parameters.add(new BigDecimal(dto.getSex().trim()));
          }
          if (dto.getPayState() != null && !dto.getPayState().equals("")) {
            criterion += "a2.pay_status = ?  and ";
            parameters.add(new BigDecimal(dto.getPayState().trim()));
          }
          if (dto.getEmpBalance() != null && !dto.getEmpBalance().equals("")) {
            criterion += "(a2.pre_balance+a2.cur_balance) > 0  and ";
          }
          if (dto.getSalaryStart() != null
              && !dto.getSalaryStart().trim().equals("")
              && dto.getSalaryEnd() != null
              && !dto.getSalaryEnd().trim().equals("")) {
            criterion += " a2.salary_base between ? and ? and";
            parameters.add(new BigDecimal(dto.getSalaryStart().trim()));
            parameters.add(new BigDecimal(dto.getSalaryEnd().trim()));
          }
          if (dto.getOrgPayStart() != null
              && !dto.getOrgPayStart().trim().equals("")
              && dto.getOrgPayEnd() != null
              && !dto.getOrgPayEnd().trim().equals("")) {
            criterion += " a2.org_pay between ? and ? and";
            parameters.add(new BigDecimal(dto.getOrgPayStart().trim()));
            parameters.add(new BigDecimal(dto.getOrgPayEnd().trim()));
          }
          if (dto.getEmpPayStart() != null
              && !dto.getEmpPayStart().trim().equals("")
              && dto.getEmpPayEnd() != null
              && !dto.getEmpPayEnd().trim().equals("")) {
            criterion += " a2.emp_pay between ? and ? and";
            parameters.add(new BigDecimal(dto.getEmpPayStart().trim()));
            parameters.add(new BigDecimal(dto.getEmpPayEnd().trim()));
          }
          String ob = orderBy;
          if (ob == null) {
            ob = " a2.id ";
          }
          String od = order;
          if (od == null) {
            od = "desc";
          }
          if (criterion.length() != 0) {// 如果有条件的话..因为程序指定不能够无条件查询..那么这个集合里一不会为null
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }
          sql += criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
//          query.setFirstResult(start);
//          query.setMaxResults(pageSize);
          query.setFirstResult(start);
          if (pageSize != 0)
            query.setMaxResults(pageSize);
          List tl= new ArrayList();
          tl=query.list();
          Iterator it = tl.iterator();
          Object obj[] = null;
          List l = new ArrayList();
          EmployeeInfoDisplayDTO employeeInfoDisplayDTO = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            employeeInfoDisplayDTO = new EmployeeInfoDisplayDTO();
            employeeInfoDisplayDTO.setEmpid(new Integer(obj[0].toString()));
            employeeInfoDisplayDTO.setSalarybase(obj[1].toString());
            employeeInfoDisplayDTO.setOrgpay(obj[2].toString());
            employeeInfoDisplayDTO.setEmppay(obj[3].toString());
            employeeInfoDisplayDTO.setEmpname(obj[4].toString());
            employeeInfoDisplayDTO.setCardnum(obj[5].toString());
            employeeInfoDisplayDTO.setPaycount(obj[6].toString());
            employeeInfoDisplayDTO.setPaystatus(obj[7].toString());
            try {
              employeeInfoDisplayDTO.setPaystatus(BusiTools.getBusiValue(
                  Integer.parseInt(employeeInfoDisplayDTO.getPaystatus()),
                  BusiConst.OLDPAYMENTSTATE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            employeeInfoDisplayDTO.setOrgpaymonth(obj[8].toString());
            employeeInfoDisplayDTO.setEmppaymonth(obj[9].toString());
            employeeInfoDisplayDTO.setOrgid(new Integer(obj[13].toString()));
            employeeInfoDisplayDTO.setBalance(obj[14].toString());
            if (obj[15] != null)
              employeeInfoDisplayDTO.setPayOldYear(new BigDecimal(obj[15]
                  .toString()));

            l.add(employeeInfoDisplayDTO);
          }
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
   * 根据单位编号和职工编号查询
   * 
   * @param empId
   * @param orgId
   * @return
   */
  public List queryByEmpId_lg(final Integer empId, final Integer orgId) {
    Validate.notNull(empId);
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
   * 李娟 根据单位ID查询职工状态为1正常3销户4调出的 职工应缴总额
   * 
   * @param orgid
   * @return 正常汇缴
   */
  public BigDecimal queryEmppay(final Serializable orgid) {
    BigDecimal empPay = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(emp.empPay) from Emp emp ";
            Vector parameters = new Vector();
            String criterion = "";
            if (orgid != null) {
              criterion = " where (emp.payStatus=1 or emp.payStatus=3 or emp.payStatus=4 ) and emp.org.id = ?";
              parameters.add(new Integer(orgid.toString()));
            }
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));
            return query.uniqueResult();
          }
        });
    if (empPay == null) {
      empPay = new BigDecimal(0.00);
    }
    return empPay;
  }

  /**
   * 李娟 根据单位ID查询职工状态为1正常3销户4调出 单位应缴总额
   * 
   * @param orgid
   * @return 正常汇缴
   */
  public BigDecimal queryOrgpay(final Serializable orgid) {
    BigDecimal orgPay = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(emp.orgPay) from Emp emp ";
            Vector parameters = new Vector();
            String criterion = "";
            if (orgid != null) {
              criterion = " where (emp.payStatus=1 or emp.payStatus=3 or emp.payStatus=4) and emp.org.id = ?";
              parameters.add(new Integer(orgid.toString()));
            }
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));
            return query.uniqueResult();
          }
        });
    if (orgPay == null) {
      orgPay = new BigDecimal(0.00);
    }
    return orgPay;
  }

  /**
   * 李文浩..根据此单位id和职工id来获得这个职工能够最多提取多少钱
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
   * 李文浩 获得提取的导出数据
   */
  public List getExportData(final int orgId) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // select info.name from aa001 org,aa002 emp,ba002 info
          // where emp.org_id = org.id and info.id = emp.emp_info_id
          // and org.id = 33
          // 职工编号、职工姓名、证件类型、证件号码
          String hql = "from Emp emp where emp.org.id=? ";

          List l = session.createQuery(hql).setInteger(0, orgId).list();
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
   * 查询状态为134的员工
   */
  public List getExportData_wsh(final int orgId) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // select info.name from aa001 org,aa002 emp,ba002 info
          // where emp.org_id = org.id and info.id = emp.emp_info_id
          // and org.id = 33
          // 职工编号、职工姓名、证件类型、证件号码
          String hql = "from Emp emp where emp.org.id=? and emp.payStatus in(1,3,4) ";

          List l = session.createQuery(hql).setInteger(0, orgId).list();
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
   * 李娟 根据单位ID查询职工状态为1正常3销户4调出的人数
   * 
   * @param orgid
   * @return
   */
  public Integer queryEmpCount(final Serializable orgid) {
    Integer empCount = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(*) from Emp emp ";
            Vector parameters = new Vector();
            String criterion = "";
            if (orgid != null) {
              criterion = " where (emp.payStatus=1 or emp.payStatus=3 or emp.payStatus=4) and emp.org.id = ?";
              parameters.add(new Integer(orgid.toString()));
            }
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));
            return query.uniqueResult();
          }
        });

    return empCount;

  }

  /**
   * 李娟
   * 
   * @param orgId
   * @param payStatus
   * @return
   * @throws Exception
   */
  public List queryEmpList(final Serializable orgId, final String[] payStatus)
      throws Exception {
    List empList = null;
    try {
      empList = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from Emp emp  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {
            criterion += "emp.org.id = ?  and ";
            parameters.add(new Integer(orgId.toString()));
          }
          if (payStatus != null && payStatus.length > 0) {
            criterion += "( ";
            for (int i = 0; i < payStatus.length; i++) {
              criterion += "emp.payStatus = ? or ";
              parameters.add(new BigDecimal(payStatus[i]));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

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

  /*
   * 职工弹出框查询
   */
  public List queryByCriterionsSL(final Serializable id, final String name,
      final String empid, final String orderBy, final String order,
      final int start, final int pageSize, final String[] status,
      final String cardnum, final String empname) {

    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select emp from  Emp emp, Org org ";
          Vector parameters = new Vector();
          String criterion = "";
          if (id != null && !id.equals("")) {
            criterion += "org.id=emp.org.id and org.id=? and  ";
            parameters.add(new Integer(id.toString()));
          }
          if (empid != null && !empid.equals("")) {
            criterion += "emp.empId=? and ";
            parameters.add(new Integer(empid));
          }

          if (name != null && !name.equals("")) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add("%" + name + "%");
          }
          if (empname != null && !empname.equals("")) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add(empname);
          }
          if (cardnum != null && !cardnum.equals("")) {
            criterion += "(emp.empInfo.cardNum like ? escape '/' or emp.empInfo.standbyCardNum like ? escape '/') and ";
            parameters.add("%" + cardnum + "%");
            parameters.add("%" + cardnum + "%");
          }
          if (status != null && status.length > 0) {
            criterion += "( ";
            for (int i = 0; i < status.length; i++) {
              criterion += "emp.payStatus = ? or ";
              parameters.add(new BigDecimal(status[i]));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          List empList = query.list();
          session.clear();
          return empList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 职工滩出框求和查询
   */
  public int queryCountByCriterionsSL(final Serializable id, final String name,
      final String empid, final String[] status, final String cardnum,
      final String empname) {

    int count = 0;
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  emp from  Emp emp, Org org ";
          Vector parameters = new Vector();
          String criterion = "";
          if (id != null && !id.equals("")) {
            criterion += "org.id=emp.org.id and org.id=? and  ";
            parameters.add(new Integer(id.toString()));

          }
          if (empid != null && !empid.equals("")) {
            criterion += "emp.empId=? and ";
            parameters.add(new Integer(empid));
          }

          if (name != null && !name.equals("")) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add("%" + name + "%");
          }
          if (empname != null && !empname.equals("")) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add(empname);
          }
          if (cardnum != null && !cardnum.equals("")) {
            criterion += "(emp.empInfo.cardNum like ? escape '/' or emp.empInfo.standbyCardNum like ? escape '/') and ";
            parameters.add("%" + cardnum + "%");
            parameters.add("%" + cardnum + "%");
          }
          if (status != null && status.length > 0) {
            criterion += "( ";
            for (int i = 0; i < status.length; i++) {
              criterion += "emp.payStatus = ? or ";
              parameters.add(new BigDecimal(status[i]));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          session.clear();

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List l = query.list();
          System.out.println(l.size());
          return query.list();
        }
      });
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
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

  public Emp queryByCriterions_wsh(final String empid, final String orgid) {

    Emp emp = null;
    emp = (Emp) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select id,pk_id,org_id,emp_info_id,salary_base,org_pay,emp_pay,org_pay_month,emp_pay_month,"
            + "pre_integral,cur_integral,pre_balance,cur_balance,pay_status,"
            + "pre_integral_sub_a,cur_integral_sub_a,pre_rate_a,cur_rate_a,"
            + "pre_integral_sub_b,cur_integral_sub_b,pre_rate_b,cur_rate_b,"
            + "pre_integral_sub_c,cur_integral_sub_c,pre_rate_c,cur_rate_c,"
            + "reservea_a,reservea_b,reservea_c,emp_id_old,emp_agent_num,"
            + "cur_integral_sub_d,pre_integral_sub_d,pre_rate_d,cur_rate_d,p"
            + "re_integral_sub_e,cur_integral_sub_e,pre_rate_e,cur_rate_e,"
            + "pre_integral_sub_f,cur_integral_sub_f,pre_rate_f,cur_rate_f,"
            + "pre_integral_sub_g,cur_integral_sub_g,pre_rate_g,cur_rate_g,"
            + "pre_integral_sub_h,cur_integral_sub_h,pre_rate_h,cur_rate_h,"
            + "pre_integral_sub_i,cur_integral_sub_i,pre_rate_i,cur_rate_i,"
            + "pre_integral_sub_j,cur_integral_sub_j,pre_rate_j,cur_rate_j,"
            + "pre_integral_sub_k,cur_integral_sub_k,pre_rate_k,cur_rate_k,"
            + "pre_integral_sub_l,cur_integral_sub_l,pre_rate_l,cur_rate_l,pay_oldyear from aa002 where aa002.id="
            + empid + " and aa002.org_id=" + orgid + "  ";
        Query query0 = session.createSQLQuery(hql);
        Object obj[] = null;
        if (query0.uniqueResult() == null) {
          return null;
        }
        obj = (Object[]) query0.uniqueResult();
        Emp emp_1 = new Emp();
        emp_1.setId(obj[0].toString());
        emp_1.getOrg().setId(obj[2].toString());
        emp_1.getEmpInfo().setId(obj[3].toString());
        emp_1.setSalaryBase(new BigDecimal(obj[4].toString()));
        emp_1.setOrgPay(new BigDecimal(obj[5].toString()));
        emp_1.setEmpPay(new BigDecimal(obj[6].toString()));
        emp_1.setOrgPayMonth(obj[7].toString());
        emp_1.setEmpPayMonth(obj[8].toString());
        emp_1.setPreIntegral(new BigDecimal(obj[9].toString()));
        emp_1.setCurIntegral(new BigDecimal(obj[10].toString()));
        emp_1.setPreBalance(new BigDecimal(obj[11].toString()));
        emp_1.setCurBalance(new BigDecimal(obj[12].toString()));
        emp_1.setPayStatus(new BigDecimal(obj[13].toString()));
        emp_1.setPreIntegralSubA(new BigDecimal(obj[14].toString()));
        emp_1.setCurIntegralSubA(new BigDecimal(obj[15].toString()));
        emp_1.setPreRateA(new BigDecimal(obj[16].toString()));
        emp_1.setCurRateA(new BigDecimal(obj[17].toString()));

        emp_1.setPreIntegralSubB(new BigDecimal(obj[14].toString()));
        emp_1.setCurIntegralSubB(new BigDecimal(obj[15].toString()));
        emp_1.setPreRateB(new BigDecimal(obj[16].toString()));
        emp_1.setCurRateB(new BigDecimal(obj[17].toString()));
        emp_1.setPreIntegralSubC(new BigDecimal(obj[14].toString()));
        emp_1.setCurIntegralSubC(new BigDecimal(obj[15].toString()));
        emp_1.setPreRateC(new BigDecimal(obj[16].toString()));
        emp_1.setCurRateC(new BigDecimal(obj[17].toString()));
        emp_1.setPreIntegralSubD(new BigDecimal(obj[14].toString()));
        emp_1.setCurIntegralSubD(new BigDecimal(obj[15].toString()));
        emp_1.setPreRateA(new BigDecimal(obj[16].toString()));
        emp_1.setCurRateA(new BigDecimal(obj[17].toString()));
        emp_1.setPreIntegralSubA(new BigDecimal(obj[14].toString()));
        emp_1.setCurIntegralSubA(new BigDecimal(obj[15].toString()));
        emp_1.setPreRateA(new BigDecimal(obj[16].toString()));
        emp_1.setCurRateA(new BigDecimal(obj[17].toString()));
        emp_1.setPreIntegralSubA(new BigDecimal(obj[14].toString()));
        emp_1.setCurIntegralSubA(new BigDecimal(obj[15].toString()));
        emp_1.setPreRateA(new BigDecimal(obj[16].toString()));
        emp_1.setCurRateA(new BigDecimal(obj[17].toString()));
        emp_1.setPreIntegralSubA(new BigDecimal(obj[14].toString()));
        emp_1.setCurIntegralSubA(new BigDecimal(obj[15].toString()));
        emp_1.setPreRateA(new BigDecimal(obj[16].toString()));
        emp_1.setCurRateA(new BigDecimal(obj[17].toString()));
        emp_1.setPreIntegralSubA(new BigDecimal(obj[14].toString()));
        emp_1.setCurIntegralSubA(new BigDecimal(obj[15].toString()));
        emp_1.setPreRateA(new BigDecimal(obj[16].toString()));
        emp_1.setCurRateA(new BigDecimal(obj[17].toString()));
        emp_1.setPreIntegralSubA(new BigDecimal(obj[14].toString()));
        emp_1.setCurIntegralSubA(new BigDecimal(obj[15].toString()));
        emp_1.setPreRateA(new BigDecimal(obj[16].toString()));
        emp_1.setCurRateA(new BigDecimal(obj[17].toString()));
        emp_1.setPreIntegralSubA(new BigDecimal(obj[14].toString()));
        emp_1.setCurIntegralSubA(new BigDecimal(obj[15].toString()));
        emp_1.setPreRateA(new BigDecimal(obj[16].toString()));
        emp_1.setCurRateA(new BigDecimal(obj[17].toString()));
        emp_1.setPreIntegralSubA(new BigDecimal(obj[14].toString()));
        emp_1.setCurIntegralSubA(new BigDecimal(obj[15].toString()));
        emp_1.setPreRateA(new BigDecimal(obj[16].toString()));
        emp_1.setCurRateA(new BigDecimal(obj[17].toString()));
        return query0.uniqueResult();
      }
    });

    return emp;
  }

  public List queryByCriterionsWZQ(final String empid, final String orgid) {
    List emplist = null;
    emplist = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {

          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {

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
              criterion = "where  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;

            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }
            return query0.list();
          }
        });
    return emplist;
  }

  /**
   * 王菱
   * 判断同一职工姓名和证件号码的人是否在该单位存在：BA002、AA002关联，BA002中职工姓名和证件号码等于录入的值，AA002中的单位编码等于录入的值，是否存在这样的记录
   * param orgid param empName, param cardNum,证件号码 return emp
   */
  public List getEmp_WL(final String orgID, final String empName,
      final String cardNum) {

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
            criterion += "(emp.empInfo.cardNum = ? or emp.empInfo.standbyCardNum = ?) and ";
            parameters.add(cardNum);
            parameters.add(cardNum);
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
   * 王菱 得到查询单位下的职工状态为1,3,4的工资基数列表
   */
  public List queryEmpSalaryByTIAOJIAN_WL(final String orgID) {

    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select emp.salaryBase from Emp emp  ";

          Vector parameters = new Vector();
          String criterion = "";

          if (orgID != null && !orgID.equals("")) {
            criterion += "emp.org.id = ? and ";
            parameters.add(new Integer(orgID));
          }

          if (criterion.length() != 0)
            criterion = "where  emp.payStatus in (1,3,4) and "
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
   * 王菱 查询 该职工是否是本单位的 空：不是该单位职工 ； 非空：是该单位职工 param orgID param empID, return Emp
   */
  public Emp getChgPersonEmp_WL(final String orgID, final String empID) {

    Emp emp = null;

    emp = (Emp) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from Emp emp where emp.org.id=?  and emp.empId=? ";

        Query query = session.createQuery(hql);
        query.setInteger(0, Integer.parseInt(orgID));
        query.setInteger(1, Integer.parseInt(empID));

        return query.uniqueResult();
      }
    });

    return emp;
  }

  /**
   * 王菱 20070703 根据单位ID 查询该单位变更前应缴总额 条件：职工状态为1、3、4
   */
  public BigDecimal getOldPayment_WL(final String orgID) {

    BigDecimal oldPayment = new BigDecimal(0.00);

    oldPayment = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select nvl(sum(emp.orgPay+emp.empPay),0) from Emp emp where emp.payStatus in (1,3,4) and  emp.org.id=? ";

            Query query = session.createQuery(hql);
            query.setInteger(0, Integer.parseInt(orgID));

            return query.uniqueResult();
          }
        });

    return oldPayment;
  }

  public BigDecimal getOldPayment_wsh(final String orgID) {

    BigDecimal oldPayment = new BigDecimal(0.00);

    oldPayment = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(a.org_pay+a.emp_pay) from aa002 a,aa001 b where a.org_id=b.id and a.pay_status in(1,3,4) and  b.id=? ";

            Query query = session.createSQLQuery(hql);
            query.setInteger(0, Integer.parseInt(orgID));

            return query.uniqueResult();
          }
        });

    return oldPayment;
  }

  public BigDecimal getEmpCount_wsh(final String orgID) {

    BigDecimal oldPayment = new BigDecimal(0.00);

    oldPayment = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(*) from Emp emp where emp.payStatus in (1,3,4) and  emp.org.id=? ";

            Query query = session.createQuery(hql);
            query.setInteger(0, Integer.parseInt(orgID));

            return query.uniqueResult();
          }
        });

    return oldPayment;
  }

  /**
   * 王菱 根据单位ID查询职工状态为1正常3销户4调出的 职工工资基数
   * 
   * @param orgid
   */
  public BigDecimal queryEmpSalary(final Serializable orgid) {
    BigDecimal empSalary = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(emp.salaryBase) from Emp emp ";
            Vector parameters = new Vector();
            String criterion = "";
            if (orgid != null && !orgid.equals("")) {
              criterion = " where (emp.payStatus=1 or emp.payStatus=3 or emp.payStatus=4 ) and emp.org.id = ?";
              parameters.add(new Integer(orgid.toString()));
            }
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));
            return query.uniqueResult();
          }
        });
    if (empSalary == null) {
      empSalary = new BigDecimal(0.00);
    }
    return empSalary;
  }

  /*
   * 职工ID生成算法
   */
  public Integer makeEmpIdSL() {
    Integer value = new Integer(0);
    try {
      value = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql0 = "select nvl(max(emp.empId),0) from Emp emp ";
          Query query0 = session.createQuery(hql0);
          String id0 = query0.uniqueResult().toString();

          String hql1 = "select nvl(max(chg.empId),0) from ChgPersonTail chg ";
          Query query1 = session.createQuery(hql1);
          String id1 = query1.uniqueResult().toString();

          String hql2 = "select nvl(max(tran.empId),0) from  TranInTail tran ";
          Query query2 = session.createQuery(hql2);
          String id2 = query2.uniqueResult().toString();
          String result = "0";
          if ((Integer.valueOf(id0)).intValue()
              - (Integer.valueOf(id1)).intValue() >= 0)
            result = id0;
          else
            result = id1;
          if ((Integer.valueOf(result)).intValue()
              - (Integer.valueOf(id2)).intValue() >= 0)
            return new Integer((Integer.valueOf(result)).intValue() + 1);
          else
            return new Integer((Integer.valueOf(id2)).intValue() + 1);
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return value;
  }

  public List getEmpListWuht(final String orgid) {

    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from Emp emp where emp.payStatus in('1','3','4')";

          Vector parameters = new Vector();
          String criterion = "";

          if (orgid != null && !orgid.equals("")) {
            criterion += " emp.org.id = ? and ";
            parameters.add(new Integer(orgid));
          }

          if (criterion.length() != 0)
            criterion = " and "
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

  public Object[] getSalaryPayAllByOrgId(final String orgid) {
    Object[] obj = null;
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select nvl(sum(t.salary_base),0),nvl(sum(t.emp_pay)+sum(t.org_pay),0) from aa002 t where t.pay_status in (1,3,4) ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgid != null && !orgid.equals("")) {
            criterion += " and t.org_id = ? ";
            parameters.add(new Integer(orgid));
          }
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return (Object[]) query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
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

  public Object[] queryTotalInfoByCriterions(final Serializable orgId,
      final Serializable empId, final String empName, final String cardNum) {
    Object[] obj = null;
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(a2.id)," + " nvl(sum(a2.org_pay),0),"
              + " nvl(sum(a2.emp_pay),0)," + " nvl(sum(a2.salary_base),0),"
              + " nvl(sum(a2.org_pay) + sum(a2.emp_pay),0)"
              + " from aa002 a2, ba002 b2" + " where a2.emp_info_id = b2.id"
              + " and a2.pay_status in ('1','3','4')";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgId != null && !orgId.equals("")) {
            criterion += "to_char(a2.org_id) = ? and ";
            parameters.add(orgId.toString());
          }
          if (empId != null && !empId.equals("")) {
            criterion += "to_char(a2.id) like ? and ";
            parameters.add("%" + empId.toString() + "%");
          }
          if (empName != null && !empName.equals("")) {
            criterion += "b2.name like ? and ";
            parameters.add("%" + empName + "%");
          }
          if (cardNum != null && !cardNum.equals("")) {
            String tempCardNum = "";
            if (cardNum.trim().length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNum.trim());
            else if (cardNum.trim().length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNum.trim());
            criterion += "(b2.card_num = ? or b2.card_num = ?) and ";
            parameters.add(cardNum.trim());
            parameters.add(tempCardNum);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql += criterion;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();

    }
    return obj;
  }

  // 职工开户--分页查询
  /**
   * 付云峰修改
   */
  public List empOpenQueryByCriterionsSL(final Serializable orgId,
      final Serializable id, final String name, final String cardNumber,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select emp from Emp emp ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += "to_char(emp.empId) like ? and ";
            parameters.add("%" + id.toString() + "%");
          }

          if (name != null && !name.equals("")) {
            criterion += "emp.empInfo.name like ? and ";
            parameters.add("%" + name + "%");
          }

          if (cardNumber != null && !cardNumber.trim().equals("")) {
            String tempCardNum = "";
            if (cardNumber.trim().length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNumber.trim());
            else if (cardNumber.trim().length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNumber.trim());
            criterion += "(emp.empInfo.cardNum like ? or emp.empInfo.cardNum like ? escape '/') and ";
            parameters.add(cardNumber.trim());
            parameters.add(tempCardNum);
          }
          if (orgId != null && !orgId.equals("")) {
            criterion += "emp.org.id = ? and ";
            parameters.add(orgId);
          }
          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "emp.empInfo.id";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + order;

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
    } catch (Exception e) {
      e.printStackTrace();

    }
    return list;
  }

  // 职工开户--分页查询求和
  public int empOpenQueryCountByCriterionsSL(final Serializable orgId,
      final Serializable id, final String name, final String cardNumber) {
    Integer value = new Integer(0);
    try {
      value = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(emp) from Emp emp ";

          Vector parameters = new Vector();
          String criterion = "";
          if (id != null && !id.equals("")) {
            criterion += "to_char(emp.empId) like ? and ";
            parameters.add("%" + id.toString() + "%");
          }

          if (name != null && !name.equals("")) {
            criterion += "emp.empInfo.name like ? and ";
            parameters.add("%" + name + "%");
          }

          if (cardNumber != null && !cardNumber.trim().equals("")) {
            String tempCardNum = "";
            if (cardNumber.trim().length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNumber.trim());
            else if (cardNumber.trim().length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNumber.trim());
            criterion += "(emp.empInfo.cardNum like ? or emp.empInfo.cardNum like ? escape '/') and ";
            parameters.add(cardNumber.trim());
            parameters.add(tempCardNum);
          }
          if (orgId != null && !orgId.equals("")) {
            criterion += "emp.org.id = ? and ";
            parameters.add(orgId);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return value.intValue();
  }

  // 自己的
  public List empOpenQueryCountByCriterionsSL1(final Serializable orgId,
      final Serializable id, final String name, final String cardNumber) {

    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select emp from Emp emp ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += "to_char(emp.empId) like ? and ";
            parameters.add("%" + id.toString() + "%");
          }

          if (name != null && !name.equals("")) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add("%" + name + "%");
          }

          if (cardNumber != null && !cardNumber.equals("")) {
            criterion += "(emp.empInfo.cardNum like ? escape '/' or emp.empInfo.standbyCardNum like ? escape '/') and ";
            parameters.add("%" + cardNumber + "%");
            parameters.add("%" + cardNumber + "%");
          }

          if (orgId != null && !orgId.equals("")) {
            criterion += "emp.org.id = ? and ";
            parameters.add(orgId);
          }

          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

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

  public int queryEmployeeCountSL(final String empCard, final String orgId,
      final String empName) {
    Integer value = new Integer(0);
    try {

      value = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(emp.id) from Emp emp ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgId != null) {
            criterion += "emp.org.id = ? and ";
            parameters.add(new Integer(orgId));
          }
          if (empCard != null) {
            criterion += "(emp.empInfo.cardNum = ? or emp.empInfo.standbyCardNum = ?) and ";
            parameters.add(empCard);
            parameters.add(empCard);
          }

          if (empName != null) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add(empName);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return value.intValue();
  }

  /**
   * 李文浩... 根据职工编号和单位单位编号求出来此职工是否存在
   */
  public Emp findEmpByOrdIdAndEmpId(final Integer orgId, final Integer empId) {

    Object obj = new Object();
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Object o = session.createQuery(
              "from Emp e where e.org.id=? and e.empId=?").setParameter(0,
              orgId).setParameter(1, empId).uniqueResult();
          session.clear();
          return o;
        }
      });
      if (obj == null)
        return null;
      return (Emp) obj;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * 根据职工编号和单位单位编号求出来此职工是否存在
   */
  public boolean isEmpAtOrg(final Integer orgId, final Integer empId) {

    boolean flag = false;
    try {
      Object obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Object o = session
              .createSQLQuery(
                  "select aa002.id from AA002 aa002 where aa002.org_id=? and aa002.id=? ")
              .setParameter(0, orgId).setParameter(1, empId).uniqueResult();
          return o;
        }
      });
      if (obj == null) {
        flag = true;
      }

    } catch (Exception s) {
      s.printStackTrace();
      flag = true;
    }
    return flag;
  }

  // 修改职工信息方法
  public void update(String epaId, Emp formEmp, Org org) {

    Validate.notNull(formEmp);
    String name = formEmp.getEmpInfo().getName();
    BigDecimal sex = formEmp.getEmpInfo().getSex();
    BigDecimal cardKind = formEmp.getEmpInfo().getCardKind();
    String cardNumber = formEmp.getEmpInfo().getCardNum();
    String birthday = formEmp.getEmpInfo().getBirthday();
    String department = formEmp.getEmpInfo().getDepartment();
    String telephone = formEmp.getEmpInfo().getTel();
    String mobileTelephone = formEmp.getEmpInfo().getMobileTle();
    BigDecimal monthIncome = formEmp.getEmpInfo().getMonthIncome();
    String empAgentNum = formEmp.getEmpAgentNum();
    String standbyCardNum = formEmp.getEmpInfo().getStandbyCardNum();

    Emp emp = queryById(new Integer(epaId));
    emp.getEmpInfo().setName(name);
    emp.getEmpInfo().setSex(sex);
    emp.getEmpInfo().setCardKind(cardKind);
    emp.getEmpInfo().setCardNum(cardNumber);
    emp.getEmpInfo().setBirthday(birthday);
    emp.getEmpInfo().setDepartment(department);
    emp.getEmpInfo().setTel(telephone);
    emp.getEmpInfo().setMobileTle(mobileTelephone);
    emp.getEmpInfo().setMonthIncome(monthIncome);
    emp.getEmpInfo().setStandbyCardNum(standbyCardNum);
    emp.setEmpAgentNum(empAgentNum);
    // System.out.println("------归集信息字段1" + formEmp.getSalaryBase());
    // emp.setOrg(org);
    emp.setSalaryBase(formEmp.getSalaryBase());
    emp.setEmpPay(formEmp.getEmpPay());
    emp.setOrgPay(formEmp.getOrgPay());
    // System.out.println("------归集信息字段2" + formEmp.getOrg().getPayMode());
    // emp.getOrg().setPayMode(formEmp.getOrg().getPayMode());
    getHibernateTemplate().update(emp);
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

  public void deleteEmpByIdSL_org(final String id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete from Emp emp where emp.org.id =?";
          Query query = session.createQuery(sql);
          query.setString(0, id);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  /*
   * 根据单位ID查职工信息
   */
  public List queryEmpByOrgIdSL(final String orgId) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from Emp emp " + "where emp.org.id = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(orgId));
        return query.list();
      }
    });
    return list;
  }

  /*
   * 根据职工编号查询职工信息
   */

  public List queryEmpInfoWZQ(final String empid) {
    List list = null;
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from Emp emp " + "where emp.empId = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(empid));
        return query.list();
      }
    });
    return list;
  }

  /*
   * 根据单位ID 查询单位应缴金额
   */
  public BigDecimal querySumPayByOrgID_WL(final String orgId) {
    BigDecimal sumPay = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(emp.orgPay+emp.empPay) from Emp emp  "
                + "where emp.payStatus in (1,3,4) and  emp.org.id = ? ";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(orgId));
            return query.uniqueResult();
          }
        });
    return sumPay;
  }

  /*
   * 根据单位ID 查询单位应缴金额
   */
  public int queryPersonCountByOrgID(final String orgId) {
    Integer num = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(emp.empId) from Emp emp  "
                + "where emp.payStatus in (1,3,4) and  emp.org.id = ? ";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(orgId));
            return query.uniqueResult();
          }
        });
    return num.intValue();
  }

  /**
   * 根据输入的条件查询单位与职工信息(List) (ChgPaymentSalaryBase) orgId, orgName chgMonth
   * 
   * @return 吴洪涛
   */
  public List queryChgslarybaseEmpOrgWuht(final String orgId,
      final String orgName, final String chgMonth, final String[] orderBy,
      final String order, final int start, final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List empList = new ArrayList();
        String hql = "select emp.empId ,emp.empInfo.name ,emp.empInfo.cardNum ,emp.salaryBase  from Emp emp ";
        String criterion = "";
        Vector parameters = new Vector();
        if (orgId != null) {
          criterion += "emp.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

        if (criterion.length() != 0)
          criterion = "where emp.payStatus in (1,3,4) and   "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = "";
        if (orderBy != null && orderBy.length > 0) {
          for (int i = 0; i < orderBy.length; i++) {
            if (orderBy[i] == "emp.empInfo.name") {
              ob += "nlssort( " + orderBy[i].toString()
                  + " ,'NLS_SORT=SCHINESE_PINYIN_M'),";
            } else {
              ob += " " + orderBy[i].toString() + " , ";
            }
          }
          ob = ob.substring(0, ob.lastIndexOf(","));
          ob += " ";
        }
        String od = order;
        if (od == null)
          od = "ASC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        Iterator iterator = query.list().iterator();
        ChgslarybaseInfoDTO dto = null;
        Object[] obj = null;

        while (iterator.hasNext()) {
          obj = (Object[]) iterator.next();
          dto = new ChgslarybaseInfoDTO();

          dto.setOrgId(orgId);
          dto.setOrgName(orgName);
          dto.setChgMonth(chgMonth);

          if (obj[0] != null) {
            dto.setEmpId(obj[0].toString());

          }
          if (obj[1] != null) {
            dto.setEmpName(obj[1].toString());
          }
          if (obj[2] != null) {
            dto.setCardNum(obj[2].toString());
          }
          if (obj[3] != null) {
            dto.setOldSalaryBase(obj[3].toString());
          }

          dto.setSalaryBase(null);

          empList.add(dto);

        }

        return empList;

      }
    });

    return list;
  }

  // 工资基数调整，批量导出，应该导出状态为1，3，4的职工
  public List queryChgslarybaseEmpOrgWuhtExports(final String orgId,
      final String orgName, final String chgMonth, final String[] orderBy,
      final String order, final int start, final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List empList = new ArrayList();
        String hql = "select emp.empId ,emp.empInfo.name ,emp.empInfo.cardNum ,emp.salaryBase  from Emp emp ";
        String criterion = "";
        Vector parameters = new Vector();
        if (orgId != null) {
          criterion += "emp.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

        if (criterion.length() != 0)
          criterion = "where emp.payStatus in (1,3,4) and   "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = "";
        if (orderBy != null && orderBy.length > 0) {
          for (int i = 0; i < orderBy.length; i++) {
            if (orderBy[i] == "emp.empInfo.name") {
              ob += "nlssort( " + orderBy[i].toString()
                  + " ,'NLS_SORT=SCHINESE_PINYIN_M'),";
            } else {
              ob += " " + orderBy[i].toString() + " , ";
            }
          }
          ob = ob.substring(0, ob.lastIndexOf(","));
          ob += " ";
        }
        String od = order;
        if (od == null)
          od = "ASC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        Iterator iterator = query.list().iterator();
        ChgslarybaseInfoDTO dto = null;
        Object[] obj = null;

        while (iterator.hasNext()) {
          obj = (Object[]) iterator.next();
          dto = new ChgslarybaseInfoDTO();

          dto.setOrgId(orgId);
          dto.setOrgName(orgName);
          dto.setChgMonth(chgMonth);

          if (obj[0] != null) {
            dto.setEmpId(obj[0].toString());

          }
          if (obj[1] != null) {
            dto.setEmpName(obj[1].toString());
          }
          if (obj[2] != null) {
            dto.setCardNum(obj[2].toString());
          }
          if (obj[3] != null) {
            dto.setOldSalaryBase(obj[3].toString());
          }

          dto.setSalaryBase(null);

          empList.add(dto);

        }

        return empList;

      }
    });

    return list;
  }

  /**
   * 根据输入的条件查询单位与职工信息(List) (Chgpay) orgId, orgName chgMonth
   * 
   * @return 吴洪涛
   */
  public List queryChgpayEmpOrgWuht(final String orgId, final String orgName,
      final String chgMonth, final String orderBy, final String order,
      final int start, final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List empList = new ArrayList();
        String hql = "select emp.empId ,emp.empInfo.name ,emp.empInfo.cardNum ,emp.orgPay,emp.empPay ,emp.salaryBase from Emp emp ";
        String criterion = "";
        Vector parameters = new Vector();
        if (orgId != null) {
          criterion += "emp.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

        if (criterion.length() != 0)
          criterion = "where    "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "emp.empId ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        Iterator iterator = query.list().iterator();
        ChgpayInfoDTO dto = null;
        Object[] obj = null;

        while (iterator.hasNext()) {
          obj = (Object[]) iterator.next();
          dto = new ChgpayInfoDTO();

          dto.setOrgId(orgId);
          dto.setOrgName(orgName);
          dto.setChgMonth(chgMonth);

          if (obj[0] != null) {
            dto.setEmpId(obj[0].toString());

          }
          if (obj[1] != null) {
            dto.setEmpName(obj[1].toString());
          }
          if (obj[2] != null) {
            dto.setCardNum(obj[2].toString());
          }
          if (obj[3] != null) {
            dto.setOldOrgPay(obj[3].toString());
          }
          if (obj[4] != null) {
            dto.setOldEmpPay(obj[4].toString());
          }
          if (obj[5] != null) {
            dto.setSalaryBase(obj[5].toString());
          }

          dto.setOrgPay(null);
          dto.setEmpPay(null);

          empList.add(dto);

        }

        return empList;

      }
    });

    return list;
  }

  public List queryByEmpId(final Integer empId, final Integer orgId) {
    Validate.notNull(empId);
    Validate.notNull(orgId);
    List list = new ArrayList();

    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "from Emp emp ";
          Vector parameters = new Vector();
          String criterion = "";

          if (empId != null) {
            criterion += "  emp.empInfo.id=?  and  ";
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
    return list;
  }

  //
  public List queryEmpById(final Integer empId, final Integer orgId) {
    Validate.notNull(empId);
    Validate.notNull(orgId);
    List list = new ArrayList();

    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

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
    return list;
  }

  /**
   * 王菱 判断同一职工是否已经在其他单位开过户
   */
  public List getOtherOrgMessage_WL(final String orgID, final String empName,
      final String cardNum) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List empList = new ArrayList();
        String hql = "from Emp emp ";
        String criterion = "";
        Vector parameters = new Vector();

        if (orgID != null) {
          criterion += "emp.org.id != ? and ";
          parameters.add(new Integer(orgID));
        }
        if (empName != null) {
          criterion += "emp.empInfo.name = ? and ";
          parameters.add(empName);
        }

        if (cardNum != null) {
          criterion += "(emp.empInfo.cardNum = ? or emp.empInfo.standbyCardNum = ?) and ";
          parameters.add(cardNum);
          parameters.add(cardNum);
        }

        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        hql = hql + criterion;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        empList = query.list();
        return empList;
      }
    });

    return list;
  }

  public List queryByEmpIdlg(final Integer orgId) {

    Validate.notNull(orgId);
    List list = new ArrayList();

    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "from Emp emp ";
          Vector parameters = new Vector();
          String criterion = "";
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
    return list;
  }

  // public List queryEmpBaseInfoCriterion(){
  // List emplist=null;
  // emplist = getHibernateTemplate().executeFind(new HibernateCallback() {
  // public Object doInHibernate(Session session) throws HibernateException,
  // SQLException {
  // List empList = new ArrayList();
  // String hql = "from Emp emp ";
  // String criterion = "";
  // Vector parameters = new Vector();
  //
  //
  // if (orgID != null) {
  // criterion += "emp.org.id != ? and ";
  // parameters.add(new Integer(orgID));
  // }
  // if (empName != null) {
  // criterion += "emp.empInfo.name = ? and ";
  // parameters.add(empName);
  // }
  //        
  // if (cardNum != null) {
  // criterion += "emp.empInfo.cardNum = ? and ";
  // parameters.add(cardNum);
  // }
  //    
  // if (criterion.length() != 0)
  // criterion = "where "
  // + criterion.substring(0, criterion.lastIndexOf("and"));
  //
  // hql = hql + criterion ;
  //
  // Query query = session.createQuery(hql);
  //
  // for (int i = 0; i < parameters.size(); i++) {
  // query.setParameter(i, parameters.get(i));
  // }
  // empList=query.list();
  // return empList;
  // }
  // });
  //
  // return emplist;
  // }
  /**
   * 判断AA002表中是否存在与将要删除的职工empid相同的纪录
   * 
   * @return 如果存在相同的empid返回true，否则返回false。
   */
  public boolean getEmpidCount(final Integer empid) {
    Integer empCount = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(*) from Emp emp ";
            Vector parameters = new Vector();
            String criterion = "";
            if (empid != null) {
              criterion = " where emp.empId = ?";
              parameters.add(new Integer(empid.toString()));
            }
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));
            return query.uniqueResult();
          }
        });
    if (empCount.intValue() > 1) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * 于庆丰 count ba002,职工开户
   * 
   * @param office
   * @param bank
   * @param orgCharacter
   * @param dept
   * @param ragion
   * @param startDate
   * @param endDate
   * @return
   */
  public int queryCountEmpOpen(final String office, final String bank,
      final String orgCharacter, final String dept, final String ragion,
      final String startDate, final String endDate) {
    int count = 0;
    try {
      Integer countInteger = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select count(emp.empInfo.id) from Emp emp ";
              Vector parameters = new Vector();
              String criterion = "";
              if (office != null && !"".equals(office)) {
                criterion += " emp.org.orgInfo.officecode = ? and ";
                parameters.add(office);
              }
              if (bank != null && !"".equals(bank)) {
                criterion += " emp.org.orgInfo.collectionBankId = ? and ";
                parameters.add(bank);
              }
              if (orgCharacter != null && !"".equals(orgCharacter)) {
                criterion += " emp.org.orgInfo.character = ? and ";
                parameters.add(orgCharacter);
              }
              if (dept != null && !"".equals(dept)) {
                criterion += " emp.org.orgInfo.deptInCharge = ? and ";
                parameters.add(dept);
              }
              if (ragion != null && !"".equals(ragion)) {
                criterion += " emp.org.orgInfo.region = ? and ";
                parameters.add(ragion);
              }
              if (startDate != null && !"".equals(startDate) && endDate != null
                  && !"".equals(endDate)) {
                criterion += " (emp.empInfo.opendate  between ?  and  ?)  and ";
                parameters.add(startDate);
                parameters.add(endDate);
              }
              if (criterion.length() != 0)
                // criterion = "where (orgHAFAccountFlow.bizStatus = 3 or
                // orgHAFAccountFlow.bizStatus = 4 or
                // orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id
                // "
                // + securityInfo.getGjjSecurityHqlSQL()
                // + " and "
                // + criterion.substring(0, criterion.lastIndexOf("and"));
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Integer countTemp = new Integer(0);
              Iterator it = query.iterate();
              if (it.hasNext()) {
                countTemp = (Integer) it.next();
              }
              return countTemp;
            }
          });
      count = countInteger.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  public List queryAllEmpList(final String empid,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "from Emp emp  ";

          Vector parameters = new Vector();
          String criterion = "";

          if (empid != null) {
            criterion += "emp.empId = ? and ";
            parameters.add(new Integer(empid));
          }
          if (criterion.length() != 0)
            criterion = "where  emp.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

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

  public List queryAllEmpListBySecurityInfo(final String empid,
      final String empname, final String orderBy, final String order,
      final int start, final int pageSize, final SecurityInfo securityInfo) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "from Emp emp where  emp.org.id "
              + securityInfo.getGjjSecurityHqlSQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (empid != null) {
            criterion += "emp.empId = ? and ";
            parameters.add(new Integer(empid));
          }

          if (empname != null) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add("%" + empname + "%");
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "emp.id";

          String od = order;
          if (od == null)
            od = "ASC";

          hql = hql + criterion + "order by " + ob + " " + order;

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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public int queryCountEmpListBySecurityInfo(final String empid,
      final String empname, final SecurityInfo securityInfo) {
    int count = 0;
    try {
      Integer iCount = new Integer(0);
      iCount = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select count(emp.id) from Emp emp where  emp.org.id "
                  + securityInfo.getGjjSecurityHqlSQL();

              Vector parameters = new Vector();
              String criterion = "";

              if (empid != null) {
                criterion += "emp.empId = ? and ";
                parameters.add(new Integer(empid));
              }

              if (empname != null) {
                criterion += "emp.empInfo.name like ? escape '/' and ";
                parameters.add("%" + empname + "%");
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult();
            }
          });
      count = iCount.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 查询职工姓名和身份证
   * 
   * @return 韩亮
   */
  public EmpInfo findEmpInfoByEmpid(final String empid, final String orgid) {

    EmpInfo empinfo = null;

    empinfo = (EmpInfo) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select distinct b2.name,b2.card_num from aa002 a2,ba002 b2 where a2.emp_info_id=b2.id and a2.id=? and a2.org_id=?";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, empid);
        query.setParameter(1, orgid);

        EmpInfo inf = new EmpInfo();
        Object obj[] = null;
        if (query.uniqueResult() == null) {
          return null;
        }
        obj = (Object[]) query.uniqueResult();
        inf.setName(obj[0].toString());
        inf.setCardNum(obj[1].toString());

        // List ll = query.list();
        // for (int i = 0; i < ll.size(); i++) {
        // obj = (Object[]) ll.get(i);
        // inf.setName(obj[0].toString());
        // inf.setCardNum(obj[1].toString());
        // }
        return inf;
      }
    });

    return empinfo;
  }

  /**
   * 查询借款人姓名，性别，证件类型，号码，出生年月日，住宅电话，移动电话,单位编号，单位名称等
   * 
   * @return 韩亮
   */
  public LoanapplyNewDTO findBorrowInfoByEmpid(final String empid,
      final String orgid) {

    LoanapplyNewDTO empinfo = null;
    try {
      empinfo = (LoanapplyNewDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select distinct b.name as empname, b.sex as sex,  b.card_kind as kind, b.card_num as num, b.birthday as birthday,"
                  + " b.tel as emptel, b.mobile_tle as tle,"
                  + " a1.id as orgid, b1.name as orgname, b1.tel as orgtel, b1.postalcode as code, "
                  + "(select sum(a02.pre_balance + a02.cur_balance) from aa002 a02 where a02.id = a.id and a02.org_id = a1.id) as cardmoney,"
                  + " b1.address as adds, a.salary_base as income, "
                  + " (select sum(a02.org_pay + a02.emp_pay) from aa002 a02 where a02.id = a.id and a02.org_id = a1.id) as monthpay, "
                  + " a.pay_status as status,substr(b.opendate,0,6) as firstmonth,"
                  + " a.emp_pay_month as paymonth,b1.officecode as offices,a.pay_oldyear"
                  + " from ba002 b, aa002 a, ba001 b1, aa001 a1"
                  + " where a.id = ? and a1.id = ? and a.emp_info_id = b.id and a1.orginfo_id = b1.id and a.org_id = a1.id";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, empid);
              query.setParameter(1, orgid);

              LoanapplyNewDTO inf = new LoanapplyNewDTO();
              Object obj[] = null;

              obj = (Object[]) query.uniqueResult();

              inf.setEmpname(obj[0].toString());
              inf.setSex(obj[1].toString());
              inf.setCardking(obj[2].toString());
              inf.setCardnum(obj[3].toString());
              inf.setBirthday(obj[4].toString());
              String tel = "";
              String mtel = "";
              if (obj[5] != null) {
                tel = obj[5].toString();
              }
              inf.setTel(tel);
              if (obj[6] != null) {
                mtel = obj[6].toString();
              }
              inf.setMobiletle(mtel);
              inf.setOrgid(obj[7].toString());
              String orgname = "";
              String orgtel = "";
              String postalcode = "";
              String cardmoney = "";
              String address = "";
              String month_income = "";
              String monthpay = "";
              String pay_status = "";
              String first_pay_month = "";
              String org_pay_month = "";
              if (obj[8] != null) {
                orgname = obj[8].toString();
              }
              if (obj[9] != null) {
                orgtel = obj[9].toString();
              }
              if (obj[10] != null) {
                postalcode = obj[10].toString();
              }
              if (obj[11] != null) {
                cardmoney = obj[11].toString();
              }
              if (obj[12] != null) {
                address = obj[12].toString();
              }
              if (obj[13] != null) {
                month_income = obj[13].toString();
              }
              if (obj[14] != null) {
                monthpay = obj[14].toString();
              }
              if (obj[15] != null) {
                pay_status = obj[15].toString();
              }
              if (obj[16] != null) {
                first_pay_month = obj[16].toString();
              }
              if (obj[17] != null) {
                org_pay_month = obj[17].toString();
              }
              inf.setOrgname(orgname);
              inf.setOrgtel(orgtel);
              inf.setPostalcode(postalcode);
              inf.setCardmoney(cardmoney);
              inf.setAddress(address);
              inf.setMonth_income(month_income);
              inf.setMonthpay(monthpay);
              inf.setPay_status(pay_status);
              inf.setFirst_pay_month(first_pay_month);
              inf.setOrg_pay_month(org_pay_month);
              String d = obj[18].toString();
              inf.setOffices(d);
              if (obj[19] != null) {
                inf.setPay_oldyear(obj[19].toString());
              }
              return inf;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return empinfo;
  }

  /**
   * yuqf 根据empId 查询BA002 AA002 BA001 AA001
   * 
   * @param empId
   */
  public EndorsecontractTdAF queryListByEmpId(final String empId,
      final String orgId) {
    Validate.notNull(empId);
    EndorsecontractTdAF endorsecontractTdAF = null;
    try {
      endorsecontractTdAF = (EndorsecontractTdAF) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              // TODO Auto-generated method stub

              String hql = " select t2.id,t1.name name1 ,"
                  + // 0,1
                  " t1.card_kind,t1.card_num,"
                  + // 2,3
                  " t1.sex,t1.birthday,"
                  + // 4,5
                  " t2.salary_base,t1.tel,"
                  + // 6,7
                  " t1.mobile_tle,t2.pay_status,"
                  + // 8,9
                  " t2.org_pay,t2.emp_pay,"
                  + // 10,11
                  " t2.pre_balance,t2.cur_balance,"
                  + // 12,13
                  " t2.org_id,t4.name,"
                  + // 14,15
                  " t4.address,t4.tel,"
                  + // 16,17
                  " t4.postalcode "
                  + // 18
                  " from ba002 t1,aa002 t2 ,aa001 t3,ba001 t4 "
                  + " where t1.id=t2.emp_info_id and "
                  + " t2.org_id=t3.id and " + " t3.orginfo_id=t4.id ";

              Vector parameters = new Vector();
              String criterion = "";

              if (empId != null && !"".equals(empId)) {
                hql = hql + " and t2.id=" + empId;
              }
              if (orgId != null && !orgId.equals("")) {
                criterion += " t2.org_id = ? and ";
                parameters.add(new Integer(orgId));
              }
              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
              if (query.list().size() != 0) {
                Iterator iterate = query.list().iterator();
                Object[] obj = null;
                while (iterate.hasNext()) {
                  obj = (Object[]) iterate.next();
                  if (obj[0] != null) {
                    endorsecontractTdAF.setEmpId(obj[0].toString());// 职工编号
                  }
                  if (obj[1] != null) {
                    endorsecontractTdAF.setEmpName(obj[1].toString());// 职工姓名
                  }
                  if (obj[2] != null) {
                    endorsecontractTdAF.setCardKind(obj[2].toString());// 证件类型
                  }
                  if (obj[3] != null) {
                    endorsecontractTdAF.setCardNum(obj[3].toString());// 证件号码
                  }
                  if (obj[4] != null) {
                    endorsecontractTdAF.setSex(obj[4].toString());// 性别
                  }
                  if (obj[5] != null) {
                    endorsecontractTdAF.setBirthday(obj[5].toString());// 出生日期
                  }
                  if (obj[6] != null) {
                    endorsecontractTdAF.setSalary(obj[6].toString());// 月工资额
                  }
                  if (obj[7] != null) {
                    endorsecontractTdAF.setTel(obj[7].toString());// 固定电话
                    endorsecontractTdAF.setHomeTel(obj[7].toString());// 家庭电话
                  }
                  if (obj[8] != null) {
                    endorsecontractTdAF.setMobile(obj[8].toString());// 移动电话
                  }
                  if (obj[9] != null) {
                    String tempSt = obj[9].toString();
                    try {
                      endorsecontractTdAF.setEmpSt(BusiTools.getBusiValue(
                          Integer.parseInt(tempSt), BusiConst.OLDPAYMENTSTATE));
                    } catch (NumberFormatException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                    } catch (Exception e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                    }// 状态
                  }
                  BigDecimal tempOrgPay = null;
                  BigDecimal tempEmpPay = null;
                  BigDecimal prebalance = null;
                  BigDecimal curbalance = null;

                  if (obj[10] != null) {
                    tempOrgPay = new BigDecimal(obj[10].toString());// 单位缴额
                  }
                  if (obj[11] != null) {
                    tempEmpPay = new BigDecimal(obj[11].toString());// 职工缴额
                  }
                  if (tempOrgPay != null && tempEmpPay != null) {
                    endorsecontractTdAF.setMonthPay(tempOrgPay.add(tempEmpPay)
                        .toString());
                  } else if (tempOrgPay == null && tempEmpPay != null) {
                    endorsecontractTdAF.setMonthPay(tempEmpPay.toString());
                  } else if (tempOrgPay != null && tempEmpPay == null) {
                    endorsecontractTdAF.setMonthPay(tempOrgPay.toString());
                  }
                  if (obj[12] != null) {
                    prebalance = new BigDecimal(obj[12].toString());// 往年余额
                  }
                  if (obj[13] != null) {
                    curbalance = new BigDecimal(obj[13].toString());// 本年余额
                  }
                  if (prebalance != null && curbalance != null) {
                    endorsecontractTdAF.setBalance(prebalance.add(curbalance)
                        .toString());
                  } else if (prebalance == null && curbalance != null) {
                    endorsecontractTdAF.setBalance(curbalance.toString());
                  } else if (prebalance != null && curbalance == null) {
                    endorsecontractTdAF.setBalance(prebalance.toString());
                  }
                  if (obj[14] != null) {
                    endorsecontractTdAF.setOrgId(obj[14].toString());
                  }
                  if (obj[15] != null) {
                    endorsecontractTdAF.setOrgName(obj[15].toString());
                  }
                  if (obj[16] != null) {
                    endorsecontractTdAF.setOrgAddr(obj[16].toString());
                  }
                  if (obj[17] != null) {
                    endorsecontractTdAF.setOrgTel(obj[17].toString());
                  }
                  if (obj[18] != null) {
                    endorsecontractTdAF.setOrgMail(obj[18].toString());
                  }
                }
              }
              return endorsecontractTdAF;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return endorsecontractTdAF;
  }

  /**
   * 根据EMPID（AA002） 查询 （AA002）单位编号，EmpInfo（BA002）职工姓名，证件类型，证件号码，OrgInfo (BA001)
   * 单位名称
   * 
   * @param id
   * @return 张列
   */
  public SpecialapplyDTO queryEmpByEmpId_zl(final String id, final String orgId) {
    SpecialapplyDTO specialapplyDTO = null;
    try {
      specialapplyDTO = (SpecialapplyDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select a2.id,b2.name b_name,b2.card_kind,b2.card_num,a2.org_id,b1.name a_name from "
                  + " aa002 a2,ba002 b2,aa001 a1,ba001 b1 "
                  + " where a2.emp_info_id=b2.id and a2.org_id=a1.id and a1.orginfo_id=b1.id and a2.id=? and a2.org_id=? ";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, id);
              query.setParameter(1, orgId);

              Object[] obj = (Object[]) query.uniqueResult();
              SpecialapplyDTO specialapplyDTO = new SpecialapplyDTO();

              specialapplyDTO.setEmpId(obj[0].toString());
              specialapplyDTO.setBorrowerName(obj[1].toString());
              specialapplyDTO.setCardKind(obj[2].toString());
              specialapplyDTO.setCardNum(obj[3].toString());
              specialapplyDTO.setOrgId(obj[4].toString());
              specialapplyDTO.setOrgName(obj[5].toString());
              return specialapplyDTO;
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return specialapplyDTO;
  }

  /*
   * 职工弹出框查询
   */
  public List queryByCriterionsZL(final Serializable id, final String name,
      final String empid, final String oldId, final String orderBy,
      final String order, final int start, final int pageSize,
      final String[] status, final String cardnum, final String empname,
      final String sfzh, final String dwbh, final String dwmc) {

    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          List empList = new ArrayList();
          String hql = "select aa002.id empid,ba002.name empname,aa002.org_id,ba001.name orgname,aa002.emp_id_old,aa002.pay_status,ba002.CARD_NUM card_num "
              + " from AA002 aa002, BA002 ba002, BA001 ba001,AA001 aa001 ";
          Vector parameters = new Vector();
          String criterion = "";
          if (empid != null && !empid.equals("")) {
            criterion += " aa002.id=? and ";
            parameters.add(new Integer(empid));
          }
          if (name != null && !name.equals("")) {
            criterion += " ba002.name like ?  and  ";
            parameters.add("%" + name + "%");
          }
          if (oldId != null && !oldId.equals("")) {
            criterion += " aa002.emp_id_old=? and ";
            parameters.add(oldId);
          }
          if (sfzh != null && !sfzh.equals("")) {
            if (sfzh.length() == 15) {
              String sfzh_temp = CardMunChange.get18Id(sfzh);
              criterion += " (ba002.card_num like ? or ba002.card_num like ? ) and ";
              parameters.add("%" + sfzh + "%");
              parameters.add("%" + sfzh_temp + "%");
            } else if (sfzh.length() == 18) {
              String sfzh_temp = CardMunChange.get15Id(sfzh);
              criterion += " (ba002.card_num like ? or ba002.card_num like ? ) and ";
              parameters.add("%" + sfzh + "%");
              parameters.add("%" + sfzh_temp + "%");
            } else {
              criterion += " ba002.card_num like ? and ";
              parameters.add("%" + sfzh + "%");
            }
          }
          if (dwbh != null && !dwbh.equals("")) {
            criterion += " aa002.org_id like ? and ";
            parameters.add("%" + Integer.valueOf(dwbh) + "%");
          }
          if (dwmc != null && !dwmc.equals("")) {
            criterion += " ba001.name like ? and ";
            parameters.add("%" + dwmc + "%");
          }
          criterion = "where aa002.emp_info_id = ba002.id"
              + " and aa002.org_id = aa001.id  and aa001.orginfo_id=ba001.id and "
              + criterion;
          criterion = criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            Emp emp = new Emp();
            Org org = new Org();
            EmpInfo empInfo = new EmpInfo();
            OrgInfo orgInfo = new OrgInfo();
            emp.setEmpId(new Integer(obj[0].toString()));
            empInfo.setName(obj[1].toString());
            empInfo.setCardNum(obj[6].toString());
            org.setId(obj[2].toString());
            orgInfo.setName(obj[3].toString());
            if (obj[4] != null) {
              emp.setOldEmpID(obj[4].toString());
            } else {
              emp.setOldEmpID("");
            }
            try {
              emp.setPayStatusStr(BusiTools.getBusiValue(Integer
                  .parseInt(obj[5].toString()), BusiConst.OLDPAYMENTSTATE));
            } catch (NumberFormatException e) {
              e.printStackTrace();
            } catch (Exception e) {
              e.printStackTrace();
            }
            emp.setEmpInfo(empInfo);
            org.setOrgInfo(orgInfo);
            emp.setOrg(org);
            empList.add(emp);
          }
          return empList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 职工滩出框求和查询
   */
  public int queryCountByCriterionsZL(final Serializable id, final String name,
      final String empid, final String oldId, final String[] status,
      final String cardnum, final String empname, final String sfzh,
      final String dwbh, final String dwmc) {

    int count = 0;
    try {
      Integer countInteger = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(aa002.id) from AA002 aa002, BA002 ba002, BA001 ba001,AA001 aa001 ";
              Vector parameters = new Vector();
              String criterion = "";
              if (empid != null && !empid.equals("")) {
                criterion += " aa002.id=? and ";
                parameters.add(new Integer(empid));
              }
              if (name != null && !name.equals("")) {
                criterion += " ba002.name like ?  and  ";
                parameters.add("%" + name + "%");
              }
              if (oldId != null && !oldId.equals("")) {
                criterion += " aa002.emp_id_old=? and ";
                parameters.add(oldId);
              }
              if (sfzh != null && !sfzh.equals("")) {
                if (sfzh.length() == 15) {
                  String sfzh_temp = CardMunChange.get18Id(sfzh);
                  criterion += " (ba002.card_num like ? or ba002.card_num like ? )and ";
                  parameters.add("%" + sfzh + "%");
                  parameters.add("%" + sfzh_temp + "%");
                } else if (sfzh.length() == 18) {
                  String sfzh_temp = CardMunChange.get15Id(sfzh);
                  criterion += " (ba002.card_num like ? or ba002.card_num like ? )and ";
                  parameters.add("%" + sfzh + "%");
                  parameters.add("%" + sfzh_temp + "%");
                } else {
                  criterion += " ba002.card_num like ? and ";
                  parameters.add("%" + sfzh + "%");
                }
              }
              if (dwbh != null && !dwbh.equals("")) {
                criterion += " aa002.org_id like ? and ";
                parameters.add("%" + Integer.valueOf(dwbh) + "%");
              }
              if (dwmc != null && !dwmc.equals("")) {
                criterion += " ba001.name like ? and ";
                parameters.add("%" + dwmc + "%");
              }
              criterion = " where aa002.emp_info_id = ba002.id "
                  + " and aa002.org_id = aa001.id  and aa001.orginfo_id=ba001.id and "
                  + criterion;
              criterion = criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;

              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator it = query.list().iterator();
              Object obj = null;
              while (it.hasNext()) {
                obj = (Object) it.next();

              }
              if (obj != null) {
                return new Integer(obj.toString());
              } else {
                return new Integer(0);
              }

            }
          });
      count = countInteger.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * Description 查看职工明细账
   * 
   * @author wangy 2007-11-02
   * @param 根据条件查询列表
   * @param 由EmpAccountPopBS调用
   * @param borrowerName 职工姓名
   * @param cardNum 证件号码
   * @param empId 职工编号
   * @param orgId 单位编号
   * @param bizType 业务类型
   * @param settDate 发生时间
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @return List
   */
  public List queryEmpAccountListByCriterions(final String borrowerName,
      final String cardNum, final String empId, final String orgId,
      final String bizType, final String settDate, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct a101.doc_num as docnum,"
              + " a101.biz_type as biztype,a101.sett_date as settdate,"
              + " a102.debit as debit,a102.credit as credit,"
              + " a102.interest as interest,a101.id as id"
              + " from aa102 a102, aa101 a101, aa002 aa002, ba002 ba002"
              + " where a102.org_flow_id = a101.id"
              + " and a102.emp_id = aa002.id"
              + " and aa002.emp_info_id = ba002.id ";

          String criterion = "";
          Vector parameters = new Vector();

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += "  ba002.name = ? and ";
            parameters.add(borrowerName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " ba002.card_num = ? and ";
            parameters.add(cardNum);
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " a102.emp_id = ? and ";
            parameters.add(empId);
          }

          if (orgId != null && !"".equals(orgId)) {
            criterion += " a101.org_id = ? and ";
            parameters.add(orgId);
          }

          if (bizType != null && !"".equals(bizType)) {
            criterion += " a101.biz_type = ? and ";
            parameters.add(bizType);
          }

          if (settDate != null && !"".equals(settDate)) {
            criterion += " a101.sett_date = ? and ";
            parameters.add(settDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " a101.id ";

          String od = order;
          if (od == null)
            od = " DESC";

          hql = hql + criterion + " order by " + ob + " " + od;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();
          // 职工明细账列表
          List empAccountPopList = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            EmpAccountPopDTO empAccountPopDTO = new EmpAccountPopDTO();
            obj = (Object[]) iterate.next();
            // 凭证编号
            if (obj[0] != null && !obj[0].equals(""))
              empAccountPopDTO.setDocNum(obj[0].toString());
            // 业务类型
            String bizType = "";
            if (obj[1] != null && !obj[1].equals(""))
              bizType = obj[1].toString();
            // 发生时间
            if (obj[2] != null && !obj[2].equals(""))
              empAccountPopDTO.setSettDate(obj[2].toString());
            // 借方发生额
            if (obj[3] != null && !obj[3].equals(""))
              empAccountPopDTO.setDebit(new BigDecimal(obj[3].toString()));
            // 贷方发生额
            if (obj[4] != null && !obj[4].equals("")) {
              empAccountPopDTO.setCredit(new BigDecimal(obj[4].toString()));
            }
            // 利息
            if (obj[5] != null && !obj[5].equals("")) {
              empAccountPopDTO.setInterest(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null && !obj[6].equals("")) {
              empAccountPopDTO.setId(obj[6].toString());
            }
            // 枚举转换业务类型
            try {
              empAccountPopDTO.setBizType(BusiTools.getBusiValue_WL(bizType,
                  BusiConst.CLEARACCOUNTBUSINESSTYPE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            empAccountPopList.add(empAccountPopDTO);
          }
          return empAccountPopList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryEmpAccountListByCriterions_yg(final String bizType,
      final String settDateA, final String settDateB, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a101.doc_num as docnum, "
              + "a101.biz_type  as biztype, a101.sett_date as settdate, "
              + "sum(a102.debit) as debit, sum(a102.credit) as credit, "
              + "sum(a102.interest) as interest ,a101.id as id "
              + "from aa102 a102, aa101 a101 "
              + "where a102.org_flow_id = a101.id ";
          String criterion = "";
          Vector parameters = new Vector();

          if (bizType != null && !"".equals(bizType)) {
            criterion += "  a101.biz_type = ? and ";
            parameters.add(bizType);
          }
          if (settDateA != null && !"".equals(settDateA)) {
            criterion += "  a101.sett_date >= ? and ";
            parameters.add(settDateA);
          }
          if (settDateB != null && !"".equals(settDateB)) {
            criterion += "  a101.sett_date <= ? and ";
            parameters.add(settDateB);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion
              + "group by a101.id,a101.doc_num,a101.biz_type,a101.sett_date ";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();
          // 职工明细账列表
          List empAccountPopList = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            EmpAccountPopDTO empAccountPopDTO = new EmpAccountPopDTO();
            obj = (Object[]) iterate.next();
            // 凭证编号
            if (obj[0] != null && !obj[0].equals(""))
              empAccountPopDTO.setDocNum(obj[0].toString());
            // 业务类型
            String bizType = "";
            if (obj[1] != null && !obj[1].equals(""))
              bizType = obj[1].toString();
            // 发生时间
            if (obj[2] != null && !obj[2].equals(""))
              empAccountPopDTO.setSettDate(obj[2].toString());
            // 借方发生额
            if (obj[3] != null && !obj[3].equals(""))
              empAccountPopDTO.setDebit(new BigDecimal(obj[3].toString()));
            // 贷方发生额
            if (obj[4] != null && !obj[4].equals("")) {
              empAccountPopDTO.setCredit(new BigDecimal(obj[4].toString()));
            }
            // 利息
            if (obj[5] != null && !obj[5].equals("")) {
              empAccountPopDTO.setInterest(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null && !obj[6].equals("")) {
              empAccountPopDTO.setId(obj[6].toString());
            }
            // 枚举转换业务类型
            try {
              empAccountPopDTO.setBizType(BusiTools.getBusiValue_WL(bizType,
                  BusiConst.CLEARACCOUNTBUSINESSTYPE));
            } catch (Exception e) {
              e.printStackTrace();
            }
            empAccountPopList.add(empAccountPopDTO);
          }
          return empAccountPopList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * /** Description 查看职工明细账
   * 
   * @author wangy 2007-11-02
   * @param 根据条件查询全部列表（记录数(count)和合计）
   * @param 由EmpAccountPopBS调用
   * @param borrowerName 职工姓名
   * @param cardNum 证件号码
   * @param empId 职工编号
   * @param orgId 单位编号
   * @param bizType 业务类型
   * @param settDate 发生时间
   * @return List
   */
  public List queryEmpAccountCountAndSumMoneyByCriterion(
      final String borrowerName, final String cardNum, final String empId,
      final String orgId, final String bizType, final String settDate) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct a101.doc_num as docnum,"
              + " a101.biz_type as biztype,a101.sett_date as settdate,"
              + " a102.debit as debit,a102.credit as credit,"
              + " a102.interest as interest,a101.id as id"
              + " from aa102 a102, aa101 a101, aa002 aa002, ba002 ba002"
              + " where a102.org_flow_id = a101.id"
              + " and a102.emp_id = aa002.id"
              + " and aa002.emp_info_id = ba002.id ";

          String criterion = "";
          Vector parameters = new Vector();

          if (borrowerName != null && !"".equals(borrowerName)) {
            criterion += "  ba002.name = ? and ";
            parameters.add(borrowerName);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " ba002.card_num = ? and ";
            parameters.add(cardNum);
          }
          if (empId != null && !"".equals(empId)) {
            criterion += " a102.emp_id = ? and ";
            parameters.add(empId);
          }

          if (orgId != null && !"".equals(orgId)) {
            criterion += " a101.org_id = ? and ";
            parameters.add(orgId);
          }

          if (bizType != null && !"".equals(bizType)) {
            criterion += " a101.biz_type = ? and ";
            parameters.add(bizType);
          }

          if (settDate != null && !"".equals(settDate)) {
            criterion += " a101.sett_date = ? and ";
            parameters.add(settDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List queryList = query.list();
          return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryEmpAccountCountAndSumMoneyByCriterion_yg(
      final String bizType, final String settDateA, final String settDateB) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a101.doc_num as docnum, "
              + "a101.biz_type  as biztype, a101.sett_date as settdate, "
              + "sum(a102.debit) as debit, sum(a102.credit) as credit, "
              + "sum(a102.interest) as interest ,a101.id as id "
              + "from aa102 a102, aa101 a101 "
              + "where a102.org_flow_id = a101.id ";
          String criterion = "";
          Vector parameters = new Vector();

          if (bizType != null && !"".equals(bizType)) {
            criterion += "  a101.biz_type = ? and ";
            parameters.add(bizType);
          }
          if (settDateA != null && !"".equals(settDateA)) {
            criterion += "  a101.sett_date >= ? and ";
            parameters.add(settDateA);
          }
          if (settDateB != null && !"".equals(settDateB)) {
            criterion += "  a101.sett_date <= ? and ";
            parameters.add(settDateB);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion
              + "group by a101.id,a101.doc_num,a101.biz_type,a101.sett_date ";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List queryList = query.list();
          return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl 查询账户状态
   * 
   * @param empid
   * @param orgid
   * @return
   */
  public String findEmpInfoPayState(final String empid, final String orgid) {

    String state = "";
    state = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select t.pay_status from aa002 t where t.id=? and t.org_id=?";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, empid);
        query.setParameter(1, orgid);
        return query.uniqueResult().toString();
      }
    });

    return state;
  }

  /**
   * hanl 查询开户时间
   * 
   * @param empid
   * @param orgid
   * @return
   */
  public String findEmpInfoOpenDate(final String empname, final String cardnum) {
    String state = "";
    state = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select t.opendate from ba002 t where t.name=? and t.card_num=?";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, empname);
        query.setParameter(1, cardnum);
        return query.uniqueResult();
      }
    });

    return state;
  }

  public String findEmpInfoOpenDate_ws(final String empId, final String orgId) {

    String state = "";
    state = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select ba002.opendate from aa002,ba002 where aa002.emp_info_id=ba002.id and aa002.org_id=? and aa002.id=? ";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, orgId);
        query.setParameter(1, empId);
        return query.uniqueResult();
      }
    });

    return state;
  }

  /**
   * hanl 查询开户时间
   * 
   * @param empid
   * @param orgid
   * @return
   */
  public String findEmpInfoOpenDate_wsh(final String empname,
      final String cardnum) {

    String state = "";
    state = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select a.org_pay_month from aa002 a where a.id=? and a.org_id=? ";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, empname);
        query.setParameter(1, cardnum);
        return query.uniqueResult();
      }
    });

    return state;
  }

  /**
   * hanl 查询开户时间
   * 
   * @param empid
   * @param orgid
   * @return
   */
  public String findEmpInfoOpenDate_wsh_emp(final String empname,
      final String cardnum) {

    String state = "";
    state = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select a.emp_pay_month from aa002 a where a.id=? and a.org_id=? ";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, empname);
        query.setParameter(1, cardnum);
        return query.uniqueResult();
      }
    });

    return state;
  }

  /**
   * 查询职工导出列表
   * 
   * @param orgId
   * @return
   */
  public List queryEmpAgentExpList(final String orgId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a002.id, b002.name, b002.card_num, a002.emp_agent_num,b002.sex,b002.birthday,b002.card_kind,b002.tel,b002.mobile_tle,b002.month_income,b002.department "
              + "from aa002 a002, ba002 b002 "
              + "where a002.emp_info_id = b002.id and a002.org_id = ? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, orgId);
          Iterator it = query.list().iterator();
          List temp_List = new ArrayList();
          Object[] obj = null;
          EmpAgentExportDTO empAgentExportDTO = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            empAgentExportDTO = new EmpAgentExportDTO();
            if (obj[0] != null) {
              empAgentExportDTO.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              empAgentExportDTO.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              empAgentExportDTO.setCardNum(obj[2].toString());
            }
            if (obj[3] != null) {
              empAgentExportDTO.setEmpAgentNum(obj[3].toString());
            }
            if (obj[4] != null) {
              empAgentExportDTO.setSex(obj[4].toString());
            }
            if (obj[5] != null) {
              empAgentExportDTO.setBirthday(obj[5].toString());
            }
            if (obj[6] != null) {
              empAgentExportDTO.setCardKind(obj[6].toString());
            }
            if (obj[7] != null) {
              empAgentExportDTO.setTel(obj[7].toString());
            }
            if (obj[8] != null) {
              empAgentExportDTO.setMobileTle(obj[8].toString());
            }
            if (obj[9] != null) {
              empAgentExportDTO.setMonthIncome(obj[9].toString());
            }
            if (obj[10] != null) {
              empAgentExportDTO.setDepartment(obj[10].toString());
            }
            temp_List.add(empAgentExportDTO);
          }
          return temp_List;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryEmpAgentExpList_wuht(final String orgId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a002.id, b002.name, b002.card_num, a002.emp_agent_num,b002.sex,b002.birthday,b002.card_kind,b002.tel,b002.mobile_tle,b002.month_income,b002.department "
              + "from aa002 a002, ba002 b002 "
              + "where a002.emp_info_id = b002.id and b002.card_num not in"
              + " (select pl110.card_num  from pl110 pl110, pl111 pl111  "
              + " where pl110.contract_id = pl111.contract_id and pl111.contract_st='11' and pl110.card_num=b002.card_num) and  a002.org_id = ? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, orgId);
          Iterator it = query.list().iterator();
          List temp_List = new ArrayList();
          Object[] obj = null;
          EmpAgentExportDTO empAgentExportDTO = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            empAgentExportDTO = new EmpAgentExportDTO();
            if (obj[0] != null) {
              empAgentExportDTO.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              empAgentExportDTO.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              empAgentExportDTO.setCardNum(obj[2].toString());
            }
            if (obj[3] != null) {
              empAgentExportDTO.setEmpAgentNum(obj[3].toString());
            }
            if (obj[4] != null) {
              empAgentExportDTO.setSex(obj[4].toString());
            }
            if (obj[5] != null) {
              empAgentExportDTO.setBirthday(obj[5].toString());
            }
            if (obj[6] != null) {
              empAgentExportDTO.setCardKind(obj[6].toString());
            }
            if (obj[7] != null) {
              empAgentExportDTO.setTel(obj[7].toString());
            }
            if (obj[8] != null) {
              empAgentExportDTO.setMobileTle(obj[8].toString());
            }
            if (obj[9] != null) {
              empAgentExportDTO.setMonthIncome(obj[9].toString());
            }
            if (obj[10] != null) {
              empAgentExportDTO.setDepartment(obj[10].toString());
            }
            temp_List.add(empAgentExportDTO);
          }
          return temp_List;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 修改职工代扣号的方法
   * 
   * @param orgId
   * @param orgAgentNum
   */
  public void updateEmpAgentNum(final String orgId, final String empId,
      final String empAgentNum, final String empName, final String cardNum,
      final String sex, final String birthday, final String cardKind,
      final String tel, final String mobileTle, final String monthIncome,
      final String department) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update Emp emp set emp.empAgentNum=?"
              + "where emp.org.id=? and emp.empId=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, empAgentNum);
          query.setParameter(1, new Integer(orgId));
          query.setParameter(2, new Integer(empId));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 判断职工代扣好是否存在
   * 
   * @param orgId
   * @param empId
   * @param empAgentNum
   * @return
   */
  public int queryEmpAgentNum(final String orgId, final String empId,
      final String empAgentNum) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a002.pk_id from aa002 a002 where a002.org_id=? and (a002.id>? or a002.id<?) and a002.emp_agent_num=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(orgId));
          query.setParameter(1, new Integer(empId));
          query.setParameter(2, new Integer(empId));
          query.setParameter(3, empAgentNum);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  /**
   * 在添加职工时判断代扣号是否存在
   * 
   * @param orgId
   * @param empId
   * @param empAgentNum
   * @return
   */
  public int queryEmpAgentNumBySave(final String orgId, final String empAgentNum) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a002.pk_id from aa002 a002 where a002.org_id=? and a002.emp_agent_num=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(orgId));
          query.setParameter(1, empAgentNum);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  /**
   * 判断在导入时该单位是否存在这个职工
   * 
   * @param empId
   * @param orgId
   * @return
   * @author 付云峰
   */
  public int queryEmpbyEmpId_FYF(final String empId, final String orgId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a002.pk_id from aa002 a002 where a002.org_id=? and a002.id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(orgId));
          query.setParameter(1, new Integer(empId));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  /**
   * 查询姓名不同但是身份证号相同的职工信息
   * 
   * @return
   * @author 付云峰
   */
  public List queryEmpInfoByCardNum_FYF(final String empName,
      final String cardNum) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select b.name,a.id" + " from ba002 b, aa002 a"
              + " where a.emp_info_id = b.id" + " and b.card_kind = '0'"
              + " and (b.name <> ?)"
              + " and (b.card_num = ? or b.card_num = ?)";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, empName);
          query.setParameter(1, cardNum);
          String cardNum_temp = "";
          if (cardNum.length() == 18) {
            cardNum_temp = CardMunChange.get15Id(cardNum);
          } else if (cardNum.length() == 15) {
            cardNum_temp = CardMunChange.get18Id(cardNum);
          }
          query.setParameter(2, cardNum_temp);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 修改职工信息时判断身份证的方法(与正常判断要多两个条件)
   * 
   * @param empName
   * @param cardNum
   * @param empId
   * @return
   * @author 付云峰
   */
  public List queryUpdateCardNum_FYF(final String empName,
      final String cardNum, final String empId, final String orgId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select b.name,a.id" + " from ba002 b, aa002 a"
              + " where a.emp_info_id = b.id" + " and b.card_kind = '0'"
              + " and (b.name > ? or b.name< ?)"
              + " and (b.card_num = ? or b.card_num = ?)"
              + " and (a.id>? or a.id<?)" + " and a.org_id = ?";
          Query query = session.createSQLQuery(sql);
          String cardNum_temp = "";
          if (cardNum.length() == 18) {
            cardNum_temp = CardMunChange.get15Id(cardNum);
          } else if (cardNum.length() == 15) {
            cardNum_temp = CardMunChange.get18Id(cardNum);
          }
          query.setParameter(0, empName);
          query.setParameter(1, empName);
          query.setParameter(2, cardNum);
          query.setParameter(3, cardNum_temp);
          query.setParameter(4, empId);
          query.setParameter(5, empId);
          query.setParameter(6, orgId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 修改职工信息时判断身份证的方法(使用AA002主键判断)
   * 
   * @param empName
   * @param cardNum
   * @param empId
   * @return
   * @author 付云峰
   */
  public List queryUpdateCardNumById_FYF(final String empName,
      final String cardNum, final String empId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select b.name,a.id" + " from ba002 b, aa002 a"
              + " where a.emp_info_id = b.id" + " and b.card_kind = '0'"
              + " and (b.name > ? or b.name< ?)"
              + " and (b.card_num = ? or b.standby_card_num = ?)"
              + " and (a.pk_id>? or a.pk_id<?)";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, empName);
          query.setParameter(1, empName);
          query.setParameter(2, cardNum);
          query.setParameter(3, cardNum);
          query.setParameter(4, empId);
          query.setParameter(5, empId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 人员变更时关于职工的提示查询
   * 
   * @param empName
   * @param cardNum
   * @return 付云峰
   */
  public List queryEmpInfoByChgperson_FYF(final String orgId,
      final String empName, final String cardNum) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List resultList = new ArrayList();
          // 查询BA002
          String sql = "select b.name,a.id" + " from ba002 b, aa002 a"
              + " where a.emp_info_id = b.id"
              // + " and a.org_id = ?"
              + " and b.card_kind = '0'" + " and (b.name <> ?)"
              + " and (b.card_num = ? or b.card_num = ?)";
          Query query = session.createSQLQuery(sql);
          // query.setParameter(0, orgId);
          query.setParameter(0, empName);
          query.setParameter(1, cardNum.length() == 15 ? CardMunChange
              .get18Id(cardNum) : cardNum);
          query.setParameter(2, cardNum.length() == 18 ? CardMunChange
              .get15Id(cardNum) : cardNum);

          List list = query.list();
          for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            resultList.add(obj);
          }
          // 查询AA205
          String sql1 = "select a205.name,a205.emp_id"
              + " from AA205 a205,AA204 a204"
              + " where a204.id = a205.chg_head_id"
              // + " and a204.org_id = ? "
              + " and a204.chg_status = 1" + " and a205.card_kind = '0' "
              + " and (a205.name <> ?) "
              + " and (a205.card_num = ? or a205.card_num = ?)";
          Query query1 = session.createSQLQuery(sql1);
          // query1.setParameter(0, orgId);
          query1.setParameter(0, empName);
          query1.setParameter(1, cardNum.length() == 15 ? CardMunChange
              .get18Id(cardNum) : cardNum);
          query1.setParameter(2, cardNum.length() == 18 ? CardMunChange
              .get15Id(cardNum) : cardNum);

          List list1 = query1.list();
          for (int i = 0; i < list1.size(); i++) {
            Object obj = list1.get(i);
            resultList.add(obj);
          }
          return resultList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 转入时关于职工的提示查询
   * 
   * @param empName
   * @param cardNum
   * @return 付云峰
   */
  public List queryEmpInfoByTranin_FYF(final String empName,
      final String cardNum) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List resultList = new ArrayList();
          // 查询BA002
          String sql = "select b.name,a.id" + " from ba002 b, aa002 a"
              + " where a.emp_info_id = b.id" + " and b.card_kind = '0'"
              + " and (b.name > ? or b.name< ?)"
              + " and (b.card_num=? or b.standby_card_num=?)";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, empName);
          query.setParameter(1, empName);
          query.setParameter(2, cardNum);
          query.setParameter(3, cardNum);
          List list = query.list();
          for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            resultList.add(obj);
          }
          // 查询AA312
          String sql1 = "select a312.name,a312.emp_id "
              + "from aa312 a312 where a312.card_kind='0' "
              + "and (a312.name>? or a312.name<?) "
              + "and (a312.card_num=? or a312.standby_card_num=?)";
          Query query1 = session.createSQLQuery(sql1);
          query1.setParameter(0, empName);
          query1.setParameter(1, empName);
          query1.setParameter(2, cardNum);
          query1.setParameter(3, cardNum);
          List list1 = query1.list();
          for (int i = 0; i < list1.size(); i++) {
            Object obj = list1.get(i);
            resultList.add(obj);
          }
          return resultList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 转入修改时关于职工的提示查询
   * 
   * @param empName
   * @param cardNum
   * @return 付云峰
   */
  public List queryEmpUpdateByTranin_FYF(final String empName,
      final String cardNum, final String empId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List resultList = new ArrayList();
          // 查询BA002
          String sql = "select b.name,a.id" + " from ba002 b, aa002 a"
              + " where a.emp_info_id = b.id" + " and b.card_kind = '0'"
              + " and (b.name > ? or b.name< ?)"
              + " and (b.card_num=? or b.standby_card_num=?)";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, empName);
          query.setParameter(1, empName);
          query.setParameter(2, cardNum);
          query.setParameter(3, cardNum);
          List list = query.list();
          for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            resultList.add(obj);
          }
          // 查询AA312
          String sql1 = "select a312.name,a312.emp_id "
              + "from aa312 a312 where a312.card_kind='0' "
              + "and (a312.name>? or a312.name<?) "
              + "and (a312.card_num=? or a312.standby_card_num=?)"
              + "and (a312.emp_id > ? or a312.emp_id < ?)";
          Query query1 = session.createSQLQuery(sql1);
          query1.setParameter(0, empName);
          query1.setParameter(1, empName);
          query1.setParameter(2, cardNum);
          query1.setParameter(3, cardNum);
          query1.setParameter(4, empId);
          query1.setParameter(5, empId);
          List list1 = query1.list();
          for (int i = 0; i < list1.size(); i++) {
            Object obj = list1.get(i);
            resultList.add(obj);
          }
          return resultList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 返回往年余额 张列
   * 
   * @param empid
   * @param orgid
   * @return
   */
  public String findChgpersonPerBalance(final String empid, final String orgid) {

    String state = "";
    state = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select sum(a002.pre_balance) from aa002 a002 "
            + " where a002.id=? and a002.org_id=? ";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, empid);
        query.setParameter(1, orgid);
        return query.uniqueResult().toString();
      }
    });

    return state;
  }

  /**
   * 返回本年余额 张列
   * 
   * @param empid
   * @param orgid
   * @return
   */
  public String findChgpersonCurBalance(final String empid, final String orgid) {

    String state = "";
    state = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select sum(a002.cur_balance) from aa002 a002 "
            + " where a002.id=? and a002.org_id=? ";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, empid);
        query.setParameter(1, orgid);
        return query.uniqueResult().toString();
      }
    });

    return state;
  }

  /**
   * 人员当前的状态 张列
   * 
   * @param empid
   * @param orgid
   * @return
   */
  public String findChgpersonStatus(final String empid, final String orgid) {

    String state = "";
    state = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select a002.pay_status from aa002 a002 "
            + " where a002.id=? and a002.org_id=? ";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, empid);
        query.setParameter(1, orgid);
        return query.uniqueResult().toString();
      }
    });

    return state;
  }

  /*
   * 职工弹出框查询
   */
  public List queryByCriterions(final Serializable id, final String name,
      final String empid, final String oldId, final String orderBy,
      final String order, final int start, final int pageSize,
      final String[] status, final String cardnum, final String empname) {

    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select emp from  Emp emp, Org org ";
          Vector parameters = new Vector();
          String criterion = "";
          if (id != null && !id.equals("")) {
            criterion += "org.id=emp.org.id and org.id=? and  ";
            parameters.add(new Integer(id.toString()));
          }
          if (empid != null && !empid.equals("")) {
            criterion += "emp.empId=? and ";
            parameters.add(new Integer(empid));
          }
          if (oldId != null && !oldId.equals("")) {
            criterion += " to_char(emp.oldEmpID) like ? escape '/'  and ";
            parameters.add("%" + oldId + "%");
          }
          if (name != null && !name.equals("")) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add("%" + name + "%");
          }
          if (empname != null && !empname.equals("")) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add(empname);
          }
          if (cardnum != null && !cardnum.equals("")) {
            // criterion += "emp.empInfo.cardNum like ? escape '/' and ";
            // parameters.add("%" + cardnum + "%");
            criterion += "( emp.empInfo.cardNum = ? or emp.empInfo.standbyCardNum = ? ) and ";
            parameters.add(cardnum);
            parameters.add(cardnum);
          }
          if (status != null && status.length > 0) {
            criterion += "( ";
            for (int i = 0; i < status.length; i++) {
              criterion += "emp.payStatus = ? or ";
              parameters.add(new BigDecimal(status[i]));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          List empList = query.list();
          session.clear();
          return empList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 职工滩出框求和查询
   */
  public int queryCountByCriterions(final Serializable id, final String name,
      final String empid, final String oldId, final String[] status,
      final String cardnum, final String empname) {

    int count = 0;
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  emp from  Emp emp, Org org ";
          Vector parameters = new Vector();
          String criterion = "";
          if (id != null && !id.equals("")) {
            criterion += "org.id=emp.org.id and org.id=? and  ";
            parameters.add(new Integer(id.toString()));

          }
          if (empid != null && !empid.equals("")) {
            criterion += "emp.empId=? and ";
            parameters.add(new Integer(empid));
          }

          if (oldId != null && !oldId.equals("")) {
            criterion += " to_char(emp.oldEmpID) like ? escape '/'  and ";
            parameters.add("%" + oldId + "%");
          }
          if (name != null && !name.equals("")) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add("%" + name + "%");
          }
          if (empname != null && !empname.equals("")) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add(empname);
          }
          if (cardnum != null && !cardnum.equals("")) {
            // criterion += "emp.empInfo.cardNum like ? escape '/' and ";
            // parameters.add("%" + cardnum + "%");
            criterion += "( emp.empInfo.cardNum = ? or emp.empInfo.standbyCardNum = ? ) and ";
            parameters.add(cardnum);
            parameters.add(cardnum);
          }
          if (status != null && status.length > 0) {
            criterion += "( ";
            for (int i = 0; i < status.length; i++) {
              criterion += "emp.payStatus = ? or ";
              parameters.add(new BigDecimal(status[i]));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          session.clear();

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List l = query.list();
          System.out.println(l.size());
          return query.list();
        }
      });
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * hanl 20080131 判断添加的辅助借款人在职工表是否存在
   * 
   * @return 如果存在返回true，否则返回false。
   */
  public boolean getEmpidCount_AssistantBorrower(final Integer empid) {
    Integer empCount = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(*) from Emp emp ";
            Vector parameters = new Vector();
            String criterion = "";
            if (empid != null) {
              criterion = " where emp.empId = ?";
              parameters.add(new Integer(empid.toString()));
            }
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));
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
   * 查询银行接口导出内容的方法
   * 
   * @param openDate
   * @return
   * @author 付云峰
   */
  public List queryBankInterfaceInfo(final String openDate) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List resultList = new ArrayList();
          String happenLocus = "";
          String organization = "";
          // 得到发生地点与数据发生机构
          String sql0 = "select a413.num,a413.value from aa413 a413 where a413.num in ('5','6')";
          Query query0 = session.createSQLQuery(sql0);
          for (int i = 0; i < query0.list().size(); i++) {
            Object[] obj = (Object[]) query0.list().get(i);
            if (obj[0].toString().equals("5")) {
              happenLocus = obj[1].toString();
            } else if (obj[0].toString().equals("6")) {
              organization = obj[1].toString();
            }
          }
          // 查询BA002
          String sql = "select b002.card_num," + "a002.org_id," + "a002.id,"
              + "b002.name," + "b002.opendate," + "a002.org_pay_month,"
              + "a002.emp_pay_month," + "a002.pay_status,"
              + "(a002.emp_pay + a002.org_pay)"
              + " from ba002 b002, aa002 a002"
              + " where a002.emp_info_id = b002.id" + " and b002.card_kind = 0"
              + " and b002.opendate<='" + openDate + "31'";
          Query query = session.createSQLQuery(sql);
          Iterator it = query.list().iterator();
          BankInterfaceInfoDTO bankInterfaceInfoDTO = null;
          Object[] obj = null;
          while (it.hasNext()) {
            bankInterfaceInfoDTO = new BankInterfaceInfoDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              bankInterfaceInfoDTO.setIdentifyCode(obj[0].toString());
            }
            if (obj[1] != null && obj[2] != null) {
              bankInterfaceInfoDTO.setAccounts(obj[1].toString()
                  + obj[2].toString());
            }
            if (obj[3] != null) {
              bankInterfaceInfoDTO.setEmpName(obj[3].toString());
            }
            if (obj[0] != null) {
              bankInterfaceInfoDTO.setCardNum(obj[0].toString());
            }
            if (obj[4] != null) {
              bankInterfaceInfoDTO.setOpenDate(obj[4].toString());
            }
            bankInterfaceInfoDTO.setHappenLocus(happenLocus);
            bankInterfaceInfoDTO.setOrganization(organization);
            // 得到单位名称
            String sql1 = "select b.name from aa001 a, ba001 b where a.orginfo_id = b.id and a.id = ?";
            Query query1 = session.createSQLQuery(sql1);
            query1.setParameter(0, obj[1].toString());
            // query1.setParameter(1, obj[2].toString());
            Object obj1 = query1.uniqueResult();
            bankInterfaceInfoDTO.setOrgName(obj1.toString());
            // 查询该单位在AA301中，最小的id，以及缴存类型
            String sql2 = " select min(a301.id),min(a301.pay_type) from aa303 a303,aa301 a301,aa302 a302 where a301.id=a302.pay_head_id and a303.month_pay_head_id=a302.id and a301.org_id = ? and a303.emp_id=? and a302.pay_month<=? and a301.pay_status=5";
            Query query2 = session.createSQLQuery(sql2);
            query2.setParameter(0, obj[1].toString());
            query2.setParameter(1, obj[2].toString());
            query2.setParameter(2, openDate);
            Object[] obj2 = (Object[]) query2.uniqueResult();
            // 初缴年月
            if (obj2[0] != null) {
              // 判断是否为补缴
              if (!obj2.toString().equals("C")) {
                String sql3 = " select min(a302.pay_month)"
                    + " from aa302 a302, aa303 a303"
                    + " where a302.id = a303.month_pay_head_id"
                    + " and a302.pay_head_id = ?" + " and a303.emp_id = ?"
                    + " and a302.pay_month<= ?";
                Query query3 = session.createSQLQuery(sql3);
                query3.setParameter(0, obj2[0].toString());
                query3.setParameter(1, obj[2].toString());
                query3.setParameter(2, openDate);
                Object obj3 = query3.uniqueResult();
                bankInterfaceInfoDTO.setPayMonth(obj3.toString());
              } else {
                String sql3 = " select min(a304.add_monht) from aa304 a304,aa301 a301 where a301.id=a304.pay_head_id and a301.id=? and a304.emp_id=? and a304.add_monht<=?";
                Query query3 = session.createSQLQuery(sql3);
                query3.setParameter(0, obj2[0].toString());
                query3.setParameter(1, obj[2].toString());
                query3.setParameter(2, openDate);
                Object obj3 = query3.uniqueResult();
                bankInterfaceInfoDTO.setPayMonth(obj3.toString());
              }
            } else {
              bankInterfaceInfoDTO.setPayMonth("000000");
            }
            // 最后一次缴交日期
            String sql4 = " select max(a301.sett_date) from aa303 a303,aa301 a301,aa302 a302 where a301.id=a302.pay_head_id and a303.month_pay_head_id=a302.id and a301.org_id = ? and a303.emp_id=? and a302.pay_month<=? and a301.pay_status=5";
            Query query4 = session.createSQLQuery(sql4);
            query4.setInteger(0, Integer.parseInt(obj[1].toString()));
            query4.setInteger(1, Integer.parseInt(obj[2].toString()));
            query4.setParameter(2, openDate);
            Object obj4 = (Object) query4.uniqueResult();
            if (obj4 != null) {
              bankInterfaceInfoDTO.setLastPayMonth(obj4.toString());
            } else {
              bankInterfaceInfoDTO.setLastPayMonth("00000000");
            }
            // 缴至年月
            // if(obj[5]!=null&&obj[6]!=null){
            // String org_pay_month = obj[5].toString();
            // String emp_pay_month = obj[6].toString();
            // // 判断缴至年月大小
            // DateFormat df = new SimpleDateFormat("yyyyMM");
            // try {
            // Date date1 = df.parse(org_pay_month);
            // Date date2 = df.parse(emp_pay_month);
            // if (date1.getTime()>date2.getTime()) {
            // bankInterfaceInfoDTO.setPayOverMonth(org_pay_month);
            // }else{
            // bankInterfaceInfoDTO.setPayOverMonth(emp_pay_month);
            // }
            // } catch (ParseException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            // }
            String sql5 = " select max(a302.pay_month)"
                + " from aa303 a303, aa302 a302, aa301 a301"
                + " where a301.id = a302.pay_head_id"
                + " and a303.month_pay_head_id=a302.id"
                + " and a301.pay_status = 5" + " and a301.org_id=?"
                + " and a303.emp_id = ?"
                + " and substr(a301.sett_date, 0, 6)<= ?";
            Query query5 = session.createSQLQuery(sql5);
            query5.setInteger(0, Integer.parseInt(obj[1].toString()));
            query5.setInteger(1, Integer.parseInt(obj[2].toString()));
            query5.setParameter(2, openDate);
            Object obj5 = (Object) query5.uniqueResult();

            String sql6 = " select max(a304.add_monht)"
                + " from aa304 a304, aa301 a301"
                + " where a301.id = a304.pay_head_id"
                + " and a301.pay_status = 5" + " and a301.org_id=?"
                + " and a304.emp_id = ?"
                + " and substr(a301.sett_date, 0, 6)<= ?";
            Query query6 = session.createSQLQuery(sql6);
            query6.setInteger(0, Integer.parseInt(obj[1].toString()));
            query6.setInteger(1, Integer.parseInt(obj[2].toString()));
            query6.setParameter(2, openDate);
            Object obj6 = (Object) query6.uniqueResult();
            if (obj5 != null && obj6 != null) {
              try {
                DateFormat df = new SimpleDateFormat("yyyyMM");
                Date date1 = df.parse(obj5.toString());
                Date date2 = df.parse(obj6.toString());
                if (date1.getTime() > date2.getTime()) {
                  bankInterfaceInfoDTO.setPayOverMonth(obj5.toString());
                } else {
                  bankInterfaceInfoDTO.setPayOverMonth(obj6.toString());
                }
              } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            } else {
              bankInterfaceInfoDTO.setPayMonth("000000");
            }
            bankInterfaceInfoDTO.setOrgName(obj1.toString());
            bankInterfaceInfoDTO.setCharacter("900");
            if (obj[7] != null) {
              if (obj[7].toString().equals("1")
                  || obj[7].toString().equals("3")
                  || obj[7].toString().equals("4")) {
                bankInterfaceInfoDTO.setPayStatus("缴交");
              } else if (obj[7].toString().equals("2")) {
                try {
                  bankInterfaceInfoDTO.setPayStatus(BusiTools.getBusiValue(
                      Integer.parseInt(obj[7].toString()),
                      BusiConst.OLDPAYMENTSTATE));
                } catch (NumberFormatException e) {
                  e.printStackTrace();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              } else {
                bankInterfaceInfoDTO.setPayStatus("销户");
              }
            }
            if (obj[8] != null) {
              bankInterfaceInfoDTO.setPay(obj[8].toString());
            }
            resultList.add(bankInterfaceInfoDTO);
          }
          return resultList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 单位版职工开户职工编号修改的时候使用 empId ,orgId,oldEmpId
   */
  public void changeEmpId_sy(String empId, String orgId, String oldEmpId)
      throws BusinessException, HibernateException, SQLException {
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
   * 缴额调整批量导出
   * 
   * @param orgId
   * @param orgName
   * @param chgMonth
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public List queryChgpayEmpOrgWuhts(final String orgId, final String orgName,
      final String chgMonth, final String[] orderBy, final String order,
      final int start, final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List empList = new ArrayList();
        String hql = "select emp.empId ,emp.empInfo.name ,emp.empInfo.cardNum ,emp.orgPay,emp.empPay ,emp.salaryBase from Emp emp ";
        String criterion = "";
        Vector parameters = new Vector();
        if (orgId != null) {
          criterion += "emp.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

        if (criterion.length() != 0)
          criterion = "where  emp.payStatus <>'5' and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = "";
        if (orderBy != null && orderBy.length > 0) {
          for (int i = 0; i < orderBy.length; i++) {
            if (orderBy[i] == "emp.empInfo.name") {
              ob += "nlssort( " + orderBy[i].toString()
                  + " ,'NLS_SORT=SCHINESE_PINYIN_M'),";
            } else {
              ob += " " + orderBy[i].toString() + " , ";
            }
          }
          ob = ob.substring(0, ob.lastIndexOf(","));
          ob += " ";
        }

        String od = order;
        if (od == null)
          od = "ASC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        Iterator iterator = query.list().iterator();
        ChgpayInfoDTO dto = null;
        Object[] obj = null;

        while (iterator.hasNext()) {
          obj = (Object[]) iterator.next();
          dto = new ChgpayInfoDTO();

          dto.setOrgId(orgId);
          dto.setOrgName(orgName);
          dto.setChgMonth(chgMonth);

          if (obj[0] != null) {
            dto.setEmpId(obj[0].toString());

          }
          if (obj[1] != null) {
            dto.setEmpName(obj[1].toString());
          }
          if (obj[2] != null) {
            dto.setCardNum(obj[2].toString());
          }
          if (obj[3] != null) {
            dto.setOldOrgPay(obj[3].toString());
          }
          if (obj[4] != null) {
            dto.setOldEmpPay(obj[4].toString());
          }
          if (obj[5] != null) {
            dto.setSalaryBase(obj[5].toString());
          }

          dto.setOrgPay(null);
          dto.setEmpPay(null);

          empList.add(dto);

        }

        return empList;

      }
    });

    return list;
  }

  /**
   * 工资基数调整导出
   * 
   * @param orgId
   * @param orgName
   * @param chgMonth
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public List queryChgslarybaseEmpOrgWuhts(final String orgId,
      final String orgName, final String chgMonth, final String[] orderBy,
      final String order, final int start, final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List empList = new ArrayList();
        String hql = "select emp.empId ,emp.empInfo.name ,emp.empInfo.cardNum ,emp.salaryBase  from Emp emp ";
        String criterion = "";
        Vector parameters = new Vector();
        if (orgId != null) {
          criterion += "emp.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

        if (criterion.length() != 0)
          criterion = "where  emp.payStatus<>'5' and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = "";
        if (orderBy != null && orderBy.length > 0) {
          for (int i = 0; i < orderBy.length; i++) {
            if (orderBy[i] == "emp.empInfo.name") {
              ob += "nlssort( " + orderBy[i].toString()
                  + " ,'NLS_SORT=SCHINESE_PINYIN_M'),";
            } else {
              ob += " " + orderBy[i].toString() + " , ";
            }
          }
          ob = ob.substring(0, ob.lastIndexOf(","));
          ob += " ";
        }
        String od = order;
        if (od == null)
          od = "ASC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        Iterator iterator = query.list().iterator();
        ChgslarybaseInfoDTO dto = null;
        Object[] obj = null;

        while (iterator.hasNext()) {
          obj = (Object[]) iterator.next();
          dto = new ChgslarybaseInfoDTO();

          dto.setOrgId(orgId);
          dto.setOrgName(orgName);
          dto.setChgMonth(chgMonth);

          if (obj[0] != null) {
            dto.setEmpId(obj[0].toString());

          }
          if (obj[1] != null) {
            dto.setEmpName(obj[1].toString());
          }
          if (obj[2] != null) {
            dto.setCardNum(obj[2].toString());
          }
          if (obj[3] != null) {
            dto.setOldSalaryBase(obj[3].toString());
          }

          dto.setSalaryBase(null);

          empList.add(dto);

        }

        return empList;

      }
    });

    return list;
  }

  /*
   * 人员变更导出
   */
  public List queryByCriterionsLP(final String empid, final String orgid,
      final String[] orderBy, final String order) {
    List emplist = null;
    emplist = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {

          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {

            String hql = "select emp from Emp emp  ";

            Vector parameters = new Vector();
            String criterion = "";
            if (empid != null) {
              criterion += "emp.empId = ? and ";
              parameters.add(new Integer(empid));
            }
            if (orgid != null) {
              criterion += "emp.org.id = ? and ";
              parameters.add(new Integer(orgid));
            }
            if (criterion.length() != 0)
              criterion = "where  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));

            String ob = "";
            if (orderBy != null && orderBy.length > 0) {
              for (int i = 0; i < orderBy.length; i++) {
                if (orderBy[i] == "emp.empInfo.name") {
                  ob += "nlssort( " + orderBy[i].toString()
                      + " ,'NLS_SORT=SCHINESE_PINYIN_M'),";
                } else {
                  ob += " " + orderBy[i].toString() + " , ";
                }
              }
              ob = ob.substring(0, ob.lastIndexOf(","));
              ob += " ";
            }
            String od = order;
            if (od == null)
              od = "ASC";
            hql = hql + criterion + "order by " + ob + " " + od;
            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }
            return query0.list();
          }
        });
    return emplist;
  }

  /*
   * 根据单位ID查职工信息 李鹏
   */
  public List queryEmpByOrgIdLP(final String orgId, final String[] orderBy) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from Emp emp "
            + "where emp.org.id = ? and emp.payStatus in (1,2) ";

        String ob = "";
        if (orderBy != null && orderBy.length > 0) {
          for (int i = 0; i < orderBy.length; i++) {
            if (orderBy[i] == "emp.empInfo.name") {
              ob += "nlssort( " + orderBy[i].toString()
                  + " ,'NLS_SORT=SCHINESE_PINYIN_M'),";
            } else {
              ob += " " + orderBy[i].toString() + " , ";
            }
          }
          ob = ob.substring(0, ob.lastIndexOf(","));
          ob += " ";
        }

        hql = hql + "order by " + ob + " ASC";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(orgId));
        return query.list();
      }
    });
    return list;
  }

  // 李鹏个人补缴批量导出使用
  public List queryByEmpIdLP(final Integer orgId, final String[] orderBy,
      final String order) {

    Validate.notNull(orgId);
    List list = new ArrayList();

    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "from Emp emp ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgId != null) {
            criterion += "  emp.org.id=?  and   ";
            parameters.add(orgId);
          }

          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf(" and "));

          String ob = "";
          if (orderBy != null && orderBy.length > 0) {
            for (int i = 0; i < orderBy.length; i++) {
              if (orderBy[i] == "emp.empInfo.name") {
                ob += "nlssort( " + orderBy[i].toString()
                    + " ,'NLS_SORT=SCHINESE_PINYIN_M'),";
              } else {
                ob += " " + orderBy[i].toString() + " , ";
              }
            }
            ob = ob.substring(0, ob.lastIndexOf(","));
            ob += " ";
          }
          String od = order;
          if (od == null)
            od = "ASC";
          hql = hql + criterion + "order by " + ob + " " + od;
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
    return list;
  }

  /*
   * 李鹏转出导出使用
   */
  public List queryEmpByOrgIdSLPL(final String orgId, final String[] orderBy) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from Emp emp "
            + "where emp.org.id = ? and emp.payStatus <> '6' ";

        String ob = "";
        if (orderBy != null && orderBy.length > 0) {
          for (int i = 0; i < orderBy.length; i++) {
            if (orderBy[i] == "emp.empInfo.name") {
              ob += "nlssort( " + orderBy[i].toString()
                  + " ,'NLS_SORT=SCHINESE_PINYIN_M'),";
            } else {
              ob += " " + orderBy[i].toString() + " , ";
            }
          }
          ob = ob.substring(0, ob.lastIndexOf(","));
          ob += " ";
        }
        String od = "ASC";
        hql = hql + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(orgId));
        return query.list();
      }
    });
    return list;
  }

  /*
   * 提取导出
   */
  public List getExportData(final int orgId, final String[] orderBy) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // select info.name from aa001 org,aa002 emp,ba002 info
          // where emp.org_id = org.id and info.id = emp.emp_info_id
          // and org.id = 33
          // 职工编号、职工姓名、证件类型、证件号码
          String hql = "from Emp emp where emp.org.id=? ";
          String ob = "";
          if (orderBy != null && orderBy.length > 0) {
            for (int i = 0; i < orderBy.length; i++) {
              if (orderBy[i] == "emp.empInfo.name") {
                ob += "nlssort( " + orderBy[i].toString()
                    + " ,'NLS_SORT=SCHINESE_PINYIN_M'),";
              } else {
                ob += " " + orderBy[i].toString() + " , ";
              }
            }
            ob = ob.substring(0, ob.lastIndexOf(","));
            ob += " ";
          }
          String od = "ASC";
          hql = hql + "order by " + ob + " " + od;

          List l = session.createQuery(hql).setInteger(0, orgId).list();
          return l;
        }
      });
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  public List empOpenQueryByCriterionsSL01(final Serializable orgId,
      final Serializable id, final String name, final String cardNumber) {

    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select emp from Emp emp ";
          Vector parameters = new Vector();
          String criterion = "";
          if (id != null && !id.equals("")) {
            criterion += "to_char(emp.empId) like ? and ";
            parameters.add("%" + id.toString() + "%");
          }

          if (name != null && !name.equals("")) {
            criterion += "emp.empInfo.name like ? escape '/' and ";
            parameters.add("%" + name + "%");
          }

          if (cardNumber != null && !cardNumber.equals("")) {
            criterion += "(emp.empInfo.cardNum like ? escape '/' or emp.empInfo.standbyCardNum like ? escape '/') and ";
            parameters.add("%" + cardNumber + "%");
            parameters.add("%" + cardNumber + "%");
          }

          if (orgId != null && !orgId.equals("")) {
            criterion += "emp.org.id = ? and ";
            parameters.add(orgId);
          }

          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

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

  /*
   * 查询职工信息-排序
   */
  public List queryEmpByOrgIdSLSort(final String orgId, final String[] orderBy) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from Emp emp " + "where emp.org.id = ? ";
        String ob = "";
        if (orderBy != null && orderBy.length > 0) {
          for (int i = 0; i < orderBy.length; i++) {
            if (orderBy[i] == "emp.empInfo.name") {
              ob += "nlssort( " + orderBy[i].toString()
                  + " ,'NLS_SORT=SCHINESE_PINYIN_M'),";
            } else {
              ob += " " + orderBy[i].toString() + " , ";
            }
          }
          ob = ob.substring(0, ob.lastIndexOf(","));
          ob += " ";
        }
        String od = "ASC";
        hql = hql + "order by " + ob + " " + od;
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(orgId));
        return query.list();
      }
    });
    return list;
  }

  /*
   * 导出排序
   */

  public Emp queryByCriterionsSort(final String empid, final String orgid,
      final String[] orderBy) {

    Emp emp = null;
    emp = (Emp) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select emp from Emp emp  ";
        Vector parameters = new Vector();
        String criterion = "";
        String ob = "";
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
        if (orderBy != null && orderBy.length > 0) {
          for (int i = 0; i < orderBy.length; i++) {
            if (orderBy[i] == "emp.empInfo.name") {
              ob += "nlssort( " + orderBy[i].toString()
                  + " ,'NLS_SORT=SCHINESE_PINYIN_M'),";
            } else {
              ob += " " + orderBy[i].toString() + " , ";
            }
          }
          ob = ob.substring(0, ob.lastIndexOf(","));
          ob += " ";
        }
        String od = "ASC";
        hql = hql + criterion + "order by " + ob + " " + od;

        Query query0 = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query0.setParameter(i, parameters.get(i));
        }
        return query0.uniqueResult();
      }
    });

    return emp;
  }

  /**
   * 在aa002中查询可以做自动变更的职工
   * 
   * @param orgId
   * @return 职工信息
   * @author 付云峰
   */
  public List queryAutoChangeEmpInfo(final String orgId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a002.id, b002.name, b002.card_num,a002.pay_status"
              + " from aa002 a002, ba002 b002"
              + " where a002.emp_info_id = b002.id"
              + " and a002.pay_status in ('3', '4')" + "and a002.org_id = ? ";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(orgId));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 归集财务对账查询-职工信息的list
   * 
   * @param orgId
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param securityInfo
   * @return
   * @author 付云峰
   */
  public List queryCollFnComparisonEmpInfoList(final String empId,
      final String empName, final String cardNum, final String orgId,
      final String orderBy, final String order, final int start,
      final int pageSize, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select " + "a.id," + "b.name," + "b.card_num,"
              + "a.salary_base," + "nvl(a.org_pay,0)," + "nvl(a.emp_pay,0),"
              + "a.pay_status," + "a.org_pay_month," + "a.emp_pay_month"
              + " from aa002 a, ba002 b" + " where a.emp_info_id = b.id"
              + " and a.org_id=? and a.org_id "
              + securityInfo.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          parameters.add(orgId);
          if (empId != null && !empId.equals("")) {
            criterion += " a.id = ? and ";
            parameters.add(empId.trim());
          }
          if (empName != null && !empName.equals("")) {
            criterion += " b.name=? and  ";
            parameters.add(empName.trim());
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " (b.card_num=? or b.standby_card_num=?) and ";
            parameters.add(cardNum.trim());
            parameters.add(cardNum.trim());
          }

          String ob = orderBy;
          if (ob == null) {
            ob = " a.id ";
          }
          String or = order;
          if (or == null) {
            or = "desc";
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf(" and "));

          hql = hql + criterion + " order by " + ob + " " + or;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          Object obj[] = null;
          List list = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CollFnComparisonEmpInfoDTO collFnComparisonEmpInfoDTO = new CollFnComparisonEmpInfoDTO();
            if (obj[0] != null) {
              collFnComparisonEmpInfoDTO
                  .setEmpid(new Integer(obj[0].toString()));
            }
            if (obj[1] != null) {
              collFnComparisonEmpInfoDTO.setEmpname(obj[1].toString());
            }
            if (obj[2] != null) {
              collFnComparisonEmpInfoDTO.setCardnum(obj[2].toString());
            }
            if (obj[3] != null) {
              collFnComparisonEmpInfoDTO.setSalarybase(new BigDecimal(obj[3]
                  .toString()));
            }
            if (obj[4] != null) {
              collFnComparisonEmpInfoDTO.setOrgpay(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[5] != null) {
              collFnComparisonEmpInfoDTO.setEmppay(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              collFnComparisonEmpInfoDTO.setPaystatus(obj[6].toString());
            }
            if (obj[7] != null) {
              collFnComparisonEmpInfoDTO.setOrgpaymonth(obj[7].toString());
            }
            if (obj[8] != null) {
              collFnComparisonEmpInfoDTO.setEmppaymonth(obj[8].toString());
            }
            collFnComparisonEmpInfoDTO.setPaycount(collFnComparisonEmpInfoDTO
                .getOrgpay().add(collFnComparisonEmpInfoDTO.getEmppay()));

            list.add(collFnComparisonEmpInfoDTO);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  /**
   * 归集财务对账查询-职工信息的count
   * 
   * @param orgId
   * @param securityInfo
   * @return
   * @author 付云峰
   */
  public List queryCollFnComparisonEmpInfoCount(final String empId,
      final String empName, final String cardNum, final String orgId,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select " + "a.id," + "b.name," + "b.card_num,"
              + "a.salary_base," + "nvl(a.org_pay,0)," + "nvl(a.emp_pay,0),"
              + "a.pay_status," + "a.org_pay_month," + "a.emp_pay_month"
              + " from aa002 a, ba002 b" + " where a.emp_info_id = b.id"
              + " and a.org_id=? and a.org_id "
              + securityInfo.getGjjSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";
          parameters.add(orgId);
          if (empId != null && !empId.equals("")) {
            criterion += " a.id = ? and ";
            parameters.add(empId.trim());
          }
          if (empName != null && !empName.equals("")) {
            criterion += " b.name=? and  ";
            parameters.add(empName.trim());
          }
          if (cardNum != null && !cardNum.equals("")) {
            criterion += " (b.card_num=? or b.standby_card_num=?) and ";
            parameters.add(cardNum.trim());
            parameters.add(cardNum.trim());
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf(" and "));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          Object obj[] = null;
          List list = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CollFnComparisonEmpInfoDTO collFnComparisonEmpInfoDTO = new CollFnComparisonEmpInfoDTO();
            if (obj[0] != null) {
              collFnComparisonEmpInfoDTO
                  .setEmpid(new Integer(obj[0].toString()));
            }
            if (obj[1] != null) {
              collFnComparisonEmpInfoDTO.setEmpname(obj[1].toString());
            }
            if (obj[2] != null) {
              collFnComparisonEmpInfoDTO.setCardnum(obj[2].toString());
            }
            if (obj[3] != null) {
              collFnComparisonEmpInfoDTO.setSalarybase(new BigDecimal(obj[3]
                  .toString()));
            }
            if (obj[4] != null) {
              collFnComparisonEmpInfoDTO.setOrgpay(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[5] != null) {
              collFnComparisonEmpInfoDTO.setEmppay(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              collFnComparisonEmpInfoDTO.setPaystatus(obj[6].toString());
            }
            if (obj[7] != null) {
              collFnComparisonEmpInfoDTO.setOrgpaymonth(obj[7].toString());
            }
            if (obj[8] != null) {
              collFnComparisonEmpInfoDTO.setEmppaymonth(obj[8].toString());
            }
            collFnComparisonEmpInfoDTO.setPaycount(collFnComparisonEmpInfoDTO
                .getOrgpay().add(collFnComparisonEmpInfoDTO.getEmppay()));

            list.add(collFnComparisonEmpInfoDTO);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public List queryCollFnComparisonEmpAccountList(final String orgId,
      final String empId, final String orgName, final String empName,
      final String cardNum, final String timeSt, final String timeEnd,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a1.* "
              + " from (select distinct a101.doc_num, "
              + "    a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + " aa101 a101, "
              + "   aa002 a002, "
              + "   ba002 b002, "
              + "  aa001 a001, "
              + "   ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + "  and a002.emp_info_id = b002.id "
              + "  and a001.id = a101.org_id "
              + "  and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + "  and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id =  "
              + empId
              + " and a002.org_id ="
              + orgId
              + " and f201.reserve_c is null "
              + " union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + " a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + "   aa101 a101, "
              + "   aa002 a002, "
              + "   ba002 b002, "
              + "   aa001 a001, "
              + "  ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + "  and a002.emp_info_id = b002.id "
              + " and a001.id = a101.org_id "
              + " and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id =  "
              + empId
              + " and a101.biz_type in ('A', 'B', 'M') "
              + " and A002.org_id = "
              + orgId
              +
              // " and f201.reserve_c = '1' "+
              " union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + " aa101 a101, "
              + "   aa002 a002, "
              + "    ba002 b002, "
              + "   aa001 a001, "
              + "   ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + "  and a002.emp_info_id = b002.id "
              + " and a001.id = a101.org_id "
              + " and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id = "
              + empId
              + " and a101.biz_type = 'C' "
              + " and a101.org_id = "
              + orgId
              +
              // " and (f201.reserve_c = '9' or f201.reserve_c = '10') "+
              " union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + " aa101 a101, "
              + "  aa002 a002, "
              + "  ba002 b002, "
              + "  aa001 a001, "
              + "   ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + " and a002.emp_info_id = b002.id "
              + " and a001.id = a101.org_id "
              + " and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id =  "
              + empId
              + " and a101.biz_type = 'E' "
              + " and a101.org_id =  "
              + orgId
              + " and f201.reserve_c = '4' "
              + " union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + " aa101 a101, "
              + "   aa002 a002, "
              + "  ba002 b002, "
              + "  aa001 a001, "
              + "   ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + " and a002.emp_info_id = b002.id "
              + "  and a001.id = a101.org_id "
              + "  and a001.orginfo_id = b001.id "
              + "  and a101.org_id = a002.org_id "
              + "  and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id =  "
              + empId
              + " and a101.biz_type = 'F' "
              + " and a101.org_id =  "
              + orgId
              + " and f201.reserve_c = '5' "
              + "               union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + "  aa101 a101, "
              + "   aa002 a002, "
              + "   ba002 b002, "
              + "   aa001 a001, "
              + "   ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + " and a002.emp_info_id = b002.id "
              + " and a001.id = a101.org_id "
              + " and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id ="
              + empId
              + " and a101.biz_type = 'D' "
              + " and a101.interest > 0 "
              + " and a101.org_id =  "
              + orgId
              + " and f201.reserve_c = '3' "
              + "           union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, " + " aa101 a101, " + "   aa002 a002, "
              + "    ba002 b002, " + "   aa001 a001, " + "    ba001 b001, "
              + "    fn201 f201 " + " where a102.org_flow_id = a101.id "
              + "  and a002.emp_info_id = b002.id "
              + " and a001.id = a101.org_id "
              + " and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' " + " and a102.emp_id =  " + empId
              + " and a101.biz_type = 'D' " + " and a101.interest = 0 "
              + " and a101.org_id =  " + orgId
              + " and f201.reserve_c = '2') a1 ";

          Vector parameters = new Vector();
          String criterion = "";

          if (cardNum != null && !cardNum.equals("")) {
            criterion += " (a1.card_num=? or a1.standby_card_num=?) and ";
            parameters.add(cardNum.trim());
            parameters.add(cardNum.trim());
          }
          if (orgName != null && !orgName.equals("")) {
            criterion += "  a1.orgname=? and ";
            parameters.add(orgName.trim());
          }
          if (empName != null && !empName.equals("")) {
            criterion += " a1.empname=? and ";
            parameters.add(empName.trim());
          }
          if (timeSt != null && !timeSt.equals("")) {
            criterion += " a1.sett_date>=? and ";
            parameters.add(timeSt.trim());
          }
          if (timeEnd != null && !timeEnd.equals("")) {
            criterion += " a1.sett_date<=? and ";
            parameters.add(timeEnd.trim());
          }

          String ob = orderBy;
          if (ob == null) {
            ob = " a1.org_id,a1.emp_id,a1.sett_date,a1.id ";
          }
          String or = order;
          if (or == null) {
            or = "asc";
          }
          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf(" and "));

          hql = hql + criterion + " order by " + ob + " " + or;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          Object obj[] = null;
          List list = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CollFnComparisonEmpAccountDTO collFnComparisonEmpAccountDTO = new CollFnComparisonEmpAccountDTO();
            if (obj[0] != null) {
              collFnComparisonEmpAccountDTO.setCollDocNum(obj[0].toString());
            }
            if (obj[1] != null) {
              collFnComparisonEmpAccountDTO.setBizType(obj[1].toString());
            }
            if (obj[2] != null) {
              collFnComparisonEmpAccountDTO.setCollDate(obj[2].toString());
            }
            if (obj[3] != null) {
              collFnComparisonEmpAccountDTO.setBizStatus(obj[3].toString());
            }
            if (obj[4] != null) {
              collFnComparisonEmpAccountDTO.setDebit(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[5] != null) {
              collFnComparisonEmpAccountDTO.setCredit(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              collFnComparisonEmpAccountDTO.setMoneySum(new BigDecimal(obj[6]
                  .toString()));
            }
            if (obj[7] != null) {
              collFnComparisonEmpAccountDTO.setInterest(new BigDecimal(obj[7]
                  .toString()));
            }
            // if (obj[8] != null) {
            // collFnComparisonEmpAccountDTO.setDebit(new BigDecimal(obj[8]
            // .toString()));
            // }
            if (obj[9] != null) {
              collFnComparisonEmpAccountDTO.setOrgId(obj[9].toString());
            }
            if (obj[10] != null) {
              collFnComparisonEmpAccountDTO.setEmpId(obj[10].toString());
            }
            if (obj[11] != null) {
              collFnComparisonEmpAccountDTO.setCaiwStatus(obj[11].toString());
            }
            // collFnComparisonEmpInfoDTO.setPaycount(collFnComparisonEmpInfoDTO
            // .getOrgpay().add(collFnComparisonEmpInfoDTO.getEmppay()));

            list.add(collFnComparisonEmpAccountDTO);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public List queryCollFnComparisonEmpAccountCount(final String orgId,
      final String empId, final String orgName, final String empName,
      final String cardNum, final String timeSt, final String timeEnd) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a1.* "
              + " from (select distinct a101.doc_num, "
              + "    a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + " aa101 a101, "
              + "   aa002 a002, "
              + "   ba002 b002, "
              + "  aa001 a001, "
              + "   ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + "  and a002.emp_info_id = b002.id "
              + "  and a001.id = a101.org_id "
              + "  and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + "  and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id =  "
              + empId
              + " and a002.org_id ="
              + orgId
              + " and f201.reserve_c is null "
              + " union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + " a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + "   aa101 a101, "
              + "   aa002 a002, "
              + "   ba002 b002, "
              + "   aa001 a001, "
              + "  ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + "  and a002.emp_info_id = b002.id "
              + " and a001.id = a101.org_id "
              + " and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id =  "
              + empId
              + " and a101.biz_type in ('A', 'B', 'M') "
              + " and A002.org_id = "
              + orgId
              +
              // " and f201.reserve_c = '1' "+
              " union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + " aa101 a101, "
              + "   aa002 a002, "
              + "    ba002 b002, "
              + "   aa001 a001, "
              + "   ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + "  and a002.emp_info_id = b002.id "
              + " and a001.id = a101.org_id "
              + " and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id = "
              + empId
              + " and a101.biz_type = 'C' "
              + " and a101.org_id = "
              + orgId
              +
              // " and (f201.reserve_c = '9' or f201.reserve_c = '10') "+
              " union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + " aa101 a101, "
              + "  aa002 a002, "
              + "  ba002 b002, "
              + "  aa001 a001, "
              + "   ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + " and a002.emp_info_id = b002.id "
              + " and a001.id = a101.org_id "
              + " and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id =  "
              + empId
              + " and a101.biz_type = 'E' "
              + " and a101.org_id =  "
              + orgId
              + " and f201.reserve_c = '4' "
              + " union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + " aa101 a101, "
              + "   aa002 a002, "
              + "  ba002 b002, "
              + "  aa001 a001, "
              + "   ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + " and a002.emp_info_id = b002.id "
              + "  and a001.id = a101.org_id "
              + "  and a001.orginfo_id = b001.id "
              + "  and a101.org_id = a002.org_id "
              + "  and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id =  "
              + empId
              + " and a101.biz_type = 'F' "
              + " and a101.org_id =  "
              + orgId
              + " and f201.reserve_c = '5' "
              + "               union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, "
              + "  aa101 a101, "
              + "   aa002 a002, "
              + "   ba002 b002, "
              + "   aa001 a001, "
              + "   ba001 b001, "
              + "   fn201 f201 "
              + " where a102.org_flow_id = a101.id "
              + " and a002.emp_info_id = b002.id "
              + " and a001.id = a101.org_id "
              + " and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' "
              + " and a102.emp_id ="
              + empId
              + " and a101.biz_type = 'D' "
              + " and a101.interest > 0 "
              + " and a101.org_id =  "
              + orgId
              + " and f201.reserve_c = '3' "
              + "           union all "
              + " select distinct a101.doc_num, "
              + "  a101.biz_type, "
              + "  a101.sett_date, "
              + "  a101.biz_status, "
              + "  nvl(a102.debit, 0), "
              + "  nvl(a102.credit, 0), "
              + "  nvl(((select sum(a.credit - a.debit) "
              + "          from aa102 a "
              + "         where a.org_flow_id < a101.id "
              + "           and a.emp_id = a002.id) + a102.credit - "
              + "      a102.debit), "
              + "      0), "
              + "  nvl(a102.interest, 0), "
              + "  a102.id, "
              + "  a002.org_id, "
              + "  a102.emp_id, "
              + "  f201.credence_st,b001.name orgname,b002.name empname,b002.card_num,b002.standby_card_num "
              + " from aa102 a102, " + " aa101 a101, " + "   aa002 a002, "
              + "    ba002 b002, " + "   aa001 a001, " + "    ba001 b001, "
              + "    fn201 f201 " + " where a102.org_flow_id = a101.id "
              + "  and a002.emp_info_id = b002.id "
              + " and a001.id = a101.org_id "
              + " and a001.orginfo_id = b001.id "
              + " and a101.org_id = a002.org_id "
              + " and a002.id = a102.emp_id "
              + " and a101.note_num = f201.sett_num(+) "
              + " and a101.biz_status = '5' " + " and a102.emp_id =  " + empId
              + " and a101.biz_type = 'D' " + " and a101.interest = 0 "
              + " and a101.org_id =  " + orgId
              + " and f201.reserve_c = '2') a1 ";
          Vector parameters = new Vector();
          String criterion = "";

          if (cardNum != null && !cardNum.equals("")) {
            criterion += " (a1.card_num=? or a1.standby_card_num=?) and ";
            parameters.add(cardNum.trim());
            parameters.add(cardNum.trim());
          }
          if (orgName != null && !orgName.equals("")) {
            criterion += "  a1.orgname=? and ";
            parameters.add(orgName.trim());
          }
          if (empName != null && !empName.equals("")) {
            criterion += " a1.empname=? and ";
            parameters.add(empName.trim());
          }
          if (timeSt != null && !timeSt.equals("")) {
            criterion += " a1.sett_date>=? and ";
            parameters.add(timeSt.trim());
          }
          if (timeEnd != null && !timeEnd.equals("")) {
            criterion += " a1.sett_date<=? and ";
            parameters.add(timeEnd.trim());
          }

          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf(" and "));

          hql = hql + criterion
              + " order by a1.org_id,a1.emp_id,a1.sett_date,a1.id asc ";

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          Object obj[] = null;
          List list = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CollFnComparisonEmpAccountDTO collFnComparisonEmpAccountDTO = new CollFnComparisonEmpAccountDTO();
            if (obj[0] != null) {
              collFnComparisonEmpAccountDTO.setCollDocNum(obj[0].toString());
            }
            if (obj[1] != null) {
              collFnComparisonEmpAccountDTO.setBizType(obj[1].toString());
            }
            if (obj[2] != null) {
              collFnComparisonEmpAccountDTO.setCollDate(obj[2].toString());
            }
            if (obj[3] != null) {
              collFnComparisonEmpAccountDTO.setBizStatus(obj[3].toString());
            }
            if (obj[4] != null) {
              collFnComparisonEmpAccountDTO.setDebit(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[5] != null) {
              collFnComparisonEmpAccountDTO.setCredit(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              collFnComparisonEmpAccountDTO.setMoneySum(new BigDecimal(obj[6]
                  .toString()));
            }
            if (obj[7] != null) {
              collFnComparisonEmpAccountDTO.setInterest(new BigDecimal(obj[7]
                  .toString()));
            }
            // if (obj[8] != null) {
            // collFnComparisonEmpAccountDTO.setDebit(new BigDecimal(obj[8]
            // .toString()));
            // }
            if (obj[9] != null) {
              collFnComparisonEmpAccountDTO.setOrgId(obj[9].toString());
            }
            if (obj[10] != null) {
              collFnComparisonEmpAccountDTO.setEmpId(obj[10].toString());
            }
            // collFnComparisonEmpInfoDTO.setPaycount(collFnComparisonEmpInfoDTO
            // .getOrgpay().add(collFnComparisonEmpInfoDTO.getEmppay()));

            list.add(collFnComparisonEmpAccountDTO);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  /**
   * 查询对账弹出框List
   * 
   * @param orgId
   * @param empId
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @author 付云峰
   */
  public List queryComparisonEmpInfoPopList(final String orgId,
      final String docNum, final String orderBy, final String order,
      final int start, final int pageSize) {

    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a102.emp_id, b002.name, nvl(a102.debit,0), nvl(a102.credit,0), nvl(a102.interest,0)"
              + " from aa101 a101, aa102 a102, aa002 a002, ba002 b002"
              + " where a101.id = a102.org_flow_id"
              + " and a002.emp_info_id = b002.id"
              + " and a002.id = a102.emp_id"
              + " and a101.org_id = ?"
              + " and a101.doc_num = ?";

          String ob = orderBy;
          if (ob == null) {
            ob = " a102.emp_id ";
          }
          String or = order;
          if (or == null) {
            or = "desc";
          }

          hql = hql + " order by " + ob + " " + or;
          session.clear();
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, new Integer(orgId));
          query.setParameter(1, new Integer(docNum));

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          Object obj[] = null;
          List list = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CollFnComparisonEmpPopDTO collFnComparisonEmpPopDTO = new CollFnComparisonEmpPopDTO();
            if (obj[0] != null) {
              collFnComparisonEmpPopDTO.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              collFnComparisonEmpPopDTO.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              collFnComparisonEmpPopDTO.setDebit(new BigDecimal(obj[2]
                  .toString()));
            }
            if (obj[3] != null) {
              collFnComparisonEmpPopDTO.setCredit(new BigDecimal(obj[3]
                  .toString()));
            }
            if (obj[4] != null) {
              collFnComparisonEmpPopDTO.setInterest(new BigDecimal(obj[4]
                  .toString()));
            }

            list.add(collFnComparisonEmpPopDTO);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;

  }

  /**
   * 查询对账弹出框Count
   * 
   * @param orgId
   * @param docNum
   * @return
   */
  public List queryComparisonEmpInfoPopCount(final String orgId,
      final String docNum) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a102.emp_id, b002.name, a102.debit, a102.credit, a102.interest"
              + " from aa101 a101, aa102 a102, aa002 a002, ba002 b002"
              + " where a101.id = a102.org_flow_id"
              + " and a002.emp_info_id = b002.id"
              + " and a002.id = a102.emp_id"
              + " and a101.org_id = ?"
              + " and a101.doc_num = ?";

          session.clear();
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, new Integer(orgId));
          query.setParameter(1, new Integer(docNum));

          Iterator it = query.list().iterator();
          Object obj[] = null;
          List list = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CollFnComparisonEmpPopDTO collFnComparisonEmpPopDTO = new CollFnComparisonEmpPopDTO();
            if (obj[0] != null) {
              collFnComparisonEmpPopDTO.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              collFnComparisonEmpPopDTO.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              collFnComparisonEmpPopDTO.setDebit(new BigDecimal(obj[2]
                  .toString()));
            }
            if (obj[3] != null) {
              collFnComparisonEmpPopDTO.setCredit(new BigDecimal(obj[3]
                  .toString()));
            }
            if (obj[4] != null) {
              collFnComparisonEmpPopDTO.setInterest(new BigDecimal(obj[4]
                  .toString()));
            }

            list.add(collFnComparisonEmpPopDTO);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  /**
   * 查询单位名称的方法
   * 
   * @param orgId
   * @return
   */
  public String queryComparisonOrgName(final String orgId) {
    String orgName = null;
    try {
      orgName = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select b.name from aa001 a,ba001 b where a.orginfo_id=b.id and a.id=?";

              session.clear();
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, new Integer(orgId));

              return query.uniqueResult();
            }
          });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return orgName;
  }

  /**
   * 职工账查询弹出框列表List的查询方法
   * 
   * @param orgid
   * @param orgname
   * @param empid
   * @param empname
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param securityInfo
   * @return
   */
  public List queryCollFnComparisonEmpAccountPopList(final String orgid,
      final String orgname, final String empid, final String empname,
      final String orderBy, final String order, final int start,
      final int pageSize, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a001.id as orgid, b001.name as orgname, a002.id as empid, b002.name as empname, b002.card_num"
              + "  from aa002 a002, ba002 b002, ba001 b001, aa001 a001"
              + " where b002.id = a002.emp_info_id"
              + " and a002.org_id = a001.id"
              + " and a001.orginfo_id = b001.id and a001.id "
              + securityInfo.getGjjSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";
          if (orgid != null && !orgid.equals("")) {
            criterion += " a001.id like ? and ";
            parameters.add("%" + new Integer(orgid.trim()).toString() + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " b001.name like ? and  ";
            parameters.add("%" + orgname.trim() + "%");
          }
          if (empid != null && !empid.equals("")) {
            criterion += " a002.id like ? and ";
            parameters.add("%" + new Integer(empid.trim()).toString() + "%");
          }
          if (empname != null && !empname.equals("")) {
            criterion += " b002.name like ? and ";
            parameters.add("%" + empname.trim() + "%");
          }

          String ob = orderBy;
          if (ob == null) {
            ob = " a001.id ";
          }
          String or = order;
          if (or == null) {
            or = "asc";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf(" and "));

          hql = hql + criterion + " order by " + ob + " " + or;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.list().iterator();
          Object obj[] = null;
          List list = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CollFnComparisonEmpAccountPopDTO collFnComparisonEmpAccountPopDTO = new CollFnComparisonEmpAccountPopDTO();
            if (obj[0] != null) {
              collFnComparisonEmpAccountPopDTO.setOrgId(new Integer(obj[0]
                  .toString()));
            }
            if (obj[1] != null) {
              collFnComparisonEmpAccountPopDTO.setOrgName(obj[1].toString());
            }
            if (obj[2] != null) {
              collFnComparisonEmpAccountPopDTO.setEmpId(obj[2].toString());
            }
            if (obj[3] != null) {
              collFnComparisonEmpAccountPopDTO.setEmpName(obj[3].toString());
            }
            if (obj[4] != null) {
              collFnComparisonEmpAccountPopDTO.setCardNum(obj[4].toString());
            }
            list.add(collFnComparisonEmpAccountPopDTO);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  /**
   * 职工账查询弹出框列表查询Count的方法
   * 
   * @param orgid
   * @param orgname
   * @param empid
   * @param empname
   * @param securityInfo
   * @return
   */
  public List queryCollFnComparisonEmpAccountPopCount(final String orgid,
      final String orgname, final String empid, final String empname,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a001.id as orgid, b001.name as orgname, a002.id as empid, b002.name as empname, b002.card_num"
              + "  from aa002 a002, ba002 b002, ba001 b001, aa001 a001"
              + " where b002.id = a002.emp_info_id"
              + " and a002.org_id = a001.id"
              + " and a001.orginfo_id = b001.id and a001.id "
              + securityInfo.getGjjSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";
          if (orgid != null && !orgid.equals("")) {
            criterion += " a001.id like ? and ";
            parameters.add("%" + new Integer(orgid.trim()).toString() + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " b001.name like ? and  ";
            parameters.add("%" + orgname.trim() + "%");
          }
          if (empid != null && !empid.equals("")) {
            criterion += " a002.id like ? and ";
            parameters.add("%" + new Integer(empid.trim()).toString() + "%");
          }
          if (empname != null && !empname.equals("")) {
            criterion += " b002.name like ? and ";
            parameters.add("%" + empname.trim() + "%");
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf(" and "));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          Object obj[] = null;
          List list = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CollFnComparisonEmpAccountPopDTO collFnComparisonEmpAccountPopDTO = new CollFnComparisonEmpAccountPopDTO();
            if (obj[0] != null) {
              collFnComparisonEmpAccountPopDTO.setOrgId(new Integer(obj[0]
                  .toString()));
            }
            if (obj[1] != null) {
              collFnComparisonEmpAccountPopDTO.setOrgName(obj[1].toString());
            }
            if (obj[2] != null) {
              collFnComparisonEmpAccountPopDTO.setEmpId(obj[2].toString());
            }
            if (obj[3] != null) {
              collFnComparisonEmpAccountPopDTO.setEmpName(obj[3].toString());
            }
            if (obj[4] != null) {
              collFnComparisonEmpAccountPopDTO.setCardNum(obj[4].toString());
            }
            list.add(collFnComparisonEmpAccountPopDTO);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  /**
   * 查询职工账打印内容的方法
   * 
   * @param orgidst
   * @param orgidend
   * @param empidst
   * @param empidend
   * @param timeSt
   * @param timeEnd
   * @return
   * @author 付云峰
   */
  public List queryEmpAccountPrint(final String orgidst, final String orgidend,
      final String empidst, final String empidend, final String timeSt,
      final String timeEnd, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct a101.doc_num,"
              + "a101.biz_type,"
              + "a101.sett_date,"
              + "a101.biz_status,"
              + "nvl(a102.debit, 0),"
              + "nvl(a102.credit, 0),"
              // + "nvl(((select sum(a.credit - a.debit)"
              // + " from aa102 a"
              // + " where a.org_flow_id < a101.id"
              // + " and a.emp_id = a002.id) + a102.credit - a102.debit),0),"
              + "nvl(a102.interest,0),"
              + "a102.id,"
              + "a002.org_id,"
              + "a102.emp_id,"
              + "b001.name as orgname,"
              + "b002.name as empname"
              + " from aa102 a102, aa101 a101, aa002 a002, ba002 b002,aa001 a001,ba001 b001"
              + " where a102.org_flow_id = a101.id"
              + " and a002.emp_info_id = b002.id"
              + " and a001.id = a101.org_id" + " and a001.orginfo_id = b001.id"
              + " and a101.org_id = a002.org_id" + " and a002.id = a102.emp_id"
              + " and a101.biz_status='5' and a001.id "
              + securityInfo.getGjjSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";
          if (orgidst != null && !orgidst.equals("")) {
            criterion += " a001.id >= ? and ";
            parameters.add(orgidst.trim());
          }

          if (orgidend != null && !orgidend.equals("")) {
            criterion += " a001.id <= ? and  ";
            parameters.add(orgidend.trim());
          }
          if (empidst != null && !empidst.equals("")) {
            criterion += " a002.id>=? and ";
            parameters.add(empidst.trim());
          }
          if (empidend != null && !empidend.equals("")) {
            criterion += " a002.id<=? and ";
            parameters.add(empidend.trim());
          }
          if (timeSt != null && !timeSt.equals("")) {
            criterion += " a101.sett_date>=? and ";
            parameters.add(timeSt.trim());
          }
          if (timeEnd != null && !timeEnd.equals("")) {
            criterion += " a101.sett_date<=? and ";
            parameters.add(timeEnd.trim());
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf(" and "));
          String ob = "a002.org_id,a102.emp_id,a101.sett_date,a101.doc_num asc";
          hql = hql + criterion + " order by " + ob;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          Object obj[] = null;
          List list = new ArrayList();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            CollFnComparisonEmpAccountDTO collFnComparisonEmpAccountDTO = new CollFnComparisonEmpAccountDTO();
            if (obj[0] != null) {
              collFnComparisonEmpAccountDTO.setCollDocNum(obj[0].toString());
            }
            if (obj[1] != null) {
              collFnComparisonEmpAccountDTO.setBizType(obj[1].toString());
            }
            if (obj[2] != null) {
              collFnComparisonEmpAccountDTO.setCollDate(obj[2].toString());
            }
            if (obj[3] != null) {
              collFnComparisonEmpAccountDTO.setBizStatus(obj[3].toString());
            }
            if (obj[4] != null) {
              collFnComparisonEmpAccountDTO.setDebit(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[5] != null) {
              collFnComparisonEmpAccountDTO.setCredit(new BigDecimal(obj[5]
                  .toString()));
            }

            if (obj[6] != null) {
              collFnComparisonEmpAccountDTO.setRemnant(new BigDecimal(obj[6]
                  .toString()));
            }
            // if (collFnComparisonEmpAccountDTO.getMoneySum().compareTo(
            // new BigDecimal(0)) >= 0) {
            // collFnComparisonEmpAccountDTO.setDirection("借");
            // } else if (collFnComparisonEmpAccountDTO.getMoneySum().compareTo(
            // new BigDecimal(0)) < 0) {
            // collFnComparisonEmpAccountDTO.setDirection("贷");
            // } else {
            // collFnComparisonEmpAccountDTO.setDirection("平");
            // }

            if (obj[8] != null) {
              collFnComparisonEmpAccountDTO.setOrgId(obj[8].toString());
            }
            if (obj[9] != null) {
              collFnComparisonEmpAccountDTO.setEmpId(obj[9].toString());
            }
            if (obj[10] != null) {
              collFnComparisonEmpAccountDTO.setOrgName(obj[10].toString());
            }
            if (obj[11] != null) {
              collFnComparisonEmpAccountDTO.setEmpName(obj[11].toString());
            }
            list.add(collFnComparisonEmpAccountDTO);
          }
          return list;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  /**
   * 根据单位编号,职工姓名和职工身份证号判断在变更新增中有无同证同名的职工
   * 
   * @author wshuang
   * @param empname
   * @param cardnum
   * @return
   */
  public int queryCountByCid_Name(final String orgId, final String empName,
      final String cardNum) {
    Integer count = new Integer(0);
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select ("
              + " (select count(a2.org_id)"
              + " from aa002 a2, ba002 b2"
              + " where a2.emp_info_id = b2.id"
              // + " and a2.org_id = ? "
              + " and b2.card_kind = 0 " + " and b2.name = ? "
              + " and (b2.card_num = ? or b2.card_num = ? ))"
              + " + "
              + " (select count(a204.org_id)"
              + " from aa204 a204,aa205 a205"
              + " where a204.id = a205.chg_head_id"
              // + " and a204.org_id = ? "
              + " and a204.chg_status = 1 " + " and a205.name = ? "
              + " and a205.card_kind = 0"
              + " and (a205.card_num = ? or a205.card_num = ? )))"
              + " from dual";
          Query query = session.createSQLQuery(sql);
          String cardNum_temp = "";
          if (cardNum.length() == 18) {
            cardNum_temp = CardMunChange.get15Id(cardNum);
          } else if (cardNum.length() == 15) {
            cardNum_temp = CardMunChange.get18Id(cardNum);
          }
          // query.setParameter(0, Integer.valueOf(orgId));
          query.setParameter(0, empName);
          query.setParameter(1, cardNum);
          query.setParameter(2, cardNum_temp);
          // query.setParameter(4, Integer.valueOf(orgId));
          query.setParameter(3, empName);
          query.setParameter(4, cardNum);
          query.setParameter(5, cardNum_temp);
          return Integer.valueOf(query.uniqueResult().toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }

  /**
   * bit add 根据单位ID查询职工各种信息
   */
  public List queryprintlistbyidandname(final String orgid, final String orgname) {
    List temp = new ArrayList();
    try {
      temp = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a.id,b.name as empname,b.sex,b.card_num,a.salary_base,(a.org_pay + a.emp_pay),a.emp_pay,a.org_pay,c.id as orgid,d.name as orgname,d.ARTIFICIAL_PERSON,d.TRANSACTOR_NAME from aa002 a,ba002 b,aa001 c,ba001 d "
              + "where a.emp_info_id=b.id and a.org_id=c.id and c.orginfo_id=d.id and c.id=? and d.name=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, Integer.valueOf(orgid));
          query.setParameter(1, orgname);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return temp;
  }

  /**
   * 查询归集单位缴存比例
   * 
   * @return
   */
  public String findOrgRate(final String orgId) {
    String orgRate = "";
    try {
      orgRate = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select a.org_rate from aa001 a  where  a.id=? ";

              session.clear();
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, new Integer(orgId));
              String orgRate = "";
              if (query.uniqueResult() != null)
                orgRate = query.uniqueResult().toString();
              return orgRate;

            }
          });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return orgRate;
  }

  /**
   * 查询归集职工缴存比例
   * 
   * @return
   */
  public String findEmpRate(final String orgId) {
    String orgRate = "";
    try {
      orgRate = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select a.emp_rate from aa001 a  where  a.id=? ";

              session.clear();
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, new Integer(orgId));
              String orgRate = "";
              if (query.uniqueResult() != null)
                orgRate = query.uniqueResult().toString();
              return orgRate;

            }
          });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return orgRate;
  }

  /**
   * 查询归集银行
   * 
   * @return
   */
  public CollBank getCollBankByCollBankid(final String collBankid) {
    CollBank collBank = null;
    collBank = (CollBank) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            String sql = " from CollBank collBank where collBank.status=1 and collBank.collBankId = ? ";
            Query query = session.createQuery(sql);
            query.setParameter(0, new Integer(collBankid));

            return query.uniqueResult();
          }
        });
    return collBank;
  }

  public Emp queryByCriterionsByEmpPayStatus(final String empid,
      final String orgid, final String[] payStatus) {

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
        if (payStatus != null && payStatus.length > 0) {
          criterion += "( ";
          for (int i = 0; i < payStatus.length; i++) {
            criterion += "emp.payStatus = ? or ";
            parameters.add(new BigDecimal(payStatus[i]));
          }
          criterion = criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") and";
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

  public List getEmpListWuhtPayStatus(final String orgid) {

    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "from Emp emp  ";

          Vector parameters = new Vector();
          String criterion = "";

          if (orgid != null && !orgid.equals("")) {
            criterion += "emp.org.id = ? and ";
            parameters.add(new Integer(orgid));
          }

          if (criterion.length() != 0)
            criterion = "where emp.payStatus in (1,3,4) and   "
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

  public List get_pl_status(final String card_num, final String card_num_two,
      final String emp_name) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Query query = session
              .createSQLQuery("select distinct t.type from pl210 t where t.status=0 and (t.card_num=? or t.card_num=?) and t.emp_name=? ");
          query.setString(0, card_num).setString(1, card_num_two).setString(2,
              emp_name);
          return query.list();
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public List get_pl_status_yg(final String card_num,
      final String card_num_two, final String emp_name) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List list1 = new ArrayList();
          Query query = session
              .createSQLQuery("select distinct t.borrower_name from pl110 t where (t.card_num=? or t.card_num=?) and t.borrower_name=? ");
          query.setString(0, card_num).setString(1, card_num_two).setString(2,
              emp_name);
          if (query.uniqueResult() != null) {
            list1.add("1");
          }
          Query query1 = session
              .createSQLQuery("select distinct t.name from pl113 t where (t.card_num=? or t.card_num=?) and t.name=? ");
          query1.setString(0, card_num).setString(1, card_num_two).setString(2,
              emp_name);
          if (query1.uniqueResult() != null) {
            list1.add("2");
          }
          return list1;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public List get_pl_status_yg_wuht(final String card_num,
      final String card_num_two, final String emp_name) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List list1 = new ArrayList();
          Query query = session
              .createSQLQuery("select distinct t.borrower_name from pl110 t,pl111 pl111 where pl111.contract_id=t.contract_id and pl111.contract_st <> '12' and pl111.contract_st <> '13' and (t.card_num=? or t.card_num=?) ");
          query.setString(0, card_num).setString(1, card_num_two);
          if (query.uniqueResult() != null) {
            list1.add("1");
          }
          Query query1 = session
              .createSQLQuery("select distinct t.name from pl113 t,pl111 pl111 where pl111.contract_id=t.contract_id and pl111.contract_st <> '12' and pl111.contract_st <> '13' and  (t.card_num=? or t.card_num=?) ");
          query1.setString(0, card_num).setString(1, card_num_two);
          if (query1.uniqueResult() != null) {
            list1.add("2");
          }
          return list1;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  /**
   * 查询职工是否作为借款人或者辅助借款人存在并且合同状态为还款中11的人的数量
   * 
   * @param empName
   * @param cardNum
   * @return
   */
  public String queryempLoanIsEleven(final String empName, final String cardNum) {
    String param_value = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = " select count(*)"
                + " from pl110 a, pl111 b, pl113 c"
                + " where a.contract_id(+) = b.contract_id "
                + " and b.contract_id = c.contract_id(+)"
                + " and b.contract_st = '11'"
                + " and ((a.borrower_name = ? and (a.card_num = ? or a.card_num = ?))"
                + " or (c.name = ? and (a.card_num = ? or a.card_num = ?)))";
            String tempCardNum = "";
            if (cardNum.length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNum);
            if (cardNum.length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNum);
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, empName);
            query.setParameter(1, cardNum);
            query.setParameter(2, tempCardNum);
            query.setParameter(3, empName);
            query.setParameter(4, cardNum);
            query.setParameter(5, tempCardNum);
            return query.uniqueResult().toString();
          }
        });
    return param_value;
  }

  /*
   * 单位开户-开户完成时把aa002.pay_oldyear更新
   */
  public void updatePayOldYearOfAA002(final String orgId) throws Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = " update aa002 set aa002.pay_oldyear = aa002.org_pay+aa002.emp_pay where aa002.org_id = '"
          + orgId + "'";
      st.executeUpdate(sql);
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int queryEmpCount(final String empName, final String cardNum) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = " select count(t.id)" + " from ba002 t"
                + " where t.name = ?"
                + " and (t.card_num = ? or t.card_num = ?)";
            Query query = session.createSQLQuery(sql);
            String tempCardNum = "";
            if (cardNum.length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNum);
            if (cardNum.length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNum);
            query.setParameter(0, empName);
            query.setParameter(1, cardNum);
            query.setParameter(2, tempCardNum);
            return Integer.valueOf(query.uniqueResult().toString());
          }
        });
    return count.intValue();
  }

  public int queryEmpCount_yg(final String empName, final String cardNum) {
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = " select count(t.id)" + " from ba002 t,aa002 a"
                + " where t.name = ?"
                + " and (t.card_num = ? or t.card_num = ?)"
                + " and t.id=a.emp_info_id and a.pay_status='1' ";
            Query query = session.createSQLQuery(sql);
            String tempCardNum = "";
            if (cardNum.length() == 15)
              tempCardNum = CardMunChange.get18Id(cardNum);
            if (cardNum.length() == 18)
              tempCardNum = CardMunChange.get15Id(cardNum);
            query.setParameter(0, empName);
            query.setParameter(1, cardNum);
            query.setParameter(2, tempCardNum);
            return Integer.valueOf(query.uniqueResult().toString());
          }
        });
    return count.intValue();
  }

  /**
   * 
   */
  public List getEmpBaseExportList(final String orgid) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select emp0_.ID,"
              + " empinfo1_.NAME, empinfo1_.CARD_NUM,"
              + " emp0_.SALARY_BASE, emp0_.ORG_PAY, emp0_.EMP_PAY,"
              + " emp0_.PAY_STATUS, emp0_.ORG_PAY_MONTH,"
              + " emp0_.EMP_PAY_MONTH,"
              + " emp0_.pre_Balance + emp0_.cur_Balance as balance"
              + " from AA002 emp0_, BA002 empinfo1_"
              + " where emp0_.EMP_INFO_ID = empinfo1_.ID"
              + " and emp0_.ORG_ID = ? order by to_number(emp0_.ID)";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(orgid));
          return query.list();
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public Object[] queryTotalInfoByCriterions(final EmployeeInfoSearchDTO dto,
      final SecurityInfo securityInfo) {
    Object[] obj = null;
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(a2.id)," + " nvl(sum(a2.org_pay),0),"
              + " nvl(sum(a2.emp_pay),0)," + " nvl(sum(a2.salary_base),0),"
              + " nvl(sum(a2.org_pay) + sum(a2.emp_pay),0)"
              + " from aa001 a1, aa002 a2, ba001 b1, ba002 b2"
              + " where a1.orginfo_id = b1.id" + " and a2.org_id = a1.id"
              + " and a2.emp_info_id = b2.id" + " and a1.id "
              + securityInfo.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (dto.getOrgId() != null && !dto.getOrgId().trim().equals("")) {
            criterion += "a1.id = ?  and ";
            parameters.add(new Integer(dto.getOrgId().trim()));
          }
          if (dto.getOrgName() != null && !dto.getOrgName().trim().equals("")) {
            criterion += "b1.name like ?  and ";
            parameters.add("%" + dto.getOrgName().trim() + "%");
          }
          if (dto.getEmpId() != null && !dto.getEmpId().equals("")) {
            criterion += "a2.id = ? and ";
            parameters.add(new Integer(dto.getEmpId().trim()));
          }
          if (dto.getEmpName() != null && !dto.getEmpName().equals("")) {
            criterion += "b2.name like ? and ";
            parameters.add("%" + dto.getEmpName() + "%");
          }
          if (dto.getIdentityCard() != null
              && !dto.getIdentityCard().trim().equals("")) {
            String tempCardNum = "";
            if (dto.getIdentityCard().trim().length() == 15) {
              tempCardNum = CardMunChange.get18Id(dto.getIdentityCard().trim());
              criterion += "(b2.card_num = ? or b2.card_num = ?) and ";
              parameters.add(dto.getIdentityCard().trim());
              parameters.add(tempCardNum);
            } else if (dto.getIdentityCard().trim().length() == 18) {
              tempCardNum = CardMunChange.get15Id(dto.getIdentityCard().trim());
              criterion += "(b2.card_num = ? or b2.card_num = ?) and ";
              parameters.add(dto.getIdentityCard().trim());
              parameters.add(tempCardNum);
            } else {
              criterion += "b2.card_num like ?  and ";
              parameters.add("%" + dto.getIdentityCard().trim() + "%");
            }
          }
          if (dto.getSex() != null && !dto.getSex().equals("")) {
            criterion += "b2.sex= ?  and ";
            parameters.add(new BigDecimal(dto.getSex().trim()));
          }
          if (dto.getPayState() != null && !dto.getPayState().equals("")) {
            criterion += "a2.pay_status = ?  and ";
            parameters.add(new BigDecimal(dto.getPayState().trim()));
          }
          if (dto.getEmpBalance() != null && !dto.getEmpBalance().equals("")) {
            criterion += "(a2.pre_balance+a2.cur_balance) > 0  and ";
          }
          if (dto.getSalaryStart() != null
              && !dto.getSalaryStart().trim().equals("")
              && dto.getSalaryEnd() != null
              && !dto.getSalaryEnd().trim().equals("")) {
            criterion += " a2.salary_base between ? and ? and";
            parameters.add(new BigDecimal(dto.getSalaryStart().trim()));
            parameters.add(new BigDecimal(dto.getSalaryEnd().trim()));
          }
          if (dto.getOrgPayStart() != null
              && !dto.getOrgPayStart().trim().equals("")
              && dto.getOrgPayEnd() != null
              && !dto.getOrgPayEnd().trim().equals("")) {
            criterion += " a2.org_pay between ? and ? and";
            parameters.add(new BigDecimal(dto.getOrgPayStart().trim()));
            parameters.add(new BigDecimal(dto.getOrgPayEnd().trim()));
          }
          if (dto.getEmpPayStart() != null
              && !dto.getEmpPayStart().trim().equals("")
              && dto.getEmpPayEnd() != null
              && !dto.getEmpPayEnd().trim().equals("")) {

            criterion += " a2.emp_pay between ? and ? and";
            parameters.add(new BigDecimal(dto.getEmpPayStart().trim()));
            parameters.add(new BigDecimal(dto.getEmpPayEnd().trim()));
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql += criterion;
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();

    }
    return obj;
  }

  public List get_pl_other_status(final String card_num,
      final String card_num_two, final String emp_name) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Query query = session.createSQLQuery("select  t.borrowername from "
              + "othersloan t " + "where  (t.borrowercardnum = '" + card_num
              + "' or t.borrowercardnum = '" + card_num_two + "' "
              + "or t.assiscardnum='" + card_num + "' or t.assiscardnum='"
              + card_num_two + "') ");
          return query.list();
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public LoanConditionsParamSetDTO querySyscollectionMonth(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=?  and p.param_value is null and p.param_num='2' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }

              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }

  /**
   * hanl 公积金开户时间大于__ 月(影响是否能贷)
   */
  public LoanConditionsParamSetDTO querySyscollectionOpenAccMonth(
      final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='3' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }

              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }

  /**
   * hanl 公积金欠缴月数要小于"?"月(影响是否能贷)
   */
  public LoanConditionsParamSetDTO querySyscollectionOpenAccMonth_wsh(
      final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_value='1' and p.param_num='2' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }

              }
              return loanConditionsParamSetDTO;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanConditionsParamSetDTO;
  }

  public List getPl110byCardNumwuht(final String card_num) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select pl110.*   from pl110 pl110, pl111 pl111"
            + "   where pl110.contract_id = pl111.contract_id and pl111.contract_st = '11'"
            + " and   ";
        Vector parameters = new Vector();
        String criterion = "";

        if (card_num != null && !card_num.equals("")) {
          criterion += " pl110.card_num=? and ";
          parameters.add(card_num);
        }
        if (criterion.length() != 0)
          criterion = criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
      }
    });
    return list;
  }

  public void insertIntoAA306_1(final String reason) throws Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = " insert into aa306_1 t values(1,'" + reason + "')";
      st.executeUpdate(sql);
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteAA306_1() throws Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = " delete aa306_1";
      st.executeUpdate(sql);
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getAA306_1() {
    String str = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select t.reason from aa306_1 t";
            Query query = session.createSQLQuery(sql);
            Object obj = query.uniqueResult();
            return obj == null ? "" : obj.toString();
          }
        });
    return str;
  }
}
