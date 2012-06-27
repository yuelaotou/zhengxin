package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpaddpayMaintainDTO;


/**
 * 单位补缴
 * 
 *@author 李娟
 *2007-6-20
 */
public class AddPayTailDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public AddPayTail queryById(Serializable id) {  
    Validate.notNull(id);
    return  (AddPayTail) getHibernateTemplate().get(AddPayTail.class,id); 
    
  }
  
  public List queryByEmpId(final Integer empId,final Integer paymentHeadId){
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
  public AddPayTail queryByHeadId(final String empId){
    AddPayTail addPayTail=new AddPayTail();
    try{
      addPayTail=(AddPayTail)getHibernateTemplate().executeFind(
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
   return addPayTail;
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
            criterion = " where "  + criterion.substring(0, criterion.lastIndexOf(" and "));    
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
   
  
  
  public void deleteAll(List list){
    Validate.notNull(list);
    this.getHibernateTemplate().deleteAll(list);
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

  /**
   * 于庆丰　根据paymentId查询
   * @param id
   * @return
   */
//  public List queryByPaymentId(final Serializable id) {
//    List list = null;
//    Validate.notNull(id);
//    list = (List)getHibernateTemplate().execute(
//        new HibernateCallback() {
//          public Object doInHibernate(Session session)
//              throws HibernateException, SQLException {
//            String hql = "select addPayTail from AddPayTail addPayTail where addPayTail.paymentHead.id = ? ";
////            Vector parameters = new Vector();
////            parameters.add((Integer)id);
//
//            Query query = session.createQuery(hql);
////            query.setParameter(0, parameters.get(0));
//
//            return query.list();
//          }
//        });
//    return list;
//  }
  

  public List queryByHeadId(final Integer paymentHeadId)
  {
 List personAddPayList = null;
 try {
   Validate.isTrue(paymentHeadId != null );
  

   personAddPayList = getHibernateTemplate().executeFind(
       new HibernateCallback() {

         public Object doInHibernate(Session session)
             throws HibernateException, SQLException {

           String hql = "from AddPayTail addPayTail  ";
           Vector parameters = new Vector();
           String criterion = "";

           if (paymentHeadId != null) {
             criterion += "  addPayTail.paymentHead.id=?  and  ";
             parameters.add(paymentHeadId);
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

 } catch (Exception e) {
   e.printStackTrace();
 }
 return personAddPayList;
}


  /**
   * 于庆丰　根据paymentHeadId查询empId & 个人汇缴＋单位汇缴的总金额
   * @param id
   * @return
   */
  public List querySumPayByEmpId(final Serializable id) {
    List list = new ArrayList();
    Validate.notNull(id);
    list = (List)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select aa304.emp_id, sum(aa304.org_add_money + aa304.emp_add_money) from aa304 where aa304.pay_head_id = ? group by aa304.emp_id";
            Vector parameters = new Vector();
            parameters.add(id);
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, parameters.get(0));
            return query.list();
          }
        });
    return list;
  }

//  public List queryByHeadId(final Integer paymentHeadId)
//     {
//
//    List personAddPayList = null;
// try {
//   Validate.isTrue(paymentHeadId != null );
//  
//  
//   personAddPayList = getHibernateTemplate().executeFind(
//       new HibernateCallback() {
//         List list=new ArrayList();
//         public Object doInHibernate(Session session)
//             throws HibernateException, SQLException {
//
//           String hql = " select addPayTail.empId,min(addPayTail.addMonht),max(addPayTail.addMonht),"+
//                        " sum(addPayTail.empAddMoney),sum(addPayTail.orgAddMoney),addPayTail.addReason " +
//                        " from AddPayTail addPayTail  ";
//           Vector parameters = new Vector();
//           String criterion = "";
//
//           if (paymentHeadId != null) {
//             criterion += "  addPayTail.paymentHead.id=?  and  ";
//             parameters.add(paymentHeadId);
//           }
//           
//           if (empId != null) {
//             criterion += "  addPayTail.empId=?  and  ";
//             parameters.add(empId);
//           }
//
//           if (criterion.length() != 0)
//             criterion = " where "  
//                 + criterion.substring(0, criterion.lastIndexOf(" and "));
//
//           String ob = orderBy;
//           System.out.println("ob在dao中================"+ob);
//           if (ob == null)
//             ob = " addPayTail.empId  ";
//
//           String od = order;
//           if (od == null)
//             od = "DESC";
//
//           hql = hql + criterion+" group by addPayTail.empId, addPayTail.addReason " + " order by  " + ob + " " + order ;
//           System.out.println("hqlss================="+hql);
//           Query query = session.createQuery(hql);
//           for (int i = 0; i < parameters.size(); i++) {
//             query.setParameter(i, parameters.get(i));
//           }
//           System.out.println("start========================="+start);
//           System.out.println("pageSize========================="+pageSize);
////           query.setFirstResult(start);
////           if (pageSize > 0)
////           query.setMaxResults(pageSize);  
//           Iterator it=query.iterate();
//           Object obj[]=null;
//           while(it.hasNext()){
//             obj=(Object[])it.next();
//             AddPayTail addPayTail=new AddPayTail();
//             addPayTail.setEmpId(new Integer(obj[0].toString()));
//             addPayTail.setBeginMonth(obj[1].toString());
//             addPayTail.setEndMonth(obj[2].toString());
//             addPayTail.setOrgAddPaySum(new BigDecimal(obj[3].toString()));
//             addPayTail.setEmpAddPaySum(new BigDecimal(obj[4].toString()));
//            
//             if((obj[5]!=null)&&(obj[5]!="")){
//             addPayTail.setAddReason((obj[5].toString()));
//             }
//             else{ 
//               addPayTail.setAddReason("");
//             }
//             list.add(addPayTail);
//           }
//            return list;
//         }
//       }
//
//   );
//
//
// } catch (Exception e) {
//   e.printStackTrace();
// }
// return personAddPayList;
//}
//  
  
  public List queryEmpAddPay(final Serializable orgid, 
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
                criterion=" where addPayTail.paymentHead.id = empAddPay.id and empAddPay.payStatus = 1 and "+
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
      final String bizStatus,final String beginMonth, final String payMonth,final String payMonth1,final String orderBy,final String order,
      final int start, final int pageSize,final int page,final String opTime,final String opTime1,final SecurityInfo securityInfo,final String collBankId)
       throws NumberFormatException, Exception {
    
    List list =null;
   
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select empAddPay.org.id,empAddPay.org.orgInfo.name," +                         
                  " empAddPay.noteNum,empAddPay.docNum,empAddPay.settDate, " +
                  " empAddPay.payStatus, empAddPay.id,empAddPay.payMoney,to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') " +
                  " from EmpAddPay empAddPay, EmpAddPayBizActivityLog empAddPayBizActivityLog"+
                  " where 1=1 and empAddPayBizActivityLog.bizid=empAddPay.id and " +
                  " empAddPay.payStatus = empAddPayBizActivityLog.action and empAddPay.org.id "+securityInfo.getGjjSecurityHqlSQL();
                  
              Vector parameters = new Vector();
              String criterion = "";
              if (orgId != null&&!orgId.equals("")) {
//                criterion += " addPayTail.paymentHead.org.id = ?  and ";
//                parameters.add(new Integer(orgId.toString()));
                criterion += " to_char(empAddPay.org.id) like ? escape '/'  and ";
                parameters.add("%" + orgId.toString() + "%");
               
              }
              if (opTime != null&&!opTime.equals("") && opTime1 != null && !opTime1.equals("")) {
                criterion += " (to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') between ?  and  ?) and ";
                parameters.add(opTime);
                parameters.add(opTime1);
              }else if (opTime != null&&!opTime.equals("")) {
                criterion += " to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(opTime);
              }else if (opTime1 != null && !opTime1.equals("")) {
                criterion += " to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(opTime1);
              }
              if (unitName != null&&!unitName.equals("")) {
                criterion += " empAddPay.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + unitName + "%");
              }
              
              if (addPayAmount != null&&!addPayAmount.equals("0")) {
                
                criterion += "empAddPay.payMoney =? and ";
                parameters.add(new BigDecimal(addPayAmount));
              }
              
//              if (bizStatus != null&&!bizStatus.equals("")) {
//                criterion += " empAddPay.payStatus = ?  and ";
//                parameters.add(new Integer(bizStatus));
//              }
//
//              else{
//                criterion += " (empAddPay.payStatus = 1 or empAddPay.payStatus = 2 ) and ";
//              }
              if (bizStatus == null||bizStatus.equals("")) {
                criterion += " (empAddPay.payStatus = 1 or empAddPay.payStatus = 2 ) and ";
  
              } else if(bizStatus.endsWith("all")){
                criterion += " (empAddPay.payStatus between 1 and 5) and ";
              } else{
                criterion += " empAddPay.payStatus = ?  and ";
                parameters.add(new Integer(bizStatus));
              }
              
//              if (beginMonth != null&&!beginMonth.equals("")&&endMonth!=null&&!endMonth.equals("")) {
//                criterion += " (addPayTail.addMonht between  ?  and  ? )  and ";
//             
//                parameters.add(beginMonth);
//                parameters.add(endMonth);
//              }

            if (payMonth != null&&!payMonth.equals("") && payMonth1 != null && !payMonth1.equals("")) {
              criterion += " (empAddPay.settDate between ?  and  ? ) and ";
              parameters.add(payMonth);
              parameters.add(payMonth1);
            }else if (payMonth != null&&!payMonth.equals("")) {
              criterion += " empAddPay.settDate = ? and ";
              parameters.add(payMonth);
            }else if (payMonth1 != null && !payMonth1.equals("")) {
              criterion += " empAddPay.settDate = ? and ";
              parameters.add(payMonth1);
            }
            if (collBankId != null&&!collBankId.equals("")) {
              criterion += " empAddPay.org.orgInfo.collectionBankId = ?  and ";
              parameters.add(collBankId);
            }

              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf(" and "));

              String ob = orderBy;
              if (ob == null)
                ob = " empAddPay.id ";

              String od = order;
              if (od == null)
                od = "DESC";

              hql = hql + criterion + "order by " + ob + " " + od ;
             
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);       
              
              Iterator it=query.iterate();
              Object obj[]=null;
              
              String hsqlCount=" select count(distinct aa304.emp_id) from AA304 aa304 where aa304.pay_head_id=?";
              while(it.hasNext()){
                obj=(Object[])it.next();
                EmpaddpayMaintainDTO empaddpayMaintainDTO=new EmpaddpayMaintainDTO();
                empaddpayMaintainDTO.setOrgId(obj[0].toString());
                empaddpayMaintainDTO.setOrgName(obj[1].toString());
                if(obj[3] != null){
                  empaddpayMaintainDTO.setDocNum(obj[3].toString());
                }
                if(obj[2] != null){
                  empaddpayMaintainDTO.setNoteNum(obj[2].toString());
                }
                if(obj[4] != null){
                  empaddpayMaintainDTO.setPayMonth(obj[4].toString());
                }
                empaddpayMaintainDTO.setOpTime(obj[8].toString());
                //empaddpayMaintainDTO.setPayMonth(obj[4].toString()+"-"+obj[5].toString());
                
                empaddpayMaintainDTO.setPay(new BigDecimal(obj[7].toString()).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                empaddpayMaintainDTO.setPayStatus(obj[5].toString());
                empaddpayMaintainDTO.setId(obj[6].toString());              
                Query queryCount = session.createSQLQuery(hsqlCount);
                queryCount.setString(0,empaddpayMaintainDTO.getId());            
                Iterator it1=queryCount.list().iterator();
                int count=0;
                if(it1.hasNext()){
                  count=(Integer.parseInt(it1.next().toString()));
                }
                empaddpayMaintainDTO.setPersonCounts(Integer.toString(count));
               
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
   * 个人补缴列表打印信息
   * @param orgId
   * @param unitName
   * @param addPayAmount
   * @param bizStatus
   * @param beginMonth
   * @param payMonth
   * @param orderBy
   * @param order
   * @param opTime
   * @param securityInfo
   * @param collBankId
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryEmpaddpayListByCriterions_jj(final Serializable orgId, final String unitName,final String addPayAmount,
      final String bizStatus,final String beginMonth, final String payMonth,final String payMonth1,final String orderBy,final String order,
      final String opTime,final String opTime1,final SecurityInfo securityInfo,final String collBankId)
       throws NumberFormatException, Exception {
    
    List list =null;
   
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select empAddPay.org.id,empAddPay.org.orgInfo.name," +                         
                  " empAddPay.noteNum,empAddPay.docNum,empAddPay.settDate, " +
                  " empAddPay.payStatus, empAddPay.id,empAddPay.payMoney,to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') " +
                  " from EmpAddPay empAddPay, EmpAddPayBizActivityLog empAddPayBizActivityLog"+
                  " where 1=1 and empAddPayBizActivityLog.bizid=empAddPay.id and " +
                  " empAddPay.payStatus = empAddPayBizActivityLog.action and empAddPay.org.id "+securityInfo.getGjjSecurityHqlSQL();
                  
              Vector parameters = new Vector();
              String criterion = "";
              if (orgId != null&&!orgId.equals("")) {
                criterion += " to_char(empAddPay.org.id) like ? escape '/'  and ";
                parameters.add("%" + orgId.toString() + "%");  
              }
              if (opTime != null&&!opTime.equals("") && opTime1 != null && !opTime1.equals("")) {
                criterion += " (to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') between ?  and  ?) and ";
                parameters.add(opTime);
                parameters.add(opTime1);
              }else if (opTime != null&&!opTime.equals("")) {
                criterion += " to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(opTime);
              }else if (opTime1 != null && !opTime1.equals("")) {
                criterion += " to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(opTime1);
              }
              if (unitName != null&&!unitName.equals("")) {
                criterion += " empAddPay.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + unitName + "%");
              }
              if (addPayAmount != null&&!addPayAmount.equals("0")) {
                criterion += "empAddPay.payMoney =? and ";
                parameters.add(new BigDecimal(addPayAmount));
              }
              if (bizStatus == null||bizStatus.equals("")) {
                criterion += " (empAddPay.payStatus = 1 or empAddPay.payStatus = 2 ) and ";
              } else if(bizStatus.endsWith("all")){
                criterion += " (empAddPay.payStatus between 1 and 5) and ";
              } else{
                criterion += " empAddPay.payStatus = ?  and ";
                parameters.add(new Integer(bizStatus));
              }
              if (payMonth != null&&!payMonth.equals("") && payMonth1 != null && !payMonth1.equals("")) {
                criterion += " (empAddPay.settDate between ?  and  ? ) and ";
                parameters.add(payMonth);
                parameters.add(payMonth1);
              }else if (payMonth != null&&!payMonth.equals("")) {
                criterion += " empAddPay.settDate = ? and ";
                parameters.add(payMonth);
              }else if (payMonth1 != null && !payMonth1.equals("")) {
                criterion += " empAddPay.settDate = ? and ";
                parameters.add(payMonth1);
              }
              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " empAddPay.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf(" and "));

              String ob = orderBy;
              if (ob == null)
                ob = " empAddPay.id ";

              String od = order;
              if (od == null)
                od = "DESC";

              hql = hql + criterion + "order by " + ob + " " + od ;
             
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              
              Iterator it=query.iterate();
              Object obj[]=null;
              
              String hsqlCount=" select count(distinct aa304.emp_id) from AA304 aa304 where aa304.pay_head_id=?";
              while(it.hasNext()){
                obj=(Object[])it.next();
                EmpaddpayMaintainDTO empaddpayMaintainDTO=new EmpaddpayMaintainDTO();
                empaddpayMaintainDTO.setOrgId(obj[0].toString());
                empaddpayMaintainDTO.setOrgName(obj[1].toString());
                if(obj[3] != null){
                  empaddpayMaintainDTO.setDocNum(obj[3].toString());
                }
                if(obj[2] != null){
                  empaddpayMaintainDTO.setNoteNum(obj[2].toString());
                }
                if(obj[4] != null){
                  empaddpayMaintainDTO.setPayMonth(obj[4].toString());
                }
                empaddpayMaintainDTO.setOpTime(obj[8].toString());                
                empaddpayMaintainDTO.setPay(new BigDecimal(obj[7].toString()).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                empaddpayMaintainDTO.setPayStatus(obj[5].toString());
                empaddpayMaintainDTO.setId(obj[6].toString());              
                Query queryCount = session.createSQLQuery(hsqlCount);
                queryCount.setString(0,empaddpayMaintainDTO.getId());            
                Iterator it1=queryCount.list().iterator();
                int count=0;
                if(it1.hasNext()){
                  count=(Integer.parseInt(it1.next().toString()));
                }
                empaddpayMaintainDTO.setPersonCounts(Integer.toString(count));
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
  //
  public List queryEmpaddpayListByCriterions(final Serializable orgId, final String unitName,final String addPayAmount,
      final String bizStatus,final String beginMonth, final String endMonth) 
       throws NumberFormatException, Exception {
    
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select empAddPay.org.id,empAddPay.org.orgInfo.name," +
                  " sum(addPayTail.orgAddMoney),sum(addPayTail.empAddMoney)," +
                  " min(addPayTail.addMonht),max(addPayTail.addMonht)," +
                  " empAddPay.noteNum,empAddPay.docNum,empAddPay.settDate, " +
                  " empAddPay.payStatus, empAddPay.id,empAddPay.payMoney ,count(addPayTail.id)" +
                  " from AddPayTail addPayTail,EmpAddPay empAddPay " +
                  " where addPayTail.paymentHead.id = empAddPay.id  " ;
                  

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
              
              if (addPayAmount != null&&!addPayAmount.equals("0")) {
                
                criterion += "empAddPay.payMoney =? and ";
                parameters.add(new BigDecimal(addPayAmount));
              }
              
              if (bizStatus != null&&!bizStatus.equals("")) {
                criterion += " empAddPay.payStatus = ?  and ";
                parameters.add(new Integer(bizStatus));
              }else{
                criterion += " (empAddPay.payStatus = 1 or empAddPay.payStatus = 2 ) and ";
              }
              
              if (beginMonth != null&&!beginMonth.equals("")&&endMonth!=null&&!endMonth.equals("")) {
                criterion += " (addPayTail.addMonht between  ?  and  ? )  and ";
             
                parameters.add(beginMonth);
                parameters.add(endMonth);
              }

          
              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion +" group by empAddPay.org.id,empAddPay.org.orgInfo.name," +
                  "empAddPay.noteNum,empAddPay.docNum,empAddPay.settDate," +
                  "empAddPay.payStatus,empAddPay.id,empAddPay.payMoney ";
              
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
                if(obj[7] != null){
                  empaddpayMaintainDTO.setDocNum(obj[7].toString());
                }
                if(obj[6] != null){
                  empaddpayMaintainDTO.setNoteNum(obj[6].toString());
                }
                if(obj[8] != null){
                  empaddpayMaintainDTO.setOpTime(obj[8].toString());
                }
                empaddpayMaintainDTO.setPayMonth(obj[4].toString()+"-"+obj[5].toString());
                
                empaddpayMaintainDTO.setPay((new BigDecimal(obj[2].toString())).add(new BigDecimal(obj[3].toString())));
                empaddpayMaintainDTO.setPayStatus(obj[9].toString());
                empaddpayMaintainDTO.setId(obj[10].toString());
                empaddpayMaintainDTO.setPersonCounts(obj[12].toString());
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
  public List queryEmpaddpayAmount(final Serializable orgId, final String unitName,final String addPayAmount,
      final String bizStatus,final String beginMonth, final String payMonth,final String opTime,final SecurityInfo securityInfo,final String collBankId)    
       throws NumberFormatException, Exception {
    
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select sum(empAddPay.payMoney)" +                       
              " from EmpAddPay empAddPay, EmpAddPayBizActivityLog empAddPayBizActivityLog"+
              " where 1=1 and empAddPayBizActivityLog.bizid=empAddPay.id and " +
              " empAddPay.payStatus = empAddPayBizActivityLog.action and empAddPay.org.id "+securityInfo.getGjjSecurityHqlSQL();
                  

              Vector parameters = new Vector();
              String criterion = "";
              if (orgId != null&&!orgId.equals("")) {
                criterion += " to_char(empAddPay.org.id) like ? escape '/'  and ";
                parameters.add("%" + orgId.toString() + "%");
              }
              if (opTime != null&&!opTime.equals("")) {
                criterion += " (to_char(empAddPayBizActivityLog.opTime,'yyyymmdd')= ? )  and ";
                parameters.add(opTime);
              }
              
              if (unitName != null&&!unitName.equals("")) {
                criterion += " empAddPay.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + unitName + "%");
              }
              
              if (addPayAmount != null&&!addPayAmount.equals("0")) {
                
                criterion += "empAddPay.payMoney =? and ";
                parameters.add(new BigDecimal(addPayAmount));
              }
              
//              if (bizStatus != null&&!bizStatus.equals("")) {
//                criterion += " empAddPay.payStatus = ?  and ";
//                parameters.add(new Integer(bizStatus));
//              }else{
//                criterion += " (empAddPay.payStatus = 1 or empAddPay.payStatus = 2 ) and ";
//              }
              if (bizStatus == null||bizStatus.equals("")) {
                criterion += " (empAddPay.payStatus = 1 or empAddPay.payStatus = 2 ) and ";
  
              } else if(bizStatus.endsWith("all")){
                criterion += " (empAddPay.payStatus between 1 and 5) and ";
              } else{
                criterion += " empAddPay.payStatus = ?  and ";
                parameters.add(new Integer(bizStatus));
              }
//              if (beginMonth != null&&!beginMonth.equals("")&&endMonth!=null&&!endMonth.equals("")) {
//                criterion += " (addPayTail.addMonht between  ?  and  ? )  and ";
//             
//                parameters.add(beginMonth);
//                parameters.add(endMonth);
//              }
              
              if (payMonth != null&&!payMonth.equals("")) {
                criterion += " empAddPay.settDate = ?  and ";
                parameters.add(payMonth);
              }
              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " empAddPay.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion ;
             
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator it=query.iterate();
              Object obj=null;
              while(it.hasNext()){
                obj=(Object)it.next();
                EmpaddpayMaintainDTO empaddpayMaintainDTO=new EmpaddpayMaintainDTO();  
                if(obj!=null){
                  empaddpayMaintainDTO.setPay(new BigDecimal(obj.toString()));
                }
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
   * 
   */
  public int queryEmpaddpayListCount(final Serializable orgId, final String unitName,final String addPayAmount,
      final String bizStatus,final String beginMonth, final String payMonth, final String payMonth1,final String orderBy,final String order,
      final int start, final int pageSize,final String opTime,final String opTime1,final SecurityInfo securityInfo,final String collBankId)
       throws NumberFormatException, Exception {
    
    Integer list = null;
    int count = 0;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list =(Integer) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql =" select count(*) " +
              " from EmpAddPay empAddPay, EmpAddPayBizActivityLog empAddPayBizActivityLog"+
              " where 1=1 and empAddPayBizActivityLog.bizid=empAddPay.id and " +
              " empAddPay.payStatus = empAddPayBizActivityLog.action and empAddPay.org.id "+securityInfo.getGjjSecurityHqlSQL();
                  

              Vector parameters = new Vector();
              String criterion = "";
              if (orgId != null&&!orgId.equals("")) {
                criterion += " to_char(empAddPay.org.id) like ? escape '/'  and ";
                parameters.add("%" + orgId.toString() + "%");
              }

             if (opTime != null&&!opTime.equals("") && opTime1 != null && !opTime1.equals("")) {
              criterion += " (to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') between ?  and  ?) and ";
              parameters.add(opTime);
              parameters.add(opTime1);
            }else if (opTime != null&&!opTime.equals("")) {
              criterion += " to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') = ? and ";
              parameters.add(opTime);
            }else if (opTime1 != null && !opTime1.equals("")) {
              criterion += " to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') = ? and ";
              parameters.add(opTime1);
            }
              if (unitName != null&&!unitName.equals("")) {
                criterion += " empAddPay.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + unitName + "%");
              }
              
              if (addPayAmount != null&&!addPayAmount.equals("0")) {
                
                criterion += "empAddPay.payMoney =? and ";
                parameters.add(new BigDecimal(addPayAmount));
              }
              
//              if (bizStatus != null&&!bizStatus.equals("")) {
//                criterion += " empAddPay.payStatus = ?  and ";
//                parameters.add(new Integer(bizStatus));
//              }else{
//                criterion += " (empAddPay.payStatus = 1 or empAddPay.payStatus = 2 ) and ";
//              }
              if (bizStatus == null||bizStatus.equals("")) {
                criterion += " (empAddPay.payStatus = 1 or empAddPay.payStatus = 2 ) and ";
  
              } else if(bizStatus.endsWith("all")){
                criterion += " (empAddPay.payStatus between 1 and 5) and ";
              } else{
                criterion += " empAddPay.payStatus = ?  and ";
                parameters.add(new Integer(bizStatus));
              }
//              if (beginMonth != null&&!beginMonth.equals("")&&endMonth!=null&&!endMonth.equals("")) {
//                criterion += " (addPayTail.addMonht between  ?  and  ? )  and ";
//             
//                parameters.add(beginMonth);
//                parameters.add(endMonth);
//              }

              if (payMonth != null&&!payMonth.equals("") && payMonth1 != null && !payMonth1.equals("")) {
                criterion += " (empAddPay.settDate between ?  and  ? ) and ";
                parameters.add(payMonth);
                parameters.add(payMonth1);
              }else if (payMonth != null&&!payMonth.equals("")) {
                criterion += " empAddPay.settDate = ? and ";
                parameters.add(payMonth);
              }else if (payMonth1 != null && !payMonth1.equals("")) {
                criterion += " empAddPay.settDate = ? and ";
                parameters.add(payMonth1);
              }
              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " empAddPay.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion ;
           
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult();
            }
          }

      );    
      count = list.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return count;
  }
  
  //
  
  public List queryEmpaddpayList(final Serializable orgId, final String unitName,final String addPayAmount,
      final String bizStatus,final String beginMonth, final String endMonth)
       throws NumberFormatException, Exception {
    
    List list = null;
    try {
      list =(List)getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select empAddPay.org.id,empAddPay.org.orgInfo.name," +
                  " sum(addPayTail.orgAddMoney),sum(addPayTail.empAddMoney)," +
                  " min(addPayTail.addMonht),max(addPayTail.addMonht)," +
                  " empAddPay.noteNum,empAddPay.docNum,empAddPay.settDate, " +
                  " empAddPay.payStatus, empAddPay.id,empAddPay.payMoney ,count(addPayTail.id)" +
                  " from AddPayTail addPayTail,EmpAddPay empAddPay " +
                  " where addPayTail.paymentHead.id = empAddPay.id  " ;
                  

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
              
              if (addPayAmount != null&&!addPayAmount.equals("0")) {
                
                criterion += "empAddPay.payMoney =? and ";
                parameters.add(new BigDecimal(addPayAmount));
              }
              
              if (bizStatus != null&&!bizStatus.equals("")) {
                criterion += " empAddPay.payStatus = ?  and ";
                parameters.add(new Integer(bizStatus));
              }else{
                criterion += " (empAddPay.payStatus = 1 or empAddPay.payStatus = 2 ) and ";
              }
              
              if (beginMonth != null&&!beginMonth.equals("")&&endMonth!=null&&!endMonth.equals("")) {
                criterion += " (addPayTail.addMonht between  ?  and  ? )  and ";
             
                parameters.add(beginMonth);
                parameters.add(endMonth);
              }

          
              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion +" group by empAddPay.org.id,empAddPay.org.orgInfo.name," +
                  "empAddPay.noteNum,empAddPay.docNum,empAddPay.settDate," +
                  "empAddPay.payStatus,empAddPay.id,empAddPay.payMoney ";
           
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
                if(obj[7] != null){
                  empaddpayMaintainDTO.setDocNum(obj[7].toString());
                }
                if(obj[6] != null){
                  empaddpayMaintainDTO.setNoteNum(obj[6].toString());
                }
                if(obj[8] != null){
                  empaddpayMaintainDTO.setOpTime(obj[8].toString());
                }
                empaddpayMaintainDTO.setPayMonth(obj[4].toString()+"-"+obj[5].toString());
                empaddpayMaintainDTO.setOrgpay(new BigDecimal(obj[2].toString()));
                empaddpayMaintainDTO.setEmppay(new BigDecimal(obj[3].toString()));
                empaddpayMaintainDTO.setPay((new BigDecimal(obj[2].toString())).add(new BigDecimal(obj[3].toString())));
                empaddpayMaintainDTO.setPayStatus(obj[9].toString());
                empaddpayMaintainDTO.setId(obj[10].toString());
                empaddpayMaintainDTO.setPersonCounts(obj[12].toString());
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
   * 查询个人补缴列表记录数
   * @param id
   * @param name
   * @param status
   * @param inceptMonth
   * @param payMonth
   * @param payType
   * @param inceptPayMoney
   * @param payMoney
   * @param inceptSettlementDate
   * @param settlementDate
   * @return
   */
  public int queryEmpaddPayCountByCriterionsLg(final Serializable orgId, final String unitName,final String bizStatus,
      final String payMonth,final String settlementDate,final String payType) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select count(*) from AddPayTail addPayTail,EmpAddPay empAddPay,EmpAddPayBizActivityLog empAddPayBizActivityLog " +
                  "where addPayTail.paymentHead.id = empAddPay.id and  " +
                  "addPayTail.paymentHead.id=empAddPayBizActivityLog.bizid and empAddPayBizActivityLog = 2 ";
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



              if (payType != null&&!payType.equals("")) {
                criterion += " empAddPay.payStatus = ?  and ";
                parameters.add(new Integer(payType));
              }
//              else{
//                criterion += " (orgAddPay.payStatus = 1 or orgAddPay.payStatus = 2 ) and ";
//              }
              
              if (payMonth != null && !payMonth.equals("")) {
                criterion += " addPayTail.beginMonth = ? and ";
                parameters.add(payMonth);
              }
              
              if (settlementDate != null && !settlementDate.equals("")) {
                criterion += " (to_char(empAddPayBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(settlementDate);
              }
              if (criterion.length() != 0)
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion +" group by empAddPay.org.id,empAddPay.org.orgInfo.name," +
                  "empAddPay.noteNum,empAddPay.docNum,empAddPayBizActivityLog.opTime," +
                  "empAddPay.payStatus,empAddPay.id,empAddPay.payMoney,addPayTail.addMonht ";
              
              session.clear();
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.list();
            }
          }

      );
      count=list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
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
  public BigDecimal queryEmpaddPayMoneyByCriterionsLg(final Serializable id, final String name,final String status,
      final String payMonth,final String payType, final String payMoney,final String settlementDate,final String compare) {
    BigDecimal money=new BigDecimal(0);
    try {
      List list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select sum(addPayTail.empAddMoney+addPayTail.orgAddMoney) " +
                  "from AddPayTail addPayTail,EmpAddPay empAddPay,EmpAddPayBizActivityLog empAddPayBizActivityLog " +
                  "where addPayTail.paymentHead.id = empAddPay.id and  " +
                  "addPayTail.paymentHead.id=empAddPayBizActivityLog.bizid ";
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += " addPayTail.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(id.toString()));
              }
              

              if (criterion.length() != 0)
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion; //+" group by empAddPay.org.id,empAddPay.org.orgInfo.name," +
                 // "empAddPay.noteNum,empAddPay.docNum,empAddPayBizActivityLog.opTime," +
                 // "empAddPay.payStatus,empAddPay.id,empAddPay.payMoney,addPayTail.addMonht ";
              
              session.clear();
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
          money = money.add(new BigDecimal(obj.toString()));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return money;
  }
  
  public List queryEmpaddpayEmpList(final String paymentid,
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
              String hql =" from AddPayTail addPayTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (paymentid != null&&!paymentid.equals("")) {
                criterion += " addPayTail.paymentHead.id = ?  and ";
                parameters.add(new Integer(paymentid));
              }
              String ob = orderBy;
              if (ob == null)
                ob = " addPayTail.id ";

              String od = order;
              if (od == null)
                od = "DESC";
              if (criterion.length() != 0)
                criterion=" where "+
                criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion; //+ " order by " + ob + " " + od ;
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
  /**
   * 
   * 根据单位ID查询个人补缴清册记录数
   * @param orgid
   * @return
   */
  public int queryEmpaddpayEmpListCount(final String paymentid) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql =" from AddPayTail addPayTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (paymentid != null&&!paymentid.equals("")) {
                criterion += " addPayTail.paymentHead.id = ?  and ";
                parameters.add(new Integer(paymentid));
              }
              
              if (criterion.length() != 0)
                criterion=" where "+
                criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion ;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
    
              return query.list();
            }
          }

      );
      count=list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  public List queryEmpaddpayList(final String paymentid) {
    
    List list=new ArrayList();
    try {
      list =(List) getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql =" from AddPayTail addPayTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (paymentid != null&&!paymentid.equals("")) {
                criterion += " addPayTail.paymentHead.id = ?  and ";
                parameters.add(new Integer(paymentid));
              }
              
              if (criterion.length() != 0)
                criterion=" where "+
                criterion.substring(0, criterion.lastIndexOf("and"));
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
    return list;
  } 
  /**
   * 
   * 根据头表ID查询尾表清册
   * @param headId
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public Serializable queryEmpaddpayList(final Serializable headId) throws NumberFormatException, Exception {
    Serializable tailId = null;
    try {
      tailId = (Serializable)getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql ="select max(addPayTail.id) from AddPayTail addPayTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null&&!headId.equals("")) {
                criterion += " addPayTail.paymentHead.id = ?  and ";
                parameters.add(new Integer(headId.toString()));
              }
              
              if (criterion.length() != 0)
                criterion=" where  "+
                criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion ;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }     
              
              return query.uniqueResult();
            }
          }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return tailId;
  }
  public List queryEmpList(final Serializable headId) throws NumberFormatException, Exception {
   List list=new ArrayList();
    try {
      list = (List)getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql =" from AddPayTail addPayTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null&&!headId.equals("")) {
                criterion += " addPayTail.paymentHead.id = ?  and ";
                parameters.add(new Integer(headId.toString()));
              }
              
              if (criterion.length() != 0)
                criterion=" where  "+
                criterion.substring(0, criterion.lastIndexOf("and"));
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

    return list;
  }
  public void delete(AddPayTail addPayTail){
    Validate.notNull(addPayTail);
    getHibernateTemplate().delete(addPayTail);
  }
  public List queryByEmpIdLg(final Integer id,final Integer paymentHeadId){
    Validate.notNull(id);
    List list=new ArrayList();
    try{
    list=(List)getHibernateTemplate().executeFind(
        new HibernateCallback() {

          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {

            String hql = "from AddPayTail addPayTail  ";
            Vector parameters = new Vector();
            String criterion = "";

            if (id != null) {
              criterion += "  addPayTail.empId=?  and  ";
              parameters.add(id);
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
  
  /**
   * 于庆丰
   * @param paymentHeadId
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public List queryByHeadId(final Integer paymentHeadId,final int start,
      final int pageSize,final String orderBy, final String order){
 List personAddPayList = null;
 try {
   Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
       || "DESC".equalsIgnoreCase(order));
   Validate.isTrue(start >= 0);
   personAddPayList = getHibernateTemplate().executeFind(
       new HibernateCallback() {

         public Object doInHibernate(Session session)
             throws HibernateException, SQLException {

           String hql = " from AddPayTail addPayTail ";
           Vector parameters = new Vector();
           String criterion = "";

           if (paymentHeadId != null) {
             criterion += "  addPayTail.paymentHead.id=?  and  ";
             parameters.add(paymentHeadId);
           }

           if (criterion.length() != 0)
             criterion = " where "  
                 + criterion.substring(0, criterion.lastIndexOf(" and "));

           String ob = orderBy;
           if (ob == null)
             ob = " addPayTail.id ";

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
       }

   );

 } catch (Exception e) {
   e.printStackTrace();
 }
 return personAddPayList;
}
  
  /**
   * 于庆丰 根据头表ID查询尾表到账确认人数
   * @param monthPaymentId
   * @return
   */
  public Integer queryPaymentPersonCountsYQF(final Serializable paymentheadId){
    Integer empCount  = (Integer) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String sql = "";
        if(paymentheadId != null && !"".equals(paymentheadId)){
           sql = "select count(distinct a304.emp_id) from AA304 a304,AA301 a301 where  a304.pay_head_id = a301.id and a301.id="+paymentheadId;              
        }
        Query query = session.createSQLQuery(sql);
        return new Integer(query.uniqueResult().toString());
       
      }
    });
  
    return empCount;
    
  }
  /**
   * 王硕
   * @param paymentHeadId
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public List queryByHeadId_wsh(final String paymentHeadId){
 List personAddPayList = null;
 try {
  
   personAddPayList = getHibernateTemplate().executeFind(
       new HibernateCallback() {

         public Object doInHibernate(Session session)
             throws HibernateException, SQLException {

           String hql ="select a.emp_id, sum(a.org_add_money), sum(a.emp_add_money), max(a.add_reason) from aa304 a, aa301 b where a.pay_head_id = b.id and b.note_num= ? group by a.emp_id ";
           Vector parameters = new Vector();
           String criterion = "";

           if (paymentHeadId != null) {
             parameters.add(paymentHeadId);
           }

          
       
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
 return personAddPayList;
}
}