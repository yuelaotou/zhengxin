package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;
import org.xpup.hafmis.syscommon.domain.entity.PayBank;

public class OrgDAODW extends HibernateDaoSupport{
  /*
   * 删除AA001单位信息从以前的方法中直接copy过来
   */
  public void deleteOrgByIdSL(Org org) {
    try {
      getHibernateTemplate().delete(org);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public Org queryById(Integer id) {
    Validate.notNull(id);
    return (Org) getHibernateTemplate().get(Org.class, id);
  }
  /*
   * insert 单位信息
   */
  public void insert(final OrgInfo orgInfo) throws BusinessException {      
    Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
    PreparedStatement pre=null;
        String hql="insert into BA001 (NAME, OFFICECODE"
          +" ,CODE, TAX_REG_NUM, ARTIFICIAL_PERSON, CHARACTER, INDUSTRY, DEPT_IN_CHARGE, ADDRESS, POSTALCODE, TEL, REGION, PAYBANK_NAME, PAYBANK_ACCOUNT_NUM, "
          +" PAYDATE, COLLECTION_BANK_ID, TRANSACTOR_NAME, TRANSACTOR_TEL,"
          +" TRANSACTOR_MOBILETEL, TRANSACTOR_EMAIL, INSPECTOR, OPENSTATUS, OPEN_DATE, RESERVEA_A, RESERVEA_B, RESERVEA_C, ID) "
          +" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) ";         
        try {
        pre=conn.prepareStatement(hql.toString());
        pre.setString(1, orgInfo.getName()); 
        pre.setString(2, orgInfo.getOfficecode());    
        pre.setString(3, orgInfo.getCode());    
        pre.setString(4, orgInfo.getTaxRegNum());    
        pre.setString(5, orgInfo.getArtificialPerson());    
        pre.setString(6, orgInfo.getCharacter());    
        pre.setString(7, orgInfo.getIndustry());    
        pre.setString(8, orgInfo.getDeptInCharge());    
        pre.setString(9, orgInfo.getAddress());    
        pre.setString(10, orgInfo.getPostalcode());    
        pre.setString(11, orgInfo.getTel());    
        pre.setString(12, orgInfo.getRegion());    
        pre.setString(13, orgInfo.getPayBank().getName());    
        pre.setString(14, orgInfo.getPayBank().getAccountNum());    
        pre.setString(15, orgInfo.getPaydate());    
        pre.setString(16, orgInfo.getCollectionBankId());    
        pre.setString(17, orgInfo.getTransactor().getName());    
        pre.setString(18, orgInfo.getTransactor().getTelephone());    
        pre.setString(19, orgInfo.getTransactor().getMobileTelephone());    
        pre.setString(20, orgInfo.getTransactor().getEmail());
        pre.setString(21, orgInfo.getInspector());    
        pre.setString(22, orgInfo.getOpenstatus());    
        pre.setString(23, orgInfo.getOpenDate());    
        pre.setString(24, orgInfo.getReserveaA());    
        pre.setString(25, orgInfo.getReserveaB());    
        pre.setString(26, orgInfo.getReserveaC());    
        pre.setInt(27, new Integer(orgInfo.getId().toString()).intValue());   
        pre.executeUpdate(); 
        } catch (SQLException e) {
          throw new BusinessException("数据操作错误");
        }
  }
  /*
   * insert 单位信息
   */
  public void insert(final Org org) throws BusinessException {
        Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
        PreparedStatement pre=null;    
        String hql="insert into AA001 (PAY_MODE, ORG_RATE, EMP_RATE, PAY_PRECISION, FIRST_PAY_MONTH, ORG_PAY_MONTH, EMP_PAY_MONTH, RESERVEA_A, RESERVEA_B, RESERVEA_C, ORG_ID_OLD, ORGINFO_ID, ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";                                         
       try{
        pre=conn.prepareStatement(hql.toString());
        pre.setBigDecimal(1, org.getPayMode()); 
        pre.setBigDecimal(2, org.getOrgRate()); 
        pre.setBigDecimal(3, org.getEmpRate()); 
        pre.setString(4, org.getPayPrecision().toString()); 
        pre.setString(5, org.getFirstPayMonth()); 
        pre.setString(6, org.getOrgPayMonth()); 
        pre.setString(7, org.getEmpPayMonth()); 
        pre.setString(8, org.getReserveaA()); 
        pre.setString(9, org.getReserveaB()); 
        pre.setString(10, org.getReserveaC()); 
        pre.setString(11,org.getOldOrgID()); 
        pre.setString(12,org.getOrgInfo().getId().toString()); 
        pre.setString(13,org.getId().toString()); 
        pre.executeUpdate();
      } catch (SQLException e) {
        throw new BusinessException("数据操作错误");
      }
  }
  /*
   * 单位开户修改
   */
  public Serializable update(Org org) {
    Validate.notNull(org);
    Org orgg = queryById(new Integer(org.getId().toString()));

    orgg.getOrgInfo().setName(org.getOrgInfo().getName());
    orgg.getOrgInfo().setOfficecode(org.getOrgInfo().getOfficecode());
    orgg.getOrgInfo().setCode(org.getOrgInfo().getCode());
    orgg.getOrgInfo().setArtificialPerson(org.getOrgInfo().getArtificialPerson());
    orgg.getOrgInfo().setCharacter(org.getOrgInfo().getCharacter());
    orgg.getOrgInfo().setIndustry(org.getOrgInfo().getIndustry());
    orgg.getOrgInfo().setDeptInCharge(org.getOrgInfo().getDeptInCharge());
    orgg.getOrgInfo().setTaxRegNum(org.getOrgInfo().getTaxRegNum()); // 税务登记号
    orgg.getOrgInfo().setInspector(org.getOrgInfo().getInspector()); // 稽查员
    orgg.getOrgInfo().setAddress(org.getOrgInfo().getAddress());
    orgg.getOrgInfo().setPostalcode(org.getOrgInfo().getPostalcode());
    if(org.getOrgInfo().getPayBank()==null){
      org.getOrgInfo().setPayBank(new PayBank());
    }
    if(orgg.getOrgInfo().getPayBank()==null){
      orgg.getOrgInfo().setPayBank(new PayBank());
    }
    orgg.getOrgInfo().getPayBank();
    orgg.getOrgInfo().getPayBank().setName(org.getOrgInfo().getPayBank().getName()); // 单位发薪银行
    orgg.getOrgInfo().getPayBank().setAccountNum(org.getOrgInfo().getPayBank().getAccountNum()); // 发薪行帐号
    orgg.getOrgInfo().setTel(org.getOrgInfo().getTel());
    orgg.getOrgInfo().setRegion(org.getOrgInfo().getRegion());
    orgg.getOrgInfo().setPayBank(new PayBank());
    orgg.getOrgInfo().getPayBank().setName(org.getOrgInfo().getPayBank().getName());
    orgg.getOrgInfo().getPayBank().setAccountNum(org.getOrgInfo().getPayBank().getAccountNum());
    orgg.getOrgInfo().setPaydate(org.getOrgInfo().getPaydate());// 发薪日
    orgg.getOrgInfo().setCollectionBankId(org.getOrgInfo().getCollectionBankId());// 归集银行
// orgg.getOrgInfo().setTransactor(new Transactor());
    orgg.getOrgInfo().getTransactor().setName(org.getOrgInfo().getTransactor().getName());
    orgg.getOrgInfo().getTransactor().setEmail(org.getOrgInfo().getTransactor().getEmail());
    orgg.getOrgInfo().getTransactor().setTelephone(org.getOrgInfo().getTransactor().getTelephone());
    orgg.getOrgInfo().getTransactor().setMobileTelephone(org.getOrgInfo().getTransactor().getMobileTelephone());
    orgg.setFirstPayMonth(org.getFirstPayMonth());
    orgg.setPayMode(org.getPayMode());
    orgg.setEmpRate(org.getEmpRate());
    orgg.setOrgRate(org.getOrgRate());
    orgg.setPayPrecision(org.getPayPrecision());
    if (orgg.getOrgInfo().getOpenstatus().equals("1")) {
      orgg.setFirstPayMonth(org.getFirstPayMonth()); 
    }

    return orgg.getId();
  }
  /**
   * 王菱 根据单位ID 查询单位信息
   */
  public Org getOrg_WL(final String orgID) {
    Org org = null;
    try {
      org = (Org) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from Org org where org.id=?  ";
          Query query = session.createQuery(hql);
          query.setInteger(0, Integer.parseInt(orgID));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return org;
  }
  /**
   * 插入记录
   * 
   * @param org
   * @return
   */
  public Serializable insertOrg(Org org) {
    Serializable id = null;
    try {
      Validate.notNull(org);
      id = getHibernateTemplate().save(org);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  public Serializable insert(ChgOrgRate chgOrgRate) {
    Serializable id = null;
    try {
      Validate.notNull(chgOrgRate);
      id = getHibernateTemplate().save(chgOrgRate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  
//吴洪涛修改 2008-3-18 查询//查询AA301，判断应汇缴年月下该单位在中心是否汇缴
  public List queryPaymentHeadlist_wuht(final String orgId,
      final String chgMonth) {

    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "  select aa301.* from aa302 aa302,aa301 aa301   ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {
            criterion += "aa301.org_id = ? and ";
            parameters.add(new Integer(orgId));
          }
          if (chgMonth != null && !chgMonth.equals("")) {
            criterion += "aa302.pay_month= ? and ";
            parameters.add(chgMonth);
          }
 
          if (criterion.length() != 0) {
            criterion = " where  aa302.pay_head_id=aa301.id  and  aa301.pay_type='A' and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          } 
          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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
  
//吴洪涛修改 2008-3-18 查询//查询AA201，判断被该汇缴应用的变更是否有汇缴比例调整
  public List queryChgOrgRatelist_wuht(final String orgId,
      final String chgMonth) {

    List list = new ArrayList();
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "  select aa201.org_rate,aa201.emp_rate from aa201 aa201 ,aa301 aa301,aa302 aa302  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {
            criterion += "aa301.org_id = ? and ";
            parameters.add(new Integer(orgId));
          }
          if (chgMonth != null && !chgMonth.equals("")) {
            criterion += "aa302.pay_month= ? and ";
            parameters.add(chgMonth);
          }
 
          if (criterion.length() != 0) {
            criterion = " where aa201.org_id=aa301.org_id   and aa201.pay_head_id=aa301.id and  aa302.pay_head_id=aa301.id  and  aa301.pay_type='A' and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          } 
          hql = hql + criterion +" order by aa201.id";

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          List queryList = query.list();
          List printList = new ArrayList();
          Iterator iterate = queryList.iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            ChgOrgRate chgOrgRate = new ChgOrgRate();
            obj = (Object[]) iterate.next();

            if (obj[0] != null) {
              chgOrgRate.setOrgRate(new BigDecimal(obj[0].toString()));
            }
            if (obj[1] != null) {
              chgOrgRate.setEmpRate(new BigDecimal(obj[1].toString()));
            }
     
       
            printList.add(chgOrgRate);
          }
          return printList;
        }
      });
    } catch (Exception e) {

      e.printStackTrace();
    }
    return list;
  }
//吴洪涛修改 2008-3-18 查询//查询AA201，判断该是否存在未被汇缴应用的汇缴比例调整记录
  public List queryChgOrgRateList_wuht(final String orgId)
  throws Exception {
List empList = null;
try {
  empList = getHibernateTemplate().executeFind(new HibernateCallback() {

    public Object doInHibernate(Session session) throws HibernateException,
        SQLException {

      String hql = " from ChgOrgRate chgOrgRate  ";
      Vector parameters = new Vector();
      String criterion = "";

      if (orgId != null && !orgId.equals("")) {
        criterion += "chgOrgRate.org.id = ?  and ";
        parameters.add(new Integer(orgId.toString()));
      }
 
        
      if (criterion.length() != 0)
        criterion = "where "
            + criterion.substring(0, criterion.lastIndexOf("and"));

      hql = hql + criterion+" order by chgOrgRate.id";
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
return empList;
}
  
}
  
