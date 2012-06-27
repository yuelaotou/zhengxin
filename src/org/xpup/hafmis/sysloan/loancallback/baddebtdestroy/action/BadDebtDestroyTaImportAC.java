package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action;

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
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface.IBadDebtDestroyBS;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.form.BadDebtDestroyTaImportAF;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaImportAF;

public class BadDebtDestroyTaImportAC extends Action {
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
    BadDebtDestroyTaImportAF forms = (BadDebtDestroyTaImportAF) form;
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
      Pagination pagination=(Pagination)request.getSession().getAttribute(BadDebtDestroyTaShowAC.PAGINATION_KEY);
      IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
      .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
      List importList = checkData(stream); // ********通过输入流获得信息
      Integer headId = badDebtDestroyBS.adCallbackBatch(importList, securityInfo);
      pagination.getQueryCriterions().put("headId", headId.toString());
    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入失败！"
          + b.getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("badDebtDestroyTaShowAC");
  }

  public List checkData(InputStream inputstream) throws Exception {
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
                 dto.setBizDate(slist.get(0).toString().trim()); //扣款日期
                 dto.setLoanBankId(slist.get(1).toString().trim()); //放银行号
                 dto.setBizType(slist.get(2).toString().trim());//业务类型
              }
            }
          } else {
            if (slist != null) {
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null && slist.get(3) != null
                  && slist.get(4) != null&& slist.get(5) != null
                  && slist.get(6) != null && slist.get(7) != null
                  && slist.get(8) != null&& slist.get(9) != null
                  && slist.get(10) != null && slist.get(11) != null
                  && slist.get(12) != null&& slist.get(13) != null) {
                 dto.setLoanKouAcc(slist.get(0).toString().trim()); //扣款帐号
                 dto.setBorrowerName(slist.get(1).toString().trim()); //借款人姓名
                 dto.setYearMonth(slist.get(2).toString().trim()); //还款年月
                 dto.setRealCorpus(slist.get(3).toString().trim());//实扣正常本金
                 dto.setRealOverdueCorpus(slist.get(4).toString().trim());//实扣逾期本金
                 dto.setRealInterest(slist.get(5).toString().trim());//实扣正常利息
                 dto.setRealOverdueInterest(slist.get(6).toString().trim());//实扣逾期利息
                 dto.setRealPunishInterest(slist.get(7).toString().trim());//实扣罚息
                 dto.setNobackCorpus(slist.get(8).toString().trim());//未还正常本金
                 dto.setNobackOverdueCorpus(slist.get(9).toString().trim());//未还逾期本金
                 dto.setNobackInterest(slist.get(10).toString().trim());//未还正常利息
                 dto.setNobackOverdueInterest(slist.get(11).toString().trim());//未还逾期利息
                 dto.setNobackPunishInterest(slist.get(12).toString().trim());//未还罚息
                 dto.setDeadLine(slist.get(13).toString().trim());//提前还款后剩余期限
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