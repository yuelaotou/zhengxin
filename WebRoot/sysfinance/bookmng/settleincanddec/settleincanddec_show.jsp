<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.bookmng.credencemodle.action.CredencemodleShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			CredencemodleShowAC.PAGINATION_KEY);
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
function seachSujectCode(){
	gotoSubjectpop('0','<%=path%>','0');
}
function seachSujectCode1(){
	gotoSubjectpop('0','<%=path%>','1');
}
function backErrors(id){
document.all.bySubjectCode.value="";
document.all.subjectCode.value="";
    alert(id);
}
function checkData(){
	if(document.all.bySubjectCode.value.trim()==""){
	   alert("请输入完整信息!");
	   return false;
	}
	
	if(document.all.subjectCode.value.trim()==""){
	   alert("请输入完整信息!");怎么不说话啊
	   return false;
	}
	return true;
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function executeAjaxIn(){	
	var subjectCode=document.all.subjectCode.value.trim();	
		if(subjectCode.length!=0){
		    var queryString = "settleincanddecCheckSubjectcodeAAC.do?";
		    queryString = queryString + "subject="+subjectCode;
		    findInfo(queryString);
		}	
}
function executeAjax()
{	
    var subjectCode=document.all.bySubjectCode.value;      
    var queryString = "settleincanddecCheckSubjectcodeAAC.do?";  
    queryString = queryString + "subject="+subjectCode;
     findInfo(queryString);
}
function delConfirm(){
return confirm("是否删除该记录?");
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function gotoEnter1(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjaxIn();
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();"
	onContextmenu="return false">
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
											<font color="00B5DB">账套管理</font><font color="00B5DB">&gt;&#25439;&#30410;&#32467;&#36716;&#35774;&#32622;</font>
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
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="132">
										<b class="font14">损 益 结 转 设 置</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="742">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/settleincanddecSaveAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="15%" class="td1">
								待结转科目代码
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td width="42%">
								<html:text name="settleincanddecAF" property="bySubjectCode"
									styleClass="input3"  onkeydown="gotoEnter();" ondblclick="return executeAjax();" onblur="executeAjax();" />
							</td>
							<td width="43%">
								<input type="button" name="Submit2" value="..." class="buttona"
									onclick="seachSujectCode();">
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1" height="18">
								结转科目代码
								<font color="#FF0000">*</font>
							</td>
							<td width="42%" height="18" align="center">
								<html:text name="settleincanddecAF" property="subjectCode"
									styleClass="input3" onkeydown="gotoEnter1();" ondblclick="return executeAjax();" onblur="executeAjaxIn();"/>
							</td>
							<td width="43%" height="18">
								<input type="button" name="Submit22" value="..." class="buttona"
									onclick="seachSujectCode1();">
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkData();">
												<bean:message key="button.sure" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="132">
										<b class="font14">损 益 结 转 列 表</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="742">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/settleincanddecDeleteAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" bgcolor="#76BEE9" border="0" cellspacing="1" cellpadding="3"
						align="center">
						<tr align="center">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td class="td2">
								待结转科目代码
							</td>
							<td class="td2">
								待结转科目名称
							</td>
							<td class="td2">
								结转后科目代码
							</td>
							<td class="td2">
								结转后科目名称
							</td>
						</tr>
						<logic:notEmpty name="settleincanddecAF" property="list">
						<%
							int j=0;
							String strClass="";
						 %>
							<logic:iterate id="element" name="settleincanddecAF"
								property="list" indexId="i">
								<%	j++;
								if (j%2==0) {strClass = "td8";
								}
							    else {strClass = "td9";
							    }%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>",idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' 
									class="<%=strClass %>">
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="element" property="id"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="bySubjectCode" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="bySubjectName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="subjectCode" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="subjectName" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="settleincanddecAF" property="list">
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
												<jsp:param name="url" value="settleincanddecShowAC.do" />
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
									<logic:empty name="settleincanddecAF" property="list">
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												disabled="true">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
									</logic:empty>
									<logic:notEmpty name="settleincanddecAF" property="list">
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona" onclick="return delConfirm();">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
