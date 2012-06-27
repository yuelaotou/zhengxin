<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page
	import="org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopListDTO"%>
<%@page import="java.util.*,java.math.BigDecimal"%>
<%
	String path = request.getContextPath();
	String type = "2";
	BigDecimal sumDebit = new BigDecimal(0.00);
	BigDecimal sumCredit = new BigDecimal(0.00);
	List list = new ArrayList();
	if (request.getAttribute("type") != null) {
		type = (String) request.getAttribute("type");
	}
	if (request.getAttribute("list") != null) {
		list = (List) request.getAttribute("list");
	}
	if (request.getAttribute("sumDebit") != null) {
		sumDebit = (BigDecimal) request.getAttribute("sumDebit");
	}
	if (request.getAttribute("sumCredit") != null) {
		sumCredit = (BigDecimal) request.getAttribute("sumCredit");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>凭证录入</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>

<script language="javascript" type="text/javascript">
var tr = "6";
var yg=0;
//每次增加两行

function addBlankRow(){
	var ddoTable=document.getElementById("ddoTable");
    var itemNum=ddoTable.rows.length-3;
	if(ddoTable.rows.length<202){
	
		var sumstr='';
		var ddoTable=document.getElementById("ddoTable");
		var office=document.getElementById("credenceFillinTaShowDTO.office").value.trim();
		var credenceCharacter=document.getElementById("credenceFillinTaShowDTO.credenceCharacter").value.trim();
		var oldCredenceNum=document.getElementById("credenceFillinTaShowDTO.oldCredenceNum").value.trim();
		var chargeUpDate=document.getElementById("credenceFillinTaShowDTO.chargeUpDate").value.trim();
		var settType=document.getElementById("credenceFillinTaShowDTO.settType").value.trim();
	
		for(i=0;i<ddoTable.rows.length-2;i++){
			var indexs=5*i+6;
			var summay=document.getElementById("summay"+i).value.trim(); 
			var freeSummay=document.getElementById("freeSummay"+i).value.trim();
			var subjectCode=document.getElementById("subjectCode"+i).value.trim();
			var debit=document.getElementById("debit"+i).value.trim();
			var credit=document.getElementById("credit"+i).value.trim();
			if(debit!="0"&&debit!=""&&(credit==""||credit=="0"||credit=="00")){
			if(debit.length>2){
				debit=debit.substr(0,debit.length-2)+"."+debit.substr(debit.length-2,debit.length);
			}else if(debit.length==2){
				debit="0."+debit.substr(debit.length-2,debit.length);
			}else{
				debit="0.0"+debit.substr(debit.length-2,debit.length);}
				credit="0";
			}
			if(credit!="0"&&credit!=""&&(debit=="" || debit=="0" ||debit=="00")){
			if(credit.length>2){
				credit=credit.substr(0,credit.length-2)+"."+credit.substr(credit.length-2,credit.length);
			}else if(credit.length==2){
				credit="0."+credit.substr(credit.length-2,credit.length);
			}else{
				credit="0.0"+credit.substr(credit.length-2,credit.length);}
				debit="0";
			}
			if(summay==null||summay==""){
				summay = "XXXXX";
			}
			if(freeSummay==null||freeSummay==""){
				freeSummay = "XXXXX";
			}
			if(subjectCode==null||subjectCode==""){
				subjectCode = "XXXXX";
			}
			if(debit==null||debit==""){
				debit = "0";
			}
			if(credit==null||credit==""){
				credit = "0";
			}
		 	listContent=summay+","+freeSummay+","+subjectCode+","+debit+","+credit+";";
		 	sumstr=sumstr+listContent;
		}
		document.forms[0].listAllContent.value=sumstr;
		document.forms[0].action="credenceFillinTaAddRowAC.do?index="+tr;
		document.forms[0].submit();
	
	}else{
		alert("已经到200条");
	}
}
   
//摘要-联想输入法执行AJAX
var key="";
var searchShowDiv="";
function executeAjax(keyword,search_suggest) {
	var str=document.getElementById(keyword).value.trim();
	str=encodeURI(str);
    if(str!=""){
    	if(window.event.keyCode!=38 && window.event.keyCode!=40 && window.event.keyCode!=13){
		    if(isLetter(str)){
		    	str=str.toUpperCase();
		    }
		    key=keyword;
			searchShowDiv=search_suggest;
			var queryString = "credenceFillinSerchSuggestAC.do?";
	        queryString = queryString + "search="+str; 
			findThinkInfo(key,queryString,searchShowDiv);
		}else if(window.event.keyCode==38){//向上
			var ss = document.getElementById(searchShowDiv);
			if(ss.innerHTML != ''){
				var str_yg=ss.innerHTML.toString().substring(ss.innerHTML.toString().lastIndexOf("\">")+2,ss.innerHTML.toString().lastIndexOf("<"));
				document.getElementById(keyword).value=str_yg;
				ss.style.display="none";
				ss.innerHTML="";
			}
		}else if(window.event.keyCode==40){//向下
			var ss = document.getElementById(searchShowDiv);
			if(ss.innerHTML != ''){
				var str_yg=ss.innerHTML.toString().substring(ss.innerHTML.toString().indexOf(">")+1,ss.innerHTML.toString().indexOf("<",ss.innerHTML.toString().indexOf(">")));
				document.getElementById(keyword).value=str_yg;
				ss.style.display="none";
				ss.innerHTML="";
			}
		}
	}else{
		var ss = document.getElementById(searchShowDiv);
		ss.style.display="none";
	}
}
function del_div(index) {
	var summaryObj = document.getElementById("search_suggest"+index);
	if(document.activeElement.id != "search_suggest"+index)
		summaryObj.style.display = "none";
}
//调用科目代码查询
function tosubject(indexs){
	gotoSubjectpop('0','<%=path%>',indexs);
}
//调出摘要弹出框
function tosummay(summayi){
	gotoSummaypop('<%=path%>',summayi);
}
//判断是否是最末一级科目
function executeAjaxIn(indexs){
    var str=document.getElementsByTagName("input")[indexs].value.trim();
    if(str!=""){
    var queryString = "credenceFillinIsEndSubjectAAC.do?";
    queryString = queryString + "subjectCode="+str+"&"+"indexs="+indexs; 
    findInfo(queryString);}
}
//弹出提示不是末级科目代码
function displays(indexs,subjectId,subjectName,subjectCodeCount){
var cueAlert;
if(subjectId==""){
alert("科目代码不存在！");
document.getElementsByTagName("input")[indexs].value="";
document.getElementsByTagName("input")[indexs].focus();
return false;
}
if(subjectCodeCount!="0"){
alert("请选择末级科目代码");
document.getElementsByTagName("input")[indexs].value="";
document.getElementsByTagName("input")[indexs].focus();
return false;
}
if(subjectId!=""&&subjectCodeCount=="0"){
document.getElementsByTagName("input")[indexs].focus();
}
}
//离开科目代码时判断是否是最末一级科目
function executeAjaxIn1(indexs){
    var str=document.getElementsByTagName("input")[indexs].value.trim();
    if(str!=""){
    var queryString = "credenceFillinIsEndSubjectSecAAC.do?";
    queryString = queryString + "subjectCode="+str+"&"+"indexs="+indexs; 
    findInfo(queryString);}
}
//离开科目代码时弹出提示不是末级科目代码
function displays1(indexs,subjectId,subjectName,subjectCodeCount){
var cueAlert;
var str=document.getElementsByTagName("input")[indexs].value;
document.getElementsByTagName("input")[indexs].value=str+" "+subjectName;
if(subjectId==""){
alert("科目代码不存在！");
document.getElementsByTagName("input")[indexs].focus();
document.getElementsByTagName("input")[indexs].value="";
return false;
}
if(subjectCodeCount!="0"){
alert("请选择末级科目代码");
document.getElementsByTagName("input")[indexs].focus();
document.getElementsByTagName("input")[indexs].value="";
return false;
}

}
//截取字符串中的数字
function getNum(code){
var str=document.getElementById(code).value;
if(str!=""){
if(str.match(/\s/)!=null){
document.getElementById(code).value=str.substr(0,str.indexOf(" "));
}
else{
document.getElementById(code).value=str;
}
}
}

//判断数据库中是否存在此摘要名
function executeAjaxSummay(summayName,i){
    var str=document.getElementById(summayName).value.trim();
    if(str!=""){
    var pinyin = toPinyinShengmu(str);
    str=encodeURI(str);
    var queryString = "credenceFillinIsSummayAAC.do?";
    queryString = queryString + "summay="+str+"&"+"i="+i+"&pinyin="+pinyin;  
    findInfo(queryString);}
}
//弹出提示不存在此摘要名
function displaySummay(cueAlert,i){
var cueAlert=cueAlert;
if(cueAlert=="false"){
var j=parseInt(i)+1;
alert("第"+j+"行摘要名称不是系统摘要名称，请重新输入！");
document.getElementById("summay"+i).value="";
document.getElementById('flag').value="1";
return false;}
else{
document.getElementById('flag').value="0";}

}
//选择办事处后，显示日期和凭证号
function executeAjaxNumDate(){
	var office=document.getElementById("credenceFillinTaShowDTO.office").value.trim();
	var queryString = "credenceFillinNumAndDateAAC.do?";
	queryString = queryString + "office="+office; 
    findInfo(queryString);

}
//给日期和凭证号附值
function displaysNumDate(credenceDate,credenceNum){
document.getElementById("credenceFillinTaShowDTO.chargeUpDate").value=credenceDate;
document.getElementById("credenceFillinTaShowDTO.credenceNum").value=credenceNum;
}
//修改日期后，动态重新生成凭证号
function executeAjaxNumByDate(){
	var office=document.getElementById("credenceFillinTaShowDTO.office").value.trim();
	var credenceDate=document.getElementById("credenceFillinTaShowDTO.chargeUpDate").value.trim();
	var date=document.getElementById("credenceFillinTaShowDTO.chargeUpDate");
	var typeButton=document.getElementById("typeButton").value.trim();
	if(!checkDate(date)){
		document.getElementById("credenceFillinTaShowDTO.chargeUpDate").focus();
	}else{
		var queryString = "credenceFillinGetNumByDateAAC.do?";
		queryString = queryString + "office="+office+"&credenceDate="+credenceDate+"&button="+typeButton;
	    findInfo(queryString);
	}
}
//给凭证号附值
function displaysNumByDate(credenceNum,error){
	if(error!=""){
		alert(error);
		document.getElementById("credenceFillinTaShowDTO.chargeUpDate").focus();
	}else{
		document.getElementById("credenceFillinTaShowDTO.credenceNum").value=credenceNum;
	}
}

//对列求和
function sum1(sumName,v) 
{
	var a=0;
	var ddoTable=document.getElementById("ddoTable");
	for(i=0;i<ddoTable.rows.length-2;i++)
	{
		if(document.getElementById(sumName+i).value!=""){
			var b=document.getElementById(sumName+i).value.trim();
  			a+=parseFloat(b);
  		}
	}
	document.getElementById('sum'+v).value=a;
}
//得到余额并且判断方向
function executeAjaxDir(i){
	var office=document.getElementById("credenceFillinTaShowDTO.office").value.trim();
	var subjectCode=document.getElementById("subjectCode"+i).value.trim();
	if(subjectCode!=""&&office!=""){
		office=encodeURI(office);
		subjectCode=encodeURI(subjectCode);
		var queryString = "credenceFillinGetBalaDirAAC.do?";
		queryString = queryString + "office="+office+"&subjectCode="+subjectCode; 
		    findInfo(queryString);
	}else{
		document.getElementById("balanceDirection").value="";
	}
}
function displayBalaDir(balance,direction){
	document.getElementById("balanceDirection").value=balance;
}
//判断必填字段不可为空
function validate(eee){
	var sumstr='',h='',i='',j='',k='',l='',m='';
	var ddoTable=document.getElementById("ddoTable");
	var office=document.getElementById("credenceFillinTaShowDTO.office").value.trim();
	var credenceCharacter=document.getElementById("credenceFillinTaShowDTO.credenceCharacter").value.trim();
	var oldCredenceNum=document.getElementById("credenceFillinTaShowDTO.oldCredenceNum").value.trim();
	var chargeUpDate=document.getElementById("credenceFillinTaShowDTO.chargeUpDate").value.trim();
	var settType=document.getElementById("credenceFillinTaShowDTO.settType").value.trim();
	if(office==""){h="所属办事处 ";}else{h="";}
	if(credenceCharacter==""){i="凭证字 ";}else{i="";}
	if(oldCredenceNum==""){j="附单据 ";}else{j="";}
	if(chargeUpDate==""){k="日期 ";}else{k="";}
	if(settType==""){l="结算方式 ";}else{l="";}
	m=h+i+j+k+l;
	if(m!=""){
		alert(m+"不可为空！");
		return false;
	}
	for(i=0;i<ddoTable.rows.length-2;i++){
     	var a="",b="",c="",d="",e="",f="",g="",indexs=5*i+6;
      	var summay=document.getElementById("summay"+i).value.trim(); 
      	var freeSummay=document.getElementById("freeSummay"+i).value.trim();
      	if(freeSummay.indexOf(",")!=-1){
      		freeSummay=freeSummay.replace(",","，");
      	}
      	var subjectCode=document.getElementById("subjectCode"+i).value.trim();
      	var debit=document.getElementById("debit"+i).value.trim();
      	var credit=document.getElementById("credit"+i).value.trim();
	 	if(summay!=""||freeSummay!=""||subjectCode!=""||debit!=""||credit!=""){
	 		var j=i+1;
		 	if(summay!=""){
		 		if(summay.indexOf(",")!=-1)
		 		{
		 			alert("第"+j+"行摘要不允许含有逗号");
			 		return false;
		 		}
		 		a="";
			 	executeAjaxSummay("summay"+i,i);
			 	if(document.getElementById('flag').value=="1"){
			 		return false;
		 		}
		 	}else{
		 		a="摘要 "
		 	}
		 	if(subjectCode!=""){c=""}else{c="科目代码 "}
		 	f=a+c;
		 	if(f!=""){
			 	alert("第"+j+"行"+f+"不可为空!");
			 	return false;
		 	}
		 	if((debit==""&&credit=="")||(debit=="0"&&credit=="0"||(debit==""&&credit=="0")||(debit=="0"&&credit==""))){
		 		alert('第'+j+'行借方金额或者贷方金额必须输入一项！');
		  		return false;
		 	}
		
		 	if(debit!="0"&&debit!=""&&credit!="0"&&credit!=""){
				alert('第'+j+'行借方金额或者贷方金额只能输入一项！');
				return false;
			}
			if(debit!="0"&&debit!=""&&(credit==""||credit=="0")){
				if(debit.length>2){
					debit=debit.substr(0,debit.length-2)+"."+debit.substr(debit.length-2,debit.length);
				}
				else if(debit.length==2){
					debit="0."+debit.substr(debit.length-2,debit.length);
				}
				else{
					debit="0.0"+debit.substr(debit.length-2,debit.length);
				}
					credit="0";
			}
			if(credit!="0"&&credit!=""&&(debit==""||debit=="0")){
				if(credit.length>2){
					credit=credit.substr(0,credit.length-2)+"."+credit.substr(credit.length-2,credit.length);
				}
				else if(credit.length==2){
					credit="0."+credit.substr(credit.length-2,credit.length);
				}
				else{
					credit="0.0"+credit.substr(credit.length-2,credit.length);
				}
				debit="0";
			}
		 	if(summay!=""&&subjectCode!=""){
			 	if(subjectCode.match(/\s/)!=null){
					subjectCode=subjectCode.substr(0,subjectCode.indexOf(" "));
				}
				else{
					subjectCode=subjectCode;
				}
			 	listContent=summay+","+freeSummay+","+subjectCode+","+debit+","+credit+";";
		 		sumstr=sumstr+listContent;
		 	}
		}
		if(summay==""&&freeSummay==""&&subjectCode==""&&debit==""&&credit==""){
			sumstr=sumstr;
		}
	}
	if(sumstr==""){
		alert("请输入数据！");
		return false;
	}
	var sum3 = document.getElementById("sum3").value.trim();
	var sum4 = document.getElementById("sum4").value.trim();
	if(document.getElementById("sum3").value.trim()!=document.getElementById("sum4").value.trim()){
		alert("合计借方金额≠合计贷方金额，请核实！");
		return false;
	}
	document.forms[0].listAllContent.value=sumstr;
	setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');
	formSubmit();
}
//判断结算号在FN201表中是否存在
function executeAjaxNum(){
	var settNum=document.getElementById("credenceFillinTaShowDTO.settNum").value.trim();
	var bookId=document.getElementById("credenceFillinTaShowDTO.bookId").value.trim();
	if(settNum!=""){
		var queryString = "credenceFillinIsSettNumAAC.do?";
		queryString = queryString + "settNum="+settNum+"&bookId="+bookId; 
		findInfo(queryString);
	}
}
function displayss(cueAlert){
	if(cueAlert==""){
		document.forms[0].submit();}
	else{
		alert(cueAlert);
		return false;
	}
}

function formSubmit(){
	var typeButton=document.getElementById("typeButton").value.trim();
	var settNum=document.getElementById("credenceFillinTaShowDTO.settNum").value.trim();
	if(typeButton==null||typeButton==''){
		if(settNum==""){
			document.forms[0].submit();}
		else{
			executeAjaxNum();
		}
	}else{
		document.forms[0].action="credenceFillinTaUpdateAC.do";
		document.forms[0].submit();
	}
}

function updateAddRow(b){
	var ddoTable=document.getElementById("ddoTable");
 	var row = ddoTable.insertRow(ddoTable.rows.length-1);
  	var cell = row.insertCell();
  	var cell1 = row.insertCell();
  	var cell2 = row.insertCell();
  	var cell3 = row.insertCell();
  	var cell4 = row.insertCell();
  	var summay='summay'+b;
  	var freeSummay='freeSummay'+b;
  	var search_suggest='search_suggest'+b;
  	var subjectCode='subjectCode'+b;
  	var debit='debit'+b;
  	var credit='credit'+b;
  	var inputNum=5*b+6;
  	var b1=b-1;
  	row.onclick=function(){gettr(b+1);};
  	//row.onkeydown=function(){keyAddRow(b+1);};
  	var summayPre='summay'+b1;
  	var subjectCodePre='subjectCode'+b1;
  	cell.innerHTML='<input type="text" name='+summay+' class="input8" autocomplete="off" onkeyup="executeAjax(\''+summay+'\',\''
  		+search_suggest+'\')" onfocus="copyPreRow(\''+summay+'\',\''+
  		summayPre+'\')" onblur="del_div('+b+');" onkeydown="enterNextFocus1();"> <a onclick="tosummay('+b+')" style="cursor:hand"><img src="<%=path%>/img/8.png"></a><br /><div id='+search_suggest+' style="display:none;position: absolute;background-color: #FFFFFF;text-align: left;border: 1px solid #000000;"></div>'  	
    cell1.innerHTML = '<input type="text" name='+freeSummay+' class="input8" onkeydown="enterNextFocus1();">';
    cell2.innerHTML ='<input type="text" name='+subjectCode+' class="input8" onkeydown="gotoKey(\''+debit+'\');" onblur="executeAjaxIn1('+inputNum+')" onfocus="getNum(\''+subjectCode+'\'),executeAjaxDir('+b+');copyPreRow(\''+subjectCode+'\',\''+subjectCodePre+'\');"> <a onclick="tosubject('+inputNum+')" style="cursor:hand"><img src="<%=path%>/img/8.png"></a>';
    cell3.innerHTML ='<input type="text" name='+debit+' class="input6" autocomplete="off" style="ime-mode:disabled" onchange="sum1(\'debit\',3)" onfocus="banlance(\''+debit+'\')" onBlur="clearZero(\''+debit+'\');" onkeydown="onlyNum(),deleEndTwo(\''+debit+'\'),gotoKey(\''+credit+'\');" onkeyup="banlance(\''+debit+'\')" onpaste="return false">';
 	cell4.innerHTML ='<input type="text" name='+credit+' class="input6" autocomplete="off" style="ime-mode:disabled" onchange="sum1(\'credit\',4)" onfocus="banlance(\''+credit+'\')" onBlur="clearZero(\''+credit+'\');" onkeydown="onlyNum(),deleEndTwo(\''+credit+'\'),keyAddRow('+(b+1)+'),enterNextFocus1();" onkeyup="banlance(\''+credit+'\')" onpaste="return false">';
		
}
function init(){
	if(<%=type%>!="2"){
	 	var b,y=<%=list.size()%>;
	 	for(b=0;b<y;b++){
	 		if(b>5){
	   			updateAddRow(b);
	 		}
		}
		init1();
	}
}
function init1(){
	if(<%=type%>!="2"){
		document.getElementById("sum3").value="<%=sumDebit%>";
		document.getElementById("sum4").value="<%=sumCredit%>";
		<%  
		for(int i=0;i<list.size();i++){
			CredencePopListDTO credencePopListDTO = (CredencePopListDTO)list.get(i);	
		 %>
	 	document.getElementById("summay<%=i%>").value="<%=credencePopListDTO.getSummay()%>";
	 	document.getElementById("freeSummay<%=i%>").value="<%=credencePopListDTO.getFreeSummay()%>";
	 	document.getElementById("subjectCode<%=i%>").value="<%=credencePopListDTO.getSubjectCode()%>";
	 	document.getElementById("debit<%=i%>").value="<%=credencePopListDTO.getDebit()%>";
	 	document.getElementById("credit<%=i%>").value="<%=credencePopListDTO.getCredit()%>";
		<%
		 	if(credencePopListDTO.getDebit().compareTo(new BigDecimal(0.00))==0){
		%>
		 		document.getElementById("debit<%=i%>").value=""; 
		<%
			}
			if(credencePopListDTO.getCredit().compareTo(new BigDecimal(0.00))==0){
		%>
		 		document.getElementById("credit<%=i%>").value=""; 
		<%
			}
		}
	%>
		var ddoTable=document.getElementById("ddoTable");
		var index = ddoTable.rows.length-3;
		if(<%=type%>!="1")
			document.getElementById("summay"+(index)).focus();
	}
 	
}

//焦点从结算日期跳到出纳
function gotoAccpsn(){
	if(event.keyCode==13){
		document.getElementById("credenceFillinTaShowDTO.accountpsn").focus();
	}
}
//焦点从主管会计跳到确定按钮
function gotoButton(){
	if(event.keyCode==13){
		document.getElementById("Button1").focus();
		return false;
	}
}
//默认进入凭证录入页选中所属办事处
function selectedoff(){
	var bookSt=document.forms[0].elements["bookSt"].value;
	var typeButton=document.getElementById("typeButton").value.trim();
	if(typeButton==null||typeButton==''){
		if(bookSt==0){
			alert('该账套未启用！');
			document.URL="<%=path%>/toindex.jsp";
		}
	}
}
//默认余额值为00
function banlance(key){
	if(document.getElementById(key).value==""){
		document.getElementById(key).value="00";
		document.getElementById(key).focus();
	}
}
//焦点离开的时候,将余额仅是00的清空
function clearZero(balaName){
	if(parseFloat(document.getElementById(balaName).value)==0){
		document.getElementById(balaName).value="";
	}
}
//焦点从会计科目跳到借方金额,借方金额跳到贷方金额
function gotoKey(key){
	if(event.keyCode==13){
		if(document.getElementById(key).value==""){
			document.getElementById(key).value="00";
		}
		document.getElementById(key).focus();
	}
}
//点击小数点删除余额最后的两位00
function deleEndTwo(balaName){
	var a=document.getElementById(balaName).value;
	if((event.keyCode==110||event.keyCode==190)&&(a.length>=2)&&(a.substr(a.length-2,a.length)=="00")){
		document.getElementById(balaName).value=a.substr(0,a.length-2);
	}
}
function formatDate(date){
	if(!checkDate(date)){
		document.getElementById("credenceFillinTaShowDTO.chargeUpDate").focus();
	}
	else{
		return true;
	}
}
function gettr(index){
	tr=index;
}
function keyAddRow(index){
	var ddoTable=document.getElementById("ddoTable");
	var row = ddoTable.rows.length-2;
	var rowIndex = index;
	if(row == rowIndex){
		tr = row ;
		if(event.keyCode==13){
			addBlankRow();
		}
	}
}

function delBlankRow(){

if(tr==null&&tr==0){
	alert("请选择要删除的行");
	return false;
}
var sumstr='';
var ddoTable=document.getElementById("ddoTable");
var office=document.getElementById("credenceFillinTaShowDTO.office").value.trim();
var credenceCharacter=document.getElementById("credenceFillinTaShowDTO.credenceCharacter").value.trim();
var oldCredenceNum=document.getElementById("credenceFillinTaShowDTO.oldCredenceNum").value.trim();
var chargeUpDate=document.getElementById("credenceFillinTaShowDTO.chargeUpDate").value.trim();
var settType=document.getElementById("credenceFillinTaShowDTO.settType").value.trim();

for(i=0;i<ddoTable.rows.length-2;i++){
	var indexs=5*i+6;
	var summay=document.getElementById("summay"+i).value.trim(); 
	var freeSummay=document.getElementById("freeSummay"+i).value.trim();
	var subjectCode=document.getElementById("subjectCode"+i).value.trim();
	var debit=document.getElementById("debit"+i).value.trim();
	var credit=document.getElementById("credit"+i).value.trim();
	
	if(debit!="0"&&debit!=""&&(credit==""||credit=="0")){
		if(debit.length>2){
		debit=debit.substr(0,debit.length-2)+"."+debit.substr(debit.length-2,debit.length);
	}else if(debit.length==2){
		debit="0."+debit.substr(debit.length-2,debit.length);
	}else{
		debit="0.0"+debit.substr(debit.length-2,debit.length);}
		credit="0";
	}
	if(credit!="0"&&credit!=""&&(debit==""||debit=="0")){
		if(credit.length>2){
		credit=credit.substr(0,credit.length-2)+"."+credit.substr(credit.length-2,credit.length);
	}else if(credit.length==2){
		credit="0."+credit.substr(credit.length-2,credit.length);
	}else{
		credit="0.0"+credit.substr(credit.length-2,credit.length);}
		debit="0";
	}
	
	if(summay==null||summay==""){
		summay = "XXXXX";
	}
	if(freeSummay==null||freeSummay==""){
		freeSummay = "XXXXX";
	}
	if(subjectCode==null||subjectCode==""){
		subjectCode = "XXXXX";
	}
	if(debit==null||debit==""){
		debit = "0";
	}
	if(credit==null||credit==""){
		credit = "0";
	}
 	listContent=summay+","+freeSummay+","+subjectCode+","+debit+","+credit+";";
 	sumstr=sumstr+listContent;
}
	document.forms[0].listAllContent.value=sumstr;
	document.forms[0].action="credenceFillinTaDelRowAC.do?index="+tr;
	document.forms[0].submit();
}
function copyPreRow(textNow,textPre){
	var debit=sum_jj('debit',3);
	var credit=sum_jj('credit',4);
	var textNowContent=document.getElementById(textNow).value;
	if(debit != credit){
		if(textNowContent==""){
			var str=document.getElementById(textPre).value;
			if(str.indexOf(" ")==-1){
				document.getElementById(textNow).value=str;
			}else{
				document.getElementById(textNow).value=str.substr(0,str.indexOf(" "));
				document.getElementById(textNow).select();
			}
		}
	}
}
//对列求和
function sum_jj(sumName,v) 
{
var a=0;
var ddoTable=document.getElementById("ddoTable");
for(i=0;i<ddoTable.rows.length-2;i++)
{if(document.getElementById(sumName+i).value!=""){
var b=document.getElementById(sumName+i).value.trim();
  a+=parseFloat(b);
  }
}
return a;
  }
function reportErrors() {
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
}
</script>
<script type="text/javascript">
			 var key2code = {65:"a",66:"b",67:"c",68:"d",69:"e",70:"f",71:"g",72:"h",73:"i",74:"j",
			               ೋ:"k",76:"l",77:"m",78:"n",79:"o",80:"p",81:"q",82:"r",83:"s",84:"t",
			               ೕ:"u",86:"v",87:"w",88:"x",89:"y",90:"z",49:"1",50:"2",51:"3",52:"4",
			               ವ:"5",54:"6",55:"7",56:"8",57:"9",48:"0"
			                };
			</script>
<script language=javascript>
var spell = {0xB0A1:"a", 0xB0A3:"ai", 0xB0B0:"an", 0xB0B9:"ang", 0xB0BC:"ao", 0xB0C5:"ba", 0xB0D7:"bai", 0xB0DF:"ban", 0xB0EE:"bang", 0xB0FA:"bao", 0xB1AD:"bei", 0xB1BC:"ben", 0xB1C0:"beng", 0xB1C6:"bi", 0xB1DE:"bian", 0xB1EA:"biao", 0xB1EE:"bie", 0xB1F2:"bin", 0xB1F8:"bing", 0xB2A3:"bo", 0xB2B8:"bu", 0xB2C1:"ca", 0xB2C2:"cai", 0xB2CD:"can", 0xB2D4:"cang", 0xB2D9:"cao", 0xB2DE:"ce", 0xB2E3:"ceng", 0xB2E5:"cha", 0xB2F0:"chai", 0xB2F3:"chan", 0xB2FD:"chang", 0xB3AC:"chao", 0xB3B5:"che", 0xB3BB:"chen", 0xB3C5:"cheng", 0xB3D4:"chi", 0xB3E4:"chong", 0xB3E9:"chou", 0xB3F5:"chu", 0xB4A7:"chuai", 0xB4A8:"chuan", 0xB4AF:"chuang", 0xB4B5:"chui", 0xB4BA:"chun", 0xB4C1:"chuo", 0xB4C3:"ci", 0xB4CF:"cong", 0xB4D5:"cou", 0xB4D6:"cu", 0xB4DA:"cuan", 0xB4DD:"cui", 0xB4E5:"cun", 0xB4E8:"cuo", 0xB4EE:"da", 0xB4F4:"dai", 0xB5A2:"dan", 0xB5B1:"dang", 0xB5B6:"dao", 0xB5C2:"de", 0xB5C5:"deng", 0xB5CC:"di", 0xB5DF:"dian", 0xB5EF:"diao", 0xB5F8:"die", 0xB6A1:"ding", 0xB6AA:"diu", 0xB6AB:"dong", 0xB6B5:"dou", 0xB6BC:"du", 0xB6CB:"duan", 0xB6D1:"dui", 0xB6D5:"dun", 0xB6DE:"duo", 0xB6EA:"e", 0xB6F7:"en", 0xB6F8:"er", 0xB7A2:"fa", 0xB7AA:"fan", 0xB7BB:"fang", 0xB7C6:"fei", 0xB7D2:"fen", 0xB7E1:"feng", 0xB7F0:"fo", 0xB7F1:"fou", 0xB7F2:"fu", 0xB8C1:"ga", 0xB8C3:"gai", 0xB8C9:"gan", 0xB8D4:"gang", 0xB8DD:"gao", 0xB8E7:"ge", 0xB8F8:"gei", 0xB8F9:"gen", 0xB8FB:"geng", 0xB9A4:"gong", 0xB9B3:"gou", 0xB9BC:"gu", 0xB9CE:"gua", 0xB9D4:"guai", 0xB9D7:"guan", 0xB9E2:"guang", 0xB9E5:"gui", 0xB9F5:"gun", 0xB9F8:"guo", 0xB9FE:"ha", 0xBAA1:"hai", 0xBAA8:"han", 0xBABB:"hang", 0xBABE:"hao", 0xBAC7:"he", 0xBAD9:"hei", 0xBADB:"hen", 0xBADF:"heng", 0xBAE4:"hong", 0xBAED:"hou", 0xBAF4:"hu", 0xBBA8:"hua", 0xBBB1:"huai", 0xBBB6:"huan", 0xBBC4:"huang", 0xBBD2:"hui", 0xBBE7:"hun", 0xBBED:"huo", 0xBBF7:"ji", 0xBCCE:"jia", 0xBCDF:"jian", 0xBDA9:"jiang", 0xBDB6:"jiao", 0xBDD2:"jie", 0xBDED:"jin", 0xBEA3:"jing", 0xBEBC:"jiong", 0xBEBE:"jiu", 0xBECF:"ju", 0xBEE8:"juan", 0xBEEF:"jue", 0xBEF9:"jun", 0xBFA6:"ka", 0xBFAA:"kai", 0xBFAF:"kan", 0xBFB5:"kang", 0xBFBC:"kao", 0xBFC0:"ke", 0xBFCF:"ken", 0xBFD3:"keng", 0xBFD5:"kong", 0xBFD9:"kou", 0xBFDD:"ku", 0xBFE4:"kua", 0xBFE9:"kuai", 0xBFED:"kuan", 0xBFEF:"kuang", 0xBFF7:"kui", 0xC0A4:"kun", 0xC0A8:"kuo", 0xC0AC:"la", 0xC0B3:"lai", 0xC0B6:"lan", 0xC0C5:"lang", 0xC0CC:"lao", 0xC0D5:"le", 0xC0D7:"lei", 0xC0E2:"leng", 0xC0E5:"li", 0xC1A9:"lia", 0xC1AA:"lian", 0xC1B8:"liang", 0xC1C3:"liao", 0xC1D0:"lie", 0xC1D5:"lin", 0xC1E1:"ling", 0xC1EF:"liu", 0xC1FA:"long", 0xC2A5:"lou", 0xC2AB:"lu", 0xC2BF:"lv", 0xC2CD:"luan", 0xC2D3:"lue", 0xC2D5:"lun", 0xC2DC:"luo", 0xC2E8:"ma", 0xC2F1:"mai", 0xC2F7:"man", 0xC3A2:"mang", 0xC3A8:"mao", 0xC3B4:"me", 0xC3B5:"mei", 0xC3C5:"men", 0xC3C8:"meng", 0xC3D0:"mi", 0xC3DE:"mian", 0xC3E7:"miao", 0xC3EF:"mie", 0xC3F1:"min", 0xC3F7:"ming", 0xC3FD:"miu", 0xC3FE:"mo", 0xC4B1:"mou", 0xC4B4:"mu", 0xC4C3:"na", 0xC4CA:"nai", 0xC4CF:"nan", 0xC4D2:"nang", 0xC4D3:"nao", 0xC4D8:"ne", 0xC4D9:"nei", 0xC4DB:"nen", 0xC4DC:"neng", 0xC4DD:"ni", 0xC4E8:"nian", 0xC4EF:"niang", 0xC4F1:"niao", 0xC4F3:"nie", 0xC4FA:"nin", 0xC4FB:"ning", 0xC5A3:"niu", 0xC5A7:"nong", 0xC5AB:"nu", 0xC5AE:"nv", 0xC5AF:"nuan", 0xC5B0:"nue", 0xC5B2:"nuo", 0xC5B6:"o", 0xC5B7:"ou", 0xC5BE:"pa", 0xC5C4:"pai", 0xC5CA:"pan", 0xC5D2:"pang", 0xC5D7:"pao", 0xC5DE:"pei", 0xC5E7:"pen", 0xC5E9:"peng", 0xC5F7:"pi", 0xC6AA:"pian", 0xC6AE:"piao", 0xC6B2:"pie", 0xC6B4:"pin", 0xC6B9:"ping", 0xC6C2:"po", 0xC6CB:"pu", 0xC6DA:"qi", 0xC6FE:"qia", 0xC7A3:"qian", 0xC7B9:"qiang", 0xC7C1:"qiao", 0xC7D0:"qie", 0xC7D5:"qin", 0xC7E0:"qing", 0xC7ED:"qiong", 0xC7EF:"qiu", 0xC7F7:"qu", 0xC8A6:"quan", 0xC8B1:"que", 0xC8B9:"qun", 0xC8BB:"ran", 0xC8BF:"rang", 0xC8C4:"rao", 0xC8C7:"re", 0xC8C9:"ren", 0xC8D3:"reng", 0xC8D5:"ri", 0xC8D6:"rong", 0xC8E0:"rou", 0xC8E3:"ru", 0xC8ED:"ruan", 0xC8EF:"rui", 0xC8F2:"run", 0xC8F4:"ruo", 0xC8F6:"sa", 0xC8F9:"sai", 0xC8FD:"san", 0xC9A3:"sang", 0xC9A6:"sao", 0xC9AA:"se", 0xC9AD:"sen", 0xC9AE:"seng", 0xC9AF:"sha", 0xC9B8:"shai", 0xC9BA:"shan", 0xC9CA:"shang", 0xC9D2:"shao", 0xC9DD:"she", 0xC9E9:"shen", 0xC9F9:"sheng", 0xCAA6:"shi", 0xCAD5:"shou", 0xCADF:"shu", 0xCBA2:"shua", 0xCBA4:"shuai", 0xCBA8:"shuan", 0xCBAA:"shuang", 0xCBAD:"shui", 0xCBB1:"shun", 0xCBB5:"shuo", 0xCBB9:"si", 0xCBC9:"song", 0xCBD1:"sou", 0xCBD4:"su", 0xCBE1:"suan", 0xCBE4:"sui", 0xCBEF:"sun", 0xCBF2:"suo", 0xCBFA:"ta", 0xCCA5:"tai", 0xCCAE:"tan", 0xCCC0:"tang", 0xCCCD:"tao", 0xCCD8:"te", 0xCCD9:"teng", 0xCCDD:"ti", 0xCCEC:"tian", 0xCCF4:"tiao", 0xCCF9:"tie", 0xCCFC:"ting", 0xCDA8:"tong", 0xCDB5:"tou", 0xCDB9:"tu", 0xCDC4:"tuan", 0xCDC6:"tui", 0xCDCC:"tun", 0xCDCF:"tuo", 0xCDDA:"wa", 0xCDE1:"wai", 0xCDE3:"wan", 0xCDF4:"wang", 0xCDFE:"wei", 0xCEC1:"wen", 0xCECB:"weng", 0xCECE:"wo", 0xCED7:"wu", 0xCEF4:"xi", 0xCFB9:"xia", 0xCFC6:"xian", 0xCFE0:"xiang", 0xCFF4:"xiao", 0xD0A8:"xie", 0xD0BD:"xin", 0xD0C7:"xing", 0xD0D6:"xiong", 0xD0DD:"xiu", 0xD0E6:"xu", 0xD0F9:"xuan", 0xD1A5:"xue", 0xD1AB:"xun", 0xD1B9:"ya", 0xD1C9:"yan", 0xD1EA:"yang", 0xD1FB:"yao", 0xD2AC:"ye", 0xD2BB:"yi", 0xD2F0:"yin", 0xD3A2:"ying", 0xD3B4:"yo", 0xD3B5:"yong", 0xD3C4:"you", 0xD3D9:"yu", 0xD4A7:"yuan", 0xD4BB:"yue", 0xD4C5:"yun", 0xD4D1:"za", 0xD4D4:"zai", 0xD4DB:"zan", 0xD4DF:"zang", 0xD4E2:"zao", 0xD4F0:"ze", 0xD4F4:"zei", 0xD4F5:"zen", 0xD4F6:"zeng", 0xD4FA:"zha", 0xD5AA:"zhai", 0xD5B0:"zhan", 0xD5C1:"zhang", 0xD5D0:"zhao", 0xD5DA:"zhe", 0xD5E4:"zhen", 0xD5F4:"zheng", 0xD6A5:"zhi", 0xD6D0:"zhong", 0xD6DB:"zhou", 0xD6E9:"zhu", 0xD7A5:"zhua", 0xD7A7:"zhuai", 0xD7A8:"zhuan", 0xD7AE:"zhuang", 0xD7B5:"zhui", 0xD7BB:"zhun", 0xD7BD:"zhuo", 0xD7C8:"zi", 0xD7D7:"zong", 0xD7DE:"zou", 0xD7E2:"zu", 0xD7EA:"zuan", 0xD7EC:"zui", 0xD7F0:"zun", 0xD7F2:"zuo"}

var spellArray = new Array()
var pn = ""
/*for (var i=0xB0A1; i<0xD7FC; i++)
{
 if (spell[i]) pn = spell[i]
 execScript("char=chr(\""+i+"\")", "vbscript")
 spellArray[char.charCodeAt(0)] = pn
}*/

function pinyin(char)
{
 if (!char.charCodeAt(0) ||char.charCodeAt(0) < 1328) return char;
 if (spellArray[char.charCodeAt(0)]) return spellArray[char.charCodeAt(0)]
 execScript("ascCode=hex(asc(\""+char+"\"))", "vbscript")
 ascCode = eval("0x"+ascCode)
 if (!(ascCode>0xB0A0 && ascCode<0xD7FC)) return char;
 for (var i=ascCode; (!spell[i] && i>0);) i--
 return spell[i]
}

function toPinyin(str)
{
 	var pStr = ""
 	for (var i=0; i<str.length; i++)
 	{
  		if (str.charAt(i) == "\n") 
  			pStr += "<br>"
  		else
  		 	pStr += "<ruby style='ruby-align:center'> "+str.charAt(i) + " <rt>"+pinyin(str.charAt(i)) + "</rt></ruby>"
 	}
 	return pStr
}


function toPinyinOnly(str)
{
 	var pStr = ""
 	for (var i=0; i<str.length; i++)
 	{
  		if (str.charAt(i) == "\n") 
  			pStr += "<br>"
  		else
  		   	pStr += " "+pinyin(str.charAt(i)) ;
 	}
 	return pStr
}

function toPinyinShengmu(str)
{
 	var pStr = ""
 	for (var i=0; i<str.length; i++)
 	{
  		if (str.charAt(i) == "\n") 
  			pStr += "";
  		else
  		    pStr += pinyin(str.charAt(i)).charAt(0) ;
 	}
 	return pStr
}

function pinyinSort(a, b)
{
 	var rValue = 0
 	for (var i=0; i<a.length; i++)
 	{
		var pinA = pinyin(a.charAt(i))
  		var pinB = pinyin(b.charAt(i))
  		if (rValue = pinA > pinB ? 1 : pinA < pinB ? -1 : 0) break
 	}
	return rValue
}
</script>
<body bgcolor="#FFFFFF" text="#000000"
	onload="init(),selectedoff(),reportErrors();">
	<jsp:include flush="true" page="/waiting.inc" />
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
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">账户处理&gt;凭证录入</font>
									</td>
									<td class=font14>
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
		<html:form action="/credenceFillinTaSaveAC.do" method="POST">
			<html:hidden name="credenceFillinTaAF" property="listAllContent" />
			<tr>
				<td class=td3>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr align="center">
							<td colspan="8" class="td1" align="left">
								<table width="100%" border="0" cellspacing="3" cellpadding="0">
									<tr>
										<td class=td1 width="9%">
											所属办事处
											<font color="#FF0000">*</font>
										</td>
										<td class="td4" width="31%">
											<html:select name="credenceFillinTaAF"
												property="credenceFillinTaShowDTO.office"
												styleClass="input4" onchange="executeAjaxNumDate();"
												onkeydown="enterNextFocus1();">
												<html:options collection="officeList" property="value"
													labelProperty="label" />
											</html:select>
										</td>
										<td width="8%">
											&nbsp;
										</td>
										<td class="td4" width="10%">
											&nbsp;
										</td>
										<td width="12%">
											&nbsp;
										</td>
										<td class="td4" width="30%">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr align="center">
							<td colspan="8" class="td1">
								<b>记账凭证</b>
							</td>
						</tr>
						<tr align="center">
							<td width="10%" class="td1">
								凭证字
								<font color="#FF0000">*</font>
							</td>
							<td class="td1" width="10%">
								<html:select name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.credenceCharacter"
									styleClass="input4" onkeydown="enterNextFocus1();">
									<html:options collection="credenceCharacterList"
										property="value" labelProperty="label" />
								</html:select>
							</td>
							<td width="13%" class=td1>
								凭证号
							</td>
							<td class="td1" width="12%">
								<html:text name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.credenceNum"
									styleClass="input7" readonly="true"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="13%" class="td1">
								附单据
								<font color="#FF0000">*</font>
							</td>
							<td class="td1" width="12%">
								<html:text name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.oldCredenceNum"
									styleClass="input7" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="13%" class=td1>
								日期
								<font color="#FF0000">*</font>
							</td>
							<td class="td1" width="12%">
								<html:text name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.chargeUpDate"
									styleClass="input7" onblur="return executeAjaxNumByDate();"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
					</table>
					<table width="95%" border="1" cellspacing="0" cellpadding="0"
						align="center" bordercolordark="#FFFFFF"
						bordercolorlight="#00B5DB" id="ddoTable">
						<tr align="center">
							<td height="23">
								摘要
							</td>
							<td height="23" width="15%">
								自由摘要
							</td>
							<td height="23" width="40%">
								会计科目
							</td>
							<td height="23" width="97">
								借方金额
							</td>
							<td height="23" width="97">
								贷方金额
							</td>
						</tr>
						<tr onclick='gettr("1")'>
							<td height="34">
								<input type="text" name="summay0" class="input8"
									autocomplete="off"
									onkeyup="executeAjax('summay0','search_suggest0')"
									onblur="del_div(0);"
									onkeydown="enterNextFocus1();">
								<a onclick="tosummay(0)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
								<br />
								<div id=search_suggest0
									style="display:none;position: absolute;background-color: #FFFFFF;text-align: left;border: 1px solid #000000;">
								</div>
							</td>
							<td height="34">
								<input type="text" name="freeSummay0" class="input8"
									onkeydown="enterNextFocus1();">
							</td>
							<td height="34">
								<input type="text" name="subjectCode0" class="input8"
									onkeydown="gotoKey('debit0');" onblur="executeAjaxIn1(6)"
									onfocus="getNum('subjectCode0'),executeAjaxDir(0);">
								<a onclick="tosubject(6)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
							</td>
							<td height="34" align="right" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="debit0" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('debit',3)" onfocus="banlance('debit0')"
									onBlur="clearZero('debit0');"
									onkeydown="onlyNum(),deleEndTwo('debit0'),gotoKey('credit0');"
									onkeyup="banlance('debit0')" onpaste="return false">
							</td>
							<td height="34" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="credit0" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('credit',4)" onfocus="banlance('credit0')"
									onBlur="clearZero('credit0');"
									onkeydown="onlyNum(),deleEndTwo('credit0'),keyAddRow('1'),enterNextFocus1();"
									onkeyup="banlance('credit0')" onpaste="return false">
							</td>
						</tr>
						<tr onclick='gettr("2")'>
							<td height="34">
								<input type="text" name="summay1" class="input8"
									autocomplete="off"
									onkeyup="executeAjax('summay1','search_suggest1')"
									onblur="del_div('1');"
									onfocus="copyPreRow('summay1','summay0')"
									onkeydown="enterNextFocus1();">
								<a onclick="tosummay(1)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
								<br />
								<div id="search_suggest1"
									style="display:'none';position: absolute;background-color: #FFFFFF;text-align: left;border: 1px solid #000000;">
								</div>
							</td>
							<td height="34">
								<input type="text" name="freeSummay1" class="input8"
									onkeydown="enterNextFocus1();">
							</td>
							<td height="34">
								<input type="text" name="subjectCode1" class="input8"
									onkeydown="gotoKey('debit1');" onblur="executeAjaxIn1(11)"
									onfocus="getNum('subjectCode1'),executeAjaxDir(1);copyPreRow('subjectCode1','subjectCode0');">
								<a onclick="tosubject(11)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
							</td>
							<td height="34" align="right" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="debit1" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('debit',3)" onfocus="banlance('debit1')"
									onBlur="clearZero('debit1');"
									onkeydown="onlyNum(),deleEndTwo('debit1'),gotoKey('credit1');"
									onkeyup="banlance('debit1')" onpaste="return false">
							</td>
							<td height="34" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="credit1" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('credit',4)" onfocus="banlance('credit1')"
									onBlur="clearZero('credit1');"
									onkeydown="onlyNum(),deleEndTwo('credit1'),keyAddRow('2'),enterNextFocus1();"
									onkeyup="banlance('credit1')" onpaste="return false">
							</td>
						</tr>
						<tr onclick='gettr("3")'>
							<td height="34">
								<input type="text" name="summay2" class="input8"
									autocomplete="off"
									onkeyup="executeAjax('summay2','search_suggest2')"
									onblur="del_div('2');"
									onfocus="copyPreRow('summay2','summay1')"
									onkeydown="enterNextFocus1();">
								<a onclick="tosummay(2)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
								<br />
								<div id="search_suggest2"
									style="display:'none';position: absolute;background-color: #FFFFFF;text-align: left;border: 1px solid #000000;">
								</div>
							</td>
							<td height="34">
								<input type="text" name="freeSummay2" class="input8"
									onkeydown="enterNextFocus1();">
							</td>
							<td height="34">
								<input type="text" name="subjectCode2" class="input8"
									onkeydown="gotoKey('debit2');" onblur="executeAjaxIn1(16)"
									onfocus="getNum('subjectCode2'),executeAjaxDir(2);copyPreRow('subjectCode2','subjectCode1');">
								<a onclick="tosubject(16)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
							</td>
							<td height="34" align="right" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="debit2" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('debit',3)" onfocus="banlance('debit2')"
									onBlur="clearZero('debit2');"
									onkeydown="onlyNum(),deleEndTwo('debit2'),gotoKey('credit2');"
									onkeyup="banlance('debit2')" onpaste="return false">
							</td>
							<td height="34" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="credit2" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('credit',4)" onfocus="banlance('credit2')"
									onBlur="clearZero('credit2');"
									onkeydown="onlyNum(),deleEndTwo('credit2'),keyAddRow('3'),enterNextFocus1();"
									onkeyup="banlance('credit2')" onpaste="return false">
							</td>
						</tr>
						<tr onclick='gettr("4")'>
							<td height="34">
								<input type="text" name="summay3" class="input8"
									autocomplete="off"
									onkeyup="executeAjax('summay3','search_suggest3')"
									onblur="del_div('3');"
									onfocus="copyPreRow('summay3','summay2')"
									onkeydown="enterNextFocus1();">
								<a onclick="tosummay(3)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
								<br />
								<div id="search_suggest3"
									style="display:'none';position: absolute;background-color: #FFFFFF;text-align: left;border: 1px solid #000000;">
								</div>
							</td>
							<td height="34">
								<input type="text" name="freeSummay3" class="input8"
									onkeydown="enterNextFocus1();">
							</td>
							<td height="34">
								<input type="text" name="subjectCode3" class="input8"
									onkeydown="gotoKey('debit3');" onblur="executeAjaxIn1(21)"
									onfocus="getNum('subjectCode3'),executeAjaxDir(3);copyPreRow('subjectCode3','subjectCode2');">
								<a onclick="tosubject(21)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
							</td>
							<td height="34" align="right" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="debit3" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('debit',3)" onfocus="banlance('debit3')"
									onBlur="clearZero('debit3');"
									onkeydown="onlyNum(),deleEndTwo('debit3'),gotoKey('credit3');"
									onkeyup="banlance('debit3')" onpaste="return false">
							</td>
							<td height="34" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="credit3" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('credit',4)" onfocus="banlance('credit3')"
									onBlur="clearZero('credit3');"
									onkeydown="onlyNum(),deleEndTwo('credit3'),keyAddRow('4'),enterNextFocus1();"
									onkeyup="banlance('credit3')" onpaste="return false">
							</td>
						</tr>
						<tr onclick='gettr("5")'>
							<td height="34">
								<input type="text" name="summay4" class="input8"
									autocomplete="off"
									onkeyup="executeAjax('summay4','search_suggest4')"
									onblur="del_div('4');"
									onfocus="copyPreRow('summay4','summay3')"
									onkeydown="enterNextFocus1();">
								<a onclick="tosummay(4)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
								<br />
								<div id="search_suggest4"
									style="display:'none';position: absolute;background-color: #FFFFFF;text-align: left;border: 1px solid #000000;">
								</div>
							</td>
							<td height="34">
								<input type="text" name="freeSummay4" class="input8"
									onkeydown="enterNextFocus1();">
							</td>
							<td height="34">
								<input type="text" name="subjectCode4" class="input8"
									onkeydown="gotoKey('debit4');" onblur="executeAjaxIn1(26)"
									onfocus="getNum('subjectCode4'),executeAjaxDir(4);copyPreRow('subjectCode4','subjectCode3');">
								<a onclick="tosubject(26)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
							</td>
							<td height="34" align="right" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="debit4" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('debit',3)" onfocus="banlance('debit4')"
									onBlur="clearZero('debit4');"
									onkeydown="onlyNum(),deleEndTwo('debit4'),gotoKey('credit4');"
									onkeyup="banlance('debit4')" onpaste="return false">
							</td>
							<td height="34" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="credit4" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('credit',4)" onfocus="banlance('credit4')"
									onBlur="clearZero('credit4');"
									onkeydown="onlyNum(),deleEndTwo('credit4'),keyAddRow('5'),enterNextFocus1();"
									onkeyup="banlance('credit4')" onpaste="return false">
							</td>
						</tr>
						<tr onclick='gettr("6")'>
							<td height="34">
								<input type="text" name="summay5" class="input8"
									autocomplete="off"
									onkeyup="executeAjax('summay5','search_suggest5')"
									onblur="del_div('5');"
									onfocus="copyPreRow('summay5','summay4')"
									onkeydown="enterNextFocus1();">
								<a onclick="tosummay(5)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
								<br />
								<div id="search_suggest5"
									style="display:'none';position: absolute;background-color: #FFFFFF;text-align: left;border: 1px solid #000000;">
								</div>
							</td>
							<td height="34">
								<input type="text" name="freeSummay5" class="input8"
									onkeydown="enterNextFocus1();">
							</td>
							<td height="34">
								<input type="text" name="subjectCode5" class="input8"
									onkeydown="gotoKey('debit5');" onblur="executeAjaxIn1(31)"
									onfocus="getNum('subjectCode5'),executeAjaxDir(5);copyPreRow('subjectCode5','subjectCode4');">
								<a onclick="tosubject(31)" style="cursor:hand"><img
										src="<%=path%>/img/8.png"> </a>
							</td>
							<td height="34" align="right" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="debit5" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('debit',3)" onfocus="banlance('debit5')"
									onBlur="clearZero('debit5');"
									onkeydown="onlyNum(),deleEndTwo('debit5'),gotoKey('credit5');"
									onkeyup="banlance('debit5')" onpaste="return false">
							</td>
							<td height="34" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="credit5" class="input6"
									style="ime-mode:disabled" autocomplete="off"
									onchange="sum1('credit',4)" onfocus="banlance('credit5')"
									onBlur="clearZero('credit5');"
									onkeydown="onlyNum(),deleEndTwo('credit5'),keyAddRow('6'),enterNextFocus1();"
									onkeyup="banlance('credit5')" onpaste="return false">
							</td>
						</tr>
						<tr>
							<td height="23" colspan="3" align="center">
								合计:
							</td>
							<td height="23" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="sum3" class="input6" readonly="true"
									onclick="sum1('debit',3)" onkeydown="enterNextFocus1();">
							</td>
							<td height="23" width="97"
								background="<%=path%>/img/bg_cwline.gif">
								<input type="text" name="sum4" class="input6" readonly="true"
									onclick="sum1('credit',4)" onkeydown="enterNextFocus1();">
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr align="center">
							<td width="13%" class="td1" height="33">
								结算方式
								<font color="#FF0000">*</font>
							</td>
							<td class="td1" height="33" width="18%">
								<html:select name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.settType" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:options collection="settTypeList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="13%" class=td1 height="33">
								结算号
							</td>
							<td class="td1" height="33" width="19%">
								<html:text name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.settNum" styleClass="input7"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="13%" class="td1" height="33">
								结算日期
							</td>
							<td class="td1" height="33" width="19%">
								<html:text name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.settDate" styleClass="input7"
									onkeydown="gotoAccpsn();"></html:text>
							</td>
						</tr>
						<tr align="center">
							<td width="13%" class="td1">
								审核
							</td>
							<td class="td1">
								<html:text name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.checkpsn" styleClass="input7"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>
							</td>
							<td width="13%" class=td1>
								过账
							</td>
							<td class="td1">
								<html:text name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.clearpsn" styleClass="input7"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>
							</td>
							<td width="13%" class="td1">
								制单
							</td>
							<td class="td1">
								<html:text name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.makebill" styleClass="input7"
									readonly="true" onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr align="center">
							<td width="13%" class="td1">
								出纳
							</td>
							<td class="td1">
								<html:text name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.accountpsn"
									styleClass="input7" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="13%" class=td1>
								主管会计
							</td>
							<td class="td1">
								<html:text name="credenceFillinTaAF"
									property="credenceFillinTaShowDTO.accountCharge"
									styleClass="input7" onkeydown="return gotoButton();"></html:text>
							</td>
							<td width="13%" class="td1">
								科目余额
							</td>
							<td class="td1">
								<input name="balanceDirection" type="text" class="input7"
									readonly="true" onkeydown="enterNextFocus1();">
							</td>
						</tr>
						<input type="hidden" name="flag" />
						<html:hidden name="credenceFillinTaAF" property="type" />
						<html:hidden name="credenceFillinTaAF"
							property="credenceFillinTaShowDTO.bookId" />
						<html:hidden name="credenceFillinTaAF" property="typeButton" />
						<html:hidden name="credenceFillinTaAF" property="bookSt" />
						<html:hidden name="credenceFillinTaAF"
							property="credenceFillinTaShowDTO.reserveC" />
						<html:hidden name="credenceFillinTaAF"
							property="credenceFillinTaShowDTO.reserveB" />
						<html:hidden name="credenceFillinTaAF"
							property="credenceFillinTaShowDTO.reserveA" />
					</table>
					<html:hidden name="credenceFillinTaAF" property="typeButton" />
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<logic:empty name="credenceFillinTaAF" property="typeButton">
											<td width="70">
												<input id="Button1" type="button" value="确定" name="Button1"
													class="buttona" onclick="return validate(this);">
											</td>
										</logic:empty>
										<logic:notEmpty name="credenceFillinTaAF"
											property="typeButton">
											<td width="70">
												<input id="Button1" type="button" value="确定" name="Button1"
													class="buttona" onclick="return validate(this);">
											</td>
										</logic:notEmpty>
										<td width="70">
											<input id="Button2" onclick="addBlankRow();" type="button"
												value="增加行" name="Button1" class="buttona">
										</td>
										<td width="70">
											<input id="Button2" onclick="delBlankRow();" type="button"
												value="删除行" name="Button1" class="buttona">
										</td>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</html:form>
	</table>
</body>
</html:html>
