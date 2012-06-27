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
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutHeadExportDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutInfoDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutListExportDTO;

/*转出单位编号、转出单位名称、转入单位编号、转入单位名称、票据编号、职工编号、职工姓名、证件类型、证件号码、是否结息、*/
/**
 * @author ly TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class tranOut_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {

    ArrayList tranoutHeadlist = new ArrayList();
    ArrayList tranoutlist = new ArrayList();
    TranoutHeadExportDTO tranoutHeadExportDTO = new TranoutHeadExportDTO();
    TranoutListExportDTO tranoutListExportDTO = new TranoutListExportDTO();

    tranoutHeadExportDTO.setOrgOutid("转出单位编号");
    tranoutHeadExportDTO.setOrgOutName("转出单位名称");
    tranoutHeadExportDTO.setOrgInid("转入单位编号");
    tranoutHeadExportDTO.setOrgInName("转入单位名称");
    tranoutHeadExportDTO.setNoteNum("票据编号");
    tranoutHeadExportDTO.setExpalanation("(是否结息说明：1：结息 2：不结息)");
    

    tranoutListExportDTO.setEmpId("职工编号");
    tranoutListExportDTO.setEmpName("职工姓名");
    tranoutListExportDTO.setCard_king("证件类型");
    tranoutListExportDTO.setCard_num("证件号码");
    tranoutListExportDTO.setIssettinrest("是否结息");
    tranoutListExportDTO.setTranReason("转移原因");
    tranoutListExportDTO.setTranin_empid("转入职工编号");
    tranoutlist.add(tranoutListExportDTO);
    tranoutHeadlist.add(tranoutHeadExportDTO);

    try {

      TranoutHeadExportDTO tranoutHeadExportDTO1 = new TranoutHeadExportDTO();
      TranoutInfoDTO tranoutInfoDTO1 = (TranoutInfoDTO) explist.get(0);
      
      String orgOutid=tranoutInfoDTO1.getOrgOutid();   
      tranoutHeadExportDTO1.setOrgOutid(BusiTools.convertSixNumber(orgOutid));
      tranoutHeadExportDTO1.setOrgOutName(tranoutInfoDTO1.getOrgOutName()); 
      String orgInid=tranoutInfoDTO1.getOrgInid();   
      if(orgInid==null||orgInid.equals("")){
        tranoutHeadExportDTO1.setOrgInid(tranoutInfoDTO1.getOrgInid());   
      }else{
        tranoutHeadExportDTO1.setOrgInid(BusiTools.convertSixNumber(orgInid));    
      }

      tranoutHeadExportDTO1.setOrgInName(tranoutInfoDTO1.getOrgInName());
      tranoutHeadExportDTO1.setNoteNum(tranoutInfoDTO1.getNoteNum());
      tranoutHeadlist.add(tranoutHeadExportDTO1);

      for (int i = 0; i < explist.size(); i++) {

        TranoutListExportDTO tranoutListExportDTO1 = new TranoutListExportDTO();
        TranoutInfoDTO tranoutInfoDTO = (TranoutInfoDTO) explist.get(i);

        String empId=tranoutInfoDTO.getEmpId();        
        tranoutListExportDTO1.setEmpId(BusiTools.convertSixNumber(empId));
        tranoutListExportDTO1.setEmpName(tranoutInfoDTO.getEmpName());
        tranoutListExportDTO1.setCard_king(tranoutInfoDTO.getCard_king());
        tranoutListExportDTO1.setCard_num(tranoutInfoDTO.getCard_num());
        tranoutListExportDTO1.setIssettinrest(tranoutInfoDTO.getIssettinrest());
        tranoutListExportDTO1.setTranReason(tranoutInfoDTO.getTranReason());

        tranoutlist.add(tranoutListExportDTO1);

      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("TranoutHeadExport", tranoutHeadlist);
      addpayExportMap.put("TranoutListExport", tranoutlist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,
          "org.xpup.hafmis.syscollection.tranmng.tranout.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }

}
