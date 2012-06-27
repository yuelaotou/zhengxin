<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.sysloan.loanapply.loancheck.action.*"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			LoanCheckShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>查询条件，审核贷款信息列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">

</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script language="javascript">
var s1="";
var s2="";
var s3="";
var tr="tr0";

function loads(){
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="拨款审核通过"){
				s1=i;
			}
			if(document.all.item(i).value=="审核不通过"){
				s2=i;
			}
			if(document.all.item(i).value=="撤消拨款审核"){
				s3=i;
			}
		}
	}
	
	if(count==0){
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
	}
}

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function check(buttonName) {
    var temp_empid = [];
    var arr= document.getElementsByName("rowArray");
	var temp=0;
		var j=0;
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			temp=temp+1;
			temp_empid[j] = arr[i].value;
 			j++;
		}
	}
	var buttonMethod=buttonName.value; 
	if(temp==0){
		alert("请选择要"+buttonMethod+"的业务！");
		return false;
	}else{
		if(buttonMethod=='审核不通过'){
		    buttonMethod='checkNotPass';
		    window.open ('<%=path%>/sysloan/loanapply/loancheck/loancheck_reason.jsp?contractId='+ temp_empid +'&buttonMethod='+buttonMethod,'newwindow','height=100,width=400,top='+(window.screen.availHeight-100)/2+',left='+(window.screen.availWidth-400)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
			return false;
		}else{
			var x=confirm("确定进行"+buttonMethod+"？");
			if(x){
				setPosiTion(buttonName);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
				return true;
			}else{
				return false;
			}
		}
	}
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads(); reportErrors();"
	onContextmenu="return false">
	<jsp:include flush="true" page="/waiting.inc" />
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="loancheckShowAC.do" />
	</jsp:include>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="3%" align="right" valign="middle">
				<table width="21" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
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
						<td width="695" background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37" align="center" valign="top"
										style="PADDING-top: 7px"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
								</tr>
							</table>

						</td>
						<td width="255">
							<table width="255" border="0" cellspacing="0" cellpadding="0"
								align="right">
								<tr>
									<td width="56">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24" align="right">
									</td>
									<td width="169" class=font14 bgcolor="#FFFFFF" align="center"
										valign="middle">
										<font color="00B5DB">申请贷款&gt;拨款审核</font>
									</td>
									<td width="30" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="18">
							<img src="<%=path%>/img/table_bg_line.gif" width="18" height="37">
						</td>
						<td width="9">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top" class=td3>
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="207">
													<b class="font14">查 询 条 件</b>
												</td>
												<td width="716" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<html:form action="/loancheckFindAC.do" method="post"
								style="margin: 0">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=0 align="center">
									<tr>
										<td width="11%" class="td1">
											合同编号
										</td>
										<td width="21%" colspan="3">
											<html:text name="loancheckShowAF" property="contractId"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
											办事处
										</td>
										<td width="21%">
											<html:select name="loancheckShowAF" property="officeCode"
												styleClass="input3" onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:options collection="officeList1" property="value"
													labelProperty="label" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											借款人姓名
										</td>
										<td width="21%" colspan="3">
											<html:text name="loancheckShowAF" property="borrowerName"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
											证件号码
										</td>
										<td width="21%">
											<html:text name="loancheckShowAF" property="cardNum"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											单位名称
										</td>
										<td width="21%" colspan="3">
											<html:text name="loancheckShowAF" property="orgName"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
											放款银行
										</td>
										<td width="21%">
											<logic:notEmpty name="loanBankNameList">
												<html:select name="loancheckShowAF" property="loanBankName"
													styleClass="input3" onkeydown="enterNextFocus1();">
													<html:option value=""></html:option>
													<html:options collection="loanBankNameList"
														property="value" labelProperty="label" />
												</html:select>
											</logic:notEmpty>
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											操作时间
										</td>
										<td width="10%">
											<html:text name="loancheckShowAF" property="beginBizDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="1%">
											至
										</td>
										<td width="10%">
											<html:text name="loancheckShowAF" property="endBizDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
											购房类型
										</td>
										<td width="21%">
											<html:select name="loancheckShowAF" property="houseType"
												styleClass="input3" onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:optionsCollection property="houseTypeMap"
													name="loancheckShowAF" label="value" value="key" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											回件日期
										</td>
										<td width="10%">
											<html:text name="loancheckShowAF" property="beginBackDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="1%">
											至
										</td>
										<td width="10%">
											<html:text name="loancheckShowAF" property="endBackDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
											合同状态
										</td>
										<td width="21%">
											<html:select name="loancheckShowAF" property="contractStFind"
												styleClass="input3" onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:optionsCollection property="contractStMap"
													name="loancheckShowAF" label="value" value="key" />
											</html:select>
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
											合计：贷款户数
											<u>：<bean:write name="pagination" property="nrOfElements" />
											</u> 房价
											<u>：<bean:write name="loancheckShowAF"
													property="totlePriceAll" /> </u>借款金额（万元）
											<u>：<bean:write name="loancheckShowAF"
													property="loanTotleMoney" /> </u> 面积
											<u>：<bean:write name="loancheckShowAF"
													property="houseAreaAll" /> </u>
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
												<td height="22" bgcolor="#CCCCCC" align="center" width="213">
													<b class="font14">审核贷款列表</b>
												</td>
												<td width="710" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

							<html:form action="/loancheckAgainMaintainAC.do" method="post"
								style="margin: 0">
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
											<a href="javascript:sort('p110.contract_id')">合同编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="p110.contract_id">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('p110.borrower_name')">借款人姓名</a>
											<logic:equal name="pagination" property="orderBy"
												value="p110.borrower_name">
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
											单位名称
										</td>
										<td align="center" class=td2>
											配偶姓名
										</td>
										<td align="center" class=td2>
											房价
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('p115.loan_money')">借款金额（万元）</a>
											<logic:equal name="pagination" property="orderBy"
												value="p115.loan_money">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('p115.loan_time_limit')">借款期限（月）</a>
											<logic:equal name="pagination" property="orderBy"
												value="p115.loan_time_limit">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											放款银行
										</td>

										<td align="center" class=td2>
											建筑面积（M
											<sup>
												2
											</sup>
											）
										</td>
										<td align="center" class=td2>
											房屋坐落
										</td>

										<td align="center" class=td2>
											购房类型
										</td>
										<td align="center" class=td2>
											操作员
										</td>
										<td align="center" class=td2>
											合同状态
										</td>
										<td align="center" class=td2>
											备注
										</td>


									</tr>
									<logic:notEmpty name="loancheckShowAF" property="list">

										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="loancheckShowAF" property="list"
											id="element" indexId="i">

											<%
														j++;
														if (j % 2 == 0) {
															strClass = "td8";
														} else {
															strClass = "td9";
														}
											%>

											<tr align="left" id="tr<%=i%>" class="<%=strClass%>">
												<td>
													<html:multibox property="rowArray">
														<bean:write name="element" property="contractId" />
													</html:multibox>
												</td>

												<td>
													<bean:write name="element" property="contractId" />
												</td>
												<td>
													<a href="#"
														onclick="window.open('<%=path%>/sysloan/showLoanCheckTaAC.do?contractIdWY=<bean:write 
													name="element" property="contractId" />','window','height=650,width=1000,top='+(window.screen.availHeight-700)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,location=no,status=no');">

														<bean:write name="element" property="borrowerName" /> </a>
												</td>
												<td>
													<bean:write name="element" property="cardNum" />
												</td>
												<td>
													<bean:write name="element" property="orgName" />
												</td>
												<td>
													<bean:write name="element" property="assistantborrowerName" />
												</td>
												<td>
													<bean:write name="element" property="totlePrice" />
												</td>
												<td>
													<bean:write name="element" property="loanMoney" />
												</td>
												<td>
													<bean:write name="element" property="loanTimeLimit" />
												</td>
												<td>
													<bean:write name="element" property="loanBankName" />
												</td>

												<td>
													<bean:write name="element" property="houseArea" />
												</td>
												<td>
													<bean:write name="element" property="houseAddr" />
												</td>
												<td>
													<bean:write name="element" property="houseType" />
												</td>
												<td>
													<bean:write name="element" property="operator" />
												</td>
												<td>
													<bean:write name="element" property="contractSt" />
												</td>
												<td>
													<bean:write name="element" property="remark" />
												</td>

											</tr>

										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="loancheckShowAF" property="list">
										<tr>
											<td colspan="15" height="30" style="color:red">
												没有找到与条件相符合的结果！
											</td>
										</tr>

									</logic:empty>
								</table>

								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr class="td1">
										<td align="center">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr align="center">
													<td align="left">
														共
														<bean:write name="pagination" property="nrOfElements" />
														项
													</td>
													<td align="right">
														<jsp:include page="/inc/pagination.jsp">
															<jsp:param name="url" value="loancheckShowAC.do" />
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
													<td width="70">
														<html:submit property="method" styleClass="buttonb"
															onclick="return check(this);">
															<bean:message key="button.auditingagain.pass" />
														</html:submit>
													</td>
													<td width="70">
														<html:submit property="method" styleClass="buttonb"
															onclick="return check(this);">
															<bean:message key="button.auditing" />
														</html:submit>
													</td>
													<td width="70">
														<html:submit property="method" styleClass="buttonb"
															onclick="return check(this);">
															<bean:message key="button.auditingagain.quash" />
														</html:submit>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</html:form>

							<form action="loancheckShowAC.do" method="POST" name="Form1"
								id="Form1">
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html:html>

