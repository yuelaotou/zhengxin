package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.dto.QueryOperationLogDTO;

/**
 * 业务活动日志PL020
 * 
 *@author 李娟
 *2007-9-13
 */
public class PlBizActiveLogDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * @param id
   * @return
   */
  public PlBizActiveLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (PlBizActiveLog) getHibernateTemplate().get(PlBizActiveLog.class,id);    
  }
  /**
   * 插入记录
   * 
   * @param plBizActiveLog
   * @return
   */
  public Serializable insert(PlBizActiveLog plBizActiveLog) {
    Serializable id = null;
    try {
      Validate.notNull(plBizActiveLog);
      id = getHibernateTemplate().save(plBizActiveLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 挂账维护
   * @author 郭婧平
   * 2007-9-28
   * 根据bizid和type=13删除记录
   */
  public void deletePlBizActiveLog(final String flowHeadId)
  {
    try{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql="delete from PlBizActiveLog plBizActiveLog where plBizActiveLog.bizid= ? and plBizActiveLog.type=13 ";
          Query query=session.createQuery(sql);
          query.setParameter(0,new BigDecimal(flowHeadId));
          return new Integer(query.executeUpdate());
          }
        });
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * 业务复核
   * @author 吴迪
   * 2007-10-8
   * 根据bizid和type和action=5删除记录
   */
  public void deletePlBizActiveLogByCriterions_WU(final String flowHeadId,final String type)
  {
    try{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql="delete from PlBizActiveLog plBizActiveLog where plBizActiveLog.bizid= ? and plBizActiveLog.type=?  and plBizActiveLog.action=5 ";
          Query query=session.createQuery(sql);
          query.setParameter(0,new BigDecimal(flowHeadId));
          query.setParameter(1,type);
          return new Integer(query.executeUpdate());
          }
        });
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  
  /**
   * 回收维护删除
   * jj
   * @param flowHeadId
   * @param action
   * 银行代扣导出删除
   */
  public void deletePlBizActiveLog_LJ(final String headId,final String action){
    try{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql="delete from PlBizActiveLog plBizActiveLog where plBizActiveLog.bizid= ? and plBizActiveLog.action = ? ";
          Query query=session.createQuery(sql);
          query.setParameter(0,new BigDecimal(headId));
          query.setParameter(1,action);
          query.executeUpdate();
          return null;
          }
        });
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  /**
   * 办理回收删除
   * jj
   * @param flowHeadId
   * @param action
   */
  public void deletePlBizActiveLog_LJ(final String headId){
    try{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql="delete from PlBizActiveLog plBizActiveLog where plBizActiveLog.bizid= ? ";
          Query query=session.createQuery(sql);
          query.setParameter(0,new BigDecimal(headId));
          query.executeUpdate();
          return null;
          }
        });
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  /**
   * 保证金登记 删除业务活动日志PL020
   * @author 王野 2007-10-04
   * 根据bizid=PL202.FLOW_HEAD_ID和type=14删除记录
   */
  public void deletePlBizActiveLogByFlowHeadId_wy(final String flowHeadId)
  {
    try{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql="delete from PlBizActiveLog plBizActiveLog where plBizActiveLog.bizid= ? and plBizActiveLog.type=14 ";
          Query query=session.createQuery(sql);
          query.setParameter(0,new BigDecimal(flowHeadId));
          return new Integer(query.executeUpdate());
          }
        });
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  
  /**
   * 根据flowHeadId与type删除PL020
   * @param flowHeadId
   * @param type
   * @author 付云峰
   */
  public void deletePlBizActiveLogByFlowHeadId_FYF(final String flowHeadId, final String type)
  {
    try{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql="delete from PlBizActiveLog plBizActiveLog where plBizActiveLog.bizid=? and plBizActiveLog.type=? ";
          Query query=session.createQuery(sql);
          query.setParameter(0,new BigDecimal(flowHeadId));
          query.setParameter(1,type);
          return new Integer(query.executeUpdate());
          }
        });
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  
  /**
   * 根据flowHeadId与bizSt删除PL020
   * @param flowHeadId
   * @param bizSt
   * @author 吴迪
   */
  public void deletePlBizActiveLogByFlowHeadId_WD(final String flowHeadId, final String bizSt)
  {
    try{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql="delete from PlBizActiveLog plBizActiveLog where plBizActiveLog.bizid=? and plBizActiveLog.action=? ";
          Query query=session.createQuery(sql);
          query.setParameter(0,new BigDecimal(flowHeadId));
          query.setParameter(1,bizSt);
          return new Integer(query.executeUpdate());
          }
        });
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * 查询日志文件L020
   * @author 李鹏
   */
  public List queryByCriterions(final int start, final String orderBy, final String order, final int pageSize, final int page,
      final String bizStatus,final String bizType,final List operator,final String beginTime, final String endTime,final SecurityInfo securityInfo
      )
  {

    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select distinct plBizActiveLog.bizid, plBizActiveLog.action,plBizActiveLog.Op_Time, plBizActiveLog.operator,plBizActiveLog.type" +
              "  from pl020  plBizActiveLog, pl202   loanFlowHead, pl203   loanFlowTail " +
              "where loanFlowHead.Flow_Head_Id = loanFlowTail.Flow_Head_Id and loanFlowHead.Flow_Head_Id = plBizActiveLog.bizid and ";
          String criterion = "";
          
          Vector parameters = new Vector();
          String ob = orderBy;
          if (ob == null && ob=="")
            ob = " plBizActiveLog.bizid ";

          String od = order;
          if (od == null)
            od = " DESC";

          if (bizStatus != null && !"".equals(bizStatus)) {
            criterion += " plBizActiveLog.action = ? and ";
            parameters.add(bizStatus);
          }

          if (bizType != null && !"".equals(bizType)) {
            criterion += " plBizActiveLog.type  = ? and ";
            parameters.add(bizType);
          }

//          if (operator != null && !"".equals(operator)) {
//            criterion += " plBizActiveLog.operator = ? and ";
//            parameters.add(operator);
//          }
          if (operator != null && operator.size() > 0) {
            criterion += "( ";
                  for (int i = 0; i < operator.size(); i++) {
                    criterion += "plBizActiveLog.operator  = ? or ";
                    parameters.add(operator.get(i).toString());
                  }
                  criterion = criterion.substring(0, criterion.lastIndexOf("or"));
                  criterion += ") and";
                }

          if (beginTime != null && !beginTime.equals("") && endTime != null
              && !endTime.equals("")) {
            criterion += " (to_char(plBizActiveLog.Op_Time,'yyyymmdd HH24:mi:ss') between ?  and ?) and ";
            parameters.add(beginTime+" 00:00:00");
            parameters.add(endTime+" 23:00:00");
          }
          if (criterion.length() != 0)
            criterion = 
                " loanFlowHead.Loan_Bank_Id "
                + securityInfo.getDkSecuritySQL() + " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + od;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (obj != null) {
              QueryOperationLogDTO queryOperationLogDTO = new QueryOperationLogDTO();
              if (obj[0] != null) {
                queryOperationLogDTO.setBizid(obj[0].toString());
              }
              if (obj[1] != null) {
                queryOperationLogDTO.setAction(obj[1].toString());
              }
              if (obj[2] != null) {
                if(obj[2].toString().length()>10)
                  queryOperationLogDTO.setOpTime(obj[2].toString().substring(0, 10));
                else 
                  queryOperationLogDTO.setOpTime(obj[2].toString());
              }
              if (obj[3] != null) {
                queryOperationLogDTO.setOperator(obj[3].toString());
              }
              if (obj[4] != null) {
                queryOperationLogDTO.setType(obj[4].toString());
              }
              if (obj[4] != null) {
                queryOperationLogDTO.setTypenum(obj[4].toString());
              }
              try {
                queryOperationLogDTO.setAction(BusiTools.getBusiValue(Integer
                    .parseInt("" + queryOperationLogDTO.getAction()),
                    BusiConst.PLBUSINESSSTATE));
              } catch (Exception e) {
                e.printStackTrace();
              }
              try {
                queryOperationLogDTO.setType(BusiTools.getBusiValue(Integer
                    .parseInt("" + queryOperationLogDTO.getType()),
                    BusiConst.PLBUSINESSTYPE));
              } catch (Exception e) {
                e.printStackTrace();
              }
              temp_list.add(queryOperationLogDTO);
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
  
  /**
   * 查询日志文件L020
   * @author 李鹏
   */
  public List queryPrintListByCriterions(final int start, final String orderBy, final String order, final int pageSize, final int page,
      final String bizStatus,final String bizType,final List operator,final String beginTime, final String endTime,final SecurityInfo securityInfo)
  {

    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select distinct plBizActiveLog.bizid, plBizActiveLog.action,plBizActiveLog.Op_Time, plBizActiveLog.operator,plBizActiveLog.type" +
              "  from pl020  plBizActiveLog, pl202   loanFlowHead, pl203   loanFlowTail " +
              "where loanFlowHead.Flow_Head_Id = loanFlowTail.Flow_Head_Id and loanFlowHead.Flow_Head_Id = plBizActiveLog.bizid and ";
          String criterion = "";
          
          Vector parameters = new Vector();
          String ob = orderBy;
          if (ob == null && ob=="")
            ob = " plBizActiveLog.bizid ";

          String od = order;
          if (od == null)
            od = " DESC";

          if (bizStatus != null && !"".equals(bizStatus)) {
            criterion += " plBizActiveLog.action = ? and ";
            parameters.add(bizStatus);
          }

          if (bizType != null && !"".equals(bizType)) {
            criterion += " plBizActiveLog.type  = ? and ";
            parameters.add(bizType);
          }

//          if (operator != null && !"".equals(operator)) {
//            criterion += " plBizActiveLog.operator = ? and ";
//            parameters.add(operator);
//          }
          if (operator != null && operator.size() > 0) {
            criterion += "( ";
                  for (int i = 0; i < operator.size(); i++) {
                    criterion += "plBizActiveLog.operator  = ? or ";
                    parameters.add(operator.get(i).toString());
                  }
                  criterion = criterion.substring(0, criterion.lastIndexOf("or"));
                  criterion += ") and";
                }

          if (beginTime != null && !beginTime.equals("") && endTime != null
              && !endTime.equals("")) {
            criterion += " (to_char(plBizActiveLog.Op_Time,'yyyymmdd HH24:mi:ss') between ?  and ?) and ";
            parameters.add(beginTime+" 00:00:00");
            parameters.add(endTime+" 23:00:00");
          }
          if (criterion.length() != 0)
            criterion = 
                " loanFlowHead.Loan_Bank_Id "
                + securityInfo.getDkSecuritySQL() + " and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + od;

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
              QueryOperationLogDTO queryOperationLogDTO = new QueryOperationLogDTO();
              if (obj[0] != null) {
                queryOperationLogDTO.setBizid(obj[0].toString());
              }
              if (obj[1] != null) {
                queryOperationLogDTO.setAction(obj[1].toString());
              }
              if (obj[2] != null) {
                if(obj[2].toString().length()>10)
                  queryOperationLogDTO.setOpTime(obj[2].toString().substring(0, 10));
                else 
                  queryOperationLogDTO.setOpTime(obj[2].toString());
              }
              if (obj[3] != null) {
                queryOperationLogDTO.setOperator(obj[3].toString());
              }
              if (obj[4] != null) {
                queryOperationLogDTO.setType(obj[4].toString());
              }
              if (obj[4] != null) {
                queryOperationLogDTO.setTypenum(obj[4].toString());
              }
              try {
                queryOperationLogDTO.setAction(BusiTools.getBusiValue(Integer
                    .parseInt("" + queryOperationLogDTO.getAction()),
                    BusiConst.PLBUSINESSSTATE));
              } catch (Exception e) {
                e.printStackTrace();
              }
              try {
                queryOperationLogDTO.setType(BusiTools.getBusiValue(Integer
                    .parseInt("" + queryOperationLogDTO.getType()),
                    BusiConst.PLBUSINESSTYPE));
              } catch (Exception e) {
                e.printStackTrace();
              }
              temp_list.add(queryOperationLogDTO);
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
  
}
