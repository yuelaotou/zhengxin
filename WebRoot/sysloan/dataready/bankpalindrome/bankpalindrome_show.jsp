<%@ page language="java"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTaShowAC" %>
<%@ include file="/checkUrl.jsp"%> 
<%
String path = request.getContextPath();
%> 
<html:html>
<head>
<title>银行回文格式设置</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/picture.js"></script>
</head>
<script language="javascript">
function gotoSure(){
 var bankId = document.all.bankId.value;
 var rowNum = document.all.rowNum.value;
 if(bankId == ""){
  alert("请选择银行");
  return false;
 }
 if(isNaN(rowNum)){
   alert("请输入正确格式的列数！");
   document.all.rowNum.value="";
   return false;
 }
 if(rowNum == ""){
  alert("列数不能为空！");
  return false;
 }
 location="bankpalindromeSaveAC.do?bankId="+bankId+"&rowNum="+rowNum;
}
function gotocheck(){
}
</script>

<body bgcolor="#FFFFFF" text="#000000">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path%>/img/table_bg_line.gif">&nbsp;</td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>数据准备</a><font color="00B5DB">&gt;</font><a href="#" class=a1>银行回文格式设置</a></td>
                <td width="29" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
    </td> 
  </tr>
  <tr> 
    <td class=td3> 
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="167"><b class="font14">银行回文格式设置</b></td>
                <td width="737" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr > 
            <td height="23" colspan="6" align="center" bgcolor="C4F0FF" >&nbsp;</td>
          </tr>
          <tr  class=td4 > 
            <td colspan="5" >&nbsp;</td>
          </tr>
           <html:form action="/bankpalindromeFindAC" style="margin: 0">
          <tr  class=td4 > 
            <td width="25%" >&nbsp;</td>
            <td colspan="2" ><html:select name="bankpalindromeAF" property="bankId"  styleClass="input4">
             		<html:option value=""></html:option>
					<html:options collection="loanBankNameList" property="value"
								labelProperty="label" />
			 </html:select></td>
            <td width="12%" align="center" ><html:submit property="method" styleClass="buttona"  onclick="return gotocheck();"><bean:message key="button.search" /></html:submit></td>
            <td width="30%" >&nbsp;</td>
          </tr>
          <tr  class=td4 > 
            <td colspan="5" >&nbsp;</td>
          </tr>
          </html:form>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td width="15%" align="center" class="td1">回文列数</td>
            <td colspan="2" ><html:text name="bankpalindromeAF" property="rowNum" styleClass="input3"/></td>
            <td >&nbsp;</td>
          </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr > 
		  <td>&nbsp; </td>
	    </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="70"> 
                  <html:submit property="method" styleClass="buttonb" onclick="return gotoSure(); "><bean:message key="button.sure"/></html:submit>
                </td>
                
              </tr>
            </table>
    </td>
  </tr>
</table>
</table></body>
</html:html>

