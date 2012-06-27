package org.xpup.hafmis.common.util;


public abstract class CardMunChange {
 
  public CardMunChange(){
    
  }
  public static final String get15Id(String id) {
    if(id.length()==15){
      return id;
    }
    String newid;
    newid = id;
    // System.out.println("newid.substring(6,8):" + newid.substring(6,8));
    if(newid.substring(6,8).equals("19")){
     newid = newid.substring(0, 6) + newid.substring(8, id.length() - 1);
     //System.out.println("get 15 id:" + newid);
   }
     return newid;
  }

  public static final String get18Id(String id) {
    if(id.length()==18){
      return id;
    }
    final int[] W = {
        7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
    final String[] A = {
        "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
    int i, j, s = 0;
    String newid;
    newid = id;
    newid = newid.substring(0, 6) + "19" + newid.substring(6, id.length());
    for (i = 0; i < newid.length(); i++) {

      j = Integer.parseInt(newid.substring(i, i + 1)) * W[i];
      s = s + j;
    }
    s = s % 11;
    newid = newid + A[s];
   // System.out.println("get18Id="+newid);
    return newid;

  }

  
}