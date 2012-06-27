package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

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
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinTaAF;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;

/**
 * 显示凭证录入页的Action
 * 
 * @author 刘冰
 */
public class CredenceFillinTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action.CredenceFillinTaShowdAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    CredenceFillinTaAF credenceFillinTaAF = new CredenceFillinTaAF();
    ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
        .getBusinessService("credenceFillinBS", this, mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    int settleType = securityInfo.getFnSettleType();
    List officeList = null;
    List credenceCharacterList = null;
    List settTypeList = null;
    try { // 根据当前用户得到其权限办事处

      List temp_officeList = securityInfo.getOfficeList();
      officeList = new ArrayList();
      OfficeDto officedto = null;
      officedto = (OfficeDto) temp_officeList.get(0);
      String bookId = securityInfo.getBookId();
      String officeCode = "";
      
      if (settleType == 0) {
        officeCode = null;
      } else if (settleType == 1) {
        officeCode = officedto.getOfficeCode();
      }
      String credenceDate = credenceFillinBS.getCredenceDate(officeCode, securityInfo);
      String credenceNum = credenceFillinBS.getCredenceNum(officeCode,
          credenceDate.substring(0, 6), "0", bookId);
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setChargeUpDate(
          credenceDate);
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setCredenceNum(
          credenceNum);
      Iterator it = temp_officeList.iterator();
      while (it.hasNext()) {
        officedto = (OfficeDto) it.next();
        officeList.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      // 调出凭证字下拉选框项

      List temp_credenceCharacterList = credenceFillinBS
          .findCredenceCharacterList(securityInfo);
      credenceCharacterList = new ArrayList();
      if (temp_credenceCharacterList.size() > 0) {
        BookParameterDTO bookParameterDTO = null;
        Iterator itr1 = temp_credenceCharacterList.iterator();
        while (itr1.hasNext()) {
          bookParameterDTO = (BookParameterDTO) itr1.next();
          credenceCharacterList.add(new org.apache.struts.util.LabelValueBean(
              bookParameterDTO.getBookParameterName(), bookParameterDTO
                  .getBookParameterId()));
        }
      }
      // 调出结算方式下拉选框项

      List temp_settTypeList = credenceFillinBS.findSettTypeList(securityInfo);
      settTypeList = new ArrayList();
      if (temp_settTypeList.size() > 0) {
        BookParameterDTO bookParameterDTO = null;
        Iterator itr1 = temp_settTypeList.iterator();
        while (itr1.hasNext()) {
          bookParameterDTO = (BookParameterDTO) itr1.next();
          settTypeList.add(new org.apache.struts.util.LabelValueBean(
              bookParameterDTO.getBookParameterName(), bookParameterDTO
                  .getBookParameterId()));
        }
      }
      String bookSt = credenceFillinBS.findBookSt(securityInfo);
      if (!bookSt.equals("")) {
        credenceFillinTaAF.setBookSt(bookSt);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // 调出当前操作员的名字
    credenceFillinTaAF.getCredenceFillinTaShowDTO().setMakebill(
        securityInfo.getUserName());
    credenceFillinTaAF.getCredenceFillinTaShowDTO().setBookId(
        securityInfo.getBookId());
    request.setAttribute("credenceFillinTaAF", credenceFillinTaAF);
    request.setAttribute("officeList", officeList);
    request.setAttribute("credenceCharacterList", credenceCharacterList);
    request.setAttribute("settTypeList", settTypeList);
    return mapping.findForward("to_credencefillinta_show");

  }
}
