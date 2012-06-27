<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>

<%@ page import="org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.action.DefineReportShowAC" %>
<%@ page import="org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.form.DefineReportAF" %>
<%
   Object pagination= request.getSession(false).getAttribute(DefineReportShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path = request.getContextPath();
   DefineReportAF defineReportAF=(DefineReportAF)request.getAttribute("defineReportAF");
 %>
<html:html>
<head>
<title>财务核算-报表定义</title>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="javascript" src="<%=path%>/sysfinance/reportmng/financereport/js/fncommon.js"></script>
</head>
<script>
function gotoExpresionss(i,j){

	retval=fPopUpExpressDlg('<%=path %>');
	if(retval!=undefined){
		var exp = retval[0];
	    var v_exp = retval[1];
	    var v_flag="v"+i+"_"+j+"";
	    var flag=""+i+"_"+j+"";
	    var t=document.defineReportAF; 
	   	document.getElementById("value("+v_flag+")").value=v_exp;
	   	document.getElementById("value("+flag+")").value=exp;
	}

}
</script>

<body bgcolor="#FFFFFF" text="#000000" >

<table width="700" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="left"> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
	                <td height="22" bgcolor="#CCCCCC" align="center" width="200"><b class="font14"><%=defineReportAF.getTablename() %>定义</b></td>
	                <td height="22" background="<%=path %>/img/bg2.gif" align="center" width="700">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
     </td>
   </tr>
</table>

      	<html:form action="/defineReportSaveAC">
  <table width="400%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
        <table border="0" cellspacing="1" cellpadding="0" bgcolor="1BA5FF">
        <%
          String col=defineReportAF.getCol();
          String row=defineReportAF.getRow();
        %>
          <tr id="tr1" align="center" valign="middle" bgcolor="1BA5FF"> 
            <td height="0" width="0" ></td>
          </tr>
        <% 
        	for(int i=1;i<(Integer.parseInt(col)+1);i++){
        %>
          <tr id="tr1" align="center" valign="middle" bgcolor="#FFFFFF">
            <td height="22"><%=i %></td>
            <%
            	for(int j=1;j<(Integer.parseInt(row)+1);j++){
            	String v_flag="v"+i+"_"+j+"";
            	String flag=""+i+"_"+j+"";
            %>
            <td height="22"> 
            	<input name="value(<%=v_flag %>)" type="text" value="<%=defineReportAF.getValue(v_flag) %>" onkeydown="enterNextFocus1();" class="input5"> 
				<input type="hidden" name="value(<%=flag %>)"  value="<%=defineReportAF.getValue(flag) %>"   />
              	<img src="<%=path%>/img/8.png" width="16" height="16" onclick="gotoExpresionss('<%=i %>','<%=j %>');"> </td>
            <%
            	}
          } 
        %>
          </tr>
        </table>
      </td>
    </tr>
  </table>
   <table width="700" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td align="left">
        <table width="100%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                	<td width="69" align="right"><html:submit styleClass="buttona" ><bean:message key="button.sure"/></html:submit></td>     
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
     </html:form>

</body>
</html:html>
