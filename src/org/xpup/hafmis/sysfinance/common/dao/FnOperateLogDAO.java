package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;
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
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.dto.QueryOperationLogFindDTO;
import org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.dto.QueryOperationLogListDTO;
import org.xpup.security.common.domain.User;

public class FnOperateLogDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public FnOperateLog queryById(Serializable id) {
    Validate.notNull(id);
    return (FnOperateLog) getHibernateTemplate().get(
        FnOperateLog.class, id);
  }

  /**
   * 插入记录
   * 
   * @param BookParameter
   * @return
   */
  public Serializable insert(FnOperateLog fnOperateLog) {
    Serializable id = null;
    try {
      Validate.notNull(fnOperateLog);
      id = getHibernateTemplate().save(fnOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  public List queryHafoperatorLog(final String opmodle,final String oper,final String starttime,final String endtime,final String opaction,
      final String orderBy, final String order,final int start, final int pageSize,final int page,final SecurityInfo securityInfo) {
    List hafOperateLog = new ArrayList();
    hafOperateLog = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from FnOperateLog hafOperateLog ";
            Vector parameters = new Vector();
            String criterion = "";
            if (opmodle != null && !opmodle.equals("")) {
              criterion += "hafOperateLog.opModel = ?  and ";
              parameters.add(opmodle);
            }
            if (oper != null && !oper.equals("")) {
              criterion += "hafOperateLog.operator = ?  and ";
              parameters.add(oper);
            } else {
              criterion+=" hafOperateLog.operator in (";
              List operList = securityInfo.getUserList();
              User user = null;
              Iterator itr2 = operList.iterator();
              while (itr2.hasNext()) {
                user = (User) itr2.next();
                criterion+="'"+user.getUsername()+"',";
              }criterion = criterion.substring(0, criterion.lastIndexOf(","));
              criterion += ") and ";
            }

            if (starttime != null && !starttime.equals("")) {
              criterion += "to_char(hafOperateLog.opTime,'yyyymmdd') >= ?  and ";
              parameters.add(starttime);
            }
            if (endtime != null && !endtime.equals("")) {
              criterion += "to_char(hafOperateLog.opTime,'yyyymmdd') <= ?  and ";
              parameters.add(endtime);
            }
            if (opaction != null && !opaction.equals("")) {
              criterion += "hafOperateLog.opButton = ?  and ";
              parameters.add(opaction);
            }

            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            
            String ob = orderBy;
            if (ob == null)
              ob = " hafOperateLog.id ";

            String od = order;
            if (od == null)
              od = "DESC";
            
            hql = hql + criterion+"  order by " + ob + " " + od;
            Query query = session.createQuery(hql);
            
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }       
            return query.list();
          }
        });
    return hafOperateLog;
  }
  
  public List queryHafoperatorLogCount(final String opmodle,final String oper,final String starttime,final String endtime,final String opaction,
      final SecurityInfo securityInfo) {
    List hafOperateLog = new ArrayList();
    hafOperateLog = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from FnOperateLog hafOperateLog ";
            Vector parameters = new Vector();
            String criterion = "";
            if (opmodle != null && !opmodle.equals("")) {
              criterion += "hafOperateLog.opModel = ?  and ";
              parameters.add(opmodle);
            }
            if (oper != null && !oper.equals("")) {
              criterion += "hafOperateLog.operator = ?  and ";
              parameters.add(oper);
            } else {
              criterion+=" hafOperateLog.operator in (";
              List operList = securityInfo.getUserList();
              User user = null;
              Iterator itr2 = operList.iterator();
              while (itr2.hasNext()) {
                user = (User) itr2.next();
                criterion+="'"+user.getUsername()+"',";
              }criterion = criterion.substring(0, criterion.lastIndexOf(","));
              criterion += ") and ";
            }

            if (starttime != null && !starttime.equals("")) {
              criterion += "to_char(hafOperateLog.opTime,'yyyymmdd') >= ?  and ";
              parameters.add(starttime);
            }
            if (endtime != null && !endtime.equals("")) {
              criterion += "to_char(hafOperateLog.opTime,'yyyymmdd') <= ?  and ";
              parameters.add(endtime);
            }
            if (opaction != null && !opaction.equals("")) {
              criterion += "hafOperateLog.opButton = ?  and ";
              parameters.add(opaction);
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
    return hafOperateLog;
  }
  /**
   * 查询业务日志表内容的List
   * @param dto
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @return 付云峰
   */
  public List queryOperationLogList(final QueryOperationLogFindDTO dto,
      final int start, final String orderBy, final String order,
      final int pageSize, final int page, final String bookId,final List userlist) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select f310.bizactivity_log_id,f310.credence_type,f310.action,f310.op_time,f310.operator,f310.credence_num,f310.credence_date,f310.office from fn310 f310 where f310.book_id="+bookId;
          String criterion = "";
          Vector parameters = new Vector();

          String credenceType = dto.getCredenceType().trim();
          if (credenceType != null && !"".equals(credenceType)) {
            criterion += " and f310.credence_type=?";
            parameters.add(credenceType);
          }

          String operator = dto.getOperator().trim();
          if (operator != null && !"".equals(operator)) {
            criterion += " and f310.operator=?";
            parameters.add(operator);
          }else {
            criterion+=" and f310.operator in (";
            List operList = userlist;
            User user = null;
            Iterator itr2 = operList.iterator();
            while (itr2.hasNext()) {
              user = (User) itr2.next();
              criterion+="'"+user.getUsername()+"',";
            }criterion = criterion.substring(0, criterion.lastIndexOf(","));
            criterion += ")";
          }

          String action = dto.getAction().trim();
          if (action != null && !"".equals(action)) {
            criterion += " and f310.action=?";
            parameters.add(action);
          }
          
          if (dto.getCredenceDateStart() != null
              && !dto.getCredenceDateStart().equals("")
              && dto.getCredenceDateEnd() != null
              && !dto.getCredenceDateEnd().equals("")) {
            criterion += " and (to_char(f310.op_time,'yyyymmdd') between ? and ?)";
            parameters.add(dto.getCredenceDateStart());
            parameters.add(dto.getCredenceDateEnd());
          } else if (dto.getCredenceDateStart() != null
              && !dto.getCredenceDateStart().equals("")
              && (dto.getCredenceDateEnd() == null || dto.getCredenceDateEnd()
                  .equals(""))) {
            criterion += " and (to_char(f310.op_time,'yyyymmdd')>=?)";
            parameters.add(dto.getCredenceDateStart());
          } else if (dto.getCredenceDateEnd() != null
              && !dto.getCredenceDateEnd().equals("")
              && (dto.getCredenceDateStart() == null || dto
                  .getCredenceDateStart().equals(""))) {
            criterion += " and (to_char(f310.op_time,'yyyymmdd')<=?)";
            parameters.add(dto.getCredenceDateEnd());
          }

//          if (criterion.length() != 0)
//            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " f310.bizactivity_log_id ";

          String od = order;
          if (od == null)
            od = " DESC";

          hql = hql + criterion + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();

          List temp_List = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          QueryOperationLogListDTO queryOperationLogListDTO = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            queryOperationLogListDTO = new QueryOperationLogListDTO();
            if (obj[0] != null) {
              queryOperationLogListDTO.setBizId(obj[0].toString());
            }
            if (obj[1] != null) {
              queryOperationLogListDTO.setBizType(obj[1].toString());
            }
            if (obj[2] != null) {
              queryOperationLogListDTO.setAction(obj[2].toString());
            }
            if (obj[3] != null) {
              queryOperationLogListDTO.setOpTime(obj[3].toString());
            }
            if (obj[4] != null) {
              queryOperationLogListDTO.setOperator(obj[4].toString());
            }
            if (obj[5] != null) {
              queryOperationLogListDTO.setCredenceNum(obj[5].toString());
            }
            if (obj[6] != null) {
              queryOperationLogListDTO.setCredenceDate(obj[6].toString());
            }
            if (obj[7] != null) {
              queryOperationLogListDTO.setOffice(obj[7].toString());
            }
            temp_List.add(queryOperationLogListDTO);
          }
          return temp_List;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 查询业务日志表的Count
   * @param dto
   * @return 付云峰
   */
  public List queryOperationLogCount(final QueryOperationLogFindDTO dto, final String bookId,final List userlist) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select f310.bizactivity_log_id,f310.credence_type,f310.action,f310.op_time,f310.operator,f310.credence_num,f310.credence_date,f310.office from fn310 f310 where f310.book_id="+bookId;

          String criterion = "";
          Vector parameters = new Vector();

          String credenceType = dto.getCredenceType().trim();
          if (credenceType != null && !"".equals(credenceType)) {
            criterion += " and f310.credence_type=? ";
            parameters.add(credenceType);
          }

          String operator = dto.getOperator().trim();
          if (operator != null && !"".equals(operator)) {
            criterion += " and f310.operator=? ";
            parameters.add(operator);
          }else {
            criterion+=" and f310.operator in (";
            List operList = userlist;
            User user = null;
            Iterator itr2 = operList.iterator();
            while (itr2.hasNext()) {
              user = (User) itr2.next();
              criterion+="'"+user.getUsername()+"',";
            }criterion = criterion.substring(0, criterion.lastIndexOf(","));
            criterion += ") ";
          }

          String action = dto.getAction().trim();
          if (action != null && !"".equals(action)) {
            criterion += " and f310.action=?";
            parameters.add(action);
          }

          if (dto.getCredenceDateStart() != null
              && !dto.getCredenceDateStart().equals("")
              && dto.getCredenceDateEnd() != null
              && !dto.getCredenceDateEnd().equals("")) {
            criterion += " and (to_char(f310.op_time,'yyyymmdd') between ? and ?)";
            parameters.add(dto.getCredenceDateStart());
            parameters.add(dto.getCredenceDateEnd());
          } else if (dto.getCredenceDateStart() != null
              && !dto.getCredenceDateStart().equals("")
              && (dto.getCredenceDateEnd() == null || dto.getCredenceDateEnd()
                  .equals(""))) {
            criterion += " and (to_char(f310.op_time,'yyyymmdd')>=?)";
            parameters.add(dto.getCredenceDateStart());
          } else if (dto.getCredenceDateEnd() != null
              && !dto.getCredenceDateEnd().equals("")
              && (dto.getCredenceDateStart() == null || dto
                  .getCredenceDateStart().equals(""))) {
            criterion += " and (to_char(f310.op_time,'yyyymmdd')<=?)";
            parameters.add(dto.getCredenceDateEnd());
          }

//          if (criterion.length() != 0)
//            criterion = criterion+criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion ;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list = query.list();
          List temp_List = new ArrayList();
          Iterator iterate = list.iterator();
          Object[] obj = null;
          QueryOperationLogListDTO queryOperationLogListDTO = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            queryOperationLogListDTO = new QueryOperationLogListDTO();
            if (obj[0] != null) {
              queryOperationLogListDTO.setBizId(obj[0].toString());
            }
            if (obj[1] != null) {
              queryOperationLogListDTO.setBizType(obj[1].toString());
            }
            if (obj[2] != null) {
              queryOperationLogListDTO.setAction(obj[2].toString());
            }
            if (obj[3] != null) {
              queryOperationLogListDTO.setOpTime(obj[3].toString());
            }
            if (obj[4] != null) {
              queryOperationLogListDTO.setOperator(obj[4].toString());
            }
            if (obj[5] != null) {
              queryOperationLogListDTO.setCredenceNum(obj[5].toString());
            }
            if (obj[6] != null) {
              queryOperationLogListDTO.setCredenceDate(obj[6].toString());
            }
            if (obj[7] != null) {
              queryOperationLogListDTO.setOffice(obj[7].toString());
            }
            temp_List.add(queryOperationLogListDTO);
          }
          return temp_List;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 得到凭证字
   * 
   * @param credenceNum
   * @param office
   * @param credenceDate
   * @param bookId
   * @return
   */
  public String queryCredenceCharacter(final String credenceNum,
      final String office, final String credenceDate, final String bookId) {
    String str = null;
    try {
      str = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct f201.credence_character" + " from fn201 f201"
              + " where f201.credence_num = ?" + " and f201.office = ?"
              + " and f201.credence_date = ?" + " and f201.book_id = ?";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, credenceNum);
          query.setParameter(1, office);
          query.setParameter(2, credenceDate);
          query.setParameter(3, bookId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }
}
