<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security" %>

<%@ page import="org.xpup.hafmis.demo.action.DemoTaShowAC" %>

<%
   Object pagination= request.getSession(false).getAttribute(DemoTaShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
 %>
<html:html>
<head>
<title>demo</title>
<link rel="stylesheet" href="../css/index.css" type="text/css">
<script language="javascript" src="js/common.js"></script>
</head>
<script language="javascript" ></script>

<script language="javascript"  type="text/javascript" >


function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function executeAjax() {
        var queryString = "demoFindInforAAC.do?";
        var id = document.demoAF.elements["id"].value.trim();
        queryString = queryString + "id="+id; 	     
	    findInfo(queryString);
}
function displays(id,name){
  document.demoAF.elements["id"].value=id;
  document.demoAF.elements["name"].value=name;
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
<jsp:include page="../inc/sort.jsp"><jsp:param name="url" value="showDemoListAC.do"/></jsp:include>

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>     
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="../img/table_left.gif" width="7" height="37"></td>
          <td background="../img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="../img/table_bg_line.gif"><img width="119" height="37" border="0" src="../img/table_bannerb2.gif"><img src="../img/table_bannerb1.gif" width="116" height="37"></td>
          <td background="../img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="../img/title_banner.gif" width="37" height="24"></td>
                <td width="189" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>demo</a><font color="00B5DB">&gt;</font><a href="#" class=a1>列表</a></td>
                <td width="74" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="img/table_right.gif" width="9" height="37"></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="190"><b class="font14">基 本 信 息</b></td>
                <td height="22" background="../img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form  action="/showDemoListAC.do" >  
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  algin="center">编号</td>
             <td width="22%" >
              <html:text name="demoAF" property="id"  styleClass="input3" onkeydown="gotoEnter();" styleId="txtsearch" ondblclick="executeAjax();"/>
            </td>
            <td class="td1" width="17%" algin="center">姓名</td>
            <td width="22%"  > 
              <html:text name="demoAF" property="name"  styleClass="input3" styleId="txtsearch" readonly="true"/>
            </td>
          </tr>
        </table>  
        </html:form>
       
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="154"><b class="font14">demo列表</b></td>
                  <td width="750" height="22" align="center" background="../img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr> 
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF">
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
 <html:form  action="/demoMaintainAC.do" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr > 
          <td align="center" height="23" bgcolor="C4F0FF" >单选</td>
          <td align="center" height="23" bgcolor="C4F0FF" class=td2>复选</td>
          <td align="center" class=td2 >
          	<a href="javascript:sort('demo.id')">编号</a>
          	<logic:equal name="pagination" property="orderBy" value="demo.id">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          </td>
          <td align="center" class=td2 >
          	<a href="javascript:sort('demo.name')">姓名</a>
          	<logic:equal name="pagination" property="orderBy" value="demo.name">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          </td>          
          <td align="center" class=td2 >身份证</td>  
          <td align="center" class=td2 >生日</td>  
          <td align="center" class=td2 >性别</td>  
          <td align="center" class=td2 >工资</td>
          <td align="center" class=td2 >账户余额</td>
        </tr>
		<logic:notEmpty name="demoAF" property="list">
		<logic:iterate name="demoAF" property="list" id="element" indexId="i">
	
        <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick=""> 
        
          <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="element" property="id"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td>
          <td><html:multibox property="rowArray" ><bean:write name="element" property="id"/></html:multibox></td>	 
          <td valign="top"><p><bean:write name="element" property="id" /></p></td>
          <td valign="top"><p><bean:write name="element" property="name" /></p></td>
          <td valign="top"><p><bean:write name="element" property="idcard" /></p></td>
          <td valign="top"><p><bean:write name="element" property="birthday" /></p></td>
          <td valign="top"><p><bean:write name="element" property="sex" /></p></td>
          <td valign="top"><p><bean:write name="element" property="salary"/></p></td>
          <td valign="top"><p><bean:write name="element" property="balance"/></p></td>
           </tr>
        <tr > 
          <td colspan="9" class=td5 ></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
      <logic:empty name="demoAF" property="list">
        <tr> 
          <td colspan="9" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td colspan="9" class=td5 ></td>
        </tr>
        </logic:empty>  
      </table>

      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../inc/pagination.jsp"><jsp:param name="url" value="showDemoListAC.do"/></jsp:include></td>
              </tr>
          </table></td>
	    </tr>
      </table>

      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>  
                <td><html:submit property="method" styleClass="buttona"><bean:message key="button.add"/></html:submit></td>
                <td><html:submit property="method" styleClass="buttona"><bean:message key="button.update"/></html:submit></td>
                <td><html:submit property="method" styleClass="buttona"><bean:message key="button.delete"/></html:submit></td>               
                <td><html:submit property="method" styleClass="buttona"><bean:message key="button.delete.multibox"/></html:submit></td>     
			    <td><html:submit property="method" styleClass="buttona"><bean:message key="button.delete.all"/></html:submit></td>    
			 	<td><html:submit property="method" styleClass="buttona"><bean:message key="button.exports"/></html:submit></td>   
			 	<td><html:submit property="method" styleClass="buttona"><bean:message key="button.imports"/></html:submit></td>   
			 	<td><html:submit property="method" styleClass="buttona"><bean:message key="button.reports"/></html:submit></td>  
			 </tr> 
            </table>
    </td>
  </tr>

</table>
     </html:form>
     <form action="showDemoListAC.do" method="POST" name="Form1" id="Form1">
    </form>
</table>
</html:html>
