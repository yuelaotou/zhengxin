package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;
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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.domain.entity.ReportMng;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.dto.ReportMngDTO;
import org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.form.DefineReportAF;
import org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.form.QueryReportAF;

public class ReportMngDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public ReportMng queryById(Serializable id) {
    Validate.notNull(id);
    return (ReportMng) getHibernateTemplate().get(ReportMng.class, id);
  }

  /**
   * 插入记录
   * 
   * @param ReportMng
   * @return
   */
  public Serializable insert(ReportMng reportMng) {
    Serializable id = null;
    try {
      Validate.notNull(reportMng);
      id = getHibernateTemplate().save(reportMng);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 修改
   * 
   * @param reportMng
   */
  public void update(ReportMng reportMng) {
    try {
      Validate.notNull(reportMng);
      getHibernateTemplate().update(reportMng);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 根据条件查询创建报表的列表
   * 
   * @param tablenamequery
   * @param createtimestart
   * @param createtimeend
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
  public List findcreateReportMngList_WL(final String tablenamequery,
      final String createtimestart, final String createtimeend,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select reportMng.id,reportMng.tailtableNameChinese,reportMng.colspan,reportMng.rowspan,reportMng.createtime,reportMng.createperson "
              + " from ReportMng reportMng  ";
          Vector parameters = new Vector();
          String criterion = "";

          criterion = " where reportMng.bookId='" + securityInfo.getBookId()
              + "' and ";

          if (tablenamequery != null && !tablenamequery.equals("")) {
            criterion += " reportMng.tailtableNameChinese = ?  and ";
            parameters.add(tablenamequery);
          }
          if (createtimestart != null && !createtimestart.equals("")) {//to_date('20071023', 'yyyy-mm-dd HH24:MI:ss')
            criterion += " reportMng.createtime > to_date( ?, 'yyyy-mm-dd HH24:MI:ss' ) and ";
            parameters.add(createtimestart);
          }
          if (createtimeend != null && !createtimeend.equals("")) {
            criterion += " reportMng.createtime < to_date( ?, 'yyyy-mm-dd HH24:MI:ss' ) and ";
            parameters.add(createtimeend);
          }

          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " reportMng.id  ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          ReportMngDTO reportMngDTO = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            reportMngDTO = new ReportMngDTO();
            reportMngDTO.setId(obj[0].toString());
            reportMngDTO.setTailtableNameChinese(obj[1].toString());
            reportMngDTO.setColspan(obj[2].toString());
            reportMngDTO.setRowspan(obj[3].toString());
            reportMngDTO.setCreatetime(obj[4].toString());
            reportMngDTO.setCreateperson(obj[5].toString());
            tableList.add(reportMngDTO);
          }

          List queryList = tableList;

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
   * 根据条件查询创建报表的列表总条数
   * 
   * @param tablenamequery
   * @param createtimestart
   * @param createtimeend
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public int findcreateReportMngListCount_WL(final String tablenamequery,
      final String createtimestart, final String createtimeend,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = new ArrayList();
    ;
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select reportMng.id " + " from ReportMng reportMng  ";
          Vector parameters = new Vector();
          String criterion = "";

          criterion = " where reportMng.bookId='" + securityInfo.getBookId()
              + "' and ";

          if (tablenamequery != null && !tablenamequery.equals("")) {
            criterion += " reportMng.tailtableNameChinese = ?  and ";
            parameters.add(tablenamequery);
          }
          if (createtimestart != null && !createtimestart.equals("")) {
            criterion += " reportMng.createtime > to_date( ?, 'yyyy-mm-dd HH24:MI:ss' ) and ";
            parameters.add(createtimestart);
          }
          if (createtimeend != null && !createtimeend.equals("")) {
            criterion += " reportMng.createtime < to_date( ?, 'yyyy-mm-dd HH24:MI:ss' ) and ";
            parameters.add(createtimeend);
          }

          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

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
    return list.size();
  }

  /**
   * 根据条件查询出要打印的创建报表的列表
   * 
   * @param tablenamequery
   * @param createtimestart
   * @param createtimeend
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findcreateReportMngAllList_WL(final String tablenamequery,
      final String createtimestart, final String createtimeend,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select reportMng.id,reportMng.tailtableNameChinese,reportMng.colspan,reportMng.rowspan,reportMng.createtime,reportMng.createperson "
              + " from ReportMng reportMng  ";
          Vector parameters = new Vector();
          String criterion = "";

          criterion = " where reportMng.bookId='" + securityInfo.getBookId()
              + "' and ";

          if (tablenamequery != null && !tablenamequery.equals("")) {
            criterion += " reportMng.tailtableNameChinese = ?  and ";
            parameters.add(tablenamequery);
          }
          if (createtimestart != null && !createtimestart.equals("")) {
            criterion += " reportMng.createtime > to_date( ?, 'yyyy-mm-dd HH24:MI:ss' ) and ";
            parameters.add(createtimestart);
          }
          if (createtimeend != null && !createtimeend.equals("")) {
            criterion += " reportMng.createtime < to_date( ?, 'yyyy-mm-dd HH24:MI:ss' ) and ";
            parameters.add(createtimeend);
          }

          if (criterion.length() != 0)
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          ReportMngDTO reportMngDTO = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            reportMngDTO = new ReportMngDTO();
            reportMngDTO.setId(obj[0].toString());
            reportMngDTO.setTailtableNameChinese(obj[1].toString());
            reportMngDTO.setColspan(obj[2].toString());
            reportMngDTO.setRowspan(obj[3].toString());
            reportMngDTO.setCreatetime(obj[4].toString());
            reportMngDTO.setCreateperson(obj[5].toString());
            tableList.add(reportMngDTO);
          }

          List queryList = tableList;

          return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 创建报表尾表 修改尾表结构
   * 
   * @param reportrow
   * @param flag:0-新建；1-修改
   */
  public void createTailTable_WL(final ReportMng reportMng, final String flag)
      throws NumberFormatException, Exception {
    try {
      String row = "";
      String newname = reportMng.getTailtableNameChinese();
      String newcol = reportMng.getColspan();
      String newrow = reportMng.getRowspan();

      if (flag.equals("0")) {
        int rowspan = Integer.parseInt(reportMng.getRowspan()) + 1;
        for (int i = 1; i < rowspan; i++) {
          row = row + "  row".concat("" + i) + " Varchar2(100), ";
          if (i == Integer.parseInt(reportMng.getRowspan())) {
            row = " id varchar(1000), "
                + row.substring(0, row.lastIndexOf(","));
          }
        }
      }

      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      CallableStatement cs = conn
          .prepareCall("{call fncreatereporttable(?,?,?,?,?,?)}");
      cs.setString(1, reportMng.getTailtableName());
      cs.setString(2, row);
      cs.setString(3, flag);
      cs.setString(4, newname);
      cs.setString(5, newcol);
      cs.setString(6, newrow);
      cs.execute();

    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("创建报表尾表失败!!!");
    }
  }

  /**
   * 查询报表的行数
   * 
   * @param tablename
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public int getReportCol_WL(final String tablename)
      throws NumberFormatException, Exception {
    int count = 0;
    List temp_count = new ArrayList();
    try {
      temp_count = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select t.id from " + tablename + " t ";
          SQLQuery query = session.createSQLQuery(hql);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (temp_count != null && temp_count.size() != 0) {
      count = temp_count.size();
    }
    return count;
  }

  /**
   * 判断报表中是否有内容
   * 
   * @param tablename
   * @param col
   * @param row
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public String getContentByColRow_WL(final String tablename, final int col,
      final int row, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    String content = "";
    try {
      content = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " select t.row" + row + " from " + tablename
                  + " t  where t.id='" + col + "'";
              SQLQuery query = session.createSQLQuery(hql);
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return content;
  }

  /**
   * 删除创建报表
   * 
   * @param reporttableid
   * @param URLID
   * @param tablename
   * @param tablename1
   * @param tablename2
   * @throws NumberFormatException
   * @throws Exception
   */
  public void deleteReport_WL(final String reporttableid, final String URLID,
      final String tablename, final String tablename1, final String tablename2)
      throws NumberFormatException, Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();

      // 删除报表尾表
      String hql0 = " drop table " + tablename + " ";

      String[] urlid = new String[2];
      urlid = URLID.split(",");
      // 删除权限表中创建报表产生的信息
      String hql1 = " delete from " + tablename1 + " t where t.MENUITEM_ID='"
          + urlid[0] + "' and t.MENUITEM_ID='" + urlid[1] + "'";
      String hql2 = " delete from " + tablename2 + " t where t.MENUITEM_ID='"
          + urlid[0] + "' and t.MENUITEM_ID='" + urlid[1] + "'";
      // 删除权限CA104表中创建报表产生的信息
      String hql3 = " delete from CA104 t where t.ID='" + urlid[0] + "'";
      String hql4 = " delete from CA104 t where t.ID='" + urlid[1] + "'";
      // 删除权限FN400表中创建报表产生的信息
      String hql5 = " delete from FN400 t where t.ID='" + reporttableid + "'";

      Statement statement = conn.createStatement();
      statement.executeUpdate(hql0);
      statement.executeUpdate(hql1);
      statement.executeUpdate(hql2);
      statement.executeUpdate(hql3);
      statement.executeUpdate(hql4);
      statement.executeUpdate(hql5);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 查询报表头表信息
   * 
   * @param tablename
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public ReportMng queryHeadReportMessageByTablename_WL(final String tablename,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    ReportMng reportMng = null;
    try {
      reportMng = (ReportMng) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " from ReportMng reportMng where reportMng.tailtableName=? and reportMng.bookId=? ";

              Query query = session.createQuery(hql);
              query.setString(0, tablename);
              query.setString(1, securityInfo.getBookId());

              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return reportMng;
  }

  /**
   * 查询报表信息
   * 
   * @param defineReportAF
   * @param reportMng
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public DefineReportAF queryReportMessageByTablename_WL(
      final DefineReportAF defineReportAF, final ReportMng reportMng,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      String hql ="";
      Statement statement = conn.createStatement();
      ResultSet rs=null;
      
      for(int i=1;i<(Integer.parseInt(reportMng.getColspan())+1);i++){
        for(int j=1;j<(Integer.parseInt(reportMng.getRowspan())+1);j++){
          hql = " select t.row"+j+" from "+reportMng.getTailtableName()+" t where t.id="+i+" ";
          rs=statement.executeQuery(hql);
            while(rs.next()){
              defineReportAF.setValue("v"+i+"_"+j+"", rs.getString(1));
              defineReportAF.setValue(""+i+"_"+j+"", rs.getString(1));
            }
          if(defineReportAF.getValue("v"+i+"_"+j+"")==null||defineReportAF.getValue("v"+i+"_"+j+"").toString().equals("")){
            defineReportAF.setValue("v"+i+"_"+j+"", "");
            defineReportAF.setValue(""+i+"_"+j+"", "");      
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return defineReportAF;
  }
  
  /**
   * 保存定义报表的信息
   * @param defineReportAF
   * @param pagination
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public void saveReportMessage_WL(final DefineReportAF defineReportAF,
      final Pagination pagination, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    Connection conn = getHibernateTemplate().getSessionFactory()
    .openSession().connection();
    Statement statement = conn.createStatement();
    try {
      
      String tablename=(String)pagination.getQueryCriterions().get("tablename");
      String col=(String)pagination.getQueryCriterions().get("col");
      String row=(String)pagination.getQueryCriterions().get("row");
      
      String hql = "";
      
      
      hql="delete from "+tablename+"";
      statement.executeUpdate(hql);
      
      for(int i=1;i<(Integer.parseInt(col)+1);i++){
        hql = " insert into "+tablename+" t ( t.id,";
        for(int j=1;j<(Integer.parseInt(row)+1);j++){
          hql+="t.row"+j+",";
        }
        hql = hql.substring(0, hql.lastIndexOf(","));
        hql+=" ) values ( "+i+",";
        for(int j=1;j<(Integer.parseInt(row)+1);j++){
          if(defineReportAF.getValue("v"+i+"_"+j+"")!=null && !defineReportAF.getValue("v"+i+"_"+j+"").toString().equals("")){
            if(!defineReportAF.getValue("v"+i+"_"+j+"").toString().substring(0, 1).equals("!")){
              defineReportAF.setValue(""+i+"_"+j+"", defineReportAF.getValue("v"+i+"_"+j+""));
            }
          }else{
            defineReportAF.setValue(""+i+"_"+j+"", "");
          }
          hql+="'"+defineReportAF.getValue(""+i+"_"+j+"")+"',";
          
        }
        hql = hql.substring(0, hql.lastIndexOf(","));
        hql+=" ) ";
        statement.executeUpdate(hql);
      }

    } catch (Exception e) {
      conn.rollback();
      e.printStackTrace();
    }
  }
  /**
   * 查询报表信息
   * @param queryReportAF
   * @param reportMng
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public QueryReportAF queryReportMessage_WL(
      final QueryReportAF queryReportAF, final ReportMng reportMng,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      String hql ="";
      Statement statement = conn.createStatement();
      ResultSet rs=null;
      
      for(int i=1;i<(Integer.parseInt(reportMng.getColspan())+1);i++){
        for(int j=1;j<(Integer.parseInt(reportMng.getRowspan())+1);j++){
          hql = " select t.row"+j+" from "+reportMng.getTailtableName()+" t where t.id="+i+" ";
          rs=statement.executeQuery(hql);
            while(rs.next()){
              queryReportAF.setValue(""+i+"_"+j+"", rs.getString(1));
            }
          if(queryReportAF.getValue(""+i+"_"+j+"")==null||queryReportAF.getValue(""+i+"_"+j+"").toString().equals("")){
            queryReportAF.setValue(""+i+"_"+j+"", "");      
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return queryReportAF;
  }
}
