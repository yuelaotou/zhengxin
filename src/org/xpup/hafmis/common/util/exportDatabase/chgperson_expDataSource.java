package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpContentDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpNoteDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpTitleDTO;

public class chgperson_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {

    ChgpersonExpDTO emp = (ChgpersonExpDTO) explist.get(0);

    ArrayList exportList1 = new ArrayList();
    ChgpersonExpTitleDTO titleDto = new ChgpersonExpTitleDTO();
    titleDto.setOrgunitcode("单位编号");
    titleDto.setOrgunitcodecontent(BusiTools.convertTenNumber(emp.getOrgunitcodecontent()));
    titleDto.setOrgunitname("单位名称");
    titleDto.setOrgunitnamecontent(emp.getOrgunitnamecontent());
    titleDto.setOrgunitchgmonth("调整年月");
    titleDto.setOrgunitchgmonthcontent(emp.getOrgunitchgmonthcontent());
    exportList1.add(titleDto);

    ArrayList exportList2 = new ArrayList();
    ChgpersonExpContentDTO contentDto = new ChgpersonExpContentDTO();
    // 职工编号、职工姓名、证件类型、证件号码、出生日期、性别、所在部门、工资基数、单位缴额、职工缴额、是否参与汇缴
    contentDto.setEmpcode("职工编号");
    contentDto.setEmpname("职工姓名");
    contentDto.setCardkind("证件类型");
    contentDto.setCardnum("证件号码");
    contentDto.setBirthday("出生日期");
    contentDto.setSex("性别");
    contentDto.setDept("所在部门");
    contentDto.setSalarybase("工资基数");
    if (emp.getPaymode().equals("2")) {
      contentDto.setOrgpay("单位缴额");
      contentDto.setEmppay("职工缴额");
    }
    contentDto.setChgtype("变更类型");
    contentDto.setPartin("是否参与汇缴");
    contentDto.setChgreason("变更原因");
    contentDto.setPayStatus("职工缴存状态");

    exportList2.add(contentDto);

    ArrayList exportList3 = new ArrayList();
    ChgpersonExpNoteDTO noteDto = new ChgpersonExpNoteDTO();
    noteDto.setNote("说明:证件类型：0身份证  1户口簿  2护照  3军官证  4士兵证  5港澳居民来往内地通行证  6台湾同胞来往内地通行证  7临时身份证  8外国人居留证  9警官证"+";;;;"
        +"性别：０未知性别１男２女 9未知说明性别"+";;;;"+"变更类型：１开户２启封３封存"+";;;;"+"是否参与汇缴：0是,1否"+";;;;"+"变更原因：1、其他；2、转出；3、转入；4、销户"+"选择变更类型为开户，必须填写是否参与汇缴，若选择变更类型为启封或封存时，可以不用填写是否参与汇缴字段");
    exportList3.add(noteDto);
    
    try {
      for (int i = 0; i < explist.size(); i++) {
        contentDto = new ChgpersonExpContentDTO();
        ChgpersonExpDTO returnEmp = (ChgpersonExpDTO) explist.get(i);
        contentDto.setEmpcode(BusiTools.convertSixNumber(returnEmp.getEmpcode()));
        contentDto.setEmpname(returnEmp.getEmpname());
        contentDto.setCardkind(returnEmp.getCardkind());
        contentDto.setCardnum(returnEmp.getCardnum());
        contentDto.setBirthday(returnEmp.getBirthday());
        contentDto.setSex(returnEmp.getSex());
        if(returnEmp.getDept()==null){
          contentDto.setDept("");
        }else{
          contentDto.setDept(returnEmp.getDept());
        }
        contentDto.setSalarybase(returnEmp.getSalarybase());
        if (returnEmp.getPaymode().equals("2")) {
          contentDto.setOrgpay(returnEmp.getOrgpay());
          contentDto.setEmppay(returnEmp.getEmppay());
        }
        contentDto.setChgtype("");
        contentDto.setPartin("");
        contentDto.setChgreason("");

        contentDto.setPayStatus(returnEmp.getPayStatus());
        exportList2.add(contentDto);
      }

      Factory faxtory = new Factory();
      Map map = new HashMap();

      map.put("ChgpersonExpTitle", exportList1);
      map.put("ChgpersonExpNote", exportList3);
      map.put("ChgpersonExpContent", exportList2);

      faxtory.setInfomation(xmlfile, xlsFile, map,
          "org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
