package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseHeadExportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseListExportDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcExportDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcShowListDTO;



/*单位编号、单位名称、调整年月、职工编号、职工姓名、证件号码、调整前工资基数、调整后工资基数（空）、*/
/**
 * @author ly TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class cashDayClear_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
//    ArrayList chgslarybaseHeadlist = new ArrayList();
    ArrayList chgslarybaselist = new ArrayList();
//    ChgslarybaseHeadExportDTO chgslarybaseHeadExportDto = new ChgslarybaseHeadExportDTO();
    CashDayClearTcExportDTO cashDayClearTcExportDTO = new CashDayClearTcExportDTO();
    
    
//    chgslarybaseHeadExportDto.setOrgId("单位编号");
//    chgslarybaseHeadExportDto.setOrgName("单位名称");
//    chgslarybaseHeadExportDto.setChgMonth("调整年月");
//    chgslarybaseHeadExportDto.setPayMode("导出说明:");
//    chgslarybaseHeadExportDto.setPayMode1("缴存方式,1:职工缴额;2:单一缴率");
    
//    chgslarybaseListExportDto.setEmpId("职工编号");
//    chgslarybaseListExportDto.setEmpName("职工姓名");
//    chgslarybaseListExportDto.setCardNum("证件号码");
//    chgslarybaseListExportDto.setOldSalaryBase("调整前工资基数");
//    chgslarybaseListExportDto.setSalaryBase("调整后工资基数");
    
    
    cashDayClearTcExportDTO.setCredenceDate("日期");
    cashDayClearTcExportDTO.setCredenceChaNum("凭证字号");
    cashDayClearTcExportDTO.setTemp_summary("摘要");
    cashDayClearTcExportDTO.setSubjectCode("科目");
    cashDayClearTcExportDTO.setSubjectName("科目名称");
    cashDayClearTcExportDTO.setDebit("贷方");
    cashDayClearTcExportDTO.setCredit("借方");
    cashDayClearTcExportDTO.setBalance("余额");
    cashDayClearTcExportDTO.setTemp_settType("结算方式");
    cashDayClearTcExportDTO.setSettNum("结算号");
    cashDayClearTcExportDTO.setDopsn("经办人");
    cashDayClearTcExportDTO.setMakebill("制单人");
    cashDayClearTcExportDTO.setCredenceSt("状态");
    chgslarybaselist.add(cashDayClearTcExportDTO);
//    chgslarybaseHeadlist.add(chgslarybaseHeadExportDto);
    
    try {
      
      
     

      for (int i = 0; i < explist.size(); i++) {
       
        CashDayClearTcExportDTO cashDayClearTcExportDTO1 = new CashDayClearTcExportDTO();
        
        
        CashDayClearTcShowListDTO cashDayClearTcExport = (CashDayClearTcShowListDTO) explist.get(i);
        
        
        cashDayClearTcExportDTO1.setCredenceDate(cashDayClearTcExport.getCredenceDate());
        cashDayClearTcExportDTO1.setCredenceChaNum(cashDayClearTcExport.getCredenceChaNum());
        cashDayClearTcExportDTO1.setTemp_summary(cashDayClearTcExport.getTemp_summary());
        cashDayClearTcExportDTO1.setSubjectCode(cashDayClearTcExport.getSubjectCode());
        cashDayClearTcExportDTO1.setSubjectName(cashDayClearTcExport.getSubjectName());
        cashDayClearTcExportDTO1.setDebit(cashDayClearTcExport.getDebit().toString());
        cashDayClearTcExportDTO1.setCredit(cashDayClearTcExport.getCredit().toString());
        cashDayClearTcExportDTO1.setBalance(cashDayClearTcExport.getBalance().toString());
        cashDayClearTcExportDTO1.setTemp_settType(cashDayClearTcExport.getTemp_settType());
        cashDayClearTcExportDTO1.setSettNum(cashDayClearTcExport.getSettNum());
        cashDayClearTcExportDTO1.setDopsn(cashDayClearTcExport.getDopsn());
        cashDayClearTcExportDTO1.setMakebill(cashDayClearTcExport.getMakebill());
        cashDayClearTcExportDTO1.setCredenceSt(cashDayClearTcExport.getCredenceSt());
   
        
        chgslarybaselist.add(cashDayClearTcExportDTO1);
        
       
      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
     
      addpayExportMap.put("CashDayClearTcExport", chgslarybaselist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
