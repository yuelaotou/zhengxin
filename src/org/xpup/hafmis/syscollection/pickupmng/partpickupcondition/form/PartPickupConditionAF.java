package org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.dto.PartPickupConditionDTO;

public class PartPickupConditionAF extends ActionForm {
  private PartPickupConditionDTO partPickupConditionDTO=new PartPickupConditionDTO();

  public PartPickupConditionDTO getPartPickupConditionDTO() {
    return partPickupConditionDTO;
  }

  public void setPartPickupConditionDTO(
      PartPickupConditionDTO partPickupConditionDTO) {
    this.partPickupConditionDTO = partPickupConditionDTO;
  }

}
