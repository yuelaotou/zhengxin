package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.OrgAgentExportDTO;

public class orgagent_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para,
      List explist) throws Exception {
    // TODO Auto-generated method stub
    ArrayList orgAgentExportList = new ArrayList();
    OrgAgentExportDTO orgAgentExportDTO = new OrgAgentExportDTO();
    orgAgentExportDTO.setOrgId("单位编号");
    orgAgentExportDTO.setOrgName("单位名称");
    orgAgentExportDTO.setOrgAgentNum("单位代扣号");
    orgAgentExportList.add(orgAgentExportDTO);
    try {
      for (int i = 0; i < explist.size(); i++) {
        OrgAgentExportDTO orgAgentExportDTO1 = new OrgAgentExportDTO();
        OrgAgentExportDTO dto = (OrgAgentExportDTO) explist.get(i);
        orgAgentExportDTO1.setOrgId(dto.getOrgId());
        orgAgentExportDTO1.setOrgName(dto.getOrgName());
        orgAgentExportDTO1.setOrgAgentNum(dto.getOrgAgentNum());
        orgAgentExportList.add(orgAgentExportDTO1);
      }
      Factory faxtory = new Factory();
      Map map = new HashMap();
      map.put("OrgAgentExport", orgAgentExportList);
      faxtory.setInfomation(xmlfile, xlsFile, map,"org.xpup.hafmis.syscollection.accountmng.accountopen.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }

}
