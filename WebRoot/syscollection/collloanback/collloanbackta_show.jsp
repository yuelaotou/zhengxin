<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.collloanback.action.CollLoanbackTaShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			CollLoanbackTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>公积金还贷</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
function reportErrors(message) {
	if(message!=null){
		alert(message);
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function on_Submit(eee){
    if(document.forms[0].theFile.value==""){
	    alert("请选择导入文件！！！");
	  	return false;
	  } else {
	    document.forms[0].url.value=document.forms[0].theFile.value.trim();
	       setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
	    return true;
	  }
} 
function onsearch(){
	var officeCode = document.all.officeCode.value;
	var collectionBankId = document.all.collectionBankId.value;	
	if(officeCode==""){
		alert('请选择办事处!');
		return false;
	}
	if(collectionBankId==""){
		alert('请选择银行!');
		return false;
	}
}
function a(){
	var p = "<%=path%>";
	officeAjax(p);
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="reportErrors();">
	<jsp:include flush="true" page="/waiting.inc" />
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
						<td background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										办理公积金还贷
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="collLoanbackTbForwardAC.do" class=a2>公积金还贷维护</a>
									</td>
								</tr>
							</table>
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="200" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="228" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">公积金还贷</font>
									</td>
									<td width="35" class=font14>
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
				<html:form action="/collLoanbackTaUpMaintainAC.do"
					styleClass="margin: 0" enctype="multipart/form-data">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="176">
											<b class="font14">办理公积金还贷业务</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="734">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="14%" class=td1>
								选择文件
							</td>
							<td width="30%" class="td4">
								<html:file name="collLoanbackTaAF" property="theFile" size="50"
									maxlength="100" styleClass="input3" />
								<html:hidden property="url" />
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
												onclick="return on_Submit(this);">
												<bean:message key="button.import" />
											</html:submit>
										</td>

									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td class=td1 width="14%">
								办事处
								<font color="#ff0000"><span>*</span>
								</font>
							</td>
							<td width="30%" class="td4">
								<html:select property="officeCode" styleClass="input4"
									onchange="a();">
									<option value=""></option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td class=td1 width="14%">
								银行
								<font color="#ff0000"><span>*</span>
								</font>
							</td>
							<td width="30%" class="td4">
								<html:select property="collectionBankId" styleClass="input4">
									<option value=""></option>
									<html:options collection="loanbankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table width="167" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return onsearch();">
												<bean:message key="button.search" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return onsearch();">
												<bean:message key="button.return.export" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/collLoanbackTaDownAC.do" styleClass="margin: 0">
					<table>
						<tr>

							<td class=h4>
								合计: 预扣款人数:
								<u><bean:write name="collLoanbackTaAF" property="p_count" />&nbsp;</u>
								应扣款金额:
								<u><bean:write name="collLoanbackTaAF" property="m_sum" />&nbsp;</u>
								实扣款人数:
								<u><bean:write name="collLoanbackTaAF" property="yg_count" />&nbsp;</u>
								实扣款金额:
								<u><bean:write name="collLoanbackTaAF" property="yg_sum" />&nbsp;</u>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="173">
											<b class="font14">导入文件批次号：<bean:write
													name="collLoanbackTaAF" property="batchNum" /> </b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="694">
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
						<tr align="center">
							<td bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td class="td2">
								扣款账号
							</td>
							<td class="td2">
								合同编号
							</td>
							<td class="td2">
								单位编号
							</td>
							<td class="td2">
								职工编号
							</td>
							<td class="td2">
								借款人
							</td>
							<td class="td2">
								账户余额
							</td>
							<td class="td2">
								应扣金额
							</td>
							<td class="td2">
								还款年月
							</td>
							<td class="td2">
								扣款标识（0）
							</td>
						</tr>
						<logic:notEmpty name="collLoanbackTaAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="collLoanbackTaAF" property="list"
								indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr class="<%=strClass %>" align="center">
									<td>
										&nbsp;
									</td>
									<td>
										<bean:write name="e" property="kouLoanAcc" />
									</td>
									<td>
										<bean:write name="e" property="contractId" />
									</td>
									<td>
										<bean:write name="e" property="orgid" format="0000000000" />
									</td>
									<td>
										<bean:write name="e" property="empid" format="000000" />
									</td>
									<td>
										<bean:write name="e" property="borrowerName" />
									</td>
									<td>
										<bean:write name="e" property="balance" />
									</td>
									<td>
										<bean:write name="e" property="shouldCorpus" />
									</td>
									<td>
										<bean:write name="e" property="yearMonth" />
									</td>
									<td>
										<bean:write name="e" property="flag" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="collLoanbackTaAF" property="list">
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
											<br>
										</td>
										<td align="right">
											<jsp:include page="../../inc/pagination.jsp">
												<jsp:param name="url" value="collLoanbackTaShowAC.do" />
											</jsp:include>
											<br>
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
										<td width="70">
											<logic:notEmpty name="collLoanbackTaAF" property="list">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.kou.money" />
												</html:submit>
											</logic:notEmpty>
											<logic:empty name="collLoanbackTaAF" property="list">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.kou.money" />
												</html:submit>
											</logic:empty>
										</td>
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

