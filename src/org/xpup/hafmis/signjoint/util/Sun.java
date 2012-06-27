package org.xpup.hafmis.signjoint.util;

import java.io.*;

public class Sun{
  int k=0;
  
  public Sun(){
    BufferedReader stdin =new BufferedReader(new InputStreamReader(System.in));
    try {
      String re=stdin.readLine();
      while(true){
        if(re.equals("exit")){
          break;
        }else{
          int n=Integer.parseInt(re);
          towers(n, "A", "B", "C");
          System.out.println("总共需要 " + k + " 次挪动");
          k=0;
          System.out.println("请输入数字：");
          System.out.println();
          stdin =new BufferedReader(new InputStreamReader(System.in));
          re=stdin.readLine();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void move_disk(String src, String dst){
    System.out.println(src+" ====> "+dst);
    k++;
  }
  public void towers(int n, String src, String mid, String dst){
    if (n==1){
      move_disk(src,dst);
      return;
    }
    towers(n-1,src,dst,mid);
    move_disk(src,dst);
    towers(n-1,mid,src,dst);
  }
  public static void main (String []args){
    Sun sun = new Sun();
  }
}