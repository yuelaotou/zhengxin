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
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayHeadExportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayListExportDTO;





/*单位编号、单位名称、调整年月*//*职工编号、职工姓名、证件号码、原单位缴额、原职工缴额、新单位缴额（空）、新职工缴额（空）*/
/**
 * @author ly TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class chgpay_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList chgpayHeadlist = new ArrayList();
    ArrayList chgpaylist = new ArrayList();
    ChgpayHeadExportDTO chgpayHeadExportDTO = new ChgpayHeadExportDTO();
    ChgpayListExportDTO chgpayListExportDTO = new ChgpayListExportDTO();

    chgpayHeadExportDTO.setOrgId("单位编号");
    chgpayHeadExportDTO.setOrgName("单位名称");
    chgpayHeadExportDTO.setChgMonth("调整年月");
    chgpayHeadExportDTO.setPayMode("导出说明:");
    chgpayHeadExportDTO.setPayMode1("缴存方式,1:职工缴额;2:单一缴率");
    
    chgpayListExportDTO.setEmpId("职工编号");
    chgpayListExportDTO.setEmpName("职工姓名");
    chgpayListExportDTO.setCardNum("证件号码");
    chgpayListExportDTO.setOldOrgPay("原单位缴额");
    chgpayListExportDTO.setOldEmpPay("原职工缴额");
    chgpayListExportDTO.setEmpPay("新单位缴额");
    chgpayListExportDTO.setOrgPay("新职工缴额");
    chgpayListExportDTO.setSalaryBase("工资基数");
   
    
    chgpaylist.add(chgpayListExportDTO);
    chgpayHeadlist.add(chgpayHeadExportDTO);
    
    try {
      
      
      ChgpayHeadExportDTO chgpayHeadExportDTO1 = new ChgpayHeadExportDTO();
      ChgpayInfoDTO chgpayInfoDTO1 = (ChgpayInfoDTO) explist.get(0);     
      String orgid=chgpayInfoDTO1.getOrgId();   
      chgpayHeadExportDTO1.setOrgId(BusiTools.convertSixNumber(orgid));
      chgpayHeadExportDTO1.setOrgName(chgpayInfoDTO1.getOrgName());
      chgpayHeadExportDTO1.setChgMonth(chgpayInfoDTO1.getChgMonth());
      chgpayHeadExportDTO1.setPayMode("");
      chgpayHeadExportDTO1.setPayMode1("");
      
      chgpayHeadlist.add(chgpayHeadExportDTO1);

      for (int i = 0; i < explist.size(); i++) { 
        ChgpayListExportDTO chgpayListExportDTO1 = new ChgpayListExportDTO();  
        ChgpayInfoDTO chgpayInfoDTO = (ChgpayInfoDTO) explist.get(i);    
        String empId=chgpayInfoDTO.getEmpId();        
        chgpayListExportDTO1.setEmpId(BusiTools.convertSixNumber(empId));
        chgpayListExportDTO1.setEmpName(chgpayInfoDTO.getEmpName());
        chgpayListExportDTO1.setCardNum(chgpayInfoDTO.getCardNum());
        chgpayListExportDTO1.setOldOrgPay(chgpayInfoDTO.getOldOrgPay());
        chgpayListExportDTO1.setOldEmpPay(chgpayInfoDTO.getOldEmpPay());
        chgpayListExportDTO1.setEmpPay(chgpayInfoDTO.getEmpPay());
        chgpayListExportDTO1.setOrgPay(chgpayInfoDTO.getOrgPay());
        chgpayListExportDTO1.setSalaryBase(chgpayInfoDTO.getSalaryBase());
        
        chgpaylist.add(chgpayListExportDTO1);
        
       
      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("ChgpayHeadExport", chgpayHeadlist);
      addpayExportMap.put("ChgpayListExport", chgpaylist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
