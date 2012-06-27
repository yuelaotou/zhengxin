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
	    <title>启用账套</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	  </head>
	  <script src="<%=path%>/js/common.js"></script>
	  <script>
	  	function reportErrors(){
		  	<logic:messagesPresent>
			var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
			alert(message);
			</logic:messagesPresent>
		}
  	  </script>
  	  
<body bgcolor="#FFFFFF" text="#000000"  onLoad="reportErrors();"  onContextmenu="return false">
	  <html:form action="/bookstartNewAC.do">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
			  <tr>
			    <td>
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
			          <td background="<%=path%>/img/table_bg_line.gif" width="56"></td>
			          <td background="<%=path%>/img/table_bg_line.gif" width="57"></td>
			          <td background="<%=path%>/img/table_bg_line.gif" align="right" width="837"> 
			            <table width="300" border="0" cellspacing="0" cellpadding="0">
			              <tr> 
			                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
			                <td class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">账套管理&gt;启用账套</font></td>
			                <td width="15" class=font14>&nbsp;</td>
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
			                <td height="22" bgcolor="#CCCCCC" align="center" width="132"><b class="font14">启 用 账 套</b></td>
			                <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="742">&nbsp;</td>
			              </tr>
			            </table>
			          </td>
			        </tr>
			      </table>
			      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
			        <tr>
			          <td width="15%" class="td1">账套名称</td>
			          <td width="35%">
						<html:text name="bookstartAF" property="bookName"
									styleClass="input3" styleId="txtsearch" readonly="true" />
					  </td>
			          <td width="15%" class="td1">账套启用时间</td>
			          <td colspan="2"><html:text name="bookstartAF" property="useYearmonth"
									styleClass="input3" styleId="txtsearch" readonly="true" />
					  </td>
			        </tr>
			        <tr>
			          <td width="15%"   class="td1" height="18">科目级数</td>
			          <td width="35%" align="center" height="18">
					  	<html:text name="bookstartAF" property="paramValue"
									styleClass="input3" styleId="txtsearch" readonly="true" />
					  </td>
			          <td width="15%" class="td1" height="18" >代码结构</td>
			          <td width="35%"  height="18">
					  	<html:text name="bookstartAF" property="paramExplain"
									styleClass="input3" styleId="txtsearch" readonly="true" />
					  </td>
			        </tr>
			      </table>
			      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
			        <tr valign="bottom"> 
			          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
			            <table border="0" cellspacing="0" cellpadding="0">
			              <tr> 
			                <logic:empty name="bookST">
			                	<td width="70"> 
			                  		<html:submit property="method" styleClass="buttona"><bean:message key="button.sure"/></html:submit>
			                	</td>
			                </logic:empty>
			                <logic:notEmpty name="bookST">
			                	<td width="70"> 
			                  		<html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.sure"/></html:submit>
			                	</td>
			                </logic:notEmpty>
			              </tr>
			            </table>
			          </td>
			        </tr>
			      </table>
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td>&nbsp;</td>
			        </tr>
			      </table>
			    </td>
			  </tr>
			</table>
		</html:form>
</body>
</html:html>
