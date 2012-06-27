<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.accounthandle.clearaccount.action.ClearaccountTaShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ClearaccountTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>个贷管理</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	
	<script language="javascript" src="js/common.js">
</head>
<script language="javascript" ></script>
	<script language="javascript" type="text/javascript">
var tr="tr0";
function gettr2(trindex) {
	  tr=trindex;
	  sureType();
}
function sureType(){
     var bizType=document.getElementById(tr).childNodes[4].childNodes[0].innerHTML.trim();
  	 if(bizType=="批量回收" || bizType=="单笔回收" || bizType=="部分提前还款" || bizType=="一次性还清"){      	
     var id  = document.getElementById(tr).childNodes[14].innerHTML.trim();
     var contractId=document.getElementById(tr).childNodes[2].innerHTML.trim();
     var originalitybizType=document.getElementById(tr).childNodes[13].innerHTML.trim();
     window.open('<%=request.getContextPath()%>/sysloan/loancallbackTbForwardURLWindowAC.do?contractId='+contractId+'&headId='+id+'&type='+originalitybizType,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	}
 	if(bizType=="发放"){  	
     var id  = document.getElementById(tr).childNodes[14].innerHTML.trim();
     window.open('<%=request.getContextPath()%>/sysloan/loanaccordFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 }
 	 if(bizType == "挂账"){ 	     	
     var id  = document.getElementById(tr).childNodes[14].innerHTML.trim();
     window.open('<%=request.getContextPath()%>/sysloan/overPayFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 }
 	 if(bizType == "保证金"){ 	     	
     var id  = document.getElementById(tr).childNodes[14].innerHTML.trim();
     window.open('<%=request.getContextPath()%>/sysloan/bailFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 }
 	  if(bizType == "错账调整"){ 	     	
     var id  = document.getElementById(tr).childNodes[14].innerHTML.trim();
     window.open('<%=request.getContextPath()%>/sysloan/adjustAccountFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 } 
 	 if(bizType =="核销收回（中心）"||bizType =="核销收回（其他）")
 	 {
 	 	var id  = document.getElementById(tr).childNodes[14].innerHTML;
 	 	window.open('<%=request.getContextPath()%>/sysloan/destoryBackTbWindowAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');
 	 }
 	  if(bizType =="呆账核销（中心）"||bizType =="呆账核销（其他）")
 	 {
 	 	var id  = document.getElementById(tr).childNodes[14].innerHTML.trim();
     	var contractId=document.getElementById(tr).childNodes[2].innerHTML.trim();
 	 	window.open('<%=request.getContextPath()%>/sysloan/badDebtDestroyTbForwardURLWindowAC.do?contractId='+contractId+'&headId='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes'); 
 	 }
}
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
    return false;
   }
  }
  if(date1 != "" ){

   var aa =checkDate0('beginBizDate');
    if(!aa){
    return false;
   }   
   }
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
var flagss=false;
function loads(){
	document.clearaccountAF.loanBankName.value="";
	document.clearaccountAF.beginBizDate.value="";
	document.clearaccountAF.endBizDate.value="";
	document.clearaccountAF.bizSt.value="";
	document.clearaccountAF.makePerson.value="";
	document.clearaccountAF.bizType.value="";
	document.clearaccountAF.borrowerName.value="";
	document.clearaccountAF.loanKouAcc.value="";
	document.clearaccountAF.contractId.value="";
	document.clearaccountAF.docNum.value="";
	
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	var x= document.getElementsByName("rowArray");
	for(var i=0;i<x.length;i++){
		x[i].checked=flagss;
	}
	flagss=(!flagss);
	return false;
	
}
function check(eee){
var arr= document.getElementsByName("rowArray");
	var temp=0;
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			temp=temp+1;
		}
	}
	if(temp==0){
		alert("请选择扎账的业务！");
		return false;
	}else{
		var x=confirm("确定进行扎账业务？");
		if(x){
		setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
			return true;
		}else{
			return false;
		}
	}
	setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
	return true;
}
var flag=true;
function checks(trindex){
	var x= document.getElementsByName("rowArray");

	tr=trindex;
	var status=document.getElementById(tr).childNodes[12].innerHTML.trim();
	//alert(status);
	if(status!='复核'){
		alert("请选择业务状态为复核的进行扎账业务");
		return false;
	}else  
	return true;
}

function check_date(eee){
	
	var x=confirm("确定进行扎账业务? ");
		if(x){
		setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');    
			return true;	
		}else{
		  return false;
		}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return loads();"
	onContextmenu="return true">
	<jsp:include flush="true" page="/waiting.inc"/>
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="clearaccountTaShowAC.do" />
	</jsp:include>
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
										扎账
									</td>
<%--									<td width="112" height="37"--%>
<%--										background="<%=path%>/img/buttong.gif" align="center"--%>
<%--										style="PADDING-top: 7px" valign="top">--%>
<%--										<a href="clearaccountTbForwardURLAC.do" class=a2>结算单查询</a>--%>
<%--									</td>--%>
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
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">账务处理&gt;扎账</font>
									</td>
									<td width="29" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="160">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="750">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/clearaccountTaFindAC.do" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								凭证编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="clearaccountAF" property="docNum"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%">
								<html:text name="clearaccountAF" property="contractId"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								扣款账号
							</td>
							<td width="33%" align="center" colspan="3">
								<html:text name="clearaccountAF" property="loanKouAcc"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%">
								<html:text name="clearaccountAF" property="borrowerName"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								业务类型
							</td>
							<td width="33%" align="center" colspan="3">
								<html:select name="clearaccountAF" property="bizType"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizTypeMap"
										name="clearaccountAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								制单人
							</td>
							<td width="33%">
								<html:select name="clearaccountAF" property="makePerson"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
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
								<html:select name="clearaccountAF" property="bizSt"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizStMap"
										name="clearaccountAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								放款银行
							</td>
							<td width="33%">
								<html:select name="clearaccountAF" property="loanBankName"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
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
								<html:text name="clearaccountAF" property="beginBizDate"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>

							<td width="4%">
								至
							</td>
							<td width="14%">
								<html:text name="clearaccountAF" property="endBizDate"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1"></td>
							<td width="33%">
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
				</html:form>
				<html:form action="/clearaccountTaMaintainAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：发放金额
								<u>：<bean:write name="clearaccountAF"
										property="occurMoneyTotle" /> </u> 回收金额
								<u>：<bean:write name="clearaccountAF"
										property="sumbackmoney" /> </u>		
							<%-- 			
								 呆账核销金额
								 <u>：<bean:write name="clearaccountAF"
										property="badDebtTotle" /> </u> 
							--%>
							回收人数	
							<u>：<bean:write name="clearaccountAF"
										property="realbackpeopercountTotal" /> </u> 
										
							回收本金	
							<u>：<bean:write name="clearaccountAF"
										property="reclaimCorpusTotle" /> </u> 
								 回收利息		
										<u>：<bean:write name="clearaccountAF"
										property="reclaimAccrualTotle" /> </u>
									 回收逾期息	
										<u>：<bean:write name="clearaccountAF"
										property="realPunishInterestTotle" /> </u>
												
								
										
										挂账金额
								<u>：<bean:write name="clearaccountAF"
										property="putUpMoneyTotle" /> </u>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="160">
											<b class="font14">业 务 列 表</b>
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
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
							<td align="center" class=td2>
								凭证编号
							</td>
							<td align="center" class="td2">
								<a href="javascript:sort('contractId')">合同编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="contractId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class="td2">
								<a href="javascript:sort('borrowername')">借款人姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="borrowername">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class="td2">
								<a href="javascript:sort('bizType')">业务类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="bizType">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class="td2">
								发放金额
							</td>
							<td align="center" class="td2">
								回收金额
							</td>
							<td align="center" class=td2>
								回收本金
							</td>
							<td align="center" class=td2>
								回收利息
							</td>
							<td align="center" class=td2>
								回收逾期息
							</td>
							<%-- 
							<td align="center" class=td2>
								呆账核销金额
							</td>
							--%>
							
							<td align="center" class=td2>
								挂账金额
							</td>
							<%-- 
							<td align="center" class=td2>
								保证金
							</td>
							--%>
							<td align="center" class=td2>
								办理日期
							</td>
							<td align="center" class=td2>
								业务状态
							</td>
						</tr>
						<logic:notEmpty name="clearaccountAF" property="list">
						<% int j=0;
			String strClass="";
		%>
		
							<logic:iterate name="clearaccountAF" property="list" id="element"
								indexId="i">
								<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
								<tr id="tr<%=i%>" class="<%=strClass%>">
									<td valign="top" onclick='return checks("tr<%=i%>")'>
										<p align="left">
											<html:multibox property="rowArray">
												<bean:write name="element" property="flowHeadId" />
											</html:multibox>
										</p>
									</td>
									<td>
										<bean:write name="element" property="docNum" />
									</td>
									<td>
										<bean:write name="element" property="contractId" />
									</td>
									<td>
										<bean:write name="element" property="borrowerName" />
									</td>
									<td>
										<a href="#" onClick="gettr2('tr<%=i%>');" /><bean:write
												name="element" property="bizType" /> </a>
									</td>
									<td>
										<bean:write name="element" property="occurMoney" />
									</td>
									<td>
										<bean:write name="element" property="sumReclaimMoney" />
									</td>
									<td>
										<bean:write name="element" property="reclaimCorpus" />
									</td>
									<td>
										<bean:write name="element" property="reclaimAccrual" />
									</td>
									<td>
										<bean:write name="element" property="realPunishInterest" />
									</td>
									<%-- 
									<td>
										<bean:write name="element" property="badDebt" />
									</td>
									--%>
									<td>
										<bean:write name="element" property="putUpMoney" />
									</td>
									<%-- 
									<td>
										<bean:write name="element" property="bail" />
									</td>
									--%>
									<td>
										<bean:write name="element" property="bizDate" />
									</td>
									<td>
										<bean:write name="element" property="bizSt" />
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
						<logic:empty name="clearaccountAF" property="list">
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
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr align="center">
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="/inc/pagination.jsp">
												<jsp:param name="url" value="clearaccountTaShowAC.do" />
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
									<tr>
										<logic:notEmpty name="clearaccountAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return check(this);">
													<bean:message key="button.in.account"/>
												</html:submit>
											</td>
											<td width="112">
												<html:submit property="method" styleClass="buttona" onclick="return check_date(this);" >
													<bean:message key="button.in.accountall"/>
												</html:submit>
											</td>
										</logic:notEmpty>
										<logic:empty name="clearaccountAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona" disabled="true">
													<bean:message key="button.in.account" />
												</html:submit>
											</td>
											<td width="112">
												<html:submit property="method" styleClass="buttona" disabled="true">
													<bean:message key="button.in.accountall"/>
												</html:submit>
											</td>
										</logic:empty>
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
