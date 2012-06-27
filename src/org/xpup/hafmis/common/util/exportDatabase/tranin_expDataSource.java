package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.demo.dao.DemoDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInTailDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.tranmng.tranin.dto.TraninExportDTO;
import org.xpup.hafmis.syscollection.tranmng.tranin.dto.TraninExportExplainDTO;
import org.xpup.hafmis.syscollection.tranmng.tranin.dto.TraninExportHeadDTO;

/**
 * @author ly TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class tranin_expDataSource implements ExportDateSourceInterface {
  protected OrgDAO orgDAO;

  public void setEmpPaymentAgreementDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setOrgPaymentAgreementDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    
    ArrayList traninExportExplainList = new ArrayList();
    TraninExportExplainDTO traninExportExplainDTO = new TraninExportExplainDTO();
    traninExportExplainDTO.setExplain("证件类型：0身份证  1户口簿  2护照  3军官证  4士兵证  5港澳居民来往内地通行证  6台湾同胞来往内地通行证" 
        +"  7临时身份证  8外国人居留证  9警官证");
    traninExportExplainList.add(traninExportExplainDTO);
    
    ArrayList traninExportList1 = new ArrayList();
    TraninExportHeadDTO traninExportHeadDTO = new TraninExportHeadDTO();
    traninExportHeadDTO.setInOrgId("转入单位编号");
    traninExportHeadDTO.setInOrgName("转入单位名称");
    traninExportHeadDTO.setNoteNum("票据编号");

    traninExportList1.add(traninExportHeadDTO);
    ArrayList traninExportList2 = new ArrayList();
    TraninExportDTO traninExportDTO = new TraninExportDTO();
    Map map = (Map) explist.get(0);

    traninExportDTO.setName("职工姓名");
    traninExportDTO.setCardKind("证件类型");
    traninExportDTO.setCardNum("证件号码");
    traninExportDTO.setBirthday("出生日期");
    traninExportDTO.setSex("性别(0未知1男2女)");
    traninExportDTO.setSalaryBase("工资基数");
    traninExportDTO.setPreBalance("往年余额");
    traninExportDTO.setCurBalance("本年余额");
    traninExportDTO.setMonthIncome("职工月收入");
    traninExportDTO.setCurInterest("利息");
    traninExportDTO.setTel("家庭电话");
    traninExportDTO.setMobileTel("移动电话");
    if (((String) map.get("payMode")).equals("2")) {
      traninExportDTO.setOrgPay("单位缴额");
      traninExportDTO.setEmpPay("职工缴额");
    }
    traninExportList2.add(traninExportDTO);
    try {
      for (int i = 0; i < explist.size(); i++) {
        Map map1 = (Map) explist.get(i);
        TraninExportHeadDTO traninExportHeadDTO1 = new TraninExportHeadDTO();
        traninExportHeadDTO1.setInOrgId(BusiTools.convertSixNumber((String) map1.get("inOrgId")));
        traninExportHeadDTO1.setInOrgName((String) map1.get("inOrgName"));
        traninExportHeadDTO1.setNoteNum("");
        // 不到出票据编号
        // traninExportHeadDTO1.setNoteNum((String)map.get("noteNum"));
        traninExportList1.add(traninExportHeadDTO1);
      }
      TraninExportDTO traninExportDTO1 = new TraninExportDTO();
      traninExportList2.add(traninExportDTO1);
      Factory faxtory = new Factory();
      Map demoExportMap = new HashMap();
      demoExportMap.put("TraninExportExplain", traninExportExplainList);
      demoExportMap.put("TraninExportHead", traninExportList1);
      demoExportMap.put("TraninExport", traninExportList2);
      faxtory.setInfomation(xmlfile, xlsFile, demoExportMap,
          "org.xpup.hafmis.syscollection.tranmng.tranin.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }

}
