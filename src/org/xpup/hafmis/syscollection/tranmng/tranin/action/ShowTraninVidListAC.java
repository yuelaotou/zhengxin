package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninStayAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * shiyan
 */
public class ShowTraninVidListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowTraninVidListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      List list = null;
      TraninVidAF traninVidAF = (TraninVidAF) form;         
      if(!pagination.getQueryCriterions().get("tranStatus").equals("13")){   
      pagination.getQueryCriterions().put("tranStatus",traninVidAF.getTranStatus());
      }
      // 显示业务状态
      Map stateMap = BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      
      list = traninBS.queryTraninVid_sy(pagination,securityInfo);
      
      List printlist = traninBS.queryTraninVid_sy_yg(pagination,securityInfo);
      String collBankId = (String) pagination.getQueryCriterions()
      .get("collBankId"); 
      String collBankName = "";
      if(collBankId!=null){
        collBankName = tranoutBS.findCollBank(collBankId);        
      }
      request.getSession().setAttribute("collBankName_yg", collBankName);
      
      request.getSession().setAttribute("printlist_yg",printlist);
      traninVidAF.setStateMap(stateMap);
      traninVidAF.setList(list);
      String count = new Integer(pagination.getNrOfElements()).toString();
      BigDecimal sumTranInAmount = (BigDecimal) pagination.getQueryCriterions()
          .get("sumTranInAmount");
      String sumCount = (String) pagination.getQueryCriterions()
          .get("sumCount");
      traninVidAF.setSumCount(sumCount);
      traninVidAF.setSumTranInAmount(sumTranInAmount);
      traninVidAF.setCount(count);
      //清空查询条件
      traninVidAF.setDocNum("");
      traninVidAF.setInOrgId("");
      traninVidAF.setInOrgName("");
      traninVidAF.setNoteNum("");
      traninVidAF.setOutOrgId("");
      traninVidAF.setOutOrgName("");
      traninVidAF.setSettDate("");
      traninVidAF.setSettDatea("");
      traninVidAF.setTranStatus("");
      traninVidAF.setCollBankId("");
      request.setAttribute("traninVidAF", traninVidAF);
      List collBankList1 = null;
      try {
        // 取出用户下归集行
        List collBankList = securityInfo.getCollBankList();
        collBankList1 = new ArrayList();
        Userslogincollbank userslogincollbank = null;
        Iterator itr2 = collBankList.iterator();
        while (itr2.hasNext()) {
          userslogincollbank = (Userslogincollbank) itr2.next();
          collBankList1.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName().toString(), userslogincollbank
                  .getCollBankId().toString()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      request.setAttribute("collBankList1", collBankList1);
//    20071213
      //判断是单位版还是中心版
      String isType=securityInfo.getIsOrgEdition()+"";
      request.getSession().setAttribute("isorgOrCenter", isType);
      traninVidAF.reset(mapping, request);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_tranin_vid_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "id", "DESC", new HashMap(0));
      pagination.getQueryCriterions().put("tranStatus", "13");
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
