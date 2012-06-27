package org.xpup.hafmis.syscollection.pickupmng.pickup.dto;
public class Convert {
  public static String getTwoDouble(String num){//转换小数的方法
    if(num.indexOf(".")!=-1){
      String str;
      str = num.substring(num.indexOf(".")+1, num.length());//获取小数点后面的位数
      if(str.length()>=2){
        str = str.substring(0,2);
      }
      if(str.length()==1){
        str = str.substring(0,1)+"0";
      }
      num = num.substring(0,num.indexOf("."))+"."+str;
    }
    return num;
  }
}
