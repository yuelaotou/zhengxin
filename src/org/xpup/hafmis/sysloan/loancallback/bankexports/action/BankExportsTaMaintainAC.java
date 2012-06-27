package org.xpup.hafmis.sysloan.loancallback.bankexports.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loancallback.bankexports.bsinterface.IBankExportsBS;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BankExportsDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class BankExportsTaMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.database.exports", "exports");
    map.put("button.delete", "delete");
    map.put("button.deleteall", "deleteall");
    map.put("button.print", "print");
    return map;
  }

  private static final String CONTENT_TYPE = "text/html; charset=gb2312";

  public Vector vecCsvData;

  private String FileName;

  public ActionForward exports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");

    List outList = new ArrayList();
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          BankExportsTaShowAC.PAGINATION_KEY);
      IBankExportsBS bankExportsBS = (IBankExportsBS) BSUtils
          .getBusinessService("bankExportsBS", this, mapping.getModuleConfig());
      outList = bankExportsBS.findExportList(pagination, securityInfo);
      // 生成文件名
      java.util.Date date = new java.util.Date();
      java.text.SimpleDateFormat simpleDate = new java.text.SimpleDateFormat(
          "yyyyMMddHHmm");
      String currentdate = simpleDate.format(date);
      String FileName = currentdate + "plExport"; // default file name
      vecCsvData = new Vector();
      int x = setCsvDataTEMP(outList);
      // String strOut = "";
      StringBuffer strOut = new StringBuffer();
      for (int i = 0; i < vecCsvData.size(); i++) {
        String[] strLine = (String[]) vecCsvData.elementAt(i);
        int col_num = x;
        for (int j = 0; j < col_num; j++) {
          if((i!=0&&i!=vecCsvData.size()-1)&&j==2){
            String str="";
            str=strLine[j];
            strOut.append(strLine[j].substring(0, 4)+"-"+strLine[j].substring(4, 6));
          }else{
            if(j==1&&i==0){
              strOut.append("0"+strLine[j]);
            }else{
              strOut.append(strLine[j]);
            }
          }
          if (j < col_num - 1&&i==0) {
            if(j<2){
              strOut.append("|");
            }else{
              break;
            }
              
        }
          if (j < col_num - 1&&i!=0) {
//            if(i==0){
//              if(!strLine[j].equals("")){
                strOut.append("|");
//              }
//            }else{
//              strOut.append("|");
//            }
          }
        }
        strOut.append("|");
        strOut.append("\r\n");
      }
      response.setContentType("application/text;charset=GBK");
      response.setHeader("Content-Disposition", "attachment; filename=\""
          + FileName + ".txt\"");

      PrintWriter out;
      out = response.getWriter();
      out.write(strOut.toString());
    } catch (IOException eio) {
      eio.printStackTrace();
    } catch (BusinessException b) {
      b.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("bankexportsTaShowAC");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      messages = new ActionMessages();
      IdAF idaf = (IdAF) form;

      Pagination pagination = (Pagination) request.getSession().getAttribute(
          BankExportsTaShowAC.PAGINATION_KEY);
      IBankExportsBS bankExportsBS = (IBankExportsBS) BSUtils
          .getBusinessService("bankExportsBS", this, mapping.getModuleConfig());
      bankExportsBS.deleteTailInfo(idaf.getId().toString(), securityInfo);

      pagination.getQueryCriterions().put("headId", null);
    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    }
    request.setAttribute("deletema", "yes");
    return mapping.findForward("bankExportsTaShowAC");
  }

  public ActionForward deleteall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        BankExportsTaShowAC.PAGINATION_KEY);
    try {
      messages = new ActionMessages();
      IBankExportsBS bankExportsBS = (IBankExportsBS) BSUtils
          .getBusinessService("bankExportsBS", this, mapping.getModuleConfig());
      bankExportsBS.deleteTailList(pagination, securityInfo);
      pagination.getQueryCriterions().put("headId", null);
      pagination.getQueryCriterions().put("batchNum", null);
    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    }
    request.setAttribute("deletema", "yes");
    return mapping.findForward("bankExportsTaShowAC");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        BankExportsTaShowAC.PAGINATION_KEY);
    try {
      IBankExportsBS bankExportsBS = (IBankExportsBS) BSUtils
          .getBusinessService("bankExportsBS", this, mapping.getModuleConfig());
      List list = bankExportsBS.getPrintList(pagination, securityInfo);

     
         String userName="";
         try {
           String name = bankExportsBS.getNamePara();

           if (name.equals("1")) {
             userName = securityInfo.getUserName();
           } else {
             userName = securityInfo.getRealName();
           }

         } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
      request.setAttribute("makePerson", userName);
      request.setAttribute("bizDate", securityInfo.getUserInfo().getPlbizDate());
      request.setAttribute("URL","bankExportsTaShowAC.do");
      request.setAttribute("List",list);
    } catch (Exception b) {
      messages = new ActionMessages();
      b.printStackTrace();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("bankexports_cell");
  }

  public int setCsvDataTEMP(List outList) {
    int x = 0;
    for (int i = 0; i < outList.size(); i++) {
      Vector vector = (Vector) outList.get(i);
      String[] strLine = new String[vector.size()];

      x = vector.size();
      for (int j = 0; j < vector.size(); j++) {
        strLine[j] = (String) vector.get(j);
      }
      vecCsvData.addElement(strLine);
    }
    return x;
  }

  public void setFileName(String filename) {
    FileName = filename;
  }
}