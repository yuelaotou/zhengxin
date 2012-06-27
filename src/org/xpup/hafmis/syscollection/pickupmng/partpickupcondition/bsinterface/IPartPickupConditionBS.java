package org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.bsinterface;

import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.dto.PartPickupConditionDTO;

public interface IPartPickupConditionBS {
  public PartPickupConditionDTO findPartPickupConditionInfo() throws Exception;
  public void savePartPickupConditionInfo(PartPickupConditionDTO partPickupConditionDTO) throws Exception;
}
