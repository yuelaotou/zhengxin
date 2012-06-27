<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action.ChgslarybaseTbWindowShowAC"%>	

<%
	Object pagination = request.getSession(false).getAttribute(
			ChgslarybaseTbWindowShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>工资基数调整</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
</head>
<script language="javascript"
	src="<%=request.getContextPath()%>/js/common.js">


<script language="javascript"></script>
<script language="javascript" type="text/javascript" >
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<jsp:include page="/inc/sort.jsp">
		<jsp:param name="url" value="chgslarybaseTbWindowShow.do" />
	</jsp:include>

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
							width="55">
							&nbsp;
						</td>
						<td width="235"
							background="<%=request.getContextPath()%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									
								</tr>
							</table>
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										 <font color="00B5DB"> 变更业务&gt; 工资基数调整 </font>
									</td>
									<td class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
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
										<b class="font14">单 位 信 息</b>
									</td>
									<td height="22" background="<%=request.getContextPath()%>/img/bg2.gif" align="center">
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
								<td width="17%" class="td1">
									单位编号
								</td>

								<td colspan="2" styleClass="input3">
									<html:text name="chgslarybaseListAF" property="org.id"
										styleClass="input3" readonly="true" styleId="txtsearch"></html:text>

								</td>
								<td class="td1" width="17%">
									单位名称
								</td>
								<td width="33%">
									<html:text name="chgslarybaseListAF"
										property="org.orgInfo.name" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									单位缴率
								</td>
								<td colspan="2" styleClass="input3">
									<html:text name="chgslarybaseListAF" property="org.orgRate"
										styleClass="input3" readonly="true" />
								</td>
								<td width="17%" class="td1">
									<font face="宋体">职工缴率</font>
								</td>
								<td width="33%" styleClass="input3">
									<html:text name="chgslarybaseListAF" property="org.empRate"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1"> 调整年月</td>
								<td width="26%" colspan="2">
									<html:text name="chgslarybaseListAF" property="chgMonth"
										styleClass="input3" styleId="txtsearch" readonly="true"></html:text>
								</td>
								<td width="17%" class="td1"></td>
								<td width="26%" colspan="2">

								</td>
							</tr>
							
						</table>
					
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<logic:equal name="chgslarybaseListAF" property="data_5"  value="2">
	          
	         <td class=h4>
							合计：<br>
							 调整前:汇缴金额
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_1" /> </u>
							 <br>
									调整后:汇缴金额
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_2" /> </u>  减少工资基数
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_3" /> </u>  减少缴存金额
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_4" /> </u>
						
	               
	          </td>
	        </logic:equal>
          <logic:equal name="chgslarybaseListAF" property="data_5"  value="3">
	          
	         <td class=h4>
							合计：<br>
							 调整前:汇缴金额
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_1" /> </u>
							 <br>
									调整后:汇缴金额
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_2" /> </u> 增加工资基数
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_3" /> </u>  增加缴存金额
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_4" /> </u> 减少工资基数
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_3" /> </u>  减少缴存金额
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_4" /> </u>
						
	               
	          </td>
	        </logic:equal>
			<logic:equal name="chgslarybaseListAF" property="data_5" value="1">
	          <td  align="left" height="33" class="h4">
	         <td class=h4>
							合计：<br>
							 调整前:汇缴金额
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_1" /> </u>
							 <br>
									调整后:汇缴金额
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_2" /> </u>  增加工资基数
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_3" /> </u>  增加缴存金额
							<u>：<bean:write name="chgslarybaseListAF"
									property="data_4" /> </u>
						
	               
	          </td>
	          </logic:equal>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">工资基数调整列表</b>
									</td>
									<td width="750" height="22" align="center"
										background="<%=request.getContextPath()%>/img/bg2.gif">
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
						<td align="center" height="6" colspan="1">


						</td>
					</tr>
				</table>
				<html:form action="/chgslarybaseTaMaintainAC" style="margin: 0">
				
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPaymentTail.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPaymentTail.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								职工姓名
							</td>
							<td align="center" class=td2>
								证件号码
							</td>
							<td align="center" class=td2>
								调整前工资基数
							</td>
								<td align="center" class=td2>
								调整前单位缴额
							</td>
							<td align="center" class=td2>
								调整前职工缴额
							</td>
							<td align="center" class=td2>
								调整前缴额合计
							</td>
							<td align="center" class=td2>
								调整后工资基数
							</td>
							<td align="center" class=td2>
								调整后单位缴额
							</td>
							<td align="center" class=td2>
								调整后职工缴额
							</td>
							<td align="center" class=td2>
								调整后缴额合计
							</td>
						</tr>
						<logic:notEmpty name="chgslarybaseListAF" property="list">
							<logic:iterate name="chgslarybaseListAF" property="list"
								id="element" indexId="i">
								<tr id="tr<%=i%>"
									onClick='gotoClick("tr<%=i%>","s<%=i%>", idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColor("tr<%=i%>","s<%=i%>", idAF);' class=td4
									onDblClick="">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empId" format="000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="emp.empInfo.name" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="emp.empInfo.cardNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="oldSalaryBase" />
										</p>
									</td>
									
									<td valign="top">
										<p>
											<bean:write name="element" property="oldOrgPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="oldEmpPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="oldOrgPayEmpPaySum" />
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
											<bean:write name="element" property="oldPaySum" />
										</p>
									</td>
								</tr>
								<tr>
									<td colspan="12" class=td5></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="chgslarybaseListAF" property="list">
							<tr>
								<td colspan="12" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
							<tr>
								<td colspan="12"></td>
							</tr>
						</logic:empty>
						<tr>
							<td colspan="12"></td>
						</tr>
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
											<jsp:include page="/inc/pagination.jsp">
												<jsp:param name="url" value="chgslarybaseTbWindowShow.do"/>
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
                    					<input type="button" name="Submit42" value="关闭" class="buttona" onClick="javascript:window.close();" ></td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</html:form>
				<form action="chgslarybaseTbWindowShow.do" method="POST" name="Form1"
					id="Form1">
				</form>
	</table>
	</body>
</html:html>
