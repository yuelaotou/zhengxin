<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.sysloan.loanapply.beforeloanapply.action.BeforeLoanApplyShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			BeforeLoanApplyShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String uMaxLoanYear = request.getParameter("uMaxLoanYear");//贷款年限
	if(uMaxLoanYear==null){
		uMaxLoanYear=(String)request.getAttribute("uMaxLoanYear");
	}
	String uMaxLoanMoney = request.getParameter("uMaxLoanMoney");//您的公积金可用额度
	if(uMaxLoanMoney==null){
		uMaxLoanMoney=(String)request.getAttribute("uMaxLoanMoney");
	}
	String monthBackMoney = request.getParameter("monthBackMoney");//月还本息
	if(monthBackMoney==null){
		monthBackMoney=(String)request.getAttribute("monthBackMoney");
	}
	String loanMonthRate = request.getParameter("loanMonthRate");//月利率
	if(loanMonthRate==null){
		loanMonthRate=(String)request.getAttribute("loanMonthRate");
	}
	
	String beforeYear = (String) request.getAttribute("beforeYear");
	String beforeMoney = (String) request.getAttribute("beforeMoney");
	String sunall = (String) request.getAttribute("sunall");
	if(beforeYear==null){
		beforeYear="";
	}
	if(beforeMoney==null){
		beforeMoney="";
	}
%>
<html>
	<head>
		<title>贷款申请</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>
	<script language="javascript">
function load(){
	
	document.all.monthBackMoney.value="<%=monthBackMoney%>";
	document.all.uMaxLoanYear.value="<%=uMaxLoanYear%>";
	document.all.uMaxLoanMoney.value="<%=uMaxLoanMoney%>";
	document.all.loanMonthRate.value="<%=loanMonthRate%>";
	document.all.beforeYear.value="<%=beforeYear%>";
	document.all.beforeMoney.value="<%=beforeMoney%>";
	<%
		if(sunall!=null && sunall.equals("0")){
	%>
			for(i=0;i<document.all.sunall.length;i++){
				if(document.all.sunall[i].value=='0'){
					document.all.sunall[i].checked="checked";
					document.all.beforeMoney.value="";
				}
			}
	<%
		}else if(sunall!=null && sunall.equals("1")){
	%>
			for(i=0;i<document.all.sunall.length;i++){
				if(document.all.sunall[i].value=='1'){
					document.all.sunall[i].checked="checked";
				}
			}
	<%
		}
	%>
}
function sun_dis(){
	for(i=0;i<document.all.sunall.length;i++){
		if(document.all.sunall[i].checked){
			var sunall=document.all.sunall[i].value;
			if(sunall=='0'){
				document.all.beforeMoney.value="";
				document.all.beforeMoney.readOnly="readonly";
			}else{
				document.all.beforeMoney.readOnly="";
			}
		}
	}
}
function cacluate(){
	var sunall;
	var beforeMoney;
	for(i=0;i<document.all.sunall.length;i++){
		if(document.all.sunall[i].checked){
			sunall=document.all.sunall[i].value;
			if(sunall=='0'){
				beforeMoney=document.all.uMaxLoanMoney.value;
			}else{
				beforeMoney=document.all.beforeMoney.value;
			}
		}
	}
	var uMaxLoanMoney = document.all.uMaxLoanMoney.value;
	var uMaxLoanYear = document.all.uMaxLoanYear.value;
	var loanMonthRate = document.all.loanMonthRate.value;
	var monthBackMoney = document.all.monthBackMoney.value;
	var beforeYear = document.all.beforeYear.value;
	var sun_yg="a";
	if(beforeYear==null || beforeYear==''){
		sun_yg="b";
	}
	document.URL="<%=path%>/sysloan/beforeLoanApplyShowAC.do?loanMonthRate="+loanMonthRate+"&uMaxLoanMoney="+uMaxLoanMoney
	+"&monthBackMoney="+monthBackMoney+"&uMaxLoanYear="+uMaxLoanYear+"&beforeYear="+beforeYear+"&beforeMoney="+beforeMoney+"&sunall="+sunall+"&sun_yg="+sun_yg;
}
function print(){
	document.URL="<%=path%>/sysloan/loanapply/beforeloanapply/beforeloanapplyprint.jsp";
}
</script>

	<body bgcolor="#FFFFFF" text="#000000" onload="load();">
		<form method="post">
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
									&nbsp;
								</td>
								<td background="<%=path%>/img/table_bg_line.gif" align="right">
									<table width="300" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="37">
												<img src="<%=path%>/img/title_banner.gif" width="37"
													height="24">
											</td>
											<td width="176" class=font14 bgcolor="#FFFFFF" align="center"
												valign="bottom">
												<font color="00B5DB">贷款申请</font><font color="00B5DB">&gt;打印还款计划表</font>
											</td>
											<td width="87" class=font14>
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
											<td height="22" bgcolor="#CCCCCC" align="center" width="158">
												<b class="font14">还款计划表信息</b>
											</td>
											<td width="752" height="22" align="center"
												background="<%=path%>/img/bg2.gif">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">


						</table>
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=0 align="center">
							<tr>
								<td width="17%" class="td1">
									住房公积金贷款额度
								</td>
								<td colspan="2">
									<input name="uMaxLoanMoney" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="17%" class="td1">
									年限
								</td>
								<td colspan="2">
									<input name="uMaxLoanYear" type="text" class="input3"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									利率
								</td>
								<td colspan="2">
									<input name="loanMonthRate" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="17%" class="td1">
									月还本息
								</td>
								<td colspan="2">
									<input name="monthBackMoney" type="text" class="input3"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									提前还款
								</td>
								<td width="25%">
									<input name="beforeYear" type="text" class="input3">
								</td>
								<td width="8%">
									年后
								</td>
								<td width="17%" class="td1">
									提前还款金额
								</td>
								<td width="8%">
									<input type="radio" name="sunall" value="0"
										onclick="sun_dis();" checked="checked">
									全部
									<input type="radio" name="sunall" value="1"
										onclick="sun_dis();">
								</td>
								<td width="20%">
									<input name="beforeMoney" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="5%">
									万
								</td>
							</tr>
						</table>
					  

						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center">
												<input type="button" name="Submit2" value="计算"
													class="buttona" onclick="cacluate();">
											</td>
									</table>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
				          <tr> 
				            <td class=h4> 应还本金合计<u>:
								<bean:write name="sumShouldCorpus"/>
				            </u>应还利息合计<u>:
								<bean:write name="sumShouldInterest"/>
				            </u> 月还本息合计<u>:
								<bean:write name="sumMonthBackMoney"/>
				            </u></td>
				          </tr>
				        </table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="161">
												<b class="font14">还款计划表信息列表 </b>
											</td>
											<td width="749" height="22" align="center"
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
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            
								<td align="center" class=td2>
									还款年月
								</td>
								<td align="center" class=td2>
									应还本金
								</td>
								<td align="center" class=td2>
									应还利息
								</td>
								<td align="center" class=td2>
									月还本息
								</td>
							</tr>
							<logic:notEmpty name="list">
							<% int j=0;
			String strClass="";
		%>
								<logic:iterate name="list" id="element">
								<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
									<tr class="<%=strClass%>">
										<td>
											<bean:write name="element" property="payMonth" />
										</td>
										<td>
											<bean:write name="element" property="shouldCorpus" />
										</td>
										<td>
											<bean:write name="element" property="shouldInterest" />
										</td>
										<td>
											<bean:write name="element" property="monthBackMoney" />
										</td>
									</tr>
						         
								</logic:iterate>
							</logic:notEmpty>
							<tr class="td1">
							<td colspan="4">
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="/inc/pagination.jsp">
												<jsp:param name="url" value="beforeLoanApplyShowAC.do"/>
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
								<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center">
												<input type="button" name="Submit" value="打印"
													class="buttona" onclick="print();">
											</td>
											<td align="center">
												<input type="button" name="Submit" value="返回"
													class="buttona" onclick="javascript:history.back();">
											</td>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
