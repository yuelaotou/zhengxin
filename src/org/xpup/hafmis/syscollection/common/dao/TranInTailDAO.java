package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;

/**
 * 转入尾表
 * 
 * @author 李娟 2007-6-19
 */
public class TranInTailDAO extends HibernateDaoSupport {
 
  /**删除尾表
   * 
   */
  public void deleteTranInTail_sy(TranInTail tranInTail)throws NumberFormatException, Exception{
    getHibernateTemplate().delete(tranInTail);
  }

  /**更新尾表
   *
   */
  public void updataTranInTail_sy(final TranInTail tranInTail)throws NumberFormatException, Exception{
    try{
     getHibernateTemplate().execute(
        new HibernateCallback() {
          public  Object doInHibernate(Session session) throws HibernateException,
              SQLException {
            String hql = " update  TranInTail tranInTail set  tranInTail.name=? ,tranInTail.cardKind=? ,tranInTail.sex=? " +
                ",tranInTail.cardNum=? ,tranInTail.orgPay=?, tranInTail.empPay=? ,tranInTail.tel=?  "+
                ",tranInTail.salaryBase=? ,tranInTail.birthday=? ,tranInTail.empId=? ,tranInTail.mobileTel=? ,tranInTail.monthIncome=? "
                +", tranInTail.preBalance=? ,tranInTail.curBalance=? ,tranInTail.curInterest=? ,tranInTail.preIntegral=?,tranInTail.curIntegral=?,tranInTail.openDate=?  where  tranInTail.id=? ";
            Vector parameters = new Vector();
            String criterion = "";
            parameters.add(tranInTail.getName());
            parameters.add(tranInTail.getCardKind());
            parameters.add(tranInTail.getSex());
            parameters.add(tranInTail.getCardNum());
            parameters.add(tranInTail.getOrgPay());
            parameters.add(tranInTail.getEmpPay());
            parameters.add(tranInTail.getTel());
            parameters.add(tranInTail.getSalaryBase());
            parameters.add(tranInTail.getBirthday());
            parameters.add(tranInTail.getEmpId());
            parameters.add(tranInTail.getMobileTel());
            parameters.add(tranInTail.getMonthIncome());
            parameters.add(tranInTail.getPreBalance());
            parameters.add(tranInTail.getCurBalance());
            parameters.add(tranInTail.getCurInterest());
            parameters.add(tranInTail.getPreIntegral());
            parameters.add(tranInTail.getCurIntegral());
            parameters.add(tranInTail.getOpenDate());
            String id=tranInTail.getId().toString();
            parameters.add(new Integer(id));
            hql = hql + criterion +  " ";
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return new Integer(query.executeUpdate());  
          }
        });
    }catch(Exception e){
      e.printStackTrace();
    }
    }
  /**
   * 根据条件查找尾表个数
   * 
   * @param id
   
   * @throws Exception
   * @throws NumberFormatException return traninTail
   */
  public List countTraninListByCriterions(final String id,final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranInTail tranInTail ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += "tranInTail.tranInHead.id= ?  and ";
            parameters.add(new Integer(id));
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          if(criterion.length() == 0){
            criterion =  " where tranInTail.tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL();
          }
          hql = hql + criterion +  " ";
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
   * 根据条件查找 shiyan
   * 
   * @param id
   * @param name
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws Exception
   * @throws NumberFormatException return traninTail
   */
  public List queryTraninListByCriterions(final String id,
      final String tranInTailName, final String tranInTailCardNum,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final int page) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranInTail tranInTail  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += "tranInTail.tranInHead.id= ?  and ";
            parameters.add(new Integer(id));
          }
          if (tranInTailName != null && !tranInTailName.equals("")) {
            criterion += "tranInTail.name= ?  and ";
            parameters.add(tranInTailName);
          }
          if (tranInTailCardNum != null && !tranInTailCardNum.equals("")) {
            criterion += "(tranInTail.cardNum= ? or tranInTail.standbyCardNum= ?) and ";
            parameters.add(tranInTailCardNum);
            parameters.add(tranInTailCardNum);
          }
          if (criterion.length() != 0)
            criterion =  " where tranInTail.tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
            + criterion.substring(0, criterion.lastIndexOf("and"));
          if(criterion.length() == 0){
            criterion =  " where tranInTail.tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL();
          }
          String ob = orderBy;
          if (ob == null)
            ob = "tranInTail.empId";
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
             List queryList=query.list();
          
          if(queryList==null||queryList.size()==0){           
            query.setFirstResult(pageSize*(page-2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList=query.list();
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
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public TranInTail queryById(Serializable id) {
    Validate.notNull(id);
    return (TranInTail) getHibernateTemplate().get(TranInTail.class, id);
  }

  /**
   * 插入记录
   * 
   * @param empInfo
   * @return
   */
  public Serializable insert(TranInTail tranInTail)throws NumberFormatException, Exception  {
    Serializable id = null;
    try {
      Validate.notNull(tranInTail);
      id = getHibernateTemplate().save(tranInTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }

  public void deleteList(List deleteList)throws NumberFormatException, Exception  {
    // TODO Auto-generated method stub
    Validate.notNull(deleteList);
    getHibernateTemplate().deleteAll(deleteList);
  }
  
  public void deleteTranTrail(final Integer tranInHeadId){
    try{
  getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
           String sql="delete TranInTail tranInTail where tranInTail.tranInHead.id=?";          
           session.createQuery(sql).setInteger(0, tranInHeadId.intValue()).executeUpdate();
            return null;
        }
      }  
  );
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  
  public List queryTraninListByCriterionsAll_sy(final String id,final String orderBy,final String order,final int start,final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranInTail tranInTail  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += "tranInTail.tranInHead.id= ?  and ";
            parameters.add(new Integer(id));
          }
          if (criterion.length() != 0)
            criterion =  " where tranInTail.tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
            + criterion.substring(0, criterion.lastIndexOf("and"));
          if(criterion.length() == 0){
            criterion =  " where tranInTail.tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL();
          }
          String ob = orderBy;
          if (ob == null)
            ob = "tranInTail.empId";
          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + order;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    
    return list;
  }
  public List queryTraninListByCriterionsAll_sy_yg(final String id) throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          
          new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
              String hql = " from TranInTail tranInTail where tranInTail.tranInHead.id= ?  ";
              Query query = session.createQuery(hql);
              query.setParameter(0,new Integer(id));
              
              return query.list();
            }
          });
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    
    return list;
  }
  /**
   * 判断时间的方法
   */
  public int getDay(String moneyDate){
//    Date date = new Date(new java.util.Date().getTime());
//    String year = moneyDate.substring(0,4);
//    String month = moneyDate.substring(4,6);
//    String day = moneyDate.substring(6,8);
//    Calendar convert = Calendar.getInstance();
//    Calendar result = Calendar.getInstance();
//    convert.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
//    //设置为本年的6月30日
//    result.set(Integer.parseInt(date.toString().substring(0,4)), 05,30);
//    if(convert.getTime().getTime()> result.getTime().getTime()){//设置为明年的
//      result.set(Integer.parseInt(date.toString().substring(0,4))+1, 05,30);
//    }
//    Timestamp one = new Timestamp(result.getTime().getTime());
//    Timestamp two = new Timestamp(convert.getTime().getTime());
//    int number = BusiTools.minusDate(one.toString().substring(0,10),two.toString().substring(0,10));
//    return number;
    String nextJXdate ="";
    String sysDate=moneyDate;
    sysDate=sysDate.substring(0,4)+"-"+sysDate.substring(4,6)+"-"+sysDate.substring(6,sysDate.length());
    int empDays = Integer.parseInt(moneyDate.substring(4, 6));
    if (empDays < 7) {
      //本年
      //下一个结息日
      nextJXdate = sysDate.substring(0, 4) + "-06-30";
    }
    else if (empDays >= 7) {
      //下一年
      //下一个结息日
      nextJXdate = (Integer.parseInt(sysDate.substring(0, 4)) + 1) + "-06-30";
    }

    //间隔天数
    int days = BusiTools.minusDate(nextJXdate, sysDate);
    
    return days;
  }
  
  
  /**
   * 于庆丰
   * 查询职工信息
   * 
   * 
   */

  public TranInTail queryByCriterions(final String empid,
      final String orgid) {

    TranInTail tranInTail = null;
    try{
    tranInTail = (TranInTail) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select tranInTail from TranInTail tranInTail  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (empid != null) {
              criterion += "tranInTail.empId = ? and ";
              parameters.add(new Integer(empid));
            }
            if (orgid != null) {
              criterion += "tranInTail.tranInHead.tranInOrg.id = ? and ";
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
            return  query0.uniqueResult();
          }
        });
    }catch(Exception e){
      e.printStackTrace();
    }
    return tranInTail;
  }
  
  /**
   * 
   * @param id
   * @param orderBy
   * @param order
   * @param start
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryTraninListByCriterionsAll_yqf(final String empid,final String orderBy,final String order,final int start,final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranInTail tranInTail  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (empid != null && !empid.equals("")) {
            criterion += "tranInTail.empId= ?  and ";
            parameters.add(new Integer(empid));
          }
          if (criterion.length() != 0)
            criterion =  " where tranInTail.tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
            + criterion.substring(0, criterion.lastIndexOf("and"));
          if(criterion.length() == 0){
            criterion =  " where tranInTail.tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL();
          }
          String ob = orderBy;
          if (ob == null)
            ob = "tranInTail.empId";
          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + order;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

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
   * 王菱 扎账明细－打印中用到
   * @param id
   * @param orderBy
   * @param order
   * @param start
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryTraninListByCriterionsAll_WL(final String empid,final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranInTail tranInTail  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (empid != null && !empid.equals("")) {
            criterion += "tranInTail.empId= ?  and ";
            parameters.add(new Integer(empid));
          }
          if (criterion.length() != 0)
            criterion =  " where tranInTail.tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
            + criterion.substring(0, criterion.lastIndexOf("and"));
          if(criterion.length() == 0){
            criterion =  " where tranInTail.tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL();
          }

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
  public List queryTranInTail(final String inOrgId,final String name,final String number){
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranInTail tranInTail ";
          Vector parameters = new Vector();
          String criterion = "";
          if (inOrgId != null && !inOrgId.equals("")) {
            criterion += "tranInTail.tranInHead.tranInOrg.id= ?  and ";
            parameters.add(new Integer(inOrgId));
          }
          if (name != null && !name.equals("")) {
            criterion += "tranInTail.name= ?  and ";
            parameters.add(name);
          }
          if (number != null && !number.equals("")) {
            criterion += "(tranInTail.cardNum= ? or tranInTail.standbyCardNum= ?) and ";
            parameters.add(number);
            parameters.add(number);
          }
          if (criterion.length() != 0)
            criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion +  " ";
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
   * 存在未进行转入确认的转出
   * @param bizid
   * @return
   */
  public String getTrainoutheadid(final String bizid){
    String flag = "0";
    try {
      flag = (String)getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql0 = "select a.in_org_id from aa101 c, aa309 a where c.biz_id=a.id and c.biz_type='E' and ";
          String hql = "select  b.tran_out_head_id from aa101 c, aa309 a,aa311 b where a.id=b.tran_out_head_id and c.biz_id=a.id and c.biz_type='E' and a.in_org_id is not null and ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizid != null && !bizid.equals("")) {
            criterion += "a.id= ?  and ";
            parameters.add(new Integer(bizid));
          }
          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          hql0 = hql0 + criterion ;
          hql = hql + criterion ;
          Query query0 = session.createSQLQuery(hql0);
          for (int i = 0; i < parameters.size(); i++) {
            query0.setParameter(i, parameters.get(i));
          }
          if(query0.uniqueResult()==null||query0.uniqueResult().toString().equals("")){
            return "0";
          }else{
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if(query.uniqueResult()==null||query.uniqueResult().toString().equals("")){
              return "1";
            }else{
              return "0";
            }
          }
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }
  public String getTraininorgid(final String bizid){
    String flag = "0";
    try {
      flag = (String)getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a.in_org_id from aa309 a where ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizid != null && !bizid.equals("")) {
            criterion += "a.id= ?  and ";
            parameters.add(new Integer(bizid));
          }
          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion +  " ";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.uniqueResult().toString();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }
  public String getTrainoutorgid(final String bizid){
    String flag = "0";
    try {
      flag = (String)getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a.out_org_id from aa309 a where ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizid != null && !bizid.equals("")) {
            criterion += "a.id= ?  and ";
            parameters.add(new Integer(bizid));
          }
          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion +  " ";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.uniqueResult().toString();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }
  /**
   * 
   * @param id
   * @param orderBy
   * @param order
   * @param start
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryTraninListByCriterionsAll_wsh(final String empid,final String orderBy,final String order,final int start,final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranInTail tranInTail  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (empid != null && !empid.equals("")) {
            criterion += "tranInTail.tranInHead.id= ?  and ";
            parameters.add(new Integer(empid));
          }
          if (criterion.length() != 0)
            criterion =  " where tranInTail.tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
            + criterion.substring(0, criterion.lastIndexOf("and"));
          if(criterion.length() == 0){
            criterion =  " where tranInTail.tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL();
          }
          String ob = orderBy;
          if (ob == null)
            ob = "tranInTail.empId";
          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + order;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    
    return list;
  }
  public List fIndTailEmpInfoWS(final String id) {
    List list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session)
      throws HibernateException, SQLException{
        String hql = " select tot from TranInTail tot,TranInOrg org where org.id = tot.tranInHead.tranInOrg.id and " +
            " tot.tranInHead.id = ? " ;
        Query query = session.createQuery(hql);
         query.setParameter(0, new Integer(id));
        return query.list();
      }
    });
    return list;
  }  
}















