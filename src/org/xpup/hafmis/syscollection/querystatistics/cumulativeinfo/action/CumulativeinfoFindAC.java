package org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.bsinterface.ICumulativeinfoFindBS;
import org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.form.CumulativeinfoFindAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 根据时间，查询住房公积金累计归集情况表
 * 
 *@author 杨光
 *2007-10-05
 */
public class CumulativeinfoFindAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.action.EmpOperationFlowTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    CumulativeinfoFindAF f=(CumulativeinfoFindAF)form;
    List returnList=new ArrayList();
    List officeCodeList=new ArrayList();
    List collectionBankIdList=new ArrayList();
    String officeCode="";
    String collectionBankId="";
    
    String officeCode1=f.getOfficeCode().trim();//办事处
    if(officeCode1==null || officeCode1.equals("")){
      officeCodeList=securityInfo.getOfficeList();
      OfficeDto officedto = null;
      for(int i=0;i<officeCodeList.size()-1;i++){
        officedto=(OfficeDto) officeCodeList.get(i);
        officeCode=officeCode+"'"+officedto.getOfficeCode()+"',";
      }
      officeCode=officeCode+"'"+((OfficeDto)(officeCodeList.get(officeCodeList.size()-1))).getOfficeCode()+"'";
    }else{
      officeCode="'"+officeCode1+"'";
    }
    String collectionBankId1=f.getCollectionBankId().trim();//归集银行
    if(collectionBankId1==null || collectionBankId1.equals("")){
      collectionBankIdList=securityInfo.getCollBankList();
      Userslogincollbank userslogincollbank = null;
      for(int i=0;i<collectionBankIdList.size()-1;i++){
        userslogincollbank=(Userslogincollbank) collectionBankIdList.get(i);
        collectionBankId=collectionBankId+"'"+userslogincollbank.getCollBankId()+"',";
      }
      collectionBankId=collectionBankId+"'"+((Userslogincollbank)(collectionBankIdList.get(collectionBankIdList.size()-1))).getCollBankId()+"'";
    }else{
      collectionBankId="'"+collectionBankId1+"'";
    }
    String queryTime=f.getQueryTime().trim();
    
    ICumulativeinfoFindBS cumulativeinfoFindBS = (ICumulativeinfoFindBS)BSUtils.getBusinessService("cumulativeinfoFindBS",this,mapping.getModuleConfig());
    returnList=cumulativeinfoFindBS.FindCumulativeinfo(securityInfo,officeCode,collectionBankId,queryTime);
    
    //各种需要相加的数据,比如小计，归集余额等等
    returnList.add(((BigDecimal)returnList.get(7)).add((BigDecimal)returnList.get(6)).add((BigDecimal)returnList.get(5)));//54
    returnList.add(((BigDecimal)returnList.get(9)).add((BigDecimal)returnList.get(16)));//55
    returnList.add(((BigDecimal)returnList.get(7)).add((BigDecimal)returnList.get(6)).add((BigDecimal)returnList.get(5)).subtract((BigDecimal)returnList.get(9)).subtract((BigDecimal)returnList.get(16)));//56
    returnList.add(((BigDecimal)returnList.get(7)).add((BigDecimal)returnList.get(6)).add((BigDecimal)returnList.get(5)).subtract((BigDecimal)returnList.get(9)).subtract((BigDecimal)returnList.get(16)).add((BigDecimal)returnList.get(17)));//57
    
    returnList.add(((BigDecimal)returnList.get(23)).add((BigDecimal)returnList.get(24)).add((BigDecimal)returnList.get(25)));//58
    returnList.add(((BigDecimal)returnList.get(27)).add((BigDecimal)returnList.get(34)));//59
    returnList.add(((BigDecimal)returnList.get(25)).add((BigDecimal)returnList.get(24)).add((BigDecimal)returnList.get(23)).subtract((BigDecimal)returnList.get(27)).subtract((BigDecimal)returnList.get(34)));//60
    returnList.add(((BigDecimal)returnList.get(25)).add((BigDecimal)returnList.get(24)).add((BigDecimal)returnList.get(23)).subtract((BigDecimal)returnList.get(27)).subtract((BigDecimal)returnList.get(34)).add((BigDecimal)returnList.get(35)));//61
    
    returnList.add(((BigDecimal)returnList.get(41)).add((BigDecimal)returnList.get(42)).add((BigDecimal)returnList.get(43)));//62
    returnList.add(((BigDecimal)returnList.get(45)).add((BigDecimal)returnList.get(52)));//63
    returnList.add(((BigDecimal)returnList.get(41)).add((BigDecimal)returnList.get(42)).add((BigDecimal)returnList.get(43)).subtract((BigDecimal)returnList.get(45)).subtract((BigDecimal)returnList.get(52)));//64
    returnList.add(((BigDecimal)returnList.get(41)).add((BigDecimal)returnList.get(42)).add((BigDecimal)returnList.get(43)).subtract((BigDecimal)returnList.get(45)).subtract((BigDecimal)returnList.get(52)).add((BigDecimal)returnList.get(35)));//65
    //本年累计数
    returnList.add(((BigDecimal)returnList.get(18)).add((BigDecimal)returnList.get(36)));//66
    returnList.add(((BigDecimal)returnList.get(19)).add((BigDecimal)returnList.get(37)));//67
    returnList.add(((BigDecimal)returnList.get(20)).add((BigDecimal)returnList.get(38)));//68
    returnList.add(((BigDecimal)returnList.get(21)).add((BigDecimal)returnList.get(39)));//69
    returnList.add(((BigDecimal)returnList.get(22)).add((BigDecimal)returnList.get(40)));//70
    returnList.add(((BigDecimal)returnList.get(23)).add((BigDecimal)returnList.get(41)));//71
    returnList.add(((BigDecimal)returnList.get(24)).add((BigDecimal)returnList.get(42)));//72
    returnList.add(((BigDecimal)returnList.get(25)).add((BigDecimal)returnList.get(43)));//73
    returnList.add(((BigDecimal)returnList.get(58)).add((BigDecimal)returnList.get(62)));//74
    returnList.add(((BigDecimal)returnList.get(26)).add((BigDecimal)returnList.get(44)));//75
    returnList.add(((BigDecimal)returnList.get(27)).add((BigDecimal)returnList.get(45)));//76
    returnList.add(((BigDecimal)returnList.get(28)).add((BigDecimal)returnList.get(46)));//77
    returnList.add(((BigDecimal)returnList.get(29)).add((BigDecimal)returnList.get(47)));//78
    returnList.add(((BigDecimal)returnList.get(30)).add((BigDecimal)returnList.get(48)));//79
    returnList.add(((BigDecimal)returnList.get(31)).add((BigDecimal)returnList.get(49)));//80
    returnList.add(((BigDecimal)returnList.get(32)).add((BigDecimal)returnList.get(50)));//81
    returnList.add(((BigDecimal)returnList.get(33)).add((BigDecimal)returnList.get(51)));//82
    returnList.add(((BigDecimal)returnList.get(34)).add((BigDecimal)returnList.get(52)));//83
    returnList.add(((BigDecimal)returnList.get(59)).add((BigDecimal)returnList.get(63)));//84
    returnList.add(((BigDecimal)returnList.get(60)).add((BigDecimal)returnList.get(64)));//85
    returnList.add(((BigDecimal)returnList.get(35)).add((BigDecimal)returnList.get(53)));//86
    returnList.add(((BigDecimal)returnList.get(61)).add((BigDecimal)returnList.get(65)));//87
    //总累计数
    returnList.add(((BigDecimal)returnList.get(0)).add((BigDecimal)returnList.get(66)));//88
    returnList.add(((BigDecimal)returnList.get(1)).add((BigDecimal)returnList.get(67)));//89
    returnList.add(((BigDecimal)returnList.get(2)).add((BigDecimal)returnList.get(68)));//90
    returnList.add(((BigDecimal)returnList.get(3)).add((BigDecimal)returnList.get(69)));//91
    returnList.add(((BigDecimal)returnList.get(4)).add((BigDecimal)returnList.get(70)));//92
    returnList.add(((BigDecimal)returnList.get(5)).add((BigDecimal)returnList.get(71)));//93
    returnList.add(((BigDecimal)returnList.get(6)).add((BigDecimal)returnList.get(72)));//94
    returnList.add(((BigDecimal)returnList.get(7)).add((BigDecimal)returnList.get(73)));//95
    returnList.add(((BigDecimal)returnList.get(54)).add((BigDecimal)returnList.get(74)));//96
    returnList.add(((BigDecimal)returnList.get(8)).add((BigDecimal)returnList.get(75)));//97
    returnList.add(((BigDecimal)returnList.get(9)).add((BigDecimal)returnList.get(76)));//98
    returnList.add(((BigDecimal)returnList.get(10)).add((BigDecimal)returnList.get(77)));//99
    returnList.add(((BigDecimal)returnList.get(11)).add((BigDecimal)returnList.get(78)));//100
    returnList.add(((BigDecimal)returnList.get(12)).add((BigDecimal)returnList.get(79)));//101
    returnList.add(((BigDecimal)returnList.get(13)).add((BigDecimal)returnList.get(80)));//102
    returnList.add(((BigDecimal)returnList.get(14)).add((BigDecimal)returnList.get(81)));//103
    returnList.add(((BigDecimal)returnList.get(15)).add((BigDecimal)returnList.get(82)));//104
    returnList.add(((BigDecimal)returnList.get(16)).add((BigDecimal)returnList.get(83)));//105
    returnList.add(((BigDecimal)returnList.get(55)).add((BigDecimal)returnList.get(84)));//106
    returnList.add(((BigDecimal)returnList.get(56)).add((BigDecimal)returnList.get(85)));//107
    returnList.add(((BigDecimal)returnList.get(17)).add((BigDecimal)returnList.get(86)));//108
    returnList.add(((BigDecimal)returnList.get(57)).add((BigDecimal)returnList.get(87)));//109
    returnList.add(queryTime.substring(0,4)+"年"+queryTime.substring(4,6)+"月");//110
    
    request.setAttribute("printList", returnList);
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_cumulativeinfo_find";
  }
  
}


