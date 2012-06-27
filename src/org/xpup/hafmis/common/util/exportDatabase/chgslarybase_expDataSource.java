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



/*单位编号、单位名称、调整年月、职工编号、职工姓名、证件号码、调整前工资基数、调整后工资基数（空）、*/
/**
 * @author ly TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class chgslarybase_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList chgslarybaseHeadlist = new ArrayList();
    ArrayList chgslarybaselist = new ArrayList();
    ChgslarybaseHeadExportDTO chgslarybaseHeadExportDto = new ChgslarybaseHeadExportDTO();
    ChgslarybaseListExportDTO chgslarybaseListExportDto = new ChgslarybaseListExportDTO();
    
    
    chgslarybaseHeadExportDto.setOrgId("单位编号");
    chgslarybaseHeadExportDto.setOrgName("单位名称");
    chgslarybaseHeadExportDto.setChgMonth("调整年月");
    chgslarybaseHeadExportDto.setPayMode("  ");
    chgslarybaseHeadExportDto.setPayMode1("  ");
    
    chgslarybaseListExportDto.setEmpId("职工编号");
    chgslarybaseListExportDto.setEmpName("职工姓名");
    chgslarybaseListExportDto.setCardNum("证件号码");
    chgslarybaseListExportDto.setOldSalaryBase("调整前工资基数");
    chgslarybaseListExportDto.setSalaryBase("调整后工资基数");
   
    
    chgslarybaselist.add(chgslarybaseListExportDto);
    chgslarybaseHeadlist.add(chgslarybaseHeadExportDto);
    
    try {
      
      
      ChgslarybaseHeadExportDTO chgslarybaseHeadExportDto1 = new ChgslarybaseHeadExportDTO();
      ChgslarybaseInfoDTO chgslarybaseInfoDTO1 = (ChgslarybaseInfoDTO) explist.get(0);
      String orgid=chgslarybaseInfoDTO1.getOrgId();
      chgslarybaseHeadExportDto1.setOrgId(BusiTools.convertTenNumber(orgid));
      chgslarybaseHeadExportDto1.setOrgName(chgslarybaseInfoDTO1.getOrgName());
      chgslarybaseHeadExportDto1.setChgMonth(chgslarybaseInfoDTO1.getChgMonth());
      chgslarybaseHeadlist.add(chgslarybaseHeadExportDto1);

      for (int i = 0; i < explist.size(); i++) {
       
        ChgslarybaseListExportDTO chgslarybaseListExportDto1 = new ChgslarybaseListExportDTO();
        
        
        ChgslarybaseInfoDTO chgslarybaseInfoDTO = (ChgslarybaseInfoDTO) explist.get(i);
        
        String empId=chgslarybaseInfoDTO.getEmpId();
        chgslarybaseListExportDto1.setEmpId(BusiTools.convertSixNumber(empId));
        chgslarybaseListExportDto1.setEmpName(chgslarybaseInfoDTO.getEmpName());
        chgslarybaseListExportDto1.setCardNum(chgslarybaseInfoDTO.getCardNum());
        chgslarybaseListExportDto1.setOldSalaryBase(chgslarybaseInfoDTO.getOldSalaryBase());
        chgslarybaseListExportDto1.setSalaryBase(chgslarybaseInfoDTO.getSalaryBase());
   
        
        chgslarybaselist.add(chgslarybaseListExportDto1);
        
       
      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("ChgslarybaseHeadExport", chgslarybaseHeadlist);
      addpayExportMap.put("ChgslarybaseListExport", chgslarybaselist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
