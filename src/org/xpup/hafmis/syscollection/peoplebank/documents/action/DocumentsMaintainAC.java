package org.xpup.hafmis.syscollection.peoplebank.documents.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import java.util.Map;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.peoplebank.documents.bsinterface.IDocumentBS;
import org.xpup.hafmis.syscollection.peoplebank.documents.dto.DocumentstopDTO;
import org.xpup.hafmis.syscollection.peoplebank.documents.form.DocumentsAF;

public class DocumentsMaintainAC extends LookupDispatchAction{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.peoplebank.documents.action.DocumentsShowAC";

  private static final String CONTENT_TYPE = "text/html; charset=gb2312";

  public Vector content;

  // private String FileName;
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.export", "exports");    
    return map;
  }

  public ActionForward exports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      ActionMessages messages = null;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IDocumentBS documentBS = (IDocumentBS) BSUtils.getBusinessService("documentBS", this, mapping.getModuleConfig());

      DocumentsAF documentsAF = (DocumentsAF) form;
      String date = documentsAF.getDate();

      DocumentstopDTO documentstopdto = new DocumentstopDTO();
      documentstopdto = documentBS.gettopInfo(securityInfo);
      if(documentstopdto.getSettingtype()==null||documentstopdto.getSettingtype().equals("")){
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("未进行参数类型设置", false));       
        saveErrors(request, messages);
      }else if(documentstopdto.getNum()==null||documentstopdto.getNum().equals("")||documentstopdto.getUporganization()==null||documentstopdto.getUporganization().equals("")
          ||documentstopdto.getPeople()==null||documentstopdto.getPeople().equals("")||documentstopdto.getTep()==null||documentstopdto.getTep().equals("")){
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("未进行报文头设置", false));       
        saveErrors(request, messages);
      }else{
        content = new Vector();
        content = documentBS.getAllInfo(date,securityInfo,documentstopdto);
  
        // 整合数据
        String FileName = documentstopdto.getUporganization() + date + "0019"; // default file name
        //int x = setCsvDataTEMP(list);
        StringBuffer strOut = new StringBuffer();
        
        int w=0;
        for (int i = 0; i < content.size(); i++) {
          if(i==0){
            String[] strLinetop = (String[]) content.elementAt(0);
            strOut.append(strLinetop[0]);
            strOut.append("\r\n");
          }else{
            String[] strLine = (String[]) content.elementAt(i);
            strOut.append(strLine[w]);
            w++;
            strOut.append("\r\n");
          }
        }
        response.setContentType("application/text;charset=GBK");
        response.setHeader("Content-Disposition", "attachment; filename=\""+ FileName + ".txt\"");
        PrintWriter out;
        out = response.getWriter();
        out.write(strOut.toString());
  
        return null;
      }
    } catch (IOException eio) {
      eio.printStackTrace();
    } catch (Exception b) {
      b.printStackTrace();
    }
    return mapping.findForward("to_documents_show");
  }

  //
  // public void setFileName(String filename) {
  // FileName = filename;
  // }
}