package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.business;

import java.util.ArrayList;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.SettInterestResultDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.bsinterface.IYearclearstatisticsBS;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.dto.YearclearstatisticsHeadDTO;

public class YearclearstatisticsBS implements IYearclearstatisticsBS {

  private SettInterestResultDAO settInterestResultDAO = null;
  private CollBankDAO collBankDAO = null;
  private OrgDAO orgDAO = null;
  public void setSettInterestResultDAO(
      SettInterestResultDAO settInterestResultDAO) {
    this.settInterestResultDAO = settInterestResultDAO;
  }

  /**
   * 根据条件查询年终结息全部列表
   */
  public List findYearclearstatisticsAllByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    List listcount = null;
    try {
      list = new ArrayList();
      listcount = new ArrayList();

      String officecode = (String) pagination.getQueryCriterions().get(
          "officeCode");
      String bankcode = (String) pagination.getQueryCriterions().get(
          "collectionBank");
      String orgcode = (String) pagination.getQueryCriterions().get("orgId");
      String orgname = (String) pagination.getQueryCriterions().get("orgName");
      String headId = (String) pagination.getQueryCriterions().get("headId");
      String chgYearStart = (String) pagination.getQueryCriterions().get(
          "chgYearStart");
      String chgYearEnd = (String) pagination.getQueryCriterions().get(
          "chgYearStart");
      String isZero = (String) pagination.getQueryCriterions().get(
      "isZero");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = settInterestResultDAO.findYearclearstatisticsListByCriterions_wsh(
          officecode, bankcode, orgcode, orgname, chgYearStart, chgYearEnd,
          orderBy, order, start, pageSize, page, securityInfo,headId,isZero);

      listcount = settInterestResultDAO
          .findYearclearstatisticsListCountByCriterions_wsh(officecode,
              bankcode, orgcode, orgname, chgYearStart, chgYearEnd,
              securityInfo,headId,isZero);
      pagination.getQueryCriterions().put("listcount", listcount);
      if (listcount != null && listcount.size() != 0) {
        pagination.setNrOfElements(listcount.size());
      }

    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 显示合计信息
   */
  public YearclearstatisticsHeadDTO findYearclearstatisticsListHead(List list)
      throws Exception, BusinessException {
    YearclearstatisticsHeadDTO dto = new YearclearstatisticsHeadDTO();
    YearclearstatisticsHeadDTO dto_return = null;
    try {
      for (int i = 0; i < list.size(); i++) {
        dto_return = (YearclearstatisticsHeadDTO) list.get(i);
        dto.setOrgcounts(new Integer(((i + 1)) + ""));
        dto.setEmpcounts(new Integer(
            (dto.getEmpcounts().intValue() + dto_return.getEmpcounts()
                .intValue())
                + ""));
        dto.setOldpreblance(dto.getOldpreblance().add(
            dto_return.getOldpreblance()));
        dto.setOldcurblance(dto.getOldcurblance().add(
            dto_return.getOldcurblance()));
        dto.setPreinterest(dto.getPreinterest()
            .add(dto_return.getPreinterest()));
        dto.setCurinterest(dto.getCurinterest()
            .add(dto_return.getCurinterest()));
        dto.setPreblance(dto.getPreblance().add(dto_return.getPreblance()));
        dto.setCurblance(dto.getCurblance().add(dto_return.getCurblance()));
        dto.setBlance(dto.getBlance().add(dto_return.getBlance()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }

  /**
   * 根据条件查询职工列表年终结息全部列表
   */
  public List findYearclearEmpAllByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    List listcount = null;
    try {
      list = new ArrayList();
      listcount = new ArrayList();

      String orgcode = (String) pagination.getQueryCriterions().get("orgId");
      String orgname = (String) pagination.getQueryCriterions().get("orgName");
      String empcode = (String) pagination.getQueryCriterions().get("empId");
      String empname = (String) pagination.getQueryCriterions().get("empName");
      String chgYearStart = (String) pagination.getQueryCriterions().get(
          "chgYearStart");
      String chgYearEnd = (String) pagination.getQueryCriterions().get(
          "chgYearEnd");
      String headid = (String) pagination.getQueryCriterions().get("headid");
      String orgid = (String) pagination.getQueryCriterions().get("orgid");
      String isZero = (String) pagination.getQueryCriterions().get("isZero");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = settInterestResultDAO.findYearclearEmpListByCriterions_wsh(orgcode,
          orgname, empcode, empname, chgYearStart, chgYearEnd, headid, orgid,
          orderBy, order, start, pageSize, page, securityInfo,isZero);

      listcount = settInterestResultDAO
          .findYearclearEmpListCountByCriterions_wsh(orgcode, orgname, empcode,
              empname, chgYearStart, chgYearEnd, headid, orgid, securityInfo,isZero);
      pagination.getQueryCriterions().put("listcount_yearclear", listcount);
      if (listcount != null && listcount.size() != 0) {
        pagination.setNrOfElements(listcount.size());
      }

    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 显示职工列表合计信息
   */
  public YearclearstatisticsHeadDTO findYearclearEmpListHead(List list)
      throws Exception, BusinessException {
    YearclearstatisticsHeadDTO dto = new YearclearstatisticsHeadDTO();
    YearclearstatisticsHeadDTO dto_return = null;
    try {
      for (int i = 0; i < list.size(); i++) {
        dto_return = (YearclearstatisticsHeadDTO) list.get(i);
        dto.setEmpcounts(new Integer(((i + 1)) + ""));
        dto.setBlance(dto.getBlance().add(dto_return.getBlance()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }

  /**
   * 根据条件查询单个职工列表年终结息
   */
  public List findYearclearEmpByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    List listcount = null;
    try {
      list = new ArrayList();
      listcount = new ArrayList();

      String headid = (String) pagination.getQueryCriterions().get("headid");
      String orgid = (String) pagination.getQueryCriterions().get("orgid");
      String empcode = (String) pagination.getQueryCriterions().get("empcode");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = settInterestResultDAO.findYearclearEmpByCriterions_WL(orgid,
          empcode, headid, orderBy, order, start, pageSize, page, securityInfo);
      if (list != null && list.size() != 0) {
        // 转换
        YearclearstatisticsHeadDTO dto = null;
        for (int i = 0; i < list.size(); i++) {
          dto = (YearclearstatisticsHeadDTO) list.get(i);
          dto.setTypes(BusiTools.getBusiValue_WL(dto.getTypes(),
              BusiConst.CLEARINTERESTTYPE));
        }
      }

      listcount = settInterestResultDAO.findYearclearEmpCountByCriterions_WL(
          orgid, empcode, headid, securityInfo);
      if (listcount != null && listcount.size() != 0) {
        pagination.setNrOfElements(listcount.size());
      }

    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public String findOrg(String orgId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String bankName="";
    Org org=null;
    try {
      org=orgDAO.queryById(new Integer(orgId));
     
      CollBank dto = collBankDAO.getCollBankByCollBankid(org.getOrgInfo().getCollectionBankId());
      bankName=dto.getCollBankName();
     
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return bankName;
  }

  public String clearperson(String bizId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String message="";
    try{
       message=orgDAO.getAa101ClearPerson_wsh(bizId);
     
    }catch(Exception e){
      e.printStackTrace();
    }
    return message;
  }
  public String queryMakerPara() throws Exception
  {
    String name="";
    name=collBankDAO.getNamePara();
    return name;
    
  }
}
