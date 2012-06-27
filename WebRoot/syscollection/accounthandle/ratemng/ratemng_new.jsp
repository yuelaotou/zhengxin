<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%
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
function checkDateRate(temprate){
	var rate=temprate.value;
	var salarybase = rate.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/)
	if (salarybase==null){	
   		alert("请正确录入金额！格式如：1.23");
   		temprate.focus();
		return false;
	}else{
		return true;
	}

}
	function checkData(){
	var date = document.ratemngAF.elements["hafInterestRate.bizDate"];
	if(date.value==""){
		alert("日期不能为空");
		return false;
	}
	if (isNaN(date.value)){
		alert("请您输入数字格式");
		return false;
	}
	if(date.value.length!=8){
		alert("请您输入数正确格式,如:20080101");
		return false;
	}
	if(document.ratemngAF.elements["hafInterestRate.preRate"].value.trim()=="" || document.ratemngAF.elements["hafInterestRate.preRate"].value.trim()=="0"){
	   alert("不能为空!");
	   return false;
	}else if(document.ratemngAF.elements["hafInterestRate.curRate"].value.trim()=="" || document.ratemngAF.elements["hafInterestRate.curRate"].value.trim()=="0"){
	   alert("不能为空!");
	   return false;
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function checkPreRate(){
var preRate=document.ratemngAF.elements["hafInterestRate.preRate"];
checkDateRate(preRate);
}
function checkCurRate(){
var curRate=document.ratemngAF.elements["hafInterestRate.curRate"];
checkDateRate(curRate);
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors();" onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="showRatemngListAC.do" />
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
									<td background="<%=path%>/img/table_bg_line.gif" align="right">
										<table width="300" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="37">
													<img src="<%=path%>/img/title_banner.gif" width="37"
														height="24">
												</td>
												<td width="189" class=font14 bgcolor="#FFFFFF"
													align="center" valign="bottom">
													<font color="00B5DB">公积金利率维护</font>
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
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="154">
													<b class="font14">公积金利率维护</b>
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
							<html:form action="/addRatemngMaintainAC.do" style="margin: 0">
							
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr valign="bottom">
										<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
											<table border="0" width="95%" id="table1" cellspacing=1
												cellpadding=3 align="center">
												<tr>
													<td width="17%" class="td1">
														办事处
														<font color="#FF0000">*</font>
													</td>
													<td width="33%">
													<html:select property="hafInterestRate.officecode" styleClass="input4" onkeydown="enterNextFocus1();">
													 <html:options  collection="list" property="value" labelProperty="label"/>
											        </html:select>
										             </td>
													<td width="17%" class="td1">
														调整时间
														<font color="#FF0000">*</font>
													</td>
													<td width="33%">
														<html:text name="ratemngAF" onkeydown="enterNextFocus1();"
														 property="hafInterestRate.bizDate" styleClass="input3" />
													</td>
												</tr>
                                                 <tr>
													<td width="17%" class="td1">
														往年利率(%)
														<font color="#FF0000">*</font>
													</td>
													<td width="33%" styleClass="input3">
														<html:text name="ratemngAF" onkeydown="enterNextFocus1();"
													onblur="return checkPreRate();"		property="hafInterestRate.preRate" styleClass="input3"/>
													</td>
													<td width="17%" class="td1">
														本年利率(%)
														<font color="#FF0000">*</font>
													</td>
													<td width="33%">
														<html:text name="ratemngAF" onkeydown="enterNextFocus1();"
														onblur="return checkCurRate();"	property="hafInterestRate.curRate" styleClass="input3" />
													</td>
												</tr>
                                                 <tr>
													<td width="17%" class="td1">
														活期是否分段记息
														<font color="#FF0000">*</font>
													</td>
													<td width="33%">
													 <html:select property="hafInterestRate.isSubDemand" styleClass="input4" onkeydown="enterNextFocus1();">
												             <html:option  value="1" > 是 </html:option>
									                         <html:option  value="2" >否</html:option>
											            </html:select>
													</td>
													<td width="17%" class="td1">
														定期是否分段记息
														<font color="#FF0000">*</font>
													</td>
													<td width="33%">
													<html:select property="hafInterestRate.isSubtRegular" styleClass="input4" onkeydown="enterNextFocus1();">
												             <html:option  value="1" >是 </html:option>
									                         <html:option  value="2" >否</html:option>
											            </html:select>
													</td>
												</tr>





											</table>
											<table border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td>
														<html:submit property="method" styleClass="buttona" onclick="return checkData()">
															<bean:message key="button.add" />
														</html:submit>
													</td>
													<td>
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.back" />
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
			</td>
		</tr>
	</table>
</body>
</html:html>
