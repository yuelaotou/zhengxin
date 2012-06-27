package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.business;

import java.util.ArrayList;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.bsinterface.IOrgBaseInfoBS;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.dto.OrgBaseInfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.form.OrgInfoSearchAF;
import org.xpup.hafmis.syscommon.domain.entity.PayBank;
import org.xpup.hafmis.syscommon.domain.entity.Transactor;

public class OrgBaseInfoBS implements IOrgBaseInfoBS {
  protected OrgDAO orgDAO = null;

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public Org findOrgInfoById(Integer orgId) throws BusinessException {

    try {
      Org org = orgDAO.getOrg_WL(orgId.toString());
      org.getOrgInfo().setTemp_officename(
          orgDAO.findOfficeNameByOrgInfoOfficeCode(org.getOrgInfo()
              .getOfficecode()));
      org.getOrgInfo().setTemp_collectionBankname(
          orgDAO.findCollBanknameByOrgInfoCollectionBankId(org.getOrgInfo()
              .getCollectionBankId()));
      org.getOrgInfo().setTemp_character(
          BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
              .getCharacter()), BusiConst.NATUREOFUNITS));
      org.getOrgInfo().setTemp_deptInCharge(
          BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
              .getDeptInCharge()), BusiConst.AUTHORITIES));
      org.getOrgInfo().setTemp_openstatus(
          BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
              .getOpenstatus()), BusiConst.ORGSTATE));
      org.getOrgInfo().setTemp_industry(
          BusiTools.getBusiValue_WL(org.getOrgInfo().getIndustry(),
              BusiConst.INDUSTRY));
      org.setPayModeStr(BusiTools.getBusiValue(Integer.parseInt(org
          .getPayMode().toString()), BusiConst.ORGPAYWAY));
      org.getOrgInfo()
          .setTemp_region(
              BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
                  .getRegion()), BusiConst.INAREA));
      PayBank pb = org.getOrgInfo().getPayBank();
      if (pb == null) {
        org.getOrgInfo().setPayBank(new PayBank());
      }
      Transactor transactor = org.getOrgInfo().getTransactor();
      if (transactor == null) {
        transactor = new Transactor();
        transactor.setName("");
        org.getOrgInfo().setTransactor(transactor);
      }
      return org;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List findOrgList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = null;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    OrgInfoSearchAF orgInfoSearchAF = (OrgInfoSearchAF) pagination
        .getQueryCriterions().get("search");
    List returnlist = new ArrayList();
    if (orgInfoSearchAF != null) {
      list = orgDAO.queryOrgBaseInfoByCriterions(orgInfoSearchAF, orderBy,
          order, start, pageSize, securityInfo);
      if (list != null) {
        for (int i = 0; i < list.size(); i++) {
          OrgBaseInfoDTO orgBaseInfoDTO = (OrgBaseInfoDTO) list.get(i);
//          org.getOrgInfo().setTemp_officename(
//              orgDAO.findOfficeNameByOrgInfoOfficeCode(org.getOrgInfo()
//                  .getOfficecode()));
//          org.getOrgInfo().setTemp_collectionBankname(
//              orgDAO.findCollBanknameByOrgInfoCollectionBankId(org.getOrgInfo()
//                  .getCollectionBankId()));
          orgBaseInfoDTO.setCharacter(
              BusiTools.getBusiValue(Integer.parseInt(orgBaseInfoDTO
                  .getCharacter()), BusiConst.NATUREOFUNITS));
          orgBaseInfoDTO.setDeptInCharge(
              BusiTools.getBusiValue(Integer.parseInt(orgBaseInfoDTO
                  .getDeptInCharge()), BusiConst.AUTHORITIES));
          orgBaseInfoDTO.setOpenStatus(
              BusiTools.getBusiValue(Integer.parseInt(orgBaseInfoDTO
                  .getOpenStatus()), BusiConst.ORGSTATE));
          
          returnlist.add(orgBaseInfoDTO);
        }
      }
      List allList = orgDAO.queryOrgBaseInfoCountByCriterions(orgInfoSearchAF,
          orderBy, order, securityInfo);
      int personcount = 0;
      if (allList != null) {
        for (int i = 0; i < allList.size(); i++) {
          OrgBaseInfoDTO orgBaseInfoDTO = (OrgBaseInfoDTO) allList.get(i);
//        org.getOrgInfo().setTemp_officename(
//            orgDAO.findOfficeNameByOrgInfoOfficeCode(org.getOrgInfo()
//                .getOfficecode()));
//        org.getOrgInfo().setTemp_collectionBankname(
//            orgDAO.findCollBanknameByOrgInfoCollectionBankId(org.getOrgInfo()
//                .getCollectionBankId()));
        orgBaseInfoDTO.setCharacter(
            BusiTools.getBusiValue(Integer.parseInt(orgBaseInfoDTO
                .getCharacter()), BusiConst.NATUREOFUNITS));
        orgBaseInfoDTO.setDeptInCharge(
            BusiTools.getBusiValue(Integer.parseInt(orgBaseInfoDTO
                .getDeptInCharge()), BusiConst.AUTHORITIES));
        orgBaseInfoDTO.setOpenStatus(
            BusiTools.getBusiValue(Integer.parseInt(orgBaseInfoDTO
                .getOpenStatus()), BusiConst.ORGSTATE));
          personcount+=Integer.parseInt(orgBaseInfoDTO.getOrgCount());
        }
      }
      pagination.getQueryCriterions().put("printlist", allList);
      pagination.getQueryCriterions().put("personcount", personcount+"");
      pagination.setNrOfElements(allList.size());
    }
    return returnlist;
  }

  public List findOrgAllList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException {

    List list = null;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    OrgInfoSearchAF orgInfoSearchAF = (OrgInfoSearchAF) pagination
        .getQueryCriterions().get("search");
    List returnlist = new ArrayList();
    if (orgInfoSearchAF != null) {
      list = orgDAO.queryOrgBaseInfoCountByCriterions(orgInfoSearchAF, orderBy,
          order, securityInfo);
      if (list != null) {
        for (int i = 0; i < list.size(); i++) {
          Org org = (Org) list.get(i);
          org.getOrgInfo().setTemp_officename(
              orgDAO.findOfficeNameByOrgInfoOfficeCode(org.getOrgInfo()
                  .getOfficecode()));
          org.getOrgInfo().setTemp_collectionBankname(
              orgDAO.findCollBanknameByOrgInfoCollectionBankId(org.getOrgInfo()
                  .getCollectionBankId()));
          org.getOrgInfo().setTemp_character(
              BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
                  .getCharacter()), BusiConst.NATUREOFUNITS));
          org.getOrgInfo().setTemp_deptInCharge(
              BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
                  .getDeptInCharge()), BusiConst.AUTHORITIES));
          org.getOrgInfo().setTemp_openstatus(
              BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
                  .getOpenstatus()), BusiConst.ORGSTATE));
          org.setPayModeStr(BusiTools.getBusiValue(Integer.parseInt(org
              .getPayMode().toString()), BusiConst.ORGPAYWAY));
          org.getOrgInfo().setTemp_region(
              BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo()
                  .getRegion()), BusiConst.INAREA));
          PayBank pb = org.getOrgInfo().getPayBank();
          if (pb == null) {
            org.getOrgInfo().setPayBank(new PayBank());
          }
          if (org.getOrgInfo().getTransactor() == null) {
            org.getOrgInfo().setTransactor(new Transactor());
          }
          returnlist.add(org);
        }
      }
    }
    return returnlist;

  }
}
