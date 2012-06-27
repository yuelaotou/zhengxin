package org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.bsinterface.IPalindromeImpSwitchBS;

public class Test extends Action{
  
 

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    IPalindromeImpSwitchBS palindromeImpSwitchBS = (IPalindromeImpSwitchBS) BSUtils
    .getBusinessService("palindromeImpSwitchBS", this, mapping.getModuleConfig());
    List list = new ArrayList();
    List headliST = new ArrayList();
    List afterList = new ArrayList();
    Object[] newObj = new Object[10];
    Object[] newObj1 = new Object[10];
    newObj[0]="5";
    newObj[1]="2";
    newObj[2]="3";
    newObj[3]="4";
    newObj[4]="5";
    newObj[5]="6";
    newObj[6]="7";
    newObj[7]="8";
    newObj[8]="9";
    newObj[9]="10";
    newObj1[0]="5";
    newObj1[1]="2";
    newObj1[2]="3";
    newObj1[3]="4";
    newObj1[4]="5";
    newObj1[5]="6";
    newObj1[6]="7";
    newObj1[7]="8";
    newObj1[8]="9";
    newObj1[9]="10";
    list.add(newObj);
    list.add(newObj1);
    try{
    afterList = palindromeImpSwitchBS.switchImpList("1",headliST, list,"txt");
    Object[] newObjj = null;
    for(int i=0;i<afterList.size();i++){
      System.out.println("===================");
      newObjj = (Object[])afterList.get(i);
      if(newObjj[0]!=null){
        System.out.println("1="+newObjj[0]);
      }
      if(newObjj[1]!=null){
        System.out.println("2="+newObjj[1]);
      }
      if(newObjj[2]!=null){
        System.out.println("3="+newObjj[2]);
      }
      if(newObjj[3]!=null){
        System.out.println("4="+newObjj[3]);
      }
      if(newObjj[4]!=null){
        System.out.println("5="+newObjj[4]);
      }
      if(newObjj[5]!=null){
        System.out.println("6="+newObjj[5]);
      }
      if(newObjj[6]!=null){
        System.out.println("7="+newObjj[6]);
      }
      if(newObjj[7]!=null){
        System.out.println("8="+newObjj[7]);
      }
      System.out.println("===================");
//      if(newObjj[8]!=null){
//        System.out.println("9="+newObjj[8]);
//      }
//      if(newObjj[9]!=null){
//        System.out.println("10="+newObjj[9]);
//      }
    }
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
}
