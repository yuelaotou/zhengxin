package org.xpup.hafmis.signjoint.quartz;

import java.util.Date;

import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.bussiness.SignjointBS;
public class SynJob {

  private boolean isRunning=false;
  private Object ibs=null;
  public Object getIbs() {
    return ibs;
  }
  public void setIbs(Object ibs) {
    this.ibs = ibs;
  }
  public void synchronizationDB(){
    if(!isRunning){
      isRunning=true;
      System.out.println(new Date()+"同步任务开始执行");
      ISignjointBS bs=(ISignjointBS)ibs;
      bs.execSynProcdure();
      System.out.println(new Date()+"同步任务执行结束");
      isRunning=false;
    }
    else{
      System.out.println("前一个任务还在执行！");
    }
  }

}
