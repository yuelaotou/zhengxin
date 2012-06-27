package org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.bsinterface.ICollectionuseinfoFindBS;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.dto.DefineFormulaDto;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.form.DefineFormulaFindAF;

public class DefineFormulaForwardAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      DefineFormulaFindAF defineFormulaFindAF=(DefineFormulaFindAF)(form);
      ICollectionuseinfoFindBS collectionuseinfoFindBS = (ICollectionuseinfoFindBS)BSUtils.getBusinessService("collectionuseinfoFindBS",this,mapping.getModuleConfig());
      List accountList=collectionuseinfoFindBS.findAccountList();
      request.getSession().setAttribute("accountList", accountList);
      
      List aa400List=collectionuseinfoFindBS.findAA400List();
      DefineFormulaDto defineFormulaDto=new DefineFormulaDto();
      if(aa400List.size()!=0){
        List a=new ArrayList();
        for(int i=0;i<aa400List.size();i++){
          a.add(aa400List.get(i).toString());
        }
        if(a.get(0)!=null && !a.get(0).equals("")){
          defineFormulaDto.setCode_201(a.get(0).toString().trim());
          defineFormulaDto.setCode_202(a.get(1).toString().trim());
          defineFormulaDto.setCode_203(a.get(2).toString().trim());
          defineFormulaDto.setCode_204(a.get(3).toString().trim());
          defineFormulaDto.setCode_205(a.get(4).toString().trim());
          defineFormulaDto.setCode_206(a.get(5).toString().trim());
          defineFormulaDto.setCode_207(a.get(6).toString().trim());
          defineFormulaDto.setCode_208(a.get(7).toString().trim());
          defineFormulaDto.setCode_209(a.get(8).toString().trim());
        }else{
          defineFormulaDto.setCode_201("");
          defineFormulaDto.setCode_202("");
          defineFormulaDto.setCode_203("");
          defineFormulaDto.setCode_204("");
          defineFormulaDto.setCode_205("");
          defineFormulaDto.setCode_206("");
          defineFormulaDto.setCode_207("");
          defineFormulaDto.setCode_208("");
          defineFormulaDto.setCode_209("");
        }
      }
      defineFormulaFindAF.setDefineFormulaDto(defineFormulaDto);
      request.getSession().setAttribute("defineFormulaFindAF", defineFormulaFindAF);
    }catch(Exception s){
      s.printStackTrace();
    }
    return mapping.findForward("defineFormula");
  }
}
