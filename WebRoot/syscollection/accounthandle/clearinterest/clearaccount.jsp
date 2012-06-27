<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.clearinterest.action.ShowClearAccountListAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ShowClearAccountListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String date = (String) request.getAttribute("bizDate");
	String cleardate = (String) request.getAttribute("clearbizDate");
%>
<html>

	<head>
		<title>账务处理-年终结息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
var flagss=false;
function loads(){
	document.clearaccountShowAF.officecode.value.value="";
	document.clearaccountShowAF.bankcode.value="";
	document.clearaccountShowAF.orgcode.value="";
	document.clearaccountShowAF.orgname.value="";
	document.clearaccountShowAF.oper.value="";
	
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	var x= document.getElementsByName("rowArray");
	for(var i=0;i<x.length;i++){
		x[i].checked=flagss;
	}
	flagss=(!flagss);
	
}

function search(){
	var officecode=document.clearaccountShowAF.officecode.value.trim();
	var bankcode=document.clearaccountShowAF.bankcode.value.trim();
	var orgcode=document.clearaccountShowAF.orgcode.value.trim();
	var orgname=document.clearaccountShowAF.orgname.value.trim();
	var oper=document.clearaccountShowAF.oper.value.trim();
	document.URL='findClearAccountListAC.do?officecode='+officecode+'&bankcode='+bankcode+'&orgcode='+orgcode+'&orgname='+orgname+'&oper='+oper+'';
}

function clearaccount(eee){
	var arr= document.getElementsByName("rowArray");
	var temp=0;
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			temp=temp+1;
		}
	}
	if(temp==0){
		alert("请选择要结息的单位！！");
		return false;
	}else{
		/*var x=confirm("当前会计日期为："+<%=date%>+"！确定进行年终结息业务？");
		if(x){
			setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
			
			return true;
		}else{
			return false;
		}*/
		var date='<%=cleardate%>';
		if(date!='0630' && date!='0701'){
			alert("请在结息日进行结息！！");
			return false;
		}else{
			setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
			return true;
		}
	}
	
}

function clearaccountall(eee){
		/*var x=confirm("当前会计日期为："+<%=date%>+"！确定进行全部年终结息业务？");
		if(x){
			setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
			
			return true;
		}else{
			return false;
		}*/
		var date='<%=cleardate%>';
		if(date!='0630' && date!='0701'){
			alert("请在结息日进行结息！！");
			return false;
		}else{
			setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
			return true;
		}
}

var flag=true;
function chooseall(){
	var x= document.getElementsByName("rowArray");
	for(var i=0;i<x.length;i++){
		x[i].checked=flag;
	}
	flag=(!flag);
	return false;
}

function checkID(){
	var id=document.clearaccountShowAF.orgcode.value.trim();
	if(isNaN(id)){
		alert("请录入单位编号!!");
		document.clearaccountShowAF.orgcode.focus();
		return false;	
	}else{
		return true;
	}
}

</script>

	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
		onload="loads();">
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
							<td width="235" background="<%=path%>/img/table_bg_line.gif">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											年终结息
										</td>
									</tr>
								</table>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">账务处理</font><font color="00B5DB">&gt;年终结息</font>
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
											<b class="font14">查 询 信 息</b>
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
					<html:form action="/findClearAccountListAC.do" style="margin: 0">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="17%" class="td1" algin="center">
									办事处
								</td>
								<td width="22%">
									<html:select property="officecode" styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="officeList1" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td class="td1" width="17%" algin="center">
									归集银行
								</td>
								<td width="22%">
									<html:select property="bankcode" styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="bankList1" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1" algin="center">
									单位编号
								</td>
								<td width="22%">
									<html:text name="clearaccountShowAF" property="orgcode"
										onblur="return checkID();" styleClass="input3"
										styleId="txtsearch">
									</html:text>
								</td>
								<td class="td1" width="17%" algin="center">
									单位名称
								</td>
								<td width="22%">
									<html:text name="clearaccountShowAF" property="orgname"
										styleClass="input3" styleId="txtsearch"></html:text>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1" algin="center">
									操作员
								</td>
								<td width="22%">
									<html:select property="oper" styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="operList1" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td width="17%" algin="center"></td>
								<td width="22%">
								</td>
							</tr>
							<tr>
								<td width="17%" algin="center"></td>
								<td width="22%">
								</td>
								<td width="17%" algin="center"></td>
								<td align="right">
									<html:submit property="method" styleClass="buttona"
										onclick="search()">
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="154">
											<b class="font14">单 位 列 表</b>
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
					<html:form action="/maintainClearAccountListAC.do"
						style="margin: 0">
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr>
								<td align="center" height="23" bgcolor="C4F0FF" class=td2></td>
								<td align="center" class=td2>
									单位编号
								</td>
								<td align="center" class=td2>
									单位名称
								</td>
								<td align="center" class=td2>
									缴存人数
								</td>
							</tr>
							<logic:notEmpty name="clearaccountlist">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate name="clearaccountlist" id="clearaccountDTO"
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
										<td>
											<html:multibox property="rowArray">
												<bean:write name="clearaccountDTO" property="orgcode" />
											</html:multibox>
										</td>
										<td valign="top">
											<p>
												<bean:write name="clearaccountDTO" property="orgcode"
													format="000000" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="clearaccountDTO" property="orgname" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="clearaccountDTO" property="empcount" />
											</p>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="clearaccountlist">
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
													<jsp:param name="url" value="showClearAccountListAC.do" />
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


									<logic:notEmpty name="clearaccountlist">
										<table border="0" cellspacing="0" cellpadding="0">
											<tr>
												<%-- <td><html:button property="method" styleClass="buttona" onclick="return chooseall();" ><bean:message key="button.chooseall"/></html:button></td>--%>
<%--												<td>--%>
<%--													<html:submit property="method" styleClass="buttona"--%>
<%--														onclick="return clearaccount(this);">--%>
<%--														<bean:message key="button.in.clearaccount" />--%>
<%--													</html:submit>--%>
<%--												</td>--%>
												<td>
													<html:submit property="method" styleClass="buttona"
														onclick="return clearaccountall(this);">
														<bean:message key="button.in.clearaccountall" />
													</html:submit>
												</td>
											</tr>
										</table>
									</logic:notEmpty>
									<logic:empty name="clearaccountlist">
										<table border="0" cellspacing="0" cellpadding="0">
											<tr>
												<%-- <td><html:button property="method" styleClass="buttona" onclick="return chooseall();" disabled="true"><bean:message key="button.chooseall"/></html:button></td>--%>
<%--												<td>--%>
<%--													<html:submit property="method" styleClass="buttona"--%>
<%--														onclick="return clearaccount(this);" disabled="true">--%>
<%--														<bean:message key="button.in.clearaccount" />--%>
<%--													</html:submit>--%>
<%--												</td>--%>
												<td>
													<html:submit property="method" styleClass="buttona"
														onclick="return clearaccountall(this);" disabled="true">
														<bean:message key="button.in.clearaccountall" />
													</html:submit>
												</td>
											</tr>
										</table>
									</logic:empty>
								</td>
							</tr>
						</table>
					</html:form>
		</table>
	</body>
</html>
