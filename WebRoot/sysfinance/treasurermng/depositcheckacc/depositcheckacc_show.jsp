<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Integer bdcsize = (Integer) request.getAttribute("bdcsize");
	Integer bcasize = (Integer) request.getAttribute("bcasize");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>银行存款对账单</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
function tosubject(){
	gotoSubjectpop('0','<%=path%>','2');
}
function onsearch(){
	var settDateSt=document.forms[0].settDateSt.value.trim();
	var settDateEnd=document.forms[0].settDateEnd.value.trim();
	if(settDateSt!=""){
		if(!checkDate(document.forms[0].settDateSt)){
			document.forms[0].settDateSt.value="";
			return false;
		}
	}
	if(settDateEnd!=""){
		if(!checkDate(document.forms[0].settDateEnd)){
			document.forms[0].settDateEnd.value="";
			return false;
		}
	}
	if((settDateSt!=""&&settDateEnd=="")||(settDateSt==""&&settDateEnd!="")){
		alert('起始日期和终止日期都必须输入！');
		return false;
	}
	return true;
}

</script>
<body bgcolor="#FFFFFF" text="#000000"  onContextmenu="return false"
	style="overflow:hidden">

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
							<a href="出纳管理_银行对账单_简约风格.htm"></a>
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
											<font color="00B5DB">出纳管理&gt;银行存款对账单</font>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="131">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="727">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/depositCheckAccFindAC.do" styleClass="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">

						<tr>
							<td width="15%" class="td1">
								结算日期
							</td>
							<td width="15%">
								<html:text name="depositCheckAccAF" property="settDateSt"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="depositCheckAccAF" property="settDateEnd"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%" class="td1">
								科目
							</td>
							<td width="20%" colspan="2">
								<html:text name="depositCheckAccAF" property="subjectCode"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%">
								<input type="button" name="Submit12" value="..." class="buttona"
									onclick="tosubject();">
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return onsearch();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/depositCheckAccMaintainAC.do"
					styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="134">
											<b class="font14">银 行 日 记 账</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="724">
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
					<div style="overflow:auto;height:175">
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3"
							align="center">
							<tr align="center">
								<td class="td2">
									结算日期
								</td>
								<td class="td2">
									科目
								</td>
								<td class="td2">
									结算方式
								</td>
								<td class="td2">
									结算号
								</td>
								<td class="td2">
									凭证字号
								</td>
								<td class="td2">
									借方金额
								</td>
								<td class="td2">
									贷方金额
								</td>
								<td class="td2">
									凭证日期
								</td>
							</tr>

							<logic:notEmpty name="depositCheckAccAF"
								property="bankDayClearList">
								<%
								int j=0;
								String strClass="";
							 	%>
								<logic:iterate id="e" name="depositCheckAccAF"
									property="bankDayClearList" indexId="i">
									<%	j++;
									if (j%2==0) {strClass = "td8";
									}
								    else {strClass = "td9";
								    }%>
									<logic:equal name="e" property="type" value="">
										<tr class="<%=strClass %>">
											<td>
												<bean:write name="e" property="settDate" />
											</td>
											<td>
												<bean:write name="e" property="subjectCode" />
											</td>
											<td>
												<bean:write name="e" property="temp_settType" />
											</td>
											<td>
												<bean:write name="e" property="settNum" />
											</td>
											<td>
												<bean:write name="e" property="credenceChaNum" />
											</td>
											<td>
												<bean:write name="e" property="debit" />
											</td>
											<td>
												<bean:write name="e" property="credit" />
											</td>
											<td>
												<bean:write name="e" property="credenceDate" />
												<input type="hidden" name="contractid"
													value="<bean:write name="e" property="credenceId" />">
											</td>
										</tr>
									</logic:equal>
									<logic:equal name="e" property="type" value="1">
										<tr style="background-color:#2EABE0;">
											<td>
												<bean:write name="e" property="settDate" />
											</td>
											<td>
												<bean:write name="e" property="subjectCode" />
											</td>
											<td>
												<bean:write name="e" property="temp_settType" />
											</td>
											<td>
												<bean:write name="e" property="settNum" />
											</td>
											<td>
												<bean:write name="e" property="credenceChaNum" />
											</td>
											<td>
												<bean:write name="e" property="debit" />
											</td>
											<td>
												<bean:write name="e" property="credit" />
											</td>
											<td>
												<bean:write name="e" property="credenceDate" />
												<input type="hidden" name="contractid"
													value="<bean:write name="e" property="credenceId" />">
											</td>
										</tr>
									</logic:equal>
								</logic:iterate>


							</logic:notEmpty>

							<logic:empty name="depositCheckAccAF" property="bankDayClearList">
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
								<td align="center">
									&nbsp;
								</td>
							</tr>
						</table>

					</div>

					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="136">
											<b class="font14">银 行 对 账 单</b>
										</td>
										<td width="722" height="22" align="center"
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
					<div style="overflow:auto;height:175">
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" 
							align="center">
							<tr align="center">
								<td class="td2">
									结算日期
								</td>
								<td class="td2">
									银行账号
								</td>
								<td class="td2">
									结算方式
								</td>
								<td class="td2">
									结算号
								</td>
								<td class="td2">
									银行借方
								</td>
								<td class="td2">
									银行贷方
								</td>
							</tr>
							<logic:notEmpty name="depositCheckAccAF"
								property="bankCheckAccList">
								<%
								int j=0;
								String strClass="";
							 	%>
								<logic:iterate id="e" name="depositCheckAccAF"
									property="bankCheckAccList" indexId="i">
									<%	j++;
									if (j%2==0) {strClass = "td8";
									}
								    else {strClass = "td9";
								    }%>
									<logic:equal name="e" property="type" value="">
										<tr class="<%=strClass %>">
											<td>
												<bean:write name="e" property="settDate" />
											</td>
											<td>
												<bean:write name="e" property="subjectCode" />
											</td>
											<td>
												<bean:write name="e" property="temp_settType" />
											</td>
											<td>
												<bean:write name="e" property="settNum" />
											</td>
											<td>
												<bean:write name="e" property="debit" />
											</td>
											<td>
												<bean:write name="e" property="credit" />
												<input type="hidden" name="contractid"
													value="<bean:write name="e" property="credenceId" />">
											</td>
										</tr>
									</logic:equal>
									<logic:equal name="e" property="type" value="1">
										<tr style="background-color:#2EABE0;">
											<td>
												<bean:write name="e" property="settDate" />
											</td>
											<td>
												<bean:write name="e" property="subjectCode" />
											</td>
											<td>
												<bean:write name="e" property="temp_settType" />
											</td>
											<td>
												<bean:write name="e" property="settNum" />
											</td>
											<td>
												<bean:write name="e" property="debit" />
											</td>
											<td>
												<bean:write name="e" property="credit" />
												<input type="hidden" name="contractid"
													value="<bean:write name="e" property="credenceId" />">
											</td>
										</tr>
									</logic:equal>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="depositCheckAccAF" property="bankCheckAccList">
								<tr>
									<td colspan="8" height="30" style="color:red">
										没有找到与条件相符合的结果！
										<br>
									</td>
								</tr>
							</logic:empty>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr class="td1">
								<td align="center">
									&nbsp;
								</td>
							</tr>
						</table>
					</div>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<%
													if (bdcsize.equals(new Integer("0"))
													&& bcasize.equals(new Integer("0"))) {
										%>
										<td width="107" align="right">
											<html:submit property="method" styleClass="buttonc"
												disabled="true">
												<bean:message key="button.createbalanceadjust" />
											</html:submit>
										</td>
										<td width="65" align="right">
											<html:submit property="method" styleClass="buttona"
												disabled="true">
												<bean:message key="button.print" />
											</html:submit>
										</td>
										<%
										} else {
										%>
										<td width="107" align="right">
											<html:submit property="method" styleClass="buttonc">
												<bean:message key="button.createbalanceadjust" />
											</html:submit>
										</td>
										<td width="65" align="right">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.print" />
											</html:submit>
										</td>
										<%
										}
										%>
									</tr>
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
