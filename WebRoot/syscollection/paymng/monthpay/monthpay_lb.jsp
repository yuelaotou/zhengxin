<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.monthpay.action.MonthpayTbShowAC"%>
<%@ include file="/checkUrl.jsp"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			MonthpayTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>缴存管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>

</head>
<script type="text/javascript">
function gotoDelete(){
	if(!confirm("确认要删除该条汇缴记录吗？")){
		return false;
	}
}
var s1="";
var s2="";
var s3="";
var s8="";
var s9="";
var tr="tr0"; 
function gettr(trindex) {
  tr=trindex;
  update1();
}

function update1() {
  	var status=document.getElementById(tr).childNodes[11].childNodes[0].innerHTML.trim();
  	if(status=='登记'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
  	}else if(status=='录入清册'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
  	}else if(status=='确认'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
  	}else if(status=='复核'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
  	}else if(status=='入账'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
  	}
}
function loads(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
		if(document.all.item(i).value=="查询"){
				s8=i;
			}
			if(document.all.item(i).value=="删除"){
				s1=i;
			}
			if(document.all.item(i).value=="套打凭证"){
				s2=i;
			}
			if(document.all.item(i).value=="打印列表"){
				s3=i;
			}
			if(document.all.item(i).value=="修改账号"){
				s9=i;
			}
		}
	}
    var list = document.all.listCount.value;
    if(list!=1){
    	
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s9).disabled="true";
    }else{
		update1();
    }
}
function gotoShow(){
	return false;
}
function gotoQuery(){
	var money1=document.all.inceptPayMoney.value;
	var money2=document.all.payMoney.value;

	if(money1 != ""){
		var str1=checkMoney(money1);
		if(!str1){
			document.all.inceptPayMoney.value="";
			document.all.inceptPayMoney.focus();
			return false;
		}
	}
	if(money2 != ""){
		var str2=checkMoney(money2);
		if(!str2){
			document.all.payMoney.value="";
			document.all.payMoney.focus();
			return false;
		}
	}
	
}
 function keyvalue()
{

   if(event.keyCode==13)
  {
     //event.keyCode=0;
      document.all.item(s8).focus();
     //document.getElementById("buttona").focus();
    return false;
 }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	>
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="monthpayTbShowAC.do" />
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
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="monthpayTaForwardUrlAC.do" class=a2>办理缴存</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										缴存维护
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
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">缴存管理<font color="00B5DB">&gt;办理缴存</font>
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
				<html:form action="/monthpayTbFindAC" style="margin: 0" focus="id">
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
							<td width="13%" class="td1">
								单位编号
							</td>
							<td colspan="3">
								<html:text property="id" styleClass="input3" styleId="txtsearch"
									onkeydown="return keyvalue();" />
							</td>
							<td width="14%" class="td1">
								单位名称
							</td>
							<td colspan="3">
								<html:text property="name" styleClass="input3"
									styleId="txtsearch" maxlength="50"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								缴存年月
							</td>
							<td>
								<html:text property="inceptMonth" styleClass="input3"
									styleId="txtsearch" maxlength="6"
									onkeydown="enterNextFocus1();" />
							</td>
							<td>
								至
							</td>
							<td>
								<html:text property="payMonth" styleClass="input3"
									styleId="txtsearch" maxlength="6"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="14%" class="td1">
								&nbsp;
								<input type="hidden" name="listCount"
									value="<bean:write name="monthpayTbFindAF" property="listCount"/>">
							</td>
							<td colspan="3">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								结算方式
							</td>
							<td colspan="3">
								<select name="status" class="input4"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<option value="1">
										均缴
									</option>
									<option value="2">
										只缴单位
									</option>
									<option value="3">
										只缴个人
									</option>
								</select>
							</td>
							<td width="14%" class="td1">
								业务状态
							</td>
							<td colspan="3">
								<html:select property="payType" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="payTypeMap"
										name="monthpayTbFindAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								缴存金额
							</td>
							<td width="15%">
								<html:text property="inceptPayMoney" styleClass="input3"
									styleId="txtsearch" maxlength="18"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text property="payMoney" styleClass="input3"
									styleId="txtsearch" maxlength="18"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="14%" class="td1">
								登记日期
							</td>
							<td width="15%">
								<html:text property="inceptSettlementDate" styleClass="input3"
									styleId="txtsearch" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text property="settlementDate" styleClass="input3"
									styleId="txtsearch" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								缴存日期
							</td>
							<td width="15%">
								<html:text property="settDate" styleClass="input3"
									styleId="txtsearch" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text property="settDate1" styleClass="input3"
									styleId="txtsearch" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="14%" class="td1">
								归集银行
							</td>
							<td width="15%" colspan="3">
								<html:select property="collBankId" styleClass="input4">
									<html:option value=""></html:option>
									<html:options collection="collBankList1" property="value"
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
									onclick="return gotoQuery();">
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
							合计：缴存金额
							<u>：<bean:write name="monthpayTbFindAF" property="totalmoney" />
							</u>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" height="22" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">正常汇缴列表 </b>
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

				<html:form action="/monthpayTbMaintainAC" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('monthPayment.noteNum')">结算号</a>
								<logic:equal name="pagination" property="orderBy"
									value="monthPayment.noteNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('monthPayment.docNum')">凭证编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="monthPayment.docNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('monthPayment.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="monthPayment.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('monthPayment.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="monthPayment.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('monthPayment.minMaxMonth')">缴存年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="monthPayment.minMaxMonth">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td algin="center" class=td2>
								缴存人数
							</td>
							<td align="center" class=td2>
								缴存金额
							</td>
							<td align="center" class=td2>
								结算方式
							</td>
							<td align="center" class=td2>
								<p>
									登记日期
								</p>
							</td>
							<td align="center" class=td2>
								<p>
									缴存日期
								</p>
							</td>
							<td align="center" class=td2>
								业务状态
							</td>
						</tr>
						<logic:notEmpty name="paymentList">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="paymentList" id="element" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);gettr("tr<%=i %>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="noteNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="docNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="orgId"
												format="0000000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<a href="#"
												onClick="window.open('monthpayTbWindowShowAC.do?paymentid=<bean:write name="element" property="id"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return gotoShow();"><bean:write
													name="element" property="orgName" /> </a>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="payMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="payCount" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="pay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="payMode" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="opTime" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="settDate" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="payStatus" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="paymentList">
							<tr>
								<td colspan="12" height="30" style="color:red">
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
												<jsp:param name="url" value="monthpayTbShowAC.do" />
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
								<table border="0" cellpadding="0" cellspacing="0" width="280">
									<tr>
										<td width="65">
											<html:submit property="method" styleClass="buttona"
												onclick="return gotoDelete();">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.printDoc" />
											</html:submit>
										</td>
										<td width="80" align="right">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.printList" />
											</html:submit>
										</td>
										<td width="80" align="right">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.reupdate" />
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
