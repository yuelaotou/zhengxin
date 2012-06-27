package org.xpup.hafmis.common.util.exportDatabase;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickEmpDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickOrgDTO;
public class pickup_expDataSource implements ExportDateSourceInterface{
  List org = new ArrayList();
  List empList = new ArrayList();
  public void makeFile(File xmlfile, String xlsfile, String orgunitcode, List list) throws Exception {
    Emp emp = (Emp)list.get(0);
    PickOrgDTO o = new PickOrgDTO();
    //设置头表的值
    o.setOrgId("单位编号");
    o.setOrgName("单位名称");
    o.setOrgNoteNumber("票据编号");
    o.setOrgunitname("提取类型：1：部分提取；2：销户提取；提取原因：类型1中：1：购房；2：公积金按月还贷；3：公积金一次性还贷款；4:重大疾病；5：特困；6：部分提取其他；类型2中：7：退休；8：死亡；9：调出市内；10：失业下岗两年；11：非本市户口解除合同；12：出国定居");
    o.setOrgunitcode("导出说明");
    PickOrgDTO orgDto = new PickOrgDTO();
    //获得头表的信息
    orgDto.setOrgId(BusiTools.convertSixNumber("0"+emp.getOrg().getId().toString()));
    orgDto.setOrgName(emp.getOrg().getOrgInfo().getName());
    //存放集合...
    org.add(o);//放标题头
    org.add(orgDto);//放具体的信息
    /*********************************************/
    PickEmpDTO empDto = new PickEmpDTO();
    empDto.setEmpId("职工编号");
    empDto.setCardKind("证件类型");
    empDto.setCardNumber("证件编号");
    empDto.setEmpName("职工姓名");
    empDto.setPickBalance("提取金额");
    empDto.setPickReason("提取原因");
    empDto.setPickType("提取类型");
    empDto.setMaxPickMon("最大提取金额");
    empDto.setHouseNum("房照号");
    empList.add(empDto);//把标题放入集合..
    Iterator it = list.iterator();
    while(it.hasNext()){//循环获取子表里面的值
      PickEmpDTO dto = new PickEmpDTO();
      Emp e = (Emp)it.next();
      dto.setEmpId(BusiTools.convertSixNumber(e.getEmpId().toString()));
      dto.setCardKind(BusiTools.getBusiValue(e.getEmpInfo().getCardKind().intValue(), BusiConst.DOCUMENTSSTATE));
      dto.setCardNumber(e.getEmpInfo().getCardNum());
      dto.setEmpName(e.getEmpInfo().getName());
      if(e.getPayOldYear()==null){
        e.setPayOldYear(e.getOrgPay().add(e.getEmpPay()));
      }
      int mul=e.getMul();
      Double maxMoney = new Double(e.getPreBalance().add(e.getCurBalance()).doubleValue()-e.getPayOldYear().doubleValue()*mul);
        if(maxMoney.compareTo(new Double(0))<=0){
          maxMoney=new Double(0);
        }
       
      dto.setMaxPickMon(new BigDecimal(maxMoney.toString()).setScale(2,BigDecimal.ROUND_DOWN).toString());
      empList.add(dto);
    }
    
//    ArrayList exportList3 = new ArrayList();
//    PickHeadExpDTO pickHeadExp=new PickHeadExpDTO();
//    pickHeadExp.setOrgunitname("提取类型：1：部分提取；2：销户提取；提取原因：类型1中：1：购房；2：还贷；3：翻建；类型2中：4：解除劳动合同；5：死亡；6：户籍迁出");
//    pickHeadExp.setOrgunitcode("导出说明");
//    exportList3.add(pickHeadExp);
    
    Factory factory = new Factory();
    Map map = new HashMap();
    map.put("PickOrg", org);//此Map的key必须和DTO前面的一样..也要和xml file name属性值是一样的
    map.put("PickEmp", empList);
//    map.put("PickHeadExp", exportList3);
    factory.setInfomation(xmlfile, xlsfile, map,"org.xpup.hafmis.syscollection.pickupmng.pickup.dto.");
  }
}
