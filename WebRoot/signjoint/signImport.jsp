<%@ page language="java" import="java.util.*,java.io.*,jcifs.smb.ImFilenameFilter" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
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
<title>添加员工信息</title>
<link rel="stylesheet" href="../css/index.css" type="text/css">
</head>
<script type="text/javascript" src="../js/picture.js"></script>
<script src="../js/common.js"></script>
<script language="javascript"  type="text/javascript" >
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
    
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()">
<jsp:include flush="true" page="/waiting.inc"/>
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
        <html:form action="/signImportAC" style="margin: 0" enctype="multipart/form-data">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">职 工 信 息</b></td>
                <td height="22" background="../img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr> 
            <td width="17%" class="td1"  >员工批量签约导入:</td>
            <td width="38%"   > 
			      <html:file name="signImportAF" property="theFile" size="50" maxlength="100" styleClass="input3"/>
			      <html:hidden property="url"/>
            </td>
                        <td width="17%" ></td>
            <td width="45%"   > 
              &nbsp
            </td>
        </tr>
        </table>
   	  <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>

                <td><html:submit styleClass="buttona" onclick="return on_Submit(this)"><bean:message key="button.import"/></html:submit></td>    
			  	<td><input type='button' class="buttona" onclick="return goBack()"  value="<bean:message key="button.back"/>"></td>    
			  </tr>  
            </table>    
      	  </td>
  		</tr>
      </table> 
</html:form>
     <form action="showSignListAC.do" method="POST" name="Form1" id="Form1">
    </form>
</body>
</html:html>
