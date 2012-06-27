<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%
	String path = request.getContextPath();
%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.querycongeallog.action.QueryCongeallogShowAC"%>
<%
   Object pagination= request.getSession(false).getAttribute(QueryCongeallogShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
%>
<html>
	<head>
		<title>统计查询</title>
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
}



function reportsErrors(){
<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>	
}

</script>

	<body bgcolor="#FFFFFF" text="#000000" onload="loads();">
			<table width="1300" border="0" cellspacing="0" cellpadding="0" align="center">
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
								<td background="<%=path%>/img/table_bg_line.gif"></td>
								<td background="<%=path%>/img/table_bg_line.gif" align="right">
									<table width="300" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="37">
												<img src="<%=path%>/img/title_banner.gif" width="37"
													height="24">
											</td>
											<td width="235" class=font14 bgcolor="#FFFFFF" align="center"
												valign="bottom">
												<font color="00B5DB">统计查询&gt;冻结表日志查询</font>
											</td>
											<td width="28" class=font14>
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
											<td height="22" bgcolor="#CCCCCC" align="center" width="181">
												<b class="font14">查 询 条 件</b>
											</td>
											<td width="678" height="22" align="center"
												background="<%=path%>/img/bg2.gif">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<html:form action="/queryCongeallogFindAC" style="margin: 0">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=0 align="center">
							<tr id="gjtr">
								<td width="17%" class="td1">
									办事处
								</td>
								<td width="33%" colspan="2">
									<html:select name="queryCongeallogAF" property="officeName"
										styleClass="input4" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="officelist" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td width="17%" class="td1">
									放款银行
								</td>
								<td>
									<html:select name="queryCongeallogAF" property="bankId"
										styleClass="input4" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="dkbanklist" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									合同编号
								</td>
								<td width="33%" colspan="2">
									<html:text property="contractId" name="queryCongeallogAF" styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									借款人姓名
								</td>
								<td width="33%">
									<html:text property="empName" name="queryCongeallogAF" styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									冻结状态
								</td>
								<td width="33%" colspan="2">
									<html:select name="queryCongeallogAF" property="status"
										styleClass="input4" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:option value="0">冻结</html:option>
										<html:option value="1">解冻</html:option>
									</html:select>
								</td>
								<td width="17%" class="td1">
									冻结类型
								</td>
								<td>
									<html:select name="queryCongeallogAF"
										property="type" styleClass="input4" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:option value="1">借款人</html:option>
										<html:option value="2">辅助借款人</html:option>
										<html:option value="3">保证人</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									证件号码
								</td>
								<td width="33%" colspan="2">
									<html:text property="cardNum" name="queryCongeallogAF" styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									 
								</td>
								 <td width="33%" colspan="3"  > <input name="textfield302232" readonly="readonly" type="text" id="txtsearch45225" class="input3" ></td>
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
						
						<html:form action="/queryCongeallogPrintAC" style="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="179">
												<b class="font14">冻结表日志查询信息列表</b>
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
									<a href="javascript:sort('p210.contract_id')">合同编号</a>
									<logic:equal name="pagination" property="orderBy"
										value="p210.contract_id">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>								
								<td align="center" class=td2>
									<a href="javascript:sort('p110.office')">办事处</a>
									<logic:equal name="pagination" property="orderBy"
										value="p110.office">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p111.loan_bank_id')">放款银行</a>
									<logic:equal name="pagination" property="orderBy"
										value="p111.loan_bank_id">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>								
								<td align="center" class=td2>
									<a href="javascript:sort('p210.org_id')">单位编号</a>
									<logic:equal name="pagination" property="orderBy"
										value="p210.org_id">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p110.org_name')">单位名称</a>
									<logic:equal name="pagination" property="orderBy"
										value="p110.org_name">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p210.emp_id')">职工编号</a>
									<logic:equal name="pagination" property="orderBy"
										value="p210.emp_id">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>								
								<td align="center" class=td2>
									<a href="javascript:sort('p210.emp_name')">借款人姓名</a>
									<logic:equal name="pagination" property="orderBy"
										value="p210.emp_name">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p210.card_num')">证件号码</a>
									<logic:equal name="pagination" property="orderBy"
										value="p210.card_num">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td width="13%" align="center" class=td2>
									账户余额
								</td>
								<td width="13%" align="center" class=td2>
									贷款金额
								</td>
								<td width="13%" align="center" class=td2>
									贷款期限
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p210.status')">冻结状态</a>
									<logic:equal name="pagination" property="orderBy"
										value="p210.status">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>								
								<td align="center" class=td2>
									<a href="javascript:sort('p210.type')">冻结类型</a>
									<logic:equal name="pagination" property="orderBy"
										value="p210.type">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>		
							</tr>
						<logic:notEmpty name="queryCongeallogAF" property="list">
						<% int j=0;
			String strClass="";
		%>
								<logic:iterate name="queryCongeallogAF" property="list"
									id="elments" indexId="i">
									<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
									<tr id="tr1" class="<%=strClass%>" onMouseOver='this.style.background="#eaeaea"'  onMouseOut='this.style.background="#ffffff"' > 
										<td valign="top" align="center">
											<bean:write name="elments" property="contactId" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="officeName" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="bankId" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="orgId" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="orgName" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="empId" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="empName" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="cardNum" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="money" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="loanMoney" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="loanTime" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="status" />
										</td>
										<td valign="top" align="center">
											<bean:write name="elments" property="type" />
										</td>
									</tr>
								
								</logic:iterate>

							</logic:notEmpty>
							<logic:empty name="queryCongeallogAF" property="list">
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
																<jsp:param name="url" value="queryCongeallogShowAC.do"/>
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
							<tr valign="bottom" align="center">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30" >
									<table width="184" border="0" cellpadding="0" cellspacing="0" align="center">
										<tr align="center">
											<td width="73" align="center" >
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
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
