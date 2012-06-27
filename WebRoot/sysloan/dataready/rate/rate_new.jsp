<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
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
function checkYearRate(){
var temprate=document.rateNewAF.elements["loanYearRate"];
var rate=temprate.value;
var salarybase = rate.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/);
if (salarybase==null)
			{	
        		alert("请正确录入年利率！格式如：1.23");
        		temprate.focus();
				return false;
			}else{
			if(temprate.value==0.00){
			 alert('年利率不能为0，请您添加');
        		temprate.focus();
        		return false;
			}else
				return true;
			}
}
function checkdate(){
var temprate=document.rateNewAF.elements["loanYearRate"];
var rate=temprate.value;
var salarybase = rate.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/);
if (salarybase==null)
			{	
        		alert("请正确录入年利率！格式如：1.23");
        		temprate.focus();
				return false;
			}else{
			if(temprate.value==0){
			    alert('年利率不能为0，请您添加');
        		temprate.focus();
        		return false;
			}else{
				return true;
			}
			}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors();" onContextmenu="return false">
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
							<table width="94%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">利 率 新 增</b>
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
				<html:form action="/rateTaNewAC.do" style="margin: 0">
				<html:hidden name="rateNewAF" property="rateId"></html:hidden>
				<html:hidden name="rateNewAF" property="loanRateTypeUpdate"></html:hidden>
				<html:hidden name="rateNewAF" property="loanRateOfficeUpdate"></html:hidden>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=3 align="center">
									<tr id="tr1">
										<td width="17%" class="td1">
											贷款类型
											<font color="#FF0000">*</font>
										</td>
										<td width="33%">
										<logic:equal name="rateNewAF" property="type" value="1">
											 <html:select name="rateNewAF" property="loanRateType" styleClass="input4" onkeydown="enterNextFocus1();">
									         	<html:options  collection="rateTypeList1" property="value" labelProperty="label"/>
								             </html:select>
							         	</logic:equal>
							         	<logic:equal name="rateNewAF" property="type" value="2">
										 	<html:select name="rateNewAF" property="loanRateType" styleClass="input4" disabled="true">
								              	<html:options  collection="rateTypeList1" property="value" labelProperty="label"/>
							              	</html:select>
								        </logic:equal>
										</td>
										<td width="17%" class="td1">
											年利率%
											<font color="#FF0000">*</font>
										</td>
										<td width="33%">
										<html:text  onkeydown="enterNextFocus1();" name="rateNewAF"
														onblur="return checkYearRate();"	property="loanYearRate" styleClass="input3" />
										</td>
									</tr>
									<tr id="tr1">
										<td width="17%" class="td1">
											所属办事处
											<font color="#FF0000">*</font>
										</td>
										<td width="33%">
										<logic:equal name="rateNewAF" property="type" value="1">
										     <html:select name="rateNewAF" property="office" styleClass="input4" onkeydown="enterNextFocus1();">
													 <html:option value="all">全部</html:option>
													 <html:options  collection="list" property="value" labelProperty="label"/>
											 </html:select>	
										
										</logic:equal>
										<logic:equal name="rateNewAF" property="type" value="2">
										     <html:select name="rateNewAF" property="office" styleClass="input4"  disabled="true">
													 <html:option value="office"><bean:write name="rateNewAF" property="officeName" /></html:option>
											 </html:select>	
										</logic:equal>
										</td>
										<td width="17%" class="td1">
											调整依据
										</td>
										<td width="33%">
										<html:text onkeydown="enterNextFocus1();" name="rateNewAF" property="adjustBasis" styleClass="input3" />
										</td>
									</tr>
									<tr id="tr1">
										<td width="17%" class="td1">
											调整日期
											<font color="#FF0000">*</font>
										</td>
										<td width="33%">
										<logic:equal name="rateNewAF" property="type" value="1">
											<html:text onkeydown="enterNextFocus1();" name="rateNewAF" property="adjustDate" styleClass="input3" />
										</logic:equal>
										<logic:equal name="rateNewAF" property="type" value="2">
											<html:text onkeydown="enterNextFocus1();" name="rateNewAF" 
												property="adjustDate" styleClass="input3" readonly="true" />
										</logic:equal>
										</td>
										<td></td><td></td>
									</tr>
								</table>
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
									<td width="70">
											<logic:equal name="rateNewAF" property="type" value="1">
												<html:submit property="method" styleClass="buttona" onclick="return checkdate()">
													<bean:message key="button.add" />
												</html:submit>
											</logic:equal>
											<logic:equal name="rateNewAF" property="type" value="2">
												<html:submit property="method" styleClass="buttona" onclick="return checkdate()">
													<bean:message key="button.update" />
												</html:submit>
											</logic:equal>
										</td>
										<td width="70">
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
</body>
</html:html>
