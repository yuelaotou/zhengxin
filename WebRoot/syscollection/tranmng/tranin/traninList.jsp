<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowTraninListAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ShowTraninListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>

<html:html>
<head>
	<title>tranin</title>
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
var s5="";
var s6="";
var s7="";
function loads(){
var status=document.traninIdAF.elements["loadsMassage"].value;
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="批量导入"){
				s1=i;
			}
			if(document.all.item(i).value=="批量导出"){
				s2=i;
			}
			if(document.all.item(i).value=="添加"){
				s3=i;
			}
			if(document.all.item(i).value=="修改"){
				s4=i;
			}
			if(document.all.item(i).value=="删除"){
				s5=i;
			}
			if(document.all.item(i).value=="全部删除"){
				s6=i;
			}
			if(document.all.item(i).value=="完成转入登记"){
				s7=i;
			}
		}
	}
	if(status=='hi'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="";
	    document.all.item(s6).disabled="";
	    document.all.item(s7).disabled="";
  	}else if(status=="nohi"){
  	    document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
  	    document.all.item(s5).disabled="true";
	    document.all.item(s6).disabled="true";
	    document.all.item(s7).disabled="true";
  		
  	}else if(status==''){
	document.all.item(s1).disabled="true";
	document.all.item(s2).disabled="true";
	document.all.item(s3).disabled="true";
	document.all.item(s4).disabled="true";
	document.all.item(s5).disabled="true";
	document.all.item(s6).disabled="true";
	document.all.item(s7).disabled="true";
	}
}
function gotoPrint(){
if(confirm("是否要打印？")){
		document.all.report.value="print";
	}else{
		document.all.report.value="noprint";
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
  
function toPop() {
	gotoOrgpop(2,'<%=path%>');
}
function executeAjax() {
var x = document.traninAF.elements["inOrgId"].value.trim();
	if(x==""){
		toPop();
	}
        var queryString = "traninFindInforAAC.do?";
        var inOrgId = document.traninAF.elements["inOrgId"].value.trim();
        queryString = queryString + "inOrgId="+inOrgId; 	     
	    findInfo(queryString);
}
function checkDetele(){
	var x=confirm("确定删除记录?");
		if(x){ 
		  	return true;
		}else{
		  return false;
		}
}

function checkDeteleAll(){
	var x=confirm("确定全部删除记录?");
		if(x){ 
		  	return true;	
		}else{
		  return false;
		}
}
function displays(inOrgId,inOrgName,noteNum){
  	document.traninAF.elements["inOrgId"].value=inOrgId;
  	document.traninAF.elements["inOrgName"].value=inOrgName;
  	if(noteNum!=""&&noteNum!=null){
  		document.traninAF.elements["noteNum"].value=noteNum;
  	}else{
  		document.traninAF.elements["noteNum"].value="";
  	}
  	if(inOrgName!=""){
  		showlist()
	}else{
	alert("转入单位不存在")
	}
}
function showlist() {
  document.Form1.submit();
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads(); reportErrors();"
	onContextmenu="return false">

	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="showTraninListAC.do" />
	</jsp:include>

	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
										<table border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="112" height="37"
													background="<%=path%>/img/buttong.gif" align="center"
													style="PADDING-top: 7px" valign="top">
													<a href="showTraninStayListAC.do" class=a2>待转入登记</a>
												</td>
												<td width="112" height="37"
													background="<%=path%>/img/buttonbl.gif" align="center"
													valign="top" style="PADDING-top: 7px">
													转入登记
												</td>
												<td width="112" height="37"
													background="<%=path%>/img/buttong.gif" align="center"
													style="PADDING-top: 7px" valign="top">
													<a href="showTraninVidListAC.do" class=a2>转入维护</a>
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
												<td width="189" class=font14 bgcolor="#FFFFFF"
													align="center" valign="bottom">
													<a href="#" class=a1>转入信息</a><font color="00B5DB">&gt;</font><a
														href="#" class=a1>办理转入列表</a>
												</td>
												<td width="74" class=font14>
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
							<html:form action="/showTraninListAC.do">
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="117">
														<b class="font14">转 入 信 息</b>
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
									cellpadding=3 align="center">
									<tr>
										<td width="14%" class="td1" algin="center">
											转入单位编号
										</td>
										<td width="18%">
											<html:text name="traninAF" property="inOrgId"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch" ondblclick="executeAjax();">
											</html:text>
										</td>
										<td width="8%">
											<html:submit property="method" styleClass="buttona"
												onclick="toPop()">
												<bean:message key="button.search" />
											</html:submit>
										</td>
										<td class="td1" width="17%" algin="center">
											转入单位名称
										</td>
										<td width="22%">
											<html:text name="traninAF" property="inOrgName"
												styleClass="input3" styleId="txtsearch" readonly="true"></html:text>
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%" algin="center">
											结算号
										</td>
										<td width="22%">
											<html:text name="traninAF" property="noteNum"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
									</tr>
								</table>
							</html:form>
							<html:form action="/traninMaintainAC.do" style="margin: 0">
								<table border="0" width="95%" id="table1" align="center"
									border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class=h4>
											合计：转入人数
											<u>：<bean:write name="traninAF" property="traninPeople" />
											</u> 转入金额
											<u>：<bean:write name="traninAF" property="sumBalanceAll" />
											</u> 利息
											<u>：<bean:write name="traninAF" property="sumInterestAll" />
											</u>转入总额
											<u>：<bean:write name="traninAF" property="sumTraninAll" />
											</u>
										</td>
									</tr>
								</table>
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="154">
														<b class="font14">办理转入列表</b>
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
								<html:hidden name="traninAF" property="inOrgId"></html:hidden>
								<html:hidden name="traninAF" property="inOrgName"></html:hidden>
								<html:hidden name="traninAF" property="noteNum"></html:hidden>
								<html:hidden name="traninAF" property="loadsMassage"></html:hidden>
								<html:hidden name="traninAF" property="tranInHeadById"></html:hidden>
								<input type="hidden" name="report" value="">
								<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
									cellpadding="3" align="center">
									<tr>
										<td height="23" align="center" bgcolor="C4F0FF">
											&nbsp;
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('tranInTail.empId')">职工编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranInTail.empId">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('tranInTail.name')">职工姓名</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranInTail.name">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											证件号码
										</td>
										<td align="center" class=td2>
											转入金额
										</td>
										<td align="center" class=td2>
											利息
										</td>
										<td align="center" class=td2>
											转入总额
										</td>
									</tr>
									<logic:notEmpty name="traninAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="traninAF" property="list" id="element"
											indexId="i">
											<%
														j++;
														if (j % 2 == 0) {
															strClass = "td8";
														} else {
															strClass = "td9";
														}
											%>
											<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",traninIdAF);'
												onMouseOver='this.style.background="#eaeaea"'
												onMouseOut='gotoColorpp("<%=i%>",traninIdAF);'
												class="<%=strClass%>">
												<td valign="top">
													<p align="left">
														<input id="s<%=i%>" type="radio" name="id"
															value="<bean:write name="element" property="id"/>"
															<%if(new Integer(0).equals(i)) out.print("checked"); %>>
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="empId" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="name" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="cardNum" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="sumBalance" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="sumInterest" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="traninAmount" />
													</p>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="traninAF" property="list">
										<tr>
											<td colspan="7" height="30" style="color:red">
												没有找到与条件相符合结果！
											</td>
										</tr>
										<tr>
											<td colspan="7"></td>
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

													</td>
													<td align="right">
														<jsp:include page="../../../inc/pagination.jsp">
															<jsp:param name="url" value="showTraninListAC.do" />
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
													<td>
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.exports" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.imports" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.add" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.update" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona"
															onclick="return checkDetele()">
															<bean:message key="button.delete" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona"
															onclick="return checkDeteleAll()">
															<bean:message key="button.deleteall" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona"
															onclick="return gotoPrint();">
															<bean:message key="button.del.tranin" />
														</html:submit>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</html:form>
							<form action="showTraninListAC.do" method="POST" name="Form1"
								id="Form1">
							</form>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>
