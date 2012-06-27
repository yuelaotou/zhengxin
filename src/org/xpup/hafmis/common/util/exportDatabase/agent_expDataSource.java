package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentExportDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentExportHeadDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentExportTitleDTO;

public class agent_expDataSource implements ExportDateSourceInterface{

  public void makeFile(File xmlfile, String xlsFile, String para, List explist) throws Exception {
    try {
      ArrayList exportList1 = new ArrayList();
      AgentExportTitleDTO AgentExportTitleDTO = new AgentExportTitleDTO();
      AgentExportTitleDTO.setAgentYearMonth("代扣年月");
      AgentExportTitleDTO.setNoteNum("结算号");
      AgentExportTitleDTO.setPayMode("结算方式");
      AgentExportTitleDTO.setAgentType("代扣类型");
      exportList1.add(AgentExportTitleDTO);
      
      List exportList = new ArrayList();

      AgentExportDTO AgentExportDTO = new AgentExportDTO();
      AgentExportDTO.setOrgAgentNum("单位代扣号");
      AgentExportDTO.setOrgName("单位名称");
      AgentExportDTO.setEmpAgentNum("职工代扣号");
      AgentExportDTO.setEmpName("职工姓名");
      AgentExportDTO.setCardNum("身份证号");
      AgentExportDTO.setAgentOrgPay("单位缴存额");
      AgentExportDTO.setAgentEmpPay("职工缴存额");
      AgentExportDTO.setAgentEmpSalary("工资");
      exportList.add(AgentExportDTO);
      
      List exportList2 = new ArrayList();
      AgentExportHeadDTO agentExportHeadDTO = new AgentExportHeadDTO();
      agentExportHeadDTO.setExplain("导出说明：结算方式：1均缴 2缴单位 3缴职工   代扣类型：1正常代扣 2欠缴代扣");
      exportList2.add(agentExportHeadDTO);
      
      Factory faxtory = new Factory();
      Map map = new HashMap();
      
      map.put("AgentExportHead", exportList2);
      map.put("AgentExportTitle", exportList1);
      map.put("AgentExport", exportList);

      faxtory.setInfomation(xmlfile, xlsFile, map,
          "org.xpup.hafmis.syscollection.paymng.agent.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
