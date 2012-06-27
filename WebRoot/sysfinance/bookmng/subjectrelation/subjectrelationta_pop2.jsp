<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action.SubjectRelationTaPop2AC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			SubjectRelationTaPop2AC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>财务核算</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="javascript"></script>
<script language="javascript" type="text/javascript">
function onpback(){
window.close();
}
var flag=false;
function reportErrors() {	
<%
String count=(String)request.getAttribute("count");
%>
var c=<%=count%>
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	var x= document.getElementsByName("rowArray");
	for(var i=0;i<x.length;i++){
		x[i].checked=flag;
	}
	<logic:messagesNotPresent>
	if(c!="1"){	
		window.close();
		opener.executeAjax();
	}	
	</logic:messagesNotPresent>
		
	return false;
}
function back(){
window.close();
opener.executeAjax();
}
function onclear(){
var x= document.getElementsByName("rowArray");
var y=true;
for(var i=0;i<x.length;i++){
		if(x[i].checked){
		y=false;
		}
	}
	if(y){
	alert(' 至少选择一条记录！');
	return false;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="reportErrors();">
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=path%>/img/table_left.gif" width="7" height="37">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" width="55">
							&nbsp;
						</td>
						<td width="235" background="<%=path%>/img/table_bg_line.gif">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<p>
											<font color="00B5DB">系统维护&gt;科目关系设置</font>
										</p>
									</td>
									<td width="74" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
				<html:form action="/subjectrelationTaSave2AC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="177">
											<b class="font14">银行列表</b>
										</td>
										<td width="681" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr align="center">
							<td class="td2">
							</td>
							<td class="td2">
								银行名称
							</td>
						</tr>
						<logic:notEmpty name="subjectrelaionTaPop2AF" property="list">
							<logic:iterate id="element" name="subjectrelaionTaPop2AF"
								property="list" indexId="i">
								<tr>
									<td>
										<html:multibox property="rowArray">
											<bean:write name="element" property="collBankId" />
										</html:multibox>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="collBankName" />
										</p>
									</td>
								</tr>
								<tr>
									<td colspan="11" class=td5></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="subjectrelaionTaPop2AF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
									<br>
								</td>
							</tr>
						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="subjectRelationTaPop2AC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<logic:empty name="subjectrelaionTaPop2AF" property="list">
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.sure" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.allsure" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.back" />
												</html:submit>
											</td>
										</logic:empty>
										<logic:notEmpty name="subjectrelaionTaPop2AF" property="list">
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona" onclick="return onclear();">
													<bean:message key="button.sure" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.allsure" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													onclick="return back();">
													<bean:message key="button.back" />
												</html:submit>
											</td>
										</logic:notEmpty>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<form action="subjectrelationTaShowAC.do" method="POST" name="Form1"
					id="Form1">
				</form>
			</td>
		</tr>
	</table>
</body>
</html:html>

