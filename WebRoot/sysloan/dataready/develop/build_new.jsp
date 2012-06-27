<%@ page language="java" import="java.math.BigDecimal" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    
    <title>新增楼栋信息</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
  </head>
<script language="javascript" src="js/common.js">
</script>  
<script> 
	function reportErrors() {
		<logic:messagesPresent>
			var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
			alert(message);
		</logic:messagesPresent>
	}
	function check(){
	
	var buildNum = document.forms[0].elements["buildNum"].value.trim();
	var build_s = document.forms[0].elements["build_s"].value.trim();	

		
		if(buildNum == null || buildNum == ""){
			alert("楼栋号不能为空!");
			return false;
		}
		var salarybase = build_s.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/)
		if (salarybase==null)
			{	
        		alert("请录入正确的面积！格式如：1087.23");
				return false;
			}
		var type = document.forms[0].elements["type"].value;
		if(type==null||type==''){
			document.getElementById("method").value="saveBuildInfo";
		}else{
			document.getElementById("method").value="modifyBuildInfo";
		}
	}
	
	function checkback(){
		var build_s = document.forms[0].elements["build_s"].value.trim();
		if(build_s == "")
			document.forms[0].elements["build_s"].value = "0.00";
		document.getElementById("method").value="backBuildInfo";
	}
</script> 
<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();" >
<html:form action="/buildNewAC">
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
                  <td width="165" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">数据准备&gt;开发商维护</font></td>
                  <td width="98" class=font14>&nbsp;</td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="147"><b class="font14">新增楼栋信息</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="757">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%"   class="td1">楼盘号<font color="#FF0000">*</font> <br>
            </td>
            <td width="33%"> 
              <html:text name="buildNewAF" property="floorId" styleClass="input3" onkeydown="enterNextFocus1();" readonly="true"></html:text>
            </td>
            <td width="17%" class="td1" >楼栋号<font color="#FF0000">*</font> </td>
            <td width="33%" align="center"  > 
              <html:text name="buildNewAF" property="buildNum" styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
            </td>
          </tr>
          
          <tr> 
             <td width="17%" class="td1" >楼栋地址</td>
            <td width="33%" align="center"  > 
              <html:text name="buildNewAF" property="buildAdd" styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
            </td>
            <td class="td1" width="17%" >是否拨款</td>
            <td width="33%" align="center" > 
              <html:select name="buildNewAF" property="fundStatus" styleClass="input4" onkeydown="enterNextFocus1();">
	              <html:option value="1">是</html:option>
	              <html:option value="0">否</html:option>
			  </html:select>
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">建筑面积</td>
            <td width="33%"> 
              <html:text name="buildNewAF" property="build_s" styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
            </td>
            <td width="17%" class="td1" >备注</td>
            <td width="33%" align="center"  > 
              <html:text name="buildNewAF" property="reserved" styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
            </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                <input type="hidden" name="method" id="method" value=""/>
               <html:hidden name="buildNewAF" property="type" />
                <logic:empty name="buildNewAF" property="type">
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona" onclick="return check();"><bean:message key="button.sure"/></html:submit>
                  </td>
                </logic:empty>
                <logic:notEmpty name="buildNewAF" property="type">
                 	<td width="70"> 
                    <html:submit property="method" styleClass="buttona" onclick="return check();"><bean:message key="button.confirm"/></html:submit>
                  </td>
                  </logic:notEmpty>
                <td width="70">
                    <html:submit property="method" styleClass="buttona" onclick="checkback();"><bean:message key="button.back"/></html:submit>
                  </td>
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
