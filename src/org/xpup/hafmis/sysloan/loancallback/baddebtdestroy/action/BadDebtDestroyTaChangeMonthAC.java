package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action; 


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.cglib.core.Converter;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface.IBadDebtDestroyBS;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTaAFDTO;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.form.BadDebtDestroyTaAF;

import sun.io.ByteToCharConverter;
import sun.io.MalformedInputException;

public class BadDebtDestroyTaChangeMonthAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
      request.setCharacterEncoding("UTF-8");
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String month = request.getParameter("month");
      String contractId = request.getParameter("contractId");
      String orgType = request.getParameter("orgType");
      String assistantOrgId ="";
      //assistantOrgId=new   String(request.getParameter("assistantOrgId").getBytes("iso-8859-1"),"UTF-8"); 
      assistantOrgId=(String)request.getParameter("assistantOrgId"); 
      Pagination pagination = getPagination(BadDebtDestroyTaShowAC.PAGINATION_KEY, request);
      if(month != null){
        pagination.getQueryCriterions().put("callbackMonth",month);
      }
      if(contractId!= null){
        pagination.getQueryCriterions().put("contractId", contractId);
      }
      //进入页面是要带权限的，从权限中取得是以中心为主还是以银行为主。以此来控制按钮灰显，要在action中设置状态。
      //贷款还款类型1.中心为主2.银行为主
      int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
      //根据权限中的还款类型判断以哪为主
      String plLoanReturnType = "";
      if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER){
        plLoanReturnType = "1";//中心为主
      }else if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK){
        plLoanReturnType = "2";//银行为主
      }
      BadDebtDestroyTaAFDTO afdto = new BadDebtDestroyTaAFDTO(); 
      IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
      .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
      afdto = badDebtDestroyBS.findShouldLoancallbackInfo(pagination, securityInfo);
      BadDebtDestroyTaAF af = new BadDebtDestroyTaAF(); 
      af.setShouldBackList(afdto.getShouldBackList());
      af.setBorrowerInfoDTO(afdto.getBorrowerInfoDTO());
      af.setSumCorpus(afdto.getSumCorpus());
      af.setSumInterest(afdto.getSumInterest());
      af.setSumMoney(afdto.getSumMoney());
      af.setRealMoney(afdto.getRealMoney());
      af.setMonthYear(afdto.getMonthYear());
      af.setOrgType(orgType);
      af.setAssistantOrgId(assistantOrgId);
      if(orgType!=null&&!"".equals(orgType)&&"2".equals(orgType)){
        request.getSession().setAttribute("orgName", "1");
      }
      request.setAttribute("badDebtDestroyTaAF", af);
      request.setAttribute("plLoanReturnType", String.valueOf(plLoanReturnType));
      pagination.getQueryCriterions().put("shouldBackList",af.getShouldBackList());
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return mapping.findForward("baddebtdestroy_jy");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination();
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
  public   char   []   byteToChar(byte   b[]) throws UnsupportedEncodingException, MalformedInputException   {   
    //设置编码方式   
    String   encoding="gb2312";   
    //byte   b[]={(byte)'\u00c4'，(byte)'\u00E3'};//去掉前面注释可调试。   
    //用转换器来将字节码转换为字符。   
    Converter   converter   =   (Converter) ByteToCharConverter.getConverter(encoding);   
    //convertAll方法实现转换。   
    char   []   converteredCharArray=((ByteToCharConverter) converter).convertAll(b);   
    //返回字符数组。   
    return   converteredCharArray;   
    }   

}