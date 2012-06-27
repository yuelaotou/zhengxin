<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>

<%
   String path = request.getContextPath();
 %>
 
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%> 
<html:html>
<head>
<title>缴存管理-单位补缴-导入</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css"/>
<script src="<%=path%>/js/common.js"></script>    
<script type="text/javascript">

function on_Submit(eee){
    if(document.forms[0].theFile.value==""){
    alert("请选择导入文件！！！");
  	return false;
  } else {
    document.forms[0].url.value=document.forms[0].theFile.value.trim();
       setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
    return true;
  }
} 

function goBack(){
    document.Form1.submit();
}
    
function reportMessage(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>

<body bgcolor="#FFFFFF"  onContextmenu="return false" text="#000000" onload="return reportMessage();">
<jsp:include flush="true" page="/waiting.inc"/>
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
                <td width="216" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>缴存管理</a><font color="00B5DB">&gt;</font><a href="#" class=a1>单位补缴</a></td>
                <td width="47" class=font14>&nbsp;</td>
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
    <html:form action="/orgaddpayTaImportAC" style="margin: 0" enctype="multipart/form-data">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">导 入 文 件</b></td>
                <td height="22" background="<%=path %>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
         
      
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        
          <tr> 
            <td width="17%" class="td1"  >单位补缴导入:</td>
            <td width="38%"   > 
			      <html:file name="orgaddpayImportTaAF" property="theFile" size="50" maxlength="100" styleClass="input3"/>
			      <html:hidden property="url"/>
			      <html:hidden name="orgaddpayImportTaAF" property="noteNum"/>
			      <html:hidden name="orgaddpayImportTaAF" property="orgid"/>
			      <html:hidden name="orgaddpayImportTaAF" property="payMonth"/>
			      <html:hidden name="orgaddpayImportTaAF" property="startPayMonth"/>
			      <html:hidden name="orgaddpayImportTaAF" property="payKind"/>
			      <html:hidden name="orgaddpayImportTaAF" property="payWay"/>
			      <html:hidden name="orgaddpayImportTaAF" property="payWay"/>
			      <html:hidden name="orgaddpayImportTaAF" property="payment_orgname"/>
            </td>
            <td  >&nbsp;</td>
          </tr>
        </table>

   	  <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>

                <td><html:submit styleClass="buttona" onclick="return on_Submit(this)"><bean:message key="button.imports"/></html:submit></td>    
			  	<td><input type='button' class="buttona" onclick="return goBack()"  value="<bean:message key="button.back"/>"></td>    
			  </tr>  
            </table>    
      	  </td>
  		</tr>
      </table> 
    </html:form> 
    </td>
  </tr>
</table>

<form action="orgaddpayTaShowAC.do" method="POST" name="Form1" id="Form1">
</form>
</body>
</html:html>	  