package org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.bsinterface.ICollectionuseinfoFindBS;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.dto.DefineFormulaDto;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.form.DefineFormulaFindAF;

public class DefineFormulaSaveAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      DefineFormulaFindAF defineFormulaFindAF = (DefineFormulaFindAF) (form);
      DefineFormulaDto defineFormulaDto = defineFormulaFindAF
          .getDefineFormulaDto();
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICollectionuseinfoFindBS collectionuseinfoFindBS = (ICollectionuseinfoFindBS) BSUtils
          .getBusinessService("collectionuseinfoFindBS", this, mapping
              .getModuleConfig());
      
      String returnString1="";
      String returnString2="";
      ActionMessages messages = null;
      List addList=new ArrayList();
      List subList=new ArrayList();
      
      addList=new ArrayList();
      subList=new ArrayList();
      String code_201=defineFormulaDto.getCode_201();
      String Ncode_201[]=code_201.split("\\+");
      for(int i=0;i<Ncode_201.length;i++){
        if(Ncode_201[i].indexOf("-")>0){
          String Mcode_201[]=Ncode_201[i].split("\\-");
          addList.add(Mcode_201[0]);
          for(int j=1;j<Mcode_201.length;j++){
            subList.add(Mcode_201[j]);
          }
        }else{
          addList.add(Ncode_201[i]);
        }
      }
      for(int k=0;k<addList.size();k++){
        returnString1=collectionuseinfoFindBS.is_CodeIn_WL(addList.get(k).toString().trim(), "0", securityInfo);
        if(returnString1==null || returnString1.equals("")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,201中:"+addList.get(k).toString().trim()+"不是正确科目代码，请检查！", false));
          saveErrors(request, messages);
          return mapping.findForward("defineFormula");
        }
      }
      if(subList.size()!=0){
        for(int j=0;j<subList.size();j++){
          returnString2=collectionuseinfoFindBS.is_CodeIn_WL(subList.get(j).toString().trim(), "0", securityInfo);
          if(returnString2==null || returnString2.equals("")){
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,201中:"+subList.get(j).toString().trim()+"不是正确科目代码，请检查！", false));
            saveErrors(request, messages);
            return mapping.findForward("defineFormula");
          }
        }
      }
      
      addList=new ArrayList();
      subList=new ArrayList();
      String code_202=defineFormulaDto.getCode_202();
      String Ncode_202[]=code_202.split("\\+");
      for(int i=0;i<Ncode_202.length;i++){
        if(Ncode_202[i].indexOf("-")>0){
          String Mcode_202[]=Ncode_202[i].split("\\-");
          addList.add(Mcode_202[0]);
          for(int j=1;j<Mcode_202.length;j++){
            subList.add(Mcode_202[j]);
          }
        }else{
          addList.add(Ncode_202[i]);
        }
      }
      for(int k=0;k<addList.size();k++){
        returnString1=collectionuseinfoFindBS.is_CodeIn_WL(addList.get(k).toString().trim(), "0", securityInfo);
        if(returnString1==null || returnString1.equals("")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,202中:"+addList.get(k).toString().trim()+"不是正确科目代码，请检查！", false));
          saveErrors(request, messages);
          return mapping.findForward("defineFormula");
        }
      }
      if(subList.size()!=0){
        for(int j=0;j<subList.size();j++){
          returnString2=collectionuseinfoFindBS.is_CodeIn_WL(subList.get(j).toString().trim(), "0", securityInfo);
          if(returnString2==null || returnString2.equals("")){
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,202中:"+subList.get(j).toString().trim()+"不是正确科目代码，请检查！", false));
            saveErrors(request, messages);
            return mapping.findForward("defineFormula");
          }
        }
      }
      
      addList=new ArrayList();
      subList=new ArrayList();
      String code_203=defineFormulaDto.getCode_203();
      String Ncode_203[]=code_203.split("\\+");
      for(int i=0;i<Ncode_203.length;i++){
        if(Ncode_203[i].indexOf("-")>0){
          String Mcode_203[]=Ncode_203[i].split("\\-");
          addList.add(Mcode_203[0]);
          for(int j=1;j<Mcode_203.length;j++){
            subList.add(Mcode_203[j]);
          }
        }else{
          addList.add(Ncode_203[i]);
        }
      }
      for(int k=0;k<addList.size();k++){
        returnString1=collectionuseinfoFindBS.is_CodeIn_WL(addList.get(k).toString().trim(), "0", securityInfo);
        if(returnString1==null || returnString1.equals("")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,203中:"+addList.get(k).toString().trim()+"不是正确科目代码，请检查！", false));
          saveErrors(request, messages);
          return mapping.findForward("defineFormula");
        }
      }
      if(subList.size()!=0){
        for(int j=0;j<subList.size();j++){
          returnString2=collectionuseinfoFindBS.is_CodeIn_WL(subList.get(j).toString().trim(), "0", securityInfo);
          if(returnString2==null || returnString2.equals("")){
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,203中:"+subList.get(j).toString().trim()+"不是正确科目代码，请检查！", false));
            saveErrors(request, messages);
            return mapping.findForward("defineFormula");
          }
        }
      }
      
      addList=new ArrayList();
      subList=new ArrayList();
      String code_204=defineFormulaDto.getCode_204();
      String Ncode_204[]=code_204.split("\\+");
      for(int i=0;i<Ncode_204.length;i++){
        if(Ncode_204[i].indexOf("-")>0){
          String Mcode_204[]=Ncode_204[i].split("\\-");
          addList.add(Mcode_204[0]);
          for(int j=1;j<Mcode_204.length;j++){
            subList.add(Mcode_204[j]);
          }
        }else{
          addList.add(Ncode_204[i]);
        }
      }
      for(int k=0;k<addList.size();k++){
        returnString1=collectionuseinfoFindBS.is_CodeIn_WL(addList.get(k).toString().trim(), "0", securityInfo);
        if(returnString1==null || returnString1.equals("")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,204中:"+addList.get(k).toString().trim()+"不是正确科目代码，请检查！", false));
          saveErrors(request, messages);
          return mapping.findForward("defineFormula");
        }
      }
      if(subList.size()!=0){
        for(int j=0;j<subList.size();j++){
          returnString2=collectionuseinfoFindBS.is_CodeIn_WL(subList.get(j).toString().trim(), "0", securityInfo);
          if(returnString2==null || returnString2.equals("")){
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,204中:"+subList.get(j).toString().trim()+"不是正确科目代码，请检查！", false));
            saveErrors(request, messages);
            return mapping.findForward("defineFormula");
          }
        }
      }
      
      addList=new ArrayList();
      subList=new ArrayList();
      String code_205=defineFormulaDto.getCode_205();
      String Ncode_205[]=code_205.split("\\+");
      for(int i=0;i<Ncode_205.length;i++){
        if(Ncode_205[i].indexOf("-")>0){
          String Mcode_205[]=Ncode_205[i].split("\\-");
          addList.add(Mcode_205[0]);
          for(int j=1;j<Mcode_205.length;j++){
            subList.add(Mcode_205[j]);
          }
        }else{
          addList.add(Ncode_205[i]);
        }
      }
      for(int k=0;k<addList.size();k++){
        returnString1=collectionuseinfoFindBS.is_CodeIn_WL(addList.get(k).toString().trim(), "0", securityInfo);
        if(returnString1==null || returnString1.equals("")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,205中:"+addList.get(k).toString().trim()+"不是正确科目代码，请检查！", false));
          saveErrors(request, messages);
          return mapping.findForward("defineFormula");
        }
      }
      if(subList.size()!=0){
        for(int j=0;j<subList.size();j++){
          returnString2=collectionuseinfoFindBS.is_CodeIn_WL(subList.get(j).toString().trim(), "0", securityInfo);
          if(returnString2==null || returnString2.equals("")){
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,205中:"+subList.get(j).toString().trim()+"不是正确科目代码，请检查！", false));
            saveErrors(request, messages);
            return mapping.findForward("defineFormula");
          }
        }
      }
      
      addList=new ArrayList();
      subList=new ArrayList();
      String code_206=defineFormulaDto.getCode_206();
      String Ncode_206[]=code_206.split("\\+");
      for(int i=0;i<Ncode_206.length;i++){
        if(Ncode_206[i].indexOf("-")>0){
          String Mcode_206[]=Ncode_206[i].split("\\-");
          addList.add(Mcode_206[0]);
          for(int j=1;j<Mcode_206.length;j++){
            subList.add(Mcode_206[j]);
          }
        }else{
          addList.add(Ncode_206[i]);
        }
      }
      for(int k=0;k<addList.size();k++){
        returnString1=collectionuseinfoFindBS.is_CodeIn_WL(addList.get(k).toString().trim(), "0", securityInfo);
        if(returnString1==null || returnString1.equals("")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,206中:"+addList.get(k).toString().trim()+"不是正确科目代码，请检查！", false));
          saveErrors(request, messages);
          return mapping.findForward("defineFormula");
        }
      }
      if(subList.size()!=0){
        for(int j=0;j<subList.size();j++){
          returnString2=collectionuseinfoFindBS.is_CodeIn_WL(subList.get(j).toString().trim(), "0", securityInfo);
          if(returnString2==null || returnString2.equals("")){
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,206中:"+subList.get(j).toString().trim()+"不是正确科目代码，请检查！", false));
            saveErrors(request, messages);
            return mapping.findForward("defineFormula");
          }
        }
      }
      
      addList=new ArrayList();
      subList=new ArrayList();
      String code_207=defineFormulaDto.getCode_207();
      String Ncode_207[]=code_207.split("\\+");
      for(int i=0;i<Ncode_207.length;i++){
        if(Ncode_207[i].indexOf("-")>0){
          String Mcode_207[]=Ncode_207[i].split("\\-");
          addList.add(Mcode_207[0]);
          for(int j=1;j<Mcode_207.length;j++){
            subList.add(Mcode_207[j]);
          }
        }else{
          addList.add(Ncode_207[i]);
        }
      }
      for(int k=0;k<addList.size();k++){
        returnString1=collectionuseinfoFindBS.is_CodeIn_WL(addList.get(k).toString().trim(), "0", securityInfo);
        if(returnString1==null || returnString1.equals("")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,207中:"+addList.get(k).toString().trim()+"不是正确科目代码，请检查！", false));
          saveErrors(request, messages);
          return mapping.findForward("defineFormula");
        }
      }
      if(subList.size()!=0){
        for(int j=0;j<subList.size();j++){
          returnString2=collectionuseinfoFindBS.is_CodeIn_WL(subList.get(j).toString().trim(), "0", securityInfo);
          if(returnString2==null || returnString2.equals("")){
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,207中:"+subList.get(j).toString().trim()+"不是正确科目代码，请检查！", false));
            saveErrors(request, messages);
            return mapping.findForward("defineFormula");
          }
        }
      }
      
      addList=new ArrayList();
      subList=new ArrayList();
      String code_208=defineFormulaDto.getCode_208();
      String Ncode_208[]=code_208.split("\\+");
      for(int i=0;i<Ncode_208.length;i++){
        if(Ncode_208[i].indexOf("-")>0){
          String Mcode_208[]=Ncode_208[i].split("\\-");
          addList.add(Mcode_208[0]);
          for(int j=1;j<Mcode_208.length;j++){
            subList.add(Mcode_208[j]);
          }
        }else{
          addList.add(Ncode_208[i]);
        }
      }
      for(int k=0;k<addList.size();k++){
        returnString1=collectionuseinfoFindBS.is_CodeIn_WL(addList.get(k).toString().trim(), "0", securityInfo);
        if(returnString1==null || returnString1.equals("")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,208中:"+addList.get(k).toString().trim()+"不是正确科目代码，请检查！", false));
          saveErrors(request, messages);
          return mapping.findForward("defineFormula");
        }
      }
      if(subList.size()!=0){
        for(int j=0;j<subList.size();j++){
          returnString2=collectionuseinfoFindBS.is_CodeIn_WL(subList.get(j).toString().trim(), "0", securityInfo);
          if(returnString2==null || returnString2.equals("")){
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,208中:"+subList.get(j).toString().trim()+"不是正确科目代码，请检查！", false));
            saveErrors(request, messages);
            return mapping.findForward("defineFormula");
          }
        }
      }
      
      addList=new ArrayList();
      subList=new ArrayList();
      String code_209=defineFormulaDto.getCode_209();
      String Ncode_209[]=code_209.split("\\+");
      for(int i=0;i<Ncode_209.length;i++){
        if(Ncode_209[i].indexOf("-")>0){
          String Mcode_209[]=Ncode_209[i].split("\\-");
          addList.add(Mcode_209[0]);
          for(int j=1;j<Mcode_209.length;j++){
            subList.add(Mcode_209[j]);
          }
        }else{
          addList.add(Ncode_209[i]);
        }
      }
      for(int k=0;k<addList.size();k++){
        returnString1=collectionuseinfoFindBS.is_CodeIn_WL(addList.get(k).toString().trim(), "0", securityInfo);
        if(returnString1==null || returnString1.equals("")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,209中:"+addList.get(k).toString().trim()+"不是正确科目代码，请检查！", false));
          saveErrors(request, messages);
          return mapping.findForward("defineFormula");
        }
      }
      if(subList.size()!=0){
        for(int j=0;j<subList.size();j++){
          returnString2=collectionuseinfoFindBS.is_CodeIn_WL(subList.get(j).toString().trim(), "0", securityInfo);
          if(returnString2==null || returnString2.equals("")){
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("设置失败,209中:"+subList.get(j).toString().trim()+"不是正确科目代码，请检查！", false));
            saveErrors(request, messages);
            return mapping.findForward("defineFormula");
          }
        }
      }
      
      collectionuseinfoFindBS.saveCollectionuseParamInfo(defineFormulaDto,securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("defineFormula");
  }
}
