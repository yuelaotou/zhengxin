package org.xpup.hafmis.syscollection.peoplebank.param.bsinterface;



import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.peoplebank.param.dto.ParamDTO;


public interface IParamBS {
  public ParamDTO findParamInfo() throws Exception;
  public void saveParamInfo(ParamDTO paramDTO,SecurityInfo securityInfo) throws Exception;
 
}
