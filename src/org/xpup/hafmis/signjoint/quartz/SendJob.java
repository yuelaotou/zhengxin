package org.xpup.hafmis.signjoint.quartz;

import java.util.Date;
import java.util.List;

import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;

import org.xpup.hafmis.signjoint.dto.HistoryDTO;
import org.xpup.hafmis.signjoint.dto.LogDTO;
import org.xpup.hafmis.signjoint.util.BatchSignTools;

public class SendJob {
  private boolean isRunning=false;
  private Object ibs=null;
  public Object getIbs() {
    return ibs;
  }
  public void setIbs(Object ibs) {
    this.ibs = ibs;
  }
  public void doSendJob(){
    if(!isRunning){
      isRunning=true;
      String mark="|";
      System.out.println(new Date()+" 联名卡批量签约任务开始执行！");
      ISignjointBS bs=(ISignjointBS)ibs;
      List list=bs.prepareSendFile();
      try{
       BatchSignTools.setRequestFile("gjjkqy_"+BatchSignTools.getTodayDateString()+".txt");
       BatchSignTools.superAdditiontoTodayFile(BatchSignTools.getRequestpath(),BatchSignTools.getRequestFile(),mark+Integer.toString(list.size())+mark);
       //序号|持卡人卡号|持卡人姓名|持卡人身份证号码|公积金帐户号|备注|
       int size=list.size();
       for(int i=0;i<size;i++){
         HistoryDTO dto=(HistoryDTO)list.get(i);
         BatchSignTools.superAdditiontoTodayFile(BatchSignTools.getRequestpath(),BatchSignTools.getRequestFile(),
         Integer.toString(i)+mark+dto.getBankcardid()+mark+dto.getEmpname()+mark+dto.getCardnum()+
         mark+dto.getEmpid()+mark+dto.getSign()+mark);
       }
       //记录文件生成记录
       LogDTO logdto=new LogDTO();
       logdto.setFile_name(BatchSignTools.getRequestFile());
       logdto.setOperation_type("0");
       bs.logFile(logdto);
      }
      catch(Exception e){
        e.printStackTrace();
      }
      
      BatchSignTools.setResponseFile("gjjkqyhp_"+BatchSignTools.getTodayDateString()+".txt");
      System.out.println(new Date()+" 联名卡批量签约任务结束执行！");
      isRunning=false;
    }
    else{
      System.out.println("前一个批量签约任务还在执行！");
    }
  }
}
