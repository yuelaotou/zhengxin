package org.xpup.hafmis.sysloan.loancallback.bankimports.action;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.FileReader;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.Factory;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.bsinterface.IPalindromeImpSwitchBS;
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.PalindromeImpSwitchHeadDTO;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BankExportsDTO;
import org.xpup.hafmis.sysloan.loancallback.bankimports.bsinterface.IBankImportsBS;
import org.xpup.hafmis.sysloan.loancallback.bankimports.form.BankImportsTaImportAF;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;

public class BankImportsTaImportAC extends Action {
  /*
   * Generated Methods
   */

  /**
   * Method execute
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    IPalindromeImpSwitchBS palindromeImpSwitchBS = (IPalindromeImpSwitchBS) BSUtils
        .getBusinessService("palindromeImpSwitchBS", this, mapping
            .getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        BankImportsTaShowAC.PAGINATION_KEY);
    IBankImportsBS bankImportsBS = (IBankImportsBS) BSUtils.getBusinessService(
        "bankImportsBS", this, mapping.getModuleConfig());
    BankImportsTaImportAF forms = (BankImportsTaImportAF) form;
    ActionMessages messages = null;
    FormFile file = forms.getTheFile();
    String filename = file.getFileName();
    String fileType = file.getFileName().substring(
        file.getFileName().lastIndexOf(".") + 1);
    if (filename != null && !"".equals(filename)) {
      if ("txt".equals(fileType)) {// 是TXT文件
        if (filename.equals("")) { // 判断是否为空
          return mapping.findForward("impFail1");
        } else if (!filename.endsWith(".txt")) {// 如果不是TXT文件
          return mapping.findForward("impFail2");
        } else if (filename.toString().length() < 1) {
          return (mapping.findForward("文件无后缀"));
        }
        InputStream stream = null;
        try {
          stream = file.getInputStream();
        } catch (Exception e) {
          e.printStackTrace();
        }
        FormFile file1 = forms.getTheFile();
        String filename1 = file1.getFileName();
        if (filename1.equals("")) { // 判断是否为空
          return mapping.findForward("impFail3");
        } else if (!filename1.endsWith(".txt")) {
          return mapping.findForward("impFail4");
        } else if (filename1.toString().length() < 1) {
          return (mapping.findForward("文件无后缀"));
        }
        InputStream stream1 = null;
        try {
          stream1 = file1.getInputStream();
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          List importList = null;
          // 从权限中取得是以中心为主还是以银行为主。以此来判断使用哪种导入文件。
          // 贷款还款类型1.中心为主2.银行为主
          int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
          // 根据权限中的还款类型判断以哪为主
          String plLoanReturnType = "";
          String paramValue = "";// 得到扣款方式的参数
          String loanBankId = "";// 放款银行
          if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER) {
            plLoanReturnType = "1";// 中心为主
            // 取得扣款方式
            loanBankId = getLoanBankId(stream1);
            pagination.getQueryCriterions().put("loanBankId", loanBankId);
            paramValue = bankImportsBS.getBackMode(loanBankId);
            if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
              // 足额扣款
              importList = checkDataByCenterFull(stream); // ********通过输入流获得信息
            } else {
              // 全额扣款
              importList = checkDataByCenterAll(stream); // ********通过输入流获得信息
            }
            // 更新流水表

            bankImportsBS.updateBankImportsInfo(importList, securityInfo);
          } else if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK) {
            plLoanReturnType = "2";// 银行为主
            importList = checkDataByBank(stream, mapping, filename); // ********通过输入流获得信息
            // 插入流水表
            loanBankId = bankImportsBS.addBankImportsInfo(importList,
                securityInfo);
            pagination.getQueryCriterions().put("loanBankId", loanBankId);
          }
        } catch (BusinessException b) {
          // b.printStackTrace();
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
              .getMessage(), false));
          saveErrors(request, messages);
        } catch (Exception e) {
          // e.printStackTrace();
        }
      } else {// 是XLS文件
        ServletContext context = request.getSession().getServletContext();// ---------------此行新加
        String jasperSrc = context.getRealPath("/impXml/"
            + "palindromeImpSwitchHead_imp.xml");// ---------------此行新加
        // 银行.xml DTO
        Factory faxtory = new Factory();// ---------------此行新加
        File xmlfile = new File(jasperSrc);// ---------------此行新加
        Map info = null;// ---------------此行新加
        try {// ---------------此行新加
          info = faxtory.getInfomation(xmlfile, file.getInputStream(),
              "org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.");// ---------------此行新加
          List palindromeImpSwitchHead = new ArrayList();// ---------------此行新加
          palindromeImpSwitchHead = (List) info.get("PalindromeImpSwitchHead");// ---------------此行新加
          // 头信息

          PalindromeImpSwitchHeadDTO palindromeImpSwitchHeadDTO = (PalindromeImpSwitchHeadDTO) palindromeImpSwitchHead
              .get(1);
          String bankId = palindromeImpSwitchHeadDTO.getBankId().trim();
          /**
           * BusiTools.getBusiValue(i+1, BusiConst.TENNUMBER) bankId+"_imp.xml"
           * Tail
           */
          // int str = BusiTools.getBusiKey(bankId,BusiConst.TENNUMBER);
          String str = BusiTools.getBusiValue(Integer.parseInt(bankId),
              BusiConst.TENNUMBER);
          String newJasperSrc = context.getRealPath("/impXml/" + str
              + "_imp.xml");
          Factory newFaxtory = new Factory();
          File newXmlfile = new File(newJasperSrc);
          Map newInfo = null;

          newInfo = newFaxtory.getInfomation(newXmlfile, file.getInputStream(),
              "org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.");
          List headList = new ArrayList();
          headList = (List) newInfo.get("PalindromeImpSwitchHead");
          PalindromeImpSwitchHeadDTO palindromeImpSwitchHeadT = (PalindromeImpSwitchHeadDTO) headList
              .get(1);
          List infoList = new ArrayList();
          infoList = (List) newInfo.get(str + "Tail");
          // if("One".equals(str)){
          // OneTailDTO oneTailDTO = new OneTailDTO();
          // System.out.println("--------"+infoList.size());
          // oneTailDTO = (OneTailDTO)infoList.get(1);
          // String emocond = oneTailDTO.getEmpcode();
          // System.out.println("======:"+emocond);
          // }else if("Two".equals(str)){
          // TwoTailDTO twoTailDTO = new TwoTailDTO();
          // twoTailDTO = (TwoTailDTO)infoList.get(1);
          // String emocond = twoTailDTO.getOne();
          // System.out.println("======:"+emocond);
          // }

          List list = palindromeImpSwitchBS.switchImpList(bankId, headList,
              infoList, fileType);
          bankImportsBS.addBankImportsInfo(list, securityInfo);
        } catch (Exception e) {// ---------------此行新加
          e.printStackTrace();
        }// ---------------此行新加
      }
    }
    return mapping.findForward("bankImportsTaShowAC");
  }

  /**
   * 得到银行代码
   * 
   * @param inputstream
   * @return
   */
  public String getLoanBankId(InputStream inputstream) throws Exception {
    String loanBankId = "";
    try {
      InputStreamReader isr = new InputStreamReader(inputstream, "ISO-8859-1");
      FileReader reader = new FileReader(isr);
      String str = "";
      while ((str = reader.readLine()) != null && !"".equals(str.trim())) {
        String[] info = str.split("\\|");
        return info[1].toString();
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("导入失败");
    }
    return loanBankId;
  }

  /**
   * 得到以中心为主导入文件的数据(足额扣款)
   * 
   * @param inputstream
   * @return
   * @throws Exception
   */
  public List checkDataByCenterFull(InputStream inputstream) throws Exception {
    int line = 1;
    List result = new ArrayList();
    try {
      InputStreamReader isr = new InputStreamReader(inputstream, "ISO-8859-1");
      FileReader reader = new FileReader(isr);
      String str = "";
      while ((str = reader.readLine()) != null && !"".equals(str.trim())) {
        String[] info = str.split("\\|");
        System.out.println(info[0]+"=============>");
        System.out.println(info[1]+"=============>");
        //System.out.println(info[2]+"=============>");
        BankExportsDTO dto = new BankExportsDTO();
        if (line == 1) {
          dto.setBizDate(info[0].trim()); // 扣款日期
          dto.setLoanBankId(info[1].trim()); // 放银行号
          if(info.length>2){
            dto.setBatchNum(info[2].trim());// 批次号
          }
          
        } else {
          dto.setLoanKouAcc(info[0].trim()); // 扣款帐号
          dto.setBorrowerName(info[1].trim()); // 借款人姓名
          dto.setLoanKouYearmonth(info[2].trim().substring(0,4)+info[2].trim().substring(5,7)); // 还款年月
          dto.setShouldMoney(new BigDecimal(info[3].trim()));// 应扣金额
          dto.setIdentifier(info[4].trim());// 扣款标识
        }
        result.add(dto);
        line++;
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("导入失败");
    }
    return result;
  }

  /**
   * 得到以中心为主的导入文件的数据(全额扣款)
   * 
   * @param inputstream
   * @return
   * @throws Exception
   */
  public List checkDataByCenterAll(InputStream inputstream) throws Exception {
    int line = 1;
    List result = new ArrayList();
    try {
      FileReader reader = new FileReader(inputstream);
      reader.setDelimiter("|");
      List list = reader.getList();
      Iterator it = null;
      if (list != null) {
        it = list.iterator();
        while (it.hasNext()) {
          BankExportsDTO dto = new BankExportsDTO();
          List slist = (List) (it.next());
          if (line == 1) {
            if (slist != null) {
              if (slist.size() != 14) {
              }
              if (slist.get(0) != null && slist.get(1) != null) {
                dto.setBizDate(slist.get(0).toString().trim()); // 扣款日期
                dto.setLoanBankId(slist.get(1).toString().trim()); // 放银行号
                if (slist.get(2) != null) {
                  dto.setBatchNum(slist.get(2).toString().trim());// 批次号
                }
              }
            }
          } else {
            if (slist != null) {
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null && slist.get(3) != null) {
                dto.setLoanKouAcc(slist.get(0).toString().trim()); // 扣款帐号
                dto.setBorrowerName(slist.get(1).toString().trim()); // 借款人姓名
                dto.setShouldMoney(new BigDecimal(slist.get(2).toString()
                    .trim())); // 应扣金额
                dto
                    .setRealMoney(new BigDecimal(slist.get(3).toString().trim()));// 实扣金额
              } else if (slist.get(3) == null) {
                throw new BusinessException("导入金额不能为空!");
              }
            }
          }
          result.add(dto);
          line++;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("导入失败");
    }
    return result;
  }

  /**
   * 得到以银行为主的导入文件的数据
   * 
   * @param inputstream
   * @return
   * @throws Exception
   */
  public List checkDataByBank(InputStream inputstream, ActionMapping mapping,
      String type) throws Exception {
    int line = 1;
    List result = new ArrayList();

    try {
      FileReader reader = new FileReader(inputstream);
      reader.setDelimiter("|");
      List list = reader.getList();
      Iterator it = null;
      if (list != null) {
        it = list.iterator();
        while (it.hasNext()) {
          LoancallbackTaImportDTO dto = new LoancallbackTaImportDTO();
          List slist = (List) (it.next());
          if (line == 1) {
            if (slist != null) {
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null) {
                dto.setBizDate(slist.get(0).toString().trim()); // 扣款日期
                dto.setLoanBankId(slist.get(1).toString().trim()); // 放银行号
                dto.setBizType(slist.get(2).toString().trim());// 业务类型
                if (slist.get(3) != null) {
                  dto.setBatchNum(slist.get(3).toString().trim());// 批次号
                }
              }
            }
          } else {
            if (slist != null) {
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null && slist.get(3) != null
                  && slist.get(4) != null && slist.get(5) != null
                  && slist.get(6) != null && slist.get(7) != null
                  && slist.get(8) != null && slist.get(9) != null
                  && slist.get(10) != null && slist.get(11) != null
                  && slist.get(12) != null) {
                dto.setLoanKouAcc(slist.get(0).toString().trim()); // 扣款帐号
                dto.setBorrowerName(slist.get(1).toString().trim()); // 借款人姓名
                dto.setYearMonth(slist.get(2).toString().trim()); // 还款年月
                dto.setRealCorpus(slist.get(3).toString().trim());// 实扣正常本金
                dto.setRealOverdueCorpus(slist.get(4).toString().trim());// 实扣逾期本金
                dto.setRealInterest(slist.get(5).toString().trim());// 实扣正常利息
                dto.setRealOverdueInterest(slist.get(6).toString().trim());// 实扣逾期利息
                dto.setRealPunishInterest(slist.get(7).toString().trim());// 实扣罚息
                dto.setNobackCorpus(slist.get(8).toString().trim());// 未还正常本金
                dto.setNobackOverdueCorpus(slist.get(9).toString().trim());// 未还逾期本金
                dto.setNobackInterest(slist.get(10).toString().trim());// 未还正常利息
                dto.setNobackOverdueInterest(slist.get(11).toString().trim());// 未还逾期利息
                dto.setNobackPunishInterest(slist.get(12).toString().trim());// 未还罚息
                dto.setDeadLine(slist.get(13).toString().trim());// 提前还款后剩余期限
                if (slist.get(14) != null) {
                  dto.setBatchNum(slist.get(14).toString().trim());// 批次号
                }
              }
            }
          }
          result.add(dto);
          line++;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("导入失败银行为主");
    }
    return result;
  }

}