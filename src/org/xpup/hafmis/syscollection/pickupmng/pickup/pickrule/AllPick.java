package org.xpup.hafmis.syscollection.pickupmng.pickup.pickrule;
import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;
/**
 * 李文浩..获得销户规则的一个类
 */
public class AllPick implements DrawRulesInterface {
  DrawRuleDAO drawRuleDAO = new DrawRuleDAO();
  /**
   * 李文浩　销户提取的最大金额..
   */
  public BigDecimal getMaxDarwMoney(int orgId,int empId,String reason){
    return drawRuleDAO.getDistroyPickMoney(orgId, empId, reason);
  }
  /**
   * 李文浩..获得是否能提取
   */
  public boolean isDraw(String reason, Serializable empid) {
    if(drawRuleDAO.findEmployeePickupCount(reason,empid)>=3)
      return false;
    return true;
  }
  public void setDrawRuleDAO(DrawRuleDAO drawRuleDAO) {
    this.drawRuleDAO = drawRuleDAO;
  }
  public DrawRuleDAO getDrawRuleDAO() {
    return drawRuleDAO;
  }
}
