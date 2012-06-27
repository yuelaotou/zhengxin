<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security" %>
<%@ page import="org.xpup.hafmis.signjoint.action.CompanyShowAC" %>
<%
   String path = request.getContextPath();
%>
<html:html>
<head>
<title>批量签约</title>
<link rel="stylesheet" href="../css/index.css" type="text/css">
<script language="javascript" src="../js/common.js"></script>
</head>
<script language="javascript"  type="text/javascript" >
function toPop() {
	gotoOrgpop('','<%=path%>','0');
}

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function executeAjax() {
        var queryString = "companyFindAAC.do?";
        var id = document.signAF.elements["orgid"].value.trim();
        queryString = queryString + "id="+id; 	     
	    findInfo(queryString);
}
function displays(id,name){
  document.signAF.elements["orgid"].value=id;
  document.signAF.elements["orgname"].value=name;
  showlist() 
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function showlist() {
  document.Form1.submit();
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()">
<jsp:include page="../inc/sort.jsp"><jsp:param name="url" value="showSignListAC.do"/></jsp:include>

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>     
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="../img/table_left.gif" width="7" height="37"></td>
          <td background="../img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="../img/table_bg_line.gif"><br></td>
          <td background="../img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="../img/title_banner.gif" width="37" height="24"></td>
                <td width="189" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>联名卡</a><font color="00B5DB">&gt;</font><a href="#" class=a1>批量签约</a></td>
                <td width="74" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="../img/table_right.gif" width="9" height="37"></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="190"><b class="font14">职工信息</b></td>
                <td height="22" background="../img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form  action="/showSignListAC.do" >  
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  algin="center">单位编号：</td>
             <td width="22%">
              <html:text name="signAF" property="orgid"  styleClass="input3" onkeydown="gotoEnter();" styleId="txtsearch" ondblclick="executeAjax();"/>
            </td>
             <td width="11%">
              <!--html:button  value="..." class="buttona" /-->
            </td>
            <td class="td1" width="17%" algin="center">单位名称：</td>
            <td width="33%"  > 
              <html:text name="signAF" property="orgname"  styleClass="input3" styleId="txtsearch" readonly="true"/>
            </td>
          </tr>
        </table>  
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  algin="center">职工编号：</td>
             <td width="33%" >
              <html:text name="signAF" property="empid"  styleClass="input3" onkeydown="gotoEnter();" styleId="txtsearch" ondblclick="executeAjax();"/>
            </td>
            <td class="td1" width="17%" algin="center">职工姓名：</td>
            <td width="33%"  > 
              <html:text name="signAF" property="empname"  styleClass="input3" styleId="txtsearch" readonly="true"/>
            </td>
          </tr>
          <tr>
            <td width="17%">&nbsp;</td>
            <td >&nbsp;</td>
            <td width="17%" ></td>
            <td width="33%" align="center" ><input type="submit" name="Submit53" value="查询" class="buttona"></td>
          
          </tr>
        </table>
        </html:form>
 
</html:html>
