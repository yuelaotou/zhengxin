<%@ page language="java" import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.LoanreturnedbyfundTaDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	List updateList = (List) request.getAttribute("updateList");
	if (updateList == null) {
		updateList = new ArrayList();
	}
	String print = (String) request.getAttribute("print");
	String contractId = (String) request.getSession().getAttribute(
			"contractId");
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
		<script language="javascript" src="<%=path%>/js/common.js"></script>

	</head>

	<script>
var s1="";
function reportErrors() {


	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	if(<%=print%>!=null){
		var x=confirm("是否打印公积金还贷委托扣款协议!");
 		 if(x){
			document.location="loanreturnedbyfundTaTaPrintAC.do";
  		}
  	} 
	
	
}
function ondelete(){
var x= document.getElementsByName("rowArray");
var y=true;
for(var i=0;i<x.length;i++){
		if(x[i].checked){
		y=false;
		}
	}
	if(y){
	alert(' 至少选择一条记录！');
	return false;
	}else{
	return true;
	}
}
function onprint(){
var x= document.getElementsByName("rowArray");
var y=true;
for(var i=0;i<x.length;i++){
		if(x[i].checked){
		y=false;
		}
	}
	if(y){
	alert(' 至少选择一条记录！');
	return false;
	}else{
	return true;
	}
}
var tr=0;
function gettr(tr){
	var a=document.getElementById(tr).childNodes[6].innerHTML;	
	var b="";
	var c='seq'+tr;
	
	var x= document.getElementsByName("rowArray");
	var y=true;
	for(var i=0;i<x.length;i++){
		if(x[i].checked){
			if(b==a.trim()){
				document.getElementById(tr).childNodes[6].value="";
			}else{
				document.idAF.elements[c].focus();
			}
		}else{
			document.idAF.elements[c].value="";
		}
	}
}
var old_temp="tr0";
function gotoClick123(id1,id2,form){
	var temp1;
	var temp2;
	var temp3;
	eval("temp1="+id1);
	eval("temp3="+old_temp);
	eval("temp2=form."+id2);	
	temp3.style.background="#ffffff";
	temp1.style.background="#EEFBFF";
	old_temp=id1;
}
function showlist() {
  	document.Form1.submit();
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function display1(s) {
  	if(confirm(s)){
	  	var time=document.all.bizTime.value.trim(); 
	    var empId=document.all.empId.value.trim();
	    var orgid=document.all.org_Id.value.trim();
	    var queryString = "loanreturnedbyfundTaBorrowerNameFindAAC.do?";  
	    queryString = queryString +"orgid="+orgid+"&borrowerEmpId="+empId+"&time="+time+"&again=again";   
	    findInfo(queryString);
  	}
}
function display2(s) {
  	if(confirm(s)){
	  	var time=document.all.bizTime.value.trim(); 
	    var empId=document.all.empId.value.trim();
	    var orgid=document.all.org_Id.value.trim();
	    var queryString = "loanreturnedbyfundTaAssiFindAAC.do?";  
	    queryString = queryString +"orgid_t="+orgid+"&borrowerEmpId_t="+empId+"&time="+time+"&again=again";   
	    findInfo(queryString);
  	}
}
function display3(s) {
  	if(confirm(s)){
	  	var time=document.all.bizTime.value.trim(); 
	    var empId=document.all.empId.value.trim();
	    var orgid=document.all.org_Id.value.trim();
	    var queryString = "loanreturnedbyfundTaAssiFindAACA.do?";  
	    queryString = queryString +"orgid_t="+orgid+"&borrowerEmpId_t="+empId+"&time="+time+"&again=again";   
	    findInfo(queryString);
  	}
}
function display4(s) {
  	if(confirm(s)){
	  	var time=document.all.bizTime.value.trim(); 
	    var empId=document.all.empId.value.trim();
	    var orgid=document.all.org_Id.value.trim();
	    var queryString = "loanreturnedbyfundTaAssiFindAACB.do?";  
	    queryString = queryString +"orgid_t="+orgid+"&borrowerEmpId_t="+empId+"&time="+time+"&again=again";   
	    findInfo(queryString);
  	}
}

function displays(contractId) {
   	showlist();
}
function executeAjax()
{   
	if( document.idAF.elements["contractId"].value.trim()==""){
	   alert("录入合同编号!");
	   return false;
	}	 
    var contractId= document.idAF.elements["contractId"].value.trim();
    var time =document.all.bizTime.value.trim();
    var queryString = "loanreturnedbyfundTaFindAAC.do?";  
    queryString = queryString + "contractId="+contractId+"&time="+time;  
    findInfo(queryString);
}
function executeAjaxIn()
{
	if( document.all.empId.value.trim()==""){
	   alert("录入借款人职工编号!");
	   return false;
	}	
	var time=document.all.bizTime.value.trim(); 
    var empId=document.all.empId.value.trim();
    var orgid=document.all.org_Id.value.trim();
    var queryString = "loanreturnedbyfundTaBorrowerNameFindAAC.do?";  
    queryString = queryString +"orgid="+orgid+"&borrowerEmpId="+empId+"&time="+time;   
    findInfo(queryString);
}
function executeAjaxIn_wsh()
{
	if( document.all.empId.value.trim()==""){
	   alert("录入共同借款人职工编号!");
	   return false;
	}	
	var time =document.all.bizTime.value.trim();
    var empId= document.all.empId.value.trim();
    var orgid=document.all.org_Id.value.trim();
    var queryString = "loanreturnedbyfundTaAssiFindAAC.do?";  
    queryString = queryString +"orgid_t="+orgid+"&borrowerEmpId_t="+empId+"&time="+time;    
    findInfo(queryString);
}
function executeAjaxIn_wsha()
{
	if( document.all.empId.value.trim()==""){
	   alert("录入共同借款人职工编号!");
	   return false;
	}	
	var time =document.all.bizTime.value.trim();
    var empId= document.all.empId.value.trim();
    var orgid=document.all.org_Id.value.trim();
    var queryString = "loanreturnedbyfundTaAssiFindAACA.do?";  
    queryString = queryString +"orgid_t="+orgid+"&borrowerEmpId_t="+empId+"&time="+time;    
    findInfo(queryString);
}
function executeAjaxIn_wshb()
{
	if( document.all.empId.value.trim()==""){
	   alert("录入共同借款人职工编号!");
	   return false;
	}	
	var time =document.all.bizTime.value.trim();
    var empId= document.all.empId.value.trim();
    var orgid=document.all.org_Id.value.trim();
    var queryString = "loanreturnedbyfundTaAssiFindAACB.do?";  
    queryString = queryString +"orgid_t="+orgid+"&borrowerEmpId_t="+empId+"&time="+time;    
    findInfo(queryString);
}
function isNumber(s,tr) //数字判断函数
{
	
    var c='seq'+tr;
    var digits = "123456789";
    var i = 0;
	if(""==s){
		document.idAF.elements[c].focus();
		alert("请输入扣款顺序，顺序不许重复！");
	}
    var sLength = s.length;
    while (i < sLength)
    {
        var c = s.charAt(i);
        if (digits.indexOf(c) == -1) alert("不是数字");return false;
        i++;
    }
    return true;
}


function BubbleSort(arr) { //交换排序->冒泡排序
  	var exchange;
  	for(var i=0; i<arr.length; i++) {
   		exchange = false;
   		for(var j=arr.length-2; j>=i; j--) {
	    	if((arr[j+1]) == (arr[j])) {
	     		return false;
	    	}
   		}
 	} 
   	return true;
}

function onSure() { //交换排序->冒泡排序
 	var date = document.all.bizTime;
 	var empid = document.idAF.elements["borrow_empid"].value.trim();
 	var empid_s = document.idAF.elements["borrow_s_empid"].value.trim();
 	if(!checkDate(date)){
 		return false;
 	}
 
<%-- 	if(empid=="" && empid_s==""){--%>
<%-- 		alert("必须先选择借款人或共同借款人才能保存!");--%>
<%-- 		return false;--%>
<%-- 	}--%>
 
	var str=[];
 	var x= document.getElementsByName("rowArray");
 	var valu;
 	var position;
	var j=0;
	var g;
	var x= document.getElementsByName("rowArray");
	var y=true;
	for(var i=0;i<x.length;i++){
		if(x[i].checked){
		 var c="seqtr";
		 var row='tr';
		 row="'"+row+i+"'";
		 var v;
		 c=c+i;
		position= document.getElementById('tr'+i).childNodes[6].innerHTML.indexOf("name"); 
	if(x.length==1){
		 valu=document.getElementById('tr'+i).childNodes[6].innerHTML.substring(77,position-1);
 v=document.idAF.rowArray.value;
		document.idAF.rowArray.value=v+","+valu;
	}else{
		 valu=document.getElementById('tr'+i).childNodes[6].innerHTML.substring(77,position-1);
 v=document.idAF.rowArray[i].value;
		document.idAF.rowArray[i].value=v+","+valu;
	}	

g=valu;
str[j]=g;
j=j+1;
		}
	}
 if(!BubbleSort(str)){
 
  alert("扣款顺序不许重复，请重新录入！");
  return false;
 }
 }
 
 function load(){
	var seq;
	var j=0;
  	<%
		LoanreturnedbyfundTaDTO loanreturnedbyfundTaDTO = new LoanreturnedbyfundTaDTO();
		for(int j=0;j<updateList.size();j++){
		loanreturnedbyfundTaDTO = (LoanreturnedbyfundTaDTO)updateList.get(j);			
	%>
	var c='tr';
	var nam="seqtr";
	seq= "<%=loanreturnedbyfundTaDTO.getSeq()%>"; 
	if(seq== "null"){
	}else{
		var x="";
		c=c+j;
		x='"'+c+'"';
		nam=nam+j;
		document.getElementById(c).childNodes[6].innerHTML="<input class='input3' onblur='isNumber(this.value,"+x+")' type='textfield' value="+seq+" name="+nam+">";
		
		var ray=document.getElementsByName("rowArray");
		ray[j].checked=true;
	}
	j=j+1;
	<%}%>
}
function findEmp(){
var con=document.idAF.elements["contractId"].value.trim();
    if(con==null || con ==""){
    alert ("请先选择合同号!");
    }else{
	window.open('<%=request.getContextPath()%>/sysloan/showEmployeesListAC.do?orgid='+''+'&st='+''+'&cardnum='+''+'&empname='+''+'&indexs='+6+'&objInput=empId','','width=600,height=400,top=200,left=300,scrollbars=yes,status=yes');
	}
 }
function findEmp_wsh(){
    var con=document.idAF.elements["contractId"].value.trim();
    if(con==null || con ==""){
    alert ("请先选择合同号!");
    }else{
	window.open('<%=request.getContextPath()%>/sysloan/showEmployeesListAC.do?orgid='+''+'&st='+''+'&cardnum='+''+'&empname='+''+'&indexs='+1000+'&objInput=empId','','width=600,height=400,top=200,left=300,scrollbars=yes,status=yes');
   	//EmployeesShowAC
   }
}
function findEmp_wsha(){
    var con=document.idAF.elements["contractId"].value.trim();
    if(con==null || con ==""){
    alert ("请先选择合同号!");
    }else{
	window.open('<%=request.getContextPath()%>/sysloan/showEmployeesListAC.do?orgid='+''+'&st='+''+'&cardnum='+''+'&empname='+''+'&indexs='+1001+'&objInput=empId','','width=600,height=400,top=200,left=300,scrollbars=yes,status=yes');
   }
}
function findEmp_wshb(){
    var con=document.idAF.elements["contractId"].value.trim();
    if(con==null || con ==""){
    alert ("请先选择合同号!");
    }else{
	window.open('<%=request.getContextPath()%>/sysloan/showEmployeesListAC.do?orgid='+''+'&st='+''+'&cardnum='+''+'&empname='+''+'&indexs='+1002+'&objInput=empId','','width=600,height=400,top=200,left=300,scrollbars=yes,status=yes');
   }
}

function reportsErrors(){
<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
		</logic:messagesPresent>
		}
</script>
	<%--reportErrors() --%>
	<body bgcolor="#FFFFFF" text="#000000" onload="load();reportsErrors();">

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
							<td background="<%=path%>/img/table_bg_line.gif">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											办理公积金还贷
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loanreturnedbyfundTbForwardAC.do"
												class=a2>公积金还贷维护</a>
										</td>
									</tr>
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
											<font color="00B5DB">签订公积金还贷合同&gt;</font>
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
				<html:form action="/loanreturnedbyfundTaMainTianAC.do"
					style="margin:0">
					<td class=td3>

						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="176">
												<b class="font14">借款人基本信息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="734">
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
								<td class="td4" colspan="2">
									<html:text property="contractId" name="loanreturnedbyfundTaAF"
										styleClass="input3" onkeydown="gotoEnter();" />
								</td>

								<td width="11%">
									<input type="button" class="buttona" value="..."
										onClick="gotoContractpop('11','<%=path%>','0','0');">
								</td>



								<td class=td1 width="12%">
									办理日期
								</td>
								<td class="td4" colspan="2">
									<html:text property="bizTime" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="8" onblur="executeAjax();" />
								</td>
							</tr>

							<tr>
								<td class=td1 width="12%">
									借款人姓名
								</td>
								<td class="td4" width="17%">
									<html:text property="borrowerName"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									证件类型
								</td>
								<td class="td4" colspan="1">
									<html:text property="borrower_cardKind"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="18" readonly="true" />
								</td>
								<td width="12%" class=td1>
									&#35777;&#20214;&#21495;&#30721;&nbsp;
									<font color="#ff0000"><span class="STYLE1"></span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower_cardNum"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="18" readonly="true" />
								</td>

							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="175">
												<b class="font14">借款人公积金归集信息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="735">
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
								<td width="12%" bgcolor="#FFFFFF" class="td1">
									&#20511;&#27454;&#20154;&#32844;&#24037;&#32534;&#21495;
								</td>
								<td class="td4" width="8%">
									<html:text property="borrow_empid"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="11%">
									<input type="button" class="buttona" value="..."
										onClick="findEmp();">
								</td>

								<td width="12%">

								</td>
								<td colspan="2">

								</td>
								<td width="12%">
								</td>

								<td width="17%">
								</td>

							</tr>
							<tr>
								<td width="12%" class=td1>
									单位编号
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_orgid"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位名称
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_orgname"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_orgtel"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="20" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									邮政编码
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.orgMail"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									账户余额
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_ablncc"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="20" readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位地址
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_orgadder"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									&#26376;&#24037;&#36164;&#39069;&nbsp;
									<font color="#ff0000"><span class="STYLE1"></span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_monthsal"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									月缴存额
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_monthpay"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									账户状态
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_st" name="loanreturnedbyfundTaAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									初缴年月
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_bingdate"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									缴至年月
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_enddate"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									办事处
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_office"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="175">
												<b class="font14">借款人贷款信息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="735">
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
									月还本息
								</td>
								<td class="td4" colspan="2">
									<html:text property="yueHuanBenXi"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" readonly="true" />
								</td>
								<td width="12%" class=td1>
									最大提取金额
								</td>
								<td class="td4" width="17%">
									<html:text property="maxPickMoney"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									最多还款月数
								</td>
								<td class="td4" width="30%">
									<html:text property="maxMonth" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="20" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									贷款总额
								</td>
								<td class="td4" colspan="2">
									<html:text property="loanMoney" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									贷款期限
								</td>
								<td class="td4" width="17%">
									<html:text property="loanTime" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="20" readonly="true" />
								</td>
								<td width="12%" class=td1>
									未还本金
								</td>
								<td class="td4" width="30%">
									<html:text property="loanYuE" name="loanreturnedbyfundTaAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									剩余期限
									<font color="#ff0000"><span class="STYLE1"></span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="shengYuTime" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="8" readonly="true" />
								</td>
								
								<td width="12%" class=td1>
									还至年月
								</td>
								<td class="td4" width="17%">
									<html:text property="returnMonth" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="20" readonly="true" />
								</td>
								<td width="12%">

								</td>
								<td width="30%">

								</td>
							</tr>

						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="176">
												<b class="font14">共同借款人基本信息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="734">
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
									借款人姓名
								</td>
								<td class="td4" width="17%">
									<html:text property="name" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									证件类型
								</td>
								<td class="td4" width="17%">
									<html:text property="cardKind" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="18" readonly="true" />
								</td>
								<td width="12%" class=td1>
									&#35777;&#20214;&#21495;&#30721;&nbsp;
									<font color="#ff0000"><span class="STYLE1"></span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="cardNum" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="18" readonly="true" />
								</td>
							</tr>
							<tr>
								<td class=td1 width="12%">
									借款人姓名
								</td>
								<td class="td4" width="17%">
									<html:text property="namea" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									证件类型
								</td>
								<td class="td4" width="17%">
									<html:text property="cardKinda" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="18" readonly="true" />
								</td>
								<td width="12%" class=td1>
									&#35777;&#20214;&#21495;&#30721;&nbsp;
									<font color="#ff0000"><span class="STYLE1"></span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="cardNuma" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="18" readonly="true" />
								</td>

							</tr>
							<tr>
								<td class=td1 width="12%">
									借款人姓名
								</td>
								<td class="td4" width="17%">
									<html:text property="nameb" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									证件类型
								</td>
								<td class="td4" width="17%">
									<html:text property="cardKindb" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="18" readonly="true" />
								</td>
								<td width="12%" class=td1>
									&#35777;&#20214;&#21495;&#30721;&nbsp;
									<font color="#ff0000"><span class="STYLE1"></span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="cardNumb" name="loanreturnedbyfundTaAF"
										styleClass="input3" maxlength="18" readonly="true" />
								</td>

							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="175">
												<b class="font14">共同借款人公积金归集信息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="735">
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
								<td width="12%" bgcolor="#FFFFFF" class="td1">
									共同借款人职工编号
								</td>
								<td class="td4" width="8%">
									<html:text property="borrow_s_empid"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
									<html:hidden property="borrow_s_empname"
										name="loanreturnedbyfundTaAF" />
									<html:hidden property="borrow_s_cardnum"
										name="loanreturnedbyfundTaAF" />
								</td>
								<td width="11%">
									<input type="button" class="buttona" value="..."
										onClick="findEmp_wsh();">
								</td>

								<td width="12%" class=td1>
									初缴年月
								</td>
								<td class="td4" colspan="1">
									<html:text property="borrow_s_bingdate"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									缴至年月
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_enddate"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>

							</tr>
							<tr>
								<td width="12%" class=td1>
									单位编号
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_s_orgid"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位名称
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_orgname"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_s_orgtel"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="20" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									邮政编码
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_s_orgmail"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									账户余额
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_ablncc"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="20" readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位地址
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_s_orgadder"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									&#26376;&#24037;&#36164;&#39069;&nbsp;
									<font color="#ff0000"><span class="STYLE1"></span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_s_monthsal"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									月缴存额
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_monthpay"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									账户状态
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_s_st" name="loanreturnedbyfundTaAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" bgcolor="#FFFFFF" class="td1">
									辅助借款人职工编号
								</td>
								<td class="td4" width="8%">
									<html:text property="borrow_s_empida"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
									<html:hidden property="borrow_s_empnamea"
										name="loanreturnedbyfundTaAF" />
									<html:hidden property="borrow_s_cardnuma"
										name="loanreturnedbyfundTaAF" />
								</td>
								<td width="11%">
									<input type="button" class="buttona" value="..."
										onClick="findEmp_wsha();">
								</td>

								<td width="12%" class=td1>
									初缴年月
								</td>
								<td class="td4" colspan="1">
									<html:text property="borrow_s_bingdatea"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									缴至年月
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_enddatea"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									单位编号
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_s_orgida"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位名称
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_orgnamea"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_s_orgtela"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="20" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									邮政编码
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_s_orgmaila"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									账户余额
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_ablncca"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="20" readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位地址
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_s_orgaddera"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									&#26376;&#24037;&#36164;&#39069;&nbsp;
									<font color="#ff0000"><span class="STYLE1"></span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_s_monthsala"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									月缴存额
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_monthpaya"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									账户状态
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_s_sta"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" bgcolor="#FFFFFF" class="td1">
									辅助借款人职工编号
								</td>
								<td class="td4" width="8%">
									<html:text property="borrow_s_empidb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
									<html:hidden property="borrow_s_empnameb"
										name="loanreturnedbyfundTaAF" />
									<html:hidden property="borrow_s_cardnumb"
										name="loanreturnedbyfundTaAF" />
								</td>
								<td width="11%">
									<input type="button" class="buttona" value="..."
										onClick="findEmp_wshb();">
								</td>

								<td width="12%" class=td1>
									初缴年月
								</td>
								<td class="td4" colspan="1">
									<html:text property="borrow_s_bingdateb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									缴至年月
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_enddateb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>

							</tr>
							<tr>
								<td width="12%" class=td1>
									单位编号
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_s_orgidb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位名称
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_orgnameb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_s_orgtelb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="20" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									邮政编码
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_s_orgmailb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									账户余额
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_ablnccb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="20" readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位地址
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_s_orgadderb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									&#26376;&#24037;&#36164;&#39069;&nbsp;
									<font color="#ff0000"><span class="STYLE1"></span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrow_s_monthsalb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										maxlength="8" readonly="true" />
								</td>
								<td width="12%" class=td1>
									月缴存额
								</td>
								<td class="td4" width="17%">
									<html:text property="borrow_s_monthpayb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
								</td>
								<td width="12%" class=td1>
									账户状态
								</td>
								<td class="td4" width="30%">
									<html:text property="borrow_s_stb"
										name="loanreturnedbyfundTaAF" styleClass="input3"
										readonly="true" />
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
												<html:hidden name="loanreturnedbyfundTaAF" property="org_Id" />
												<html:hidden name="loanreturnedbyfundTaAF" property="empId" />
												<logic:empty name="new">
													<html:submit property="method" styleClass="buttona"
														onclick="return onSure();">
														<bean:message key="button.sure" />
													</html:submit>
												</logic:empty>
												<logic:notEmpty name="new">
													<html:submit property="method" styleClass="buttona"
														disabled="true" onclick="return onSure();">
														<bean:message key="button.sure" />
													</html:submit>
												</logic:notEmpty>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</html:form>
			</tr>
		</table>
		<form action="loanreturnedbyfundTaShowAC.do" method="POST"
			name="Form1" id="Form1">
		</form>
	</body>
</html>

