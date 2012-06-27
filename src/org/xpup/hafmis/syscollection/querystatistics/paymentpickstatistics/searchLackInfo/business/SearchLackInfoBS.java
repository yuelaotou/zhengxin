package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.SearchLackInfoDAO;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.AddpayInfoDto;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.bsinterface.ISearchLackInfoBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoContentDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoHeadDTO;
import org.xpup.hafmis.syscommon.dao.OrgInfoDAO;

public class SearchLackInfoBS implements ISearchLackInfoBS {

  private SearchLackInfoDAO searchLackInfoDAO = null;

  public void setSearchLackInfoDAO(SearchLackInfoDAO searchLackInfoDAO) {
    this.searchLackInfoDAO = searchLackInfoDAO;
  }

  /**
   * 根据条件查询欠缴列表
   */
  public List findSearchLackInfoAllByCriterions(Pagination pagination,
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
      String natureOfUnits = (String) pagination.getQueryCriterions().get(
          "natureOfUnits");
      String authorities = (String) pagination.getQueryCriterions().get(
          "authorities");
      String orgcode = (String) pagination.getQueryCriterions().get("orgId");
      String orgname = (String) pagination.getQueryCriterions().get("orgName");
      String chgMonthStart = (String) pagination.getQueryCriterions().get(
          "chgMonthStart");
      String chgMonthEnd = (String) pagination.getQueryCriterions().get(
          "chgMonthEnd");
      String inArea = (String) pagination.getQueryCriterions().get("inArea");
      String yearMonth = (String) pagination.getQueryCriterions().get(
          "yearMonth");
      String orgratebeg = (String) pagination.getQueryCriterions().get(
      "orgratebeg");
      String orgrateend = (String) pagination.getQueryCriterions().get(
      "orgrateend");
      String empratebeg = (String) pagination.getQueryCriterions().get(
      "empratebeg");
      String emprateend = (String) pagination.getQueryCriterions().get(
      "emprateend");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = searchLackInfoDAO.findSearchLackInfoListByCriterions_jj(
          officecode, bankcode, natureOfUnits, authorities, orgcode, orgname,
          chgMonthStart, chgMonthEnd, inArea, yearMonth, orgratebeg, orgrateend, empratebeg, emprateend, orderBy, order, start,
          pageSize, page, securityInfo);

      listcount = searchLackInfoDAO.findSearchLackInfoListByCriterionsALL_jj(
          officecode, bankcode, natureOfUnits, authorities, orgcode, orgname,
          chgMonthStart, chgMonthEnd, inArea, yearMonth, orgratebeg, orgrateend, empratebeg, emprateend, orderBy, order,
          securityInfo);

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
   * 根据条件查询欠缴全部列表
   */
  public List findSearchLackInfoByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  public List findSearchLackInfoListByCriterions_oldsys(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    List listcount = null;
    try {
      list = new ArrayList();
      listcount = new ArrayList();

      String orgid_old = (String) pagination.getQueryCriterions().get(
          "orgId_old_q");
      String orgname_old = (String) pagination.getQueryCriterions().get(
          "orgName_old_q");
      String yearMonth = (String) pagination.getQueryCriterions().get(
          "yearMonth_old_q");

      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = searchLackInfoDAO.findSearchLackInfoListByCriterions_oldsys(
          orgid_old, orgname_old, yearMonth, start, pageSize, page,
          securityInfo);

      listcount = searchLackInfoDAO
          .findSearchLackInfoListByCriterions_oldsys_all(orgid_old,
              orgname_old, yearMonth, securityInfo);

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
   * 根据条件查询欠缴单笔信息
   */
  public SearchLackInfoContentDTO findSearchLackInfoOneByCriterions(
      String orgid, Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = null;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    SearchLackInfoContentDTO dto = null;
    String yearMonth = (String) pagination.getQueryCriterions()
        .get("yearMonth");
    list = searchLackInfoDAO.findSearchLackInfoListByCriterionsALL_jj("", "",
        "", "", orgid, "", "", "", "", yearMonth,"", "", "", "", orderBy, order, securityInfo);
    if (!list.isEmpty()) {
      dto = (SearchLackInfoContentDTO) list.get(0);
    }
    return dto;
  }

  /**
   * 显示合计信息
   */
  public SearchLackInfoHeadDTO findSearchLackInfoListHead(List list)
      throws Exception, BusinessException {
    SearchLackInfoHeadDTO dto = new SearchLackInfoHeadDTO();
    SearchLackInfoContentDTO searchLackInfoContentDTO = null;
    try {
      for (int i = 0; i < list.size(); i++) {
        searchLackInfoContentDTO = (SearchLackInfoContentDTO) list.get(i);
        dto.setOrgpayLack(dto.getOrgpayLack().add(
            new BigDecimal(searchLackInfoContentDTO.getOrgPay().toString())));
        dto.setEmppayLack(dto.getEmppayLack().add(
            new BigDecimal(searchLackInfoContentDTO.getEmpPay().toString())));
        dto.setSumpayLack(dto.getSumpayLack().add(
            new BigDecimal(searchLackInfoContentDTO.getSumPay().toString())));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }

  public void createSearchLackInfo() {
    searchLackInfoDAO.executeCreateSearchLackInfo();
  }

  /**
   * @param id
   * @param orgId
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public List findOrgAddPayExpList_old(String id, String orgId, String orgName,
      String addPayMonth) throws Exception, BusinessException {
    List list = null;
    try {
      list = searchLackInfoDAO.queryOrgAddPayExpList_old(id, BusiTools
          .removePreZero(orgId));
      AddpayInfoDto dto = null;
      for (int i = 0; i < list.size(); i++) {
        dto = (AddpayInfoDto) list.get(i);
        dto.setEmpPayStatus(BusiTools.getBusiValue(Integer.parseInt(dto
            .getEmpPayStatus()), BusiConst.OLDPAYMENTSTATE));
        dto.setAddStartPayMonth(addPayMonth);
        dto.setAddpayMonth(addPayMonth);
        dto.setOrgid(orgId);
        dto.setOrgName(orgName);
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }
}
