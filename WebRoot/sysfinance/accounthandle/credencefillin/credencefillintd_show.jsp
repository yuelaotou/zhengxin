<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@	taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@page
	import="org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action.CredenceFillinTdShowdAC"%>

<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = session
			.getAttribute(CredenceFillinTdShowdAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>凭证录入维护</title>
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	</head>
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script>
var tr="tr0";
var s1,s2,s3,s4,s5;
function init(){
 	for(i=0;i<document.all.length;i++){//固定写法
		if(document.all.item(i).type=="submit"){//如果按钮是提交
			if(document.all.item(i).value=="修改"){
				s1=i;
			}
			if(document.all.item(i).value=="删除"){
				s2=i;
			}
			if(document.all.item(i).value=="全部删除"){
				s3=i;
			}
			if(document.all.item(i).value=="打印"){
				s4=i;
			}
			if(document.all.item(i).value=="凭证连打"){
				s5=i;
			}
		}
	}
	if(document.getElementById(tr)!=null){
		var bizSt=document.getElementById(tr).childNodes[7].innerHTML;
  		if(bizSt.trim()!='确认'){
  			document.all.item(s1).disabled=true;
  			document.all.item(s2).disabled=true;
  		}else{
  			document.all.item(s1).disabled=false;
  			document.all.item(s2).disabled=false;
  		}
	}
	var count = "<bean:write name='pagination' property='nrOfElements'/>";
	if(count == 0) {
		document.all.item(s1).disabled=true;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=true;
		document.all.item(s5).disabled=true;
	}
}
function gettr(trindex) {
  	tr=trindex;
  	init();
}

function seachSujectCode(){
	gotoSubjectpop('0','<%=path%>','4');
}
function tofocus(){ //按回车置下一个位置
	document.forms[0].elements["credencecheckFindDTO.subjectName"].focus();
	return false;
}
function reportErrors(){
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
}
function ondelete(){
  	var x=confirm("是否要进行删除操作！");
  	if(x){
		return true;
  	}else{
    	return false;
  	}
}
function buttonForward(){
 	var credenceId=document.getElementById(tr).childNodes[12].innerHTML;
 	window.open('<%=request.getContextPath()%>/sysfinance/queryFlowShowAC.do?credenceId='+credenceId,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes'); 
	return false;
}
</script>
	<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();init();">
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
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">账户处理&gt;凭证维护</font>
										</td>
										<td class=font14>
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
					<html:form action="/credenceFillinTdFindAC">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="127">
												<b class="font14">查 询 条 件</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="777">
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
								<td width="15%" class="td1">
									日期
									<br>
								</td>
								<td width="15%">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.credenceDateSt"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="5%" align="center">
									至
								</td>
								<td width="15%">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.credenceDateEnd"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="15%" class="td1">
									结算日期
								</td>
								<td width="15%">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.settDateSt" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="5%" align="center">
									至
								</td>
								<td width="15%">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.settDateEnd"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="15%" class="td1">
									科目代码
								</td>
								<td width="20%" colspan="2">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.subjectCode"
										styleClass="input3"
										onkeydown="javascrip:if(window.event.keyCode==13){return tofocus();}" />
								</td>
								<td width="15%">
									<input type="button" name="submit12" class=buttona value="..."
										onclick="seachSujectCode();" />
								</td>
								<td width="15%" class="td1">
									科目名称
								</td>
								<td width="35%" colspan="3">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.subjectName"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="15%" class="td1">
									凭证号
								</td>
								<td width="15%">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.credenceNumSt"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="5%" align="center">
									至
								</td>
								<td width="15%">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.credenceNumEnd"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="15%" class="td1">
									金额
								</td>
								<td width="15%">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.moneySt" styleClass="input3"
										onkeydown="enterNextFocus1();" maxlength="15" />
								</td>
								<td width="5%" align="center">
									至
								</td>
								<td width="15%">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.moneyEnd" styleClass="input3"
										onkeydown="enterNextFocus1();" maxlength="15" />
								</td>
							</tr>
							<tr>
								<td width="15%" class="td1">
									结算方式
								</td>
								<td width="35%" colspan="3">
									<html:select property="credencecheckFindDTO.settType"
										styleClass="input4" name="credenceFillinTdAF"
										onkeydown="enterNextFocus1();">
										<option value=""></option>
										<html:options collection="settTypeList1" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td width="15%" class="td1">
									结算号
								</td>
								<td width="35%" colspan="3">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.settNum" styleClass="input3"
										onkeydown="enterNextFocus1();" maxlength="15" />
								</td>
							</tr>
							<tr>
								<td width="15%" class="td1">
									状态
								</td>
								<td width="35%" colspan="3">
									<html:select name="credenceFillinTdAF"
										property="credencecheckFindDTO.credenceSt" styleClass="input4"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="credenceStMap"
											name="credenceFillinTdAF" label="value" value="key" />
									</html:select>
								</td>
								<td class="td1" width="15%">
									操作员
								</td>
								<td width="35%" colspan="3">
									<html:select property="credencecheckFindDTO.operator"
										styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="operList1" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="15%" class="td1">
									摘要
								</td>
								<td width="35%" colspan="3">
									<html:text name="credenceFillinTdAF"
										property="credencecheckFindDTO.summary" styleClass="input3"
										onkeydown="enterNextFocus1();" maxlength="15" />
								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<input type="submit" name="submit1" class=buttona value="查询" />
								</td>
							</tr>
						</table>
					</html:form>
					<html:form action="/credenceFillinTdMainTainAC">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td class=h4>
									合计： 借方金额
									<u>:<bean:write name="credenceFillinTdAF"
											property="credencecheckFindDTO.debitSum" format="0.00" /> </u>
									贷方金额
									<u>：<bean:write name="credenceFillinTdAF"
											property="credencecheckFindDTO.creditSum" format="0.00" /> </u>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="124">
												<b class="font14">凭 证 列 表</b>
											</td>
											<td width="734" height="22" align="center"
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
							<tr align="center">
								<td height="23" bgcolor="C4F0FF">
									&nbsp;
								</td>
								<td class="td2">
									<a href="javascript:sort('fn201.credence_date')">日期</a>
									<logic:equal name="pagination" property="orderBy"
										value="fn201.credence_date">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td class="td2">
									<a href="javascript:sort('fn201.credence_num')">凭证号</a>
									<logic:equal name="pagination" property="orderBy"
										value="fn201.credence_num">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td class="td2">
									借方金额
								</td>
								<td class="td2">
									贷方金额
								</td>
								<td class="td2">
									制单人
								</td>
								<td class="td2">
									复核人
								</td>
								<td class="td2">
									状态
								</td>
							</tr>
							<logic:notEmpty name="credenceFillinTdAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate id="e" name="credenceFillinTdAF" property="list"
									indexId="i">
									<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
									%>
									<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);'
										onMouseOver='this.style.background="#eaeaea"'
										onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
										<td>
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="e" property="credenceNum"/>,<bean:write name="e" property="credenceId"/>,<bean:write name="e" property="settNum"/>,<bean:write name="e" property="credenceDate"/>,<bean:write name="e" property="office"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</td>
										<td>
											<bean:write name="e" property="credenceDate" />
										</td>
										<td>
											<p>
												<a href="#"
													onClick="window.open('credencePopShowAC.do?docNum=<bean:write name="e" property="credenceNum"/>&credenceDate=<bean:write name="e" property="credenceDate"/>&credenceId=<bean:write name="e" property="credenceId"/>&office=<bean:write name="e" property="office"/>','','width=900,height=500,top=150,left=150,scrollbars=yes');"><bean:write
														name="e" property="credenceNum" /> </a>
											</p>
										</td>
										<td align="right">
											<bean:write name="e" property="debit" format="#,##0.00" />
										</td>
										<td align="right">
											<bean:write name="e" property="credit" format="#,##0.00" />
										</td>
										<td align="center">
											<bean:write name="e" property="makeBill" />
										</td>
										<td align="center">
											<bean:write name="e" property="checkpsn" />
										</td>
										<td>
											<bean:write name="e" property="credenceSt" />
										</td>
										<td style="display:none">
											<bean:write name="e" property="credenceId" />
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="credenceFillinTdAF" property="list">
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
											</td>
											<td align="right">
												<jsp:include page="../../../inc/pagination.jsp">
													<jsp:param name="url" value="credenceFillinTdShowdAC.do" />
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
											<logic:notEqual name="single" value="single">
												<td width="70" align="center">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.update" />
													</html:submit>
												</td>
												<td width="70" align="center">
													<html:submit property="method" styleClass="buttona"
														onclick="return ondelete();">
														<bean:message key="button.delete" />
													</html:submit>
												</td>
												<td width="70" align="center">
													<html:submit property="method" styleClass="buttona"
														onclick="return ondelete();">
														<bean:message key="button.deleteall" />
													</html:submit>
												</td>
											</logic:notEqual>
											<td width="70" align="center">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="70" align="center">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.continuum.print" />
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
