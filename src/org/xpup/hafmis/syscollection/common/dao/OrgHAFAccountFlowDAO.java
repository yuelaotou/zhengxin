package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.dto.BizcheckDTO;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto.ClearAccountBalanceDTO;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto.ClearAccountDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.dto.OrgAccountInfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.dto.OrgAccountInfoTotalDTO;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonOrgAccountDTO;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.dto.EmpOperationFlowTotalDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.dto.CollectionstatisticsExportDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.dto.ListDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.dto.SearchDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.dto.PaymentyearBankDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.dto.LoanBackByFundDTO;
import org.xpup.security.common.domain.User;

public class OrgHAFAccountFlowDAO extends HibernateDaoSupport {

  /**
   * 根据凭证号,结算号,查询 return OrgHAFAccountFlow
   */
  public OrgHAFAccountFlow updateByDocNum(final String docNum,
      final String notNum) {
    return (OrgHAFAccountFlow) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from OrgHAFAccountFlow orgHAFAccountFlow  where "
                + " orgHAFAccountFlow.docNum=? and orgHAFAccountFlow.noteNum = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, docNum);
            query.setParameter(1, notNum);
            return query.uniqueResult();
          }
        });
  }

  /**
   * 根据BIZ_ID查询 return OrgHAFAccountFlow
   */
  public OrgHAFAccountFlow queryByBizId(final String bis_id) {
    return (OrgHAFAccountFlow) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from OrgHAFAccountFlow orgHAFAccountFlow  where "
                + " orgHAFAccountFlow.bizId=?";
            Query query = session.createQuery(hql);
            query.setBigDecimal(0, new BigDecimal(bis_id));
            return query.uniqueResult();
          }
        });
  }

  /**
   * 根据BIZ_ID查询 return OrgHAFAccountFlow
   */
  public OrgHAFAccountFlow queryByBizId_WL(final String bis_id) {
    return (OrgHAFAccountFlow) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from OrgHAFAccountFlow orgHAFAccountFlow  where  orgHAFAccountFlow.biz_Type='D' and "
                + " orgHAFAccountFlow.bizId=?";
            Query query = session.createQuery(hql);
            query.setBigDecimal(0, new BigDecimal(bis_id));
            return query.uniqueResult();
          }
        });
  }

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public OrgHAFAccountFlow queryById(Integer id) {
    Validate.notNull(id);
    return (OrgHAFAccountFlow) getHibernateTemplate().get(
        OrgHAFAccountFlow.class, id);
  }

  /**
   * 根据主键删除
   * 
   * @param id
   * @return
   */
  public void deleteById(String id) {
    try {
      Validate.notNull(id);
      // getHibernateTemplate().clear();
      OrgHAFAccountFlow orgHAFAccountFlow = queryById(new Integer(id));

      getHibernateTemplate().delete(orgHAFAccountFlow);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 李鹏 根据错帐凭证号ID
   * 
   * @param docNum 错帐凭证号
   * @return OrgHAFAccountFlow
   */
  public OrgHAFAccountFlow queryByDocNote(final String docNum) {
    OrgHAFAccountFlow orgHAFAccountFlow = null;
    try {
      orgHAFAccountFlow = (OrgHAFAccountFlow) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "from OrgHAFAccountFlow orgHAFAccountFlow  where "
                  + " orgHAFAccountFlow.docNum=?";
              Query query = session.createQuery(hql);
              query.setString(0, docNum);
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orgHAFAccountFlow;
  }

  /**
   * 李鹏 查询流水表
   * 
   * @param docNum 错帐凭证号
   * @param settDate 日期
   * @param orgid 单位id
   * @return OrgHAFAccountFlow
   */
  public OrgHAFAccountFlow queryByCriterion(final String docNum,
      final String settDate, final String orgid) {
    // System.out.println("-----::::"+docNum+" -s- "+settDate+" o-- "+orgid);
    OrgHAFAccountFlow orgHAFAccountFlow = null;
    try {
      orgHAFAccountFlow = (OrgHAFAccountFlow) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "from OrgHAFAccountFlow orgHAFAccountFlow  where "
                  + " orgHAFAccountFlow.docNum=? and orgHAFAccountFlow.settDate=? and orgHAFAccountFlow.org.id=? "
                  + " and (orgHAFAccountFlow.biz_Type='A' or orgHAFAccountFlow.biz_Type='B' or orgHAFAccountFlow.biz_Type='M') "
                  + " and orgHAFAccountFlow.bizStatus = 5 ";
              Query query = session.createQuery(hql);
              query.setString(0, docNum);
              query.setString(1, settDate);
              query.setParameter(2, new Integer(orgid));
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orgHAFAccountFlow;
  }

  /**
   * 李鹏 根据业务id查询操作员
   * 
   * @param id 业务id
   * @return BizActivityLog
   */
  public BizActivityLog queryOperatorByBizId(final String id) {
    BizActivityLog bizActivityLog = null;
    try {
      bizActivityLog = (BizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select bizActivityLog from OrgHAFAccountFlow orgHAFAccountFlow ,BizActivityLog bizActivityLog where bizActivityLog.bizid=orgHAFAccountFlow.bizId and "
                  + "orgHAFAccountFlow.bizId=? and bizActivityLog.action=orgHAFAccountFlow.bizStatus and bizActivityLog.types=orgHAFAccountFlow.biz_Type ";
              Query query = session.createQuery(hql);
              query.setParameter(0, new BigDecimal(id));
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bizActivityLog;
  }

  /**
   * 于庆丰 根据业务id,个人补缴查询操作员
   * 
   * @param id 业务id
   * @return BizActivityLog
   */
  public BizActivityLog queryOperatorByBizId_(final String id) {
    BizActivityLog bizActivityLog = null;
    try {
      bizActivityLog = (BizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select bizActivityLog from OrgHAFAccountFlow orgHAFAccountFlow ,BizActivityLog bizActivityLog where bizActivityLog.bizid=orgHAFAccountFlow.bizId and "
                  + "orgHAFAccountFlow.bizId=? and bizActivityLog.action=orgHAFAccountFlow.bizStatus and bizActivityLog.types = 'K' ";
              Query query = session.createQuery(hql);
              query.setParameter(0, new BigDecimal(id));
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bizActivityLog;
  }

  /**
   * 于庆丰 根据业务id,单位补缴查询操作员
   * 
   * @param id 业务id
   * @return BizActivityLog
   */
  public BizActivityLog queryOperatorByBizIdOrgAdd(final String id) {
    BizActivityLog bizActivityLog = null;
    try {
      bizActivityLog = (BizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select bizActivityLog from OrgHAFAccountFlow orgHAFAccountFlow ,BizActivityLog bizActivityLog where bizActivityLog.bizid=orgHAFAccountFlow.bizId and "
                  + "orgHAFAccountFlow.bizId=? and bizActivityLog.action=orgHAFAccountFlow.bizStatus and bizActivityLog.types = 'B' ";
              Query query = session.createQuery(hql);
              query.setParameter(0, new BigDecimal(id));
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bizActivityLog;
  }

  /**
   * 李鹏 根据转出单位查询转入单位
   * 
   * @param id 转出单位id
   * @return BizActivityLog
   */
  public TranOutHead queryTranInOrgIdByBizId(final String id) {
    TranOutHead tranOutHead = null;
    try {
      tranOutHead = (TranOutHead) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select tranOutHead from TranOutHead tranOutHead where  "
                  + "tranOutHead.tranOutOrg.id = ? ";
              Query query = session.createQuery(hql);
              query.setParameter(0, new Integer(id));
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tranOutHead;
  }

  /**
   * 李鹏 根据错帐凭证号和结算日期查询出ID
   * 
   * @param docNum 错帐凭证号
   * @param settDate 结算日期
   * @return OrgHAFAccountFlow
   */
  public OrgHAFAccountFlow queryByDocNote(final String docNum,
      final String settDate) {
    return (OrgHAFAccountFlow) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from OrgHAFAccountFlow orgHAFAccountFlow  where "
                + "orgHAFAccountFlow.docNum=? and orgHAFAccountFlow.settDate=? ";
            Query query = session.createQuery(hql);
            query.setParameter(0, docNum);
            query.setParameter(1, settDate);
            return query.uniqueResult();
          }
        });
  }

  /**
   * 李鹏 根据错单位ID和业务类型以及业务状态询出OrgHAFAccountFlow
   * 
   * @param orgId 单位ID
   * @param bisType 业务类型
   * @param status 业务状态
   * @return OrgHAFAccountFlow
   */
  public OrgHAFAccountFlow queryOrgHAFAccountFlowByCriterions(
      final String bis_id, final BigDecimal status, final String bizType,
      final String bizDate) {
    return (OrgHAFAccountFlow) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from OrgHAFAccountFlow orgHAFAccountFlow  where "
                + " orgHAFAccountFlow.bizId=? and orgHAFAccountFlow.bizStatus=? and orgHAFAccountFlow.biz_Type=? and orgHAFAccountFlow.settDate=? ";
            Query query = session.createQuery(hql);
            query.setBigDecimal(0, new BigDecimal(bis_id));
            query.setBigDecimal(1, new BigDecimal(status.toString()));
            query.setString(2, bizType);
            query.setString(3, bizDate);
            return query.uniqueResult();
          }
        });
  }

  /**
   * 扎账李鹏 根据业务状态查询出流水 OrgHAFAccountFlow(默认情况)
   * 
   * @param date 日期
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return List
   */

  public List queryOrgHAFAccountFlowListByCriterions(final String date,
      final String orderBy, final String order, final int start,
      final SecurityInfo securityInfo, final int pageSize)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from OrgHAFAccountFlow orgHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (criterion.length() != 0) {
            criterion = "where orgHAFAccountFlow.bizStatus=4 and (orgHAFAccountFlow.biz_Type!='H' and orgHAFAccountFlow.biz_Type!='I' and orgHAFAccountFlow.biz_Type!='J') and orgHAFAccountFlow.reserveaA = '"
                + securityInfo.getUserName()
                + "'  and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1) and orgHAFAccountFlow.bizStatus=4 and (orgHAFAccountFlow.biz_Type!='H' and orgHAFAccountFlow.biz_Type!='I' and orgHAFAccountFlow.biz_Type!='J') and orgHAFAccountFlow.reserveaA = '"
                + securityInfo.getUserName()
                + "'  and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }
          String ob = orderBy;
          if (ob == null)
            ob = " orgHAFAccountFlow.bizStatus ";
          String od = order;
          if (od == null)
            od = "ASC";

          hql = hql + criterion + " order by " + ob + " " + order;
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 扎账王菱 查询默认可以全部扎账的列表 (列表中是AA101的流水ID)
   * 
   * @return List
   */

  public List queryOrgHAFAccountFlowListByCriterions_WL(
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.id from OrgHAFAccountFlow orgHAFAccountFlow ";
          String criterion = "";

          if (criterion.length() != 0) {
            criterion = "where orgHAFAccountFlow.bizStatus=4 and (orgHAFAccountFlow.biz_Type!='H' and orgHAFAccountFlow.biz_Type!='I' and orgHAFAccountFlow.biz_Type!='J') and orgHAFAccountFlow.reserveaA = '"
                + securityInfo.getUserName()
                + "'  and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1) and orgHAFAccountFlow.bizStatus=4 and (orgHAFAccountFlow.biz_Type!='H' and orgHAFAccountFlow.biz_Type!='I' and orgHAFAccountFlow.biz_Type!='J') and orgHAFAccountFlow.reserveaA = '"
                + securityInfo.getUserName()
                + "'  and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          hql = hql + criterion;
          Query query = session.createQuery(hql);

          return query.list();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 扎账李鹏 根据业务状态查询出流水 OrgHAFAccountFlow(默认情况 返回所有记录)
   * 
   * @param date 日期
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return List
   */
  public List queryOrgHAFAccountFlowAllListByCriterions(final String date,
      final String orderBy, final String order, final int start,
      final SecurityInfo securityInfo, final int pageSize)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from OrgHAFAccountFlow orgHAFAccountFlow ";

          Vector parameters = new Vector();
          String criterion = "";

          if (criterion.length() != 0) {
            criterion = "where  (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1) and orgHAFAccountFlow.bizStatus=4 and (orgHAFAccountFlow.biz_Type!='H' and orgHAFAccountFlow.biz_Type!='I' and orgHAFAccountFlow.biz_Type!='J') and orgHAFAccountFlow.reserveaA = '"
                + securityInfo.getUserName()
                + "'  and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1) and orgHAFAccountFlow.bizStatus=4 and (orgHAFAccountFlow.biz_Type!='H' and orgHAFAccountFlow.biz_Type!='I' and orgHAFAccountFlow.biz_Type!='J') and orgHAFAccountFlow.reserveaA = '"
                + securityInfo.getUserName()
                + "'  and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }
          String ob = orderBy;
          if (ob == null)
            ob = " orgHAFAccountFlow.bizStatus ";
          String od = order;
          if (od == null)
            od = "ASC";

          hql = hql + criterion + "order by " + ob + " " + order;
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
   * 扎账李鹏 根据综合查询出OrgHAFAccountFlow
   * 
   * @param bank 归集银行id
   * @param orgId 单位编号
   * @param orgName 单位名称
   * @param operator 操作员
   * @param type 业务类型
   * @param status 业务状态
   * @param docNum 凭证号
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return List
   */
  public List queryOrgHAFAccountFlowByCriterionsTotal(final String bank,
      final String orgId, final String orgName, final String operator,
      final String type, final String status, final String docNum,
      final String noteNum, final String orderBy, final String order,
      final int start, final int pageSize, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from OrgHAFAccountFlow orgHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (bank != null && !bank.equals("")) {
            criterion += "orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(bank);
          }
          if (orgId != null && !orgId.equals("")) {
            criterion += " to_char(orgHAFAccountFlow.org.id ) like ? escape '/'  and ";
            parameters.add("%" + orgId + "%");
          }
          if (orgName != null && !orgName.equals("")) {
            criterion += "orgHAFAccountFlow.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }
          if (operator != null && !operator.equals("")) {
            criterion += "orgHAFAccountFlow.reserveaA = ?  and ";
            parameters.add(operator);
          } else {
            criterion += " ( ";

            List operList = securityInfo.getUserList();
            User user = null;
            Iterator itr2 = operList.iterator();
            while (itr2.hasNext()) {
              user = (User) itr2.next();
              criterion += "orgHAFAccountFlow.reserveaA = '"
                  + user.getUsername() + "'  or ";
            }

            criterion = criterion.substring(0, criterion.lastIndexOf("or"))
                + " ) and ";
          }
          if (type != null && !type.equals("")) {
            criterion += "orgHAFAccountFlow.biz_Type= ?  and ";
            parameters.add(type);
          } else {
            criterion += "(orgHAFAccountFlow.biz_Type!='H' and orgHAFAccountFlow.biz_Type!='I' and orgHAFAccountFlow.biz_Type!='J') and ";
          }
          if (status != null && !status.equals("")) {
            criterion += "orgHAFAccountFlow.bizStatus = ?  and ";
            parameters.add(new BigDecimal(status));
          } else {
            criterion += "orgHAFAccountFlow.bizStatus in (3,4,5)  and ";
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += "orgHAFAccountFlow.docNum = ?  and ";
            parameters.add(docNum);
          }
          if (noteNum != null && !noteNum.equals("")) {
            criterion += "orgHAFAccountFlow.noteNum = ?  and ";
            parameters.add(noteNum.trim());
          }
          if (criterion.length() != 0)
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1) and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          else
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1) and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL();

          String ob = orderBy;
          if (ob == null)
            ob = " orgHAFAccountFlow.bizStatus ";
          String od = order;
          if (od == null)
            od = "ASC";

          hql = hql + criterion + "order by " + ob + " " + od;

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
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /**
   * 扎账王菱 根据综合查询出可以全部扎账的列表（列表中是AA101的ID）
   * 
   * @param bank 归集银行id
   * @param orgId 单位编号
   * @param orgName 单位名称
   * @param operator 操作员
   * @param type 业务类型
   * @param status 业务状态
   * @param docNum 凭证号
   * @return List
   */
  public List queryOrgHAFAccountFlowByCriterionsTotal_WL(final String bank,
      final String orgId, final String orgName, final String operator,
      final String type, final String status, final String docNum,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.id from OrgHAFAccountFlow orgHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (bank != null && !bank.equals("")) {
            criterion += "orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(bank);
          }
          if (orgId != null && !orgId.equals("")) {
            criterion += " to_char(orgHAFAccountFlow.org.id ) like ? escape '/'  and ";
            parameters.add("%" + orgId + "%");
          }
          if (orgName != null && !orgName.equals("")) {
            criterion += "orgHAFAccountFlow.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }
          if (operator != null && !operator.equals("")) {
            criterion += "orgHAFAccountFlow.reserveaA = ?  and ";
            parameters.add(operator);
          } else {
            criterion += " ( ";

            List operList = securityInfo.getUserList();
            User user = null;
            Iterator itr2 = operList.iterator();
            while (itr2.hasNext()) {
              user = (User) itr2.next();
              criterion += "orgHAFAccountFlow.reserveaA = '"
                  + user.getUsername() + "'  or ";
            }

            criterion = criterion.substring(0, criterion.lastIndexOf("or"))
                + " ) and ";
          }
          if (type != null && !type.equals("")) {
            criterion += "orgHAFAccountFlow.biz_Type= ?  and ";
            parameters.add(type);
          } else {
            criterion += "(orgHAFAccountFlow.biz_Type!='H' and orgHAFAccountFlow.biz_Type!='I' and orgHAFAccountFlow.biz_Type!='J')  and ";
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += "orgHAFAccountFlow.docNum = ?  and ";
            parameters.add(docNum);
          }

          // if (status != null && !status.equals("")) {
          // criterion += "orgHAFAccountFlow.bizStatus = ? and ";
          // parameters.add(new BigDecimal(status));
          // }else{
          // criterion += "orgHAFAccountFlow.bizStatus in (3,4,5) and ";
          // }
          criterion += "orgHAFAccountFlow.bizStatus=4 and ";

          if (criterion.length() != 0)
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1) and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          else
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1) and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL();

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
   * 扎账王菱 根据综合查询出可以全部扎账的列表的语句（列表中是AA101的ID）
   * 
   * @param bank 归集银行id
   * @param orgId 单位编号
   * @param orgName 单位名称
   * @param operator 操作员
   * @param type 业务类型
   * @param status 业务状态
   * @param docNum 凭证号
   * @return List
   */
  public List queryOrgHAFAccountFlowByCriterionsTotal_WLSQL(final String bank,
      final String orgId, final String orgName, final String operator,
      final String type, final String status, final String docNum,
      final SecurityInfo securityInfo, final String officeid)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.id from aa101 orgHAFAccountFlow,ba001 b001,aa001 a001 ";
          String criterion = "";

          if (officeid != null && !officeid.equals("")) {
            criterion += "orgHAFAccountFlow.Officecode = '" + officeid
                + "'  and ";
          }
          if (bank != null && !bank.equals("")) {
            criterion += "orgHAFAccountFlow.moneybank = '" + bank + "'  and ";
          }
          if (orgId != null && !orgId.equals("")) {
            criterion += " to_char(orgHAFAccountFlow.Org_Id ) like '%" + orgId
                + "%'  and ";
          }
          if (orgName != null && !orgName.equals("")) {
            criterion += "b001.name like '%" + orgName + "%'  and ";
          }
          if (operator != null && !operator.equals("")) {
            criterion += "orgHAFAccountFlow.Reservea_a = '" + operator
                + "'  and ";
          } else {
            criterion += " ( ";

            List operList = securityInfo.getUserList();
            User user = null;
            Iterator itr2 = operList.iterator();
            while (itr2.hasNext()) {
              user = (User) itr2.next();
              criterion += "orgHAFAccountFlow.Reservea_a = '"
                  + user.getUsername() + "'  or ";
            }

            criterion = criterion.substring(0, criterion.lastIndexOf("or"))
                + " ) and ";
          }
          if (type != null && !type.equals("")) {
            criterion += "orgHAFAccountFlow.biz_Type= '" + type + "'  and ";
          } else {
            criterion += "(orgHAFAccountFlow.biz_Type!='H' and orgHAFAccountFlow.biz_Type!='I' and orgHAFAccountFlow.biz_Type!='J')  and ";
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += "orgHAFAccountFlow.doc_num = '" + docNum + "'  and ";
          }

          // if (status != null && !status.equals("")) {
          // criterion += "orgHAFAccountFlow.Biz_Status = '"+new
          // BigDecimal(status)+"' and ";
          // }else{
          // criterion += "orgHAFAccountFlow.Biz_Status in (3,4,5) and ";
          // }
          criterion += "orgHAFAccountFlow.Biz_Status=4 and ";

          if (criterion.length() != 0)
            criterion = "where orgHAFAccountFlow.Org_Id=a001.id and a001.orginfo_id=b001.id and orgHAFAccountFlow.Org_Id "
                + securityInfo.getGjjSecuritySQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          else
            criterion = "where orgHAFAccountFlow.Org_Id=a001.id and a001.orginfo_id=b001.id and orgHAFAccountFlow.Org_Id "
                + securityInfo.getGjjSecuritySQL();

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /**
   * 王菱 根据AA101表的ID 查询所有选择的单位ID
   */
  public List queryOrgIDListByFlowID_WL(final String[] rowArray,
      final String operator) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Connection conn = getHibernateTemplate().getSessionFactory()
              .openSession().connection();
          // 循环插入临时表PreparedStatement不能使用，因为其相当开启隐形游标当时164抛出异常
          Statement statement = conn.createStatement();
          List tableList = new ArrayList();
          try {
            for (int j = 0; j < rowArray.length; j++) {
              String sql = "insert into tmp_id(id, operators) values ('"
                  + rowArray[j] + "', '" + operator + "')";
              statement.executeUpdate(sql);
            }
            String hql = "select distinct t.org_id orgid from aa101 t where t.id in ("
                + "select tmp.id from tmp_id tmp where tmp.operators = '"
                + operator + "')";
            ResultSet rs = statement.executeQuery(hql);

            while (rs.next()) {
              tableList.add(rs.getString("orgid"));
            }

          } catch (Exception e) {
            e.printStackTrace();
            // throw new BusinessException("扎账失败!!!");
          } finally {
            String dsql = "delete from tmp_id t where t.operators = '"
                + operator + "'";
            statement.executeUpdate(dsql);
            statement.close();
            conn.close();
          }
          return tableList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 王菱 根据AA101表的ID 查询所有选择的单位ID
   */
  public List queryNoClearAccountBussiness_WL(final String orgID)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // / select min(a101.id) into v_min_a101_id from aa101 a101 where
          // a101.org_id=v_orgid and a101.biz_status!=5 and a101.biz_type in
          // ('D','E');
          String hql = "select orgHAFAccountFlow.id from OrgHAFAccountFlow orgHAFAccountFlow where orgHAFAccountFlow.biz_Type in ('D','E') and orgHAFAccountFlow.bizStatus !=5 ";

          Vector parameters = new Vector();
          String criterion = "";

          if (orgID != null && !orgID.equals("")) {
            criterion += " and orgHAFAccountFlow.org.id =? ";
            parameters.add(new Integer(orgID));
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

  /**
   * 王菱 判断这个单位是否有A:汇缴B:补缴C: 挂账F:转入 G:调账 K: L：的业务或者查询业务
   */
  public List queryHaveClearAccountBussiness_WL(final String orgID,
      final String[] rowArray) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.id from OrgHAFAccountFlow orgHAFAccountFlow where orgHAFAccountFlow.biz_Type in ('A','B','C','F','G','K','L','M') and ";

          Vector parameters = new Vector();
          String criterion = "";

          if (orgID != null && !orgID.equals("")) {
            criterion += " orgHAFAccountFlow.org.id =? and ";
            parameters.add(new Integer(orgID));
          }

          if (rowArray != null && !rowArray[0].equals("")) {
            criterion += "  orgHAFAccountFlow.id in ( ";

            for (int i = 0; i < rowArray.length; i++) {
              criterion += " ? , ";
              parameters.add(new Integer(rowArray[i]));
            }
            criterion = criterion.substring(0, criterion.lastIndexOf(","))
                + " ) and ";
          }

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
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

  /**
   * 王菱 根据流水表101的ID 查询得到 该业务是否是汇缴
   */
  public String queryflowIDByTypeA_WL(final String flowID, final String orgID) {
    String flowCount = null;
    try {
      flowCount = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select orgHAFAccountFlow.id from OrgHAFAccountFlow orgHAFAccountFlow ";
              Vector parameters = new Vector();
              String criterion = "";

              if (flowID != null && !flowID.equals("")) {
                criterion += "orgHAFAccountFlow.id = ?  and ";
                parameters.add(new Integer(flowID));
              }
              if (orgID != null && !orgID.equals("")) {
                criterion += " orgHAFAccountFlow.org.id =? and ";
                parameters.add(new Integer(orgID));
              }

              if (criterion.length() != 0) {
                criterion = "where orgHAFAccountFlow.biz_Type='A'  and  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              }

              hql = hql + criterion;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                return null;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flowCount;
  }

  /**
   * 王菱 根据流水表101的ID 查询得到 该业务的业务MINID
   */
  public BigDecimal queryflowIDByFlowID_WL(final String[] flowID) {
    BigDecimal flowbizid = null;
    try {
      flowbizid = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select min(orgHAFAccountFlow.bizId) from OrgHAFAccountFlow orgHAFAccountFlow ";
              Vector parameters = new Vector();
              String criterion = "";

              if (flowID.length != 0) {
                criterion = "where ( ";
                for (int i = 0; i < flowID.length; i++) {
                  criterion += " orgHAFAccountFlow.id = ?  or ";
                  parameters.add(new Integer(flowID[i]));
                }
                criterion = criterion.substring(0, criterion.lastIndexOf("or"))
                    + " ) ";
              }

              hql = hql + criterion;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              if (query.uniqueResult() != null) {
                return query.uniqueResult();
              } else {
                return null;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flowbizid;
  }

  /**
   * 王菱 根据流水表101的ID 查询得到 该业务的业务MAXID
   */
  public BigDecimal queryflowIDByFlowIDMAX_WL(final String[] flowID) {
    BigDecimal flowbizid = null;
    try {
      flowbizid = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select max(orgHAFAccountFlow.bizId) from OrgHAFAccountFlow orgHAFAccountFlow ";
              Vector parameters = new Vector();
              String criterion = "";

              if (flowID.length != 0) {
                criterion = "where ( ";
                for (int i = 0; i < flowID.length; i++) {
                  criterion += " orgHAFAccountFlow.id = ?  or ";
                  parameters.add(new Integer(flowID[i]));
                }
                criterion = criterion.substring(0, criterion.lastIndexOf("or"))
                    + " ) ";
              }

              hql = hql + criterion;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              if (query.uniqueResult() != null) {
                return query.uniqueResult();
              } else {
                return null;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flowbizid;
  }

  /**
   * 王菱 根据单位ID 取得AA101的表中有的汇缴业务的条数
   */
  public String queryCountByTypeA_WL(final String flowID,
      final BigDecimal minID101) {
    String flowCount = null;
    try {
      flowCount = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select count(orgHAFAccountFlow.id) from OrgHAFAccountFlow orgHAFAccountFlow ";
              Vector parameters = new Vector();
              String criterion = "";

              if (flowID != null && !flowID.equals("")) {
                criterion += "orgHAFAccountFlow.org.id = ?  and ";
                parameters.add(new Integer(flowID));
              }
              if (minID101 != null && !minID101.equals("")) {
                criterion += "orgHAFAccountFlow.bizId <= ?  and ";
                parameters.add(minID101);
              }

              if (criterion.length() != 0) {
                criterion = " where orgHAFAccountFlow.biz_Type='A'  and  "
                    + " (orgHAFAccountFlow.bizStatus=2 or orgHAFAccountFlow.bizStatus=3 or orgHAFAccountFlow.bizStatus=4 ) and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              }

              hql = hql + criterion;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              if (query.uniqueResult() != null) {
                return query.uniqueResult().toString();
              } else {
                return null;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flowCount;
  }

  /**
   * 王菱 根据所选记录的办事处进行分组---扎账
   */
  public List getrowArrayByOffice_WL(final String[] rowArray,
      final String operator) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Connection conn = getHibernateTemplate().getSessionFactory()
              .openSession().connection();
          // 循环插入临时表PreparedStatement不能使用，因为其相当开启隐形游标当时164抛出异常
          Statement statement = conn.createStatement();
          List tableList = new ArrayList();
          try {
            for (int j = 0; j < rowArray.length; j++) {
              String sql = "insert into tmp_id(id, operators) values ('"
                  + rowArray[j] + "', '" + operator + "')";
              statement.executeUpdate(sql);
            }
            String hql = "select distinct t.OFFICECODE office,t.id id from aa101 t where t.id in ("
                + "select tmp.id from tmp_id tmp where tmp.operators = '"
                + operator + "') order by t.OFFICECODE";
            ResultSet rs = statement.executeQuery(hql);
            ClearAccountDTO clearaccountDTO = null;

            while (rs.next()) {
              clearaccountDTO = new ClearAccountDTO();
              System.out.println(rs.getString("office") + "=>");
              clearaccountDTO.setOffice(rs.getString("office"));
              clearaccountDTO.setFlowid(rs.getString("id"));
              tableList.add(clearaccountDTO);
            }

          } catch (Exception e) {
            e.printStackTrace();
            // throw new BusinessException("扎账失败!!!");
          } finally {
            String dsql = "delete from tmp_id t where t.operators = '"
                + operator + "'";
            statement.executeUpdate(dsql);
            statement.close();
            conn.close();
          }
          return tableList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("扎账失败");
    }
    return list;
  }

  /**
   * 王菱 根据所选记录的办事处进行分组--年终结息
   */
  public List getrowArrayByOffice1_WL(final String[] rowArray)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sun = "";
          for (int i = 0; i < rowArray.length; i++) {
            if (rowArray[i] != null && !rowArray[i].equals("")) {
              if (i != rowArray.length - 1) {
                sun = sun + new Integer(rowArray[i]) + ",";
              } else {
                sun = sun + new Integer(rowArray[i]);
              }
            }
          }

          String hql = "select distinct b.officecode,a.id from aa001 a,ba001 b where a.orginfo_id=b.id and a.id in (select distinct t.org_id from jiexiorg t) order by b.officecode";
          Query query = session.createSQLQuery(hql);

          ClearAccountDTO clearaccountDTO = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearaccountDTO = new ClearAccountDTO();
            clearaccountDTO.setOffice(obj[0].toString());
            clearaccountDTO.setFlowid(obj[1].toString());
            tableList.add(clearaccountDTO);
          }

          return tableList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 扎账李鹏 根据综合查询出OrgHAFAccountFlow
   * 
   * @param bank 归集银行id
   * @param orgId 单位编号
   * @param orgName 单位名称
   * @param operator 操作员
   * @param type 业务类型
   * @param status 业务状态
   * @param docNum 凭证号
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return List
   */
  public List queryOrgHAFAccountFlowAllByCriterionsTotal(final String bank,
      final String orgId, final String orgName, final String operator,
      final String type, final String status, final String docNum,
      final String noteNum, final String orderBy, final String order,
      final int start, final int pageSize, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "from OrgHAFAccountFlow orgHAFAccountFlow  ";

          Vector parameters = new Vector();
          String criterion = "";

          if (bank != null && !bank.equals("")) {
            criterion += "orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(bank);
          }
          if (orgId != null && !orgId.equals("")) {
            criterion += " to_char(orgHAFAccountFlow.org.id ) like ? escape '/'  and ";
            parameters.add("%" + orgId.toString() + "%");
          }
          if (orgName != null && !orgName.equals("")) {
            criterion += "orgHAFAccountFlow.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }
          if (operator != null && !operator.equals("")) {
            criterion += "orgHAFAccountFlow.reserveaA = ?  and ";
            parameters.add(operator);
          } else {
            criterion += " ( ";

            List operList = securityInfo.getUserList();
            User user = null;
            Iterator itr2 = operList.iterator();
            while (itr2.hasNext()) {
              user = (User) itr2.next();
              criterion += "orgHAFAccountFlow.reserveaA = '"
                  + user.getUsername() + "'  or ";
            }

            criterion = criterion.substring(0, criterion.lastIndexOf("or"))
                + " ) and ";
          }
          if (type != null && !type.equals("")) {
            criterion += "orgHAFAccountFlow.biz_Type= ?  and ";
            parameters.add(type);
          } else {
            criterion += "(orgHAFAccountFlow.biz_Type!='H' and orgHAFAccountFlow.biz_Type!='I' and orgHAFAccountFlow.biz_Type!='J')   and ";
          }
          if (status != null && !status.equals("")) {
            criterion += "orgHAFAccountFlow.bizStatus = ?  and ";
            parameters.add(new BigDecimal(status));
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += "orgHAFAccountFlow.docNum = ?  and ";
            parameters.add(docNum);
          }
          if (noteNum != null && !noteNum.equals("")) {
            criterion += "orgHAFAccountFlow.noteNum = ?  and ";
            parameters.add(noteNum.trim());
          }
          if (criterion.length() != 0)
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1) and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          else
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1) and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL();

          String ob = orderBy;

          if (ob == null)
            ob = " orgHAFAccountFlow.bizStatus ";
          String od = order;
          if (od == null)
            od = "ASC";
          hql = hql + criterion + "order by " + ob + " " + order;

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
   * 李鹏 根据流水id返回empHAFAccountFlow集合
   * 
   * @param id 流水id
   * @return List
   */
  public List queryByFlowId(final Serializable id) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select empHAFAccountFlow from OrgHAFAccountFlow orgHAFAccountFlow,EmpHAFAccountFlow empHAFAccountFlow "
              + "where orgHAFAccountFlow.id=? and  orgHAFAccountFlow.id=empHAFAccountFlow.orgHAFAccountFlow";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(id + ""));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 李鹏 插入记录
   * 
   * @param orgHAFAccountFlow
   * @return
   */
  public Serializable insert(OrgHAFAccountFlow orgHAFAccountFlow) {
    Serializable id = null;
    try {
      Validate.notNull(orgHAFAccountFlow);
      id = getHibernateTemplate().save(orgHAFAccountFlow);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 李鹏 明细查询所有记录
   * 
   * @param orgHAFAccountFlow
   * @return
   */
  public List queryOrgHAFAccountFlowAllTailList(final String headid,
      final String orderBy, final String order, final int start,
      final int pageSize) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (headid != null && !headid.equals("")) {
            criterion += " empHAFAccountFlow. orgHAFAccountFlow.id = ?  and ";
            parameters.add(new Integer(headid));
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;

          if (ob == null)
            ob = " empHAFAccountFlow.id ";
          String od = order;
          if (od == null)
            od = "ASC";
          hql = hql + criterion + "order by " + ob + " " + od;
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
   * 王菱 明细查询所有记录 －打印中用到
   * 
   * @param orgHAFAccountFlow
   * @return
   */
  public List queryOrgHAFAccountFlowAllTailList_WL(final String headid)
      throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (headid != null && !headid.equals("")) {
            criterion += " empHAFAccountFlow. orgHAFAccountFlow.id = ?  and ";
            parameters.add(new Integer(headid));
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 李鹏 明细查询
   * 
   * @param orgHAFAccountFlow
   * @return
   */
  public List queryOrgHAFAccountFlowTailList(final String headid,
      final String orderBy, final String order, final int start,
      final int pageSize) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (headid != null && !headid.equals("")) {
            criterion += " empHAFAccountFlow. orgHAFAccountFlow.id = ?  and ";
            parameters.add(new Integer(headid));
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;

          if (ob == null)
            ob = " empHAFAccountFlow.id ";
          String od = order;
          if (od == null)
            od = "ASC";
          hql = hql + criterion + "order by " + ob + " " + od;
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 王菱 明细查询-打印中用到
   * 
   * @param orgHAFAccountFlow
   * @return
   */
  public List queryOrgHAFAccountFlowTailList_WL(final String headid)
      throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (headid != null && !headid.equals("")) {
            criterion += " empHAFAccountFlow. orgHAFAccountFlow.id = ?  and ";
            parameters.add(new Integer(headid));
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 于庆丰 明细查询总数(分页)
   * 
   * @param orgHAFAccountFlow
   * @return
   */
  public List queryOrgHAFAccountFlowTailList_(final String headid)
      throws NumberFormatException, Exception {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (headid != null && !headid.equals("")) {
            criterion += " empHAFAccountFlow. orgHAFAccountFlow.id = ?  and ";
            parameters.add(new Integer(headid));
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 李鹏 结算单查询 结算单查询 (默认情况)
   * 
   * @param date 日期
   * @return object
   */

  public List queryOrgHAFAccountFlowBalanceByCriterions(
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select "
              + " (select count(distinct ai101.id) from aa101 ai101 where ai101.biz_type = 'A') as aa,"
              + "(select nvl(sum(ai101.credit),0) from aa101 ai101 where ai101.biz_type = 'A' ) as ab,"
              + "(select count(distinct ai101.id) from aa101 ai101 where ai101.biz_type = 'M' ) as ma,"
              + "(select nvl(sum(ai101.credit),0) from aa101 ai101 where ai101.biz_type = 'M' ) as mb,"
              + "(select count(distinct ai101.id) from aa101 ai101 where ai101.biz_type = 'B' ) as ba,"
              + "(select nvl(sum(ai101.credit),0) from aa101 ai101 where ai101.biz_type = 'B' ) as bb,"
              + "(select count(distinct ai101.id) from aa101 ai101 where ai101.biz_type = 'C' ) as ca,"
              + "(select nvl(sum(ai101.credit),0) from aa101 ai101 where ai101.biz_type = 'C' ) as cb,"
              + "(select count(distinct ai101.id) from aa101 ai101 , aa102 ai102 where ai101.id=ai102.org_flow_id and ai101.biz_type = 'F' ) as fa,"
              + "(select nvl(sum(ai101.credit),0) from aa101 ai101 , aa102 ai102 where ai101.id=ai102.org_flow_id and ai101.biz_type = 'F') as fb,"
              + "(select count(distinct ai101.id) from aa101 ai101 , aa102 ai102 where ai101.id=ai102.org_flow_id and ai101.biz_type in ('K','L','G') ) as ka,"
              + "(select nvl(sum(ai101.credit),0) from aa101 ai101 , aa102 ai102 where ai101.id=ai102.org_flow_id and ai101.biz_type in ('K','L','G') ) as kb,"
              + "(select count(distinct ai101.id) from aa101 ai101 where ai101.biz_type = 'H') as ha,"
              + "(select nvl(sum(ai101.credit),0) from aa101 ai101 where ai101.biz_type = 'H') as hb,"
              + "(select count(distinct ai101.id) from aa101 ai101 where ai101.biz_type != 'C') as nca,"
              + "(select nvl(sum(ai101.credit-ai101.debit),0) from aa101 ai101 where ai101.biz_type != 'C') as ncb "
              +

              " from aa101 a101 where a101.sett_date = ? group by a101.sett_date ";

          Query query = session.createSQLQuery(hql);
          query.setString(0, bizDate);
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setOrg_payment_count(new Integer(obj[0]
                .toString()));
            clearAccountBalanceDTOs.setOrg_payment_balance(new BigDecimal(
                obj[1].toString()));
            clearAccountBalanceDTOs.setOrg_add_payment_count(new Integer(obj[2]
                .toString()));
            clearAccountBalanceDTOs.setOrg_add_payment_balance(new BigDecimal(
                obj[3].toString()));
            clearAccountBalanceDTOs.setEmp_add_payment_count(new Integer(obj[4]
                .toString()));
            clearAccountBalanceDTOs.setEmp_add_payment_balance(new BigDecimal(
                obj[5].toString()));
            clearAccountBalanceDTOs.setOrg_over_pay_count(new Integer(obj[6]
                .toString()));
            clearAccountBalanceDTOs.setOrg_over_paybalance(new BigDecimal(
                obj[7].toString()));
            clearAccountBalanceDTOs.setOrg_tranin_count(new Integer(obj[8]
                .toString()));
            clearAccountBalanceDTOs.setOrg_tranin_paybalance(new BigDecimal(
                obj[9].toString()));
            clearAccountBalanceDTOs.setAdjustaccout_credit_count(new Integer(
                obj[10].toString()));
            clearAccountBalanceDTOs
                .setAdjustaccout_credit_paybalance(new BigDecimal(obj[11]
                    .toString()));
            clearAccountBalanceDTOs.setClearinteres_count(new Integer(obj[12]
                .toString()));
            clearAccountBalanceDTOs.setClearinteres_paybalance(new BigDecimal(
                obj[13].toString()));

            clearAccountBalanceDTOs.setPre_rest_paybalance(new BigDecimal(
                obj[14].toString()));
            clearAccountBalanceDTOs.setCur_rest_paybalance(new BigDecimal(
                obj[15].toString()));
            int credit_count = 0;
            int credit_balance = 0;
            credit_count = Integer.parseInt(obj[0].toString())
                + Integer.parseInt(obj[2].toString())
                + Integer.parseInt(obj[4].toString())
                + Integer.parseInt(obj[6].toString())
                + Integer.parseInt(obj[8].toString())
                + Integer.parseInt(obj[10].toString())
                + Integer.parseInt(obj[12].toString());
            // System.out.println("credit----"+credit_count);
            credit_balance = Integer.parseInt(obj[0].toString())
                + Integer.parseInt(obj[2].toString())
                + Integer.parseInt(obj[4].toString())
                + Integer.parseInt(obj[6].toString())
                + Integer.parseInt(obj[8].toString())
                + Integer.parseInt(obj[10].toString())
                + Integer.parseInt(obj[12].toString());
            // System.out.println("credit_balance----"+credit_balance);

            clearAccountBalanceDTOs.setCredit_count(new Integer(credit_count));
            // clearAccountBalanceDTOs.setCredit_paybalance(new
            // BigDecimal(credit_balance));

            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    ClearAccountBalanceDTO clearAccountBalanceDTO = (ClearAccountBalanceDTO) tableList
        .get(0);
    // System.out.println("--------clearAccountBalanceDTO---------"+clearAccountBalanceDTO);
    return tableList;
  }

  /**
   * 于庆丰 根据biz_id查询 aa101
   * 
   * @param id
   * @return
   */
  public List queryOrgHAFAccountFlowByBizId(final String id) {

    List list = null;

    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow from OrgHAFAccountFlow orgHAFAccountFlow  where orgHAFAccountFlow.bizId = ?";
          Vector parameters = new Vector();
          parameters.add(new BigDecimal(id));
          Query query = session.createQuery(hql);
          query.setParameter(0, parameters.get(0));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 于庆丰 根据bizID 业务类型 查询AA101
   * 
   * @param id
   * @return
   */
  public String queryOrgHAFAccountFlowByBizIdType(final String id,
      final String type) {

    // List list = null;
    String pkid = "";
    try {
      pkid = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a101.id from AA101 a101 ";
          Vector parameters = new Vector();
          String criterion = "";
          if (id != null && !"".equals(id)) {
            criterion += "a101.biz_id = ?  and ";
            parameters.add(new BigDecimal(id));
          }
          if (type != null && !"".equals(type)) {
            criterion += "a101.biz_type = ?  and ";
            parameters.add(type);
          }
          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pkid;
  }

  /**
   * 按钮查询无条件情况下和有条件情况下 根据条件查询() 2007-07-11
   * 
   * @author 于庆丰
   * @param noteNum
   * @param docNum
   * @param orgId
   * @param orgName
   * @param operator
   * @param bank
   * @param payStatus
   * @param startDate
   * @param endDate
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public List queryByCriterions(final String noteNum, final String docNum,
      final String orgId, final String orgName, final String operator,
      final String collectionBank, final String bizStatus,
      final String startDate, final String endDate, final int start,
      final int pageSize, final String orderBy, final String order,
      final SecurityInfo securityInfo, final int page, final String biz_Type) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List temp_list = new ArrayList();
          String hql = "select orgHAFAccountFlow.noteNum,orgHAFAccountFlow.docNum,"
              + "orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name,"
              + "orgHAFAccountFlow.bizType_,orgHAFAccountFlow.personTotal,"
              + "orgHAFAccountFlow.interest,orgHAFAccountFlow.debit,"
              + "orgHAFAccountFlow.credit,orgHAFAccountFlow.settDate,"
              + "orgHAFAccountFlow.bizStatus,orgHAFAccountFlow.id,orgHAFAccountFlow.reserveaA  from OrgHAFAccountFlow orgHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {// 部门编号
            criterion += "To_Char(orgHAFAccountFlow.org.id) like ?  and ";
            parameters.add("%" + orgId + "%");
          }

          if (orgName != null && !orgName.equals("")) {// 部门名称
            criterion += "orgHAFAccountFlow.org.orgInfo.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (docNum != null && !docNum.equals("")) {// 凭证编号
            criterion += "orgHAFAccountFlow.docNum like ?  and ";
            parameters.add("%" + docNum + "%");
          }

          if (noteNum != null && !noteNum.equals("")) {// 票据编号
            criterion += "orgHAFAccountFlow.noteNum  like ?  and ";
            parameters.add("%" + noteNum + "%");
          }

          if (operator != null && !operator.equals("")) {// 制表人
            String criterinon_ = "and select bizActivityLog.bizid from BizActivityLog bizActivityLog where bizActivityLog.operator = ?  and "
                + "bizActivityLog.bizid = (select orgHAFAccountFlow.bizId from OrgHAFAccountFlow orgHAFAccountFlow where orgHAFAccountFlow.bizStatus = 3)";
            parameters.add(operator);
            criterion += "orgHAFAccountFlow.bizId = ? and ";
            parameters.add(criterinon_);
          }

          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {// 开始日期
            criterion += " (orgHAFAccountFlow.settDate between ?  and  ? ) and ";

            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (endDate != null && !endDate.equals("")
              && (startDate == null || startDate.equals(""))) {// 结束日期
            criterion += "  orgHAFAccountFlow.settDate= ? and  ";
            parameters.add(endDate);
          }
          if (startDate != null && !startDate.equals("")
              && (endDate == null || endDate.equals(""))) {// 结束日期
            criterion += "  orgHAFAccountFlow.settDate= ? and  ";
            parameters.add(startDate);
          }

          if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
            criterion += "orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collectionBank);
          }

          if (bizStatus != null && !bizStatus.equals("")) {// 业务状态

            criterion += "orgHAFAccountFlow.bizStatus = ? and ";
            parameters.add(new BigDecimal(bizStatus));
          }
          if (biz_Type != null && !biz_Type.equals("")) {// 业务类型
            criterion += "orgHAFAccountFlow.biz_Type = ? and ";
            parameters.add(biz_Type);
          }
          if (bizStatus == null && criterion.length() != 0) {
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and  (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5)  and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

            String ob = orderBy;
            if (ob == null)
              ob = " orgHAFAccountFlow.bizStatus ASC ,orgHAFAccountFlow.bizId ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "order by " + ob + " " + od;

          }
          if (bizStatus != null && criterion.length() != 0) {
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and  orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

            String ob = orderBy;
            if (ob == null)
              ob = " orgHAFAccountFlow.bizStatus ASC ,orgHAFAccountFlow.bizId ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "order by " + ob + " " + od;

          }
          if (criterion.length() == 0) {

            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL();

            String ob = orderBy;
            if (ob == null)
              ob = " orgHAFAccountFlow.id ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "order by " + ob + " " + od;
          }

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator it = query.iterate();
          List queryList = query.list();
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            it = query.iterate();
          }
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BizcheckDTO bizcheckDTO = new BizcheckDTO();
            if (obj[0] == null) {
              bizcheckDTO.setNoteNum("");
            } else {
              bizcheckDTO.setNoteNum(obj[0].toString());
            }
            if (obj[1] == null) {
              bizcheckDTO.setDocNum("");
            } else {
              bizcheckDTO.setDocNum(obj[1].toString());
            }
            bizcheckDTO.setOrgId(BusiTools.convertTenNumber(obj[2].toString()));
            bizcheckDTO.setOrgName(obj[3].toString());

            // bizcheckDTO.setBizType(obj[4].toString());
            try {
              bizcheckDTO.setBizType(BusiTools.getBusiValue_WL(obj[4]
                  .toString(), BusiConst.CLEARACCOUNTBUSINESSTYPE_WL));
            } catch (Exception e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
            }

            bizcheckDTO.setPersonTotal(obj[5].toString());
            bizcheckDTO.setInterest(obj[6].toString());
            BigDecimal dcitsum = new BigDecimal(0.0);
            BigDecimal debit = new BigDecimal(obj[7].toString());// 借方
            BigDecimal credit = new BigDecimal(obj[8].toString());// 贷方
            if (debit.compareTo(credit) == 1) {
              dcitsum = dcitsum.add(debit);
              bizcheckDTO.setType("借");
            }
            if (debit.compareTo(credit) == -1) {
              dcitsum = dcitsum.add(credit);
              bizcheckDTO.setType("贷");
            }
            if (debit.compareTo(credit) == 0) {
              bizcheckDTO.setType("平");
            }
            bizcheckDTO.setMoney(dcitsum.toString());

            // bizcheckDTO.setDebit(obj[7].toString());
            // bizcheckDTO.setCredit(obj[8].toString());
            bizcheckDTO.setSettDate(obj[9].toString());
            try {
              bizcheckDTO.setBizStatus(BusiTools.getBusiValue(Integer
                  .parseInt("" + obj[10].toString()), BusiConst.BUSINESSSTATE));
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            bizcheckDTO.setId(obj[11].toString());
            if (obj[12] != null && !obj[12].toString().equals("")) {
              bizcheckDTO.setReserveaA(obj[12].toString());
            } else {
              bizcheckDTO.setReserveaA("");
            }
            temp_list.add(bizcheckDTO);
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

  public List queryByCriterionsOtherWuht(final String noteNum,
      final String docNum, final String orgId, final String orgName,
      final String operator, final String collectionBank,
      final String bizStatus, final String startDate, final String endDate,
      final int start, final int pageSize, final String orderBy,
      final String order, final SecurityInfo securityInfo, final String biz_Type) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List temp_list = new ArrayList();
          String hql = "select orgHAFAccountFlow.personTotal,"
              + "orgHAFAccountFlow.interest,orgHAFAccountFlow.debit,"
              + "orgHAFAccountFlow.credit,orgHAFAccountFlow.bizStatus,orgHAFAccountFlow.bizId,orgHAFAccountFlow.id,orgHAFAccountFlow.org.id,orgHAFAccountFlow.biz_Type,orgHAFAccountFlow.reserveaA "
              + " from OrgHAFAccountFlow orgHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {// 部门编号
            criterion += "To_Char(orgHAFAccountFlow.org.id) like ?  and ";
            parameters.add("%" + orgId + "%");
          }

          if (orgName != null && !orgName.equals("")) {// 部门名称
            criterion += "orgHAFAccountFlow.org.orgInfo.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (docNum != null && !docNum.equals("")) {// 凭证编号
            criterion += "orgHAFAccountFlow.docNum like ?  and ";
            parameters.add("%" + docNum + "%");
          }

          if (noteNum != null && !noteNum.equals("")) {// 票据编号
            criterion += "orgHAFAccountFlow.noteNum  like ?  and ";
            parameters.add("%" + noteNum + "%");
          }
          if (operator != null && !operator.equals("")) {// 制表人
            String criterinon_ = "and select bizActivityLog.bizid from BizActivityLog bizActivityLog where bizActivityLog.operator = ?  and "
                + "bizActivityLog.bizid = (select orgHAFAccountFlow.bizId from OrgHAFAccountFlow orgHAFAccountFlow.bizStatus = 3)";
            parameters.add(operator);
            criterion += "orgHAFAccountFlow.bizId = ? and ";
            parameters.add(criterinon_);
          }

          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {// 开始日期
            criterion += " (orgHAFAccountFlow.settDate between ?  and  ? ) and ";

            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (endDate != null && !endDate.equals("")
              && (startDate == null || startDate.equals(""))) {// 结束日期
            criterion += "  orgHAFAccountFlow.settDate= ? and  ";
            parameters.add(endDate);
          }
          if (startDate != null && !startDate.equals("")
              && (endDate == null || endDate.equals(""))) {// 结束日期
            criterion += "  orgHAFAccountFlow.settDate= ? and  ";
            parameters.add(startDate);
          }

          if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
            criterion += "orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collectionBank);
          }

          if (bizStatus != null && !bizStatus.equals("")) {// 业务状态

            criterion += "orgHAFAccountFlow.bizStatus = ? and ";
            parameters.add(new BigDecimal(bizStatus));
          }
          if (biz_Type != null && !biz_Type.equals("")) {// 业务类型
            criterion += "orgHAFAccountFlow.biz_Type = ? and ";
            parameters.add(biz_Type);
          }
          if (criterion.length() != 0) {
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and  (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5)  and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

            String ob = orderBy;
            if (ob == null)
              ob = " orgHAFAccountFlow.id ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "order by " + ob + " " + od;

          } else {

            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and  (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL();

            // and select count(*) count from empHAFAccountFlow

            String ob = orderBy;
            if (ob == null)
              ob = " orgHAFAccountFlow.id ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "order by " + ob + " " + od;
          }

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.iterate();

          it = query.iterate();

          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BizcheckDTO bizcheckDTO = new BizcheckDTO();
            if (obj[0] == null) {
              bizcheckDTO.setPersonTotal("0");
            } else {
              bizcheckDTO.setPersonTotal(obj[0].toString());
            }
            if (obj[1] == null) {
              bizcheckDTO.setInterest("0");
            } else {
              bizcheckDTO.setInterest(obj[1].toString());
            }
            if (obj[2] == null) {
              obj[2] = new BigDecimal(0);
            }
            if (obj[3] == null) {
              obj[3] = new BigDecimal(0);
            }
            BigDecimal dcitsum = new BigDecimal(0.0);
            BigDecimal debit = new BigDecimal(obj[2].toString());// 借方
            BigDecimal credit = new BigDecimal(obj[3].toString());// 贷方
            if (debit.compareTo(credit) == 1) {
              dcitsum = dcitsum.add(debit);
            } else {
              dcitsum = dcitsum.add(credit);
            }
            bizcheckDTO.setMoney(dcitsum.toString());
            bizcheckDTO.setBizStatus(obj[4].toString());
            bizcheckDTO.setBizId(obj[5].toString());
            bizcheckDTO.setId(obj[6].toString());
            bizcheckDTO.setOrgId(obj[7].toString());
            bizcheckDTO.setBizType(obj[8].toString());
            if (obj[9] != null && !obj[9].toString().equals("")) {
              bizcheckDTO.setReserveaA(obj[9].toString());
            } else {
              bizcheckDTO.setReserveaA("");
            }
            temp_list.add(bizcheckDTO);
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

  public int queryByCriterionsWuht(final String noteNum, final String docNum,
      final String orgId, final String orgName, final String operator,
      final String collectionBank, final String bizStatus,
      final String startDate, final String endDate, final int start,
      final int pageSize, final String orderBy, final String order,
      final SecurityInfo securityInfo, final String biz_Type) {
    int count = 0;
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      Integer countInteger = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select count(orgHAFAccountFlow.id)  from OrgHAFAccountFlow orgHAFAccountFlow ";
              Vector parameters = new Vector();
              String criterion = "";

              if (orgId != null && !orgId.equals("")) {// 部门编号
                criterion += "To_Char(orgHAFAccountFlow.org.id) like ?  and ";
                parameters.add("%" + orgId + "%");
              }

              if (orgName != null && !orgName.equals("")) {// 部门名称
                criterion += "orgHAFAccountFlow.org.orgInfo.name like ? and ";
                parameters.add("%" + orgName + "%");
              }

              if (docNum != null && !docNum.equals("")) {// 凭证编号
                criterion += "orgHAFAccountFlow.docNum like ?  and ";
                parameters.add("%" + docNum + "%");
              }

              if (noteNum != null && !noteNum.equals("")) {// 票据编号
                criterion += "orgHAFAccountFlow.noteNum  like ?  and ";
                parameters.add("%" + noteNum + "%");
              }
              if (operator != null && !operator.equals("")) {// 制表人
                String criterinon_ = "and select bizActivityLog.bizid from BizActivityLog bizActivityLog where bizActivityLog.operator = ?  and "
                    + "bizActivityLog.bizid = (select orgHAFAccountFlow.bizId from OrgHAFAccountFlow orgHAFAccountFlow.bizStatus = 3)";
                // String criterinon
                parameters.add(operator);
                criterion += "orgHAFAccountFlow.bizId = ? and ";
                parameters.add(criterinon_);
              }

              if (startDate != null && !startDate.equals("") && endDate != null
                  && !endDate.equals("")) {// 开始日期
                criterion += " (orgHAFAccountFlow.settDate between ?  and  ? ) and ";

                parameters.add(startDate);
                parameters.add(endDate);
              }
              if (endDate != null && !endDate.equals("")
                  && (startDate == null || startDate.equals(""))) {// 结束日期
                criterion += "  orgHAFAccountFlow.settDate= ? and  ";
                parameters.add(endDate);
              }
              if (startDate != null && !startDate.equals("")
                  && (endDate == null || endDate.equals(""))) {// 结束日期
                criterion += "  orgHAFAccountFlow.settDate= ? and  ";
                parameters.add(startDate);
              }

              if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
                criterion += "orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collectionBank);
              }

              if (bizStatus != null && !bizStatus.equals("")) {// 业务状态
                criterion += "orgHAFAccountFlow.bizStatus = ? and ";
                parameters.add(new BigDecimal(bizStatus));
              }
              if (biz_Type != null && !biz_Type.equals("")) {// 业务类型
                criterion += "orgHAFAccountFlow.biz_Type = ? and ";
                parameters.add(biz_Type);
              }
              if (criterion.length() != 0) {
                criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and  (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id  "
                    + securityInfo.getGjjSecurityHqlSQL()
                    + " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

                String ob = orderBy;
                if (ob == null)
                  ob = " orgHAFAccountFlow.id ";

                String od = order;
                if (od == null)
                  od = "DESC";

                hql = hql + criterion + "order by " + ob + " " + od;
              } else {

                criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and  (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5)  and orgHAFAccountFlow.org.id "
                    + securityInfo.getGjjSecurityHqlSQL();

                String ob = orderBy;
                if (ob == null)
                  ob = " orgHAFAccountFlow.id ";

                String od = order;
                if (od == null)
                  od = "DESC";

                hql = hql + criterion + "order by " + ob + " " + od;
              }

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Integer countTemp = new Integer(0);
              Iterator it = query.iterate();
              if (it.hasNext()) {
                countTemp = (Integer) it.next();
              }
              return countTemp;
            }
          });
      count = countInteger.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 查询记录(List) 默认进入业务复核时查询 orgHAFAccountFlow.bizStatus = 3 吴洪涛 2007.6.27
   * 
   * @retu
   */
  public List queryOrgHAFAccountFlowListWuht(final int start,
      final int pageSize, final String orderBy, final String order,
      final SecurityInfo securityInfo, final int page) {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List temp_list = new ArrayList();
          // String hql = "select orgHAFAccountFlow from OrgHAFAccountFlow
          // orgHAFAccountFlow where orgHAFAccountFlow.bizStatus = 3 and
          // orgHAFAccountFlow.org.id "
          // + securityInfo.getGjjSecurityHqlSQL();
          String hql = "select orgHAFAccountFlow.noteNum,orgHAFAccountFlow.docNum,"
              + "orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name,"
              + "orgHAFAccountFlow.bizType_,orgHAFAccountFlow.personTotal,"
              + "orgHAFAccountFlow.interest,orgHAFAccountFlow.debit,"
              + "orgHAFAccountFlow.credit,orgHAFAccountFlow.settDate,"
              + "orgHAFAccountFlow.bizStatus,orgHAFAccountFlow.id,orgHAFAccountFlow.reserveaA  from OrgHAFAccountFlow orgHAFAccountFlow where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and  orgHAFAccountFlow.bizStatus = 3 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();

          // String hql = "select t.note_num,t.doc_num, t.org_id, b.name," +
          // " t.biz_type,t.busicount,t.interest,t.debit,t.credit," +
          // "t.sett_date,t.biz_status from AA101 t, " +
          // "AA001 a,BA001 b where t.biz_status = 3 and " +
          // "t.org_id = a.id and a.orginfo_id = b.id and t.org_id = "+
          // securityInfo.getGjjSecurityHqlSQL();
          String ob = orderBy;
          if (ob == null)
            ob = " orgHAFAccountFlow.bizStatus ASC ,orgHAFAccountFlow.bizId ";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + " order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator it = query.iterate();
          List queryList = query.list();
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            it = query.iterate();
          }
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BizcheckDTO bizcheckDTO = new BizcheckDTO();
            if (obj[0] == null) {
              bizcheckDTO.setNoteNum("");
            } else {
              bizcheckDTO.setNoteNum(obj[0].toString());
            }
            if (obj[1] == null) {
              bizcheckDTO.setDocNum("");
            } else {
              bizcheckDTO.setDocNum(obj[1].toString());
            }
            bizcheckDTO.setOrgId(BusiTools.convertTenNumber(obj[2].toString()));
            bizcheckDTO.setOrgName(obj[3].toString());

            // bizcheckDTO.setBizType(obj[4].toString());
            try {
              bizcheckDTO.setBizType(BusiTools.getBusiValue_WL(obj[4]
                  .toString(), BusiConst.CLEARACCOUNTBUSINESSTYPE_WL));
            } catch (Exception e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
            }
            if (obj[5] == null) {
              bizcheckDTO.setPersonTotal("0");
            } else {
              bizcheckDTO.setPersonTotal(obj[5].toString());
            }
            bizcheckDTO.setInterest(obj[6].toString());
            BigDecimal dcitsum = new BigDecimal(0.0);
            BigDecimal debit = new BigDecimal(obj[7].toString());// 借方
            BigDecimal credit = new BigDecimal(obj[8].toString());// 贷方
            if (debit.compareTo(credit) == 1) {
              dcitsum = dcitsum.add(debit);
              bizcheckDTO.setType("借");
            }
            if (debit.compareTo(credit) == -1) {
              dcitsum = dcitsum.add(credit);
              bizcheckDTO.setType("贷");
            }
            if (debit.compareTo(credit) == 0) {
              bizcheckDTO.setType("平");
            }
            bizcheckDTO.setMoney(dcitsum.toString());

            // bizcheckDTO.setDebit(obj[7].toString());
            // bizcheckDTO.setCredit(obj[8].toString());
            bizcheckDTO.setSettDate(obj[9].toString());
            try {
              bizcheckDTO.setBizStatus(BusiTools.getBusiValue(Integer
                  .parseInt("" + obj[10].toString()), BusiConst.BUSINESSSTATE));
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            bizcheckDTO.setId(obj[11].toString());
            if (obj[12] != null && !obj[12].toString().equals("")) {
              bizcheckDTO.setReserveaA(obj[12].toString());
            }
            temp_list.add(bizcheckDTO);
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

  public String getOperator_yg(final String name) {
    String str = "";
    try {
      str = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select t.real_name from bb201 t,ca101 c where t.id=c.id and c.username='"
              + name + "'";
          Query query = session.createSQLQuery(sql);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }

  public List queryOrgHAFAccountFlowListOtherWuht(final int start,
      final int pageSize, final String orderBy, final String order,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List temp_list = new ArrayList();
          String hql = "select orgHAFAccountFlow.personTotal,"
              + "orgHAFAccountFlow.interest,orgHAFAccountFlow.debit,"
              + "orgHAFAccountFlow.credit,orgHAFAccountFlow.bizStatus,orgHAFAccountFlow.bizId,orgHAFAccountFlow.id,orgHAFAccountFlow.org.id,orgHAFAccountFlow.biz_Type ,orgHAFAccountFlow.reserveaA"
              + " from OrgHAFAccountFlow orgHAFAccountFlow where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and  orgHAFAccountFlow.bizStatus = 3 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          String ob = orderBy;
          if (ob == null)
            ob = " orgHAFAccountFlow.bizStatus ASC ,orgHAFAccountFlow.bizId ";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + " order by " + ob + " " + od;
          Query query = session.createQuery(hql);

          Iterator it = query.iterate();

          it = query.iterate();

          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BizcheckDTO bizcheckDTO = new BizcheckDTO();
            if (obj[0] == null) {
              bizcheckDTO.setPersonTotal("0");
            } else {
              bizcheckDTO.setPersonTotal(obj[0].toString());
            }
            if (obj[1] == null) {
              bizcheckDTO.setInterest("0");
            } else {
              bizcheckDTO.setInterest(obj[1].toString());
            }
            if (obj[2] == null) {
              obj[2] = new BigDecimal(0);
            }
            if (obj[3] == null) {
              obj[3] = new BigDecimal(0);
            }
            BigDecimal dcitsum = new BigDecimal(0.0);
            BigDecimal debit = new BigDecimal(obj[2].toString());// 借方
            BigDecimal credit = new BigDecimal(obj[3].toString());// 贷方
            if (debit.compareTo(credit) == 1) {
              dcitsum = dcitsum.add(debit);
            } else {
              dcitsum = dcitsum.add(credit);
            }
            bizcheckDTO.setMoney(dcitsum.toString());
            bizcheckDTO.setBizStatus(obj[4].toString());
            bizcheckDTO.setBizId(obj[5].toString());
            bizcheckDTO.setId(obj[6].toString());
            bizcheckDTO.setOrgId(obj[7].toString());
            bizcheckDTO.setBizType(obj[8].toString());
            if (obj[9] != null && !obj[9].toString().equals("")) {
              bizcheckDTO.setReserveaA(obj[9].toString());
            }
            temp_list.add(bizcheckDTO);
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
   * 查询记录(List) 默认进入业务复核时查询 count 吴洪涛 2007.6.27
   * 
   * @retu
   */
  public int queryOrgHAFAccountFlowListWuht(final SecurityInfo securityInfo) {
    int count = 0;
    try {
      Integer countInteger = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(orgHAFAccountFlow.id) from OrgHAFAccountFlow orgHAFAccountFlow where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and  orgHAFAccountFlow.bizStatus = 3 and orgHAFAccountFlow.org.id "
                  + securityInfo.getGjjSecurityHqlSQL();
              session.clear();
              Query query = session.createQuery(hql);

              Integer countTemp = new Integer(0);
              Iterator it = query.iterate();
              if (it.hasNext()) {
                countTemp = (Integer) it.next();
              }
              return countTemp;
            }
          });
      count = countInteger.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return count;
  }

  /**
   * 查询记录(List) 业务复核查询 operator(有值的情况) 吴洪涛 2007.6.27
   * 
   * @retu
   */
  public List queryByCriterionsByoperatorWuht(final String noteNum,
      final String docNum, final String orgId, final String orgName,
      final String operator, final String collectionBank,
      final String bizStatus, final String startDate, final String endDate,
      final int start, final int pageSize, final String orderBy,
      final String order, final SecurityInfo securityInfo, final int page,
      final String biz_Type) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List temp_list = new ArrayList();
          String hql = "select orgHAFAccountFlow.noteNum,orgHAFAccountFlow.docNum,"
              + "orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name,"
              + "orgHAFAccountFlow.bizType_,orgHAFAccountFlow.personTotal,"
              + "orgHAFAccountFlow.interest,orgHAFAccountFlow.debit,"
              + "orgHAFAccountFlow.credit,orgHAFAccountFlow.settDate,"
              + "orgHAFAccountFlow.bizStatus,orgHAFAccountFlow.id,orgHAFAccountFlow.reserveaA  from OrgHAFAccountFlow orgHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {// 部门编号
            criterion += "To_Char(orgHAFAccountFlow.org.id) like ?  and ";
            parameters.add("%" + orgId + "%");
          }

          if (orgName != null && !orgName.equals("")) {// 部门名称
            criterion += "orgHAFAccountFlow.org.orgInfo.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (docNum != null && !docNum.equals("")) {// 凭证编号
            criterion += "orgHAFAccountFlow.docNum like ?  and ";
            parameters.add("%" + docNum + "%");
          }

          if (noteNum != null && !noteNum.equals("")) {// 票据编号
            criterion += "orgHAFAccountFlow.noteNum  like ?  and ";
            parameters.add("%" + noteNum + "%");
          }
          if (operator != null && !operator.equals("")) {// 制表人

            criterion += "orgHAFAccountFlow.reserveaA = ? and ";
            parameters.add(operator);
          }

          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {// 开始日期
            criterion += " (orgHAFAccountFlow.settDate between ?  and  ? ) and ";

            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (endDate != null && !endDate.equals("")
              && (startDate == null || startDate.equals(""))) {// 结束日期
            criterion += "  orgHAFAccountFlow.settDate= ? and  ";
            parameters.add(endDate);
          }
          if (startDate != null && !startDate.equals("")
              && (endDate == null || endDate.equals(""))) {// 结束日期
            criterion += "  orgHAFAccountFlow.settDate= ? and  ";
            parameters.add(startDate);
          }

          if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
            criterion += "orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collectionBank);
          }

          if (bizStatus != null && !bizStatus.equals("")) {// 业务状态
            criterion += "orgHAFAccountFlow.bizStatus = ? and ";
            parameters.add(new BigDecimal(bizStatus));
          }
          if (biz_Type != null && !biz_Type.equals("")) {// 业务类型
            criterion += "orgHAFAccountFlow.biz_Type = ? and ";
            parameters.add(biz_Type);
          }

          if (criterion.length() != 0) {
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1) and (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5  )  and "
                + "  orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

            String ob = orderBy;
            if (ob == null)
              ob = " orgHAFAccountFlow.bizStatus ASC ,orgHAFAccountFlow.bizId ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "order by " + ob + " " + od;

          } else {

            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 "
                + "or orgHAFAccountFlow.bizStatus = 5  )  "
                + " and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL();

            String ob = orderBy;
            if (ob == null)
              ob = " orgHAFAccountFlow.id ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "order by " + ob + " " + od;
          }

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator it = query.iterate();
          List queryList = query.list();
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            it = query.iterate();
          }
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BizcheckDTO bizcheckDTO = new BizcheckDTO();
            if (obj[0] == null) {
              bizcheckDTO.setNoteNum("");
            } else {
              bizcheckDTO.setNoteNum(obj[0].toString());
            }
            if (obj[1] == null) {
              bizcheckDTO.setDocNum("");
            } else {
              bizcheckDTO.setDocNum(obj[1].toString());
            }
            bizcheckDTO.setOrgId(BusiTools.convertTenNumber(obj[2].toString()));
            bizcheckDTO.setOrgName(obj[3].toString());

            // bizcheckDTO.setBizType(obj[4].toString());
            try {
              bizcheckDTO.setBizType(BusiTools.getBusiValue_WL(obj[4]
                  .toString(), BusiConst.CLEARACCOUNTBUSINESSTYPE_WL));
            } catch (Exception e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
            }

            bizcheckDTO.setPersonTotal(obj[5].toString());
            bizcheckDTO.setInterest(obj[6].toString());
            BigDecimal dcitsum = new BigDecimal(0.0);
            BigDecimal debit = new BigDecimal(obj[7].toString());// 借方
            BigDecimal credit = new BigDecimal(obj[8].toString());// 贷方
            if (debit.compareTo(credit) == 1) {
              dcitsum = dcitsum.add(debit);
              bizcheckDTO.setType("借");
            }
            if (debit.compareTo(credit) == -1) {
              dcitsum = dcitsum.add(credit);
              bizcheckDTO.setType("贷");
            }
            if (debit.compareTo(credit) == 0) {
              bizcheckDTO.setType("平");
            }
            bizcheckDTO.setMoney(dcitsum.toString());

            // bizcheckDTO.setDebit(obj[7].toString());
            // bizcheckDTO.setCredit(obj[8].toString());
            bizcheckDTO.setSettDate(obj[9].toString());
            try {
              bizcheckDTO.setBizStatus(BusiTools.getBusiValue(Integer
                  .parseInt("" + obj[10].toString()), BusiConst.BUSINESSSTATE));
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            bizcheckDTO.setId(obj[11].toString());
            if (obj[12] != null && !obj[12].toString().equals("")) {
              bizcheckDTO.setReserveaA(obj[12].toString());
            }
            temp_list.add(bizcheckDTO);
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

  public List queryByCriterionsByoperatorOtherWuht(final String noteNum,
      final String docNum, final String orgId, final String orgName,
      final String operator, final String collectionBank,
      final String bizStatus, final String startDate, final String endDate,
      final int start, final int pageSize, final String orderBy,
      final String order, final SecurityInfo securityInfo, final String biz_Type) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List temp_list = new ArrayList();
          String hql = "select orgHAFAccountFlow.personTotal,"
              + "orgHAFAccountFlow.interest,orgHAFAccountFlow.debit,"
              + "orgHAFAccountFlow.credit,orgHAFAccountFlow.bizStatus,orgHAFAccountFlow.bizId,orgHAFAccountFlow.id,orgHAFAccountFlow.org.id,orgHAFAccountFlow.biz_Type,orgHAFAccountFlow.reserveaA "
              + " from OrgHAFAccountFlow orgHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {// 部门编号
            criterion += "To_Char(orgHAFAccountFlow.org.id) like ?  and ";
            parameters.add("%" + orgId + "%");
          }

          if (orgName != null && !orgName.equals("")) {// 部门名称
            criterion += "orgHAFAccountFlow.org.orgInfo.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (docNum != null && !docNum.equals("")) {// 凭证编号
            criterion += "orgHAFAccountFlow.docNum like ?  and ";
            parameters.add("%" + docNum + "%");
          }

          if (noteNum != null && !noteNum.equals("")) {// 票据编号
            criterion += "orgHAFAccountFlow.noteNum  like ?  and ";
            parameters.add("%" + noteNum + "%");
          }

          if (operator != null && !operator.equals("")) {// 制表人

            criterion += "orgHAFAccountFlow.reserveaA = ? and ";
            parameters.add(operator);
          }

          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {// 开始日期
            criterion += " (orgHAFAccountFlow.settDate between ?  and  ? ) and ";

            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (endDate != null && !endDate.equals("")
              && (startDate == null || startDate.equals(""))) {// 结束日期
            criterion += "  orgHAFAccountFlow.settDate= ? and  ";
            parameters.add(endDate);
          }
          if (startDate != null && !startDate.equals("")
              && (endDate == null || endDate.equals(""))) {// 结束日期
            criterion += "  orgHAFAccountFlow.settDate= ? and  ";
            parameters.add(startDate);
          }

          if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
            criterion += "orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collectionBank);
          }

          if (bizStatus != null && !bizStatus.equals("")) {// 业务状态
            criterion += "orgHAFAccountFlow.bizStatus = ? and ";
            parameters.add(new BigDecimal(bizStatus));
          }
          if (biz_Type != null && !biz_Type.equals("")) {// 业务类型
            criterion += "orgHAFAccountFlow.biz_Type = ? and ";
            parameters.add(biz_Type);
          }

          if (criterion.length() != 0) {
            criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5  ) "
                + "  and orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

            String ob = orderBy;
            if (ob == null)
              ob = " orgHAFAccountFlow.bizStatus ASC ,orgHAFAccountFlow.bizId ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "order by " + ob + " " + od;

          } else {

            criterion = "where  (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and  (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 "
                + "or orgHAFAccountFlow.bizStatus = 5  ) "
                + " and  orgHAFAccountFlow.org.id "
                + securityInfo.getGjjSecurityHqlSQL();

            String ob = orderBy;
            if (ob == null)
              ob = " orgHAFAccountFlow.id ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "order by " + ob + " " + od;
          }

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.iterate();

          it = query.iterate();

          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            BizcheckDTO bizcheckDTO = new BizcheckDTO();
            if (obj[0] == null) {
              bizcheckDTO.setPersonTotal("0");
            } else {
              bizcheckDTO.setPersonTotal(obj[0].toString());
            }
            if (obj[1] == null) {
              bizcheckDTO.setInterest("0");
            } else {
              bizcheckDTO.setInterest(obj[1].toString());
            }
            if (obj[2] == null) {
              obj[2] = new BigDecimal(0);
            }
            if (obj[3] == null) {
              obj[3] = new BigDecimal(0);
            }
            BigDecimal dcitsum = new BigDecimal(0.0);
            BigDecimal debit = new BigDecimal(obj[2].toString());// 借方
            BigDecimal credit = new BigDecimal(obj[3].toString());// 贷方
            if (debit.compareTo(credit) == 1) {
              dcitsum = dcitsum.add(debit);
            } else {
              dcitsum = dcitsum.add(credit);
            }
            bizcheckDTO.setMoney(dcitsum.toString());
            bizcheckDTO.setBizStatus(obj[4].toString());
            bizcheckDTO.setBizId(obj[5].toString());
            bizcheckDTO.setId(obj[6].toString());
            bizcheckDTO.setOrgId(obj[7].toString());
            bizcheckDTO.setBizType(obj[8].toString());
            if (obj[9] != null && !obj[9].toString().equals("")) {
              bizcheckDTO.setReserveaA(obj[9].toString());
            }
            temp_list.add(bizcheckDTO);
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
   * 根据错单位ID和业务类型询出OrgHAFAccountFlow
   * 
   * @param orgId 单位ID
   * @param bisType 业务类型
   * @param status 业务状态
   * @return OrgHAFAccountFlow yu
   */
  public OrgHAFAccountFlow queryOrgHAFAccountFlowByIdAndType(
      final String bizId, final String bizType) {
    return (OrgHAFAccountFlow) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from OrgHAFAccountFlow orgHAFAccountFlow ";
            Vector parameters = new Vector();
            String criterion = "";
            if (bizId != null && !bizId.equals("")) {
              criterion += "orgHAFAccountFlow.bizId = ? and ";
              parameters.add(new BigDecimal(bizId));
            }
            if (bizType != null && !bizType.equals("")) {
              criterion += "orgHAFAccountFlow.bizType_ = ? and ";
              parameters.add(bizType);
            }
            if (criterion.length() != 0) {
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }
            hql = hql + criterion;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();
          }
        });
  }

  /**
   * 王菱 根据AA101的主键ID返回对应业务的业务ID
   */
  public String queryBizIDByFlowID_WL(final String flowID) {
    String bizID = "";
    try {
      bizID = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select orgHAFAccountFlow.bizId  from OrgHAFAccountFlow orgHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";

          if (flowID != null && !flowID.equals("")) {
            criterion += " orgHAFAccountFlow.id=?  and ";
            parameters.add(new Integer(flowID));
          }

          if (criterion.length() != 0) {
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          }
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bizID;
  }

  public int queryByCriterionsByoperatorCountWuht(final String noteNum,
      final String docNum, final String orgId, final String orgName,
      final String operator, final String collectionBank,
      final String bizStatus, final String startDate, final String endDate,
      final int start, final int pageSize, final String orderBy,
      final String order, final SecurityInfo securityInfo, final String biz_Type) {
    int count = 0;
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      Integer countInteger = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select count(orgHAFAccountFlow.id)  from OrgHAFAccountFlow orgHAFAccountFlow ";
              Vector parameters = new Vector();
              String criterion = "";

              if (orgId != null && !orgId.equals("")) {// 部门编号
                criterion += "To_Char(orgHAFAccountFlow.org.id) like ?  and ";
                parameters.add("%" + orgId + "%");
              }

              if (orgName != null && !orgName.equals("")) {// 部门名称
                criterion += "orgHAFAccountFlow.org.orgInfo.name like ? and ";
                parameters.add("%" + orgName + "%");
              }

              if (docNum != null && !docNum.equals("")) {// 凭证编号
                criterion += "orgHAFAccountFlow.docNum like ?  and ";
                parameters.add("%" + docNum + "%");
              }

              if (noteNum != null && !noteNum.equals("")) {// 票据编号
                criterion += "orgHAFAccountFlow.noteNum  like ?  and ";
                parameters.add("%" + noteNum + "%");
              }
              if (operator != null && !operator.equals("")) {// 制表人

                criterion += "orgHAFAccountFlow.reserveaA = ? and ";
                parameters.add(operator);
              }

              if (startDate != null && !startDate.equals("") && endDate != null
                  && !endDate.equals("")) {// 开始日期
                criterion += " (orgHAFAccountFlow.settDate between ?  and  ? ) and ";

                parameters.add(startDate);
                parameters.add(endDate);
              }
              if (endDate != null && !endDate.equals("")
                  && (startDate == null || startDate.equals(""))) {// 结束日期
                criterion += "  orgHAFAccountFlow.settDate= ? and  ";
                parameters.add(endDate);
              }
              if (startDate != null && !startDate.equals("")
                  && (endDate == null || endDate.equals(""))) {// 结束日期
                criterion += "  orgHAFAccountFlow.settDate= ? and  ";
                parameters.add(startDate);
              }

              if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
                criterion += "orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
                parameters.add(collectionBank);
              }

              if (bizStatus != null && !bizStatus.equals("")) {// 业务状态
                criterion += "orgHAFAccountFlow.bizStatus = ? and ";
                parameters.add(new BigDecimal(bizStatus));
              }
              if (biz_Type != null && !biz_Type.equals("")) {// 业务类型
                criterion += "orgHAFAccountFlow.biz_Type = ? and ";
                parameters.add(biz_Type);
              }
              if (criterion.length() != 0) {
                criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5  )  "
                    + "  and orgHAFAccountFlow.org.id "
                    + securityInfo.getGjjSecurityHqlSQL()
                    + " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

                String ob = orderBy;
                if (ob == null)
                  ob = " orgHAFAccountFlow.id ";

                String od = order;
                if (od == null)
                  od = "DESC";

                hql = hql + criterion + "order by " + ob + " " + od;
              } else {
                criterion = "where (orgHAFAccountFlow.specailType is null or orgHAFAccountFlow.specailType=1)  and  (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 "
                    + "or orgHAFAccountFlow.bizStatus = 5  ) "
                    + " and orgHAFAccountFlow.org.id "
                    + securityInfo.getGjjSecurityHqlSQL();

                String ob = orderBy;
                if (ob == null)
                  ob = " orgHAFAccountFlow.id ";

                String od = order;
                if (od == null)
                  od = "DESC";

                hql = hql + criterion + "order by " + ob + " " + od;
              }

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              Integer countTemp = new Integer(0);
              Iterator it = query.iterate();
              if (it.hasNext()) {
                countTemp = (Integer) it.next();
              }
              return countTemp;
            }
          });
      count = countInteger.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * 李鹏 扎账
   * 
   * @param orgStr 主键id
   * @param busiDate 会计日期
   * @param status 业务状态
   * @return OrgHAFAccountFlow yu
   */
  public boolean dealwithClearAccount(String orgStr, String yearaccount,
      String years, String op_time, String oper, String ip)
      throws BusinessException, HibernateException, SQLException {
    boolean voild = false;
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      CallableStatement cs = conn.prepareCall("{call NEWACCOUNT(?,?,?,?,?,?)}");
      cs.setString(1, orgStr);
      cs.setString(2, yearaccount);
      cs.setString(3, years);
      cs.setString(4, op_time);
      cs.setString(5, oper);
      cs.setString(6, ip);
      voild = cs.execute();
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("扎账失败!!!");
    }
    return voild;
  }

  public boolean dealwithClearAllAccount(List list, String yearaccount,
      String years, String op_time, String oper, String ip)
      throws BusinessException, HibernateException, SQLException {
    boolean voild = false;
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      // 循环插入临时表PreparedStatement不能使用，因为其相当开启隐形游标当时164抛出异常
      Statement statement = conn.createStatement();
      for (int i = 0; i < list.size(); i++) {
        String temp_flowid = "";
        temp_flowid = list.get(i) + "";
        String sql = "insert into AACLEARTEMP(id)" + "values ('" + temp_flowid
            + "')";
        statement.executeUpdate(sql);
      }
      CallableStatement cs = conn
          .prepareCall("{call NEWALLACCOUNT(?,?,?,?,?)}");
      cs.setString(1, yearaccount);
      cs.setString(2, years);
      cs.setString(3, op_time);
      cs.setString(4, oper);
      cs.setString(5, ip);
      voild = cs.execute();
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("扎账失败!!!");
    }
    return voild;
  }

  /**
   * * 于庆丰 归集情况查询--根据条件查询
   * 
   * @param officeCod
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param orgCharacter
   * @param deptInCharge
   * @param startDate
   * @param endDate
   * @param region
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryByCollectionstatisticsCriterions(final String officeCode,
      final String collectionBank, final String orgId, final String orgName,
      final String orgCharacter, final String deptInCharge,
      final String startDate, final String endDate, final String region,
      final int start, final int pageSize, final String orderBy,
      final String order, final SecurityInfo securityInfo) {
    List list = null;

    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          //
          // String hql = " select orgHAFAccountFlow from OrgHAFAccountFlow
          // orgHAFAccountFlow ";
          // Vector parameters = new Vector();
          // String criterion = "";
          //
          // if (officeCode != null && !officeCode.equals("")) {// 办事处
          // criterion += "orgHAFAccountFlow.org.orgInfo.officecode = ? and ";
          // parameters.add(officeCode);
          // }
          // if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
          // criterion += "orgHAFAccountFlow.org.orgInfo.collectionBankId = ?
          // and ";
          // parameters.add(collectionBank);
          // }
          //
          // if (orgId != null && !orgId.equals("")) {// 部门编号
          // criterion += "To_Char(orgHAFAccountFlow.org.id) like ? and ";
          // parameters.add("%" + orgId + "%");
          // }
          //
          // if (orgName != null && !orgName.equals("")) {// 部门名称
          // criterion += "orgHAFAccountFlow.org.orgInfo.name like ? and ";
          // parameters.add("%" + orgName + "%");
          // }
          //
          // if (orgCharacter != null && !orgCharacter.equals("")) {// 单位性质
          //
          // criterion += "orgHAFAccountFlow.org.orgInfo.character like ? and ";
          // parameters.add("%" + orgCharacter + "%");
          // }
          //
          // if (deptInCharge != null && !deptInCharge.equals("")) {// 主管部门
          //
          // criterion += "orgHAFAccountFlow.org.orgInfo.deptInCharge like ? and
          // ";
          // parameters.add("%" + deptInCharge + "%");
          // }
          //
          // if (startDate != null && !startDate.equals("") && endDate != null
          // && !endDate.equals("")) {// 开始日期结束日期
          // criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
          // parameters.add(startDate);
          // parameters.add(endDate);
          // }
          // if (endDate != null && !endDate.equals("")
          // && (startDate == null || startDate.equals(""))) {// 结束日期
          // criterion += " orgHAFAccountFlow.settDate= ? and ";
          // parameters.add(endDate);
          // }
          // if (startDate != null && !startDate.equals("")
          // && (endDate == null || endDate.equals(""))) {// 结束日期
          // criterion += " orgHAFAccountFlow.settDate= ? and ";
          // parameters.add(startDate);
          // }

          // System.out.println("=="+startDate);
          // System.out.println("=="+endDate);
          // String hql = " select orgHAFAccountFlow from OrgHAFAccountFlow
          // orgHAFAccountFlow ";
          String hql = "select distinct bb101.name as namel,"
              + "bb105.coll_bank_name,"
              + "aa001.id,"
              + "ba001.name,"
              + "ba001.character,"
              + "ba001.dept_in_charge,"
              + "ba001.region,"
              + " nvl((select sum(t.credit - t.debit) "
              + " from aa101 t "
              + " where (t.biz_type = 'A' or t.biz_type = 'B' or t.biz_type = 'C' or "
              + "t.biz_type = 'K' or t.biz_type = 'M') "
              + " and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate)) {
            hql = hql + " and t.sett_date <  " + startDate;
          }

          hql = hql
              + " and t.org_id = aa101.org_id),0), "
              +

              "nvl((select sum(t.credit - t.debit)"
              + " from aa101 t "
              + " where (t.biz_type = 'A' or t.biz_type = 'B' or t.biz_type = 'C' or "
              + " t.biz_type = 'K' or t.biz_type = 'M') "
              + " and t.biz_status = 5 ";
          if (endDate != null && !"".equals(endDate)) {
            hql = hql + " and t.sett_date <= " + endDate;
          }

          hql = hql + " and t.org_id = aa101.org_id),0), " +

          "nvl(( select sum(t.credit)" + " from aa101 t "
              + " where t.biz_type = 'A'" + "and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate) && endDate != null
              && !"".equals(endDate)) {
            hql = hql + " and t.sett_date between " + startDate + " and "
                + endDate;
          }

          hql = hql + " and t.org_id = aa101.org_id),0)," +

          "nvl((select sum(t.credit) " + " from aa101 t "
              + " where t.biz_type = 'M' " + " and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate) && endDate != null
              && !"".equals(endDate)) {
            hql = hql + " and t.sett_date between " + startDate + " and "
                + endDate;
          }
          hql = hql + " and t.org_id = aa101.org_id),0), " +

          " nvl((select sum(t.credit - t.debit)" + " from aa101 t "
              + " where t.biz_type = 'C' " + " and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate) && endDate != null
              && !"".equals(endDate)) {
            hql = hql + " and t.sett_date between " + startDate + " and "
                + endDate;
          }
          hql = hql + " and t.org_id = aa101.org_id),0)," +

          "nvl((select sum(t.credit) " + " from aa101 t "
              + " where t.biz_type = 'B' " + " and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate) && endDate != null
              && !"".equals(endDate)) {
            hql = hql + " and t.sett_date between " + startDate + " and "
                + endDate;
          }
          hql = hql + " and t.org_id = aa101.org_id),0)," +

          "nvl((select sum(t.credit - t.debit) " + " from aa101 t "
              + " where t.biz_type = 'K' " + " and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate) && endDate != null
              && !"".equals(endDate)) {
            hql = hql + " and t.sett_date between " + startDate + " and "
                + endDate;
          }
          hql = hql + " and t.org_id = aa101.org_id),0)" +

          " from AA101 aa101, " + " BB101 bb101, " + " AA001 aa001, "
              + " BA001 ba001, " + " BB105 bb105 "
              + " where aa101.officeCode = bb101.id "
              + " and aa001.orginfo_id = ba001.id "
              + " and aa101.org_id = aa001.id "
              + " and bb105.coll_bank_id = aa101.moneyBank " + " and aa001.id "
              + securityInfo.getGjjSecuritySQL();
          if (officeCode != null && !"".equals(officeCode)) {
            hql = hql + " and aa101.officeCode = '" + officeCode + "'";
          }
          if (collectionBank != null && !"".equals(collectionBank)) {
            hql = hql + " and aa101.moneyBank = '" + collectionBank + "'";
          }
          if (orgId != null && !"".equals(orgId)) {
            hql = hql + " and aa001.id = '" + orgId + "'";
          }
          if (orgName != null && !"".equals(orgName)) {
            hql = hql + " and ba001.name = '" + orgName + "'";
          }
          if (orgCharacter != null && !"".equals(orgCharacter)) {
            hql = hql + " and ba001.character = '" + orgCharacter + "'";
          }
          if (deptInCharge != null && !"".equals(deptInCharge)) {
            hql = hql + " and ba001.dept_in_charge = '" + deptInCharge + "'";
          }
          if (region != null && !"".equals(region)) {
            hql = hql + " and ba001.region = '" + region + "'";
          }

          // Vector parameters = new Vector();
          // String criterion = "";
          //
          // if (officeCode != null && !officeCode.equals("")) {// 办事处
          // criterion += "orgHAFAccountFlow.org.orgInfo.officecode = ? and ";
          // parameters.add(officeCode);
          // }
          // if (collectionBank != null && !collectionBank.equals("")) {// 归集银行
          // criterion += "orgHAFAccountFlow.org.orgInfo.collectionBankId = ?
          // and ";
          // parameters.add(collectionBank);
          // }
          //
          // if (orgId != null && !orgId.equals("")) {// 部门编号
          // criterion += "To_Char(orgHAFAccountFlow.org.id) like ? and ";
          // parameters.add("%" + orgId + "%");
          // }
          //
          // if (orgName != null && !orgName.equals("")) {// 部门名称
          // criterion += "orgHAFAccountFlow.org.orgInfo.name like ? and ";
          // parameters.add("%" + orgName + "%");
          // }
          //
          // if (orgCharacter != null && !orgCharacter.equals("")) {// 单位性质
          //
          // criterion += "orgHAFAccountFlow.org.orgInfo.character like ? and ";
          // parameters.add("%" + orgCharacter + "%");
          // }
          //
          // if (deptInCharge != null && !deptInCharge.equals("")) {// 主管部门
          //
          // criterion += "orgHAFAccountFlow.org.orgInfo.deptInCharge like ? and
          // ";
          // parameters.add("%" + deptInCharge + "%");
          // }
          //
          // if (startDate != null && !startDate.equals("") && endDate != null
          // && !endDate.equals("")) {// 开始日期结束日期
          // criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
          // parameters.add(startDate);
          // parameters.add(endDate);
          // }
          // if (endDate != null && !endDate.equals("")
          // && (startDate == null || startDate.equals(""))) {// 结束日期
          // criterion += " orgHAFAccountFlow.settDate= ? and ";
          // parameters.add(endDate);
          // }
          // if (startDate != null && !startDate.equals("")
          // && (endDate == null || endDate.equals(""))) {// 结束日期
          // criterion += " orgHAFAccountFlow.settDate= ? and ";
          // parameters.add(startDate);
          // }
          //          
          // if(region != null && !region.equals("")){//所在地区
          // criterion += " orgHAFAccountFlow.org.orgInfo.region like ? and ";
          // parameters.add("%" +region+ "%");
          // }
          //
          // if (criterion.length() != 0) {
          // criterion = "where orgHAFAccountFlow.bizStatus=5 and
          // orgHAFAccountFlow.org.id "
          // // criterion = "where orgHAFAccountFlow.org.id "
          // + securityInfo.getGjjSecurityHqlSQL() + " and "
          // + criterion.substring(0, criterion.lastIndexOf("and"));
          //
          String ob = orderBy;

          if (ob == null)
            ob = " aa001.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + " order by " + ob + " " + od;
          //
          // hql = hql + criterion + "order by " + ob + " " + od + " group by
          // orgHAFAccountFlow.org.id";
          //
          // } else {
          //
          // criterion = "where orgHAFAccountFlow.org.id "
          // + securityInfo.getGjjSecurityHqlSQL();
          // hql = hql + criterion;
          // }
          // System.out.println(hql);
          Query query = session.createSQLQuery(hql);
          // for (int i = 0; i < parameters.size(); i++) {
          // query.setParameter(i, parameters.get(i));

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List temp_list = query.list();
          List t = new ArrayList();
          Object obj[] = null;
          for (int i = 0; i < temp_list.size(); i++) {
            try {
              obj = (Object[]) temp_list.get(i);
              if (obj[0] != null) {
                CollectionstatisticsExportDTO collectionstatisticsExportDTO = new CollectionstatisticsExportDTO();
                collectionstatisticsExportDTO.setOfficeCode(obj[0].toString());
                collectionstatisticsExportDTO.setCollectionBank(obj[1]
                    .toString());
                collectionstatisticsExportDTO.setOrgId(obj[2].toString());
                collectionstatisticsExportDTO.setOrgName(obj[3].toString());
                String orgCharacter = obj[4].toString();// 单位性质
                String orgCharacterName = BusiTools.getBusiValue(Integer
                    .parseInt("" + orgCharacter), BusiConst.NATUREOFUNITS);// 枚举转换单位性质
                collectionstatisticsExportDTO.setOrgCharacter(orgCharacterName);
                String deptId = obj[5].toString();// 主管部门
                String deptName = BusiTools.getBusiValue(Integer.parseInt(""
                    + deptId), BusiConst.AUTHORITIES);
                collectionstatisticsExportDTO.setDeptInCharge(deptName);
                String ragionId = obj[6].toString();// 所在地区
                String ragionName = BusiTools.getBusiValue(Integer.parseInt(""
                    + ragionId), BusiConst.INAREA);
                collectionstatisticsExportDTO.setRegion(ragionName);
                BigDecimal lastMonth = new BigDecimal(obj[7].toString());
                BigDecimal thisMonth = new BigDecimal(obj[8].toString());
                collectionstatisticsExportDTO.setLastMonthCollect(lastMonth
                    .toString());
                collectionstatisticsExportDTO.setThisMonthCollect(thisMonth
                    .toString());
                collectionstatisticsExportDTO.setMonthPay(obj[9].toString());
                collectionstatisticsExportDTO.setOrgAddPay(obj[10].toString());
                collectionstatisticsExportDTO.setPersonAddPay(obj[11]
                    .toString());
                collectionstatisticsExportDTO.setOrgOverPay(obj[12].toString());
                collectionstatisticsExportDTO.setChgPay(obj[13].toString());

                if (!lastMonth.toString().equals("0")) {
                  double rate = ((thisMonth.doubleValue() - lastMonth
                      .doubleValue()) / lastMonth.doubleValue()) * 100;
                  collectionstatisticsExportDTO.setRate(new Double(rate)
                      .toString().substring(0,
                          (new Double(rate).toString().indexOf(".")) + 2)
                      + "%");
                } else {
                  collectionstatisticsExportDTO.setRate("0%");
                }

                t.add(collectionstatisticsExportDTO);
              }

            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }

          }

          return t;
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /**
   * * 于庆丰 归集情况查询--根据条件查询分页
   * 
   * @param officeCod
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param orgCharacter
   * @param deptInCharge
   * @param startDate
   * @param endDate
   * @param region
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryByCollectionstatisticsCriterions_(final String officeCode,
      final String collectionBank, final String orgId, final String orgName,
      final String orgCharacter, final String deptInCharge,
      final String startDate, final String endDate, final String region,
      final int start, final int pageSize, final String orderBy,
      final String order, final SecurityInfo securityInfo) {
    List list = null;

    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          // String hql = " select orgHAFAccountFlow from OrgHAFAccountFlow
          // orgHAFAccountFlow ";
          // Vector parameters = new Vector();
          // String criterion = "";

          // System.out.println("=="+startDate);
          // System.out.println("=="+endDate);
          // String hql = " select orgHAFAccountFlow from OrgHAFAccountFlow
          // orgHAFAccountFlow ";
          String hql = "select distinct bb101.name as namel,"
              + "bb105.coll_bank_name,"
              + "aa001.id,"
              + "ba001.name,"
              + "ba001.character,"
              + "ba001.dept_in_charge,"
              + "ba001.region,"
              + " nvl((select sum(t.credit - t.debit) "
              + " from aa101 t "
              + " where (t.biz_type = 'A' or t.biz_type = 'B' or t.biz_type = 'C' or "
              + "t.biz_type = 'K' or t.biz_type = 'M') "
              + " and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate)) {
            hql = hql + " and t.sett_date <  " + startDate;
          }

          hql = hql
              + " and t.org_id = aa101.org_id),0), "
              +

              "nvl((select sum(t.credit - t.debit)"
              + " from aa101 t "
              + " where (t.biz_type = 'A' or t.biz_type = 'B' or t.biz_type = 'C' or "
              + " t.biz_type = 'K' or t.biz_type = 'M') "
              + " and t.biz_status = 5 ";
          if (endDate != null && !"".equals(endDate)) {
            hql = hql + " and t.sett_date <= " + endDate;
          }

          hql = hql + " and t.org_id = aa101.org_id),0), " +

          "nvl(( select sum(t.credit)" + " from aa101 t "
              + " where t.biz_type = 'A'" + "and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate) && endDate != null
              && !"".equals(endDate)) {
            hql = hql + " and t.sett_date between " + startDate + " and "
                + endDate;
          }

          hql = hql + " and t.org_id = aa101.org_id),0)," +

          "nvl((select sum(t.credit) " + " from aa101 t "
              + " where t.biz_type = 'M' " + " and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate) && endDate != null
              && !"".equals(endDate)) {
            hql = hql + " and t.sett_date between " + startDate + " and "
                + endDate;
          }
          hql = hql + " and t.org_id = aa101.org_id),0), " +

          " nvl((select sum(t.credit - t.debit)" + " from aa101 t "
              + " where t.biz_type = 'C' " + " and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate) && endDate != null
              && !"".equals(endDate)) {
            hql = hql + " and t.sett_date between " + startDate + " and "
                + endDate;
          }
          hql = hql + " and t.org_id = aa101.org_id),0)," +

          "nvl((select sum(t.credit) " + " from aa101 t "
              + " where t.biz_type = 'B' " + " and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate) && endDate != null
              && !"".equals(endDate)) {
            hql = hql + " and t.sett_date between " + startDate + " and "
                + endDate;
          }
          hql = hql + " and t.org_id = aa101.org_id),0)," +

          "nvl((select sum(t.credit - t.debit) " + " from aa101 t "
              + " where t.biz_type = 'K' " + " and t.biz_status = 5 ";
          if (startDate != null && !"".equals(startDate) && endDate != null
              && !"".equals(endDate)) {
            hql = hql + " and t.sett_date between " + startDate + " and "
                + endDate;
          }
          hql = hql + " and t.org_id = aa101.org_id),0)" +

          " from AA101 aa101, " + " BB101 bb101, " + " AA001 aa001, "
              + " BA001 ba001, " + " BB105 bb105 "
              + " where aa101.officeCode = bb101.id "
              + " and aa001.orginfo_id = ba001.id "
              + " and aa101.org_id = aa001.id "
              + " and bb105.coll_bank_id = aa101.moneyBank " + " and aa001.id "
              + securityInfo.getGjjSecuritySQL();
          if (officeCode != null && !"".equals(officeCode)) {
            hql = hql + " and aa101.officeCode = '" + officeCode + "'";
          }
          if (collectionBank != null && !"".equals(collectionBank)) {
            hql = hql + " and aa101.moneyBank = '" + collectionBank + "'";
          }
          if (orgId != null && !"".equals(orgId)) {
            hql = hql + " and aa001.id = '" + orgId + "'";
          }
          if (orgName != null && !"".equals(orgName)) {
            hql = hql + " and ba001.name = '" + orgName + "'";
          }
          if (orgCharacter != null && !"".equals(orgCharacter)) {
            hql = hql + " and ba001.character = '" + orgCharacter + "'";
          }
          if (deptInCharge != null && !"".equals(deptInCharge)) {
            hql = hql + " and ba001.dept_in_charge = '" + deptInCharge + "'";
          }
          if (region != null && !"".equals(region)) {
            hql = hql + " and ba001.region = '" + region + "'";
          }

          String ob = orderBy;
          if (ob == null)
            ob = " aa001.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          Query query = session.createSQLQuery(hql);
          List temp_list = query.list();
          List t = new ArrayList();
          Object obj[] = null;
          for (int i = 0; i < temp_list.size(); i++) {
            try {
              obj = (Object[]) temp_list.get(i);
              if (obj[0] != null) {
                CollectionstatisticsExportDTO collectionstatisticsExportDTO = new CollectionstatisticsExportDTO();
                collectionstatisticsExportDTO.setOfficeCode(obj[0].toString());
                collectionstatisticsExportDTO.setCollectionBank(obj[1]
                    .toString());
                collectionstatisticsExportDTO.setOrgId(obj[2].toString());
                collectionstatisticsExportDTO.setOrgName(obj[3].toString());
                String orgCharacter = obj[4].toString();// 单位性质
                String orgCharacterName = BusiTools.getBusiValue(Integer
                    .parseInt("" + orgCharacter), BusiConst.NATUREOFUNITS);// 枚举转换单位性质
                collectionstatisticsExportDTO.setOrgCharacter(orgCharacterName);
                String deptId = obj[5].toString();// 主管部门
                String deptName = BusiTools.getBusiValue(Integer.parseInt(""
                    + deptId), BusiConst.AUTHORITIES);
                collectionstatisticsExportDTO.setDeptInCharge(deptName);
                String ragionId = obj[6].toString();// 所在地区
                String ragionName = BusiTools.getBusiValue(Integer.parseInt(""
                    + ragionId), BusiConst.INAREA);
                collectionstatisticsExportDTO.setRegion(ragionName);
                collectionstatisticsExportDTO.setLastMonthCollect(obj[7]
                    .toString());
                collectionstatisticsExportDTO.setThisMonthCollect(obj[8]
                    .toString());
                collectionstatisticsExportDTO.setMonthPay(obj[9].toString());
                collectionstatisticsExportDTO.setOrgAddPay(obj[10].toString());
                collectionstatisticsExportDTO.setPersonAddPay(obj[11]
                    .toString());
                collectionstatisticsExportDTO.setOrgOverPay(obj[12].toString());
                collectionstatisticsExportDTO.setChgPay(obj[13].toString());
                BigDecimal lastMonth = new BigDecimal(obj[7].toString());
                BigDecimal thisMonth = new BigDecimal(obj[8].toString());
                if (!lastMonth.toString().equals("0")) {
                  double rate = ((thisMonth.doubleValue() - lastMonth
                      .doubleValue()) / lastMonth.doubleValue()) * 100;
                  collectionstatisticsExportDTO.setRate(new Double(rate)
                      .toString().substring(0,
                          (new Double(rate).toString().indexOf(".")) + 2)
                      + "%");
                } else {
                  collectionstatisticsExportDTO.setRate("0%");
                }

                t.add(collectionstatisticsExportDTO);
              }
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }

          }

          return t;
        }
      }

      );
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /**
   * 于庆丰 查询数量(分页)合计
   * 
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param orgCharacter
   * @param deptInCharge
   * @param startDate
   * @param endDate
   * @param region
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public CollectionstatisticsExportDTO queryCountByCollectionstatisticsCriterions(
      final String officeCode, final String collectionBank, final String orgId,
      final String orgName, final String orgCharacter,
      final String deptInCharge, final String startDate, final String endDate,
      final String region, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    CollectionstatisticsExportDTO collectionstatisticsExportDTO = null;

    try {
      collectionstatisticsExportDTO = (CollectionstatisticsExportDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              CollectionstatisticsExportDTO ce = new CollectionstatisticsExportDTO();

              // String hql = "select distinct "+
              // " sum(nvl((select sum(t.credit - t.debit) "+
              // " from aa101 t "+
              // " where (t.biz_type = 'A' or t.biz_type = 'B' or t.biz_type =
              // 'C' or "+
              // "t.biz_type = 'K' or t.biz_type = 'M') "+
              // " and t.biz_status = 5 ";
              // if(startDate!= null && !"".equals(startDate)){
              // hql = hql + " and t.sett_date < "+ startDate;
              // }
              //                 
              // hql = hql +" and t.org_id = aa101.org_id),0)), "+
              //              
              // " sum(nvl((select sum(t.credit - t.debit)"+
              // " from aa101 t "+
              // " where (t.biz_type = 'A' or t.biz_type = 'B' or t.biz_type =
              // 'C' or "+
              // " t.biz_type = 'K' or t.biz_type = 'M') "+
              // " and t.biz_status = 5 ";
              // if(endDate != null && !"".equals(endDate)){
              // hql = hql + " and t.sett_date <= "+endDate;
              // }
              //            
              // hql = hql + " and t.org_id = aa101.org_id),0)), "+
              //             
              // " sum(nvl(( select sum(t.credit)"+
              // " from aa101 t "+
              // " where t.biz_type = 'A'"+
              // "and t.biz_status = 5 ";
              // if(startDate != null && !"".equals(startDate) && endDate !=
              // null && !"".equals(endDate)){
              // hql = hql + " and t.sett_date between "+startDate+" and "+
              // endDate;
              // }
              //                
              // hql = hql + " and t.org_id = aa101.org_id),0)),"+
              //           
              // " sum(nvl((select sum(t.credit) "+
              // " from aa101 t "+
              // " where t.biz_type = 'M' "+
              // " and t.biz_status = 5 ";
              // if(startDate != null && !"".equals(startDate) && endDate !=
              // null && !"".equals(endDate)){
              // hql = hql + " and t.sett_date between "+startDate+" and "+
              // endDate;
              // }
              // hql = hql + " and t.org_id = aa101.org_id),0)), "+
              //              
              // " sum(nvl((select sum(t.credit - t.debit)"+
              // " from aa101 t "+
              // " where t.biz_type = 'C' "+
              // " and t.biz_status = 5 ";
              // if(startDate != null && !"".equals(startDate) && endDate !=
              // null && !"".equals(endDate)){
              // hql = hql + " and t.sett_date between "+startDate+" and "+
              // endDate;
              // }
              // hql = hql + " and t.org_id = aa101.org_id),0)),"+
              //              
              // " sum(nvl((select sum(t.credit) "+
              // " from aa101 t "+
              // " where t.biz_type = 'B' "+
              // " and t.biz_status = 5 ";
              // if(startDate != null && !"".equals(startDate) && endDate !=
              // null && !"".equals(endDate)){
              // hql = hql + " and t.sett_date between "+startDate+" and "+
              // endDate;
              // }
              // hql = hql + " and t.org_id = aa101.org_id),0)),"+
              //            
              // " sum(nvl((select sum(t.credit - t.debit) "+
              // " from aa101 t "+
              // " where t.biz_type = 'K' "+
              // " and t.biz_status = 5 ";
              // if(startDate != null && !"".equals(startDate) && endDate !=
              // null && !"".equals(endDate)){
              // hql = hql + " and t.sett_date between "+startDate+" and "+
              // endDate;
              // }
              // hql = hql + " and t.org_id = aa101.org_id),0))"+
              //            
              // " from AA101 aa101, "+
              // " BB101 bb101, "+
              // " AA001 aa001, "+
              // " BA001 ba001, "+
              // " BB105 bb105 "+
              // " where ba001.officecode = bb101.id "+
              // " and aa001.orginfo_id = ba001.id "+
              // " and aa101.org_id = aa001.id "+
              // " and bb105.coll_bank_id = ba001.collection_bank_id "+
              // " and aa001.id "+securityInfo.getGjjSecuritySQL();
              // if(officeCode!=null && !"".equals(officeCode)){
              // hql = hql + " and ba001.officecode = '"+officeCode+"'";
              // }
              // if(collectionBank != null && !"".equals(collectionBank)){
              // hql = hql + " and ba001.collection_bank_id =
              // '"+collectionBank+"'";
              // }
              // if(orgId != null && !"".equals(orgId)){
              // hql = hql + " and aa001.id = '"+orgId+"'";
              // }
              // if(orgName != null && !"".equals(orgName)){
              // hql = hql + " and ba001.name = '"+orgName+"'";
              // }
              // if(orgCharacter != null && !"".equals(orgCharacter)){
              // hql = hql + " and ba001.character = '"+orgCharacter+"'";
              // }
              // if(deptInCharge != null && !"".equals(deptInCharge)){
              // hql = hql + " and ba001.dept_in_charge = '"+deptInCharge+"'";
              // }
              // if(region != null && !"".equals(region)){
              // hql = hql + " and ba001.region = '"+region+"'";
              // }

              // *******************************************************************************************
              String hql = "select distinct "
                  +

                  " nvl((select sum(t.credit - t.debit) "
                  + " from aa101 t "
                  + " where (t.biz_type = 'A' or t.biz_type = 'B' or t.biz_type = 'C' or "
                  + "t.biz_type = 'K' or t.biz_type = 'M') "
                  + " and t.biz_status = 5 ";
              if (startDate != null && !"".equals(startDate)) {
                hql = hql + " and t.sett_date <  " + startDate;
              }

              hql = hql
                  + " and t.org_id = aa101.org_id),0), "
                  +

                  "nvl((select sum(t.credit - t.debit)"
                  + " from aa101 t "
                  + " where (t.biz_type = 'A' or t.biz_type = 'B' or t.biz_type = 'C' or "
                  + " t.biz_type = 'K' or t.biz_type = 'M') "
                  + " and t.biz_status = 5 ";
              if (endDate != null && !"".equals(endDate)) {
                hql = hql + " and t.sett_date <= " + endDate;
              }

              hql = hql + " and t.org_id = aa101.org_id),0), " +

              "nvl(( select sum(t.credit)" + " from aa101 t "
                  + " where t.biz_type = 'A'" + "and t.biz_status = 5 ";
              if (startDate != null && !"".equals(startDate) && endDate != null
                  && !"".equals(endDate)) {
                hql = hql + " and t.sett_date between " + startDate + " and "
                    + endDate;
              }

              hql = hql + " and t.org_id = aa101.org_id),0)," +

              "nvl((select sum(t.credit) " + " from aa101 t "
                  + " where t.biz_type = 'M' " + " and t.biz_status = 5 ";
              if (startDate != null && !"".equals(startDate) && endDate != null
                  && !"".equals(endDate)) {
                hql = hql + " and t.sett_date between " + startDate + " and "
                    + endDate;
              }
              hql = hql + " and t.org_id = aa101.org_id),0), " +

              " nvl((select sum(t.credit - t.debit)" + " from aa101 t "
                  + " where t.biz_type = 'C' " + " and t.biz_status = 5 ";
              if (startDate != null && !"".equals(startDate) && endDate != null
                  && !"".equals(endDate)) {
                hql = hql + " and t.sett_date between " + startDate + " and "
                    + endDate;
              }
              hql = hql + " and t.org_id = aa101.org_id),0)," +

              "nvl((select sum(t.credit) " + " from aa101 t "
                  + " where t.biz_type = 'B' " + " and t.biz_status = 5 ";
              if (startDate != null && !"".equals(startDate) && endDate != null
                  && !"".equals(endDate)) {
                hql = hql + " and t.sett_date between " + startDate + " and "
                    + endDate;
              }
              hql = hql + " and t.org_id = aa101.org_id),0)," +

              "nvl((select sum(t.credit - t.debit) " + " from aa101 t "
                  + " where t.biz_type = 'K' " + " and t.biz_status = 5 ";
              if (startDate != null && !"".equals(startDate) && endDate != null
                  && !"".equals(endDate)) {
                hql = hql + " and t.sett_date between " + startDate + " and "
                    + endDate;
              }
              hql = hql + " and t.org_id = aa101.org_id),0)" +

              " from AA101 aa101, " + " BB101 bb101, " + " AA001 aa001, "
                  + " BA001 ba001, " + " BB105 bb105 "
                  + " where aa101.officeCode = bb101.id "
                  + " and aa001.orginfo_id = ba001.id "
                  + " and aa101.org_id = aa001.id "
                  + " and bb105.coll_bank_id = aa101.moneyBank "
                  + " and aa001.id " + securityInfo.getGjjSecuritySQL();
              if (officeCode != null && !"".equals(officeCode)) {
                hql = hql + " and aa101.officeCode = '" + officeCode + "'";
              }
              if (collectionBank != null && !"".equals(collectionBank)) {
                hql = hql + " and aa101.moneyBank = '" + collectionBank + "'";
              }
              if (orgId != null && !"".equals(orgId)) {
                hql = hql + " and aa001.id = '" + orgId + "'";
              }
              if (orgName != null && !"".equals(orgName)) {
                hql = hql + " and ba001.name = '" + orgName + "'";
              }
              if (orgCharacter != null && !"".equals(orgCharacter)) {
                hql = hql + " and ba001.character = '" + orgCharacter + "'";
              }
              if (deptInCharge != null && !"".equals(deptInCharge)) {
                hql = hql + " and ba001.dept_in_charge = '" + deptInCharge
                    + "'";
              }
              if (region != null && !"".equals(region)) {
                hql = hql + " and ba001.region = '" + region + "'";
              }

              String ob = orderBy;
              if (ob == null)
                ob = " aa001.id ";

              String od = order;
              if (od == null)
                od = "DESC";

              Query query = session.createSQLQuery(hql);
              List temp_list = query.list();
              Object obj[] = null;
              BigDecimal lastmonth = new BigDecimal(0.00);
              BigDecimal thismonth = new BigDecimal(0.00);
              BigDecimal month = new BigDecimal(0.00);
              BigDecimal orgadd = new BigDecimal(0.00);
              BigDecimal personadd = new BigDecimal(0.00);
              BigDecimal orgover = new BigDecimal(0.00);
              BigDecimal chgpay = new BigDecimal(0.00);

              // BigDecimal lastMonth = new BigDecimal(0.00);
              // BigDecimal thisMonth = new BigDecimal(0.00);

              for (int i = 0; i < temp_list.size(); i++) {
                try {
                  obj = (Object[]) temp_list.get(i);

                  if (obj[0] != null) {

                    BigDecimal lastmonthh = new BigDecimal(obj[0].toString());
                    BigDecimal thismonthh = new BigDecimal(obj[1].toString());
                    BigDecimal monthh = new BigDecimal(obj[2].toString());
                    BigDecimal orgaddh = new BigDecimal(obj[3].toString());
                    BigDecimal personaddh = new BigDecimal(obj[4].toString());
                    BigDecimal orgoverh = new BigDecimal(obj[5].toString());
                    BigDecimal chgpayh = new BigDecimal(obj[6].toString());

                    // lastMonth = new BigDecimal(obj[0].toString());
                    // thisMonth = new BigDecimal(obj[1].toString());

                    lastmonth = lastmonth.add(lastmonthh);
                    thismonth = thismonth.add(thismonthh);
                    month = month.add(monthh);
                    orgadd = orgadd.add(orgaddh);
                    personadd = personadd.add(personaddh);
                    orgover = orgover.add(orgoverh);
                    chgpay = chgpay.add(chgpayh);

                    //       
                    // ce.setSumlastMonth(obj[0].toString());
                    // ce.setSumthisMonth(obj[1].toString());
                    // ce.setSummonthPay(obj[2].toString());
                    // ce.setSumorgAddPay(obj[3].toString());
                    // ce.setSumpersonAddPay(obj[4].toString());
                    // ce.setSumorgOverPay(obj[5].toString());
                    // ce.setSumChgPay(obj[6].toString());

                    // if(!lastMonth.toString().equals("0")){
                    // double rate = ((thisMonth.doubleValue() -
                    // lastMonth.doubleValue()) / lastMonth.doubleValue())*100;
                    // ce.setSumRate(new
                    // Double(rate).toString().substring(0,(new
                    // Double(rate).toString().indexOf("."))+2)+"%");
                    // }else{
                    // ce.setSumRate("0%");
                    // }
                  }

                  // t.add(collectionstatisticsExportDTO);

                } catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }

              }

              if (!lastmonth.toString().equals("0")) {
                double rate = ((thismonth.doubleValue() - lastmonth
                    .doubleValue()) / lastmonth.doubleValue()) * 100;
                ce.setSumRate(new Double(rate).toString().substring(0,
                    (new Double(rate).toString().indexOf(".")) + 2)
                    + "%");
              } else {
                ce.setSumRate("0%");
              }
              ce.setSumlastMonth(lastmonth.toString());
              ce.setSumthisMonth(thismonth.toString());
              ce.setSummonthPay(month.toString());
              ce.setSumorgAddPay(orgadd.toString());
              ce.setSumpersonAddPay(personadd.toString());
              ce.setSumorgOverPay(orgover.toString());
              ce.setSumChgPay(chgpay.toString());

              return ce;
            }
          }

          );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return collectionstatisticsExportDTO;
  }

  /**
   * 于庆丰 上月归集 根据日期 sum AA101 中 biz_type =a,b,c,k,m的credit-debit
   * 
   * @param lastsettDate
   * @return
   */
  public BigDecimal queryLastMonthCollection(final String settDate) {
    BigDecimal bd = new BigDecimal(0.00);
    try {
      bd = (BigDecimal) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // String sql = " select sum(sum(t.credit-t.debit)) from aa101 t,aa001
          // a where (t.biz_type='A' or t.biz_type='B' or t.biz_type='C' or
          // t.biz_type='K' or t.biz_type='M') and t.biz_status=5 and
          // t.sett_date < ? ";
          String sql = " select sum(sum(t.credit-t.debit)) from aa101 t where (t.biz_type='A' or t.biz_type='B' or t.biz_type='C' or t.biz_type='K' or t.biz_type='M') and t.biz_status=5 and t.sett_date < ? ";
          Vector parameters = new Vector();
          // parameters.add("%"+ settDate +"%");
          parameters.add(settDate);
          // sql = sql + "group by a.id";
          sql = sql + "group by t.org_id";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bd;
  }

  /**
   * 于庆丰 正常汇缴
   * 
   * @param lastsettDate
   * @return
   */
  public BigDecimal queryMonthPay(final String startDate, final String endDate) {
    BigDecimal monthPay = null;
    try {
      monthPay = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select sum(sum(t.credit)) from aa101 t,aa001 a where t.biz_type='A' and t.biz_status=5 and t.sett_date  between ?  and  ? ";
              Vector parameters = new Vector();
              parameters.add(startDate);
              parameters.add(endDate);
              sql = sql + "group by a.id";
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return monthPay;
  }

  /**
   * 于庆丰 单位补缴
   * 
   * @param lastsettDate
   * @return
   */
  public BigDecimal queryOrgAddPay(final String startDate, final String endDate) {
    BigDecimal monthPay = null;
    try {
      monthPay = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select sum(sum(t.credit)) from aa101 t,aa001 a where t.biz_type='M' and t.biz_status=5 and t.sett_date between ? and ? ";
              Vector parameters = new Vector();
              parameters.add(startDate);
              parameters.add(endDate);
              sql = sql + "group by a.id";
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return monthPay;
  }

  /**
   * 于庆丰 个人补缴
   * 
   * @param lastsettDate
   * @return
   */
  public BigDecimal queryPersonAddPay(final String startDate,
      final String endDate) {
    BigDecimal monthPay = null;
    try {
      monthPay = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select sum(sum(t.credit)) from aa101 t,aa001 a where t.biz_type='B' and t.biz_status=5  and t.sett_date between ? and ? ";
              Vector parameters = new Vector();
              parameters.add(startDate);
              parameters.add(endDate);
              sql = sql + "group by a.id";
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return monthPay;
  }

  /**
   * 于庆丰 单位挂账
   * 
   * @param lastsettDate
   * @return
   */
  public BigDecimal queryOrgOverPay(final String startDate, final String endDate) {
    BigDecimal monthPay = null;
    try {
      monthPay = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select sum(sum(t.credit-t.debit)) from aa101 t,aa001 a where t.biz_type='C' and t.biz_status=5 and t.sett_date between ? and ?  ";
              Vector parameters = new Vector();
              parameters.add(startDate);
              parameters.add(endDate);
              sql = sql + "group by a.id";
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return monthPay;
  }

  /**
   * 于庆丰 调缴存
   * 
   * @param lastsettDate
   * @return
   */
  public BigDecimal queryChgPay(final String startDate, final String endDate) {
    BigDecimal monthPay = null;
    try {
      monthPay = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select sum(sum(t.credit-t.debit)) from aa101 t,aa001 a where t.biz_type='K' and t.biz_status=5  and t.sett_date between ? and ? ";
              Vector parameters = new Vector();
              parameters.add(startDate);
              parameters.add(endDate);
              sql = sql + "group by a.id";
              Query query = session.createSQLQuery(sql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return monthPay;
  }

  /**
   * 王菱 查询单位业务流水
   * 
   * @param officecode
   * @param bankcode
   * @param orgid
   * @param orgname
   * @param noteNum
   * @param docNum
   * @param bsstatus
   * @param bstype
   * @param setmonthstart
   * @param setmonthend
   * @param setmoneystart
   * @param setmoneyend
   * @param setpeopcountstart
   * @param setpeopcountend
   * @param setdirection
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findOrgFlowListByCriterions_WL(final String officecode,
      final String bankcode, final String orgcode, final String orgname,
      final String noteNum, final String docNum, final String bsstatus,
      final String bstype, final String setmonthstart,
      final String setmonthend, final String setmoneystart,
      final String setmoneyend, final String setpeopcountstart,
      final String setpeopcountend, final String setdirection,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from OrgHAFAccountFlow orgHAFAccountFlow where orgHAFAccountFlow.bizStatus=5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (bankcode != null && !bankcode.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(bankcode);
          }
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ?  and ";
            parameters.add(new Integer(orgcode.trim()));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (noteNum != null && !noteNum.equals("")) {
            criterion += " orgHAFAccountFlow.noteNum like ?  and ";
            parameters.add("%" + noteNum + "%");
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += " orgHAFAccountFlow.docNum like ?  and ";
            parameters.add("%" + docNum + "%");
          }
          if (bsstatus != null && !bsstatus.equals("")) {
            criterion += " orgHAFAccountFlow.bizStatus = ?  and ";
            parameters.add(new BigDecimal(bsstatus));
          }
          if (bstype != null && !bstype.equals("")) {
            criterion += " orgHAFAccountFlow.biz_Type = ?  and ";
            parameters.add(bstype);
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && setmonthend != null && !setmonthend.equals("")) {
            criterion += "(orgHAFAccountFlow.settDate between ?  and  ? ) and ";
            parameters.add(setmonthstart);
            parameters.add(setmonthend);
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && (setmonthend == null || setmonthend.equals(""))) {
            criterion += "orgHAFAccountFlow.settDate = ? and ";
            parameters.add(setmonthstart);
          }

          if (setmonthend != null && !setmonthend.equals("")
              && (setmonthstart == null || setmonthstart.equals(""))) {
            criterion += "orgHAFAccountFlow.settDate = ? and ";
            parameters.add(setmonthend);
          }

          if (setmoneystart != null && !setmoneystart.equals("")
              && setmoneyend != null && !setmoneyend.equals("")) {
            criterion += "(orgHAFAccountFlow.moneyTotal between ?  and  ? ) and ";
            parameters.add(new BigDecimal(setmoneystart));
            parameters.add(new BigDecimal(setmoneyend));
          }

          if (setmoneystart != null && !setmoneystart.equals("")
              && (setmoneyend == null || setmoneyend.equals(""))) {
            criterion += "orgHAFAccountFlow.moneyTotal  = ? and ";
            parameters.add(new BigDecimal(setmoneystart));
          }

          if (setmoneyend != null && !setmoneyend.equals("")
              && (setmonthstart == null || setmonthstart.equals(""))) {
            criterion += "orgHAFAccountFlow.moneyTotal = ? and ";
            parameters.add(new BigDecimal(setmoneyend));
          }

          if (setpeopcountstart != null && !setpeopcountstart.equals("")
              && setpeopcountend != null && !setpeopcountend.equals("")) {
            criterion += "(orgHAFAccountFlow.personTotal between ?  and  ? ) and ";
            parameters.add(new Integer(setpeopcountstart));
            parameters.add(new Integer(setpeopcountend));
          }

          if (setpeopcountstart != null && !setpeopcountstart.equals("")
              && (setpeopcountend == null || setpeopcountend.equals(""))) {
            criterion += "orgHAFAccountFlow.personTotal  = ? and ";
            parameters.add(new Integer(setpeopcountstart));
          }

          if (setpeopcountend != null && !setpeopcountend.equals("")
              && (setpeopcountstart == null || setpeopcountstart.equals(""))) {
            criterion += "orgHAFAccountFlow.personTotal = ? and ";
            parameters.add(new Integer(setpeopcountend));
          }

          if (setdirection != null && !setdirection.equals("")) {
            if (setdirection.equals("1")) {
              criterion += " orgHAFAccountFlow.debit_wl > 0 and ";
            } else if (setdirection.equals("2")) {
              criterion += "  orgHAFAccountFlow.credit_wl > 0  and ";
            } else {
              criterion += "  orgHAFAccountFlow.credit_wl > 0  and orgHAFAccountFlow.debit_wl > 0 and ";
            }
          }
          String ob = orderBy;
          if (ob == null)
            ob = " orgHAFAccountFlow.id ";

          String od = order;
          if (od == null)
            od = "DESC";
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + od;
          session.clear();
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
          return queryList;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /**
   * 王菱 查询单位业务流水条数
   * 
   * @param officecode
   * @param bankcode
   * @param orgid
   * @param orgname
   * @param noteNum
   * @param docNum
   * @param bsstatus
   * @param bstype
   * @param setmonthstart
   * @param setmonthend
   * @param setmoneystart
   * @param setmoneyend
   * @param setpeopcountstart
   * @param setpeopcountend
   * @param setdirection
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public int findOrgFlowListCountByCriterions_WL(final String officecode,
      final String bankcode, final String orgcode, final String orgname,
      final String noteNum, final String docNum, final String bsstatus,
      final String bstype, final String setmonthstart,
      final String setmonthend, final String setmoneystart,
      final String setmoneyend, final String setpeopcountstart,
      final String setpeopcountend, final String setdirection,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    Integer count = new Integer(0);
    try {

      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select count(orgHAFAccountFlow.id) from OrgHAFAccountFlow orgHAFAccountFlow where orgHAFAccountFlow.bizStatus=5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (bankcode != null && !bankcode.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(bankcode);
          }
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ?  and ";
            parameters.add(new Integer(orgcode.trim()));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (noteNum != null && !noteNum.equals("")) {
            criterion += " orgHAFAccountFlow.noteNum like ?  and ";
            parameters.add("%" + noteNum + "%");
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += " orgHAFAccountFlow.docNum like ?  and ";
            parameters.add("%" + docNum + "%");
          }
          if (bsstatus != null && !bsstatus.equals("")) {
            criterion += " orgHAFAccountFlow.bizStatus = ?  and ";
            parameters.add(new BigDecimal(bsstatus));
          }
          if (bstype != null && !bstype.equals("")) {
            criterion += " orgHAFAccountFlow.biz_Type = ?  and ";
            parameters.add(bstype);
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && setmonthend != null && !setmonthend.equals("")) {
            criterion += "(orgHAFAccountFlow.settDate between ?  and  ? ) and ";
            parameters.add(setmonthstart);
            parameters.add(setmonthend);
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && (setmonthend == null || setmonthend.equals(""))) {
            criterion += "orgHAFAccountFlow.settDate = ? and ";
            parameters.add(setmonthstart);
          }

          if (setmonthend != null && !setmonthend.equals("")
              && (setmonthstart == null || setmonthstart.equals(""))) {
            criterion += "orgHAFAccountFlow.settDate = ? and ";
            parameters.add(setmonthend);
          }

          if (setmoneystart != null && !setmoneystart.equals("")
              && setmoneyend != null && !setmoneyend.equals("")) {
            criterion += "(orgHAFAccountFlow.moneyTotal between ?  and  ? ) and ";
            parameters.add(new BigDecimal(setmoneystart));
            parameters.add(new BigDecimal(setmoneyend));
          }

          if (setmoneystart != null && !setmoneystart.equals("")
              && (setmoneyend == null || setmoneyend.equals(""))) {
            criterion += "orgHAFAccountFlow.moneyTotal  = ? and ";
            parameters.add(new BigDecimal(setmoneystart));
          }

          if (setmoneyend != null && !setmoneyend.equals("")
              && (setmonthstart == null || setmonthstart.equals(""))) {
            criterion += "orgHAFAccountFlow.moneyTotal = ? and ";
            parameters.add(new BigDecimal(setmoneyend));
          }

          if (setpeopcountstart != null && !setpeopcountstart.equals("")
              && setpeopcountend != null && !setpeopcountend.equals("")) {
            criterion += "(orgHAFAccountFlow.personTotal between ?  and  ? ) and ";
            parameters.add(new Integer(setpeopcountstart));
            parameters.add(new Integer(setpeopcountend));
          }

          if (setpeopcountstart != null && !setpeopcountstart.equals("")
              && (setpeopcountend == null || setpeopcountend.equals(""))) {
            criterion += "orgHAFAccountFlow.personTotal  = ? and ";
            parameters.add(new Integer(setpeopcountstart));
          }

          if (setpeopcountend != null && !setpeopcountend.equals("")
              && (setpeopcountstart == null || setpeopcountstart.equals(""))) {
            criterion += "orgHAFAccountFlow.personTotal = ? and ";
            parameters.add(new Integer(setpeopcountend));
          }

          if (setdirection != null && !setdirection.equals("")) {
            if (setdirection.equals("1")) {
              criterion += " orgHAFAccountFlow.debit_wl > 0 and ";
            } else if (setdirection.equals("2")) {
              criterion += "  orgHAFAccountFlow.credit_wl > 0  and ";
            } else {
              criterion += "  orgHAFAccountFlow.credit_wl > 0  and orgHAFAccountFlow.debit_wl > 0 and ";
            }
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return Integer.valueOf(query.uniqueResult().toString());
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }

  /**
   * 王菱 查询单位业务流水全部列表
   * 
   * @param officecode
   * @param bankcode
   * @param orgid
   * @param orgname
   * @param noteNum
   * @param docNum
   * @param bsstatus
   * @param bstype
   * @param setmonthstart
   * @param setmonthend
   * @param setmoneystart
   * @param setmoneyend
   * @param setpeopcountstart
   * @param setpeopcountend
   * @param setdirection
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findOrgFlowAllListByCriterions_WL(final String officecode,
      final String bankcode, final String orgcode, final String orgname,
      final String noteNum, final String docNum, final String bsstatus,
      final String bstype, final String setmonthstart,
      final String setmonthend, final String setmoneystart,
      final String setmoneyend, final String setpeopcountstart,
      final String setpeopcountend, final String setdirection,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from OrgHAFAccountFlow orgHAFAccountFlow where  orgHAFAccountFlow.bizStatus=5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.officecode = ?  and ";
            parameters.add(officecode);
          }
          if (bankcode != null && !bankcode.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(bankcode);
          }
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ?  and ";
            parameters.add(new Integer(orgcode.trim()));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (noteNum != null && !noteNum.equals("")) {
            criterion += " orgHAFAccountFlow.noteNum like ?  and ";
            parameters.add("%" + noteNum + "%");
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += " orgHAFAccountFlow.docNum like ?  and ";
            parameters.add("%" + docNum + "%");
          }
          if (bsstatus != null && !bsstatus.equals("")) {
            criterion += " orgHAFAccountFlow.bizStatus = ?  and ";
            parameters.add(new BigDecimal(bsstatus));
          }
          if (bstype != null && !bstype.equals("")) {
            criterion += " orgHAFAccountFlow.biz_Type = ?  and ";
            parameters.add(bstype);
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && setmonthend != null && !setmonthend.equals("")) {
            criterion += "(orgHAFAccountFlow.settDate between ?  and  ? ) and ";
            parameters.add(setmonthstart);
            parameters.add(setmonthend);
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && (setmonthend == null || setmonthend.equals(""))) {
            criterion += "orgHAFAccountFlow.settDate = ? and ";
            parameters.add(setmonthstart);
          }

          if (setmonthend != null && !setmonthend.equals("")
              && (setmonthstart == null || setmonthstart.equals(""))) {
            criterion += "orgHAFAccountFlow.settDate = ? and ";
            parameters.add(setmonthend);
          }

          if (setmoneystart != null && !setmoneystart.equals("")
              && setmoneyend != null && !setmoneyend.equals("")) {
            criterion += "(orgHAFAccountFlow.moneyTotal between ?  and  ? ) and ";
            parameters.add(new BigDecimal(setmoneystart));
            parameters.add(new BigDecimal(setmoneyend));
          }

          if (setmoneystart != null && !setmoneystart.equals("")
              && (setmoneyend == null || setmoneyend.equals(""))) {
            criterion += "orgHAFAccountFlow.moneyTotal  = ? and ";
            parameters.add(new BigDecimal(setmoneystart));
          }

          if (setmoneyend != null && !setmoneyend.equals("")
              && (setmonthstart == null || setmonthstart.equals(""))) {
            criterion += "orgHAFAccountFlow.moneyTotal = ? and ";
            parameters.add(new BigDecimal(setmoneyend));
          }

          if (setpeopcountstart != null && !setpeopcountstart.equals("")
              && setpeopcountend != null && !setpeopcountend.equals("")) {
            criterion += "(orgHAFAccountFlow.personTotal between ?  and  ? ) and ";
            parameters.add(new Integer(setpeopcountstart));
            parameters.add(new Integer(setpeopcountend));
          }

          if (setpeopcountstart != null && !setpeopcountstart.equals("")
              && (setpeopcountend == null || setpeopcountend.equals(""))) {
            criterion += "orgHAFAccountFlow.personTotal  = ? and ";
            parameters.add(new Integer(setpeopcountstart));
          }

          if (setpeopcountend != null && !setpeopcountend.equals("")
              && (setpeopcountstart == null || setpeopcountstart.equals(""))) {
            criterion += "orgHAFAccountFlow.personTotal = ? and ";
            parameters.add(new Integer(setpeopcountend));
          }

          if (setdirection != null && !setdirection.equals("")) {
            if (setdirection.equals("1")) {
              criterion += " orgHAFAccountFlow.debit_wl > 0 and ";
            } else if (setdirection.equals("2")) {
              criterion += "  orgHAFAccountFlow.credit_wl > 0  and ";
            } else {
              criterion += "  orgHAFAccountFlow.credit_wl > 0  and orgHAFAccountFlow.debit_wl > 0 and ";
            }
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by to_number(orgHAFAccountFlow.id) ";

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

  // 查看是否有为完成的业务
  public List queryorgHAFAccountFlow_sy() throws NumberFormatException,
      Exception {
    List list = null;
    list = getHibernateTemplate().executeFind(

    new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "  from OrgHAFAccountFlow orgHAFAccountFlow where orgHAFAccountFlow.bizStatus='3' or orgHAFAccountFlow.bizStatus= '4'";
        Query query = session.createQuery(hql);
        return query.list();
      }
    });
    return list;
  }

  // 判断是否有提取、转出、转入、错帐调整录入清册的业务，如果有，不允许利率启用。
  public String queryBizstatus_WL() throws NumberFormatException, Exception {
    String flag = "";
    flag = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql0 = "select count(t.id) from aa001 t, aa306 a306 where t.id=a306.org_id and a306.pick_satatus in('1','2')";
        Query query0 = session.createSQLQuery(hql0);
        if (query0.uniqueResult().toString().equals("0")) {
          String hql1 = "select count(t.id) from aa001 t,aa309 a309 where t.id=a309.out_org_id and a309.tran_status in('1','2')";
          Query query1 = session.createSQLQuery(hql1);
          if (query1.uniqueResult().toString().equals("0")) {
            String hql2 = "select count(t.id) from aa001 t,aa311 a311 where t.id=a311.in_org_id and a311.tran_status in('1','2')";
            Query query2 = session.createSQLQuery(hql2);
            if (query2.uniqueResult().toString().equals("0")) {
              String hql3 = "select count(t.id) from aa001 t,aa314 a314 where t.id=a314.org_id and a314.adjust_status in('1','2')";
              Query query3 = session.createSQLQuery(hql3);
              if (query3.uniqueResult().toString().equals("0")) {
                return "";
              } else {
                return "G";
              }
            } else {
              return "F";
            }
          } else {
            return "E";
          }
        } else {
          return "D";
        }
      }
    });
    return flag;
  }

  // 判断是否有提取、转出、转入、错帐调整录入清册的业务
  public String queryBizstatus1_WL(final String orgid)
      throws NumberFormatException, Exception {
    String flag = "";
    flag = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql0 = "select count(t.id) from aa001 t, aa306 a306 where t.id=a306.org_id and a306.pick_satatus in('1','2') and t.id= ? ";
        Query query0 = session.createSQLQuery(hql0);
        if (orgid != null) {
          query0.setInteger(0, Integer.parseInt(orgid));
        }
        if (query0.uniqueResult().toString().equals("0")) {
          String hql1 = "select count(t.id) from aa001 t,aa309 a309 where t.id=a309.out_org_id and a309.tran_status in('1','2') and t.id= ? ";
          Query query1 = session.createSQLQuery(hql1);
          if (orgid != null) {
            query1.setInteger(0, Integer.parseInt(orgid));
          }
          if (query1.uniqueResult().toString().equals("0")) {
            String hql2 = "select count(t.id) from aa001 t,aa311 a311 where t.id=a311.in_org_id and a311.tran_status in('1','2') and t.id= ? ";
            Query query2 = session.createSQLQuery(hql2);
            if (orgid != null) {
              query2.setInteger(0, Integer.parseInt(orgid));
            }
            if (query2.uniqueResult().toString().equals("0")) {
              String hql3 = "select count(t.id) from aa001 t,aa314 a314 where t.id=a314.org_id and a314.adjust_status in('1','2') and t.id= ? ";
              Query query3 = session.createSQLQuery(hql3);
              if (orgid != null) {
                query3.setInteger(0, Integer.parseInt(orgid));
              }
              if (query3.uniqueResult().toString().equals("0")) {
                return "";
              } else {
                return "G";
              }
            } else {
              return "F";
            }
          } else {
            return "E";
          }
        } else {
          return "D";
        }
      }
    });
    return flag;
  }

  /**
   * 汇缴
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceMonthPay(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(orgHAFAccountFlowPayment.id),nvl(sum(orgHAFAccountFlowPayment.credit),0) "
              + " from  OrgHAFAccountFlowPayment orgHAFAccountFlowPayment "
              + " where orgHAFAccountFlowPayment.bizStatus = 5 and "
              + " orgHAFAccountFlowPayment.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlowPayment.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowPayment.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowPayment.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowPayment.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowPayment.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowPayment.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowPayment.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowPayment.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setOrg_payment_count(new Integer(obj[0]
                .toString()));
            clearAccountBalanceDTOs.setOrg_payment_balance(new BigDecimal(
                obj[1].toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 单位补缴
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceOrgAddPay(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(orgHAFAccountFlowOrgAddPay.id),nvl(sum(orgHAFAccountFlowOrgAddPay.credit),0) "
              + " from  OrgHAFAccountFlowOrgAddPay orgHAFAccountFlowOrgAddPay "
              + " where orgHAFAccountFlowOrgAddPay.bizStatus = 5 and "
              + " orgHAFAccountFlowOrgAddPay.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlowOrgAddPay.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowOrgAddPay.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowOrgAddPay.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowOrgAddPay.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowOrgAddPay.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowOrgAddPay.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowOrgAddPay.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowOrgAddPay.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setOrg_add_payment_count(new Integer(obj[0]
                .toString()));
            clearAccountBalanceDTOs.setOrg_add_payment_balance(new BigDecimal(
                obj[1].toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            // System.out.println(clearAccountBalanceDTOs.getOrg_add_payment_balance()+"------------"+clearAccountBalanceDTOs.getOrg_add_payment_count());
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 个人补缴
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceEmpAddPay(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(orgHAFAccountFlowOverduePayment.id),nvl(sum(orgHAFAccountFlowOverduePayment.credit),0) "
              + " from  OrgHAFAccountFlowOverduePayment orgHAFAccountFlowOverduePayment "
              + " where orgHAFAccountFlowOverduePayment.bizStatus = 5 and "
              + " orgHAFAccountFlowOverduePayment.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlowOverduePayment.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowOverduePayment.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowOverduePayment.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowOverduePayment.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowOverduePayment.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowOverduePayment.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowOverduePayment.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowOverduePayment.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setEmp_add_payment_count(new Integer(obj[0]
                .toString()));
            clearAccountBalanceDTOs.setEmp_add_payment_balance(new BigDecimal(
                obj[1].toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 单位挂账
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceExcessPayment(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(orgHAFAccountFlowExcessPayment.id),nvl(sum(orgHAFAccountFlowExcessPayment.credit)-sum(orgHAFAccountFlowExcessPayment.debit),0) "
              + " from  OrgHAFAccountFlowExcessPayment orgHAFAccountFlowExcessPayment "
              + " where orgHAFAccountFlowExcessPayment.bizStatus = 5 and "
              + " orgHAFAccountFlowExcessPayment.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlowExcessPayment.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowExcessPayment.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowExcessPayment.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowExcessPayment.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowExcessPayment.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowExcessPayment.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowExcessPayment.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowExcessPayment.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setOrg_over_pay_count(new Integer(obj[0]
                .toString()));
            clearAccountBalanceDTOs.setOrg_over_paybalance(new BigDecimal(
                obj[1].toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  public List queryOrgHAFAccountFlowBalanceExcessPayment_wsh(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(orgHAFAccountFlowExcessPayment.id),nvl(sum(orgHAFAccountFlowExcessPayment.credit)-sum(orgHAFAccountFlowExcessPayment.debit),0) "
              + " from  OrgHAFAccountFlowExcessPayment orgHAFAccountFlowExcessPayment "
              + " where orgHAFAccountFlowExcessPayment.bizStatus = 5 and "
              + " orgHAFAccountFlowExcessPayment.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlowExcessPayment.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowExcessPayment.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowExcessPayment.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowExcessPayment.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowExcessPayment.settDate <= ?  and ";
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowExcessPayment.settDate <= ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowExcessPayment.settDate <= ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowExcessPayment.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setOrg_over_pay_count(new Integer(obj[0]
                .toString()));
            clearAccountBalanceDTOs.setOrg_over_paybalance(new BigDecimal(
                obj[1].toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 单位转入
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceTransIn(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(orgHAFAccountFlowTransIn.personTotal),0),nvl(sum(orgHAFAccountFlowTransIn.credit),0) "
              + " from OrgHAFAccountFlowTransIn orgHAFAccountFlowTransIn "
              + " where orgHAFAccountFlowTransIn.bizStatus = 5 "
              + " and orgHAFAccountFlowTransIn.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowTransIn.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowTransIn.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowTransIn.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowTransIn.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowTransIn.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowTransIn.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowTransIn.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setOrg_tranin_count(new Integer(obj[0]
                .toString()));
            clearAccountBalanceDTOs.setOrg_tranin_paybalance(new BigDecimal(
                obj[1].toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 错账调整--调缴存贷方
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceAdjustaccountPay(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct orgHAFAccountFlowAdjustPayment.id),"
              + " nvl(sum(empHAFAccountFlow.credit),0) "
              + " from  OrgHAFAccountFlowAdjustPayment orgHAFAccountFlowAdjustPayment,EmpHAFAccountFlow empHAFAccountFlow  "
              + " where empHAFAccountFlow.orgHAFAccountFlow.id=orgHAFAccountFlowAdjustPayment.id and orgHAFAccountFlowAdjustPayment.bizStatus = 5 "
              + " and orgHAFAccountFlowAdjustPayment.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowAdjustPayment.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowAdjustPayment.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowAdjustPayment.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowAdjustPayment.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowAdjustPayment.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowAdjustPayment.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowAdjustPayment.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs
                .setAdjustaccoutPayment_credit_count(new Integer(obj[0]
                    .toString()));
            clearAccountBalanceDTOs
                .setAdjustaccoutPayment_credit_paybalance(new BigDecimal(obj[1]
                    .toString()).divide(new BigDecimal(1), 2,
                    BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 错账调整--调提取贷方
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceAdjustaccountPick(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct orgHAFAccountFlowAdjustPick.id),"
              + " nvl(sum(empHAFAccountFlow.credit),0) "
              + " from  OrgHAFAccountFlowAdjustPick orgHAFAccountFlowAdjustPick,EmpHAFAccountFlow empHAFAccountFlow "
              + " where empHAFAccountFlow.orgHAFAccountFlow.id=orgHAFAccountFlowAdjustPick.id and orgHAFAccountFlowAdjustPick.bizStatus = 5 "
              + " and orgHAFAccountFlowAdjustPick.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowAdjustPick.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowAdjustPick.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowAdjustPick.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowAdjustPick.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowAdjustPick.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowAdjustPick.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowAdjustPick.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs
                .setAdjustaccoutPick_credit_count(new Integer(obj[0].toString()));
            clearAccountBalanceDTOs
                .setAdjustaccoutPick_credit_paybalance(new BigDecimal(obj[1]
                    .toString()).divide(new BigDecimal(1), 2,
                    BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 错账调整--调其他贷方
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceAdjustaccountOther(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct orgHAFAccountFlowAdjustOther.id),"
              + " nvl(sum(empHAFAccountFlow.credit),0) "
              + " from  OrgHAFAccountFlowAdjustOther orgHAFAccountFlowAdjustOther,EmpHAFAccountFlow empHAFAccountFlow  "
              + " where empHAFAccountFlow.orgHAFAccountFlow.id=orgHAFAccountFlowAdjustOther.id and orgHAFAccountFlowAdjustOther.bizStatus = 5 "
              + " and orgHAFAccountFlowAdjustOther.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowAdjustOther.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowAdjustOther.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowAdjustOther.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowAdjustOther.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowAdjustOther.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowAdjustOther.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowAdjustOther.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs
                .setAdjustaccoutOther_credit_count(new Integer(obj[0]
                    .toString()));
            clearAccountBalanceDTOs
                .setAdjustaccoutOthert_credit_paybalance(new BigDecimal(obj[1]
                    .toString()).divide(new BigDecimal(1), 2,
                    BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 结息
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceEndAccrual(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(orgHAFAccountFlowEndAccrual.id),nvl(sum(orgHAFAccountFlowEndAccrual.reserveaC),0),nvl(sum(orgHAFAccountFlowEndAccrual.credit-orgHAFAccountFlowEndAccrual.reserveaC),0) "
              + " from  OrgHAFAccountFlowEndAccrual orgHAFAccountFlowEndAccrual "
              + " where orgHAFAccountFlowEndAccrual.bizStatus = 5 and "
              + " orgHAFAccountFlowEndAccrual.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlowEndAccrual.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowEndAccrual.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowEndAccrual.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowEndAccrual.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowEndAccrual.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowEndAccrual.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowEndAccrual.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowEndAccrual.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setClearinteres_count(new Integer(obj[0]
                .toString()));
            clearAccountBalanceDTOs.setClearinteres_paybalance(new BigDecimal(
                obj[1].toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            clearAccountBalanceDTOs
                .setClearinteres_paybalance_1(new BigDecimal(obj[2].toString())
                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 借方
   */
  /**
   * 单位转出
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceTransOut(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(orgHAFAccountFlowTransOut.personTotal),0),nvl(sum(orgHAFAccountFlowTransOut.debit),0) "
              + " from  OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut"
              + " where orgHAFAccountFlowTransOut.bizStatus = 5 and "
              + " orgHAFAccountFlowTransOut.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowTransOut.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowTransOut.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowTransOut.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowTransOut.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowTransOut.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowTransOut.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowTransOut.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setOrg_tranout_count(new Integer(obj[0]
                .toString()));
            clearAccountBalanceDTOs.setOrg_tranout_balance(new BigDecimal(
                obj[1].toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 提取
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception pickType
   */
  public List queryOrgHAFAccountFlowBalancePick(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(count(pickTail.id),0),nvl(sum(pickTail.pickPreBalance+pickTail.pickCurBalance),0) "
              + " from  OrgHAFAccountFlowDrawing orgHAFAccountFlowDrawing,PickTail pickTail "
              + " where pickTail.pickHead.id=orgHAFAccountFlowDrawing.bizId and pickTail.pickType='1' and pickTail.pickReason not in('2','3') and orgHAFAccountFlowDrawing.bizStatus = 5 and "
              + " orgHAFAccountFlowDrawing.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowDrawing.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowDrawing.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs
                .setPick_count(new Integer(obj[0].toString()));
            clearAccountBalanceDTOs.setPick_balance(new BigDecimal(obj[1]
                .toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 提取
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception pickType
   */
  public List queryOrgHAFAccountFlowBalancePick_xiaohu(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(count(pickTail.id),0),nvl(sum(pickTail.pickPreBalance+pickTail.pickCurBalance),0) "
              + " from  OrgHAFAccountFlowDrawing orgHAFAccountFlowDrawing,PickTail pickTail "
              + " where pickTail.pickHead.id=orgHAFAccountFlowDrawing.bizId and pickTail.pickType='2' and  orgHAFAccountFlowDrawing.bizStatus = 5 and "
              + " orgHAFAccountFlowDrawing.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowDrawing.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowDrawing.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs
                .setPick_count(new Integer(obj[0].toString()));
            clearAccountBalanceDTOs.setPick_balance(new BigDecimal(obj[1]
                .toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 提取--还贷（公积金按月还贷）
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalancePickCredit(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo, final Integer pickReason)
      throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // String hql = "select
          // count(orgHAFAccountFlowDrawing.personTotal),nvl(sum(orgHAFAccountFlowDrawing.debit),0)
          // " +
          // " from OrgHAFAccountFlowDrawing orgHAFAccountFlowDrawing,PickTail
          // pickTail " +
          // " where pickTail.pickHead.id=orgHAFAccountFlowDrawing.bizId " +
          // " and orgHAFAccountFlowDrawing.bizStatus = 5 and " +
          // " orgHAFAccountFlowDrawing.org.id
          // "+securityInfo.getGjjSecurityHqlSQL();
          String hql = "select count(pickTail.id),nvl(sum(pickTail.pickPreBalance+pickTail.pickCurBalance),0) "
              + " from  OrgHAFAccountFlowDrawing orgHAFAccountFlowDrawing,PickTail pickTail "
              + " where pickTail.pickHead.id=orgHAFAccountFlowDrawing.bizId "
              + " and orgHAFAccountFlowDrawing.bizStatus = 5 and "
              + " orgHAFAccountFlowDrawing.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowDrawing.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowDrawing.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (pickReason != null && !pickReason.equals("")) {
            criterion += " pickTail.pickReason = ?  and ";
            parameters.add(new BigDecimal(pickReason.toString()));
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setPick_payload_count(new Integer(obj[0]
                .toString()));
            clearAccountBalanceDTOs.setPick_payload_balance(new BigDecimal(
                obj[1].toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 提取--还贷(公积金一次性还贷款)
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception ld修改
   */
  public List queryOrgHAFAccountFlowBalancePickCredit_LD(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo, final Integer pickReason)
      throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // String hql = "select
          // count(orgHAFAccountFlowDrawing.personTotal),nvl(sum(orgHAFAccountFlowDrawing.debit),0)
          // " +
          // " from OrgHAFAccountFlowDrawing orgHAFAccountFlowDrawing,PickTail
          // pickTail " +
          // " where pickTail.pickHead.id=orgHAFAccountFlowDrawing.bizId " +
          // " and orgHAFAccountFlowDrawing.bizStatus = 5 and " +
          // " orgHAFAccountFlowDrawing.org.id
          // "+securityInfo.getGjjSecurityHqlSQL();
          String hql = "select count(pickTail.id),nvl(sum(pickTail.pickPreBalance+pickTail.pickCurBalance),0) "
              + " from  OrgHAFAccountFlowDrawing orgHAFAccountFlowDrawing,PickTail pickTail "
              + " where pickTail.pickHead.id=orgHAFAccountFlowDrawing.bizId "
              + " and orgHAFAccountFlowDrawing.bizStatus = 5 and "
              + " orgHAFAccountFlowDrawing.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowDrawing.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowDrawing.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (pickReason != null && !pickReason.equals("")) {
            criterion += " pickTail.pickReason = ?  and ";
            parameters.add(new BigDecimal(pickReason.toString()));
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setPick_payload_count_ld(new Integer(obj[0]
                .toString()));
            clearAccountBalanceDTOs.setPick_payload_balance_ld(new BigDecimal(
                obj[1].toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  // /**
  // * 提取--其他
  // * @param orgid
  // * @param orgname
  // * @param collBankId
  // * @param startday
  // * @param untilday
  // * @param operator
  // * @param bizDate
  // * @param securityInfo
  // * @return
  // * @throws NumberFormatException
  // * @throws Exception
  // */
  // public List queryOrgHAFAccountFlowBalancePickOther(final Serializable
  // orgid,final String orgname,
  // final String collBankId,final String startday,final String untilday,final
  // String operator,
  // final SecurityInfo securityInfo) throws NumberFormatException, Exception {
  // List tableList = new ArrayList();
  // // final String bizDate = securityInfo.getUserInfo().getBizDate();
  // try {
  // tableList =getHibernateTemplate().executeFind(
  // new HibernateCallback() {
  // public Object doInHibernate(Session session)
  // throws HibernateException, SQLException {
  // String hql = "select
  // count(empHAFAccountFlow.id),nvl(sum(empHAFAccountFlow.debit),0) " +
  // " from EmpHAFAccountFlow empHAFAccountFlow, PickTail pickTail" +
  // " where pickTail.pickHead.id=empHAFAccountFlow.orgHAFAccountFlow.bizId "+
  // " and empHAFAccountFlow.orgHAFAccountFlow.bizStatus = 5 and " +
  // " empHAFAccountFlow.orgHAFAccountFlow.biz_Type='D' and
  // empHAFAccountFlow.orgHAFAccountFlow.org.id
  // "+securityInfo.getGjjSecurityHqlSQL();
  // Vector parameters = new Vector();
  // String criterion = "";
  //              
  // // if (bizDate != null && !bizDate.equals("")) {
  // // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and ";
  // // parameters.add(bizDate);
  // // }
  // if (orgid != null && !orgid.equals("")) {
  // criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.id = ? and ";
  // parameters.add(orgid);
  // }
  // if (orgname != null && !orgname.equals("")) {
  // criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name = ? and
  // ";
  // parameters.add(orgname);
  // }
  // if (collBankId != null && !collBankId.equals("")) {
  // criterion += "
  // empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.collectionBankId = ? and ";
  // parameters.add(collBankId);
  // }
  // if(startday != null && !startday.equals("") && untilday != null &&
  // !untilday.equals("")){
  // criterion += " (empHAFAccountFlow.orgHAFAccountFlow.settDate between ? and
  // ? ) and ";
  // parameters.add(startday);
  // parameters.add(untilday);
  // }else if (startday != null && !startday.equals("")) {
  // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and ";
  // parameters.add(startday);
  // }else if (untilday != null && !untilday.equals("")) {
  // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and ";
  // parameters.add(untilday);
  // }
  // if (operator != null && !operator.equals("")) {
  // criterion += " empHAFAccountFlow.orgHAFAccountFlow.reserveaA = ? and ";
  // parameters.add(operator);
  // }
  // if (criterion.length() != 0)
  // criterion = " and "
  // + criterion.substring(0, criterion.lastIndexOf("and"));
  // hql = hql + criterion;
  //
  // Query query = session.createQuery(hql);
  // for (int i = 0; i < parameters.size(); i++) {
  // query.setParameter(i, parameters.get(i));
  // }
  // Iterator iterate = query.list().iterator();
  //              
  // ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
  // List temp_list = new ArrayList();
  // Object obj[] = null;
  //              
  // while (iterate.hasNext()) {
  // obj = (Object[]) iterate.next();
  // clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
  // clearAccountBalanceDTOs.setPick_sumcount(new Integer(obj[0].toString()));
  // clearAccountBalanceDTOs.setPick_sumbalance(new
  // BigDecimal(obj[1].toString()));
  // temp_list.add(clearAccountBalanceDTOs);
  // }
  // return temp_list;
  // }
  // }
  // );
  // } catch (Exception e) {
  // e.printStackTrace();
  // }
  // return tableList;
  // }
  /**
   * 错账调整--调缴存
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceAdjustaccountPayDebit(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(empHAFAccountFlow.orgHAFAccountFlow.id),nvl(sum(empHAFAccountFlow.debit),0) "
              + " from  EmpHAFAccountFlow empHAFAccountFlow "
              + " where empHAFAccountFlow.orgHAFAccountFlow.bizStatus = 5 and "
              + " empHAFAccountFlow.orgHAFAccountFlow.biz_Type='K' and  empHAFAccountFlow.debit <> 0 "
              + " and empHAFAccountFlow.orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(empHAFAccountFlow.orgHAFAccountFlow.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (empHAFAccountFlow.orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs
                .setAdjustaccoutPayment_debit_count(new Integer(obj[0]
                    .toString()));
            clearAccountBalanceDTOs
                .setAdjustaccoutPayment_debit_paybalance(new BigDecimal(obj[1]
                    .toString()).divide(new BigDecimal(1), 2,
                    BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 错账调整--调提取
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceAdjustaccountPickDebit(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(empHAFAccountFlow.orgHAFAccountFlow.id),nvl(sum(empHAFAccountFlow.debit),0) "
              + " from  EmpHAFAccountFlow empHAFAccountFlow "
              + " where empHAFAccountFlow.orgHAFAccountFlow.bizStatus = 5 and "
              + " empHAFAccountFlow.orgHAFAccountFlow.biz_Type='L' and empHAFAccountFlow.debit <> 0 "
              + " and empHAFAccountFlow.orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(empHAFAccountFlow.orgHAFAccountFlow.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (empHAFAccountFlow.orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs
                .setAdjustaccoutPick_debit_count(new Integer(obj[0].toString()));
            clearAccountBalanceDTOs
                .setAdjustaccoutPick_debit_paybalance(new BigDecimal(obj[1]
                    .toString()).divide(new BigDecimal(1), 2,
                    BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 错账调整--调其他
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceAdjustaccountOtherDebit(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(empHAFAccountFlow.orgHAFAccountFlow.id),nvl(sum(empHAFAccountFlow.debit),0) "
              + " from  EmpHAFAccountFlow empHAFAccountFlow "
              + " where empHAFAccountFlow.orgHAFAccountFlow.bizStatus = 5 and "
              + " empHAFAccountFlow.orgHAFAccountFlow.biz_Type='G' and  empHAFAccountFlow.debit <> 0 "
              + " and empHAFAccountFlow.orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(empHAFAccountFlow.orgHAFAccountFlow.org.id)  like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name  like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (empHAFAccountFlow.orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs
                .setAdjustaccoutOther_debit_count(new Integer(obj[0].toString()));
            clearAccountBalanceDTOs
                .setAdjustaccoutOther_debit_paybalance(new BigDecimal(obj[1]
                    .toString()).divide(new BigDecimal(1), 2,
                    BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 转出利息
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceTransOutInterest(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(orgHAFAccountFlowTransOut.personTotal),nvl(sum(orgHAFAccountFlowTransOut.interest),0) "
              + " from  OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut "
              + " where orgHAFAccountFlowTransOut.bizStatus = 5 "
              + " and orgHAFAccountFlowTransOut.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowTransOut.org.id)  like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowTransOut.org.orgInfo.name  like  ? escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowTransOut.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowTransOut.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowTransOut.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowTransOut.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowTransOut.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setTranoutinterest_count(new Integer(0));
            clearAccountBalanceDTOs
                .setTranoutinterest_paybalance(new BigDecimal(obj[1].toString())
                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 销户利息
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryOrgHAFAccountFlowBalanceDistroypickInterest(
      final Serializable orgid, final String orgname, final String collBankId,
      final String startday, final String untilday, final String operator,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(orgHAFAccountFlowDrawing.personTotal),nvl(sum(orgHAFAccountFlowDrawing.interest),0) "
              + " from  OrgHAFAccountFlowDrawing orgHAFAccountFlowDrawing "
              + " where orgHAFAccountFlowDrawing.bizStatus = 5 "
              + " and orgHAFAccountFlowDrawing.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ? and
          // ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlowDrawing.org.id)  like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.org.orgInfo.name  like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlowDrawing.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.settDate = ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlowDrawing.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs
                .setDeleteaccount_interest_count(new Integer(0));
            clearAccountBalanceDTOs
                .setDeleteaccount_interest_paybalance(new BigDecimal(obj[1]
                    .toString()).divide(new BigDecimal(1), 2,
                    BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 期初余额
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception AA101中settdate<会计日期的并且type！=C的记录中的credit-debit
   */
  public List queryOrgHAFAccountFlowBalanceFirst(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo, final String bizType)
      throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0) "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + "  and  orgHAFAccountFlow.biz_Type!='C' and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlow.org.id)  like  ? escape '/' and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " orgHAFAccountFlow.settDate < ? and ";
            parameters.add(startday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlow.settDate < ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlow.settDate < ?  and ";
            parameters.add(untilday);
          }
          // else{
          // criterion += " orgHAFAccountFlow.settDate < "+bizDate+" and ";
          // }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlow.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += " orgHAFAccountFlow.biz_Type = ?  and ";
            parameters.add(bizType);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj = null;

          while (iterate.hasNext()) {
            obj = (Object) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setPre_rest_paybalance(new BigDecimal(obj
                .toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 本期发生额
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception AA101中settdate＝会计日期的并且type！=C的记录中的credit-debit
   */
  public List queryOrgHAFAccountFlowBalanceThis(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo, final String bizType)
      throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0) "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + "  and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlow.org.id)  like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name  like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(startday);
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(untilday);
          }
          // else{
          // criterion += " orgHAFAccountFlow.settDate = "+bizDate+" and ";
          // }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlow.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += " orgHAFAccountFlow.biz_Type = ?  and ";
            parameters.add(bizType);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj = null;

          while (iterate.hasNext()) {
            obj = (Object) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setPre_debit_paybalance(new BigDecimal(obj
                .toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 期末余额
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception AA101中settdate<=会计日期type！=C的记录中的credit-debit
   */
  public List queryOrgHAFAccountFlowBalanceEnd(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo, final String bizType)
      throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0) "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + "  and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlow.org.id)  like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name  like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(untilday);
          }
          // else{
          // criterion += " orgHAFAccountFlow.settDate <= "+bizDate+" and ";
          // }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlow.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += " orgHAFAccountFlow.biz_Type = ?  and ";
            parameters.add(bizType);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj = null;

          while (iterate.hasNext()) {
            obj = (Object) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setCur_rest_paybalance(new BigDecimal(obj
                .toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 账面余额
   * 
   * @param orgid
   * @param orgname
   * @param collBankId
   * @param startday
   * @param untilday
   * @param operator
   * @param bizDate
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception AA101中所有记录的credit-debit
   */
  public List queryOrgHAFAccountFlowBalanceAccount(final Serializable orgid,
      final String orgname, final String collBankId, final String startday,
      final String untilday, final String operator,
      final SecurityInfo securityInfo, final String bizType)
      throws NumberFormatException, Exception {
    List tableList = new ArrayList();
    // final String bizDate = securityInfo.getUserInfo().getBizDate();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0) "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " to_char(orgHAFAccountFlow.org.id) like  ? escape '/'  and ";
            parameters.add("%" + orgid + "%");
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name  like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (collBankId != null && !collBankId.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.collectionBankId = ?  and ";
            parameters.add(collBankId);
          }
          if (startday != null && !startday.equals("") && untilday != null
              && !untilday.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(untilday);
          } else if (startday != null && !startday.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(startday);
          } else if (untilday != null && !untilday.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(untilday);
          }
          if (operator != null && !operator.equals("")) {
            criterion += " orgHAFAccountFlow.reserveaA = ?  and ";
            parameters.add(operator);
          }
          if (bizType != null && !bizType.equals("")) {
            criterion += " orgHAFAccountFlow.biz_Type = ?  and ";
            parameters.add(bizType);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          ClearAccountBalanceDTO clearAccountBalanceDTOs = null;
          List temp_list = new ArrayList();
          Object obj = null;

          while (iterate.hasNext()) {
            obj = (Object) iterate.next();
            clearAccountBalanceDTOs = new ClearAccountBalanceDTO();
            clearAccountBalanceDTOs.setCur_debit_paybalance(new BigDecimal(obj
                .toString()).divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_DOWN));
            temp_list.add(clearAccountBalanceDTOs);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 统计查询-单位收支明细一览表
   * 
   * @param dto 查询条件的DTO
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param securityInfo
   * @return 查询信息的List 郭婧平
   */
  public List queryStatisticInfo(final SearchDTO dto, final String orderBy,
      final String order, final int start, final int pageSize,
      final SecurityInfo securityInfo) {
    List tableList = new ArrayList();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String criterion = "";
          Vector parameters = new Vector();
          String hql = "select "
              + "distinct "
              + "a.org_id orgid,"
              + "c.name orgname,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type not in('C','J') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date<?) jzbalance,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type in('C','J') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date<?) jzgzbalance,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_status=5 and b.org_id=a.org_id and b.sett_date<?) jzzmbalance,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type ='C' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) gzpay,"
              + "(select nvl(sum(b.credit),0) from aa101 b where b.biz_type ='A' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) payment,"
              + "(select nvl(sum(b.credit),0) from aa101 b where b.biz_type in ('B','M') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) addpay,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type in ('K','L','G') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) adjustaccount,"
              + "(select nvl(sum(b.credit),0) from aa101 b where b.biz_type ='F' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) tranin,"
              + "(select nvl(sum(b.credit),0) from aa101 b where b.biz_type ='H' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) interest,"
              + "(select nvl(sum(b.debit),0) from aa101 b where b.biz_type ='D' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) pick,"
              + "(select nvl(sum(b.debit),0) from aa101 b where b.biz_type ='E' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) tranout,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type not in('C','J') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date<=?) currentbalance,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type in('C','J') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date<=?) gzbalance,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_status=5 and b.org_id=a.org_id and b.sett_date<=?) zmbalance "
              + "from aa101 a,aa001 d,ba001 c where a.org_id=d.id and c.id=d.orginfo_id and a.org_id "
              + securityInfo.getGjjSecuritySQL();

          if (dto != null) {
            // 根据单位编号查询
            if (dto.getOrgId().trim() != null
                && !dto.getOrgId().trim().equals("")) {
              criterion += "a.org_id=? and ";
              parameters.add(dto.getOrgId().trim());
            }
            // 根据办事处查询
            if (dto.getOfficeCode() != null && !dto.getOfficeCode().equals("")) {
              criterion += "a.officeCode=? and ";
              parameters.add(dto.getOfficeCode());
            }
            // 根据归集银行查询
            if (dto.getCollectionBankId() != null
                && !dto.getCollectionBankId().equals("")) {
              criterion += "a.moneyBank=? and ";
              parameters.add(dto.getCollectionBankId());
            }
            // 根据主管部门查询
            if (dto.getDeptInCharge() != null
                && !dto.getDeptInCharge().equals("")) {
              criterion += "c.dept_in_charge=? and ";
              parameters.add(dto.getDeptInCharge());
            }
            // 根据单位性质查询
            if (dto.getCharacter() != null && !dto.getCharacter().equals("")) {
              criterion += "c.character=? and ";
              parameters.add(dto.getCharacter());
            }
            // 根据单位名称
            if (dto.getOrgName() != null && !dto.getOrgName().equals("")) {
              criterion += "c.name like ? and ";
              parameters.add("%" + dto.getOrgName() + "%");
            }
            // 根据所在地区查询
            if (dto.getRegion() != null && !dto.getRegion().equals("")) {
              criterion += "c.region=? and ";
              parameters.add(dto.getRegion());
            }
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " a.org_id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          query.setString(0, dto.getDateStart());
          query.setString(1, dto.getDateStart());
          query.setString(2, dto.getDateStart());
          query.setString(3, dto.getDateStart());
          query.setString(5, dto.getDateStart());
          query.setString(7, dto.getDateStart());
          query.setString(9, dto.getDateStart());
          query.setString(11, dto.getDateStart());
          query.setString(13, dto.getDateStart());
          query.setString(15, dto.getDateStart());
          query.setString(17, dto.getDateStart());
          query.setString(4, dto.getDateEnd());
          query.setString(6, dto.getDateEnd());
          query.setString(8, dto.getDateEnd());
          query.setString(10, dto.getDateEnd());
          query.setString(12, dto.getDateEnd());
          query.setString(14, dto.getDateEnd());
          query.setString(16, dto.getDateEnd());
          query.setString(18, dto.getDateEnd());
          query.setString(19, dto.getDateEnd());
          query.setString(20, dto.getDateEnd());
          query.setString(21, dto.getDateEnd());

          for (int i = 22, j = 0; i < parameters.size() + 22; i++, j++) {
            query.setParameter(i, parameters.get(j));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          ListDTO listDTO = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            listDTO = new ListDTO();
            listDTO.setOrgid(new Integer(obj[0].toString()));
            listDTO.setOrgname(obj[1].toString());
            if (obj[2] != null) {
              listDTO.setJzbalance(new BigDecimal(obj[2].toString()));
            }
            if (obj[3] != null) {
              listDTO.setJzgzbalance(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              listDTO.setJzzmbalance(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              listDTO.setGzpay(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null) {
              listDTO.setPayment(new BigDecimal(obj[6].toString()));
            }
            if (obj[7] != null) {
              listDTO.setAddpay(new BigDecimal(obj[7].toString()));
            }
            if (obj[8] != null) {
              listDTO.setAdjustaccount(new BigDecimal(obj[8].toString()));
            }
            if (obj[9] != null) {
              listDTO.setTanin(new BigDecimal(obj[9].toString()));
            }
            if (obj[10] != null) {
              listDTO.setInterest(new BigDecimal(obj[10].toString()));
            }
            if (obj[11] != null) {
              listDTO.setPick(new BigDecimal(obj[11].toString()));
            }
            if (obj[12] != null) {
              listDTO.setTranout(new BigDecimal(obj[12].toString()));
            }
            if (obj[13] != null) {
              listDTO.setCurrentbalance(new BigDecimal(obj[13].toString()));
            }
            if (obj[14] != null) {
              listDTO.setGzbalance(new BigDecimal(obj[14].toString()));
            }
            if (obj[15] != null) {
              listDTO.setZmbalance(new BigDecimal(obj[15].toString()));
            }
            temp_list.add(listDTO);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 统计查询-单位收支明细一览表
   * 
   * @param dto 查询条件的DTO
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param securityInfo
   * @return 查询信息的List条数 郭婧平
   */
  public int queryStatisticInfoCount(final SearchDTO dto,
      final SecurityInfo securityInfo) {
    List tableList = new ArrayList();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String criterion = "";
          Vector parameters = new Vector();
          String hql = "select "
              + "distinct "
              + "a.org_id "
              + "from aa101 a,aa001 d,ba001 c where a.org_id=d.id and c.id=d.orginfo_id and a.org_id "
              + securityInfo.getGjjSecuritySQL();
          if (dto != null) {
            // 根据单位编号查询
            if (dto.getOrgId().trim() != null
                && !dto.getOrgId().trim().equals("")) {
              criterion += "a.org_id=? and ";
              parameters.add(dto.getOrgId().trim());
            }
            // 根据办事处查询
            if (dto.getOfficeCode() != null && !dto.getOfficeCode().equals("")) {
              criterion += "a.officeCode=? and ";
              parameters.add(dto.getOfficeCode());
            }
            // 根据归集银行查询
            if (dto.getCollectionBankId() != null
                && !dto.getCollectionBankId().equals("")) {
              criterion += "a.moneyBank=? and ";
              parameters.add(dto.getCollectionBankId());
            }
            // 根据主管部门查询
            if (dto.getDeptInCharge() != null
                && !dto.getDeptInCharge().equals("")) {
              criterion += "c.dept_in_charge=? and ";
              parameters.add(dto.getDeptInCharge());
            }
            // 根据单位性质查询
            if (dto.getCharacter() != null && !dto.getCharacter().equals("")) {
              criterion += "c.character=? and ";
              parameters.add(dto.getCharacter());
            }
            // 根据单位名称
            if (dto.getOrgName() != null && !dto.getOrgName().equals("")) {
              criterion += "c.name like ? and ";
              parameters.add("%" + dto.getOrgName() + "%");
            }
            // 根据所在地区查询
            if (dto.getRegion() != null && !dto.getRegion().equals("")) {
              criterion += "c.region=? and ";
              parameters.add(dto.getRegion());
            }
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList.size();
  }

  /**
   * 统计查询-单位收支明细一览表页面求和用
   * 
   * @param dto 查询条件的DTO
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param securityInfo
   * @return 查询信息的List 郭婧平
   */
  public List queryStatisticInfoSum(final SearchDTO dto,
      final SecurityInfo securityInfo) {
    List tableList = new ArrayList();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String criterion = "";
          Vector parameters = new Vector();
          String hql = "select "
              + "distinct "
              + "(select nvl(sum(b.credit),0.00) from aa101 b where b.biz_type ='A' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) payment,"
              + "(select nvl(sum(b.credit),0.00) from aa101 b where b.biz_type in ('B','M') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) addpay,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0.00) from aa101 b where b.biz_type in ('K','L','G') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) adjustaccount,"
              + "(select nvl(sum(b.credit),0.00) from aa101 b where b.biz_type ='F' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) tranin,"
              + "(select nvl(sum(b.credit),0.00) from aa101 b where b.biz_type ='H' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) interest,"
              + "(select nvl(sum(b.debit),0.00) from aa101 b where b.biz_type ='D' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) pick,"
              + "(select nvl(sum(b.debit),0.00) from aa101 b where b.biz_type ='E' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) tranout,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type ='C' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) gzpay,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_status=5 and b.org_id=a.org_id and b.sett_date<=?) zmbalance "
              + "from aa101 a,aa001 d,ba001 c where a.org_id=d.id and c.id=d.orginfo_id and a.org_id "
              + securityInfo.getGjjSecuritySQL();

          if (dto != null) {
            // 根据单位编号查询
            if (dto.getOrgId().trim() != null
                && !dto.getOrgId().trim().equals("")) {
              criterion += "a.org_id=? and ";
              parameters.add(dto.getOrgId().trim());
            }
            // 根据办事处查询
            if (dto.getOfficeCode() != null && !dto.getOfficeCode().equals("")) {
              criterion += "a.officeCode=? and ";
              parameters.add(dto.getOfficeCode());
            }
            // 根据归集银行查询
            if (dto.getCollectionBankId() != null
                && !dto.getCollectionBankId().equals("")) {
              criterion += "a.moneyBank=? and ";
              parameters.add(dto.getCollectionBankId());
            }
            // 根据主管部门查询
            if (dto.getDeptInCharge() != null
                && !dto.getDeptInCharge().equals("")) {
              criterion += "c.dept_in_charge=? and ";
              parameters.add(dto.getDeptInCharge());
            }
            // 根据单位性质查询
            if (dto.getCharacter() != null && !dto.getCharacter().equals("")) {
              criterion += "c.character=? and ";
              parameters.add(dto.getCharacter());
            }
            // 根据单位名称
            if (dto.getOrgName() != null && !dto.getOrgName().equals("")) {
              criterion += "c.name like ? and ";
              parameters.add("%" + dto.getOrgName() + "%");
            }
            // 根据所在地区查询
            if (dto.getRegion() != null && !dto.getRegion().equals("")) {
              criterion += "c.region=? and ";
              parameters.add(dto.getRegion());
            }
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          query.setString(0, dto.getDateStart());
          query.setString(2, dto.getDateStart());
          query.setString(4, dto.getDateStart());
          query.setString(6, dto.getDateStart());
          query.setString(8, dto.getDateStart());
          query.setString(10, dto.getDateStart());
          query.setString(12, dto.getDateStart());

          query.setString(1, dto.getDateEnd());
          query.setString(3, dto.getDateEnd());
          query.setString(5, dto.getDateEnd());
          query.setString(7, dto.getDateEnd());
          query.setString(9, dto.getDateEnd());
          query.setString(11, dto.getDateEnd());
          query.setString(13, dto.getDateEnd());

          query.setString(14, dto.getDateStart());
          query.setString(15, dto.getDateEnd());
          query.setString(16, dto.getDateEnd());

          for (int i = 17, j = 0; i < parameters.size() + 17; i++, j++) {
            query.setParameter(i, parameters.get(j));
          }
          Iterator iterate = query.list().iterator();

          ListDTO listDTO = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            listDTO = new ListDTO();
            if (obj[0] != null) {
              listDTO.setPayment(new BigDecimal(obj[0].toString()));
            }
            if (obj[1] != null) {
              listDTO.setAddpay(new BigDecimal(obj[1].toString()));
            }
            if (obj[2] != null) {
              listDTO.setAdjustaccount(new BigDecimal(obj[2].toString()));
            }
            if (obj[3] != null) {
              listDTO.setTanin(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              listDTO.setInterest(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              listDTO.setPick(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null) {
              listDTO.setTranout(new BigDecimal(obj[6].toString()));
            }
            if (obj[7] != null) {
              listDTO.setGzpay(new BigDecimal(obj[7].toString()));
            }
            if (obj[8] != null) {
              listDTO.setZmbalance(new BigDecimal(obj[8].toString()));
            }
            temp_list.add(listDTO);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 统计查询-单位收支明细一览表（打印）
   * 
   * @param dto 查询条件的DTO
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param securityInfo
   * @return 查询信息的List 郭婧平
   */
  public List queryStatisticPrintInfo(final SearchDTO dto,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List tableList = new ArrayList();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String criterion = "";
          Vector parameters = new Vector();
          String hql = "select "
              + "distinct "
              + "a.org_id orgid,"
              + "c.name orgname,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type not in('C','J') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date<?) jzbalance,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type in('C','J') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date<?) jzgzbalance,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_status=5 and b.org_id=a.org_id and b.sett_date<?) jzzmbalance,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type ='C' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) gzpay,"
              + "(select nvl(sum(b.credit),0) from aa101 b where b.biz_type ='A' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) payment,"
              + "(select nvl(sum(b.credit),0) from aa101 b where b.biz_type in ('B','M') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) addpay,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type in ('K','L','G') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) adjustaccount,"
              + "(select nvl(sum(b.credit),0) from aa101 b where b.biz_type ='F' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) tranin,"
              + "(select nvl(sum(b.credit),0) from aa101 b where b.biz_type ='H' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) interest,"
              + "(select nvl(sum(b.debit),0) from aa101 b where b.biz_type ='D' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) pick,"
              + "(select nvl(sum(b.debit),0) from aa101 b where b.biz_type ='E' and b.biz_status=5 and b.org_id=a.org_id and b.sett_date>=? and b.sett_date<=?) tranout,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type not in('C','J') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date<=?) currentbalance,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_type in('C','J') and b.biz_status=5 and b.org_id=a.org_id and b.sett_date<=?) gzbalance,"
              + "(select nvl(sum(b.credit)-sum(b.debit),0) from aa101 b where b.biz_status=5 and b.org_id=a.org_id and b.sett_date<=?) zmbalance "
              + "from aa101 a,aa001 d,ba001 c where a.org_id=d.id and c.id=d.orginfo_id and a.org_id "
              + securityInfo.getGjjSecuritySQL();

          if (dto != null) {
            // 根据单位编号查询
            if (dto.getOrgId().trim() != null
                && !dto.getOrgId().trim().equals("")) {
              criterion += "a.org_id=? and ";
              parameters.add(dto.getOrgId().trim());
            }
            // 根据办事处查询
            if (dto.getOfficeCode() != null && !dto.getOfficeCode().equals("")) {
              criterion += "a.officeCode=? and ";
              parameters.add(dto.getOfficeCode());
            }
            // 根据归集银行查询
            if (dto.getCollectionBankId() != null
                && !dto.getCollectionBankId().equals("")) {
              criterion += "a.moneyBank=? and ";
              parameters.add(dto.getCollectionBankId());
            }
            // 根据主管部门查询
            if (dto.getDeptInCharge() != null
                && !dto.getDeptInCharge().equals("")) {
              criterion += "c.dept_in_charge=? and ";
              parameters.add(dto.getDeptInCharge());
            }
            // 根据单位性质查询
            if (dto.getCharacter() != null && !dto.getCharacter().equals("")) {
              criterion += "c.character=? and ";
              parameters.add(dto.getCharacter());
            }
            // 根据单位名称
            if (dto.getOrgName() != null && !dto.getOrgName().equals("")) {
              criterion += "c.name like ? and ";
              parameters.add("%" + dto.getOrgName() + "%");
            }
            // 根据所在地区查询
            if (dto.getRegion() != null && !dto.getRegion().equals("")) {
              criterion += "c.region=? and ";
              parameters.add(dto.getRegion());
            }
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " a.org_id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          query.setString(0, dto.getDateStart());
          query.setString(1, dto.getDateStart());
          query.setString(2, dto.getDateStart());
          query.setString(3, dto.getDateStart());
          query.setString(5, dto.getDateStart());
          query.setString(7, dto.getDateStart());
          query.setString(9, dto.getDateStart());
          query.setString(11, dto.getDateStart());
          query.setString(13, dto.getDateStart());
          query.setString(15, dto.getDateStart());
          query.setString(17, dto.getDateStart());
          query.setString(4, dto.getDateEnd());
          query.setString(6, dto.getDateEnd());
          query.setString(8, dto.getDateEnd());
          query.setString(10, dto.getDateEnd());
          query.setString(12, dto.getDateEnd());
          query.setString(14, dto.getDateEnd());
          query.setString(16, dto.getDateEnd());
          query.setString(18, dto.getDateEnd());
          query.setString(19, dto.getDateEnd());
          query.setString(20, dto.getDateEnd());
          query.setString(21, dto.getDateEnd());

          for (int i = 22, j = 0; i < parameters.size() + 22; i++, j++) {
            query.setParameter(i, parameters.get(j));
          }

          Iterator iterate = query.list().iterator();

          ListDTO listDTO = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            listDTO = new ListDTO();
            listDTO.setOrgid(new Integer(obj[0].toString()));
            listDTO.setOrgname(obj[1].toString());
            if (obj[2] != null) {
              listDTO.setJzbalance(new BigDecimal(obj[2].toString()));
            }
            if (obj[3] != null) {
              listDTO.setJzgzbalance(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              listDTO.setJzzmbalance(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              listDTO.setGzpay(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null) {
              listDTO.setPayment(new BigDecimal(obj[6].toString()));
            }
            if (obj[7] != null) {
              listDTO.setAddpay(new BigDecimal(obj[7].toString()));
            }
            if (obj[8] != null) {
              listDTO.setAdjustaccount(new BigDecimal(obj[8].toString()));
            }
            if (obj[9] != null) {
              listDTO.setTanin(new BigDecimal(obj[9].toString()));
            }
            if (obj[10] != null) {
              listDTO.setInterest(new BigDecimal(obj[10].toString()));
            }
            if (obj[11] != null) {
              listDTO.setPick(new BigDecimal(obj[11].toString()));
            }
            if (obj[12] != null) {
              listDTO.setTranout(new BigDecimal(obj[12].toString()));
            }
            if (obj[13] != null) {
              listDTO.setCurrentbalance(new BigDecimal(obj[13].toString()));
            }
            if (obj[14] != null) {
              listDTO.setGzbalance(new BigDecimal(obj[14].toString()));
            }
            if (obj[15] != null) {
              listDTO.setZmbalance(new BigDecimal(obj[15].toString()));
            }
            temp_list.add(listDTO);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * 按办事处查询单位账
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOfficeCode(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = "orgHAFAccountFlow.officeCode";
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode "
              + "order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj = null;

          while (iterate.hasNext()) {
            obj = (Object) iterate.next();
            if (obj != null) {
              dto = new OrgAccountInfoDTO();
              dto.setOfficecode(obj.toString());
            } else {
              dto = new OrgAccountInfoDTO();
              dto.setOfficecode("");
            }
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账(打印)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountPrintByOfficeCode(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = "orgHAFAccountFlow.officeCode";
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode "
              + "order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj = null;

          while (iterate.hasNext()) {
            obj = (Object) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOfficecode(obj.toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountMonthByOfficeCode(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode,substr(orgHAFAccountFlow.settDate,0,6) "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode,substr(orgHAFAccountFlow.settDate,0,6) "
              + "order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object[] obj = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOfficecode(obj[0].toString());
            dto.setOpTime(obj[1].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账(打印)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountMonthPrintByOfficeCode(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode,substr(orgHAFAccountFlow.settDate,0,6) "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode,substr(orgHAFAccountFlow.settDate,0,6) "
              + "order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();
          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object[] obj = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOfficecode(obj[0].toString());
            dto.setOpTime(obj[1].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountDayByOfficeCode(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode,orgHAFAccountFlow.settDate "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode,orgHAFAccountFlow.settDate "
              + "order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object[] obj = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOfficecode(obj[0].toString());
            dto.setOpTime(obj[1].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账(打印)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountDayPrintByOfficeCode(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode,orgHAFAccountFlow.settDate "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode,orgHAFAccountFlow.settDate "
              + "order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();
          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object[] obj = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOfficecode(obj[0].toString());
            dto.setOpTime(obj[1].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账记录条数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountCountByOfficeCode(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode ";
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
   * 按办事处查询单位账记录条数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountCountMonthByOfficeCode(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode,substr(orgHAFAccountFlow.settDate,0,6) ";
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
   * 按办事处查询单位账记录条数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountCountDayByOfficeCode(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode,orgHAFAccountFlow.settDate ";
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
   * 按办事处查询单位账(合计)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountTotalByOfficeCode(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0),"
              + " nvl(sum(orgHAFAccountFlow.interest),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();

          OrgAccountInfoTotalDTO dto = null;
          List temp_list = new ArrayList();
          Object[] obj = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoTotalDTO();
            dto.setSumMoney(new BigDecimal(obj[0].toString()));
            dto.setSumInterest(new BigDecimal(obj[1].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账(借方笔数、借方发生额)
   * Count发生日期期间的符合查询条件的BIZ_TYPE=D、E、K、L和G的所有ORG_ID下的DEBIT！=0
   * Sum发生日期期间的符合查询条件的BIZ_TYPE=D、E、K、L和G的所有ORG_ID下的DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOfficeCodeDebit(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode,"
              + " nvl(sum(orgHAFAccountFlow.debit),0), count(orgHAFAccountFlow.id)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.debit <> 0 "
              + " and (orgHAFAccountFlow.biz_Type='D' or orgHAFAccountFlow.biz_Type='E' or orgHAFAccountFlow.biz_Type='K' "
              + " or orgHAFAccountFlow.biz_Type='L' or orgHAFAccountFlow.biz_Type='G') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode "
              + "order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          //
          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);
          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOfficecode(obj[0].toString());
            dto.setTemp_debit(new BigDecimal(obj[1].toString()));
            dto.setCountDebit(obj[2].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账(贷方笔数、贷方发生额)
   * Sum发生日期期间的符合查询条件的BIZ_TYPE=A、B、F、K、L、G、H、M的所有ORG_ID下的CREDIT
   * Count发生日期期间的符合查询条件的BIZ_TYPE=A、B、F、K、L、G、H、M的所有ORG_ID的CREDIT！=0的笔数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOfficeCodeCredit(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode,"
              + " nvl(sum(orgHAFAccountFlow.credit),0), count(orgHAFAccountFlow.id)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.credit <> 0 "
              + " and (orgHAFAccountFlow.biz_Type='A' or orgHAFAccountFlow.biz_Type='B' or orgHAFAccountFlow.biz_Type='F' "
              + " or orgHAFAccountFlow.biz_Type='K' or orgHAFAccountFlow.biz_Type='L' or orgHAFAccountFlow.biz_Type='G' "
              + " or orgHAFAccountFlow.biz_Type='H' or orgHAFAccountFlow.biz_Type='M') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOfficecode(obj[0].toString());
            dto.setTemp_credit(new BigDecimal(obj[1].toString()));
            dto.setCountCredit(obj[2].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账(期初余额) 发生日期前的，符合查询条件的ORG_ID下的BIZ_TYPE！=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOfficeCodePreBalance(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and orgHAFAccountFlow.biz_Type <>'C' and orgHAFAccountFlow.biz_Type<>'J' "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate  < ? and ";
            parameters.add(inOpDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate < ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate < ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOfficecode(obj[0].toString());
            dto.setPrebalance(new BigDecimal(obj[1].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账(挂账金额) Sum发生日期期间的符合查询条件的所有ORG_ID下的BIZ_TYPE=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOfficeCodeOverMoney(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and (orgHAFAccountFlow.biz_Type = 'C' or orgHAFAccountFlow.biz_Type = 'J') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOfficecode(obj[0].toString());
            dto.setOrgOverPaybalance(new BigDecimal(obj[1].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账(期末余额) sum所有查询截至日期前的BIZ_TYPE！=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOfficeCodeCurBalance(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and orgHAFAccountFlow.biz_Type <> 'C' and orgHAFAccountFlow.biz_Type <> 'J' "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ? and ";
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOfficecode(obj[0].toString());
            dto.setCurbalance(new BigDecimal(obj[1].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账(挂账余额) sum所有查询截至日期前的BIZ_TYPE=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOfficecodeOverBalance(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and (orgHAFAccountFlow.biz_Type = 'C' or orgHAFAccountFlow.biz_Type = 'J') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ? and ";
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOfficecode(obj[0].toString());
            dto.setOrgOverPaybalance(new BigDecimal(obj[1].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByCollBank(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct orgHAFAccountFlow.moneyBank "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.moneyBank";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.moneyBank "
              + "order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj = null;

          while (iterate.hasNext()) {
            obj = (Object) iterate.next();
            if (obj != null) {
              dto = new OrgAccountInfoDTO();
              dto.setCollbankid(obj.toString());
            } else {
              dto = new OrgAccountInfoDTO();
              dto.setCollbankid("");
            }

            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账(打印)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountPrintByCollBank(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct orgHAFAccountFlow.moneyBank "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.orgInfo.collectionBankId";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by  orgHAFAccountFlow.moneyBank ";
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();
          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj = null;

          while (iterate.hasNext()) {
            obj = (Object) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj.toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountMonthByCollBank(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank,substr(orgHAFAccountFlow.settDate,0,6) "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.moneyBank";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode, "
              + " orgHAFAccountFlow.moneyBank,substr(orgHAFAccountFlow.settDate,0,6) "
              + "order by orgHAFAccountFlow.officeCode," + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object[] obj = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj[0].toString());
            dto.setOpTime(obj[1].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账(打印)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountMonthPrintByCollBank(
      final String collectionBankId, final String opDate,
      final String inOpDate, final SecurityInfo securityInfo,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank,substr(orgHAFAccountFlow.settDate,0,6) "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.moneyBank";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode, "
              + " orgHAFAccountFlow.moneyBank,substr(orgHAFAccountFlow.settDate,0,6) "
              + "order by orgHAFAccountFlow.officeCode," + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();
          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object[] obj = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj[0].toString());
            dto.setOpTime(obj[1].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountDayByCollBank(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.settDate "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.moneyBank";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode, "
              + " orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.settDate "
              + "order by orgHAFAccountFlow.officeCode," + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object[] obj = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj[0].toString());
            dto.setOpTime(obj[1].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账(打印)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountDayPrintByCollBank(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.settDate "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.orgInfo.collectionBankId";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode, "
              + " orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.settDate "
              + "order by orgHAFAccountFlow.officeCode," + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();
          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object[] obj = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj[0].toString());
            dto.setOpTime(obj[1].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账记录条数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountCountByCollBank(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct orgHAFAccountFlow.moneyBank "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion + " group by  orgHAFAccountFlow.moneyBank ";
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
   * 按银行查询单位账记录条数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountCountMonthByCollBank(
      final String collectionBankId, final String opDate,
      final String inOpDate, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode,"
              + "orgHAFAccountFlow.moneyBank,substr(orgHAFAccountFlow.settDate,0,6) ";
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
   * 按银行查询单位账记录条数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountCountDayByCollBank(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode,"
              + "orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.settDate ";
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
   * 按银行查询单位账(合计)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountTotalByCollBank(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0),"
              + " nvl(sum(orgHAFAccountFlow.interest),0) "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          OrgAccountInfoTotalDTO dto = null;
          List temp_list = new ArrayList();
          Object[] obj = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoTotalDTO();
            dto.setSumMoney(new BigDecimal(obj[0].toString()));
            dto.setSumInterest(new BigDecimal(obj[1].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账(借方笔数、借方发生额)
   * Count发生日期期间的符合查询条件的BIZ_TYPE=D、E、K、L和G的所有ORG_ID下的DEBIT！=0
   * Sum发生日期期间的符合查询条件的BIZ_TYPE=D、E、K、L和G的所有ORG_ID下的DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByCollBankDebit(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank ,"
              + " nvl(sum(orgHAFAccountFlow.debit),0), count(orgHAFAccountFlow.id)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.debit <> 0 "
              + " and (orgHAFAccountFlow.biz_Type='D' or orgHAFAccountFlow.biz_Type='E' or orgHAFAccountFlow.biz_Type='K' "
              + " or orgHAFAccountFlow.biz_Type='L' or orgHAFAccountFlow.biz_Type='G') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.moneyBank";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by  orgHAFAccountFlow.moneyBank "
              + "order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj[0].toString());
            dto.setTemp_debit(new BigDecimal(obj[1].toString()));
            dto.setCountDebit(obj[2].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账(贷方笔数、贷方发生额)
   * Sum发生日期期间的符合查询条件的BIZ_TYPE=A、B、F、K、L、G、H、M的所有ORG_ID下的CREDIT
   * Count发生日期期间的符合查询条件的BIZ_TYPE=A、B、F、K、L、G、H、M的所有ORG_ID的CREDIT！=0的笔数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByCollBankCredit(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank,"
              + " nvl(sum(orgHAFAccountFlow.credit),0), count(orgHAFAccountFlow.id)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.credit <> 0 "
              + " and (orgHAFAccountFlow.biz_Type='A' or orgHAFAccountFlow.biz_Type='B' or orgHAFAccountFlow.biz_Type='F' "
              + " or orgHAFAccountFlow.biz_Type='K' or orgHAFAccountFlow.biz_Type='L' or orgHAFAccountFlow.biz_Type='G' "
              + " or orgHAFAccountFlow.biz_Type='H' or orgHAFAccountFlow.biz_Type='M') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.moneyBank";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by  orgHAFAccountFlow.moneyBank "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          //
          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj[0].toString());
            dto.setTemp_credit(new BigDecimal(obj[1].toString()));
            dto.setCountCredit(obj[2].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账(期初余额) 发生日期前的，符合查询条件的ORG_ID下的BIZ_TYPE！=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByCollBankPreBalance(
      final String collectionBankId, final String opDate,
      final String inOpDate, final SecurityInfo securityInfo,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and orgHAFAccountFlow.biz_Type <>'C' and orgHAFAccountFlow.biz_Type<>'J' "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate  < ? and ";
            parameters.add(inOpDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate < ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate < ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.moneyBank";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by  orgHAFAccountFlow.moneyBank "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj[0].toString());
            dto.setPrebalance(new BigDecimal(obj[1].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账(挂账金额) Sum发生日期期间的符合查询条件的所有ORG_ID下的BIZ_TYPE=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByCollBankOverMoney(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and (orgHAFAccountFlow.biz_Type = 'C' or orgHAFAccountFlow.biz_Type = 'J') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.moneyBank";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by  orgHAFAccountFlow.moneyBank "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          //
          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj[0].toString());
            dto.setOrgOverMoney(new BigDecimal(obj[1].toString()));

            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账(期末余额) sum所有查询截至日期前的BIZ_TYPE！=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByCollBankCurBalance(
      final String collectionBankId, final String opDate,
      final String inOpDate, final SecurityInfo securityInfo,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and orgHAFAccountFlow.biz_Type <> 'C' and orgHAFAccountFlow.biz_Type <> 'J' "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ? and ";
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.moneyBank";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by  orgHAFAccountFlow.moneyBank "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj[0].toString());
            dto.setCurbalance(new BigDecimal(obj[1].toString()));

            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按银行查询单位账(挂账余额) sum所有查询截至日期前的BIZ_TYPE=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByCollBankOverBalance(
      final String collectionBankId, final String opDate,
      final String inOpDate, final SecurityInfo securityInfo,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and (orgHAFAccountFlow.biz_Type = 'C' or orgHAFAccountFlow.biz_Type = 'J') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ? and ";
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.moneyBank";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by  orgHAFAccountFlow.moneyBank "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj[0].toString());
            dto.setOrgOverPaybalance(new BigDecimal(obj[1].toString()));

            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOrg(final String orgid, final String orgname,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.officeCode "
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ?  and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name "
              + "order by " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            if (obj[2] != null && !"".equals(obj[2])) {
              dto.setCollbankid(obj[2].toString());
            }
            if (obj[3] != null && !"".equals(obj[3])) {
              dto.setOfficecode(obj[3].toString());
            }
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账(打印)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountPrintByOrg(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " orgHAFAccountFlow.moneyBank"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ?  and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name "
              + "order by orgHAFAccountFlow.moneyBank, " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();
          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setCollbankid(obj[2].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountMonthByOrg(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " orgHAFAccountFlow.moneyBank,substr(orgHAFAccountFlow.settDate,0,6)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id =? and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name,substr(orgHAFAccountFlow.settDate,0,6),orgHAFAccountFlow.moneyBank "
              + "order by  " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setCollbankid(obj[2].toString());
            dto.setOpTime(obj[3].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账(打印)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountMonthPrintByOrg(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " orgHAFAccountFlow.moneyBank,substr(orgHAFAccountFlow.settDate,0,6)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id =? and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name,substr(orgHAFAccountFlow.settDate,0,6),orgHAFAccountFlow.moneyBank "
              + "order by  " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();
          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setCollbankid(obj[2].toString());
            dto.setOpTime(obj[3].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountDayByOrg(final String orgid, final String orgname,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.settDate"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id =? and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name,orgHAFAccountFlow.settDate,orgHAFAccountFlow.moneyBank "
              + "order by  " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setCollbankid(obj[2].toString());
            dto.setOpTime(obj[3].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账(打印)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountDayPrintByOrg(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.settDate"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id =? and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name,orgHAFAccountFlow.settDate,orgHAFAccountFlow.moneyBank "
              + "order by  " + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator iterate = query.list().iterator();
          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setCollbankid(obj[2].toString());
            dto.setOpTime(obj[3].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账记录条数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountCountByOrg(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " orgHAFAccountFlow.moneyBank"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ?  and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name";
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
   * 按单位查询单位账记录条数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountCountMonthByOrg(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " orgHAFAccountFlow.moneyBank"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ?  and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,"
              + "orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name"
              + ",substr(orgHAFAccountFlow.settDate,0,6) ";
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
   * 按单位查询单位账记录条数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountCountDayByOrg(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " orgHAFAccountFlow.moneyBank"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ?  and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,"
              + "orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name"
              + ",orgHAFAccountFlow.settDate ";
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
   * 按单位查询单位账(合计)
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountTotalByOrg(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0),"
              + " nvl(sum(orgHAFAccountFlow.interest),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ?  and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          OrgAccountInfoTotalDTO dto = null;
          List temp_list = new ArrayList();
          Object[] obj = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoTotalDTO();
            dto.setSumMoney(new BigDecimal(obj[0].toString()));
            dto.setSumInterest(new BigDecimal(obj[1].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账(借方笔数、借方发生额)
   * Count发生日期期间的符合查询条件的BIZ_TYPE=D、E、K、L和G的所有ORG_ID下的DEBIT！=0
   * Sum发生日期期间的符合查询条件的BIZ_TYPE=D、E、K、L和G的所有ORG_ID下的DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOrgDebit(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final String bankid, final String officecode,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " nvl(sum(orgHAFAccountFlow.debit),0), count(orgHAFAccountFlow.id)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.debit <> 0 "
              + " and (orgHAFAccountFlow.biz_Type='D' or orgHAFAccountFlow.biz_Type='E' or orgHAFAccountFlow.biz_Type='K' "
              + " or orgHAFAccountFlow.biz_Type='L' or orgHAFAccountFlow.biz_Type='G') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ? and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (bankid != null && !bankid.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank  = ?  and ";
            parameters.add(bankid);
          }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name "
              + "order by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank, "
              + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setTemp_debit(new BigDecimal(obj[2].toString()));
            dto.setCountDebit(obj[3].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账(贷方笔数、贷方发生额)
   * Sum发生日期期间的符合查询条件的BIZ_TYPE=A、B、F、K、L、G、H、M的所有ORG_ID下的CREDIT
   * Count发生日期期间的符合查询条件的BIZ_TYPE=A、B、F、K、L、G、H、M的所有ORG_ID的CREDIT！=0的笔数
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOrgCredit(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final String bankid, final String officecode,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " nvl(sum(orgHAFAccountFlow.credit),0), count(orgHAFAccountFlow.id)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 and orgHAFAccountFlow.credit <> 0 "
              + " and (orgHAFAccountFlow.biz_Type='A' or orgHAFAccountFlow.biz_Type='B' or orgHAFAccountFlow.biz_Type='F' "
              + " or orgHAFAccountFlow.biz_Type='K' or orgHAFAccountFlow.biz_Type='L' or orgHAFAccountFlow.biz_Type='G' "
              + " or orgHAFAccountFlow.biz_Type='H' or orgHAFAccountFlow.biz_Type='M') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ? and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (bankid != null && !bankid.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(bankid);
          }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name "
              + "order by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank, "
              + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setTemp_credit(new BigDecimal(obj[2].toString()));
            dto.setCountCredit(obj[3].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账(期初余额) 发生日期前的，符合查询条件的ORG_ID下的BIZ_TYPE！=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOrgPreBalance(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final String bankid, final String officecode,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and orgHAFAccountFlow.biz_Type <>'C' and orgHAFAccountFlow.biz_Type<>'J' "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ? and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (bankid != null && !bankid.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank =? and ";
            parameters.add(bankid);
          }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode =? and  ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate  < ? and ";
            parameters.add(inOpDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate < ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate < ?  and ";
            parameters.add(opDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name "
              + "order by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank, "
              + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setPrebalance(new BigDecimal(obj[2].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账(挂账金额) Sum发生日期期间的符合查询条件的所有ORG_ID下的BIZ_TYPE=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOrgOverMoney(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final String bankid, final String officecode,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;

    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and (orgHAFAccountFlow.biz_Type = 'C' or orgHAFAccountFlow.biz_Type = 'J') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ? and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (bankid != null && !bankid.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(bankid);
          }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (orgHAFAccountFlow.settDate between ? and ? ) and ";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate = ?  and ";
            parameters.add(opDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name "
              + "order by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank, "
              + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setOrgOverMoney(new BigDecimal(obj[2].toString()));

            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按办事处查询单位账(期末余额) sum所有查询截至日期前的BIZ_TYPE！=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOrgCurBalance(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final String bankid, final String officecode,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;

    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and orgHAFAccountFlow.biz_Type <> 'C' and orgHAFAccountFlow.biz_Type <> 'J' "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ? and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }

          if (bankid != null && !bankid.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank =?  and ";
            parameters.add(bankid);
          }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode =?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ? and ";
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(opDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name "
              + "order by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank, "
              + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setCurbalance(new BigDecimal(obj[2].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 按单位查询单位账(挂账余额) sum所有查询截至日期前的BIZ_TYPE=C,J的CREDIT-DEBIT
   * 
   * @param officecode
   * @param opDate
   * @param inOpDate
   * @param securityInfo
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryOrgAccountByOrgOverBalance(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final String bankid, final String officecode,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;

    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and (orgHAFAccountFlow.biz_Type = 'C' or orgHAFAccountFlow.biz_Type = 'J') "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ? and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (bankid != null && !bankid.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(bankid);
          }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ? and ";
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate <= ?  and ";
            parameters.add(opDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name "
              + "order by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank, "
              + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setOrgOverPaybalance(new BigDecimal(obj[2].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryByBusinessId(final Integer bizId) {

    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from OrgHAFAccountFlowPayment orgHAFAccountFlowPayment ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bizId != null && !bizId.equals("")) {
            criterion += " orgHAFAccountFlowPayment.bizId=? ";
            parameters.add(bizId);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;

  }

  /**
   * 验证当前系统当前日期是否扎账
   */
  public String queryClearaccount_WL(final String opsys, final String oper,
      final SecurityInfo securityInfo) throws Exception, BusinessException {
    String flag = "0";
    try {
      flag = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          Vector parameters = new Vector();
          if (opsys.equals("1")) {// 归集
            sql = "select t.id from aa101 t where (t.biz_status=3 or t.biz_status=4) and t.org_id "
                + securityInfo.getGjjSecuritySQL() + " and t.reservea_a=?";
            parameters.add(oper);
          } else if (opsys.equals("2")) {// 个贷
            sql = "select t.flow_head_id from pl202 t where (t.biz_st=4 or t.biz_st=5) and t.loan_bank_id "
                + securityInfo.getDkSecuritySQL() + " and t.make_person=?";
            parameters.add(oper);
          } else if (opsys.equals("3")) {// 财务
            sql = "select t.credence_id from fn201 t where t.book_id=? and (t.credence_st is null or t.credence_st='0' or t.credence_st='1') and t.makebill=?";
            parameters.add(securityInfo.getBookId());
            parameters.add(oper);
          } else {
            sql = "";
          }
          if (!sql.equals("")) {
            Query query = session.createSQLQuery(sql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (query.list().size() == 0) {
              return "0";
            } else {
              return "1";
            }
          } else {
            return "0";
          }
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }

  public String headId(final Integer bizId, final String type) {

    String headid = "";
    try {
      headid = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select id from aa101 where biz_id=? and biz_type=? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bizId);
          query.setParameter(1, type);
          Object obj = null;
          Iterator it = query.list().iterator();
          if (it.hasNext())
            obj = (Object) it.next();
          return obj;
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return headid;
  }

  /*
   * 根据主键查询单位id
   */
  public String queryOrgID(final Integer id) {

    BigDecimal orgId = new BigDecimal(0);
    try {
      orgId = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select a.org_id from aa101 a where a.id=?  ";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, id);
              Object obj = null;
              Iterator it = query.list().iterator();
              if (it.hasNext())
                obj = (Object) it.next();
              return obj;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orgId.toString();
  }

  /**
   * 根据BIZ_ID查询 return OrgHAFAccountFlow
   */
  public OrgHAFAccountFlow queryByBizId_wsh(final String bis_id,
      final String biz_type) {
    return (OrgHAFAccountFlow) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from OrgHAFAccountFlow orgHAFAccountFlow  where  orgHAFAccountFlow.biz_Type='"
                + biz_type + "' and " + " orgHAFAccountFlow.bizId=?";
            Query query = session.createQuery(hql);
            query.setBigDecimal(0, new BigDecimal(bis_id));
            return query.uniqueResult();
          }
        });
  }

  /**
   * 根据BIZ_ID查询 return OrgHAFAccountFlow
   */
  public OrgHAFAccountFlow queryByBizId_fuyf(final String bis_id,
      final String biz_type) {
    return (OrgHAFAccountFlow) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from OrgHAFAccountFlow orgHAFAccountFlow  where  orgHAFAccountFlow.biz_Type='"
                + biz_type
                + "' and "
                + " orgHAFAccountFlow.bizId=? and orgHAFAccountFlow.bizStatus !=5";
            Query query = session.createQuery(hql);
            query.setBigDecimal(0, new BigDecimal(bis_id));
            return query.uniqueResult();
          }
        });
  }

  /**
   * 根据BIZ_ID查询 return OrgHAFAccountFlow
   */
  public OrgHAFAccountFlow queryByBizId_wsh1(final String bis_id,
      final String biz_type) {
    return (OrgHAFAccountFlow) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from OrgHAFAccountFlow orgHAFAccountFlow  where  orgHAFAccountFlow.biz_Type='"
                + biz_type
                + "' and "
                + " orgHAFAccountFlow.bizId=? and orgHAFAccountFlow.bizStatus in(4,5)";
            Query query = session.createQuery(hql);
            query.setBigDecimal(0, new BigDecimal(bis_id));
            return query.uniqueResult();
          }
        });
  }

  /*
   * 根据办事处查询当前余额
   */

  public List queryOrgBalanceByOffice(final String officecode,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select sum(a2.pre_balance+a2.cur_balance)"
              + " from aa002 a2,aa001  a1,ba001 b1 "
              + " where a2.org_id=a1.id and a1.orginfo_id=b1.id "
              + " and a2.org_id " + securityInfo.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " b1.officecode = ?  and ";
            parameters.add(officecode);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion + " group by b1.officecode ";

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          ;

          Iterator it = query.list().iterator();
          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj = null;

          while (it.hasNext()) {
            obj = (Object) it.next();
            dto = new OrgAccountInfoDTO();
            dto.setAccountBalance(new BigDecimal(obj.toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 根据归集银行查询
   */

  public List queryOrgBalanceByBank(final String collectionBankId,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select sum(a2.pre_balance+a2.cur_balance)"
              + " from aa002 a2,aa001  a1,ba001 b1 "
              + " where a2.org_id=a1.id and a1.orginfo_id=b1.id "
              + " and a2.org_id " + securityInfo.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " b1.collection_bank_id = ?  and ";
            parameters.add(collectionBankId);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion + " group by b1.collection_bank_id ";

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          ;

          Iterator it = query.list().iterator();
          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj = null;

          while (it.hasNext()) {
            obj = (Object) it.next();
            dto = new OrgAccountInfoDTO();
            dto.setAccountBalance(new BigDecimal(obj.toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 根据单位查询
   */

  public List queryOrgBalanceByOrgid(final String orgid, final String orgname,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select sum(a2.pre_balance+a2.cur_balance)"
              + " from aa002 a2,aa001  a1,ba001 b1 "
              + " where a2.org_id=a1.id and a1.orginfo_id=b1.id "
              + " and a2.org_id " + securityInfo.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " a1.id = ?  and ";
            parameters.add(orgid);
          }

          if (orgname != null && !orgname.equals("")) {
            criterion += " b1.name = ?  and ";
            parameters.add(orgname);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          ;

          Iterator it = query.list().iterator();
          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj = null;

          while (it.hasNext()) {
            obj = (Object) it.next();
            dto = new OrgAccountInfoDTO();
            dto.setAccountBalance(new BigDecimal(obj.toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 根据归集银行查询返回帐户余额
   */

  public String findBankBalance(final String bankid) {

    String balance = "";
    balance = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select sum(a2.pre_balance + a2.cur_balance) "
            + " from aa002 a2  " + " where a2.org_id in ( "
            + " select distinct  " + " aa101.org_id "
            + " from aa101  where aa101.moneybank=? )";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, bankid);
        return query.uniqueResult().toString();
      }
    });

    return balance;
  }

  public String findOrgBalance(final String orgid) {

    String balance = "";
    balance = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select sum(a2.pre_balance + a2.cur_balance) "
            + " from aa002 a2  " + " where a2.org_id = ?";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, orgid);
        return query.uniqueResult().toString();
      }
    });

    return balance;
  }

  /*
   * 按办事处查询帐户余额
   */
  public List queryOrgAccountByOfficeCodeBalance(final String officecode,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.officeCode,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and orgHAFAccountFlow.biz_Type <>'C' and orgHAFAccountFlow.biz_Type<>'J' "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode = ?  and ";
            parameters.add(officecode);
          }
          if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate  < ? and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.officeCode";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by orgHAFAccountFlow.officeCode "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            // dto.setOfficecode(obj[0].toString());
            dto.setAccountBalance((new BigDecimal(obj[1].toString())));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 按银行查询帐户余额
   */
  public List queryOrgAccountByCollBankBalance(final String collectionBankId,
      final String opDate, final String inOpDate,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.moneyBank,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and orgHAFAccountFlow.biz_Type <>'C' and orgHAFAccountFlow.biz_Type<>'J' "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (collectionBankId != null && !collectionBankId.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank = ?  and ";
            parameters.add(collectionBankId);
          }
          if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate  < ? and ";
            parameters.add(opDate);
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.moneyBank";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + " group by  orgHAFAccountFlow.moneyBank "
              + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setCollbankid(obj[0].toString());
            dto.setAccountBalance(new BigDecimal(obj[1].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /*
   * 根据单位查询该单位的帐户余额
   */
  public List queryOrgAccountByOrgBalance(final String orgid,
      final String orgname, final String opDate, final String inOpDate,
      final String bankid, final String officecode,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select orgHAFAccountFlow.org.id, orgHAFAccountFlow.org.orgInfo.name,"
              + " nvl(sum(orgHAFAccountFlow.credit-orgHAFAccountFlow.debit),0)"
              + " from  OrgHAFAccountFlow orgHAFAccountFlow "
              + " where orgHAFAccountFlow.bizStatus = 5 "
              + " and orgHAFAccountFlow.biz_Type <>'C' and orgHAFAccountFlow.biz_Type<>'J' "
              + " and orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          // if (bizDate != null && !bizDate.equals("")) {
          // criterion += " orgHAFAccountFlow.settDate = ? and ";
          // parameters.add(bizDate);
          // }
          if (orgid != null && !orgid.equals("")) {
            criterion += " orgHAFAccountFlow.org.id = ? and ";
            parameters.add(new Integer(orgid));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orgHAFAccountFlow.org.orgInfo.name like ?  escape '/' and ";
            parameters.add("%" + orgname + "%");
          }
          if (bankid != null && !bankid.equals("")) {
            criterion += " orgHAFAccountFlow.moneyBank =? and ";
            parameters.add(bankid);
          }
          if (officecode != null && !officecode.equals("")) {
            criterion += " orgHAFAccountFlow.officeCode =? and  ";
            parameters.add(officecode);
          }
          if (opDate != null && !opDate.equals("")) {
            criterion += " orgHAFAccountFlow.settDate  < ? and ";
            parameters.add(opDate);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgHAFAccountFlow.org.id";
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank,orgHAFAccountFlow.org.id,orgHAFAccountFlow.org.orgInfo.name "
              + "order by orgHAFAccountFlow.officeCode ,orgHAFAccountFlow.moneyBank, "
              + ob + " " + od;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          // query.setFirstResult(start);
          // if (pageSize > 0)
          // query.setMaxResults(pageSize);

          Iterator iterate = query.list().iterator();

          OrgAccountInfoDTO dto = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgAccountInfoDTO();
            dto.setOrgid(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setAccountBalance(new BigDecimal(obj[2].toString()));
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 根据流水尾表求出职工上年
  public BigDecimal queryqcyeTrail(final String orgId, final String empId,
      final String timeSt) {
    BigDecimal qcye = new BigDecimal(0.00);
    try {
      qcye = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select nvl(sum(a102.credit - a102.debit), 0) "
                  + " from aa101 a101, aa102 a102"
                  + " where a101.id = a102.org_flow_id" + " and a101.org_id=?"
                  + " and a102.emp_id=?" + " and a101.sett_date < ?";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, orgId);
              query.setParameter(1, empId);
              query.setParameter(2, timeSt);
              BigDecimal qcye = (BigDecimal) query.uniqueResult();
              return qcye;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return qcye;
  }

  public List queryCollFnComparisonList(final String officeCode,
      final String bankId, final String orgId, final String orgId_1,
      final String orgName, final String timeSt, final String timeEnd,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String orgiiid = "";
          if (orgId == null || orgId.trim().equals("")) {
            orgiiid = " and a.org_id=" + orgId_1;
          } else {
            orgiiid = " and a.org_id=" + orgId;
          }
          String hql = "";
          if ((orgId == null || orgId.equals(""))
              && (orgId_1 == null || orgId_1.equals(""))) {
            orgiiid = "";
          }

          hql = "select a1.* " + "from (select distinct a.doc_num, "
              + "  a.biz_type, " + " a.sett_date, " + " a.biz_status, "
              + " nvl(a.debit, 0), " + " nvl(a.credit, 0), "
              + " nvl(a.interest, 0), " + " a.org_id, " + " d.name, "
              + " a.id, " + " f201.credence_st "
              + " from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + " where a.org_id = c.id " + "  and c.orginfo_id = d.id "
              + "  and a.note_num = f201.sett_num(+) "
              + " and a.biz_status = 5 " + orgiiid
              + " and f201.reserve_c is null " + " union all "
              + "  select distinct a.doc_num, " + "               a.biz_type, "
              + "              a.sett_date, " + "             a.biz_status, "
              + "            nvl(a.debit, 0), "
              + "           nvl(a.credit, 0), "
              + "          nvl(a.interest, 0), " + "         a.org_id, "
              + "        d.name, " + "       a.id, "
              + "      f201.credence_st "
              + "  from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + " where a.org_id = c.id " + "  and c.orginfo_id = d.id "
              + "  and a.note_num = f201.sett_num(+) "
              + " and a.biz_status = 5 "
              + " and a.biz_type in ('A', 'B', 'M') " + orgiiid
              + " and f201.reserve_c = '1' " + " union all "
              + " select distinct a.doc_num, " + " a.biz_type, "
              + " a.sett_date, " + " a.biz_status, " + " nvl(a.debit, 0), "
              + " nvl(a.credit, 0), " + " nvl(a.interest, 0), " + " a.org_id, "
              + " d.name, " + " a.id, " + " f201.credence_st "
              + " from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + " where a.org_id = c.id " + "  and c.orginfo_id = d.id "
              + "  and a.note_num = f201.sett_num(+) "
              + "  and a.biz_status = 5 " + "  and a.biz_type = 'C' " + orgiiid
              + "  and (f201.reserve_c = '9' or f201.reserve_c = '10') "
              + "  union all " + "  select distinct a.doc_num, "
              + "  a.biz_type, " + "   a.sett_date, " + "  a.biz_status, "
              + " nvl(a.debit, 0), " + " nvl(a.credit, 0), "
              + " nvl(a.interest, 0), " + " a.org_id, " + " d.name, "
              + " a.id, " + " f201.credence_st "
              + "   from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + "  where a.org_id = c.id " + "   and c.orginfo_id = d.id "
              + "  and a.note_num = f201.sett_num(+) "
              + "  and a.biz_status = 5 " + "  and a.biz_type ='E' " + orgiiid
              + "  and  f201.reserve_c = '4' " + "  union all "
              + "  select distinct a.doc_num, " + "    a.biz_type, "
              + " a.sett_date, " + " a.biz_status, " + " nvl(a.debit, 0), "
              + " nvl(a.credit, 0), " + " nvl(a.interest, 0), " + " a.org_id, "
              + " d.name, " + " a.id, " + " f201.credence_st "
              + "  from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + "  where a.org_id = c.id " + "   and c.orginfo_id = d.id "
              + "   and a.note_num = f201.sett_num(+) "
              + "   and a.biz_status = 5 " + "   and a.biz_type = 'F' "
              + orgiiid + "  and f201.reserve_c = '5' " + "   union all "
              + "  select distinct a.doc_num, " + "   a.biz_type, "
              + "   a.sett_date, " + "   a.biz_status, "
              + "   nvl(a.debit, 0), " + "   nvl(a.credit, 0), "
              + "   nvl(a.interest, 0), " + "   a.org_id, " + "   d.name, "
              + "   a.id, " + "   f201.credence_st "
              + "   from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + "   where a.org_id = c.id " + "   and c.orginfo_id = d.id "
              + "   and a.note_num = f201.sett_num(+) "
              + "  and a.biz_status = 5 " + "  and a.biz_type = 'D' "
              + "  and a.interest>0 " + orgiiid + "  and f201.reserve_c = '3' "
              + "     union all " + "   select distinct a.doc_num, "
              + "    a.biz_type, " + " a.sett_date, " + " a.biz_status, "
              + " nvl(a.debit, 0), " + " nvl(a.credit, 0), "
              + " nvl(a.interest, 0), " + " a.org_id, " + " d.name, "
              + " a.id, " + " f201.credence_st "
              + " from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + " where a.org_id = c.id " + "   and c.orginfo_id = d.id "
              + "  and a.note_num = f201.sett_num(+) "
              + "  and a.biz_status = 5 " + "   and a.biz_type = 'D' "
              + "  and a.interest=0" +

              orgiiid +

              "  and f201.reserve_c = '2') a1  ";

          Vector parameters = new Vector();
          String criterion = "";
          if (timeSt != null && !timeSt.trim().equals("") && timeEnd != null
              && !timeEnd.equals("")) {// 有开始日期结束日期
            criterion += " a1.sett_date >=? and a1.sett_date <=? and ";
            parameters.add(timeSt.trim());
            parameters.add(timeEnd.trim());
          }
          if (timeSt != null && !timeSt.equals("")
              && (timeEnd == null || timeEnd.trim().equals(""))) {// 有开始日期无结束日期
            criterion += " a1.sett_date >= ? and ";
            parameters.add(timeSt.trim());
          }
          if (timeEnd != null && !timeEnd.trim().equals("")
              && (timeSt == null || timeSt.trim().equals(""))) {// 无开始日期有结束日期
            criterion += " a1.sett_date <= ? and ";
            parameters.add(timeEnd.trim());
          }

          // if (orgId != null && !orgId.trim().equals("")) {
          // criterion += " a1.org_id = ? and ";
          // parameters.add(orgId.trim());
          // }
          // if (orgId_1 != null && !orgId_1.trim().equals("")) {
          // criterion += " a1.org_id = ? and ";
          // parameters.add(orgId_1.trim());
          // }

          if (orgName != null && !orgName.trim().equals("")) {
            criterion += " a1.name = ? and ";
            parameters.add(orgName.trim());
          }

          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy.trim();
          if (ob == null)
            ob = " a1.org_id,a1.sett_date,a1.id ";
          String od = order;
          if (od == null)
            od = "ASC";
          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();

          Iterator it = queryList.iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO = new CollFnComparisonOrgAccountDTO();
              if (obj[0] != null) {
                collFnComparisonOrgAccountDTO.setDoc_num(obj[0].toString());
              }
              collFnComparisonOrgAccountDTO.setBiz_type(obj[1].toString());
              if (obj[2] != null) {
                collFnComparisonOrgAccountDTO.setCollsett_date(obj[2]
                    .toString());
              }
              if (obj[3] != null) {
                collFnComparisonOrgAccountDTO.setBiz_status(obj[3].toString());
              }
              if (obj[4] != null) {
                collFnComparisonOrgAccountDTO.setDebit(new BigDecimal(obj[4]
                    .toString()));
              }
              if (obj[5] != null) {
                collFnComparisonOrgAccountDTO.setCredit(new BigDecimal(obj[5]
                    .toString()));
              }
              if (obj[6] != null) {
                collFnComparisonOrgAccountDTO.setDistorybalance(new BigDecimal(
                    obj[6].toString()));
              }
              if (obj[7] != null) {
                collFnComparisonOrgAccountDTO.setOrg_id(obj[7].toString());
              }
              if (obj[8] != null) {
                collFnComparisonOrgAccountDTO.setName(obj[8].toString());
              }
              if (obj[10] != null) {
                collFnComparisonOrgAccountDTO.setCaiw_type(obj[10].toString());
              }
              temp_list.add(collFnComparisonOrgAccountDTO);
            }
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryCollFnComparisonCountList(final String officeCode,
      final String bankId, final String orgId, final String orgId_1,
      final String orgName, final String timeSt, final String timeEnd,
      final SecurityInfo securityInfo, final String orgidst,
      final String orgidend) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String orgiiid = "";
          if (orgId == null || orgId.trim().equals("")) {
            orgiiid = " and a.org_id=" + orgId_1;
          } else {
            orgiiid = " and a.org_id=" + orgId;
          }
          String hql = "";
          if ((orgId == null || orgId.equals(""))
              && (orgId_1 == null || orgId_1.equals(""))) {
            orgiiid = "";
          }

          hql = "select a1.* " + "from (select distinct a.doc_num, "
              + "  a.biz_type, " + " a.sett_date, " + " a.biz_status, "
              + " nvl(a.debit, 0), " + " nvl(a.credit, 0), "
              + " nvl(a.interest, 0), " + " a.org_id, " + " d.name, "
              + " a.id, " + " f201.credence_st "
              + " from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + " where a.org_id = c.id " + "  and c.orginfo_id = d.id "
              + "  and a.note_num = f201.sett_num(+) "
              + " and a.biz_status = 5 " + orgiiid
              + " and f201.reserve_c is null " + " union all "
              + "  select distinct a.doc_num, " + "               a.biz_type, "
              + "              a.sett_date, " + "             a.biz_status, "
              + "            nvl(a.debit, 0), "
              + "           nvl(a.credit, 0), "
              + "          nvl(a.interest, 0), " + "         a.org_id, "
              + "        d.name, " + "       a.id, "
              + "      f201.credence_st "
              + "  from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + " where a.org_id = c.id " + "  and c.orginfo_id = d.id "
              + "  and a.note_num = f201.sett_num(+) "
              + " and a.biz_status = 5 "
              + " and a.biz_type in ('A', 'B', 'M') " + orgiiid
              + " and f201.reserve_c = '1' " + " union all "
              + " select distinct a.doc_num, " + " a.biz_type, "
              + " a.sett_date, " + " a.biz_status, " + " nvl(a.debit, 0), "
              + " nvl(a.credit, 0), " + " nvl(a.interest, 0), " + " a.org_id, "
              + " d.name, " + " a.id, " + " f201.credence_st "
              + " from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + " where a.org_id = c.id " + "  and c.orginfo_id = d.id "
              + "  and a.note_num = f201.sett_num(+) "
              + "  and a.biz_status = 5 " + "  and a.biz_type = 'C' " + orgiiid
              + "  and (f201.reserve_c = '9' or f201.reserve_c = '10') "
              + "  union all " + "  select distinct a.doc_num, "
              + "  a.biz_type, " + "   a.sett_date, " + "  a.biz_status, "
              + " nvl(a.debit, 0), " + " nvl(a.credit, 0), "
              + " nvl(a.interest, 0), " + " a.org_id, " + " d.name, "
              + " a.id, " + " f201.credence_st "
              + "   from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + "  where a.org_id = c.id " + "   and c.orginfo_id = d.id "
              + "  and a.note_num = f201.sett_num(+) "
              + "  and a.biz_status = 5 " + "  and a.biz_type ='E' " + orgiiid
              + "  and  f201.reserve_c = '4' " + "  union all "
              + "  select distinct a.doc_num, " + "    a.biz_type, "
              + " a.sett_date, " + " a.biz_status, " + " nvl(a.debit, 0), "
              + " nvl(a.credit, 0), " + " nvl(a.interest, 0), " + " a.org_id, "
              + " d.name, " + " a.id, " + " f201.credence_st "
              + "  from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + "  where a.org_id = c.id " + "   and c.orginfo_id = d.id "
              + "   and a.note_num = f201.sett_num(+) "
              + "   and a.biz_status = 5 " + "   and a.biz_type = 'F' "
              + orgiiid + "  and f201.reserve_c = '5' " + "   union all "
              + "  select distinct a.doc_num, " + "   a.biz_type, "
              + "   a.sett_date, " + "   a.biz_status, "
              + "   nvl(a.debit, 0), " + "   nvl(a.credit, 0), "
              + "   nvl(a.interest, 0), " + "   a.org_id, " + "   d.name, "
              + "   a.id, " + "   f201.credence_st "
              + "   from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + "   where a.org_id = c.id " + "   and c.orginfo_id = d.id "
              + "   and a.note_num = f201.sett_num(+) "
              + "  and a.biz_status = 5 " + "  and a.biz_type = 'D' "
              + "  and a.interest>0 " + orgiiid + "  and f201.reserve_c = '3' "
              + "     union all " + "   select distinct a.doc_num, "
              + "    a.biz_type, " + " a.sett_date, " + " a.biz_status, "
              + " nvl(a.debit, 0), " + " nvl(a.credit, 0), "
              + " nvl(a.interest, 0), " + " a.org_id, " + " d.name, "
              + " a.id, " + " f201.credence_st "
              + " from aa101 a, aa001 c, ba001 d, fn201 f201 "
              + " where a.org_id = c.id " + "   and c.orginfo_id = d.id "
              + "  and a.note_num = f201.sett_num(+) "
              + "  and a.biz_status = 5 " + "   and a.biz_type = 'D' "
              + "  and a.interest=0" +

              orgiiid +

              "  and f201.reserve_c = '2') a1  ";

          Vector parameters = new Vector();
          String criterion = "";
          if (timeSt != null && !timeSt.trim().equals("") && timeEnd != null
              && !timeEnd.equals("")) {// 有开始日期结束日期
            criterion += " a1.sett_date >=? and a1.sett_date <=? and ";
            parameters.add(timeSt.trim());
            parameters.add(timeEnd.trim());
          }
          if (timeSt != null && !timeSt.equals("")
              && (timeEnd == null || timeEnd.trim().equals(""))) {// 有开始日期无结束日期
            criterion += " a1.sett_date >= ? and ";
            parameters.add(timeSt.trim());
          }
          if (timeEnd != null && !timeEnd.trim().equals("")
              && (timeSt == null || timeSt.trim().equals(""))) {// 无开始日期有结束日期
            criterion += " a1.sett_date <= ? and ";
            parameters.add(timeEnd.trim());
          }

          // if (orgId != null && !orgId.trim().equals("")) {
          // criterion += " a1.org_id = ? and ";
          // parameters.add(orgId.trim());
          // }
          // if (orgId_1 != null && !orgId_1.trim().equals("")) {
          // criterion += " a1.org_id = ? and ";
          // parameters.add(orgId_1.trim());
          // }

          if (orgName != null && !orgName.trim().equals("")) {
            criterion += " a1.name = ? and ";
            parameters.add(orgName.trim());
          }

          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion
              + " order by a1.org_id ASC,a1.sett_date ASC,a1.id ASC ";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO = new CollFnComparisonOrgAccountDTO();
              if (obj[0] != null) {
                collFnComparisonOrgAccountDTO.setDoc_num(obj[0].toString());
              }
              collFnComparisonOrgAccountDTO.setBiz_type(obj[1].toString());
              if (obj[2] != null) {
                collFnComparisonOrgAccountDTO.setCollsett_date(obj[2]
                    .toString());
              }
              if (obj[3] != null) {
                collFnComparisonOrgAccountDTO.setBiz_status(obj[3].toString());
              }
              if (obj[4] != null) {
                collFnComparisonOrgAccountDTO.setDebit(new BigDecimal(obj[4]
                    .toString()));
              }
              if (obj[5] != null) {
                collFnComparisonOrgAccountDTO.setCredit(new BigDecimal(obj[5]
                    .toString()));
              }
              if (obj[6] != null) {
                collFnComparisonOrgAccountDTO.setDistorybalance(new BigDecimal(
                    obj[6].toString()));
              }
              if (obj[7] != null) {
                collFnComparisonOrgAccountDTO.setOrg_id(obj[7].toString());
              }
              if (obj[8] != null) {
                collFnComparisonOrgAccountDTO.setName(obj[8].toString());
              }
              if (obj[10] != null) {
                collFnComparisonOrgAccountDTO.setCaiw_type(obj[10].toString());
              }
              temp_list.add(collFnComparisonOrgAccountDTO);
            }
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 求期初余额&&上年结转
  public BigDecimal queryqcye(final String orgId, final String timeSt) {
    BigDecimal qcye = new BigDecimal(0.00);
    try {
      qcye = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select nvl(sum(a101.credit - a101.debit), 0) "
                  + "from aa101 a101 " + "where a101.org_id = ? "
                  + "and a101.sett_date < ? ";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, orgId);
              query.setParameter(1, timeSt);
              BigDecimal qcye = (BigDecimal) query.uniqueResult();
              return qcye;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return qcye;
  }

  /**
   * 汇缴时得到挂账余额的方法
   * 
   * @param orgId
   * @return
   * @author 付云峰
   */
  public BigDecimal queryOverPay(final String orgId) {
    BigDecimal overPay = new BigDecimal(0.00);
    try {
      overPay = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select sum(a101.credit-a101.debit) from aa101 a101 where a101.biz_type in ('C','J') and a101.biz_status=5 and a101.org_id=?";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, orgId);
              BigDecimal big = (BigDecimal) query.uniqueResult();
              return big;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return overPay;
  }

  /**
   * 判断是否有未入账的扎账
   * 
   * @param orgId
   * @return
   * @author 付云峰
   */
  public List queryIsOverPay(final String orgId) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select a301.id from aa301 a301 where a301.pay_status != 5 and a301.pay_type='D' and a301.org_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, orgId);
          List l = query.list();
          return l;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public int queryPaymentmonthBankDTODNLJ2(final String office,
      final String startDate, final String bizDate, final String bankId) {
    String str = "";
    try {
      str = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // String hql = " select nvl(count(distinct(t.org_id)), 0),
          // nvl(count(distinct(s.emp_id)), 0), nvl(sum(nvl(s.credit,0) -
          // nvl(s.debit,0)), 0) from aa101 t, aa102 s where t.biz_type in
          // ('A','B','C','M') and t.id = s.org_flow_id and t.biz_status = '5'";
          String hql = " select  " + " nvl(count(distinct(s.emp_id)), 0) "
              + " from aa101 t, aa102 s where t.biz_type in ('A','B','C','M') "
              + " and t.id = s.org_flow_id and t.biz_status = '5'";
          Vector parameters = new Vector();
          String criterion = "";

          if (office != null && !"".equals(office)) {
            criterion += " t.officecode = ?  and ";
            parameters.add(office);
          }

          if (bizDate != null && !"".equals(bizDate)) {
            criterion += " t.sett_date between ? and ?  and ";
            parameters.add(startDate);
            parameters.add(bizDate);

          }

          if (bankId != null && !"".equals(bankId)) {
            criterion += " t.moneybank = ?  and ";
            parameters.add(bankId);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

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
    return Integer.parseInt(str);
  }

  /**
   * 办事处查银行
   * 
   * @param offcie
   * @return
   */
  public List queryBankIdByOffice(final String office) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select t.coll_bank_id from bb105 t where t.office= '"
              + office + "'";
          Query query = session.createSQLQuery(hql);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 办事处名称
   * 
   * @param office
   * @param bizDate
   * @param bankId
   * @return
   */
  public String queryOfficeName(final String officeId) {
    String name = "";
    try {
      name = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select t.name from bb101 t where t.id = '" + officeId
              + "'";
          Query query = session.createSQLQuery(hql);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return name;
  }

  /**
   * 统计查询 -- 缴存提取统计 --公积金缴存年报表 上月末余额 根据银行ID和业务日期查询户数人数金额
   * 
   * @author yqf 20080918
   * @param office
   * @param bizDate
   * @param bankId
   * @return
   */
  public PaymentyearBankDTO queryPaymentyear(final String office,
      final String bizDate, final String bankId) {
    PaymentyearBankDTO paymentyearBankDTO = null;
    try {
      paymentyearBankDTO = (PaymentyearBankDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              // String hql = " select nvl(count(distinct(t.org_id)), 0),"+
              // " nvl(count(distinct(s.emp_id)), 0),"+
              // " nvl(sum(nvl(s.credit,0) - nvl(s.debit,0)), 0)"+
              // " from aa101 t, aa102 s"+
              // " where t.biz_type in ('A', 'B','M','C')"+
              // " and t.id = s.org_flow_id"+
              // " and t.biz_status = '5'";
              // String hql = " select nvl(count(distinct(t.org_id)), 0), "+
              // " nvl(sum(t.credit-t.debit), 0) "+
              // " from aa101 t "+
              // " where t.biz_type in ('A', 'B','M','C') "+
              // " and t.biz_status = '5' "+
              // " union "+
              // " select nvl(count(distinct(s.emp_id)), 0) "+
              // " from aa101 t, aa102 s"+
              // " where t.biz_type in ('A', 'B','M','C')"+
              // " and t.id = s.org_flow_id"+
              // " and t.biz_status = '5'";
              String hql = " select nvl(count(distinct(t.org_id)), 0), "
                  + " nvl(sum(t.credit-t.debit), 0) " + " from aa101 t "
                  + " where t.biz_type in ('A', 'B','M','C') "
                  + " and t.biz_status = '5' ";
              Vector parameters = new Vector();
              String criterion = "";

              if (office != null && !"".equals(office)) {
                criterion += " t.officecode = ?  and ";
                parameters.add(office);
              }

              if (bizDate != null && !"".equals(bizDate)) {
                criterion += " t.sett_date like ?  and ";
                parameters.add("%" + bizDate + "%");
              }

              if (bankId != null && !"".equals(bankId)) {
                criterion += " t.moneybank = ?  and ";
                parameters.add(bankId);
              }

              if (criterion.length() != 0)
                criterion = " and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;

              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              Iterator it = query.list().iterator();
              PaymentyearBankDTO dto = new PaymentyearBankDTO();
              Object obj[] = null;
              while (it.hasNext()) {
                obj = (Object[]) it.next();
                if (obj[0] != null) {
                  dto.setHushu(Integer.parseInt(obj[0].toString()));
                }
                if (obj[1] != null) {
                  dto.setJiner(new BigDecimal(obj[1].toString()));
                }
              }
              return dto;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paymentyearBankDTO;
  }

  public int queryCountEmpId(final String office, final String bizDate,
      final String bankId) {
    Integer count = new Integer(0);
    String str = "";
    try {
      str = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select nvl(count(distinct(s.emp_id)), 0)"
              + " from aa101 t,aa102 s " + " where t.id=s.org_flow_id"
              + " and t.biz_type in ('A', 'B', 'M', 'C')"
              + " and t.biz_status = '5' ";
          Vector parameters = new Vector();
          String criterion = "";

          if (office != null && !"".equals(office)) {
            criterion += " t.officecode = ?  and ";
            parameters.add(office);
          }

          if (bizDate != null && !"".equals(bizDate)) {
            criterion += " t.sett_date like ?  and ";
            parameters.add("%" + bizDate + "%");
          }

          if (bankId != null && !"".equals(bankId)) {
            criterion += " t.moneybank = ?  and ";
            parameters.add(bankId);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

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
    if (str == null || "".equals(str))
      str = "0";

    return Integer.parseInt(str);
  }

  /**
   * jj 查询职工业务流水
   * 
   * @param empid
   * @param empname
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryEmpHAFAccountFlowList_jj(final Serializable empid,
      final String empname, final String orderBy, final String order,
      final int start, final int pageSize, final int page,
      final SecurityInfo securityInfo, final String orgbusinessflowHeadID)
      throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from EmpHAFAccountFlow empHAFAccountFlow where empHAFAccountFlow.orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (empid != null && !empid.equals("")) {
            criterion += " empHAFAccountFlow.empId = ? and ";
            parameters.add(new Integer(empid.toString()));
          }
          if (empname != null && !empname.equals("")) {
            criterion += " empHAFAccountFlow. empName like  ? escape '/'  and ";
            parameters.add("%" + empname + "%");
          }
          if (orgbusinessflowHeadID != null
              && !orgbusinessflowHeadID.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.id = ?  and";
            parameters.add(new Integer(orgbusinessflowHeadID));

          }
          String ob = orderBy;
          if (ob == null)
            ob = " empHAFAccountFlow.id ";

          String od = order;
          if (od == null)
            od = "DESC";
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + "order by " + ob + " " + od;

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
          return queryList;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public String findReason(final String bizid, final String empidd) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = " select a307.pick_reason from aa307 a307  where a307.pickhead_id=? and  a307.emp_id=? ";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, new Integer(bizid));
            query.setParameter(1, new Integer(empidd));
            return query.uniqueResult().toString();
          }
        });

    return id;
  }

  /**
   * jj 查询职工流水记录数
   * 
   * @param empid
   * @param empname
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public int queryEmpHAFAccountFlowCount_jj(final Serializable empid,
      final String empname, final SecurityInfo securityInfo,
      final String orgbusinessflowHeadID) throws NumberFormatException,
      Exception {
    int count = 0;
    Integer iCount = null;
    try {

      iCount = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select count(empHAFAccountFlow.id) from EmpHAFAccountFlow empHAFAccountFlow where empHAFAccountFlow.orgHAFAccountFlow.org.id "
                  + securityInfo.getGjjSecurityHqlSQL();
              Vector parameters = new Vector();
              String criterion = "";

              if (empid != null && !empid.equals("")) {
                criterion += " empHAFAccountFlow.empId = ? and ";
                parameters.add(new Integer(empid.toString()));
              }
              if (empname != null && !empname.equals("")) {
                criterion += " empHAFAccountFlow. empName like  ? escape '/'  and ";
                parameters.add("%" + empname + "%");
              }
              if (orgbusinessflowHeadID != null
                  && !orgbusinessflowHeadID.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.id = ?  and";
                parameters.add(new Integer(orgbusinessflowHeadID));

              }
              if (criterion.length() != 0)
                criterion = " and  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              return query.uniqueResult();
            }
          });
      count = iCount.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return count;
  }

  public Object queryByCriterionsTranInTail(final String empid,
      final String headid) {

    Object tranInTail = null;
    try {
      tranInTail = (Object) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select tranInTail.name from TranInTail tranInTail  ";
              Vector parameters = new Vector();
              String criterion = "";
              if (empid != null) {
                criterion += "tranInTail.empId = ? and ";
                parameters.add(new Integer(empid));
              }
              if (headid != null) {
                criterion += "tranInTail.tranInHead.id = ? and ";
                parameters.add(new Integer(headid));
              }
              if (criterion.length() != 0)
                criterion = " where  "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;

              Query query0 = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query0.setParameter(i, parameters.get(i));
              }
              return query0.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tranInTail;
  }

  /**
   * jj 查询合计
   * 
   * @param empid
   * @param empname
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryEmpHAFAccountFlowTotal_jj(final Serializable empid,
      final String empname, final SecurityInfo securityInfo,
      final String orgbusinessflowHeadID) throws NumberFormatException,
      Exception {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List temp_list = new ArrayList();
          String hql = "select count(empHAFAccountFlow.id),sum(empHAFAccountFlow.debit+empHAFAccountFlow.credit),sum(empHAFAccountFlow.interest)"
              + " from EmpHAFAccountFlow empHAFAccountFlow where empHAFAccountFlow.orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (empid != null && !empid.equals("")) {
            criterion += " empHAFAccountFlow.empId = ? and ";
            parameters.add(new Integer(empid.toString()));
          }
          if (empname != null && !empname.equals("")) {
            criterion += " empHAFAccountFlow. empName like  ? escape '/'  and ";
            parameters.add("%" + empname + "%");
          }
          if (orgbusinessflowHeadID != null
              && !orgbusinessflowHeadID.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.id = ?  and";
            parameters.add(new Integer(orgbusinessflowHeadID));

          }

          if (criterion.length() != 0)
            criterion = " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.iterate();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            EmpOperationFlowTotalDTO empOperationFlowTotalDTO = new EmpOperationFlowTotalDTO();
            empOperationFlowTotalDTO.setCounts(obj[0].toString());
            if (empOperationFlowTotalDTO.getCounts().equals("0")) {
              empOperationFlowTotalDTO.setSumMoney("0.00");
              empOperationFlowTotalDTO.setSumInterest("0.00");
            } else {
              empOperationFlowTotalDTO.setSumMoney(obj[1].toString());
              empOperationFlowTotalDTO.setSumInterest(obj[2].toString());
            }
            temp_list.add(empOperationFlowTotalDTO);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /*
   * 根据AA101的主键ID查询转出业务的转入是否已经入账
   */
  public String isTansInFive(final Integer id) {

    BigDecimal transInStatus = new BigDecimal(0);
    try {
      transInStatus = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select aa311.tran_status from aa311 where aa311.tran_out_head_id in(select aa101.biz_id from aa101 where aa101.id=? ) ";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, id);
              Object obj = null;
              Iterator it = query.list().iterator();
              if (it.hasNext())
                obj = (Object) it.next();
              return obj;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (transInStatus == null) {
      return null;
    }
    return transInStatus.toString();
  }

  /**
   * 杨光 查询公积金还贷，归集提取信息
   * 
   * @param empid
   * @param empname
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryLoanBackByFund(final SecurityInfo securityInfo,
      final String bankid, final String beginDate, final String endDate,
      final int start, final int pageSize) throws NumberFormatException,
      Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List temp_list = new ArrayList();
          String sql = " select a1.id," + " b1.name orgname," + " a307.emp_id,"
              + " b2.name empname,"
              + " a307.pick_pre_balance + a307.pick_cur_balance,"
              + " a307.contract_id," + " a101.sett_date," + " a101.note_num"
              + " from aa101 a101, aa307 a307,"
              + " aa001 a1, ba001 b1, ba002 b2,"
              + " aa002 a2 where a1.orginfo_id = b1.id"
              + " and a101.org_id = a1.id"
              + " and a307.pickhead_id = a101.biz_id"
              + " and b2.id = a2.emp_info_id" + " and a2.org_id = a1.id"
              + " and a2.id = a307.emp_id" + " and a101.biz_status = 5"
              + " and a101.biz_type = 'D'" + " and a101.org_id "
              + securityInfo.getGjjSecuritySQL() + " and a307.pick_reason = 2 ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bankid != null && !bankid.equals("")) {
            criterion += " b1.collection_bank_id = ? and";
            parameters.add(bankid);
          }
          if (beginDate != null && !beginDate.equals("")) {
            criterion += " a101.sett_date >= ? and ";
            parameters.add(beginDate);
          }
          if (endDate != null && !endDate.equals("")) {
            criterion += " a101.sett_date <= ? and ";
            parameters.add(endDate);
          }
          if (criterion.length() != 0)
            criterion = " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion;

          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            LoanBackByFundDTO loanBackByFundDTO = new LoanBackByFundDTO();
            loanBackByFundDTO.setOrgId(new Integer(obj[0].toString()));
            loanBackByFundDTO.setOrgName(obj[1].toString());
            loanBackByFundDTO.setKouEmpId(new Integer(obj[2].toString()));
            loanBackByFundDTO.setKouEmpName(obj[3].toString());
            loanBackByFundDTO.setPickMoney(obj[4].toString());
            loanBackByFundDTO.setContractId(obj[5].toString());
            loanBackByFundDTO.setBizDate(obj[6].toString());
            temp_list.add(loanBackByFundDTO);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 杨光 查询公积金还贷，归集提取信息 所有信息
   * 
   * @param empid
   * @param empname
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryLoanBackByFundAll(final SecurityInfo securityInfo,
      final String bankid, final String beginDate, final String endDate)
      throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          List temp_list = new ArrayList();
          String sql = " select a1.id as org_id," + " b1.name as orgname,"
              + " a307.emp_id," + " b2.name as empname,"
              + " a307.pick_pre_balance + a307.pick_cur_balance as debit,"
              + " a307.contract_id," + " a101.SETT_DATE as biz_date"
              + " from AA101 a101, AA307 a307,"
              + " AA001 a1, BA001 b1, ba002 b2,"
              + " aa002 a2 where a101.BIZ_TYPE = 'D'"
              + " and a1.ORGINFO_ID = b1.ID" + " and a101.ORG_ID = a1.ID"
              + " and a307.PICKHEAD_ID = a101.BIZ_ID"
              + " and a101.BIZ_STATUS = 5" + " and a101.ORG_ID"
              + securityInfo.getGjjSecuritySQL()
              + " and b2.id = a2.emp_info_id" + " and a2.org_id = a1_.id"
              + " and a2.id = a307.emp_id" + " and a307.PICK_REASON = 2 ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bankid != null && !bankid.equals("")) {
            criterion += " b1.COLLECTION_BANK_ID = ? and";
            parameters.add(bankid);
          }
          if (beginDate != null && !beginDate.equals("")) {
            criterion += " a101.SETT_DATE >= ? and ";
            parameters.add(beginDate);
          }
          if (endDate != null && !endDate.equals("")) {
            criterion += " a101.SETT_DATE <= ? and ";
            parameters.add(endDate);
          }
          if (criterion.length() != 0)
            criterion = " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion;

          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            LoanBackByFundDTO loanBackByFundDTO = new LoanBackByFundDTO();
            loanBackByFundDTO.setOrgId(new Integer(obj[0].toString()));
            loanBackByFundDTO.setOrgName(obj[1].toString());
            loanBackByFundDTO.setKouEmpId(new Integer(obj[2].toString()));
            loanBackByFundDTO.setKouEmpName(obj[3].toString());
            loanBackByFundDTO.setPickMoney(obj[4].toString());
            loanBackByFundDTO.setContractId(obj[5].toString());
            loanBackByFundDTO.setBizDate(obj[6].toString());
            temp_list.add(loanBackByFundDTO);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 杨光 查询公积金还贷，归集提取信息 所有信息
   * 
   * @param empid
   * @param empname
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryLoanBackByFundData(final SecurityInfo securityInfo,
      final String bankid, final String beginDate, final String endDate)
      throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select count(a307.id),"
              + " nvl(sum(a307.pick_pre_balance + a307.pick_cur_balance),0) as debit"
              + " from AA101 a101, AA307 a307,"
              + " AA001 a1, BA001 b1, ba002 b2,"
              + " aa002 a2 where a101.BIZ_TYPE = 'D'"
              + " and a1.ORGINFO_ID = b1.ID" + " and a101.ORG_ID = a1.ID"
              + " and a307.PICKHEAD_ID = a101.BIZ_ID"
              + " and a101.BIZ_STATUS = 5" + " and a101.ORG_ID "
              + securityInfo.getGjjSecuritySQL()
              + " and b2.id = a2.emp_info_id" + " and a2.org_id = a1.id"
              + " and a2.id = a307.emp_id" + " and a307.PICK_REASON = 2 ";
          Vector parameters = new Vector();
          String criterion = "";
          if (bankid != null && !bankid.equals("")) {
            criterion += " b1.COLLECTION_BANK_ID = ? and";
            parameters.add(bankid);
          }
          if (beginDate != null && !beginDate.equals("")) {
            criterion += " a101.SETT_DATE >= ? and ";
            parameters.add(beginDate);
          }
          if (endDate != null && !endDate.equals("")) {
            criterion += " a101.SETT_DATE <= ? and ";
            parameters.add(endDate);
          }
          if (criterion.length() != 0)
            criterion = " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          sql = sql + criterion;

          Query query = session.createSQLQuery(sql);
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

  public String queryAA306ReserveaA(final String doc_num, final String note_num) {
    return (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select t.reservea_a from aa306 t where t.doc_num = ? and t.note_num = ?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, doc_num);
        query.setParameter(1, note_num);
        return query.uniqueResult();
      }
    });
  }

  public String queryAA306ReserveaB(final String doc_num, final String note_num) {
    return (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select t.reservea_b from aa306 t where t.doc_num = ? and t.note_num = ?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, doc_num);
        query.setParameter(1, note_num);
        return query.uniqueResult();
      }
    });
  }
}
