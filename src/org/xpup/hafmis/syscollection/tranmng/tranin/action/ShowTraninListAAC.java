package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;

public class ShowTraninListAAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowTraninListAAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      /**
       * ·ÖÒ³
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      String report=request.getParameter("report");
      saveToken(request);
      TraninAF traninAF = new TraninAF();
      List list=null;
      String taninHeadIdAAC=request.getParameter("taninHeadIdAAC");
      if(taninHeadIdAAC!=null&&!taninHeadIdAAC.equals("")){
      pagination.getQueryCriterions().put("taninHeadIdAAC", taninHeadIdAAC);
      }  
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
            this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      if (report!=null&&!report.equals("")&&report.equals("print")) {
          String temp_taninHeadIdAAC=(String) request.getSession().getAttribute("taninHeadIdAAC");
          TraninVidAF traninVidAF = traninBS.print_sy(temp_taninHeadIdAAC,securityInfo);         
//        wuht
          String orgId = traninVidAF.getInOrgId();         
          ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
          .getBusinessService("loanDocNumDesignBS", this, mapping.getModuleConfig());
          String userName="";
          SecurityInfo securityInfo1=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
            try {
              String name = loanDocNumDesignBS .getNamePara();

              if (name.equals("1")) {
                userName = securityInfo.getUserName();
              } else {
                userName = securityInfo.getRealName();
              }

            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            String collectionBankId = "";
            String collectionBankId_out = "";
            String collectionBankName = "";
            String collectionBankName_out = "";
            String collectionBankAcc = "";
            String outorgId = traninVidAF.getOutOrgId();
            if (orgId != null && !orgId.equals("")) {
              collectionBankName = loanDocNumDesignBS.queryCollectionBankNameById(
                  orgId, securityInfo);
              collectionBankAcc = loanDocNumDesignBS.queryCollectionBankAccById(
                  orgId, securityInfo);
              Org inorg = traninBS.queryOrg_yg(new Integer(orgId));
              collectionBankId = inorg.getOrgInfo().getCollectionBankId();
              request.setAttribute("collectionBankId", collectionBankId);
              request.setAttribute("collectionBankName", collectionBankName);
              request.setAttribute("collectionBankAcc", collectionBankAcc);
            }
            if (outorgId != null && !outorgId.equals("")) {
              collectionBankName_out = loanDocNumDesignBS
                  .queryCollectionBankNameById(outorgId, securityInfo);
              Org outorg = traninBS.queryOrg_yg(new Integer(outorgId));
              collectionBankId_out = outorg.getOrgInfo().getCollectionBankId();
              request.setAttribute("collectionBankId_out", collectionBankId_out);
              request.setAttribute("collectionBankName_out", collectionBankName_out);
            }
            String bizDate = securityInfo.getUserInfo().getBizDate();
            request.setAttribute("userName", userName);
            request.setAttribute("bizDate", bizDate);
//          wuht
            TranInTail traninTail=(TranInTail)traninVidAF.getList().get(0);
            try {
              List lista=traninBS.tranoutTailReason(traninTail.getTranInHead().getTranOutHeadId().toString());
              traninVidAF.setLista(lista);
            } catch (Exception e) {
              e.printStackTrace();
            }
          request.setAttribute("traninVidAF", traninVidAF);
          request.setAttribute("URL", "showTraninListAAC.do");
          if(traninTail.getTranInHead().getTranOutOrgId()==null || traninTail.getTranInHead().getTranOutOrgId().equals("")){
              return mapping.findForward("tranin_tail_cell_yga");
          }else{
            if(traninVidAF.getList().size()==1){
              return mapping.findForward("tranin_tail_cell_yg");
            }else{
              return mapping.findForward("tranin_tail_cell");
            }
          }
      }else{
        traninAF = traninBS.findTraninListByCriterionsAAC(pagination,securityInfo);
        if(taninHeadIdAAC==null){
          taninHeadIdAAC=(String) pagination.getQueryCriterions().get("taninHeadIdAAC");
        }
        if (taninHeadIdAAC != null && !taninHeadIdAAC.equals("")) {
          pagination.getQueryCriterions().put("tranInHeadById", taninHeadIdAAC);
          Pagination pagination1 = traninBS.countTraninListAll(pagination,securityInfo);
          Integer traninPeople = (Integer) pagination1.getQueryCriterions()
              .get("traninPeople");
          BigDecimal sumBalanceAll = (BigDecimal) pagination1
              .getQueryCriterions().get("sumBalanceAll");
          BigDecimal sumInterestAll = (BigDecimal) pagination1
              .getQueryCriterions().get("sumInterestAll");
          BigDecimal sumTraninAll = (BigDecimal) pagination1
              .getQueryCriterions().get("sumTraninAll");
          traninAF.setTraninPeople(traninPeople.intValue());
          traninAF.setSumBalanceAll(sumBalanceAll);
          traninAF.setSumInterestAll(sumInterestAll);
          traninAF.setSumTraninAll(sumTraninAll);
        }
        request.setAttribute("traninAF", traninAF);
        request.getSession().setAttribute("taninHeadIdAAC", taninHeadIdAAC);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("tranin_AAC_List");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "tranInTail.empId", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
