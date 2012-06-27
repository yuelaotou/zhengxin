<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.pickupmng.pickupwh.action.PickupWHShowAC"%>
<!DOCTYPE HTML  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
			Object pagination = request.getSession(false).getAttribute(
			PickupWHShowAC.VINDICATE);
	String queryOrCheck = (String) request.getSession().getAttribute(
			"queryOrCheckPickUpWH");
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String type = (String) request.getAttribute("type");
%>
<script type="text/javascript">
var oldColor="#ffffff";
var newColor="#E8FCFD";
var s1="";
var s2="";
var tr="tr0";
var type="<%=type%>";
var queryOrCheck="<%=queryOrCheck%>";
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;
	oTD.style.backgroundColor=newColor;
	newColor=oldColor;
}
function gettr(trindex) {
  tr=trindex;
  if(queryOrCheck=="check"){
  	update();
  }
}
function loads1(){
	if(queryOrCheck=="check"){
		for(i=0;i<document.all.length;i++){
			if(document.all.item(i).type=="submit"){
				if(document.all.item(i).value=="审核通过"){
					s1=i;
				}
				if(document.all.item(i).value=="撤消审核"){
					s2=i;
				}
			}
		}
		var id = document.vindicateListAF.elements["dto.orgId"].value;
		if(id != ""){
		    document.vindicateListAF.elements["dto.orgId"].value=format(id)+id;
		}
		var pass_yg;
		<logic:notEmpty name="info" property="list">
			pass_yg = document.getElementById(tr).cells[11].innerHTML;
		</logic:notEmpty>
		var initiaButton = document.getElementById("buttonState").value;
		if(initiaButton == '1'){
			document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
		}else if(initiaButton == '3'){
			if(pass_yg=='审核通过'){
	  			document.all.item(s1).disabled="true";
				document.all.item(s2).disabled="";
	  		}else if(pass_yg=='审核未通过'){
	  			document.all.item(s1).disabled="";
				document.all.item(s2).disabled="true";
	  		}
		}
		else if(initiaButton == 'other'){
			document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
		}
		if(initiaButton==null || initiaButton==""){
		  	document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
	  	}else{
			update();
	  	}
  	}
}
function update() {
	if(type==1){
		var childValue=document.getElementById(tr).cells[10].innerHTML;
		var pass_yg=document.getElementById(tr).cells[11].innerHTML;
	  	if(childValue=="录入清册"){
		  	document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
	  	}if(childValue=='确认'){
	  		if(pass_yg=='审核通过'){
	  			document.all.item(s1).disabled="true";
				document.all.item(s2).disabled="";
	  		}else if(pass_yg=='审核未通过'){
	  			document.all.item(s1).disabled="";
				document.all.item(s2).disabled="true";
	  		}
	  	}if(childValue=='登记'||childValue=='复核'||childValue=='入账'){
		  	document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
	  	}
	}else{
		var childValue=document.getElementById(tr).cells[10].innerHTML;
		var pass_yg=document.getElementById(tr).cells[11].innerHTML.trim();
	  	if(childValue.trim()=="录入清册"){
		  	document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
	  	}if(childValue.trim()=='确认'){
		  	if(pass_yg=='审核通过'){
	  			document.all.item(s1).disabled="true";
				document.all.item(s2).disabled="";
	  		}else if(pass_yg=='审核未通过'){
	  			document.all.item(s1).disabled="";
				document.all.item(s2).disabled="true";
	  		}
	  	}if(childValue.trim()=='复核'||childValue.trim()=='入账'){
		  	document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
	  	}
  	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function check(form){
	var orgId = form.elements["dto.orgId"].value;
	var start = form.elements["dto.start"].value;
	var data = form.elements["dto.pickDate"].value;
	var end = form.elements["dto.end"].value;
	if(orgId!=""){
		if(isNaN(orgId)){alert("请你输入正确的单位编号");return false;}
		if(orgId.indexOf(".")!=-1){alert("单位编号不能有小数点");return false;}
		if(orgId<=0){alert("单位编号必须是正整数");return false;}
	}
	if(start!=""){
		if(isNaN(start)){alert("请你输入正确的查找金额");return false;}
		if(start<=0){alert("金额必须是大于0的数字");return false;}
	}
	if(end!=""){//这个地方必须用这样的方法来取值
		if(isNaN(end)){alert("请你输入正确的查找金额");return false;}
		if(end<=0){alert("金额必须是大于0的数字");return false;}
	}
	if(start!="" && end!=""){	
		if(parseInt(start)>parseInt(end)){alert("金额错误\n第一个金额必须大于第二个金额");return false;}
	}
	if(start!="" && end==""){alert("查询金额的时候请你输入两个金额");return false;}
	if(start=="" && end!=""){alert("查询金额的时候请你输入两个金额");return false;}
	if(data!=""){
		if(data.length<8 || data.length>8){alert("提取日期必须是8位数字\n日期格式为YYYYMMDD\n如:20050521");return false;}
		if(isNaN(data)){alert("请你输入正确的提取日期\n日期格式为YYYYMMDD\n如:20050521");return false;}
		if(data.indexOf(".")!=-1){alert("提取日期不能有小数点\n日期格式为YYYYMMDD\n如:20050521");return false;}
	}
	return true;
}
</script>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
<html:html lang="true">
<head>
	<html:base />
	<title>提取管理_维护列表</title>
</head>
<body bgcolor="#FFFFFF" text="#000000"
	onload="loads1();return reportErrors();">
	<jsp:include page="/inc/sort.jsp">
		<jsp:param name="url" value="pickupWHShowAC.do" />
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
										style="PADDING-top: 7px" valign="top">
										<logic:equal name="queryOrCheckPickUpWH" value="check" >
											提取审核
										</logic:equal>
										<logic:equal name="queryOrCheckPickUpWH" value="query" >
											提取查询
										</logic:equal>
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
									<td width="190" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">
											<logic:equal name="queryOrCheckPickUpWH" value="check" >
												提取管理&gt;提取审核
											</logic:equal>
											<logic:equal name="queryOrCheckPickUpWH" value="query" >
												提取查询&gt;提取业务查询
											</logic:equal>
										</font>
									</td>
									<td width="73" class=font14>
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
				<html:form action="/pickupWHShowAC.do" onsubmit="return check(this)">
					<html:hidden property="search" value="jiancha" />
					<html:hidden name="info" property="buttonState" />
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
							<td width="15%" class="td1">
								单位编号
							</td>
							<td colspan="3">
								<html:text property="dto.orgId" name="info" styleClass="input3"></html:text>
							</td>
							<td width="14%" class="td1">
								单位名称
							</td>
							<td colspan="3">
								<html:text property="dto.orgName" name="info"
									styleClass="input3"></html:text>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								结算号
							</td>
							<td colspan="3">
								<html:text property="dto.noteNumber" name="info"
									styleClass="input3"></html:text>
							</td>
							<td width="14%" class="td1">
								凭证编号
							</td>
							<td colspan="3">
								<html:text property="dto.docNumber" name="info"
									styleClass="input3"></html:text>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								提取金额
							</td>
							<td width="14%">
								<html:text property="dto.start" name="info" styleClass="input3"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="14%">
								<html:text property="dto.end" name="info" styleClass="input3"></html:text>
							</td>
							<td width="14%" class="td1">
								提取日期
							</td>
							<td width="14%">
								<html:text property="dto.pickDate" name="info"
									styleClass="input3"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="14%">
								<html:text property="dto.pickDate_end" name="info"
									styleClass="input3"></html:text>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								业务状态
							</td>
							<td colspan="3">
								<html:select style="input4" property="dto.state"
									styleClass="input4" name="info">
									<option></option>
									<html:optionsCollection property="busiState" name="info"
										label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								归集银行
							</td>
							<td align="center" colspan="3">
								<html:select property="dto.collectionBank" styleClass="input4"
									name="info">
									<html:option value=""></html:option>
									<html:options collection="bankList1" property="value"
										labelProperty="label" />
								</html:select>

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
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td class=h4>
							总计：提取人数
							<u>: <bean:write name="info" property="sumPerson" /> </u> 提取金额
							<u>: <bean:write name="info" property="sumBalance"
									format="0.00" /> </u>
							<security:orgcannot> 销户利息
								<u>: <bean:write name="info" property="sumInterest"
										format="0.00" /> </u>
							</security:orgcannot>
							<security:orgcannot>提取总额
								<u>: <bean:write name="info" property="sumTotal"
										format="0.00" /> </u>
								<br>
							</security:orgcannot>

							公积金按月还贷提取人数
							<u>: <bean:write name="info" property="countmon" /> </u> 金额
							<u>: <bean:write name="info" property="moneymon"
									format="0.00" /> </u>
							<br>
							公积金一次性还贷款人数
							<u>: <bean:write name="info" property="countcls" format="0" />
							</u> 金额
							<u>: <bean:write name="info" property="moneycls"
									format="0.00" /> </u>
							<br>
							其他部分提取人数
							<u>: <bean:write name="info" property="countrest" format="0" />
							</u> 金额
							<u>: <bean:write name="info" property="moneyrest"
									format="0.00" /> </u>
							<br>
							销户提取人数
							<u>: <bean:write name="info" property="count_xh" format="0" />
							</u> 金额
							<u>: <bean:write name="info" property="sum_xh" format="0.00" />
							</u>

						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="115">
										<b class="font14">
										<logic:equal name="queryOrCheckPickUpWH" value="check" >
												办理提取列表
											</logic:equal>
											<logic:equal name="queryOrCheckPickUpWH" value="query" >
												提取业务列表
											</logic:equal>
										</b>
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
				<html:form action="/pickupWHMiantainAC.do">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td width="2%" height="23" align="center" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td width="8%" align="center" class=td2>
								<p>
									<a href="javascript:sort('p.noteNum')">结算号</a>
									<logic:equal name="pagination" property="orderBy"
										value="p.noteNum">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</p>
							</td>
							<td width="7%" align="center" class=td2>
								<a href="javascript:sort('p.docNum')">凭证编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="p.docNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td width="7%" align="center" class=td2>
								<a href="javascript:sort('p.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="p.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td width="14%" align="center" class=td2>
								<a href="javascript:sort('p.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="p.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td width="4%" align="center" class=td2>
								提取人数
							</td>
							<td width="8%" align="center" class=td2>
								提取金额
							</td>
							<security:orgcannot>
								<td width="9%" align="center" class=td2>
									销户利息
								</td>
								<td width="10%" align="center" class=td2>
									提取总额
								</td>
							</security:orgcannot>
							<td width="10%" align="center" class=td2>
								提取日期
							</td>
							<td width="6%" align="center" class=td2>
								业务状态
							</td>
							<td width="8%" align="center" class=td2>
								审核状态
							</td>
							<td width="5%" align="center" class=td2>
								制单人
							</td>
							<security:orgcan>
								<td width="10%" align="center" class=td2>
									提取状态
								</td>
							</security:orgcan>
						</tr>
						<input type="hidden" name="report" />
						<html:hidden property="buttonState" name="info" />
						<logic:notEmpty name="info" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="info" property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>",idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="e" property="id"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td valign="top">
										<bean:write name="e" property="noteNum" />
									</td>
									<td valign="top">
										<bean:write name="e" property="docNum" />
									</td>
									<td valign="top">

										<bean:write name="e" property="org.id" format="0000000000" />
									</td>
									<td valign="top">
										<a href="#"
											onclick="window.open('wehuwindowshowAC.do?id=<bean:write name="e" property="id"/>&noteNumber=<bean:write name="e" property="noteNum" />&docNumber=<bean:write name="e" property="docNum" />','','width=750,height=550,scrollbars=yes');return false;">
											<bean:write name="e" property="org.orgInfo.name" /> </a>
									</td>
									<td valign="top">
										<bean:write name="e" property="pickPersonCount" />
									</td>
									<td valign="top">
										<bean:write name="e" property="pickBalance" format="0.00" />
									</td>
									<security:orgcannot>
										<td valign="top">
											<bean:write name="e" property="distroyInterest" format="0.00" />
										</td>
										<td valign="top">
											<bean:write name="e" property="pickMoneyCount" format="0.00" />
										</td>
									</security:orgcannot>
									<td valign="top">
										<bean:write name="e" property="settDate" />
									</td>
									<td valign="top">
										<bean:write name="e" property="businessState" />
									</td>
									<td valign="top" align="center">
										<bean:write name="e" property="reserveaA" />
									</td>
									<td valign="top" align="center">
										<bean:write name="e" property="reserveaB" />
									</td>
									<security:orgcan>
										<td valign="top">
											<bean:write name="e" property="temp_pick" />
										</td>
									</security:orgcan>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="info" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果!
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
										</td>
										<td align="right">
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="pickupWHShowAC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<logic:equal name="queryOrCheckPickUpWH" value="check" >
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.auditing.pass" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.auditing.quash" />
												</html:submit>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</logic:equal>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
