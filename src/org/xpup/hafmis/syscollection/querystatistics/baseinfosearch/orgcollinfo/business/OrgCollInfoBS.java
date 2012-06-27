package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.bsinterface.IOrgCollInfoBS;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollInfoFindDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollInfoFindResultDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollinfoSumDTO;

public class OrgCollInfoBS implements IOrgCollInfoBS {
  
  protected OrgDAO orgDAO = null;
  
  protected CollBankDAO collBankDAO = null;
  
  protected OrganizationUnitDAO organizationUnitDAO = null;

  /**
   *  根据插叙条件统计查询单位归集信息
   */
  public Object[] findOrgCollInfo(Pagination pagination) throws Exception {
    // TODO Auto-generated method stub
    Object[] resultList = new Object[3];
    Object[] resultSum = new Object[2];
    OrgCollinfoSumDTO orgCollinfoSumDTO = new OrgCollinfoSumDTO();
    List orgCollInfoFindResultDTOs = new ArrayList();
    List pirntList = new ArrayList();
    List printList_temp = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    OrgCollInfoFindDTO dto = (OrgCollInfoFindDTO) pagination
        .getQueryCriterions().get("searchDTO");
    SecurityInfo securityInfo = (SecurityInfo) pagination.getQueryCriterions()
        .get("securityInfo");
    // 如果条件为空时不进行查询。
    if (dto != null) {
      // 查询将在列表中显示的统计内容
      List list = orgDAO.queryStatisticOrgCollInfo(dto, orderBy, order, start,
          pageSize, securityInfo);
      // 转换OrgCollInfoFindResultDTO中的属性，转换内容包括:中文的转换与小数格式的转换
      for (int i = 0; i < list.size(); i++) {
        OrgCollInfoFindResultDTO orgCollInfoFindResultDTO = (OrgCollInfoFindResultDTO) list
            .get(i);
        orgCollInfoFindResultDTO.setEmpPay(orgCollInfoFindResultDTO.getEmpPay()
            .setScale(2));
        orgCollInfoFindResultDTO.setOrgPay(orgCollInfoFindResultDTO.getOrgPay()
            .setScale(2));
        orgCollInfoFindResultDTO.setPaySum(orgCollInfoFindResultDTO.getPaySum()
            .setScale(2, BigDecimal.ROUND_HALF_UP));
        orgCollInfoFindResultDTO.setSalarySum(orgCollInfoFindResultDTO
            .getSalarySum().setScale(2));
        orgCollInfoFindResultDTO.setBalance(orgCollInfoFindResultDTO
            .getBalance().setScale(2));
        orgCollInfoFindResultDTO.setOverPay(orgCollInfoFindResultDTO
            .getOverPay().setScale(2));
        orgCollInfoFindResultDTO.setPaybalance(orgCollInfoFindResultDTO
            .getPaybalance().setScale(2, BigDecimal.ROUND_HALF_UP));
        // 归集银行
        CollBank collBankdto = collBankDAO
            .getCollBankByCollBankid(orgCollInfoFindResultDTO
                .getCollectionBankId());
        orgCollInfoFindResultDTO.setCollectionBankId(collBankdto
            .getCollBankName());
        // 办事处
        OrganizationUnit organizationUnit = organizationUnitDAO
            .queryOrganizationUnitListByCriterions(orgCollInfoFindResultDTO
                .getOfficecode());
        orgCollInfoFindResultDTO.setOfficecode(organizationUnit.getName());
        // 转换单位性质
        orgCollInfoFindResultDTO.setCharacter(BusiTools.getBusiValue(Integer
            .parseInt(orgCollInfoFindResultDTO.getCharacter()),
            BusiConst.NATUREOFUNITS));

        orgCollInfoFindResultDTOs.add(orgCollInfoFindResultDTO);
      }
      /*
       * 求出合计与count，resultSum[0]是合计的List，resultSum[1]count
       */
      try {
        resultSum = orgDAO.queryStatisticCountOrgCollInfo(dto, securityInfo);
      } catch (RuntimeException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      // 求出count
      pirntList = (List) resultSum[0];
      for (int i = 0; i < pirntList.size(); i++) {
        OrgCollinfoSumDTO orgCollinfoSumDTO1 = (OrgCollinfoSumDTO) pirntList
            .get(i);
        orgCollinfoSumDTO1
            .setEmpPay(orgCollinfoSumDTO1.getEmpPay().setScale(2));
        orgCollinfoSumDTO1
            .setOrgPay(orgCollinfoSumDTO1.getOrgPay().setScale(2));
        orgCollinfoSumDTO1.setPaySum(orgCollinfoSumDTO1.getPaySum().setScale(2,
            BigDecimal.ROUND_HALF_UP));
        orgCollinfoSumDTO1.setSalarySum(orgCollinfoSumDTO1.getSalarySum()
            .setScale(2));
        orgCollinfoSumDTO1.setBalance(orgCollinfoSumDTO1.getBalance().setScale(
            2));
        orgCollinfoSumDTO1.setOverPay(orgCollinfoSumDTO1.getOverPay().setScale(
            2));
        orgCollinfoSumDTO1.setPaybalance(orgCollinfoSumDTO1.getPaybalance()
            .setScale(2, BigDecimal.ROUND_HALF_UP));
        // 归集银行
        CollBank collBankdto = collBankDAO
            .getCollBankByCollBankid(orgCollinfoSumDTO1.getCollectionBankId());
        orgCollinfoSumDTO1.setCollectionBankId(collBankdto.getCollBankName());
        // 办事处
        OrganizationUnit organizationUnit = organizationUnitDAO
            .queryOrganizationUnitListByCriterions(orgCollinfoSumDTO1
                .getOfficecode());
        orgCollinfoSumDTO1.setOfficecode(organizationUnit.getName());
        // 转换单位性质
        orgCollinfoSumDTO1.setCharacter(BusiTools.getBusiValue(Integer
            .parseInt(orgCollinfoSumDTO1.getCharacter()),
            BusiConst.NATUREOFUNITS));
        printList_temp.add(orgCollinfoSumDTO1);
      }
      pagination.setNrOfElements(new Integer((String) resultSum[1]).intValue());
      // 计算合计
      double empPay = 0.00;
      double orgPay = 0.00;
      int empSum = 0;
      double overPay = 0.00;
      double payBalance = 0.00;
      double paySum = 0.00;
      double salarySum = 0.00;
      double balance = 0.00;
      int personCount = 0;
      List sumList = (List) resultSum[0];
      for (int i = 0; i < sumList.size(); i++) {
        OrgCollinfoSumDTO temp_DTO = (OrgCollinfoSumDTO) sumList.get(i);
        empPay += temp_DTO.getEmpPay().doubleValue();
        orgPay += temp_DTO.getOrgPay().doubleValue();
        empSum += temp_DTO.getEmpSum().intValue();
        overPay += temp_DTO.getOverPay().doubleValue();
        payBalance += temp_DTO.getPaybalance().doubleValue();
        paySum += temp_DTO.getPaySum().doubleValue();
        salarySum += temp_DTO.getSalarySum().doubleValue();
        balance += temp_DTO.getBalance().doubleValue();
        personCount += temp_DTO.getPersonCount().intValue();
      }
      orgCollinfoSumDTO.setPersonCount(new Integer(personCount));
      orgCollinfoSumDTO.setEmpPay(new BigDecimal(empPay).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setOrgPay(new BigDecimal(orgPay).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setOverPay(new BigDecimal(overPay).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setPaybalance(new BigDecimal(payBalance).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setPaySum(new BigDecimal(paySum).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setSalarySum(new BigDecimal(salarySum).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setBalance(new BigDecimal(balance).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      orgCollinfoSumDTO.setEmpSum(new Integer(empSum));
    }
    // 将列表中的信息放入Object[]中
    resultList[0] = orgCollInfoFindResultDTOs;
    resultList[1] = orgCollinfoSumDTO;
    resultList[2] = printList_temp;

    return resultList;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

}
