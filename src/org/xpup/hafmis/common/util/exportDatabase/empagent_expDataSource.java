package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpAgentExportDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpAgentExportTitleDTO;

public class empagent_expDataSource implements ExportDateSourceInterface {
  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    try {
      ArrayList exportList1 = new ArrayList();
      EmpAgentExportTitleDTO empAgentExportTitleDTO = new EmpAgentExportTitleDTO();
      empAgentExportTitleDTO.setOrgId("单位编号");
      empAgentExportTitleDTO.setOrgName("单位名称");
      exportList1.add(empAgentExportTitleDTO);
      EmpAgentExportTitleDTO empAgentExportTitleDTO1 = new EmpAgentExportTitleDTO();
      String orgId = (String) explist.get(0);
      String orgName = (String) explist.get(1);
      empAgentExportTitleDTO1.setOrgId(orgId);
      empAgentExportTitleDTO1.setOrgName(orgName);
      exportList1.add(empAgentExportTitleDTO1);

      List exportList = new ArrayList();

      EmpAgentExportDTO empAgentExportDTO = new EmpAgentExportDTO();
      empAgentExportDTO.setCardNum("证件号码");
      // empAgentExportDTO.setEmpAgentNum("职工代扣号");
      empAgentExportDTO.setEmpId("职工编号");
      empAgentExportDTO.setEmpName("职工姓名");
      empAgentExportDTO.setSex("性别");
      empAgentExportDTO.setBirthday("出生日期");
      empAgentExportDTO.setCardKind("证件类型");
      empAgentExportDTO.setTel("家庭电话");
      empAgentExportDTO.setMobileTle("移动电话");
      empAgentExportDTO.setMonthIncome("职工月收入");
      empAgentExportDTO.setDepartment("所在部门");
      exportList.add(empAgentExportDTO);

      List temp_exportList = (List) explist.get(2);
      for (int i = 0; i < temp_exportList.size(); i++) {
        EmpAgentExportDTO empAgentExportDTO1 = new EmpAgentExportDTO();
        EmpAgentExportDTO temp_empAgentExportDTO = (EmpAgentExportDTO) temp_exportList.get(i);
        empAgentExportDTO1.setCardNum(temp_empAgentExportDTO.getCardNum());
        // empAgentExportDTO1.setEmpAgentNum(temp_empAgentExportDTO.getEmpAgentNum());
        empAgentExportDTO1.setEmpId(BusiTools
            .convertSixNumber(temp_empAgentExportDTO.getEmpId()));
        empAgentExportDTO1.setEmpName(temp_empAgentExportDTO.getEmpName());

        empAgentExportDTO1.setSex(temp_empAgentExportDTO.getSex());
        empAgentExportDTO1.setBirthday(temp_empAgentExportDTO.getBirthday());
        empAgentExportDTO1.setCardKind(temp_empAgentExportDTO.getCardKind());
        empAgentExportDTO1.setTel(temp_empAgentExportDTO.getTel());
        empAgentExportDTO1.setMobileTle(temp_empAgentExportDTO.getMobileTle());
        empAgentExportDTO1.setMonthIncome(temp_empAgentExportDTO
            .getMonthIncome());
        empAgentExportDTO1
            .setDepartment(temp_empAgentExportDTO.getDepartment());
        exportList.add(empAgentExportDTO1);
      }

      Factory faxtory = new Factory();
      Map map = new HashMap();

      map.put("EmpAgentExportTitle", exportList1);
      map.put("EmpAgentExport", exportList);

      faxtory.setInfomation(xmlfile, xlsFile, map,
          "org.xpup.hafmis.syscollection.accountmng.accountopen.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
