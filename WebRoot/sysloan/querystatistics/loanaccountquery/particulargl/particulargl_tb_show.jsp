<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.action.ParticularglTbShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(ParticularglTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
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

function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="打印"){
				s1=i;
			}
			
		}
	} 
	var count=document.all.count.value
	if(count==0){
		document.all.item(s1).disabled="true";
	}
}

function check(){
var borrowercontractid=document.all.borrowercontractid.value;
var bizdateB=document.all.bizdateB.value;
var bizdateE=document.all.bizdateE.value;
if(borrowercontractid==""){
alert("请输入合同编号");
return false;
}
if(bizdateB==""){
alert("请输入查询日期");
return false;
}
if(bizdateE==""){
alert("请输入查询日期");
return false;
}
}
</script>

	<body bgcolor="#FFFFFF" text="#000000" onload="loads();">
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
										<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">贷款账查询&gt;明细账</font>
										</td>
										<td width="29" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="176">
											<b class="font14">借款人合同信息</b>
										</td>
										<td width="683" height="22" align="center"
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
						<tr>
						<html:hidden property="count" name="particularglTaAF" />
							<td width="17%" class="td1">
								借款合同编号
								<br>
							</td>
							<td>
								<html:text property="contractid" name="particularglTaAF" styleClass="input3" />
							</td>
							<td class="td1" width="17%">
								扣款账号
								<br>
							</td>
							<td width="33%">
								<html:text property="loankouacc" name="particularglTaAF" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td>
								<html:text property="borrowername" name="particularglTaAF" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="33%">
							<html:text property="cardnum" name="particularglTaAF" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								放款银行
							</td>
							<td>
								<html:text property="loanbank" name="particularglTaAF" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								贷款金额
							</td>
							<td width="33%">
							<html:text property="loanmoney" name="particularglTaAF" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								贷款期限（月）
							</td>
							<td>
								<html:text property="loanlimit" name="particularglTaAF" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								还款方式
							</td>
							<td colspan="2">
								<html:text property="loanmode" name="particularglTaAF" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								贷款余额
							</td>
							<td>
								<html:text property="overplusloanmoney" name="particularglTaAF" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								呆账未回收金额
							</td>
							<td colspan="2">
							<html:text property="nobackmoney" name="particularglTaAF" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								挂账余额
							</td>
							<td>
								<html:text property="oveaerloanrepay" name="particularglTaAF" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								保证金余额
							</td>
							<td colspan="2">
								<html:text property="ballbalance" name="particularglTaAF" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								总还本金
							</td>
							<td>
								<html:text property="srealcorpus" name="particularglTaAF" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								总还利息
							</td>
							<td colspan="2">
								<html:text property="srealinterest" name="particularglTaAF" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								总还罚息利息
							</td>
							<td>
								<html:text property="srealpunishinterest" name="particularglTaAF" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								欠还本金
							</td>
							<td colspan="2">
								<html:text property="owercorpus" name="particularglTaAF" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								欠还利息
							</td>
							<td>
								<html:text property="oweinterest" name="particularglTaAF" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								欠还罚息利息
							</td>
							<td colspan="2">
								<html:text property="owepunishinterest" name="particularglTaAF" styleClass="input3" />
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="1" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								总计：本期借方      
								<u>：<bean:write name="particularglTaAF" property="tolborrower" />
								</u> 本期贷方
								<u>：<bean:write name="particularglTaAF" property="tolpaymoney" />
								</u> 本期利息
								<u>：<bean:write name="particularglTaAF" property="tolinterest" />
								</u> 本期罚息
								<u>：<bean:write name="particularglTaAF" property="tolpunishinterest" />
								</u> 挂账金额
								<u>：<bean:write name="particularglTaAF" property="tolloanrepay" />
								</u> 保证金
								<u>：<bean:write name="particularglTaAF" property="tolballbalance" />
								</u> 呆账核销金额
								<u>：<bean:write name="particularglTaAF" property="tolbaddebtmoney" />
								</u> 
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="179">
											<b class="font14">明细账列表</b>
										</td>
										<td width="680" height="22" align="center"
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
								发生月份
							</td>
							<td align="center" class=td2>
								期初本金余额
							</td>
							<td align="center" class=td2>
								本期借方
							</td>
							<td align="center" class=td2>
								本期贷方
							</td>
							<td align="center" class=td2>
								本期利息
							</td>
							<td align="center" class=td2>
								本期罚息
							</td>
							<td align="center" class=td2>
								挂账金额
							</td>
							<td align="center" class=td2>
								保证金
							</td>
							<td align="center" class=td2>
								呆账核销金额
							</td>
							<td align="center" class=td2>
								期末本金余额
							</td>
						</tr>
					<logic:notEmpty name="particularglTaAF" property="list">
					<% int j=0;
			String strClass="";
		%>
								<logic:iterate name="particularglTaAF" property="list"
									id="elments" indexId="i">
							<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
									<tr id="tr<%=i%>"   class="<%=strClass%>" onMouseOver='this.style.background="#eaeaea"'  onMouseOut='this.style.background="#ffffff"' > 
										<td valign="top">
										<a href="<%=path%>/sysloan/particularglTcShowAC.do?ocyear=<bean:write name="elments" property="ocyear" />">
											<bean:write name="elments" property="ocyear" />
											</a>
										</td>
										<td valign="top">
											<bean:write name="elments" property="firstcorpus" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thisborrower" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thispaymoney" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thisinterest" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thispunishinterest" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thisloanrepay" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thisballbalance" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thisbaddebtmoney" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="lastcorpus" />
										</td>
										
									</tr>
									
								</logic:iterate>

							</logic:notEmpty>
							<logic:empty name="particularglTaAF" property="list">
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
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr align="center">
											<td align="center">
												<table width="95%" border="0" cellspacing="0"
													cellpadding="3" align="center">
													<tr class="td1">

														<td align="left">
															共
															<bean:write name="pagination" property="nrOfElements" />
															条记录
														</td>
														<td align="right">
															<jsp:include page="../../../../inc/pagination.jsp">
																<jsp:param name="url" value="particularglTbShowAC.do"/>
															</jsp:include>

														</td>
													</tr>
												</table>

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
												<html:submit property="method" styleClass="buttona" onclick="location.href='particularglTbPrintAC.do'">
													<bean:message key="button.print" />
												</html:submit>
												
											</td>
											<td width="70" align="center" valign="middle">
												 <input type="button" name="Submit4" value="返回" class="buttona" onclick="location.href='particularglTaShowAC.do'">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
		</table>
	</body>
</html>

