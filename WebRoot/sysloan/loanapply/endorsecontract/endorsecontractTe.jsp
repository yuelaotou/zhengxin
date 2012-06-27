<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTeShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			EndorsecontractTeShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
	<script type="text/javascript"
		src="<%=path%>/js/picture.js"></script>
</head>
<script type="text/javascript">
var goin="";
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
var s6="";
var s7="";
var tr="tr0"; 
function gettr(trindex) {
  	tr=trindex;
  	update1();
}
function update1() {
    var contractSt=document.getElementById(tr).childNodes[11].innerHTML.trim();
    var contractStatus=document.getElementById(tr).childNodes[12].innerHTML.trim();
    if(contractSt=="提交审核"){
    	if(contractStatus=="已提交"){
    		document.all.item(s1).disabled="true";
    		document.all.item(s2).disabled="true";
    		document.all.item(s6).disabled="true";
    		document.all.item(s7).disabled="";
    	}
    	if(contractStatus=="未提交"){
    		document.all.item(s7).disabled="true";
    		document.all.item(s1).disabled="";
    		document.all.item(s2).disabled="";
    		document.all.item(s6).disabled="";
    	}
    }else{
    	document.all.item(s1).disabled="true";
   		document.all.item(s2).disabled="true";
   		document.all.item(s6).disabled="true";
   		document.all.item(s7).disabled="true";
    }
}
function gotoDelete(){
	if(!confirm("确定要进行该次删除吗？")){
		return false;
	}

}
function gotoEdit(){
 	if(!confirm("确定要进行签订合同吗？")){
   		return false;
 	}else{
 		var contractId = document.getElementById(tr).childNodes[1].innerHTML.trim();
 		var queryString = "endorsecontractTeFindAAC.do?contractid="+contractId;
	    findInfo(queryString);
	    if(goin == 'yes'){
	    	alert("此合同没有签订抵押合同，不允许提交");
			return false;
	    }else{
	    	return true;
	    }
 	}
}
function display_yg(){
	goin = "yes";
}
function display_yga(){
	goin = "no";
}
function gotoEdit2(){
 	if(!confirm("确定要进行撤销签订合同吗？")){
   		return false;
 	}
}
function loads(){
    document.all.bank.value="";
    document.all.contractSt.value="";
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
			if(document.all.item(i).value=="打印借款合同"){
				s3=i;
			}
			if(document.all.item(i).value=="打印抵押合同"){
				s4=i;
			}
			if(document.all.item(i).value=="打印委托扣款保证"){
			   	s5=i;
			}
			if(document.all.item(i).value=="提交审核"){
				s6=i;
			}
			if(document.all.item(i).value=="撤销提交审核"){
				s7=i;
			}
		}
	}
	var count = "<bean:write name="pagination" property="nrOfElements" />";
	if(count==0){
	  document.all.item(s1).disabled="true";
	  document.all.item(s2).disabled="true";
	  document.all.item(s3).disabled="true";
	  document.all.item(s4).disabled="true";
	  document.all.item(s5).disabled="true";
	  document.all.item(s6).disabled="true";
	  document.all.item(s7).disabled="true";
	}else{
		update1();
	}
} 
function gotoShow(){
	return false;
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	>
	<jsp:include page="/syscommon/picture/progressbar.jsp" />
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="to_EndorsecontractTeShowAC.do" />
	</jsp:include>
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=path%>/img/table_left.gif"
								width="7" height="37">
						</td>
						<td
							background="<%=path%>/img/table_bg_line.gif"
							width="10">
							&nbsp;
						</td>
						<td
							background="<%=path%>/img/table_bg_line.gif"
							valign="top">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif"
										align="center" valign="top" style="PADDING-top: 7px">
										<a href="<%=path%>/sysloan/endorsecontractTaShowAC.do" class=a2>借款合同信息</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/endorsecontractTbShowAC.do" class=a2>抵押合同信息</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										签订合同维护
									</td>
								</tr>
							</table>
						</td>
						<td
							background="<%=path%>/img/table_bg_line.gif"
							align="right">
							<table width="200" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td width="228" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">申请贷款&gt;签订合同</font>
									</td>
									<td width="35" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="10">
							<img src="images/table_right.gif" width="9" height="37">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="165">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22"
										background="<%=path%>/img/bg2.gif"
										align="center" width="773">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/endorsecontractTeFindAC.do" method="post"
					style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="theEndorsecontractTeAF" property="contractId"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%" colspan="3">
								<html:text name="theEndorsecontractTeAF" property="debitter"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								借款人职工编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="theEndorsecontractTeAF" property="empId"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="33%" colspan="3">
								<html:text name="theEndorsecontractTeAF" property="cardNum"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								放款银行
							</td>
							<td width="33%" align="center" colspan="3">
								<html:select property="bank" onkeydown="enterNextFocus1();"
									styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="bankList"
										name="theEndorsecontractTeAF" label="collBankName"
										value="collBankId" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								借款期限(月)
							</td>
							<td align="center" colspan="3">
								<html:text name="theEndorsecontractTeAF"
									property="loanTimeLimit" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								借款起始日期
							</td>

							<td width="15%">
								<html:text name="theEndorsecontractTeAF" property="startSDate"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="4%">
								至
							</td>
							<td width="14%">
								<html:text name="theEndorsecontractTeAF" property="startEDate"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								借款终止日期
							</td>
							<td width="15%">
								<html:text name="theEndorsecontractTeAF" property="endSDate"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="4%">
								至
							</td>
							<td width="14%">
								<html:text name="theEndorsecontractTeAF" property="endEDate"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								合同状态
								<br>
							</td>
							<td width="33%" align="center" colspan="3">
								<html:select name="theEndorsecontractTeAF" property="contractSt"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="contractstMap"
										name="theEndorsecontractTeAF" label="value" value="key" />
								</html:select>
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
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="164">
										<b class="font14">贷款合同信息列表</b>
									</td>
									<td height="22" background="images/bg2.gif" align="center"
										width="761">
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
				<html:form action="/endorsecontractTeMaintainAC.do" method="post"
					style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								合同编号
							</td>
							<td align="center" class=td2>
								借款人职工编号
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('t1.borrower_name')">借款人姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="t1.borrower_name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('t2.loan_money')">借款金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="t2.loan_money">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
								<br>
							</td>
							<td align="center" class=td2>
								借款起始日期
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('t2.loan_time_limit')">借款期限（月） </a>
								<logic:equal name="pagination" property="orderBy"
									value="t2.loan_time_limit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								借款终止日期
							</td>
							<td align="center" class=td2>
								借款每月利率
							</td>
							<td align="center" class=td2>
								月还本息
							</td>
							<td align="center" class=td2>
								放款银行
							</td>

							<td align="center" class=td2>
								合同状态
							</td>
							<td align="center" class=td2>
								签订合同是否提交
							</td>
							<td align="center" class=td2 style="display:none">
								是否拨款
							</td>
							<td align="center" class=td2>
								浏览附件
							</td>
						</tr>
						<logic:notEmpty name="theEndorsecontractTeAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="theEndorsecontractTeAF"
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
												value="<bean:write name="element" property="contractId"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<bean:write name="element" property="contractId" />
									</td>
									<td>
										<bean:write name="element" property="empId" />
									</td>
									<td>
										<a href="#"
											onclick="window.open('loancontractqueryTaShowAC.do?contractIdHl=<bean:write name="element" property="contractId"/>','window','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,location=no,status=yes');return gotoShow();">
											<bean:write name="element" property="debitter" /> </a>
									</td>
									<td>
										<bean:write name="element" property="loanMoney" />
									</td>
									<td>
										<bean:write name="element" property="startDate" />
									</td>
									<td>
										<bean:write name="element" property="loanTimeLimit" />
									</td>
									<td>
										<bean:write name="element" property="endDate" />
									</td>
									<td>
										<bean:write name="element" property="temploanMonthRate" />
									</td>
									<td>
										<bean:write name="element" property="corpusInterest" />
									</td>
									<td>
										<bean:write name="element" property="bank" />
									</td>

									<td>
										<bean:write name="element" property="strContractSt" />
									</td>
									<td>
										<logic:equal name="element" property="contractStatus" value="未签订">
											未提交
										</logic:equal>
										<logic:equal name="element" property="contractStatus" value="已签订">
											已提交
										</logic:equal>
									</td>
									<td style="display:none">
										<bean:write name="element" property="fundStatus" />
									</td>
									<td>
										<a href='javascript:excHz("<bean:write name="element" property="photoUrl"/>");'><img
												src="<%=path%>/img/lookinfo.gif" width="37" height="24">
										</a>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="theEndorsecontractTeAF" property="list">
							<tr>
								<td colspan="16" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>

						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="center">
								<table width="100%" height="19" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="to_EndorsecontractTeShowAC.do" />
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
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.update" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb"
												onclick="return gotoDelete(); ">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.printborrowcontract" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.printpledgecontract" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonc">
												<bean:message key="button.entrustassurance.print" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb"
												onclick=" return gotoEdit();">
												<bean:message key="button.submit" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb"
												onclick=" return gotoEdit2();">
												<bean:message key="button.delsubmit" />
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

