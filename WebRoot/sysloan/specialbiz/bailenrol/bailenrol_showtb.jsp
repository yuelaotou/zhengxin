<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.sysloan.specialbiz.bailenrol.action.*"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			BailenRolTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>查询条件，保证金登记列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script>
var s1="";
var s2="";
var tr='tr0'; 
function loads(){
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="删除"){
				s1=i;
			}
			if(document.all.item(i).value=="打印"){
				s2=i;
			}
		}
	}
	document.all.item(s1).disabled="true";
	document.all.item(s2).disabled="true";
	if(count!=0){
		update();
	}
}

var contractId;
function gettr(trindex) {
  tr=trindex;
  contractId=document.getElementById(tr).childNodes[1].innerHTML;
  update();
}

function update() {	
  var status=document.getElementById(tr).childNodes[7].innerHTML.trim();
     if(status=='确认'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
  	}else if(status=='复核'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
  	}else if(status=='入账'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
  	}
}

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function ondelete(){
  var x;
  x=confirm("是否删除该信息！");
  if(x){
  	if(tr=='tr0'){
  		document.all.contractid.value=document.getElementById(tr).childNodes[1].innerHTML;
  		showlist();
  	}
  	else{
  		document.all.contractid.value=contractId;
  		showlist();
  	}
  }else
  {
    return false;
  }
}

function showlist() {
  document.Form1.submit();
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads(); reportErrors();"
	onContextmenu="return false">
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
										<a href="bailenRolTaShowAC.do" class=a2>办理保证金登记</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="bailenRolTbForwardAC.do" class=a2>保证金登记维护</a>
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
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">特殊业务处理&gt;保证金登记</font>
									</td>
									<td width="29" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="205">
										<b class="font14">查 询 条 件</b>
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
				<html:form action="/bailenRolTbFindAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="13%" class="td1">
								合同编号
							</td>
							<td width="18%">
								<html:text name="bailenRolTbAF" property="contractId"
									styleClass="input3" />
							</td>
							<td width="11%" class="td1">
								借款人姓名
							</td>
							<td width="21%">
								<html:text name="bailenRolTbAF" property="borrowerName"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								证件号码
							</td>
							<td width="18%">
								<html:text name="bailenRolTbAF" property="cardNum"
									styleClass="input3" />
							</td>
							<td width="11%" class="td1">
								放款银行
							</td>
							<td width="21%">
								<logic:notEmpty name="loanBankNameList">
									<html:select name="bailenRolTbAF" property="loanBankName"
										styleClass="input3">
										<html:option value=""></html:option>
										<html:options collection="loanBankNameList" property="value"
											labelProperty="label" />
									</html:select>
								</logic:notEmpty>
							</td>
						</tr>

						<tr>
							<td width="13%" class="td1">
								业务状态
							</td>
							<td width="18%">
								<html:select name="bailenRolTbAF" property="bizSt"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizStMap"
										name="bailenRolTbAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="11%" class="td1"></td>
							<td width="21%" align="center" class="td7">
								&nbsp;
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
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：保证金金额
								<u>：<bean:write name="bailenRolTbAF"
										property="occurTotleMoney" /> </u>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="204">
										<b class="font14">保证金登记列表</b>
									</td>
									<td width="721" height="22" align="center"
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
				<html:form action="/bailenRolTbMaintainAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								合同编号
							</td>
							<td align="center" class=td2>
								借款人姓名
							</td>
							<td align="center" class=td2>
								证件号码
							</td>
							<td align="center" class=td2>
								放款银行
							</td>
							<td align="center" class=td2>
								收取日期
							</td>
							<td align="center" class=td2>
								保证金金额
							</td>
							<td align="center" class=td2>
								业务状态
							</td>
						</tr>
						<input type="hidden" name="contractid" value="">
						<logic:notEmpty name="bailenRolTbAF" property="list">
							<logic:iterate name="bailenRolTbAF" property="list" id="element"
								indexId="i">
								<tr id="tr<%=i%>"
									onclick='gotoClick("tr<%=i%>","s<%=i%>",idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColor("tr<%=i%>","s<%=i%>", idAF);' class=td4
									onDblClick="">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="flowHeadId"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<bean:write name="element" property="contractId" />
									</td>
									<td>
										<bean:write name="element" property="borrowerName" />
									</td>
									<td>
										<bean:write name="element" property="cardNum" />
									</td>
									<td>
										<bean:write name="element" property="loanBankName" />
									</td>
									<td>
										<bean:write name="element" property="bizDate" />
									</td>
									<td>
										<bean:write name="element" property="occurMoney" />
									</td>
									<td>
										<bean:write name="element" property="bizSt" />
									</td>
								</tr>
								<tr>
									<td colspan="8" class=td5></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="bailenRolTbAF" property="list">
							<tr>
								<td colspan="4" height="30" style="color:red">
									没有找到与条件相符合的结果！
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
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="bailenRolTbShowAC.do" />
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
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												onclick="return ondelete();">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.print" />
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
		<form action="bailenRolTbMaintainAC.do" method="POST" name="Form1"
			id="Form1">
		</form>
	</table>
</body>
</html:html>
