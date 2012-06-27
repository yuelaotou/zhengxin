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
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentHead;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentTail;
import org.xpup.hafmis.syscollection.paymng.monthpay.dto.MonthpayMaintainDto;
import org.xpup.hafmis.syscollection.paymng.monthpay.dto.MonthpayTbWindowDto;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.AddpayInfoDto;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgAddpayInfoDTO;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayMaintainDto;
import org.xpup.hafmis.syscollection.paymng.paysure.dto.AddPayTailDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.dto.OrgpaymentstatisticsDTO;

/**
 * 汇缴尾表
 * 
 *@author 李娟
 *2007-6-20
 */
public class MonthPaymentTailDAO extends HibernateDaoSupport{
  
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public MonthPaymentTail queryById(Serializable id) {  
    Validate.notNull(id);
    return  (MonthPaymentTail) getHibernateTemplate().get(MonthPaymentTail.class,id);    
  }
  /**
   * 插入记录
   * @param monthPaymentTail
   * @return
   */
  public Serializable insert(MonthPaymentTail monthPaymentTail){
    Serializable id = null;
    try{    
    Validate.notNull(monthPaymentTail);
    id = getHibernateTemplate().save(monthPaymentTail);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * 李娟
   * 根据单位ID汇缴年月查询单位实缴金额
   * @param orgId
   * @param payMonth
   * @return
   */
  public BigDecimal queryOrgRealPay(final Serializable orgId,final String payMonth){
    BigDecimal paymoney = null;
    try {
      paymoney= (BigDecimal) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "select nvl(sum(monthPaymentTail.orgRealPay),0) " +
            " from MonthPaymentTail monthPaymentTail ,MonthPayment monthPayment ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgId != null&&!orgId.equals("")) {
          criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
          parameters.add(new Integer(orgId.toString()));
        }
        if(payMonth != null && !payMonth.equals("")){
          criterion +=" monthPaymentTail.monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
        }
        if (criterion.length() != 0)
          criterion = " where monthPaymentTail.monthPaymentHead.paymentHead.id = monthPayment.id and monthPayment.payStatus=5 and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        
        return query.uniqueResult();
      }
    });
    }catch(Exception e){
        e.printStackTrace();
      }
    if(paymoney == null){
      paymoney = new BigDecimal(0.00); 
    }

    return paymoney;
  }
  /**
   * 杨光
   * 根据单位ID汇缴年月查询单位实缴金额
   * @param orgId
   * @param payMonth
   * @return
   */
  public BigDecimal queryOrgRealPay_yg(final Serializable orgId,final String payMonth){
    BigDecimal paymoney = null;
    try {
      paymoney= (BigDecimal) getHibernateTemplate()
      .execute(new HibernateCallback() {
        public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
          String hql = "select nvl(sum(monthPaymentTail.orgRealPay),0) " +
          " from MonthPaymentTail monthPaymentTail ,MonthPayment monthPayment ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (orgId != null&&!orgId.equals("")) {
            criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
            parameters.add(new Integer(orgId.toString()));
          }
          if(payMonth != null && !payMonth.equals("")){
            criterion +=" monthPaymentTail.monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
          }
          if (criterion.length() != 0)
            criterion = " where monthPaymentTail.monthPaymentHead.paymentHead.id = monthPayment.id and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          
          return query.uniqueResult();
        }
      });
    }catch(Exception e){
      e.printStackTrace();
    }
    if(paymoney == null){
      paymoney = new BigDecimal(0.00); 
    }
    
    return paymoney;
  }
  /**
   * 李娟
   * 根据单位ID汇缴年月查询职工实缴金额
   * @param orgId
   * @param payMonth
   * @return
   */
  public BigDecimal queryEmpRealPay(final Serializable orgId,final String payMonth){
    BigDecimal paymoney = null;
    try {
      paymoney= (BigDecimal) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "select nvl(sum(monthPaymentTail.empRealPay),0) " +
            " from MonthPaymentTail monthPaymentTail ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgId != null&&!orgId.equals("")) {
          criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
          parameters.add(new Integer(orgId.toString()));
        }
        if(payMonth != null && !payMonth.equals("")){
          criterion +=" monthPaymentTail.monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
        }
        if (criterion.length() != 0)
          criterion = "  where monthPaymentTail.monthPaymentHead.paymentHead.payType_='A' and monthPaymentTail.monthPaymentHead.paymentHead.payStatus=5 and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        
        return query.uniqueResult();
      }
    });
    }catch(Exception e){
        e.printStackTrace();
      }
    if(paymoney == null){
      paymoney = new BigDecimal(0.00); 
    }

    return paymoney;
  }
  /**
   * 杨光
   * 根据单位ID汇缴年月查询职工实缴金额
   * @param orgId
   * @param payMonth
   * @return
   */
  public BigDecimal queryEmpRealPay_yg(final Serializable orgId,final String payMonth){
    BigDecimal paymoney = null;
    try {
      paymoney= (BigDecimal) getHibernateTemplate()
      .execute(new HibernateCallback() {
        public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
          String hql = "select nvl(sum(monthPaymentTail.empRealPay),0) " +
          " from MonthPaymentTail monthPaymentTail ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (orgId != null&&!orgId.equals("")) {
            criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
            parameters.add(new Integer(orgId.toString()));
          }
          if(payMonth != null && !payMonth.equals("")){
            criterion +=" monthPaymentTail.monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
          }
          if (criterion.length() != 0)
            criterion = "  where monthPaymentTail.monthPaymentHead.paymentHead.payType_='A' and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          
          return query.uniqueResult();
        }
      });
    }catch(Exception e){
      e.printStackTrace();
    }
    if(paymoney == null){
      paymoney = new BigDecimal(0.00); 
    }
    
    return paymoney;
  }
  /**
   * 李娟
   * 根据年月、单位ID查询
   * @param orgId
   * @param payMonth
   * @return
   * @throws Exception
   */
  public List queryEmpList(final Serializable orgId,final String payMonth) throws Exception{
    List empList=null;
    try {
      empList = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select monthPaymentTail from MonthPaymentTail monthPaymentTail , MonthPayment monthPayment ";
              Vector parameters = new Vector();
              String criterion = "";

              if (orgId != null&&!orgId.equals("")) {
                criterion += "monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgId.toString()));
              }
              if(payMonth != null && !payMonth.equals("")){
                criterion +=" monthPaymentTail.monthPaymentHead.payMonth = ? and  ";
                  parameters.add(payMonth);
              }
              if (criterion.length() != 0)
                criterion = "where  monthPaymentTail.monthPaymentHead.paymentHead.id = monthPayment.id and monthPayment.payStatus=5 and "
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
  /**
   * 杨光
   * 根据年月、单位ID查询
   * @param orgId
   * @param payMonth
   * @return
   * @throws Exception
   */
  public List queryEmpList_yg(final Serializable orgId,final String payMonth) throws Exception{
    List empList=null;
    try {
      empList = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            
            public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
              
              String hql = "select monthPaymentTail from MonthPaymentTail monthPaymentTail , MonthPayment monthPayment ";
              Vector parameters = new Vector();
              String criterion = "";
              
              if (orgId != null&&!orgId.equals("")) {
                criterion += "monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgId.toString()));
              }
              if(payMonth != null && !payMonth.equals("")){
                criterion +=" monthPaymentTail.monthPaymentHead.payMonth = ? and  ";
                parameters.add(payMonth);
              }
              if (criterion.length() != 0)
                criterion = "where  monthPaymentTail.monthPaymentHead.paymentHead.id = monthPayment.id and "
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
  /**
   * 李娟
   * 查询缴存人数（欠缴）
   * @param monthPaymentId
   * @return
   */
  public Integer queryPaymentPersonCountsLJ(final Serializable monthPaymentId){
    Integer empCount = (Integer) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "select count(*) from MonthPaymentTail monthPaymentTail ";
        Vector parameters = new Vector();
        String criterion = "";
        if (monthPaymentId != null) {
          criterion = " where monthPaymentTail.monthPaymentHead.id = ?";
          parameters.add(new Integer(monthPaymentId.toString()));
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
   * 查询缴存人数(明细)
   * @param monthPaymentId
   * @return
   */
  public Integer queryPaymentPersonCountsMXLJ(final Serializable monthPaymentId){
    Integer empCount = (Integer) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "select count(*) from MonthPaymentTail monthPaymentTail ";
        Vector parameters = new Vector();
        String criterion = "";
        if (monthPaymentId != null) {
          criterion = " where monthPaymentTail.monthPaymentHead.id = ?";
          parameters.add(new Integer(monthPaymentId.toString()));
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
   * 根据单位ID汇缴年月查询单位应缴金额
   * @param orgId
   * @param payMonth
   * @return
   */
  public BigDecimal queryOrgShouldPayLJ(final Serializable orgId,final String payMonth){
    BigDecimal paymoney = null;
    try {
      paymoney= (BigDecimal) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "select nvl(sum(monthPaymentTail.orgShouldPay),0) " +
            " from MonthPaymentTail monthPaymentTail ,MonthPayment monthPayment ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgId != null&&!orgId.equals("")) {
          criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
          parameters.add(new Integer(orgId.toString()));
        }
        if(payMonth != null && !payMonth.equals("")){
          criterion +=" monthPaymentTail.monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
        }
        if (criterion.length() != 0)
          criterion = " where monthPaymentTail.monthPaymentHead.paymentHead.id = monthPayment.id and monthPayment.payStatus=5 and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        
        return query.uniqueResult();
      }
    });
    }catch(Exception e){
        e.printStackTrace();
      }
    if(paymoney == null){
      paymoney = new BigDecimal(0.00); 
    }

    return paymoney;
  }
  /**
   * 杨光
   * 根据单位ID汇缴年月查询单位应缴金额
   * @param orgId
   * @param payMonth
   * @return
   */
  public BigDecimal queryOrgShouldPayLJ_yg(final Serializable orgId,final String payMonth){
    BigDecimal paymoney = null;
    try {
      paymoney= (BigDecimal) getHibernateTemplate()
      .execute(new HibernateCallback() {
        public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
          String hql = "select nvl(sum(monthPaymentTail.orgShouldPay),0) " +
          " from MonthPaymentTail monthPaymentTail ,MonthPayment monthPayment ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (orgId != null&&!orgId.equals("")) {
            criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
            parameters.add(new Integer(orgId.toString()));
          }
          if(payMonth != null && !payMonth.equals("")){
            criterion +=" monthPaymentTail.monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
          }
          if (criterion.length() != 0)
            criterion = " where monthPaymentTail.monthPaymentHead.paymentHead.id = monthPayment.id and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          
          return query.uniqueResult();
        }
      });
    }catch(Exception e){
      e.printStackTrace();
    }
    if(paymoney == null){
      paymoney = new BigDecimal(0.00); 
    }
    
    return paymoney;
  }
  /**
   * 李娟
   * 根据单位ID汇缴年月查询职工应缴金额
   * @param orgId
   * @param payMonth
   * @return
   */
  public BigDecimal queryEmpShouldPayLJ(final Serializable orgId,final String payMonth){
    BigDecimal paymoney = null;
    try {
      paymoney= (BigDecimal) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hql = "select nvl(sum(monthPaymentTail.empShouldPay),0) " +
            " from MonthPaymentTail monthPaymentTail ,MonthPayment monthPayment";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgId != null&&!orgId.equals("")) {
          criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
          parameters.add(new Integer(orgId.toString()));
        }
        if(payMonth != null && !payMonth.equals("")){
          criterion +=" monthPaymentTail.monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
        }
        if (criterion.length() != 0)
          criterion = "  where monthPaymentTail.monthPaymentHead.paymentHead.id = monthPayment.id and monthPayment.payStatus=5 and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        
        return query.uniqueResult();
      }
    });
    }catch(Exception e){
        e.printStackTrace();
      }
    if(paymoney == null){
      paymoney = new BigDecimal(0.00); 
    }

    return paymoney;
  }
  /**
   * 杨光
   * 根据单位ID汇缴年月查询职工应缴金额
   * @param orgId
   * @param payMonth
   * @return
   */
  public BigDecimal queryEmpShouldPayLJ_yg(final Serializable orgId,final String payMonth){
    BigDecimal paymoney = null;
    try {
      paymoney= (BigDecimal) getHibernateTemplate()
      .execute(new HibernateCallback() {
        public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
          String hql = "select nvl(sum(monthPaymentTail.empShouldPay),0) " +
          " from MonthPaymentTail monthPaymentTail ,MonthPayment monthPayment";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (orgId != null&&!orgId.equals("")) {
            criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
            parameters.add(new Integer(orgId.toString()));
          }
          if(payMonth != null && !payMonth.equals("")){
            criterion +=" monthPaymentTail.monthPaymentHead.payMonth = ? and  ";
            parameters.add(payMonth);
          }
          if (criterion.length() != 0)
            criterion = "  where monthPaymentTail.monthPaymentHead.paymentHead.id = monthPayment.id and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          
          return query.uniqueResult();
        }
      });
    }catch(Exception e){
      e.printStackTrace();
    }
    if(paymoney == null){
      paymoney = new BigDecimal(0.00); 
    }
    
    return paymoney;
  }
  /**
   * 李娟
   * 查询汇缴列表
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
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryPaymentListByCriterionsLJ(final Serializable id, final String name,final String status,
      final String inceptMonth,final String payMonth,final String payType,final String inceptPayMoney,
      final String payMoney,final String inceptSettlementDate,final String settlementDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final int page,final String settDate,final String settDate1,final String collBankId) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select monthPayment.org.id,monthPayment.org.orgInfo.name," +
                  " monthPayment.payModel, " +
                  " min(monthPaymentHead.payMonth),max(monthPaymentHead.payMonth)," +
                  " monthPayment.noteNum,monthPayment.docNum,to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd'),monthPayment.payStatus, monthPayment.id,monthPayment.payMoney ,monthPayment.minMaxMonth," +
                  " monthPayment.settDate " +
                  " from MonthPayment monthPayment,MonthPaymentBizActivityLog monthPaymentBizActivityLog, MonthPaymentHead monthPaymentHead" +
                  " where monthPaymentHead.paymentHead.id=monthPayment.id and " +
                  " monthPaymentHead.paymentHead.id=monthPaymentBizActivityLog.bizid and monthPaymentBizActivityLog.action = '2' "+
                  " and monthPayment.org.id "+securityInfo.getGjjSecurityHqlSQL();
              
//              String hql = "select new MonthPaymentTail(monthPaymentTail,monthPaymentBizActivityLog) " +
//                  "from MonthPaymentTail monthPaymentTail,MonthPayment monthPayment," +
//                  "MonthPaymentBizActivityLog monthPaymentBizActivityLog ";
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += " to_char(monthPaymentHead.paymentHead.org.id ) like ? escape '/'  and ";
                parameters.add("%" + id.toString()+ "%");
              }
              
              if (name != null&&!name.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + name + "%");
              }
              if (status != null&&!status.equals("")) {
                  criterion += " monthPayment.payModel = ? and ";
                  parameters.add(new Integer(status));
              }

              if (inceptMonth != null&&!inceptMonth.equals("")&&payMonth!=null&&!payMonth.equals("")) {
                criterion += " (monthPaymentHead.payMonth between ?  and  ? ) and ";
                parameters.add(inceptMonth);
                parameters.add(payMonth);
              }else if (inceptMonth != null&&!inceptMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth = ? and ";
                parameters.add(inceptMonth);
              }else if (payMonth!=null&&!payMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth = ?  and ";
                parameters.add(payMonth);
              }
              if (payType != null&&!payType.equals("")) {
                criterion += " monthPayment.payStatus = ?  and ";
                parameters.add(new Integer(payType));
              }
              if (settDate != null&&!settDate.equals("") && settDate1 != null && !settDate1.equals("")) {
                criterion += " (monthPayment.settDate between ?  and  ? ) and ";
                parameters.add(settDate);
                parameters.add(settDate1);
              }else if (settDate != null&&!settDate.equals("")) {
                criterion += " monthPayment.settDate = ? and ";
                parameters.add(settDate);
              }else if (settDate1 != null && !settDate1.equals("")) {
                criterion += " monthPayment.settDate = ? and ";
                parameters.add(settDate1);
              }
              if (inceptPayMoney != null&&!inceptPayMoney.equals("") && payMoney != null && !payMoney.equals("")) {
                criterion += " (monthPayment.payMoney between ?  and  ? ) and ";
                parameters.add(new BigDecimal(inceptPayMoney));
                parameters.add(new BigDecimal(payMoney));
              }else if (inceptPayMoney != null&&!inceptPayMoney.equals("")) {
                criterion += " monthPayment.payMoney = ? and ";
                parameters.add(new BigDecimal(inceptPayMoney));
              }else if (payMoney != null && !payMoney.equals("")) {
                criterion += " monthPayment.payMoney = ? and ";
                parameters.add(new BigDecimal(payMoney));
              }
              //对缴存金额查询不好用进行了修改，添加了两个else
              if (inceptSettlementDate != null&&!inceptSettlementDate.equals("") && settlementDate != null && !settlementDate.equals("")) {
                criterion += " (to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') between ?  and  ?) and ";
                parameters.add(inceptSettlementDate);
                parameters.add(settlementDate);
              }else if (inceptSettlementDate != null&&!inceptSettlementDate.equals("")) {
                criterion += " to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(inceptSettlementDate);
              }else if (settlementDate != null && !settlementDate.equals("")) {
                criterion += " to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(settlementDate);
              }

              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              String ob = orderBy;
              if (ob == null)
                ob = " monthPayment.id ";

              String od = order;
              if (od == null)
                od = "DESC";

              hql = hql + criterion +" group by monthPayment.org.id,monthPayment.org.orgInfo.name," +
                  "monthPayment.noteNum,monthPayment.docNum,monthPaymentBizActivityLog.opTime," +
                  "monthPayment.payStatus,monthPayment.id,monthPayment.payMoney,monthPayment.payModel,monthPayment.settDate "+ "order by " + ob + " " + od ;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);       
              Iterator it=query.iterate();
              List queryList=query.list();
              if(queryList==null||queryList.size()==0){           
                query.setFirstResult(pageSize*(page-2));
                if (pageSize > 0)
                  query.setMaxResults(pageSize);
                it=query.iterate();
              }
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                MonthpayMaintainDto monthpayMaintainDto=new MonthpayMaintainDto();
                monthpayMaintainDto.setOrgId(new Integer(obj[0].toString()));
                monthpayMaintainDto.setOrgName(obj[1].toString());
                if(obj[6] != null){
                  monthpayMaintainDto.setDocNum(obj[6].toString());
                }
                if(obj[5] != null){
                  monthpayMaintainDto.setNoteNum(obj[5].toString());
                }
                monthpayMaintainDto.setPayMonth(obj[3].toString()+"-"+obj[4].toString());
                monthpayMaintainDto.setOpTime(obj[7].toString());
                if(obj[2].toString().equals("3")){
                  monthpayMaintainDto.setPayMode("只缴职工");
                }else if(obj[2].toString().equals("2")){
                  monthpayMaintainDto.setPayMode("只缴单位");
                }else {
                  monthpayMaintainDto.setPayMode("均缴");
                }
                monthpayMaintainDto.setPay((new BigDecimal(obj[10].toString())).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                monthpayMaintainDto.setPayStatus(obj[8].toString());
                monthpayMaintainDto.setId(obj[9].toString());
                if(obj[12] !=null){
                  monthpayMaintainDto.setSettDate(obj[12].toString());
                }
                temp_list.add(monthpayMaintainDto);
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
   * 维护查询打印列表
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
   * @param orderBy
   * @param order
   * @param securityInfo
   * @param settDate
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryPaymentListByCriterionsPrint_jj(final Serializable id, final String name,final String status,
      final String inceptMonth,final String payMonth,final String payType,final String inceptPayMoney,
      final String payMoney,final String inceptSettlementDate,final String settlementDate,
      final String orderBy, final String order,final SecurityInfo securityInfo,final String settDate,final String settDate1,
      final String collBankId) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select monthPayment.org.id,monthPayment.org.orgInfo.name," +
                  " monthPayment.payModel, " +
                  " min(monthPaymentHead.payMonth),max(monthPaymentHead.payMonth)," +
                  " monthPayment.noteNum,monthPayment.docNum,to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd'),monthPayment.payStatus, monthPayment.id,monthPayment.payMoney ,monthPayment.minMaxMonth," +
                  " monthPayment.settDate " +
                  " from MonthPayment monthPayment,MonthPaymentBizActivityLog monthPaymentBizActivityLog, MonthPaymentHead monthPaymentHead" +
                  " where monthPaymentHead.paymentHead.id=monthPayment.id and " +
                  " monthPaymentHead.paymentHead.id=monthPaymentBizActivityLog.bizid and monthPaymentBizActivityLog.action = monthPayment.payStatus "+
                  " and monthPayment.org.id "+securityInfo.getGjjSecurityHqlSQL();

              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += " to_char(monthPaymentHead.paymentHead.org.id ) like ? escape '/'  and ";
                parameters.add("%" + id.toString()+ "%");
              }
              
              if (name != null&&!name.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + name + "%");
              }
              if (status != null&&!status.equals("")) {
                  criterion += " monthPayment.payModel = ? and ";
                  parameters.add(new Integer(status));
              }

              if (inceptMonth != null&&!inceptMonth.equals("")&&payMonth!=null&&!payMonth.equals("")) {
                criterion += " (monthPaymentHead.payMonth between ?  and  ? ) and ";
                parameters.add(inceptMonth);
                parameters.add(payMonth);
              }else if (inceptMonth != null&&!inceptMonth.equals("")) {
                criterion += " (monthPaymentHead.payMonth = ? and ";
                parameters.add(inceptMonth);
              }else if (payMonth!=null&&!payMonth.equals("")) {
                criterion += " (monthPaymentHead.payMonth = ?  and ";
                parameters.add(payMonth);
              }
              if (payType != null&&!payType.equals("")) {
                criterion += " monthPayment.payStatus = ?  and ";
                parameters.add(new Integer(payType));
              }
              if (settDate != null&&!settDate.equals("") && settDate1 != null && !settDate1.equals("")) {
                criterion += " (monthPayment.settDate between ?  and  ? ) and ";
                parameters.add(settDate);
                parameters.add(settDate1);
              }else if (settDate != null&&!settDate.equals("")) {
                criterion += " monthPayment.settDate = ? and ";
                parameters.add(settDate);
              }else if (settDate1 != null && !settDate1.equals("")) {
                criterion += " monthPayment.settDate = ? and ";
                parameters.add(settDate1);
              }
              if (inceptPayMoney != null&&!inceptPayMoney.equals("") && payMoney != null && !payMoney.equals("")) {
                criterion += " (monthPayment.payMoney between ?  and  ? ) and ";
                parameters.add(new BigDecimal(inceptPayMoney));
                parameters.add(new BigDecimal(payMoney));
              }else if (inceptPayMoney != null&&!inceptPayMoney.equals("")) {
                criterion += " monthPayment.payMoney = ? and ";
                parameters.add(new BigDecimal(inceptPayMoney));
              }else if (payMoney != null && !payMoney.equals("")) {
                criterion += " monthPayment.payMoney = ? and ";
                parameters.add(new BigDecimal(payMoney));
              }
              //对缴存金额查询不好用进行了修改，添加了两个else
              if (inceptSettlementDate != null&&!inceptSettlementDate.equals("") && settlementDate != null && !settlementDate.equals("")) {
                criterion += " (to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') between ?  and  ?) and ";
                parameters.add(inceptSettlementDate);
                parameters.add(settlementDate);
              }else if (inceptSettlementDate != null&&!inceptSettlementDate.equals("")) {
                criterion += " to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(inceptSettlementDate);
              }else if (settlementDate != null && !settlementDate.equals("")) {
                criterion += " to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(settlementDate);
              }

              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              String ob = orderBy;
              if (ob == null)
                ob = " monthPayment.id ";

              String od = order;
              if (od == null)
                od = "DESC";

              hql = hql + criterion +" group by monthPayment.org.id,monthPayment.org.orgInfo.name," +
                  "monthPayment.noteNum,monthPayment.docNum,monthPaymentBizActivityLog.opTime," +
                  "monthPayment.payStatus,monthPayment.id,monthPayment.payMoney,monthPayment.payModel,monthPayment.settDate "+ "order by " + ob + " " + od ;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }     
              
              
              Iterator it=query.iterate();
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                MonthpayMaintainDto monthpayMaintainDto=new MonthpayMaintainDto();
                monthpayMaintainDto.setOrgId(new Integer(obj[0].toString()));
                monthpayMaintainDto.setOrgName(obj[1].toString());
                if(obj[6] != null){
                  monthpayMaintainDto.setDocNum(obj[6].toString());
                }
                if(obj[5] != null){
                  monthpayMaintainDto.setNoteNum(obj[5].toString());
                }
                monthpayMaintainDto.setPayMonth(obj[3].toString()+"-"+obj[4].toString());
                monthpayMaintainDto.setOpTime(obj[7].toString());
                if(obj[2].toString().equals("3")){
                  monthpayMaintainDto.setPayMode("只缴职工");
                }else if(obj[2].toString().equals("2")){
                  monthpayMaintainDto.setPayMode("只缴单位");
                }else {
                  monthpayMaintainDto.setPayMode("均缴");
                }
                monthpayMaintainDto.setPay((new BigDecimal(obj[10].toString())).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                monthpayMaintainDto.setPayStatus(obj[8].toString());
                monthpayMaintainDto.setId(obj[9].toString());
                if(obj[12] !=null){
                  monthpayMaintainDto.setSettDate(obj[12].toString());
                }
                temp_list.add(monthpayMaintainDto);
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
   * 李娟
   * 查询汇缴列表记录数
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
  public int queryPaymentCountByCriterionsLJ(final Serializable id, final String name,final String status,
      final String inceptMonth,final String payMonth,final String payType,final String inceptPayMoney,
      final String payMoney,final String inceptSettlementDate,final String settlementDate,
      final SecurityInfo securityInfo,final String settDate,final String settDate1,final String collBankId) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select count(*) from MonthPayment monthPayment,MonthPaymentBizActivityLog monthPaymentBizActivityLog,MonthPaymentHead monthPaymentHead " +
                  " where monthPaymentHead.paymentHead.id = monthPayment.id and  " +
                  " monthPaymentHead.paymentHead.id=monthPaymentBizActivityLog.bizid and monthPaymentBizActivityLog.action = monthPayment.payStatus " +
                  " and monthPayment.org.id "+securityInfo.getGjjSecurityHqlSQL();
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {

                criterion += " to_char( monthPaymentHead.paymentHead.org.id ) like ? escape '/'  and ";
                parameters.add("%" + id.toString()+ "%");

              }
              
              if (name != null&&!name.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + name + "%");
              }
              if (status != null&&!status.equals("")) {
                criterion += " monthPayment.payModel = ? and ";
                parameters.add(new Integer(status));
              }

              if (inceptMonth != null&&!inceptMonth.equals("")&&payMonth!=null&&!payMonth.equals("")) {
                criterion += " (monthPaymentHead.payMonth between ?  and  ? ) and ";
                parameters.add(inceptMonth);
                parameters.add(payMonth);
              }else if (inceptMonth != null&&!inceptMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth = ?  and ";
                parameters.add(inceptMonth);
              }else if (payMonth!=null&&!payMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth = ? and ";
                parameters.add(payMonth);
              }
              if (payType != null&&!payType.equals("")) {
                criterion += " monthPayment.payStatus = ?  and ";
                parameters.add(new Integer(payType));
              }
              if (settDate != null&&!settDate.equals("") && settDate1 != null && !settDate1.equals("")) {
                criterion += " (monthPayment.settDate between ?  and  ? )and ";
                parameters.add(settDate);
                parameters.add(settDate1);
              }else if (settDate != null&&!settDate.equals("") ) {
                criterion += " monthPayment.settDate = ? and ";
                parameters.add(settDate);
              }else if (settDate1 != null && !settDate1.equals("")) {
                criterion += " monthPayment.settDate = ? and ";
                parameters.add(settDate1);
              }
              
              if (inceptPayMoney != null&&!inceptPayMoney.equals("") && payMoney != null && !payMoney.equals("")) {
                criterion += " (monthPayment.payMoney between ?  and  ? )and ";
                parameters.add(new BigDecimal(inceptPayMoney));
                parameters.add(new BigDecimal(payMoney));
              }else if (inceptPayMoney != null&&!inceptPayMoney.equals("") ) {
                criterion += " monthPayment.payMoney = ? and ";
                parameters.add(new BigDecimal(inceptPayMoney));
              }else if (payMoney != null && !payMoney.equals("")) {
                criterion += " monthPayment.payMoney = ? and ";
                parameters.add(new BigDecimal(payMoney));
              }
              if (inceptSettlementDate != null&&!inceptSettlementDate.equals("") && settlementDate != null && !settlementDate.equals("")) {
                criterion += " (to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') between ?  and  ?) and ";
                parameters.add(inceptSettlementDate);
                parameters.add(settlementDate);
              }else if (inceptSettlementDate != null&&!inceptSettlementDate.equals("")) {
                criterion += " to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(inceptSettlementDate);
              }else if (settlementDate != null && !settlementDate.equals("")) {
                criterion += " to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(settlementDate);
              }

              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion +" group by monthPayment.org.id,monthPayment.org.orgInfo.name," +
                  "monthPayment.noteNum,monthPayment.docNum,monthPaymentBizActivityLog.opTime," +
                  "monthPayment.payStatus,monthPayment.id,monthPayment.payMoney,monthPayment.settDate ";
              
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
  public BigDecimal queryPaymentListTotalLJ(final Serializable id, final String name,final String status,
      final String inceptMonth,final String payMonth,final String payType,final String inceptPayMoney,
      final String payMoney,final String inceptSettlementDate,final String settlementDate,final SecurityInfo securityInfo,final String settDate) throws NumberFormatException, Exception {
     BigDecimal totalmoney = null;
    try {

      totalmoney = (BigDecimal)getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql =" select nvl(sum(monthPayment.payMoney),0)" +
                  " from MonthPayment monthPayment,MonthPaymentHead monthPaymentHead,MonthPaymentBizActivityLog monthPaymentBizActivityLog,Org orgs " +
                  " where monthPaymentHead.paymentHead.id=monthPayment.id and monthPayment.id=monthPaymentBizActivityLog.bizid and monthPaymentBizActivityLog.action = monthPayment.payStatus " +
                  " and orgs.id=monthPayment.org.id and monthPaymentHead.paymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL();

              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += " to_char(monthPaymentHead.paymentHead.org.id ) like ? escape '/'  and ";
                parameters.add("%" + id.toString()+ "%");
              }                
              if (name != null&&!name.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + name + "%");
              }
              if (status != null&&!status.equals("")) {
                criterion += " monthPayment.payModel = ? and ";
                parameters.add(new Integer(status));
              }

              if (inceptMonth != null&&!inceptMonth.equals("")&&payMonth!=null&&!payMonth.equals("")) {
                criterion += " (monthPaymentHead.payMonth between ?  and  ? ) and ";
                parameters.add(inceptMonth);
                parameters.add(payMonth);
              }else if (inceptMonth != null&&!inceptMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth = ? and ";
                parameters.add(inceptMonth);
              }else if (payMonth!=null&&!payMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth = ? and ";
                parameters.add(payMonth);
              }
              if (payType != null&&!payType.equals("")) {
                criterion += " monthPayment.payStatus = ?  and ";
                parameters.add(new Integer(payType));
              }
              if (settDate != null&&!settDate.equals("")) {
                criterion += " monthPayment.settDate = ?  and ";
                parameters.add(settDate);
              }
              
              
              if (inceptPayMoney != null&&!inceptPayMoney.equals("") && payMoney != null && !payMoney.equals("")) {
                criterion += " (monthPayment.payMoney between ?  and  ? )and ";
                parameters.add(new BigDecimal(inceptPayMoney));
                parameters.add(new BigDecimal(payMoney));
              }else if (inceptPayMoney != null&&!inceptPayMoney.equals("")) {
                criterion += " monthPayment.payMoney = ? and ";
                parameters.add(new BigDecimal(inceptPayMoney));
              }else if (payMoney != null && !payMoney.equals("")) {
                criterion += " monthPayment.payMoney = ? and ";
                parameters.add(new BigDecimal(payMoney));
              }
              if (inceptSettlementDate != null&&!inceptSettlementDate.equals("") && settlementDate != null && !settlementDate.equals("")) {
                criterion += " (to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') between ?  and  ? ) and ";
                parameters.add(inceptSettlementDate);
                parameters.add(settlementDate);
              }else if (inceptSettlementDate != null&&!inceptSettlementDate.equals("")) {
                criterion += " to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(inceptSettlementDate);
              }else if (settlementDate != null && !settlementDate.equals("")) {
                criterion += " to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(settlementDate);
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

    } catch (Exception e) {
      e.printStackTrace();
    }

    return totalmoney;
  }
  /**
   * 删除单个记录
   * @param monthPaymentTail
   */
  public void delete(MonthPaymentTail monthPaymentTail){
    Validate.notNull(monthPaymentTail);
    getHibernateTemplate().delete(monthPaymentTail);
  }
  /**
   * 删除list
   */
  public void deleteList(List list){
    Validate.notNull(list);
    getHibernateTemplate().deleteAll(list);
  }


  /**
   * 李娟
   * 根据缴存ID查询汇缴清册
   * @param paymentId
   * @return
   * @throws Exception
   */
  public List queryPaymentTailListLJ(final Serializable paymentId) throws Exception{
    List empList=null;
    try {
      empList = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " from MonthPaymentTail monthPaymentTail ";
              Vector parameters = new Vector();
              String criterion = "";

              if (paymentId != null&&!paymentId.equals("")) {
                criterion += "monthPaymentTail.monthPaymentHead.paymentHead.id = ?  and ";
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
  public List queryPaymentTailListLJ_(final Serializable monthpaymentId) throws Exception{
    List empList=null;
    try {
      empList = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " from MonthPaymentTail monthPaymentTail ";
              Vector parameters = new Vector();
              String criterion = "";

              if (monthpaymentId != null&&!monthpaymentId.equals("")) {
                criterion += "monthPaymentTail.monthPaymentHead.id = ?  and ";
                parameters.add(new Integer(monthpaymentId.toString()));
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
  
  /**
   * jj20071218
   * 根据缴存ID查询汇缴清册
   * @param monthpaymentId
   * @return
   * @throws Exception
   */
  public List queryPaymentTailList(final Serializable monthpaymentId) throws Exception{
    List empList=null;
    try {
      empList = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " from MonthPaymentTail monthPaymentTail ";
              Vector parameters = new Vector();
              String criterion = "";

              if (monthpaymentId != null&&!monthpaymentId.equals("")) {
                criterion += "monthPaymentTail.monthPaymentHead.id = ?  and ";
                parameters.add(new Integer(monthpaymentId.toString()));
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
  
  /**
   * 单位补缴金额合计
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
  public BigDecimal queryOrgaddPayMoneyByCriterionsLJ(final Serializable id, final String name,final String status,
      final String payMonth,final String payType, final String payMoney,final String settlementDate,final String compare,
      final SecurityInfo securityInfo,final String settDate,final String collBankId) {
    BigDecimal money=new BigDecimal(0);
    try {
      List list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select distinct orgAddPay.id,orgAddPay.payMoney " +
                  " from MonthPaymentHead monthPaymentHead,OrgAddPay orgAddPay,OrgAddPayBizActivityLog orgAddPayBizActivityLog " +
                  " where monthPaymentHead.paymentHead.id = orgAddPay.id and  " +
                  " monthPaymentHead.paymentHead.id=orgAddPayBizActivityLog.bizid  and orgAddPayBizActivityLog.action=orgAddPay.payStatus " +
                  " and orgAddPay.org.id "+securityInfo.getGjjSecurityHqlSQL();
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += " to_char(monthPaymentHead.paymentHead.org.id ) like ? escape '/'  and ";
                parameters.add("%" + id.toString()+ "%");
              }
              
              if (name != null&&!name.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + name + "%");
              }
              if (status != null&&!status.equals("")) {
                criterion += " orgAddPay.payModel = ? and ";
                parameters.add(new Integer(status));
              }

              if (settDate != null&&!settDate.equals("")) {
                criterion += " orgAddPay.settDate = ?  and ";
                parameters.add(settDate);
              }
              if (payMoney!=null&&!payMoney.equals("")) {
                if(compare != null && !compare.equals("")){
                  if(compare.equals("1")){
                    criterion += " orgAddPay.payMoney > ? and ";
                  }else if(compare.equals("2")){
                    criterion += " orgAddPay.payMoney <  ? and ";
                  }
                }else{
                  criterion += " orgAddPay.payMoney = ? and ";
                }
                parameters.add(new BigDecimal(payMoney));
              }
              if (payType != null&&!payType.equals("")) {
                if(payType.equals("0")){
                  criterion += " (orgAddPay.payStatus = 1 or orgAddPay.payStatus = 2 ) and ";
                }else{
                  criterion += " orgAddPay.payStatus = ?  and ";
                  parameters.add(new Integer(payType));
                }
              }
              
              if (payMonth != null && !payMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth = ? and ";
                parameters.add(payMonth);
              }
              
              if (settlementDate != null && !settlementDate.equals("")) {
                criterion += " (to_char(orgAddPayBizActivityLog.opTime,'yyyymmdd') = ? )and ";
                parameters.add(settlementDate);
              }
              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion +" group by orgAddPay.org.id,orgAddPay.org.orgInfo.name," +
                  "orgAddPay.noteNum,orgAddPay.docNum,orgAddPayBizActivityLog.opTime," +
                  "orgAddPay.payStatus,orgAddPay.id,orgAddPay.payMoney,monthPaymentHead.payMonth,orgAddPay.payModel,orgAddPay.settDate ";
              
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
        Object obj[]=null;
        while(it.hasNext()){
          obj=(Object[])it.next();
          money = money.add(new BigDecimal(obj[1].toString()));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return money;
  }
  /**
   * 查询单位补缴清册
   * @param orgid
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgaddpayEmpList_jj(final Serializable orgid,
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
               String hql ="select max(monthPaymentTail.monthPaymentHead.paymentHead.id),monthPaymentTail.empId,monthPaymentTail.empName,sum(monthPaymentTail.orgRealPay)," +
                  "sum(monthPaymentTail.empRealPay),min(monthPaymentTail.monthPaymentHead.payMonth)," +
                  "max(monthPaymentTail.monthPaymentHead.payMonth)" +
                  " from MonthPaymentTail monthPaymentTail,OrgAddPay orgAddPay " ;
               Vector parameters = new Vector();
               String criterion = "";
               if (orgid != null&&!orgid.equals("")) {
                 criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
                 parameters.add(new Integer(orgid.toString()));
               }
               String ob = orderBy;
               if (ob == null)
                 ob = " max(monthPaymentTail.id) ";

               String od = order;
               if (od == null)
                 od = "ASC";
               if (criterion.length() != 0)
                 criterion=" where monthPaymentTail.monthPaymentHead.paymentHead.id = orgAddPay.id and orgAddPay.payStatus = 1 and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));
               hql = hql + criterion + " group by monthPaymentTail.empId order by " + ob + " " + od ;

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
               List templist = new ArrayList();
               Iterator it=queryList.iterator();
               Object obj[]=null;
               while(it.hasNext()){
                 obj=(Object[])it.next();
                 OrgAddpayInfoDTO orgAddpayInfoDTO=new OrgAddpayInfoDTO();
                 orgAddpayInfoDTO.setId(new Integer(obj[0].toString()));
                 orgAddpayInfoDTO.setEmpId(new Integer(obj[1].toString()));
                 orgAddpayInfoDTO.setEmpName(obj[2].toString());
                 orgAddpayInfoDTO.setOrgAddPaySum(new BigDecimal(obj[3].toString()));
                 orgAddpayInfoDTO.setEmpAddPaySum(new BigDecimal(obj[4].toString()));
                 orgAddpayInfoDTO.setBeginMonth(obj[5].toString());
                 orgAddpayInfoDTO.setEndMonth(obj[6].toString());
                 orgAddpayInfoDTO.setAddPaySum(orgAddpayInfoDTO.getEmpAddPaySum().add(orgAddpayInfoDTO.getOrgAddPaySum()));
                 templist.add(orgAddpayInfoDTO);
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
  /**
   * 查询清册所有记录
   * @param orgid
   * @param orderBy
   * @param order
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryCountOrgaddpayEmpList_jj(final Serializable orgid,
      final String orderBy, final String order) throws NumberFormatException, Exception {
    List list = null;
     try {
       Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
           || "DESC".equalsIgnoreCase(order));

       list = getHibernateTemplate().executeFind(
           new HibernateCallback() {

             public Object doInHibernate(Session session)
                 throws HibernateException, SQLException {
               String hql ="select max(monthPaymentTail.monthPaymentHead.paymentHead.id),monthPaymentTail.empId,monthPaymentTail.empName,sum(monthPaymentTail.orgRealPay)," +
                  "sum(monthPaymentTail.empRealPay),min(monthPaymentTail.monthPaymentHead.payMonth)," +
                  "max(monthPaymentTail.monthPaymentHead.payMonth)" +
                  " from MonthPaymentTail monthPaymentTail,OrgAddPay orgAddPay " ;
               Vector parameters = new Vector();
               String criterion = "";
               if (orgid != null&&!orgid.equals("")) {
                 criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
                 parameters.add(new Integer(orgid.toString()));
               }
               String ob = orderBy;
               if (ob == null)
                 ob = " monthPaymentTail.empId ";
               String od = order;
               if (od == null)
                 od = "ASC";
               if (criterion.length() != 0)
                 criterion=" where monthPaymentTail.monthPaymentHead.paymentHead.id = orgAddPay.id and orgAddPay.payStatus = 1 and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));
               hql = hql + criterion + "group by monthPaymentTail.empId order by " + ob + " " + od ;

               Query query = session.createQuery(hql);
               for (int i = 0; i < parameters.size(); i++) {
                 query.setParameter(i, parameters.get(i));
               }
              
               List queryList=query.list();
               List templist = new ArrayList();
               Iterator it=queryList.iterator();
               Object obj[]=null;
               while(it.hasNext()){
                 obj=(Object[])it.next();
                 OrgAddpayInfoDTO orgAddpayInfoDTO=new OrgAddpayInfoDTO();
                 orgAddpayInfoDTO.setId(new Integer(obj[0].toString()));
                 orgAddpayInfoDTO.setEmpId(new Integer(obj[1].toString()));
                 orgAddpayInfoDTO.setEmpName(obj[2].toString());
                 orgAddpayInfoDTO.setOrgAddPaySum(new BigDecimal(obj[3].toString()));
                 orgAddpayInfoDTO.setEmpAddPaySum(new BigDecimal(obj[4].toString()));
                 orgAddpayInfoDTO.setBeginMonth(obj[5].toString());
                 orgAddpayInfoDTO.setEndMonth(obj[6].toString());
                 orgAddpayInfoDTO.setAddPaySum(orgAddpayInfoDTO.getEmpAddPaySum().add(orgAddpayInfoDTO.getOrgAddPaySum()));
                 templist.add(orgAddpayInfoDTO);
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
  /**
   * 李娟
   * 根据单位ID查询补缴状态为1的单位补缴清册
   * @param orgid
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgaddpayEmpListLJ(final Serializable orgid,
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
              String hql ="select monthPaymentTail from MonthPaymentTail monthPaymentTail,OrgAddPay orgAddPay " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (orgid != null&&!orgid.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgid.toString()));
              }
              String ob = orderBy;
              if (ob == null)
                ob = " monthPaymentTail.id ";

              String od = order;
              if (od == null)
                od = "DESC";
              if (criterion.length() != 0)
                criterion=" where monthPaymentTail.monthPaymentHead.paymentHead.id = orgAddPay.id and orgAddPay.payStatus = 1 and "+
                criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion + "order by " + ob + " " + od ;

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
   * 李娟
   * 根据单位ID查询补缴状态为1的单位补缴清册记录数
   * @param orgid
   * @return
   */
  public int queryOrgaddpayEmpListCountLJ(final Serializable orgid) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql ="select monthPaymentTail from MonthPaymentTail monthPaymentTail,OrgAddPay orgAddPay " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (orgid != null&&!orgid.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgid.toString()));
              }
              
              if (criterion.length() != 0)
                criterion=" where monthPaymentTail.monthPaymentHead.paymentHead.id = orgAddPay.id and orgAddPay.payStatus = 1 and "+
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
  /**
   * 李娟
   * 根据头表ID查询尾表清册
   * @param headId
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public Serializable queryOrgaddpayListingLJ(final Serializable headId) throws NumberFormatException, Exception {
    Serializable tailId = null;
    try {
      tailId = (Serializable)getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql ="select max(monthPaymentTail.id) from MonthPaymentTail monthPaymentTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null&&!headId.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.id = ?  and ";
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

  /**
   * 根据头表ID和职工ID删除AA303
   * @param headId
   * @param empId
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgaddpayListing_jj(final Serializable headId,final String empId) throws NumberFormatException, Exception {
    List tailId = null;
    try {
      tailId = (List)getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql ="select monthPaymentTail.id from MonthPaymentTail monthPaymentTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null&&!headId.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.id = ?  and ";
                parameters.add(new Integer(headId.toString()));
              }
              if (empId != null&&!empId.equals("")) {
                criterion += " monthPaymentTail.empId = ?  and ";
                parameters.add(new Integer(empId));
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

    return tailId;
  }
  /**
   * 根据头表ID和职工ID查询补缴金额
   * @param headId
   * @param empId
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public BigDecimal queryOrgaddpayMoney_jj(final Serializable headId,final String empId) throws NumberFormatException, Exception {
    BigDecimal tailMoney = null;
    try {
      tailMoney = (BigDecimal)getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql ="select sum(monthPaymentTail.orgRealPay+monthPaymentTail.empRealPay) from MonthPaymentTail monthPaymentTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (headId != null&&!headId.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.id = ?  and ";
                parameters.add(new Integer(headId.toString()));
              }
              if (empId != null&&!empId.equals("")) {
                criterion += " monthPaymentTail.empId = ?  and ";
                parameters.add(new Integer(empId));
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

    return tailMoney;
  }
  /**
   * 李娟
   * 查询单位补缴列表
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
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgaddpayListByCriterionsLJ(final Serializable id, final String name,final String status,
      final String payMonth,final String payType, final String payMoney,final String settlementDate,final String settlementDate1,final String compare,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final int page,final String settDate,final String settDate1,final String collBankId) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select orgAddPay.org.id, orgAddPay.org.orgInfo.name," +
                  " orgAddPay.payModel," +
                  " min(monthPaymentHead.payMonth),max(monthPaymentHead.payMonth), " +
                  " orgAddPay.noteNum,orgAddPay.docNum,to_char(orgAddPayBizActivityLog.opTime,'yyyymmdd')," +
                  " orgAddPay.payStatus, orgAddPay.id,orgAddPay.payMoney ,orgAddPay.settDate " +
                  " from OrgAddPay orgAddPay,MonthPaymentHead monthPaymentHead,OrgAddPayBizActivityLog orgAddPayBizActivityLog " +
                  " where monthPaymentHead.paymentHead.id = orgAddPay.id and  " +
                  " monthPaymentHead.paymentHead.id=orgAddPayBizActivityLog.bizid and orgAddPayBizActivityLog.action=orgAddPay.payStatus " +
                  " and orgAddPay.org.id "+securityInfo.getGjjSecurityHqlSQL();
              
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += " to_char(monthPaymentHead.paymentHead.org.id ) like ? escape '/'  and ";
                parameters.add("%" + id.toString()+ "%");
              }
              
              if (name != null&&!name.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + name + "%");
              }
              if (status != null&&!status.equals("")) {
                criterion += " orgAddPay.payModel = ? and ";
                parameters.add(new Integer(status));
              }
              if (settDate != null&&!settDate.equals("")) {
                criterion += " orgAddPay.settDate = ? and ";
                parameters.add(settDate);
              }
              if (settDate != null&&!settDate.equals("")&&settDate1!=null&&!settDate1.equals("")) {
                criterion += " (orgAddPay.settDate between ?  and  ? ) and ";
                parameters.add(settDate);
                parameters.add(settDate1);
              }else if (settDate != null&&!settDate.equals("")) {
                criterion += " orgAddPay.settDate = ? and ";
                parameters.add(settDate);
              }else if (settDate1!=null&&!settDate1.equals("")) {
                criterion += " orgAddPay.settDate = ?  and ";
                parameters.add(settDate1);
              }
              if (payMoney!=null&&!payMoney.equals("")) {
                if(compare != null && !compare.equals("")){
                  if(compare.equals("1")){
                    criterion += " orgAddPay.payMoney > ? and ";
                  }else if(compare.equals("2")){
                    criterion += " orgAddPay.payMoney <  ? and ";
                  }
                }else{
                  criterion += " orgAddPay.payMoney = ? and ";
                }
                parameters.add(new BigDecimal(payMoney));
              }
              if (payType != null&&!payType.equals("")) {
                if(payType.equals("0")){
                  criterion += " (orgAddPay.payStatus = 1 or orgAddPay.payStatus = 2 ) and ";
                }else{
                  criterion += " orgAddPay.payStatus = ?  and ";
                  parameters.add(new Integer(payType));
                }
              } 
              
              if (payMonth != null && !payMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth = ? and ";
                parameters.add(payMonth);
              }
              if (settlementDate != null&&!settlementDate.equals("")&&settlementDate1!=null&&!settlementDate1.equals("")) {
                criterion += " (to_char(orgAddPayBizActivityLog.opTime,'yyyymmdd') between ?  and  ? ) and ";
                parameters.add(settlementDate);
                parameters.add(settlementDate1);
              }else if (settlementDate != null&&!settlementDate.equals("")) {
                criterion += " to_char(orgAddPayBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(settlementDate);
              }else if (settlementDate1!=null&&!settlementDate1.equals("")) {
                criterion += " to_char(orgAddPayBizActivityLog.opTime,'yyyymmdd') = ?  and ";
                parameters.add(settlementDate1);
              }
              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              String ob = orderBy;
              if (ob == null)
                ob = " orgAddPay.id ";

              String od = order;
              if (od == null)
                od = "DESC";

              hql = hql + criterion +" group by orgAddPay.org.id,orgAddPay.org.orgInfo.name,orgAddPayBizActivityLog.bizid," +
                  "orgAddPay.noteNum,orgAddPay.docNum,orgAddPayBizActivityLog.opTime," +
                  "orgAddPay.payStatus,orgAddPay.id,orgAddPay.payMoney,orgAddPay.payModel,orgAddPay.settDate "+ "order by " + ob + " " + od ;
              
              String hsqlCount=" select count(distinct aa303.emp_id) from AA303 aa303,AA302 AA302 where AA303.MONTH_PAY_HEAD_ID = AA302.ID and aa302.pay_head_id = ?";
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
                it=query.iterate();
              }
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                OrgaddpayMaintainDto orgaddpayMaintainDto=new OrgaddpayMaintainDto();
                orgaddpayMaintainDto.setOrgId(new Integer(obj[0].toString()));
                orgaddpayMaintainDto.setOrgName(obj[1].toString());
                if(obj[6] != null){
                  orgaddpayMaintainDto.setDocNum(obj[6].toString());
                }
                if(obj[5] != null){
                  orgaddpayMaintainDto.setNoteNum(obj[5].toString());
                }
                orgaddpayMaintainDto.setStartPayMonth(obj[3].toString());
                orgaddpayMaintainDto.setPayMonth(obj[4].toString());
                orgaddpayMaintainDto.setOpTime(obj[7].toString());
                if(obj[2].toString().equals("3")){
                  orgaddpayMaintainDto.setPayMode("只缴职工");
                }else if(obj[2].toString().equals("2")){
                  orgaddpayMaintainDto.setPayMode("只缴单位");
                }else {
                  orgaddpayMaintainDto.setPayMode("均缴");
                }
                orgaddpayMaintainDto.setPay(new BigDecimal(obj[10].toString()).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                orgaddpayMaintainDto.setPayStatus(obj[8].toString());
                orgaddpayMaintainDto.setId(obj[9].toString());
                if(obj[11] != null){
                  orgaddpayMaintainDto.setSettDate(obj[11].toString());
                }              
                Query queryCount = session.createSQLQuery(hsqlCount);
                queryCount.setString(0,orgaddpayMaintainDto.getId());            
                Iterator it1=queryCount.list().iterator();
                int count=0;
                if(it1.hasNext()){
                  count=(Integer.parseInt(it1.next().toString()));
                }
                orgaddpayMaintainDto.setPersonCounts(Integer.toString(count));
                temp_list.add(orgaddpayMaintainDto);
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
   * jj
   * 查询单位补缴列表打印
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
   * @param orderBy
   * @param order
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgaddpayListByCriterionsPrint_jj(final Serializable id, final String name,final String status,
      final String payMonth,final String payType, final String payMoney,final String settlementDate,final String settlementDate1,final String compare,
      final String orderBy, final String order, final SecurityInfo securityInfo,final String settDate,final String settDate1,final String collBankId) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select orgAddPay.org.id, orgAddPay.org.orgInfo.name," +
                  " orgAddPay.payModel," +
                  " min(monthPaymentHead.payMonth),max(monthPaymentHead.payMonth), " +
                  " orgAddPay.noteNum,orgAddPay.docNum,to_char(orgAddPayBizActivityLog.opTime,'yyyymmdd')," +
                  " orgAddPay.payStatus, orgAddPay.id,orgAddPay.payMoney ,orgAddPay.settDate " +
                  " from OrgAddPay orgAddPay,MonthPaymentHead monthPaymentHead,OrgAddPayBizActivityLog orgAddPayBizActivityLog " +
                  " where monthPaymentHead.paymentHead.id = orgAddPay.id and  " +
                  " monthPaymentHead.paymentHead.id=orgAddPayBizActivityLog.bizid and orgAddPayBizActivityLog.action=orgAddPay.payStatus " +
                  " and orgAddPay.org.id "+securityInfo.getGjjSecurityHqlSQL();
              
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += " to_char(monthPaymentHead.paymentHead.org.id ) like ? escape '/'  and ";
                parameters.add("%" + id.toString()+ "%");
              }
              
              if (name != null&&!name.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + name + "%");
              }
              if (status != null&&!status.equals("")) {
                criterion += " orgAddPay.payModel = ? and ";
                parameters.add(new Integer(status));
              }
              if (settDate != null&&!settDate.equals("")) {
                criterion += " orgAddPay.settDate = ? and ";
                parameters.add(settDate);
              }
              if (settDate != null&&!settDate.equals("") && settDate1 != null && !settDate1.equals("")) {
                criterion += " (orgAddPay.settDate between ?  and  ? ) and ";
                parameters.add(settDate);
                parameters.add(settDate1);
              }else if (settDate != null&&!settDate.equals("")) {
                criterion += " orgAddPay.settDate = ? and ";
                parameters.add(settDate);
              }else if (settDate1 != null && !settDate1.equals("")) {
                criterion += " orgAddPay.settDate = ? and ";
                parameters.add(settDate1);
              }
              if (payMoney!=null&&!payMoney.equals("")) {
                if(compare != null && !compare.equals("")){
                  if(compare.equals("1")){
                    criterion += " orgAddPay.payMoney > ? and ";
                  }else if(compare.equals("2")){
                    criterion += " orgAddPay.payMoney <  ? and ";
                  }
                }else{
                  criterion += " orgAddPay.payMoney = ? and ";
                }
                parameters.add(new BigDecimal(payMoney));
              }
              if (payType != null&&!payType.equals("")) {
                if(payType.equals("0")){
                  criterion += " (orgAddPay.payStatus = 1 or orgAddPay.payStatus = 2 ) and ";
                }else{
                  criterion += " orgAddPay.payStatus = ?  and ";
                  parameters.add(new Integer(payType));
                }
              } 
              
              if (payMonth != null && !payMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth = ? and ";
                parameters.add(payMonth);
              }
              if (settlementDate != null && !settlementDate.equals("")) {
                criterion += " (to_char(orgAddPayBizActivityLog.opTime,'yyyymmdd') = ? )and ";
                parameters.add(settlementDate);
              }
              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              String ob = orderBy;
              if (ob == null)
                ob = " orgAddPay.id ";

              String od = order;
              if (od == null)
                od = "DESC";

              hql = hql + criterion +" group by orgAddPay.org.id,orgAddPay.org.orgInfo.name,orgAddPayBizActivityLog.bizid," +
                  "orgAddPay.noteNum,orgAddPay.docNum,orgAddPayBizActivityLog.opTime," +
                  "orgAddPay.payStatus,orgAddPay.id,orgAddPay.payMoney,orgAddPay.payModel,orgAddPay.settDate "+ "order by " + ob + " " + od ;
              
              String hsqlCount=" select count(distinct aa303.emp_id) from AA303 aa303,AA302 AA302 where AA303.MONTH_PAY_HEAD_ID = AA302.ID and aa302.pay_head_id = ?";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
                  
              Iterator it=query.iterate();
 
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                OrgaddpayMaintainDto orgaddpayMaintainDto=new OrgaddpayMaintainDto();
                orgaddpayMaintainDto.setOrgId(new Integer(obj[0].toString()));
                orgaddpayMaintainDto.setOrgName(obj[1].toString());
                if(obj[6] != null){
                  orgaddpayMaintainDto.setDocNum(obj[6].toString());
                }
                if(obj[5] != null){
                  orgaddpayMaintainDto.setNoteNum(obj[5].toString());
                }
                orgaddpayMaintainDto.setStartPayMonth(obj[3].toString());
                orgaddpayMaintainDto.setPayMonth(obj[4].toString());
                orgaddpayMaintainDto.setOpTime(obj[7].toString());
                if(obj[2].toString().equals("3")){
                  orgaddpayMaintainDto.setPayMode("只缴职工");
                }else if(obj[2].toString().equals("2")){
                  orgaddpayMaintainDto.setPayMode("只缴单位");
                }else {
                  orgaddpayMaintainDto.setPayMode("均缴");
                }
                orgaddpayMaintainDto.setPay(new BigDecimal(obj[10].toString()).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                orgaddpayMaintainDto.setPayStatus(obj[8].toString());
                orgaddpayMaintainDto.setId(obj[9].toString());
                if(obj[11] != null){
                  orgaddpayMaintainDto.setSettDate(obj[11].toString());
                }              
                Query queryCount = session.createSQLQuery(hsqlCount);
                queryCount.setString(0,orgaddpayMaintainDto.getId());            
                Iterator it1=queryCount.list().iterator();
                int count=0;
                if(it1.hasNext()){
                  count=(Integer.parseInt(it1.next().toString()));
                }
                orgaddpayMaintainDto.setPersonCounts(Integer.toString(count));
                temp_list.add(orgaddpayMaintainDto);
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
   * 李娟
   * 查询单位补缴列表记录数
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
  public int queryOrgaddPayCountByCriterionsLJ(final Serializable id, final String name,final String status,
      final String payMonth,final String payType, final String payMoney,final String settlementDate,final String settlementDate1,final String compare,
      final SecurityInfo securityInfo,final String settDate,final String settDate1,final String collBankId) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select count(*) from OrgAddPay orgAddPay,MonthPaymentHead monthPaymentHead, OrgAddPayBizActivityLog orgAddPayBizActivityLog " +
                  " where monthPaymentHead.paymentHead.id = orgAddPay.id and  " +
                  " monthPaymentHead.paymentHead.id=orgAddPayBizActivityLog.bizid  and orgAddPayBizActivityLog.action=orgAddPay.payStatus " +
                  " and orgAddPay.org.id "+securityInfo.getGjjSecurityHqlSQL();
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += " to_char(monthPaymentHead.paymentHead.org.id ) like ? escape '/'  and ";
                parameters.add("%" + id.toString()+ "%");
              }
              
              if (name != null&&!name.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + name + "%");
              }

              if (status != null&&!status.equals("")) {
                criterion += " orgAddPay.payModel = ? and ";
                parameters.add(new Integer(status));
              }

              if (settDate != null&&!settDate.equals("") && settDate1 != null && !settDate1.equals("")) {
                criterion += " (orgAddPay.settDate between ?  and  ? ) and ";
                parameters.add(settDate);
                parameters.add(settDate1);
              }else if (settDate != null&&!settDate.equals("")) {
                criterion += " orgAddPay.settDate = ? and ";
                parameters.add(settDate);
              }else if (settDate1 != null && !settDate1.equals("")) {
                criterion += " orgAddPay.settDate = ? and ";
                parameters.add(settDate1);
              }
              if (payMoney!=null&&!payMoney.equals("")) {
                if(compare != null && !compare.equals("")){
                  if(compare.equals("1")){
                    criterion += " orgAddPay.payMoney > ? and ";
                  }else if(compare.equals("2")){
                    criterion += " orgAddPay.payMoney <  ? and ";
                  }
                }else{
                  criterion += " orgAddPay.payMoney = ? and ";
                }
                parameters.add(new BigDecimal(payMoney));
              }

              if (payType != null&&!payType.equals("")) {
                if(payType.equals("0")){
                  criterion += " (orgAddPay.payStatus = 1 or orgAddPay.payStatus = 2 ) and ";
                }else{
                  criterion += " orgAddPay.payStatus = ?  and ";
                  parameters.add(new Integer(payType));
                }
              } 
              
              if (payMonth != null && !payMonth.equals("")) {
                criterion += " monthPaymentHead.payMonth = ? and ";
                parameters.add(payMonth);
              }
              if (settlementDate != null&&!settlementDate.equals("") && settlementDate1 != null && !settlementDate1.equals("")) {
                criterion += " (to_char(orgAddPayBizActivityLog.opTime,'yyyymmdd') between ?  and  ?) and ";
                parameters.add(settlementDate);
                parameters.add(settlementDate1);
              }else if (settlementDate != null&&!settlementDate.equals("")) {
                criterion += " to_char(orgAddPayBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(settlementDate);
              }else if (settlementDate1 != null && !settlementDate1.equals("")) {
                criterion += " to_char(orgAddPayBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(settlementDate1);
              }
              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion +" group by orgAddPay.org.id,orgAddPay.org.orgInfo.name," +
                  "orgAddPay.noteNum,orgAddPay.docNum,orgAddPayBizActivityLog.opTime," +
        //          "orgAddPay.payStatus,orgAddPay.id,orgAddPay.payMoney,monthPaymentHead.payMonth,orgAddPay.payModel,orgAddPay.settDate ";
                  "orgAddPay.payStatus,orgAddPay.id,orgAddPay.payMoney,orgAddPay.payModel,orgAddPay.settDate ";
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

  public List queryPaymentTailListMXLJ(final String paymentid,
      final String orderBy, final String order, final int start,
      final int pageSize) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select monthPaymentTail.empId, nvl(sum(monthPaymentTail.orgRealPay),0),nvl(sum(monthPaymentTail.empRealPay),0), " +
              " min(monthPaymentTail.monthPaymentHead.payMonth),max(monthPaymentTail.monthPaymentHead.payMonth),monthPaymentTail.empName " +
              " from MonthPaymentTail monthPaymentTail,Org org" ;

              Vector parameters = new Vector();
              String criterion = "";
              if (paymentid != null&&!paymentid.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.id = ?  and ";
                parameters.add(new Integer(paymentid));
              }         
              if (criterion.length() != 0)   
                 criterion=" where org.id=monthPaymentTail.monthPaymentHead.paymentHead.org.id and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              String ob = orderBy;
              if (ob == null)
                ob = " monthPaymentTail.empId";

              String od = order;
              if (od == null)
                od = "ASC";
              hql = hql + criterion +" group by monthPaymentTail.empId " + "order by " + ob + " " + od ;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);       
              
              Iterator it=query.iterate();
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                MonthpayTbWindowDto monthpayTbWindowDto=new MonthpayTbWindowDto();
                monthpayTbWindowDto.setEmpid(new Integer(obj[0].toString()));
                monthpayTbWindowDto.setOrgpay(obj[1].toString());
                monthpayTbWindowDto.setEmppay(obj[2].toString());
                monthpayTbWindowDto.setSumpay((new BigDecimal(obj[1].toString()).add(new BigDecimal(obj[2].toString()))).toString());
                monthpayTbWindowDto.setPayMonth(obj[3].toString()+"-"+obj[4].toString());
                monthpayTbWindowDto.setEmpName(obj[5].toString());
                temp_list.add(monthpayTbWindowDto);
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
   * 根据汇缴ID查询汇缴清册
   * @param paymentid
   * @param orderBy
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryPaymentTailListMXExport_jj(final String paymentid,final String[] orderBy) throws NumberFormatException, Exception {
    List list = null;
    try {
     
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select monthPaymentTail.empId, nvl(max(monthPaymentTail.orgShouldPay),0),nvl(max(monthPaymentTail.empShouldPay),0), " +
              " min(monthPaymentTail.monthPaymentHead.payMonth),max(monthPaymentTail.monthPaymentHead.payMonth),monthPaymentTail.empName," +
              " max(monthPaymentTail.salaryBase),max(monthPaymentTail.orgRate),max(monthPaymentTail.empRate) " +
              " from MonthPaymentTail monthPaymentTail,Emp emp" ;

              Vector parameters = new Vector();
              String criterion = "";
              String ob = "";
              if (paymentid != null&&!paymentid.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.id = ?  and ";
                parameters.add(new Integer(paymentid));
              }     
              if (orderBy != null && orderBy.length > 0) {
                for (int i = 0; i < orderBy.length; i++) {
                  if(orderBy[i]=="monthPaymentTail.empName"){
                    ob += "nlssort( "+orderBy[i].toString()+" ,'NLS_SORT=SCHINESE_PINYIN_M'),";
                  }else {
                  ob += " "+orderBy[i].toString()+" , ";
                  }
                }
                ob = ob.substring(0, ob.lastIndexOf(","));
                ob += " ";
              }
              if (criterion.length() != 0)   
                 criterion=" where emp.empId=monthPaymentTail.empId and emp.org.id = monthPaymentTail.monthPaymentHead.paymentHead.org.id and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion +" group by monthPaymentTail.empId,monthPaymentTail.orgShouldPay,emp.payStatus " + "order by " + ob +" ASC" ;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              Iterator it=query.iterate();
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                MonthpayTbWindowDto monthpayTbWindowDto=new MonthpayTbWindowDto();
                monthpayTbWindowDto.setEmpid(new Integer(obj[0].toString()));
                monthpayTbWindowDto.setOrgpay(obj[1].toString());
                monthpayTbWindowDto.setEmppay(obj[2].toString());
                monthpayTbWindowDto.setSumpay((new BigDecimal(obj[1].toString()).add(new BigDecimal(obj[2].toString()))).toString());
                monthpayTbWindowDto.setPayMonth(obj[3].toString()+"-"+obj[4].toString());
                monthpayTbWindowDto.setEmpName(obj[5].toString());
                monthpayTbWindowDto.setSalaryBase(new BigDecimal(obj[6].toString()));
                monthpayTbWindowDto.setOrgRate(new BigDecimal(obj[7].toString()));
                monthpayTbWindowDto.setEmpRate(new BigDecimal(obj[8].toString()));
                temp_list.add(monthpayTbWindowDto);
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
  public int queryPaymentTailCountMXLJ(final String paymentid) {
    Integer count=new Integer(0);
    //List list=new ArrayList();
    try {
      count =(Integer)getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql =" select count(distinct monthPaymentTail.empId)  " +
              " from MonthPaymentTail monthPaymentTail " ;

              Vector parameters = new Vector();
              String criterion = "";
              if (paymentid != null&&!paymentid.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.id = ?  and ";
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
              Iterator it=query.iterate();
              Integer counttemp=new Integer(0);
              if(it.hasNext()){
                counttemp=(Integer)it.next();
              }
//              return query.list();
              return counttemp;
            }
          }

      );
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }
  public List queryPaymentTailListMXHJLJ(final String paymentid) throws NumberFormatException, Exception {
    List list = null;
    try {
      
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select nvl(sum(monthPaymentTail.orgRealPay),0),nvl(sum(monthPaymentTail.empRealPay),0) " +
              " from MonthPaymentTail monthPaymentTail " ;

              Vector parameters = new Vector();
              String criterion = "";
              if (paymentid != null&&!paymentid.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.id = ?  and ";
                parameters.add(new Integer(paymentid));
              } 
              if (criterion.length() != 0)   
                 criterion=" where  "+
                 criterion.substring(0, criterion.lastIndexOf("and"));
      
              hql = hql + criterion;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }    
              
              Iterator it=query.iterate();
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                MonthpayTbWindowDto monthpayTbWindowDto=new MonthpayTbWindowDto();
                //monthpayTbWindowDto.setCounts(obj[0].toString());
                monthpayTbWindowDto.setOrgpay(obj[0].toString());
                monthpayTbWindowDto.setEmppay(obj[1].toString());
                monthpayTbWindowDto.setSumpay((new BigDecimal(obj[0].toString()).add(new BigDecimal(obj[1].toString()))).toString());
                temp_list.add(monthpayTbWindowDto);
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
  public List queryOrgaddpayEmpListMXLJ(final String paymentid,
      final String orderBy, final String order, final int start,final int pageSize) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind( new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql =" from MonthPaymentTail monthPaymentTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (paymentid != null&&!paymentid.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.id = ?  and ";
                parameters.add(new Integer(paymentid));
              }
              String ob = orderBy;
              if (ob == null)
                ob = " monthPaymentTail.id ";

              String od = order;
              if (od == null)
                od = "DESC";
              if (criterion.length() != 0)
                criterion=" where "+
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
  /**
   * 李娟
   * 根据单位ID查询补缴状态为1的单位补缴清册记录数
   * @param orgid
   * @return
   */
  public int queryOrgaddpayEmpListCountMXLJ(final String paymentid) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql =" from MonthPaymentTail monthPaymentTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (paymentid != null&&!paymentid.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.id = ?  and ";
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

  /**
   * 于庆丰　根据汇缴头表(aa302)ID查询汇缴尾表(aa303)empId 的聚合
   * @param id
   * @return
   */
  public List queryByMonthPaymentHeadId(final Serializable id) {
    
    List list = null;
    try{
    Validate.notNull(id);
    list = (List) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select monthPaymentTail from MonthPaymentTail monthPaymentTail where monthPaymentTail.monthPaymentHead.id = ?";
            Vector parameters = new Vector();
            parameters.add(id);

            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));
            return query.list();
          }
        });
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 于庆丰　根据empID查询个人汇缴＋单位汇缴的总金额
   * @param id
   * @return
   */
  public List querySumPayByPaymentHeadId(final Serializable id) {
    List list = new ArrayList();
    Validate.notNull(id);
    list = (List)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            //String hql = "select sum(monthPaymentTail.orgRealPay + monthPaymentTail.empRealPay) from MonthPaymentTail monthPaymentTail where monthPaymentTail.empId = ? ";
            String hql = "select aa303.emp_id, sum(aa303.org_real_pay+aa303.emp_real_pay) from aa303 where aa303.month_pay_head_id in (select aa302.id from aa302,aa301 where aa301.id= ?  and aa301.id=aa302.pay_head_id) group by aa303.emp_id";
            Vector parameters = new Vector();
            parameters.add(id);
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, parameters.get(0));

            return query.list();
          }
        });
    return list;
  }

  /**
   * 于庆丰　根据paymentHeadid查询个人汇缴＋单位汇缴的总金额
   * @param id
   * @return
   */
  public BigDecimal querySumPayByEmpId(final Serializable idd) {
    BigDecimal sumPay = null;
    Validate.notNull(idd);
    sumPay = (BigDecimal)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(monthPaymentTail.orgRealPay + monthPaymentTail.empRealPay) from MonthPaymentTail monthPaymentTail where monthPaymentTail.empId = ? ";
            Vector parameters = new Vector();
            parameters.add(idd);
            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));

            return query.uniqueResult();
          }
        });
    return sumPay;
  }
  
  /**
   * 王志强
   * @param orgid
   * @return
   */

  public List queryOrgMonthPaymentListWZQ(final String orgid) {
   
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind( new HibernateCallback() {

            public Object doInHibernate(Session session)throws HibernateException, SQLException {
             
             String hql = " from MonthPaymentTail monthPaymentTail where  monthPaymentTail.monthPaymentHead.paymentHead.org.id = ? ";
             Vector parameters = new Vector();

              if (orgid != null&&!orgid.equals("")) {
                parameters.add(new Integer(orgid));
              }
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
 * 王志强
 * @param orgid
 * @param year
 * @param orderBy
 * @param order
 * @param start
 * @param pageSize
 * @return
 * @throws NumberFormatException
 * @throws Exception
 */

  public List queryPaymentTailListWZQ(final String orgid,final String year,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              
              String hql =" select nvl(sum(monthPaymentTail.orgRealPay),0),"+
             " nvl(sum(monthPaymentTail.empRealPay),0),monthPaymentTail.monthPaymentHead.payMonth "+
              "from MonthPaymentTail monthPaymentTail  ";
              
              Vector parameters = new Vector();  
              String criterion = "";
             
              if (orgid != null&&!orgid.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgid));
              }         
              
              if (year != null && !year.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.payMonth like ? escape '/' and  ";
                parameters.add("%"+year+"%");
              }  

              if (criterion.length() != 0)   
                 criterion=" where " + criterion.substring(0, criterion.lastIndexOf("and"));
               

              String ob = orderBy;
              if (ob == null)
                ob = " monthPaymentTail.empId";
              String od = order;
              if (od == null)
                od = "ASC";
//              hql = hql + criterion +" order by " + ob + " " + od ;
              hql = hql + criterion +" group by monthPaymentTail.monthPaymentHead.payMonth ";
              
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);    
              Iterator it=query.iterate();
              List temp_list = new ArrayList();
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                OrgpaymentstatisticsDTO orgpaymentstatisticsDTO=new OrgpaymentstatisticsDTO();
                orgpaymentstatisticsDTO.setOrgPay(new BigDecimal(obj[0].toString()));
                orgpaymentstatisticsDTO.setEmpPay(new BigDecimal(obj[1].toString()));
                orgpaymentstatisticsDTO.setPay_month(new Integer(obj[2].toString()));
                temp_list.add(orgpaymentstatisticsDTO);
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
   * 王志强
   * @param orgid
   * @param year
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  
  public int queryPaymentTailCountWZQ(final String orgid,final String year,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    
    List list = new ArrayList();
    final List temp_list = new ArrayList();
    int count=0;
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              
              String hql =" select nvl(sum(monthPaymentTail.orgRealPay),0),"+
             " nvl(sum(monthPaymentTail.empRealPay),0),monthPaymentTail.monthPaymentHead.payMonth "+
              "from MonthPaymentTail monthPaymentTail  ";
              
              Vector parameters = new Vector();  
              String criterion = "";
             
              if (orgid != null&&!orgid.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.org.id = ?  and ";
                parameters.add(new Integer(orgid));
              }         
              if (year != null && !year.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.payMonth like ? escape '/' and  ";
                parameters.add("%"+year+"%");
              }  
              if (criterion.length() != 0)   
                 criterion=" where " + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion +" group by monthPaymentTail.monthPaymentHead.payMonth ";
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator it=query.iterate();
              
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                OrgpaymentstatisticsDTO orgpaymentstatisticsDTO=new OrgpaymentstatisticsDTO();
                orgpaymentstatisticsDTO.setOrgPay(new BigDecimal(obj[0].toString()));
                orgpaymentstatisticsDTO.setEmpPay(new BigDecimal(obj[1].toString()));
                orgpaymentstatisticsDTO.setPay_month(new Integer(obj[2].toString()));
                temp_list.add(orgpaymentstatisticsDTO);
              }
              return temp_list;
            }
          }

      );
     count = temp_list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  public List queryPaymentTailListMXPrintLJ(final String paymentid,
      final String orderBy, final String order) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select monthPaymentTail.empId, nvl(sum(monthPaymentTail.orgRealPay),0),nvl(sum(monthPaymentTail.empRealPay),0), " +
              " min(monthPaymentTail.monthPaymentHead.payMonth),max(monthPaymentTail.monthPaymentHead.payMonth),monthPaymentTail.empName " +
              " from MonthPaymentTail monthPaymentTail,Org org" ;

              Vector parameters = new Vector();
              String criterion = "";
              if (paymentid != null&&!paymentid.equals("")) {
                criterion += " monthPaymentTail.monthPaymentHead.paymentHead.id = ?  and ";
                parameters.add(new Integer(paymentid));
              }         
              if (criterion.length() != 0)   
                 criterion=" where org.id=monthPaymentTail.monthPaymentHead.paymentHead.org.id and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              String ob = orderBy;
              if (ob == null)
                ob = " monthPaymentTail.empId";

              String od = order;
              if (od == null)
                od = "ASC";
              hql = hql + criterion +" group by monthPaymentTail.empId " + "order by " + ob + " " + od ;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

//              query.setFirstResult(start);
//              if (pageSize > 0)
//                query.setMaxResults(pageSize);       
              
              Iterator it=query.iterate();
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                MonthpayTbWindowDto monthpayTbWindowDto=new MonthpayTbWindowDto();
                monthpayTbWindowDto.setEmpid(new Integer(obj[0].toString()));
                monthpayTbWindowDto.setOrgpay(obj[1].toString());
                monthpayTbWindowDto.setEmppay(obj[2].toString());
                monthpayTbWindowDto.setSumpay((new BigDecimal(obj[1].toString()).add(new BigDecimal(obj[2].toString()))).toString());
                monthpayTbWindowDto.setPayMonth(obj[3].toString()+"-"+obj[4].toString());
                monthpayTbWindowDto.setEmpName(obj[5].toString());
                temp_list.add(monthpayTbWindowDto);
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
   * 李娟
   * 查询303均缴
   * @param orgid
   * @param payMonth
   * @return
   */
  public BigDecimal queryMnothpaymentTailMXAll(final Serializable headid) {
    BigDecimal sumPay = null;
    sumPay =(BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select nvl(sum(monthPaymentTail.orgShouldPay+monthPaymentTail.empShouldPay),0) from MonthPaymentTail monthPaymentTail ";
            String criterion = "";
            if (headid != null && !headid.equals("")) {
              criterion += " monthPaymentTail.monthPaymentHead.id = ?  and ";
            }
            if (criterion.length() != 0)
              criterion = "where monthPaymentTail.monthPaymentHead.paymentHead.payType_='A' and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(headid.toString()));
               
            return query.uniqueResult();
          }
        });

    return sumPay;
  }
  /**
   * 李娟
   * 查询303只缴单位
   * @param orgid
   * @param payMonth
   * @return
   */
  public BigDecimal queryMnothpaymentTailMXOrg(final Serializable headid) {
    BigDecimal sumPay = null;
    sumPay =(BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select nvl(sum(monthPaymentTail.orgShouldPay),0) from MonthPaymentTail monthPaymentTail ";
            String criterion = "";
            if (headid != null && !headid.equals("")) {
              criterion += " monthPaymentTail.monthPaymentHead.id = ?  and ";
            }
            if (criterion.length() != 0)
              criterion = "where monthPaymentTail.monthPaymentHead.paymentHead.payType_='A' and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(headid.toString()));
               
            return query.uniqueResult();
          }
        });

    return sumPay;
  } 
  /**
   * 李娟
   * 查询303只缴职工
   * @param orgid
   * @param payMonth
   * @return
   */
  public BigDecimal queryMnothpaymentTailMXEmp(final Serializable headid) {
    BigDecimal sumPay = null;
    sumPay =(BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select nvl(sum(monthPaymentTail.empShouldPay),0) from MonthPaymentTail monthPaymentTail ";
            String criterion = "";
            if (headid != null && !headid.equals("")) {
              criterion += " monthPaymentTail.monthPaymentHead.id = ?  and ";
            }
            if (criterion.length() != 0)
              criterion = "where monthPaymentTail.monthPaymentHead.paymentHead.payType_='A' and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(headid.toString()));
               
            return query.uniqueResult();
          }
        });

    return sumPay;
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
           sql = "select count(distinct a303.emp_id) from AA303 a303,AA302 a302,AA301 a301 where a303.month_pay_head_id = a302.id and a302.pay_head_id = a301.id and a301.id="+paymentheadId;              
        }
        Query query = session.createSQLQuery(sql);
        return new Integer(query.uniqueResult().toString());
       
      }
    });
  
    return empCount;
    
  }
 
  /**
   * 李鹏
   * 单位补缴导出使用
   * @param paymentId
   * @return
   * @throws Exception
   */
  public List queryPaymentTailListLP(final Serializable paymentId,final String[] orderBy ,final String orgId) throws Exception{
    List empList=null;
    try {
            empList = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select monthPaymentTail from MonthPaymentTail monthPaymentTail,Emp emp ";
              Vector parameters = new Vector();
              String criterion = "";
              if (paymentId != null&&!paymentId.equals("")) {
                criterion += "monthPaymentTail.monthPaymentHead.paymentHead.id = ?";
                //郑新修改单位补缴批量导出
                criterion +=" and monthPaymentTail.monthPaymentHead.id = (select max(c.id) from MonthPaymentHead c where c.paymentHead = monthPaymentTail.monthPaymentHead.paymentHead ) and " +
                    " emp.empId=monthPaymentTail.empId and " +
                    "";
                parameters.add(new Integer(paymentId.toString()));
              }
              if(orgId !=null&&!orgId.equals("")){
                criterion += " emp.org.id = ? and ";
                parameters.add(new Integer(orgId));
                
              }
              String ob = "";
              if (orderBy != null && orderBy.length > 0) {
                      for (int i = 0; i < orderBy.length; i++) {
                        if(orderBy[i]=="emp.empInfo.name"){
                          ob += "nlssort( "+orderBy[i].toString()+" ,'NLS_SORT=SCHINESE_PINYIN_M'),";
                        }else {
                        ob += " "+orderBy[i].toString()+" , ";
                        }
                      }
                      ob = ob.substring(0, ob.lastIndexOf(","));
                      ob += " ";
                    }
              String od = "ASC";     
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
                hql = hql + criterion + "order by " + ob +" "+od;
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
   * 查询数据库中是否有值
   * 刘聃添加
   * */
  public List queryByIdld(final Serializable id) {  
    List list=null;
    try{
       list=getHibernateTemplate().executeFind(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String empid;
                String  hql = "select aa303.emp_id from aa303 aa303 where aa303.id= ?";              
                Query query = session.createSQLQuery(hql);
                query.setParameter(0, id);
                           
                return query.list();           
            }
          }
      );
    }catch(Exception s){
      s.printStackTrace();
    }
    return list;   
  }
  
  /*
   * 查询补缴职工数
   */
  public Integer queryAddPaymentPersonCounts(final Serializable paymentheadId){
    Integer empCount  = (Integer) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String sql = "";
        if(paymentheadId != null && !"".equals(paymentheadId)){
           sql = "select count(distinct a304.emp_id) from AA304 a304 where a304.pay_head_id ="+paymentheadId;              
        }
        Query query = session.createSQLQuery(sql);
        return new Integer(query.uniqueResult().toString());
       
      }
    });
  
    return empCount;
    
  }
  
  
 /*
  * 缴存维护列表-不分页
  */ 
  
  public List queryPaymentListByCriterions_wxg(final Serializable id, final String name,final String status,
      final String inceptMonth,final String payMonth,final String payType,final String inceptPayMoney,
      final String payMoney,final String inceptSettlementDate,final String settlementDate,
      final SecurityInfo securityInfo,final String settDate,final String settDate1,final String collBankId) throws NumberFormatException, Exception {
    List list = null;
    try {
     

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql =" select monthPayment.org.id,monthPayment.org.orgInfo.name," +
                  " monthPayment.payModel, " +
                  " min(monthPaymentHead.payMonth),max(monthPaymentHead.payMonth)," +
                  " monthPayment.noteNum,monthPayment.docNum,to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd'),monthPayment.payStatus, monthPayment.id,monthPayment.payMoney ,monthPayment.minMaxMonth," +
                  " monthPayment.settDate " +
                  " from MonthPayment monthPayment,MonthPaymentBizActivityLog monthPaymentBizActivityLog, MonthPaymentHead monthPaymentHead" +
                  " where monthPaymentHead.paymentHead.id=monthPayment.id and " +
                  " monthPaymentHead.paymentHead.id=monthPaymentBizActivityLog.bizid and monthPaymentBizActivityLog.action = monthPayment.payStatus "+
                  " and monthPayment.org.id "+securityInfo.getGjjSecurityHqlSQL();
              
//              String hql = "select new MonthPaymentTail(monthPaymentTail,monthPaymentBizActivityLog) " +
//                  "from MonthPaymentTail monthPaymentTail,MonthPayment monthPayment," +
//                  "MonthPaymentBizActivityLog monthPaymentBizActivityLog ";
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += " to_char(monthPaymentHead.paymentHead.org.id ) like ? escape '/'  and ";
                parameters.add("%" + id.toString()+ "%");
              }
              
              if (name != null&&!name.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.name  like ? escape '/'  and ";
                parameters.add("%" + name + "%");
              }
              if (status != null&&!status.equals("")) {
                  criterion += " monthPayment.payModel = ? and ";
                  parameters.add(new Integer(status));
              }

              if (inceptMonth != null&&!inceptMonth.equals("")&&payMonth!=null&&!payMonth.equals("")) {
                criterion += " (monthPaymentHead.payMonth between ?  and  ? ) and ";
                parameters.add(inceptMonth);
                parameters.add(payMonth);
              }else if (inceptMonth != null&&!inceptMonth.equals("")) {
                criterion += " (monthPaymentHead.payMonth = ? and ";
                parameters.add(inceptMonth);
              }else if (payMonth!=null&&!payMonth.equals("")) {
                criterion += " (monthPaymentHead.payMonth = ?  and ";
                parameters.add(payMonth);
              }
              if (payType != null&&!payType.equals("")) {
                criterion += " monthPayment.payStatus = ?  and ";
                parameters.add(new Integer(payType));
              }
              if (settDate != null&&!settDate.equals("") && settDate1 != null && !settDate1.equals("")) {
                criterion += " (monthPayment.settDate between ?  and  ? ) and ";
                parameters.add(settDate);
                parameters.add(settDate1);
              }else if (settDate != null&&!settDate.equals("")) {
                criterion += " monthPayment.settDate = ? and ";
                parameters.add(settDate);
              }else if (settDate1 != null && !settDate1.equals("")) {
                criterion += " monthPayment.settDate = ? and ";
                parameters.add(settDate1);
              }
              if (inceptPayMoney != null&&!inceptPayMoney.equals("") && payMoney != null && !payMoney.equals("")) {
                criterion += " (monthPayment.payMoney between ?  and  ? ) and ";
                parameters.add(new BigDecimal(inceptPayMoney));
                parameters.add(new BigDecimal(payMoney));
              }else if (inceptPayMoney != null&&!inceptPayMoney.equals("")) {
                criterion += " monthPayment.payMoney = ? and ";
                parameters.add(new BigDecimal(inceptPayMoney));
              }else if (payMoney != null && !payMoney.equals("")) {
                criterion += " monthPayment.payMoney = ? and ";
                parameters.add(new BigDecimal(payMoney));
              }
              //对缴存金额查询不好用进行了修改，添加了两个else
              if (inceptSettlementDate != null&&!inceptSettlementDate.equals("") && settlementDate != null && !settlementDate.equals("")) {
                criterion += " (to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') between ?  and  ?) and ";
                parameters.add(inceptSettlementDate);
                parameters.add(settlementDate);
              }else if (inceptSettlementDate != null&&!inceptSettlementDate.equals("")) {
                criterion += " to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(inceptSettlementDate);
              }else if (settlementDate != null && !settlementDate.equals("")) {
                criterion += " to_char(monthPaymentBizActivityLog.opTime,'yyyymmdd') = ? and ";
                parameters.add(settlementDate);
              }

              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " monthPaymentHead.paymentHead.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)   
                 criterion=" and "+
                 criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion +" group by monthPayment.org.id,monthPayment.org.orgInfo.name," +
                  "monthPayment.noteNum,monthPayment.docNum,monthPaymentBizActivityLog.opTime," +
                  "monthPayment.payStatus,monthPayment.id,monthPayment.payMoney,monthPayment.payModel,monthPayment.settDate ";

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }     
              Iterator it=query.iterate();
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                MonthpayMaintainDto monthpayMaintainDto=new MonthpayMaintainDto();
                monthpayMaintainDto.setOrgId(new Integer(obj[0].toString()));
                monthpayMaintainDto.setOrgName(obj[1].toString());
                if(obj[6] != null){
                  monthpayMaintainDto.setDocNum(obj[6].toString());
                }
                if(obj[5] != null){
                  monthpayMaintainDto.setNoteNum(obj[5].toString());
                }
                monthpayMaintainDto.setPayMonth(obj[3].toString()+"-"+obj[4].toString());
                monthpayMaintainDto.setOpTime(obj[7].toString());
                if(obj[2].toString().equals("3")){
                  monthpayMaintainDto.setPayMode("只缴职工");
                }else if(obj[2].toString().equals("2")){
                  monthpayMaintainDto.setPayMode("只缴单位");
                }else {
                  monthpayMaintainDto.setPayMode("均缴");
                }
                monthpayMaintainDto.setPay((new BigDecimal(obj[10].toString())).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                monthpayMaintainDto.setPayStatus(obj[8].toString());
                monthpayMaintainDto.setId(obj[9].toString());
                if(obj[12] !=null){
                  monthpayMaintainDto.setSettDate(obj[12].toString());
                }
                temp_list.add(monthpayMaintainDto);
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
  
  public String countOrgAddPayPerson(final String headId){
    String count = "";
    try {
      count= (String) getHibernateTemplate()
    .execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
        String hsqlCount=" select count(distinct aa303.emp_id) from AA303 aa303,AA302 AA302 where AA303.MONTH_PAY_HEAD_ID = AA302.ID and aa302.pay_head_id = ?";
        
        Query queryCount = session.createSQLQuery(hsqlCount);
        queryCount.setString(0,headId);            
        Iterator it1=queryCount.list().iterator();
        int count=0;
        if(it1.hasNext()){
          count=(Integer.parseInt(it1.next().toString()));
        }
        String s = new Integer(count).toString();
        return s;
      }
    });
    }catch(Exception e){
        e.printStackTrace();
      }

    return count;
  }
  public String findPayHeadIdInAA201(final String id){
    String count = "";
    try {
      count= (String) getHibernateTemplate()
      .execute(new HibernateCallback() {
        public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
          String sql="select count(a21.id) from aa201 a21 where a21.pay_head_id = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(id));
          return query.uniqueResult().toString();
        }
      });
    }catch(Exception e){
      e.printStackTrace();
    }
    return count;
  }
  public String findPayHeadIdInAA202(final String id){
    String count = "";
    try {
      count= (String) getHibernateTemplate()
      .execute(new HibernateCallback() {
        public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
          String sql="select count(a22.id) from aa202 a22 where a22.pay_head_id = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(id));
          return query.uniqueResult().toString();
        }
      });
    }catch(Exception e){
      e.printStackTrace();
    }
    return count;
  }
  public String findPayHeadIdInAA204(final String id){
    String count = "";
    try {
      count= (String) getHibernateTemplate()
      .execute(new HibernateCallback() {
        public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
          String sql="select count(a24.id) from aa204 a24 where a24.pay_head_id = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(id));
          return query.uniqueResult().toString();
        }
      });
    }catch(Exception e){
      e.printStackTrace();
    }
    return count;
  }
  
}



