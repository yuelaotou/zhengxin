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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPayment;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayMaintainDto;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.dto.OrgExcessPaymentDTO;

/**
 * 单位挂账
 * 
 *@author 李娟
 *2007-6-20
 */
public class OrgExcessPaymentDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public OrgExcessPayment queryById( final Serializable id) {  
    Validate.notNull(id);
    return  (OrgExcessPayment) getHibernateTemplate().get(OrgExcessPayment.class,id);    
  }
  /**
   * 
   * @param id
   * @return
   */
  public OrgExcessPayment findById(final Serializable id) {  
    OrgExcessPayment orgExcessPayment = null;
    orgExcessPayment = (OrgExcessPayment) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from OrgExcessPayment orgExcessPayment ";
        Vector parameters = new Vector();
        String criterion = "";
 
        if (id != null&&!id.equals("")) {
          criterion += "orgExcessPayment.id = ?  and ";
          parameters.add(new Integer(id.toString()));
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
    if(orgExcessPayment == null){
      orgExcessPayment = new OrgExcessPayment();
    }
    return orgExcessPayment;
  } 
  
  /**
   * 插入记录
   * @param orgExcessPayment
   * @return
   */
  public Serializable insert(OrgExcessPayment orgExcessPayment){
    Serializable id = null;
    try{    
    Validate.notNull(orgExcessPayment);
    id = getHibernateTemplate().save(orgExcessPayment);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 根据单位ID缴存状态查找
   * @param payStatus
   * @return
   */

  public OrgExcessPayment findOrgoverpayInfo(final Serializable orgid,final Integer payStatus ){
    OrgExcessPayment orgExcessPayment = null;
    orgExcessPayment = (OrgExcessPayment) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from OrgExcessPayment orgExcessPayment ";
        Vector parameters = new Vector();
        String criterion = "";
 
        if (orgid != null&&!orgid.equals("")) {
          criterion += "orgExcessPayment.org.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
        }

        if (payStatus != null&&!payStatus.equals("")) {
          criterion += "orgExcessPayment.payStatus = ?  and ";
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
        return query.uniqueResult();
      }
    });
//    if(orgExcessPayment == null){
//      orgExcessPayment = new OrgExcessPayment();
//    }
    return orgExcessPayment;
  }
  
  public List queryOrgoverpayList(final String orgId,final String unitName,final String amount,
      final String  payStatus,final String payMonth,final String payMonth1, final int start,final int pageSize,
      final String orderBy, final String order,final int page,final String opTime,final String opTime1,final SecurityInfo securityInfo){
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        List temp_list=new ArrayList();
        String hql = "select orgExcessPayment.noteNum,orgExcessPayment.docNum,orgExcessPayment.org.id,orgExcessPayment.org.orgInfo.name," +
            " orgExcessPayment.payMoney,orgExcessPayment.payStatus,orgExcessPayment.settDate,to_char(orgExcessPaymentBizActivityLog.opTime,'yyyymmdd'),orgExcessPayment.id " +
            "  from OrgExcessPayment orgExcessPayment,OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog " +
            " where orgExcessPayment.id = orgExcessPaymentBizActivityLog.bizid and orgExcessPayment.payStatus = orgExcessPaymentBizActivityLog.action " +
            " and orgExcessPayment.org.id "+securityInfo.getGjjSecurityHqlSQL();
        Vector parameters = new Vector();
        String criterion = "";
        if (orgId != null&&!orgId.equals("")) {
//          criterion += " orgExcessPayment.org.id = ?  and ";
//          parameters.add(new Integer(orgId));
          criterion += " to_char(orgExcessPayment.org.id) like ? escape '/'  and ";
          parameters.add("%" + orgId.toString() + "%"); 
        }
        
        if (opTime != null&&!opTime.equals("") && opTime1 != null && !opTime1.equals("")) {
          criterion += " (to_char(orgExcessPaymentBizActivityLog.opTime,'yyyymmdd') between ?  and  ?) and ";
          parameters.add(opTime);
          parameters.add(opTime1);
        }else if (opTime != null&&!opTime.equals("")) {
          criterion += " to_char(orgExcessPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
          parameters.add(opTime);
        }else if (opTime1 != null && !opTime1.equals("")) {
          criterion += " to_char(orgExcessPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
          parameters.add(opTime1);
        }
        if (unitName != null&&!unitName.equals("")) {
          criterion += " orgExcessPayment.org.orgInfo.name = ?  and ";
          parameters.add(unitName);
        }
        if (amount != null&&!amount.equals("")) {
          criterion += " orgExcessPayment.payMoney = ?  and ";
          parameters.add(new BigDecimal(amount));
        }
//        if (payStatus != null&&!payStatus.equals("")) {
//          criterion += " orgExcessPayment.payStatus = ?  and ";
//          parameters.add(new Integer(payStatus));
//        }else{
//          criterion += " orgExcessPayment.payStatus = 2  and ";      
//        }
        if (payStatus == null||payStatus.equals("")) {
          criterion += " orgExcessPayment.payStatus = 2  and ";
          
        }else if(payStatus.endsWith("all")){
          criterion += " orgExcessPayment.payStatus > 0  and ";
         
        }else{
          criterion += " orgExcessPayment.payStatus = ?  and ";
          parameters.add(new Integer(payStatus));
        }
        if (payMonth != null&&!payMonth.equals("")&&payMonth1!=null&&!payMonth1.equals("")) {
          criterion += " (orgExcessPayment.settDate between ?  and  ? ) and ";
          parameters.add(payMonth);
          parameters.add(payMonth1);
        }else if (payMonth != null&&!payMonth.equals("")) {
          criterion += " (orgExcessPayment.settDate = ? and ";
          parameters.add(payMonth);
        }else if (payMonth1!=null&&!payMonth1.equals("")) {
          criterion += " (orgExcessPayment.settDate = ?  and ";
          parameters.add(payMonth1);
        }

        String ob = orderBy;
        if (ob == null)
          ob = "orgExcessPayment.id ";

        String od = order;
        if (od == null)
          od = "ASC";
        
        if (criterion.length() != 0)
            criterion = " and "
            + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion +" group by orgExcessPayment.org.id,orgExcessPayment.org.orgInfo.name,orgExcessPaymentBizActivityLog.bizid," +
        "orgExcessPayment.noteNum,orgExcessPayment.docNum,orgExcessPaymentBizActivityLog.opTime," +
        "orgExcessPayment.payStatus,orgExcessPayment.id,orgExcessPayment.payMoney,orgExcessPayment.settDate "+ "order by " + ob + " " + od ;
        
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }

        query.setFirstResult(start);
        if (pageSize > 0)
         query.setMaxResults(pageSize); 
        
        List queryList=query.list();
        Iterator it=query.iterate();
        if(queryList==null||queryList.size()==0){           
          query.setFirstResult(pageSize*(page-2));
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          queryList=query.list();
        }

        Object obj[]=null;
        while(it.hasNext()){
          obj=(Object[])it.next();
          OrgExcessPaymentDTO orgExcessPaymentDTO=new OrgExcessPaymentDTO();
          orgExcessPaymentDTO.setOrgId(new Integer(obj[2].toString()));
          orgExcessPaymentDTO.setOrgName(obj[3].toString());
          if(obj[1] != null){
            orgExcessPaymentDTO.setDocNum(obj[1].toString());
          }
          if(obj[0] != null){
            orgExcessPaymentDTO.setNoteNum(obj[0].toString());
          }
          orgExcessPaymentDTO.setPayMonth(obj[3].toString());
          orgExcessPaymentDTO.setOpTime(obj[7].toString());
          orgExcessPaymentDTO.setPayMoney(new BigDecimal(obj[4].toString()).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
          orgExcessPaymentDTO.setPayStatus(obj[5].toString());
          orgExcessPaymentDTO.setId(obj[8].toString());
          if(obj[6] != null){
            orgExcessPaymentDTO.setSettDate(obj[6].toString());
          }
          temp_list.add(orgExcessPaymentDTO);
        }
        return temp_list;
      }
    });
    
    return list;
  }
  //
  public BigDecimal queryOrgoverpayAmount(final String orgId,final String unitName,final String amount,
      final String  payStatus,final String payMonth,final String opTime,final String payMonth1,final String opTime1,final SecurityInfo securityInfo){  
    BigDecimal money = null;
    money = (BigDecimal) getHibernateTemplate().execute(new HibernateCallback() {   
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "select sum(orgExcessPayment.payMoney) from OrgExcessPayment orgExcessPayment," +
            "OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog  "+
        " where orgExcessPayment.id = orgExcessPaymentBizActivityLog.bizid and orgExcessPayment.payStatus = orgExcessPaymentBizActivityLog.action " +
        " and orgExcessPayment.org.id "+securityInfo.getGjjSecurityHqlSQL();
        Vector parameters = new Vector();
        String criterion = "";
        if (orgId != null&&!orgId.equals("")) {
          criterion += " orgExcessPayment.org.id = ?  and ";
          parameters.add(new Integer(orgId));
        }
        if (unitName != null&&!unitName.equals("")) {
          criterion += " orgExcessPayment.org.orgInfo.name = ?  and ";
          parameters.add(unitName);
        }
        if (amount != null&&!amount.equals("")) {
          criterion += " orgExcessPayment.payMoney = ?  and ";
          parameters.add(new BigDecimal(amount));
        }
//        if (payStatus != null&&!payStatus.equals("")) {
//          criterion += " orgExcessPayment.payStatus = ?  and ";
//          parameters.add(new Integer(payStatus));
//        }else{
//          criterion += " orgExcessPayment.payStatus = 2  and ";      
//        }

        if (opTime != null&&!opTime.equals("") && opTime1 != null && !opTime1.equals("")) {
          criterion += " (to_char(orgExcessPaymentBizActivityLog.opTime,'yyyymmdd') between ?  and  ?) and ";
          parameters.add(opTime);
          parameters.add(opTime1);
        }else if (opTime != null&&!opTime.equals("")) {
          criterion += " to_char(orgExcessPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
          parameters.add(opTime);
        }else if (opTime1 != null && !opTime1.equals("")) {
          criterion += " to_char(orgExcessPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
          parameters.add(opTime1);
        }
        if (payStatus == null||payStatus.equals("")) {
          criterion += " orgExcessPayment.payStatus = 2  and ";
          
        }else if(payStatus.endsWith("all")){
          criterion += " orgExcessPayment.payStatus > 0  and ";
         
        }else{
          criterion += " orgExcessPayment.payStatus = ?  and ";
          parameters.add(new Integer(payStatus));
        }
        if (payMonth != null&&!payMonth.equals("") && payMonth1 != null && !payMonth1.equals("")) {
          criterion += " (orgExcessPayment.settDate between ?  and  ? ) and ";
          parameters.add(payMonth);
          parameters.add(payMonth1);
        }else if (payMonth != null&&!payMonth.equals("")) {
          criterion += " orgExcessPayment.settDate = ? and ";
          parameters.add(payMonth);
        }else if (payMonth1 != null && !payMonth1.equals("")) {
          criterion += " orgExcessPayment.settDate = ? and ";
          parameters.add(payMonth1);
        }
        if (criterion.length() != 0)
            criterion = " and "
            + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql+ criterion;
        session.clear();
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }   
        return query.uniqueResult();
      }
    });
    
    return money;
  }
  //count
  public int queryOrgoverpayCount(final String orgId,final String unitName,final String amount,
      final String payStatus,final String payMonth,final String payMonth1,final String opTime,final String opTime1, final SecurityInfo securityInfo){
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "select orgExcessPayment.noteNum,orgExcessPayment.docNum,orgExcessPayment.org.id,orgExcessPayment.org.orgInfo.name," +
        " orgExcessPayment.payMoney,orgExcessPayment.payStatus,orgExcessPayment.settDate,orgExcessPaymentBizActivityLog.opTime,orgExcessPayment.id " +
        "  from OrgExcessPayment orgExcessPayment,OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog " +
        " where orgExcessPayment.id = orgExcessPaymentBizActivityLog.bizid and orgExcessPayment.payStatus = orgExcessPaymentBizActivityLog.action " +
        " and orgExcessPayment.org.id "+securityInfo.getGjjSecurityHqlSQL();
    Vector parameters = new Vector();
    String criterion = "";
    if (orgId != null&&!orgId.equals("")) {
//      criterion += " orgExcessPayment.org.id = ?  and ";
//      parameters.add(new Integer(orgId));
      criterion += " to_char(orgExcessPayment.org.id) like ? escape '/'  and ";
      parameters.add("%" + orgId.toString() + "%"); 
    }

    if (opTime != null&&!opTime.equals("") && opTime1 != null && !opTime1.equals("")) {
      criterion += " (to_char(orgExcessPaymentBizActivityLog.opTime,'yyyymmdd') between ?  and  ?) and ";
      parameters.add(opTime);
      parameters.add(opTime1);
    }else if (opTime != null&&!opTime.equals("")) {
      criterion += " to_char(orgExcessPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
      parameters.add(opTime);
    }else if (opTime1 != null && !opTime1.equals("")) {
      criterion += " to_char(orgExcessPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
      parameters.add(opTime1);
    }
    if (unitName != null&&!unitName.equals("")) {
      criterion += " orgExcessPayment.org.orgInfo.name = ?  and ";
      parameters.add(unitName);
    }
    if (amount != null&&!amount.equals("")) {
      criterion += " orgExcessPayment.payMoney = ?  and ";
      parameters.add(new BigDecimal(amount));
    }
//    if (payStatus != null&&!payStatus.equals("")) {
//      criterion += " orgExcessPayment.payStatus = ?  and ";
//      parameters.add(new Integer(payStatus));
//    }else{
//      criterion += " orgExcessPayment.payStatus = 2  and ";      
//    }
    if (payStatus == null||payStatus.equals("")) {
      criterion += " orgExcessPayment.payStatus = 2  and ";
      
    }else if(payStatus.endsWith("all")){
      criterion += " orgExcessPayment.payStatus > 0  and ";
     
    }else{
      criterion += " orgExcessPayment.payStatus = ?  and ";
      parameters.add(new Integer(payStatus));
    }

    if (payMonth != null&&!payMonth.equals("") && payMonth1 != null && !payMonth1.equals("")) {
      criterion += " (orgExcessPayment.settDate between ?  and  ? ) and ";
      parameters.add(payMonth);
      parameters.add(payMonth1);
    }else if (payMonth != null&&!payMonth.equals("")) {
      criterion += " orgExcessPayment.settDate = ? and ";
      parameters.add(payMonth);
    }else if (payMonth1 != null && !payMonth1.equals("")) {
      criterion += " orgExcessPayment.settDate = ? and ";
      parameters.add(payMonth1);
    }
    if (criterion.length() != 0)
        criterion = " and "
        + criterion.substring(0, criterion.lastIndexOf("and"));
    hql = hql + criterion +" group by orgExcessPayment.org.id,orgExcessPayment.org.orgInfo.name,orgExcessPaymentBizActivityLog.bizid," +
    "orgExcessPayment.noteNum,orgExcessPayment.docNum,orgExcessPaymentBizActivityLog.opTime," +
    "orgExcessPayment.payStatus,orgExcessPayment.id,orgExcessPayment.payMoney,orgExcessPayment.settDate ";
    
    Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
      }
    });

    return list.size();
  }
  
  public void deleteById(Serializable id) {  
    Validate.notNull(id);
    OrgExcessPayment orgExcessPayment=this.queryById(id);
    this.getHibernateTemplate().delete(orgExcessPayment);
  } 
  
  public OrgExcessPayment queryByHeadId(final Serializable orgid){
    OrgExcessPayment orgExcessPayment = null;
    orgExcessPayment = (OrgExcessPayment) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "from OrgExcessPayment orgExcessPayment ";
        Vector parameters = new Vector();
        String criterion = "";
 
        if (orgid != null&&!orgid.equals("")) {
          criterion += "orgExcessPayment.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
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

    return orgExcessPayment;
  }
  public void delete(OrgExcessPayment orgExcessPayment) {  
    Validate.notNull(orgExcessPayment);
    this.getHibernateTemplate().delete(orgExcessPayment);
  } 
  public void update(OrgExcessPayment orgExcessPayment) {  
    Validate.notNull(orgExcessPayment);
    this.getHibernateTemplate().update(orgExcessPayment);
  }
  
  /**
   * 根据单位编号  看是否存在缴存类型为D的并且缴存状态不等于5的数据
   * @param orgId
   * @param securityInfo
   * @return
   */
  public int queryOrgoverpayId(final String orgId,final SecurityInfo securityInfo){
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "select a301.id, a301.org_id from aa301 a301 " +
            " where a301.org_id = ? and a301.pay_type = 'D' and " +
            " ( a301.pay_status <= 4 or a301.pay_status >=6 ) " +
            " and a301.org_id "+securityInfo.getGjjSecuritySQL();
        
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, orgId);
        return query.list();
      }
    });
    return list.size();
  }
  /**
   * ld_add
   * 用来测试数据库中是否存在要删除的数据
   */
  public List testquery(final String id){
    List list=null;
    try{
      list=getHibernateTemplate().executeFind(new HibernateCallback(){

        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          String sql="select a301.org_id from aa301 a301 where a301.id=?";
          Query query=session.createSQLQuery(sql);
          query.setParameter(0, id);
         // Object obj[]=null;
//          Iterator it=query.list().iterator();
          //List templist=new ArrayList();
//          while(it.hasNext()){
            
          return query.list();
        }});
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 查询单位余额
   * 
   */
  public BigDecimal queryOrgBalanceByOrgID(final Serializable orgId) {
    BigDecimal sumPay = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(a2.pre_balance+a2.cur_balance) from aa002 a2   "
                + " where a2.org_id = ? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, orgId);
            return query.uniqueResult();
          }
        });
    return sumPay;
  }
  
  
  /*
   * 查询记账员
   */
  public String queryMaker(final Serializable  id ){
    String status="";
    try{
       status=(String)getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String status;
                String  hql = "select aa319.operator from  aa319  aa319  where aa319.type='C' and aa319.action='2' and  aa319.bizid=? ";              
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
//  /**
//   * 根据单位编号  看是否存在缴存类型为D的并且缴存状态不等于5的数据
//   * @param orgId
//   * @param securityInfo
//   * @return
//   */
//  public int queryOrgoverpayId_1(final String orgId,final String account,final SecurityInfo securityInfo){
//    List list = null;
//    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
//      
//      public Object doInHibernate(Session session)
//          throws HibernateException, SQLException {
//        String hql = "select count(*) " +
//            "from( (select case when ((select count(*) from aa301 where aa301.note_num=?)>0  and aa301.pay_money+? >=0) then 1 " +
//            "else 2 end b from aa301 where aa301.pay_type in('A','B','C')) )a where a.b=1 " +
//            " and aa301.org_id "+securityInfo.getGjjSecuritySQL();
//        
//        Query query = session.createSQLQuery(hql);
//        query.setParameter(0, orgId);
//        query.setInteger(1, Integer.parseInt(account));
//        return query.list();
//      }
//    });
//    return list.size();
//  }
  
  public int queryOrgoverpayId_1(final String orgId,final String account,final SecurityInfo securityInfo){
    String status="";
    try{
       status=(String)getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String status;
              String hql = "select count(*) " +
              "from( (select case when (select count(*)  from aa301 d  where (select count(*) from aa301 c where c.note_num =? and c.id = d.id) > 0 and d.pay_money +? >= 0) > 0 then 1  " +
              "else 2 end b from aa301 where aa301.pay_type in('A','B','C') 　and aa301.note_num = ?　and aa301.org_id "+securityInfo.getGjjSecuritySQL()+") )a where a.b=1 "
              ;
          
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, orgId);
          query.setParameter(1, account);
          query.setParameter(2, orgId);
                status = query.uniqueResult().toString();            
                return status;           
            }
          }
      );
    }catch(Exception s){
      s.printStackTrace();
    }
    return Integer.parseInt(status);
  } 
  
  public String queryOrgOverPayAccount(final String  orgId,final SecurityInfo securityInfo ){
    String status="";
    try{
       status=(String)getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String status;
                String  hql = "select nvl(sum(aa301.pay_money),0) from aa301 where aa301.pay_money<0 and aa301.pay_status!=5 and aa301.pay_type='D' and aa301.org_id=? and aa301.org_id " +securityInfo.getGjjSecuritySQL();              
                Query query = session.createSQLQuery(hql);
                query.setParameter(0, orgId);
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