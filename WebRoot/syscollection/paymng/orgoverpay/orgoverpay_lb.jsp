<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.orgoverpay.action.OrgoverpayTbShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			OrgoverpayTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>单位挂账</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
</head>
<script type="text/javascript">
var s1="";
var s2="";
var s3="";
var tr="tr0"; 
function gettr(trindex) {
  tr=trindex;
  update1();
}

function update1() {
var status=document.getElementById(tr).childNodes[8].childNodes[0].innerHTML.trim(); 
  	if(status=='登记'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
  	}else if(status=='录入清册'){   
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
  	}else if(status=='确认'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
  	}else if(status=='复核'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
  	}else if(status=='入账'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
  	}
}

function loads(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="修改"){
				s1=i;
			}
			if(document.all.item(i).value=="删除"){
				s2=i;
			}
			if(document.all.item(i).value=="打印"){
				s3=i;
			}

		}
	}
    var list = document.orgoverpayTbAF.listCount.value;
    if(list!=1){
    	
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
	
    }else{
		update1();
    }
}

function gotoDelAddpay(){
	if(!confirm("确认要进行该次补缴登记撤销吗？")){
		return false;
	}

}

function gotoShow(){
	return false;
}
function confirmDel(){
if(confirm("是否删除这条记录")){
  return true;
}
else{
return false;
}
}
function deleteConfirm()
{
if(confirm("是否删除这条记录")){
return true;
}else{
return false;
}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="loads();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="orgoverpayTbShowAC.do" />
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
										<a
											href="<%=path%>/syscollection/paymng/orgoverpay/orgoverpayTaForwardUrlAC.do"
											class=a2>办理挂账</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										挂账维护
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
									<td width="136" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">缴存管理&gt;单位挂账</font>
									</td>
									<td width="127" class=font14>
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

				<html:form action="/orgoverpayTbFindAC">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="15%" class="td1">
								单位编号：
							</td>
							<td colspan="3">
								<html:text name="orgoverpayTbAF" property="orgId"
									styleClass="input3" styleId="txtsearch" />
							</td>
							<td width="15%" class="td1">
								单位名称：
							</td>
							<td width="34%" colspan="3">
								<html:text name="orgoverpayTbAF" property="unitName"
									styleClass="input3" styleId="txtsearch" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								挂账金额：
							</td>
							<td colspan="3">
								<html:text name="orgoverpayTbAF" property="payMoney"
									styleClass="input3" styleId="txtsearch" />
							</td>
							<td width="15%" class="td1">
								业务状态
							</td>
							<td width="25%" align="center" colspan="3">
								<html:select name="orgoverpayTbAF" property="bizStatus"
									styleClass="input4" style="bizType">
									<html:option value=""></html:option>
									<html:optionsCollection property="payTypeMap"
										name="orgoverpayTbAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								挂账日期：
							</td>
							<td width="15%">
								<html:text name="orgoverpayTbAF" property="payMonth"
									styleClass="input3" styleId="txtsearch" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="orgoverpayTbAF" property="payMonth1"
									styleClass="input3" styleId="txtsearch" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="14%" class="td1">
								办理日期：
							</td>
							<td width="15%">
								<html:text name="orgoverpayTbAF" property="opTime"
									styleClass="input3" styleId="txtsearch" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="orgoverpayTbAF" property="opTime1"
									styleClass="input3" styleId="txtsearch" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
					</table>
					<table border="0" width="100%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr id="gjtr">
							<td colspan="11">
								<table border="0" width="96%" id="table1" cellspacing=1
									cellpadding=3 align="center">

								</table>
							</td>
						</tr>

						<tr>
							<td width="14%">
								<input type="hidden" name="listCount"
									value="<bean:write name="orgoverpayTbAF" property="listCount"/>">
							</td>
							<td width="29%">
								&nbsp;
							</td>
							<td width="18%">
								&nbsp;
							</td>
							<td width="22%">
								&nbsp;
							</td>
							<td width="17%">
								<html:submit property="method" styleClass="buttona">
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
							合计：挂账金额
							<u>：<bean:write name="orgoverpayTbAF" property="money" /> </u>
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
										<b class="font14">单位挂账列表</b>
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

				<html:form action="/orgoverpayMaintainAC.do" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="0" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF"></td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgExcessPayment.noteNum')">结算号</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgExcessPayment.noteNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgExcessPayment.docNum')">凭证编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgExcessPayment.docNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgExcessPayment.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgExcessPayment.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgExcessPayment.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgExcessPayment.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgExcessPayment.payMoney')">挂账金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgExcessPayment.payMoney">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								办理日期
							</td>
							<td align="center" class=td2>
								挂账日期
							</td>
							<td align="center" class=td2>
								业务状态
							</td>

						</tr>
						<logic:notEmpty name="orgoverpayList">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="orgoverpayList" id="element" indexId="i">
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
											<bean:write name="element" property="orgId" />
										</p>
									</td>
									<td valign="top">
										<p>
											<a href="#"
												onClick="window.open('orgoverpayTbWindowShowAC.do?paymentid=<bean:write name="element" property="id"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return gotoShow();"><bean:write
													name="element" property="orgName" />
											</a>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="payMoney" />
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
						<logic:empty name="orgoverpayList">
							<tr>
								<td colspan="12" height="30" style="color:red">
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
												<jsp:param name="url" value="orgoverpayTbShowAC.do" />
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

										<td width="65">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.update" />
											</html:submit>
											&nbsp;
										</td>
										<td width="65">
											<html:submit property="method" styleClass="buttona"
												onclick="return deleteConfirm();">
												<bean:message key="button.delete" />
											</html:submit>
											&nbsp;
										</td>
										<td width="65">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.print" />
											</html:submit>
											&nbsp;
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
