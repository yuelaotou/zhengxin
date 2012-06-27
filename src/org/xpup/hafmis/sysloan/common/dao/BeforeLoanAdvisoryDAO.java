package org.xpup.hafmis.sysloan.common.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.dto.EmployeeInfoDisplayDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.dto.EmployeeInfoSearchDTO;
import org.xpup.hafmis.sysloan.common.loanconditionsset.LoanConditionsParamSetDTO;

public class BeforeLoanAdvisoryDAO extends HibernateDaoSupport {
  
  /*
   * 根据条件查询职工的信息
   */
  
  public List getEmployeeInfoList_wxg(final String empid,final String empname,final String empCardnum,final String orgid,
      final String orgname, final SecurityInfo info) {
    try {
      List list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select b2.name as empName,a2.id as empId,b2.card_num as empCardnum,b1.name as orgName, "+
          " a1.id as orgId,a2.salary_base as empSalaryBase,a2.emp_pay+a2.org_pay as pay,"+
          " a2.pre_balance+a2.cur_balance as empBalance,  a2.pay_status as payStatus,b2.birthday,b1.officecode  as officecode ,b2.sex"+
          " from aa001 a1,aa002 a2,ba001 b1,ba002 b2 where a1.orginfo_id=b1.id and a2.org_id=a1.id and a2.emp_info_id=b2.id and " +
           " a1.id "  + info.getGjjSecuritySQL() + " and ";
          List parameters = new ArrayList();
          String criterion = "";
          if (empid != null && !empid.trim().equals("")) {

            criterion += " a2.id = ?  and ";
            parameters.add(new Integer(empid.trim()));
          }
          if (empname != null && !empname.trim().equals("")) {

            criterion += " b2.name  =?  and ";
            parameters.add(empname);
          }
          if (empCardnum != null && !empCardnum.trim().equals("")) {
            criterion += " b2.card_num = ?  and ";
            parameters.add(empCardnum);
          }
          if (orgid != null && !orgid.trim().equals("")) {

            criterion += " a1.id=?  and ";
            parameters.add(orgid);
          }
          if (orgname != null && !orgname.trim().equals("")) {

            criterion += " b1.name=?  and ";
            parameters.add(orgname);
          }
          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          }
          hql = hql + criterion;
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
       
          return  query.list();
          
        }
      });
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }
  
  
  /**
   * hanl
   * 连续汇缴月数
   */
  public int queryMonthCounts_wxg(final String empId,final String orgId) {
    List list = null;
    int count=0;
    try {
      list = (List) getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select monthPaymentTail.monthPaymentHead.payMonth from MonthPaymentTail monthPaymentTail " +
                  "where monthPaymentTail.empId = ? and monthPaymentTail.monthPaymentHead.paymentHead.org.id = ? " +
                  "and monthPaymentTail.monthPaymentHead.paymentHead.payType_ = 'A' order by monthPaymentTail.monthPaymentHead.payMonth asc";

              Query query = session.createQuery(hql);
              query.setParameter(0, new Integer(empId));
              query.setParameter(1, new Integer(orgId));
              return query.list();
            }

          });
      Object obj1=null;
      Object obj2=null;
      if(!list.isEmpty()){
        for(int i=0;i<list.size();i++){
        
            obj1=(Object)list.get(i);
            if(i+1==list.size()){
              break;
            }
            obj2=(Object)list.get(i+1);
            if(Integer.parseInt(obj2.toString())-Integer.parseInt(obj1.toString())==1){
              count += 1;              
            }else{
              count = 0;
            }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
 /*
  * 贷款金额不能超过商品房价的多少
  */
  public LoanConditionsParamSetDTO queryHouseprice(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='8' and p.is_use='1'";

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

 /*
  *  贷款金额不能超过二手房价的多少
  */
  
  public List queryHandHouseprice(final String office) {
    List list = null;
    try {
        list = (List) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='9' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              Iterator iterate = query.list().iterator();
              List templist=new ArrayList();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
                templist.add(loanConditionsParamSetDTO);
              }
              return templist;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /*
   * 商品房金额最高不超过
   */
  
  public LoanConditionsParamSetDTO queryMaxHouseprice(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='10' and p.is_use='1'";

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
  
  /*
   * 二手房金额最高不超过
   */
  
  public LoanConditionsParamSetDTO queryMaxHandHouseprice(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='11' and p.is_use='1'";

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
  
  /*
   * 贷款人实际年龄加贷款期限不得超过多少
   */
  
  public List queryEmpLoanAge(final String office) {
    List list = null;
    try {
        list = (List) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='4' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
             
              Iterator iterate = query.list().iterator();
              List templist=new ArrayList();
              Object[] obj = null;
              while (iterate.hasNext()) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
                obj = (Object[]) iterate.next();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
                templist.add(loanConditionsParamSetDTO);
              }
              return templist;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
/*
 * 贷款最高年限：1商品房2二手房
 */  
  public List queryEmpMaxLoanAge(final String office) {
    List list = null;
    try {
        list = (List) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='13' and p.is_use='1'";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, office);
              Iterator iterate = query.list().iterator();
              List templist=new ArrayList();
              Object[] obj = null;
              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
                if (obj[0] != null) {
                  loanConditionsParamSetDTO.setParamValue(obj[0].toString());
                }
                if (obj[1] != null) {
                  loanConditionsParamSetDTO.setParamExplain(obj[1].toString());
                }
                templist.add(loanConditionsParamSetDTO);
              }
              return templist;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  
  /*
   * 借款人月收入还款比例
   */
  
  public LoanConditionsParamSetDTO queryRepaymentRate(final String office) {
    LoanConditionsParamSetDTO loanConditionsParamSetDTO = null;
    try {
      loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p.param_value,p.param_explain from pl008 p where p.office=? and p.param_num='14' and p.is_use='1'";

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
   * 得到两个日期之间间隔的天数
   * @param startDate
   * @param endDate
   * @return
   */
  public String getDaysInteval(final String startDate, final String endDate) {
    String days = "";
    try {
      days = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select to_date(?, 'yyyy-mm-dd') - to_date(?, 'yyyy-mm-dd') from dual";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, startDate);
          query.setParameter(1, endDate);
          return query.uniqueResult() == null ? "0" : query.uniqueResult()
              .toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return days;
  }
}
