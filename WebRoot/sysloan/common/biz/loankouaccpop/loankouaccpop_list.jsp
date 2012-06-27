<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.common.biz.loankouaccpop.action.LoanKouAccpopShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			LoanKouAccpopShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String indexs = (String) session.getAttribute("indexs");
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>贷款管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>
<script language="javascript">
function loads(){
 document.loanKouAccpopAF.elements["loankouacc"].value="";
 document.loanKouAccpopAF.elements["contractId"].value="";
 document.loanKouAccpopAF.elements["borrowerName"].value="";
 document.loanKouAccpopAF.elements["cardNum"].value="";
 document.loanKouAccpopAF.elements["empId"].value="";
 
 var counts="<bean:write name="pagination" property="nrOfElements"/>";
     if(counts=="0"){
     	document.form1.elements["sure"].disabled="true";
     }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">

	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=path%>/img/table_left.gif" width="7" height="37">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" width="555">
							&nbsp;
						</td>
						<td width="635" background="<%=path%>/img/table_bg_line.gif"></td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
						</td>
						<td width="1">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
				<html:form action="/loanKouAccpopFindAC.do" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="13%" class="td1">
								扣款账号
							</td>
							<td width="18%">
								<html:text name="loanKouAccpopAF" property="loankouacc"
									styleClass="input3" styleId="txtsearch"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="11%" class="td1">
								合同编号
							</td>
							<td width="21%">
								<html:text name="loanKouAccpopAF" property="contractId"
									styleClass="input3" styleId="txtsearch"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								借款人姓名
							</td>
							<td width="18%">
								<html:text name="loanKouAccpopAF" property="borrowerName"
									styleClass="input3" styleId="txtsearch"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="11%" class="td1">
								证件号码
							</td>
							<td width="21%">
								<html:text name="loanKouAccpopAF" property="cardNum"
									styleClass="input3" styleId="txtsearch"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								职工编号
							</td>
							<td width="18%">
								<html:text name="loanKouAccpopAF" property="empId"
									styleClass="input3" styleId="txtsearch"
									onkeydown="enterNextFocus1();"></html:text>
							</td>

						</tr>
						<tr align="right">
							<td colspan="4">
								<html:submit property="method" styleClass="buttona">
									<bean:message key="button.search" />
								</html:submit>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="187">
										<b class="font14">扣款账号信息列表</b>
									</td>
									<td width="443" height="22" align="center"
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
				<form name="idAF" action="" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('p111.loan_kou_acc')">扣款账号</a>
								<logic:equal name="pagination" property="orderBy"
									value="p111.loan_kou_acc">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
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
								<a href="javascript:sort('p110.card_num')">证件号码</a>
								<logic:equal name="pagination" property="orderBy"
									value="p110.card_num">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								单位编号
							</td>
							<td align="center" class=td2>
								单位名称
							</td>
							<td align="center" class=td2>
								职工编号
							</td>
							<td align="center" class=td2>
								账户状态
							</td>
						</tr>
						<logic:notEmpty name="loankouaccpopList">
							<%
										int j = 0;
										String strClass = "";
							%>
							<logic:iterate id="loankouaccpopList" name="loankouaccpopList"
								indexId="i">
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
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass%>"
									onDblClick='loanqdValues("<%=indexs%>");'>

									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="loankouaccpopList" property="loankouacc"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="loankouaccpopList" property="loankouacc" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="loankouaccpopList" property="contractId" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="loankouaccpopList" property="borrowerName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="loankouaccpopList" property="cardNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="loankouaccpopList" property="orgId" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="loankouaccpopList" property="orgName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="loankouaccpopList" property="empId" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="loankouaccpopList" property="contractSt" />
										</p>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="loankouaccpopList">
							<tr>
								<td colspan="12" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>

						</logic:empty>
					</table>
				</form>
				<form name="form1" style="margin: 0">
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
										</td>
										<td align="right">
											<jsp:include page="../../../../inc/pagination.jsp">
												<jsp:param name="url" value="loanKouAccpopShowAC.do" />
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
											<input type="button" name="sure" value="确 定" class="buttona"
												onclick='loanqdValues("<%=indexs%>");'>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html:html>
