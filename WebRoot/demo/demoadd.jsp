<%@ page language="java" import="java.util.*,java.io.*,jcifs.smb.ImFilenameFilter" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<title>demo</title>
<link rel="stylesheet" href="../css/index.css" type="text/css">
</head>
<script type="text/javascript" src="../js/picture.js"></script>
<script src="../js/common.js"></script>
<script language="javascript"  type="text/javascript" >
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
  <logic:equal name="demoAddAF" property="isNeedDel" value="1">
  del();
  </logic:equal>
}
</script>

<script language="javascript"  type="text/javascript" >
function checkData() {

	if(document.demoAddAF.elements["demo.idcard"].value.trim()==""){
	   alert(" 请填写身份证!");
	   return false;
	}else if(!isIdCardNo(document.demoAddAF.elements["demo.idcard"].value.trim())){
	   return false;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()">
 <jsp:include page="../syscommon/picture/progressbar.jsp"/>

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="../img/table_left.gif" width="7" height="37"></td>
          <td background="../img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="../img/table_bg_line.gif">&nbsp;</td>
          <td background="../img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="../img/title_banner.gif" width="37" height="24"></td>
                <td width="216" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>demo</a><font color="00B5DB">&gt;</font><a href="#" class=a1><logic:equal name="demoAddAF" property="type" value="1">添加�</logic:equal><logic:equal name="demoAddAF" property="type" value="2">修改</logic:equal></a></td>
                <td width="47" class=font14>&nbsp;</td>
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
    <html:form action="/addDemoMaintainAC.do" style="margin: 0" >
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14"></b></td>
                <td height="22" background="../img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr> 
          <td width="17%" class="td1"  >姓名</td>
          <td width="33%" colspan="2"   > 
            <html:text name="demoAddAF" property="demo.name"  styleClass="input3"/>
          </td>
          <td width="17%" class="td1"  >身份证</td>
          <td width="33%"  > 
            <html:text name="demoAddAF" property="demo.idcard"  styleClass="input3"/>
          </td>
        </tr>
        <tr> 
          <td width="17%" class="td1"  >生日</td>
          <td width="33%" colspan="2"   > 
           <html:text name="demoAddAF" property="demo.birthday"  styleClass="input3"/>
          </td>
          <td width="17%" class="td1"  >性别</td>
          <td width="33%"  > 
          <html:select property="demo.sex"  styleClass="input4"><html:optionsCollection property="sexMap" name="demoAddAF" label="value" value="key"/></html:select>
          </td>
        </tr>
             
        <tr> 
          <td width="17%" class="td1"  >工资</td>
          <td width="33%" colspan="2"   > 
            <html:text name="demoAddAF" property="demo.salary"  styleClass="input3" />
          </td>
          <td width="17%" class="td1"  ><html:hidden name="demoAddAF" property="demo.id"/></td>
          <td width="33%" class="td7"  >&nbsp;</td>
        </tr>
         <tr> 
          <td width="17%" class="td1" ></td>
          <td width="33%" colspan="2"  > 
         
            <a href='javascript:excHz("<bean:write  name="demoAddAF" property="demo.photoUrl"/>");'>浏览证书</a>
          </td>
          <td width="17%" class="td1"  ></td>
          <td width="33%" class="td7"  >&nbsp;</td>
        </tr>
        
      </table>     
       <html:hidden name="demoAddAF" property="isNeedDel"/>
<table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><logic:equal name="demoAddAF" property="type" value="1"><html:submit property="method" styleClass="buttona" onclick="return checkData()"><bean:message key="button.add"/></html:submit></logic:equal></td>
                <td><logic:equal name="demoAddAF" property="type" value="2"><html:submit property="method" styleClass="buttona" onclick="return checkData()"><bean:message key="button.add.update"/></html:submit></logic:equal></td>
                <td><html:submit property="method" styleClass="buttona"><bean:message key="button.back"/></html:submit></td>    
			   <td><html:submit onclick="javascript:return showselect();" property="method" styleClass="buttona"><bean:message key="button.upload"/></html:submit></td>
			  </tr>
            </table>    
      </html:form>
    </td>
  </tr>
</table>

</body>
</html:html>
