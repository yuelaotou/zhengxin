package org.xpup.hafmis.sysloan.loanapply.personalloancalc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.loanapply.personalloancalc.bsinterface.IPersonalloancalcBS;
import org.xpup.hafmis.sysloan.loanapply.personalloancalc.dto.LoanbackInfoDTO;

public class PersonalloancalcBS extends CorpusinterestBS implements
    IPersonalloancalcBS {

  public List queryCorpusInterestList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    List list = new ArrayList();
    try {
      String loanTimeLimit = (String) pagination.getQueryCriterions().get(
          "loanTimeLimit");
      String loanMoney = (String) pagination.getQueryCriterions().get(
          "loanMoney");
      BigDecimal loanRate = new BigDecimal((String) pagination
          .getQueryCriterions().get("loanRate"));
      // 计算月还本息
      BigDecimal corpusInterest = getCorpusInterest(new BigDecimal(loanMoney),
          loanRate.divide(new BigDecimal(1200), 6, BigDecimal.ROUND_HALF_UP),
          loanTimeLimit);
      BigDecimal overplusLoanMoney = new BigDecimal(loanMoney);
      BigDecimal interest = null;
      BigDecimal corpus = null;
      BigDecimal sumInterest = new BigDecimal(0.00);
      for (int i = 1; i <= Integer.parseInt(loanTimeLimit); i++) {
        LoanbackInfoDTO dto = new LoanbackInfoDTO();
        dto.setLoanTimeLimit(i + "");
        interest = overplusLoanMoney.multiply(
            loanRate.divide(new BigDecimal(1200), 6, BigDecimal.ROUND_HALF_UP))
            .setScale(2, BigDecimal.ROUND_HALF_UP);
        sumInterest = sumInterest.add(interest);
        corpus = corpusInterest.subtract(interest);
        dto.setCorpus(corpus);
        dto.setInterest(interest);
        
        if (i == Integer.parseInt(loanTimeLimit)) {
          dto.setCorpus(overplusLoanMoney);
          dto.setCorpusInterest(overplusLoanMoney.add(interest));
          dto.setOverplusLoanMoney(new BigDecimal(0.00));
        }
        else {
          dto.setCorpus(corpus);
          dto.setCorpusInterest(corpusInterest);
          overplusLoanMoney = overplusLoanMoney.subtract(corpus);
          dto.setOverplusLoanMoney(overplusLoanMoney);
        }
        list.add(dto);
      }
      if(Integer.parseInt(loanTimeLimit) != 0) {
        LoanbackInfoDTO dto = new LoanbackInfoDTO();
        dto.setLoanTimeLimit("合计");
        dto.setCorpus(new BigDecimal(loanMoney));
        dto.setInterest(sumInterest);
        dto.setCorpusInterest(new BigDecimal(loanMoney).add(sumInterest));
        list.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}
