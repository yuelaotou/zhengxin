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
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.AddpayInfoDto;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayHeadExportDTO;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayListExportDTO;

/*单位编号、单位名称、补缴年月、票据编号、职工编号、职工姓名、单位应缴金额、职工应缴金额、单位补缴金额、职工补缴金额（单补的时候另一个置0）*/
/**
 * @author ly TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class orgaddpay_expDataSource
    implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList orgaddpayHeadlist = new ArrayList();
    ArrayList orgaddpaylist = new ArrayList();
    OrgaddpayHeadExportDTO orgaddpayHeadExportDto = new OrgaddpayHeadExportDTO();
    OrgaddpayListExportDTO orgaddpayListExportDto = new OrgaddpayListExportDTO();
    orgaddpayHeadExportDto.setOrgid("单位编号");
    orgaddpayHeadExportDto.setOrgName("单位名称");
    orgaddpayHeadExportDto.setAddStartPayMonth("补缴起始年月");
    orgaddpayHeadExportDto.setAddpayMonth("补缴终止年月");
    orgaddpayHeadExportDto.setNoteNum("结算号");
    orgaddpayListExportDto.setEmpId("职工编号");
    orgaddpayListExportDto.setEmpName("职工姓名");
    orgaddpayListExportDto.setOrgShouldpay("单位应缴金额");
    orgaddpayListExportDto.setEmpShouldpay("职工应缴金额");
    orgaddpayListExportDto.setOrgAddpayMoney("单位补缴金额");
    orgaddpayListExportDto.setEmpAddpayMoney("职工补缴金额");
    orgaddpayListExportDto.setEmpPayStatus("职工状态");
    orgaddpayListExportDto.setStartPayMonth("补缴起始年月");
    orgaddpayListExportDto.setEndPayMonth("补缴终止年月");
    orgaddpayListExportDto.setSalaryBase("工资基数");
    orgaddpayListExportDto.setOrgRate("单位缴率");
    orgaddpayListExportDto.setEmpRate("职工缴率");
    orgaddpaylist.add(orgaddpayListExportDto);
    orgaddpayHeadlist.add(orgaddpayHeadExportDto);
    try {
      for (int i = 0; i < explist.size(); i++) {
        OrgaddpayHeadExportDTO orgaddpayHeadExportDto1 = new OrgaddpayHeadExportDTO();
        OrgaddpayListExportDTO orgaddpayListExportDto1 = new OrgaddpayListExportDTO();
        AddpayInfoDto addpayInfoDto = (AddpayInfoDto) explist.get(i);
        orgaddpayHeadExportDto1.setOrgid(BusiTools.convertTenNumber(addpayInfoDto.getOrgid()));
        orgaddpayHeadExportDto1.setOrgName(addpayInfoDto.getOrgName());
        orgaddpayHeadExportDto1.setAddStartPayMonth(addpayInfoDto.getAddStartPayMonth());
        orgaddpayHeadExportDto1.setAddpayMonth(addpayInfoDto.getAddpayMonth());
        orgaddpayHeadExportDto1.setNoteNum(addpayInfoDto.getNoteNum());
        orgaddpayListExportDto1.setEmpId(BusiTools.convertSixNumber(addpayInfoDto.getEmpId()));
        orgaddpayListExportDto1.setEmpName(addpayInfoDto.getEmpName());
        orgaddpayListExportDto1.setOrgShouldpay(addpayInfoDto.getOrgShouldpay());
        orgaddpayListExportDto1.setEmpShouldpay(addpayInfoDto.getEmpShouldpay());
        orgaddpayListExportDto1.setOrgAddpayMoney(addpayInfoDto.getOrgAddpayMoney());
        orgaddpayListExportDto1.setEmpAddpayMoney(addpayInfoDto.getEmpAddpayMoney());  
        orgaddpayListExportDto1.setEmpPayStatus(addpayInfoDto.getEmpPayStatus());
        orgaddpayListExportDto1.setStartPayMonth(addpayInfoDto.getAddStartPayMonth());
        orgaddpayListExportDto1.setEndPayMonth(addpayInfoDto.getAddpayMonth());
        orgaddpayListExportDto1.setSalaryBase(addpayInfoDto.getSalaryBase());
        orgaddpayListExportDto1.setOrgRate(addpayInfoDto.getOrgRate());
        orgaddpayListExportDto1.setEmpRate(addpayInfoDto.getEmpRate());
        orgaddpaylist.add(orgaddpayListExportDto1);
        orgaddpayHeadlist.add(orgaddpayHeadExportDto1);
      }
      Factory faxtory = new Factory();   
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("OrgaddpayHeadExport", orgaddpayHeadlist);
      addpayExportMap.put("OrgaddpayListExport", orgaddpaylist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
