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
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto.EmpOperationFlowDTO;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto.EmpOperationFlowExportDTO;

/**
 * @author ly TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class empOperationFlow_expDataSource
    implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList empOperationFlowlist = new ArrayList();
    EmpOperationFlowExportDTO empOperationFlowExportDTO = new EmpOperationFlowExportDTO();
    empOperationFlowExportDTO.setNoteNum("票据编号");
    empOperationFlowExportDTO.setDocNum("凭证号");
    empOperationFlowExportDTO.setOrgid("单位编号");
    empOperationFlowExportDTO.setOrgname("单位名称");
    empOperationFlowExportDTO.setEmpid("职工编号");
    empOperationFlowExportDTO.setEmpname("职工姓名");
    empOperationFlowExportDTO.setOpType("业务类型");
    empOperationFlowExportDTO.setOpDate("发生日期");
    empOperationFlowExportDTO.setOpMoney("发生金额");
    empOperationFlowExportDTO.setOpInterest("发生利息");
    empOperationFlowExportDTO.setOpDirection("发生方向");
    empOperationFlowExportDTO.setOpStatus("业务状态");
    empOperationFlowlist.add(empOperationFlowExportDTO);
    try {
      for (int i = 0; i < explist.size(); i++) {
        EmpOperationFlowExportDTO empOperationFlowExportDTO1 = new EmpOperationFlowExportDTO();
        EmpOperationFlowDTO empOperationFlowDTO = (EmpOperationFlowDTO) explist.get(i);
        empOperationFlowExportDTO1.setNoteNum(empOperationFlowDTO.getNoteNum());
        empOperationFlowExportDTO1.setDocNum(empOperationFlowDTO.getDocNum());
        empOperationFlowExportDTO1.setOrgid(BusiTools.convertSixNumber(empOperationFlowDTO.getOrgid().toString()));
        empOperationFlowExportDTO1.setOrgname(empOperationFlowDTO.getOrgname());
        empOperationFlowExportDTO1.setEmpid(BusiTools.convertSixNumber(empOperationFlowDTO.getEmpid().toString()));
        empOperationFlowExportDTO1.setEmpname(empOperationFlowDTO.getEmpname());
        empOperationFlowExportDTO1.setOpType(empOperationFlowDTO.getOpType());
        empOperationFlowExportDTO1.setOpDate(empOperationFlowDTO.getOpDate());
        empOperationFlowExportDTO1.setOpMoney(empOperationFlowDTO.getOpMoney());
        empOperationFlowExportDTO1.setOpInterest(empOperationFlowDTO.getOpInterest());
        empOperationFlowExportDTO1.setOpDirection(empOperationFlowDTO.getOpDirection());
        empOperationFlowExportDTO1.setOpStatus(empOperationFlowDTO.getOpStatus());
        
        empOperationFlowlist.add(empOperationFlowExportDTO1);
      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("EmpOperationFlowExport", empOperationFlowlist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
