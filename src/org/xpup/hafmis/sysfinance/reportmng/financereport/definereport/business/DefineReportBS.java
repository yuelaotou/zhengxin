package org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.business;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.ReportMngDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.ReportMng;
import org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.bsinterface.IDefineReportBS;
import org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.form.DefineReportAF;

public class DefineReportBS implements IDefineReportBS {

  private ReportMngDAO reportMngDAO = null;

  public void setReportMngDAO(ReportMngDAO reportMngDAO) {
    this.reportMngDAO = reportMngDAO;
  }

  /**
   * 查询报表信息
   */
  public DefineReportAF queryReportMessage(DefineReportAF defineReportAF,
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    try{
      String tablename=(String)pagination.getQueryCriterions().get("tablename");
      ReportMng reportMng=reportMngDAO.queryHeadReportMessageByTablename_WL(tablename,securityInfo);
      pagination.getQueryCriterions().put("col", reportMng.getColspan());
      pagination.getQueryCriterions().put("row", reportMng.getRowspan());
      defineReportAF.setCol(reportMng.getColspan());
      defineReportAF.setRow(reportMng.getRowspan());
      defineReportAF.setTablename(reportMng.getTailtableNameChinese());
      
      defineReportAF=reportMngDAO.queryReportMessageByTablename_WL(defineReportAF,reportMng,securityInfo);
      for(int i=1;i<(Integer.parseInt(reportMng.getColspan())+1);i++){
        for(int j=1;j<(Integer.parseInt(reportMng.getRowspan())+1);j++){
          if ((defineReportAF.getValue("v"+i+"_"+j+"")!= null) && (!defineReportAF.getValue("v"+i+"_"+j+"").toString().equals(""))) {
            if(defineReportAF.getValue("v"+i+"_"+j+"").toString().substring(0, 1).equals(BusiConst.REPORTLOGO_FORMULA)){
              String temp_str=defineReportAF.getValue("v"+i+"_"+j+"").toString().substring(1);
              temp_str=BusiTools.getBusiValue_WL(BusiConst.REPORTLOGO_FORMULA, BusiConst.REPORTLOGO)+BusiTools.getExpressName(temp_str);
              defineReportAF.setValue("v"+i+"_"+j+"",temp_str);
            }
          }
        }    
      }

    }catch(Exception e){
      e.printStackTrace();
    }
    return defineReportAF;
  }
  
  /**
   * 保存报表定义的信息
   */
  public void saveReportMessage(DefineReportAF defineReportAF,Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException {
   try{
     reportMngDAO.saveReportMessage_WL(defineReportAF,pagination,securityInfo);
     
   }catch(Exception e){
     e.printStackTrace();
   }
    
  }

}
