<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.collloanback.action.CollLoanbackTcShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			CollLoanbackTcShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>公积金还贷查询</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
var tr="tr0"; 
	function gettr(trindex) {
	  tr=trindex;
	}
function a(){
	var p = "<%=path%>";
	officeAjax1(p);
}
function officeAjax1(path){
	var officeCode = document.all.officeName.value;
	if(officeCode.length==0){
		officeCode="all";
	}
	var url = path+"/syscollection/collloanback/officeAndBankAAC.do?officeCode="+officeCode;
	officeFindInfo1(url);
}
function officeFindInfo1(url) {
 		createXMLHttpRequest();
	    xmlHttp.onreadystatechange = officeStateChange1;
	    xmlHttp.open("GET", url, true);
	    xmlHttp.send(null);   
}
function officeStateChange1() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {
        var xmlDoc = xmlHttp.responseXML;
		var values = xmlDoc.getElementsByTagName("value");
		 
		var texts  = xmlDoc.getElementsByTagName("text");		
		var selectObj = document.getElementById("bankName");
		selectObj.length = 0;
		
		var mynull = new Option("","");
		selectObj.options[selectObj.length++] = mynull;
		for ( i=0; i < values.length; i++ ) {
			var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
			selectObj.options[selectObj.length++] = childOption;
		}
		document.all.bankName.value="";	
      }
   }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
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

						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="200" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="228" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">公积金还贷查询</font>
									</td>
									<td width="35" class=font14>
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
				<html:form action="/collLoanbackTcFindAC.do" styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="176">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="734">
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
							<td width="14%" class=td1>
								单位编号
							</td>
							<td>
								<html:text name="collLoanbackTcAF" property="orgId"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="14%" class=td1>
								单位名称
							</td>
							<td>
								<html:text name="collLoanbackTcAF" property="orgName"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="14%" class=td1>
								职工编号
							</td>
							<td>
								<html:text name="collLoanbackTcAF" property="empId"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="14%" class=td1>
								职工名称
							</td>
							<td>
								<html:text name="collLoanbackTcAF" property="empName"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="14%">
								办事处
							</td>
							<td width="36%" class="td4">
								<html:select name="collLoanbackTcAF" property="officeName"
									styleClass="input4" onchange="a();">
									<option value=""></option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td class=td1 width="14%">
								银行
							</td>
							<td width="36%" class="td4">
								<html:select name="collLoanbackTcAF" property="bankName"
									styleClass="input4">
									<option value=""></option>
									<html:options collection="loanbankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="14%" class=td1>
								合同编号
							</td>
							<td>
								<html:text name="collLoanbackTcAF" property="contractId"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="14%" class=td1>
								扣款日期
							</td>
							<td>
								<html:text name="collLoanbackTcAF" property="bizDate"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" align="right" cellpadding="0" cellspacing="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								总计：
								<u>人数合计:<bean:write name="collLoanbackTcAF"
										property="personSum" />
								</u>&nbsp;&nbsp;
								<u>提取金额合计:<bean:write name="collLoanbackTcAF"
										property="checkMoneySum" />
								</u>
							</td>
						</tr>
					</table>
				</html:form>

				<html:form action="/collLoanbackTcMaintainAC.do"
					styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="174">
											<strong class="font14">公积金还贷列表</strong>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="736">
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
						<tr align="center">
							<td bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td bgcolor="C4F0FF" class="td2">
								单位编号
							</td>
							<td class="td2">
								单位名称
							</td>
							<td class="td2">
								职工编号
							</td>
							<td class="td2">
								职工姓名
							</td>
							<td class="td2">
								提取金额
							</td>
							<td class="td2">
								借款合同编号
							</td>
							<td class="td2">
								借款人姓名
							</td>
							<td class="td2">
								扣款日期
							</td>
						</tr>
						<logic:notEmpty name="collLoanbackTcAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="collLoanbackTcAF" property="list"
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
									<!-- 
									<td onclick='gettr("tr<%=i%>")'>
										<html:multibox property="rowArray">
											<bean:write name="e" property="id" />
										</html:multibox>
									</td>
								 -->
									<td>
										0
										<bean:write name="e" property="orgId" />
									</td>
									<td>
										<bean:write name="e" property="orgName" />
									</td>
									<td>
										<bean:write name="e" property="empId" format="000000" />
									</td>
									<td>
										<bean:write name="e" property="empName" />
									</td>
									<td>
										<bean:write name="e" property="checkMoney" />
									</td>
									<td>
										<bean:write name="e" property="contractId" />
									</td>
									<td>
										<bean:write name="e" property="borrowerName" />
									</td>
									<td>
										<bean:write name="e" property="bizDate" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="collLoanbackTcAF" property="list">
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
							<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
											<br>
										</td>
										<td align="right">
											<jsp:include page="../../inc/pagination.jsp">
												<jsp:param name="url" value="collLoanbackTcShowAC.do" />
											</jsp:include>
											<br>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<!-- 	<table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<logic:notEmpty name="collLoanbackTcAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="">
													<bean:message key="button.deleteall" />
												</html:submit>
											</td>
										</logic:notEmpty>
										<logic:empty name="collLoanbackTcAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.deleteall" />
												</html:submit>
											</td>
										</logic:empty>
									</tr>
								</table>  
							</td>
						</tr>
					</table> -->
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
