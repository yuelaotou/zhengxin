<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loancallback.bankimports.action.BankImportsTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			BankImportsTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
</head>
<script type="text/javascript">
function loads(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
</logic:messagesPresent>
	document.forms[1].elements["loanKouAcc"].value="";
	document.forms[1].elements["contractId"].value="";
	document.forms[1].elements["borrowerName"].value="";
	document.forms[1].elements["cardNum"].value="";
	var count="<bean:write name="pagination" property="nrOfElements"/>";
	if(count==0){
		document.all.disp2.disabled="true";
		document.all.disp3.disabled="true";
	}
}
function on_Submit(eee){
    if(document.forms[0].theFile.value==""){
    	alert("请选择导入文件！！！");
  		return false;
 	} else {
    	document.forms[0].url.value=document.forms[0].theFile.value.trim();
       	setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
    	return true;
  	}
} 

function gotoDeleteAll(){
	if(!confirm("是否确定删除所有记录？")){
		return false;
	}
}
function gotoCallBack(){
	var allcount=document.all.allCount.value;
	var count="<bean:write name="pagination" property="nrOfElements"/>";
	if(count != allcount){
		alert("列表中记录不是该银行下所有记录，不能进行回收操作！");
		return false;
	}
	if(!confirm("是否确定回收该银行下所有记录？")){
		return false;
	}
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">
	<jsp:include flush="true" page="/waiting.inc" />
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
						<td background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37" align="center" valign="top"
										style="PADDING-top: 7px"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
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
									<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">回收贷款&gt;银行代扣导入</font>
									</td>
									<td width="100" class=font14>
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
				<html:form action="/bankImportsTaImportAC" style="margin: 0"
					enctype="multipart/form-data">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="194">
											<b class="font14">回收银行信息</b>
										</td>
										<td width="731" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								数据来源
							</td>
							<td width="20%">
								<html:file name="bankImportsTaImportAF" property="theFile"
									size="50" maxlength="100" styleClass="input3" />
								<html:hidden property="url" />
							</td>
							<td width="11%">
							</td>
							<td width="17%">
								&nbsp;
							</td>
							<td width="33%">
								<html:submit property="method" styleClass="buttonb"
									styleId="disp1" onclick="return on_Submit(this);">
									<bean:message key="button.database.imports" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/bankImportsTaFindAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="195">
											<b class="font14">查 询 条 件</b>
										</td>
										<td width="730" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								放款银行
							</td>
							<td width="33%">
								<html:text name="bankImportsTaAF" property="loanBankName"
									styleClass="input3" readonly="true" />
								<html:hidden name="bankImportsTaAF" property="loanBankId" />
							</td>
							<td width="17%" class="td1">
								扣款账号
							</td>
							<td width="33%">
								<html:text property="loanKouAcc" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%">
								<html:text property="contractId" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%">
								<html:text property="borrowerName" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="33%">
								<html:text property="cardNum" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1"></td>
							<td width="33%" class="td7">
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona" onclick="">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/bankImportsTaMaintainAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：人数
								<u>：<bean:write name="DTO" property="realCount" /> </u> 实还本金
								<u>：<bean:write name="DTO" property="realCorpus" /> </u> 实还利息
								<u>：<bean:write name="DTO" property="realInterest" /> </u>逾期利息
								<u>：<bean:write name="DTO" property="realPunishInterest" />
								</u>总还款额
								<u>：<bean:write name="DTO" property="sumMoney" /> </u>挂账发生额
								<u>：<bean:write name="DTO" property="occurMoney" /> </u>实收金额
								<u>：<bean:write name="DTO" property="realMoney" /> </u>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="197">
											<b class="font14">银行代扣数据导入信息列表</b>
										</td>
										<td width="728" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
											<input type="hidden" name="allCount"
												value="<bean:write name="allCount"/>">
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
							<td class="td2">
								扣款账号
							</td>
							<td class="td2">
								合同编号
							</td>
							<td class="td2">
								<a href="javascript:sort('borrower.borrowerName')">借款人姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="borrower.borrowerName">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								证件号码
							</td>
							<td class="td2">
								还款类型
							</td>
							<td class="td2">
								还款年月
							</td>
							<td class="td2">
								实还本金
							</td>
							<td class="td2">
								实还利息
							</td>
							<td class="td2">
								逾期天数
							</td>
							<td class="td2">
								逾期利息
							</td>
							<td class="td2">
								总还款额
							</td>
							<td class="td2">
								挂账发生额
							</td>
							<td class="td2">
								实收金额
							</td>
						</tr>
						<logic:notEmpty name="callbacklist">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="callbacklist" id="element" indexId="i">
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
									class="<%=strClass%>" onDblClick="">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanKouAcc" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="contractId" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="borrowerName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="cardNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanType" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanKouYearmonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="realCorpus" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="realInterest" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="days" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="realPunishInterest" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="sumMoney" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="occurMoney" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="realMoney" />
										</p>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="callbacklist">
							<tr>
								<td colspan="15" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>

						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="left">
								共
								<bean:write name="pagination" property="nrOfElements" />
								项
							</td>
							<td align="right">
								<jsp:include page="../../../inc/pagination.jsp">
									<jsp:param name="url" value="bankImportsTaShowAC.do" />
								</jsp:include>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr align="center">
										<td width="70" height="30">
											<html:submit property="method" styleClass="buttona"
												styleId="disp2" onclick="return gotoDeleteAll();">
												<bean:message key="button.deleteall" />
											</html:submit>
										</td>
										<td width="70" height="30">
											<html:submit property="method" styleClass="buttona"
												styleId="disp3" onclick="return gotoCallBack();">
												<bean:message key="button.callback" />
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
</html:html>
