<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%
String path = request.getContextPath();
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
		<script src="<%=path%>/js/common.js" />

		<script language="JavaScript">	
</script>
	</head>
	<script language="JavaScript">

function findInfo(){
	var id=getCheckId();
	location.href='<%=path%>/sysloan/loancontractqueryThShowAC.do?pl123IdWY='+id;
	
}
</script>

	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="3%" align="right" valign="middle">
					<table width="21" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryTeShowAC.do" class=a2>借款合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryTfShowAC.do" class=a2>抵押合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryTgShowAC.do" class=a2>质押合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttonbl1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryThShowAC.do" class=a2>保证人信息</a>
							</td>
						</tr>
					</table>
				</td>
				<td width="97%" align="left" valign="top">
					<table width="98%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="<%=path%>/img/table_left.gif" width="7" height="37">
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" width="10">
								&nbsp;
							</td>
							<td background="<%=path%>/img/table_bg_line.gif">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											<a href="<%=path%>/sysloan/loancontractqueryTaShowAC.do" class=a2>借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loancontractqueryTbShowAC.do" class=a2>共同借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loancontractqueryTcShowAC.do" class=a2>购房信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loancontractqueryTdShowAC.do" class=a2>借款人额度信息</a>
										</td>
										<td width="112" height="37" align="center"
											style="PADDING-top: 7px" valign="top"></td>
									</tr>
								</table>
							</td>
							<td width="9">
								<img src="<%=path%>/img/table_right.gif" width="9" height="37">
							</td>
						</tr>
					</table>
					<table width="98%" border="0" cellspacing="0" cellpadding="0"
						height="500">
						<tr>
							<td valign="top" class=td3>
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="166" class="font14">
														<b>保证人信息</b>
													</td>
													<td height="22" background="<%=path%>/img/bg2.gif"
														align="center" width="738">
														&nbsp;
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="95%" id="table9" cellspacing=1 cellpadding=3
									align="center">
									<tr>
										<td width="17%" class=td1>
											合同编号
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF"
												property="contractId" styleClass="input3" readonly="true" />
										</td>
										<td class="td1" width="17%">
											借款人姓名
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="debitter"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="17%" class=td1>
											职工编号
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="empId"
												styleClass="input3" readonly="true" />
										</td>
										<td width="17%" class=td1>
											职工姓名
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="empName"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											证件类型
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="cardKind"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											证件号码
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="cardNum"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											性别
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="sex"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											出生日期
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="birthday"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											月工资额
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="salary"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											月缴存额
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="monthPay"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											账户余额
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="balance"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											账户状态
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="empSt"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											固定电话
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="tel"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											移动电话
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="mobile"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											家庭电话
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="homeTel"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											家庭住址
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="homeAddr"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											邮政编码
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="homeMai"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											单位编号
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="orgId"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											单位名称
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="orgName"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											单位地址
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="orgAddr"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											单位电话
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="orgTel"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											邮政编码
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTdAF" property="orgMail"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
								</table>
								<form name="idAF" style="margin: 0">
									<table width="95%" border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
											<td height="35">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="22" bgcolor="#CCCCCC" align="center"
															width="202">
															<b class="font14">保证人信息列表</b>
														</td>
														<td width="703" height="22" align="center"
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
										<tr>
											<td align="center" height="23" bgcolor="C4F0FF">
												&nbsp;
											</td>
											<td align="center" class=td2>
												职工姓名
											</td>
											<td align="center" class=td2>
												证件号码
											</td>
											<td align="center" class=td2>
												账户余额
											</td>
											<td align="center" class=td2>
												月工资额
											</td>
											<td align="center" class=td2>
												月缴存额
											</td>
											<td align="center" class=td2>
												单位名称
											</td>
											<td align="center" class=td2>
												状态
											</td>
										</tr>
										<logic:notEmpty name="theEndorsecontractTdAF" property="list">
											<logic:iterate name="theEndorsecontractTdAF" property="list"
												id="element" indexId="i">
												<tr id="tr<%=i%>"
													onclick='gotoClick("tr<%=i%>","s<%=i%>", idAF);'
													onMouseOver='this.style.background="#eaeaea"'
													onMouseOut='gotoColor("tr<%=i%>","s<%=i%>", idAF);'
													class=td4 onDblClick='findInfo();'>
													<td valign="top">
														<p align="left">
															<input id="s<%=i%>" type="radio" name="id"
																value="<bean:write name="element" property="id"/>"
																<%if(new Integer(0).equals(i)) out.print("checked"); %>>
														</p>
													</td>
													<td>
														<bean:write name="element" property="empName" />
													</td>
													<td>
														<bean:write name="element" property="cardNum" />
													</td>
													<td>
														<bean:write name="element" property="balance" />
													</td>
													<td>
														<bean:write name="element" property="salary" />
													</td>
													<td>
														<bean:write name="element" property="monthPay" />
													</td>
													<td>
														<bean:write name="element" property="orgName" />
													</td>
													<td>
														<bean:write name="element" property="status" />
													</td>
												</tr>
												<tr>
													<td colspan="9" class=td5></td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
										<logic:empty name="theEndorsecontractTdAF" property="list">
											<tr>
												<td colspan="16" height="30" style="color:red">
													没有找到与条件相符合的结果！
												</td>
											</tr>
											<tr>
												<td colspan="16" class=td5></td>
											</tr>
										</logic:empty>
									</table>
								</form>
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr class="td1">
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
										align="center">
										<tr valign="bottom">
											<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
												<table border="0" cellspacing="0" cellpadding="0">
													<tr>
													<logic:empty name="theEndorsecontractTdAF" property="list">
														<td width="73" align="center" valign="middle">
															<html:submit styleClass="buttona" onclick="location.href='loancontractqueryThPrintAC.do'" disabled="true">
																<bean:message key="button.print" />
															</html:submit>
														</td>
														</logic:empty>
														<logic:notEmpty name="theEndorsecontractTdAF" property="list">
														<td width="73" align="center" valign="middle">
															<html:submit styleClass="buttona" onclick="location.href='loancontractqueryThPrintAC.do'">
																<bean:message key="button.print" />
															</html:submit>
														</td>
														</logic:notEmpty>
														<td width="73" align="center" valign="middle">
															<input type="button" name="Submit3" value="关闭"
																class="buttona" onclick="window.close()" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>

