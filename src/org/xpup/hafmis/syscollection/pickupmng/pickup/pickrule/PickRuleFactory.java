package org.xpup.hafmis.syscollection.pickupmng.pickup.pickrule;
public class PickRuleFactory {//提取规则的工厂类..根据原因而得到实际的类
    public static DrawRulesInterface getDrawRule(String pickType){
      if(pickType.equals("1"))
        return new SomePick();
      if(pickType.equals("2"))
        return new AllPick();
      return null;
    }
}
