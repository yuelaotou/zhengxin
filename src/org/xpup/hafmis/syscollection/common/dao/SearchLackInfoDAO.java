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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.SearchLackInfo;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.AddpayInfoDto;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.dto.OrgpaymentstatisticsDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoContentDTO;

public class SearchLackInfoDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public SearchLackInfo queryById(Integer id) {
    Validate.notNull(id);
    return (SearchLackInfo) getHibernateTemplate()
        .get(SearchLackInfo.class, id);
  }

  /**
   * 插入记录
   * 
   * @param searchLackInfo
   * @return
   */
  public Serializable insert(SearchLackInfo searchLackInfo) {
    Serializable id = null;
    try {
      Validate.notNull(searchLackInfo);
      id = getHibernateTemplate().save(searchLackInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * 生成欠缴数据
   */

  public void executeCreateSearchLackInfo() {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call temp }");
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * 王菱 根据条件查询单位欠缴信息
   * 
   * @param officecode
   * @param bankcode
   * @param natureOfUnits
   * @param authorities
   * @param orgid
   * @param orgname
   * @param setmonthstart
   * @param setmonthend
   * @param inArea
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findSearchLackInfoListByCriterions_WL(final String officecode,
      final String bankcode, final String natureOfUnits,
      final String authorities, final String orgcode, final String orgname,
      final String setmonthstart, final String setmonthend,
      final String inArea, final String orderBy, final String order,
      final int start, final int pageSize, final int page,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct org2_.id, "
              + " orginfo3_.name, "
              + " (select count(t.id) from aa305 t where (t.org_pay_real = 0 or t.emp_pay_real = 0) and t.org_id = org2_.id  and t.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ) as lackMonths , "
              + " (select sum(t1.org_pay) from aa305 t1 where t1.org_pay_real = 0 and t1.org_id=org2_.id  and t1.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ), "
              + " (select sum(t2.emp_pay) from aa305 t2 where t2.emp_pay_real = 0 and t2.org_id=org2_.id  and t2.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ), "
              + " (select sum(t1.org_pay) from aa305 t1 where t1.org_pay_real = 0 and t1.org_id=org2_.id  and t1.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ) + "
              + " (select sum(t2.emp_pay) from aa305 t2 where t2.emp_pay_real = 0 and t2.org_id=org2_.id  and t2.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ), "
              + " orginfo3_.transactor_name, "
              + " orginfo3_.tel, "
              + " orginfo3_.address,org2_.org_pay_month,org2_.emp_pay_month,org2_.org_rate,org2_.emp_rate, "
              + "(select sum(a101.credit-a101.debit) from aa101 a101 where a101.biz_status=5 and a101.org_id=org2_.id) as orgyue,"
              + " (select sum(a305.org_pay+a305.emp_pay) from aa305 a305 where a305.org_id=org2_.id) as yjehj,"
              + "(select count(a002.pk_id) from aa002 a002 where a002.org_id=org2_.id and a002.pay_status in(1,3,4)) as zcjcrs "
              + " from AA001 org2_, BA001 orginfo3_  "
              + " where org2_.id "
              + securityInfo.getGjjSecuritySQL()
              + " and (select count(t.id) from aa305 t where (t.org_pay_real = 0 or t.emp_pay_real = 0) and t.org_id = org2_.id  and t.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ) != 0 " + " and org2_.ORGINFO_ID = orginfo3_.ID and ";

          Vector parameters = new Vector();
          String criterion = "";

          if (officecode != null && !officecode.equals("")) {
            criterion += " orginfo3_.officecode = ?  and ";
            parameters.add(officecode);
          }
          if (bankcode != null && !bankcode.equals("")) {
            criterion += " orginfo3_.collection_bank_id = ?  and ";
            parameters.add(bankcode);
          }
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " org2_.id = ?  and ";
            parameters.add(orgcode);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orginfo3_.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && setmonthend != null && !setmonthend.equals("")) {
            criterion += " ( (select count(t.id) from aa305 t where (t.org_pay_real = 0 or t.emp_pay_real = 0) and t.org_id = org2_.id) between ? and ? ) and ";
            parameters.add(setmonthstart);
            parameters.add(setmonthend);
          }

          if (inArea != null && !inArea.equals("")) {
            criterion += " orginfo3_.region = ? and ";
            parameters.add(inArea);
          }

          String ob = orderBy;
          if (ob == null)
            ob = " org2_.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion + "order by " + ob + " " + od;
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          SearchLackInfoContentDTO clearaccountDTO = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearaccountDTO = new SearchLackInfoContentDTO();
            clearaccountDTO.setOrgcode(obj[0].toString());
            clearaccountDTO.setOrgname(obj[1].toString());
            clearaccountDTO.setLackMonths(obj[2].toString());
            clearaccountDTO.setOrgpayLack(obj[3].toString());
            clearaccountDTO.setEmppayLack(obj[4].toString());
            clearaccountDTO.setSumpayLack(obj[5].toString());
            if (obj[6] != null) {
              clearaccountDTO.setTransactor_name(obj[6].toString());
            }
            if (obj[7] != null) {
              clearaccountDTO.setTel(obj[7].toString());
            }
            if (obj[8] != null) {
              clearaccountDTO.setAddress(obj[8].toString());
            }
            if (obj[9] != null) {
              clearaccountDTO.setOrg_pay_month(obj[9].toString());
            }
            if (obj[10] != null) {
              clearaccountDTO.setEmp_pay_month(obj[10].toString());
            }
            if (obj[11] != null) {
              clearaccountDTO.setOrg_rate(obj[11].toString());
            }
            if (obj[12] != null) {
              clearaccountDTO.setEmp_rate(obj[12].toString());
            }
            if (obj[13] != null) {
              clearaccountDTO.setOrgyue(obj[13].toString());
            }
            if (obj[14] != null) {
              clearaccountDTO.setYjehj(obj[14].toString());
            }
            if (obj[15] != null) {
              clearaccountDTO.setZcjcrs(obj[15].toString());
            }
            tableList.add(clearaccountDTO);
          }

          List queryList = tableList;

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList = tableList;
          }
          return queryList;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public List findSearchLackInfoListByCriterions_oldsys(final String orgid_old,
      final String orgname_old, final String yearMonth, final int start,
      final int pageSize, final int page, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.unitacc,a.unitname,t.hndindate,t.nmonasdhndinamnt,"
              + " t.handinmod,t.nmonusdhndinamnt,t.nmonesdhndinamnt,t.hndintran_id from tb_hndin_tran t,tb_unit_mng a"
              + " where t.is_short = '1' and t.handinmod in('1','2') and t.handinst='5' and t.unitacc=a.unitacc ";

          Vector parameters = new Vector();
          String criterion = "";

          if (orgid_old != null && !orgid_old.equals("")) {
            criterion += " t.unitacc = ?  and ";
            parameters.add(orgid_old);
          }
          if (orgname_old != null && !orgname_old.equals("")) {
            criterion += " a.unitname like ?  and ";
            parameters.add("%" + orgname_old + "%");
          }
          if (yearMonth != null && !yearMonth.equals("")) {
            criterion += " substr(t.hndindate,0,4)||substr(t.hndindate,6,7) = ?  and ";
            parameters.add(yearMonth);
          }

          if (criterion.length() != 0) {
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion + "order by t.hndindate";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();
          SearchLackInfoContentDTO clearaccountDTO = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();

            clearaccountDTO = new SearchLackInfoContentDTO();
            clearaccountDTO.setOrgid_old(obj[0].toString());
            clearaccountDTO.setOrgname_old(obj[1].toString());
            clearaccountDTO.setLack_month_old(obj[2].toString());
            clearaccountDTO.setLack_pay_old(obj[3].toString());

            if (obj[4].toString().equals("1")) {
              clearaccountDTO.setLack_status_old("欠缴单位");
              clearaccountDTO.setLack_pay_old(obj[5].toString());
            }
            if (obj[4].toString().equals("2")) {
              clearaccountDTO.setLack_status_old("欠缴职工");
              clearaccountDTO.setLack_pay_old(obj[6].toString());
            }
            if (obj[4].toString().equals("3")) {
              clearaccountDTO.setLack_status_old("均欠缴");
            }
            // 设置主键id
            clearaccountDTO.setId(obj[7].toString());
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

  public List findSearchLackInfoListByCriterions_oldsys_all(
      final String orgid_old, final String orgname_old, final String yearMonth,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.unitacc,a.unitname,t.hndindate,t.nmonasdhndinamnt,t.handinmod from tb_hndin_tran t,tb_unit_mng a"
              + " where t.is_short = '1' and t.handinmod in('1','2') and t.handinst='5' and t.unitacc=a.unitacc ";

          Vector parameters = new Vector();
          String criterion = "";

          if (orgid_old != null && !orgid_old.equals("")) {
            criterion += " t.unitacc = ?  and ";
            parameters.add(orgid_old);
          }
          if (orgname_old != null && !orgname_old.equals("")) {
            criterion += " a.unitname like ?  and ";
            parameters.add("%" + orgname_old + "%");
          }
          if (yearMonth != null && !yearMonth.equals("")) {
            criterion += " substr(t.hndindate,0,4)||substr(t.hndindate,6,7) = ?  and ";
            parameters.add(yearMonth);
          }

          if (criterion.length() != 0) {
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();
          SearchLackInfoContentDTO clearaccountDTO = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();

            clearaccountDTO = new SearchLackInfoContentDTO();

            clearaccountDTO.setOrgid_old(obj[0].toString());
            clearaccountDTO.setOrgname_old(obj[1].toString());
            clearaccountDTO.setLack_month_old(obj[2].toString());
            clearaccountDTO.setLack_pay_old(obj[3].toString());

            if (obj[4].toString().equals("1")) {
              clearaccountDTO.setLack_status_old("欠缴单位");
            }
            if (obj[4].toString().equals("2")) {
              clearaccountDTO.setLack_status_old("欠缴职工");
            }
            if (obj[4].toString().equals("3")) {
              clearaccountDTO.setLack_status_old("均欠缴");
            }

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
   * 根据查询条件查询欠缴信息分页显示
   * 
   * @param officecode
   * @param bankcode
   * @param natureOfUnits
   * @param authorities
   * @param orgcode
   * @param orgname
   * @param setmonthstart
   * @param setmonthend
   * @param inArea
   * @param yearMonth
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
  public List findSearchLackInfoListByCriterions_jj(final String officecode,
      final String bankcode, final String natureOfUnits,
      final String authorities, final String orgcode, final String orgname,
      final String setmonthstart, final String setmonthend,
      final String inArea, final String yearMonth, final String orgratebeg,
      final String orgrateend, final String empratebeg,
      final String emprateend, final String orderBy, final String order,
      final int start, final int pageSize, final int page,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select aa305.org_id,"
              + " ba001.name,"
              + " aa305.pay_status1 + aa305.pay_status3 + aa305.pay_status4,"
              + " aa305.org_rate,"
              + " aa305.emp_rate,"
              + " aa305.reservea_a,"
              + " aa305.org_money,"
              + " aa305.emp_money,"
              + " aa305.org_money + aa305.emp_money,"
              + " aa305.org_month,"
              + " aa305.emp_month,"
              + " aa305.org_pay_month,"
              + " aa305.emp_pay_month,"
              + " ba001.character,"
              + " aa305.balance,"
              + " ba001.transactor_name,"
              + " ba001.tel,"
              + " ba001.address,"
              + " aa305.reservea_a*aa305.org_rate+aa305.reservea_a*aa305.emp_rate "
              + " from AA305_1 aa305, BA001 ba001"
              + " where aa305.reservea_b = ba001.id and ba001.openstatus=2 and (aa305.org_money + aa305.emp_money)>0 and aa305.org_id "
              + securityInfo.getGjjSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (officecode != null && !officecode.equals("")) {
            criterion += " ba001.officecode = ?  and ";
            parameters.add(officecode);
          }
          if (bankcode != null && !bankcode.equals("")) {
            criterion += " ba001.collection_bank_id = ?  and ";
            parameters.add(bankcode);
          }
          if (natureOfUnits != null && !natureOfUnits.equals("")) {
            criterion += " ba001.character = ?  and ";
            parameters.add(new Integer(natureOfUnits).toString());
          }
          if (authorities != null && !authorities.equals("")) {
            criterion += " ba001.dept_in_charge = ?  and ";
            parameters.add(new Integer(authorities).toString());
          }
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " aa305.org_id = ?  and ";
            parameters.add(new Integer(orgcode).toString());
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " ba001.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && setmonthend != null && !setmonthend.equals("")) {
            criterion += " ( (aa305.org_month >=" + setmonthstart
                + " and aa305.org_month<=" + setmonthend + ")"
                + " or (aa305.emp_month  >=" + setmonthstart
                + " and aa305.emp_month<=" + setmonthend + ")) and ";
          }
          if (yearMonth != null && !yearMonth.equals("")) {
            criterion += " aa305.year_month = ? and ";
            parameters.add(yearMonth);
          }
          if (inArea != null && !inArea.equals("")) {
            criterion += " ba001.region = ? and ";
            parameters.add(inArea);
          }
          if (orgratebeg != null && !orgratebeg.equals("")) {
            criterion += " aa305.org_rate >= ? and ";
            parameters.add(new BigDecimal(orgratebeg));
          }
          if (orgrateend != null && !orgrateend.equals("")) {
            criterion += " aa305.org_rate <= ? and ";
            parameters.add(new BigDecimal(orgrateend));
          }
          if (empratebeg != null && !empratebeg.equals("")) {
            criterion += " aa305.emp_rate >= ? and ";
            parameters.add(new BigDecimal(empratebeg));
          }
          if (emprateend != null && !emprateend.equals("")) {
            criterion += " aa305.emp_rate <= ? and ";
            parameters.add(new BigDecimal(emprateend));
          }

          String ob = orderBy;
          if (ob == null)
            ob = " aa305.org_id ";

          String od = order;
          if (od == null)
            od = "DESC";

          if (criterion.length() != 0) {
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            SearchLackInfoContentDTO clearaccountDTO = new SearchLackInfoContentDTO();
            clearaccountDTO.setOrgcode(obj[0].toString());
            clearaccountDTO.setOrgname(obj[1].toString());
            clearaccountDTO.setZcjcrs(obj[2].toString());
            clearaccountDTO.setOrgRate(new BigDecimal(obj[3].toString()));
            clearaccountDTO.setEmpRate(new BigDecimal(obj[4].toString()));
            clearaccountDTO.setSalaryBase(new BigDecimal(obj[5].toString()));
            clearaccountDTO.setOrgPay(new BigDecimal(obj[6].toString()));
            clearaccountDTO.setEmpPay(new BigDecimal(obj[7].toString()));
            clearaccountDTO.setSumPay(new BigDecimal(obj[8].toString()));
            clearaccountDTO.setOrgMonth(obj[9].toString());
            clearaccountDTO.setEmpMonth(obj[10].toString());
            clearaccountDTO.setOrgPayMonth(obj[11].toString());
            clearaccountDTO.setEmpPayMonth(obj[12].toString());
            clearaccountDTO.setCharacter(obj[13].toString());
            clearaccountDTO.setOrgBalance(new BigDecimal(obj[14].toString()));
            if (obj[15] != null) {
              clearaccountDTO.setTransactor_name(obj[15].toString());
            }
            if (obj[16] != null) {
              clearaccountDTO.setTel(obj[16].toString());
            }
            if (obj[17] != null) {
              clearaccountDTO.setAddress(obj[17].toString());
            }
            if (obj[18] != null && !"".equals(obj[18].toString().trim())) {
              clearaccountDTO.setSumPay_1(new BigDecimal(obj[18].toString()));
            }
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
   * 根据查询条件查询欠缴信息
   * 
   * @param officecode
   * @param bankcode
   * @param natureOfUnits
   * @param authorities
   * @param orgcode
   * @param orgname
   * @param setmonthstart
   * @param setmonthend
   * @param inArea
   * @param yearMonth
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findSearchLackInfoListByCriterionsALL_jj(final String officecode,
      final String bankcode, final String natureOfUnits,
      final String authorities, final String orgcode, final String orgname,
      final String setmonthstart, final String setmonthend,
      final String inArea, final String yearMonth, final String orgratebeg,
      final String orgrateend, final String empratebeg,
      final String emprateend, final String orderBy, final String order,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select aa305.org_id,"
              + " ba001.name,"
              + " aa305.pay_status1 + aa305.pay_status3 + aa305.pay_status4,"
              + " aa305.org_rate,"
              + " aa305.emp_rate,"
              + " aa305.reservea_a,"
              + " aa305.org_money,"
              + " aa305.emp_money,"
              + " aa305.org_money + aa305.emp_money,"
              + " aa305.org_month,"
              + " aa305.emp_month,"
              + " aa305.org_pay_month,"
              + " aa305.emp_pay_month,"
              + " ba001.character,"
              + " aa305.balance,"
              + " ba001.transactor_name,"
              + " ba001.tel,"
              + " ba001.address,"
              + " ba001.artificial_person,"
              + " ba001.paybank_name,"
              + " ba001.paybank_account_num,"
              + " ba001.postalcode,"
              + " ba001.paydate,"
              + " aa305.reservea_a*aa305.org_rate+aa305.reservea_a*aa305.emp_rate "
              + " from AA305_1 aa305, BA001 ba001"
              + " where aa305.reservea_b = ba001.id and ba001.openstatus=2 and (aa305.org_money + aa305.emp_money)>0 and aa305.org_id "
              + securityInfo.getGjjSecuritySQL();

          Vector parameters = new Vector();
          String criterion = "";

          if (officecode != null && !officecode.equals("")) {
            criterion += " ba001.officecode = ?  and ";
            parameters.add(officecode);
          }
          if (bankcode != null && !bankcode.equals("")) {
            criterion += " ba001.collection_bank_id = ?  and ";
            parameters.add(bankcode);
          }
          if (natureOfUnits != null && !natureOfUnits.equals("")) {
            criterion += " ba001.character = ?  and ";
            parameters.add(new Integer(natureOfUnits).toString());
          }
          if (authorities != null && !authorities.equals("")) {
            criterion += " ba001.dept_in_charge = ?  and ";
            parameters.add(new Integer(authorities).toString());
          }
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " aa305.org_id = ?  and ";
            parameters.add(new Integer(orgcode).toString());
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " ba001.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (orgratebeg != null && !orgratebeg.equals("")) {
            criterion += " aa305.org_rate >= ? and ";
            parameters.add(new BigDecimal(orgratebeg));
          }
          if (orgrateend != null && !orgrateend.equals("")) {
            criterion += " aa305.org_rate <= ? and ";
            parameters.add(new BigDecimal(orgrateend));
          }
          if (empratebeg != null && !empratebeg.equals("")) {
            criterion += " aa305.emp_rate >= ? and ";
            parameters.add(new BigDecimal(empratebeg));
          }
          if (emprateend != null && !emprateend.equals("")) {
            criterion += " aa305.emp_rate <= ? and ";
            parameters.add(new BigDecimal(emprateend));
          }
          if (setmonthstart != null && !setmonthstart.equals("")
              && setmonthend != null && !setmonthend.equals("")) {
            criterion += " ( (aa305.org_month >=" + setmonthstart
                + " and aa305.org_month<=" + setmonthend + ")"
                + " or (aa305.emp_month  >=" + setmonthstart
                + " and aa305.emp_month<=" + setmonthend + ")) and ";
          }
          if (yearMonth != null && !yearMonth.equals("")) {
            criterion += " aa305.year_month = ? and ";
            parameters.add(yearMonth);
          }
          if (inArea != null && !inArea.equals("")) {
            criterion += " ba001.region = ? and ";
            parameters.add(inArea);
          }

          String ob = orderBy;
          if (ob == null)
            ob = " aa305.org_id ";

          String od = order;
          if (od == null)
            od = "DESC";

          if (criterion.length() != 0) {
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }
          hql = hql + criterion + "order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            SearchLackInfoContentDTO clearaccountDTO = new SearchLackInfoContentDTO();
            clearaccountDTO.setOrgcode(obj[0].toString());
            clearaccountDTO.setOrgname(obj[1].toString());
            clearaccountDTO.setZcjcrs(obj[2].toString());
            clearaccountDTO.setOrgRate(new BigDecimal(obj[3].toString()));
            clearaccountDTO.setEmpRate(new BigDecimal(obj[4].toString()));
            clearaccountDTO.setSalaryBase(new BigDecimal(obj[5].toString()));
            clearaccountDTO.setOrgPay(new BigDecimal(obj[6].toString()));
            clearaccountDTO.setEmpPay(new BigDecimal(obj[7].toString()));
            clearaccountDTO.setSumPay(new BigDecimal(obj[8].toString()));
            clearaccountDTO.setOrgMonth(obj[9].toString());
            clearaccountDTO.setEmpMonth(obj[10].toString());
            clearaccountDTO.setOrgPayMonth(obj[11].toString());
            clearaccountDTO.setEmpPayMonth(obj[12].toString());
            clearaccountDTO.setCharacter(obj[13].toString());
            clearaccountDTO.setOrgBalance(new BigDecimal(obj[14].toString()));
            if (obj[15] != null) {
              clearaccountDTO.setTransactor_name(obj[15].toString());
            }
            if (obj[16] != null) {
              clearaccountDTO.setTel(obj[16].toString());
            }
            if (obj[17] != null) {
              clearaccountDTO.setAddress(obj[17].toString());
            }
            if (obj[18] != null) {
              clearaccountDTO.setArtificialPerson(obj[18].toString());
            }
            if (obj[19] != null) {
              clearaccountDTO.setPaybankName(obj[19].toString());
            }
            if (obj[20] != null) {
              clearaccountDTO.setPaybankAcc(obj[20].toString());
            }
            if (obj[21] != null) {
              clearaccountDTO.setPostalcode(obj[21].toString());
            }
            if (obj[22] != null) {
              clearaccountDTO.setPaydate(obj[22].toString());
            }
            if (obj[23] != null && !"".equals(obj[23].toString().trim())) {
              clearaccountDTO.setSumPay_1(new BigDecimal(obj[23].toString()));
            }
            tableList.add(clearaccountDTO);
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
   * 王菱 根据条件查询单位欠缴信息的(全部)
   * 
   * @param officecode
   * @param bankcode
   * @param natureOfUnits
   * @param authorities
   * @param orgid
   * @param orgname
   * @param setmonthstart
   * @param setmonthend
   * @param inArea
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findSearchLackInfoListCountByCriterions_WL(
      final String officecode, final String bankcode,
      final String natureOfUnits, final String authorities,
      final String orgcode, final String orgname, final String setmonthstart,
      final String setmonthend, final String inArea,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct org2_.id, "
              + " orginfo3_.name, "
              + " (select count(t.id) from aa305 t where (t.org_pay_real = 0 or t.emp_pay_real = 0) and t.org_id = org2_.id  and t.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ) as lackMonths , "
              + " (select sum(t1.org_pay) from aa305 t1 where t1.org_pay_real = 0 and t1.org_id=org2_.id  and t1.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ), "
              + " (select sum(t2.emp_pay) from aa305 t2 where t2.emp_pay_real = 0 and t2.org_id=org2_.id  and t2.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ), "
              + " (select sum(t1.org_pay) from aa305 t1 where t1.org_pay_real = 0 and t1.org_id=org2_.id  and t1.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ) + "
              + " (select sum(t2.emp_pay) from aa305 t2 where t2.emp_pay_real = 0 and t2.org_id=org2_.id  and t2.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ), "
              + " orginfo3_.transactor_name, "
              + " orginfo3_.tel, "
              + " orginfo3_.address,org2_.org_pay_month,org2_.emp_pay_month,org2_.org_rate,org2_.emp_rate ,"
              + "(select (a101.credit-a101.debit) from aa101 a101 where a101.biz_status=5 and a101.org_id=org2_.id) as orgyue,"
              + " (select (a305.org_pay+a305.emp_pay) from aa305 a305 where a305.org_id=org2_.id) as yjehj,"
              + "(select count(a002.pk_id) from aa002 a002 where a002.org_id=org2_.id and a002.pay_status in(1,3,4)) as zcjcrs "
              + " from AA001 org2_, BA001 orginfo3_ "
              + " where org2_.id "
              + securityInfo.getGjjSecuritySQL()
              + " and (select count(t.id) from aa305 t where (t.org_pay_real = 0 or t.emp_pay_real = 0) and t.org_id = org2_.id  and t.year_month< "
              + securityInfo.getUserInfo().getBizDate().substring(0, 6)
              + " ) != 0 " + " and org2_.ORGINFO_ID = orginfo3_.ID and ";

          Vector parameters = new Vector();
          String criterion = "";

          if (officecode != null && !officecode.equals("")) {
            criterion += " orginfo3_.officecode = ?  and ";
            parameters.add(officecode);
          }
          if (bankcode != null && !bankcode.equals("")) {
            criterion += " orginfo3_.collection_bank_id = ?  and ";
            parameters.add(bankcode);
          }
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " org2_.id = ?  and ";
            parameters.add(orgcode);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " orginfo3_.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }

          if (setmonthstart != null && !setmonthstart.equals("")
              && setmonthend != null && !setmonthend.equals("")) {
            criterion += " ( (select count(t.id) from aa305 t where (t.org_pay_real = 0 or t.emp_pay_real = 0) and t.org_id = org2_.id) between ? and ? ) and ";
            parameters.add(setmonthstart);
            parameters.add(setmonthend);
          }

          if (inArea != null && !inArea.equals("")) {
            criterion += " orginfo3_.region = ? and ";
            parameters.add(inArea);
          }

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion;
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          SearchLackInfoContentDTO clearaccountDTO = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearaccountDTO = new SearchLackInfoContentDTO();
            clearaccountDTO.setOrgcode(obj[0].toString());
            clearaccountDTO.setOrgname(obj[1].toString());
            clearaccountDTO.setLackMonths(obj[2].toString());
            clearaccountDTO.setOrgpayLack(obj[3].toString());
            clearaccountDTO.setEmppayLack(obj[4].toString());
            clearaccountDTO.setSumpayLack(obj[5].toString());
            if (obj[6] != null) {
              clearaccountDTO.setTransactor_name(obj[6].toString());
            }
            if (obj[7] != null) {
              clearaccountDTO.setTel(obj[7].toString());
            }
            if (obj[8] != null) {
              clearaccountDTO.setAddress(obj[8].toString());
            }
            if (obj[9] != null) {
              clearaccountDTO.setOrg_pay_month(obj[9].toString());
            }
            if (obj[10] != null) {
              clearaccountDTO.setEmp_pay_month(obj[10].toString());
            }
            if (obj[11] != null) {
              clearaccountDTO.setOrg_rate(obj[11].toString());
            }
            if (obj[12] != null) {
              clearaccountDTO.setEmp_rate(obj[12].toString());
            }
            if (obj[13] != null) {
              clearaccountDTO.setOrgyue(obj[13].toString());
            }
            if (obj[14] != null) {
              clearaccountDTO.setYjehj(obj[14].toString());
            }
            if (obj[15] != null) {
              clearaccountDTO.setZcjcrs(obj[15].toString());
            }
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
   * 王菱 根据单位ID查询单位欠缴信息
   * 
   * @param orgid
   * @throws NumberFormatException
   * @throws Exception
   */
  public SearchLackInfoContentDTO findSearchLackInfoOneByCriterions_WL(
      final String orgcode, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    SearchLackInfoContentDTO searchLackInfoContentDTO = new SearchLackInfoContentDTO();
    try {
      searchLackInfoContentDTO = (SearchLackInfoContentDTO) getHibernateTemplate()
          .execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select distinct org2_.id, "
                  + " orginfo3_.name, "
                  + " (select count(t.id) from aa305 t where (t.org_pay_real = 0 or t.emp_pay_real = 0) and t.org_id = org2_.id  and t.year_month< "
                  + securityInfo.getUserInfo().getBizDate().substring(0, 6)
                  + " ) as lackMonths , "
                  + " (select sum(t1.org_pay) from aa305 t1 where t1.org_pay_real = 0 and t1.org_id=org2_.id  and t1.year_month< "
                  + securityInfo.getUserInfo().getBizDate().substring(0, 6)
                  + " ), "
                  + " (select sum(t2.emp_pay) from aa305 t2 where t2.emp_pay_real = 0 and t2.org_id=org2_.id  and t2.year_month< "
                  + securityInfo.getUserInfo().getBizDate().substring(0, 6)
                  + " ), "
                  + " (select sum(t1.org_pay) from aa305 t1 where t1.org_pay_real = 0 and t1.org_id=org2_.id  and t1.year_month< "
                  + securityInfo.getUserInfo().getBizDate().substring(0, 6)
                  + " ) + "
                  + " (select sum(t2.emp_pay) from aa305 t2 where t2.emp_pay_real = 0 and t2.org_id=org2_.id  and t2.year_month< "
                  + securityInfo.getUserInfo().getBizDate().substring(0, 6)
                  + " ), " + " orginfo3_.transactor_name, "
                  + " orginfo3_.tel, " + " orginfo3_.address "
                  + " from AA001 org2_, BA001 orginfo3_ " + " where org2_.id "
                  + securityInfo.getGjjSecuritySQL()
                  + " and org2_.ORGINFO_ID = orginfo3_.ID and ";

              Vector parameters = new Vector();
              String criterion = "";

              if (orgcode != null && !orgcode.equals("")) {
                criterion += " org2_.id = ?  and ";
                parameters.add(orgcode);
              }

              if (criterion.length() != 0) {
                criterion = criterion
                    .substring(0, criterion.lastIndexOf("and"));
              } else {
                hql = hql.substring(0, hql.lastIndexOf("and"));
              }

              hql = hql + criterion;
              session.clear();
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              SearchLackInfoContentDTO clearaccountDTO = null;
              Object obj[] = null;
              Iterator iterate = query.list().iterator();

              while (iterate.hasNext()) {
                obj = (Object[]) iterate.next();
                clearaccountDTO = new SearchLackInfoContentDTO();
                clearaccountDTO.setOrgcode(obj[0].toString());
                clearaccountDTO.setOrgname(obj[1].toString());
                clearaccountDTO.setLackMonths(obj[2].toString());
                clearaccountDTO.setOrgpayLack(obj[3].toString());
                clearaccountDTO.setEmppayLack(obj[4].toString());
                clearaccountDTO.setSumpayLack(obj[5].toString());
                if (obj[6] != null) {
                  clearaccountDTO.setTransactor_name(obj[6].toString());
                }
                if (obj[7] != null) {
                  clearaccountDTO.setTel(obj[7].toString());
                }
                if (obj[8] != null) {
                  clearaccountDTO.setAddress(obj[8].toString());
                }
              }

              return clearaccountDTO;
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return searchLackInfoContentDTO;
  }

  public List querySearchLackInfoYear(final String orgid) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct substr(searchLackInfo.yearMonth,0,4) from SearchLackInfo searchLackInfo "
              + " where searchLackInfo.org.id = ? ";

          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(orgid));

          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List querySearchLackInfoByYear(final String orgid, final String payYear) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select substr(searchLackInfo.yearMonth,5,6),searchLackInfo.orgPayReal,searchLackInfo.empPayReal "
              + " from SearchLackInfo searchLackInfo "
              + " where searchLackInfo.org.id = ? and searchLackInfo.yearMonth like ? order by searchLackInfo.yearMonth";

          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(orgid));
          query.setParameter(1, payYear + "%");
          OrgpaymentstatisticsDTO dto = null;
          List templist = new ArrayList();
          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgpaymentstatisticsDTO();
            dto.setPay_month(new Integer(obj[0].toString()));
            dto.setOrgPay(new BigDecimal(obj[1].toString()));
            dto.setEmpPay(new BigDecimal(obj[2].toString()));
            templist.add(dto);
          }
          return templist;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List querySearchLackInfoTotalByYear(final String orgid,
      final String payYear) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select sum(searchLackInfo.orgPayReal),sum(searchLackInfo.empPayReal) "
              + " from SearchLackInfo searchLackInfo "
              + " where searchLackInfo.org.id = ? and searchLackInfo.yearMonth like ? ";

          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(orgid));
          query.setParameter(1, payYear + "%");
          OrgpaymentstatisticsDTO dto = null;
          List templist = new ArrayList();
          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new OrgpaymentstatisticsDTO();
            dto.setOrgPay(new BigDecimal(obj[0].toString()));
            dto.setEmpPay(new BigDecimal(obj[1].toString()));
            templist.add(dto);
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
   * 单位版_余额结转 通过orgId取AA305最大的年月
   * 
   * @author wangy 2008-02-27
   * @param orgId 单位编号
   * @return
   */
  public String queryMaxYearMonthByOrgId(final String orgId) {
    String maxYearMonth = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select max(t.year_month) from aa305 t where t.org_id=?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, new Integer(orgId));
            if (query.uniqueResult() == null) {
              return null;
            } else {
              return query.uniqueResult().toString();
            }
          }
        });
    return maxYearMonth;
  }

  /**
   * 查询单位欠缴信息
   * 
   * @param orgid
   * @param payYear
   * @return
   */
  public List queryOrgLackMoney(final String orgid, final String payYear) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select sum(org_pay-org_pay_real) from aa305 where  (org_pay-org_pay_real)>0"
              + "and year_month<? and org_id=?"
              + " union all "
              + "select sum(emp_pay-emp_pay_real) from aa305 where  (emp_pay-emp_pay_real)>0"
              + "and year_month<? and org_id=? ";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, payYear);
          query.setParameter(1, new Integer(orgid));
          query.setParameter(2, payYear);
          query.setParameter(3, new Integer(orgid));
          OrgpaymentstatisticsDTO dto = null;
          List templist = new ArrayList();
          Iterator iterate = query.list().iterator();
          Object obj = null;
          while (iterate.hasNext()) {
            obj = (Object) iterate.next();
            dto = new OrgpaymentstatisticsDTO();
            if (obj != null)
              dto.setOrgPay(new BigDecimal(obj.toString()));
            // dto.setEmpPay(new BigDecimal(obj.toString()));
            templist.add(dto);
          }
          return templist;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List queryOrgAddPayExpList_old(final String pkid, final String orgid) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select d.id," + "c.name," + "b.unithandinamnt,"
              + "b.emplyhandinamnt," + "d.pay_status," + "d.salary_base,"
              + "f.org_rate," + "f.emp_rate "
              + "from tb_hndinemply_info b, aa002 d, ba002 c, aa001 f "
              + "where b.hndintran_id = ? " + "and b.emply_st = '0' "
              + "and to_number(b.emplyacc) = d.id "
              + "and d.emp_info_id = c.id " + "and d.org_id = f.id "
              + "and d.org_id = ?";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, pkid);
          query.setParameter(1, new Integer(orgid));
          AddpayInfoDto dto = null;
          List templist = new ArrayList();
          Iterator it = query.list().iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            dto = new AddpayInfoDto();
            if (obj[0] != null) {
              dto.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              dto.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              dto.setOrgShouldpay(obj[2].toString());
            }
            if (obj[3] != null) {
              dto.setEmpShouldpay(obj[3].toString());
            }
            if (obj[4] != null) {
              dto.setEmpPayStatus(obj[4].toString());
            }
            if (obj[5] != null) {
              dto.setSalaryBase(obj[5].toString());
            }
            if (obj[6] != null) {
              dto.setOrgRate(obj[6].toString());
            }
            if (obj[7] != null) {
              dto.setEmpRate(obj[7].toString());
            }
            templist.add(dto);
          }
          return templist;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}
