<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.contractchg.baseinfochg.action.BaseinfochgTaShowAC"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			BaseinfochgTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<%
String path = request.getContextPath();
%>
<html>
	<head>
		<title>合同变更</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">


var s1="";

function loads(){

for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="修改"){
				s1=i;
			}
		}
	}

	 var count = document.loanapplytenewAF.elements["count"].value.trim();

	 if(count=='0'){
	  document.all.item(s1).disabled="true";
	 }
	 
}



function reportsErrors(){
<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>	
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
											background="<%=path%>/img/buttonbl.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/baseinfochgtashowAC.do" class=a2>基本信息变更维护</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											<a href="<%=path%>/sysloan/baseinfochgtbshowAC.do" class=a2>借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/baseinfochgtcshowAC.do" class=a2>共同借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/baseinfochgtdshowAC.do" class=a2>购房信息</a>
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
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/baseinfochgtafindAC.do" style="margin: 0">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=0 align="center">
							<tr>
								<html:hidden name="loanapplytenewAF" property="count" />
								<td width="17%" class="td1">
									合同编号
								</td>
								<td width="33%">
									<html:text property="contractId" name="loanapplytenewAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									借款人姓名
								</td>
								<td width="33%">
									<html:text property="borrowerName" name="loanapplytenewAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									借款人职工编号
								</td>
								<td width="33%">
									<html:text property="empId" name="loanapplytenewAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									证件号码
								</td>
								<td width="33%">
									<html:text property="cardNum" name="loanapplytenewAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
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
								<td width="17%" class="td1" >&nbsp;</td>
               				   <td align="center" class="td7" >&nbsp;</td>
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
					<html:form action="/baseinfochgtamaintainAC.do" style="margin: 0">
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
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
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
								<td width="13%" align="center" class=td2>
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
								<td align="center" class=td2>
									<a href="javascript:sort('loan_money')">贷款金额</a>
									<logic:equal name="pagination" property="orderBy"
										value="loan_money">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('loan_time_limit')">贷款期限（月）</a>
									<logic:equal name="pagination" property="orderBy"
										value="loan_time_limit">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
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
							</tr>
							<logic:notEmpty name="loanapplytenewAF" property="list">
							<% int j=0;
			String strClass="";
		%>
								<logic:iterate name="loanapplytenewAF" property="list"
									id="elments" indexId="i">
									<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
									<tr id="tr<%=i%>"
										onclick='gotoClickpp("<%=i %>", idAF);' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"
										onDblClick="">

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
										<bean:write name="elments" property="borrowername" /> 
										</td>
										<td valign="top">
											<bean:write name="elments" property="cardnum" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="buyhousetype" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanmoney" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanlimit" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="contract_st" />
										</td>
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
													<jsp:param name="url" value="baseinfochgtashowAC.do"/>
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
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.update" />
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

