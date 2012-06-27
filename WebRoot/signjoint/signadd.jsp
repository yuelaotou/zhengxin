<%@ page language="java" import="java.util.*,java.io.*,jcifs.smb.ImFilenameFilter" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<html:html>
<head>
<title>添加员工信息</title>
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
}
</script>

<script language="javascript"  type="text/javascript" >
function checkData() {
	if(document.signAddAF.elements["userinfo.bankcardid"].value.trim()==""){
	   alert("请填写银行卡号!");
	   return false;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()">


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
    <html:form action="/addSignMaintainAC.do" >
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
    <logic:equal name="signAddAF" property="type" value="1">
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr> 
          <td width="17%" class="td1"  >职工编号</td>
          <td width="22%" colspan="2"   > 
            <html:text name="signAddAF" property="userinfo.empid"  styleClass="input3"/>
          </td>
          <td width="11%" colspan="2"> 
            <input type="button" name="Submit5" value="..." class="buttona"/>
          </td>
          <td width="17%" class="td1"  >职工姓名</td>
          <td width="33%"  > 
            <html:text name="signAddAF" property="userinfo.empname"  styleClass="input3"  />
          </td>
        </tr>
        </table>
        <table  border="0" width="95%" id="table2" cellspacing=1  cellpadding=3 align="center">
         <tr> 
          <td width="17%" class="td1"  >身份证号</td>
          <td width="33%" colspan="2"   > 
           <html:text name="signAddAF" property="userinfo.cardnum"  styleClass="input3"/>
          </td>
          <td width="17%" class="td1"  >银行卡号</td>
          <td width="33%"  > 
          <html:text name="signAddAF" property="userinfo.bankcardid"  styleClass="input3"/>
          </td>
        </tr>
      </table>
     </logic:equal>

      <logic:equal name="signAddAF" property="type" value="2">

       <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr> 
          <td width="17%" class="td1"  >职工编号</td>
          <td width="33%" colspan="2"   > 
            <html:text name="signAddAF" property="userinfo.empid"  styleClass="input3" readonly="true"/>
          </td>          
          <td width="17%" class="td1"  >职工姓名</td>
          <td width="33%"  > 
            <html:text name="signAddAF" property="userinfo.empname"  styleClass="input3"  readonly="true" />
          </td>
        </tr>
        </table>
        <table  border="0" width="95%" id="table2" cellspacing=1  cellpadding=3 align="center">
         <tr> 
          <td width="17%" class="td1"  >身份证号</td>
          <td width="33%" colspan="2"   > 
           <html:text name="signAddAF" property="userinfo.cardnum"  styleClass="input3" readonly="true" />
          </td>
          <td width="17%" class="td1"  >银行卡号</td>
          <td width="33%"  > 
          <html:text name="signAddAF" property="userinfo.bankcardid"  styleClass="input3"/>
          </td>
        </tr>
      </table>


      </logic:equal>
      <html:hidden name="signAddAF" property="olduserinfo.bankcardid"  styleClass="input3"/>
      <html:hidden name="signAddAF" property="olduserinfo.orgid"  styleClass="input3"/>     
      <html:hidden name="signAddAF" property="olduserinfo.empid"  styleClass="input3"/>     
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><logic:equal name="signAddAF" property="type" value="1"><html:submit property="method" styleClass="buttona" onclick="return checkData()"><bean:message key="button.add"/></html:submit></logic:equal></td>
                <td><logic:equal name="signAddAF" property="type" value="2"><html:submit property="method" styleClass="buttona" onclick="return checkData()"><bean:message key="button.update"/></html:submit></logic:equal></td>
                <td><html:submit property="method" styleClass="buttona"><bean:message key="button.back"/></html:submit></td>    
			  </tr>
       </table>    
    </td>
  </tr>
</table>
</html:form>
</body>
</html:html>
