package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.form.PastyearscontrasAF;

public interface IPastyearscontrastBS {
  //查询
  public PastyearscontrasAF queryList(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
  //根据办事处下拉框查询归集银行,所在地区下拉框
  public PastyearscontrasAF fingTree(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
  //根据办事处编号查名称
  public String findOfficeName(String officeId)throws Exception,BusinessException;
  //根据银行编号查询名称
  public String findBankName(String bankId)throws Exception,BusinessException;
}
