package org.xpup.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.common.util.imp.domn.interfaces.exception.XlsFileException;
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.AddPayExpDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.PersonAddPayDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;

public class AddPay_expDataSource    implements ExportDateSourceInterface{
 

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {


    List vlist = new ArrayList();
    AddPayExpDTO addPayExportDTOTemp = new AddPayExpDTO();

    addPayExportDTOTemp.setOrgId("单位编号");
    addPayExportDTOTemp.setOrgName("单位名称");
    addPayExportDTOTemp.setDocNum("票据编号");
    addPayExportDTOTemp.setEmpId("职工编号");
    addPayExportDTOTemp.setEmpName("职工姓名");
    addPayExportDTOTemp.setOrgPay("单位补缴金额");
    addPayExportDTOTemp.setEmpPay("职工补缴金额");
    addPayExportDTOTemp.setBeginMonth("补缴起始年月");
    addPayExportDTOTemp.setEndMonth("补缴终止年月");
    addPayExportDTOTemp.setReason("补缴原因");
   
    vlist.add(addPayExportDTOTemp);

    try {
      
      for (int i = 0; i < explist.size(); i++) {
        AddPayExpDTO addPayExportDTO = new AddPayExpDTO();
        PersonAddPayDTO personAddPayDTO = (PersonAddPayDTO) explist .get(i);      
        addPayExportDTO.setOrgId(personAddPayDTO.getOrgId());
        addPayExportDTO.setOrgName(personAddPayDTO.getOrgName());
        addPayExportDTO.setDocNum(personAddPayDTO.getDocNum());
        addPayExportDTO.setEmpId(personAddPayDTO.getEmpId());
        addPayExportDTO.setEmpName(personAddPayDTO.getEmpName());
        addPayExportDTO.setOrgPay(personAddPayDTO.getOrgPaySum().toString());
        addPayExportDTO.setEmpPay(personAddPayDTO.getEmpPaySum().toString());
        addPayExportDTO.setBeginMonth(personAddPayDTO.getAddPayBeginYearMonth());
        addPayExportDTO.setEndMonth(personAddPayDTO.getAddPayEndYearMonth());
        addPayExportDTO.setReason(personAddPayDTO.getAddPayReason());      
        vlist.add(addPayExportDTO);
      }
      Factory faxtory = new Factory();
      Map aaa = new HashMap();

      aaa.put("AddPayExp", vlist);
      faxtory.setInfomation(xmlfile, xlsFile, aaa,"org.xpup.hafmis.syscollection.paymng.personaddpay.dto.");
    } catch (XlsFileException e) {
      e.printStackTrace();
    }
  }

}
