package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.dto.EmpBaseExportsEmpDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.dto.EmpBaseExportsOrgDTO;

public class empbaseexportDataSource implements ExportDateSourceInterface {
  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    try {
      ArrayList exportList1 = new ArrayList();
      EmpBaseExportsOrgDTO empBaseExportsOrgDTO = new EmpBaseExportsOrgDTO();
      empBaseExportsOrgDTO.setOrgId("单位编号");
      empBaseExportsOrgDTO.setOrgName("单位名称");
      exportList1.add(empBaseExportsOrgDTO);

      String orgId = (String) explist.get(0);
      String orgName = (String) explist.get(1);
      EmpBaseExportsOrgDTO empBaseExportsOrgDTO1 = new EmpBaseExportsOrgDTO();
      empBaseExportsOrgDTO1.setOrgId(BusiTools.convertTenNumber(orgId));
      empBaseExportsOrgDTO1.setOrgName(orgName);
      exportList1.add(empBaseExportsOrgDTO1);

      ArrayList exportList2 = new ArrayList();
      EmpBaseExportsEmpDTO empBaseExportsEmpDTO = new EmpBaseExportsEmpDTO();
      empBaseExportsEmpDTO.setEmpId("职工编号");
      empBaseExportsEmpDTO.setEmpName("职工姓名");
      empBaseExportsEmpDTO.setCardNum("证件号码");
      empBaseExportsEmpDTO.setSalaryBase("工资基数");
      empBaseExportsEmpDTO.setOrgPay("单位缴额");
      empBaseExportsEmpDTO.setEmpPay("职工缴额");
      empBaseExportsEmpDTO.setAllPay("缴额合计");
      empBaseExportsEmpDTO.setPayStatus("缴存状态");
      empBaseExportsEmpDTO.setOrg_pay_month("单位缴至年月");
      empBaseExportsEmpDTO.setEmp_pay_month("职工缴至年月");
      empBaseExportsEmpDTO.setBalance("账户余额");
      exportList2.add(empBaseExportsEmpDTO);

      Factory faxtory = new Factory();
      Map map = new HashMap();
      map.put("EmpBaseExportsOrg", exportList1);
      map.put("EmpBaseExportsEmp", exportList2);
      map.put("EmpBaseExports", explist.subList(2, explist.size()));

      faxtory
          .setInfomation(
              xmlfile,
              xlsFile,
              map,
              "org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
