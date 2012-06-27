package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
      
import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.dto.DemoExportDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenExpContentDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenExpNoteDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenExpTitleDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenImpContentDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpContentDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpTitleDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;

public class empopen_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    try {
    ArrayList exportList1 = new ArrayList();
    EmpOpenExpTitleDTO titleDto1 = new EmpOpenExpTitleDTO();
    titleDto1.setOrgunitcode("单位编号");
    titleDto1.setOrgunitname("单位名称");
    exportList1.add(titleDto1);
    
    String orgId = (String) explist.get(0);
    String orgname = (String)explist.get(1);
    // 判断缴存方式
    String type = (String)explist.get(2);
    EmpOpenExpTitleDTO titleDto2 = new EmpOpenExpTitleDTO();
    titleDto2.setOrgunitcode(orgId);
    titleDto2.setOrgunitname(orgname);
    exportList1.add(titleDto2);
    
    
    
    ArrayList exportList2 = new ArrayList();
    EmpOpenExpContentDTO contentDto1 = new EmpOpenExpContentDTO();
    // 职工编号、职工姓名、证件号码、工资基数、单位缴额、职工缴额
    contentDto1.setEmpname("职工姓名");
    contentDto1.setCardnum("证件号码");
    contentDto1.setCardkind("证件类型");
    contentDto1.setDept("所在部门");
    contentDto1.setTel("家庭电话");
    contentDto1.setMobileTle("移动电话");
    contentDto1.setMonthIncome("职工月收入");
    contentDto1.setSalarybase("工资基数");
    if (type.equals("2")) {
      contentDto1.setOrgpay("单位缴额");
      contentDto1.setEmppay("职工缴额");
    }
    exportList2.add(contentDto1);
    if(explist.size()>3){
      for(int i=3;i<explist.size();i++){
        EmpOpenExpContentDTO empOpenImpContentDTO =new EmpOpenExpContentDTO();
        empOpenImpContentDTO=(EmpOpenExpContentDTO)explist.get(i);
        exportList2.add(i-2, empOpenImpContentDTO);
      }
    }
      

    ArrayList exportList3 = new ArrayList();
    EmpOpenExpNoteDTO noteDto3 = new EmpOpenExpNoteDTO();
    noteDto3.setNote("导出说明");
    noteDto3.setDocmun("证件类型：0身份证  1户口簿  2护照  3军官证  4士兵证  5港澳居民来往内地通行证  6台湾同胞来往内地通行证" +
        "  7临时身份证  8外国人居留证  9警官证");
    exportList3.add(noteDto3);
    
    
    
      Factory faxtory = new Factory();
      Map map = new HashMap();

      map.put("EmpOpenExpTitle", exportList1);
      map.put("EmpOpenExpContent", exportList2);
      map.put("EmpOpenExpNote", exportList3);

      faxtory.setInfomation(xmlfile, xlsFile, map,
          "org.xpup.hafmis.syscollection.accountmng.accountopen.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
