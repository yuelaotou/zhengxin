package org.xpup.hafmis.syscollection.common.biz.identitycard;

import org.xpup.common.exception.BusinessException;
/**
 *  李文浩..java验证身份证的号码.. 
 *  return Objectr[] [0]为生日[1]为性别
 */
public class IdentityCardCheck {
  public static Object[] identityCardCheck(String card)throws BusinessException{
    Object obj[] = new Object[2];
    String birthday = "";
    String month = "";
    String day = "";
    String sex="";
    try{
    if(card.length()!=15 && card.length()!=18){
      throw new BusinessException("身份证位数必须是15或18位:"+card.length()+",\ncard:"+card);
    }
    if(card.length() == 15){
      month = card.substring(8,10);
      day = card.substring(10,12);
      sex = card.substring(14,15);
      if(Integer.parseInt(month)>12)
        throw new BusinessException("身份证的月份必须在1-12之间");
      if(Integer.parseInt(day)>31)
        throw new BusinessException("身份证的日期必须在1-31之间");
      if(Integer.parseInt(sex)!=1 && Integer.parseInt(sex)!=2)
        throw new BusinessException("15位身份证的性别出现错误");
      birthday = "19"+card.substring(6,8)+month+day;
      obj[0] = birthday;
      obj[1] = sex;
    }
    if(card.length() ==18){
      month = card.substring(10,12);
      day = card.substring(12,14);
      sex = card.substring(16,17);
      if(Integer.parseInt(month)>12)
        throw new BusinessException("身份证的月份必须在1-12之间");
      if(Integer.parseInt(day)>31)
        throw new BusinessException("身份证的日期必须在1-31之间");
      if(Integer.parseInt(sex)!=1 && Integer.parseInt(sex)!=2)
        throw new BusinessException("18位身份证的性别出现错误");
      birthday = card.substring(6,10)+month+day;
      obj[0] = birthday;
      obj[1] = sex;
    }
    }catch(BusinessException b){
       throw b;
    }
    return obj;
  }
}
