package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpaddpayMaintainDTO;

/**
 * 职工补缴
 * 
 *@author 李娟
 *2007-6-20
 */
public class EmpAddPayDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public EmpAddPay queryById(Serializable id) {  
    Validate.notNull(id);
    return  (EmpAddPay) getHibernateTemplate().get(EmpAddPay.class,id);    
  }
  /**
   * 插入记录
   * @param empAddPay
   * @return
   */
  public Serializable insert(EmpAddPay empAddPay){
    Serializable id = null;
    try{    
    Validate.notNull(empAddPay);
    id = getHibernateTemplate().save(empAddPay);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  
  /**
   * 根据结算号和业务类型查询缴存金额
   * @param noteNum
   * @param type
   * @return
   */
  public BigDecimal getPayMoney_jj(final String noteNum,final String type) {
    BigDecimal payMoney = null;
    payMoney = (BigDecimal)getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select aa301.pay_money from AA301 aa301" +
            " where aa301.note_num = ? and aa301.pay_type = ? ";       
        Query query = session.createSQLQuery(sql);
        query.setParameter(0,noteNum);
        query.setParameter(1,type);        
        return query.uniqueResult();
      }
    });
    if(payMoney==null){
      payMoney = new BigDecimal(0.00);
    }
    return payMoney;
  }
  
  public List queryByEmpId(final Integer empId,final Integer paymentHeadId){
    Validate.notNull(empId);
    List list=new ArrayList();
    try{
    list=(List)getHibernateTemplate().executeFind(
        new HibernateCallback() {

          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {

            String hql = "from AddPayTail addPayTail  ";
            Vector parameters = new Vector();
            String criterion = "";

            if (empId != null) {
              criterion += "  addPayTail.empId=?  and  ";
              parameters.add(empId);
            }
            if (paymentHeadId != null) {
              criterion += "  addPayTail.paymentHead.id=?  and  ";
              parameters.add(paymentHeadId);
            }

            if (criterion.length() != 0)
            criterion = " where " + criterion.substring(0, criterion.lastIndexOf(" and "));        
            hql = hql + criterion;     
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            return query.list();
          }
        }

    );
    }
   catch (Exception e) {
    e.printStackTrace();
  }
   return list;
}

  public List queryByEmpId_lg(final Integer org,final Integer empInfo){
    Validate.notNull(org);
    Validate.notNull(empInfo);
    List list=new ArrayList();
    try{
    list=(List)getHibernateTemplate().executeFind(
        new HibernateCallback() {

          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {

            String hql = "from Emp emp  ";
            Vector parameters = new Vector();
            String criterion = "";

            if (org != null) {
              criterion += "  emp.org.id=?  and  ";
              parameters.add(org);
            }
            if (empInfo != null) {
              criterion += "  emp.empInfo.id=?  and  ";
              parameters.add(empInfo);
            }

            if (criterion.length() != 0)
            criterion = " where "+ criterion.substring(0, criterion.lastIndexOf(" and ")); 
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.list();
          }
        }

    );
    }
   catch (Exception e) {
    e.printStackTrace();
  }
   return list;
}
   
  
  
  public void deleteAll(final Integer addPayHeadId){
    getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
             String sql="delete AddPayTail addPayTail where addPayTail.paymentHead.id=?";          
             session.createQuery(sql).setInteger(0, addPayHeadId.intValue()).executeUpdate();
              return null;
          }
        }  
    );
  }
  /**
   * 根据职工ID和头表ID查询删除尾表
   * @param empId
   * @param addPayHeadId
   */
  public void deleteTail(final Integer empId,final Integer addPayHeadId){
    getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
             String hql="delete AddPayTail addPayTail where addPayTail.empId = ? and addPayTail.paymentHead.id = ?";          
             Query query = session.createQuery(hql);
             query.setInteger(0, empId.intValue());
             query.setInteger(1, addPayHeadId.intValue());
             query.executeUpdate();
              return null;
          }
        }  
    );
  }
  /**
   * 根据头表ID查询尾表缴存金额
   * @param addPayHeadId
   * @return
   */
  public BigDecimal queryTailMoney(final Integer addPayHeadId){
    BigDecimal tailMoney = (BigDecimal)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
             String hql="select sum(addPayTail.orgAddMoney + addPayTail.empAddMoney) from  AddPayTail addPayTail " +
                " where addPayTail.paymentHead.id = ?";          
             Query query = session.createQuery(hql);
             query.setInteger(0, addPayHeadId.intValue());
             
              return query.uniqueResult();
          }
        }  
    );
    return tailMoney;
  }
  /**
   * 插入记录
   * @param addPayTail
   * @return
   */
  public Serializable insert(AddPayTail addPayTail){
    Serializable id = null;
    try{    
    Validate.notNull(addPayTail);
    id = getHibernateTemplate().save(addPayTail);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  

  public List queryByHeadId(final Integer paymentHeadId, final Integer empId,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) {
    List personAddPayList = null;
    try {

      Validate.isTrue(paymentHeadId != null);
      personAddPayList = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            List list = new ArrayList();

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select addPayTail.empId,min(addPayTail.addMonht),max(addPayTail.addMonht),"
                  + " sum(addPayTail.empAddMoney),sum(addPayTail.orgAddMoney),addPayTail.addReason,"
                  + " addPayTail.empName,addPayTail.reserveaC "
                  + " from AddPayTail addPayTail  ";
              Vector parameters = new Vector();
              String criterion = "";

              if (paymentHeadId != null) {
                criterion += "  addPayTail.paymentHead.id=?  and  ";
                parameters.add(paymentHeadId);
              }

              if (empId != null) {
                criterion += " addPayTail.empId = ? and  ";
                parameters.add(empId);
              }

              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf(" and "));

              String ob = orderBy;

              if (ob == null)
                ob = " max(addPayTail.id) ";

              if (ob.equals("addPayTail.addPaySum")) {
                ob = " sum(addPayTail.orgAddMoney+addPayTail.empAddMoney)";
              }
              String od = order;
              if (od == null)
                od = "ASC";

              hql = hql + criterion
                  + " group by addPayTail.empId,addPayTail.addReason,addPayTail.reserveaC  "
                  + " order by  " + ob + " " + od;

              Query query = session.createQuery(hql);
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

              Iterator it = query.iterate();
              Object obj[] = null;
              while (it.hasNext()) {
                obj = (Object[]) it.next();
                AddPayTail addPayTail = new AddPayTail();
                addPayTail.setEmpId(new Integer(obj[0].toString()));
                addPayTail.setBeginMonth(obj[1].toString());
                addPayTail.setEndMonth(obj[2].toString());
                addPayTail.setEmpAddPaySum(new BigDecimal(obj[3].toString()));
                addPayTail.setOrgAddPaySum(new BigDecimal(obj[4].toString()));
                addPayTail.setAddPaySum(addPayTail.getEmpAddPaySum().add(
                    addPayTail.getOrgAddPaySum()));
                addPayTail.setEmpName(obj[6].toString());
                if (obj[7] != null && !"".equals(obj[7].toString())) {
                  addPayTail.setReserveaC(obj[7].toString());
                }
                if ((obj[5] != null) && (obj[5] != "")) {
                  addPayTail.setAddReason((obj[5].toString()));
                } else {
                  addPayTail.setAddReason("");
                }
                list.add(addPayTail);
              }

              return list;
            }
          }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return personAddPayList;
  }
//
  public List queryByHeadIdCount(final Integer paymentHeadId)
 
      {
        List personAddPayList = null;
     try {
     
       Validate.isTrue(paymentHeadId != null );
       personAddPayList = getHibernateTemplate().executeFind(
           new HibernateCallback() {
             List list=new ArrayList();
             public Object doInHibernate(Session session)
                 throws HibernateException, SQLException {

               String hql = " select addPayTail.empId,min(addPayTail.addMonht),max(addPayTail.addMonht),"+
                            " sum(addPayTail.empAddMoney),sum(addPayTail.orgAddMoney),addPayTail.addReason,addPayTail.empName,addPayTail.reserveaC,addPayTail.salaryBase,addPayTail.empRate " +
                            " from AddPayTail addPayTail  ";
               Vector parameters = new Vector();
               String criterion = "";

               if (paymentHeadId != null) {
                 criterion += "  addPayTail.paymentHead.id=?  and  ";
                 parameters.add(paymentHeadId);
               }
               
               if (criterion.length() != 0)
                 criterion = " where "+ criterion.substring(0, criterion.lastIndexOf(" and "));
                     
               hql = hql + criterion+" group by addPayTail.empId, addPayTail.addReason,addPayTail.reserveaC,addPayTail.salaryBase,addPayTail.empRate " ;
             
               Query query = session.createQuery(hql);
               for (int i = 0; i < parameters.size(); i++) {
                 query.setParameter(i, parameters.get(i));
               }
               Iterator it=query.iterate();
               Object obj[]=null;
               while(it.hasNext()){
                 obj=(Object[])it.next();
                 AddPayTail addPayTail=new AddPayTail();
                 addPayTail.setEmpId(new Integer(obj[0].toString()));
                 addPayTail.setBeginMonth(obj[1].toString());
                 addPayTail.setEndMonth(obj[2].toString());
                 addPayTail.setOrgAddPaySum(new BigDecimal(obj[3].toString()));
                 addPayTail.setEmpAddPaySum(new BigDecimal(obj[4].toString()));
                 if((obj[5]!=null)&&(obj[5]!="")){
                 addPayTail.setAddReason((obj[5].toString()));
                 }
                 else{ 
                   addPayTail.setAddReason("");
                 }
                 addPayTail.setEmpName(obj[6].toString());
                 if(obj[7]!=null && !"".equals(obj[7].toString())){
                   addPayTail.setReserveaC(obj[7].toString());
                 }
                 if(obj[8]!=null && !"".equals(obj[8].toString())){
                   addPayTail.setSalaryBase(new BigDecimal(obj[8].toString()));
                 }
                 if(obj[9]!=null && !"".equals(obj[9].toString())){
                   addPayTail.setEmpRate(new BigDecimal(obj[9].toString()));
                 }
                 list.add(addPayTail);
               }
                
                return list;
             }
           }

       );


     } catch (Exception e) {
       e.printStackTrace();
     }
     return personAddPayList;
    }
  
  public List queryEmpAddPay(final Serializable orgid, 
      final String orderBy, final String order, final int start,final int pageSize,final int page) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql ="select addPayTail from AddPayTail addPayTail,EmpAddPay empAddPay " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (orgid != null&&!orgid.equals("")) {
                criterion += " addPayTail.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgid.toString()));
              }
              String ob = orderBy;
              String od = order;
              if (criterion.length() != 0)
                criterion=" where addPayTail.paymentHead.id = empAddPay.id and empAddPay.payStatus = 1 and "+
                criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              query.setFirstResult(start);
              if (pageSize > 0)
              query.setMaxResults(pageSize); 
              List queryList=query.list();
              
              if(queryList==null||queryList.size()==0){           
                query.setFirstResult(pageSize*(page-2));
                if (pageSize > 0)
                  query.setMaxResults(pageSize);
                queryList=query.list();
              }
              return queryList;
             
            }
          }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  
  public List queryEmpAddPayList(final Serializable orgid, 
      final String orderBy, final String order, final int start,final int pageSize) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql ="select addPayTail from AddPayTail addPayTail,EmpAddPay empAddPay " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (orgid != null&&!orgid.equals("")) {
                criterion += " addPayTail.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgid.toString()));
              }
              String ob = orderBy;
              if (ob == null)
                ob = " addPayTail.empId ";

              String od = order;
              if (od == null)
                od = "DESC";
              if (criterion.length() != 0)
                criterion=" where addPayTail.paymentHead.id = empAddPay.id  "+
                criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion + "order by " + ob + " " + od ;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);       
              
              return query.list();
            }
          }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  

  public List queryEmpaddpayListByCriterionsLg(final Serializable orgId, final String unitName,final String addPayAmount,
      final String bizStatus,final String beginMonth,final String endMonth, final String orderBy,final String order,
      final int start, final int pageSize)
       throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = (List)getHibernateTemplate().executeFind(new HibernateCallback() {
          

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select empAddPay.org.id,empAddPay.org.orgInfo.name," +
                  " sum(addPayTail.orgAddMoney),sum(addPayTail.empAddMoney)," +
                  " addPayTail.addMonht," +
                  " empAddPay.noteNum,empAddPay.docNum,to_char(empAddPayBizActivityLog.opTime,'yyyymmdd')," +
                  " empAddPay.payStatus, empAddPay.id,empAddPay.payMoney ,count(addPayTail.id)" +
                  " from AddPayTail addPayTail,EmpAddPay empAddPay,EmpAddPayBizActivityLog empAddPayBizActivityLog " +
                  " where addPayTail.paymentHead.id = empAddPay.id and  " +
                  " addPayTail.paymentHead.id=empAddPayBizActivityLog.bizid and empAddPayBizActivityLog.action = 2 ";

              Vector parameters = new Vector();
              String criterion = "";
              if (orgId != null&&!orgId.equals("")) {
                criterion += " addPayTail.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgId.toString()));
              }
              
              if (unitName != null&&!unitName.equals("")) {
                criterion += " addPayTail.paymentHead.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + unitName + "%");
              }
              if (addPayAmount != null&&!addPayAmount.equals("")) {
                criterion += " addPayTail.addPaySum= ? and ";
                parameters.add("%" + addPayAmount + "%");
              }

              if (bizStatus != null&&!bizStatus.equals("")) {
                criterion += " empAddPay.payStatus = ?  and ";
                parameters.add(new Integer(bizStatus));
              }else{
                criterion += " (empAddPay.payStatus = 1 or empAddPay.payStatus = 2 ) and ";
              }
              
              if (beginMonth != null && !beginMonth.equals("")) {
                criterion += " addPayTail.addMonht >= ? and ";
                parameters.add(beginMonth);
              }
              if (endMonth != null && !endMonth.equals("")) {
                criterion += " addPayTail.addMonht <= ? and ";
                parameters.add(endMonth);
              }

          
              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              String ob = orderBy;
              if (ob == null)
                ob = " empAddPay.id ";

              String od = order;
              if (od == null)
                od = "DESC";

              hql = hql + criterion +" group by empAddPay.org.id,empAddPay.org.orgInfo.name," +
                  "empAddPay.noteNum,empAddPay.docNum,empAddPayBizActivityLog.opTime," +
                  "empAddPay.payStatus,empAddPay.id,empAddPay.payMoney,addPayTail.addMonht "+ "order by " + ob + " " + order ;
             
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator it=query.iterate();
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                EmpaddpayMaintainDTO empaddpayMaintainDTO=new EmpaddpayMaintainDTO();
                empaddpayMaintainDTO.setOrgId(obj[0].toString());
                empaddpayMaintainDTO.setOrgName(obj[1].toString());
                if(obj[6] != null){
                  empaddpayMaintainDTO.setDocNum(obj[6].toString());
                }
                if(obj[5] != null){
                  empaddpayMaintainDTO.setNoteNum(obj[5].toString());
                }
                empaddpayMaintainDTO.setPayMonth(obj[4].toString());
                empaddpayMaintainDTO.setOpTime(obj[7].toString());
                empaddpayMaintainDTO.setPay((new BigDecimal(obj[2].toString())).add(new BigDecimal(obj[3].toString())));
                empaddpayMaintainDTO.setPayStatus(obj[8].toString());
                empaddpayMaintainDTO.setId(obj[9].toString());
                empaddpayMaintainDTO.setPersonCounts(obj[11].toString());
                temp_list.add(empaddpayMaintainDTO);
              }
              return temp_list;
            }
          }
         
      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  /**
   * 个人补缴金额合计
   * @param id
   * @param name
   * @param status
   * @param payMonth
   * @param payType
   * @param payMoney
   * @param settlementDate
   * @param compare
   * @return
   */
  public BigDecimal queryEmpaddPayMoneyByCriterionsLg(final Serializable orgId, final String unitName,final String addPayAmount,
      final String bizStatus,final String beginMonth,final String endMonth) {  
    BigDecimal money=new BigDecimal(0.00);
    try {
      List list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select distinct sum(addPayTail.orgAddMoney+addPayTail.empAddMoney) " +
                  "from AddPayTail addPayTail,EmpAddPay empAddPay,EmpAddPayBizActivityLog empAddPayBizActivityLog " +
                  "where addPayTail.paymentHead.id = empAddPay.id and  " +
                  "addPayTail.paymentHead.id=empAddPayBizActivityLog.bizid ";
              Vector parameters = new Vector();
              String criterion = "";
              if (orgId != null&&!orgId.equals("")) {
                criterion += " addPayTail.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgId.toString()));
              }
              
//              if (unitName != null&&!unitName.equals("")) {
//                criterion += " addPayTail.paymentHead.org.orgInfo.name  like ? escape '/'  and ";
//                parameters.add("%" + unitName + "%");
//              }
//              if (bizStatus != null&&!bizStatus.equals("")) {
//                if(bizStatus.equals("1")){
//                  criterion += " empAddPay.payStatus=1 and ";
//                }else if(bizStatus.equals("2")){
//                  criterion += " empAddPay.payStatus=2 and ";
//                }else if(bizStatus.equals("3")){
//                  criterion += " empAddPay.payStatus=3  and ";
//                }
//              else if(bizStatus.equals("4")){
//                criterion += " empAddPay.payStatus=4  and ";
//              }
//              else if(bizStatus.equals("5")){
//              criterion += " empAddPay.payStatus=5  and ";
//              }
//              }
//
//              if (addPayAmount!=null&&!addPayAmount.equals("0")) {    
//                criterion += " orgAddPay.payMoney = ? and ";
//                parameters.add(new BigDecimal(addPayAmount));
//              }
//              if (beginMonth != null&&!beginMonth.equals("")&&endMonth!=null&&!endMonth.equals("")) {
//                criterion += " (addPayTail.addMonht between  ?  and  ? )  and ";
//             
//                parameters.add(beginMonth);
//                parameters.add(endMonth);
//              }
              if (criterion.length() != 0)
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion; //+" group by empAddPay.org.id,empAddPay.org.orgInfo.name," +
                  //"empAddPay.noteNum,empAddPay.docNum,empAddPayBizActivityLog.opTime," +
                  //"empAddPay.payStatus,empAddPay.id,empAddPay.payMoney,addPayTail.addMonht ";
              
             
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.list();
              }
            }
          
      );

      if(list != null){
        Iterator it=list.iterator();
        Object obj=null;
        while(it.hasNext()){
          obj=(Object)it.next();
          if(obj!=null)
          money = money.add(new BigDecimal(obj.toString()));
         
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return money;
  }
  
  public List queryPaymentTailListLg(final Serializable paymentId) throws Exception{
    List empList=null;
    try {
      empList =(List) getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " from AddPayTail addPayTail ";
              Vector parameters = new Vector();
              String criterion = "";

              if (paymentId != null&&!paymentId.equals("")) {
                criterion += "addPayTail.paymentHead.id = ?  and ";
                parameters.add(new Integer(paymentId.toString()));
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              
              hql = hql + criterion ;
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
  public void delete(EmpAddPay empAddPay){
    Validate.notNull(empAddPay);
    getHibernateTemplate().delete(empAddPay);
  }
  
  /**
   * 
   * 根据单位ID和缴存状态查询
   * @param orgId
   * @param payStatus
   * @return
   */
  public EmpAddPay queryEmpAddPayByOrgId(final Serializable orgId,final Integer payStatus ){
    EmpAddPay empAddPay = null;
    empAddPay = (EmpAddPay) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from EmpAddPay empAddPay ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgId != null&&!orgId.equals("")) {
          criterion += "empAddPay.org.id = ?  and ";
          parameters.add(new Integer(orgId.toString()));
        }

        if (payStatus != null&&!payStatus.equals("")) {
          criterion += "empAddPay.payStatus = ?  and ";
          parameters.add(payStatus);
        }
        
        if (criterion.length() != 0)
          criterion = " where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.uniqueResult();
      }
    });
    if(empAddPay == null){
      empAddPay = new EmpAddPay();
    }
    return empAddPay;
  }
  
  
  public List queryEmpAddPayByOrgId_lg(final Serializable orgId,final Integer payStatus ){
    List list = null;
    list = (List) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from EmpAddPay empAddPay ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgId != null&&!orgId.equals("")) {
          criterion += "empAddPay.org.id = ?  and ";
          parameters.add(new Integer(orgId.toString()));
        }

        if (payStatus != null&&!payStatus.equals("")) {
          criterion += "empAddPay.payStatus = ?  and ";
          parameters.add(payStatus);
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

    return list;
  }
  public void updateEmpAddPay(EmpAddPay empAddPay) {
    
    try {
      Validate.notNull(empAddPay);
      this.getHibernateTemplate().update(empAddPay);
    } catch (Exception e) {
      e.printStackTrace();
    }   
  }
  public EmpAddPay queryByHeadId(final Serializable id) {  
    EmpAddPay empAddPay = null;
    empAddPay = (EmpAddPay) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from EmpAddPay empAddPay ";
        String headId=id.toString();
        Vector parameters = new Vector();
        String criterion = "";

        if (id != null&&!id.equals("")) {
          criterion += "empAddPay.id = ?  and ";
          parameters.add(new Integer(headId));
        }
        
        if (criterion.length() != 0)
          criterion = " where "+ criterion.substring(0, criterion.lastIndexOf("and"));
              
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.uniqueResult();
      }
    });
    if(empAddPay == null){
      empAddPay = new EmpAddPay();
    }
    return empAddPay;
  }
  
  //ld—add修改，防止个人补缴并发操作
  public List querytestbyid(final String id){
    List list=null;
    try{
      list=getHibernateTemplate().executeFind(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          String sql="select a.org_add_money from aa304 a where a.emp_id=? ";
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, id);
          return query.list();
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  public List querytestbyid1(final String id){
    List list=null;
    try{
      list=getHibernateTemplate().executeFind(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          String sql="select a.org_add_money from aa304 a where a.pay_head_id=? ";
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, id);
          return query.list();
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  //刘聃修改：查询状态
  public String queryStatusById(final Serializable id){
    String status="";
    try{
       status=(String)getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String status;
                String  hql = "select aa301.pay_status from aa301 aa301 where id= ?";              
                Query query = session.createSQLQuery(hql);
                query.setParameter(0, id);
                status = query.uniqueResult().toString();
             
                return status;           
            }
          }
      );
    }catch(Exception s){
      s.printStackTrace();
    }
    return status;
  }
    
  
}

