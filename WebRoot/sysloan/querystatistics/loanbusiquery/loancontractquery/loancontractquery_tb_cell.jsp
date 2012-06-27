<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>

<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
		<script src="<%=path%>/js/common.js">
		</script>
		
		<script language="JavaScript">


</script>
	</head>

<script language="javascript">
var s1="";
function loads(){

for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		}
	} 
	 var name=document.loanapplytbnewAF.elements["name"].value
	if(name==""){
	document.all.item(s1).disabled="true";
	}
}

tr="tr0";


function executeAjaxIn(trindex) {
	tr=trindex;
	var assitId=getCheckId();
	var queryString = "loancontractqueryTbFindempInfoAAC.do?";
    queryString = queryString + "assitId="+assitId;    
	findInfo(queryString);	  
}


function displayIn(empId,name,relation,sex,cardKind,cardNum,birthday,age,nation,nativePlace,business,title,marriageSt,degree,homeAddr,homeMail,homeMobile,houseTel,orgId,orgName,orgTel,orgAddr,orgMail,accBlnce,monthSalary,monthPay,empSt,bgnDate,endDate){		
				
		document.loanapplytbnewAF.elements["empId"].value=empId;
		document.loanapplytbnewAF.elements["name"].value=name;
		document.loanapplytbnewAF.elements["relation"].value=relation;
		document.loanapplytbnewAF.elements["sex"].value=sex;
		document.loanapplytbnewAF.elements["cardKind"].value=cardKind;
		document.loanapplytbnewAF.elements["cardNum"].value=cardNum;
		document.loanapplytbnewAF.elements["birthday"].value=birthday;
		document.loanapplytbnewAF.elements["age"].value=age;
		document.loanapplytbnewAF.elements["nation"].value=nation
		document.loanapplytbnewAF.elements["nativePlace"].value=nativePlace;
		document.loanapplytbnewAF.elements["business"].value=business;
		document.loanapplytbnewAF.elements["title"].value=title;
		document.loanapplytbnewAF.elements["marriageSt"].value=marriageSt;
		document.loanapplytbnewAF.elements["degree"].value=degree;
		document.loanapplytbnewAF.elements["homeAddr"].value=homeAddr;
		document.loanapplytbnewAF.elements["homeMail"].value=homeMail;
		document.loanapplytbnewAF.elements["homeMobile"].value=homeMobile;
		document.loanapplytbnewAF.elements["houseTel"].value=houseTel;
		document.loanapplytbnewAF.elements["orgId"].value=orgId;
		document.loanapplytbnewAF.elements["orgName"].value=orgName;
		document.loanapplytbnewAF.elements["orgTel"].value=orgTel;
		document.loanapplytbnewAF.elements["orgAddr"].value=orgAddr;
		document.loanapplytbnewAF.elements["orgMail"].value=orgMail;
		document.loanapplytbnewAF.elements["accBlnce"].value=accBlnce;
		document.loanapplytbnewAF.elements["monthSalary"].value=monthSalary;
		document.loanapplytbnewAF.elements["monthPay"].value=monthPay;
		document.loanapplytbnewAF.elements["empSt"].value=empSt;
		document.loanapplytbnewAF.elements["bgnDate"].value=bgnDate;
		document.loanapplytbnewAF.elements["endDate"].value=endDate;	
}

function gototbPrint(){
location='loancontractqueryTbPrintAC.do';
}
</script>

	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return true" onload="loads();">
	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="3%" align="right" valign="middle">
					<table width="21" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryTeShowAC.do" class=a2>借款合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryTfShowAC.do" class=a2>抵押合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryTgShowAC.do" class=a2>质押合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryThShowAC.do" class=a2>保证人信息</a>
							</td>
						</tr>
					</table>
				</td>
				<td width="97%" align="left" valign="top">
					<table width="98%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="<%=path%>/img/table_left.gif" width="7" height="37">
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" width="10">
								&nbsp;
							</td>
							<td background="<%=path%>/img/table_bg_line.gif">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											<a href="<%=path%>/sysloan/loancontractqueryTaShowAC.do" class=a2>借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loancontractqueryTbShowAC.do" class=a2>共同借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loancontractqueryTcShowAC.do" class=a2>购房信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loancontractqueryTdShowAC.do" class=a2>借款人额度信息</a>
										</td>
										<td width="112" height="37" align="center"
											style="PADDING-top: 7px" valign="top"></td>
									</tr>
								</table>
							</td>
							<td width="9">
								<img src="<%=path%>/img/table_right.gif" width="9" height="37">
							</td>
						</tr>
					</table>

					<table width="98%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td valign="top" class=td3>
								<html:form action="loancontractqueryTbPrintAC">
									<table width="95%" border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
											<td height="35">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="22" bgcolor="#CCCCCC" align="center"
															width="430">
															<b class="font14">共 同 借 款 人 基 本 信 息</b>
														</td>
														<td height="22" background="<%=path%>/img/bg2.gif"
															align="center" width="651">
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
											<td class=td1 width="12%">
												合同编号
											</td>
											<td class="td4">
												<html:text property="contractId" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td class="td1" width="12%">
												借款人姓名
											</td>
											<td class="td4" width="17%">
												<html:text property="borrowerName" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1 width="12%">
												共同借款人状态
											</td>
											<td class="td4" width="30%">
												<html:text property="relationStatus" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td class=td1 width="12%">
												职工编号
											</td>
											<td class="td4">
												<html:text property="empId" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td class="td1" width="12%">
												职工姓名
											</td>
											<td class="td4" width="17%">
												<html:text property="name" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1 width="12%">
												与借款人关系
											</td>
											<td class="td4" width="30%">
												<html:select name="loanapplytbnewAF" property="relation" disabled="true"
													styleClass="input4" 
													onkeydown="enterNextFocus1();">
													<html:optionsCollection property="relationMap"
														name="loanapplytbnewAF" label="value" value="key" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td class=td1>
												性别
											</td>
											<td class="td4">
												<html:text name="loanapplytbnewAF" property="sex"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1>
												证件类型
											</td>
											<td class="td4">
												<html:text name="loanapplytbnewAF" property="cardKind"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1>
												证件号码
											</td>
											<td class="td4">
												<html:text property="cardNum" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												出生日期
											</td>
											<td class="td4">
												<html:text property="birthday" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												年龄
											</td>
											<td class="td4" width="17%">
												<html:text property="age" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												民族
											</td>
											<td class="td4" width="30%">
												<html:text property="nation" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												户籍所在地
											</td>
											<td class="td4">
												<html:text property="nativePlace" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												职务
											</td>
											<td class="td4" width="17%">
												<html:text property="business" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												职称
											</td>
											<td class="td4" width="30%">
												<html:text property="title" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												婚姻状况
											</td>
											<td class="td4">
												<html:text property="marriageSt" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												文化程度
											</td>
											<td class="td4" width="17%">
												<html:text property="degree" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												家庭住址
											</td>
											<td class="td4" width="30%">
												<html:text property="homeAddr" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												邮政编码
											</td>
											<td class="td4">
												<html:text property="homeMail" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												移动电话
											</td>
											<td class="td4" width="17%">
												<html:text property="homeMobile" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												住宅电话
											</td>
											<td class="td4" width="30%">
												<html:text property="houseTel" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
									</table>
									<table width="95%" border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
											<td height="35">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="22" bgcolor="#CCCCCC" align="center"
															width="370">
															<b class="font14">共 同 借 款 人 公 积 金 归 集 信 息</b>
														</td>
														<td height="22" background="<%=path%>/img/bg2.gif"
															align="center" width="581">
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
											<td width="12%" class=td1>
												单位编号
											</td>
											<td class="td4" colspan="2">
												<html:text property="orgId" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												单位名称
											</td>
											<td class="td4" width="17%">
												<html:text property="orgName" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												单位电话
											</td>
											<td class="td4" width="30%">
												<html:text property="orgTel" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												单位地址
											</td>
											<td class="td4" colspan="2">
												<html:text property="orgAddr" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												邮政编码
											</td>
											<td class="td4" width="17%">
												<html:text property="orgMail" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												账户余额
											</td>
											<td class="td4" width="30%">
												<html:text property="accBlnce" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												月工资额
											</td>
											<td class="td4" colspan="2">
												<html:text property="monthSalary" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												月缴存额
											</td>
											<td class="td4" width="17%">
												<html:text property="monthPay" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												账户状态
											</td>
											<td class="td4" width="30%">
												<html:text property="empSt" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												初缴年月
											</td>
											<td class="td4" colspan="2">
												<html:text property="bgnDate" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												缴至年月
											</td>
											<td class="td4" width="17%">
												<html:text property="endDate" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												&nbsp;
											</td>
											<td class="td4" width="30%">
												<input type="text" class="input3" readonly="true">
											</td>
										</tr>
									</table>
							</html:form>
								<form name="idAF" style="margin: 0">
									<table width="95%" border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
											<td height="35">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="22" bgcolor="#CCCCCC" align="center"
															width="430">
															<b class="font14">辅助借款人信息列表</b>
														</td>
														<td width="703" height="22" align="center"
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
									<table width="95%" border="0" cellspacing="0" cellpadding="3"
										align="center">
										<tr>
											<td align="center" height="23" bgcolor="C4F0FF">
												&nbsp;
											</td>
											<td align="center" class=td2>
												职工编号
												<br>
											</td>
											<td align="center" class=td2>
												职工姓名
											</td>
											<td align="center" class=td2>
												账户状态
											</td>
											<td align="center" class=td2>
												账户余额
											</td>
											<td align="center" class=td2>
												月工资额
											</td>
											<td align="center" class=td2>
												月缴存额
											</td>
											<td align="center" class=td2>
												单位名称
											</td>
											<td align="center" class=td2>
												状态
											</td>
										</tr>
										<logic:notEmpty name="loanapplytbnewAF" property="list">
											<logic:iterate name="loanapplytbnewAF" property="list"
												id="tail" indexId="i">
												<tr id="tr<%=i%>"
													onclick='gotoClick("tr<%=i%>","s<%=i%>",idAF);'
													onMouseOver='this.style.background="#eaeaea"'
													onMouseOut='gotoColor("tr<%=i%>","s<%=i%>", idAF);'
													class=td4 ondblclick='executeAjaxIn("tr<%=i%>");'>
													<td valign="top">
														<p align="left">
															<input id="s<%=i%>" type="radio" name="id"
																value="<bean:write name="tail" property="id"/>"
																<%if(new Integer(0).equals(i)) out.print("checked"); %> />
														</p>
													</td>
													<td valign="top">
														<bean:write name="tail" property="empid" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="empname" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="empst" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="accblnce" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="monthsalary" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="monthpay" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="orgname" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="status" />
													</td>
												</tr>
												<tr>
													<td colspan="11" class=td5></td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
										<logic:empty name="loanapplytbnewAF" property="list">
											<tr>
												<td colspan="16" height="30" style="color:red">
													没有找到与条件相符合的结果！
												</td>
											</tr>
											<tr>
												<td colspan="16" class=td5></td>
											</tr>
										</logic:empty>
										<tr>
											<td colspan="10" class=td5 height="37"></td>
										</tr>
									</table>
								</form>
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr class="td1">
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr valign="bottom">
										<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
											<table border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="73" align="center" valign="middle">
														<html:submit styleClass="buttona" onclick="gototbPrint();">
															<bean:message key="button.print" />
														</html:submit>
													</td>
											<td width="73" align="center" valign="middle">
												<input type="button" name="Submit3" value="关闭" class="buttona" onclick="window.close()"/>
											</td>
												</tr>
											</table>
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
</html>

