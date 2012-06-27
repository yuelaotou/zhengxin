package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.sql.Date;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;

/**
 * 公积金利息利率表
 * 
 * @author 李娟 2007-6-19
 */
public class HafInterestRateDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public HafInterestRate queryById(Serializable id) {
    Validate.notNull(id);
    return (HafInterestRate) getHibernateTemplate().get(HafInterestRate.class,
        id);
  }

  /**
   * 插入记录
   * 
   * @param hafInterestRate
   * @return
   */
  public Serializable insert(HafInterestRate hafInterestRate) {
    Serializable id = null;
    try {
      Validate.notNull(hafInterestRate);
      id = getHibernateTemplate().save(hafInterestRate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }
  
  //通过单位编号OrgOutid 查询OfficeCode 对应 313 中OfficeCode
  
  public List queryOfficeWZQ(final String id) {
    List list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " from HafInterestRate tot where tot.officecode =?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(id));
        return query.list();
      }
    });
    return list;
  } 

  /**
   * 分页查找所有利率
   * 
   * @param
   * @return list
   */
  public List queryRatemngList_sy(final String officecode,final String usetime, final String ratetype,final String orderBy, final String order,
      final int start, final int pageSize,final int page) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Vector parameters = new Vector();
          String criterion = "";
          String hql = "from HafInterestRate hafInterestRate  ";
          if (officecode != null && !officecode.equals("")) {
            criterion += " hafInterestRate.officecode = ?  and ";
            parameters.add(officecode);
          }

          if (usetime != null && !usetime.equals("")) {
            criterion += " hafInterestRate.bizDate = ?  and ";
            parameters.add(usetime);
          }

          if (ratetype != null && !ratetype.equals("")) {
            criterion += " hafInterestRate.isStart = ?  and ";
            parameters.add(new BigDecimal(ratetype));
          }

          if (criterion.length() != 0)
            criterion = "where  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = "hafInterestRate.bizDate";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql +criterion+ "order by " + ob + " " + order + "" +",  hafInterestRate.officecode ASC";
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
   * 统计查找所有利率
   * 
   * @param
   * @return list
   */
  public List findRatemngCountList_sy(final String officecode,final String usetime,final String ratetype) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Vector parameters = new Vector();
          String criterion = "";
          String hql = "from HafInterestRate hafInterestRate  ";
          if (officecode != null && !officecode.equals("")) {
            criterion += " hafInterestRate.officecode = ?  and ";
            parameters.add(officecode);
          }

          if (usetime != null && !usetime.equals("")) {
            criterion += " hafInterestRate.bizDate = ?  and ";
            parameters.add(usetime);
          }

          if (ratetype != null && !ratetype.equals("")) {
            criterion += " hafInterestRate.isStart = ?  and ";
            parameters.add(new BigDecimal(ratetype));
          }

          if (criterion.length() != 0)
            criterion = "where  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          
          hql = hql +criterion;
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
  public void deletRatemng_sy(HafInterestRate hafInterestRate){
    getHibernateTemplate().delete(hafInterestRate);
  }
  /**
   * 通过办事处查找利率
   * @param  办事处
   * @return list
   **/
  public List findRatemngByCriterions(final String officecode){
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from HafInterestRate hafInterestRate ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "hafInterestRate.officecode = ?  and ";
          parameters.add(officecode);
          if (criterion.length() != 0)
            criterion = " where "
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
  public List getOfficecodeName(final String officecode){
    List list=new ArrayList();
    try{
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from HafInterestRate hafInterestRate,OrganizationUnit organizationUnit ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "organizationUnit.type='2'  and  ";
          criterion += "organizationUnit.id = ?  and ";
          parameters.add(officecode);
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          session.clear();
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
    public Map getday_sy(String bizDate){
      Map map=new HashMap();
      try{ 
        String nextJXdate ="";
        String lastdate="";
        String sysDate=bizDate;
        sysDate=sysDate.substring(0,4)+"-"+sysDate.substring(4,6)+"-"+sysDate.substring(6,sysDate.length());

        int empDays = Integer.parseInt(bizDate.substring(4, 6));
        if (empDays < 7) {
          //本年
          //下一个结息日
          nextJXdate = sysDate.substring(0, 4) + "06"+"30";
          lastdate=(Integer.parseInt(sysDate.substring(0, 4)) - 1) + "07"+"01";
        }
        else if (empDays >= 7) {
          //下一年
          //下一个结息日
          nextJXdate = (Integer.parseInt(sysDate.substring(0, 4)) + 1) + "-06-30";
          lastdate=sysDate.substring(0, 4) + "07"+"01";
        }
      map.put("nowDate", nextJXdate);
      map.put("oldDate", lastdate);
      }catch(Exception e){
        e.printStackTrace();
      }
      return map;
    }
    /**
     * 统计本年本结息年度中记录数等于3
     * param 结息年度的两个日期
     *return list
     */
    public List queryHafInterestRate_sy(final String nowDate,final String oldDate,final String officecode){
      List list=new ArrayList();
      try {
        list = getHibernateTemplate().executeFind(new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {
            String hql = "from HafInterestRate hafInterestRate ";
            Vector parameters = new Vector();
            String criterion = "";
            criterion += " hafInterestRate.bizDate>=  ? and ";
            parameters.add(oldDate);
            criterion += " hafInterestRate.bizDate<=  ? and ";
            parameters.add(nowDate);
            criterion += " hafInterestRate.officecode=  ? and ";
            parameters.add(officecode);
            if (criterion.length() != 0)
              criterion = " where "
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
    /**
     * 石岩
     * 利率启用
     */
    
    public void doUseRatemng(String ip,String oper,String moneyday,int rateCount) throws BusinessException, HibernateException, SQLException{
      try{
        final int number=this.getDay(moneyday);
      Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
      CallableStatement cs=conn.prepareCall("{call RATEMNGUSE2(?,?,?,?)}");
      cs.setInt(1, number);
      cs.setString(2, ip);
      cs.setString(3, oper);
      cs.setInt(4, rateCount);
      cs.execute();
      }catch(Exception e){
        e.printStackTrace();
        throw new BusinessException("利率调整失败!!!");
      }
    }
    /**
     * 判断时间的方法
     */
    public int getDay(String moneyDate){
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
     * 根据办事处ID查询已启用最后一条
     * @param officecode
     * @return
     */
    public HafInterestRate queryHafInterestRate(final String officecode){
      HafInterestRate hafInterestRate=null;
      hafInterestRate=(HafInterestRate)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
        SQLException {
      String hql = "from HafInterestRate hafInterestRate where  hafInterestRate.id =(select max(hafInterestRate1.id) from HafInterestRate hafInterestRate1 where hafInterestRate1.officecode = ? and hafInterestRate1.isStart=2)";
      Query query = session.createQuery(hql);
      query.setParameter(0, officecode);   
      return query.uniqueResult();
      
    }
  });
      if(hafInterestRate == null){
        hafInterestRate = new HafInterestRate();
      }
      return  hafInterestRate;
    }
}


















