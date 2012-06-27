<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>

<%@ page
	import="org.xpup.hafmis.sysloan.loancallback.destoryback.action.*"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			DestoryBackTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>特殊业务处理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script>
function gotoDelcheck() {
	if(confirm("是否删除该信息？")){
		return true;
	} else {
		return false;
	}
}
function gotoPincheck() {
	if(confirm("是否确认打印？")){
		return true;
	} else {
		return false;
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function gettr2(trindex) {
  tr=trindex;
  sureType();
}
function sureType(){ 
	
     var id  = document.getElementById(tr).childNodes[8].innerHTML;
     
     window.open('<%=request.getContextPath()%>/sysloan/destoryBackTbWindowAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 
 }

</script>

<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()" 
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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="destoryBackTaShowAC.do" class=a2>办理核销收回</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										核销收回维护
									</td>
								</tr>
							</table>
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="185" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">回收贷款&gt;核销收回</font>
									</td>
									<td width="78" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="205">
										<b class="font14">查 询 条 件</b>
									</td>
									<td width="720" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/destoryBackTbFindAC.do" method="post"
					style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								贷款账号
							</td>
							<td width="33%">
								<html:text onkeydown="enterNextFocus1();"name="destoryBackTbAF" property="loanKouAcc"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%">
								<html:text onkeydown="enterNextFocus1();"name="destoryBackTbAF" property="contractId"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%">
								<html:text onkeydown="enterNextFocus1();"name="destoryBackTbAF" property="borrowerName"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="33%">
								<html:text onkeydown="enterNextFocus1();"name="destoryBackTbAF" property="cardNum"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								发放银行
							</td>
							<td align="center">
								<html:select onkeydown="enterNextFocus1();"name="destoryBackTbAF" property="loanBankName"
									styleClass="input3">
									<html:option value=""></html:option>
									<html:options collection="loanBankNameList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								业务状态
							</td>
							<td width="33%" align="center" colspan="3">
								<html:select onkeydown="enterNextFocus1();"name="destoryBackTbAF" property="bizSt"
									styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizStMap"
										name="destoryBackTbAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								<span class="td2">凭证编号</span>
							</td>
							<td width="33%">
								<html:text onkeydown="enterNextFocus1();"name="destoryBackTbAF" property="docNum"
									styleClass="input3" />
							</td>
							<td width="11%" class="td1">
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：回收金额
								<u>：<bean:write name="destoryBackTbAF"
										property="reclaimCorpusTotle" /> </u>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="204">
										<b class="font14">核销收回列表</b>
									</td>
									<td width="721" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/destoryBackTbMaintainAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								贷款账号
							</td>
							<td align="center" class=td2>
								凭证编号
							</td>
							<td align="center" class=td2>
								合同编号
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('borrowername')">借款人姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="borrowername">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								证件号码
							</td>
							<td align="center" class=td2>
								收回金额
							</td>
							<td align="center" class=td2>
								业务状态
							</td>
						</tr>
						<logic:notEmpty name="destoryBackTbAF" property="list">
							<logic:iterate name="destoryBackTbAF" property="list"
								id="element" indexId="i">
								<tr align="left" id="tr<%=i%>"
									onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColor("tr<%=i%>","s<%=i%>", idAF);' class=td4
									onDblClick="">
									<td>
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="flowHeadId"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<p>
											<bean:write name="element" property="loanKouAcc" />
										</p>
									</td>
									<td>
										<p>
											<bean:write name="element" property="docNum" />
										</p>
									</td>
									<td>
										<bean:write name="element" property="contractId" />
									</td>
									<td>
										<p>
											<a href="#" onClick="gettr2('tr<%=i%>');" /> <bean:write
													name="element" property="borrowerName" />
											</a>
										</p>
									</td>
									<td>
										<p>
											<bean:write name="element" property="cardNum" />
										</p>
									</td>
									<td>
										<p>
											<bean:write name="element" property="reclaimCorpus" />
										</p>
									</td>
									<td>
										<p>
											<bean:write name="element" property="bizSt" />
										</p>
									</td>
									<td style="display:none">
										<bean:write name="element" property="flowHeadId" />
									</td>
								<tr>
									<td colspan="16" class=td5></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="destoryBackTbAF" property="list">
							<tr>
								<td colspan="9" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>
							<tr>
								<td colspan="9" class=td5></td>
							</tr>
						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="center">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr align="center">
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="/inc/pagination.jsp">
												<jsp:param name="url" value="destoryBackTbShowAC.do" />
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
									<logic:notEmpty name="destoryBackTbAF" property="list">
										<tr>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													onclick="return gotoDelcheck();">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td>
												<html:submit property="method" styleClass="buttona"
													onclick="return gotoPincheck();">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</tr>
									</logic:notEmpty>
									<logic:empty name="destoryBackTbAF" property="list">
										<tr>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td>
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
		<form action="destoryBackTbShowAC.do" method="POST" name="Form1"
			id="Form1">
		</form>
	</table>
</body>
</html:html>
