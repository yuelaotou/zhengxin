package org.xpup.hafmis.sysloan.dataready.param.bsinterface;



import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.param.dto.AheadReturnParamDTO;
import org.xpup.hafmis.sysloan.dataready.param.dto.ParamDTO;

public interface IParamBS {
  public ParamDTO findParamInfo(String loanBankId,SecurityInfo securityInfo) throws Exception;
  public void saveParamInfo(ParamDTO paramDTO,SecurityInfo securityInfo) throws Exception;
  public AheadReturnParamDTO findAheadReturnParamInfo(String loanBankId) throws Exception;
  public void saveAheadReturnParamInfo(AheadReturnParamDTO aheadReturnParamDTO,SecurityInfo securityInfo) throws Exception;
  public String checkLoanFlow() throws Exception;
}
