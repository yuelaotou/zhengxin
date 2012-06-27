package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.bsinterface;

import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.dto.AssistantorgQueryTbDTO;

public interface IAssistantOrgBS {

  public List findAssistantOrgList(Pagination pagination) throws Exception;

  public AssistantorgQueryTbDTO findAssistantOrgInfo(String id)
      throws Exception;

  public List queryAssistantOrgListCount_wsh(String assistantOrgName,
      String artfclprsn, String assistantType, String inArea) throws Exception;
  public String getUserRealName(String name) throws Exception;
}
