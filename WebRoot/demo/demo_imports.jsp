<%@ page language="java"%>
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>

<html:html xhtml="true" locale="true">
<head>

</head>
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%> 
<script language="javascript">

  function onExport(){
	    document.ReportParaForm1.submit(); 
  }
   function on_Submit1111(eee){  
    if(document.forms[0].theFile.value==""){
   //  alert("请选择导入文件！！！");
     return false;
     } else {
       document.forms[0].url.value=document.forms[0].theFile.value;
   //    setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
       return true;
     }
    } 
  </script>
  
<body bgcolor="#FFFFFF" text="#000000"  onContextmenu="return false" >
 

<table width="740" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td height="50" width="43%">&nbsp;</td>
          <td height="50" width="57%">&nbsp;</td>
        </tr>
        <tr> 
          <td width="43%">&nbsp;</td>
          <td width="57%" class="font14"><b>┈┈导出导入演示</b></td>
        </tr>
      </table>
  </tr>
</table>


<html:form action="/demoTaImportAC" method="post" enctype="multipart/form-data">

<table width="650" border="0" align="center" >
  <tr> 
    <td width="125" class="td1">&nbsp;导入文件路径:</td>
    <td width="176" class="td2"> 
      <html:file name="demoImportAF" property="theFile" />
      <html:hidden property="url"/>
    </td>
    <tr > 
  </tr>
  </table>
  <table width="650" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
  </tr>
</table>
<br>
<jsp:include flush="true" page="/waiting.inc"/>
<center><html:submit property="action"  onclick="return on_Submit1111(this)">导入</html:submit></center>
</html:form>

</body>
</html:html>