package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.dto.AssistantorgQueryTbDTO;

public class AssistantorgQueryTbAF extends ActionForm {

  private AssistantorgQueryTbDTO assistantorgQueryTbDTO = new AssistantorgQueryTbDTO();

  public AssistantorgQueryTbDTO getAssistantorgQueryTbDTO() {
    return assistantorgQueryTbDTO;
  }

  public void setAssistantorgQueryTbDTO(
      AssistantorgQueryTbDTO assistantorgQueryTbDTO) {
    this.assistantorgQueryTbDTO = assistantorgQueryTbDTO;
  }
}
