<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%
	String path = request.getContextPath();
	String isFinished = (String) request.getAttribute("isFinished");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

	<title>凭证查询</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script language="javascript">
	function sun(){
		if(confirm("是否复核？")){
			if(document.credencePopShowAF.elements["credencePopInfoDTO.oldCredenceNum"].value==''){
				alert("附单据不能为空！");
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
	
	function reportErrors() {
		var flag = "<%=isFinished%>";
		if(flag=="1"){
			window.close();
			window.opener.document.Form1.submit();
		}
		<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		window.opener.alert(message);
		</logic:messagesPresent>
	}
	function buttonForward(){
	 	var credenceId=document.all.credenceId.value;
	 	window.open('<%=request.getContextPath()%>/sysfinance/queryFlowShowAC.do?credenceId='+credenceId,'','width=900,height=400,top='+(window.screen.availHeight-400)/2+',left='+(window.screen.availWidth-900)/2+',scrollbars=yes'); 
		return false;
	}
	</script>
	</head>

	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
		onload="reportErrors();">
		<html:form action="/credencePopPrintAC">
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
													<font color="00B5DB">账户处理&gt;凭证录入</font>
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
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr align="center">
								<td colspan="8" class="td1" align="left">
									<table width="100%" border="0" cellspacing="3" cellpadding="0">
										<tr>
											<td class=td1 width="9%">
												所属办事处：
											</td>
											<td class="td4" width="31%">
												<html:text name="credencePopShowAF"
													property="credencePopInfoDTO.office" styleClass="input7"
													readonly="true" />
											</td>
											<td width="8%">
												&nbsp;
											</td>
											<td class="td4" width="10%">
												&nbsp;
											</td>
											<td width="12%">
												&nbsp;
											</td>
											<td class="td4" width="30%">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr align="center">
								<td colspan="8" class="td1">
									<b><bean:write name="credencePopShowAF"
											property="credencePopInfoDTO.paramExplain" />凭证</b>
								</td>
							</tr>
							<tr align="center">
								<td width="10%" class="td1">
									凭证字
								</td>
								<td class="td1">
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.credenceCharacter"
										styleClass="input7" readonly="true" />
								</td>
								<td width="13%" class="td1">
									附单据
								</td>
								<td class="td1">
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.oldCredenceNum"
										styleClass="input7" />
								</td>
								<td width="13%" class=td1>
									日期
								</td>
								<td class="td1">
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.credenceDate" styleClass="input7"
										readonly="true" />
								</td>
								<td width="13%" class=td1>
									凭证号
								</td>
								<td class="td1">
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.credenceNum" styleClass="input7"
										readonly="true" />
								</td>
							</tr>
						</table>
						<table width="95%" border="1" cellspacing="0" cellpadding="0"
							align="center" bordercolordark="#FFFFFF"
							bordercolorlight="#00B5DB">
							<tr align="center">
								<td height="23">
									摘要
								</td>
								<logic:notEqual name="Bookid" value="2">
									<td height="23">
										自由摘要
									</td>
								</logic:notEqual>
								<td height="23">
									结算号
								</td>
								<td height="23">
									科目代码
								</td>
								<td height="23" width="90">
									科目名称
								</td>
								<td height="23">
									借方金额
								</td>
								<td height="23">
									贷方金额
								</td>
							</tr>
							<logic:notEmpty name="credencePopShowAF" property="list">
								<logic:iterate id="credencePopListDTO" name="credencePopShowAF"
									property="list" indexId="i">
									<tr>
										<td height="23">
											<p>
												<logic:empty name="credencePopListDTO" property="summay">&nbsp;</logic:empty>
												<logic:notEmpty name="credencePopListDTO" property="summay">
													<bean:write name="credencePopListDTO" property="summay" />
												</logic:notEmpty>
											</p>
										</td>
										<td height="23">
											<p>
												<logic:empty name="credencePopListDTO" property="freeSummay">&nbsp;</logic:empty>
												<logic:notEmpty name="credencePopListDTO"
													property="freeSummay">
													<bean:write name="credencePopListDTO" property="freeSummay" />
												</logic:notEmpty>
											</p>
										</td>
										<td height="23">
											<p>
												<logic:empty name="credencePopListDTO" property="settnum">&nbsp;</logic:empty>
												<logic:notEmpty name="credencePopListDTO" property="settnum">
													<bean:write name="credencePopListDTO" property="settnum" />
												</logic:notEmpty>
											</p>
										</td>
										<td height="23">
											<p>
												<logic:empty name="credencePopListDTO"
													property="subjectCode">&nbsp;</logic:empty>
												<logic:notEmpty name="credencePopListDTO"
													property="subjectCode">
													<bean:write name="credencePopListDTO"
														property="subjectCode" />
												</logic:notEmpty>
											</p>
										</td>
										<td height="23">
											<p>
												<bean:write name="credencePopListDTO" property="subjectName" />
											</p>
										</td>
										<td height="23" align="right"
											background="<%=path%>/img/bg_cwline.gif">
											<p>
												<bean:write name="credencePopListDTO" property="debit" />
											</p>
										</td>
										<td height="23" align="right"
											background="<%=path%>/img/bg_cwline.gif">
											<p>
												<bean:write name="credencePopListDTO" property="credit" />
											</p>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<tr>
								<td height="23" colspan="5" align="right">
									合计:
								</td>
								<td height="23" align="right" width="97"
									background="<%=path%>/img/bg_cwline.gif">
									<bean:write name="credencePopShowAF" property="sumDebit" />
								</td>
								<td height="23" align="right" width="97"
									background="<%=path%>/img/bg_cwline.gif">
									<bean:write name="credencePopShowAF" property="sumCredit" />
								</td>
							</tr>
						</table>
						<table border="0" width="95%" id="table1" cellspacing="1"
							cellpadding="3" align="center">
							<tr align="center">
								<td width="13%" class="td1" height="33">
									结算方式
								</td>
								<td class="td1" height="33">
									<html:hidden name="credencePopShowAF" property="credenceId" />
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.settType" styleClass="input7"
										readonly="true" />
								</td>
								<td width="13%" class=td1 height="33">
									结算号
								</td>
								<td class="td1" height="33">
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.settNum" styleClass="input7"
										readonly="true" />
								</td>
								<td width="13%" class="td1" height="33">
									结算日期
								</td>
								<td class="td1" height="33">
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.settDate" styleClass="input7"
										readonly="true" />
								</td>
							</tr>
							<tr align="center">
								<td width="13%" class="td1">
									审核
								</td>
								<td class="td1">
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.checkpsn" styleClass="input7"
										readonly="true" />
								</td>
								<td width="13%" class="td1">
									过账
								</td>
								<td class="td1">
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.clearpsn" styleClass="input7"
										readonly="true" />
								</td>
								<td width="13%" class="td1">
									制单
								</td>
								<td class="td1">
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.makebill" styleClass="input7"
										readonly="true" />
								</td>
							</tr>
							<tr align="center">
								<td width="13%" class="td1">
									出纳
								</td>
								<td class="td1">
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.accountpsn" styleClass="input7"
										readonly="true" />
								</td>
								<td width="13%" class="td1">
									主管会计
								</td>
								<td class="td1">
									<html:text name="credencePopShowAF"
										property="credencePopInfoDTO.accountCharge"
										styleClass="input7" readonly="true" />
								</td>
								<td width="13%" class="td1"></td>
								<td class="td1">
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<logic:notEmpty name="credencePopShowAF" property="check">
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														onclick="return sun();">
														<bean:message key="button.checkc" />
													</html:submit>
												</td>
											</logic:notEmpty>
											<td width="100" align="center">
												<input type="submit" name="method" value="查看流水账"
													class="buttonb" onclick="return buttonForward();">
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="70">
												<input type="button" name="Submit42" value="关闭"
													class="buttona" onClick="javascript:window.close();">
											</td>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
