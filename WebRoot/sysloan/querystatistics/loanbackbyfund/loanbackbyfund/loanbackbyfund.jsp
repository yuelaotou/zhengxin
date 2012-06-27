<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.action.LoanBackByFundShowAC"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			LoanBackByFundShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>查询条件，业务流水信息列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">

</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script language="javascript" type="text/javascript">
function checkdate(){
	var beginBizDate = document.all.beginBizDate.value;
	var endBizDate = document.all.endBizDate.value;
	if(beginBizDate=='' && endBizDate==''){
		alert("请输入日期查询");
		return false;
	}
	if(beginBizDate!=''){
		checkDate0("beginBizDate");
	}
	if(endBizDate!=''){
		checkDate0("endBizDate");
	}
}
/*---用来判断只输入一个日期YYYYMMDD---*/
function checkDate0(date)
{
   var tempDate1; 
   eval("tempDate1=document.all."+date);
   var strDate = tempDate1.value;
  if(strDate.length!=8)
  {
    alert("请输入八位的日期格式，例如20070101！");
    tempDate1.value="";
    tempDate1.focus();
    return false;
  }
  if(strDate.substring(0,4)<1900){
    alert("年份应该大于1900！");
    tempDate1.value="";
    tempDate1.focus();
    return false;
  }
  if(strDate.substring(4,6)>12 || strDate.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    tempDate1.value="";
    tempDate1.focus();
    return false;
  }
  var tempStrDate=strDate.substring(6,8);
  var tempStrMonth=strDate.substring(4,6);
 if(tempStrMonth==2&&tempStrDate>29)
  {
    alert("日期不能大于29！");
    tempDate1.value="";
    tempDate1.focus();
    return false;
  }else if((tempStrMonth==1||tempStrMonth==3||tempStrMonth==5||tempStrMonth==7||tempStrMonth==8||tempStrMonth==10||tempStrMonth==12)&&tempStrDate>31){
    alert("日期不能大于31！");
    tempDate1.value="";
    tempDate1.focus();
    return false;
  }else if((tempStrMonth==2||tempStrMonth==4||tempStrMonth==6||tempStrMonth==9||tempStrMonth==11)&&tempStrDate>30){
    alert("日期不能大于30！");
    tempDate1.value="";
    tempDate1.focus();
    return false;
  }
  return true;
}

function checkDate1(){
  var date1 = document.all.beginBizDate.value;
  var date2 = document.all.endBizDate.value;
  //判断是否输入日期，如为空提示必须输入日期
  if((date1 == null || date1 == "") && (date2 == null || date2 == "")){
	alert("必须输入办理日期！");
    document.forms[1].elements["beginBizDate"].focus();
    return false;
   }
   if(date1 == null || date1 == ""){
	alert("请输入办理开始日期！");
    document.forms[1].elements["beginBizDate"].focus();
    return false;
   }
   if(date2 == null || date2 == ""){
	alert("请输入办理终止日期！");
    document.forms[1].elements["endBizDate"].focus();
    return false;
   }
  //两个日期都不为空
  if(date1 != "" && date2 != ""){
   var aa = checkDate2('beginBizDate','endBizDate');
   if(!aa){
    return false;
   }
  }
  //起始日期不空
  if(date1 != "" ){
    var aa =checkDate0('beginBizDate');
    if(!aa){
    return false;
    }   
   }
  //终止日期不空
  if(date2 != "" ){
   var aa =checkDate0('endBizDate');
   if(!aa){
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
function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="loanBackByFundShowAC.do" />
	</jsp:include>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="3%" align="right" valign="middle">
				<table width="21" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
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
						<td width="695" background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37" align="center" valign="top"
										style="PADDING-top: 7px"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
								</tr>
							</table>

						</td>
						<td width="300">
							<table width="300" border="0" cellspacing="0" cellpadding="0"
								align="right">
								<tr>
									<td width="56">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24" align="right">
									</td>
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">公积金还贷查询&gt;还贷查询</font>
									</td>
									<td class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="18">
							<img src="<%=path%>/img/table_bg_line.gif" width="18" height="37">
						</td>
						<td width="9">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top" class=td3>
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="207">
													<b class="font14">查 询 条 件</b>
												</td>
												<td width="716" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<html:form action="/loanBackByFundFindAC" style="margin: 0">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=0 align="center">
									<tr>
										<td width="15%" class="td1">
											扣款日期<font color="red">*</font>
											<br>
										</td>
										<td width="15%">
											<html:text name="loanBackByFundAF" property="beginBizDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="5%" align="center">
											至
										</td>
										<td width="15%">
											<html:text name="loanBackByFundAF" property="endBizDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="17%" class="td1">
											放款银行
										</td>
										<td width="33%">
											<html:select name="loanBackByFundAF" property="loanBankName"
												styleClass="input3" onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:options collection="loanBankNameList" property="value"
													labelProperty="label" />
											</html:select>
										</td>
									</tr>
								</table>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="right">
											<html:submit property="method" styleClass="buttona" onclick="return checkDate1();">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td class=h4>
											合计：笔数
											<u>：<bean:write name="pagination" property="nrOfElements" />
											</u>扣款金额总计
											<u>：<bean:write name="loanBackByFundAF" property="data_1" />
											</u>
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
												<td height="22" bgcolor="#CCCCCC" align="center" width="213">
													<b class="font14">公积金还贷列表</b>
												</td>
												<td width="710" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<html:form action="/loanBackByFundMaintainAC" style="margin: 0">
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr bgcolor="1BA5FF">
										<td align="center" height="6" colspan="1"></td>
									</tr>
								</table>
								<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
									cellpadding="3" align="center">
									<tr align="center" bgcolor="C4F0FF">
										<td align="center" class=td2>
											单位编号
										</td>
										<td align="center" class=td2>
											单位名称
										</td>
										<td align="center" class=td2>
											合同编号
										</td>
										<td align="center" class=td2>
											扣款人账号
										</td>
										<td align="center" class=td2>
											扣款人姓名
										</td>
										<td align="center" class=td2>
											提取金额
										</td>
										<td align="center" class=td2>
											扣款日期
										</td>
									</tr>
									<logic:notEmpty name="loanBackByFundAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="loanBackByFundAF" property="list"
											id="element" indexId="i">
											<%
														j++;
														if (j % 2 == 0) {
															strClass = "td8";
														} else {
															strClass = "td9";
														}
											%>
											<tr id="tr<%=i%>" class="<%=strClass%>">
												<td>
													<bean:write name="element" property="orgId" format="0000000000"/>
												</td>
												<td>
													<bean:write name="element" property="orgName" />
												</td>
												<td>
													<bean:write name="element" property="contractId" />
												</td>
												<td>
													<bean:write name="element" property="kouEmpId" format="000000"/>
												</td>
												<td>
													<bean:write name="element" property="kouEmpName" />
												</td>
												<td>
													<bean:write name="element" property="pickMoney" />
												</td>
												<td>
													<bean:write name="element" property="bizDate" />
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="loanBackByFundAF" property="list">
										<tr>
											<td colspan="9" height="30" style="color:red">
												没有找到与条件相符合的结果！
											</td>
										</tr>
									</logic:empty>
								</table>
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr class="td1">
										<td align="center">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr align="center">
													<td align="left">
														共
														<bean:write name="pagination" property="nrOfElements" />
														项
													</td>
													<td align="right">
														<jsp:include page="../../../../inc/pagination.jsp">
															<jsp:param name="url" value="loanBackByFundShowAC.do" />
														</jsp:include>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</html:form>
							<form action="loanBackFundShowAC.do" name="Form1"></form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>

