package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.PledgeContractDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.DeveloperDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.bsinterface.IAssureModeBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.dto.AssureModeDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.dto.FloorDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form.AssureModeAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form.FloorPOPNewAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 王野 2007-10-11
 */
public class AssureModeBS implements IAssureModeBS {

  private PledgeContractDAO pledgeContractDAO = null;// 抵押合同信息表 PL121

  private CollBankDAO collBankDAO = null;// 转换银行名称

  private OrganizationUnitDAO organizationUnitDAO = null;// 转换办事处

  private AssistantOrgDAO assistantOrgDAO = null;// PL007 获得担保公司下拉框

  private DeveloperDAO developerDAO = null;// PL005 开发商信息表

  private DevelopProjectDAO developProjectDAO = null;// PL006 开发商项目 楼盘

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public void setPledgeContractDAO(PledgeContractDAO pledgeContractDAO) {
    this.pledgeContractDAO = pledgeContractDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  /**
   * 担保方式统计查询列表
   * 
   * @author 王野 2007-10-11
   */
  public AssureModeAF queryAssureModeListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String mode = (String) pagination.getQueryCriterions().get("mode");
    String office = (String) pagination.getQueryCriterions().get("office");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String developerId = (String) pagination.getQueryCriterions().get(
        "developerId");
    String floorNum = (String) pagination.getQueryCriterions().get("floorNum");
    String floorId = (String) pagination.getQueryCriterions().get("floorId");
    String assistantOrgName = (String) pagination.getQueryCriterions().get(
        "assistantOrgName");
    String assistantOrgId = (String) pagination.getQueryCriterions().get(
        "assistantOrgId");
    AssureModeAF assureModeAF = new AssureModeAF();
    List officeList = new ArrayList();
    String officeCode = null;
    List list1 = null;
    List list2 = null;
    List list3 = null;
    List list4 = null;
    List loanBankList = new ArrayList();
    String loanBankId = null;
    List developerList = new ArrayList();
    List floorNumList = new ArrayList();
    List assistOrgList = new ArrayList();
    List returnList = new ArrayList();
    String type = (String) pagination.getQueryCriterions().get("type");
    if (type == null) {
      type = "1";
    }
    try {
      if (!type.equals("0")) {
        if (mode.equals("1")) {
          if (office.equals("all")) {
            List tempOfficeList = securityInfo.getOfficeList();
            for (int i = 0; i < tempOfficeList.size(); i++) {
              OfficeDto officeDto = (OfficeDto) tempOfficeList.get(i);
              officeList.add(officeDto.getOfficeCode());
            }
          } else {
            officeList.add(office);
          }
          // begin
          for (int i = 0; i < officeList.size(); i++) {
            officeCode = (String) officeList.get(i);
            AssureModeDTO assureModeDTO1 = new AssureModeDTO();
            OrganizationUnit organizationUnit = organizationUnitDAO
                .queryOrganizationUnitListByCriterions(officeCode);
            String officeName = organizationUnit.getName();
            assureModeDTO1.setOffice(officeName);
            list1 = pledgeContractDAO.queryAssureModeListByCriterionsPledge(
                officeCode, loanBankId, developerId, floorNum, assistantOrgId,
                securityInfo);
            if (!list1.isEmpty()) {
              for (int j = 0; j < list1.size(); j++) {
                AssureModeDTO dto1 = (AssureModeDTO) list1.get(j);
                assureModeDTO1.setPledgeCount(dto1.getPledgeCount());// 抵押户数
                assureModeDTO1.setPledgeValue(dto1.getPledgeValue());// 抵押金额
              }
            }
            list2 = pledgeContractDAO.queryAssureModeListByCriterionsImpawn(
                officeCode, loanBankId, developerId, floorNum, assistantOrgId,
                securityInfo);
            if (!list2.isEmpty()) {
              for (int j = 0; j < list2.size(); j++) {
                AssureModeDTO dto2 = (AssureModeDTO) list2.get(j);
                assureModeDTO1.setImpawnCount(dto2.getImpawnCount());// 质押户数
                assureModeDTO1.setImpawnValue(dto2.getImpawnValue());// 质押金额
              }
            }
            list3 = pledgeContractDAO
                .queryAssureModeListByCriterionsPledgeAssurer(officeCode,
                    loanBankId, developerId, floorNum, assistantOrgId,
                    securityInfo);
            if (!list3.isEmpty()) {
              for (int j = 0; j < list3.size(); j++) {
                AssureModeDTO dto3 = (AssureModeDTO) list3.get(j);
                assureModeDTO1.setPledgeAssurerCount(dto3
                    .getPledgeAssurerCount());// 抵押+保证
                // 户数
                assureModeDTO1.setPledgeAssurerValue(dto3
                    .getPledgeAssurerValue());// 抵押+保证
                // 金额
              }
            }
            list4 = pledgeContractDAO
                .queryAssureModeListByCriterionsImpawnAssurer(officeCode,
                    loanBankId, developerId, floorNum, assistantOrgId,
                    securityInfo);
            if (!list4.isEmpty()) {
              for (int j = 0; j < list4.size(); j++) {
                AssureModeDTO dto4 = (AssureModeDTO) list4.get(j);
                assureModeDTO1.setImpawnAssurerCount(dto4
                    .getImpawnAssurerCount());// 抵押+保证
                // 户数
                assureModeDTO1.setImpawnAssurerValue(dto4
                    .getImpawnAssurerValue());// 抵押+保证
                // 金额
              }
            }
            // 合计
            // 户数
            int totalCount = new Integer(assureModeDTO1.getPledgeCount())
                .intValue()
                + new Integer(assureModeDTO1.getImpawnCount()).intValue()
                + new Integer(assureModeDTO1.getPledgeAssurerCount())
                    .intValue()
                + new Integer(assureModeDTO1.getImpawnAssurerCount())
                    .intValue();
            // 金额
            BigDecimal totalValue = new BigDecimal(assureModeDTO1
                .getPledgeValue()).add(new BigDecimal(assureModeDTO1
                .getImpawnValue()).add(new BigDecimal(assureModeDTO1
                .getPledgeAssurerValue()).add(new BigDecimal(assureModeDTO1
                .getImpawnAssurerValue()))));
            assureModeDTO1.setTotalCount(totalCount + "");
            assureModeDTO1.setTotalValue(totalValue.toString());
            assureModeDTO1.setLoanBankName("");
            assureModeDTO1.setDeveloperName("");
            assureModeDTO1.setFloorNum("");
            assureModeDTO1.setAssistantOrgName("");
            returnList.add(assureModeDTO1);
          }
          // end
        } else if (mode.equals("2")) {
          if (loanBankName.equals("all")) {
            // 获取权限中的银行
            List bankList = securityInfo.getDkUserBankList();
            Userslogincollbank userslogincollbank = null;
            Iterator bank = bankList.iterator();
            while (bank.hasNext()) {
              userslogincollbank = (Userslogincollbank) bank.next();
              loanBankList.add(userslogincollbank.getCollBankId().toString());
            }
          } else {
            loanBankList.add(loanBankName);
          }

          // begin
          for (int i = 0; i < loanBankList.size(); i++) {
            loanBankId = (String) loanBankList.get(i);
            AssureModeDTO assureModeDTO1 = new AssureModeDTO();
            String officeID = pledgeContractDAO
                .queryOfficeIdByBankId(loanBankId);
            OrganizationUnit organizationUnit = organizationUnitDAO
                .queryOrganizationUnitListByCriterions(officeID);
            String officeName = organizationUnit.getName();
            assureModeDTO1.setOffice(officeName);
            // 转换银行名称
            CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId);
            assureModeDTO1.setLoanBankName(dto.getCollBankName());
            list1 = pledgeContractDAO.queryAssureModeListByCriterionsPledge(
                officeCode, loanBankId, developerId, floorNum, assistantOrgId,
                securityInfo);
            if (!list1.isEmpty()) {
              for (int j = 0; j < list1.size(); j++) {
                AssureModeDTO dto1 = (AssureModeDTO) list1.get(j);
                assureModeDTO1.setPledgeCount(dto1.getPledgeCount());// 抵押户数
                assureModeDTO1.setPledgeValue(dto1.getPledgeValue());// 抵押金额
              }
            }
            list2 = pledgeContractDAO.queryAssureModeListByCriterionsImpawn(
                officeCode, loanBankId, developerId, floorNum, assistantOrgId,
                securityInfo);
            if (!list2.isEmpty()) {
              for (int j = 0; j < list2.size(); j++) {
                AssureModeDTO dto2 = (AssureModeDTO) list2.get(j);
                assureModeDTO1.setImpawnCount(dto2.getImpawnCount());// 质押户数
                assureModeDTO1.setImpawnValue(dto2.getImpawnValue());// 质押金额
              }
            }
            list3 = pledgeContractDAO
                .queryAssureModeListByCriterionsPledgeAssurer(officeCode,
                    loanBankId, developerId, floorNum, assistantOrgId,
                    securityInfo);
            if (!list3.isEmpty()) {
              for (int j = 0; j < list3.size(); j++) {
                AssureModeDTO dto3 = (AssureModeDTO) list3.get(j);
                assureModeDTO1.setPledgeAssurerCount(dto3
                    .getPledgeAssurerCount());// 抵押+保证
                // 户数
                assureModeDTO1.setPledgeAssurerValue(dto3
                    .getPledgeAssurerValue());// 抵押+保证
                // 金额
              }
            }
            list4 = pledgeContractDAO
                .queryAssureModeListByCriterionsImpawnAssurer(officeCode,
                    loanBankId, developerId, floorNum, assistantOrgId,
                    securityInfo);
            if (!list4.isEmpty()) {
              for (int j = 0; j < list4.size(); j++) {
                AssureModeDTO dto4 = (AssureModeDTO) list4.get(j);
                assureModeDTO1.setImpawnAssurerCount(dto4
                    .getImpawnAssurerCount());// 抵押+保证
                // 户数
                assureModeDTO1.setImpawnAssurerValue(dto4
                    .getImpawnAssurerValue());// 抵押+保证
                // 金额
              }
            }
            // 合计
            // 户数
            int totalCount = new Integer(assureModeDTO1.getPledgeCount())
                .intValue()
                + new Integer(assureModeDTO1.getImpawnCount()).intValue()
                + new Integer(assureModeDTO1.getPledgeAssurerCount())
                    .intValue()
                + new Integer(assureModeDTO1.getImpawnAssurerCount())
                    .intValue();
            // 金额
            BigDecimal totalValue = new BigDecimal(assureModeDTO1
                .getPledgeValue()).add(new BigDecimal(assureModeDTO1
                .getImpawnValue()).add(new BigDecimal(assureModeDTO1
                .getPledgeAssurerValue()).add(new BigDecimal(assureModeDTO1
                .getImpawnAssurerValue()))));
            assureModeDTO1.setTotalCount(totalCount + "");
            assureModeDTO1.setTotalValue(totalValue.toString());
            assureModeDTO1.setDeveloperName("");
            assureModeDTO1.setFloorNum("");
            assureModeDTO1.setAssistantOrgName("");
            returnList.add(assureModeDTO1);
          }
          // end

        } else if (mode.equals("3")) {
          if (developerId != null && !developerId.equals("")) {
            developerList.add(developerId);
          } else {
            // 获取全部开发商
            List developerDTOList = developerDAO
                .queryAllDevelopIdAndDevelopName();
            DeveloperDTO developerDTO = null;
            Iterator developerIter = developerDTOList.iterator();
            while (developerIter.hasNext()) {
              developerDTO = (DeveloperDTO) developerIter.next();
              developerList.add(developerDTO.getDeveloperid());
            }
          }
          // begin
          for (int i = 0; i < developerList.size(); i++) {
            developerId = (String) developerList.get(i);
            // 通过开发商id获得开发商名称
            String developerNameTable = developerDAO
                .queryDeveloperNameByDeveloperId(developerId);
            AssureModeDTO assureModeDTO1 = new AssureModeDTO();
            assureModeDTO1.setDeveloperName(developerNameTable);
            list1 = pledgeContractDAO.queryAssureModeListByCriterionsPledge(
                officeCode, loanBankId, developerId, floorNum, assistantOrgId,
                securityInfo);
            if (!list1.isEmpty()) {
              for (int j = 0; j < list1.size(); j++) {
                AssureModeDTO dto1 = (AssureModeDTO) list1.get(j);
                assureModeDTO1.setPledgeCount(dto1.getPledgeCount());// 抵押户数
                assureModeDTO1.setPledgeValue(dto1.getPledgeValue());// 抵押金额
              }
            }
            list2 = pledgeContractDAO.queryAssureModeListByCriterionsImpawn(
                officeCode, loanBankId, developerId, floorNum, assistantOrgId,
                securityInfo);
            if (!list2.isEmpty()) {
              for (int j = 0; j < list2.size(); j++) {
                AssureModeDTO dto2 = (AssureModeDTO) list2.get(j);
                assureModeDTO1.setImpawnCount(dto2.getImpawnCount());// 质押户数
                assureModeDTO1.setImpawnValue(dto2.getImpawnValue());// 质押金额
              }
            }
            list3 = pledgeContractDAO
                .queryAssureModeListByCriterionsPledgeAssurer(officeCode,
                    loanBankId, developerId, floorNum, assistantOrgId,
                    securityInfo);
            if (!list3.isEmpty()) {
              for (int j = 0; j < list3.size(); j++) {
                AssureModeDTO dto3 = (AssureModeDTO) list3.get(j);
                assureModeDTO1.setPledgeAssurerCount(dto3
                    .getPledgeAssurerCount());// 抵押+保证
                // 户数
                assureModeDTO1.setPledgeAssurerValue(dto3
                    .getPledgeAssurerValue());// 抵押+保证
                // 金额
              }
            }
            list4 = pledgeContractDAO
                .queryAssureModeListByCriterionsImpawnAssurer(officeCode,
                    loanBankId, developerId, floorNum, assistantOrgId,
                    securityInfo);
            if (!list4.isEmpty()) {
              for (int j = 0; j < list4.size(); j++) {
                AssureModeDTO dto4 = (AssureModeDTO) list4.get(j);
                assureModeDTO1.setImpawnAssurerCount(dto4
                    .getImpawnAssurerCount());// 抵押+保证
                // 户数
                assureModeDTO1.setImpawnAssurerValue(dto4
                    .getImpawnAssurerValue());// 抵押+保证
                // 金额
              }
            }
            // 合计
            // 户数
            int totalCount = new Integer(assureModeDTO1.getPledgeCount())
                .intValue()
                + new Integer(assureModeDTO1.getImpawnCount()).intValue()
                + new Integer(assureModeDTO1.getPledgeAssurerCount())
                    .intValue()
                + new Integer(assureModeDTO1.getImpawnAssurerCount())
                    .intValue();
            // 金额
            BigDecimal totalValue = new BigDecimal(assureModeDTO1
                .getPledgeValue()).add(new BigDecimal(assureModeDTO1
                .getImpawnValue()).add(new BigDecimal(assureModeDTO1
                .getPledgeAssurerValue()).add(new BigDecimal(assureModeDTO1
                .getImpawnAssurerValue()))));
            assureModeDTO1.setTotalCount(totalCount + "");
            assureModeDTO1.setTotalValue(totalValue.toString());
            assureModeDTO1.setOffice("");
            assureModeDTO1.setLoanBankName("");
            assureModeDTO1.setFloorNum("");
            assureModeDTO1.setAssistantOrgName("");
            returnList.add(assureModeDTO1);
          }
          // end
        } else if (mode.equals("4")) {
          if (floorId != null && !floorId.equals("")) {
            floorNumList.add(floorId.trim());
          } else {
            // 获取全部楼盘
            List floorDTOList = developProjectDAO.queryAllFloorIdAndFloorName();
            FloorDTO floorDTO = null;
            Iterator floorIter = floorDTOList.iterator();
            while (floorIter.hasNext()) {
              floorDTO = (FloorDTO) floorIter.next();
              floorNumList.add(floorDTO.getFloorId());
            }
          }
          // begin
          for (int i = 0; i < floorNumList.size(); i++) {
            floorNum = (String) floorNumList.get(i);
            AssureModeDTO assureModeDTO1 = new AssureModeDTO();
            // 通过楼盘ID获得楼盘名称和开发商ID
            List floorName = developProjectDAO
                .queryFloorNameAndHeadIdByFloorId(floorNum);
            FloorDTO floorDTO = null;
            Iterator floorIter = floorName.iterator();
            while (floorIter.hasNext()) {
              floorDTO = (FloorDTO) floorIter.next();
              assureModeDTO1.setFloorNum(floorDTO.getFloorName());
              // 通过开发商id获得开发商名称
              String developerNameTable = developerDAO
                  .queryDeveloperNameByDeveloperId(floorDTO.getDeveloperId());
              assureModeDTO1.setDeveloperName(developerNameTable);
            }

            list1 = pledgeContractDAO.queryAssureModeListByCriterionsPledge(
                officeCode, loanBankId, developerId, floorNum, assistantOrgId,
                securityInfo);
            if (!list1.isEmpty()) {
              for (int j = 0; j < list1.size(); j++) {
                AssureModeDTO dto1 = (AssureModeDTO) list1.get(j);
                assureModeDTO1.setPledgeCount(dto1.getPledgeCount());// 抵押户数
                assureModeDTO1.setPledgeValue(dto1.getPledgeValue());// 抵押金额
              }
            }
            list2 = pledgeContractDAO.queryAssureModeListByCriterionsImpawn(
                officeCode, loanBankId, developerId, floorNum, assistantOrgId,
                securityInfo);
            if (!list2.isEmpty()) {
              for (int j = 0; j < list2.size(); j++) {
                AssureModeDTO dto2 = (AssureModeDTO) list2.get(j);
                assureModeDTO1.setImpawnCount(dto2.getImpawnCount());// 质押户数
                assureModeDTO1.setImpawnValue(dto2.getImpawnValue());// 质押金额
              }
            }
            list3 = pledgeContractDAO
                .queryAssureModeListByCriterionsPledgeAssurer(officeCode,
                    loanBankId, developerId, floorNum, assistantOrgId,
                    securityInfo);
            if (!list3.isEmpty()) {
              for (int j = 0; j < list3.size(); j++) {
                AssureModeDTO dto3 = (AssureModeDTO) list3.get(j);
                assureModeDTO1.setPledgeAssurerCount(dto3
                    .getPledgeAssurerCount());// 抵押+保证
                // 户数
                assureModeDTO1.setPledgeAssurerValue(dto3
                    .getPledgeAssurerValue());// 抵押+保证
                // 金额
              }
            }
            list4 = pledgeContractDAO
                .queryAssureModeListByCriterionsImpawnAssurer(officeCode,
                    loanBankId, developerId, floorNum, assistantOrgId,
                    securityInfo);
            if (!list4.isEmpty()) {
              for (int j = 0; j < list4.size(); j++) {
                AssureModeDTO dto4 = (AssureModeDTO) list4.get(j);
                assureModeDTO1.setImpawnAssurerCount(dto4
                    .getImpawnAssurerCount());// 抵押+保证
                // 户数
                assureModeDTO1.setImpawnAssurerValue(dto4
                    .getImpawnAssurerValue());// 抵押+保证
                // 金额
              }
            }
            // 合计
            // 户数
            int totalCount = new Integer(assureModeDTO1.getPledgeCount())
                .intValue()
                + new Integer(assureModeDTO1.getImpawnCount()).intValue()
                + new Integer(assureModeDTO1.getPledgeAssurerCount())
                    .intValue()
                + new Integer(assureModeDTO1.getImpawnAssurerCount())
                    .intValue();
            // 金额
            BigDecimal totalValue = new BigDecimal(assureModeDTO1
                .getPledgeValue()).add(new BigDecimal(assureModeDTO1
                .getImpawnValue()).add(new BigDecimal(assureModeDTO1
                .getPledgeAssurerValue()).add(new BigDecimal(assureModeDTO1
                .getImpawnAssurerValue()))));
            assureModeDTO1.setTotalCount(totalCount + "");
            assureModeDTO1.setTotalValue(totalValue.toString());
            assureModeDTO1.setOffice("");
            assureModeDTO1.setLoanBankName("");
            assureModeDTO1.setAssistantOrgName("");
            returnList.add(assureModeDTO1);
          }
          // end
        } else if (mode.equals("5")) {
          assistantOrgId = assistantOrgName;
          if (assistantOrgId.equals("all")) {
            // 获取全部担保公司
            List assistantOrgList = this.getAssistantOrgNameList();
            AssistantOrg assistantOrg = null;
            Iterator assistantOrgIter = assistantOrgList.iterator();
            while (assistantOrgIter.hasNext()) {
              assistantOrg = (AssistantOrg) assistantOrgIter.next();
              assistOrgList.add(assistantOrg.getAssistantOrgId().toString());
            }
          } else {
            assistOrgList.add(assistantOrgId);
          }
          // begin
          for (int i = 0; i < assistOrgList.size(); i++) {
            assistantOrgId = (String) assistOrgList.get(i);
            AssureModeDTO assureModeDTO1 = new AssureModeDTO();
            // 转换担保公司名称
            String assistantOrgNameTable = assistantOrgDAO
                .queryAssistantOrgNameByAssistantOrgId(assistantOrgId);
            assureModeDTO1.setAssistantOrgName(assistantOrgNameTable);
            list1 = pledgeContractDAO.queryAssureModeListByCriterionsPledge(
                officeCode, loanBankId, developerId, floorNum, assistantOrgId,
                securityInfo);
            if (!list1.isEmpty()) {
              for (int j = 0; j < list1.size(); j++) {
                AssureModeDTO dto1 = (AssureModeDTO) list1.get(j);
                assureModeDTO1.setPledgeCount(dto1.getPledgeCount());// 抵押户数
                assureModeDTO1.setPledgeValue(dto1.getPledgeValue());// 抵押金额
              }
            }
            list2 = pledgeContractDAO.queryAssureModeListByCriterionsImpawn(
                officeCode, loanBankId, developerId, floorNum, assistantOrgId,
                securityInfo);
            if (!list2.isEmpty()) {
              for (int j = 0; j < list2.size(); j++) {
                AssureModeDTO dto2 = (AssureModeDTO) list2.get(j);
                assureModeDTO1.setImpawnCount(dto2.getImpawnCount());// 质押户数
                assureModeDTO1.setImpawnValue(dto2.getImpawnValue());// 质押金额
              }
            }
            list3 = pledgeContractDAO
                .queryAssureModeListByCriterionsPledgeAssurer(officeCode,
                    loanBankId, developerId, floorNum, assistantOrgId,
                    securityInfo);
            if (!list3.isEmpty()) {
              for (int j = 0; j < list3.size(); j++) {
                AssureModeDTO dto3 = (AssureModeDTO) list3.get(j);
                assureModeDTO1.setPledgeAssurerCount(dto3
                    .getPledgeAssurerCount());// 抵押+保证
                // 户数
                assureModeDTO1.setPledgeAssurerValue(dto3
                    .getPledgeAssurerValue());// 抵押+保证
                // 金额
              }
            }
            list4 = pledgeContractDAO
                .queryAssureModeListByCriterionsImpawnAssurer(officeCode,
                    loanBankId, developerId, floorNum, assistantOrgId,
                    securityInfo);
            if (!list4.isEmpty()) {
              for (int j = 0; j < list4.size(); j++) {
                AssureModeDTO dto4 = (AssureModeDTO) list4.get(j);
                assureModeDTO1.setImpawnAssurerCount(dto4
                    .getImpawnAssurerCount());// 抵押+保证
                // 户数
                assureModeDTO1.setImpawnAssurerValue(dto4
                    .getImpawnAssurerValue());// 抵押+保证
                // 金额
              }
            }
            // 合计
            // 户数
            int totalCount = new Integer(assureModeDTO1.getPledgeCount())
                .intValue()
                + new Integer(assureModeDTO1.getImpawnCount()).intValue()
                + new Integer(assureModeDTO1.getPledgeAssurerCount())
                    .intValue()
                + new Integer(assureModeDTO1.getImpawnAssurerCount())
                    .intValue();
            // 金额
            BigDecimal totalValue = new BigDecimal(assureModeDTO1
                .getPledgeValue()).add(new BigDecimal(assureModeDTO1
                .getImpawnValue()).add(new BigDecimal(assureModeDTO1
                .getPledgeAssurerValue()).add(new BigDecimal(assureModeDTO1
                .getImpawnAssurerValue()))));
            assureModeDTO1.setTotalCount(totalCount + "");
            assureModeDTO1.setTotalValue(totalValue.toString());
            assureModeDTO1.setOffice("");
            assureModeDTO1.setLoanBankName("");
            assureModeDTO1.setDeveloperName("");
            assureModeDTO1.setFloorNum("");
            returnList.add(assureModeDTO1);
          }
          // end
        }
      }
      assureModeAF.setList(returnList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return assureModeAF;
  }

  /**
   * 获取担保公司信息，用于显示下拉框
   * 
   * @author 王野 2007-10-12
   */
  public List getAssistantOrgNameList() throws Exception {
    List list = null;
    try {
      list = assistantOrgDAO.queryAssistantOrgList();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 获得楼盘名称
   * 
   * @author 王野 2007-10-13
   */
  public FloorPOPNewAF findFloorInfoList(Pagination pagination) throws Exception,
      BusinessException {
    FloorPOPNewAF floorPOPNewAF = new FloorPOPNewAF();
    try {
      String floorName = (String) pagination.getQueryCriterions().get(
          "floorName");
      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();

      List list = developProjectDAO.findFloorInfoList(floorName, orderBy,
          orderother, start, pageSize);
      FloorDTO floorDTO = null;
      Object obj[] = null;
      // 暂时存放列表的DTO
      List temp_list = new ArrayList();
      Iterator iterate = list.iterator();
      // 将查询的结果封装到DTO中
      while (iterate.hasNext()) {
        obj = (Object[]) iterate.next();
        floorDTO = new FloorDTO();
        if (obj[0] != null)
          floorDTO.setFloorId(obj[0].toString());
        if (obj[1] != null)
          floorDTO.setDeveloperName(obj[1].toString());
        if (obj[2] != null)
          floorDTO.setFloorName(obj[2].toString());
        if (obj[3] != null)
          floorDTO.setFloorSt(BusiTools.getBusiValue(new Integer(obj[3]
              .toString()).intValue(), BusiConst.PLCOMMONSTATUS_1));// 枚举转换楼盘状态
        temp_list.add(floorDTO);
      }
      int count = developProjectDAO.findFloorInfoCount(floorName);
      pagination.setNrOfElements(count);
      floorPOPNewAF.setList(temp_list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return floorPOPNewAF;
  }
}
