package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.dto.InterestAccInfDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.bsinterface.IInterestBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.dto.InterestTaDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.form.InterestTaAF;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.dto.PrincipalTaDTO;

public class InterestBS implements IInterestBS {
  private CollBankDAO collBankDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public InterestTaAF queryYearAccList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    InterestTaAF af = new InterestTaAF();
    List list = new ArrayList();
    try {
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int count = 0;
      String startYear = (String) pagination.getQueryCriterions().get(
          "startYear");
      String endYear = (String) pagination.getQueryCriterions().get("endYear");
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      List allList = loanFlowHeadDAO.queryYearIntAccInfoList(startYear,
          endYear, loanBankId);
      BigDecimal totalInterest = new BigDecimal(0.00);// 累计借方金额
      BigDecimal totalOverdueInterest = new BigDecimal(0.00);// 累计贷方金额
      BigDecimal bgnInterest = null;// 期初借方金额
      BigDecimal bgnOverdueInterest = null;// 期初贷方金额
      BigDecimal valInterest = null;// pl500中利息差的钱
      BigDecimal valOverdueInterest = null;// pl500中逾期利息差的钱
      for (int i = 0; i < allList.size(); i++) {
        InterestAccInfDTO dto = (InterestAccInfDTO) allList.get(i);
        valInterest = loanFlowHeadDAO.queryPl500Interest(startYear, null, dto
            .getLoanBankId());
        valOverdueInterest = loanFlowHeadDAO.queryPl500Punish_interest(
            startYear, null, dto.getLoanBankId());
        bgnInterest = loanFlowHeadDAO.queryBgnInterest(dto.getLoanBankId(),
            dto.getYear()).add(valInterest);
        bgnOverdueInterest = loanFlowHeadDAO.queryBgnOverdueInterest(
            dto.getLoanBankId(), dto.getMonth()).add(valOverdueInterest);
        dto.setLoanBank(collBankDAO.getCollBankByCollBankid_(
            dto.getLoanBankId()).getCollBankName());
        totalInterest = bgnInterest.add(new BigDecimal(dto.getRealInterest()));
        totalOverdueInterest = bgnOverdueInterest.add(new BigDecimal(dto
            .getRealOverdueInterest()));
        dto.setBgnInterest(bgnInterest.toString());
        dto.setBgnOverdueInterest(bgnOverdueInterest.toString());
        dto.setEndInterest(totalInterest.toString());
        dto.setEndOverdueInterest(totalOverdueInterest.toString());
      }
      // 插入最后一条
      if (!allList.isEmpty()) {
        count = allList.size();
        if (start + pageSize > count) {
          list = allList.subList(start, count);
        } else {
          list = allList.subList(start, start + pageSize);
        }
      }
      af.setList(list);
      af.setPrintList(allList);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  }

  public InterestTaAF queryMonthAccList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    InterestTaAF af = new InterestTaAF();
    List list = new ArrayList();
    try {
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int count = 0;
      String year = (String) pagination.getQueryCriterions().get("year");
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      List allList = loanFlowHeadDAO.queryMonthIntAccInfoList(year, loanBankId);
      BigDecimal totalInterest = new BigDecimal(0.00);// 累计借方金额
      BigDecimal totalOverdueInterest = new BigDecimal(0.00);// 累计贷方金额
      BigDecimal bgnInterest = null;// 期初借方金额
      BigDecimal bgnOverdueInterest = null;// 期初贷方金额
      BigDecimal valInterest = null;// pl500中利息差的钱
      BigDecimal valOverdueInterest = null;// pl500中逾期利息差的钱
      for (int i = 0; i < allList.size(); i++) {
        InterestAccInfDTO dto = (InterestAccInfDTO) allList.get(i);
        valInterest = loanFlowHeadDAO.queryPl500Interest(year, null, dto
            .getLoanBankId());
        valOverdueInterest = loanFlowHeadDAO.queryPl500Punish_interest(
            year, null, dto.getLoanBankId());
        bgnInterest = loanFlowHeadDAO.queryBgnInterest(dto.getLoanBankId(),
            dto.getMonth()).add(valInterest);
        bgnOverdueInterest = loanFlowHeadDAO.queryBgnOverdueInterest(
            dto.getLoanBankId(), dto.getMonth()).add(valOverdueInterest);
        dto.setLoanBank(collBankDAO.getCollBankByCollBankid_(
            dto.getLoanBankId()).getCollBankName());
        totalInterest = bgnInterest.add(new BigDecimal(dto.getRealInterest()));
        totalOverdueInterest = bgnOverdueInterest.add(new BigDecimal(dto
            .getRealOverdueInterest()));
        dto.setBgnInterest(bgnInterest.toString());
        dto.setBgnOverdueInterest(bgnOverdueInterest.toString());
        dto.setEndInterest(totalInterest.toString());
        dto.setEndOverdueInterest(totalOverdueInterest.toString());
      }
      // 插入最后一条
      if (!allList.isEmpty()) {
        count = allList.size();
        if (start + pageSize > count) {
          list = allList.subList(start, count);
        } else {
          list = allList.subList(start, start + pageSize);
        }
      }
      af.setList(list);
      af.setPrintList(allList);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  }

  public InterestTaAF queryDayAccList(Pagination pagination, SecurityInfo securityInfo) throws BusinessException, Exception {
    InterestTaAF af = new InterestTaAF();
    List list = new ArrayList();
    try {
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int count = 0;
      String year = (String) pagination.getQueryCriterions().get(
          "year");
      String month = (String) pagination.getQueryCriterions().get(
        "month");
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      String yearMonth = year + (month.length() < 2 ? "0" + month : month);
      List allList = loanFlowHeadDAO.queryDayIntAccInfoList(yearMonth, loanBankId);
      BigDecimal totalInterest = new BigDecimal(0.00);// 累计借方金额
      BigDecimal totalOverdueInterest = new BigDecimal(0.00);// 累计贷方金额
      BigDecimal bgnInterest = null;// 期初借方金额
      BigDecimal bgnOverdueInterest = null;// 期初贷方金额
      BigDecimal valInterest = null;// pl500中利息差的钱
      BigDecimal valOverdueInterest = null;// pl500中逾期利息差的钱
      
      BigDecimal interestSum = new BigDecimal(0.00);// 合计借方金额
      BigDecimal overdueInterestSum = new BigDecimal(0.00);// 合计贷方金额
      for (int i = 0; i < allList.size(); i++) {
        if(i == 0) {
          valInterest = loanFlowHeadDAO.queryPl500Interest(yearMonth, null, loanBankId);
          valOverdueInterest = loanFlowHeadDAO.queryPl500Punish_interest(
              yearMonth, null, loanBankId);
          bgnInterest = loanFlowHeadDAO.queryBgnInterest(loanBankId,
              yearMonth).add(valInterest);
          bgnOverdueInterest = loanFlowHeadDAO.queryBgnOverdueInterest(
              loanBankId, yearMonth).add(valOverdueInterest);
          
        } else {
          bgnInterest = totalInterest;
          bgnOverdueInterest = totalOverdueInterest;
        }
        InterestAccInfDTO dto = (InterestAccInfDTO) allList.get(i);
        dto.setLoanBank(collBankDAO.getCollBankByCollBankid_(
            dto.getLoanBankId()).getCollBankName());
        totalInterest = bgnInterest.add(new BigDecimal(dto.getRealInterest()));
        totalOverdueInterest = bgnOverdueInterest.add(new BigDecimal(dto
            .getRealOverdueInterest()));
        dto.setBgnInterest(bgnInterest.toString());
        dto.setBgnOverdueInterest(bgnOverdueInterest.toString());
        dto.setEndInterest(totalInterest.toString());
        dto.setEndOverdueInterest(totalOverdueInterest.toString());
        interestSum = interestSum.add(new BigDecimal(dto.getRealInterest()));
        overdueInterestSum = overdueInterestSum.add(new BigDecimal(dto.getRealOverdueInterest()));
      }
      if(!allList.isEmpty()) {
        InterestAccInfDTO dto = new InterestAccInfDTO();
        dto.setDate("总合计");
        dto.setRealInterest(interestSum.toString());
        dto.setRealOverdueInterest(overdueInterestSum.toString());
        allList.add(dto);
      }
      // 插入最后一条
      if (!allList.isEmpty()) {
        count = allList.size();
        if (start + pageSize > count) {
          list = allList.subList(start, count);
        } else {
          list = allList.subList(start, start + pageSize);
        }
      }
      af.setList(list);
      af.setPrintList(allList);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  }
}
