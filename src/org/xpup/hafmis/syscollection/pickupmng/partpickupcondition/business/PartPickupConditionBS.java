package org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.business;

import org.xpup.hafmis.syscollection.common.dao.PartPickupConditionDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.PartPickupCondition;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.bsinterface.IPartPickupConditionBS;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.dto.PartPickupConditionDTO;


public class PartPickupConditionBS implements IPartPickupConditionBS{
  private PartPickupConditionDAO partPickupConditionDAO=null;
  /**
   * 部分提取前提录入
   * @author 郭婧平
   * 2007-12-6
   * 查aa401表内容
   */
  public PartPickupConditionDTO findPartPickupConditionInfo() throws Exception{
    PartPickupConditionDTO partPickupConditionDTO=null;
    try{
      partPickupConditionDTO=partPickupConditionDAO.queryPartPickupConditionInfo();
    }catch(Exception e){
      e.printStackTrace();
    }
    return partPickupConditionDTO;
  }
  /**
   * 部分提取前提录入
   * 确定按钮
   * @author 郭婧平
   * 2007-12-6
   */
  public void savePartPickupConditionInfo(PartPickupConditionDTO partPickupConditionDTO) throws Exception{
    try{
      partPickupConditionDAO.deletePartPickupCondition();
      PartPickupCondition partPickupCondition=new PartPickupCondition();
      partPickupCondition.setPickMoneyMax(partPickupConditionDTO.getPickMoneyMax());
      partPickupCondition.setPickTimeMax(partPickupConditionDTO.getPickTimeMax());
      partPickupCondition.setLeavingsBalance(partPickupConditionDTO.getLeavingsBalance());
      partPickupCondition.setMultiple(partPickupConditionDTO.getMultiple());
      partPickupConditionDAO.insert(partPickupCondition);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  public void setPartPickupConditionDAO(
      PartPickupConditionDAO partPickupConditionDAO) {
    this.partPickupConditionDAO = partPickupConditionDAO;
  }
}
