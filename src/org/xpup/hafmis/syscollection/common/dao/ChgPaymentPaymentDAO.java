package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentSalaryBase;

public  class ChgPaymentPaymentDAO extends HibernateDaoSupport{

  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public ChgPaymentPayment queryById(Integer id) {
    Validate.notNull(id);
    return  (ChgPaymentPayment) getHibernateTemplate().get(ChgPaymentPayment.class,id);    
  }
  public void queryByIdGJP(ChgPaymentPayment chgPaymentPayment)
  throws NumberFormatException, Exception {
    ChgPaymentPayment chgPaymentPayment1=(ChgPaymentPayment)getHibernateTemplate().load(ChgPaymentPayment.class, chgPaymentPayment.getId());
    chgPaymentPayment1.setChgStatus(chgPaymentPayment.getChgStatus());
}
  /**
   * 插入记录
   * @param chgOrgRate
   * @return
   */
  public Serializable insert(ChgPaymentPayment chgPaymentPayment){
    Serializable id = null;
    try{    
    Validate.notNull(chgPaymentPayment);
    id = getHibernateTemplate().save(chgPaymentPayment);
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  
  
  /**
   * 根据主键删除单个记录
   * @return
   * 吴洪涛 
   * 6.30
   */
  public void delete(ChgPaymentPayment chgPaymentPayment){
    Validate.notNull(chgPaymentPayment);
    getHibernateTemplate().delete(chgPaymentPayment);
  }
  /**
   * 查询记录
   * 吴洪涛
   * 2007.6.27
   * @return boolean:false－未启用；true－启用
   */
  public boolean getChgStatus(final Integer orgId) {
    Validate.notNull(orgId);

    boolean chgStatus = false;
    Integer TEMP_status = (Integer)getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select chgPaymentPayment.chgStatus from ChgPaymentPayment chgPaymentPayment where chgPaymentPayment.chgStatus=1 and chgPaymentPayment.org.id=? ";
        Vector parameters = new Vector();
        parameters.add(orgId);
       
        Query query = session.createQuery(hql);
        query.setParameter(0, parameters.get(0));
        
        return query.uniqueResult();
      }
    });
    if(TEMP_status==null){
      chgStatus=true;
    }else{
      if(TEMP_status.equals(new Integer(1))){
        chgStatus=false;
      }else{
        chgStatus=true;
      }
    }
 
    return chgStatus;
  }
  
  /**
   * 查询记录
   * 吴洪涛
   * 2007.6.27
   * @return ChgPaymentPayment下的单位信息aa202
   */
  public ChgPaymentPayment queryByCriterionsWuht(final String id) {

    ChgPaymentPayment chgPaymentPayment = null;
    chgPaymentPayment = (ChgPaymentPayment) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {

        

            String hql = "select chgPaymentPayment from ChgPaymentPayment chgPaymentPayment  ";

            Vector parameters = new Vector();
            String criterion = "";
            if (id != null) {
              criterion += "chgPaymentPayment.org.id = ? and ";
              parameters.add(new Integer(id));
            }
        
            if (criterion.length() != 0) 
              criterion = "where chgPaymentPayment.chgStatus=1 and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;

            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }
   
  

     
            return  query0.uniqueResult();
          }
        });

    return chgPaymentPayment;
  }
  
  
  /**
   * 根据输入的条件查询单位信息(List)
   * type=A(ChgPaymentSalaryBase)
   * @param String id,
   * @param String    chgMonth
   * @return 吴洪涛
   */ 
  public List queryChgPaymentPaymentWuht(final String id,final String name, final String chgMonth,
      final String orderBy, final String order, final int start,
      final int pageSize,final int page,final SecurityInfo securityInfo) {
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
       
        String hql = "select chgPaymentPayment from ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null && !chgMonth.equals("")) {
    
          criterion += "chgPaymentPayment.chgMonth = ?  and ";
          parameters.add(chgMonth );
        }
        
        
        if (name != null && !name.equals("")) {
    
          criterion += "chgPaymentPayment.org.orgInfo.name like ?  and ";
          parameters.add("%" + name + "%");
        }
        
        if (id != null && !id.equals("")) {
          criterion += " To_Char(chgPaymentPayment.org.id) like ? and ";
          parameters.add("%" + id + "%");
        }

        if (criterion.length() != 0){
          criterion = "where  chgPaymentPayment.org.payMode=2 and ( chgPaymentPayment.chgStatus=1 or ( chgPaymentPayment.chgStatus=2 and  chgPaymentPayment.paymentHead.id is null)) and chgPaymentPayment.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        }
        else{
          criterion = "where chgPaymentPayment.org.payMode=2 and ( chgPaymentPayment.chgStatus=1 or (chgPaymentPayment.chgStatus=2  and chgPaymentPayment.paymentHead.id  is null )) and chgPaymentPayment.org.id "+securityInfo.getGjjSecurityHqlSQL() ;
        }
        String ob = orderBy;
        if (ob == null)
          ob = " chgPaymentPayment.chgStatus ASC,chgPaymentPayment.id DESC ";

        String od = order;
        if (od == null)
          od = "";
        hql = hql + criterion + "order by " + ob + " " + od;

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
        
        
        return query.list();
      }
    }

    );
//111111111111111111111111111
    return orglist;
  }

  public int queryChgPaymentPaymentWuht(final String id,final String name,
      final String chgMonth,final SecurityInfo securityInfo) {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
      
          String hql = "select chgPaymentPayment from ChgPaymentPayment chgPaymentPayment ";
          String criterion = "";
          Vector parameters = new Vector();
          if (chgMonth != null && !chgMonth.equals("")) {
            criterion += "chgPaymentPayment.chgMonth = ?  and ";
            parameters.add(chgMonth );
          }
          
          if (name != null && !name.equals("")) {
            criterion += "chgPaymentPayment.org.orgInfo.name like ?  and ";
            parameters.add("%" + name + "%");
          }
          
          
          if (id != null && !id.equals("")) {
            criterion += " To_Char(chgPaymentPayment.org.id) like ? and ";
            parameters.add("%" + id + "%");
          }
          if (criterion.length() != 0){
            criterion = "where  chgPaymentPayment.org.payMode=2 and ( chgPaymentPayment.chgStatus=1 or ( chgPaymentPayment.chgStatus=2 and  chgPaymentPayment.paymentHead.id is null)) and chgPaymentPayment.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }
          else{
            criterion = "where chgPaymentPayment.org.payMode=2 and ( chgPaymentPayment.chgStatus=1 or (chgPaymentPayment.chgStatus=2  and chgPaymentPayment.paymentHead.id  is null )) and chgPaymentPayment.org.id "+securityInfo.getGjjSecurityHqlSQL() ;
          }

          hql = hql + criterion;

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  
  /**
   * 根据输入的条件查询单位信息(chgPaymentPayment)
   * id
   *chgPaymentPayment.chgStatus=1
   * @return 吴洪涛
   */ 
  public ChgPaymentPayment queryChgPaymentPaymentByIdWuht(final String id,final String chgStatus) {

    ChgPaymentPayment chgPaymentPayment = null;
    chgPaymentPayment = (ChgPaymentPayment) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {    
            String hql = "select chgPaymentPayment from ChgPaymentPayment chgPaymentPayment  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (id != null) {
              criterion += "chgPaymentPayment.id = ? and ";
              parameters.add(new Integer(id));
            }        
            if (chgStatus != null) {
              criterion += "chgPaymentPayment.chgStatus= ? and ";
              parameters.add(new Integer(chgStatus));
            }   
            if (criterion.length() != 0){
              criterion = "where   " 
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }
            hql = hql + criterion;

            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }     
            return  query0.uniqueResult();
          }
        });

    return chgPaymentPayment;
  }
  /**
   * 根据输入的条件查询单位信息(List)
   * type=A(ChgPaymentSalaryBase)
   * @param String id,
   * @param String    chgMonth
   * 无默认的查询全部查询
   * @return 吴洪涛
   */ 
  public List queryChgPaymentPaymentByCriterionsWuht(final String id,final String name, final String chgMonth,
      final String orderBy, final String order, final int start,
      final int pageSize,final int page,final SecurityInfo securityInfo)  {

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
       
        String hql = "select chgPaymentPayment from ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null && !chgMonth.equals("")) {
          criterion += "chgPaymentPayment.chgMonth = ?  and ";
          parameters.add(chgMonth );
        }
        
        
        if (name != null && !name.equals("")) {
          criterion += "chgPaymentPayment.org.orgInfo.name like ?  and ";
          parameters.add("%" + name + "%");
        }
        if (id != null && !id.equals("")) {
          criterion += " To_Char(chgPaymentPayment.org.id) like ? and ";
          parameters.add("%" + id + "%");
        }
        if (criterion.length() != 0){
          criterion = "where  chgPaymentPayment.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        }
        else{
          criterion = " where chgPaymentPayment.org.id "+securityInfo.getGjjSecurityHqlSQL();
        }
        String ob = orderBy;
        if (ob == null)
          ob = " chgPaymentPayment.chgStatus ASC,chgPaymentPayment.id DESC ";

        String od = order;
        if (od == null)
          od = "";
        hql = hql + criterion + "order by " + ob + " " + od;

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
        return query.list();
      }
    }
//11111111111111111111111111111
    );

    return orglist;
  }
  public int queryChgPaymentPaymentByCriterionsCountWuht(final String id,final String name,
      final String chgMonth,final SecurityInfo securityInfo)  {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
      
          String hql = "select chgPaymentPayment from ChgPaymentPayment chgPaymentPayment ";
          String criterion = "";
          Vector parameters = new Vector();
          if (chgMonth != null && !chgMonth.equals("")) {
            criterion += "chgPaymentPayment.chgMonth = ?  and ";
            parameters.add(chgMonth );
          }
          
          if (name != null && !name.equals("")) {
            criterion += "chgPaymentPayment.org.orgInfo.name like ?  and ";
            parameters.add("%" + name + "%");
          }
          
          if (id != null && !id.equals("")) {
            criterion += " To_Char(chgPaymentPayment.org.id) like ? and ";
            parameters.add("%" + id + "%");
          }
        
          if (criterion.length() != 0){
            criterion = "where  chgPaymentPayment.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }
          else{
            criterion = " where chgPaymentPayment.org.id "+securityInfo.getGjjSecurityHqlSQL();
          }

          hql = hql + criterion;

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  

  
  /**
   * 根据输入的条件查询单位信息(List) type=A(ChgPaymentPayment)
   * 统计查询的变更信息的缴额调整
   *   final String orgId,
      final String orgName, final String officecode,
      final String collectionBankId, final String startChgMonth,
      final String endChgMonth, final String startBizDate,
      final String endBizDate, final String chgStatus,
   * @return 吴洪涛
   */
  public List queryChgPaymentPaymentQueryWuht(final String orgId,
      final String orgName, final String officecode,
      final String collectionBankId, final String startChgMonth,
      final String endChgMonth, final String startBizDate,
      final String endBizDate, final String chgStatus, final String orderBy,
      final String order, final int start, final int pageSize,final SecurityInfo securityInfo) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = " from ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (officecode != null && !officecode.equals("")) {
          criterion += "chgPaymentPayment.org.orgInfo.officecode = ?  and ";
          parameters.add(officecode);
        }
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentPayment.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentPayment.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// 开始日期
          criterion += " (chgPaymentPayment.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentPayment.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentPayment.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// 开始日期
          criterion += " (chgPaymentPayment.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        if (chgStatus != null && !chgStatus.equals("")) {
          criterion += "chgPaymentPayment.chgStatus = ?  and ";
          parameters.add(new Integer(chgStatus));
        }
        
        if (collectionBankId != null && !collectionBankId.equals("")) {
          criterion += "chgPaymentPayment.org.orgInfo.collectionBankId = ?  and ";
          parameters.add(collectionBankId);
        }
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentPayment.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += "  chgPaymentPayment.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

        if (criterion.length() != 0)
          criterion = "where   chgPaymentPayment.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentPayment.org.orgInfo.officecode DESC, chgPaymentPayment.org.orgInfo.collectionBankId DESC ,  chgPaymentPayment.org.id DESC , chgPaymentPayment.id  ";

        String od = order;
        if (od == null)
          od = " ASC ";
        hql = hql + criterion + "order by " + ob + " " + od;
        session.clear();
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

    return orglist;
  }

  public List queryChgPaymentPaymentQueryOtherWuht(final String orgId,
      final String orgName, final String officecode,
      final String collectionBankId, final String startChgMonth,
      final String endChgMonth, final String startBizDate,
      final String endBizDate, final String chgStatus, final String orderBy,
      final String order, final int start, final int pageSize,final SecurityInfo securityInfo) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = " from ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (officecode != null && !officecode.equals("")) {
          criterion += "chgPaymentPayment.org.orgInfo.officecode = ?  and ";
          parameters.add(officecode);
        }
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentPayment.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentPayment.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// 开始日期
          criterion += " (chgPaymentPayment.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentPayment.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentPayment.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// 开始日期
          criterion += " (chgPaymentPayment.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        if (chgStatus != null && !chgStatus.equals("")) {
          criterion += "chgPaymentPayment.chgStatus = ?  and ";
          parameters.add(new Integer(chgStatus));
        }
        
        if (collectionBankId != null && !collectionBankId.equals("")) {
          criterion += "chgPaymentPayment.org.orgInfo.collectionBankId = ?  and ";
          parameters.add(collectionBankId);
        }
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentPayment.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += "  chgPaymentPayment.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

        if (criterion.length() != 0)
          criterion = "where   chgPaymentPayment.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentPayment.org.orgInfo.officecode DESC, chgPaymentPayment.org.orgInfo.collectionBankId DESC ,  chgPaymentPayment.org.id DESC , chgPaymentPayment.id  ";

        String od = order;
        if (od == null)
          od = " ASC ";
        hql = hql + criterion + "order by " + ob + " " + od;
        session.clear();
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        System.out.println(query.list().size()+"--------------------query.list().size()222------------------->");
     
        return query.list();
      }
    }

    );

    return orglist;
  }

  public List queryChgPaymentPaymentQueryoldPaymentOrgWuht(final String orgId,
      final String orgName, final String officecode,
      final String collectionBankId, final String startChgMonth,
      final String endChgMonth, final String startBizDate,
      final String endBizDate, final String chgStatus, final String orderBy,
      final String order, final int start, final int pageSize,final SecurityInfo securityInfo) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = " select distinct chgPaymentPayment.org.id from ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (officecode != null && !officecode.equals("")) {
          criterion += "chgPaymentPayment.org.orgInfo.officecode = ?  and ";
          parameters.add(officecode);
        }
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentPayment.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentPayment.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// 开始日期
          criterion += " (chgPaymentPayment.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentPayment.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentPayment.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// 开始日期
          criterion += " (chgPaymentPayment.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        if (chgStatus != null && !chgStatus.equals("")) {
          criterion += "chgPaymentPayment.chgStatus = ?  and ";
          parameters.add(new Integer(chgStatus));
        }
        
        if (collectionBankId != null && !collectionBankId.equals("")) {
          criterion += "chgPaymentPayment.org.orgInfo.collectionBankId = ?  and ";
          parameters.add(collectionBankId);
        }
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentPayment.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += "  chgPaymentPayment.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

        if (criterion.length() != 0)
          criterion = "where   chgPaymentPayment.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

 
        hql = hql + criterion ;
        session.clear();
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
  
     
        return query.list();
      }
    }

    );

    return orglist;
  }

  
  public void updatePayChgUse(final Integer orgId ,final Integer chgHeadId,final String chgDate) throws SQLException{
    
    Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call PayChgUse(?,?,?)}");
      cs.setInt(1,orgId.intValue());
      cs.setInt(2,chgHeadId.intValue());
      cs.setString(3,chgDate);
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
   }
   public void updatePayChgReUse(final Integer orgId ,final Integer chgHeadId) throws SQLException{
     Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
     CallableStatement cs;
     try {
       cs = conn.prepareCall("{call PayChgReUse(?,?)}");
       cs.setInt(1,orgId.intValue());
       cs.setInt(2,chgHeadId.intValue());
       cs.execute();
     } catch (SQLException e) {
       e.printStackTrace();
     }
 }
   /**
    * 于庆丰 count chg_type=2
    * @param office
    * @param bank
    * @param orgCharacter
    * @param dept
    * @param ragion
    * @param startDate
    * @param endDate
    * @return
    */
   public int queryCount(final String office,final String bank,final String orgCharacter,
       final String dept,final String ragion,final String startDate,final String endDate){
     int count = 0;
     try{
       Integer countInteger = (Integer)getHibernateTemplate().execute(
         new HibernateCallback(){
           public Object doInHibernate(Session session)
           throws HibernateException, SQLException {
      
         String hql = "select count(chgPaymentPayment.id) from ChgPaymentPayment chgPaymentPayment ";
         Vector parameters = new Vector();
         String criterion = "";
         if(office != null && !"".equals(office)){
           criterion += " chgPaymentPayment.org.orgInfo.officecode = ? and ";
           parameters.add(office);
         }
         if(bank != null && !"".equals(bank)){
           criterion += " chgPaymentPayment.org.orgInfo.collectionBankId = ? and ";
           parameters.add(bank);
         }
         if(orgCharacter != null && !"".equals(orgCharacter)){
           criterion += " chgPaymentPayment.org.orgInfo.character = ? and ";
           parameters.add(orgCharacter);
         }
         if(dept != null && !"".equals(dept)){
           criterion += " chgPaymentPayment.org.orgInfo.deptInCharge = ? and ";
           parameters.add(dept);
         }
         if(ragion != null && !"".equals(ragion)){
           criterion += " chgPaymentPayment.org.orgInfo.region = ? and ";
           parameters.add(ragion);
         }
         if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
           criterion += " (chgPaymentPayment.bizDate  between ?  and  ?)  and ";
           parameters.add(startDate);
           parameters.add(endDate);
         }
         if (criterion.length() != 0) 
           criterion = " where chgPaymentPayment.chgStatus=2 and "
               + criterion.substring(0, criterion.lastIndexOf("and"));
//           criterion = " where "
//             + criterion.substring(0, criterion.lastIndexOf("and"));

           hql = hql + criterion ;
           Query query = session.createQuery(hql);
           for (int i = 0; i < parameters.size(); i++) {
             query.setParameter(i, parameters.get(i));
           }
           Integer countTemp=new Integer(0);
           Iterator it=query.iterate();          
           if(it.hasNext()){
             countTemp=(Integer)it.next();
           }
           return countTemp;
       }
     });
       count = countInteger.intValue();
     }catch(Exception e){
       e.printStackTrace();
     }
     return count;
   }
   
   /*
    * 返回职工的状态(根据职工编号和单位编号查询)
    * lishan
    * 
    */
   public String empstatuslishan(final Integer empid,final Integer orgid) {

     String status="";
     status = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a2.pay_status  " +
             " from aa002 a2  "+
             " where a2.id = ? and a2.ORG_ID = ?  ";
          Query query = session.createSQLQuery(hql);
          String aa="";
          query.setParameter(0, empid);
          query.setParameter(1, orgid);
          if(query.uniqueResult()!=null)
          {
          return  query.uniqueResult().toString();
          }
          else
          {
            return aa;
          }
        }
      });

      return  status;
    }
   //更新aa２０３的被选AB
   public void updateAA203(final String orgRate,final String empRate ,final String id){
     try {
       getHibernateTemplate().execute(new HibernateCallback() {
         public Object doInHibernate(Session session) throws HibernateException,
             SQLException {
           String hql = " update ChgPaymentTail chgPaymentTail set chgPaymentTail.reserveaA=?,chgPaymentTail.reserveaB=? "
               + "where chgPaymentTail.chgPaymentHead.id=?  ";
           Query query = session.createQuery(hql);
           query.setParameter(0, orgRate);
           query.setParameter(1, empRate); 
           query.setParameter(2, new Integer(id)); 
           query.executeUpdate();
           return null;
         }
       });
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
   //更新aa２０３的被选AB
   public void updateAA202(final String count,final String  money,final String id){
     try {
       getHibernateTemplate().execute(new HibernateCallback() {
         public Object doInHibernate(Session session) throws HibernateException,
             SQLException {
           String hql = " update ChgPaymentSalaryBase chgPaymentSalaryBase set chgPaymentSalaryBase.reserveaA=?,chgPaymentSalaryBase.reserveaB=? "
               + "where chgPaymentSalaryBase.id=?  ";
           Query query = session.createQuery(hql);
           query.setParameter(0, count);
           query.setParameter(1, money); 
           query.setParameter(2, new Integer(id)); 
           query.executeUpdate();
           return null;
         }
       });
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
}
