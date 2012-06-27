<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanapply.action.LoanapplyTeShowAC"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			LoanapplyTeShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<%
String path = request.getContextPath();
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">

var s1="";
var s2="";
<%--var s3="";--%>
var s4="";
var s5="";
var s6="";
var s7="";
var s8="";
var s9="";
var s10="";
var tr='tr0'
function loads(){

	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="修改"){
				s1=i;
			}
			if(document.all.item(i).value=="删除"){
				s2=i;
			}
<%--			if(document.all.item(i).value=="列表打印"){--%>
<%--				s3=i;--%>
<%--			}--%>
			if(document.all.item(i).value=="打印贷款申请表"){
				s4=i;
			}
			if(document.all.item(i).value=="打印贷款承诺书"){
				s5=i;
			}
			if(document.all.item(i).value=="打印二手房联系单"){
				s6=i;
			}
			if(document.all.item(i).value=="完成信息录入"){
				s7=i;
			}
			if(document.all.item(i).value=="打印审批表"){
				s8=i;
			}
			if(document.all.item(i).value=="扫描其他"){
				s9=i;
			}
			if(document.all.item(i).value=="撤销申请"){
				s10=i;
			}
		}
	}
	document.all.item(s1).disabled="true";
   	document.all.item(s2).disabled="true";
<%--    document.all.item(s3).disabled="true";--%>
    document.all.item(s4).disabled="true";
    document.all.item(s5).disabled="true";
    document.all.item(s6).disabled="true";
    document.all.item(s7).disabled="true";
    document.all.item(s8).disabled="true";
    document.all.item(s10).disabled="true";
	var count = document.loanapplytenewAF.elements["count"].value.trim();
	if(count!='0'){
<%--		document.all.item(s3).disabled="";--%>
		document.all.item(s4).disabled="";
 		var statuts=document.getElementById(tr).childNodes[8].childNodes[0].innerHTML;
		var a=statuts.indexOf("|");
		var b=statuts.lastIndexOf("|");
		statuts=statuts.substring(a+1,b);
		if(statuts=="申请"||statuts=="审核未通过"||statuts=="审批未通过"||statuts=="审核不通过"||statuts=="提交审核"){
		 	document.all.item(s1).disabled="";
		 	if(statuts!="提交审核"){
			   	document.all.item(s2).disabled="";
			    document.all.item(s7).disabled="";
			}
	 	}
	 	if(statuts=="审批通过"||statuts=="拨款审核通过"||statuts=="拨款审批通过"||statuts=="终审通过"||statuts=="回件确认"||statuts=="打印借据"){
		 	document.all.item(s8).disabled="";
	 	}
	 	if(statuts=="提交审核"){
	 		var houseType=document.getElementById(tr).childNodes[5].childNodes[0].innerHTML.trim();
	 		if(houseType=="商品房"){
	 			document.all.item(s5).disabled="";
	 		}else if(houseType=="二手房"){
	 			document.all.item(s6).disabled="";
	 		}
	 		document.all.item(s10).disabled="";
	 	}
 	}
}

function reportsErrors(){
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>	
}

function gettr(indexs){
	tr=indexs;
	loads();
}

</script>

	<body bgcolor="#FFFFFF" text="#000000"
		onload="reportsErrors();loads();">

		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
											<a href="<%=path%>/sysloan/showLoanapplyAC.do" class=a2>借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loanapplytbshowAC.do" class=a2>共同借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loanapplytcshowAC.do" class=a2>购房信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loanapplytdshowAC.do" class=a2>借款人额度信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loanapplyteshowAC.do" class=a2>申请贷款维护</a>
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
											<font color="00B5DB">申请贷款&gt;申请贷款</font>
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
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="157">
											<b class="font14">查询条件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="767">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/loanapplytefindAC.do" style="margin: 0">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=0 align="center">
							<tr>
								<html:hidden name="loanapplytenewAF" property="count" />
								<td width="17%" class="td1">
									合同编号
								</td>
								<td width="33%">
									<html:text property="contractId" name="loanapplytenewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="17%" class="td1">
									借款人姓名
								</td>
								<td width="33%">
									<html:text property="borrowerName" name="loanapplytenewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									借款人职工编号
								</td>
								<td width="33%">
									<html:text property="empId" name="loanapplytenewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="17%" class="td1">
									证件号码
								</td>
								<td width="33%">
									<html:text property="cardNum" name="loanapplytenewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									购房类型
								</td>
								<td width="33%" align="center">
									<html:select name="loanapplytenewAF" property="buyHouseType"
										styleClass="input4" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="buyHouseTypeMap"
											name="loanapplytenewAF" label="value" value="key" />
									</html:select>
								</td>
								<td width="17%" class="td1">
									合同状态
								</td>
								<td align="center">
									<html:select name="loanapplytenewAF" property="contranct_st"
										styleClass="input4" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="contranct_stMap"
											name="loanapplytenewAF" label="value" value="key" />
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

					</html:form>
					<html:form action="/loanapplytemaintianAC.do" style="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="152">
												<b class="font14">申请贷款列表</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="772">
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
									<a href="javascript:sort('contract_id')">合同编号</a>
									<logic:equal name="pagination" property="orderBy"
										value="contract_id">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td width="13%" align="center" class=td2>
									借款人职工编号
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('borrower_name')">借款人姓名</a>
									<logic:equal name="pagination" property="orderBy"
										value="borrower_name">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td width="10%" align="center" class=td2>
									证件号码
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('house_type')">购房类型</a>
									<logic:equal name="pagination" property="orderBy"
										value="house_type">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td width="10%" align="center" class=td2>
									贷款金额
								</td>
								<td width="10%" align="center" class=td2>
									贷款期限（月）
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('contract_st')">合同状态</a>
									<logic:equal name="pagination" property="orderBy"
										value="contract_st">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td width="10%" align="center" class=td2>
									是否已打印
								</td>
							</tr>
							<logic:notEmpty name="loanapplytenewAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate name="loanapplytenewAF" property="list"
									id="elments" indexId="i">
									<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
									%>
									<tr id="tr<%=i%>"
										onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
										onMouseOver='this.style.background="#eaeaea"'
										onMouseOut='gotoColorpp("<%=i%>", idAF);'
										class="<%=strClass%>" onDblClick="">

										<td valign="top">
											<p align="left">
												<input id="s<%=i%>" type="radio" name="id"
													value="<bean:write name="elments" property="id"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>
										<td valign="top">
											<bean:write name="elments" property="contractid" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="empid" />
										</td>
										<td valign="top">
											<a
												href="<%=path%>/sysloan/showLoanapplyAC.do?contractIdHL=<bean:write name="elments" property="contractid" />">
												<bean:write name="elments" property="borrowername" /> </a>

										</td>
										<td valign="top">
											<bean:write name="elments" property="cardnum" />
										</td>
										<td valign="top">
											<p>
												<bean:write name="elments" property="buyhousetype" />
											</p>
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanmoney" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanlimit" />
										</td>
										<td valign="top">
											<p>
												<logic:equal property="temp_contract_st" name="elments"
													value="1">
													<a href="#"
														onclick="window.open('<%=path%>/sysloan/findNotPassReasonAC.do?contractId=<bean:write name="elments" property="contractid"/>&type=<bean:write name="elments" property="temp_c_st"/>','window','height=230,width=450,top=300,left=400,scrollbars=no,location=no,status=no');">|<bean:write
															name="elments" property="contract_st" />|</a>
												</logic:equal>
												<logic:equal property="temp_contract_st" name="elments"
													value="2">|<bean:write name="elments"
														property="contract_st" />|</logic:equal>
											</p>
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="isPrintApply" />
										</td>
										<html:hidden name="elments" property="temp_contract_st" />
									</tr>

								</logic:iterate>

							</logic:notEmpty>
							<logic:empty name="loanapplytenewAF" property="list">
								<tr>
									<td colspan="7" height="30" style="color:red">
										没有找到与条件相符合的结果！
									</td>
								</tr>

							</logic:empty>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr class="td1">
								<td align="center">
									<table width="95%" border="0" cellspacing="0" cellpadding="3"
										align="center">
										<tr class="td1">

											<td align="left">
												共
												<bean:write name="pagination" property="nrOfElements" />
												条记录
											</td>
											<td align="right">
												<jsp:include page="../../../inc/pagination.jsp">
													<jsp:param name="url" value="loanapplyteshowAC.do" />
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
											<td>
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.update" />
												</html:submit>
											</td>
											<td>
												<html:submit property="method" styleClass="buttona"
													onclick="return confirm('是否删除？');">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td>
												<html:submit property="method" styleClass="buttonb">
													<bean:message key="button.vipcheck.print" />
												</html:submit>
											</td>
<%--											<td>--%>
<%--												<html:submit property="method" styleClass="buttona">--%>
<%--													<bean:message key="button.print.list" />--%>
<%--												</html:submit>--%>
<%--											</td>--%>
											<td>
												<html:submit property="method" styleClass="buttonc">
													<bean:message key="button.printloanapply" />
												</html:submit>
											</td>
											<td>
												<html:submit property="method" styleClass="buttonc">
													<bean:message key="button.printacceptance" />
												</html:submit>
											</td>
											<td>
												<html:submit property="method" styleClass="buttonc">
													<bean:message key="button.print.sechousecontactlist" />
												</html:submit>
											</td>
											<td>
												<html:submit property="method" styleClass="buttonc"
													onclick="return confirm('是否确定提交')">
													<bean:message key="button.true.pproval" />
												</html:submit>
											</td>
											<td>
												<html:submit property="method" styleClass="buttonb">
													<bean:message key="button.scan.other" />
												</html:submit>
											</td>
											<td>
												<html:submit property="method" styleClass="buttonb">
													<bean:message key="button.chexiaoshenqing" />
												</html:submit>
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

</html>

