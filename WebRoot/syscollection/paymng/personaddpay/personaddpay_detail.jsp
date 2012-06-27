<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonaddpayTbWindowShowAC"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			PersonaddpayTbWindowShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>个人补缴</title>
	<link rel="stylesheet" href="../../../css/index.css" type="text/css">
	<script language="javascript" src="../../../js/common.js"></script>

	<script language="javascript" type="text/javascript">





function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
  
function executeAjax() {

doRequestUsingGET();
}

//

var xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function doRequestUsingGET() {
	
 	createXMLHttpRequest();
 
    var queryString = "<%=path%>/syscollection/paymng/personaddpay/personAddPayTaFindInforAAC.do?";
    var orgId = document.getElementById("orgId").value.trim();   
    queryString = queryString + "orgId="+orgId; 
    if(orgId!=""){
	    xmlHttp.onreadystatechange = handleStateChange;
	    xmlHttp.open("GET", queryString, true);
	    xmlHttp.send(null);   
    }
}

function handleStateChange() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {
         var x=xmlHttp.responseText;
         eval(x);
      }
   }
}
function showlist() {
  document.Form1.submit();
}

function displays(unitName,docNumber){
  
  document.getElementById("unitName").value=unitName;
  document.getElementById("docNumber").value=docNumber;

  showlist() 
}

function gotoSelect(){

gotoOrgpop("" ,"/hafmis");
}

function empAddPay(){
document.getElementById("types").value="add";

}

function empAddPayUpdate(){
document.getElementById("types").value="update";

}
function loads(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	//var count=document.all.list.value;
	var orgid=document.all.orgId.value;
	if(orgid != ""){
		document.all.orgId.value=formatTen(orgid)+orgid;
	}
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="批量导出"){
				s1=i;
			}
			if(document.all.item(i).value=="批量导入"){
				s2=i;
			}
			if(document.all.item(i).value=="删除"){
				s3=i;
			}
			if(document.all.item(i).value=="全部删除"){
				s4=i;
			}
			if(document.all.item(i).value=="完成补缴登记"){
				s5=i;
			}
		    if(document.all.item(i).value=="添加"){
				s6=i;
			}
		    if(document.all.item(i).value=="修改"){
				s7=i;
			}
		}
	}
	if(orgid==""){
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		document.all.item(s7).disabled="true";
	}
	/*if(count==1){
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
	}else{
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s7).disabled="true";
	}*/
	
}
function deleteConfirm(){
if(confirm("是否删除记录?")){
return true;
}
else{
return false;
}
}

function addpayConfirm(){
if(confirm("确认要进行该次补缴登记吗?")){
if(confirm("是否打印")){
document.getElementById("printReport").value="yes";
alert(document.getElementById("printReport").value);
}
else{
document.getElementById("printReport").value="no";
alert(document.getElementById("printReport").value);
}
return true;
}
else{
return false;
}

}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="personaddpayTbWindowShowAC.do" />
	</jsp:include>
	<input type="hidden" name="types" id="types" value="" />
	<input type="hidden" name="printReport" id="printReport" value="" />
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="../../../img/table_left.gif" width="7" height="37">
						</td>
						<td background="../../../img/table_bg_line.gif" width="55">
							&nbsp;
						</td>
						<td width="235" background="../../../img/table_bg_line.gif">
							<a
								href="<%=path%>/syscollection/paymng/personaddpay/personAddPayTbShowAC.do"></a>
						</td>
						<td background="../../../img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="../../../img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">缴存管理&gt;个人补缴</font>
									</td>
									<td width="74" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="../../../img/table_right.gif" width="9" height="37">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">缴存信息</b>
									</td>
									<td width="750" height="22" align="center"
										background="../../../img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/personAddPayTaShowAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1" algin="center">
								单位编号
							</td>
							<td width="22%">
								<html:text name="personAddPayMaintainAF" property="orgId"
									styleClass="input3" readonly="true">
								</html:text>
							<td class="td1" width="17%" algin="center">
								单位名称
							</td>
							<td width="22%">
								<html:text name="personAddPayMaintainAF" property="unitName"
									styleClass="input3" styleId="unitName" readonly="true"></html:text>
							</td>
						</tr>

						<tr>
							<td width="17%" class="td1" algin="center">
								结算号
							</td>
							<td width="22%">
								<html:text name="personAddPayMaintainAF" property="noteNum"
									styleClass="input3" onkeydown="goEnter();" styleId="docNumber"
									readonly="true">
								</html:text>
							</td>
							<td width="17%" class="td1" algin="center">
								凭证编号
							</td>
							<td width="22%">
								<html:text name="personAddPayMaintainAF" property="docNumber"
									styleClass="input3" onkeydown="goEnter();" styleId="docNumber"
									readonly="true">
								</html:text>
							</td>
						</tr>

					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								总计： 补缴人数
								<u>：<bean:write name="pagination" property="nrOfElements" />
								</u> 单位补缴金额
								<u>：<bean:write name="personAddPayMaintainAF"
										property="orgPaySum" /> </u> 个人补缴金额
								<u>：<bean:write name="personAddPayMaintainAF"
										property="empPaySum" />
								</u> 补缴金额
								<u>：<bean:write name="personAddPayMaintainAF"
										property="paySum" /> </u>
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
										<b class="font14">个人补缴列表</b>
									</td>
									<td width="750" height="22" align="center"
										background="../../../img/bg2.gif">
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
				<html:form action="/personaddpayTbMXPrintAC.do" style="margin: 0">

					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF"></td>

							<td align="center" class=td2>
								<a href="javascript:sort('addPayTail.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="addPayTail.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('addPayTail.empName')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="addPayTail.empName">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								单位补缴金额
							</td>
							<td align="center" class=td2>
								个人补缴金额
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('addPayTail.addPaySum')">补缴金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="addPayTail.addPaySum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								补缴起始年月
							</td>
							<td align="center" class=td2>
								补缴终止年月
							</td>
							<td align="center" class=td2>
								补缴原因
							</td>
						</tr>
						<logic:notEmpty name="empaddpayMaintainDTO" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="empaddpayMaintainDTO" property="list"
								id="element" indexId="i">
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
											<bean:write name="element" property="personId" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="orgAddPaySum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empAddPaySum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="addPaySum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="beginMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="endMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="addReason" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="empaddpayMaintainDTO" property="list">
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
												<jsp:param name="url" value="personaddpayTbWindowShowAC.do" />
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

										<logic:notEmpty name="empaddpayMaintainDTO" property="list">
											
<%--											<td width="70" >--%>
<%--												<html:submit property="method" styleClass="buttona">--%>
<%--													<bean:message key="button.print" />--%>
<%--												</html:submit>--%>
<%--											</td>--%>
											<td width="70" align="right">
												<input type="button" name="Submit42" value="关闭"
													class="buttona" onClick="javascript:window.close();">
											</td>
										</logic:notEmpty>
										<logic:empty name="empaddpayMaintainDTO" property="list">
											
<%--											<td width="70" >--%>
<%--												<html:submit property="method" styleClass="buttona"--%>
<%--													disabled="true">--%>
<%--													<bean:message key="button.print" />--%>
<%--												</html:submit>--%>
<%--											</td>--%>
											<td width="70" align="right">
												<input type="button" name="Submit42" value="关闭"
													class="buttona" onClick="javascript:window.close();">
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
</html:html>
