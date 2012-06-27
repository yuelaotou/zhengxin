<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.collloanback.action.CollLoanbackTbShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			CollLoanbackTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>公积金还贷</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
var flag=false;
function reportErrors(message) {
	if(message!=null){
		alert(message);
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var x= document.getElementsByName("rowArray");
	for(var i=0;i<x.length;i++){
		x[i].checked=flag;
	}
	flag=(!flag);
	return false;
}
var tr="tr0"; 
function checks(trindex){
	tr=trindex;
	var status=document.getElementById(tr).childNodes[4].innerHTML.trim();
	if(status=='导出'){
		alert("状态为导出的不能删除!");
		return false;
	}else  
	return true;
}
function a(){
	var p = "<%=path%>";
	officeAjax(p);
}
function ondelete(){
  var x;
  var arr= document.getElementsByName("rowArray");
  var temp=0;
  for(var i=0;i<arr.length;i++){
	if(arr[i].checked==true){
		temp=temp+1;
	}
  }
  if(temp==0){
	alert("请选择删除的记录！");
	return false;
  }else{
	  x=confirm("是否删除！");
	  if(x){
	  	return true;
	  }else
	  {	
	    return false;
	  }
   }
}
function ondeleteall(){
  var array= document.getElementsByName("rowArray");
  for(var i=0;i<array.length;i++){
	var status=document.getElementById('tr'+i).childNodes[4].innerHTML.trim();
  	if(status=='导出'){
		alert("有状态为导出的数据，不能全部删除!");
		return false;
	}
  }
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
						<td background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<logic:notEqual name="collLoanbackTaAF" property="sun"
										value="sun">
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											<a href="collLoanbackTaForwardAC.do" class=a2>办理公积金还贷</a>
										</td>
									</logic:notEqual>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										公积金还贷维护
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
										<font color="00B5DB">公积金还贷</font>
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
				<html:form action="/collLoanbackTbFindAC.do" styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="176">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="734">
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
							<td class=td1 width="14%">
								办事处
							</td>
							<td width="36%" class="td4">
								<html:select name="collLoanbackTaAF" property="officeCode"
									styleClass="input4" onchange="a();">
									<option value=""></option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td class=td1 width="14%">
								银行
							</td>
							<td colspan="3" class="td4">
								<html:select name="collLoanbackTaAF" property="collectionBankId"
									styleClass="input4">
									<option value=""></option>
									<html:options collection="loanbankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="14%" class=td1>
								批次号
							</td>
							<td>
								<html:text name="collLoanbackTaAF" property="batchNum"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>

							<td width="14%" class=td1>
								办理日期
							</td>
							<td>
								<html:text name="collLoanbackTaAF" property="startdate"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="4%">
								至
							</td>
							<td>
								<html:text name="collLoanbackTaAF" property="enddate"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>

						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" align="right" cellpadding="0" cellspacing="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/collLoanbackTbMaintainAC.do"
					styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="174">
											<strong class="font14">公积金还贷列表</strong>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="736">
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
							<td bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td bgcolor="C4F0FF" class="td2">
								办事处
							</td>
							<td class="td2">
								银行
							</td>
							<td class="td2">
								批次号
							</td>
							<td class="td2">
								总扣款人数
							</td>
							<td class="td2">
								总扣款金额
							</td>
							<td class="td2">
								实扣款人数
							</td>
							<td class="td2">
								实扣款金额
							</td>
							<td class="td2">
								失败扣款人数
							</td>
							<td class="td2">
								失败扣款金额
							</td>
							<td class="td2">
								状态
							</td>
							<td class="td2">
								扣款日期
							</td>
						</tr>
						<logic:notEmpty name="collLoanbackTaAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="collLoanbackTaAF" property="list"
								indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" class="<%=strClass%>">
									<td onclick='return checks("tr<%=i%>")'>
										<html:multibox property="rowArray">
											<bean:write name="e" property="id" />
										</html:multibox>
									</td>
									<td>
										<bean:write name="e" property="officeCode" />
									</td>
									<td>
										<bean:write name="e" property="loanBankId" />
									</td>
									<td>
										<%--
									loanBankId=<bean:write name="e" property="loanBankId"/>&
										--%>
										<a href="#"
											onClick="window.open('<%=path%>/syscollection/common/biz/loankoupop/loanKouPopForwardAC.do?batchNum=<bean:write name="e" property="batchNum"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');"><bean:write
												name="e" property="batchNum" /> </a>
									</td>

									<td>
										<bean:write name="e" property="all_count" />
									</td>

									<td>
										<bean:write name="e" property="all_kou_money" />
									</td>
									<td>
										<bean:write name="e" property="real_count" />
									</td>

									<td>
										<bean:write name="e" property="real_kou_money" />
									</td>
									<td>
										<bean:write name="e" property="no_count" />
									</td>

									<td>
										<bean:write name="e" property="no_kou_money" />
									</td>

									<td>
										<bean:write name="e" property="status" />
									</td>
									<td>
										<bean:write name="e" property="bizdate" />
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="collLoanbackTaAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
									<br>
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
											<jsp:include page="../../inc/pagination.jsp">
												<jsp:param name="url" value="collLoanbackTbShowAC.do" />
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
										<logic:notEmpty name="collLoanbackTaAF" property="list">
											<logic:equal name="collLoanbackTaAF" property="sun"
												value="sun">
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.delete" />
													</html:submit>
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.deleteall" />
													</html:submit>
												</td>
											</logic:equal>
											<logic:notEqual name="collLoanbackTaAF" property="sun"
												value="sun">
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														onclick="return ondelete();">
														<bean:message key="button.delete" />
													</html:submit>
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														onclick="return ondeleteall();">
														<bean:message key="button.deleteall" />
													</html:submit>
												</td>
											</logic:notEqual>
										</logic:notEmpty>
										<logic:empty name="collLoanbackTaAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.deleteall" />
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
