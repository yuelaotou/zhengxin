<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonWindowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ShowChgpersonWindowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>人员变更-变更维护</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script type="text/javascript">
function loads(){
	var orgid=document.all.id.value;
	if(orgid!=""){
	document.all.id.value=format(document.all.id.value)+document.all.id.value;
	}
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="return loads();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="showChgpersonWindowAC.do" />
	</jsp:include>

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
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">变更业务</font><font color="00B5DB">&gt;人员变更</font>
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
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">单 位 信 息</b>
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
						<td width="17%" class="td1" algin="center">
							单位编号
						</td>
						<td width="33%">
							<html:text name="chgpersonDoListAF" property="id"
								styleClass="input3" styleId="txtsearch" readonly="true">
							</html:text>
						</td>
						<td class="td1" width="17%" algin="center">
							单位名称
						</td>
						<td width="33%">
							<html:text name="chgpersonDoListAF" property="name"
								styleClass="input3" styleId="txtsearch" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td width="17%" class="td1">
							变更年月
						</td>
						<td width="33%">
							<html:text name="chgpersonDoListAF" property="date"
								styleClass="input3" styleId="txtsearch" readonly="true"></html:text>
						</td>
						<td width="17%">
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>

						<logic:notEmpty name="chgpersonAF">
							<td align="left" height="33" class="h4">
								合计:
								<br>
								调整前：汇缴人数
								<u><bean:write name="chgpersonAF" property="beforeChgperson" />
								</u> 汇缴金额:
								<u><bean:write name="chgpersonAF" property="oldOldPayment" />
								</u>
								<br>
								调整后：汇缴人数
								<u><bean:write name="chgpersonAF" property="sumChgPerson" />
								</u> 汇缴金额:
								<u><bean:write name="chgpersonAF" property="newOldPayment" />
								</u> 增加人数:
								<u><bean:write name="chgpersonAF" property="insChgperson" />
								</u> 增加缴额:
								<u><bean:write name="chgpersonAF" property="insPayment" /> </u>
								减少人数:
								<u><bean:write name="chgpersonAF" property="mulChgperson" />
								</u> 减少缴额:
								<u><bean:write name="chgpersonAF" property="mulPayment" /> </u>

							</td>
						</logic:notEmpty>

						<logic:empty name="chgpersonAF">
							<td align="left" height="33" class="h4">
								合计:变更总人数:
								<u>0 </u> 增加:
								<u>0</u> 减少:
								<u>0</u> 增加缴额:
								<u>0.0</u> 减少缴额:
								<u>0.0</u> 单位缴额:
								<u>0.0</u> 职工缴额:
								<u>0.0</u> 缴额合计:
								<u>0.0</u> 变更前应汇缴总额:
								<u>0.0</u> 变更后应汇缴总额:
								<u>0.0</u>
							</td>
						</logic:empty>

					</tr>
				</table>

				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">人员变更列表</b>
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
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr bgcolor="1BA5FF">
						<td align="center" height="6" colspan="1"></td>
					</tr>
				</table>


				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr>
						<td align="center" height="23" bgcolor="C4F0FF"></td>
						<td align="center" class=td2>
							<a href="javascript:sort('chgPersonTail.chgType')">变更类型</a>
							<logic:equal name="pagination" property="orderBy"
								value="chgPersonTail.chgType">
								<logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
								<logic:equal name="pagination" property="orderother"
									value="DESC">▼</logic:equal>
							</logic:equal>
						</td>
						<td align="center" class=td2>
							<a href="javascript:sort('chgPersonTail.empId')">职工编号</a>
							<logic:equal name="pagination" property="orderBy"
								value="chgPersonTail.empId">
								<logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
								<logic:equal name="pagination" property="orderother"
									value="DESC">▼</logic:equal>
							</logic:equal>
						</td>
						<td align="center" class=td2>
							<a href="javascript:sort('chgPersonTail.name')">职工姓名</a>
							<logic:equal name="pagination" property="orderBy"
								value="chgPersonTail.name">
								<logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
								<logic:equal name="pagination" property="orderother"
									value="DESC">▼</logic:equal>
							</logic:equal>
						</td>
						<td align="center" class=td2>
							证件号码
						</td>
						<td align="center" class=td2>
							变更后职工状态
						</td>
						<td align="center" class=td2>
							工资基数
						</td>
						<td align="center" class=td2>
							单位缴额
						</td>
						<td align="center" class=td2>
							职工缴额
						</td>
						<td align="center" class=td2>
							缴额合计
						</td>
						<td align="center" class=td2>
							变更原因
						</td>
					</tr>
					<logic:iterate name="chgpersonDoListAF" property="list"
						id="element" indexId="i">

						<tr>

							<td valign="top"></td>
							<td valign="top">
								<p>
									<bean:write name="element" property="chgType" />
								</p>
							</td>
							<td valign="top">
								<p>
									<bean:write name="element" property="empId" format="000000" />
								</p>
							</td>
							<td valign="top">
								<p>
									<bean:write name="element" property="name" />
								</p>
							</td>
							<td valign="top">
								<p>
									<bean:write name="element" property="cardNum" />
								</p>
							</td>
							<td valign="top">
								<p>
									<bean:write name="element" property="temp_oldPayStatus" />
								</p>
							</td>
							<td valign="top">
								<p>
									<bean:write name="element" property="salaryBase" />
								</p>
							</td>
							<td valign="top">
								<p>
									<bean:write name="element" property="orgPay" />
								</p>
							</td>
							<td valign="top">
								<p>
									<bean:write name="element" property="empPay" />
								</p>
							</td>
							<td valign="top">
								<p>
									<bean:write name="element" property="sumPay" />
								</p>
							</td>
							<td valign="top">
								<p>
									<bean:write name="element" property="temp_chgreason" />
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="11" class=td5></td>
						</tr>
					</logic:iterate>
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
									</td>
									<td align="right">
										<jsp:include page="../../../inc/pagination.jsp">
											<jsp:param name="url" value="showChgpersonWindowAC.do" />
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
										<input type="button" name="Submit42" value="关闭"
											class="buttona" onClick="javascript:window.close();">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
	</table>
</body>
</html:html>
