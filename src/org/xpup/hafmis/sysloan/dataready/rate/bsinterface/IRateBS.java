package org.xpup.hafmis.sysloan.dataready.rate.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateNewAF;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateShowAF;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateUseAF;

public interface IRateBS {
  //利率维护显示页面
  public RateShowAF findRateList(Pagination pagination)throws BusinessException;
  //利率维护添加方法
  public void addRateInfo(RateNewAF rateNewAF,SecurityInfo securityInfo)throws BusinessException;
  //利率维护查找修改的记录
  public RateNewAF findRateInfo(String rateId)throws BusinessException;
  //利率维护修改方法
  public void updateRateInfo(RateNewAF rateNewAF,SecurityInfo securityInfo)throws BusinessException;
  //利率启用
  public void useRateInfo_sy(RateUseAF rateUseAF,SecurityInfo securityInfo)throws BusinessException;
  //利率全部启用
  public void useRateAll_sy(String appMode, SecurityInfo securityInfo)throws BusinessException;
  //检验利率启用是否满足条件
  public String checkUseInfo_sy(String rateId)throws BusinessException;
}
