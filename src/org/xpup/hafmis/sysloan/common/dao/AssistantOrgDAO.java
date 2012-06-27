package org.xpup.hafmis.sysloan.common.dao;

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
import org.xpup.hafmis.sysloan.common.biz.assistantorgpop.dto.AssistantorgpopDTO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.AssistantOrgDTO;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.EvaluateDTO;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.InsuranceDTO;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.NotarialDTO;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.SurrogateDTO;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.dto.AssistantOrgQueryDTO;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.dto.AssistantorgQueryTbDTO;

/**
 * 协作单位信息表PL007
 * 
 * @author 李娟 2007-9-13
 */
public class AssistantOrgDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public AssistantOrg queryById(Serializable id) {
    Validate.notNull(id);
    return (AssistantOrg) getHibernateTemplate().get(AssistantOrg.class, id);
  }

  /**
   * 插入记录
   * 
   * @param assistantOrg
   * @return
   */
  public Serializable insert(AssistantOrg assistantOrg) {
    Serializable id = null;
    try {
      Validate.notNull(assistantOrg);
      id = getHibernateTemplate().save(assistantOrg);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * yuqf------list
   * 
   * @param id
   * @param name
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryAssistantOrgList(final String id, final String name,
      final int start, final int pageSize, final String orderBy,
      final String order, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = " select t.assistant_org_id,t.assistant_org_name from pl007 t where t.assistant_org_type='A' ";

          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !"".equals(id)) {
            criterion += " t.assistant_org_id = ? and ";
            parameters.add(id);
          }

          if (name != null && !"".equals(name)) {
            criterion += " t.assistant_org_name = ? and ";
            parameters.add(name);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " t.assistant_org_id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List templist = new ArrayList();

          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            AssistantorgpopDTO assistantorgpopDTO = new AssistantorgpopDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              assistantorgpopDTO.setId(obj[0].toString());
            }
            if (obj[1] != null) {
              assistantorgpopDTO.setName(obj[1].toString());
            }
            templist.add(assistantorgpopDTO);
          }
          return templist;
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * yuqf-----count list
   * 
   * @param id
   * @param name
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public int queryAssistantOrgList_(final String id, final String name,
      final int start, final int pageSize, final String orderBy,
      final String order, final SecurityInfo securityInfo) {
    int count = 0;
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub

          String hql = " select t.assistant_org_id,t.assistant_org_name from pl007 t where t.assistant_org_type='A' ";

          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !"".equals(id)) {
            criterion += " t.assistant_org_id = ? and ";
            parameters.add(id);
          }

          if (name != null && !"".equals(name)) {
            criterion += " t.assistant_org_name = ? and ";
            parameters.add(name);
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
    count = list.size();
    return count;
  }

  /**
   * 担保方式统计
   * 
   * @author 王野 2007-10-12 查询全部担保公司
   * @return
   */
  public List queryAssistantOrgList() {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select t.assistant_org_id,t.assistant_org_name from pl007 t where t.assistant_org_type='A' ";
          Query query = session.createSQLQuery(hql);

          Iterator iterate = query.list().iterator();

          AssistantOrg assistantOrg = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            assistantOrg = new AssistantOrg();
            if (obj[0] != null && !obj[0].equals(""))
              assistantOrg.setAssistantOrgId(new Integer(obj[0].toString()));
            if (obj[1] != null && !obj[1].equals(""))
              assistantOrg.setAssistantOrgName(obj[1].toString());
            tempList.add(assistantOrg);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 担保方式统计
   * 
   * @author 王野 2007-10-12 根据id查询担保公司名称
   * @param id
   * @return
   */
  public String queryAssistantOrgNameByAssistantOrgId(final String id) {
    String name = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select tt.assistant_org_name from pl007 tt where tt.assistant_org_id = ? ";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);

            return query.uniqueResult();
          }
        });
    return name;
  }

  /**
   * yuqf 根据合同编号查询担保单位名称
   * 
   * @param id
   * @return
   */
  public String queryOrgName(final String id) {
    String name = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select tt.assistant_org_name from pl120 t,pl007 tt where t.assistant_org_id=tt.assistant_org_id and t.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);

            return query.uniqueResult();
          }
        });

    return name;
  }
  //根据担保公司编号查询名称
  public String queryOrgName_(final String id) {
    String name = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select tt.assistant_org_name from pl007 tt where tt.assistant_org_id =?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);

            return query.uniqueResult();
          }
        });

    return name;
  }

  /**
   * yuqf 根据合同名称查询担保单位编号
   * 
   * @param name
   * @return
   */
  public String queryOrgId(final String name) {
    Validate.notNull(name);
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select distinct t.assistant_org_id from pl007 t where t.assistant_org_name=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, name);

            return query.uniqueResult().toString();
          }
        });

    return id;
  }

  /**
   * yuqf 根据合同编号查询ID
   * 
   * @param id
   * @return
   */
  public String queryId(final String id) {
    String idd = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select tt.assistant_org_id from pl120 t,pl007 tt where t.assistant_org_id=tt.assistant_org_id and t.contract_id=?";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            } else {
              return null;
            }

          }
        });

    return idd;
  }

  /**
   * name 杨蒙 更新担保公司维护信息表
   */
  public void update_YM(AssistantOrg assistantOrg) {
    try {
      Validate.notNull(assistantOrg);
      getHibernateTemplate().update(assistantOrg);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * name 杨蒙 获得担保公司维护 协作单位等于A的所有记录 return Integer;
   */
  public int findAssistantOrgTypeCountA_YM() {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select assistantOrg.assistantOrgId from AssistantOrg assistantOrg "
              + "where assistantOrg.assistantOrgType= 'A' ";
          Query query = session.createQuery(hql);
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list.size();
  }

  /**
   * 根据id删除记录
   * 
   * @param id
   */
  public void delete_YM(final Integer id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from AssistantOrg aog where aog.assistantOrgId= ? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, id);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * name 杨蒙 担保公司维护(pl007)全部数据显示列表
   * 
   * @param orderBy 排序列
   * @param order 排序类型
   * @param start 开始
   * @param pageSize 每页显示条数 return List;
   */
  public List findAssistantOrgs_YM(final String orderBy, final String order,
      final int start, final int pageSize, final int page) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select assistantOrg.assistantOrgId,"
              + // id
              "assistantOrg.assistantOrgName,"
              + // 代理机构地址
              "assistantOrg.assistantOrgAddr,"
              + // 办事处
              "assistantOrg.arear,"
              + // 所属地区
              "assistantOrg.paybank,"
              + // 开户银行
              "assistantOrg.contactPrsn,"
              + // 联系人
              "assistantOrg.contactTel,"
              + // 联系人电话
              "assistantOrg.assistantOrgType"
              + " from AssistantOrg assistantOrg where assistantOrg.assistantOrgType='A'";
          String ob = orderBy;
          if (ob == null) {
            ob = " assistantOrg.assistantOrgId ";
          }
          String or = order;
          if (or == null) {
            or = "desc";
          }
          hql = hql + " order by " + ob + " " + or;
          Query query = session.createQuery(hql);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }
          Iterator it = queryList.iterator();
          List assorg = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();

            AssistantOrgDTO surrogateDTO = new AssistantOrgDTO();
            surrogateDTO.setId(new Integer(obj[0].toString()));// id
            if (obj[1] != null)
              surrogateDTO.setAssistantOrgName((obj[1].toString()));// 名字 null
            else
              surrogateDTO.setAssistantOrgName("");
            if (obj[2] != null)
              surrogateDTO.setAssistantOrgAddr((obj[2].toString()));// 地址 null
            else
              surrogateDTO.setAssistantOrgAddr("");
            if (obj[3] != null)
              surrogateDTO.setArear((obj[3].toString()));// 所属地区
            else
              surrogateDTO.setArear("");
            if (obj[4] != null)
              surrogateDTO.setPaybank((obj[4].toString()));// 银行
            else
              surrogateDTO.setPaybank("");
            if (obj[5] != null)
              surrogateDTO.setContactPrsn((obj[5].toString()));// 联系人 null
            else
              surrogateDTO.setContactPrsn("");
            if (obj[6] != null)
              surrogateDTO.setContactTel((obj[6].toString()));// 联系人电话 null
            else
              surrogateDTO.setContactTel("");
            assorg.add(surrogateDTO);
          }
          return assorg;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // 代理机构维护 代理机构维护 代理机构维护 代理机构维护 代理机构维护
  /**
   * name 杨蒙 代理机构维护(pl007)全部数据显示列表
   * 
   * @param orderBy 排序列
   * @param order 排序类型
   * @param start 开始
   * @param pageSize 每页显示条数 return List;
   */
  public List findSurrogate_YM(final String orderBy, final String order,
      final int start, final int pageSize, final int page) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select assistantOrg.assistantOrgId,"
              + // id
              "assistantOrg.assistantOrgName,"
              + // 代理机构地址
              "assistantOrg.assistantOrgAddr,"
              + // 办事处
              "assistantOrg.arear,"
              + // 所属地区
              "assistantOrg.paybank,"
              + // 开户银行
              "assistantOrg.contactPrsn,"
              + // 联系人
              "assistantOrg.contactTel,"
              + // 联系人电话
              "assistantOrg.assistantOrgType,"
              + //注册资金
              "assistantOrg.registerFund"
              + " from AssistantOrg assistantOrg where assistantOrg.assistantOrgType='B'";
          String ob = orderBy;
          if (ob == null) {
            ob = " assistantOrg.assistantOrgId ";
          }
          String or = order;
          if (or == null) {
            or = "desc";
          }
          hql = hql + " order by " + ob + " " + or;
          Query query = session.createQuery(hql);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }
          Iterator it = queryList.iterator();
          List assorg = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();

            SurrogateDTO surrogateDTO = new SurrogateDTO();
            surrogateDTO.setId(new Integer(obj[0].toString()));// id
            if (obj[1] != null)
              surrogateDTO.setAssistantOrgName((obj[1].toString()));// 名字 null
            else
              surrogateDTO.setAssistantOrgName("");
            if (obj[2] != null)
              surrogateDTO.setAssistantOrgAddr((obj[2].toString()));// 地址 null
            else
              surrogateDTO.setAssistantOrgAddr("");
            if (obj[3] != null)
              surrogateDTO.setArear((obj[3].toString()));// 所属地区
            else
              surrogateDTO.setArear("");
            if (obj[4] != null)
              surrogateDTO.setPaybank((obj[4].toString()));// 银行
            else
              surrogateDTO.setPaybank("");
            if (obj[5] != null)
              surrogateDTO.setContactPrsn((obj[5].toString()));// 联系人 null
            else
              surrogateDTO.setContactPrsn("");
            if (obj[6] != null)
              surrogateDTO.setContactTel((obj[6].toString()));// 联系人电话 null
            else
              surrogateDTO.setContactTel("");
            if(obj[8] != null)
              surrogateDTO.setRegisterFund(obj[8].toString());//注册资金
            else
              surrogateDTO.setRegisterFund("");
            assorg.add(surrogateDTO);
          }
          return assorg;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * name 杨蒙 获得代理机构维护 协作单位等于B的所有记录数 return Integer;
   */
  public int findSurrogateTypeCountA_YM() {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select assistantOrg.assistantOrgId from AssistantOrg assistantOrg "
              + "where assistantOrg.assistantOrgType= 'B' ";
          Query query = session.createQuery(hql);
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list.size();
  }

  // 保险公司维护 保险公司维护 保险公司维护 保险公司维护
  /**
   * name 杨蒙 保险公司维护(pl007)全部数据显示列表
   * 
   * @param orderBy 排序列
   * @param order 排序类型
   * @param start 开始
   * @param pageSize 每页显示条数 return List;
   */
  public List findInsurance_YM(final String orderBy, final String order,
      final int start, final int pageSize, final int page) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select assistantOrg.assistantOrgId,"
              + // id
              "assistantOrg.assistantOrgName,"
              + // 代理机构地址
              "assistantOrg.assistantOrgAddr,"
              + // 办事处
              "assistantOrg.arear,"
              + // 所属地区
              "assistantOrg.paybank,"
              + // 开户银行
              "assistantOrg.contactPrsn,"
              + // 联系人
              "assistantOrg.contactTel,"
              + // 联系人电话
              "assistantOrg.assistantOrgType,"
              + //注册资金
              "assistantOrg.registerFund"
              + " from AssistantOrg assistantOrg where assistantOrg.assistantOrgType='C'";
          String ob = orderBy;
          if (ob == null) {
            ob = " assistantOrg.assistantOrgId ";
          }
          String or = order;
          if (or == null) {
            or = "desc";
          }
          hql = hql + " order by " + ob + " " + or;
          Query query = session.createQuery(hql);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }
          Iterator it = queryList.iterator();
          List assorg = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();

            InsuranceDTO surrogateDTO = new InsuranceDTO();
            surrogateDTO.setId(new Integer(obj[0].toString()));// id
            if (obj[1] != null)
              surrogateDTO.setAssistantOrgName((obj[1].toString()));// 名字 null
            else
              surrogateDTO.setAssistantOrgName("");
            if (obj[2] != null)
              surrogateDTO.setAssistantOrgAddr((obj[2].toString()));// 地址 null
            else
              surrogateDTO.setAssistantOrgAddr("");
            if (obj[3] != null)
              surrogateDTO.setArear((obj[3].toString()));// 所属地区
            else
              surrogateDTO.setArear("");
            if (obj[4] != null)
              surrogateDTO.setPaybank((obj[4].toString()));// 银行
            else
              surrogateDTO.setPaybank("");
            if (obj[5] != null)
              surrogateDTO.setContactPrsn((obj[5].toString()));// 联系人 null
            else
              surrogateDTO.setContactPrsn("");
            if (obj[6] != null)
              surrogateDTO.setContactTel((obj[6].toString()));// 联系人电话 null
            else
              surrogateDTO.setContactTel("");
            if(obj[8] != null)
              surrogateDTO.setRegisterFund(obj[8].toString());//注册资金
            else
              surrogateDTO.setRegisterFund("");
            assorg.add(surrogateDTO);
          }
          return assorg;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * name 杨蒙 保险公司维护 协作单位等于C的所有记录 return Integer;
   */
  public int findInsuranceTypeCountA_YM() {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select assistantOrg.assistantOrgId from AssistantOrg assistantOrg "
              + "where assistantOrg.assistantOrgType= 'C' ";
          Query query = session.createQuery(hql);
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list.size();
  }

  // 评估机构维护 评估机构维护 评估机构维护 评估机构维护
  /**
   * name 杨蒙 评估机构维护(pl007)全部数据显示列表
   * 
   * @param orderBy 排序列
   * @param order 排序类型
   * @param start 开始
   * @param pageSize 每页显示条数 return List;
   */
  public List findEvaluate_YM(final String orderBy, final String order,
      final int start, final int pageSize, final int page) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select assistantOrg.assistantOrgId,"
              + // id
              "assistantOrg.assistantOrgName,"
              + // 代理机构地址
              "assistantOrg.assistantOrgAddr,"
              + // 办事处
              "assistantOrg.arear,"
              + // 所属地区
              "assistantOrg.paybank,"
              + // 开户银行
              "assistantOrg.contactPrsn,"
              + // 联系人
              "assistantOrg.contactTel,"
              + // 联系人电话
              "assistantOrg.assistantOrgType,"
              + //注册资金
              "assistantOrg.registerFund"
              + " from AssistantOrg assistantOrg where assistantOrg.assistantOrgType='D'";
          String ob = orderBy;
          if (ob == null) {
            ob = " assistantOrg.assistantOrgId ";
          }
          String or = order;
          if (or == null) {
            or = "desc";
          }
          hql = hql + " order by " + ob + " " + or;
          Query query = session.createQuery(hql);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }
          Iterator it = queryList.iterator();
          List assorg = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();

            EvaluateDTO surrogateDTO = new EvaluateDTO();
            surrogateDTO.setId(new Integer(obj[0].toString()));// id
            if (obj[1] != null)
              surrogateDTO.setAssistantOrgName((obj[1].toString()));// 名字 null
            else
              surrogateDTO.setAssistantOrgName("");
            if (obj[2] != null)
              surrogateDTO.setAssistantOrgAddr((obj[2].toString()));// 地址 null
            else
              surrogateDTO.setAssistantOrgAddr("");
            if (obj[3] != null)
              surrogateDTO.setArear((obj[3].toString()));// 所属地区
            else
              surrogateDTO.setArear("");
            if (obj[4] != null)
              surrogateDTO.setPaybank((obj[4].toString()));// 银行
            else
              surrogateDTO.setPaybank("");
            if (obj[5] != null)
              surrogateDTO.setContactPrsn((obj[5].toString()));// 联系人 null
            else
              surrogateDTO.setContactPrsn("");
            if (obj[6] != null)
              surrogateDTO.setContactTel((obj[6].toString()));// 联系人电话 null
            else
              surrogateDTO.setContactTel("");
            if(obj[8] != null)
              surrogateDTO.setRegisterFund(obj[8].toString());//注册资金
            else
              surrogateDTO.setRegisterFund("");
            assorg.add(surrogateDTO);
          }

          return assorg;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * name 杨蒙 获得评估公司维护 协作单位等于D的所有记录数 return Integer;
   */
  public int findEvaluateTypeCountA_YM() {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select assistantOrg.assistantOrgId from AssistantOrg assistantOrg "
              + "where assistantOrg.assistantOrgType= 'D' ";
          Query query = session.createQuery(hql);
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list.size();
  }

  // 公证处 公证处 公证处 公证处 公证处
  /**
   * name 杨蒙 公证处维护(pl007)全部数据显示列表
   * 
   * @param orderBy 排序列
   * @param order 排序类型
   * @param start 开始
   * @param pageSize 每页显示条数 return List;
   */
  public List findNotarial_YM(final String orderBy, final String order,
      final int start, final int pageSize, final int page) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select assistantOrg.assistantOrgId,"
              + // id
              "assistantOrg.assistantOrgName,"
              + // 代理机构地址
              "assistantOrg.assistantOrgAddr,"
              + // 办事处
              "assistantOrg.arear,"
              + // 所属地区
              "assistantOrg.paybank,"
              + // 开户银行
              "assistantOrg.contactPrsn,"
              + // 联系人
              "assistantOrg.contactTel,"
              + // 联系人电话
              "assistantOrg.assistantOrgType"
              + " from AssistantOrg assistantOrg where assistantOrg.assistantOrgType='E'";
          String ob = orderBy;
          if (ob == null) {
            ob = " assistantOrg.assistantOrgId ";
          }
          String or = order;
          if (or == null) {
            or = "desc";
          }
          hql = hql + " order by " + ob + " " + or;
          Query query = session.createQuery(hql);
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List queryList = query.list();

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            queryList = query.list();
          }
          Iterator it = queryList.iterator();
          List assorg = new ArrayList();
          Object obj[] = null;

          while (it.hasNext()) {
            obj = (Object[]) it.next();

            NotarialDTO notarialDTO = new NotarialDTO();
            notarialDTO.setId(new Integer(obj[0].toString()));// id
            if (obj[1] != null)
              notarialDTO.setAssistantOrgName((obj[1].toString()));// 名字 null
            else
              notarialDTO.setAssistantOrgName("");
            if (obj[2] != null)
              notarialDTO.setAssistantOrgAddr((obj[2].toString()));// 地址 null
            else
              notarialDTO.setAssistantOrgAddr("");
            if (obj[3] != null)
              notarialDTO.setArear((obj[3].toString()));// 所属地区
            else
              notarialDTO.setArear("");
            if (obj[4] != null)
              notarialDTO.setPaybank((obj[4].toString()));// 银行
            else
              notarialDTO.setPaybank("");
            if (obj[5] != null)
              notarialDTO.setContactPrsn((obj[5].toString()));// 联系人 null
            else
              notarialDTO.setContactPrsn("");
            if (obj[6] != null)
              notarialDTO.setContactTel((obj[6].toString()));// 联系人电话 null
            else
              notarialDTO.setContactTel("");
            assorg.add(notarialDTO);
          }

          return assorg;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * name 杨蒙 获得公证处维护 协作单位等于E的所有记录数 return Integer;
   */
  public int findNotarialTypeCountA_YM() {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select assistantOrg.assistantOrgId from AssistantOrg assistantOrg "
              + "where assistantOrg.assistantOrgType= 'E' ";
          Query query = session.createQuery(hql);
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list.size();
  }

  /**
   * 代理机构查询 author wsh
   * 
   * @param assistantOrgName
   * @param artfclprsn
   * @param assistantType
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public List queryAssistantOrgList_wsh(final String assistantOrgName,
      final String artfclprsn, final String assistantType, final String inArea,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a.assistant_org_id assitantorgid,"
            + "a.assistant_org_type assistantorgtype,"
            + " a.assistant_org_name assistantorgname,"
            + " a.assistant_org_addr assistantorgadd," + " a.arear arear,"
            + " a.paybank paybank," + " a.contact_prsn contactprsn,"
            + " a.contact_tel contacttel," + "a.photo_url " + " from pl007 a  ";
        Vector parameters = new Vector();
        String criterion = "";
        if (assistantOrgName != null && !assistantOrgName.equals("")) {
          criterion += " a.assistant_org_name like ? and ";
          parameters.add("%"+assistantOrgName+"%");
        }
        if (artfclprsn != null && !artfclprsn.equals("")) {
          criterion += " a.artfclprsn like ? and ";
          parameters.add("%"+artfclprsn+"%");
        }
        if (assistantType != null && !assistantType.equals("")) {
          criterion += " a.assistant_org_type like ? and ";
          parameters.add(assistantType);
        }
        if (inArea != null && !inArea.equals("")) {
          criterion += " a.arear like ? and ";
          parameters.add(inArea);
        }
        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null) {
          ob = " assitantorgid ";
        }
        String or = order;
        if (or == null) {
          or = "desc";
        }
        hql = hql + criterion + " order by " + ob + " " + or;
        Query query = session.createSQLQuery(hql);
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        List queryList = query.list();
        Iterator it = queryList.iterator();
        List assorg = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          AssistantOrgQueryDTO notarialDTO = new AssistantOrgQueryDTO();
          notarialDTO.setId(new Integer(obj[0].toString()));// id
          if (obj[1] != null) {
            if (obj[1].toString().equals("A")) {
              notarialDTO.setAssistantOrgType("担保公司");// 类型 null
            }
            if (obj[1].toString().equals("B")) {
              notarialDTO.setAssistantOrgType("代理机构");// 类型 null
            }
            if (obj[1].toString().equals("C")) {
              notarialDTO.setAssistantOrgType("保险公司");// 类型 null
            }
            if (obj[1].toString().equals("D")) {
              notarialDTO.setAssistantOrgType("评估机构");// 类型 null
            }
            if (obj[1].toString().equals("E")) {
              notarialDTO.setAssistantOrgType("公证处");// 类型 null
            }

          }

          else
            notarialDTO.setAssistantOrgType("");
          if (obj[2] != null)
            notarialDTO.setAssistantOrgName((obj[2].toString()));// 名字 null
          else
            notarialDTO.setAssistantOrgName("");
          if (obj[3] != null)
            notarialDTO.setAssistantOrgAddr((obj[3].toString()));// 地址 null
          else
            notarialDTO.setAssistantOrgAddr("");
          if (obj[4] != null)
            notarialDTO.setArear((obj[4].toString()));// 所属地区
          else
            notarialDTO.setArear("");
          if (obj[5] != null)
            notarialDTO.setPaybank((obj[5].toString()));// 银行
          else
            notarialDTO.setPaybank("");
          if (obj[6] != null)
            notarialDTO.setContactPrsn((obj[6].toString()));// 联系人 null
          else
            notarialDTO.setContactPrsn("");
          if (obj[7] != null)
            notarialDTO.setContactTel((obj[7].toString()));// 联系人电话 null
          else
            notarialDTO.setContactTel("");
          if (obj[7] != null)
            notarialDTO.setContactTel((obj[7].toString()));// 联系人电话 null
          else
            notarialDTO.setContactTel("");
          if(obj[8] != null){
            notarialDTO.setPhotoUrl(obj[8].toString());//扫描图片
          }
          assorg.add(notarialDTO);
        }

        return assorg;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 代理机构查询-列表打印 author wsh
   * 
   * @param assistantOrgName
   * @param artfclprsn
   * @param assistantType
   * @return
   */
  public List queryAssistantOrgListCount_wsh(final String assistantOrgName,
      final String artfclprsn, final String assistantType, final String inArea) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a.assistant_org_id assitantorgid,"
              + "a.assistant_org_type assistantorgtype,"
              + " a.assistant_org_name assistantorgname,"
              + " a.assistant_org_addr assistantorgadd," + " a.arear arear,"
              + " a.paybank paybank," + " a.contact_prsn contactprsn,"
              + " a.contact_tel contacttel " + "from pl007 a  ";
          Vector parameters = new Vector();
          String criterion = "";
          if (assistantOrgName != null && !assistantOrgName.equals("")) {
            criterion += " a.assistant_org_name like ? and ";
            parameters.add("%"+assistantOrgName+"%");
          }
          if (artfclprsn != null && !artfclprsn.equals("")) {
            criterion += " a.artfclprsn like ? and ";
            parameters.add("%"+artfclprsn+"%");
          }
          if (assistantType != null && !assistantType.equals("")) {
            criterion += " a.assistant_org_type like ? and ";
            parameters.add(assistantType);
          }
          if (inArea != null && !inArea.equals("")) {
            criterion += " a.arear like ? and ";
            parameters.add(inArea);
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List queryList = query.list();
          Iterator it = queryList.iterator();
          List assorg = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            AssistantOrgQueryDTO notarialDTO = new AssistantOrgQueryDTO();
            notarialDTO.setId(new Integer(obj[0].toString()));// id
            if (obj[1] != null) {
              if (obj[1].toString().equals("A")) {
                notarialDTO.setAssistantOrgType("担保公司");// 类型 null
              }
              if (obj[1].toString().equals("B")) {
                notarialDTO.setAssistantOrgType("代理机构");// 类型 null
              }
              if (obj[1].toString().equals("C")) {
                notarialDTO.setAssistantOrgType("保险公司");// 类型 null
              }
              if (obj[1].toString().equals("D")) {
                notarialDTO.setAssistantOrgType("评估机构");// 类型 null
              }
              if (obj[1].toString().equals("E")) {
                notarialDTO.setAssistantOrgType("公证处");// 类型 null
              }

            } else
              notarialDTO.setAssistantOrgName("");
            if (obj[2] != null)
              notarialDTO.setAssistantOrgName((obj[2].toString()));// 名字 null
            else
              notarialDTO.setAssistantOrgName("");
            if (obj[3] != null)
              notarialDTO.setAssistantOrgAddr((obj[3].toString()));// 地址 null
            else
              notarialDTO.setAssistantOrgAddr("");
            if (obj[4] != null)
              notarialDTO.setArear((obj[4].toString()));// 所属地区
            else
              notarialDTO.setArear("");
            if (obj[5] != null)
              notarialDTO.setPaybank((obj[5].toString()));// 银行
            else
              notarialDTO.setPaybank("");
            if (obj[6] != null)
              notarialDTO.setContactPrsn((obj[6].toString()));// 联系人 null
            else
              notarialDTO.setContactPrsn("");
            if (obj[7] != null)
              notarialDTO.setContactTel((obj[7].toString()));// 联系人电话 null
            else
              notarialDTO.setContactTel("");
            if (obj[7] != null)
              notarialDTO.setContactTel((obj[7].toString()));// 联系人电话 null
            else
              notarialDTO.setContactTel("");
            assorg.add(notarialDTO);
          }

          return assorg;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 代理机构查询-弹出框 author wsh
   * 
   * @param id
   * @return
   */
  public List findAssistantOrgInfo_wsh(final String id) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select assistanto0_.ASSISTANT_ORG_ID     as ASSISTANT1_69_0_,"
              + " assistanto0_.ASSISTANT_ORG_NAME   as ASSISTANT2_69_0_,"
              + " assistanto0_.ARTFCLPRSN           as ARTFCLPRSN69_0_,"
              + " assistanto0_.CODE                 as CODE69_0_,"
              + " assistanto0_.ASSISTANT_ORG_ADDR   as ASSISTANT5_69_0_,"
              + " assistanto0_.BASED_DATE           as BASED6_69_0_,"
              + " assistanto0_.ARTFCLPRSN_CARD_KIND as ARTFCLPRSN7_69_0_,"
              + " assistanto0_.ARTFCLPRSN_CARD_NUM  as ARTFCLPRSN8_69_0_,"
              + " assistanto0_.ALLOW_DEPT           as ALLOW9_69_0_,"
              + " assistanto0_.ALLOW_ID             as ALLOW10_69_0_,"
              + " assistanto0_.AGREEMENT_START_DATE as AGREEMENT11_69_0_,"
              + " assistanto0_.AGREEMENT_END_DATE   as AGREEMENT12_69_0_,"
              + " assistanto0_.REGISTER_FUND        as REGISTER13_69_0_,"
              + " assistanto0_.PAYBANK              as PAYBANK69_0_,"
              + " assistanto0_.PAYACC               as PAYACC69_0_,"
              + " assistanto0_.CONTACT_PRSN         as CONTACT16_69_0_,"
              + " assistanto0_.CONTACT_TEL          as CONTACT17_69_0_,"
              + " assistanto0_.BUSINESS             as BUSINESS69_0_,"
              + " assistanto0_.REMARK               as REMARK69_0_ "
              + " from PL007 assistanto0_ ";
          Vector parameters = new Vector();
          String criterion = "";
          if (id != null && !id.equals("")) {
            criterion += " assistanto0_.assistant_org_id=? and ";
            parameters.add(id);
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List queryList = query.list();
          Iterator it = queryList.iterator();
          List assorg = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            AssistantorgQueryTbDTO notarialDTO = new AssistantorgQueryTbDTO();
            notarialDTO.setAssistantOrgId(obj[0].toString());// id
            if (obj[1] != null)
              notarialDTO.setAssistantOrgName((obj[1].toString()));// 名字 null
            else
              notarialDTO.setAssistantOrgName("");
            if (obj[2] != null)
              notarialDTO.setAssistantOrgType((obj[2].toString()));// 类型 null
            else
              notarialDTO.setAssistantOrgType("");
            if (obj[2] != null)
              notarialDTO.setArtfclprsn(obj[2].toString());// 法人代表 null
            else
              notarialDTO.setArtfclprsn("");
            if (obj[3] != null)
              notarialDTO.setCode(obj[3].toString());// 组织机构代码 null
            else
              notarialDTO.setCode("");
            if (obj[4] != null)
              notarialDTO.setAssistantOrgAddr(obj[4].toString());// 代理机构地址 null
            else
              notarialDTO.setAssistantOrgAddr("");
            if (obj[5] != null)
              notarialDTO.setBasedDate(obj[5].toString());// 成立日期 null
            else
              notarialDTO.setBasedDate("");
            if (obj[6] != null)
              notarialDTO.setArtfclprsnCardKind(obj[6].toString());// 法人证件类型
            // null
            else
              notarialDTO.setArtfclprsnCardKind("");
            if (obj[7] != null)
              notarialDTO.setArtfclprsnCardNum(obj[7].toString());// 法人证件号码 null
            else
              notarialDTO.setArtfclprsnCardNum("");
            if (obj[8] != null)
              notarialDTO.setAllowDept(obj[8].toString());// 批准机关 null
            else
              notarialDTO.setAllowDept("");
            if (obj[9] != null)
              notarialDTO.setAllowId((obj[9].toString()));// 批准文号 null
            else
              notarialDTO.setAllowId("");
            if (obj[10] != null)
              notarialDTO.setAgreementStartDate((obj[10].toString()));// 协议签订日期
            else
              notarialDTO.setAgreementStartDate("");
            if (obj[11] != null)
              notarialDTO.setAgreementEndDate((obj[11].toString()));// 协议到期日期
            else
              notarialDTO.setAgreementEndDate("");
            if (obj[12] != null)
              notarialDTO.setRegisterFund(new BigDecimal((obj[12].toString())));// 注册资金
            // null
            else
              notarialDTO.setRegisterFund(null);
            if (obj[13] != null)
              notarialDTO.setPaybank((obj[13].toString()));// 开户银行
            else
              notarialDTO.setPaybank("");
            if (obj[14] != null)
              notarialDTO.setPayacc((obj[14].toString()));// 开户银行帐号
            else
              notarialDTO.setPaybank("");
            if (obj[15] != null)
              notarialDTO.setContactPrsn((obj[15].toString()));// 联系人 null
            else
              notarialDTO.setContactPrsn("");
            if (obj[16] != null)
              notarialDTO.setContactTel((obj[16].toString()));// 联系人电话 null
            else
              notarialDTO.setContactTel("");
            if (obj[17] != null)
              notarialDTO.setBusiness((obj[17].toString()));// 职务 null
            else
              notarialDTO.setBusiness("");
            if (obj[18] != null)
              notarialDTO.setRemark((obj[18].toString()));// 备注 null
            else
              notarialDTO.setRemark("");
            assorg.add(notarialDTO);
          }
          return assorg;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 合同查询 贷款余额
   * @param startDate
   * @param endDate
   * @param loanBankId
   * @return
   */
  public BigDecimal queryPl500CorpusConteract(final String bizDate,
      final String loanBankId) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
          interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select nvl(sum(t.corpus),0) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();
              
              if (bizDate!=null && !"".equals(bizDate)) {
                criterion += " t.add_date  = ?  and ";
                parameters.add(bizDate);
              }
              if(loanBankId!=null && !"".equals(loanBankId)){
                criterion += " t.loan_bank_id=? and ";
                parameters.add(loanBankId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              
              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query
                    .uniqueResult().toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }
  /**
   * 合同查询 贷款余额
   * @param startDate
   * @param endDate
   * @param loanBankId
   * @return
   */
  public BigDecimal queryPl500CorpusConteract_(
      final String loanBankId) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
          interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " select nvl(sum(t.corpus),0) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();
              if(loanBankId!=null && !"".equals(loanBankId)){
                criterion += " t.loan_bank_id=? and ";
                parameters.add(loanBankId);
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              
              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query
                    .uniqueResult().toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }
  public BigDecimal queryPl500CorpusConteract_office(
      final String office) {
    BigDecimal interest = new BigDecimal(0.00);
    try {
      interest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            
            public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
              
              String hql = " select nvl(sum(t.corpus),0) from pl500 t ";
              String criterion = "";
              Vector parameters = new Vector();
              if(office!=null && !"".equals(office)){
                criterion += " t.office=? and ";
                parameters.add(office);
              }
              if (criterion.length() != 0)
                criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              
              if (query.uniqueResult() != null) {
                BigDecimal interest = new BigDecimal(query
                    .uniqueResult().toString());
                return interest;
              } else {
                return new BigDecimal(0.00);
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return interest;
  }
}
