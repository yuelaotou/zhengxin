<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loancallback.loancallback.action.LoancallbackTbShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			LoancallbackTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
</script>
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
  	var status=document.getElementById(tr).childNodes[12].childNodes[0].innerHTML;
  	if(status=='导出'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
  	}else if(status=='导入'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
  	}else if(status=='登记'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
  	}else if(status=='确认'){
  		document.all.item(s1).disabled="";
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
  	var status_1=document.getElementById(tr).childNodes[15].childNodes[0].innerHTML;
  	
  	if(status_1=='是'){
  		
		document.all.item(s3).disabled="true";
		
  	}
}
function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	document.loancallbackTbAF.dateStart.value="";
	document.loancallbackTbAF.dateEnd.value="";
	document.loancallbackTbAF.loanKouAcc.value="";
	document.loancallbackTbAF.contractId.value="";
	document.loancallbackTbAF.borrowerName.value="";
	document.loancallbackTbAF.cardNum.value="";
	document.loancallbackTbAF.docNum.value="";
	document.loancallbackTbAF.type.value="";
	document.loancallbackTbAF.loanBankId.value="";
	document.loancallbackTbAF.status.value="";
	for(i=0;i<document.all.length;i++){//获得所有form
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="删除"){
				s1=i;
			}
			if(document.all.item(i).value=="回收"){
				s2=i;
			}
			if(document.all.item(i).value=="打印"){
				s3=i;
			}
			
		}
	}
    var count = "<bean:write name="pagination" property="nrOfElements"/>";
    if(count == "0"){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
    }else{
		update1();
    }
}

function gotoShow(){
	return false;
}
function gotoDelete(){
	if(!confirm("是否确定删除该笔业务？")){
		return false;
	}
}
function gotoCallback(){
 if(!confirm("是否确定回收？")){
		return false;
	}
}

function gotoPrint(){

}
function gotoSearch(){	
	var dateStart=document.loancallbackTbAF.dateStart.value;
	var dateEnd=document.loancallbackTbAF.dateEnd.value;
	if(dateStart==""||dateEnd==""){
		alert("请输入查询日期！");
		return false;
	}
	document.loancallbackTbAF.dateStart.focus();
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="loancallbackTbShowAC.do" />
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
										<a href="<%=path%>/sysloan/loancallbackTaForwardURLAC.do"
											class=a2>办理回收</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										回收维护
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
										<font color="00B5DB">回收贷款&gt;回收贷款</font>
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
				<html:form action="/loancallbackTbFindAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="166">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="744">
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
								回收日期
							</td>
							<td width="15%" align="center">
								<html:text property="dateStart" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%" align="center">
								至
							</td>
							<td width="15%" align="center">
								<html:text property="dateEnd" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								扣款账号
							</td>
							<td width="33%">
								<html:text property="loanKouAcc" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%" colspan="3">
								<html:text property="contractId" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								凭证编号
							</td>
							<td width="33%" align="center">
								<html:text property="docNum" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%" align="center" colspan="3">
								<html:text property="borrowerName" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td align="center">
								<html:text property="cardNum" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								业务类型
							</td>
							<td align="center" colspan="3">
								<html:select property="type" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="typeMap"
										name="loancallbackTbAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								业务状态
							</td>
							<td align="center">
								<html:select property="status" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="statusMap"
										name="loancallbackTbAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								放款银行
							</td>
							<td width="33%" align="center" colspan="3">
								<html:select property="loanBankId" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:options collection="banklist" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								公积金还贷
							</td>
							<td align="center">
								<html:select property="yesOrNo" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="yesornoMap"
										name="loancallbackTbAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return gotoSearch();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/loancallbackTbMaintainAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：本次实还笔数
								<u>：<bean:write name="pagination" property="nrOfElements" />
								</u>本次实还人数
								<u>：<bean:write name="loancallbackTbDTO"
										property="realCount" />
								</u>本次实还款本金
								<u>：<bean:write name="loancallbackTbDTO"
										property="realCorpus" /> </u> 本次实还款利息
								<u>：<bean:write name="loancallbackTbDTO"
										property="realInterest" /> </u>本次实还款逾期利息
								<u>：<bean:write name="loancallbackTbDTO"
										property="realPunishInterest" /> </u> 本次实还款金额
								<u>：<bean:write name="loancallbackTbDTO"
										property="realbackMoney" /> </u>挂账发生额
								<u>：<bean:write name="loancallbackTbDTO"
										property="occurMoney" /> </u>实收金额
								<u>：<bean:write name="loancallbackTbDTO"
										property="realMoney" /> </u>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="167">
											<b class="font14">还 款 列 表</b>
										</td>
										<td width="743" height="22" align="center"
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
							<td align="center" class=td2>
								扣款账号
							</td>
							<td align="center" class=td2>
								凭证编号
							</td>
							<td align="center" class=td2>
								合同编号
							</td>
							<td align="center" class=td2>
								借款人姓名
							</td>
							<td align="center" class=td2>
								本次实还款本金
							</td>
							<td align="center" class=td2>
								本次实还款利息
							</td>
							<td align="center" class=td2>
								实还逾期利息
							</td>
							<td align="center" class=td2>
								本次实还款金额
							</td>
							<td align="center" class=td2>
								挂账发生额
							</td>
							<td align="center" class=td2>
								实收金额
							</td>
							<td align="center" class=td2>
								业务类型
							</td>
							<td align="center" class=td2>
								业务状态
							</td>
							<td align="center" class=td2>
								业务日期
							</td>
							<td align="center" class=td2>
								公积金还贷
							</td>
							<td align="center" class=td2>
								是否需要审核
							</td>
						</tr>
						<logic:notEmpty name="callbacklist">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="callbacklist" id="element" indexId="i">
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
												value="<bean:write name="element" property="id"/>,<bean:write name="element" property="contractId"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanKouAcc" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="docNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="contractId" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="borrowerName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="realCorpus" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="realInterest" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="realPunishInterest" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="realbackMoney" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="occurMoney" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="realMoney" />
										</p>
									</td>
									<td valign="top">
										<p>
											<a href="#"
												onClick="window.open('loancallbackTbForwardURLWindowAC.do?contractId=<bean:write name="element" property="contractId"/>&headId=<bean:write name="element" property="id"/>&type=<bean:write name="element" property="type"/>','','width=1000,height=600,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,location=no, status=no');return gotoShow();">
												<bean:write name="element" property="bizType" />
											</a>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="bizSt" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="bizDate" />
										</p>
									</td>
									<td align="center">
										<p>
											<bean:write name="element" property="yesOrNo" />
										</p>
									</td>
									<td align="center">
										<p>
											<bean:write name="element" property="mark" />
										</p>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="callbacklist">
							<tr>
								<td colspan="15" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>

						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="left">
								共
								<bean:write name="pagination" property="nrOfElements" />
								项
							</td>
							<td align="right">
								<jsp:include page="../../../inc/pagination.jsp">
									<jsp:param name="url" value="loancallbackTbShowAC.do" />
								</jsp:include>
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
											<html:submit property="method" styleClass="buttona"
												onclick="return gotoDelete();">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return gotoCallback();">
												<bean:message key="button.callback" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return gotoPrint();">
												<bean:message key="button.print" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
	</table>
</body>
</html:html>

