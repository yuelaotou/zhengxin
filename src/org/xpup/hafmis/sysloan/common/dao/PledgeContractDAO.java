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
import org.xpup.hafmis.sysloan.common.domain.entity.PledgeContract;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.dto.AssureModeDTO;

public class PledgeContractDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public PledgeContract queryById(Serializable id) {
    Validate.notNull(id);
    return (PledgeContract) getHibernateTemplate()
        .get(PledgeContract.class, id);
  }

  /**
   * 根据主键删除 yuqf
   * 
   * @param id
   * @return
   */
  public void deleteById(String id) {
    try {
      Validate.notNull(id);
      // getHibernateTemplate().clear();
      PledgeContract pledgeContract = queryById(new Integer(id));
      getHibernateTemplate().delete(pledgeContract);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 插入记录
   * 
   * @param PledgeContract
   * @return
   */
  public Serializable insert(PledgeContract pledgeContract) {
    Serializable id = null;
    try {
      Validate.notNull(pledgeContract);
      id = getHibernateTemplate().save(pledgeContract);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * yuqf 根据合同编号查询
   * 
   * @param id
   * @return
   */
  public List queryIdByContractIdYU(final String id) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.id from pl121 t where t.contract_id=?";

          Query query = session.createSQLQuery(hql);
          query.setParameter(0, id);

          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * yuqf 根据合同编号查询PL121
   * 
   * @param id
   * @return
   */
  public List queryPledgeListYU(final String id) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.pledge_matter_name," +
              "t.pledge_addr," +
              "t.pledge_value," +
              "t.evaluate_value," +
              "t.status," +
              "t.id," +
              "t.photo_url," +
              "t.name," +
              "t.card_kind," +
              "t.tel," +
              "t.reservea_a," +
              "t.paper_name," +
              "t.pledge_contract_id," +
              "t.paper_num," +
              "t.paper_name," +
              "t.card_num," +
              "t.mobile," +
              "t.area" +
              " from pl121 t where t.contract_id=? order by t.id DESC ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, id);
          List tempList = new ArrayList();
          if (query.list().size() != 0) {
            Iterator iterate = query.list().iterator();
            Object[] obj = null;
            while (iterate.hasNext()) {
              obj = (Object[]) iterate.next();
              EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
              if (obj[0] != null) {
                endorsecontractTbAF.setPledgeMatterName(obj[0].toString());// 抵押物
              }
              if (obj[1] != null) {
                endorsecontractTbAF.setPledgeAddr(obj[1].toString());// 抵押物地址
              }
              if (obj[2] != null) {
                endorsecontractTbAF.setPledgeValue(obj[2].toString());// 抵押值
              }
              if (obj[3] != null) {
                endorsecontractTbAF.setEvaluateValue(obj[3].toString());// 评估值
              }
              if (obj[4] != null) {
                endorsecontractTbAF.setStatus(obj[4].toString());// 合同状态
              }
              if (obj[5] != null) {
                endorsecontractTbAF.setId(obj[5].toString());// 外键
              }
              if(obj[6] != null){
                endorsecontractTbAF.setPhotoUrl(obj[6].toString());//路径
              }
              if(obj[7] != null){
                endorsecontractTbAF.setPledgePerson(obj[7].toString());
              }
              if(obj[8] != null){
                endorsecontractTbAF.setCardKind(obj[8].toString());
              }
              if(obj[9] != null){
                endorsecontractTbAF.setTel(obj[9].toString());
              }
              if(obj[10] != null){
                endorsecontractTbAF.setBuyHouseContractId(obj[10].toString());
              }
              if(obj[11] != null){
                endorsecontractTbAF.setPaperName(obj[11].toString());
              }
              if(obj[12] != null){
                endorsecontractTbAF.setPledgeContractId(obj[12].toString());
              }
              if(obj[13] != null){
                endorsecontractTbAF.setPaperNum(obj[13].toString());
              }
              if(obj[14] != null){
                endorsecontractTbAF.setPaperPersonName(obj[14].toString());
              }
              if(obj[15] != null){
                endorsecontractTbAF.setCarNum(obj[15].toString());
              }
              if(obj[16] != null){
                endorsecontractTbAF.setMobile(obj[16].toString());
              }
              if(obj[17] != null){
                endorsecontractTbAF.setArea(obj[17].toString());
              }
              tempList.add(endorsecontractTbAF);
            }
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
   * 根据合同编号查询最大ID
   * 
   * @param id
   * @return
   */
  public String queryMaxId(final String id) {
    String maxId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select max(t.id) from pl121 t where t.contract_id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, id);

            return query.uniqueResult();
          }
        });

    return maxId;
  }

  /**
   * 担保方式统计查询
   * 
   * @author 王野 2007-10-12 查询抵押户数和金额
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorNum
   * @param assistantOrgId
   * @param securityInfo
   * @return
   */
  public List queryAssureModeListByCriterionsPledge(final String officeCode,
      final String loanBankId, final String developerId, final String floorNum,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractid), nvl(sum(pl121pledgevalue), 0) "
              + " from (select pl121.contract_id contractid, "
              + " sum(pl121.pledge_value) pl121pledgevalue "
              + " from pl121 pl121, pl111 pl111 "
              + " where pl111.contract_id = pl121.contract_id "
              + " and not exists (select pl122.contract_id "
              + " from pl122 pl122 "
              + " where pl122.contract_id = pl121.contract_id) "
              + " and not exists (select pl123.contract_id "
              + " from pl123 pl123 "
              + " where pl123.contract_id = pl121.contract_id) "
              + " group by pl121.contract_id), pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110  where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p110.contract_id "
                + " and p110.office = ? ";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.loan_bank_id = ? ";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p114.contract_id "
                + " and p114.head_id = ? ";
            parameters.add(developerId);
          }

          if (floorNum != null && !floorNum.equals("")) {
            criterion += " , pl114 p114 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p114.contract_id "
                + " and p114.floor_id = ? ";
            parameters.add(floorNum);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p120.contract_id "
                + " and p120.assistant_org_id = ? ";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion + " and p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          AssureModeDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new AssureModeDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setPledgeCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setPledgeValue(obj[1].toString());
            tempList.add(dto);
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
   * 担保方式统计查询
   * 
   * @author 王野 2007-10-12 查询质押户数和金额
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorNum
   * @param assistantOrgId
   * @param securityInfo
   * @return
   */
  public List queryAssureModeListByCriterionsImpawn(final String officeCode,
      final String loanBankId, final String developerId, final String floorNum,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractid), nvl(sum(pl122impawnvalue), 0) "
              + " from (select pl122.contract_id contractid, "
              + " sum(pl122.impawn_value) pl122impawnvalue "
              + " from pl122 pl122, pl111 pl111 "
              + " where pl111.contract_id = pl122.contract_id "
              + " and not exists (select pl121.contract_id "
              + " from pl121 pl121 "
              + " where pl121.contract_id = pl122.contract_id) "
              + " and not exists (select pl123.contract_id "
              + " from pl123 pl123 "
              + " where pl123.contract_id = pl122.contract_id) "
              + " group by pl122.contract_id), pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110  where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p110.contract_id "
                + " and p110.office = ? ";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.loan_bank_id = ? ";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p114.contract_id "
                + " and p114.head_id = ? ";
            parameters.add(developerId);
          }

          if (floorNum != null && !floorNum.equals("")) {
            criterion += " , pl114 p114 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p114.contract_id "
                + " and p114.floor_id = ? ";
            parameters.add(floorNum);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p120.contract_id "
                + " and p120.assistant_org_id = ? ";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion + " and p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          AssureModeDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new AssureModeDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setImpawnCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setImpawnValue(obj[1].toString());
            tempList.add(dto);
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
   * 担保方式统计查询
   * 
   * @author 王野 2007-10-13 查询抵押+保证户数和金额
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorNum
   * @param assistantOrgId
   * @param securityInfo
   * @return
   */
  public List queryAssureModeListByCriterionsPledgeAssurer(
      final String officeCode, final String loanBankId,
      final String developerId, final String floorNum,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractid), nvl(sum(pl121pledgeassurevalue), 0) "
              + " from (select pl121.contract_id contractid, "
              + " sum(pl121.pledge_value) pl121pledgeassurevalue "
              + " from pl121 pl121, pl111 pl111 "
              + " where pl111.contract_id = pl121.contract_id "
              + " and exists "
              + " (select pl123.contract_id "
              + " from pl123 pl123 "
              + " where pl123.contract_id = pl121.contract_id) "
              + " group by pl121.contract_id), pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110  where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p110.contract_id "
                + " and p110.office = ? ";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.loan_bank_id = ? ";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p114.contract_id "
                + " and p114.head_id = ? ";
            parameters.add(developerId);
          }

          if (floorNum != null && !floorNum.equals("")) {
            criterion += " , pl114 p114 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p114.contract_id "
                + " and p114.floor_id = ? ";
            parameters.add(floorNum);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p120.contract_id "
                + " and p120.assistant_org_id = ? ";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion + " and p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          AssureModeDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new AssureModeDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setPledgeAssurerCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setPledgeAssurerValue(obj[1].toString());
            tempList.add(dto);
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
   * 担保方式统计查询
   * 
   * @author 王野 2007-10-13 查询质押+保证户数和金额
   * @param officeCode
   * @param loanBankId
   * @param developerId
   * @param floorNum
   * @param assistantOrgId
   * @param securityInfo
   * @return
   */
  public List queryAssureModeListByCriterionsImpawnAssurer(
      final String officeCode, final String loanBankId,
      final String developerId, final String floorNum,
      final String assistantOrgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(contractid), nvl(sum(pl122impawnassurevalue), 0) "
              + " from (select pl122.contract_id contractid, "
              + " sum(pl122.impawn_value) pl122impawnassurevalue "
              + " from pl122 pl122, pl111 pl111 "
              + " where pl111.contract_id = pl122.contract_id "
              + " and exists "
              + " (select pl123.contract_id "
              + " from pl123 pl123 "
              + " where pl123.contract_id = pl122.contract_id) "
              + " group by pl122.contract_id), pl111 p111 ";
          Vector parameters = new Vector();
          String criterion = "";

          if (officeCode != null && !officeCode.equals("")) {
            criterion += ", pl110 p110  where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p110.contract_id "
                + " and p110.office = ? ";
            parameters.add(officeCode);
          }

          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += " where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.loan_bank_id = ? ";
            parameters.add(loanBankId);
          }

          if (developerId != null && !developerId.equals("")) {
            criterion += " , pl114 p114 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p114.contract_id "
                + " and p114.head_id = ? ";
            parameters.add(developerId);
          }

          if (floorNum != null && !floorNum.equals("")) {
            criterion += " , pl114 p114 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p114.contract_id "
                + " and p114.floor_id = ? ";
            parameters.add(floorNum);
          }

          if (assistantOrgId != null && !assistantOrgId.equals("")) {
            criterion += " , pl120 p120 where p111.contract_st = '11' "
                + " and contractid = p111.contract_id "
                + " and p111.contract_id = p120.contract_id "
                + " and p120.assistant_org_id = ? ";
            parameters.add(assistantOrgId);
          }

          hql = hql + criterion + " and p111.loan_bank_id "
              + securityInfo.getDkSecuritySQL();
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Iterator iterate = query.list().iterator();

          AssureModeDTO dto = null;
          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new AssureModeDTO();
            if (obj[0] != null && !obj[0].equals(""))
              dto.setImpawnAssurerCount(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              dto.setImpawnAssurerValue(obj[1].toString());
            tempList.add(dto);
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
   * 担保方式统计查询
   * 
   * @author 王野 2007-10-12 通过银行ID获得办事处ID(BB105)
   * @param bankId
   * @return
   */
  public String queryOfficeIdByBankId(final String bankId) {
    String office = "";
    try {
      office = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select b05.office from bb105 b05 where b05.coll_bank_id=?";

          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(bankId));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return office;
  }

}
