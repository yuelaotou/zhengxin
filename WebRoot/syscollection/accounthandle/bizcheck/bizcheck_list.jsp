<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.bizcheck.action.BizcheckTaShowAC"%>


<%
			Object pagination = request.getSession(false).getAttribute(
			BizcheckTaShowAC.PAGINATION_KEY);
	String officeCount = (String) request.getSession().getAttribute(
			"officeCount");
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String checkOver = (String) request.getSession().getAttribute(
			"checkOver");
	request.getSession().setAttribute("checkOver", null);
	String isModify = (String) request.getSession().getAttribute(
			"isModify");
	request.getSession().setAttribute("isModify", null);
%>
<html:html>
<head>
	<title>业务复核</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
</head>
<script language="javascript"
	src="<%=request.getContextPath()%>/js/common.js">



<script language="javascript"></script>
<script language="javascript" type="text/javascript">
 var orgId="";
var s1="";
var s2="";
var s3="";
var s4="";
var tr="tr0"; 
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
var tr='tr0';
function gettr(trindex){
tr=trindex;
var a=document.getElementById(tr).childNodes[11].innerHTML;	
orgId=document.getElementById(tr).childNodes[3].innerHTML;	
	var b="入账";
	var c="复核";
	var d="确认";
	var count=0;
	var i=0;
	var status="";
	var length=document.all.id;
	for(i=0;i<length.length;i++){
		if(i==0){
			count=1;
			status=document.getElementById("tr0").childNodes[11].innerHTML;	
		}
		
		if(status!=document.getElementById("tr"+i).childNodes[11].innerHTML){
			count=2;
		}
		
	}
	if(count==0){
		count=1;
	}
	if(count==1){
		if(b==a.trim()){	
		document.all.item(s1).disabled=true;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=true;
		}
		if(c==a.trim()){	
		document.all.item(s1).disabled=true;
		document.all.item(s2).disabled=false;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=false;
		}
		if(d==a.trim()){	
		document.all.item(s1).disabled=false;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=false;
		document.all.item(s4).disabled=true;
		}
	}
	if(count==2){
		
		if(b==a.trim()){	
		document.all.item(s1).disabled=true;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=true;
		}
		if(c==a.trim()){	
		document.all.item(s1).disabled=true;
		document.all.item(s2).disabled=false;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=true;
		}
		if(d==a.trim()){	
		document.all.item(s1).disabled=false;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=true;
		}
	}
}
function update2() {
  	var bizStatus_=document.getElementById(tr).childNodes[11].childNodes[0].innerHTML;
  	var statusType=document.bizcheckAF.elements["statusType"].value;
  	//if(statusType=="1"){
  		//document.all.item(s1).disabled="";
		//document.all.item(s2).disabled="";
		//document.all.item(s3).disabled="true";
		//document.all.item(s4).disabled="true";
  	//}else if(statusType=="0"){//入账
  	    //document.all.item(s1).disabled="";
		//document.all.item(s2).disabled="";
		//document.all.item(s3).disabled="true";
		//document.all.item(s4).disabled="true";
  	//}
  	//else if(statusType=="2"){
  		//document.all.item(s1).disabled="true";
		//document.all.item(s2).disabled="true";
		//document.all.item(s3).disabled="";
		//document.all.item(s4).disabled="";
  	//}else
  	if(bizStatus_=="确认"){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		if(statusType=="1"){
		  document.all.item(s1).disabled="";
		  document.all.item(s2).disabled="";
		  document.all.item(s3).disabled="true";
		  document.all.item(s4).disabled="true";
		}
  	}else
  	if(bizStatus_=="复核"){	
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
		if(statusType=="2"){
		   document.all.item(s1).disabled="true";
		   document.all.item(s2).disabled="true";
		   document.all.item(s3).disabled="";
		   document.all.item(s4).disabled="";
		}
  	}else  
  	if(bizStatus_=="入账"){	
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
  	}  
  
}
function update1() {
  	var bizStatus_=document.getElementById("tr0").childNodes[11].childNodes[0].innerHTML;
  	var statusType=document.bizcheckAF.elements["statusType"].value;
  	//if(statusType=="1"){
  		//document.all.item(s1).disabled="";
		//document.all.item(s2).disabled="";
		//document.all.item(s3).disabled="true";
		//document.all.item(s4).disabled="true";
  	//}else if(statusType=="0"){//入账
  	    //document.all.item(s1).disabled="";
		//document.all.item(s2).disabled="";
		//document.all.item(s3).disabled="true";
		//document.all.item(s4).disabled="true";
  	//}
  	//else if(statusType=="2"){
  		//document.all.item(s1).disabled="true";
		//document.all.item(s2).disabled="true";
		//document.all.item(s3).disabled="";
		//document.all.item(s4).disabled="";
  	//}else
  	if(bizStatus_=="确认"){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		if(statusType=="1"){
		  document.all.item(s1).disabled="";
		  document.all.item(s2).disabled="";
		  document.all.item(s3).disabled="true";
		  document.all.item(s4).disabled="true";
		}
  	}else
  	if(bizStatus_=="复核"){	
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
		if(statusType=="2"){
		  document.all.item(s1).disabled="true";
		  document.all.item(s2).disabled="true";
		  document.all.item(s3).disabled="";
		  document.all.item(s4).disabled="";
		}
  	}else  
  	if(bizStatus_=="入账"){	
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
  	}  
  
}
function reportErrors() {
for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="复核通过"){
				s1=i;
			}
			if(document.all.item(i).value=="撤消复核"){
				s2=i;
			}	
			if(document.all.item(i).value=="批量复核"){
				s3=i;
			}		
			if(document.all.item(i).value=="撤消批量复核"){
				s4=i;
			}	
			if(document.all.item(i).value=="查看流水账"){			
				s5=i;
			}			
		}
	} 
var a=document.all.id;
if(a==undefined){
document.all.item(s1).disabled=true;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=true;
}
if(a!=undefined){
	orgId=document.getElementById("tr0").childNodes[3].innerHTML;	
	var status=document.getElementById("tr0").childNodes[11].innerHTML;	
	
	var b="入账";
	var c="复核";
	var d="确认";
	var count=0;
	var i=0;
	
	
	for(i=0;i<a.length;i++){
		if(i==0){
			count=1;
			status=document.getElementById("tr0").childNodes[11].innerHTML;	
		}
		
		if(status!=document.getElementById("tr"+i).childNodes[11].innerHTML){
			count=2;
		}
		
	}
	if(count==0){
		count=1;
	}
	
	if(count==1){
		if(b==status.trim()){	
		document.all.item(s1).disabled=true;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=true;
		}
		if(c==status.trim()){	
		document.all.item(s1).disabled=true;
		document.all.item(s2).disabled=false;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=false;
		}
		if(d==status.trim()){	
		document.all.item(s1).disabled=false;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=false;
		document.all.item(s4).disabled=true;
		}
	}
	if(count==2){
		status=document.getElementById("tr0").childNodes[11].innerHTML;	
		if(b==status.trim()){	
		document.all.item(s1).disabled=true;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=true;
		}
		if(c==status.trim()){	
		document.all.item(s1).disabled=true;
		document.all.item(s2).disabled=false;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=true;
		}
		if(d==status.trim()){	
		document.all.item(s1).disabled=false;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=true;
		}
	}
	
}
	var checkOver="<%=checkOver%>";
	if(checkOver=="1"){
		var a=<%=isModify%>;
		if(a=='0'){
			window.open('<%=path%>/syscollection/accounthandle/bizcheck/bizCheckCheckAC.do?orgId='+orgId,"window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes");       
		}
		else{
			document.URL='<%=path%>/syscollection/accounthandle/bizcheck/bizcheckModifyBankAC.do';
		}	
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function backErrors(errors){
   alert(errors);
}


function executeAjax()
{
    var queryString = "bizCheckCheckAAC.do?orgId="+orgId; 
     findInfo(queryString);
}
function onclear(){
var x= document.getElementsByName("id");
var y=true;
alert(x.length);
for(var i=0;i<x.length;i++){
		if(x[i].checked){
		y=false;
		}
	}
	if(y){
	alert(' 至少选择一条记录！');
	return false;
	}
}
function execute()
{	 
	var count="0";
	count=<%=officeCount%>;
	if(count!="1")
	{
		alert("批量复核单位不在同一办事处下，请进行复核业务！");
		return false;
	}
	
    var queryString = "bizCheckCheckAAC.do?orgId="+orgId; 
     findInfo(queryString);
}

</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors();">

	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="bizcheckTaShowAC.do" />
	</jsp:include>

	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=request.getContextPath()%>/img/table_left.gif"
								width="7" height="37">
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							width="55">
							&nbsp;
						</td>
						<td width="235"
							background="<%=request.getContextPath()%>/img/table_bg_line.gif">

						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">财务处理&gt; 业务复核</font>
									</td>
									<td class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=request.getContextPath()%>/img/table_right.gif"
								width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
				<html:form action="/bizcheckTaFindAC.do">
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
							<td width="17%" class="td1">
								凭证编号
							</td>
							<td width="33%">
								<html:text name="bizcheckAF" property="docNum"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								结算号
							</td>
							<td width="33%">
								<html:text name="bizcheckAF" property="noteNum"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr id="gjtr">
							<td colspan="4">
								<table width="100%" border="0" align="center" cellpadding=0
									cellspacing=1 id="table1">
									<tr>
										<td width="17%" class="td1">
											单位编号
										</td>
										<td width="33%" align="center">
											<html:text name="bizcheckAF" property="orgId"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td width="17%" class="td1">
											单位名称
										</td>
										<td align="center" colspan="3">
											<html:text name="bizcheckAF" property="orgName"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											制单人
										</td>
										<td width="33%" align="center">

											<html:select property="operator" styleClass="input4">
												<html:option value=""></html:option>
												<html:options collection="operList1" property="value"
													labelProperty="label" />
											</html:select>
										</td>
										<td width="17%" class="td1">
											归集银行
										</td>
										<td align="center" colspan="3">

											<html:select property="collectionBank" styleClass="input4">
												<html:option value=""></html:option>
												<html:options collection="bankList1" property="value"
													labelProperty="label" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											业务状态
										</td>
										<td width="33%" align="center">
											<html:select property="bizStatus" styleClass="input4">
												<html:option value=""></html:option>
												<html:optionsCollection property="map" name="bizcheckAF"
													label="value" value="key" />
											</html:select>
										</td>
										<td width="17%" class="td1">
											办理日期
										</td>
										<td align="center" width="15%">
											<html:text name="bizcheckAF" property="startDate"
												styleClass="input3" styleId="txtsearch"></html:text>
											<html:hidden name="bizcheckAF" property="type"></html:hidden>
										</td>
										<td align="center" width="3%">
											至
										</td>
										<td align="center" width="15%">
											<html:text name="bizcheckAF" property="endDate"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%">
											业务类型
										</td>
										<td width="33%" align="center">
											<html:select property="biz_Type" styleClass="input4">
												<html:option value=""></html:option>
												<html:optionsCollection property="bis_Type"
													name="bizcheckAF" label="value" value="key" />
											</html:select>
										</td>
										<td width="17%" class="td1">
											&nbsp;
										</td>
										<td align="center" colspan="3">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											<html:hidden name="bizcheckAF" property="statusType"
												styleClass="input3" styleId="txtsearch"></html:hidden>
											<html:hidden name="bizcheckAF" property="listCount"
												styleClass="input3" styleId="txtsearch"></html:hidden>
										</td>

									</tr>
								</table>
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
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td class=h4>
							合计：发生人数
							<u>：<bean:write name="bizcheckAF" property="totalCount" /> </u>
							发生金额
							<u>：<bean:write name="bizcheckAF" property="totalDcitsum" />
							</u> 利息
							<u>：<bean:write name="bizcheckAF" property="totalInterest" />
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">业务复核列表</b>
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
				<html:form action="/bizcheckTaMaintainAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td width="4%" height="23" align="center" bgcolor="C4F0FF">
								选项
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
								<a href="javascript:sort('orgHAFAccountFlow.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgHAFAccountFlow.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td width="8%" align="center" class=td2>
								业务类型
							</td>
							<td width="8%" align="center" class=td2>
								发生人数
							</td>
							<td width="8%" align="center" class=td2>
								利息
							</td>
							<td width="8%" align="center" class=td2>
								发生金额
							</td>
							<td width="6%" align="center" class=td2>
								发生方向
							</td>

							<td width="8%" align="center" class=td2>
								业务日期
							</td>
							<td width="6%" align="center" class=td2>
								业务状态
							</td>
							<td width="6%" align="center" class=td2>
								制单人
							</td>
						</tr>


						<logic:notEmpty name="bizcheckAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="bizcheckAF" property="list"
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
										<div align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="noteNum" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="docNum" />
										</div>
									</td>
									<td>
										<bean:write name="element" property="orgId"
											format="0000000000" />
									</td>
									<td>
										<div align="left">
											<a href="#"
												onclick="window.open('bizcheckTaWindowAC.do?headid=<bean:write name="element" property="id" />','window','height=450,width=700,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,location=yes, status=yes');">
												<bean:write name="element" property="orgName" /> </a>
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="bizType" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="personTotal" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="interest" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="money" format="0.00" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="type" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="settDate" />
										</div>
									</td>
									<td>
										<bean:write name="element" property="bizStatus" />
									</td>
									<td>
										<bean:write name="element" property="reserveaA" />
									</td>

								</tr>


							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="bizcheckAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
							<tr>
								<td colspan="11"></td>
							</tr>
						</logic:empty>
						<tr>
							<td colspan="9"></td>
						</tr>
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
												<jsp:param name="url" value="bizcheckTaShowAC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td align="center" height="6" colspan="1">

								<input type="hidden" name="chgMonth2" value="">
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
												<bean:message key="button.check.through" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.checkall" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.del.check" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.del.checkall" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<form action="bizcheckTaShowAC.do" method="POST" name="Form1"
					id="Form1">
				</form>
			</td>
		</tr>
	</table>
</body>
</html:html>

