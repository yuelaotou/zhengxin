package org.xpup.hafmis.syscollection.pickupmng.pickup.pickrule;
import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.type.BigDecimalType;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.dto.PartPickupConditionDTO;
public class SomePick implements DrawRulesInterface{
  //部分提取的一个接口..
  private DrawRuleDAO drawRuleDAO = new DrawRuleDAO();
  private EmpDAO empDAO = new EmpDAO();
  /**
   * 获得最大金额
   */
  public BigDecimal getMaxDarwMoney(int orgId,int empId,String reason) {
    try{
      BigDecimal max = new BigDecimal(0.00);
      BigDecimal maxMoney = drawRuleDAO.getSomePickMoney(orgId, empId,reason);
      PartPickupConditionDTO partPickupConditionDTO=new PartPickupConditionDTO();
      partPickupConditionDTO = drawRuleDAO.queryPartPickupConditionInfo();
      BigDecimal mul =new BigDecimal(partPickupConditionDTO.getMultiple());
      Emp emp = empDAO.findEmpByOrdIdAndEmpId(new Integer(orgId), new Integer(empId));
      BigDecimal paymoney = emp.getPayOldYear();
      System.out.println("somepick.."+paymoney);
      if(paymoney==null||paymoney.toString().equals("0")){
        paymoney=emp.getEmpPay().add(emp.getOrgPay());
      }
      if(reason.equals("4")||reason.equals("5")){
        max=drawRuleDAO.getPerbalance(orgId, empId, reason);
      }
      else{
      max = maxMoney.subtract(paymoney.multiply(mul));
     if(max.floatValue()<0){
       max=new BigDecimal(0.00);
     }
        
    
      }
//      out.println("maxMoney:"+maxMoney);
      return max;
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
  /**
   *根据次数来判断是否能够取钱
   */
  public boolean isDraw(String reason, Serializable empid) {
    int count = 3; 
    if(drawRuleDAO.findEmployeePickupCount(reason,empid)>=3)
      return false;
    return true;
  }
  public void setDrawRuleDAO(DrawRuleDAO drawRuleDAO) {
    this.drawRuleDAO = drawRuleDAO;
  }
  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }
}
