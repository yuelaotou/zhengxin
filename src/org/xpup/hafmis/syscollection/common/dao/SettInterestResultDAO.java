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
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestResult;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.dto.YearclearstatisticsHeadDTO;

/**
 * 结算结果
 * 
 * @author 李娟 2007-6-19
 */
public class SettInterestResultDAO extends HibernateDaoSupport {

  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public SettInterestResult queryById(Serializable id) {
    Validate.notNull(id);
    return (SettInterestResult) getHibernateTemplate().get(
        SettInterestResult.class, id);
  }

  /**
   * 插入记录
   * 
   * @param settInterestResult
   * @return
   */
  public Serializable insert(SettInterestResult settInterestResult) {
    Serializable id = null;
    try {
      Validate.notNull(settInterestResult);
      id = getHibernateTemplate().save(settInterestResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }

  /**
   * 王菱 根据条件查询年终结息全部列表
   * 
   * @param officecode
   * @param bankcode
   * @param orgid
   * @param orgname
   * @param setyearstart
   * @param setyearend
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findYearclearstatisticsListByCriterions_WL(
      final String officecode, final String bankcode, final String orgcode,
      final String orgname, final String setyearstart, final String setyearend,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page, final SecurityInfo securityInfo,
      final String headId) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select aa001.id,ba001.name, "
              + " count(aa318.id),sum(aa318.bef_pre_balance), sum(aa318.bef_cur_balance), "
              + " sum(aa318.pre_interest),sum(aa318.cur_interest), "
              + " sum(aa318.end_pre_balance),sum(aa318.end_cur_balance), "
              + " sum(aa318.end_pre_balance)+sum(aa318.end_cur_balance),aa318.sett_head_id "
              + " from AA318 aa318, AA316 aa316, AA001 aa001, BA001 ba001 "
              + " where aa001.id " + securityInfo.getGjjSecuritySQL()
              + " and aa001.orginfo_id = ba001.id "
              + " and aa001.id = aa316.org_id "
              + " and aa001.id = aa316.org_id "
              + " and aa318.sett_head_id = aa316.id and ";

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
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " aa001.id = ?  and ";
            parameters.add(orgcode);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " ba001.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (headId != null && !headId.equals("")) {
            criterion += " aa318.sett_head_id = ?   and ";
            parameters.add(headId);
          }
          if (setyearstart != null && !setyearstart.equals("")
              && setyearend != null && !setyearend.equals("")) {
            criterion += " aa318.year between ? and ? and ";
            parameters.add(setyearstart);
            parameters.add(setyearend);
          }

          String ob = orderBy;
          if (ob == null)
            ob = " aa318.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion
              + " group by aa001.id,ba001.name,aa318.sett_head_id "
              + " order by " + ob + " " + od;
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          YearclearstatisticsHeadDTO dto = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new YearclearstatisticsHeadDTO();
            dto.setOrgcode(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setEmpcounts(new Integer(obj[2].toString()));
            dto.setOldpreblance(new BigDecimal(obj[3].toString()));
            dto.setOldcurblance(new BigDecimal(obj[4].toString()));
            dto.setPreinterest(new BigDecimal(obj[5].toString()));
            dto.setCurinterest(new BigDecimal(obj[6].toString()));
            dto.setPreblance(new BigDecimal(obj[7].toString()));
            dto.setCurblance(new BigDecimal(obj[8].toString()));
            dto.setBlance(new BigDecimal(obj[9].toString()));
            dto.setSet_headid(obj[10].toString());

            tableList.add(dto);
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

  public List findYearclearstatisticsListByCriterions_wsh(
      final String officecode, final String bankcode, final String orgcode,
      final String orgname, final String setyearstart, final String setyearend,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page, final SecurityInfo securityInfo,
      final String headId, final String isZero) throws NumberFormatException,
      Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select aa001.id,ba001.name, "
              + " count(aa318.id),sum(aa318.bef_pre_balance), sum(aa318.bef_cur_balance), "
              + " sum(aa318.pre_interest),sum(aa318.cur_interest), "
              + " sum(aa318.end_pre_balance),sum(aa318.end_cur_balance), "
              + " sum(aa318.end_pre_balance)+sum(aa318.end_cur_balance),aa318.sett_head_id "
              + " from AA318 aa318, AA316 aa316, AA001 aa001, BA001 ba001 "
              + " where aa001.id " + securityInfo.getGjjSecuritySQL()
              + " and aa001.orginfo_id = ba001.id "
              + " and aa001.id = aa316.org_id "
              + " and aa001.id = aa316.org_id "
              + " and aa318.sett_head_id = aa316.id and ";

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
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " aa001.id = ?  and ";
            parameters.add(orgcode);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " ba001.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (headId != null && !headId.equals("")) {
            criterion += " aa318.sett_head_id = ?   and ";
            parameters.add(headId);
          }
          if (setyearstart != null && !setyearstart.equals("")
              && setyearend != null && !setyearend.equals("")) {
            criterion += " aa318.year between ? and ? and ";
            parameters.add(setyearstart);
            parameters.add(setyearend);
          }
//          if (isZero != null && !isZero.equals("") && isZero.equals("0")) {
//            criterion += " (select sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318 aaa318 where aaa318.sett_head_id=aa318.sett_head_id  and aaa318.sett_head_id = aa316.id) > 0   and ";
//
//          }
//          if (isZero != null && !isZero.equals("") && isZero.equals("1")) {
//            criterion += " (select sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318 aaa318 where aaa318.sett_head_id=aa318.sett_head_id  and aaa318.sett_head_id = aa316.id) <= 0   and ";
//
//          }
          String ob = orderBy;
          if (ob == null)
            ob = " aa318.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion
              + " group by aa001.id,ba001.name,aa318.sett_head_id "
              + " order by " + ob + " " + od;
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          YearclearstatisticsHeadDTO dto = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new YearclearstatisticsHeadDTO();
            dto.setOrgcode(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setEmpcounts(new Integer(obj[2].toString()));
            dto.setOldpreblance(new BigDecimal(obj[3].toString()));
            dto.setOldcurblance(new BigDecimal(obj[4].toString()));
            dto.setPreinterest(new BigDecimal(obj[5].toString()));
            dto.setCurinterest(new BigDecimal(obj[6].toString()));
            dto.setPreblance(new BigDecimal(obj[7].toString()));
            dto.setCurblance(new BigDecimal(obj[8].toString()));
            dto.setBlance(new BigDecimal(obj[9].toString()));
            dto.setSet_headid(obj[10].toString());
            // setyearstart
            String ursun = "select nvl(sum(t.credit-t.debit),0) from aa101 t where t.org_id = "
                + new Integer(obj[0].toString())
                + " and t.biz_status=5 and t.biz_type='C' and t.sett_date<'"
                + setyearstart + "0701'";
            Query query1 = session.createSQLQuery(ursun);
            dto.setGuazhang(new BigDecimal(query1.uniqueResult().toString()));
            tableList.add(dto);
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

  public List findYearclearstatisticsListByCriterions_wsh(
      final String officecode, final String bankcode, final String orgcode,
      final String orgname, final String setyearstart, final String setyearend,
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
          String hql = "select aa001.id,ba001.name, "
              + " count(aa318.id),sum(aa318.bef_pre_balance), sum(aa318.bef_cur_balance), "
              + " sum(aa318.pre_interest),sum(aa318.cur_interest), "
              + " sum(aa318.end_pre_balance),sum(aa318.end_cur_balance), "
              + " sum(aa318.end_pre_balance)+sum(aa318.end_cur_balance),aa318.sett_head_id,ba001.collection_bank_id "
              + " from AA318 aa318, AA316 aa316, AA001 aa001, BA001 ba001 "
              + " where aa001.id " + securityInfo.getGjjSecuritySQL()
              + " and aa001.orginfo_id = ba001.id "
              + " and aa001.id = aa316.org_id "
              + " and aa001.id = aa316.org_id "
              + " and aa318.sett_head_id = aa316.id and ";

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
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " aa001.id = ?  and ";
            parameters.add(orgcode);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " ba001.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }

          if (setyearstart != null && !setyearstart.equals("")
              && setyearend != null && !setyearend.equals("")) {
            criterion += " aa318.year between ? and ? and ";
            parameters.add(setyearstart);
            parameters.add(setyearend);
          }

          String ob = orderBy;
          if (ob == null)
            ob = " aa318.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion
              + " group by aa001.id,ba001.name,aa318.sett_head_id "
              + " order by " + ob + " " + od;
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          YearclearstatisticsHeadDTO dto = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new YearclearstatisticsHeadDTO();
            dto.setOrgcode(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setEmpcounts(new Integer(obj[2].toString()));
            dto.setOldpreblance(new BigDecimal(obj[3].toString()));
            dto.setOldcurblance(new BigDecimal(obj[4].toString()));
            dto.setPreinterest(new BigDecimal(obj[5].toString()));
            dto.setCurinterest(new BigDecimal(obj[6].toString()));
            dto.setPreblance(new BigDecimal(obj[7].toString()));
            dto.setCurblance(new BigDecimal(obj[8].toString()));
            dto.setBlance(new BigDecimal(obj[9].toString()));
            dto.setSet_headid(obj[10].toString());
            dto.setBank(obj[11].toString());
            tableList.add(dto);
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

  /**
   * 王菱 根据条件查询年终结息全部列表条数
   * 
   * @param officecode
   * @param bankcode
   * @param orgid
   * @param orgname
   * @param setyearstart
   * @param setyearend
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findYearclearstatisticsListCountByCriterions_WL(
      final String officecode, final String bankcode, final String orgcode,
      final String orgname, final String setyearstart, final String setyearend,
      final SecurityInfo securityInfo, final String headId)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select aa001.id,ba001.name, "
              + " count(aa318.id),sum(aa318.bef_pre_balance), sum(aa318.bef_cur_balance), "
              + " sum(aa318.pre_interest),sum(aa318.cur_interest), "
              + " sum(aa318.end_pre_balance),sum(aa318.end_cur_balance), "
              + " sum(aa318.end_pre_balance)+sum(aa318.end_cur_balance),aa318.sett_head_id  "
              + " from AA318 aa318, AA316 aa316, AA001 aa001, BA001 ba001 "
              + " where aa001.id " + securityInfo.getGjjSecuritySQL()
              + " and aa001.orginfo_id = ba001.id "
              + " and aa001.id = aa316.org_id "
              + " and aa001.id = aa316.org_id "
              + " and aa318.sett_head_id = aa316.id and ";

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
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " aa001.id = ?  and ";
            parameters.add(orgcode);
          }
          if (headId != null && !headId.equals("")) {
            criterion += " aa318.sett_head_id = ?  and ";
            parameters.add(headId);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " ba001.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }

          if (setyearstart != null && !setyearstart.equals("")
              && setyearend != null && !setyearend.equals("")) {
            criterion += " aa318.year between ? and ? and ";
            parameters.add(setyearstart);
            parameters.add(setyearend);
          }

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion
              + " group by aa001.id,ba001.name,aa318.sett_head_id ";
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          YearclearstatisticsHeadDTO dto = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new YearclearstatisticsHeadDTO();
            dto.setOrgcode(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setEmpcounts(new Integer(obj[2].toString()));
            dto.setOldpreblance(new BigDecimal(obj[3].toString()));
            dto.setOldcurblance(new BigDecimal(obj[4].toString()));
            dto.setPreinterest(new BigDecimal(obj[5].toString()));
            dto.setCurinterest(new BigDecimal(obj[6].toString()));
            dto.setPreblance(new BigDecimal(obj[7].toString()));
            dto.setCurblance(new BigDecimal(obj[8].toString()));
            dto.setBlance(new BigDecimal(obj[9].toString()));
            dto.setSet_headid(obj[10].toString());

            tableList.add(dto);
          }

          return tableList;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public List findYearclearstatisticsListCountByCriterions_wsh(
      final String officecode, final String bankcode, final String orgcode,
      final String orgname, final String setyearstart, final String setyearend,
      final SecurityInfo securityInfo, final String headId, final String isZero)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select aa001.id,ba001.name, "
              + " count(aa318.id),sum(aa318.bef_pre_balance), sum(aa318.bef_cur_balance), "
              + " sum(aa318.pre_interest),sum(aa318.cur_interest), "
              + " sum(aa318.end_pre_balance),sum(aa318.end_cur_balance), "
              + " sum(aa318.end_pre_balance)+sum(aa318.end_cur_balance),aa318.sett_head_id  "
              + " from AA318 aa318, AA316 aa316, AA001 aa001, BA001 ba001 "
              + " where aa001.id " + securityInfo.getGjjSecuritySQL()
              + " and aa001.orginfo_id = ba001.id "
              + " and aa001.id = aa316.org_id "
              + " and aa001.id = aa316.org_id "
              + " and aa318.sett_head_id = aa316.id and ";

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
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " aa001.id = ?  and ";
            parameters.add(orgcode);
          }
          if (headId != null && !headId.equals("")) {
            criterion += " aa318.sett_head_id = ?  and ";
            parameters.add(headId);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " ba001.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }

          if (setyearstart != null && !setyearstart.equals("")
              && setyearend != null && !setyearend.equals("")) {
            criterion += " aa318.year between ? and ? and ";
            parameters.add(setyearstart);
            parameters.add(setyearend);
          }
//          if (isZero != null && !isZero.equals("") && isZero.equals("0")) {
//            criterion += " (select sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318 aaa318 where aaa318.sett_head_id=aa318.sett_head_id  and aaa318.sett_head_id = aa316.id) > 0   and ";
//
//          }
//          if (isZero != null && !isZero.equals("") && isZero.equals("1")) {
//            criterion += " (select sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318 aaa318 where aaa318.sett_head_id=aa318.sett_head_id  and aaa318.sett_head_id = aa316.id) <= 0   and ";
//
//          }
          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion
              + " group by aa001.id,ba001.name,aa318.sett_head_id ";
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          YearclearstatisticsHeadDTO dto = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new YearclearstatisticsHeadDTO();
            dto.setOrgcode(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setEmpcounts(new Integer(obj[2].toString()));
            dto.setOldpreblance(new BigDecimal(obj[3].toString()));
            dto.setOldcurblance(new BigDecimal(obj[4].toString()));
            dto.setPreinterest(new BigDecimal(obj[5].toString()));
            dto.setCurinterest(new BigDecimal(obj[6].toString()));
            dto.setPreblance(new BigDecimal(obj[7].toString()));
            dto.setCurblance(new BigDecimal(obj[8].toString()));
            dto.setBlance(new BigDecimal(obj[9].toString()));
            dto.setSet_headid(obj[10].toString());
            String ursun = "select nvl(sum(t.credit-t.debit),0) from aa101 t where t.org_id = "
                + new Integer(obj[0].toString())
                + " and t.biz_status=5 and t.biz_type='C' and t.sett_date<'"
                + setyearstart + "0701'";
            Query query1 = session.createSQLQuery(ursun);
            dto.setGuazhang(new BigDecimal(query1.uniqueResult().toString()));
            tableList.add(dto);
          }
          return tableList;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public List findYearclearstatisticsListCountByCriterions_wsh(
      final String officecode, final String bankcode, final String orgcode,
      final String orgname, final String setyearstart, final String setyearend,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select aa001.id,ba001.name, "
              + " count(aa318.id),sum(aa318.bef_pre_balance), sum(aa318.bef_cur_balance), "
              + " sum(aa318.pre_interest),sum(aa318.cur_interest), "
              + " sum(aa318.end_pre_balance),sum(aa318.end_cur_balance), "
              + " sum(aa318.end_pre_balance)+sum(aa318.end_cur_balance),aa318.sett_head_id  "
              + " from AA318 aa318, AA316 aa316, AA001 aa001, BA001 ba001 "
              + " where aa001.id " + securityInfo.getGjjSecuritySQL()
              + " and aa001.orginfo_id = ba001.id "
              + " and aa001.id = aa316.org_id "
              + " and aa001.id = aa316.org_id "
              + " and aa318.sett_head_id = aa316.id and ";

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
          if (orgcode != null && !orgcode.equals("")) {
            criterion += " aa001.id = ?  and ";
            parameters.add(orgcode);
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " ba001.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }

          if (setyearstart != null && !setyearstart.equals("")
              && setyearend != null && !setyearend.equals("")) {
            criterion += " aa318.year between ? and ? and ";
            parameters.add(setyearstart);
            parameters.add(setyearend);
          }

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion
              + " group by aa001.id,ba001.name,aa318.sett_head_id ";
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          YearclearstatisticsHeadDTO dto = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new YearclearstatisticsHeadDTO();
            dto.setOrgcode(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setEmpcounts(new Integer(obj[2].toString()));
            dto.setOldpreblance(new BigDecimal(obj[3].toString()));
            dto.setOldcurblance(new BigDecimal(obj[4].toString()));
            dto.setPreinterest(new BigDecimal(obj[5].toString()));
            dto.setCurinterest(new BigDecimal(obj[6].toString()));
            dto.setPreblance(new BigDecimal(obj[7].toString()));
            dto.setCurblance(new BigDecimal(obj[8].toString()));
            dto.setBlance(new BigDecimal(obj[9].toString()));
            dto.setSet_headid(obj[10].toString());

            tableList.add(dto);
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
   * 王菱 根据条件查询年终结息职工全部列表
   * 
   * @param orgid
   * @param orgname
   * @param empcode
   * @param empname
   * @param setyearstart
   * @param setyearend
   * @param headid
   * @param orgid
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findYearclearEmpListByCriterions_WL(final String orgcode,
      final String orgname, final String empcode, final String empname,
      final String setyearstart, final String setyearend, final String headid,
      final String orgid, final String orderBy, final String order,
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
          String hql = " select aa001.id as orgid, "
              + " ba001.name as orgname, "
              + " aa002.id as empid, "
              + " ba002.name as empname, "
              + " aa318.bef_pre_balance as befprebalance, "
              + " aa318.bef_cur_balance as befcurbalance, "
              + " aa318.pre_interest as preinterest, "
              + " aa318.cur_interest as curinterest, "
              + " aa318.end_pre_balance as endprebalance, "
              + " aa318.end_cur_balance as endcurbalance, "
              + " aa318.end_pre_balance + aa318.end_cur_balance as balance, "
              + " aa318.id as headid "
              + " from AA318 aa318, AA316 aa316, AA001 aa001, BA001 ba001, AA002 aa002, BA002 ba002 "
              + " where aa001.id " + securityInfo.getGjjSecuritySQL()
              + " and aa001.orginfo_id = ba001.id "
              + " and aa002.org_id=aa001.id " + " and aa002.id=aa318.emp_id "
              + " and ba002.id=aa002.emp_info_id "
              + " and aa001.id = aa316.org_id "
              + " and aa001.id = aa316.org_id "
              + " and aa318.sett_head_id = aa316.id and ";

          Vector parameters = new Vector();
          String criterion = "";

          if ((headid != null && !headid.equals(""))
              && (orgid != null && !orgid.equals(""))) {
            criterion += " aa318.sett_head_id = ?  and aa001.id = ?  and ";
            parameters.add(headid);
            parameters.add(orgid);
          } else {
            if (orgcode != null && !orgcode.equals("")) {
              criterion += " aa001.id = ?  and ";
              parameters.add(orgcode);
            }
            if (orgname != null && !orgname.equals("")) {
              criterion += " ba001.name like  ? escape '/'  and ";
              parameters.add("%" + orgname + "%");
            }

            if (empcode != null && !empcode.equals("")) {
              criterion += " aa002.id = ?  and ";
              parameters.add(empcode);
            }
            if (empname != null && !empname.equals("")) {
              criterion += " ba002.name like  ? escape '/'  and ";
              parameters.add("%" + empname + "%");
            }

            if (setyearstart != null && !setyearstart.equals("")
                && setyearend != null && !setyearend.equals("")) {
              criterion += " aa318.year between ? and ? and ";
              parameters.add(setyearstart);
              parameters.add(setyearend);
            }

          }

          String ob = orderBy;
          if (ob == null)
            ob = " aa318.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion + " order by " + ob + " " + od;
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          YearclearstatisticsHeadDTO dto = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new YearclearstatisticsHeadDTO();
            dto.setOrgcode(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setEmpcode(obj[2].toString());
            dto.setEmpname(obj[3].toString());
            dto.setOldpreblance(new BigDecimal(obj[4].toString()));
            dto.setOldcurblance(new BigDecimal(obj[5].toString()));
            dto.setPreinterest(new BigDecimal(obj[6].toString()));
            dto.setCurinterest(new BigDecimal(obj[7].toString()));
            dto.setPreblance(new BigDecimal(obj[8].toString()));
            dto.setCurblance(new BigDecimal(obj[9].toString()));
            dto.setBlance(new BigDecimal(obj[10].toString()));
            dto.setAa318id(obj[11].toString());

            tableList.add(dto);
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

  public List findYearclearEmpListByCriterions_wsh(final String orgcode,
      final String orgname, final String empcode, final String empname,
      final String setyearstart, final String setyearend, final String headid,
      final String orgid, final String orderBy, final String order,
      final int start, final int pageSize, final int page,
      final SecurityInfo securityInfo, final String isZero)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select aa001.id as orgid, "
              + " ba001.name as orgname, "
              + " aa002.id as empid, "
              + " ba002.name as empname, "
              + " aa318.bef_pre_balance as befprebalance, "
              + " aa318.bef_cur_balance as befcurbalance, "
              + " aa318.pre_interest as preinterest, "
              + " aa318.cur_interest as curinterest, "
              + " aa318.end_pre_balance as endprebalance, "
              + " aa318.end_cur_balance as endcurbalance, "
              + " aa318.end_pre_balance + aa318.end_cur_balance as balance, "
              + " aa318.id as headid "
              + " from AA318 aa318, AA316 aa316, AA001 aa001, BA001 ba001, AA002 aa002, BA002 ba002 "
              + " where aa001.id " + securityInfo.getGjjSecuritySQL()
              + " and aa001.orginfo_id = ba001.id "
              + " and aa002.org_id=aa001.id " + " and aa002.id=aa318.emp_id "
              + " and ba002.id=aa002.emp_info_id "
              + " and aa001.id = aa316.org_id "
              + " and aa001.id = aa316.org_id "
              + " and aa318.sett_head_id = aa316.id and ";

          Vector parameters = new Vector();
          String criterion = "";

          if ((headid != null && !headid.equals(""))
              && (orgid != null && !orgid.equals(""))) {
            criterion += " aa318.sett_head_id = ?  and aa001.id = ?  and ";
            parameters.add(headid);
            parameters.add(orgid);
            // if (isZero != null && !isZero.equals("")&& isZero.equals("0")) {
            // criterion += " (select
            // sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318
            // aaa318 where aaa318.sett_head_id=aa318.sett_head_id and
            // aaa318.sett_head_id = aa316.id) >= 0 and ";
            //              
            // }
            // if (isZero != null && !isZero.equals("")&& isZero.equals("1")) {
            // criterion += " (select
            // sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318
            // aaa318 where aaa318.sett_head_id=aa318.sett_head_id and
            // aaa318.sett_head_id = aa316.id) <= 0 and ";
            //            
            // }
          } else {
            if (orgcode != null && !orgcode.equals("")) {
              criterion += " aa001.id = ?  and ";
              parameters.add(orgcode);
            }
            if (orgname != null && !orgname.equals("")) {
              criterion += " ba001.name like  ? escape '/'  and ";
              parameters.add("%" + orgname + "%");
            }

            if (empcode != null && !empcode.equals("")) {
              criterion += " aa002.id = ?  and ";
              parameters.add(empcode);
            }
            if (empname != null && !empname.equals("")) {
              criterion += " ba002.name like  ? escape '/'  and ";
              parameters.add("%" + empname + "%");
            }

            if (setyearstart != null && !setyearstart.equals("")
                && setyearend != null && !setyearend.equals("")) {
              criterion += " aa318.year between ? and ? and ";
              parameters.add(setyearstart);
              parameters.add(setyearend);
            }
            // if (isZero != null && !isZero.equals("")&& isZero.equals("0")) {
            // criterion += " (select
            // sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318
            // aaa318 where aaa318.sett_head_id=aa318.sett_head_id and
            // aaa318.sett_head_id = aa316.id) > 0 and ";
            //              
            // }
            // if (isZero != null && !isZero.equals("")&& isZero.equals("1")) {
            // criterion += " (select
            // sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318
            // aaa318 where aaa318.sett_head_id=aa318.sett_head_id and
            // aaa318.sett_head_id = aa316.id) <= 0 and ";
            //            
            // }
          }

          String ob = orderBy;
          if (ob == null)
            ob = " aa318.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion + " order by " + ob + " " + od;
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          YearclearstatisticsHeadDTO dto = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new YearclearstatisticsHeadDTO();
            dto.setOrgcode(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setEmpcode(obj[2].toString());
            dto.setEmpname(obj[3].toString());
            dto.setOldpreblance(new BigDecimal(obj[4].toString()));
            dto.setOldcurblance(new BigDecimal(obj[5].toString()));
            dto.setPreinterest(new BigDecimal(obj[6].toString()));
            dto.setCurinterest(new BigDecimal(obj[7].toString()));
            dto.setPreblance(new BigDecimal(obj[8].toString()));
            dto.setCurblance(new BigDecimal(obj[9].toString()));
            dto.setBlance(new BigDecimal(obj[10].toString()));
            dto.setAa318id(obj[11].toString());

            tableList.add(dto);
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

  /**
   * 王菱 根据条件查询年终结息职工全部列表条数
   * 
   * @param orgid
   * @param orgname
   * @param empcode
   * @param empname
   * @param setyearstart
   * @param setyearend
   * @param headid
   * @param orgid
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findYearclearEmpListCountByCriterions_WL(final String orgcode,
      final String orgname, final String empcode, final String empname,
      final String setyearstart, final String setyearend, final String headid,
      final String orgid, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select aa001.id as orgid, "
              + " ba001.name as orgname, "
              + " aa002.id as empid, "
              + " ba002.name as empname, "
              + " aa318.bef_pre_balance as befprebalance, "
              + " aa318.bef_cur_balance as befcurbalance, "
              + " aa318.pre_interest as preinterest, "
              + " aa318.cur_interest as curinterest, "
              + " aa318.end_pre_balance as endprebalance, "
              + " aa318.end_cur_balance as endcurbalance, "
              + " aa318.end_pre_balance + aa318.end_cur_balance as balance, "
              + " aa318.id as headid "
              + " from AA318 aa318, AA316 aa316, AA001 aa001, BA001 ba001, AA002 aa002, BA002 ba002 "
              + " where aa001.id " + securityInfo.getGjjSecuritySQL()
              + " and aa001.orginfo_id = ba001.id "
              + " and aa002.org_id=aa001.id " + " and aa002.id=aa318.emp_id "
              + " and ba002.id=aa002.emp_info_id "
              + " and aa001.id = aa316.org_id "
              + " and aa001.id = aa316.org_id "
              + " and aa318.sett_head_id = aa316.id and ";

          Vector parameters = new Vector();
          String criterion = "";

          if ((headid != null && !headid.equals(""))
              && (orgid != null && !orgid.equals(""))) {
            criterion += " aa318.sett_head_id = ?  and aa001.id = ?  and ";
            parameters.add(headid);
            parameters.add(orgid);
          } else {
            if (orgcode != null && !orgcode.equals("")) {
              criterion += " aa001.id = ?  and ";
              parameters.add(orgcode);
            }
            if (orgname != null && !orgname.equals("")) {
              criterion += " ba001.name like  ? escape '/'  and ";
              parameters.add("%" + orgname + "%");
            }

            if (empcode != null && !empcode.equals("")) {
              criterion += " aa002.id = ?  and ";
              parameters.add(empcode);
            }
            if (empname != null && !empname.equals("")) {
              criterion += " ba002.name like  ? escape '/'  and ";
              parameters.add("%" + empname + "%");
            }

            if (setyearstart != null && !setyearstart.equals("")
                && setyearend != null && !setyearend.equals("")) {
              criterion += " aa318.year between ? and ? and ";
              parameters.add(setyearstart);
              parameters.add(setyearend);
            }

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

          YearclearstatisticsHeadDTO dto = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new YearclearstatisticsHeadDTO();
            dto.setOrgcode(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setEmpcode(obj[2].toString());
            dto.setEmpname(obj[3].toString());
            dto.setOldpreblance(new BigDecimal(obj[4].toString()));
            dto.setOldcurblance(new BigDecimal(obj[5].toString()));
            dto.setPreinterest(new BigDecimal(obj[6].toString()));
            dto.setCurinterest(new BigDecimal(obj[7].toString()));
            dto.setPreblance(new BigDecimal(obj[8].toString()));
            dto.setCurblance(new BigDecimal(obj[9].toString()));
            dto.setBlance(new BigDecimal(obj[10].toString()));
            dto.setAa318id(obj[11].toString());

            tableList.add(dto);
          }

          return tableList;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public List findYearclearEmpListCountByCriterions_wsh(final String orgcode,
      final String orgname, final String empcode, final String empname,
      final String setyearstart, final String setyearend, final String headid,
      final String orgid, final SecurityInfo securityInfo, final String isZero)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select aa001.id as orgid, "
              + " ba001.name as orgname, "
              + " aa002.id as empid, "
              + " ba002.name as empname, "
              + " aa318.bef_pre_balance as befprebalance, "
              + " aa318.bef_cur_balance as befcurbalance, "
              + " aa318.pre_interest as preinterest, "
              + " aa318.cur_interest as curinterest, "
              + " aa318.end_pre_balance as endprebalance, "
              + " aa318.end_cur_balance as endcurbalance, "
              + " aa318.end_pre_balance + aa318.end_cur_balance as balance, "
              + " aa318.id as headid "
              + " from AA318 aa318, AA316 aa316, AA001 aa001, BA001 ba001, AA002 aa002, BA002 ba002 "
              + " where aa001.id " + securityInfo.getGjjSecuritySQL()
              + " and aa001.orginfo_id = ba001.id "
              + " and aa002.org_id=aa001.id " + " and aa002.id=aa318.emp_id "
              + " and ba002.id=aa002.emp_info_id "
              + " and aa001.id = aa316.org_id "
              + " and aa001.id = aa316.org_id "
              + " and aa318.sett_head_id = aa316.id and ";

          Vector parameters = new Vector();
          String criterion = "";

          if ((headid != null && !headid.equals(""))
              && (orgid != null && !orgid.equals(""))) {
            criterion += " aa318.sett_head_id = ?  and aa001.id = ?  and ";
            parameters.add(headid);
            parameters.add(orgid);
            // if (isZero != null && !isZero.equals("")&& isZero.equals("0")) {
            // criterion += " (select
            // sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318
            // aaa318 where aaa318.sett_head_id=aa318.sett_head_id and
            // aaa318.sett_head_id = aa316.id) >= 0 and ";
            //              
            // }
            // if (isZero != null && !isZero.equals("")&& isZero.equals("1")) {
            // criterion += " (select
            // sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318
            // aaa318 where aaa318.sett_head_id=aa318.sett_head_id and
            // aaa318.sett_head_id = aa316.id) <= 0 and ";
            //            
            // }
          } else {
            if (orgcode != null && !orgcode.equals("")) {
              criterion += " aa001.id = ?  and ";
              parameters.add(orgcode);
            }
            if (orgname != null && !orgname.equals("")) {
              criterion += " ba001.name like  ? escape '/'  and ";
              parameters.add("%" + orgname + "%");
            }

            if (empcode != null && !empcode.equals("")) {
              criterion += " aa002.id = ?  and ";
              parameters.add(empcode);
            }
            if (empname != null && !empname.equals("")) {
              criterion += " ba002.name like  ? escape '/'  and ";
              parameters.add("%" + empname + "%");
            }

            if (setyearstart != null && !setyearstart.equals("")
                && setyearend != null && !setyearend.equals("")) {
              criterion += " aa318.year between ? and ? and ";
              parameters.add(setyearstart);
              parameters.add(setyearend);
            }
            // if (isZero != null && !isZero.equals("")&& isZero.equals("0")) {
            // criterion += " (select
            // sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318
            // aaa318 where aaa318.sett_head_id=aa318.sett_head_id and
            // aaa318.sett_head_id = aa316.id) > 0 and ";
            //              
            // }
            // if (isZero != null && !isZero.equals("")&& isZero.equals("1")) {
            // criterion += " (select
            // sum(aa318.end_pre_balance+aa318.end_cur_balance) from aa318
            // aaa318 where aaa318.sett_head_id=aa318.sett_head_id and
            // aaa318.sett_head_id = aa316.id) <= 0 and ";
            //            
            // }
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

          YearclearstatisticsHeadDTO dto = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new YearclearstatisticsHeadDTO();
            dto.setOrgcode(obj[0].toString());
            dto.setOrgname(obj[1].toString());
            dto.setEmpcode(obj[2].toString());
            dto.setEmpname(obj[3].toString());
            dto.setOldpreblance(new BigDecimal(obj[4].toString()));
            dto.setOldcurblance(new BigDecimal(obj[5].toString()));
            dto.setPreinterest(new BigDecimal(obj[6].toString()));
            dto.setCurinterest(new BigDecimal(obj[7].toString()));
            dto.setPreblance(new BigDecimal(obj[8].toString()));
            dto.setCurblance(new BigDecimal(obj[9].toString()));
            dto.setBlance(new BigDecimal(obj[10].toString()));
            dto.setAa318id(obj[11].toString());

            tableList.add(dto);
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
   * 王菱 根据条件查询单个职工列表年终结息
   * 
   * @param orgcode
   * @param empcode
   * @param headid
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findYearclearEmpByCriterions_WL(final String orgcode,
      final String empcode, final String headid, final String orderBy,
      final String order, final int start, final int pageSize, final int page,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select b001.name as orgname,"
              + " b002.name as empname,  "
              + " t.pre_integral as pre_integral,  "
              + " t.cur_integral as cur_integral, "
              + " t.pre_rate as pre_rate, "
              + " t.cur_rate as cur_rate, "
              + " t.pre_interest as pre_interest, "
              + " t.cur_interest as cur_interest, "
              + " a.org_id as orgid, t.emp_id as empid "
              + " from aa317 t, aa316 a, aa001 a001, ba001 b001, aa002 a002, ba002 b002 ,aa318 a318 "
              + " where " + " t.sett_head_id = a.id and "
              + " a.org_id = a001.id and " + " a001.orginfo_id = b001.id and "
              + " a001.id = a002.org_id and "
              + " a002.emp_info_id = b002.id and "
              + " a318.sett_head_id=a.id and " + " t.emp_id=a002.id and ";

          Vector parameters = new Vector();
          String criterion = "";

          if (headid != null && !headid.equals("")) {
            criterion += " a318.id = ?  and ";
            parameters.add(headid);
          }
          if (empcode != null && !empcode.equals("")) {
            criterion += " t.emp_id = ?  and ";
            parameters.add(empcode);
          }

          if (orgcode != null && !orgcode.equals("")) {
            criterion += " a.org_id = ? and ";
            parameters.add(orgcode);
          }

          String ob = orderBy;
          if (ob == null)
            ob = " t.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          if (criterion.length() != 0) {
            criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql.substring(0, hql.lastIndexOf("and"));
          }

          hql = hql + criterion + " order by " + ob + " " + od;
          session.clear();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          YearclearstatisticsHeadDTO dto = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new YearclearstatisticsHeadDTO();
            dto.setOrgname(obj[0].toString());
            dto.setEmpname(obj[1].toString());
            dto.setPreIntegral(new BigDecimal(obj[2].toString()));
            dto.setCurIntegral(new BigDecimal(obj[3].toString()));
            dto.setPreRate(new BigDecimal(obj[4].toString()));
            dto.setCurRate(new BigDecimal(obj[5].toString()));
            dto.setPreinterest(new BigDecimal(obj[6].toString()));
            dto.setCurinterest(new BigDecimal(obj[7].toString()));
            dto.setOrgcode(obj[8].toString());
            dto.setEmpcode(obj[9].toString());

            tableList.add(dto);
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

  /**
   * 王菱 根据条件查询单个职工列表年终结息条数
   * 
   * @param orgcode
   * @param empcode
   * @param headid
   * @param securityInfo
   * @throws NumberFormatException
   * @throws Exception
   */
  public List findYearclearEmpCountByCriterions_WL(final String orgcode,
      final String empcode, final String headid, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select b001.name as orgname,"
              + " b002.name as empname,  "
              + " t.pre_integral as pre_integral,  "
              + " t.cur_integral as cur_integral, "
              + " t.pre_rate as pre_rate, "
              + " t.cur_rate as cur_rate, "
              + " t.pre_interest as pre_interest, "
              + " t.cur_interest as cur_interest, "
              + " a.org_id as orgid, t.emp_id as empid "
              + " from aa317 t, aa316 a, aa001 a001, ba001 b001, aa002 a002, ba002 b002 ,aa318 a318 "
              + " where " + " t.sett_head_id = a.id and "
              + " a.org_id = a001.id and " + " a001.orginfo_id = b001.id and "
              + " a001.id = a002.org_id and "
              + " a002.emp_info_id = b002.id and "
              + " a318.sett_head_id=a.id and " + " t.emp_id=a002.id and ";

          Vector parameters = new Vector();
          String criterion = "";

          if (headid != null && !headid.equals("")) {
            criterion += " a318.id = ?  and ";
            parameters.add(headid);
          }
          if (empcode != null && !empcode.equals("")) {
            criterion += " t.emp_id = ?  and ";
            parameters.add(empcode);
          }

          if (orgcode != null && !orgcode.equals("")) {
            criterion += " a.org_id = ? and ";
            parameters.add(orgcode);
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

          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /**
   * 单位版_年终结息 根据AA316ID,AA316.YEAR,查询AA318,关联DA002,
   * AA316类型为A(SettInterestHeadNZJX)的
   * 
   * @author wangy 2008-02-27
   * @param orgId AA316.ORG_ID
   * @param year AA316.YEAR
   * @param 由ClearaccountBS调用
   */
  public List querySettInterestResultListBySettHeadId(final String orgId,
      final String year) throws BusinessException {
    List list = null;
    try {
      list = (List) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select settInterestResult from SettInterestResult settInterestResult, SettInterestHeadNZJX settInterestHead, OrgEdition orgEdition where settInterestHead.org.id=orgEdition.orgId and orgEdition.isOrg=1 and settInterestResult.settHeadId=settInterestHead.id and settInterestHead.org.id=? and settInterestHead.year=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(orgId));
          query.setParameter(1, year);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 单位版_余额结转 根据单位ID,AA316.YEAR,查询 结转余额时，插入AA101所需的Interest
   * 
   * @author wangy 2008-02-27
   * @param orgId AA316.ORG_ID
   * @param accYear 结转年度
   * @param empId 职工编号
   * @param empName 职工姓名
   * @param 由OrgVerAccountBalanceBS调用
   */
  public BigDecimal queryOVAccBalanceInterestByOrgIdAndYear(final String orgId,
      final String accYear) {
    BigDecimal sumInterest = new BigDecimal(0);
    try {
      sumInterest = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select nvl(sum(a318.pre_interest+a318.cur_interest),0) from aa318 a318,aa316 a316"
                  + " where a316.id=a318.sett_head_id and a316.type = 'A' ";

              Vector parameters = new Vector();
              String criterion = "";

              if (orgId != null && !"".equals(orgId)) {
                criterion += " a316.org_id = ? and ";
                parameters.add(new Integer(orgId));
              }

              if (accYear != null && !"".equals(accYear)) {
                criterion += " a316.year = ? and ";
                parameters.add(accYear);
              }

              if (criterion.length() != 0)
                criterion = " and "
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
    return sumInterest;
  }

}
