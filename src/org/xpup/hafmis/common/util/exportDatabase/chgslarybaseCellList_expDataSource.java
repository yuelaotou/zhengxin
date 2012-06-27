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
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseCellListHeadExportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseCellListListExportDTO;

 


//吴洪涛2008616
/*  职工编号 ▲  职工姓名  证件号码  调整前工资基数  调整前单位缴额  调整前职工缴额  调整前缴额合计  调整后工资基数  调整后单位缴额  调整后职工缴额  调整后缴额合计 、*/

public class chgslarybaseCellList_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList chgslarybaseCellListHeadlist = new ArrayList();
    ArrayList chgslarybaseCellListList = new ArrayList();
    ChgslarybaseCellListHeadExportDTO chgslarybaseCellListHeadExportDTO = new ChgslarybaseCellListHeadExportDTO();
    ChgslarybaseCellListListExportDTO chgslarybaseCellListListExportDTO = new ChgslarybaseCellListListExportDTO();
    
    
    chgslarybaseCellListHeadExportDTO.setOrgId("单位编号");
    chgslarybaseCellListHeadExportDTO.setOrgName("单位名称");
    chgslarybaseCellListHeadExportDTO.setChgMonth("调整年月");
    
    chgslarybaseCellListListExportDTO.setEmpId("职工编号");
    chgslarybaseCellListListExportDTO.setEmpName("职工姓名");
    chgslarybaseCellListListExportDTO.setCardNum("证件号码");
 
    chgslarybaseCellListListExportDTO.setOldSalaryBase("调整前工资基数");
    chgslarybaseCellListListExportDTO.setOldOrgPay(" 调整前单位缴额");
    chgslarybaseCellListListExportDTO.setOldEmpPay("调整前单位缴额");

    chgslarybaseCellListListExportDTO.setOldEmpPay("调整前职工缴额");
    chgslarybaseCellListListExportDTO.setOldOrgPayEmpPaySum("调整前缴额合计");
    chgslarybaseCellListListExportDTO.setSalaryBase(" 调整后工资基数");

    chgslarybaseCellListListExportDTO.setOrgPay("调整后单位缴额");
    chgslarybaseCellListListExportDTO.setEmpPay("调整后职工缴额");
    chgslarybaseCellListListExportDTO.setOldPaySum("调整后缴额合计");
   
    
    chgslarybaseCellListList.add(chgslarybaseCellListListExportDTO);
    chgslarybaseCellListHeadlist.add(chgslarybaseCellListHeadExportDTO);
    
    try {
      
      ChgslarybaseCellListHeadExportDTO chgslarybaseCellListHeadExportDTO1 = new ChgslarybaseCellListHeadExportDTO();
    
      ChgslarybaseCellListListExportDTO chgslarybaseCellListInfoDTO1 = (ChgslarybaseCellListListExportDTO)explist.get(0);
      String orgid=chgslarybaseCellListInfoDTO1.getOrgId();
      chgslarybaseCellListHeadExportDTO1.setOrgId(BusiTools.convertTenNumber(orgid));
      chgslarybaseCellListHeadExportDTO1.setOrgName(chgslarybaseCellListInfoDTO1.getOrgName());
      chgslarybaseCellListHeadExportDTO1.setChgMonth(chgslarybaseCellListInfoDTO1.getChgMonth());
      chgslarybaseCellListHeadlist.add(chgslarybaseCellListHeadExportDTO1);

      for (int i = 0; i < explist.size(); i++) {
       
     
        ChgslarybaseCellListListExportDTO chgslarybaseCellListListExportDTO1 = new ChgslarybaseCellListListExportDTO();
        ChgslarybaseCellListListExportDTO chgslarybaseCellListInfoDTO = (ChgslarybaseCellListListExportDTO)explist.get(i); 
        String empId=chgslarybaseCellListInfoDTO.getEmpId().toString();
        chgslarybaseCellListListExportDTO1.setEmpId(BusiTools.convertSixNumber(empId));
        chgslarybaseCellListListExportDTO1.setEmpName(chgslarybaseCellListInfoDTO.getEmpName());
        chgslarybaseCellListListExportDTO1.setCardNum(chgslarybaseCellListInfoDTO.getCardNum());      
        chgslarybaseCellListListExportDTO1.setOldSalaryBase(chgslarybaseCellListInfoDTO.getOldSalaryBase());
        chgslarybaseCellListListExportDTO1.setOldOrgPay(chgslarybaseCellListInfoDTO.getOldOrgPay());
        chgslarybaseCellListListExportDTO1.setOldEmpPay(chgslarybaseCellListInfoDTO.getOldEmpPay());
        chgslarybaseCellListListExportDTO1.setOldEmpPay(chgslarybaseCellListInfoDTO.getOldEmpPay());
        chgslarybaseCellListListExportDTO1.setOldOrgPayEmpPaySum(chgslarybaseCellListInfoDTO.getOldOrgPayEmpPaySum());
        chgslarybaseCellListListExportDTO1.setSalaryBase(chgslarybaseCellListInfoDTO.getSalaryBase());
        chgslarybaseCellListListExportDTO1.setOrgPay(chgslarybaseCellListInfoDTO.getOrgPay());
        chgslarybaseCellListListExportDTO1.setEmpPay(chgslarybaseCellListInfoDTO.getEmpPay());
        chgslarybaseCellListListExportDTO1.setOldPaySum(chgslarybaseCellListInfoDTO.getOldPaySum());  
        chgslarybaseCellListList.add(chgslarybaseCellListListExportDTO1);
        
       
      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("ChgslarybaseCellListHeadExport", chgslarybaseCellListHeadlist);
      addpayExportMap.put("ChgslarybaseCellListListExport", chgslarybaseCellListList);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
