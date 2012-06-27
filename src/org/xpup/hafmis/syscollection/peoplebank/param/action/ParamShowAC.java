package org.xpup.hafmis.syscollection.peoplebank.param.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.peoplebank.param.bsinterface.IParamBS;
import org.xpup.hafmis.syscollection.peoplebank.param.dto.ParamDTO;
import org.xpup.hafmis.syscollection.peoplebank.param.form.ParamAF;

public class ParamShowAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      ParamAF paramAF=new ParamAF();
      String settingType="";
//      String settingType="";
//      if(request.getParameter("settingType")!=null){
//        settingType=(String)request.getParameter("settingType");
//      }
      IParamBS paramBS = (IParamBS) BSUtils
      .getBusinessService("paramBS", this, mapping.getModuleConfig());
      ParamDTO paramDTO=null;
      paramDTO=paramBS.findParamInfo();
      if(paramDTO!=null){
        paramAF.setParamDTO(paramDTO);
      }
      if(paramDTO.getSettingType()!=null){
        settingType=paramDTO.getSettingType();
      }
      request.setAttribute("paramAF", paramAF);
      request.setAttribute("settingType", settingType);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_param_show");
  }
}
