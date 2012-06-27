package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.sysloan.dataready.develop.dto.BuildDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.BuildExportDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.BuildExportHeadDTO;

/**
 * @author ly TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class build_expDataSource implements ExportDateSourceInterface {
  
  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList bulidExportList1 = new ArrayList();
    BuildExportHeadDTO buildExportHeadDTO = new BuildExportHeadDTO();
    buildExportHeadDTO.setDeveloperId("开发商号");
    buildExportHeadDTO.setDeveloperName("开发商名称");
    buildExportHeadDTO.setFloorId("楼盘号");
    buildExportHeadDTO.setFloorName("楼盘名称");

    bulidExportList1.add(buildExportHeadDTO);
    
    ArrayList bulidExportList2 = new ArrayList();
    BuildExportDTO buildExportDTO = new BuildExportDTO();
   // buildExportDTO.setBuildId("楼栋编号");
   // buildExportDTO.setFloorId("楼盘编号");
    
    buildExportDTO.setBuildNum("楼栋号");
    buildExportDTO.setBuildAdd("楼栋地址");
    buildExportDTO.setBuild_s("建筑面积");
    buildExportDTO.setFundStatus("是否拨款(1是,0否)");
    buildExportDTO.setReserved("备注");
    
    bulidExportList2.add(buildExportDTO);
    try {
      BuildExportHeadDTO buildExportHeadDTO1 = new BuildExportHeadDTO();
      buildExportHeadDTO1.setDeveloperId((String)explist.get(0));
      buildExportHeadDTO1.setDeveloperName((String)explist.get(1));
      buildExportHeadDTO1.setFloorId((String)explist.get(2));
      buildExportHeadDTO1.setFloorName((String)explist.get(3));
      bulidExportList1.add(buildExportHeadDTO1);
      
      List list = (List)explist.get(4);
      for(int i=0;i<list.size();i++){
        BuildExportDTO buildExportDTO1 = new BuildExportDTO();
        BuildDTO buildDTO = (BuildDTO)list.get(i);
       // buildExportDTO1.setBuildId(buildDTO.getBuildId());
      //  buildExportDTO1.setFloorId(buildDTO.getFloorId());
        buildExportDTO1.setBuildNum(buildDTO.getBuildNum());
        buildExportDTO1.setBuildAdd(buildDTO.getBuildAdd());
       
        buildExportDTO1.setBuild_s(buildDTO.getBuild_s().toString());
        buildExportDTO1.setFundStatus(buildDTO.getFundStatus());
        buildExportDTO1.setReserved(buildDTO.getReserved());
        bulidExportList2.add(buildExportDTO1);
      }
      Factory faxtory = new Factory();
      Map demoExportMap = new HashMap();
      demoExportMap.put("BuildExportHead", bulidExportList1);
      demoExportMap.put("BuildExport", bulidExportList2);
      faxtory.setInfomation(xmlfile, xlsFile, demoExportMap,
          "org.xpup.hafmis.sysloan.dataready.develop.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }

}
