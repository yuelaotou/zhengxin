package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 转出头表
 * 
 * @author 李娟 2007-6-19
 */
public class TranOutHeadDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public TranOutHead queryById(Serializable id) {
   
    Validate.notNull(id);
  
   return (TranOutHead) getHibernateTemplate().get(TranOutHead.class, id);
  }
  /**
   * 插入记录
   * 
   * @param empInfo
   * @return
   */
  public Serializable insert(TranOutHead tranOutHead) {
    Serializable id = null;
    try {
      Validate.notNull(tranOutHead);
      id = getHibernateTemplate().save(tranOutHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }
  
  
  public List queryOutOrg(final String id) {

    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " from TranOutHead toh where toh.tranOutOrg.id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(id));
        return query.list();
      }
    });
    return list;
  }


  // ----------------------------------------------------

  // 查询AA309 Primary key id

  public List FindPkid(final String outOrgid) {
    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
//        String hql = "select toh from TranOutHead toh,TranOutTail tot where toh.id=tot.tranOutHead and toh.tranOutOrg.id = ?";
        String hql = "from TranOutHead toh where toh.tranOutOrg.id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(outOrgid));
        return query.list();
      }
    });

    return list;
  }

//查询票据编号，和状态  
  public List FindStatusNoteNum(final String outOrgid) {
    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from TranOutHead toh where toh.tranOutOrg.id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(outOrgid));
        return query.list();
      }
    });

    return list;
  }

  
  
  
  // 更新 309
  public List UpdateTranoutHead(final String pkid) {

    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from TranOutHead toh where toh.id= ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(pkid));
        return query.list();
      }
    });

    return list;

  }

  /*
   * 默认查询aa309中payStatus = 1 or 3的记录 wzq @return list 转出维护，默认查询
   */

  public List DefaultQueryTbWZQ(final String orderBy, final String order, final int start, final int pageSize,final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

//          String hql = "from TranOutHead tot where tot.tranStatus=1 or tot.tranStatus=3 ";
          String hql = "from TranOutHead tot where (tot.tranStatus=1 or tot.tranStatus=3) and tot.tranOutOrg.id "+securityInfo.getGjjSecurityHqlSQL();

          String ob = orderBy;
          if (ob == null)
            ob = " tot.id ";
          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + "order by " + ob + " " + od;
          
          Query query = session.createQuery(hql);

//          query.setFirstResult(start);
//          if (pageSize > 0)
//            query.setMaxResults(pageSize);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  
  /**转出维护--王志强
   * 根据综合查询出 TranOutHead
   * @param orgOutid     转出单位编号
   * @param orgOutName   转出单位名称
   * @param orgInid      转入单位编号
   * @param orgInName    转入单位名称
   * @param noteNum      票据编号
   * @param docNum       凭证号
   * @param Dates        转出日期
   * @param status       转出状态
   */
  public List DefaultQueryWhereTbWZQ( final String orgOutid,final String orgOutName,
                                  final String orgInid,   final String orgInName,
                                  final String noteNum,final String docNum,
                                  final String Dates, final String status, 
                                  final String orderBy, final String order,
                                  final int start, final int pageSize,final int page,final String tranType,
                                  final SecurityInfo securityInfo)throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = "  from TranOutHead tot where tot.tranOutOrg.id "+securityInfo.getGjjSecurityHqlSQL();
              
              Vector parameters = new Vector();
              String criterion = "";
              if ( orgOutid!= null&&!orgOutid.equals("")) {
                criterion += " to_char(tot.tranOutOrg.id) like ? escape '/'  and ";
                parameters.add("%" + orgOutid +"%");
              }
              if ( orgOutName!= null&&!orgOutName.equals("")) {  //tot.tranOutOrg.orgInfo.name
                criterion += " tot.tranOutOrg.orgInfo.name  like ? escape '/'  and ";//tot.tranInOrg.orgInfo.name
                parameters.add("%" + orgOutName +"%");
              }
              if ( orgInid!= null&&!orgInid.equals("")) {     //  转入单位编号
                criterion += " to_char(tot.tranInOrg.id) like ? escape '/'  and ";
                parameters.add("%" + orgInid +"%");
              }
              if ( orgInName!= null&&!orgInName.equals("")) {
                criterion += "tot.tranInOrg.orgInfo.name  like ? escape '/' and ";
                parameters.add("%" + orgInName +"%");
              }
              if ( noteNum!= null&&!noteNum.equals("")) {
                criterion += "tot.noteNum  like ? escape '/'  and ";
                parameters.add("%" + noteNum +"%");
              }
              if ( docNum!= null&&!docNum.equals("")) {
                criterion += "tot.docNum  like ? escape '/'  and ";
                parameters.add("%" + docNum +"%");
              }
              if ( Dates!= null&&!Dates.equals("")) {
                criterion += "tot.settDate = ?  and ";
                parameters.add(Dates);
              }
              if ( status != null && !status.equals("")) {
                criterion += "tot.tranStatus = ?  and ";
                parameters.add(new BigDecimal(status));
              }
              
              if (tranType != null&& !tranType.equals("")) {
                if(tranType.equals("0")){
                  criterion += " (tot.tranStatus = 1 or tot.tranStatus = 3 ) and ";
                }else{
                  criterion += " tot.tranStatus = ?  and ";
                  parameters.add(new Integer(tranType));
                }
              } 
              if (criterion.length() != 0)
                criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));      
              
              String ob = orderBy;
              
              if (ob == null)
                ob = " tot.id ";
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
  public List DefaultQueryWhereTbWZQ( final String orgOutid,final String orgOutName,
      final String orgInid,   final String orgInName,
      final String noteNum,final String docNum,
      final String Dates,final String Datesa, final String status, 
      final String orderBy, final String order,
      final int start, final int pageSize,final int page,final String tranType,
      final SecurityInfo securityInfo, final String collBankId)throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = "  from TranOutHead tot where tot.tranOutOrg.id "+securityInfo.getGjjSecurityHqlSQL();
              
              Vector parameters = new Vector();
              String criterion = "";
              if ( orgOutid!= null&&!orgOutid.equals("")) {
                criterion += " to_char(tot.tranOutOrg.id) like ? and ";
                parameters.add("%" + orgOutid +"%");
              }
              if ( orgOutName!= null&&!orgOutName.equals("")) {  //tot.tranOutOrg.orgInfo.name
                criterion += " tot.tranOutOrg.orgInfo.name  like ?  and ";//tot.tranInOrg.orgInfo.name
                parameters.add("%" + orgOutName +"%");
              }
              if ( orgInid!= null&&!orgInid.equals("")) {     //  转入单位编号
                criterion += " to_char(tot.tranInOrg.id) like ?   and ";
                parameters.add("%" + orgInid +"%");
              }
              if ( orgInName!= null&&!orgInName.equals("")) {
                criterion += "tot.tranInOrg.orgInfo.name  like ?  and ";
                parameters.add("%" + orgInName +"%");
              }
              if ( noteNum!= null&&!noteNum.equals("")) {
                criterion += "tot.noteNum  like ?   and ";
                parameters.add("%" + noteNum +"%");
              }
              if ( docNum!= null&&!docNum.equals("")) {
                criterion += "tot.docNum  like ?   and ";
                parameters.add("%" + docNum +"%");
              }
              if ( Dates!= null&&!Dates.equals("")) {
                criterion += "tot.settDate >= ?  and ";
                parameters.add(Dates);
              }
              if ( Datesa!= null&&!Datesa.equals("")) {
                criterion += "tot.settDate <= ?  and ";
                parameters.add(Datesa);
              }
              if ( status != null && !status.equals("")) {
                criterion += "tot.tranStatus = ?  and ";
                parameters.add(new BigDecimal(status));
              }
              
              if (tranType != null&& !tranType.equals("")) {
                if(tranType.equals("0")){
                  criterion += " (tot.tranStatus = 2 ) and ";
                }else{
                  criterion += " tot.tranStatus = ?  and ";
                  parameters.add(new Integer(tranType));
                }
              } 
              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " (tot.tranInOrg.orgInfo.collectionBankId = ? or tot.tranOutOrg.orgInfo.collectionBankId = ?) and ";
                parameters.add(collBankId);
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)
                criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));      
              
              String ob = orderBy;
              
              if (ob == null)
                ob = " tot.id ";
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
  public List DefaultQueryWhereTbWZQ_yg( final String orgOutid,final String orgOutName,
      final String orgInid,   final String orgInName,
      final String noteNum,final String docNum,
      final String Dates, final String Datesa, final String status, 
      final String orderBy, final String order,
      final int start, final int pageSize,final int page,final String tranType,
      final SecurityInfo securityInfo, final String collBankId)throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = "  from TranOutHead tot where tot.tranOutOrg.id "+securityInfo.getGjjSecurityHqlSQL();
              
              Vector parameters = new Vector();
              String criterion = "";
              if ( orgOutid!= null&&!orgOutid.equals("")) {
                criterion += " to_char(tot.tranOutOrg.id) like ?   and ";
                parameters.add("%" + orgOutid +"%");
              }
              if ( orgOutName!= null&&!orgOutName.equals("")) {  //tot.tranOutOrg.orgInfo.name
                criterion += " tot.tranOutOrg.orgInfo.name  like ?   and ";//tot.tranInOrg.orgInfo.name
                parameters.add("%" + orgOutName +"%");
              }
              if ( orgInid!= null&&!orgInid.equals("")) {     //  转入单位编号
                criterion += " to_char(tot.tranInOrg.id) like ?   and ";
                parameters.add("%" + orgInid +"%");
              }
              if ( orgInName!= null&&!orgInName.equals("")) {
                criterion += "tot.tranInOrg.orgInfo.name  like ?  and ";
                parameters.add("%" + orgInName +"%");
              }
              if ( noteNum!= null&&!noteNum.equals("")) {
                criterion += "tot.noteNum  like ?   and ";
                parameters.add("%" + noteNum +"%");
              }
              if ( docNum!= null&&!docNum.equals("")) {
                criterion += "tot.docNum  like ?   and ";
                parameters.add("%" + docNum +"%");
              }
              if ( Dates!= null&&!Dates.equals("")) {
                criterion += "tot.settDate >= ?  and ";
                parameters.add(Dates);
              }
              if ( Datesa!= null&&!Datesa.equals("")) {
                criterion += "tot.settDate <= ?  and ";
                parameters.add(Datesa);
              }
              if ( status != null && !status.equals("")) {
                criterion += "tot.tranStatus = ?  and ";
                parameters.add(new BigDecimal(status));
              }
              
              if (tranType != null&& !tranType.equals("")) {
                if(tranType.equals("0")){
                  criterion += " (tot.tranStatus = 2 ) and ";
                }else{
                  criterion += " tot.tranStatus = ?  and ";
                  parameters.add(new Integer(tranType));
                }
              } 
              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " (tot.tranInOrg.orgInfo.collectionBankId = ? or tot.tranOutOrg.orgInfo.collectionBankId = ?) and ";
                parameters.add(collBankId);
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)
                criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));      
              
              String ob = orderBy;
              
              if (ob == null)
                ob = " tot.id ";
              String od = order;
              if (od == null)
                od = "DESC";
              hql = hql + criterion + "order by " + ob + " " + od;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              
              List queryList=query.list();
              return queryList;
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**转出维护--王志强
   * 根据综合查询出 TranOutHead
   * @param orgOutid     转出单位编号
   * @param orgOutName   转出单位名称
   * @param orgInid      转入单位编号
   * @param orgInName    转入单位名称
   * @param noteNum      票据编号
   * @param docNum       凭证号
   * @param Dates        转出日期
   * @param status       转出状态
   */
  public List queryTranOutTotalWZQ( final String orgOutid,final String orgOutName,
                                  final String orgInid,   final String orgInName,
                                  final String noteNum,final String docNum,
                                  final String Dates, final String status,final String tranType,
                                  final SecurityInfo securityInfo, final String collBankId)throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = " from TranOutHead tot where tot.tranOutOrg.id "+securityInfo.getGjjSecurityHqlSQL();
              
              Vector parameters = new Vector();
              String criterion = "";
              if ( orgOutid!= null&&!orgOutid.equals("")) {
                criterion += " to_char(tot.tranOutOrg.id) like ?   and ";
                parameters.add("%" + orgOutid +"%");
              }
              if ( orgOutName!= null&&!orgOutName.equals("")) {  //tot.tranOutOrg.orgInfo.name
                criterion += " tot.tranOutOrg.orgInfo.name  like ?   and ";//tot.tranInOrg.orgInfo.name
                parameters.add("%" + orgOutName +"%");
              }
              if ( orgInid!= null&&!orgInid.equals("")) {     //  转入单位编号
                criterion += " to_char(tot.tranInOrg.id) like ?   and ";
                parameters.add("%" + orgInid +"%");
              }
              if ( orgInName!= null&&!orgInName.equals("")) {
                criterion += "tot.tranInOrg.orgInfo.name  like ?  and ";
                parameters.add("%" + orgInName +"%");
              }
              if ( noteNum!= null&&!noteNum.equals("")) {
                criterion += "tot.noteNum  like ?   and ";
                parameters.add("%" + noteNum +"%");
              }
              if ( docNum!= null&&!docNum.equals("")) {
                criterion += "tot.docNum  like ?   and ";
                parameters.add("%" + docNum +"%");
              }
              if ( Dates!= null&&!Dates.equals("")) {
                criterion += "tot.settDate = ?  and ";
                parameters.add(Dates);
              }
              if ( status != null && !status.equals("")) {
                criterion += "tot.tranStatus = ?  and ";
                parameters.add(new BigDecimal(status));
              }
              
              if (tranType != null&& !tranType.equals("")) {
                if(tranType.equals("0")){
                  criterion += " (tot.tranStatus = 2) and ";
                }else{
                  criterion += " tot.tranStatus = ?  and ";
                  parameters.add(new Integer(tranType));
                }
              } 
              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " (tot.tranInOrg.orgInfo.collectionBankId = ? or tot.tranOutOrg.orgInfo.collectionBankId = ?) and ";
                parameters.add(collBankId);
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)
                criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));      
    
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
    return list;
  }
  public List queryTranOutTotalWZQ( final String orgOutid,final String orgOutName,
      final String orgInid,   final String orgInName,
      final String noteNum,final String docNum,
      final String Dates, final String status,final String tranType,
      final SecurityInfo securityInfo)throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = " from TranOutHead tot where tot.tranOutOrg.id "+securityInfo.getGjjSecurityHqlSQL();
              
              Vector parameters = new Vector();
              String criterion = "";
              if ( orgOutid!= null&&!orgOutid.equals("")) {
                criterion += " to_char(tot.tranOutOrg.id) like ? escape '/'  and ";
                parameters.add("%" + orgOutid +"%");
              }
              if ( orgOutName!= null&&!orgOutName.equals("")) {  //tot.tranOutOrg.orgInfo.name
                criterion += " tot.tranOutOrg.orgInfo.name  like ? escape '/'  and ";//tot.tranInOrg.orgInfo.name
                parameters.add("%" + orgOutName +"%");
              }
              if ( orgInid!= null&&!orgInid.equals("")) {     //  转入单位编号
                criterion += " to_char(tot.tranInOrg.id) like ? escape '/'  and ";
                parameters.add("%" + orgInid +"%");
              }
              if ( orgInName!= null&&!orgInName.equals("")) {
                criterion += "tot.tranInOrg.orgInfo.name  like ? escape '/' and ";
                parameters.add("%" + orgInName +"%");
              }
              if ( noteNum!= null&&!noteNum.equals("")) {
                criterion += "tot.noteNum  like ? escape '/'  and ";
                parameters.add("%" + noteNum +"%");
              }
              if ( docNum!= null&&!docNum.equals("")) {
                criterion += "tot.docNum  like ? escape '/'  and ";
                parameters.add("%" + docNum +"%");
              }
              if ( Dates!= null&&!Dates.equals("")) {
                criterion += "tot.settDate = ?  and ";
                parameters.add(Dates);
              }
              if ( status != null && !status.equals("")) {
                criterion += "tot.tranStatus = ?  and ";
                parameters.add(new BigDecimal(status));
              }
              
              if (tranType != null&& !tranType.equals("")) {
                if(tranType.equals("0")){
                  criterion += " (tot.tranStatus = 1 or tot.tranStatus = 3 ) and ";
                }else{
                  criterion += " tot.tranStatus = ?  and ";
                  parameters.add(new Integer(tranType));
                }
              } 
              if (criterion.length() != 0)
                criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));      
              
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
    return list;
  }
  /**转出维护--王志强
   * 根据综合查询出 TranOutHead
   * @param orgOutid     转出单位编号
   * @param orgOutName   转出单位名称
   * @param orgInid      转入单位编号
   * @param orgInName    转入单位名称
   * @param noteNum      票据编号
   * @param docNum       凭证号
   * @param Dates        转出日期
   * @param status       转出状态
   */
  public int DefaultQueryWhereTbCountWZQ( final String orgOutid,final String orgOutName,
                                  final String orgInid,   final String orgInName,
                                  final String noteNum,final String docNum,
                                  final String Dates, final String status, 
                                  final String orderBy, final String order,
                                  final int start, final int pageSize,final int page,final String tranType,
                                  final SecurityInfo securityInfo)throws NumberFormatException, Exception {
    List list = null;
    int count=0;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = " from TranOutHead tot where tot.tranOutOrg.id "+securityInfo.getGjjSecurityHqlSQL();
             
              Vector parameters = new Vector();
              String criterion = "";
              if ( orgOutid!= null&&!orgOutid.equals("")) {
                criterion += " to_char(tot.tranOutOrg.id) like ? escape '/'  and ";
                parameters.add("%" + orgOutid +"%");
              }
              if ( orgOutName!= null&&!orgOutName.equals("")) {  //tot.tranOutOrg.orgInfo.name
                criterion += " tot.tranOutOrg.orgInfo.name  like ? escape '/'  and ";//tot.tranInOrg.orgInfo.name
                parameters.add("%" + orgOutName +"%");
              }
              if ( orgInid!= null&&!orgInid.equals("")) {     //  转入单位编号
                criterion += " to_char(tot.tranInOrg.id) like ? escape '/'  and ";
                parameters.add("%" + orgInid +"%");
              }
              if ( orgInName!= null&&!orgInName.equals("")) {
                criterion += "tot.tranInOrg.orgInfo.name  like ? escape '/' and ";
                parameters.add("%" + orgInName +"%");
              }
              if ( noteNum!= null&&!noteNum.equals("")) {
                criterion += "tot.noteNum  like ? escape '/'  and ";
                parameters.add("%" + noteNum +"%");
              }
              if ( docNum!= null&&!docNum.equals("")) {
                criterion += "tot.docNum  like ? escape '/'  and ";
                parameters.add("%" + docNum +"%");
              }
              if ( Dates!= null&&!Dates.equals("")) {
                criterion += "tot.settDate = ?  and ";
                parameters.add(Dates);
              }
              if ( status != null && !status.equals("")) {
                criterion += "tot.tranStatus = ?  and ";
                parameters.add(new BigDecimal(status));
              }
              
              if (tranType != null&& !tranType.equals("")) {
                if(tranType.equals("0")){
                  criterion += " (tot.tranStatus = 1 or tot.tranStatus = 3 ) and ";
                }else{
                  criterion += " tot.tranStatus = ?  and ";
                  parameters.add(new Integer(tranType));
                }
              } 
              if (criterion.length() != 0)
                criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));      
    
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
  public int DefaultQueryWhereTbCountWZQ( final String orgOutid,final String orgOutName,
      final String orgInid,   final String orgInName,
      final String noteNum,final String docNum,
      final String Dates, final String Datesa, final String status, 
      final String orderBy, final String order,
      final int start, final int pageSize,final int page,final String tranType,
      final SecurityInfo securityInfo, final String collBankId)throws NumberFormatException, Exception {
    List list = null;
    int count=0;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              String hql = " from TranOutHead tot where tot.tranOutOrg.id "+securityInfo.getGjjSecurityHqlSQL();
              
              Vector parameters = new Vector();
              String criterion = "";
              if ( orgOutid!= null&&!orgOutid.equals("")) {
                criterion += " to_char(tot.tranOutOrg.id) like ?  and ";
                parameters.add("%" + orgOutid +"%");
              }
              if ( orgOutName!= null&&!orgOutName.equals("")) {  //tot.tranOutOrg.orgInfo.name
                criterion += " tot.tranOutOrg.orgInfo.name  like ?  and ";//tot.tranInOrg.orgInfo.name
                parameters.add("%" + orgOutName +"%");
              }
              if ( orgInid!= null&&!orgInid.equals("")) {     //  转入单位编号
                criterion += " to_char(tot.tranInOrg.id) like ?  and ";
                parameters.add("%" + orgInid +"%");
              }
              if ( orgInName!= null&&!orgInName.equals("")) {
                criterion += "tot.tranInOrg.orgInfo.name  like ? and ";
                parameters.add("%" + orgInName +"%");
              }
              if ( noteNum!= null&&!noteNum.equals("")) {
                criterion += "tot.noteNum  like ?  and ";
                parameters.add("%" + noteNum +"%");
              }
              if ( docNum!= null&&!docNum.equals("")) {
                criterion += "tot.docNum  like ?  and ";
                parameters.add("%" + docNum +"%");
              }
              if ( Dates!= null&&!Dates.equals("")) {
                criterion += "tot.settDate >= ?  and ";
                parameters.add(Dates);
              }
              if ( Datesa!= null&&!Datesa.equals("")) {
                criterion += "tot.settDate <= ?  and ";
                parameters.add(Datesa);
              }
              if ( status != null && !status.equals("")) {
                criterion += "tot.tranStatus = ?  and ";
                parameters.add(new BigDecimal(status));
              }
              
              if (tranType != null&& !tranType.equals("")) {
                if(tranType.equals("0")){
                  criterion += " (tot.tranStatus = 2 ) and ";
                }else{
                  criterion += " tot.tranStatus = ?  and ";
                  parameters.add(new Integer(tranType));
                }
              } 
              if (collBankId != null&&!collBankId.equals("")) {
                criterion += " (tot.tranInOrg.orgInfo.collectionBankId = ? or tot.tranOutOrg.orgInfo.collectionBankId = ?) and ";
                parameters.add(collBankId);
                parameters.add(collBankId);
              }
              if (criterion.length() != 0)
                criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));      
              
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
  
  //----------------------    2    -----------------------
  
  public List queryOrgInfo(final String id,String status) {
    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
      throws HibernateException, SQLException{
        String hql = " from TranOutHead toh where toh.tranOutOrg.id = ?" ;
        Query query = session.createQuery(hql);
         query.setParameter(0, new Integer(id));
        return query.list();
      }
    });
    return list;
  }   
//----------------------------------------------------

//查询in_org_id不为空tranStatus>=3的所有记录
  public List queryTranOutListByCriterions_sy(final String inOrgId, final String outOrgId, final String outOrgName,
      final String inOrgName, final String noteNum, final String docNum,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo){
    List list = null;
    list = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {
            String hql = " from TranOutHead tranOutHead ";
            Vector parameters = new Vector();
            String criterion = "";
            if (inOrgId != null && !inOrgId.equals("")) {
              criterion += "to_char(tranOutHead.tranInOrg.id) like ?  and ";
              parameters.add("%"+new Integer(inOrgId)+"%");
            }
            if (outOrgId != null && !outOrgId.equals("")) {
              criterion += "to_char(tranOutHead.tranOutOrg.id) like ?  and ";
              parameters.add("%"+new Integer(outOrgId)+"%");
            }
            if (outOrgName != null && !outOrgName.equals("")) {
              criterion += "tranOutHead.tranOutOrg.orgInfo.name like ?  and ";
              parameters.add("%"+outOrgName+"%");
            }
            if (inOrgName != null && !inOrgName.equals("")) {
              criterion += "tranOutHead.tranInOrg.orgInfo.name like ?  and ";
              parameters.add("%"+inOrgName+"%");
            }
            if (noteNum != null && !noteNum.equals("")) {
              criterion += "tranOutHead.noteNum like ?  and ";
              parameters.add("%"+noteNum+"%");
            }
            if (docNum != null && !docNum.equals("")) {
              criterion += "tranOutHead.docNum like ?  and ";
              parameters.add("%"+docNum+"%");
            }
            
            criterion += " tranOutHead.tranInOrg.orgInfo.id is not null and ";
            criterion += " tranOutHead.id not in (select tranInHead.tranOutHeadId from TranInHead tranInHead where tranInHead.tranOutHeadId=tranOutHead.id ) and";
            criterion += " tranOutHead.tranStatus>= ?  and ";
            parameters.add(new BigDecimal(3.00));
            if (criterion.length() != 0)
            criterion = "where tranOutHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
            String ob = orderBy;
            if (ob == null)
              ob = "tranOutHead.id";
            String od = order;
            if (od == null)
              od = "DESC";
            hql = hql + criterion + " order by " + ob+" "+od;
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
    
    return list;
  }
//查询in_org_id不为空tranStatus>=3的所有记录
  public List countTranOutListByCriterions_sy(final String inOrgId, final String outOrgId, final String outOrgName,
      final String inOrgName, final String noteNum, final String docNum ,final SecurityInfo securityInfo){
    List list = null;
    list = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {
            String hql = " from TranOutHead tranOutHead ";
            Vector parameters = new Vector();
            String criterion = "";
            if (inOrgId != null && !inOrgId.equals("")) {
              criterion += "to_char(tranOutHead.tranInOrg.id) like ?  and ";
              parameters.add("%"+new Integer(inOrgId)+"%");
            }
            if (outOrgId != null && !outOrgId.equals("")) {
              criterion += "to_char(tranOutHead.tranOutOrg.id) like ?  and ";
              parameters.add("%"+new Integer(outOrgId)+"%");
            }
            if (outOrgName != null && !outOrgName.equals("")) {
              criterion += "tranOutHead.tranOutOrg.orgInfo.name like ?  and ";
              parameters.add("%"+outOrgName+"%");
            }
            if (inOrgName != null && !inOrgName.equals("")) {
              criterion += "tranOutHead.tranInOrg.orgInfo.name like ?  and ";
              parameters.add("%"+inOrgName+"%");
            }
            if (noteNum != null && !noteNum.equals("")) {
              criterion += "tranOutHead.noteNum like ?  and ";
              parameters.add("%"+noteNum+"%");
            }
            if (docNum != null && !docNum.equals("")) {
              criterion += "tranOutHead.docNum like ?  and ";
              parameters.add("%"+docNum+"%");
            }
            criterion += "tranOutHead.tranStatus>= ?  and ";
            parameters.add(new BigDecimal(3.00));
            criterion += "tranOutHead.tranInOrg.orgInfo.id is not null and ";
            criterion += " tranOutHead.id not in (select tranInHead.tranOutHeadId from TranInHead tranInHead where tranInHead.tranOutHeadId=tranOutHead.id ) and";
            if (criterion.length() != 0)
            criterion = "where tranOutHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion + " " ;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.list();
          }
          }
    );
    
    return list;
  }
  /*
   * 转出维护---删除单位记录
   */
  public void delete(TranOutHead tranOutHead) {
    try{
       Validate.notNull(tranOutHead);
       getHibernateTemplate().delete(tranOutHead);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  
  //办理转出--添加--判断头表是否存在对应的单位编号
  public List queryHeadOrgId(final String id) {
    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
      throws HibernateException, SQLException{
        String hql = " from TranOutHead toh where toh.tranStatus!=5 and toh.tranOutOrg.id = ?" ;
        Query query = session.createQuery(hql);
         query.setParameter(0, new Integer(id));
        return query.list();
      }
    });
    return list;
  }   
  
  // 查询OfficeCode  Tb`
  public List FindOfficeCodeTb(final String HeadPkid) {
    List list = null;
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " from TranOutHead t where t.id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(HeadPkid));
        return query.list();
      }
    });
    return list;
  }
  public String FindBankName_yg(final String bankid) {
    Object o = "";
    o = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
      SQLException {
        String hql = "select t.coll_bank_name from bb105 t where t.coll_bank_id="+bankid;
        Query query = session.createSQLQuery(hql);
        return query.uniqueResult();
      }
    });
    return o.toString();
  }
  /**
   * 李娟
   * 查询转出头表信息
   * @param orgid
   * @return
   */
  public TranOutHead queryTranOutHeadByOrgid(final String orgid) {
    TranOutHead tranOutHead = null;
    try {
      tranOutHead = (TranOutHead) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select tranOutHead from TranOutHead tranOutHead where tranOutHead.tranStatus=1 and tranOutHead.tranOutOrg.id = ?  ";
              Vector parameters = new Vector();
              parameters.add(new Integer(orgid));
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

    return tranOutHead;


  }
  /**
   * 李娟
   * @param orgid
   * @return
   */
  public List queryTranOutHeadsByOrgid(final String orgid) {
    List tranOutHead = null;
    try {
      tranOutHead = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " from TranOutHead tranOutHead where (tranOutHead.tranStatus=2 or tranOutHead.tranStatus=3 or tranOutHead.tranStatus=4) " +
                  " and tranOutHead.tranOutOrg.id = ?  ";
              Vector parameters = new Vector();
              parameters.add(new Integer(orgid));
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
    return tranOutHead;
  }
  /**
   * 是否已进行转入登记
   * @param tranOutHeadId
   * @return
   */
  public List queryTranOutHeadsByTranOutHeadid(final String tranOutHeadId){
    List list=null;
    list = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = " from TranInHead tranInHead where tranInHead.tranOutHeadId = ?  ";
            Query query = session.createQuery(hql);
              query.setParameter(0, new BigDecimal(tranOutHeadId));
          
            return query.list();
          }
        });
    return list;
  }
  /**
   * 根据错帐凭证号和adjustStatus状态返回AdjustWrongAccountHead
   * @param orgID 单位编号
   * @param securityInfo 权限
   * @return AdjustWrongAccountHead
   */
  public List queryByIDGJP(final String orgID,final SecurityInfo securityInfo) {
    List list =null;
    try{
      list=getHibernateTemplate().executeFind(    
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from AdjustWrongAccountHead adjustWrongAccountHead  where "+
            "adjustWrongAccountHead.org.id=? and adjustWrongAccountHead.adjustStatus not in (5)";//and adjustWrongAccountHead.org.id "+securityInfo.getGjjSecurityHqlSQL();
            Query query = session.createQuery(hql);
             query.setParameter(0,new Integer(orgID));
             return query.list();
        }
        }); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 根据org_id查询306表
   * @param id
   * @return
   */
  public List queryByOrgId(final String id) {  
    List list = null;
    Validate.notNull(id);
    list = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pickHead from PickHead pickHead where pickHead.org.id = ? and pickHead.pickSatatus!=5";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(id));
            return query.list();
          }
        });
    return list;
  }
  public BigDecimal queryAdjustMoney(final String orgId, final String empId) {
    BigDecimal sum = new BigDecimal(0.00);
    List list = null;
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select nvl(a315.adjust_money,0),a314.biz_type"
            + " from aa315 a315, aa314 a314"
            + " where a314.id = a315.adjust_head_id"
            + " and (a314.adjust_status < 5 or a314.adjust_status > 5)"
            + " and a314.org_id = ?" + " and a315.emp_id = ?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, new Integer(orgId));
        query.setParameter(1, new Integer(empId));

        return query.list();
      }
    });
    if (list!=null) {
      for (int i = 0; i < list.size(); i++) {
        Object[] obj = (Object[]) list.get(i);
        BigDecimal temp_money = new BigDecimal(0.00);
        String type = "";
        if (obj[0]!=null&&obj[1]!=null) {
          
          type = obj[1].toString();
          if (type.equals("K")&&temp_money.compareTo(new BigDecimal(0.00))<=0) {
            temp_money = new BigDecimal(obj[0].toString());
          }else if(type.equals("L")&&temp_money.compareTo(new BigDecimal(0.00))>=0){
            temp_money = new BigDecimal(obj[0].toString()).multiply(new BigDecimal(-1));
          }else if(type.equals("G")&&temp_money.compareTo(new BigDecimal(0.00))<=0){
            temp_money = new BigDecimal(obj[0].toString());
          }else{
            temp_money = new BigDecimal(0.00);
          }
        }
        sum = sum.add(temp_money);
      }
    }
    return sum;
  }
  public Object queryPickMoney(final String orgId, final String empId) {
    Object obj = null;
    obj = getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select nvl((a307.pick_pre_balance + a307.pick_cur_balance), 0)"
                + " from aa306 a306, aa307 a307"
                + " where a306.id = a307.pickhead_id"
                + " and a306.org_id = ?"
                + " and a307.emp_id = ?"
                + " and (a306.pick_satatus < 5 or a306.pick_satatus > 5)";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, new Integer(orgId));
            query.setParameter(1, new Integer(empId));

            return query.uniqueResult();
          }
        });
    return obj;
  }
  public String findAA103_DayTime(final String collbankid) {
    String str = "";
    str =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
            String sql = "select t.bank_date from aa103 t where t.bank_id = ?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0,collbankid);
            
            return query.uniqueResult();
          }
        });
    return str;
  }
}



