<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	List photoURLList = (List) request.getSession().getAttribute(
			"photoURLList");
	String photo_urlAssist = photoURLList.get(0) + "";
	String photo_urlBuyhouse = photoURLList.get(1) + "";
	String photo_urlLimit = photoURLList.get(2) + "";
	String photo_urlContract = photoURLList.get(3) + "";
	String photo_urlMortgage = photoURLList.get(4) + "";
	String photo_urlBorrower = photoURLList.get(5) + "";
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
		<script src="<%=path%>/js/common.js" />
		
		
		<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
		<script language="JavaScript">	
function gotoBorrower(){
		javascript:excHz("<%=photo_urlBorrower%>");
	}
	function gotoAssist(){
		javascript:excHz("<%=photo_urlAssist%>");
	}
	function gotoBuyhouse(){
		javascript:excHz("<%=photo_urlBuyhouse%>");
	}
	function gotoLimit(){
		javascript:excHz("<%=photo_urlLimit%>");
	}
	function gotoContract(){
		javascript:excHz("<%=photo_urlContract%>");
	}
	function gotoMortgage(){
		javascript:excHz("<%=photo_urlMortgage%>");
	}
</script>
<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
	</head>
	<script language="JavaScript">

function findInfo(){
	var id=getCheckId();
	location.href='<%=path%>/sysloan/to_LoanVIPCheckTbShowAC.do?pl121IdWY='+id;
	
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
								<a href="<%=path%>/sysloan/to_LoanVIPCheckTaShowAC.do" class=a2>借款合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttonbl1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/to_LoanVIPCheckTbShowAC.do" class=a2>抵押合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/to_LoanVIPCheckTcShowAC.do" class=a2>质押合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/to_LoanVIPCheckTdShowAC.do" class=a2>保证人信息</a>
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
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/showLoanVIPCheckTaAC.do" class=a2>借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/showLoanVIPCheckTbAC.do" class=a2>共同借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/showLoanVIPCheckTcAC.do" class=a2>购房信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/showLoanVIPCheckTdAC.do" class=a2>借款人额度信息</a>
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
														<b>抵押信息</b>
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
										<td class="td4">
											<html:text name="theEndorsecontractTbAF"
												property="contractId" styleClass="input3" readonly="true" />
										</td>
										<td class="td1" width="17%">
											借款人姓名
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTbAF" property="debitter"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											抵押人
										</td>
										<td class="td4">
											<font color="#FF0000"> <html:text
													name="theEndorsecontractTbAF" property="pledgePerson"
													styleClass="input3" readonly="true" /> </font>
										</td>
										<td class=td1 width="17%">
											抵押权人
										</td>
										<td class="td4" width="33%">
											<font color="#FF0000"> <html:text
													name="theEndorsecontractTbAF" property="office"
													styleClass="input3" readonly="true" /> </font>
										</td>
									</tr>
									<tr>
										<td width="17%" class=td1>
											抵押合同编号
										</td>
										<td class="td4">
											<font color="#FF0000"> <html:text
													name="theEndorsecontractTbAF" property="pledgeContractId"
													styleClass="input3" readonly="true" /> </font>
										</td>
										<td width="17%" class=td1>
											担保公司名称
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTbAF"
												property="assistantOrgName" styleClass="input3"
												readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											抵押物名称
										</td>
										<td class="td4">
											<html:text name="theEndorsecontractTbAF"
												property="pledgeMatterName" styleClass="input3"
												readonly="true" />
										</td>
										<td class=td1 width="17%">
											所有权证编号
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTbAF" property="paperNum"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											所有权证名称
										</td>
										<td class="td4">
											<html:text name="theEndorsecontractTbAF" property="paperName"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											所有权人姓名
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTbAF"
												property="paperPersonName" styleClass="input3"
												readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											所有权人证件类型
										</td>
										<td class="td4">
											<html:text name="theEndorsecontractTbAF" property="cardKind"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											所有权人证件号码
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTbAF" property="carNum"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="17%">
											所有权人固定电话
										</td>
										<td class="td4">
											<html:text name="theEndorsecontractTbAF" property="tel"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="17%">
											所有权人移动电话
										</td>
										<td class="td4" width="33%">
											<html:text name="theEndorsecontractTbAF" property="mobile"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="17%" class=td1>
											抵押物地址
										</td>
										<td height="31" class="td4">
											<html:text name="theEndorsecontractTbAF"
												property="pledgeAddr" styleClass="input3" readonly="true" />
										</td>
										<td width="17%" class=td1>
											建筑面积（M
											<sup>
												2
											</sup>
											）
										</td>
										<td height="31" class="td4" width="33%">
											<html:text name="theEndorsecontractTbAF" property="area"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="17%" class=td1>
											购房合同编号
										</td>
										<td height="31" class="td4">
											<html:text name="theEndorsecontractTbAF"
												property="buyHouseContractId" styleClass="input3"
												readonly="true" />
										</td>
										<td width="17%" class=td1>
											&nbsp;
										</td>
										<td height="31" class="td4" width="33%">
											<input type="text" class="input3" readonly="true">
										</td>
									</tr>
									<tr>
										<td width="17%" class=td1>
											抵押值
										</td>
										<td height="31" class="td4">
											<html:text name="theEndorsecontractTbAF"
												property="pledgeValue" styleClass="input3" readonly="true" />
										</td>
										<td width="17%" class=td1>
											评估值
										</td>
										<td height="31" class="td4" width="33%">
											<html:text name="theEndorsecontractTbAF"
												property="evaluateValue" styleClass="input3" readonly="true" />
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
															<b class="font14">抵押信息列表</b>
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
									<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
										cellpadding="3" align="center">
										<tr align="center" bgcolor="C4F0FF">
											<td height="23" bgcolor="C4F0FF">
												&nbsp;
											</td>
											<td align="center" class=td2>
												抵押人
											</td>
											<td align="center" class=td2>
												抵押权人
											</td>
											<td align="center" class=td2>
												抵押物名称
											</td>
											<td align="center" class=td2>
												抵押物地址
											</td>
											<td align="center" class=td2>
												抵押值
											</td>
											<td align="center" class=td2>
												评估值
											</td>
											<td align="center" class=td2>
												状态
											</td>
											<td align="center" class=td2>
												浏览附件
											</td>
										</tr>
										<logic:notEmpty name="theEndorsecontractTbAF" property="list">
											<%
													int j = 0;
													String strClass = "";
											%>
											<logic:iterate name="theEndorsecontractTbAF" property="list"
												id="element" indexId="i">
												<%
															j++;
															if (j % 2 == 0) {
														strClass = "td8";
															} else {
														strClass = "td9";
															}
												%>
												<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>", idAF);'
													onMouseOver='this.style.background="#eaeaea"'
													onMouseOut='gotoColorpp("<%=i%>", idAF);'
													class="<%=strClass%>" onDblClick='findInfo();'>
													<td valign="top">
														<p align="left">
															<input id="s<%=i%>" type="radio" name="id"
																value="<bean:write name="element" property="id"/>"
																<%if(new Integer(0).equals(i)) out.print("checked"); %>>
														</p>
													</td>
													<td>
														<bean:write name="element" property="debitter" />
													</td>
													<td>
														<bean:write name="element" property="office" />
													</td>
													<td>
														<bean:write name="element" property="pledgeMatterName" />
													</td>
													<td>
														<bean:write name="element" property="pledgeAddr" />
													</td>
													<td>
														<bean:write name="element" property="pledgeValue" />
													</td>
													<td>
														<bean:write name="element" property="evaluateValue" />
													</td>
													<td>
														<bean:write name="element" property="status" />
													</td>
													<td>
														<a
															href='javascript:excHz("<bean:write name="element" property="photoUrl"/>");'><img
																src="<%=path%>/img/lookinfo.gif" width="37" height="24">
														</a>
													</td>
												</tr>

											</logic:iterate>
										</logic:notEmpty>

										<logic:empty name="theEndorsecontractTbAF" property="list">
											<tr>
												<td colspan="16" height="30" style="color:red">
													没有找到与条件相符合的结果！
												</td>
											</tr>

										</logic:empty>
									</table>
								</form>
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr valign="bottom">
										<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
											<table border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="110">
														<html:submit property="look" styleClass="buttonc"
															onclick="gotoBorrower();">
															<bean:message key="button.borrower.certificate" />
														</html:submit>
													</td>
<%--													<td width="110">--%>
<%--														<html:submit property="look" styleClass="buttonc"--%>
<%--															onclick="gotoAssist();">--%>
<%--															<bean:message key="button.borrower.assist.certificate" />--%>
<%--														</html:submit>--%>
<%--													</td>--%>
<%--													<td width="110">--%>
<%--														<html:submit property="look" styleClass="buttonc"--%>
<%--															onclick="gotoBuyhouse();">--%>
<%--															<bean:message key="button.buyhouse.certificate" />--%>
<%--														</html:submit>--%>
<%--													</td>--%>
													<td width="110">
														<html:submit property="look" styleClass="buttonc"
															onclick="gotoContract();">
															<bean:message key="button.borrower.contract.certificate" />
														</html:submit>
													</td>
													<td width="110">
														<html:submit property="look" styleClass="buttonc"
															onclick="gotoLimit();">
															<bean:message key="button.borrower.limit.certificate" />
														</html:submit>
													</td>
													<td width="110">
														<html:submit property="look" styleClass="buttonc"
															onclick="gotoMortgage();">
															<bean:message key="button.borrower.mortgage.certificate" />
														</html:submit>
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

