<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.action.BankCheckAccTbShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			BankCheckAccTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>银行对账单</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
function reportErrors(message) {
	if(message!=null){
		alert(message);
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function onsearch(){
	var settDateSt=document.forms[0].elements["bankCheckAccTbFindDTO.settDateSt"].value.trim();
	var settDateEnd=document.forms[0].elements["bankCheckAccTbFindDTO.settDateEnd"].value.trim();
	var moneySt=document.forms[0].elements["bankCheckAccTbFindDTO.moneySt"].value.trim();
	var moneyEnd=document.forms[0].elements["bankCheckAccTbFindDTO.moneyEnd"].value.trim();
	if(settDateSt!=""){
		if(!checkDate(document.forms[0].elements["bankCheckAccTbFindDTO.settDateSt"])){
			document.forms[0].elements["bankCheckAccTbFindDTO.settDateSt"].value="";
			return false;
		}
	}
	if(settDateEnd!=""){
		if(!checkDate(document.forms[0].elements["bankCheckAccTbFindDTO.settDateEnd"])){
			document.forms[0].elements["bankCheckAccTbFindDTO.settDateEnd"].value="";
			return false;
		}
	}
	if(moneySt!=""){
		if(!checkMoney(moneySt)){
			document.forms[0].elements["bankCheckAccTbFindDTO.moneySt"].value="";
			return false;
		}
	}
	if(moneyEnd!=""){
		if(!checkMoney(moneyEnd)){
			document.forms[0].elements["bankCheckAccTbFindDTO.moneyEnd"].value="";
			return false;
		}
	}
	return true;
}
function tosubject(){
	gotoSubjectpop('0','<%=path%>','0');
}
function ondelete(){
  var x;
  x=confirm("是否删除！");
  if(x){
  	return true;
  }else
  {	
    return false;
  }
}
function ondeleteall(){
  var x;
  x=confirm("是否全部删除！");
  if(x){
  	return true;
  }else
  {	
    return false;
  }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" 
	onload="reportErrors();">
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
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="bankCheckAccTaShowAC.do" class=a2>银行对账单</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										银行对账单维护
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
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<p>
											<font color="00B5DB">出纳管理&gt;银行对账单</font>
										</p>
									</td>
									<td width="74" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="156">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="702">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/bankCheckAccTbFindAC.do" styleClass="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">

						<tr>
							<td width="15%" class="td1">
								科目
							</td>
							<td width="20%" colspan="2">
								<html:text name="bankCheckAccTbAF"
									property="bankCheckAccTbFindDTO.subjectCode"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%">
								<input type="button" name="Submit12" value="..." class="buttona"
									onclick="tosubject();">
							</td>
							<td width="15%" class="td1">
								科目名称
							</td>
							<td colspan="3" width="35%">
								<html:text name="bankCheckAccTbAF"
									property="bankCheckAccTbFindDTO.subjectName"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								结算日期
							</td>
							<td width="15%">
								<html:text name="bankCheckAccTbAF"
									property="bankCheckAccTbFindDTO.settDateSt" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="bankCheckAccTbAF"
									property="bankCheckAccTbFindDTO.settDateEnd"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%" class="td1">
								结算号
							</td>
							<td width="15%">
								<html:text name="bankCheckAccTbAF"
									property="bankCheckAccTbFindDTO.settNumSt" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="bankCheckAccTbAF"
									property="bankCheckAccTbFindDTO.settNumEnd" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								摘要
							</td>
							<td colspan="3" width="35%">
								<span class="td4"> <html:select
										property="bankCheckAccTbFindDTO.summary" styleClass="input4"
										name="bankCheckAccTbAF" onkeydown="enterNextFocus1();">
										<option value=""></option>
										<html:options collection="summrayList1" property="value"
											labelProperty="label" />
									</html:select> </span>
							</td>
							<td width="15%" class="td1">
								金额
							</td>
							<td width="15%">
								<html:text name="bankCheckAccTbAF"
									property="bankCheckAccTbFindDTO.moneySt" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="bankCheckAccTbAF"
									property="bankCheckAccTbFindDTO.moneyEnd" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona" onclick="return onsearch()">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td class=h4>
							合计： 银行借方金额
							<u>：<bean:write name="bankCheckAccTbAF"
									property="bankCheckAccTbFindDTO.debitSum" format="0.00" /> </u>；银行贷方金额
							<u>：<bean:write name="bankCheckAccTbAF"
									property="bankCheckAccTbFindDTO.creditSum" format="0.00" /> </u>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="156">
										<b class="font14">银 行 对 账 单 列 表</b>
									</td>
									<td width="702" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/bankCheckAccTbMaintainAC.do"
					styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3"
						align="center">
						<tr align="center">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td class="td2">
								<a href="javascript:sort('fn211.sett_date')">结算日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn211.sett_date">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								摘要
							</td>
							<td class="td2">
								<a href="javascript:sort('fn211.subject_code')">科目</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn211.subject_code">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								科目名称
							</td>
							<td class="td2">
								<a href="javascript:sort('fn211.debit')">银行借方金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn211.debit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('fn211.credit')">银行贷方金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn211.credit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								结算方式
							</td>
							<td class="td2">
								结算号
							</td>
							<td class="td2">
								经手人
							</td>
						</tr>
						<logic:notEmpty name="bankCheckAccTbAF" property="list">
							<%
								int j=0;
								String strClass="";
							 %>
							<logic:iterate id="e" name="bankCheckAccTbAF" property="list"
								indexId="i">
								<%	j++;
								if (j%2==0) {strClass = "td8";
								}
							    else {strClass = "td9";
							    }%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>",idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass %>">
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="e" property="credenceId"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td>
										<bean:write name="e" property="settDate" />
									</td>
									<td>
										<bean:write name="e" property="temp_summary" />
									</td>
									<td>
										<bean:write name="e" property="subjectCode" />
									</td>
									<td>
										<bean:write name="e" property="subjectName" />
									</td>
									<td>
										<bean:write name="e" property="debit" />
									</td>
									<td>
										<bean:write name="e" property="credit" />
									</td>
									<td>
										<bean:write name="e" property="temp_settType" />
									</td>
									<td>
										<bean:write name="e" property="settNum" />
									</td>
									<td>
										<bean:write name="e" property="dopsn" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="bankCheckAccTbAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>
						</logic:empty>
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
											<br>
										</td>
										<td align="right">
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="bankCheckAccTbShowAC.do" />
											</jsp:include>
											<br>
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
									    <td width="69" align="right">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.example.exports" />
											</html:submit>
										</td>
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.database.imports" />
											</html:submit>
										</td>
										<logic:notEmpty name="bankCheckAccTbAF" property="list">
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.update" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona" onclick="return ondelete();">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona" onclick="return ondeleteall();">
													<bean:message key="button.deleteall" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</logic:notEmpty>
										<logic:empty name="bankCheckAccTbAF" property="list">
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona" disabled="true">
													<bean:message key="button.update" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona" disabled="true">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona" disabled="true">
													<bean:message key="button.deleteall" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona" disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</logic:empty>
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
