<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.accounthandle.clearaccount.action.PrincipalDayAccShowAC"%>
<%
	Object pagination = request.getSession(false).getAttribute(
	PrincipalDayAccShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>个贷管理</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	<script language="javascript" src="js/common.js">
</head>
<script language="javascript" ></script>
	<script language="javascript" type="text/javascript">
function checkDate1(){
  	var date1 = document.forms[1].elements["beginBizDate"].value;
  	var date2 = document.forms[1].elements["endBizDate"].value;
  	if(date1 != "" && date2 != ""){
	   	var aa = checkDate2('beginBizDate','endBizDate');
	   	if(!aa){
	    	return false;
	   	}
  	}
  	if(date1 != "" ){
	   	var aa =checkDate0('beginBizDate');
	    if(!aa){
	    	return false;
	   	}   
   	}
    if(date2 != "" ){
	   	var aa =checkDate0('endBizDate');
    	if(!aa){
    		return false;
	   	}   
   	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" 
	onContextmenu="return true">
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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										本金日账
									</td>
								</tr>
							</table>
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
										<font color="00B5DB">统计查询&gt;本金日账</font>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="160">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="750">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/principalDayAccFindAC.do" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								业务日期
							</td>
							<td width="33%" align="center" colspan="3">
								<html:text name="principalDayAccAF" property="searchDate"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								放款银行
							</td>
							<td width="33%">
								<html:select name="principalDayAccAF" property="loanBankName"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3">
									<html:option value=""></html:option>
									<html:options collection="loanBankNameList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkDate1();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/principalDayAccPrintAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="160">
											<b class="font14">本金日账明细</b>
										</td>
										<td width="750" height="22" align="center"
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
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td align="center" class=td2>
								日总账
							</td>
							<td align="center" class="td2">
								期初余额
							</td>
							<td align="center" class="td2">
								本期借方
							</td>
							<td align="center" class=td2>
								本期贷方
							</td>
							<td align="center" class=td2>
								期末余额
							</td>
							<td align="center" class=td2>
								本期借方笔数
							</td>
							<td align="center" class=td2>
								本期贷方笔数
							</td>
						</tr>
						<logic:notEmpty name="clearaccountAF" property="list">
							<logic:iterate name="clearaccountAF" property="list" id="element"
								indexId="i">
								<tr>
									<td>
										<bean:write name="element" property="monthDay" />
									</td>
									<td>
										<bean:write name="element" property="beforebalance" />
									</td>
									<td>
										<bean:write name="element" property="thisdebit" />
									</td>
									<td>
										<bean:write name="element" property="thiscredit" />
									</td>
									<td>
										<bean:write name="element" property="lastbalance" />
									</td>
									<td>
										<bean:write name="element" property="thisdebitcount" />
									</td>
									<td>
										<bean:write name="element" property="thiscreditcount" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="principalDayAccAF" property="list">
							<tr>
								<td colspan="9" height="30" style="color:red">
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
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="/inc/pagination.jsp">
												<jsp:param name="url" value="principalDayAccShowAC.do" />
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
										<logic:notEmpty name="principalDayAccAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</logic:notEmpty>
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
</html:html>
