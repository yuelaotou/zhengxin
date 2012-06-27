<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>

<%
String path = request.getContextPath();
String update = (String)request.getAttribute("update");
if(update==null)
update = "insert";
%>

<html:html>
<head>
	<title>办理缴存基数，比例，补缴</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
	<script src="<%=path%>/js/common.js"></script>
	<script type="text/javascript">
var new_orgrate="";//新单位缴率
var new_emprate="";//新职工缴率

function toPop() {
	gotoOrgpop(2,'<%=path%>','0');
}

function executeAjax(){
	var x = document.forms[0].elements["orgid"].value.trim();
	if(x==""){
		toPop();
	}else if(isNaN(x)){
        	alert("请输入正确格式的编号！");
        	 document.forms[0].elements["orgid"].value.focus();
        	return false;
    }else{
	    var queryString = "orgChgAAC.do?";
	    queryString = queryString + "orgID="+ document.forms[0].elements["orgid"].value.trim();
		findInfo(queryString);
    }
}

function reportErrors() {
	document.forms[0].elements["orgid"].focus();
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	document.all.update.value="<%=update%>";
}

function findEmployeeInfo(){
	var id=document.forms[0].elements["orgid"].value;
	if(id==""){
		toPop();
	}else{
		if(isNaN(id)){
        	alert("请输入正确格式的编号！");
        	document.forms[0].elements["orgid"].value.focus();
        	return false;
	    }else{
			var queryString = "orgChgAAC.do?";
	    	queryString = queryString + "orgID="+ document.forms[0].elements["orgid"].value.trim();
	    	findInfo(queryString);
		}
	
	}
}

function goDBClick(){
	var id=document.forms[0].elements["orgid"].value;
	if(id==""){
		toPop();
	}else{
		var queryString = "orgChgAAC.do?";
    	queryString = queryString + "orgID="+ document.forms[0].elements["orgid"].value.trim();
    	findInfo(queryString);
	}
}

function displays(orgid,orgname,orgaddress,orgdep,orgcharacter,orgtransactorname,orgtransactortelephone,orgcount,preRate,prePay){
	document.forms[0].elements["orgid"].value=orgid;
	document.forms[0].elements["orgname"].value=orgname;
	document.forms[0].elements["orgaddress"].value=orgaddress;
	document.forms[0].elements["orgdep"].value=orgdep;
	document.forms[0].elements["orgcharacter"].value=orgcharacter;
	document.forms[0].elements["orgtransactorname"].value=orgtransactorname;
	document.forms[0].elements["orgtransactortelephone"].value=orgtransactortelephone;
	document.forms[0].elements["orgcount"].value=orgcount;
	document.forms[0].elements["orgChg.preRate"].value=preRate;
	document.forms[0].elements["orgChg.prePay"].value=prePay;
}
function backErrors(m){
	alert(m);
	document.forms[0].elements["orgid"].value="";
	document.forms[0].elements["orgname"].value="";
	document.forms[0].elements["orgaddress"].value="";
	document.forms[0].elements["orgdep"].value="";
	document.forms[0].elements["orgcharacter"].value="";
	document.forms[0].elements["orgtransactorname"].value="";
	document.forms[0].elements["orgtransactortelephone"].value="";
	document.forms[0].elements["orgcount"].value="";
	document.forms[0].elements["orgChg.preRate"].value="0";
	document.forms[0].elements["orgChg.prePay"].value="0";
	document.forms[0].elements["orgid"].focus();
}

function check(){
	var orgname=document.forms[0].elements["orgname"].value.trim();
	if(orgname==""){
		alert("单位不能为空！！");
		return false;
	}
	if(!checkRate(document.forms[0].elements["orgChg.newRate"].value.trim())){
	 	alert("调整后月缴存比例输入格式错误!必须小于1,小数点保留两位.");
	 	document.forms[0].elements["orgChg.newRate"].focus();
	 	return false;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors();">
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
										background="<%=path%>/img/buttonbl.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										办理变更
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="orgChgForwardTbAC.do" class=a2>变更维护</a>
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
									<td width="216" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">变更业务</font>
									</td>
									<td width="47" class=font14>
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
				<br>
				<html:form action="/orgChgSaveAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="137">
											<b class="font14">单 位 基 本 信 息</b>
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
					<table border="0" width="95%" cellspacing=1 cellpadding=3
						align="center">
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="22%">
								<html:text name="orgChgAF" property="orgid" onkeydown="goEnter();"
									ondblclick="goDBClick();" maxlength="20" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="22%">
								<input type="button" class="buttona" onclick="toPop()"
									value="..." />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="22%">
								<html:text name="orgChgAF" property="orgname" readonly="true" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								单位地址
							</td>
							<td width="22%">
								<html:text name="orgChgAF" property="orgaddress" readonly="true" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								经办部门
							</td>
							<td width="22%">
								<html:text name="orgChgAF" property="orgdep" readonly="true" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								所属性质
							</td>
							<td width="22%">
								<html:text name="orgChgAF" property="orgcharacter" readonly="true"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								联系人
							</td>
							<td width="22%">
								<html:text name="orgChgAF" property="orgtransactorname" readonly="true"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								联系电话
							</td>
							<td width="22%">
								<html:text name="orgChgAF" property="orgtransactortelephone" readonly="true"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								在职职工人数
							</td>
							<td width="22%">
								<html:text name="orgChgAF" property="orgcount" readonly="true" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">调整缴存比例</b>
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
					<table border="0" width="95%" id="table1" cellspacing=0
						cellpadding=0 align="center">
						<tr >
							<td >
								<table border="0" width="100%" cellspacing=1 height="100%"
									cellpadding=3 align="center" style="">
									<tr >
										<td class="td1" align="center" width="33%">
											调整前月缴存比例%
										</td>
										<td class="td1" align="center" width="33%">
											调整后月缴存比例%
										</td>
										<td class="td1" align="center" width="33%">
											调整后月缴存差额
										</td>
									</tr>
									<tr >
										<td align="center" width="33%">
											<html:text name="orgChgAF" property="orgChg.preRate"
												readonly="true" maxlength="20" styleClass="input3"
												styleId="txtsearch"></html:text>
										</td>
										<td align="center" width="33%">
											<html:text name="orgChgAF" property="orgChg.newRate"
												maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td align="center" width="33%">
											<html:text name="orgChgAF" property="orgChg.payRate"
												maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">调整工资基数</b>
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
					<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr >
							<td >
								<table border="0" width="100%" cellspacing=1 height="100%"
									cellpadding=3 align="center">
									<tr>
										<td class="td1" align="center" width="33%">
											调整前应缴额
										</td>
										<td class="td1" align="center" width="33%">
											调整后应缴额
										</td>
										<td class="td1" align="center" width="33%">
											调整后月缴额差额
										</td>
									</tr>
									<tr>
										<td align="center" width="33%" >
											<html:text name="orgChgAF" property="orgChg.prePay"
												readonly="true" maxlength="20" styleClass="input3"
												styleId="txtsearch"></html:text>
										</td>
										<td align="center" width="33%">
											<html:text name="orgChgAF" property="orgChg.newPay"
												maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td align="center" width="33%">
											<html:text name="orgChgAF" property="orgChg.paySalary"
												maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">补缴</b>
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
					<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr >
							<td >
								<table border="0" width="100%" cellspacing=1 height="100%"
									cellpadding=3 align="center">
									<tr>
										<td class="td1" align="center" width="15%">
											补缴时间
										</td>
										<td class="td1" align="center" width="20%">
											补缴起止月份
										</td>
										<td class="td1" align="center" width="15%">
											补缴人数
										</td>
										<td class="td1" colspan="3" width="50%" >
											<table width="100%" height="56" cellspacing=1 cellpadding=3 border="0">
												<tr >
													<td colspan="3" align="center">
														补缴金额
													</td>
												</tr>
												<tr>
													<td align="center">
														合计
													</td>
													<td align="center">
														个人
													</td>
													<td align="center">
														单位
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td align="center">
											<html:text name="orgChgAF" property="orgChg.addMonth"
												maxlength="8" styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td align="center">
											<html:text name="orgChgAF" property="orgChg.addStEndMonth"
												maxlength="50" styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td align="center">
											<html:text name="orgChgAF" property="orgChg.addCount"
												maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td align="center">
											<html:text name="orgChgAF" property="orgChg.addSum"
												maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td align="center">
											<html:text name="orgChgAF" property="orgChg.addEmp"
												maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td align="center">
											<html:text name="orgChgAF" property="orgChg.addOrg"
												maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
											<input type="hidden" name="update">
											<html:hidden name="orgChgAF" property="orgChg.id"/>
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
											<html:submit styleId="but1" property="method"
												styleClass="buttona" onclick="return check();">
												<bean:message key="button.sure" />
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
