package org.xpup.hafmis.syscollection.collloanback.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.apache.struts.upload.FormFile;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.FileReader;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.collloanback.bsinterface.ICollLoanbackBS;
import org.xpup.hafmis.syscollection.collloanback.business.CollLoanbackBS;
import org.xpup.hafmis.syscollection.collloanback.form.CollLoanbackTaAF;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BankExportsDTO;


public class CollLoanbackTaUpMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.search", "search");
    map.put("button.import", "imports");
    map.put("button.return.export", "exports");
    return map;
  }

  public Vector vecCsvData;

  public ActionForward search(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      CollLoanbackTaAF collLoanbackTaAF = (CollLoanbackTaAF) form;
      String bank = collLoanbackTaAF.getCollectionBankId();
      String office = collLoanbackTaAF.getOfficeCode();
      Pagination pagination = new Pagination(0, 10, 1, "aa411.loan_kou_acc",
          "DESC", new HashMap(0));
      pagination.getQueryCriterions().put("bank", bank);
      pagination.getQueryCriterions().put("office", office);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("collloanbackta_show");
  }

  public ActionForward imports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    CollLoanbackBS.LoanKouAcc_kou_="";
    CollLoanbackTaAF forms = (CollLoanbackTaAF) form;
    ActionMessages messages = null;
    FormFile file = forms.getTheFile();
    String filename = file.getFileName();
    if (filename.equals("")) { // 判断是否为空
      return mapping.findForward("impFail");
    } else if (!filename.endsWith(".txt")) {
      return mapping.findForward("impFail");
    } else if (filename.toString().length() < 1) {
      return (mapping.findForward("文件无后缀"));
    }
    InputStream stream = null;
    try {
      stream = file.getInputStream();
    } catch (Exception e) {
      e.printStackTrace();
    }
    FormFile file1 = forms.getTheFile();
    String filename1 = file1.getFileName();
    if (filename1.equals("")) { // 判断是否为空
      return mapping.findForward("impFail");
    } else if (!filename1.endsWith(".txt")) {
      return mapping.findForward("impFail");
    } else if (filename1.toString().length() < 1) {
      return (mapping.findForward("文件无后缀"));
    }
    InputStream stream1 = null;
    try {
      stream1 = file1.getInputStream();
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICollLoanbackBS collLoanbackBS = (ICollLoanbackBS) BSUtils
          .getBusinessService("collLoanbackBS", this, mapping.getModuleConfig());
      Pagination pagination = new Pagination(0, 10, 1, "aa411.loan_kou_acc",
          "DESC", new HashMap(0));
      List importList = null;
      String paramValue = "";// 得到扣款方式的参数
      String loanBankId = "";// 放款银行
      // 取得扣款方式
      loanBankId = getLoanBankId(stream1);
      String bizDate = securityInfo.getUserInfo().getBizDate();//业务日期
      String bankDate = "";
      bankDate = collLoanbackBS.getCollBankDate(loanBankId);
      if(!bizDate.equals(bankDate)){
        throw new BusinessException("登录日期与银行业务日期不一致，不能做业务！");
      }
      paramValue = collLoanbackBS.getBackMode(loanBankId);
      if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
        // 足额扣款
        importList = checkDataByCenterFull(stream); // 通过输入流获得信息
      } else {
        // 全额扣款
        importList = checkDataByCenterAll(stream); // 通过输入流获得信息
      }
      collLoanbackBS.imports(importList, securityInfo);
      pagination.getQueryCriterions().put("bank", loanBankId);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("collloanbackta_show");
  }

  public ActionForward exports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    CollLoanbackTaAF forms = (CollLoanbackTaAF) form;
    String loanBankId=forms.getCollectionBankId();
    List outList = new ArrayList();
    try {
      ICollLoanbackBS collLoanbackBS = (ICollLoanbackBS) BSUtils
      .getBusinessService("collLoanbackBS", this, mapping.getModuleConfig());
      outList = collLoanbackBS.findExportList(loanBankId,securityInfo);
      // 生成文件名
      java.util.Date date = new java.util.Date();
      java.text.SimpleDateFormat simpleDate = new java.text.SimpleDateFormat(
          "yyyymmdd");
      String currentdate = simpleDate.format(date);
      String FileName = currentdate + "plExport"; // default file name
      vecCsvData = new Vector();
      int x = setCsvDataTEMP(outList);
      StringBuffer strOut = new StringBuffer();
      for (int i = 0; i < vecCsvData.size(); i++) {
        String[] strLine = (String[]) vecCsvData.elementAt(i);
        int col_num = x;
        
        for (int j = 0; j < col_num; j++) {
          if (i != 0 && j == 2&&i!=vecCsvData.size()-1) {
            strOut.append(strLine[j].substring(0, 4) + "-"
                + strLine[j].substring(4, 6));
          }else{
            if(i==0&&j==1){
              strOut.append("0"+strLine[j]);
            }else{
              strOut.append(strLine[j]);
            }
            
          }
          if (j < col_num - 1) {
                strOut.append("|");
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
    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("collloanbackta_show");
    }
   return null;
  }

  /**
   * 得到银行代码
   * 
   * @param inputstream
   * @return
   */
  public String getLoanBankId(InputStream inputstream) throws Exception {
    String loanBankId = "";
    try {
      InputStreamReader isr = new InputStreamReader(inputstream, "ISO-8859-1");
      FileReader reader = new FileReader(isr);
      String str = "";
      while ((str = reader.readLine()) != null && !"".equals(str.trim())) {
        String[] info = str.split("\\|");
        return String.valueOf(Integer.parseInt(info[1].toString()));
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("导入失败");
    }
    return loanBankId;
  }

  /**
   * 得到以中心为主导入文件的数据(足额扣款)
   * 
   * @param inputstream
   * @return
   * @throws Exception
   */
  public List checkDataByCenterFull(InputStream inputstream) throws Exception {
    int line = 1;
    List result = new ArrayList();
    try {
      InputStreamReader isr = new InputStreamReader(inputstream, "ISO-8859-1");
      FileReader reader = new FileReader(isr);
      String str = "";
      while ((str = reader.readLine()) != null && !"".equals(str.trim())) {
        String[] info = str.split("\\|");
        System.out.println(info[0]+"=============>");
        System.out.println(info[1]+"=============>");
        System.out.println(info[2]+"=============>");
        BankExportsDTO dto = new BankExportsDTO();
        if (line == 1) {
          dto.setBizDate(info[0].trim()); // 扣款日期
          dto.setLoanBankId(info[1].trim()); // 放银行号
          dto.setBatchNum(info[2].trim());// 批次号
        } else {
          dto.setLoanKouAcc(info[0].trim()); // 扣款帐号
          dto.setBorrowerName(info[1].trim()); // 借款人姓名
          dto.setLoanKouYearmonth(info[2].trim().substring(0,4)+info[2].trim().substring(5,7)); // 还款年月
          dto.setShouldMoney(new BigDecimal(info[3].trim()));// 应扣金额
          dto.setIdentifier(info[4].trim());// 扣款标识
        }
        result.add(dto);
        line++;
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("导入失败");
    }
    return result;
  }

  /**
   * 得到以中心为主的导入文件的数据(全额扣款)
   * 
   * @param inputstream
   * @return
   * @throws Exception
   */
  public List checkDataByCenterAll(InputStream inputstream) throws Exception {
    int line = 1;
    List result = new ArrayList();
    try {
      FileReader reader = new FileReader(inputstream);
      reader.setDelimiter("|");
      List list = reader.getList();
      Iterator it = null;
      if (list != null) {
        it = list.iterator();
        while (it.hasNext()) {
          BankExportsDTO dto = new BankExportsDTO();
          List slist = (List) (it.next());
          if (line == 1) {
            if (slist != null) {
              if (slist.size() != 14) {
              }
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null) {
                dto.setBizDate(slist.get(0).toString().trim()); // 扣款日期
                dto.setLoanBankId(slist.get(1).toString().trim()); // 放银行号
                dto.setBatchNum(slist.get(2).toString().trim());// 批次号
              }
            }
          } else {
            if (slist != null) {
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null) {
                dto.setLoanKouAcc(slist.get(0).toString().trim()); // 扣款帐号
                dto.setBorrowerName(slist.get(1).toString().trim()); // 借款人姓名
                dto.setShouldMoney(new BigDecimal(slist.get(2).toString()
                    .trim())); // 应扣金额
                //dto.setRealMoney(new BigDecimal(slist.get(3).toString().trim()));// 实扣金额
              } 
//              else if (slist.get(3) == null) {
//                throw new BusinessException("导入金额不能为空!");
//              }
            }
          }
          result.add(dto);
          line++;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("导入失败");
    }
    return result;
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
  protected String getPaginationKey() {
    return CollLoanbackTaShowAC.PAGINATION_KEY;
  }
}
