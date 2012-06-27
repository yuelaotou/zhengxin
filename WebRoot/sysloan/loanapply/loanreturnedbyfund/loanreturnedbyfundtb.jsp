<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.action.LoanreturnedbyfundTbShowAC"%>
<%
	String path = request.getContextPath();
	String contractid = (String) request.getSession().getAttribute(
			"contractid");
	Object pagination = request.getSession().getAttribute(
			LoanreturnedbyfundTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
		<script language="javascript" src="<%=path%>/js/common.js"></script>

	</head>

	<script src="js/common.js"></script>
	<script>
var tr="tr0"; 
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";

var xieYiNum="";
var banLiRiQi="";
var contractId="";
var s="";
function gettr(trindex){
tr=trindex;
xieYiNum=document.getElementById(tr).childNodes[1].innerHTML;	
banLiRiQi=document.getElementById(tr).childNodes[7].innerHTML;	
contractId=document.getElementById(tr).childNodes[2].innerHTML;
s=document.getElementById(tr).childNodes[9].innerHTML;

	if("正常"==s.trim()){
		document.all.item(s1).disabled=false;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=false;
		document.all.item(s5).disabled=true;
	}else{	 
	 if("未启用"==s.trim()){
	    document.all.item(s1).disabled=true;
	   document.all.item(s2).disabled=false;
	   document.all.item(s3).disabled=false;
	   document.all.item(s4).disabled=true;
	   document.all.item(s5).disabled=true;
	 }else{
	   document.all.item(s1).disabled=true;
	   document.all.item(s2).disabled=false;
	   document.all.item(s3).disabled=true;	
	   document.all.item(s4).disabled=true; 
	   document.all.item(s5).disabled=false;
	 }	   
  }	
}


function reportErrors() {
for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="撤消"){
				s1=i;
			}
			if(document.all.item(i).value=="删除"){
				s2=i;
			}
			if(document.all.item(i).value=="启用"){
			   s3=i;
			}
			if(document.all.item(i).value=="打印"){
			   s4=i;
			}
			if(document.all.item(i).value=="撤消打印"){
			   s5=i;
			}
			
		}
	} 
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
var a=document.all.id;
if(a==undefined){

}
if(a!=undefined){
	
	xieYiNum=document.getElementById("tr0").childNodes[1].innerHTML;	
	banLiRiQi=document.getElementById("tr0").childNodes[7].innerHTML;	
	contractId=document.getElementById("tr0").childNodes[2].innerHTML;
	var s=document.getElementById("tr0").childNodes[9].innerHTML;
		if("正常"==s.trim()){
		document.all.item(s1).disabled=false;
		document.all.item(s2).disabled=true;
		document.all.item(s3).disabled=true;
		document.all.item(s4).disabled=false;
		document.all.item(s5).disabled=true;
	}else{	 
	 if("未启用"==s.trim()){
	    document.all.item(s1).disabled=true;
	   document.all.item(s2).disabled=false;
	   document.all.item(s3).disabled=false;
	   document.all.item(s4).disabled=true;
	   document.all.item(s5).disabled=true;
	 }else{
	   document.all.item(s1).disabled=true;
	   document.all.item(s2).disabled=false;
	   document.all.item(s3).disabled=true;
	   document.all.item(s4).disabled=true;
	   document.all.item(s5).disabled=false;	 
	 }	   
  }	
}
}

function gotolink(l){
location.href=l;
}
function gotodel(l){
	if(!confirm("是否确认删除？")){
	}
	location.href=l;
	}
	function gotopri(l){
	if(!confirm("是否确认打印？")){
	}
	location.href=l;
	}
function gotoDetail(){
window.showModalDialog("开户销户_开户登记_开户登记详细.htm","window","dialogHeight:350px;dialogWidth:650px;dialogLeft:200;dialogTop:200;help:no;status=no;scroll=no;center:yes"); 
}
function sure(){
	document.URL="<%=path%>/sysloan/loanreturnedbyfundTbDeleteAC.do?xieYiNum="+xieYiNum+"&year="+banLiRiQi+"&contractId="+contractId;
	window.close();
}
</script>

	<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();">

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
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											<a href="<%=path%>/sysloan/loanreturnedbyfundTaForwardAC.do"
												class=a2>办理公积金还贷</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											公积金还贷维护
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
										<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">签订公积金划款合同&gt;</font>
										</td>
										<td width="100" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="202">
											<b class="font14">查 询 条 件</b>
										</td>
										<td width="723" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/loanreturnedbyfundTbFindAC" style="margin: 0">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=0 align="center">
							<tr>
								<td width="13%" class="td1">
									合同编号
								</td>
								<td width="30%" colspan="2">
									<html:text property="contractId" name="loanreturnedbyfundTbAF"
										styleClass="input3" />
								</td>
								<td width="7%">
									<input type="button" class="buttona" value="aaa"
										onClick="gotoContractpop('11','<%=path%>','0','1');">
								</td>
								<td width="13%" class="td1">
									协议号
								</td>
								<td width="37%">
									<html:text property="xieYiNum" name="loanreturnedbyfundTbAF"
										styleClass="input3" maxlength="10" />
								</td>
							</tr>
							<tr>
								<td width="13%" class="td1">
									借款人姓名
								</td>
								<td width="37%" colspan="3">
									<html:text property="borrowerName"
										name="loanreturnedbyfundTbAF" styleClass="input3" />
								</td>
								<td width="13%" class="td1">
									证件号码
								</td>
								<td width="37%">
									<html:text property="cardNum" name="loanreturnedbyfundTbAF"
										styleClass="input3" maxlength="18" />
								</td>
							</tr>
							<tr>
								<td width="13%" class="td1">
									配偶姓名
								</td>
								<td width="37%" colspan="3">
									<html:text property="assiBorrowerName"
										name="loanreturnedbyfundTbAF" styleClass="input3" />
								</td>
								<td width="13%" class="td1">
									配偶证件号码
								</td>
								<td width="37%">
									<html:text property="assiBorrowerCardNum"
										name="loanreturnedbyfundTbAF" styleClass="input3"
										maxlength="18" />
								</td>
							</tr>
							<tr>
								<td width="13%" class="td1">
									单位公积金账号
								</td>
								<td width="37%" colspan="3">
									<html:text property="orgId" name="loanreturnedbyfundTbAF"
										styleClass="input3" maxlength="10" />
								</td>
								<td width="13%" class="td1">
									职工公积金账号
								</td>
								<td width="37%">
									<html:text property="empId" name="loanreturnedbyfundTbAF"
										styleClass="input3" />
								</td>
							</tr>
							<tr>
								<td width="13%" class="td1">
									签订日期
									<br>
								</td>
								<td width="17%">
									<html:text name="loanreturnedbyfundTbAF" property="startDate"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="3%" align="center">
									至
								</td>
								<td width="17%">
									<html:text name="loanreturnedbyfundTbAF" property="endDate"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="13%" class="td1">
									状态
								</td>
								<td width="37%">
									<html:select property="sta"
											styleClass="input4" name="loanreturnedbyfundTbAF"
											onkeydown="enterNextFocus1();">
											<html:option value=""></html:option>
											<html:option value="1">正常</html:option>
											<html:option value="2">未启用</html:option>
											<html:option value="0">撤消</html:option>

										</html:select> 
								</td>
							</tr>
							<tr>
								<td width="13%" class="td1">
									撤销日期
									<br>
								</td>
								<td width="17%">
									<html:text name="loanreturnedbyfundTbAF" property="begstop"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="3%" align="center">
									至
								</td>
								<td width="17%">
									<html:text name="loanreturnedbyfundTbAF" property="endstop"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="13%" class="td1">
									放款银行
								</td>
								<td width="37%">
									<html:select name="loanreturnedbyfundTbAF"
										property="loanBankId" styleClass="input4"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="banklist" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<html:submit styleClass="buttona">
										<bean:message key="button.search" />
									</html:submit>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td class=h4>
									合计：合同数
									<u>：<bean:write name="loanreturnedbyfundTbAF"
											property="count" /> </u> 人数
									<u>：<bean:write name="loanreturnedbyfundTbAF"
											property="personCount" /> </u>
								</td>
							</tr>
						</table>
					</html:form>
					<html:form action="/loanreturnedbyfundTbMainTianAC.do"
						style="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="205">
												<b class="font14"><a href="贷款申请_贷款申请_辅助借款人基本信息.htm"
													class=a2></a>公积金还贷列表</b>
											</td>
											<td width="720" height="22" align="center"
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
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr align="center" bgcolor="C4F0FF">
								<td height="23" bgcolor="C4F0FF">
									&nbsp;
								</td>
								<td class="td2">
									<a href="javascript:sort('a.reservea_c')">协议号</a>
									<logic:equal name="pagination" property="orderBy"
										value="a.reservea_c">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td class="td2">
									<a href="javascript:sort('p110.contract_id')">合同编号</a>
									<logic:equal name="pagination" property="orderBy"
										value="p110.contract_id">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td class="td2">
									借款人姓名
								</td>
								<td class="td2">
									证件号码
								</td>
								<td class="td2">
									单位编号
								</td>
								<td class="td2">
									单位名称
								</td>
								<td class="td2">
									<a href="javascript:sort('a.reservea_a')">签订日期</a>
									<logic:equal name="pagination" property="orderBy"
										value="a.reservea_a">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td class="td2">
									<a href="javascript:sort('a.date_stop')">撤销日期</a>
									<logic:equal name="pagination" property="orderBy"
										value="a.date_stop">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td class="td2">
									状态
								</td>
								<td class="td2">
									扣款人类别
								</td>
							</tr>
							<logic:notEmpty name="loanreturnedbyfundTbAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate id="e" name="loanreturnedbyfundTbAF"
									property="list" indexId="i">
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
													value="<bean:write name="e" property="contractId"/>,<bean:write name="e" property="id"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>
										<td>
											<bean:write name="e" property="xieYuNum" />
										</td>
										<td>
											<bean:write name="e" property="contractId" />
										</td>
										<td>
											<bean:write name="e" property="borrowerName" />
										</td>
										<td>
											<bean:write name="e" property="cardNum" />
										</td>
										<td>
											<bean:write name="e" property="orgId" format="0000000000" />
										</td>
										<td>
											<bean:write name="e" property="orgName" />
										</td>
										<td>
											<bean:write name="e" property="riQi" />
										</td>
										<td>
											<bean:write name="e" property="riQia" />
										</td>
										<td>
											<bean:write name="e" property="status" />
										</td>
										<td>
											<bean:write name="e" property="seq" />
										</td>
									</tr>

								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="loanreturnedbyfundTbAF" property="list">
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
								<td align="center">

									<table width="95%" border="0" cellspacing="0" cellpadding="3"
										align="center">
										<tr class="td1">
											<td>
												<table width="100%" border="0" align="center"
													cellpadding="0" cellspacing="0">
													<tr>
														<td align="left">
															共
															<bean:write name="pagination" property="nrOfElements" />
															项
														</td>
														<td align="right">
															<jsp:include page="../../../inc/pagination.jsp">
																<jsp:param name="url"
																	value="loanreturnedbyfundTbShowAC.do" />
															</jsp:include>
														</td>
													</tr>
												</table>
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
											<logic:empty name="loanreturnedbyfundTbAF" property="list">

												<td width="65">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.delete" />
													</html:submit>
												</td>

												<td width="69" align="right">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.revert" />
													</html:submit>
												</td>
												<td width="69" align="right">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.print" />
													</html:submit>
												</td>
												<td width="69" align="right">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.use" />
													</html:submit>
												</td>
											</logic:empty>
											<logic:notEmpty name="loanreturnedbyfundTbAF" property="list">
												<td width="65">
													<html:submit property="method" styleClass="buttona"
														onclick="sure();">
														<bean:message key="button.delete" />
													</html:submit>
												</td>
												<td width="69" align="right">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.revert" />
													</html:submit>
												</td>
												<td width="69" align="right">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.print" />
													</html:submit>
												</td>
												<td width="69" align="right">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.revert.print" />
													</html:submit>
												</td>
												<td width="69" align="right">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.print.list" />
													</html:submit>
												</td>
												<td width="69" align="right">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.use" />
													</html:submit>
												</td>
											</logic:notEmpty>
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
