package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Assurer;
import org.xpup.hafmis.sysloan.common.domain.entity.ImpawnContract;
import org.xpup.hafmis.sysloan.common.domain.entity.OthersLoan;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;

public class OthersLoanDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OthersLoan queryById(Serializable id) {  
    Validate.notNull(id);
    return  (OthersLoan) getHibernateTemplate().get(OthersLoan.class,id);    
  }

  /**
   * 插入记录
   * @param Assurer
   * @return
   */
  public Serializable insert(OthersLoan othersLoan){
    Serializable id = null;
    try{    
    Validate.notNull(othersLoan);
    id = getHibernateTemplate().save(othersLoan);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  //更新实体
  public void updateOthersLoan(OthersLoan othersLoan){
    try {
      Validate.notNull(othersLoan);
      getHibernateTemplate().update(othersLoan);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
   

  }
  /**
   * 根据主键删除
   * yuqf
   * @param id
   * @return
   */
  public void deleteById(String id) {
    try {
      Validate.notNull(id);
      // getHibernateTemplate().clear();
      OthersLoan othersLoan = queryById(new Integer(id));

      getHibernateTemplate().delete(othersLoan);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  //查询维护页面的列表记录
  public List queryBorrowerListByCriterions(final String name,
      final String cardNum, final String orgId, final String empId,
      final String orgName, final String office, final String city,
      final String beginBizDate, final String endBizDate, final int start,
      final String orderBy, final String order, final int pageSize,
      final int page, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select * from " +
              "(select t.borrowername c," +
              "t.id d," +
              " t.borrwerempid e," +
              " t.borrowerorgname f ," +
              " t.borrowerorgid g , " +
              " t.borrowercardnum h," +
              " t.houseaddr i," +
              " t.housetotalprice j," +
              " t.housearea k," +
              " t.loanmoney l," +
              " t.loantime n," +
              " t.loancity,'借款人',t.office p,t.op_time q from othersloan t " +
             ") a ";  
          Vector parameters = new Vector();
          String criterion = "";
          List tempList = new ArrayList();
          if (name != null && !"".equals(name)) {
            criterion += " a.c = ? and ";
            parameters.add(name);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " a.h = ? and ";
            parameters.add(cardNum);
          }

          if (orgId != null && !"".equals(orgId)) {
            criterion += " a.g = ? and ";
            parameters.add(orgId);
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " a.e = ? and ";
            parameters.add(empId);
          }
          
          if (orgName != null && !"".equals(orgName)) {
            criterion += " a.f = ? and ";
            parameters.add(orgName);
          }

          if (office != null && !"".equals(office)) {
            criterion += " a.p = ? and ";
            parameters.add(office);
          }

          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " to_char(a.q,'yyyymmdd') >= ? and ";
            parameters.add(beginBizDate);
          }

          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " to_char(a.q,'yyyymmdd') <= ? and ";
            parameters.add(endBizDate);
          }


          if (criterion.length() != 0)
            criterion = " where 1=1 and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " a.d ";

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
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList = query.list();
          }
          
          if(query.list().size() != 0){
            Iterator iterate = query.list().iterator();
            Object[] obj = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            OthersLoan othersLoan = new OthersLoan();
            if(obj[0] != null){
              othersLoan.setBorrowerName(obj[0].toString());//姓名
            }
            if(obj[1] != null){
              othersLoan.setId(new Integer(obj[1].toString()));//othersloan主键
            }
            if(obj[2] != null){
              othersLoan.setBorrowerEmpId(obj[2].toString());//职工编号
            }
            if(obj[3] != null){
              othersLoan.setBORROWERORGNAME(obj[3].toString());//单位名称
            }
            if(obj[4] != null){
              othersLoan.setBORROWERORGID(obj[4].toString());//单位编号
            }
            if(obj[5] != null){
              othersLoan.setBORROWERCARDNUM(obj[5].toString());//身份证号
            }
            if(obj[6] != null){
              othersLoan.setHOUSEADDR(obj[6].toString());//房屋坐落
            }
            if(obj[7] != null){
              othersLoan.setHOUSETOTALPRICE(obj[7].toString());//房屋总价
            }
            if(obj[8] != null){
              othersLoan.setHOUSEAREA(obj[8].toString());//房屋面积
            }
            if(obj[9] != null){
              othersLoan.setLOANMONEY(obj[9].toString());//贷款金额
            }
            if(obj[10] != null){
              othersLoan.setLOANTIME(obj[10].toString());//贷款年限
            }
            if(obj[11] != null){
              othersLoan.setLOANCITY(obj[11].toString());//贷款城市
            }
            if(obj[12] != null){
              othersLoan.setBORROWERADDR(obj[12].toString());//借款人类型
            }
            tempList.add(othersLoan);
           }
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  public List queryBorrowerAllListByCriterions(final String name,
      final String cardNum, final String orgId, final String empId,
      final String orgName, final String office, final String city,
      final String beginBizDate, final String endBizDate, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select * from " +
              "(select t.borrowername c," +
              "t.id d," +
              " t.borrwerempid e," +
              " t.borrowerorgname f ," +
              " t.borrowerorgid g , " +
              " t.borrowercardnum h," +
              " t.houseaddr i," +
              " t.housetotalprice j," +
              " t.housearea k," +
              " t.loanmoney l," +
              " t.loantime n," +
              " t.loancity,'借款人',t.office p,t.op_time q from othersloan t " +
              " union all " +
              " select m.assisname c, " +
              " m.id d," +
              " m.assisempid e," +
              " m.assisorgname f," +
              " m.assisorgid g," +
              " m.assiscardnum h," +
              " m.houseaddr i," +
              " m.housetotalprice j," +
              " m.housearea k," +
              " m.loanmoney l," +
              " m.loantime n," +
              " m.loancity,'共同借款人',m.office p,m.op_time q from othersloan m " +
              "where m.assisname is not null) a ";  
          Vector parameters = new Vector();
          String criterion = "";
          List tempList = new ArrayList();
          if (name != null && !"".equals(name)) {
            criterion += " a.c = ? and ";
            parameters.add(name);
          }

          if (cardNum != null && !"".equals(cardNum)) {
            criterion += " a.h = ? and ";
            parameters.add(cardNum);
          }

          if (orgId != null && !"".equals(orgId)) {
            criterion += " a.g = ? and ";
            parameters.add(orgId);
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " a.e = ? and ";
            parameters.add(empId);
          }
          
          if (orgName != null && !"".equals(orgName)) {
            criterion += " a.f = ? and ";
            parameters.add(orgName);
          }

          if (office != null && !"".equals(office)) {
            criterion += " a.p = ? and ";
            parameters.add(office);
          }

          if (beginBizDate != null && !"".equals(beginBizDate)) {
            criterion += " to_char(a.q,'yyyymmdd') >= ? and ";
            parameters.add(beginBizDate);
          }

          if (endBizDate != null && !"".equals(endBizDate)) {
            criterion += " to_char(a.q,'yyyymmdd') <= ? and ";
            parameters.add(endBizDate);
          }


          if (criterion.length() != 0)
            criterion = " where 1=1 and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by a.d desc ";

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          if(query.list().size() != 0){
            Iterator iterate = query.list().iterator();
            Object[] obj = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            OthersLoan othersLoan = new OthersLoan();
            if(obj[0] != null){
              othersLoan.setBorrowerName(obj[0].toString());
            }
            if(obj[1] != null){
              othersLoan.setId(new Integer(obj[1].toString()));//othersloan主键
            }
            if(obj[2] != null){
              othersLoan.setBorrowerEmpId(obj[2].toString());//职工编号
            }
            if(obj[3] != null){
              othersLoan.setBORROWERORGNAME(obj[3].toString());//单位名称
            }
            if(obj[4] != null){
              othersLoan.setBORROWERORGID(obj[4].toString());//单位编号
            }
            if(obj[5] != null){
              othersLoan.setBORROWERCARDNUM(obj[5].toString());//身份证号
            }
            if(obj[6] != null){
              othersLoan.setHOUSEADDR(obj[6].toString());//房屋坐落
            }
            if(obj[7] != null){
              othersLoan.setHOUSETOTALPRICE(obj[7].toString());//房屋总价
            }
            if(obj[8] != null){
              othersLoan.setHOUSEAREA(obj[8].toString());//房屋面积
            }
            if(obj[9] != null){
              othersLoan.setLOANMONEY(obj[9].toString());//贷款金额
            }
            if(obj[10] != null){
              othersLoan.setLOANTIME(obj[10].toString());//贷款年限
            }
            if(obj[11] != null){
              othersLoan.setLOANCITY(obj[11].toString());//贷款城市
            }
            if(obj[12] != null){
              othersLoan.setBORROWERADDR(obj[12].toString());//借款人类型
            }
            tempList.add(othersLoan);
           }
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
   /**
    * yuqf
    * 根据ID查询empId
    * @param id
    * @return
    * 2007-10-02
    */
   public String queryEmpId(final String id){
     String empId = "";
       empId = (String)getHibernateTemplate().execute(
         new HibernateCallback() {
           public Object doInHibernate(Session session)
               throws HibernateException, SQLException {
             String hql = "select t.emp_id from pl123 t where t.id=?";
             Query query = session.createSQLQuery(hql);
             query.setParameter(0, id);
            
               return query.uniqueResult().toString();
            
           }
         });

     return empId;
   }
   /**
    * 根据 合同编号，职工姓名，职工身份证编号 判断PL123中该合同编号下是否存在该职工
    * @param contractId
    * @param empName
    * @param cardNum
    * @return
    */
   public String queryIdYU(final String contractId,final String empName,final String cardNum){
     String pkId = null;
     pkId = (String)getHibernateTemplate().execute(
         new HibernateCallback() {
           public Object doInHibernate(Session session)
               throws HibernateException, SQLException {
             String hql = " select t.id from pl123 t where t.contract_id=? and t.emp_name=? and t.card_num=?";
             Query query = session.createSQLQuery(hql);
             query.setParameter(0, contractId);
             query.setParameter(1, empName);
             query.setParameter(2, cardNum);
             if(query.uniqueResult()!= null){
             return query.uniqueResult().toString();
             }else{
               return null;
             }
           }
         });

     return pkId;
   }
   /**
    * 判断同一合同下是否已经存在该担保人
    * @param contractId
    * @param empName
    * @param cardNum
    * @return
    * @author 付云峰
    */
   public Assurer queryCautionerInfo(final String contractId,final String empName,final String cardNum){
     Assurer assurer = null;
     assurer = (Assurer) getHibernateTemplate().execute(
         new HibernateCallback() {
           public Object doInHibernate(Session session)
               throws HibernateException, SQLException {
             String hql = " from Assurer a where a.contractId=? and a.empName=? and a.cardNum=? and a.cardKind=0";
             Query query = session.createQuery(hql);
             query.setParameter(0, contractId);
             query.setParameter(1, empName);
             query.setParameter(2, cardNum);
             if(query.uniqueResult()!= null){
             return query.uniqueResult();
             }else{
               return null;
             }
           }
         });

     return assurer;
   }
   
   public List queryAssurerInfo(final String contractId){
     List list=new ArrayList();
     list = (List) getHibernateTemplate().executeFind(
         new HibernateCallback() {
           public Object doInHibernate(Session session)
               throws HibernateException, SQLException {
             String hql = " from Assurer a where a.contractId=? and a.status=0";
             Query query = session.createQuery(hql);
             query.setParameter(0, contractId);
             return query.list();
           }
         });

     return list;
   }
// 查询维护页面的列表记录
   public List queryBorrowerTcListByCriterions(final String name,
       final String cardNum, final String orgId, final String empId,
       final String orgName, final String office, final String city,
       final String beginBizDate, final String endBizDate, final int start,
       final String orderBy, final String order, final int pageSize,
       final int page, final SecurityInfo securityInfo) {
     List list = new ArrayList();
     try {
       list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

         public Object doInHibernate(Session session) throws HibernateException,
             SQLException {

           String hql = "select a.borrower_name," + "a.org_name,"
              + "a.card_num," + "a.month_salary," + "a.month_pay,"
              + "a.acc_blnce," + "a.op_time," + " case "
              + " when c.house_type = '01' then " + "  c.house_addr "
              + " else " + "  c.bargainor_house_addr " + "end, " + " case "
              + " when c.house_type = '01' then " + " c.house_totle_price "
              + " else " + " c.bargainor_totle_price " + " end, " + " case "
              + " when c.house_type = '01' then " + "  to_char(c.house_area) "
              + " else " + "  to_char(c.bargainor_house_area) " + " end, "
              + "d.loan_money, " + " d.loan_time_limit ,a.contract_id ,a.fundcity "
              + "from pl110 a, pl111 b, pl114 c, pl115 d "
              + "where a.contract_id = b.contract_id "
              + " and b.contract_id = c.contract_id "
              + " and c.contract_id = d.contract_id " + " and a.loantype='1'";  
           Vector parameters = new Vector();
           String criterion = "";
           List tempList = new ArrayList();
           if (name != null && !"".equals(name)) {
             criterion += " a.borrower_name = ? and ";
             parameters.add(name);
           }

           if (cardNum != null && !"".equals(cardNum)) {
             criterion += " a.card_num = ? and ";
             parameters.add(cardNum);
           }

//           if (orgId != null && !"".equals(orgId)) {
//             criterion += " a.g = ? and ";
//             parameters.add(orgId);
//           }

//           if (empId != null && !"".equals(empId)) {
//             criterion += " a.e = ? and ";
//             parameters.add(empId);
//           }
           
           if (orgName != null && !"".equals(orgName)) {
             criterion += " a.org_name = ? and ";
             parameters.add(orgName);
           }

           if (office != null && !"".equals(office)) {
             criterion += " a.office = ? and ";
             parameters.add(office);
           }

           if (beginBizDate != null && !"".equals(beginBizDate)) {
             criterion += " to_char(a.op_time,'yyyymmdd') >= ? and ";
             parameters.add(beginBizDate);
           }

           if (endBizDate != null && !"".equals(endBizDate)) {
             criterion += " to_char(a.op_time,'yyyymmdd') <= ? and ";
             parameters.add(endBizDate);
           }


           if (criterion.length() != 0)
             criterion = " and "
                 + criterion.substring(0, criterion.lastIndexOf("and"));

           String ob = orderBy;
           if (ob == null)
             ob = " a.contract_id ";

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
           System.out.print("dfghfd");
           Iterator iterate =null;
           try{
             iterate = query.list().iterator();
           }
           catch(Exception e){
             e.printStackTrace();
           }
            
            Object[] obj = null;
            while (iterate.hasNext()) {
              obj = (Object[]) iterate.next();
              OthersLoan othersLoan = new OthersLoan();
              if (obj[0] != null) {
                othersLoan.setBorrowerName(obj[0].toString());// 姓名
              }
              if (obj[1] != null) {
                othersLoan.setBORROWERORGNAME(obj[1].toString());// 单位名称
              }
              if (obj[2] != null) {
                othersLoan.setBORROWERCARDNUM(obj[2].toString());// othersloan主键
              }
              if (obj[3] != null) {
                othersLoan.setBORROWERSALARYBASE(obj[3].toString());// 职工编号
              }

              if (obj[4] != null) {
                othersLoan.setBORROWERMONTHPAY(obj[4].toString());// 单位编号
              }
              if (obj[5] != null) {
                othersLoan.setBORROWERBALANCE(obj[5].toString());// 身份证号
              }
              if (obj[6] != null) {
                othersLoan.setASSISAGE(obj[6].toString());// 贷款时间
              }
              if (obj[7] != null) {
                othersLoan.setHOUSEADDR(obj[7].toString());// 房屋总价
              }
              if (obj[8] != null) {
                othersLoan.setHOUSETOTALPRICE(obj[8].toString());// 房屋总价
              }
              if (obj[9] != null) {
                othersLoan.setHOUSEAREA(obj[9].toString());// 房屋面积
              }
              if (obj[10] != null) {
                othersLoan.setLOANMONEY(obj[10].toString());// 贷款金额
              }
              if (obj[11] != null) {
                othersLoan.setLOANTIME(obj[11].toString());// 贷款年限
              }
              if (obj[12] != null) {
                othersLoan.setHOUSETYPE(obj[12].toString());// 贷款年限
              }
              if (obj[13] != null) {
                othersLoan.setBORROWERADDR(obj[13].toString());// 贷款年限
              }
              tempList.add(othersLoan);
            }
           return tempList;
         }
       });
     } catch (Exception e) {
       e.printStackTrace();
     }
     return list;
   }
// 查询维护页面的列表记录
   public List queryBorrowerTcAllListByCriterions(final String name,
       final String cardNum, final String orgId, final String empId,
       final String orgName, final String office, final String city,
       final String beginBizDate, final String endBizDate, final SecurityInfo securityInfo) {
     List list = new ArrayList();
     try {
       list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

         public Object doInHibernate(Session session) throws HibernateException,
             SQLException {

           String hql = "select a.borrower_name," + "a.org_name,"
              + "a.card_num," + "a.month_salary," + "a.month_pay,"
              + "a.acc_blnce," + "a.op_time," + " case "
              + " when c.house_type = '01' then " + "  c.house_addr "
              + " else " + "  c.bargainor_house_addr " + "end, " + " case "
              + " when c.house_type = '01' then " + " c.house_totle_price "
              + " else " + " c.bargainor_totle_price " + " end, " + " case "
              + " when c.house_type = '01' then " + "  to_char(c.house_area) "
              + " else " + "  to_char(c.bargainor_house_area) " + " end, "
              + "d.loan_money, " + " d.loan_time_limit ,a.contract_id,a.fundcity "
              + "from pl110 a, pl111 b, pl114 c, pl115 d "
              + "where a.contract_id = b.contract_id "
              + " and b.contract_id = c.contract_id "
              + " and c.contract_id = d.contract_id " + " and a.loantype='1'";  
           Vector parameters = new Vector();
           String criterion = "";
           List tempList = new ArrayList();
           if (name != null && !"".equals(name)) {
             criterion += " a.borrower_name = ? and ";
             parameters.add(name);
           }

           if (cardNum != null && !"".equals(cardNum)) {
             criterion += " a.card_num = ? and ";
             parameters.add(cardNum);
           }

//           if (orgId != null && !"".equals(orgId)) {
//             criterion += " a.g = ? and ";
//             parameters.add(orgId);
//           }

//           if (empId != null && !"".equals(empId)) {
//             criterion += " a.e = ? and ";
//             parameters.add(empId);
//           }
           
           if (orgName != null && !"".equals(orgName)) {
             criterion += " a.org_name = ? and ";
             parameters.add(orgName);
           }

           if (office != null && !"".equals(office)) {
             criterion += " a.office = ? and ";
             parameters.add(office);
           }

           if (beginBizDate != null && !"".equals(beginBizDate)) {
             criterion += " to_char(a.op_time,'yyyymmdd') >= ? and ";
             parameters.add(beginBizDate);
           }

           if (endBizDate != null && !"".equals(endBizDate)) {
             criterion += " to_char(a.op_time,'yyyymmdd') <= ? and ";
             parameters.add(endBizDate);
           }


           if (criterion.length() != 0)
             criterion = " and "
                 + criterion.substring(0, criterion.lastIndexOf("and"));

           hql = hql + criterion + " order by a.contract_id desc" ;

           Query query = session.createSQLQuery(hql);
           for (int i = 0; i < parameters.size(); i++) {
             query.setParameter(i, parameters.get(i));
           }
           
           if(query.list().size() != 0){
             Iterator iterate = query.list().iterator();
             Object[] obj = null;
           while (iterate.hasNext()) {
             obj = (Object[]) iterate.next();
             OthersLoan othersLoan = new OthersLoan();
             if(obj[0] != null){
               othersLoan.setBorrowerName(obj[0].toString());//姓名
             }
             if(obj[1] != null){
               othersLoan.setBORROWERORGNAME(obj[1].toString());//单位名称
             }
             if(obj[2] != null){
               othersLoan.setBORROWERCARDNUM(obj[2].toString());//othersloan主键
             }
             if(obj[3] != null){
               othersLoan.setBORROWERSALARYBASE(obj[3].toString());//职工编号
             }
            
             if(obj[4] != null){
               othersLoan.setBORROWERMONTHPAY(obj[4].toString());//单位编号
             }
             if(obj[5] != null){
               othersLoan.setBORROWERBALANCE(obj[5].toString());//身份证号
             }
             if(obj[6] != null){
               othersLoan.setASSISAGE(obj[6].toString());//贷款时间
             }
             if(obj[7] != null){
               othersLoan.setHOUSEADDR(obj[7].toString());//房屋总价
             }
             if(obj[8] != null){
               othersLoan.setHOUSETOTALPRICE(obj[8].toString());//房屋总价
             }
             if(obj[9] != null){
               othersLoan.setHOUSEAREA(obj[9].toString());//房屋面积
             }
             if(obj[10] != null){
               othersLoan.setLOANMONEY(obj[10].toString());//贷款金额
             }
             if(obj[11] != null){
               othersLoan.setLOANTIME(obj[11].toString());//贷款年限
             }
               if(obj[12] != null){
                 othersLoan.setHOUSETYPE(obj[12].toString());//贷款年限
               }
               if(obj[13] != null){
                 othersLoan.setBORROWERADDR(obj[13].toString());//贷款年限
               }
             tempList.add(othersLoan);
            }
           }
           return tempList;
         }
       });
     } catch (Exception e) {
       e.printStackTrace();
     }
     return list;
   }
   public List queryBorrowerAllListByCriterions_1(final String name,
       final String cardNum, final String orgId, final String empId,
       final String orgName, final String office, final String city,
       final String beginBizDate, final String endBizDate, final SecurityInfo securityInfo) {
     List list = new ArrayList();
     try {
       list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

         public Object doInHibernate(Session session) throws HibernateException,
             SQLException {

           String hql = "select * from " +
               "(select t.borrowername c," +
               "t.id d," +
               " t.borrwerempid e," +
               " t.borrowerorgname f ," +
               " t.borrowerorgid g , " +
               " t.borrowercardnum h," +
               " t.houseaddr i," +
               " t.housetotalprice j," +
               " t.housearea k," +
               " t.loanmoney l," +
               " t.loantime n," +
               " t.loancity,'借款人',t.office p,t.op_time q from othersloan t " +
               ") a ";  
           Vector parameters = new Vector();
           String criterion = "";
           List tempList = new ArrayList();
           if (name != null && !"".equals(name)) {
             criterion += " a.c = ? and ";
             parameters.add(name);
           }

           if (cardNum != null && !"".equals(cardNum)) {
             criterion += " a.h = ? and ";
             parameters.add(cardNum);
           }

           if (orgId != null && !"".equals(orgId)) {
             criterion += " a.g = ? and ";
             parameters.add(orgId);
           }

           if (empId != null && !"".equals(empId)) {
             criterion += " a.e = ? and ";
             parameters.add(empId);
           }
           
           if (orgName != null && !"".equals(orgName)) {
             criterion += " a.f = ? and ";
             parameters.add(orgName);
           }

           if (office != null && !"".equals(office)) {
             criterion += " a.p = ? and ";
             parameters.add(office);
           }

           if (beginBizDate != null && !"".equals(beginBizDate)) {
             criterion += " to_char(a.q,'yyyymmdd') >= ? and ";
             parameters.add(beginBizDate);
           }

           if (endBizDate != null && !"".equals(endBizDate)) {
             criterion += " to_char(a.q,'yyyymmdd') <= ? and ";
             parameters.add(endBizDate);
           }


           if (criterion.length() != 0)
             criterion = " where 1=1 and "
                 + criterion.substring(0, criterion.lastIndexOf("and"));
           hql = hql + criterion + " order by a.d desc ";

           Query query = session.createSQLQuery(hql);
           for (int i = 0; i < parameters.size(); i++) {
             query.setParameter(i, parameters.get(i));
           }
           if(query.list().size() != 0){
             Iterator iterate = query.list().iterator();
             Object[] obj = null;
           while (iterate.hasNext()) {
             obj = (Object[]) iterate.next();
             OthersLoan othersLoan = new OthersLoan();
             if(obj[0] != null){
               othersLoan.setBorrowerName(obj[0].toString());
             }
             if(obj[1] != null){
               othersLoan.setId(new Integer(obj[1].toString()));//othersloan主键
             }
             if(obj[2] != null){
               othersLoan.setBorrowerEmpId(obj[2].toString());//职工编号
             }
             if(obj[3] != null){
               othersLoan.setBORROWERORGNAME(obj[3].toString());//单位名称
             }
             if(obj[4] != null){
               othersLoan.setBORROWERORGID(obj[4].toString());//单位编号
             }
             if(obj[5] != null){
               othersLoan.setBORROWERCARDNUM(obj[5].toString());//身份证号
             }
             if(obj[6] != null){
               othersLoan.setHOUSEADDR(obj[6].toString());//房屋坐落
             }
             if(obj[7] != null){
               othersLoan.setHOUSETOTALPRICE(obj[7].toString());//房屋总价
             }
             if(obj[8] != null){
               othersLoan.setHOUSEAREA(obj[8].toString());//房屋面积
             }
             if(obj[9] != null){
               othersLoan.setLOANMONEY(obj[9].toString());//贷款金额
             }
             if(obj[10] != null){
               othersLoan.setLOANTIME(obj[10].toString());//贷款年限
             }
             if(obj[11] != null){
               othersLoan.setLOANCITY(obj[11].toString());//贷款城市
             }
             if(obj[12] != null){
               othersLoan.setBORROWERADDR(obj[12].toString());//借款人类型
             }
             tempList.add(othersLoan);
            }
           }
           return tempList;
         }
       });
     } catch (Exception e) {
       e.printStackTrace();
     }
     return list;
   }
}
