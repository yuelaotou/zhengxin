<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanaccord.printplan.action.PrintplanTaShowAC"%>
<%
	Object pagination = request.getSession(false).getAttribute(
	PrintplanTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
String buttonInfo=(String)request.getAttribute("buttonInfo");
String path = request.getContextPath();
%>

<html:html>
<head>
	<title>贷款发放</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	
	
	<script language="javascript" src="js/common.js">
</head>
<script language="javascript" ></script>
	<script language="javascript" type="text/javascript">
	var s1="";
	function loads(){
	var count = "<%=buttonInfo%>";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		
		}
	}
	if(count=='disabled'){
	document.all.item(s1).disabled="true";
	}else{
	document.all.item(s1).disabled="";
	}
}
	function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	if(message!=''){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		
		}
	}
	document.all.item(s1).disabled="true";
	}
	</logic:messagesPresent>
   }
 function checkContractId(){
 gotoContractpop('10,11','<%=path%>','0');
}
function printplan(){
location.href='<%=path%>/sysloan/printplanPrintAC.do'
}
function executeAjax() {
var contractId=document.printplanShowAF.elements["printplanDTO.contractId"].value.trim();
location.href='<%=path%>/sysloan/printplanTaFindAC.do?contractId='+contractId;
}
function enterToSubmit(){
  if(event.keyCode == 13){
var contractId=document.printplanShowAF.elements["printplanDTO.contractId"].value.trim();
location.href='<%=path%>/sysloan/printplanTaFindAC.do?contractId='+contractId;
  }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors();"
	onContextmenu="return false">
    <jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="printplanTaShowAC.do" />
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
							&nbsp;
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="176" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">发放贷款&gt;打印还款计划表</font>
									</td>
									<td width="87" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="158">
										<b class="font14">还款计划表信息</b>
									</td>
									<td width="752" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table border="0" width="95%" id="table1" cellspacing=1
					cellpadding=3 align="center">
				</table>
				<html:form action="/printplanTaFindAC.do" style="margin: 0">

					<table width="96%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" width="100%" id="table1" cellspacing=1
									cellpadding=3 align="center">
									<tr>
										<td width="17%" class="td1">
											合同编号
										</td>
										<td width="22%">
											<html:text onkeydown="enterToSubmit();"
											name="printplanShowAF"	property="printplanDTO.contractId" styleClass="input3"
												ondblclick="executeAjax();" />
										<td width="11%">
											<input type="button" class="buttona" value="..."
												onClick="checkContractId()">
										</td>
										<td width="17%" class="td1">
											扣款账号
										</td>
										<td width="33%">
											<html:text onkeydown="enterNextFocus1();" readonly="true"
												name="printplanShowAF" property="printplanDTO.loanKouAcc"
												styleClass="input3" />
										</td>

									</tr>
									<tr>
										<td width="17%" class="td1">
											借款人姓名
										</td>
										<td width="33%"  colspan="2">
											<html:text onkeydown="enterNextFocus1();"
												name="printplanShowAF" readonly="true"
												property="printplanDTO.borrowerName" styleClass="input3" />
										</td>
										<td width="17%" class="td1">
											证件类型
										</td>
										<td width="33%">
											<html:text onkeydown="enterNextFocus1();"
												name="printplanShowAF" readonly="true"
												property="printplanDTO.cardKindName" styleClass="input3" />
										</td>

									</tr>
									<tr>
										<td width="17%" class="td1">
											证件号码
										</td>
										<td width="33%"  colspan="2">
											<html:text onkeydown="enterNextFocus1();"
												name="printplanShowAF" readonly="true"
												property="printplanDTO.cardNum" styleClass="input3" />
										</td>

										<td width="17%" class="td1">
											借款金额
										</td>
										<td width="33%">
											<html:text onkeydown="enterNextFocus1();"
												name="printplanShowAF" readonly="true"
												property="printplanDTO.loanMoney" styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											借款期限（月）
										</td>
										<td width="33%"  colspan="2">
											<html:text onkeydown="enterNextFocus1();"
												name="printplanShowAF" readonly="true"
												property="printplanDTO.loanTimeLimit" styleClass="input3" />
										</td>
										<td width="17%" class="td1">
											还款方式
										</td>
										<td width="33%">
											<html:text onkeydown="enterNextFocus1();"
												name="printplanShowAF" readonly="true"
												property="printplanDTO.loanModeName" styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											剩余本金
										</td>
										<td width="33%" colspan="2">
											<html:text onkeydown="enterNextFocus1();"
												name="printplanShowAF" readonly="true"
												property="printplanDTO.overplusLoanMoney"
												styleClass="input3" />
										</td>
										<td width="17%" class="td1">
											剩余期限（月）
										</td>
										<td width="33%">
											<html:text onkeydown="enterNextFocus1();"
												name="printplanShowAF" readonly="true"
												property="printplanDTO.overplusLimite" styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											放款银行
										</td>
										<td width="33%" colspan="2">
											<html:text onkeydown="enterNextFocus1();"
												name="printplanShowAF" readonly="true"
												property="printplanDTO.loanBankName" styleClass="input3" />
										</td>
										<td width="17%" class="td1">

										</td>
										<td width="33%">
										</td>
									</tr>
								</table>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="161">
										<b class="font14">还款计划表信息列表 </b>
									</td>
									<td width="749" height="22" align="center"
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
				<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            
						<td align="center" class=td2>
							还款年月
						</td>
						<td align="center" class=td2>
							应还本金
						</td>
						<td align="center" class=td2>
							应还利息
						</td>
						<td align="center" class=td2>
							月还本息合计
						</td>
						<td align="center" class=td2>
							每月利率
						</td>
					</tr>
					<logic:notEmpty name="printplanShowAF" property="list">
					<% int j=0;
			String strClass="";
		%>
						<logic:iterate name="printplanShowAF" property="list" id="element">
						<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
							<tr class="<%=strClass%>">
								<td valign="top">
									<p>
										<bean:write name="element" property="loanKouYearmonth" />
									</p>
								</td>
								<td valign="top">

									<bean:write name="element" property="shouldCorpus" />

								</td>
								<td valign="top">
									<p>
										<bean:write name="element" property="shouldInterest" />

									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="element" property="ciMoney" />
									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="element" property="temploanRate" />
									</p>
								</td>

							</tr>
							
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="printplanShowAF" property="list">
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
													<jsp:param name="url" value="printplanTaShowAC.do" />
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
										<html:submit styleClass="buttona" onclick="return printplan()">
											<bean:message key="button.print" />
										</html:submit>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>
