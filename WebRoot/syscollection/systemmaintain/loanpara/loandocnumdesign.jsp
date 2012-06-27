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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>参数维护</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>
	
  </head>
  <body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" >
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
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>数据准备</a><font color="00B5DB">&gt;</font><a href="#" class=a1>生成方式</a></td>
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
    <html:form action="/loanDocNumDesignSaveAC.do">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="144"><b class="font14">生成方式</b></td>
                <td width="550" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr id="tr1"> 
          <td class="td1">1</td>
          <td class="td1">凭证号生成方式　　　 
            <html:radio name="loanDocNumDesignAF" property="loanDocNumDocument" value="1" onkeydown="enterNextFocus1();"/>
            按中心
            <html:radio name="loanDocNumDesignAF" property="loanDocNumDocument" value="2" onkeydown="enterNextFocus1();"/>
            按银行</td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">2</td>
          <td class="td1">用户名生成方式      
            <html:radio name="loanDocNumDesignAF" property="name" value="1" onkeydown="enterNextFocus1();"/>
              登录姓名
            <html:radio name="loanDocNumDesignAF" property="name" value="2" onkeydown="enterNextFocus1();"/>
              真实姓名</td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="70"> 
                  <html:submit property="method" styleClass="buttona" onclick="return onCheck();"><bean:message key="button.sure"/></html:submit>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      </html:form>
    </table>
</body>
</html:html>
