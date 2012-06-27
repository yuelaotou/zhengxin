/**
 * Copy Right Information   : Goldsoft 
 * Project                  : PreLoanRefrBS
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2008-05-19
 **/
package org.xpup.hafmis.sysloan.loanapply.preloanrefr.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.dao.LoanRateDAO;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.bsinterface.IPreLoanRefrBS;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.dto.PreLoanRefrDTO;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.form.PreLoanRefrShowAF;

public class PreLoanRefrBS implements IPreLoanRefrBS {

  LoanRateDAO loanRateDAO = null;

  public void setLoanRateDAO(LoanRateDAO loanRateDAO) {
    this.loanRateDAO = loanRateDAO;
  }

  /**
   * Description 贷前咨询
   * 
   * @author wangy 2008-05-19
   * @param 贷前咨询信息列表
   * @param 由PreLoanRefrShowAC调用
   * @return LoanCheckShowAF
   * @throws Exception, BusinessException
   */
  public PreLoanRefrShowAF queryPreLoanRefrListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String timelimit = null;
    String loanMoney = (String) pagination.getQueryCriterions()
        .get("loanMoney");// 贷款金额:万元
    BigDecimal loanmoney = new BigDecimal(loanMoney).multiply(new BigDecimal(
        10000));

    // 月还本息的值=（剩余本金-应还本金合-提前还款金额）*（1+月利率）新剩余期限*月利率/(1+月利率)新剩余期限-1
    BigDecimal corpusInterest = new BigDecimal(0.00);// 月还款额(月还本息)
    BigDecimal loanmoneyTotal = new BigDecimal(0.00);// 还款总额
    BigDecimal interestTotal = new BigDecimal(0.00);// 利息总额

    BigDecimal loanRateFive = new BigDecimal(0.00);// 贷款利率（月利率）
    BigDecimal loanRateFiveUp = new BigDecimal(0.00);// 贷款利率（月利率）
    BigDecimal rate = new BigDecimal(0.00);// 年利率
    String officeCode = securityInfo.getOfficeDto().getOfficeCode();// 办事处
    // 1-5年利率 取PL001中loan_rate_type=0的最新的利率
    loanRateFive = loanRateDAO.findMontRate(officeCode, String
        .valueOf(BusiConst.PLLOANTYPE_FIVE));
    // 5年以上利率 取PL001中loan_rate_type=1的最新利率
    loanRateFiveUp = loanRateDAO.findMontRate(officeCode, String
        .valueOf(BusiConst.PLLOANTYPE_FIVEUP));
    List templist = new ArrayList();
    for (int i = 1; i <= 30; i++) {
      timelimit = new Integer(i * 12).toString();// 贷款期限 = 贷款期限（年） * 12
      if (i <= 5) {
        corpusInterest = CorpusinterestBS.getCorpusInterest(loanmoney,
            loanRateFive, timelimit);
        if(loanRateFive!=null)//wudi
        {
          rate = loanRateFive.multiply(new BigDecimal(12));// 求出最新的年利率
        }
        
      } else {
        corpusInterest = CorpusinterestBS.getCorpusInterest(loanmoney,
            loanRateFiveUp, timelimit);
        if(loanRateFive!=null)
        {
          rate = loanRateFiveUp.multiply(new BigDecimal(12));// 求出最新的年利率
        }
      
      }
      loanmoneyTotal = corpusInterest.multiply(new BigDecimal(timelimit));// 还款总额=月还款额*贷款期限
      interestTotal = loanmoneyTotal.subtract(loanmoney);// 利息总额 = 还款总额-贷款金额
      PreLoanRefrDTO preLoanRefrDTO = new PreLoanRefrDTO();
      preLoanRefrDTO.setYearlimit(new Integer(i));
      preLoanRefrDTO.setCorpusInterest(corpusInterest);
      preLoanRefrDTO.setLoanmoneyTotal(loanmoneyTotal);
      preLoanRefrDTO.setInterestTotal(interestTotal);
      preLoanRefrDTO.setRate(rate.multiply(new BigDecimal(100.00)).setScale(2,
          BigDecimal.ROUND_HALF_UP).toString()
          + "%");// 年利率
      templist.add(preLoanRefrDTO);
    }
    PreLoanRefrShowAF preLoanRefrShowAF = new PreLoanRefrShowAF();
    preLoanRefrShowAF.setLoanMoney(loanMoney);
    preLoanRefrShowAF.setPrintMoney(loanmoney.divide(new BigDecimal(1), 2,
        BigDecimal.ROUND_HALF_UP)
        + "元");
    pagination.setNrOfElements(30);
    preLoanRefrShowAF.setList(templist);
    preLoanRefrShowAF.setCount(new Integer(30));
    return preLoanRefrShowAF;
  }
}
