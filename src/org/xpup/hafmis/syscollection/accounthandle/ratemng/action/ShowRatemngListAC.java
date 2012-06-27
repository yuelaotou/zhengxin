package org.xpup.hafmis.syscollection.accounthandle.ratemng.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.bsinterface.IRatemngBS;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.form.RatemngAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAF;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateShowAF;

/**
 * shiyan
 */
public class ShowRatemngListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowRatemngListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      RateShowAF rateShowAF=new RateShowAF();
      IRatemngBS ratemngBS = (IRatemngBS) BSUtils.getBusinessService("ratemngBS",
          this, mapping.getModuleConfig());
      List list=ratemngBS.findRatemngList_sy(pagination);
      //以下程序是判断是否启用按钮好用。
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      List temp_list=securityInfo.getAllOfficeList();
      List temp_officecode=new ArrayList();
      OfficeDto officeDto=null;
      String info="";
      String loadsMassage="";
      //确定有几个没有启用
      if(!temp_list.isEmpty()){
        for(int i=0;i<temp_list.size();i++){
          officeDto=(OfficeDto)temp_list.get(i);
          String officecode=officeDto.getOfficeCode();
          info=ratemngBS.checkOfficeCode(officecode);
          if(!info.equals("")&&info.equals("nohi")){
            temp_officecode.add(info);
          }
        }
      }
      //办事处个数和没启用个数相等的时候启用按钮可用
      if(temp_list.size()==temp_officecode.size()){
        loadsMassage="hi";
      }
      RatemngAF ratemngAF=new RatemngAF();
      ratemngAF.setLoadsMassage(loadsMassage);
      ratemngAF.setList(list);
      List officeList = securityInfo.getAllOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
      }
      request.getSession(true).setAttribute("officeList1", officeList1);
      ratemngAF.setOfficecode("");
      rateShowAF.setRatetypemap(BusiTools.listBusiProperty(BusiConst.CHGTYPESTATUS));
      request.setAttribute("ratemngAF", ratemngAF);
      request.setAttribute("rateShowAF", rateShowAF);
      ratemngAF.reset(mapping, request);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_ratemng_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "hafInterestRate.bizDate", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
