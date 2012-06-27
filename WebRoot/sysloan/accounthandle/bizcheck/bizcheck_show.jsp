<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.accounthandle.bizcheck.action.*"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			BizCheckShowListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>查询条件，业务复核列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">

</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script language="javascript" type="text/javascript">
/*---用来判断日期YYYYMMDD---*/
function checkDate0(date)
{
   	var tempDate1; 
   	eval("tempDate1=document.all."+date);
   	var strDate = tempDate1.value;
  	if(strDate.length!=8)
  	{
    	alert("请输入八位的日期格式，例如20070101！");
    	return false;
  	}
  	if(strDate.substring(0,4)<1900){
    	alert("年份应该大于1900！");
    	return false;
  	}
  	if(strDate.substring(4,6)>12 || strDate.substring(4,6)<1)
  	{
    	alert("月份应该在1-12月之间！");
    	return false;
  	}
  	var tempStrDate=strDate.substring(6,8);
  	var tempStrMonth=strDate.substring(4,6);
 	if(tempStrMonth==2&&tempStrDate>29)
  	{
    	alert("日期不能大于29！");
    	return false;
  	}else if((tempStrMonth==1||tempStrMonth==3||tempStrMonth==5||tempStrMonth==7||tempStrMonth==8||tempStrMonth==10||tempStrMonth==12)&&tempStrDate>31){
    	alert("日期不能大于31！");
    	return false;
  	}else if((tempStrMonth==2||tempStrMonth==4||tempStrMonth==6||tempStrMonth==9||tempStrMonth==11)&&tempStrDate>30){
    	alert("日期不能大于30！");
    	return false;
  	}
  	return true;
}

function checkDate1(){

  	var date1 = document.forms[1].elements["beginBizDate"].value;
  	var date2 = document.forms[1].elements["endBizDate"].value;
  
  	if(date1 != "" && date2 != ""){
   		var aa = checkDate2('beginBizDate','endBizDate');
   		if(!aa){
   			document.forms[1].elements["beginBizDate"].value="";
   			document.forms[1].elements["endBizDate"].value="";
    		return false;
   		}
  	}
  	if(date1 != "" ){
   		var aa =checkDate0('beginBizDate');
    	if(!aa){
    		document.forms[1].elements["beginBizDate"].value="";
    		return false;
   		}   
   	}
    if(date2 != "" ){
   		var aa =checkDate0('endBizDate');
    	if(!aa){
    		document.forms[1].elements["endBizDate"].value="";
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
function gettr2(trindex) {
  tr=trindex;
  sureType();
}
function sureType(){
     var bizType=document.getElementById(tr).childNodes[5].childNodes[0].innerHTML.trim();
  	 if(bizType=="批量回收" || bizType=="单笔回收" || bizType=="部分提前还款" || bizType=="一次性还清"){      	
	     var id  = document.getElementById(tr).childNodes[16].innerHTML.trim();
	     var contractId=document.getElementById(tr).childNodes[3].innerHTML.trim();
	     var originalitybizType=document.getElementById(tr).childNodes[15].innerHTML.trim();
	     window.open('<%=request.getContextPath()%>/sysloan/loancallbackTbForwardURLWindowAC.do?contractId='+contractId+'&headId='+id+'&type='+originalitybizType,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	}
 	if(bizType=="发放"){  	
    var id  = document.getElementById(tr).childNodes[16].innerHTML;
     window.open('<%=request.getContextPath()%>/sysloan/loanaccordFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 }
 	 if(bizType == "挂账"){ 	     	
     var id  = document.getElementById(tr).childNodes[16].innerHTML;
     window.open('<%=request.getContextPath()%>/sysloan/overPayFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 }
 	 if(bizType == "保证金"){ 	     	
     var id  = document.getElementById(tr).childNodes[16].innerHTML;
     window.open('<%=request.getContextPath()%>/sysloan/bailFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 }
 	 if(bizType == "错账调整"){ 	     	
     var id  = document.getElementById(tr).childNodes[16].innerHTML;
     window.open('<%=request.getContextPath()%>/sysloan/adjustAccountFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 }
 	 if(bizType =="核销收回（中心）"||bizType =="核销收回（其他）")
 	 {
 	 	var id  = document.getElementById(tr).childNodes[16].innerHTML;
 	 	window.open('<%=request.getContextPath()%>/sysloan/destoryBackTbWindowAC.do?id='+id,'','width=400,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');
 	 }
 	  if(bizType =="呆账核销（中心）"||bizType =="呆账核销（其他）")
 	 {
 	 	var id  = document.getElementById(tr).childNodes[16].innerHTML.trim();
     	var contractId=document.getElementById(tr).childNodes[3].innerHTML.trim();
 	 	window.open('<%=request.getContextPath()%>/sysloan/badDebtDestroyTbForwardURLWindowAC.do?contractId='+contractId+'&headId='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes'); 
 	 }
}
var s1="";
var s2="";
var s3="";
var s4="";
var tr="tr0";

function loads(){
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="复核通过"){
				s1=i;
			}
			if(document.all.item(i).value=="批量复核"){
				s2=i;
			}
			if(document.all.item(i).value=="撤消复核"){
				s3=i;
			}
			if(document.all.item(i).value=="撤消批量复核"){
				s4=i;
			}
		}
	}
  	//初始状态按钮全部禁用
	
	if(count==0){
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
	}else{
		update();
	}
}

function update() {
  	//var bizSt=document.getElementById(tr).childNodes[6].childNodes[0].innerHTML.trim();
  	var affirmbizSt = "<bean:write name="bizCheckShowListAF" property="affirmbizSt"/>";
  	var checkbizSt = "<bean:write name="bizCheckShowListAF" property="checkbizSt"/>";
  	var count = "<bean:write name="pagination" property="nrOfElements"/>";
     
    /*if(bizSt=='确认'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
  	}else if(bizSt=='复核'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
  	}else if(bizSt=='入账'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
  	}*/
  	if(affirmbizSt!=count)
  	{
  		document.all.item(s2).disabled="true";
  	}
  	if(checkbizSt!=count)
  	{
  		document.all.item(s4).disabled="true";
  	}
  	
}
function check(buttonName) {
    var temp_empid = [];
    var arr= document.getElementsByName("rowArray");
	var temp=0;
	var j=0;
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			temp=temp+1;
			temp_empid[j] = arr[i].value;
 			j++;
		}
	}
	var buttonMethod=buttonName.value; 
	if((buttonMethod=="复核通过"||buttonMethod=="撤消复核")&&temp==0){
		alert("请选择要"+buttonMethod+"的业务！");
		return false;
	}else{
		return confirm("是否"+buttonMethod);
		
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads(); reportErrors();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="bizCheckShowListAC.do" />
	</jsp:include>
	<table width=1300 border="0" cellspacing="0" cellpadding="0">
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
						<td width="255">
							<table width="255" border="0" cellspacing="0" cellpadding="0"
								align="right">
								<tr>
									<td width="56">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24" align="right">
									</td>
									<td width="169" class=font14 bgcolor="#FFFFFF" align="center"
										valign="middle">
										<font color="00B5DB">财务处理&gt; 业务复核</font>
									</td>
									<td width="30" class=font14>
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
							<html:form action="/bizCheckFindAC.do" method="post"
								style="margin: 0">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=0 align="center">
									<tr>
										<td width="17%" class="td1">
											凭证编号
										</td>
										<td width="33%" colspan="3">
											<html:text onkeydown="enterNextFocus1();"
												name="bizCheckShowListAF" property="docNum"
												styleClass="input3" />
										</td>
										<td width="17%" class="td1">
											合同编号
										</td>
										<td width="33%">
											<html:text onkeydown="enterNextFocus1();"
												name="bizCheckShowListAF" property="contractId"
												styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											扣款账号
										</td>
										<td width="33%" align="center" colspan="3">
											<html:text onkeydown="enterNextFocus1();"
												name="bizCheckShowListAF" property="loanKouAcc"
												styleClass="input3" />
										</td>
										<td width="17%" class="td1">
											借款人姓名
										</td>
										<td align="center">
											<html:text onkeydown="enterNextFocus1();"
												name="bizCheckShowListAF" property="borrowerName"
												styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											业务类型
										</td>
										<td width="33%" align="center" colspan="3">
											<html:select onkeydown="enterNextFocus1();"
												name="bizCheckShowListAF" property="bizType"
												styleClass="input3">
												<html:option value=""></html:option>
												<html:optionsCollection property="bizTypeMap"
													name="bizCheckShowListAF" label="value" value="key" />
											</html:select>
										</td>
										<td width="17%" class="td1">
											制单人
										</td>
										<td align="center">

											<html:select onkeydown="enterNextFocus1();"
												name="bizCheckShowListAF" property="makePerson"
												styleClass="input3">
												<html:option value=""></html:option>
												<html:options collection="operList1" property="value"
													labelProperty="label" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											业务状态
										</td>
										<td width="33%" align="center" colspan="3">
											<html:select onkeydown="enterNextFocus1();"
												name="bizCheckShowListAF" property="bizSt"
												styleClass="input3">
												<html:option value=""></html:option>
												<html:optionsCollection property="bizStMap"
													name="bizCheckShowListAF" label="value" value="key" />
											</html:select>
										</td>
										<td width="17%" class="td1">
											放款银行
										</td>
										<td align="center">
											<html:select onkeydown="enterNextFocus1();"
												name="bizCheckShowListAF" property="loanBankName"
												styleClass="input3">
												<html:option value=""></html:option>
												<html:options collection="loanBankNameList" property="value"
													labelProperty="label" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											办理日期
										</td>
										<td width="15%">
											<html:text onkeydown="enterNextFocus1();"
												name="bizCheckShowListAF" property="beginBizDate"
												styleClass="input3" />
										</td>

										<td width="4%">
											至
										</td>
										<td width="14%">
											<html:text onkeydown="enterNextFocus1();"
												name="bizCheckShowListAF" property="endBizDate"
												styleClass="input3" />
										</td>
										<td width="17%" class="td1"></td>
										<td align="center" class="td7">
											&nbsp;
										</td>
									</tr>
								</table>

								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="right">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDate1();">
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
											</u>发放金额
											<u>：<bean:write name="bizCheckShowListAF"
													property="occurMoneyTotle" /> </u> 回收本金
											<u>：<bean:write name="bizCheckShowListAF"
													property="reclaimCorpusTotle" /> </u> 回收利息
											<u>：<bean:write name="bizCheckShowListAF"
													property="reclaimAccrualTotle" /> </u> 回收逾期利息
											<u>：<bean:write name="bizCheckShowListAF"
													property="realPunishInterestTotle" /> </u> 挂账金额
											<u>：<bean:write name="bizCheckShowListAF"
													property="putUpMoneyTotle" /> </u> 本次实还金额
											<u>：<bean:write name="bizCheckShowListAF"
													property="reclaimtotle" /> </u>本次实收金额
											<u>：<bean:write name="bizCheckShowListAF"
													property="reclaimbacktotle" /> </u>
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
													<b class="font14">业务复核列表</b>
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

							<html:form action="/bizcheckMaintainAC.do" method="post"
								style="margin: 0">
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
											凭证编号
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('loankouacc')">扣款账号</a>
											<logic:equal name="pagination" property="orderBy"
												value="loankouacc">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('contractId')">合同编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="contractId">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('borrowername')">借款人姓名</a>
											<logic:equal name="pagination" property="orderBy"
												value="borrowername">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>

										<td align="center" class=td2>
											<a href="javascript:sort('bizType')">业务类型</a>
											<logic:equal name="pagination" property="orderBy"
												value="bizType">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>

										<td align="center" class=td2>
											业务状态
										</td>
										<td align="center" class=td2>
											发放金额
										</td>
										<td align="center" class=td2>
											回收本金
										</td>
										<td align="center" class=td2>
											回收利息
										</td>
										<td align="center" class=td2>
											回收逾期利息
										</td>
										<td align="center" class=td2>
											挂账金额
										</td>
										<td align="center" class=td2>
											本次实还金额
										</td>
										<td align="center" class=td2>
											本次实收金额
										</td>
										<td align="center" class=td2>
											办理日期
										</td>

									</tr>
									<logic:notEmpty name="bizCheckShowListAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="bizCheckShowListAF" property="list"
											id="element" indexId="i">
											<%
														j++;
														if (j % 2 == 0) {
															strClass = "td8";
														} else {
															strClass = "td9";
														}
											%>
											<tr align="left" id="tr<%=i%>" class="<%=strClass%>">
												<td>
													<html:multibox property="rowArray">
														<bean:write name="element" property="flowHeadId" />
													</html:multibox>
												</td>
												<td>
													<p>
														<bean:write name="element" property="docNum" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="loanKouAcc" />
													</p>
												</td>
												<td>
													<bean:write name="element" property="contractId" />
												</td>
												<td>
													<p>
														<bean:write name="element" property="borrowerName" />
													</p>
												</td>
												<td>
													<a href="#" onClick="gettr2('tr<%=i%>');" /><bean:write
															name="element" property="bizType" /> </a>
												</td>
												<td>
													<p>
														<bean:write name="element" property="bizSt" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="occurMoney" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="reclaimCorpus" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="reclaimAccrual" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="realPunishInterest" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="putUpMoney" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="reclaim" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="reclaimback" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="bizDate" />
													</p>
												</td>
												<td style="display:none">
													<bean:write name="element" property="originalitybizType" />
												</td>
												<td style="display:none">
													<bean:write name="element" property="flowHeadId" />
												</td>
											</tr>

										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="bizCheckShowListAF" property="list">
										<tr>
											<td colspan="16" height="30" style="color:red">
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
														<jsp:include page="/inc/pagination.jsp">
															<jsp:param name="url" value="bizCheckShowListAC.do" />
														</jsp:include>
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
												<tr align="center">
													<td width="70">
														<html:submit property="method" styleClass="buttona"
															onclick="return check(this);">
															<bean:message key="button.check.through" />
														</html:submit>
													</td>
													<td width="70">
														<html:submit property="method" styleClass="buttona"
															onclick="return check(this);">
															<bean:message key="button.checkall" />
														</html:submit>
													</td>
													<td width="70">
														<html:submit property="method" styleClass="buttona"
															onclick="return check(this);">
															<bean:message key="button.del.check" />
														</html:submit>
													</td>
													<td width="90">
														<html:submit property="method" styleClass="buttonb"
															onclick="return check(this);">
															<bean:message key="button.del.checkall" />
														</html:submit>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</html:form>
							<form action="bizCheckShowListAC.do" name="Form1"></form>
				</table>
</body>
</html:html>

