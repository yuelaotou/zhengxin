package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import java.util.Iterator;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.biz.buildingpop.dto.BuildingPopListDTO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;

import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.BankListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.BuyHouserDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.DeveloperDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.FloorListDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.DevelopTbFindDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.DevelopTbListDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorDevelopInfoDTO;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 开发商信息表PL005
 * 
 * @author 李娟 2007-9-13
 */
public class DeveloperDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public Developer queryById(Serializable id) {
    Validate.notNull(id);
    return (Developer) getHibernateTemplate().get(Developer.class, id);
  }
  public Integer queryTestCount() {
    Integer count = new Integer(0);
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
  
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(t.param_explain) from PL008 t";
          Query query = session.createSQLQuery(hql);
          return Integer.valueOf(query.uniqueResult().toString());
        }
      });
      return count;
    }
  /**
   * 插入记录
   * 
   * @param developer
   * @return
   */
  public Serializable insert(Developer developer) {
    Serializable id = null;
    try {
      Validate.notNull(developer);
      id = getHibernateTemplate().save(developer);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  public List queryDeveloperTbList_info(final DevelopTbFindDTO dto,
      final SecurityInfo securityInfo, final String orderBy,
      final String order, final int start, final int pageSize, final int page)
      throws Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // wshuang修改语句
          String hql = "select p5.developer_code," 
              + " p5.developer_name,"
              + " p5.office,p5.contact_prsn_a," 
              + " p5.contact_tel_a,"
              + " p5.developer_st," 
              + " p5.id,p5.artfclprsn,"
              + " p5.developer_paybank_a," 
              + " p5.developer_paybank_b,"
              + " nvl(count(distinct p6.floor_name),0)," 
              + " nvl(count(p6.floor_num),0),"
              + " p5.photo_url"
              + " from pl005 p5, pl006 p6" 
              + " where p5.developer_st <> 3"
              + " and p5.id = p6.head_id(+)"
              + " and (p6.floor_st <> 3 or p6.floor_st is null)";
          if(securityInfo != null) {
            hql += " and p5.office " + securityInfo.getOfficeSecuritySQL();
          }
          Vector parameters = new Vector();
          String criterion = "";
          if (dto.getIsSpecial() != null && !"".equals(dto.getIsSpecial())) {
            criterion += " p5.reservea_b = ? and ";
            parameters.add(dto.getIsSpecial());
          }
            
          String buyhouseorgid = dto.getBuyhouseorgid();
          if (buyhouseorgid != null && !buyhouseorgid.equals("")) {
            criterion += " p5.id = ? and ";
            parameters.add(buyhouseorgid);
          }
          String developerId = dto.getDeveloperId().trim();
          if (developerId != null && !developerId.equals("")) {
            criterion += " p5.developer_code like '%" + developerId + "%' and ";
          }
          String developerName = dto.getDeveloperName().trim();
          if (developerName != null && !developerName.equals("")) {
            criterion += " p5.developer_name like '%" + developerName
                + "%' and";
          }
          String office = dto.getOffice().trim();
          if (office != null && !office.equals("") && !office.equals("all")) {
            criterion += " p5.office = ? and";
            parameters.add(office);
          }
          String developerSt = dto.getDeveloperSt().trim();
          if (developerSt != null && !developerSt.equals("")) {
            criterion += " p5.developer_st = ? and";
            parameters.add(developerSt);
          }
          String contactPrsnA = dto.getContactPrsnA().trim();
          if (contactPrsnA != null && !contactPrsnA.equals("")) {
            criterion += " p5.contact_prsn_a = ? and";
            parameters.add(contactPrsnA);
          }
          String code = dto.getCode().trim();
          if (code != null && !code.equals("")) {
            criterion += " p5.code like '%" + code + "%' and ";
          }
          String developerPaybank = dto.getPaybank().trim();
          if (dto.getDeveloperPaybankA() != null
              && !"".equals(dto.getDeveloperPaybankA())) {
            developerPaybank = dto.getDeveloperPaybankA();
          }
          if (developerPaybank != null && !developerPaybank.equals("")) {
            criterion += " ( p5.developer_paybank_a like '%" + developerPaybank
                + "%' or p5.developer_paybank_b like '%" + developerPaybank
                + "%' ) and ";
          }
          String developerPaybankAcc = dto.getPaybankacc().trim();
          if (developerPaybankAcc != null && !developerPaybankAcc.equals("")) {
            criterion += " ( p5.developer_paybank_a_acc like '%"
                + developerPaybankAcc
                + "%' or p5.developer_paybank_b_acc like '%"
                + developerPaybankAcc + "%' ) and ";
          }
          String artfclprsn = dto.getArtfclprsn().trim();
          if (artfclprsn != null && !artfclprsn.equals("")) {
            criterion += " p5.artfclprsn like '%" + artfclprsn + "%' and ";
          }

          // 协议签订日期查询
          String startAgreementStartDate = dto.getStartAgreementStartDate()
              .trim();
          String endAgreementStartDate = dto.getEndAgreementStartDate().trim();
          if (startAgreementStartDate != null
              && !startAgreementStartDate.equals("")
              && endAgreementStartDate != null
              && !endAgreementStartDate.equals("")) {
            criterion += " p5.agreement_start_date between ? and ? and ";
            parameters.add(startAgreementStartDate);
            parameters.add(endAgreementStartDate);
          } else if (startAgreementStartDate != null
              && !startAgreementStartDate.equals("")
              && (endAgreementStartDate == null || endAgreementStartDate
                  .equals(""))) {
            criterion += " p5.agreement_start_date >=? and ";
            parameters.add(startAgreementStartDate);
          } else if (endAgreementStartDate != null
              && !endAgreementStartDate.equals("")
              && (startAgreementStartDate == null || startAgreementStartDate
                  .equals(""))) {
            criterion += " p5.agreement_start_date <=? and ";
            parameters.add(endAgreementStartDate);
          }

          // 协议到期日期查询
          String startAgreementEndDate = dto.getStartAgreementEndDate().trim();
          String endAgreementEndDate = dto.getEndAgreementEndDate().trim();
          if (startAgreementEndDate != null
              && !startAgreementEndDate.equals("")
              && endAgreementEndDate != null && !endAgreementEndDate.equals("")) {
            criterion += " p5.agreement_end_date between ? and ? and ";
            parameters.add(startAgreementEndDate);
            parameters.add(endAgreementEndDate);
          } else if (startAgreementEndDate != null
              && !startAgreementEndDate.equals("")
              && (endAgreementEndDate == null || endAgreementEndDate.equals(""))) {
            criterion += " p5.agreement_end_date >=? and ";
            parameters.add(startAgreementEndDate);
          } else if (endAgreementEndDate != null
              && !endAgreementEndDate.equals("")
              && (startAgreementEndDate == null || startAgreementEndDate
                  .equals(""))) {
            criterion += " p5.agreement_end_date <=? and ";
            parameters.add(endAgreementEndDate);
          }
          String floorNum = dto.getFloorNum();
          if (floorNum != null && !floorNum.equals("") && !floorNum.equals("all")) {
            criterion += " p6.floor_id = ? and ";
            parameters.add(floorNum);
          }
          String floorName = dto.getFloorName();
          if (floorName != null && !floorName.equals("") && !floorName.equals("all")) {
            criterion += " p6.floor_name like '%" + floorName.trim() + "%' and ";
          }
          if (criterion.length() != 0) {
            criterion = " and " + criterion.substring(0,criterion.lastIndexOf("and"));
          }
          String ob = orderBy;
          if (ob == null)
            ob = " p5.developer_code ";

          String od = order;
          if (od == null)
            od = "ASC";
          hql = hql + criterion + " group by p5.developer_code,"
            + " p5.developer_name,"
            + " p5.office,p5.contact_prsn_a," 
            + " p5.contact_tel_a,"
            + " p5.developer_st," 
            + " p5.id,"
            + " p5.artfclprsn,"
            + " p5.developer_paybank_a," 
            + " p5.developer_paybank_b,"
            + " p5.photo_url"
            + " order by " + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          if (pageSize != 0) {
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
          }
          Object obj[] = null;
          DevelopTbListDTO developTbListDTO = null;
          // 暂时存放列表的DTO
          List temp_list = new ArrayList();
          List queryList = query.list();
          if (pageSize != 0) {
            if (queryList == null || queryList.size() == 0) {
              query.setFirstResult(pageSize * (page - 2));
              queryList = query.list();
            }
          }

          Iterator iterate = queryList.iterator();
          // 将查询的结果封装到DTO中
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            developTbListDTO = new DevelopTbListDTO();

            developTbListDTO.setDeveloperId(obj[0].toString());
            developTbListDTO.setDeveloperName(obj[1].toString());
            developTbListDTO.setOffice(obj[2].toString());
            if (obj[3] != null) {
              developTbListDTO.setContactPrsnA(obj[3].toString());
            }
            if (obj[4] != null) {
              developTbListDTO.setContactTelA(obj[4].toString());
            }
            developTbListDTO.setDeveloperSt(obj[5].toString());
            developTbListDTO.setId(obj[6].toString());
            if (obj[7] != null) {
              developTbListDTO.setArtfclprsn(obj[7].toString());
            }
            if (obj[8] != null) {
              developTbListDTO.setDeveloperPaybankA(obj[8].toString());
            }
            if (obj[9] != null) {
              developTbListDTO.setDeveloperPaybankB(obj[9].toString());
            }
            if (obj[10] != null) {
              developTbListDTO.setSumFloor(Integer.parseInt(obj[10].toString()));
            }
            if (obj[11] != null) {
              developTbListDTO.setSumFloorNum(Integer.parseInt(obj[11].toString()));
            }
            if (obj[12] != null) {
              developTbListDTO.setPhotoUrl(obj[12].toString());
            }
            temp_list.add(developTbListDTO);
          }
          return temp_list;
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public int queryDeveloperTbCount_info(final DevelopTbFindDTO dto,
      final SecurityInfo securityInfo) throws Exception {
    Integer count = new Integer(0);
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select count(distinct p5.id) from pl005 p5,pl006 p6"
              + " where p5.developer_st <> 3" 
              + " and p5.id = p6.head_id(+)"
              + " and (p6.floor_st <> 3 or p6.floor_st is null)";
          Vector parameters = new Vector();
          if(securityInfo != null) {
            hql += " and p5.office " + securityInfo.getOfficeSecuritySQL();
          }
          String criterion = "";
          if (dto.getIsSpecial() != null && !"".equals(dto.getIsSpecial())) {
            criterion += " p5.reservea_b = ? and ";
            parameters.add(dto.getIsSpecial());
          }
          String buyhouseorgid = dto.getBuyhouseorgid();
          if (buyhouseorgid != null && !buyhouseorgid.equals("")) {
            criterion += " p5.id = ? and ";
            parameters.add(buyhouseorgid);
          }
          String developerId = dto.getDeveloperId().trim();
          if (developerId != null && !developerId.equals("")) {
            criterion += " p5.developer_code like '%" + developerId + "%' and ";
          }
          String developerName = dto.getDeveloperName().trim();
          if (developerName != null && !developerName.equals("")) {
            criterion += " p5.developer_name like '%" + developerName
                + "%' and";
          }
          String office = dto.getOffice().trim();
          if (office != null && !office.equals("") && !office.equals("all")) {
            criterion += " p5.office = ? and";
            parameters.add(office);
          }
          String developerSt = dto.getDeveloperSt().trim();
          if (developerSt != null && !developerSt.equals("")) {
            criterion += " p5.developer_st = ? and";
            parameters.add(developerSt);
          }
          String contactPrsnA = dto.getContactPrsnA().trim();
          if (contactPrsnA != null && !contactPrsnA.equals("")) {
            criterion += " p5.contact_prsn_a = ? and";
            parameters.add(contactPrsnA);
          }
          String code = dto.getCode().trim();
          if (code != null && !code.equals("")) {
            criterion += " p5.code like '%" + code + "%' and ";
          }
          
          String developerPaybank = dto.getPaybank().trim();
          if (dto.getDeveloperPaybankA() != null
              && !"".equals(dto.getDeveloperPaybankA())) {
            developerPaybank = dto.getDeveloperPaybankA();
          }
          if (developerPaybank != null && !developerPaybank.equals("")) {
            criterion += " ( p5.developer_paybank_a like '%" + developerPaybank
                + "%' or p5.developer_paybank_b like '%" + developerPaybank
                + "%' ) and ";
          }
          String developerPaybankAcc = dto.getPaybankacc().trim();
          if (developerPaybankAcc != null && !developerPaybankAcc.equals("")) {
            criterion += " ( p5.developer_paybank_a_acc like '%"
                + developerPaybankAcc
                + "%' or p5.developer_paybank_b_acc like '%"
                + developerPaybankAcc + "%' ) and ";
          }
          String artfclprsn = dto.getArtfclprsn().trim();
          if (artfclprsn != null && !artfclprsn.equals("")) {
            criterion += " p5.artfclprsn like '%" + artfclprsn + "%' and ";
          }
          // 协议签订日期查询
          String startAgreementStartDate = dto.getStartAgreementStartDate()
              .trim();
          String endAgreementStartDate = dto.getEndAgreementStartDate().trim();
          if (startAgreementStartDate != null
              && !startAgreementStartDate.equals("")
              && endAgreementStartDate != null
              && !endAgreementStartDate.equals("")) {
            criterion += " p5.agreement_start_date between ? and ? and ";
            parameters.add(startAgreementStartDate);
            parameters.add(endAgreementStartDate);
          } else if (startAgreementStartDate != null
              && !startAgreementStartDate.equals("")
              && (endAgreementStartDate == null || endAgreementStartDate
                  .equals(""))) {
            criterion += " p5.agreement_start_date >=? and ";
            parameters.add(startAgreementStartDate);
          } else if (endAgreementStartDate != null
              && !endAgreementStartDate.equals("")
              && (startAgreementStartDate == null || startAgreementStartDate
                  .equals(""))) {
            criterion += " p5.agreement_start_date <=? and ";
            parameters.add(endAgreementStartDate);
          }

          // 协议到期日期查询
          String startAgreementEndDate = dto.getStartAgreementEndDate().trim();
          String endAgreementEndDate = dto.getEndAgreementEndDate().trim();
          if (startAgreementEndDate != null
              && !startAgreementEndDate.equals("")
              && endAgreementEndDate != null && !endAgreementEndDate.equals("")) {
            criterion += " p5.agreement_end_date between ? and ? and ";
            parameters.add(startAgreementEndDate);
            parameters.add(endAgreementEndDate);
          } else if (startAgreementEndDate != null
              && !startAgreementEndDate.equals("")
              && (endAgreementEndDate == null || endAgreementEndDate.equals(""))) {
            criterion += " p5.agreement_end_date >=? and ";
            parameters.add(startAgreementEndDate);
          } else if (endAgreementEndDate != null
              && !endAgreementEndDate.equals("")
              && (startAgreementEndDate == null || startAgreementEndDate
                  .equals(""))) {
            criterion += " p5.agreement_end_date <=? and ";
            parameters.add(endAgreementEndDate);
          }
          String floorNum = dto.getFloorNum();
          if (floorNum != null && !floorNum.equals("") && !floorNum.equals("all")) {
            criterion += " p6.floor_id = ? and ";
            parameters.add(floorNum);
          }
          String floorName = dto.getFloorName();
          if (floorName != null && !floorName.equals("")) {
            criterion += " p6.floor_name  like '%" + floorName.trim() + "%' and ";
          }
          if (criterion.length() != 0) {
            criterion = " and " + criterion.substring(0,criterion.lastIndexOf("and"));
          }

          hql = hql + criterion;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return Integer.valueOf(query.uniqueResult().toString());
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }

  /**
   * 根据PL005主键查询开发商状态的方法
   * 
   * @param id 主键
   * @return
   * @throws Exception
   * @author 付云峰
   */
  public String queryDeveloperST(final Integer id) throws Exception {
    String developerST = "";
    try {
      developerST = (String) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select p5.developer_st from pl005 p5 where p5.id=?";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, id);
              return query.uniqueResult();
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return developerST;
  }

  /**
   * 修改Developer的方法
   * 
   * @param id 主键
   * @throws Exception
   * @author 付云峰
   */
  public void updateDeveloper(Developer developer) {
    Validate.notNull(developer);
    getHibernateTemplate().update(developer);
  }

  public void update_floor(final String develpoer_id, final String floorSt) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = "update DevelopProject t set t.floorSt = ? where t.headId = ? and t.floorSt = ? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, floorSt);
          query.setParameter(1, develpoer_id);
          if(floorSt.equals("0"))
            query.setParameter(2, "1");
          else if(floorSt.equals("1"))
            query.setParameter(2, "0");
          query.executeUpdate();

          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * 用于查询楼盘的开发商信息
   * 
   * @param id PL005的主键
   * @return 封装了开发商信息的DTO
   * @author 付云峰
   */
  public FloorDevelopInfoDTO queryDevelopInfo(final Integer id) {
    FloorDevelopInfoDTO floorDevelopInfo = null;
    try {
      floorDevelopInfo = (FloorDevelopInfoDTO) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select " + "p5.developer_code,"
                  + "p5.developer_name," + "p5.land_use_id,"
                  + "p5.sale_permis," + "p5.office," + "p5.code,"
                  + "p5.agreement_start_date," + "p5.agreement_end_date,"
                  + "p5.developer_paybank_a," + "p5.developer_paybank_a_acc,"
                  + "p5.developer_paybank_b," + "p5.developer_paybank_b_acc,"
                  + "p5.developer_addr,p5.artfclprsn,p5.register_fundnumber "
                  + " from pl005 p5 where p5.id=?";

              Query query = session.createSQLQuery(hql);
              query.setParameter(0, id);

              Object[] obj = (Object[]) query.uniqueResult();
              FloorDevelopInfoDTO floorDevelopInfo = new FloorDevelopInfoDTO();

              floorDevelopInfo.setDeveloperId(obj[0].toString());
              floorDevelopInfo.setDeveloperName(obj[1].toString());
              if (obj[2] != null) {
                floorDevelopInfo.setLandUseId(obj[2].toString());
              }
              if (obj[3] != null) {
                floorDevelopInfo.setSalePermis(obj[3].toString());
              }
              floorDevelopInfo.setOffice(obj[4].toString());
              if (obj[5] != null) {
                floorDevelopInfo.setCode(obj[5].toString());
              }
              if (obj[6] != null) {
                floorDevelopInfo.setAgreementStartDate(obj[6].toString());
              }
              if (obj[7] != null) {
                floorDevelopInfo.setAgreementEndDate(obj[7].toString());
              }
              if (obj[8] != null) {
                floorDevelopInfo.setDeveloperPaybankA(obj[8].toString());
              }
              if (obj[9] != null) {
                floorDevelopInfo.setDeveloperPaybankAAcc(obj[9].toString());
              }
              if (obj[10] != null) {
                floorDevelopInfo.setDeveloperPaybankB(obj[10].toString());
              }
              if (obj[11] != null) {
                floorDevelopInfo.setDeveloperPaybankBAcc(obj[11].toString());
              }
              if (obj[12] != null) {
                floorDevelopInfo.setDeveloperAddr(obj[12].toString());
              }
              if (obj[13] != null) {
                floorDevelopInfo.setArtfclprsn(obj[13].toString());
              }
              if (obj[14] != null && !"".equals(obj[14].toString())) {
                floorDevelopInfo.setRegisterFundNumber(new BigDecimal(obj[14]
                    .toString()));
              }

              return floorDevelopInfo;
            }
          });

    } catch (Exception e) {
      e.printStackTrace();
    }
    return floorDevelopInfo;
  }

  /**
   * 根据表内主键求的房产信息 hal
   * 
   * @param developer
   * @return
   */
  public BuyHouserDTO findHosesSomeInfo(final String buyhouseorgid) {

    BuyHouserDTO buyhouserDTO = new BuyHouserDTO();
    try {
      buyhouserDTO = (BuyHouserDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select distinct p5.developer_name,p5.org_tel from pl005 p5 where p5.id=?";
              Object[] obj = null;
              Query query = session.createSQLQuery(hql);
              query.setString(0, buyhouseorgid);
              obj = (Object[]) query.uniqueResult();
              BuyHouserDTO bDTO = null;
              if (obj != null) {
                bDTO = new BuyHouserDTO();
                if (obj[0] != null) {
                  bDTO.setDeveloperName(obj[0].toString());
                }
                if (obj[1] != null) {
                  bDTO.setDeveloperTel(obj[1].toString());
                }
              }
              return bDTO;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return buyhouserDTO;

  }

  /**
   * han 查询银行
   * 
   * @param pkid
   * @return
   */

  public List findBankListById(final String pkid) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select p5.developer_paybank_a,p5.developer_paybank_b,p5.developer_paybank_a_acc,p5.developer_paybank_b_acc from pl005 p5 where p5.id=? ";
          Object[] obj = null;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, pkid);
          obj = (Object[]) query.uniqueResult();
          List li = new ArrayList();
          if (obj[0] != null) {
            BankListDTO bdto = new BankListDTO();
            bdto.setBankKey(obj[2].toString());
            bdto.setBankValue(obj[0].toString());
            bdto.setBankacc(obj[2].toString());
            li.add(bdto);
          }
          if (obj[1] != null) {
            BankListDTO bdto = new BankListDTO();
            bdto.setBankKey(obj[3].toString());
            bdto.setBankValue(obj[1].toString());
            bdto.setBankacc(obj[3].toString());
            li.add(bdto);
          }

          return li;
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl 正常状态下的楼盘
   * 
   * @param pkid
   * @return
   */
  public List findSomeFloorListById(final String pkid) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select p.head_id,p.floor_name from pl006 p "
              + " where p.head_id=? and p.floor_st='0'"
              + " group by p.head_id,floor_name" + " order by min(p.floor_id)";
          Object[] obj = null;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, pkid);
          if (query.list() == null) {
            return null;
          }
          List li = new ArrayList();
          Iterator it = query.list().iterator();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            FloorListDTO fdto = new FloorListDTO();
            fdto.setFloorKey(obj[0].toString());
            fdto.setFloorValue(obj[1].toString());
            li.add(fdto);
          }
          return li;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl 所有状态下的楼盘
   * 
   * @param pkid
   * @return
   */
  public List findAllFloorListById(final String pkid) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select p.head_id,p.floor_name from pl006 p "
              + " where p.head_id=? and p.floor_st='0'"
              + " group by p.head_id,floor_name" + " order by min(p.floor_id)";

          Object[] obj = null;
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, pkid);
          if (query.list() == null) {
            return null;
          }
          List li = new ArrayList();
          Iterator it = query.list().iterator();
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            FloorListDTO fdto = new FloorListDTO();
            fdto.setFloorKey(obj[0].toString());
            fdto.setFloorValue(obj[1].toString());
            li.add(fdto);
          }
          return li;

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
   * @author 王野 2007-10-13 获得开发商的ID和NAME
   * @return
   */
  public List queryAllDevelopIdAndDevelopName() {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.id, t.developer_name from pl005 t ";
          Query query = session.createSQLQuery(hql);

          Iterator iterate = query.list().iterator();

          DeveloperDTO developerDTO = null;
          List tempList = new ArrayList();
          Object obj[] = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            developerDTO = new DeveloperDTO();
            if (obj[0] != null && !obj[0].equals(""))
              developerDTO.setDeveloperid(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
              developerDTO.setDevelopername(obj[1].toString());
            tempList.add(developerDTO);
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
   * @author 王野 2007-10-13 通过pl005.id获得开发商NAME
   * @param id
   * @return
   */
  public String queryDeveloperNameByDeveloperId(final String id) {
    String name = null;
    try {
      name = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.developer_name from pl005 t where t.id = ? ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, new Integer(id));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return name;
  }

  /**
   * hanl 求出楼盘名称
   * 
   * @param floorId
   * @return
   */
  public String findfloorName(final String floorId) {
    String name = null;
    try {
      name = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select t.floor_name from pl006 t where t.floor_id=? ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, new Integer(floorId));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return name;
  }

  /**
   * hanL 删除pl110
   */
  public void deleteDeveloperInfo(final Serializable id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " update Developer developer set developer.developerSt=? where developer.id=?";
          Query query = session.createQuery(sql);
          query.setParameter(0, new Integer(3));
          query.setParameter(1, new Integer(id.toString()));
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void delFloorInfo(final Integer id) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String sql = " update DevelopProject developer set developer.floorSt=? where developer.floorId=?";
          Query query = session.createQuery(sql);
          query.setParameter(0, new Integer(3));
          query.setParameter(1, id);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List findBuildingListByHeadid(final String developerId,
      final String developerName, final String orderBy, final String order,
      final int start, final int pageSize) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select p6.head_id,p5.developer_name,p6.floor_name "
              + " from pl006 p6,pl005 p5 where p5.id = p6.head_id ";
          Vector parameters = new Vector();
          String criterion = "";
          if (developerId != null && !developerId.equals("")) {
            criterion += " p6.head_id = ? and";
            parameters.add(developerId);
          }
          if (developerName != null && !developerName.trim().equals("")) {
            criterion += " p5.developer_name like ? and";
            parameters.add("%" + developerName.trim() + "%");
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " p6.head_id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql
              + criterion
              + " group by p6.head_id,p5.developer_name,p6.floor_name order by "
              + ob + " " + od;
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Object obj[] = null;
          BuildingPopListDTO dto = null;
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // 将查询的结果封装到DTO中
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new BuildingPopListDTO();
            dto.setDeveloperId(obj[0].toString());
            dto.setDeveloperName(obj[1].toString());
            dto.setFloorName(obj[2].toString());
            temp_list.add(dto);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List findBuildingAllListByHeadid(final String developerId,
      final String developerName) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select p6.head_id,p5.developer_name,p6.floor_name "
              + " from pl006 p6,pl005 p5 where p5.id = p6.head_id ";
          Vector parameters = new Vector();
          String criterion = "";
          if (developerId != null && !developerId.equals("")) {
            criterion += " p6.head_id = ? and";
            parameters.add(developerId);
          }
          if (developerName != null && !developerName.trim().equals("")) {
            criterion += " p5.developer_name like ? and";
            parameters.add("%" + developerName.trim() + "%");
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion
              + " group by p6.head_id,p5.developer_name,p6.floor_name ";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Object obj[] = null;
          BuildingPopListDTO dto = null;
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // 将查询的结果封装到DTO中
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            dto = new BuildingPopListDTO();
            dto.setDeveloperId(obj[0].toString());
            dto.setDeveloperName(obj[1].toString());
            dto.setFloorName(obj[2].toString());
            temp_list.add(dto);
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
