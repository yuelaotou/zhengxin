package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearinterest.dto.ClearaccountDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonHeadFormule;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgpersonOrgHeadDTO;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;


public class ChgPersonHeadDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public ChgPersonHead queryById(Integer id) {
    Validate.notNull(id);
    return (ChgPersonHead) getHibernateTemplate().get(ChgPersonHead.class, id);
  }

  /**
   * 插入记录
   * 
   * @param chgPersonHead
   * @return
   */
  public Serializable insert(ChgPersonHead chgPersonHead) {
    Serializable id = null;
    try {
      Validate.notNull(chgPersonHead);
      id = getHibernateTemplate().save(chgPersonHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 王菱 判断是否存在未启用的人员变更
   * 
   * @param 单位ID
   * @return boolean:false－未启用；true－启用
   */
  public boolean getChgStatus(final Integer orgId) {
    Validate.notNull(orgId);

    boolean chgStatus = false;
    Integer TEMP_status = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select chgPersonHead.chgStatus from ChgPersonHead chgPersonHead where chgPersonHead.chgStatus=1 and chgPersonHead.org.id=? ";
            Vector parameters = new Vector();
            parameters.add(orgId);

            Query query = session.createQuery(hql);
            query.setParameter(0, parameters.get(0));

            return query.uniqueResult();
          }
        });
    if (TEMP_status == null) {
      chgStatus = true;
    } else {
      if (TEMP_status.equals(new Integer(1))) {
        chgStatus = false;
      } else {
        chgStatus = true;
      }
    }

    return chgStatus;
  }

  /**
   * 李娟 根据缴存头表ID及启用状态查询
   * 
   * @param payHeadId
   * @param chgStatus
   * @return
   */
  public List queryChgPersonHeadByPayHeadId(final Serializable orgid,
      final Serializable payHeadId, final Integer chgStatus) {
    List chgPersonHead = null;
    chgPersonHead = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgPersonHead chgPersonHead ";
            Vector parameters = new Vector();
            String criterion = "";
            if (orgid != null && !orgid.equals("")) {
              criterion += "chgPersonHead.org.id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }
            if (payHeadId != null && !payHeadId.equals("")) {
              criterion += "chgPersonHead.paymentHead.id = ?  and ";
              parameters.add(new Integer(payHeadId.toString()));
            } else {
              criterion += "chgPersonHead.paymentHead.id is null and ";
            }

            if (chgStatus != null && !chgStatus.equals("")) {
              criterion += "chgPersonHead.chgStatus = ?  and ";
              parameters.add(chgStatus);
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
    return chgPersonHead;
  }

  /**
   * 王菱 AA204里是否存在该单位未被启用的清册：AA204里是否存在该单位变更状态=1的记录 param orgID return
   * ChgPersonHead
   */
  public ChgPersonHead getChgPersonHead_WL(final String orgID) {
    ChgPersonHead chgPersonHead = null;
    chgPersonHead = (ChgPersonHead) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgPersonHead chgPersonHead ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgID != null && !orgID.equals("")) {
              criterion += "chgPersonHead.org.id = ?  and ";
              parameters.add(new Integer(orgID));
            }

            if (criterion.length() != 0)
              criterion = " where chgPersonHead.chgStatus=1 and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));

            hql = hql + criterion;
            session.clear();
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();
          }
        });
    if (chgPersonHead == null) {
      chgPersonHead = new ChgPersonHead();
    }
    return chgPersonHead;
  }

  /**
   * 王菱 删除单个记录
   * 
   * @param chgPersonHead
   */
  public void delete_WL(ChgPersonHead chgPersonHead) {
    Validate.notNull(chgPersonHead);
    getHibernateTemplate().delete(chgPersonHead);
  }

  /**
   * 根据单位ID 查询人员变更头表信息
   * 
   * @param id
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public ChgPersonHeadFormule findChgpersonDoListByCriterions_WL(final String id,final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    ChgPersonHeadFormule dto = null;
    try {

      dto = (ChgPersonHeadFormule) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

             // String hql = " from ChgPersonHead chgPersonHead where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "+"  chgPersonHead.chgStatus=1  and chgPersonHead.org.id = ? ";

              String hql = "select "+
              " (select count(a205.id) from aa205 a205 ,aa204 a204 where a204.id=a205.chg_head_id and a204.chg_status=1 and a204.id=a.id ) , "+
              " (select count(a205.id) from aa205 a205 ,aa204 a204 where a205.chg_type in (1,3) and a204.id=a205.chg_head_id and a204.chg_status=1 and a204.id=a.id ), "+
              " (select count(a205.id) from aa205 a205 ,aa204 a204 where a205.chg_type =4 and a204.id=a205.chg_head_id and a204.chg_status=1 and a204.id=a.id  ), "+
              " (select nvl(sum(a205.org_pay),0)+nvl(sum(a205.emp_pay),0) from aa205 a205 ,aa204 a204 where a205.chg_type in (1,3) and a204.id=a205.chg_head_id and a204.chg_status=1 and a204.id=a.id  ), "+
              " (select nvl(sum(a205.org_pay),0)+nvl(sum(a205.emp_pay),0) from aa205 a205 ,aa204 a204 where a205.chg_type =4 and a204.id=a205.chg_head_id and a204.chg_status=1 and a204.id=a.id  ), "+
              " (select nvl(sum(a205.org_pay),0) from aa205 a205 ,aa204 a204 where a204.id=a205.chg_head_id and a204.chg_status=1 and a204.id=a.id  ), "+
              " (select nvl(sum(a205.emp_pay),0) from aa205 a205 ,aa204 a204 where a204.id=a205.chg_head_id and a204.chg_status=1 and a204.id=a.id ), "+
              " (select nvl(sum(a205.org_pay),0)+nvl(sum(a205.emp_pay),0) from aa205 a205,aa204 a204 where a204.id=a205.chg_head_id and a204.chg_status=1 and a204.id=a.id ), "+
              " (select nvl(a204.old_payment,0) from aa204 a204  where a204.id=a.id), a.id "+
              " from aa204 a  "+
              " where a.org_id "+securityInfo.getGjjSecuritySQL()+" and "+"  a.chg_status=1  and a.org_id = ? ";

             Query query = session.createSQLQuery(hql);
             query.setInteger(0, Integer.parseInt(id));
             
             ChgPersonHeadFormule returnDto = null;
             Object obj[] = null;
             Iterator iterate = query.list().iterator();
            
             while (iterate.hasNext()) {
               obj = (Object[]) iterate.next();
               returnDto = new ChgPersonHeadFormule();
               returnDto.setSumChgPerson(new Integer(obj[0].toString()));
               returnDto.setInsChgperson(new Integer(obj[1].toString()));
               returnDto.setMulChgperson(new Integer(obj[2].toString()));
               returnDto.setInsPayment(new BigDecimal(obj[3].toString()));
               returnDto.setMulPayment(new BigDecimal(obj[4].toString()));
               returnDto.setSumOrgPay(new BigDecimal(obj[5].toString()));
               returnDto.setSumEmpPay(new BigDecimal(obj[6].toString()));
               returnDto.setSumSumPay(new BigDecimal(obj[7].toString()));
               returnDto.setOldOldPayment(new BigDecimal(obj[8].toString()));
               returnDto.setHeadId(obj[9].toString());
             }
            
             return returnDto;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }

  /**
   * 根据变更头表ID 查询对应原合计信息
   * 
   * @param id
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public ChgPersonHeadFormule findChgpersonHeadFormuleByCriterions_WL(final String id,final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    ChgPersonHeadFormule dto = null;
    try {

      dto = (ChgPersonHeadFormule) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

             // String hql = " from ChgPersonHead chgPersonHead where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "+"  chgPersonHead.chgStatus=1  and chgPersonHead.org.id = ? ";

              String hql = "select "+
              " (select count(a205.id) from aa205 a205  where a205.chg_head_id=a.id ) , "+
              " (select count(a205.id) from aa205 a205  where a205.chg_type in (1,3) and a205.chg_head_id=a.id ), "+
              " (select count(a205.id) from aa205 a205  where a205.chg_type =4 and a205.chg_head_id=a.id  ), "+
              " (select nvl(sum(a205.org_pay),0)+nvl(sum(a205.emp_pay),0) from aa205 a205  where a205.chg_type in (1,3) and a205.chg_head_id=a.id  ), "+
              " (select nvl(sum(a205.org_pay),0)+nvl(sum(a205.emp_pay),0) from aa205 a205  where a205.chg_type =4 and a205.chg_head_id=a.id  ), "+
              " (select nvl(sum(a205.org_pay),0) from aa205 a205  where a205.chg_head_id=a.id  ), "+
              " (select nvl(sum(a205.emp_pay),0) from aa205 a205  where a205.chg_head_id=a.id ), "+
              " (select nvl(sum(a205.org_pay),0)+nvl(sum(a205.emp_pay),0) from aa205 a205 where a205.chg_head_id=a.id ), "+
              "  nvl(a.old_payment,0) , a.id "+
              " from aa204 a  "+
              " where a.org_id "+securityInfo.getGjjSecuritySQL()+"  and a.id = ? ";

             Query query = session.createSQLQuery(hql);
             query.setInteger(0, Integer.parseInt(id));
             
             ChgPersonHeadFormule returnDto = null;
             Object obj[] = null;
             Iterator iterate = query.list().iterator();
            
             while (iterate.hasNext()) {
               obj = (Object[]) iterate.next();
               returnDto = new ChgPersonHeadFormule();
               returnDto.setSumChgPerson(new Integer(obj[0].toString()));
               returnDto.setInsChgperson(new Integer(obj[1].toString()));
               returnDto.setMulChgperson(new Integer(obj[2].toString()));
               returnDto.setInsPayment(new BigDecimal(obj[3].toString()));
               returnDto.setMulPayment(new BigDecimal(obj[4].toString()));
               returnDto.setSumOrgPay(new BigDecimal(obj[5].toString()));
               returnDto.setSumEmpPay(new BigDecimal(obj[6].toString()));
               returnDto.setSumSumPay(new BigDecimal(obj[7].toString()));
               returnDto.setOldOldPayment(new BigDecimal(obj[8].toString()));
               returnDto.setHeadId(obj[9].toString());
             }
            
             return returnDto;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }
  
  
  
  /**
   * 王菱 AA204里是否存在未被启用的清册或或未被缴存应用的：AA204里是否存在该单位变更状态=1的记录 param orgID return
   * ChgPersonHead
   */
  public List getChgPersonHeadNoTJ_WL(final String orderBy, final String order,
      final int start, final int pageSize, final String orgID,
      final String orgName, final String chgDate) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from ChgPersonHead chgPersonHead ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgID != null && !orgID.equals("")) {
          criterion += " To_Char(chgPersonHead.org.id) like ? and ";
          parameters.add("%" + orgID + "%");
        }

        if (orgName != null && !orgName.equals("")) {
          criterion += " chgPersonHead.org.orgInfo.name like ? and ";
          parameters.add("%" + orgName + "%");
        }

        if (chgDate != null && !chgDate.equals("")) {
          criterion += " chgPersonHead.chngMonth= ? and ";
          parameters.add(chgDate);
        }

        if (criterion.length() != 0)
          criterion = " where (chgPersonHead.chgStatus=1 or chgPersonHead.paymentHead.id is null) and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        String ob = orderBy;
        if (ob == null)
          ob = " chgPersonHead.org.id  ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "  order by " + ob + " " + od;

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
    return list;
  }

  /**
   * 王菱 
   * 根据条件查询AA204中的记录信息
   * param orgID 
   * return ChgPersonHead
   */
  public List getChgPersonHeadFirst_WL(final String orderBy, final String order,
      final int start, final int pageSize,final int page,final SecurityInfo securityInfo) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from ChgPersonHead chgPersonHead ";
        
        String ob = orderBy;
        if (ob == null)
          ob = " chgPersonHead.chgStatus ASC,chgPersonHead.id DESC ";

        String od = order;
        if (od == null)
          od = "";

        hql = hql + " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "+" chgPersonHead.paymentHead.id is null " + "  order by " + ob + " " + od;

        Query query = session.createQuery(hql);

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
    return list;
  }
  /**
   * 王菱 根据条件查询AA204中的记录信息条数
   */
  public int queryChgpersonMaintainListFirst_WL(final SecurityInfo securityInfo) {

    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from ChgPersonHead chgPersonHead ";

          hql = hql + " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "+" chgPersonHead.paymentHead.id is  null " ;

          session.clear();
          Query query = session.createQuery(hql);

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
   * 王菱 
   * 根据条件查询AA204中的记录信息
   * param orgID 
   * return ChgPersonHead
   */
  public List getChgPersonHead_WL(final String orderBy, final String order,
      final int start, final int pageSize, final String orgID,
      final String orgName, final String chgDate,final int page,final SecurityInfo securityInfo) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from ChgPersonHead chgPersonHead ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgID != null && !orgID.equals("")) {
          criterion += " To_Char(chgPersonHead.org.id) like ? and ";
          parameters.add("%" + orgID + "%");
        }

        if (orgName != null && !orgName.equals("")) {
          criterion += " chgPersonHead.org.orgInfo.name like ? and ";
          parameters.add("%" + orgName + "%");
        }

        if (chgDate != null && !chgDate.equals("")) {
          criterion += " chgPersonHead.chngMonth= ? and ";
          parameters.add(chgDate);
        }

        if (criterion.length() != 0){
          criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        }else{
          criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
        }

        String ob = orderBy;
        if (ob == null)
          ob = " chgPersonHead.org.id  ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "  order by " + ob + " " + od;

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
    return list;
  }
  public List getChgPersonHead_wsh(final String orderBy, final String order,
      final int start, final int pageSize, final String orgID,
      final String orgName, final String chgDate,final String endchgDate,final int page,final SecurityInfo securityInfo) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from ChgPersonHead chgPersonHead ";
        Vector parameters = new Vector();
        String criterion = "";

        if (orgID != null && !orgID.equals("")) {
          criterion += " To_Char(chgPersonHead.org.id) like ? and ";
          parameters.add("%" + orgID + "%");
        }

        if (orgName != null && !orgName.equals("")) {
          criterion += " chgPersonHead.org.orgInfo.name like ? and ";
          parameters.add("%" + orgName + "%");
        }

        if (chgDate != null && !chgDate.equals("")) {
          criterion += " chgPersonHead.chngMonth>= ? and ";
          parameters.add(chgDate);
        }
        if (endchgDate != null && !endchgDate.equals("")) {
          criterion += " chgPersonHead.chngMonth<= ? and ";
          parameters.add(endchgDate);
        }

        if (criterion.length() != 0){
          criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        }else{
          criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
        }

        String ob = orderBy;
        if (ob == null)
          ob = " chgPersonHead.chgStatus ASC,chgPersonHead.id DESC ";

        String od = order;
        if (od == null)
          od = "";

        hql = hql + criterion + "  order by " + ob + " " + od;

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
    return list;
  }
  /**
   * 王菱 根据条件查询AA204中的记录信息条数
   */
  public int queryChgpersonMaintainListByCriterions_WL(final String orgID,
      final String orgName, final String chgDate,final SecurityInfo securityInfo) {

    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from ChgPersonHead chgPersonHead ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgID != null && !orgID.equals("")) {
            criterion += "  To_Char(chgPersonHead.org.id) like ? and ";
            parameters.add("%" + orgID + "%");
          }

          if (orgName != null && !orgName.equals("")) {
            criterion += " chgPersonHead.org.orgInfo.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgDate != null && !chgDate.equals("")) {
            criterion += " chgPersonHead.chngMonth= ? and ";
            parameters.add(chgDate);
          }

          if (criterion.length() != 0){
            criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }else{
            criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
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
  public int queryChgpersonMaintainListByCriterions_wsh(final String orgID,
      final String orgName, final String chgDate,final String endchgDate,final SecurityInfo securityInfo) {

    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from ChgPersonHead chgPersonHead ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgID != null && !orgID.equals("")) {
            criterion += "  To_Char(chgPersonHead.org.id) like ? and ";
            parameters.add("%" + orgID + "%");
          }

          if (orgName != null && !orgName.equals("")) {
            criterion += " chgPersonHead.org.orgInfo.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgDate != null && !chgDate.equals("")) {
            criterion += " chgPersonHead.chngMonth>= ? and ";
            parameters.add(chgDate);
          }
          if (endchgDate != null && !endchgDate.equals("")) {
            criterion += " chgPersonHead.chngMonth<= ? and ";
            parameters.add(endchgDate);
          }

          if (criterion.length() != 0){
            criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }else{
            criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
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
   * 王菱 根据AA204头表ID 获得 单位ID
   * 
   * @param id
   * @throws NumberFormatException
   * @throws Exception
   */
  public String getOrgID_WL(final String chgPersonHeadID)
      throws NumberFormatException, Exception {
    String orgID = null;
    try {

      orgID = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select chgPersonHead.org.id from ChgPersonHead chgPersonHead where chgPersonHead.id = ? ";

          Query query = session.createQuery(hql);
          query.setInteger(0, Integer.parseInt(chgPersonHeadID));

          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orgID;
  }

  /**
   * 返回AA201、AA202、AA204中的最大ID
   * 
   * @throws NumberFormatException
   * @throws Exception
   */
  public Integer getMaxHeadID_WL(final String orgID)
      throws NumberFormatException, Exception {
    Integer maxHeadID = null;
    try {
      maxHeadID = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql0 = "select nvl(max(chgOrgRate.id),0) from ChgOrgRate chgOrgRate where  chgOrgRate.org.id=? ";
              Query query0 = session.createQuery(hql0);
              query0.setInteger(0, Integer.parseInt(orgID));
              String id0="";
              if(query0.uniqueResult()!=null){
                id0=query0.uniqueResult().toString();
              }

              String hql1 = "select nvl(max(chgPaymentHead.id),0) from ChgPaymentHead chgPaymentHead where chgPaymentHead.org.id=? ";
              Query query1 = session.createQuery(hql1);
              query1.setInteger(0, Integer.parseInt(orgID));
              String id1="";
              if(query1.uniqueResult()!=null){
                id1=query1.uniqueResult().toString();
              }

              String hql2 = "select nvl(max(chgPersonHead.id),0) from  ChgPersonHead chgPersonHead where  chgPersonHead.org.id=? ";
              Query query2 = session.createQuery(hql2);
              query2.setInteger(0, Integer.parseInt(orgID));
              String id2="";
              if(query2.uniqueResult()!=null){
                id2=query2.uniqueResult().toString();
              }
              String result = "0";
              if(id0.compareTo(id1)>0){
                if(id0.compareTo(id2)>0){
                  result=id0;
                }else{
                  result=id2;
                }
              }else{
                if(id1.compareTo(id2)>0){
                  result=id1;
                }else{
                  result=id2;
                }
              }
              
              return new Integer(result.toString());
          }});
    } catch (Exception e) {
      e.printStackTrace();
    }
    return maxHeadID;
  }

  /**
   * 王菱 根据AA204头表ID 获得 头表信息
   * 
   * @param id
   * @throws NumberFormatException
   * @throws Exception
   */
  public ChgPersonHead getHeadMessage_WL(final String chgPersonHeadID)
      throws NumberFormatException, Exception {
    ChgPersonHead chgPersonHead = null;
    try {

      chgPersonHead = (ChgPersonHead) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "from ChgPersonHead chgPersonHead where chgPersonHead.id = ? ";

          Query query = session.createQuery(hql);
          query.setInteger(0, Integer.parseInt(chgPersonHeadID));

          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return chgPersonHead;
  }
  
  /**
   * 根据尾表ID查询对应头表ID
   */
  public String queryChgpersonHeadID_WL(final String tailID)
      throws NumberFormatException, Exception {
    String headID = "";
    try {

      headID = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
        SQLException {

          String hql = "select chgPersonTail.chgPersonHead.id from ChgPersonTail chgPersonTail where chgPersonTail.id = ? ";

          Query query = session.createQuery(hql);
          query.setInteger(0, Integer.parseInt(tailID));

          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return headID;
  }
  
  
  

  /**
   * 王菱 AA204里是否存在未被启用的清册：AA204里是否存在该单位变更状态=1的记录 单位ID=传入的单位ID
   * List
   */
  public List getChgPersonHeadNoQY_WL(final String orgID) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from ChgPersonHead chgPersonHead where chgPersonHead.chgStatus=1 and chgPersonHead.org.id=? ";
        Query query = session.createQuery(hql);
        query.setInteger(0, Integer.parseInt(orgID));

        return query.list();
      }
    });
    return list;
  }
  /**
   * 王菱
   * 年终结息
   */
  
  public boolean doClearAccount(String orgStr,String busiDate,String busiDateTime,String docNum,String ip,String op_time,String oper) throws BusinessException, HibernateException, SQLException{

    try{
    boolean voild=false;
    Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
    CallableStatement cs=conn.prepareCall("{call YEARENDINTEREST(?,?,?,?,?,?,?)}");
    cs.setString(1, orgStr);
    cs.setString(2, busiDate);
    cs.setString(3, busiDateTime);
    cs.setString(4, docNum);
    cs.setString(5, ip);
    cs.setString(6, op_time);
    cs.setString(7, oper);
    voild=cs.execute();
    return voild;
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("年终结息失败!!!");
    }
  }

  /**
   * 王菱
   * 全部年终结息
   */
  
  public boolean doClearAllAccount(List list,String busiDate,String busiDateTime,String docNum,String ip,String op_time,String oper) throws BusinessException, HibernateException, SQLException{

    try{
    boolean voild=false;
    Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
    //循环插入临时表PreparedStatement不能使用，因为其相当开启隐形游标当时164抛出异常
    Statement statement = conn.createStatement();
     for (int i = 0; i < list.size(); i++) {
       String temp_mes="";
       temp_mes=(String)list.get(i);
       String sql= "insert into AAYEARCLEARTEMP(id)" +
       "values ('"+new Integer(temp_mes)+"')";
       statement.executeUpdate(sql);
      }
    CallableStatement cs=conn.prepareCall("{call YEARENDALLINTEREST(?,?,?,?,?,?)}");
    cs.setString(1, busiDate);
    cs.setString(2, busiDateTime);
    cs.setString(3, docNum);
    cs.setString(4, ip);
    cs.setString(5, op_time);
    cs.setString(6, oper);
    voild=cs.execute();
    return voild;
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("年终结息失败!!!");
    }
  }

  /**
   * 王菱 
   * 统计查询-人员变更:根据各种条件查询
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public List queryChgpersonOrgListByCriterions_WL(final String officeCode,final String collectionBank,final String orgId,final String orgName,
      final String chgMonthStart,final String chgMonthEnd,final String chgDateStart,final String chgDateEnd,final Integer chgStatus,final int start,
      final int pageSize, final String orderBy, final String order,final int page,final SecurityInfo securityInfo){
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgPersonHead chgPersonHead";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null&&!officeCode.equals("")) {//办事处
            criterion += "chgPersonHead.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null&&!collectionBank.equals("")) {//归集银行
            criterion += "chgPersonHead.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null&&!orgId.equals("")) {//单位编号
            criterion += "chgPersonHead.org.id = ?  and ";
            parameters.add(new Integer(orgId));
          }
          
          if (orgName != null&&!orgName.equals("")) {//单位名称
            criterion += "chgPersonHead.org.orgInfo.name like ?  and ";
            parameters.add("%"+orgName+"%");
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && chgMonthEnd != null&&!chgMonthEnd.equals("")) {//有开始年月结束年月
            criterion += "(chgPersonHead.chngMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && (chgMonthEnd == null || chgMonthEnd.equals(""))) {//有开始年月无结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthStart);
          }
          
          if (chgMonthEnd != null&&!chgMonthEnd.equals("") && (chgMonthStart == null || chgMonthStart.equals(""))) {//无开始年月有结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthEnd);
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && chgDateEnd != null&&!chgDateEnd.equals("")) {//有开始日期结束日期
            criterion += " (chgPersonHead.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd );
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && (chgDateEnd == null || chgDateEnd.equals(""))) {//有开始日期无结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }
          
          if (chgDateEnd != null&&!chgDateEnd.equals("") && (chgDateStart == null || chgDateStart.equals(""))) {//无开始日期有结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart );
          }
          
          if (chgStatus != null&&!chgStatus.equals("")) {//业务状态
            criterion += " chgPersonHead.chgStatus = ? and ";
            parameters.add(chgStatus);
          }
          
          if (criterion.length() != 0){
            criterion =  " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }else{
            criterion =  " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
          }

          String ob = orderBy;
          if (ob == null)
            ob = " chgPersonHead.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "  order by " + ob + " " + od;
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
  
  
  public List queryChgpersonOrgListByCriterions_LY(final String officeCode,final String collectionBank,final String orgId,final String orgName,
      final String chgMonthStart,final String chgMonthEnd,final String chgDateStart,final String chgDateEnd,final Integer chgStatus,final int start,
      final int pageSize, final String orderBy, final String order,final int page,final SecurityInfo securityInfo){
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select chgPersonHead.id,chgPersonHead.chgStatus,chgPersonHead.org.id,organizationUnit.name,collBank.collBankName,chgPersonHead.org.orgInfo.name,chgPersonHead.chngMonth,chgPersonHead.bizDate from ChgPersonHead chgPersonHead,OrganizationUnit organizationUnit,CollBank collBank  ";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null&&!officeCode.equals("")) {//办事处
            criterion += "chgPersonHead.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null&&!collectionBank.equals("")) {//归集银行
            criterion += "chgPersonHead.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null&&!orgId.equals("")) {//单位编号
            criterion += "chgPersonHead.org.id = ?  and ";
            parameters.add(new Integer(orgId));
          }
          
          if (orgName != null&&!orgName.equals("")) {//单位名称
            criterion += "chgPersonHead.org.orgInfo.name like ?  and ";
            parameters.add("%"+orgName+"%");
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && chgMonthEnd != null&&!chgMonthEnd.equals("")) {//有开始年月结束年月
            criterion += "(chgPersonHead.chngMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && (chgMonthEnd == null || chgMonthEnd.equals(""))) {//有开始年月无结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthStart);
          }
          
          if (chgMonthEnd != null&&!chgMonthEnd.equals("") && (chgMonthStart == null || chgMonthStart.equals(""))) {//无开始年月有结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthEnd);
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && chgDateEnd != null&&!chgDateEnd.equals("")) {//有开始日期结束日期
            criterion += " (chgPersonHead.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd );
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && (chgDateEnd == null || chgDateEnd.equals(""))) {//有开始日期无结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }
          
          if (chgDateEnd != null&&!chgDateEnd.equals("") && (chgDateStart == null || chgDateStart.equals(""))) {//无开始日期有结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart );
          }
          
          if (chgStatus != null&&!chgStatus.equals("")) {//业务状态
            criterion += " chgPersonHead.chgStatus = ? and ";
            parameters.add(chgStatus);
          }
          
          if (criterion.length() != 0){
            criterion =  " where chgPersonHead.org.orgInfo.officecode=organizationUnit.id and collBank.collBankId=chgPersonHead.org.orgInfo.collectionBankId and chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }else{
            criterion =  " where chgPersonHead.org.orgInfo.officecode=organizationUnit.id and collBank.collBankId=chgPersonHead.org.orgInfo.collectionBankId and chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
          }

          String ob = orderBy;
          if (ob == null)
            ob = " chgPersonHead.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "  order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize); 
        
        List returnList=new ArrayList();
        Iterator it=query.iterate();
        Object obj[]=null;
        while(it.hasNext()){
          obj=(Object[])it.next();
          ChgPersonHead chgPersonHead=new ChgPersonHead();
          chgPersonHead.setId(obj[0].toString());
          chgPersonHead.setChgStatus(new Integer(obj[1].toString()));
          try {
            chgPersonHead.setTemp_chgStatus(BusiTools.getBusiValue(chgPersonHead.getChgStatus().intValue(),BusiConst.CHGTYPESTATUS).toString());
          } catch (Exception e) {
            e.printStackTrace();
          }
          Org org=new Org();
          OrgInfo orgInfo=new OrgInfo();
          orgInfo.setTemp_officename(obj[3].toString());
          orgInfo.setTemp_collectionBankname(obj[4].toString());
          orgInfo.setName(obj[5].toString());
          org.setId(obj[2].toString());
          org.setOrgInfo(orgInfo);
          chgPersonHead.setOrg(org);
          chgPersonHead.setChngMonth(obj[6].toString());
          chgPersonHead.setBizDate(obj[7].toString());
          returnList.add(chgPersonHead);
        }
        
        return returnList;
        }
      });
     
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 王菱 
   * 统计查询-人员变更:根据各种条件查询条数
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public int queryChgpersonOrgCountByCriterions_WL(final String officeCode,final String collectionBank,final String orgId,final String orgName,
      final String chgMonthStart,final String chgMonthEnd,final String chgDateStart,final String chgDateEnd,final Integer chgStatus,final SecurityInfo securityInfo){
    int count = 0 ;
    Integer countInteger=new Integer(0);
    try {
       countInteger = (Integer)getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(chgPersonHead.id) from ChgPersonHead chgPersonHead";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null&&!officeCode.equals("")) {//办事处
            criterion += "chgPersonHead.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null&&!collectionBank.equals("")) {//归集银行
            criterion += "chgPersonHead.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null&&!orgId.equals("")) {//单位编号
            criterion += "chgPersonHead.org.id = ?  and ";
            parameters.add(new Integer(orgId));
          }
          
          if (orgName != null&&!orgName.equals("")) {//单位名称
            criterion += "chgPersonHead.org.orgInfo.name like ?  and ";
            parameters.add("%"+orgName+"%");
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && chgMonthEnd != null&&!chgMonthEnd.equals("")) {//有开始年月结束年月
            criterion += "(chgPersonHead.chngMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && (chgMonthEnd == null || chgMonthEnd.equals(""))) {//有开始年月无结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthStart);
          }
          
          if (chgMonthEnd != null&&!chgMonthEnd.equals("") && (chgMonthStart == null || chgMonthStart.equals(""))) {//无开始年月有结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthEnd);
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && chgDateEnd != null&&!chgDateEnd.equals("")) {//有开始日期结束日期
            criterion += " (chgPersonHead.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd );
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && (chgDateEnd == null || chgDateEnd.equals(""))) {//有开始日期无结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }
          
          if (chgDateEnd != null&&!chgDateEnd.equals("") && (chgDateStart == null || chgDateStart.equals(""))) {//无开始日期有结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart );
          }
          
          if (chgStatus != null&&!chgStatus.equals("")) {//业务状态
            criterion += " chgPersonHead.chgStatus = ? and ";
            parameters.add(chgStatus);
          }
          
          if (criterion.length() != 0){
            criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }else{
            criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
          }


          hql = hql + criterion ;
          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Object obj=query.uniqueResult();
          if(obj==null){
            return new Integer(0);
          }else{
            return obj;
          }
          
        }
      });
     
    } catch (Exception e) {
      e.printStackTrace();
    }
    count=countInteger.intValue();
    return count;
  }
  


  /**
   * 王菱 
   * 统计查询-人员变更:根据各种条件查询头信息
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @return
   */
  public List queryChgpersonOrgHeadByCriterions_WL(final String officeCode,final String collectionBank,final String orgId,final String orgName,
      final String chgMonthStart,final String chgMonthEnd,final String chgDateStart,final String chgDateEnd,final Integer chgStatus,final SecurityInfo securityInfo){
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgPersonHead chgPersonHead";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null&&!officeCode.equals("")) {//办事处
            criterion += "chgPersonHead.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null&&!collectionBank.equals("")) {//归集银行
            criterion += "chgPersonHead.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null&&!orgId.equals("")) {//单位编号
            criterion += "to_char(chgPersonHead.org.id) like ?  and ";
            parameters.add("%"+orgId+"%");
          }
          
          if (orgName != null&&!orgName.equals("")) {//单位名称
            criterion += "chgPersonHead.org.orgInfo.name like ?  and ";
            parameters.add("%"+orgName+"%");
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && chgMonthEnd != null&&!chgMonthEnd.equals("")) {//有开始年月结束年月
            criterion += "(chgPersonHead.chngMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && (chgMonthEnd == null || chgMonthEnd.equals(""))) {//有开始年月无结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthStart);
          }
          
          if (chgMonthEnd != null&&!chgMonthEnd.equals("") && (chgMonthStart == null || chgMonthStart.equals(""))) {//无开始年月有结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthEnd);
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && chgDateEnd != null&&!chgDateEnd.equals("")) {//有开始日期结束日期
            criterion += " (chgPersonHead.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd);
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && (chgDateEnd == null || chgDateEnd.equals(""))) {//有开始日期无结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }
          
          if (chgDateEnd != null&&!chgDateEnd.equals("") && (chgDateStart == null || chgDateStart.equals(""))) {//无开始日期有结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }
          
          if (chgStatus != null&&!chgStatus.equals("")) {//业务状态
            criterion += " chgPersonHead.chgStatus = ? and ";
            parameters.add(chgStatus);
          }
          
          if (criterion.length() != 0){
            criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }else{
            criterion = " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
          }

          hql = hql + criterion ;
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
   * 王菱 
   * 统计查询-人员变更:根据各种条件查询 打印中用到
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @return
   */
  public List queryChgpersonOrgAllListByCriterions_WL(final String officeCode,final String collectionBank,final String orgId,final String orgName,
      final String chgMonthStart,final String chgMonthEnd,final String chgDateStart,final String chgDateEnd,final Integer chgStatus,
      final SecurityInfo securityInfo){
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgPersonHead chgPersonHead";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null&&!officeCode.equals("")) {//办事处
            criterion += "chgPersonHead.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null&&!collectionBank.equals("")) {//归集银行
            criterion += "chgPersonHead.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null&&!orgId.equals("")) {//单位编号
            criterion += " chgPersonHead.org.id = ?  and ";
            parameters.add(new Integer(orgId));
          }
          
          if (orgName != null&&!orgName.equals("")) {//单位名称
            criterion += "chgPersonHead.org.orgInfo.name like ?  and ";
            parameters.add("%"+orgName+"%");
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && chgMonthEnd != null&&!chgMonthEnd.equals("")) {//有开始年月结束年月
            criterion += "(chgPersonHead.chngMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && (chgMonthEnd == null || chgMonthEnd.equals(""))) {//有开始年月无结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthStart);
          }
          
          if (chgMonthEnd != null&&!chgMonthEnd.equals("") && (chgMonthStart == null || chgMonthStart.equals(""))) {//无开始年月有结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthEnd );
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && chgDateEnd != null&&!chgDateEnd.equals("")) {//有开始日期结束日期
            criterion += " (chgPersonHead.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart );
            parameters.add(chgDateEnd);
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && (chgDateEnd == null || chgDateEnd.equals(""))) {//有开始日期无结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }
          
          if (chgDateEnd != null&&!chgDateEnd.equals("") && (chgDateStart == null || chgDateStart.equals(""))) {//无开始日期有结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }
          
          if (chgStatus != null&&!chgStatus.equals("")) {//业务状态
            criterion += " chgPersonHead.chgStatus = ? and ";
            parameters.add(chgStatus);
          }
          
          if (criterion.length() != 0){
            criterion =  " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }else{
            criterion =  " where chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
          }

          hql = hql + criterion +"order by chgPersonHead.id desc";
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
  

  public List queryChgpersonOrgAllListByCriterions_LY(final String officeCode,final String collectionBank,final String orgId,final String orgName,
      final String chgMonthStart,final String chgMonthEnd,final String chgDateStart,final String chgDateEnd,final Integer chgStatus,
      final SecurityInfo securityInfo){
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select chgPersonHead.id,chgPersonHead.chgStatus,chgPersonHead.org.id,organizationUnit.name,collBank.collBankName,chgPersonHead.org.orgInfo.name,chgPersonHead.chngMonth,chgPersonHead.bizDate from ChgPersonHead chgPersonHead,OrganizationUnit organizationUnit,CollBank collBank  ";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null&&!officeCode.equals("")) {//办事处
            criterion += "chgPersonHead.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null&&!collectionBank.equals("")) {//归集银行
            criterion += "chgPersonHead.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null&&!orgId.equals("")) {//单位编号
            criterion += "chgPersonHead.org.id = ?  and ";
            parameters.add(new Integer(orgId));
          }
          
          if (orgName != null&&!orgName.equals("")) {//单位名称
            criterion += "chgPersonHead.org.orgInfo.name like ?  and ";
            parameters.add("%"+orgName+"%");
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && chgMonthEnd != null&&!chgMonthEnd.equals("")) {//有开始年月结束年月
            criterion += "(chgPersonHead.chngMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }
          
          if (chgMonthStart != null&&!chgMonthStart.equals("") && (chgMonthEnd == null || chgMonthEnd.equals(""))) {//有开始年月无结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthStart);
          }
          
          if (chgMonthEnd != null&&!chgMonthEnd.equals("") && (chgMonthStart == null || chgMonthStart.equals(""))) {//无开始年月有结束年月
            criterion += "chgPersonHead.chngMonth = ? and ";
            parameters.add(chgMonthEnd);
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && chgDateEnd != null&&!chgDateEnd.equals("")) {//有开始日期结束日期
            criterion += " (chgPersonHead.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd );
          }
          
          if (chgDateStart != null&&!chgDateStart.equals("") && (chgDateEnd == null || chgDateEnd.equals(""))) {//有开始日期无结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart);
          }
          
          if (chgDateEnd != null&&!chgDateEnd.equals("") && (chgDateStart == null || chgDateStart.equals(""))) {//无开始日期有结束日期
            criterion += " chgPersonHead.bizDate = ? and ";
            parameters.add(chgDateStart );
          }
          
          if (chgStatus != null&&!chgStatus.equals("")) {//业务状态
            criterion += " chgPersonHead.chgStatus = ? and ";
            parameters.add(chgStatus);
          }
          
          if (criterion.length() != 0){
            criterion =  " where chgPersonHead.org.orgInfo.officecode=organizationUnit.id and collBank.collBankId=chgPersonHead.org.orgInfo.collectionBankId and chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }else{
            criterion =  " where chgPersonHead.org.orgInfo.officecode=organizationUnit.id and collBank.collBankId=chgPersonHead.org.orgInfo.collectionBankId and chgPersonHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
          }

          hql = hql + criterion +"order by chgPersonHead.id desc";
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

        
        List returnList=new ArrayList();
        Iterator it=query.iterate();
        Object obj[]=null;
        while(it.hasNext()){
          obj=(Object[])it.next();
          ChgPersonHead chgPersonHead=new ChgPersonHead();
          chgPersonHead.setId(obj[0].toString());
          chgPersonHead.setChgStatus(new Integer(obj[1].toString()));
          try {
            chgPersonHead.setTemp_chgStatus(BusiTools.getBusiValue(chgPersonHead.getChgStatus().intValue(),BusiConst.CHGTYPESTATUS).toString());
          } catch (Exception e) {
            e.printStackTrace();
          }
          Org org=new Org();
          OrgInfo orgInfo=new OrgInfo();
          orgInfo.setTemp_officename(obj[3].toString());
          orgInfo.setTemp_collectionBankname(obj[4].toString());
          orgInfo.setName(obj[5].toString());
          org.setId(obj[2].toString());
          org.setOrgInfo(orgInfo);
          chgPersonHead.setOrg(org);
          chgPersonHead.setChngMonth(obj[6].toString());
          chgPersonHead.setBizDate(obj[7].toString());
          returnList.add(chgPersonHead);
        }
        
        return returnList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 根据变更头表ID 查询 合计信息
   * 
   */
  public ChgPersonHeadFormule queryFormule3_WL(final String chgPersonHeadID) {
    ChgPersonHeadFormule dto =null;
    try {
      dto =(ChgPersonHeadFormule) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select "+
                       " (select count(a205.id) from aa205 a205 ,aa204 a204 where a204.id=a205.chg_head_id and a204.id=a.id ) , "+
                       " (select count(a205.id) from aa205 a205 ,aa204 a204 where a205.chg_type in (1,3) and a204.id=a205.chg_head_id and a204.id=a.id ), "+
                       " (select count(a205.id) from aa205 a205 ,aa204 a204 where a205.chg_type =4 and a204.id=a205.chg_head_id and a204.id=a.id ), "+
                       " (select nvl(sum(a205.org_pay),0)+nvl(sum(a205.emp_pay),0) from aa205 a205 ,aa204 a204 where a205.chg_type in (1,3) and a204.id=a205.chg_head_id and a204.id=a.id ), "+
                       " (select nvl(sum(a205.org_pay),0)+nvl(sum(a205.emp_pay),0) from aa205 a205 ,aa204 a204 where a205.chg_type =4 and a204.id=a205.chg_head_id and a204.id=a.id ), "+
                       " (select nvl(sum(a205.org_pay),0) from aa205 a205 ,aa204 a204 where a204.id=a205.chg_head_id and a204.id=a.id ), "+
                       " (select nvl(sum(a205.emp_pay),0) from aa205 a205 ,aa204 a204 where a204.id=a205.chg_head_id and a204.id=a.id ), "+
//                       " (select nvl(sum(a205.org_pay),0)+nvl(sum(a205.emp_pay),0) from aa205 a205 ) "+
                       " (select nvl(a204.old_payment,0) from aa204 a204  where a204.id=a.id) "+
                       " from aa204 a  "+
                       " where a.id=? ";

          Query query = session.createSQLQuery(hql);
          query.setInteger(0, Integer.parseInt(chgPersonHeadID));
          
          ChgPersonHeadFormule returnDto = null;
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            returnDto = new ChgPersonHeadFormule();
            returnDto.setSumChgPerson(new Integer(obj[0].toString()));
            returnDto.setInsChgperson(new Integer(obj[1].toString()));
            returnDto.setMulChgperson(new Integer(obj[2].toString()));
            returnDto.setInsPayment(new BigDecimal(obj[3].toString()));
            returnDto.setMulPayment(new BigDecimal(obj[4].toString()));
            returnDto.setSumOrgPay(new BigDecimal(obj[5].toString()));
            returnDto.setSumEmpPay(new BigDecimal(obj[6].toString()));
            returnDto.setOldOldPayment(new BigDecimal(obj[7].toString()));
          }

          return returnDto;
        }
      }

      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }
  /**
   * 于庆丰 COUNT 204
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
     
        String hql = "select count(chgPersonHead.id) from ChgPersonHead chgPersonHead ";
        Vector parameters = new Vector();
        String criterion = "";
        if(office != null && !"".equals(office)){
          criterion += " chgPersonHead.org.orgInfo.officecode = ? and ";
          parameters.add(office);
        }
        if(bank != null && !"".equals(bank)){
          criterion += " chgPersonHead.org.orgInfo.collectionBankId = ? and ";
          parameters.add(bank);
        }
        if(orgCharacter != null && !"".equals(orgCharacter)){
          criterion += " chgPersonHead.org.orgInfo.character = ? and ";
          parameters.add(orgCharacter);
        }
        if(dept != null && !"".equals(dept)){
          criterion += " chgPersonHead.org.orgInfo.deptInCharge = ? and ";
          parameters.add(dept);
        }
        if(ragion != null && !"".equals(ragion)){
          criterion += " chgPersonHead.org.orgInfo.region = ? and ";
          parameters.add(ragion);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
          criterion += " (chgPersonHead.bizDate  between ?  and  ?)  and ";
          parameters.add(startDate);
          parameters.add(endDate);
        }
        if (criterion.length() != 0) 
//          criterion = "where (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id  "
//              + securityInfo.getGjjSecurityHqlSQL()
//              + " and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
          criterion = " where chgPersonHead.chgStatus = 2 and "
            + criterion.substring(0, criterion.lastIndexOf("and"));

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
  /**
   * 根据变更状态查询aa204id
   * @return 清册id
   */
  public Object queryIdByChgStatus(final String orgId){
    Object obj = null;
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a204.id from aa204 a204 where a204.chg_status = '1' and a204.org_id = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(orgId));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }
  

}
