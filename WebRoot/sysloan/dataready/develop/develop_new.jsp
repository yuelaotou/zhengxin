<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page
	import="org.xpup.hafmis.sysloan.dataready.develop.form.DevelopNewAF"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	DevelopNewAF developNewAF = (DevelopNewAF) request
			.getAttribute("developNewAF");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

	<title>开发商维护</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
	<script language="javascript" src="<%=path%>/js/common.js" />	
  
</head>
<script language="javascript" src="<%=path%>/js/common.js">
	var oldColor="#ffffff";                     //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;		//保存当前颜色
		oTD.style.backgroundColor=newColor;		//改变表格颜色
		newColor=oldColor;                 		//改变下次要变成的颜色
	}
	
</script>
<script>
	var s1="";
	var s2="";
	function reportErrors() {
		<logic:messagesPresent>
			var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
			alert(message);
		</logic:messagesPresent>
		for(i=0;i<document.all.length;i++){
			if(document.all.item(i).type=="submit"){
				if(document.all.item(i).value=="确定"){
					s1=i;
				}
			}
		}
		
		/*var developerName = document.forms[0].elements["developer.developerName"].value;
		if(developerName==null||developerName==""){
			document.all.item(s2).disabled="true";
		}*/
	}
	function initWindow(){
		var type = document.forms[0].elements["type_window"].value;
		if(type=='2'){
			document.forms[0].elements["developer.developerId"].readOnly=true;
			document.forms[0].elements["developer.developerName"].readOnly=true;
			document.forms[0].elements["developer.landUseId"].readOnly=true;
			document.forms[0].elements["developer.salePermis"].readOnly=true;
			document.forms[0].elements["developer.office"].disabled=true;
			document.forms[0].elements["developer.code"].readOnly=true;
			document.forms[0].elements["developer.agreementStartDate"].readOnly=true;
			document.forms[0].elements["developer.agreementEndDate"].readOnly=true;
			document.forms[0].elements["developer.artfclprsn"].readOnly=true;
			document.forms[0].elements["developer.artfclprsnTel"].readOnly=true;
			document.forms[0].elements["developer.developerAddr"].readOnly=true;
			document.forms[0].elements["developer.contactPrsnA"].readOnly=true;
			document.forms[0].elements["developer.contactTelA"].readOnly=true;
			document.forms[0].elements["developer.contactMobileA"].readOnly=true;
			document.forms[0].elements["developer.businessA"].readOnly=true;
			document.forms[0].elements["developer.contactPrsnB"].readOnly=true;
			document.forms[0].elements["developer.contactTelB"].readOnly=true;
			document.forms[0].elements["developer.contactMobileB"].readOnly=true;
			document.forms[0].elements["developer.businessB"].readOnly=true;
			document.forms[0].elements["developer.contactPrsnC"].readOnly=true;
			document.forms[0].elements["developer.contactTelC"].readOnly=true;
			document.forms[0].elements["developer.contactMobileC"].readOnly=true;
			document.forms[0].elements["developer.businessC"].readOnly=true;
			document.forms[0].elements["developer.workAddr"].readOnly=true;
			document.forms[0].elements["developer.postalCode"].readOnly=true;
			document.forms[0].elements["developer.orgTel"].readOnly=true;
			document.forms[0].elements["developer.region"].readOnly=true;
			document.forms[0].elements["developer.developerPaybankA"].readOnly=true;
			document.forms[0].elements["developer.developerPaybankAAcc"].readOnly=true;
			document.forms[0].elements["developer.developerPaybankB"].readOnly=true;
			document.forms[0].elements["developer.developerPaybankBAcc"].readOnly=true;
			document.forms[0].elements["developer.remark"].readOnly=true;
			document.forms[0].elements["developer.registerFundNumber"].readOnly=true;
		}
	}
	function check(){
		var developerId = document.forms[0].elements["developer.developerId"].value.trim();
		var developerName = document.forms[0].elements["developer.developerName"].value.trim();
		var office = document.forms[0].elements["developer.office"].value.trim();
		var developerPaybankA = document.forms[0].elements["developer.developerPaybankA"].value.trim();
		var developerPaybankAAcc = document.forms[0].elements["developer.developerPaybankAAcc"].value.trim();
		var remark = document.forms[0].elements["developer.remark"].value.trim();
		var postalCode = document.forms[0].elements["developer.postalCode"].value.trim();
		
		
		var artfclprsnTel = document.forms[0].elements["developer.artfclprsnTel"].value.trim();
		var contactTelA = document.forms[0].elements["developer.contactTelA"].value.trim();
		var contactTelB = document.forms[0].elements["developer.contactTelB"].value.trim();
		var contactTelC = document.forms[0].elements["developer.contactTelC"].value.trim();
		var contactMobileA = document.forms[0].elements["developer.contactMobileA"].value.trim();
		var contactMobileB = document.forms[0].elements["developer.contactMobileB"].value.trim();
		var contactMobileC = document.forms[0].elements["developer.contactMobileC"].value.trim();
		var orgTel = document.forms[0].elements["developer.orgTel"].value.trim();
		
		var artfclprsn = document.forms[0].elements["developer.artfclprsn"].value.trim();
		var contactPrsnA = document.forms[0].elements["developer.contactPrsnA"].value.trim();
		var contactPrsnB = document.forms[0].elements["developer.contactPrsnB"].value.trim();
		var contactPrsnC = document.forms[0].elements["developer.contactPrsnC"].value.trim();
		
		var registerFundNumber = document.forms[0].elements["developer.registerFundNumber"].value.trim();
		
		if(developerId==null || developerId==""){
			alert("请输入开发商代码！");
			return false;
		}else if(developerName==null || developerName==""){
			alert("请输入开发商名称！");
			return false;
		}else if(office==null || office==""){
			alert("请输入所属办事处!");
			return false;
		}else if(!isCHorEN(contactPrsnA) || !isCHorEN(contactPrsnB) || !isCHorEN(contactPrsnC)){
			alert("联系人姓名输入错误!");
			return false;
		}else if(developerPaybankA==null || developerPaybankA==""){
			alert("请输入开户银行！");
			return false;
		}else if(developerPaybankAAcc==null || developerPaybankAAcc==""){
			alert("请输入开户行账号！");
			return false;
		}else if(!isNumber(postalCode)){
			alert("邮政编码输入错误!");
			return false;
		}else if(remark.length>50){
			alert("备注字符过长！");
			return false;
		}

	}
	function checkAgreementStartDate(){
		var agreementStartDate = document.forms[0].elements["developer.agreementStartDate"].value.trim();
		if(agreementStartDate!=null && agreementStartDate!=""){
			if(isNumber(agreementStartDate)){
				 if(!checkDate(document.forms[0].elements["developer.agreementStartDate"])){
				 	document.forms[0].elements["developer.agreementStartDate"].focus();
				 	return false;
				 }
			}else{
				alert("请输入八位的日期格式，例如20070101！");
				document.forms[0].elements["developer.agreementStartDate"].focus();
			}
		}
	}
	function checkAgreementEndDate(){
		var agreementStartDate = document.forms[0].elements["developer.agreementEndDate"].value.trim();
		if(agreementStartDate!=null && agreementStartDate!=""){
			if(isNumber(agreementStartDate)){
				if(!checkDate(document.forms[0].elements["developer.agreementEndDate"])){
				 	document.forms[0].elements["developer.agreementStartDate"].focus();
				 	return false;
				 }
			}else{
				alert("请输入八位的日期格式，例如20070101！");
				document.forms[0].elements["developer.agreementEndDate"].focus();
			}
		}
	}
	function isRegisterUserName(s){
		var patrn=/^[a-zA-Z0-9]{1}([a-zA-Z0-9]){0,100}$/;
		if (!patrn.exec(s)) return false
		return true
	}

</script>
<body bgcolor="#FFFFFF" text="#000000"
	onload="reportErrors();initWindow();" >
	<html:form method="post" action="/developTaMainTainAC">
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
										<logic:empty name="developNewAF" property="type_window">
											<td width="112" height="37"
												background="<%=path%>/img/buttonbl.gif" align="center"
												valign="top" style="PADDING-top: 7px">
												添加开发商
											</td>
										</logic:empty>
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
										<td width="165" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">数据准备&gt;开发商维护</font>
										</td>
										<td width="98" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="166">
											<b class="font14">开发商信息录入</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="738">
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
							<td width="19%" class="td1">
								开发商编号
								<font color="#FF0000">*</font>
							</td>
							<logic:empty name="developNewAF" property="type_window">
								<td width="31%" height="31">
									<html:text name="developNewAF" property="developer.developerId"
										styleClass="input3" onkeydown="enterNextFocus1();"
										maxlength="100"></html:text>
								</td>
							</logic:empty>
							<logic:notEmpty name="developNewAF" property="type_window">
								<td width="31%" height="31">
									<html:text name="developNewAF" property="developer.developerCode"
										styleClass="input3" onkeydown="enterNextFocus1();"
										maxlength="100"></html:text>
								</td>
							</logic:notEmpty>
							<td width="17%" class=td1>
								开发商名称
								<font color="#FF0000">*</font>
							</td>
							<td width="33%" height="31">
								<html:text name="developNewAF"
									property="developer.developerName" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								国有土地使用权证编号
							</td>
							<td width="31%">
								<html:text name="developNewAF" property="developer.landUseId"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
							<td width="17%" class="td1">
								商品房销售许可证号
							</td>
							<td width="33%">
								<html:text name="developNewAF" property="developer.salePermis"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								所属办事处
								<font color="#FF0000">*</font>
							</td>
							<td width="31%">
								<html:select name="developNewAF" property="developer.office"
									styleClass="input4" onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:options collection="officeList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								组织机构代码
							</td>
							<td width="33%">
								<html:text name="developNewAF" property="developer.code"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								协议签订日期(8位数字)
							</td>
							<td width="31%">
								<html:text name="developNewAF"
									property="developer.agreementStartDate" styleClass="input3"
									onkeydown="enterNextFocus1();"
									onblur="return checkAgreementStartDate();" maxlength="8"></html:text>
							</td>
							<td width="17%" class="td1">
								协议到期日期(8位数字)
							</td>
							<td width="33%">
								<html:text name="developNewAF"
									property="developer.agreementEndDate" styleClass="input3"
									onkeydown="enterNextFocus1();"
									onblur="return checkAgreementEndDate();" maxlength="8"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								法人代表
							</td>
							<td width="31%">
								<html:text name="developNewAF" property="developer.artfclprsn"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
							<td width="17%" class="td1">
								法人代表联系电话
							</td>
							<td width="33%">
								<html:text name="developNewAF"
									property="developer.artfclprsnTel" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								开发商地址
							</td>
							<td width="31%">
								<html:text name="developNewAF"
									property="developer.developerAddr" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
							<td width="17%" class="td1">
								企业注册资金(万元)
							</td>
							<td width="33%">
								<html:text name="developNewAF"
									property="developer.registerFundNumber" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								联系人
							</td>
							<td width="31%">
								<html:text name="developNewAF" property="developer.contactPrsnA"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
							<td width="17%" class="td1">
								联系电话
							</td>
							<td width="33%">
								<html:text name="developNewAF" property="developer.contactTelA"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								联系手机
							</td>
							<td width="31%">
								<html:text name="developNewAF"
									property="developer.contactMobileA" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
							<td width="17%" class="td1">
								职务
							</td>
							<td width="33%">
								<html:text name="developNewAF" property="developer.businessA"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" bgcolor="#D1EDD7">
								联系人
							</td>
							<td width="31%">
								<html:text name="developNewAF" property="developer.contactPrsnB"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
							<td width="17%" bgcolor="#D1EDD7">
								联系电话
							</td>
							<td width="33%">
								<html:text name="developNewAF" property="developer.contactTelB"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" bgcolor="#D1EDD7">
								联系手机
							</td>
							<td width="31%">
								<html:text name="developNewAF"
									property="developer.contactMobileB" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
							<td width="17%" bgcolor="#D1EDD7">
								职务
							</td>
							<td width="33%">
								<html:text name="developNewAF" property="developer.businessB"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" bgcolor="#F8EDB8">
								联系人
							</td>
							<td width="31%">
								<html:text name="developNewAF" property="developer.contactPrsnC"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
							<td width="17%" bgcolor="#F8EDB8">
								联系电话
							</td>
							<td width="33%">
								<html:text name="developNewAF" property="developer.contactTelC"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" bgcolor="#F8EDB8">
								联系手机
							</td>
							<td width="31%">
								<html:text name="developNewAF"
									property="developer.contactMobileC" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
							<td width="17%" bgcolor="#F8EDB8">
								职务
							</td>
							<td width="33%">
								<html:text name="developNewAF" property="developer.businessC"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								办公地址
							</td>
							<td width="31%">
								<html:text name="developNewAF" property="developer.workAddr"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
							<td width="17%" class="td1">
								邮政编码
							</td>
							<td width="33%">
								<html:text name="developNewAF" property="developer.postalCode"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td class="td1" width="19%">
								单位电话
							</td>
							<td width="31%">
								<html:text name="developNewAF" property="developer.orgTel"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
							<td class="td1" width="17%">
								所在地区
							</td>
							<td width="33%">
								<html:text name="developNewAF" property="developer.region"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td class="td1" width="19%">
								开户银行
								<font color="#FF0000">*</font>
							</td>
							<td width="31%">
								<html:text name="developNewAF"
									property="developer.developerPaybankA" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
							<td class="td1" width="17%">
								开户行账号
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="developNewAF"
									property="developer.developerPaybankAAcc" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td class="td1" width="19%">
								开户银行
							</td>
							<td width="31%">
								<html:text name="developNewAF"
									property="developer.developerPaybankB" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
							<td class="td1" width="17%">
								开户行账号
							</td>
							<td width="33%">
								<html:text name="developNewAF"
									property="developer.developerPaybankBAcc" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td class="td1" width="17%">
								特殊开发商
							</td>
							<td width="33%">
								<html:select property="developer.reserveaB" name="developNewAF"
									styleClass="input3" onkeydown="enterNextFocus1();">
									<html:option value="1">否</html:option>
									<html:option value="0">是</html:option>
								</html:select>
							</td>
							<html:hidden name="developNewAF" property="type_window" />
							<html:hidden name="developNewAF" property="type_button" />
						</tr>
						<tr>
							<td class="td1" width="19%">
								备注
							</td>
							<td colspan="3">
								<html:textarea name="developNewAF" property="developer.remark"
									rows="3" style="width=95%;"></html:textarea>
							</td>
							<html:hidden name="developNewAF" property="type_window" />
							<html:hidden name="developNewAF" property="type_button" />
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<logic:empty name="developNewAF" property="type_window">
										<logic:notEmpty name="developNewAF" property="type_button">
											<tr>
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														onclick="return check();">
														<bean:message key="button.edit" />
													</html:submit>
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.scan" />
													</html:submit>
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.back" />
													</html:submit>
												</td>
												<td width="33%" colspan="2">
													<a
														href='javascript:excHz("<bean:write name="developNewAF" property="developer.photoUrl"/>");'>浏览证书</a>
												</td>

											</tr>
										</logic:notEmpty>
										<logic:empty name="developNewAF" property="type_button">
											<tr>
												<td width="70">
													<html:submit property="method" styleClass="buttona"
														onclick="return check();">
														<bean:message key="button.sure" />
													</html:submit>
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.scan" />
													</html:submit>
												</td>
												<td width="70">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.back" />
													</html:submit>
												</td>

												<td width="33%" colspan="2">
													<a
														href='javascript:excHz("<bean:write name="developNewAF" property="developer.photoUrl"/>");'>浏览证书</a>
												</td>

											</tr>
										</logic:empty>
									</logic:empty>
									<logic:notEmpty name="developNewAF" property="type_window">
										<tr>
											<td width="70">
												<input type="button" name="Submit42" value="关闭"
													class="buttona" onClick="javascript:window.close();">
											</td>
										</tr>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
