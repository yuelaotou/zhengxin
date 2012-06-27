package org.xpup.hafmis.sysloan.dataready.bankpalindrome.business;

import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BankpalindromeDAO;
import org.xpup.hafmis.sysloan.common.dao.PalindromFormatHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Bankpalindrome;
import org.xpup.hafmis.sysloan.common.domain.entity.PalindromFormatHead;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.dataready.bankpalindrome.bsinterface.IBankpalindromeBS;
import org.xpup.hafmis.sysloan.dataready.bankpalindrome.form.BankpalindromeAF;

import java.io.*;
import java.math.BigDecimal;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
/**
 * 
 * @author yuqf
 *2007-12-20
 */
public class BankpalindromeBS implements IBankpalindromeBS {
  private PalindromFormatHeadDAO palindromFormatHeadDAO = null;// PL011

  private BankpalindromeDAO bankpalindromeDAO = null;// PL010 银行回文格式设置

  private PlOperateLogDAO plOperateLogDAO=null;//PL021 操作日志
  
  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setBankpalindromeDAO(BankpalindromeDAO bankpalindromeDAO) {
    this.bankpalindromeDAO = bankpalindromeDAO;
  }

  public void setPalindromFormatHeadDAO(
      PalindromFormatHeadDAO palindromFormatHeadDAO) {
    this.palindromFormatHeadDAO = palindromFormatHeadDAO;
  }

  public BankpalindromeAF queryRowNumByBank(SecurityInfo securityInfo,
      Pagination pagination) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BankpalindromeAF bankpalindromeAF = new BankpalindromeAF();
    String rowNum = "";
    Bankpalindrome bankpalindrome = null;
    String bankId = (String) pagination.getQueryCriterions().get("bankId");
    String value = (String) pagination.getQueryCriterions().get("key");
    if("value".equals(value)){
      if(bankId != null && !"".endsWith(bankId)){
    bankpalindrome = bankpalindromeDAO.queryById(new Integer(bankId));
      }
    if (bankpalindrome != null) {
      rowNum = bankpalindrome.getRowNum();
//      if (rowNum == null || "".equals(rowNum)) {
//        rowNum = "未查询到结果";
//      }
     }
    }
    bankpalindromeAF.setBankId(bankId);
    bankpalindromeAF.setRowNum(rowNum);
    return bankpalindromeAF;
  }

  public void insertRowNum(SecurityInfo securityInfo,String bankId, String rowNum) throws Exception,
      BusinessException , IOException, JDOMException{
    // TODO Auto-generated method stub
    Bankpalindrome bankpalindrome = null;
    Integer pkId = null;
    if (bankId != null && !"".equals(bankId)) {
      bankpalindrome = bankpalindromeDAO.queryById(new Integer(bankId));
      if (bankpalindrome != null) {// 有记录 更新
        bankpalindrome.setRowNum(rowNum);
//      插入日志PL021
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_BANKPALINDROME));
        plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
        plOperateLog.setOpBizId(new BigDecimal(bankId));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());

        plOperateLogDAO.insert(plOperateLog);
      } else {// 没有记录 插入
        bankpalindrome = new Bankpalindrome();
        bankpalindrome.setBankId(new Integer(bankId));
        if (rowNum != null) {
         bankpalindrome.setRowNum(rowNum);
        }
        pkId = (Integer)bankpalindromeDAO.insert(bankpalindrome);
//      插入日志PL021
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_BANKPALINDROME));
        plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        plOperateLog.setOpBizId(new BigDecimal(pkId.toString()));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());

        plOperateLogDAO.insert(plOperateLog);
      }
//      /**
//       * 生成XML文件
//       */
//      int intRowNum = Integer.parseInt(rowNum);
//      Element rootElement = new Element("root");//(根元素)所有的XML元素都是 Element 的实例。根元素也不例外：）
//      Document document = new Document(rootElement);//以根元素作为参数创建Document对象。一个Document只有一个根，即root元素。
//      rootElement.setAttribute("file", "1");
//      Element rootElement2 = new Element("file");//二级节点
//      rootElement.setContent(rootElement2);//将次节点添加到根节点上
//      Attribute attribute1 = new Attribute("name","OrgaddpayHeadImport");
//      Attribute attribute2 = new Attribute("col",rowNum);//5列
//      Attribute attribute3 = new Attribute("space","0");//距离空间
//      Attribute attribute4 = new Attribute("row","2");//距离空间
//      rootElement2.setAttribute(attribute1);//将属性添加到次节点上
//      rootElement2.setAttribute(attribute2);
//      rootElement2.setAttribute(attribute3);
//      rootElement2.setAttribute(attribute4);
//      
//      for(int i=0;i<intRowNum;i++){
//        Element rootElement3 = new Element("column");//三级节点
//        rootElement2.addContent(rootElement3);
//        Element rootElement4 = new Element("name");//四级节点
//        rootElement3.addContent(rootElement4);
//        rootElement4.addContent(BusiTools.getBusiValue(i+1, BusiConst.TENNUMBER));
//        Element rootElement4_ = new Element("typle");//平行的另一个四级节点
//        rootElement3.addContent(rootElement4_);
//        rootElement4_.addContent("java.lang.String");
//      }
//      XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
//      try {
////        xmlOut.output(document, System.out);
//        System.out.println("======"+bankId+".xml");
//        xmlOut.output(document, new FileWriter("/workspace/hafmis/src/org/xpup/hafmis/sysloan/dataready/bankpalindrome/business/"+bankId +".xml"));
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
    }
  }
  
  public void insertPlOperateLog(SecurityInfo securityInfo){
    
  }
}
