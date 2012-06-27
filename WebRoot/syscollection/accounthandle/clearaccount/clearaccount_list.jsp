<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			ClearAccountTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<html>
	<head>
		<title>账务处理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
		<script>
var flagss=false;
function loads(){
	
	document.clearAccountShowAF.bank1.value="";
	document.clearAccountShowAF.orgId.value="";
	document.clearAccountShowAF.orgName.value="";
	document.clearAccountShowAF.operator1.value="";
	document.clearAccountShowAF.bis_type1.value="";
	document.clearAccountShowAF.bis_Status1.value="";
	document.clearAccountShowAF.docNum.value="";
	
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	var x= document.getElementsByName("rowArray");
	for(var i=0;i<x.length;i++){
		x[i].checked=flagss;
	}
	flagss=(!flagss);
	return false;
	
}
</script>

		<script>
var zz=0;
var rowspan=[];//保存选中的选项号,第几行的数据
var j=0;//数组下标
var temp=0;//选中几条记录
var irr=0;//数组下标
var arr= document.getElementsByName("rowArray");
function check(eee){
zz=0;
rowspan=[];//保存选中的选项号,第几行的数据
j=0;//数组下标
temp=0;//选中几条记录
irr=0;//数组下标
//var rowArray=document.forms[1].elements.length;
//var rowArray=document.clearAccountShowAF.elements["rowArray"].length;
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			rowspan[j]=i;
			j++;
			temp=temp+1;
		}
	}
	checktrainout(irr);
	if(temp==0){
		alert("请选择扎账的单位！！");
		return false;
	}else if(zz==1){
		var x=confirm("确定进行扎账业务？");
		if(x){
			setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
			return true;
		}else{
			zz=0;
			return false;
		}
	}else{
		zz=0;
		return false;
	}
}
function checktrainout(irr){
		if(irr==temp){
			zz=1;
			return true;
		}else if(irr==temp+1){
			zz=0;
			return false;
		}
		var tr;
		for(var i=irr;i<temp;i++){
			tr="tr"+rowspan[irr];
  			var status=document.getElementById(tr).childNodes[6].childNodes[0].innerHTML;
  			if(status=='转出'){
			    var queryString = "clearaccountTacheckAAC.do?";
			    queryString = queryString + "rowarray="+arr[rowspan[irr]].value+"&row="+i+"";
			    findInfo(queryString);
  			}else{
  				zz=1;
  			}
			irr++;
		}
		return true;
}
function display(flag,trainoutorgid,traininorgid,arrayrow){
	if(flag=='1'){
		//存在未确认的转入
		var x=confirm("编号为"+trainoutorgid+"单位的转出业务对应编号为"+traininorgid+"单位的转入业务未确认，是否继续？");		
		if(x){
			zz=1;
			arrayrow=parseFloat(arrayrow)+1;
			checktrainout(arrayrow);
		}else{
			zz=0;
			arrayrow=temp+1;
			checktrainout(arrayrow);
		}
	}else{
		zz=1;
		arrayrow=parseFloat(arrayrow)+1;
		checktrainout(arrayrow);
	}
}

var tr="tr0"; 
var flag=true;
function checks(trindex){
	var x= document.getElementsByName("rowArray");

	tr=trindex;
	var status=document.getElementById(tr).childNodes[11].childNodes[0].innerHTML.trim();
	var type=document.getElementById(tr).childNodes[6].childNodes[0].innerHTML.trim();
	
	if(type=='结息'){
		alert("业务类型为结息的不能扎账");
		return false;
	/*}else if(type=='公积金余额结转'){
		alert("业务类型为公积金余额结转的不能扎账");
		return false;
	}else if(type=='挂账余额结转'){
		alert("业务类型为挂账余额结转的不能扎账");
		return false;*/
	}
	if(status!='复核'){
		alert("请选择业务状态为复核的进行扎账业务");
		return false;
	}else  
	return true;
}
function check_date(eee){
zz=0;
j=0;//数组下标
temp=0;//选中几条记录
irr=0;//数组下标
	<%
			session = request.getSession();
    		List list=(List) session.getAttribute("clearaccountList"); 
 	%>	
 	temp=<%=list.size()%>;
 	for(var i=0;i<temp;i++){
		var queryString = "clearaccountTacheckAAC.do?";
		queryString = queryString + "rowarray=wl&row="+i+"";
		findInfo(queryString);
		if(zz==0){
			break;
		}
 	}
	if(zz==1){
		var x=confirm("确定进行扎账业务? ");
		if(x){    
			setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
			return true;
		}else{
		  return false;
		}
	}else{
		return false;
	}
}
function display2(flag,trainoutorgid,traininorgid){
	if(flag=='1'){
		//存在未确认的转入
		var x=confirm("编号为"+trainoutorgid+"单位的转出业务对应编号为"+traininorgid+"单位的转入业务未确认，是否继续？");		
		if(x){
			zz=1;
		}else{
			zz=0;
		}
	}else{
		zz=1;
	}
}
</script>


	</head>
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<body bgcolor="#FFFFFF" text="#000000" onload="return loads();"
		onContextmenu="return false">
		<jsp:include flush="true" page="/waiting.inc" />
		<form method="post">
			<jsp:include page="../../../inc/sort.jsp">
				<jsp:param name="url" value="showClearAccountAC.do" />
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
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="112" height="37"
												background="<%=path%>/img/buttonbl.gif" align="center"
												valign="top" style="PADDING-top: 7px">
												扎账
											</td>
											<!-- <td width="112" height="37" background="<%=path%>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="clearAccountTaBalanceForwardURLAC.do" class=a2>结算单查询</a></td>-->
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
											<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
												valign="bottom">
												<font color="00B5DB">账务处理&gt;扎账</font>
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

						<html:form action="/findClearAccountAC" styleClass="margin: 0">
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="117">
													<b class="font14">查 询 条 件</b>
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
								cellpadding=0 align="center">
								<tr>
									<td class="td1" width="17%">
										归集银行
									</td>
									<td width="33%">
										<html:select property="bank1" styleClass="input4">
											<html:option value=""></html:option>
											<html:options collection="bankList1" property="value"
												labelProperty="label" />
										</html:select>
									</td>
									<td class="td1" width="17%">
										单位编号
									</td>
									<td width="33%">
										<html:text name="clearAccountShowAF" property="orgId"
											styleClass="input3" styleId="txtsearch" />
									</td>
								</tr>
								<tr>
									<td class="td1" width="17%">
										单位名称
									</td>
									<td width="33%">
										<html:text name="clearAccountShowAF" property="orgName"
											styleClass="input3" styleId="txtsearch" />
									</td>
									<td class="td1" width="17%">
										操作员
									</td>
									<td width="33%">
										<html:select property="operator1" styleClass="input4">
											<html:option value=""></html:option>
											<html:options collection="operList1" property="value"
												labelProperty="label" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td class="td1" width="17%">
										业务类型
									</td>
									<td width="33%">
										<html:select property="bis_type1" styleClass="input4">
											<html:option value=""></html:option>
											<html:optionsCollection property="bis_type"
												name="clearAccountShowAF" label="value" value="key" />
										</html:select>
									</td>
									<td class="td1" width="17%">
										业务状态
									</td>
									<td width="33%">
										<html:select property="bis_Status1" styleClass="input4">
											<html:option value=""></html:option>
											<html:optionsCollection property="bis_Status"
												name="clearAccountShowAF" label="value" value="key" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td class="td1" width="17%">
										凭证编号
									</td>
									<td width="33%">
										<html:text name="clearAccountShowAF" property="docNum"
											styleClass="input3" styleId="txtsearch" />
									</td>
									<td width="17%" class="td1">
										结算号
									</td>
									<td width="33%">
										<html:text name="clearAccountShowAF" property="noteNum"
											styleClass="input3" styleId="txtsearch" />
									</td>
								</tr>
							</table>
							<table width="95%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td align="right">
										<html:submit property="method" styleClass="buttona">
											<bean:message key="button.search" />
										</html:submit>
									</td>
								</tr>
							</table>
						</html:form>

						<html:form action="/maintainClearAccountAC" styleClass="margin: 0">

							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td class=h4>
										合计：发生人数
										<u>：<bean:write name="clearAccountShowAF"
												property="totalPeople" /> </u> 借方发生额
										<u>：<bean:write name="clearAccountShowAF" property="debit" />
										</u> 贷方发生额
										<u>：<bean:write name="clearAccountShowAF"
												property="credit" /> </u> 利息
										<u>：<bean:write name="clearAccountShowAF"
												property="totalInterest" /> </u>
									</td>
								</tr>
							</table>
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="117">
													<b class="font14">业 务 列 表</b>
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
							<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
								cellpadding="3" align="center">
								<tr>
									<td align="center" height="23" bgcolor="C4F0FF" class=td2></td>
									<td align="center" class=td2>
										归集银行
									</td>
									<td align="center" class=td2>
										<a href="javascript:sort('orgHAFAccountFlow.noteNum')">结算号</a>
										<logic:equal name="pagination" property="orderBy"
											value="orgHAFAccountFlow.noteNum">
											<logic:equal name="pagination" property="orderother"
												value="ASC">▲</logic:equal>
											<logic:equal name="pagination" property="orderother"
												value="DESC">▼</logic:equal>
										</logic:equal>
									</td>
									<td align="center" class=td2>
										<a href="javascript:sort('orgHAFAccountFlow.docNum')">凭证编号</a>
										<logic:equal name="pagination" property="orderBy"
											value="orgHAFAccountFlow.docNum">
											<logic:equal name="pagination" property="orderother"
												value="ASC">▲</logic:equal>
											<logic:equal name="pagination" property="orderother"
												value="DESC">▼</logic:equal>
										</logic:equal>
									</td>
									<td align="center" class=td2>
										<a href="javascript:sort('orgHAFAccountFlow.org.id')">单位编号</a>
										<logic:equal name="pagination" property="orderBy"
											value="orgHAFAccountFlow.org.id">
											<logic:equal name="pagination" property="orderother"
												value="ASC">▲</logic:equal>
											<logic:equal name="pagination" property="orderother"
												value="DESC">▼</logic:equal>
										</logic:equal>
									</td>
									<td align="center" class=td2>
										单位名称
									</td>
									<td align="center" class=td2>
										业务类型
									</td>
									<td align="center" class=td2>
										发生人数
									</td>
									<td align="center" class=td2>
										<a href="javascript:sort('orgHAFAccountFlow.moneyTotal')">发生金额</a>
										<logic:equal name="pagination" property="orderBy"
											value="orgHAFAccountFlow.moneyTotal">
											<logic:equal name="pagination" property="orderother"
												value="ASC">▲</logic:equal>
											<logic:equal name="pagination" property="orderother"
												value="DESC">▼</logic:equal>
										</logic:equal>
									</td>
									<td align="center" class=td2>
										发生方向
									</td>
									<td align="center" class=td2>
										利息
									</td>
									<td align="center" class=td2>
										业务状态
									</td>
									<td align="center" class=td2>
										业务日期
									</td>
								</tr>
								<logic:notEmpty name="clearAccountShowAF" property="list">
									<%
												int j = 0;
												String strClass = "";
									%>
									<logic:iterate name="clearAccountShowAF" property="list"
										id="elements" indexId="i">
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
													<bean:write name="elements" property="id" />
												</html:multibox>
											</td>


											<td valign="top">
												<p>
													<bean:write name="elements"
														property="org.orgInfo.temp_collectionBankname" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="noteNum" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="docNum" />
												</p>
											</td>
											<td valign="top">
												<p>
													
													<bean:write name="elements" property="org.id"
														format="0000000000" />
												</p>
											</td>
											<td valign="top">
												<p>
													<a href="#"
														onclick="window.open('clearaccountForwardURLAC.do?headid=<bean:write name="elements" property="id" />','window','height=450,width=700,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,resizable=yes,location=no, status=no');"><bean:write
															name="elements" property="org.orgInfo.name" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="bizType" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="personTotal" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="moneyTotal" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="setdirection" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="interestTotal" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="status" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="settDate" />
												</p>
											</td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="clearAccountShowAF" property="list">
									<tr>
										<td colspan="15" height="30" style="color:red">
											没有找到与条件相符合结果！
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
														<jsp:param name="url" value="showClearAccountAC.do" />
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
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<logic:equal name="clearAccountShowAF" property="type"
													value="1">
													<td>
														<html:submit property="method" styleClass="buttona"
															onclick="return check(this); ">
															<bean:message key="button.in.account" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona"
															onclick="return check_date(this);">
															<bean:message key="button.in.accountall" />
														</html:submit>
													</td>
												</logic:equal>
												<logic:equal name="clearAccountShowAF" property="type"
													value="2">
													<td>
														<html:submit property="method" styleClass="buttona"
															disabled="true">
															<bean:message key="button.in.account" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona"
															disabled="true">
															<bean:message key="button.in.accountall" />
														</html:submit>
													</td>
												</logic:equal>
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
