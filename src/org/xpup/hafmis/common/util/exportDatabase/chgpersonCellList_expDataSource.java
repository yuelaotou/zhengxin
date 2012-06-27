package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonCellListHeadExportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonCellListListExportDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;


 


//吴洪涛2008617
/*变更类型  职工编号  职工姓名  证件号码 变更后职工状态 工资基数 单位缴额 职工缴额 缴额合计 变更原因 、*/
/*单位编号、单位名称、调整年月、*/

public class chgpersonCellList_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList chgPersonCellListHeadlist = new ArrayList();
    ArrayList chgPersonCellListList = new ArrayList();
    ChgPersonCellListHeadExportDTO chgPersonCellListHeadExportDTO = new ChgPersonCellListHeadExportDTO();
    ChgPersonCellListListExportDTO chgPersonCellListListExportDTO = new ChgPersonCellListListExportDTO();
    
    
    chgPersonCellListHeadExportDTO.setOrgId("单位编号");
    chgPersonCellListHeadExportDTO.setOrgName("单位名称");
    chgPersonCellListHeadExportDTO.setChgMonth("调整年月");
    
    chgPersonCellListListExportDTO.setChgType("变更类型");
    chgPersonCellListListExportDTO.setEmpId ("职工编号");
    chgPersonCellListListExportDTO.setName ("职工姓名");
    chgPersonCellListListExportDTO.setCardNum("证件号码");
    chgPersonCellListListExportDTO.setTemp_oldPayStatus("变更后职工状态");
    chgPersonCellListListExportDTO.setSalaryBase("工资基数");
    chgPersonCellListListExportDTO.setOrgPay("单位缴额");
    chgPersonCellListListExportDTO.setEmpPay(" 职工缴额");
    chgPersonCellListListExportDTO.setSumPay("缴额合计");
    chgPersonCellListListExportDTO.setTemp_chgreason("变更原因");
  
   
    
    chgPersonCellListList.add(chgPersonCellListListExportDTO);
    chgPersonCellListHeadlist.add(chgPersonCellListHeadExportDTO);
    
    try {
      
      ChgPersonCellListHeadExportDTO chgPersonCellListHeadExportDTO1 = new ChgPersonCellListHeadExportDTO();
    
      ChgPersonCellListListExportDTO chgPersonCellListListExportDTO1 = (ChgPersonCellListListExportDTO)explist.get(0);
      String orgid=chgPersonCellListListExportDTO1.getOrgId();
      chgPersonCellListHeadExportDTO1.setOrgId(BusiTools.convertTenNumber(orgid));
      chgPersonCellListHeadExportDTO1.setOrgName(chgPersonCellListListExportDTO1.getOrgName());
      chgPersonCellListHeadExportDTO1.setChgMonth(chgPersonCellListListExportDTO1.getChgMonth());
      chgPersonCellListHeadlist.add(chgPersonCellListHeadExportDTO1);
      


      for (int i = 0; i < explist.size(); i++) {
       
     
        ChgPersonCellListListExportDTO chgPersonCellListListExportDTO2 = new ChgPersonCellListListExportDTO();
        ChgPersonCellListListExportDTO chgPersonCellListListExportDTO3 = (ChgPersonCellListListExportDTO)explist.get(i); 
        
        chgPersonCellListListExportDTO2.setChgType(chgPersonCellListListExportDTO3.getChgType());
        chgPersonCellListListExportDTO2.setEmpId(chgPersonCellListListExportDTO3.getEmpId());
        chgPersonCellListListExportDTO2.setName(chgPersonCellListListExportDTO3.getName());
        chgPersonCellListListExportDTO2.setCardNum(chgPersonCellListListExportDTO3.getCardNum());
        chgPersonCellListListExportDTO2.setTemp_oldPayStatus(chgPersonCellListListExportDTO3.getTemp_oldPayStatus());
        chgPersonCellListListExportDTO2.setSalaryBase(chgPersonCellListListExportDTO3.getSalaryBase());
        chgPersonCellListListExportDTO2.setOrgPay(chgPersonCellListListExportDTO3.getOrgPay());
        chgPersonCellListListExportDTO2.setEmpPay(chgPersonCellListListExportDTO3.getEmpPay());
        chgPersonCellListListExportDTO2.setSumPay(chgPersonCellListListExportDTO3.getSumPay());
        chgPersonCellListListExportDTO2.setTemp_chgreason(chgPersonCellListListExportDTO3.getTemp_chgreason());
      
        chgPersonCellListList.add(chgPersonCellListListExportDTO2);
        
       
      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("ChgPersonCellListHeadExport", chgPersonCellListHeadlist);
      addpayExportMap.put("ChgPersonCellListListExport", chgPersonCellListList);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
