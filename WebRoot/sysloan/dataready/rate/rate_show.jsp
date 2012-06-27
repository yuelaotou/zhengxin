<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.dataready.rate.action.RateTaShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			RateTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html lang="true">
<head>
	<title>个贷管理</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	
	
	<script language="javascript" src="js/common.js">
</head>
<script language="javascript" ></script>
	<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var tr="tr0"; 
function loads(){

	document.rateShowAF.officecode.value="";
	document.rateShowAF.usetime.value="";
	document.rateShowAF.ratetype.value="";
	
var count = "<bean:write name="pagination" property="nrOfElements"/>";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="添加"){
				s1=i;
			}
			if(document.all.item(i).value=="修改"){
				s2=i;
			}
			if(document.all.item(i).value=="启用"){
				s3=i;
			}
			if(document.all.item(i).value=="全部启用"){
				s4=i;
			}
		}
	}
	document.all.item(s1).disabled="";
	document.all.item(s2).disabled="true";
	document.all.item(s3).disabled="true";
	document.all.item(s4).disabled="true";
	
if(count!=0){
		update();
	}
}
function gettr(trindex) {
  tr=trindex;
  update();
}
function update() {	
  var status=document.getElementById(tr).childNodes[6].childNodes[0].innerHTML.trim();
     if(status=='启用'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
  	}else if(status=='未启用'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
  	}
}
//function checkUse(){
//if(confirm("是否启用？")){
  //  var status=getCheckId();
    //window.open ('<%=path%>/sysloan/dataready/rate/rate_appuse.jsp?rateid='+status,'newwindow','height=100,width=400,top=400,left=400,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	//return false;
	//}else{
	//	return false;
	//}
//}
function checkUseAll(){
if(confirm("是否启用？")){
   window.open ('<%=path%>/sysloan/dataready/rate/rate_appuse.jsp?rateid=all','newwindow','height=100,width=400,top=400,left=400,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	return false;
	}else{
		return false;
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	if(message=='pass'){
	   if(confirm("是否启用？")){
       var status="<%=request.getSession().getAttribute("rateId")%>";
      //var status=getCheckId();
       window.open ('<%=path%>/sysloan/dataready/rate/rate_appuse.jsp?rateid='+status,'newwindow','height=100,width=400,top=400,left=400,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	   <%request.getSession().setAttribute("rateId",null);%>
	}
	}else{
	alert(message);
	}
	</logic:messagesPresent>
}
function search(){
	var temp_usetime=document.rateShowAF.usetime.value;
	var usetime=document.rateShowAF.usetime;
	if(temp_usetime!=null&&temp_usetime!=""){
		if(usetime!=null&&usetime!=""){
			if(!checkDate(usetime)){
				document.rateShowAF.usetime.focus();
				return false;
			}
		}
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors();"
	onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="rateTaShowAC.do" />
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
						<td width="350" background="<%=path%>/img/table_bg_line.gif">
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">数据准备</font><font color="00B5DB">&gt;</font><font
											color="00B5DB">利率维护</font>
									</td>
									<td width="74" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=path%>/table_right.gif" width="9" height="37">
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
				<html:form action="/rateTaFindAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1" algin="center">
								办事处
							</td>
							<td width="22%">
								<html:select property="officecode" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="all">全部</html:option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td class="td1" width="17%" algin="center">
								启用时间
							</td>
							<td width="22%">
								<html:text name="rateShowAF" property="usetime"
									onkeydown="enterNextFocus1();" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								利率类型
							</td>
							<td width="22%">
								<html:select property="ratetype" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:options collection="rateTypeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td class="td1" width="17%" algin="center">
								调整依据
							</td>
							<td width="22%">
								<html:text name="rateShowAF" property="adjustBasis"
									onkeydown="enterNextFocus1();" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" algin="center"></td>
							<td width="22%">
							</td>
							<td width="17%" algin="center"></td>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return search()">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>

					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								<bean:write name="rateShowAF" property="latestDate" />
								之前已发放贷款的启用日期为
								<bean:write name="rateShowAF" property="latestDateNex" />
								,
								<bean:write name="rateShowAF" property="latestDate" />
								之后发放货款的启用日期为
								<bean:write name="rateShowAF" property="latestDate" />
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="180">
										<b class="font14">利率设置</b>
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
				<html:form action="/rateTaMaintainAC.do" style="margin: 0">
					<input type="hidden" name="appDate" value="" />
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('loanRate.office')">办事处</a>
								<logic:equal name="pagination" property="orderBy"
									value="loanRate.office">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('loanRate.loanRateType')">贷款类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="loanRate.loanRateType">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								年利率
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('loanRate.ajustDate')">调整日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="loanRate.ajustDate">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('loanRate.appDate')">启用日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="loanRate.appDate">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								启用状态
							</td>
							<td align="center" class=td2>
								调整依据
							</td>
						</tr>
						<logic:notEmpty name="rateShowAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="rateShowAF" property="list" id="element"
								indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
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
											<bean:write name="element" property="office" />
										</p>
									</td>
									<td valign="top">

										<bean:write name="element" property="loanRateType" />

									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanMonthRate" />
											%
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="ajustDate" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="appDate" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="status" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="adjustBasis" />
										</p>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="rateShowAF" property="list">
							<tr>
								<td colspan="4" height="30" style="color:red">
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
												<jsp:param name="url" value="rateTaShowAC.do" />
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
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.update" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkUse();">
												<bean:message key="button.use" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkUseAll();">
												<bean:message key="button.use.all" />
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
