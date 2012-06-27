<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.action.PickMonthReportShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			PickMonthReportShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询--缴存提取统计--公积金提取统计月报表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
</head>

<script language="javascript" type="text/javascript">
function checkdate(){
    var startDate=document.all.startDate.value.trim();
    var endDate=document.all.endDate.value.trim();
    if(startDate=="" && endDate==""){
    	alert("请输入日期");
    	document.all.startDate.focus();
    	return false;
    }else if(startDate!="" && endDate==""){
        alert("请输入结束日期");
    	document.all.startDate.focus();
    	return false;
    }else if(startDate=="" && endDate!=""){
        alert("请输入开始日期");
    	document.all.startDate.focus();
    	return false;
    }else{
      if(!checkDate(document.all.startDate)){
       return false;
      }
      if(!checkDate(document.all.endDate)){
       return false;
      }
     }
}
</script>

<body bgcolor="#FFFFFF" text="#000000">
	<table width="1500" border="0" cellspacing="0" cellpadding="0"
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
							&nbsp;
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询&gt;公积金缴存情况月报表</font>
									</td>
									<td width="115" class=font14>
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
				<html:form action="/pickMonthReportFindAC.do" style="margin:0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="174">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="746">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td width="8%" class="td1">
								办事处
							</td>
							<td width="16%">
								<html:select name="af" property="officeCode" styleClass="input4">
									<option value=""></option>
									<html:options collection="officeList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="8%" class="td1">
								归集银行
							</td>
							<td width="16%">
								<html:select name="af" property="collBank" styleClass="input4">
									<option value=""></option>
									<html:options collection="bankList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="8%"></td>
							<td width="16%"></td>
						</tr>
						<tr>
							<td width="8%" class="td1">
								日期
								<font color="red">*</font>
							</td>
							<td width="16%">
								<html:text name="af" property="startDate" maxlength="8"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="8%" class="td1">
								至
							</td>
							<td width="16%">
								<html:text name="af" property="endDate" maxlength="8"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td colspan="2" align="left">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkdate();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
							<td width="8%"></td>
							<td width="16%"></td>
						</tr>
					</table>

				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="216">
										<b class="font14">公积金提取统计月报表 </b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="688">
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
				<html:form action="/pickMonthReportMaintainAC.do">
					<table width="95%" border="0" cellspacing="1" cellpadding="3"
						align="center" bgcolor="1BA5FF">
						<tr align="center">
							<td class="td1" width="5%" rowspan="2">
								&nbsp;
							</td>
							<td align="center" class="td1" colspan="3">
								购房
							</td>
							<td align="center" class="td1" colspan="3">
								还贷
							</td>
							<td align="center" class="td1" colspan="3">
								其他
							</td>
							<td align="center" class="td1" colspan="3">
								退休
							</td>
							<td align="center" class="td1" colspan="3">
								失业
							</td>
							<td align="center" class="td1" colspan="3">
								死亡
							</td>
							<td align="center" class="td1" colspan="3">
								个贷扣款
							</td>
							<td align="center" class="td1" colspan="2">
								合计
							</td>
						</tr>
						<tr align="center">
							<td align="center" class="td1">
								金额
							</td>
							<td align="center" class="td1">
								人数
							</td>
							<td align="center" class="td1">
								比例
							</td>
							<td align="center" class="td1">
								金额
							</td>
							<td align="center" class="td1">
								人数
							</td>
							<td align="center" class="td1">
								比例
							</td>
							<td align="center" class="td1">
								金额
							</td>
							<td align="center" class="td1">
								人数
							</td>
							<td align="center" class="td1">
								比例
							</td>
							<td align="center" class="td1">
								金额
							</td>
							<td align="center" class="td1">
								人数
							</td>
							<td align="center" class="td1">
								比例
							</td>
							<td align="center" class="td1">
								金额
							</td>
							<td align="center" class="td1">
								人数
							</td>
							<td align="center" class="td1">
								比例
							</td>
							<td align="center" class="td1">
								金额
							</td>
							<td align="center" class="td1">
								人数
							</td>
							<td align="center" class="td1">
								比例
							</td>
							<td align="center" class="td1">
								金额
							</td>
							<td align="center" class="td1">
								人数
							</td>
							<td align="center" class="td1">
								比例
							</td>
							<td align="center" class="td1">
								金额
							</td>
							<td align="center" class="td1">
								人数
							</td>
						</tr>
						<logic:notEmpty name="infoList">
						<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="infoList" indexId="i">
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

										<logic:match name="e" property="collBank" value="总计"
											location="start">

											<bean:write name="e" property="collBank" />

										</logic:match>
										<logic:notMatch name="e" property="collBank" value="总计">
											<a href="#"
												onclick='window.open("<%=path%>/syscollection/querystatistics/paymentpickstatistics/pickmonthreport/pickMonthReportBankPopShowAC.do?bankId=<bean:write name="e" property="collBankId"/>","window","height=450,width=700,top="+(window.screen.availHeight-30-450)/2+",left="+(window.screen.availWidth-10-700)/2+",scrollbars=yes, status=yes")'>
												<bean:write name="e" property="collBank" /> </a>
										</logic:notMatch>
									</td>
									<td>
										<bean:write name="e" format="0.00"
											property="pickMoney_buyhouse" />
									</td>
									<td>
										<bean:write name="e" property="personCount_buyhouse" />
									</td>
									<td>
										<bean:write name="e" property="pickMoneyRate_buyhouse" />
									</td>
									<td>
										<bean:write name="e" format="0.00"
											property="pickMoney_callback" />
									</td>
									<td>
										<bean:write name="e" property="personCount_callback" />
									</td>
									<td>
										<bean:write name="e" property="pickMoneyRate_callback" />
									</td>
									<td>
										<bean:write name="e" format="0.00" property="pickMoney_other" />
									</td>
									<td>
										<bean:write name="e" property="personCount_other" />
									</td>
									<td>
										<bean:write name="e" property="pickMoneyRate_other" />
									</td>
									<td>
										<bean:write name="e" format="0.00" property="pickMoney_retire" />
									</td>
									<td>
										<bean:write name="e" property="personCount_retire" />
									</td>
									<td>
										<bean:write name="e" property="pickMoneyRate_retire" />
									</td>
									<td>
										<bean:write name="e" format="0.00"
											property="pickMoney_jobless" />
									</td>
									<td>
										<bean:write name="e" property="personCount_jobless" />
									</td>
									<td>
										<bean:write name="e" property="pickMoneyRate_jobless" />
									</td>
									<td>
										<bean:write name="e" format="0.00" property="pickMoney_death" />
									</td>
									<td>
										<bean:write name="e" property="personCount_death" />
									</td>
									<td>
										<bean:write name="e" property="pickMoneyRate_death" />
									</td>
									<td>
										<bean:write name="e" format="0.00" property="pickMoney_deduct" />
									</td>
									<td>
										<bean:write name="e" property="personCount_deduct" />
									</td>
									<td>
										<bean:write name="e" property="pickMoneyRate_deduct" />
									</td>
									<td>
										<bean:write name="e" format="0.00" property="pickMoney_total" />
									</td>
									<td>
										<bean:write name="e" property="personCount_total" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="infoList">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
									<br>
								</td>
							</tr>
						</logic:empty>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<logic:empty name="infoList">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</logic:empty>
											<logic:notEmpty name="infoList">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</logic:notEmpty>
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
