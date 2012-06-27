package org.xpup.hafmis.signjoint.quartz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.dto.LogDTO;
import org.xpup.hafmis.signjoint.dto.RecieveFileDTO;
import org.xpup.hafmis.signjoint.util.BatchSignTools;
import org.xpup.hafmis.signjoint.util.SignTools;

public class ReceiveJob {
  private boolean isRunning=false;
  private Object ibs=null;
  public Object getIbs() {
    return ibs;
  }
  public void setIbs(Object ibs) {
    this.ibs = ibs;
  }
  public void doReceiveJob() throws IOException{
    if(!isRunning){
      isRunning=true;
      System.out.println(new Date()+" 联名卡批量签约确认任务开始执行！");
      
      if(!BatchSignTools.isHaveResponseFile()){//如果出现故障
        System.out.println(new Date()+" 联名卡批量签约任务有故障！");
        System.out.println("请管理员检查：");
        System.out.println("1,与银行网络是否连通");
        System.out.println("2,服务器是否宕机");
        System.out.println("3,FTP服务是否开启");
        //String str=BatchSignTools.readResponseFile(BatchSignTools.getResponseFile());
        //BatchSignTools.setRequestFile("gjjkqy_"+BatchSignTools.getTodayDateString()+".txt");
        //BatchSignTools.superAdditiontoTodayFile(BatchSignTools.getRequestpath(),BatchSignTools.getRequestFile(), str);
        System.out.println(new Date()+" 联名卡批量签约确认任务结束执行！");
        isRunning=false;
        return ;
      }
      
      String content=BatchSignTools.readResponseFile(BatchSignTools.getResponseFile());
      ISignjointBS bs=(ISignjointBS)ibs;
      List data=SignTools.Compart(content);
      System.out.println(content);
      int size=data.size();
      List list=new ArrayList();
      try{
       for(int i=0;i<size;i=i+7){
        RecieveFileDTO dto=new RecieveFileDTO();
        //序号|持卡人卡号|持卡人姓名|持卡人身份证号码|公积金帐户号|备注|成功标志|
        dto.setId((String)data.get(i));
        dto.setBankcardid((String)data.get(i+1));
        dto.setName((String)data.get(i+2));
        dto.setCardnum((String)data.get(i+3));
        dto.setEmpid((String)data.get(i+4));
        dto.setSign((String)data.get(i+5));
        dto.setS_f((String)data.get(i+6));
        list.add(dto);
       }
       bs.prepareReceiveFile(list);
      }catch(Exception e){//如果格式不对就退出
        e.printStackTrace();
        System.out.println(new Date()+" 回文格式错误！");
        System.out.println(new Date()+" 联名卡批量签约确认任务结束执行！");
        isRunning=false;
        return;
      }
      
      //保存文件记录
      try{
        LogDTO logdto=new LogDTO();
        logdto.setFile_name(BatchSignTools.getResponseFile());
        logdto.setOperation_type("1");
        bs.logFile(logdto);
        bs.clearTemp();//清空缓存表
      }catch(Exception e){
        e.printStackTrace();
      }
      System.out.println(new Date()+" 联名卡批量签约确认任务结束执行！");
      isRunning=false;
    }
    else{
      System.out.println("前一个接收任务还在执行！");
    }
  }


}
