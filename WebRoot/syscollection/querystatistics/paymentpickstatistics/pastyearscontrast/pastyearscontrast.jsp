<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.action.PastyearscontrastShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			PastyearscontrastShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询>>缴存提取统计>>历年基础数据比照</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>

<script language="javascript">
var s1="";

function loads(){

   document.forms[0].elements["startDate"].value="";
   document.forms[0].elements["endDate"].value="";
   document.forms[0].elements["ragion"].value="";
   //document.forms[0].elements["collectionBank"].value="";
   document.forms[0].elements["orgChracter"].value="";
   document.forms[0].elements["dept"].value="";
  
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		}
	}
	 var list = document.all.listCount.value;
  
    if(list==1){
  		document.all.item(s1).disabled="";
		
    }else{
		document.all.item(s1).disabled="true";
    }
    
}

function gotoOffice(){
	 var queryString = "pastyearscontrastOfficeCodeAAC.do?officeCode=";
        var officecode = document.all.officeCode.value;
        queryString = queryString +officecode; 
        document.URL=queryString;
	    //findInfo(queryString);
}

function checkDate(){
  	var startdate = document.all.startDate.value;
  	var enddate = document.all.endDate.value;
  	if(startdate == ""&& enddate == ""){
    	alert("必须输入查询日期!");
   	return false;
  	}
   	if(startdate != ""&& enddate == ""){
    	alert("请输入查询结束日期!");
   	return false;
 	 }
   	if(startdate == ""&& enddate != ""){
    	alert("请输入查询开始日期!");
   	return false;
  	}
  	if(startdate != "" && enddate != "" ){
     	var strr = checkDate2('startDate','endDate');
     	if(!strr){
        	return false;
     	}
   	}
}

function checkDate2(date1,date2){
   	var tempDate1;
   	var tempDate2;
   	eval("tempDate1=document.all."+date1);
   	eval("tempDate2=document.all."+date2);
   	var strDate1 = tempDate1.value;
   	var strDate2 = tempDate2.value;
   	if(strDate1.length != 8)
   	{
     	alert("请输入八位的日期，格式为20070707！");
     	tempDate1.value="";
     	tempDate1.focus();
     	return false;
   	}
   	if(strDate2.length != 8)
   	{
     	alert("请输入八位的日期，格式为20070707！");
     	tempDate2.value="";
     	tempDate2.focus();
     	return false;
   	}
    if(strDate1.substring(0,4)<1900)
   	{
     	alert("年份应该大于1900!");
     	tempDate1.value="";
     	tempDate1.focus();
     	return false;
   	}
   	if(strDate2.substring(0,4)<1900)
   	{
     	alert("年份应该大于1900!");
     	tempDate2.value="";
     	tempDate2.focus();
     	return false;
   	}
   	if(strDate1.substring(4,6)>12 || strDate1.substring(4,6)<1)
   	{
     	alert("月份应该在1-12月之间！");
     	tempDate1.value="";
     	tempDate1.focus();
     	return false;
   	}
   	if(strDate2.substring(4,6)>12 || strDate2.substring(4,6)<1)
   	{
     	alert("月份应该在1-12月之间！");
     	tempDate2.value="";
     	tempDate2.focus();
     	return false;
   	}
   	if((strDate1.substring(4,6)==01 || strDate1.substring(4,6)==03 ||
   	strDate1.substring(4,6)==05 || strDate1.substring(4,6)==07 ||
   	strDate1.substring(4,6)==08 || strDate1.substring(4,6)==10 ||
   	strDate1.substring(4,6)==12)&&strDate1.substring(6,8)>31){
   		alert("大月日期应该在1-31之间");
	   	tempDate1.value="";
	   	tempDate1.focus();
	   	return false;
   	}
   	if((strDate1.substring(4,6)==04 || 
   	strDate1.substring(4,6)==05 || strDate1.substring(4,6)==09 || 
   	strDate1.substring(4,6)==11)&&strDate1.substring(6,8)>30){
   		alert("小月日期应该在1-30之间");
   		tempDate1.value="";
   		tempDate1.focus();
   		return false;
   	}
   	if((strDate2.substring(4,6)==01 || strDate2.substring(4,6)==03 ||
   	strDate2.substring(4,6)==05 || strDate2.substring(4,6)==07 ||
   	strDate2.substring(4,6)==08 || strDate2.substring(4,6)==10 ||
   	strDate2.substring(4,6)==12)&&strDate2.substring(6,8)>31){
   		alert("大月日期应该在1-31之间");
   		tempDate2.value="";
   		tempDate2.focus();
   		return false;
   	}
   	if((strDate2.substring(4,6)==04 || 
   	strDate2.substring(4,6)==05 || strDate2.substring(4,6)==09 || 
   	strDate2.substring(4,6)==11)&&strDate2.substring(6,8)>30){
   		alert("小月日期应该在1-30之间");
   		tempDate2.value="";
   		tempDate2.focus();
   		return false;
   	}
   	if(strDate1.substring(4,6)==02 && strDate1.substring(6,8)>29){
   		alert("二月份日期应该在1-29之间");
   		tempDate1.value="";
   		tempDate1.focus();
   		return false;
   	}
   	if((strDate2.substring(4,6)==02)&&strDate2.substring(6,8)>29){
	   	alert("二月份日期应该在1-29之间");
	   	tempDate2.value="";
	   	tempDate2.focus();
	   	return false;
   	}
   	if(strDate1>strDate2)
   	{
   		alert("起始日期应该不大于终止日期!");
   		return false;
   	}
   	return true;
}
function a(){
	var p = "<%=request.getContextPath()%>";
	officeAjax(p);
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads()"
	>
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=request.getContextPath()%>/img/table_left.gif"
								width="7" height="37">
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							width="55">
							&nbsp;
						</td>
						<td width="235"
							background="<%=request.getContextPath()%>/img/table_bg_line.gif">
							&nbsp;
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询</font><font color="00B5DB">&gt;</font><font
											color="00B5DB">历年基础数据比照</font>
									</td>
									<td class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=request.getContextPath()%>/img/table_right.gif"
								width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
				<html:form action="/pastyearscontrastFindAC.do">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="174">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22"
										background="<%=request.getContextPath()%>/img/bg2.gif"
										align="center" width="746">
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
							<td width="17%" class="td1">
								办事处
							</td>
							<td colspan="3">
								<html:select property="officeCode" styleClass="input4"
									onchange="a();">
									<option value=""></option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								归集银行
							</td>
							<td width="33%">
							
								<html:select property="collectionBankId" styleClass="input4">
									<option value=""></option>
									<html:options collection="loanbankList1" property="value"
										labelProperty="label" />
								</html:select>
								
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位性质
							</td>
							<td width="33%" colspan="3">
								<html:select property="orgChracter" styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="orgCharacterMap"
										name="thePastyearscontrasAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								主管部门
							</td>
							<td width="33%" colspan="3">
								<html:select property="dept" styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="deptMap"
										name="thePastyearscontrasAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								发生日期
							</td>
							<td width="15%">
								<html:text name="pastyearscontrasAF" property="startDate"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="pastyearscontrasAF" property="endDate"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								所在地区
							</td>
							<td width="33%" colspan="3">
								<html:select property="ragion" styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="ragionMap"
										name="thePastyearscontrasAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkDate();">
									<bean:message key="button.search" />
								</html:submit>
								<input type="hidden" name="listCount"
									value="<bean:write name="thePastyearscontrasAF" property="type"/>">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="216">
										<b class="font14">历年基础数据比照列表 </b>
									</td>
									<td height="22"
										background="<%=request.getContextPath()%>/img/bg2.gif"
										align="center" width="688">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="1" cellpadding="3"
					align="center">
					<tr>
						<td class="td1" width="40%">
							开户业务
						</td>
						<td class="td1">
							户数
						</td>
					</tr>
					<tr>
						<td class="td1" width="40%">
							单位开户
						</td>
						<td class="td4">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.orgOpen" styleClass="input3"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<td class="td1" width="40%">
							职工开户
						</td>
						<td class="td4" width="60%">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.empOpen" styleClass="input3"
								readonly="true" />
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="1" cellpadding="3"
					align="center">
					<tr id="tr3" class=td4>
						<td class="td1" width="40%">
							变更业务类型
						</td>
						<td class="td1" width="30%">
							笔数
						</td>
						<td class="td1" width="30%">
							缴额变化
						</td>
					</tr>
					<tr id="tr3" class=td4>
						<td class="td1" width="40%">
							缴额调整
						</td>
						<td width="30%">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.payCount" styleClass="input3"
								readonly="true" />
						</td>
						<td width="30%">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.payChg" styleClass="input3"
								readonly="true" />
						</td>
					</tr>
					<tr id="tr3" class=td4>
						<td class="td1" width="40%">
							汇缴比例调整
						</td>
						<td width="30%">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.rateCount" styleClass="input3"
								readonly="true" />
						</td>
						<td width="30%">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.rateChg" styleClass="input3"
								readonly="true" />
						</td>
					</tr>
					<tr id="tr3" class=td4>
						<td class="td1" width="40%">
							工资基数调整
						</td>
						<td width="30%">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.laborageCount" styleClass="input3"
								readonly="true" />
						</td>
						<td width="30%">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.laborageChg" styleClass="input3"
								readonly="true" />
						</td>
					</tr>
					<tr id="tr3" class=td4>
						<td class="td1" width="40%">
							人员变更
						</td>
						<td width="30%">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.empCount" styleClass="input3"
								readonly="true" />
						</td>
						<td width="30%">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.empChg" styleClass="input3"
								readonly="true" />
						</td>
					</tr>
					<tr id="tr3" class=td4>
						<td class="td1" width="40%">
							合计
						</td>
						<td width="30%">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.sumCount" styleClass="input3"
								readonly="true" />
						</td>
						<td width="30%">
							<html:text name="thePastyearscontrasAF"
								property="pastyearscontrasDTO.sumChg" styleClass="input3"
								readonly="true" />
						</td>
					</tr>
				</table>
				<html:form action="/pastyearscontrastPrintAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.print" />
											</html:submit>
											&nbsp;&nbsp;
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
