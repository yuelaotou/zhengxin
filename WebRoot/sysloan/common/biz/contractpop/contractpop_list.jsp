<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.common.biz.contractpop.action.ContractpopShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ContractpopShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String indexs = (String) session.getAttribute("indexs");
%>
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
 	document.contractpopAF.elements["contractId"].value="";
 	document.contractpopAF.elements["borrowerName"].value="";
 	document.contractpopAF.elements["cardNum"].value="";
 	document.contractpopAF.elements["empId"].value="";
 
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
							<img src="<%=request.getContextPath()%>/img/table_left.gif"
								width="7" height="37">
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							width="555">
							&nbsp;
						</td>
						<td width="635"
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"></td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">

						</td>
						<td width="1">
							<img src="<%=request.getContextPath()%>/img/table_right.gif"
								width="9" height="37">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22"
										background="<%=request.getContextPath()%>/img/bg2.gif"
										align="center">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/contractpopFind.do" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="13%" class="td1">
								合同编号
							</td>
							<td width="18%">
								<html:text name="contractpopAF" property="contractId"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="11%" class="td1">
								借款人姓名
							</td>
							<td width="21%">
								<html:text name="contractpopAF" property="borrowerName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								证件号码
							</td>
							<td width="18%">
								<html:text name="contractpopAF" property="cardNum"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="11%" class="td1">
								职工编号
							</td>
							<td width="21%">
								<html:text name="contractpopAF" property="empId"
									styleClass="input3" styleId="txtsearch"></html:text>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">合 同 列 表 </b>
									</td>
									<td height="22"
										background="<%=request.getContextPath()%>/img/bg2.gif"
										align="center">
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
						<logic:notEmpty name="contractpopList">
							<%
										int j = 0;
										String strClass = "";
							%>
							<logic:iterate id="contractpopList" name="contractpopList"
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
									onMouseOut='gotoColorpp("<%=i%>", idAF);'
									class="<%=strClass%>" onDblClick='loanqdValues("<%=indexs%>");'>

									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="contractpopList" property="contractId"/>"
												<%if(new Integer(0).equals(i)) out.print

("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="contractpopList" property="contractId"
												format="0000000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="contractpopList" property="borrowerName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="contractpopList" property="cardNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="contractpopList" property="orgId" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="contractpopList" property="orgName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="contractpopList" property="empId" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="contractpopList" property="contractSt" />
										</p>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="contractpopList">
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
												<jsp:param name="url" value="contractpopShow.do" />
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
