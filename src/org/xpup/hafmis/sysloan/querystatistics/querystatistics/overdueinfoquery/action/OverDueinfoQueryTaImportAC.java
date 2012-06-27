package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.action;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaImportAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.bsinterface.IOverDueinfoQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryImportDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.form.OverDueinfoQueryTaImportAF;

public class OverDueinfoQueryTaImportAC extends Action {
  /*
   * Generated Methods
   */

  /**
   * Method execute
   * jj 2007-10-29
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    OverDueinfoQueryTaImportAF forms = (OverDueinfoQueryTaImportAF) form;
    ActionMessages messages = null;
    FormFile file = forms.getTheFile();
    String filename = file.getFileName();
    if (filename.equals("")) { // 判断是否为空
      return mapping.findForward("impFail");
    } else if (!filename.endsWith(".txt")) {
      return mapping.findForward("impFail");
    } else if (filename.toString().length() < 1) {
      return (mapping.findForward("文件无后缀"));
    }
    InputStream stream = null;
    try {
      stream = file.getInputStream();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Pagination pagination=(Pagination)request.getSession().getAttribute(OverDueinfoQueryShowListAC.PAGINATION_KEY);
      IOverDueinfoQueryBS overDueinfoQueryBS = (IOverDueinfoQueryBS) BSUtils.getBusinessService(
          "overDueinfoQueryBS", this, mapping.getModuleConfig());
      List importList = checkData(stream); // ********通过输入流获得信息
      overDueinfoQueryBS.importOverdueData(importList, securityInfo);
    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入失败！"
          + b.getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("overDueinfoQueryFindAC");
  }

  public List checkData(InputStream inputstream) throws Exception {
    int line = 1;
    List result = new ArrayList();
    try {
      FileReader reader = new FileReader(inputstream);// ?
      reader.setDelimiter("|");
      List list = reader.getList();
      Iterator it = null;
      if (list != null) {
        it = list.iterator();
        while (it.hasNext()) {
          OverDueinfoQueryImportDTO dto = new OverDueinfoQueryImportDTO();
          List slist = (List) (it.next());
          if (line == 1) {
            if (slist != null) {
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null) {
                 dto.setBizDate(slist.get(0).toString().trim()); //扣款日期
                 dto.setLoanBankId(slist.get(1).toString().trim()); //放银行号
              }
            }
          } else {
            if (slist != null) {
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null && slist.get(3) != null
                  && slist.get(4) != null&& slist.get(5) != null
                  && slist.get(6) != null) {
                 dto.setLoanKouAcc(slist.get(0).toString().trim()); //扣款帐号
                 dto.setBorrowerName(slist.get(1).toString().trim()); //借款人姓名
                 dto.setNobackCorpus(slist.get(2).toString().trim());//未还本金
                 dto.setNobackInterest(slist.get(3).toString().trim());//未还利息
                 dto.setNobackPunishInterest(slist.get(4).toString().trim());//未还罚息
                 dto.setMonthsCount(slist.get(5).toString().trim());//逾期月数
                 dto.setCorpusInterest(slist.get(6).toString().trim());//月还本息
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