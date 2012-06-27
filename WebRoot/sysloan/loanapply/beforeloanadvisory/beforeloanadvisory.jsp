<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>

<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%
String path = request.getContextPath();
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">




function loads(){
}
function reportsErrors(){
	<logic:messagesPresent>
			var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
			alert(message);
	</logic:messagesPresent>	
}

function changes()
{
  	var empid=document.beforeLoanAdvisoryShowAF.elements["empId"].value.trim();
  	var orgid=document.beforeLoanAdvisoryShowAF.elements["empOrgid"].value.trim();
  	var totalPrice=document.beforeLoanAdvisoryShowAF.elements["houseTotalPrice"].value.trim();
  	if(empid!=""&&orgid!="")
  	{
    	var housetype=document.beforeLoanAdvisoryShowAF.elements["houseType"].value.trim();
    	var queryString = "queryLoanInfoAAC.do?";
    	queryString = queryString + "housetype="+housetype+"&totalPrice="+totalPrice;	
    	findInfo(queryString);	   
  	}
}
function  display(maxLoanMoney,maxLoanYear,monthBackMoney){
  	document.beforeLoanAdvisoryShowAF.elements["maxLoanMoney"].value=maxLoanMoney;
 	document.beforeLoanAdvisoryShowAF.elements["maxLoanYear"].value=maxLoanYear;
 	document.beforeLoanAdvisoryShowAF.elements["monthBackMoney"].value=monthBackMoney;
}
function queryInfo()
{
  	var inputSalary=document.beforeLoanAdvisoryShowAF.elements["inputSalary"].value.trim();
  	var housetype=document.beforeLoanAdvisoryShowAF.elements["houseType"].value.trim();
  	if(inputSalary!="")
  	{
    	var queryString="queryInputLoanInfoAAC.do?";
     	queryString = queryString + "inputSalary="+inputSalary+"&housetype="+housetype;
     	findInfo(queryString);
  	}
}


function dis(outloanyear,outloanmoney)
{
    document.beforeLoanAdvisoryShowAF.elements["outLoanYear"].value=outloanyear;
    document.beforeLoanAdvisoryShowAF.elements["outLoanMoney"].value=outloanmoney;
}
function skip(){
	var orgId=document.beforeLoanAdvisoryShowAF.elements["empOrgid"].value.trim();
	var empId =document.beforeLoanAdvisoryShowAF.elements["empId"].value.trim(); 
	window.open('<%=path%>/syscollection/querystatistics/operationflow/empoperationflow/empOperationFlowTaForwardURLAC.do?empId='+empId+'&orgId='+orgId,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',resizable,scrollbars=yes');
}
	
function check()
{
   	var  orgid=document.beforeLoanAdvisoryFindAF.elements["orgid"].value.trim();
   	var empid=document.beforeLoanAdvisoryFindAF.elements["empid"].value.trim();
   	if(orgid==""||empid=="")
   	{
      	alert("职工账号和单位编号不能为空！");
      	return false;
   	}
 
   	if(!isNumber(orgid))
   	{
      	alert("输入单位编号不是数字！");
      	return false;
   	}
   	if(!isNumber(empid))
   	{
     	alert("输入的职工账号不是数字！");
     	return false;
   	}
}	
</script>

	<body bgcolor="#FFFFFF" text="#000000"
		onload="loads();reportsErrors();">

		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="<%=path%>/img/table_left.gif" width="7" height="37">
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" width="10">
								&nbsp;
							</td>
							<td background="<%=path%>/img/table_bg_line.gif">
								<table border="0" cellspacing="0" cellpadding="0">
								</table>
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
											<font color="00B5DB">申请贷款&gt;贷前咨询</font>
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
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="157">
											<b class="font14">查询条件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="767">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/beforeLoanAdvisoryFindAC.do" style="margin: 0">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=0 align="center">
							<tr>
								<td width="17%" class="td1">
									职工账号
									<font color="#FF0000">*</font>
								</td>
								<td width="33%">
									<html:text property="empid" name="beforeLoanAdvisoryFindAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									职工姓名
								</td>
								<td width="33%">
									<html:text property="empName" name="beforeLoanAdvisoryFindAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									职工身份证号
								</td>
								<td width="33%">
									<html:text property="empCardNum" name="beforeLoanAdvisoryFindAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									单位编号
							      <font color="#FF0000">*</font>
								</td>
								<td width="33%">
									<html:text property="orgid" name="beforeLoanAdvisoryFindAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									单位名称
								</td>
								<td width="33%" align="center">
									<html:text property="orgName" name="beforeLoanAdvisoryFindAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
								
								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<html:submit property="method" styleClass="buttona" onclick="return check();">
										<bean:message key="button.empsearch" />
									</html:submit>
									<html:submit property="method" styleClass="buttona" onclick="return check();">
										<bean:message key="button.spousesearch" />
									</html:submit>
								</td>
								
							</tr>
						</table>

					</html:form>
					<html:form action="/beforeLoanAdvisoryMaintainAC.do" style="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="157">
											<b class="font14">职工信息</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="767">
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
									职工姓名
								</td>
								<td width="33%">
									<html:text property="empname" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									职工账号
								</td>
								<td width="33%">
									<html:text property="empId" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									身份证号
								</td>
								<td width="33%">
									<html:text property="empCardnum" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								    <html:hidden property="beforeLoanAdvisoryShowAF"   value="sex"></html:hidden>  		
								</td>
								<td width="17%" class="td1">
									年龄
								</td>
								<td width="33%">
									<html:text property="empAge" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									单位名称
								</td>
								<td width="33%" align="center">
									<html:text property="empOrgname" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
								    单位账号
								</td>
								<td width="33%" align="center">
									<html:text property="empOrgid" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								   <html:hidden property="beforeLoanAdvisoryShowAF"   value="officecode"></html:hidden>  
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									工资基数
								</td>
								<td width="33%" align="center">
									<html:text property="empSalaryBase" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
								    月缴存额
								</td>
								<td width="33%" align="center">
									<html:text property="empMonthPay" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									帐户余额
								</td>
								<td width="33%" align="center">
									<html:text property="empBalance" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
								    帐户状态
								</td>
								<td width="33%" align="center">
									<html:text property="empStatus" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									连续缴存月数
								</td>
								<td width="33%" align="center">
									<html:text property="empContinus" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
								    
								</td>
								
							</tr>
						</table>
				    <table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="157">
											<b class="font14">配偶信息</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="767">
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
									职工姓名
								</td>
								<td width="33%">
									<html:text property="spouseName" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									职工账号
								</td>
								<td width="33%">
									<html:text property="spouseId" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									身份证号
								</td>
								<td width="33%">
									<html:text property="spouseCardnum" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									年龄
								</td>
								<td width="33%">
									<html:text property="spouseAge" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									单位名称
								</td>
								<td width="33%" align="center">
									<html:text property="spouseOrgname" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
								    单位账号
								</td>
								<td width="33%" align="center">
									<html:text property="spouseOrgid" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									工资基数
								</td>
								<td width="33%" align="center">
									<html:text property="spouseSalaryBase" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
								    月缴存额
								</td>
								<td width="33%" align="center">
									<html:text property="spouseMonthPay" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									帐户余额
								</td>
								<td width="33%" align="center">
									<html:text property="spouseBalance" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
								    帐户状态
								</td>
								<td width="33%" align="center">
									<html:text property="spouseStatus" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									连续缴存月数
								</td>
								<td width="33%" align="center">
									<html:text property="spouseContinus" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
								    
								</td>
								
							</tr>
						</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="157">
											<b class="font14">录入信息</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="767">
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
									房屋总价
								</td>
								<td width="33%">
									<html:text property="houseTotalPrice" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									房屋类型
								</td>
								<td width="33%" align="center">
									<html:select name="beforeLoanAdvisoryShowAF" property="houseType"
										styleClass="input4" onkeydown="enterNextFocus1();" onchange="changes();">
										<html:option value=""></html:option>
										<html:optionsCollection property="houseTypeMap"
											name="beforeLoanAdvisoryShowAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									最高贷款额度
								</td>
								<td width="33%">
									<html:text property="maxLoanMoney" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
									最高贷款年限
								</td>
								<td width="33%">
									<html:text property="maxLoanYear" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									最高月还本息
								</td>
								<td width="33%" align="center">
									<html:text property="monthBackMoney" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
								   
								</td>
								
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="157">
											<b class="font14">贷款计算器</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="767">
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
									贷款人工资
								</td>
								<td width="33%">
									<html:text property="inputSalary" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();" onblur="queryInfo();"/>
								</td>
								<td width="17%" class="td1">
									贷款年限
								</td>
								<td width="33%" align="center">
									<html:text property="outLoanYear" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									金额
								</td>
								<td width="33%">
									<html:text property="outLoanMoney" name="beforeLoanAdvisoryShowAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="17%" class="td1">
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
									 <logic:empty name="beforeLoanAdvisoryShowAF" property="empId">
                                     <input type="button" class="buttona" value="职工流水" onclick="return skip()" disabled="true" >
                                
                                      </logic:empty>
                                     <logic:notEmpty name="beforeLoanAdvisoryShowAF" property="empId">
                                     <input type="button" class="buttona" value="职工流水" onclick="return skip();" >
                                      </logic:notEmpty>
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

</html>

