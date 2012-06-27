package org.xpup.hafmis.sysloan.loancallback.loancallback.action;

import java.io.File;
import java.io.InputStream;
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
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.OneTailDTO;
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.PalindromeImpSwitchHeadDTO;
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.TwoTailDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaImportAF;

public class LoancallbackTaImportAC extends Action {
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
    LoancallbackTaImportAF forms = (LoancallbackTaImportAF) form;
    ActionMessages messages = null;
    ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
        .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
    IPalindromeImpSwitchBS palindromeImpSwitchBS = (IPalindromeImpSwitchBS) BSUtils
        .getBusinessService("palindromeImpSwitchBS", this, mapping
            .getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        LoancallbackTaShowAC.PAGINATION_KEY);
    FormFile file = forms.getTheFile();
    String filename = file.getFileName();
    String fileType = file.getFileName().substring(
        file.getFileName().lastIndexOf(".") + 1);// ---------------此行新加
    if (filename.equals("")) { // 判断是否为空
      return mapping.findForward("impFail");
    } else if (!(filename.endsWith(".txt") || filename.endsWith(".xls"))) {// --------此行已变
      return mapping.findForward("impFail");
    } else if (filename.toString().length() < 1) {
      return (mapping.findForward("文件无后缀"));
    }
    InputStream stream = null;
    if ("txt".equals(fileType)) {// ---------------此行新加 (判断文件格式 txt)

      try {
        List headList = null;
        List infoList = null;
        stream = file.getInputStream();
        List importList = checkData(stream); // ********通过输入流获得信息
        LoancallbackTaImportDTO dto = new LoancallbackTaImportDTO();
        dto = (LoancallbackTaImportDTO) importList.get(0);
        String bankId = dto.getLoanBankId();
        headList = new ArrayList();
        headList.add(dto);
//        infoList = (List) importList.remove(0);//去除头信息
//        List list = palindromeImpSwitchBS.switchImpList(bankId, headList,
//            infoList, fileType);// 通过银行接口合并
        Integer headId = loancallbackBS.adCallbackBatch(importList, securityInfo);
        pagination.getQueryCriterions().put("headId", headId.toString());

      } catch (BusinessException b) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入失败！"
            + b.getMessage(), false));
        saveErrors(request, messages);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else { // ---------------此行新加 (判断文件格式 xsl)
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
//         int str = BusiTools.getBusiKey(bankId,BusiConst.TENNUMBER);
        String str = BusiTools.getBusiValue(Integer.parseInt(bankId),
            BusiConst.TENNUMBER);
        String newJasperSrc = context
            .getRealPath("/impXml/" + str + "_imp.xml");
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
        Integer headId = loancallbackBS.adCallbackBatch(list, securityInfo);
      } catch (Exception e) {// ---------------此行新加
        e.printStackTrace();
      }// ---------------此行新加
    }

    return mapping.findForward("loancallbackTaShowAC");
  }

  public List checkData(InputStream inputstream) throws Exception {
    // Session sess=ThreadLocalSessionManager.getSession();
    int line = 1;

    List result = new ArrayList();

    try {
      FileReader reader = new FileReader(inputstream);// ?

      reader.setDelimiter("|");
      List list = reader.getList();
      // System.out.println("---->" + list.get(0));
      // System.out.println("---->" + list.get(2));
      Iterator it = null;
      if (list != null) {
        it = list.iterator();
        while (it.hasNext()) {
          LoancallbackTaImportDTO dto = new LoancallbackTaImportDTO();
          List slist = (List) (it.next());
          // System.out.println("---->" + slist.size());
          if (line == 1) {
            if (slist != null) {
              if (slist.size() != 14) {
                // System.out.println("文件头信息可能存在问题！");
              }
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null) {
                dto.setBizDate(slist.get(0).toString().trim()); // 扣款日期
                dto.setLoanBankId(slist.get(1).toString().trim()); // 放银行号
                dto.setBizType(slist.get(2).toString().trim());// 业务类型
                // System.out.println("文件头信息：" + slist.get(0).toString().trim()
                // + "|" + slist.get(1).toString().trim() + "|"
                // + slist.get(2).toString().trim());
              }
            }
          } else {
            if (slist != null) {
              // log.info("有几列=" + slist.size());
              // System.out.println(slist.size());
              if (slist.size() != 14) {
                // System.out.println("信息不完整！！！");
              }
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null && slist.get(3) != null
                  && slist.get(4) != null && slist.get(5) != null
                  && slist.get(6) != null && slist.get(7) != null
                  && slist.get(8) != null && slist.get(9) != null
                  && slist.get(10) != null && slist.get(11) != null
                  && slist.get(12) != null && slist.get(13) != null) {
                dto.setLoanKouAcc(slist.get(0).toString().trim()); // 扣款帐号
                // System.out.println("扣款帐号" + slist.get(0).toString().trim());
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
              }
            }
          }
          result.add(dto);
          line++;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

}