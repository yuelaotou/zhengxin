package org.xpup.hafmis.syscollection.peoplebank.documentstop.bsinterface;

import java.util.List;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IDocumentstopBS {

  //插入数据
  public void insertBankInfo(List list, SecurityInfo securityInfo) throws Exception;
  
  //获得BANKINFO 记录条数
  public int countBankInfo() throws Exception;
  
  //查询BANKINFO 信息
  public List queryBankInfo() throws Exception;
  
  //修改BANKINFO 信息
  public void updateBankInfo(List list ,SecurityInfo securityInfo) throws Exception;
}
