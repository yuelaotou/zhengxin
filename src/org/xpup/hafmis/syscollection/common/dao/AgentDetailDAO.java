package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.AgentDetail;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAgentDetail;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentChgListDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentInfoDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentInfoQueryTaDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentInfoQueryTbDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentInfoQueryTcDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.PayChgInfoDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.PersonChgInfoDTO;

/**
 * Copy Right Information : 代扣清册表 Goldsoft Project : AgentDetailDAO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.17
 */
public class AgentDetailDAO extends HibernateDaoSupport {

  /**
   * 插入信息
   * 
   * @param agentDetail
   * @return
   * @author 付云峰
   */
  public Serializable insert(AgentDetail agentDetail) {
    Serializable id = null;
    try {
      Validate.notNull(agentDetail);
      id = getHibernateTemplate().save(agentDetail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 根据缴存年月查找是否有未使用的业务
   * 
   * @param agentYearMonth
   * @return
   * @author 付云峰
   */
  public Object queryStatusByAgentYearMonth(final String agentYearMonth) {
    Object obj = null;
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select e001.id from ea001 e001 where e001.agent_year_month=? and e001.status=0";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, agentYearMonth);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * 根据代扣批号查询对应的信息列表
   * 
   * @param agentDetailId
   * @return
   * @author 付云峰
   */
  public List queryAgentInfoByIdList(final String agentDetailId,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select e002.org_agent_id," + "e002.org_agent_num,"
              + "a001.id," + "b001.name,"
              + "(select nvl(count(e003.emp_agent_id), 0)" + "from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_org_pay), 0)" + "from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_emp_pay), 0)" + "from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_emp_salary), 0)"
              + " from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + " e001.pay_mode "
              + " from ea001 e001, ea002 e002, aa001 a001, ba001 b001"
              + " where e001.id = e002.agent_head_id"
              + " and a001.orginfo_id = b001.id"
              + " and a001.org_agent_num = e002.org_agent_num"
              + " and e001.id = ?" + " and e001.status = 0";

          String ob = orderBy.trim();

          if (ob == null)
            ob = " a001.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          sql = sql + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(agentDetailId));

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          List queryList = query.list();

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }

          Iterator it = queryList.iterator();

          List temp_List = new ArrayList();
          AgentInfoDTO agentInfoDTO = null;
          Object[] obj = null;
          while (it.hasNext()) {
            agentInfoDTO = new AgentInfoDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              agentInfoDTO.setOrgAgentId(obj[0].toString());
            }
            if (obj[1] != null) {
              agentInfoDTO.setOrgAgentNum(obj[1].toString());
            }
            if (obj[2] != null) {
              agentInfoDTO.setOrgId(obj[2].toString());
            }
            if (obj[3] != null) {
              agentInfoDTO.setOrgName(obj[3].toString());
            }
            if (obj[4] != null) {
              agentInfoDTO.setCountEmpId(new Integer(obj[4].toString()));
            }
            if (obj[5] != null) {
              agentInfoDTO.setSumAgentOrgPay(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null) {
              agentInfoDTO.setSumAgentEmpPay(new BigDecimal(obj[6].toString()));
            }
            if (obj[7] != null) {
              agentInfoDTO.setSumAgentEmpSalary(new BigDecimal(obj[7]
                  .toString()));
            }
            if (obj[8] != null) {
              agentInfoDTO.setPayMode(obj[8].toString());
            }
            temp_List.add(agentInfoDTO);
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
   * 根据代扣批号查询对应的信息Count
   * 
   * @param agentDetailId
   * @return
   */
  public List queryAgentInfoByIdCount(final String agentDetailId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select e002.org_agent_id," + "e002.org_agent_num,"
              + "a001.id," + "b001.name,"
              + "(select nvl(count(e003.emp_agent_id), 0)" + "from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_org_pay), 0)" + "from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_emp_pay), 0)" + "from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_emp_salary), 0)"
              + " from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id)"
              + " from ea001 e001, ea002 e002, aa001 a001, ba001 b001"
              + " where e001.id = e002.agent_head_id"
              + " and a001.orginfo_id = b001.id"
              + " and a001.org_agent_num = e002.org_agent_num"
              + " and e001.id = ?" + " and e001.status = 0";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(agentDetailId));

          Iterator it = query.list().iterator();

          List temp_List = new ArrayList();
          AgentInfoDTO agentInfoDTO = null;
          Object[] obj = null;
          while (it.hasNext()) {
            agentInfoDTO = new AgentInfoDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              agentInfoDTO.setOrgAgentId(obj[0].toString());
            }
            if (obj[1] != null) {
              agentInfoDTO.setOrgAgentNum(obj[1].toString());
            }
            if (obj[2] != null) {
              agentInfoDTO.setOrgId(obj[2].toString());
            }
            if (obj[3] != null) {
              agentInfoDTO.setOrgName(obj[3].toString());
            }
            if (obj[4] != null) {
              agentInfoDTO.setCountEmpId(new Integer(obj[4].toString()));
            }
            if (obj[5] != null) {
              agentInfoDTO.setSumAgentOrgPay(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null) {
              agentInfoDTO.setSumAgentEmpPay(new BigDecimal(obj[6].toString()));
            }
            if (obj[7] != null) {
              agentInfoDTO.setSumAgentEmpSalary(new BigDecimal(obj[7]
                  .toString()));
            }
            temp_List.add(agentInfoDTO);
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
   * 查询导入的单位是否为操作员权限
   * 
   * @param orgAgentNum
   * @param securityInfo
   * @return
   * @author 付云峰
   */
  public List queryOrg(final String orgAgentNum, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select a001.id from aa001 a001 where a001.org_agent_num=? and a001.id "
              + securityInfo.getGjjSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, orgAgentNum);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询该单位在这个月是否进行过代扣,并得到缴存类型
   * 
   * @param orgAgentNum
   * @param agentYearMonth
   * @return
   * @author 付云峰
   */
  public List queryPayMode(final String orgAgentNum, final String agentYearMonth) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select e001.pay_mode from ea002 e002, ea001 e001 where e002.agent_head_id=e001.id and e001.agent_year_month=? and e002.org_agent_num=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, agentYearMonth);
          query.setParameter(1, orgAgentNum);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据单位代扣号与代扣年月查询缴存信息
   * 
   * @param orgAgentNum
   * @param agentYearMonth
   * @return
   */
  public List queryPayMentInfo(final String orgAgentNum,
      final String agentYearMonth) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select a303.emp_id,"
              + "a301.org_id,"
              + "a303.org_should_pay,"
              + "a303.emp_should_pay,"
              + "(select a002.emp_agent_num"
              + " from aa002 a002"
              + " where a002.id = a303.emp_id"
              + " and a002.org_id = a301.org_id),"
              + "b002.name,"
              + "b002.card_num,"
              + "a002.salary_base"
              + " from aa303 a303, ba002 b002, aa302 a302, aa301 a301, aa002 a002"
              + " where b002.id = a002.emp_info_id"
              + " and a002.id = a303.emp_id" 
              + " and a301.id = a302.pay_head_id"
              + " and a302.id = a303.month_pay_head_id"
              + " and a301.org_id = a002.org_id" 
              + " and a301.org_id ="
              + "(select a001.id from aa001 a001 where a001.org_agent_num = ?)"
              + " and a301.financial_pay_id=" 
              + " (select e001.id"
              + " from ea002 e002, ea001 e001"
              + " where e002.agent_head_id = e001.id"
              + " and e001.agent_year_month = ?"
              + " and e002.org_agent_num = ?)";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, orgAgentNum);
          query.setParameter(1, agentYearMonth);
          query.setParameter(2, orgAgentNum);
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object[] obj = null;
          EmpAgentDetail empAgentDetail = null;
          while (it.hasNext()) {
            empAgentDetail = new EmpAgentDetail();
            obj = (Object[]) it.next();
            if (obj[2] != null) {
              empAgentDetail.setAgentOrgPay(new BigDecimal(obj[2].toString()));
            }
            if (obj[3] != null) {
              empAgentDetail.setAgentEmpPay(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              empAgentDetail.setEmpAgentNum(obj[4].toString());
            }
            if (obj[5] != null) {
              empAgentDetail.setEmpName(obj[5].toString());
            }
            if (obj[6] != null) {
              empAgentDetail.setCardNum(obj[6].toString());
            }
            if (obj[7] != null) {
              empAgentDetail.setAgentEmpSalary(new BigDecimal(obj[7].toString()));
            }
            temp_list.add(empAgentDetail);
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
   * 删除代扣导入的信息
   * 
   * @param agentDetailId
   * @param orgAgentNum
   * @author 付云峰
   */
  public void deleteAgentInfoByOrg(final String agentDetailId, final String orgAgentId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // 删除EA003
          String hql = "delete EmpAgentDetail empAgentDetail where empAgentDetail.orgAgentId=?";
          Query query1 = session.createQuery(hql);
          query1.setParameter(0, new Integer(orgAgentId));
          query1.executeUpdate();
          // 删除EA002
          String hql1 = "delete OrgAgentDetail orgAgentDetail where orgAgentDetail.orgAgentId=?";
          Query query2 = session.createQuery(hql1);
          query2.setParameter(0, new Integer(orgAgentId));
          query2.executeUpdate();
          // 判断该代扣批号下是否还存在做财政代扣的单位，如果不存在则在EA001中删除改笔财政代扣业务
          String sql = "select count(e002.org_agent_id) from ea002 e002 where e002.agent_head_id=?";
          Query query3 = session.createSQLQuery(sql);
          query3.setParameter(0, new Integer(agentDetailId));
          Object obj = (Object) query3.uniqueResult();
          if (obj.toString().equals("0")) {
            // 删除EA001
            String hql2 = "delete AgentDetail agentDetail where agentDetail.id=?";
            Query query4 = session.createQuery(hql2);
            query4.setParameter(0, new Integer(agentDetailId));
            query4.executeUpdate();
          }
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 全部删除的方法
   * 
   * @param agentDetailId
   * @param orgAgentNum
   * @author 付云峰
   */
  public void deleteAgentInfoByAgentDetailId(final String agentDetailId,
      final String orgAgentNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select e002.org_agent_id from ea002 e002 where e002.agent_head_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(agentDetailId));
          List list = query.list();

          for (int i = 0; i < list.size(); i++) {
            Object orgAgentId = (Object) list.get(i);
            // 删除EA003
            String hql = "delete EmpAgentDetail empAgentDetail where empAgentDetail.orgAgentId=?";
            Query query1 = session.createQuery(hql);
            query1.setParameter(0, new Integer(orgAgentId.toString()));
            query1.executeUpdate();
          }

          // 删除EA002
          String hql1 = "delete OrgAgentDetail orgAgentDetail where orgAgentDetail.agentHeadId=?";
          Query query2 = session.createQuery(hql1);
          query2.setParameter(0, new Integer(agentDetailId));
          query2.executeUpdate();
          // 删除EA001
          String hql2 = "delete AgentDetail agentDetail where agentDetail.id=?";
          Query query3 = session.createQuery(hql2);
          query3.setParameter(0, new Integer(agentDetailId));
          query3.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 查询代扣变更列表的方法
   * 
   * @param agentHeadId
   * @param agentYearMonth
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   * @author 付云峰
   */
  public List queryAgentChgInfoList(final String agentHeadId,
      final String agentYearMonth, final String orderBy, final String order,
      final int start, final int pageSize, final int page,final SecurityInfo info) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select distinct e001.id," + "e001.note_num," + "e001.agent_year_month,"
              + "(select count(e002.org_agent_id)" + " from ea002 e002"
              + " where e002.agent_head_id = e001.id),"
              + "(select count(e003.emp_agent_id)"
              + " from ea003 e003, ea002 e002"
              + " where e002.org_agent_id = e003.org_agent_id"
              + " and e002.agent_head_id = e001.id),"
              + "(select nvl(sum(e003.agent_org_pay), 0)"
              + " from ea003 e003, ea002 e002"
              + " where e002.org_agent_id = e003.org_agent_id"
              + " and e002.agent_head_id = e001.id),"
              + "(select nvl(sum(e003.agent_emp_pay), 0)"
              + " from ea003 e003, ea002 e002"
              + " where e002.org_agent_id = e003.org_agent_id"
              + " and e002.agent_head_id = e001.id),"
              + "(select nvl(sum(e003.agent_emp_salary), 0)"
              + " from ea003 e003, ea002 e002"
              + " where e002.org_agent_id = e003.org_agent_id"
              + " and e002.agent_head_id = e001.id)," + " e001.status,"
              + " e001.pay_mode "
              + " from ea001 e001,ea002 e002,aa001 a001 where e002.agent_head_id=e001.id" +
                  " and e002.org_agent_num=a001.org_agent_num and a001.id "+info.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (agentHeadId != null && !agentHeadId.trim().equals("")) {
            criterion += " e001.id = ? and ";
            parameters.add(agentHeadId.trim());
          }
          
          if (agentYearMonth != null && !agentYearMonth.trim().equals("")) {
            criterion += " e001.agent_year_month = ? and ";
            parameters.add(agentYearMonth.trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          
          String ob = orderBy;
          if (ob == null)
            ob = " e001.id ";
          String od = order;
          if (od == null)
            od = "DESC";

          sql = sql + criterion + " order by " + ob + " " + od;
          
          Query query = session.createSQLQuery(sql);
          
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object[] obj = null;
          AgentChgListDTO agentChgListDTO = null;
          while (it.hasNext()) {
            agentChgListDTO = new AgentChgListDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              agentChgListDTO.setAgentHeadId(obj[0].toString());
            }
            if (obj[1] != null) {
              agentChgListDTO.setNoteNum(obj[1].toString());
            }
            if (obj[2] != null) {
              agentChgListDTO.setAgentYearMonth(obj[2].toString());
            }
            if (obj[3] != null) {
              agentChgListDTO.setCountOrg(new Integer(obj[3].toString()));
            }
            if (obj[4] != null) {
              agentChgListDTO.setCountEmp(new Integer(obj[4].toString()));
            }
            if (obj[5] != null) {
              agentChgListDTO.setSumAgentOrgPay(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              agentChgListDTO.setSumAgentEmpPay(new BigDecimal(obj[6]
                  .toString()));
            }
            if (obj[7] != null) {
              agentChgListDTO.setSumAgentEmpSalary(new BigDecimal(obj[7]
                  .toString()));
            }
            if (obj[8] != null) {
              agentChgListDTO.setStatus(obj[8].toString());
            }
            if (obj[9] != null) {
              agentChgListDTO.setPayMode(obj[9].toString());
            }
            temp_list.add(agentChgListDTO);
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
   * 查询变更列表count的方法
   * 
   * @param agentHeadId
   * @param agentYearMonth
   * @return
   * @author 付云峰
   */
  public List queryAgentChgInfoCount(final String agentHeadId,
      final String agentYearMonth,final SecurityInfo info) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select distinct e001.id" + " from ea001 e001,ea002 e002,aa001 a001 where e002.agent_head_id=e001.id" +
          " and e002.org_agent_num=a001.org_agent_num and a001.id "+info.getGjjSecuritySQL();
          
          Vector parameters = new Vector();
          String criterion = "";

          if (agentHeadId != null && !agentHeadId.trim().equals("")) {
            criterion += " e001.id = ? and ";
            parameters.add(agentHeadId.trim());
          }
          
          if (agentYearMonth != null && !agentYearMonth.trim().equals("")) {
            criterion += " e001.agent_year_month = ? and ";
            parameters.add(agentYearMonth.trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
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

  /**
   * 判断代扣变更的最小年月
   * 
   * @param agentHeadId
   * @return
   * @author 付云峰
   */
  public List queryAgentChgMinYearMonth(final String agentHeadId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select e001.id from ea001 e001 where e001.agent_year_month=(select min(e.agent_year_month) from ea001 e where e.status=0) and e001.id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, agentHeadId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 调用代扣变更存储过程
   * 
   * @param agentHeadId
   * @author 付云峰
   */
  public void insertAgentChangeInfo(final String agentHeadId,
      final SecurityInfo securityInfo) throws BusinessException {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      CallableStatement cs = conn.prepareCall("{call agentchange(?,?,?)}");
      cs.setString(1, agentHeadId);
      cs.setString(2, securityInfo.getUserInfo().getUsername());
      cs.setString(3, securityInfo.getUserInfo().getBizDate());
      cs.execute();
    } catch (SQLException se) {
      se.printStackTrace();
      throw new BusinessException(String.valueOf(se.getErrorCode()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List queryPayByFinancialPayId(final String agentHeadId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select a301.id,a301.org_id from aa301 a301 where a301.financial_pay_id=? and a301.pay_status=2";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, agentHeadId);
          List list = query.list();
          for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            String orgId = obj[1].toString();
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
   * 判断是否可以进行变更的撤销
   * @param agentHeadId
   * @return
   * @author 付云峰
   */
  public List queryIsbackAgentChagne(final String agentHeadId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select a301.id from aa301 a301 where a301.financial_pay_id=? and a301.pay_status>2";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, agentHeadId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 获得代扣信息集合 list 张列
   * 
   * @param payId
   * @param payMonth
   * @return
   * @throws Exception
   */
  public List queryAgentInfoTaList(final String payId, final String payMonth,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo info) throws Exception {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List agentInfoTaList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select a301.financial_pay_id,"
                + "min(a301.note_num)," 
                + "min(a302.pay_month),"
                + "(select count(a1.id)" 
                + " from aa301 a1"
                + " where a1.financial_pay_id = a301.financial_pay_id),"
                + "(select count(a3.id)" 
                + " from aa303 a3, aa301 a1, aa302 a2"
                + " where a3.month_pay_head_id = a2.id"
                + " and a2.pay_head_id = a1.id"
                + " and a1.financial_pay_id = a301.financial_pay_id),"
                + "nvl(sum(a303.org_real_pay), 0),"
                + "nvl(sum(a303.emp_real_pay), 0),"
                + "min(a301.pay_model)"
                + " from aa301 a301, aa302 a302, aa303 a303"
                + " where a301.id = a302.pay_head_id"
                + " and a302.id = a303.month_pay_head_id"
                + " and a301.financial_pay_id>0"
                + " and a301.org_id "+info.getGjjSecuritySQL();
            String criterion = "";
            Vector parameters = new Vector();
            if (payId != null && !payId.equals("")) {
              criterion += " a301.financial_pay_id = ?  and ";
              parameters.add(payId);
            }

            if (payMonth != null && !payMonth.equals("")) {
              criterion += " a302.pay_month = ?  and ";
              parameters.add(payMonth);
            }
            if (criterion.length() != 0) {
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }
            String ob = orderBy;
            if (ob == null)
              ob = " a301.financial_pay_id ";
            String od = order;
            if (od == null)
              od = " DESC ";
            hql = hql + criterion + " group by a301.financial_pay_id order by "
                + ob + " " + od;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            List queryList = query.list();

            Object obj[] = null;
            List list = new ArrayList();
            Iterator iter = queryList.iterator();
            while (iter.hasNext()) {
              AgentInfoQueryTaDTO agentInfoQueryTaDTO = new AgentInfoQueryTaDTO();
              obj = (Object[]) iter.next();
              if (obj[0] != null) {
                agentInfoQueryTaDTO.setPayId(obj[0].toString());
              }
              if (obj[1] != null) {
                agentInfoQueryTaDTO.setNoteNum(obj[1].toString());
              }
              if (obj[2] != null) {
                agentInfoQueryTaDTO.setPayMonth(obj[2].toString());
              }
              if (obj[3] != null) {
                agentInfoQueryTaDTO.setOrgIdSum(obj[3].toString());
              }
              if (obj[4] != null) {
                agentInfoQueryTaDTO.setEmpIdSum(obj[4].toString());
              }
              if (obj[5] != null) {
                agentInfoQueryTaDTO.setOrgRealPaySum(obj[5].toString());
              }
              if (obj[6] != null) {
                agentInfoQueryTaDTO.setEmpRealPaySum(obj[6].toString());
              }
              if (obj[7] != null) {
                agentInfoQueryTaDTO.setPayMode(obj[7].toString());
              }
              list.add(agentInfoQueryTaDTO);
            }
            return list;
          }
        });
    return agentInfoTaList;
  }

  /**
   * 获得代扣信息count数 张列
   * 
   * @param payId
   * @param payMonth
   * @return
   * @throws Exception
   */
  public Integer queryAgentInfoTaListCount(final String payId,
      final String payMonth, final String orderBy, final String order,
      final int start,final SecurityInfo info) throws Exception {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select min(a301.note_num),min(a302.pay_month) "
                + "from aa301 a301, aa302 a302, aa303 a303 "
                + "where a301.id = a302.pay_head_id and a302.id = a303.month_pay_head_id and a301.org_id "+info.getGjjSecuritySQL()
                + "and a301.financial_pay_id>0 ";
            String criterion = "";
            Vector parameters = new Vector();
            if (payId != null && !payId.equals("")) {
              criterion += " a301.financial_pay_id = ?  and ";
              parameters.add(payId);
            }

            if (payMonth != null && !payMonth.equals("")) {
              criterion += " a302.pay_month = ?  and ";
              parameters.add(payMonth);
            }
            if (criterion.length() != 0) {
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }
            String ob = orderBy;
            if (ob == null)
              ob = " a301.financial_pay_id ";
            String od = order;
            if (od == null)
              od = " DESC ";
            hql = hql + criterion + " group by a301.financial_pay_id order by "
                + ob + " " + od;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            Integer temp_count = new Integer(0);
            temp_count = new Integer(query.list().size());
            return temp_count;
          }
        });
    return count;
  }

  /**
   * 获得代扣信息(单位)集合 list 张列
   * 
   * @param payId
   * @param payMonth
   * @return
   * @throws Exception
   */
  public List queryAgentInfoTbList(final String payId, final String payMonth,
      final String orgId, final String orgName, final String orgAgentNum,
      final String orderBy, final String order, final int start,
      final int pageSize) throws Exception {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List agentInfoTbList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select distinct a301.org_id,max(a001.org_agent_num),max(b001.name),"
                + "sum(a303.emp_real_pay),sum(a303.org_real_pay) "
                + "from aa301 a301, aa302 a302, aa303 a303, aa001 a001, ba001 b001 "
                + "where a301.org_id = a001.id and a301.id = a302.pay_head_id "
                + "and a302.id = a303.month_pay_head_id and a001.orginfo_id = b001.id ";
            String criterion = "";
            Vector parameters = new Vector();
            if (payId != null && !payId.equals("")) {
              criterion += " a301.financial_pay_id = ?  and ";
              parameters.add(payId);
            }
            if (payMonth != null && !payMonth.equals("")) {
              criterion += " a302.pay_month = ?  and ";
              parameters.add(payMonth);
            }
            if (orgId != null && !orgId.equals("")) {
              criterion += " a301.org_id = ?  and ";
              parameters.add(orgId);
            }
            if (orgName != null && !orgName.equals("")) {
              criterion += " b001.name = ?  and ";
              parameters.add(orgName);
            }
            if (orgAgentNum != null && !orgAgentNum.equals("")) {
              criterion += " a001.org_agent_num = ?  and ";
              parameters.add(orgAgentNum);
            }

            if (criterion.length() != 0) {
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }
            String ob = orderBy;
            if (ob == null)
              ob = " a301.org_id ";
            String od = order;
            if (od == null)
              od = " DESC ";
            hql = hql + criterion + " group by a301.org_id order by " + ob
                + " " + od;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            List queryList = query.list();

            Object obj[] = null;
            List list = new ArrayList();
            Iterator iter = queryList.iterator();
            while (iter.hasNext()) {
              AgentInfoQueryTbDTO agentInfoQueryTbDTO = new AgentInfoQueryTbDTO();
              obj = (Object[]) iter.next();
              if (obj[0] != null) {
                agentInfoQueryTbDTO.setOrgId(obj[0].toString());
              }
              if (obj[1] != null) {
                agentInfoQueryTbDTO.setOrgAgentNum(obj[1].toString());
              }
              if (obj[2] != null) {
                agentInfoQueryTbDTO.setOrgName(obj[2].toString());
              }
              if (obj[3] != null) {
                agentInfoQueryTbDTO.setEmpRealPaySum(obj[3].toString());
              }
              if (obj[4] != null) {
                agentInfoQueryTbDTO.setOrgRealPaySum(obj[4].toString());
              }
              list.add(agentInfoQueryTbDTO);
            }
            return list;
          }
        });
    return agentInfoTbList;
  }

  /**
   * 获得代扣信息(单位)count数 张列
   * 
   * @param payId
   * @param payMonth
   * @return
   * @throws Exception
   */
  public Integer queryAgentInfoTbListCount(final String payId,
      final String payMonth, final String orgId, final String orgName,
      final String orgAgentNum, final String orderBy, final String order,
      final int start) throws Exception {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select distinct a301.org_id,max(a001.org_agent_num) "
                + "from aa301 a301, aa302 a302, aa303 a303, aa001 a001, ba001 b001 "
                + "where a301.org_id = a001.id and a301.id = a302.pay_head_id "
                + "and a302.id = a303.month_pay_head_id and a001.orginfo_id = b001.id ";
            String criterion = "";
            Vector parameters = new Vector();
            if (payId != null && !payId.equals("")) {
              criterion += " a301.financial_pay_id = ?  and ";
              parameters.add(payId);
            }
            if (payMonth != null && !payMonth.equals("")) {
              criterion += " a302.pay_month = ?  and ";
              parameters.add(payMonth);
            }
            if (orgId != null && !orgId.equals("")) {
              criterion += " a301.org_id = ?  and ";
              parameters.add(orgId);
            }
            if (orgName != null && !orgName.equals("")) {
              criterion += " b001.name = ?  and ";
              parameters.add(orgName);
            }
            if (orgAgentNum != null && !orgAgentNum.equals("")) {
              criterion += " a001.org_agent_num = ?  and ";
              parameters.add(orgAgentNum);
            }

            if (criterion.length() != 0) {
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }
            String ob = orderBy;
            if (ob == null)
              ob = " a301.org_id ";
            String od = order;
            if (od == null)
              od = " DESC ";
            hql = hql + criterion + " group by a301.org_id order by " + ob
                + " " + od;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            Integer temp_count = new Integer(0);
            temp_count = new Integer(query.list().size());
            return temp_count;
          }
        });
    return count;
  }

  /**
   * 获得代扣信息(职工)集合 list 张列
   * 
   * @param payId
   * @param payMonth
   * @return
   * @throws Exception
   */
  public List queryAgentInfoTcList(final String payId, final String orgId,
      final String empId, final String empName, final String empAgentNum,
      final String cardNum, final String orderBy, final String order,
      final int start, final int pageSize) throws Exception {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List agentInfoTcList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select distinct a303.emp_id,a002.emp_agent_num,b002.name,"
                + "b002.card_num,a303.org_real_pay,a303.emp_real_pay "
                + "from aa301 a301, aa302 a302, aa303 a303, aa002 a002, ba002 b002 "
                + "where a301.id = a302.pay_head_id and a302.id = a303.month_pay_head_id "
                + "and a303.emp_id = a002.id and a002.org_id = a301.org_id and a002.emp_info_id = b002.id ";
            String criterion = "";
            Vector parameters = new Vector();
            if (payId != null && !payId.equals("")) {
              criterion += " a301.financial_pay_id = ?  and ";
              parameters.add(payId);
            }
            if (orgId != null && !orgId.equals("")) {
              criterion += " a301.org_id = ?  and ";
              parameters.add(orgId);
            }
            if (empId != null && !empId.equals("")) {
              criterion += " a303.emp_id = ?  and ";
              parameters.add(empId);
            }
            if (empName != null && !empName.equals("")) {
              criterion += " b002.name = ?  and ";
              parameters.add(empName);
            }
            if (empAgentNum != null && !empAgentNum.equals("")) {
              criterion += " a002.emp_agent_num = ?  and ";
              parameters.add(empAgentNum);
            }
            if (cardNum != null && !cardNum.equals("")) {
              criterion += " b002.card_num = ?  and ";
              parameters.add(cardNum);
            }

            if (criterion.length() != 0) {
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }
            String ob = orderBy;
            if (ob == null)
              ob = " a303.emp_id ";
            String od = order;
            if (od == null)
              od = " DESC ";
            hql = hql + criterion + " order by " + ob + " " + od;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            List queryList = query.list();

            Object obj[] = null;
            List list = new ArrayList();
            Iterator iter = queryList.iterator();
            while (iter.hasNext()) {
              AgentInfoQueryTcDTO agentInfoQueryTcDTO = new AgentInfoQueryTcDTO();
              obj = (Object[]) iter.next();
              if (obj[0] != null) {
                agentInfoQueryTcDTO.setEmpId(obj[0].toString());
              }
              if (obj[1] != null) {
                agentInfoQueryTcDTO.setEmpAgentNum(obj[1].toString());
              }
              if (obj[2] != null) {
                agentInfoQueryTcDTO.setEmpName(obj[2].toString());
              }
              if (obj[3] != null) {
                agentInfoQueryTcDTO.setCardNum(obj[3].toString());
              }
              if (obj[4] != null) {
                agentInfoQueryTcDTO.setOrgRealPay(obj[4].toString());
              }
              if (obj[5] != null) {
                agentInfoQueryTcDTO.setEmpRealPay(obj[5].toString());
              }
              list.add(agentInfoQueryTcDTO);
            }
            return list;
          }
        });
    return agentInfoTcList;
  }

  /**
   * 获得代扣信息(职工)count数 张列
   * 
   * @param payId
   * @param payMonth
   * @return
   * @throws Exception
   */
  public Integer queryAgentInfoTcListCount(final String payId,
      final String orgId, final String empId, final String empName,
      final String empAgentNum, final String cardNum, final String orderBy,
      final String order, final int start) throws Exception {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select distinct a303.emp_id,a002.emp_agent_num "
                + "from aa301 a301, aa302 a302, aa303 a303, aa002 a002, ba002 b002 "
                + "where a301.id = a302.pay_head_id and a302.id = a303.month_pay_head_id "
                + "and a303.emp_id = a002.id and a002.org_id = a301.org_id and a002.emp_info_id = b002.id ";
            String criterion = "";
            Vector parameters = new Vector();
            if (payId != null && !payId.equals("")) {
              criterion += " a301.financial_pay_id = ?  and ";
              parameters.add(payId);
            }
            if (orgId != null && !orgId.equals("")) {
              criterion += " a301.org_id = ?  and ";
              parameters.add(orgId);
            }
            if (empId != null && !empId.equals("")) {
              criterion += " a303.emp_id = ?  and ";
              parameters.add(empId);
            }
            if (empName != null && !empName.equals("")) {
              criterion += " b002.name = ?  and ";
              parameters.add(empName);
            }
            if (empAgentNum != null && !empAgentNum.equals("")) {
              criterion += " a002.emp_agent_num = ?  and ";
              parameters.add(empAgentNum);
            }
            if (cardNum != null && !cardNum.equals("")) {
              criterion += " b002.card_num = ?  and ";
              parameters.add(cardNum);
            }

            if (criterion.length() != 0) {
              criterion = " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }
            String ob = orderBy;
            if (ob == null)
              ob = " a303.emp_id ";
            String od = order;
            if (od == null)
              od = " DESC ";
            hql = hql + criterion + " order by " + ob + " " + od;
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            Integer temp_count = new Integer(0);
            temp_count = new Integer(query.list().size());
            return temp_count;
          }
        });
    return count;
  }

  /**
   * 修改职工信息
   * 
   * @param empId
   * @param orgId
   * @param oldSalaryBase
   * @param oldOrgPay
   * @param oldEmpPay
   * @author 付云峰
   */
  public void updateEmpPay(final String empId, final String orgId,
      final BigDecimal oldSalaryBase, final BigDecimal oldOrgPay,
      final BigDecimal oldEmpPay) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update Emp emp set emp.salaryBase=?,emp.orgPay= ? ,emp.empPay= ? "
              + "where emp.empId=? " + "and emp.org.id=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, oldSalaryBase);
          query.setParameter(1, oldOrgPay);
          query.setParameter(2, oldEmpPay);
          query.setParameter(3, new Integer(empId));
          query.setParameter(4, new Integer(orgId));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 查询做个人员变更人的信息。
   * 
   * @param payHeadId
   * @param orgId
   * @return
   * @author 付云峰
   */
  public List queryPersonChgInfo(final String payHeadId, final String orgId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select a204.id, a204.org_id, a205.emp_id, a205.chg_type,a205.old_pay_status"
              + " from aa204 a204, aa205 a205"
              + " where a204.id = a205.chg_head_id"
              + " and a204.chg_status = 2"
              + " and a204.org_id = ?"
              + " and a204.pay_head_id = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(orgId));
          query.setParameter(1, new Integer(payHeadId));
          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object[] obj = null;
          PersonChgInfoDTO personChgInfoDTO = null;
          while (it.hasNext()) {
            personChgInfoDTO = new PersonChgInfoDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              personChgInfoDTO.setId(obj[0].toString());
            }
            if (obj[1] != null) {
              personChgInfoDTO.setOrgId(obj[1].toString());
            }
            if (obj[2] != null) {
              personChgInfoDTO.setEmpId(obj[2].toString());
            }
            if (obj[3] != null) {
              personChgInfoDTO.setChgType(obj[3].toString());
            }
            if (obj[4] != null) {
              personChgInfoDTO.setOldPayStatus(obj[4].toString());
            }
            temp_list.add(personChgInfoDTO);
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
   * 删除缴额调整信息
   * 
   * @param payChgId
   * @author 付云峰
   */
  public void deletePayChg(final String payChgId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // 删除尾表
          String hql = "delete ChgPaymentTail chgPaymentTail where chgPaymentTail.chgPaymentHead.id=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(payChgId));
          query.executeUpdate();
          // 删除头表
          String hql1 = "delete ChgPaymentHead chgPaymentHead where chgPaymentHead.id=?";
          Query query1 = session.createQuery(hql1);
          query1.setParameter(0, new Integer(payChgId));
          query1.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除职工信息
   * 
   * @param orgId
   * @param empId
   * @author 付云峰
   */
  public void deleteEmpAndEmpInfo(final String orgId, final String empId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select a002.emp_info_id" + " from aa002 a002"
              + " where a002.id = ?" + " and a002.org_id = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(empId));
          query.setParameter(1, new Integer(orgId));
          Object obj = query.uniqueResult();

          // 删除尾表
          String hql = "delete EmpInfo empInfo where empInfo.id=?";
          Query query1 = session.createQuery(hql);
          query1.setParameter(0, new Integer(obj.toString()));
          query1.executeUpdate();
          // 删除头表
          String hql1 = "delete Emp emp where emp.empId=? and emp.org.id=?";
          Query query2 = session.createQuery(hql1);
          query2.setParameter(0, new Integer(empId));
          query2.setParameter(1, new Integer(orgId));
          query2.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 修改职工表中的状态
   * 
   * @param orgId
   * @param empId
   * @author 付云峰
   */
  public void updateEmp(final String orgId, final String empId,
      final String payStatus) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update Emp emp set emp.payStatus=? "
              + "where emp.empId=? " + "and emp.org.id=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, new BigDecimal(payStatus));
          query.setParameter(1, new Integer(empId));
          query.setParameter(2, new Integer(orgId));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除人员变更信息
   * 
   * @param personChgId
   * @author 付云峰
   */
  public void deletePersonChgInfo(final String personChgId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // 删除尾表
          String hql = "delete ChgPersonTail chgPersonTail where chgPersonTail.chgPersonHead.id=?";
          Query query1 = session.createQuery(hql);
          query1.setParameter(0, new Integer(personChgId));
          query1.executeUpdate();
          // 删除头表
          String hql1 = "delete ChgPersonHead chgPersonHead where chgPersonHead.id=?";
          Query query2 = session.createQuery(hql1);
          query2.setParameter(0, new Integer(personChgId));
          query2.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除汇缴表
   * 
   * @param agentHeadId
   * @author 付云峰
   */
  public void deletePayment(final String agentHeadId) throws Exception{
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // 得到AA302中将要删除记录的主键
          String sql = "";
          sql = "select a302.id from aa302 a302,aa301 a301 where a301.id=a302.pay_head_id and a301.financial_pay_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(agentHeadId));
          List list = query.list();
          for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            String paymentId = obj.toString();
            // 删除aa303
            String hql = "delete MonthPaymentTail monthPaymentTail where monthPaymentTail.monthPaymentHead.id=?";
            Query query1 = session.createQuery(hql);
            query1.setParameter(0, new Integer(paymentId));
            query1.executeUpdate();
            // 删除aa302
            String hql1 = "delete MonthPaymentHead monthPaymentHead where monthPaymentHead.id=?";
            Query query2 = session.createQuery(hql1);
            query2.setParameter(0, new Integer(paymentId));
            query2.executeUpdate();   
          }
          // 删除aa301
          String hql2 = "delete PaymentHead paymentHead where paymentHead.financialPayId=?";
          Query query3 = session.createQuery(hql2);
          query3.setParameter(0, new Integer(agentHeadId));
          query3.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 更新代扣清册
   * 
   * @param agentHeadId
   * @author 付云峰
   */
  public void updateAgentStatus(final String agentHeadId) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update AgentDetail agentDetail set agentDetail.status=0 "
              + "where agentDetail.id=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(agentHeadId));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 查询单位最大的业务id
   * 
   * @param orgId
   * @return
   * @author 付云峰
   */
  public Object queryPayIdByOrgId(final String orgId) {
    Object obj = null;
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select max(a301.id) from aa301 a301 where a301.org_id=? and a301.financial_pay_id is not null and (a301.financial_pay_id!='' or a301.financial_pay_id!=0)";
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

  /**
   * 查询做过缴额调整的职工信息
   * 
   * @param payChgId
   * @param orgId
   * @return
   */
  public List queryPayChgInfo(final String payHeadId, final String orgId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select a203.emp_id,a203.old_salary_base,a203.old_org_pay,a203.old_emp_pay,a202.id"
              + " from aa203 a203, aa202 a202"
              + " where a203.chng_head_id = a202.id"
              + " and a202.chg_type = 'B'"
              + " and a202.chg_status = 2"
              + " and a202.org_id = ?" + " and a202.pay_head_id = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(orgId));
          query.setParameter(1, new Integer(payHeadId));
          Iterator it = query.list().iterator();
          Object[] obj = null;
          PayChgInfoDTO payChgInfoDTO = null;
          List temp_list = new ArrayList();
          while (it.hasNext()) {
            payChgInfoDTO = new PayChgInfoDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              payChgInfoDTO.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              payChgInfoDTO.setOldSalaryBase(new BigDecimal(obj[1].toString()));
            }
            if (obj[2] != null) {
              payChgInfoDTO.setOldOrgPay(new BigDecimal(obj[2].toString()));
            }
            if (obj[3] != null) {
              payChgInfoDTO.setOldEmpPay(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              payChgInfoDTO.setId(obj[4].toString());
            }
            temp_list.add(payChgInfoDTO);
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
   * 删除319日志的方法,汇缴
   * 
   * @param Bizid
   * @param type
   * @author 付云峰
   */
  public void deleteBizActivityLogByPayment(final String Bizid) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "delete MonthPaymentBizActivityLog monthPaymentBizActivityLog where monthPaymentBizActivityLog.bizid=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(Bizid));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 删除319日志的方法,人员变更
   * 
   * @param Bizid
   * @param type
   * @author 付云峰
   */
  public void deleteBizActivityLogByPersonChg(final String Bizid) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "delete ChgPersonBizActivityLog chgPersonBizActivityLog where chgPersonBizActivityLog.bizid=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(Bizid));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 删除319日志的方法,缴额调整
   * 
   * @param Bizid
   * @param type
   * @author 付云峰
   */
  public void deleteBizActivityLogByPayChg(final String Bizid) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "delete ChgPaymentBizActivityLog chgPaymentBizActivityLog where chgPaymentBizActivityLog.bizid=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(Bizid));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 查询财政代扣单位明细List
   * 
   * @param agentDetailId
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   * @author 付云峰
   */
  public List queryAgentOrgInfoList(final String agentHeadId,final String orgId,final String orgAgentNum,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select e002.org_agent_id," + " e002.org_agent_num,"
              + "(select a001.id" + " from aa001 a001"
              + " where a001.org_agent_num = e002.org_agent_num),"
              + "(select b001.name" + " from aa001 a001, ba001 b001"
              + " where a001.orginfo_id = b001.id"
              + " and a001.org_agent_num = e002.org_agent_num),"
              + "(select count(e003.emp_agent_id)" + " from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_org_pay), 0)" + " from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_emp_pay), 0)" + " from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_emp_salary), 0)"
              + " from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + " (select e001.pay_mode from ea001 e001 where e001.id = "+ agentHeadId +")"
              + " from ea002 e002,aa001 a001" + " where a001.org_agent_num=e002.org_agent_num and e002.agent_head_id ="+agentHeadId;
          
          String criterion = "";
          Vector parameters = new Vector();
          
          if (orgId != null && !orgId.trim().equals("")) {
            criterion += " a001.id = ?  and ";
            parameters.add(orgId.trim());
          }
          
          if (orgAgentNum != null && !orgAgentNum.trim().equals("")) {
            criterion += " e002.org_agent_num = ?  and ";
            parameters.add(orgAgentNum.trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          
          String ob = orderBy;
          if (ob == null)
            ob = " e001.id ";
          String od = order;
          if (od == null)
            od = "DESC";

          sql = sql + criterion + " order by " + ob + " " + od;
          
          Query query = session.createSQLQuery(sql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }
          
          Iterator it = query.list().iterator();

          List temp_List = new ArrayList();
          AgentInfoDTO agentInfoDTO = null;
          Object[] obj = null;
          while (it.hasNext()) {
            agentInfoDTO = new AgentInfoDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              agentInfoDTO.setOrgAgentId(obj[0].toString());
            }
            if (obj[1] != null) {
              agentInfoDTO.setOrgAgentNum(obj[1].toString());
            }
            if (obj[2] != null) {
              agentInfoDTO.setOrgId(obj[2].toString());
            }
            if (obj[3] != null) {
              agentInfoDTO.setOrgName(obj[3].toString());
            }
            if (obj[4] != null) {
              agentInfoDTO.setCountEmpId(new Integer(obj[4].toString()));
            }
            if (obj[5] != null) {
              agentInfoDTO.setSumAgentOrgPay(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null) {
              agentInfoDTO.setSumAgentEmpPay(new BigDecimal(obj[6].toString()));
            }
            if (obj[7] != null) {
              agentInfoDTO.setSumAgentEmpSalary(new BigDecimal(obj[7]
                  .toString()));
            }
            if (obj[8] != null) {
              agentInfoDTO.setPayMode(obj[8].toString());
            }
            temp_List.add(agentInfoDTO);
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
   * 查询财政代扣单位明细Count
   * 
   * @param agentHeadId
   * @return
   * @author 付云峰
   */
  public List queryAgentOrgInfoCount(final String agentHeadId,final String orgId,final String orgAgentNum) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
          sql = "select e002.org_agent_id,"
              + "(select count(e003.emp_agent_id)" + " from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_org_pay), 0)" + " from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_emp_pay), 0)" + " from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id),"
              + "(select nvl(sum(e003.agent_emp_salary), 0)"
              + " from ea003 e003"
              + " where e003.org_agent_id = e002.org_agent_id)"
              + " from ea002 e002,aa001 a001" + " where a001.org_agent_num=e002.org_agent_num and e002.agent_head_id = "+agentHeadId;
          
          String criterion = "";
          Vector parameters = new Vector();
          
          if (orgId != null && !orgId.trim().equals("")) {
            criterion += " a001.id = ?  and ";
            parameters.add(orgId.trim());
          }
          
          if (orgAgentNum != null && !orgAgentNum.trim().equals("")) {
            criterion += " e002.org_agent_num = ?  and ";
            parameters.add(orgAgentNum.trim());
          }
          
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          
          sql = sql + criterion;
          
          Query query = session.createSQLQuery(sql);
          
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();

          List temp_List = new ArrayList();
          AgentInfoDTO agentInfoDTO = null;
          Object[] obj = null;
          while (it.hasNext()) {
            agentInfoDTO = new AgentInfoDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              agentInfoDTO.setOrgAgentId(obj[0].toString());
            }
            if (obj[1] != null) {
              agentInfoDTO.setCountEmpId(new Integer(obj[1].toString()));
            }
            if (obj[2] != null) {
              agentInfoDTO.setSumAgentOrgPay(new BigDecimal(obj[2].toString()));
            }
            if (obj[3] != null) {
              agentInfoDTO.setSumAgentEmpPay(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              agentInfoDTO.setSumAgentEmpSalary(new BigDecimal(obj[4].toString()));
            }
            temp_List.add(agentInfoDTO);
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
   * 查询职工代扣明细的方法
   * 
   * @param orgAgentId
   * @param orgId
   * @param empId
   * @param empName
   * @param cardNum
   * @param empAgentNum
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryAgentEmpInfoList(final String orgAgentId,
      final String orgId, final String empId, final String empName,
      final String cardNum, final String empAgentNum, final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
//           sql = "select a002.id,"
//           + "a002.emp_agent_num,"
//           + "b002.name,"
//           + "b002.card_num,"
//           + "e003.agent_org_pay,"
//           + "e003.agent_emp_pay,"
//           + "e003.agent_emp_salary,"
//           + "e003.emp_agent_id"
//           + " from ba002 b002, ea003 e003, aa002 a002"
//           + " where b002.id = a002.emp_info_id"
//           + " and e003.emp_agent_num = a002.emp_agent_num"
//           + " and splitcardnum(e003.cardnum) = splitcardnum(b002.card_num)"
//           + " and e003.emp_name = b002.name"
//           + " and e003.org_agent_id = "+orgAgentId
//           + " and a002.org_id="+orgId;
          sql = "select (select a002.id" 
              + " from aa002 a002"
              + " where a002.org_id ="+orgId
              + " and a002.emp_agent_num = e003.emp_agent_num),"
              + "e003.emp_agent_num," 
              + "e003.emp_name,"
              + "e003.cardnum,"
              + "e003.agent_org_pay," 
              + "e003.agent_emp_pay,"
              + "e003.agent_emp_salary," 
              + "e003.emp_agent_id"
              + " from ea003 e003" 
              + " where e003.org_agent_id = "+orgAgentId;

          String criterion = "";
          Vector parameters = new Vector();

          if (empId != null && !empId.trim().equals("")) {
            criterion += " ((e003.emp_name, e003.cardnum) = (select b.name, b.card_num from ba002 b, aa002 a where a.emp_info_id = b.id and a.id = ? and a.org_id = ?))  and ";
            parameters.add(empId.trim());
            parameters.add(orgId.trim());
          }

          if (empName != null && !empName.trim().equals("")) {
            criterion += " e003.emp_name = ?  and ";
            parameters.add(empName.trim());
          }

          if (cardNum != null && !cardNum.trim().equals("")) {
            criterion += " e003.cardnum = ?  and ";
            parameters.add(cardNum.trim());
          }
          if (empAgentNum != null && !empAgentNum.trim().equals("")) {
            criterion += " e003.emp_agent_num = ?  and ";
            parameters.add(empAgentNum.trim());
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " e003.emp_agent_id ";
          String od = order;
          if (od == null)
            od = "DESC";

          sql = sql + criterion + " order by " + ob + " " + od;

          Query query = session.createSQLQuery(sql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();
          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }

          Iterator it = query.list().iterator();

          List temp_List = new ArrayList();
          AgentChgListDTO agentChgListDTO = null;
          Object[] obj = null;
          while (it.hasNext()) {
            agentChgListDTO = new AgentChgListDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              agentChgListDTO.setAgentEmppopId(obj[0].toString());
            }
            if (obj[1] != null) {
              agentChgListDTO.setAgentEmppopkouCode(obj[1].toString());
            }
            if (obj[2] != null) {
              agentChgListDTO.setAgentEmppopname(obj[2].toString());
            }
            if (obj[3] != null) {
              agentChgListDTO.setAgentEmppopCardID(obj[3].toString());
            }
            if (obj[4] != null) {
              agentChgListDTO.setAgentEmppoporgpay(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[5] != null) {
              agentChgListDTO.setAgentEmppopemppay(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              agentChgListDTO.setAgentEmppopmonthsalary(new BigDecimal(obj[6]
                  .toString()));
            }
            if (obj[7] != null) {
              agentChgListDTO.setEmpAgentId(obj[7].toString());
            }
            temp_List.add(agentChgListDTO);
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
   * 查询职工代扣明细Count的方法
   * @param orgAgentId
   * @param orgId
   * @param empId
   * @param empName
   * @param cardNum
   * @param empAgentNum
   * @return
   */
  public List queryAgentEmpInfoCount(final String orgAgentId,
      final String orgId, final String empId, final String empName,
      final String cardNum, final String empAgentNum) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "";
//          sql = "select a002.id," 
//              + "e003.emp_agent_num," 
//              + "b002.name,"
//              + "b002.card_num,"
//              + "e003.agent_org_pay,"
//              + "e003.agent_emp_pay," 
//              + "e003.agent_emp_salary"
//              + " from ba002 b002, ea003 e003, aa002 a002"
//              + " where b002.id = a002.emp_info_id"
//              + " and e003.emp_agent_num = a002.emp_agent_num"
//              + " and splitcardnum(e003.cardnum) = splitcardnum(b002.card_num)"
//              + " and e003.emp_name = b002.name"
//              + " and e003.org_agent_id = "+orgAgentId 
//              + " and a002.org_id="+orgId;
          sql = "select (select a002.id" 
            + " from aa002 a002"
            + " where a002.org_id ="+orgId
            + " and a002.emp_agent_num = e003.emp_agent_num),"
            + "e003.emp_agent_num," 
            + "e003.emp_name,"
            + "e003.cardnum,"
            + "e003.agent_org_pay," 
            + "e003.agent_emp_pay,"
            + "e003.agent_emp_salary," 
            + "e003.emp_agent_id"
            + " from ea003 e003" 
            + " where e003.org_agent_id = "+orgAgentId;
          String criterion = "";
          Vector parameters = new Vector();

          if (empId != null && !empId.trim().equals("")) {
            criterion += " ((e003.emp_name, e003.cardnum) = (select b.name, b.card_num from ba002 b, aa002 a where a.emp_info_id = b.id and a.id = ? and a.org_id = ?)) and ";
            parameters.add(empId.trim());
            parameters.add(orgId.trim());
          }

          if (empName != null && !empName.trim().equals("")) {
            criterion += " e003.emp_name = ?  and ";
            parameters.add(empName.trim());
          }
          
          if (cardNum != null && !cardNum.trim().equals("")) {
            criterion += " e003.cardnum = ?  and ";
            parameters.add(cardNum.trim());
          }
          if (empAgentNum != null && !empAgentNum.trim().equals("")) {
            criterion += " e003.emp_agent_num = ?  and ";
            parameters.add(empAgentNum.trim());
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          sql = sql + criterion;

          Query query = session.createSQLQuery(sql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator it = query.list().iterator();

          List temp_List = new ArrayList();
          AgentChgListDTO agentChgListDTO = null;
          Object[] obj = null;
          while (it.hasNext()) {
            agentChgListDTO = new AgentChgListDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              agentChgListDTO.setAgentEmppopId(obj[0].toString());
            }
            if (obj[1] != null) {
              agentChgListDTO.setAgentEmppopkouCode(obj[1].toString());
            }
            if (obj[2] != null) {
              agentChgListDTO.setAgentEmppopname(obj[2].toString());
            }
            if (obj[3] != null) {
              agentChgListDTO.setAgentEmppopCardID(obj[3].toString());
            }
            if (obj[4] != null) {
              agentChgListDTO.setAgentEmppoporgpay(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              agentChgListDTO.setAgentEmppopemppay(new BigDecimal(obj[5].toString()));
            }
            if (obj[6] != null) {
              agentChgListDTO.setAgentEmppopmonthsalary(new BigDecimal(obj[6].toString()));
            }
            temp_List.add(agentChgListDTO);
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
   * 删除职工信息的方法
   * @param empAgentId
   */
  public void deleteAgentInfoByEmpAgentId(final String empAgentId){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
            // 删除EA003
            String hql = "delete EmpAgentDetail empAgentDetail where empAgentDetail.empAgentId=?";
            Query query1 = session.createQuery(hql);
            query1.setParameter(0, new Integer(empAgentId));
            query1.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
